package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class CommonFilterServiceImpl implements CommonFilterService{


	
	private CommonFilterDao commonFilterDao ; 
	
	public CommonFilterServiceImpl(DBActionTemplate dbActionTemplate)
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
	}
	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter,String keyid)
			throws Exception {
		StringBuffer condSql = new StringBuffer();
		if(UIUtils.isValidKeyId(keyid)){
			if(keyid.substring(0, 3).equals("CMP"))
				condSql.append(" AND COMP_KEYID != '" + keyid + "'");
		}
		if( condSql.length() > 0){
			commonFilter.getCompany().setCondSql(condSql.toString());
		}
		return commonFilterDao.getCompanyComboList(commonFilter);
	}

	public List<ComboBox> getFactoryComboList(CommonFilter commonFilter,String keyid,String lcnid,String type)
			throws Exception {
		StringBuffer condSql = new StringBuffer();
	
		if(UIUtils.isValidKeyId(lcnid)){
			condSql.append(" and FACT_LOCATIONID ='"+lcnid+"'");
		}
		if(UIUtils.isValidKeyId(keyid)){
			if(keyid.substring(0, 3).equals("FCT"))
				condSql.append(" AND FACT_KEYID != '" + keyid+ "'");
		}
		if( condSql.length() > 0){
			commonFilter.getFactory().setCondSql(condSql.toString());
		}
	
		return commonFilterDao.getFactoryComboList(commonFilter);
	}
//		public List<ComboBox> getFlidComboList(CommonFilter commonFilter)
//		throws Exception {
//			
//			ComboFilter cmbflid = commonFilter.getUom();
//			cmbflid.setIdField("FLID");
//			
//			cmbflid.setNameField("FNLN_DESCRIPTION");
//			cmbflid.setTableName("GEN_MV_FLIDHIERARCHY");			
//			//cmbflid.setCodeField("DECODE(FNLN_ELEMENTTYPE,'C','JH','L','DMT','FNLN_ELEMENTTYPE') ");
//			
//			
//			StringBuilder condSql = new StringBuilder(" AND FNLN_ELEMENTTYPE <> 'M' ");
//			
//			if("LCN".equals(commonFilter.getType())) 
//				condSql.append(" AND fnln_elementtype ='LCN' ");
//			else
//				cmbflid.setCodeField("DECODE(FNLN_ELEMENTTYPE,'C','JH','L','DMT',FNLN_ELEMENTTYPE) ");
//			
//		    if (UIUtils.isValidKeyId(commonFilter.getFlid()))
//		    	condSql.append(" and instr(parentflids,'"+commonFilter.getFlid()+"') > 0 ");
//				
//			
//		    cmbflid.setCondSql(condSql.toString());
//			
//			return commonFilterDao.fillComboValues(cmbflid);
//			
//		}
	
	public List<ComboBox> getFlidComboList(CommonFilter commonFilter) throws Exception {

	    ComboFilter cmbflid = commonFilter.getUom();
	    cmbflid.setIdField("FLID");
	    cmbflid.setNameField("FNLN_DESCRIPTION");
	    cmbflid.setTableName("GEN_MV_FLIDHIERARCHY");

	    StringBuilder condSql = new StringBuilder(" AND FNLN_ELEMENTTYPE <> 'M' ");

	    // If rolelocn = 'LCN' => only LCN rows (same behavior as before)
	    if ("LCN".equals(commonFilter.getType())) {
	        condSql.append(" AND FNLN_ELEMENTTYPE = 'LCN' ");
	        // In your original code you do NOT set codeField for LCN; keep that behavior
	        cmbflid.setCodeField(null);
	    } else {
	        // Oracle DECODE(...) -> PostgreSQL CASE(...)
	        cmbflid.setCodeField(
	            "CASE FNLN_ELEMENTTYPE " +
	            "  WHEN 'C' THEN 'JH' " +
	            "  WHEN 'L' THEN 'DMT' " +
	            "  ELSE FNLN_ELEMENTTYPE " +
	            "END"
	        );
	    }

	    // Oracle INSTR(parentflids, :flid) > 0 -> PostgreSQL position(:flid in coalesce(parentflids,'')) > 0
	    if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
	        String flid = commonFilter.getFlid().replace("'", "''"); // basic literal escaping
	        condSql.append(" AND position('").append(flid).append("' in coalesce(parentflids,'')) > 0 ");
	    }

	    cmbflid.setCondSql(condSql.toString());

	    return commonFilterDao.fillComboValues(cmbflid);
	}
	// commonfilter serviceimpl

	
	public List<ComboBox> getSectionComboList(CommonFilter commonFilter,String keyid,String factoryId)
			throws Exception {
		StringBuffer condSql = new StringBuffer();
		if(UIUtils.isValidKeyId(keyid)){
			if(keyid.substring(0, 3).equals("LIN"))
				condSql.append(" AND SECT_KEYID != '" + keyid + "'");
		}
		if(UIUtils.isValidKeyId(factoryId)){
			condSql.append(" AND SECT_FACTORYID = '" + factoryId + "'");
		}
		if( condSql.length() > 0){
			commonFilter.getSection().setCondSql(condSql.toString());
		}
		return commonFilterDao.getSectionComboList(commonFilter);
	}

	public List<ComboBox> getCellComboList(CommonFilter commonFilter,String keyid,String sectionid)
			throws Exception {
		StringBuffer condSql = new StringBuffer();
		if(UIUtils.isValidKeyId(keyid)){
			if(keyid.substring(0, 3).equals("CEL"))
				condSql.append(" AND CELL_KEYID != '" + keyid + "'");
		}
		if(UIUtils.isValidKeyId(commonFilter.getLineNotToShown()) )
		{
			commonFilter.getCell().setCondSql(" and CELL_KEYID <> '"+commonFilter.getLineNotToShown()+"'");
		}
		if(UIUtils.isValidKeyId(sectionid)){
			condSql.append(" and CELL_SECTIONID= '"+sectionid+"' ");
		}
		if( condSql.length() > 0){
			commonFilter.getCell().setCondSql(condSql.toString());
		}
		CommonMessage.debugMsg(commonFilter.getCell().getCondSql());
		return commonFilterDao.getCellComboList(commonFilter);
	}

