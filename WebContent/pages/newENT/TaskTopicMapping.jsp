<script type="text/javascript">
jQuery(document).ready(function(){
	
	initialiseForm('frmTaskTopicMap');
	//alert("frmTaskTopicMap");
	jQuery('#submitForm').val('frmTaskTopicMap');
	
	var flid = jQuery("#frmTaskTopicMap input[id='flid']").val();  
	fillComboBox("frmTaskTopicMap","cmbTmtmRoleKeyid","roleMst.commonFilter?flid="+flid);
	fillComboBox("frmTaskTopicMap","cmbTmtmTopiKeyid","combo_Topic.commonFilter?flid="+flid);
	
	jQuery("#cmbTmtmRoleKeyid").combobox({onRequest:function( ){
			var flid = jQuery("#frmTaskTopicMap input[id='flid']").val();
			return "flid="+flid;
	}});
	  
	var factId = jQuery("#frmTaskTopicMap input[id='factory']").val();
    var sectionId = jQuery("#frmTaskTopicMap input[id='section']").val();
	var cellId = jQuery("#frmTaskTopicMap input[id='cell']").val();
	var machId = jQuery("#frmTaskTopicMap input[id='machine']").val();
	flid = jQuery("#frmTaskTopicMap input[id='flid']").val(); 
	
	var dataStr = "&factId=" + factId
			+ "&sectionId=" + sectionId
			+ "&cellId=" + cellId + "&machId="
			+ machId+"&flid="+ flid;
	  loadFunctionalLocation("TaskTopicfunLocation", "functionalLoc.tmtopic", "TaskTopicfunLocation", "frmTaskTopicMap",dataStr);
	  var keyid=jQuery('#hdnTmtmKeyid').val();
		if(keyid!=null && keyid!=" " && keyid!=""){			
			disableField('frmTaskTopicMap', 'cmbTmtmRoleKeyid');
		}	   
		var UP =getFieldValue('cmbTmtmRoleKeyid');
		var topic=getFieldValue('cmbTmtmTopiKeyid');
	    viewGrid('q=2&mstkeyid='+keyid+'&flid='+flid+'&Unique='+UP+'&topic='+topic);

	    
	   jQuery("#btnAttView").click(function()
				 {	
		           var flid = jQuery("#frmTaskTopicMap input[id='flid']").val();      
			       var UniquePostion =getFieldValue('cmbTmtmRoleKeyid'); 
			       var createmode=jQuery('#createmode').val();
			       if((flid== null || flid=='') || (UniquePostion==null || UniquePostion==' '))
				       {
				         alert("Select Function Location and UniquePosition" );
				       }
			       else{				       
				        // processGridnew("TaskTopicDetails_input.tmtopic","?q=2&flid="+flid+"&Unique="+UniquePostion+'&createmode='+createmode, "TaskTopicGrid", "pager","","","","Load_CompleteAtt");
			    	   var Topic = getFieldValue('cmbTmtmTopiKeyid');
			    		var Rolekeyid = getFieldValue('cmbTmtmRoleKeyid');
			    		var keyid=jQuery("#hdnTmtmKeyid").val();
			    		//alert(keyid);
			    		viewGrid('q=2&mstkeyid='+keyid+"&flid="+flid+"&Unique="+Rolekeyid+"&topic="+Topic);				 
			       }
			 }); 

	   numericTextBox('txtTmtmCutoffmark');
});

