package org.projectphema.rcp.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.projectphema.rcp.dialog.I2B2ConfigurationDialog;

public class OHDSIHandler {

	   @Execute
	   public void execute(Shell shell) 
	   {
	      I2B2ConfigurationDialog dialog = new I2B2ConfigurationDialog(shell);
	   
		   dialog.create();
		   
		   if (dialog.open() == Window.OK) {
			    System.out.println("url = " +dialog.getProjectManagementUrl());
			    System.out.println("login = " +dialog.getLogin());
			    System.out.println("pass = " +dialog.getPassword());
			    System.out.println("project = " +dialog.getProject());
			    System.out.println("domain = " +dialog.getDomain());
			}
		   
	   }
}
