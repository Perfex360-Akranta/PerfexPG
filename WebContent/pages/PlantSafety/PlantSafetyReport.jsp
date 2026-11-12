
<script type="text/javascript">
var status;
var gridid="PlantSafetyGrid";
jQuery.noConflict();
jQuery(document).ready(function(){
	initialiseForm('frmSafetyReport');
	 formatDateBox('dteFromdate','dd-MMM-yyyy');
	 formatDateBox('dteTodate','dd-MMM-yyyy');
	fillComboBox("frmAuditReport","cmbResponsibleby","employee.commonFilter");
	jQuery('#submitForm').val('frmSafetyReport');
    viewGrid("safetyinspectionReport_input.safetyinspect","q=2&status="+"A"+"&role="+jQuery("#hdnrolename").val());
	
    
    jQuery("#btnview").click(function()
    		{
    	//alert(2);
    	  var status=jQuery("#cmbStatus").combobox('getValue');
    	  var fromdate=jQuery("#dteFromdate").datebox('getValue');
    	  var todate=jQuery("#dteTodate").datebox('getValue');
    	  var type=jQuery("#hdnaudittype").val();
    	  var repons=jQuery("#cmbResponsibleby").combobox('getValue');
    	  processGridnew("safetyinspectionReport_input.safetyinspect","q=2&repons="+repons+"&status="+status+"&fromdate="+fromdate+"&todate="+todate+"&role="+jQuery("#hdnrolename").val(),"PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");

    	
    	});
});


jQuery("#dteTodate").datebox({
	onSelect:function(recordid){
	
	var fromdate=jQuery("#dteFromdate").datebox('getValue');
	var todate=jQuery("#dteTodate").datebox('getValue');

	/*alert(fromdate);
	alert(fromdate.length);
	
	if(fromdate.length==10){
		alert("if"+fromdate.charAt(0));
	if(fromdate.charAt(0)=="1"){
		alert("1");
		fromdate=fromdate.replace(fromdate.charAt(0),"01");
	}
	else if(fromdate.charAt(0)=="2"){
		alert("2");
		fromdate=fromdate.replace(fromdate.charAt(0),"02");
	}
	else if(fromdate.charAt(0)=="3"){
		alert("3");
		fromdate=fromdate.replace(fromdate.charAt(0),"03");

	}
	else if(fromdate.charAt(0)=="4"){
		alert("4");
		fromdate=fromdate.replace(fromdate.charAt(0),"04");

	}
	else if(fromdate.charAt(0)=="5"){
		alert("5");
		fromdate=fromdate.replace(fromdate.charAt(0),"05");

	}
	else if(fromdate.charAt(0)=="6"){
		alert("6");
		fromdate=fromdate.replace(fromdate.charAt(0),"06");

	}
	else if(fromdate.charAt(0)=="7"){
		alert("7");
		fromdate=fromdate.replace(fromdate.charAt(0),"07");

	}
	else if(fromdate.charAt(0)=="8"){
		alert("8");
		fromdate=fromdate.replace(fromdate.charAt(0),"08");

	}
	else if(fromdate.charAt(0)=="9"){
		alert("9");
		fromdate=fromdate.replace(fromdate.charAt(0),"09");
	}
	}
	
	
	if(todate.length==10){
		alert("if"+todate.charAt(0));
	if(todate.charAt(0)=="1"){
		alert("1");
		todate=todate.replace(todate.charAt(0),"01");
	}
	else if(todate.charAt(0)=="2"){
		alert("2");
		todate=todate.replace(todate.charAt(0),"02");
	}
	else if(todate.charAt(0)=="3"){
		alert("3");
		todate=todate.replace(todate.charAt(0),"03");

	}
	else if(todate.charAt(0)=="4"){
		alert("4");
		todate=todate.replace(todate.charAt(0),"04");

	}
	else if(todate.charAt(0)=="5"){
		alert("5");
		todate=todate.replace(todate.charAt(0),"05");

	}
	else if(todate.charAt(0)=="6"){
		alert("6");
		todate=todate.replace(todate.charAt(0),"06");

	}
	else if(todate.charAt(0)=="7"){
		alert("7");
		todate=todate.replace(todate.charAt(0),"07");

	}
	else if(todate.charAt(0)=="8"){
		alert("8");
		todate=todate.replace(todate.charAt(0),"08");

	}
	else if(todate.charAt(0)=="9"){
		alert("9");
		todate=todate.replace(todate.charAt(0),"09");

	}
	

	
	}*/
	
	

	processGridnew("safetyinspectionReport_input.safetyinspect","q=2&fromdate="+fromdate+"&todate="+todate+"&role="+jQuery("#hdnrolename").val(),"PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");

	}
});

jQuery("#cmbStatus").combobox({
	onSelect:function(recordid){			
		
   var status=jQuery("#cmbStatus").combobox('getValue');
   var type=jQuery("#hdnaudittype").val();
   processGridnew("safetyinspectionReport_input.safetyinspect","q=2&status="+status+"&type="+type+"&role="+jQuery("#hdnrolename").val(),"PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");
	  
	}
	});
	
