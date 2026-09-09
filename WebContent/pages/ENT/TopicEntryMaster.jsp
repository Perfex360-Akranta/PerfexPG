
<script type="text/javascript">
jQuery(document).ready(function(){	
	 // set the id of form to submit
	 //alert("ready");
	initialiseForm('frmTopicPopup');
	 
	jQuery('#submitForm').val('frmTopicPopup');	

	jQuery('#frmTopicPopup .easyui-text').css('text-transform', 'uppercase');	
	jQuery('#frmTopicPopup textarea').css('text-transform', 'uppercase');	
    fillComboBox("frmTopicPopup","cmbTopiTrainingmode","deliveryMode_Combo.topi","",false); 
	//fillComboBox("frmTopicPopup","cmbTopiLocationid","combo_location.topi");
	fillComboBox("frmTopicPopup","cmbTopiSpokeid","Spoke.commonFilter");
	//fillComboBox("frmTopicPopup","cmbTopics","topic_fillcombo.tfl");
	fillComboBox("frmTopicPopup","cmbTopics","combo_Topic.commonFilter");
	fillComboBox("frmTopicPopup","cmbTopiType","ksa_combo.tmks"); 
	//fillComboBox("frmTopicPopup","cmbTopiTrainingmode","deliveryMode_Combo.topmst","",false); 
	//fillComboBox("frmTopicPopup","cmbRoleKeyid","roleMst.commonFilter");			
	fillComboBox("frmTopicPopup","cmbTopiEvaluationtypeid","combo_EvaluationType.topi");
//	fillComboBox("frmTopicPopup","cmbTopiCategory","combo_Category.topi");
	var topiKeyid=jQuery('#txtTopiKeyid').val();
	
	var trainAreaParentId=jQuery('#txtTrainAreaParentId').val();

	processGridnew("TopicEntryMaster_input.topmst","?q=2&TopicKeyid="+topiKeyid,"TopicList","pager","","docDoubleClick");

	var locnId = jQuery("#frmEmpPage input[id='location']").val();
	var sectionId = jQuery("#frmTopicPopup input[id='section']").val();
	var cellId = jQuery("#frmTopicPopup input[id='cell']").val();
	var machId = jQuery("#frmTopicPopup input[id='machine']").val();	
	var flId = jQuery("#frmTopicPopup input[id='flid']").val();		
	//var dataStr = "&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flId;
	var dataStr = "&locnId="+locnId;
	loadFunctionalLocation("topicfunLocation","functionalLocTopicMst.topi","topicfunLocationValues","frmTopicPopup",dataStr);
	
	if(topiKeyid=='' || topiKeyid==null || topiKeyid=='null' || topiKeyid==' ' || topiKeyid=='undefined'){
		//disableUIButton('btntopicFilManage');
	}
	else{
		disableField('frmTopicPopup','cmbTopiParentid');
	}
		
	if(trainAreaParentId!='' && trainAreaParentId!=null && trainAreaParentId!='null' && trainAreaParentId!=' ' && trainAreaParentId!='undefined')
		disableField('frmTopicPopup','cmbTopiParentid');
		
	jQuery("#btntopicFilManage").click(function(){
		/*var url="topi_save.topi?trAreaParentId?q=2";	
		var trParentId=jQuery('#txtTrainAreaParentId').val();
		if(trParentId!=null && trParentId !='null' && trParentId!='' && trParentId!=' ')
			url=url + "&trParentId=" + trParentId;

		url+= '&openFileManager=Y';
		saveForm("frmTopicPopup",url);	
		/*var documentNo =getFieldValue('txtTopiKeyid','frmTopicPopup');
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"TOP");
		}
		else{
 			alert("Topic should be selected to view FileManager");
		}*/
	});
	fileManagerPopUp(topiKeyid,"TOP","frmTopicPopup","btntopicFilManage","topicFileMgr");
	var displayCode = jQuery('#hdnDislaycode').val();
	if(displayCode == 'True')
		jQuery('#topicCodeDiv').css('display','none');
	else
		jQuery('#topicCodeDiv').css('display','block');
});

