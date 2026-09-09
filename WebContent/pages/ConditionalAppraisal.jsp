<script>
jQuery(document).ready(function(){ 
	//alert("dsgdgfsdfgsdgsdgdsfgdg");
	initialiseForm('frmConditional'); 
	jQuery('#submitForm').val('frmConditional');
	processGridnew("ConditionalAppraisalform_input.condapp","q=2","conditionalgrid","conditionalpager","","","","");
	formatDateBox('dteCdapCompleteddate','dd-MMM-yyyy');
	formatDateBox('dteCdapTargetdate','dd-MMM-yyyy');
	fillComboBox('frmConditional','cmbCdapStatus','');
	fillComboBox('frmConditional','cmbCdapCompletedby', 'employeeFilter.commonFilter');
	fillComboBox('frmConditional','cmbCdapResponsibility', 'employeeFilter.commonFilter');
	fillComboBox('frmConditional','cmbCdapComponentid', 'product.commonFilter');

	fileManagerPopUp("","CON","frmConditional","btnFilManage","condapprFilemgr");
	
	readOnlyFields("dteCdapCompleteddate");
	readOnlyFields("cmbCdapCompletedby");
	
	
	var factId = jQuery("#frmConditional input[id='factory']").val();
	var sectionId = jQuery("#frmConditional input[id='section']").val(); 
	var cellId = jQuery("#frmConditional input[id='cell']").val(); 
	var machId = jQuery("#frmConditional input[id='machine']").val(); 
	var flid = jQuery("#frmConditional input[id='flid']").val(); 
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("confunLocation","functionalLoc.brdn","confunLocationValues","frmConditional",dataStr);
	
	/*aDDED FOR PROTOTTYPE*/
jQuery('#txtCdapActualconditin').val("OK");	
});
function frmConditional_FuntLocHierarchy_SuccessCallBack(result){
	var flid = result.flid;
	jQuery("#cmbCdapFlnid").val(flid);
	

}
function btnFilManage_click(){
	
    
   
	if(1 != null && 1 != ''){
		fileManagerPopUp(1,"CON","","","");
	}
	
}

function frmConditional_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	  
}function  frmConditionalcmbCdapStatus_onSelect(record)
{
	if(record.id == 'C')
	{   
		
		enableFields("dteCdapCompleteddate");
		enableFields("cmbCdapCompletedby");
		
	}
	else
	{
		readOnlyFields("dteCdapCompleteddate");
		readOnlyFields("cmbCdapCompletedby");
	}
}

</script>

<form name="frmConditional" id="frmConditional"  action=" " method="post">
<div id="wrapper">
		
<div style="margin-left:4%;">
		<table>
		<tr>
		<td>
		
		<div id="frmConditionalFuntKeyIds">
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>
								 <input type="hidden"id="flid" name="cmbCdapFlnid" value="${requestScope.genTlConditionalappraisal.cdapFlnid}" />
					</div>
					<div id="confunLocation" style="width:1000px;"></div>
				   
				
		</td>
			</tr>
		</table>
		 <div style="position:relative;">
