package com.akranta.tpm.utils;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPMessage;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.SAPExceptions;
import com.akranta.tpm.bean.SapQueueBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.service.BreakdownService;
import com.akranta.tpm.service.impl.BreakdownServiceImpl;
import com.akranta.tpm.upload.ObjectFactory;
import com.akranta.tpm.upload.UploadData;
import java.util.Base64;

public class SOAPClient {
	
	
	private static String ConfigTblName = "ADM_TL_EXTERNALSYSTEM_CONFIG";
	private static String TBL_SAP_TL_PERFEXMAPPINGMST = "SAP_TL_PERFEXMAPPINGMST";
	private static String TBL_SAP_TL_PERFEXMAPPINGDTL = "SAP_TL_PERFEXMAPPINGDTL";
	
	
	private static String SAP_SYSTEM_CODE = "ERP_SAP";
	private String userName;
	private String password;
	private boolean useSAPField = true;
	static BreakdownService bdService;
	 static BdmTlDtl newBdmTlDtl;
	 static BdmTlDtl existBdmTlDtl;
	
	String filePath;
	private  static DBActionTemplate dbActionTemplate;
	/* Constructor to set the Object for database activity and to get the authentication information from db*/
	public SOAPClient(DBActionTemplate dbActionTemplate) throws SAPExceptions{
		this.dbActionTemplate = dbActionTemplate;
		setAuthenticationInfo();
	}
	
