<script>
var Cnt=0;
var flnid;
var errlid;
jQuery(document).ready(function(){
	initialiseForm('frmSkillAssessmentGrdMod');

  	jQuery('#submitForm').val('frmSkillAssessmentGrdMod');
  	formatDateBox('dteSiamReviewdate','dd-MMM-yyyy');
  //	fillWithCurrentDate('dteSiamReviewdate');
  	//fillComboBox("frmSkillAssessmentGrdMod","cmbSiamUniqueposid","roleMst.commonFilter");
  	
  	fillComboBox("frmSkillAssessmentGrdMod","cmbSiamUniqueposid","combo_empType.sirp");
  	
	var factId = jQuery("#frmSkillAssessmentGrdMod input[id='factory']").val();
	var sectionId = jQuery("#frmSkillAssessmentGrdMod input[id='section']").val();
	var cellId = jQuery("#frmSkillAssessmentGrdMod input[id='cell']").val();
	var machId = jQuery("#frmSkillAssessmentGrdMod input[id='machine']").val();
	var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();
	
	flnid=flid;
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
	loadFunctionalLocation("SkillAssementfunLocation", "functionalLoc_skillAssement.sirp","SkillAssementfunLocationEmp", "frmSkillAssessmentGrdMod", dataStr);
  	
/* 	var filterstr="q=2";
	var url="SkillIndexAssessment_input.sirp";
	viewGrid(url,filterstr);
 */  
   var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="multipleSkillIndexModfy_input.grdenty";
	viewGrid(url,filterstr);
	jQuery ("#btnNew").click(function(){
		    // navigateToNextForm("RePointDetail_input.sirp","Skill Index Review point ");	
	});


	jQuery('#dteSiamReviewdate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   			skillDateEvt();
	   			
	   		//	viewEmpGrid();
			} 
	   });

	
});


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
	var url="multipleSkillIndexModfy_input.grdenty";
	viewGrid(url,filterstr);
//	viewEmpGrid();
	
});


jQuery('#btnRefreshGrid').click(function() {
	
	var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();

	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	//alert(reviewDate +" reviewDate");
	var filterstr="?q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.sirp";
	viewGrid(url,filterstr);
});

function viewEmpGrid() {
	var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();
	var cellId = jQuery("#frmSkillAssessmentGrdMod input[id='cell']").val();
	/*
	if (cellId=='' || cellId==' ') {
		popupCommonErrorMsg('Select JH');
		return false;
	}
	*/
		
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var reviewDate = jQuery("#dteSiamReviewdate").datebox("getValue");
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	
	processGridnew("multipleSkillIndexModfy_input.grdenty",filterstr,"upEmployeegrid","pageremp","","uniquedoubleclick","","empload_complete");

}




function empload_complete() {
	
	jQuery('#jqg_upEmployeegrid_1').attr('checked',true);
	//upEmployeegrid_selectRow(1);
}

function docDoubleClick(id)
{	
	
}

function formatterCheckbox(id, options, rowObject)
{
	//alert(Object.keys(rowObject) +".......123");
	var rowId = options.rowId;
	var colId = options.pos;
	var chkd ="";
	if (parseFloat(id)>0)
		chkd = ' checked=checked '; 
	
	
	var tstr= '<input type="checkbox" id="chkEmpScore_'+rowId+'_'+colId+'" name="chkEmpScore_'+rowId+'_'+colId+'" ' + chkd ;
		tstr+= ' onclick="if(this.checked){chkboxCheck('+rowId +','+colId+');}else{chkboxUnCheck('+ rowId +','+colId+');}" />';
	//alert(tstr +" rowId "+rowId+" colId "+colId);
	return tstr;
	//alert("tstr" +tstr);
}

