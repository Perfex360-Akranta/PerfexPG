<script>
jQuery(document).ready(function(){

	initialiseForm('frmDesign');
	
	 //jQuery(function () { jQuery('.rpn').click(updateTotal); });
	jQuery('#submitForm').val('frmDesign'); 
	fillComboBox('frmDesign','cmbFormat', '');
	fillComboBox('frmDesign','cmbPfmaResponsible', 'employee.commonFilter');
	formatDateBox('dtePfmaTargetdate','dd-MMM-YYYY');
	fillComboBox('frmDesign','cmbPfmaPreparedby', 'employee.commonFilter');
	formatDateBox('dtePfmadate','dd-MMM-YYYY');
	fileManagerPopUp("","ABN","frmDesign","btnfilemgr","abnFilemgr");
	processGridnew("FMEAProcesspop_input.fmeaf","q=2","equipmentGrid","pagerequipment","","");
	processGridnew("FMEAFormatpop_input.fmeaf","q=2","designGrid","pager","","");
	var factId = jQuery("#frmDesign input[id='factory']").val();
    var sectionId = jQuery("#frmDesign input[id='section']").val();
    var cellId = jQuery("#frmDesign input[id='cell']").val();
    var machId = jQuery("#frmDesign input[id='machine']").val();
    var flid = jQuery("#flid").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
    loadFunctionalLocation("designfunlocation","functionalLoc.commonFilter","fmeafunLocationValues","frmDesign",dataStr);
    processGridnew("FMEAPillar_input.fmeaf","q=2","pillarGrid","pillarpager","","");
    processGridnew("FMEARootCause_input.fmeaf","q=2","rootCauseGrid","rootCausepager","","");
    
  
  
});
function btnfilemgr_click()
{
	//alert("11");
   // var documentNo =jQuery("#hdnabnkeyID").val();
   
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"ABN","","","");
	}
	
}
function frmDesign_deleteSuccessCallback(result)
{
	
	alert(result.successData.msg);
	
	  
}

function frmDesign_FuntLocHierarchy_SuccessCallBack(result){
	 
	
	var flid = result.flid;
	jQuery("#cmbPfmaFlnid").val(flid);
	
}
function rpn(){
	var occ = jQuery("select[id='txtPfmaOccurence'] option:selected").val();
	var sev = jQuery("select[id='txtPfmaSeverity'] option:selected").val();
	var det = jQuery("select[id='txtPfmaDetection'] option:selected").val();
	var rpn = occ*sev*det;
	jQuery("#txtPfmaRPN").val(rpn);
}
function rp(){
	var occ = jQuery("select[id='txtOccurence'] option:selected").val();
	var sev = jQuery("select[id='txtSeverity'] option:selected").val();
	var det = jQuery("select[id='txtDetection'] option:selected").val();
	var rpn = occ*sev*det;
	jQuery("#txtRPN").val(rpn);
}
function btnFormatter(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	if(id==1)
	return '<input type="button" class="easyui-button" id="btnjh" name="btnViewTemplate" value="JH" style="height: 15px; width: 50px; margin-top: 0px;" />';
	if(id==2)
		return '<input type="button" class="easyui-button" id="btnKZN" name="btnViewTemplate" value="KZN" style="height: 15px; width: 50px; margin-top: 0px;" />';
	if(id==3)
		return '<input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="OPL" style="height: 15px; width: 50px; margin-top: 0px;" />';
	if(id==4)
		return '<input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="PM" style="height: 15px; width: 50px; margin-top: 0px;" />';
	if(id==5)
		return '<input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="ET" style="height: 15px; width: 50px; margin-top: 0px;" />';
	if(id==6)
		return '<input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="SOP" style="height: 15px; width: 50px; margin-top: 0px;" />';
	if(id==7)
		return '<input type="button" class="easyui-button" id="btnViewTemplate" name="btnViewTemplate" value="..." style="height: 15px; width: 50px; margin-top: 0px;" />';

	}
</script>
<form name="frmDesign" id="frmDesign" >
<div id="wrapper" style="width:80%" >

