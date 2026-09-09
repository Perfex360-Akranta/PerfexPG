<script type="text/javascript"><!--
jQuery(document).ready(function(){
	
	initialiseForm('frmuniqueposition');
	jQuery('#submitForm').val('frmuniqueposition');
	fillComboBox("frmuniqueposition","cmbuniqueposition","roleMst.commonFilter?&childFlids=N");  
	/* for functionalLocation*/
	var factId = jQuery("#frmuniqueposition input[id='factory']").val();
	var sectionId = jQuery("#frmuniqueposition input[id='section']").val();
	var cellId = jQuery("#frmuniqueposition input[id='cell']").val();
	var machId = jQuery("#frmuniqueposition input[id='machine']").val();
	var flid = jQuery("#frmuniqueposition input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("uniquePosfunLocation","functionalLoc.topi","uniqPosfunLocationValues","frmuniqueposition",dataStr);
	
	//viewGrid("&flid="+flid);
	//processGridnew("UniquePositionform_input.topi","?q=2&flid="+flid,"upEmployeegrid","pageremp","","uniquedoubleclick","","empload_complete");
	//processGridnew("UniquePositionSelectd_input.topi","?q=2&flid="+flid+"&upid="+upid,"selectedRoleGrd","rolepageremp","","uniqueempdoubleclick","","upempload_complete");
	var rolekeyid = jQuery("#hdnroleKeyid").val();//hdnUserRole 
	
    if(rolekeyid.trim().length<=0){
  //alert("length of rolekeyid " + rolekeyid.length);
       disableField('frmuniqueposition','cmbuniqueposition');
    }
    jQuery('#btnshowSaveEmp').click(function(){
    	saveForm("frmuniqueposition","upEmployee_save.topi");
    });
    jQuery('#btnDelSaveEmp').click(function(){
    	deleteRecord("frmuniqueposition","upEmployee_delete.topi");
    });
    jQuery("#cmbuniqueposition").combobox({onRequest:function( ){
		var flid = jQuery("#frmuniqueposition input[id='flid']").val();
		return "flid="+flid;
  	}}); 

    //jQuery("#txtRoleName").css('text-transform', 'uppercase');
});
function frmuniqueposition_successsCallback(result){
	var roleKey = result.roleKeyid;
	var flid    = result.flid;
	jQuery('#hdnroleKeyid').val(roleKey);
	//setFieldValue("cmbuniqueposition", roleKey,"frmuniqueposition");
	enableFields("cmbuniqueposition");
	viewGrid("&upid="+roleKey+"&flid="+flid);
	
	//viewGrid("&flid="+keyIds.flId);
	
	//loadFunctionalLocation("uniquePosfunLocation","functionalLoc.topi","uniqPosfunLocationValues","frmuniqueposition","&flid="+flid);
}

function  frmuniquepositioncmbuniqueposition_onLoadSuccess()
{ 
	
	//alert(jQuery("#cmbuniqueposition").combobox('getText'));
	

	var rolekeyid = jQuery("#hdnroleKeyid").val();
	
	if (getFieldValue("cmbuniqueposition").length<=1) {
		setFieldValue("cmbuniqueposition", rolekeyid);
		setTimeout(function() { 
			var rcData= jQuery('#cmbuniqueposition').combobox('getText');
			var unqData = rcData.split("-");
			jQuery('#txtRoleName').val(unqData[0]);
			jQuery('#hdnroleKeyid').val(rolekeyid);
			var flid = jQuery("#frmuniqueposition input[id='flid']").val();
			//viewGrid("&upid="+rolekeyid+"&flid="+flid);
		},300);
	}
	if (jQuery("#cmbuniqueposition").combobox('getText').contains('ROL')) {
		jQuery("#cmbuniqueposition").combobox('clear');
		jQuery('#txtRoleName').val('');
	}	
}
function  frmuniquepositioncmbuniqueposition_onSelect(record)
{ 
	var unqData = record.text.split("-");
	jQuery('#txtRoleName').val(unqData[0]);
	jQuery('#hdnroleKeyid').val(record.id);
	var flid = jQuery("#frmuniqueposition input[id='flid']").val();
	viewGrid("&upid="+record.id+"&flid="+flid);
}


function frmuniqueposition_FuntLocHierarchy_SuccessCallBack(keyIds)
{
    jQuery("#cmbuniqueposition").combobox('setValue',"");
	jQuery('#txtRoleName').val('');
	if(undefined == keyIds.flId)
    keyIds.flId =  jQuery("#frmuniqueposition input[id='flid']").val();
	viewGrid("&flid="+keyIds.flId);
	reloadCombo("frmuniqueposition","cmbuniqueposition","roleMst.commonFilter?&flid="+keyIds.flId+"&childFlids=N");
}
function frmuniqueposition_deleteSuccessCallback(result){
	alert(result.successData.msg);
	jQuery('#selectedRoleGrd').trigger("reloadGrid");
	jQuery('#upEmployeegrid').trigger("reloadGrid");
}
function viewGrid(filterStr){
	//var upid = getFieldValue("cmbuniqueposition");
	var upid = jQuery("#hdnroleKeyid").val();
	processGridnew("UniquePositionform_input.topi","?q=2"+filterStr,"upEmployeegrid","pageremp","","uniquedoubleclick","","empload_complete");
     
	if(upid.trim().length>0)
		filterStr+="&upid="+upid;
	processGridnew("UniquePositionSelectd_input.topi","?q=2"+filterStr,"selectedRoleGrd","rolepageremp","","uniqueempdoubleclick","","upempload_complete");
}
function empload_complete(ids){ 
	var row = jQuery("#upEmployeegrid").jqGrid('getDataIDs');
	var cm = jQuery("#upEmployeegrid").jqGrid("getGridParam", "colModel");
	var comboid = getFieldValue("cmbuniqueposition");	
	if(comboid.trim().length==0)
		comboid = jQuery('#hdnroleKeyid').val();
	 for(var i=0;i<row.length;i++)
	 {
		var roleid = jQuery("#upEmployeegrid").jqGrid('getCell',row[i],"ROLE_NAME");
		 
		if( roleid.trim().length>0)
			{
			 
			//jQuery("#jqg_upEmployeegrid_"+row[i]).attr('disabled','disabled');
			 for(var j=3;j<cm.length;j++)
			 {  
				 var celVal = jQuery("#upEmployeegrid").jqGrid('getCell',row[i],cm[j].name);
				jQuery("#upEmployeegrid").jqGrid('setCell',row[i],cm[j].name,celVal,{ 'background-color':'#74F7C2'});
			 }
			}
	 } 
}

function frmuniqueposition_beforeSubmit()
{	
	if(getGridSelectArray("upEmployeegrid").trim().length>0){  
		var elementType = jQuery("#frmuniqueposition input[name='hdnRoleelementType']").val();
    	return "&employee="+getGridSelectArray("upEmployeegrid")+"&elementType="+elementType;
	}else
	{
		alert("No Data Selected To Save");
		return false;
	}
}

function frmuniqueposition_beforeDelete()
{	
	if(getGridSelectArray("selectedRoleGrd").trim().length>0){
      return "&remEmployee="+getGridSelectArray("selectedRoleGrd");
	}else
	{
		alert("No Data Selected To Remove");
		return false;
	}
}
</script>
<form id="frmuniqueposition" name="frmuniqueposition">
	<div id="wrapper" >
	<div style=" ">
		<table>
			<tr>
				<td colspan='3'>
					<div  id="frmuniquepositionFuntKeyIds">
					 	<input type="hidden" id="location" name="hdnLocationid" value=""  ></input>
						<input type="hidden" id="factory" name="hdnFactoryid" value=""  ></input>
						<input type="hidden" id="section" name="hdnSectionid" value=""  ></input>
						<input type="hidden" id="cell" name="hdnCellid" value=""  ></input>
						<input type="hidden" id="machine" name="hdnMachineid" value=""  ></input>
						<input type="hidden" id="flid" name="hdnRoleFlid" value="${requestScope.gentlrolemst.roleFlid}"  ></input>
						<input type="hidden" id="elementId" name="hdnRoleElementId" value="${requestScope.gentlrolemst.roleElementId}"  ></input>
						<input id="elementType" type="hidden" name="hdnRoleelementType" style="cursor: default;">
					</div>
				 	<div id="uniquePosfunLocation" style="width:152%;"></div>
				</td>
		    </tr>
			<tr>
				<td valign="top"  style="width:42%;">
							<div class="easyui-paddingbfpx">
								<label >Unique Position</label>
							</div>
							<div>
								<input class="easyui-combobox" id="cmbuniqueposition" name="cmbuniqueposition"  value="${requestScope.gentlrolemst.roleKeyid}" style=" width : 400px;"  />
							</div>
				</td>				
			 
				<td style="width:42%;">
					<div class="easyui-paddingbfpx">
								<label class="mandatory-lbl">Unique Position Name</label>
							</div>
							<div>
								<input class="easyui-text" id="txtRoleName" name="txtRoleName"  value="${requestScope.gentlrolemst.roleName}" style=" width : 400px;text-transform: "  />
							</div>
				</td>
				<td style="display: none;padding-left: 100px;">
					<div class="easyui-paddingbfpx" >
								<label >Unique Code</label>
							</div>
							<div style="padding-left: 20px;">
								<input class="easyui-text" id="txtRoleCode" name="txtRoleCode"  value="${requestScope.gentlrolemst.roleCode}" style=" width : 200px;text-transform: "  />
							</div>
				</td>
			</tr>
	</table>

<div style="width:95%;position:relative;">
	
	<div style="float:left;">
		 <table id="selectedRoleGrd">
			<tr> <td> </td> </tr>
		 </table>
		 <div id='rolepageremp'></div>
		 <span style="margin-left:2%;position:absolute;top:120;right:48%;">
		 	<input type="button" class="easyui-button" value="<<"  id='btnshowSaveEmp' style="font-weight:normal;height:21px;"/>
		 </span>
		 <span style="margin-left:2%;position:absolute;top:155;right:48%;">
		 	<input type="button" class="easyui-button" value=">>"  id='btnDelSaveEmp' style="font-weight:normal;height:21px;"/>
		 </span>
	</div>
	<div style="float:right;">
		<table id="upEmployeegrid">
			<tr> <td> </td> </tr> 
		</table>
		<div id='pageremp'></div>
		</div>
	</div>
</div>
		</div>
		<input type="hidden" id="mode" value=""/>
<input type="hidden" id="hdnroleKeyid" name="hdnroleKeyid" value="${requestScope.gentlrolemst.roleKeyid}"/>
</form>