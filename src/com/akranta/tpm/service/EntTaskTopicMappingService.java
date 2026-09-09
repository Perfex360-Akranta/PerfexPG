package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;

public interface EntTaskTopicMappingService {

public	EntTlUniqpostopicLinkmst create(EntTlUniqpostopicLinkmst newentTlTaskMappingTopicmst,EntTlUniqpostopicLinkmst existEntTlTaskMappingTopicmst) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;

public	EntTlUniqpostopicLinkmst update(EntTlUniqpostopicLinkmst newentTlTaskMappingTopicmst,EntTlUniqpostopicLinkmst existEntTlTaskMappingTopicmst) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;

public List<String[]> getTaskMainGrid(CommonFilter commonFilter) throws Exception;

public EntTlUniqpostopicLinkmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception;

public List<String[]> getTopicDetail(CommonFilter commonFilter, String flid, String uniquePostion, String mstkeyid, String createmode, String topic);

public Workbook TaskTopicExcel(CommonFilter commonFilter, JSONObject colmodel,String format) throws SQLException, Exception;

public EntTlUniqpostopicLinkmst delete(EntTlUniqpostopicLinkmst newEntTlTaskMappingTopicmst) throws Exception;

public void DeleteTasklist(String keyid) throws BusinessApplicationExceptions, Exception;

}
