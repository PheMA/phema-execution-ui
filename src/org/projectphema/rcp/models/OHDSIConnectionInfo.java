package org.projectphema.rcp.models;

import org.eclipse.swt.widgets.Text;

public class OHDSIConnectionInfo {
	private String textName;
	private String textUrl;
	private String textUser;
	private String textPass;
	private String textProject;
	private String textDomain;
	
	
	public OHDSIConnectionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OHDSIConnectionInfo(String textName,String textUrl,String textUser,String textPass,String textProject,String textDomain) {
		super();
		this.textName = textName;
		this.textUrl = textUrl;
		this.textUser = textUser;
		this.textPass = textPass;
		this.textProject = textProject;
		this.textDomain = textDomain;
	}
	
	public String getOHDSIConnName() {
		return textName;
	}
	public void setOHDSIConnName(String textName) {
		this.textName = textName;
	}
	public String getOHDSIConnUrl() {
		return textUrl;
	}
	public void setOHDSIConnUrl(String textUrl) {
		this.textUrl = textUrl;
	}
	public String getOHDSIConnUser() {
		return textUser;
	}
	public void setOHDSIConnUser(String textUser) {
		this.textUser = textUser;
	}
	public String getOHDSIConnPass() {
		return textPass;
	}
	public void setOHDSIConnPass(String textPass) {
		this.textPass = textPass;
	}
	public String getOHDSIConnProject() {
		return textProject;
	}
	public void setOHDSIConnProject(String textProject) {
		this.textProject = textProject;
	}
	public String getOHDSIConnDomain() {
		return textDomain;
	}
	public void setOHDSIConnDomain(String textDomain) {
		this.textDomain = textDomain;
	}
	
	
}
