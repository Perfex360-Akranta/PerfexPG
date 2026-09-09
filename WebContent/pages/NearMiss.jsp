
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
	jQuery(document).ready(
			function() {

	initialiseForm('frmNearMiss');
	jQuery('#submitForm').val('frmNearMiss');
	// readOnlyFields("txtdocuno");

	var keyid=jQuery("#txtNmrtKeyid").val();

	formatDateBox('dteNmrtOccurrencedatetime','dd-MMM-yyyy');	
	spinnerKeyPress('spnPreparedDatetime');
	spinnerChange('spnPreparedDatetime','setShiftEvt');
	spinnerUp('spnPreparedDatetime','setShiftEvt');
	spinnerDown('spnPreparedDatetime','setShiftEvt');	
	
	formatDateBox('dteNmrtPreparedDatetime','dd-MMM-yyyy');
	spinnerKeyPress('spnOccurrencetime');
	spinnerChange('spnOccurrencetime','setShiftEvt');
	spinnerUp('spnOccurrencetime','setShiftEvt');
	spinnerDown('spnOccurrencetime','setShiftEvt');	
	
	
	formatDateBox('dteNmrtCompleteddate','dd-MMM-yyyy');
	formatDateBox('dtenmrtTargetdate','dd-MMM-yyyy');

	fillComboBox('frmNearMiss','cmbNmrtCompletedby', "employee.commonFilter");
	fillComboBox('frmNearMiss','cmbNmrtEmployeeid', "employee.commonFilter");
	fillComboBox('frmNearMiss','cmbNmrtApprovedby', "employee.commonFilter");
	fillComboBox('frmNearMiss','cmbnmrtResponsibility', "employee.commonFilter");
	
	fillComboBox('frmNearMiss','cmbnmrtIdentifiedby', 'employee.commonFilter');
	fillComboBox('frmNearMiss','cmbNmrtSeveritypotentialid', 'comboSeverity.commonFilter');
	fillComboBox('frmNearMiss','cmbNmrtDeptid', 'sectionCombo.commonFilter');
	fillComboBox('frmNearMiss','cmbNmrtProbablerecrate', 'comboProbable.commonFilter');
	fillComboBox("frmNearMiss","cmbNmrtStatus","Combo_Status.api");

	fileManagerPopUp("","NEAR","frmNearMiss","btnFilManage","nearmissFilemgr");
// 	jQuery('#frmNearMiss .easyui-text').css('text-transform', 'uppercase');
// 	jQuery('#frmNearMiss textarea').css('text-transform', 'uppercase');


	//var factId = jQuery("#frmNearMiss input[id='factory']").val();
    var sectionId = jQuery("#frmNearMiss input[id='section']").val();
    var cellId = jQuery("#frmNearMiss input[id='cell']").val();
    var machId = jQuery("#frmNearMiss input[id='machine']").val();
    var flid = jQuery("#frmNearMiss input[id='flid']").val();
    
	var dataStr = "&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	//alert(dataStr);
		loadFunctionalLocation("nearMissFunLocation","functionalLoc.nmr","nearMissFunLocationValues","frmNearMiss",dataStr);	
	//alert(2); 
	var tableCaption = "";
	viewGrid("","masterKeyid="+keyid,tableCaption);
	jQuery('#colVal').val('0');
	var emp = jQuery('#txtEmployeeid').val();
	var form=jQuery("#txtNearKeyid").val();	
	var time =jQuery('#txttime').val();
	var txtPreparetime = jQuery("#txtPreparetime").val();
    var preparedBy = jQuery("#cmbnmrtIdentifiedby").combobox('getValue');
	if(preparedBy != null || preparedBy.trim() == "")
		jQuery("#cmbnmrtIdentifiedby").combobox('setValue',emp);
	
	//alert(time);
	if(time ==null || time=="undefined"||time==''){
		fillWithCurrentDate('dteNmrtOccurrencedatetime');
		fillWithCurrentDate('spnOccurrencetime');		
	}
	
	if(txtPreparetime ==null || txtPreparetime=="undefined"||txtPreparetime==''){		
		fillWithCurrentDate('dteNmrtPreparedDatetime');
		fillWithCurrentDate('spnPreparedDatetime');	
	}

	 jQuery("#btnabnActionpln").click(function(){


		 var keyid = jQuery('#txtNmrtKeyid').val();
		    //alert(" Keyid :: "+keyid);   //escape(mainTask)  txtkznmIdea
		 if(keyid.trim().length>0)
	    { 
			 var flid = jQuery("#frmNearMiss input[id='flid']").val();
			 var mainTask= getFieldValue("txtNmrtDescnearmiss");
			 var mode = jQuery("#mode").val();

			 apMode = "create";
			if(mode=="view")
			   apMode = "view";
			   
  		   openActionPlan("NMRActionPlan",keyid,"NMR",flid,mainTask,keyid,"",apMode);
  		 
	    }else
		   saveForm('frmNearMiss','Nearmiss_save.nmr?openactnpln=openactnpln');
	 });

	 jQuery("#btnexcelview").click(function(){
		    var keyid=jQuery("#txtNmrtKeyid").val();
		 	var flid = jQuery("#frmNearMiss input[id='flid']").val();
		 	//alert(keyid+" keyid "+flid);
		 	if(keyid != null && keyid.length >0){
		 		window.open("NearMissExcelview_view.nmr?&keyid="+keyid+"&flid="+flid);
		 	}
	 });
	 
	 var nearmiss= jQuery('#hdnReportType').val();
	 var status = jQuery("#cmbNmrtStatus").combobox('getValue');
	 
	 	if(nearmiss=="approval" || nearmiss == "completion" || nearmiss == "closure"){
	 		var Nearid = getFieldValue("txtNmrtKeyid");
			//workFlow("nearmissapproval",false,"NEARMISAPR", Nearid, "NRT",flid);
			 jQuery('#divApprovedby').show();
			 if (nearmiss == "completion" || nearmiss == "closure" ) {
				 readOnlyFields('chkApprovedBy');
				 readOnlyFields('cmbNmrtApprovedby');
			 }
		}
		
		var NrmKeyid=jQuery('#txtNmrtKeyid').val();
		
		if(NrmKeyid.length>0){
			jQuery("#txtNmrtOthers").attr("disabled",true);
			jQuery("#txtNmrtOthersuc").attr("disabled",true);
			jQuery("#btnexcelview").show();
			if(jQuery('#chkOthersuc').is(':checked') == true){
			    checkOthersucEnableDisable();
		    }
			if(jQuery('#chkOthers').is(':checked') == true){
					checkOthersEnableDisable();
			}
			
		}
		
		var others=jQuery('#hdnOthers').val();
	     
		if(others != undefined && others.trim() == "Y"){
			jQuery("#chkkzbnOthers").attr("checked","checked");
	    }
		
		var respothers=jQuery('#hdnRespOthers').val();
	     
		if(respothers != undefined && respothers.trim() == "Y"){
			jQuery("#chkkzbnRespOthers").attr("checked","checked");
	    }

		 setTimeout(function() {

			 var approvalRequired = jQuery('#hdnApprovalRequired').val();
			 if (approvalRequired=='Y') {
			 	readOnlyFields('cmbNmrtStatus');
			 }
			 nearmissStatus(status);
			 
		 	if(nearmiss=="completion")
		 		enableFields('cmbNmrtStatus');
		 
	 		var mode = jQuery("#mode").val();
			if(mode == "view" || nearmiss == "view"){
				disableForm("frmNearMiss");
				jQuery("#btnFilManage").attr("disabled",true);
				jQuery('#frmNearMissFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0%;left:0;width:100%;z-index:2;opacity:0.4;height:10%;"> </div>');
				enableUIButton('btnexcelview');
				enableUIButton('btnabnActionpln');
				enableUIButton('btnFilManage');
					
			}
			
			if(nearmiss == "completion" || nearmiss == "closure"){
				readOnlyFields('cmbNmrtEmployeeid');
				readOnlyFields('cmbNmrtSeveritypotentialid');
				readOnlyFields('cmbNmrtProbablerecrate');
				readOnlyFields('dtenmrtTargetdate');
				readOnlyFields('cmbnmrtResponsibility');
				
				readOnlyFields('cmbnmrtIdentifiedby');
				readOnlyFields('dteNmrtOccurrencedatetime');
				readOnlyFields('spnOccurrencetime');
			}
			
		 } ,650); 

		    
		
		 
	});

	function frmNearMiss_FuntLocHierarchy_SuccessCallBack(result){
		
		setFunctionalLocWidth('frmNearMiss','845px');
		
		var cellId = jQuery("#frmNearMiss input[id='cell']").val();
		reloadCombo('frmNearMiss','cmbNmrtCompletedby', "employee.commonFilter?&cellId="+cellId);
		//reloadCombo('frmNearMiss','cmbNmrtEmployeeid', "employee.commonFilter");
		reloadCombo('frmNearMiss','cmbnmrtResponsibility', "employee.commonFilter?&cellId="+cellId);
		reloadCombo('frmNearMiss','cmbNmrtApprovedby', "employee.commonFilter?&cellId="+cellId);
		reloadCombo('frmNearMiss','cmbnmrtIdentifiedby', "employee.commonFilter?&cellId="+cellId);
		reloadCombo("frmNearMiss","cmbNmrtEmployeeid","employee.commonFilter?&cellId="+cellId);
		
		var keyid = jQuery("#txtNmrtKeyid").val();
		
		if(keyid == null || keyid=="" || keyid== ' ')
		{
			jQuery("#chkOthers").attr("checked",false);
			jQuery("#chkOthersuc").attr("checked",false);
			jQuery("#txtNmrtOthers").attr("disabled",true);
			jQuery("#txtNmrtOthersuc").attr("disabled",true);			
		}
		
		var mode = jQuery("#mode").val();
		
		if(mode == "view"){
			disableForm("frmNearMiss");
			jQuery("#btnFilManage").attr("disabled",true);
			jQuery('#frmNearMissFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0%;left:0;width:90%;z-index:2;opacity:0.4;height:10%;"> </div>');
			enableUIButton('btnexcelview');
			enableUIButton('btnabnActionpln');
			enableUIButton('btnFilManage');
			
		}
	}
	
	
