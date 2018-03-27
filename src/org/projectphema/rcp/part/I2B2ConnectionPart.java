package org.projectphema.rcp.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.projectphema.rcp.composite.ButtonComposite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class I2B2ConnectionPart {
	private static Text textName;
	private static Text textUrl;
	private static Text textUser;
	private static Text textPass;
	private static Text textProject;
	private static Text textDomain;

	public I2B2ConnectionPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {

       Group connectionInfo = new Group(parent, SWT.NULL);
       //ownerInfo.setText("Owner Info");
       GridLayout gridLayout = new GridLayout();
       gridLayout.numColumns = 2;
       connectionInfo.setLayout(gridLayout);
       GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
       gridData.horizontalSpan = 2;
       connectionInfo.setLayoutData(gridData);
       
		new Label(connectionInfo, SWT.NONE).setText("Name:");
		textName = new Text(connectionInfo, SWT.SINGLE | SWT.BORDER);
		textName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(connectionInfo, SWT.NONE).setText("URL:");
		textUrl = new Text(connectionInfo, SWT.SINGLE | SWT.BORDER);
		textUrl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(connectionInfo, SWT.NONE).setText("User Name:");
		textUser = new Text(connectionInfo, SWT.SINGLE | SWT.BORDER);
		textUser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(connectionInfo, SWT.NONE).setText("Password");
		textPass = new Text(connectionInfo, SWT.SINGLE | SWT.BORDER);
		textPass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(connectionInfo, SWT.NONE).setText("Project");
		textProject = new Text(connectionInfo, SWT.SINGLE | SWT.BORDER);
		textProject.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new Label(connectionInfo, SWT.NONE).setText("Domain");
		textDomain = new Text(connectionInfo, SWT.SINGLE | SWT.BORDER);
		textDomain.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@PreDestroy
	public void dispose() {
		//System.out.println("dispose");
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
		//System.out.println("setFocus");
	}

	public static String getName()  {
		return textName.getText();
	}
	
	public static String getURL()  {
		return textUrl.getText();
	}
	
	public static String getUser()  {
		return textUser.getText();
	}
	
	public static String getPassword()  {
		return textPass.getText();
	}
	
	public static String getProject()  {
		return textProject.getText();
	}
	
	public static String getDomain()  {
		return textDomain.getText();
	}

	public static void setName(String textName) {
		I2B2ConnectionPart.textName.setText(textName);
	}

	public static void setURL(String textUrl) {
		I2B2ConnectionPart.textUrl.setText(textUrl);
	}

	public static void setUser(String textUser) {
		I2B2ConnectionPart.textUser.setText(textUser);
	}

	public static void setPassword(String textPass) {
		I2B2ConnectionPart.textPass.setText(textPass);
	}

	public static void setProject(String textProject) {
		I2B2ConnectionPart.textProject.setText(textProject);
	}

	public static void setDomain(String textDomain) {
		I2B2ConnectionPart.textDomain.setText(textDomain);
	}

	
}
