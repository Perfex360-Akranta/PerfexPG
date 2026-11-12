
<script type="text/javascript">

jQuery(document).ready(function(){
	var url = "safetyinspectiondetails_input.safetyinspect";
	var formtype =jQuery('#frmmode').val();
	
	var glbval=null;
	jQuery( "#HdResponsibilityPopup" ).hide();
	jQuery( "#HdResponsibilityPopupMain" ).hide();
	//alert(jQuery("#hdnrole").val());
	var role=jQuery("#hdnrole").val();
	if(formtype=="modify"||formtype=="delete"||formtype=="view")
	{
	setLoadFormCallBackFrmId("frmSafetyInspectList");
	invokeAfterLoadFormCallBack();
	}
	
	
	
	setTimeout(function(){
	
	viewGrid(url,"?q=2&mode="+formtype+"&role="+role);
	},100);
	
	if(formtype=="Response")
	{	//disableField("frmSafetyInspectList", "hdnAssignValue");
		
	    jQuery( "#hdnAssignValue" ).show();
		jQuery( "#hdnselect" ).hide();
		jQuery( "#hdnselectlbl" ).show();
		//jQuery('#hdnAssignValue').disable(true); 
	}else{
	jQuery( "#hdnAssignValue" ).hide();
	jQuery( "#hdnselect" ).hide();
	jQuery( "#hdnselectlbl" ).hide();}
});
	
function frmSafetyInspectList_afterLoadCallBack(){
	toggleCommonFilter();	
}


function viewGrid(url,dataString)
{
	if( validateFilterSelection(dataString))
	{
		var functionalloc=getFilterValue(dataString, "cmbCircle");
		jQuery("#hiddencircle").val(functionalloc);
		var removeBlank = getFilterValue(dataString, "chkRemoveBlank");
		var role=jQuery("#hdnrole").val();
		var mode =jQuery('#frmmode').val();
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		dataString += '&Flid=f&firstClick=Y'+"&mode="+mode+"&role="+role;
		processGridnew("safetyinspectiondetails_input.safetyinspect",dataString,"safetyinspectgrid","safetyinspectpager","","doubleClickGrid","","loadComplete","","");
	    
return true;
}
return false;		
}

function validateFilterSelection(dataString){
  return true;
}

function safetyinspectgrid_selectRow(rowId){
	var mode = jQuery('#frmmode').val();
	
	
	if (mode=="delete"){
	deleteItem(rowId);
	}
	if (mode=="Response"){
	jQuery('#btnMultiple').hide();
		/*	var gridId="safetyinspectgrid";
		var reschek = jQuery('#jqg_'+gridId+'_'+rowId).is(':checked');
		 var dtlkeyid=jQuery("#safetyinspectgrid").jqGrid('getCell',rowId,"DocumentNo");
		// alert(dtlkeyid);
		 setFieldValue('hdnResVal',dtlkeyid);
		// alert("dtlkeyid OK");
		if(reschek == true){
		fillComboBox("HdResponsibilityPopup","cmbResponseid","employee.commonFilter");
		formatDateBox('dteTargetdate','dd-MMM-yyyy');
		fillWithCurrentDate('dteTargetdate');
		jQuery( "#HdResponsibilityPopupMain" ).show();
		jQuery( "#HdResponsibilityPopupMain" ).dialog({
			autoOpen: true,
			show: "blind",
			hide: "explode",
			height: 245,
			width: 350,
			left:420,
			top:150,	
			modal: true,
			title:'Responsibility and Target Date for Audit'
	});
		}else
			{
			return false;
			}
		*/
		//var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
		  var o=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"observations");
		   var r=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"responsibility");
		 //  alert(o);
	    jQuery('input[id=hdnresob]').val("o");		  
		   

			var selArray =  jQuery("#safetyinspectgrid").jqGrid('getGridParam', 'selarrrow');
			var selrowid="";
			 var jsonArr='';
		  if(selArray !=null && selArray!=" " && selArray!=""){
			for(var i=0;i<selArray.length;i++)
			{
			selrowid=selArray[i];
		        var Keyid =jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"dtlkeyid");
		   
	  var fl=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"flid");
	  var Apm=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"ACTIONPLANNO");
	  var Apd=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"ACTIONPLANDTL");
		   var Criteriasplit= Keyid.split(',');
			 	for(var k=0;k<Criteriasplit.length;k++){
				 	var Keyidval=Criteriasplit[k]; 
				 	var keyvalSplit=Keyidval.split(";");
				 	var keyvalu=keyvalSplit[0];
				 	jsonArr += '"'+keyvalu + '",';	
		 	    }
			 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
				jsonArr += ',';
			}	
			jsonArr = jsonArr.substring(0,jsonArr.length-1);
		    var U=jsonArr;
		  
		    if(U!=null)
	   	{
	   	 LoadPopUp("loadAuditverifyResponse","AuditDetailsgridpopup_input.safetyinspect?q=2&U="+U+"&fl="+fl+"&m="+Apm+"&d="+Apd, true,"50%","40%","30%","30%", "","PlantSafety Responsibilty"," "," " );
		}
		  }
	   }
	
