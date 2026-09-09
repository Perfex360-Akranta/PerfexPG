<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script>

jQuery(document).ready(function(){	

	viewGrid("ManPowerCost_input.ManPwrCostRpt","");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Man Power Cost";
		processGrid(url,filterString,"list","pager",tableCaption,"");
		//jQuery("#list").setGridParam({url:"EqpReport_view.eqm"+dataString,dataType: "json" }).trigger('reloadGrid');
		
	}	
}

function validateFilterSelection(filterString){
	return  true;
}
</script>
<div id="wrapper">
<table id="list" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>