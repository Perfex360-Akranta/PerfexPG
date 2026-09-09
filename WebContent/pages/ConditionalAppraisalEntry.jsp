<script><!--
jQuery(document).ready(function() {
    try {
        console.log("=== Entry Mode Page Loading Started ===");
        
        // CRITICAL: Role-based access check FIRST
        var roleid = jQuery("#hdnroleId").val();
        var roleName = jQuery("#hdnroleName").val();
        
        console.log("=== ROLE CHECK DEBUG ===");
        console.log("Role ID: '" + roleid + "'");
        console.log("Role Name: '" + roleName + "'");
        console.log("Role ID Length: " + (roleid ? roleid.length : 0));
        console.log("Role Name Length: " + (roleName ? roleName.length : 0));
        
        // Trim values to remove any whitespace
        if(roleid) roleid = roleid.trim();
        if(roleName) roleName = roleName.trim();
        
        // Check if user has PM PILLAR MEMBER role
        if(roleName !== "PM PILLAR MEMBER" || roleid !== "AROL0052"){
            console.log("Access DENIED - Role mismatch");
            console.log("Expected: 'PM PILLAR MEMBER' (AROL0052)");
            console.log("Got: '" + roleName + "' (" + roleid + ")");
            
            // Hide all form content
            jQuery('#frmConditionalAppEntry').hide();
            
            // Show error message
            if(typeof popupCommonErrorMsg === 'function') {
                popupCommonErrorMsg(" Only PM PILLAR MEMBER role can create Conditional Appraisal");
            } else {
                alert("Only PM PILLAR MEMBER role can create Conditional Appraisal");
            }
            
            // Redirect back after showing message
            setTimeout(function() {
                navigateToPrevForm();
            }, 3000);
            
            return false;
        }
        
        console.log("Access GRANTED - Initializing form");
        
        // If role check passes, continue with form initialization
        initialiseForm('frmConditionalAppEntry');
        jQuery('#submitForm').val('frmConditionalAppEntry');
        jQuery("#newComp").hide();
        
        disableField('frmConditionalAppEntry', 'cmbCdapStatus');
        formatDateBox('dteCdamDate', 'dd-MMM-yyyy');
        
        var newfrm = jQuery("#hdnnewFrm").val();
        var date = getFieldValue("dteCdamDate");
        if(!date || date.trim().length == 0){
            fillWithCurrentDate('dteCdamDate');
        }
        
        var compType = jQuery("#cmbCdapComponentType").val();
        if(!compType || compType.trim().length == 0){
            setFieldValue('cmbCdapComponentType',"E");
        }
        
        // Fill all combo boxes
        fillComboBox("frmConditionalAppEntry","cmbCdapTypeofcheck","checkType.commonFilter");
        fillComboBox("frmConditionalAppEntry","cmbCdapComponentid","spareCombo.commonFilter");
        fillComboBox("frmConditionalAppEntry","cmbCdapUom","uomCombo.commonFilter");
        fillComboBox("frmConditionalAppEntry","cmbCdapComponentType","component_combo.condapp","",false);
        fillComboBox("frmConditionalAppEntry","cmbCdapOknotok","actualOknotok_combo.condapp","",false);
        fillComboBox("frmConditionalAppEntry","cmbCdapCheckingtool","checkingtool_combo.condapp");
        fillComboBox("frmConditionalAppEntry","cmbCdapStatus","status_combo.condapp","",false);
        fillComboBox("frmConditionalAppEntry","cmbCdapIdealtype","idealType_combo.condapp","",false);
        fillComboBox("frmConditionalAppEntry","cmbCdapRefurbishmentStatus","refurbishment_combo.condapp","",false);
        fillComboBox("frmConditionalAppEntry","cmbCdapMachineid","machineCombo.commonFilter");
        
        var idealType = jQuery("#cmbCdapIdealtype").val();
        if(!idealType || idealType.trim().length == 0){
            setFieldValue('cmbCdapIdealtype',"M");
        }
        
        numericTextBox('txtCdapIdealminimum');
        numericTextBox('txtCdapIdealmaximum');
        numericTextBox('txtCdapActualvalue');
        existNew(getFieldValue("cmbCdapComponentType"));
        idealActual(getFieldValue("cmbCdapIdealtype"));
        statusColor();
        
        // Load functional location
        var factId = jQuery("#frmConditionalAppEntry input[id='factory']").val() || "";
        var sectionId = jQuery("#frmConditionalAppEntry input[id='section']").val() || "";
        var cellId = jQuery("#frmConditionalAppEntry input[id='cell']").val() || "";
        var machId = jQuery("#frmConditionalAppEntry input[id='machine']").val() || "";
        var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val() || "";
        
        var dataStr = "";
        if(flid && flid.trim().length > 0) {
            dataStr = "&flid=" + flid;
        } else if(machId && machId.trim().length > 0) {
            dataStr = "&factId=" + factId + "&sectionId=" + sectionId + "&cellId=" + cellId + "&machId=" + machId;
        } else {
            dataStr = "";
        }
        
        setTimeout(function() {
            loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalAppEntry", dataStr);
        }, 500);
        
        // Button click handlers
        jQuery("#btnInsert").click(function(){
            saveForm("frmConditionalAppEntry","ConditionalAppraisalEntryForm_save.condapp?clear=false");
        });
        
        jQuery("#btnDelete").click(function(){
            var detalkeyid = getFieldValue('hdnCdapKeyid');
            if(detalkeyid != '' && detalkeyid.length != 0) {
                deleteRecord("frmConditionalAppEntry","ConditionalAppraisalEntryDetail_delete.condapp?clear=false");
            } else {
                alert("No Detail record to delete");
            }
        });
        
        jQuery("#btnClear").click(function(){
            cleardtlFields();
        });
        
        enableBtn();
        
        jQuery("#btnActionplan").click(function(){
            var keyid = jQuery("#hdnCdapKeyid").val();
            var mstkeyid = jQuery("#hdnCdapCdamKeyid").val();
            var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val();
            openActionPlan("divActionplan",mstkeyid,"CAP",flid,"",keyid);
        });
        
        fileManagerPopUp("","CNA","frmConditionalAppEntry","btnfilemgr","ConFilemgr");
        
        setFieldValue('cmbCdapRefurbishmentStatus',"N");
        setFieldValue('txtCdapActionrequired',"");
        
        jQuery("#cmbCdapMachineid").combobox({
            onRequest:function(){
                var cellid = jQuery('#cmbCellid').val();
                if(cellid && cellid != '' && cellid != ' ') {
                    return "cellId=" + cellid;
                }
            }
        });
        
        jQuery("#cmbCdapComponentid").combobox({
            onRequest:function(){
                var machId = jQuery("#frmConditionalAppEntry input[id='machine']").val();
                if(machId && machId != '' && machId != ' ') {
                    return "cmbAssmbid=" + machId;
                }
            }
        });
        
        setUpdateValues();
        
        console.log("Entry Mode page loading completed successfully");
        
    } catch(e) {
        console.error("Error in document.ready (Entry Mode):", e);
        alert("Error initializing form: " + e.message);
    }
});



