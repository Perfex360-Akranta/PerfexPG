<script>
jQuery(document).ready(function(){
	initialiseForm('frmMultiSelectEmp');
  	jQuery('#submitForm').val('frmMultiSelectEmp');
// 	formatDateBox("dteRsplTargetdate", 'dd-MMM-yyyy');
	var factId = jQuery("#frmMultiSelectEmp input[id='factory']").val();
	var sectionId = jQuery("#frmMultiSelectEmp input[id='section']").val();
	var cellId = jQuery("#frmMultiSelectEmp input[id='cell']").val();
	var machId = jQuery("#frmMultiSelectEmp input[id='machine']").val();
	var flid = jQuery("#frmMultiSelectEmp input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
  	loadFunctionalLocation("ApfunLocationLinkRes", "functionalLoc.commonFilter","ApfunLocationValuesLinkEmp", "frmMultiSelectEmp", dataStr);
  	
	jQuery("#btnOk").click(function(){
		if(jQuery('#chkManual').is(':checked')==true ){
			valName=getFieldValue('txtNactName', 'frmMultiSelectEmp');
			if(valName.trim().length==0)
				alert("Plese Enter Others");
			else
				saveForm("frmMultiSelectEmp","MultiSelectEmp_save.api?","");
		}else{
			
		saveForm("frmMultiSelectEmp","MultiSelectEmp_save.api?","");
		}
 	 	});

	jQuery('#chkApplytoall').click(function() {
		var targetDate=	getFieldValue('dteRsplTargetdate', 'frmMultiSelectEmp'); 
		var row = jQuery("#multiSelectEmp").jqGrid('getDataIDs'); 

		if(jQuery('#chkApplytoall').is(':checked')==true ){
			
			if(targetDate.trim().length>0){
				
						for(i=0;i<row.length;i++){
							var chkval= jQuery("#multiSelectEmp").jqGrid('getCell',row[i],'CheckVal3');	
							if(chkval=='1'){
						    jQuery("#multiSelectEmp").jqGrid('setCell',row[i],'TargetDate2',targetDate);	
						    }
				}
				}else{
				showValidationErrorMsg('chkApplytoall','Selete Target Date');	
				jQuery('#chkApplytoall').attr('checked',false);
				}
		}else{
			for(i=0;i<row.length;i++){
				jQuery("#multiSelectEmp").jqGrid('setCell',row[i],'TargetDate2',' ');	
				}


	}
			
});

	jQuery('#chkManual').click(function() {
		if(jQuery('#chkManual').is(':checked')==true ) 
			enableFields("txtNactName");
		else
			disableField("frmMultiSelectEmp","txtNactName");
	});

});
function viewGrid(url,filterstr){
		
	processGridnew(url,filterstr,"multiSelectEmp", "multiSelectEmpPager","Remarks","","","multiSelectEmponloadcompletecallback","multiselect","" );
	
}
function viewGridOthers(url,filterstr){
	
// 	processGridnew(url,filterstr,"Othersgrid", "OthersgridPager","Others ","","","","","" );
	
}
function frmMultiSelectEmp_successsCallback(result){
	
		closePopUpDialoge("MultiSelectEmployee");
}
function multiSelectEmponloadcompletecallback()
{
	 var row = jQuery("#multiSelectEmp").jqGrid('getDataIDs');
	// alert(row.length);
	 for(var i=0;i<row.length;i++)
	 {
		 
		 var refKeyid = jQuery("#multiSelectEmp").jqGrid('getCell',row[i],"RSPL_REFDOCID5"); 
		 
				  if(refKeyid.trim().length>0){
					jQuery('#multiSelectEmp').setSelection(row[i], true);  
					jQuery('#jqg_multiSelectEmp_'+row[i]).attr('checked',true);
					chkboxCheck(row[i]);
				  }
				  
     	 }
}
function multiSelectEmp_selectRow(id){
	//alert(jQuery('#jqg_multiSelectEmp_'+id).is(':checked'));
	 var refKeyid = jQuery("#multiSelectEmp").jqGrid('getCell',id,"CheckVal3");
	if(jQuery('#jqg_multiSelectEmp_'+id).is(':checked') && refKeyid == 0){
		chkboxCheck(id);
	}
	else{
		chkboxUnCheck(id);
	}
	
}
function multiSelectEmp_selectAll(id,status){

	for(var i=0; i<id.length; i++){
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}
function chkboxCheck(rowId)
{
	//formatDateBox("dtePrdParam_"+rowId, 'dd-MMM-yyyy');
	//jQuery("#multiSelectEmp tr[id="+ rowId +"] td[aria-describedby=multiSelectEmp_TargetDate2]" ).html('<input id="dteTargetDate_'+rowId+'" name="dteTargetDate_'+rowId+'"/> ');
	jQuery("#multiSelectEmp").jqGrid('setCell',rowId,'CheckVal3','1');	
}
function chkboxUnCheck(rowId)

{	 
	//jQuery("dteTargetDate_"+rowId ).remove();
	jQuery("#multiSelectEmp").jqGrid('setCell',rowId,'CheckVal3','0');
}
function formatDateBoxIngrid(id){
formatDateBox("dteRsplTargetdate", 'dd-MMM-yyyy');		
}
function DateFormattor(id, options, rowObject)
{	
	
	var id=options.rowId;
	
	return '<input id="dtePrdParam_'+options.rowId+'" style="width:90px;"  class="easyui-datebox" />';
	
}
function frmMultiSelectEmp_beforeSubmit()
{
	  var gridData = '&employeeData='+getSelectdRowsEmp('multiSelectEmp','cb','CheckVal3');
	  return gridData ; 
	
}
function frmMultiSelectEmp_FuntLocHierarchy_SuccessCallBack(result){
 
	var jh=result.cellId;
	var dmt=result.sectId;
	setFunctionalLocWidth('frmMultiSelectEmp','506px');
	//disableField("frmMultiSelectEmp","dispFunctionalLoc");
	var url="multiEmpSelect_input.api";
	var url2="others_input.api";
	var refKeyId=jQuery("#txtRsplRefdocid").val();
	var refdoctype=jQuery("#txtRsplRefdoctype").val();
	
  	var filterstr="q=2&refKeyId="+refKeyId+"&refdoctype="+refdoctype;
	if(dmt==undefined||jh==undefined || dmt=="undefined" || jh=="undefined"){
		var refKeyId=jQuery("#txtRsplRefdocid").val();
		
	  	var filterstr="q=2&refKeyId="+refKeyId+"&refdoctype="+refdoctype +"&flid="+result.flId;
	  	
	  	viewGrid(url,filterstr);
	  	viewGridOthers(url2,filterstr);
		}
	else{
		var filstr="q=2&jh="+jh+"&dmt="+dmt+"&refKeyId="+refKeyId+"&refdoctype="+refdoctype+"&flid="+result.flId;;
		viewGrid(url,filstr);
		viewGridOthers(url2,filterstr);
		}
	
	  
	 
}
function getSelectdRowsEmp(jqGridId,checkBoxColName,ckeckForSelColName){

	var allRows = jQuery("#multiSelectEmp").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row["CheckVal3"];	
		
		var employeeId = parseJqGridCellValue(row["EmployeeId4"]);
		
		var mstrefId= parseJqGridCellValue(row["RSPL_REFDOCID5"]);
		//alert(mstrefId);
		var targetdate=parseJqGridCellValue(row["TargetDate2"]);
		if( value != null  &&  value.trim()  != ""){					
			if(value == '1' && (mstrefId==null || mstrefId.trim()==""))
			{
				
				flg=true;
				jsonArrO += '{';
				jsonArrO += '"txtRsplEmployeeid":"' + employeeId+'"';
				jsonArrO += ',"txtRsplKeyid":"' + mstrefId+'"';
				jsonArrO += ',"dteRsplTargetdate":"' + targetdate+'"';
				jsonArrO += ',"txtFlag":"I"';				
				jsonArrO +=  "},";
			}
			else if(value == '1' && mstrefId!=null && mstrefId.trim()!="")
			{
				flg=true;
				jsonArrO += '{';
				jsonArrO += '"txtRsplEmployeeid":"' + employeeId+'"';
				jsonArrO += ',"txtRsplKeyid":"' + mstrefId+'"';
				jsonArrO += ',"dteRsplTargetdate":"' + targetdate+'"';
				jsonArrO += ',"txtFlag":"U"';
				jsonArrO +=  "},";
			}
			else if(value == '0' && mstrefId!=null && mstrefId.trim()!="")
			{
				flg=true;
				jsonArrO += '{';
				jsonArrO += '"txtRsplEmployeeid":"' + employeeId+'"';
				jsonArrO += ',"txtRsplKeyid":"' + mstrefId+'"';
				jsonArrO += ',"dteRsplTargetdate":"' + targetdate+'"';
				jsonArrO += ',"txtFlag":"D"';
				jsonArrO +=  "},";
			}			
		} 
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert('jsonArrO:'+jsonArrO);
	return jsonArrO; 
}

</script>
<form id = "frmMultiSelectEmp" name="frmMultiSelectEmp">


<div style="padding-top: 7px; " >
<table>
<tr>
<td width="50%">
		<div id="frmActionPlanFuntKeyIds" style="width: 537px; margin-left:4px;">
			<input type="hidden" id="location" name="cmbAplmLocationid"	value="" /> 
			<input	type="hidden" id="factory" name="cmbAplmFactoryid"	value=""/>
			<input type="hidden" id="section" name="cmbAplmSectionid"	value=""/>
			<input type="hidden" id="cell" name="cmbAplmWherecellid"	value=""/>
			<input type="hidden" id="machine" name="cmbAplmWhichmachine" value=""/>
			<input type="hidden" id="flid" name="cmbAplmFlid" value="${requestScope.flid}"/>
			<div id="ApfunLocationLinkRes" style="width: 74%; "></div>

		</div>
</td>
<td style="padding-left: 10px;">
<table>
<tr>
	<td>
	<div><label style="display: none;">Target Date</label></div>
	<div><input class="easyui-datebox" id="dteRsplTargetdate" name= "dteRsplTargetdate" style="width: 100px;display: none;" ></div>
	</td>
	<td>
	<div><input type="checkbox"" id="chkApplytoall" style="display: none;" name="chkApplytoall" >
	</div>
	</td>
	<td>
	<div>
	<label style="padding-left:5px;display: none;"  >Apply to All</label>
	<span><input type="button" class="easyui-button"  style="display: none;" value="View"/></span>
	<span><input type="checkbox"" id="chkManual"  style="display: none;"><label style="padding-left:5px; display: none;">Others</label></span>
	</div>
	</td>
	<td>
	<div><input id="txtNactName" type="text"  style="display: none;" class="easyui-text" size="50" name="txtNactName" disabled="disabled"></div>
	</td>
</tr>
</table>
</td>
</tr>
</table>

	
	


	
	<table>
	<tr>
	<td style="padding-left: 4px;">
	<table id="multiSelectEmp">	</table>
	<div id="multiSelectEmpPager"></div>
	</td>
	<td style="padding-left: 30px;" style="display: none;">
	<table id="Othersgrid">	</table>
	<div id="OthersgridPager"></div>
	</td>
	</tr>
	</table>
</div>	
	
	<div align="center">
	<span>
	<input type="button" class="easyui-button" style="width:100px;" id="btnOk" value="Ok"/>
	</span>
	</div>
	<input type="hidden" id="hdnflid" value="${requestScope.flid}">
	<input type="hidden" id="txtRsplRefdocid" style="display: none;" name="txtRsplRefdocid" value="${requestScope.refKeyId}">
	<input type="hidden" id="txtRsplRefdoctype" style="display: none;" name="txtRsplRefdoctype" value="${requestScope.refdoctype}">

</form>