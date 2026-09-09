	
<script>
	
	jQuery(document).ready(function(){
	
	
		  initialiseForm('frmMsgBrdNw');				
			jQuery('#submitForm').val('frmMsgBrdNw');
			formatDateBox('dtesmsgFromdate','dd-MMM-yyyy');
			formatDateBox('dtesmsgTodate','dd-MMM-yyyy');
			fillComboBox("frmMsgBrdNw","cmbsmsgIstobedisplayed","tobedisplay_combo.dashboard","",false);
			fillComboBox("frmMsgBrdNw","cmbsmsgRoleid","employeeRole.commonFilter");			
			var factId = jQuery("#frmMsgBrdNw input[id='factory']").val();
		    var sectionId = jQuery("#frmMsgBrdNw input[id='section']").val();
		    //var cellId = jQuery("#frmMom input[id='cell']").val();
		    var cellId = jQuery("#frmMsgBrdNw input[id='cell']").val();
		    
			var machId = jQuery("#frmMsgBrdNw input[id='machine']").val();
            var flid = jQuery("#frmMsgBrdNw input[id='flid']").val(); 
            //alert(" In Jsp ::   "+flid);
        
            var keyid=jQuery('#txtsmsgKeyid').val();
               if(keyid.trim().length<=0){
                  fillWithCurrentDate("dtesmsgFromdate");
                  fillWithCurrentDate("dtesmsgTodate");
               }
		    var dataStr = "&factId=" + factId
							+ "&sectionId=" + sectionId
							+ "&cellId=" + cellId + "&machId="
							+ machId+"&flid="+ flid;
			loadFunctionalLocation("MsgBrdfunLocation", "functionalLoc.dashboard", "MsgBrdfunLocation", "frmMsgBrdNw",dataStr);

			
			 var url = jQuery('#hiddenUrl').val();
			 var tableCaption = "Message Board";
		//	 viewGrid(url,"?q=2");
			 
			 setFieldValue('cmbsmsgIstobedisplayed','Y');
			 jQuery('input:checkbox[name=chksmsgDisAlllevel]').attr('checked',true);
	
	}); 
	
	function viewGrid(url,filterString)
	{
		var keyid=jQuery('#txtsmsgKeyid').val();
	    if(keyid.trim().length>0) 
	    	filterString+="&keyid="+keyid;
	    processGridnew("messageboard_input.dashboard",filterString,"msgbrdGrid","msgbrdpager","","doubleClickGrid","","loadComplete","","");
	    return true;
	}
	
	function frmMsgBrdNw_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		//reloadCombo("frmMsgBrdNw","cmbsmsgRoleid","employeeRole.commonFilter");
		viewGrid("","?q&flid="+keyIds.flId);
	}
	
	function frmMsgBrdNw_successsCallback(result){
	    
	    var Keyid=result.keyId;
	    //alert(" Success :: Keyid :: "+Keyid);
	    
	     jQuery("#txtsmsgKeyid").val(Keyid);
	     var flid = jQuery("#frmMsgBrdNw input[id='flid']").val();
	     viewGrid("messageboard_input.dashboard","?q&flid="+flid);
	     
	     fillWithCurrentDate("dtesmsgFromdate");
         fillWithCurrentDate("dtesmsgTodate");
         
	     jQuery("#txtsmsgKeyid").val('');
	     setFieldValue('cmbsmsgIstobedisplayed','Y');
	     jQuery('input:checkbox[name=chksmsgDisAlllevel]').attr('checked',true);
	     messageBoard();
	     
	}
	
   function frmMsgBrdNw_deleteSuccessCallback(result){
	    
	    alert(result.successData.msg);
	    jQuery('#msgbrdGrid').trigger("reloadGrid");
	    jQuery('#txtsmsgMessage').val('');
	    setFieldValue('cmbsmsgIstobedisplayed','');
	    jQuery('#txtsmsgKeyid').val('');
	    
	    messageBoard();
	}
	function doubleClickGrid(rowid)
	{
		var rowData = jQuery("#msgbrdGrid").jqGrid('getRowData',rowid);
		var keyid=rowData.KEYID;
		var roleId=rowData.ROLEID;
		
		setFieldValue('txtsmsgKeyid',keyid);
		setFieldValue('txtsmsgMessage',rowData.MESSAGEDESCRIPTION);
		setFieldValue('dtesmsgFromdate',rowData.FROMDATE);
		setFieldValue('dtesmsgTodate',rowData.TODATE);
		setFieldValue('cmbsmsgIstobedisplayed',rowData.DISPLAY);
		setFieldValue('cmbsmsgRoleid',roleId);
		if (rowData.DISALLLEVEL=='Y')
			jQuery('input:checkbox[name=chksmsgDisAlllevel]').attr('checked',true);
		else
			jQuery('input:checkbox[name=chksmsgDisAlllevel]').attr('checked',false);
		
		//processAjaxCalls("messageboard_recall.dashboard?&KEYID="+keyid,"","recallsuccessCallBack","errorCallBack");
	}
	
	function recallsuccessCallBack(result){
		//alert(result[0][0]);
		
		setFieldValue('txtsmsgKeyid',result[0][0]);
		setFieldValue('txtsmsgMessage',result[0][2]);
		setFieldValue('dtesmsgFromdate',result[0][3]);
		setFieldValue('dtesmsgTodate',result[0][4]);
		setFieldValue('cmbsmsgIstobedisplayed',result[0][5]);
		
	}
	
