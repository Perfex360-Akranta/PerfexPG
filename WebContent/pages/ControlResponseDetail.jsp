
<script type="text/javascript">
	jQuery(document).ready(function() {
		
		var roleid = jQuery("#hdnUserRole").val();
	    //var roleName = jQuery("#hdnroleName").val();
	    
	    console.log("=== JAVASCRIPT DEBUG ===");
	    console.log("Role ID: '" + roleid + "'");
	    console.log("Role ID Length: " + (roleid ? roleid.length : 0));
	    
	    // Trim values to remove any whitespace
	    if(roleid) roleid = roleid.trim();
	  
	    
	    // Check if user has QM PILLAR MEMBER role
	    if(roleid !== "AROL0055"){
	        console.log("Access DENIED - Role mismatch");
	        console.log("Expected: 'QM PILLAR MEMBER' (AROL0055)");
	       
	        
	        // Hide all form content
	        jQuery('#frmCtrlRes').hide();
	        
	        // Show error message
	        if(typeof popupCommonErrorMsg === 'function') {
	            popupCommonErrorMsg("Please select the QM PILLAR MEMBER");
	        } else {
	            alert("Please select the QM PILLAR MEMBER");
	        }
	        
	        // Redirect back after showing message
	        setTimeout(function() {
	            navigateToPrevForm();
	        }, 5000);
	        
	        return false;
	    }
		initialiseForm('frmCtrlRes');
		jQuery('#submitForm').val('frmCtrlRes');
		//jQuery('#txtCarpProcessstep').css('text-transform','uppercase');
		fillComboBox("frmCtrlRes", "cmbCarpUom", "uomCombo.commonFilter");
		//var conresplanId=jQuery('#txtCarpKeyid').val();
		//alert("conresplanId   "+conresplanId);
		//numericTextBox("txtCarpSpeclimits");
		numericTextBox("txtCarpSamplesize");
		numericTextBox("txtCarpFrequency");

		var factId = jQuery("#frmCtrlRes input[id='factory']").val();
		var sectionId = jQuery("#frmCtrlRes input[id='section']").val();
		var cellId = jQuery("#frmCtrlRes input[id='cell']").val();
		var machId = jQuery("#frmCtrlRes input[id='machine']").val();
		var flid = jQuery("#frmCtrlRes input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		
		loadFunctionalLocation("CarpfunLocation","functionalLoc.commonFilter","CarpfunLocationValues","frmCtrlRes",dataStr);

		var KeyId=jQuery("#txtCarpKeyid").val();
		fileManagerPopUp(KeyId,"CRP","frmCtrlRes","btnfilemgr","CarpFilemgr");
		
	});


	function btnfilemgr_click()
	{
		var KeyId=jQuery("#txtCarpKeyid").val();
        if(KeyId.trim().length<=0){
	  		saveForm('frmCtrlRes','controlandresponse_save.conres?filemanger=filemanger');
	  	}else if(KeyId != null && KeyId != ''){
				fileManagerPopUp(KeyId,"CRP","","","");
		    }
		
	}
	function frmCtrlRes_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);
		navigateToPrevForm();
	}
	function frmCtrlRes_successsCallback(result) { //alert(" SuccessCallBack :: "+result.MomMstkeyid);	  
           clearForm("frmCtrlRes");
           navigateToPrevForm();
	       var filemanger =result.filemanger;
	       var Carpreskeyid=result.Carpreskeyid;
	       jQuery('#txtCarpKeyid').val(Carpreskeyid);
	      // var mode = result.formMode;	
	     //  alert("mode"+mode);
	       //alert(result.successData.mode);
	      // var formmode=result.successData.mode;
	       if(result.successData.mode=="Modify")
			{
				navigateToPrevForm("controlandresponse_modify.conres");
			}		
	      // alert(result.successData.msg);
	       if(filemanger==true){
	       	if(Carpreskeyid.trim().length>0){
		   		fileManagerPopUp(Carpreskeyid,"CRP","","","");

		   		if(result.successData.mode=="Modify")
				{
					navigateToPrevForm("controlandresponse_modify.conres");
				}		
	  		 }
	  		}

		}
	
	</script>
<form action="" method="post" id="frmCtrlRes">
<div id='wrapper' style="width:100%;margin-top:4px;">
<div style="margin-left:50px;">
<!--<div class="main-cntborder" style="height:410px;width:92%;width:88%\9;margin-left:50px;margin-top:-10px;">-->
<table>
<tr>
<td>
<div style="margin-left: 0px;margin-right:20px;">
				
		   <div  id="frmUnasfePracticeFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
			<input type="hidden" id="factory" name="cmbCarpFactoryid" value=""  ></input>			
			<input type="hidden" id="section" name="cmbCarpSectionid" value=""  ></input>
			<input type="hidden" id="cell" name="cmbCarpCellid" value=""  ></input>
			<input type="hidden" id="machine" name="cmbCarpEquipmentid1" value=""  ></input>
			<input type="hidden" id="flid" name="cmbCarpFlid" value="${requestScope.genTlControlandresponseplan.carpFlid}"  ></input>
			
			</div>
			<div id="CarpfunLocation" style="width: 846px; "></div>

</div>
</div>
</td>
<td>
<div style=" margin-left:-44px; margin-left:-70px\9; margin-top:-6px\9;position:relative; ">
					 <span  id="CarpFilemgr" style="position:absolute;" >
     		
             </span> 
             </div>	
