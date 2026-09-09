<script>
jQuery(document).ready(function(){

	initialiseForm('frmFMEA');
	fillComboBox('frmFMEA','cmbFormat', '');
	fillComboBox('frmFMEA','cmbResp', 'employee.commonFilter');
	formatDateBox('dteTargetDate','dd-MMM-YYYY');
	var factId = jQuery("#frmFMEA input[id='factory']").val();
    var sectionId = jQuery("#frmFMEA input[id='section']").val();
    var cellId = jQuery("#frmFMEA input[id='cell']").val();
    var machId = jQuery("#frmFMEA input[id='machine']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
    loadFunctionalLocation("fmeafunLocation","functionalLoc.brdn","fmeafunLocationValues","frmMom",dataStr);

    var cmb =jQuery('#cmbFormat').val();
	
	if(cmb == "D"){
		jQuery('#divProcess').hide();
		
		jQuery('#divFunctional').hide();
		
	}
});
function  frmFMEAcmbFormat_onSelect(record)
{
	
	if(record.id == 'D')
	{   
		
		
		jQuery('#divItem').show();
		jQuery('#divProcess').hide();
		
		
		jQuery('#divFunctional').hide();
		
	}
	else if(record.id == 'P'){
	
		jQuery('#divProcess').show();
		
		
		jQuery('#divItem').hide();
		
		jQuery('#divFunctional').hide();
		
		}
	else 	{
		
		jQuery('#divFunctional').show();
		
		jQuery('#divItem').hide();
		jQuery('#divProcess').hide();
		
		
	}
}
</script>
<form name="frmFMEA" id="frmFMEA" >
<div id="wrapper" style="padding-left:-100px;">
<div class="main-cntborder"  style="height:100%; width:77%" >
<div  style="float:left; margin-left:40px; margin-top:20px;">
<table>
<tr >
<td colspan="3">
				<div id="frmFMEAFuntKeyIds"  >							
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>							
							</div>						
						<div id="fmeafunLocation" style="width:700px;"></div>	
</td>
</tr>
<tr>
<td style="padding-top:10px;">
<div class="easyui-paddingbfpx" >
<label>FMEA Format</label>
</div>
<div class="easyui-paddingbfpx" >
<select  id="cmbFormat" name="cmbFormat" class="easyui-text" style="width:255px; height:21px;">
<option value="D">Design FMEA</option>
<option value="P">Process FMEA</option>
<option value="E">Equipment FMEA</option>
</select>
</div>
</td>


</tr>
<tr id="divItem">

<td >
<div class="easyui-paddingbfpx" ><label >Item</label></div>
<div class="easyui-paddingbfpx" >

	<input type="text" id="txtItem" name="txtItem" class="easyui-text"  maxlength="25" style="width:255px; height: 21px;"value=""/>
</div>
</td>
<td style="padding-left:10px;">
	<div class="easyui-paddingbfpx" ><label >Function</label></div>
			<div  class="easyui-paddingbfpx" >
				<input type="text" id="txtFunction" name="txtFunction"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
			</div>

</td>

<tr id="divProcess">
<td >
	
		<div class="easyui-paddingbfpx"  style="padding-top: 10px;"><label >Process Step</label></div>
		<div class="easyui-paddingbfpx" >
			<input type="text" id="txtProcessStep" name="txtProcessStep"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
		</div>
</td>
<td style="padding-left:10px;">
		<div "class="easyui-paddingbfpx"  style="padding-top: 10px;"><label >Key Process Input</label></div>
			<div  class="easyui-paddingbfpx"  >
				<input type="text" id="txtProcessInput" name="txtProcessInput"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
			</div>
	
</td>
<tr id="divFunctional">
<td >

		<div class="easyui-paddingbfpx"  style="padding-top: 10px;"><label >Functional Failure</label></div>
		<div class="easyui-paddingbfpx"  >
			<input type="text" id="txtFunctionalFailure" name="txtFunctionalFailure" class="easyui-text" maxlength="75" style="width:255px; height: 21px;"value=""/>
		</div>
</td>
<td style="padding-left:10px;">
		<div class="easyui-paddingbfpx"  style="padding-top: 10px;"><label >Component</label></div>
			<div class="easyui-paddingbfpx"  >
				<input type="text" id="txtComponent" name="txtComponent"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
			</div>
</td>
<td style="padding-left:10px;padding-top:10px;">
			<div class="easyui-paddingbfpx"  ><label >Function</label></div>
			<div class="easyui-paddingbfpx"  >
				<input type="text" id="txtFunctions" name="txtFunctions"  maxlength="25" class="easyui-text" style="width:255px; height: 21px;"value=""/>
			</div>

</td>


</tr>
<tr>
<td>
		<div style="padding-top: 10px;" class="easyui-paddingbfpx" >
		<label>Severity(S)</label><label style="padding-left:96px;">Occurrence(O)</label>
</div>
<div class="easyui-paddingbfpx" >
<input type="text" id="txtSeverity" name="txtSeverity"  maxlength="25" class="easyui-text" style="width:150px; height: 21px;"value=""/>
<span style="padding-left:1px;"><input type="text" id="txtOccurrence" name="txtOccurence"  maxlength="25" class="easyui-text" style="width:100px; height: 21px;"value=""/></span>
</div>
<div style="padding-top:10px;" class="easyui-paddingbfpx" ><label>Potential Failure Mode</label></div>
<div class="easyui-paddingbfpx" >
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width:255px;height:44px;" id="txtPotentialFailure" name="txtPotentialFailure" ></textarea></div>
</td>
<td style="padding-left:10px;">

<div class="easyui-paddingbfpx"   style="padding-top: 10px;"><label>Detection(D)</label><label style="padding-left:98px;">Target Date</label>
		
</div>
<div class="easyui-paddingbfpx" >
		<input type="text" id="txtDetection" name="txtDetection"  maxlength="25" class="easyui-text" style="width:166px; height: 21px;"value=""/>
		<span style="padding-left:-6px;">
			<input type="text" id="dteTargetDate" name="dteTargetDate" maxlength="25" class="easyui-text" style="width:85px; height:21px;" value=""/>
		</span>
</div>
<div class="easyui-paddingbfpx"  style="padding-top:10px;"><label>Potential Effect(s) of Failure</label></div>
<div class="easyui-paddingbfpx" >
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="495" style="width:255px; height:44px;" id="txtPotentialEffects" name="txtPotentialEffects" ></textarea></div>
</td>
<td style="padding-left:10px;">
<div  class="easyui-paddingbfpx"  style="padding-top: 10px;"><label>Responsible</label>
</div>
<div class="easyui-paddingbfpx" >
		<input type="text" id="cmbResp" name="cmbResp" maxlength="25" class="easyui-text" style="width:255px; height:21px;" value=""/>
</div>
<div class="easyui-paddingbfpx"  style="padding-top:10px;"><label>Current Controls</label></div>
<div class="easyui-paddingbfpx" >
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width:255px; height:44px;" maxlength="495" id="txtCurrentControls" name="txtCurrentControls" ></textarea></div>
</td>

</tr>
<tr>
<td  >
<div class="easyui-paddingbfpx"  style="padding-top:10px;"><label>Recommended Action(s)</label></div>
<div class="easyui-paddingbfpx"  >
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width:255px; height:44px;" maxlength="495" id="txtRecommendedAction" name="txtRecommendedAction" ></textarea></div>
</td>
<td style="padding-left:10px;" >
<div class="easyui-paddingbfpx"  style="padding-top:10px;"><label>Action Taken</label></div>
<div class="easyui-paddingbfpx" >
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style="width:255px;height:44px;" maxlength="495" id="txtActionTaken" name="txtActionTaken" ></textarea></div>
</td>
</tr>
</table>
</div>
</div>
</div>
</form>