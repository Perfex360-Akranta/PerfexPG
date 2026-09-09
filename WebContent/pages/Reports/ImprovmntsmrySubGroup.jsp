<script type="text/javascript">
jQuery(document).ready(function()
{	
	
	var actionPart = jQuery('#hiddenUrl').val();	
	var keyId = jQuery("#hiddenComp").val();
	//alert("keyId" +keyId);
	chkRelatedResult();
	//viewGrid(actionPart,"");
	jQuery("#chkResultWise").attr("checked",true);
	var comp=jQuery("#hiddenComp").val();
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
    //alert("1 " +prevDataUrl);	
	jQuery('#btnGraph').click(function(){
		prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		//alert("prevDataUrl :" +prevDataUrl );
		if(jQuery("#chkPillerWise").attr("checked")) {
			prevDataUrl = prevDataUrl.replace("subGrp=R", "subGrp=P"); 
		} 
		if(jQuery("#chkResultWise").attr("checked")) {
			prevDataUrl = prevDataUrl.replace("subGrp=P", "subGrp=R");
		}
		
		//alert("prevDataUrl :" +prevDataUrl );
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
	//alert("Url:"+ url);
	//alert("filterString:"+ filterString);
	
	var prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	
	if(jQuery("#chkPillerWise").attr("checked")) {
		url="ImpSmrySubGrpPiller_input.impSmrRpt?";
		prevDataUrl = prevDataUrl.replace("subGrp=R", "subGrp=P"); 
	} 
	if(jQuery("#chkResultWise").attr("checked")) {
		url="ImpSmrySubGrp_input.impSmrRpt?";
		prevDataUrl = prevDataUrl.replace("subGrp=P", "subGrp=R");
	}
	
	if( validateFilterSelection(filterString))
	{	
		var comp=jQuery("#hiddenComp").val();
		//alert("comp:"  +comp);
		processGridnew(url,prevDataUrl,"kznGridSubGroup","pager","","doubleClickGrid","","ImprovementSmry_loadComplete");
		return true;
	}
	return false;	
}

function ImprovementSmry_loadComplete()
{
	
	hideShowBack(false);
}
function validateFilterSelection(filterString){
	
	return  true;
}
function chkRelatedResult()	{
	jQuery("#chkPillerWise").attr("checked",false);	
	var url="ImpSmrySubGrp_input.impSmrRpt?";	
	compId = jQuery("#hiddenComp").val();	
	var filterStr = '';
	filterStr += "&subGrp=R";
	filterStr += "&flid="+compId;	
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();	
	//alert("prevDataUrl:" +prevDataUrl);
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


jQuery('#chkPillerWise').click(function(){
	
	jQuery("#chkResultWise").attr("checked",false);	
	var url="ImpSmrySubGrp_input.impSmrRpt?";	
	compId = jQuery("#hiddenComp").val();	
	var filterStr = '';
	filterStr += "&subGrp=P";
	filterStr += "&flid="+compId;	
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();	
	//alert("prevDataUrl:" +prevDataUrl);
	if( prevDataUrl == null || prevDataUrl.length <=0)			
		viewGrid("ImpSmry_input.impSmrRpt","?q=1&firstClick=Y");
	else{
		
		viewGrid(url,unescape(prevDataUrl));
	}
	/*jQuery("#chkResultWise").attr("checked",false);
	var url="ImpSmrySubGrpPiller_input.impSmrRpt?";	
	var prevDataUrl = jQuery('#hdnPrevDataUrl').val();	
	if(jQuery("#chkPillerWise").attr("checked")) {
		prevDataUrl = prevDataUrl.replace("subGrp=R", "subGrp=P"); 
	} 
	viewGrid(url,prevDataUrl);*/
});




</script>

<div id="wrapperRpt">
<div class="clear"></div>	    

<input type="checkbox" id="chkResultWise" />Result Wise
<%--<span><input type="checkbox" id="chkLossWise" />Loss Wise</span> --%>
<span><input type="checkbox" id="chkPillerWise" />Pillar Wise</span>
<%--<span><input type="checkbox" id="chkEquipmentWise" />Equipment Wise</span>--%>
<span style="position:relative;top:5px\9;"><input id="btnGraph" class="easyui-button" style="padding-top:0;" type="button" value="Pie Chart"/> </span>
<!--<span  style="padding-right:20px;"> <input type="button"  class="easyui-button" id="close" value="Back"/>   </span>-->
<table id="kznGridSubGroup" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="" />
<input type="hidden" id="hiddenComp" value="${requestScope.compId}"/>
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>