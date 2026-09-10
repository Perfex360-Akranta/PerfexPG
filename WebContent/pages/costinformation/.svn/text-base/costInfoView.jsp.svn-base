
<script type="text/javascript">

		jQuery.noConflict();
		
		jQuery(document).ready(function(){

			jQuery('#submitForm').val('frmcostInfoView');
				

			var currentTime = getServerDateTime();
			var curmonth = currentTime.getMonth();
			var premonth = currentTime.getMonth()-1;
			var year = currentTime.getFullYear();
			var day = currentTime.getDate()+1;			
				
			var fromMon = getMonthStringFromInt(premonth==-1?11:premonth);
			var toMon = getMonthStringFromInt(curmonth);
			var fromYear = premonth==-1?year-1:year;
			if (day < 10){
				day = "0" + day;
			}	
			var FromDate = "01-"+fromMon+"-"+fromYear;
			var ToDate = day+"-"+toMon+"-"+year;
			
			//var reportType = "BreakDown";
			
			var reportType = jQuery('#hdnReportType').val();			
			
			var url = "CostRpt_view.crt";
			
			var dataString ="";
			dataString += "?q=2&dtFromDate="+FromDate;
			dataString += "&dtToDate="+ToDate;
		    dataString += "&cmbReportType="+reportType;
			
			viewGrid(url,dataString);	

			jQuery("#btnView").click(function(){

				var url = jQuery('#hiddenUrl').val();	
				if( url != null && url.length > 2 ){
					var formHeader = getFormMainHeader();	
					url = url.replace("_view","_input");	
					url += (url.indexOf('?') >= 0 ? '&':'?') + 'userEvent=new';  
					navigateToNextForm(url,formHeader);
				}
			});			
			
		});
		function viewGrid(url,filterString)
		{	
			////alert(url);
			//alert(filterString);
			processGridnew(url,filterString,"costinfoviewGrid","costinfoviewPager","Cost Information","costInfo_dblClick","","LoadInforGrid");
			return true;		
		}
		function costInfo_dblClick(id)
		{
			var rowData = jQuery("#costinfoviewGrid").jqGrid('getRowData',id);
			var dataStr = convertJSONToDataString(rowData);
			var docType = jQuery('#hdnReportType').val();
			dataStr += 'DocType='+docType;	
			dataStr += '&formType=Actual';
			
			//alert(dataStr);			
			navigateToNextForm('costSummary_input.crt?'+dataStr,'Cost Information - Actual Value');						
			//navigateToNextForm('Breakdown_input.brdn'+'?BDKeyid='+keyId,'Breakdown Analysis');
		}
		
		function LoadInforGrid()
		{
			//alert("loadedd");
			var bdGridId = jQuery("#costinfoviewGrid").jqGrid('getDataIDs');
			 for(i=1;i<=bdGridId.length;i++)	
			 {			 
				//if(jQuery("#list").getCell(i, 'status')=="COMPLETED")
				//	jQuery("#list").jqGrid('setCell',i,"keyId","",{'background-color':'#c0ffc0'});							
					
			 }		
		}
		
</script>

<input type=hidden id="hdnReportType" name="hdnReportType" value="${requestScope.reportType}">

<form id="frmcostInfoView" name="frmcostInfoView" style="margin-top:1%;">
 <span style="padding-left:990px;margin-top:5px;">
   	
    <input type="button" class="easyui-button"  id="btnView" value="View" style="width:100px;height: 25px;"/>
</span>
<div style="float: left;padding-left: 30px;margin-top: -15px">
<!--<div style="margin-left:4%; _margin-left:3%;margin-top:-33px; _margin-top:0%;"> -->
	 <table id="costinfoviewGrid"><tr><td/></tr></table>
	 <div id="costinfoviewPager"></div>
</div>
</form>