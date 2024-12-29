package org.objectstyle.wolips.htmlpreview.editor.tags;

import java.util.Stack;

import org.objectstyle.wolips.bindings.wod.IWodBinding;
import org.objectstyle.wolips.bindings.wod.IWodElement;
import org.objectstyle.wolips.wodclipse.core.completion.WodParserCache;

import jp.aonir.fuzzyxml.FuzzyXMLElement;
import jp.aonir.fuzzyxml.FuzzyXMLNode;
import jp.aonir.fuzzyxml.internal.RenderContext;

public class TBRepetitionTagDelegate extends WORepetitionTagDelegate {

	@Override
	public void renderNode(IWodElement wodElement, FuzzyXMLElement xmlElement, RenderContext renderContext, StringBuffer htmlBuffer, StringBuffer cssBuffer, Stack<WodParserCache> caches, Stack<FuzzyXMLNode> nodes) {
		IWodBinding listBinding = wodElement.getBindingNamed("list");
		String listName;
		if (listBinding != null) {
			listName = listBinding.getValue();
		} else {
			listName = "TBRepetition";
		}
		htmlBuffer.append("<span class = \"wodclipse_block wodclipse_TBRepetition\"><span class = \"wodclipse_tag wodclipse_open_tag\">[loop " + listName + "]</span>");
		xmlElement.toXMLString(renderContext, htmlBuffer);
		htmlBuffer.append("<span class = \"wodclipse_tag wodclipse_close_tag\">[/loop " + listName + "]</span></span>");
		
		if (!_cssAdded) {
			cssBuffer.append("span.wodclipse_TBRepetition {");
			cssBuffer.append("  /*border: 1px dashed green;*/");
			cssBuffer.append("}");
			cssBuffer.append("span.wodclipse_TBRepetition span.wodclipse_tag {");
			cssBuffer.append("  color: green;");
			cssBuffer.append("}");
			_cssAdded = true;
		}
	}

}
