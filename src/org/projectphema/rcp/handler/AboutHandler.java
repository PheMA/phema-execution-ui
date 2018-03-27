package org.projectphema.rcp.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.projectphema.rcp.dialog.AboutDialog;
 
public class AboutHandler {
    
   @Execute
   public void execute(Shell shell) 
   {
      // MessageDialog.openInformation(shell, "About", "Phema Executor");
       
//    // customized MessageDialog with configured buttons
//       MessageDialog dialog = new MessageDialog(shell, "My Title", null,
//           "This is the PhEMA Executor", MessageDialog.INFORMATION, new String[] { "First",
//           "Second", "Third" }, 0);
//       int result = dialog.open();
//       System.out.println(result);
	   
	   AboutDialog dialog = new AboutDialog(shell);
	   
	   dialog.create();
	   
	   dialog.open();
	   
	   
   }
}