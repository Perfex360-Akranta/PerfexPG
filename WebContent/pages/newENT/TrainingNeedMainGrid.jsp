
<script>

jQuery(document).ready(function(){	

   viewGrid("","?q=2");
	jQuery ("#btnNew").click(function(){    
		     var mode="create";
		     navigateToNextForm("traningNeed_input.tatnd?&createmode="+mode,"Training Need Identification");	
	});	
});
function viewGrid(url,filterString){
	 
	 if( validateFilterSelection(filterString))
		{
		 processGridnew("traningLead_input.tatnd",filterString,"TrainingNeedGrid","pager","","docDoubleClick",""); 
		 return true;
		}
		return false;
	}
	function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
		
		if(filterString=="?q=2")
			return true;

		if(getFilterValue(filterString, "flid") == "" ){
			alert(" Select Functional Location ");
			return false;
		}
		 
		    return true;
	}	
function docDoubleClick(id){		
	var rowData = jQuery("#TrainingNeedGrid").jqGrid('getRowData',id);
	//var keyid = rowData.KEYID;
	var Flid=rowData.FLN;
	var type=rowData.TYPEID;
	var Date=rowData.DATED;
	var Remarks=rowData.REMARKS;
	navigateToNextForm('traningNeed_input.tatnd?&grid=true&flid='+Flid+"&type="+type+"&date="+Date+"&Remarks="+Remarks,"Training Need Identification");  
}
</script>
<form id="frmTrainingNeed">
<div id="WrapperRpt">
<table>
 <tr>

	<td Style="padding-left: 5px;">
    <div ><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">
    <span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span>
    
   </div></td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="TrainingNeedGrid" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="paramDiv" style="display:none;" title="param">
</div>
</div>	
</form>
	
	