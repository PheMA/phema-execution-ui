package org.projectphema.rcp.part;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class I2B2ListPart {

    static List categories;
    private Text txtInput;
    private TableViewer tableViewer;
    private HashMap<String, I2B2ConnectionInfo> conns;
    Composite parent;
    I2B2ConnectionInfo connInfo;
 
    @Inject
    private MDirtyable dirty;
	@Inject
	private EPartService partService;
	@Inject
	private EModelService modelService;
	@Inject
	private MApplication application;
    
	public I2B2ListPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		//System.out.println("\n i2b2 createControls" );
		this.parent = parent;
		
		conns = new HashMap<String, I2B2ConnectionInfo>();
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
           	   	   connInfo = conns.get(selectedItem);
 	               
	               I2B2ConnectionPart.setName(connInfo.getI2B2ConnName());
	               I2B2ConnectionPart.setURL(connInfo.getI2B2ConnUrl());
	               I2B2ConnectionPart.setUser(connInfo.getI2B2ConnUser());
	               I2B2ConnectionPart.setPassword(connInfo.getI2B2ConnPass());
	               I2B2ConnectionPart.setDomain(connInfo.getI2B2ConnDomain());
	               I2B2ConnectionPart.setProject(connInfo.getI2B2ConnProject());

	               
              }
