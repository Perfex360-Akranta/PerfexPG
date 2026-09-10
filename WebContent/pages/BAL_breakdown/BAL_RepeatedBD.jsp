<script>
jQuery(document).ready(function(){
	 initialiseForm('frmRepeatedBD');
	var url = "repeatedBD_input.brdn";
	
	var filterString = "machineId=" + jQuery('#hdnRptBdMachineId').val();
	
	 processGridnew(url, filterString, "grdRepeatedBd", "pgrRepeatedBd");
	// processGridnew("entAddEmployee_input.tfl","?q=2&erdlKeyid="+erdlKeyid,"grdAddEmployee","grdAddEmployeePager","","","","addEmploadComplete","EmpConfigselectRowFunction");
	 
});



function repeatedBd_selectFormater(cellValue,options,rowObject){

	var gridId = options.gid;
	var rowId= options.rowId;
	var fieldID ="chkSelect_" + gridId+"_"+rowId ;
	return '<input id="'+fieldID+'" name="'+fieldID+'" value="Y" type="checkbox" onclick="if(this.checked){grdRepeatedBd_selectRow(\''+ rowId + '\',\''+ gridId +'\');}" style="text-align:center;"/>';
}

function grdRepeatedBd_selectRow(id){
	
	
	jQuery('input[id^=chkSelect_]').not('#chkSelect_grdRepeatedBd_'+id).attr('checked', false);
	

	if(jQuery('#chkSelect_grdRepeatedBd_'+id).is(':checked')){		
		chkboxCheck(id);
	}
	else{		
		chkboxUnCheck(id);
	}
}
function chkboxCheck(rowId)
{	
	var rowData = jQuery("#grdRepeatedBd").jqGrid('getRowData',rowId);
	jQuery("#hdnRptRootCause").val(rowData.ROOTCAUSE);
	jQuery("#hdnRptCounterMeasure").val(rowData.COUNTERMEASURE);
	jQuery("#hdnRptBDNO").val(rowData.BDNO);
	jQuery("#hdnRptWHYID").val(rowData.WHYWHYKEYID);
}	
function chkboxUnCheck(rowId)
{
	jQuery("#txabdanRootcause").val('');
	jQuery("#txabdanCountermeasure").val("");
	jQuery("#hdnRptBDNO").val("");
	jQuery("#hdnRptWHYID").val('');
}
jQuery("#btnOk").click(function(){
	
	var rootCause = jQuery("#hdnRptRootCause").val();
	var cntMeasr = jQuery("#hdnRptCounterMeasure").val();
	jQuery("#txabdanRootcause").val(rootCause);
	jQuery("#txabdanCountermeasure").val(cntMeasr);
	jQuery("#txtbdmsRepeatedbdno").val(jQuery("#hdnRptBDNO").val());
	jQuery("#hdnBdmsRepeatedbdWhWhyNo").val(jQuery("#hdnRptWHYID").val());
	closePopUpDialoge("divRepeatedBd");	
});

</script>
<form id="frmRepeatedBD">
<table id="grdRepeatedBd"></table>
<div id="pgrRepeatedBd"></div>

<div align="center"><input type="button" id="btnOk" value="OK" class="easyui-button" style="width: 200px;margin-top:10px"></div>
<input type="hidden" id="hdnRptBdMachineId" value="${requestScope.machineId}"/>
<input type="hidden" id="hdnRptRootCause" value=""/>
<input type="hidden" id="hdnRptCounterMeasure" value=""/>
<input type="hidden" id="hdnRptBDNO" value=""/>
<input type="hidden" id="hdnRptWHYID" value=""/>
</form>