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
package org.objectstyle.wolips.eomodeler.core.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.objectstyle.wolips.baseforplugins.util.ComparisonUtils;
import org.objectstyle.wolips.baseforplugins.util.StringUtils;
import org.objectstyle.wolips.eomodeler.core.Messages;
import org.objectstyle.wolips.eomodeler.core.utils.BooleanUtils;

public class EORelationship extends UserInfoableEOModelObject<EOEntity> implements IEOAttribute, ISortableEOModelObject {
	public static final String TO_MANY = "toMany";

	public static final String TO_ONE = "toOne";

	public static final String CLASS_PROPERTY = "classProperty";

	public static final String CLIENT_CLASS_PROPERTY = "clientClassProperty";

	public static final String COMMON_CLASS_PROPERTY = "commonClassProperty";

	public static final String NAME = "name";

	public static final String DESTINATION = "destination";

	public static final String DEFINITION = "definition";

	public static final String DELETE_RULE = "deleteRule";

	public static final String JOIN_SEMANTIC = "joinSemantic";

	public static final String OPTIONAL = "optional";

	public static final String MANDATORY = "mandatory";

	public static final String OWNS_DESTINATION = "ownsDestination";

	public static final String PROPAGATES_PRIMARY_KEY = "propagatesPrimaryKey";

	public static final String NUMBER_OF_TO_MANY_FAULTS_TO_BATCH_FETCH = "numberOfToManyFaultsToBatchFetch";

	public static final String JOINS = "joins";

	public static final String JOIN = "join";

	public static final String CORE_DATA = "coreData";

	public static final String COPY_TYPE = "copyType";

	public static final String D2WTYPE = "d2wType";	

	public static final String ENGLISH = "english";
	public static final String FRENCH = "french";
	public static final String GERMAN = "german";
	public static final String DUTCH = "dutch";
	public static final String ITALIAN = "italian";
	public static final String JAPANESE = "japanese";
	public static final String CHINESE = "chinese";
	public static final String SPANISH = "spanish";
	public static final String PORTUGUESE = "portuguese";
	public static final String BRAZILIAN = "brazilian";
	
	private EOEntity myEntity;

	private EOEntity myDestination;

	private String myName;

	private String myDefinition;

	private Boolean myCoreData;

	private String myCopytypeName;

	private EORelationshipPath myDefinitionPath;

	private Boolean myMandatory;

	private Boolean myToMany;

	private Boolean myOwnsDestination;

	private Boolean myPropagatesPrimaryKey;

	private Boolean myClassProperty;

	private Boolean myClientClassProperty;

	private Boolean _commonClassProperty;

	private Integer myNumberOfToManyFaultsToBatchFetch;

	private EODeleteRule myDeleteRule;

	private EOJoinSemantic myJoinSemantic;

	private List<EOJoin> myJoins;

	private EOModelMap myRelationshipMap;

	private EOEntity myEntityBeforeCloning;

	private String myD2wType;

	private String myEnglish;
	private String myFrench;
	private String myGerman;
	private String myDutch;
	private String myItalian;
	private String myJapanese;
	private String myChinese;
	private String mySpanish;
	private String myPortuguese;
	private String myBrazilian;

	public EORelationship() {
		myJoins = new LinkedList<EOJoin>();
		myRelationshipMap = new EOModelMap();
		myDeleteRule = EODeleteRule.getDeleteRuleByID(null);
		myJoinSemantic = EOJoinSemantic.getJoinSemanticByID(null);
	}

	public EORelationship(String _name) {
		this();
		myName = _name;
	}

	public EORelationship(String _name, String _definition) {
		this(_name);
		myDefinition = _definition;
	}

	public Set<EOModelReferenceFailure> getReferenceFailures() {
		Set<EOModelReferenceFailure> referenceFailures = new HashSet<EOModelReferenceFailure>();
		for (EORelationship referencingRelationship : getReferencingFlattenedRelationships()) {
			referenceFailures.add(new EOFlattenedRelationshipRelationshipReferenceFailure(referencingRelationship, this));
		}
		for (EOAttribute referencingAttributes : getReferencingFlattenedAttributes()) {
			referenceFailures.add(new EOFlattenedAttributeRelationshipReferenceFailure(referencingAttributes, this));
		}
		return referenceFailures;
	}

	public List<EOAttribute> getReferencingFlattenedAttributes() {
		List<EOAttribute> referencingFlattenedAttributes = new LinkedList<EOAttribute>();
		if (myEntity != null) {
			for (EOModel model : getEntity().getModel().getModelGroup().getModels()) {
				for (EOEntity entity : model.getEntities()) {
					for (EOAttribute attribute : entity.getAttributes()) {
						if (attribute.isFlattened()) {
							EOAttributePath attributePath = attribute.getDefinitionPath();
							if (attributePath != null && attributePath.isRelatedTo(this)) {
								referencingFlattenedAttributes.add(attribute);
							}
						}
					}
				}
			}
		}
		return referencingFlattenedAttributes;
	}

	public List<EORelationship> getReferencingFlattenedRelationships() {
		List<EORelationship> referencingFlattenedRelationships = new LinkedList<EORelationship>();
		if (myEntity != null) {
			for (EOModel model : getEntity().getModel().getModelGroup().getModels()) {
				for (EOEntity entity : model.getEntities()) {
					for (EORelationship relationship : entity.getRelationships()) {
						if (relationship.isFlattened()) {
							EORelationshipPath relationshipPath = relationship.getDefinitionPath();
							if (relationshipPath != null && relationshipPath.isRelatedTo(this)) {
								referencingFlattenedRelationships.add(relationship);
							}
						}
					}
				}
			}
		}
		return referencingFlattenedRelationships;
	}

