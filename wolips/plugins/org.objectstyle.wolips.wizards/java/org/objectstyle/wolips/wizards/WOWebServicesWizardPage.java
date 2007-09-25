package org.objectstyle.wolips.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.WizardNewLinkPage;

public class WOWebServicesWizardPage extends WizardNewLinkPage {
	public static final String CLIENT_WEBSERVICES_STRING = "client web services";
	public static final String SERVER_WEBSERVICES_STRING = "server web services";
	
	Button clientWebServiceCheckBox;
	Button serverWebServiceCheckBox;
	
	static boolean clientEnabled = false;
	static boolean serverEnabled = false;
	
	static CheckBoxListener  checkboxListener;
	
	
	public WOWebServicesWizardPage(String pageName, int type) {
		super(pageName, type);
		checkboxListener = new CheckBoxListener();
	}
	
	public boolean getClientSupport () {
		return clientEnabled;
	}
	
	public boolean getServerSupport() {
		return clientEnabled;
	}
	
	public void createControl(Composite parent) {
		Font font = parent.getFont();
		initializeDialogUnits(parent);
		
		// top level group
		Composite topLevel = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		topLevel.setLayout(layout);
		topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		topLevel.setFont(font);

        Group clientGroup = new Group(topLevel, SWT.SHADOW_IN);
        GridLayout clientlayout = new GridLayout();
        clientGroup.setLayout(clientlayout);
        clientGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL));
        clientGroup.setText("Client Support");
        clientGroup.setFont(topLevel.getFont());
        
		clientWebServiceCheckBox = new Button(clientGroup, SWT.CHECK);
		clientWebServiceCheckBox.setText(CLIENT_WEBSERVICES_STRING);
		clientWebServiceCheckBox.addSelectionListener(checkboxListener);

		
        Group serverGroup = new Group(topLevel, SWT.SHADOW_IN);
        GridLayout serverlayout = new GridLayout();
        serverGroup.setLayout(serverlayout);
        serverGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL));
        serverGroup.setText("Server Support");
        serverGroup.setFont(topLevel.getFont());
        
		serverWebServiceCheckBox = new Button(serverGroup, SWT.CHECK);
		serverWebServiceCheckBox.setText(SERVER_WEBSERVICES_STRING);
		serverWebServiceCheckBox.addSelectionListener(checkboxListener);
		
		setErrorMessage(null);
		setMessage(null);
		setControl(topLevel);
	}

	public boolean handleUpdatedSelection(SelectionEvent e) {
		Button s = (Button)e.getSource();
		if (s.equals(clientWebServiceCheckBox)) {
			clientEnabled = s.getSelection();
		} else if (s.equals(serverWebServiceCheckBox)){
			serverEnabled = s.getSelection();
		}  else {
			//do anything?
		}
		return true;
	}

	class CheckBoxListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			handleUpdatedSelection(e);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			handleUpdatedSelection(e);
		}
	}
	
}
