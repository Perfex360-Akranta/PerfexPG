package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlSparesmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, partno, partname, factoryid, source, type, ischangepart
		, ismachinespecific, storeslocationref, criticalityid, classificationid
		, categoryid, subcategoryid, abcclass, supplierpartno, uomid
		, make, model, specification, shelflifeitem, shelflifemonths
		, leadtimeinternal, leadtimeexternal, maxinventorylevel, reorderlevel
		, reorderqty, prefsupplier1, prefsupplier2, prefsupplier3, equipmentgroup
		, standardrate, isdirectentry, drawingno, erpname, erpcode, shelflifeunit
		, tempfield2, tempfield3, active, createdby, createdon, modifiedon
	}

	public BAL_GenTlSparesmst()
	{
		saveArray = new  Object [ 42 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getSprmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSprmKeyid(String sprmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sprmKeyid;
	}

	public String getSprmPartno() {
		return (String) saveArray[ tableFldConstants.partno.ordinal() ];
	}

	public void setSprmPartno(String sprmpartno) {
		saveArray[ tableFldConstants.partno.ordinal() ] = sprmpartno;
	}

	public String getSprmPartname() {
		return (String) saveArray[ tableFldConstants.partname.ordinal() ];
	}

	public void setSprmPartname(String sprmpartname) {
		saveArray[ tableFldConstants.partname.ordinal() ] = sprmpartname;
	}

	public String getSprmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setSprmFactoryid(String sprmfactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = sprmfactoryid;
	}

	public String getSprmSource() {
		return (String) saveArray[ tableFldConstants.source.ordinal() ];
	}

	public void setSprmSource(String sprmsource) {
		saveArray[ tableFldConstants.source.ordinal() ] = sprmsource;
	}

	public String getSprmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setSprmType(String sprmtype) {
		saveArray[ tableFldConstants.type.ordinal() ] = sprmtype;
	}

	public String getSprmIschangepart() {
		return (String) saveArray[ tableFldConstants.ischangepart.ordinal() ];
	}

	public void setSprmIschangepart(String sprmischangepart) {
		saveArray[ tableFldConstants.ischangepart.ordinal() ] = sprmischangepart;
	}

	public String getSprmIsmachinespecific() {
		return (String) saveArray[ tableFldConstants.ismachinespecific.ordinal() ];
	}

	public void setSprmIsmachinespecific(String sprmismachinespecific) {
		saveArray[ tableFldConstants.ismachinespecific.ordinal() ] = sprmismachinespecific;
	}

	public String getSprmStoreslocationref() {
		return (String) saveArray[ tableFldConstants.storeslocationref.ordinal() ];
	}

	public void setSprmStoreslocationref(String sprmstoreslocationref) {
		saveArray[ tableFldConstants.storeslocationref.ordinal() ] = sprmstoreslocationref;
	}

	public String getSprmCriticalityid() {
		return (String) saveArray[ tableFldConstants.criticalityid.ordinal() ];
	}

	public void setSprmCriticalityid(String sprmcriticalityid) {
		saveArray[ tableFldConstants.criticalityid.ordinal() ] = sprmcriticalityid;
	}

	public String getSprmClassificationid() {
		return (String) saveArray[ tableFldConstants.classificationid.ordinal() ];
	}

	public void setSprmClassificationid(String sprmclassificationid) {
		saveArray[ tableFldConstants.classificationid.ordinal() ] = sprmclassificationid;
	}

	public String getSprmCategoryid() {
		return (String) saveArray[ tableFldConstants.categoryid.ordinal() ];
	}

	public void setSprmCategoryid(String sprmcategoryid) {
		saveArray[ tableFldConstants.categoryid.ordinal() ] = sprmcategoryid;
	}

	public String getSprmSubcategoryid() {
		return (String) saveArray[ tableFldConstants.subcategoryid.ordinal() ];
	}

	public void setSprmSubcategoryid(String sprmsubcategoryid) {
		saveArray[ tableFldConstants.subcategoryid.ordinal() ] = sprmsubcategoryid;
	}

	public String getSprmAbcclass() {
		return (String) saveArray[ tableFldConstants.abcclass.ordinal() ];
	}

	public void setSprmAbcclass(String sprmabcclass) {
		saveArray[ tableFldConstants.abcclass.ordinal() ] = sprmabcclass;
	}

	public String getSprmSupplierpartno() {
		return (String) saveArray[ tableFldConstants.supplierpartno.ordinal() ];
	}

	public void setSprmSupplierpartno(String sprmsupplierpartno) {
		saveArray[ tableFldConstants.supplierpartno.ordinal() ] = sprmsupplierpartno;
	}

	public String getSprmUomid() {
		return (String) saveArray[ tableFldConstants.uomid.ordinal() ];
	}

	public void setSprmUomid(String sprmuomid) {
		saveArray[ tableFldConstants.uomid.ordinal() ] = sprmuomid;
	}

	public String getSprmMake() {
		return (String) saveArray[ tableFldConstants.make.ordinal() ];
	}

	public void setSprmMake(String sprmmake) {
		saveArray[ tableFldConstants.make.ordinal() ] = sprmmake;
	}

	public String getSprmModel() {
		return (String) saveArray[ tableFldConstants.model.ordinal() ];
	}

	public void setSprmModel(String sprmmodel) {
		saveArray[ tableFldConstants.model.ordinal() ] = sprmmodel;
	}

	public String getSprmSpecification() {
		return (String) saveArray[ tableFldConstants.specification.ordinal() ];
	}

	public void setSprmSpecification(String sprmspecification) {
		saveArray[ tableFldConstants.specification.ordinal() ] = sprmspecification;
	}

	public String getSprmShelflifeitem() {
		return (String) saveArray[ tableFldConstants.shelflifeitem.ordinal() ];
	}

	public void setSprmShelflifeitem(String sprmshelflifeitem) {
		saveArray[ tableFldConstants.shelflifeitem.ordinal() ] = sprmshelflifeitem;
	}

	public String getSprmShelflifemonths() {
		return (String) saveArray[ tableFldConstants.shelflifemonths.ordinal() ];
	}

	public void setSprmShelflifemonths(String sprmshelflifemonths) {
		saveArray[ tableFldConstants.shelflifemonths.ordinal() ] = sprmshelflifemonths;
	}

	public String getSprmLeadtimeinternal() {
		return (String) saveArray[ tableFldConstants.leadtimeinternal.ordinal() ];
	}

	public void setSprmLeadtimeinternal(String sprmleadtimeinternal) {
		saveArray[ tableFldConstants.leadtimeinternal.ordinal() ] = sprmleadtimeinternal;
	}

	public String getSprmLeadtimeexternal() {
		return (String) saveArray[ tableFldConstants.leadtimeexternal.ordinal() ];
	}

	public void setSprmLeadtimeexternal(String sprmleadtimeexternal) {
		saveArray[ tableFldConstants.leadtimeexternal.ordinal() ] = sprmleadtimeexternal;
	}

	public String getSprmMaxinventorylevel() {
		return (String) saveArray[ tableFldConstants.maxinventorylevel.ordinal() ];
	}

	public void setSprmMaxinventorylevel(String sprmmaxinventorylevel) {
		saveArray[ tableFldConstants.maxinventorylevel.ordinal() ] = sprmmaxinventorylevel;
	}

	public String getSprmReorderlevel() {
		return (String) saveArray[ tableFldConstants.reorderlevel.ordinal() ];
	}

	public void setSprmReorderlevel(String sprmreorderlevel) {
		saveArray[ tableFldConstants.reorderlevel.ordinal() ] = sprmreorderlevel;
	}

	public String getSprmReorderqty() {
		return (String) saveArray[ tableFldConstants.reorderqty.ordinal() ];
	}

	public void setSprmReorderqty(String sprmreorderqty) {
		saveArray[ tableFldConstants.reorderqty.ordinal() ] = sprmreorderqty;
	}

	public String getSprmPrefsupplier1() {
		return (String) saveArray[ tableFldConstants.prefsupplier1.ordinal() ];
	}

	public void setSprmPrefsupplier1(String sprmprefsupplier1) {
		saveArray[ tableFldConstants.prefsupplier1.ordinal() ] = sprmprefsupplier1;
	}

	public String getSprmPrefsupplier2() {
		return (String) saveArray[ tableFldConstants.prefsupplier2.ordinal() ];
	}

	public void setSprmPrefsupplier2(String sprmprefsupplier2) {
		saveArray[ tableFldConstants.prefsupplier2.ordinal() ] = sprmprefsupplier2;
	}

	public String getSprmPrefsupplier3() {
		return (String) saveArray[ tableFldConstants.prefsupplier3.ordinal() ];
	}

	public void setSprmPrefsupplier3(String sprmprefsupplier3) {
		saveArray[ tableFldConstants.prefsupplier3.ordinal() ] = sprmprefsupplier3;
	}

	public String getSprmEquipmentgroup() {
		return (String) saveArray[ tableFldConstants.equipmentgroup.ordinal() ];
	}

	public void setSprmEquipmentgroup(String sprmequipmentgroup) {
		saveArray[ tableFldConstants.equipmentgroup.ordinal() ] = sprmequipmentgroup;
	}

	public String getSprmStandardrate() {
		return (String) saveArray[ tableFldConstants.standardrate.ordinal() ];
	}

	public void setSprmStandardrate(String sprmstandardrate) {
		saveArray[ tableFldConstants.standardrate.ordinal() ] = sprmstandardrate;
	}

	public String getSprmIsdirectentry() {
		return (String) saveArray[ tableFldConstants.isdirectentry.ordinal() ];
	}

	public void setSprmIsdirectentry(String sprmisdirectentry) {
		saveArray[ tableFldConstants.isdirectentry.ordinal() ] = sprmisdirectentry;
	}

	public String getSprmDrawingno() {
		return (String) saveArray[ tableFldConstants.drawingno.ordinal() ];
	}

	public void setSprmDrawingno(String sprmdrawingno) {
		saveArray[ tableFldConstants.drawingno.ordinal() ] = sprmdrawingno;
	}

	public String getSprmErpname() {
		return (String) saveArray[ tableFldConstants.erpname.ordinal() ];
	}

	public void setSprmErpname(String sprmerpname) {
		saveArray[ tableFldConstants.erpname.ordinal() ] = sprmerpname;
	}

	public String getSprmErpcode() {
		return (String) saveArray[ tableFldConstants.erpcode.ordinal() ];
	}

	public void setSprmErpcode(String sprmerpcode) {
		saveArray[ tableFldConstants.erpcode.ordinal() ] = sprmerpcode;
	}

	public String getSprmShelflifeunit() {
		return (String) saveArray[ tableFldConstants.shelflifeunit.ordinal() ];
	}

	public void setSprmShelflifeunit(String sprmshelflifeunit) {
		saveArray[ tableFldConstants.shelflifeunit.ordinal() ] = sprmshelflifeunit;
	}

	public String getSprmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSprmTempfield2(String sprmtempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = sprmtempfield2;
	}

	public String getSprmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSprmTempfield3(String sprmtempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sprmtempfield3;
	}

	public String getSprmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSprmActive(String sprmactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sprmactive;
	}

	public String getSprmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSprmCreatedby(String sprmcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sprmcreatedby;
	}

	public String getSprmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSprmCreatedon(String sprmcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sprmcreatedon;
	}

	public String getSprmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSprmModifiedon(String sprmmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sprmmodifiedon;
	}

}

