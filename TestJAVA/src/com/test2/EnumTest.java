package com.test2;

public class EnumTest {

	public static void main(String[] args) {
	//	System.out.println(AuthCode.values());
          if(1 == AuthCode.LOCKED_BY_ADMIN.getAuthCode())
        	  System.out.println("@@@@");
		
	}

}

enum AuthCode{
	NOT_IN_MES(0),
	LOCKED_BY_ADMIN(1),
	FIRST_TIME_LOGIN(2),
	TYPED_WRONG_PASSWORD_5_TIMES(3),
	NOT_LOGGED_IN_FROM_3_MONTHS(4),
	PASSWORD_NOT_CHANGED_FOR_3_MONTHS(5),
	AUTO_GENERATED_PASSWORD(6),
	VALID_USER(7);
	private int authCode;
	 AuthCode(int authCode){
		this.authCode=authCode;
	}
	 
	  public int getAuthCode() {
	        return this.authCode;
	    }
}