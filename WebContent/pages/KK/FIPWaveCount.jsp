<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1");
	jQuery('#btnGraph').click(function(){
			
			   var rowId = jQuery("#FIPgrd").jqGrid('getGridParam','selrow');
			    var rowData = jQuery("#FIPgrd").jqGrid('getRowData',rowId);
				var selId = rowData.keyid;
				var url = "FIPWaveCountchart.prpo?rowid=" + selId + "&rownum="+rowId;
				showGraphData(url);	
		});
});

 function FIPgrd_loadComplete()
{
	setDrillDownHeader("jqgh_FIPgrd_CODEFIELD","dmaicgrd","KEYFIELD1");
}
function FIPgrd_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("FIPgrd","KEYFIELD1",keyfieldData);
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
		var tableCaption = "FIP Count";
		processGridnew(url,filterString,"FIPgrd","FIPpager",tableCaption,"","","FIPgrd_loadComplete");		
		return true;
	}
	return false;	
}

</script>
<form id="frmFIPCount" name="frmFIPCount">
<div id="wrapperRpt"style= margin-top:3px;>
<table>
      <tr>
           <td><div>
           <span style="border: solid 2px #c1c1c1;margin-left:8px ;" >
	        <input id="btnGraph" class="easyui-button" style="height:25px;width:70px" type="button" value="Bar-Graph"/></span>  </div> </td>
	         <td>
           <div style="margin-top: -3px">
          </div>
           </td>
            </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="FIPgrd" ></table>
	                <div id="FIPpager"></div>
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