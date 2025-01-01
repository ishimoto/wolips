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
package org.objectstyle.wolips.eomodeler.editors.entity;

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
import org.objectstyle.wolips.eomodeler.core.model.EOEntity;
import org.objectstyle.wolips.eomodeler.core.model.EOModel;
import org.objectstyle.wolips.eomodeler.editors.attribute.EOCopyTypeEntityContentProvider;
import org.objectstyle.wolips.eomodeler.editors.attribute.EOCopyTypeLabelProvider;
import org.objectstyle.wolips.eomodeler.utils.BooleanUpdateValueStrategy;
import org.objectstyle.wolips.eomodeler.utils.ComboViewerBinding;
import org.objectstyle.wolips.eomodeler.utils.FormUtils;
import org.objectstyle.wolips.eomodeler.utils.UglyFocusHackWorkaroundListener;

public class EOEntityAdvancedEditorSection extends AbstractPropertySection {
	private EOEntity _entity;

	private Text _maxNumberOfInstancesToBatchFetchText;

	private Button _cacheInMemoryButton;

	private Button _readOnlyButton;
	
	private Button _coreDataButton;

	private Button _restControllerButton;

	private Button _qtClientButton;
	
	private Button _auditButton;
	
	private Button _subEntityButton;
	
	private Text _tagText;
	
	private ComboViewer _copyTypeComboViewer;
	private ComboViewerBinding _copyTypeBinding;

	private Button _immutableButton;

	private Button _generateSourceButton;

	private Button _rawRowsOnlyButton;

	private Text _externalQueryText;

	private Text _clientClassNameText;

	private Text _parentClassNameText;

	private ComboViewer _partialEntityComboViewer;

	private ComboViewerBinding _partialEntityBinding;

	private DataBindingContext _bindingContext;

	public EOEntityAdvancedEditorSection() {
		// DO NOTHING
	}

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite form = getWidgetFactory().createFlatFormComposite(parent);
		FormLayout formLayout = new FormLayout();
		form.setLayout(formLayout);

