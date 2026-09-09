<script type="text/javascript">
jQuery(document).ready(function(){
	
	var url = jQuery('#hiddenUrl').val();
    //alert(url);
    var spokeID = url.substring(url.indexOf('SP'),url.indexOf('&trarId'));
    var trarid = url.substring(url.indexOf('TRA'),url.indexOf('&navigateNext'));
    
   
	var mode= getFilterValue(url+'&',"mode");
	
	initialiseForm("frmProgramSkill");
	 //jQuery('#submitForm').val('frmProgramSkill'); // set the id of form to submit
	
	 var keyid=  jQuery('#hdnKeyid').val();//getFieldValue('cmbPrtsProgKeyid','frmProgramSkill');

	 fillComboBox("frmProgramSkill","cmbPrtsProgKeyid","progKey_fillcombo.prgEnt?&keyId="+keyid);
	 setFieldValue('cmbPrtsProgKeyid',keyid);
	 processGridnew("skill_input.prgEnt",'?q=2&Progkeyid='+keyid,"grdSkill","","","skillGrid_dblclick","","loadComplete");
	 disableField('frmProgramSkill','cmbPrtsProgKeyid');
	// disableField("frmProgramSkill","cmbPrtsTopiKeyid");
	 setTimeout(function() {readOnlyFields('cmbPrtsTopiKeyid');},500);
	});

function loadComplete(ids){
	hideJqGridRow('grdSkill', '1');
	 var row = jQuery("#grdSkill").jqGrid('getDataIDs');
	
	var cm = jQuery("#grdSkill").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {	
		 for(var j=11;j<cm.length-1;j++)    {
		 var zeroVal = jQuery("#grdSkill").jqGrid('getCell',row[i],cm[j].name);			 
		  if(zeroVal != ' ' && zeroVal != '' && zeroVal != null)
		  {
			  //alert('j=== '+j+"  name  :"+cm[j].name  + " " + zeroVal);
			  if(zeroVal == '0'){
				  //alert('ss');		
			  		jQuery("#grdSkill").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#ff8040','font-weight':'bold','font-size':'15px','background-color':'#ECE9D8'});
				  }
	 	   }
	 }
	 }
}
function frmProgramSkillcmbPrtsProgKeyid_onLoadSuccess(){
	fillComboBox("frmProgramSkill","cmbPrtsTopiKeyid","prtsSkillKey_fillcombo.prgEnt");
	
    //alert(url);
   
}
function frmProgramSkillcmbPrtsTopiKeyid_onLoadSuccess(){
//	 fillComboBox("frmProgramSkill","cmbPrtsRatingType","prtsRatingType_fillcombo.prgEnt");
	 fillComboBox("frmProgramSkill","cmbPrtsSkilEvaluvationtype","prtsEvalType_fillcombo.prgEnt");
}
function frmProgramSkillcmbPrtsRatingType_onLoadSuccess(){
	fillComboBox("frmProgramSkill","cmbPrtsImpactSkillrate","combo_targetRatting.dfl?&skillZeronotReq=true");
}
function frmProgramSkillcmbPrtsImpactSkillrate_onLoadSuccess(){
	fillComboBox("frmProgramSkill","cmbPrtsSkilEvaluvationtype","prtsEvalType_fillcombo.prgEnt");
}
function frmProgramSkillcmbPrtsSkilEvaluvationtype_onLoadSuccess(){
	 fillComboBox("frmProgramSkill","cmbPrtsSkilDeliverymode","prtsSkillDelvMode_fillcombo.prgEnt");
}
function frmProgramSkillcmbPrtsSkilDeliverymode_onLoadSuccess(){
	fillComboBox("frmProgramSkill","cmbPrtsSpokeKeyid","spokeKeyid_fillcombo.prgEnt");
	processAjaxCalls("getSkillRattings.dfl","","rattingRecallSuccess","rattingRecallError");
}


