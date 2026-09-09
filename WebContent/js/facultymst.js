//fromMode
 var filterStr =jQuery('#hiddenUrl').val();
 filterStr = filterStr.substring(filterStr.indexOf('&genmstmenuid='),filterStr.length);
 filterStr =filterStr.substring(filterStr.indexOf('='),filterStr.length);
 filterStr = filterStr.substring(1);
 if(filterStr=="MNUCTQMST")
	{
		 //LoadForm("LoadMasterTbl","","CTQ_input.qams");
		 
		 //jQuery('#grdMMC').setGridWidth(300);
		 
	}
//''&genmstmenuid=MNUCTQMST
 
	var type = getFieldValue("combftymtype");
	jQuery('#combftymtype').attr("onchange","getTypeData();");
	enaDisEMployee(type);
	function frmMasterTblcmbftymempmkeyid_onSelect(record){
		jQuery('#txtftymname').val(record.text);
		 
	}
	function frmMasterTblcmbftymcombftymtype_onSelect(record){
		 alert( record.id);
	}
	
	function getTypeData(){
		var typeas = getFieldValue("combftymtype");
		enaDisEMployee(typeas);
	}
	function enaDisEMployee(type){ 
		if("I"==type)
		 enableFields("cmbftymempmkeyid");
		else if("E"==type){ 
			disableField("frmMasterTbl", "cmbftymempmkeyid");
	        clearField("cmbftymempmkeyid");
		}
	
}