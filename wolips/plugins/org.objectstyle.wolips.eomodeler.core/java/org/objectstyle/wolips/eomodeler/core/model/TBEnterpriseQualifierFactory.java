/*
 * ====================================================================
 * 
 * The ObjectStyle Group Software License, Version 1.0
 * 
 * Copyright (c) 2006 The ObjectStyle Group and individual authors of the
 * software. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 * include the following acknowledgement: "This product includes software
 * developed by the ObjectStyle Group (http://objectstyle.org/)." Alternately,
 * this acknowledgement may appear in the software itself, if and wherever such
 * third-party acknowledgements normally appear.
 * 
 * 4. The names "ObjectStyle Group" and "Cayenne" must not be used to endorse or
 * promote products derived from this software without prior written permission.
 * For written permission, please contact andrus@objectstyle.org.
 * 
 * 5. Products derived from this software may not be called "ObjectStyle" nor
 * may "ObjectStyle" appear in their names without prior written permission of
 * the ObjectStyle Group.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * OBJECTSTYLE GROUP OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on
 * behalf of the ObjectStyle Group. For more information on the ObjectStyle
 * Group, please see <http://objectstyle.org/>.
 *  
 */
package org.objectstyle.wolips.eomodeler.core.model;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.objectstyle.wolips.eomodeler.core.model.qualifier.EOAggregateQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseAndQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.EOKeyComparisonQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseKeyValueQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.EONamedQualifierVariable;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseNotQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseOrQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifierBinding;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifierParser;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifierVariable;

public class TBEnterpriseQualifierFactory {
	private static class SelectorMap {
		private String _methodName;

		private String _operatorName;

		public SelectorMap(String methodName, String operatorName) {
			_methodName = methodName;
			_operatorName = operatorName;
		}

		public String getMethodName() {
			return _methodName;
		}

		public String getOperatorName() {
			return _operatorName;
		}
	}

	private static List<SelectorMap> _selectorMaps;

	static {
		_selectorMaps = new LinkedList<SelectorMap>();
		_selectorMaps.add(new SelectorMap("isEqualTo:", "="));
		_selectorMaps.add(new SelectorMap("isEqualTo", "="));
		_selectorMaps.add(new SelectorMap("isNotEqualTo:", "<>"));
		_selectorMaps.add(new SelectorMap("isNotEqualTo", "<>"));
		_selectorMaps.add(new SelectorMap("isLessThan:", "<"));
		_selectorMaps.add(new SelectorMap("isLessThan", "<"));
		_selectorMaps.add(new SelectorMap("isGreaterThan:", ">"));
		_selectorMaps.add(new SelectorMap("isGreaterThan", ">"));
		_selectorMaps.add(new SelectorMap("isLessThanOrEqualTo:", "<="));
		_selectorMaps.add(new SelectorMap("isLessThanOrEqualTo", "<="));
		_selectorMaps.add(new SelectorMap("isGreaterThanOrEqualTo:", ">="));
		_selectorMaps.add(new SelectorMap("isGreaterThanOrEqualTo", ">="));
		_selectorMaps.add(new SelectorMap("doesContain:", "contains"));
		_selectorMaps.add(new SelectorMap("doesContain", "contains"));
		_selectorMaps.add(new SelectorMap("isLike:", "like"));
		_selectorMaps.add(new SelectorMap("isLike", "like"));
		_selectorMaps.add(new SelectorMap("isCaseInsensitiveLike:", "caseInsensitiveLike"));
		_selectorMaps.add(new SelectorMap("isCaseInsensitiveLike", "caseInsensitiveLike"));

		// To correct the previous Cayenne syntax ... Just in case you had
		// either one in your fetch specs.
		_selectorMaps.add(new SelectorMap("likeIgnoreCase", "caseInsensitiveLike"));
		_selectorMaps.add(new SelectorMap("likeIgnoreCase:", "caseInsensitiveLike"));
	}

