<script type="text/javascript">
jQuery(document).ready(function(){	

	var actionPart = jQuery('#hiddenUrl').val();		

	viewGrid(actionPart,"?q=1");
	
	jQuery('#btnGraph').click(function(){
		
			var rowid = jQuery("#suggVsimple").jqGrid('getGridParam','selrow');
			var url="";
			if(rowid!=null && rowid!=''&& rowid!=' '&& rowid!=undefined && rowid!='undefined'){
			var rowData = jQuery("#suggVsimple").jqGrid('getRowData',rowid);
			var selId = rowData.KEYFIELD2;
			var colData = selId.split("#");
			var flid=colData[0];
			
					
				if(checkForZeroes("suggVsimple",rowid,5))
					{	
					url = "sugstvsimp_getChart.imvscom?rowid="+rowid; // + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
					showGraphData(url);
			    }else{ 
					alert("No Record to View Graph");
					return false;
			        }
			}
			else{
				rowid="";
				url = "sugstvsimp_getChart.imvscom?rowid="+rowid; // + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
				showGraphData(url);

				}
			 
		});
});

function doubleClickGrid(id)
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("suggVsimple","KEYFIELD2",id,keyfieldData);
	
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function suggVsimp_loadComplete()
{
	setDrillDownHeader("CH1-0","suggVsimple","KEYFIELD2");
	setTotalRowCss('suggVsimple');
}
function suggVsimple_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("suggVsimple","KEYFIELD2",keyfieldData);
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
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBl").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		//filterString += '&skipLine=Y';
		var tableCaption = "Suggested Vs Implemented";
		processGridnew(url,filterString,"suggVsimple","suggpager",tableCaption,"doubleClickGrid","","suggVsimp_loadComplete");		
		return true;
	}
	return false;	
}

</script>

<div id="wrapperRpt"style= margin-top:3px;>
<table>
      <tr>
           <td style="float:left;">
           <div style="margin-top: -3px">
           <label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label></div>
           </td>
           <td><div  style="float:right;">
	        <input id="btnGraph" class="easyui-button" style="height:25px;width:70px" type="button" value="Graph"/>  </div> </td>
      </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="suggVsimple" ></table>
	                <div id="suggpager"></div>
	                <div id="divGraphContain" ></div>	
           </td>
      </tr>
</table>
	
	
</div>	
	
	
	<input type="hidden" id="hiddenStri" value="sdsds" />
	<input type="hidden" id="hiddenRemoveBl" value=""  />
	<input type="hidden" id="hiddencirc" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />


