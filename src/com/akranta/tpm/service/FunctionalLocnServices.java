package com.akranta.tpm.service;

import java.io.FileInputStream;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GenTlPbumstBean;
import com.akranta.tpm.bean.GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSbumst;


public interface FunctionalLocnServices {
	
	public  List<FunctionalLocn> getFunctionalLocnValues(String userId) throws Exception;	
	public  List<String[]> getGridDetail() throws Exception;	
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception;
	public  List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception;
	public FunctionalLocn create(FunctionalLocn functionalLocn,List<String> locnValues)throws Exception;
	public GenTlLayoutfieldimg saveBlobImage(GenTlLayoutfieldimg genTlLayoutfieldimg)throws Exception;
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end,String key,GridParams gridParams) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield) throws Exception;
	public  List<String[]>  getSearchNode(String searchNode) throws Exception;
	public FunctionalLocn cutEqp(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception;
	public FunctionalLocn deleteNode(FunctionalLocn functionalLocn)throws Exception;
	public FunctionalLocn deletMachine(FunctionalLocn functionalLocn)throws Exception;
	public FunctionalLocn copyNode(FunctionalLocn functionalLocn,String eqpId)	throws Exception;
	public GenTlLayoutfieldimg select(String locnId) throws Exception;
	public GenTlLayoutfieldimg getLayoutImg(GenTlLayoutfieldimg genTlLayoutfieldimg) throws Exception;
	public FunctionalLocn deleteImage(String nodeId)throws Exception;
	public List<String[]>  cutValidEqp(String nodeId)throws Exception;
	public List<String[]>  getBdforEqp(String nodeId)throws Exception;	
	public List<ComboBox> getFindComboList(ComboFilter comboFilter,List<String> childElem,String formfield,String type,String code) throws Exception;	
	public List<ComboBox> getLocationComboList(CommonFilter commonFilter) throws Exception;	
	public List<ComboBox> getAssemblyComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getSubassemblyComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getSpareComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getSubCellComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getMachineComboList(CommonFilter commonFilter)throws Exception;
	public String getNameForId(String currentSearchId)throws Exception;
	public FactoryLayout getFactoryLayoutElements(FactoryLayout factoryLayout) throws Exception;
	public FactoryLayout getEmployeeFunctionalLocation(String employeeId, String roleId, String flid) throws NoDataFoundException, Exception;
    
	
	public GenTlSbumst fillcontrol(String Sbukeyid)throws Exception;
	public GenTlSbumst sbucreate(GenTlSbumst newGenTlSbumst,GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean)throws Exception;
	public GenTlSbumst sbuupdate(GenTlSbumst newGenTlSbumst,GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean)throws Exception;
	public GenTlPbumst fillpbucontrol(String Pbukeyid)throws Exception;
	public GenTlPbumst pbucreate(GenTlPbumst newGenTlPbumst,GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean)throws Exception;
	public GenTlPbumst pbuupdate(GenTlPbumst newGenTlPbumst,GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean)throws Exception;
	public GenTlSbumst sbudelete(GenTlSbumst newGenTlSbumst)throws Exception;
	public GenTlPbumst pbudelete(GenTlPbumst newGenTlPbumst)throws Exception;
	//public String getfunctionalid(String id) throws Exception;

	

}
