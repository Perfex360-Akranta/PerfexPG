
<script>
jQuery(document).ready(function(){	

	viewGrid("FiveW2HRpt_input.fwh","");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Action Plan Report";
		processGrid(url,filterString,"list","pager",tableCaption);
	}	
}

function validateFilterSelection(filterString){
	return  true;
}
</script>

<form>
<div id="wrapperRpt">
<table id="list" ></table>
<div id="pager"></div>
	</div>
	</form>