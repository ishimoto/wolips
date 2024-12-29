package org.objectstyle.wolips.eomodeler.core.model.qualifier;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TBEnterpriseOrQualifier extends EOAggregateQualifier {
	private List<TBEnterpriseQualifier> _qualifiers;

	public TBEnterpriseOrQualifier() {
		_qualifiers = new LinkedList<TBEnterpriseQualifier>();
	}

	public TBEnterpriseOrQualifier(Collection<TBEnterpriseQualifier> qualifiers) {
		_qualifiers = new LinkedList<TBEnterpriseQualifier>();
		for (TBEnterpriseQualifier qualifier : qualifiers) {
			if (qualifier instanceof TBEnterpriseOrQualifier) {
				_qualifiers.addAll(((TBEnterpriseOrQualifier) qualifier).getQualifiers());
			} else {
				_qualifiers.add(qualifier);
			}
		}
	}

	public TBEnterpriseOrQualifier(TBEnterpriseQualifier... qualifiers) {
		_qualifiers = new LinkedList<TBEnterpriseQualifier>();
		for (TBEnterpriseQualifier qualifier : qualifiers) {
			if (qualifier instanceof TBEnterpriseOrQualifier) {
				_qualifiers.addAll(((TBEnterpriseOrQualifier) qualifier).getQualifiers());
			} else {
				_qualifiers.add(qualifier);
			}
		}
	}

	public void addQualifier(TBEnterpriseQualifier qualifier) {
		_qualifiers.add(qualifier);
	}

	public List<TBEnterpriseQualifier> getQualifiers() {
		return _qualifiers;
	}

	public String toString(int depth) {
		StringBuffer sb = new StringBuffer();
		if (depth > 0) {
			sb.append("(");
		}
		for (int i = 0; i < _qualifiers.size(); i++) {
			TBEnterpriseQualifier qualifier = _qualifiers.get(i);
			if (i > 0) {
				sb.append(" or ");
			}
			if (qualifier != null) {
				sb.append(qualifier.toString(depth + 1));
			}
			else {
				sb.append("true");
			}
		}
		if (depth > 0) {
			sb.append(")");
		}
		return sb.toString();
	}
}
