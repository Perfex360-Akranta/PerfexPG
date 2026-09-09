<script><!--
jQuery(document).ready(function() {
    try {
        console.log("=== Page Loading Started ===");
        
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
            jQuery('#frmConditionalApp').hide();
            
            // Show error message
            if(typeof popupCommonErrorMsg === 'function') {
                popupCommonErrorMsg(" Only PM PILLAR MEMBER role can create Conditional Appraisal");
            } else {
                alert(" Only PM PILLAR MEMBER role can create Conditional Appraisal");
            }
            
            // Redirect back after showing message
            setTimeout(function() {
                navigateToPrevForm();
            }, 3000);
            
            return false;
        }
        
        console.log("Access GRANTED - Initializing form");
        
        // If role check passes, continue with form initialization
        initialiseForm('frmConditionalApp');
        jQuery('#submitForm').val('frmConditionalApp');
        
        jQuery("#newComp").hide();	
        
        disableField('frmConditionalApp', 'cmbCdapStatus');
        formatDateBox('dteCdamDate', 'dd-MMM-yyyy');
        
        var newfrm = jQuery("#hdnnewFrm").val();
        if(newfrm && newfrm.trim().length == 0){
            fillWithCurrentDate('dteCdamDate');
        }
        
        var compType = jQuery("#cmbCdapComponentType").val();
        if(!compType || compType.trim().length == 0){
            setFieldValue('cmbCdapComponentType',"E");
        }
        
        // Fill all combo boxes
        fillComboBox("frmConditionalApp","cmbCdapTypeofcheck","checkType.commonFilter");
        fillComboBox("frmConditionalApp","cmbCdapComponentid","spareCombo.commonFilter");
        fillComboBox("frmConditionalApp","cmbCdapUom","uomCombo.commonFilter");
        fillComboBox("frmConditionalApp","cmbCdapComponentType","component_combo.condapp","",false);
        fillComboBox("frmConditionalApp","cmbCdapOknotok","actualOknotok_combo.condapp","",false);
        fillComboBox("frmConditionalApp","cmbCdapCheckingtool","checkingtool_combo.condapp");	
        fillComboBox("frmConditionalApp","cmbCdapStatus","status_combo.condapp","",false);
        fillComboBox("frmConditionalApp","cmbCdapIdealtype","idealType_combo.condapp","",false);
        fillComboBox("frmConditionalApp","cmbCdapRefurbishmentStatus","refurbishment_combo.condapp","",false);
        fillComboBox("frmConditionalApp","cmbCdapMachineid","machineCombo.commonFilter");
        
        var idealType = jQuery("#cmbCdapIdealtype").val();
        if(!idealType || idealType.trim().length == 0){
            setFieldValue('cmbCdapIdealtype',"M");
        }
        
        numericTextBox('txtCdapIdealminimum');
        numericTextBox('txtCdapIdealmaximum');
        numericTextBox('txtCdapActualvalue');
        existNew(getFieldValue("cmbCdapComponentType"));
        statusColor();
        
        // Load functional location
        var factId = jQuery("#frmConditionalApp input[id='factory']").val() || "";
        var sectionId = jQuery("#frmConditionalApp input[id='section']").val() || "";
        var cellId = jQuery("#frmConditionalApp input[id='cell']").val() || "";
        var machId = jQuery("#frmConditionalApp input[id='machine']").val() || "";
        var flid = jQuery("#frmConditionalApp input[id='flid']").val() || "";
        
        var dataStr = "";
        if(flid && flid.trim().length > 0) {
            dataStr = "&flid=" + flid;
        } else {
            dataStr = "&factId=" + factId + "&sectionId=" + sectionId + "&cellId=" + cellId + "&machId=" + machId;
        }
        
        setTimeout(function() {
            loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalApp", dataStr);
        }, 500);
        
        // Button click handlers
        jQuery("#btnInsert").click(function(){
            saveForm("frmConditionalApp","ConditionalAppraisalForm_save.condapp?clear=false");
        });	
        
        jQuery("#btnDelete").click(function(){
            var detalkeyid = getFieldValue('hdnCdapKeyid');
            if(detalkeyid != '' && detalkeyid.length != 0) {
                deleteRecord("frmConditionalApp","ConditionalAppraisalDetail_delete.condapp?clear=false");
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
            var mstkeyid = jQuery("#hdnCdamKeyid").val();
            var flid = jQuery("#frmConditionalApp input[id='flid']").val(); 
            openActionPlan("divActionplan",mstkeyid,"CAP",flid,"",keyid);
        });	
        
        fileManagerPopUp("","CNA","frmConditionalApp","btnfilemgr","ConFilemgr");
        
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
                var machId = jQuery("#frmConditionalApp input[id='machine']").val();
                if(machId && machId != '' && machId != ' ') {
                    return "cmbAssmbid=" + machId;
                }
            }
        });
        
        console.log("Page loading completed successfully");
        
    } catch(e) {
        console.error("Error in document.ready:", e);
        alert("Error initializing form: " + e.message);
    }
});


