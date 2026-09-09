package com.akranta.tpm.dao;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.PcsTlLosscelllink;

public interface PcsTlLosscelllinkDao {

	public abstract PcsTlLosscelllink create(PcsTlLosscelllink pcsTlLosscelllink) throws Exception;
	
	public PcsTlLosscelllink createJHLink(List<PcsTlLosscelllink> pcsTlLosscelllinkList) throws Exception;
	
	public abstract PcsTlLosscelllink update(PcsTlLosscelllink pcsTlLosscelllink) throws Exception;
	public abstract PcsTlLosscelllink delete(PcsTlLosscelllink pcsTlLosscelllink) throws Exception;

}