/* jQuery("#btnInsert").click(function(){
    alert("Button clicked!"); // This should show if click is working
    console.log("About to call saveForm");
    saveForm("frmConditionalAppEntry","ConditionalAppraisalEntryForm_save.condapp?clear=false");
}); */

function setUpdateValues() {
	var updtDate  = jQuery('#hdnCdamUpdtDate').val();
	var date =getFieldValue("dteCdamDate");
	if ((updtDate!=null && updtDate!='' && updtDate!=' ') && updtDate!=date) {
		setFieldValue('dteCdamDate',updtDate);  
		dteCdamDate_onSelect(updtDate);
	}
	
}

function  frmConditionalAppEntrycmbCdapComponentid_onSelect(record)
{    
	var machine = jQuery("#frmConditionalAppEntry input[id='machine']").val();
	//alert(machine);
	if(machine == '' || machine == ' ' || machine == undefined)
	{   
		setFieldValue('cmbCdapComponentid','');
		alert('Select Equipment');
	}
	

}

/* function  frmConditionalAppEntrycmbCdapMachineid_onSelect(record)
{    
	var dataStr = "&machId="+record.id ;
	
	var machid = record.id ;
	 //alert(machid);
	 if (machid.length != 0 )
	{
		loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalAppEntry",dataStr);
	}
} */

function frmConditionalAppEntrycmbCdapMachineid_onSelect(record) {    
    var dataStr = "&machId=" + record.id;
    var machid = record.id;
    
    if (machid && machid.length != 0) {
        alert("Machine selected: " + machid + " - Loading functional location");
        loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalAppEntry", dataStr);
    }
}

function  frmConditionalAppEntrycmbCdapRefurbishmentStatus_onSelect(record)
{
	var refurismnt=record.id;
	
	if(refurismnt=="Y")
	{
		jQuery('#refursh').addClass("mandatory-lbl");
		
	}else{
		jQuery('#refursh').removeClass("mandatory-lbl");
		
		setFieldValue('txtCdapActionrequired',"");
	}
		
}

/* function frmConditionalAppEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
{
   if(keyIds.machId != undefined && keyIds.machId.trim() != "" && keyIds.machId.trim() !=null)
	{	
		reloadCombo("frmConditionalAppEntry","cmbCdapComponentid","spareCombo.commonFilter?cmbAssmbid="+keyIds.machId);
	}
   
   reloadCombo("frmConditionalAppEntry","cmbCdapMachineid","machineCombo.commonFilter?cellId="+keyIds.cellId);
   
   var mstkeyid=jQuery('#hdnCdapCdamKeyid').val();
   //alert(mstkeyid);
  /* if(!keyid.trim().length > 0)
	 {   alert(123);
	   	setFieldValue("cmbCdapMachineid" , keyIds.machId);
	 }
     */
     /* setFieldValue("cmbCdapMachineid" , keyIds.machId);
   
   setFunctionalLocWidth('frmConditionalAppEntry','650px');
   idealActual(getFieldValue("cmbCdapIdealtype"));
   
   var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val();
   
   if(flid != undefined && flid.trim() != "" && flid.trim() != null)
	{
	    var fflid ="&flid="+flid;
	   	viewgrid(fflid);
	}

   
} */

function frmConditionalAppEntry_FuntLocHierarchy_SuccessCallBack(keyIds) {
    console.log("FuntLocHierarchy_SuccessCallBack called", keyIds);
    alert("Functional location loaded successfully!");
    
    // Reload component combobox if machId is available
    if(keyIds.machId != undefined && keyIds.machId.trim() != "" && keyIds.machId.trim() != null) {	
        reloadCombo("frmConditionalAppEntry","cmbCdapComponentid","spareCombo.commonFilter?cmbAssmbid=" + keyIds.machId);
        // Set the machine value
        setFieldValue("cmbCdapMachineid", keyIds.machId);
    }
    
    // Reload machine combobox if cellId is available
    if(keyIds.cellId != undefined && keyIds.cellId.trim() != "" && keyIds.cellId.trim() != null) {
        reloadCombo("frmConditionalAppEntry","cmbCdapMachineid","machineCombo.commonFilter?cellId=" + keyIds.cellId);
    }
    
    setFunctionalLocWidth('frmConditionalAppEntry','650px');
    idealActual(getFieldValue("cmbCdapIdealtype"));
    
    // Load grid data if flid is available
    var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val();
    
    console.log("Loading grid with flid: " + flid);
    
    if(flid != undefined && flid.trim() != "" && flid.trim() != null) {
        var fflid = "&flid=" + flid;
        alert("Loading grid data with flid: " + flid);
        viewgrid(fflid);
    }
}


function btnfilemgr_click()
{
  	var keyid = jQuery('#hdnCdapCdamKeyid').val();
  	
  	if(keyid.trim().length<=0){
	    saveForm('frmConditionalAppEntry','ConditionalAppraisalEntryForm_save.condapp?filemanger=filemanger');
  	}else if(keyid != null && keyid != ''){
			fileManagerPopUp(keyid,"CNA","","","");
	    }		
}

