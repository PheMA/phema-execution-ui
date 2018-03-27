package org.projectphema.rcp.models;

import org.eclipse.swt.widgets.Text;

public class I2B2ConnectionInfo {
	private String textName;
	private String textUrl;
	private String textUser;
	private String textPass;
	private String textProject;
	private String textDomain;
	
	
	public I2B2ConnectionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public I2B2ConnectionInfo(String textName,String textUrl,String textUser,String textPass,String textProject,String textDomain) {
		super();
		this.textName = textName;
		this.textUrl = textUrl;
		this.textUser = textUser;
		this.textPass = textPass;
		this.textProject = textProject;
		this.textDomain = textDomain;
	}
	
	public String getI2B2ConnName() {
		return textName;
	}
	public void setI2B2ConnName(String textName) {
		this.textName = textName;
	}
	public String getI2B2ConnUrl() {
		return textUrl;
	}
	public void setI2B2ConnUrl(String textUrl) {
		this.textUrl = textUrl;
	}
	public String getI2B2ConnUser() {
		return textUser;
	}
	public void setI2B2ConnUser(String textUser) {
		this.textUser = textUser;
	}
	public String getI2B2ConnPass() {
		return textPass;
	}
	public void setI2B2ConnPass(String textPass) {
		this.textPass = textPass;
	}
	public String getI2B2ConnProject() {
		return textProject;
	}
	public void setI2B2ConnProject(String textProject) {
		this.textProject = textProject;
	}
	public String getI2B2ConnDomain() {
		return textDomain;
	}
	public void setI2B2ConnDomain(String textDomain) {
		this.textDomain = textDomain;
	}
	
	
}