	public void pasted() throws DuplicateNameException {
		if (myEntityBeforeCloning != null) {
			if (myDestination == myEntityBeforeCloning) {
				myDestination = myEntity;
			} else {
				if (myDestination != null) {
					EOModel model = myEntity.getModel();
					EOModelGroup modelGroup = model.getModelGroup();
					myDestination = modelGroup.getEntityNamed(myDestination.getName());
				}
			}
			for (EOJoin join : myJoins) {
				join.pasted();
			}
			myEntityBeforeCloning = null;
		}
	}

	@SuppressWarnings("unused")
	protected void _joinChanged(EOJoin _join, String _propertyName, Object _oldValue, Object _newValue) {
		firePropertyChange(EORelationship.JOIN, null, _join);
	}

	protected void _propertyChanged(String _propertyName, Object _oldValue, Object _newValue) {
		if (myEntity != null) {
			myEntity._relationshipChanged(this, _propertyName, _oldValue, _newValue);
		}
	}

//	public int hashCode() {
//		return ((myEntity == null) ? 1 : myEntity.hashCode()) * ((myName == null) ? 1 : myName.hashCode());
//	}
//
//	public boolean equals(Object _obj) {
//		boolean equals = false;
//		if (_obj instanceof EORelationship) {
//			EORelationship relationship = (EORelationship) _obj;
//			equals = (relationship == this) || (ComparisonUtils.equals(relationship.myEntity, myEntity) && ComparisonUtils.equals(relationship.myName, myName));
//			if (equals && _obj != this) {
//				throw new IllegalStateException("RUH ROH!");
//			}
//		}
//		return equals;
//	}

	public boolean isInverseRelationship(EORelationship _relationship) {
		boolean isInverse;
		if (_relationship == null) {
			isInverse = false;
		} else {
			List<EOJoin> inverseJoins = new LinkedList<EOJoin>(_relationship.getJoins());
			if (inverseJoins.size() != myJoins.size()) {
				isInverse = false;
			} else {
				Iterator<EOJoin> joinsIter = myJoins.iterator();
				isInverse = true;
				while (isInverse && joinsIter.hasNext()) {
					EOJoin join = joinsIter.next();
					EOJoin inverseJoin = null;
					int inverseJoinsSize = inverseJoins.size();
					for (int inverseJoinNum = 0; inverseJoin == null && inverseJoinNum < inverseJoinsSize; inverseJoinNum++) {
						EOJoin potentialInverseJoin = inverseJoins.get(inverseJoinNum);
						if (potentialInverseJoin.isInverseJoin(join)) {
							inverseJoin = potentialInverseJoin;
						}
					}
					if (inverseJoin == null) {
						isInverse = false;
					}
				}
			}
		}
		return isInverse;
	}

	public EORelationship getInverseRelationship() {
		EORelationship inverseRelationship = null;
		if (myDestination != null) {
			Iterator<EORelationship> relationshipsIter = myDestination.getRelationships().iterator();
			while (inverseRelationship == null && relationshipsIter.hasNext()) {
				EORelationship potentialInverseRelationship = relationshipsIter.next();
				if (potentialInverseRelationship.isInverseRelationship(this)) {
					inverseRelationship = potentialInverseRelationship;
				}
			}
		}
		return inverseRelationship;
	}

	public EORelationship createInverseRelationshipNamed(String _name, boolean _toMany) {
		EORelationship inverseRelationship = new EORelationship(myDestination.findUnusedRelationshipName(_name));
		inverseRelationship.setClassProperty(getClassProperty());
		inverseRelationship.setToMany(Boolean.valueOf(_toMany));
		inverseRelationship.setDestination(myEntity);
		for (EOJoin join : getJoins()) {
			join.addInverseJoinInto(inverseRelationship, false);
		}
		inverseRelationship._setEntity(myDestination);
		return inverseRelationship;
	}

	public boolean isRelatedTo(EOEntity _entity) {
		return _entity.equals(myDestination);
	}

	public boolean isRelatedTo(EOAttribute _attribute) {
		boolean isRelated = false;
		Iterator<EOJoin> joinsIter = myJoins.iterator();
		while (!isRelated && joinsIter.hasNext()) {
			EOJoin join = joinsIter.next();
			isRelated = join.isRelatedTo(_attribute);
		}
		return isRelated;
	}

	public Set<EOAttribute> getRelatedAttributes() {
		Set<EOAttribute> relatedAttributes = new HashSet<EOAttribute>();
		Iterator<EOJoin> joinsIter = myJoins.iterator();
		while (joinsIter.hasNext()) {
			EOJoin join = joinsIter.next();
			EOAttribute sourceAttribute = join.getSourceAttribute();
			if (sourceAttribute != null) {
				relatedAttributes.add(sourceAttribute);
			}
			EOAttribute destinationAttribute = join.getDestinationAttribute();
			if (destinationAttribute != null) {
				relatedAttributes.add(destinationAttribute);
			}
		}
		return relatedAttributes;
	}

	public void setDefinition(String _definition) {
		String oldDefinition = myDefinition;
		myDefinition = _definition;
		updateDefinitionPath();
		firePropertyChange(EORelationship.DEFINITION, oldDefinition, myDefinition);
	}

