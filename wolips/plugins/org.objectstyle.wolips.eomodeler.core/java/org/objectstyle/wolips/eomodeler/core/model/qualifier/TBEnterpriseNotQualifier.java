package org.objectstyle.wolips.eomodeler.core.model.qualifier;

public class TBEnterpriseNotQualifier extends TBEnterpriseQualifier {
	private TBEnterpriseQualifier _qualifier;

	public TBEnterpriseNotQualifier() {
		// DO NOTHING
	}

	public TBEnterpriseNotQualifier(TBEnterpriseQualifier qualifier) {
		_qualifier = qualifier;
	}

	public TBEnterpriseQualifier getQualifier() {
		return _qualifier;
	}
	@Override
	public String toString(int depth) {
		StringBuffer sb = new StringBuffer();
		sb.append("not ");
		boolean containsAggregateQualifier = (_qualifier instanceof EOAggregateQualifier); 
		if (!containsAggregateQualifier) {
			sb.append("(");
		}
		sb.append(_qualifier.toString(depth + 1));
		if (!containsAggregateQualifier) {
			sb.append(")");
		}
		return sb.toString();
	}
}