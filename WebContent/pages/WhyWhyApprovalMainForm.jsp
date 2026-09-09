
<script>
jQuery(document).ready(function(){
    //alert(123);
	initialiseForm('frmWhyWhyApproval');
	var url = jQuery('#hiddenUrl').val();
    jQuery("#submitForm").val("frmWhyWhyApproval");
 	var formId = 'frm' + '<%= request.getAttribute("transCode")%>';
	var empId = jQuery("#" + formId + " input[id=hdnEmployee]").val();
	var refId = jQuery("#" + formId + " input[id=hdnRefId]").val();
	var enable = jQuery("#" + formId + " input[id=hdnWrkFowEnable]").val();
	var refType = jQuery("#" + formId + " input[id=hdnRefType]").val();  
	var transCode = jQuery("#" + formId + " input[id=hdnTransCode]").val();
	var flId = jQuery("#" + formId + " input[id=hdnFlId]").val();
	var refRoleId = jQuery("#" + formId + " input[id=hdnRefRoleId]").val();
	var gridId = "WhyApproveGrid_"+'<%= request.getAttribute("transCode")%>';     
    viewGrid(url,"q=2");
});


function viewGrid(url,filterString)
{	
	var userRole=jQuery("#hdnrolename").val();
		//var roleKeyid=jQuery("#hdnrolekeyid").val();
		var roleKeyid=jQuery("#hdnUserRole").val();
	filterString=filterString+"&roleKeyid="+roleKeyid;
	
		processGridnew(url,filterString,"WhyApproveGrid","WhyApprovePages","","","Whywhy_loadComplete","");
	
}

function Whywhy_loadComplete(){

	var rowIds = jQuery("#WhyApproveGrid").getDataIDs();	
	for(var i=1;i<=rowIds.length;i++){
	 jQuery("#cmbWhyWhyStatus_"+i).attr('disabled',true);
	 jQuery("#btnWhyApproveGrid_"+i).attr('disabled',true); 
	 jQuery("#txtWhyRemarks_"+i).attr('disabled',true);
	 jQuery("#btnApproveGrid_"+i).attr('disabled',true);
	 
	 jQuery("#cmbWhyWhyIsCobd_"+i).attr('disabled',true);
	 jQuery("#txtWhyCobdValue_"+i).attr('disabled',true);
	 jQuery("#txtWhyCobdHours_"+i).attr('disabled',true);

	 
}
	
}
 



function chkFormatter(id, options, rowObject)
{  
	var rowId = options.rowId;
	var colId = options.pos;
	return '<input type="checkbox" id="WhyWhycheckbox_'+rowId+'_'+colId+'" name="WhyWhycheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="" onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
}

function btnSaveAIFormatter(id, options, rowObject)
{	
	var disable=" ";
	var rowId = options.rowId;
	var colId = options.pos;
	
	return '<input type="button" id="btnApproveGrid_'+rowId+'" name="btnApproveGrid_'+rowId+'" onclick="saveWhyApprAI('+rowId+','+colId+')"  style="width:45px;  height:36px;text-align: center;"   class="easyui-button" value="Submit"/>';		
	
}
function btnSavePCFormatter(id, options, rowObject)
{	
	var disable=" ";
	var rowId = options.rowId;
	var colId = options.pos;
	
	return '<input type="button" id="btnApproveGrid_'+rowId+'" name="btnApproveGrid_'+rowId+'" onclick="saveWhyAppr('+rowId+','+colId+')"  style="width:45px;  height:36px;text-align: center;"   class="easyui-button" value="Submit"/>';		
	
}


	function cmbStsFormatter(cellValue,options, rowObject){
		var disable=" ";
		
		var rowId = options.rowId;
		var colId = options.pos;
	
		return '<select class="easyui-text" id="cmbWhyWhyStatus_'+rowId+'" name="cmbWhyWhyStatus_'+rowId+'"  panelHeight=80px;  style="width:  100px; height: 21px;"  >'+
		'<option value="P">Pending</option>'+
		'<option value="A">Accept</option>'+
		'<option value="R">Rework</option>'+
		
	'</select>';
	
	}
	
	function cmbCobdFormatter(cellValue,options, rowObject){
		var disable=" ";
		
		var rowId = options.rowId;
		var colId = options.pos;
	
		return '<select class="easyui-text" id="cmbWhyWhyIsCobd_'+rowId+'" name="cmbWhyWhyIsCobd_'+rowId+'" onchange="onCobdChange(' + rowId + ', this.value)"  panelHeight=80px;  style="width:  100px; height: 21px;"  >'+
		'<option value="" selected>Select</option>'+
		'<option value="Y">Yes</option>'+
		'<option value="N">No</option>'+
		
	'</select>';
	
	}

	function onCobdChange(rowId, value) {
	    console.log("Row Id :", rowId);
	    console.log("Selected Value :", value);

	    if (value === "Y") {
	    	jQuery("#txtWhyCobdValue_"+rowId).attr('disabled',false);
	   	 jQuery("#txtWhyCobdHours_"+rowId).attr('disabled',false);
	   	numericTextBox('txtWhyCobdValue_'+rowId);
	   	numericTextBox('txtWhyCobdHours_'+rowId);
	    } else if (value === "N") {
	    	jQuery("#txtWhyCobdValue_"+rowId).attr('disabled',true);
	   	 jQuery("#txtWhyCobdHours_"+rowId).attr('disabled',true);
	    }
	}
	