	public String getDefinition() {
		String definition;
		if (isFlattened() && myDefinitionPath != null) {
			definition = myDefinitionPath.toKeyPath();
		} else {
			definition = _getDefinition();
		}
		return definition;
	}

	public EORelationshipPath getDefinitionPath() {
		if (myDefinitionPath == null) {
			updateDefinitionPath();
		}
		return myDefinitionPath;
	}

	public String _getDefinition() {
		return myDefinition;
	}

	public void updateDefinitionBecauseRelationshipNameChanged(EORelationship relationship) {
		if (isFlattened()) {
			EORelationshipPath definitionPath = getDefinitionPath();
			if (definitionPath != null && definitionPath.isRelatedTo(relationship)) {
				setDefinition(definitionPath.toKeyPath());
			}
		}
	}

	protected void updateDefinitionPath() {
		if (isFlattened()) {
			EOEntity entity = getEntity();
			if (entity != null) {
				AbstractEOAttributePath definitionPath = entity.resolveKeyPath(_getDefinition());
				if (definitionPath instanceof EORelationshipPath && definitionPath.isValid()) {
					myDefinitionPath = (EORelationshipPath) definitionPath;
				} else {
					myDefinitionPath = null;
				}
			}
			else {
				myDefinition = null;
			}
		} else {
			myDefinitionPath = null;
		}
	}

	public void setClassProperty(Boolean _classProperty) {
		setClassProperty(_classProperty, true);
	}

	public void setClassProperty(Boolean _classProperty, boolean _fireEvents) {
		Boolean oldClassProperty = myClassProperty;
		myClassProperty = _classProperty;
		if (_fireEvents) {
			firePropertyChange(EORelationship.CLASS_PROPERTY, oldClassProperty, myClassProperty);
		}
	}

	public Boolean isClassProperty() {
		return myClassProperty;
	}

	public Boolean getClassProperty() {
		return isClassProperty();
	}

	public void setCommonClassProperty(Boolean commonClassProperty) {
    setCommonClassProperty(commonClassProperty, true);
  }

  public void setCommonClassProperty(Boolean commonClassProperty, boolean fireEvents) {
    Boolean oldCommonClassProperty = _commonClassProperty;
    _commonClassProperty = commonClassProperty;
    if (fireEvents) {
      firePropertyChange(EORelationship.COMMON_CLASS_PROPERTY, oldCommonClassProperty, _commonClassProperty);
    }
  }

  public Boolean isCommonClassProperty() {
    return _commonClassProperty;
  }

  public Boolean getCommonClassProperty() {
    return isCommonClassProperty();
  }

	public void setClientClassProperty(Boolean _clientClassProperty) {
		setClientClassProperty(_clientClassProperty, true);
	}

	public void setClientClassProperty(Boolean _clientClassProperty, boolean _fireEvents) {
		Boolean oldClientClassProperty = myClientClassProperty;
		myClientClassProperty = _clientClassProperty;
		if (_fireEvents) {
			firePropertyChange(EORelationship.CLIENT_CLASS_PROPERTY, oldClientClassProperty, myClientClassProperty);
		}
	}

	public Boolean isClientClassProperty() {
		return myClientClassProperty;
	}

	public Boolean getClientClassProperty() {
		return isClientClassProperty();
	}

	public boolean isFlattened() {
		return StringUtils.isKeyPath(_getDefinition());
	}

	public EORelationship getParentRelationship() {
		EORelationship parentRelationship = null;
		EOEntity parent = myEntity.getParent();
		if (parent != null) {
			parentRelationship = parent.getRelationshipNamed(myName);
		}
		return parentRelationship;
	}

	public boolean isInherited() {
		return getParentRelationship() != null;
	}

	public void _setEntity(EOEntity _entity) {
		myEntity = _entity;
	}

	public EOEntity getEntity() {
		return myEntity;
	}

	public void setName(String _name) throws DuplicateNameException {
		setName(_name, true);
	}

	public void setName(String _name, boolean _fireEvents) throws DuplicateNameException {
		if (_name == null) {
			throw new NullPointerException(Messages.getString("EORelationship.noBlankRelationshipNames"));
		}
		String oldName = myName;
		if (myEntity != null) {
			myEntity._checkForDuplicateRelationshipName(this, _name, null);
		}
		myName = _name;
		if (myEntity != null && myEntity.getModel() != null) {
			EOModelGroup modelGroup = myEntity.getModel().getModelGroup();
			for (EOEntity entity : modelGroup.getEntities()) {
				for (EOAttribute attribute : entity.getAttributes()) {
					attribute.updateDefinitionBecauseRelationshipNameChanged(this);
				}
				for (EORelationship relationship : entity.getRelationships()) {
					relationship.updateDefinitionBecauseRelationshipNameChanged(this);
				}
			}
		}
		if (_fireEvents) {
			firePropertyChange(EORelationship.NAME, oldName, myName);
		}
	}

	public String getName() {
		return myName;
	}

	public String getUppercaseName() {
		return getName().toUpperCase();
	}
	
	public String getUppercaseUnderscoreName() {
		return StringUtils.camelCaseToUnderscore(getName()).toUpperCase();
	}

	public String getCapitalizedName() {
		String name = getName();
		if (name != null) {
			name = StringUtils.toUppercaseFirstLetter(name);
		}
		return name;
	}

	public EODeleteRule getDeleteRule() {
		return myDeleteRule;
	}

