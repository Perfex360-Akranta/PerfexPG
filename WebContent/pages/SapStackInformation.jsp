<script>
jQuery(document).ready(function(){
	initialiseForm("frmsapstackinformation");
	fillComboBox("frmsapstackinformation","cmbWorkCenter","workCenter.commonFilter");
	jQuery('#btngetSapData').click(function(){
	 viewGrid('q=2&type=sap');
    });
	jQuery('#btnSAPSPROK').click(function(){
		var colData = JqGridToJsonSelectdRows("Resultgrid","Checkbox","checkVal" );
		
		jQuery('#hdnSaveSpareSapInfo').html(colData);
		jQuery('#hdnSprOkBtnClick').val("Y");
		closePopUpDialoge("divSAPSpares");
	});
	viewGrid('q=2');
	fillComboBox("frmsapstackinformation","cmbPlant","companyCombo.commonFilter");
	
	processGridnew("Sapstackinformation_input.sapinfo","?q=2","Relatedgrid","Relatedpager");
	processGridnew("Sapstackstorage_input.sapinfo","?q=2","Storagegrid","Storagepager");
	
	

});
function viewGrid(filter){
	
	processGridnew("SapstackResult_input.sapinfo",filter,"Resultgrid","Resultpager");
	
}

function TxtSap(id, options, rowObject){

	var Id = options.rowId;
	
	var columnid = options.pos;
	
	return '<input id="txtIndividual" name="txtIndividual" type="text" value="" style="width:200px;text-align:left"/>';
	

	}
	function TxtSapstorage(id, options, rowObject){

	var Id = options.rowId;
	
	var columnid = options.pos;
	
	return '<input id="txtIndividual" name="txtIndividual" type="text" value="" style="width:100px;text-align:left"/>';
	

	}
	function TxtSapresult(id, options, rowObject){//alert("");
		var rowId = options.rowId;
		return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value=""  onclick="if(this.checked){SapchkboxCheck(\''+rowId + '\');}else{SapchkboxUnCheck(\''+ rowId +'\')}"/>';
	}
   function SapchkboxCheck(rowId){
	  // KEYID -- selected value will be in this column [1 or 0]
	   jQuery("#Resultgrid").jqGrid('setCell',rowId,'checkVal','1');
   }
   function SapchkboxUnCheck(rowId){
	   jQuery("#Resultgrid").jqGrid('setCell',rowId,'checkVal','0');
   }

</script>
<form id="frmsapstackinformation" name="frmsapstackinformation" >
<div style="">
<div class="sub-header" style="width:884px;">
<label >Enter Part No. and Storage Location To Fetch Information From SAP</label>
</div>
<table cellspacing='15'>
<tr>
<td valign="top">
<div>
<label>Plant</label>
</div>
<div>
<input type="text" id="txtPlant" name="txtPlant" clear="false" class="easyui-text"  style="width:60px;" value="" >
<span style="padding-left:10px;">
<input  id="cmbPlant" name="cmbPlant" clear="false" class="easyui-text"  style="width:180px;" value="" >
</span>
</div>
</td>
<td valign="top">
<div>
<label style="padding-left:10px;">Work Center</label>
</div>
<div style="padding-left:10px;">
<input   id="cmbWorkCenter" name="cmbWorkCenter" clear="false" class="easyui-combo"  style="width:220px;" value="" >
</div>
</td>
 <td valign="bottom">
<div>
	<input type="button" class="easyui-button" id="btngetSapData" name="btngetSapData" style="width:170px;height:22px" value="Get SAP Data"/>
	<span style="padding-top:6px;">
	<input id="btnRfrsh" name="btnRfrsh" class="easyui-button"  type="button" value="Clear Query" style="width:170px;height:21px;"/>
	</span>
</div>

</td>
</tr>
<tr>
<td valign="top" style=" " colspan='2'>
 
<div>
	<input id="btnRfrsh" name="btnRfrsh" class="easyui-button"  type="button" value="Refresh Storage Location" style="width:170px;height:21px;"/>
	<span style="padding-top:6px;" >
		<input id="btnRfrsh" name="btnRfrsh" class="easyui-button"  type="button" value="Remove Selected Spare" style="width:170px;height:21px;"/>
	</span>
	<span style="padding-left:2px;" >
			<input type="button" class="easyui-button" id="btnExporttoExcel" name="btnExporttoExcel" style="width:130px;height:22px" value="Export Excel"/>
	</span>
</div>
  </td>
  	<td>
		<div>
		
			<input type="button" class="easyui-button" id="btnSAPSPROK" name="btnSAPSPROK" style="width:130px;height:22px" value="Ok"/>
		</div>
	</td>
</tr>
<tr>
	<td>
		<div style="float:left;">
			<table id='Storagegrid'><tr><td></td></tr></table>
			<div id='Storagepager'></div>
		</div>
	</td>
	<td>
		<div style="float:right;">
			<table id='Relatedgrid'><tr><td></td></tr></table>
			<div id='Relatedpager'></div>
		</div>
		
	</td>
</tr>
<tr>
<td colspan="4">
<div style="margin-top:6px;">
<label>Result Area</label>
</div>
<table id='Resultgrid'><tr><td></td></tr></table>
<div id='Resultpager'></div>
</td>
</tr>
</table>
 </div>
 <input type='hidden' id='hdnSprOkBtnClick' name='hdnSprOkBtnClick'/>
 <div id="hdnSaveSpareSapInfo" style="display:none;"></div>
</form>