function frmProgramSkillcmbPrtsSpokeKeyid_onSelect(record){
	var url = jQuery('#hiddenUrl').val();
	var trarid = url.substring(url.indexOf('TRA'),url.indexOf('&navigateNext'));
reloadCombo("frmProgramSkill","cmbPrtsTopiKeyid","topic_fillcombo.tfl?&spokeId="+record.id+"&trarkeyid="+trarid);
enableFields('cmbPrtsTopiKeyid');
}
function rattingRecallSuccess(result) {
	//alert('result');
	// alert(Object.keys(result));
	//alert(result.rattingData.length);	
	var rattingDiv = document.getElementById('ratingDiv');
	var rattingLabel = document.getElementById('ratingLabel');
	rattingDiv.innerHTML ="";
	rattingLabel.innerHTML ="";
	jQuery('#hdnNoofRatting').val(result.rattingData.length);
	for(var i=0; i<result.rattingData.length;i++){
		var rattingNo = result.rattingData[i].orderNo;			
		rattingDiv.innerHTML += ' <span  style="margin-left: 5px;"> <input name="txtRatting_'+rattingNo +'" id="txtRatting_'+rattingNo +'" type="text" class="easyui-text" style="width:38px;" /> </span>';
		rattingLabel.innerHTML += ' <span style="margin-left:38px;"> <label class=""> '+rattingNo +' </label>  </span> '; 
	}
	for(var i=0; i<result.rattingData.length;i++){
		var rattingNo = result.rattingData[i].orderNo;
		numericTextBox('txtRatting_'+rattingNo);
	}
}	
function rattingRecallError() {
	//alert("error");
}
function frmProgramSkillcmbPrtsImpactSkillrate_onSelect(record) {	
	autoEnableRatting();
}
function frmProgramSkillcmbPrtsRatingType_onSelect(record) {		
	autoEnableRatting();
}
function frmProgramSkillcmbPrtsTopiKeyid_onSelect(record) {
	var spokId= jQuery('#cmbPrtsSpokeKeyid').combobox('getValue');
	var row = jQuery("#grdSkill").jqGrid('getDataIDs');
	if(spokId != ' ' && spokId != '' && spokId != null){
		
		 for(var i=0;i<row.length;i++)
		 {	
		  var topiid = jQuery("#grdSkill").jqGrid('getCell',row[i],"TOPI_KEYID");
		  if(record.id == topiid ){
			  var prtsid = jQuery("#grdSkill").jqGrid('getCell',row[i],"PRTS_KEYID");
			  jQuery('#txtPrtsKeyid').val(prtsid);
		  }
		 //alert(topiid +""+record.id);	
		 }
		//jQuery('#txtPrtsKeyid').val(pRTSkeyId);
	processAjaxCalls("getEvalType_input.prgEnt","&topikeyId="+record.id , 'evaltype_successCallBack','evaltype_errorCallBack')	;
	}
	else{
		alert("Select Spoke");	
		clearField('cmbPrtsTopiKeyid');
		disableField("frmProgramSkill","cmbPrtsTopiKeyid");
	}
}
function evaltype_successCallBack(result){

	setFieldValue('cmbPrtsSkilEvaluvationtype',result.evalId,'frmProgramSkill');
}
function chkGreaterThanHundred(){
	var noofRatting = jQuery('#hdnNoofRatting').val();
	var chkRating = '';
	
		for(var i=0; i<noofRatting;i++){				
			chkRating =jQuery('#txtRatting_'+i).val();	
			
				if(parseInt(chkRating )>100){
					alert("Enter value lesser Than 100");
					jQuery('#txtRatting_'+i).css('border-color','red');
					jQuery('#txtRatting_'+i).css('background-color','#F9ACAF');
					//showValidationErrorMsg('txtRatting_'+i,'Enter value lesser Than 100');
					return false;
				}
				/*else if(jQuery('#txtRatting_'+i).css('background-color')=="rgb(255, 255, 255)" ){
					if(chkRating == ' '|| chkRating == '' || chkRating == null){
						alert('Enter Ratting Value');
						return false;
					}
				}*/
		}
		if(  !jQuery('#txtRatting_'+i).is(':disabled') ){
			jQuery('#txtRatting_'+i).css('border-color','#008BC2');
			jQuery('#txtRatting_'+i).css('background-color','#fff');
		}
		//(i+"--"+jQuery('#txtRatting_'+i).is(':disabled'));
		return true;
	}

	