	public void setDeleteRule(EODeleteRule _deleteRule) {
		EODeleteRule oldDeleteRule = myDeleteRule;
		myDeleteRule = _deleteRule;
		firePropertyChange(EORelationship.DELETE_RULE, oldDeleteRule, myDeleteRule);
	}

	public EOEntity getActualDestination() {
		EOEntity destination = getDestination();
		if (destination != null && destination.isPartialEntitySet()) {
			destination = destination.getPartialEntity();
		}
		return destination;
	}

	public EOEntity getDestination() {
		EOEntity destination;
		if (isFlattened()) {
			AbstractEOAttributePath targetAttributePath = myEntity.resolveKeyPath(getDefinition());
			if (targetAttributePath != null && targetAttributePath.getChildIEOAttribute() != null) {
				destination = ((EORelationshipPath) targetAttributePath).getChildRelationship().getDestination();
			} else {
				destination = null;
			}
		} else {
			destination = myDestination;
		}
		return destination;
	}

	public void setDestination(EOEntity _destination) {
		setDestination(_destination, true);
	}

	public void setDestination(EOEntity _destination, boolean _fireEvents) {
		EOEntity oldDestination = myDestination;
		myDestination = _destination;
		if (_fireEvents) {
			firePropertyChange(EORelationship.DESTINATION, oldDestination, myDestination);
		}
	}

	public EOJoinSemantic getJoinSemantic() {
		return myJoinSemantic;
	}

	public void setJoinSemantic(EOJoinSemantic _joinSemantic) {
		EOJoinSemantic oldJoinSemantic = myJoinSemantic;
		myJoinSemantic = _joinSemantic;
		firePropertyChange(EORelationship.JOIN_SEMANTIC, oldJoinSemantic, myJoinSemantic);
	}

	public Boolean getMandatory() {
		return isMandatory();
	}

	public Boolean isMandatory() {
		return myMandatory;
	}

	public void setMandatory(Boolean _mandatory) {
		_setMandatory(_mandatory);
		if (BooleanUtils.isTrue(isToOne())) {
			EOEntity entity = getEntity();
			if (entity != null && !entity.isSingleTableInheritance()) {
				Iterator<EOJoin> joinsIter = getJoins().iterator();
				while (joinsIter.hasNext()) {
					EOJoin join = joinsIter.next();
					EOAttribute sourceAttribute = join.getSourceAttribute();
					if (sourceAttribute != null) {
						sourceAttribute._setAllowsNull(BooleanUtils.negate(_mandatory), true);
					}
				}
			}
		}
	}

	public void _setMandatory(Boolean _mandatory) {
		Boolean oldMandatory = myMandatory;
		myMandatory = _mandatory;
		firePropertyChange(EORelationship.MANDATORY, oldMandatory, myMandatory);
		firePropertyChange(EORelationship.OPTIONAL, BooleanUtils.negate(oldMandatory), BooleanUtils.negate(myMandatory));
	}

	public void setMandatoryIfNecessary() {
		boolean mandatory = false;
		if (BooleanUtils.isTrue(isToOne())) {
			Iterator<EOJoin> joinsIter = getJoins().iterator();
			while (!mandatory && joinsIter.hasNext()) {
				EOJoin join = joinsIter.next();
				EOAttribute sourceAttribute = join.getSourceAttribute();
				if (sourceAttribute != null) {
					mandatory = BooleanUtils.isFalse(sourceAttribute.isAllowsNull());
				}
			}
		}
		setMandatory(Boolean.valueOf(mandatory));
	}

	public Boolean getOptional() {
		return isOptional();
	}

	public Boolean isOptional() {
		return BooleanUtils.negate(isMandatory());
	}

	public void setOptional(Boolean _optional) {
		setMandatory(BooleanUtils.negate(_optional));
	}

	public Boolean getOwnsDestination() {
		return isOwnsDestination();
	}

	public Boolean isOwnsDestination() {
		return myOwnsDestination;
	}

	public void setOwnsDestination(Boolean _ownsDestination) {
		Boolean oldOwnsDestination = myOwnsDestination;
		myOwnsDestination = _ownsDestination;
		firePropertyChange(EORelationship.OWNS_DESTINATION, oldOwnsDestination, myOwnsDestination);
	}

	public Boolean getPropagatesPrimaryKey() {
		return isPropagatesPrimaryKey();
	}

	public Boolean isPropagatesPrimaryKey() {
		return myPropagatesPrimaryKey;
	}

	public void setPropagatesPrimaryKey(Boolean _propagatesPrimaryKey) {
		Boolean oldPropagatesPrimaryKey = myPropagatesPrimaryKey;
		myPropagatesPrimaryKey = _propagatesPrimaryKey;
		firePropertyChange(EORelationship.PROPAGATES_PRIMARY_KEY, oldPropagatesPrimaryKey, myPropagatesPrimaryKey);
	}

	public Boolean getToMany() {
		return isToMany();
	}

	public Boolean isToMany() {
		Boolean toMany = null;
		if (isFlattened() && myEntity != null) {
			AbstractEOAttributePath targetAttributePath = myEntity.resolveKeyPath(getDefinition());
			if (targetAttributePath != null && targetAttributePath.getChildIEOAttribute() != this) {
				toMany = targetAttributePath.isToMany();
			}
		} else {
			toMany = myToMany;
		}
		return toMany;
	}

