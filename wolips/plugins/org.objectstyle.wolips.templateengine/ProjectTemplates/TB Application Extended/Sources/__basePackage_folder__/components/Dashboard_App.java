package ${basePackage}.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.treasureboat.webcore.appserver.TBContext;
import org.treasureboat.webcore.appserver.iface.ITBWActionResults;
import org.treasureboat.webcore.components.TBComponent;

public class Dashboard_App extends TBComponent {

	private static final long serialVersionUID = 1L;

	/**
	 * <a href="http://wiki.wocommunity.org/display/documentation/Wonder+Logging">new org.slf4j.Logger</a>
	 */
	static final Logger log = LoggerFactory.getLogger(Dashboard_App.class);

	//********************************************************************
	//	Constructor : コンストラクタ
	//********************************************************************

	public Dashboard_App(TBContext context) {
		super(context);
	}

	//********************************************************************
	//	Actions : アクション
	//********************************************************************

	public ITBWActionResults doTestAction() {
		System.err.println("--> I am doing a test."); // XXX

		// do something here for testing
		
		return goToMySelfAction();
	}
}
