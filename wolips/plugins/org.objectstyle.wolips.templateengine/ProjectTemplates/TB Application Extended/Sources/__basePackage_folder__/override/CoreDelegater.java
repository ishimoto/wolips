package ${basePackage}.override;

import lombok.extern.slf4j.Slf4j;

#if ($pro)
import org.treasureboat.basemodel.eo.TBPerson;
import org.treasureboat.enterprise.datasource.TBEnterpriseAbstractDataSource;
import org.treasureboat.enterprise.datasource.TBEnterpriseDatabaseDataSource;
import org.treasureboat.enterprise.eof.ITBEnterpriseObject;
import org.treasureboat.enterprise.eof.ec.TBEnterpriseEditingContext;
import org.treasureboat.enterprise.eof.qualifiers.TBEnterpriseQualifier;
import org.treasureboat.enterprise.model.TBEntity;
import org.treasureboat.enterprise.qualifiers.TBEnterpriseAndQualifier;
import org.treasureboat.foundation.TBFConstants;
import org.treasureboat.foundation.TBFString;
import org.treasureboat.override.TBBM_CoreDelegater;
import org.treasureboat.foundation.date.TBFLocalDate;
import org.treasureboat.webcore.appserver.TBApplication;
import org.treasureboat.webcore.override.core.TBWCoreQualifierFinder;

import com.webobjects.directtoweb.D2WContext;

@Slf4j
/**
 * <p>CoreDelegater hooks allows the you the programmer to improve/customize the behavior of Sangria Applications.</p>
 * <p>restrictedChoiceList() - You can qualify the lists of toOnePopups in Create/Edit PageConfiguration (PC).</p>
 * <p>qualifierForConfiguration() - You can qualify the result sets of PageConfigurations (PCs)</p>
 * <p>queryDidDataSource() - You can set the auxiliaryQualifier on the Find PageConfiguration (PC) based on ids</p>
 * <p>NOTE:  You can now use the @TBCoreDelegate annotation on MyEoButtonDelegate.java class to implement these features</p>
 * <p>Using the @TBCoreDelegate is better in that it will allow you to keep the code closer to the Object of Interest</P
 * <p>instead of having HUGE switch statements in this class</p>
 * @author treasureboat.org
 */
public class CoreDelegater extends TBBM_CoreDelegater {
  
 
  //********************************************************************
  //  Methods : メソッド
  //********************************************************************
	@Override
	/**
	 *  <p>NOTE:  You can now use the @TBCoreDelegate annotation on MyEoButtonDelegate.java class to implement these features</p>
	 *  <p>Control toOne relationship list display</p>
	 *  <p>for example:  you have a toOne relationship categoryType on the MYEntity EO</p>
	 *  <p>finder = "/CreateMYEntity/categoryType" or "/EditMYEntity/categoryType".  You could have separate case statements for each.</p>
	 *  <p>In the case statement you would have a qualifer
	 *  			qualifier = new TBEnterpriseAndQualifier(qualifier, _MyCategoryType.SOME_ATTRIBUTE.eq(someValue));
	 * 
	 * @param d2wContext
	 *            the D2W Context
	 * @param destinationEntity
	 *            the destination entity of the toOneRelationship
	 * @param qualifier
	 *            the current qualifier
	 * @param finder
	 *            the TBWCoreQualifierFinder finder
	 * @return
	 */
	public TBEnterpriseQualifier restrictedChoiceList(D2WContext d2wContext, TBEntity destinationEntity, TBEnterpriseQualifier qualifier, TBWCoreQualifierFinder finder) {
		qualifier = super.restrictedChoiceList(d2wContext, destinationEntity, qualifier, finder);
	    String parentPageConfiguration = d2wContext.parentPageConfiguration();
	    String pageConfigurationName = d2wContext.dynamicPage();
	    String propertyKey = d2wContext.propertyKey();

//    String finder = TBFString.emptyStringForNull(parentPageConfiguration) + "/" + TBFString.emptyStringForNull(pageConfigurationName) + TBFConstants.SLASH
//            + TBFString.emptyStringForNull(propertyKey);
    
	    // get the current EO in the d2wcontext
	    Object myObject = d2wContext.valueForKey("object");
		System.err.print("\n restrictedChoiceList finder.finder() case \"" + finder.finder() + "\":");
    
	    switch (finder.finder()) {
		case "**Selector**":
			// do your code
			break;
		
//		case "/[Create | Edit][EntityName]":
//		qualifier = new TBEnterpriseAndQualifier(qualifier, _TBPerson.ROLES.eq(sacRole));
//		return qualifier

		default:
			// log.info("the restrictedChoiceList finder is '{}'", finder);
			break;
		}
	    
	    return qualifier;
	}
   
