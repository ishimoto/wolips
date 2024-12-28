package ${basePackage};

import org.treasureboat.webcore.appserver.TBApplication;
import org.treasureboat.webcore.override.TBWInitializerOfFirstDataCreator;
import org.treasureboat.webcore.override.TBWInitializerOfRest;
import org.treasureboat.webcore.override.core.TBWCoreDelegaterBase;
import org.treasureboat.webcore.override.core.TBWCoreQualifierBase;
import org.treasureboat.webcore.security.password.TBWLoginBaseCheck;
import org.treasureboat.webcore.security.user.TBWFirstResponder;

import ${basePackage}.override.CoreDelegater;
import ${basePackage}.override.CoreQualifier;
import ${basePackage}.override.FirstDataCreator;
import ${basePackage}.override.FirstResponder;
import ${basePackage}.override.LoginCheck;
import ${basePackage}.override.RestInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends TBApplication {

	//********************************************************************
	//  BOOT : アプリケーション起動開始
	//********************************************************************

	public static void main(String[] argv) {
		TBApplication.main(argv, Application.class);
	}

	//********************************************************************
	//  Constructor : コンストラクタ
	//********************************************************************

	public Application() {
		super();
		
		log.info("=================================================");
		log.info("Welcome to " + name() + " !");
		log.info("=================================================");
	}

	//********************************************************************
	//  Setup : セットアップ
	//********************************************************************

	@Override
	public TBWCoreDelegaterBase coreDelegater() {
		return new CoreDelegater(); /* TreasureBoat Pro Base Model */
	}

	@Override
	public TBWCoreQualifierBase coreQualifier() {
		return new CoreQualifier(); /* TreasureBoat Pro Base Model */
	}

	@Override
	public TBWInitializerOfFirstDataCreator firstDataCreator() {
		return new FirstDataCreator(); /* TreasureBoat Pro Base Model */
	}

	@Override
	public TBWLoginBaseCheck loginChecker() {
		return new LoginCheck(); /* TreasureBoat Pro Base Model */
	}

	@Override
	public TBWFirstResponder firstResponder() {
		return new FirstResponder();
	}

	@Override
	public TBWInitializerOfRest restInitializer() {
		return new RestInitializer();
	}
}