function txtRmrkFormatter(id, options, rowObject)
{	
	//alert(" Inside the txtRmrkFormatter");
	
	var rowId = options.rowId;
	var colId = options.pos;
	var columnKey="";
	var color='';
	
	
	var	idval='txtWhyRemarks';
		//return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 150px;text-align:right;" maxlength="2" value="'+rowObject[2]+'" onfocus="gotFocuse('+id+','+columnNo+','+columnKey+')" onChange="outFocus('+id+','+columnNo+','+columnKey+')">';
		return '<textarea class="easyui-text" id="txtWhyRemarks_'+rowId+'" name="txtWhyRemarks_'+rowId+'" style="width: 250px;text-align:left;"   value=""></textarea>';
     
	
	
	
	//return '<input type="button" id="btnApproveGrid_'+rowId+'_'+colId+'" name="btnApproveGrid_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:45px;  height:36px;text-align: center;"   class="easyui-button" value="Submit"/>';		

}
function txtValueFormatter(id, options, rowObject)
{	
	//alert(" Inside the txtRmrkFormatter");
	
	var rowId = options.rowId;
	var colId = options.pos;
	var columnKey="";
	var color='';
	
	
	var	idval='txtWhyCobdValue';
		//return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 150px;text-align:right;" maxlength="2" value="'+rowObject[2]+'" onfocus="gotFocuse('+id+','+columnNo+','+columnKey+')" onChange="outFocus('+id+','+columnNo+','+columnKey+')">';
		//return '<textarea class="easyui-text" id="txtWhyCobdValue_'+rowId+'" name="txtWhyCobdValue_'+rowId+'" style="width: 250px;text-align:left;"   value=""></textarea>';
     return '<input type="text" class="easyui-text" id="txtWhyCobdValue_'+rowId+'" name="txtWhyCobdValue_'+rowId+'"  style="width: 150px;text-align:right;"  value="" >';
	
	
	
	//return '<input type="button" id="btnApproveGrid_'+rowId+'_'+colId+'" name="btnApproveGrid_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:45px;  height:36px;text-align: center;"   class="easyui-button" value="Submit"/>';		

}

function txtHoursFormatter(id, options, rowObject)
{	
	//alert(" Inside the txtRmrkFormatter");
	
	var rowId = options.rowId;
	var colId = options.pos;
	var columnKey="";
	var color='';
	
	
	var	idval='txtWhyCobdHours';
		//return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 150px;text-align:right;" maxlength="2" value="'+rowObject[2]+'" onfocus="gotFocuse('+id+','+columnNo+','+columnKey+')" onChange="outFocus('+id+','+columnNo+','+columnKey+')">';
		//return '<textarea class="easyui-text" id="txtWhyCobdHours_'+rowId+'" name="txtWhyCobdHours_'+rowId+'" style="width: 250px;text-align:left;"   value=""></textarea>';
		return '<input type="text" class="easyui-text" id="txtWhyCobdHours_'+rowId+'" name="txtWhyCobdHours_'+rowId+'"  style="width: 150px;text-align:right;"  value="">';
	
	
	
	//return '<input type="button" id="btnApproveGrid_'+rowId+'_'+colId+'" name="btnApproveGrid_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:45px;  height:36px;text-align: center;"   class="easyui-button" value="Submit"/>';		

}

