<script>
jQuery.noConflict();
jQuery(document).ready(function(){
	
	var url = jQuery('#hiddenUrl').val();
	initialiseForm('frmMachineRankMain');	
	
	jQuery('#submitForm').val('frmMachineRankMain');
	setTimeout(function() {viewGrid(url);},1250);   
	formatDateBox('dteMrsmEvaluationdate','dd-MMM-yyyy');
	fillComboBox("frmMachineRankMain","cmbMrsmMachineid","machineCombo.commonFilter");
	fillComboBox("frmMachineRankMain","cmbMrsmEvaluatedby","employee.commonFilter");
	 
});

function frmMachineRankMaincmbMrsmMachineid_onSelect(record)
{
	var machineid=record.id;
	
	jQuery("#hdnmachineid").val(machineid);
}
function dteMrsmEvaluationdate_onSelect(record){
	var filterString; 
	
	var date=jQuery("#dteMrsmEvaluationdate").datebox('getValue');
	
	var machine=jQuery("#hdnmachineid").val();
	filterString += '&machine='+machine;
	filterString += '&date='+date;
	
	processGridnew("MchrnkmstrMain_input.mchrnkmstr",filterString,"MchRankMain","MachineRankpager","","","","MachineRankLoadComplete","");
}
function viewGrid(url,filterString,record)
{	
	var keyid=jQuery("#txtmrsmKeyid").val();
	var Machine=jQuery("#cmbMrsmMachineid").combobox('getText');
	processGridnew("MchrnkmstrMain_input.mchrnkmstr","&keyid="+keyid+"&machine="+Machine,"MchRankMain","MachineRankpager","","","","MachineRankLoadComplete","");			
	return true;	
}
function MachineRankLoadComplete(){
	
	var keyid=jQuery("#txtmrsmKeyid").val();
	var row=jQuery("#MchRankMain").getDataIDs();
	for(id=1;id<row.length;id++){
	var value=jQuery("#txtPoints"+id).val();
	var j=row.length;
	jQuery("#txtPoints"+j).attr("disabled", "disabled");
	jQuery("#txtMrsmRankskill").attr("disabled", "disabled");
	if(keyid!=null && keyid!='' && keyid!=' '){
		
		if(value==null || value=='' || value==' '){
			jQuery("#txtPoints"+id).val('0');
		}
	}
		
	}
	setTotalRowCss("MchRankMain");
	 jQuery("#" + row[row.length]).find("td").addClass('auditRow');
	 jQuery("#txtPoints"+j).css('background-color','#fec488');

	 var getPage = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('w'),jQuery('.ui-paging-info').html().indexOf('-'));
	 getPage = getPage.substring(1);
	 var tworow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('-'),jQuery('.ui-paging-info').html().indexOf('of'));
	tworow =tworow.substring(1);
		var totrow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('of'));
		totrow =totrow.substring(2);
		tworow =tworow-1;
		totrow =totrow-1;
		jQuery('.ui-paging-info').html('View '+getPage +' - '+tworow+' of '+totrow);
	 
}
function frmMachineRankMain_successsCallback(result){
	 var keyid=result.Keyid;
	 var rank=result.Rank;
	
	 jQuery("#txtMrsmRankskill").val(rank);
		 
	 	jQuery("#txtmrsmKeyid").val(keyid);
	processGridnew("MchrnkmstrMain_input.mchrnkmstr","keyid="+keyid,"MchRankMain","MachineRankpager","","","","MachineRankLoadComplete","");
}

