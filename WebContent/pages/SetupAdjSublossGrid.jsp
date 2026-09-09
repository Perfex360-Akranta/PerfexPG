<script type="text/javascript">
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	processGridnew("subloss_input.genmain","?q=2&refDocId="+jQuery('#hdnGmntRefdocid').val(),"subLossGrid","","","subLossGrid_dblclick","","subLossGridCallback");
	formatDateBox('dteShiftdate','dd-MMM-yyyy');
	fillComboBox("frmSetupAdjSubloss","cmbSubLoss","combo_SubLoss.genmain");	
	var factId = getFieldValue('factory','frmGenralMaintenance');
	var fromDate = jQuery('#dteGmntOccureddate').datebox('getValue') + ' '+jQuery('#spnoccuredTime').spinner('getValue');
	var toDate = jQuery('#dteGmntWoenddate').datebox('getValue') + ' '+jQuery('#spnWorkendTime').spinner('getValue');
	fillComboBox("frmSetupAdjSubloss","cmbShiftid","combo_SubLossShift.genmain?q=2&factId="+factId+"&fromDate="+fromDate+"&toDate="+toDate);
	numericTextBox('txtDuration'); 
	jQuery('#frmSetupAdjSubloss .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmSetupAdjSubloss textarea').css('text-transform', 'uppercase');	

    var occDate = jQuery('#dteGmntOccureddate').datebox('getValue');
    var workEndDate = jQuery('#dteGmntWoenddate').datebox('getValue');
    if(occDate == workEndDate)
    	 jQuery('#dteShiftdate').datebox('setValue',occDate);
    var durationTotal = jQuery('#txtGmntDowntime').val();
    if(durationTotal != null && durationTotal != '' && durationTotal != ' ')
    	jQuery('#txtTotDuration').val(durationTotal);
        
});
jQuery("#txtDuration").change(function(){
	clearValidationErrorMsg('txtDuration');	
	/*var durationTotal = jQuery("#hdnTotalDuration").val();	
	var downTime = jQuery("#txtGmntDowntime").val();	

	if(durationTotal != null && durationTotal != '' && durationTotal != ' ')
	{		
		durationTotal = parseInt(durationTotal)+parseInt(jQuery("#txtDuration").val());
		if(parseInt(durationTotal)>parseInt(downTime))
		{
			jQuery("#txtDuration").val(' ');
			showValidationErrorMsg('txtDuration','Duration Should Not Exceed Downtime');
		}
		else
		{
			enableUIButton('btnInsertSubLoss');
			clearValidationErrorMsg('txtDuration');	
		}
	}
	else
	{
		if(parseInt(jQuery("#txtDuration").val())>parseInt(downTime))
		{
			jQuery("#txtDuration").val(' ');
			showValidationErrorMsg('txtDuration','Duration Should Not Exceed Downtime');
		}
		else
		{
			enableUIButton('btnInsertSubLoss');
			clearValidationErrorMsg('txtDuration');	
		}
	}*/
});
jQuery('#btnDelSubLoss').click(function(){

	var dataString = '?q=2';
	var keyId = jQuery('#hdnSetUpLossKeyId').val();
	if(keyId != null && keyId != '' && keyId != ' ')
		dataString += '&keyId='+keyId;
	
	processAjaxCalls('SubLoss_del.genmain',dataString,'afterDelLoss','errDelLoss');
});