<div  style=" margin-left:20px; margin-top:10px;margin-top:20px\9;">
<table>
<tr >
<td colspan="3">
		<div id="frmDesignFuntKeyIds"  >							
						<input type="hidden" id="factory" name="factory"  value="" ></input>
						<input type="hidden" id="section" name="section"  value=""></input>
						<input type="hidden" id="cell"    name="cell"     value=""></input>
						<input type="hidden" id="machine" name="machine"  value=""></input>
						<input type="hidden" id="flid" name="cmbPfmaFlnid" value=" "  ></input>							
					</div>						
				<div id="designfunlocation" style="width:103%;"></div>	
</td>
</tr>
<tr>
<td style=" padding-top:10px;">
<div class="easyui-paddingbfpx"  ><label >FMEA No</label></div>
		<div class="easyui-paddingbfpx" >
			<input type="text" id="txtFMEANo" name="txtFMEANo"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
		</div>
</td>
<td style=" padding-top:10px; padding-left:10px;">
<div class="easyui-paddingbfpx"  ><label  >Date</label><label style="padding-left:107px;" >Prepared By</label></div>
		<div class="easyui-paddingbfpx" >
			
			<input type="text" id="dtePfmadate" name="dtePfmadate"  maxlength="25" class="easyui-text" style="width:120px;width:115px\9; height: 21px;"value=""/>
			<span style="padding-left:10px;"><input type="text" id="cmbPfmaPreparedby" name="cmbPfmaPreparedby"  maxlength="25" class="easyui-text" style="width:120px;width:115px\9; height: 21px;"value=""/></span>
		</div>
</td>
<td style="padding-top:0px;">
		 <div style="position:relative;">
			 <span  id="abnFilemgr" style="position:absolute;right:5%;right:5%\9;top:0px;top:0px\9;" >
             </span> 
             </div>
		</td>
</tr>
<tr>
<td style="padding-top:10px;">
<div class="easyui-paddingbfpx"  ><label >Core Team</label></div>
		<div class="easyui-paddingbfpx" >
			<input type="text" id="txtPfmaCoreteam" name="txtPfmaCoreteam"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
			
		</div>
</td>
<td style=" padding-top:10px; padding-left:10px;">
<div class="easyui-paddingbfpx" ><label class="mandatory-lbl" >Process Step</label></div>
<div class="easyui-paddingbfpx" >

	<input type="text" id="txtPfmaProcessstep" name="txtPfmaProcessstep" class="easyui-text"  maxlength="25" style="width:255px; height: 21px;"value=""/>
</div>
</td>
<td style=" padding-top:10px; padding-left:10px;">
<div class="easyui-paddingbfpx" ><label class="mandatory-lbl" >Key Process Input</label></div>
<div class="easyui-paddingbfpx" >

	<input type="text" id="txtPfmaKeyprocessinput" name="txtPfmaKeyprocessinput" class="easyui-text"  maxlength="25" style="width:255px; height: 21px;"value=""/>
</div>
</td>

</tr>
<tr>
<td>
<div style="padding-top:10px;" class="easyui-paddingbfpx" ><label>Potential Failure Mode</label></div>
<div class="easyui-paddingbfpx" >
<textarea rows="2" cols="80" style="width:255px;height:44px;text-transform: uppercase;" id="txtPfmaPotentialfailmode" name="txtPfmaPotentialfailmode" ></textarea></div>
</td>
<td style="padding-left:10px;">
<div class="easyui-paddingbfpx"  style="padding-top:10px;"><label>Potential Effect(s) of Failure</label></div>
<div class="easyui-paddingbfpx" >
<textarea rows="2" cols="80" maxlength="495" style="width:255px;text-transform:uppercase; height:44px;" id="txtPfmaPotentialeffectfail" name="txtPfmaPotentialeffectfail" ></textarea></div>
</td>
<td style="padding-left:10px;">
<div style="padding-top:10px;" class="easyui-paddingbfpx" ><label>Potential Cause(s)/ Mechanism(s) of failure</label></div>
<div class="easyui-paddingbfpx" >
<textarea rows="2" cols="80" style="width:255px;height:44px;text-transform: uppercase;" id="txtPfmaPotentialcause" name="txtPfmaPotentialcause" >${requestScope.design.pfmaPotentialfailmode}</textarea></div>
</td>

