<script type="text/javascript">
jQuery(document).ready(function()
{	
//	alert("Implemented transaction");
	var actionPart = jQuery('#hiddenUrl').val();	
	var keyId = jQuery("#hiddenComp").val();
	//alert("actionPart:" +keyId);
	//alert("keyId" +keyId);
	chkRelatedResult();
	
	//jQuery("#chkResultWise").attr("checked",true);
	var comp=jQuery("#hiddenComp").val();
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
    //alert("1 " +prevDataUrl);	
		
});

function viewGrid(url,filterString)
{ 
	var prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
	if( validateFilterSelection(filterString))
	{	
		var comp=jQuery("#hiddenComp").val();
		processGridnew(url,prevDataUrl,"kznGridView","pager","","doubleClickGrid","","ImprovementSmry_loadComplete");
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
	
	var url="ImpSmrykaizen_input.impSmrRpt?";	
	compId = jQuery("#hiddenComp").val();	
	var filterStr = '';
	filterStr += "&flid="+compId;	
	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();	
	
	if( prevDataUrl == null || prevDataUrl.length <=0)	{		
		//alert("prevDataUrl == null:" +prevDataUrl);
		viewGrid("ImpSmry_input.impSmrRpt","?q=1&firstClick=Y");
	}else{
		//alert("prevDataUrl test:" +prevDataUrl);
		viewGrid(url,unescape(prevDataUrl));
	}
	
}
	

</script>

<div id="wrapperRpt">
<div class="clear"></div>	    

<table id="kznGridView" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="" />
<input type="hidden" id="hiddenComp" value="${requestScope.compId}"/>
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>