package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.BAL_GenTlPbumstBean;
import com.akranta.tpm.bean.BAL_GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSbumst;


public interface BAL_FunctionalLocnDao {
	
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception;
	public  List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception;
	public  List<FunctionalLocn> getFunctionalLocnValues(String userId) throws Exception;
	public FunctionalLocn create(FunctionalLocn functionalLocn,List<String> locnValues) throws Exception;
	public GenTlLayoutfieldimg saveBlobImage(GenTlLayoutfieldimg genTlLayoutfieldimg)throws Exception;
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getGridDetail() throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end,String key,GridParams gridParams,String parentId) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield,String parentId) throws Exception;
	public  List<String[]> getSearchNode(String searchNode) throws Exception;
	public String getNameForId(String currentSearchId)throws Exception;
	public FunctionalLocn cutEqp(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception;
	public FunctionalLocn deleteNode(FunctionalLocn functionalLocn)throws Exception;
	public FunctionalLocn copyNode(FunctionalLocn functionalLocn,String eqpId)	throws Exception;
	public GenTlLayoutfieldimg select(String locnId) throws Exception;
	public GenTlLayoutfieldimg getLayoutImg(GenTlLayoutfieldimg genTlLayoutfieldimg) throws Exception;
	public FunctionalLocn deleteImage(String nodeId)throws Exception;
	public List<String[]>  cutValidEqp(String nodeId)throws Exception;
	public List<String[]>  getBdforEqp(String nodeId)throws Exception;
	public FunctionalLocn deletMachine(FunctionalLocn functionalLocn)throws Exception;
	
	public FactoryLayout getFactoryLayoutElements(FactoryLayout factoryLayout) throws Exception;
	public FactoryLayout getEmployeeFunctionalLocation(String employeeId, String roleId, String flid) throws NoDataFoundException, Exception;
	public BAL_GenTlSbumst fillcontrol(String Sbukeyid)throws Exception;
	public BAL_GenTlSbumst sbucreate(BAL_GenTlSbumst newGenTlSbumst,BAL_GenTlSbumst exitGenTlSbumst, BAL_GenTlSbumstBean genTlSbumstBean)throws Exception;
	public BAL_GenTlSbumst sbuupdate(BAL_GenTlSbumst newGenTlSbumst, BAL_GenTlSbumst exitGenTlSbumst, BAL_GenTlSbumstBean genTlSbumstBean)throws Exception;
	public BAL_GenTlPbumst fillpbucontrol(String Pbukeyid)throws Exception;
	public BAL_GenTlPbumst pbucreate(BAL_GenTlPbumst newGenTlPbumst,BAL_GenTlPbumst exitGenTlPbumst, BAL_GenTlPbumstBean genTlPbumstBean)throws Exception;
	public BAL_GenTlPbumst pbuupdate(BAL_GenTlPbumst newGenTlPbumst,BAL_GenTlPbumst exitGenTlPbumst, BAL_GenTlPbumstBean genTlPbumstBean)throws Exception;
	public BAL_GenTlSbumst sbudelete(BAL_GenTlSbumst newGenTlSbumst)throws Exception;
	public BAL_GenTlPbumst pbudelete(BAL_GenTlPbumst newGenTlPbumst)throws Exception;
	public FunctionalLocn update(FunctionalLocn functionalLocn,List<String> locnValues)throws Exception;
	public FunctionalLocn inActivsteMachine(FunctionalLocn functionalLocn)throws Exception;;
	
	
}
