package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlCellmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.BAL_GenTlCellmst;
import com.akranta.tpm.model.BAL_GenTlSectionmst;

public interface BAL_GenTlCellmstService {
	
		public BAL_GenTlCellmst create(BAL_GenTlCellmst newGenTlCellmst,BAL_GenTlCellmst oldGenTlCellmst,  BAL_GenTlCellmstBean genTlCellmstBean ) throws ValidationExceptions, Exception;
		public BAL_GenTlCellmst update(BAL_GenTlCellmst newGenTlCellmst,BAL_GenTlCellmst oldGenTlCellmst,  BAL_GenTlCellmstBean genTlCellmstBean )  throws Exception;
		public BAL_GenTlCellmst delete(String delemode, BAL_GenTlCellmst genTlCellmst) throws ValidationExceptions, Exception;
		public BAL_GenTlCellmst select(String keyid) throws Exception;
		public List<ComboBox> getGenTlCellmstcombo(String condSql)  throws Exception;
		public String getCompany(BAL_GenTlCellmst genTlCellmst) throws SQLException;
		public BAL_GenTlCellmst fillcellcontrol(String cellkeyid) throws  Exception;

}
