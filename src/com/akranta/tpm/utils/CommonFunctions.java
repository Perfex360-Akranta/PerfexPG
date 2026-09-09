package com.akranta.tpm.utils;

import com.akranta.tpm.controller.UIUtils; 
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CommonFunctions {
   private static final Logger LOG = Logger.getLogger(CommonFunctions.class);
   private DBActionTemplate dbActionTemplate;
   public static final String DATE_TIME_FORMAT_NOW = "dd-MMM-yyyy HH:mm:ss";
   public static final String DATE_FORMAT_NOW = "dd-MMM-yyyy";

   public static String now() {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm",Locale.ENGLISH);
      return sdf.format(cal.getTime());
   }
   
   //related masters
   public static String pg_PG_dateTimeNow() {
	      Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	      return sdf.format(cal.getTime());
	   }

public static String pg_pldateTimeNow() {
	      Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
	      return sdf.format(cal.getTime());
	   }


public static String pg_pggetFormatDateFromDate(String inputDate) {
	    if (inputDate == null) {
	        return null; 
	    }
	    try {

	    	 SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
	}
   
//related masters


   public static  String getFinancialYear(String dates) {
	   if(dates.equals("Jan-1801")||dates.equals("01-Jan-1801")) {
		   dates=getDate();
	   }
	   
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);	   
	  
    
       LocalDate date = LocalDate.parse(dates, formatter);
      
       int year = date.getYear();

       if (date.getMonthValue() >= 4) {
    	   return "Apr-"+String.valueOf(year) ;
           
           
       } else {

           return "Apr-"+String.valueOf(year-1) ; 
       }
   }

   public static Date getDate(String date, String dateFormat) {
      try {
         SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
         java.util.Date dd = null;
         dd = sdf.parse(date);
         CommonMessage.debugMsg(dd);
         Date sqlDate = new Date(dd.getTime());
         return sqlDate;
      } catch (ParseException var5) {
         return null;
      }
   }

   public static String getMonthYearMMMYYYY(String date, String dateFormat) {
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
      SimpleDateFormat monthYear = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);

      try {
         java.util.Date dd = sdf.parse(date);
         CommonMessage.debugMsg(" date formt dd " + dd);
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         String formatDate = monthYear.format(cal.getTime());
         formatDate = "01" + formatDate.substring(formatDate.indexOf("-"));
         return formatDate;
      } catch (ParseException var7) {
         return null;
      }
   }

   public static String dateTimeNow() {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
      return sdf.format(cal.getTime());
   }
   public static String pg_dateTimeNow() {
	      Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	      return sdf.format(cal.getTime());
	   }

   public static String getDate() {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
      return sdf.format(cal.getTime());
   }
   
   
   public static String pg_getDate() {
	      Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	      return sdf.format(cal.getTime());
	   }
   public static String pg_getDate(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
//	    final String oldFormat = "dd-MMM-yyyy";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//	    return sdf.format(inputDate);
	}
   public static String pg_getDateTimeFromDate(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
//	    final String oldFormat = "dd-MMM-yyyy";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//	    return sdf.format(inputDate);
	}
   
   public static String pg_getFormatDateFromDate(String inputDate) {
	    if (inputDate == null) {
	        return null; 
	    }
	    try {

	        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
	}
   public static String pg_getDateFromTimeStamp(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
//	    final String oldFormat = "dd-MMM-yyyy";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//	    return sdf.format(inputDate);
	}
   
   public static String pg_getDateTimeFromTimeStamp(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
//	    final String oldFormat = "dd-MMM-yyyy";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//	    return sdf.format(inputDate);
	}
   
   public static String pg_getDateTimeFromPGTimeStamp(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
//	    final String oldFormat = "dd-MMM-yyyy";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//	    return sdf.format(inputDate);
	}
   
   public static String pg_getPGDateTimeFromDateTime(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
	}
   public static String pg_getPGTimeStampFromDateTime(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
	}
   
   public static String pg_getDateFromPGTimeStamp(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
	}
   
   public static String pg_getDateTimeFromPGTimeStamp1(String inputDate) {
	    if (inputDate == null) {
	        return null; // or throw IllegalArgumentException("Date cannot be null");
	    }
	    try {
	        // Input format like: 18-Sep-2025
	        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
	        java.util.Date date = oldFormat.parse(inputDate);

	        // Desired output format: 2025-09-18
	        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
	        return newFormat.format(date);

	    } catch (Exception e) {
	        throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
	    }
//	    final String oldFormat = "dd-MMM-yyyy";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//	    return sdf.format(inputDate);
	}
   
//   public static java.sql.Timestamp pg_convertoSqlTimeStamp(String dateStr) {
//	    if (dateStr == null) return null;
//	    String s = dateStr.trim();
//
//	    final String oldFormat = "dd-MMM-yyyy HH:mm:ss"; // e.g., 16-Sep-2025 17:42:55
//	    java.text.SimpleDateFormat sdf1 =
//	            new java.text.SimpleDateFormat(oldFormat, java.util.Locale.ENGLISH);
//	    sdf1.setLenient(false);
//
//	    try {
//	        java.util.Date d = sdf1.parse(s);
//	        return new java.sql.Timestamp(d.getTime());
//	    } catch (java.text.ParseException e) {
//	        e.printStackTrace(); // keep your existing behavior
//	        return null;
//	    }
//	}

   public static boolean isValidKeyId(String keyId) {
      return keyId != null && !keyId.isEmpty() && !keyId.equals("{}") && !keyId.equals("-") && !keyId.toLowerCase().equals("null");
   }

   public static boolean isFileExists(String fileNamePath) {
      File file = new File(fileNamePath);
      return file.exists();
   }

   public static String getFirstDateofMonth(Integer diffMonth) {
      String[] monthArray = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
      Calendar cal = Calendar.getInstance();
      StringBuffer firstDateStr = new StringBuffer("");
      cal.setTime(cal.getTime());
      cal.add(2, diffMonth);
      firstDateStr.append("01-");
      firstDateStr.append(monthArray[cal.get(2)]);
      firstDateStr.append("-");
      firstDateStr.append(cal.get(1));
      return firstDateStr.toString();
   }

   public static String getTodayNextDate() {
      Calendar cal = Calendar.getInstance();
      cal.setTime(cal.getTime());
      cal.add(5, 1);
      return convertToDDMMMYYYY(cal);
   }

   public static String addMonth(String date, int amount) {
      SimpleDateFormat monthYear = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

      try {
         java.util.Date dd = monthYear.parse(date);
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         cal.add(2, amount);
         return convertToDDMMMYYYY(cal);
      } catch (Exception var5) {
         return null;
      }
   }

   public static String addDay(String date, int amount) {
      SimpleDateFormat monthYear = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);

      try {
         java.util.Date dd = monthYear.parse(date);
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         cal.add(5, amount);
         return convertToDDMMMYYYY(cal);
      } catch (Exception var5) {
         return null;
      }
   }

   private static String convertToDDMMMYYYY(Calendar cal) {
      String[] monthArray = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
      StringBuffer nextDateStr = new StringBuffer();
      nextDateStr.append(cal.get(5));
      nextDateStr.append("-");
      nextDateStr.append(monthArray[cal.get(2)]);
      nextDateStr.append("-");
      nextDateStr.append(cal.get(1));
      return nextDateStr.toString();
   }

   public static String getLastDayOfMonth(String date) {
      SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);

      try {
         java.util.Date dd = format.parse(date);
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         int maxDay = cal.getActualMaximum(5);
         String tmp = date.substring(date.indexOf("-"));
         return maxDay + tmp;
      } catch (Exception var6) {
         return null;
      }
   }

   public static String getFirstDateofMonth1(Integer diffMonth) {
      String[] monthArray = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
      Calendar cal = Calendar.getInstance();
      StringBuffer firstDateStr = new StringBuffer("");
      cal.setTime(cal.getTime());
      cal.add(2, diffMonth);
      firstDateStr.append(monthArray[cal.get(0)]);
      firstDateStr.append("-");
      firstDateStr.append(cal.get(1));
      return firstDateStr.toString();
   }

   public static String getTodayNextDate1() {
      String[] monthArray = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
      Calendar cal = Calendar.getInstance();
      StringBuffer nextDateStr = new StringBuffer("");
      cal.setTime(cal.getTime());
      cal.add(2, 1);
      nextDateStr.append(cal.get(2));
      nextDateStr.append("-");
      nextDateStr.append(monthArray[cal.get(0)]);
      nextDateStr.append("-");
      nextDateStr.append(cal.get(1));
      return nextDateStr.toString();
   }

   public static String getCurrentYear() {
      String year = null;
      Calendar cal = Calendar.getInstance();
      year = "" + cal.get(1);
      return year;
   }

   public static String getCurrentMonth() {
      String monthStr = null;
      SimpleDateFormat month = new SimpleDateFormat("MMM",Locale.ENGLISH);
      monthStr = month.format(new java.util.Date());
      debugMsg(" monthStr " + monthStr);
      return monthStr;
   }

   public static String addYear(String date, int amount) {
      String year = null;
      SimpleDateFormat monthYear = new SimpleDateFormat("yyyy",Locale.ENGLISH);

      try {
         java.util.Date dd = monthYear.parse(date);
         Calendar cal = Calendar.getInstance();
         cal.setTime(dd);
         cal.add(1, amount);
         year = "" + cal.get(1);
         return year;
      } catch (Exception var6) {
         return null;
      }
   }

   public static int getDateDiff(String str1, String str2) throws ParseException {
      
     // SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
     
	   if (!str1.matches(".*\\d{2}:\\d{2}:\\d{2}$")) {
	    	  str1 = str1 + " 00:00:00";
	      }
	   if (!str2.matches(".*\\d{2}:\\d{2}:\\d{2}$")) {
    	  str2 = str2 + " 00:00:00";
      }
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm", Locale.ENGLISH);

      CommonMessage.debugMsg(" CommonMessage.debugMsg str1 "+str1);
      CommonMessage.debugMsg(" CommonMessage.debugMsg str2 "+str2);
      java.util.Date date1 = simpleDateFormat.parse(str1);
      java.util.Date date2 = simpleDateFormat.parse(str2);
     
      int difInDays = (int)((date2.getTime() - date1.getTime()) / 86400000L);
      return difInDays;
   }

   public static void debugMsg(Object msg) {
      String logReq = DBActionTemplate.getLogRequire();
      if (UIUtils.isValidKeyId(logReq) && "Y".equals(logReq)) {
    	  System.out.println(msg);
      }

   }
   
   public static void debugMsgUrl(Object msg) {
	      String logReq = DBActionTemplate.getUrlLogRequire();
	      if (UIUtils.isValidKeyId(logReq) && "Y".equals(logReq)) {
	    	  System.out.println(msg);
	      }

	   }
   
   public static void debugMsgCon(Object msg) {
	      String logReq = DBActionTemplate.getConLogRequire();
	      if (UIUtils.isValidKeyId(logReq) && "Y".equals(logReq)) {
	    	  System.out.println(msg);
	      }

	   }

   public static String getLoginLocaton(HttpServletRequest request) {
      HttpSession httpSession = request.getSession(false);
      String loginLocationId = (String)httpSession.getAttribute("loginLocnId");
      return loginLocationId;
   }

   public static String getLoginFlid(HttpServletRequest request) {
      HttpSession httpSession = request.getSession(false);
      String loginFlid = (String)httpSession.getAttribute("loginFlid");
      return loginFlid;
   }

   public static String getLoginLevel(HttpServletRequest request) {
      HttpSession httpSession = request.getSession(false);
      String loginLevel = (String)httpSession.getAttribute("loginRoleLevelNo");
      return loginLevel;
   }

   public static String getLoginElementId(HttpServletRequest request) {
      HttpSession httpSession = request.getSession(false);
      String loginElementid = (String)httpSession.getAttribute("loginElementid");
      return loginElementid;
   }

   public static long getMonthDiff(String str1, String str2) {
      long diff = 0L;
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);

      try {
         java.util.Date date1 = simpleDateFormat.parse(str1);
         java.util.Date date2 = simpleDateFormat.parse(str2);
         Calendar calfrom = Calendar.getInstance();
         calfrom.setTime(date1);
         Calendar calTill = Calendar.getInstance();
         calTill.setTime(date2);
         diff = calTill.getTimeInMillis() - calfrom.getTimeInMillis();
      } catch (ParseException var9) {
         var9.printStackTrace();
      }

      return diff;
   }

   public static String getDateWithFormat(String format) {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.ENGLISH);
      return sdf.format(cal.getTime());
   }
}