function frmConditionalAppEntry_beforeSubmit(){
	console.log("beforeSubmit called");
	var mechid = jQuery("#frmConditionalAppEntry input[id='machine']").val();
	console.log("mechid:", mechid);
	if (mechid =='' || mechid ==' ') {
		popupCommonErrorMsg(" Select Equipment " );
		return false;
	}
	var date =getFieldValue("dteCdamDate");
	 console.log("date:", date);
	if (date =='' || date ==' ') {
		popupCommonErrorMsg(" Select Date " );
		return false;
	}
	
	var min = getFieldValue('txtCdapIdealminimum');
	var max = getFieldValue('txtCdapIdealmaximum');
	var actualval = getFieldValue('txtCdapActualvalue');
	
	
	
	var idealtype = getFieldValue('cmbCdapIdealtype');
	var refstatus = getFieldValue('cmbCdapRefurbishmentStatus');
	
	var comptype = getFieldValue('cmbCdapComponentType');
	var compttxt = getFieldValue('txtCdapNewcomponent');
	//alert(compttxt);
	
	if ( comptype == 'N')
	{   if(compttxt != '' && compttxt.length != 0)
		{   //alert(compttxt);
			processAjaxCalls("newcomponent_add.condapp?&compname="+compttxt+"&mechid="+mechid,"","","");
		}
	}
	//alert(idealtype);
	//alert(refstatus);
	/*if(refstatus == 'Y')
	{  
		var reftext = getFieldValue('txtCdapActionrequired');
		//alert(reftext.length);
		if(reftext.length == 0)
		{
			popupCommonErrorMsg(" Enter Action Required " );
			return false;
		}
		
	}*/
	
	if ( idealtype == 'M')
	{
		if(min.length == 0 || max.length == 0 )
		{
			popupCommonErrorMsg(" Enter Min and Max Value" );
			return false;
	
		}
		if(actualval.length == 0 )
		{
			popupCommonErrorMsg(" Enter Actual Condition" );
			return false;
		}
		if(parseFloat(min,10) > parseFloat(max,10) )
		{
			//alert('Minimum value shoud be lesser then Maximum');
			popupCommonErrorMsg(" Minimum value shoud be lesser then Maximum Value " );
			//showValidationErrorMsg('txtCdapIdealminimum','Minimum value shoud be lesser then Maximum Value');
			return false;
		}
	}
	
	

	
	
}
function dteCdamDate_onChange(date) {
	
	if (date!=null && date!='')
		jQuery('#hdnOldDate').val(date);
	
  	fnDateSelect(date);
}

function dteCdamDate_onSelect(date)
{
	if (date!=null && date!='')
		jQuery('#hdnOldDate').val(date);
	
  	fnDateSelect(date);
}

function fnDateSelect(date) {

  	var fromdate = jQuery('#dteCdamDate').datebox('getValue');
 	var fromDate = convertStringToDate(fromdate);
	var currentDate = getServerDateTime();	
	var todateValidation=jQuery("#hdnEscFutureDateVal").val();
	if(  todateValidation != "true"  &&   date > currentDate)
	{					
		jQuery('#dteCdamDate').datebox('clear');
		popupCommonErrorMsg(" Should Not Exceed Current Date " );
		//showValidationErrorMsg('dteCdamDate','Should Not Exceed Current Date');	
	}	
	else
	{
		clearValidationErrorMsg('dteCdamDate');	
		var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val();
		var fflid ="&flid="+flid;

		var oldDate = jQuery('#hdnOldDate').val();
		if (oldDate!=date) {
	    	viewgrid(fflid);
	    	//alert(1);
		}
	}
}

function cleardtlFields(){
	setFieldValue('hdnCdapKeyid',"");
	setFieldValue('cmbCdapComponentType',"E");
	existNew("E");
	setFieldValue('cmbCdapComponentid',"");
	setFieldValue('txtCdapNewcomponent',"");
	setFieldValue('txtCdapDimension',"");
	setFieldValue('txtCdapCheckingtool',"");
	setFieldValue('cmbCdapTypeofcheck',"");
	setFieldValue('cmbCdapIdealtype',"");
	idealActual("M");
	setFieldValue('txtCdapIdealminimum',"");
	setFieldValue('txtCdapIdealmaximum',"");
	setFieldValue('cmbCdapUom',"");
	setFieldValue('txtCdapIdealcondition',"");
	setFieldValue('txtCdapActualcondition',"");
	setFieldValue('txtCdapActualvalue',"");
	setStatus();
	statusColor();
	setFieldValue('cmbCdapOknotok',"");
	setFieldValue('cmbCdapStatus',"");
	setFieldValue('txtCdapActionrequired',"");
	setFieldValue('cmbCdapRefurbishmentStatus',"N");
    setFieldValue('txtCdapActionrequired',"");
	setFieldValue('hdnCdapCreatedon',"");
	enableBtn();
    
}
function frmConditionalAppEntry_beforeDelete(){
	
			return false;
	
}
function viewgrid(filterString){
	var keyId=getFieldValue("hdnCdapCdamKeyid");
	var date =getFieldValue("dteCdamDate");
	if(keyId.length>0){
		filterString+="&keyId="+keyId;
	}
	if(date.length>0){
		filterString+="&date="+date;
	}

	processGridnew("ConditionalAppraisalEntryForm_input.condapp",filterString,"conditionalAppGrid", "conditionalAppPager","","doubleClickCondApp","","ongridcomplete");
	
}

