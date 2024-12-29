package org.objectstyle.wolips.eomodeler.core.model.qualifier;

import java.util.List;

public abstract class EOAggregateQualifier extends TBEnterpriseQualifier {
	public abstract void addQualifier(TBEnterpriseQualifier qualifier);

	public abstract List<TBEnterpriseQualifier> getQualifiers();
}
