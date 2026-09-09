
<script type="text/javascript">
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmTrainging'); 	
	processGridnew("WhyWhyTrainingList_view.why","?q=2","trainingGrid","trainingPager","","trainingDblClick","","trainingGrid_onComplete");
	
	var factId = jQuery("#frmTrainging input[id='factory']").val();
	var sectionId = jQuery("#frmTrainging input[id='section']").val();
	var cellId = jQuery("#frmTrainging input[id='cell']").val();
	var machId = jQuery("#frmTrainging input[id='machine']").val();
	var flid = jQuery("#frmTrainging input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
 	
	loadFunctionalLocation("traingingfunlocation","functionalLoc.commonFilter","traingingfunlocationValues","frmTrainging",dataStr);	
});
function trainingDblClick(id){
	var rowData = jQuery("#trainingGrid").jqGrid('getRowData',id);		
	var url="topi_input.topi";
	var topiKeyid = rowData.parentId;	
	LoadPopUp("divShowTrAreaPopup", url+"?keyId=" + topiKeyid , true,"60%","60%","15%","20%", "multiSelectOk_Callback","Topic Entry",false);
}
function trainingGrid_onComplete(result){
	var elmType = jQuery('#hdnElmType').val();
	if(elmType != null && elmType != ' ' && elmType != '' && elmType == 'TOP')
	{
		if(screen.width >= 1300)		
		 {
			jQuery('#trainingGrid').setGridWidth(740);
		 }
	}
} 

function cboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
  	return '<input  type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
}

function selectData(rowId){
	jQuery("#trainingGrid").setCell(rowId, "chkFlg","1");
	var selTopic = jQuery("#trainingGrid").jqGrid('getCell',rowId,'keyId');
	jQuery('#hdnTopiId').val(selTopic);
}

function unselectData(id){
	jQuery("#trainingGrid").setCell(id, "chkFlg"," ");	
}

function loadCompletePillarGrid(){
	jQuery(":input[type=checkbox]").removeAttr('disabled');	
}

jQuery("#btndlgSave").click( function()
{
	var url="triningProgram_save.why";	
	alert("Data Saved Successfully");
	//saveForm("frmTrainging",url);	
});

jQuery('#btndlgClose').click( function()
{
	closePopUpDialoge("divShowTrArea");	
});
jQuery('#btndlgDelete').click( function()
{
	//closePopUpDialoge("divShowTrArea");
	var topiId = jQuery('#hdnTopiId').val();//alert(topiId);
	if(topiId.trim().length>0)
		deleteRecord("frmTrainging","trArea_Delete.trArea?topicid="+topiId);
	else
		alert('Select Topic');	
});	


jQuery( "#btndlgNew" ).click(function() {
	var persistData = null; 
	jQuery('#delSpn').css('display','none');
	//openMasterForm('loadmst_grid.gnms?q=2&menuCaption=SpokeMaster&menuName=MNUETSPOKEMST&isMMC=Y&loadFormArg=MNUETSPOKEMST',frmMode.create,'frmTrainging','','mstFrm');
	LoadPopUp("divShowWhyTrainingPopup", "WhyWhyTraining_input.why?q=2&menuCaption=ProgramMaster&menuName=MNUETPROGRAMMST&isMMC=Y&loadFormArg=MNUETPROGRAMMST" , true,"31%","45%","30%","33%", "mstFrm","Program Entry",false);
	jQuery('#newMstFrm').css("z-index","2222");
	
});


jQuery( "#btnOK" ).click(function() {
	
	
	closePopUpDialoge("OPL",true);
});



jQuery(document).keydown(function(e) {
    if (e.keyCode == 27) {
    	jQuery("#newMstFrm").hide(0);
    }    
    jQuery('#newMstFrm').focusout(function() { 
 });
});
function frmTrainging_deleteSuccessCallback(result){
	alert(result.retmsg);
	if( result.retmsg == "Data Deleted Successfully")
		jQuery("#trainingGrid").trigger("reloadGrid");
}
function divShowWhyTrainingPopup_onClose()
	{
	
	return true;
	}
