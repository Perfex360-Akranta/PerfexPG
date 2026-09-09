<script type="text/javascript">
jQuery(document).ready(function(){
	var actionPart = jQuery('#hiddenUrl').val();		
	jQuery('#chkcompleted').attr('checked',true);
	viewGrid(actionPart,"?q=1");

	jQuery('#btnGraph').click(function(){
			
		    var rowId = jQuery("#KznBenefitgrd").jqGrid('getGridParam','selrow');
		    var rowData = jQuery("#KznBenefitgrd").jqGrid('getRowData',rowId);
			var selId = rowData.keyid;
			var url = "KaizenBenefitCountchart.kaz?rowid=" + selId + "&rownum="+rowId;
			showGraphData(url);	 
		});
});

function doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("FIPgrd","KEYFIELD1",id,keyfieldData);
	
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function dmaic_loadComplete()
{
	setDrillDownHeader("jqgh_dmaicgrd_CODEFIELD","dmaicgrd","KEYFIELD1");
//	setTotalRowCss('dmaicgrd');
}
function dmaicgrd_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("KznBenefitgrd","KEYFIELD1",keyfieldData);
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
		//filterString += '&skipLine=Y';
		var tableCaption = "Kaizen Benefit Trend Report";
		processGridnew(url,filterString,"KznBenefitgrd","Kznbenpager",tableCaption,"doubleClickGrid","","dmaic_loadComplete");		
		return true;
	}
	return false;	
}

</script>
<form id="frmKznBenefitrend" name="frmKznBenefitrend">
<div id="wrapperRpt"style= margin-top:3px;>
<table>
      <tr>
           <td><div>
           <span style="border: solid 2px #c1c1c1;margin-left:8px ;" >
	        <input id="btnGraph" class="easyui-button" style="height:25px;width:70px" type="button" value="Bar-Chart"/></span>  </div> </td>
	         <td>
           <div style="margin-top: -3px">
          </div>
           </td>
            </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="KznBenefitgrd" ></table>
	                <div id="Kznbenpager"></div>
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