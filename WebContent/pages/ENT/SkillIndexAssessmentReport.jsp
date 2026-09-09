<script>
var Cnt=0;
var flnid;
var errlid;
var currentSiamKeyid = "";//ADDED
jQuery(document).ready(function(){
	initialiseForm('frmSkillAssessment');
	

  	jQuery('#submitForm').val('frmSkillAssessment');
  	formatDateBox('dteSiamReviewdate','dd-MMM-yyyy');

  	//fillComboBox("frmSkillAssessment","cmbSiamUniqueposid","roleMst.commonFilter");
  	
  	fillComboBox("frmSkillAssessment","cmbSiamUniqueposid","combo_empType.sirp");
  	
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


	jQuery('#dteSiamReviewdate').datebox({  	   
		onSelect:function(recordid)
			{
				var selectedDate = formatDate(recordid);
				
				skillDateEvt();
				jQuery('#dteSiamReviewdate').datebox('setValue', selectedDate);
				viewEmpGrid();
			} 
	   });

	
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

function formatDate(date){
	let newDate = new Date(date);
		   

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
				            months[newDate.getMonth()] + '-' +
				            newDate.getFullYear();
	return formatted;
 }
	

function skillDateEvt() { 
	var currentDate = getServerDateTime();
	var entryDate = jQuery('#dteSiamReviewdate').datebox("getValue");
	
		if(convertStringToDate(entryDate) > currentDate)
		{
			popupCommonErrorMsg('Should Not Exceed Current Date');
			fillWithCurrentDate('dteSiamReviewdate');
		}
		else
		    clearValidationErrorMsg('dteSiamReviewdate');
}

jQuery('#btnView').click(function() {
	
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.sirp";
	//viewGrid(url,filterstr);
	viewEmpGrid();
	
});

function viewEmpGrid() {
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	/*
	if (cellId=='' || cellId==' ') {
		popupCommonErrorMsg('Select JH');
		return false;
	}
	*/
		
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	var halfYear = getReviewHalfYear(reviewDate);
	//alert("half year"+halfYear);
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate+"&halfYear="+halfYear;
	processGridnew("UniquePositionform_input.sirp",filterstr,"upEmployeegrid","pageremp","","uniquedoubleclick","","empload_complete");

}

function empload_complete() {
	
	jQuery('#jqg_upEmployeegrid_1').prop('checked',true);
	upEmployeegrid_selectRow(1);
}

function docDoubleClick(id)
{	
	
}

function formatterCheckbox(id, options, rowObject)
{
	//alert(Object.keys(rowObject));
	var rowId = options.rowId;
	var colId = options.pos;
	var chkd ="";
	if (parseFloat(id)>0)
		chkd = ' checked=checked ';
	
	var tstr= '<input type="checkbox" id="chkEmpScore_'+rowId+'_'+colId+'" name="chkEmpScore_'+rowId+'_'+colId+'" ' + chkd ;
		tstr+= ' onclick="if(this.checked){chkboxCheck('+rowId +','+colId+');}else{chkboxUnCheck('+ rowId +','+colId+');}" />';
	//alert(tstr +" rowId "+rowId+" colId "+colId);
	return tstr;
}

function chkboxCheck(rowId,colId) {

	var rowData = jQuery("#SkillAssesmentRpt").jqGrid('getRowData',rowId);
	var revType =rowData.REVTYPE5;
		
	if (revType=='S') {
		var mark = getSubPointMark(rowId);
		jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, mark);
	}
	else
		jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, '1');
	
	enableFields('chkEmpScore_'+(rowId+1)+'_9');
}

function getSubMainNo(subMainNo) {
	var mainArr = subMainNo.split('-');
	return mainArr[0];
}
function getSubPointMark(rowId) {
	var noofSubPoints = 1;
	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	
	var rowData = jQuery("#SkillAssesmentRpt").jqGrid('getRowData',rowId);
	var subMainNo =rowData.SkillIndexAssessmentSheet3;
	subMainNo = getSubMainNo(subMainNo);
	
	for( var i = rowId-1; i >= 1; i-- ){
		rowData = jQuery("#SkillAssesmentRpt").jqGrid('getRowData',i);
		revType =rowData.REVTYPE5;
		prevSubMainNo =rowData.SkillIndexAssessmentSheet3;
		prevSubMainNo = getSubMainNo(prevSubMainNo);
		if (revType=='M' || subMainNo != prevSubMainNo) {
			rowId = parseInt(i)+1;
			i=0;
		}
	}
		
	rowData = jQuery("#SkillAssesmentRpt").jqGrid('getRowData',rowId);
	subMainNo =rowData.SkillIndexAssessmentSheet3;
	subMainNo = getSubMainNo(subMainNo);
	for( var i = rowId+1; i < allRows.length;i++){
		rowData = jQuery("#SkillAssesmentRpt").jqGrid('getRowData',i);
		revType =rowData.REVTYPE5;
		prevSubMainNo =rowData.SkillIndexAssessmentSheet3;
		prevSubMainNo = getSubMainNo(prevSubMainNo);
		//alert(revType +"  revType");
		if (revType=='S' && subMainNo == prevSubMainNo) 
			noofSubPoints = parseInt(noofSubPoints) +1;
		else
			i = allRows.length;
	}
	var mark = parseInt(1) / parseInt(noofSubPoints);
	mark=Math.round(mark*100)/100;
	return mark;
	
}

