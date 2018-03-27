package org.projectphema.rcp.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class OHDSIConfigurationDialog extends TitleAreaDialog {

    private Text projectURLText;
    private Text loginText;
    private Text passText;
    private Text projectText;
    private Text domainText;

    private String projectURL;
    private String login;
    private String pass;
    private String project;
    private String domain;

    public OHDSIConfigurationDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    public void create() {
        super.create();
        setTitle("i2b2 Configuration");
        setMessage("Fill in the i2b2 connection information", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        createProjectURLText(container);
        createLoginText(container);
        createPassText(container);
        createProjectText(container);
        createDomainText(container);

        return area;
    }

    private void createProjectURLText(Composite container) {
        Label label = new Label(container, SWT.NONE);
        label.setText("Project URL");

        projectURLText = new Text(container, SWT.BORDER);
        projectURLText.setLayoutData(getGridData());
    }

    private void createLoginText(Composite container) {
        Label label = new Label(container, SWT.NONE);
        label.setText("Login Name");
        
        loginText = new Text(container, SWT.BORDER);
        loginText.setLayoutData(getGridData());
    }
    
    private void createPassText(Composite container) {
        Label label = new Label(container, SWT.NONE);
        label.setText("Password");
        
        passText = new Text(container, SWT.PASSWORD | SWT.BORDER);
        passText.setLayoutData(getGridData());
        
        Button button = new Button(container, SWT.PUSH);
        button.setText("Show Password");
        
        Label labelInfo = new Label(container, SWT.NONE);
        labelInfo.setText("?");
  
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                labelInfo.setText(passText.getText());
                labelInfo.pack();
            }
        });
    }
    
    private void createProjectText(Composite container) {
        Label label = new Label(container, SWT.NONE);
        label.setText("Project Name");
        
        projectText = new Text(container, SWT.BORDER);
        projectText.setLayoutData(getGridData());
    }
    
    private void createDomainText(Composite container) {
        Label label = new Label(container, SWT.NONE);
        label.setText("Domain Name");
        
        domainText = new Text(container, SWT.BORDER);
        domainText.setLayoutData(getGridData());
    }



    @Override
    protected boolean isResizable() {
        return true;
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
    	projectURL = projectURLText.getText();
    	login = loginText.getText();
        pass = passText.getText();
        project = projectText.getText();
        domain = domainText.getText();
    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }
    
    public String getProjectManagementUrl() {
    	return projectURL;
	}
	
	public String getLogin()  {
		return login;
	}
	
	public String getPassword()  {
		return pass;
	}
	
	public String getProject()  {
		return project;
	}
	
	public String getDomain()  {
		return domain;
	}
	
	private GridData getGridData()  {
        GridData grid = new GridData();
        grid.grabExcessHorizontalSpace = true;
        grid.horizontalAlignment = GridData.FILL;
        
        return grid;
        
	}

}

