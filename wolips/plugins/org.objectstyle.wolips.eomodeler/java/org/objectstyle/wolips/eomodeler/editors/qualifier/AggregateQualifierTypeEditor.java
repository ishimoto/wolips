/**
 * 
 */
package org.objectstyle.wolips.eomodeler.editors.qualifier;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.EOAggregateQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.TBEnterpriseQualifier;
import org.objectstyle.wolips.eomodeler.core.model.qualifier.EOTruePredicate;

public abstract class AggregateQualifierTypeEditor extends AbstractQualifierTypeEditor implements IQualifierEditorListener {
	private List<TBFQualifierEditor> _editors;

	public AggregateQualifierTypeEditor(Composite parent, int style) {
		super(parent, style);
		_editors = new LinkedList<TBFQualifierEditor>();
		GridLayout layout = new GridLayout(1, false);
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginWidth = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);
	}

	public void setQualifier(TBEnterpriseQualifier qualifier) {
		for (TBFQualifierEditor editor : _editors) {
			editor.dispose();
		}
		_editors.clear();

		List<TBEnterpriseQualifier> qualifiers;
		if (qualifier instanceof EOAggregateQualifier) {
			EOAggregateQualifier andQualifier = (EOAggregateQualifier) qualifier;
			qualifiers = andQualifier.getQualifiers();
		} else {
			qualifiers = new LinkedList<TBEnterpriseQualifier>();
			qualifiers.add(qualifier);
		}
		if (qualifiers.size() == 1) {
			qualifiers.add(new EOTruePredicate());
		}
		for (TBEnterpriseQualifier childQualifier : qualifiers) {
			TBFQualifierEditor childEditor = createEditor();
			childEditor.setQualifier(childQualifier);
			_editors.add(childEditor);
		}
	}

	public TBEnterpriseQualifier getQualifier() {
		List<TBEnterpriseQualifier> qualifiers = new LinkedList<TBEnterpriseQualifier>();
		for (TBFQualifierEditor editor : _editors) {
			TBEnterpriseQualifier childQualifier = editor.getQualifier();
			if (childQualifier != null) {
				qualifiers.add(childQualifier);
			}
		}
		TBEnterpriseQualifier qualifier;
		if (qualifiers.isEmpty()) {
			qualifier = null;
		} else if (qualifiers.size() == 1) {
			qualifier = qualifiers.get(0);
		} else {
			EOAggregateQualifier aggregateQualifier = createQualifier();
			aggregateQualifier.getQualifiers().addAll(qualifiers);
			qualifier = aggregateQualifier;
		}
		return qualifier;
	}

	protected TBFQualifierEditor createEditor() {
		TBFQualifierEditor childEditor = new TBFQualifierEditor(this, SWT.NONE);
		childEditor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		childEditor.setQualifierEditorListener(this);
		return childEditor;
	}

	public void qualifierAddedAbove(TBFQualifierEditor editor) {
		int index = _editors.indexOf(editor);
		TBFQualifierEditor newEditor = createEditor();
		_editors.add(index, newEditor);
		newEditor.moveAbove(editor);
		layout(true, true);
		getParent().layout(true, true);
	}

	public void qualifierAddedBelow(TBFQualifierEditor editor) {
		int index = _editors.indexOf(editor);
		TBFQualifierEditor newEditor = createEditor();
		_editors.add(index + 1, newEditor);
		newEditor.moveBelow(editor);
		layout(true, true);
		getParent().layout(true, true);
	}

	public void qualifierRemoved(TBFQualifierEditor editor) {
		_editors.remove(editor);
		editor.dispose();

		if (_editors.size() == 1) {
			IQualifierTypeEditorListener listener = getQualifierTypeEditorListener();
			if (listener != null) {
				listener.qualifierTypeChanged(this);
			}
		}

		layout(true, true);
		getParent().layout(true, true);
	}

	protected abstract EOAggregateQualifier createQualifier();
}