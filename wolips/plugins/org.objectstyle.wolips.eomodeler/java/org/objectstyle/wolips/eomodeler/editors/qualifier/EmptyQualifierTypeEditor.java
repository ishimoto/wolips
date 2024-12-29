/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;

public class EmptyQualifierTypeEditor extends AbstractQualifierTypeEditor {
	private TBEnterpriseQualifier _qualifier;

	public EmptyQualifierTypeEditor(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		Label label = new Label(this, SWT.NONE);
		label.setText("Empty");
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		_qualifier = qualifier;
	}

	public TBEnterpriseQualifier getQualifier() {
		return _qualifier;
	}
}