//              final String id = "phemaexecutor.part.i2b2ConnDetails";
//				showPart(e, id);
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
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
		               I2B2ConnectionPart.setName("");
		               I2B2ConnectionPart.setURL("");
		               I2B2ConnectionPart.setUser("");
		               I2B2ConnectionPart.setPassword("");
		               I2B2ConnectionPart.setDomain("");
		               I2B2ConnectionPart.setProject("");
		               
		                categories.deselectAll();
		           }
		        });
				
			Button btnSaveButton = new Button(childGroup, SWT.PUSH);
			btnSaveButton.setText("Save");
			btnSaveButton.addSelectionListener(new SelectionAdapter() {
		           public void widgetSelected(SelectionEvent event) {
		        	   //dirty.setDirty(true);
		        	   String connName = I2B2ConnectionPart.getName();
		               
		               connInfo = new I2B2ConnectionInfo(connName, I2B2ConnectionPart.getURL(), 
		            		   			I2B2ConnectionPart.getUser(), I2B2ConnectionPart.getPassword(), 
		            		   			I2B2ConnectionPart.getDomain(), I2B2ConnectionPart.getProject());

		               putInList(connName, connInfo);
		           }
		        });

			Button btnDeleteButton = new Button(childGroup, SWT.PUSH);
			btnDeleteButton.setText("Delete");
			btnDeleteButton.addSelectionListener(new SelectionAdapter() {
		           public void widgetSelected(SelectionEvent event) {
		        	   //dirty.setDirty(true);
		               String[] selectedItems = categories.getSelection();
		               for (String selectedItem : selectedItems) {
		            	   categories.remove(selectedItem);
		            	   conns.remove(selectedItem);
		               }
		               I2B2ConnectionPart.setName("");
		               I2B2ConnectionPart.setURL("");
		               I2B2ConnectionPart.setUser("");
		               I2B2ConnectionPart.setPassword("");
		               I2B2ConnectionPart.setDomain("");
		               I2B2ConnectionPart.setProject("");
		           }
		        });
			
			Button btnTestButton = new Button(childGroup, SWT.PUSH);
			btnTestButton.setText("Test");
			btnTestButton.addSelectionListener(new SelectionAdapter() {
		           public void widgetSelected(SelectionEvent event) {
		               MessageBox messageBox = new MessageBox(parent.getShell(), SWT.OK | SWT.ICON_ERROR);
		               
		               messageBox.setText("Connection Test");
		               messageBox.setMessage("The connection to I2B2 " +connInfo.getI2B2ConnName() +" was successful.");
		               int buttonID = messageBox.open();
		               switch(buttonID) {
		                 case SWT.OK:
		                   //System.out.println("ok");
		                   break;
		                 case SWT.CANCEL:
		                	 //System.out.println("cancel");
		               }
		           }
		        });
			
			populateList();
     
	}

	private void printConns()  {
		//System.out.println("\n i2b2 Printing connections" );
		
		Set<String> keys = conns.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
		}
		
  	  Collection<I2B2ConnectionInfo> conndata = conns.values();
  	  for (Iterator iterator = conndata.iterator(); iterator.hasNext();) {
			I2B2ConnectionInfo i2b2ConnectionInfo = (I2B2ConnectionInfo) iterator.next();
			
             System.out.println("\t Name: " + i2b2ConnectionInfo.getI2B2ConnName());
             System.out.println("\t url: " + i2b2ConnectionInfo.getI2B2ConnUrl());
             System.out.println("\t user: " + i2b2ConnectionInfo.getI2B2ConnUser());
             System.out.println("\t password: " + i2b2ConnectionInfo.getI2B2ConnPass());
             System.out.println("\t domain: " + i2b2ConnectionInfo.getI2B2ConnDomain());
             System.out.println("\t project: " + i2b2ConnectionInfo.getI2B2ConnProject());
		} 
	}
	
	@PreDestroy
	public void dispose() {
		//System.out.println("\n i2b2 dispose" );
		//printConns();
	}

	@Focus
	public void setFocus() {
		//System.out.println("\n i2b2 setFocus" );
		I2B2ConnectionPart icp = new I2B2ConnectionPart();
		icp.setFocus();
		
		sortList();
		
		MPart part = partService.findPart("phemaexecutor.part.i2b2ConnDetails");
	    part.setVisible(true);
	    partService.showPart(part, PartState.VISIBLE);
		
	}
	
	@Persist
	void save(MPart part)
	{
		//System.out.println("i2b2 list save");
		outputConns();
	}
	
	
	private void outputConns()  {
		//System.out.println("\n i2b2 outputConns" );
		ObjectMapper mapper = new ObjectMapper();
		Collection<I2B2ConnectionInfo> conndata = conns.values();

		try {
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(new File(Utils.getLocation() +Terms.I2B2_FILE), conndata);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void populateList()  {
		//System.out.println("\n i2b2 populateList" );
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			File f = new File(Utils.getLocation() +Terms.I2B2_FILE);
			if(f.exists() && !f.isDirectory()) {
				
				FileInputStream fis = new FileInputStream(Utils.getLocation() +Terms.I2B2_FILE);
				TypeReference<java.util.List<I2B2ConnectionInfo>> typeReference = new TypeReference<java.util.List<I2B2ConnectionInfo>>() {};
				ArrayList<I2B2ConnectionInfo> i2b2ConnectionInfos = objectMapper.readValue(fis, typeReference);

				for (Iterator iterator = i2b2ConnectionInfos.iterator(); iterator.hasNext();) {
					I2B2ConnectionInfo i2b2ConnectionInfo = (I2B2ConnectionInfo) iterator.next();
					String connName = i2b2ConnectionInfo.getI2B2ConnName();
			           conns.put(connName, i2b2ConnectionInfo);
			           if(categories.indexOf(connName) < 0)  {
			        	   categories.add(connName);
			           }
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	
	
	private void sortList()  {
		//System.out.println("\n i2b2 sortList" );
		String[] items = this.categories.getItems();
		java.util.Arrays.sort(items);
		this.categories.setItems(items);
	}
	
	private void putInList(String connName, I2B2ConnectionInfo connInfo)  {
		//System.out.println("\n i2b2 putInList" );
        conns.put(connName, connInfo);
        int index = categories.indexOf(connName);
        if(index < 0)  {
     	   categories.add(connName);
     	   index = categories.indexOf(connName);
        }
        sortList();
        outputConns();
        
        index = categories.indexOf(connName);
        categories.setSelection(index);
	}
	
}