jQuery('#btnDelete').click( function(){	
        var count=0;
	    var momrow = jQuery("#TaskTopicGrid").jqGrid('getDataIDs');  
	    for(i=0;i<momrow.length;i++){                      
	      var CHEKVal = jQuery("#TaskTopicGrid").jqGrid('getCell',momrow[i],"CheckVal");                   
		  if(CHEKVal =='1'){
		      count++;
		  }	              		          		                                    
	    }
		if(count=='0'){ 
		       alert("Select Task"); 
		     }                     
		 else
		 {  removeRecord();}
});	
function removeRecord(keyid) {
	var momrow = jQuery("#TaskTopicGrid").jqGrid('getDataIDs');//	row get data
	var rowid = "";
	var r = confirm("Do You Want To Delete?");
	for (i = 0; i < momrow.length; i++) {
		rowid = momrow[i];
		var CHEKVal = jQuery("#TaskTopicGrid").jqGrid('getCell', momrow[i],"CheckVal");
		if (CHEKVal == '1') {
			keyid = jQuery("#TaskTopicGrid").jqGrid('getCell', momrow[i], "KEYID");
			if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "") {
				if (r == true) {
					processAjaxCalls("TaskTopicMappingList_remove.tmtopic", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
					} else
					return false;

			} else {

				var r = confirm("Do You Want To Remove Row?");
				if (r == true)
					jQuery("#TaskTopicGrid").trigger("reloadGrid");
				else
					return false;
			  }
		   }
	}
}
function remove_successCallBack(result){	
	jQuery("#TaskTopicGrid").trigger("reloadGrid");
}
function viewGrid(filter){
	var topic = getFieldValue('cmbTmtmTopiKeyid');
	if (topic=='' || topic==' ' || topic==null) {
		topic=' ';
	}
		
	 processGridnew("TaskTopicDetails_input.tmtopic",filter,"TaskTopicGrid","pager","","doubleclick","","Load_CompleteAtt");
}
function formatterCheckbox(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	var colvalue=rowObject[3];
	//alert(colvalue);
	var chkd ="";
	if (colvalue==1)
		chkd  = ' checked = "checked" ';
	return '<input type="checkbox" id="checkbox_'+rowId+'_'+colId+'" name="checkbox_'+rowId+'_'+colId+'"  style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxCheck1(\''+rowId + '\');}else{chkboxUnCheck1(\''+ rowId +'\');}"  '+chkd+' "/>';
}
function chkboxCheck1(rowId) {
	jQuery("#TaskTopicGrid").jqGrid('setCell', rowId, 'CheckVal', '1');
}

function chkboxUnCheck1(rowId) {
	jQuery("#TaskTopicGrid").jqGrid('setCell', rowId, 'CheckVal', '0');
}

