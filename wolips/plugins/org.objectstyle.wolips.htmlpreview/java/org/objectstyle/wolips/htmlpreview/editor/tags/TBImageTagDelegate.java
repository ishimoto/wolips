package org.objectstyle.wolips.htmlpreview.editor.tags;

import java.util.Stack;

import org.objectstyle.wolips.bindings.wod.IWodElement;
import org.objectstyle.wolips.htmlpreview.editor.TagDelegate;
import org.objectstyle.wolips.wodclipse.core.completion.WodParserCache;

import jp.aonir.fuzzyxml.FuzzyXMLElement;
import jp.aonir.fuzzyxml.FuzzyXMLNode;
import jp.aonir.fuzzyxml.internal.RenderContext;

public class TBImageTagDelegate extends WOImageTagDelegate {

	@Override
	public void renderNode(IWodElement wodElement, FuzzyXMLElement xmlElement, RenderContext renderContext, StringBuffer htmlBuffer, StringBuffer cssBuffer, Stack<WodParserCache> caches, Stack<FuzzyXMLNode> nodes) {
		String imageUrl = TagDelegate.getResourceUrl("framework", "filename", "href", wodElement, caches);
		htmlBuffer.append("<img");
		TagDelegate.appendHtmlBindings(htmlBuffer, wodElement);
		htmlBuffer.append(" src = \"" + imageUrl + "\"/>");
	}

}
