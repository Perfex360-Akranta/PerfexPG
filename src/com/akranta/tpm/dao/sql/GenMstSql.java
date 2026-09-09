
package com.akranta.tpm.dao.sql;


public class GenMstSql {
	
	/*
	public static String selectEquipment()
	{
		String sql = "SELECT DISTINCT MCHM_KEYID AS KEYID,MCHM_MACHINENO AS EQUIPMENTNO,";
			   sql += "MCHM_MACHINENAME AS NAME,REPLACE(CELL_CODE,'{}','') AS CELL,";
			   sql += "REPLACE(CSTM_CODE ,'{}','') AS COSTCENTRE,";
			   sql += "REPLACE( SECT_CODE,'{}','') AS SECTIONNAME,REPLACE(MAINGRPNAME ,'{}','') AS MAINGROUP,";
			   sql += "REPLACE(SUBGRPNAME ,'{}','') AS SUBGROUP,PRPM_CODE AS PURPOSE,";
			   sql += "REPLACE(CATM_CODE,'{}','') AS CATEGORY,SBCM_CODE AS SUBCATEGORY,"; 
			   sql += "MCHM_MACHINERANK AS MACHINERANK,JHSM_CODE AS AUTMAINTSTEPS,"; 
			   sql += "MCHM_PHASE,MCHM_WIRES,MCHM_IPVOLT AS INPUTVOLT,MCHM_IPVOLTMIN AS INPUTMINVOLT,"; 
			   sql += "MCHM_IPVOLTMAX AS INPUTMAXVOLT,MCHM_IPFREQ AS INPUTFREQ,";
			   sql += "MCHM_IPFREQMIN AS INPUTMINFREQ,MCHM_IPFREQMAX AS INPUTMAXFREQ,"; 
			   sql += "REPLACE(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,MCHM_SPECIFICATION,MCHM_REMARKS,"; 
			   sql += "TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY') AS MFRDATE,MCHM_MFRSLNO AS MFRSLNO,"; 
			   sql += "MCHM_MFRREMARKS AS MFRREMARKS,MCHM_PONO AS PONO,"; 
			   sql += "TO_CHAR(MCHM_PODATE,'DD-MON-YYYY') AS PODATE,";
			   sql += "TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY') AS PURCHASEDATE,";
			   sql += "NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"; 
			   sql += "TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY') AS INSTALLDATE,MCHM_ISUNDERWARRANTY,"; 
			   sql += "TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY') AS WARRANTYDATE,"; 
			   sql += "MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS,MCHM_ISUNDERAMC,"; 
			   sql += "TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY') AS AMCDATE,";
			   sql += "TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY') AS AMCRENEWALDATE,"; 
			   sql += "REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS"; 
			   sql += " FROM GEN_TL_MACHINEMST, GEN_TL_CELLMST, GEN_TL_SECTIONMST,GEN_VW_EQPGROUPMST,";
			   sql += "GEN_TL_PURPOSEMST, GEN_TL_CATEGORYMST, GEN_TL_SUBCATEGORYMST, GEN_TL_JHSTEPMST, GEN_TL_COSTCENTREMST";
			   sql += " WHERE MCHM_ACTIVE = 'Y' AND CELL_KEYID = MCHM_CELLID";
			   sql += " AND SECT_KEYID = CELL_SECTIONID AND MCHM_COSTCENTREID=CSTM_KEYID(+)"; 
			   sql += " AND MCHM_EQUIPMENTGROUP = EQGM_KEYID(+) AND MCHM_PURPOSE = PRPM_KEYID(+)";
			   sql += " AND MCHM_CATEGORY = CATM_KEYID(+) AND MCHM_SUBCATEGORY = SBCM_KEYID(+)"; 
			   sql += " AND MCHM_JHSTEP = JHSM_KEYID(+) AND CELL_ACTIVE = 'Y'";
			   sql += " AND CELL_FACTORYID ='' AND CELL_SECTIONID ='' AND CELL_KEYID ='';";
		return sql; 
	}
	*/
	public static String selectFact()
	{
		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/ 
		String sql = " SELECT FACT_KEYID AS KEYID,FACT_NAME AS NAME , FACT_CODE AS CODE FROM GEN_TL_FACTORYMST ";
		
		return sql;
	}
	
	
	
