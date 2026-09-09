package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.MpSheetBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MpsTlMstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.dao.sql.KznTlMstSql;
import com.akranta.tpm.dao.sql.MpsTlDtlSql;
import com.akranta.tpm.dao.sql.MpsTlImprovementsdtlSql;
import com.akranta.tpm.dao.sql.MpsTlMstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.KznTlGraphdata;
import com.akranta.tpm.model.MpsTlDtl;
import com.akranta.tpm.model.MpsTlImprovementsdtl;
import com.akranta.tpm.model.MpsTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class MpsTlMstDaoImpl implements MpsTlMstDao 
{


	private DBActionTemplate dbActionTemplate; 
	private MpsTlMstSql mpsTlMstSql ; 
	private MpsTlImprovementsdtlSql mpsTlImprovementsdtlSql;
	private MpsTlDtlSql mpsTlDtlSql;

	public MpsTlMstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		mpsTlMstSql = new MpsTlMstSql(); 
		mpsTlImprovementsdtlSql = new MpsTlImprovementsdtlSql();
		mpsTlDtlSql = new MpsTlDtlSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public MpsTlMst create(MpsTlMst mpsTlMst) 	throws Exception
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
		try
		{
			mpsTlMst.setMpsmKeyid(dbActionTemplate.getSequenceNumber(MpsTlMstSql.TBL_MPS_TL_MST, 10, "MPS", "", "Y")); // set the sequnce number 
			sqls.add(MpsTlMstSql.getInsertSql(mpsTlMstSql.getMpsmDbFields(), mpsTlMst.getSaveArray())); // add insert sql for master table
			
			if(mpsTlMst.getMpsTlImprovementsdtl() != null)
			{
				CommonMessage.debugMsg("TEST DTL..........");
				List <MpsTlImprovementsdtl> mpsTlImprovementsdtlList = mpsTlMst.getMpsTlImprovementsdtl();
				for(MpsTlImprovementsdtl mpsTlImprovementsdtl:mpsTlImprovementsdtlList)
				{
					if(mpsTlImprovementsdtl.getMpidActivitydesc().equals("{}")&&mpsTlImprovementsdtl.getMpidAfter().equals("{}")
							&&mpsTlImprovementsdtl.getMpidBefore().equals("{}"))
						continue;
					
					CommonMessage.debugMsg("TEST DTL..........123.......");
						mpsTlImprovementsdtl.setMpidKeyid(mpsTlMst.getMpsmKeyid());
						sqls.add(MpsTlImprovementsdtlSql.getDeleteSql(mpsTlImprovementsdtlSql.getMpidDbFields(), mpsTlImprovementsdtl.getSaveArray()));
						sqls.add(MpsTlImprovementsdtlSql.getInsertSql(mpsTlImprovementsdtlSql.getMpidDbFields(), mpsTlImprovementsdtl.getSaveArray())); 
				}
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return mpsTlMst;
	}
	
	public MpsTlMst update(MpsTlMst mpsTlMst)	throws Exception 
	{ 
		List<String> sqls = new ArrayList<String>();
		try {

			sqls.add(MpsTlMstSql.getUpdateSql(mpsTlMstSql.getMpsmDbFields(), mpsTlMst.getSaveArray()));

			if(mpsTlMst.getMpsTlImprovementsdtl() != null)
			{
				CommonMessage.debugMsg("TEST DTL..........");
				List <MpsTlImprovementsdtl> mpsTlImprovementsdtlList = mpsTlMst.getMpsTlImprovementsdtl();
				for(MpsTlImprovementsdtl mpsTlImprovementsdtl:mpsTlImprovementsdtlList)
				{
					if(mpsTlImprovementsdtl.getMpidActivitydesc().equals("{}")&&mpsTlImprovementsdtl.getMpidAfter().equals("{}")
							&&mpsTlImprovementsdtl.getMpidBefore().equals("{}"))
						continue;
					
					mpsTlImprovementsdtl.setMpidKeyid(mpsTlMst.getMpsmKeyid());
					//if(mpsTlImprovementsdtl.getMpidKeyid() == null)
					{
						//mpsTlImprovementsdtl.setMpidKeyid(dbActionTemplate.getSequenceNumber(MpsTlImprovementsdtlSql.TBL_MPS_TL_IMPROVEMENTSDTL));  
						sqls.add(MpsTlImprovementsdtlSql.getDeleteSql(mpsTlImprovementsdtlSql.getMpidDbFields(), mpsTlImprovementsdtl.getSaveArray())); 
						sqls.add(MpsTlImprovementsdtlSql.getInsertSql(mpsTlImprovementsdtlSql.getMpidDbFields(), mpsTlImprovementsdtl.getSaveArray()));
						CommonMessage.debugMsg("AFTER INSERT MASTER.........."+sqls.get(0));
					}
					//else
						//sqls.add(MpsTlImprovementsdtlSql.getUpdateSql(mpsTlImprovementsdtlSql.getMpidDbFields(), mpsTlImprovementsdtl.getSaveArray())); 
				}
				
			}
			if(mpsTlMst.getMpsTlDtl() != null)
			{
				List <MpsTlDtl> mpsTlDtlList = mpsTlMst.getMpsTlDtl();
				CommonMessage.debugMsg("INSIDE DETAIL ..........");
				for(MpsTlDtl mpsTlDtl:mpsTlDtlList)
				{
					if(mpsTlDtl.getMpsdResultid() == null)
					{
						if(CommonFunctions.isValidKeyId(mpsTlDtl.getMpsdMpsid()))
							mpsTlDtl.setMpsdResultid(mpsTlDtl.getMpsdMpsid());
						mpsTlDtl.setMpsdMpsid(mpsTlMst.getMpsmKeyid());
						//mpsTlDtl.setMpsdResultid(dbActionTemplate.getSequenceNumber(MpsTlDtlSql.TBL_MPS_TL_DTL, 10, preFix, dateFormat, formatReset));  
						sqls.add(MpsTlDtlSql.getInsertSql(mpsTlDtlSql.getMpsdDbFields(), mpsTlDtl.getSaveArray())); 
					}
					else
						sqls.add(MpsTlDtlSql.getDeleteSql(mpsTlDtlSql.getMpsdDbFields(), mpsTlDtl.getSaveArray())); 
				}	
				
			}
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return mpsTlMst;
	}
	
	public MpsTlMst delete(MpsTlMst mpsTlMst)throws Exception 
	{
		
		CommonMessage.debugMsg("INSIDE delete daoimpl ..........");
		List<String> sqls = new ArrayList<String>();
		MpsTlMstSql mpsTlMstSql = new MpsTlMstSql();
		try 
		{
			/*if(mpsTlMst.getMpsTlDtl() != null)
			{
				List <MpsTlDtl> mpsTlDtlList = mpsTlMst.getMpsTlDtl();
				CommonMessage.debugMsg("INSIDE DETAIL ..........");
				for(MpsTlDtl mpsTlDtl:mpsTlDtlList)
				{
					if(mpsTlDtl.getMpsdResultid() != null)
					{
						
						
					}
				}
				
			}*/
			sqls.add(MpsTlMstSql.getDeleteDtlSql(mpsTlMst.getMpsmKeyid()));
			sqls.add(MpsTlMstSql.getDeleteSql(mpsTlMstSql.getMpsmDbFields(), mpsTlMst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return mpsTlMst;
	}

	@Override
	public MpsTlMst select(String keyid) throws Exception 
	{
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:"+keyid);
		MpsTlMst mpsTlMst = new MpsTlMst();

		String sql = MpsTlMstSql.getMpsSelectQuery();
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { keyid };
		mpsTlMst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+mpsTlMst.getMpsmKeyid());
		return  mpsTlMst;

	}

	@Override
	public List<String[]> getModifyFormDao(CommonFilter commonFilter) throws Exception 
	{
		try
		{
			CommonMessage.debugMsg("Inside DAO Impl");
			boolean isInteger = false;
			
			CommonMessage.debugMsg("stat ="+commonFilter.getAbnStatus().getId());
			List<String> paramValues = getFilterParamValues(commonFilter);
			CommonMessage.debugMsg("paramValues Size ="+paramValues.size());
			CommonMessage.debugMsg("paramValue Names="+paramValues.get(0));
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("MPS_PC_MPSHEET.MPS_FN_MPSHEETVU", paramValues);
				
			CommonMessage.debugMsg("Before funct");
			if( commonFilter.getViewClick() == 'Y'){
				CommonMessage.debugMsg("After funct");
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("Total Count:"+totalCnt);
				isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("SIze ==="+dataList.size());
			return dataList;
			}
		
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook mpsExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		  ResultSet rs = null;
		   try
		   {
			   	rs =   getMpsResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,  0, 0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }
	}
	
	private ResultSet getMpsResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return  dbActionTemplate.dbFunctionCall("MPS_PC_MPSHEET.MPS_FN_MPSHEETVU", paramValues);
		
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter)
	{
		List<String> paramValues = new ArrayList<String>();
		CommonMessage.debugMsg("GTE PARAM VAL= "+commonFilter.getFromDate());
		
		String  condParam= FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		CommonMessage.debugMsg("GTE PARAM VAL= "+paramValues.get(0));
		return paramValues;
		
	}

	@Override
	public List<GenTlAllmoduleimgfile> saveMpsImg(MpsTlMst mpsTlMst, MpSheetBean mpSheetBean)	throws Exception
	{
		// TODO Auto-generated method stub
		try
		{
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = mpsTlMst.getAllmoduleimgfile();
			List<String> imflImageType = new ArrayList<String>();
			
			CommonMessage.debugMsg("kznTlMst.getAllmoduleimgfile()"+mpsTlMst.getAllmoduleimgfile());
			
		
			if(mpSheetBean.getAfterImage()!=null && !(mpSheetBean.getAfterImage().equals("")))
			{	
				if(mpSheetBean.getAfterImage().equals("after"))
					imflImageType.add("AFT");
			}
			if(mpSheetBean.getResultImage()!=null && !(mpSheetBean.getResultImage().equals("")))
			{
				if(mpSheetBean.getResultImage().equals("result"))
					imflImageType.add("RES");
			}
			if(mpSheetBean.getBeforeImage()!=null && !(mpSheetBean.getBeforeImage().equals("")))
			{
				if(mpSheetBean.getBeforeImage().equals("present"))
					imflImageType.add("PRE");
			}
			
			for(String kznimageType:imflImageType)
			{
				com.akranta.tpm.utils.CommonMessage.debugMsg("kznimageType =="+kznimageType);
				if(kznimageType!=null && !kznimageType.trim().equals(""))
				{	
					//sqls.add(GenTlAllmoduleimgfileSql.getDeleteImgSql(kznTlMst.getKznmKeyid(),"KZN",imflImageType));
					sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());	
					Object [] delValue	= { mpsTlMst.getMpsmKeyid(),"KZN",kznimageType};
					int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					valueList.add(delValue);
					dataTypes.add(delTypes);
				}
			}
			
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
			{
				CommonMessage.debugMsg("FILE NAME:"+genTlAllmoduleimgfile.getImflFilename());
				CommonMessage.debugMsg("BLOB LENGTH:"+genTlAllmoduleimgfile.getImflBloblength());
				CommonMessage.debugMsg("Image Type= "+genTlAllmoduleimgfile.getImflImagetype());
				CommonMessage.debugMsg("Ref Doc Type= "+genTlAllmoduleimgfile.getImflRefdoctype());
				CommonMessage.debugMsg("REF KEYID "+genTlAllmoduleimgfile.getImflRefkeyid());
				
				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
					continue;
				
				com.akranta.tpm.utils.CommonMessage.debugMsg("Before INSERT");
				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
				
				
				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				java.sql.Timestamp  timeStamp = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStamp(genTlAllmoduleimgfile.getImflModifiedon()); 
				Object [] insValues = { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype(),
				genTlAllmoduleimgfile.getImflBlobimage(),genTlAllmoduleimgfile.getImflBloblength(),
				genTlAllmoduleimgfile.getImflFilename(),genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
				
				valueList.add(delValue);
				valueList.add(insValues);
		
				dataTypes.add(delTypes);
				dataTypes.add(insDataType);
			
			}
			
			CommonMessage.debugMsg("sqls ="+sqls);
			CommonMessage.debugMsg("valueList ="+valueList);
			CommonMessage.debugMsg("dataTypes ="+dataTypes);
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public List<GenTlAllmoduleimgfile> getMpsImage(List<GenTlAllmoduleimgfile> mpsImgList) throws NoDataFoundException, Exception
	{
		CommonMessage.debugMsg("Inside Dao impl Imgh");
		
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:mpsImgList)
		{
			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			String condSql1 = " AND IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND " + condSql ;
			
			String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid(),condSql);
			
			if( fileName != null )
			{
				
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
				
				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
			
				String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
				genTlAllmoduleimgfile.setImflFilename(imgFileName);
				
				CommonMessage.debugMsg(" fileName " + fileNamePath);
				
				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
			}
			
		}
		return genTlAllmoduleimgList;
	}

	@Override
	public List<String[]> getTargetImpData(String mpsKeyid) throws Exception 
	{
		try
		{
			String sql = MpsTlMstSql.getTargetImpSql(mpsKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(mpsKeyid);
			return dbActionTemplate.getDataList(sql);
			//ImprvCategoryList = dbActionTemplate.getDataList(sql);
			//CommonMessage.debugMsg("Dao impl ImprvCategoryList="+ImprvCategoryList.size());
			//return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in get Target Imp dao impl"+e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<String[]> getResultsCreationData(String mpsKeyid)throws Exception 
	{
		try
		{
			String sql = MpsTlMstSql.getResultsSql(mpsKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(mpsKeyid);
			return dbActionTemplate.getDataList(sql);
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in get Results Imp dao impl"+e.getMessage());
		}
		return null;
	}
	

	@Override
	public List<String[]> getMpsCompletedDtls(CommonFilter commonFilter)throws Exception
	{
		// TODO Auto-generated method stub
		try	
		{	
			List<String > paramValues = getFilterParamValues(commonFilter);
			return dbActionTemplate.processFunctionCalls("MPS_PC_MPSHEET.MPS_FN_MPSHEETCOMPLETE", paramValues);
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in MP Sheet Completed Details dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getMpsHDdata(String mpsKeyid) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> mpsHDScanList=null;
		try
		{
			String sql = MpsTlMstSql.getMpsHDScanDataSql(mpsKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			mpsHDScanList = dbActionTemplate.getDataList(sql);
			return mpsHDScanList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in MP Sheet HDScan dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public MpsTlMst updateMpsCompletion(MpsTlMst mpsTlMst) throws Exception 
	{
		List<String> sqls = new ArrayList<String>();
		try 
		{
			sqls.add(MpsTlMstSql.getUpdateCompletionSql(mpsTlMstSql.getMpsmDbFields(), mpsTlMst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mpsTlMst;
	}

	@Override
	public Workbook completeExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		ResultSet rs = null;
		   try
		   {
			   	rs =   getMpsCompleteResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,0,0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }
	}

	private ResultSet getMpsCompleteResultSet(CommonFilter commonFilter) throws Exception {
		
		List<String> paramValues = getFilterParamValues(commonFilter);		
		return  dbActionTemplate.dbFunctionCall("MPS_PC_MPSHEET.MPS_FN_MPSHEETCOMPLETE", paramValues);
	}

	
	
}