jQuery('#btnInsertSubLoss').click(function(){
	 var row = jQuery("#subLossGrid").jqGrid('getDataIDs');
	 var durationTotal = jQuery("#hdnTotalDuration").val();
	 var downTime = jQuery("#txtGmntDowntime").val();	
	 var lossId = jQuery('#cmbSubLoss').combobox('getValue'); 
	 var lossName = jQuery('#cmbSubLoss').combobox('getText'); 
	 var shiftDate = jQuery('#dteShiftdate').datebox('getValue');
	 var shiftId  = jQuery('#cmbShiftid').combobox('getValue'); 
	 var shiftCode = jQuery('#cmbShiftid').combobox('getText'); 
	 var duration = jQuery('#txtDuration').val();
	 var keyid = jQuery('#hdnSetUpLossKeyId').val();
	 if(keyid == null || keyid == '' || keyid == ' ')
		 keyid == '';
	 var insertFlag = true;
 	 var emptyItem =[{txtsupsLossid:lossId,losscode:keyid,subloss:lossName,txtsupsSplitdate:shiftDate,shiftCode:shiftCode,txtsupsSplitshift:shiftId,txtsupsDuration:duration}];
 	 var subLossRowId = jQuery('#hdnSublossRowId').val(); 	
 	/* if(durationTotal != null && durationTotal != '' && durationTotal != ' ')
		 durationTotal = parseInt(durationTotal) + parseInt(duration); 
	 else
		 durationTotal = duration;*/
	if(lossId == null || lossId == '' || lossId == ' ')
	{
		insertFlag = false;
		showValidationErrorMsg('cmbSubLoss','Select Loss');
	}
	else if(shiftDate == null || shiftDate == '' || shiftDate == ' ')
	{
		insertFlag = false;
		showValidationErrorMsg('dteShiftdate','Select ShiftDate');
	}
	else if(shiftId == null || shiftId == '' || shiftId == ' ')
	{
		insertFlag = false;
		showValidationErrorMsg('cmbShiftid','Select Shift');
	}
	else if(duration == null || duration == '' || duration == ' ' || duration == '0')
	{
		insertFlag = false;
		if(duration != '0')
			showValidationErrorMsg('txtDuration','Enter Duration ');
		
	}
		
	if(durationTotal != null && durationTotal != '' && durationTotal != ' ')
	{		
		if(subLossRowId != null && subLossRowId != '' && subLossRowId != ' ')
		{	
			 var selDuration = jQuery("#subLossGrid").jqGrid('getCell',subLossRowId,'txtsupsDuration');
			 durationTotal = parseInt(durationTotal)-parseInt(selDuration);
		}
		durationTotal = parseInt(durationTotal)+parseInt(duration);
		if(parseInt(durationTotal)>parseInt(downTime))
		{
			jQuery("#txtDuration").val(' ');
			showValidationErrorMsg('txtDuration','Duration Should Not Exceed Downtime');
			insertFlag = false;
		}
		else
		{
			clearValidationErrorMsg('txtDuration');	
		}
	}
	else
	{
		 /*if(row.length > 0)
		 {
			
			 for(var i =0;i<row.length;i++)
			 {
				 var prevDuration = jQuery("#subLossGrid").jqGrid('getCell',row[i],'txtsupsDuration');
				 
				 if(i == 0)
					 durationTotal = prevDuration;
				 else
					durationTotal = parseInt(durationTotal)+parseInt(prevDuration);
			 }
			 durationTotal = parseInt(durationTotal)+parseInt(duration);
			
			 if(parseInt(durationTotal)>parseInt(downTime))
			 {
				jQuery("#txtDuration").val(' ');
				showValidationErrorMsg('txtDuration','Duration Should Not Exceed Downtime');
				insertFlag = false;
			 }
			 else
			 {
				clearValidationErrorMsg('txtDuration');	
			 }
		 }
		 else
		 {*/
			 durationTotal = duration;
			 if(parseInt(duration)>parseInt(downTime))
			 {
				 jQuery("#txtDuration").val(' ');
				 showValidationErrorMsg('txtDuration','Duration Should Not Exceed Downtime');
				 insertFlag = false;
			 }
			 else
			 {
				clearValidationErrorMsg('txtDuration');	
			 }
		 //}
		 
	}

 	 if(insertFlag)
 	 {
 		if(subLossRowId != null && subLossRowId != '' && subLossRowId != ' ')
		{
 			
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"txtsupsLossid",lossId,"");
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"losscode",keyid,"");
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"subloss",lossName,"");
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"txtsupsSplitdate",shiftDate,"");
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"shiftCode",shiftCode,"");
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"txtsupsSplitshift",shiftId,"");
 			jQuery("#subLossGrid").jqGrid('setCell',subLossRowId,"txtsupsDuration",duration,"");
 			jQuery('#hdnSublossRowId').val('');
		}
 		else
 	 	{
			 if ( row == null || row == '' || parseInt(row) <= 0) {		
			 	jQuery("#subLossGrid").jqGrid('addRowData',1, emptyItem[0]);
			 }
			 else
			 {
				jQuery("#subLossGrid").jqGrid('addRowData',row.length+1, emptyItem[0]);
			 }
 	 	}
		 
		 jQuery("#hdnTotalDuration").val(durationTotal);
		
		 var afterSaveRows = jQuery("#subLossGrid").jqGrid('getDataIDs');		
		 jQuery("#hdnLossValues").val('');
			var tempVal = null;
		 for(var i=0;i<afterSaveRows.length;i++)
		 {
			 lossId = jQuery("#subLossGrid").getCell(afterSaveRows[i], 'txtsupsLossid').trim();
			 lossName = jQuery("#subLossGrid").getCell(afterSaveRows[i], 'subloss').trim();
			 shiftDate = jQuery("#subLossGrid").getCell(afterSaveRows[i], 'txtsupsSplitdate').trim();
			 shiftCode = jQuery("#subLossGrid").getCell(afterSaveRows[i], 'shiftCode').trim();
			 shiftId = jQuery("#subLossGrid").getCell(afterSaveRows[i], 'txtsupsSplitshift').trim();
			 duration = jQuery("#subLossGrid").getCell(afterSaveRows[i], 'txtsupsDuration').trim();
		 	 var hdnLossVal = lossId + ","+lossName+","+shiftDate+","+shiftCode+","+shiftId+","+duration+";";
		    //jQuery("#hdnLossValues").val();
			 if(tempVal != null  && tempVal != '' && tempVal != ' ')
			 	tempVal = tempVal + hdnLossVal;
			 else
			 	tempVal = hdnLossVal;			
		 	
		 }	
		 if(tempVal != null)
		 	jQuery("#hdnLossValues").val(tempVal);	
			
		 clearField('cmbSubLoss');
		 var occDate = jQuery('#dteGmntOccureddate').datebox('getValue');
		 var workEndDate = jQuery('#dteGmntWoenddate').datebox('getValue');
		 if(occDate == workEndDate)
		    jQuery('#dteShiftdate').datebox('setValue',occDate);
		 else
		 	clearField('dteShiftdate');
		 clearField('cmbShiftid');
		 clearField('txtDuration');	
		 clearField('hdnSetUpLossKeyId');	
 	 }
 
	
});
function afterDelLoss(result)
{
	 alert(result.successData.msg);				
	 jQuery("#subLossGrid").trigger("reloadGrid");
	 clearField('cmbSubLoss');
	 var occDate = jQuery('#dteGmntOccureddate').datebox('getValue');
	 var workEndDate = jQuery('#dteGmntWoenddate').datebox('getValue');
	 if(occDate == workEndDate)
	    jQuery('#dteShiftdate').datebox('setValue',occDate);
	 else
	 	clearField('dteShiftdate');
	 clearField('cmbShiftid');
	 clearField('txtDuration');	
	 clearField('hdnSetUpLossKeyId');
}
function frmSetupAdjSublosscmbSubLoss_onSelect(record)
{
	clearValidationErrorMsg('cmbSubLoss');	
}
function frmSetupAdjSublosscmbShiftid_onSelect(record)
{
	clearValidationErrorMsg('cmbShiftid');	
}
function dteShiftdate_onSelect(date)
{   
		clearValidationErrorMsg('dteShiftdate');	
	 	var shiftDate = jQuery('#dteShiftdate').datebox('getValue') + '00:00';
	    var occuredDate = jQuery('#dteGmntOccureddate').datebox('getValue') + '00:00';
		var workEndDate = jQuery('#dteGmntWoenddate').datebox('getValue') + '00:00';
			
		if(compareDateTime(shiftDate,occuredDate)>0)
		{
			showValidationErrorMsg('dteShiftdate','Shift Date should not be lesser than Occured Date');
			jQuery('#dteShiftdate').datebox('setValue',jQuery('#dteGmntOccureddate').datebox('getValue'));		
	 	}
		else if( compareDateTime(workEndDate,shiftDate) > 0 )
	    {
			showValidationErrorMsg('dteShiftdate','Shift Date should not be greater than Work End Date');
			jQuery('#dteShiftdate').datebox('setValue',jQuery('#dteGmntWoenddate').datebox('getValue'));	
	    }
	    else
	    	clearValidationErrorMsg('dteShiftdate');	
}
function SubLossSNA_Callback()
{
	
	
}
function subLossGrid_dblclick(id)
{
	var rowData = jQuery("#subLossGrid").jqGrid('getRowData',id);	
	jQuery('#cmbSubLoss').combobox('setValue',rowData.txtsupsLossid);
	jQuery('#dteShiftdate').datebox('setValue',rowData.txtsupsSplitdate);
	jQuery('#cmbShiftid').combobox('setValue',rowData.txtsupsSplitshift);
	jQuery('#txtDuration').val(rowData.txtsupsDuration);
	jQuery('#hdnSublossRowId').val(id);	
	jQuery('#hdnSetUpLossKeyId').val(rowData.losscode);
}
function subLossGridCallback()
{
	 var lossGridIds = jQuery("#subLossGrid").jqGrid('getDataIDs');
	 if(lossGridIds.length > 0)
	 {
		 var durationTotal = null;
		 for(var i =0;i<lossGridIds.length;i++)
		 {
			 var prevDuration = jQuery("#subLossGrid").jqGrid('getCell',lossGridIds[i],'txtsupsDuration');
			 
			 if(i == 0)
				 durationTotal = prevDuration;
			 else
				durationTotal = parseInt(durationTotal)+parseInt(prevDuration);
		 }
		 if(durationTotal != null)
			 jQuery("#hdnTotalDuration").val(durationTotal);
	 }
	 else
	 {
		 var hdnLoss = jQuery('#hdnLossValues').val();	
		 //var row = jQuery("#subLossGrid").jqGrid('getDataIDs');
		 var lossId = null;	
		 var lossName = null;
		 var shiftDate = null;
		 var shiftCode = null;
		 var shiftId = null;
		 var duration = null;
		 if(hdnLoss != null && hdnLoss != '' && hdnLoss != ' ')
		 {
			var hdnLossArr =  hdnLoss.split(';');		
			for(var i=0;i<hdnLossArr.length-1;i++)
			{
				var lossTempVal = hdnLossArr[i].split(',');
				var flag = 0;			
				while(flag < lossTempVal.length)
				{
					if(flag == 0)				
						lossId = lossTempVal[flag];				
					else if(flag == 1)
						lossName = lossTempVal[flag];	
					else if(flag == 2)
						shiftDate = lossTempVal[flag];	
					else if(flag == 3)
						shiftCode = lossTempVal[flag];	
					else if(flag == 4)
						shiftId = lossTempVal[flag];
					else if(flag == 5)
						duration = lossTempVal[flag];	
					flag++;				
				}
				 var emptyItem =[{txtsupsLossid:lossId,losscode:lossId,subloss:lossName,txtsupsSplitdate:shiftDate,shiftCode:shiftCode,txtsupsSplitshift:shiftId,txtsupsDuration:duration}];
				 if ( lossGridIds == null || lossGridIds == '' || parseInt(lossGridIds) <= 0) {		
					 	jQuery("#subLossGrid").jqGrid('addRowData',1, emptyItem[0]);
				 }
				 else
				 {
						jQuery("#subLossGrid").jqGrid('addRowData',lossGridIds.length+1, emptyItem[0]);
				 }
						
			}
		}
	 }
	
	/*var hdnLoss = jQuery('#hdnLossValues').val();
	if(hdnLoss != null && hdnLoss != '' && hdnLoss != ' ')
	{
		var hdnLossArr = hdnLoss.split(';');
		var lossGridIds = jQuery("#subLossGrid").jqGrid('getDataIDs');
		for(i=1;i<=lossGridIds.length;i++)	
		{	
			var flag = 0;
			while(flag<hdnLossArr.length)
			{
				
				var mapField = hdnLossArr[flag].substring(0,hdnLossArr[flag].indexOf('='));
				var duration = hdnLossArr[flag].substring(hdnLossArr[flag].indexOf('=')+1);
				
				if(jQuery("#subLossGrid").getCell(i, 'losscode') == mapField)
					jQuery("#subLossGrid").jqGrid('setCell',i,"duration",duration);
			
				flag++;
				
			} 
		}
	}
	jQuery("#subLossGrid").setGridParam({
		
		afterEditCell: function(rowid, name, value, iRow, iCol) {	
			 var inputControl = jQuery('#' + (iRow) + '_' + name);	
			 numericTextBox(iRow+ '_' + name);			
			
		},
		afterSaveCell : function(rowid,name,val,iRow,iCol) 
		{
			var lossVal = jQuery('#hdnLossValues').val();
			var mapField = jQuery("#subLossGrid").jqGrid('getCell',rowid,'losscode');
			var durationTotal = jQuery("#hdnTotalDuration").val();
			//var downTime = jQuery("#txtGmntDowntime").val();
			// alert(durationTotal + ' <> '+downTime);
			// if(parseInt(durationTotal)>parseInt(downTime))
				// jQuery('#' + (iRow) + '_' + name).val(' ');
			if(lossVal != null && lossVal != '' && lossVal != ' ')
			{
				if(lossVal.indexOf(mapField)>=0)
				{
					var loss1 = lossVal.substring(0,lossVal.indexOf(mapField));					
					var loss2 = lossVal.substring(lossVal.indexOf(mapField));					
					var index = loss2.indexOf(';');							
					if(loss1 != null && loss1 != '' && loss1 != ' ')	
						lossVal = loss1 + loss2.substring(index +1);
					else
						lossVal = loss2.substring(index +1);

					var prevVal = 	loss2.substring(loss2.indexOf('=')+1,index);
					durationTotal = parseInt(durationTotal) - parseInt(prevVal);
				}
				
				if(val != null && val != '' && val != ' ')
				{
					lossVal += mapField + '='+val+';';
					durationTotal = parseInt(durationTotal) + parseInt(val);
				}
				
			}
			else
			{
				if(val != null && val != '' && val != ' ')
				{
					lossVal = mapField + '='+val+';';
					durationTotal = val;
				}
			}
			
			jQuery('#hdnLossValues').val(lossVal);	
			jQuery("#hdnTotalDuration").val(durationTotal);	
		}
	});*/
	
}
</script>
<form id="frmSetupAdjSubloss" name="frmSetupAdjSubloss">
<div id="wrapper">
<div>
	<label>Sub Loss</label>
	<label style="padding-left:211px;">Shift Date</label>
	<label style="padding-left:43px;">Shift</label>
	<label style="padding-left:57px;">Duration</label>
	<label style="padding-left:16px;">Total Duration</label>
