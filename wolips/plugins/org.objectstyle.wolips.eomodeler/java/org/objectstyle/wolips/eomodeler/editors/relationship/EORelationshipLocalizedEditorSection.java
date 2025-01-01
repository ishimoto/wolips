package org.objectstyle.wolips.eomodeler.editors.relationship;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.objectstyle.wolips.baseforplugins.util.ComparisonUtils;
import org.objectstyle.wolips.eomodeler.Messages;
import org.objectstyle.wolips.eomodeler.core.model.EORelationship;
import org.objectstyle.wolips.eomodeler.core.model.EORelationshipPath;
import org.objectstyle.wolips.eomodeler.utils.FormUtils;
import org.objectstyle.wolips.eomodeler.utils.UglyFocusHackWorkaroundListener;

/**
 * this is the part on the left bottom side. after you click the 'Localized' Tab,
 * this code get executed.
 * 
 * @author ishimoto
 */
public class EORelationshipLocalizedEditorSection extends AbstractPropertySection {
	
	private EORelationship _relationship;

	private Text _englishText;
	private Text _frenchText;
	private Text _germanText;
	private Text _dutchText;
	private Text _italianText;
	private Text _japaneseText;
	private Text _chineseText;
	private Text _spanishText;
	private Text _portugueseText;
	private Text _brazilianText;
	
	private DataBindingContext _bindingContext;
	
	public EORelationshipLocalizedEditorSection() {
		// DO NOTHING
	}

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite form = getWidgetFactory().createFlatFormComposite(parent);
		FormLayout formLayout = new FormLayout();
		form.setLayout(formLayout);

		Composite topForm = FormUtils.createForm(getWidgetFactory(), form);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.ENGLISH), SWT.NONE);
		_englishText = new Text(topForm, SWT.BORDER);
		_englishText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		 UglyFocusHackWorkaroundListener.addListener(_englishText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.FRENCH), SWT.NONE);
		_frenchText = new Text(topForm, SWT.BORDER);
		_frenchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_frenchText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.GERMAN), SWT.NONE);
		_germanText = new Text(topForm, SWT.BORDER);
		_germanText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_germanText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.DUTCH), SWT.NONE);
		_dutchText = new Text(topForm, SWT.BORDER);
		_dutchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_dutchText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.ITALIAN), SWT.NONE);
		_italianText = new Text(topForm, SWT.BORDER);
		_italianText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_italianText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.JAPANESE), SWT.NONE);
		_japaneseText = new Text(topForm, SWT.BORDER);
		_japaneseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_japaneseText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.CHINESE), SWT.NONE);
		_chineseText = new Text(topForm, SWT.BORDER);
		_chineseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_chineseText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.SPANISH), SWT.NONE);
		_spanishText = new Text(topForm, SWT.BORDER);
		_spanishText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_spanishText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.PORTUGUESE), SWT.NONE);
		_portugueseText = new Text(topForm, SWT.BORDER);
		_portugueseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_portugueseText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EORelationship." + EORelationship.BRAZILIAN), SWT.NONE);
		_brazilianText = new Text(topForm, SWT.BORDER);
		_brazilianText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_brazilianText);
	}
	
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (ComparisonUtils.equals(selection, getSelection())) {
			return;
		}

		super.setInput(part, selection);
		disposeBindings();

		Object selectedObject = ((IStructuredSelection) selection).getFirstElement();
		if (selectedObject instanceof EORelationship) {
			_relationship = (EORelationship) selectedObject;
		} else if (selectedObject instanceof EORelationshipPath) {
			_relationship = ((EORelationshipPath) selectedObject).getChildRelationship();
		}
		if (_relationship != null) {
			_bindingContext = new DataBindingContext();
			
			
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_englishText), BeanProperties.value(EORelationship.ENGLISH).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_frenchText), BeanProperties.value(EORelationship.FRENCH).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_germanText), BeanProperties.value(EORelationship.GERMAN).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_dutchText), BeanProperties.value(EORelationship.DUTCH).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_italianText), BeanProperties.value(EORelationship.ITALIAN).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_japaneseText), BeanProperties.value(EORelationship.JAPANESE).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_chineseText), BeanProperties.value(EORelationship.CHINESE).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_spanishText), BeanProperties.value(EORelationship.SPANISH).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_portugueseText), BeanProperties.value(EORelationship.PORTUGUESE).observe(_relationship), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_brazilianText), BeanProperties.value(EORelationship.BRAZILIAN).observe(_relationship), null, null);
		}
	}

	protected void disposeBindings() {
		if (_bindingContext != null) {
			_bindingContext.dispose();
		}
	}

	public void dispose() {
		super.dispose();
		
		disposeBindings();
	}

}