</td>
</tr>
</table>
		  
		
			<div style=" width: 940px;width: 944px\9;"class="sub-header">
				<span style="">Control Plan</span> 
			</div>
			
					<table>
						<tr>
							<td valign="top">
								<div class="easyui-paddingbfpx">
										<label class="mandatory-lbl">Process Step</label>
								</div>
								<div class="easyui-paddingbfpx">
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style=" width : 226px; height : 70px;height : 74px\9;text-transform: uppercase;" id="txtCarpProcessstep" name="txtCarpProcessstep">${requestScope.genTlControlandresponseplan.carpProcessstep}</textarea>
								</div>
							<div style="padding-top:0px;">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Measurement Method</label>
								</div>
								<div class="easyui-paddingbfpx">

									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width: 226px; height : 70px;text-transform: uppercase;"
										id="txtCarpMeasurementmethod" name="txtCarpMeasurementmethod">${requestScope.genTlControlandresponseplan.carpMeasurementmethod}</textarea>
								</div>
 							</div>
                                							
							</td>

							<td valign="top" style="padding-left: 10px;">
							    <div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">KPOV</label>
								</div>
								<div class="easyui-paddingbfpx">               
                   	             	<input class="easyui-text" id="txtCarpKpov" name="txtCarpKpov"  style="width: 226px; height: 21px;text-transform: uppercase;" value="${requestScope.genTlControlandresponseplan.carpKpov}" />
					  			</div>
                               
								
								<div class="easyui-paddingbfpx">
									<label>Sample Size</label>
								</div>

								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtCarpSamplesize" name="txtCarpSamplesize"
										maxlength="20" style="width: 226px; height: 21px;text-align:right;text-transform: uppercase;" value="${requestScope.genTlControlandresponseplan.carpSamplesize}" />
								</div>
								<div class="easyui-paddingbfpx" style="padding-top:3px;">
									<label>Where It Is Recorded</label>
								</div>
								<div class="easyui-paddingbfpx">
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width: 226px; height : 70px;text-transform: uppercase;"	id="txtCarpWhererecorded" name="txtCarpWhererecorded">${requestScope.genTlControlandresponseplan.carpWhererecorded}</textarea>
								</div>
								</td>
							<td  valign="top" style="padding-left: 10px;">
								<div class="easyui-paddingbfpx">
									<label>Spec Limits</label>
								</div>

								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtCarpSpeclimits" name="txtCarpSpeclimits"
										maxlength="20" style="width: 226px; height: 21px;text-align:right;"
										value="${requestScope.genTlControlandresponseplan.carpSpeclimits}" />
								</div>
							   
							   
								<div class="easyui-paddingbfpx">
									<label>Frequency</label>
								</div>
								<div class="easyui-paddingbfpx">
								
								 <input class="easyui-text" id="txtCarpFrequency" name="txtCarpFrequency"  style="width: 226px; height: 21px;text-align:left;" value="${requestScope.genTlControlandresponseplan.carpFrequency}" />
									
								</div>
								
								<div class="easyui-paddingbfpx">
									<label>UoM</label>
								</div>

								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="cmbCarpUom" name="cmbCarpUom" style="width: 226px;" value="${requestScope.genTlControlandresponseplan.carpUom}" />
								</div>
							</td>
							<td style="padding-left: 10px;" valign="top" rowspan="3">
								<div class="easyui-paddingbfpx">
									<label>Control Limits</label>
								</div>

								<div class="easyui-paddingbfpx">
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width: 226px; height : 58px;text-transform: uppercase;" id="txtCarpControllimits" name="txtCarpControllimits">${requestScope.genTlControlandresponseplan.carpControllimits}</textarea>
								</div>
								<div class="easyui-paddingbfpx">
									<label>Who Measures</label>
								</div>
								<div class="easyui-paddingbfpx">
                                    <input class="easyui-text" id="txtCarpWhomeasures" name="txtCarpWhomeasures"  style="width: 226px; height: 21px;text-transform: uppercase;" value="${requestScope.genTlControlandresponseplan.carpWhomeasures}" />									
								</div>
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Decision Rule To Involve Response Plan</label>
								</div>
								<div class="easyui-paddingbfpx">
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width: 226px;height : 70px;text-transform: uppercase;"
										id="txtCarpDecisionrule" name="txtCarpDecisionrule">${requestScope.genTlControlandresponseplan.carpDecisionrule}</textarea>
								</div>	
							</td>
						</tr>
					</table>
			<div
				style="position: relative; width: 940px; width: 944px\9;"	class="sub-header">
				<span style="position: absolute;">Response Plan</span> 
				 
			</div>
			<div style="padding-top: 10px; ">
				<div>
					<table style="float:left;">
						<tr>
							<td>
								<div class="easyui-paddingbfpx">
									<label>Inform To </label>
								</div>
								<div class="easyui-paddingbfpx">

									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width: 226px; height : 58px;text-transform: uppercase;"
										id="txtCarpInformto" name="txtCarpInformto">${requestScope.genTlControlandresponseplan.carpInformto}</textarea>
								</div>
							</td>
							<td style="padding-left: 10px;">
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Corrective Action</label>
								</div>
								<div class="easyui-paddingbfpx" style="padding-top: -10px\9;">

									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width: 226px; height : 58px;text-transform: uppercase;" id="txtCarpCorrectiveaction" name="txtCarpCorrectiveaction">${requestScope.genTlControlandresponseplan.carpCorrectiveaction}</textarea>
								
								</div>
								</td>
							
					</table>
				</div>
			</div>
			</div>
			</div>
<!--			</div> -->
		<input type="hidden" id="mode" name="mode" />   
		<input type="hidden" id="txtCarpKeyid" name="txtCarpKeyid" value="${requestScope.genTlControlandresponseplan.carpKeyid}"/>
		<input type="hidden" id="clrFrmVal" name="clrFrmVal"value="${requestScope.clearfrom}" />
	</form>
