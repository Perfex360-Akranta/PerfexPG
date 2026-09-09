<script>
var textOldVal=0;
var glbFlid  = "";
var glbCtqId = "";
var glbProcessid = "";
var glbDate = "";

jQuery(document).ready(function(){
     
	initialiseForm('frmQa');
	jQuery('#submitForm').val('frmQa'); 

	jQuery('#divTitle').hide();
	
	formatDateBox('dteQammDate','dd-MMM-yyyy');
	
	var date=jQuery('#dteQammDate').datebox('getValue');
	if (date=="" || date==" " )
		fillWithCurrentDate('dteQammDate');
	
	var filterStr = jQuery('#hdndatStr').val(); 
	//processid=PRS0000001&tenStpFlid=FNL000000124&ctqid=KPIV0001&Preparedbyid=EMP00001&Defectid=QPDM0003
	//var processid = getFilterValue(filterStr+'&', 'processid');
	var processid =jQuery('#cmbProcess').combobox('getValue');
	var defectid =getFilterValue(filterStr+'&', 'Defectid');	
	var btnName = jQuery("#hdnBtnName").val();
	
	//alert( );
	var flid = jQuery("#frmQa input[id='flid']").val();
	
	fillComboBox("frmQa","cmbProcess","process.commonFilter?flid="+flid);
	
	fillComboBox("frmQa","cmbQamxCtq","getCtq.qams?q&flid="+flid);
	fillComboBox("frmQa","cmbGradeSpec","comboGradeSpec.commonFilter?q&flid="+flid);
	var ds ="?&flid="+flid+"&childFlids=Y"; 
	//fillComboBox("frmQa","cmbQamxPreparedby","employee.commonFilter");
	fillComboBox("frmQa","cmbQamxPreparedby","roleBasedEmployeeCombo.commonFilter"+ds);
	setFieldValue("cmbQamxCtq",  getFilterValue(filterStr+'&', 'ctqid'));
	setFieldValue("cmbQamxPreparedby",  getFilterValue(filterStr+'&', 'Preparedbyid'));
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function() {		
		processAjaxCalls("openFile.file?fileName=QAMatrix.xls", "", "", "", "", "new");					
	});

	var factId = jQuery("#frmQa input[id='factory']").val();
	var sectionId = jQuery("#frmQa input[id='section']").val();
	var cellId = jQuery("#frmQa input[id='cell']").val();
	var machId = jQuery("#frmQa input[id='machine']").val();
	var flid = jQuery("#frmQa input[id='flid']").val();
	if(flid.trim().length<=0)
		flid = getFilterValue(filterStr+'&', 'tenStpFlid');
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("qaMatrixfunLocation","functionalLoc.commonFilter","frmQafunLocationValues","frmQa",dataStr);
	//alert(processid+" - " +defectid );				  
	
	var mainForm = jQuery('#txtMainform').val();
	if(mainForm != true){
		//jQuery("#matrix").attr('id','wrapperRpt');
	}
	//fileManagerPopUp("","tenStep","frmQa","btnFilManage","tenStepFilemgr");

	
});
	
	function qaLoadcomplete(){
		
		hideJqGridRow("Qamatrixgrid", 1);
		hideJqGridRow("Qamatrixgrid", 2);
		hideJqGridRow("Qamatrixgrid", 3);
		hideJqGridRow("Qamatrixgrid", 4); 
		
		var headerRowSpan = jQuery('#CH1-3').attr('rowspan');
		if(parseInt(headerRowSpan)>=2){
			jQuery('#CH1-3').css('padding-top','20');
		}
		 var row = jQuery("#Qamatrixgrid").jqGrid('getDataIDs');
		 var cm = jQuery("#Qamatrixgrid").jqGrid("getGridParam", "colModel");
		 //jQuery("#Qamatrixgrid  tr[id=1]").find('td[aria-describedby=Qamatrixgrid_txtctq ]').css('text-align','left');
		 //jQuery("#Qamatrixgrid  tr[id=2]").find('td[aria-describedby=Qamatrixgrid_txtctq ]').css('text-align','left');
		  var newAria ="";
		  /*jQuery("#Qamatrixgrid  tr[id='2']").find('td[role=gridcell]').each (function()
			{  
 				var aria=jQuery(this).attr('aria-describedby');
 				  if(-1 != aria.indexOf("txtQAMXRATING") ){
 					  aria = aria.split('_');
 					  newAria = aria[0]+"_txtQAMXTARGETRATING_"+aria[2]+"_"+aria[3];
 					  jQuery(this).attr('aria-describedby',newAria);
 				  } 
 				 //var newaria=jQuery(this).attr('aria-describedby');
			 });*/
			 
		var rowIds = jQuery("#Qamatrixgrid").getDataIDs();
		//alert(rowIds.length);
		for(var i = 5; i<rowIds.length+1; i++) {
			jQuery("#Qamatrixgrid").jqGrid('setCell',i	,"rn",parseInt(i)-4);
		}
 	  }
	 
function btnFilManage_click(){
    
    var documentNo = "1"; //for prtoType use Only

	//if(documentNo != null && documentNo != ''){
		//fileManagerPopUp("","TenStep","","","");
	//}
}

function dteQammDate_onChange(date) {
	setTitle();
	viewGrid();	
}


function frmQacmbProcess_onSelect(record) {
	jQuery('#hdnSelectedProcessid').val(record.id);
	viewGrid();	
	setTitle();
}

