<script>
jQuery(document).ready(function(){
	initialiseForm('frmMaterial');
	processGridnew("materialCharacteristics_input.qins", "q=2", "materialGrid", "pagermaterial", "", "", "");
	fileManagerPopUp("","MAC","frmMaterial","btnfilemgr","MatcFilemgr");
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		
		processAjaxCalls("openFile.file?fileName=MaterialCharacteristicsXL.xlsx", "", "", "", "", "new");		
					
	});
});

function btnfilemgr_click()
{
	//alert("11");
   // var documentNo =jQuery("#hdnabnkeyID").val();
   
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"MAC","","","");
	}
	
}

function formatterJHLevel(id, options, rowObject)
{
	
	var columnKey="";
	var color='';
	var id = options.rowId;
	var columnName = options.colModel.name;	
	var columnNo=columnName.substring(columnName.indexOf("_")+1);
	var idval;
	 if(columnName=="MIN") {
		idval='txtMin_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=minoutFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 180px;text-align:right;background-color:'+color+'" / >';
				
		}
	else if(columnName=="MAX" ){
		idval='txtMax_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 180px;text-align:right;background-color:'+color+'" / >';		
		
		}
	else if(columnName=="TargetValue"  ){
		idval='txtTar_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=taroutFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 180px;text-align:right;background-color:'+color+'" / >';		
		
		}
	else{
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		

		}
	
}
function minoutFocus(id,colId,rowId) {
	var Min="txtMin_"+colId + "_"+id;
	var  MinValue = jQuery("#"+Min).val();
	jQuery("#minvalue").val(MinValue);

}
function outFocus(id,colId,rowId) {
	var Min="txtMin_"+colId + "_"+id;
	var Max="txtMax_"+colId + "_"+id;
	var Target="txtTar_"+colId + "_"+id;
	
	var  MinValue = jQuery("#"+Min).val();
	var  MaxValue = jQuery("#"+Max).val();
	var  TarValue = jQuery("#"+Target).val();
	
	var minval=jQuery("#minvalue").val();
	//alert('MinValue'+MinValue);
	if(minval>MaxValue){
		alert('The maximum Value amount must be larger than the minimum value.');
		return false;
		}
		
	
	else {
		jQuery("#maxvalue").val(MaxValue);
		return true;
		}
	
		
	

}
function taroutFocus(id,colId,rowId) {
	var Min="txtMin_"+colId + "_"+id;
	var Max="txtMax_"+colId + "_"+id;
	var Target="txtTar_"+colId + "_"+id;
	
	var  MinValue = jQuery("#"+Min).val();
	var  MaxValue = jQuery("#"+Max).val();
	var  TarValue = jQuery("#"+Target).val();
	var minval=jQuery("#minvalue").val();
	var maxval=jQuery("#maxvalue").val();
	jQuery("#tar").val(TarValue);
	//alert(maxval);
	if((minval < TarValue) && (maxval> TarValue )){
		return true;
		}
		
	
	else {
		alert('The Target Value should be in between min value and max value.');
		return false;
		}

}
function gotFocuse(id,colId,rowId){

	numericTextBox(id);
}
</script>

<form id="frmMaterial" name="frmMaterial" method="post">
	<div id="wrapperRpt">
		<div style="margin-top: -28px">
			<label>Material Code</label>
				<span style="padding-left:10px;">
					<input type='text' name='txtMaterialCode' id='txtMaterialCode' class='easyui-text' maxlength='20' style="width:255px; height:21px;"/>
				</span><span style="padding-left:10px;"><input type='button' name='txtMaterialCode' id='txtMaterialCode' class='easyui-button' value="View" maxlength='20' style="width:70px; height:21px;"/></span>
				<span style='padding-left:6px;'><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 21px; width : 102px;"/></span>
		</div>
		<div style=" padding-left:10px; padding-top:10px;position:relative; ">
					 <span  id="MatcFilemgr" style="position:absolute;right:238px;right:430px\9;top:-25px;top:-30px\9;" >
     		
             </span> 
             </div>	
		<div style="padding-top:10px;margin-top: -18px">
				<table id='materialGrid'>
					<tr>
						<td></td>
					</tr>
				</table>
				<div id='pagermaterial'></div>
		</div>
	</div>
	<input type="hidden" id="minvalue">
	<input type="hidden" id="maxvalue">
	<input type="hidden" id="tar">
	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>