function frmTopicPopup_FuntLocHierarchy_SuccessCallBack(result){
	//var flid = result.flid;
	//jQuery("#frmTopicPopup div[id=dispFunctionalLoc]").css('width','96%');
	setFunctionalLocWidth('frmTopicPopup','520px');
}

jQuery("#btndlgSaveTop").click( function()
{	
	var url="topi_save.topmst?trRoleid?q=2";	
	var trRoleid=getFieldValue('cmbRoleKeyid');
	//alert("trRoleid   " +trRoleid);
	//var trParentId=jQuery('#txtTrainAreaParentId').val();
	/*if(trParentId!=null && trParentId !='null' && trParentId!='' && trParentId!=' ')
		url=url + "&trParentId=" + trParentId;*/
		if(trRoleid!=null && trRoleid !='null' && trRoleid!='' && trRoleid!=' ')
			url=url + "&trRoleid=" + trRoleid;
	    saveForm("frmTopicPopup",url);	
});

jQuery('#btndlgCloseTop').click( function()
{	
	closePopUpDialoge("divShowTrAreaPopup");	
	jQuery("#traingingAreaGrid").trigger("reloadGrid");	
});	

jQuery('#btndlgDeleteTop').click( function()
		{	
	        removeRecord();	
			jQuery("#TopicList").trigger("reloadGrid");	
});	



/*jQuery(document).keydown(function(e) {
    if (e.keyCode == 27) {
    	jQuery("#newMstFrm").hide(0);
    }    
    jQuery('#newMstFrm').focusout(function() { 
 });
});
*/

function frmTopicPopupcmbTopics_onSelect(record){
	//alert(record.id);
	jQuery('#txtTopiKeyid').val(record.id);
	processAjaxCalls("TopicRecall_input.topmst?keyId="+record.id,"","selectedReport_onsuccesscallback");	
	} 
	function docDoubleClick(id)
	{
		var rowData = jQuery("#TopicList").jqGrid('getRowData',id);
		var keyid = rowData.topicKeyid;
		processAjaxCalls("TopicRecall_input.topmst?keyId="+keyid,"","selectedReport_onsuccesscallback");
	}
function selectedReport_onsuccesscallback(result){
	setFieldValue('cmbTopics',result[0][0]);
	jQuery("#txtTopiKeyid").val(result[0][0]);
	jQuery("#txtTopiName").val(result[0][3]);
	//jQuery("#cmbTopiEvaluationtypeid").combobox('setValue',result[0][6]);
	//jQuery("#cmbTopiType").combobox('setValue',result[0][7]);
	 setFieldValue('cmbTopiType',result[0][7],'frmTopicPopup');
	 
	setFieldValue('cmbTopiEvaluationtypeid',result[0][6],'frmTopicPopup');	
	jQuery("#txtTopiRemarks").val(result[0][8]);	
	//jQuery("#cmbTopiSpokeid").combobox('setValue',result[0][11]);
	setFieldValue('cmbTopiSpokeid',result[0][11],'frmTopicPopup');
	setFieldValue('cmbTopiTrainingmode',result[0][13],'frmTopicPopup');
	
	var flId=result[0][1];	
	//alert("flId"+flId);
	if(flId.trim().length>0){
		//var factId = jQuery("#frmTopicPopup input[id='factory']").val();
		var sectionId = jQuery("#frmTopicPopup input[id='section']").val();
		var cellId = jQuery("#frmTopicPopup input[id='cell']").val();
		var machId = jQuery("#frmTopicPopup input[id='machine']").val();	
		var dataStr = "&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flId;
		//alert(dataStr);	
		loadFunctionalLocation("topicfunLocation","functionalLoc.topmst","topicfunLocationValues","frmTopicPopup",dataStr);
	}

}
function txtTopicLinkDetDeleteformatter(id, options, rowObject)
{
	var rowId = options.rowId;
	var colId = options.pos;
	return '<input type="checkbox" id="checkbox_'+rowId+'_'+colId+'" name="checkbox_'+rowId+'_'+colId+'"  style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxCheck1(\''+rowId + '\');}else{chkboxUnCheck1(\''+ rowId +'\');}" />';
}


function chkboxCheck1(rowId) {

	jQuery("#TopicList").jqGrid('setCell', rowId, 'checkval', '1');
}

