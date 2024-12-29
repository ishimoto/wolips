/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseAndQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;

public class AndQualifierType implements IQualifierType {
	public String getDisplayString() {
		return "And";
	}

	public boolean isTypeFor(TBEnterpriseQualifier qualifier) {
		return qualifier instanceof TBEnterpriseAndQualifier;
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		// DO NOTHING
	}

	public AbstractQualifierTypeEditor createEditor(Composite parent) {
		return new AndQualifierTypeEditor(parent, SWT.NONE);
	}

	public String toString() {
		return getDisplayString();
	}
}