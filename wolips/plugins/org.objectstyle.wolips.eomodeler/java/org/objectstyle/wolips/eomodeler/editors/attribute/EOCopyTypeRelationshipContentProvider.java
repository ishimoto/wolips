/*
 * ====================================================================
 * 
 * TreasureBoat, Version 2.0
 * 
 * Copyright (c) 2018 The TreasureBoat Group and individual authors of the
 * software. All rights reserved.
 */
package org.objectstyle.wolips.eomodeler.editors.attribute;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class EOCopyTypeRelationshipContentProvider implements IStructuredContentProvider {
	
    public Object[] getElements(Object _inputElement) {		
		return new Object[] {"", "Skip", "Nullify", "Reference", "Shallow", "Deep"};
	}

	public void dispose() {
		// DO NOTHING
	}

	public void inputChanged(Viewer _viewer, Object _oldInput, Object _newInput) {
		// DO NOTHING
	}
}