</script>

<form name="frmMsgBrdNw" id="frmMsgBrdNw">
<div id="wrapperRpt" style="margin-left:100px;margin-top:40px;">
<table>
        <tr>
        <td colspan="2">
        <div id="frmMsgBrdFuntKeyIds">
					
					<input type="hidden" id="location" name="cmbSmsgLocationId" value=""></input> 
					<input type="hidden" id="section"  name="cmbSmsgSectionid" value=""></input>
					<input type="hidden" id="sbu"      name="cmbSmsgSbu" value=""></input> 
					<input type="hidden" id="pbu"      name="cmbSmsgPbu" value=""></input>
					<input type="hidden" id="dmt"      name="cmbSmsgDmt" value=""></input>
					<input type="hidden" id="jh"       name="cmbSmsgJh" value=""></input>
					<input type="hidden" id="cell"     name="cmbSmsgCellid" value=""></input> 
					<input type="hidden" id="flid"     name="cmbSmsgFlid" value="${requestScope.newadmTlScrollmsgmst.smsgFlid}"></input>        
		</div>

     <div  class="easyui-paddingbfpx" id="MsgBrdfunLocation" style="width: 104%;margin-top:-12px;width:108%\9;"></div>
     </td>
     </tr>
</table>

<table>
	<tr>
		<td>
			<div>
			<label> Role </label>
			</div>
			<div>
			 <input id="cmbsmsgRoleid" name="cmbsmsgRoleid" class="easyui-combobox" style="width:180px;" value=" " />
			</div>
		<td>
		<td>
			<div>
			<label>From Date</label>
			</div>
			<div>
			 <input id="dtesmsgFromdate" name="dtesmsgFromdate" class="easyui-datebox" style="width:90px;" value=" " />
			</div>
		<td>
		<div style="padding-left:10px">
			<div>
				<label>To Date</label>
			</div>
			<div>
			 <input id="dtesmsgTodate" name="dtesmsgTodate" class="easyui-datebox" style="width:90px;" value=" "  />
			</div>
		</div>
		</td>
		<td>
			<div style="padding-left:10px">
			<div>
			<label> Display ? </label>
			</div>
			<div>
			<input class="easyui-combobox" id="cmbsmsgIstobedisplayed" name="cmbsmsgIstobedisplayed"  style=" width : 60px;"  value=" " />
			</div>
		</div>
		</td>
		<td rowspan="2">
	    <div style="padding-top:10px;">
			<div>
			  <label>Description</label>
			</div>
			<div>
			  <textarea  rows="3"  cols="17" id="txtsmsgMessage" title="Maximum Length is 500" maxlength="500" name="txtsmsgMessage"  style="height : 80px;width:466px; margin-left: 0px;text-transform: ;"></textarea>
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
	    <td  colspan="3" style="padding-left: 10px;">
			<input type="checkbox" id="chksmsgDisAlllevel" name="chksmsgDisAlllevel" value="Y" > 
			<label class="mandatory-lbl"> Display Messages to all levels below the selected Function Location</label>
		</td>
	</tr>
	
</table>


<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:10px;" >
<table id="msgbrdGrid"></table>
		<div id="msgbrdpager"></div>
		</div>
</div>
 <input type="hidden" id="mode"/>
 <input type="hidden" id="txtsmsgKeyid"  name="txtsmsgKeyid" value="${requestScope.newadmTlScrollmsgmst.smsgKeyid}" />


</form>