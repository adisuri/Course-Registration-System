package com.flipkart.constants;


public enum RolesConstant {
	
	STUDENT,ADMIN,PROFESSOR,NONE;
	
	public static RolesConstant stringToName(String role){
		
	
		switch(role)
		{	
			case "STUDENT":
				return RolesConstant.STUDENT;
			case "PROFESSOR":
				return RolesConstant.PROFESSOR;
			case "ADMIN":
				return RolesConstant.ADMIN;
			default:
				return RolesConstant.NONE;
		}
	}
}
