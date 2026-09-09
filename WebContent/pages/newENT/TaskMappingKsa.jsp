<script type="text/javascript">
jQuery(document).ready(function(){	 
	initialiseForm('frmTaskMappingKsa');
	jQuery('#submitForm').val('frmTaskMappingKsa');
	fillComboBox("frmTaskMappingKsa","cmbTmkmRoleKeyid","roleMst.commonFilter");	
	fillComboBox("frmTaskMappingKsa","cmbTmkdSkrmKeyid","skillRating.commonFilter");
	fillComboBox("frmTaskMappingKsa","cmbTmkdSpokeKeyid","Spoke.commonFilter");
	fillComboBox("frmTaskMappingKsa","cbmTmkdKsa","ksa_combo.tmks","",false);
		
	var factId = jQuery("#frmTaskMappingKsa input[id='factory']").val();
    var sectionId = jQuery("#frmTaskMappingKsa input[id='section']").val();
	var cellId = jQuery("#frmTaskMappingKsa input[id='cell']").val();
	var machId = jQuery("#frmTaskMappingKsa input[id='machine']").val();
	var flid = jQuery("#frmTaskMappingKsa input[id='flid']").val(); 
   
	var dataStr = "&factId=" + factId
			+ "&sectionId=" + sectionId
			+ "&cellId=" + cellId + "&machId="
			+ machId+"&flid="+ flid;
	 // alert(dataStr);
	  loadFunctionalLocation("TaskKsafunLocation", "functionalLoc.tmks", "TaskKsafunLocation", "frmTaskMappingKsa",dataStr);
      var keyid=jQuery('#hdnTmkmKeyid').val();
     // alert("keyid "+keyid);
      if(keyid!=null && keyid!=" " && keyid!=""){			
			disableField('frmTaskMappingKsa', 'cmbTmkmRoleKeyid');
		}	
	  viewGrid('q=2&mstkeyid='+keyid);
	
	  //jQuery('#frmTaskMappingKsa .easyui-text').css('text-transform', 'uppercase');
	   //jQuery('#frmTaskMappingKsa textarea').css('text-transform', 'uppercase');
});
jQuery("#btndlgSaveTop").click( function() {	
	 
     var Task=getFieldValue('txtTmkdTask');
     saveForm("frmTaskMappingKsa","TaskMappingMst_save.tmks?&type=type");	   			       
});
jQuery('#btndlgDeleteRp').click( function(){	
    var count=0;
    var momrow = jQuery("#TaskKSAGrid").jqGrid('getDataIDs');  
    //removeRecord();
  for(i=0;i<momrow.length;i++)
    {                      
     var CHEKVal = jQuery("#TaskKSAGrid").jqGrid('getCell',momrow[i],"CheckVal");                   
     if(CHEKVal =='1')
        {
    	 count++;
        }	              		          		                                    
    }
 if(count=='0')
     { 
       alert("Select Task"); 
     }                     
 else{  removeRecord();}
});	

	  
function viewGrid(filter){
	 processGridnew("TaskKsaDetails_input.tmks",filter,"TaskKSAGrid","pager","","doubleclick","","loadComplte");
}
function frmTaskMappingKsa_FuntLocHierarchy_SuccessCallBack(keyIds)
{	  
	  var selFlid = keyIds.flId;
	  reloadCombo("frmTaskMappingKsa","cmbTmkmRoleKeyid","roleMst.commonFilter?&flid="+selFlid);
}
function doubleclick(id){
	var rowData = jQuery("#TaskKSAGrid").jqGrid('getRowData',id);
	var keyid = rowData.Keyid;	
    var task = rowData.TASK;
    var ksa = rowData.KSAID;
    var ksades  = rowData.KSADIC;
    var basicelm = rowData.BASIC;
    var EXPECTED= rowData.EXPECTED;
    var SPOKE = rowData.CRITERIAKEYID;
    //var ksa= rowData.KSA;
   
    
    setFieldValue('txtTmkdTask',task);
    setFieldValue('txtTmkdBasicemnt',basicelm);
    setFieldValue('txtTmkdKsadesc',ksades);
    
    setFieldValue('hdnTmkdKeyid',keyid);
    readOnlyFields('cmbTmkmRoleKeyid');
    
    setFieldValue('cmbTmkdSkrmKeyid',EXPECTED,'frmTaskMappingKsa');
    setFieldValue('cmbTmkdSpokeKeyid',SPOKE,'frmTaskMappingKsa');
    setFieldValue('cbmTmkdKsa',ksa,'frmTaskMappingKsa');
    
    //jQuery("#cbmTmkdKsa").combobox("setValue",ksa);
}
/*function formatterCheckbox(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return '<input type="checkbox" id="checkbox_'+rowId+'_'+colId+'" name="checkbox_'+rowId+'_'+colId+'"  style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxCheck1(\''+rowId + '\');}else{chkboxUnCheck1(\''+ rowId +'\');}" />';
}
*/


