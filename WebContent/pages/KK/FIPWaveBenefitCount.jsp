<script type="text/javascript">
jQuery(document).ready(function(){
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
	jQuery('#btnGraph').click(function(){
			   var rowId = jQuery("#FIPBenefitgrd").jqGrid('getGridParam','selrow');
			    var rowData = jQuery("#FIPBenefitgrd").jqGrid('getRowData',rowId);
				var selId = rowData.keyid;
				var url = "FIPBenefitCountchart.prpo?rowid=" + selId + "&rownum="+rowId;
				showGraphData(url);	
		});
});

 
function doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("FIPBenefitgrd","KEYFIELD1",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function FIPBenefit_loadComplete()
{
	setDrillDownHeader("jqgh_FIPBenefitgrd_CODEFIELD","FIPBenefitgrd","KEYFIELD1");
}
function FIPBenefit_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("FIPBenefitgrd","KEYFIELD1",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	

function validateFilterSelection(filterString){
	
		return true;
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddenci").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);		
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "FIP Benefit Count";
		processGridnew(url,filterString,"FIPBenefitgrd","FIPBenefitpager",tableCaption,"","","FIPBenefit_loadComplete");		
		return true;
	}
	return false;	
}

</script>
<form id="frmFIPWaveBenefitCount" name="frmFIPWaveBenefitCount">
<div id="wrapperRpt"style= margin-top:3px;>
<table>
      <tr>
           <td><div>
           <span style="border: solid 2px #c1c1c1;margin-left:8px ;" >
	        <input id="btnGraph" class="easyui-button" style="height:25px;width:70px" type="button" value="Bar-Graph"/></span></div> </td>
	         <td>
           <div style="margin-top: -3px">
          </div>
           </td>
            </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="FIPBenefitgrd" ></table>
	                <div id="FIPBenefitpager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
</div>	
	<input type="hidden" id="hiddenString" value="sdsdsw" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddenci" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />
</form>