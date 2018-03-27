package org.projectphema.rcp.part;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
 
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
 
public class ConnectionPart {
 
    private Text txtInput;
    private TableViewer tableViewer;
 
    @Inject
    private MDirtyable dirty;
 
    @PostConstruct
    public void createComposite(Composite parent) {
        parent.setLayout(new GridLayout(1, false));
 
        txtInput = new Text(parent, SWT.BORDER);
        txtInput.setMessage("Search");
        txtInput.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                dirty.setDirty(true);
            }
        });
        txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
 
        tableViewer = new TableViewer(parent);
 
        tableViewer.add("phema server");
        tableViewer.add("development");
        tableViewer.add("production");

        tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
    }
 
    @Focus
    public void setFocus() {
    	System.out.println("setFocus");
        tableViewer.getTable().setFocus();
    }
 
    @Persist
    public void save() {
    	System.out.println("save");
        dirty.setDirty(false);
    }
}
