package org.objectstyle.wolips.eomodeler.core.model;

public class EOModelRenderContext {
	private static ThreadLocal<EOModelRenderContext> _renderContext = new ThreadLocal<EOModelRenderContext>();

	public static EOModelRenderContext getInstance() {
		EOModelRenderContext renderContext = _renderContext.get();
		if (renderContext == null) {
			renderContext = new EOModelRenderContext();
		}
		return renderContext;
	}

	public static void setRenderContext(EOModelRenderContext renderContext) {
		_renderContext.set(renderContext);
	}

	public static void clearRenderContext() {
		_renderContext.remove();
	}

	private String _prefix;

	private String _eogenericRecordClassName;

	private String _superclassPackage;
	
	private boolean _java; // Server-side
	
	private boolean _javaClient; // Client-side
	
	private boolean _javaClientCommon; // Client-side (common classes, not used)

	public EOModelRenderContext() {
		_prefix = "_";
//		_eogenericRecordClassName = "com.webobjects.eocontrol.EOGenericRecord";
		_eogenericRecordClassName = "org.treasureboat.enterprise.eof.TBEnterpriseGenericRecord";
	}
	
	public void setJava(boolean javaServerSide) {
		_java = javaServerSide;
	}
	
	public boolean isJava() {
		return _java;
	}
	
	public void setJavaClient(boolean javaClient) {
		_javaClient = javaClient;
	}
	
	public boolean isJavaClient() {
		return _javaClient;
	}
	
	public void setJavaClientCommon(boolean javaClientCommon) {
		_javaClientCommon = javaClientCommon;
	}
	
	public boolean isJavaClientCommon() {
		return _javaClientCommon;
	}

	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	public String getPrefix() {
		return _prefix;
	}

	public void setSuperclassPackage(String superclassPackage) {
		_superclassPackage = superclassPackage;
	}

	public String getSuperclassPackage() {
		return _superclassPackage;
	}

	public void setEOGenericRecordClassName(String eogenericRecordClassName) {
		_eogenericRecordClassName = eogenericRecordClassName;
	}

	public String getEOGenericRecordClassName() {
		return _eogenericRecordClassName;
	}

	// If there is a JavaClient class defined in the TBModel, then this method gives preference over the server class name
	public String getClassNameForEntity(EOEntity entity) {
	  String className;
	  if (_javaClientCommon) {
		className = entity.getParentClassName();
	  }
	  else if (_javaClient) {
	    className = entity.getClientClassName();
	  }
	  else {
	    className = entity.getClassName();
	  }
	  if (className != null) {
		  className = className.replace('$', '.');
	  }
	  return className;
	}
	
	/*
	 * Return the class name for a server-side entity 
	 */
	public String getServerClassNameForEntity(EOEntity entity) {
		  String className;
		 
		  if (_java) {
			  className = entity.getClassName();
		  } else className = null;
		  
		  if (className != null) {
			  className = className.replace('$', '.');
		  }
		  return className;
		}
	
	/*
	 * If "JavaClient" is selected in the EOGenerator file then the JavaClient class name is returned from the TBModel
	 */
	public String getClientClassNameForEntity(EOEntity entity) {
		  String className;
		  if (_javaClientCommon) {
			className = entity.getParentClassName();
		  }
		  else if (_javaClient) {
		    className = entity.getClientClassName();
		  }
		  else {
		    className = null;
		  }
		  if (className != null) {
			  className = className.replace('$', '.');
		  }
		  return className;
	}

}