	public static String selectEmployee()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		/*
		 * StringBuffer sb = new StringBuffer(); sb.append(
		 * " SELECT  EMPM_KEYID AS KEYID, EMPM_NAME AS NAME , EMPM_CODE AS CODE, "); sb.
		 * append(" DECODE (empm_employeetype,'R', 'REGULAR' ,  'A', 'ASSOCIATE' ,'C', 'CONTRACT','M','MANAGER','B','BADLI','T','TRAINEE','E','EXECUTIVE','O','OTHERS') AS TYPE,EMPM_EMPLOYEENUMBER AS EMPNO,DEPT_NAME AS DEPARTMENT,"
		 * ); sb.
		 * append(" DESG_NAME AS DESIGNATION,ROLE_NAME AS ROLE,FACT_NAME AS UNIT,SECT_NAME AS SECTION,CELL_NAME AS LINE,TRDM_NAME AS TRADE"
		 * ); sb.
		 * append(" FROM GEN_TL_EMPLOYEEMST,GEN_TL_DEPARTMENTMST,GEN_TL_DESIGNATIONMST,GEN_TL_FACTORYMST,"
		 * );
		 * sb.append(" GEN_TL_SECTIONMST,GEN_TL_CELLMST,GEN_TL_TRADEMST,GEN_TL_ROLEMST"
		 * ); sb.
		 * append(" WHERE EMPM_DEPARTMENTID = DEPT_KEYID(+) AND EMPM_DESIGNATIONID = DESG_KEYID(+)"
		 * ); sb.
		 * append(" AND EMPM_FACTORYID = FACT_KEYID(+) AND EMPM_SECTIONID = SECT_KEYID(+)"
		 * ); sb.
		 * append(" AND EMPM_CELLID = CELL_KEYID(+) AND EMPM_TRADEID = TRDM_KEYID(+) AND EMPM_ROLEID = ROLE_KEYID(+) AND EMPM_ACTIVE IN(?,?) AND EMPM_KEYID NOT IN (SELECT USRM_CCNO FROM ADM_TL_USERMST WHERE USRM_ISADMINISTARTOR='Y') order by KEYID desc  "
		 * );
		 */	
		
		
		//sriram-2 changes oct-16-25
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT e.EMPM_KEYID AS KEYID, e.EMPM_NAME AS NAME, e.EMPM_CODE AS CODE, ");
		sb.append(" CASE e.EMPM_EMPLOYEETYPE ");
		sb.append("   WHEN 'R' THEN 'REGULAR' ");
		sb.append("   WHEN 'A' THEN 'ASSOCIATE' ");
		sb.append("   WHEN 'C' THEN 'CONTRACT' ");
		sb.append("   WHEN 'M' THEN 'MANAGER' ");
		sb.append("   WHEN 'B' THEN 'BADLI' ");
		sb.append("   WHEN 'T' THEN 'TRAINEE' ");
		sb.append("   WHEN 'E' THEN 'EXECUTIVE' ");
		sb.append("   WHEN 'O' THEN 'OTHERS' ");
		sb.append(" END AS TYPE, ");
		sb.append(" e.EMPM_EMPLOYEENUMBER AS EMPNO, d.DEPT_NAME AS DEPARTMENT, ");
		sb.append(" des.DESG_NAME AS DESIGNATION, r.ROLE_NAME AS ROLE, f.FACT_NAME AS UNIT, ");
		sb.append(" s.SECT_NAME AS SECTION, c.CELL_NAME AS LINE, t.TRDM_NAME AS TRADE ");
		sb.append(" FROM GEN_TL_EMPLOYEEMST e ");
		sb.append(" LEFT JOIN GEN_TL_DEPARTMENTMST d ON e.EMPM_DEPARTMENTID = d.DEPT_KEYID ");
		sb.append(" LEFT JOIN GEN_TL_DESIGNATIONMST des ON e.EMPM_DESIGNATIONID = des.DESG_KEYID ");
		sb.append(" LEFT JOIN GEN_TL_FACTORYMST f ON e.EMPM_FACTORYID = f.FACT_KEYID ");
		sb.append(" LEFT JOIN GEN_TL_SECTIONMST s ON e.EMPM_SECTIONID = s.SECT_KEYID ");
		sb.append(" LEFT JOIN GEN_TL_CELLMST c ON e.EMPM_CELLID = c.CELL_KEYID ");
		sb.append(" LEFT JOIN GEN_TL_TRADEMST t ON e.EMPM_TRADEID = t.TRDM_KEYID ");
		sb.append(" LEFT JOIN GEN_TL_ROLEMST r ON e.EMPM_ROLEID = r.ROLE_KEYID ");
		sb.append(" WHERE e.EMPM_ACTIVE IN (?, ?) ");
		sb.append(" AND e.EMPM_KEYID NOT IN (SELECT USRM_CCNO FROM ADM_TL_USERMST WHERE USRM_ISADMINISTARTOR = 'Y') ");
		sb.append(" ORDER BY KEYID DESC ");
		
