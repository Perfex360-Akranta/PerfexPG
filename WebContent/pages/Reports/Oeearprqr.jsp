<script type="text/javascript">

jQuery(document).ready(function()
		{
		     jQuery('#submitForm').val('frmOeereport');
			 viewGrid("Oeearprqr_input.oeear","?q=2&firstClick=Y");

		});




function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
	
		var tableCaption = "Pcs Oee";
		filterString =filterString+ "&drillFlag=f";
	
		processGridnew(url,filterString,"OeeGrid","pager","tableCaption","doubleClickGrid","","visGrid","","");  //OeeGrid_loadComplete
		
		return true;
	}
	
	return false;	
}
function validateFilterSelection(filterString){
	return  true;
}

jQuery('#btnGraph').click(function() //// initialiseForm('frmOeereport');	
	{
			var rowid = jQuery("#OeeGrid").jqGrid('getGridParam','selrow');
			var url="";
			if(rowid!=null && rowid!=''&& rowid!=' '&& rowid!=undefined && rowid!='undefined'){
			var rowData = jQuery("#OeeGrid").jqGrid('getRowData',rowid);
			var selId = rowData.KEYFIELD2;
			var colData = selId.split("#");
			var conflid=colData[0];
	        if(checkForZeroes("OeeGrid",rowid,3))
				{	
					url = "chart.oeear?conflid="+conflid; // + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
					showGraphData(url);
		    }else{ 
					alert("No Record to View Graph");
					return false;
			        }
		 }
		else{
					rowid="";
				    url = "chart.oeear";  
				   showGraphData(url);

			}
		 
	});
	


function OeeGrid_onProcessGridBack()
{
	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("OeeGrid","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");	
	
}	

function doubleClickGrid(id)
{ 	
	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("OeeGrid","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);

}

function visGrid()
{
	
	setDrillDownHeader("CH1-0","OeeGrid","KEYFIELD2");
	setTotalRowCss('OeeGrid');
	
}


</script>



<form name="frmOeereport" id="frmOeereport" >
<div id="wrapperRpt" style= margin-top:2px;>
<div >
<input type="button" class="easyui-button" id="btnGraph"  name="btnGraph"     value="Graph" style="height: 22px;" />
<label  style="font-weight: bold;padding-left:5px;">${requestScope.drilldownMsgs}</label>
</div>
<table id="OeeGrid" ></table>
<div id="pager"></div>
<div id="divGraphContainer" ></div>	<br><br><br>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenIds" name="hiddenIds" value="${requestScope.hiddenIds}" />
<input type="hidden" id="hiddencircle" value=""  />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hiddenparentId" value="${requestScope.hiddenparentId}"  />
<input type="hidden" id="hiddenCompId" value="${requestScope.cmbCompid}"  />
<input type="hidden" id="hdnfromMonth" value="${requestScope.fromMonth}"  />
<input type="hidden" id="hdntoMonth" value="${requestScope.toMonth}"  />
<input type="hidden" id="hdnchkMonWise" value="${requestScope.chkMonthwise}"  />
<input type="hidden" id="hdnchkDatewise" value="${requestScope.chkDatewise}"  />
<input type="hidden" id="hdnFnlnKeyid" />
</div>
</form>
	