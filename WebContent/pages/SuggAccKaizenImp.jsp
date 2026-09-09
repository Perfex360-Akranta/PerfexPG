<script type="text/javascript">
jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId("frmSuggAccVsKaizenImp");
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();
	
   // viewGrid(actionPart,"?q=1");
   jQuery('#btnGraph').click(function(){
			var rowid = jQuery("#SugVskznImp").jqGrid('getGridParam','selrow');
			var rowData = jQuery("#SugVskznImp").jqGrid('getRowData',rowid);				
			var url = "chart.kaz";
			showGraphData(url);
		});
	
	jQuery('#btnBarGraph').click(function(){
		var rowid = jQuery("#SugVskznImp").jqGrid('getGridParam','selrow');
		var rowData = jQuery("#SugVskznImp").jqGrid('getRowData',rowid);				
		//var url = "barChart.kaz";
	
		// + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
	  var selId = rowData.keyid;
	
			var url = "barChart.kaz";
		
		showGraphData(url);
	});
	
});

function setDrillHeader(eleType){
	var levHeader="";
	if(eleType.trim()=="CMP")
		levHeader="Company";
	else if(eleType.trim()=="LCN")
		levHeader="Mill";
	else if(eleType.trim()=="SBU")
		levHeader="SBU";
	else if(eleType.trim()=="PBU")
		levHeader="PBU";
	else if(eleType.trim()=="L")
		levHeader="DMT";
	else if(eleType.trim()=="C")
		levHeader="JH";
	else if(eleType.trim()=="M")
		levHeader="Machine";
	return levHeader;			
}

function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);

	jQuery('#chkMonthwise').click(function(){
 		if(jQuery('#chkMonthwise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
		}
 			
	});
	
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "Suggestion Accepted Vs Kaizen Implemented";
		processGridnew(url,filterString,"SugVskznImp","pager",tableCaption,"doubleClickGrid","","SugVskznImp_loadComplete");
		return true;
	}
	return false;	
}

function frmSuggAccVsKaizenImp_afterLoadCallBack(){
	 toggleCommonFilter();
	}
	
function validateFilterSelection(filterString){
	if(filterString == "?q=1" )
		return true;
	if(jQuery('#chkMonthwise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select  FromMonth");
			return false;
		}
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	}
	return  true;
}


function impVscomp_loadComplete()
{
	setDrillDownHeader("CH1-0","SugVskznImp","KEYFIELD2");
	setTotalRowCss('SugVskznImp');
}

function impVscomp_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("SugVskznImp","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			

function doubleClickGrid(id){ 	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("SugVskznImp","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}

</script>
<form id="frmSuggAccVsKaizenImp">
<div id="wrapperRpt"style= margin-top:2px;>
<table>
      <tr>
      		<td><div  style="">
	        <input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>
	        <input id="btnBarGraph" class="easyui-button" type="button" value="Bar-Graph">
	         </div> 
	        </td>
           <td style="float:left;">
           <div style="margin-top: 2px">
            <label class="notes" style="font-weight: bold;">Double Click  on Company/Mill/SBU/PBU/DMT/JH to Drilldown</label></div>
           </td>
           
      </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="SugVskznImp" ></table>
	                <div id="pager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
</div>	
	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddencircle" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />
	</form>