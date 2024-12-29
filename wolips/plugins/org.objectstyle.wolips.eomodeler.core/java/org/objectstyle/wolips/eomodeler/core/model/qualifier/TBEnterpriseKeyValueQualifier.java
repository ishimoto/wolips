package org.objectstyle.wolips.eomodeler.core.model.qualifier;

public class TBEnterpriseKeyValueQualifier extends TBEnterpriseQualifier {
	private String _key;

	private TBEnterpriseQualifier.Comparison _comparison;

	private Object _value;

	public TBEnterpriseKeyValueQualifier() {
		// DO NOTHING
	}

	public TBEnterpriseKeyValueQualifier(String key, String comparison, Object value) {
		this(key, new TBEnterpriseQualifier.Comparison(comparison), value);
	}

	public TBEnterpriseKeyValueQualifier(String key, TBEnterpriseQualifier.Comparison comparison, Object value) {
		_key = key;
		_comparison = comparison;
		_value = value;
	}

	public String getKey() {
		return _key;
	}

	public TBEnterpriseQualifier.Comparison getComparison() {
		return _comparison;
	}

	public Object getValue() {
		return _value;
	}

	public String toString(int depth) {
		StringBuffer sb = new StringBuffer();
		sb.append(_key);
		sb.append(" ");
		sb.append(_comparison);
		sb.append(" ");
		if (_value instanceof String) {
			sb.append("'");
			String escapedValue = (String) _value;
			escapedValue = escapedValue.replaceAll("\\\\", "\\\\\\\\");
			escapedValue = escapedValue.replaceAll("'", "\\\\'");
			sb.append(escapedValue);
			sb.append("'");
		} else {
			sb.append(_value);
		}
		return sb.toString();
	}
}
