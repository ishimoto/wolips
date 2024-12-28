package ${basePackage}.override;

#if ($pro || $base)
import org.treasureboat.basemodel.override.TBBM_LoginCheck;
#else
import org.treasureboat.webcore.security.password.TBWLoginBaseCheck;
#end

import lombok.extern.slf4j.Slf4j;

/**
 * this is the place that get called after the login to check if it is allowed or not to login,
 * you can use it with the basemodel out of the box, or you can override all what you need, and do 
 * your own logic.
 * 
 * @author treasureboat.org
 */
@Slf4j
#if ($pro || $base)
	public class LoginCheck extends TBBM_LoginCheck {
#else
	public class LoginCheck extends TBWLoginBaseCheck {
#end
	
	//********************************************************************
	//  implements ITBWLoginProcess
	//********************************************************************

	// override your needs ...

	//********************************************************************
	//  implements ITBWPasswordChange
	//********************************************************************

	// override your needs ...

	//********************************************************************
	//  implements ITBWPasswordReset
	//********************************************************************

	// override your needs ...

}
