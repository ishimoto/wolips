package ${basePackage}.override;

import org.treasureboat.webcore.appserver.TBApplication;
import org.treasureboat.webcore.appserver.TBSession;
import org.treasureboat.webcore.appserver.iface.ITBWActionResults;
import org.treasureboat.webcore.security.user.TBWFirstResponder;

import lombok.extern.slf4j.Slf4j;

/**
 * this is the place after the login has succeeded, what page we should show first.
 * TB will try to find from the NavigationBar the first entry, that allows to show,
 * but you can override the here.
 * 
 * @author treasureboat.org
 */
@Slf4j
public class FirstResponder extends TBWFirstResponder {

	// static final String MyCustomGamePage = "menuItem.custom[ComponentName]Action";  // set in Navigation.plist

	//********************************************************************
	//  Methods : メソッド
	//********************************************************************

	@Override
	public ITBWActionResults createFirstResponseAction(String loginUri) {
		ITBWActionResults result = super.createFirstResponseAction(loginUri);
		if (result != null) {
			// Example of returning a custom page instead of the default Main page
			// return TBNavigationHandler.actionFromHandler(MyCustomGamePage);
			// ...
			// we got something from TB itself
//			// FaceEnums.java contains the ENUMS for the case		
//			switch (FaceEnums.currentFace()) {
//			case default:
//				if (me().can(CoreQualifier.IS_SOME_ROLE)) {
//					return TBNavigationHandler.actionFromHandler(MyCustomGamePage);
//				}
//				break;
//			}
			return result;
		}
		return TBApplication.application().pageWithName("Main", TBSession.session().context());
	}
}
