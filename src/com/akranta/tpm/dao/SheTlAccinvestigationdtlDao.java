package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlAccinvestigationdtl;

public interface SheTlAccinvestigationdtlDao {

	public abstract SheTlAccinvestigationdtl create(SheTlAccinvestigationdtl sheTlAccinvestigationdtl) throws Exception;
	public abstract SheTlAccinvestigationdtl update(SheTlAccinvestigationdtl sheTlAccinvestigationdtl) throws Exception;
	public abstract List<SheTlAccinvestigationdtl> delete(List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl) throws Exception;

}