/* function doubleClickCondApp(id){
	var rowData = jQuery("#conditionalAppGrid").jqGrid("getRowData",id);
	var chktolid = rowData.CHECKINGTOOLID ;
	setFieldValue('cmbCdapCheckingtool',chktolid);

	
	var cdapid = rowData.KEYID;
	var cdapCdapid = rowData.CDAP_KEYID;

	if(cdapCdapid==cdapid) {
		setFieldValue('hdnCdapCdapkeyid', cdapid);
	}
	else {
		setFieldValue('hdnCdapCdapkeyid', cdapCdapid);
		setFieldValue('hdnCdapKeyid', cdapid);
	}
	
//alert(rowData.KEYID);
	var mskeyid=rowData.CDAM_KEYID;
	
	setFieldValue('hdnCdapCdamKeyid' , mskeyid);
	setFieldValue('hdnCdamKeyid' , mskeyid);
	//alert('22'+mskeyid);
	var rowstatus = rowData.ROWSTATUS;
	//alert(rowstatus + " " + rowData.KEYID);
	if (rowstatus == "O")
		{
			processAjaxCalls("ConditionalAppraisalEntryForm_recall.condapp?q=2&KEYID="+rowData.KEYID,"","recallsuccessCallBack","errorCallBack");
			
		}
	else
		{
			processAjaxCalls("ConditionalAppraisalForm_recall.condapp?q=2&KEYID="+rowData.KEYID,"","recallsuccessCallBack","errorCallBack");
		}
	
} */
function doubleClickCondApp(id){
    var rowData = jQuery("#conditionalAppGrid").jqGrid("getRowData",id);
    
    // Use lowercase 'keyid' instead of uppercase 'KEYID'
    var chktolid = rowData.checkingtoolid; // Also lowercase
    setFieldValue('cmbCdapCheckingtool',chktolid);
    
    var cdapid = rowData.keyid; // Changed from rowData.KEYID
    var cdapCdapid = rowData.cdap_keyid; // Changed from rowData.CDAP_KEYID

    if(cdapCdapid == cdapid) {
        setFieldValue('hdnCdapCdapkeyid', cdapid);
    }
    else {
        setFieldValue('hdnCdapCdapkeyid', cdapCdapid);
        setFieldValue('hdnCdapKeyid', cdapid);
    }
    
    var mskeyid = rowData.cdam_keyid; // Changed from rowData.CDAM_KEYID
    
    setFieldValue('hdnCdapCdamKeyid', mskeyid);
    setFieldValue('hdnCdamKeyid', mskeyid);
    
    var rowstatus = rowData.rowstatus; // Changed from rowData.ROWSTATUS
    
    if (rowstatus == "O") {
        processAjaxCalls("ConditionalAppraisalEntryForm_recall.condapp?q=2&KEYID=" + cdapid, "", "recallsuccessCallBack", "errorCallBack");
    }
    else {
        processAjaxCalls("ConditionalAppraisalForm_recall.condapp?q=2&KEYID=" + cdapid, "", "recallsuccessCallBack", "errorCallBack");
    }
}
/* function ongridcomplete(){
	var row=jQuery("#conditionalAppGrid").jqGrid('getDataIDs');
	 for(var i=0;i<row.length;i++){
		 var rowid=row[i];
		 var status=jQuery("#conditionalAppGrid").jqGrid('getCell', rowid,"STATUS");
		 var mskeyid=jQuery("#conditionalAppGrid").jqGrid('getCell', rowid,"CDAM_KEYID");
		 //alert(mskeyid);
		 setFieldValue('hdnCdapCdamKeyid' , mskeyid);
		 if(status.contains("R")){
			 jQuery("#conditionalAppGrid").jqGrid('setCell',rowid,"STATUSSHW",'',{'background-color':'red'});
		 }
		 else if(status.contains("G")){
			 jQuery("#conditionalAppGrid").jqGrid('setCell',rowid,"STATUSSHW",'',{'background-color':'green'});
			 disableField("frmConditionalAppEntry", "btnActionplan_"+(i+1));
		 } 
		 else{ 
			 jQuery("#conditionalAppGrid").jqGrid('setCell',rowid,"STATUSSHW",'',{'background-color':'white'});
		 }
	 }
} */

/* function ongridcomplete(){
    var row = jQuery("#conditionalAppGrid").jqGrid('getDataIDs');
    for(var i = 0; i < row.length; i++){
        var rowid = row[i];
        // Use lowercase field names
        var status = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "cdap_status");
        var mskeyid = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "cdap_cdam_keyid");
        
        setFieldValue('hdnCdapCdamKeyid', mskeyid);
        
        if(status && status.indexOf('R') > -1){
            jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'red'});
        }
        else if(status && status.indexOf('G') > -1){
            jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'green'});
            disableField("frmConditionalAppEntry", "btnActionplan_" + (i+1));
        } 
        else{ 
            jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'white'});
        }
    }
} */

function ongridcomplete(){
    var row = jQuery("#conditionalAppGrid").jqGrid('getDataIDs');
    if(row.length > 0) {
        // Set master keyid from first row if not already set
        var firstRowId = row[0];
        var mskeyid = jQuery("#conditionalAppGrid").jqGrid('getCell', firstRowId, "cdap_cdam_keyid");
        if(mskeyid && mskeyid.trim().length > 0) {
            setFieldValue('hdnCdapCdamKeyid', mskeyid);
            console.log("Set master keyid in ongridcomplete:", mskeyid);
        }
    }
    
    for(var i = 0; i < row.length; i++){
        var rowid = row[i];
        var status = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "cdap_status");
        
        if(status && status.indexOf('R') > -1){
            jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'red'});
        }
        else if(status && status.indexOf('G') > -1){
            jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'green'});
            disableField("frmConditionalAppEntry", "btnActionplan_" + (i+1));
        } 
        else{ 
            jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'white'});
        }
    }
}
function recallsuccessCallBack(result){
	//alert("entered");
	//alert(result[0][0]);
	setFieldValue('hdnCdapKeyid',result[0][0]);
	setFieldValue('cmbCdapComponentType',result[0][1]);
	existNew(result[0][1]);
	setFieldValue('cmbCdapComponentid',result[0][2]);
	setFieldValue('txtCdapNewcomponent',result[0][3]);
	setFieldValue('txtCdapDimension',result[0][4]);
	setFieldValue('txtCdapCheckingtool',result[0][5]);
	setFieldValue('cmbCdapTypeofcheck',result[0][6]);
	setFieldValue('cmbCdapIdealtype',result[0][7]);
	idealActual(result[0][7]);
	setFieldValue('txtCdapIdealminimum',result[0][8]);
	setFieldValue('txtCdapIdealmaximum',result[0][9]);
	setFieldValue('cmbCdapUom',result[0][10]);
	setFieldValue('txtCdapIdealcondition',result[0][11]);
	setFieldValue('txtCdapActualcondition',result[0][12]);
	setFieldValue('txtCdapActualvalue',result[0][13]);
	setFieldValue('cmbCdapOknotok',result[0][14]);
	setFieldValue('cmbCdapStatus',result[0][15]);
	setStatus();
	statusColor();
	setFieldValue('txtCdapActionrequired',result[0][16]);
	setFieldValue('cmbCdapRefurbishmentStatus',result[0][17]);
	setFieldValue('hdnCdapCreatedon',result[0][18]);
	enableBtn();
    	
}
function enableBtn(){
	var keyid =jQuery("#hdnCdapKeyid").val();
    if(keyid.length>0){
    	jQuery("#actionPlan").show();
    	jQuery("#dtlDelete").show();
    }
    else{
    	jQuery("#actionPlan").hide();
    	jQuery("#dtlDelete").hide();
    }
}
function frmConditionalAppEntry_deleteSuccessCallback(result)
{
	alert(result.successData.msg);	
	//alert(result.keyId);
	if((result.keyId).length>0){
		setDefaultVal(result);
	}else
		navigateToPrevForm();
}
function setDefaultVal(result){
	var date="";
	var flid="";
	var keyId="";
	if((result.date).length>0)
		date=result.date;
	if((result.flid).length>0)
		flid=result.flid;
	if((result.keyId).length>0)
		keyId=result.keyId;
	clearForm("frmConditionalAppEntry");
	//setFieldValue('dteCdamDate',date);
	setFieldValue('cmbCdamFlid',flid);
	setFieldValue('hdnCdapCdamKeyid',keyId);
	setFieldValue('cmbCdapComponentType',"E");
	jQuery("#newComp").hide();
	jQuery("#existComp").show();
	setFieldValue('dteCdamDate',date);
	setFieldValue('cmbCdapIdealtype',"M");
	idealActual(getFieldValue("cmbCdapIdealtype"));
	statusColor();

	loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalAppEntry","&flid="+flid);
		 
	//jQuery("#conditionalAppGrid").trigger("reloadGrid");
	var filterString="?q=2";
	if(keyId.length>0){
		filterString+="&keyId="+keyId;
	}
	//var url="ConditionalAppraisalEntryForm_getData.condapp"+filterString;
	//jQuery("#conditionalAppGrid").setGridParam({url:url}).trigger('reloadGrid');
	
	enableBtn();
	
}
function frmConditionalAppEntry_successsCallback(result)
{
	 setDefaultVal(result);
	 //alert(Object.keys(result));
	 var filemanger =result.filemanger;
	 var keyid=result.keyId;
	 
	 if(filemanger==true){
     	if(keyid.trim().length>0){
	   			 
	   			 fileManagerPopUp(keyid,"CNA","","","");
			 }
		}

    setFieldValue('txtCdapActionrequired',"");
	setFieldValue('hdnCdapCreatedon',"");

	
}
function  frmConditionalAppEntrycmbCdapComponentType_onSelect(record){
	existNew(record.id);
}