function frmQacmbGradeSpec_onSelect(record) {
	jQuery('#hdnSelectedGradeSpec').val(record.id);
	viewGrid();	
	setTitle();
}

function frmQacmbGradeSpec_onClear() {
	jQuery('#hdnSelectedGradeSpec').val('');
	viewGrid();	
	setTitle();
}



function setTitle()  {
	var title = jQuery('#cmbQamxCtq').combobox('getText');
	title+= ' - ' + jQuery('#cmbGradeSpec').combobox('getText');
	title+= ' - ' + jQuery('#cmbProcess').combobox('getText');
	jQuery('#txtQammTitle').val(title);
}


function frmQacmbQamxPreparedby_onLoadSuccess() {
	setTimeout(function() {	
	viewGrid();	
	
	if (jQuery('#hdnSelectedDate').val()!='' && jQuery('#hdnSelectedDate').val()!=' ' 
		&& jQuery('#hdnSelectedDate').val()!=null)
			setFieldValue("dteQammDate",jQuery('#hdnSelectedDate').val());
	if (jQuery('#hdnSelectedCtqid').val()!='' && jQuery('#hdnSelectedCtqid').val()!=' ' 
		&& jQuery('#hdnSelectedCtqid').val()!=null)
		setFieldValue("cmbQamxCtq",jQuery('#hdnSelectedCtqid').val());
	if (jQuery('#hdnSelectedProcessid').val()!='' && jQuery('#hdnSelectedProcessid').val()!=' ' 
		&& jQuery('#hdnSelectedProcessid').val()!=null)
		setFieldValue("cmbProcess",jQuery('#hdnSelectedProcessid').val());
	if (jQuery('#hdnSelectedGradeSpec').val()!='' && jQuery('#hdnSelectedGradeSpec').val()!=' ' 
		&& jQuery('#hdnSelectedGradeSpec').val()!=null)	
		setFieldValue("cmbGradeSpec",jQuery('#hdnSelectedGradeSpec').val());
	
	},350);
}


function frmQa_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	jQuery('#hdnSelectedFlid').val(keyIds.flId);
	reloadCombo("frmQa","cmbQamxCtq","getCtq.qams?&flid="+keyIds.flId );
	reloadCombo("frmQa","cmbProcess","process.commonFilter?&flid="+keyIds.flId );
	reloadCombo("frmQa","cmbGradeSpec","comboGradeSpec.commonFilter?&flid="+keyIds.flId);
	var ds ="?&flid="+keyIds.flId+"&childFlids=Y"; 
	//fillComboBox("frmQa","cmbQamxPreparedby","employee.commonFilter");
	reloadCombo("frmQa","cmbQamxPreparedby","roleBasedEmployeeCombo.commonFilter"+ds);
	
}

function frmQacmbQamxCtq_onSelect(record) {
	jQuery('#hdnSelectedCtqid').val(record.id);
	var date=jQuery('#dteQammDate').datebox('getValue');
	jQuery('#hdnSelectedDate').val(date);
	recallValues();
	setTitle();
}

function recallValues() { 
	var ctq =jQuery('#cmbQamxCtq').combobox('getValue');
	var flid = jQuery("#frmQa input[id='flid']").val();
	var date=jQuery('#dteQammDate').datebox('getValue');
	var ds = "?q=2&ctqid="+ctq+"&flid="+flid+"&date="+date;
	processAjaxCalls("getQammKeyid.qams",ds,'qammKeyid_OnSuccess','qamm_OnError');
}


function qammKeyid_OnSuccess(result) {
	jQuery('#hdnQammKeyid').val(result.qammKeyid);
	viewGrid();
}

function frmQa_successsCallback(result) {
	
	alert(result.msg );
	
	navigateToPrevForm("");
	/* var msg =result.msg + ",  Do you want Start 10 Step Process ";
	if(confirm(msg) == false)				
		return;
	fnOpenTenStepProcess(); */
	
}

function viewGrid(){
	 
	var filterStr = jQuery('#hdndatStr').val();
	//alert(filterStr);
	//processid=PRS0000001&tenStpFlid=FNL000000124&ctqid=KPIV0001&Preparedbyid=EMP00001&Defectid=QPDM0003
	//var processid = getFilterValue(filterStr+'&', 'processid');
	var processid = jQuery('#cmbProcess').combobox('getValue');
	var qammKeyid = jQuery('#hdnQammKeyid').val();
	var gradeSpec = jQuery('#cmbGradeSpec').combobox('getValue');
	
	 /* if(processid=="" || processid==" ") {
		alert('Select Process');
		return false;
	} */
	 
	 var ctq =jQuery('#cmbQamxCtq').combobox('getValue');
	 var flid = jQuery("#frmQa input[id='flid']").val();
	 
	filterStr+="&processid="+processid+"&gradeSpec="+gradeSpec+"&qammKeyid="+qammKeyid;
	filterStr+="&ctqid="+ctq+"&tenStpFlid="+flid;
	//alert(filterStr);
	processGridnew("Qamatrixstep_input.qams","q=2"+filterStr,"Qamatrixgrid","pager","","","","qaLoadcomplete");

}