</tr>
<tr>
<td valign="top">

<div style="padding-top: 10px;" class="easyui-paddingbfpx" >
		<label>Severity(S)</label><label style="padding-left:73px;">Occurrence(O)</label>
</div>
<div class="easyui-paddingbfpx" >
<select  id="txtPfmaSeverity" name="txtPfmaSeverity" onchange="rpn()" class="easyui-text" style="width:120px; height: 21px;">
					<option value=" "></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
</select>
<span style="padding-left:10px;"><select  id="txtPfmaOccurence"  name="txtPfmaOccurence"  onchange="rpn()" class="easyui-text" style="width:120px; height: 21px;">
					<option value=" "></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
</select></span>
</div>
</td>

<td style="padding-left:10px;" valign="top">
<div style="padding-top: 10px;" class="easyui-paddingbfpx" >
		<label>Detection(D)</label><label style="padding-left:64px;">RPN(S*O*D)</label>
</div>
<div class="easyui-paddingbfpx" >
<select  id="txtPfmaDetection" name="txtPfmaDetection" onchange="rpn()"  class="easyui-text" style="width:120px; height: 21px;">
					<option value=" "></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
</select>
<span style="padding-left:10px;"><input type="text" id="txtPfmaRPN" name="txtPfmaRPN" class="easyui-text"  maxlength="25" style="width:120px; height: 21px;text-align: right;"value="${requestScope.design.pfmaItem}"/></span>
</div>


</td>
<td style="padding-left:10px;" >
<div class="easyui-paddingbfpx"  style="padding-top:10px;"><label>Current  Controls</label></div>
<div class="easyui-paddingbfpx" >
<textarea rows="2" cols="80" style="width:255px; height:44px;text-transform: uppercase;" maxlength="495" id="txtPfmaCurrentcontrol" name="txtPfmaCurrentcontrol" ></textarea></div>
</td>
</tr>
<tr>
<td colspan="4">
<div class="sub-header" style="text-align: left;float:left;width:810px; width:900px\9;height:18px\9;position:relative;margin-right:4%;margin-left:0%\9;">
				<span style="position:absolute;">Process FMEA</span>
      	</div>
</td>
</tr>
<tr>
<td style="padding-left:10px;padding-top:10px;">
<label> Process Step </label>
<div class="easyui-paddingbfpx" >
	<input type="text" id="txtPfmaProcessStep" name="txtPfmaProcessStep" class="easyui-text"  maxlength="25" style="width:255px; height: 21px;"value=""/>
</div>
</td>
<td style="padding-left:10px;padding-top:10px;">
<label> Key Process Input </label>
<div class="easyui-paddingbfpx" >
	<input type="text" id="txtPfmaProcess" name="txtPfmaProcess" class="easyui-text"  maxlength="25" style="width:255px; height: 21px;"value=""/>
</div>
</td>
<td style="padding-left:10px;padding-top:10px;">
	<div >	
						<input type="button" class="easyui-button" id="btnupdate"
									name="btnupdate" style="width:100px;height:21px;" value="Insert" />
									
						<input type="button" class="easyui-button" id="btndelete"
									name="btndelete"style="width:100px;height:21px;" value="Delete" />
									</div>