function chkboxUnCheck1(rowId) {

	jQuery("#TopicList").jqGrid('setCell', rowId, 'checkval', '0');
}

function removeRecord(keyid) {
	var momrow = jQuery("#TopicList").jqGrid('getDataIDs');//	row get data
	var rowid = "";
	var r = confirm("Do You Want To Delete?");

	for (i = 0; i < momrow.length; i++) {
		rowid = momrow[i];
		var CHEKVal = jQuery("#TopicList").jqGrid('getCell', momrow[i],"checkval");
		if (CHEKVal == '1') {
			keyid = jQuery("#TopicList").jqGrid('getCell', momrow[i], "Keyid");
			if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "") {
				if (r == true) {
					processAjaxCalls("TopicListDetl_remove.topmst", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
				} else
					return false;

			} else {

				var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#TopicList").trigger("reloadGrid");
				else
					return false;
			}

		}

	}
}
function remove_successCallBack(result) {
	jQuery("#TopicList").trigger("reloadGrid");
}
function remove_errorCallBack() {
}


function btntopicFilManage_click(){
	var url="topi_save.topmst?trRoleid?q=2";	
	var trParentId=jQuery('#txtTrainAreaParentId').val();
	var trRoleid=jQuery('#hdnRolemstkeyid').val();
	//alert("trRoleid  " +trRoleid);
	if(trRoleid!=null && trRoleid !='null' && trRoleid!='' && trRoleid!=' ')
		url=url + "&trRoleid=" + trRoleid;

	url+= '&openFileManager=Y';
	saveForm("frmTopicPopup",url);	
	/*var documentNo =getFieldValue('txtTopiKeyid','frmTopicPopup');
	if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"TOP");
	}
	else{
			alert("Topic should be selected to view FileManager");
	}*/
}
function frmTopicPopupcmbTopiLocationid_onSelect(record) 	{
	var locnId = record.id;	
}

function frmTopicPopup_beforeSubmit(){}

function resetControls(){
	jQuery("#txtTopiName").val(""); 
	jQuery("#txtTopiCode").val(""); 
	setFieldValue('cmbTopiType','');
	setFieldValue('cmbTopiEvaluationtypeid','');	
	setFieldValue('cmbTopiLocationid','');
	setFieldValue('cmbTopiParentid','');
	setFieldValue('cmbTopiTrainingmode','');
	jQuery("#txtTopiRemarks").val("");
}

function frmTopicPopup_successsCallback(args){
	var openFileMgr = args.openFileMgr;
	if(openFileMgr != null && openFileMgr != '' && openFileMgr != ' ')
	{
		var documentNo =args.keyId;
	 
		if(documentNo != null && documentNo != ''){
			jQuery('#txtTopiKeyid').val(documentNo);
			jQuery('#frmTopicPopup input[id=mode]').val('MODIFY');
			fileManagerPopUp(documentNo,"TOP","","","");
			
		}
		else{
 			alert("Topic should be selected to view FileManager");
		}
	}
	else{
		resetControls();
	//alert("suc");	
		closePopUpDialoge("divShowTrAreaPopup");
		if(jQuery("#trAreaTreeComponent").jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
		{
			var isLeaf = jQuery("#trAreaTreeComponent").jstree("is_leaf", jQuery('#'+jQuery('#hdnMstId').val()));
			if(isLeaf == true){			
				jQuery("#trAreaTreeComponent").jstree("load_node",jQuery('#'+jQuery('#hdnMstId').val()));
				setTimeout(function() {jQuery("#trAreaTreeComponent").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));},1250);
			}
			else{	
				//jQuery("#trAreaTreeComponent").jstree("refresh",jQuery('#'+jQuery('#hdnMstId').val()));
				jQuery("#trAreaTreeComponent").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));
			}  
		}
		else
		  refreshNode("trAreaTreeComponent",jQuery('#hdnMstId').val());	
	}	
}
function frmTopicPopup_successsCallback(result) {	
	jQuery("#TopicList").trigger("reloadGrid");           
}
function frmTopicPopup_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	clearForm('frmTopicPopup');
	jQuery("#TopicList").trigger("reloadGrid");  
}
function frmTopicPopupcmbTopiParentid_onLoadSuccess(){}
function frmTopicPopupcmbTopiLocationid_onLoadSuccess(){}
function frmTopicPopupcmbTopiEvaluationtypeid_onLoadSuccess(){}
function frmTopicPopupcmbTopiType_onLoadSuccess(){}
function frmTopicPopupcmbTopiLocationid_onSelect(){}
function frmTopicPopupcmbTopiType_onSelect(){}
function frmTopicPopupcmbTopiEvaluationtypeid_onSelect(){}
jQuery('#cmbTopiType').val(jQuery('#hdnTopiType').val());

