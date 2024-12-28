package ${basePackage}.migration;

import org.treasureboat.enterprise.migration.TBEnterpriseMigrationDatabase;
import org.treasureboat.enterprise.migration.TBEnterpriseModelVersion;

import org.treasureboat.enterprise.eof.ec.TBEnterpriseEditingContext;
import org.treasureboat.foundation.array.TBFArray;

public class ${projectName}0 extends TBEnterpriseMigrationDatabase.Migration {

	@Override
	public TBFArray<TBEnterpriseModelVersion> modelDependencies() {
		return null;
	}
  
//	@Override
//	public void downgrade(TBEnterpriseEditingContext editingContext, TBEnterpriseMigrationDatabase database) throws Throwable {
//	    // DO NOTHING
//	}

	@Override
	public void upgrade(TBEnterpriseEditingContext editingContext, TBEnterpriseMigrationDatabase database) throws Throwable {
	  
    // migration code comes here
	  
	}
	
	@Override
	public void foreignKeyUpgrade(TBEnterpriseEditingContext editingContext, TBEnterpriseMigrationDatabase database) throws Throwable {
		
	}

	@Override
	public void indexUpgrade(TBEnterpriseEditingContext editingContext, TBEnterpriseMigrationDatabase database) throws Throwable {
		
	}

	@Override
	public void runMigrationUpgrade(TBEnterpriseEditingContext editingContext, TBEnterpriseMigrationDatabase database) throws Throwable {
		
	}

	
}