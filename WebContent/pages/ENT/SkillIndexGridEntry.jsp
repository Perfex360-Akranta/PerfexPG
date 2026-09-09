<script>
var Cnt=0;
var flnid;
var errlid;
jQuery(document).ready(function(){
	initialiseForm('frmSkillAssessmentGrdEntry');

  	jQuery('#submitForm').val('frmSkillAssessmentGrdEntry');
  	formatDateBox('dteSiamReviewdate','dd-MMM-yyyy');
  //	fillWithCurrentDate('dteSiamReviewdate');
  	  	fillComboBox("frmSkillAssessmentGrdEntry","cmbSiamUniqueposid","combo_empType.sirp");

  	var revDate=jQuery('#hdnReviewdate').val();
  	var unqPosn=jQuery('#hdnUniquePosition').val();
  	var flids=jQuery('#hdnflid').val();
  	var empKeyids=jQuery('#hdnEmpKeyid').val();
  	jQuery('#dteSiamReviewdate').datebox("setValue",revDate);
 	jQuery('#cmbSiamUniqueposid').combobox("setValue",unqPosn);
  	
	var factId = jQuery("#frmSkillAssessmentGrdEntry input[id='factory']").val();
	var sectionId = jQuery("#frmSkillAssessmentGrdEntry input[id='section']").val();
	var cellId = jQuery("#frmSkillAssessmentGrdEntry input[id='cell']").val();
	var machId = jQuery("#frmSkillAssessmentGrdEntry input[id='machine']").val();
	var flid = jQuery("#frmSkillAssessmentGrdEntry input[id='flid']").val();
	 	if(flid==null||flid=='undefined'||flid==""){
	 		flid=flids;
	 	}

	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
	loadFunctionalLocation("multiSkillAssementfunLocation", "functionalLoc_skillAssement.sirp","multiSkillAssementfunLocationEmp", "frmSkillAssessmentGrdEntry", dataStr);
	
	var filterstr="?q=2&flid="+flid+"&errlid="+unqPosn+"&reviewDate="+revDate+"&empKeyid="+empKeyids;
	var url="multipleSkillIndexEntry_input.grdenty";
	//viewGrid(url,filterstr);
    processGridnew("multipleSkillIndexEntry_input.grdenty",filterstr,"SkillAssesmentEnty","SkillAssesmentEntypager","","","","ldCompl");

});

  disableField('frmSkillAssessmentGrdEntry','cmbSiamUniqueposid');
  disableField('frmSkillAssessmentGrdEntry','dteSiamReviewdate');
  disableField('frmSkillAssessmentGrdEntry','multiSkillAssementfunLocation');
  

function frmSkillAssessmentGrdEntry_FuntLocHierarchy_SuccessCallBack(result){
	//var flid = jQuery("#frmSkillAssessmentGrdEntry input[id='flid']").val();
	var flid = result.flId;
	//alert(flid);
	 //flnid=result.flid;
	 flnid=flid;
	 //alert('flnid'+flnid);
	//var jh=result.cellId;
	setFunctionalLocWidth('frmSkillAssessmentGrdEntry','450px');
	//var dmt=result.sectId;
	
	var errlid=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	var flid = jQuery("#frmSkillAssessmentGrdEntry input[id='flid']").val();
	var reviewDate = jQuery('#hdndteSiamReviewdate').datebox('getValue');
	var filterstr="?q=2&flid="+flid+"&errlid="+unqPosn+"&reviewDate="+reviewDate;
	
//	var filterstr="q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
	var url="multipleSkillIndexEntry_input.grdenty";
	viewGrid(url,filterstr);
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
	var rowData = jQuery("#SkillAssesmentEnty").jqGrid('getRowData',rowId);
	var revType =rowData.REVTYPE5;
	if (revType=='S') {
		var mark = getSubPointMark(rowId);
		jQuery("#SkillAssesmentEnty").jqGrid('setCell', rowId, 'CHK'+colId, mark);
	}
	else
		jQuery("#SkillAssesmentEnty").jqGrid('setCell', rowId, 'CHK'+colId, '1');
	
	enableFields('chkEmpScore_'+(rowId+1)+'_'+colId);
}