jQuery('#chkkzbnOthers').click(function() {
    
    //alert(" Others "+jQuery('#chkkzbnOthers').val());
    
    if(jQuery("#chkkzbnOthers").is(':checked')== true){
   	 jQuery('#chkkzbnOthers').val('Y');
   	 var sat = jQuery('#chkkzbnOthers:checked').val();
	     jQuery("#cmbNmrtEmployeeid").combobox('setValue',"");
		 othersClickAction(sat);
    }else if(jQuery("#chkkzbnOthers").is(':checked')== false){
        jQuery('#chkkzbnOthers').val('N');
        var sat = jQuery('#chkkzbnOthers').val();
   	 jQuery("#cmbNmrtEmployeeid").combobox('setValue',"");
		 othersClickAction(sat);
	 }
});

jQuery('#chkkzbnRespOthers').click(function() {
    
    if(jQuery("#chkkzbnRespOthers").is(':checked')== true){
   	 jQuery('#chkkzbnRespOthers').val('Y');
   	 var sat = jQuery('#chkkzbnRespOthers:checked').val();
	     jQuery("#cmbnmrtResponsibility").combobox('setValue',"");
		 othersRespClickAction(sat);
    }else if(jQuery("#chkkzbnRespOthers").is(':checked')== false){
        jQuery('#chkkzbnRespOthers').val('N');
        var sat = jQuery('#chkkzbnRespOthers').val();
   	 jQuery("#cmbnmrtResponsibility").combobox('setValue',"");
		 othersRespClickAction(sat);
	 }
});


