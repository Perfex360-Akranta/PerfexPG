<!--Author Manikandan-->


<script>
jQuery(document).ready(function(){	
	var dateTime = getServerDateTime();
	var fm = dateTime.getMonth()-1;
	var tm =dateTime.getMonth();			
	var fromMon = getMonthStringFromInt(fm);
	var toMon = getMonthStringFromInt(tm);			
	var FromDate = "01-"+fromMon+"-"+dateTime.getFullYear();
	var ToDate = dateTime.getDay()+"-"+toMon+"-"+dateTime.getFullYear();

	var range = 10;	
	var url = jQuery('#hiddenUrl').val();
	
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	dataString += "&range="+range;
	//alert(dataString);
	viewGrid(url,dataString);	

});


function viewGrid(url,filterString)
{
	/*var range = jQuery('#rng').val();
	alert("rangejsp  "+range);	
	filterString += "&range="+range;*/
	//alert(filterString.length);\
	var range = jQuery('#rng').val();
	
	if(range.length>0)
			filterString += "&range=" + range;
	else if(filterString.length > 0)
		filterString += "&range=" + range;
			
	if(validateFilterSelection(filterString))
	{
		var tableCaption = "Top N Failure Report";
		
		
		processGridnew(url,filterString,"list","pager",tableCaption,"","","");
		//jQuery("#list").setGridParam({url:"EqpReport_view.eqm"+dataString,dataType: "json" }).trigger('reloadGrid');
		
	}	
}
function validateFilterSelection(filterString){
//alert(filterString);
	if( filterString.length != 0)
	{ //alert(filterString);
		 if( ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Enter From date");
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Enter To date");
			return false;
		}
		else if( ! checkFilterValueExist(filterString+="&", "range"))
			{
				alert("Enter range");
				return false;
			}
	}	
	return  true;
}

</script>

<br/><br/>
<div id="wrapperRpt">
<div class="cntborder">
<div style="padding-left:1.8%" class="mrgnleftsxt">
		<label>Enter the Range</label>
			<input type="text" id="rng" class="easyui-text easyui-paddingbfpx" style="width:3%">
		</div>


<div style=""> 
	 <table id="list" style="width:100%"><tr><td/></tr></table>
	 <div id="pager"></div>
</div>
</div>
</div>
		
		