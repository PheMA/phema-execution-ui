package org.projectphema.rcp.handler;
//
//import org.eclipse.e4.core.di.annotations.Execute;
//import org.eclipse.swt.widgets.FileDialog;
//import org.eclipse.swt.widgets.Shell;
// 
//public class OpenHandler {
// 
//   @Execute
//   public void execute(Shell shell)
//   {
//       FileDialog dialog = new FileDialog(shell);
//       dialog.open();
//   }
//}

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.impl.PartImpl;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents.Item;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.projectphema.rcp.part.AlgorithmPart;

public class OpenHandler {
	
//    @Inject
//    private MDirtyable dirty;
	@Inject
	private EPartService partService;
//	@Inject
//	private EModelService modelService;
//	@Inject
//	private MApplication application;
	
	@Inject
	EModelService modelService;
	@Inject
	MApplication app;
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, MPart part){
//		if (part==null) {
//			return;
//		}
		
		part = partService.findPart("phemaexecutor.part.algorithm");
	    part.setVisible(true);
	    partService.showPart(part, PartState.VISIBLE);
	    
	    
		AlgorithmPart editor = (AlgorithmPart) part.getObject();		
		FileDialog dialog = new FileDialog(shell);		
		
		String[] filterExt = { "*.XML;*.xml" };
		String[] filterNames = { "XML files" };
		dialog.setFilterExtensions(filterExt);
		dialog.setFilterNames(filterNames);
		
		String fileName = dialog.open();
		if (fileName!= null) {
//			try {
				
				File inputFile = new File(fileName);
				
				
				
//				Object input = part.getTransientData().get("inputKey");
//				
//				editor.styledText.setText(new Scanner(new File(fileName)).next());
//				editor.styledText.setText(new Scanner(new File(fileName)).useDelimiter("\\A").next());
//
//			PartImpl algPart = (PartImpl)modelService.find("phemaexecutor.part.algorithm", app);
//			partService.showPart(algPart, PartState.ACTIVATE);

			
			openNode(inputFile, partService, modelService, app);
			
			
//				MPartStack editorStack = (MPartStack)modelService.find("phemaexecutor.part.algorithm", app);
//
//				editorStack.getChildren().add(part);
//
//				partService.showPart(part, PartState.ACTIVATE);
//			}
//			catch(IOException e) {
//			    MessageDialog.openError(shell, "Error opening file", "File " + fileName + " could not be opened." );
//			}
		}
	}	
	
	
	@Execute
	public void openNode(
			File inputFile, EPartService partService, EModelService modelService, MApplication app) {
		System.out
				.println("OpenTreeNodeHandler - Handler invoked with selected item "
						+ inputFile.getName());
//		String partId="test.rcp.inputpart.dataseteditor";
		//String partId = createEditorPartIdFor(selectedItem);
		
		String partId = "phemaexecutor.part.algorithm.editor";
		MPart editorPart = partService.findPart(partId);
		if (editorPart == null) {
			System.out.println("OpenTreeNodeHandler - No editor found with id=" + partId);
			editorPart = createEditorFor(inputFile);

			MPartStack editorsStack = (MPartStack) modelService.find("sample.part.stack.editors", app);
			editorsStack.getChildren().add(editorPart);
			System.out.println("OpenTreeNodeHandler - Editor with id=" + partId
					+ " created and added to stack");
		} else {
			System.out.println("OpenTreeNodeHandler - Editor with id=" + partId
					+ " already exists");
		}

		partService.activate(editorPart);
		System.out.println("OpenTreeNodeHandler - Editor with id=" + partId
				+ " activated");
	}

	private String createEditorPartIdFor(File inputFile) {
		return "sample.editor." + inputFile.getName();
	}

	private MPart createEditorFor(File inputFile) {
		MPart editorPart = MBasicFactory.INSTANCE.createPart();
		editorPart.setElementId(createEditorPartIdFor(inputFile));
		String shortName = inputFile.getName();
		editorPart.setLabel(shortName);
		editorPart.setTooltip(shortName);
		editorPart.setCloseable(true);
		editorPart
				.setContributionURI("bundleclass://test.rcp/com.itf.tvt.tool.datastore.gui.DatasetEditorPart");
		return editorPart;
	}
	
	
}