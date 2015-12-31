package com.attendace.tools;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexValidationTools {
	
	public static Pattern FIRST_NAME_PATTERN = Pattern.compile("([A-Za-z]{1,20})((\\s{1}[A-Za-z]{1,20}){0,1})");
	public static Pattern SECOND_NAME_PATTERN = Pattern.compile("([A-Za-z]{1,20})((\\s{1}[A-Za-z]{1,20}){0,1})");
	public static Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
	

}
