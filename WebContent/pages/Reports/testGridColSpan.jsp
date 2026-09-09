
<script type="text/javascript">
jQuery(document).ready(function(){	

	var url = jQuery('#hiddenUrl').val();
	viewGrid(url);

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Test Report";
		processGridnew(url,filterString,"list","pager",tableCaption);
		
	}	
}

function validateFilterSelection(filterString){

	return  true;
}
								  

</script>
	
<form name="form_abn" method="post">
<div id="wrapperRpt">
	<table id="list" ></table>
	<div id="pager"></div>
</div>
</form>
	