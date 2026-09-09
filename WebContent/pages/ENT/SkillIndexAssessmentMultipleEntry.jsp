<script>
var Cnt=0;
var flnid;
var errlid;
jQuery(document).ready(function(){
	initialiseForm('frmSkillAssessment');

  	jQuery('#submitForm').val('frmSkillAssessment');
  	formatDateBox('dteReviewdate','dd-MMM-yyyy');

  	//fillComboBox("frmSkillAssessment","cmbUniqueposid","roleMst.commonFilter");
  	
  	fillComboBox("frmSkillAssessment","cmbUniqueposid","combo_empType.sirp");
  	
	var factId = jQuery("#frmSkillAssessment input[id='factory']").val();
	var sectionId = jQuery("#frmSkillAssessment input[id='section']").val();
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	var machId = jQuery("#frmSkillAssessment input[id='machine']").val();
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	
	flnid=flid;
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
	loadFunctionalLocation("SkillAssementfunLocation", "functionalLoc_skillAssement.sirp","SkillAssementfunLocationEmp", "frmSkillAssessment", dataStr);
  	
/* 	var filterstr="q=2";
	var url="SkillIndexAssessment_input.sirp";
	viewGrid(url,filterstr);
 */    
	jQuery ("#btnNew").click(function(){
		    // navigateToNextForm("RePointDetail_input.sirp","Skill Index Review point ");	
	});


/* 	jQuery('#dteReviewdate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			skillDateEvt();
	   			
	   			viewEmpGrid();
			} 
	   }); */

	
});


function getReviewHalfYear(reviewDate) {
    // Input format: 20-May-2026
    const parts = reviewDate.split('-');

    const day = parseInt(parts[0], 10);
    const monthStr = parts[1];
    const year = parseInt(parts[2], 10);

    const months = {
        Jan: 0,
        Feb: 1,
        Mar: 2,
        Apr: 3,
        May: 4,
        Jun: 5,
        Jul: 6,
        Aug: 7,
        Sep: 8,
        Oct: 9,
        Nov: 10,
        Dec: 11
    };

    const month = months[monthStr];

    // Apr-Sep => H1
    if (month >= 3 && month <= 8) {
        return year + "-H1";
    }

    // Oct-Dec => H2
    if (month >= 9) {
        return year + "-H2";
    }

    // Jan-Mar => previous year H2
    return (year - 1) + "-H2";
}

function skillDateEvt() { 
	var currentDate = getServerDateTime();
	var entryDate = jQuery('#dteReviewdate').datebox("getValue");
	
		if(convertStringToDate(entryDate) > currentDate)
		{
			popupCommonErrorMsg('Should Not Exceed Current Date');
			fillWithCurrentDate('dteReviewdate');
		}
		else
		    clearValidationErrorMsg('dteReviewdate');
}

jQuery('#btnView').click(function() {
	
	var errlid=jQuery('#cmbUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteReviewdate').datebox('getValue');
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.grdenty";
	//viewGrid(url,filterstr);
	viewEmpGrid();
	
});

function viewEmpGrid() {
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	
	if (cellId=='' || cellId==' ') {
		popupCommonErrorMsg('Select JH');
		return false;
	}
	
		
	var errlid=jQuery('#cmbUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteReviewdate').datebox('getValue');
	
	var halfYear = getReviewHalfYear(reviewDate);
	
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate+"&halfYear="+halfYear;
	
	processGridnew("UniquePositionform_input.grdenty",filterstr,"upMulEmployeegrid","pageremp","","","","empload_complete");

}

 function empload_complete() {
	
	//jQuery('#jqg_upMulEmployeegrid_1').attr('checked',true);
	//upMulEmployeegrid_selectRow(1);
} 

function docDoubleClick(id)
{	
	
}



function frmSkillAssessment_FuntLocHierarchy_SuccessCallBack(result){
	//var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	var flid = result.flId;
	//alert(flid);
	 //flnid=result.flid;
	 flnid=flid;
	 //alert('flnid'+flnid);
	//var jh=result.cellId;
	setFunctionalLocWidth('frmSkillAssessment','600px');
	//var dmt=result.sectId;
	
	var errlid=jQuery('#cmbUniqueposid').combobox('getValue');
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	var reviewDate = jQuery('#dteReviewdate').datebox('getValue');
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.grdenty";
	//alert(url);
//	viewEmpGrid();
	//viewGrid(url,filterstr);
	
	
  	var reviewDate = jQuery('#dteReviewdate').datebox('getValue');
  	if (reviewDate =='')
  		fillWithCurrentDate('dteReviewdate');

}


