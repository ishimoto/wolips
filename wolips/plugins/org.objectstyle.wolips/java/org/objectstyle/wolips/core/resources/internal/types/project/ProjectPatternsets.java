/* ====================================================================
 * 
 * The ObjectStyle Group Software License, Version 1.0 
 *
 * Copyright (c) 2004 - 2006 The ObjectStyle Group 
 * and individual authors of the software.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        ObjectStyle Group (http://objectstyle.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "ObjectStyle Group" and "Cayenne" 
 *    must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact andrus@objectstyle.org.
 *
 * 5. Products derived from this software may not be called "ObjectStyle"
 *    nor may "ObjectStyle" appear in their names without prior written
 *    permission of the ObjectStyle Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE OBJECTSTYLE GROUP OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the ObjectStyle Group.  For more
 * information on the ObjectStyle Group, please see
 * <http://objectstyle.org/>.
 *
 */
package org.objectstyle.wolips.core.resources.internal.types.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.objectstyle.wolips.core.resources.pattern.PatternsetMatcher;
import org.objectstyle.wolips.core.resources.types.ILocalizedPath;
import org.objectstyle.wolips.core.resources.types.IPBDotProjectOwner;
import org.objectstyle.wolips.core.resources.types.IResourceType;
import org.objectstyle.wolips.core.resources.types.project.IProjectPatternsets;

/**
 * @author ulrich
 */
public class ProjectPatternsets implements IProjectPatternsets, IResourceType {

	/**
	 * EXTENSION file extension for patternset files
	 */
	public final static String EXTENSION = "patternset";

	/**
	 * name of the woproject folder
	 */
	public final static String ANT_FOLDER_NAME = "woproject";

	private PatternsetMatcher woappResourcesIncludeMatcher;

	private PatternsetMatcher woappResourcesExcludeMatcher;

	private PatternsetMatcher resourcesIncludeMatcher;

	private PatternsetMatcher resourcesExcludeMatcher;

	private PatternsetMatcher classesIncludeMatcher;

	private PatternsetMatcher classesExcludeMatcher;

	private final static PatternsetMatcher DEFAULT_EXCLUDE_MATCHER = new PatternsetMatcher(new String[] { "**/.svn", "**/.svn/**", "**/CVS", "**/*.eomodeld~", "**/*.eomodeld~/**", "**/CVS/**", "**/build/**", "**/dist/**" });

	private IProject project;

	/**
	 * @param project
	 */
	public ProjectPatternsets(IProject project) {
		super();
		this.project = project;
	}
	
	private String[] getWSResourcesIncludeStringsDefault() {
		return new String[] { "WebServerResources/**/*" };
	}

	private String[] getWSResourcesExcludeStringsDefault() {
		return new String[0];
	}

	private String[] getResourcesIncludeStringsDefault() {
		return new String[] { "Components/**/*.wo/**/*", "Components/**/*.api", "Resources/**/*" };
	}

	private String[] getResourcesExcludeStringsDefault() {
		return new String[] { "Resources/**/*.eomodeld~/**" };
	}

	private String[] getClassesIncludeStringsDefault() {
		return new String[] { "**/*.class", "*.properties" };
	}

	private String[] getClassesExcludeStringsDefault() {
		return new String[] { "build.properties" };
	}

	protected static class PatternsetWorkspaceRunnable implements Runnable {

		private PatternsetMatcher matcher;

		String[] defaultPattern;

		PatternsetWorkspaceRunnable(String[] defaultPattern) {
			super();
			this.defaultPattern = defaultPattern;

		}

		public void run() {
			matcher = new PatternsetMatcher(defaultPattern);
		}

		protected PatternsetMatcher getMatcher() {
			return matcher;
		}
	}

	/**
	 * @return Returns the classesExcludeMatcher.
	 */
	public PatternsetMatcher getClassesExcludeMatcher() {
		if (this.classesExcludeMatcher != null) {
			return this.classesExcludeMatcher;
		}
		PatternsetWorkspaceRunnable patternsetWorkspaceRunnable = new PatternsetWorkspaceRunnable(getClassesExcludeStringsDefault());
		patternsetWorkspaceRunnable.run();
		this.classesExcludeMatcher = patternsetWorkspaceRunnable.getMatcher();
		return this.classesExcludeMatcher;
	}

	/**
	 * @return Returns the classesIncludeMatcher.
	 */
	public PatternsetMatcher getClassesIncludeMatcher() {
		if (this.classesIncludeMatcher != null) {
			return this.classesIncludeMatcher;
		}
		PatternsetWorkspaceRunnable patternsetWorkspaceRunnable = new PatternsetWorkspaceRunnable(getClassesIncludeStringsDefault());
		patternsetWorkspaceRunnable.run();
		this.classesIncludeMatcher = patternsetWorkspaceRunnable.getMatcher();
		return this.classesIncludeMatcher;
	}

	/**
	 * @return Returns the resourcesExcludeMatcher.
	 */
	public PatternsetMatcher getResourcesExcludeMatcher() {
		if (this.resourcesExcludeMatcher != null) {
			return this.resourcesExcludeMatcher;
		}
		PatternsetWorkspaceRunnable patternsetWorkspaceRunnable = new PatternsetWorkspaceRunnable(getResourcesExcludeStringsDefault());
		patternsetWorkspaceRunnable.run();
		this.resourcesExcludeMatcher = patternsetWorkspaceRunnable.getMatcher();
		return this.resourcesExcludeMatcher;
	}

