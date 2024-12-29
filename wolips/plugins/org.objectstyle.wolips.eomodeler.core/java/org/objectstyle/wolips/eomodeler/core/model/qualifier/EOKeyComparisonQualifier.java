package org.objectstyle.wolips.eomodeler.core.model.qualifier;

public class EOKeyComparisonQualifier extends TBEnterpriseQualifier {
	private String _leftKey;

	private TBEnterpriseQualifier.Comparison _comparison;

	private String _rightKey;

	public EOKeyComparisonQualifier() {
		// DO NOTHING
	}

	public EOKeyComparisonQualifier(String leftKey, String comparison, String rightKey) {
		this(leftKey, new TBEnterpriseQualifier.Comparison(comparison), rightKey);
	}

	public EOKeyComparisonQualifier(String leftKey, TBEnterpriseQualifier.Comparison comparison, String rightKey) {
		_leftKey = leftKey;
		_comparison = comparison;
		_rightKey = rightKey;
	}

	public String getLeftKey() {
		return _leftKey;
	}

	public TBEnterpriseQualifier.Comparison getComparison() {
		return _comparison;
	}

	public String getRightKey() {
		return _rightKey;
	}

	public String toString(int depth) {
		return _leftKey + " " + _comparison + " " + _rightKey;
	}
}