	public static TBEnterpriseQualifier fromString(String qualifierString) {
		try {
			TBEnterpriseQualifier qualifier = new TBEnterpriseQualifierParser().parseQualifier(qualifierString);
			return qualifier;
		} catch (ParseException e) {
			throw new RuntimeException("Failed to parse qualfier.", e);
		}
	}

	public static String toString(TBEnterpriseQualifier qualifier) {
		String qualifierString;
		if (qualifier == null) {
			qualifierString = null;
		} else {
			qualifierString = qualifier.toString();
		}
		return qualifierString;
	}

	protected static String operatorNameForMethodNamed(String methodName) {
		for (SelectorMap selectorMap : _selectorMaps) {
			if (selectorMap.getMethodName().equalsIgnoreCase(methodName)) {
				return selectorMap.getOperatorName();
			}
		}
		return methodName;
	}

	protected static String methodNameForOperatorNamed(String operatorName) {
		for (SelectorMap selectorMap : _selectorMaps) {
			if (selectorMap.getOperatorName().equalsIgnoreCase(operatorName)) {
				return selectorMap.getMethodName();
			}
		}
		return operatorName;
	}

	public static TBEnterpriseQualifier createQualifierFromQualifierMap(EOModelMap qualifierMap) {
		TBEnterpriseQualifier qualifier = null;
		if (qualifierMap != null) {
			String className = qualifierMap.getString("class", true);
			if ("TBEnterpriseAndQualifier".equals(className) || "org.treasureboat.enterprise.qualifiers.TBEnterpriseAndQualifier".equals(className)) {
				qualifier = new TBEnterpriseAndQualifier(TBEnterpriseQualifierFactory.createQualifiersFromQualifierMaps(qualifierMap.getList("qualifiers")));
			} else if ("TBEnterpriseOrQualifier".equals(className) || "org.treasureboat.enterprise.qualifiers.TBEnterpriseOrQualifier".equals(className)) {
				qualifier = new TBEnterpriseOrQualifier(TBEnterpriseQualifierFactory.createQualifiersFromQualifierMaps(qualifierMap.getList("qualifiers")));
			} else if ("TBEnterpriseNotQualifier".equals(className) || "org.treasureboat.enterprise.qualifiers.TBEnterpriseNotQualifier".equals(className)) {
				qualifier = new TBEnterpriseNotQualifier(TBEnterpriseQualifierFactory.createQualifierFromQualifierMap(new EOModelMap(qualifierMap.getMap("qualifier"))));
			} else if ("TBEnterpriseKeyValueQualifier".equals(className) || "org.treasureboat.enterprise.qualifiers.TBEnterpriseKeyValueQualifier".equals(className)) {
				String key = qualifierMap.getString("key", true);
				Object value = TBEnterpriseQualifierFactory.createValue(qualifierMap.get("value"));
				String selectorName = TBEnterpriseQualifierFactory.operatorNameForMethodNamed(qualifierMap.getString("selectorName", true));
				qualifier = TBEnterpriseQualifierFactory.createKeyValueExpression(key, selectorName, value);
			} else if ("EOKeyComparisonQualifier".equals(className) || "com.webobjects.eocontrol.EOKeyComparisonQualifier".equals(className)) {
				String leftKey = qualifierMap.getString("leftKey", true);
				String rightKey = qualifierMap.getString("rightKey", true);
				String selectorName = TBEnterpriseQualifierFactory.operatorNameForMethodNamed(qualifierMap.getString("selectorName", true));
				qualifier = TBEnterpriseQualifierFactory.createKeyComparisonExpression(leftKey, selectorName, rightKey);
			} else {
				throw new IllegalArgumentException("Unknown qualifier className '" + className + "'.");
			}
		}
		return qualifier;
	}

