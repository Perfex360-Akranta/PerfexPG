<script>
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmPhenWiseRpt");
	invokeAfterLoadFormCallBack();
});
function frmPhenWiseRpt_afterLoadCallBack(){
		toggleCommonFilter();
}

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		processGridnew(url,filterString,"PhenWiseRpt","pager","Phenomena Wise Report","","","PhenWiseRpt_loadComplete");
		return true;
	}
	return false;
}
function PhenWiseRpt_loadComplete(){
	var url = jQuery('#hiddenUrl').val();
	if(url == "PhenWiseRpt_input.QMSTR")
	  setTotalRowCss("PhenWiseRpt");
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

<form name="frmPhenWiseRpt" id="frmPhenWiseRpt"> 
<div id="wrapperRpt">
<table id="PhenWiseRpt"><tr><td></td></tr></table>
<div id="pager"></div>
</div>

</form>