if(mode=="Closure"){
	var rolename = jQuery("rolename").val();
	var Aplmmst=null;
	var AplmDtl=null;
	var Abn=null;
	var gridId="safetyinspectgrid";
	if(jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked'))
	   {
		  var UpdateList =jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"dtlkeyid");
		  var flid=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"flid");
		  var Keyid =jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"mstkeyid");
		  var selArray =  jQuery("#safetyinspectgrid").jqGrid('getGridParam', 'selarrrow');
		  var Abnormality=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"ABNM_KEYID");
		  
		   var  Aplmmst =jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"ACTIONPLANNO");
			 
		   var   AplmDtl=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"ACTIONPLANDTL");
		   var Suggestion=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"KZBN_KEYID");
		// alert("Suggstion"+Suggstion);
		   if(Abnormality.length<=1){
			   Abnormality="X";
			 
	    	} if(AplmDtl.length<=1){
	    		AplmDtl="X";
	    		Aplmmst="X";
	    	}
	    	 if(Suggestion.length<=1){
	    		
	    		Suggestion="X";
	    	
	    	}
		
		    	 LoadPopUp("loadAuditclosure","AuditDetailsClosurepopup_input.safetyinspect?q=2&UpdateList="+UpdateList+"&flid="+flid+"&Suggestion="+Suggestion+"&rolename="+rolename+"&abnormality="+Abnormality+"&Aplmmst="+Aplmmst+"&AplmDtl="+AplmDtl,  true,"58%","45%","30%","30%", "","Audit Closure"," "," " );
		        }
		    
		  
	   
	   }	


if(mode=="verify"){
	
	var gridId="safetyinspectgrid";
	   if(jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked'))
	   {
		  var DetailKeyid =jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"dtlkeyid");
		  var flid=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"flid");
		  var Keyid =jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"mstkeyid");
		  var selArray =  jQuery("#safetyinspectgrid").jqGrid('getGridParam', 'selarrrow');
		  var selrowid="";
		  var jsonArr='';
		  if(selArray !=null && selArray!=" " && selArray!=""){
		   for(var i=0;i<selArray.length;i++){
		     selrowid=selArray[i];
		       var Keyid =jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"dtlkeyid");
			   var Criteriasplit= Keyid.split(',');
			 	for(var k=0;k<Criteriasplit.length;k++){
				 	var Keyidval=Criteriasplit[k]; 
				 	var keyvalSplit=Keyidval.split(";");
				 	var keyvalu=keyvalSplit[0];
				 	jsonArr += '"'+keyvalu + '",';	
		 	    }
			 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
				jsonArr += ',';
			}	
			jsonArr = jsonArr.substring(0,jsonArr.length-1);
		    var UpdateList=jsonArr;
		  
		    if(UpdateList!=null)
		    	{
		    	 LoadPopUp("loadAuditverify","plantsafetyVerifypopup_input.safetyinspect?q=2&UpdateList="+UpdateList+"&flid="+flid, true,"50%","40%","30%","30%", "","PlantSafety Verification"," "," " );
		        }
		  }
	   }
	
}
 
}


function loadAuditverify_afterClose()
{
	//alert(1);
	jQuery("#safetyinspectgrid").trigger("reloadGrid");
	
}
       
function loadAuditclosure_afterClose()
{
	//alert(1);
	jQuery("#safetyinspectgrid").trigger("reloadGrid");
	
}