	@Override
	/**
	 *  <p>NOTE:  You can now use the @TBCoreDelegate annotation on MyEoButtonDelegate.java class to implement these features</p>
	 * Qualify Find actions
	 * <p>
	 * Use this method to add qualifiers to attributes on the <code>Find</code> actions
	 * </p>
	 * <p>
	 * finder in this case is [MyEntityName]/[id], no need to add the "Find" action prefix
	 * </p>
	 * <p>
	 * You are setting the auxiliaryQualifier on the databaseDataSource to pre-qualify the find qualifier created by the UI
	 * </p>
	 * <p>
	 * For example if you wanted to limit the query result to my organization or current year, you would use this to control what the "id"/any find
	 * can see
	 * </p>
	 * 
	 * @param editingContext
	 *            the current TBEnterprise editingContext
	 * @param d2wContext
	 *            the current D2W context
	 * @param ds
	 *            the TBEnterpriseAbstractDataSource
	 * @param finder
	 *            TBWCoreQualifierFinder
	 * @return
	 */
  public TBEnterpriseAbstractDataSource queryDidDataSource(TBEnterpriseEditingContext editingContext, D2WContext d2wContext, TBEnterpriseAbstractDataSource ds, TBWCoreQualifierFinder finder) {
	  ds = super.queryDidDataSource(editingContext, d2wContext, ds, finder);

	  String entityName = d2wContext.entity().name();
	  String id = d2wContext.id();

	  TBEnterpriseDatabaseDataSource dbs = (TBEnterpriseDatabaseDataSource) ds;
	  TBEnterpriseQualifier qualifier;

		
	  String finderX = TBFConstants.SLASH + finder.finder();
	  System.err.print("\n queryDidDataSource finder.finder() case \"" + finderX + "\":");
	  
	  switch (finderX) {
	  case "**Selector**":
		  // inject a Qualifier
		  //	TBPerson person = TBPerson.currentLoginPerson();
		  qualifier = new TBEnterpriseAndQualifier(coreQualifier(dbs) /*, _Rental.CUSTOMER.dot(_Customer.TB_PERSON.eq(person))*/ );
		  dbs.setAuxiliaryQualifier(qualifier);
		  break;

	  default:
		  // log.info("the qualifierForConfiguration finder is '{}'", finder);
		  break;
	  }

	  return ds;
	}
}


	/**
	 * <p>NOTE:  You should use the @TBCoreDelegate annotation on [MyEO]ButtonDelegate.java class to implement these features</p>
	 * Set Qualifier for filtering List page configurations.
	 * <p>
	 * Use this method to qualify the set of data visible to the "id" (role)
	 * </p>
	 * <p>
	 * d2wContext.id() returns to string value of SomeID of the NavigationBar List_SomeID_MyEntity
	 * </p>
	 * <p>
	 * For example. if you have a List_Clerk_MyEntity and a List_Supervisor_MyEntity entry in the Navigationmenu.plist/default.plist, <br>
	 * then you can limit what the _Clerk_ can see which will be smaller set than the _Supervisor_.
	 * </p>
	 * <p>
	 * You should add a case statements to the switch () {} where the case is equal to a string literal of any PageConfiguration with a suffix of
	 * "id", e.g. case "/[List | Create | Edit][EntityName]/[id]":
	 * </p>
	 * <p>
	 * For example. the PC string would look like "/ListMyEntityName/Developer" or "ListMyEntityName/Clerk" within the case you can qualify the EO for
	 * the "id" or semi-role.
	 * </p>
	 * 
	 * @param ec
	 *            the current TBEnterpriseEditingContext
	 * @param d2wContext
	 *            the current D2W Context
	 * @param masterObject
	 *            ITBEnterpriseObject MyEntity
	 * @param destinationEntityName
	 *            the string of the destination entity
	 * @param finder
	 *            the TBWCoreQualifierFinder
	 * @return
	 */
	//  public TBEnterpriseQualifier qualifierForConfiguration(TBEnterpriseEditingContext ec, D2WContext d2wContext, ITBEnterpriseObject masterObject,
	//                                               String destinationEntityName, TBWCoreQualifierFinder finder) {
	//
	//	  TBEnterpriseQualifier qualifier = super.qualifierForConfiguration(ec, d2wContext, masterObject, destinationEntityName, finder);
	//
	//	String parentPageConfiguration = d2wContext.parentPageConfiguration();
	//	String pageConfigurationName = d2wContext.dynamicPage();
	//	String id = d2wContext.id();
	//	String finderPlusId = TBFConstants.SLASH + finder.finder() + TBFString.emptyStringForNull(id);
	//	TBPerson person = TBPerson.currentLoginPerson();
	//	System.err.print("\n qualifierForConfiguration finderPlusId case \"" + finderPlusId + "\":");
	//
	//    switch (finderPlusId) {
	//	case "**Selector**":
	//		// do your code
	//		break;
	//		
	////		case "/[List | Create | Edit][EntityName]/[id]":
	////		return _[EntityName].CREATED_BY.eq(person).and(qualifier);
	//
	//	default:
	//		// log.info("the qualifierForConfiguration finder is '{}'", finder);
	//		break;
	//	}
	//	
	//    return qualifier;
	//  }

#else
import org.treasureboat.webcore.override.core.TBWCoreDelegaterBase;
  
/**
 * this is the place for the coreDelegater. this is a hook for make it easy to improve
 * Sangria Applications.
 * 
 * @author treasureboat.org
 */
@
public class CoreDelegater extends TBWCoreDelegaterBase {
  
}
#end