	private static TBEnterpriseQualifier createKeyValueExpression(String key, String selectorName, Object value) {
		TBEnterpriseQualifier qualifier = new TBEnterpriseKeyValueQualifier(key, selectorName, value);
		return qualifier;
	}

	private static TBEnterpriseQualifier createKeyComparisonExpression(String leftKey, String selectorName, String rightKey) {
		TBEnterpriseQualifier qualifier = new EOKeyComparisonQualifier(leftKey, selectorName, rightKey);
		return qualifier;
	}

	private static Object createValue(Object _rawValue) {
		Object value;
		if (_rawValue instanceof Map) {
			EOModelMap valueMap = new EOModelMap((Map) _rawValue);
			String valueClass = valueMap.getString("class", true);
			if ("EONull".equals(valueClass) || "com.webobjects.eocontrol.EONull".equals(valueClass)) {
				value = null;
			} else if ("TBEnterpriseQualifierVariable".equals(valueClass) || "org.treasureboat.enterprise.qualifiers.TBEnterpriseQualifierVariable".equals(valueClass)) {
				String variableKey = valueMap.getString("_key", true);
				// Fix up previously broken _key's
				if (variableKey.startsWith("$")) {
					variableKey = variableKey.substring(1);
				}
				value = new EONamedQualifierVariable(variableKey);
			} else if ("NSNumber".equals(valueClass)) {
				value = valueMap.get("value");
				if (value instanceof String) {
					String valueStr = (String) value;
					if (valueStr.indexOf('.') == -1) {
						value = Integer.parseInt(valueStr);
						if (!String.valueOf(value).equals(valueStr)) {
							value = Long.parseLong(valueStr);
						}
					} else {
						value = Float.parseFloat(valueStr);
					}
				}
			} else {
				throw new IllegalArgumentException("Unknown TBEnterpriseKeyValueQualifier value class " + valueClass);
			}
		} else {
			value = _rawValue;
		}
		return value;
	}

	private static Collection<TBEnterpriseQualifier> createQualifiersFromQualifierMaps(Collection<Map<Object, Object>> _qualifiers) {
		List<TBEnterpriseQualifier> qualifiers = new LinkedList<TBEnterpriseQualifier>();
		if (_qualifiers != null) {
			for (Map<Object, Object> qualifierMap : _qualifiers) {
				TBEnterpriseQualifier exp = TBEnterpriseQualifierFactory.createQualifierFromQualifierMap(new EOModelMap(qualifierMap));
				qualifiers.add(exp);
			}
		}
		return qualifiers;
	}

	private static Object createQualifierValue(Object value) {
		Object qualifierValue;
		if (value == null) {
			EOModelMap map = new EOModelMap();
			map.setString("class", "EONull", false);
			qualifierValue = map;
		} else if (value instanceof EONamedQualifierVariable) {
			EOModelMap map = new EOModelMap();
			String name = ((EONamedQualifierVariable) value).getName();
			map.setString("_key", name, true);
			map.setString("class", "TBEnterpriseQualifierVariable", false);
			qualifierValue = map;
		} else if (value instanceof TBEnterpriseQualifierVariable) {
			EOModelMap map = new EOModelMap();
			String name = ((TBEnterpriseQualifierVariable) value).getName();
			map.setString("_key", name, true);
			map.setString("class", "TBEnterpriseQualifierVariable", false);
			qualifierValue = map;
		} else if (value instanceof Number) {
			EOModelMap map = new EOModelMap();
			map.setString("class", "NSNumber", false);
			map.put("value", value);
			qualifierValue = map;
		} else if (value instanceof Boolean) {
			EOModelMap map = new EOModelMap();
			map.setString("class", "NSNumber", false);
			map.put("value", value);
			qualifierValue = map;
		} else if (value instanceof String) {
			qualifierValue = value;
		} else {
			throw new IllegalArgumentException("Unknown qualifier value type: " + value + " (type = " + value.getClass().getName() + ")");
		}
		return qualifierValue;
	}