		/*String sql = " select *  from  (SELECT rownum rn, EMPM_KEYID AS KEYID, EMPM_NAME AS NAME , EMPM_CODE AS CODE, "+
		   " EMPM_EMPLOYEETYPE AS TYPE,EMPM_EMPLOYEENUMBER AS EMPNO,DEPT_NAME AS DEPARTMENT,"+
		   " DESG_NAME AS DESIGNATION,FACT_NAME AS FACTORY,SECT_NAME AS SECTION,CELL_NAME AS CELL,TRDM_NAME AS TRADE"+
		   " FROM GEN_TL_EMPLOYEEMST,GEN_TL_DEPARTMENTMST,GEN_TL_DESIGNATIONMST,GEN_TL_FACTORYMST,"+
		    " GEN_TL_SECTIONMST,GEN_TL_CELLMST,GEN_TL_TRADEMST"+
		   " WHERE EMPM_DEPARTMENTID = DEPT_KEYID(+) AND EMPM_DESIGNATIONID = DESG_KEYID(+)"+
		    " AND EMPM_FACTORYID = FACT_KEYID(+) AND EMPM_SECTIONID = SECT_KEYID(+)"+
		    " AND EMPM_CELLID = CELL_KEYID(+) AND EMPM_TRADEID = TRDM_KEYID(+) order by KEYID desc)  a where rn between ? and ? ";*/
		  return sb.toString();
	}
	
	public static String selectEmployeeCost()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT EMPM_KEYID AS KEYID,EMPM_NAME AS NAME , EMPC_COSTPERHOUR, EMPC_OTCOSTPERHOUR,  EMPC_CALLOUTCOSTPERHOUR  ";
			   sql += " FROM GEN_TL_EMPLOYEECOST, GEN_TL_EMPLOYEEMST WHERE  EMPC_EMPLOYEEID = EMPM_KEYID AND EMPC_TYPE = 'E' AND EMPC_ACTIVE in (?,?)    ";
		return sql;
	}	

	public static String selectUtilityCost()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT TOLM_KEYID AS KEYID,TOLM_NAME AS NAME , EMPC_COSTPERHOUR,EMPC_OTCOSTPERHOUR,  EMPC_CALLOUTCOSTPERHOUR ";
			   sql += " FROM GEN_TL_EMPLOYEECOST, GEN_TL_TOOLSMST WHERE EMPC_EMPLOYEEID = TOLM_KEYID AND EMPC_TYPE = 'U' AND EMPC_ACTIVE IN(?,?) ";
		return sql;
	}
	public static String selectCircle()
	{
		String sql = " SELECT CRCM_KEYID AS KEYID ,CRCM_CODE AS CODE,CRCM_NAME AS NAME,CRCM_DESCRIPTION AS DESCRIPTION ,EMPM_NAME AS OWNER";
         
			   sql += " FROM GEN_TL_CIRCLEMST,GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID(+)=CRCM_OWNER AND CRCM_ACTIVE IN(?,?)";
		return sql;
	}

	public static String selectContractorCost()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT CNCS_VENDORID AS KEYID,AMVM_NAME AS NAME   ,  ";
			   sql += " UNGM_CODE AS CODE, CNCS_COSTPERHOUR AS COSTPERHOUR, CNCS_OTCOST AS OTCOST, CNCS_HOLIDAYCOST AS HOLIDAY" ;
			   sql += "  FROM WOM_TL_CONTRACTORMST, GEN_TL_AMCVENDORMST, PLM_TL_UNSKILLEDGRADEMST_I " ;
			   sql += " WHERE CNCS_VENDORID = AMVM_KEYID AND UNGM_KEYID = CNCS_UNSKILLEDGRADEID ";		
			   sql += " AND CNCS_ACTIVE IN(?,?) ";
		return sql;
	}
	
	public static String selectUtility()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT  TOLM_KEYID AS KEYID,TOLM_NAME AS NAME , TOLM_CODE AS CODE FROM GEN_TL_TOOLSMST ";
			   sql += " WHERE TOLM_TYPE='U' AND TOLM_ACTIVE IN(?,?)  ";
		return sql;
	}
	
	public static String selectTools()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT  TOLM_KEYID AS KEYID,TOLM_NAME AS NAME , TOLM_CODE AS CODE FROM GEN_TL_TOOLSMST";
			   sql += " WHERE TOLM_TYPE='T' AND TOLM_ACTIVE IN(?,?) ";
		return sql;
	}
	
	public static String getCount()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = "SELECT COUNT(*) FROM ?";
		return sql;
	}
	
	
	public static String selectAssembly()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		
		// sriram-1 oct -16-2025 removed function close bracket ) before "  
		String sql = " SELECT  ASSM_KEYID AS KEYID , ASSM_NAME AS NAME , ASSM_CODE AS CODE FROM GEN_TL_ASSEMBLYMST WHERE ASSM_ACTIVE='Y'";
			   sql += "  a  ";
		return sql;
	}
	public static String selectCell()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT  CELL_KEYID AS KEYID ,ALLPARENTS  as FUNCTIONLOCN, CELL_NAME AS NAME , CELL_CODE AS CODE FROM GEN_TL_CELLMST,GEN_MV_FLIDHIERARCHY WHERE CELL_FLID=FLID and  CELL_ACTIVE  in(?,?)  ";
			  
		return sql;
	}
	public static String selectSbu()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " select SBUT_KEYID AS KEYID ,ALLPARENTS  as FUNCTIONLOCN,SBUT_NAME AS NAME,SBUT_CODE AS CODE FROM GEN_TL_SBUMST,GEN_MV_FLIDHIERARCHY WHERE  SBUT_FLID=FLID and SBUT_ACTIVE IN (?,?)  ";
			       
		return sql;
	}
	public static String selectPbu()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " select PBUT_KEYID AS KEYID,ALLPARENTS  as FUNCTIONLOCN,PBUT_NAME AS NAME,PBUT_CODE AS CODE FROM GEN_TL_PBUMST,GEN_MV_FLIDHIERARCHY WHERE PBUT_FLID=FLID and PBUT_ACTIVE IN (?,?)  ";
			        
		return sql;
	}
	
	public static String selectSection()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT SECT_KEYID AS KEYID ,ALLPARENTS  as FUNCTIONLOCN, SECT_NAME AS NAME , SECT_CODE AS CODE FROM GEN_TL_SECTIONMST,GEN_MV_FLIDHIERARCHY WHERE  SECT_FLID=FLID and  SECT_ACTIVE in(?,?) ";
			 
		return sql;
	}
	
	public static String selectShift()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT SFTM_KEYID AS KEYID , SFTM_NAME AS NAME , SFTM_CODE AS CODE , to_char(SFTM_DURATION,'HH24:MI') AS DURATION , to_char(SFTM_STARTTIME,'HH24:MI') AS STARTTIME ,to_char(SFTM_ENDTIME,'HH24:MI')  AS ENDTIME   FROM GEN_TL_SHIFTMST  WHERE SFTM_ACTIVE in(?,?) ";
			  
		return sql;
		//, SFTM_DURATION AS DURATION , SFTM_STARTTIME AS STARTTIME , SFTM_ENDTIME AS ENDTIME  , SFTM_DESCRIPTION  AS DESCRIPTION
	}
	public static String selectFactory()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT FACT_KEYID AS KEYID , FACT_NAME AS NAME , FACT_CODE AS CODE FROM GEN_TL_FACTORYMST WHERE FACT_ACTIVE  in(?,?)  ";
			
		
		return sql;
	}
	public static String selectCompany()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = "SELECT COMP_KEYID AS KEYID , COMP_NAME AS NAME , COMP_CODE AS CODE FROM GEN_TL_COMPANYMST WHERE COMP_ACTIVE in(?,?) ";
			   
		return sql;
	}
	public static String selectLocation()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT LOCN_KEYID AS KEYID ,ALLPARENTS  as FUNCTIONLOCN, LOCN_NAME AS NAME , LOCN_CODE AS CODE FROM GEN_TL_LOCATIONMST,GEN_MV_FLIDHIERARCHY   WHERE LOCN_FLID=FLID and  LOCN_ACTIVE IN(?,?) ";
			   
		return sql;
	}
	
	public static String selectEquipmentGroup()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT EQGM_KEYID AS KEYID , EQGM_NAME  ");
		sql.append(" AS NAME ,   EQGM_CODE   AS CODE,  EQGM_REMARKS AS REMARKS");
		sql.append(" FROM GEN_TL_EQPGROUPMST,GEN_TL_FACTORYMST  ");
		sql.append(" WHERE EQGM_FACTORYID = FACT_KEYID(+) AND FACT_ACTIVE='Y'AND EQGM_ACTIVE IN(?,?) ");
  		
		return sql.toString();
		
	}

	public static String selectCheckListMainGrid() {
		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  TOPI.TOPI_KEYID || " );
		sql.append("	  CASE " );
		sql.append("      WHEN SKRM.SKRm_KEYID IS NOT NULL THEN '-' " );
		sql.append("      ELSE '' " );
		sql.append("    END || " );
		sql.append("     SKRM.SKRm_KEYID AS KEYID, " );
        sql.append("       TOPI.TOPI_NAME        AS \"Topic Name\", " );
		sql.append("   SKRM.SKRm_DESCRIPTION AS \"Rating Description\", " );
        sql.append("     COUNT(CHKD.CHKD_CHKM_KEYID) AS \"No.Of CheckList\" " );
		sql.append("    FROM ENT_TL_TOPICMST TOPI " );

		sql.append("     LEFT JOIN ENT_TL_CHECKLISTMST CHKM " );
		sql.append("  ON CHKM.CHKM_TOPI_KEYID = TOPI.TOPI_KEYID " );
		sql.append("   LEFT JOIN ENT_TL_CHECKLISTDTL CHKD " );
		sql.append("        ON CHKD.CHKD_CHKM_KEYID = CHKM.CHKM_KEYID " );
		sql.append("     LEFT JOIN ENT_TL_SKILL_RATINGMST SKRM " );
     	sql.append("         ON SKRM.SKRm_KEYID = CHKM.CHKM_SKRM_KEYID " );
		sql.append("   LEFT JOIN ENT_TL_EVALUATIONTYPEMST EVAL " );
        sql.append("       ON EVAL.EVAL_KEYID = TOPI.TOPI_EVALUATIONTYPEID " );
     	sql.append("    WHERE TOPI.TOPI_ACTIVE IN(?,? )" );
		sql.append("   AND EVAL.EVAL_TYPE = 'C' " );
		sql.append("    GROUP BY  TOPI.TOPI_KEYID, TOPI.TOPI_NAME, SKRM.SKRm_KEYID, SKRM.SKRm_DESCRIPTION ");
		
		/*
		 * sql.
		 * append(" SELECT TOPI_KEYID || NVL2(SKRM_KEYID, '-', NULL) || SKRM_KEYID AS KEYID, TOPI_NAME AS \"Topic Name\" ,SKRM_DESCRIPTION AS \"Rating Description\",COUNT (CHKD_CHKM_KEYID) AS \"No.Of CheckList\" FROM "
		 * ); sql.
		 * append(" ENT_TL_CHECKLISTMST,ENT_TL_CHECKLISTDTL,ENT_TL_TOPICMST,ENT_TL_SKILL_RATINGMST,ENT_TL_EVALUATIONTYPEMST WHERE CHKM_KEYID = CHKD_CHKM_KEYID(+) AND CHKM_TOPI_KEYID(+) = TOPI_KEYID  AND  SKRM_KEYID(+) =  CHKM_SKRM_KEYID "
		 * ); sql.
		 * append(" AND TOPI_ACTIVE IN(?,?) AND EVAL_KEYID=TOPI_EVALUATIONTYPEID(+) AND EVAL_TYPE='C' GROUP BY TOPI_KEYID || NVL2(SKRM_KEYID, '-', NULL) ||SKRM_KEYID, TOPI_NAME, SKRM_DESCRIPTION"
		 * );
		 */
		return sql.toString();
		
	}
	public static String selectTopic()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.
		 * append("  SELECT TOPI_KEYID as KEYID    ,TOPI_NAME  as NAME ,DECODE(TOPI_TYPE,'S','SKILL','K','KNOWLEDGE','A','ATTITUDE') as TYPE "
		 * );
		 * 
		 * sql.append(" FROM ENT_TL_TOPICMST  ");
		 * sql.append(" WHERE 1=1 AND TOPI_ACTIVE in (?,?)");
		 */
  		
		sql.append("  SELECT  TOPI_KEYID AS keyid, TOPI_NAME AS name, ");
		sql.append("		CASE TOPI_TYPE  WHEN 'S' THEN 'SKILL' WHEN 'K' THEN 'KNOWLEDGE' WHEN 'A' THEN 'ATTITUDE' ");
		sql.append("		END AS type FROM ENT_TL_TOPICMST WHERE TOPI_ACTIVE in (?,?) ");
		
		return sql.toString();
		
	}
	
	public static String selectBatch()
	{
		StringBuffer sql = new StringBuffer();

		sql.append( " SELECT  BACH_KEYID AS keyid,    BACH_NAME AS name,BACH_FROMDATE fromdate FROM ENT_TL_BATCHMST  WHERE BACH_ACTIVE IN (?,?)");
		
		return sql.toString();

	}
	
	public static String selectMessageBoard(){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MSGB_KEYID AS \"KEYID\",MSGB_TITLE AS \"Message Title\",MSGB_CONTENT AS \"Content\",to_char(MSGB_EFFECTIVEFROM) as \"EffectiveFrom\",to_char(MSGB_EFFECTIVETO) as \"Effective To\",MSGB_SHOWNFORDAYS as \"Show For Days\" ");
		
		sql.append(" FROM GEN_TL_MESSAGEBOARD  ");
		sql.append(" WHERE 1=1 AND MSGB_ACTIVE in (?,?)");
		return sql.toString();
	}
	public static String selectCtq()
	{		// ALIAS NAME IS Mandatory  it is used as identifer so do put the identifer eg:/*FACT_KEYID AS KEYID*/
		String sql = " SELECT KPIV_KEYID AS KEYID , KPIV_NAME AS NAME FROM QTM_TL_KPIVMST WHERE KPIV_ACTIVE in(?,?) ";
			 
		return sql;
	}
	
	public static String selectSapFunctionalLoc(){
		StringBuilder sql = new StringBuilder("SELECT  FNLN_FUNCTIONAL_LOCN as KEYID, FNLN_STRUCT_INDICATOR as \"Indicator\",   ") ;
		sql.append("  FNLN_CATEGORY as \"Category\", FNLN_FUNCTIONAL_LOCN \"Functional Location\" ," );
		sql.append("  FNLN_DESCRIPTION as \"Description\", FNLN_MAINT_PLANT \"Maint. Plant\" ," );
		sql.append("  FNLN_COMPANY_CODE as \"Company Code\", FNLN_BUSINESS_AREA \"Business Area\" ," );
		sql.append("  FNLN_COSTCENTER as \"Costcenter\", FNLN_PLANNING_PLANT \"Planning Plant\" ," );
		sql.append("  FNLN_PLANNER_GROUP as \"Planner Group\", FNLN_MAIN_WORKCENTER \"Work Center\" ," );
		sql.append("  FNLN_SUPERIOR_FUNCTIONAL_LOCN as \"Superior FL.\", FNLN_POSITION \"Position\", " );
		sql.append("  FNLN_EQP_INSTALL_ALLOWED as \"Eqp Install Allowed\" FROM SAP_FUNCTIONAL_LOCN where FNLN_active in(?,?) ");
		 
		return sql.toString(); 
	}
	
	public static String selectSapEqpMaster(){
		
		StringBuilder sql = new StringBuilder("SELECT  EQPM_NUMBER as KEYID, EQPM_NUMBER as \"EQUIPMENTNO\" ,") ;
		sql.append("  EQPM_CATEGORY as \"CATEGORY\", EQPM_DESCRIPTION as \"DESCRIPTION\" ," );
		sql.append("  EQPM_VALID_FROM as \"VALIDFROM\", EQPM_TECHOBJECT_TYPE \"TECHOBJECTTYPE\" ," );
		sql.append("  EQPM_AUTH_GROUP as \"AUTHGROUP\", EQPM_WEIGHT \"WEIGHT\" ," );
		sql.append("  EQPM_WEIGHT_CHAR as \"WEIGHTCHAR\", EQPM_SIZE_DIMENSION \"DIMENSION\" ," );
		sql.append("  EQPM_MANUFACTURER as \"MANUFACTURER\", EQPM_MFR_COUNTRY \"Manu.Country\" ," );
		sql.append("  EQPM_MODEL_NO as \"MODELNO\", EQPM_CONS_YEAR \"CONSYEAR\", " );
		sql.append("  EQPM_CONS_MONTH as \"CONSMONTH\",EQPM_INVENTORY_NO as \"INVENTORYNO\", " ) ;
		sql.append("  EQPM_MAINT_PLANT as \"MAINTPLANT\", EQPM_LOCATION \"LOCATION\" ," );
		sql.append("  EQPM_PLANT_SECTION as \"PLANTSECTION\", EQPM_ABC_INDICATOR as \"ABCINDICATOR\", " );
		sql.append("  EQPM_SORT_FIELD as \"SORTFIELD\",EQPM_COMPANY_CODE as \"COMPANYCODE\", " ) ;
		sql.append("  EQPM_BUSINESS_AREA as \"BUSINESSAREA\", EQPM_MAIN_ASSET_NO \"MAINASSETNO\" ," );
		sql.append("  EQPM_SUB_ASSET_NO as \"SUBASSETN0\", EQPM_COSTCENTER as \"COSTCENTER\", " );
		sql.append("  EQPM_PLANNING_PLANT as \"PLANNINGPLANT\",EQPM_PLANNER_GROUP as \"PLANNERGROUP\", " ) ;
		sql.append("  EQPM_MAIN_WORKCENTER as \"MAINWORKCENTER\", EQPM_FUNCTIONAL_LOCN \"FUNCTIONALLOCN\" ," );
		sql.append("  EQPM_SUPERIOR_EQP as \"SUPERIOR EQUIPMENT\", EQPM_POSITION as \"POSITION\", " );
		sql.append("  EQPM_WARRANTY_ST_DATE as \"WARRANTY START DATE\",EQPM_WARRANTY_ED_DATE as \"WARRANTY END DATE\", " ) ;
		sql.append("  EQPM_MATERIAL_NO as \"MATERIALNO\", EQPM_SERIAL_NO as \"SERIALNO\" " );
		sql.append(" FROM SAP_EQUIPMENT_MST where EQPM_ACTIVE in(?,?) ");
		 
		return sql.toString();
	}
	
	public static String selectSapDowntimeAreamst(){
		
		StringBuilder sql = new StringBuilder("SELECT  SAP_AREA_DTAREAID as KEYID,SAP_AREA_DTAREAID AS \" Area \", SAP_AREA_DTAREADESC as \"Description\"  ") ;
		sql.append("  FROM SAP_DOWNTIME_AREA_MST where  SAP_AREA_ACTIVE in (?,?) " );
		
		return sql.toString();
	}
	
	public static String selectSapDowntimeReason(){	
		
		StringBuilder sql = new StringBuilder("SELECT  SAP_REAS_DTREASONID as KEYID,SAP_REAS_DTAREAID as \"Area\",SAP_REAS_DTREASONID AS \" Reason \" ,SAP_REAS_DTREASONDESC as \" Description\"   ") ;
		sql.append("  FROM SAP_DOWNTIME_REASONS where   SAP_REAS_ACTIVE in (?,?) " );
		
		return sql.toString();
	}