		Composite topForm = FormUtils.createForm(getWidgetFactory(), form);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.MAX_NUMBER_OF_INSTANCES_TO_BATCH_FETCH), SWT.NONE);
		_maxNumberOfInstancesToBatchFetchText = new Text(topForm, SWT.BORDER);
		GridData maxNumberOfInstancesToBatchFetchFieldLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		_maxNumberOfInstancesToBatchFetchText.setLayoutData(maxNumberOfInstancesToBatchFetchFieldLayoutData);

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_cacheInMemoryButton = new Button(topForm, SWT.CHECK);
		_cacheInMemoryButton.setText(Messages.getString("EOEntity." + EOEntity.CACHES_OBJECTS));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_readOnlyButton = new Button(topForm, SWT.CHECK);
		_readOnlyButton.setText(Messages.getString("EOEntity." + EOEntity.READ_ONLY));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_coreDataButton = new Button(topForm, SWT.CHECK);
		_coreDataButton.setText(Messages.getString("EOEntity." + EOEntity.CORE_DATA));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_restControllerButton = new Button(topForm, SWT.CHECK);
		_restControllerButton.setText(Messages.getString("EOEntity." + EOEntity.REST_CONTROLLER));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_qtClientButton = new Button(topForm, SWT.CHECK);
		_qtClientButton.setText(Messages.getString("EOEntity." + EOEntity.QT_CLIENT));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_auditButton = new Button(topForm, SWT.CHECK);
		_auditButton.setText(Messages.getString("EOEntity." + EOEntity.AUDIT));
		
		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_subEntityButton = new Button(topForm, SWT.CHECK);
		_subEntityButton.setText(Messages.getString("EOEntity." + EOEntity.HAS_SUB_ENTITY));
		
		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_immutableButton = new Button(topForm, SWT.CHECK);
		_immutableButton.setText(Messages.getString("EOEntity." + EOEntity.IMMUTABLE));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_generateSourceButton = new Button(topForm, SWT.CHECK);
		_generateSourceButton.setText(Messages.getString("EOEntity." + EOEntity.GENERATE_SOURCE));

		getWidgetFactory().createCLabel(topForm, "", SWT.NONE);
		_rawRowsOnlyButton = new Button(topForm, SWT.CHECK);
		_rawRowsOnlyButton.setText(Messages.getString("EOEntity." + EOEntity.RAW_ROWS_ONLY));

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.EXTERNAL_QUERY), SWT.NONE);
		_externalQueryText = new Text(topForm, SWT.BORDER);
		GridData externalQueryFieldLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		_externalQueryText.setLayoutData(externalQueryFieldLayoutData);
		UglyFocusHackWorkaroundListener.addListener(_externalQueryText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.CLIENT_CLASS_NAME), SWT.NONE);
		_clientClassNameText = new Text(topForm, SWT.BORDER);
		GridData clientClassNameLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		_clientClassNameText.setLayoutData(clientClassNameLayoutData);
		UglyFocusHackWorkaroundListener.addListener(_clientClassNameText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.PARENT_CLASS_NAME), SWT.NONE);
		_parentClassNameText = new Text(topForm, SWT.BORDER);
		GridData parentClassNameLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		_parentClassNameText.setLayoutData(parentClassNameLayoutData);
		UglyFocusHackWorkaroundListener.addListener(_parentClassNameText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.TAG), SWT.NONE);
		_tagText = new Text(topForm, SWT.BORDER);
		_tagText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_tagText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.PARTIAL_ENTITY), SWT.NONE);
		Combo partialEntityCombo = new Combo(topForm, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		_partialEntityComboViewer = new ComboViewer(partialEntityCombo);
		_partialEntityComboViewer.setLabelProvider(new EOEntityLabelProvider());
		_partialEntityComboViewer.setContentProvider(new EOEntityListContentProvider(true, false, false));
		GridData entityComboLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		partialEntityCombo.setLayoutData(entityComboLayoutData);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.COPY_TYPE), SWT.NONE);
		Combo copyTypeCombo = new Combo(topForm, SWT.BORDER | SWT.FLAT | SWT.READ_ONLY);
		_copyTypeComboViewer = new ComboViewer(copyTypeCombo);
		_copyTypeComboViewer.setLabelProvider(new EOCopyTypeLabelProvider());
		_copyTypeComboViewer.setContentProvider(new EOCopyTypeEntityContentProvider());
		_copyTypeComboViewer.setInput(EOEntity.CORE_DATA);
		GridData copyTypeComboLayoutData = new GridData(GridData.FILL_HORIZONTAL);
		copyTypeCombo.setLayoutData(copyTypeComboLayoutData);
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (ComparisonUtils.equals(selection, getSelection())) {
			return;
		}
		
		super.setInput(part, selection);
		disposeBindings();

		Object selectedObject = ((IStructuredSelection) selection).getFirstElement();
		_entity = (EOEntity) selectedObject;
		if (_entity != null) {
			_bindingContext = new DataBindingContext();
			_bindingContext.bindValue(
					//SWTObservables.observeText(_maxNumberOfInstancesToBatchFetchText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_maxNumberOfInstancesToBatchFetchText), 
					//BeansObservables.observeValue(_entity, EOEntity.MAX_NUMBER_OF_INSTANCES_TO_BATCH_FETCH),
					BeanProperties.value(EOEntity.MAX_NUMBER_OF_INSTANCES_TO_BATCH_FETCH).observe(_entity), 
					null, null);
			// new BindSpec(null, null, new RegexStringValidator("^[0-9]*$",
			// "^[0-9]+$", "Please enter a number"), null));
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_cacheInMemoryButton),
					WidgetProperties.buttonSelection().observe(_cacheInMemoryButton), 
					//BeansObservables.observeValue(_entity, EOEntity.CACHES_OBJECTS),
					BeanProperties.value(EOEntity.CACHES_OBJECTS).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_readOnlyButton),
					WidgetProperties.buttonSelection().observe(_readOnlyButton), 
					//BeansObservables.observeValue(_entity, EOEntity.READ_ONLY),
					BeanProperties.value(EOEntity.READ_ONLY).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_coreDataButton),
					WidgetProperties.buttonSelection().observe(_coreDataButton), 
					//BeansObservables.observeValue(_entity, EOEntity.CORE_DATA),
					BeanProperties.value(EOEntity.CORE_DATA).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_restControllerButton),
					WidgetProperties.buttonSelection().observe(_restControllerButton), 
					//BeansObservables.observeValue(_entity, EOEntity.REST_CONTROLLER),
					BeanProperties.value(EOEntity.REST_CONTROLLER).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_qtClientButton),
					WidgetProperties.buttonSelection().observe(_qtClientButton), 
					//BeansObservables.observeValue(_entity, EOEntity.QT_CLIENT),
					BeanProperties.value(EOEntity.QT_CLIENT).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_auditButton),
					WidgetProperties.buttonSelection().observe(_auditButton), 
					//BeansObservables.observeValue(_entity, EOEntity.AUDIT),
					BeanProperties.value(EOEntity.AUDIT).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_subEntityButton),
					WidgetProperties.buttonSelection().observe(_subEntityButton), 
					//BeansObservables.observeValue(_entity, EOEntity.HAS_SUB_ENTITY),
					BeanProperties.value(EOEntity.HAS_SUB_ENTITY).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_immutableButton),
					WidgetProperties.buttonSelection().observe(_immutableButton), 
					//BeansObservables.observeValue(_entity, EOEntity.IMMUTABLE),
					BeanProperties.value(EOEntity.IMMUTABLE).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_generateSourceButton),
					WidgetProperties.buttonSelection().observe(_generateSourceButton), 
					//BeansObservables.observeValue(_entity, EOEntity.GENERATE_SOURCE),
					BeanProperties.value(EOEntity.GENERATE_SOURCE).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeSelection(_rawRowsOnlyButton),
					WidgetProperties.buttonSelection().observe(_rawRowsOnlyButton), 
					//BeansObservables.observeValue(_entity, EOEntity.RAW_ROWS_ONLY),
					BeanProperties.value(EOEntity.RAW_ROWS_ONLY).observe(_entity), 
					null, new BooleanUpdateValueStrategy());
			_bindingContext.bindValue(
					//SWTObservables.observeText(_externalQueryText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_externalQueryText), 
					//BeansObservables.observeValue(_entity, EOEntity.EXTERNAL_QUERY),
					BeanProperties.value(EOEntity.EXTERNAL_QUERY).observe(_entity), 
					null, null);
			_bindingContext.bindValue(
					//SWTObservables.observeText(_clientClassNameText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_clientClassNameText), 
					//BeansObservables.observeValue(_entity, EOEntity.CLIENT_CLASS_NAME),
					BeanProperties.value(EOEntity.CLIENT_CLASS_NAME).observe(_entity), 
					null, null);
			_bindingContext.bindValue(
					//SWTObservables.observeText(_parentClassNameText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_parentClassNameText), 
					//BeansObservables.observeValue(_entity, EOEntity.PARENT_CLASS_NAME),
					BeanProperties.value(EOEntity.PARENT_CLASS_NAME).observe(_entity), 
					null, null);
			_bindingContext.bindValue(
					//SWTObservables.observeText(_tagText, SWT.Modify),
					WidgetProperties.text(SWT.Modify).observe(_tagText), 
					//BeansObservables.observeValue(_entity, EOEntity.TAG),
					BeanProperties.value(EOEntity.TAG).observe(_entity), 
					null, null);

			_partialEntityComboViewer.setInput(_entity);
			_partialEntityBinding = new ComboViewerBinding(_partialEntityComboViewer, _entity, EOEntity.PARTIAL_ENTITY, _entity.getModel(), EOModel.ENTITIES, EOEntityListContentProvider.BLANK_ENTITY);

			_copyTypeComboViewer.setInput(_entity);
			_copyTypeBinding = new ComboViewerBinding(_copyTypeComboViewer, _entity, EOEntity.COPY_TYPE, null, null, null);		
		}
	}

	protected void disposeBindings() {
		if (_bindingContext != null) {
			_bindingContext.dispose();
			_partialEntityBinding.dispose();
		}
		if (_copyTypeBinding != null) {
			_copyTypeBinding.dispose();
		}
	}

	public void dispose() {
		super.dispose();
		disposeBindings();
	}
}
