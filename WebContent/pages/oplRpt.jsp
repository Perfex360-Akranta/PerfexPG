
<script type="text/javascript">

		jQuery.noConflict();
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
			//if( validateFilterSelection(filterString))
		//	{				
				processGrid(url,filterString,"list","pager","","");
		//	}	
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

<form>
<div id="wrapperRpt">
	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
</form>
