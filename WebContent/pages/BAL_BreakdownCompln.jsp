 <!-- 	 Created By Suresh.K on Dec 19 2011-->
 <script type="text/javascript">	
       jQuery(document).ready(function(){
    	   initialiseForm('frmBDCompln');
    		var currentTime = getServerDateTime();
			var curmonth = currentTime.getMonth();
			var premonth = currentTime.getMonth()-1;
			var year = currentTime.getFullYear();
			var day = currentTime.getDate()+1;			
			var fromMon = getMonthStringFromInt(premonth);
			var toMon = getMonthStringFromInt(curmonth);

			if (day < 10){
				day = "0" + day;
			}	
					
			var FromDate = "01-"+fromMon+"-"+year;
			var ToDate = day+"-"+toMon+"-"+year;			
							
			var url = jQuery('#hiddenUrl').val();
			
			var dataString ="";
			dataString += "?dtFromDate="+FromDate;
			dataString += "&dtToDate="+ToDate;
			viewGrid(url,dataString);	
						
        });

       function viewGrid(url,filterString)
		{
			processGridnew(url,filterString,"list","pager","BreakDown Master","fillForm");		
		}
		function fillForm(id)
		{
			var rowData = jQuery("#list").jqGrid('getRowData',id);			
			var keyId = rowData.keyId;
			//var keyId = rowData.keyId;			
			navigateToNextForm('Breakdown_input.Bbrdn'+'?keyId='+keyId,'Breakdown Analysis');
		}
 </script>
 <form name="frmBDCompln" id="frmBDCompln" action="SparesMaster_input.sm" method="post">
 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
</div>
 </form>