/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseOrQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;

public class OrQualifierType implements IQualifierType {
	public String getDisplayString() {
		return "Or";
	}

	public boolean isTypeFor(TBEnterpriseQualifier qualifier) {
		return qualifier instanceof TBEnterpriseOrQualifier;
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		// DO NOTHING
	}

	public AbstractQualifierTypeEditor createEditor(Composite parent) {
		return new OrQualifierTypeEditor(parent, SWT.NONE);
	}

	public String toString() {
		return getDisplayString();
	}
}