function  frmConditionalAppcmbCdapComponentid_onSelect(record)
{    
	var machine = jQuery("#frmConditionalApp input[id='machine']").val();
	//alert(machine);
	if(machine == '' || machine == ' ' || machine == undefined)
	{   
		setFieldValue('cmbCdapComponentid','');
		alert('Select Equipment');
	}
	

}

function  frmConditionalAppcmbCdapMachineid_onSelect(record)
{    
	var dataStr = "&machId="+record.id ;
	
	var machid = record.id ;
	 //alert(machid);
	 if (machid.length != 0 )
	{
		loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalApp",dataStr);
	}
}
function  frmConditionalAppcmbCdapRefurbishmentStatus_onSelect(record)
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

function frmConditionalApp_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	console.log("FuntLocHierarchy_SuccessCallBack called", keyIds);
	
	if(keyIds.machId != undefined && keyIds.machId.trim() != "" && keyIds.machId.trim() != null) {	
		reloadCombo("frmConditionalApp","cmbCdapComponentid","spareCombo.commonFilter?cmbAssmbid="+keyIds.machId);
	}
	
	if(keyIds.cellId != undefined && keyIds.cellId.trim() != "" && keyIds.cellId.trim() != null) {
		reloadCombo("frmConditionalApp","cmbCdapMachineid","machineCombo.commonFilter?cellId="+keyIds.cellId);
	}
	
	// Always set the machine value if available
	if(keyIds.machId != undefined && keyIds.machId.trim() != "" && keyIds.machId.trim() != null) {
		setFieldValue("cmbCdapMachineid", keyIds.machId);
	}
	
	setFunctionalLocWidth('frmConditionalApp','650px');
	
	var flid = jQuery("#frmConditionalApp input[id='flid']").val();
	
	console.log("Loading grid with flid: " + flid);
	
	if(flid != undefined && flid.trim() != "" && flid.trim() != null) {
		var fflid = "&flid=" + flid;
		viewgrid(fflid);
	}
}
function btnfilemgr_click()
{
  	var keyid = jQuery('#hdnCdamKeyid').val();
  	
  	if(keyid.trim().length<=0){
	    saveForm('frmConditionalApp','ConditionalAppraisalForm_save.condapp?filemanger=filemanger');
  	}else if(keyid != null && keyid != ''){
			fileManagerPopUp(keyid,"CNA","","","");
	    }		
}