function txtFormatter(id, options, rowObject)
{	
	var columnKey="";
	var color='';
	var id = options.rowId;
	
	var columnName = options.colModel.name;	
	
	//alert("columnName::::::::"+columnName);
	var columnNo=columnName.substring(columnName.indexOf("_")==1);
	
	var idval;
	if(columnName=="CriticalityofSub-process2"){
		idval='txtCriticality_';
		columnNo='process';
		//return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 150px;text-align:right;" maxlength="2" value="'+rowObject[2]+'" onfocus="gotFocuse('+id+','+columnNo+','+columnKey+')" onChange="outFocus('+id+','+columnNo+','+columnKey+')">';
		return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 150px;text-align:right;" maxlength="2" value="'+rowObject[2]+'" ">';
      }
	else if(columnName=="Co-relationRating(1-Low,3-Moderate,9-High)3" ){
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +'  type="text" keyId="'+columnKey+'" value="'+rowObject[3]+'" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		
		
		}
	else if(columnName=="Co-relationRating(1-Low,3-Moderate,9-High)4" ){
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +' type="text" keyId="'+columnKey+'" value="'+rowObject[4]+'" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		
		
		}
	else if(columnName=="Co-relationRating(1-Low,3-Moderate,9-High)5" ){
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +'  type="text" keyId="'+columnKey+'" value="'+rowObject[5]+'" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		
		
		}
	else if(columnName=="Co-relationRating(1-Low,3-Moderate,9-High)6" ){
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +'  type="text" keyId="'+columnKey+'" value="'+rowObject[6]+'" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		
		
		}
	
	else{
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +' type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		

		}
	
}

function outFocus(id,colId,rowId) {
	var clid="txtKkp_"+colId + "_"+id;
	var totid="txtCriticality_process_"+id;
	var  colValue = jQuery("#"+clid).val();
	
	var res=jQuery("#"+totid).val();
	if(undefined == res || res.trim().length<=0 )
		 res=0;
	 var mul= parseInt(colValue)*parseInt(colId);
		
	var result=parseInt(res)+parseInt(mul);   
    jQuery("#"+totid).val(result);
}

function gotFocuse(id,colId,rowId){
	numericTextBox(id);
}


jQuery("#btnView").click(function()	{
	
	var processid =jQuery('#cmbProcess').combobox('getValue');
	var ctq =jQuery('#cmbQamxCtq').combobox('getValue');
	var preparedBy =jQuery('#cmbQamxPreparedby').combobox('getValue');
	
	/* if(processid=="" || processid==" ") {
		alert('Select Process');
		return ;
	} */
	if(ctq=="" || ctq==" ") {
		popupCommonErrorMsg('Select CTQ');
		return ;
	}
	if(preparedBy=="" || preparedBy==" ") {
		popupCommonErrorMsg('Select prepared By');
		return ;
	}
		viewGrid();
});


jQuery("#btnProcess").click(function(){
		openMasterForm('loadmst_grid.gnms?q=2&menuCaption=ProcessMaster&menuName=MNUQTPROCESSMASTER&isMMC=Y&loadFormArg=ProcessMaster_input.pro%3F',frmMode.create,'frmQa','Process','mstFrm');
});

jQuery("#btnSubProcess").click(function(){
	openMasterForm('loadmst_grid.gnms?q=2&menuCaption=SubProcessMaster&menuName=MNUQTSUBPROCESSMASTER&isMMC=Y&loadFormArg=MNUQTSUBPROCESSMASTER%3F',frmMode.create,'frmQa','Sub-Process','mstFrm');
});


jQuery("#btnDefectLink").click(function()
{
	//fnOpenTenStepProcess();
	var flid = jQuery("#frmQa input[id='flid']").val();
	var ctqid = getFieldValue("cmbQamxCtq");
	var gradeSpec = getFieldValue("cmbGradeSpec");
	if (ctqid==null || ctqid==' ') ctqid='-';
	if (gradeSpec==null || gradeSpec==' ') gradeSpec='-';
	var ds = "?q=2&flid="+flid+"&ctqid="+ctqid+"&gradeSpec="+gradeSpec;
	LoadPopUp("divDefectNew","DefectSeverity_input.tsdi"+ds, true,"90%","90%","2%","10px", "defectSeverity_Callback","CTQ - Defect Mode Link",false,true);
	
});

function newMstFrm_onClose() {
	//alert(1);
	jQuery('#submitForm').val('frmQa');
	reloadComboBox();
	return true;
}

function divDefectNew_onClose() {			
	//reloadCombo("frmQa","cmbQamxCtq","getCtq.qams");
	jQuery('#submitForm').val('frmQa');
	reloadComboBox();
	return true;
}

function reloadComboBox() {
	/*
	glbFlid = jQuery("#frmQa input[id='flid']").val();
	glbCtqId = getFieldValue("cmbQamxCtq");
	glbProcessid =jQuery('#cmbProcess').combobox('getValue');
	glbDate=jQuery('#dteQammDate').datebox('getValue');
	*/

	//initialiseForm('frmQa');
	//jQuery('#submitForm').val('frmQa');

	setTimeout(function() {
		refreshForm();
		setTimeout(function() {
			/*reloadCombo("frmQa","cmbQamxCtq","getCtq.qams?&flid="+flid );
			reloadCombo("frmQa","cmbProcess","process.commonFilter?q&flid="+flid );
			reloadCombo("frmQa","cmbGradeSpec","comboGradeSpec.commonFilter?&flid="+flid);
			*/
			//initialiseForm('frmQa');
			//jQuery('#submitForm').val('frmQa');
			//alert(jQuery('#hdnSelectedFlid').val());
			var dataStr ="&flid="+jQuery('#hdnSelectedFlid').val();
			//alert(dataStr);
			loadFunctionalLocation("qaMatrixfunLocation","functionalLoc.commonFilter","frmQafunLocationValues","frmQa",dataStr);
			/*
			setFieldValue("dteQammDate",jQuery('#hdnSelectedDate').val());
			setFieldValue("cmbQamxCtq",jQuery('#hdnSelectedCtqid').val());
			setFieldValue("cmbProcess",jQuery('#hdnSelectedProcessid').val());
			setFieldValue("cmbGradeSpec",jQuery('#hdnSelectedGradeSpec').val());
			*/
			recallValues();
			setTitle();
			viewGrid();	
		},400);
		
	},600); 
}
function defectSeverity_Callback() {
}