jQuery('#chkkzbnPrepareOthers').click(function() {
    
    if(jQuery("#chkkzbnPrepareOthers").is(':checked')== true){
   	 jQuery('#chkkzbnPrepareOthers').val('Y');
   	 var sat = jQuery('#chkkzbnPrepareOthers:checked').val();
	     jQuery("#cmbnmrtIdentifiedby").combobox('setValue',"");
	     othersPrepareClickAction(sat);
    }else if(jQuery("#chkkzbnPrepareOthers").is(':checked')== false){
        jQuery('#chkkzbnPrepareOthers').val('N');
        var sat = jQuery('#chkkzbnPrepareOthers').val();
   	 jQuery("#cmbnmrtIdentifiedby").combobox('setValue',"");
		 othersPrepareClickAction(sat);
	 }
});


function othersClickAction(sat)
{

	var cellId = jQuery("#frmNearMiss input[id='cell']").val();
	var flid = jQuery("#frmNearMiss input[id='flid']").val();
	if(sat=="Y")
	{
	  reloadCombo("frmNearMiss","cmbNmrtEmployeeid","employee.commonFilter?&cellId="+cellId+"&others=Y");
	}
	else
	{  
	  reloadCombo("frmNearMiss","cmbNmrtEmployeeid","employee.commonFilter?&cellId="+cellId);
	}	
}

function othersRespClickAction(sat)
{

	var cellId = jQuery("#frmNearMiss input[id='cell']").val();
	var flid = jQuery("#frmNearMiss input[id='flid']").val();
	if(sat=="Y")
	{
	  reloadCombo("frmNearMiss","cmbnmrtResponsibility","employee.commonFilter?&cellId="+cellId+"&others=Y");
	}
	else
	{  
	  reloadCombo("frmNearMiss","cmbnmrtResponsibility","employee.commonFilter?&cellId="+cellId);
	}	
}

function othersPrepareClickAction(sat)
{

	var cellId = jQuery("#frmNearMiss input[id='cell']").val();
	var flid = jQuery("#frmNearMiss input[id='flid']").val();
	if(sat=="Y")
	{
	  reloadCombo("frmNearMiss","cmbnmrtIdentifiedby","employee.commonFilter?&cellId="+cellId+"&others=Y");
	}
	else
	{  
	  reloadCombo("frmNearMiss","cmbnmrtIdentifiedby","employee.commonFilter?&cellId="+cellId);
	}	
}

jQuery("#cmbNmrtEmployeeid").combobox({onRequest:function(opts){	
	
	var empId = getFieldValue("cmbNmrtEmployeeid");	
	return "combokey="+empId;
	}
});

jQuery("#cmbNmrtApprovedby").combobox({onRequest:function(opts){	
	
	var empId = getFieldValue("cmbNmrtApprovedby");	
	return "combokey="+empId;
	}
});



jQuery("#cmbNmrtCompletedby").combobox({onRequest:function(opts){	
	
	var empId = getFieldValue("cmbNmrtCompletedby");	
	return "combokey="+empId;
	}
});

jQuery("#cmbnmrtResponsibility").combobox({onRequest:function(opts){	
	
	var empId = getFieldValue("cmbnmrtResponsibility");	
	return "combokey="+empId;
	}
});

jQuery("#cmbnmrtIdentifiedby").combobox({onRequest:function(opts){	
	
	var empId = getFieldValue("cmbnmrtIdentifiedby");	
	return "combokey="+empId;
	}
});

function btnFilManage_click(){
	   var documentNo =jQuery("#txtNmrtKeyid").val();
	   if(documentNo != null && documentNo != ''){
			 var fmgMode = "";
			 var mode = jQuery("#mode").val();
			 if(mode=="view")
				 fmgMode = "view";
			 
			fileManagerPopUp(documentNo,"NRM","","","", fmgMode);
		}
	   else {    
		   saveForm('frmNearMiss','Nearmiss_save.nmr?openfilemgr=openfilemgr');		 
	   }		
	}
	

function dtenmrtTargetdate_onSelect(record){
	
	var targetDate = jQuery('#dtenmrtTargetdate').datebox('getValue') ;
 	var detectoinDate = jQuery('#dteNmrtOccurrencedatetime').datebox('getValue') ;
 	//var currentDate = getServerDateTime();
 
 	//alert(targetDate+detectoinDate);
 	 if(convertStringToDate(targetDate) < convertStringToDate(detectoinDate) )
 	{
 		
 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Less than Detected Date');
 		popupCommonErrorMsg('Should Not Less than Detected Date');
 		fillWithCurrentDate('dtenmrtTargetdate');
 	}
 	else
 	    clearValidationErrorMsg('dtenmrtTargetdate');	


}
function dteNmrtOccurrencedatetime_onSelect(record) {
	var detectoinDate = jQuery('#dteNmrtOccurrencedatetime').datebox('getValue') ;
	var currentDate = getServerDateTime();
	  if((currentDate) < convertStringToDate(detectoinDate) )
		 {
	 		popupCommonErrorMsg('Should not greater than Current Date');
	 		fillWithCurrentDate('dteNmrtOccurrencedatetime');
	 		
		 }
	 	else
	 	    clearValidationErrorMsg('dteNmrtCompleteddate');
}
function dteNmrtCompleteddate_onSelect(record) {
		
	var targetDate = jQuery('#dteNmrtCompleteddate').datebox('getValue') ;
 	var detectoinDate = jQuery('#dteNmrtOccurrencedatetime').datebox('getValue') ;
 	var currentDate = getServerDateTime();
 	
 	
 	 if(convertStringToDate(targetDate) < convertStringToDate(detectoinDate) )
 	{
 		//showValidationErrorMsg('dteAbnmTargetdate','Should Not Less than Detected Date');
 		popupCommonErrorMsg('Should Not Less than Detected Date');
 		fillWithCurrentDate('dteNmrtCompleteddate');
 	}
 	 
 	  if((currentDate) < convertStringToDate(targetDate) )
	 {
 		popupCommonErrorMsg('Should Not Less than Current Date');
 		fillWithCurrentDate('dteNmrtCompleteddate');
	 }
 	else
 	    clearValidationErrorMsg('dteNmrtCompleteddate');	

 	
} 


