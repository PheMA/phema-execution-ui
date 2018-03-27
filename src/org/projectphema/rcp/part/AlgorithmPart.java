 
package org.projectphema.rcp.part;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents.Item;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

public class AlgorithmPart {
	public StyledText styledText = null;
	
	
	@Inject
	public AlgorithmPart() {
		
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		styledText = new StyledText(parent, SWT.BORDER);
	}
	
	
	@Focus
	public void onFocus() {
		styledText.setFocus();
	}
	

	
	
//	  @Inject
//	  private MApplication application;
//
//	  @PostConstruct
//	  public void init(Composite parent) {
//	    application.getContext().set(Composite.class, this);
//	    
//	    
//	  }
	  
	
//   
//	@PostConstruct
	//  https://www.eclipse.org/forums/index.php/t/627972/
//	void init(IEclipseContext ctx) {
//		ctx.put(AlgorithmPart.class,this);
//	}
//	
//	public void openDialog() {
//		// your dialog code
//		}
	
}