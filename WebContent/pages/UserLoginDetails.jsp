<script>

   jQuery(document).ready(function(){
	   var url = jQuery('#hiddenUrl').val();
	   var filterString = "?q=2&firstClick=Y";
	   setLoadFormCallBackFrmId("frmLoginDetails");
	   invokeAfterLoadFormCallBack();
	 //  viewGrid(url, filterString);//"?q=2",
	});

   function viewGrid(url,filterString)
   {     
   	jQuery('#hdnFilterString').val(filterString);
      	if( validateFilterSelection(filterString))
   	{   
   		var flid = getFilterValue(filterString, 'flid');
   		var FromDte = getFilterValue(filterString, "dtFromDate");
   		var ToDte = getFilterValue(filterString, "dtToDate");
   		var Frommonth = getFilterValue(filterString, "dtFromMonth");
   		var Tomonth = getFilterValue(filterString, "dtToMonth");
   		filterString += '&flid='+flid;
   		var tableCaption = "User Login Details";
   		processGridnew(url,filterString,"LoginDetailstableid","LoginDetailspagerid");
   		return true; 
   	}
   	return false;
   }
   
   function validateFilterSelection(filterString){
		return true;
		
   }

   function frmLoginDetails_afterLoadCallBack(){
		toggleCommonFilter();  
		}
   
   
</script>

<form name="frmLoginDetails" id="frmLoginDetails" action=" " method="post">
<div id='WrapperRpt'>
<table id='LoginDetailstableid'><tr><td></td></tr></table>
<div id='LoginDetailspagerid'></div>
</div>
</form>
