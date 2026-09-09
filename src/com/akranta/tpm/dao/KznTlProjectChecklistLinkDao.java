package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.KznTlProjectChecklistLink;

public interface KznTlProjectChecklistLinkDao {

	public void create(List<KznTlProjectChecklistLink> kznTlProjectChecklistLinkList) 	throws Exception ;
	public abstract KznTlProjectChecklistLink update(KznTlProjectChecklistLink kznTlProjectChecklistLink) throws Exception;
	

}

