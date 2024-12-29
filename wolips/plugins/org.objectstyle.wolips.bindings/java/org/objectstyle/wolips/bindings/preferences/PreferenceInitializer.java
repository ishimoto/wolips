/*
 * ====================================================================
 * 
 * The ObjectStyle Group Software License, Version 1.0
 * 
 * Copyright (c) 2005 The ObjectStyle Group and individual authors of the
 * software. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: 1.
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. 2. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution. 3. The end-user documentation
 * included with the redistribution, if any, must include the following
 * acknowlegement: "This product includes software developed by the ObjectStyle
 * Group (http://objectstyle.org/)." Alternately, this acknowlegement may
 * appear in the software itself, if and wherever such third-party
 * acknowlegements normally appear. 4. The names "ObjectStyle Group" and
 * "Cayenne" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please
 * contact andrus@objectstyle.org. 5. Products derived from this software may
 * not be called "ObjectStyle" nor may "ObjectStyle" appear in their names
 * without prior written permission of the ObjectStyle Group.
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
 * Group, please see <http://objectstyle.org/> .
 *  
 */
package org.objectstyle.wolips.bindings.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.objectstyle.wolips.bindings.Activator;
import org.objectstyle.wolips.bindings.wod.BindingValidationRule;
import org.objectstyle.wolips.bindings.wod.TagShortcut;

public class PreferenceInitializer extends AbstractPreferenceInitializer {
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();

    List<TagShortcut> tagShortcuts = new ArrayList<TagShortcut>();
    
    tagShortcuts.add(new TagShortcut("localized", "TBWLocalizedString"));
    tagShortcuts.add(new TagShortcut("not", "TBConditional"));
    tagShortcuts.add(new TagShortcut("else", "TBElse"));
    tagShortcuts.add(new TagShortcut("if", "TBConditional"));
    tagShortcuts.add(new TagShortcut("conditional", "TBConditional"));
    tagShortcuts.add(new TagShortcut("condition", "TBConditional"));
    tagShortcuts.add(new TagShortcut("foreach", "TBRepetition"));
    tagShortcuts.add(new TagShortcut("repeat", "TBRepetition"));
    tagShortcuts.add(new TagShortcut("repetition", "TBRepetition"));
    tagShortcuts.add(new TagShortcut("loop", "TBRepetition"));
    tagShortcuts.add(new TagShortcut("content", "TBComponentContent"));
    tagShortcuts.add(new TagShortcut("componentContent", "TBComponentContent"));
    tagShortcuts.add(new TagShortcut("str", "TBString"));
    tagShortcuts.add(new TagShortcut("string", "TBString"));
    tagShortcuts.add(new TagShortcut("switchComponent", "TBSwitchComponent"));
    tagShortcuts.add(new TagShortcut("switch", "TBSwitchComponent"));
    tagShortcuts.add(new TagShortcut("XMLNode", "TBXMLNode"));
    tagShortcuts.add(new TagShortcut("param", "TBParam"));
    tagShortcuts.add(new TagShortcut("noContentElement", "TBNoContentElement"));
    tagShortcuts.add(new TagShortcut("noContent", "TBNoContentElement"));
    tagShortcuts.add(new TagShortcut("body", "TBBody"));
    tagShortcuts.add(new TagShortcut("embeddedObject", "TBEmbeddedObject"));
    tagShortcuts.add(new TagShortcut("embedded", "TBEmbeddedObject"));
    tagShortcuts.add(new TagShortcut("frame", "TBFrame"));
    tagShortcuts.add(new TagShortcut("image", "TBImage"));
    tagShortcuts.add(new TagShortcut("img", "TBImage"));
    tagShortcuts.add(new TagShortcut("form", "TBForm"));
    tagShortcuts.add(new TagShortcut("javaScript", "TBJavaScript"));
    tagShortcuts.add(new TagShortcut("resourceURL", "TBResourceURL"));
    tagShortcuts.add(new TagShortcut("genericElement", "TBGenericElement"));
    tagShortcuts.add(new TagShortcut("element", "TBGenericElement"));
    tagShortcuts.add(new TagShortcut("genericContainer", "TBGenericContainer"));
    tagShortcuts.add(new TagShortcut("container", "TBGenericContainer"));
    tagShortcuts.add(new TagShortcut("activeImage", "TBActiveImage"));
    tagShortcuts.add(new TagShortcut("checkBox", "TBCheckBox"));
    tagShortcuts.add(new TagShortcut("checkbox", "TBCheckBox"));
    tagShortcuts.add(new TagShortcut("fileUpload", "TBFileUpload"));
    tagShortcuts.add(new TagShortcut("upload", "TBFileUpload"));
    tagShortcuts.add(new TagShortcut("hiddenField", "TBHiddenField"));
    tagShortcuts.add(new TagShortcut("hidden", "TBHiddenField"));
    tagShortcuts.add(new TagShortcut("imageButton", "TBImageButton"));
    tagShortcuts.add(new TagShortcut("browser", "TBBrowser"));
    tagShortcuts.add(new TagShortcut("popUpButton", "TBPopUp"));
    tagShortcuts.add(new TagShortcut("select", "TBPopUp")); 
    tagShortcuts.add(new TagShortcut("passwordField", "TBPasswordField"));
    tagShortcuts.add(new TagShortcut("password", "TBPasswordField"));
    tagShortcuts.add(new TagShortcut("radioButton", "TBRadioButton"));
    tagShortcuts.add(new TagShortcut("radio", "TBRadioButton"));
    tagShortcuts.add(new TagShortcut("resetButton", "TBResetButton"));
    tagShortcuts.add(new TagShortcut("reset", "TBResetButton"));
    tagShortcuts.add(new TagShortcut("submitButton", "TBSubmitButton"));
    tagShortcuts.add(new TagShortcut("submit", "TBSubmitButton"));
    tagShortcuts.add(new TagShortcut("text", "TBText"));
    tagShortcuts.add(new TagShortcut("textField", "TBTextField"));
    tagShortcuts.add(new TagShortcut("textfield", "TBTextField"));
    tagShortcuts.add(new TagShortcut("search", "TBSearchField"));
    tagShortcuts.add(new TagShortcut("searchfield", "TBSearchField"));
    tagShortcuts.add(new TagShortcut("hyperlink", "TBHyperlink"));
    tagShortcuts.add(new TagShortcut("link", "TBHyperlink"));
    tagShortcuts.add(new TagShortcut("actionURL", "TBActionURL"));
    tagShortcuts.add(new TagShortcut("map", "TBMap"));
    tagShortcuts.add(new TagShortcut("ajaxUC", "JQAjaxUpdateContainer"));
    tagShortcuts.add(new TagShortcut("ajaxHyperlink", "JQAjaxHyperlink"));

