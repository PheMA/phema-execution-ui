package org.projectphema.rcp.event;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class OpenDocumentEventProcessor implements Listener {
	private ArrayList<String> filesToOpen = new ArrayList<String>(1);
	
	public void handleEvent(Event event) {
		if (event.text != null)
			filesToOpen.add(event.text);
	}

	public void openFiles() {
		if (filesToOpen.isEmpty())
			return;

		String[] filePaths = filesToOpen.toArray(
			new String[filesToOpen.size()]);
		filesToOpen.clear();
		
		for (String path : filePaths) {
			// open the file path
		}
	}
}