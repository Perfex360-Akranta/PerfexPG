<script>
jQuery(document).ready(function(){	
	
	
	initialiseForm('frmcountrmsr');
	formatDateBox('dtedate','dd-MMM-yyyy');

	var factId = jQuery("#frmcountrmsr input[id='factory']").val();
    var sectionId = jQuery("#frmcountrmsr input[id='section']").val();
    var cellId = jQuery("#frmcountrmsr input[id='cell']").val();
    var machId = jQuery("#frmcountrmsr input[id='machine']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
   loadFunctionalLocation("countrmsrfunLocation","functionalLoc.cmsr","countrmsrfunLocationValues","frmcountrmsr",dataStr);
	fillComboBox("frmcountrmsr","cmbdocumentno ","DocNo.cmsr");
 
   
});
</script>
<form name="frmcountrmsr" id="frmcountrmsr" action="" method="post">
	<div id="wrapper" style="height: 114%">
		<div align="center"  class="main-cntborder" style="width:800;height: 497px;" >
			
			
				<table width=80%> 
					<tr>
						<td  style="width:50%;padding-left:50px" valign='top'  class="easyui-paddingbfpx cntborder" >
						<div id="frmcountrmsrFuntKeyIds" >
							<input type="hidden" id="factory" name="cmbcountrmsrFactoryid" ></input>
							<input type="hidden" id="section" name="cmbcountrmsrSectionid" ></input>
							<input type="hidden" id="cell" name="cmbcountrmsrCellid"  ></input>
							<input type="hidden" id="machine" name="cmbcountrmsrMachineidhdn" ></input>
						</div>
						<div id="countrmsrfunLocation"  style="width: 281%;"></div>
					
						<div>
			  				<span> <label > Document No</label></span>
						 	<span> <label class="mandatory-lbl" style="padding-left:129px;">Date</label></span>
						 </div> 
							<div style="padding-bottom: 8px;padding-right:10px;"> 
		                		<input id="cmbdocumentno" class="easyui-combobox" name="cmbdocumentno"  style="height: 22px;width:205px;">
									
							<input class="easyui-combobox" name="dtedate" type="text" id="dtedate" style="width:100px;"type="text" value="" />
							</div>
							<div class="sub-header" style="padding-right:2%;margin-top:-1%">
								<label><b>Counter Measure</b></label>
							</div>
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl"><b>Title</b></label> </div>
                 	 		<div class="easyui-paddingbfpx">
                				<textarea id="txttitle" name="txttitle"  rows="4" cols="36" maxlength="250" ></textarea>
							</div>
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl"><b>Theme</b></label> </div>
                 	 		<div class="easyui-paddingbfpx">
                				<textarea id="txttheme" name="txttheme"  rows="4" cols="36" maxlength="250" ></textarea>
							</div>
							<div class="easyui-paddingbfpx"><label class="">Improvement</label> </div>
                 	 		<div class="easyui-paddingbfpx">
                				<textarea id="txtimprovement" name="txtimprovement"  rows="4" cols="36" maxlength="250" ></textarea>
							</div>
							
						</td>
						<td valign='top' class="cntborder" style="width:50%;" class="easyui-paddingbfpx">
						<div class="sub-header" style="margin-top: 23%"><label > Condition</label></div>
						<div id="tabCondition" class="easyui-tabs"   style="height:380px;width:409px;padding-left:2%;margin-top:-1%;" >
							<div title="Before Condition" style="padding:10px">
								 <div class="easyui-paddingbfpx"> 
								 	<div class="sub-header" style=""><label><b>Before Description</b></label></div>
		                			<textarea id="txtBeforedesc" name="txtBeforedesc" rows="4" cols="15" maxlength="200"  style=" width : 350px;padding-left:2%"></textarea>
								</div>
							<div class="sub-header" style="padding-left:2%" > Before Condition<span style=" padding-left:5px;font-size:9px;height:20px;">width:8.1 cm height:3.8 cm
							<input class="easyui-button" type="button" value ="Image" id="dlgImgAfter"style="height:20px" />
							<input class="easyui-button" type="button" value ="Clear Image" id="btnImgAfterClear" onclick="" style="height:20px" /></span>
							</div>
							</div>
							<div title="After Condition" style="padding:10px;">
							 	<div class="easyui-paddingbfpx"> 
								 	<div class="sub-header" style=""><label><b>After Description</b></label></div>
		                			<textarea id="txtAfterdesc" name="txtAfterdesc" rows="4" cols="15" maxlength="200"  style=" width : 350px;padding-left:2%"></textarea>
								</div>
									<div class="sub-header" style="padding-left:2%" > After Condition<span style=" padding-left:5px;font-size:9px;height:20px;">width:8.1 cm height:3.8 cm
									<input class="easyui-button" type="button" value ="Image" id="dlgImgAfter"style="height:20px" />
									<input class="easyui-button" type="button" value ="Clear Image" id="btnImgAfterClear" onclick="" style="height:20px" /></span>
									</div>
							
							</div>
							<div title="Benefit" style="padding:10px;">
							<div class="easyui-paddingbfpx"> 
								 	<div class="sub-header" style=""><label class="mandatorylbl"><b>Benefit</b></label></div>
		                			<textarea id="txtBenefit" name="txtBenefit" rows="6" cols="15" maxlength="200"  style=" width : 350px;padding-left:2%"></textarea>
								</div>
								<div class="easyui-paddingbfpx"> 
								 	<div class="sub-header"><label><b>Remarks</b></label></div>
		                			<textarea id="txtRemarks" name="txtRemarks" rows="6" cols="15" maxlength="200"  style=" width : 350px;padding-left:2%"></textarea>
								</div>
								
						</div>
						</div>
							
						</td>
					</tr>
					
				</table>		
		</div>
	
	</div>


</form>