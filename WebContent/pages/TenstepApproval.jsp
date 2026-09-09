
<script>
var menu = "";
	
	jQuery(document).ready(		function() {
		
		initialiseForm('frmTenstepApproval');
		jQuery('#submitForm').val('frmTenstepApproval');
		

		fillComboBox("frmTenstepApproval", "cmbQammApprovedby", "employee.commonFilter");
		
		formatDateBox('dteQammApproveddate','dd-MMM-yyyy');	
 		fillWithCurrentDate('dteQammApproveddate');
 
		var qamatrixId = jQuery('#hdnQamatrixId').val();
		menu = jQuery('#hdnMenu').val();
		if(menu == "APPROVAL") {
			jQuery('#btnApprove').val('Approved');
			jQuery('#divApproval').show();
			disableField("frmTenstepApproval","cmbQammApprovedby");
			disableField("frmTenstepApproval","cmbQammApprovedflag");
			
		}
		else  { 
			jQuery('#btnApprove').val('Submit for Approval');
			jQuery('#divApproval').hide();
		}
		
		
		//	alert('qamatrixId'+qamatrixId);
		processGridnew("tenstepsapproval_input.tsdi","q=2&menu="+menu+"&qamatrixId="+qamatrixId,"ApprovalGrid", "approvalpager", "", "","","apprLoad");
		/* formatDateBox('dteApprovedDate','dd-MMM-yyyy');
		formatDateBox('dteCreatedDate','dd-MMM-yyyy');
		fillComboBox("frmTenstepApproval", "cmbVsomIssuedby", "employee.commonFilter");				
		fillComboBox("frmTenstepApproval", "cmbVsomApprovedby", "employee.commonFilter");
		
		fillComboBox("frmTenstepApproval", "cmbVsomEquipmentid", "machineCombo.commonFilter");
		fillComboBox("frmTenstepApproval", "cmbVsomProductid", "product.commonFilter"); */
	});
	
	function apprLoad() {
		var rowIds = jQuery("#ApprovalGrid").getDataIDs();
		//alert(rowIds);
		var menu = jQuery('#hdnMenu').val();
		//if(!(menu == "APPROVAL")) {
		 for(var i = 1; i<=rowIds.length; i++) {
			 if((menu != "APPROVAL" && i<=3))
				jQuery('#jqg_ApprovalGrid_'+i).attr('checked',true);
			
			 if((menu == "APPROVAL")) {
				jQuery('#jqg_ApprovalGrid_'+i).attr('disabled',true);
				jQuery('#jqg_ApprovalGrid_'+i).attr('checked',true);
			 }
		 }
	}
	
	
	function ApprovalGrid_selectRow()
	{
			//alert(1);
	}
	
	jQuery('#chkApproved').click(function() {
		var rowIds = jQuery("#ApprovalGrid").getDataIDs();
		if(jQuery('#chkApproved').is(':checked')==true ) {  
			enableFields("cmbQammApprovedby");
			enableFields("cmbQammApprovedflag");			
			var loginUserid = jQuery('#hdnLoginUserid').val();
			setFieldValue('cmbQammApprovedby',loginUserid);
			for(var i=1;i<rowIds.length+1;i++)
			{				
				jQuery("#jqg_ApprovalGrid_"+i).attr("checked",true);
				ApprovalGrid_selectRow();
			}
		}
		else{
			disableField("frmTenstepApproval","cmbQammApprovedby");
			disableField("frmTenstepApproval","cmbQammApprovedflag");
			
			for(var i=1;i<rowIds.length+1;i++)
			{				
				jQuery("#jqg_ApprovalGrid_"+i).attr("checked",false);
				ApprovalGrid_selectRow();
			}
			//readOnlyFields("txttrimmingqty");
		}
	});

	
	function btnfilemgr_click()
	{
			fileManagerPopUp("","ABN","frmVisualSop","btnfilemgr","abnFilemgr");
	}
	
	
	jQuery("#btnApprove").click(function() {
		menu = jQuery('#hdnMenu').val();
		if(menu == "SELECTION") {
			var selctedProcess = selectedQaMatProcess();
			if (selctedProcess==false) {
				return false;
			}
				
			var filterString = selctedProcess;
			processAjaxCalls("updateQaMatrix_input.tsdi",filterString,'update_OnSuccess','update_OnError');
		 	//processAjaxCalls("selectionQaMatrix_input.tsdi","&selectionData="+selectionData ,'approve_OnSuccess','approve_OnError');
		}
		else {
			/* var approvalData = getGridSelectArray("ApprovalGrid");
			alert(approvalData); */
			
			var approvedBy =getFieldValue("cmbQammApprovedby");
			if (approvedBy=='' || approvedBy==' ') {
				popupCommonErrorMsg(' Select Approved By');
				return false;
			}
				
			var approvalData = getSelectedRows("ApprovalGrid");
			//alert(approvalData);
		 	processAjaxCalls("ApproveQaMatrix_input.tsdi","&approvalData="+approvalData ,'approve_OnSuccess','approve_OnError');
		}
	});
			
	function update_OnSuccess(result){
		if(result.msg == "dataUpdated" )
			alert("Saved Successfully");   
		navigateToPrevForm("");
	}
	
	function getSelectedRows(gridId) { 
		
		var grid = jQuery("#"+gridId);
		var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
	    //if( selArray.length <= 0 ) return "";
	        var row=jQuery("#"+gridId).jqGrid('getDataIDs');
	        var qamatrixId = jQuery('#hdnQamatrixId').val();
			var rowid="";
			var jsonArr='[';
		 	var approvedBy =getFieldValue("cmbQammApprovedby");
			var approveFlag = getFieldValue("cmbQammApprovedflag");
		 	var approvedDate ="01-jan-2014";
		 	for(var i=0;i<row.length;i++)
			{
			 	rowid=row[i];
			 	
			 	var qamatrixId = jQuery("#ApprovalGrid").jqGrid('getCell',rowid,"txtQammKeyid");
			 	//var checkVal =jQuery("#jqg_Qamatrixgrid_"+row[i]).is(':checked');
			 	//if(checkVal){
			 		if(rowid>0){  
					 	jsonArr+= '{';
							jsonArr += '"txtQammKeyid":"'+qamatrixId+'",';
							jsonArr += '"txtQammApprovedflag":"'+approveFlag+'",';
						 	jsonArr += '"txtQammApprovedby":"'+approvedBy+'",';
							jsonArr += '"txtQammApproveddate":"'+approvedDate+'",'; 
							//jsonArr += '"txtQammprocessid":"'+severityVal+'",';
							
							jsonArr+='},';
			 		 }
				//}
		 	}

		 	if (jsonArr=="["){
				jsonArr=="";
				return false;
			}
			else{ 
				jsonArr = jsonArr.slice(0,-1) ;
				jsonArr = jsonArr.slice(0,-2)+ "}]";
				return jsonArr;
			
		}
	}
	
	function selectedQaMatProcess() {
		var grid = jQuery("#ApprovalGrid");
		//var colModels = grid.jqGrid("getGridParam", "colModel");
		/* var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
		if( selArray.length <= 0 ){
				popupCommonErrorMsg('Select Process for Submit for Approval');
	    	  return false;
	     }
 */		
 		var QaMatrixKeyid="";
	var rowIds = jQuery("#ApprovalGrid").getDataIDs();
	for(var i=1;i<rowIds.length+1;i++)
	{				
	 	var checkVal =jQuery("#jqg_ApprovalGrid_"+(i)).is(':checked');
	 	if(true == checkVal){
	 		//alert(checkVal);
			QaMatrixKeyid +=grid.jqGrid('getCell', (i),"txtQammKeyid")+","; 		
	 	}
	}
		if(QaMatrixKeyid.length<2)  {
			popupCommonErrorMsg('Select Process for Submit for Approval');
  	  		return false;
		}
  	  		
	 	return "&qamKeyid="+QaMatrixKeyid;
	}
	