/*	public static String  selectSapCostcentermst(){
		StringBuilder sql = new StringBuilder("SELECT  COCR_COSTCENTER as KEYID , COCR_CONTROLLING_AREA as \" Controlling Area \",COCR_COSTCENTER as \"Costcenter\", COCR_VALID_FROM_DATE_STR as \"Valid From Date\",   ") ;
		sql.append("  COCR_NAME \"Name\",COCR_DESCRIPTION as \"Description\", COCR_RESPONSIBILITY \"Responsibility\",COCR_CATEGORY \"Category\"," ) ;
		sql.append("  COCR_STD_HIERARCHY_AREA \"Hierarchy Area\", COCR_BUSINESS_AREA \"Business Area\",COCR_CURRENCY_KEY as \"Currency Key\",COCR_PROFITCENTER as \"Profit Center\" " );
		sql.append("  FROM SAP_costcenter where COCR_ACTIVE in (?,?) " );
		
		return sql.toString();
}*/
	
        public static String  selectSapCostcentermst(){
		StringBuilder sql = new StringBuilder("SELECT  CSTM_KEYID as KEYID ,CSTM_FACTORYID AS \" CSTM FACTORYID \",CSTM_CODE as \"CSTMCODE\",CSTM_NAME as \"CSTMNAME\", CSTM_DESCRIPTION as \"DESCRIPTION\"   ") ;
		//sql.append("  COCR_NAME \"Name\",COCR_DESCRIPTION as \"Description\", COCR_RESPONSIBILITY \"Responsibility\",COCR_CATEGORY \"Category\"," ) ;
		//sql.append("  COCR_STD_HIERARCHY_AREA \"Hierarchy Area\", COCR_BUSINESS_AREA \"Business Area\",COCR_CURRENCY_KEY as \"Currency Key\",COCR_PROFITCENTER as \"Profit Center\" " );
		sql.append("  FROM GEN_TL_COSTCENTREMST where CSTM_ACTIVE in (?,?) " );
		return sql.toString();
	}
	
	/*public static String  selectSapWorkcenter(){
		StringBuilder sql = new StringBuilder(" SELECT   WKCR_WORKCENTER as KEYID,WKCR_PLANT AS \"Plant\", WKCR_WORKCENTER as \"Work Center\", WKCR_CATEGORY as \"Category\",   ") ;
		sql.append("  WKCR_DESCRIPTION \"Description\",WKCR_RESPONSIBILITY as \"Responsibility\", WKCR_TASKLIST_KEY \"Task List Key\",WKCR_STDVALUE_KEY \"Std. Value Key\"," ) ;
		sql.append("  WKCR_CAP_CATEGORY \"Cap Category\", WKCR_CAP_PLAN_GROUP \"Cap Plan Group\",WKCR_CAP_MEASURE_BASE_CHAR as \"Cap Measure Base Char\",WKCR_START_TIME_STR as \"Start Time\", " );
		sql.append("  WKCR_END_TIME_STR \"End Time\", WKCR_CAP_UTIL_RATE \"Cap Util Rate\",WKCR_BREAK_CUMLENGTH as \"Break Cumlength\",WKCR_INDI_CAP_NO as \"Indi. Cap No.\", " );
		sql.append("  WKCR_INDICATOR \"Indicator\", WKCR_START_DATE_STR \"Start Date\",WKCR_END_DATE as \"End Date\",WKCR_COSTCENTER as \"Costcenter\", " );
		sql.append("  WKCR_ACTIVITY_TYPE \"Activity Type\", WKCR_ACTIVITY_CHAR \"Activity Char\",WKCR_FORMULA as \"Formula\",WKCR_ACTTYPE_INT as \"Act.Type Int.\", WKCR_COST_FOMULAKEY as \"Cost Formula Key \" " );
		sql.append("  FROM SAP_workcenter where WKCR_ACTIVE in (?,?) " );
		
		return sql.toString();
	}*/

      public static String  selectSapWorkcenter(){
		StringBuilder sql = new StringBuilder(" SELECT WKCM_KEYID as KEYID,WKCM_FACTORYID AS \"FactoryId\", WKCM_CODE as \"WKCMCODE\", WKCM_NAME as \"WKCMNAME\", WKCM_DESCRIPTION \"Description\"  ") ;
		//sql.append("   " ) ;
		//sql.append("  WKCR_CAP_CATEGORY \"Cap Category\", WKCR_CAP_PLAN_GROUP \"Cap Plan Group\",WKCR_CAP_MEASURE_BASE_CHAR as \"Cap Measure Base Char\",WKCR_START_TIME_STR as \"Start Time\", " );
		//sql.append("  WKCR_END_TIME_STR \"End Time\", WKCR_CAP_UTIL_RATE \"Cap Util Rate\",WKCR_BREAK_CUMLENGTH as \"Break Cumlength\",WKCR_INDI_CAP_NO as \"Indi. Cap No.\", " );
		//sql.append("  WKCR_INDICATOR \"Indicator\", WKCR_START_DATE_STR \"Start Date\",WKCR_END_DATE as \"End Date\",WKCR_COSTCENTER as \"Costcenter\", " );
		//sql.append("  WKCR_ACTIVITY_TYPE \"Activity Type\", WKCR_ACTIVITY_CHAR \"Activity Char\",WKCR_FORMULA as \"Formula\",WKCR_ACTTYPE_INT as \"Act.Type Int.\", WKCR_COST_FOMULAKEY as \"Cost Formula Key \" " );
		sql.append("  FROM GEN_TL_WORKCENTREMST where WKCM_ACTIVE in (?,?) " );
		
		return sql.toString();
	}        
         
	
	/*public static String  selectSapVendorMaster(){
		StringBuilder sql = new StringBuilder(" SELECT   SPNR_NAME as KEYID,SPNR_NAME AS \"Vendor Name\", SPNR_SHORTNAME as \"Short Text\", SPNR_ADDRESS as \"Address\",   ") ;
		sql.append("  SPNR_CONTACT_PERSOR as \"Contact Person\",SPNR_EMAIL_ADDRESS as \"E-Mail Adress\",SPNR_MOBILE_NUMBER as \"Mobile No.\" ,SPNR_PAN_NUMBER as \"Pan Number\" ,SPNR_CITY as ");
		sql.append(" \"City\" ,SPNR_GROUP as \"Group\", SPNR_PURCHASE_GROUP as \"Purchase Group\",SPNR_BUYER_EMAID as \"Buyer Email Id\" FROM SAP_TL_PARTNORMST where SPNR_TYPE = 'VEN' and SPNR_ACTIVE in (?,?) " );
		
		return sql.toString();
	}*/
      
   
	public static String  selectSapVendorMaster(){
	StringBuilder sql = new StringBuilder(" SELECT   PNOR_NAME as KEYID,PNOR_NAME AS \"VENDORNAME\", PNOR_SHORTNAME as \"SHORTNAME\", PNOR_ADDRESS as \"ADDRESS\",   ") ;
	sql.append("  PNOR_CONTACT_PERSOR as \"CONTACTPERSOR\",PNOR_EMAIL_ADDRESS as \"E-MailAdress\",PNOR_MOBILE_NUMBER as \"MOBILENUMBER\", PNOR_PAN_NUMBER as \"PANNUMBER\" ,PNOR_CITY as ");
	sql.append(" \"CITY\",PNOR_GROUP as \"PNORGROUP\",PNOR_PURCHASE_GROUP as \"PURCHASEGROUP\",PNOR_BUYER_EMAID as \"BUYEREMAID\" FROM GEN_TL_PARTNORMST where PNOR_TYPE = 'VEN' and PNOR_ACTIVE in (?,?) " );
	
	return sql.toString();
}	
	
	public static String  selectSapCustomerMaster(){
		StringBuilder sql = new StringBuilder(" SELECT   SPNR_NAME as KEYID,SPNR_NAME AS \"Customer Name\", SPNR_SHORTNAME as \"Short Text\", SPNR_ADDRESS as \"Address\",   ") ;
		sql.append("  SPNR_CONTACT_PERSOR as \"Contact Person\",SPNR_EMAIL_ADDRESS as \"E-Mail Adress\",SPNR_MOBILE_NUMBER as \"Mobile No.\" ,SPNR_PAN_NUMBER as \"Pan Number\",SPNR_CITY as ");
		sql.append(" \"CITY\" ,SPNR_GROUP as \"GROUP\", SPNR_PURCHASE_GROUP as \"Purchase Group\",SPNR_BUYER_EMAID as \"Buyer Email Id\" FROM SAP_TL_PARTNORMST where SPNR_TYPE = 'CUS' and SPNR_ACTIVE in (?,?) " );
		
		return sql.toString();
	}
	
	/*public static String  selectSapServiceCenter(){
		StringBuilder sql = new StringBuilder(" SELECT SERV_ACTIVITY_NUMBER as KEYID,  SERV_ACTIVITY_NUMBER as \"Number\",SERV_MATERIAL_GROUP AS \"Material Group\", SERV_UOM as \"UOM\", SERV_SERVICE_CATEGORY as \"Service Category\",   ") ;
		sql.append("  SERV_DIVISION as \"Service Division\",SERV_VALUATION_CLASS as \"Valuation Class\",SERV_SHORT_TEXT as \"Short Text\" ,SERV_TEXT_LINE as ");
		sql.append(" \"TextLine\"  FROM SAP_TL_SERVICEMST where 'Y' in (?,?) " );
		
		return sql.toString();
	}*/
   public static String  selectSapServiceCenter(){
	StringBuilder sql = new StringBuilder(" SELECT SERVM_ACTIVITY_NUMBER as KEYID,  SERVM_ACTIVITY_NUMBER as \"ACTIVITYNUMBER\",SERVM_MATERIAL_GROUP AS \"MATERIALGROUP\", SERVM_UOM as \"UOM\", SERVM_SERVICE_CATEGORY as \"SERVICECATEGOR\",   ") ;
	sql.append("  SERVM_DIVISION as \"DIVISION\",SERVM_VALUATION_CLASS as \"VALUATIONCLASS\",SERVM_SHORT_TEXT as \"SHORTTEXT\" ,SERVM_TEXT_LINE as ");
	sql.append(" \"TEXTLINE\"  FROM GEN_TL_SERVICEMST where 'Y' in (?,?) " );
	
	return sql.toString();
}	
	
	public static String  selectSapWBSElement(){
		StringBuilder sql = new StringBuilder(" SELECT  GWE_KEYID as KEYID, GWE_PROJECT_CODE as \"PROJECTCODE\",GWE_CODE AS \"CODE\", GWE_DESCRIPTION as \"DESCRIPTION\", GWE_LEVEL_NO as \"LEVELNO\",   ") ;
		sql.append("  GWE_CUR_NOOFPROJECTS as \"NOOFPROJECTS\",GWE_UP as \"UP\",GWE_DOWN as \"DOWN\" ,GWE_LEFT as ");
		sql.append(" \"LEFT\" ,GWE_RIGHT as \"RIGHT\" ,GWE_DATAFLAG as \"DATAFLAG\" FROM GEN_TL_WBSELEMENT where 'Y' in (?,?) " );
		
		return sql.toString();
	}

	public static String  selectSapEnvironmentBOM(){
		StringBuilder sql = new StringBuilder(" SELECT  EBOM_EQUIP_NO as KEYID, EBOM_EQUIP_NO as \"Equipment No\",EBOM_PLANT AS \"Plant\", EBOM_USAGE as \"Usage\",EBOM_TEXT as \"Text\",   ") ;
		sql.append("  EBOM_ITEM_NO as \"Item  No\",EBOM_ITEM_CATEGORY as \"Item Category\",EBOM_COMPONENT as \"Component\" ,EBOM_COMPONENT_DESC as ");
		sql.append(" \"Component desc\" ,EBOM_COMPONENT_NO as \"Quantity\" ,EBOM_MEASURE_CHAR as \"Measure Char\",EBOM_DATAFLAG as\"Data Flag\" FROM SAP_EQUIPMENT_BOM where 'Y' in (?,?) " );
		
		return sql.toString();
	}
	
}
