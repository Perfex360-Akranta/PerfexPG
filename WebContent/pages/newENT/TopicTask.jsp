<script>
var Cnt=0;
var flnid;
var errlid;
jQuery(document).ready(function(){
	initialiseForm('frmSkillAssessment');
  	jQuery('#submitForm').val('frmSkillAssessment');
  	formatDateBox('dteReviewDate','dd-MMM-yyyy');
  	fillComboBox("frmSkillAssessment","cmbSiamUniqueposid","roleMst.commonFilter");
	var factId = jQuery("#frmSkillAssessment input[id='factory']").val();
	var sectionId = jQuery("#frmSkillAssessment input[id='section']").val();
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	var machId = jQuery("#frmSkillAssessment input[id='machine']").val();
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
	loadFunctionalLocation("SkillfunLocation", "functionalLoc.commonFilter","SkillfunLocationEmp", "frmSkillAssessment", dataStr);
  	var filterstr="q=2";
	var url=jQuery("#hiddenUrl").val();
	viewGrid(url,filterstr);
    jQuery ("#btnNew").click(function(){
		    // navigateToNextForm("RePointDetail_input.sirp","Skill Index Review point ");	
	});


});

jQuery('#btnView').click(function() {
	
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid;
	
	var url=jQuery("#hiddenUrl").val();
	viewGrid(url,filterstr);
	
});

function docDoubleClick(id)
{	
	var rowData = jQuery("#Skilllist").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	//alert("keyid  "+keyid);
	var cmbSiamFlid =  rowData.FLN;
	//navigateToNextForm('RePointDetail_input.sirp?&grid=true&keyId='+keyid,"Skill Index Review point");
    
}

function txtKSA(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input type="text" id="txtKSA_'+rowId+'" name="txtKSA_'+rowId+'" width="100px;  class="easyui-text" ">';
}

function dteSkillDate(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input id="dteSkillDate_'+rowId+'" name="dteSkillDate_'+rowId+'" class="easyui-datebox" ';
}
function formatterCheckbox(id, options, rowObject)
{
	//alert(Object.keys(rowObject));
	var rowId = options.rowId;
	var colId = options.pos;
	var chkd ="";
	if (parseFloat(id)>0)
		chkd = ' checked=checked ';
	
	var tstr= '<input type="checkbox" id="checkbox_'+rowId+'_'+colId+'" name="checkbox_'+rowId+'_'+colId+'" ' + chkd ;
		tstr+= ' onclick="if(this.checked){chkboxCheck('+rowId +','+colId+');}else{chkboxUnCheck('+ rowId +','+colId+');}" />';
	
	return tstr;
}

function chkboxCheck(rowId,colId) {
	var rowData = jQuery("#SkillAssesmentRpt").jqGrid('getRowData',rowId);
	var revType =rowData.REVTYPE4;
	if (revType=='S')
		jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, '0.5');
	else
		jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, '1');
}

function chkboxUnCheck(rowId,colId) {
	jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, ' ');
}

function frmSkillAssessment_FuntLocHierarchy_SuccessCallBack(result){
	// alert(Object.keys(result))
	 flnid=result.flid;
	//var jh=result.cellId;
	setFunctionalLocWidth('frmSkillAssessment','600px');
	//var dmt=result.sectId;
	

	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid;
	var url=jQuery("#hiddenUrl").val();
	//viewGrid(url,filterstr);
	
	
}
function frmSkillAssessmentcmbSiamUniqueposid_onSelect(record) {
	var filterstr="q=2";
	var url=jQuery("#hiddenUrl").val();
	viewGrid(url,filterstr);
    	
}

function viewGrid(url,filterstr){
	
    processGridnew(url,"q=2"+filterstr,"SkillAssesmentRpt","SkillAssesmentRptpager","","docDoubleClick","");
    processGridnew("SkillTask_input.skillIndex","q=2"+filterstr,"Skilltopic","SkilltopicPager","","docDoubleClick","","loadcomplete");
}

function loadcomplete() {
		
	var rowIds = jQuery("#Skilltopic").getDataIDs();
	for(var i = 0; i<rowIds.length; i++) {

		jQuery("#Skilltopic").jqGrid('setCell', i, 'Task', 'Task '+i);
		if (i % 3==0) {
			jQuery('#txtKSA_'+i).val("Knowledge");
			jQuery('#dteSkillDate_'+i).val(1);
		}
		else {
			if (i % 2==0) {
				jQuery('#txtKSA_'+i).val("Skill");
				jQuery('#dteSkillDate_'+i).val(2);
			}
			else {
			jQuery('#txtKSA_'+i).val("Attitude");
			jQuery('#dteSkillDate_'+i).val(4);
			}
			
		}
		
				
	}
	
	
	
}
function frmSkillAssessment_successsCallback() { 
	
}