//	public List<ComboBox> getMachineComboList(CommonFilter commonFilter,String keyid,String cellid)
//			throws Exception {
//		
//		// TODO Auto-generated method stub
//		StringBuffer condSql = new StringBuffer();
//
//		if(UIUtils.isValidKeyId(cellid)){
//			condSql.append(" and MCHM_CELLID='"+cellid+"' ");
//		}
//		
//		String flid = commonFilter.getFlid();
//		if(UIUtils.isValidKeyId(flid)){
////			condSql.append(" and MCHM_FLID IN (SELECT FLID FROM gen_mv_flidhierarchy WHERE INSTR(PARENTFLIDS ||FLID,'"+flid+"')>0 ) ");
//			condSql.append(" and MCHM_FLID IN (SELECT FLID FROM gen_mv_flidhierarchy WHERE   POSITION('"+flid+"' IN (PARENTFLIDS || FLID)) > 0 )");
//		}
//		if(UIUtils.isValidKeyId(keyid)){
//			condSql.append(" and MCHM_KEYID != '"+keyid+"'");
//		}
//		//CommonMessage.debugMsg(UIUtils.isValidKeyId(commonFilter.getMachineNotToShown()) +"  SevicemachineNotToShown   "+commonFilter.getMachineNotToShown());
//		if( UIUtils.isValidKeyId(commonFilter.getMachineNotToShown()) )
//		{ //CommonMessage.debugMsg("Inside condition");
//			condSql.append(" and MCHM_KEYID <> '"+commonFilter.getMachineNotToShown()+"'");
//		}
//		if(UIUtils.isValidKeyId(commonFilter.getStatus())){
//		String eqpGrpId=commonFilter.getEqpGroup()!=null?commonFilter.getEqpGroup().getId():null;
//		String eqpSubGrp=commonFilter.getEqpSubGroup()!=null?commonFilter.getEqpSubGroup().getId():null;
//		
//		if(commonFilter.getStatus()!=null && UIUtils.isValidKeyId(commonFilter.getStatus()))
//
//        {	CommonMessage.debugMsg("circle id service imp; "+commonFilter.getStatus());
//			condSql.append(" AND MCHM_KEYID IN (SELECT MCLK_MACHINEID FROM "+ TableNames.TBL_GEN_TL_MCHCIRCLELINK + "  WHERE MCLK_ACTIVE = 'Y' AND MCLK_CIRCLEID = '"+commonFilter.getStatus()+"')");
//		}
//		
//		if(commonFilter.getMould() != null && UIUtils.isValidKeyId(commonFilter.getMould().getId())){
//			condSql.append(" AND MCHM_KEYID in ( select MMLK_MACHINEID FROM " + TableNames.TBL_GEN_TL_MOULDMACHINELINK + " Where MMLK_MOULDID = '" + commonFilter.getMould().getId() +"')");
//		}
//		/* added by Dhanalakshmi on 10-6-13*/
//		if(UIUtils.isValidKeyId(eqpGrpId)) 
//		{
//			condSql.append(" AND MCHM_EQUIPMENTGROUP='"+eqpGrpId+"'");
//		}
//		
//		if(UIUtils.isValidKeyId(eqpSubGrp))
//		{
//			condSql.append(" AND MCHM_EQUIPMENTGROUP IN (SELECT EQGM_KEYID FROM "+ TableNames.VIEW_EQG_EQPGROUPMST+"  WHERE  EQGM_SUBGROUPID='"+eqpSubGrp+"')");
//		}
//		}
//		/* Modified by Prasanth   */
//		if (commonFilter.getPcsEnabled() != null && commonFilter.getPcsEnabled().equals("Y")){
//			condSql.append(" AND (");
//			condSql.append(" MCHM_KEYID IN ( SELECT PELC_MACHINEID FROM  " + TableNames.TBL_PCS_TL_ENABLELOSSCAPTURE ); 
//			condSql.append(" WHERE  PELC_ISPCSENABLED = 'Y')  ") ;
//			condSql.append(" OR  MCHM_CELLID IN (SELECT PELC_CELLID FROM   " + TableNames.TBL_PCS_TL_ENABLELOSSCAPTURE ); 
//			condSql.append(" WHERE  PELC_ISPCSENABLED = 'Y')  ") ;
//			condSql.append(" )");
//		}
//		/*commonFilter.getEmpch() returns loged employee id Added for Base page*/
//		if(UIUtils.isValidKeyId(commonFilter.getEmpch())){
//			condSql.append("  AND MCHM_KEYID in( select efll_funclocn from GEN_TL_EMPFUNCLOCNLINK  where EFLL_FUNCLOCNTYPE = 'MCHM'  AND  EFLL_EMPLOYEEID  = '"+commonFilter.getEmpch()+"')");
//		}
//		/*if( UIUtils.isValidKeyId(commonFilter.getFlid())){
//			condSql.append("  AND MCHM_KEYID in (select FNLN_ORIGINALID from GEN_VW_FLIDHIERARCHY where instr(parentFlids,'" + commonFilter.getFlid() + "') > 0 and fnln_elementtype='M') " ); 
//		}*/
//		//CommonMessage.debugMsg("condSql  "+condSql);
//		if( condSql.length() > 0){
//			commonFilter.getMachine().setCondSql(condSql.toString());
//		}
//		
//		/*if("grid".equals(commonFilter.getMachine().getMode()))
//		{
//			newSql.append("select MCHM_KEYID as ID,MCHM_MACHINENO||'-'||MCHM_MACHINENAME as Text,CELL_NAME as Cell FROM GEN_VW_FACTORYLAYOUT WHERE MCHM_CELLID = CELL_KEYID");
//			commonFilter.getMachine().setNewSelectQuery(newSql.toString());
//		}*/
//		return commonFilterDao.getMachineComboList(commonFilter);
//	}
	
	public List<ComboBox> getMachineComboList(CommonFilter commonFilter,String keyid,String cellid)
			throws Exception {
		
		// TODO Auto-generated method stub
		StringBuffer condSql = new StringBuffer();

		if(UIUtils.isValidKeyId(cellid)){
			condSql.append(" and MCHM_CELLID='"+cellid+"' ");
		}
		
		String flid = commonFilter.getFlid();
		if(UIUtils.isValidKeyId(flid)){
			condSql.append(" and MCHM_FLID IN (SELECT FLID FROM gen_mv_flidhierarchy WHERE POSITION('"+flid+"',PARENTFLIDS || FLID)>0 ) ");
		}
		if(UIUtils.isValidKeyId(keyid)){
			condSql.append(" and MCHM_KEYID != '"+keyid+"'");
		}
		//CommonMessage.debugMsg(UIUtils.isValidKeyId(commonFilter.getMachineNotToShown()) +"  SevicemachineNotToShown   "+commonFilter.getMachineNotToShown());
		if( UIUtils.isValidKeyId(commonFilter.getMachineNotToShown()) )
		{ //CommonMessage.debugMsg("Inside condition");
			condSql.append(" and MCHM_KEYID <> '"+commonFilter.getMachineNotToShown()+"'");
		}
		if(UIUtils.isValidKeyId(commonFilter.getStatus())){
		String eqpGrpId=commonFilter.getEqpGroup()!=null?commonFilter.getEqpGroup().getId():null;
		String eqpSubGrp=commonFilter.getEqpSubGroup()!=null?commonFilter.getEqpSubGroup().getId():null;
		
		if(commonFilter.getStatus()!=null && UIUtils.isValidKeyId(commonFilter.getStatus()))

        {	CommonMessage.debugMsg("circle id service imp; "+commonFilter.getStatus());
			condSql.append(" AND MCHM_KEYID IN (SELECT MCLK_MACHINEID FROM "+ TableNames.TBL_GEN_TL_MCHCIRCLELINK + "  WHERE MCLK_ACTIVE = 'Y' AND MCLK_CIRCLEID = '"+commonFilter.getStatus()+"')");
		}
		
		if(commonFilter.getMould() != null && UIUtils.isValidKeyId(commonFilter.getMould().getId())){
			condSql.append(" AND MCHM_KEYID in ( select MMLK_MACHINEID FROM " + TableNames.TBL_GEN_TL_MOULDMACHINELINK + " Where MMLK_MOULDID = '" + commonFilter.getMould().getId() +"')");
		}
		/* added by Dhanalakshmi on 10-6-13*/
		if(UIUtils.isValidKeyId(eqpGrpId)) 
		{
			condSql.append(" AND MCHM_EQUIPMENTGROUP='"+eqpGrpId+"'");
		}
		
		if(UIUtils.isValidKeyId(eqpSubGrp))
		{
			condSql.append(" AND MCHM_EQUIPMENTGROUP IN (SELECT EQGM_KEYID FROM "+ TableNames.VIEW_EQG_EQPGROUPMST+"  WHERE  EQGM_SUBGROUPID='"+eqpSubGrp+"')");
		}
		}
		/* Modified by Prasanth   */
		if (commonFilter.getPcsEnabled() != null && commonFilter.getPcsEnabled().equals("Y")){
			condSql.append(" AND (");
			condSql.append(" MCHM_KEYID IN ( SELECT PELC_MACHINEID FROM  " + TableNames.TBL_PCS_TL_ENABLELOSSCAPTURE ); 
			condSql.append(" WHERE  PELC_ISPCSENABLED = 'Y')  ") ;
			condSql.append(" OR  MCHM_CELLID IN (SELECT PELC_CELLID FROM   " + TableNames.TBL_PCS_TL_ENABLELOSSCAPTURE ); 
			condSql.append(" WHERE  PELC_ISPCSENABLED = 'Y')  ") ;
			condSql.append(" )");
		}
		/*commonFilter.getEmpch() returns loged employee id Added for Base page*/
		if(UIUtils.isValidKeyId(commonFilter.getEmpch())){
			condSql.append("  AND MCHM_KEYID in( select efll_funclocn from GEN_TL_EMPFUNCLOCNLINK  where EFLL_FUNCLOCNTYPE = 'MCHM'  AND  EFLL_EMPLOYEEID  = '"+commonFilter.getEmpch()+"')");
		}
		/*if( UIUtils.isValidKeyId(commonFilter.getFlid())){
			condSql.append("  AND MCHM_KEYID in (select FNLN_ORIGINALID from GEN_VW_FLIDHIERARCHY where instr(parentFlids,'" + commonFilter.getFlid() + "') > 0 and fnln_elementtype='M') " ); 
		}*/
		//CommonMessage.debugMsg("condSql  "+condSql);
		if( condSql.length() > 0){
			commonFilter.getMachine().setCondSql(condSql.toString());
		}
		
		/*if("grid".equals(commonFilter.getMachine().getMode()))
		{
			newSql.append("select MCHM_KEYID as ID,MCHM_MACHINENO||'-'||MCHM_MACHINENAME as Text,CELL_NAME as Cell FROM GEN_VW_FACTORYLAYOUT WHERE MCHM_CELLID = CELL_KEYID");
			commonFilter.getMachine().setNewSelectQuery(newSql.toString());
		}*/
		return commonFilterDao.getMachineComboList(commonFilter);
	}
	
	public List<ComboBox> getproductComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter product = commonFilter.getproduct();
		product.setIdField("PRDM_KEYID");
		product.setCodeField("PRDM_CODE");
		product.setNameField("PRDM_NAME");		
		product.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);		
		
		StringBuffer condSql = new StringBuffer();
		StringBuffer condSql1 = new StringBuffer();
		
		String machineId = commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null;
		String cellId = commonFilter.getCell() != null ? commonFilter.getCell().getId() : null;
		String sectId = commonFilter.getSection() != null ? commonFilter.getSection().getId() : null;
		
		condSql.append("  SELECT CYTM_PRODUCTID FROM  " );
		condSql.append(  TableNames.TBL_PCS_TL_CYCLETIMEMST + " WHERE 1=1 " );
		if( CommonFunctions.isValidKeyId(machineId))
		{
			condSql1.append( " AND CYTM_MACHINEID = '" + machineId + "'");
		}
		if(CommonFunctions.isValidKeyId(cellId))	
		{			
			condSql1.append( " AND CYTM_CELLID = '" + cellId + "'");
		}
		
		if(condSql1.length() > 0 )
			condSql.append(condSql1);
		
		product.setCondSql(" AND PRDM_KEYID IN ( " + condSql.toString() + ")");
		
		return commonFilterDao.fillComboValues(product);
	}
	public List<ComboBox> getCostCentreComboList(CommonFilter commonFilter)	throws Exception {	
		
		ComboFilter costCenter = commonFilter.getCostCenter();
		costCenter.setIdField("CSTM_KEYID");
		costCenter.setCodeField("CSTM_CODE");
		costCenter.setNameField("CSTM_NAME");
		costCenter.setTableName(TableNames.TBL_GEN_TL_COSTCENTREMST);
		
		StringBuffer condSql = new StringBuffer();
		String machineId = commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null;
		String cellId = commonFilter.getCell() != null ? commonFilter.getCell().getId() : null;
		String factId = commonFilter.getFactory() != null ? commonFilter.getFactory().getId() : null;
		
		
		if( CommonFunctions.isValidKeyId(machineId))
		{
			condSql.append("  SELECT MCHM_COSTCENTREID FROM  " );
			condSql.append(  TableNames.TBL_GEN_TL_MACHINEMST );
			condSql.append( " WHERE MCHM_KEYID = '" + machineId + "'");
		}
		else if( CommonFunctions.isValidKeyId(cellId)  )
		{	
			condSql.append("  SELECT CELL_COSTCENTREID FROM  " + TableNames.TBL_GEN_TL_CELLMST  );
			condSql.append( " where CELL_KEYID = '" + cellId + "' ");
		}
		else if(CommonFunctions.isValidKeyId(factId)){
		
			condSql.append("SELECT CSTM_KEYID  FROM " +TableNames.TBL_GEN_TL_COSTCENTREMST + " WHERE CSTM_ACTIVE = 'Y' " ); 
		
			condSql.append(" AND CSTM_FACTORYID = '" + factId + "'");
			
			condSql.append(" Minus SELECT DISTINCT CELL_COSTCENTREID   FROM " + TableNames.TBL_GEN_TL_CELLMST );
			
			if(CommonFunctions.isValidKeyId(cellId) ){
			
			    condSql.append(" WHERE CELL_KEYID != '" + cellId + "'" );
			}
	
		}
			
		if(condSql.length() > 0 )
			costCenter.setCondSql( " AND CSTM_KEYID IN ( " + condSql + ")");
				
		return commonFilterDao.fillComboValues(costCenter);
	
		
	}
	public List<ComboBox> getLocationComboList(CommonFilter commonFilter,String keyid)throws Exception {
		

		ComboFilter locn = commonFilter.getLocation();
		locn.setIdField("LOCN_KEYID");
		locn.setCodeField("LOCN_CODE");
		locn.setNameField("LOCN_NAME");
		locn.setTableName(TableNames.TBL_GEN_TL_LOCATIONMST);
		
		if(UIUtils.isValidKeyId(keyid)){
			locn.setCondSql(" AND LOCN_KEYID  != '" +keyid+ "'");
		}
		//StringBuffer condSql = new StringBuffer();
		if( commonFilter.getCompany() != null){
			locn.setCondSql(" AND LOCN_COMPANYID = '" + commonFilter.getCompany().getId() + "'");
		}	
		//locn.setCondSql(condSql.toString());
		
		return commonFilterDao.fillComboValues(locn);
	}
	

	public List<ComboBox> getSubCellComboList(CommonFilter commonFilter)throws Exception {
		

		ComboFilter sbcl = commonFilter.getLocation();
		sbcl.setIdField("SBCL_KEYID");
		sbcl.setCodeField("SBCL_CODE");
		sbcl.setNameField("SBCL_NAME");
		sbcl.setTableName(TableNames.TBL_GEN_TL_SUBCELLMST);
		
		StringBuffer condSql = new StringBuffer();
		if( (commonFilter.getCostCenter() != null && commonFilter.getCostCenter().getId() != null))
			condSql.append(" AND CELL_COSTCENTREID = '" + commonFilter.getCostCenter().getId() + "'");

		sbcl.setCondSql(condSql.toString());
		
		return commonFilterDao.fillComboValues(sbcl);
	}
	public List<ComboBox> getUomComboList(CommonFilter commonFilter)throws Exception {
		

		ComboFilter uom = commonFilter.getUom();
		uom.setIdField("UOMM_KEYID");
		uom.setCodeField("UOMM_CODE");
		uom.setNameField("UOMM_DESCRIPTION");
		uom.setTableName(TableNames.TBL_ADM_TL_UOMMST);
		
		StringBuffer condSql = new StringBuffer();

			
		uom.setCondSql(condSql.toString());
		
		return commonFilterDao.fillComboValues(uom);
	}
	@Override
	public List<ComboBox> getEmployeeComboList(ComboFilter empComboFillter,String rtal)
			throws Exception {
		
		
		if (!UIUtils.isValidKeyId( empComboFillter.getCondSql()))
				empComboFillter.setCondSql(" AND 1 = 1 " );
		
		if( UIUtils.isValidKeyId(rtal) )
		{
			if("empFilter".equals(rtal)){
				
			}
			else{
				String[] rtlRole = rtal.split(",");
			 CommonMessage.debugMsg(rtlRole[0]+" -- "+rtlRole[1]);
				if(UIUtils.isValidKeyId(rtlRole[1]))
					empComboFillter.setCondSql(empComboFillter.getCondSql() + "  AND  EMPM_ROLEID = '" + rtlRole[1] + "' AND EMPM_KEYID NOT IN ( SELECT EREL_EMPM_KEYID FROM ENT_TL_ROLE_EMP_LINK WHERE EREL_RTAL_KEYID = '" + rtlRole[0] + "')");
				else
					empComboFillter.setCondSql(empComboFillter.getCondSql() + " AND EMPM_KEYID NOT IN( SELECT EREL_EMPM_KEYID FROM ENT_TL_ROLE_EMP_LINK WHERE EREL_ACTIVE = 'Y')" );
			}
		}
		
//		if (UIUtils.isValidKeyId( empComboFillter.getCondSql()))
//			empComboFillter.setCondSql(empComboFillter.getCondSql() + " AND EMPM_ACTIVE='Y' ");
		
		return commonFilterDao.getEmployeeComboList(empComboFillter);
		
	}
	
	


	@Override
	public List<String[]> getMachineHierarchy(String eqpID) {
		// TODO Auto-generated method stub
		return this.commonFilterDao.getMachineHierarchy(eqpID);
	}
	public List<String[]> getCostCenterRelCell(String costCenterID) {
		// TODO Auto-generated method stub
		return this.commonFilterDao.getCostCenterRelCell(costCenterID);
	}	
	
	
	
	/*
	 * public List<ComboBox> getAssemblyComboList(CommonFilter commonFilter,String
	 * relatedTo) throws Exception {
	 * 
	 * ComboFilter assembly =commonFilter.getAssembly(); String machineId =
	 * (commonFilter.getMachine() != null ? commonFilter.getMachine().getId() :
	 * null); if( ! UIUtils.isValidKeyId(relatedTo) || ! "MLD".equals(relatedTo)){
	 * assembly.setIdField("ASSEMBLYID"); assembly.setNameField("ASSEMBLYNAME");
	 * 
	 * if( UIUtils.isValidKeyId(machineId) ){
	 * assembly.setCondSql(" AND MACHINEID = '" + machineId + "'"); }
	 * 
	 * assembly.setTableName(TableNames.TBL_GEN_VW_MCHASMLINK); } else {
	 * assembly.setIdField("ASSM_KEYID"); assembly.setNameField("ASSM_NAME");
	 * assembly.setCondSql(" AND ASSM_RELATEDTO = 'MLD'");
	 * assembly.setTableName(TableNames.TBL_GEN_TL_ASSEMBLYMST); if(
	 * UIUtils.isValidKeyId(machineId)) { assembly.
	 * setCondSql(" AND ASSM_KEYID IN( SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE INSTR(FNLN_ELEMENTID,'"
	 * +machineId+"',1,1)>0 AND FNLN_ELEMENTTYPE='A'"); } }
	 * CommonMessage.debugMsg("machineId:"+machineId); return
	 * commonFilterDao.fillComboValues(assembly); }
	 */
	
	public List<ComboBox> getAssemblyComboList(CommonFilter commonFilter, String relatedTo) throws Exception {

	    ComboFilter assembly = commonFilter.getAssembly();
	    String machineId = (commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null);
	    String machineNotToShown = commonFilter.getMachineNotToShown();

	    if (!UIUtils.isValidKeyId(relatedTo) || !"MLD".equals(relatedTo)) {
	        assembly.setIdField("ASSEMBLYID");
	        assembly.setNameField("ASSEMBLYNAME");

	        String condSql = "";
	        if (UIUtils.isValidKeyId(machineId)) {
	            condSql += " AND MACHINEID = '" + machineId + "'";
	        }
	        if (UIUtils.isValidKeyId(machineNotToShown)) {
	            condSql += " AND MACHINEID <> '" + machineNotToShown + "'";
	        }
	        assembly.setCondSql(condSql);

	        assembly.setTableName(TableNames.TBL_GEN_VW_MCHASMLINK);
	    } else {
	        assembly.setIdField("ASSM_KEYID");
	        assembly.setNameField("ASSM_NAME");

	        String condSql = " AND ASSM_RELATEDTO = 'MLD'";
	        if (UIUtils.isValidKeyId(machineId)) {
	            condSql += " AND ASSM_KEYID IN( SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE INSTR(FNLN_ELEMENTID,'" + machineId + "',1,1)>0 AND FNLN_ELEMENTTYPE='A')";
	        }
	        if (UIUtils.isValidKeyId(machineNotToShown)) {
	            condSql += " AND ASSM_KEYID NOT IN( SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE INSTR(FNLN_ELEMENTID,'" + machineNotToShown + "',1,1)>0 AND FNLN_ELEMENTTYPE='A')";
	        }
	        assembly.setCondSql(condSql);

	        assembly.setTableName(TableNames.TBL_GEN_TL_ASSEMBLYMST);
	    }
	    CommonMessage.debugMsg("machineId:" + machineId + " machineNotToShown:" + machineNotToShown);
	    return commonFilterDao.fillComboValues(assembly);
	}
	
	// end
	
		public List<ComboBox> getSubassemblyComboList(CommonFilter commonFilter)	throws Exception {

			ComboFilter subAssembly = commonFilter.getSubassembly();
			subAssembly.setIdField("SBAM_KEYID");
			subAssembly.setCodeField("SBAM_CODE");
			subAssembly.setNameField("SBAM_NAME");
			subAssembly.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST);
			
			//StringBuffer condSql = new StringBuffer();
			
			return commonFilterDao.fillComboValues(subAssembly);
		}
		
		public List<ComboBox> getSpareComboList(CommonFilter commonFilter)	throws Exception {

			ComboFilter spare = commonFilter.getSpare();
			spare.setIdField("SPRM_KEYID");
			spare.setCodeField("SPRM_PARTNO");
			spare.setNameField("SPRM_PARTNAME");
			spare.setTableName(TableNames.TBL_GEN_TL_SPARESMST);
			StringBuffer condSql = new StringBuffer();
			String assmId=commonFilter.getAssembly()!=null?commonFilter.getAssembly().getId():null;
			String machineId = commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null;
			/*Added By Dhanalakshmi*/
			if(UIUtils.isValidKeyId(assmId))
			{
				//condSql.append(" AND  SPRM_KEYID IN(SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE  INSTR(FNLN_ELEMENTID,'"+assmId+"',1,1)>0 AND FNLN_ELEMENTTYPE='SPR')");
				condSql.append(" AND  SPRM_KEYID IN(SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE  POSITION('"+assmId+"' IN FNLN_ELEMENTID) > 0   AND FNLN_ELEMENTTYPE='SPR')");
			}
			if(UIUtils.isValidKeyId(machineId))
			{
			//	condSql.append(" AND  SPRM_KEYID IN(SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE   INSTR(FNLN_ELEMENTID,'"+machineId+"',1,1)>0 AND FNLN_ELEMENTTYPE='SPR')");
				condSql.append(" AND  SPRM_KEYID IN(SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE POSITION('"+machineId+"' IN FNLN_ELEMENTID) > 0   AND FNLN_ELEMENTTYPE='SPR')");
			}
			if(condSql.length()>0)
				spare.setCondSql(condSql.toString());
			return commonFilterDao.fillComboValues(spare);
		}		
	/*

	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter) throws Exception {
			
			
			CommonMessage.debugMsg(" Company common service impl");
			ComboFilter comp = commonFilter.getCompany();
			comp.setIdField("COMP_KEYID");
			comp.setCodeField("COMP_CODE");
			comp.setNameField("COMP_NAME");
			comp.setTableName(TableNames.TBL_GEN_TL_COMPANYMST);
			
			return commonFilterDao.fillComboValues(comp);
			//return commonFilterDao.getCompanyComboList(commonFilter);
		}






	-------------------------------factory




		public List<ComboBox> getFactoryComboList(CommonFilter commonFilter) throws Exception {
			CommonMessage.debugMsg(" factory common service impl");
			ComboFilter fact = commonFilter.getFactory();
			fact.setIdField("FACT_KEYID");
			fact.setCodeField("FACT_CODE");
			fact.setNameField("FACT_NAME");
			fact.setTableName(TableNames.TBL_GEN_TL_FACTORYMST);
							
			return commonFilterDao.fillComboValues(fact);
			
			//return commonFilterDao.getFactoryComboList(commonFilter);
		}






	--------------------------section



		public List<ComboBox> getSectionComboList(CommonFilter commonFilter) throws Exception {
			CommonMessage.debugMsg(" section common service impl");
			ComboFilter sect = commonFilter.getSection();
			sect.setIdField("SECT_KEYID");
			sect.setCodeField("SECT_CODE");
			sect.setNameField("SECT_NAME");
			sect.setTableName(TableNames.TBL_GEN_TL_SECTIONMST);
			
			return commonFilterDao.fillComboValues(sect);
			//return commonFilterDao.getSectionComboList(commonFilter);
		}



	-----------------------------shift


	public List<ComboBox> getShiftComboList(CommonFilter commonFilter)	throws Exception {
			CommonMessage.debugMsg(" common shift service  impl");
			ComboFilter shift = commonFilter.getShift();
			shift.setIdField("SFTM_KEYID");
			shift.setCodeField("SFTM_CODE");
			shift.setNameField("SFTM_NAME");
			shift.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		
			return commonFilterDao.fillComboValues(shift);
		}





	--------------------------------cell




	public List<ComboBox> getCellComboList(CommonFilter commonFilter) throws Exception {
			
			ComboFilter cell = commonFilter.getCell();
			cell.setIdField("CELL_KEYID");
			cell.setCodeField("CELL_CODE");
			cell.setNameField("CELL_NAME");
			cell.setTableName(TableNames.TBL_GEN_TL_CELLMST);
			
			return commonFilterDao.fillComboValues(cell);
		}
		
*/
	@Override
	public List<String[]> getCellHierarchy(String cellId) {
		
		return commonFilterDao.getCellHierarchy(cellId);
	}
	public List<String[]> getSubUnitHierarchy(String subUnitId)
	{
		return commonFilterDao.getSubUnitHierarchy(subUnitId);
	}
	@Override
	public List<String[]> getSectionHierarchy(String sectionId) {
		// TODO Auto-generated method stub
		return commonFilterDao.getSectionHierarchy(sectionId);
	}
	
	public List<String[]> getfactoryHierarchy(String fctId)  {
		
		return commonFilterDao.getfactoryHierarchy(fctId);
	}
	public List<String[]> getlocationHierarchy(String lcnId)  {
		
		return commonFilterDao.getlocationHierarchy(lcnId);
	}
	
	public List<String[]> getSbuHierarchy(String sbuId) {
		return null;//return commonFilterDao.getSbuHierarchy(sbuId);
	}
	public List<String[]> getCityHierarchy(String cityId) {
		
		return commonFilterDao.getCityHierarchy(cityId);
	}
	
	public List<ComboBox> getEqpGroupComboList(CommonFilter commonFilter) throws Exception 
	{
		ComboFilter eqpGroup = commonFilter.getEqpGroup();
		eqpGroup.setIdField("EQGM_KEYID");
		eqpGroup.setNameField("EQGM_NAME");
		eqpGroup.setTableName(TableNames.TBL_GEN_TL_EQPGROUPMST);
		String factId = null;
		StringBuffer condSql = new StringBuffer();
		
		
		/* Modified by Dhanalakshmi 10-6-13*/
		
		if(commonFilter.getMachineId()!=null && UIUtils.isValidKeyId(commonFilter.getMachineId()))
		{
			condSql.append(" AND EQGM_KEYID IN(SELECT MCHM_EQUIPMENTGROUP FROM GEN_TL_MACHINEMST WHERE MCHM_KEYID='"+commonFilter.getMachineId()+"')");
		}
		if(commonFilter.getEqpSubGroup()!=null && UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
		{
			condSql.append(" AND EQGM_KEYID IN(SELECT EQGM_KEYID FROM "+TableNames.VIEW_EQG_EQPGROUPMST+"  WHERE EQGM_SUBGROUPID='"+commonFilter.getEqpSubGroup().getId()+"')");
		}
		else if( commonFilter.getFactory() != null )
		{
			factId = commonFilter.getFactory().getId(); 
			if( UIUtils.isValidKeyId(factId) )
			{
				condSql.append(" AND EQGM_FACTORYID = '");
				condSql.append( factId + "'");				
			}		
		}
		eqpGroup.setCondSql(condSql.toString());
		return commonFilterDao.fillComboValues(eqpGroup);
	}
	/*Added By Dhanalakshmi.R*/
	@Override
	public List<ComboBox> getEqpSubGroupComboList(CommonFilter commonFilter)throws Exception
	{
		ComboFilter eqpSubGroup = commonFilter.getEqpSubGroup();
		StringBuffer condSql = new StringBuffer();
		eqpSubGroup.setNameField("SUBGROUPNAME");
		eqpSubGroup.setIdField("EQGM_SUBGROUPID");
		eqpSubGroup.setTableName(TableNames.VIEW_EQG_EQPGROUPMST);
		if(commonFilter.getEqpGroup()!=null && UIUtils.isValidKeyId(commonFilter.getEqpGroup().getId()))
		{
			condSql.append(" AND EQGM_KEYID='"+commonFilter.getEqpGroup().getId()+ "'");
		}
		if(commonFilter.getMachineId()!=null && UIUtils.isValidKeyId(commonFilter.getMachineId()))
		{
			condSql.append(" AND EQGM_SUBGROUPID IN( SELECT EQGM_SUBGROUPID FROM EQG_VW_EQPGROUPMST,GEN_TL_MACHINEMST WHERE EQGM_KEYID=MCHM_EQUIPMENTGROUP AND MCHM_KEYID='"+commonFilter.getMachineId()+"')");
		}
		if(commonFilter.getEqpSubGroup().getId()!=null && UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
		{
			condSql.append(" AND EQGM_SUBGROUPID='"+commonFilter.getEqpSubGroup().getId()+"'");
		}
		if(UIUtils.isValidKeyId(condSql.toString()))
			eqpSubGroup.setCondSql(condSql.toString());
			CommonMessage.debugMsg("before Fill Combo values");
		return commonFilterDao.fillComboValues(eqpSubGroup);
	}
	@Override
	public List<ComboBox> getCircleComboList(CommonFilter commonFilter)	throws Exception {
		ComboFilter circle = commonFilter.getCircle();
		CommonMessage.debugMsg("inside circle");
		circle.setIdField("CRCM_KEYID");
		//circle.setCodeField("CRCM_CODE");
		circle.setNameField("CRCM_NAME");
		circle.setTableName(TableNames.TBL_GEN_TL_CIRCLEMST);
		
		String machineId = commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null;
		String cellId = commonFilter.getCell() != null ? commonFilter.getCell().getId() : null;
		String sectId = commonFilter.getSection() != null ? commonFilter.getSection().getId() : null;
		String factId = commonFilter.getFactory() != null ? commonFilter.getFactory().getId() : null;
		
		StringBuffer condSql = new StringBuffer();
		
		if( CommonFunctions.isValidKeyId(machineId)){
			condSql.append(" and CRCM_keyid in ( select MCLK_CIRCLEID FROM GEN_TL_MCHCIRCLELINK where MCLK_MACHINEID = '");
			condSql.append( machineId + "')");
			circle.setCondSql(condSql.toString());
		}
		/* SINCE FACTORY TO CHECK 
		 * else if( CommonFunctions.isValidKeyId(factId) ){
			condSql.append(" and CRCM_FACTORYID = '" + factId + "'");
		} 
		else if( UIUtils.isValidKeyId(cellId) ){
			condSql.append(" and CRCM_FACTORYID in ( select CELL_FACTORYID FROM GEN_TL_CELLMST where CELL_KEYID = '");
			condSql.append( cellId + "')");
		}	
		else if( UIUtils.isValidKeyId(sectId) ){
			condSql.append(" and CRCM_FACTORYID in ( select SECT_FACTORYID FROM GEN_TL_SECTIONMST where SECT_KEYID = '");
			condSql.append( sectId + "')");
		}*/ 
		
		if( condSql != null)
			circle.setCondSql(condSql.toString() +" AND CRCM_ACTIVE = 'Y' ");
		
		return commonFilterDao.fillComboValues(circle);
	}
	@Override
	public List<ComboBox> getTradeComboList(CommonFilter commonFilter) throws Exception {
		ComboFilter trade = commonFilter.getTrade();
		trade.setIdField("TRDM_KEYID");
		//trade.setCodeField("TRDM_CODE");
		trade.setNameField("TRDM_NAME");
		trade.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		
		return commonFilterDao.fillComboValues(trade);
	}
	@Override
	public List<ComboBox> getETTradeComboList(CommonFilter commonFilter) throws Exception {
		ComboFilter trade = commonFilter.getTrade();
		trade.setIdField("ETFN_KEYID");
		//trade.setCodeField("TRDM_CODE");
		trade.setNameField("ETFN_NAME");
		trade.setTableName(TableNames.TBL_ENT_TL_TRGCALFUNCTION);
		
		return commonFilterDao.fillComboValues(trade);
	}
	@Override
	public List<ComboBox> getMachineRankComboList(CommonFilter commonFilter)
			throws Exception {
		ComboFilter machine = commonFilter.getMachineRank();
		machine.setIdField("MCHM_MACHINERANK");
		machine.setCodeField("MCHM_MACHINERANK");
		//machine.setNameField("MCHM_MACHINENAME");
		
		String condSql ="";
		condSql = " AND MCHM_MACHINERANK <> '{}'";
		machine.setCondSql(condSql);
		machine.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		
		return commonFilterDao.fillComboValues(machine);
	}

	
	public List<ComboBox> getJhAuditLevelIdComboList(CommonFilter commonFilter) throws Exception {
		ComboFilter jhStep = commonFilter.getJhStep();
		jhStep.setIdField("JHAT_KEYID");
		//jhStep.setCodeField("JHSM_CODE");
		jhStep.setNameField("JHAT_NAME");
		jhStep.setTableName(TableNames.TBL_JHA_TL_AUDITTEAM);
		
		return commonFilterDao.fillComboValues(jhStep);	
	}
	public List<ComboBox> getJhAuditComboList(CommonFilter commonFilter) throws Exception {
		ComboFilter jhAudit = commonFilter.getJhStep();
		jhAudit.setIdField("JHAM_KEYID");
		//jhStep.setCodeField("JHSM_CODE");
		jhAudit.setNameField("JHAM_AUDITORNAME");
		jhAudit.setTableName(TableNames.TBL_JHA_TL_AUDITMST);
		
		return commonFilterDao.fillComboValues(jhAudit);	
	}
	public List<ComboBox> getProdcngroupComboList(CommonFilter commonFilter) throws Exception {
		
		ComboFilter prodcngroup = commonFilter.getCmbprodcngroup();
		//prodcngroup.setIdField("PGM");
/*		prodcngroup.setCodeField("PGMCODE");
		prodcngroup.setNameField("PGMNAME");
		prodcngroup.setTableName(TableNames.TBL_GEN_VW_MACHINEPRODUCTION);*/		
		return commonFilterDao.fillComboValues(prodcngroup);
	}
	public List<ComboBox> getOplNoComboList(CommonFilter commonFilter)
			throws Exception {
		ComboFilter oplNo = commonFilter.getOplNoid();
		oplNo.setIdField("OPLM_KEYID");
		//jhStep.setCodeField("JHSM_CODE");
		oplNo.setNameField("OPLM_KEYID");
		oplNo.setTableName(TableNames.TBL_OPL_TL_MST);
		
		return commonFilterDao.fillComboValues(oplNo);	
	}



public List<ComboBox> getGrpByCellComboList(CommonFilter commonFilter)
			throws Exception {
		/*ComboFilter grpcell = commonFilter.getGrpByCellid();
		grpcell.setIdField("TPMP_KEYID");
		grpcell.setCodeField("TPMP_CODE");
		grpcell.setNameField("TPMP_NAME");
		grpcell.setTableName(TableNames.TBL_GEN_TL_TPMPILLARMST);
		
		return commonFilterDao.fillComboValues(grpcell);*/	
	return null;
	}

	public List<ComboBox> getImprovmntNoComboList(CommonFilter commonFilter)
			throws Exception {
		ComboFilter impNo = commonFilter.getImprovmntNoid();
		impNo.setIdField("KZNM_KEYID");
		impNo.setCodeField("KZNM_KEYID");
		//impNo.setNameField("KZNM_DATE");
		impNo.setTableName(TableNames.TBL_KZN_TL_MST);
		
		return commonFilterDao.fillComboValues(impNo);	
	}
	@Override
	public List<ComboBox> getPillarComboList(CommonFilter commonFilter)
			throws Exception {
		ComboFilter pillar = commonFilter.getPillarid();
		pillar.setIdField("TPMP_KEYID");
		pillar.setCodeField("TPMP_CODE");
		CommonMessage.debugMsg(commonFilter.getType());
		if(!"MOM".equals(commonFilter.getType()))
		{
			pillar.setNameField("TPMP_NAME");
		}
			
		pillar.setTableName(TableNames.TBL_GEN_TL_TPMPILLARMST);
		
		return commonFilterDao.fillComboValues(pillar);	
	}
	
	public List<ComboBox> getShiftComboList(CommonFilter commonFilter)	throws Exception {
		CommonMessage.debugMsg(" common shift service  impl");
		ComboFilter shift = commonFilter.getShift();
		if(UIUtils.isValidKeyId(commonFilter.getWostatus())){
			if(commonFilter.getWostatus().equals("yes"))
				shift.setIdField("SFTM_KEYID");
			else
				shift.setIdField("SFTM_CODE");
			shift.setCodeField("SFTM_CODE");
		}
		else{
			shift.setIdField("SFTM_KEYID");			
			shift.setNameField("SFTM_NAME");
		}
		//shift.setNameField("SFTM_NAME");SFTM_KEYID

		String factId = commonFilter.getFactory() != null ? commonFilter.getFactory().getId() : null;
		String condSql ="";
		if (UIUtils.isValidKeyId(factId)) {			
			condSql = " AND SFTM_FACTORYID ='"+factId+"'";		
			condSql += " AND SFTM_ACTIVE = 'Y' AND SFTM_SHIFTORDER < 4";
			shift.setCondSql(condSql);
		}else {
			condSql += " AND SFTM_ACTIVE = 'Y' ";
			shift.setCondSql(condSql);
		}
		
		shift.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);	
		return commonFilterDao.fillComboValuesWithoutDistinct(shift);
	}
	@Override
	public List<ComboBox> getFailTypeComboList(CommonFilter commonFilter)throws Exception {
		CommonMessage.debugMsg(" common failtype service  impl");
		ComboFilter fail = commonFilter.getCmbFailureType();
		fail.setIdField("FLTM_KEYID");
		//fail.setCodeField("FLTM_CODE");
		fail.setNameField("FLTM_NAME");
		fail.setTableName(TableNames.TBL_BDM_TL_FAILURETYPEMST);
	
		return commonFilterDao.fillComboValues(fail);
	}

	public List<ComboBox> getCauseComboList(CommonFilter commonFilter,String phenomenakeyId)throws Exception {
		CommonMessage.debugMsg(" common cause service  impl");
		ComboFilter cause = commonFilter.getCmbcause();
		cause.setIdField("BCSM_KEYID");
		//cause.setCodeField("BCSM_CODE");
		cause.setNameField("BCSM_NAME");
		cause.setTableName(TableNames.TBL_BDM_TL_CAUSEMST);
	
		StringBuffer condSql = new StringBuffer();
		StringBuffer condSql1 = new StringBuffer();
		String phenomena = commonFilter.getPhenomena() != null ? commonFilter.getPhenomena().getId() : null;
		
		condSql.append("  SELECT BCSM_KEYID FROM  " );
		condSql.append(  TableNames.TBL_BDM_TL_CAUSEMST+","+TableNames.TBL_BDM_TL_PHNCAUSELINK + " WHERE 1=1 " );
		if( CommonFunctions.isValidKeyId(phenomenakeyId))
		{
			condSql1.append( " AND BPCL_ORIGINALID =  BCSM_KEYID  AND INSTR(BPCL_ELEMENTID,'"+phenomenakeyId+"') > 0");
		}		
		CommonMessage.debugMsg("Condsql ..........."+condSql1);
		if(condSql1.length() > 0 )
			condSql.append(condSql1);
		cause.setCondSql(" AND BCSM_KEYID  IN ( " + condSql.toString() + ")");
		
		
		return commonFilterDao.fillComboValues(cause);
	}
	@Override
	public List<ComboBox> getPhenomenaComboList(CommonFilter commonFilter)throws Exception {
		CommonMessage.debugMsg(" common phenomena service  impl");
		ComboFilter phenomena = commonFilter.getPhenomena();
		phenomena.setIdField("BPHM_KEYID");
		//phenomena.setCodeField("BPHM_SHORTNAME");
		phenomena.setNameField("BPHM_PHENOMENANAME");
		phenomena.setTableName(TableNames.TBL_BDM_TL_PHENOMENAMST);
	
		
		StringBuffer condSql = new StringBuffer();
		StringBuffer condSql1 = new StringBuffer();
		String machineId = commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null;
		String assemblyId = commonFilter.getAssembly() != null ? commonFilter.getAssembly().getId() : null;
		CommonMessage.debugMsg("Assembly Id............."+assemblyId);
		String cellId = commonFilter.getCell() != null ? commonFilter.getCell().getId() : null;
		String sectId = commonFilter.getSection() != null ? commonFilter.getSection().getId() : null;
		condSql.append("  SELECT BPHM_KEYID FROM  " );
		condSql.append(  TableNames.TBL_BDM_TL_PHENOMENAMST+","+TableNames.TBL_BDM_TL_PHNCAUSELINK + " WHERE 1=1 " );
		if( CommonFunctions.isValidKeyId(machineId))
		{
			condSql1.append( " AND BPCL_ORIGINALID =  BPHM_KEYID  AND INSTR(BPCL_ELEMENTID,'"+machineId+"') > 0");
		}		
		CommonMessage.debugMsg("Condsql ..........."+condSql1);
		if(condSql1.length() > 0 )
			condSql.append(condSql1);
		phenomena.setCondSql(" AND BPHM_KEYID IN ( " + condSql.toString() + ")");
		
		
		
		return commonFilterDao.fillComboValues(phenomena);
	}

	@Override
	public List<ComboBox> getShiftInchargeComboList(CommonFilter commonFilter)throws Exception {
		CommonMessage.debugMsg(" common shiftinchsrge service  impl");
		ComboFilter shiftchrg = commonFilter.getCmbshiftIncharge();
		shiftchrg.setIdField("EMPM_KEYID");
		shiftchrg.setCodeField("EMPM_CODE");
		shiftchrg.setNameField("EMPM_NAME");
		shiftchrg.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
	
		return commonFilterDao.fillComboValues(shiftchrg);
	}
	@Override
	public List<ComboBox> getYyComboList(CommonFilter commonFilter)throws Exception {
		CommonMessage.debugMsg(" common yy service  impl");
		ComboFilter yy = commonFilter.getYyy();
		yy.setIdField("WWMS_KEYID");
		yy.setCodeField("WWMS_KEYID");
		yy.setNameField("WWMS_KEYID");
		yy.setTableName(TableNames.TBL_BDM_TL_WHYWHYMST);
	
		return commonFilterDao.fillComboValues(yy);
	}
	@Override
	public List<ComboBox> getBdRootCauseComboList(CommonFilter commonFilter)throws Exception {
		CommonMessage.debugMsg(" common shiftinchsrge service  impl");
		ComboFilter shiftchrg = commonFilter.getCmbbdRootCause();
		shiftchrg.setIdField("BCLM_KEYID");
		shiftchrg.setCodeField("BCLM_CODE");
		shiftchrg.setNameField("BCLM_NAME ");
		shiftchrg.setTableName(TableNames.TBL_BDM_VW_CLASSIFICATION);
	
		return commonFilterDao.fillComboValues(shiftchrg);
	}
	@Override
	public List<ComboBox> getProductComboList(CommonFilter commonFilter) throws Exception {
		ComboFilter product = commonFilter.getCmbpcsprrod();
		product.setIdField("PRDM_KEYID");
		product.setCodeField("PRDM_CODE");
		product.setNameField("PRDM_NAME ");
		product.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);
	
		return commonFilterDao.fillComboValues(product);
	}
	
	//ADDED BY MANI-14.03.12
	@Override
	public List<ComboBox> getMachineAreaComboList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter machineArea = commonFilter.getMachineArea();
		machineArea.setIdField("MCAM_NAME");
		machineArea.setCodeField("MCAM_CODE");
		//machine.setNameField("MCHM_MACHINENAME");
		machineArea.setTableName(TableNames.TBL_GEN_TL_MACHINEAREAMST);
		
		return commonFilterDao.fillComboValues(machineArea);
	}
	/*eND */
	@Override
	public List<ComboBox> getLossComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter lossType = commonFilter.getLossType();
		//add order by column and loss no
		if("PCS".equals(commonFilter.getFromPcs()))
		{	
			lossType.setIdField("PLCM_KEYID");
			lossType.setNameField("PLCM_LOSSNO");
			lossType.setCodeField("REPLACE(PLCM_PARAMETERNAME,'(+)','') ");			
			lossType.setTableName(TableNames.PCS_TL_LOGCONFIGURATION );			
			lossType.setCondSql(" AND PLCM_ISLOSS = 'M' ");
			lossType.setOrderByField("PLCM_ORDER");
			
		}
		else
		{
			lossType.setIdField("LOSM_KEYID");
			lossType.setNameField("LOSM_LOSSNAME");
			lossType.setTableName(TableNames.TBL_GEN_TL_LOSSMST);
		}
		
		
		return commonFilterDao.fillComboValues(lossType);
	}
	@Override
	public List<ComboBox> getComplaintnoComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter Complaintno = commonFilter.getComplaintno();
		Complaintno.setIdField("CUCM_KEYID");
		Complaintno.setNameField("CUCM_COMPLAINTNO");
		Complaintno.setTableName(TableNames.TBL_QTM_TL_CUSTCOMPLAINTMST);
		
		return commonFilterDao.fillComboValues(Complaintno);
	}
	@Override
	public List<ComboBox> getCustIDComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter CustID = commonFilter.getCustID();
		//CustID.setIdField("QCUM_KEYID");
		//CustID.setCodeField("QCUM_CODE");
		//CustID.setNameField("QCUM_NAME");		
		//CustID.setTableName(TableNames.TBL_QTM_TL_CUSTOMERMST);
		
		CustID.setNameField("PNOR_NAME");
		CustID.setIdField("PNOR_KEYID");
		
		CustID.setTableName("GEN_TL_PARTNORMST");//QTM_TL_CUSTOMERMST
		
		return commonFilterDao.fillComboValues(CustID);
	}
	public List<ComboBox> getPartnoComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter CustID = commonFilter.getCustID();
		
		CustID.setIdField("PNOR_KEYID");
		CustID.setCodeField("PNOR_SHORTNAME");
		CustID.setNameField("PNOR_NAME");		
		CustID.setTableName("GEN_TL_PARTNORMST");
		
		if (commonFilter.getType().equals("CUS") )
			CustID.setCondSql(" AND PNOR_TYPE = 'CUS' " ) ;
		else
			CustID.setCondSql(" AND PNOR_TYPE = 'SUP' " ) ;
		return commonFilterDao.fillComboValues(CustID);
	}


	public List<ComboBox> getDefactparamComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter Defactparam = commonFilter.getDefactparam();
		Defactparam.setIdField("DFPM_KEYID");
		Defactparam.setCodeField(" DFPM_CODE");
		Defactparam.setNameField(" DFPM_NAME");		
		Defactparam.setTableName(TableNames.TBL_QTM_TL_DEFECTPARAMETERMST);
		
		return commonFilterDao.fillComboValues(Defactparam);
	}
	@Override
	public List<ComboBox> getRecordedbyComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter Recordedby = commonFilter.getRecordedby();
		Recordedby.setIdField("EMPM_KEYID");
		Recordedby.setCodeField(" EMPM_CODE");
		Recordedby.setNameField(" EMPM_NAME");		
		Recordedby.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		
		return commonFilterDao.fillComboValues(Recordedby);
	}
	@Override
	public List<ComboBox> getinspectionComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter inspection = commonFilter.getInspection();
		inspection.setIdField("INSM_KEYID");
		inspection.setCodeField(" INSM_CODE");
		inspection.setNameField(" INSM_NAME");		
		inspection.setTableName(TableNames.TBL_PLM_TL_INSPECTIONMST);
		
		return commonFilterDao.fillComboValues(inspection);
	}
	
	
	
	  @Override 
	  public List<ComboBox> getDefactPhenamenComboList(CommonFilter commonFilter)throws Exception { 
		  // TODO Auto-generated method stub
	  ComboFilter DefactPhenamen = commonFilter.getDefactPhenamena();
	  DefactPhenamen.setIdField("QPHM_KEYID");
	  DefactPhenamen.setNameField(" QPHM_NAME ");
	  DefactPhenamen.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);
	  
	  return commonFilterDao.fillComboValues(DefactPhenamen); 
	  }
	 
	
	
	
	/*
	 * @Override public List<ComboBox> getDefactPhenamenComboList(CommonFilter
	 * commonFilter) throws Exception { ComboFilter defactPhenamen =
	 * commonFilter.getDefactPhenamena(); defactPhenamen.setIdField("QPHM_KEYID");
	 * defactPhenamen.setNameField("QPHM_NAME");
	 * defactPhenamen.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);
	 * 
	 * String flid = commonFilter.getFlid(); String sectionId =
	 * commonFilter.getSectionId();
	 * 
	 * String callingModule = ""; String defectMode = ""; String existingCond =
	 * defactPhenamen.getCondSql();
	 * 
	 * if (existingCond != null && existingCond.startsWith("##")) { String[] parts =
	 * existingCond.split("##"); callingModule = parts.length > 1 ? parts[1] : "";
	 * defectMode = parts.length > 2 ? parts[2] : ""; defactPhenamen.setCondSql("");
	 * }
	 * 
	 * CommonFunctions.debugMsg("=== getDefactPhenamenComboList ===");
	 * CommonFunctions.debugMsg("callingModule : " + callingModule);
	 * CommonFunctions.debugMsg("defectMode    : " + defectMode);
	 * CommonFunctions.debugMsg("flid          : " + flid);
	 * CommonFunctions.debugMsg("sectionId     : " + sectionId);
	 * 
	 * if ("COMPLAINT_GALLERY".equals(callingModule)) { if
	 * ("OTHERS".equals(defectMode)) { defactPhenamen.setCondSql(
	 * " AND QPHM_KEYID NOT IN ( " + "   SELECT PHNM_QPHM_KEYID " +
	 * "   FROM QTM_TL_PHENOMENA_MAPPING " + "   WHERE PHNM_ACTIVE = 'Y' " +
	 * "   AND PHNM_SECT_FLID = '" + sectionId + "' " + " ) " ); } else {
	 * 
	 * defactPhenamen.setCondSql( " AND QPHM_KEYID IN ( " +
	 * "   SELECT PHNM_QPHM_KEYID " + "   FROM QTM_TL_PHENOMENA_MAPPING " +
	 * "   WHERE PHNM_ACTIVE = 'Y' " + "   AND PHNM_SECT_FLID = '" + sectionId +
	 * "' " + " ) " ); } } else if (sectionId != null &&
	 * !sectionId.trim().isEmpty()) {
	 * 
	 * if ("OTHERS".equals(defectMode)) { defactPhenamen.setCondSql(
	 * " AND QPHM_KEYID NOT IN ( " + "   SELECT PHNM_QPHM_KEYID " +
	 * "   FROM QTM_TL_PHENOMENA_MAPPING " + "   WHERE PHNM_ACTIVE = 'Y' " +
	 * "   AND PHNM_SECT_FLID = '" + sectionId + "' " + " ) " ); } else {
	 * defactPhenamen.setCondSql( " AND QPHM_KEYID IN ( " +
	 * "   SELECT PHNM_QPHM_KEYID " + "   FROM QTM_TL_PHENOMENA_MAPPING " +
	 * "   WHERE PHNM_ACTIVE = 'Y' " + "   AND PHNM_SECT_FLID = '" + sectionId +
	 * "' " + " ) " ); } } else {
	 * 
	 * if ("QM".equals(defectMode)) { defactPhenamen.setCondSql(
	 * " AND QPHM_FLID IN ( " + "   SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN " +
	 * "   WHERE FNLN_ELEMENTID LIKE ( " + "       SELECT FNLN_ELEMENTID || '%' " +
	 * "       FROM GEN_TL_FUNCTIONALLOCN " + "       WHERE FNLN_KEYID = '" + flid +
	 * "' " + "   ) " + " ) " ); } else if ("OTHERS".equals(defectMode)) {
	 * defactPhenamen.setCondSql( " AND QPHM_FLID NOT IN ( " +
	 * "   SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN " +
	 * "   WHERE FNLN_ELEMENTID LIKE ( " + "       SELECT FNLN_ELEMENTID || '%' " +
	 * "       FROM GEN_TL_FUNCTIONALLOCN " + "       WHERE FNLN_KEYID = '" + flid +
	 * "' " + "   ) " + " ) " ); } else {
	 * 
	 * defactPhenamen.setCondSql(" AND 1 = 0 "); } }
	 * 
	 * return commonFilterDao.fillComboValuesWithoutCondition(defactPhenamen); }
	 */
	
