<script>
jQuery(document).ready(function(){
	 initialiseForm('repeatedAbn');
	var url = jQuery('#hiddenUrl').val();
	var data = url.split("?");
	url = data[0];
	var filterSreing = data[1];
	 processGridnew(url, filterSreing, "tblrepeatedAbn", "repAbnPager", "", "", "","load_complete","selectRowFunction");
	// processGridnew("entAddEmployee_input.tfl","?q=2&erdlKeyid="+erdlKeyid,"grdAddEmployee","grdAddEmployeePager","","","","addEmploadComplete","EmpConfigselectRowFunction");
	 
});

function tblrepeatedAbn_selectAll(id,status){
	
	var row = jQuery("#tblrepeatedAbn").jqGrid('getDataIDs');
	for(var i=0 ;i<row.length;i++){

			jQuery('#jqg_tblrepeatedAbn_'+row[i]).attr('checked', false);
	 }

}


function tblrepeatedAbn_selectRow(id){
	
	var row = jQuery("#tblrepeatedAbn").jqGrid('getDataIDs');
	for(var i=0 ;i<row.length;i++){
		if(row[i]!= id)
			jQuery('#jqg_tblrepeatedAbn_'+row[i]).attr('checked', false);
	 }

	if(jQuery('#jqg_tblrepeatedAbn_'+id).is(':checked')){		
		chkboxCheck(id);
	}
	else{		
		chkboxUnCheck(id);
	}
}
function chkboxCheck(rowId)
{	
	var rowData = jQuery("#tblrepeatedAbn").jqGrid('getRowData',rowId);
	
	var repabn = rowData.ABNId;
	
	var selId = rowData.Description;
		jQuery("#hdnAbnmRepeatedabn").val(repabn);
		jQuery("#hdntxtAbnmRemarks").val(selId);
}
function chkboxUnCheck(rowId)
{
	jQuery("#hdntxtAbnmRemarks").val("");
}
jQuery("#btnOk").click(function(){
	
	var remarks = jQuery("#hdntxtAbnmRemarks").val();
	//jQuery("#txtAbnmDescription").val(remarks);
	if(remarks.trim()=="")
		jQuery("#chkRepAbn").attr('checked',false);
	else
		jQuery("#chkRepAbn").attr('checked',true);
	closePopUpDialoge("RepAbn");	
});

</script>
<form id="repeatedAbn">
<table id="tblrepeatedAbn"></table>
<div id="repAbnPager"></div>

<div align="center"><input type="button" id="btnOk" value="OK" class="easyui-button"></div>
<input type="hidden" id="hdntxtAbnmRemarks" value=""/>
<input type="hidden" id="hdnAbnmRepeatedabn" value=""/>
</form>