function viewGrid(url,dataString,tableCaption)
{
    processGridnew("NearMissAct_input.nmr",dataString,"Actgrid","pageract",tableCaption,"actDoubleClick","","actgridcompletecallback");
    processGridnew("NearMissCondition_input.nmr",dataString,"Conditiongrid","pagercondition",tableCaption,"condDoubleClick","","conditiongridcompletecallback");
}
function actgridcompletecallback()
{
	 var rowACT = jQuery("#Actgrid").jqGrid('getDataIDs');
	 //alert(rowACT.length);
	 for(var i=0;i<rowACT.length;i++)
	 {
		
		 var colKeyid = jQuery("#Actgrid").jqGrid('getCell',rowACT[i],"NEAR_KEYID"); 
		 var dtlKeyId = jQuery("#Actgrid").jqGrid('getCell',rowACT[i],"NMUA_KEYID");
		
				  if(dtlKeyId.trim().length==0){
				 	 
				  }else{
						  tick(rowACT[i],'Act','Actgrid','unsafeAct',colKeyid,dtlKeyId);
					  }
     	 }
}
function conditiongridcompletecallback()
{
	 var rowCON = jQuery("#Conditiongrid").jqGrid('getDataIDs');
	 for(var i=0;i<rowCON.length;i++)
	 {
		 var colKeyid = jQuery("#Conditiongrid").jqGrid('getCell',rowCON[i],"NEAR_KEYID"); 
		 var dtlKeyId = jQuery("#Conditiongrid").jqGrid('getCell',rowCON[i],"NMUA_KEYID");	
		  if(dtlKeyId.trim().length==0){
				  }
		 else{
			 tick(rowCON[i],'Con','Conditiongrid','unsafeCon',colKeyid,dtlKeyId);
			 
			 }
     	 }
	 
}
function tick(rowid,name,gridId,identifier,keyId,dtlkeyid)
{  
	 var tickVal;
	   if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0") 
	   {  
	       tickVal = "&#x2713;";
		  }
	     else
	   { 
	        tickVal = "&#10004;";
	    }
	    
	 	if(identifier == "unsafeCon"){
	 		for(var i=0;i<=9;i++)
	 			{
	 			  // alert("rowid::"+rowid+"::i::"+i);
	 			  var number=i; 
	 			  if(rowid==number)
	 				  {
	   		           jQuery("#"+gridId).jqGrid('setCell',rowid,name,tickVal,{},{'unsafeCon':'true','KEYID':keyId,'DTLKEYID':dtlkeyid});
	   		         
	 				  }
	 			  else
	 				  { 
	 				   jQuery("#"+gridId).jqGrid('setCell',i,name,' ',{},{'unsafeCon':'true','KEYID':keyId,'DTLKEYID':dtlkeyid});
	 				  }
	 	}
	 	}
	 	
	  else{
	 		for(var i=0;i<=9;i++)
 			{
 			 //  alert("rowid::"+rowid+"::i::"+i);
 			  var number=i; 
 			  if(rowid==number)
 				  {
 				  jQuery("#"+gridId).jqGrid('setCell',rowid,name,tickVal,{},{'unsafeAct':'true','KEYID':keyId,'DTLKEYID':dtlkeyid});
 				  }
 			  else
 				  {
 				   jQuery("#"+gridId).jqGrid('setCell',i,name,' ',{},{'unsafeAct':'true','KEYID':keyId,'DTLKEYID':dtlkeyid});
 				  }
 	                }
	 	                }
}