function  frmConditionalAppEntrycmbCdamEquipmentid_onSelect(record){
	loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalAppEntry","&machId="+record.id);
}

function existNew(data){
	if(data == 'N'){
		setFieldValue('cmbCdapComponentid',"");
		jQuery("#existComp").hide();
		jQuery("#newComp").show();
	}else{
		setFieldValue('txtCdapNewcomponent',"");
		jQuery("#newComp").hide();
		jQuery("#existComp").show();
	}
}
function  frmConditionalAppEntrycmbCdapIdealtype_onSelect(record){
	idealActual(record.id);
}
function idealActual(data){
	clearIdealRelated();
	//alert(data);
	if(data == 'M'){
		jQuery("#idealTxt").hide();		
		jQuery("#actualOK").hide();
		//jQuery("#cmbCdapOknotok").hide();
		
		//jQuery("#actualTxt").hide();
		jQuery("#idealMinmax").show();
		//jQuery("#actualVal").show();
		jQuery('#txtCdapActualvalue').show();
		
		jQuery("#status").show();
		//jQuery("#lblUom").show();
		//jQuery("#uomControl").show();
		jQuery('#minlbl').addClass('mandatory-lbl');
		jQuery('#maxlbl').addClass('mandatory-lbl');
		//jQuery('#uomlbl').addClass('mandatory-lbl');
		//alert(1);
		//enableFields('cmbCdapUom');
		
		//enableFields('txtCdapIdealminimum');
		//enableFields('txtCdapIdealmaximum');
		
		jQuery('#txtCdapActualcondition').hide();
		disableField('frmConditionalAppEntry', 'cmbCdapStatus');		
		//disableField('frmConditionalAppEntry', 'cmbCdapUom');		
		//disableField('frmConditionalAppEntry', 'txtCdapIdealcondition');
	}else if(data == 'O'){
		jQuery("#idealTxt").hide();
		jQuery("#idealMinmax").show();
		//jQuery("#actualTxt").hide();
		jQuery('#txtCdapActualcondition').hide();
		//jQuery("#actualVal").hide();
		jQuery('#txtCdapActualvalue').hide();
		//jQuery("#lblUom").hide();
		//jQuery("#uomControl").hide();
		jQuery("#actualOK").show();
		//jQuery("#cmbCdapOknotok").show();
		jQuery("#status").show();
		//alert(2);
		disableField('frmConditionalAppEntry', 'cmbCdapStatus');
		disableField('frmConditionalAppEntry', 'cmbCdapUom');
		disableField('frmConditionalAppEntry', 'txtCdapIdealminimum');
		disableField('frmConditionalAppEntry', 'txtCdapIdealmaximum');	
		enableFields('cmbCdapStatus');
		jQuery('#minlbl').removeClass('mandatory-lbl');
		jQuery('#maxlbl').removeClass('mandatory-lbl');
		
		//jQuery("#cmbCdapUom").attr('disabled','disabled');
		//disableField('frmConditionalAppEntry', 'txtCdapIdealcondition');
	}else if(data == 'T'){
		jQuery("#idealMinmax").hide();
		jQuery("#actualOK").hide();
		//jQuery("#cmbCdapOknotok").hide();
		//jQuery("#actualVal").hide();
		jQuery('#txtCdapActualvalue').hide();
		jQuery("#status").hide();
		//jQuery("#lblUom").hide();
		//jQuery("#uomControl").hide();
		//jQuery("#actualTxt").show();
		jQuery('#txtCdapActualcondition').show();
		jQuery("#idealTxt").show();
		//alert(3);
		enableFields('cmbCdapStatus');
		disableField('frmConditionalAppEntry', 'cmbCdapUom');
		//enableFields('frmConditionalAppEntry', 'txtCdapIdealcondition');
		//jQuery("#cmbCdapUom").attr('disabled','disabled');
		
	}
}
function clearIdealRelated(){
	setFieldValue('txtCdapIdealminimum',"");
	setFieldValue('txtCdapIdealmaximum',"");
	setFieldValue('txtCdapActualvalue',"");
	setFieldValue('cmbCdapUom',"");
	setFieldValue('txtCdapIdealcondition',"");
	setFieldValue('txtCdapActualcondition',"");
	setFieldValue('cmbCdapOknotok',"");
	setFieldValue('cmbCdapStatus',"");
}
function  frmConditionalAppEntrycmbCdapOknotok_onSelect(record){
	setStatus();
}
function  frmConditionalAppEntrycmbCdapStatus_onSelect(record){
	statusColor();
}
function setStatus(){
	var idealType=getFieldValue('cmbCdapIdealtype');
	if(idealType=='M'){
		var min=getFieldValue('txtCdapIdealminimum');
		var max=getFieldValue('txtCdapIdealmaximum');
		var actVal=getFieldValue('txtCdapActualvalue');
		if(parseFloat(actVal,10)>=parseFloat(min,10) &&parseFloat(actVal,10)<=parseFloat(max,10) ){
			 setFieldValue('cmbCdapStatus','G');
			 statusColor();
		}else {
			 setFieldValue('cmbCdapStatus','R');
			 statusColor();
		}
		disableField('frmConditionalAppEntry', 'cmbCdapStatus');
	}else if(idealType=='O'){
		var oknotok=getFieldValue('cmbCdapOknotok');
		if(oknotok=='OK'){
			setFieldValue('cmbCdapStatus','G');
			statusColor();
		}
		else if(oknotok=='NK'){
			setFieldValue('cmbCdapStatus','R');
			statusColor();
		}
		disableField('frmConditionalAppEntry', 'cmbCdapStatus');
	}else if(idealType=='T'){
		enableFields('cmbCdapStatus');
	}
}
function statusColor(){
	var val=getFieldValue('cmbCdapStatus');
	/* if(val.contains('G')) */
	if(val && val.indexOf('G') > -1)
		jQuery("#StatusDiv").css("background","green");
	else 
		jQuery("#StatusDiv").css("background","red");
}
function btnActPlan(id, options, rowObject)
{	
	var rowId = options.rowId;
	var colId = options.pos;	
	//if(rowObject[10].contains("R")){
	return '<input type="button" id="btnActionplan_'+rowId+'"  name="btnActionplanGrid_'+rowId+'" onclick="condApp('+rowId+')"  style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
	//}
}
/* function condApp(rowid){
	
	var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val(); 
	var refDocId = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid,"KEYID");
	var keyid = jQuery("#hdnCdapCdamKeyid").val();

	var rowData = jQuery("#conditionalAppGrid").jqGrid("getRowData",rowid);
	var cdapid = rowData.KEYID;
	var cdapCdapid = rowData.CDAP_KEYID;
	if(cdapCdapid==cdapid) { 
		alert(" Please Save Record First." );
		return false;
	}
	
	var mainTask =jQuery("#conditionalAppGrid").jqGrid('getCell', rowid,"ACTIONREQUIRED");
	if(refDocId.trim().length>0)
		openActionPlan("divCdapActionplan",keyid,"CDA",flid,mainTask,refDocId);
} */

