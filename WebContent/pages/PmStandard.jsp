
<script>
jQuery(document).ready(function(){	
	var dateTime = getServerDateTime();
	var fm = dateTime.getMonth()-1;
	var tm =dateTime.getMonth();			
	var fromMon = getMonthStringFromInt(fm);
	var toMon = getMonthStringFromInt(tm);			
	var FromDate = "01-"+fromMon+"-"+dateTime.getFullYear();
	var ToDate = dateTime.getDay()+"-"+toMon+"-"+dateTime.getFullYear();
					
	var url = jQuery('#hiddenUrl').val();
	
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	
	viewGrid(url,dataString);	
	});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "PM Standards";
		processGrid(url,filterString,"list","pager",tableCaption);
		return true;
	}	
	return false;
}

function validateFilterSelection(filterString){
	
	return  true;
}


</script>

<form>
<div id="wrapperRpt">
<table id="list" style="width:100%">
	<tr><td/></tr></table>

			<div id="pager"></div>
</div>
</form>