	/**
	 * @return Returns the resourcesIncludeMatcher.
	 */
	public PatternsetMatcher getResourcesIncludeMatcher() {
		if (this.resourcesIncludeMatcher != null) {
			return this.resourcesIncludeMatcher;
		}
		PatternsetWorkspaceRunnable patternsetWorkspaceRunnable = new PatternsetWorkspaceRunnable(getResourcesIncludeStringsDefault());
		patternsetWorkspaceRunnable.run();
		this.resourcesIncludeMatcher = patternsetWorkspaceRunnable.getMatcher();
		return this.resourcesIncludeMatcher;
	}

	/**
	 * @return Returns the woappResourcesExcludeMatcher.
	 */
	public PatternsetMatcher getWoappResourcesExcludeMatcher() {
		if (this.woappResourcesExcludeMatcher != null) {
			return this.woappResourcesExcludeMatcher;
		}
		PatternsetWorkspaceRunnable patternsetWorkspaceRunnable = new PatternsetWorkspaceRunnable(getWSResourcesExcludeStringsDefault());
		patternsetWorkspaceRunnable.run();
		this.woappResourcesExcludeMatcher = patternsetWorkspaceRunnable.getMatcher();
		return this.woappResourcesExcludeMatcher;
	}

	/**
	 * @return Returns the woappResourcesIncludeMatcher.
	 */
	public PatternsetMatcher getWoappResourcesIncludeMatcher() {
		if (this.woappResourcesIncludeMatcher != null) {
			return this.woappResourcesIncludeMatcher;
		}
		PatternsetWorkspaceRunnable patternsetWorkspaceRunnable = new PatternsetWorkspaceRunnable(getWSResourcesIncludeStringsDefault());
		patternsetWorkspaceRunnable.run();
		this.woappResourcesIncludeMatcher = patternsetWorkspaceRunnable.getMatcher();
		return this.woappResourcesIncludeMatcher;
	}

	/**
	 * @param resource
	 * @return true if the resource matches the classes pattern
	 */
	public boolean matchesClassesPattern(IResource resource) {
		String relativePath = resource.getProjectRelativePath().toString();
		return !getClassesExcludeMatcher().match(relativePath) && !DEFAULT_EXCLUDE_MATCHER.match(relativePath) && getClassesIncludeMatcher().match(relativePath);
	}

	/**
	 * @param resource
	 * @return true if the resource matches the WOAppResources pattern
	 */
	public boolean matchesWOAppResourcesPattern(IResource resource) {
		String relativePath = resource.getProjectRelativePath().toString();
		return !this.getWoappResourcesExcludeMatcher().match(relativePath) && !DEFAULT_EXCLUDE_MATCHER.match(relativePath) && this.getWoappResourcesIncludeMatcher().match(relativePath);
	}

	/**
	 * @param resource
	 * @return true if the resource matches the Resources pattern
	 */
	public boolean matchesResourcesPattern(IResource resource) {
		String relativePath = resource.getProjectRelativePath().toString();
		boolean matches = !this.getResourcesExcludeMatcher().match(relativePath) && !DEFAULT_EXCLUDE_MATCHER.match(relativePath) && this.getResourcesIncludeMatcher().match(relativePath);
		return matches;
	}

	/**
	 * Sets up the *.patternset files in the case they don't exist.
	 */
	public void setUpPatternsetFiles() {
		this.getClassesExcludeMatcher();
		this.getClassesIncludeMatcher();
		this.getResourcesExcludeMatcher();
		this.getResourcesIncludeMatcher();
		this.getWoappResourcesExcludeMatcher();
		this.getWoappResourcesIncludeMatcher();
	}

	/**
	 * Releases the patternset cache
	 */
	public void releasePatternsetCache() {
		this.woappResourcesIncludeMatcher = null;
		this.woappResourcesExcludeMatcher = null;
		this.resourcesIncludeMatcher = null;
		this.resourcesExcludeMatcher = null;
		this.classesIncludeMatcher = null;
		this.classesExcludeMatcher = null;
	}

	/**
	 * @param string
	 * @return true is the pattern allready exists
	 */
	public boolean hasClassesIncludePattern(String string) {
		return this.getClassesIncludeMatcher().hasPattern(string);
	}

	/**
	 * @param string
	 * @return true is the pattern allready exists
	 */
	public boolean hasClassesExcludePattern(String string) {
		return this.getClassesExcludeMatcher().hasPattern(string);
	}

	/**
	 * @param string
	 * @return true is the pattern allready exists
	 */
	public boolean hasWOAppResourcesIncludePattern(String string) {
		return this.getWoappResourcesIncludeMatcher().hasPattern(string);
	}

	/**
	 * @param string
	 * @return true is the pattern allready exists
	 */
	public boolean hasWOAppResourcesExcludePattern(String string) {
		return this.getWoappResourcesExcludeMatcher().hasPattern(string);
	}

	/**
	 * @param string
	 * @return true is the pattern allready exists
	 */
	public boolean hasResourcesIncludePattern(String string) {
		return this.getResourcesIncludeMatcher().hasPattern(string);
	}

	/**
	 * @param string
	 * @return true is the pattern allready exists
	 */
	public boolean hasResourcesExcludePattern(String string) {
		return this.getResourcesExcludeMatcher().hasPattern(string);
	}

	public IProject getIProject() {
		return project;
	}

	public IPBDotProjectOwner getPBDotProjectOwner() {
		return null;
	}

	public IPBDotProjectOwner getPBDotProjectOwner(IResource resource) {
		return null;
	}

	public IResource getUnderlyingResource() {
		return null;
	}

	public ILocalizedPath localizedRelativeResourcePath(IPBDotProjectOwner pbDotProjectOwner, IResource resource) {
		return null;
	}

}