/* function chkboxUnCheck(rowId,colId) {
	jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, ' ');
	var prevCriteria = '';
	var curCriteria = '';	
	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	for( var i = rowId+1; i <=allRows.length;i++){
		var row = allRows[i-1];
		curCriteria = row["SIRMKEYID1"];
		if (prevCriteria == '' || prevCriteria != curCriteria )
			i =allRows.length; 
		else {
				//jQuery('#chkEmpScore_'+i+'_9').prop('checked',false);
				disableField("SkillAssesmentRpt",'chkEmpScore_'+i+'_9');
		}
		prevCriteria = curCriteria;
	}
} */

function chkboxUnCheck(rowId,colId) {
	jQuery("#SkillAssesmentRpt").jqGrid('setCell', rowId, 'CHK'+colId, ' ');
	var prevCriteria = '';
	var curCriteria = '';	
	var LocnId = jQuery("#frmSkillAssessment input[id='location']").val();

	var empType=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	
	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var k=null;
	for( var i = rowId+1; i <=allRows.length;i++){
		var row = allRows[i-1];
		curCriteria = row["SIRMKEYID1"];
		
		if (prevCriteria == '' || prevCriteria != curCriteria ){			
			row = allRows[i-2];
			curCriteria = row["SIRMKEYID1"];
			if(empType=='ETPM0001'){
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');

				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=21;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=29;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=38;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=46;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=53;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
		 
		
		}
		else if(empType=='ETPM0002'){
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');

				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=12;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=41;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=47;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
		 
		
		
		}
		
		else if(empType=='ETPM0003'){
			
			if(LocnId=='LCN0000003'){
				
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=7;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');

				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=39;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=45;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
		     }	 
			}				
			else{
				
			if(curCriteria=='SPO001'){
				
				for ( k=rowId;k<=7;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');

				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=20;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=28;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=34;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=40;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=46;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			
		 }
		}
		else {
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');

				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=12;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=41;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=47;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).prop('checked',false);
				jQuery("#SkillAssesmentRpt").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentRpt",'chkEmpScore_'+k+'_9');
				}
				enableFields('chkEmpScore_'+(rowId)+'_9');
			}
						
		}
			i =allRows.length;
	}

		else {
			//	jQuery('#chkEmpScore_'+i+'_9').prop('checked',false);
				disableField("SkillAssesmentRpt",'chkEmpScore_'+i+'_9');
		}
		prevCriteria = curCriteria;
	}
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
	
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var flid = jQuery("#frmSkillAssessment input[id='flid']").val();
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.sirp";
	//alert(url);
	viewEmpGrid();
	//viewGrid(url,filterstr);
	
	
  	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
  	if (reviewDate =='')
  		fillWithCurrentDate('dteSiamReviewdate');

}


/* function frmSkillAssessmentcmbSiamUniqueposid_onSelect(record) {
	
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  jQuery('#cmbSiamUniqueposid').combobox('clear');
		  return false;
	 }
	
	var errlid=record.id;
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	//alert(reviewDate +" review date");
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.sirp";
	processGridnew("UniquePositionform_input.sirp",filterstr,"upEmployeegrid","pageremp","","uniquedoubleclick","","empload_complete");
	//viewGrid(url,filterstr);
	//viewEmpGrid();
} */

