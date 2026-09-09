package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlCellmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlCellmst;
//import com.akranta.tpm.model.GenTlSectionmst;

public interface GenTlCellmstService {
	
		public GenTlCellmst create(GenTlCellmst newGenTlCellmst,GenTlCellmst oldGenTlCellmst,  GenTlCellmstBean genTlCellmstBean ) throws ValidationExceptions, Exception;
		public GenTlCellmst update(GenTlCellmst newGenTlCellmst,GenTlCellmst oldGenTlCellmst,  GenTlCellmstBean genTlCellmstBean )  throws Exception;
		public GenTlCellmst delete(String delemode, GenTlCellmst genTlCellmst) throws ValidationExceptions, Exception;
		public GenTlCellmst select(String keyid) throws Exception;
		public List<ComboBox> getGenTlCellmstcombo(String condSql)  throws Exception;
		public String getCompany(GenTlCellmst genTlCellmst) throws SQLException;
		public GenTlCellmst fillcellcontrol(String cellkeyid) throws  Exception;
		public String getfunctionalid(String elemId) throws Exception;

}
