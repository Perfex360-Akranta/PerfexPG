<script>
jQuery(document).ready(function(){		
	initialiseForm('frmEmployeeNomination');
	jQuery('#submitForm').val('frmEmployeeNomination');
	var flid = 	jQuery('#hdnNomFlidId').val();
	var url = jQuery('#hiddenUrl').val();	
	fillComboBox("frmEmployeeNomination","cmbNommProgKeyid","program.commonFilter?flid="+flid );
	fillComboBox("frmEmployeeNomination","cmbNommBachKeyid","Batch.commonFilter?frmmode=nomination" );
	fillComboBox("frmEmployeeNomination","cmbNommPreparedBy","employee.commonFilter?flid="+flid );
	fillComboBox("frmEmployeeNomination","cmbNoddApprovedBy","employee.commonFilter?flid="+flid );
	formatDateBox('dteNommPreparedDate','dd-MMM-yyyy');
	formatDateBox('dteNoddApprovedDate','dd-MMM-yyyy');
	if (getFieldValue('dteNommPreparedDate').trim().length<=0){fillWithCurrentDate('dteNommPreparedDate');}
	if (getFieldValue('dteNoddApprovedDate').trim().length<=0){fillWithCurrentDate('dteNoddApprovedDate'); }
	//readOnlyFields('cmbNommProgKeyid');
	readOnlyFields('cmbNommBachKeyid');

	var nommMode = jQuery('#hdnNommMode').val();	
	if(nommMode=="approval"){
		readOnlyFields('cmbNommPreparedBy');
		readOnlyFields('dteNommPreparedDate');
		readOnlyFields('txtNommRemarks');
		jQuery("#divApproval").css("display","block");
	}
	url += "&flid="+flid;	
	processGridnew(url, "&q=1","employeenomgrid", "employeenompager", "", "","","onLoadComplete");	 
});

function employeenomgrid_selectRow(rowId)
{
	var nommMode = jQuery('#hdnNommMode').val();	
	var jqGridId="employeenomgrid";
	if(nommMode=="request"){
		var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'hdnNoddKeyid');
		if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (keyId.trim().length>0))
		{			
			jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','Y');
		}
		else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
			//alert('checked==true');
			//alert("checked");
			checkMaxCapacity();
			jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','N');			
		}
		else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete',' ');}
	}
	else if(nommMode=="approval"){
		var isApproved = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtNoddIsapproved');	
		if((jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == false) && (isApproved=="Y"))
		{			
			jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','Y');
		}
		else if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
			//alert('checked==true');
			jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete','N');
		}
		else{jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'hdnIsDelete',' ');}
	}	
	
}



function onLoadComplete()
{	
	var nommMode = jQuery('#hdnNommMode').val();	
	var jqGridId="employeenomgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;	
			if(nommMode=="request"){
				var keyId = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'hdnNoddKeyid');			
				if (keyId.trim().length>0){
					//jQuery("#jqg_"+jqGridId+"_"+rowId).setSelection(allRows[i], true);
					jQuery('#'+jqGridId).setSelection(rowId, true);					
					jQuery("#jqg_"+jqGridId+"_"+rowId).attr('checked',true);
				}
				//alert(keyId);
			/*	if(keyId == "1")
					jQuery("#"+jqGridId).jqGrid('setCell',rowId, 'hdnIsDelete',"B"); */	
			}
			else if(nommMode=="approval"){
				var isApproved = jQuery("#"+jqGridId).jqGrid('getCell',rowId, 'txtNoddIsapproved');			
				if (isApproved=="Y"){
					jQuery('#'+jqGridId).setSelection(rowId, true);					
					jQuery("#jqg_"+jqGridId+"_"+rowId).attr('checked',true);
				}
			}			
		}	
	}
	
}

function frmEmployeeNomination_beforeDelete()
{  
	if (jQuery("#txtNommKeyid").val().length>0)
		return true;
	else
		return false;
}

function checkMaxCapacity(){
	//alert("checkMaxCapacity");
	var jqGridId="employeenomgrid";
	var cnt=0;
	var maxCapacity=jQuery("#txtMaxCapacity").val();
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;
			//alert("rowId:"+rowId);
			if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
				cnt=parseInt(cnt)+1;
				//alert("cnt:"+cnt);
				if (parseInt(cnt)>parseInt(maxCapacity)){
					alert("Maximum Students Allowed Is " + maxCapacity);
					jQuery("#jqg_"+jqGridId+"_"+rowId).attr('checked',false);
					jQuery("#"+jqGridId).setSelection(rowId,false);
					return false;
				}
			}	
		}
	}
	
}
function frmEmployeeNomination_beforeSubmit()
{	
	//alert('frmEmployeeNomination_beforeSubmit:');  
	var flg=false;
	var errText="";
	var jqGridId="employeenomgrid";
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	if(allRows.length>0){
		for( var i = 0; i < allRows.length;i++){
			var rowId=	parseInt(i)+1;
			if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){
				flg=true;
			}	
		}
	}
	
	if (flg==false){
		setTimeout(function() {
			showCommonErrorMsg('No Employee Selected To Nominate');
		}, 200);
		div_err();		
        return false;
	}
	
	var gridval=getGridSelectArray('employeenomgrid');  
    var gridData  = '&nomdtls='+gridval;	
    //alert('gridData:'+gridData);
    var gridvaldel=getGridUnselectedKeyids('employeenomgrid','hdnNoddKeyid','hdnIsDelete');
    gridData += '&nomdtlsdel='+gridvaldel;

    if (gridval.trim().length<=0 && gridvaldel.trim().length<=0) {
    	setTimeout(function() {
			showCommonErrorMsg('No Employee Selected To Nominate');
		}, 200);
		div_err();		
        return false;
    } 
    //alert('gridval:'+gridval);
    
    
	return gridData; 	
	/*setTimeout(function() {
		showCommonErrorMsg(errText);
	}, 200);
	div_err();*/	
}