function condDoubleClick(ids,options)
{
	var nearmiss= jQuery('#hdnReportType').val();
	var mode = jQuery("#mode").val();
	if(mode == "view" || nearmiss == "view"){
		return false;
	}
	
	var colKeyid = jQuery("#Conditiongrid").jqGrid('getCell',ids,"NEAR_KEYID");
	var dtlkeyid = jQuery("#Conditiongrid").jqGrid('getCell',ids,"NMUA_KEYID");
	var checkTick = jQuery("#Conditiongrid").jqGrid('getCell',ids,"Con");
	if(checkTick.trim().length==0 ||checkTick.trim().length==2){
		 tick(ids,'Con','Conditiongrid','unsafeCon',colKeyid,dtlkeyid);
	}
	else{
		 untick(ids,'Con','Conditiongrid','unsafeCon',dtlkeyid);
		}
}
function actDoubleClick(ids,options)
{
	var nearmiss= jQuery('#hdnReportType').val();
	var mode = jQuery("#mode").val();
	if(mode == "view" || nearmiss == "view"){
		return false;
	}
	
	var colKeyid = jQuery("#Actgrid").jqGrid('getCell',ids,"NEAR_KEYID");
	var dtlkeyid = jQuery("#Actgrid").jqGrid('getCell',ids,"NMUA_KEYID");
	//alert("dtlkeyid::"+dtlkeyid.trim.length);
	var checkTick = jQuery("#Actgrid").jqGrid('getCell',ids,"Act");
	//alert("checkTick::"+checkTick.trim().length);
	if(checkTick.trim().length==0 ||checkTick.trim().length==2){
	tick(ids,'Act','Actgrid','unsafeAct',colKeyid,dtlkeyid);
	}else{
		untick(ids,'Act','Actgrid','unsafeAct',dtlkeyid);
		}
}
/*function actionFormatterC(cellvalue, options, rowObject) 
{    
	 //alert(object.Keys(cellvalue)+" "+options+" "+rowObject);
	 var rowId = options.rowId;
	 var formatStr  = '<span id="rc_'+rowId+'"' ;
		 //formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
	
	
}*/
function untick(ids,name,gridId,identifier,dtlkeyid)
{   
 
	//alert("untick rowid:::"+rowid);
	//alert("untick name"+name);
	//alert("untick gridId"+gridId);
	//alert("untick identifier"+identifier);
if(identifier == "unsafeAct"){
	
	if(dtlkeyid!=null)
		jQuery("#"+gridId).setCell(ids, name,'  ',{},{'unsafeAct':'true','KEYID':'','DTLKEYID':dtlkeyid});
	else
	jQuery("#"+gridId).setCell(ids, name,'  ',{},{'unsafeAct':'false','KEYID':'','DTLKEYID':dtlkeyid});
}
	else{
		if(dtlkeyid!=null)
			jQuery("#"+gridId).setCell(ids, name,'  ',{},{'unsafeCon':'true','KEYID':' ','DTLKEYID':dtlkeyid});
		else
			jQuery("#"+gridId).setCell(ids, name,'  ',{},{'unsafeCon':'false','KEYID':' ','DTLKEYID':dtlkeyid});
	}
}
function frmNearMiss_beforeSubmit(){
	
	var actnplnstatus=jQuery('#hdnactnpnsts').val();
	var NearStatus=jQuery('#cmbNmrtStatus').combobox('getText');
	var cellId = jQuery("#frmNearMiss input[id='cell']").val();
	if(cellId.trim().length==0){
		alert(" Select JH ");
		return false;
	}
	
	  var rowACT = jQuery("#Actgrid").jqGrid('getDataIDs');
	  var rowCON = jQuery("#Conditiongrid").jqGrid('getDataIDs');
	  var unsafeact ="";
	  var unsafecon ="";
	  for(var i=0;i<rowACT.length;i++)
	  {
		 unsafeact = jQuery("#Actgrid").jqGrid('getCell',parseInt(i)+1,"Act");
		 if(!(unsafeact==null || unsafeact=='' || unsafeact==' ') )
			 i=rowACT.length;
      }
	  
	  for(var i=0;i<rowCON.length;i++)
	  {
		  unsafecon = jQuery("#Conditiongrid").jqGrid('getCell',parseInt(i)+1,"Con");
		 if(!(unsafecon==null || unsafecon=='' || unsafecon==' ') )
			 i=rowACT.length;
      }

	  if((unsafeact.trim().length==0 && unsafecon.trim().length==0)){
		     popupCommonErrorMsg(" Select Unsafe Act or Unsafe Conditions ");
		 	 return false;
		}
	  
	 /* if(unsafecon==null || unsafecon=='' || unsafecon==' ' ){
		     popupCommonErrorMsg(" Select Unsafe Condition ");
		 	 return false;
	  }*/
	  
	if(actnplnstatus=="C" ||actnplnstatus.length==0){
	    var gridData  = '&unsafeGridsData='+unsafeGridDatas();
	    var formType= jQuery('#hdnReportType').val();
		 var approvalRequired = jQuery('#hdnApprovalRequired').val();
	    gridData+= "&formType="+formType+"&approvalRequired="+approvalRequired;
	    return gridData;
	}else if(actnplnstatus=="P" && NearStatus=="COMPLETED"){
		popupCommonErrorMsg(" Complete all Action Plan Before Complete Near Miss ");
		return false;
	}
		
}
function unsafeGridDatas(){
	
	var jsonArrO='[';
	jQuery("#Actgrid").find('td[unsafeAct="true"]').each (function()
			{
				
				var aria=jQuery(this).attr('KEYID');
				var dtlKeyId=jQuery(this).attr('DTLKEYID');
				
				if(aria=="undefined" ||aria ==undefined){
					aria='';
					}
				if(dtlKeyId=="undefined" ||dtlKeyId ==undefined){
					dtlKeyId='';
					}
					if((aria.trim().length==0)&& (dtlKeyId.trim().length==0)){
					//alert("both are null");
					}
				else{
				jsonArrO += '{';
				jsonArrO += "txtNmuaCode :UA, ";
				jsonArrO += "txtNmuaNearkeyid :\""+ aria+"\",";
				jsonArrO += "txtNmuaKeyid :\""+ dtlKeyId+"\"";
				jsonArrO +=  "},";
				}
			});
	jQuery("#Conditiongrid").find('td[unsafeCon="true"]').each (function()
			{
				
				var aria1=jQuery(this).attr('KEYID');
				var dtlKeyId=jQuery(this).attr('DTLKEYID');
					if(aria1=="undefined" ||aria1 ==undefined){
					aria1='';
					}
				if(dtlKeyId=="undefined" ||dtlKeyId ==undefined){
					dtlKeyId='';
					}
				if((aria1.trim().length==0)&& (dtlKeyId.trim().length==0)){
					//alert("both are null");
					}
				else{
				jsonArrO += '{';
				jsonArrO += "txtNmuaCode :UC, ";
				jsonArrO += "txtNmuaNearkeyid :\""+ aria1+"\",";
				jsonArrO += "txtNmuaKeyid :\""+ dtlKeyId+"\"";
				jsonArrO +=  "},";
				}
			});
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");

    ///alert("jsonArrO: "+jsonArrO);
	
	return jsonArrO; 
}
function frmNearMiss_successsCallback(result){
	
	jQuery("#Actgrid").trigger("reloadGrid");
	jQuery("#Conditiongrid").trigger("reloadGrid");  
	
	 var openactnpln =result.successData.openactnpln;
	 var openfilemgr =result.successData.openfilemgr;
	 var keyid = result.successData.keyId;
	 
	 var mode = jQuery("#hdnMode").val();
	 if (openactnpln!=true &&  openfilemgr!=true && mode == 'modify')
		 navigateToPrevForm();
	 
	 jQuery('#txtNmrtKeyid').val(keyid);

	 fillWithCurrentDate('dteNmrtOccurrencedatetime');
	 fillWithCurrentDate('spnOccurrencetime');		
	 fillWithCurrentDate('dteNmrtPreparedDatetime');
	 fillWithCurrentDate('spnPreparedDatetime');
	 var loginId =jQuery('#hdnLoginId').val();
	 setFieldValue("cmbnmrtIdentifiedby",loginId);
	 var url = jQuery('#hiddenUrl').val();
	 viewGrid(url,"?q=2&masterKeyid=","");
	 
	 setFocusOnField("cmbNmrtEmployeeid");
	 setFieldValue("cmbNmrtStatus","P");
	 if(openactnpln==true )
	 { 
		 var flid = result.successData.flid;
		 var maintask = getFieldValue("txtNmrtDescnearmiss");
		 var actionTask = jQuery('#txtNmrtActionrecommended').val();
		 var targetDate = jQuery('#dtenmrtTargetdate').datebox('getValue');
		 var abnDetectDate = getFieldValue("dteNmrtOccurrencedatetime");
		 abnDetectDate = abnDetectDate.trim();
		 openActionPlan("divActionPlan",keyid,"NMR",flid,actionTask,keyid,abnDetectDate,"create");
		 
		 
		 //fileManagerPopUp(keyid,"abn","","",""); 
		 
	 }
	 else if (openfilemgr==true ) {
		 var documentNo = result.successData.keyId;
		 if(documentNo != null && documentNo != ''){
			 var fmgMode = "";
			 fileManagerPopUp(documentNo,"NRM","","","", fmgMode);
		}

	} 
	

	 
	
}
function frmNearMiss_deleteSuccessCallback(result)
{ 
	alert(result.successData.msg);
	jQuery("#Actgrid").trigger("reloadGrid");  
	jQuery("#Conditiongrid").trigger("reloadGrid");
	clearForm("frmNearMiss");
	
}



