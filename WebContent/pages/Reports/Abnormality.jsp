<!--  Author ManiKandan-->

<script>
jQuery(document).ready(function(){	

	//viewGrid("AbnRpt_input.abnRpt","");
	var url = jQuery('#hiddenUrl').val();
	var FromDate = null;
	var ToDate = null;
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	viewGrid(url,dataString);

	

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var fromDate = getFilterValue(filterString,"dtFromDate");
		var toDate = getFilterValue(filterString,"dtToDate");//alert("fromdate");
		var tableCaption = "Abnormality Reports From Date:"+fromDate+" To Date:"+toDate;
		processGridnew(url,filterString,"list","pager",tableCaption);
		return true;
	}
	return false;	
}
function formatColumnValues(cellvalue, options, rowObject) {	
	//alert(cellvalue);
	var formatStr; //= "<img src =\"images/annualcal/";
	if(cellvalue==1)
	{	
		formatStr = "<span style=\"color:blue;font-size:25px;\"> &#10003;</span>" ;
		return formatStr;
	}

	return " ";
}


function validateFilterSelection(filterString){

	if( filterString.length != 0)
	{
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
		
			
	}	
	return  true;
}
								  

        </script>
        <form name="form_list" method="post">
         <input type="hidden" name="exporthtml" id="exporthtml" />
        
   	<div id="wrapperRpt" style="margin-top: 10px;">
   			<table id="list" border="1" rules="all" ></table>
			<div id="pager"></div>
	   </div>
	</form>