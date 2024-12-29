/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import org.eclipse.swt.widgets.Composite;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;

public interface IQualifierType {
	public boolean isTypeFor(TBEnterpriseQualifier qualifier);

	public void setQualifier(TBEnterpriseQualifier qualifier);
	
	public String getDisplayString();

	public AbstractQualifierTypeEditor createEditor(Composite parent);
}