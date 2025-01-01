/*
 * ====================================================================
 * 
 * The ObjectStyle Group Software License, Version 1.0
 * 
 * Copyright (c) 2006 The ObjectStyle Group and individual authors of the
 * software. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 * include the following acknowlegement: "This product includes software
 * developed by the ObjectStyle Group (http://objectstyle.org/)." Alternately,
 * this acknowlegement may appear in the software itself, if and wherever such
 * third-party acknowlegements normally appear.
 * 
 * 4. The names "ObjectStyle Group" and "Cayenne" must not be used to endorse or
 * promote products derived from this software without prior written permission.
 * For written permission, please contact andrus@objectstyle.org.
 * 
 * 5. Products derived from this software may not be called "ObjectStyle" nor
 * may "ObjectStyle" appear in their names without prior written permission of
 * the ObjectStyle Group.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * OBJECTSTYLE GROUP OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on
 * behalf of the ObjectStyle Group. For more information on the ObjectStyle
 * Group, please see <http://objectstyle.org/>.
 *  
 */
package org.objectstyle.wolips.eomodeler.editors.attribute;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.objectstyle.wolips.baseforplugins.util.ComparisonUtils;
import org.objectstyle.wolips.eomodeler.Messages;
import org.objectstyle.wolips.eomodeler.core.model.EOAttribute;
import org.objectstyle.wolips.eomodeler.core.model.EOAttributePath;
import org.objectstyle.wolips.eomodeler.utils.BooleanUpdateValueStrategy;
import org.objectstyle.wolips.eomodeler.utils.ComboViewerBinding;
import org.objectstyle.wolips.eomodeler.utils.FormUtils;
import org.objectstyle.wolips.eomodeler.utils.UglyFocusHackWorkaroundListener;

/**
 * this is the part on the left bottom side. after you click the 'Advanced' Tab,
 * this code get executed.
 */
public class EOAttributeAdvancedEditorSection extends AbstractPropertySection {
	private EOAttribute _attribute;

	private Button _readOnlyButton;

	private Button _clientClassPropertyButton;
	private Button _generateSourceButton;
	private Button _coreDataButton;
	private Button _qtClientButton;
	private Button _auditButton;
	private Button _deprecatedButton;
	private Button _encryptionButton;

	private ComboViewer _copyTypeComboViewer;
	private ComboViewerBinding _copyTypeBinding;

	private ComboViewer _d2wTypeComboViewer;
	private ComboViewerBinding _d2wTypeBinding;
	
	private Text _readFormatText;

	private Text _writeFormatText;

	private Text _validationText;
	private Text _convertText;
		
	private DataBindingContext _bindingContext;

	public EOAttributeAdvancedEditorSection() {
		// DO NOTHING
	}

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite form = getWidgetFactory().createFlatFormComposite(parent);
		FormLayout formLayout = new FormLayout();
		form.setLayout(formLayout);