function fnOpenTenStepProcess() {
	//var url = "tenStepChart.tsdi";  
	//var url = "tenstepApproval_input.tsdi";
	var url = "Qamatrixsteps_input.qams";
	//var url = "updateQaMatrix_input.tsdi";
	//showGraphData(url);
	var CtqId = getFieldValue("cmbQamxCtq");
	var processid =jQuery('#cmbProcess').combobox('getValue');
	var flid = jQuery("#frmQa input[id='flid']").val();
	//var filterString = "?flid="+flid+"&CtqId="+CtqId+"&processId="+processid;
	var filterString = selectedQaMAtrix();
	if (filterString==false)
		return false;
	//navigateToNextForm(url,"Select Project",null,{"filterString":filterString});
	filterString=filterString+"&processid="+processid ;
	processAjaxCalls("updateQaMatrix_input.tsdi",filterString,'update_OnSuccess','update_OnError');
	

}

function update_OnSuccess(result){
	var filterString = selectedQaMAtrix();
	//alert('filterString'+filterString);
	if(result.msg == "dataUpdated" )
		navigateToNextForm("tenstepApproval_input.tsdi?q=2"+filterString,"Approval",null,{"filterString":filterString});
	else
		alert("Not Updates");   
}

function selectedQaMAtrix(){
	
	var grid = jQuery("#Qamatrixgrid");
	var colModels = grid.jqGrid("getGridParam", "colModel");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
    
	if( selArray.length <= 0 ){
		popupCommonErrorMsg('Select Process');
    	  return false;
      }
	
        var row=grid.jqGrid('getDataIDs');		
		var rowid="";
		var QaMatrixKeyid = "";
	 	for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	var checkVal =jQuery("#jqg_Qamatrixgrid_"+(i+1)).is(':checked');
		 	if(true == checkVal){
		 		QaMatrixKeyid +=grid.jqGrid('getCell', rowid,"TXTQAMMKEYID")+","; 		
		 	}
		}
	 	//alert(QaMatrixKeyid);
	 	return "&qamKeyid="+QaMatrixKeyid;
}


function Qamatrixgrid_selectRow(id){
	disableField("frmQa",'txtctq_Qamatrixgrid_'+id);
	jQuery('#txtctq_Qamatrixgrid_'+id).css({'border':'none','cursor':'default','text-transform':'none'});
	jQuery('#txtctq_Qamatrixgrid_2').css({'border':'none','cursor':'default','text-transform':'none','text-align':'left'});
	
	var rowId=id; 
	jQuery("#frmQa input[class='easyui-text']").css('text-align','center');
	/*
		var row=jQuery("#Qamatrixgrid").jqGrid('getDataIDs');
		var colModel = jQuery("#Qamatrixgrid").jqGrid("getGridParam","colModel" );
		for(var j=4;j<colModel.length-2;j++){   
 		var paramid=colModel[j].name+"_grdCriticallity_"+rowId;
 		jQuery("#frmQa input[id='"+paramid+"']").css('text-align','center');
	}*/
}


//onkeyup=allow139("'+ id+ '","'+ columnid+ '")

 

function Qamatrixgrid_Test_onBlur(record){
	
	var gridId = record.gridId; 
	var txtId  = record.txtId;
	var rowId  = record.rowId;
	var countCols = jQuery('#Qamatrixgrid').jqGrid('getGridParam', 'colNames').length;
	var colIds  = jQuery('#Qamatrixgrid').jqGrid('getGridParam', 'colNames');
	 var totSev ="";
	//DEFM000007_txtQAMXRATING_1_Qamatrixgrid_5
	for (var i=7;i<= countCols-1;i++) {
		totSev = jQuery("#Qamatrixgrid").jqGrid('getCell',rowId,i);
		//alert(totSev );	
	}
	//alert('totSev'+totSev);
	
	return;
	var txtBoxId = txtId+"_"+gridId+"_"+rowId;
   var txtVal = jQuery('#'+txtBoxId).val();
  var sevRatting = txtId.split("_");
  txtVal = txtVal|0; 
  var mulVal =( parseInt(txtVal) * parseInt(sevRatting[2]));
  var ctqVal = jQuery('#txtctq_Qamatrixgrid_'+rowId).val();
  if(ctqVal.trim().length<=0)
	  ctqVal = 0;
	  var  consVal =(parseInt(ctqVal)+parseInt(mulVal)) ;
		  if( mulVal!="" ||mulVal!=null) {
			 if(consVal == 0 || consVal == "0" || isNaN(consVal)  )   
				consVal =mulVal;
			   
			 jQuery('#txtctq_Qamatrixgrid_'+rowId).val(consVal);
			 
	 	 
	}
}

function Qamatrixgrid_onchange(record){
	var gridId = record.gridId; 
	var txtId  = record.txtId;
	var rowId  = record.rowId;
	var txtBoxId = txtId+"_"+gridId+"_"+rowId;
	var txtVal = jQuery('#'+txtBoxId).val();
	
	if(txtVal==1 || txtVal==3 || txtVal==9){ 
			
	}
	else {
		jQuery('#'+txtBoxId).val('');
		setFocusOnField(txtBoxId);
		return false;
	  }
		 		  
}