function condApp(rowid){
    console.log("condApp called with rowid:", rowid);
    
    var flid = jQuery("#frmConditionalAppEntry input[id='flid']").val();
    console.log("flid:", flid);
    
    // Get row data first
    var rowData = jQuery("#conditionalAppGrid").jqGrid("getRowData", rowid);
    console.log("Full rowData:", rowData);
    
    // Get the detail record ID
    var refDocId = rowData.cdap_keyid || jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "cdap_keyid");
    console.log("refDocId:", refDocId);
    
    // Get master key - try multiple sources
    var keyid = jQuery("#hdnCdapCdamKeyid").val();
    if(!keyid || keyid == 'false' || keyid.trim().length == 0) {
        // Try to get from grid
        keyid = rowData.cdap_cdam_keyid || rowData.cdam_keyid;
    }
    console.log("keyid (master):", keyid);
    
    var cdapid = rowData.cdap_keyid;
    console.log("cdapid (detail):", cdapid);
    
    // Check if this is an unsaved record
    if(!cdapid || cdapid.trim().length == 0) {
        alert("Please Save Record First.");
        return false;
    }

    // Get mainTask - try different possible field names
    var mainTask = rowData.cdap_actionrequired || 
                   rowData.actionrequired || 
                   jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "cdap_actionrequired") ||
                   jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "ACTIONREQUIRED") ||
                   "";
    
    console.log("mainTask:", mainTask);
    
    if(refDocId && refDocId.trim().length > 0 && keyid && keyid.trim().length > 0) {
        console.log("Opening action plan with params:", {
            div: "divCdapActionplan",
            keyid: keyid,
            type: "CDA",
            flid: flid,
            mainTask: mainTask,
            refDocId: refDocId
        });
        openActionPlan("divCdapActionplan", keyid, "CDA", flid, mainTask, refDocId);
    } else {
        console.log("Missing required parameters");
        if(!keyid || keyid.trim().length == 0) {
            alert("Master record ID not found. Please select the record from the grid first.");
        } else {
            alert("No valid record ID found");
        }
    }
}
function divCdapActionplan_onClose(result){
	//jQuery("#conditionalAppGrid").trigger("reloadGrid");
	return true;
}
</script>
<form id="frmConditionalAppEntry" name="frmConditionalAppEntry">
	<div id="" style="width:100%;padding: 10px;">
		<div>
			<table>
				<tr>
					<td colspan="2">
						<div id="frmConditionalAppEntry">
							<input type="hidden" id="section" name=cmbSectionid value=""></input> 
							<input type="hidden" id="cell"    name="cmbCellid" value=""></input> 
							<input type="hidden" id="machine" name="cmbMachineid" value=""></input>
							<input type="hidden" id="flid"    name="cmbCdamFlid" value="${requestScope.PlmTlConditionalappraisalmstentry.cdamFlid}"></input>  
						</div>
						<div  class="easyui-paddingbfpx" id="CondtnlNewfunLocation"  ></div>
					</td>
					<td valign="top">
						<div  style="margin-left:19px;">
							<div>
						     	<label class="mandatory-lbl">Date</label>
						     </div>
						    
						     <div>
						     <span style="position:absolute; width: 100px " >
						     	<input id="dteCdamDate" name="dteCdamDate" class="easyui-datebox" style="width:100px;" value="${requestScope.plmTlConappraisalmstentry.cdamDate}"/>
						     </span>
						     	<span  id="ConFilemgr" style="position:absolute;margin-left:150px;" > </span>
						     </div>
					     </div>
					     <div>
					     <span style="width: 160px ">
							<label id="err_txtCdamDate" class="tpm-errormsg"></label>
						</span>
					     </div>
					</td>
				</tr>
			</table>
			<table style="padding-top: 0px;">
				<tr>
					<td>
					<div style ="width: 300px;">
					    <div>
							<div class="easyui-paddingbfpx">
						     	<label class="mandatory-lbl" >Equipment</label>
						     </div>
						     <div>
						     	<input id="cmbCdapMachineid" name="cmbCdapMachineid"  class="easyui-combobox"  style="width:260px;"  value="${requestScope.plmTlConappraisalentry.cdamEquipmentid}"  />
						     </div>
					     </div>
						<div style ="padding-top:0px">
							<div class="easyui-paddingbfpx">
						     	<label class="mandatory-lbl">Component Type</label>
						     </div>
						     <div>
						     	<input id="cmbCdapComponentType" readonly="readonly" name="cmbCdapComponentType" class="easyui-combobox" style="width:260px;" value="${requestScope.plmTlConappraisalentry.cdapComponentType}"/>
						     </div>
					     </div>
					     
					     <div style ="padding-top:0px">
							<div class="easyui-paddingbfpx">
						     	<label class="mandatory-lbl">Component</label>
						     </div>
						     <div>
						     	<span id="existComp">
						     		<input id="cmbCdapComponentid" readonly="readonly" name="cmbCdapComponentid" class="easyui-combobox" style="width:260px;"  value="${requestScope.plmTlConappraisalentry.cdapComponentid}"/>
						     	</span>
						     	<span id="newComp">
									<input id="txtCdapNewcomponent" readonly="readonly" name="txtCdapNewcomponent" class="easyui-text" maxlength="90" style="width:260px;" value="${requestScope.plmTlConappraisalentry.cdapNewcomponent}">
								</span>
						     </div>
					     </div>
					     
					     <div style ="padding-top:0px">
							<div>
								<div class="easyui-paddingbfpx">
							     	<label class="mandatory-lbl" >Dimension</label>
							     </div>
							     <div>
							    	<input id="txtCdapDimension" readonly="readonly" name="txtCdapDimension" class="easyui-text" maxlength="90"  style="width:260px;" value="${requestScope.plmTlConappraisalentry.cdapDimension}"/>
								</div>
							</div>
							<div  class="easyui-paddingbfpx" style ="padding-top:0px">
								<div class="easyui-paddingbfpx">
							     	<label class="mandatory-lbl">Checking Tool</label>
							     </div>
							     <div>
							    	<!--  <input id="txtCdapCheckingtool" name="txtCdapCheckingtool" class="easyui-text" maxlength="90" style="width:260px;" value="${requestScope.plmTlConappraisalentry.cdapCheckingtool}"/>
							    	-->
							    	<input id="cmbCdapCheckingtool" readonly="readonly" name="cmbCdapCheckingtool" class="easyui-combobox" style="width:260px;"  value="${requestScope.plmTlConappraisalentry.cdapCheckingtool}"/>
								</div>
							</div>
						</div>
					     </div>
					</td>
					<td valign="top">
					<div style ="width: 400px;">
						<div style="margin-left:10px ;">
							<div class="easyui-paddingbfpx" >
						     	<label class="mandatory-lbl">Type of Check</label>
						     </div>
						     <div>
						    	<input id="cmbCdapTypeofcheck" readonly="readonly" name="cmbCdapTypeofcheck" class="easyui-combobox" style="width:310px;"  value="${requestScope.plmTlConappraisalentry.cdapTypeofcheck}"/>
							</div>
						</div>
						<div style="margin-left:10px ; padding-top:0px">
							<div>
								<div class="easyui-paddingbfpx">
									<span><label class="mandatory-lbl">Ideal Condition</label></span>
									<span id="lblUom"  style="margin-left: 60px;">
									<label id="uomlbl">UoM</label></span>
								</div>
							    <div  >
							    	<span><input id="cmbCdapIdealtype" readonly="readonly" name="cmbCdapIdealtype" class="easyui-combobox" style="width:140px;"  value="${requestScope.plmTlConappraisalentry.cdapIdealtype}"/></span>
							    	<span id="uomControl" style="margin-left: 10px;">
							    	<input id="cmbCdapUom" readonly="readonly" name="cmbCdapUom" class="easyui-combobox"  style="width:156px;" value="${requestScope.plmTlConappraisalentry.cdapUom}"/></span>
								</div>
							</div>
							<div>
						    	<span id="err_cmbCdapIdealtype" class="tpm-errormsg" style="width:140px"></span>
						    	<span id="err_cmbCdapUom" class="tpm-errormsg" style="width:140px; width:110px;margin-left:6px;"></span>
							</div>
						</div>
						
						<div  style="padding-left:10px;padding-top:0px;">
						<div id="idealMinmax">
						<div>
								<label id="minlbl" class="mandatory-lbl">Minimum</label>
							<span style="margin-left: 90px;">
								<label id="maxlbl" class="mandatory-lbl">Maximum</label>
								</span>
			    		
			    		</div>
			    		<div>
					    		<input id="txtCdapIdealminimum" name="txtCdapIdealminimum" readonly="readonly" class="easyui-text" maxlength="6"  style="width:140px;" value="${requestScope.plmTlConappraisalentry.cdapIdealminimum}"/>
					    		<span style="margin-left:10px;">
							   	<input id="txtCdapIdealmaximum" name="txtCdapIdealmaximum" readonly="readonly" class="easyui-text" maxlength="6" style="width:150px;"  value="${requestScope.plmTlConappraisalentry.cdapIdealmaximum}"/>
							   	</span>
						</div>
						</div>
						<div id="idealTxt">						
							<div >
					     		<label class="mandatory-lbl" >Ideal Condition</label>
					    	 </div>						
						    <div >
						    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtCdapIdealcondition"  disabled="disabled" name="txtCdapIdealcondition"  style="text-transform: uppercase;height:60px;width:300px">${requestScope.plmTlConappraisalentry.cdapIdealcondition}</textarea>
							</div>
						</div>
						
						</div>
						<div style="padding-left:20px;">
								<div >
								<span style="position:absolute; width: 140px ">
								<label id="err_txtCdapIdealminimum" class="tpm-errormsg"></label>
								</span>
										<span style="margin-left: 157px; position:absolute;">
								<label id="err_txtCdapIdealmaximum" class="tpm-errormsg"></label>
								</span>
							   </div>	   	
							 
						</div> 
						<div  style="margin-left:10px; padding-top:0px">
							<div>
								<div class="easyui-paddingbfpx">
							     	<label class="mandatory-lbl">Actual Condition</label>
							     	<span style="margin-left:49px;">
							     	 	<label  class="mandatory-lbl">Status</label>
							     	</span>
							     </div>
							     <div >		
							     <span>					     
								     	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtCdapActualcondition"  name="txtCdapActualcondition"   style="text-transform: uppercase;height:25px;width:140px;">${requestScope.plmTlConappraisalentry.cdapActualcondition}</textarea>
								 </span>
								 <span>  
								     	<input id="txtCdapActualvalue" name="txtCdapActualvalue" maxlength="6" class="easyui-text" style="width:140px;" onblur="setStatus();" value="${requestScope.plmTlConappraisalentry.cdapActualvalue}"/>
								     
							    </span>
							     <span id="actualOK">
								    
								     	<input id="cmbCdapOknotok" name="cmbCdapOknotok" class="easyui-combobox" style="width:140px;" value="${requestScope.plmTlConappraisalentry.cdapOknotok}"/>
								    
								    
							     </span>
							   
							     <span style="margin-left:10px;">
							    		<input class="easyui-combobox" id="cmbCdapStatus" name="cmbCdapStatus"  style="width:120px;"/>
							      </span>
							     	
							    		<span class="hse-comp" id = "StatusDiv" style="margin-top: -16px;  margin-left: 290px;" ></span>
							  
							    
							
							     </div>
							   </div>
							   
							   
						    </div>
						     
						   <!--   <div style ="padding-top:10px">
							     <div>
							     	<label  class="mandatory-lbl">Status</label>
							     	<div>
								     	<table>
								     		<tr>
								     			<td>
								     				<input class="easyui-combobox" id="cmbCdapStatus" name="cmbCdapStatus"  style="width:140px;"></input>
								     			</td>
								     			<td>
								     				<div style="margin-left:10px">
								     				
											     		<span class="easyui-text" id = "StatusDiv" style="display:inline-block;width:60px;background:red;font-size:13px;font-weight:bold;"></span>
										     		</div>
								     			</td>
								     		</tr>
								     	</table>
						     		</div>
							     </div>
						     </div>
					     </div>	
					      -->
					      </div>
					</td>
					<td valign="top" colspan="2">
					<div style ="width: 400px;">
						<div style="margin-left:-20px">
							<div class="easyui-paddingbfpx" >
						     	<label class="mandatory-lbl" >Refurbishment Required ?</label>
						     </div>
						     <div>
						     	<input id="cmbCdapRefurbishmentStatus" name="cmbCdapRefurbishmentStatus" class="easyui-combobox" style="width:200px;"  value="${requestScope.plmTlConappraisalentry.cdapRefurbishmentStatus}"/>
						     </div>
					     </div>
					     
					      
						<div style="margin-left:-20px;padding-top:-0px; padding-top:0px">
							<div >
						     	<label id ="refursh">Action Required</label>
						     </div>
						     <div>
						    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtCdapActionrequired" maxlength="490" name="txtCdapActionrequired" style="text-transform: uppercase;height:50px;width:200px;">${requestScope.plmTlConappraisalentry.cdapActionrequired}</textarea>
							</div>
						</div>
						
						
					
					<div  style="margin-left:0px;padding-top:-0px; padding-top:0px" >			
						 	
							<span>
								<input id="btnInsert" name="btnInsert" class="easyui-button"  type="button" value="Insert" style="width:50px;height:21px;" />
							</span>						
							<span style="display:none;">
								<input id="btnClear" name="btnClear" class="easyui-button"  type="button" value="Clear" style="width:50px;height:21px;" />
							</span>
							
							<span style="display:none;">
								<input id="btnDelete" name="btnDelete" class="easyui-button"  type="button" value="Delete" style="width:50px;height:21px;" />
							</span>
						
					</div>
					</div>
                    </td>
				</tr>				
				
				<tr>
					<td colspan="6" valign="top" >
						<div style="margin-top:0px">
							<table id="conditionalAppGrid">
								<tr>
									<td>
									</td>
								</tr>
							</table>
							<div id="conditionalAppPager"></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<input type="hidden"id="hdnCdapKeyid" name="hdnCdapKeyid" value="${requestScope.plmTlConappraisalentry.cdapKeyid}" ></input>
			<input type="hidden"id="hdnCdamKeyid" name="hdnCdamKeyid" value="${requestScope.plmTlConappraisalmstentry.cdapCdamKeyid}" ></input>
			<input type="hidden"id="hdnCdapCdamKeyid" name="hdnCdapCdamKeyid" value="${requestScope.plmTlConappraisalmstentry.cdapCdamKeyid}" ></input>
			<input type="hidden"id="hdnCdamCreatedon" name="hdnCdamCreatedon" value="${requestScope.plmTlConappraisalmstentry.cdamCreatedon}" ></input>
			<input type="hidden"id="hdnCdapCreatedon" name="hdnCdapCreatedon" ></input>
			<input type="hidden"id="hdnCdapCdapkeyid" name="hdnCdapCdapkeyid" ></input>
			<input type="hidden"id="hdnCdamUpdtDate" name="hdnCdamUpdtDate" value="${requestScope.cdamUpdtDate}"></input>
			
			<input type="hidden"id="hdnnewFrm" name="hdnnewFrm" value="${requestScope.newFrm}" ></input>
			<input type="hidden"id="mode" name="mode" ></input>
			<input type="hidden" id="hdnroleName" name="hdnroleName" value="${requestScope.rolename}"/>
<input type="hidden" id="hdnroleId" name="hdnroleId" value="${requestScope.rolekeyid}"/>
		</div>
	</div>
</form>
<input type="hidden"id="hdnOldDate" name="hdnOldDate" val=""></input>