function chkboxCheck(rowId,colId) {
	var rowData = jQuery("#SkillAssesmentMod").jqGrid('getRowData',rowId);
	var revType =rowData.REVTYPE5;
	if (revType=='S') {
		var mark = getSubPointMark(rowId);
		jQuery("#SkillAssesmentMod").jqGrid('setCell', rowId, 'CHK'+colId, mark);
	}
	else
		jQuery("#SkillAssesmentMod").jqGrid('setCell', rowId, 'CHK'+colId, '1');
	
	enableFields('chkEmpScore_'+(rowId+1)+'_'+colId);
}

function getSubMainNo(subMainNo) {
	var mainArr = subMainNo.split('-');
	return mainArr[0];
}
function getSubPointMark(rowId) {
	var noofSubPoints = 1;
	var allRows = jQuery("#SkillAssesmentMod").jqGrid('getRowData');
	
	var rowData = jQuery("#SkillAssesmentMod").jqGrid('getRowData',rowId);
	var subMainNo =rowData.SkillIndexAssessmentSheet3;
	subMainNo = getSubMainNo(subMainNo);
	
	for( var i = rowId-1; i >= 1; i-- ){
		rowData = jQuery("#SkillAssesmentMod").jqGrid('getRowData',i);
		revType =rowData.REVTYPE5;
		prevSubMainNo =rowData.SkillIndexAssessmentSheet3;
		prevSubMainNo = getSubMainNo(prevSubMainNo);
		if (revType=='M' || subMainNo != prevSubMainNo) {
			rowId = parseInt(i)+1;
			i=0;
		}
	}
		
	rowData = jQuery("#SkillAssesmentMod").jqGrid('getRowData',rowId);
	subMainNo =rowData.SkillIndexAssessmentSheet3;
	subMainNo = getSubMainNo(subMainNo);
	for( var i = rowId+1; i < allRows.length;i++){
		rowData = jQuery("#SkillAssesmentMod").jqGrid('getRowData',i);
		revType =rowData.REVTYPE5;
		prevSubMainNo =rowData.SkillIndexAssessmentSheet3;
		prevSubMainNo = getSubMainNo(prevSubMainNo);
		
		if (revType=='S' && subMainNo == prevSubMainNo) 
			noofSubPoints = parseInt(noofSubPoints) +1;
		else
			i = allRows.length;
	}
	var mark = parseInt(1) / parseInt(noofSubPoints);
	mark=Math.round(mark*100)/100;
	return mark;
	
}


function chkboxUnCheck(rowId,colId) {
	
	jQuery("#SkillAssesmentMod").jqGrid('setCell', rowId, 'CHK'+colId, ' ');
	var prevCriteria = '';
	var curCriteria = '';	
	var LocnId = jQuery("#frmSkillAssessmentGrdMod input[id='location']").val();

	var empType=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	
	var allRows = jQuery("#SkillAssesmentMod").jqGrid('getRowData');
	/* // if(rowId==2){
		alert(12345);
		for( var i = colId; i <=allRows.length;i++){
			for( var j=rowId ;j<=allRows.length;j++){
			jQuery('#chkEmpScore_'+i+'_'+colId).attr('checked',false);
			disableField("SkillAssesmentMod",'chkEmpScore_'+(i+1)+'_'+colId);

		}
		enableField("SkillAssesmentMod",'chkEmpScore_'+rowId+'_'+colId);
	}  */
	
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
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=21;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=29;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=38;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=46;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=53;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
		 
		
		}
		else if(empType=='ETPM0002'){
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=12;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=41;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=47;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
		 
		
		
		}
		
		else if(empType=='ETPM0003'){
			
			if(LocnId=='LCN0000003'){
				
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=7;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=39;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=45;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
		     }	 
			}				
			else{
				
			if(curCriteria=='SPO001'){
				
				for ( k=rowId;k<=7;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=20;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=28;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=34;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=40;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=46;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			
		 }
		}
		else {
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=12;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=41;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=47;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentMod",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
						
		}
			i =allRows.length;
	}

		else {
			//	jQuery('#chkEmpScore_'+i+'_'+colId).attr('checked',false);
				disableField("SkillAssesmentMod",'chkEmpScore_'+i+'_'+colId);
		}
		prevCriteria = curCriteria;
	}
}