function loadAuditverifyResponse_afterClose()
{
	//alert(1);
	jQuery("#safetyinspectgrid").trigger("reloadGrid");
	
}
function deleteItem(rowId) {
	/*if(mode=='delete'){
		var rowData = jQuery("#safetyinspectgrid").jqGrid('getRowData');
		 keyid = rowData.DetailKeyid;
		 alert(keyid);
		if (confirm("Are you Sure to Delete this Record ?")) {
    	processAjaxCalls("safetyinspection_delete.safetyinspect?&keyid="+keyid+"&mode="+mode,"","deleteSuccessCallback","");
    }
    return false;
} */
		var gridId="safetyinspectgrid";
		if(jQuery('#jqg_'+gridId+'_'+rowId).is(':checked'))
			//var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs(); 
			//jQuery("#safetyinspectgrid").jqGrid('setCell', rowId, '1', '1');
		     var dtlkeyid=jQuery("#safetyinspectgrid").jqGrid('getCell',rowId,"DocumentNo");
		//	 alert("in delete :"+dtlkeyid);
		     var chkhide=jQuery("#safetyinspectgrid").jqGrid('getCell',rowId,"Verified?");
		     if(chkhide=='Yes'){
		    	 alert("The Audit Data Already Verified");
		    	 jQuery('#jqg_'+gridId+'_'+rowId).attr("disabled",true);
		    	 return false;
		     }
			else{
					var r=confirm("Are You Sure to Delete?");
					if(r){
					processAjaxCalls("safetyinspectiondtl_delete.safetyinspect?&dtlid="+dtlkeyid,"","deleteSuccessCallback","errorCallBack");	
					 jQuery("#safetyinspectgrid").trigger("reloadGrid");	
					 alert("Data Deleted Successfully");	
					return true;
					}
					else{
						return false;
				}
				}	 

}
function loadComplete(){
	//hideJqGridRow("safetyinspectgrid", "1");
}
function frmSafetyInspectList_deleteSuccessCallback(reslut){
//	alert("delete success");
	var msg = result.successData.msg;
	//alert(msg);
	jQuery("#safetyinspectgrid").trigger("reloadGrid");	
}

jQuery('#btnMultiple').click(function(){
/*	var mode = jQuery('#frmmode').val();
	if(mode=="Response"){
	var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
	var gridId="safetyinspectgrid";
	//var chkval=jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked');
	if(glbval==true)	
	loadResponsibility();
	else 
	alert("Select any row to Assign Responsibility");
	} });
*/
	/*	var selArray =  jQuery("#safetyinspectgrid").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
	  	if(selArray !=null && selArray!=" " && selArray!=""){
		for(var i=0;i<selArray.length;i++){
	       selrowid=selArray[i];
	       var Keyid =jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"dtlkeyid");
		   var flid=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"flid");
	       var Masterkeyid=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"mstkeyid");
	       var observe=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"observations");
	       var Criteriasplit= Keyid.split(',');
		 	for(var k=0;k<Criteriasplit.length;k++){
			 	var Keyidval=Criteriasplit[k]; 
			 	var keyvalSplit=Keyidval.split(";");
			 	var keyvalu=keyvalSplit[0];
			 	jsonArr += '"'+keyvalu + '",';	
	 	    }
		 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
			jsonArr += ',';
		}	
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
	    var UpdateList=jsonArr;
	  
	    if(UpdateList!=null)
	    	{
	    	 LoadPopUp("loadAuditReponse","AuditDetailsgridpopup_input.safetyinspect?q=2&UpdateList="+UpdateList+"&flid="+flid+"&mstkeyid="+Masterkeyid+"&observe="+observe, true,"50%","40%","30%","30%", "","Audit Responsibility"," "," " );
	        }
	  }  */
/*	  
	  var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
	  var o=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"observations");
	   var r=jQuery("#safetyinspectgrid").jqGrid('getCell', rowId,"responsibility");
	   alert(o);
    jQuery('input[id=hdnresob]').val("o");		  
	   

		var selArray =  jQuery("#safetyinspectgrid").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
	  if(selArray !=null && selArray!=" " && selArray!=""){
		for(var i=0;i<selArray.length;i++)
		{
		selrowid=selArray[i];
	        var Keyid =jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"dtlkeyid");
	   
  var fl=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"flid");
  var Apm=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"ACTIONPLANNO");
  var Apd=jQuery("#safetyinspectgrid").jqGrid('getCell', selrowid,"ACTIONPLANDTL");
	   var Criteriasplit= Keyid.split(',');
		 	for(var k=0;k<Criteriasplit.length;k++){
			 	var Keyidval=Criteriasplit[k]; 
			 	var keyvalSplit=Keyidval.split(";");
			 	var keyvalu=keyvalSplit[0];
			 	jsonArr += '"'+keyvalu + '",';	
	 	    }
		 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
			jsonArr += ',';
		}	
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
	    var U=jsonArr;
	  
	    if(U!=null)
   	{
   	 LoadPopUp("loadAuditverifyResponse","AuditDetailsgridpopup_input.safetyinspect?q=2&U="+U+"&fl="+fl+"&m="+Apm+"&d="+Apd, true,"50%","40%","30%","30%", "","Audit Responsibilty"," "," " );
	}
	  }  */
	});

