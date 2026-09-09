<style>
</style>
<script type="text/javascript">
	jQuery.noConflict();
	var FromMonth = null;
	var ToMonth= null;
	jQuery(document).ready(
			function() {
				 var url = jQuery('#hiddenUrl').val();
			
				setLoadFormCallBackFrmId("frmtransaction");	
				var dataString ="";
				dataString += "?dtFromMonth="+FromMonth;
				dataString += "&dtToMonth="+ToMonth;
				invokeAfterLoadFormCallBack();
				//viewGrid(url,dataString);
			});
	 function frmtransaction_afterLoadCallBack(){
		toggleCommonFilter();  
  } 
  

	function viewGrid(url,dataString)
	{ 
		if( validateFilterSelection(dataString))
		{ 		
	    	 fromMonth = getFilterValue(dataString,"dtFromMonth");
			// alert("fromMonth:::"+fromMonth);
			 toMonth = getFilterValue(dataString,"dtToMonth");//alert("fromdate");
			// alert("toMonth:::"+toMonth);
			processGridnew(url,dataString,"transactionSmryViewGrid","pager","","docDoubleClick");
             return true;
	}
      return false;	
	}
	

/*   function validateFilterSelection(dataString){	 
		if( dataString.length != 0)
		{
			 if( ! checkFilterValueExist(dataString, "dtFromMonth"))
			{
				alert("Enter From Month");
				return false;
			}
			else if( ! checkFilterValueExist(dataString, "dtToMonth"))
			{
				//alert("Enter To Month");
				return false;
			}		
		}
      return  true;
}	
   */
  function validateFilterSelection(dataString){	 
		if( dataString.length != 0)
			//alert("dataString" + dataString);
		{
			if(jQuery('#chkMonthwise').is(':checked') == true){
				if( dataString.length > 0  &&  ! checkFilterValueExist(dataString, "dtFromMonth"))
				{
					alert("Select  FromMonth");
					return false;
				}
				if( dataString.length > 0  &&  ! checkFilterValueExist(dataString, "dtToMonth"))
				{
					alert("Select  ToMonth");
					return false;
				}
			}
			if(jQuery('#chkMonthwise').is(':checked') == false){
				if( dataString.length > 0  &&  ! checkFilterValueExist(dataString, "dtFromDate"))
				{
					alert("Select  FromMonth");
					return false;
				}
				if( dataString.length > 0  &&  ! checkFilterValueExist(dataString, "dtToDate"))
				{
					alert("Select  ToMonth");
					return false;
				}
			}
			 
		}
    return  true;
}

  
	function getSelectedCellRow(gridId)
	{
		return jQuery('table#'+gridId+' tr.selected-row ').index();
	}
	function getSelectedCellColumn(gridId)
	{
		return jQuery('table#'+gridId+' tr.selected-row  > td.ui-state-highlight ').index();
	}

	function docDoubleClick(id){
		var rowData = jQuery("#transactionSmryViewGrid").jqGrid('getRowData',id);
		var Cellrow=getSelectedCellRow("transactionSmryViewGrid");
		var Cellcolumn=getSelectedCellColumn("transactionSmryViewGrid");
	    var Flid="";
		Flid=jQuery("#hdnflid").val();
		var FunclocationId=rowData.flids;
		if(Cellcolumn=='5'){
			LoadPopUp("loadSuggestion","KaizenSuggestionPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Suggestion");
		}
		else if(Cellcolumn=='6'){
			LoadPopUp("loadKaizen","kaizenPopup_input.trs?q=2&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Kaizen");
		}
		else if(Cellcolumn=='7'){
			LoadPopUp("loadOPL","oplPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","OPL");				
		}
		else if(Cellcolumn=='8'){			
			LoadPopUp("loadAbmormality","abnormalitypopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Abnormality");
		} 
		else if(Cellcolumn=='9'){
			LoadPopUp("loadMeeting","meetingPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Meetings");
		}
		else if(Cellcolumn=='10'){
			LoadPopUp("loadNearmiss","NearMisspopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","NearMiss");
		}
		else if(Cellcolumn=='11'){
			LoadPopUp("loadWhyWhy","whywhyanalysispopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","WhyWhy");
		}
		
		else if(Cellcolumn=='12'){ 
			LoadPopUp("loadActionPlan","actionplanPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","ActionPlan"); 	
		}
		else if(Cellcolumn=='13'){
			LoadPopUp("loadLoss","lossPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Loss");
		}
		else if(Cellcolumn=='14'){
			LoadPopUp("loadKPI","KpiPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","KPI");
		} 
		else if(Cellcolumn=='15'){
			LoadPopUp("loadSusa","susapopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Susa");
		}
		else if(Cellcolumn=='16'){
			LoadPopUp("loadUnsafeWorkPractics","unsafeworkpopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","UnsafeWorkPractics");
		}
		else if(Cellcolumn=='17'){
			LoadPopUp("loadKnowwhy","knowwhypopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","KnowWhy");	     
		}
		else if(Cellcolumn=='18'){
			LoadPopUp("loadComplaintgallery","ComplaintGalleryPopup_input.trs?&FunclocationId="+FunclocationId+"&fromMonth="+fromMonth+"&toMonth="+toMonth,true,"95%","90%","3%","1%","popup_callback()","Complaint Gallery");
		}
	}
	
	function loadSuggestion_afterClose()
	{
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}
	
	function loadKaizen_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
	}
	
	function loadOPL_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}
	
	function loadAbmormality_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}

	function loadMeeting_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}
	function loadNearmiss_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}
	function loadWhyWhy_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}	
	
	function loadActionPlan_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}	
	function loadLoss_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}	
	function loadKPI_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}	
	function loadSusa_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}
	
	function loadUnsafeWorkPractics_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}	
	function loadKnowwhy_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
		
	}	
	
	function loadComplaintgallery_afterClose(){
		jQuery("#transactionSmryViewGrid").trigger("reloadGrid");
		refreshForm();
	}
	
</script>
<form id="frmtransaction">
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
<div style="margin-left:1px;margin-top:20px;">
	 <label><b>Double Click on Row to View Data in Popup</label></b>	
	 </div>		
	<table id='transactionSmryViewGrid'>
		<tr>
			<td></td>
		<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}">
</form>
			
	