	/* Set the file path where the generated and return xml files need to store*/
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	/*  Get the authentication details such as username and password from db table ADM_TL_EXTERNALSYSTEM_CONFIG
	 *  and store into instance variable 
	 *  and if no authentication found throws SAPExceptions, user defined exception with error
	 *  code ERROR_CODE_NOAUTHENTICATION_FOUND
	 */
	private void setAuthenticationInfo() throws SAPExceptions{
		String sql = "Select EXSC_USERNAME,EXSC_PASSWORD from "+ ConfigTblName +" WHERE EXSC_SYSTEMCODE='"+SAP_SYSTEM_CODE+"'";
		try{
			CommonFunctions.debugMsg("  sql 0000" + sql);
			List<String[]> data =  dbActionTemplate.getDataList(sql);
			this.userName = data.get(0)[0];
			this.password = data.get(0)[1];
		}catch(Exception e){
			throw new SAPExceptions(SAPExceptions.ERROR_CODE_NOAUTHENTICATION_FOUND, e);
		}
	}
	/* This method is used to submit the data to configured SAP webservice.
	 * It generates the xml data by using processCode and transId, and 
	 * return data stores back into configured tables based on processcode and transId 
	 * 
	 * @processCode: process code of the web service scenario 
	 * @transId : unique value to identify the record to send or submit to web service
	 * Exceptions 
	 * $SAPExceptions : throws when no web service is configured for the process code,it is a userdefined exception
	 *                 	Exception code is ERROR_CODE_NOMAPPING_FOUND
	 * $SOAPException : throws when not able to create SOAP connection
	 * $IOException   : throws when IO Operation fails such as file not created etc                  
	 */
	public void sumbitToSAP(String processCode, String transId ) throws SAPExceptions, SOAPException, IOException{
		 
		 SAPMappingMst sapMappingMst = getConfigData(processCode);
		 
		 SOAPMessage soapMessage = createSOAPMesage(sapMappingMst,transId);
		 //try{
			 CommonFunctions.debugMsg(" filePath " + filePath);
			// SOAPMessage response = sendSOAPMessage(soapMessage,sapMappingMst.getSpmmWebservicename());
			 
			 FileOutputStream out = new FileOutputStream(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
			// response.writeTo(out);
			 SOAPMessage response=null;
				try {
					MessageFactory factory = MessageFactory.newInstance();
					InputStream is = new FileInputStream(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
					response = factory.createMessage(new MimeHeaders(), is);
					//response = getSOAPMesgDataObject(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
					readResponse(response,sapMappingMst);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// readResponse(response,sapMappingMst);
			 
			 try {
				readResponse(response,sapMappingMst);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		
	}
	/* This method is used to submit the data to configured SAP webservice.
	 * It generates the xml data by using configuration and transId, and 
	 * return or received  data from web service stores back into configured tables based on 
	 * configuration and transId 
	 * 
	 * @SAPMappingMst: configuration Object 
	 * @transId : unique value to identify the record to send or submit to web service
	 * Exceptions 
	 * $SAPExceptions : throws when no web service is configured for the process code,it is a userdefined exception
	 *                 	Exception code is ERROR_CODE_NOMAPPING_FOUND
	 * $SOAPException : throws when not able to create SOAP connection
	 * $IOException   : throws when IO Operation fails such as file not created etc                  
	 */
	
	public String sumbitToSAP(SAPMappingMst sapMappingMst, String transId,String processCode ) throws SAPExceptions, SOAPException, IOException{
		 
		String resMsg = "";
		 	 SOAPMessage soapMessage = createSOAPMesage(sapMappingMst,transId);
		 //try{
			 CommonFunctions.debugMsg(" filePath " + filePath);
			 SOAPMessage response = sendSOAPMessage(soapMessage,sapMappingMst.getSpmmWebservicename());
			 
			 FileOutputStream out = new FileOutputStream(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
			 response.writeTo(out);
			 try {
				 resMsg = readResponse(response,sapMappingMst);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// readResponse(response,sapMappingMst);
			 
			 
		/* }catch(MalformedURLException ex){
			 
		 }catch(SOAPException e){
			 
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
			
			return resMsg;
	}
	
	public String sumbitToSAPTest(SAPMappingMst sapMappingMst, String transId,String processCode ) throws SAPExceptions, SOAPException, IOException{
		SOAPMessage response=null;
		String resMsg = "";
	  SOAPMessage soapMessage = createSOAPMesage(sapMappingMst,transId);
	 //try{
		 CommonFunctions.debugMsg(" .......filePath " + filePath);
		  response = sendSOAPMessage(soapMessage,sapMappingMst.getSpmmWebservicename());
		    
		 FileOutputStream out = new FileOutputStream(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
		 response.writeTo(out);
		//String fileName="C:/Users/akranta-42/Desktop/bal/retmsg_BD_ORDER_CREATE.xml";
		
		try {
			MessageFactory factory = MessageFactory.newInstance();
			InputStream is = new FileInputStream(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
			response = factory.createMessage(new MimeHeaders(), is);
			//response = getSOAPMesgDataObject(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
			resMsg=testReadResponse(response,sapMappingMst,transId,processCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		 
		 
	/* }catch(MalformedURLException ex){
		 
	 }catch(SOAPException e){
		 
	 } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 */
		return resMsg;
}
	
	private static String  testReadResponse(SOAPMessage message,SAPMappingMst sapMappingMst,String transId,String processCode) throws BusinessApplicationExceptions, Exception {
		String msgStr=""; 
		CommonFunctions.debugMsg(" Processing Request .." );
		try{
		SOAPBody retBody = message.getSOAPBody();
		CommonFunctions.debugMsg(" retBody " + retBody);
		try{
		if( retBody.hasFault()){
			SOAPFault fault = retBody.getFault();
			throw new SOAPException("Fault When processing request......... "+fault.getFaultReasonTexts());
		}	
		}catch(SOAPException e){
			e.printStackTrace();
			
		}
		// msgStr = getMsgAsString(message);
		CommonFunctions.debugMsg(msgStr+"         Message String In the SAOP Clint ");
		 
		CommonFunctions.debugMsg(" No Fault Processing Request .." );
		//Iterator returnElements = retBody.getChildElements(); //create an iterator on elements
		//int counter=0;
		CommonFunctions.debugMsg( " retBody " + retBody.toString());
		
		String status=retBody.getElementsByTagName("TYPE").item(0).getTextContent();
		 msgStr=retBody.getElementsByTagName("MESSAGE").item(0).getTextContent();
		StringBuffer stb= new StringBuffer();
		String sql="";
		//	String stats=null;
		//SapQueueBean sap=new SapQueueBean();
		//sap.setTxtMessage(messages);
		//sap.setTxtStatus(status);
		
		CommonFunctions.debugMsg(" ---------- " +retBody.getElementsByTagName("TYPE").item(0).getTextContent());
		CommonFunctions.debugMsg(" ---------- " +msgStr);
		CommonFunctions.debugMsg(" ---------- " +retBody.getElementsByTagName("item").item(0).getTextContent());
		String stats="";
		 // bdService = (BreakdownServiceImpl)UIUtils.getServiceObject(request,"BreakdownServiceImpl");
		//newBdmTlDtl=new BdmTlDtl();
		if(status.equalsIgnoreCase("S")){
		//	newBdmTlDtl.setBdanErppoststatus("C");
			stats="C";
		}
		else
			stats="X";
    		//newBdmTlDtl.setBdanErppoststatus("X");

			//newBdmTlDtl .setBdanErpnumber(transId);
		
			//existBdmTlDtl = bdService.updateErpStatus(newBdmTlDtl,existBdmTlDtl );				
			if(processCode.equals("GEN_MAINTENANCE_EQUIPMENT")){
				sql="update PLM_TL_GENMAINTENANCE Set GMNT_ERPNUMBER='"+transId+"',GMNT_ERPPOSTSTATUS='"+stats+"' where GMNT_KEYID='"+transId+"'";

			}
			else if(processCode.equals("BD_ORDER_CREATE")){
			 sql="update BDM_TL_DTL Set BDAN_ERPNUMBER='"+transId+"',BDAN_ERPPOSTSTATUS='"+stats+"' where BDAN_BDMS_KEYID='"+transId+"'";
			}
					
		dbActionTemplate.executeStatement(sql);
		
		CommonFunctions.debugMsg("SQl Query In Client"+sql);
		//retBody.getElementsByTagName("type").item(0).getFirstChild().getNodeValue(); 
		//return;
	}catch(Exception e){
		e.printStackTrace();
	}
		//throw new SOAPException("Success Msg "+msgStr);
		//return msgStr;
	 return msgStr;
	}
	/* xml data (SOAP Message)is generating based on the JSONObject 
	 * and the configuration information such as web service  URI is from data base, 
	 * and the data received from web service is again convert to JSON Object and returned 
	 */
	public org.json.JSONObject sumbitToSAPDirect(SAPMappingMst sapMappingMst, org.json.JSONObject dataObj ) throws SAPExceptions, SOAPException, IOException, JSONException, ParserConfigurationException, SAXException{
		
		 CommonFunctions.debugMsg(" filePath " + filePath);
		 SOAPMessage soapMessage = createSOAPMesage(sapMappingMst,dataObj);		 
		 CommonFunctions.debugMsg(soapMessage +" URL......... " + sapMappingMst.getSpmmWebservicename());
		 SOAPMessage response = sendSOAPMessage(soapMessage,sapMappingMst.getSpmmWebservicename());
		 FileOutputStream out = new FileOutputStream(filePath+"retmsg_"+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
		 response.writeTo(out);
		 out.close();
		 SOAPBody retBody = response.getSOAPBody();
		 CommonFunctions.debugMsg(" retBody " + retBody);
		 if( retBody.hasFault()){
			SOAPFault fault = retBody.getFault();
			CommonFunctions.debugMsg(" fault.getFaultString() "+fault.getFaultString());
			throw new SOAPException("Fault When processing request "+fault.getFaultString());
		 }
		 
		 String strMsg = getMsgAsString(response);
		 
		 CommonFunctions.debugMsg(" return strMsg " + strMsg);
		 org.json.JSONObject retObj = XML.toJSONObject( strMsg);
		 CommonFunctions.debugMsg(" return retObj " + retObj);
		 org.json.JSONObject retJBody = retObj.getJSONObject("SOAP:Envelope").getJSONObject("SOAP:Body");
		 CommonFunctions.debugMsg(" return retJBody " + retJBody);
		 return retJBody.getJSONObject( retJBody.names().getString(0));
		// readResponse(response,sapMappingMst);
	}
	/*  Send the SOAP Message to the web service end point 
	 *  return back the SOAPMessage from web service
	 *  @soapMessage : message need to send
	 *  @endPoint    : location at which the SOAP message need to submit 
	 */
	
	
	private SOAPMessage sendSOAPMessage(SOAPMessage soapMessage,String endPoint) throws MalformedURLException, SOAPException{
		
		SOAPConnectionFactory soapConnectionFactory;
		SOAPConnection connection = null;
		java.net.URL endpointURL;
		CommonFunctions.debugMsg(" url endPoint" +endPoint);
		try {
		
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			//java.net.HttpURLConnection c = HttpURLConnection.g;
			
			connection = soapConnectionFactory.createConnection();

			endpointURL = new URL( endPoint);
			
			SOAPMessage retMsg =  connection.call(soapMessage, endpointURL);
			CommonFunctions.debugMsg(" sendSOAPMessage " +retMsg +"connection");

			return retMsg;
			
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			throw e;
		} catch (MalformedURLException e) {
			throw e;
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}finally{
			if( connection  != null){
				try {
					connection.close();
					connection=null;
					
				} catch (SOAPException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} 
			}	
		}
		
	}
	private SOAPMessage getSOAPMesgDataObject(String fileName) throws JAXBException, FileNotFoundException{
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		 
		//CommonFunctions.debugMsg( "file Name "+ fileName );
		//2. Use JAXBContext instance to create the Unmarshaller.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		//String fileName="C:/Users/akranta-42/Desktop/bal/retmsg_BD_ORDER_CREATE.xml";
		//InputStream is = this.getClass().getResourceAsStream("/com/akranta/tpm/validations/" + fileName + ".xml");
		//InputStream is = this.getClass().getResourceAsStream(fileName );
		//ClassLoader.getSystemResourceAsStream("com/akranta/tpm/validations/" + fileName + ".xml");
		//3. Use the Unmarshaller to unmarshal the XML document to get an instance of JAXBElement.
		CommonFunctions.debugMsg( "file Name isnew "+ fileName );
		
		InputStream is = new FileInputStream(fileName);
		//JAXBElement<?> unmarshalledObject = unmarshaller.unmarshal(is);
		SOAPMessage soapMessage =(SOAPMessage )unmarshaller.unmarshal(is);
		//4. Get the instance of the required JAXB Root Class from the JAXBElement.
		//UploadData uploadData = (UploadData) unmarshalledObject.getValue();
		
		return soapMessage;
		
		
	}
	/*
	 * Sql for getting configuration data for the processCode
	 */
	private String getConfigMappingSql(String processCode){
		StringBuilder sql = new StringBuilder(); 
		sql.append(" select SPMM_PROCESSCODE, SPMM_WEBSERVICENAME,SPMM_WEBSERVICEURI,SPMM_SCENARIOID, ");
		sql.append(" SPMM_PREFIX,SPMD_PERFEX_TABLE_NAME,SPMD_PERFEX_COLUMN_NAME,");
		sql.append(" SPMD_PERFEX_COLUMN_TYPE,SPMD_PERFEX_COLUMN_WIDTH, ");
		sql.append(" SPMD_PERFEX_REF_TABLE,SPMD_PERFEX_REF_COLUMN,SPMD_PERFEX_REF_MAPCOLUMN, ");
		sql.append(" SPMD_SAP_TABLE_NAME,SPMD_SAP_COLUMN_NAME,SPMD_SAP_COLUMN_TYPE,");
		sql.append(" SPMD_SAP_COLUMN_WIDTH,");
		sql.append(" SPMD_INCLUDEREQUEST,SPMD_INCLUDERESPONSE,SPMD_INCLUDECONDITION,SPMD_CONDITIONVALUE,SPMD_ISFORALERT,SPMD_ISFROMTABLE,SPMD_DISPLAYNAME from "); 
		sql.append(  TBL_SAP_TL_PERFEXMAPPINGMST ).append("," ).append(TBL_SAP_TL_PERFEXMAPPINGDTL );	
		sql.append(" where SPMD_SPMM_KEYID=SPMM_KEYID ");
		sql.append(" and  SPMM_PROCESSCODE = '").append(processCode ).append("' "); 
		//sql.append(" order by SPMD_PERFEX_TABLE_NAME,DECODE(SPMD_INCLUDEREQUEST ,'Y',1,2) ");
		sql.append(" order by SPMD_PERFEX_TABLE_NAME, CASE WHEN SPMD_INCLUDEREQUEST = 'Y' THEN 1 ELSE 2 END ");
		//sql.append(" order by SPMM_WEBSERVICENAME,SPMM_WEBSERVICEURI, SPMM_SCENARIOID ,SPMD_PERFEX_TABLE_NAME ");
		return sql.toString();
	}
	
	/*
	 * Read the response SOAP Message from response based on SAPMappingMst Object  
	 * And Store into the corresponding table 
	 */
	private static String  readResponse(SOAPMessage message,SAPMappingMst sapMappingMst) throws SOAPException {
		 
		CommonFunctions.debugMsg(" Processing Request .." );
		SOAPBody retBody = message.getSOAPBody();
		CommonFunctions.debugMsg(" retBody " + retBody);
		if( retBody.hasFault()){
			SOAPFault fault = retBody.getFault();
			throw new SOAPException("Fault When processing request "+fault.getFaultReasonTexts());
			
		}
		String msgStr = getMsgAsString(message);
		CommonFunctions.debugMsg(msgStr+"         Message String In the SAOP Clint ");
		try{
		String status =retBody.getElementsByTagName("TYPE").item(0).getTextContent();
			
		String Msg = retBody.getElementsByTagName("MESSAGE").item(0).getTextContent();
		CommonFunctions.debugMsg("Status Mesage in readResponse..."+status);
		
		/*CommonFunctions.debugMsg(" No Fault Processing Request .." );
		Iterator returnElements = retBody.getChildElements(); //create an iterator on elements
		int counter=0;

		//retBody.getElementsByTagName("bbb").item(0).getFirstChild().getNodeValue(); 
		try{ 
			while(returnElements.hasNext())
			{
			  SOAPBodyElement returnEl = (SOAPBodyElement)returnElements.next();
			  CommonFunctions.debugMsg("  element Name returnEl " + returnEl.getTagName());
			  if( returnEl.hasChildNodes())
			  {// returnEl.g
				  Iterator nextIt2 = returnEl.getChildElements();
				  
				  while(nextIt2.hasNext() ){
					  
					  SOAPElement ch2 = (SOAPElement)nextIt2.next();
		
					  CommonFunctions.debugMsg("  element Name ch2 " + ch2.getTagName());
					  if( ch2.hasChildNodes()){
						  Iterator nextIt3 = ch2.getChildElements();
						  while(nextIt3.hasNext() ){
							  SOAPElement ch3 = (SOAPElement)nextIt3.next();
							  CommonFunctions.debugMsg("  element Name ch3 " + ch3.getTagName());
							 if(ch3.hasChildNodes()){
								 Iterator nextIt4=ch3.getChildElements();
								 while(nextIt4.hasNext()){
									 SOAPElement ch4=(SOAPElement) nextIt4.next();
									 CommonFunctions.debugMsg("element Name Ch4 "+ch4.getTagName());
									 	
									 String ch4V = ch3.getValue();
									 System.out.println(" ch4 value :"+ ch4V);
									 	
									 counter++;
						  }	  
					  }
							else{
								 String ch3V=ch2.getValue();
								 System.out.println(" ch3 val :"+ ch3V);
							 }
						  }
					  }
					  else{
						  String ch2V = ch2.getValue();
						  System.out.println(" ch2 val :"+ ch2V);
					  }
				  }	  
			   }
			  else{
				  String ch1V = returnEl.getValue();
				  System.out.println(" ch1V val :"+ ch1V);
			  }
			}*/
		
		}catch(Exception e){
			e.printStackTrace();
			throw new SOAPException("Success Msg "+msgStr + ", but error while Processing response: " );
		
		}
		
		//commented on 25jun15
		//throw new SOAPException("Success Msg "+msgStr);
		return msgStr;
	}
	

	private static void convertRetBodytoJson(SOAPBody retBody,JSONObject ret){
		Iterator returnElements = retBody.getChildElements(); //create an iterator on elements
		int counter=0;

		//retBody.getElementsByTagName("bbb").item(0).getFirstChild().getNodeValue(); 
		try{ 
			while(returnElements.hasNext())
			{
			  SOAPBodyElement returnEl = (SOAPBodyElement)returnElements.next();
			  
			  CommonFunctions.debugMsg("  element Name returnEl " + returnEl.getTagName());
			  if( returnEl.hasChildNodes())
			  {// returnEl.g
				  Iterator nextIt2 = returnEl.getChildElements();

				  while(nextIt2.hasNext() ){
					  
					  SOAPElement ch2 = (SOAPElement)nextIt2.next();
		
					  CommonFunctions.debugMsg("element Name ch2 " + ch2.getTagName());
					  if( ch2.hasChildNodes()){
						  Iterator nextIt3 = ch2.getChildElements();
						  while(nextIt3.hasNext() ){
							  SOAPElement ch3 = (SOAPElement)nextIt3.next();
							  CommonFunctions.debugMsg(" element Name ch3 " + ch3.getTagName());
							  String ch3V = ch3.getValue();
							  System.out.println(" ch3 value :"+ ch3V);
							  
							  counter++;
						  }	  
					  }
					  else{
						  String ch2V = ch2.getValue();
						  System.out.println(" ch3 val :"+ ch2V);
					  }
				  }	  
			   }
			  else{
				  String ch1V = returnEl.getValue();
				  System.out.println(" ch1V val :"+ ch1V);
				  ret.put(returnEl.getTagName(),ch1V);
			  }
			}
		}catch(Exception e){
			
		}
	}
	
	public static String getMsgAsString(SOAPMessage message) {
		String msg = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);
			msg = baos.toString();
			//baos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
		}
	
	/*
	 * create the SOAPMessage by using SAPMappingMst Object and the transId
	 * @SAPMappingMst : contains all information needed to generate and send the SOAP Xml data and web service
	 * @transId 	  : the required unique id to get the transaction record
	 */
	private SOAPMessage createSOAPMesage(SAPMappingMst sapMappingMst,String transId) throws SOAPException{
		
		//System.setProperty("javax.xml.soap.MessageFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl");
		
		////////* Create message factory by using SOAP protocol 1.1*/
		MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
		SOAPMessage message = factory.createMessage();
		
		message.getSOAPPart().getEnvelope().addNamespaceDeclaration(sapMappingMst.getSpmmPrefix()/*.replace("-", "")*/, sapMappingMst.getSpmmWebserviceuri());
		//SOAPHeader header = message.getSOAPHeader();
		//header.detachNode();
		MimeHeaders headers = message.getMimeHeaders();
		/*
		 * Add the authentication information to the Header
		 */
		CommonFunctions.debugMsg(" USER NAME AND PASSWORD" +userName +"  "+password) ;
		if( UIUtils.isValidKeyId(userName) && UIUtils.isValidKeyId(password)  ){
			String authStr = userName +":"+password;
			//String authorization = new sun.misc.BASE64Encoder().encode(authStr.getBytes());
			String authorization = Base64.getEncoder().encodeToString(authStr.getBytes(StandardCharsets.UTF_8));
			//MimeHeaders hd = message.getMimeHeaders();
			//CommonFunctions.debugMsg(" Creating mimiheader for authentication " );
			headers.addHeader("Authorization", "Basic " + authorization);
		}
		//MimeHeaders hd = message.getMimeHeaders();
		//headers.addHeader("SOAPAction",sapMappingMst.getSpmmWebserviceuri() +"/"+ sapMappingMst.getSpmmScenarioid() );
		headers.addHeader("SOAPAction",sapMappingMst.getSpmmWebserviceuri());
		
		CommonFunctions.debugMsg("Service Url "+sapMappingMst.getSpmmWebserviceuri() +" URL  "+ sapMappingMst.getSpmmWebservicename());
		/*AttachmentPart attachment = message.createAttachmentPart();
		attachment.setContentType("text/xml");
		MimeHeaders hd = message.getMimeHeaders();
		hd.addHeader("Content-Type", "text/xml");
		*/
		//MimeHeaders hd = message.getMimeHeaders();
		//message.setProperty("content-Type", "text/xml");
		 
		CommonFunctions.debugMsg(" creating method.... " );
		//QName bodyName = 
		  //  new QName(sapMappingMst.getSpmmWebserviceuri(), sapMappingMst.getSpmmScenarioid(), sapMappingMst.getSpmmPrefix());
		//QName bodyName = new QName(sapMappingMst.getSpmmScenarioid(),sapMappingMst.getSpmmPrefix());
		
		
		
		//QName bodyName = new QName(sapMappingMst.getSpmmScenarioid());
	//	new QName(QName)
		CommonFunctions.debugMsg(" creating body.... " );
		SOAPBody body = message.getSOAPBody();
		QName bodyName = body.createQName(sapMappingMst.getSpmmScenarioid(), sapMappingMst.getSpmmPrefix());
		CommonFunctions.debugMsg(" creating body element.... " );
		SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
		
		/* Contains the details of data to submit or send*/
		List<SAPMappingTbl> sapMappingTbls = sapMappingMst.getSapMappingDtls();
		//QName 
		//QName  parentName1 = new QName("IN_DATA");
		
	//	SOAPElement parentElement1 = bodyElement.addChildElement(parentName1);
	//	int t =0;
		for(SAPMappingTbl sapMappingTbl: sapMappingTbls){
			try{
				QName  parentName = new QName(sapMappingTbl.getSpmdSapTableName());
				CommonFunctions.debugMsg(" sapMappingTbl.getSpmdSapTableName() " + sapMappingTbl.getSpmdSapTableName());
				CommonFunctions.debugMsg(" sapMappingTbl.getSpmdSapTableName() " + sapMappingTbl.getFieldDtls());
				SOAPElement parentElement = bodyElement.addChildElement(parentName);
				/* Dynamically creates the sql by using SapMappingTbl  */
				String sql = getItemDataSql(sapMappingTbl,transId);
				CommonFunctions.debugMsg(" sql -- " + sql );
				
				List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql, null);
				String [] tagNames = dataList.get(0)  ;
				for(int i = 1;i<dataList.size();i++){
					QName  parentName1 = new QName("item");
					SOAPElement parentName2 = parentElement.addChildElement(parentName1);
					String [] row = dataList.get(i); 
					for(int j = 0;j<row.length;j++){
						QName childName = new QName(tagNames[j]);
						//QName childName = bodyElement.createQName(tagNames[j], sapMappingMst.getSpmmPrefix());
						SOAPElement childElement = parentName2.addChildElement(childName);
						childElement.addTextNode(row[j]);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			FileOutputStream out = new FileOutputStream(filePath+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
			message.writeTo(out);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return message;
	}
	
	
	/* Dynamically creates the sql by using SapMappingTbl
	 * 
	 */
	
	private String getItemDataSql(SAPMappingTbl sapMappingTbl, String trnasId){

		StringBuilder sql = new StringBuilder(" SELECT ");
		StringBuilder tablesSql = new StringBuilder(sapMappingTbl.getSpmdPerfexTableName());
		StringBuilder condSql = new StringBuilder(" Where 1=1  ");
		List<SAPMappingTblField> sapMappingTblFields = sapMappingTbl.getFieldDtls();
		
		char alias ='A'; 
		for(SAPMappingTblField  sapMappingTblField:sapMappingTblFields ){
			//CommonFunctions.debugMsg(" sql build -- " + sql );
			if( sapMappingTblField.isSpmdIncludeRequest()  ) {//&& ! sapMappingTblField.isSpmdIncludeCondition()){
				if( "T".equals(sapMappingTblField.getSpmdSapColumnType()) || "D".equals(sapMappingTblField.getSpmdSapColumnType())){
					sql.append(" replace( replace( To_char( "  );
				}
				if(  sapMappingTblField.getSpmdPerfexRefTable() !=null && sapMappingTblField.getSpmdPerfexRefTable().trim().length() > 2){
					tablesSql.append(","+ sapMappingTblField.getSpmdPerfexRefTable() + " " + alias);
					sql.append( " replace( " + alias+"."+ sapMappingTblField.getSpmdPerfexRefMapcolumn()+",'{}','')" );
					condSql.append(" AND "+ sapMappingTblField.getSpmdPerfexColumnName() +" = " + alias+"." +sapMappingTblField.getSpmdPerfexRefColumn() +"(+)");
					alias += 1;
				}
				else if( "T".equals(sapMappingTblField.getSpmdSapColumnType()) || "D".equals(sapMappingTblField.getSpmdSapColumnType()))
					sql.append( sapMappingTblField.getSpmdPerfexColumnName() );
				else{
					if(sapMappingTblField.getSpmdPerfexColumnName().equals("BDM_CELLCODE")){
						sql.append( " replace( " + sapMappingTblField.getSpmdPerfexColumnName() +",'{}','')" );
					}
					else
					sql.append( " replace(replace( " + sapMappingTblField.getSpmdPerfexColumnName() +"::TEXT,'{}',''),'-','')" );
					
					CommonFunctions.debugMsg("...............column Name........"+sapMappingTblField.getSpmdPerfexColumnName()+".........SAP Column Name......"+sapMappingTblField.getSpmdSapColumnName());
				}
				if( "T".equals(sapMappingTblField.getSpmdSapColumnType())){
					sql.append(" ,'HH24:MM'),'00:00',''),'00:01','') ");
				}
				else if( "D".equals(sapMappingTblField.getSpmdSapColumnType())){
					sql.append(" ,'DD-Mon-YYYY'),'01-Jan-1801',''),'31-Dec-2100','') ");
				}
				if( useSAPField )
					CommonFunctions.debugMsg("in side use sap fields");
								
					sql.append( " as \"" + sapMappingTblField.getSpmdSapColumnName() +"\"");
				
				sql.append( ",");
				
			}
			if( sapMappingTblField.isSpmdIncludeCondition()){
				CommonFunctions.debugMsg("in side use sap include condition");
				condSql.append(" AND " +  sapMappingTblField.getSpmdPerfexColumnName() + " = ");
				if( "-".equals(sapMappingTblField.getSpmdConditionValue() )  ){
					CommonFunctions.debugMsg("in side use sap include condition If");
					condSql.append("'"+trnasId +"'");
				}
				else{
					condSql.append("'"+sapMappingTblField.getSpmdConditionValue()+"'");
				}
			}
		}
		sql.deleteCharAt(sql.length()-1);
		//sql = sql.substring(0, sql.length()-1);
		sql.append( " FROM " );
		sql.append( tablesSql);
		sql.append( condSql);
		return sql.toString();
	}
	/*
	 * Get the configuration data from table based on processCode 
	 * And populate and return the SAPMappingMst's object
	 * 
	 * @processCode : webservice process code
	 * 
	 */
	public SAPMappingMst getConfigData(String processCode) throws SAPExceptions{
		
		
		String sql = getConfigMappingSql(processCode);
		ResultSet data = null;
		try{
			CommonFunctions.debugMsg( " sql " + sql);
			data = dbActionTemplate.getData(sql);
			if( data == null  )
				throw new SAPExceptions(SAPExceptions.ERROR_CODE_NOMAPPING_FOUND,processCode,null);
			
			SAPMappingMst sapMappingMst = populateSAPMappingMst(data);
			return sapMappingMst;
		}catch(Exception e){
			throw new SAPExceptions(SAPExceptions.ERROR_CODE_NOMAPPING_FOUND,processCode, e);
		}finally{
			if(  data != null){
				Statement statement = null;
				Connection conn =null;
				try {
					statement = data.getStatement();
					if(statement!= null)
						conn = statement.getConnection();
					DBActionTemplate.closeConnection(data, statement, null, null, conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
	}
	/*
	 * Create, Populate and return the SAPMappingMst object from the result set
	 */
	
	private SAPMappingMst populateSAPMappingMst(ResultSet data) throws SQLException{
		SAPMappingMst sapMappingMst = new SAPMappingMst();
		String prvTblName=null;
		String prvRetTblName=null;
		
		SAPMappingTbl sapMappingTbl = null;
		SAPMappingTbl sapMappingRetTbl = null;
		
		List<SAPMappingTbl> sapMappingTblList = new ArrayList<SAPMappingTbl>();
		List<SAPMappingTbl> sapMappingRetTblList = new ArrayList<SAPMappingTbl>();
		
		List<SAPMappingTblField> sapMappingTblFieldList = null;
		List<SAPMappingTblField> sapMappingRetTblFieldList = null;
		while(data.next()){
			String perfexTblName = data.getString("SPMD_PERFEX_TABLE_NAME");
			String sapTblName = data.getString("SPMD_SAP_TABLE_NAME");
			SAPMappingTblField sqpMappingTblField = new SAPMappingTblField();
			
			sqpMappingTblField.setSpmdIncludeRequest( data.getString("SPMD_INCLUDEREQUEST").equals("Y")?true:false);
			sqpMappingTblField.setSpmdIncludeResponse(data.getString("SPMD_INCLUDERESPONSE").equals("Y")?true:false);
			sqpMappingTblField.setSpmdIncludeCondition(data.getString("SPMD_INCLUDECONDITION").equals("Y")?true:false);
			
			CommonFunctions.debugMsg( " SPMD_PERFEX_TABLE_NAME = " + perfexTblName );
			CommonFunctions.debugMsg( " SPMD_INCLUDEREQUEST = " + sqpMappingTblField.isSpmdIncludeRequest() );
			CommonFunctions.debugMsg( " SPMD_INCLUDERESPONSE = " + sqpMappingTblField.isSpmdIncludeResponse() );
			CommonFunctions.debugMsg( " SPMD_INCLUDECONDITION = " + sqpMappingTblField.isSpmdIncludeCondition() );
			

			if( prvTblName == null  &&  ( sqpMappingTblField.isSpmdIncludeRequest() || sqpMappingTblField.isSpmdIncludeCondition() ) ){
				CommonFunctions.debugMsg( " inside 1" );
				sapMappingTbl = new SAPMappingTbl();
				sapMappingTbl.setSpmdPerfexTableName(perfexTblName);
				sapMappingTbl.setSpmdSapTableName(sapTblName);
				sapMappingTblFieldList = new ArrayList<SAPMappingTblField>();
			}
			if( prvRetTblName == null && sqpMappingTblField.isSpmdIncludeResponse() ){
				sapMappingRetTbl = new SAPMappingTbl();
				sapMappingRetTbl.setSpmdPerfexTableName(perfexTblName);
				sapMappingRetTbl.setSpmdSapTableName(sapTblName);
				sapMappingRetTblFieldList = new ArrayList<SAPMappingTblField>();
				CommonFunctions.debugMsg( " inside SOAPClient : 1 " + prvRetTblName );
			}
			if( prvTblName == null && prvRetTblName == null){
				sapMappingMst.setSpmmProcesscode(data.getString("SPMM_PROCESSCODE"));
				sapMappingMst.setSpmmWebservicename(data.getString("SPMM_WEBSERVICENAME"));
				sapMappingMst.setSpmmWebserviceuri(data.getString("SPMM_WEBSERVICEURI"));
				sapMappingMst.setSpmmScenarioid(data.getString("SPMM_SCENARIOID"));
				sapMappingMst.setSpmmPrefix(data.getString("SPMM_PREFIX"));
				CommonFunctions.debugMsg( " inside SOAPClient : 2 " + prvTblName + "-"+ prvRetTblName );
			}
			
			if( prvTblName != null && ! perfexTblName.equals(prvTblName) && ( sqpMappingTblField.isSpmdIncludeRequest() || sqpMappingTblField.isSpmdIncludeCondition() )){
				
				sapMappingTbl.setFieldDtls(sapMappingTblFieldList);
				sapMappingTblList.add(sapMappingTbl);
				sapMappingTblFieldList = new ArrayList<SAPMappingTblField>();
				sapMappingTbl = new SAPMappingTbl();
				sapMappingTbl.setSpmdPerfexTableName(perfexTblName);
				sapMappingTbl.setSpmdSapTableName(sapTblName);
				CommonFunctions.debugMsg( " inside SOAPClient : 3 " + prvTblName + "-"+ prvRetTblName );
			}
			if( prvRetTblName != null && ! perfexTblName.equals(prvRetTblName) && sqpMappingTblField.isSpmdIncludeResponse()){
				
				sapMappingRetTbl.setFieldDtls(sapMappingRetTblFieldList);
				sapMappingRetTblList.add(sapMappingRetTbl);
				sapMappingRetTblFieldList = new ArrayList<SAPMappingTblField>();
				sapMappingRetTbl = new SAPMappingTbl();
				sapMappingRetTbl.setSpmdPerfexTableName(perfexTblName);
				sapMappingRetTbl.setSpmdSapTableName(sapTblName);
				CommonFunctions.debugMsg( " inside SOAPClient : 4 " + prvTblName + "-"+ prvRetTblName );
			}

			
			sqpMappingTblField.setSpmdPerfexColumnName(data.getString("SPMD_PERFEX_COLUMN_NAME"));
			sqpMappingTblField.setSpmdPerfexColumnType(data.getString("SPMD_PERFEX_COLUMN_TYPE"));
			sqpMappingTblField.setSpmdPerfexColumnWidth(data.getInt("SPMD_PERFEX_COLUMN_WIDTH")+"");
			
			sqpMappingTblField.setSpmdPerfexRefTable(data.getString("SPMD_PERFEX_REF_TABLE"));
			sqpMappingTblField.setSpmdPerfexRefColumn(data.getString("SPMD_PERFEX_REF_COLUMN"));
			sqpMappingTblField.setSpmdPerfexRefMapcolumn(data.getString("SPMD_PERFEX_REF_MAPCOLUMN"));
			
			sqpMappingTblField.setSpmdSapTableName(data.getString("SPMD_SAP_TABLE_NAME"));
			sqpMappingTblField.setSpmdSapColumnName(data.getString("SPMD_SAP_COLUMN_NAME"));
			sqpMappingTblField.setSpmdSapColumnType(data.getString("SPMD_SAP_COLUMN_TYPE"));
			sqpMappingTblField.setSpmdSapColumnWidth(data.getInt("SPMD_SAP_COLUMN_WIDTH")+"");
			
			sqpMappingTblField.setSpmdDisplayname(data.getString("SPMD_DISPLAYNAME"));
			
			sqpMappingTblField.setSpmdConditionValue(data.getString("SPMD_CONDITIONVALUE"));
			
			sqpMappingTblField.setSpmdIsforalert(data.getString("SPMD_ISFORALERT"));
			
			if( sqpMappingTblField.isSpmdIncludeRequest() ){
				sapMappingTblFieldList.add(sqpMappingTblField);
				prvTblName = perfexTblName;
				CommonFunctions.debugMsg( " inside SOAPClient : 5 " + prvTblName + "-"+ prvRetTblName );
			}	
			else if( sqpMappingTblField.isSpmdIncludeResponse() ){
				sapMappingRetTblFieldList.add(sqpMappingTblField);
				prvRetTblName = perfexTblName;
				CommonFunctions.debugMsg( " inside SOAPClient : 6 " + prvTblName + "-"+ prvRetTblName );
			}
			
			if( sqpMappingTblField.isSpmdIncludeCondition()){
				sapMappingTblFieldList.add(sqpMappingTblField);
				if( sqpMappingTblField.isSpmdIncludeResponse() )
					sapMappingRetTblFieldList.add(sqpMappingTblField);
				CommonFunctions.debugMsg( " inside SOAPClient : 6 " + prvTblName + "-"+ prvRetTblName );
			}
		}
		if( sapMappingTblFieldList != null && sapMappingTblFieldList.size() > 0){
			sapMappingTbl.setFieldDtls(sapMappingTblFieldList);
			sapMappingTblList.add(sapMappingTbl);
			sapMappingMst.setSapMappingDtls(sapMappingTblList);
			CommonFunctions.debugMsg( " inside SOAPClient : 7 " + prvTblName + "-"+ prvRetTblName );
		}
		if( sapMappingRetTblFieldList != null && sapMappingRetTblFieldList.size() > 0){
			sapMappingRetTbl.setFieldDtls(sapMappingRetTblFieldList);
			sapMappingRetTblList.add(sapMappingRetTbl);
			sapMappingMst.setSapMappingRetDtls(sapMappingRetTblList);
			CommonFunctions.debugMsg( " inside SOAPClient : 8 " + prvTblName + "-"+ prvRetTblName );
		}
		CommonFunctions.debugMsg(" size " + sapMappingTblList.size() +  " end of populate " + sapMappingTbl.getSpmdPerfexTableName() );
		return sapMappingMst;
		
	}
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	    return sdf.format(cal.getTime());

	}
	
	/*
	 * Create the SOAPMessage by using SAPMappingMst Object and JSONObject
	 * @SAPMappingMst : contains all information needed to generate and send the SOAP Xml data and web service
	 * @postdata 	  :it is a JSON Object  contains the data to generate SOAP Message
	 */
	public SOAPMessage createSOAPMesage(SAPMappingMst sapMappingMst,org.json.JSONObject postdata) throws SOAPException, JSONException, ParserConfigurationException, SAXException, IOException{
		
		//System.setProperty("javax.xml.soap.MessageFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl");
		
		CommonFunctions.debugMsg(" creating msg factory  " );
		MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
		CommonFunctions.debugMsg(" creating msg   " );
		SOAPMessage message = factory.createMessage();
		message.getSOAPPart().getEnvelope().addNamespaceDeclaration(sapMappingMst.getSpmmPrefix()/*.replace("-", "")*/, sapMappingMst.getSpmmWebserviceuri());
		CommonFunctions.debugMsg(" creating msg   " );
		//SOAPHeader header = message.getSOAPHeader();
		//header.detachNode();
		MimeHeaders headers = message.getMimeHeaders();
		if( UIUtils.isValidKeyId(userName) && UIUtils.isValidKeyId(password)  ){
			String authStr = userName +":"+password;
			String authorization = new   String(authStr.getBytes());
			CommonFunctions.debugMsg(" authorization str" + authorization);
			
			//MimeHeaders hd = message.getMimeHeaders();
			//CommonFunctions.debugMsg(" Creating mimiheader for authentication " );
			headers.addHeader("Authorization", "Basic " + authorization);
			
			CommonFunctions.debugMsg(" added authentication info " );
		}
		//MimeHeaders hd = message.getMimeHeaders();
		//headers.addHeader("SOAPAction",sapMappingMst.getSpmmWebserviceuri() +"/"+ sapMappingMst.getSpmmScenarioid() );
		headers.addHeader("SOAPAction",sapMappingMst.getSpmmWebserviceuri());
		/*AttachmentPart attachment = message.createAttachmentPart();
		attachment.setContentType("text/xml");
		MimeHeaders hd = message.getMimeHeaders();
		hd.addHeader("Content-Type", "text/xml");
		*/
		//MimeHeaders hd = message.getMimeHeaders();
		//message.setProperty("content-Type", "text/xml");
		 
		CommonFunctions.debugMsg(" creating method.... " );
		//QName bodyName = 
		  //  new QName(sapMappingMst.getSpmmWebserviceuri(), sapMappingMst.getSpmmScenarioid(), sapMappingMst.getSpmmPrefix());
		//QName bodyName = new QName(sapMappingMst.getSpmmScenarioid(),sapMappingMst.getSpmmPrefix());
		
		
		
		//QName bodyName = new QName(sapMappingMst.getSpmmScenarioid());
	//	new QName(QName)
		CommonFunctions.debugMsg(" creating body.... " + sapMappingMst.getSpmmScenarioid() );
		SOAPBody body = message.getSOAPBody();
		QName bodyName = body.createQName(sapMappingMst.getSpmmScenarioid(), sapMappingMst.getSpmmPrefix());
		CommonFunctions.debugMsg(" creating body element.... " );
		CommonFunctions.debugMsg(" creating body element....777777 "+sapMappingMst.getSpmmPrefix() );
		CommonFunctions.debugMsg(" creating body element....777777 "+bodyName);

		SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
		
		
		
		//QName 
		//QName  parentName1 = new QName("IN_DATA");
		
		
		//\SOAPElement parentElement1 = bodyElement.addChildElement(parentName1);
		
		CommonFunctions.debugMsg(" postdata " + postdata.toString() );
		//String s = postdata.toString().replace('"', '\0');
		//CommonFunctions.debugMsg(" postdata --  " + s  );
		//XMLSerializer serializer = new XMLSerializer();  
		org.json.JSONObject xmlObj = new org.json.JSONObject();
		xmlObj.put("IN_DATA", postdata);
		String xmlChild = XML.toString( xmlObj );
		CommonFunctions.debugMsg(" xmlChild --- " + xmlChild );
		//xmlChild = XMLSerializer.write(postdata);
		
		CommonFunctions.debugMsg(" xmlChild " + xmlChild );
		
		StringReader stringReader = new StringReader(xmlChild);
        InputSource inputSource = new InputSource(stringReader);
        SAXParserFactory facto = SAXParserFactory.newInstance();
        facto.setNamespaceAware(true);
        SAXParser parser = facto.newSAXParser();
        SoapElementSaxHandler handler = new SoapElementSaxHandler();
        parser.parse(inputSource, handler);
        
        
		
		try{
			bodyElement.addChildElement( handler.getSOAPElement());
			//bodyElement.addChildElement (xmlChild);
			
			FileOutputStream out = new FileOutputStream(filePath+sapMappingMst.getSpmmProcesscode()+"_"+now() +".xml");
			message.writeTo(out);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	
}


class SoapElementSaxHandler extends DefaultHandler
{

    public SoapElementSaxHandler()
    {
        prefixURIMapping = new HashMap();
        uris = new ArrayList();
        rootElement = null;
        currentElement = null;
    }

    public SOAPElement getSOAPElement()
    {
        return rootElement;
    }

    public void startDocument()
        throws SAXException
    {
        try
        {
            soapFactory = SOAPFactory.newInstance();
        }
        catch(SOAPException e)
        {
            throw new SAXException("Can't create a SOAPFactory instance", e);
        }
    }

    public void startPrefixMapping(String prefix, String uri)
    {
        prefixURIMapping.put(uri, prefix);
        uris.add(uri);
    }

    public void characters(char ch[], int start, int length)
        throws SAXException
    {
        String str = String.valueOf(ch);
        if(length > 0)
            try
            {
                currentElement.addTextNode(str.substring(start, start + length));
            }
            catch(SOAPException e)
            {
                throw new SAXException("Can't add a text node into SOAPElement from text", e);
            }
    }

    public void endElement(String uri, String localName, String qName)
    {
        if(currentElement != rootElement)
            currentElement = currentElement.getParentElement();
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
        throws SAXException
    {
        String prefix = (String)prefixURIMapping.get(namespaceURI);
        try
        {
            if(rootElement == null && currentElement == null)
            {
                rootElement = soapFactory.createElement(localName, prefix, namespaceURI);
                currentElement = rootElement;
            } else
            {
                currentElement = currentElement.addChildElement(localName, prefix, namespaceURI);
            }
            if(uris.size() > 0)
            {
                for(int i = 0; i < uris.size(); i++)
                {
                    String uri = (String)uris.get(i);
                    String pre = (String)prefixURIMapping.get(uri);
                    currentElement.addNamespaceDeclaration(pre, uri);
                }

                uris.clear();
            }
            for(int i = 0; i < atts.getLength(); i++)
            {
            	jakarta.xml.soap.Name attriName;
                if(atts.getURI(i) != null)
                {
                    String attriPre = (String)prefixURIMapping.get(atts.getURI(i));
                    attriName = soapFactory.createName(atts.getLocalName(i), attriPre, atts.getURI(i));
                } else
                {
                    attriName = soapFactory.createName(atts.getLocalName(i));
                }
                currentElement.addAttribute(attriName, atts.getValue(i));
            }

        }
        catch(SOAPException e)
        {
            throw new SAXException(e);
        }
    }

    private HashMap prefixURIMapping;
    private ArrayList uris;
    private SOAPElement rootElement;
    private SOAPElement currentElement;
    private SOAPFactory soapFactory;
}
