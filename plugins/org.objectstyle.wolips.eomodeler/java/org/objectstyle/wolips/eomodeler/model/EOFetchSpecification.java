/*
 * ====================================================================
 * 
 * The ObjectStyle Group Software License, Version 1.0
 * 
 * Copyright (c) 2006 The ObjectStyle Group and individual authors of the
 * software. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 * include the following acknowlegement: "This product includes software
 * developed by the ObjectStyle Group (http://objectstyle.org/)." Alternately,
 * this acknowlegement may appear in the software itself, if and wherever such
 * third-party acknowlegements normally appear.
 * 
 * 4. The names "ObjectStyle Group" and "Cayenne" must not be used to endorse or
 * promote products derived from this software without prior written permission.
 * For written permission, please contact andrus@objectstyle.org.
 * 
 * 5. Products derived from this software may not be called "ObjectStyle" nor
 * may "ObjectStyle" appear in their names without prior written permission of
 * the ObjectStyle Group.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * OBJECTSTYLE GROUP OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on
 * behalf of the ObjectStyle Group. For more information on the ObjectStyle
 * Group, please see <http://objectstyle.org/>.
 *  
 */
package org.objectstyle.wolips.eomodeler.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.internal.databinding.provisional.observable.list.WritableList;

public class EOFetchSpecification extends EOModelObject {
  public static final String NAME = "name";
  public static final String SORT_ORDERINGS = "sortOrderings";
  public static final String QUALIFIER = "qualifier";
  public static final String ENTITY = "entity";
  public static final String FETCH_LIMIT = "fetchLimit";
  public static final String DEEP = "deep";
  public static final String LOCKS_OBJECTS = "locksObjects";
  public static final String PREFETCHING_RELATIONSHIP_KEY_PATHS = "prefetchingRelationshipKeyPaths";
  public static final String PROMPTS_AFTER_FETCH_LIMIT = "promptsAfterFetchLimit";
  public static final String RAW_ROW_KEY_PATHS = "rawRowKeyPaths";
  public static final String REFRESHES_REFETCHED_OBJECTS = "refreshesRefetchedObjects";
  public static final String REQUIRES_ALL_QUALIFIER_BINDING_VARIABLES = "requiresAllQualifierBindingVariables";
  public static final String USES_DISTINCT = "usesDistinct";

  private EOEntity myEntity;
  private String myName;
  private String myClass;
  private Integer myFetchLimit;
  private Boolean myDeep;
  private Boolean myLocksObjects;
  private List myPrefetchingRelationshipKeyPaths;
  private Boolean myPromptsAfterFetchLimit;
  private List myRawRowKeyPaths;
  private Boolean myRefreshesRefetchedObjects;
  private Boolean myRequiresAllQualifierBindingVariables;
  private Boolean myUsesDistinct;
  private List mySortOrderings;
  private IEOQualifier myQualifier;
  private EOModelMap myFetchSpecMap;

  public EOFetchSpecification(EOEntity _entity, String _name) {
    myEntity = _entity;
    myName = _name;
    myClass = "EOFetchSpecification";
    mySortOrderings = new WritableList(new LinkedList(), EOSortOrdering.class);
  }

  protected void firePropertyChange(String _propertyName, Object _oldValue, Object _newValue) {
    super.firePropertyChange(_propertyName, _oldValue, _newValue);
    myEntity._fetchSpecificationChanged(this);
  }

  public int hashCode() {
    return myEntity.hashCode() * myName.hashCode();
  }

  public boolean equals(Object _obj) {
    return (_obj instanceof EOFetchSpecification && ((EOFetchSpecification) _obj).myEntity.equals(myEntity) && ((EOFetchSpecification) _obj).myName.equals(myName));
  }

  public void setName(String _name) {
    String oldName = myName;
    myName = _name;
    firePropertyChange(EOFetchSpecification.NAME, oldName, myName);
  }

  public String getName() {
    return myName;
  }

  public void addSortOrdering(EOSortOrdering _sortOrdering) {
    mySortOrderings.add(_sortOrdering);
    firePropertyChange(EOFetchSpecification.SORT_ORDERINGS, null, null);
  }

  public void removeSortOrdering(EOSortOrdering _sortOrdering) {
    mySortOrderings.remove(_sortOrdering);
    firePropertyChange(EOFetchSpecification.SORT_ORDERINGS, null, null);
  }

