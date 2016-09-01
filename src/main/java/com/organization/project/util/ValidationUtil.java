package com.organization.project.util;

public class ValidationUtil {

	/**
	 * Method isExist to check value of object
	 * 
	 * @param Object
	 *            object
	 * @return
	 */
	public static Boolean isExist(Object object) {
		if (object != null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static Boolean isExistValue(Object object) {
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}
}