	private static EOModelMap createQualifierMapFromKeyComparisonQualifier(EOKeyComparisonQualifier qualifier) {
		String leftKey = qualifier.getLeftKey();
		String rightKey = qualifier.getRightKey();

		EOModelMap map = new EOModelMap();
		map.setString("class", "EOKeyComparisonQualifier", false);
		map.setString("leftKey", leftKey, false);
		map.setString("rightKey", rightKey, false);
		TBEnterpriseQualifier.Comparison comparison = qualifier.getComparison();
		String selectorName = (comparison == null) ? null : TBEnterpriseQualifierFactory.methodNameForOperatorNamed(comparison.getName());
		map.setString("selectorName", selectorName, false);
		return map;
	}

	private static EOModelMap createQualifierMapFromKeyValueQualifier(TBEnterpriseKeyValueQualifier qualifier) {
		String key = qualifier.getKey();
		Object value = qualifier.getValue();
		EOModelMap map = new EOModelMap();
		map.setString("class", "TBEnterpriseKeyValueQualifier", false);
		Object processedValue = createQualifierValue(value);
		map.setString("key", key, false);
		TBEnterpriseQualifier.Comparison comparison = qualifier.getComparison();
		String selectorName = (comparison == null) ? null : TBEnterpriseQualifierFactory.methodNameForOperatorNamed(comparison.getName());
		map.setString("selectorName", selectorName, false);
		map.put("value", processedValue);
		return map;
	}

	private static List<EOModelMap> createQualifierMapsFromAggregateQualifier(EOAggregateQualifier aggregateQualifier) {
		List<EOModelMap> qualifierMaps = new LinkedList<EOModelMap>();
		for (TBEnterpriseQualifier qualifier : aggregateQualifier.getQualifiers()) {
			qualifierMaps.add(TBEnterpriseQualifierFactory.createQualifierMapFromQualifier(qualifier));
		}
		return qualifierMaps;
	}

	public static EOModelMap createQualifierMapFromQualifier(TBEnterpriseQualifier qualifier) {
		EOModelMap map;
		if (qualifier instanceof TBEnterpriseKeyValueQualifier) {
			map = TBEnterpriseQualifierFactory.createQualifierMapFromKeyValueQualifier((TBEnterpriseKeyValueQualifier) qualifier);
		} else if (qualifier instanceof EOKeyComparisonQualifier) {
			map = TBEnterpriseQualifierFactory.createQualifierMapFromKeyComparisonQualifier((EOKeyComparisonQualifier) qualifier);
		} else if (qualifier instanceof TBEnterpriseAndQualifier) {
			map = new EOModelMap();
			map.setString("class", "TBEnterpriseAndQualifier", false);
			map.setList("qualifiers", createQualifierMapsFromAggregateQualifier((TBEnterpriseAndQualifier) qualifier), true);
		} else if (qualifier instanceof TBEnterpriseOrQualifier) {
			map = new EOModelMap();
			map.setString("class", "TBEnterpriseOrQualifier", false);
			map.setList("qualifiers", createQualifierMapsFromAggregateQualifier((TBEnterpriseOrQualifier) qualifier), true);
		} else if (qualifier instanceof TBEnterpriseNotQualifier) {
			map = new EOModelMap();
			map.setString("class", "TBEnterpriseNotQualifier", false);
			map.setMap("qualifier", createQualifierMapFromQualifier(((TBEnterpriseNotQualifier) qualifier).getQualifier()), true);
		} else {
			throw new IllegalArgumentException("Unknown qualifier " + qualifier + ".");
		}
		return map;
	}

	public static Set<String> getQualifierKeysFromQualifierString(String qualifierString) {
		TBEnterpriseQualifier qualifier = TBEnterpriseQualifierFactory.fromString(qualifierString);
		return TBEnterpriseQualifierFactory.getQualifierKeysFromQualifier(qualifier);
	}

