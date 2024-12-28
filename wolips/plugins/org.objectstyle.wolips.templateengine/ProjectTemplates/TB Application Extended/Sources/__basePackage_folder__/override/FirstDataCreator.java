package ${basePackage}.override;

#if ($pro || $base)
import org.treasureboat.basemodel.override.TBBM_FirstDataCreator;
#else
import org.treasureboat.webcore.override.TBWInitializerOfFirstDataCreator;
#end
import org.treasureboat.foundation.TBFTarget;
import org.treasureboat.foundation.dashboard.TBFDashboardEntry;
import org.treasureboat.foundation.dashboard.TBFDashboardManager;
import org.treasureboat.foundation.enums.ETBFDashboardGroup;
import org.treasureboat.foundation.enums.ETBFDashboardRestriction;
import org.treasureboat.foundation.image.TBFImage;
import org.treasureboat.webcore.security.grant.TBWGrantAccess;


import lombok.extern.slf4j.Slf4j;


/**
 * this is the place after the application has started, to create some automatic data.
 * it also is used for creating some application Dashboard entries.
 * 
 * @author treasureboat.org
 */
@Slf4j
#if ($pro || $base)
public class FirstDataCreator extends TBBM_FirstDataCreator {
#else
public class FirstDataCreator extends TBWInitializerOfFirstDataCreator { 
#end

	//********************************************************************
	//  Methods : メソッド
	//********************************************************************

	@Override
	public void createFirstDataset() {
		super.createFirstDataset();

		// Dashboard
		TBFImage image;
		TBFDashboardEntry entry;
		
		/* 
		 * Special Case : APPLICATION_PANEL 
		 * we don't need a Icon, but we need the 'page://' and text for Display the Component
		 */
		image = new TBFImage(null, "Dashboard.PlayGround", 64, 64, "page://Dashboard_App", TBFTarget.NULL);
		entry = new TBFDashboardEntry("App_PlayGround", ETBFDashboardGroup.APPLICATION_PANEL, image, ETBFDashboardRestriction.DEVELOPER_EXCLUSIVE,
				null);
		TBFDashboardManager.manager().addEntry(entry);

		TBWGrantAccess.setGrantAsRestrict();
	}
}