function TaskTopicGrid_selectAll(id,status){
	
	for(var i=0; i<id.length; i++){
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}
function TaskTopicGrid_selectRow(id){

	if(jQuery('#jqg_TaskTopicGrid_'+id).is(':checked'))
		chkboxCheck(id);
	else
		chkboxUnCheck(id);
}
function frmTaskTopicMap_beforeSubmit(){
	  var count=0;
	 var momrow = jQuery("#TaskTopicGrid").jqGrid('getDataIDs');  
	  for(i=0;i<momrow.length;i++){                      
	   var CHEKVal = jQuery("#TaskTopicGrid").jqGrid('getCell',momrow[i],"CheckVal");   
	  if(CHEKVal =='1'){
			
			      count++;
			  }	              		          		                                    
		    }
			if(count=='0'){ 
			       alert("Select Task"); 
			       return false;
			     } 
		var gridData = '&TopicDetails='+ convertJHFSAuditToJSONString();
		return gridData;
}
/*function frmTaskTopicMap_beforeSubmit(){
	
	var gridData = '&TopicDetails='+ convertJHFSAuditToJSONString();
	return gridData;
}*/
function convertJHFSAuditToJSONString() {
	var momrow = jQuery("#TaskTopicGrid").jqGrid('getDataIDs');//	row get data
	var momcol = jQuery("#TaskTopicGrid").jqGrid("getGridParam", "colModel");// col get data
	var rowid = "";
	var controlId = "";
	var cKeyId; //det keyid
	var colValue = "";
	var controlId1 = "";
	var colValue1 = "";
	var keyid;
	var jsonArrO = '[';
	for (i = 0; i < momrow.length; i++) {
		rowid = momrow[i];
		cKeyId = jQuery("#TaskTopicGrid").jqGrid('getCell', rowid, "KEYID"); // propertie index id of value detailkeyid
		tmtdid  = jQuery("#TaskTopicGrid").jqGrid('getCell', rowid, "SLNO");  
		var colkeyid = jQuery('#hdnTmtmKeyid').val(); 
		var TaskKeyid = jQuery("#TaskTopicGrid").jqGrid('getCell',rowid,"TASKKEYID");
		var CHEKVal = jQuery("#TaskTopicGrid").jqGrid('getCell', momrow[i],"CheckVal");
			if((CHEKVal == '1' && tmtdid.trim().length==0 )|| (CHEKVal=='0' && tmtdid.trim().length>0))
			{			
				if (tmtdid.trim().length>0)
					keyid=tmtdid;						
				else if(cKeyId.trim().length !="0"){
					keyid=cKeyId;
					}
				else{
					keyid="";
					}
				jsonArrO += '{';
				jsonArrO += '"txtTmtdKeyid":"' + keyid + '",';
				jsonArrO += '"txtTmtdTmtmKeyid":"' + colkeyid + '",';		
				jsonArrO += '"txtTmtdTmkmKeyid":"' + TaskKeyid + '"';
				jsonArrO += '},';
		    }
		}
	if (jsonArrO != "[")
		jsonArrO = jsonArrO.slice(0, -1) + "]";
	else
		jsonArrO = "";
	return jsonArrO;
}
function frmTaskTopicMap_successsCallback(result) {	

    clearForm("frmTaskTopicMap");
    setTimeout(function(){ 
 		navigateToPrevForm();
 		},1000);
    //navigateToPrevForm();
	jQuery("#frmTaskTopicMap input[id='flid']").val(result.successData.Flid);
	jQuery("#hdnTmtmKeyid").val(result.successData.keyId);
	
	setFieldValue("cmbTmtmTopiKeyid", result.successData.Topic );
	setFieldValue("cmbTmtmRoleKeyid", result.successData.Rollmast );
	//jQuery("#cmbTmtmTopiKeyid").val(result.successData.Topic);
	//jQuery("#cmbTmtmRoleKeyid").val(result.successData.Rollmast);
	
	
	var Topic = getFieldValue('cmbTmtmTopiKeyid');
	var Rolekeyid = getFieldValue('cmbTmtmRoleKeyid');
	var keyid=jQuery("#hdnTmtmKeyid").val();
	//jQuery("#TaskTopicGrid").trigger("reloadGrid");	
	viewGrid('q=2&mstkeyid='+keyid+"&flid="+result.successData.Flid+"&Unique="+Rolekeyid+"&topic="+Topic);		    
}
function frmTaskTopicMap_deleteSuccessCallback(result){	
	var keyid=result.successData.TmtmKeyid;
	if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
		var r = confirm("Are you sure to Delete?");
		if (r == true) 
		{
		alert(result.successData.msg);	
		clearForm('frmTaskTopicMap'); 
		navigateToNextForm("TaskTopicMapping_input.tmtopic","Task Topic Mapping"); 
		}
		else
			{			
			 return false; 
			}
	}
	else if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined")
	{
	   alert(result.successData.msg);
	}
}
function Load_CompleteAtt() { 
	var checkvalue;
	var row = jQuery("#TaskTopicGrid").jqGrid('getDataIDs');
	for ( var i = 0; i < row.length; i++) {
		var cKeyId = jQuery("#TaskTopicGrid").jqGrid('getCell', row[i], "KEYID"); 		 
		if (cKeyId.trim().length>0) { 
			//jQuery('#TaskTopicGrid').setSelection(row[i], true);
			//jQuery("#jqg_TaskTopicGrid_" + row[i]).attr('checked', 'checked');
			jQuery('#checkbox_'+row[i]+'_2').attr('checked',true);
		} 
	}
}
function maxvaluutoff(id)
{	
	    if (jQuery("#"+id).val() >= 101) {
			   alert("Maximum value 100");
	     }
}	
function frmTaskTopicMap_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	  
	  var selFlid = keyIds.flId;
	   //alert(selFlid);
	  reloadCombo("frmTaskMappingKsa","cmbTmtmRoleKeyid","roleMst.commonFilter?flid="+selFlid);
	  reloadCombo("frmTaskTopicMap","cmbTmtmTopiKeyid","combo_Topic.commonFilter?flid="+selFlid);
	  
	setFunctionalLocWidth("frmTaskTopicMap","760px");
}
 
 function frmTaskTopicMap_deleteSuccessCallback(result) {
	 alert(result.successData.msg);
 }
 