jQuery('#btnOk').click(function()
		{
	var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
	var mode = jQuery('#frmmode').val();
	var dtlid = jQuery('#hdnResVal').val();
//alert(dtlid);
	var verifiedid=getComboBoxText("cmbVerResponsibilityid");	
//	alert(verifiedid);
	var verifiedbyId=jQuery('#cmbVerResponsibilityid').combobox('getValue');
//	alert(verifiedbyId);
	var verifiedDate=getFieldValue('dteVerTargetdate');
//	alert(verifiedDate);
	if(verifiedbyId.trim().length<= 0)
	{
		alert("Select Responsibility");
		return false;
	}
	else if(verifiedDate==null||verifiedDate.trim()=="")
	{
		alert("Enter Date");
		return false;
	}else 
	{
			setTimeout(function() {
	            jQuery( "#HdResponsibilityPopup" ).hide();
	            jQuery("#HdResponsibilityPopup").dialog("close");
	        },100);
			processAjaxCalls("safetyinspection_verify.safetyinspect?dtlid="+dtlid+"&Verid="+verifiedbyId+"&mode="+mode+"&verifydate="+verifiedDate,"","verifyupdatesuccessCallBack","");	
			jQuery("#safetyinspectgrid").trigger("reloadGrid");
		}
			//{applyRestoAll(responsibilityId,responsibility,targetDate);}
		});
jQuery('#btnResOk').click(function()
		{	
			var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
			var mode = jQuery('#frmmode').val();
			var dtlid = jQuery('#hdnResVal').val();
	
			var responsibility=getComboBoxText("cmbResponseid");	
	//		alert(responsibility);
			var responsibilityId=jQuery('#cmbResponseid').combobox('getValue');
	//		alert(responsibilityId);
			var targetDate=getFieldValue('dteTargetdate');
//			alert(targetDate);
			
			//alert("resId : "+responsibilityId+" targetDate : "+targetDate);
			if(responsibilityId.trim().length<= 0)
			{
				alert("Select Responsibility");
				return false;
			}
			else if(targetDate==null||targetDate.trim()=="")
			{
				alert("Enter Date");
				return false;
			}
			else 
			{
			//navigateToNextForm("safetyinspection_update.safetyinspect?dtlid="+dtlid+"Resid="+responsibilityId+"&mode="+mode+"&targetdate="+targetDate);
				setTimeout(function() {
		            jQuery( "#HdResponsibilityPopupMain" ).hide();
		            jQuery("#HdResponsibilityPopupMain").dialog("close");
		        },100);
				processAjaxCalls("safetyinspection_update.safetyinspect?dtlid="+dtlid+"&Resid="+responsibilityId+"&mode="+mode+"&targetdate="+targetDate,"","resupdatesuccessCallBack","");	
				jQuery("#safetyinspectgrid").trigger("reloadGrid");
			}
			//navigateToNextForm("safetyinspection_update.safetyinspect?keyid="+keyid+"&mode="+mode+"&audittype="+audittype);
		});
jQuery('#btnResCancel').click(function()
		{
	 jQuery("#HdResponsibilityPopupMain").dialog("close");
	//setTimeout(function() {jQuery("#HdResponsibilityPopupMain").dialog("close");},1000);
	//var rowId = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
	//jQuery('#jqg_safetyinspectgrid_'+rowId).attr('checked', false);
		});
		
function frmSafetyInspectList_resupdatesuccessCallBack(result){
		 var msg = result.successData.msg; 
		 alert(msg);
		}