  public void setSortOrderings(List _sortOrderings) {
    mySortOrderings = _sortOrderings;
    firePropertyChange(EOFetchSpecification.SORT_ORDERINGS, null, null);
  }

  public void clearSortOrderings() {
    mySortOrderings.clear();
    firePropertyChange(EOFetchSpecification.SORT_ORDERINGS, null, null);
  }

  public List getSortOrderings() {
    return mySortOrderings;
  }

  public void setQualifier(IEOQualifier _qualifier) {
    IEOQualifier oldQualifier = myQualifier;
    myQualifier = _qualifier;
    firePropertyChange(EOFetchSpecification.QUALIFIER, oldQualifier, myQualifier);
  }

  public IEOQualifier getQualifier() {
    return myQualifier;
  }

  public void setEntity(EOEntity _entity) {
    EOEntity oldEntity = myEntity;
    myEntity = _entity;
    firePropertyChange(EOFetchSpecification.ENTITY, oldEntity, myEntity);
  }

  public EOEntity getEntity() {
    return myEntity;
  }

  public Boolean isDeep() {
    return myDeep;
  }

  public void setDeep(Boolean _deep) {
    Boolean oldDeep = myDeep;
    myDeep = _deep;
    firePropertyChange(EOFetchSpecification.DEEP, oldDeep, myDeep);
  }

  public Integer getFetchLimit() {
    return myFetchLimit;
  }

  public void setFetchLimit(Integer _fetchLimit) {
    Integer oldFetchLimit = myFetchLimit;
    myFetchLimit = _fetchLimit;
    firePropertyChange(EOFetchSpecification.FETCH_LIMIT, oldFetchLimit, myFetchLimit);
  }

  public Boolean isLocksObjects() {
    return myLocksObjects;
  }

  public void setLocksObjects(Boolean _locksObjects) {
    Boolean oldLocksObjects = myLocksObjects;
    myLocksObjects = _locksObjects;
    firePropertyChange(EOFetchSpecification.LOCKS_OBJECTS, oldLocksObjects, myLocksObjects);
  }

  public List getPrefetchingRelationshipKeyPaths() {
    return myPrefetchingRelationshipKeyPaths;
  }

  public void setPrefetchingRelationshipKeyPaths(List _prefetchingRelationshipKeyPaths) {
    myPrefetchingRelationshipKeyPaths = _prefetchingRelationshipKeyPaths;
    firePropertyChange(EOFetchSpecification.PREFETCHING_RELATIONSHIP_KEY_PATHS, null, null);
  }

  public Boolean isPromptsAfterFetchLimit() {
    return myPromptsAfterFetchLimit;
  }

  public void setPromptsAfterFetchLimit(Boolean _promptsAfterFetchLimit) {
    Boolean oldPromptsAfterFetchLimit = myPromptsAfterFetchLimit;
    myPromptsAfterFetchLimit = _promptsAfterFetchLimit;
    firePropertyChange(EOFetchSpecification.PROMPTS_AFTER_FETCH_LIMIT, oldPromptsAfterFetchLimit, myPromptsAfterFetchLimit);
  }

  public List getRawRowKeyPaths() {
    return myRawRowKeyPaths;
  }

  public void setRawRowKeyPaths(List _rawRowKeyPaths) {
    myRawRowKeyPaths = _rawRowKeyPaths;
    firePropertyChange(EOFetchSpecification.RAW_ROW_KEY_PATHS, null, null);
  }

  public Boolean isRefreshesRefetchedObjects() {
    return myRefreshesRefetchedObjects;
  }

  public void setRefreshesRefetchedObjects(Boolean _refreshesRefetchedObjects) {
    Boolean oldRefreshesRefetchedObjects = myRefreshesRefetchedObjects;
    myRefreshesRefetchedObjects = _refreshesRefetchedObjects;
    firePropertyChange(EOFetchSpecification.REFRESHES_REFETCHED_OBJECTS, oldRefreshesRefetchedObjects, myRefreshesRefetchedObjects);
  }

  public Boolean isRequiresAllQualifierBindingVariables() {
    return myRequiresAllQualifierBindingVariables;
  }

  public void setRequiresAllQualifierBindingVariables(Boolean _requiresAllQualifierBindingVariables) {
    Boolean oldRequiresAllQualifierBindingVariables = myRequiresAllQualifierBindingVariables;
    myRequiresAllQualifierBindingVariables = _requiresAllQualifierBindingVariables;
    firePropertyChange(EOFetchSpecification.REQUIRES_ALL_QUALIFIER_BINDING_VARIABLES, oldRequiresAllQualifierBindingVariables, myRequiresAllQualifierBindingVariables);
  }

