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

public class EOCopyTypeLabelProvider implements ILabelProvider {
	public Image getImage(Object _element) {
		return null;
	}

	public String getText(Object _element) {
		String copyType = (String) _element;
		if ("Skip".equals(copyType)) {
			return "Skip";
		} else if ("Nullify".equals(copyType)) {
			return "Nullify";
		} else if ("Reference".equals(copyType)) {
			return "Reference";
		} else if ("Shallow".equals(copyType)) {
			return "Shallow";
		} else if ("Deep".equals(copyType)) {
			return "Deep";
		} else if ("CurrentTimestamp".equals(copyType)) {
			return "CurrentTimestamp";
		} else if ("UUID".equals(copyType)) {
			return "UUID";
		} else if ("Model".equals(copyType)) {
			return "Model";
		} else if ("Default".equals(copyType)) {
			return "Default";
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
