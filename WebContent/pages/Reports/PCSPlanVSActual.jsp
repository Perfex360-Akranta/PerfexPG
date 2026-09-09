<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	var  prevData = unescape(jQuery('#hdnPrevDataUrl').val());		
	setLoadFormCallBackFrmId("frmPcsPlanVsActual");
	invokeAfterLoadFormCallBack();
	jQuery('#hdnfromBackPcs').val(prevData);
	viewGrid(actionPart,"?q=1");
	/*jQuery('#btnGraph').click(function(){
		var rowid = jQuery("#abnCumulativeGrid").jqGrid('getGridParam','selrow');	
		var url = "chart.abnCumulative";
		showGraphData(url);
	});*/	
});
function frmPcsPlanVsActual_afterLoadCallBack(){
	if(jQuery('#hdnfromBackPcs').val() == "")
		toggleCommonFilter();		
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		filterString += '&drillFlag=f&firstClick=Y';		
		var tableCaption = "Planned Vs Actual";
		processGridnew(url,filterString,"pcsPlanVsActualGrid","pcsPlanVsActualPager",tableCaption,"pcsPlanVsActualdoubleClick","","pcsPlanVsActualCumulative");		
		return true;
	}
	
	return false;	
}

function validateFilterSelection(filterString){
	
	if(filterString=="?q=1")
		return true;
	else{ 
	if(!filterMonthnDateDifference(filterString,40,24))
		return false;
	}
	if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
		alert("Select either Date or Month ");
		return false;
	}	

	if(jQuery('#hdnfromBackPcs').val() == "" || jQuery('#hdnfilterClicked').val()== "true"){
		if(getFilterValue(filterString, "cmbSectid") == ""  && !checkFilterValueExist(filterString,"cmbCircle")){
			alert("Select Section");
			return false;
			}
		
	}
	
	return  true;
}




function pcsPlanVsActualdoubleClick(id){	
	
}
function pcsPlanVsActualCumulative()
{
	var row = jQuery("#pcsPlanVsActualGrid").jqGrid('getDataIDs');
	setTotalRowCss('pcsPlanVsActualGrid');
	jQuery("#" + row[row.length-2]).find("td").addClass('cumulativeRow');	
}


function frmFilter_enableDisableSuccessCallBack()
{
	var url = jQuery('#hiddenUrl').val();
	if(jQuery('#hdnPrevDataUrl').val().length > 0){
		jQuery('#hdnfilterClicked').val("true");
	}
	enableDisableDatenMonthFilter();	
}


</script>

<form id="frmPcsPlanVsActual">
<div id="wrapperRpt" style="max-width: 1123px;">
<!--<div class="floatright" style="padding-right:20px;"><input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>   -->

<!--<div id="divGraphContainer" ></div>	-->
<!--<div class="clear"></div>-->
<table id="pcsPlanVsActualGrid" ></table>
<div id="pcsPlanVsActualPager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnfromBackPcs" name="hdnfromBackPcs"  />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnfilterClicked" name="hdnfilterClicked"  />
</div>
</form>
	