	public void setToMany(Boolean _toMany) {
		if (!isFlattened()) {
			Boolean oldToMany = myToMany;
			myToMany = _toMany;
			firePropertyChange(EORelationship.TO_MANY, oldToMany, myToMany);
			firePropertyChange(EORelationship.TO_ONE, BooleanUtils.negate(oldToMany), BooleanUtils.negate(myToMany));
		}
	}

	public Boolean getToOne() {
		return isToOne();
	}

	public Boolean isToOne() {
		return BooleanUtils.negate(isToMany());
	}

	public void setToOne(Boolean _toOne) {
		setToMany(BooleanUtils.negate(_toOne));
	}

	public Boolean getCoreData() {
		return isCoreData();
	}
	
	public Boolean isCoreData() {
		return myCoreData;
	}
	
	public void setCoreData(Boolean _coreData) {
		setCoreData(_coreData, true);
	}
	
	public void setCoreData(Boolean _coreData, boolean _fireEvents) {
		Boolean oldCoreData = getCoreData();
		myCoreData = _coreData;
		if (_fireEvents) {
			firePropertyChange(EORelationship.CORE_DATA, oldCoreData, getCoreData());
		}
	}	
	
	public String getCopyType() {
		return myCopytypeName;
	}
		
	public void setCopyType(String _copyType) {
		setCopyType(_copyType, true);
	}
	
	public void setCopyType(String _copyType, boolean _fireEvents) {
		String oldCopyType = getCopyType();
		myCopytypeName = _copyType;
		if (_fireEvents) {
			firePropertyChange(EORelationship.COPY_TYPE, oldCopyType, getCopyType());
		}
	}		
	
	public String getD2wType() {
		return myD2wType;
	}
	
	public void setD2wType(String _d2wType) {
		setD2wType(_d2wType, true);
	}
	
	public void setD2wType(String _d2wType, boolean _fireEvents) {
		String oldD2wType = getD2wType();
		myD2wType = _d2wType;
		if (_fireEvents) {
			firePropertyChange(EORelationship.D2WTYPE, oldD2wType, myD2wType);
		}
	}		

	public String getEnglish() {
		return myEnglish;
	}
	
	public void setEnglish(String _English) {
		setEnglish(_English, true);
	}

	public void setEnglish(String _English, boolean _fireEvents) {
		String old = getEnglish();
		myEnglish = _English;
		if (_fireEvents) {
			firePropertyChange(ENGLISH, old, myEnglish);
		}
	}		

	public String getFrench() {
		return myFrench;
	}
	
	public void setFrench(String _French) {
		setFrench(_French, true);
	}
	
	public void setFrench(String _French, boolean _fireEvents) {
		String old = getFrench();
		myFrench = _French;
		if (_fireEvents) {
			firePropertyChange(FRENCH, old, myFrench);
		}
	}		
	
	public String getGerman() {
		return myGerman;
	}
	
	public void setGerman(String _German) {
		setGerman(_German, true);
	}
	
	public void setGerman(String _German, boolean _fireEvents) {
		String old = getGerman();
		myGerman = _German;
		if (_fireEvents) {
			firePropertyChange(GERMAN, old, myGerman);
		}
	}		
	
	public String getDutch() {
		return myDutch;
	}
	
	public void setDutch(String _Dutch) {
		setDutch(_Dutch, true);
	}
	
	public void setDutch(String _Dutch, boolean _fireEvents) {
		String old = getDutch();
		myDutch = _Dutch;
		if (_fireEvents) {
			firePropertyChange(DUTCH, old, myDutch);
		}
	}		
	
	public String getItalian() {
		return myItalian;
	}
	
	public void setItalian(String _Italian) {
		setItalian(_Italian, true);
	}
	
	public void setItalian(String _Italian, boolean _fireEvents) {
		String old = getItalian();
		myItalian = _Italian;
		if (_fireEvents) {
			firePropertyChange(ITALIAN, old, myItalian);
		}
	}		
	
	public String getJapanese() {
		return myJapanese;
	}
	
	public void setJapanese(String _Japanese) {
		setJapanese(_Japanese, true);
	}
	
	public void setJapanese(String _Japanese, boolean _fireEvents) {
		String old = getJapanese();
		myJapanese = _Japanese;
		if (_fireEvents) {
			firePropertyChange(JAPANESE, old, myJapanese);
		}
	}		
	
	public String getChinese() {
		return myChinese;
	}
	
	public void setChinese(String _Chinese) {
		setChinese(_Chinese, true);
	}
	
	public void setChinese(String _Chinese, boolean _fireEvents) {
		String old = getChinese();
		myChinese = _Chinese;
		if (_fireEvents) {
			firePropertyChange(CHINESE, old, myChinese);
		}
	}		
	
	public String getSpanish() {
		return mySpanish;
	}
	
	public void setSpanish(String _Spanish) {
		setSpanish(_Spanish, true);
	}
	
	public void setSpanish(String _Spanish, boolean _fireEvents) {
		String old = getSpanish();
		mySpanish = _Spanish;
		if (_fireEvents) {
			firePropertyChange(SPANISH, old, mySpanish);
		}
	}		
	
	public String getPortuguese() {
		return myPortuguese;
	}
	
	public void setPortuguese(String _Portuguese) {
		setPortuguese(_Portuguese, true);
	}
	
	public void setPortuguese(String _Portuguese, boolean _fireEvents) {
		String old = getPortuguese();
		myPortuguese = _Portuguese;
		if (_fireEvents) {
			firePropertyChange(PORTUGUESE, old, myPortuguese);
		}
	}		
	