function TaskKSAGrid_selectAll(id,status){
	for(var i=0; i<id.length; i++){
		if(status)
			 jQuery("#TaskKSAGrid").jqGrid('setCell', id[i], 'CheckVal', '1');
		else
			 jQuery("#TaskKSAGrid").jqGrid('setCell', id[i], 'CheckVal', '0');
	}
}
function TaskKSAGrid_selectRow(id){
	if(jQuery('#jqg_TaskKSAGrid_'+id).is(':checked'))
		jQuery("#TaskKSAGrid").jqGrid('setCell', id, 'CheckVal', '1');		
	else
		jQuery("#TaskKSAGrid").jqGrid('setCell', id, 'CheckVal', '0');
      
}
function chkboxCheck(rowId) {
	
	jQuery("#TaskKSAGrid").jqGrid('setCell', rowId, 'CheckVal', '1');
}

function chkboxUnCheck(rowId) {
	jQuery("#TaskKSAGrid").jqGrid('setCell', rowId, 'CheckVal', '0');
}
function removeRecord(keyid) {
	var taskrow = jQuery("#TaskKSAGrid").jqGrid('getDataIDs');//	row get data
	var rowid = "";
	var keyid="";
	var task=0;
	var r = confirm("Do You Want To Delete?");
	for (i = 0; i < taskrow.length; i++) {
		rowid = taskrow[i];
		var CHEKVal = jQuery("#TaskKSAGrid").jqGrid('getCell', taskrow[i],"CheckVal");
		//alert("checkval::"+CHEKVal);
		if (CHEKVal == '1') {
			keyid += jQuery("#TaskKSAGrid").jqGrid('getCell', taskrow[i], "Keyid")+",";			
			if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "") {
				if (r == true) {
					task++;
					  
					} else
					return false;
			} else {
				var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#TaskKSAGrid").trigger("reloadGrid");
				else
					return false;
			}
		}
	}
	if(parseInt(task)>0)	
		{	
			processAjaxCalls("TaskMappingList_remove.tmks", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
		}
jQuery("#hdnCount").val("0");
}
function remove_successCallBack(result){
	clearField('cmbTmkdSpokeKeyid');
	clearField('txtTmkdTask');
	clearField('cmbTmkdSkrmKeyid');
	var momrow = jQuery("#TaskKSAGrid").jqGrid('getDataIDs');//	row get data
	var rowid = "";
	alert(result.successData.msg);
	/*if(result.successData.msg){
		
		var r = confirm(result.successData.msg);
		for (i = 0; i < momrow.length; i++) {
			
			rowid = momrow[i];
			var CHEKVal = jQuery("#TaskKSAGrid").jqGrid('getCell', momrow[i],"CheckVal");
			if (CHEKVal == '1') {
				
				keyid = jQuery("#TaskKSAGrid").jqGrid('getCell', momrow[i], "Keyid");
				if (r == true)
				{
					processAjaxCalls("TaskMappingList_remove.tmks", "?&keyid="+ keyid+"&deletechid=Y", 'remove_successCallBackAnother','remove_errorCallBack1');
					jQuery("#TaskKSAGrid").trigger("reloadGrid");
				}
				else
					{
					return false;
				}
			}
		}*/
	/*}else{
			var hdncount=jQuery("#hdnCount").val();
		  	hdncount++;
			jQuery("#hdnCount").val(hdncount);
		for (i = 0; i < momrow.length; i++) {
			rowid = momrow[i];
			var CHEKVal = jQuery("#TaskKSAGrid").jqGrid('getCell', momrow[i],"CheckVal");
			if (CHEKVal == '1') {
				count++;
			}
		}	
		if (parseInt(count)>0)
		{
			alert(count);
			alert(result.successData.msg);
		}
		countalert(result);
	}*/
	jQuery("#TaskKSAGrid").trigger("reloadGrid");
}
function countalert(result)
{
	var hdncount=jQuery("#hdnCount").val();
	var momrow = jQuery("#TaskKSAGrid").jqGrid('getDataIDs');//	row get data
	var rowid = "";
	var count=0;
	for (i = 0; i < momrow.length; i++) {
		rowid = momrow[i];
		var CHEKVal = jQuery("#TaskKSAGrid").jqGrid('getCell', momrow[i],"CheckVal");
		if (CHEKVal == '1') {
			count++;
		}
	}	
	if (parseInt(count)==parseInt(hdncount))
	{
		
		alert("hdncount   "+hdncount+""+result.successData.msg);
	}
}