function frmSkillAssessmentGrdMod_FuntLocHierarchy_SuccessCallBack(result){
	//var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();
	var flid = result.flId;
	//alert(flid);
	 //flnid=result.flid;
	 flnid=flid;
	 //alert('flnid'+flnid);
	//var jh=result.cellId;
	setFunctionalLocWidth('frmSkillAssessmentGrdMod','450px');
	//var dmt=result.sectId;
	
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="SkillIndexAssessment_input.sirp";
	//alert(url);
	//viewEmpGrid();
	//viewGrid(url,filterstr);
	
	
  	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
  	if (reviewDate =='')
  		fillWithCurrentDate('dteSiamReviewdate');

}


function frmSkillAssessmentGrdModcmbSiamUniqueposid_onSelect(record) {
	
	var cellId = jQuery("#frmSkillAssessmentGrdMod input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  jQuery('#cmbSiamUniqueposid').combobox('clear');
		  return false;
	 }
	/*var filterstr="q=2";
	var url="SkillIndexAssessment_input.sirp";
	viewGrid(url,filterstr);*/
	var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();

	var errlid=record.id;
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	//alert(reviewDate +" reviewDate");
	var filterstr="?q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="multipleSkillIndexModfy_input.grdenty";
	viewGrid(url,filterstr);
	//viewEmpGrid(url,filterstr);
	//alert(filterstr);
	//processGridnew("multipleSkillIndexModfy_input.grdenty",filterstr,"SkillAssesmentMod","SkillAssesmentModpager","","uniquedoubleclick","","empload_complete");

}

function upEmployeegrid_selectRow(rowId) {
	var allRows = jQuery("#upEmployeegrid").jqGrid('getRowData');
	for( var i = 1; i <=allRows.length;i++){
		if (!(parseInt(i) == parseInt(rowId)))
			jQuery('#jqg_upEmployeegrid_'+i).attr('checked',false);
	}
	var empId = jQuery("#upEmployeegrid").jqGrid('getCell',rowId,'txtempmKeyid');
	jQuery('#jqg_upEmployeegrid_'+rowId).attr('checked',true);
	
		var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
		var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
		var filterstr="q=2&flnid="+flnid+"&errlid="+errlid+"&reviewDate="+reviewDate+"&empId="+empId;
	//	var url="multipleSkillIndexModfy_input.grdenty"; 
	//	viewGrid(url,filterstr);
}


function viewGrid(url,filterstr){
    processGridnew("multipleSkillIndexModfy_input.grdenty",filterstr,"SkillAssesmentMod","SkillAssesmentModpager","","docDoubleClick","","ldCompl");
}

/* function ldCompl() { 
	
	var allRows = jQuery("#SkillAssesmentMod").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentMod").jqGrid("getGridParam","colModel");

	var prevCriteria = '';
	var curCriteria = '';
	var k=null;
	//alert(allRows.length +"  ColName "+col.length);
	for( k=7;k<=col.length;k++){
	for( var i = 1; i <=allRows.length;i++){
		

			if(jQuery('#chkEmpScore_'+i+'_'+k+'').is(':checked')==false )
				disableField("frmSkillAssessmentGrdMod",'chkEmpScore_'+i+'_'+k+'');
		
		
		

	}
	}
	setFocusOnField('chkEmpScore_'+i+'_'+k+'');
	alert("Check box completed at");
} */