function frmSkillAssessmentcmbSiamUniqueposid_onSelect(record) {
	
	var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  jQuery('#cmbSiamUniqueposid').combobox('clear');
		  return false;
	 }
	/*var filterstr="q=2";
	var url="SkillIndexAssessment_input.sirp";
	viewGrid(url,filterstr);*/
	var errlid=record.id;
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	//alert(reviewDate +" review date");
	var halfYear = getReviewHalfYear(reviewDate);//-Get half year
	
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate+"&halfYear="+halfYear; //Sending to servlet
	var url="SkillIndexAssessment_input.sirp";
	
	processGridnew("UniquePositionform_input.sirp",filterstr,"upEmployeegrid","pageremp","","uniquedoubleclick","","empload_complete");
	//viewGrid(url,filterstr);
	//viewEmpGrid();
}
/* 
function upEmployeegrid_selectRow(rowId) {
	var allRows = jQuery("#upEmployeegrid").jqGrid('getRowData');
	for( var i = 1; i <=allRows.length;i++){
		if (!(parseInt(i) == parseInt(rowId)))
			jQuery('#jqg_upEmployeegrid_'+i).prop('checked',false);
	}
	var empId = jQuery("#upEmployeegrid").jqGrid('getCell',rowId,'txtempmKeyid');
	jQuery('#jqg_upEmployeegrid_'+rowId).prop('checked',true);
	
		var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
		var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
		var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate+"&empId="+empId;
		var url="SkillIndexAssessment_input.sirp";
		viewGrid(url,filterstr);
} */

function upEmployeegrid_selectRow(rowId) {

    var allRows = jQuery("#upEmployeegrid").jqGrid('getRowData');
    var siamKeyIdsSave = [];

    for (var i = 0; i < allRows.length; i++) {

        var currentRowId = i + 1; // jqGrid checkbox id format

        // Uncheck all except selected row
        if (parseInt(currentRowId) !== parseInt(rowId)) {
            jQuery('#jqg_upEmployeegrid_' + currentRowId).prop('checked', false);
        }

        var rowData = allRows[i];
        

        if (rowData.empm_siamkeyid && rowData.empm_siamkeyid.trim() !== "") {
            siamKeyIdsSave.push(rowData.empm_siamkeyid.trim());
        }
    }

    // Get unique values AFTER loop
    console.log("siamKeyIdsSave:", siamKeyIdsSave);
    var uniqueSiamKeyIdsSave = [...new Set(siamKeyIdsSave)];
    
    console.log("uniqueSiamKeyIdsSave:", uniqueSiamKeyIdsSave);

    // Select current row
    jQuery('#jqg_upEmployeegrid_' + rowId).prop('checked', true);

    var empId = jQuery("#upEmployeegrid").jqGrid('getCell', rowId, 'txtempmKeyid');

    var errlid = jQuery('#cmbSiamUniqueposid').combobox('getValue');
    var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');

    var filterstr = "q=2&flnid=" + flnid + "&errlid=" + errlid + 
                    "&reviewDate=" + reviewDate + "&empId=" + empId;

    
        filterstr += "&siamKeyidNew=" + uniqueSiamKeyIdsSave[0];
        currentSiamKeyid = uniqueSiamKeyIdsSave[0] || '';
	
    var url = "SkillIndexAssessment_input.sirp";
    viewGrid(url, filterstr);
}


function viewGrid(url,filterstr){
    processGridnew("SkillIndexAssessment_input.sirp","q=2"+filterstr,"SkillAssesmentRpt","SkillAssesmentRptpager","","docDoubleClick","","ldCompl");
}

function ldCompl() { 
	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var prevCriteria = '';
	var curCriteria = '';
	//alert(allRows.length);
	for( var i = 1; i <=allRows.length;i++){
		var row = allRows[i-1];
		curCriteria = row["SIRMKEYID1"];
		if (prevCriteria == '' || prevCriteria != curCriteria )
			prevCriteria = curCriteria; 
		else {
			if(jQuery('#chkEmpScore_'+i+'_9').is(':checked')==false )
				disableField("frmSkillAssessment",'chkEmpScore_'+i+'_9');
		}
		
		prevCriteria = curCriteria;
	}
	setFocusOnField('chkEmpScore_1_9');
}

function frmSkillAssessment_successsCallback() { 
	
	//navigateToPrevForm();
	jQuery("#upEmployeegrid").jqGrid().trigger("reloadGrid");
	
}

function frmSkillAssessment_beforeSubmit()
{
	  //var gridData ='&detailData='+getDetailJson('SkillAssesmentRpt');
	  var detailJson = getDetailJson('SkillAssesmentRpt');
	  
	  var cellId = jQuery("#frmSkillAssessment input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  return false;
	  }
		  
	  if (detailJson==false) {
		  popupCommonErrorMsg(" Provide Score for minimum one Criteria.");
		  return false;
	  }
		  
	  var gridData = '&masterData='+getMasterJson('SkillAssesmentRpt')+'&detailData='+detailJson;
	  //alert(gridData);
	  return gridData ; 
}

