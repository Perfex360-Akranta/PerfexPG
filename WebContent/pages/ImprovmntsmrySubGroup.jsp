<script type="text/javascript">
jQuery(document).ready(function()
{	
	//alert(1);
	var actionPart = jQuery('#hiddenUrl').val();	
	var keyId = jQuery("#hiddenComp").val();
	chkRelatedResult();
	//viewGrid(actionPart,"");
	jQuery("#chkResultWise").attr("checked",true);
	var comp=jQuery("#hiddenComp").val();
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();


	jQuery('#btnGraph').click(function(){
		prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		var rowid = jQuery("#kznGridSubGroup").jqGrid('getGridParam','selrow');
		//alert(rowid);
		var url="";
		var rowData = jQuery("#kznGridSubGroup").jqGrid('getRowData',rowid);
		var selId = rowData.keyid;
		 url = "piechart.impSmrRpt";
		if(checkForZeroes("kznGridSubGroup",rowid,2)){	
			
			  url = url+"?rowid=" + selId + "&rownum="+rowid+prevDataUrl ;
			  showGraphData(url);	
		}else 
			alert("No Record to View Graph");
	});
		
});

function viewGrid(url,filterString)
{ 
	var prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	
	if( validateFilterSelection(filterString))
	{	
		var comp=jQuery("#hiddenComp").val();
		processGridnew(url,filterString,"kznGridSubGroup","pager","","doubleClickGrid","","ImprovementSmry_loadComplete");
		return true;
	}
	return false;	
}

function ImprovementSmry_loadComplete()
{
	/*jQuery('#btnJqGridBack').click(function (){
		 navigateToPrevForm();
		 
	});*/
	hideShowBack(false);
}
function validateFilterSelection(filterString){
	
	return  true;
}
function chkRelatedResult()
{
	jQuery("#chkLossWise").attr("checked",false);
	jQuery("#chkPillerWise").attr("checked",false);
	jQuery("#chkEquipmentWise").attr("checked",false);
	var url="ImpSmrySubGrp_input.impSmrRpt?";
	
	compId = jQuery("#hiddenComp").val();
	
	var filterStr = '';
	if(compId.substr(0,3) == 'CMP')
		filterStr += "compId="+compId;
	else if(compId.substr(0,3) == 'FCT')
		filterStr += "factId="+compId;	
	else if(compId.substr(0,3) == 'LIN')
		filterStr += "sectId="+compId;
	else if(compId.substr(0,3) == 'CEL')
		filterStr += "cellId="+compId;
	else if(compId.substr(0,3) == 'MCH')
		filterStr += "mchId="+compId;
	filterStr += "&subGrp=R";
	filterStr += "&flid="+compId;
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		
		viewGrid("ImpSmry_input.impSmrRpt","?q=1&firstClick=Y");
	else{
		viewGrid(url,unescape(prevDataUrl));
	}
	
	}
jQuery('#chkResultWise').click(function(){
	var keyId = jQuery("#hiddenComp").val();
	
	chkRelatedResult();
				
});

jQuery('#chkLossWise').click(function(){	
	jQuery("#chkResultWise").attr("checked",false);
	jQuery("#chkPillerWise").attr("checked",false);
	jQuery("#chkEquipmentWise").attr("checked",false);	
	var url="ImpSmrySubGrpLoss_input.impSmrRpt?";
	var compId = jQuery("#hiddenComp").val();	
	//jQuery("#hiddenStr").val(compId);
	
	var filterStr = '';
	if(compId.substr(0,3) == 'CMP')
		filterStr += "compId="+compId;
	else if(compId.substr(0,3) == 'FCT')
		filterStr += "factId="+compId;	
	else if(compId.substr(0,3) == 'LIN')
		filterStr += "sectId="+compId;
	else if(compId.substr(0,3) == 'CEL')
		filterStr += "cellId="+compId;
	else if(compId.substr(0,3) == 'MCH')
		filterStr += "mchId="+compId;
	filterStr += "&subGrp=L";
	filterStr += "&flid="+compId;
	viewGrid(url,filterStr);
});

jQuery('#chkPillerWise').click(function(){
	jQuery("#chkResultWise").attr("checked",false);
	jQuery("#chkLossWise").attr("checked",false);
	jQuery("#chkEquipmentWise").attr("checked",false);	
	var url="ImpSmrySubGrpPiller_input.impSmrRpt?";
	var compId = jQuery("#hiddenComp").val();
	
	var filterStr = '';
	if(compId.substr(0,3) == 'CMP')
		filterStr += "compId="+compId;
	else if(compId.substr(0,3) == 'FCT')
		filterStr += "factId="+compId;	
	else if(compId.substr(0,3) == 'LIN')
		filterStr += "sectId="+compId;
	else if(compId.substr(0,3) == 'CEL')
		filterStr += "cellId="+compId;
	else if(compId.substr(0,3) == 'MCH')
		filterStr += "mchId="+compId;
	filterStr += "&subGrp=P";
	filterStr += "&flid="+compId;

	viewGrid(url,filterStr);
});
jQuery('#chkEquipmentWise').click(function(){	
	jQuery("#chkResultWise").attr("checked",false);
	jQuery("#chkLossWise").attr("checked",false);
	jQuery("#chkPillerWise").attr("checked",false);	
	var url="ImpSmrySubGrpEqp_input.impSmrRpt?";
	var compId = jQuery("#hiddenComp").val();

	var filterStr = '';
	if(compId.substr(0,3) == 'CMP')
		filterStr += "compId="+compId;
	else if(compId.substr(0,3) == 'FCT')
		filterStr += "factId="+compId;	
	else if(compId.substr(0,3) == 'LIN')
		filterStr += "sectId="+compId;
	else if(compId.substr(0,3) == 'CEL')
		filterStr += "cellId="+compId;
	else if(compId.substr(0,3) == 'MCH')
		filterStr += "mchId="+compId;
	filterStr += "&subGrp=E";
	filterStr += "&flid="+compId;
	viewGrid(url,filterStr);
});



</script>

<div id="wrapperRpt">
<div class="clear"></div>	    

<input type="checkbox" id="chkResultWise" />Result Wise
<span><input type="checkbox" id="chkLossWise" />Loss Wise</span>
<span><input type="checkbox" id="chkPillerWise" />Pillar Wise</span>
<span><input type="checkbox" id="chkEquipmentWise" />Equipment Wise</span>
<span style="position:relative;top:5px\9;"><input id="btnGraph" class="easyui-button" style="padding-top:0;" type="button" value="Pie Chart"/> </span>
<!--<span  style="padding-right:20px;"> <input type="button"  class="easyui-button" id="close" value="Back"/>   </span>-->
<table id="kznGridSubGroup" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="" />
<input type="hidden" id="hiddenComp" value="${requestScope.compId}"/>
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>