function ldCompl() {

    var gridId = jQuery("#SkillAssesmentMod");
    var allRows = gridId.jqGrid('getRowData');
    var colModel = gridId.jqGrid("getGridParam", "colModel");

    var prevCriteria = '';
    var rowCount = allRows.length;
    var colCount = colModel.length;

    for (var k = 7; k < colCount; k++) {

        prevCriteria = '';

        for (var i = 0; i < rowCount; i++) {

            var row = allRows[i];
            var curCriteria = row["SIRMKEYID1"];

            if (prevCriteria && prevCriteria === curCriteria) {

                var checkboxId = '#chkEmpScore_' + (i + 1) + '_' + k;
                var chk = jQuery(checkboxId);

                if (chk.length && !chk.prop('checked')) {
                    disableField(
                        "frmSkillAssessmentGrdMod",
                        'chkEmpScore_' + (i + 1) + '_' + k
                    );
                }
            }

            prevCriteria = curCriteria;
        }
    }
	jQuery('#chkEmpScore_'+i+'_'+k+'').focus();

}

// To Use the Select All Check Box By Kiran 
 /* 
 Commented not approved by BE cell
 jQuery("#chkSelectAll").click(function() {

	
	var allRows = jQuery("#SkillAssesmentMod").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentMod").jqGrid("getGridParam","colModel");
	alert(allRows +" all rows "+  col);
		
	var k=null;
	for( k=7;k<=col.length;k++){
		for( var i = 1; i <=allRows.length;i++){
			
			if(jQuery('#chkEmpScore_'+i+'_'+k+'').is(':checked')==false ){
			jQuery('#chkEmpScore_'+i+'_'+k+'').attr('checked',true);
			var rowData = jQuery("#SkillAssesmentMod").jqGrid('getRowData',i);
			var revType =rowData.REVTYPE5;
			//enableFields("SkillAssesmentMod",'chkEmpScore_'+i);
			if (revType=='S') {
				var mark = getSubPointMark(i);
				jQuery("#SkillAssesmentMod").jqGrid('setCell', i, 'CHK'+k, mark);
			}
			else
				jQuery("#SkillAssesmentMod").jqGrid('setCell', i, 'CHK'+k, '1');

			}
			
			 // To Uncheck all the rows commented by Kiran
			else {
				
			
				jQuery('#chkEmpScore_'+i+'_'+k+'').attr('checked',false);
				if(jQuery('#chkEmpScore_'+i+'_'+k+'').is(':checked')==false ){
				jQuery("#SkillAssesmentMod").jqGrid('setCell', i, 'CHK'+k, ' ');
				
				}
			} 
		}
	}

	
}); */


function frmSkillAssessmentGrdMod_successsCallback() { 
	
	

	var flid = jQuery("#frmSkillAssessmentGrdMod input[id='flid']").val();

	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
	//alert(reviewDate +" reviewDate");
	var filterstr="?q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="multipleSkillIndexModfy_input.grdenty";
	viewGrid(url,filterstr);
	
	}

	

function frmSkillAssessmentGrdMod_beforeSubmit()
{
	 // alert(345672);
	  //var gridData ='&detailData='+getDetailJson('SkillAssesmentMod');
	  var detailJson = getDetailJson('SkillAssesmentMod');
	 // alert(34567);
	  var cellId = jQuery("#frmSkillAssessmentGrdMod input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  return false;
	  }
		  
	  if (detailJson==false) {
		  popupCommonErrorMsg(" Provide Score for minimum one Criteria.");
		  return false;
	  }
		  
	  var gridData = '&masterData='+getMasterJson('SkillAssesmentMod')+'&detailData='+detailJson;
	  //alert(gridData);
	  return gridData ; 
}

function getMasterJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentMod").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentMod").jqGrid("getGridParam","colModel");
	
	var jsonArrO='[';
	
	//for( var i = 0; i < allRows.length;i++){
	for( var i = 0; i < 1 ;i++) {
	
		var row = allRows[i];
		
		//var critriaId = parseJqGridCellValue(row["SIRMKEYID11"]);
		//var rewId = parseJqGridCellValue(row["SIRD_KEYID5"]);
		var uniqPosId = jQuery('#cmbSiamUniqueposid').combobox('getValue');
		var reviewDate = jQuery('#dteSiamReviewdate').datebox('getValue');
		var siamKeyid = parseJqGridCellValue(row["SIAMKEYID0"]);
		//alert(siamKeyid +"siamKeyidsiamKeyid");
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
//alert(345);
	var allRows = jQuery("#SkillAssesmentMod").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentMod").jqGrid("getGridParam","colModel");
	//alert(allRows +"  "+col.length);
	var jsonArrO='[';
	var selected = false;
	for( var i = 0; i < allRows.length;i++){
			for(var j=6;j<col.length;j++){
			
				var colIndexName = col[j].name; 
				var cols = j-parseInt(1);
				var row = allRows[i];
				//alert(row +"11");
				var critriaId = parseJqGridCellValue(row["SIRMKEYID1"]);
			//	alert(critriaId)
				var rewId= parseJqGridCellValue(row["SIRD_KEYID6"]);
				
				//alert('rewId'+rewId);
				//var revType = parseJqGridCellValue(row["REVTYPE4"]);
				
			
				//alert(" colIndexName "+ colIndexName.substring(0,3));
				if(colIndexName.substring(0,3)=="CHK"){
					var score = parseJqGridCellValue(row["CHK"+cols]);
					//alert(score +"Score");
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
	
	
	if (selected==false)
		return false;
	else
		return jsonArrO; 

}
</script>
<form id="frmSkillAssessmentGrdMod" name="frmSkillAssessmentGrdMod">
<div id="mainDiv" >
<div id="WrapperRpt" > 
<div>
<table style="width: 120%;">
<tr>
<td width="45%">
		<div id="frmSkillAssessmentGrdMod" style="width: 200px; margin-left:4px;">
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
<td width="15%" >
  <div > 
<label class="mandatory-lbl">Employee Type</label>	
  </div> 
 <div >	  
<input id="cmbSiamUniqueposid" name="cmbSiamUniqueposid" value="${requestScope.uniqPosid}" class="easyui-combobox"  style="width:220px"  value="" />
<span id="err_cmbSiamUniqueposid" class="tpm-errormsg"> </span>	    		
 </div> 
</td> 
<td style="padding-left:10px;" >
<div > <label  class="mandatory-lbl">Review Date</label>	</div>
<div><input id="dteSiamReviewdate" name="dteSiamReviewdate" value="${requestScope.reviewDate}" class="easyui-datebox" tabindex="4"  style="width:100px;"  >
<span id="err_dteSiamReviewdate" class="tpm-errormsg"> </span>
</div>
</td>
<td >
	<span style="padding-left:5px; ">
		 <input type="button" id="btnView" name="btnView" class="easyui-button"  value="View" style=""/>
	</span>
	
</td>
<!-- <td >
	<span style="padding-left:5px; ">
		 <input type="button" id="btnExcelView" name="btnExcelView" class="easyui-button"  value="Export To Excel" style=""/>
	</span>
	
</td> -->
</tr>
<tr>
<td >
<!--<span style="padding-left:10px;">

<label> Select All</label>
</span>
 	<span>


 <input type="checkbox" id="chkSelectAll" name="chkSelectAll" value="Y" /> 
</span> -->
</td>
</tr>
</table>

</div>
<!-- <div>
<span style="padding-left:10px; ">
		 <input type="button" id="btnRefreshGrid" name="btnRefreshGrid" class="easyui-button"  value="Refresh Grid" style=""/>
	</span>
</div> -->
<div>
<table>
<tr>
<td width="100%">
    <table id="SkillAssesmentMod" ><tr><td></td></tr></table>
	<div id="SkillAssesmentModpager"></div>
</td>
<!--  <td width="30%">
<div style="padding-left: 20px;">
		<table id="upEmployeegrid">
			<tr> <td> </td> </tr> 
		</table>
		<div id='pageremp'></div>
		</div>	
</td>  -->
</tr>
</table>
</div>
</div>
<input type="hidden" id="mode" >
</div>
</form>
	
	