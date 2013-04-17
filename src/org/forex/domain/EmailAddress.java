package org.forex.domain;

public class EmailAddress {
	
	private String id;
	private String address;
	private String password;
	private String smtpServer;
	private String smtpPort;
	private Boolean isSSL;
	private Boolean isTLS;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public Boolean getIsSSL() {
		return isSSL;
	}
	public void setIsSSL(Boolean isSSL) {
		this.isSSL = isSSL;
	}
	public Boolean getIsTLS() {
		return isTLS;
	}
	public void setIsTLS(Boolean isTLS) {
		this.isTLS = isTLS;
	}
	
	
}