function frmEmployeeNomination_successsCallback(result)
{
	//if (jQuery("#txtNommKeyid").val().length>0)
		jQuery("#employeenomgrid").trigger("reloadGrid");  
	/*else		
		jQuery("#employeenomgrid").clearGridData();*/
} 

function frmEmployeeNomination_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	
	jQuery("#employeenomgrid").trigger("reloadGrid");
	jQuery("#employeenomgrid").clearGridData();
	  
}
</script>

<form name="frmEmployeeNomination" id="frmEmployeeNomination">
	<div style="padding-top: 0px;padding-left:0px;">	
		<table>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" style="padding-left:0px;">
							<div><label class="mandatory-lbl">Program</label></div>
							<div>
								<input id="cmbNommProgKeyid" name="cmbNommProgKeyid" class="easyui-combobox"  style="width: 265px;"
																							value="${requestScope.entTlNominationmst.nommProgKeyid}"/>
							</div>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:0px;">
							<div><label class="mandatory-lbl">Session</label></div>
							<div>
								<input id="cmbNommBachKeyid" name="cmbNommBachKeyid" class="easyui-combobox"  style="width: 265px;"
																							value="${requestScope.entTlNominationmst.nommBachKeyid}"/>
							</div>
					</div>
				</td>
				<td>		
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
						<div><label class="mandatory-lbl">Prepared By</label></div>
						<div>
							<input id="cmbNommPreparedBy" name="cmbNommPreparedBy" class="easyui-combobox"  style="width: 265px;"
																			value="${requestScope.entTlNominationmst.nommPreparedBy}"/>
						</div>
					</div>
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
						<div><label class="mandatory-lbl">Date</label></div>
						<div>
							<span style="float:left;padding-right:0px;">					
						 		<input class="easyui-datebox" style=" width : 87px;" id="dteNommPreparedDate" name="dteNommPreparedDate" 
						 													value="${requestScope.entTlNominationmst.nommPreparedDate}"/>
					      	</span>
						</div>
					</div>
				</td>
				<td>				
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
						<div><label class="lbl">Remarks</label></div>
						<div>
							<textarea id="txtNommRemarks" name="txtNommRemarks" style="resize:none;width:235px;" maxlength="100" 
																			value="${requestScope.entTlNominationmst.nommRemarks}">${requestScope.entTlNominationmst.nommRemarks}</textarea>
						</div>
					</div>
				</td>
			</tr>			
		</table>
		<div id="divApproval" style="display:none;">
			<table >
			<tr>
				<td valign="top" style=" width : 0px;">
					<div class="easyui-paddingbfpx" style="padding-left:0px;">
						<div><label class="mandatory-lbl">Approved By</label></div>
						<div>
							<input id="cmbNoddApprovedBy" name="cmbNoddApprovedBy" class="easyui-combobox"  style="width: 265px;"
																			value="${requestScope.entTlNominationdtl.noddApprovedBy}"/>
						</div>
					</div>
				</td>
				<td valign="top" style="margin-left:10%;width:268px;">
					<div class="easyui-paddingbfpx" style="padding-left:120px;">
						<div><label class="mandatory-lbl">Approved Date</label></div>					
						<span style="float:left;padding-right:0px;">					
					 		<input class="easyui-datebox" style=" width : 87px;" id="dteNoddApprovedDate" name="dteNoddApprovedDate" 
					 													value="${requestScope.entTlNominationdtl.noddApprovedDate}"/>
				      	</span>
					</div>
				</td>
				<td valign="top">
					<div class="easyui-paddingbfpx" style="padding-left:236px;">
						<div><label class="lbl">Approved Remarks</label></div>
						<div>
							<textarea id="txtNoddApprovedRemarks" name="txtNoddApprovedRemarks" style="resize:none;width:235px;" maxlength="100" 
																			value="${requestScope.entTlNominationdtl.noddApprovedRemarks}">${requestScope.entTlNominationdtl.noddApprovedRemarks}</textarea>
						</div>
					</div>
				</td>
			</tr>	
			</table>
		</div>		
		<table id='employeenomgrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='employeenompager'></div>
	</div>
	<input type="hidden" id="mode" name="mode" value=""/>
	<input type="hidden" id="txtNommKeyid" name="txtNommKeyid" value="${requestScope.entTlNominationmst.nommKeyid}" />
	<input type="hidden" id="txtMaxCapacity" name="txtMaxCapacity" value="${requestScope.maxCapacity}" />
	<input type="hidden" id="hdnNommMode" name="hdnNommMode" value="${requestScope.nommMode}"/>
	<input type="hidden" id="hdnNomFlidId" name="hdnNomFlidId" value="${requestScope.flid}"/>
	<!--<div style="padding-left: 43%; padding-top: 5px;">
		<input type="button" class="easyui-button" id="btnAdd" name="btnAdd" value="Save" style=" height : 20px;"/>
		<input type="reset" class="easyui-button" id="btnclear" name="btnclear" value="Clear" style=" height : 20px;"/>
	</div>-->
</form>