function btnShowFormatter(id, options, rowObject)
{	
	
	var rowId = options.rowId;
	var colId = options.pos;
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	
		return '<input type="button" id="btnWhyApproveGrid_'+id+'"  name="btnWhyApproveGrid_'+id+'" onclick="WhyWhy('+id+')"  style="width:46px;  height:36px;text-align:center" readonly="readonly"  class="easyui-button" value="View"/>';
}

function saveWhyApprAI(id, colId){
	var rowData = jQuery("#WhyApproveGrid").jqGrid('getRowData',id);
	    var checkVal=jQuery("#WhyApproveGrid").jqGrid('getCell', id,"CHECKVAL");
	    if(checkVal==1){
	//if(jQuery('#WhyWhycheckbox_'+id+'_'+colId+'').is(':checked')==true){
	var rowid = id;//jQuery('#WhyApproveGrid').jqGrid('getGridParam','selrow');
	var rwid=jQuery('#WhyApproveGrid').jqGrid('getGridParam','selrow');
 	if(rowid==null||rowid==""||rowid=='')
		{
 		alert("Select Atleast One Row to Approve ...");
		return false;
		}
	
 	else if(rowid !=null){
			/* var rowData = jQuery("#WhyApproveGrid").jqGrid('getRowData',rowid);
 	
 	    var kaizenDate=jQuery("#WhyApproveGrid").jqGrid('getCell', rowid,"txtWhyRemarks"); 	 */
 		var remark = getFieldValue('txtWhyRemarks_'+rowid);
 	    
 		var status = getFieldValue('cmbWhyWhyStatus_'+rowid);
 		var isCobd = getFieldValue('cmbWhyWhyIsCobd_'+rowid);
 		var cobdValue = getFieldValue('txtWhyCobdValue_'+rowid);
 		var cobdHours = getFieldValue('txtWhyCobdHours_'+rowid);
 		
 		var userRole=jQuery("#hdnrolename").val();
 		//var roleKeyid=jQuery("#hdnrolekeyid").val();
 		var roleKeyid=jQuery("#hdnUserRole").val();
 		
 		var keyid=rowData.KEYID;
 		
 		if(isCobd == "Y"){
 		if(cobdValue == ""){
 			alert("Enter COBD Value.");
 			return;
 		}
 		if(cobdHours == ""){
 			alert("Enter COBD Hours.");
 			return;
 		}
 		}else if(isCobd == ""){
 			alert("Select COBD ");
 			return;
 		}else{
 			cobdValue = 0;
 			cobdHours = 0;
 		}
 	
	 saveForm("frmWhyWhyApproval", "whyWhyApprovalAI_save.whyApr?keyId="+keyid +"&status="+status+"&remark="+remark+"&isCobd="+isCobd+"&cobdValue="+cobdValue+"&cobdHours="+cobdHours+"&roleKeyid="+roleKeyid);

	}
	
  }
	else{
		alert(" Select Any row To Submit .....");
	}
}

function saveWhyAppr(id, colId){
	var rowData = jQuery("#WhyApproveGrid").jqGrid('getRowData',id);
	    var checkVal=jQuery("#WhyApproveGrid").jqGrid('getCell', id,"CHECKVAL");
	    if(checkVal==1){
	//if(jQuery('#WhyWhycheckbox_'+id+'_'+colId+'').is(':checked')==true){
	var rowid = id;//jQuery('#WhyApproveGrid').jqGrid('getGridParam','selrow');
	var rwid=jQuery('#WhyApproveGrid').jqGrid('getGridParam','selrow');
 	if(rowid==null||rowid==""||rowid=='')
		{
 		alert("Select Atleast One Row to Approve ...");
		return false;
		}
	
 	else if(rowid !=null){
			/* var rowData = jQuery("#WhyApproveGrid").jqGrid('getRowData',rowid);
 	
 	    var kaizenDate=jQuery("#WhyApproveGrid").jqGrid('getCell', rowid,"txtWhyRemarks"); 	 */
 		var remark = getFieldValue('txtWhyRemarks_'+rowid);
 	    
 		var status = getFieldValue('cmbWhyWhyStatus_'+rowid);
 		
 		var userRole=jQuery("#hdnrolename").val();
 		//var roleKeyid=jQuery("#hdnrolekeyid").val();
 		var roleKeyid=jQuery("#hdnUserRole").val();
 		
 		var keyid=rowData.KEYID;
 	
	 saveForm("frmWhyWhyApproval", "whyWhyApproval_save.whyApr?keyId="+keyid +"&status="+status+"&remark="+remark+"&roleKeyid="+roleKeyid);

	}
	
  }
	else{
		alert(" Select Any row To Submit .....");
	}
}
function frmWhyWhyApproval_successsCallback(result)
{
	jQuery("#WhyApproveGrid").trigger("reloadGrid");
}