</script>
<form id="frmTopicPopup" name="frmTopicPopup">
	<div id="wrapper">					     	
		<div style="padding-top:0px;">
			<table width="90%" align="Center">
				<tr>
					<td>
						<div style="margin-left:-30px;" >
							<table id="TopicList"><tr><td></td></tr></table>
							<div id="pager"></div>
							<div id="paramDiv" style="display:none;" title="param"></div>
						</div>
					</td>
					<td>
						<table width="100%" align="Center">
						   	<tr>
							   	<td  valign="top">
							   	 <div style="margin-left:20px;">
							   		<div  id="frmTopicPopupFuntKeyIds">
<!--									<input type="hidden" id="factory" name="cmbFactoryid" value=" "  ></input>			-->
										<input type="hidden" id="location" name="cmbLocation" value="${requestScope.locnId}"  ></input>
										<input type="hidden" id="section" name="cmbSectionid" value=" "  ></input>
										<input type="hidden" id="cell" name="cmbCellid" value=" "  ></input>
										<input type="hidden" id="machine" name="cmbEquipmentid" value=" "  ></input>
										<input type="hidden" id="flid" name="cmbTopiLocationid" value="${requestScope.entTlTopicmst.topiLocationid}"></input>	
									</div>
								 	<div id="topicfunLocation" style="width:85%;margin-top: 17px;"></div>
									<div>
				                        <label> Topic </label>                       
				                    </div> 
				                    <div class="easyui-paddingbfpx"> 
					                        <input id="cmbTopics" name="cmbTopics" class="easyui-combobox"  style="width:350px"  value=""  value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"  />                       
					                    </div>	
									<div  class="easyui-paddingbfpx" style="padding-top:6px">
				                        <label class="mandatory-lbl"> Topic Name</label>                       
				                    </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="txtTopiName" name="txtTopiName" value="${requestScope.entTlTopicmst.topiName}" style="width:350px" maxlength="100" class="easyui-text"  value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>                   
				                    </div> 
				                    <table>
				                     <tr>
			                          <td><span id="err_txtTopiName" class="tpm-errormsg"></span></td>
			                          </tr>
				                    </table>
				                    <div  class="easyui-paddingbfpx">
					                    <label class="mandatory-lbl">KSA</label>                    
					                    <span style='margin-left:100px;'><label  class="mandatory-lbl">Type</label></span>
					                </div>	  
					                             
					                <div class="easyui-paddingbfpx">
 					              
 					              <!-- 
 					                <input id="cmbTopiType" name="cmbTopiType" class="easyui-combobox"  style="width:110px"  value=" " />					               
 				                  -->
 				                    <select id="cmbTopiType"   name="cmbTopiType" class="easyui-combobox" style="width:160px;" required="true"   value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}">
										<option value="S">Skill</option>
										<option value="K">Knowledge</option>
										<option value="A">Attitude </option>																	
									</select>  
									
									
									<span style='margin-left:20px;'>
										<select id="cboTopiRelatedto" class="easyui-combobox" name="cboTopiRelatedto" style="width:160px;" required="true"  value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}">
										<option value=" "> </option>
										<option value="EHS">Safety</option>
										<option value="GT">Technical</option>
										<option value="B">Behavioral</option>										
									</select> 
									</span>
									<input type="hidden" id="hdnTopiType" name="hdnTopiType" value="${requestScope.entTlTopicmst.topiType}"/>
										<!-- <input id="cmbTopiType" name="cmbTopiType" class="easyui-combobox"  style="width:350px"  value="${requestScope.entTlTopicmst.topiType}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/> >-->
				                      
				                    </div>
				                   <table>
					                     <tr>
					                          <td><span id="err_cmbTopiType" class="tpm-errormsg"></span></td>
					                          <td style="padding-left:160px;"><span id="err_cmbRoleKeyid" class="tpm-errormsg"></span></td>
					                     </tr>
			                      </table>
				                     
					             <div style="padding-top:0px;">				               			
						                <div  class="easyui-paddingbfpx">
						                    <label  class="mandatory-lbl">Topic Evaluation Type</label>                       
						                </div>	                
						                <div class="easyui-paddingbfpx"> 
					                        <input id="cmbTopiEvaluationtypeid" name="cmbTopiEvaluationtypeid" class="easyui-combobox"  style="width:350px"  value="${requestScope.entTlTopicmst.topiEvaluationtypeid}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/> >                       
					                    </div>
					                    
					                    												
					                    <div style="padding-top:0px;">				               			
						              <%--   <div  class="easyui-paddingbfpx">						
						                <label  class="mandatory-lbl">Topic Category</label>                       						
						                </div>	                					
						                <div class="easyui-paddingbfpx"> 						
					                        <input id="cmbTopiCategory" name="cmbTopiCategory" class="easyui-combobox"  style="width:350px"  value="${requestScope.entTlTopicmst.topiCategory}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/> >                       							
					                    </div>							
					                     --%>

					                      <table>
				                             <tr>
					                          <td><span id="err_cmbTopiSpokeid" class="tpm-errormsg"></span></td>
					                          </tr>
				                         </table>
				                        <div  class="easyui-paddingbfpx">
						                    <label  class="mandatory-lbl">Training mode</label> 
						                </div>                                                                   
						                <div class="easyui-paddingbfpx">
						                <input id="cmbTopiTrainingmode" name="cmbTopiTrainingmode" class="easyui-combobox"  style="width:350px"  value="${requestScope.entTlTopicmst.TopiTrainingmode}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/> >                   
						                </div>
					                    <div  class="easyui-paddingbfpx">
						                    <label >Remarks</label> 
						                </div> 
						                <div class="easyui-paddingbfpx">
						                    <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtTopiRemarks" name="txtTopiRemarks" style="resize:none;width:350px;height:70px" <c:out value = "${requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>>${requestScope.entTlTopicmst.topiRemarks}</textarea>                   
						                </div>
					               	</div>
                                 </div>
				               </td>
						   	</tr>
			            </table>
					</td>
				</tr>
			</table>
		</div> 
		<div style="float:center;padding-top:10px;padding-left:10px;">	
			<!--<table width="100%">
				<tr>
					 <td align="center">
						<input type="button" class="easyui-button" id="btndlgSaveTop" value="Save" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>
						<input type="button" class="easyui-button" id="btndlgCloseTop" value="Close"/>
						<input type="button" class="easyui-button" id="btndlgDeleteTop" value="Delete"/>																	
					</td> --!>
				<!--	<td>
					<span id='topicFileMgr' style="float:right;z-index:110;margin-top:-3">
				  		<input class="easyui-button" id="btntopicFilManage" type="button" value="File Manager" style=" height : 21px;" >
				 	</span> 
					</td> 
				</tr>
			</table> -->
			<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/> 
			<input type="hidden" id="txtTopiKeyid" name="txtTopiKeyid" value="${requestScope.entTlTopicmst.topiKeyid}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>
			<input type="hidden" id="txtTopicIschild" name="txtTopicIschild" value="${requestScope.entTlTopicmst.topiIschild}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>
			<input type="hidden" id="txtTrainAreaParentId" name="txtTrainAreaParentId" value="${requestScope.trainAreaParentId}" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>
			<input type="hidden" id="hdnDislaycode" name="hdnDislaycode" value="${requestScope.displayEntCode }">
			<input type="hidden" id="hdnRolemstkeyid" name="hdnRolemstkeyid" value="${requestScope.Rolemstkeyid}">
			<input type="hidden" id="hdnLocnId" name="hdnLocnId" value="${requestScope.locnId}" ></input>
		</div>
	</div>		
</form>

