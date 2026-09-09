 <!-- Created By: Siddharth.A -->
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>


  <style>

.pokayoke
{

 padding-left: 70px;
 padding-left: 48px\9;
 
}




</style>

<script type="text/javascript">
jQuery.noConflict();
 jQuery(document).ready(function(){

	 initialiseForm('frmPokaYoke');
	 jQuery('#submitForm').val('frmPokaYoke'); // set the id of form to submit		

	 jQuery('#frmPokaYoke .easyui-text').css('text-transform', 'uppercase');
	 jQuery('#frmPokaYoke textarea').css('text-transform', 'uppercase');
		 
	 
/* for functionalLocation*/
	var factId = jQuery("#frmPokaYoke input[id='factory']").val();
	var sectionId = jQuery("#frmPokaYoke input[id='section']").val();
	var cellId = jQuery("#frmPokaYoke input[id='cell']").val();
	var machId = jQuery("#frmPokaYoke input[id='machine']").val();
	var flid = jQuery("#frmPokaYoke input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;		
	loadFunctionalLocation("pkymfunLocation","functionalLoc.pky","pkymfunLocationValues","frmPokaYoke",dataStr);
/*---------*/



	var compId = getFieldValue('company','frmPokaYoke');
	var locnId = getFieldValue('location','frmPokaYoke');
	var factId = getFieldValue('factory','frmPokaYoke');
	var sectId = getFieldValue('section','frmPokaYoke');
	var cellId = getFieldValue('cell','frmPokaYoke');
	var machId = getFieldValue('machine','frmPokaYoke');
      fillComboBox("frmPokaYoke","cmbpkymPhenomenaid","phenomena.commonFilter");
	fillComboBox("frmPokaYoke","cmbpkymMachineid","machineCombo.commonFilter");
     fillComboBox("frmPokaYoke","cmbpkymCauseid","cause.commonFilter");

	 
	imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgPkymAfterimage","imgPkyAfterImgFilename");
	imageUpload(jQuery( "#dlgImgPresent" ),'ImageUpload.commonFilter','dlgImgPresent',"imgPkymPresentimage","imgPkyPresentImgFilename");	

	
	formatDateBox('dtepkymDate','dd-MMM-yyyy');
	fillWithCurrentDate("dtepkymDate");


	jQuery("#imgPkymPresentimage").load(function() {
 		if((jQuery(this).width()>510.23)||(jQuery(this).height()>340.15))
		{	jQuery('#imgPkymPresentimage').attr('src', "");
			alert('Select Image with width not greater than 11.5 cms and height not greater than 9 cms');
			return false;
		}
        
    });
			
	jQuery("#imgPkymAfterimage").load(function() {
		if((jQuery(this).width()>510.23)||(jQuery(this).height()>340.15))
		{	jQuery('#imgPkymAfterimage').attr('src', "");
			alert('Select Image with width not greater than 11.5 cms and height not greater than 9 cms');
			return false;
		}
    });
	
	
  });


 // Onload Success

 function frmPokaYokecmbpkymMachineid_onLoadSuccess()
 {
	// fillComboBox("frmPokaYoke","cmbpkymPhenomenaid","phenomena.commonFilter");
 }
 function frmPokaYokecmbpkymPhenomenaid_onLoadSuccess()
 {
	// fillComboBox("frmPokaYoke","cmbpkymCauseid","cause.commonFilter");
 }

 function frmPokaYoke_FuntLocHierarchy_SuccessCallBack(keyIds)
 {
	setFieldValue('cmbpkymMachineid',keyIds.machId);
	reloadMachine("frmPokaYoke",'cmbpkymMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
 }

// On Select

function  frmPokaYokecmbpkymMachineid_onSelect(record)
{
	loadFunctionalLocation("pkymfunLocation","functionalLoc.pky","pkymfunLocationValues","frmPokaYoke","&machId="+record.id);
}

// On Success CallBacks

function frmPokaYoke_successsCallback(result)
{

	 jQuery('#imgPkymAfterimage').attr('src', "");
	 jQuery('#imgPkyPresentImgFilename').val("");	 
	 jQuery('#imgPkymPresentimage').attr('src', "");
	 jQuery('#imgPkyAfterImgFilename').val("");
	 fillWithCurrentDate("dtepkymDate");
	 loadFunctionalLocation("pkymfunLocation","functionalLoc.pky","pkymfunLocationValues","frmPokaYoke","");
}

/** Delete Success CallBack **/
function frmPokaYoke_deleteSuccessCallback(result)
{
	if(result.successData.mode!="View")
		navigateToPrevForm();	
}
/** Delete Error Callback **/
function frmPokaYoke_deleteErrorCallback(result)
{
	alert(result.errMsg.msg);
}

function frmPokaYoke_beforeDelete()
{
	 var delMsg = "Do You Want To Delete This Poka Yoke ("+jQuery('#cmbpkymKeyid').combobox('getValue')+") ?";
		if(confirm(delMsg) == false)
		{
				return false;
		}
}


/** Tick Other **/
function tickOther(chk1,chk2)
{
	jQuery(chk1).attr({"checked":false});
	jQuery(chk2).attr({"checked":false});
}

/** Clear image **/
 
jQuery( "#btnImgPresentClear" ).click(function() {
	jQuery('#imgPkymPresentimage').attr('src', "");
jQuery('#imgPkyPresentImgFilename').val("");

});

jQuery( "#btnImgAfterClear" ).click(function() {
	jQuery('#imgPkymAfterimage').attr('src', "");
jQuery('#imgPkyAfterImgFilename').val("");
});
</script>

<form name="frmPokaYoke" id="frmPokaYoke" action="" method="post">
<div style="margin-left: 4%;width:1100px;margin-top:20px;">
<div align="center">

<div align="center" style="width: 1000px;height:800px;"  class="main-cntborder">
<div class="cntborder" align="center" style="padding: 23px;">
<table>
		<tr>
<!--left  pane -->
			<td  valign="top" style="width:50%" colspan='2'>
				
						<div class="easyui-paddingbfpx" style="text-align: left;">
							
							<span style="padding-left: 10px;"><label >Poka Yoke</label></span>
						</div> 
							<div class="easyui-paddingbfpx" style="padding-left: 10px;"> 
		                		<input id="cmbpkymKeyid" name="cmbpkymKeyid" class="easyui-combobox"  style="width:300px;" value="${requestScope.pkyTlMst.pkymKeyid}" disabled="${requestScope.pokaYokeBean.disablePokeyoke}"> 
							</div>
						<div style="padding-left: 10px;" >
									<div id="pkymfunLocation" style="text-align: left;width: 200%;" ></div>
						</div>	
			</td>
			</tr>
			<tr>
			<td  valign="top" style="margin-left:10px;margin-top:10px;">	
					<div class="sub-header" align="left" style="width:400px;float:left;">Poka Yoke</div>
					<table>
						<tr>
							<td>
							<div class="easyui-paddingbfpx">
								<label class="mandatory-lbl">Poka Yoke No</label>
							</div> 
		                  	<div class="easyui-paddingbfpx"> 
		                			<input type="text" class="easyui-text" id="txtpkymPokayokeno" name="txtpkymPokayokeno" style="width:300px;" maxlength="30" value="${requestScope.pkyTlMst.pkymPokayokeno }" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/>
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="easyui-paddingbfpx"><label>Date</label></div> 
		                  	<div class="easyui-paddingbfpx"> 
		                			 <input id="dtepkymDate" name="dtepkymDate" class="easyui-datebox"  style="width:160px;" value="${requestScope.pkyTlMst.pkymDate}" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/> 
							</div>
							</td>
						</tr>
					</table>
					<div class="sub-header" align="left" style="width:400px;float:left;">Details</div>
					<table>
						
						<tr>
							<td>
							<div  id="frmPokaYokeFuntKeyIds"  >
								<div style="float: left;padding-right: 20px;">
									<input type="hidden" id="factory" name="cmbpkymFactoryid" value="${requestScope.pkyTlMst.pkymFactoryid}"  ></input>
									<input type="hidden" id="section" name="cmbpkymSectionid" value="${requestScope.pkyTlMst.pkymSectionid}"  ></input>
									<input type="hidden" id="cell" name="cmbpkymCellid" value="${requestScope.pkyTlMst.pkymCellid}"  ></input>
									<input type="hidden" id="machine" name="cmbpkymMachineid1" value="${requestScope.pkyTlMst.pkymMachineid}"  ></input>
									<input type="hidden" id="flid" name="cmbpkymFlid" value="${requestScope.pkyTlMst.pkymFlid}"  ></input>
									</div>
							 </div>		
							</td>
						</tr>
						<tr>
							<td>
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Machine</label></div> 
							<div class="easyui-paddingbfpx">  
		                	 <input id="cmbpkymMachineid" name="cmbpkymMachineid" class="easyui-combobox"  style="width:300px;" value="${requestScope.pkyTlMst.pkymMachineid }" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> />
							</div>
							</td>
						</tr>
						<tr>
							<td>	
							<div class="easyui-paddingbfpx"><label>Phenomena</label></div> 
							<div class="easyui-paddingbfpx">  
		                		<input id="cmbpkymPhenomenaid" name="cmbpkymPhenomenaid" class="easyui-combobox"  style="width:300px;" value="${requestScope.pkyTlMst.pkymPhenomenaid}"  <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/>	
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="easyui-paddingbfpx"><label>Cause</label></div> 
							<div class="easyui-paddingbfpx">  
		                	  <input id="cmbpkymCauseid" name="cmbpkymCauseid" class="easyui-combobox"  style="width:300px;" value="${requestScope.pkyTlMst.pkymCauseid}" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> >
							</div>
							</td>
						</tr>
						<tr>
							<td>
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Problem</label></div> 
                 	 		<div class="easyui-paddingbfpx">  
                				<textarea id="txtpkymProblem" name="txtpkymProblem" rows="5" cols="6" maxLength="490" style="width : 300px; height : 61px;resize:none;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>>${requestScope.pkyTlMst.pkymProblem}</textarea>
							</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Solution</label></div> 
                 	 			<div class="easyui-paddingbfpx">  
                					<textarea id="txtpkymSolution" name="txtpkymSolution" rows="5" cols="6" maxlength="490" style="width : 300px; height : 61px;resize:none;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>>${requestScope.pkyTlMst.pkymSolution}</textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="easyui-paddingbfpx"><label>Key Improvement</label></div> 
                 	 			<div class="easyui-paddingbfpx">  
                					<textarea id="txtpkymKeyimprovement" name="txtpkymKeyimprovement" rows="5" cols="6" maxlength="490" style="width : 300px; height : 61px;resize:none;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>>${requestScope.pkyTlMst.pkymKeyimprovement}</textarea>
								</div>
							</td>
						</tr>
					</table>
					</div>
			</td>
<!--Right  pane -->
			<td valign='top' style="margin-left:10px;margin-top:10px;">
				<div id="PokaYoketab" style="width : 552px; height :556px;  ">
					<div id="tabPokaYoke" class="easyui-tabs" fit="true" plain="true" >
						<div title="Before" style="padding:10px;">
							<div class="sub-header ">
								<label class="mandatory-lbl">Present Description</label>
							 </div>
							
		                 	 		<div class="easyui-paddingbfpx"> 
		                		<textarea id="txtpkymPresentdesc" name="txtpkymPresentdesc" rows="5" cols="6" maxlength="490" style="width : 500px;resize:none; height : 145px;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>>${requestScope.pkyTlMst.pkymPresentdesc}</textarea>
							</div>
							<div class="sub-header "><label>Present Condition</label><span style="padding-left:5px;font-size:9px;">width:13.5 cm height:9 cm</span><span style="padding-left:0px;height:20px">
								<input type="button" class="easyui-button" value ="Image" id="dlgImgPresent" style="height:20px;" name="dlgImgPresent" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/>
								<input type="button" class="easyui-button" value ="Clear" id="btnImgPresentClear" name="btnImgPresentClear" style="height:20px;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/>
								</span>	
								
							</div>
							<div><img alt="" id="imgPkymPresentimage" name="imgPkymPresentimage" src="${requestScope.pkyTlMst.pkymPresentimg}"/></div>
							
						</div>
						<div title="After" style="padding:10px;">
							<div class="sub-header"><label class="mandatory-lbl">After Description</label>
							</div>
						
	                 	 <div class="easyui-paddingbfpx"> 
	                		<textarea id="txtpkymAfterdesc" name="txtpkymAfterdesc" rows="5" cols="6" maxLength="490" style="width : 500px; height : 145px;resize:none;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>>${requestScope.pkyTlMst.pkymAfterdesc}</textarea>
						</div>
							<div class="sub-header "><label>After Condition</label><span style="padding-left:5px;font-size:9px;">width:13.5 cm height:9 cm</span><span style="padding-left:0px;height:20px">
								<input type="button" class="easyui-button" value ="Image" id="dlgImgAfter" name="dlgImgAfter" style="height:20px;">
									<input type="button" class="easyui-button" value ="Clear" id="btnImgAfterClear" name="btnImgAfterClear" style="height:20px;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/>
							</span>
						</div>
						<div><img alt="" id="imgPkymAfterimage" name="imgImflFilename" src="${requestScope.pkyTlMst.pkymAfterimg}"  width="" height="" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>/></div>
	                 	</div>
						<div title="Effects&Results" style="padding:10px;">
							<table border=2 width="100%">
								<tr>
									<td>
										<div class="sub-header" style="position:relative;"><label class="mandatory-lbl"> Type</label>
										<span style="position:absolute;right: 0px;"><input type="button" class="easyui-button" value ="Excel View" id="Exv" style="width:80px;height:20px;" onclick=""/></span></div>
									</td>
								</tr>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Prevention</label> 
											<input id= "chkPrevention" name="chkPrevention" type="checkbox" onclick="tickOther(chkDetection)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.prevention == 'Y' ? ' checked':''}"/>/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Detection</label> 
											<input id="chkDetection" name="chkDetection" type="checkbox" onclick="tickOther(chkPrevention)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.detection == 'Y' ? ' checked':''}"/>/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="sub-header" style=" height : 17px;margin-bottom:10px;">
											<label class="mandatory-lbl"> Function</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Shut Down</label> 
											<input id="chkShutdown" name="chkShutdown" type="checkbox" onclick="tickOther(chkControl,chkWarning)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.shutdown == 'Y' ? ' checked':''}"/>/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
									   <div class="easyui-paddingbfpx" style="padding-right:250"align="right">	
											<label>Control</label>
											 <span><input id="chkControl" name="chkControl" type="checkbox" onclick="tickOther(chkShutdown,chkWarning)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/><c:out value = "${ requestScope.pokaYokeBean.control == 'Y' ? ' checked':''}"/>/></span>
										</div>
									</td>
								</tr>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Warning</label> 
											<input id="chkWarning" name="chkWarning" type="checkbox" onclick="tickOther(chkShutdown,chkControl)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.warning == 'Y' ? ' checked':''}"/>/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="sub-header" style=" height : 17px;margin-bottom:10px;"> <label class="mandatory-lbl">Effect On</label></div>
									</td>
								</tr>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Complaint</label>
											<input id="chkComplaint" name="chkComplaint" type="checkbox" onclick="tickOther(chkRejection,chkBreakdown)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.complaint == 'Y' ? ' checked':''}"/>/><br>
											
										</div>
									</td>
								</tr>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Rejection</label>
											<input id="chkRejection" name="chkRejection" type="checkbox" onclick="tickOther(chkComplaint,chkBreakdown)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.rejection == 'Y' ? ' checked':''}"/>/><br>
										</div>
									</td>
								<tr>
									<td>
									<div class="easyui-paddingbfpx" style="padding-right:250"align="right">
											<label>Breakdown</label>
											<input id="chkBreakdown" name="chkBreakdown" type="checkbox" onclick="tickOther(chkRejection,chkComplaint)" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.pokaYokeBean.breakdown == 'Y' ? ' checked':''}"/>/>
									</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="sub-header" style=" height : 17px;padding-bottom:0px;margin-bottom:10px;"> Result</div>
										<div style="padding-bottom: 3px;padding-right:10px;"> 
			                				<textarea id="txtpkymResultdesc" name="txtpkymResultdesc" rows="5" cols="6" maxlength="490" style="resize:none; width : 490px; height : 100px;" <c:out value = "${ requestScope.pokaYokeBean.disableForm == true ? ' disabled':''}"/>>${requestScope.pkyTlMst.pkymResultdesc}</textarea>
							 				<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
							 				<input type="hidden" id="imgPkyPresentImgFilename" name="imgPkyPresentImgFilename" value="" />
											<input type="hidden" id="imgPkyAfterImgFilename" name="imgPkyAfterImgFilename" value="" />
							 			
							 			</div>
							 		</td>
							 	</tr>
							 </table>
							</div>
						</div>
				</div>
			</td>
		</tr>
	</table> 
</div>
</div>
</div>
</form>