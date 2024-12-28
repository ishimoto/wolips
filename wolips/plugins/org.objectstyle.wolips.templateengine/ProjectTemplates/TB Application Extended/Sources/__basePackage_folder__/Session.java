package $basePackage;

import ${basePackage}.navigation.NavigationController;
import org.treasureboat.webcore.appserver.TBSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Session extends TBSession {

	private static final long serialVersionUID = 1L;
   
	//********************************************************************
	//  Constructor : コンストラクター
	//********************************************************************

	public Session() {
		super();

		// Set the Navigation Controller
		if(getNavController() == null) {
			setNavController(new NavigationController(this));
		}

	}
}