</td>
</tr>
</table>
<div style="padding-top:10px;float: left;margin-left:0%\9;">
<table id='equipmentGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagerequipment'></div>
</div>
<div class="sub-header" style="text-align: left;float:left;  width:1018px; width:1000px\9;height:18px\9;position:relative;margin-right:4%;margin-left:0%\9;">
				<span style="position:absolute;">Review</span>
      	</div>

		<table>
				<tr style="padding-top: 10px;">
					<td>
						<div class="easyui-paddingbfpx"  ><label>Recommended Action(s)</label></div>
						<div class="easyui-paddingbfpx"  >
						<textarea rows="2" cols="80" style="width:255px; height:44px;text-transform: uppercase;" maxlength="495" id="txtPfmaRecommendedaction" name="txtPfmaRecommendedaction" >${requestScope.design.pfmaRecommendedaction}</textarea></div>
					</td>
					<td style="padding-left:10px;" valign="top">
							<div  class="easyui-paddingbfpx"  ><label>Responsible</label>
							</div>
							<div class="easyui-paddingbfpx" >
									<input type="text" id="cmbPfmaResponsible" name="cmbPfmaResponsible" maxlength="25" class="easyui-text" style="width:255px; height:21px;" value="${requestScope.design.pfmaResponsible}"/>
							</div>
					</td>
					<td style="padding-left:10px;" valign="top">
							<div class="easyui-paddingbfpx"><label>Target Date</label></div>
							<div><input type="text" id="dtePfmaTargetdate" name="dtePfmaTargetdate" maxlength="25" class="easyui-text" style="width:85px; height:21px;" value="${requestScope.design.pfmaTargetdate}"/></div>
					</td>
				</tr>
				<tr style="padding-top:10px;">
					<td>
					<div class="easyui-paddingbfpx"  ><label>Action Taken</label></div>
					<div class="easyui-paddingbfpx" >
					<textarea rows="2" cols="80" style="width:255px;height:44px;text-transform: uppercase;" maxlength="495" id="txtPfmaActiontaken" name="txtPfmaActiontaken" >${requestScope.design.pfmaActiontaken}</textarea></div>
					</td>
					<td style="padding-left:10px;" valign="top">
						
						<div  class="easyui-paddingbfpx" >
								<label>Severity(S)</label><label style="padding-left:73px;">Occurrence(O)</label>
						</div>
						<div class="easyui-paddingbfpx" >
						<select  id="txtSeverity" name="txtPfmaSeverity" onchange="rp()"  class="easyui-text" style="width:120px; height: 21px;">
											<option value=" "></option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
						</select>
						<span style="padding-left:10px;"><select  id="txtOccurence" name="txtPfmaOccurence" onchange="rp()"  class="easyui-text" style="width:120px; height: 21px;">
											<option value=" "></option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
						</select></span>
						</div>
					</td>
				
						<td style="padding-left:10px;" valign="top">
							<div  class="easyui-paddingbfpx" >
									<label>Detection(D)</label><label style="padding-left:34px;padding-left:24px\9;">RPN(S*O*D)</label>
							</div>
							<div class="easyui-paddingbfpx" >
							<select  id="txtDetection" name="txtPfmaDetection" onchange="rp()"  class="easyui-text" style="width:90px;width:85px\9; height: 21px;">
												<option value=" "></option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
							</select>
							<span style="padding-left:10px;"><input type="text" id="txtRPN" name="txtRPN" class="easyui-text"  maxlength="25" style="width:90px;width:90px\9; height: 21px;text-align: right;"value="${requestScope.design.pfmaItem}"/></span>
							</div>
						</td>
				</tr>
				<tr>
<td style="padding-left:10px;padding-top:10px;">
</td>
<td>
</td>
<td style="padding-left:10px;padding-top:10px;">
	<div >	
						<input type="button" class="easyui-button" id="btnupdate"
									name="btnupdate" style="width:100px;height:21px;" value="Insert" />
									
						<input type="button" class="easyui-button" id="btndelete"
									name="btndelete"style="width:100px;height:21px;" value="Delete" />
									</div>
</td>
</tr>
	<tr>
<td colspan="3">
<div style="padding-top:10px;float: left;margin-left:0%\9;">
<table id='designGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
</td>
</tr>
</table>
<div class="sub-header" style="text-align: left;float:left;width:770px; width:900px\9;height:18px\9;position:relative;margin-right:4%;margin-left:0%\9;">
				<span style="position:absolute;">Sustenance Action</span>
      	</div>
<table>		
<tr>
<td >
		<table id="rootCauseGrid" ></table>
		<div id="rootCausepager"></div>
		</td>
		<td style="padding-left: 20px;">
		<table id="pillarGrid" ></table>
		<div id="pillarpager"></div>
		</td>
		
</tr>
</table>
</div>
</div>
<input type="hidden" id="mode" name="mode" /> 
<input type="hidden" id="txtPfmaKeyid" name="txtPfmaKeyid" value="${requestScope.design.pfmaKeyid}" />
<input type="hidden"  id="txtPfmaProcessid"	name="txtPfmaProcessid" value="PRS0000001" /> 
<%-- ${requestScope.design.pfmaProcessid}" --%>
</form>