jQuery("#cmbResponsibleby").combobox({
	onSelect:function(recordid){
		
	 var repons=jQuery("#cmbResponsibleby").combobox('getValue');
	
	  var type=jQuery("#hdnaudittype").val();
	 processGridnew("safetyinspectionReport_input.safetyinspect","q=2&repons="+repons+"&status="+"P"+"&role="+jQuery("#hdnrolename").val(),"PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");
	}
});	



function docDoubleClick(id) { 
	//var rowData = jQuery("#list").jqGrid('getRowData',id);
	//var level = rowData.frl_level;
	//var Flid =  rowData.flid;
	//navigateToNextForm('roleteamall_input.roleteam?flid='+Flid+'&level='+level,"Role & Team");
}

function viewGrid(url,dataString)
{
	//alert("dataString"+dataString);
	processGridnew("safetyinspectionReport_input.safetyinspect",dataString,"PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");
}

function getSelectedCellRow(gridid)
{
	return jQuery('table#'+gridId+' tr.selected-row ').index();
}
function getSelectedCellColumn(gridid)
{
	return jQuery('table#'+gridId+' tr.selected-row  > td.ui-state-highlight ').index();
}

/*jQuery("#btnview").click(function(){
	var url = jQuery('#hiddenUrl').val();
	processGridnew("safetyinspectionReport_input.safetyinspect","q=2","PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");
});
*/
/* function viewGrid(url,dataString)
{
	if( validateFilterSelection(dataString))
	{
		var functionalloc=getFilterValue(dataString, "cmbCircle");
		jQuery("#hiddencircle").val(functionalloc);
		var removeBlank = getFilterValue(dataString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		dataString += '&Flid=f&firstClick=Y'+"&status=A";
		processGridnew("safetyinspectionReport_input.safetyinspect",dataString,"PlantSafetyGrid","SafetyGridpager","","nxtgrid","","docDoubleClick");
return true;
} 
return false;		
}*/

function validateFilterSelection(dataString){
  return true;
}

function PlantSafetyGrid_selectRow(id){
	gridId="PlantSafetyGrid";

	var selectval= jQuery("#techocloseGrid").jqGrid('getCell',id,"cb");
	alert("selectval"+selectval);
	var chk=jQuery("#jqg_PlantSafetyGrid_"+id).val();
	alert("chk:::"+chk);
	 if(jQuery('#jqg_'+ gridId +'_'+id).is(':checked'))
			 {
		        //jQuery("#PlantSafetyGrid").jqGrid('setCell', rowId, 'Cb', '1');
		        var chk=jQuery("#jqg_PlantSafetyGrid_"+id).val();
		        alert("chk::"+chk);
			 }
}	
	
</script>
<form id="frmSafetyReport">
<div id="wrapperRpt">
	 	<table style="margin-top:-16px; border-collapse: separate; border-spacing: 8px 0;">
    <tbody>
    <tr style="vertical-align: bottom;">
        <td style="white-space: nowrap;">
            <div style="margin-bottom: 9px;">
                <label style="display:block; margin-bottom: 1px;">Status</label>
                <select id="cmbStatus" name="cmbStatus" style="width:105px;">				
                    <option value='A'>All</option>
                    <option value='P'>Pending</option>
                    <option value='C'>Completed</option>
                </select>
            </div>
        </td>
        <td style="white-space: nowrap; padding-left: 10px;">
            <div style="margin-bottom: 9px;">
                <label style="display:block; margin-bottom: 1px;">Responsible By</label>
                <input id="cmbResponsibleby" name="cmbResponsibleby" type="text" class="easyui-combobox" style="width: 180px; margin-left: 0px;" value="">
            </div>
        </td>
        <td style="white-space: nowrap; padding-left: 10px;">
            <div style="margin-bottom: 9px;">
                <label style="display:block; margin-bottom: 1px;">From Date</label>
                <input class="easyui-text" style="width: 87px;" id="dteFromdate" name="dteFromdate" value=""/>
            </div>
        </td>
        <td style="white-space: nowrap; padding-left: 10px;">
            <div style="margin-bottom: 9px;">
                <label style="display:block; margin-bottom: 1px;">To Date</label>
                <input class="easyui-text" style="width: 87px;" id="dteTodate" name="dteTodate" value=""/>
            </div>
        </td>
        <td style="white-space: nowrap; padding-left: 10px;">
            <div style="margin-bottom: 9px;">
                <label style="display:block; margin-bottom: 1px;">&nbsp;</label>
                <input type="button" class="easyui-button" value="View" id="btnview" style="width: 70px;">
            </div>
        </td>
    </tr>
    </tbody>
</table>
	 <table id="PlantSafetyGrid"><tr><td/></tr></table>
	 <div id="SafetyGridpager"></div>
<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="mode" value="">
<input type="hidden" id="hdnLoginId" name="hdnLoginId" value="${requestScope.loginUser}" >
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.role}" >
</form>