<script>
jQuery(document).ready(function(){
	var cmbUrl = jQuery('#cmbUrl').val();
	var datastr;
 //alert(cmbUrl+" -- "+cmbUrl.replace('?', ''));
	if(cmbUrl.indexOf('?') >-1)
		datastr =	"&cmbUrl="+cmbUrl.replace('?', '&')+"&mode=grid&";
	else
		datastr =	"&cmbUrl="+cmbUrl+"&mode=grid&";
	//alert(cmbUrl +"   "+datastr);
	processGridnew(cmbUrl,datastr,"comboGrid","cmbPager","","","","onloadComplete","");
	jQuery("#btnCmbClose").click(function(){

		var selectedKeyid = jQuery('#hdnCmbSelectedID').val();
		var selectedText  = jQuery('#hdnCmbSelectedTxt').val();
		if(selectedKeyid.trim().length>0){
			
			var cmbId = jQuery("#cmbId").val();
			var cmbfrmName = jQuery("#cmbfrmName").val();
			//var cmbUrl = jQuery('#cmbUrl').val();

			setFieldValue(cmbId,selectedKeyid); 
			var opts1 = jQuery("#"+cmbfrmName + " input[id=" +  cmbId +"]").combobox("options");
			 
			var recorddat = '{"text":"'+selectedText+'" ,"id":"'+selectedKeyid+'"}';
			var record = JSON.parse(recorddat);
			opts1.onSelect.call(jQuery("#"+cmbId),record);
		}
		closePopUpDialoge("ComboPopDivId");
	});
	jQuery("#mybutton").click(function() { 
		   //jQuery("#comboGrid").setColumns(options);
		   jQuery("#comboGrid").jqGrid('columnChooser', options);
		   //return false;
		});
});
function onloadComplete(){

}
function chkbxCmbFormatter(cellvalue,options,rowObject){
	
	 
	return '<input id="combo_checkbox_'+options.rowId+'" name="combo_checkbox"   type="checkbox"  onclick="if(this.checked){chkboxCheck(\''+options.rowId + '\');}else{chkboxUnCheck(\''+ options.rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{ 
		
	var row = jQuery("#comboGrid").jqGrid('getDataIDs');
	for(var i=0 ;i<row.length;i++){
		
		/* var rowNum = jQuery("#comboGrid").jqGrid('getCell',row[i],"rn");
		alert("rowNum   "+rowNum ); */
		if(row[i]!= rowId)
			jQuery('#combo_checkbox_'+row[i]).attr('checked', false);//For Unchecking other CheckBoxes
	 }
	var cmbId= jQuery("#comboGrid").jqGrid('getCell',rowId,"ID");
	var cmbText = jQuery("#comboGrid").jqGrid('getCell',rowId,"TEXT");
	//alert(cmbId);
	jQuery('#hdnCmbSelectedID').val(cmbId);
	jQuery('#hdnCmbSelectedTxt').val(cmbText);
	
	jQuery("#comboGrid").jqGrid('setCell',rowId,'checkcmbvalue','1');	
}
function chkboxUnCheck(rowId)
{

	jQuery("#comboGrid").jqGrid('setCell',rowId,'checkcmbvalue','0');
}
</script>
<div id="grdCmb">
	<table id="comboGrid">
		<tr><td></td></tr>
	</table>
	<div id="cmbPager"></div>
	<div align="center" style="margin-top:2%;" class="easyui-paddingbfpx">
		<input type="button" id="btnCmbClose" value="Ok" class="easyui-button"/>
<!-- 		<input type="button" id="mybutton" value="mybtn" class="easyui-button"/> -->
	</div>
</div>


<input type="hidden" id="cmbUrl" name="cmbUrl" value="${requestScope.cmbUrl }"/>
<input type="hidden" id="cmbfrmName" name="cmbfrmName" value="${requestScope.frmName }"/>
<input type="hidden" id="cmbId" name="cmbId" value="${requestScope.cmbId }"/>
<input type="hidden" id="hdnCmbSelectedID" name="hdnCmbSelectedID" value=""/>
<input type="hidden" id="hdnCmbSelectedTxt" name="hdnCmbSelectedTxt" value=""/>