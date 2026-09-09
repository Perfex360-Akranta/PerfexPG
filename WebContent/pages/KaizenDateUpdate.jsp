<script>
jQuery(document).ready(function(){
	 initialiseForm('frmKaizenDateUpdate');
	 
	 var url = jQuery('#hiddenUrl').val();
	// alert(url);
	 viewGrid(url,"q=2");
	////// var url = jQuery('#hiddenUrl').val();
	// alert("Url:" + url);
	 ////jQuery('#submitForm').val('frmKaizenDateUpdate');
	// processGridnew("kaizenDateUpdateData_input.kaizen", "q=2", "tblKaizenDateUpdate", "abnAllocationPager", "", "", "","","");
	 //processGridnew(url, "?q=2&cmbdetectedBy="+jQuery("#hdnLoginId").val(), "tblKaizenDateUpdate", "abnAllocationPager", "", "", "","","");
	   //processGridnew(url, "?q=2&cmbdetectedBy="+jQuery("#hdnLoginId").val(), "tblKaizenDateUpdate", "abnAllocationPager", "", "", "","load_complete","selectRowFunction");
});


function submitBtn(id, options, rowObject){
	//alert(options.rowId+'_'+options.pos);
	return '<input type="button" id="btnSubmit_'+options.rowId+'_'+options.pos+'" onclick=updateKaizenDate("'+options.rowId+'"); name="btntblKaizenDateUpdate_'+options.rowId+'_'+options.pos+'"    style="width:80px;  height:23px;"   class="easyui-button" value="Update"/>';
}
//else{chkboxUnCheck(\''+ rowId +'\');}
function checkBoxKzn(id, options, rowObject){
	return '<input type="checkbox" id="chkBox_'+options.rowId+'_'+options.pos+'" name="chk_'+options.rowId+'_'+options.pos+'" onclick="if(this.checked){chkkaizenxboxCheck(\''+options.rowId +'\');}"  style="width:80px;  height:23px;"   class="easyui-button" value=""/>';
}

function chkkaizenxboxCheck(row){
	var respId=jQuery("#tblKaizenDateUpdate").jqGrid('getCell', row,"ResponsibilityId");
    setFieldValue("cmbResponsibility_"+row,respId);
    fillComboBox("frmKaizenDateUpdate","cmbResponsibility_"+row, "employee.commonFilter");
}

function cmbResponsbilityformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	var str = '';
	str+="<input type='text'  id='cmbResponsibility_" + rowId+"' style='width:160px;'  name='cmbResponsibility_"+rowId+"' value='"+id+"' class='easyui-combobox' /> ";
	return str;
	//return '<input id="cmbResponsibility_'+options.rowId+'" name="cmbResponsibility_'+options.rowId+'" value='"+id+"' class="easyui-combobox" />';
}

function updateKaizenDate(id){
	//alert("U Clicked On the Button");
	var kaizenId = jQuery("#tblKaizenDateUpdate").jqGrid('getCell',id,4);
	var isChecked = jQuery('#chkBox_'+id+'_2').is(':checked');
	var respid = getFieldValue("cmbResponsibility_"+id);
	
	if(isChecked==true){
	   //saveForm('frmKaizenDateUpdate','kaizenDateUpdate_save.kaizen?kaizenId='+kaizenId+"&kznRespid="+respid);
	   processAjaxCalls("kaizenDateUpdate_save.kaizen","kaizenId="+kaizenId+"&kznRespid="+respid, 'update_successCallBack','remove_errorCallBack');
	   jQuery('#tblKaizenDateUpdate').trigger("reloadGrid");
	}
	//alert("updateKaizenDate_successsCallback");
}
function update_successCallBack(result) {
	alert(result.successData.msg);
}
function viewGrid(url,dataString)
{   //alert("dataString:" +dataString);
	processGridnew(url, dataString, "tblKaizenDateUpdate", "abnAllocationPager", "", "");
	//processGridnew(url,dataString,"roleViewGrid","pager","","docDoubleClick");
	return true;
}
</script>
<form id="frmKaizenDateUpdate">
<div id="wrapperRpt" style="margin-top:20px;">
<div><label style="color: blue;font-size: 14px;">Kaizen End date will update as Kaizen Date</label></div>
<table id="tblKaizenDateUpdate"></table>
<div id="abnAllocationPager"></div>

</div>

</form>