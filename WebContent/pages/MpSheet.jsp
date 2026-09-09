<!-- Created By: Siddharth.A -->
<!-- Modified By: KarthicK.T -->


<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script>	 

jQuery.noConflict();
jQuery(document).ready(function()
{	

	
	jQuery('#submitForm').val('frmMpSheet'); // set the id of form to submit
	initialiseForm('frmMpSheet');	

	jQuery('#frmMpSheet .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmMpSheet textarea').css('text-transform', 'uppercase');

	/*------------- for functionalLocation ------------------*/
	
	var factId = jQuery("#frmMpSheet input[id='factory']").val();
	var sectionId = jQuery("#frmMpSheet input[id='section']").val();
	var cellId = jQuery("#frmMpSheet input[id='cell']").val();
	var machId = jQuery("#frmMpSheet input[id='machine']").val();
	var flid = jQuery("#frmMpSheet input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;	
	
	loadFunctionalLocation("mpsmfunLocation","functionalLoc.mps","mpsmfunLocationValues","frmMpSheet",dataStr);
	/*-------------------------------------------------------*/
	
	var compId = getFieldValue('company','frmMpSheet');
	var locnId = getFieldValue('location','frmMpSheet');
	var factId = getFieldValue('factory','frmMpSheet');
	var sectId = getFieldValue('section','frmMpSheet');
	var cellId = getFieldValue('cell','frmMpSheet');
	var machId = getFieldValue('machine','frmMpSheet');
	
	fillComboBox("frmMpSheet","cmbmpsmCostcentreid","costCenter.commonFilter");
	fillComboBox("frmMpSheet","cmbmpsmMachineid","machineCombo.commonFilter");
	formatDateBox("dtempsmCreateddate","dd-MMM-yyyy");

	imageUpload(jQuery("#dlgImgBefore" ),'ImageUpload.commonFilter','dlgImgBefore',"imgMpsmBeforeimage","imgMpsBeforeImgFilename");
    imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgMpsmAfterimage","imgMpsAfterImgFilename");
	
	processGridnew("tgtImprovement_input.mps","?row=2&mpsKeyid="+jQuery('#cmbmpsmKeyid').combobox('getValue'),"tgtImpGrid","pager","","","","tgtImpOnCompleteGrid");
	processGridnew("mpsHdScan_input.mps","?row=2&mpsKeyid="+jQuery('#cmbmpsmKeyid').combobox('getValue'),"mpsHDGrid","mpsHDPager","","","","mpsHDGrid_OnGridload");
	processGridnew("resultType_input.mps","?row=2","resultsGrid","pager","","","","tgtImpOnCompleteGrid");

	numericTextBox("txtmpsmTotalamount");
	jQuery("#imgMpsmBeforeimage").load(function() {
 		if((jQuery(this).width()>375)||(jQuery(this).height()>216))
		{	jQuery('#imgMpsmBeforeimage').attr('src', "");
			alert('Select Image with width not greater than 9.91 cms and height not greater than 5.69 cms');
			return false;
		}
        
    });
			
	jQuery("#imgMpsmAfterimage").load(function() {
		if((jQuery(this).width()>375)||(jQuery(this).height()>216))
		{	jQuery('#imgMpsmAfterimage').attr('src', "");
			alert('Select Image with width not greater than 9.91 cms and height not greater than 5.69 cms');
			return false;
		}
    });


	
});		


function frmMpSheetcmbmpsmMachineid_onLoadSuccess()
{
	 setComboDefaultValue("frmMpSheet","cmbmpsmCostcentreid");
}

function frmMpSheet_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	//setFieldValue('cmbmpsmMachineid',keyIds.machId);
	
	//reloadMachine("frmMpSheet",'cmbmpsmMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	jQuery("#cmbmpsmCostcentreid").combobox('clear');
	//reloadCombo("frmMpSheet","cmbmpsmCostcentreid","costCenter.commonFilter?cellId="+keyIds.cellId  );
	
}


//--------------------------------------------------------------------//

function frmMpSheet_successsCallback(result)
{

	 var modes = result.successData.mode;
	 var persistentData = result.persistentData;
	 var forwardData = result.forwardData;
	
	 if(modes != null && modes == "hdScan" )
	 {
		 //jQuery("#hdnhdScan").val('');
		 navigateToNextForm("hrzdplymnt_input.hrzdply","Horizontal Deployment",forwardData,persistentData );
		 
	 }
	 
}
function frmMpSheet_beforeSubmit()
{
	//var gridData ='&MpTgtImp='+convertJqGridToJSONStringArr('tgtImpGrid');
	var gridData ='&MpTgtImp='+JqGridToJsonSelectdRows('tgtImpGrid','imprvName','txtMpidImprovementid');
		gridData+='&MpResult='+JqGridToJsonSelectdRows('resultsGrid','result_checkbox','txtselectionFlag');
	
	return  gridData;
}
function frmMpSheet_exceptionCallback(result){}