function autoEnableRatting(){
	var ratetype = getFieldValue('cmbPrtsRatingType','frmProgramSkill');
	var impactid =getComboBoxText('cmbPrtsImpactSkillrate');
	
	var noofRatting = jQuery('#hdnNoofRatting').val();
	var specific = jQuery('#hdnImpactId').val();
	
	for(var i=0; i<noofRatting;i++){				
		jQuery('#txtRatting_'+i).val("");			
		enableFields("txtRatting_"+i);					
	}
	if (ratetype !="A") {					
		for(var i=0; i<noofRatting;i++){
			if (i==impactid)
				enableFields("txtRatting_"+i);	
			else 					
				readOnlyFields('txtRatting_'+i);					
		}
	} 
	else{
		for(var i=0; i<noofRatting;i++){
			if (i<=impactid)
				enableFields("txtRatting_"+i);	
			else 					
				readOnlyFields('txtRatting_'+i);					
		}
	}
}
function skillGrid_dblclick(rowId){
	enableFields('cmbPrtsTopiKeyid');
	var rowData = jQuery("#grdSkill").jqGrid('getRowData',rowId);
	var keyId = rowData.PRTS_KEYID;
	var skillkey = rowData.TOPI_KEYID;
	var spokeKeyId= rowData.Spokeid;
	var evaluationtypekey =rowData.EVALUATIONTYPEID;
	var delivModeKey =rowData.DELIVERYMODE;
	var targetKey = rowData.TargetKeyid;
	var impactkey = rowData.Impactkeyid;
	//var pimsKey = rowData.PIMS_KEYID;
	setFieldValue('cmbPrtsSpokeKeyid',spokeKeyId,'frmProgramSkill');
	setFieldValue('cmbPrtsTopiKeyid',skillkey,'frmProgramSkill');
	setFieldValue('cmbPrtsSkilEvaluvationtype',evaluationtypekey,'frmProgramSkill');
	setFieldValue('cmbPrtsSkilDeliverymode',delivModeKey,'frmProgramSkill');
	setFieldValue('txtPrtsKeyid',keyId,'frmProgramSkill');
	setFieldValue('cmbPrtsImpactSkillrate',impactkey,'frmProgramSkill');
	setFieldValue('cmbPrtsRatingType',targetKey,'frmProgramSkill');
	//setFieldValue('txtPimsKeyid',pimsKey,'frmProgramSkill');
	/**fill 0 1 2 3 etc*/
	var noofRatting = jQuery('#hdnNoofRatting').val();

	var countCols = jQuery('#grdSkill').jqGrid('getGridParam', 'colModel').length;
	var colIds  = jQuery('#grdSkill').jqGrid('getGridParam', 'colModel');
	
	for(var i=0; i<noofRatting;i++){
		for (var j=11;j<= countCols-2;j++)  {
			var tarId = jQuery("#grdSkill").jqGrid('getCell',1,j);				
			if (tarId==i) {	
			 //var zeroVal = jQuery("#grdSkill").jqGrid('getCell',i,colIds[j].name);
			 var zeroVal = jQuery("#grdSkill").jqGrid('getCell',rowId,j);
			 //alert(zeroVal);
				  if(zeroVal != ' ' && zeroVal != '' && zeroVal != null)
				  {
						 setFieldValue("txtRatting_"+i,zeroVal,'frmProgramSkill');
						 enableFields("txtRatting_"+i);
				  } 		  
				  else {
						  setFieldValue("txtRatting_"+i,"",'frmProgramSkill');
						  readOnlyFields('txtRatting_'+i);
				  }
			}							
		}
	}
	/*END*/
	//processAjaxCalls("skill_input.prgEnt","&skillkeyId="+keyId , 'skillIn_successCallBack','skillIn_errorCallBack')	;
}