function frmSafetyInspectList_verifyupdatesuccessCallBack(result){
	 var msg = result.successData.msg; 
	 alert(msg);
	}
function applyRestoAll(responsibilityId,responsibility,targetDate,applyAll)
{
	var rowIds = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
	checkAll(rowIds,true);
	for(var i=0;i<rowIds.length;i++)
	{
		if(applyAll==false)
		{
			//jQuery('#safetyinspectgrid').setCell(rowIds[i],"cmbkhdmResponsibilityid"," ");
			jQuery('#safetyinspectgrid').setCell(rowIds[i],"Responsibility"," ");
			jQuery('#safetyinspectgrid').setCell(rowIds[i],"TargetDate"," ");
		}
		else
		{		
			//jQuery('#safetyinspectgrid').setCell(rowIds[i],"cmbkhdmResponsibilityid",responsibilityId);
			jQuery('#safetyinspectgrid').setCell(rowIds[i],"Responsibility",responsibility);
			jQuery('#safetyinspectgrid').setCell(rowIds[i],"TargetDate",targetDate);
		}
	}
}	
jQuery('#chkSelectAll').click(function(){
	    var rowIds = jQuery('#safetyinspectgrid').jqGrid().getDataIDs();
			if(jQuery('#chkSelectAll').is(':checked'))
				checkAll(rowIds,true);
			else
				checkAll(rowIds,false);
		});
function checkAll(rowIds,para)
{ 
	for(var i=0;i<rowIds.length;i++)
	{   gridId='safetyinspectgrid';
		//jQuery('#hd_checkbox_'+rowIds[i]).attr({'checked':para});
		jQuery('#jqg_'+ gridId +'_'+rowIds[i]).attr({'checked':para});
		if(para==true)
			jQuery("#safetyinspectgrid").setCell(rowIds[i],"txtselectionFlag","INSERT");
		else 
			jQuery("#safetyinspectgrid").setCell(rowIds[i],"txtselectionFlag"," ");
	}
}
function loadResponsibility()
{
	//alert("within loadResponsibility start");
	//jQuery("#cmbkhdmResponsibilityid").combobox('clear');
LoadPopUp("divRoleMapping","safetyPopup_input.safetyinspect", true,"80%","70%","10%","10%", "","plantSafety Responsibility"," "," " );
	/*	fillComboBox("HdResponsibilityPopup","cmbkhdmResponsibilityid","employee.commonFilter");
	formatDateBox('dtekhdmTargetdate','dd-MMM-yyyy');
	fillWithCurrentDate('dtekhdmTargetdate');
	jQuery( "#HdResponsibilityPopup" ).show();
	jQuery( "#HdResponsibilityPopup" ).dialog({
		autoOpen: false,
		show: "blind",
		hide: "explode",
		height: 245,
		width: 350,
		left:420,
		top:150,	
		modal: true,
		title:'Responsibility and Target Date for Audit'
}); */	
}

/*function dailyauditgrid_selectRow(rowId)
{
	var gridId="dailyauditgrid";
	 
	if(jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked'))
	 {
       
       alert("INSIDE THE checkbox");
       //var selArray =  jQuery("#techocloseGrid").jqGrid('getGridParam', 'selarrrow');
       var Keyid =jQuery("#dailyauditgrid").jqGrid('getCell', rowId,"DTLD");
       alert("Keyid::"+Keyid);
       
  
       LoadPopUp("loadAuditReponse","dailyAuditpopup_input.dailyaudit?q=2&Keyid="+Keyid, true,"50%","40%","30%","30%", "","Audit Responsibility"," "," " );
	 }

}
*/



