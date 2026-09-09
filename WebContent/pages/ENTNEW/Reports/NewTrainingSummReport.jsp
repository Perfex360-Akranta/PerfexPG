<script type="text/javascript">
jQuery(document).ready(function(){	
	initialiseForm('frmTrainingSummrpt');
	var url = jQuery('#hiddenUrl').val();	
	viewGrid(url,"&q=2");
});

function viewGrid(url,filterString)
{
	if(validateFilterSelection(filterString))
	{
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		filterString += '&drillFlag=f&firstClick=Y';
		jQuery('#hdnFilterString').val(filterString);
		var tableCaption = "Cummulative Report";
		processGridnew(url,filterString,"safeunsafegrd","pager",tableCaption,"","","");
		return true;
	}
	return false;
}

 function validateFilterSelection(filterString)
 {
	return  true;
}
</script>
<form id="frmTrainingSummrpt">
 <div id="wrapperRpt" style="max-width: 1210px;">
 <table>
<tr>
 <td >
</td>
</tr>			
</table>	
<div style="margin-top: -5px">
	<div id="divGraphContainer" ></div>	
	<table id="safeunsafegrd" ></table>
	<div id="pager"></div>
	</div>
	<input type="hidden" id="hiddenStr" value="sdsd" />
</div>	
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
<input type="hidden" id="hiddenRemoveBlank" value=""  />
<input type="hidden" id="hdnFnlnKeyid" />
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}">
</form>