	public String getBrazilian() {
		return myBrazilian;
	}
	
	public void setBrazilian(String _Brazilian) {
		setBrazilian(_Brazilian, true);
	}
	
	public void setBrazilian(String _Brazilian, boolean _fireEvents) {
		String old = getBrazilian();
		myBrazilian = _Brazilian;
		if (_fireEvents) {
			firePropertyChange(BRAZILIAN, old, myBrazilian);
		}
	}		
	
	public void setNumberOfToManyFaultsToBatchFetch(Integer _numberOfToManyFaultsToBatchFetch) {
		Integer oldNumberOfToManyFaultsToBatchFetch = myNumberOfToManyFaultsToBatchFetch;
		myNumberOfToManyFaultsToBatchFetch = _numberOfToManyFaultsToBatchFetch;
		firePropertyChange(EORelationship.NUMBER_OF_TO_MANY_FAULTS_TO_BATCH_FETCH, oldNumberOfToManyFaultsToBatchFetch, myNumberOfToManyFaultsToBatchFetch);
	}

	public Integer getNumberOfToManyFaultsToBatchFetch() {
		return myNumberOfToManyFaultsToBatchFetch;
	}

	public void clearJoins() {
		myJoins.clear();
		firePropertyChange(EORelationship.JOINS, null, null);
	}

	public void setJoins(List<EOJoin> _joins) {
		myJoins.clear();
		myJoins.addAll(_joins);
		firePropertyChange(EORelationship.JOINS, null, null);
	}

	public void addJoin(EOJoin _join) {
		addJoin(_join, true);
	}

	public void addJoin(EOJoin _join, boolean _fireEvents) {
		// TODO: Check duplicates?
		_join._setRelationship(this);
		List<EOJoin> oldJoins = null;
		if (_fireEvents) {
			oldJoins = myJoins;
			List<EOJoin> newJoins = new LinkedList<EOJoin>();
			newJoins.addAll(myJoins);
			newJoins.add(_join);
			myJoins = newJoins;
			firePropertyChange(EORelationship.JOINS, oldJoins, myJoins);
		} else {
			myJoins.add(_join);
		}
	}

	public void removeAllJoins() {
		List<EOJoin> oldJoins = myJoins;
		List<EOJoin> newJoins = new LinkedList<EOJoin>();
		myJoins = newJoins;
		firePropertyChange(EORelationship.JOINS, oldJoins, newJoins);
		for (EOJoin join : oldJoins) {
			join._setRelationship(null);
		}
	}

	public void removeJoin(EOJoin _join) {
		List<EOJoin> oldJoins = myJoins;
		List<EOJoin> newJoins = new LinkedList<EOJoin>();
		newJoins.addAll(myJoins);
		newJoins.remove(_join);
		myJoins = newJoins;
		firePropertyChange(EORelationship.JOINS, oldJoins, newJoins);
		_join._setRelationship(null);
	}

	public List<EOJoin> getJoins() {
		return myJoins;
	}

	public EOJoin getFirstJoin() {
		EOJoin join = null;
		Iterator<EOJoin> joinsIter = myJoins.iterator();
		if (joinsIter.hasNext()) {
			join = joinsIter.next();
		}
		return join;
	}

	public void loadFromMap(EOModelMap _relationshipMap, Set<EOModelVerificationFailure> _failures) {
		myRelationshipMap = _relationshipMap;
		if (_relationshipMap.containsKey("dataPath")) {
			myDefinition = _relationshipMap.getString("dataPath", true);
		} else {
			myDefinition = _relationshipMap.getString("definition", true);
		}
		myMandatory = _relationshipMap.getBoolean("isMandatory");
		myToMany = _relationshipMap.getBoolean("isToMany");
		String joinSemanticID = _relationshipMap.getString("joinSemantic", true);
		myJoinSemantic = EOJoinSemantic.getJoinSemanticByID(joinSemanticID);
		myName = _relationshipMap.getString("name", true);
		String deleteRuleID = _relationshipMap.getString("deleteRule", true);
		myDeleteRule = EODeleteRule.getDeleteRuleByID(deleteRuleID);
		myCoreData = _relationshipMap.getBoolean("coreData");
		myCopytypeName = _relationshipMap.getString("copyType", true);
		myD2wType = _relationshipMap.getString("d2wType", true);
		
		myEnglish = _relationshipMap.getString(ENGLISH, true);
		myFrench = _relationshipMap.getString(FRENCH, true);
		myGerman = _relationshipMap.getString(GERMAN, true);
		myDutch = _relationshipMap.getString(DUTCH, true);
		myItalian = _relationshipMap.getString(ITALIAN, true);
		myJapanese = _relationshipMap.getString(JAPANESE, true);
		myChinese = _relationshipMap.getString(CHINESE, true);
		mySpanish = _relationshipMap.getString(SPANISH, true);
		myPortuguese = _relationshipMap.getString(PORTUGUESE, true);
		myBrazilian = _relationshipMap.getString(BRAZILIAN, true);
		
		myOwnsDestination = _relationshipMap.getBoolean("ownsDestination");
		myNumberOfToManyFaultsToBatchFetch = _relationshipMap.getInteger("numberOfToManyFaultsToBatchFetch");
		myPropagatesPrimaryKey = _relationshipMap.getBoolean("propagatesPrimaryKey");
		Set<Map> joins = _relationshipMap.getSet("joins");
		if (joins != null) {
			for (Map originalJoinMap : joins) {
				EOModelMap joinMap = new EOModelMap(originalJoinMap);
				EOJoin join = new EOJoin();
				join.loadFromMap(joinMap, _failures);
				addJoin(join, false);
			}
		}
		loadUserInfo(_relationshipMap);
	}