jQuery('#btnAddSkill').click(function(){
	
	if(chkGreaterThanHundred()){
		saveForm('frmProgramSkill','skillPop_save.prgEnt');
		}
	//
});
jQuery('#btnClrSkill').click(function(){
	clearField('cmbPrtsTopiKeyid');
	clearField('cmbPrtsSkilEvaluvationtype');
	clearField('cmbPrtsSkilDeliverymode');
	clearField('txtPrtsKeyid');
	clearField('cmbPrtsImpactSkillrate');
	clearField('cmbPrtsRatingType');
	clearField('cmbPrtsSpokeKeyid');
	var noofRatting = jQuery('#hdnNoofRatting').val();
	for(var i=0; i<noofRatting;i++){
		
			clearField("txtRatting_"+i);	
							
	}
	clearField('');
	disableField("frmProgramSkill","cmbPrtsTopiKeyid");
});

function frmProgramSkill_beforeSubmit(){
	//closePopUpDialoge("loadSkill");
}
function BtnFormatterOperator(id, options, rowObject)
{					
	var rowId = options.rowId;
   
	return '<input type="button" id="remov" class="grdButton" value="" onclick="removeOperator(\''+rowId + '\');"/>';
}
function removeOperator(rowId){	
	var rowData = jQuery("#grdSkill").jqGrid('getRowData',rowId);
	var keyId =rowData.PRTS_KEYID;
	var conFdelete = confirm("Do You Want To Delete");
	if(conFdelete){
		if( keyId != null && keyId.length > 0  ){	
			processAjaxCalls("skill_delete.prgEnt","&skillkeyId="+keyId , 'topic_successCallBack','topic_errorCallBack')	;
			jQuery("#grdSkill").delRowData(rowId);
		}
	}
}
function topic_successCallBack(result){
	alert(result.tpmException);
	//alert("Deleted Successfully");
}
function topic_errorCallBack(result){
	//alert('error');
	
	
}
function frmProgramSkill_beforeSubmit() {	
	var noofRatting = jQuery('#hdnNoofRatting').val();		
	var rattingNumbers="";
	for(var i=0; i<noofRatting;i++){
		var ratNo = jQuery('#txtRatting_'+i).val();						
		if (ratNo!= "" && ratNo!= " " && ratNo!= null)
			rattingNumbers += i + ":" + ratNo + ","; 
	}
	rattingNumbers = rattingNumbers.substring(0, rattingNumbers.length-1);	
	return "cmbrattingNumbers="+rattingNumbers;
}
function frmProgramSkill_successsCallback(result){
	clearField('cmbPrtsTopiKeyid');
	clearField('cmbPrtsSkilEvaluvationtype');
	clearField('cmbPrtsSkilDeliverymode');
	clearField('txtPrtsKeyid');
jQuery("#grdSkill").trigger("reloadGrid") ;
jQuery("#grdProgSkill").trigger("reloadGrid") ;

}
function frmProgramSkill_exceptionCallback(){
	jQuery(".main-cntborder ").css("height","380px");
	jQuery("#loadSkill").css("height","400px");
	jQuery("#loadSkill").css("top","-2");
}
</script>
<form id='frmProgramSkill' name="frmProgramSkill">
	<div id="" style="margin-top:1%;">