function Qamatrixgrid_onFocus(record){
	 var gridId = record.gridId; 
	var txtId  = record.txtId;
	var rowId  = record.rowId;
	var txtBoxId = txtId+"_"+gridId+"_"+rowId;
	//alert(txtId);
	if (txtId!="txtctq" ) {
		 var foucusMode = jQuery('#hdnSetGot').val();
		if (foucusMode=="0") { 
			var txtVal = jQuery('#'+txtBoxId).val();
			textOldVal=txtVal;
			jQuery('#hdnSetGot').val("1");
		 }
		/*else
			jQuery('#hdnSetGot').val("0"); */
	}
	else {
		/* textOldVal="0";
		jQuery('#hdnSetGot').val("0"); */
	} 
}

function Qamatrixgrid_onBlur(record){
	//Qamatrixgrid_DEFM000007_txtQAMXRATING_1
	var gridId = record.gridId; 
	var txtId  = record.txtId;
	var rowId  = record.rowId;
	//var cm = jQuery("#Qamatrixgrid").jqGrid("getGridParam", "colModel");
	 	  var txtBoxId = txtId+"_"+gridId+"_"+rowId;
		  var txtVal = jQuery('#'+txtBoxId).val();
		  jQuery('#'+txtBoxId).val(txtVal.trim());
		  if(txtVal==1 || txtVal==3 || txtVal==9){ 
			  var sevRatting = txtId.split("_");
			  txtVal = txtVal|0;
			  if(isNaN(txtVal) || txtVal=='' || txtVal	==' ' )
				  txtVal="0";  
			  var targetRatting = jQuery("#Qamatrixgrid").jqGrid('getCell',3,sevRatting[0]+"_txtQAMXRATING_"+sevRatting[2]);
			  if(isNaN(targetRatting) || targetRatting=='' || targetRatting	==' ' )
				  txtVal="0";
			  //var mulVal =( parseInt(txtVal) * parseInt(sevRatting[2]));
			  var mulVal =( parseInt(txtVal) * parseInt(targetRatting));
			  var ctqVal = jQuery('#txtctq_Qamatrixgrid_'+rowId).val();
			  //alert(txtVal+"  "+mulVal+"  "+ctqVal);
			  if(ctqVal.trim().length<=0)
				  ctqVal = 0;
			   
			  var  consVal =(parseInt(ctqVal)+parseInt(mulVal)) ;
		 	  if( mulVal!="" ||mulVal!=null) 
		 	  {
		 		 if(consVal == 0 || consVal == "0" || isNaN(consVal)  )   
		 			consVal =mulVal;
		 		
		 		if(isNaN(textOldVal) || textOldVal=='' || textOldVal==' ' )
					textOldVal="0";
		 		consVal = parseInt(consVal) - ( parseInt(textOldVal) * parseInt(targetRatting)) ;
		 		
		 		if(isNaN(consVal) || consVal=='' || consVal	==' ' )
		 			consVal="0";
		 		//alert(consVal);
		 		 jQuery('#txtctq_Qamatrixgrid_'+rowId).val(consVal);
		 		jQuery('#hdnSetGot').val("0");
		 	}
		  }
		 else {
			 if (txtId!="txtctq" ) {
				var sevRatting = txtId.split("_");
		 		var targetRatting = jQuery("#Qamatrixgrid").jqGrid('getCell',3,sevRatting[0]+"_txtQAMXRATING_"+sevRatting[2]);
		 		if(isNaN(targetRatting) || targetRatting=='' || targetRatting==' ' )
		 			targetRatting="0";
		 		var consVal = jQuery('#txtctq_Qamatrixgrid_'+rowId).val();
		 		if(isNaN(consVal) || consVal=='' || consVal==' ' )
		 			consVal="0";
		 		
		 		if(isNaN(textOldVal) || textOldVal=='' || textOldVal==' ' )
		 			textOldVal="0";
		 		
		 		if(isNaN(targetRatting) || targetRatting=='' || targetRatting==' ' )
		 			textOldVal="0";
		 		
		 		var oldVal = parseInt(textOldVal) * parseInt(targetRatting);
		 		//alert(oldVal);
		 		consVal = parseInt(consVal) - ( oldVal) ;
		 		//alert(consVal);
		 		if(isNaN(consVal) || consVal=='' || consVal==' ' )
		 			consVal="0";
		 		//alert(consVal);
		 		jQuery('#txtctq_Qamatrixgrid_'+rowId).val(consVal);
			 	jQuery('#hdnSetGot').val("0");
			 	jQuery('#'+txtBoxId).val('');
		 		setFocusOnField(txtBoxId);
		 		
		 		return false;
			 }
		}
		 		  
}

function frmQa_beforeDelete() {
	
	var msg = " Are you sure want to delete QA Matrix ";
	if(confirm(msg) == false)				
		return;
	
	var qammKeyid = jQuery('#hdnQammKeyid').val();
	if (qammKeyid==null ||qammKeyid=='' || qammKeyid==' ')
		return false;
	else	
	 return "&qammKeyid="+qammKeyid;	
}

function frmQa_deleteSuccessCallback(result) {
	alert('Data Deleted Successfully');
	//popupCommonErrorMsg(result.successData.msg);
	navigateToPrevForm("");
}

