/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.EOKeyComparisonQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseKeyValueQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;

public class KeyValueQualifierType implements IQualifierType {
	private String _key;

	public boolean isTypeFor(TBEnterpriseQualifier qualifier) {
		return qualifier instanceof TBEnterpriseKeyValueQualifier;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getKey() {
		return _key;
	}

	public String getDisplayString() {
		String displayString = _key;
		if (displayString == null) {
			displayString = "";
		}
		return displayString;
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		if (qualifier instanceof TBEnterpriseKeyValueQualifier) {
			setKey(((TBEnterpriseKeyValueQualifier) qualifier).getKey());
		} else if (qualifier instanceof EOKeyComparisonQualifier) {
			setKey(((EOKeyComparisonQualifier) qualifier).getLeftKey());
		} else {
			setKey(null);
		}
	}

	public AbstractQualifierTypeEditor createEditor(Composite parent) {
		return new KeyValueQualifierTypeEditor(parent, SWT.NONE);
	}

	public String toString() {
		return getDisplayString();
	}
}