package ${basePackage}.components;

#if ($pro)
import org.treasureboat.d2w.component.TBD2WComponent;
#else
import org.treasureboat.webcore.components.TBComponent;
#end
import org.treasureboat.webcore.annotations.TBPageAccess;
import org.treasureboat.webcore.appserver.TBContext;

@TBPageAccess(navigationState = "Welcome", requireLogin = "#if($base || $pro)true#{else}false#end")
public class Main extends #if ($pro) TBD2WComponent #else TBComponent #end {

	private static final long serialVersionUID = 1L;

	//********************************************************************
	//  Constructor : コンストラクタ
	//********************************************************************

	public Main(TBContext context) {
		super(context);
	}

}
