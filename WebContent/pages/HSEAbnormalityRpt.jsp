<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

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
			alert(dataString);
			viewGrid(url,dataString);		

		});

		
		function viewGrid(url,filterString)
		{				
				processGrid(url,filterString,"list","pager","","");
			
		}

</script>

<form>
<div id="wrapperRpt">
<div class="main-header">HSEAbnormality Report </div>

<div class="cntborder">

	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
		<div class="clearfix"></div>
</div>
</div>
</form>