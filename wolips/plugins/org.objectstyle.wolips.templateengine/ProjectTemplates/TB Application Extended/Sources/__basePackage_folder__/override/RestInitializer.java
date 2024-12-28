package ${basePackage}.override;

import org.treasureboat.webcore.override.TBWInitializerOfRest;


import lombok.extern.slf4j.Slf4j;

/**
 * this is the place for initialize your rest calls
 * 
 * @author treasureboat.org
 */
@Slf4j
public class RestInitializer extends TBWInitializerOfRest {

	@Override
	public void restInitializer() {
		super.restInitializer();

		/*
		 * Uncomment this to create the restRequestHandler for Example : TBPerson
		 * 
		 * TBRouteRequestHandler restRequestHandler = new TBRouteRequestHandler();
		 * restRequestHandler.addDefaultRoutes(_TBPerson.ENTITY_NAME);
		 * TBRouteRequestHandler.register(restRequestHandler);
		 */
	}
}