	public static Set<String> getQualifierKeysFromQualifier(TBEnterpriseQualifier expression) {
		Set<String> keys = new HashSet<String>();
		try {
			TBEnterpriseQualifierFactory.fillInQualifierKeysFromQualifier(expression, keys);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return keys;
	}

	public static void fillInQualifierKeysFromQualifier(TBEnterpriseQualifier qualifier, Set<String> keys) {
		if (qualifier instanceof TBEnterpriseKeyValueQualifier) {
			String key = ((TBEnterpriseKeyValueQualifier) qualifier).getKey();
			keys.add(key);
		} else if (qualifier instanceof EOKeyComparisonQualifier) {
			String leftKey = ((EOKeyComparisonQualifier) qualifier).getLeftKey();
			keys.add(leftKey);
			String rightKey = ((EOKeyComparisonQualifier) qualifier).getRightKey();
			keys.add(rightKey);
		} else if (qualifier instanceof EOAggregateQualifier) {
			for (TBEnterpriseQualifier childQualifier : ((EOAggregateQualifier) qualifier).getQualifiers()) {
				TBEnterpriseQualifierFactory.fillInQualifierKeysFromQualifier(childQualifier, keys);
			}
		} else if (qualifier instanceof TBEnterpriseNotQualifier) {
			TBEnterpriseQualifierFactory.fillInQualifierKeysFromQualifier(((TBEnterpriseNotQualifier) qualifier).getQualifier(), keys);
		} else {
			throw new IllegalArgumentException("Unknown expression " + qualifier + ".");
		}
	}

	public static List<TBEnterpriseQualifierBinding> getQualifierBindingsFromQualifierString(EOEntity entity, String qualifierString) {
		TBEnterpriseQualifier qualifier = TBEnterpriseQualifierFactory.fromString(qualifierString);
		return TBEnterpriseQualifierFactory.getQualifierBindingsFromQualifier(entity, qualifier);
	}

	public static List<TBEnterpriseQualifierBinding> getQualifierBindingsFromQualifier(EOEntity entity, TBEnterpriseQualifier qualifier) {
		List<TBEnterpriseQualifierBinding> bindings = new LinkedList<TBEnterpriseQualifierBinding>();
		try {
			if (qualifier != null) {
				TBEnterpriseQualifierFactory.fillInQualifierBindingsFromQualifier(entity, qualifier, bindings);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return bindings;
	}

	public static void fillInQualifierBindingsFromQualifier(EOEntity entity, TBEnterpriseQualifier qualifier, List<TBEnterpriseQualifierBinding> bindings) {
		if (qualifier instanceof TBEnterpriseKeyValueQualifier) {
			String key = ((TBEnterpriseKeyValueQualifier) qualifier).getKey();
			Object value = ((TBEnterpriseKeyValueQualifier) qualifier).getValue();
			if (value instanceof EONamedQualifierVariable) {
				String bindingName = ((EONamedQualifierVariable) value).getName();
				TBEnterpriseQualifierBinding binding = new TBEnterpriseQualifierBinding(entity, bindingName, key);
				bindings.add(binding);
			}
		} else if (qualifier instanceof EOKeyComparisonQualifier) {
			// DO NOTHING
		} else if (qualifier instanceof EOAggregateQualifier) {
			for (TBEnterpriseQualifier aggregatedQualifier : ((EOAggregateQualifier) qualifier).getQualifiers()) {
				TBEnterpriseQualifierFactory.fillInQualifierBindingsFromQualifier(entity, aggregatedQualifier, bindings);
			}
		} else if (qualifier instanceof TBEnterpriseNotQualifier) {
			TBEnterpriseQualifierFactory.fillInQualifierBindingsFromQualifier(entity, ((TBEnterpriseNotQualifier) qualifier).getQualifier(), bindings);
		} else {
			throw new IllegalArgumentException("Unknown qualifier '" + qualifier + "'.");
		}
	}

}