/* function getMasterJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentRpt").jqGrid("getGridParam","colModel");
	var jsonArrO='[';
	
	//for( var i = 0; i < allRows.length;i++){
	for( var i = 0; i < 1 ;i++) {
	
		var row = allRows[i];
		
		//var critriaId = parseJqGridCellValue(row["SIRMKEYID1"]);
		//var rewId = parseJqGridCellValue(row["SIRD_KEYID5"]);
		var uniqPosId = jQuery('#cmbSiamUniqueposid').combobox('getValue');
		var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
		var siamKeyid = parseJqGridCellValue(row["SIAMKEYID0"]);
		//alert(siamKeyid +" siamKeyid");
		jsonArrO += '{';
		//jsonArrO += '"txtSiamCriteriaid":"'+ critriaId+'",';
		jsonArrO += '"cmbSiamUniqueposid":"'+ uniqPosId+'",';
		jsonArrO += '"cmbSiamKeyid":"'+ siamKeyid+'",';
		jsonArrO += '"dteSiamReviewdate":"'+ reviewDate+'",';
		jsonArrO += '"txtSiamFlid":"'+ flnid+'" ';
		//jsonArrO +='"txtempmKeyid":"'+ empKeyid+'"';
		
	
	jsonArrO +=  "},";
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
} */

function getMasterJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentRpt").jqGrid("getGridParam","colModel");
	var jsonArrO='[';
	
	//for( var i = 0; i < allRows.length;i++){
	for( var i = 0; i < 1 ;i++) {
	
		var row = allRows[i];
		
		//var critriaId = parseJqGridCellValue(row["SIRMKEYID1"]);
		//var rewId = parseJqGridCellValue(row["SIRD_KEYID5"]);
		var uniqPosId = jQuery('#cmbSiamUniqueposid').combobox('getValue');
		var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
		var siamKeyid = parseJqGridCellValue(row["SIAMKEYID0"]);
		 if (!siamKeyid || siamKeyid.trim() === '') {
			 siamKeyid = currentSiamKeyid;
			  }
		//alert(siamKeyid +" siamKeyid");
		jsonArrO += '{';
		//jsonArrO += '"txtSiamCriteriaid":"'+ critriaId+'",';
		jsonArrO += '"cmbSiamUniqueposid":"'+ uniqPosId+'",';
		jsonArrO += '"cmbSiamKeyid":"'+ siamKeyid+'",';
		jsonArrO += '"dteSiamReviewdate":"'+ reviewDate+'",';
		jsonArrO += '"txtSiamFlid":"'+ flnid+'" ';
		//jsonArrO +='"txtempmKeyid":"'+ empKeyid+'"';
		
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
	
	return jsonArrO; 
}
	
function getDetailJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentRpt").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentRpt").jqGrid("getGridParam","colModel");
	var jsonArrO='[';
	var selected = false;
	for( var i = 0; i < allRows.length;i++){
			for(var j=6;j<col.length;j++){
				var row = allRows[i];
				var critriaId = parseJqGridCellValue(row["SIRMKEYID1"]);
				var rewId= parseJqGridCellValue(row["SIRD_KEYID6"]);
				
				//alert('rewId'+rewId);
				//var revType = parseJqGridCellValue(row["REVTYPE4"]);
				var colIndexName = col[j].name; 
				var cols = j-parseInt(1);
				if(colIndexName.substring(0,3)=="CHK"){
					var score = parseJqGridCellValue(row["CHK"+cols]);
					
					if ( score ==1 )
						selected=true;
					//alert(checkVal);
					//if(checkVal=='1'){
						colIndexName = col[cols].name;
						var colIndex; 
						if(cols>10){
							colIndex = colIndexName.slice(0,-2);
						}
						else{
							colIndex = colIndexName.slice(0,-1);
							}
						jsonArrO += '{';
						jsonArrO += '"txtSiadCriteriaid":"'+ critriaId+'",';
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
	
	if (selected==false)
		return false;
	else
		return jsonArrO; 

}
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
<input id="cmbSiamUniqueposid" name="cmbSiamUniqueposid" value="${requestScope.uniqPosid}" class="easyui-combobox"  style="width:220px"  value="" />
<span id="err_cmbSiamUniqueposid" class="tpm-errormsg"> </span>	    		
 </div>
</td> 
<td width="10%" style="padding-left: 10px; padding-top: 9px">
<div style="width: 120px;"> <label  class="mandatory-lbl">Review Date</label>	</div>
<div><input id="dteSiamReviewdate" name="dteSiamReviewdate" value="${requestScope.reviewDate}" class="easyui-datebox" tabindex="4"  style="width:100px;"  >
<span id="err_dteSiamReviewdate" class="tpm-errormsg"> </span>
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
<tr>
<td width="60%">
    <table id="SkillAssesmentRpt" ><tr><td></td></tr></table>
	<div id="SkillAssesmentRptpager"></div>
</td>
<td width="30%">
<div style="padding-left: 20px;">
		<table id="upEmployeegrid">
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
	
	