function remove_errorCallBack(result)
{
	alert(result.successData.msg);
}
function frmTaskMappingKsa_deleteSuccessCallback(result){	
	var keyid=result.successData.TmkmKeyid;
	var tkeyid=result.successData.TKeyid;
	if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
		var r = confirm("Are you sure to Delete?");
		if(r==true)
		 {
	      alert(result.successData.msg);
   		  clearForm('frmTaskMappingKsa'); 
          navigateToNextForm("TaskMappingKsa_input.tmks","Task Master"); 
		 }
		else
		   {
			   return false;
			}		 	 
	   }	
	else if(tkeyid!=null && tkeyid !=" " && tkeyid !="" && tkeyid !="undefined")
		{
		   alert(result.successData.msg);
		}
}
function frmTaskMappingKsa_successsCallback(result) {
	clearForm('frmTaskMappingKsa');
	var Type=result.type;
    setFieldValue("cmbTmkmRoleKeyid",result.successData.Rollmast);
    jQuery("#frmTaskMappingKsa input[id='flid']").val(result.successData.Flid);
	jQuery("#hdnTmkmKeyid").val(result.successData.keyId);
	var keyid=jQuery("#hdnTmkmKeyid").val();
	viewGrid('q=2&mstkeyid='+keyid);	
	//jQuery("#TaskKSAGrid").trigger("reloadGrid");	
	if (Type!="type")
		setTimeout(function(){ 
	 		navigateToPrevForm();
	 		},1000);	
		
	    
}
function frmTaskMappingKsa_deleteExceptionCallback(result)
{
	//alert(result.successData);	
}
function frmTaskMappingKsacmbTmkmRoleKeyid_onSelect()
{
	var flid = jQuery("#frmTaskMappingKsa input[id='flid']").val();
	var unique=getFieldValue('cmbTmkmRoleKeyid');
	//var keyid=jQuery("#hdnTmkmKeyid").val();
	//viewGrid('q=2&flid='+flid+'&unique='+unique);
	processAjaxCalls("TaskMasterListNew_Select.tmks", "flid="+flid+"&unique="+unique, 'list_successCallBack','list_errorCallBack');
}
function list_successCallBack(result)
{
	setFieldValue('hdnTmkmKeyid',result[0][0]);
	var keyid=jQuery("#hdnTmkmKeyid").val();
	viewGrid('q=2&mstkeyid='+keyid);	
}