//	@Override
//	public List<ComboBox> getProcessComboList(CommonFilter commonFilter , String originalid )throws Exception {
//		// TODO Auto-generated method stub
//		ComboFilter process = commonFilter.getProcess();
//		process.setIdField("QPOM_KEYID");		
//		process.setNameField(" QPOM_NAME ");	
//		
//		if (UIUtils.isValidKeyId(originalid))
//		{
//			process.setCondSql(" AND QPOM_FLID IN (  SELECT b.flid  FROM gen_mv_flidhierarchy a , gen_mv_flidhierarchy b WHERE instr(b.PARENTFLIDS || b.flid, a.flid ) > 0 and a.FNLN_ORIGINALID = '"+ originalid +"' ) ");
//			
//		}
//		else
//		{
//		
//			if (UIUtils.isValidKeyId( commonFilter.getFlid()))
//			{
//					// process.setCondSql(" AND QPOM_FLID = '" + commonFilter.getFlid() + "' " );
//				process.setCondSql(" AND QPOM_FLID IN ( SELECT flid FROM gen_mv_flidhierarchy   WHERE INSTR (parentflids || '-' || flid, '" + commonFilter.getFlid() + "' ) >0) ");
//			}
//		}
//		
//		
//		process.setTableName(TableNames.TBL_QTM_TL_PROCESSMST);
//		
//		return commonFilterDao.fillComboValues(process);
//	}
	
	@Override
	public List<ComboBox> getProcessComboList(CommonFilter commonFilter, String originalid) throws Exception {
	    ComboFilter process = commonFilter.getProcess();

	    // Use same fields as before
	    process.setIdField("QPOM_KEYID");
	    process.setNameField(" QPOM_NAME ");
	    process.setTableName(TableNames.TBL_QTM_TL_PROCESSMST);

	    // Build Postgres-safe filter
	    if (UIUtils.isValidKeyId(originalid)) {
	        // Former Oracle: INSTR(b.PARENTFLIDS || b.flid, a.flid ) > 0
	        process.setCondSql(
	            " AND QPOM_FLID IN ( " +
	            "   SELECT b.flid " +
	            "   FROM gen_mv_flidhierarchy a, gen_mv_flidhierarchy b " +
	            "   WHERE POSITION(a.flid IN (COALESCE(b.parentflids,'') || COALESCE(b.flid,''))) > 0 " +
	            "     AND a.FNLN_ORIGINALID = '" + originalid + "' " +
	            " ) "
	        );
	    } else {
	        if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
	            // Former Oracle: INSTR(parentflids || '-' || flid, :flid) > 0
	            process.setCondSql(
	                " AND QPOM_FLID IN ( " +
	                "   SELECT flid " +
	                "   FROM gen_mv_flidhierarchy " +
	                "   WHERE POSITION('" + commonFilter.getFlid() + "' " +
	                "                  IN (COALESCE(parentflids,'') || '-' || COALESCE(flid,''))) > 0 " +
	                " ) "
	            );
	        }
	    }

	    return commonFilterDao.fillComboValues(process);
	}


	
	@Override
	public List<ComboBox> getQtmCauseComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter cause = commonFilter.getQtmCause();
		cause.setIdField("QCAM_KEYID");		
		cause.setNameField(" QCAM_NAME ");		
		cause.setTableName(TableNames.TBL_QTM_TL_CAUSEMST);
		
		return commonFilterDao.fillComboValues(cause);
	}
	
	@Override
	public String getDesigId(String empId) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getDesigId(empId);
	}
	@Override
	public  List<ComboBox> getMould(String condSql,ComboFilter comboFilter) {
		// TODO Auto-generated method stub
		try {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("MLDM_DESCRIPTION");
		comboFilter.setIdField("MLDM_MOULDID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MOULDMST);
		if(UIUtils.isValidKeyId(condSql))
		{
			StringBuffer sb = new StringBuffer();
			sb.append("AND MLDM_MOULDID IN(SELECT MMLK_MOULDID FROM "+TableNames.TBL_GEN_TL_MOULDMACHINELINK);
			sb.append(" WHERE MMLK_MACHINEID = '"+condSql+"')");
			comboFilter.setCondSql(sb.toString());
		}
		
			return commonFilterDao.fillComboValues(comboFilter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	public  List<ComboBox> getBreakdown() {		
		try {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("BDMS_KEYID");
		comboFilter.setIdField("BDMS_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_MST);
		return commonFilterDao.fillComboValues(comboFilter);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return null;	
	}
	public  List<ComboBox> getMSR(ComboFilter msrFilter,String activityType) {		
		try {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("WOMS_KEYID");
		comboFilter.setIdField("WOMS_KEYID");	
		comboFilter.setTableName(TableNames.TBL_WOM_TL_MST);
		
		if(UIUtils.isValidKeyId(activityType))
		{
			StringBuffer sb = new StringBuffer();			
			sb.append(" AND WOMS_FINALACTIVITYTYPE = '"+activityType+"'");
			sb.append(" AND WOMS_ACTIVITYID <> '{}'");
			comboFilter.setCondSql(sb.toString());
		}
		if(UIUtils.isValidKeyId(msrFilter.getName()))
		{
			StringBuffer sb = new StringBuffer();	
			sb.append(" AND WOMS_KEYID LIKE '"+msrFilter.getName()+"%'");
			comboFilter.setCondSql(sb.toString());
		}
		return commonFilterDao.fillComboValues(comboFilter);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return null;	
	}
	@Override
	public List<ComboBox> getRawMatrialComboList(CommonFilter commonFilter)throws Exception {
		
		ComboFilter rawMatrial = commonFilter.getRawMatrial();
		rawMatrial.setIdField("RAWMATERIALTYPE");
		rawMatrial.setCodeField("RAWMATERIALTYPE");
		//rawMatrial.setNameField("MCHM_MACHINENAME");
		rawMatrial.setTableName(TableNames.TBL_PCS_TL_YIELD);
		
		return commonFilterDao.fillComboValues(rawMatrial);
		
	}
	@Override
	public List<ComboBox> getsupplierComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter supplier = commonFilter.getSupplier();
		supplier.setIdField("SUPM_KEYID");		
		supplier.setNameField("SUPM_NAME1");		
		supplier.setTableName(TableNames.TBL_GEN_TL_SUPPLIERMST);
		
		return commonFilterDao.fillComboValues(supplier);
	}
	@Override
	public List<ComboBox> getproductModelComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter processModel = commonFilter.getProductModel();
		processModel.setIdField("PRMM_KEYID");		
		processModel.setNameField("PRMM_NAME ");		
		processModel.setTableName(TableNames.TBL_PCS_TL_PRODUCTMODELMST);
		
		return commonFilterDao.fillComboValues(processModel);
	}
	
	@Override
	public List<ComboBox> getWorkOrderComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter workorder = commonFilter.getWorkOrder();
		workorder.setIdField("WOMS_KEYID");		
		workorder.setNameField("WOMS_KEYID ");		
		workorder.setTableName(TableNames.TBL_WOM_TL_MST);		
		return commonFilterDao.fillComboValues(workorder);
	}	
	@Override
	public List<ComboBox> getDesignationComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter training = commonFilter.getDesignation();
		training.setIdField("DESG_KEYID");		
		training.setNameField("DESG_NAME ");		
		training.setTableName(TableNames.TBL_GEN_TL_DESIGNATIONMST);		
		return commonFilterDao.fillComboValues(training);
	}
	@Override
	public List<ComboBox> getProgramComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter training = commonFilter.getProgm();
		/*training.setIdField("PROG_KEYID");		
		training.setNameField("PROG_NAME");		
		training.setTableName(TableNames.TBL_ENT_TL_PROGRAMMST);*/
		
		training.setIdField("PROG_KEYID");		
		training.setNameField(" PROG_NAME || '-' || PROG_MONTH " );		
		training.setTableName(" ENT_TL_PROGRAMMST " );
		
		if(UIUtils.isValidKeyId(commonFilter.getFlid()))
		{
			training.setCondSql(" AND PROG_TRAR_KEYID = '" + commonFilter.getFlid() + "'");
		}
		return commonFilterDao.fillComboValues(training);
	}
	
	@Override
	public List<ComboBox> getPgmBenefitComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter training = commonFilter.getPgmbenefit();
		training.setIdField("PROG_KEYID");		
		training.setNameField("PROG_BENIFIT");		
		training.setTableName(TableNames.TBL_ENT_TL_PROGRAMMST);		
		return commonFilterDao.fillComboValues(training);
	}
	
	public List<ComboBox> getBatchComboList(CommonFilter commonFilter)throws Exception {

		// TODO Auto-generated method stub
		ComboFilter training = commonFilter.getBatch();
		training.setIdField("BACH_KEYID");		
		if(UIUtils.isValidKeyId(commonFilter.getType()))
			training.setNameField(" BACH_NAME ||' '|| to_char(BACH_FROMDATE,'DD-Mon-YYYY  hh24:mi') ||' - '|| to_char(BACH_TILLDATE,'hh24:mi') ");		
		else
			training.setNameField("BACH_NAME");
		training.setTableName(TableNames.TBL_ENT_TL_BATCHMST);		
		//CommonMessage.debugMsg("commonFilter.getBatch()"+commonFilter.getBatch());
		return commonFilterDao.fillComboValues(training);
	}

	
	public List<ComboBox> getPgmnoComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter training = commonFilter.getPgmno();
		training.setIdField("EPRO_KEYID");		
		training.setNameField("EPRO_SLNO");		
		training.setTableName(TableNames.TBL_ENT_TL_PROGRAMOUTCOME);		
		return commonFilterDao.fillComboValues(training);
	}
	public List<ComboBox> getAccidentNoComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter incNo = commonFilter.getIncidentno();
		incNo.setIdField("SINC_KEYID");		
		incNo.setNameField("SINC_KEYID");		
		incNo.setTableName(TableNames.TBL_SHE_TL_INCIDENTMST);		
		return commonFilterDao.fillComboValues(incNo);
	}
	public List<ComboBox> getInjurymodeComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter incNo = commonFilter.getInjuryType();
		incNo.setIdField("INJM_KEYID");		
		incNo.setNameField("INJM_NAME");		
		incNo.setTableName(TableNames.TBL_SHE_TL_INJURYMODEMST);		
		return commonFilterDao.fillComboValues(incNo);
	}
	public List<ComboBox> getBodypartComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter incNo = commonFilter.getBodypart();
		incNo.setIdField("IBOP_KEYID");		
		incNo.setNameField("IBOP_NAME");		
		incNo.setTableName(TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST);		
		return commonFilterDao.fillComboValues(incNo);
	}
	public List<ComboBox> getJhAuditNameComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter AdtName = commonFilter.getCmbAuditorName();
		AdtName.setIdField("JHAM_AUDITORNAME");		
		AdtName.setNameField("JHAM_AUDITORNAME");		
		AdtName.setTableName(TableNames.TBL_JHA_TL_AUDITMST);		
		return commonFilterDao.fillComboValues(AdtName);
	}
	public List<ComboBox> getJhAuditLevelComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter AdtLevel = commonFilter.getCmbAuditorLevel();
		AdtLevel.setIdField("JHAT_KEYID");		
		AdtLevel.setNameField("JHAT_NAME");		
		AdtLevel.setTableName(TableNames.TBL_JHA_TL_AUDITTEAM);		
		return commonFilterDao.fillComboValues(AdtLevel);
	}
	
	
	
	public String getspokekeyId(String progkeyId) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getspokeid(progkeyId);
	}
	
	public List<ComboBox> getSkillComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter skill = commonFilter.getSkilName();
		skill.setIdField("SKIL_KEYID");		
		skill.setNameField("SKIL_NAME ");		
		skill.setTableName(TableNames.TBL_ENT_TL_SKILLMST);
		
		return commonFilterDao.fillComboValues(skill);
	}
	
	public List<ComboBox> getSkillRatingComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter skillRating = commonFilter.getSkilRating();
		skillRating.setIdField("SKRM_KEYID");	
		skillRating.setCodeField("SKRM_DESCRIPTION");
		skillRating.setNameField("SKRM_ORDERNO");
			
		skillRating.setTableName(TableNames.TBL_ENT_TL_SKILL_RATINGMST);
		
		return commonFilterDao.fillComboValues(skillRating);
	}
	
	/*public List<ComboBox> getRoleComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter  training = commonFilter.getRoleId();
		training.setIdField("ROLE_KEYID");		
		training.setNameField("ROLE_NAME");	
		CommonMessage.debugMsg(commonFilter.getTopicid() +  " commonfiltertopic ");
		
		String keyIdCnd="";
		if(UIUtils.isValidKeyId(commonFilter.getKey() ))
			keyIdCnd = " AND ROLE_KEYID = '" + commonFilter.getKey() + "' "; 

		if(UIUtils.isValidKeyId(commonFilter.getTopicid()))
			keyIdCnd =  keyIdCnd + " AND ROLE_KEYID in( select  TMTM_ROLE_KEYID from  ENT_TL_UNIQPOSTOPIC_LINKMST where TMTM_TOPI_KEYID ='"+commonFilter.getTopicid()+"' )" ;
		
		CommonMessage.debugMsg(commonFilter.getAbnormalityType() +  " commonfilter :: Checking for Type :: ");
		
		String cnd="";
		
		if(UIUtils.isValidKeyId(commonFilter.getFlid()) && (!commonFilter.getAbnormalityType().equals("opl"))) {
			training.setNameField("ROLE_NAME  ||'-' || FNLN_DISPLAYCODE");
			 cnd = "  AND ROLE_FLID = FLID ";
			
			//changed on 04-aug-2014
			if (commonFilter.getType().equals("Y"))
				cnd+= " AND INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0    ";
			else 				
				cnd+= " AND FLID  = '"+commonFilter.getFlid()+"'  ";
			
			//cnd+= " AND INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0    ";  
			training.setCondSql( cnd + keyIdCnd);
		
			// training.setTableName(" GEN_TL_ROLEMST, GEN_MV_FLIDHIERARCHY ");
			training.setTableName(" Ent_Vw_Rolemst  ");
		}
		else{
			training.setNameField("ROLE_NAME  ||'-' || FNLN_DISPLAYCODE");
			if(commonFilter.getAbnormalityType().equals("opl"))
				cnd+= " and role_flid=flid(+) AND (INSTR(role_elementid ,'"+commonFilter.getKK()+"')>0)  ";
			
			training.setCondSql( cnd + keyIdCnd);
			
			training.setTableName("GEN_TL_ROLEMST,GEN_MV_FLIDHIERARCHY");
		}
		//select  TMTM_ROLE_KEYID from  ent_tl_task_mapping_topicmst where TMTM_TOPI_KEYID ='TOP0000072'
		
		return commonFilterDao.fillComboValues(training);
	}*/