function  frmTrainging_beforeSubmit(){
		//return 'trainingGrid='+getSelectdRows("trainingGrid","operator_checkbox","chkFlg");
		//alert("beforeSubmit");	
		
		/*//var tableDatas = jQuery("#trainingGrid").jqGrid('getRowData');	
		if(tableDatas.length==0)	{
			alert("Select Atleast One Row");
			return false;
		}
		var returnVal=false;		
		var rowIds = jQuery("#trainingGrid").jqGrid('getDataIDs');

		for( var i = 0; i < rowIds.length;i++){
			var isAdd= jQuery("#trainingGrid").jqGrid('getCell',rowIds[i],'chkFlg');
			if (isAdd=='1')
			{				
				returnVal=true;
			}				
		}
		
		if (returnVal==false){
			alert("Select Atleast One Row");
			return false;
		}
		
		var gridData  = '&multiplemethods='+convertJqGridToJSONStringArrLocal('trainingGrid');	
		return gridData;*/ 	
}
function convertJqGridToJSONStringArrLocal(jqGridId){	
	var rowIds = jQuery("#"+jqGridId).jqGrid('getDataIDs');
	//alert(rowIds[0]);
	var rowObject = jQuery("#"+jqGridId).getRowData(rowIds[0]);
	var totalCol = 0;
	for(var col in rowObject) totalCol++;
	
	var jsonArrO='[';
	//alert("totalCol " + totalCol);
	for( var i = 0; i < rowIds.length;i++){
		
		var isAdd= jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'chkFlg');
		if (isAdd=='1')
		{
			jsonArrO += '{';
			var parentId=jQuery('#txtTrarParentid').val();
			var locationId=jQuery('#txtTrarLocationid').val();
			var elementType=jQuery('#txtTrarElementtype').val();
			var refType=jQuery('#txtRefType').val();
			var Levelno=jQuery('#txtTrarLevelno').val();
			
			//alert(jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'name'));
			//alert(parentId);
			jsonArrO += '"txtTrarParentid":"' + parentId +'",';
			jsonArrO += '"txtTrarLocationid":"' + locationId +'",';
			jsonArrO += '"txtTrarElementtype":"' + elementType +'",';
			jsonArrO += '"txtTrarReftype":"' + refType +'",';
			jsonArrO += '"txtTrarLevelno":"' + Levelno +'",';
			jsonArrO += '"txtTrarRefid":"' + jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'keyId') +'",'; 
			jsonArrO += '"txtTrarName":"' + jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'name').replace('&','AND') +'",';
			//jsonArrO += '"Qaplkeyid":"' + jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],'QAPLKeyId') +'",'; 		
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	return jsonArrO; 
}

function frmTrainging_successsCallback(result){	
	//alert("suc");		
	closePopUpDialoge("divShowWhyTrainingPopup");	
	
}

jQuery("#chkOPL").click(function(){

	if(jQuery('#chkOPL').is(':checked') == true){
		jQuery("#chkTRN").attr("checked",false);
		LoadPopUp("OPL","create_input.opl", true, "88%", "82%", "7%", "6%", " ", "One Point Lesson","");
		//navigateToNextForm("create_input.opl","One Point Lesson");
	}
	
});
jQuery("#chkTRN").click(function(){

	if(jQuery('#chkTRN').is(':checked') == true){
		jQuery("#chkOPL").attr("checked",false);
	}
});
</script>
<form id="frmTrainging" name="frmTrainging">
	<div style="padding-left:5%;" align="left">
		<div id="trainingAreaPopupId" title="" >
			<table>
				<tr>
				
					<td>
					<div>
						<input type="checkbox" id="chkOPL" name="chkOPL"> <label>OPL</label>
						<span> <input type="checkbox" id="chkTRN" name="chkTRN" checked="checked"> <label>Traning</label> </span>
						</div>
						<div  id="frmTraingingFuntKeyIds"  >
						<div style="padding-right: 20px;">
							<input type="hidden" id="factory" name="factory" value=" "  ></input>			
							<input type="hidden" id="section" name="section" value=" "  ></input>
							<input type="hidden" id="cell" name="cell" value=" "  ></input>
							<input type="hidden" id="machine" name="machine" value=" "  ></input>
							<input type="hidden" id="flid" name="flid" value=" "  ></input>
						</div>
						<div id="traingingfunlocation"  style="width:90%;"></div>
						<div >
						
<!-- 							<span class="notes">Double Click on Data Row to Edit</span>			      	 -->
						   <div style="height:300px;width:70% " align="left" >
					     		<table id="trainingGrid"><tr><td/></tr></table>
							 	<div id="trainingPager"></div>
						    </div>
						    
						    <div style="padding-top:-80px;" >
						    <label>Others</label>
						    </div>
						    <div >
						    	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="2" style="width:725px;"></textarea>
						    </div>
							<div  style="padding-left:300px;padding-top:20px; ;padding-top: 2px\9; width : 250px;">
							<input type="button" style="width:100px;" class="easyui-button" id="btnOK" value="OK"/>
<!-- 					      	<input type="button" class="easyui-button" id="btndlgSave" value="Save"/> -->
<!--					      	<input type="button" class="easyui-button" id="btndlgClose" value="Close"/>-->
<!--					      	<span id="delSpn" style="display:none;position:absolute;right:111px;bottom:29px;bottom:16px\9;right:50px\9;">-->
<!--					      	<input type="button" class="easyui-button" id="btndlgDelete" value="Delete"/>-->
					      	</span>
						    </div>
				   		</div>
					</td>
				</tr>	
			</table>
		</div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/> 
		
</form>

