package org.projectphema.rcp.util;

import java.io.File;

public class Utils {

	private static String location;
	
	public static String getLocation()  {
		if(location == null)  {
			location = System.getProperty("user.dir") + File.separator;
			System.out.println(location);
			//location = System.getProperty("user.dir") + File.separator + "prefs" + File.separator;
		}
		
		return location;
	}
}