	public EOModelMap toMap() {
		EOModelMap relationshipMap = myRelationshipMap.cloneModelMap();
		if (myDestination != null) {
			relationshipMap.setString("destination", myDestination.getName(), true);
		} else {
			relationshipMap.remove("destination");
		}
		relationshipMap.setString("definition", getDefinition(), true);
		relationshipMap.remove("dataPath");
		relationshipMap.setBoolean("isMandatory", myMandatory, EOModelMap.YNOptionalDefaultNo);
		relationshipMap.setBoolean("isToMany", myToMany, EOModelMap.YN);
		if (!isFlattened() && myJoinSemantic != null) {
			relationshipMap.setString("joinSemantic", myJoinSemantic.getID(), true);
		} else if (isFlattened() && myRelationshipMap.get("joinSemantic") != null) {
			relationshipMap.setString("joinSemantic", (String) myRelationshipMap.get("joinSemantic"), true);
		} else {
			relationshipMap.remove("joinSemantic");
		}
		relationshipMap.setString("name", myName, true);
		if (myDeleteRule != null && myDeleteRule != EODeleteRule.NULLIFY) {
			relationshipMap.setString("deleteRule", myDeleteRule.getID(), true);
		} else {
			relationshipMap.remove("deleteRule");
		}
		relationshipMap.setBoolean("coreData", myCoreData, EOModelMap.YN);
		
		if (myCopytypeName != null) {
			relationshipMap.setString("copyType", myCopytypeName, true);
		} else {
			relationshipMap.remove("copyType");
		}
		
		if (myD2wType != null) {
			relationshipMap.setString("d2wType", myD2wType, true);
		} else {
			relationshipMap.remove("d2wType");
		}
		
		if (myEnglish != null) {
			relationshipMap.setString(ENGLISH, myEnglish, true);
		} else {
			relationshipMap.remove(ENGLISH);
		}
		
		if (myFrench != null) {
			relationshipMap.setString(FRENCH, myFrench, true);
		} else {
			relationshipMap.remove(FRENCH);
		}
		
		if (myGerman != null) {
			relationshipMap.setString(GERMAN, myGerman, true);
		} else {
			relationshipMap.remove(GERMAN);
		}
		
		if (myDutch != null) {
			relationshipMap.setString(DUTCH, myDutch, true);
		} else {
			relationshipMap.remove(DUTCH);
		}
		
		if (myItalian != null) {
			relationshipMap.setString(ITALIAN, myItalian, true);
		} else {
			relationshipMap.remove(ITALIAN);
		}
		
		if (myJapanese != null) {
			relationshipMap.setString(JAPANESE, myJapanese, true);
		} else {
			relationshipMap.remove(JAPANESE);
		}
		
		if (myChinese != null) {
			relationshipMap.setString(CHINESE, myChinese, true);
		} else {
			relationshipMap.remove(CHINESE);
		}
		
		if (mySpanish != null) {
			relationshipMap.setString(SPANISH, mySpanish, true);
		} else {
			relationshipMap.remove(SPANISH);
		}
		
		if (myPortuguese != null) {
			relationshipMap.setString(PORTUGUESE, myPortuguese, true);
		} else {
			relationshipMap.remove(PORTUGUESE);
		}
		
		if (myBrazilian != null) {
			relationshipMap.setString(BRAZILIAN, myBrazilian, true);
		} else {
			relationshipMap.remove(BRAZILIAN);
		}
		relationshipMap.setBoolean("ownsDestination", myOwnsDestination, EOModelMap.YNOptionalDefaultNo);
		relationshipMap.setBoolean("propagatesPrimaryKey", myPropagatesPrimaryKey, EOModelMap.YNOptionalDefaultNo);
		relationshipMap.setInteger("numberOfToManyFaultsToBatchFetch", myNumberOfToManyFaultsToBatchFetch);
		Set<Map> joins = new PropertyListSet<Map>();
		for (EOJoin join : myJoins) {
			EOModelMap joinMap = join.toMap();
			joins.add(joinMap);
		}
		relationshipMap.setSet("joins", joins, true);
		writeUserInfo(relationshipMap);
		// remove join semantic for flattened if anything else is changing
		if (isFlattened()) {
			if (!ComparisonUtils.deepEquals(relationshipMap, myRelationshipMap)) {
				relationshipMap.remove("joinSemantic");
			}
		}

		return relationshipMap;
	}

