  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script>

jQuery.noConflict();
jQuery(document).ready(function(){

	initialiseForm('frmPendingActivitydwnload');
	
	processGridnew("clitCompliance_input.clitcal","&q=2","gridData","gridpager","","","","loadSuccess");
	var url="clitCompliance_input.clitcal";
	//viewGrid(url,dataStr);	
	
});

function loadSuccess(){}
/*
function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		url = url.replace("_getData.clitcal","_view.clitcal");
		if(url.indexOf('UPM') >=0 || url.indexOf('unplanned')>=0)
			filterString += '&maintMode=upm';
		processGridnew(url,filterString,"gridData","gridpager","","","","");
		return true;	
	}
	return false;	
}
function validateFilterSelection(filterString){
	return true;
}
function loadSuccess(){}
*/

</script>
<form id="frmPendingActivitydwnload" name="frmActivitydwnload">
<div id="wrapperRpt">
<div style="margin-top: -28px">


</div>
<div> 
	 <table id="gridData"><tr><td/></tr></table>
<div style="float:left;"> 
	 <table id="gridData" style="width:100%%;"><tr><td/></tr></table>
	 <div id="gridpager"></div>
</div>
</div>
</div>
<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" />

</form>