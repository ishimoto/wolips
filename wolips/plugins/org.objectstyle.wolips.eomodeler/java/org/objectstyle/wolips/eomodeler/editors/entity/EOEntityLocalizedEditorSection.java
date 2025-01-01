package org.objectstyle.wolips.eomodeler.editors.entity;

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
import org.objectstyle.wolips.eomodeler.core.model.EOEntity;
import org.objectstyle.wolips.eomodeler.utils.FormUtils;
import org.objectstyle.wolips.eomodeler.utils.UglyFocusHackWorkaroundListener;

/**
 * this is the part on the left bottom side. after you click the 'Localized' Tab,
 * this code get executed.
 * 
 * @author ishimoto
 */
public class EOEntityLocalizedEditorSection extends AbstractPropertySection {
	
	private EOEntity _entity;

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
	private Text _iconText;

	private DataBindingContext _bindingContext;

	public EOEntityLocalizedEditorSection() {
		// DO NOTHING
	}

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite form = getWidgetFactory().createFlatFormComposite(parent);
		FormLayout formLayout = new FormLayout();
		form.setLayout(formLayout);

		Composite topForm = FormUtils.createForm(getWidgetFactory(), form);
				
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.ENGLISH), SWT.NONE);
		_englishText = new Text(topForm, SWT.BORDER);
		_englishText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_englishText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.FRENCH), SWT.NONE);
		_frenchText = new Text(topForm, SWT.BORDER);
		_frenchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_frenchText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.GERMAN), SWT.NONE);
		_germanText = new Text(topForm, SWT.BORDER);
		_germanText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_germanText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.DUTCH), SWT.NONE);
		_dutchText = new Text(topForm, SWT.BORDER);
		_dutchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_dutchText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.ITALIAN), SWT.NONE);
		_italianText = new Text(topForm, SWT.BORDER);
		_italianText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_italianText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.JAPANESE), SWT.NONE);
		_japaneseText = new Text(topForm, SWT.BORDER);
		_japaneseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_japaneseText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.CHINESE), SWT.NONE);
		_chineseText = new Text(topForm, SWT.BORDER);
		_chineseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_chineseText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.SPANISH), SWT.NONE);
		_spanishText = new Text(topForm, SWT.BORDER);
		_spanishText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_spanishText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.PORTUGUESE), SWT.NONE);
		_portugueseText = new Text(topForm, SWT.BORDER);
		_portugueseText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_portugueseText);
		
		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.BRAZILIAN), SWT.NONE);
		_brazilianText = new Text(topForm, SWT.BORDER);
		_brazilianText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_brazilianText);

		getWidgetFactory().createCLabel(topForm, Messages.getString("EOEntity." + EOEntity.ICON), SWT.NONE);
		_iconText = new Text(topForm, SWT.BORDER);
		_iconText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UglyFocusHackWorkaroundListener.addListener(_iconText);
		
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
			
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_englishText), BeanProperties.value(EOEntity.ENGLISH).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_frenchText), BeanProperties.value(EOEntity.FRENCH).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_germanText), BeanProperties.value(EOEntity.GERMAN).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_dutchText), BeanProperties.value(EOEntity.DUTCH).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_italianText), BeanProperties.value(EOEntity.ITALIAN).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_japaneseText), BeanProperties.value(EOEntity.JAPANESE).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_chineseText), BeanProperties.value(EOEntity.CHINESE).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_spanishText), BeanProperties.value(EOEntity.SPANISH).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_portugueseText), BeanProperties.value(EOEntity.PORTUGUESE).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_brazilianText), BeanProperties.value(EOEntity.BRAZILIAN).observe(_entity), null, null);
			_bindingContext.bindValue( WidgetProperties.text(SWT.Modify).observe(_iconText), BeanProperties.value(EOEntity.ICON).observe(_entity), null, null);
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
