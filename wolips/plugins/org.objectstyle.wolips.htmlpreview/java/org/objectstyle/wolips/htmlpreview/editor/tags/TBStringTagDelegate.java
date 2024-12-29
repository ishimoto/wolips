package org.objectstyle.wolips.htmlpreview.editor.tags;

import java.util.Stack;

import org.objectstyle.wolips.bindings.wod.IWodBinding;
import org.objectstyle.wolips.bindings.wod.IWodElement;
import org.objectstyle.wolips.wodclipse.core.completion.WodParserCache;

import jp.aonir.fuzzyxml.FuzzyXMLElement;
import jp.aonir.fuzzyxml.FuzzyXMLNode;
import jp.aonir.fuzzyxml.internal.RenderContext;

public class TBStringTagDelegate extends WOStringTagDelegate {
	
	@Override
	public void renderNode(IWodElement wodElement, FuzzyXMLElement xmlElement, RenderContext renderContext, StringBuffer htmlBuffer, StringBuffer cssBuffer, Stack<WodParserCache> caches, Stack<FuzzyXMLNode> nodes) {
		IWodBinding valueBinding = wodElement.getBindingNamed("value");
		if (valueBinding.isKeyPath()) {
			htmlBuffer.append("<span class = \"wodclipse_TBString\">[" + valueBinding.getValue() + "]</span>");
		} else {
			htmlBuffer.append(valueBinding.getValue());
		}
		
		if (!_cssAdded) {
			cssBuffer.append("span.wodclipse_TBString {");
			cssBuffer.append("  color: red;");
			cssBuffer.append("}");
			_cssAdded = true;
		}
	}

}
