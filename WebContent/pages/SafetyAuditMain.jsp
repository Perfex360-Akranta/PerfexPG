<script>
jQuery(document).ready(function(){
	initialiseForm('frmSafetyAudit');
	jQuery('#submitForm').val('frmSafetyAudit');
	 imageUpload(jQuery( "#dlgImg" ),'ImageUpload.commonFilter','dlgImg',"imgSafetyimage","imgSafetyImgFilename");
	 var factId = jQuery("#frmSafetyAudit input[id='factory']").val();
	 var sectionId = jQuery("#frmSafetyAudit input[id='section']").val();
	 var cellId = jQuery("#frmSafetyAudit input[id='cell']").val();
	 var machId = jQuery("#frmSafetyAudit input[id='machine']").val();
	 var flid = jQuery("#frmSafetyAudit input[id='flid']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
	 loadFunctionalLocation("frmsafetyfunloc","functionalLoc.commonFilter","safetyfunLocationValues","frmSafetyAudit",dataStr);
	 jQuery( "#btnImgClear" ).click(function() {
			jQuery('#imgSafetyimage').attr('src', "");
			jQuery('#imgSafetyImgFilename').val("");
			});
	 var imgurl = jQuery('#img').val();
	 jQuery('#imgSafetyimage').attr('src', imgurl);
	 var val = jQuery('#val').val();
	 jQuery('#txtSafmFrequency').val(val);
});
function frmSafetyAudit_FuntLocHierarchy_SuccessCallBack(result)
{
	 
	
	var flid = result.flid;
	jQuery("#txtSafmFlid").val(flid);
	
}
function frmSafetyAudit_deleteSuccessCallback(result)
{
	
	alert(result.successData.msg);
	clearForm("frmSafetyAudit");
	
	  
}
function frmSafetyAudit_successsCallback(response){
	
	//showCommonErrorMsg(response);
	clearForm("frmSafetyAudit");
	jQuery("#flid").val(" ");
	
}
</script>
<form id="frmSafetyAudit" name="frmSafetyAudit">
	<div id="wrapper" style="padding-left:20%;width:80%">
		<table >
			<tr>
				<td colspan="3">
						<div id="frmSafetyAuditFuntKeyIds"  >							
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>
								<input type="hidden" id="flid" name="txtSafmFlid"  value="${requestScope.safety.safmFlid}"></input>							
							</div>						
						<div id="frmsafetyfunloc" style="width:97%;"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx"><label>Item</label></div>
					<div class="easyui-paddingbfpx">
						<input type="text" id="txtSafmItem" name="txtSafmItem" maxlength="30" style="width:255px;height:21px;" value="${requestScope.safety.safmItem}" class="easyui-text" />
					</div>
				</td>
				<td style="padding-left:10px;">
					
				</td>
				<td style="padding-left:10px; width : 305px;"  rowspan="3"> 
							<img id="imgSafetyimage" name="imgSafetyimage" src="" width="200px" height="100px"  />
					<div  style="padding-top: 10px;">
							<span style="padding-right: 136px;">
								<input	type="button" class="easyui-button" id="dlgImg"	name="dlgImg" value="+" /> 
							</span>
							<input	type="button" class="easyui-button" id="btnImgClear"name="btnImgClear" value="-"  style=" width : 29px;"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx"><label>Check Point</label></div>
					<div class="easyui-paddingbfpx">
						<textarea id="txtSafmCheckpoint" maxlength="495" name="txtSafmCheckpoint" rows="2" cols="80" style="width:255px; height:44px;text-transform: uppercase;">${requestScope.safety.safmCheckpoint}</textarea>
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx"><label>How To Check</label></div>
					<div class="easyui-paddingbfpx">
						<textarea id="txtSafmHowtocheck" maxlength="495" name="txtSafmHowtocheck" rows="2" cols="80" style="width:255px; height:44px;text-transform: uppercase;">${requestScope.safety.safmHowtocheck}</textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx"><label>Tools Used</label></div>
					<div class="easyui-paddingbfpx">
						<textarea id="txtSafmToolsused" maxlength="495" name="txtSafmToolsused" rows="2" cols="80" style="width:255px; height:44px;text-transform: uppercase;">${requestScope.safety.safmToolsused}</textarea>
					</div>
				</td>
				<td style="padding-left:10px;" valign="top">
					<div class="easyui-paddingbfpx"><label>Frequency</label></div>
					<div class="easyui-paddingbfpx">
						<select id="txtSafmFrequency" name="txtSafmFrequency" class="easyui-text" style="width:100px;height:21px;">
							<option value=""></option>
							<option value="D">Daily</option>
							<option value="M">Monthly</option>
							<option value="Q">Quarterly</option>
							<option value="H">Half Yearly</option>
							<option value="Y">Yearly</option>
						</select>
					</div>
				</td>
			</tr>
		</table>
</div>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnSafmKeyid" name="hdnSafmKeyid" value="${requestScope.safety.safmKeyid}"/>
<input type="hidden" id="imgSafetyImgFilename" name="imgSafetyImgFilename" value=""/>
<input type="hidden" id="img" name="img" value="${requestScope.safety.safmImagename}"/>
<input type="hidden" id="val" name="val" value="${requestScope.safety.safmFrequency}"/>
</form>