    prefs.setDefault(PreferenceConstants.TAG_SHORTCUTS_KEY, TagShortcut.toPreferenceString(tagShortcuts));

    List<BindingValidationRule> validationRules = new ArrayList<BindingValidationRule>();
    validationRules.add(new BindingValidationRule(".*", "^session\\.localizer\\..*"));
    validationRules.add(new BindingValidationRule(".*", "^localizer\\..*"));
    
    validationRules.add(new BindingValidationRule(".*", "^sangria\\..*"));
    validationRules.add(new BindingValidationRule(".*", "^d2wContext\\..*"));
    validationRules.add(new BindingValidationRule(".*", "^localContext\\..*"));

    validationRules.add(new BindingValidationRule(".*", "^nonCachingContext\\..*"));
    
    validationRules.add(new BindingValidationRule(".*", "^cmsContext\\..*"));
    prefs.setDefault(PreferenceConstants.BINDING_VALIDATION_RULES_KEY, BindingValidationRule.toPreferenceString(validationRules));

    prefs.setDefault(PreferenceConstants.USE_INLINE_BINDINGS_KEY, false);

    prefs.setDefault(PreferenceConstants.VALIDATE_TEMPLATES_KEY, true);
    prefs.setDefault(PreferenceConstants.VALIDATE_TEMPLATES_ON_BUILD_KEY, true);
    prefs.setDefault(PreferenceConstants.VALIDATE_BINDING_VALUES, true);
//    prefs.setDefault(PreferenceConstants.VALIDATE_WOO_ENCODINGS_KEY, true);

    prefs.setDefault(PreferenceConstants.INVALID_OGNL_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.MISSING_COLLECTION_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.MISSING_COMPONENT_SEVERITY_KEY, PreferenceConstants.ERROR);
    prefs.setDefault(PreferenceConstants.MISSING_NSKVC_SEVERITY_KEY, PreferenceConstants.ERROR);
    prefs.setDefault(PreferenceConstants.AMBIGUOUS_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.HTML_ERRORS_SEVERITY_KEY, PreferenceConstants.ERROR);
    prefs.setDefault(PreferenceConstants.WOD_ERRORS_IN_HTML_SEVERITY_KEY, PreferenceConstants.ERROR);
    prefs.setDefault(PreferenceConstants.UNUSED_WOD_ELEMENT_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.WOD_MISSING_COMPONENT_SEVERITY_KEY, PreferenceConstants.ERROR);
    prefs.setDefault(PreferenceConstants.WOD_API_PROBLEMS_SEVERITY_KEY, PreferenceConstants.ERROR);
    prefs.setDefault(PreferenceConstants.DEPRECATED_BINDING_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.AT_OPERATOR_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.HELPER_FUNCTION_SEVERITY_KEY, PreferenceConstants.WARNING);
    prefs.setDefault(PreferenceConstants.WELL_FORMED_TEMPLATE_KEY, PreferenceConstants.DEFAULT);

    prefs.setDefault(PreferenceConstants.THREADED_VALIDATION_KEY, true);
  }
}