		Composite topForm = FormUtils.createForm(getWidgetFactory(), form);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOAttribute." + EOAttribute.READ_FORMAT), SWT.NONE);
		_readFormatText = new Text(topForm, SWT.BORDER);
		GridData readFormatFieldLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		_readFormatText.setLayoutData(readFormatFieldLayoutData);
		UglyFocusHackWorkaroundListener.addListener(_readFormatText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOAttribute." + EOAttribute.WRITE_FORMAT), SWT.NONE);
		_writeFormatText = new Text(topForm, SWT.BORDER);
		GridData writeFormatFieldLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		_writeFormatText.setLayoutData(writeFormatFieldLayoutData);
		UglyFocusHackWorkaroundListener.addListener(_writeFormatText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOAttribute." + EOAttribute.VALIDATION), SWT.NONE);
		_validationText = new Text(topForm, SWT.BORDER);
		_validationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_validationText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOAttribute." + EOAttribute.CONVERT), SWT.NONE);
		_convertText = new Text(topForm, SWT.BORDER);
		_convertText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_convertText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOAttribute." + EOAttribute.D2WTYPE), SWT.NONE);
		Combo d2wTypeCombo = new Combo(topForm, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		d2wTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		_d2wTypeComboViewer = new ComboViewer(d2wTypeCombo);
		_d2wTypeComboViewer.setLabelProvider(new D2WTypeLabelProvider());
		_d2wTypeComboViewer.setContentProvider(new D2WTypeAttributeContentProvider());
		_d2wTypeComboViewer.setInput(EOAttribute.D2WTYPE);

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_readOnlyButton = new Button(topForm, SWT.CHECK);
		_readOnlyButton.setText(Messages.getString("EOAttribute." + EOAttribute.READ_ONLY));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_clientClassPropertyButton = new Button(topForm, SWT.CHECK);
		_clientClassPropertyButton.setText(Messages.getString("EOAttribute." + EOAttribute.CLIENT_CLASS_PROPERTY));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_generateSourceButton = new Button(topForm, SWT.CHECK);
		_generateSourceButton.setText(Messages.getString("EOAttribute." + EOAttribute.GENERATE_SOURCE));
		
		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_coreDataButton = new Button(topForm, SWT.CHECK);
		_coreDataButton.setText(Messages.getString("EOAttribute." + EOAttribute.CORE_DATA));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_qtClientButton = new Button(topForm, SWT.CHECK);
		_qtClientButton.setText(Messages.getString("EOAttribute." + EOAttribute.QT_CLIENT));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_auditButton = new Button(topForm, SWT.CHECK);
		_auditButton.setText(Messages.getString("EOAttribute." + EOAttribute.AUDIT));
		
		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_deprecatedButton = new Button(topForm, SWT.CHECK);
		_deprecatedButton.setText(Messages.getString("EOAttribute." + EOAttribute.DEPRECATED));
		
		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_encryptionButton = new Button(topForm, SWT.CHECK);
		_encryptionButton.setText(Messages.getString("EOAttribute." + EOAttribute.ENCRYPTION));
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOAttribute." + EOAttribute.COPY_TYPE), SWT.NONE);
		Combo copyTypeCombo = new Combo(topForm, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		copyTypeCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		_copyTypeComboViewer = new ComboViewer(copyTypeCombo);
		_copyTypeComboViewer.setLabelProvider(new EOCopyTypeLabelProvider());
		_copyTypeComboViewer.setContentProvider(new EOCopyTypeAttributeContentProvider());
		_copyTypeComboViewer.setInput(EOAttribute.COPY_TYPE);
		
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (ComparisonUtils.equals(selection, getSelection())) {
			return;
		}
		
		super.setInput(part, selection);
		disposeBindings();

		Object selectedObject = ((IStructuredSelection) selection).getFirstElement();
		if (selectedObject instanceof EOAttribute) {
			_attribute = (EOAttribute) selectedObject;
		} else if (selectedObject instanceof EOAttributePath) {
			_attribute = ((EOAttributePath) selectedObject).getChildAttribute();
		}

		if (_attribute != null) {
			_bindingContext = new DataBindingContext();
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_readOnlyButton),
					WidgetProperties.buttonSelection().observe(_readOnlyButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.READ_ONLY),
					BeanProperties.value(EOAttribute.READ_ONLY).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_generateSourceButton),
					WidgetProperties.buttonSelection().observe(_generateSourceButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.GENERATE_SOURCE),
					BeanProperties.value(EOAttribute.GENERATE_SOURCE).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_clientClassPropertyButton),
					WidgetProperties.buttonSelection().observe(_clientClassPropertyButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.CLIENT_CLASS_PROPERTY),
					BeanProperties.value(EOAttribute.CLIENT_CLASS_PROPERTY).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_coreDataButton),
					WidgetProperties.buttonSelection().observe(_coreDataButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.CORE_DATA),
					BeanProperties.value(EOAttribute.CORE_DATA).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_qtClientButton),
					WidgetProperties.buttonSelection().observe(_qtClientButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.QT_CLIENT),
					BeanProperties.value(EOAttribute.QT_CLIENT).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_auditButton),
					WidgetProperties.buttonSelection().observe(_auditButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.AUDIT),
					BeanProperties.value(EOAttribute.AUDIT).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_deprecatedButton),
					WidgetProperties.buttonSelection().observe(_deprecatedButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.DEPRECATED),
					BeanProperties.value(EOAttribute.DEPRECATED).observe(_attribute),
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_encryptionButton),
					WidgetProperties.buttonSelection().observe(_encryptionButton),
					//BeansObservables.observeValue(_attribute, EOAttribute.ENCRYPTION),
					BeanProperties.value(EOAttribute.ENCRYPTION).observe(_attribute),
					null, new BooleanUpdateValueStrategy());

			_bindingContext.bindValue(
					//SWTObservables.observeText(_readFormatText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_readFormatText),
					//BeansObservables.observeValue(_attribute, EOAttribute.READ_FORMAT),
					BeanProperties.value(EOAttribute.READ_FORMAT).observe(_attribute),
					null, null);
			_bindingContext.bindValue(
					//SWTObservables.observeText(_writeFormatText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_writeFormatText),
					//BeansObservables.observeValue(_attribute, EOAttribute.WRITE_FORMAT),
					BeanProperties.value(EOAttribute.WRITE_FORMAT).observe(_attribute),
					null, null);
			_bindingContext.bindValue(
					//SWTObservables.observeText(_validationText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_validationText),
					//BeansObservables.observeValue(_attribute, EOAttribute.VALIDATION),
					BeanProperties.value(EOAttribute.VALIDATION).observe(_attribute),
					null, null);
			_bindingContext.bindValue(
					//SWTObservables.observeText(_convertText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_convertText),
					//BeansObservables.observeValue(_attribute, EOAttribute.CONVERT),
					BeanProperties.value(EOAttribute.CONVERT).observe(_attribute),
					null, null);
			
			_copyTypeComboViewer.setInput(_attribute);
			_copyTypeBinding = new ComboViewerBinding(_copyTypeComboViewer, _attribute, EOAttribute.COPY_TYPE, null, null, null);
			
			_d2wTypeComboViewer.setInput(_attribute);
			_d2wTypeBinding = new ComboViewerBinding(_d2wTypeComboViewer, _attribute, EOAttribute.D2WTYPE, null, null, null);
		}
	}

	protected void disposeBindings() {
		if (_bindingContext != null) {
			_bindingContext.dispose();
		}
		if (_copyTypeBinding != null) {
			_copyTypeBinding.dispose();
		}
		if (_d2wTypeBinding != null) {
			_d2wTypeBinding.dispose();
		}
	}

	public void dispose() {
		super.dispose();
		disposeBindings();
	}
}
