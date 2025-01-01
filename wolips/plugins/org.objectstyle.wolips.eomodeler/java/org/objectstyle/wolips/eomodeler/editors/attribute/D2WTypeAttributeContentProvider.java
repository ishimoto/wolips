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

public class D2WTypeAttributeContentProvider implements IStructuredContentProvider {
	
    public Object[] getElements(Object _inputElement) {		
		return new Object[] {"", 
				"amount",
				"arrayFromRule",
				"arrayString",
				"attachment",
				"audio",
				"barcode",
				"boolean",
				"booleanCheckbox",
				"booleanText",
				"cell",
				"checkbox",
				"clipboard",
				"color",
				"combo",
				"currency",
				"data",
				"date",
				"dateAsBoolean",
				"dateAsPicker",
				"dateAsPopup",
				"dictionary",
				"diff",
				"dossier",
				"double",
				"dts",
				"entityName",
				"enum",
				"enumsAsCheckboxes",
				"enumsAsRadioButtons",
				"gid",
				"gravatar",
				"html",
				"hr",
				"icom",
				"image",
				"imageCheckbox",
				"imageFromFile",
				"integer",
				"language",
				"largeString",
				"localDate",
				"localDateTime",
				"localTime",
				"localTimeExtended",
				"long",
				"lucene",
				"mail",
				"map",
				"markdown",
				"markdownFromFile",
				"osType",
				"password",
				"plist",
				"rating",
				"rolePermissionHelper",
				"roles",
				"static",
				"staticResource",
				"string",
				"stringFixedLength",
				"stringWithChoice",
				"stringWithLink",
				"tag",
				"tel",
				"time",
				"tiny",
				"trafficLight",
				"url",
				"vdate",
				"video",
				"yyyymm",
				"zip",
				"zonedDateTime"
		};
	}

	public void dispose() {
		// DO NOTHING
	}

	public void inputChanged(Viewer _viewer, Object _oldInput, Object _newInput) {
		// DO NOTHING
	}
}
