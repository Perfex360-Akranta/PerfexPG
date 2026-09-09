<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	var dataString ="";
	var url = jQuery('#hiddenUrl').val();
	var disableBooking = jQuery('#hdnBooking').val();
	
	if(disableBooking=="true"){
		jQuery('#btnnewBooking').css('display','none');
	}
	
	var  prevDataUrl = jQuery('#hdnWOPreviousDataUrl').val();
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid(url,"?q=1");
	else{
		viewGrid(unescape(prevDataUrl),"&q=1");
	}	
	
	jQuery('#btnnewBooking').click(function(event){	
		navigateToNextForm("serviceBooking_input.serv?mode=create","Service Booking");
	});
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{	
		
		processGridnew(url,filterString,"serviceGrid","pager","","doubleClickGrid","","ServiceBookingGrid");
		return true;
	}
	return false;
}
function frmFilter_enableDisableSuccessCallBack()
{
	
	
			
	
}


function validateFilterSelection(filterString){
		return  true;
}
function doubleClickGrid(rowid) 
{
	var url = jQuery('#hiddenUrl').val();
	var title = "Service Booking";	
	var rowData = jQuery("#serviceGrid").jqGrid('getRowData',rowid );	
	var ds = "serviceBooking_input.serv?&filterButton=false&keyId="+rowData.serviceId;
	
	if(url.indexOf('Booking')>0)
		ds += "&disableAllocation=Y&disableApproval=Y&mode=EDIT";
	else  if(url.indexOf('Approval')>0)
	{
		ds += "&disableAllocation=Y&mode=EDITAPPROVAL";
		title = "Service Approval/Cancel";
	}
	else if(url.indexOf('Allocation')>0){
		ds +="&mode=EDITALLOCATION&filemanager=N&disableApproval=Y";
		title = "Service Allocation/Completion";
	}
	else if(url.substr(0,11)=="serviceView"){
		ds +="&mode=VIEW&filemanager=N";
		title = "Service View";
	}
	else if(url.indexOf('Closure')>0)
	{
		ds += "&mode=CLOSURE&filemanager=N";
		title = "Service Closure";
	}
	navigateToNextForm(ds,title);
}	
function ServiceBookingGrid()
{
	var bdGridId = jQuery("#serviceGrid").jqGrid('getDataIDs');
	//alert(bdGridId);
	 for(i=1;i<=bdGridId.length;i++)	
	 {			
			if(jQuery("#serviceGrid").getCell(i, 'Status') == "Booked")
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Booked');
			else if(jQuery("#serviceGrid").getCell(i, 'Status')=="Completed")
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Completed');
			else if(jQuery("#serviceGrid").getCell(i, 'Status')== "Allocated")
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Allotted');//'#E0C3C3'
			else if(jQuery("#serviceGrid").getCell(i, 'Status')=="Cancelled" )
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Cancelled');//'#c0ffc0'
			else if(jQuery("#serviceGrid").getCell(i, 'Status')=="Approved" )
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Approved');
			else if(jQuery("#serviceGrid").getCell(i, 'Status')=="Closed" )
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Closed');
			else if(jQuery("#serviceGrid").getCell(i, 'Status')=="Reopen" )
				jQuery("#serviceGrid").jqGrid('setCell',i,"Status","",'service-Reopened');
	}		
}
	
</script>
<form id="frmServiceGrid">
 
 <div ></div>
 <div id="wrapperRpt">
 <div style="">			
			<table>	
				<tr>
					<td><span class="wo-priority service-Booked"></span></td>
					<td>&nbsp;</td>
					<td><label class="wo-LegendLabel">Booked</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td><span class="wo-priority service-Approved"></span></td>
					<td>&nbsp;</td>
					<td><label class="wo-LegendLabel">Approved</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>						
					<td><span class="wo-priority service-Cancelled"></span></td>	
					<td>&nbsp;</td>
					<td><label class="wo-LegendLabel">Cancelled</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>						 
					<td><span class="wo-priority service-Allotted"></span></td>	
					<td>&nbsp;</td>
					<td><label class="wo-LegendLabel" >Allocated</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>					
					<td><span class="wo-priority service-Completed"></span></td>	
					<td>&nbsp;</td>
					<td><label  class="wo-LegendLabel">Completed</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>	
					<td><span class="wo-priority service-Closed"></span></td>	
					<td>&nbsp;</td>
					<td><label  class="wo-LegendLabel">Closed</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>	
					<td><span class="wo-priority service-Reopened"></span></td>	
					<td>&nbsp;</td>
					<td><label  class="wo-LegendLabel">Reopened</label></td>
					<td>&nbsp;&nbsp;&nbsp;</td>	
					<td><input id="btnnewBooking" class="easyui-button" type="button" value="New Booking" style="height:21px;z-index:110;float:right;'" /></td>
					
				</tr>
				</table>	
			 </div>
	
	
	<table id="serviceGrid" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
	<input type="hidden" id="hdnBooking" name="hdnBooking" value="${requestScope.disableBooking}" />
</div>
</form>