/* function frmSkillAssessmentcmbUniqueposid_onSelect(record) {
	
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  jQuery('#cmbUniqueposid').combobox('clear');
		  return false;
	 }
	/*var filterstr="q=2";
	var url="SkillIndexAssessment_input.sirp";
	viewGrid(url,filterstr);*/
	/*var errlid=record.id;
	var reviewDate = jQuery('#dteReviewdate').datebox('getValue');	
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	
} */




/* function upMulEmployeegrid_selectRow() {
	//alert('getSelectdRows');
	var flg=false;	
	var isSelect=false;	
	var errMsg="";	
	var datarow = jQuery("#upMulEmployeegrid").jqGrid('getDataIDs');//	row get data

	var rowid = "";
	var jsonArrO = '[';
	var empIds="";
	for (i = 0; i < datarow.length; i++) {
		rowid = datarow[i];
		var errFlgRow=false;
		var errMsgRow="";
	
		var keyId = jQuery("#upMulEmployeegrid").jqGrid('getCell', i,"txtempmKeyid"); // Call detail Key Id	
		
		var CHEKVal = jQuery("#upMulEmployeegrid").jqGrid('getCell',i, "SelectVal");
		//alert(keyId+" CHEKVal "+CHEKVal);
		if (CHEKVal == "1") {
			isSelect=true;		
			if (empIds.trim().length==0){
				empIds=keyId;
			}
			else{
				empIds+=","+keyId;
			}
		}
		//alert("empIdsempIds"+empIds);
	}
	
	if(isSelect==false){		
		errMsg=errMsg + " Check Atleast One  One Employee"  ;
		flg=true;
	}
	
	empIds="&keyid="+empIds;
	
	if(flg==true){				
		return empIds+"&errText="+errMsg; 
	}
	else{
		return empIds+"&errText="; 
	}		
} */


/* function viewGrid(url,filterstr){
    processGridnew("SkillIndexAssessment_input.grdenty","q=2"+filterstr,"SkillAssesmentRpt","SkillAssesmentRptpager","","docDoubleClick","","ldCompl");
} */


jQuery('#btnMakeEntry').click(function() {
	//alert(" Click");
	var errlid=jQuery('#cmbUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteReviewdate').datebox('getValue');
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();

	var addRowId = new Array();
	var selrowid=null;
	var allRows = jQuery("#upMulEmployeegrid").jqGrid('getDataIDs');
	
	//*********************************************************Swetha changes start***************************************************//
	var siamKeyIds = [];

	for (var i = 0; i < allRows.length; i++) {
	    var rowData = jQuery("#upMulEmployeegrid").jqGrid('getRowData', allRows[i]);

	    // Collect only non-empty siam_keyid values
	    if (rowData.empm_siamkeyid && rowData.empm_siamkeyid.trim() !== "") {
	        siamKeyIds.push(rowData.empm_siamkeyid.trim());
	    }
	}

	console.log(siamKeyIds);
	var uniqueSiamKeyIds = [...new Set(siamKeyIds)];
	console.log(uniqueSiamKeyIds);
	if (uniqueSiamKeyIds.length === 1) {
	    console.log("All rows have same SIAM Key ID:", uniqueSiamKeyIds[0]);
	} else {
	    console.log("Different SIAM Key IDs found:", uniqueSiamKeyIds);
	}
	
	var maxSiamKeyId = uniqueSiamKeyIds.sort().pop();//------------------CHANGES ADDED

	console.log("Maximum SIAM Key ID:", maxSiamKeyId);//------------------CHANGES ADDED
	//*********************************************************************Swetha changes*****************************************************//
	
	
	/* var colModels = jQuery("#upMulEmployeegrid").jqGrid("getGridParam", "colModel");
	var check=jQuery("#upMulEmployeegrid_"+i).is(':checked');
 */
 //alert(" Click 2");
	var	selArray = jQuery("#upMulEmployeegrid").jqGrid('getGridParam', 'selarrrow');
	//alert(selArray +" selrowid " +selArray.length);
	// if(selArray !=null && selArray!=" " && selArray!=""){
	for(var i=0;i<selArray.length;i++)
		
	{
		selrowid=selArray[i];
	
		var rowData = jQuery("#upMulEmployeegrid").jqGrid('getRowData',selrowid);
	//	var Keyid =jQuery("#upMulEmployeegrid").jqGrid('getCell', selrowid,"txtempmKeyid");
		//alert( rowData.txtempmKeyid +"  Keyid " +i );
		var keyId = rowData.txtempmKeyid;
		addRowId.push(keyId);
		//alert(addRowId);
		//addRowId.push(Keyid);
/*  if(jQuery("#jqg_upMulEmployeegrid_"+i).is(':checked')==true)
		{	
			var keyId = rowData.txtempmKeyid;
			addRowId.add(keyId);
			
		}  */
		
	}
	
	var keyIds = addRowId;
	//alert(keyIds.length +"  keyid");
	if(keyIds.length >15){
		
		alert (" System Will Support Only 15 Employees at a Time.");
		return false;
	}
	if (keyIds == null  || keyIds.length < 0 ){
		alert("Select  Employee to Enter Review ");
		return false;
	}
	else{
		//alert(keyIds);
		
var dataStr="?q=2&flid="+flid+"&reviewDate="+reviewDate+"&errlid="+errlid+"&empKeyid="+keyIds;

	
	//dataStr+="&siamKeyidNew="+maxSiamKeyId;//------------------CHANGES ADDED REMOVED THE IF CONDITION
	

	


LoadPopUp("divSkillIndexEntry", "multipleSkillIndexEntry_input.grdenty"+dataStr, true,"90%","85%","0px","1%", "","Multipl Skill Index Entry"," ",true,true);
//LoadPopUp("loadAbnModify","Abnormality_input.abnForm?q=2&AbnId="+filterData+"&filterButton=false", true,"95%","90%","3%","1%", "popup_callback()","Abnormality Modify"," ",true,true );

return true;
	}
	
});