//	public List<ComboBox> getRoleComboList(CommonFilter commonFilter)throws Exception {
//		ComboFilter  training = commonFilter.getRoleId();
//		training.setIdField("ROLE_KEYID");		
//		training.setNameField("ROLE_NAME");	
//		CommonMessage.debugMsg(commonFilter.getFlid() +  " commonfiltertopic ");
//		
//		String keyIdCnd="";
//		if(UIUtils.isValidKeyId(commonFilter.getKey() ))
//			keyIdCnd = " AND ROLE_KEYID = '" + commonFilter.getKey() + "' "; 
//
//		if(UIUtils.isValidKeyId(commonFilter.getTopicid()))
//			keyIdCnd =  keyIdCnd + " AND ROLE_KEYID in( select  TMTM_ROLE_KEYID from  ENT_TL_UNIQPOSTOPIC_LINKMST where TMTM_TOPI_KEYID ='"+commonFilter.getTopicid()+"' )" ;
//		//CommonMessage.debugMsg(commonFilter.getFlid() +  " commonfilter ");
//
//		if(UIUtils.isValidKeyId(commonFilter.getFlid())) {
//			training.setNameField("ROLE_NAME  ||'-' || FNLN_DISPLAYCODE");
//			String cnd = "  AND ROLE_FLID = FLID ";
//			
//			//changed on 04-aug-2014
//			if (commonFilter.getType().equals("Y"))
//				cnd+= " AND INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0    ";
//			else				
//				cnd+= " AND FLID  = '"+commonFilter.getFlid()+"'  ";
//			//cnd+= " AND INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0    ";  
//			training.setCondSql( cnd + keyIdCnd);
//		    CommonMessage.debugMsg("keyIdCnd:"+keyIdCnd);
//		    CommonMessage.debugMsg("con:"+cnd);
//			// training.setTableName(" GEN_TL_ROLEMST, GEN_MV_FLIDHIERARCHY ");
//			training.setTableName(" Ent_Vw_Rolemst  ");
//		}
//		else
//			training.setTableName(TableNames.TBL_GEN_TL_ROLEMST);	
//		//select  TMTM_ROLE_KEYID from  ent_tl_task_mapping_topicmst where TMTM_TOPI_KEYID ='TOP0000072'
//		CommonMessage.debugMsg("Training Data"+keyIdCnd);
//		return commonFilterDao.fillComboValues(training);
//	}
	
	public List<ComboBox> getRoleComboList(CommonFilter commonFilter) throws Exception {
	    ComboFilter training = commonFilter.getRoleId();
	    training.setIdField("ROLE_KEYID");
	    training.setNameField("ROLE_NAME");

	    CommonMessage.debugMsg(commonFilter.getFlid() + " commonfiltertopic ");

	    String keyIdCnd = "";
	    if (UIUtils.isValidKeyId(commonFilter.getKey())) {
	        keyIdCnd = " AND ROLE_KEYID = '" + commonFilter.getKey() + "' ";
	    }

	    if (UIUtils.isValidKeyId(commonFilter.getTopicid())) {
	        keyIdCnd = keyIdCnd
	                + " AND ROLE_KEYID in( "
	                + "  select TMTM_ROLE_KEYID "
	                + "  from ENT_TL_UNIQPOSTOPIC_LINKMST "
	                + "  where TMTM_TOPI_KEYID = '" + commonFilter.getTopicid() + "' "
	                + ")";
	    }

	    if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
	        // show ROLE_NAME - FNLN_DISPLAYCODE
	        training.setNameField("ROLE_NAME || '-' || FNLN_DISPLAYCODE");

	        // base condition
	        String cnd = " AND ROLE_FLID = FLID ";

	        // changed on 04-aug-2014 (hierarchy)
	        if ("Y".equals(commonFilter.getType())) {
	            // POSTGRES FIX: use POSITION instead of INSTR
	            cnd += " AND POSITION('" + commonFilter.getFlid()
	                    + "' IN (PARENTFLIDS || FLID)) > 0 ";
	        } else {
	            cnd += " AND FLID = '" + commonFilter.getFlid() + "' ";
	        }

	        training.setCondSql(cnd + keyIdCnd);
	        CommonMessage.debugMsg("keyIdCnd:" + keyIdCnd);
	        CommonMessage.debugMsg("con:" + cnd);

	        // POSTGRES: use lowercase view name (safe if view was created unquoted)
	        training.setTableName("ent_vw_rolemst");
	    } else {
	        // fall back to base role master table
	        training.setTableName(TableNames.TBL_GEN_TL_ROLEMST);
	    }

	    CommonMessage.debugMsg("Training Data" + keyIdCnd);
	    return commonFilterDao.fillComboValues(training);
	}
	// REPLACE IN COMMONFILTER SERVICE IMPL

	
	
	public List<ComboBox> getskillTypeComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter  training = commonFilter.getSkillType();
		training.setIdField("STYP_KEYID");		
		training.setNameField("STYP_CODE");		
		training.setTableName(TableNames.TBL_ENT_TL_SKILLTYPE);
		return commonFilterDao.fillComboValues(training);
	}
	public List<ComboBox> getThemeCombo() throws Exception {

		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("KZNM_THEME");
		comboFilter.setIdField("KZNM_KEYID");		
		comboFilter.setTableName(TableNames.TBL_KZN_TL_MST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getTopicCombo(CommonFilter commonFilter) throws Exception {

		ComboFilter comboFilter = commonFilter.getTopics();
		comboFilter.setNameField("TOPI_NAME");
		comboFilter.setCodeField("TOPI_TYPE");
		comboFilter.setIdField("TOPI_KEYID");		
		comboFilter.setTableName("ent_tl_topicmst");
		
		if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
			StringBuffer sb = new StringBuffer();
			sb.append(" AND TOPI_LOCATIONID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  " );
			sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
			sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + commonFilter.getFlid() + "') ) " );
			comboFilter.setCondSql(sb.toString());
		}
			
		
		return commonFilterDao.fillComboValues(comboFilter);
	}