function frmQa_beforeSubmit(){
	 
	var grid = jQuery("#Qamatrixgrid");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
     
	var processid =jQuery('#cmbProcess').combobox('getValue');
	if(processid=="" || processid==" ") {
		popupCommonErrorMsg('Select Process');
		return false;
	}
	
	if( selArray.length <= 0 ) {
		popupCommonErrorMsg("Select Row To Save");
		return "";
	}
	//var saveData = convertGrdDatToJson("Qamatrixgrid");
	// return "&qagridData="+saveData;
	
	var mstData = convertGrdDatToJsonMaster("Qamatrixgrid");
	var detData = convertGrdDatToJsonDetails("Qamatrixgrid");
	//alert('mstData'+mstData);
	//alert('detData'+detData);
	if (detData==false) {	
		popupCommonErrorMsg('Enter Criticality Value for Defects ');
		return false;
	}
	 return "&qaMstData="+mstData+"&qaDetData="+detData;
}




function convertGrdDatToJsonMaster(gridId){
	var grid = jQuery("#"+gridId);
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
       
     if( selArray.length <= 0 ) return "";
        var row=jQuery("#"+gridId).jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
	 	var CtqId = getFieldValue("cmbQamxCtq");
	 	var preparedBy =getFieldValue("cmbQamxPreparedby");
	 	var flid = jQuery("#frmQa input[id='flid']").val();
	 	
	 	for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	var checkVal =jQuery("#jqg_Qamatrixgrid_"+row[i]).is(':checked');
		 	if(checkVal){
			
		 		var processKeyid =jQuery("#"+gridId).jqGrid('getCell', rowid,"TXTQPOMKEYID");
		 		var gradeSpec = getFieldValue("cmbGradeSpec");
		 		var subProcessKeyid =jQuery("#"+gridId).jqGrid('getCell',rowid,"TXTSUBPKEYID");
		 		var totSeverity = jQuery('#txtctq_Qamatrixgrid_'+rowid).val();
		 		var qammKeyid =jQuery("#"+gridId).jqGrid('getCell',rowid,"TXTQAMMKEYID");
		 		
		 		/* if (qammKeyid=="" || qammKeyid==" ")
		 			qammKeyid= jQuery('#hdnQammKeyid').val();
		 		 */
		 		var date=jQuery('#dteQammDate').datebox('getValue');
		 		
			 	if(rowid>2){  
			 		var title = jQuery('#txtQammTitle').val();
				 	jsonArr+= '{';
				 		jsonArr += '"txtQammKeyid":"'+qammKeyid+'",';
				 		jsonArr += '"txtQammDate":"'+date+'",';
				 		jsonArr += '"txtQammTitle":"'+title+'",';
				 		jsonArr += '"txtQammGradespecid":"'+gradeSpec+'",';
				 		jsonArr += '"txtQammProcessid":"'+processKeyid+'",';
						jsonArr += '"txtQammSubprocessid":"'+subProcessKeyid+'",';
						jsonArr += '"txtQammCtq":"'+CtqId+'",';
						jsonArr += '"txtQammFlid":"'+flid+'",';
						jsonArr += '"txtQammPreparedby":"'+preparedBy+'",';
						jsonArr += '"txtQammTotSeverity":"'+totSeverity+'" ';
						jsonArr+='},';					
				}
	 		}
		}
	 	if (jsonArr=="["){
			jsonArr=="";
			return false;
		}
		else{ 
			jsonArr = jsonArr.slice(0,-1) ;
			jsonArr = jsonArr.slice(0,-2)+ "}]";
			return jsonArr;
		}
	
}

function convertGrdDatToJsonDetails(gridId){
	var grid = jQuery("#"+gridId);
	var colModels = grid.jqGrid("getGridParam", "colModel");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
       
      if( selArray.length <= 0 ) return "";
        var row=jQuery("#"+gridId).jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
	 	var colModel = jQuery("#"+gridId).jqGrid("getGridParam","colModel" );
	 	var CtqId = getFieldValue("cmbQamxCtq");
	 	var preparedBy =getFieldValue("cmbQamxPreparedby");
	 	var flid = jQuery("#frmQa input[id='flid']").val();
	 	
	 	var mstId ="-1";
	 	for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	var checkVal =jQuery("#jqg_Qamatrixgrid_"+row[i]).is(':checked');
		 	var SeverityVal= "";
		 	var OccurenceVal= "";
		 	var TotalRattingVal= "";
		 	
		 	if(checkVal){
		 		mstId = parseInt(mstId)+1;
			 	for(var j=7;j<colModel.length;j++){   
				 	var criVal=(colModel[j].name).split("_");
				 	
			 		//var processKeyid =jQuery("#"+gridId).jqGrid('getCell', rowid,"TXTQPOMKEYID");
			 		//var subProcessKeyid =jQuery("#"+gridId).jqGrid('getCell',rowid,"TXTSUBPKEYID");
			 		//var occurenceid= criVal[0]+"_txtQAMXRATING_"+criVal[2]+"_"+(parseInt(j)-7)+"_"+gridId+"_1";
			 		var occurenceVal = "";
			 		//SeverityVal =jQuery("#"+gridId).jqGrid('getCell', 3,criVal[0]+"_txtQAMXRATING_"+(parseInt(j)-7));
			 		var targetRatting = "";
			 		var severityVal = '';
				 	
				 	if(rowid>4){  
				 		var paramid=criVal[0]+"_"+criVal[1]+"_"+(parseInt(j)-7)+"_"+gridId+"_"+rowid;
				 		//alert(paramid);
				 		var parameterval=jQuery("#"+paramid).val();
				 		parameterval=parameterval|0;
				 		//alert(parameterval);
			 		 if(parseInt(parameterval)>0){ 
			 			 //alert('(parseInt(i)-4)'+(parseInt(i)-4));
			 			severityVal = jQuery("#txtctq_Qamatrixgrid_"+rowid).val();
			 			targetRatting = jQuery("#"+gridId).jqGrid('getCell',3,criVal[0]+"_txtQAMXRATING_"+(parseInt(j)-7));
				 		occurenceVal = jQuery("#"+gridId).jqGrid('getCell', 2,criVal[0]+"_txtQAMXRATING_"+(parseInt(j)-7));	   
					 	jsonArr+= '{';
							jsonArr += '"txtQamdOccurence":"'+occurenceVal+'",';
							jsonArr += '"txtQamdTargetrating":"'+targetRatting+'",';
						 	jsonArr += '"txtQamdDefectmodeid":"'+criVal[0]+'",';
							jsonArr += '"txtQamdRating":"'+parameterval+'",'; 
							jsonArr += '"txtQamdSeverity":"'+severityVal+'",';
							jsonArr += '"txtQamdTempfield5":"'+mstId+'" ';
							jsonArr+='},';
			 		 }
			 		 else {
			 			 /* alert(1);
			 			popupCommonErrorMsg('Enter Rating for all defects');
			 			return false; */
			 		 }
			 			 
				 	}
					
			      }
	 	     }
		}
	 	//alert(jsonArr);
	 	if (jsonArr=="["){
			jsonArr=="";
			return false;
		}
		else{ 
			jsonArr = jsonArr.slice(0,-1) ;
			jsonArr = jsonArr.slice(0,-2)+ "}]";
			return jsonArr;
		}
	
}