	public void resolve(Set<EOModelVerificationFailure> _failures) {
		if (!isFlattened()) {
			String destinationName = myRelationshipMap.getString("destination", true);
			if (destinationName == null) {
				_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + " has no destination entity.", false));
			} else {
				myDestination = myEntity.getModel().getModelGroup().getEntityNamed(destinationName);
				if (myDestination == null) {
					_failures.add(new MissingEntityFailure(myEntity.getModel(), destinationName));
				}
			}
		} else {
			updateDefinitionPath();
		}

		for (EOJoin join : myJoins) {
			join.resolve(_failures);
		}
	}

	public void verify(Set<EOModelVerificationFailure> _failures) {
		String name = getName();
		if (name == null || name.trim().length() == 0) {
			_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + " has an empty name.", false));
		} else {
			if (name.indexOf(' ') != -1) {
				_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + "'s name has a space in it.", false));
			}
			if (!StringUtils.isLowercaseFirstLetter(name)) {
				_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + "'s name is capitalized, but should not be.", true));
			}
		}
		if (isFlattened()) {
			if (myEntity.resolveKeyPath(getDefinition()) == null) {
				_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + " is flattened and either creates a loop or points to a non-existent target.", false));
			}
		} else {
			if (myDestination == null) {
				_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + " has no destination entity.", false));
			}
		}
		// Q: EOF supports this for some specific cases - disabling until I work out a more concise check
		//if (BooleanUtils.isTrue(isPropagatesPrimaryKey()) && BooleanUtils.isTrue(isToMany())) {
		//	EOEntity destination = getDestination();
		//	if (destination != null && destination.getPrimaryKeyAttributes().size() == 1) {
		//		_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + " is a to-many but also propagates its primary key.", false));
		//	}
		//}
		EOEntity entity = getEntity();
		boolean singleTableInheritance = entity != null && entity.isSingleTableInheritance();
		boolean mandatory = BooleanUtils.isTrue(isMandatory());
		boolean toOne = BooleanUtils.isTrue(isToOne());
		Iterator<EOJoin> joinsIter = myJoins.iterator();
		if (!joinsIter.hasNext() && !isFlattened()) {
			_failures.add(new EOModelVerificationFailure(myEntity.getModel(), this, "The relationship " + getName() + " does not have any joins.", false));
		}
		while (joinsIter.hasNext()) {
			EOJoin join = joinsIter.next();
			join.verify(_failures);
			EOAttribute sourceAttribute = join.getSourceAttribute();
			if (toOne && mandatory && !singleTableInheritance && sourceAttribute != null && BooleanUtils.isTrue(sourceAttribute.isAllowsNull())) {
				_failures.add(new EORelationshipOptionalityMismatchFailure(myEntity.getModel(), this, "The relationship " + getName() + " is mandatory but the attribute " + sourceAttribute.getName() + " allows nulls.", true));
			} else if (toOne && !mandatory && sourceAttribute != null && !BooleanUtils.isTrue(sourceAttribute.isAllowsNull())) {
				_failures.add(new EORelationshipOptionalityMismatchFailure(myEntity.getModel(), this, "The relationship " + getName() + " is optional but the attribute " + sourceAttribute.getName() + " does not allow nulls.", true));
			}
		}
	}

	public String getFullyQualifiedName() {
		return ((myEntity == null) ? "?" : myEntity.getFullyQualifiedName()) + "/rel: " + getName();
	}

	@Override
	public EORelationship _cloneModelObject() {
		EORelationship relationship = new EORelationship(myName);
		if (myEntity == null) {
			relationship.myEntityBeforeCloning = myEntityBeforeCloning;
		} else {
			relationship.myEntityBeforeCloning = myEntity;
		}
		relationship.myDestination = myDestination;
		relationship.myDefinition = myDefinition;
		relationship.myMandatory = myMandatory;
		relationship.myToMany = myToMany;
		relationship.myOwnsDestination = myOwnsDestination;
		relationship.myPropagatesPrimaryKey = myPropagatesPrimaryKey;
		relationship.myClassProperty = myClassProperty;
		relationship.myClientClassProperty = myClientClassProperty;
		relationship.myNumberOfToManyFaultsToBatchFetch = myNumberOfToManyFaultsToBatchFetch;
		relationship.myDeleteRule = myDeleteRule;
		relationship.myJoinSemantic = myJoinSemantic;
		relationship.myCoreData = myCoreData;
		relationship.myD2wType = myD2wType;
		
		relationship.myEnglish = myEnglish;
		relationship.myFrench = myFrench;
		relationship.myGerman = myGerman;
		relationship.myDutch = myDutch;
		relationship.myItalian = myItalian;
		relationship.myJapanese = myJapanese;
		relationship.myChinese = myChinese;
		relationship.mySpanish = mySpanish;
		relationship.myPortuguese = myPortuguese;
		relationship.myBrazilian = myBrazilian;
		
		relationship.myCopytypeName = myCopytypeName;
		for (EOJoin join : myJoins) {
			EOJoin newJoin = join._cloneModelObject();
			relationship.addJoin(newJoin, false);
		}
		_cloneUserInfoInto(relationship);
		return relationship;
	}

	@Override
	public Class<EOEntity> _getModelParentType() {
		return EOEntity.class;
	}

	public EOEntity _getModelParent() {
		return getEntity();
	}

	public void _removeFromModelParent(Set<EOModelVerificationFailure> failures) {
		if (getEntity() != null) {
			getEntity().removeRelationship(this, true);
		}
	}

	public void _addToModelParent(EOEntity modelParent, boolean findUniqueName, Set<EOModelVerificationFailure> failures) throws EOModelException {
		if (findUniqueName) {
			setName(modelParent.findUnusedRelationshipName(getName()));
		}
		modelParent.addRelationship(this);
	}

	public boolean getSqlGenerationCreateProperty() {
		return !isInherited() || getEntity().getSqlGenerationCreateInheritedProperties(); 
	}

	public String toString() {
		return "[EORelationship: name = " + myName + "; destination = " + ((myDestination == null) ? "null" : myDestination.getName()) + "; joins = " + myJoins + "]"; //$NON-NLS-4$ //$NON-NLS-5$
	}
}
