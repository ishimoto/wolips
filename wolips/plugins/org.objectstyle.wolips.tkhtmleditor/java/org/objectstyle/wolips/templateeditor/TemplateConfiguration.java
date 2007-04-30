package org.objectstyle.wolips.templateeditor;

import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.ui.part.FileEditorInput;
import org.objectstyle.wolips.wodclipse.core.completion.WodParserCache;

import tk.eclipse.plugin.htmleditor.ColorProvider;
import tk.eclipse.plugin.htmleditor.assist.HTMLAssistProcessor;
import tk.eclipse.plugin.htmleditor.editors.HTMLConfiguration;

public class TemplateConfiguration extends HTMLConfiguration {
  //private JSPScriptletScanner scriptletScanner = null;
  //private JSPDirectiveScanner directiveScanner = null;
  private IContentAssistant _assistant;

  //private JSPDirectiveAssistProcessor directiveProcessor;
  //private JSPScriptletAssistProcessor scriptletProcessor;

  /**
   * The constructor. 
   * 
   * @param colorProvider the <code>ColorProvider</code>.
   */
  public TemplateConfiguration(ColorProvider colorProvider) {
    super(colorProvider);
  }

  //  /**
  //   * @since 2.0.3
  //   */
  //  protected HTMLHyperlinkDetector createHyperlinkDetector() {
  //    HTMLHyperlinkDetector hyperlink = super.createHyperlinkDetector();
  //    hyperlink.addHyperlinkProvider(new JSPHyperlinkProvider());
  //    
  //    IHyperlinkProvider[] providers = HTMLPlugin.getDefault().getHyperlinkProviders();
  //    for(int i=0;i<providers.length;i++){
  //      hyperlink.addHyperlinkProvider(providers[i]);
  //    }
  //    
  //    return hyperlink;
  //  }

  /**
   * Creates and returns the <code>TemplateAssistProcessor</code>.
   * 
   * @return the <code>TemplateAssistProcessor</code>
   * @see TemplateAssistProcessor
   */
  @Override
  protected HTMLAssistProcessor createAssistProcessor() {
    try {
      return new TemplateAssistProcessor(getEditorPart(), WodParserCache.parser(((FileEditorInput) getEditorPart().getEditorInput()).getFile()));
    }
    catch (Exception e) {
      throw new RuntimeException("Failed to create assist processor.", e);
    }
  }
  //  /**
  //   * Returns the <code>JSPScriptletScanner</code>.
  //   * 
  //   * @return the <code>JSPScriptletScanner</code>
  //   * @see JSPScriptletScanner
  //   */
  //  protected RuleBasedScanner getScriptScanner() {
  //    if (scriptletScanner == null) {
  //      scriptletScanner = new JSPScriptletScanner(getColorProvider());
  //      scriptletScanner.setDefaultReturnToken(
  //          getColorProvider().getToken(HTMLPlugin.PREF_COLOR_FG));
  //    }
  //    return scriptletScanner;
  //  }
  //  
  //  /**
  //   * Returns the <code>JSPDirectiveScanner</code>.
  //   * 
  //   * @return the <code>JSPDirectiveScanner</code>
  //   * @see JSPDirectiveScanner
  //   */
  //  protected RuleBasedScanner getDirectiveScanner() {
  //    if (directiveScanner == null) {
  //      directiveScanner = new JSPDirectiveScanner(getColorProvider());
  //      directiveScanner.setDefaultReturnToken(
  //          getColorProvider().getToken(HTMLPlugin.PREF_COLOR_TAG));
  //    }
  //    return directiveScanner;
  //  }
  //  
  //  /**
  //   * Returns the <code>JSPAutoEditStrategy</code>.
  //   * 
  //   * @return the <code>JSPAutoEditStrategy</code>
  //   * @since 2.0.3
  //   * @see JSPAutoEditStrategy
  //   */
  //  protected HTMLAutoEditStrategy createAutoEditStrategy(){
  //    return new JSPAutoEditStrategy();
  //  }
  //
  //  public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
  //    if(assistant==null){
  //      assistant = super.getContentAssistant(sourceViewer);
  //      
  //      directiveProcessor = new JSPDirectiveAssistProcessor();
  //      ((ContentAssistant)assistant).setContentAssistProcessor(
  //          directiveProcessor,HTMLPartitionScanner.HTML_DIRECTIVE);
  //      
  //      scriptletProcessor = new JSPScriptletAssistProcessor();
  //      ((ContentAssistant)assistant).setContentAssistProcessor(
  //          scriptletProcessor,HTMLPartitionScanner.HTML_SCRIPT);
  //    }
  //    return assistant;
  //  }
  //  
  //  /**
  //   * Returns the <code>JSPDirectiveAssistProcessor</code>.
  //   * 
  //   * @return the <code>JSPDirectiveAssistProcessor</code>.
  //   * @see JSPDirectiveAssistProcessor
  //   */
  //  public JSPDirectiveAssistProcessor getDirectiveAssistProcessor(){
  //    return directiveProcessor;
  //  }
  //
  //  /**
  //   * Returns the <code>JSPScriptletAssistProcessor</code>.
  //   * 
  //   * @return the <code>JSPScriptletAssistProcessor</code>.
  //   * @see JSPScriptletAssistProcessor
  //   */
  //  public JSPScriptletAssistProcessor getScriptletAssistProcessor(){
  //    return scriptletProcessor;
  //  }
}
