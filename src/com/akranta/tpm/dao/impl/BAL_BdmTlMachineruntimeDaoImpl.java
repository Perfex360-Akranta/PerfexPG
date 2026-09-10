package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlMachineruntimeDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlMachineruntimeSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BAL_BdmTlMachineruntime;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BAL_BdmTlMachineruntimeDaoImpl implements BAL_BdmTlMachineruntimeDao {

	BAL_BdmTlMachineruntimeSql bdmTlMachineruntimeSql;
	private DBActionTemplate dbActionTemplate; 

	public BAL_BdmTlMachineruntimeDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		bdmTlMachineruntimeSql = new BAL_BdmTlMachineruntimeSql();
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<BAL_BdmTlMachineruntime> create(List<BAL_BdmTlMachineruntime> mchnHrsList) 	throws Exception,BusinessApplicationExceptions
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(), BAL_BdmTlMachineruntimeSql.TBL_BDM_TL_MACHINERUNTIME, 12, "MCRH", null,null);
		for( BAL_BdmTlMachineruntime bdmTlMachineruntime : mchnHrsList)
		{
			if(UIUtils.isValidKeyId(bdmTlMachineruntime.getMcrhKeyid()))
			{
				if(bdmTlMachineruntime.getIsDelete().equals("N"))
				{
					if(!bdmTlMachineruntime.getMcrhEffectivefrom().equals(bdmTlMachineruntime.getRefEffDate()))
					{
						sqls.add(BAL_BdmTlMachineruntimeSql.getUpdateSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray(),"updateInsert"));
						bdmTlMachineruntime.setMcrhKeyid(sequenceNumber.getSequnceNumber()); // set the sequnce number
						sqls.add(BAL_BdmTlMachineruntimeSql.getInsertSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray()));
					}	
					else 
					{
						sqls.add(BAL_BdmTlMachineruntimeSql.getUpdateSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray(),"updateOnly"));
					}	
				}
				else if(bdmTlMachineruntime.getIsDelete().equals("Y"))
				{
					sqls.add(BAL_BdmTlMachineruntimeSql.getDeleteSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray())); // add insert sql for master table
				}				
			}
			else if(!UIUtils.isValidKeyId(bdmTlMachineruntime.getMcrhKeyid()) )
			{
				if(bdmTlMachineruntime.getIsDelete().equals("N"))
				{
					bdmTlMachineruntime.setMcrhKeyid(sequenceNumber.getSequnceNumber()); // set the sequnce number
					sqls.add(BAL_BdmTlMachineruntimeSql.getInsertSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray()));
				}
			}
		}
		if(sqls.size()<=0)
			throw new BusinessApplicationExceptions("Select Any Line/Machine To Save");
		else
			dbActionTemplate.executeStatements(sqls);
		return mchnHrsList;
	}
	
	public BAL_BdmTlMachineruntime update(BAL_BdmTlMachineruntime bdmTlMachineruntime)	throws Exception { 
		
		/*List<String> sqls = new ArrayList<String>();
		
		try {

			sqls.add(BdmTlMachineruntimeSql.getUpdateSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}*/
		
		return bdmTlMachineruntime;
	}
	
	public BAL_BdmTlMachineruntime delete(BAL_BdmTlMachineruntime bdmTlMachineruntime)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		
		try {
			
			sqls.add(BAL_BdmTlMachineruntimeSql.getDeleteSql(bdmTlMachineruntimeSql.getMcrhDbFields(), bdmTlMachineruntime.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return bdmTlMachineruntime;
	}

	@Override
	public List<String[]> getMachinerRunHrs(String cellId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		String sql=bdmTlMachineruntimeSql.getMchnRunGridSql(cellId,gridParams.getFromRow(),gridParams.getToRow());
		if(gridParams.getGridFilters()!=null)
			sql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		String cnt=dbActionTemplate.getSingleValue("select count(*) from ("+bdmTlMachineruntimeSql.getInnerSqlMchnRun(cellId)+")");
		long count=Long.parseLong(cnt);
		gridParams.setTotalRecordCnt(count);
		List<String[]> result=dbActionTemplate.getDataList(sql);
		return result;
	}

	@Override
	public String getCellOrMchWiseConfig() throws Exception {
		String sql="SELECT CNFM_SETTINGVALUE  FROM ADM_TL_CONFIGURATIONMST,ADM_TL_APPSETTINGS WHERE CNFM_KEYID =APPS_KEYID  AND APPS_CODE ='MCHRUNHOURS_CELLWISE'";
		return dbActionTemplate.getSingleValue(sql);
	}

	@Override
	public Workbook MchnRnHrsExportExcel(JSONObject colmodel, String format, GridParams gridParams,String keyId) throws Exception 
	{
		ResultSet rs = null;
		try
		{
			rs =  getMchnRunHrsResultSet( gridParams,keyId);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			ExcelUtils excelUtils =null;
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(0,0,254)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("N"); //Tick
			condFormat.setIdentfier("N");
			condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
			condFormats.add(condFormat);
		
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(254,0,0)); //red font
			condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(0);
			condFormatTick.setToCol(-1);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("Y"); //Tick
			condFormatTick.setSymbolStr("");
			condFormatTick.setIdentfier("Y");
			condFormats.add(condFormatTick);
			
			excelUtils = new ExcelUtils(colmodel,condFormats);
			excelUtils.setCondFormats(condFormats);
			
			return excelUtils.writeToExcel(rs,format,0,0,0 );
		}
		finally
		{
			if( rs != null)
				DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	    }	
	}

	private ResultSet getMchnRunHrsResultSet(GridParams gridParams,String keyId) throws Exception 
	{
		String sql=bdmTlMachineruntimeSql.getMchnRunGridSql(keyId,gridParams.getFromRow(),gridParams.getToRow());
		sql="SELECT select1,machine,effdate,value1 FROM("+sql+")";
		return dbActionTemplate.getData(sql);
	}
	
}