<span  id="condapprFilemgr" style="position:absolute;top:-30px;right: 157px;" >
     		       </span>

             </div>
		<table >
		<tr>
				<td valign='top'>
				<div><label>Area</label></div>
					<div class="easyui-paddingbfpx" >
						<input type="text" id="CdapComponentid" name="CdapComponentid" value="${requestScope.genTlConditionalappraisal.cdapComponentid}" class="easyui-text" style="width:300px; height:21px"></input>
					</div>
					
					<div ><label>Component</label></div>
					<div class="easyui-paddingbfpx">
						<input type="text" id="cmbCdapComponentid" name="cmbCdapComponentid" value="${requestScope.genTlConditionalappraisal.cdapComponentid}" class="easyui-text" style="width:300px; height:21px"></input>
					</div>
					 <div><label>Action Required</label></div>
					<div class="easyui-paddingbfpx" >
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="495" id="txtCdapActionrequired" name="txtCdapActionrequired"   style="width:300px;height:61px; ">${requestScope.genTlConditionalappraisal.cdapActionrequired}</textarea>
					</div>
					<div ><label>Responsibility</label> <span><label class="mandatory-lbl" style="padding-left:118px;">Target Date</label></span></div>
					<div>
						<input type="text" id="cmbCdapResponsibility" name="cmbCdapResponsibility" value="${requestScope.genTlConditionalappraisal.cdapResponsibility}" class="easyui-text" style="width:170px; height:21px" value="" ></input>
					<span style="padding-left: 26px;"><input type="text" id="dteCdapTargetdate" name="dteCdapTargetdate" value="${requestScope.genTlConditionalappraisal.cdapTargetdate}" class="easyui-text" style="width:100px;" ></input></span>
					</div>
					
				</td>
				<td valign="top" style="padding-left: 45px">
				    <div><label>Sub Equipment</label></div>
					<div class="easyui-paddingbfpx">
						<input type="text" id="cmbCdapSubequipmentid" name="cmbCdapSubequipmentid" value="${requestScope.genTlConditionalappraisal.cdapComponentid}" class="easyui-text" style="width:300px; height:21px" ></input>
					</div>
					<div ><label>Type Of Check</label></div>
					<div class="easyui-paddingbfpx" >
						<input type="text" id="txtCdapTypeofcheck" name="txtCdapTypeofcheck" value="${requestScope.genTlConditionalappraisal.cdapTypeofcheck}" class="easyui-text" style="width:300px; height:21px"></input>
					</div>
					
					<div><label>Ideal Condition</label></div>
					<div class="easyui-paddingbfpx">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="495" id="txtCdapIdealcondition" name="txtCdapIdealcondition"  style="width:300px; height:61px" >${requestScope.genTlConditionalappraisal.cdapIdealcondition}</textarea>
						
					</div>
				</td>
				<td valign="top" style="padding-left: 45px;">
				<div><label>Dimension</label></div>
					<div class="easyui-paddingbfpx">
					  <input type="text" id="txtCdapDimension" name="txtCdapDimension" value="${requestScope.genTlConditionalappraisal.cdapDimension}" class="easyui-text" style="width:300px; height:21px" ></input>
					</div>
			
				    <div>
					<div><label>Checking Tool</label></div>
					<div class="easyui-paddingbfpx">
						<input type="text" id="txtCdapCheckingtool" name="txtCdapCheckingtool" value="${requestScope.genTlConditionalappraisal.cdapCheckingtool}" class="easyui-text" style="width:300px; height:21px"></input>
					</div>
					</div>
					<div ><label>Actual Condition</label></div>
					<div class="easyui-paddingbfpx">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="495" id="txtCdapActualconditin" name="txtCdapActualconditin"   style="width:300px; height:61px; ">${requestScope.genTlConditionalappraisal.cdapActualconditin}</textarea>
					</div>
					
					
				</td>
			</tr>
		        
		</table>	
			<div style=" position:relative;  width:86.1%; width:954px\9;  height:20px; height:25px\9;" class="sub-header">
			<span style="position: absolute;">Completed  Detail</span>
			</div>	 
			<table>
			<tr>
			<td  valign='top' >
					<div >
					<label>Status</label><span style="padding-left: 163px;"><label class="mandatory-lbl">Completed Date</label></span> 
					</div>
					<div class="easyui-paddingbfpx">
						
						<select id="cmbCdapStatus" name="cmbCdapStatus"  class="easyui-text"  style="width:170px; height:21px"  >
							<option value="P">Pending</option>																						
							<option value="C">Completed</option></select>
							<span style="padding-left: 26px;">
							<input type="text" id="dteCdapCompleteddate" name="dteCdapCompleteddate" value="${requestScope.genTlConditionalappraisal.cdapCompleteddate}" class="easyui-text"  style="width: 100px"></input>
							</span>
								<div ><label class="mandatory-lbl">Completed By</label></div>
					<div>
						<input type="text" id="cmbCdapCompletedby" name="cmbCdapCompletedby" value="${requestScope.genTlConditionalappraisal.cdapCompletedby}" class="easyui-text" style="width:300px; height:21px" ></input>
					</div>
					</div>
					</td>
					<td style="padding-left: 45px;">
					<div><label>Remarks</label></div>
					<div>
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="495" id="txtCdapRemarks" name="txtCdapRemarks"   style="width:300px; height : 61px;">${requestScope.genTlConditionalappraisal.cdapRemarks}</textarea>
					</div>
					</td>
			</tr>	
			</table>			
			
				 	<div style="position: relative;">
				 	<span style="position: absolute; right: 151px;">
				 	<input type="button" style="width:50px;height:22px;" class="easyui-button" id="btnInsert" name="btnInsert" value="Insert"  ></input>
				 	<input type="button" style="width:50px;height:22px;" class="easyui-button" id="btnDelete" name="btnDelete" value="Delete" ></input>
				 	<input type="button" style="width:50px;height:22px;" class="easyui-button" id="btnClear" name="btnClear" value="Clear" ></input>
				 	</span>
				 	</div>
					
					
			
	<div style="padding-top: 24px; float: left;">	
		<table id='conditionalgrid' ">
	       <tr>
		<td></td>
	    </tr>
</table>
<div id='conditionalpager'></div>
	</div>
  </div>
</div>
<input type="hidden" id="mode" name="mode" value="" ></input>
	 <input type="hidden"id="txtCdapKeyid" name="txtCdapKeyid" value="${requestScope.genTlConditionalappraisal.cdapKeyid}" ></input>
	 
</form>
	