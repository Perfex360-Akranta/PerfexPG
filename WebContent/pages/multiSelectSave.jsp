
<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var url=jQuery('#hdnPopupUrl').val();
	var dataStr =jQuery('#hdnCondition').val();
	jQuery('#submitForm').val('addMchArea');
	var selectdmchId = jQuery('#hien').val();
	var dataStr = "selectdmchId="+selectdmchId;
	//alert(url);
	processGridnew(url,"?&selectdmchId="+selectdmchId,"multiSelectSaveGrid","multiSelectSavePager","","","","multiSelectSaveGrid_onComplete");


});

function multiSelectSaveGrid_onComplete(result){
	
} 

function cboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
  	return '<input  type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
}

function selectData(rowId){

	jQuery("#multiSelectSaveGrid").setCell(rowId, "hdnchkSel","1");

}

function unselectData(id){
	
	jQuery("#multiSelectSaveGrid").setCell(id, "hdnchkSel"," ");
}

function loadCompletePillarGrid()
{
	jQuery(":input[type=checkbox]").removeAttr('disabled');
	
}
function getSelectdRows(){
	
	//"multiSelectSaveGrid","tick","hdnchkSel"
	var jqGridId ="multiSelectSaveGrid";
	var checkBoxColName="tick";
	var ckeckForSelColName="hdnchkSel";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		
		if( value != null  &&  value.trim()  != ""){
			
			for(var colName in row) {
				//colName = "txtmcamkeyId";
				
				if(colName == "txtmcamkeyId"){
					
					jsonArrO +=  row[colName] +',';
					
				}
			}
			
		}
	} 
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.length > 1 ?jsonArrO:"";
	return jsonArrO; 
}

function  addMchArea_beforeSubmit(){
	
	var mchId =jQuery('#hien').val();
	var tblData = 'multiSelSave='+getSelectdRows();
		tblData += '&mchId='+mchId;
		
	return tblData; 
}

function addMchArea_successsCallback(result){
	var okCallback = jQuery("#hdnokCallback").val();
	
	try{
		var args = [ result ]; 
		window[okCallback].apply(this,args);
	}catch(Exception){}
	multiSelectSaveOk_Callback();
	jQuery('#multiSelectSavePopUpId').dialog('close');			
}

jQuery('#btnsaveIcon').click( function()
{
	var url=jQuery('#hdnPopupUrl').val();
	saveForm("addMchArea",url);
	

});
jQuery('#btnRefreshicon').click(function(){
	jQuery('#multiSelectSaveGrid').trigger("reloadGrid");
	
});	

jQuery('#btndialogClose').click( function()
{
	var cancelCallback = jQuery('#hdnCancelCallBack').val(); ;
	var id=jQuery('#hdnRowId').val();
	if( typeof eval('(' + cancelCallback +')') == 'function')
		eval('(' + cancelCallback +'(id))');
	jQuery('#multiSelectSavePopUpId').dialog('close');
});	


</script>
<form id="addMchArea">
<div id="multiSelectSavePopUpId" title=""  style="height:507px;background-image: -moz-radial-gradient(center, circle farthest-corner, #FFFFFF 0%, #ACC0EF 100%);">
<!--SAVE BUTTON-->
<div class="dialo_gu_hdr">
<div id="saveIcon"  style="width:100%;margin-top:0px;">
	<span style="margin-left:170px;cursor:pointer;">
		<img id="btnsaveIcon" src="images/4_save.png"/>
	</span>

	<span id="Refreshicon" style="margin-left:10px;cursor:pointer;">
		<img id="btnRefreshicon" src="images/2_refresh blue.png"/>
	</span>
</div>
</div>
<!--<input type=text id="dfs" class="easyui-text" value=""/>-->
<table >
	<tr>
		<td>
			<div class="floatleft" style="width: 300px;height: 380px;padding-right: 100px;text-align:left;">
					<div id="griddivImprCat" style="margin-left:10px;"> 
						 <table id="multiSelectSaveGrid" style="width:100%"><tr><td/></tr></table>
					 	 <div id="multiSelectSaveGrid"></div> 
					</div>
			</div>
			<div style="clear: both;"></div>
<!--			<div style="margin-top: 30px;">-->
<!--					<span><input type="button" id="btndialogOk" class="easyui-button" value="Ok" /> </span>-->
<!--					<span style="padding-top: 20px;"><input type="button" id="btndialogClose" class="easyui-button" value="Cancel" onclick="javascript:jQuery('#multiSelectSavePopUpId').dialog('close')"/> </span>-->
<!--			</div>-->
	
		</td>
	</tr>	
</table>
</div>
<input type="hidden" id="hdnSltedRowIds" value=" " />
<input type="hidden" id="hdnPopupUrl" value="${requestScope.dataUrl}" >
<input type="hidden" id="hdnGridId" value="${requestScope.gridId}" >
<input type="hidden" id="hdnRowId" value="${requestScope.rowId}" >
<input type="hidden" id="hdnIsmultiSelectSave" value="${requestScope.ismultiSelectSave}" >
<input type="hidden" id="hdnColNames" value="${requestScope.colNames}" >
<input type="hidden" id="hdnCondition" value="${requestScope.condition}" >
<input type="hidden" id="hdnCancelCallBack" value="${requestScope.multiSelectSaveCancel_Callback}" >
<input type="hidden" id="hdnokCallback" value="${requestScope.multiSelectSaveOk_Callback}"/>
</form>