function convertGrdDatToJson(gridId){
	var grid = jQuery("#"+gridId);
	var colModels = grid.jqGrid("getGridParam", "colModel");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
       
      if( selArray.length <= 0 ) return "";
        var row=jQuery("#"+gridId).jqGrid('getDataIDs');	
		var rowid="";
		var jsonArr='[';
	 	var colModel = jQuery("#"+gridId).jqGrid("getGridParam","colModel" );
	 	var CtqId = getFieldValue("cmbQamxCtq");
	 	var preparedBy =getFieldValue("cmbQamxPreparedby");
	 	var flid = jQuery("#frmQa input[id='flid']").val();
	 	
	 	for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	var checkVal =jQuery("#jqg_Qamatrixgrid_"+row[i]).is(':checked');
		 	var SeverityVal= "";
		 	var OccurenceVal= "";
		 	var TotalRattingVal= "";
		 	if(checkVal){
			 	for(var j=7;j<colModel.length;j++){   
				 	var criVal=(colModel[j].name).split("_");
				 	
			 		var processKeyid =jQuery("#"+gridId).jqGrid('getCell', rowid,"TXTQPOMKEYID");
			 		var subProcessKeyid =jQuery("#"+gridId).jqGrid('getCell',rowid,"TXTSUBPKEYID");
			 		var occurenceid= criVal[0]+"_txtQAMXRATING_"+criVal[2]+"_"+(j-7)+"_"+gridId+"_1";
			 		//var occurenceVal = jQuery('#'+occurenceid).val();
			 		var occurenceVal = "";
			 		/* if(occurenceVal.trim().length<=0)
			 		{ alert(1+" "+j);
			 			alert("Enter Occurence");
			 			return ;
			 		} */ 
			 		SeverityVal =jQuery("#"+gridId).jqGrid('getCell', 1,criVal[0]+"_txtQAMXRATING_"+(j-7));
				 //	OccurenceVal=jQuery("#"+gridId).jqGrid('getCell', 2,criVal[0]+"_txtQAMXRATING_"+(j-7));
				//	TotalRattingVal=jQuery("#"+gridId).jqGrid('getCell',3,criVal[0]+"_txtQAMXRATING_"+(j-7));
			 		//var targetRatting = jQuery("#"+gridId).jqGrid('getCell', 2,colModel[j].name);
			 		var targetRatting = "";
			 		 
			 		//txtQAMXRATING_5_Qamatrixgrid_1
			 		//alert(j+" "+"txtQAMXRATING_5_1_Qamatrixgrid_1"+" - "+"txtQAMXRATING_"+criVal[1]+"_"+gridId+"_"+rowid);
			 		var severityVal = '';
			 		//alert(rowid);
			 		//alert( " - "+rowid >2);			 		
				 	
				 	if(rowid>2){  
				 		var paramid=criVal[0]+"_"+criVal[1]+"_"+(j-7)+"_"+gridId+"_"+rowid;
				 		 
				 		var parameterval=jQuery("#"+paramid).val();
				 		parameterval=parameterval|0;
				 		 //alert("occurenceVal   "+occurenceVal);
			 		 if(parameterval!=0){ 
			 			severityVal = jQuery("#txtctq_Qamatrixgrid_"+rowid).val();
			 			targetRatting = jQuery("#"+gridId).jqGrid('getCell',3,criVal[0]+"_txtQAMXRATING_"+(j-7));
				 		occurenceVal = jQuery("#"+gridId).jqGrid('getCell', 2,criVal[0]+"_txtQAMXRATING_"+(j-7));	   
					 	jsonArr+= '{';
							jsonArr += '"txtQamxOccurence":"'+occurenceVal+'",';
							jsonArr += '"txtQamxTargetrating":"'+targetRatting+'",';
						 	jsonArr += '"txtQamxProcessid":"'+processKeyid+'",';
							jsonArr += '"txtQamxSubprocessid":"'+subProcessKeyid+'",';
							jsonArr += '"txtQamxCtq":"'+CtqId+'",';
							jsonArr += '"txtQamxFlid":"'+flid+'",';
							jsonArr += '"txtQamxPreparedby":"'+preparedBy+'",';
							jsonArr += '"txtQamxDefectmodeid":"'+criVal[0]+'",';
							jsonArr += '"txtQamxRating":"'+parameterval+'",'; 
							jsonArr += '"txtQamxSeverity":"'+severityVal+'",';
						//	alert(processKeyid+" - "+subProcessKeyid+" - "+parameterval+" - "+targetRatting+" - "+severityVal);
						 
							jsonArr+='},';
			 		 }
				 	}
					
			      }
	 	     }
		}
	 	if (jsonArr=="["){
			jsonArr=="";
			return false;
		}
		else{ 
			jsonArr = jsonArr.slice(0,-1) ;
			jsonArr = jsonArr.slice(0,-2)+ "}]";
	//	 alert(jsonArr);
			return jsonArr;
		}
	
}

