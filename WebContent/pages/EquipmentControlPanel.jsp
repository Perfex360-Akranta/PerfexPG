
<script>
jQuery(document).ready(function(){	
var dateTime = getServerDateTime();	
var FromDate = dateTime.getMonth()-1+"/01/"+dateTime.getFullYear();
var ToDate=dateTime.getMonth()+"/"+dateTime.getDay()+"/"+dateTime.getFullYear();
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
		var tableCaption = "Equipment control Panel";
		processGridnew(url,filterString,"list","pager",tableCaption);
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