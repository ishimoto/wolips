/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;

public class ExpressionQualifierType implements IQualifierType {
	public boolean isTypeFor(TBEnterpriseQualifier qualifier) {
		return true;
	}

	public String getDisplayString() {
		return "Expression";
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		// DO NOTHING
	}

	public AbstractQualifierTypeEditor createEditor(Composite parent) {
		return new ExpressionQualifierTypeEditor(parent, SWT.NONE);
	}

	public String toString() {
		return getDisplayString();
	}
}