</script>
<form id="frmSkillAssessment" name="frmSkillAssessment">
<div id="mainDiv" >
<div id="WrapperRpt" > 

<table style="width: 120%;">
<tr>
<td width="64%">
		<div id="frmSkillAssessment" style="width: 537px; margin-left:4px;">
			<input type="hidden" id="location" name="cmbAplmLocationid"	value="" /> 
			<input type="hidden" id="factory" name="cmbAplmFactoryid"	value=""/>
			<input type="hidden" id="section" name="cmbAplmSectionid"	value=""/>
			<input type="hidden" id="cell" name="cmbAplmWherecellid"	value=""/>
			<input type="hidden" id="machine" name="cmbAplmWhichmachine" value=""/>
			<input type="hidden" id="flid" name="cmbSiamFlid" value="${requestScope.flId}"/>
			<div id="SkillAssementfunLocation" style="width: 100%; "></div>
			<span id="err_SkillAssementfunLocation" class="tpm-errormsg"> </span>
		</div>
</td>
<td width="20%" style="padding-left: 50px; padding-top: 9px">
<div>
<label class="mandatory-lbl">Employee Type</label>	
</div>
 <div>	 
<input id="cmbUniqueposid" name="cmbUniqueposid" value="${requestScope.uniqPosid}" class="easyui-combobox"  style="width:220px"  value="" />
<span id="err_cmbUniqueposid" class="tpm-errormsg"> </span>	    		
 </div>
</td> 
<td width="10%" style="padding-left: 10px; padding-top: 9px">
<div style="width: 120px;"> <label  class="mandatory-lbl">Review Date</label>	</div>
<div><input id="dteReviewdate" name="dteReviewdate" value="${requestScope.reviewDate}" class="easyui-datebox" tabindex="4"  style="width:100px;"  >
<span id="err_dteReviewdate" class="tpm-errormsg"> </span>
</div>
</td>
<td width="10%" style="padding-left: 10px; padding-top: 9px">
	<span style="padding-left:10px;">
			 <input type="button" id="btnView" name="btnView" class="easyui-button"  value="View" style=""/>
	</span>
</td>
</tr>
</table>

<table>
<tr><td style="padding-left: 20px;">
	<span style="padding-left:60px;">
			 <input type="button" id="btnMakeEntry" name="btnMakeEntry" class="easyui-button"  value="Prepare Assessment" style=""/>
	</span>
</td></tr>
<tr>
<!-- <td width="60%">
    <table id="SkillAssesmentRpt" ><tr><td></td></tr></table>
	<div id="SkillAssesmentRptpager"></div>
</td> -->
<td width="40%">
<div style="padding-left: 20px;">
		<table id="upMulEmployeegrid">
			<tr> <td> </td> </tr> 
		</table>
		<div id='pageremp'></div>
		</div>
	
</td>

</tr>
</table>
</div>
<input type="hidden" id="mode" >
</div>
</form>
	
	