</script>

<form name="frmQa" id="frmQa">
<div id="matrix" style="padding: 10px;"  >
	<table>
	<tr>
	<td colspan="0">
		<div  id="frmQaFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
				<input type="hidden" id="section" name="cmbhdnSectionid" value=""  ></input>
				<input type="hidden" id="cell" name="cmbhdnCellid" value=""  ></input>
				<input type="hidden" id="machine" name="cmbhdnEquipmentid1" value=""  ></input>
				<input type="hidden" id="flid" name="cmbhdnFlid" value=""  ></input>
			</div>
			<div id="qaMatrixfunLocation" style="width: 90%; "></div>
		</div>
	</td>
	
	<td style="padding-left: 10px;">
			<div>
				<label class="mandatory-lbl">Date</label>
			</div>
			
			<div>
				<input type="text" class="easyui-text" id="dteQammDate" name="dteQammDate" style='width:100px;' value="${requestScope.date}"/>
			</div>

	</td>
	</tr>
	</table>
	<table style="width: 105%;">
	<tr>
				<td style="padding-left: 0px;">
				<div id="divTitle">
					<div>
						<label class="mandatory-lbl">Title</label>
					</div>
					<div >
						<input type="text" class="easyui-text" id="txtQammTitle" name="txtQammTitle" style='width:320px;' value="${requestScope.title}"/>
					</div>
				</div>
				</td>

				<td style="padding-left: 10px;">
					<div>
						<label class="mandatory-lbl">CTQ</label>
					</div>
					<div>
						<input class="easyui-combobox" id="cmbQamxCtq" name="cmbQamxCtq" style='width:180px;' value="${requestScope.ctqId}"/>
					</div>
				</td>

				<td style="padding-left: 10px;">
					<div>
						<label class="">Grade Spec</label>
					</div>
					<div>
						<input class="easyui-combobox" id="cmbGradeSpec" name="cmbGradeSpec" style='width:180px;' value="${requestScope.gradeSpec}"/>
					</div>
				</td>
				
				<td style="padding-left: 10px;">
					<div>
						<label class="mandatory-lbl">Process</label>
					</div>
					<div>
						<input class="easyui-combobox" id="cmbProcess" name="cmbProcess" style='width:180px;' value="${requestScope.processId}"/>
					</div>
				</td>
				
				<td style="vertical-align: bottom;">
						
						<span style="padding-left: 2px;"> 
							<input type="button" class="easyui-button" id="btnProcess"  name="btnProcess" value="Process" style="height: 20px;"/> 
						</span>
						
						<span style="padding-left: 2px;"> 
							<input type="button" class="easyui-button" id="btnSubProcess"	name="btnSubProcess" value="SubProcess" style="height: 20px;"/> 
						</span>
				
				</td>	
							
				<td style="padding-left: 10px;">
					<div>
						<label class="mandatory-lbl">Prepared By</label>
					</div>
					<div>
						<input class="easyui-combobox" id="cmbQamxPreparedby" name="cmbQamxPreparedby" style='width:180px;' value="${requestScope.preparedbyId}"/>
					</div>
				</td>
				<td valign='bottom' style="padding-left: 10px;">
				 <div>
					<input type="button" class="easyui-button" id="btnView"	name="btnView" value="View" style="height: 25px;"/>
				 </div>				 
				</td>

				<td valign='bottom' style="padding-left: 10px;">
				 <div>
<!-- 					<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/> -->
					<input type="button" class="easyui-button" id="btnDefectLink"	name="btnDefectLink" value="CTQ - Defect Mode Link" style="height: 25px;"/>
				 </div>
				 
				</td>
			</tr>
		</table>
			 <div style="position:relative;">
			 <span  id="tenStepFilemgr" style="position:absolute;right:40px;right:70px\9;top:-30px;">
             </span> 
        </div>
		
<table id='Qamatrixgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
<div id='jsonOb' ></div>
<input type="hidden" id="mode"/>
<input type="hidden" id="hdndatStr" value='${requestScope.datStr}'/>

<input type="hidden" name = "hdnSetGot" id ="hdnSetGot" value="0"/>
<input type="hidden" name = "txtMainform" id ="txtMainform" value="${requestScope.mainForm }"/>
<input type="hidden" name = "hdnQammKeyid" id ="hdnQammKeyid" value="${requestScope.qammKeyid }"/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>