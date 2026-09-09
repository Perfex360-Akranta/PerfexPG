<script>
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmQADaily");
	invokeAfterLoadFormCallBack();
	
	//viewGrid(url,"?q=2");
});
function frmQADaily_afterLoadCallBack(){
		toggleCommonFilter();	
	
}
function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		processGridnew(url,filterString,"QADaily","pager","QA Daily Rejection Report","","","");
		return true;
	}
	return false;
}
function validateFilterSelection(filterString){
	
	if(filterString.length > 0  && ! checkFilterValueExist(filterString, "cmbSectid"))
	{
	alert("Select DMT");
	return false;
	}
  return  true;
}

</script>

<form name="frmQADaily" id="frmQMDaily"> 
<div id="wrapperRpt">
<table id="QADaily"><tr><td></td></tr></table>
<div id="pager"></div>
</div>

</form>