/*
 * ====================================================================
 * 
 * The ObjectStyle Group Software License, Version 1.0
 * 
 * Copyright (c) 2002 - 2006 The ObjectStyle Group and individual authors of the
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
 * include the following acknowlegement: "This product includes software
 * developed by the ObjectStyle Group (http://objectstyle.org/)." Alternately,
 * this acknowlegement may appear in the software itself, if and wherever such
 * third-party acknowlegements normally appear.
 * 
 * 4. The names "ObjectStyle Group" and "Cayenne" must not be used to endorse
 * or promote products derived from this software without prior written
 * permission. For written permission, please contact andrus@objectstyle.org.
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
 * This software consists of voluntary contributions made by many individuals
 * on behalf of the ObjectStyle Group. For more information on the ObjectStyle
 * Group, please see <http://objectstyle.org/>.
 *  
 */
package org.objectstyle.wolips.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.objectstyle.wolips.preferences.Preferences;
import org.objectstyle.wolips.preferences.PreferencesMessages;

/**
 * @author uli
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class BuildPreferencesPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	/**
	 * Constructor
	 */
	public BuildPreferencesPage() {
		super(GRID);
		setPreferenceStore(Preferences.getPreferenceStore());
		setDescription(PreferencesMessages
				.getString("Preferences.Build.PageDescription"));
		Preferences.setDefaults();
	}

	/**
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(
				Preferences.PREF_RUN_WOBUILDER_ON_BUILD, PreferencesMessages
						.getString("Preferences.RunWOBuilderOnBuild.Label"),
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(
				Preferences.PREF_CAPTURE_ANT_OUTPUT, PreferencesMessages
						.getString("Preferences.CaptureAntOutput.Label"),
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(Preferences.PREF_WRITE_XCODE_ON_BUILD,
				PreferencesMessages
						.getString("Preferences.WriteXcodeOnBuild.Label"),
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(
				Preferences.PREF_WRITE_XCODE21_ON_BUILD, PreferencesMessages
						.getString("Preferences.WriteXcode21OnBuild.Label"),
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(Preferences.PREF_VALIDATE_WOD_ON_BUILD,
				PreferencesMessages
						.getString("Preferences.ValidateWODOnBuild.Label"),
				getFieldEditorParent()));
		addField(new FileFieldEditor(Preferences.PREF_EOGENERATOR_PATH,
				PreferencesMessages
						.getString("Preferences.EOGeneratorPath.Label"),
				getFieldEditorParent()));
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		return;
	}

	/**
	 * Method performOK.
	 * 
	 * @return boolean
	 */
	public boolean performOk() {
		return super.performOk();
	}
}