</script>
<form id="frmTaskTopicMap" name="frmTaskTopicMap">
<div style="margin-top:16px;width:100%;">
 <div id="frmRevFuntKeyIds">
					<input type="hidden" id="factory" name="factory" value=""></input> 
					<input type="hidden" id="section" name=section value=""></input> 
					<input type="hidden" id="cell"    name="cell" value=""></input> 
					<input type="hidden" id="machine" name="machine" value=""></input>
					<input type="hidden" id="flid" name="cmbTmtmFlid" value="${requestScope.TMTM.tmtmFlid}"></input>
					<input type="hidden" id="elementId" name="txtTmtmElementId" value="${requestScope.TMTM.tmtmElementId}"></input>
					<input type="hidden" id="elementType" name="txtTmtmElementType" value=""></input>
					
 </div>
 <div style="padding-left:774px;padding-top:14px;padding-left:700px\9;"></div>
 <div  class="easyui-paddingbfpx" id="TaskTopicfunLocation" style="width: 95%;width:104%\9;padding-left:100px;padding-left:10px\9;"> 
 </div>
  
<table style="padding-left:100px;">
 <tr>
 <td>
 <label class="mandatory-lbl">Unique Position</label>	
      <div>	 
          <input id="cmbTmtmRoleKeyid" name="cmbTmtmRoleKeyid" class="easyui-combobox"  style="width:180px"  value="${requestScope.TMTM.tmtmRoleKeyid}" />
     </div>
 </td>
 <td style="padding-left:10px">
<label class="mandatory-lbl">Topic</label> 
   <div>
   <input id="cmbTmtmTopiKeyid" name="cmbTmtmTopiKeyid" class="easyui-combobox"  style="width:180px"  value="${requestScope.TMTM.tmtmTopiKeyid}"/>	
   </div>
 </td>
 <td style="padding-left:10px"><label>Cutoff Marks</label>
     <div><input id="txtTmtmCutoffmark" name="txtTmtmCutoffmark" class="easyui-text" maxlength="3" style="width:100px;text-align:right;" onblur="maxvaluutoff(this.id);" value="${requestScope.TMTM.tmtmCutoffmark}" /></div>
 </td>
 </tr>
   </table> 
  <div Style="padding-left:101px;padding-top:10px;"><input type="button" class="easyui-button" id="btnAttView" name="btnAttView" value="View" style="height:23px;font-size: 12"/><span style="padding-left:5px"><input type="button" class="easyui-button" id="btnDelete" name="btnDelete" value="Delete" style="height:23px"/></span></div>
  <div id="jqGridTaskTopic">
  <div style="padding-left:100px;padding-top:8px;" >
	<table id="TaskTopicGrid"><tr><td></td></tr></table>
		<div id="pager"></div>
	    <div id="paramDiv" style="display:none;" title="param">
	   </div>	
	   </div>
</div> 
</div>
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
 <input type="hidden" id="createmode" name="createmode" value="${requestScope.createmode}"/>
 <input type="hidden" id="hdnTmtmKeyid" name="hdnTmtmKeyid" value="${requestScope.TMTM.tmtmKeyid}"/>
</form>