function getSubMainNo(subMainNo) {
	var mainArr = subMainNo.split('-');
	return mainArr[0];
}
function getSubPointMark(rowId) {
	var noofSubPoints = 1;
	var allRows = jQuery("#SkillAssesmentEnty").jqGrid('getRowData');
	
	var rowData = jQuery("#SkillAssesmentEnty").jqGrid('getRowData',rowId);
	var subMainNo =rowData.SkillIndexAssessmentSheet3;
	subMainNo = getSubMainNo(subMainNo);
	
	for( var i = rowId-1; i >= 1; i-- ){
		rowData = jQuery("#SkillAssesmentEnty").jqGrid('getRowData',i);
		revType =rowData.REVTYPE5;
		prevSubMainNo =rowData.SkillIndexAssessmentSheet3;
		prevSubMainNo = getSubMainNo(prevSubMainNo);
		if (revType=='M' || subMainNo != prevSubMainNo) {
			rowId = parseInt(i)+1;
			i=0;
		}
	}
		
	rowData = jQuery("#SkillAssesmentEnty").jqGrid('getRowData',rowId);
	subMainNo =rowData.SkillIndexAssessmentSheet3;
	subMainNo = getSubMainNo(subMainNo);
	for( var i = rowId+1; i < allRows.length;i++){
		rowData = jQuery("#SkillAssesmentEnty").jqGrid('getRowData',i);
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
	
	jQuery("#SkillAssesmentEnty").jqGrid('setCell', rowId, 'CHK'+colId, ' ');
	var prevCriteria = '';
	var curCriteria = '';	
	var LocnId = jQuery("#frmSkillAssessmentGrdEntry input[id='location']").val();

	var empType=jQuery('#cmbSiamUniqueposid').combobox('getValue');
	
	var allRows = jQuery("#SkillAssesmentEnty").jqGrid('getRowData');
	/* // if(rowId==2){
		alert(12345);
		for( var i = colId; i <=allRows.length;i++){
			for( var j=rowId ;j<=allRows.length;j++){
			jQuery('#chkEmpScore_'+i+'_'+colId).attr('checked',false);
			disableField("SkillAssesmentEnty",'chkEmpScore_'+(i+1)+'_'+colId);

		}
		enableField("SkillAssesmentEnty",'chkEmpScore_'+rowId+'_'+colId);
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
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=21;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=29;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=38;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=46;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=53;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
		 
		
		}
		else if(empType=='ETPM0002'){
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=12;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=41;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=47;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
		 
		
		
		}
		
		else if(empType=='ETPM0003'){
			
			if(LocnId=='LCN0000003'){
				
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=7;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=39;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=45;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
		     }	 
			}				
			else{
				
			if(curCriteria=='SPO001'){
				
				for ( k=rowId;k<=7;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=13;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=20;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=28;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=34;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=40;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=46;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			
		 }
		}
		else {
			if(curCriteria=='SPO001'){
				for ( k=rowId;k<=6;k++){					
				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);

				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO002'){				
				for ( var k=rowId; k<=12;k++){
									
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO003'){				
				for ( var k=rowId;k<=19;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO004'){
				for (var k=rowId;k<=27;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO005'){
				for (var k=rowId;k<=33;k++){				
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO006'){
				for (var k=rowId;k<=41;k++){					
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
			else if (curCriteria=='SPO007'){
				for (var k=rowId;k<=47;k++){	
				jQuery('#chkEmpScore_'+k+'_'+colId).attr('checked',false);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', k, 'CHK'+colId, ' ');
				disableField("SkillAssesmentEnty",'chkEmpScore_'+k+'_'+colId);
				}
				enableFields('chkEmpScore_'+(rowId)+'_'+colId);
			}
						
		}
			i =allRows.length;
	}

		else {
			//	jQuery('#chkEmpScore_'+i+'_'+colId).attr('checked',false);
				disableField("SkillAssesmentEnty",'chkEmpScore_'+i+'_'+colId);
		}
		prevCriteria = curCriteria;
	}
}






/* function upEmployeegrid_selectRow(rowId) {
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
	//	var url="multipleSkillIndex_input.grdenty"; 
	//	viewGrid(url,filterstr);
} */


function viewGrid(url,filterstr){
	//alert(12345);

    processGridnew("multipleSkillIndexEntry_input.grdenty",filterstr,"SkillAssesmentEnty","SkillAssesmentEntypager","","","","ldCompl");
//alert(345 +"   "+filterstr);
}

function ldCompl() { 
	
	var allRows = jQuery("#SkillAssesmentEnty").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentEnty").jqGrid("getGridParam","colModel");
	//alert("entered one");
	var prevCriteria = '';
	var curCriteria = '';
	var k=null;
	//alert(allRows.length +"  ColName "+col.length);
	for( k=7;k<=col.length;k++){
	for( var i = 1; i <=allRows.length;i++){
		
		var row = allRows[i-1];		
		curCriteria = row["SIRMKEYID1"];
	//alert(curCriteria +" SIRMKEYID1")
		if (prevCriteria == '' || prevCriteria != curCriteria )
			prevCriteria = curCriteria; 
		else {
			if(jQuery('#chkEmpScore_'+i+'_'+k+'').is(':checked')==false )
				disableField("frmSkillAssessmentGrdEntry",'chkEmpScore_'+i+'_'+k+'');
		}
		
		prevCriteria = curCriteria;

	}
	}
	setFocusOnField('chkEmpScore_'+i+'_'+k+'');
}



// To Use the Select All Check Box By Kiran 
 /* 
 Commented not approved by BE cell
 jQuery("#chkSelectAll").click(function() {

	
	var allRows = jQuery("#SkillAssesmentEnty").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentEnty").jqGrid("getGridParam","colModel");
	alert(allRows +" all rows "+  col);
		
	var k=null;
	for( k=7;k<=col.length;k++){
		for( var i = 1; i <=allRows.length;i++){
			
			if(jQuery('#chkEmpScore_'+i+'_'+k+'').is(':checked')==false ){
			jQuery('#chkEmpScore_'+i+'_'+k+'').attr('checked',true);
			var rowData = jQuery("#SkillAssesmentEnty").jqGrid('getRowData',i);
			var revType =rowData.REVTYPE5;
			//enableFields("SkillAssesmentEnty",'chkEmpScore_'+i);
			if (revType=='S') {
				var mark = getSubPointMark(i);
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', i, 'CHK'+k, mark);
			}
			else
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', i, 'CHK'+k, '1');

			}
			
			 // To Uncheck all the rows commented by Kiran
			else {
				
			
				jQuery('#chkEmpScore_'+i+'_'+k+'').attr('checked',false);
				if(jQuery('#chkEmpScore_'+i+'_'+k+'').is(':checked')==false ){
				jQuery("#SkillAssesmentEnty").jqGrid('setCell', i, 'CHK'+k, ' ');
				
				}
			} 
		}
	}

	
}); */


function frmSkillAssessmentGrdEntry_successsCallback() { 
	
	closePopUpDialoge("divSkillIndexEntry");
	jQuery('#upMulEmployeegrid').trigger('reloadGrid');
	}

	

function frmSkillAssessmentGrdEntry_beforeSubmit()
{
	 // alert(345672);
	  //var gridData ='&detailData='+getDetailJson('SkillAssesmentEnty');
	  var detailJson = getDetailJson('SkillAssesmentEnty');
	 // alert(34567);
	  var cellId = jQuery("#frmSkillAssessmentGrdEntry input[id='cell']").val();
	  if (cellId=='' || cellId==' ') {
		  popupCommonErrorMsg("Select JH");
		  return false;
	  }
		  
	  if (detailJson==false) {
		  popupCommonErrorMsg(" Provide Score for minimum one Criteria.");
		  return false;
	  }
		  
	  var gridData = '&masterData='+getMasterJson('SkillAssesmentEnty')+'&detailData='+detailJson;
	  //alert(gridData);
	  show_winMask(1);
	  return gridData ; 
	//  jQuery("#preLodDiv").css('display','block');
	//   jQuery("#preLodDiv").addClass("tpm-loading");
	   
}

function getMasterJson(jqGridId){

	var allRows = jQuery("#SkillAssesmentEnty").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentEnty").jqGrid("getGridParam","colModel");
	
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
	var allRows = jQuery("#SkillAssesmentEnty").jqGrid('getRowData');
	var col=jQuery("#SkillAssesmentEnty").jqGrid("getGridParam","colModel");
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
<form id="frmSkillAssessmentGrdEntry" name="frmSkillAssessmentGrdEntry">
<div id="mainDiv" >
<div id="WrapperRpt" > 
<div>
<table style="width: 120%;">
<tr>
<td width="45%">
		<div id="frmSkillAssessmentGrdEntry" style="width: 200px; margin-left:4px;">
			<input type="hidden" id="location" name="cmbAplmLocationid"	value="" /> 
			<input type="hidden" id="factory" name="cmbAplmFactoryid"	value=""/>
			<input type="hidden" id="section" name="cmbAplmSectionid"	value=""/>
			<input type="hidden" id="cell" name="cmbAplmWherecellid"	value=""/>
			<input type="hidden" id="machine" name="cmbAplmWhichmachine" value=""/>
			<input type="hidden" id="flid" name="cmbSiamFlid" value="${requestScope.flId}"/>
			<div id="multiSkillAssementfunLocation" style="width: 100%; "></div>
			<span id="err_multiSkillAssementfunLocation" class="tpm-errormsg"> </span>
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
	<span style="padding-left:10px; Display:none">
		 <input type="button" id="btnView" name="btnView" class="easyui-button"  value="View" style=""/>
	</span>
	
</td>
</tr>
<tr>
<td >
<!-- 	<span>
<span style="padding-left:10px;">

<label> Select All</label>
</span>
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
    <table id="SkillAssesmentEnty" ><tr><td></td></tr></table>
	<div id="SkillAssesmentEntypager"></div>
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
<input type='hidden' id="hdnReviewdate" name="hdnReviewdate" value="${requestScope.reviewdate}" />
<input type='hidden' id="hdnUniquePosition" name="hdnUniquePosition" value="${requestScope.uniquePosition}" />
<input type='hidden' id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
<input type='hidden' id="hdnEmpKeyid" name="hdnEmpKeyid" value="${requestScope.empKeyid}" />

</div>
</form>
	
	