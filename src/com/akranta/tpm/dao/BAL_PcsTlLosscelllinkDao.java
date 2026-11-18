package com.akranta.tpm.dao;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLosscelllink;

public interface BAL_PcsTlLosscelllinkDao {

	public abstract PcsTlLosscelllink create(PcsTlLosscelllink pcsTlLosscelllink) throws Exception;
	
	public BAL_PcsTlLosscelllink createJHLink(List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkList) throws Exception;
	
	public abstract PcsTlLosscelllink update(PcsTlLosscelllink pcsTlLosscelllink) throws Exception;
	public abstract PcsTlLosscelllink delete(PcsTlLosscelllink pcsTlLosscelllink) throws Exception;

}