function frmSkillAssessment_beforeSubmit()
{
	  //var gridData ='&detailData='+getDetailJson('SkillAssesmentRpt');
	
	  var gridData = '&masterData='+getMasterJson('SkillAssesmentRpt')+'&detailData='+getDetailJson('SkillAssesmentRpt');
	  return gridData ; 
	
}
function getMasterJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentRpt").jqGrid("getGridParam","colModel");
	var jsonArrO='[';
	
	for( var i = 0; i < allRows.length;i++){
	
		var row = allRows[i];
		
		var critriaId = parseJqGridCellValue(row["SIRMKEYID1"]);
		var rewId = parseJqGridCellValue(row["SIRD_KEYID5"]);
		var uniqPosId = jQuery('#cmbSiamUniqueposid').combobox('getValue');
		var siamKeyid = parseJqGridCellValue(row["SIAMKEYID0"]);
		jsonArrO += '{';
		
		jsonArrO += '"txtSiamCriteriaid":"'+ critriaId+'",';
		jsonArrO += '"cmbSiamUniqueposid":"'+ uniqPosId+'",';
		jsonArrO += '"cmbSiamKeyid":"'+ siamKeyid+'",';
		jsonArrO += '"txtSiamFlid":"'+ flnid+'" ';
		
		/* for(var j=6;j<col.length;j++){
			var colIndexName = col[j].name; 
			var cols = j-parseInt(1);
			if(colIndexName.substring(0,3)=="EMP")
			jsonArrO += '"txtSiadEmpmKeyid":"'+ colIndexName.slice(0, -1)+'",';
			if(colIndexName.substring(0,3)=="CHK"){
				var checkVal= parseJqGridCellValue(row["CHK"+cols]);
				jsonArrO += '"txtcheckVal":"'+ checkVal+'",';
			}	
		} 
	jsonArrO += '"txtSiadReviewid":"' + rewId+'"';
	*/
	jsonArrO +=  "},";
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert('jsonArrO:'+jsonArrO);
	return jsonArrO; 
}

function getDetailJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentRpt").jqGrid("getGridParam","colModel");
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
			for(var j=6;j<col.length;j++){
				var row = allRows[i];
				var critriaId = parseJqGridCellValue(row["SIRMKEYID1"]);
				var rewId= parseJqGridCellValue(row["SIRD_KEYID5"]);
				var revType = parseJqGridCellValue(row["REVTYPE4"]);
				var colIndexName = col[j].name; 
				var cols = j-parseInt(1);
				if(colIndexName.substring(0,3)=="CHK"){
					var score = parseJqGridCellValue(row["CHK"+cols]);
					
					//alert(checkVal);
					//if(checkVal=='1'){
						colIndexName = col[cols].name;
						var colIndex; 
						if(cols>9){
							colIndex = colIndexName.slice(0,-2);
							}
						else{
							colIndex = colIndexName.slice(0,-1);
							}
						jsonArrO += '{';
					//jsonArrO += '"txtSiamCriteriaid":"'+ critriaId+'",';
						//jsonArrO += '"txtcheckVal":"'+ checkVal+'",';
						jsonArrO += '"txtSiadEmpmKeyid":"'+ colIndex+'",';
						jsonArrO += '"txtSiadScore":"' + score+'", ';
						jsonArrO += '"txtSiadReviewid":"' + rewId+'"';
						jsonArrO +=  "},";

						//}
				}
		}
}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert('jsonArrO:'+jsonArrO);
	return jsonArrO; 

}

function SkillAssesmentRpt_selectRow(id){
	if(jQuery('#jqg_SkillAssesmentRpt_'+id).is(':checked')){
		chkboxCheck(id);
	}
	else{
		chkboxUnCheck(id);
	}
}
function SkillAssesmentRpt_selectAll(id,status){
	for(var i=0; i<id.length; i++){
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}
function chkboxCheck(rowId)
{	
	var url = "SkillTask_input.skillIndex";
	
	processGridnew(url,"q=2","Skilltopic","SkilltopicPager","","docDoubleClick","","loadcomplete");
}
function chkboxUnCheck(rowId)
{
	
}

</script>
<form id="frmSkillAssessment" name="frmSkillAssessment">
<div id="mainDiv" >
<div id="WrapperRpt" > 

<table style="width: 115%;">
<tr>
<td width="74%">
		<div id="frmSkillAssessment" style="width: 537px; margin-left:4px;">
			<input type="hidden" id="location" name="cmbAplmLocationid"	value="" /> 
			<input type="hidden" id="factory" name="cmbAplmFactoryid"	value=""/>
			<input type="hidden" id="section" name="cmbAplmSectionid"	value=""/>
			<input type="hidden" id="cell" name="cmbAplmWherecellid"	value=""/>
			<input type="hidden" id="machine" name="cmbAplmWhichmachine" value=""/>
			<input type="hidden" id="flid" name="cmbSiamFlid" value="${requestScope.flId}"/>
			<div id="SkillfunLocation" style="width: 100%; "></div>

		</div>
</td>
<td width="20%" style="padding-left: 10px; padding-top: 9px">
<div>
<label class="mandatory-lbl">Unique Position</label>	
</div>
 <div>	 
<input id="cmbSiamUniqueposid" name="cmbSiamUniqueposid" value="${requestScope.uniqPosid}" class="easyui-combobox"  style="width:220px"  value="" />	    		
 </div>
</td>

<td width="6%" valign="bottom">
	<span style="padding-left:10px;">
			 <input type="button" id="btnView" name="btnView" class="easyui-button"  value="View" style=""/>
	</span>

</td>

</tr>
</table>
<table>
<tr> 
	<td>
	    <table id="SkillAssesmentRpt" ><tr><td></td></tr></table>
		<div id="SkillAssesmentRptpager"></div>
	</td>
	<td >
		<table id="Skilltopic" ><tr><td></td></tr></table>
		<div id="SkilltopicPager"></div>
	</td>
</tr>
</table>


	
</div>
<input type="hidden" id="mode" >
</div>
</form>
	
	