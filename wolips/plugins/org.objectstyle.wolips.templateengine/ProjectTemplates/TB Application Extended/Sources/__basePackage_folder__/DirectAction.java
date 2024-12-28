package ${basePackage};

#if ($pro)
import org.treasureboat.cms.action.TBCMS_DirectAction;
#else
import ${basePackage}.components.Main;
#end
import org.treasureboat.webcore.annotations.TBAction;
import org.treasureboat.webcore.appserver.TBDirectAction;
import org.treasureboat.webcore.appserver.TBRequest;
import org.treasureboat.webcore.appserver.iface.ITBWActionResults;
import org.treasureboat.webcore.security.user.TBWFirstResponder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DirectAction extends TBDirectAction {

	//********************************************************************
	//  Constructor : コンストラクタ
	//********************************************************************

	public DirectAction(TBRequest request) {
		super(request);
	}

	//********************************************************************
	//  Login Starter ログイン入り口
	//********************************************************************

	@Override
	@TBAction
	public ITBWActionResults standard() {
		ITBWActionResults result = TBWFirstResponder.standard(existingSession(), context());
		if (result != null) {
			return result;
		}
#if ($pro)
		return TBCMS_DirectAction.startingPageForD2WSession(context(), "intraCms");
#else
		Main nextPage = pageWithName(Main.class);
		return nextPage;
#end	
	}

}