function mpSheetLoad()
{
	
	fillWithCurrentDate("dtecucmDcdate");
	 jQuery('#imgMpsmAfterimage').attr('src', "");
	 jQuery('#imgMpsBeforeImgFilename').val("");	 
	 jQuery('#imgMpsmBeforeimage').attr('src', "");
	 jQuery('#imgMpsAfterImgFilename').val("");
	loadFunctionalLocation("mpsmfunLocation","functionalLoc.mps","mpsmfunLocationValues","frmMpSheet","");
}

function frmMpSheet_deleteSuccessCallback(result)
{
	var mode =result.successData.mode; 
	if(mode=="Modify")
		navigateToPrevForm();
}

function frmMpSheet_beforeDelete()
{
	 var delMsg = "Do You Want To Delete This MPSheet ("+jQuery('#cmbmpsmKeyid').combobox('getValue')+") ?";
		if(confirm(delMsg) == false)
		{
			return false;
		}
}

function tgtImpOnCompleteGrid()
{
	/*jQuery("#tgtImpGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			var keyId= jQuery("#tgtImpGrid").jqGrid('getCell',id,name="txtCfedKeyid");
		
		},
		afterEditCell: function(rowid, name, value, iRow, iCol) {			
			 var inputControl = jQuery('#' + (iRow) + '_' + name);	
			
			 inputControl.css('text-transform', 'uppercase');
			 jQuery('#'+iRow+'_txtMpidActivitydesc').attr('maxlength','500');
			 jQuery('#'+iRow+'_txtMpidBefore').attr('maxlength','10');
			 jQuery('#'+iRow+'_txtMpidAfter').attr('maxlength','10');	
			 	 	
			 inputControl.keydown(function(e) {		
				if(value.indexOf("<input")!=-1)
				{
						
				}
				 		
				  if (e.keyCode === 9) { 		
					  //alert("tab");
					 
				  }
			 });
		 },

	});*/
}


function resultcboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
	var checked="";
	if(rowObject[0]=="1")
		checked="checked=checked";
	else
		checked="";
	return '<input id="result_checkbox'+ id +'"  type="checkbox" ' + ' onclick="if(this.checked){checkBoxClick(\''+id + '\')}else{checkBoxUnchecked(\''+id+'\')}" '+ checked  +'/>';
}

function checkBoxClick(id)
{
 	jQuery("#resultsGrid").setCell(id,"txtselectionFlag","INSERT");
}

function checkBoxUnchecked(id){
	jQuery("#resultsGrid").setCell(id,"txtselectionFlag","DELETE");
}


function mpsOnload()
{
	 if(!(jQuery('#chkHdRequiredY').is(':checked')))
	 {	  jQuery("#btnnavigateHd").attr('disabled','disabled');
	  	  jQuery('#chkHdRequiredN').attr('checked','checked');
	 }
}

/** Clear image **/
 
jQuery( "#btnImgBeforeClear" ).click(function() {
	jQuery('#imgMpsmBeforeimage').attr('src', "");
jQuery('#imgMpsBeforeImgFilename').val("");

});

jQuery( "#btnImgAfterClear" ).click(function() {
	jQuery('#imgMpsmAfterimage').attr('src', "");
jQuery('#imgMpsAfterImgFilename').val("");
});

jQuery("#btnnavigateHd").click(function(){
	if(jQuery('#chkHdRequiredY').is(':checked')==true)
		saveForm('frmMpSheet','horizontalDeplymnt.mps');
});

jQuery('#chkHdRequiredN').click(function(){
	tickOtherCheckbx('chkHdRequiredN','chkHdRequiredY');
	jQuery("#btnnavigateHd").removeAttr('class', 'easyui-button');
    jQuery("#btnnavigateHd").attr('disabled','disabled');
});

jQuery('#chkHdRequiredY').click(function(){
	tickOtherCheckbx('chkHdRequiredY','chkHdRequiredN');
	//jQuery('#btnnavigateHd').attr({'disabled':true});
	jQuery("#btnnavigateHd").attr('class', 'easyui-button');
	jQuery("#btnnavigateHd").removeAttr('disabled');
});

