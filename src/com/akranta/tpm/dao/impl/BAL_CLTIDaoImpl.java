package com.akranta.tpm.dao.impl;

import java.util.List;

import com.akranta.tpm.dao.BAL_CLTIDao;
import com.akranta.tpm.model.CommonFilter;

public class BAL_CLTIDaoImpl implements BAL_CLTIDao {
	
	private DBActionTemplate dbActionTemplate;
	public BAL_CLTIDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
	}

//	@Override
//	public List<String[]> getCLTIReport(CommonFilter commonFilter)
//			throws Exception {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" Select ' Item', 'What / Standard','How','Sched','Resp','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30'"+
//				" From Dual "+
//				" union all "+
//				" Select 'Maintaining F.C.Lye and Water ratio 1:5', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''"+
//				" From Dual "+
//				" union all "+
//				" Select 'Maintaining strength of F.C lye around 48-49%', 'No Unloading if any deviation','Eye','Each Truck','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''"+
//				" From Dual "+
//				" union all "+
//				" Select 'Monitoring density fluctuation through trends', '1.112-1.116','Eye','Twice in a Shift','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''"+
//				" From Dual "+
//				" union all "+
//				" Select 'Cross checking F.C.Lye flow meter density with tank level', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''"+
//				" From Dual "+
//				" union all "+
//				" Select 'Calibrating flowmeters', 'It should match with density meter','Eye','Once in a Month','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual "+
//				" union all "+
//				" Select 'Cross checking F.C.Lye flow meter density with tank level', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual "+
//				" union all "+
//				" Select 'Maintaining strength of F.C lye around 48-49%', 'No Unloading if any deviation','Eye','Each Truck','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual "+
//				" union all "+
//				" Select 'Maintaining F.C.Lye and Water ratio 1:5', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual "+
//				" union all "+
//				" Select 'Monitoring density fluctuation through trends', '1.112-1.116','Eye','Twice in a Shift','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual "+
//				
//				" union all "+
//				" Select 'Calibrating flowmeters', 'It should match with density meter','Eye','Once in a Month','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual "+
//				
//				" union all "+
//				" Select 'Cross checking F.C.Lye flow meter density with tank level', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','' "+
//				" From Dual ");
//		
//		System.out.println("sql..." + sql);
//		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//		return gridData;
//	}
	
	@Override
	public List<String[]> getCLTIReport(CommonFilter commonFilter) throws Exception {
	    StringBuffer sql = new StringBuffer();

	    // Header: 5 fixed + 31 day columns (col '0' added OR match 36 total)
	    // Data rows have: 5 fixed cols + 31 empty strings = 36 cols
	    // So header must also have 36 cols → add one more label e.g. '0' or adjust

	    String dataRow1  = "SELECT 'Maintaining F.C.Lye and Water ratio 1:5', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''";
	    String dataRow2  = "SELECT 'Maintaining strength of F.C lye around 48-49%', 'No Unloading if any deviation','Eye','Each Truck','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''";
	    String dataRow3  = "SELECT 'Monitoring density fluctuation through trends', '1.112-1.116','Eye','Twice in a Shift','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''";
	    String dataRow4  = "SELECT 'Cross checking F.C.Lye flow meter density with tank level', 'No Deviation','Eye','Every Hour','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''";
	    String dataRow5  = "SELECT 'Calibrating flowmeters', 'It should match with density meter','Eye','Once in a Month','DC operator','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''";

	    sql.append("SELECT ' Item','What / Standard','How','Sched','Resp','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'")
	       .append(" UNION ALL ").append(dataRow1)
	       .append(" UNION ALL ").append(dataRow2)
	       .append(" UNION ALL ").append(dataRow3)
	       .append(" UNION ALL ").append(dataRow4)
	       .append(" UNION ALL ").append(dataRow5)
	       .append(" UNION ALL ").append(dataRow4)  
	       .append(" UNION ALL ").append(dataRow2)   // Maintaining strength (repeat)
	       .append(" UNION ALL ").append(dataRow1)   // Maintaining ratio (repeat)
	       .append(" UNION ALL ").append(dataRow3)   // Monitoring density (repeat)
	       .append(" UNION ALL ").append(dataRow5)   
	       .append(" UNION ALL ").append(dataRow4);  

	    System.out.println("sql..." + sql);
	    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	    return gridData;
	}

}