  public Boolean isUsesDistinct() {
    return myUsesDistinct;
  }

  public void setUsesDistinct(Boolean _usesDistinct) {
    Boolean oldUsesDistinct = myUsesDistinct;
    myUsesDistinct = _usesDistinct;
    firePropertyChange(EOFetchSpecification.USES_DISTINCT, oldUsesDistinct, myUsesDistinct);
  }

  public void loadFromMap(EOModelMap _map) throws EOModelException {
    myFetchSpecMap = _map;
    // "entityName" = myEntity
    myClass = _map.getString("class", true);
    myFetchLimit = _map.getInteger("fetchLimit");
    myDeep = _map.getBoolean("isDeep");
    myLocksObjects = _map.getBoolean("locksObjects");
    myPrefetchingRelationshipKeyPaths = _map.getList("prefetchingRelationshipKeyPaths", true);
    myPromptsAfterFetchLimit = _map.getBoolean("prompsAfterFetchLimit");

    Map qualifierMap = _map.getMap("qualifier");
    if (qualifierMap != null) {
      myQualifier = EOQualifierFactory.qualifierForMap(new EOModelMap(qualifierMap));
    }
    myRawRowKeyPaths = _map.getList("rawRowKeyPaths", true);
    myRefreshesRefetchedObjects = _map.getBoolean("refreshesRefetchedObjects");
    myRequiresAllQualifierBindingVariables = _map.getBoolean("requiresAllQualifierBindingVariables");
    myUsesDistinct = _map.getBoolean("usesDistinct");

    List sortOrderings = _map.getList("sortOrderings");
    if (sortOrderings != null) {
      Iterator sortOrderingsIter = sortOrderings.iterator();
      while (sortOrderingsIter.hasNext()) {
        EOModelMap sortOrderingMap = new EOModelMap((Map) sortOrderingsIter.next());
        EOSortOrdering sortOrdering = new EOSortOrdering();
        sortOrdering.loadFromMap(sortOrderingMap);
        addSortOrdering(sortOrdering);
      }
    }
  }

  public EOModelMap toMap() {
    EOModelMap fetchSpecMap = myFetchSpecMap.cloneModelMap();
    fetchSpecMap.setString("entityName", myEntity.getName(), true);
    fetchSpecMap.setString("class", myClass, true);
    fetchSpecMap.setInteger("fetchLimit", myFetchLimit);
    fetchSpecMap.setBoolean("isDeep", myDeep);
    fetchSpecMap.setBoolean("locksObjects", myLocksObjects);
    fetchSpecMap.setList("prefetchingRelationshipKeyPaths", myPrefetchingRelationshipKeyPaths);
    fetchSpecMap.setBoolean("prompsAfterFetchLimit", myPromptsAfterFetchLimit);
    if (myQualifier == null) {
      fetchSpecMap.setMap("qualifier", null);
    }
    else {
      fetchSpecMap.setMap("qualifier", myQualifier.toMap());
    }
    fetchSpecMap.setList("rawRowKeyPaths", myRawRowKeyPaths);
    fetchSpecMap.setBoolean("refreshesRefetchedObjects", myRefreshesRefetchedObjects);
    fetchSpecMap.setBoolean("requiresAllQualifierBindingVariables", myRequiresAllQualifierBindingVariables);
    fetchSpecMap.setBoolean("usesDistinct", myUsesDistinct);

    List sortOrderings = new LinkedList();
    Iterator sortOrderingsIter = mySortOrderings.iterator();
    while (sortOrderingsIter.hasNext()) {
      EOSortOrdering sortOrdering = (EOSortOrdering) sortOrderingsIter.next();
      EOModelMap sortOrderingMap = sortOrdering.toMap();
      sortOrderings.add(sortOrderingMap);
    }
    fetchSpecMap.setList("sortOrderings", sortOrderings);
    return fetchSpecMap;
  }

  public void resolve(List _failures) {
    // TODO
  }
  
  public void verify(List _failures) {
    // TODO
    if (myQualifier != null) {
      myQualifier.verify(_failures);
    }
    Iterator sortOrderingsIter = mySortOrderings.iterator();
    while (sortOrderingsIter.hasNext()) {
      EOSortOrdering sortOrdering = (EOSortOrdering) sortOrderingsIter.next();
      sortOrdering.verify(_failures);
    }
  }
}