function frmConditionalApp_beforeSubmit(){
	var mechid = jQuery("#frmConditionalApp input[id='machine']").val();
	if (mechid =='' || mechid ==' ') {
		popupCommonErrorMsg(" Select Equipment " );
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
		if(parseFloat(min,10) > parseFloat(max,10) )
		{
			//alert('Minimum value shoud be lesser then Maximum');
			popupCommonErrorMsg(" Minimum value shoud be lesser then Maximum Value " );
			//showValidationErrorMsg('txtCdapIdealminimum','Minimum value shoud be lesser then Maximum Value');
			return false;
		}
		setFieldValue('txtCdapActualcondition','NA');
		setFieldValue('cmbCdapStatus','R');
		setFieldValue('txtCdapActionrequired','NA');
		setFieldValue('cmbCdapRefurbishmentStatus',"N");
		setFieldValue('txtCdapActualvalue','0');
		
	}
	else if(idealtype == 'T')
	{
		setFieldValue('txtCdapActualcondition','NA');
		setFieldValue('txtCdapActualvalue','0');
		setFieldValue('txtCdapActionrequired','NA');
		setFieldValue('cmbCdapStatus','R');
		setFieldValue('cmbCdapRefurbishmentStatus',"N");
		
	}
     else if(idealtype == 'O')
	{		
			setFieldValue('cmbCdapOknotok',"NK");
			setFieldValue('cmbCdapStatus',"R");
			setFieldValue('txtCdapActualcondition','NA');
			setFieldValue('txtCdapActionrequired','NA');
			setFieldValue('cmbCdapRefurbishmentStatus',"N");
			setFieldValue('txtCdapActualvalue','0');
			
			
	}
		
	
	

	
	
}
function dteCdamDate_onSelect(date)
{
  		
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
		clearValidationErrorMsg('dteCdamDate');		
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
function frmConditionalApp_beforeDelete(){
	var keyid =jQuery("#hdnCdamKeyid").val();
	
	//alert(keyid);
	if(!keyid.trim().length>0)
		return false;
	else{
		var r=confirm("Are You Sure to Delete? It will delete all check list details");
		if(r){
			return "&clear=false";
		}
		else
			return false;
	}
}
/* function viewgrid(filterString){
	var keyId=getFieldValue("hdnCdamKeyid");
	if(keyId.length>0){
		filterString+="&keyId="+keyId;
	}
	processGridnew("ConditionalAppraisalForm_input.condapp",filterString,"conditionalAppGrid", "conditionalAppPager","","doubleClickCondApp","","ongridcomplete");
} */
function viewgrid(filterString){
    var keyId = getFieldValue("hdnCdamKeyid");
    if(keyId.length > 0){
        filterString += "&keyId=" + keyId;
    }
    
    // Make sure the grid is configured correctly
    processGridnew(
        "ConditionalAppraisalForm_input.condapp",
        filterString,
        "conditionalAppGrid", 
        "conditionalAppPager",
        "", // Make sure this parameter isn't setting a report URL
        "doubleClickCondApp", // Your double-click handler
        "",
        "ongridcomplete"
    );
}
/* function doubleClickCondApp(id){
	var rowData = jQuery("#conditionalAppGrid").jqGrid("getRowData",id);
	var chktolid = rowData.CHECKINGTOOLID ;
	setFieldValue('cmbCdapCheckingtool',chktolid);
	processAjaxCalls("ConditionalAppraisalForm_recall.condapp?q=2&KEYID="+rowData.KEYID,"","recallsuccessCallBack","errorCallBack");
} */

function doubleClickCondApp(id){
    try {
        var rowData = jQuery("#conditionalAppGrid").jqGrid("getRowData", id);
        console.log("Double-click row data:", rowData);
        
        var keyid = rowData.KEYID;
        var chktolid = rowData.CHECKINGTOOLID;
        
        if(!keyid || keyid.trim().length === 0) {
            alert("No record ID found");
            return;
        }
        
        // Set the checking tool first
        setFieldValue('cmbCdapCheckingtool', chktolid);
        
        // Recall the form data
        processAjaxCalls(
            "ConditionalAppraisalForm_recall.condapp?q=2&KEYID=" + keyid,
            "",
            "recallsuccessCallBack",
            "errorCallBack"
        );
        
    } catch(e) {
        console.error("Error in doubleClickCondApp:", e);
        alert("Error loading record: " + e.message);
    }
}

// Add error callback if not present
function errorCallBack(error) {
    console.error("AJAX Error:", error);
    alert("Failed to load record. Please check console for details.");
}

function ongridcomplete(){
	var row = jQuery("#conditionalAppGrid").jqGrid('getDataIDs');
	for(var i=0; i<row.length; i++){
		var rowid = row[i];
		var status = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "STATUS");
		var mskeyid = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid, "CDAM_KEYID");
		setFieldValue('hdnCdamKeyid', mskeyid);
		
		if(status && status.indexOf("R") > -1){ // Changed from status.contains("R")
			jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'red'});
		}
		else if(status && status.indexOf("G") > -1){ // Changed from status.contains("G")
			jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'green'});
			disableField("frmConditionalApp", "btnActionplan_"+(i+1));
		} 
		else{ 
			jQuery("#conditionalAppGrid").jqGrid('setCell', rowid, "STATUSSHW", '', {'background-color':'white'});
		}
	}
}
/* function recallsuccessCallBack(result){
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
    	
} */
function recallsuccessCallBack(result){
    try {
        console.log("Recall success:", result);
        
        if(!result || !result[0]) {
            alert("No data returned from server");
            return;
        }
        
        setFieldValue('hdnCdapKeyid', result[0][0]);
        setFieldValue('cmbCdapComponentType', result[0][1]);
        existNew(result[0][1]);
        setFieldValue('cmbCdapComponentid', result[0][2]);
        setFieldValue('txtCdapNewcomponent', result[0][3]);
        setFieldValue('txtCdapDimension', result[0][4]);
        setFieldValue('txtCdapCheckingtool', result[0][5]);
        setFieldValue('cmbCdapTypeofcheck', result[0][6]);
        setFieldValue('cmbCdapIdealtype', result[0][7]);
        idealActual(result[0][7]);
        setFieldValue('txtCdapIdealminimum', result[0][8]);
        setFieldValue('txtCdapIdealmaximum', result[0][9]);
        setFieldValue('cmbCdapUom', result[0][10]);
        setFieldValue('txtCdapIdealcondition', result[0][11]);
        setFieldValue('txtCdapActualcondition', result[0][12]);
        setFieldValue('txtCdapActualvalue', result[0][13]);
        setFieldValue('cmbCdapOknotok', result[0][14]);
        setFieldValue('cmbCdapStatus', result[0][15]);
        setStatus();
        statusColor();
        setFieldValue('txtCdapActionrequired', result[0][16]);
        setFieldValue('cmbCdapRefurbishmentStatus', result[0][17]);
        setFieldValue('hdnCdapCreatedon', result[0][18]);
        enableBtn();
        
        console.log("Form successfully populated");
        
    } catch(e) {
        console.error("Error in recallsuccessCallBack:", e);
        alert("Error populating form: " + e.message);
    }
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
function frmConditionalApp_deleteSuccessCallback(result)
{
	alert(result.successData.msg);	
	//alert(result.keyId);
	//if((result.keyId).length>0){
		//setDefaultVal(result);
	//}else
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
	clearForm("frmConditionalApp");
	setFieldValue('dteCdamDate',date);
	setFieldValue('cmbCdamFlid',flid);
	setFieldValue('hdnCdamKeyid',keyId);
	setFieldValue('cmbCdapComponentType',"E");
	jQuery("#newComp").hide();
	jQuery("#existComp").show();
	setFieldValue('dteCdamDate',date);
	setFieldValue('cmbCdapIdealtype',"M");
	idealActual(getFieldValue("cmbCdapIdealtype"));
	statusColor();
	setTimeout(function() {
		loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalApp","&flid="+flid);
	},450); 
	//jQuery("#conditionalAppGrid").trigger("reloadGrid");
	var filterString="?q=2";
	if(keyId.length>0){
		filterString+="&keyId="+keyId;
	}
	//var url="ConditionalAppraisalForm_getData.condapp"+filterString;
	//jQuery("#conditionalAppGrid").setGridParam({url:url}).trigger('reloadGrid');
	enableBtn();
}
function frmConditionalApp_successsCallback(result)
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
function  frmConditionalAppcmbCdapComponentType_onSelect(record){
	existNew(record.id);
}



function  frmConditionalAppcmbCdamEquipmentid_onSelect(record){
	loadFunctionalLocation("CondtnlNewfunLocation", "functionalLoc.condapp", "CondtnlNewfunLocation", "frmConditionalApp","&machId="+record.id);
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
function  frmConditionalAppcmbCdapIdealtype_onSelect(record){
	idealActual(record.id);
}
function idealActual(data){
	clearIdealRelated();
	//alert(data);
	if(data == 'M'){
		jQuery("#idealTxt").hide();		
		jQuery("#actualOK").hide();		
		jQuery("#idealMinmax").show();
		
	}else if(data == 'O'){
		jQuery("#idealTxt").hide();
		jQuery("#idealMinmax").hide();		
		jQuery("#actualOK").show();
		
	}else if(data == 'T'){
		jQuery("#idealMinmax").hide();
		jQuery("#actualOK").hide();		
		jQuery("#idealTxt").show();	
		
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
function  frmConditionalAppcmbCdapOknotok_onSelect(record){
	setStatus();
}
function  frmConditionalAppcmbCdapStatus_onSelect(record){
	statusColor();
}
function setStatus(){
	var idealType = getFieldValue('cmbCdapIdealtype');
	if(idealType == 'M'){
		var min = getFieldValue('txtCdapIdealminimum');
		var max = getFieldValue('txtCdapIdealmaximum');
		var actVal = getFieldValue('txtCdapActualvalue');
		if(parseFloat(actVal,10) >= parseFloat(min,10) && parseFloat(actVal,10) <= parseFloat(max,10)){
			setFieldValue('cmbCdapStatus','G');
			statusColor();
		} else {
			setFieldValue('cmbCdapStatus','R');
			statusColor();
		}
		disableField('frmConditionalApp', 'cmbCdapStatus');
	} else if(idealType == 'O'){
		var oknotok = getFieldValue('cmbCdapOknotok');
		if(oknotok == 'OK'){
			setFieldValue('cmbCdapStatus','G');
			statusColor();
		}
		else if(oknotok == 'NK'){
			setFieldValue('cmbCdapStatus','R');
			statusColor();
		}
		disableField('frmConditionalApp', 'cmbCdapStatus');
	} else if(idealType == 'T'){
		enableFields('cmbCdapStatus');
	}
}
function statusColor(){
	var val = getFieldValue('cmbCdapStatus');
	if(val && val.indexOf('G') > -1) // Changed from val.contains('G')
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
function condApp(rowid){
	var flid = jQuery("#frmConditionalApp input[id='flid']").val(); 
	var refDocId = jQuery("#conditionalAppGrid").jqGrid('getCell', rowid,"KEYID");
	var keyid = jQuery("#hdnCdamKeyid").val();
	var mainTask =jQuery("#conditionalAppGrid").jqGrid('getCell', rowid,"ACTIONREQUIRED");
	if(refDocId.trim().length>0)
		openActionPlan("divCdapActionplan",keyid,"CDA",flid,mainTask,refDocId);
}
function divCdapActionplan_onClose(result){
	jQuery("#conditionalAppGrid").trigger("reloadGrid");
	return true;
}
</script>
<form id="frmConditionalApp" name="frmConditionalApp">
	<div id="" style="width:100%;padding: 10px;">
		<div>
			<table>
				<tr>
					<td colspan="2">
						<div id="frmTroubleFuntKeyIds">
							<input type="hidden" id="section" name=cmbSectionid value=""></input> 
							<input type="hidden" id="cell"    name="cmbCellid" value=""></input> 
							<input type="hidden" id="machine" name="cmbMachineid" value=""></input>
							<input type="hidden" id="flid"    name="cmbCdamFlid" value="${requestScope.plmTlConditionalappraisalmst.cdamFlid}"></input>  
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
						     	<input id="dteCdamDate" name="dteCdamDate" class="easyui-datebox" style="width:100px;" value="${requestScope.plmTlConditionalappraisalmst.cdamDate}"/>
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
						     	<input id="cmbCdapMachineid" name="cmbCdapMachineid"  class="easyui-combobox"  style="width:260px;"  value="${requestScope.plmTlConditionalappraisal.cdamEquipmentid}"  />
						     </div>
					     </div>
						<div style ="padding-top:0px">
							<div class="easyui-paddingbfpx">
						     	<label class="mandatory-lbl">Component Type</label>
						     </div>
						     <div>
						     	<input id="cmbCdapComponentType" name="cmbCdapComponentType" class="easyui-combobox" style="width:260px;" value="${requestScope.plmTlConditionalappraisal.cdapComponentType}"/>
						     </div>
					     </div>
					     
					     <div style ="padding-top:0px">
							<div class="easyui-paddingbfpx">
						     	<label class="mandatory-lbl">Component</label>
						     </div>
						     <div>
						     	<span id="existComp">
						     		<input id="cmbCdapComponentid" name="cmbCdapComponentid" class="easyui-combobox" style="width:260px;"  value="${requestScope.plmTlConditionalappraisal.cdapComponentid}"/>
						     	</span>
						     	<span id="newComp">
									<input id="txtCdapNewcomponent" name="txtCdapNewcomponent" class="easyui-text" maxlength="90" style="width:260px;" value="${requestScope.plmTlConditionalappraisal.cdapNewcomponent}">
								</span>
						     </div>
					     </div>
					     
					     <div style ="padding-top:0px">
							<div>
								<div class="easyui-paddingbfpx">
							     	<label class="mandatory-lbl" >Dimension</label>
							     </div>
							     <div>
							    	<input id="txtCdapDimension" name="txtCdapDimension" class="easyui-text" maxlength="90"  style="width:260px;" value="${requestScope.plmTlConditionalappraisal.cdapDimension}"/>
								</div>
							</div>
							<div  class="easyui-paddingbfpx" style ="padding-top:0px">
								<div class="easyui-paddingbfpx">
							     	<label class="mandatory-lbl">Checking Tool</label>
							     </div>
							     <div>
							    	<!--  <input id="txtCdapCheckingtool" name="txtCdapCheckingtool" class="easyui-text" maxlength="90" style="width:260px;" value="${requestScope.plmTlConditionalappraisal.cdapCheckingtool}"/>
							    	-->
							    	<input id="cmbCdapCheckingtool" name="cmbCdapCheckingtool" class="easyui-combobox" style="width:260px;"  value="${requestScope.plmTlConditionalappraisal.cdapCheckingtool}"/>
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
						    	<input id="cmbCdapTypeofcheck" name="cmbCdapTypeofcheck" class="easyui-combobox" style="width:310px;"  value="${requestScope.plmTlConditionalappraisal.cdapTypeofcheck}"/>
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
							    	<span><input id="cmbCdapIdealtype" name="cmbCdapIdealtype" class="easyui-combobox" style="width:140px;"  value="${requestScope.plmTlConditionalappraisal.cdapIdealtype}"/></span>
							    	<span id="uomControl" style="margin-left: 10px;">
							    	<input id="cmbCdapUom"  name="cmbCdapUom" class="easyui-combobox"  style="width:156px;" value="${requestScope.plmTlConditionalappraisal.cdapUom}"/></span>
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
					    		<input id="txtCdapIdealminimum" name="txtCdapIdealminimum" class="easyui-text" maxlength="6"  style="width:140px;" value="${requestScope.plmTlConditionalappraisal.cdapIdealminimum}"/>
					    		<span style="margin-left:10px;">
							   	<input id="txtCdapIdealmaximum" name="txtCdapIdealmaximum" class="easyui-text" maxlength="6" style="width:150px;"  value="${requestScope.plmTlConditionalappraisal.cdapIdealmaximum}"/>
							   	</span>
						</div>
						</div>
						<div id="idealTxt" style="display: none;" >						
							<div >
					     		<label class="mandatory-lbl" >Ideal Condition</label>
					    	 </div>						
						    <div >
						    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtCdapIdealcondition"  name="txtCdapIdealcondition"  style="text-transform: uppercase;height:60px;width:300px">${requestScope.plmTlConditionalappraisal.cdapIdealcondition}</textarea>
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
						<div  style="margin-left:10px; padding-top:0px; display:none; ">
							<div>
								<div class="easyui-paddingbfpx">
							     	<label class="mandatory-lbl">Actual Condition</label>
							     	<span style="margin-left:49px;">
							     	 	<label  class="mandatory-lbl">Status</label>
							     	</span>
							     </div>
							     <div >		
							     <span>					     
								     	<input  id="txtCdapActualcondition"  name="txtCdapActualcondition"    style="text-transform: uppercase;height:25px;width:140px;" value="" />
								 </span>
								 <span>  
								     	<input id="txtCdapActualvalue" name="txtCdapActualvalue" maxlength="6" class="easyui-text" style="width:140px;" onblur="setStatus();" value=""/>
								     
							    </span>
							     <span id="actualOK">
								    
								     	<input id="cmbCdapOknotok" name="cmbCdapOknotok" class="easyui-combobox" style="width:140px;" value="NK"/>
								    
								    
							     </span>
							   
							     <span style="margin-left:10px;">
							    		<input class="easyui-combobox" id="cmbCdapStatus" name="cmbCdapStatus"  value="R" style="width:120px;"/>
							      </span>
							     	
							    		<span class="hse-comp" id = "StatusDiv" style="margin-top: -16px;  margin-left: 290px;" ></span>
							  
							    
							
							     </div>
							   </div>
							   
							   
						    </div>
						     <div  style="padding-top:30px; padding-left:10px; ">							 	
								<span>
									<input id="btnInsert" name="btnInsert" class="easyui-button"  type="button" value="Insert" style="width:50px;height:21px;" />
								</span>						
								<span style="">
									<input id="btnClear" name="btnClear" class="easyui-button"  type="button" value="Clear" style="width:50px;height:21px;" />
								</span>
								
								<span style="">
									<input id="btnDelete" name="btnDelete" class="easyui-button"  type="button" value="Delete" style="width:50px;height:21px;" />
								</span>
						
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
					<div style ="width: 400px;display: none;">
						<div style="margin-left:-20px;display: none;">
							<div class="easyui-paddingbfpx" >
						     	<label class="mandatory-lbl" >Refurbishment Required ?</label>
						     </div>
						     <div>
						     	<input id="cmbCdapRefurbishmentStatus" name="cmbCdapRefurbishmentStatus" class="easyui-combobox" style="width:200px;"  value="N"/>
						     </div>
					     </div>
					     
					      
						<div style="margin-left:-20px;padding-top:-0px; padding-top:0px;display: none;">
							<div >
						     	<label id ="refursh">Action Required</label>
						     </div>
						     <div>
						    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtCdapActionrequired" maxlength="490" name="txtCdapActionrequired" style="text-transform: uppercase;height:50px;width:200px;">${requestScope.plmTlConditionalappraisal.cdapActionrequired}</textarea>
							</div>
						</div>						
						
					
					<div >			
						 	
							<span>
								<input id="btnInsert" name="btnInsert" class="easyui-button"  type="button" value="Insert" style="width:50px;height:21px;" />
							</span>						
							<span style="">
								<input id="btnClear" name="btnClear" class="easyui-button"  type="button" value="Clear" style="width:50px;height:21px;" />
							</span>
							
							<span style="">
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
			<input type="hidden"id="hdnCdapKeyid" name="hdnCdapKeyid" value="${requestScope.plmTlConditionalappraisal.cdapKeyid}" ></input>
			<input type="hidden"id="hdnCdamKeyid" name="hdnCdamKeyid" value="${requestScope.plmTlConditionalappraisalmst.cdamKeyid}" ></input>
			<input type="hidden"id="hdnCdamCreatedon" name="hdnCdamCreatedon" value="${requestScope.plmTlConditionalappraisalmst.cdamCreatedon}" ></input>
			<input type="hidden"id="hdnCdapCreatedon" name="hdnCdapCreatedon" ></input>
			
			<input type="hidden"id="hdnnewFrm" name="hdnnewFrm" value="${requestScope.newFrm}" ></input>
			<input type="hidden"id="mode" name="mode" ></input>
			<input type="hidden" id="hdnroleName" name="hdnroleName" value="${requestScope.rolename}"/>
<input type="hidden" id="hdnroleId" name="hdnroleId" value="${requestScope.rolekeyid}"/>
		</div>
	</div>
</form>