function doubleClickGrid(rowid) 
{ 
	var mode = jQuery('#frmmode').val();
	//alert(mode);
	if(mode=='modify' || mode == 'delete' || mode == 'view'){
	var rowData = jQuery("#safetyinspectgrid").jqGrid('getRowData',rowid );
	
	var keyid = rowData.mstkeyid;
	//alert("keyid::"+keyid);
	var audittype = rowData.audittype;
//alert("audittype::"+audittype);
	var flid = rowData.flid;
	//alert("flid:::"+flid);
// LoadPopUp("DivSafety","safetyinspection_input.safetyinspect?keyid="+keyid+"&mode="+mode+"&audittype="+audittype+"&auditflid="+flid,true,"1100px","550px","4px","4%", "multiSelectOk_Callback","Plant Safety Inspection",false,true);	
 navigateToNextForm("safetyinspection_input.safetyinspect?keyid="+keyid+"&mode="+mode+"&audittype="+audittype+"&auditflid="+flid+"&filterButton=false");
}
}
</script>
<form name="frmSafetyInspectList" id="frmSafetyInspectList" >
<div id="wrapperRpt"  >
	<!--<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
		<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
		<label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label>
	</div>
	-->
	<div style="clear: both;"></div> 
			   <div id="HdResponsibilityPopup"  >
			
			<div class="easyui-paddingbfpx "; style="margin-left: 20px;"><label>Verified By </label></div> 
			    <div class="easyui-paddingbfpx"style="margin-left: 20px;"> 
				    <input id="cmbVerResponsibilityid" name="cmbVerResponsibilityid" class="easyui-combobox"  style="width:255px;" value=""/>
				 <span id="err_cmbVerResponsibilityid" class="tpm-errormsg" style=""></span> 
				</div>
				<div class="easyui-paddingbfpx"style="margin-left: 20px;">
					<label>Verified Date</label>
<!--					<span style="padding-left:70px;"><label>Status</label></span>-->
				</div> 
				<div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
				    <input id="dteVerTargetdate" name="dteVerTargetdate" class="easyui-datebox" style="width:110px;" />
				    <span style="padding-left:20px;">
				       
				    </span> 
				</div>
				<div class="easyui-paddingbfpx"style="margin-left: 100px;margin-top: 40px;">
					<input id="btnOk" name="btnOk" class="easyui-button" type="button" value ="Ok" />
					<input id="btnCancel" name="btnCancel" class="easyui-button" type="button" value="Cancel" />
				</div>
			   
			   </div>
			   
			   <div style="clear: both;"></div> 
			   <div id="HdResponsibilityPopupMain"  >
			
			<div class="easyui-paddingbfpx "; style="margin-left: 20px;"><label>Responsibility By </label></div> 
			    <div class="easyui-paddingbfpx"style="margin-left: 20px;"> 
				    <input id="cmbResponseid" name="cmbResponseid" class="easyui-combobox"  style="width:255px;" value=""/>
				 <span id="err_cmbResponseid" class="tpm-errormsg" style=""></span> 
				</div>
				<div class="easyui-paddingbfpx"style="margin-left: 20px;">
					<label>Target To</label>
<!--					<span style="padding-left:70px;"><label>Status</label></span>-->
				</div> 
				<div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
				    <input id="dteTargetdate" name="dteTargetdate" class="easyui-datebox" style="width:110px;" />
				    <span style="padding-left:20px;">
				       
				    </span> 
				</div>
				<div class="easyui-paddingbfpx"style="margin-left: 100px;margin-top: 40px;">
					<input id="btnResOk" name="btnResOk" class="easyui-button" type="button" value ="Ok" />
					<input id="btnResCancel" name="btnResCancel" class="easyui-button" type="button" value="Cancel" />
				</div>
			   
			   </div>
			   <div id="hdnselect"  class="floatleft" style=" padding-right: 35%">
			   				<input type="checkbox" id="chkSelectAll" name="chkSelectAll" />
			   				<span id="hdnselectlbl" style="margin-top:-10px;">
			   				<label>Select All</label>
			   				</span>
			   				
			   				</div>
	 <div id="hdnAssignValue" style="margin-left:1050px;">
	 <input id="btnMultiple" name="btnMultiple" class="easyui-button" type="button" value ="Apply Responsibility" style="width:150px; height:20px;display:none;"/>
	 <!--  <input id="btnMultiple" name="btnMultiple" class="easyui-button" type="button" value ="Single Assign" style="width:150px;height:20px;"/> --></div>
	<table id="safetyinspectgrid"></table>
	<div id="safetyinspectpager"></div>
</div>
<input type="hidden" id="frmmode" name="frmmode" value="${requestScope.type}"></input>
<input type="hidden" id="hdnSelectedVal" name="hdnSelectedVal" value=""/>
<input type="hidden" id="hdnResVal" name="hdnResVal" value=""/>
<input type="hidden" id="hdnresob" name="hdnresob" value=" "></input>
<input type="hidden" id="rolename" name="rolename" value="${requestScope.rolename}"/>
<input type="hidden" id="hdnrole" name="hdnrole" value="${requestScope.role}"/>

</form>