function frmNearMisscmbNmrtStatus_onSelect(record) {//alert(record.id);
	nearmissStatus(record.id);
}


/*function frmNearMiss_FuntLocHierarchy_SuccessCallBack(keyIds)
{		
	//alert(keyIds.sectId);
	setFieldValue("cmbNmrtDeptid",keyIds.sectId);
	readOnlyFields('cmbNmrtDeptid');	
	var empId = getFieldValue("cmbNmrtEmployeeid");
	
	//alert(empId);
	//setTimeout(function() {
		//reloadCombo("frmNearMiss","cmbNmrtEmployeeid","employeeCombo.commonFilter?combokey="+empId);
		//},1250);
	
}*/
function nearmissStatus(recordid)
{
	if (recordid == 'P') {
		enableFields('dtenmrtTargetdate');
		//readOnlyFields('txtNmrtRemarks');
		readOnlyFields('cmbNmrtCompletedby');
		readOnlyFields('dteNmrtCompleteddate');
		jQuery('#lblCompletedBy').removeClass("mandatory-lbl");
		jQuery('#lblCompleteddte').removeClass("mandatory-lbl");
		enableUIButton("btnabnActionpln");
		} else{
		readOnlyFields('dtenmrtTargetdate');
		//enableFields("txtNmrtRemarks");
		enableFields("cmbNmrtCompletedby");
		enableFields("dteNmrtCompleteddate");
		jQuery("#lblCompletedBy").addClass('mandatory-lbl');
		jQuery("#lblCompleteddte").addClass('mandatory-lbl');
		disableUIButton("btnabnActionpln");
		var keyid=jQuery('#txtNmrtKeyid').val();
		processAjaxCalls("actioncheck_modify.nmr", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
		}
}

function remove_successCallBack(result){
	jQuery("#hdnactnpnsts").val(result[0][0]);
}

function frmNearMiss_beforeDelete()
{
	var nearmiss= jQuery('#hdnReportType').val();
	var mode = jQuery("#mode").val();
	if(mode == "view" || nearmiss == "view"){
		popupCommonErrorMsg('Data Can not Delete in View Mode.');
		return false;
	}
	var msrdelMsg = "Do You Want Delete Record?";
	 if(confirm(msrdelMsg) == false)
	 {
				return false;
	 }
	 else
		 return true;
}
jQuery("#chkOthers").click(function(){
	
	checkOthersEnableDisable();
	//alert(12);
});

jQuery("#chkOthersuc").click(function(){
	
	checkOthersucEnableDisable();
	//alert(13);
});

function checkOthersEnableDisable()
{
	var chkVal = getFieldValue('chkOthers');	
	if(chkVal){
		jQuery("#txtNmrtOthers").attr("disabled",false);
		setFocusOnField("txtNmrtOthers");
		
	}
	else{
		jQuery("#txtNmrtOthers").attr("disabled",true);
		clearField("txtNmrtOthers");
	}
}
function checkOthersucEnableDisable()
{
	var chkVal = getFieldValue('chkOthersuc');	
	if(chkVal){
		jQuery("#txtNmrtOthersuc").attr("disabled",false);
		setFocusOnField("txtNmrtOthersuc");
		
	}
	else{
		jQuery("#txtNmrtOthersuc").attr("disabled",true);
		clearField("txtNmrtOthersuc");
	}
}


</script>

<form id="frmNearMiss" name="frmNearMiss" >
<div id=""  style="padding: 10px;">
<div style=" margin-left:3%;margin-top:-2px;">
<table>
<tr >
<td colspan="3">
				<div id="frmNearMissFuntKeyIds"  >							
							
								<input type="hidden" id="section" name="cmbnmrtSectionid"  value=""></input>
								<input type="hidden" id="cell"    name="cmbnmrtCellid"     value=""></input>
								<input type="hidden" id="machine" name="cmbnmrtMachineid"  value=""></input>		
								<input type="hidden" id="flid" name="cmbnmrtFlnid" value="${requestScope.genTlNearmissreportmst.nmrtFlnid}"  />
								<input type="hidden" id="elementId" name="txtelementid" value="${requestScope.genTlNearmissreportmst.elementid}"  />					
							</div>						
						<div id="nearMissFunLocation" style="width:100%;"></div>	
</td>

</tr>

	<tr>
		<td>
			<div >
			<label class="mandatory-lbl">Employee Name</label>
            <span style="padding-left:10px;">
            	<input type="checkbox" id="chkkzbnOthers" name="chkkzbnOthers"    style="margin-left:10px;" />
		    </span>
		    <span style="padding-left:10px;">
			    <label>	Others</label>
		    </span>

			</div>
			<div class="easyui-paddingbfpx">
				<input type="text" class="easyui-combobox" id="cmbNmrtEmployeeid" name="cmbNmrtEmployeeid"  style="width: 300px; height: 20px" 
				value="${requestScope.genTlNearmissreportmst.nmrtEmployeeid}" />
			</div>
		
			
			<div><label class="mandatory-lbl">Brief Description of Near Miss</label>
			</div>
			<div class="easyui-paddingbfpx">
				<textarea maxlength="480" rows="2" cols="80"  style="width : 300px; height : 66px;" id="txtNmrtDescnearmiss" name="txtNmrtDescnearmiss">${requestScope.genTlNearmissreportmst.nmrtDescnearmiss}</textarea>
			</div>
			<div><label class="mandatory-lbl">Action Recommended</label></div>
			<div>
					<textarea  rows="2" cols="80" maxlength="480" style=" width : 300px; height:66px;" id="txtNmrtActionrecommended" name="txtNmrtActionrecommended" >${requestScope.genTlNearmissreportmst.nmrtActionrecommended}</textarea>
			</div>
			
		
				
			
			
 		</td>
		
		<td  style="padding-left:5px;" valign="top" >
			<div   style="padding-top:1px;display:none">
			<label >Department</label>
			</div>
			<div class="easyui-paddingbfpx" style="display:none">
					<input type="text"class="easyui-combobox" id="cmbNmrtDeptid" name="cmbNmrtDeptid" 
							 style="width: 200px; height: 20px" 
<%-- 							value="${requestScope.genTlNearmissreportmst.nmrtDeptid}"  --%>
							/>
			</div>
			
			<div>
			<label class="mandatory-lbl">Severity Potential</label>
			<span style="padding-left:31px;"><label class="mandatory-lbl">Probable Recurrence Rate</label></span>
			</div>
			
			<div >
				<input type="text" class="easyui-combobox" id="cmbNmrtSeveritypotentialid"
				name="cmbNmrtSeveritypotentialid"  style="width: 120px; height: 20px" 
				value="${requestScope.genTlNearmissreportmst.nmrtSeveritypotentialid}" />
				<span style="padding-left:10px;" ><input type="text" class="easyui-combobox" id="cmbNmrtProbablerecrate"	name="cmbNmrtProbablerecrate"  style="height: 20px; width : 166px;" 
				value="${requestScope.genTlNearmissreportmst.nmrtProbablerecrate}" /></span>
			</div>
			
		
			<div >
			<label class="easyui-paddingbfpx mandatory-lbl" >Target Date</label>
			<span  style="padding-left:65px;"><label class="mandatory-lbl">Responsibility</label></span>
			<span style="padding-left:5px;">
            	<input type="checkbox" id="chkkzbnRespOthers" name="chkkzbnRespOthers"    style="margin-left:10px;" />
		    </span>
		    <span style="padding-left:5px;"> <label> Others</label> </span>
			
			<div class="easyui-paddingbfpx">
			<input id="dtenmrtTargetdate"  name="dtenmrtTargetdate" clear="false" class="easyui-datebox" value="${requestScope.genTlNearmissreportmst.nmrtTargetdate}"  style="width:120px;height:21px;"  />
			  <span style='padding-left:10px'>
			  <input class="easyui-combobox" type="text"  id="cmbnmrtResponsibility"	name="cmbnmrtResponsibility"   style="width:166px; height: 20px" 
				 value="${requestScope.genTlNearmissreportmst.nmrtResponsibility}" />
			
				 </span>
			</div>
			</div>	
			<div>	
				<label>Remarks</label>	
			</div>
			<div >
					<textarea maxlength="480" rows="1" cols="34" id="txtNmrtRemarks" tabindex="8"
						name="txtNmrtRemarks" style="width: 300px; height : 66px;">${requestScope.genTlNearmissreportmst.nmrtRemarks}</textarea>
			</div>
			
			<div id="divApprovedby" style="display: none;">	
				<label>
					<input type="checkbox" id="chkApprovedBy"  value='' />
					Approved By 
				</label>	
				<div >
						<input type="text" class="easyui-combobox" id="cmbNmrtApprovedby" name="cmbNmrtApprovedby"  style="width: 300px; height: 20px" 
				value="${requestScope.genTlNearmissreportmst.nmrtApprovedby}" />
				</div>	
			 </div>
		</td>
		<td style="padding-left:35px;" valign="top">
			
			<div >
			<label class="mandatory-lbl">Date & Time of Occurrence</label>
						
			</div>
			<div class="easyui-paddingbfpx">
				<span   >
						<input id="dteNmrtOccurrencedatetime"  name="dteNmrtOccurrencedatetime" clear="false" class="easyui-datebox" value="${requestScope.genTlNearmissreportmst.nmrtOccurrencedatetime}"  style="width:118px;width:190px\9;height:21px;"  />
				</span>
				<span class="spinner easyi-paddingbfpx">
						<input  id="spnOccurrencetime"  name="spnOccurrencetime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.preparedtime}"  style="width: 60px;" />
				</span>
				</div>
				<div>
					<label class="easyui-paddingbfpx mandatory-lbl">Prepared By</label>
					<span style="padding-left:5px;">
	            		<input type="checkbox" id="chkkzbnPrepareOthers" name="chkkzbnPrepareOthers"    style="margin-left:10px;" />
			    	</span>
					<span style="padding-left:5px;"> <label> Others</label> </span>
				</div>
				<div>
					<input class="easyui-combobox" type="text"  id="cmbnmrtIdentifiedby"	name="cmbnmrtIdentifiedby"   style="width: 184px; height: 20px" 
				 value="${requestScope.genTlNearmissreportmst.nmrtIdentifiedby}" />
				</div>
						
			
			<div >
			<label class="mandatory-lbl">Prepared Date & Time</label>
						
			</div>
			<div class="easyui-paddingbfpx">
				<span   >
						<input id="dteNmrtPreparedDatetime"  name="dteNmrtPreparedDatetime" disabled="disabled" clear="false" class="easyui-datebox" value="${requestScope.genTlNearmissreportmst.nmrtPreparedDatetime}"  style="width:118px;width:190px\9;height:21px;"  />
				</span>
				<span class="spinner easyi-paddingbfpx">
						<input  id="spnPreparedDatetime"  name="spnPreparedDatetime"  disabled="disabled" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.preparedtime}"  style="width: 60px;" />
				</span>
				</div>
				
				<div>	<label class="mandatory-lbl">Investigation</label>	</div>
							<div >
								<textarea maxlength="480" rows="1" cols="20" id="txtNmrtInvestigation" tabindex="8"
									name="txtNmrtInvestigation" style="width: 190px; height : 66px;">${requestScope.genTlNearmissreportmst.nmrtInvestigation}</textarea>
							</div>
		 </td>
		<td valign="top">
          <div style="padding-top:18px;padding-left:64px;padding-left:140px\9;">
			 <span  id="nearmissFilemgr" style="">
     		
             </span> 
          </div>
          
		<div style="padding-top:18px;padding-left:64px;padding-left:140px\9;">
			<input type="button" id="btnabnActionpln" name="btnabnActionpln" class="easyui-button" style="width:80px;height:22px;" value="Action Plan" />
		</div>
		<div style="padding-top:18px;padding-left:64px;padding-left:140px\9;">
			<input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:22px;display:none;" value="Excel View" />
		</div>
		</td>
		
		
	</tr>
	</table>
	<div style=" position:relative;  width:81%; width:954px\9;  height:20px; height:25px\9;" class="sub-header">
			<span style="position: absolute;">Completed  Detail</span>
							
			</div>
	<table>
	<tr>
	<td valign="top">
		<div>
		<label  class="mandatory-lbl" >Status</label>
		</div>
			<div>
				 <input class="easyui-text" type="text"  id="cmbNmrtStatus"	name="cmbNmrtStatus"   style="width:100px; height: 20px" 
				 value="${requestScope.genTlNearmissreportmst.nmrtStatus}" />
			</div>
	</td>
	<td valign="top" style="padding-left: 20px;">
	<div>
		<label id=lblCompletedBy class="mandatory-lbl">Completed By</label>
		<span style="padding-left: 128px;"><label id=lblCompleteddte class="mandatory-lbl">Completed Date</label></span>
	</div>
	<div>
		<input id="cmbNmrtCompletedby" name="cmbNmrtCompletedby" type="text" class="easyui-text"  tabindex="5"
		style="width: 132px\9; width : 174px;" value="${requestScope.genTlNearmissreportmst.nmrtCompletedby}" />
			
		<span style="padding-left: 33px;">
		<input id="dteNmrtCompleteddate" name="dteNmrtCompleteddate" tabindex="6" type="text" class="easyui-text" 
		style="width: 90px;" value="${requestScope.genTlNearmissreportmst.nmrtCompleteddate}"/></span>
		
	</div>
	</td>
	<tr>
	<td>
	</td>
	<td>
	<div>
	<span id="err_cmbNmrtCompletedby" class="tpm-errormsg" style="padding-left:10px;"></span>
	</div>
	</td>
	<td>
	<div>
	<span id="err_dteNmrtCompleteddate" class="tpm-errormsg" style="margin-left:-100px;"></span>
	</div>
	</td>
	</tr>
	</table>