</div>
<div class="easyui-paddingbfpx">
	<input id="cmbSubLoss" name="cmbSubLoss" class="easyui-combobox"   style="width: 265px;" />
	<input id="dteShiftdate"  name="dteShiftdate" clear="false" class="easyui-datebox"/>
	<input  id="cmbShiftid" name="cmbShiftid" class="easyui-combobox" style="width: 80px;" />
	<input type="text" id="txtDuration" name="txtDuration" class="easyui-text"  style="height: 22px;width:65px;"/>
	<input type="text" id="txtTotDuration" name="txtTotDuration" tabindex = "-1"  class="easyui-text" readonly="readonly" style="height: 22px;width:65px;background-color:#ece9d8;font-weight:bold;text-align:right;color:#245edc;"/>
	<input type="button" value="Insert" id="btnInsertSubLoss" class="easyui-button" >
	<input type="button" value="Delete" id="btnDelSubLoss" class="easyui-button" >
</div>
<div>
	<span id="err_cmbSubLoss" class="tpm-errormsg"></span>
	<span id="err_dteShiftdate" style="padding-left:265px;"class="tpm-errormsg"></span>
	<span id="err_cmbShiftid" style="padding-left:300px;"class="tpm-errormsg"></span>
	<span id="err_txtDuration" style="padding-left:380px;"class="tpm-errormsg"></span>
</div>
<div style="float:left;">
	<table id="subLossGrid"><tr><td></td></tr></table>
	<div id="subLossPager"></div>
</div>
</div>
<input type="hidden" id="hdnTotalDuration" name="hdnTotalDuration"/>
<input type="hidden" id="hdnSublossRowId" name="hdnSublossRowId"/>
<input type="hidden" id="hdnSetUpLossKeyId" name="hdnSetUpLossKeyId"/>

</form>