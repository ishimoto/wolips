/*
 * ====================================================================
 * 
 * TreasureBoat, Version 2.0
 * 
 * Copyright (c) 2018 The TreasureBoat Group and individual authors of the
 * software. All rights reserved.
 */
package org.objectstyle.wolips.eomodeler.editors.attribute;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class D2WTypeLabelProvider implements ILabelProvider {
	public Image getImage(Object _element) {
		return null;
	}

	public String getText(Object _element) {
		String d2wType = (String) _element;
		if (d2wType != null && d2wType.length() > 0) {
			return d2wType;
		}
		return null;
	}

	public void addListener(ILabelProviderListener _listener) {
		// DO NOTHING
	}

	public void dispose() {
		// DO NOTHING
	}

	public boolean isLabelProperty(Object _element, String _property) {
		return true;
	}

	public void removeListener(ILabelProviderListener _listener) {
		// DO NOTHING
	}

}