<table style="float:left;">
<tr>
<td>

	<table id="Actgrid">
			<tr><td></td></tr>
	</table>
		<div id="pageract"></div>

</td>
<td >
    <div  style="margin-left:74px;margin-left:110px\9;">
		<table id="Conditiongrid">
			<tr><td></td></tr>
		</table>
		<div id="pagercondition">	
		</div>	
</div>
</td>
</tr>
<tr>
<td>
	<div>
		<label>Others </label>
		<input type="checkbox" id="chkOthers"  value='Y' value="${requestScope.genTlNearmissreportmst.nmrtOthers}" <c:out value = "${ requestScope.genTlNearmissreportmst.nmrtOthers == '' ? '':'checked'}" />/>
</div>
<div>
		<textarea rows="2" cols="80"  id="txtNmrtOthers"	name="txtNmrtOthers"  maxlength="480" style="width:455px;">${requestScope.genTlNearmissreportmst.nmrtOthers}</textarea>
				 
				 
	</div>
</td>

<td colspan="5">
	<div >
		<label>Others </label>
		<input type="checkbox" id="chkOthersuc"   value='Y' value="${requestScope.genTlNearmissreportmst.nmrtOthersuc}" <c:out value = "${ requestScope.genTlNearmissreportmst.nmrtOthersuc == '' ? '':'checked'}" />/>
