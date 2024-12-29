/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.objectstyle.wolips.eomodeler.core.model.TBEnterpriseQualifierFactory;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.EOTruePredicate;

public class ExpressionQualifierTypeEditor extends AbstractQualifierTypeEditor {
	private Text _expressionText;

	public ExpressionQualifierTypeEditor(Composite parent, int style) {
		super(parent, style);
		FillLayout layout = new FillLayout(SWT.HORIZONTAL);
		layout.marginWidth = 0;
		setLayout(layout);
		_expressionText = new Text(this, SWT.BORDER);
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		String qualifierString;
		if (qualifier instanceof EOTruePredicate) {
			qualifierString = "";
		} else {
			qualifierString = TBEnterpriseQualifierFactory.toString(qualifier);
		}
		if (qualifierString == null) {
			qualifierString = "";
		}
		_expressionText.setText(qualifierString);
	}

	public TBEnterpriseQualifier getQualifier() {
		TBEnterpriseQualifier qualifier = TBEnterpriseQualifierFactory.fromString(_expressionText.getText());
		if (qualifier == null) {
			qualifier = new EOTruePredicate();
		}
		return qualifier;
	}
}