//	public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception {
//		
//		ComboFilter  topic = commonFilter.getTopic();
//		topic.setIdField("TOPI_KEYID");		
//		topic.setNameField("TOPI_NAME");		
//		topic.setTableName(TableNames.TBL_ENT_TL_TOPICMST+","+TableNames.ENT_TL_EVALUATIONTYPEMST);
//		topic.setCondSql("AND EVAL_KEYID=TOPI_EVALUATIONTYPEID(+) AND EVAL_TYPE='C'");
//		return commonFilterDao.fillComboValues(topic);		
//		
//		
//	}
public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception {
		
		ComboFilter  topic = commonFilter.getTopic();
		topic.setIdField("TOPI_KEYID");		
		topic.setNameField("TOPI_NAME");		
		topic.setTableName(TableNames.TBL_ENT_TL_TOPICMST+" LEFT JOIN   "+TableNames.ENT_TL_EVALUATIONTYPEMST +" ON eval_keyid = topi_evaluationtypeid ");
		
		topic.setCondSql(" AND EVAL_TYPE='C'");
		return commonFilterDao.fillComboValues(topic);
}
	@Override
	public List<ComboBox> getTopicsComboList(CommonFilter commonFilter,String spokeKeyid,String trarKeyid )
			throws Exception {
		 ComboFilter  topics = commonFilter.getTopics();
		 topics.setIdField("TOPI_KEYID");
		 topics.setNameField("TOPI_NAME");
		 topics.setTableName(TableNames.TBL_ENT_TL_TOPICMST);
		/* String  sql = "select topi_keyid from  ent_tl_topicmst where TOPI_ISCHILD = 'Y' and TOPI_KEYID in ( ";
		 sql	+="select trar_refid from ent_vw_trainingareachildpath ";
		 sql	+="where instr(CHILDPATH, (SELECT trar_keyid FROM ent_tl_trainingarea WHERE";
		 if(UIUtils.isValidKeyId(trarKeyid))
		 sql	+=" trar_parentid = '"+trarKeyid+"' and ";
		 if(UIUtils.isValidKeyId(spokeKeyid))
		 { sql	+=" trar_refid = '"+spokeKeyid+"')) > 0 and";
		 sql	+=" trar_reftype <> 'SPK')";*/
		 String sql = null;
		 if(UIUtils.isValidKeyId(spokeKeyid))
		 {
			 sql = "select topicid from  ENT_VW_SPOKETOPIC where SPOKID = '"+spokeKeyid+"'";
		 }
		 if(UIUtils.isValidKeyId(trarKeyid))
			 sql = sql + "AND  instr(CHILDPATH, '"+trarKeyid+"') > 0  AND LEAF = 1";
		 
			 topics.setCondSql("AND TOPI_KEYID in ("+sql+")");
		 
		 
         return commonFilterDao.fillComboValues(topics);	
	}
	@Override
	public List<ComboBox> getSpokeComboList(CommonFilter commonFilter)
			throws Exception {
		ComboFilter  spoke = commonFilter.getSpoke();
		spoke.setIdField("SPOK_KEYID");
		spoke.setNameField("SPOK_NAME");
		spoke.setTableName(TableNames.TBL_ENT_TL_SPOKEMST);
		
		// TODO Auto-generated method stub
		return commonFilterDao.fillComboValues(spoke);
	}
	@Override
	public List<ComboBox> getKaizenNoComboList(CommonFilter commonFilter)	throws Exception {
		ComboFilter kaizenNo = commonFilter.getJHKaizenNo();
		kaizenNo.setIdField("JHKZ_KEYID");		
		kaizenNo.setNameField("JHKZ_KEYID");
		kaizenNo.setTableName(TableNames.TBL_JHK_TL_KAIZENMST);		
		return commonFilterDao.fillComboValues(kaizenNo);	
	}
	@Override
	public List<ComboBox> getJHKaizenCategoryComboList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		ComboFilter kaizenCategory = commonFilter.getJHKaizenCategory();
		kaizenCategory.setIdField("KCTM_KEYID");		
		kaizenCategory.setNameField("KCTM_NAME");
		kaizenCategory.setTableName(TableNames.TBL_KZN_TL_CATEGORYMST);
		return commonFilterDao.fillComboValues(kaizenCategory);		

	}
	@Override
	public List<ComboBox> getJhStepComboList(CommonFilter commonFilter)	throws Exception {
		ComboFilter jhStep = commonFilter.getJhStep();
		jhStep.setIdField("JHSM_KEYID");		
		jhStep.setNameField("JHSM_NAME");
		jhStep.setTableName(TableNames.TBL_GEN_TL_JHSTEPMST);		
		return commonFilterDao.fillComboValues(jhStep);	
	}
	@Override
	public List<ComboBox> getManagerComboList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		ComboFilter Manager = commonFilter.getManager();
		Manager.setIdField("EMPM_KEYID");
		Manager.setCodeField(" EMPM_CODE");
		Manager.setNameField(" EMPM_NAME");	
		Manager.setCondSql(" AND EMPM_DESIGNATIONID in(select DESG_KEYID from  GEN_TL_designationmst where DESG_NAME like '%MANAGER%')");
		Manager.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		
		return commonFilterDao.fillComboValues(Manager);
	}
	
	
	public List<ComboBox> getTeamComboList(String funLocCndSql, String empCndSql) throws Exception {		
		return commonFilterDao.getTeamComboList(funLocCndSql, empCndSql);
	}
	
	@Override
	public List<ComboBox> getSeverityCombo() throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("serviceimpl");
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("EMP_CODE");
		comboFilter.setNameField("NMSP_NAME");
		comboFilter.setIdField("NMSP_KEYID");
		comboFilter.setTableName("SHE_TL_NEARMISSSEVERITY");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getProbableCombo() throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("serviceimpl");
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("EMP_CODE");
		comboFilter.setNameField("PRRR_NAME");
		comboFilter.setIdField("PRRR_KEYID");
		
		comboFilter.setTableName("SHE_TL_PROBABLERECURANCERATE");
		return commonFilterDao.fillComboValues(comboFilter);
	}

	
	  @Override 
	  public List<ComboBox> getGradeSpecComboList(CommonFilter commonFilter, ComboFilter comboFilter) throws Exception {
	  CommonMessage.debugMsg("serviceimpl"); 
	 // ComboFilter comboFilter = new ComboFilter(); 
	  comboFilter.setCodeField("GSPC_CODE");
	  comboFilter.setNameField("GSPC_NAME"); 
	  comboFilter.setIdField("GSPC_KEYID");
	  comboFilter.setTableName("PCS_TL_GRADESPECMST");
	  
	  if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
	  //comboFilter.setCondSql(" AND GSPC_FLID = '" + commonFilter.getFlid() +"' "); 
	  StringBuffer sb = new StringBuffer(); 
	  sb.append(" AND GSPC_FLID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  "); 
	  sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
	  sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + commonFilter.getFlid() +"') ) " ); 
	  comboFilter.setCondSql(sb.toString()); 
	  }
	  
	  //if (UIUtils.isValidKeyId( commonFilter.getLocation().getId()) )
	  //comboFilter.setCondSql( " AND 1=1"); 
	  return  commonFilterDao.fillComboValues(comboFilter); 
	  }
	 
	
	/*
	 * @Override public List<ComboBox> getGradeSpecComboList(CommonFilter
	 * commonFilter, ComboFilter comboFilter) throws Exception {
	 * comboFilter.setCodeField("GSPC_CODE"); comboFilter.setNameField("GSPC_NAME");
	 * comboFilter.setIdField("GSPC_KEYID");
	 * comboFilter.setTableName("PCS_TL_GRADESPECMST");
	 * 
	 * String gradeMode = ""; String existingCond = comboFilter.getCondSql();
	 * 
	 * if(existingCond != null && existingCond.startsWith("##")) { String[] parts =
	 * existingCond.split("##"); gradeMode = parts.length > 1 ? parts[1] : ""; //
	 * reset condSql so it doesn't affect the actual query
	 * comboFilter.setCondSql(""); }
	 * 
	 * CommonFunctions.debugMsg("=== getGradeSpecComboList ===");
	 * CommonFunctions.debugMsg("gradeMode : " + gradeMode);
	 * 
	 * if (UIUtils.isValidKeyId(commonFilter.getFlid())) { if
	 * ("QM".equals(gradeMode)) { comboFilter.setCondSql(" AND GSPC_FLID = '" +
	 * commonFilter.getFlid() + "' "); } else { StringBuffer sb = new
	 * StringBuffer(); sb.
	 * append(" AND GSPC_FLID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  "
	 * ); sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
	 * sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + commonFilter.getFlid() +
	 * "') ) "); comboFilter.setCondSql(sb.toString()); } }
	 * 
	 * return commonFilterDao.fillComboValues(comboFilter); }
	 */
	
	/*
	 * @Override public List<ComboBox> getGradeSpecComboList(CommonFilter
	 * commonFilter, ComboFilter comboFilter) throws Exception {
	 * comboFilter.setCodeField("GSPC_CODE"); comboFilter.setNameField("GSPC_NAME");
	 * comboFilter.setIdField("GSPC_KEYID");
	 * comboFilter.setTableName("PCS_TL_GRADESPECMST");
	 * 
	 * String gradeMode = ""; String existingCond = comboFilter.getCondSql();
	 * 
	 * if (existingCond != null && existingCond.startsWith("##")) { String[] parts =
	 * existingCond.split("##"); gradeMode = parts.length > 1 ? parts[1] : ""; //
	 * reset condSql so it doesn't affect the actual query
	 * comboFilter.setCondSql(""); }
	 * 
	 * CommonFunctions.debugMsg("=== getGradeSpecComboList ===");
	 * CommonFunctions.debugMsg("gradeMode : " + gradeMode);
	 * 
	 * if (UIUtils.isValidKeyId(commonFilter.getFlid())) { if
	 * ("OTHERS".equals(gradeMode)) { // Load grade specs from parent/related
	 * locations (broad search) StringBuffer sb = new StringBuffer(); sb.
	 * append(" AND GSPC_FLID IN (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE "
	 * ); sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
	 * sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID = '" + commonFilter.getFlid() +
	 * "') ) "); comboFilter.setCondSql(sb.toString()); } else {
	 * 
	 * comboFilter.setCondSql(" AND GSPC_FLID = '" + commonFilter.getFlid() + "' ");
	 * } } else {
	 * 
	 * comboFilter.setCondSql(" AND 1 = 0 "); }
	 * 
	 * return commonFilterDao.fillComboValuesWithoutCondition(comboFilter); }
	 */
	
	@Override
	public List<ComboBox> getCustomerComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = commonFilter.getproduct();
		//comboFilter.setCodeField("EMP_CODE");
		comboFilter.setNameField("PNOR_NAME");
		comboFilter.setIdField("PNOR_KEYID");
		
		comboFilter.setTableName("GEN_TL_PARTNORMST");//QTM_TL_CUSTOMERMST
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public String getFuncLocnHierarchy(String sbuId) throws Exception {
		return commonFilterDao.getFuncLocnHierarchy(sbuId);
	}
	
	public List<ComboBox> getSbuComboList(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception{
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("EMP_CODE");
		StringBuffer 		sql = new StringBuffer("");
		if(commonFilter.getLocation() != null && UIUtils.isValidKeyId(commonFilter.getLocation().getId()) ){
			//sql = new StringBuffer();
			sql.append( " and SBUT_LOCATIONID ='"+commonFilter.getLocation().getId() +"'");
			
		}
		if(commonFilter.getCompany() != null && UIUtils.isValidKeyId(commonFilter.getCompany().getId()) ){
			//sql = new StringBuffer();
			sql.append( "  and SBUT_COMPANYID ='"+commonFilter.getCompany().getId() +"'");
		}
		if( sql != null) {
			comboFilter.setCondSql(sql.toString());
		}
		comboFilter.setNameField("SBUT_NAME");
		comboFilter.setCodeField("SBUT_CODE");
		comboFilter.setIdField("SBUT_KEYID");
		
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SBUMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<ComboBox> getPbuComboList(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception{
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("EMP_CODE");
		StringBuffer sql =  null; 
		comboFilter.setNameField("PBUT_NAME");
		comboFilter.setCodeField("PBUT_CODE");
		comboFilter.setIdField("PBUT_KEYID");
		sql = new StringBuffer("");
		
		if(commonFilter.getSbu() != null && UIUtils.isValidKeyId(commonFilter.getSbu().getId()) ){
		//	sql = new StringBuffer();
			sql.append( " and PBUT_SBUID = '"+commonFilter.getSbu().getId() +"'");
			
		}
		if(commonFilter.getLocation() != null && UIUtils.isValidKeyId(commonFilter.getLocation().getId()) ){
			
			sql.append( " and PBUT_SBUID in ( select sbut_keyid from gen_TL_sbumst where  SBUT_LOCATIONID ='"+commonFilter.getLocation().getId() +"')");
			
		}
		if(commonFilter.getCompany() != null && UIUtils.isValidKeyId(commonFilter.getCompany().getId()) ){
			//sql = new StringBuffer();
			sql.append( " and PBUT_SBUID in ( select sbut_keyid from gen_TL_sbumst where  SBUT_COMPANYID ='"+commonFilter.getCompany().getId() +"')");
			
		}
		if( sql != null) {
			comboFilter.setCondSql(sql.toString());
			comboFilter.setTableName(TableNames.TBL_GEN_TL_PBUMST);
		}
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getDepartmentCombo(CommonFilter commonFilter) throws Exception {
		
		CommonMessage.debugMsg("getDepartmentCombo serviceimpl");
		ComboFilter comboFilter = commonFilter.getDesignation();
		//comboFilter.setCodeField("EMP_CODE");
		comboFilter.setNameField("DEPT_NAME");
		comboFilter.setIdField("DEPT_KEYID");
		
		comboFilter.setTableName("gen_tl_departmentmst");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getTargetGrpComboList(ComboFilter comboFilter, String string) throws Exception {
		// TODO Auto-generated method stub
		comboFilter.setNameField("TGTM_TITLE");
		comboFilter.setIdField("TGTM_KEYID");
		comboFilter.setTableName(TableNames.ENT_TL_TARGETGROUPMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	 
	@Override
	public List<String[]> getEmpData(String userKeyid) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getEmpData(userKeyid);
	}
	@Override
	public List<String[]> getempEqpData(String userID) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getempEqpData(userID);
	}
	@Override
	public List<String[]> getMenu(String pillarid, String usrm_keyid)
			throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getMenu(pillarid, usrm_keyid);
	}
	@Override
	public List<String[]> getPcsLoss(String string) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getPcsLoss("");
	}

	/*@Override
	public List<String[]> getMenu(String pillarid, String usrm_keyid)
			throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getMenu(pillarid, usrm_keyid);
	}
	@Override
	public List<String[]> getPcsLoss(String string) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getPcsLoss("");
	}
	*/
	@Override
	public List<GenTlAllmoduleimgfile> saveImg(List<GenTlAllmoduleimgfile> allmoduleimgfiles,String refKeyId,String refDocType) throws Exception {
		CommonMessage.debugMsg("refKeyId:::::"+refKeyId);
		if( allmoduleimgfiles != null )
		{
			
			fillAllmoduleimgfileImgValues(allmoduleimgfiles,refKeyId,refDocType);
			return commonFilterDao.saveImg(allmoduleimgfiles);
		}
		return null;
		
	}
	
	private void fillAllmoduleimgfileImgValues(List<GenTlAllmoduleimgfile> allmoduleimgfiles,String refKeyId, String refDocType) {
		
			List<GenTlAllmoduleimgfile> genTlAllmoduleimgfileList =new ArrayList<GenTlAllmoduleimgfile>();
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:allmoduleimgfiles)
			{
				String dateTime = CommonFunctions.dateTimeNow();
				CommonMessage.debugMsg(dateTime);
				
				genTlAllmoduleimgfile.setImflModifiedon((dateTime));
				genTlAllmoduleimgfile.setImflRefkeyid(refKeyId);
				genTlAllmoduleimgfile.setImflRefdoctype(refDocType);
				long length =0;
				
				String fileName = genTlAllmoduleimgfile.getImflFilename();
				if( CommonFunctions.isValidKeyId(fileName))
				{
					fileName =  fileName.substring(fileName.lastIndexOf("/")+1);
					genTlAllmoduleimgfile.setImflFilename(fileName );
					fileName =genTlAllmoduleimgfile.getImflBlobimage() + fileName;
					CommonMessage.debugMsg(" fileName " + fileName);
					genTlAllmoduleimgfile.setImflBlobimage(fileName);
					
					if( CommonFunctions.isFileExists(fileName ) )
						length = new File(fileName).length();
					
					genTlAllmoduleimgfile.setImflBloblength(Long.toString(length) );
								
					if( genTlAllmoduleimgfile.getImflTempfield1() == null )
						genTlAllmoduleimgfile.setImflTempfield1("{}");
					if( genTlAllmoduleimgfile.getImflTempfield2() == null )
						genTlAllmoduleimgfile.setImflTempfield2("{}");
					genTlAllmoduleimgfileList.add(genTlAllmoduleimgfile);
				}
			}
		
		
	}
	@Override
	public List<String[]> getPilarWiseEmployee(String pillar) throws Exception {
		// TODO Auto-generated method stub
		return commonFilterDao.getPilarWiseEmployee(pillar);
	}
	

	public List<ComboBox> getEffectiveeComboList(CommonFilter commonFilter)	throws Exception {
		ComboFilter effectiveness = commonFilter.getEffectiveness();
		effectiveness.setIdField("WHYE_KEYID");		
		effectiveness.setNameField("WHYE_EFFECTIVENAME");
		effectiveness.setTableName(TableNames.TBL_BDM_TL_WHYWHYEFFECTIVE);		
		return commonFilterDao.fillComboValues(effectiveness);	
	}
	@Override
	public List<ComboBox> getReason(CommonFilter commonFilter) throws Exception {
		ComboFilter comboFilter = commonFilter.getReason();
		comboFilter.setNameField("PJBR_REASON");
		comboFilter.setIdField("PJBR_KEYID");
		comboFilter.setTableName(TableNames.TBL_SHE_TL_PJOB_REASONMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
public List<ComboBox> getproductSLAComboList(CommonFilter commonFilter)	throws Exception {
		
		ComboFilter product = commonFilter.getproduct();
		String sql="";
		product.setIdField("PRDM_KEYID");
		product.setCodeField("PRDM_CODE");
		product.setNameField("PRDM_NAME");		
		product.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);		
		if(UIUtils.isValidKeyId(commonFilter.getType())){
			if("IMR".equals(commonFilter.getType())){
				sql+=" AND PRDM_KEYID IN ( SELECT IMRG_PRODUCTID FROM GEN_TL_IMRCHARACTERISTCONFIG  ";
				if(UIUtils.isValidKeyId(commonFilter.getChartType()))
					sql+=" WHERE IMRG_CHARACTERISTICID ='"+commonFilter.getChartType()+"'";
				sql+=" )";
				product.setCondSql(sql);
			}
		}
		return commonFilterDao.fillComboValues(product);
		
	}
	@Override
	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter,
			ComboFilter currentFilter) throws Exception {
		// TODO Auto-generated method stub
		currentFilter.setIdField("EMPM_KEYID");
		currentFilter.setCodeField("EMPM_CODE");
		currentFilter.setNameField("EMPM_NAME");
		
		String flid = commonFilter.getFlid();
		String sectid=commonFilter.getSect();
		String locnid=commonFilter.getAbnAllch();
		if (UIUtils.isValidKeyId(sectid)) {
			StringBuffer sb = new StringBuffer();
			sb.append(" AND FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ACTIVE='Y' AND FRT_ROLE_KEYID IN('AROL0005','AROL0003') AND FRT_FNLN_KEYID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  " );
			sb.append(" FNLN_ORIGINALID IN ('"+sectid+"'))");
			//sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
			currentFilter.setCondSql(sb.toString());
		}
		else{
			StringBuffer sb = new StringBuffer();
			sb.append(" AND FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ACTIVE='Y' AND EMPM_LOCATION='"+locnid+"'"+" AND FRT_ROLE_KEYID IN('AROL0005','AROL0003')  " );
		//	sb.append(" FNLN_ORIGINALID IN ('"+sectid+"'))");
			//sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
			currentFilter.setCondSql(sb.toString());
		}
		currentFilter.setTableName("GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST");
		return commonFilterDao.fillComboValues(currentFilter);
	}
	
	
	public List<ComboBox> getSubProcessComboList(ComboFilter Subprocess, String processId) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter Subprocess = commonFilter.getProcess();
		Subprocess.setIdField("SUBP_KEYID");		
		Subprocess.setNameField("SUBP_NAME");		
		Subprocess.setTableName(TableNames.TBL_QTM_TL_SUBPROCESSMST);
		if (UIUtils.isValidKeyId(processId))
			Subprocess.setCondSql(" AND SUBP_PROCESSID ='" + processId + "' ");
		return commonFilterDao.fillComboValues(Subprocess);
	}
	@Override
	public List<ComboBox> getSourceOFKPI(CommonFilter commonFilter,	ComboFilter currentFilter) throws Exception {
		
		currentFilter.setIdField("KSOK_KEYID");		
		currentFilter.setNameField("KSOK_NAME");
		currentFilter.setTableName(TableNames.TBL_KPI_TL_SOURCEOFKPI);
		return commonFilterDao.fillComboValues(currentFilter);
		
	}
	@Override
	public List<ComboBox> getCheckType(CommonFilter commonFilter) throws Exception {
		ComboFilter comboFilter = commonFilter.getCheckType();
		comboFilter.setNameField("CHEK_NAME ");
		comboFilter.setIdField("CHEK_KEYID");
		comboFilter.setTableName(TableNames.TBL_PLM_TL_CHECKTYPEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getWorkCentreComboList(CommonFilter commonFilter,
			ComboFilter currentFilter) throws Exception {
		// TODO Auto-generated method stub
		currentFilter.setIdField("WKCM_KEYID");
		currentFilter.setCodeField("WKCM_CODE");
		currentFilter.setNameField("WKCM_NAME");
		currentFilter.setTableName("GEN_TL_WORKCENTREMST");
		return commonFilterDao.fillComboValues(currentFilter);
	}
	@Override
	public List<ComboBox> getPlannerGroupComboList(CommonFilter commonFilter,
			ComboFilter currentFilter) throws Exception {
		// TODO Auto-generated method stub
		/*currentFilter.setIdField("SPGM_KEYID");
		currentFilter.setCodeField("SPGM_CODE");
		currentFilter.setNameField("SPGM_NAME");
		currentFilter.setTableName("SAP_TL_PLANNERGROUPMST");*/
		
		currentFilter.setIdField("GPLG_KEYID");
		currentFilter.setCodeField("GPLG_CODE");
		currentFilter.setNameField("GPLG_NAME");
		currentFilter.setTableName("GEN_TL_PLANNER_GROUP");
		
		return commonFilterDao.fillComboValues(currentFilter);
	}
	
	/*
	 * To Get All the Role
	 */
	public List<ComboBox> getRoleComboList(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception{
		
		StringBuffer sql = new StringBuffer("");
	/*	if(commonFilter.getLocation() != null && UIUtils.isValidKeyId(commonFilter.getLocation().getId()) ){
			//sql = new StringBuffer();
			sql.append( " and SBUT_LOCATIONID ='"+commonFilter.getLocation().getId() +"'");
			
		}
		if(commonFilter.getCompany() != null && UIUtils.isValidKeyId(commonFilter.getCompany().getId()) ){
			//sql = new StringBuffer();
			sql.append( "  and SBUT_COMPANYID ='"+commonFilter.getCompany().getId() +"'");
		}
		if( sql != null) {
			comboFilter.setCondSql(sql.toString());
		}*/
		
		           
		comboFilter.setNameField("ROLE_NAME");
		comboFilter.setCodeField("ROLE_CODE");
		comboFilter.setIdField("ROLE_KEYID");
		
		comboFilter.setTableName(TableNames.TBL_ADM_TL_ROLEMST);
		
		CommonMessage.debugMsg("This is my Test------------------->");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getCellComboList(CommonFilter commonFilter,
			String keyid, String sectionid, String flid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer condSql = new StringBuffer();
		if(UIUtils.isValidKeyId(keyid)){
			if(keyid.substring(0, 3).equals("CEL"))
				condSql.append(" AND CELL_KEYID != '" + keyid + "'");
		}
		if(UIUtils.isValidKeyId(commonFilter.getLineNotToShown()) )
		{
			commonFilter.getCell().setCondSql(" and CELL_KEYID <> '"+commonFilter.getLineNotToShown()+"'");
		}
		if(UIUtils.isValidKeyId(sectionid)){
			condSql.append(" and CELL_SECTIONID= '"+sectionid+"' ");
		}
		//if(UIUtils.isValidKeyId(flid) && !UIUtils.isValidKeyId(sectionid)){
	/*	else {
			CommonMessage.debugMsg("inside flid cond...");
			condSql.append("AND CELL_FLID IN (SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE INSTR(PARENTFLIDS,'");
			condSql.append(flid);
			condSql.append("')>0 )");
		}*/
		if( condSql.length() > 0){
			commonFilter.getCell().setCondSql(condSql.toString());
		}
		CommonMessage.debugMsg(commonFilter.getCell().getCondSql());
		return commonFilterDao.getCellComboList(commonFilter);
	}
	
	public List<ComboBox> getKaizenThemeCategory(CommonFilter commonFilter)throws Exception{
		ComboFilter kaizentheme=commonFilter.getKznmThemecategoryid();
		kaizentheme.setNameField("KZCT_NAME");
		kaizentheme.setIdField("KZCT_KEYID");
		kaizentheme.setTableName("KZN_TL_CATEGORYTHMMST");
		return commonFilterDao.fillComboValues(kaizentheme);
	}
	@Override
public String getEmployeeLocation(String userKeyid)throws Exception{	
		return commonFilterDao.getEmployeeLocation(userKeyid);
	}
	
	@Override
	public List<ComboBox> getMocItemComboList(CommonFilter commonFilter) throws Exception {
		ComboFilter mocitem = commonFilter.getMocItem();
		mocitem.setIdField("MOC_ITM_KEYID");
		//trade.setCodeField("TRDM_CODE");
		mocitem.setNameField("MOC_ITM_ITEM");
		mocitem.setTableName(TableNames.TBL_MOC_TL_ITEM);
		
		return commonFilterDao.fillComboValues(mocitem);
	}
	
}