</div>
<div>
		<textarea rows="2" cols="80"  id="txtNmrtOthersuc"	name="txtNmrtOthersuc"  maxlength="480" style="width:455px;">${requestScope.genTlNearmissreportmst.nmrtOthersuc}</textarea>
				 
				 
	</div>
</td>

</tr>
</table>
	
			
			
</div>	
</div>


<input type="hidden" id="colNo" value=""/>
<input type="hidden" id="colNo1" value=""/>
		<input type="hidden" id="colVal" value=""/>
		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" />
	<input type="hidden" id="txtNmrtKeyid" name="txtNmrtKeyid" value="${requestScope.genTlNearmissreportmst.nmrtKeyid}" />
    <input type="hidden" id="txtEmployeeid" name="txtEmployeeid" value="${requestScope.empid}" />
    <input type="hidden" id="txttime" name="txttime" value="${requestScope.time}" />
    <input type="hidden" id="txtPreparetime" name="txtPreparetime" value="${requestScope.preparedtime}" />
	<input type="hidden" id="hdnactnpnsts" name="hdnactnpnsts" value="" />
</form>
<input type="hidden" id="hdnLoginId" name="hdnLoginId" value="${requestScope.genTlNearmissreportmst.nmrtIdentifiedby}" />
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />
<input type="hidden" id="hdnReportType" name="hdnReportType" value="${requestScope.formType}" />
<input type="hidden" id="hdnApprovalRequired" name="hdnApprovalRequired" value="${requestScope.approvalRequired}" />

