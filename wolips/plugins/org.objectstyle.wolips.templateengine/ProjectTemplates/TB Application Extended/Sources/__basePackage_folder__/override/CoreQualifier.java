package ${basePackage}.override;

#if ($pro)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.treasureboat.basemodel.corequalifier.TBBM_CoreQualifier;
import org.treasureboat.basemodel.eo.TBDomain;
import org.treasureboat.enterprise.eof.qualifiers.TBEnterpriseQualifier;
import org.treasureboat.foundation.role.TBFRoleConstants;

import org.treasureboat.enterprise.model.TBEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * this is the place for the coreQualifier. usually the super will handle already TB delete and TB domain
 * processing.
 * 
 * @author treasureboat.org
 */
@Slf4j
public class CoreQualifier extends TBBM_CoreQualifier {
  

	public static String IS_DEVELOPER = TBFRoleConstants.IS_THE_ROLE + "Developer";

	
	//********************************************************************
	//  Methods : メソッド
	//********************************************************************

	/**
	 * CORE QUALIFIER
	 * 
	 * @param entity
	 * @param domain
	 * @param isStopRestrictDomainQualifier
	 * @param isStopRestrictDeleteQualifier
	 * @return TBEnterpriseQualifier
	 */
	@Override
	public TBEnterpriseQualifier qualifier(TBEntity entity, TBDomain domain, boolean isStopRestrictDomainQualifier, boolean isStopRestrictDeleteQualifier) {
		TBEnterpriseQualifier parentQualifier = super.qualifier(entity, domain, isStopRestrictDomainQualifier, isStopRestrictDeleteQualifier);

//		TBPerson person = TBPerson.currentLoginPerson();
		
		TBEnterpriseQualifier qualifier = parentQualifier;

			//  Example:  Change [EntityName] with entities of interest ********************
//			switch (entity.name()) {
//			case _{EntityName].ENTITY_NAME: {
//				System.err.println("\n{EntityName] SubClass Entity");
//				if (!person.can(IS_DEVELOPER)) {
//					qualifier = _[EntityName].TB_PERSON.eq(person).and(parentQualifier);
//				} else {
//					System.err.print("\n Developer ");
//				}
//			}
//			default: {
//				
//			}
//			}
		return qualifier;

	}

	
}
#else
import org.treasureboat.webcore.override.core.TBWCoreQualifierBase;

/**
 * this is the place for the coreQualifier. usually the super will handle already TB delete and TB domain
 * processing.
 * 
 * @author treasureboat.org
 */
public class CoreQualifier extends TBWCoreQualifierBase {
	
//	public static String IS_SOME_ROLE = TBFRoleConstants.IS_THE_ROLE + "SomeRole";

  
}
#end