function frmMachineRankMain_deleteSuccessCallback(result){
	 var keyid=null;
	 processGridnew("MchrnkmstrMain_input.mchrnkmstr","keyid="+keyid,"MchRankMain","MachineRankpager","","","","MachineRankLoadComplete","");
	 
	
}
function PointScored_Formatter(idVal, options, rowObject){
	var id = options.rowId;
	var mode='I';
	var value = (idVal !=null&&idVal!=''&&idVal!=' ')?idVal:"";	
	if(value=='-1'){
		
		value='0';
	}
	return '<input id="txtPoints'+id+'" name="txtPoints'+id+'" mode="'+mode+'" onblur="lostfocus(this.id)" onfocus="gotFocuse(this.id)"  onchange="AddGridValues(\''+id + '\');" style="width:100px;text-align:right"  value="'+value+'"/>';
	
}
function lostfocus(id,hdnMode){

	var value=jQuery('#' +id).val();
	var mode="";
	
	var textval=jQuery("#hdnMode").val();
	if(value==textval)
		jQuery('#' +id).attr('mode',"I");
	else
		jQuery('#' +id).attr('mode',"U");
	
	if(value=='')
		jQuery('#' +id).val("0");
	
}
function gotFocuse(id) {
	numericTextBox(id);
	var value=jQuery('#' +id).val();
	jQuery("#hdnMode").val(value);
	
	
	var allRows = jQuery("#MchRankMain").jqGrid('getRowData');
	

}
function AddGridValues(id){
	var sum=0;
	var allRows = jQuery("#MchRankMain").jqGrid('getRowData');
	var value=jQuery("#txtPoints"+id).val();
	var maxval = jQuery("#MchRankMain").getCell(id, 'MaxMark');
	
	if(parseInt(value)>parseInt(maxval)){
		jQuery("#txtPoints"+id).val('');
		alert("Point Scored Can not be Greater Than Max Mark");
		}
	
	if(value.indexOf(".")>='1' || value.indexOf(".")=='0'){
		jQuery("#txtPoints"+id).val('');
		alert("Decimal Values are Not Allowed");
		}
	var j=allRows.length;
	for(id=0;id<allRows.length;id++){
	jQuery("#txtPoints"+id).each(function() {
		
		 if(!isNaN(this.value) && this.value.length!=0 ) {
		
			  sum += parseFloat(this.value);
         }
	 });
	}
	jQuery("#txtPoints"+j).val(sum);

}
function frmMachineRankMain_beforeSubmit(){

	var allRows = jQuery("#MchRankMain").jqGrid('getRowData');
	var j=allRows.length;
	var total=jQuery("#txtPoints"+j).val();
	var gridData  = '&MachineDatas='+convertMachineGridToJSONArr('MchRankMain');

	gridData += '&total='+total;
	
	return gridData;
		
}
function convertMachineGridToJSONArr(jqGridId){
	var id="";
	var ColValue="";
	 var colkeyid ="";
	 var Detailid="";
	var allRows = jQuery("#MchRankMain").jqGrid('getRowData');
	
	var jsonArrO='[';	
	for( var i = 1; i<allRows.length;i++){
		id="txtPoints"+i;
		
		ColValue=jQuery('#' +id).val();
		if(ColValue=="0")
			ColValue='-1';
		
		var row = allRows[i];
		var keyid=jQuery("#txtmrsmKeyid").val();
		var rowData = jQuery("#MchRankMain").jqGrid('getRowData',i);	
		 var gridData= jQuery("#MchRankMain").jqGrid('getRowData',i);
		var Mode=jQuery('#' +id).attr('mode');
		colkeyid = rowData.Keyid;
		 Detailid=rowData.txtMrsdKeyid;
		
		if((ColValue!=null && ColValue!="")){
			if(Mode=="U" ){ 
				jsonArrO += '{';
				jsonArrO += '"cmbMrsdMasterid":"' + keyid +'",';
				jsonArrO += '"txtMrsdKeyid":"' + Detailid +'",';
				jsonArrO += '"txtMrsdParameterid":"' + colkeyid +'",';
				jsonArrO += '"txtMrsdPointsscored":"' + ColValue +'"},';
			}
				}
		else  {
					if(Detailid.trim().length>0){
						if(Mode=="U"){ 
						jsonArrO += '{';
						jsonArrO += '"cmbMrsdMasterid":"' + keyid +'",';
						jsonArrO += '"txtMrsdKeyid":"' + Detailid +'",';
						jsonArrO += '"txtMrsdParameterid":"' + colkeyid +'",';
						jsonArrO += '"txtMrsdPointsscored":"' + ColValue +'"},';
						}
							}
			}
		
	}
	
jsonArrO = jsonArrO.slice(0,-1)+ "]";
jsonArrO = (jsonArrO != ']'?jsonArrO:"");	
	

	return jsonArrO; 	
}
</script>

<form id="frmMachineRankMain">
<div id="wrapper">
		<table>
			<tr>
				<td style="width: 1%">
				<input type="hidden" id="hdnMode" name="hdnMode" value=""/>
				<input type="hidden" id="hdnDelete" name="hdnDelete" value=""/>
				<input type="hidden" id="hdnmachineid" name="hdnmachineid" value=""/>
				<div style="display: none"><input type="hidden" class="easyui-text" id="txtmrsmKeyid" name="txtmrsmKeyid" value="${requestScope.GenTlMchranksheetmst.mrsmKeyid}"/></div>
						
							<label class="mandatory-lbl">Machine</label>
								
						<div class="easyui-paddingfbx" >
							<input  id="cmbMrsmMachineid" name="cmbMrsmMachineid" class="easyui-combobox" style="width: 250px;  " value="${requestScope.GenTlMchranksheetmst.mrsmMachineid}"/>
							
							
							</div>
							<label class="mandatory-lbl" >Evaluated By</label>	
							<div class="easyui-paddingfbx"  >
								<input  id="cmbMrsmEvaluatedby" name="cmbMrsmEvaluatedby" class="easyui-combobox" style="width:250px;"value="${requestScope.GenTlMchranksheetmst.mrsmEvaluatedby}"/>
								
							</div>
				</td>
				<td valign="top" style=" width : 158px;padding-left:30px;">
					<label class="mandatory-lbl" >Evaluation Date</label>
					<div class="easyui-paddingfbx" style="width:150px\9;" >
						<input type="text" id="dteMrsmEvaluationdate"  name="dteMrsmEvaluationdate" class="easyui-datebox" style="width: 150px;" value="${requestScope.GenTlMchranksheetmst.mrsmEvaluationdate}"/>
					</div>
					<div class="easyui-paddingfbx" style="margin-top: 12%;margin-top:20px\9;width:150px\9">
					<input type="text" id="txtMrsmRankskill" name="txtMrsmRankskill"  class="easyui-text" value="${requestScope.GenTlMchranksheetmst.mrsmRankskill}"/>
					</div>
				</td>
					<td valign="top">
					<div style="padding-left:30px;">
						<div style="vertical-align: top">
							<label style="vertical-align: top">Remarks</label>
						</div>
						<div class="easyui-paddingfbx">
							<span style="vertical-align: top;" >
								<textarea class="txtarea" id="txtMrsmRemarks" name="txtMrsmRemarks" rows="1" style="width:323px;height: 62px;" cols="3">${requestScope.GenTlMchranksheetmst.mrsmRemarks}</textarea>
							</span>
						</div>
					</div>
					</td>
			</tr>
		</table>

</div>
 <div id="wrapperRpt" style="margin-left:44px;margin-top: -0.2%">
 <table id="MchRankMain" ></table>
			<div id="MachineRankpager"></div>
 </div>
 
 <input type="hidden" id="mode" name="mode" value=""/>
</form>