function WhyWhy(id, colId){
	var keyId=jQuery("#WhyApproveGrid").jqGrid('getCell', id,"KEYID");		
	var type="whywhyview";
		LoadPopUp("divIdWhy","whywhyanalysismodify_input.why?keyid="+keyId+"&type="+type+"&hdnMode=View&filterButton=false",true,"95%","90%","1%","1%","","Why Why Analysis");
}

function chkboxCheck(rowId) {
	
	 jQuery("#WhyApproveGrid").jqGrid('setCell', rowId, 'CHECKVAL', '1');
	 
	 var remarkTextBox = "txtWhyRemarks_" + rowId;
	   jQuery("#" + remarkTextBox).on("mousedown keydown click", function(e){
         e.stopPropagation();
     });
	   var statusCombo = "cmbWhyWhyStatus_" + rowId;
		jQuery("#" + statusCombo).on("mousedown keydown click", function(e){
           e.stopPropagation();
       });
		 var cobdCombo = "cmbWhyWhyIsCobd_" + rowId;
			jQuery("#" + cobdCombo).on("mousedown keydown click", function(e){
	           e.stopPropagation();
	       });
			var cobdValue = "txtWhyCobdValue_" + rowId;
			jQuery("#" + cobdValue).on("mousedown keydown click", function(e){
	           e.stopPropagation();
	       });
			var cobdHours = "txtWhyCobdHours_" + rowId;
			jQuery("#" + cobdHours).on("mousedown keydown click", function(e){
	           e.stopPropagation();
	       });

     jQuery("#cmbWhyWhyStatus_"+rowId).attr('disabled',false);
	 jQuery("#btnWhyApproveGrid_"+rowId).attr('disabled',false); 
	 jQuery("#txtWhyRemarks_"+rowId).attr('disabled',false);
	 jQuery("#btnApproveGrid_"+rowId).attr('disabled',false);
	 
	 jQuery("#cmbWhyWhyIsCobd_"+rowId).attr('disabled',false);

}

function chkboxUnCheck(rowId) {
	
	 jQuery("#WhyApproveGrid").jqGrid('setCell', rowId, 'CHECKVAL', '0');
		var approvedId=jQuery("#WhyApproveGrid").jqGrid('getCell', rowId,"txtWhyRemarks_1");
		
	 jQuery("#cmbWhyWhyStatus_"+rowId).attr('disabled',true);
	 jQuery("#btnWhyApproveGrid_"+rowId).attr('disabled',true); 
	 jQuery("#txtWhyRemarks_"+rowId).attr('disabled',true);
	 jQuery("#btnApproveGrid_"+rowId).attr('disabled',true); 
	 jQuery("#cmbWhyWhyIsCobd_"+rowId).attr('disabled',true);
}

function frmSimplifiedKaizenapproval_beforeSubmit(){
	var gridval=getGridSelectArray('WhyApproveGrid');
	var gridData='&paramJsonArrConvert='+gridval;
	if(gridval.trim().length>0)	
		return gridData;
	    else 
	    saveForm("WhyWhycheckbox","WhyWhyAnalysisApproval_save.whyApr?");
	    return false;
}






</script>

<form id="frmWhyWhyApproval">
<div id='wrapperRpt' >
<div style="margin-top: -18px">
<table  id='WhyApproveGrid' >
			<tr>
				<td ></td>
			</tr>
		</table>
		<div id='WhyApprovePages'></div>
		</div>
</div>
<input type="hidden" id="hdnLocation" name="hdnLocation" value="${requestScope.location}">

<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="mode" name="mode" value="create"/>
<input type="hidden" id="hdnEmployee" value="${requestScope.empId}">
<input type="hidden" id="hdnRefId" name="hdnRefId" value="${requestScope.refId}">
<input type="hidden" id="hdnRefType" name="hdnRefType" value="${requestScope.refType}">
<input type="hidden" id="hdnTransCode" name="hdnTransCode" value="${requestScope.transCode}">
<input type="hidden" id="hdnloginuser" name="hdnloginuser" value="${requestScope.loginuser}">
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.rolename}">
<input type="hidden" id="hdnrolekeyid" name="hdnrolekeyid" value="${requestScope.rolekeyid}">

</form>