<!--		<div class="sub-header" style="width:100%">Programs</div>-->
		<div class="main-cntborder">
				<table width="100%" style="margin-left: 2%; width :0px; height : 224px;" align="center" cellpadding="5px;">
					<tr>
						<td valign="top"  colspan=""  width="44%" style=" "> 
							<div><label>Program</label></div>
							<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:266px;" id="cmbPrtsProgKeyid" name="cmbPrtsProgKeyid" value="${requestScope.entTlProgTargetSkills.prtsProgKeyid }"/></div>
							<div><label class="mandatory-lbl">Spoke</label></div>
							<div class="easyui-paddingbfpx" style="">
							<input  class="easyui-combobox" style="width:266px;" id="cmbPrtsSpokeKeyid" name="cmbPrtsSpokeKeyid" value="${requestScope.entTlProgTargetSkills.prtsSpokeKeyid}"/>
							</div>
						</td>
						<td valign="top" style=" width : 425px;">
							<div><label class="mandatory-lbl">Evaluation Type</label></div>
							<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:266px;" id="cmbPrtsSkilEvaluvationtype" name="cmbPrtsSkilEvaluvationtype" value="${requestScope.entTlProgTargetSkills.prtsSkilEvaluvationtype}"/></div>
							<div><label  class="mandatory-lbl">Delivery Mode</label></div>
							<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:266px;" id="cmbPrtsSkilDeliverymode" name="cmbPrtsSkilDeliverymode" value="${requestScope.entTlProgTargetSkills.prtsSkilDeliverymode}"/></div>
							<input class="" type="hidden" style="width:266px;" id="txtPrtsKeyid" name="txtPrtsKeyid" value=""/>
							<input class="" type="hidden" style="width:266px;" id="txtPimsKeyid" name="txtPimsKeyid" value=""/>
							
						</td>
						
						
					</tr>
					<tr>
						<td  valign="top">
							<div><label  class="mandatory-lbl">Topic</label></div>
							<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:266px;" id="cmbPrtsTopiKeyid" name="cmbPrtsTopiKeyid" value="${requestScope.entTlProgTargetSkills.prtsSkilKeyid }"/></div>
							
							
						</td>
						<td colspan="" width="">
						<span style="margin-left:160px;" class="easyui-paddingbfpx">
						<input class="easyui-button" style=" width : 50px;height: 20px;" id="btnAddSkill" name="btnAddSkill" value="Add"/>
						<input class="easyui-button" style=" width : 50px;height: 20px;" id="btnClrSkill" name="btnClrSkill" value="Clear"/>
						</span>
							<!--<table>
								<tr>
									<td valign="baseline">
										<div><label  class="">Impact Topic</label></div>
										<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:66px;" id="cmbPrtsImpactSkillrate" name="cmbPrtsImpactSkillrate" value="${requestScope.entTlProgTargetSkills.prtsImpactSkillrate }"/></div>
									</td>
									<td valign="baseline" >
										<div><label  class="" style="padding-left:5px;">Rating Type</label><span id="ratingLabel" style="vertical-align: text-top;"></span></div>
										<div style="" class="easyui-paddingbfpx"><input class="easyui-combobox" style="width:77px;" id="cmbPrtsRatingType" name="cmbPrtsRatingType" value="${requestScope.entTlProgTargetSkills.prtsRatingType }"/>
										<span id="ratingDiv" style="vertical-align: text-top;"></span>
										<span>
										<input type="hidden" id="hdnNoofRatting" name="hdnNoofRatting">
										</span>
										
										<span  width="" style="margin-top:15">
										<span style="margin-left:10px;float: right;" class="easyui-paddingbfpx">
										<input class="easyui-button" style=" width : 38px;" id="btnAddSkill" name="btnAddSkill" value="Add"/>
										<input class="easyui-button" style=" width : 44px;" id="btnClrSkill" name="btnClrSkill" value="Clear"/>
										</span>
										</span>
										</div>
										<span id="err_cmbPrtsRatingType" class="tpm-errormsg" style="" ></span>
									</td>
									
								</tr>
							</table>
						
						
									--></td>
					</tr>
					<tr>
						<td colspan="2" style="">
							<div>
								<table id="grdSkill"><tr><td></td></tr></table>
								<div id="skillPager"></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
	<div class="grdGraphBtnPos">
		
	</div>
	
	
</div>
<input type="hidden" id="hdnKeyid" name="hdnKeyid" value="${requestScope.progKeyId }"/>
<input type="hidden" id="hdnImpactId" name="hdnImpactId" value=""/>

</form>