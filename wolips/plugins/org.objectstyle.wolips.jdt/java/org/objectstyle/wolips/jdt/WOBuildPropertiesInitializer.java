package org.objectstyle.wolips.jdt;

import org.eclipse.core.resources.IProject;
import org.objectstyle.wolips.core.resources.types.project.ProjectAdapter;
import org.objectstyle.wolips.variables.BuildProperties;
import org.objectstyle.wolips.variables.IBuildPropertiesInitializer;
import org.objectstyle.wolips.variables.VariablesPlugin;

public class WOBuildPropertiesInitializer implements IBuildPropertiesInitializer {
	public void initializeDefaults(BuildProperties buildProperties) {
		IProject project = buildProperties.getProject();
		if (!project.isAccessible()) {
			return;
		}
	}
	

	public void initialize(BuildProperties buildProperties) {
		IProject project = buildProperties.getProject();
		if (!project.isAccessible()) {
			return;
		}

		String buildPropertiesVersionStr = VariablesPlugin.getDefault().getProjectVariables(project).getString("wolips.buildPropertiesVersion");
		int buildPropertiesVersion = buildPropertiesVersionStr == null ? Integer.MAX_VALUE : Integer.parseInt(buildPropertiesVersionStr);
		// MS: if wolips.buildPropertiesVersion is 0, then don't rename framework.name to project.name
		if (!buildProperties.hasValidProjectType() && buildPropertiesVersion > 0) {
			ProjectAdapter projectAdapter = (ProjectAdapter) project.getAdapter(ProjectAdapter.class);
			boolean framework = false;
			if (projectAdapter != null) {
				framework = projectAdapter.isFramework();
			}
			buildProperties.setFramework(framework);
			String projectName = buildProperties.getName();
			if (framework) {
				if (projectName == null) {
					String frameworkName = buildProperties.get("framework.name");
					if (frameworkName == null) {
						buildProperties.setName(project.getName());
					} else {
						buildProperties.setName(frameworkName);
					}
				} else {
					// reset it so we update the dependent properties
					buildProperties.setName(projectName);
				}
				buildProperties.remove("framework.name");
			} else {
				if (projectName == null) {
					buildProperties.setName(project.getName());
				} else {
					// reset it so we update the dependent properties
					buildProperties.setName(projectName);
				}
			}
		}
	}

}
