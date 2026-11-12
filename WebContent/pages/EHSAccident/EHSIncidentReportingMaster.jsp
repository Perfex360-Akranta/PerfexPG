<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
			
			var modeCheck = jQuery("#hdnmode").val();
			var roleId = jQuery("#hdnUserRole").val();
			
			
			if(roleId != 'AROL0079')
				
			{
				if(modeCheck == 'CA' || modeCheck == 'InvestRPT' || modeCheck == 'Response' || modeCheck  == 'Verify' || modeCheck == 'Closure'){
				 
				 console.log("Access DENIED - Role mismatch");
			        console.log("Expected: 'QM PILLAR Champion' (AROL0079)");
			       
			        
			        // Hide all form content
			        jQuery('#frmAccidentGrid').hide();
			        
			        // Show error message
			        if(typeof popupCommonErrorMsg === 'function') {
			            popupCommonErrorMsg("Please select the QM PILLAR Champion");
			        } else {
			            alert("Please select the QM PILLAR Champion ");
			        }
			        
			        // Redirect back after showing message
			        setTimeout(function() {
			            navigateToPrevForm();
			        }, 5000);
			        
			        return false;
				}
				
			}
			
			
			initialiseForm('frmAccidentGrid');

			var dataString = " ";
			var url = jQuery('#hiddenUrl').val();
			//alert(url);
			if(url.indexOf('Modify')>0)
				jQuery('#btnSpanNewBooking').hide();
			
			var formtype = jQuery("#hdnmode").val();
			
			if(formtype=="Modify"||formtype=="Delete"||formtype=="View")
			{
			//setLoadFormCallBackFrmId("frmAccidentGrid");
			}
			
			var actionPart = jQuery('#hiddenUrl').val();	
			var prevDataUrl = jQuery('#hdnPreviousDataUrl').val();

			if( prevDataUrl == null || prevDataUrl.length <=0)
			{	  
				viewGrid(url,"?q=1");
			}
			else
			{
				viewGrid(unescape(prevDataUrl),"?q=1");
			}
			
		});
		jQuery("#btnNewReporting").click(function(){
			navigateToNextForm('safteyincident_input.ehsaccIncRpt?q=2&bookingMode=Booking','Incident Reporting');
		});	
		function viewGrid(url,filterString)
		{
			
			if( validateFilterSelection(filterString))
			{
				url = url.replace("_getData.brdn","_view.brdn");					
				processGridnew(url,filterString,"incidentRptModifcnGrid","incidentRptModifcnPager","Incidence Recording Modification","fillModifcnForm","","IRMGrid");
				return true;	
			}
			return false;	
		}
		
		
		function frmAccidentGrid_afterLoadCallBack(){
			toggleCommonFilter();	
		}
	
		function validateFilterSelection(filterString)
		{
			return true;
		}
		

		function IncidentCA_afterClose()
		{
			//alert(1);
			jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
			
		}
		
		function IncidentResponse_afterClose()
		{
			//alert(1);
			jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
			
		}
		
		
		function IncidentAction_afterClose()
		{
			//alert(1);
			jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
			
		}
		
		
		function IncidentVerify_afterClose()
		{
			//alert(1);
			jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
			
		}
		
		
		function IncidentClosure_afterClose()
		{
			//alert(1);
			jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
			
		}
		function incidentRptModifcnGrid_selectRow(rowId){
			
			var mode = jQuery("#hdnmode").val();
			//alert("mode :"+mode);
			if(mode=="CA"){
				//fillModifcnForm(rowId);
				//var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"sinm_keyid");
				//incidentRptModifcnGrid_IncidentNo
				var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"IncidentNo");
			//	alert("keyid"+Keyid);
				LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
			}
			
			
			if(mode=="InvestRPT"){
				//fillModifcnForm(rowId);
				
				//var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"sinm_keyid");
				var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"IncidentNo");
				//alert("keyid"+Keyid);
				LoadPopUp("IncidentCA","safteyincidentInvesReport_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
			}
			if(mode=="Response"){
			//	if(jQuery('#incidentRptModifcnGrid_'+rowId).is(':checked')){
				//var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"sinm_keyid");
				var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"IncidentNo");
				LoadPopUp("IncidentResponse","AccResponsePOP_input.ehsaccIncRpt?q=2&Keyid="+Keyid, true,"50%","40%","30%","20%", "","Responsibility"," "," " );
			//	}else{
					
			//		return false;
			//	}
			}
			if(mode=="Action"){
				//var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"sinm_keyid");
				var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"IncidentNo");
				LoadPopUp("IncidentAction","AccActionPOP_input.ehsaccIncRpt?q=2&Keyid="+Keyid, true,"55%","40%","30%","20%", "","Action Closure"," "," " );
			}
			if(mode=="Verify"){
				//var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"sinm_keyid");
				var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"IncidentNo");
				LoadPopUp("IncidentVerify","AccVerifyPOP_input.ehsaccIncRpt?q=2&Keyid="+Keyid, true,"45%","45%","30%","30%", "","Verification"," "," " );
			}
			if(mode=="Closure"){
				//var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"sinm_keyid");
				var Keyid =jQuery("#incidentRptModifcnGrid").jqGrid('getCell',rowId,"IncidentNo");
				LoadPopUp("IncidentClosure","AccClosurePOP_input.ehsaccIncRpt?q=2&Keyid="+Keyid, true,"45%","45%","30%","30%", "","Closure"," "," " );
			}
		}
		
		function fillModifcnForm(id)
		{
			var rowData = jQuery("#incidentRptModifcnGrid").jqGrid('getRowData',id);		
			var keyId = rowData.sinm_keyid;
			var keyId2 = rowData.IncidentNo;
			alert("keyId"+keyId2);
			//incidentRptModifcnGrid_Status
			
			//var status = rowData.sinm_status;
			var status = rowData.Status;
			//alert("status"+status);
			//var yy = rowData.WHYWHYSTATUS;
			//var yy = rowData.SINM_WHYWHYFLAG;
			var mode=jQuery("#hdnmode").val();
			//alert(mode);
			if(mode=="Modify"||mode=="Delete"||mode=="View")
				{
			navigateToNextForm("safteyincident_input.ehsaccIncRpt?keyId="+keyId2+"&filterButton=false&mode="+mode+"&status="+status);
				}
			else{
				return false;
			}
			//var mode = "MODIFY";
			//var url = jQuery("#incidentRptModifcnGrid").jqGrid('getGridParam', 'url');
			//alert(url);
			//url = url.replace("_getData","_input");
			//url = escape(url);	
		
			/*var dataString = '?filterButton=false&keyId='+keyId;			
			var title='Incident Reporting';
			 //if(url.indexOf('Modify') >0)
				 if(url.indexOf('incident') >0)
				 {
				    mode = "MODIFY";					
					title = 'Safety Incident';
					if(status != null && status != '' && status != ' ')
					 {
						 if(status == 'OPEN')
						 {
							 if(yy != null && yy != '' && yy != ' ')
							 {
							  if(yy == 'COMPLETE'){
								  dataString += '&pending=actionPlan';
								  mode = "view";
							  }
							  else{
								  dataString += '&pending=yy';
								  mode = "MODIFY";
							  }
							 }
						 }
						 else
							 mode = "VIEW"; 
					 }
				}
				else
				{
					mode = "create";					
				}
			 dataString += '&mode='+mode;
			 LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt"+dataString, true,"90%","80%","10%","5%", "","Investigation"," "," " );
			 *///navigateToNextForm('safteyincident_input.ehsaccIncRpt'+dataString,title,null,{"filterString":url});
		}
		function IRMGrid()
		{
			 var docInsRowId = jQuery("#incidentRptModifcnGrid").jqGrid('getDataIDs');
			 for(i=1;i<=docInsRowId.length;i++)	
			 {
				 var yy = jQuery("#incidentRptModifcnGrid").getCell(i, 'WhyWhyStatus26');
				 var status = jQuery("#incidentRptModifcnGrid").getCell(i, 'Status1');
				 if(status != null && status != '' && status != ' ')
				 {
				 if(status == 'OPEN')
				 {
					 if(yy != null && yy != '' && yy != ' ')
					 {
					  if(yy == 'COMPLETE')
						 jQuery("#incidentRptModifcnGrid").jqGrid('setCell',i,"Status1","",{'background-color':'#E0C3C3'});
					  else
						 jQuery("#incidentRptModifcnGrid").jqGrid('setCell',i,"Status1","",{'background-color':'#afd6fe'});	 
					 }
				 }
				 else if(status == 'CLOSE')
					jQuery("#incidentRptModifcnGrid").jqGrid('setCell',i,"IncidentNo2","",{'background-color':'#F9A2AC'});
				 }
			 }
		}
</script>
<form id="frmAccidentGrid">
<div id="wrapper">


<div style="padding-left:20px;">
<!-- <table  style="margin-top:2px;">
	<tr>
	
	<td>
	<span id="btnSpanNewBooking" > 
	<input type="button" class="easyui-button"  id="btnNewReporting" value="New Booking" style="width:100px;height: 25px;"/>
</span>
	</td>
	
<td>
    <div>
	<label style="padding-left:20px;color:dark brown;font-weight: bold ;width:400px; ">Completed Why Why Pending Action Plan Pending</label>
    </div>
    
    <div class="notes" style="padding-left:20px;font-weight: bold;  color: blue;width:300px;">  Double Click on Row to View Data </div>
   
</td>

	
	</tr>
</table> -->

</div>

<div style="padding-left:10px;float:left;"> 
	 <table id="incidentRptModifcnGrid" style="width:90%"><tr><td/></tr></table>
	 <div id="incidentRptModifcnPager"></div>
</div>
</div>
<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.formMode}" />
<input type="hidden" id="hdnstatus" name="hdnstatus" value="${requestScope.status}" />
</form>