function approve_OnSuccess(result){
	var flid = jQuery("#ApprovalGrid").jqGrid('getCell',1,"txtQamxFlid");
	var processid= jQuery("#ApprovalGrid").jqGrid('getCell',1,"txtQamxprocessid");
	var processname= jQuery("#ApprovalGrid").jqGrid('getCell',1,"processname");
	var qamatrixId = jQuery('#hdnQamatrixId').val();
	//alert('qamatrixId'+qamatrixId);
	if(result.msg =="Data Updated")
		alert("Approved Successfully");
	navigateToPrevForm("");
	/* if(result.msg =="Data Updated"){
		//tenstepsLink_input.tsdi
		var dataStr = "?&processid="+processid+"&flid="+flid+"&processname="+processname+"&qamatrixId="+qamatrixId;
		navigateToNextForm("tenstepsLink_input.tsdi"+dataStr,"10step",null,{"filterString":"tenstepsLink_input.tsdi"});
	} */
}	

</script>


	<form action="" method="post" id="frmTenstepApproval">
		<div id='wrapperRpt'>

	<table><tr><td>
		<div id="divApproval">
			<div style="padding-left: 90px;">
				<label> Approved By </label>
			</div>
			<div>
				<input type="checkbox" id="chkApproved" name="chkApproved" value="N" >
				<span style="padding-left: 10px">
				<select id='cmbQammApprovedflag' name='cmbQammApprovedflag' disabled="disabled" panelHeight=60px; class="easyui-combobox" style='width :60px;height:20px;' > 
				 	<option value='Y'>Accept</option>
					 <option value='N'>Reject</option>
					 <option value='E'>Rework</option>
				</select>
				</span>
				<span>
					<input class="easyui-combobox" id="cmbQammApprovedby" name="cmbQammApprovedby" style='width:240px;' value="${requestScope.approvedBy}"/>
				</span>
				<span style="padding-left: 10px">
					<input class="easyui-text" id="dteQammApproveddate" name="dteQammApproveddate" type="text" value="${requestScope.approvedDate}" style="width:100px;height:20px;color:black;" />
				</span>
			</div>
		</div>
	</td><td>
		<span style="padding-left: 20px;">
			 <input type="button" class="easyui-button"  value="Approve"  id="btnApprove" name="btnApprove"> 
		</span>
	</td>
	</tr></table>
		<div>
			<table id='ApprovalGrid'>
				<tr>
					<td></td>
				</tr>
				
			</table>
			<div id='approvalpager'></div>
		</div>
		</div>

	<input type="hidden" name = "hdnQamatrixId" id ="hdnQamatrixId" value="${requestScope.qamatrixId}"/>
	<input type="hidden" name = "hdnMenu" id ="hdnMenu" value="${requestScope.menu}"/>
	<input type="hidden" name = "hdnLoginUserid" id ="hdnLoginUserid" value="${requestScope.loginUserid}"/>
	
	</form>
	