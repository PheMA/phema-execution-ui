package org.projectphema.rcp.part;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.projectphema.rcp.models.I2B2ConnectionInfo;
import org.projectphema.rcp.models.OHDSIConnectionInfo;
import org.projectphema.rcp.util.Terms;
import org.projectphema.rcp.util.Utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class OHDSIListPart {

    static List categories;
    private Text txtInput;
    private TableViewer tableViewer;
    private HashMap<String, OHDSIConnectionInfo> conns;
 
    @Inject
    private MDirtyable dirty;
	@Inject
	private EPartService partService;
	@Inject
	private EModelService modelService;
	@Inject
	private MApplication application;
    
	public OHDSIListPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {

		conns = new HashMap<String, OHDSIConnectionInfo>();
		GridData data = new GridData(GridData.FILL, GridData.FILL, true, true);
		
	       categories = new List(parent, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);

	       GridData gridData = new GridData(GridData.FILL_BOTH);
//	       gridData.horizontalAlignment = GridData.FILL;
//	       gridData.grabExcessHorizontalSpace = true;
//	       gridData.verticalAlignment = GridData.FILL;
//	       gridData.grabExcessVerticalSpace = true;
	       categories.setLayoutData(gridData);
	       categories.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
  	    	  String[] selectedItems = categories.getSelection();
              for (String selectedItem : selectedItems) {
            	  
            	  //printConns();
           	   		OHDSIConnectionInfo connInfo = conns.get(selectedItem);
 	               
	               OHDSIConnectionPart.setName(connInfo.getOHDSIConnName());
	               OHDSIConnectionPart.setURL(connInfo.getOHDSIConnUrl());
	               OHDSIConnectionPart.setUser(connInfo.getOHDSIConnUser());
	               OHDSIConnectionPart.setPassword(connInfo.getOHDSIConnPass());
	               OHDSIConnectionPart.setDomain(connInfo.getOHDSIConnDomain());
	               OHDSIConnectionPart.setProject(connInfo.getOHDSIConnProject());

              }
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
//				System.out.println("widget selected");
				
			}
	       });

	       
	       Group childGroup = new Group(parent, SWT.NONE);
	       GridLayout layout = new GridLayout();
	       layout.numColumns = 4;
	       childGroup.setLayout(layout);
	       GridData childdata = new GridData(GridData.FILL_BOTH);
	       data.horizontalSpan = 2;
	       childGroup.setLayoutData(childdata);
	       
		       
		    Button btnNewButton = new Button(childGroup, SWT.PUSH);
			btnNewButton.setText("New");
			btnNewButton.addSelectionListener(new SelectionAdapter() {
		           public void widgetSelected(SelectionEvent event) {
		               OHDSIConnectionPart.setName("");
		               OHDSIConnectionPart.setURL("");
		               OHDSIConnectionPart.setUser("");
		               OHDSIConnectionPart.setPassword("");
		               OHDSIConnectionPart.setDomain("");
		               OHDSIConnectionPart.setProject("");
		               
		                categories.deselectAll();
		           }
		        });
				
			Button btnSaveButton = new Button(childGroup, SWT.PUSH);
			btnSaveButton.setText("Save");
			btnSaveButton.addSelectionListener(new SelectionAdapter() {
		           public void widgetSelected(SelectionEvent event) {
		        	   String connName = OHDSIConnectionPart.getName();
		               
		               OHDSIConnectionInfo connInfo = new OHDSIConnectionInfo(connName, OHDSIConnectionPart.getURL(), 
		            		   			OHDSIConnectionPart.getUser(), OHDSIConnectionPart.getPassword(), 
		            		   			OHDSIConnectionPart.getDomain(), OHDSIConnectionPart.getProject());

		               putInList(connName, connInfo);
		           }
		        });

			Button btnDeleteButton = new Button(childGroup, SWT.PUSH);
			btnDeleteButton.setText("Delete");
			btnDeleteButton.addSelectionListener(new SelectionAdapter() {
		           public void widgetSelected(SelectionEvent event) {
		               String[] selectedItems = categories.getSelection();
		               for (String selectedItem : selectedItems) {
		            	   categories.remove(selectedItem);
		            	   conns.remove(selectedItem);
		               }
		               OHDSIConnectionPart.setName("");
		               OHDSIConnectionPart.setURL("");
		               OHDSIConnectionPart.setUser("");
		               OHDSIConnectionPart.setPassword("");
		               OHDSIConnectionPart.setDomain("");
		               OHDSIConnectionPart.setProject("");
		           }
		        });
			
			Button btnTestButton = new Button(childGroup, SWT.PUSH);
			btnTestButton.setText("Test");
			btnTestButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MessageBox messageBox = new MessageBox(parent.getShell(), SWT.OK | SWT.ICON_ERROR);
		               
		               messageBox.setText("Connection Test");
		               messageBox.setMessage("The connection to OHDSI was successful.");
		               int buttonID = messageBox.open();
		               switch(buttonID) {
		                 case SWT.OK:
		                   //System.out.println("ok");
		                   break;
		                 case SWT.CANCEL:
		                	// System.out.println("cancel");
		               }
		           }
		        });
			
			populateList();
	        
	}

	private void printConns()  {
		Set<String> keys = conns.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();
		}
		
		Collection<OHDSIConnectionInfo> conndata = conns.values();
  	  	for (Iterator<OHDSIConnectionInfo> iterator = conndata.iterator(); iterator.hasNext();) {
			OHDSIConnectionInfo i2b2ConnectionInfo = iterator.next();
			
			System.out.println("\t Name: " + i2b2ConnectionInfo.getOHDSIConnName());
			System.out.println("\t url: " + i2b2ConnectionInfo.getOHDSIConnUrl());
			System.out.println("\t user: " + i2b2ConnectionInfo.getOHDSIConnUser());
			System.out.println("\t password: " + i2b2ConnectionInfo.getOHDSIConnPass());
			System.out.println("\t domain: " + i2b2ConnectionInfo.getOHDSIConnDomain());
			System.out.println("\t project: " + i2b2ConnectionInfo.getOHDSIConnProject());
		} 
	}
	
	private void outputConns()  {
		ObjectMapper mapper = new ObjectMapper();
		Collection<OHDSIConnectionInfo> conndata = conns.values();
		
		try {
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(new File(Utils.getLocation() +Terms.OHDSI_FILE), conndata);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void populateList()  {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			File f = new File(Utils.getLocation() +Terms.OHDSI_FILE);
			if(f.exists() && !f.isDirectory()) {
				
				FileInputStream fis = new FileInputStream(Utils.getLocation() +Terms.OHDSI_FILE);
				TypeReference<java.util.List<OHDSIConnectionInfo>> typeReference = new TypeReference<java.util.List<OHDSIConnectionInfo>>() {};
				ArrayList<OHDSIConnectionInfo> ohdsiConnectionInfos = objectMapper.readValue(fis, typeReference);

				for (Iterator iterator = ohdsiConnectionInfos.iterator(); iterator.hasNext();) {
					OHDSIConnectionInfo ohdsiConnectionInfo = (OHDSIConnectionInfo) iterator.next();
					String connName = ohdsiConnectionInfo.getOHDSIConnName();
			           conns.put(connName, ohdsiConnectionInfo);
			           if(categories.indexOf(connName) < 0)  {
			        	   categories.add(connName);
			           }
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	
	@PreDestroy
	public void dispose() {
		//System.out.println("dispose");
		 outputConns() ;
	}

	@Focus
	public void setFocus() {
		sortList();        
		
		MPart part = partService.findPart("phemaexecutor.part.ohdsiConnDetails");
	    part.setVisible(true);
	    partService.showPart(part, PartState.VISIBLE);
	}
	
	private void sortList()  {
		String[] items = this.categories.getItems();
		java.util.Arrays.sort(items);
		this.categories.setItems(items);
	}

	private void putInList(String connName, OHDSIConnectionInfo connInfo)  {
        conns.put(connName, connInfo);
        
        //make sure it is not already in the list
        int index = categories.indexOf(connName);
        if(index < 0)  {
     	   categories.add(connName);
     	   
        }
        sortList();
        outputConns();
        
        index = categories.indexOf(connName);
        categories.setSelection(index);
	}
}
