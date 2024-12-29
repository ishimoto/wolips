package org.objectstyle.wolips.eomodeler.actions;

import java.util.Comparator;

import org.objectstyle.wolips.eomodeler.core.model.EOAttribute;
import org.objectstyle.wolips.eomodeler.core.model.EOEntity;
import org.objectstyle.wolips.eomodeler.core.model.EOEntityIndex;
import org.objectstyle.wolips.eomodeler.core.model.TBEnterpriseFetchSpecification;
import org.objectstyle.wolips.eomodeler.core.model.EORelationship;

/**
 * When you paste, you always want attributes to come first, then relationships.
 * This comparator ensures that.
 * 
 * @author mschrag
 */
public class PasteOrderComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		int comparison;
		if (o1 instanceof EOEntity) {
			if (o2 instanceof EORelationship) {
				comparison = -1;
			} else if (o2 instanceof EOAttribute) {
				comparison = -1;
			} else if (o2 instanceof TBEnterpriseFetchSpecification) {
				comparison = -1;
			} else if (o2 instanceof EOEntityIndex) {
				comparison = -1;
			} else {
				comparison = 0;
			}
		} else if (o1 instanceof EORelationship) {
			if (o2 instanceof EOEntity) {
				comparison = 1;
			} else if (o2 instanceof EOAttribute) {
				comparison = 1;
			} else if (o2 instanceof TBEnterpriseFetchSpecification) {
				comparison = 1;
			} else {
				comparison = 0;
			}
		} else if (o1 instanceof EOAttribute) {
			if (o2 instanceof EOEntity) {
				comparison = 1;
			} else if (o2 instanceof EORelationship) {
				comparison = -1;
			} else if (o2 instanceof TBEnterpriseFetchSpecification) {
				comparison = 1;
			} else {
				comparison = 0;
			}
		} else if (o1 instanceof TBEnterpriseFetchSpecification) {
			if (o2 instanceof EOEntity) {
				comparison = 1;
			} else if (o2 instanceof EOAttribute) {
				comparison = -1;
			} else if (o2 instanceof EORelationship) {
				comparison = -1;
			} else {
				comparison = 0;
			}
		} else if (o1 instanceof EOEntityIndex) {
			if (o2 instanceof EOEntity) {
				comparison = 1;
			}
			else {
				comparison = 0;
			}
		} else {
			comparison = 0;
		}
		return comparison;
	}

}