function mpsHDGrid_OnGridload()
{
	getHdCount();
}
function getHdCount()
{  
	var hdCount=jQuery("#mpsHDGrid").getGridParam('reccount'); 
	jQuery('#txtHdnos').val(hdCount);
}

function tickOtherCheckbx(checkBoxId,checkBoxId1)
{
	jQuery("#"+checkBoxId).attr({'checked':true});
	jQuery("#"+checkBoxId1).attr({'checked':false});

}


</script>
<form name="frmMpSheet" id="frmMpSheet" action="" method="post">
	<div style="margin-left: 4%;width:1100px;margin-top:20px;">				
		
			<div id="frmMpSheetFuntKeyIds"  >
				<div style="float: left;padding-right: 20px;">
					<input type="hidden" id="factory" name="cmbFactoryId" value="${requestScope.mpSheetBean.factoryId}" / >
					<input type="hidden" id="section" name="cmbSectionId" value="${requestScope.mpSheetBean.sectionId}"  />
				<input type="hidden" id="cell" name="cmbmpsmCellid" value="${requestScope.mpsTlMst.mpsmCellid}"  />
					<input type="hidden" id="machine" name="cmbmpsmMachineid" value="${requestScope.mpsTlMst.mpsmMachineid}"/ >
					<input type="hidden" id="flid" name="cmbmpsmFlid" value="${requestScope.mpsTlMst.mpsmFlid}"  />
					
				</div>
			</div>
			
			<div style="padding-left:6.5%">	
				<div id="mpsmfunLocation" style="width: 900px;text-align: left;margin-top: 10px; "></div>
				<div id="err_cell" class="tpm-errormsg" style="display: block;padding-right: 900px;"></div>		
						
				<table>
					
				<tr>
					<td  >
				        <div style="padding-left:0px;">
						<div> <label>MP Sheet No</label> <span style="padding-left:85px; "> <label class="mandatory-lbl">Date</label></span></div>
						<div class="easyui-paddingbfpx" style="">
							<input id="cmbmpsmKeyid" name="cmbmpsmKeyid" class="easyui-combobox"  style="width:140px;" value="${requestScope.mpsTlMst.mpsmKeyid }" disabled="${requestScope.mpSheetBean.disableMpsKeyid }" >
							<span style="padding-left:15px;"> <input id="dtempsmCreateddate" name="dtempsmCreateddate" class="easyui-datebox"  style="width:94px;" value="${requestScope.mpsTlMst.mpsmCreateddate }" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>/></span>
						</div>
				
						<div  class="mandatory-lbl" style=""> <label>Cost Center</label></div>
						
						<div class="easyui-paddingbfpx" style="">
							<input id="cmbmpsmCostcentreid" name="cmbmpsmCostcentreid" class="easyui-combobox"  style="width:255px;" value="${requestScope.mpsTlMst.mpsmCostcentreid }" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/> >
						                                                                                                                                                          
						</div>
						
					</div>	
					</td>
			
					<td >
			
			      <div style="padding-left:20px;">
						<div style="width:100px;" > <label>Equipment</label></div>
						
						<div class="easyui-paddingbfpx" style="width:255px;">
							<input id="cmbmpsmMachineid" name="cmbmpsmMachineid" class="easyui-combobox"  style="width:255px;" value="${requestScope.mpsTlMst.mpsmMachineid}" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/> >
						</div>
							
						<div style="" > <label>Work Center</label></div>
						
						<div class="easyui-paddingbfpx" style="">
							<input id="cmbmpsmWorkcentreid" name="cmbmpsmWorkcentreid" class="easyui-combobox"  style="width:255px;" value="${requestScope.mpsTlMst.mpsmWorkcentreid}"  <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>
						</div>	
				</div>
					
					</td>
			
					<td >
					
					<div style="padding-left:20px;">
						<div   class="mandatory-lbl" style=""> <label>Improvement Theme</label></div>		
						
						<div class="easyui-paddingbfpx" style="">
							<textarea rows="2" style="width:225;height:70%;resize:none;" cols="" id="txtmpsmImprovementtheme" name="txtmpsmImprovementtheme" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmImprovementtheme}</textarea>
						</div>
						
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div  style="width:100%;height:100px;padding-left:70px;" >
		
			<div closable="true" iconCls="icon-cut" >
					<div id="tabMPs" class="easyui-tabs"  style="width:900px;">
						<div title="Mp Sheet Information" style="padding:10px;">
							<table width="75%" height="400">
								<tr>
									<td height="200px" width=45% valign="top">
										<div class="mandatory-lbl" > <label>Problem</label></div>
										
										<div class="easyui-paddingbfpx">
											<textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmProblem" name="txtmpsmProblem" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmProblem}</textarea>
										</div>
								
										<div class="mandatory-lbl" > <label>Causes</label></div>
										
										<div class="easyui-paddingbfpx"><textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmCause" name="txtmpsmCause" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmCause}</textarea></div>
								
										<div class="mandatory-lbl" > <label>Before Improvement</label></div>
										<div class="easyui-paddingbfpx"><textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmBeforeimprovement" name="txtmpsmBeforeimprovement" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmBeforeimprovement}</textarea></div>
								
										<div class="mandatory-lbl" > <label>After Improvement</label></div>
										<div class="easyui-paddingbfpx"><textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmAfterimprovement" name="txtmpsmAfterimprovement" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmAfterimprovement}</textarea></div>
								
										<div> <label>Standardization of Maintenance Work</label></div>
										<div class="easyui-paddingbfpx"><textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmStandardization" name="txtmpsmStandardization" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmStandardization}</textarea></div>
										
										<div> <label>Change</label></div>
										<div class="easyui-paddingbfpx"><textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmChange" name="txtmpsmChange" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmChange}</textarea></div>
										
										<div> <label>Other</label></div>
										<div class="easyui-paddingbfpx"><textarea rows="2" style="width:260px;resize:none;" cols="" id="txtmpsmOthers" name="txtmpsmOthers" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>${requestScope.mpsTlMst.mpsmOthers}</textarea></div>
									</td>
							
									<td valign="top" height="200px" width=50% style="" >
										<div class="sub-header" style="padding-top:3px;padding-bottom:5px;">
											<b> <label>Before Conditions</label></b>
											<span style="padding-left:10px; " >
											<input type="button" class="easyui-button" value ="Image" id="dlgImgBefore" style="height:20px;" name="dlgImgPresent" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>/>
											<input type="button" class="easyui-button" value ="Clear" id="btnImgBeforeClear" name="btnImgBeforeClear" style="height:20px;" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>/>
										</span>	
										</div>
										 
										
										 
										<div style="width: 255px;height: 180px; padding-top:5px;padding-bottom:5px; border-left-style: solid;">
											<fieldset style="width: 340px;height: 160px; border: 1px solid #a4a4a4;">
											 	<div><img alt="" id="imgMpsmBeforeimage" name="imgMpsmBeforeimage" src="${requestScope.mpsTlMst.mpsmBeforeimprovement}"/></div>
											 </fieldset>
										</div>				 
										 
										<div class="sub-header" style="padding-top:0px;padding-bottom:5px;">
											<b> <label>After Conditions</label></b>
											<span style="padding-left:10px; " >
												<input type="button" class="easyui-button" value ="Image" id="dlgImgAfter" name="dlgImgAfter" style="height:20px;" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>
												<input type="button" class="easyui-button" value ="Clear" id="btnImgAfterClear" name="btnImgAfterClear" style="height:20px;" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>/>
										</span>
										</div>
										 
										
										 
										<div style="width: 255px;height: 186px; padding-top:5px;padding-bottom:5px;"  >
										 	<fieldset style="width: 340px;height: 160px;border: 1px solid #a4a4a4;">
												<div><img alt="" id="imgMpsmAfterimage" name="imgMpsmAfterimage" src="${requestScope.mpsTlMst.mpsmAfterimprovement}"/></div>
											 </fieldset>
										</div>	 
									</td>
								</tr>
							</table>
						</div>
						
						<div title="Cost and Improvement" style="padding:10px;">
							<div class="sub-header" style="padding-top:3px;padding-bottom:5px;"><b>Effect of Cost Estimation</b></div>
						 	<table width=75%>
						 		<tr>
						 			<td width=50% valign="top">
						 			 	<div style="padding-top:3px;padding-bottom:5px;">
						 					<input type="checkbox" name="chkmpsmIscosteffective" id="chkmpsmIscosteffective" <c:out value = "${ requestScope.mpsTlMst.mpsmIscosteffective == 'Y' ? ' checked':''}"/> <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>/> <label>Possible</label>
						 				</div>
						 
						 				<div style="padding-top:3px;padding-bottom:5px;">
						 					<input type="checkbox" name="chkmpsmIscosteffective" id="chkmpsmIscosteffective" <c:out value = "${ requestScope.mpsTlMst.mpsmIscosteffective == 'N' ? ' checked':''}"/> <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>/> <label>Not Possible</label>
						 				</div>
						 
						 			</td>
						 
						 			<td width=50%>
						 				<div><label>Total Amount</label> </div> 
						 				<div class="easyui-paddingbfpx">
						 					<input type="text" class="easyui-text"; id="txtmpsmTotalamount" name="txtmpsmTotalamount" style=" width: 255px; height : 21px;" value="${requestScope.mpsTlMst.mpsmTotalamount }" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>
						 				</div>
						 				
						 				<div><label>Effective Amount Reported</label></div>
						 				<div class="easyui-paddingbfpx">
						 					<input type="text" class="easyui-text"; id="txtmpsmEffsavingsreported" name="txtmpsmEffsavingsreported" style="width: 255px; height : 21px;" value="${requestScope.mpsTlMst.mpsmEffsavingsreported }" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>></div>
						 				</td>
						 
						 				<td width=50%>
						 					<div style="padding-left:20px\9;"> <label> Registration Code</label> </div>
						 					<div class="easyui-paddingbfpx" style="padding-left:20px\9;">
						 						<input type="text" class="easyui-text" style=" width: 255px; height : 21px;" id="txtmpsmRegistrationcode" name="txtmpsmRegistrationcode" value="${requestScope.mpsTlMst.mpsmRegistrationcode }" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>
						 					</div>
						 					
						 					<div style="padding-left:20px\9;"> <label>Control No</label></div>
						 					<div class="easyui-paddingbfpx" style="padding-left:20px\9;">
						 						<input type="text" class="easyui-text"; style=" width: 255px; height : 21px;" id="txtmpsmControlno" name="txtmpsmControlno" value="${requestScope.mpsTlMst.mpsmControlno }" <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>>
						 					</div>
						 				</td>
						 			</tr>
								</table>
						 
						 		<div id="err_chkmpsmIscosteffective"  class="tpm-errormsg" style="display: block;padding-right:0px;"></div>	
						 
						 		<div class="sub-header" style="padding-top:3px;padding-bottom:5px;">
						 			<b> <label>Target of Improvement</label></b>
						 		</div>
						 	 	<table id="tgtImpGrid" style="width:70%"><tr><td/></tr></table>
						 		<div id="pager"></div>
							</div>
						
							<div title="HD" style="padding:10px;">
								<div class="sub-header" style="padding-top:3px;padding-bottom:5px;">
								<b> <label>Horizontal Deployment Details</label></b></div>
								<table width=75%>
									<tr>
										<td width=50% valign="top">
											<div class="mandatory-lbl" > 
												<label> HD Required</label>
												<input id="chkHdRequiredY" name="chkHdRequiredY" type="checkbox" value="Y" <c:out value = "${ requestScope.mpsTlMst.mpsmIshdrequired == 'Y' ? ' checked':''}"/> <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>   /> <label> Yes</label>
												<input id="chkHdRequiredN"  name="chkHdRequiredN" type="checkbox" value="N" <c:out value = "${ requestScope.mpsTlMst.mpsmIshdrequired == 'N' ? ' checked':''}"/>  <c:out value = "${ requestScope.mpSheetBean.disableForm == true ? ' disabled':''}"/>  /> <label>No</label> 
											</div>
							
											<div  > <label> HD Nos</label></div>
											<div class="easyui-paddingbfpx" >
												<input type="text" class="easyui-text" id="txtHdnos" style=" width: 255px;padding-top:-10px\9">
												<span  style="padding-left:5px;" ><input type="button" class="easyui-button" id="btnnavigateHd" value="..."/></span>
											</div>
										</td>
									</tr>
						
									<tr>
										<td width=100%>
											<div class="easyui-paddingbfpx" > 
												<div >
													<div class="sub-header"class="easyui-paddingbfpx" align="center" >
														<b> <label >Responsibility and Target for HD</label></b>
													</div>
													
													<table id="mpsHDGrid" style="width:100%"><tr><td/></tr></table>
													
													<div id="mpsHDPager"></div>
						
												</div>
											</div>
										</td>
									</tr>
							</table>	
						
							<table id="HD" style="width:100%">
								<tr><td/></tr>
							</table>
							<div id="pager1"></div>	
						</div>
						
						<div title="Result Creation Details" style="padding:10px;">
							<table id="resultsGrid" style="width:100%">
								<tr><td/></tr>
							</table>
							
							<div id="pager"></div>	
						</div>
					</div>
				</div>
			</div>
		
			<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
			<input type="hidden" id="imgMpsBeforeImgFilename" name="imgMpsBeforeImgFilename" value="${requestScope.mode}" />
			<input type="hidden" id="imgMpsAfterImgFilename" name="imgMpsAfterImgFilename" value="${requestScope.mode}" />
		</div>
	
</form>

