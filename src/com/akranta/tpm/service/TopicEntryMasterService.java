package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTopicmst;

public interface TopicEntryMasterService {

  public List<String[]> getTopicDet(CommonFilter commonFilter,String TopicKeyid);
  public Workbook TopicExportExcel(CommonFilter commonFilter, JSONObject colmodel,String format) throws SQLException, Exception;
  public EntTlTopicmst create(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst existEntTlTopicmst, String trSpokeid) throws  Exception;

  public EntTlTopicmst update(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst existEntTlTopicmst,String trSpokeid) throws Exception;

  public void DeleteTopiclist(String keyid) throws BusinessApplicationExceptions, Exception;

  public EntTlTopicmst delete(EntTlTopicmst entTlTopicmst) throws Exception;
  public List<String[]> getSql(String keyid) throws Exception;
  public int selectCount(CommonFilter commonFilter) throws Exception;
  public List<ComboBox> getDeliveryModeCombo(ComboFilter comboFilter)throws Exception;
  public String getLocationid(String locationid) throws Exception;

}
