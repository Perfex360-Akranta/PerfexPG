<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){

			var dataString = " ";
			var url = jQuery('#hiddenUrl').val();
			
			
			
			var actionPart = jQuery('#hiddenUrl').val();	
			var prevDataUrl = jQuery('#hdnPreviousDataUrl').val();
			var fromdate = jQuery("#hdndtFromMonth").val();
			var todate = jQuery("#hdndtToMonth").val();
			var parentId = jQuery("#hdnparentId").val();
			
			
			if( prevDataUrl == null || prevDataUrl.length <=0)
			{	
				viewGrid(url,"?q=1");
			}
			else
			{
				viewGrid(url,unescape(prevDataUrl));
				/*var filterData ="?q=1";
				filterData += "&dtFromMonth="+fromdate;
				filterData += "&dtToMonth="+todate;
				filterData += "&parentId="+parentId;
				viewGrid(url,filterData);*/
			}
			
		});
		
		function viewGrid(url,filterString)
		{
		
			if( validateFilterSelection(filterString))
			{
				
				url = url.replace("_getData.accIncRpt","_view.accIncRpt");					
				processGridnew(url,filterString,"incidentRptViewGrid","incidentRptViewPager","Incidence Recording View","fillModifcnForm","","IRMGrid");
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function fillModifcnForm(id)
		{
			var rowData = jQuery("#incidentRptViewGrid").jqGrid('getRowData',id);		
			var keyId = rowData.INCIDENTNO;
			var status = rowData.STATUS;
			var yy = rowData.WHYWHYSTATUS;
			var url = jQuery("#incidentRptViewGrid").jqGrid('getGridParam', 'url');
			url = url.replace("_getData","_input");
			url = escape(url);	
		
			var dataString = '?filterButton=false&keyId='+keyId;			
			var title='Incident Reporting View';
			
		
				dataString += '&mode=VIEW';
			
				 if(status != null && status != '' && status != ' ')
				 {
					 if(status == 'OPEN')
					 {
						 if(yy != null && yy != '' && yy != ' ')
						 {
						  if(yy == 'COMPLETE')
							  dataString += '&pending=actionPlan';
						  else
							  dataString += '&pending=yy';	 
						 }
					 }
				 }
				
			navigateToNextForm('safteyincident_input.accIncRpt'+dataString,title,null,{"filterString":url});
		}
		function IRMGrid()
		{
			 var docInsRowId = jQuery("#incidentRptViewGrid").jqGrid('getDataIDs');
			 for(i=1;i<=docInsRowId.length;i++)	
			 {
				 var yy = jQuery("#incidentRptViewGrid").getCell(i, 'WhyWhyStatus26');
				 var status = jQuery("#incidentRptViewGrid").getCell(i, 'Status1');
				 if(status != null && status != '' && status != ' ')
				 {
				 if(status == 'OPEN')
				 {
					 if(yy != null && yy != '' && yy != ' ')
					 {
					  if(yy == 'COMPLETE')
						 jQuery("#incidentRptViewGrid").jqGrid('setCell',i,"Status1","",{'background-color':'#E0C3C3'});
					  else
						 jQuery("#incidentRptViewGrid").jqGrid('setCell',i,"Status1","",{'background-color':'#afd6fe'});	 
					 }
				 }
				 else if(status == 'CLOSE')
					jQuery("#incidentRptViewGrid").jqGrid('setCell',i,"IncidentNo2","",{'background-color':'#F9A2AC'});
				 }
			 }
		}
</script>
<div id="wrapperRpt">
<table  style="margin-top:-10px;">
	<tr>
	<td><span class="bd-repeatedbd"></span></td>
	<td><label style="color:dark brown;font-weight: bold ;padding-left:2px; ">Completed</label></td>
	<td><span class="bd-yypending"></span></td>
	<td><label style="color:dark brown;font-weight: bold ; ">Why Why Pending</label></td>
	<td><span class="bd-countermeasurepending"></span></td>
	<td><label style="color:dark brown;font-weight: bold ; ">Action Plan Pending</label></td>
	<td><div class="notes"  style="font-weight: bold; padding-left:20px; color: blue;" >  Double Click on Row to View Data </div></td>
	</tr>
</table>
<!--	<table id="docInsTable">-->
<!--		<tr>-->
<!--			<td><span class="bd-repeatedbd"></span></td>-->
<!--			<td><span><label style="color:dark brown;font-weight: bold">Completed</label></span></td>-->
<!--			<td><div class="notes"  style="font-weight: bold; padding-left:20px; color: blue;" >  Double Click on Row to View Data </div></td>-->
<!--		</tr>	-->
<!--	</table>-->
<div  > 
	 <table id="incidentRptViewGrid" style="width:100%"><tr><td/></tr></table>
	 <div id="incidentRptViewPager"></div>
</div>
</div>
<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnparentId" name="hdnparentId" value="${requestScope.parentId}" />
<input type="hidden" id="hdndtFromMonth" name="hdndtFromMonth" value="${requestScope.dtFromMonth}" />
<input type="hidden" id="hdndtToMonth" name="hdndtToMonth" value="${requestScope.dtToMonth}" />