</script>
<form id="frmTaskMappingKsa" name="frmTaskMappingKsa">
<div id='wrapper' style="margin-top:16px;width:100%;">
        <div id="frmTaskMappingKsa">
					<input type="hidden" id="factory" name="factory" value=""></input> 
					<input type="hidden" id="section" name=section value=""></input> 
					<input type="hidden" id="cell"    name="cell" value=""></input> 
					<input type="hidden" id="machine" name="machine" value=""></input>
					<input type="hidden" id="flid" name="cmbTmkmFlid" value="${requestScope.TMKM.tmkmFlid}"></input>
					<input type="hidden" id="elementType" name="txtTmkmElementType" value=""></input>
		</div>
  <div style="padding-left:774px;padding-top:1px;padding-left:700px\9;"></div>
  <div   id="TaskKsafunLocation" style="width: 95%;width:104%\9;padding-left:120px;padding-left:10px\9;"> 
   </div>
   <div style="padding-left:120px;padding-left:10px\9;">
 <table>
 <tr>
 <td  style="vertical-align: top;" >
 <label class="mandatory-lbl">Unique Position</label>	
      <div>	 
          <input id="cmbTmkmRoleKeyid" name="cmbTmkmRoleKeyid" class="easyui-combobox"  style="width:180px"  value="${requestScope.TMKM.tmkmRoleKeyid}" />
     </div>
 </td>
 <td  style="padding-left:10px;"><label >Basic Element</label>
 <div><textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="50" id="txtTmkdBasicemnt" name="txtTmkdBasicemnt" class="easyui-text" maxlength="98" style="width:300px;height:40px;font-size: 14" ></textarea></div></td>

 <td  style="padding-left:10px;"><label class="mandatory-lbl">Task Description</label>
 <div><textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="50" id="txtTmkdTask" name="txtTmkdTask" class="easyui-text" maxlength="98" style="width:300px;height:40px;font-size: 14" ></textarea></div></td>
 


 </tr>
 <tr style=" height : 48px;">
  <td style="vertical-align: top; " ><label class="mandatory-lbl">KSA</label><div>	
      <input id="cbmTmkdKsa" name="cbmTmkdKsa" class="easyui-combobox"  style="width:180px"  value=" " />
      <span class="tpm-errormsg" id="err_cbmTmkdKsa" style=""></span><!--
      <select id="cbmTmkdKsa" class="easyui-combobox" name="cbmTmkdKsa" style="width:110px;" required="true"  value="">
	  <option value="S">Skill</option>
	  <option value="K">Knowledge</option>
	  <option value="A">Attitude </option>																	
	  </select> 
   --> 
</td>
<td  style="padding-left:10px;"><label >KSA Description</label>
 <div><textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="50" id="txtTmkdKsadesc" name="txtTmkdKsadesc" class="easyui-text" maxlength="98" style="width:300px;height:40px;font-size: 14" ></textarea></div></td>

 <td style="padding-left: 10px;vertical-align: top; ">
	 <div class="easyui-paddingbfpx" >
	 	
	    	<label class="mandatory-lbl" >Expected Target Skill</label>	
	    	 <span style="padding-left: 30px;"><label class="mandatory-lbl">Criteria</label> </span>   	
	   	 	
	   	</div> 	                        
		<div class="easyui-paddingbfpx">
			<input id="cmbTmkdSkrmKeyid" name="cmbTmkdSkrmKeyid" class="easyui-combobox"  style="width:142px"  value="" />
		<span style="padding-left: 10px;">
			<input id="cmbTmkdSpokeKeyid" name="cmbTmkdSpokeKeyid" class="easyui-combobox"  style="width:142px"  value="" />
		</span>
	 			
		
	</div> 
 </td>
 
  
 </tr>
<tr>
<td>
</td>
<td>
</td>
 	<td style="padding-left: 160px;vertical-align: top;" >
	     <div >
	     <div><input type="button" class="easyui-button" id="btndlgSaveTop" name="btndlgSaveTop" value="Add Task"/>
	     <span style="padding-left: 5px;"><input type="button" class="easyui-button" id="btndlgDeleteRp" value="Delete"></span></div>
	     </div>
    </td>  
</tr>

 </table> 
 </div>
 <div style="padding-left:119px;">
	<table id="TaskKSAGrid"><tr><td></td></tr></table>
		<div id="pager"></div>
	    </div>  
</div> 
  <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
  <input type="hidden" id="hdnTmkmKeyid" name="hdnTmkmKeyid" value="${requestScope.TMKM.tmkmKeyid}"/> 
  <input type="hidden" id="hdnTmkdKeyid" name="hdnTmkdKeyid" value=""/> 
  <input type="hidden" id="hdnCount" name="hdnCount" value="0"/>
  <!--  <input type="text" id="hdnSirmKeyid" name="hdnSirmKeyid" value="${requestScope.mstkeyid}"/>  -->
     
</form>