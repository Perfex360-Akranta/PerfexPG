<!-- Created By :Siddharth.A -->
<script type="text/javascript">

jQuery.noConflict();
	jQuery(document).ready(function(){

		var url = jQuery('#hiddenUrl').val();
		initialiseForm('frmeqpMachineLink');	
		jQuery('#submitForm').val('frmeqpMachineLink'); // set the id of form to submit
		var eqpgrpId=url.substring(url.indexOf("&eqpgmKeyid")+"&eqpgmKeyid".length+1 );
		
		var index=url.substring(url.indexOf("&eqpgmKeyid")+"&eqpgmKeyid".length+1 ).indexOf('&');
	
	/*	if( index !=-1)
			jQuery('#hdneqpgrpId').val(eqpgrpId.substring(0,index));
		else
			jQuery('#hdneqpgrpId').val(eqpgrpId);	
		
		
		/*jQuery('#hdnFactoryId').val(url.substring(url.indexOf("&factId")+"&factId".length+1 ));
		if(url.indexOf('ASM')>0)
		{processGridnew("eqpAssmblyData_input.eqg","?eqpgmKeyid="+jQuery('#hdneqpgrpId').val(),"EquipmentGrpLnk","pager","","","","EquipmentGrpLnkOnLoad");jQuery('#hdnLinkType').val('ASM');}
		else if(url.indexOf('EQP')>0)
		{processGridnew(url,"&factId="+jQuery('#hdnFactoryId').val(),"EquipmentGrpLnk","pager"); jQuery('#hdnLinkType').val('EQP');jQuery("#btnAdd").css("display","none");}
		else if(url.indexOf('ALM')>0)
		{processGridnew(url,"&row=2","EquipmentGrpLnk","pager"); jQuery('#hdnLinkType').val('ALM');}
		else if(url.indexOf('SAM')>0)
		{processGridnew(url,"&row=2","EquipmentGrpLnk","pager"); jQuery('#hdnLinkType').val('SAM');}
		*/

		processGridnew("eqpgroupLink_input.eqg","&factId="+jQuery('#hdnFctId').val()+"&formField="+jQuery('#hdnLinkType').val()+"&eqpgmKeyid="+jQuery('#hdneqpgrpId').val(),"EquipmentGrpLnk","pager","","","","EquipmentGrpLnkOnLoad");

		if(jQuery('#hdnLinkType').val()=="EQP")
		{	
		 	jQuery("#btnAdd").css("display","none");
		 	jQuery('#titlebar').html("Equipment");
		}
		else if(jQuery('#hdnLinkType').val()=="ALM")
		{
			jQuery('#titlebar').html("Alarm");	
		}	 
			
			
});

jQuery('#btnAdd').click(function(){
	var type=jQuery('#hdnLinkType').val();
	//navigateToNextForm("assembly_input.asb");
	loadMasterForm("loadmst_grid.gnms?q=2&menuCaption=BDAlarm&menuName=MNUMASBDALARM&isMMC=Y&loadFormArg=MNUMASBDALARM%3F",frmMode.create,'','Functional Loaction');	
	
});


jQuery('#btnSave').click(function(){
	saveForm("frmeqpMachineLink","eqpgroupLink_save.eqg");
	
});

function  frmeqpMachineLink_beforeSubmit()
{
	var gridData ='&GenTlEqpgrouplink='+JqGridToJsonSelectdRows('EquipmentGrpLnk','my_checkbox','txtSelectedRow');
		gridData+='&linkType='+jQuery('#hdnLinkType').val()+'&eqpgmKeyid='+jQuery('#hdneqpgrpId').val();			
	return gridData; 
}


function eqpAssmcboxFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input id="my_checkbox'+ rowId +'" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){assmMultiSelect(\''+rowId + '\');}else   {asmUncheck(\''+ rowId +'\')}"/>';
}

function assmMultiSelect(id)
{
	jQuery('#EquipmentGrpLnk').setCell(id,"txtSelectedRow","INSERT");
	
}

function asmUncheck(id)
{
	if(jQuery('#hdnLinkType').val()=="ALM")
	{	jQuery('#EquipmentGrpLnk').setCell(id,"txtSelectedRow","DELETE");}
	else
		jQuery('#EquipmentGrpLnk').setCell(id,"txtSelectedRow"," ");

	if(jQuery('#hdnLinkType').val()=="EQP")
	{	if(!(jQuery('#my_checkbox'+id).is(':checked')))
			jQuery('#my_checkbox'+id).attr('checked','checked');
	}
}

function EquipmentGrpLnkOnLoad()
{
	
/*	var rowIds = jQuery('#EquipmentGrpLnk').jqGrid().getDataIDs();
	for(var i=0;i<rowIds.length;i++)
	{	//jQuery("#EquipmentGrpLnk").setCell(rowIds[i],"txtSelectedRow","MCH M ");
		if(jQuery('#my_checkbox'+rowIds[i]).is(':checked'))
			jQuery('#my_checkbox'+rowIds[i]).attr({'checked':false});
				

				
	})*/
}

function frmeqpMachineLink_successsCallback(result)
{}

function frmeqpMachineLink_exceptionCallback()
{}

function frmeqpMachineLink_errorCallback()
{}


</script>


<form id="frmeqpMachineLink">
<!-- <div align="center"><label style="font-weight: bold;font-size: 14px;">Equipment Group Link</label></div> -->


	<div class ="floatright" style="padding-right: 22px;">
	<!--<span><input type="button" id="btnAdd" class="easyui-button" value="Add" /> </span> -->
		<span ><input type="button" id="btnSave" class="easyui-button" value="Save" /> </span>
	<!--<span><input type="button" id="btnClose" class="easyui-button" value="Close" /> </span> -->
	
	</div>
	<div class="clear"></div>
<div id="headin" class="sub-header" style=" margin-bottom: 10px;width:99%;">
<span id="lblHeader" style="font-size:15px;font-weight:bold;"><label id="titlebar">Alarm</label></span>
</div>

	<div>
	<table id="EquipmentGrpLnk" style="width:100%">
		<tr><td/></tr></table>
		<div id="pager"></div>
		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
		<input type="hidden" id="hdnLinkType" value="${requestScope.linkType}" />
		<input type="hidden" id="hdneqpgrpId" value="${requestScope.eqpgmKeyid}"/>
		<input type="hidden" id="hdnFctId" value="${requestScope.factId} "/>
	</div>	
	
</form>