package edu.asu.ss2015.group4.model;

public class UserRequest {

	private String requesterName;
	private String requestByUserName;
	private String requestType;
	private String approverName;
	private String approvedByUserName;

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getRequestByUserName() {
		return requestByUserName;
	}

	public void setRequestByUserName(String requestByUserName) {
		this.requestByUserName = requestByUserName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getApprovedByUserName() {
		return approvedByUserName;
	}

	public void setApprovedByUserName(String approvedByUserName) {
		this.approvedByUserName = approvedByUserName;
	}

}
