package com.test;

public class AssignSrApprovedBean {

/*	  <ActionType>0</ActionType>
      <Activity>9</Activity>
      <DeptName>MES_G (MES최적화)</DeptName>
      <Duty>Agent</Duty>
      <Opinion />
      <Sequence>8</Sequence>
      <UserID>I140513095754C406428</UserID>
      <UserName>이현주</UserName>
      <Delegated>0</Delegated>
      <Approved>20150521052444</Approved>
      <Arbitrary />
      <Arirved>20150521052444</Arirved>
      <BodyModify>-1</BodyModify>
      <DutyCode>JJKK</DutyCode>
      <MailAddress>hyunju337.lee@samsung.com</MailAddress>
      <RouteModify>-1</RouteModify>
      
      
      
      <ActionType>0</ActionType>
      <Activity>9</Activity>
      <UserName>이현주</UserName>
      <Approved>20150521052444</Approved>
  */
	
	String mApproveStatus;
	String mSrApprovedType;
	String mSrApprodeName;
	String mSrApprovedDateTime;

	public AssignSrApprovedBean(){
	}

	public AssignSrApprovedBean(String mApproveStatus, String mSrApprovedType,
			String mSrApprodeName, String mSrApprovedDateTime) {
		super();
		this.mApproveStatus = mApproveStatus;
		this.mSrApprovedType = mSrApprovedType;
		this.mSrApprodeName = mSrApprodeName;
		this.mSrApprovedDateTime = mSrApprovedDateTime;
	}

	public String getmApproveStatus() {
		return mApproveStatus;
	}

	public void setmApproveStatus(String mApproveStatus) {
		this.mApproveStatus = mApproveStatus;
	}

	public String getmSrApprovedType() {
		return mSrApprovedType;
	}

	public void setmSrApprovedType(String mSrApprovedType) {
		this.mSrApprovedType = mSrApprovedType;
	}

	public String getmSrApprodeName() {
		return mSrApprodeName;
	}

	public void setmSrApprodeName(String mSrApprodeName) {
		this.mSrApprodeName = mSrApprodeName;
	}

	public String getmSrApprovedDateTime() {
		return mSrApprovedDateTime;
	}

	public void setmSrApprovedDateTime(String mSrApprovedDateTime) {
		this.mSrApprovedDateTime = mSrApprovedDateTime;
	}

}
