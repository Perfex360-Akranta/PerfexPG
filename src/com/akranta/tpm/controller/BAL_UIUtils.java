package com.akranta.tpm.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import org.json.simple.JSONObject;
import lotus.domino.NotesException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.jdbc.pool.OracleDataSource;

import org.apache.commons.fileupload2.core.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload2.core.FileUploadException;
//import org.apache.commons.fileupload2.core.RequestContext;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.apache.commons.fileupload2.core.FileItemFactory;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_FunctLocFieldNameBean;
import com.akranta.tpm.bean.BAL_FunctLocHierarchyIdentBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.HtmlElementBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridEditOptions;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlTeamDoucmentLink;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.MenuTree;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.NotesMailClient;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class BAL_UIUtils {
	public static final String TPM_TEMPIMG_DIR = "tmp/images/"; 
	private static final String[] DEFAULT_BROWSERS = { "MSIE 8.0"};
	public static Date GetDateNow()
	{
		Calendar currentDate = Calendar.getInstance();
		//SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		return currentDate.getTime();
	}

	public static String convertTime(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
	    return format.format(date).toString();
	}

	
	public static java.util.Date convertToUtilDate(String date, String dateFormat)
	{
		//The format of the input date string
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		System.out.println("Date format " + dateFormat);
		
		java.util.Date dd = null;
		try {
			dd = sdf.parse(date);
			System.out.println("utildate " + dd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return dd;
	}
	
	public static String convertToDisplayFormat(String sqlDate,String dateFormat)
	{
		String monthArray[ ] = { "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		//String monthArray[ ] = { "01","02","03","04","05","06","07","08","09","10","11","12"};
		StringBuffer oracleDate = new StringBuffer(""); 
		Calendar cal=Calendar.getInstance();
	    
		Date  date = convertToUtilDate(sqlDate,dateFormat);
		cal.setTime( date);
		
		oracleDate.append(cal.get(Calendar.DAY_OF_MONTH ));
		oracleDate.append("-");
		oracleDate.append(monthArray[cal.get(Calendar.MONTH  )] );
		oracleDate.append("-");
		oracleDate.append( cal.get(Calendar.YEAR) );
	
	    return oracleDate.toString();
	 }
	
	public static JSONObject convertToJqGridTableObject(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, long totalRecords)
	{
		return convertToJqGridTableObject(dataArrayList,request,rowStart,colStart, 0, totalRecords);
	}
	public static JSONObject convertToJqGridTableObject(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, int colSub, long totalRecords){
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		//if( page == 1)
		tableDataObject.put("records", totalRecords - rowStart); //total records
		
		JSONArray rowArr = new JSONArray(); 
       
    	int rowId = rows * (page-1);
		int slno = 0;
        for( String [] row : dataArrayList)
		{
        	if( slno++ >= rowStart )
        	{	
	    	    JSONObject rowObj =new JSONObject();
	    	    	
	    	    rowObj.put("id",rowId -rowStart +1);
	            
	            JSONArray cell=new JSONArray();
	            for( int i = colStart ;i < (row.length - colSub); i++)
	            {	 
	            	cell.put( ( row[i] != null ?row[i].isEmpty() ?" ":  row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
	            }	
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
        	}
        	rowId++;
       }

        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}
	
	public static JSONObject convertToJqGridTableObject(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart)
	{
		return convertToJqGridTableObject(dataArrayList, request, rowStart, colStart,  dataArrayList.size());
	}


	public static JSONObject getColumnType(String [][] colNames)
	{
		JSONObject tableModel = new JSONObject();
		JSONArray headerNames = new JSONArray();
		
		JSONArray colModelArr = new JSONArray();
		for(int i=0;i<colNames.length;i++)
		{
			  headerNames.put(colNames[i][0]);
		
			  JSONObject colModel = new JSONObject();
			  
			  colModel.put("name",colNames[i][1]);
			  colModel.put("index",colNames[i][1]);
			  colModel.put("editable","false");	
			  colModel.put("width",Integer.parseInt(colNames[i][2]));
			  
			  colModel.put("hidden",Boolean.parseBoolean(colNames[i][3]));
			  
			  colModelArr.put(colModel);
			  //System.out.println(" j " + i);
		}
		tableModel.put("colNames",headerNames);
		tableModel.put("colModel",colModelArr);
		//System.out.println("tableModel = " + tableModel);
		return tableModel;
		 
	}
	
	public static JSONObject getGroupByColumnModel(String [][] colNames)
	{
		JSONObject tableModel = new JSONObject();
		JSONArray headerNames = new JSONArray();
		
		JSONArray colModelArr = new JSONArray();
		for(int i=0;i<colNames.length;i++)
		{
			  headerNames.put(colNames[i][0]);
		
			  JSONObject colModel = new JSONObject();
			  
			  colModel.put("name",colNames[i][1]);
			  colModel.put("index",colNames[i][1]);
			  colModel.put("editable","false");	
			  colModel.put("width",Integer.parseInt(colNames[i][2]));
			  
			  colModel.put("hidden",Boolean.parseBoolean(colNames[i][3]));
			  if( colNames[i].length > 4)
			  {	  
				  if(colNames[i][4] != null && (colNames[i][4].length() > 0)) /*group by with summary row */
				  {
					  colModel.put("align",colNames[i][4]);
				  }
			  }
			  if( colNames[i].length > 5)
			  {	  
				  if(colNames[i][5] != null && (colNames[i][5].length() > 0)) /*group by with summary row */
				  {
					  colModel.put("summaryType","sum");
					  colModel.put("summaryTpl",colNames[i][5]);
				  }
			  }
			  if( colNames[i].length > 6 )
			  {	  
				  if(colNames[i][6] != null && (colNames[i][6].length() > 0)) /* cell formatter */
				  {
					  colModel.put("formatter",colNames[i][6]);
				  }
			  }
			  if( colNames[i].length > 7)
			  {	  
				  if(colNames[i][7] != null && (colNames[i][7].length() > 0)) /* cell attr */
				  {
					  colModel.put("cellattr",colNames[i][7]+"(rowId, tv, rawObject, cm, rdata)");
				  }
			  }	  
			  colModelArr.put(colModel);
			  
			  //System.out.println(" j " + i);
		}
		tableModel.put("colNames",headerNames);
		tableModel.put("colModel",colModelArr);
	//	System.out.println("tableModel = " + tableModel);
		return tableModel;
		 
	}


	public static boolean isValidDate(String dateStr)
	{
		if( dateStr != null && ! dateStr.trim().isEmpty() && ! dateStr.equals("undefined"))
		{
			return true;
		}
		dateStr = null;
		return false;
	}

	public static JSONObject getColModelJSONObject(String [][] colModel,String[][]colHeaders)
	{
		JSONObject tableModel = new JSONObject();
		
		JSONArray colModelArr = new JSONArray();
		
		JSONArray colHeadersJSON = new JSONArray();
		for( int i = 0; i < colHeaders.length ; i++)
		{
			colHeadersJSON.put(JSONArray.fromArray(colHeaders[i]));
		}
		for(int i=0;i<colModel.length;i++)
		{
			  JSONObject colModelJSON = new JSONObject();
			  
			  colModelJSON.put("name",colModel[i][0]);
			  colModelJSON.put("index",colModel[i][0].toLowerCase());
			  colModelJSON.put("editable","false");	
			  colModelJSON.put("width",Integer.parseInt(colModel[i][1]));
			  
			  colModelJSON.put("hidden",Boolean.parseBoolean(colModel[i][2]));
			  if( colModel[i].length > 3)
			  {	  
				  if(colModel[i][3] != null && (colModel[i][3].length() > 0)) /*group by with summary row */
				  {
					  colModelJSON.put("align",colModel[i][3]);
				  }
			  }
			  if( colModel[i].length > 4)
			  {	  
				  if(colModel[i][4] != null && (colModel[i][4].length() > 0)) /*group by with summary row */
				  {
					  colModelJSON.put("summaryType","sum");
					  colModelJSON.put("summaryTpl",colModel[i][4]);
				  }
			  }
			  if( colModel[i].length > 5 )
			  {	  
				  if(colModel[i][5] != null && (colModel[i][5].length() > 0)) /* cell formatter */
				  {
					  colModelJSON.put("formatter",colModel[i][5]);
				  }
			  }
			  if( colModel[i].length > 6)
			  {	  
				  if(colModel[i][6] != null && (colModel[i][6].length() > 0)) /* cell attr */
				  {
					  colModelJSON.put("cellattr",colModel[i][6]+"(rowId, tv, rawObject, cm, rdata)");
				  }
			  }	  
			  colModelArr.put(colModelJSON);
			  
		}
		tableModel.put("rowHeaders",colHeadersJSON);
		tableModel.put("colModel",colModelArr);
		return tableModel;
		 
	}

	public static String getPropertyValue(String baseName, String identifier)
	{
		Locale locale = new Locale("en", "IN");  
		ResourceBundle labels =ResourceBundle.getBundle( baseName, locale);
		
		return labels.getString(identifier);
	}
	
	/*public static JSONObject getJqGridTableModel(JqGridTableModel jqGridTableModel)
	{
		List<String[]> rowHeaders = jqGridTableModel.getHeaders();
		List<JqGridColModel> colModelList = jqGridTableModel.getColModel();
		
		JSONObject tableModel = new JSONObject();
		
		JSONArray colModelArr = new JSONArray();
		
		JSONArray colHeadersJSON = new JSONArray();
		//int i = 0;
		for( String [] rowHeader : rowHeaders)
		{	
			colHeadersJSON.put(JSONArray.fromArray(rowHeader));
		
			for(JqGridColModel colModel: colModelList)
			{
				colModelArr.put(JSONObject.fromBean(colModel));
			}
		}	
		tableModel.put("rowHeaders",colHeadersJSON);
		tableModel.put("colModel",colModelArr);
		
		return tableModel;
	}
*/
	public static JSONObject getJqGridTableModel(JqGridTableModel jqGridTableModel)
	{
		/*List<String[]> rowHeaders = jqGridTableModel.getHeaders();
		List<JqGridColModel> colModelList = jqGridTableModel.getColModel();
		
		JSONObject tableModel = new JSONObject();
		
		JSONArray colModelArr = new JSONArray();
		
		JSONArray colHeadersJSON = new JSONArray();
		//int i = 0;
		for( String [] rowHeader : rowHeaders)
		{	
			colHeadersJSON.put(JSONArray.fromArray(rowHeader));
		}	
		for(JqGridColModel colModel: colModelList)
		{
			colModelArr.put(JSONObject.fromBean(colModel));
		}
		tableModel.put("rowHeaders",colHeadersJSON);
		tableModel.put("colModel",colModelArr);
		
		return tableModel;
		*/
		return  JSONObject.fromBean(jqGridTableModel);
	}

	public static JSONObject validationExceptions(String errMsg, String propertyFile)
	{

		errMsg = errMsg.substring(errMsg.indexOf(":")+1).trim();
		String [] errMessages = errMsg.split(",");
		JSONObject err = new JSONObject(); 
		JSONArray a = new JSONArray();
		for(String s: errMessages)
		{

			try{
				//CommonFunctions.debugMsg("com.akranta.tpm.resources.");
				//CommonFunctions.debugMsg("com.akranta.tpm.resources."+propertyFile);				
				//CommonFunctions.debugMsg("com.akranta.tpm.resources."+s);
				a.put(getPropertyValue("com.akranta.tpm.resources."+propertyFile,s));
			}catch(Exception e)
			{
				System.out.println("exce " + e.getMessage());
			}
		}
		err.put("exception",true);
		err.put("messages" ,a );
		return err;
	}
	
	public static AdmTlUsermst getLoginUser(HttpServletRequest request)
	{
    	HttpSession userSession =request.getSession(false);
    	
    	if( userSession != null )
    	 return (AdmTlUsermst) userSession.getAttribute("user");
    	
    	return null;
	}
	
	public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException
	{
	
		RequestDispatcher rd = request.getRequestDispatcher(page); 
		rd.forward(request, response);
	}
	
	public static Cookie getCookie(HttpServletRequest request,String cookieName )
	{
		Cookie [] cookies = request.getCookies() ;
		if( cookies != null )
		{	
			for(int i = 0; i < cookies.length; i++ ){

			  if( cookies[i].getName().equals(cookieName) ){
				  return cookies[i]; 
			  	}
			}
		}
		
		
		return null;
	}
	
	public static void removeCookie(HttpServletResponse response, String cookieName){
		Cookie delCookie = new Cookie(cookieName, null);
		delCookie.setMaxAge(0);
		response.addCookie(delCookie);
	}
	public static String getCookieValue(HttpServletRequest request,String cookieName )
	{
		Cookie cookie = getCookie(request,cookieName);
		if( cookie != null )
			return cookie.getValue();
		
		return null;
	}
	
	public static void setCookieValue(HttpServletResponse response,String cookieName, String cookieValue )
	{
		Cookie cookie = new Cookie(cookieName, cookieValue);
		response.addCookie(cookie);
	}

	public static JSONObject fromTpmModel(Object tpmModel)
	{
		Class<?> cls  = tpmModel.getClass();
		Method [] methods 	= cls.getDeclaredMethods();
		JSONObject retObject = new JSONObject();
		for(Method method : methods)  
		{
			 if( ! isValidTpmModelRetType(method))
				 continue;
			 
			 try {
				retObject.put(method.getName().substring(3), method.invoke(tpmModel,null));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retObject;
	}
	
	public static JSONArray fromTpmModelList(List<Object> tpmModelList )
	{
		JSONArray  tpmArrayObject = new JSONArray();
		for(Object tpmModel: tpmModelList){
			tpmArrayObject.put(fromTpmModel(tpmModel));
		}	
		return tpmArrayObject;
	}
	
	private static boolean isValidTpmModelRetType(Method method)
	{
		Class<?> retType = method.getReturnType();
		if(method.getName().startsWith("get") && ( String.class.isAssignableFrom(retType) || int.class.isAssignableFrom(retType) ||  Date.class.isAssignableFrom(retType) || 
						char.class.isAssignableFrom(retType) || double.class.isAssignableFrom(retType) || float.class.isAssignableFrom(retType) ) )
		{	
			return (method.getParameterTypes().length == 0 ? true:false);
			
		}	
		return false;
	}
	public static Object copyObject(Object fromObject, Object toObject )
	{
		  Class fromCls  = fromObject.getClass();
		  Method [] fromMethods = fromCls.getMethods();
		  Class toCls  = toObject.getClass();
		  Method [] toMethods = toCls.getMethods();
		  int i=0;
		  while(i<fromMethods.length)
		  {
			  String getFlag = fromMethods[i].getName().substring(0,3);
			  String fromField = null;
			  if(fromMethods[i].getName().length() >= 7)
				  fromField=fromMethods[i].getName().substring(7);
			  for(int j =0; j<toMethods.length;j++ )  
			  {
				  String setFlag = null;
				  String toField = null;	
				  
				  if(isValidKeyId(toMethods[j].getName()))
					  setFlag =  toMethods[j].getName().substring(0,3);
			
				  if(getFlag.equals("get") && setFlag.equals("set") )
				  {
					 
					  if(toMethods[j].getName().length() >= 7)
						  toField=toMethods[j].getName().substring(7);
					 
					  if(fromField.equals(toField))
					  {
						  try {
							  toMethods[j].invoke( toObject,fromMethods[i].invoke( fromObject) ) ;
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
					  }
				 
				  }
			  }
			  i++;
		  }
		 
		return toObject;
	}
	public static Object setBeanProperties(Object beanObject, HttpServletRequest request )
	{
		  Class cls  = beanObject.getClass();

		 // String methodeName = "get"+propertyName;
		  //Method  method = cls.getDeclaredMethod(methodeName,  null) ;
		  Method [] methods 		= cls.getMethods();
		  Enumeration<String> params = request.getParameterNames();
		  int i=0;
		  while( params.hasMoreElements())
		  {
			  String reqParam = params.nextElement();
			//  System.out.println("reqParam     "+reqParam);
			  if( reqParam.length() < 4 )
				  continue;
			  
			  String paramValue = request.getParameter(reqParam);
			 // System.out.println("paramValue     "+paramValue);
			  if( paramValue != null && ! paramValue.isEmpty() &&  paramValue.trim().length() > 0 )
			  {	  
				 // System.out.println("paramValue " + paramValue);
				  
				  //paramValue = reqParam.toLowerCase().startsWith("img") ? paramValue: paramValue.toUpperCase();
				  
				  String pName = reqParam.substring(3);
				  char u = Character.toUpperCase(pName.charAt(0));
				  pName = u+pName.substring(1);
				  String methodName = "set" +pName;
				  System.out.println("in utils set methodName " + methodName+" : "+paramValue);
				  String methodName1 = "get" +pName;
				  System.out.println("in utils set methodName " + methodName1+" : "+paramValue);
				  for(i =0; i<methods.length;i++ )  
				  {
					  if( methods[i].getName().equals(methodName)  )
					  {	  
						    //Class[] paramTyArr = methods[i].getParameterTypes();
							try {
								methods[i].invoke( beanObject,paramValue ) ;
							} catch (IllegalArgumentException e) {
							} catch (IllegalAccessException e) {
							} catch (InvocationTargetException e) {
							}
						    break;
					  }
				  }	  
			  }	  
		  }
		  
		  return beanObject;
	}

	public static List<?> convertJSONArrToList(Object beanObject, JSONArray inPutJSONArray   ) 
	{
		 try{
			  Class cls  = beanObject.getClass();
			  Method [] methods 	= cls.getDeclaredMethods();
			  						  	
			  Class<?> [ ] methodType = new Class[] { String.class };
			  //int i=0;
			  List<Object> resultObjects = new ArrayList<Object>();
			  Method method;
			  for( int i = 0;i< inPutJSONArray.length();i++)
			  {
				  JSONObject rowData = (JSONObject)inPutJSONArray.get(i);
				  JSONArray names = rowData.names();
				  boolean canAdd = false;
				  Object newObject  = cls.newInstance();
				  for( int j = 0; j< names.length();j++ )
				  {
					  String propertyName = names.getString(j);
					  String paramValue = rowData.getString(propertyName);
					 
					  if( propertyName.length() < 4  && paramValue != null && ! paramValue.trim().isEmpty())
						  continue;
					  
					  if( paramValue != null && ! paramValue.isEmpty() &&  paramValue.trim().length() > 0 )
					  {	  
						  //paramValue = paramValue.toUpperCase();
						  
						  String pName = propertyName.substring(3);
						  char u = Character.toUpperCase(pName.charAt(0));
						  pName = u+pName.substring(1);
						  String methodName = "set" +pName;

						   //System.out.println("------------->"+ propertyName +  " : " +methodName+" - "+ paramValue );
 
						  for(int k =0; k<methods.length;k++ )  
						  {
							 
							  if( methods[k].getName().equals(methodName)  )
							  {	  
								  
								  try {
									    System.out.println("Invoke ------------->"+ methods[k].getName() + " - "+ paramValue );
									  	methods[k].invoke(newObject,paramValue ) ;
										canAdd = true;
										
								  } catch (IllegalArgumentException e) {
										e.printStackTrace();
								  } catch (IllegalAccessException e) {
										e.printStackTrace();
								  } catch (InvocationTargetException e) {
										e.printStackTrace();
								  } catch (SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								  }
								  break;
							  }	  
						  }
					  }
					  
				  }
				  if( canAdd )
					  resultObjects.add(newObject);
			  }	  
			 
			  return resultObjects;
		 }catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	 
	 public static JSONObject getTableModel(List<String[]> headers,String [] colHeaderCon,JqGridTableModel jqGridTableModel,GridColModel gridColModel)
		{
			//JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			 //CommonFunctions.debugMsg("Header Num   "+colHeaderCon);
		    boolean gridEdit = false;
			int headerNum= gridColModel.getHeaderNum(); 
			CommonFunctions.debugMsg("in sie java utils"+headerNum);
			String [] colHeader = null;
			for(int k=0;k<headerNum;k++){
				if(headerNum>1 && k==0){
					
					String [] emptyHeader = new String[ headers.get(0).length];
						for(int i=0;i<headers.get(0).length;i++)
							emptyHeader[i]="";
						jqGridTableModel.getRowHeaders().add(emptyHeader);
				}
				colHeader = headers.get(k);	
				jqGridTableModel.getRowHeaders().add(colHeader);
			}
			//String [] colHeader = headers.get(1);			
			String [] colHeaderCond = colHeaderCon;
			//List<Integer> rotationRows = new ArrayList<Integer>();
			//rotationRows.add(2);
			//jqGridTableModel.setRotationRows(rotationRows);
			
			for(int i =0; i < colHeader.length; i++)
			{		
				//CommonFunctions.debugMsg("123..."+colHeader[i]);
				JqGridColModel jqGridColModel = new JqGridColModel();
				JqGridEditOptions jqGridEditOptions = new JqGridEditOptions();
				jqGridColModel.setEditable(false);
				
				String [] cond = colHeaderCond[i].split("#");
				boolean hidden = false;
				boolean key = false;
				String alignment = "left";
				String width = "100";
				String index = "";
				String pEditOptions  = "";
				String editOptions = "";
				boolean psave = false;
				boolean isNumeric = false;
				boolean isMandatory = false;
				
				for(int j=0;j<cond.length;j++)
				{				
					String[] args = cond[j].split("=");
					if(("HD".equals(args[0]) && "T".equals(args[1])) || (colHeaderCond[i].length()<3) )
						hidden = true;
					else if("KE".equals(args[0]) && "T".equals(args[1]))
						key = true;
					else if("AL".equals(args[0]) && "R".equals(args[1]))
						alignment = "right";
					else if("AL".equals(args[0]) && "C".equals(args[1]))
						alignment = "center";
					else if("AL".equals(args[0]) && "L".equals(args[1]))
						alignment = "left";
					else if("WI".equals(args[0]) && BAL_UIUtils.isValidKeyId(args[1]))
						width = args[1];
					
					else if("IND".equals(args[0]) && BAL_UIUtils.isValidKeyId(args[1]))
						index = args[1];
					else if("SV".equals(args[0]) && BAL_UIUtils.isValidKeyId(args[1])){
						psave  = true;
						
					}
					
					else if("MD".equals(args[0]) && BAL_UIUtils.isValidKeyId(args[1]))
						isMandatory  = true;
					else if("EO".equals(args[0])&& args.length>1 ){
						// CommonFunctions.debugMsg("ARGUMENT     "+args[1]);
					  
						 editOptions=args[1];
						 //CommonFunctions.debugMsg("editOptions=    "+editOptions);
						 JSONObject pedtOPt = null;//new JSONObject();
						 if(BAL_UIUtils.isValidKeyId(editOptions))
						    pedtOPt = JSONObject.fromString(editOptions); 
						 else
						    continue; 
						 
						 gridEdit = true;
						 //CommonFunctions.debugMsg("pedtOPt=    "+pedtOPt.get("editType"));
						 //JqGridEditOptions  peditOptions =(JqGridEditOptions) JSONObject.toBean(pedtOPt);
						 
						 if(pedtOPt.has("editType")) {
							 jqGridEditOptions.setEditType(pedtOPt.get("editType").toString());
							 if(BAL_UIUtils.isValidKeyId(pedtOPt.get("editType").toString()))
							 jqGridColModel.setpEditable(true);	
						 }
						 if(pedtOPt.has("url"))
							 jqGridEditOptions.setUrl(pedtOPt.get("url").toString());
						 if(pedtOPt.has("cols"))
							 jqGridEditOptions.setCols(Integer.parseInt(pedtOPt.get("cols").toString()));
						 if(pedtOPt.has("caption"))
							 jqGridEditOptions.setCaption(pedtOPt.get("caption").toString());
						 if(pedtOPt.has("number")){
							 boolean isnum = false;
							 if("true".equals(pedtOPt.get("number").toString()))
								 isnum = true;
							 jqGridEditOptions.setNumber(isnum);
						 }
						 if(pedtOPt.has("idColName"))
							 jqGridEditOptions.setIdColName(pedtOPt.get("idColName").toString());
						 if(pedtOPt.has("rows"))
							 jqGridEditOptions.setRows(Integer.parseInt(pedtOPt.get("rows").toString()));
						 if(pedtOPt.has("panelHeight"))
							 jqGridEditOptions.setPanelHeight(Integer.parseInt(pedtOPt.get("panelHeight").toString()));
						 if(pedtOPt.has("maxLength"))
							 jqGridEditOptions.setMaxLength(Integer.parseInt(pedtOPt.get("maxLength").toString()));
						 if(pedtOPt.has("dateFormat"))
							 jqGridEditOptions.setDateFormat(pedtOPt.get("dateFormat").toString());
						 if(pedtOPt.has("local")){
							 boolean islocal = false;
							 if("true".equals(pedtOPt.get("local").toString()))
								 islocal = true;
							 jqGridEditOptions.setLocal(islocal);
						 }
						 if(pedtOPt.has("panelWidth"))
							 jqGridEditOptions.setPanelWidth(Integer.parseInt(pedtOPt.get("panelWidth").toString()));
						 if(pedtOPt.has("multiple")){
							 boolean ismultiple = false;
							 if("true".equals(pedtOPt.get("multiple").toString()))
								 ismultiple = true;
							 jqGridEditOptions.setMultiple(ismultiple);
						 }
						 JSONArray jOptions=new JSONArray();
						 if(pedtOPt.has("options"))
							 jOptions=JSONArray.fromString(pedtOPt.get("options").toString());
						 	 List<ComboBox> cOptions = JSONArray.toList(jOptions);
							 jqGridEditOptions.setOptions(cOptions );
						     jqGridColModel.setpEditOptions( jqGridEditOptions );
						     //CommonFunctions.debugMsg("EDITTTTYPE   "+jqGridEditOptions.getEditType());
					  }
						
				  }
					 
				jqGridColModel.setpSave(psave);
				jqGridColModel.setMandatory(isMandatory);
				String formattor = gridColModel.getFormatter();
				String fromCol = gridColModel.getFormattorFromCol();
				String toCol = gridColModel.getFormattorToCol();
				
				jqGridColModel.setIndex(index.replaceAll(" ", ""));
				jqGridColModel.setName(index.replaceAll(" ", ""));
				jqGridColModel.setKey(key);
				jqGridColModel.setHidden(hidden);
				jqGridColModel.setAlign(alignment);
				jqGridColModel.setWidth(Integer.parseInt(width));
				if(BAL_UIUtils.isValidKeyId(formattor)){
					if(BAL_UIUtils.isValidKeyId(fromCol) && BAL_UIUtils.isValidKeyId(toCol)){
						int from = Integer.parseInt(fromCol);
						int to =  Integer.parseInt(toCol);
						if(i>=from && i<=to)
						{
							//CommonFunctions.debugMsg(formattor+"   "+fromCol+" inside to "+toCol);
							jqGridColModel.setFormatter(formattor);
						}
					}
				}
				List<String> listformattor = gridColModel.getMultiformatter();
				List<String> listformattorFrom = gridColModel.getMultiformattorFromCol();
				List<String> listformattorTo = gridColModel.getMultiformattorToCol();
				if(listformattor!=null){
					for(int k=0; k<listformattor.size();k++){
						if(BAL_UIUtils.isValidKeyId(listformattorFrom.get(k)) && BAL_UIUtils.isValidKeyId(listformattorTo.get(k))){
							int from = Integer.parseInt(listformattorFrom.get(k));
							int to =  Integer.parseInt(listformattorTo.get(k));
							if(i>=from && i<=to)
							{
								//CommonFunctions.debugMsg(listformattor.get(k)+"   "+from+" inside to "+to);
								jqGridColModel.setFormatter(listformattor.get(k));
							}
						}
					}
				}
			 
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
			if( gridEdit )
				jqGridTableModel.setGridEdit(true);
			 JSONObject tableModel = BAL_UIUtils.getJqGridTableModel(jqGridTableModel);
			 //CommonFunctions.debugMsg("tableModelUIT  "+tableModel);
			 return tableModel;
		}
	 public static String getTablemodelSql(JqGridColModel jqGridColModel) {
			
			boolean hidden = jqGridColModel.isHidden();
			boolean key = jqGridColModel.isKey();
			String align = jqGridColModel.getAlign();
			int width = jqGridColModel.getWidth();
			String index = jqGridColModel.getIndex();
			
			if(!BAL_UIUtils.isValidKeyId(align))
				align="left";
			//CommonFunctions.debugMsg("hidden"+String.valueOf(hidden).toUpperCase()+"key.."+key+"..align.."+align+"..width.."+width+"..index.."+index);
			String sql = " '||CHR(39)|| GEN_PC_COMMONFUNCTIONS.COLMOD('"+String.valueOf(hidden).toUpperCase()+"','"+String.valueOf(key).toUpperCase()+"','"+align.toUpperCase()+"',"+width+",'"+index.toUpperCase()+"')||CHR(39)||' AS "+index.toUpperCase()+",";

			return sql;
		}
 
	public static String imageUpload(HttpServletRequest request,String fileUrl)
	{
		String dispImage = null;
		boolean isMultipart = JakartaServletFileUpload.isMultipartContent(request);//ServletFileUpload.isMultipartContent(request);
		boolean isBrowserIE = false; 
		System.out.println("request: " + request);
		
		String userAgent = ((HttpServletRequest) request).getHeader("User-Agent");
	    //CommonFunctions.debugMsg("userAgent "  +userAgent);
	    
	    for (String browser_id : DEFAULT_BROWSERS)
        {
	        if (userAgent.contains(browser_id))
	        {
	        	isBrowserIE = true;
	        	break;
	        }
        }
		if (!isMultipart) 
		{
			System.out.println("File Not Uploaded");
		} 
		else 
		{
			System.out.println("Inside Else");
//			FileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
			FileItemFactory factory = DiskFileItemFactory.builder().get();
	        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			List items = null;
			try 
			{
				System.out.println("Inside Try Block");
				items = upload.parseRequest(request);
				System.out.println("items: " + items);
			}
			catch (FileUploadException e) 
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			Iterator itr = items.iterator();
			while (itr.hasNext()) 
			{
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) 
				{
					 String name = item.getFieldName();
	                    try {
	                        String value = item.getString(); // 2.x can throw IOException
	                    } catch (IOException io) {
	                        io.printStackTrace();
	                    }
				} 
				else 
				{
					try 
					{
						String itemName = item.getName();
						itemName = itemName.toUpperCase();
						if(isBrowserIE)
							itemName = itemName.substring(itemName.lastIndexOf('\\')+1,itemName.length());
						 
						System.out.println("itemName   >>>>>:"+itemName.substring(itemName.lastIndexOf('\\')+1,itemName.length()));
						Random generator = new Random();
						int r = Math.abs(generator.nextInt());

						String reg = "[.*]";
						String replacingtext = "";
						System.out.println("Text before replacing is:-"
								+ itemName);
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();

						while (matcher.find()) 
						{
							matcher.appendReplacement(buffer, replacingtext);
						}
						int IndexOf = itemName.indexOf(".");
						String domainName = itemName.substring(IndexOf);
						System.out.println("domainName: " + domainName);

						String finalimage = buffer.toString() + "_" + r
								+ domainName;
						//System.out.println("before space===" + finalimage);
						String AfterRemove= finalimage.replaceAll("\\s+","_");
					//	System.out.println("Final Image===" + AfterRemove);
						
						//File savedFile = new File( fileUrl + "\\" + AfterRemove);
						File savedFile = new File( fileUrl + "\\" + AfterRemove.toUpperCase());
						//item.write(savedFile);
						 // FileUpload 2.x writes to Path (not File)
                        item.write(savedFile.toPath());
						dispImage = AfterRemove;
						
						String widthStr = request.getParameter("width");
						String heightStr = request.getParameter("height");
						if( widthStr != null && heightStr != null ){
							int width = Integer.parseInt(widthStr);
							int height = Integer.parseInt(heightStr);
							resizeImage(fileUrl + AfterRemove,width,height);
						}	
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		return dispImage;
	}
    
	private static void resizeImage(String  imageName, int width,int height) throws IOException{
		
		//CommonFunctions.debugMsg( " file Name "+ imageName);
		
		BufferedImage originalImage = ImageIO.read(new File(imageName));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		String extension = getFileExtension(imageName);
		//CommonFunctions.debugMsg(" extension " + extension + " file Name "+ imageName);
		ImageIO.write(resizedImage, extension, new File(imageName));
		
		
		
	 }
	
	public static String getFileExtension(String fileName){
		int lastIndex =  fileName.lastIndexOf(".")+1;
		return fileName.substring(lastIndex).trim();
	}
	
	
	
	public static JSONObject businessValidationExceptions(String errMsg, String propertyFile)
	{
		if(errMsg.indexOf(':')>0)
			errMsg = errMsg.substring(errMsg.indexOf(":")+1).trim();
		String [] errMessages = errMsg.split(",");
		JSONObject err = new JSONObject(); 
		JSONArray a = new JSONArray();
		for(String s: errMessages)
		{
			try{
				System.out.println(" PROP : "+s);
				a.put(getPropertyValue("com.akranta.tpm.resources."+propertyFile,s));
			}catch(Exception e)
			{
				
			}
		}
		err.put("tpmException",a);
		//err.put("messages" ,a );
		return err;
	}
	public static JSONObject commonApplicationException(String msg){
		
		JSONObject err = new JSONObject();
		JSONArray a = new JSONArray();
		a.put(msg);
		err.put("tpmException",a);
		return  err;
	}
	public static String getActionPart(HttpServletRequest request){
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/"); 
		return uri.substring(lastIndex + 1);
	}
	public static void writeMachineHirearchy(HttpServletResponse response,List<String[]> machineHirerachy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 	
			
	     //JSONArray jsonObject = JSONArray.fromObject(machineHirerachy);
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("factory", machineHirerachy.get(0)[0]);
		 //jsonObject.put("sbu", machineHirerachy.get(0)[0]);
		 jsonObject.put("section",machineHirerachy.get(0)[1]);
		 jsonObject.put("cell",machineHirerachy.get(0)[2]);
		 jsonObject.put("circle",machineHirerachy.get(0)[3]);
		 jsonObject.put("eqpGroup",machineHirerachy.get(0)[4]);
		 jsonObject.put("costcenterid",machineHirerachy.get(0)[5]);
		 jsonObject.put("company",machineHirerachy.get(0)[6]);
		 jsonObject.put("location",machineHirerachy.get(0)[7]);
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("machineHirerachy", jsonObject);
	     //System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	
	
	
	public static void writeCellHirearchy(HttpServletResponse response,List<String[]> cellHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			
     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("factory", cellHierarchy.get(0)[0]);		 
		 jsonObject.put("section",cellHierarchy.get(0)[1]);	
		 jsonObject.put("costcentre",cellHierarchy.get(0)[2]);
		 jsonObject.put("company",cellHierarchy.get(0)[3]);
		 jsonObject.put("location",cellHierarchy.get(0)[4]);
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("cellHierarchy", jsonObject);
	     System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	
	public static void writeCityHirearchy(HttpServletResponse response,List<String[]> cityHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			
		 System.out.println("cityHierarchy"+cityHierarchy);	     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("state",cityHierarchy.get(0)[0]);
		 jsonObject.put("country", cityHierarchy.get(0)[1]);			 
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("cityHierarchy", jsonObject);
	     System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	/*for year hirerachy plan configuration*/
	
	public static void writeYearHirerachy(HttpServletResponse response,List<String[]> yearHierarchy ) throws IOException
	{
		CommonFunctions.debugMsg(" yearHierarchy.size() -------------E"   );
		CommonFunctions.debugMsg(" yearHierarchy.size() -------------" + yearHierarchy.size());
		// response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			
		 	     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("halfyearly",yearHierarchy.get(0)[1]);
		 jsonObject.put("monthly", yearHierarchy.get(1)[1]);
		 jsonObject.put("quartely",yearHierarchy.get(2)[1]);
		
		 JSONObject yrhierarchy = new  JSONObject();
		 yrhierarchy.put("yearHierarchy", jsonObject);
         out.print(yrhierarchy);
	}
	/*end*/
	public static void writeSectionHirearchy(HttpServletResponse response,List<String[]> sectionHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			
		 System.out.println("sectionHierarchy"+sectionHierarchy);	     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("company",sectionHierarchy.get(0)[0]);
		 jsonObject.put("factory", sectionHierarchy.get(0)[1]);
		 jsonObject.put("location", sectionHierarchy.get(0)[2]);
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("sectionHierarchy", jsonObject);
	     System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	public static void writeSubUnitHirearchy(HttpServletResponse response,List<String[]> subUnitHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("cellId",subUnitHierarchy.get(0)[0]);
		// jsonObject.put("factory", sectionHierarchy.get(0)[1]);			 		 
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("subUnitHierarchy", jsonObject);
	     System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	public static void writefactoryHierarchy(HttpServletResponse response,List<String[]> factoryHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("company",factoryHierarchy.get(0)[0]);
		 jsonObject.put("location", factoryHierarchy.get(0)[1]);			 		 
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("factoryHierarchy", jsonObject);
	     System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	public static void writelocationHierarchy(HttpServletResponse response,List<String[]> locationHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("company",locationHierarchy.get(0)[0]);
		// jsonObject.put("factory", sectionHierarchy.get(0)[1]);			 		 
		 JSONObject eqphirerachy = new  JSONObject();
		 eqphirerachy.put("locationHierarchy", jsonObject);
	     System.out.println(eqphirerachy);
         out.print(eqphirerachy);
	}
	
	public static void writecostCenterHierarchy(HttpServletResponse response,List<String[]> costcenterHierarchy ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 			     
		 JSONObject jsonObject = new  JSONObject();
		 jsonObject.put("factory",costcenterHierarchy.get(0)[0]);
		 jsonObject.put("section",costcenterHierarchy.get(0)[1]);
		 jsonObject.put("cell",costcenterHierarchy.get(0)[2]);
		// jsonObject.put("factory", sectionHierarchy.get(0)[1]);			 		 
		 JSONObject cchirerachy = new  JSONObject();
		 cchirerachy.put("costCenterHierarchy", jsonObject);
	     System.out.println(cchirerachy);
         out.print(cchirerachy);
	}	
	
	

	  public static JSONObject convertListToJqGridTableObject(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart,int count)
	  {
			int page = 1;
			int limit = 100;
			int totalpages = 0;		
			
	 	    String pageStr = request.getParameter("page");
	 	 
	 	    if(pageStr != null)
	 	    	page = Integer.parseInt(pageStr);
	 	 
			if (count > 0)
			   totalpages =  (count / limit)+1;
			else
			   totalpages = 0;

			 if (page > totalpages)
			      page = totalpages;
		
			 JSONObject tableDataObject = new JSONObject();
		
			
			tableDataObject.put("page", page); //current page	 
			tableDataObject.put("total",totalpages); // total page
			tableDataObject.put("records", count); //total records
			
			JSONArray rowArr = new JSONArray(); 
	       
	      
			int rowId = 0;
	        for( String [] row : dataArrayList)
			{
	        	if( rowId >= rowStart )
	        	{	
		    	    JSONObject rowObj =new JSONObject();
		    	    rowObj.put("id",rowId+1);
		            
		            JSONArray cell=new JSONArray();
		            for( int i = colStart ;i < row.length ; i++)
		            {	
		            	
		            	cell.put( ( row[i] != null ? row[i].isEmpty() ? " " : row[i].replace("{", "-").replace("}", "").replace("[", " ").replace("]", "") :" ") ); 
		            }	
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	       }

	        tableDataObject.put("rows", rowArr);
	        
	        return tableDataObject;
	 }

	 
	  
	/*end*/
	  
	  public static JSONArray fromTpmModelToArray(Object tpmModel)
	  {
			Class<?> cls  = tpmModel.getClass();
			Method [] methods 	= cls.getDeclaredMethods();
			JSONArray retArray = new JSONArray();
			String value = null;
			for(Method method : methods)  
			{
				 if( ! isValidTpmModelRetType(method))
					 continue;
				 
				 try {
					 value =(String)method.invoke(tpmModel,null);
					 value = (value != null ? value.isEmpty() ?" ": value.replace("{", "").replace("}", ""):" ");
					   
					 retArray.put( value );
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return retArray;
		}
	  
	  public static JSONArray fromTpmModelToArray(Object tpmModel, JSONArray colModelArr)
	  {
			Class<?> cls  = tpmModel.getClass();
			Method [] methods 	= cls.getDeclaredMethods();
			JSONArray retArray = new JSONArray();
			String value = null;
			Method method = null;
			JSONObject colModel = null;
			String colName = null;
			for(int i = 0;i< colModelArr.length();i++){  
				colModel = colModelArr.getJSONObject(i);
				if( colModel.has("name") && ( (colName = colModel.getString("name")) != null ) )
					method = containsColModel(methods,colName);
				else
					continue;
				
				if( method == null )
					 continue;
				 
				 try {
					 value =(String)method.invoke(tpmModel,null);
					 value = (value != null ? value.isEmpty() ?" ": value.replace("{", "").replace("}", ""):" ");
					   
					 retArray.put( value );
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return retArray;
	  }
	  
	  

	  
	  private static Method containsColModel(Method [] methods, String methodName){
		  String name =null;
		
		  for(Method method:methods )
		  {	  
			  if(! isValidTpmModelRetType(method) )
				  continue;
			  name = method.getName();
			
			  if( name.toLowerCase().endsWith((methodName.substring(3).toLowerCase())))
				  return method;
		  }
		  return  null;
	  }
	  
	  public static JSONObject convertToJqGridTableObject(List<?> dataObjectList,HttpServletRequest request,JSONObject colModel, int rowStart)
	  {
			
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			int rows = 1000;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			
			JSONObject tableDataObject = new JSONObject();
			
			
			tableDataObject.put("page", page); //current page
			tableDataObject.put("total",Math.ceil(dataObjectList.size()/rows)==0?1:Math.ceil(dataObjectList.size()/rows)); // total page
			tableDataObject.put("records", (dataObjectList.size()-rowStart)); //total records
			
			JSONArray rowArr = new JSONArray(); 

			int rowId = 0;
			JSONArray colModelArr = colModel.getJSONArray("colModel");
			for(Object tpmModel: dataObjectList){
				
			
	        	if( rowId >= rowStart )
	        	{	
	        		JSONObject rowObj =new JSONObject();
		    	    rowObj.put("id",rowId+1);
		            
		            JSONArray cell=fromTpmModelToArray(tpmModel,colModelArr);
		           
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	       }

	        tableDataObject.put("rows", rowArr);
	        
	        return tableDataObject;
		}
	  
	  public static JSONObject convertToJqGridTableObject(List<?> dataObjectList,HttpServletRequest request,int rowStart)
	  {
			
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			int rows = 1000;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			
			JSONObject tableDataObject = new JSONObject();
			
			
			tableDataObject.put("page", page); //current page
			tableDataObject.put("total",Math.ceil(dataObjectList.size()/rows)==0?1:Math.ceil(dataObjectList.size()/rows)); // total page
			tableDataObject.put("records", (dataObjectList.size()-rowStart)); //total records
			
			JSONArray rowArr = new JSONArray(); 

			int rowId = 0;
			
			for(Object tpmModel: dataObjectList){
				
			
	        	if( rowId >= rowStart )
	        	{	
	        		JSONObject rowObj =new JSONObject();
		    	    rowObj.put("id",rowId+1);
		            
		            JSONArray cell=fromTpmModelToArray(tpmModel);
		           
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	       }

	        tableDataObject.put("rows", rowArr);
	        
	        return tableDataObject;
		}
	  
	  	public static void displayRequestParamsValue(HttpServletRequest request){
			 Enumeration<String> params = request.getParameterNames();
			 System.out.println( "------------Request Parameters--------------");
			 while( params.hasMoreElements()){
				 String pName = params.nextElement();
				 System.out.println( pName + ":ooooooooo" + request.getParameter(pName) );
			 }
			 System.out.println( "-------------------End----------------------");
		 }
	  	
	  	public static boolean isValidKeyId(String keyId)
	  	{
	  		
	  		if( keyId != null && keyId.trim().length() > 0  && ! keyId.equals("{}") && ! keyId.equals("<**>") && ! keyId.equals("-") && ! keyId.toLowerCase().equals("null") && ! keyId.toLowerCase().equals("undefined"))
	  			return true;
	  		return false;
	  	}
	  	
	  	public static boolean isNumericString(String str){
	  		
	  		if( str != null && str.trim().length() > 0){
		  		char [] charArr = str.trim().toCharArray();
		  		
		  		for(char c:charArr){
		  			
		  			if( c >57 || c< 48)
		  				return false;
		  		}
		  		
		  		return true;
	  		}
	  		return false;
	  	}
	  	
	  	
	  	public static boolean checkUserSession(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	  		
	  		AdmTlUsermst user = getLoginUser(request);
			if( user == null ){
				//forwardRequest(request, response, "logout.userLogin");
				return false;
			}	
			return true;
	  	}
	  	
	  	public static boolean checkUserRights(HttpServletRequest request,HttpServletResponse response, String actionPart) throws ServletException, IOException{
	  		
	  		if( actionPart != null && actionPart.contains("_input")){
		  		AdmTlUsermst user = getLoginUser(request);
		  		String menuName =  null;
		  		if( !user.getUsrm_isadministartor().equals("Y") ){
			  		HttpSession httpSession = request.getSession(false);
			  		List<MenuTree> menuList = (List<MenuTree>) httpSession.getAttribute("userMenuRights");   
					if( menuList == null ){
						
						for( MenuTree menuTree :  menuList){
							menuName =  menuTree.getMenuName();
							if(  menuName != null && menuName.equals(actionPart)  )
							{	
								
								return true;
							}
						}
						
						return false;
					}
		  		}
	  		}
			return true;
			
	  	}
	  	
	  	public static String getImagePath(HttpServletRequest request){

	  		String imgPath = request.getServletContext().getRealPath("CommonFilterServlet");
			
			imgPath = imgPath.replace("CommonFilterServlet",TPM_TEMPIMG_DIR).replace("\\","/").replace("[]","");
			
			return imgPath;
	  	}

	  	public static String getExcelTemplatePath(HttpServletRequest request){
	  		String path = request.getServletContext().getRealPath("CommonFilterServlet");
			
	  		path = path.replace("CommonFilterServlet","WEB-INF/exceltemplates").replace("\\","/") ;
			
			return path;
	  	}
	  	
	  	public static String getEasyUIComboboxHtml(String id,boolean clearGotFocus){
			StringBuffer form = new StringBuffer();
			
			form.append("<input id='");
			form.append(id);
			form.append("' name='");
			form.append( id );
			if( !clearGotFocus )
				form.append("' clear='false");
			
			form.append("' type='text' class='easyui-combobox' style='width:300px;'/>" );
			
			return form.toString();
		}
	  	
	  	public static String getEasyUICheckBoxHtml(String id, String displayName){
	  		StringBuffer element = new StringBuffer();
			
	  		element.append("<input id='");
	  		element.append(id);
	  		element.append("' name='");
	  		element.append( id );
	  		element.append("' type='checkbox'  style='width:300px;'/>" );
	  		
			return element.toString();
	  	}
		
	  	public static String getSelectHtml(String id,String options, String codeValues ){
			StringBuffer form = new StringBuffer();
			form.append("<Select id='");
			form.append(id);
			form.append("' name='");
			form.append( id );
			form.append("' type='text' class='easyui-combobox' style='width:200px;'>" );
			//options = options.substring(1);
			//codeValues = codeValues.substring(1); 
			String [] optionArr =  options.split(",");
			String [] codeArr = codeValues.split(",");
			
			for(int i=0;i<codeArr.length;i++)
			{
				form.append("<option value='");
				form.append(codeArr[i]);
				form.append("'>");
				form.append(optionArr[i]);
				form.append("</option>");
			}
			 
			form.append("</select>");
			
			return form.toString();
		}
		
	  	public static String getEasyUIDateBoxHtml(String id){
			StringBuffer form = new StringBuffer();
			form.append("<input id='");
			form.append(id);
			form.append("' name='");
			form.append( id );
			form.append("' type='text' class='easyui-datebox' style='width:200px;'/>" );
			
			return form.toString();
		}
	  	
	  	public static String getEasyUITextBoxHtml(String id, int maxlength, boolean isNumeric){
			StringBuffer form = new StringBuffer();
			
			form.append("<input id='");
			form.append(id);
			form.append("' name='");
			form.append( id );
			form.append("' maxlength='");
			form.append(maxlength);
			
			if( isNumeric)
				form.append("' class='easyui-text");
			else
				form.append("' class='easyui-text");
			
			form.append("' type='text' style='width:300px;'/>" );
			return form.toString();
		}
	  	
	  	public static String getEasyUITextAreaHtml(String id,int maxlength){
			StringBuffer form = new StringBuffer();
			form.append("<textarea id='");
			form.append(id);
			form.append("' name='");
			form.append( id );
			form.append("' maxlength='");
			form.append(maxlength);
			form.append("' type='text' style='width:300px;' cols='34' rows='5';>" );
			form.append("</textarea>");
			return form.toString();
		}

	  	public static String getEasyUILabelHtml(String labelName,boolean isMandatory ){
			StringBuffer form = new StringBuffer();
			form.append("<label ");
			
			if(isMandatory)
				form.append(" class='mandatory-lbl'");
			
			form.append(">");
			form.append( labelName );
			form.append("</label>" );
			return form.toString();
		}
	  	
	  	public static String getHiddenTextHtml(String id){
			StringBuffer form = new StringBuffer();
			form.append("<input id='");
			form.append(id);
			form.append("' name='");
			form.append( id );
			form.append("' type='hidden' />" );
			
			return form.toString();
		}
	  	public static String getFunctionalLocation(String id){
	  		//<input type=\"hidden\" id=\""+ defaultId +"\" name= \"hdn" + defaultId +"\">' );
	  		StringBuffer form = new StringBuffer();
	  		form.append(" <div id='frmMasterTblFuntKeyIds'>");							
	  		form.append(" <input type='hidden' id='section' name='section'/>");	
            form.append(" <input type='hidden' id='cell' name='cell'/>");	
            form.append(" <input type='hidden' id='machine' name='machine'/>");	
            form.append(" <input type='hidden' id='flid' name='"+ id +"'/>");								
            form.append(" </div>");				
            form.append(" <div id='"+id+"funLocation'></div>");	
	  		 
			return form.toString();
	  	}
	  	public static int getMaxDayOfMonth(String dateStr)
	  	{
	  		  
	  		/*String monthStart= dateStr.substring(dateStr.indexOf("-"));*/
	  			String monthStart="-"+dateStr;
	  		  monthStart ="01"+monthStart;
	  		  Date cDate = convertToUtilDate(monthStart, "dd-MMM-yyyy");	
	  		  Calendar calendar = Calendar.getInstance();
	  		  
	  		  calendar.setTime(cDate);
	  		  int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	  		  return days;
	  	}
	  	

	  	public static String getOracleConstraintMessages(String errEessage){
	  		String message = null;
	  		if( errEessage != null ){
	  			if( errEessage.indexOf("UK_") > -1 )
	  				message = " Already Exist";
	  			else if( errEessage.indexOf("FK_") > -1 )
	  				message = " Reference Exist, Data can not be Deleted ";
	  		}
	  		return message;
	  	}


	  	public static void setFunctionalLocationPopupVal(HttpServletRequest request, HttpServletResponse response,
	  			BAL_FunctLocFieldNameBean functLocFieldNameBean,FormModes formMode) throws ServletException, IOException{
	  		
	  		
	  		BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean = new BAL_FunctLocHierarchyIdentBean();
	  		
	  		functLocHierarchyIdentBean.setFormMode(formMode);
	  		
	  		String companyId = request.getParameter("compId");
	  		String locationId = request.getParameter("locnId");
	  		String factoryId = request.getParameter("factId");
	  		String sbuId = request.getParameter("sbuId");
	  		String pbuId = request.getParameter("pbuId");
			String sectionId = request.getParameter("sectId");
			String cellId = request.getParameter("cellId");
			String machId = request.getParameter("machId");
			String flid = request.getParameter("flid");
			
			HttpSession userSession = request.getSession();
			
			String sessionFlid =(String )userSession.getAttribute("loginFlid");
			

			if (!BAL_UIUtils.isValidKeyId(flid) && !BAL_UIUtils.isValidKeyId(machId) &&
					!BAL_UIUtils.isValidKeyId(cellId) && !BAL_UIUtils.isValidKeyId(sectionId) &&
					!BAL_UIUtils.isValidKeyId(factoryId) && // !UIUtils.isValidKeyId(sbuId) &&
					!BAL_UIUtils.isValidKeyId(locationId) && !BAL_UIUtils.isValidKeyId(companyId)
					)
				flid =sessionFlid;
			
			
			companyId = BAL_UIUtils.isValidKeyId(companyId) == true ? companyId:"";
			locationId = BAL_UIUtils.isValidKeyId(locationId) == true ? locationId:"";
			factoryId = BAL_UIUtils.isValidKeyId(factoryId) == true ? factoryId:"";
			sbuId = BAL_UIUtils.isValidKeyId(sbuId) == true ? sbuId:"";
			pbuId = BAL_UIUtils.isValidKeyId(pbuId) == true ? pbuId:"";
			sectionId = BAL_UIUtils.isValidKeyId(sectionId) == true ? sectionId:"";
			cellId = BAL_UIUtils.isValidKeyId(cellId) == true ? cellId:"";
			machId = BAL_UIUtils.isValidKeyId(machId) == true ? machId:"";
			flid = BAL_UIUtils.isValidKeyId(flid) == true ? flid:"";
			
	  		//if( functLocFieldNameBean.getCompany() != null ){
	  		
	  			HtmlElementBean company = new HtmlElementBean();
	  			company.setName(functLocFieldNameBean.getCompany());
	  			company.setMandatory(functLocFieldNameBean.isCompMandatory());
	  			company.setValue(companyId);
	  			
	  			functLocHierarchyIdentBean.setCompany(company);
	  		//}
	  		//if( functLocFieldNameBean.getLocation()  != null ){
		  		
	  			HtmlElementBean location = new HtmlElementBean();
	  			location.setName(functLocFieldNameBean.getLocation());
	  			location.setMandatory(functLocFieldNameBean.isLocnMandatory());
	  			location.setValue(locationId);
	  			functLocHierarchyIdentBean.setLocation(location);
	  		//}
	  		
	  		//if( functLocFieldNameBean.getFactory() != null ){
		  		
	  			HtmlElementBean factory = new HtmlElementBean();
				factory.setName(functLocFieldNameBean.getFactory());
				factory.setMandatory(functLocFieldNameBean.isFactMandatory());
				factory.setDisable(functLocFieldNameBean.isFactDisable());
				factory.setValue(factoryId);
	  			functLocHierarchyIdentBean.setFactory(factory);
	  		
	  		//}
			
	  		/*	HtmlElementBean sbu = new HtmlElementBean();
	  			sbu.setName(functLocFieldNameBean.getSbu());
	  			sbu.setMandatory(functLocFieldNameBean.isSbuMandatory());
	  			sbu.setDisable(functLocFieldNameBean.isSbuDisable());
	  			sbu.setValue(sbuId);
	  			functLocHierarchyIdentBean.setSbu(sbu);
	  			
	  			HtmlElementBean pbu = new HtmlElementBean();
	  			pbu.setName(functLocFieldNameBean.getPbu());
	  			pbu.setMandatory(functLocFieldNameBean.isPbuMandatory());
	  			pbu.setDisable(functLocFieldNameBean.isPbuDisable());
	  			pbu.setValue(pbuId);
	  			functLocHierarchyIdentBean.setPbu(pbu);
	  			*/
	  		//if( functLocFieldNameBean.getSection() != null ){
	  			
				HtmlElementBean section = new HtmlElementBean();
				section.setName(functLocFieldNameBean.getSection());
				
				section.setMandatory(functLocFieldNameBean.isSectMandatory());
				section.setDisable(functLocFieldNameBean.isSectDisable());
				CommonFunctions.debugMsg(" section in UI Utils............"+sectionId);
				section.setValue(sectionId);
				functLocHierarchyIdentBean.setSection(section);
				
				//CommonFunctions.debugMsg("if sect is disabled   :::  "+functLocFieldNameBean.isSectDisable());
	  		//}
	  		//if( functLocFieldNameBean.getCell() != null ){
	  			
				HtmlElementBean cell = new HtmlElementBean();
				cell.setName(functLocFieldNameBean.getCell());
				cell.setMandatory(functLocFieldNameBean.isCellMandatory());
				cell.setDisable(functLocFieldNameBean.isCellDisable());
				cell.setValue(cellId);
				functLocHierarchyIdentBean.setCell(cell);
				
			//CommonFunctions.debugMsg(" cell Afterrrrrr"+cell);	
	  		//}
	  		
	  		//if( functLocFieldNameBean.getMachine() != null ){
	  			
				HtmlElementBean machine = new HtmlElementBean();
				machine.setName(functLocFieldNameBean.getMachine());
				machine.setMandatory(functLocFieldNameBean.isMachMandatory());
				machine.setDisable(functLocFieldNameBean.isMachDisable());
				machine.setValue(machId);
				functLocHierarchyIdentBean.setMachine(machine);
	  		//}

				HtmlElementBean functionalLocn = new HtmlElementBean();
				functionalLocn.setName(functLocFieldNameBean.getFunctionalLocId());
				functionalLocn.setValue(flid);				
				functLocHierarchyIdentBean.setFlid(functionalLocn);
			
			//String tablFields = "CELL=KznmCellid,FACT=KznmFactoryid";
			
			request.setAttribute("divId", request.getParameter("divId"));
			request.setAttribute("formId", request.getParameter("formId"));
			request.setAttribute("roleId", request.getParameter("roleId"));
			request.setAttribute("flid", flid);
			request.setAttribute("functLocHierarchyIdentBean", functLocHierarchyIdentBean);
			
			String load = request.getParameter("load");
			if( load != null && load.equals("true")){
				String url = request.getParameter("url");
				CommonFunctions.debugMsg("uiutils divid........"+request.getParameter("divId"));
				CommonFunctions.debugMsg("uiutils formId........"+request.getParameter("formId"));
				CommonFunctions.debugMsg("uiutils roleId........"+request.getParameter("roleId"));
				CommonFunctions.debugMsg("uiutils url........"+url);
				
				CommonFunctions.debugMsg("uiutils url........"+url);
				request.setAttribute("url", url);	
				forwardRequest(request, response, "loadFuntLoc.selectFuntLoc") ;
			}	
			else	
				forwardRequest(request, response, "getFuntLoc.selectFuntLoc") ;
	  	}

	  	
	  	public static String getActualDateForm(String date){
			if(  date != null )
				date = date.indexOf(Constants.passNullDate) > -1 ? "": date.length() > 11 ? date.substring(0, 11) : date;
			return date;	
		}

	  	public static void writeToExcel(HttpServletRequest request, HttpServletResponse response, String fileName, List<String[]> data){
	  		 HSSFWorkbook myWorkBook = new HSSFWorkbook();
	         HSSFSheet mySheet = myWorkBook.createSheet();
	         HSSFRow myRow = null;
	         HSSFCell myCell = null;
	         
	         
	         //fileName = getImagePath(request) +  fileName;
	         CommonFunctions.debugMsg(fileName);
	         for (int rowNum = 0; rowNum < data.size(); rowNum++){
	             myRow = mySheet.createRow(rowNum);
	             String [] rowData = data.get(rowNum);
	             for (int cellNum = 0; cellNum < rowData.length ; cellNum++){
	                 myCell = myRow.createCell(cellNum);
	                 myCell.setCellValue(rowData[cellNum]);     
	             }//http://localhost:8080/perfexitc/functionalLoc.commonFilter?divId=frmEmpPagebasefunLocationValues&formId=frmEmpPage&flid=FNL000000001&compId=CMP0000001
	         }
	         mySheet.setColumnHidden(0, true);
	         mySheet.setColumnWidth(1, 250);
	         myWorkBook.writeProtectWorkbook("admin", "admin");
	         mySheet.protectSheet("admin");
	         try{
/*	             FileOutputStream filout = new FileOutputStream(fileName);
	             myWorkBook.write(filout);
	             filout.close();
*/	             
	            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
	 			response.setHeader("Pragma", "no-cache");  
	 	        response.setHeader("Cache-Control", "no-cache");  
	 	        // Set the Header Info to tell the browser the format of the file.  
	 	        response.setHeader("Content-Type","application/vnd.ms-excel");  
	 	        ServletOutputStream out = response.getOutputStream();  

	 	        myWorkBook.write(out);
	 	        out.flush();
	 	        out.close();
  
	 	        
	         }catch(Exception e){ e.printStackTrace();}   
	  		
	         
	  	}
	  	
	  	public static JSONObject convertToJSON(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
	  		   
	  		   Class cls  = object.getClass();
			   String mName = null;
			   String pName = null;
			   Method []  method = cls.getMethods() ;
			   //Field []  fields = cls.getFields();
			   JSONObject jsonObject = new JSONObject();
			   Object o = null;
			   Class typeArgClass = null;
			   for( int i=0;i < method.length;i++ )
			   {
				   mName = method[i].getName();
				   if( (mName.startsWith("get") || mName.startsWith("is"))  &&  fieldDeclared(object, mName ) && ! mName.equals("getClass")  ){
					   pName = mName.substring( mName.startsWith("is")? 2:3 );
					   pName = pName.substring(0,1).toLowerCase() + pName.substring(1);
					   Type returnType = method[i].getGenericReturnType();
					   
					   if(returnType instanceof ParameterizedType){
						   ParameterizedType type = (ParameterizedType) returnType;
						   Type[] typeArguments = type.getActualTypeArguments();
				           List<?> retList = (List<?>)method[i].invoke(object, null);	
				           if( retList != null &&  retList.size() <= 0  )
				        	   continue;
						   for(Type typeArgument : typeArguments){
							   
							    if( typeArgument == null )
							    	continue;
							    if( ! typeArgument.toString().equals("?"))
							    	typeArgClass = (Class) typeArgument;
							    else
							    	typeArgClass = null;
							    
					        	JSONArray jsonArr = new JSONArray();
					        	
					        	
				        		if(  ( typeArgClass != null && ( isPredefinedType(typeArgClass ))) || (retList.get(0) == null || isPredefinedType( retList.get(0).getClass())) ){
					        		jsonObject.put(pName, JSONArray.fromCollection(retList) );
					        	}	
				        		else{
						        	for(Object listObj : retList ){
						        		JSONObject retJSONO = convertToJSON(listObj);
										if( retJSONO != null){
											jsonArr.put(retJSONO);
										}
						        	}
						        	if( jsonArr.length() > 0  ){
										jsonObject.put(pName,jsonArr);
									}
				        	   }	
						   }
					   }
					   else if( isPredefinedType((Class)returnType)) {
						   o = method[i].invoke(object, null); 
						   if( o != null)
							   jsonObject.put(pName, o);
					   }   
					   else{
						   o = method[i].invoke(object, null); 
						   if( o != null){
							   JSONObject retJSON = convertToJSON(o);
							   if( retJSON != null)
								   jsonObject.put(pName,retJSON );
						   }	   
					   }	   
				   }
			   }
			   return  jsonObject.isEmpty() ? null : jsonObject;
	  	}
	  	
	  	private static final Set<Class> WRAPPER_TYPES = new HashSet(
	  			Arrays.asList( Boolean.class, Character.class, Byte.class,
	  			Short.class, Integer.class, Long.class, Float.class, Double.class, String.class,
	  			Void.class, byte.class, int.class,char.class,short.class,boolean.class,long.class,double.class,float.class, Object[].class));
  		
	  	public static boolean isPredefinedType(Class clazz) {
	  		return WRAPPER_TYPES.contains(clazz);
  		   /* return clazz.equals(Boolean.class ) ||
  		    clazz.equals(String.class ) ||
            clazz.equals(Integer.class ) ||
            clazz.equals(Character.class) ||
            clazz.equals(Byte.class) ||
            clazz.equals(Short.class) ||
            clazz.equals(Double.class) ||
            clazz.equals(Long.class) ||
            clazz.equals(Float.class);
            */

  		}
	  	
	  	private static boolean fieldDeclared(Object object, String methodName ){
	  		
	  		Field [] fields = object.getClass().getDeclaredFields();
	  		if( methodName !=  null && methodName.length() > 3 ){
		  		String name = methodName.substring(methodName.startsWith("is")?2:3);
				name = name.substring(0,1).toLowerCase() + name.substring(1);
		  		for( Field field: fields )
		  		{	
		  			if( field.getName().equals(name)  )
		  				return  true;
		  		}
		  		Field [] superFields = object.getClass().getSuperclass().getDeclaredFields();
		  		for( Field field: superFields )
		  		{	
		  			if( field.getName().equals(name)  )
		  				return  true;
		  		}
	  		}
	  		return false;
	  	}
	  	
	  	 public static String getTitle(String elementType)
		 {	   
	  		 	String title = null;
		    	if(elementType.equals(DrillLevelConstants.COMP) || elementType.equals(DrillLevelConstants.COMPANY))
		    		title =  DrillLevelConstants.COMPHEADER;
		    	else if(elementType.equals(DrillLevelConstants.LOCN) || elementType.equals(DrillLevelConstants.LOCATION))
		    		title =  DrillLevelConstants.LOCNHEADER;
		    	else if(elementType.equals(DrillLevelConstants.FACT) || elementType.equals(DrillLevelConstants.FACTORY))
		    		title =  DrillLevelConstants.FACTHEADER;
		    	else if(elementType.equals(DrillLevelConstants.SECT) || elementType.equals(DrillLevelConstants.SECTION))
		    		title =  DrillLevelConstants.SECTHEADER;
		    	else if(elementType.equals(DrillLevelConstants.CEL) || elementType.equals(DrillLevelConstants.CELL))
		    		title =  DrillLevelConstants.CELHEADER;
		    	else if(elementType.equals(DrillLevelConstants.MCHM) || elementType.equals(DrillLevelConstants.MACHINE))
		    		title =  DrillLevelConstants.MCHMHEADER;
		    	else if(elementType.equals(DrillLevelConstants.ASSM))
		    		title =  DrillLevelConstants.ASSMHEADER;
		    	else if(elementType.equals(DrillLevelConstants.S))
		    		title =  DrillLevelConstants.SPRHEADER;
		    	else if(elementType.equals(DrillLevelConstants.SBU))
		    		title =  DrillLevelConstants.SBU;
		    	else if(elementType.equals(DrillLevelConstants.PBU))
		    		title =  DrillLevelConstants.PBU;
		    	else if(elementType.equals(DrillLevelConstants.PHEN) || elementType.equals(DrillLevelConstants.PHENOMENA))
		    		title =  DrillLevelConstants.PHNHEADER;
		    	else if(elementType.equals(DrillLevelConstants.CAS))
		    		title =  DrillLevelConstants.CASHEADER;
		    	else if(elementType.equals(DrillLevelConstants.ESG))
		    		title= DrillLevelConstants.ESGHEADER;
		    	else if(elementType.equals(DrillLevelConstants.SGP))
		    		title= DrillLevelConstants.SGPHEADER;
		    	else if(elementType.equals(DrillLevelConstants.QCAM) || elementType.equals(DrillLevelConstants.QUALITYCAUSE))
		    		title= DrillLevelConstants.CASHEADER;
		    	else if(elementType.equals(DrillLevelConstants.QPHM))
		    		title= DrillLevelConstants.PHNHEADER;
		    	else if(elementType.equals(DrillLevelConstants.QPOM) || elementType.equals(DrillLevelConstants.PROCESS))
		    		title= DrillLevelConstants.PROHEADER;
		    	
		    	return title;
		    	
		    }
	  	  public static  void  downloadFile(HttpServletResponse response,String fileName, String path ) throws FileNotFoundException,IOException{
			    FileInputStream fileToDownload = new FileInputStream(path + fileName);
			    ServletOutputStream output = response.getOutputStream();
			    if(fileName.endsWith(".pdf")){
			    	response.setContentType("application/pdf");
			    	
			    	//response.setHeader("Content-Disposition", "inline; filename="+ fileName);
			    }
			    else
			    	response.setContentType("application/txt");
			    
			    response.setHeader("Content-Disposition", "attachment; filename="+fileName);
			  
			    response.setContentLength(fileToDownload.available());
			    byte[] buf = new byte[1024];
			    int c;
			    while ((c = fileToDownload.read(buf)) >  0)
			    {
			    	output.write(buf, 0, c);
			    }
			    output.flush();
			    output.close();
			    fileToDownload.close();

		    }
		 
	  	public static List<String[]> transposeListArr(List<String[]> dataList)
		{
			//if( dataList.size() <=0 ) return null;
			List<String[]> transposeList = new ArrayList<String[]>();		
			for( int i =0; i<dataList.get(0).length-2; i++)
			{	
				String [] tRow = new String [ dataList.size()];
				for (int j=0; j<dataList.size();j++)
				{
					tRow [ j ]= dataList.get(j)[i].equals("0")?dataList.get(j)[i].replace("0", "-"):dataList.get(j)[i];
				}
				transposeList.add(tRow);
			}
			return transposeList;
		}
	  	/**Remove Blank for Transpose List
	  	 * @param hide **/
	  	public static List<String[]> transposeListArr(List<String[]> dataList, String removeChar, int fromCol, int toCol, int colHide)
		{
			//if( dataList.size() <=0 ) return null;
			List<String[]> transposeList = new ArrayList<String[]>();
			int len = dataList.get(0).length-colHide;
			removeChar =removeChar+","; 
			String [] rmValues = removeChar.split(",");
			
			for( int i =0; i<len; i++)
			{	
				String [] tRow = new String [ dataList.size()];
				boolean remove = true;
				for (int j=0; j<dataList.size();j++)
				{
					tRow [ j ]= dataList.get(j)[i];
					
					if(j > fromCol && j < toCol && remove &&  !checkValueExist(rmValues,tRow [ j ]) )
					{
						remove = false;
					}
				}
				if( !remove )
					transposeList.add(tRow);
			}
			return transposeList;
		}
	  	
	  	private static boolean checkValueExist(String [] valueList, String value){
	  		for(String s:valueList)
	  			if( s.equals(value)||(value != null && value.isEmpty())) return true;
	  				
	  		return false;
	  	}
	  	
	  	public static boolean isInteger(String value){
	  		return Pattern.matches("^\\d*$", value);
	  	}
	  	
	  	/*
	  	 * Sorts columns based on specified row in List<String[]>
	  	 * List<String[]> list
	  	 * list(0) = {'header', 'name', 'code', 'department','other'};
	  	 * list(1) = {'names',  'n1'  , 'c1'  , 'd1'        ,'o1'};
	  	 * list(2) = {'orderno','4'   , '2'   , '6'         ,'5'};
	  	 * After calling sortColDate(list,2,1,4);
		 * list(0) = {'header', 'code','name', 'other', 'department'};
	  	 * list(1) = {'names',  'c1'  ,'n1'  , 'o1'   , 'd1'};
	  	 * list(2) = {'orderno','2'   ,'4'   , '5'    , '6'};
	  	 *
	  	 */
	  	public static void sortColDataAsc(List<String[]> data, int basedOnRowNo,int startFromCol,int toCol){
	  		String [] row = null;
	  		String tmp =null;
	  		row =  data.get(basedOnRowNo);
	  		int len = row.length - toCol;
	  		for( int i = startFromCol ; i < len;i++){
	  			
	  			//firstValue = Double.parseDouble(row)
	  			for( int j = i+1;j<len;j++){
	  				
	  				if( Double.parseDouble(row[i]) >  Double.parseDouble(row[j]) ){
	  					for(int k = 0;k<data.size();k++){
	  						tmp = (String)data.get(k)[i];
	  						data.get(k)[i] = data.get(k)[j]; 
	  						data.get(k)[j] =tmp;
	  					}
	  					
	  				}
	  				
	  			}
	  		}
	  	}
	  	
	  	public static void sortColDataDesc(List<String[]> data, int basedOnRowNo,int startFromCol,int toCol){
	  		
	  		String [] row = null;
	  		String tmp =null;
	  		row =  data.get(basedOnRowNo);
	  		
	  		int len = row.length - toCol;
	  		
	  		for( int i = startFromCol ; i < len;i++){
	  			
	  			
	  			//firstValue = Double.parseDouble(row)
	  			for( int j = i+1;j<len;j++){
	  				
	  				if( Double.parseDouble(row[i]) <  Double.parseDouble(row[j]) ){
	  					for(int k = 0;k<data.size();k++){
	  						tmp = (String)data.get(k)[i];
	  						data.get(k)[i] = data.get(k)[j]; 
	  						data.get(k)[j] =tmp;
	  					}
	  					
	  				}
	  			
	  			}
	  			
	  		}
	  	}
	  	
	  	
	  	boolean isDouble(String str) {
	        try {
	            Double.parseDouble(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	  	public static void setDashBoardIdentifier(HttpServletRequest request,JSONObject chartObj){
	  		String dashboardIdnt = request.getParameter("dashboardIdent");
	  		if( BAL_UIUtils.isValidKeyId(dashboardIdnt));
	  			chartObj.set("dashboardIdent", dashboardIdnt);
	  	}
	  	
	  	
	  	public static DBActionTemplate getDBActionTemplate(HttpServletRequest request) throws ValidationExceptions {
	  		
	  		
	  		HttpSession userSession = request.getSession();
	  		
			String environmnet =(String )userSession.getAttribute("admDbEnvironment");
			String location =(String )userSession.getAttribute("admDbLocation");
			String dataSourceIdentifier = location+environmnet;
			
			DBActionTemplate  dbActionTemplate = null; 
			boolean dbActionTemplateExist = false;
			StringBuffer exceptionMsg =new StringBuffer();
			if( location != null && environmnet != null && isValidKeyId(environmnet) && isValidKeyId(location)){
				dataSourceIdentifier = dataSourceIdentifier.toLowerCase();
				dbActionTemplate = (DBActionTemplate)userSession.getServletContext().getAttribute("dbActionTemplate"+dataSourceIdentifier);
				if( dbActionTemplate != null)
				{	
					
					dbActionTemplateExist = true;
				}
			}else{
				location = request.getParameter("cmbLocation");
				environmnet = request.getParameter("cmbEnvironment");
				
				if( location == null )
					exceptionMsg.append("Location-required,");
				if( environmnet == null )
					exceptionMsg.append("Environment-required,");
			}
			
			if(!dbActionTemplateExist && location != null && environmnet != null){
				dataSourceIdentifier = location+environmnet;
				dataSourceIdentifier = dataSourceIdentifier.toLowerCase();
				try {
					dbActionTemplate = new DBActionTemplate( getDataSource(dataSourceIdentifier));
				} catch (NamingException e) {
					e.printStackTrace();
					throw new ValidationExceptions("Environment-invalid,");
				}catch(Exception e){
					e.printStackTrace();
					throw new ValidationExceptions("Environment-invalid,");
				}
				userSession.getServletContext().setAttribute("dbActionTemplate"+dataSourceIdentifier,dbActionTemplate);
					//dbActionTemplate.setDataSource(getDataSource(dataSourceIdentifier));
				userSession.setAttribute("admDbEnvironment", environmnet);
				userSession.setAttribute("admDbLocation", location);
				
			}
			
			if(exceptionMsg.length() > 0)
				throw new ValidationExceptions(exceptionMsg.toString());
			
			return dbActionTemplate;
	  	}
	  	
		public static OracleDataSource getDataSource(String dataSourceIdentifier) throws NamingException{
			
	
			  Context initContext = new InitialContext();
			  Context envContext = (Context) initContext.lookup("java:/comp/env");
			  CommonFunctions.debugMsg(" dataSourceIdentifier " + dataSourceIdentifier);
			  OracleDataSource ds = (OracleDataSource) envContext.lookup("jdbc/"+dataSourceIdentifier);
			  return ds;
		}
		public static String getlocation(HttpServletRequest request){
			HttpSession userSession = request.getSession(false);
			if( userSession == null)
				return null;
			
			String location =null;
			synchronized (userSession) {
				location =(String)userSession.getAttribute("admDbLocation");
			}
			return location;
		}
		
		public static Object getServiceObject(HttpServletRequest request, String className) throws ServiceObjectCreationException {
			try{
				
				HttpSession userSession = request.getSession();
				
				if( userSession == null)
					return null;
				
				DBActionTemplate dbActionTemplate = getDBActionTemplate( request);
				
				String environmnet = null, location =null;
				synchronized (userSession) {
					environmnet =(String )userSession.getAttribute("admDbEnvironment");
					location =(String )userSession.getAttribute("admDbLocation");
				}
				
				
				String identifier = location+environmnet;
				Object serviceObj = null;
				
				if( environmnet != null && location != null ){
					
					synchronized (userSession) {
						serviceObj = userSession.getServletContext().getAttribute(className+identifier);
						
						if( serviceObj == null && dbActionTemplate != null){
						//	Object [] arg = { dbActionTemplate };
						//	CommonFunctions.debugMsg(className);
							Class<?> clazz = Class.forName("com.akranta.tpm.service.impl."+className);

							
							Constructor<?> ctor = clazz.getConstructor(DBActionTemplate.class);
							//unctions.debugMsg(" className 22 " + ctor.newInstance(new Object[] { dbActionTemplate }));
							serviceObj = ctor.newInstance(new Object[] { dbActionTemplate });
							//serviceObj =  Class.forName(className).getConstructor(String.class).newInstance(arg);
							
							userSession.getServletContext().setAttribute(className+identifier,serviceObj);
						}	
						//CommonFunctions.debugMsg( "dbActionTemplate " + dbActionTemplate.getDataSource().getUser());
					}
				}
				return serviceObj;
				
			}catch(IllegalArgumentException e){
				CommonFunctions.debugMsg("IllegalArgumentException " +e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
				
			}catch(SecurityException e){
				CommonFunctions.debugMsg("SecurityException " +e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}catch(InstantiationException e){
				CommonFunctions.debugMsg("InstantiationException " +e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}catch(IllegalAccessException e){
				CommonFunctions.debugMsg("IllegalAccessException " +e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}catch(InvocationTargetException e){e.printStackTrace();
				CommonFunctions.debugMsg("InvocationTargetException " +e.getLocalizedMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}catch(NoSuchMethodException e){
				e.printStackTrace();
				CommonFunctions.debugMsg("NoSuchMethodException " +e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}catch(ClassNotFoundException e){
				CommonFunctions.debugMsg("ClassNotFoundException " +e.getMessage());
				CommonFunctions.debugMsg(e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}catch(ValidationExceptions e){
				CommonFunctions.debugMsg("ValidationExceptions " +e.getMessage());
				throw new ServiceObjectCreationException(e.getMessage());
			}
		
		}
		
		public static String removeDefaultDate(String dateStr,String indexValue)
		{
			String date = "";  
			if(isValidDate(dateStr))
			{
				int dtePos = dateStr.indexOf(indexValue);				
							
				if(dtePos>0)
					date = dateStr.substring(0, dtePos);				
				else
					date = "";
				
				if(date.equals(Constants.passNullDate) || date.equals(Constants.futureNullDate))
					date = "";				
			}
			return  date;		
		}
		
		
		public static void removeBlankRowExcel(CommonFilter commonFilter,JSONObject tblJSONObj,int fromCol,int toCol,String removeVal)
		  {
			JSONObject removeBlank = new JSONObject();
			if( "Y".equals(commonFilter.getRemoveBlank() )){		
				 
				removeBlank.put("fromCol", fromCol);
				removeBlank.put("toCol", toCol);
				removeBlank.put("value", removeVal);
				tblJSONObj.put("removeRow",removeBlank);
			}

		  }
		 
		public static void dashBoardSetChartObject(HttpServletRequest request,JSONObject chartObj )
		{
			String dashboardIdnt = request.getParameter(ReqtParamNameConst.DASHBOARDIDENT);
			if( BAL_UIUtils.isValidKeyId(dashboardIdnt));
				chartObj.set(ReqtParamNameConst.DASHBOARDIDENT, dashboardIdnt);
		}

		public static String valueTooLongExceptions(String Exception, String string2) {
			// TODO Auto-generated method stub
			
			String colName = Exception.substring(Exception.indexOf("."),Exception.indexOf( " (actual:"));
			colName = colName.substring(colName.lastIndexOf("_"));
			colName = colName.substring(1);
			
			String maximum = Exception.substring(Exception.indexOf("maximum"),Exception.length()-3);
			return maximum +" is length for Column "+colName;
		}
		public static String displayENTCode(HttpServletRequest request) throws ValidationExceptions{
			
			String location = BAL_UIUtils.getlocation(request);
			String retData = null;
			DBActionTemplate dbActionTemplate = getDBActionTemplate(request);
			String sql = "select CNFM_KEYID  from "+TableNames.TBL_ADM_TL_CONFIGURATIONMST +" where CNFM_CODE = 'ENTCODETRUE'";
			try {
				String codeDisplaytrue = dbActionTemplate.getSingleValue(sql);
				//CommonFunctions.debugMsg("codeDisplaytrue  :"+codeDisplaytrue);
				if( BAL_UIUtils.isValidKeyId(codeDisplaytrue)){
					retData = BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.EntCodeDisplay","EntCode-False");
				}
				else{
					retData = BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.EntCodeDisplay","EntCode-True");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return retData;
		}
		
//for tam
		public static List<GenTlTeamDoucmentLink> getTeamDetails(HttpServletRequest request ) {
			
			String dateTime = CommonFunctions.dateTimeNow();
			AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
			
			//CommonFunctions.debugMsg("user.getUsrm_ccno()"+user.getUsrm_ccno());
			
			String teamIds = request.getParameter("cmbTmdlTeamid");
			String empType = request.getParameter("hdnEmpType");
			
			if (empType ==null) 
				empType ="R";
			
			if (teamIds==null) 
				return null;
			
			//CommonFunctions.debugMsg("teamIds=="+teamIds);
			if (!BAL_UIUtils.isValidKeyId(teamIds))
				teamIds = request.getParameter("hdnSelectedTeamIds");
			
			//CommonFunctions.debugMsg("teamIds=="+teamIds);
			
			List<GenTlTeamDoucmentLink> teamDocLink =new ArrayList<GenTlTeamDoucmentLink>();
			
	    	teamIds=teamIds.replace("[", "").replace("]", "");
	    	
	    	String teamArr[]=teamIds.split(",");

			//CommonFunctions.debugMsg("teamArr.length=="+teamArr.length);

	    	if (!teamIds.equals("")) {
		    	for (int i=0;i<teamArr.length;i++) {
		    		GenTlTeamDoucmentLink genTlTeamDoucmentLink = new GenTlTeamDoucmentLink();
		    		genTlTeamDoucmentLink.setTmdlTeamid(teamArr[i]);
		    		genTlTeamDoucmentLink.setTmdlEmptype(empType);
		    		genTlTeamDoucmentLink.setTmdlTemp2("{}");
		    		genTlTeamDoucmentLink.setTmdlTemp3("{}");
		    		genTlTeamDoucmentLink.setTmdlCreatedby(user.getUsrm_ccno());
		    		genTlTeamDoucmentLink.setTmdlCreatedon(dateTime);
		    		genTlTeamDoucmentLink.setTmdlModifiedon(dateTime);
		    		teamDocLink.add(genTlTeamDoucmentLink);
		    	}	    		
	    	}		
			return teamDocLink;			
		}

		public static void writeFunctionlocnHirearchy(HttpServletResponse response, String funcHierarchy)throws IOException {

			//response.setContentType("text/html;charset=UTF-8");
			 PrintWriter out = response.getWriter(); 	
			 //CommonFunctions.debugMsg("funcHierarchy...."+funcHierarchy);
			 if(isValidKeyId(funcHierarchy)){
				 String[] funcLocnVal = funcHierarchy.split("-");
				 int index = 0;
				 JSONObject jsonObject = new  JSONObject();
				 if(funcLocnVal.length > index )
					 jsonObject.put("company",funcLocnVal[index++]);
				 if(funcLocnVal.length > index)
					 jsonObject.put("location",funcLocnVal[index++]);
				 if(funcLocnVal.length > index)
					 jsonObject.put("factory",funcLocnVal[index++]);
				/* if(funcLocnVal.length > index)
					 jsonObject.put("sbu",funcLocnVal[index++]);
				 if(funcLocnVal.length > index)
					 jsonObject.put("pbu",funcLocnVal[index++]);
			*/	 if(funcLocnVal.length > index)
					 jsonObject.put("section",funcLocnVal[index++]);
				 if(funcLocnVal.length > index)
					 jsonObject.put("cell",funcLocnVal[index++]);
				 if(funcLocnVal.length > index)
					 jsonObject.put("machine",funcLocnVal[index++]);
			     //JSONArray jsonObject = JSONArray.fromObject(machineHirerachy);
				 
				 /*jsonObject.put("factory", funcHierarchy.get(0)[0]);
				 jsonObject.put("section",funcHierarchy.get(0)[1]);
				 jsonObject.put("cell",funcHierarchy.get(0)[2]);
				 jsonObject.put("circle",funcHierarchy.get(0)[3]);
				 jsonObject.put("eqpGroup",funcHierarchy.get(0)[4]);
				 jsonObject.put("costcenterid",funcHierarchy.get(0)[5]);
				 jsonObject.put("company",funcHierarchy.get(0)[6]);
				 jsonObject.put("location",funcHierarchy.get(0)[7]);*/
				 JSONObject eqphirerachy = new  JSONObject();
				 eqphirerachy.put("flHirerachy", jsonObject);
			     System.out.println(eqphirerachy);
		         out.print(eqphirerachy);
			 }
		}

 
  
		public static void openFile(HttpServletRequest request) throws IOException {
			String path = request.getServletContext().getRealPath("CommonFilterServlet");
			String fileName = request.getParameter("fileName");
	  		path = path.replace("CommonFilterServlet","WEB-INF/prototypetemplate").replace("\\","/") ;
	  		launchFile(path+"/"+fileName);
	  		/*File document = new File(path+"/"+fileName);
		    Desktop dt = Desktop.getDesktop();
		    dt.open(document);
		    */
		}
		
		public static void launchFile(String url){
	        String os = System.getProperty("os.name").toLowerCase();
	        Runtime rt = Runtime.getRuntime();
	        //Process p = new Process();
	        try {
	            if (os.indexOf("win") >= 0) {
	               //p =
	            	   rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        //return p;
	    }
 		
		public static ComboFilter fillComboFilter(HttpServletRequest request) {
			// TODO Auto-generated method stub
			ComboFilter comboFilter = new ComboFilter();
			String showMode = request.getParameter("mode");
			String filter = request.getParameter("q");
			String combokey = request.getParameter("combokey");
			String ifGetCol = request.getParameter("getCol");
			if("grid".equals(showMode )){
			   comboFilter.setIsGetCol(ifGetCol);
				GridParams gridparams = new GridParams();
				FilterValues.populateGridParams( request, gridparams);
				/*CommonFunctions.debugMsg(" frmRow  "+gridparams.getFromRow()+
				"  toRow  "+gridparams.getToRow()+
				"  totalrecor  "+gridparams.getTotalRecordCnt() );
				*/
				comboFilter.setGridparam(gridparams);
				String pageCnt = request.getParameter("page");
				String rowsStr = request.getParameter("rows");
				//CommonFunctions.debugMsg("rowsStr  "+rowsStr);
				//CommonFunctions.debugMsg("combokey  "+combokey);
				//CommonFunctions.debugMsg("ShowMode  "+showMode);
				if(!BAL_UIUtils.isValidKeyId(pageCnt))
					pageCnt = "1";	
				if(!BAL_UIUtils.isValidKeyId(rowsStr))
					rowsStr = "1";
				
				comboFilter.setPage(pageCnt);
				comboFilter.setRows(rowsStr);
			}
			
			comboFilter.setMode(showMode);
			comboFilter.setId(combokey);
			/*comboFilter.setFromRow(gridparams.getFromRow());
			comboFilter.setToRow(gridparams.getToRow());
			comboFilter.setGridFilter(gridparams.getGridFilters());*/
			comboFilter.setCode(filter);
			comboFilter.setName(filter);
			CommonFunctions.debugMsg("filter  "+filter);
			return comboFilter;
		}	
public static void writeComboBox(HttpServletResponse response,List<ComboBox> comboList, ComboFilter comboFilter ) throws IOException
	{
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter(); 	
	     JSONArray jsonObject = new JSONArray();
	     JSONObject jsonGridData= new JSONObject();
	     JSONObject jsonGridCol= new JSONObject();
	   //  CommonFunctions.debugMsg("TotalRecords  "+comboFilter.getGridparam().getTotalRecordCnt());
	     //CommonFunctions.debugMsg("ModeMode  "+comboFilter.getMode());
	     
	     if(BAL_UIUtils.isValidKeyId(comboFilter.getMode())){
	    	 if("true".equals( comboFilter.getIsGetCol())){
	    		 jsonGridCol = getcomboTableModel(comboList);
	    		 out.print(jsonGridCol);
	    	 }
	    	 else{
	    		 jsonGridData  = convertComboToGrid(comboList,comboFilter);
	    		 out.print(jsonGridData);
	    	 }
	    	 
	    	 System.out.println(jsonGridData);
	     }
	     else{
	    	 jsonObject = JSONArray.fromObject(comboList);
	    	 out.print(jsonObject);
	    	 System.out.println(jsonObject);
	     }
	    	 
	     
         out.flush();
         out.close();
	}
	private static JSONObject getcomboTableModel(List<ComboBox> comboList) {
		// TODO Auto-generated method stub
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		 //CommonFunctions.debugMsg("comboList.size   :"+comboList.size());
		String [] colHeader =  comboList.get(0).getColumns() ; 		
		//String [] colHeader1 = headers.get(2);
		colHeader[0] = "";
		jqGridTableModel.getRowHeaders().add(colHeader);
		 
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			 
			jqGridTableModel.setTableButton(false);		
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridColModel.setWidth(200);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0  )
			{
				jqGridColModel.setFormatter("chkbxCmbFormatter");
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("center");
			}
			if(i == 1 )
			{
				jqGridColModel.setHidden(true);
			}
			if(i == 2 )
			{
				jqGridColModel.setWidth(350);
			}
			//CommonFunctions.debugMsg("colHeader["+i+"] "+colHeader[i]);
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
			JSONObject tableModel = BAL_UIUtils.getJqGridTableModel(jqGridTableModel);		 
			tableModel.set("tableHeight", "84%%");
			tableModel.set("tableWidth", "50%%");
		 return tableModel;
	}

	/*Added By MANIKANDAN for combo data to appear in grid*/
	private static JSONObject convertComboToGrid(List<ComboBox> comboList,ComboFilter comboFilter )
	  	{
	   		
		 String pageStr = comboFilter.getPage();
		 String rowsStr= comboFilter.getRows();
	  		JSONObject ComboGridDataObject = new JSONObject();
	  		long totalRecords = comboFilter.getGridparam().getTotalRecordCnt();
	  		//String [] colHeader =  ;
			int rows = 100;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			ComboGridDataObject.put("page",page); //current page
	  		ComboGridDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
	  		ComboGridDataObject.put("records", totalRecords); //total records
			System.out.println(" totalRecords " + totalRecords);
			System.out.println(" colHeaderlength " + comboList.get(0).getColumns().length);
			System.out.println(" colHeaderlength " + comboList.size());
			
			JSONArray rowArr = new JSONArray(); 
			int rowStart = 1;
			int rowid = rows * (page-1);
			int slno = 0;
			String[] colData ;
			for(int i = 0 ; i<comboList.size();i++){
				if( slno++ >= rowStart )
	        	{
			  		JSONObject rowObj =new JSONObject();
				    rowObj.put("id",++rowid);
				    colData=comboList.get(i).getColumns(); 
				    JSONArray cell = new  JSONArray();
				    for(int j=0;j<colData.length;j++){
						  cell.put( colData[j]);
		            }
			    rowObj.put("cell",cell);
		        rowArr.put(rowObj);
			}
	  		/*for(ComboBox tpmModel: comboList)
	  		{
	  			if( slno++ >= rowStart )
	        	{
		  		JSONObject rowObj =new JSONObject();
			    rowObj.put("id",++rowid);
			     //CommonFunctions.debugMsg("value "+rowid+ " - "+ComboBox.class.getDeclaredFields().toString());
			 //   CommonFunctions.debugMsg("rowObj   :"+rowid);
		        JSONArray cell = new  JSONArray();
		        cell.put( tpmModel.getId());
		        cell.put("");
		        cell.put("");
		        cell.put(tpmModel.getText());
		       
		        rowObj.put("cell",cell);
		        rowArr.put(rowObj);
			}*/
			 
	  		//CommonFunctions.debugMsg("rowArr.length  :"+rowArr.length());
	  		}
	  		ComboGridDataObject.put("rows", rowArr);
	  		comboList =null;  
	  			 //CommonFunctions.debugMsg("ComboGridDataObject="+ComboGridDataObject.toString());
		        return ComboGridDataObject;
	   	}
	/*Added by Manikandan for deleting temp imagefile delete*/
	 
	public static void delete(File file)
    {
        if(file.isDirectory())
        {
            String fileList[] = file.list();
            if(fileList.length == 0)
            {
                System.out.println("Deleting Directory : "+file.getPath());
                file.delete();
            }else
            {
                int size = fileList.length;
                for(int i = 0 ; i < size ; i++)
                {
                    String fileName = fileList[i];
                    System.out.println("File path : "+file.getPath()+" and name :"+fileName);
                    String fullPath = file.getPath()+"/"+fileName;
                    File fileOrFolder = new File(fullPath);
                    System.out.println("Full Path :"+fileOrFolder.getPath());
                    delete(fileOrFolder);
                }
            }
        }else
        {
            System.out.println("Deleting file : "+file.getPath());
            file.delete();
        }
    }
	//GIVEN BY B.KARTHIKEYAN 14-MAR-2014
	public static JSONObject getXlColModel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String colModelStr = request.getParameter(ReqtParamNameConst.JQGridColModel);
	    if(colModelStr == null ){
	    	response.getWriter().print("Error P-EXCOL01! Please Try Again ! ");
	    	throw new IOException("Error P-EXCOL01 ! Please Try Again ");
	    }
	    return JSONObject.fromString(colModelStr);
	}
	
	public static List<?> convertJSONArrToListNoConv(Object beanObject, JSONArray inPutJSONArray   ) 
	{
		 try{
			  Class cls  = beanObject.getClass();
			  Method [] methods 	= cls.getDeclaredMethods();
			  						  	
			  Class<?> [ ] methodType = new Class[] { String.class };
			  //int i=0;
			  List<Object> resultObjects = new ArrayList<Object>();
			  Method method;
			  for( int i = 0;i< inPutJSONArray.length();i++)
			  {
				  JSONObject rowData = (JSONObject)inPutJSONArray.get(i);
				  JSONArray names = rowData.names();
				  boolean canAdd = false;
				  Object newObject  = cls.newInstance();
				  for( int j = 0; j< names.length();j++ )
				  {
					  String propertyName = names.getString(j);
					  String paramValue = rowData.getString(propertyName);
					 
					  if( propertyName.length() < 4  && paramValue != null && ! paramValue.trim().isEmpty())
						  continue;
					  
					  if( paramValue != null && ! paramValue.isEmpty() &&  paramValue.trim().length() > 0 )
					  {	  
						  //paramValue = paramValue.toUpperCase();
						  
						  String pName = propertyName.substring(3);
						  char u = Character.toUpperCase(pName.charAt(0));
						  pName = u+pName.substring(1);
						  String methodName = "set" +pName;

						   //System.out.println("------------->"+ propertyName +  " : " +methodName+" - "+ paramValue );
 
						  for(int k =0; k<methods.length;k++ )  
						  {
							 
							  if( methods[k].getName().equals(methodName)  )
							  {	  
								  
								  try {
									    //System.out.println("Invoke ------------->"+ methods[k].getName() + " - "+ paramValue );
									  	methods[k].invoke(newObject,paramValue ) ;
										canAdd = true;
										
								  } catch (IllegalArgumentException e) {
										e.printStackTrace();
								  } catch (IllegalAccessException e) {
										e.printStackTrace();
								  } catch (InvocationTargetException e) {
										e.printStackTrace();
								  } catch (SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								  }
								  break;
							  }	  
						  }
					  }
					  
				  }
				  if( canAdd )
					  resultObjects.add(newObject);
			  }	  
			 
			  return resultObjects;
		 }catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	public static Object setBeanPropertiesNoConv(Object beanObject, HttpServletRequest request )
	{
		  Class cls  = beanObject.getClass();

		 // String methodeName = "get"+propertyName;
		  //Method  method = cls.getDeclaredMethod(methodeName,  null) ;
		  Method [] methods 		= cls.getMethods();
		  Enumeration<String> params = request.getParameterNames();
		  int i=0;
		  while( params.hasMoreElements())
		  {
			  String reqParam = params.nextElement();
			//  System.out.println("reqParam     "+reqParam);
			  if( reqParam.length() < 4 )
				  continue;
			  
			  String paramValue = request.getParameter(reqParam);
			 // System.out.println("paramValue     "+paramValue);
			  if( paramValue != null && ! paramValue.isEmpty() &&  paramValue.trim().length() > 0 )
			  {	  
				 // System.out.println("paramValue " + paramValue);
				  
				  //paramValue = reqParam.toLowerCase().startsWith("img") ? paramValue: paramValue.toUpperCase();
				  
				  String pName = reqParam.substring(3);
				  char u = Character.toUpperCase(pName.charAt(0));
				  pName = u+pName.substring(1);
				  String methodName = "set" +pName;
				  System.out.println("methodName " + methodName+" : "+paramValue);
				  for(i =0; i<methods.length;i++ )  
				  {
					  if( methods[i].getName().equals(methodName)  )
					  {	  
						    //Class[] paramTyArr = methods[i].getParameterTypes();
							try {
								methods[i].invoke( beanObject,paramValue ) ;
							} catch (IllegalArgumentException e) {
							} catch (IllegalAccessException e) {
							} catch (InvocationTargetException e) {
							}
						    break;
					  }
				  }	  
			  }	  
		  }
		  
		  return beanObject;
	}
	
	//22-jul-2014
public static NotesMailClient getNotesMailClientObj(HttpServletRequest request,HttpServletResponse response){
		
		NotesMailClient notesMailClient = (NotesMailClient)request.getServletContext().getAttribute("NotesMailClient");
		if( notesMailClient == null){
			notesMailClient = new NotesMailClient(); 
			request.getServletContext().setAttribute("NotesMailClient",notesMailClient);
		}
		return notesMailClient;
	}
	
	public static void sendLotusNotesMail(HttpServletRequest request,
			HttpServletResponse response,String toAddress, 
				String ccs,String subject,String content, String fileName) throws Exception{
		NotesMailClient notesMailClient = getNotesMailClientObj(request,response);
	//	notesMailClient.send(toAddress,ccs,subject,content,fileName);
	}
	public static boolean isValidEmail(String emailId){
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return emailId.matches(EMAIL_REGEX);
		
	}
	
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	    return sdf.format(cal.getTime());

	  }

	
	public static String getAppPath(HttpServletRequest request){

  		String imgPath = request.getServletContext().getRealPath("CommonFilterServlet");
		
		imgPath = imgPath.replace("CommonFilterServlet","").replace("\\","/").replace("[]","");
		
		return imgPath;
  	}
	
	public static void bindAppExcetionMsg(HttpServletResponse response,Exception e, String errMsg ) throws IOException{
		JSONObject error = new JSONObject();
		error.put("exception", true);
		error.put("errMsg", errMsg);
		error.put("errDtl",e.getLocalizedMessage());
		response.getWriter().print(error);
	}
	
	public static void bindAppExcetionMsg(HttpServletResponse response,JSONObject errMsg ) throws IOException{
		JSONObject error = new JSONObject();
		error.put("exception", true);
		error.put("errMsg", errMsg);
		response.getWriter().print(error);
	}
	
	public static String getUserLoginElementId(HttpServletRequest  request){
		
		return (String)request.getSession().getAttribute("loginElementid");
	}

}
