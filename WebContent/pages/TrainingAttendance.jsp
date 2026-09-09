<!-- POST ASSESMENT FOR ITC  -->
<script>
jQuery(document).ready(	function() {
  	initialiseForm('frmtrngattendnce');
	jQuery('#submitForm').val('frmtrngattendnce');
	formatDateBox('dteAsmmEvaluationDate', 'dd-MMM-yyyy');
	
	//alert(jQuery('#hdnAsmdBachKeyid').val());
	var facultyIds = jQuery("#cmbFacultyId").combobox('getValues');
	 //alert(facultyIds);
	 
	 fillComboBox("frmtrngattendnce", "cmbFacultyId", "facultyCombo.commonFilter","","",true);
	// fillComboBox("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.prgEnt");
	 fillComboBox("frmtrngattendnce", "cmbAsmmAssessedBy", "employee.commonFilter");

	 
	var factId = jQuery("#frmtrngattendnce input[id='factory']").val();
	var sectionId = jQuery("#frmtrngattendnce input[id='section']").val();
	var cellId = jQuery("#frmtrngattendnce input[id='cell']").val();
	var machId = jQuery("#frmtrngattendnce input[id='machine']").val();
	var flId = jQuery("#frmtrngattendnce input[id='flid']").val();
	var dataStr = "&factId=" + factId + "&sectionId="+ sectionId + "&cellId=" + cellId + "&machId="+ machId+"&flid="+flId;
	
	// fillComboBox("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.prgEnt?&flid="+flId); 
	 fillComboBox("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.empatt?&flid="+flId); 
	 fillComboBox("frmtrngattendnce","cmbBachKeyid","BachCombo.entbatch?batchProgKey=123" );	
		
	loadFunctionalLocation("pcmsfunLocation","functionalLoc.commonFilter", "frmtrngattendncepcmsfunLocationValues","frmtrngattendnce", dataStr);
	var progkeyid = getFieldValue('cmbProgKeyid'); //combo box value
	//alert(progkeyid);
	var datedata = getFieldValue('dteAsmmEvaluationDate');
	//alert(datedata);
	/*
	if(progkeyid==null || progkeyid=="" || progkeyid==" ")
		viewGrid("trngatndance_input.tatnd", 'q=2');
	else
		viewGrid("trngatndance_input.tatnd", 'q=2&progkeyid='+progkeyid+"&date="+datedata); //program fill
	if(progkeyid.trim()!= " " && batchid.trim()!= ' ')
		fillDetails(progkeyid);
	*/
		
	fileManagerPopUp("","ASSM","frmtrngattendnce","btnfilemgr","TmstFilemgr");
	
	
	jQuery("#cmbProgKeyid").combobox({onRequest:function( ) { 
		var flId = jQuery("#frmtrngattendnce input[id='flid']").val();
		return "&flid="+flId;
	}});
	if(getFieldValue("dteAsmmEvaluationDate").trim().length==0)
		fillWithCurrentDate('dteAsmmEvaluationDate');

	setTimeout(function() {
		var progId = jQuery('#cmbProgKeyid').val();
		if (progId.trim().length==0)
			viewGrid("trngatndance_input.tatnd", 'q&progkeyid=123');
		else {
			var batchId =getFieldValue("cmbBachKeyid");
			viewGrid("trngatndance_input.tatnd", 'q&progkeyid='+progId+"&bachId="+batchId);
		}

		var batchId =getFieldValue("cmbBachKeyid");
		var progkeyid = getFieldValue('cmbProgKeyid');
		if (batchId!=null && batchId!='' && batchId!=' ')
			jQuery('#hdnAsmdBachKeyid').val(batchId);
		if (progkeyid!=null && progkeyid!='' && progkeyid!=' ')
			jQuery('#hdnAsmdProgKeyid').val(progkeyid);

		/*setTimeout(function() {
			setFieldValue("cmbFacultyId", facultyIds,"frmtrngattendnce");
		},550);*/ 
	},550); 
	

});	

function AssDtlFileManger_formatter(cellValue, options, rowObject){
	var gridId = options.gid;
	var rowId= options.rowId;
	
	return '<input type="button" class="easyui-button" id="btnFileManger_'+rowId+'"  name="btnFileManger_'+rowId+'" value="..." onclick="addFileManager(\''+ gridId  + '\',\''+ rowId+'\');"; style="text-align:center;width:50px;height:20px"/>';
}
function addFileManager(gridId,rowId){
	var txtAsmdKeyid = jQuery("#TrainingGrid").jqGrid('getCell',rowId,'txtAsmdKeyid');
	var id = "btnFileManger_"+rowId;  
	if(txtAsmdKeyid != null && txtAsmdKeyid.trim().length>0)
		fileManagerPopUp(txtAsmdKeyid,"EMPASM",undefined,id,"","","",  "");	
	else{
		alert("Save Training Skill Assessment before adding files");
	}
			
}

function frmtrngattendnce_FuntLocHierarchy_SuccessCallBack(keyIds)
{	  
	    var flId = keyIds.flId; 
		//reloadCombo("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.prgEnt?&flid="+flId);
		var keyid =jQuery('#hdnAsmdAsmmKeyid').val();
		//if (keyid.trim().length==0)
	    	reloadCombo("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.empatt?&flid="+flId);
}

function loadComplete_val()
    {
		var row = jQuery("#TrainingGrid").jqGrid('getDataIDs');
		//alert(row.length);
		for(var i=0;i<row.length;i++){
			//alert(row[i]);
			var type = jQuery("#TrainingGrid").jqGrid('getCell',row[i],'type');
			jQuery('#txtType_6_'+row[i]).val(type);		
			var Select = jQuery("#TrainingGrid").jqGrid('getCell',row[i],'selectVal'); //select val
			//salert(Select);
			  if(Select=="1"){
			 jQuery('#employee_checkbox_'+row[i]).attr('checked',true);
		        }
		else{
			 jQuery('#employee_checkbox_'+row[i]).attr('checked',false);
			 }
				//alert(type);
		}	
}


/*var dtlKeyid=jQuery('#hdnAsmdAsmmKeyid').val();

viewGrid('q=2&dtlKeyid='+dtlKeyid+"date="+Date);*/

function viewGrid(url, filterString)
	{
		processGridnew(url,filterString,"TrainingGrid","pager","","","","loadComplete_val");
		return true;
	}
	
	function btnfilemgr_click()
	{
		//alert("11");
	   // var documentNo =jQuery("#hdnabnkeyID").val();
	   //getGridCell("kaizenEvalgrid",rowid,colModel[j+1].name)
	   var progkeyid = getFieldValue('cmbProgKeyid'); //combo box value
		if(progkeyid.trim().length>0)
		{
     			fileManagerPopUp(progkeyid,"ASSM","","","");
		}
		
	}
	function frmtrngattendnce_successsCallback(result)
	
	{
		navigateToPrevForm();
		jQuery("#TrainingGrid").trigger("reloadGrid");
	}
function gotFocuse(id){
	
	numericTextBox(id);

}
function chkboxCheck(rowId)
{
	jQuery("#TrainingGrid").jqGrid('setCell',rowId,'selectVal','1');
	//setFormater(gridId,formId,url,rowId,colName,idColName,width,multiple);
	//setFormater("TrainingGrid","" ,"",rowId,"SCOR","","42",false);
}

function checkBoxUnChecked(rowId){
	jQuery("#TrainingGrid").jqGrid('setCell',rowId,'selectVal','0');
	//removeFormater("TrainingGrid","" ,rowId,"","SCOR");
}

function frmtrngattendnce_beforeSubmit()
{
	
	var row=jQuery("#TrainingGrid").jqGrid('getDataIDs');//	row get data
  	for(var i=0;i<row.length;i++)
  	{
  		var rowid=row[i];
  		var scoreVal = jQuery('#txtAsmdScore_TrainingGrid_'+rowid).val();	//text value
  		if (parseInt(scoreVal)>100) {
  			alert('Assessment Score can not be more than 100 ');
  			jQuery('#txtAsmdScore_TrainingGrid_'+rowid).val('');
  			//setFocusOnField('#txtAsmdScore_TrainingGrid_'+rowid);
  			//return true;
  			i=row.length;
  		}
  	}
	//alert(jQuery('#hdnAsmdBachKeyid').val());
	var facultyIds = jQuery("#cmbFacultyId").combobox('getValues');
	//var ASSMASTERData  = '&MASTERDATA='+convertMASTERT();
	//var ASSdETAILData  = '&TrainingDetails='+convertDETAIL();  //detail
	//alert(ASSdETAILData);
	//alert(facultyIds);
      //master
 	var gridData = getGridSelectArray("TrainingGrid"); 
      
      if( gridData.trim().length>1  && gridData.trim()!="]"  ){ 
    	  gridData ="&assessGrdData="+gridData;
	  }else{ 
		  alert('Select Employee');
    	  return false;
      }
      var progkeyid = getFieldValue('cmbProgKeyid');
      if (progkeyid.trim().length==0){
    	  alert('Select Batch');
    	  return false;
      }
      
       //alert(gridData+"&facultyIds="+facultyIds);
      return gridData+"&facultyIds="+facultyIds;
		
	}
function convertDETAIL()
	{
	//alert(convertDETAIL);
	        var row=jQuery("#TrainingGrid").jqGrid('getDataIDs');//	row get data
			var col=jQuery("#TrainingGrid").jqGrid("getGridParam","colModel");// col get data
			var jsonArrO='[';
    
       for(var i=0;i<row.length;i++)
		{
                        
			var rowid=row[i];
			var value = jQuery("#TrainingGrid").jqGrid('getCell',rowid,"SELCTVAL");//select val
			var scoreVal = jQuery('#TrainingGridtxtScore_'+rowid).val();	//text value
			// alert(scoreVal);
			var Type = jQuery('#txtType_6_'+rowid).val();	
			var progkeyid = getFieldValue('cmbProgKeyid');
			var batchId =getFieldValue("cmbBachKeyid");
			//alert(value);
			var masKeyid = jQuery("#TrainingGrid").jqGrid('getCell',rowid,"MASTE");
			//alert(masKeyid);
			var dtlKeyid = jQuery("#TrainingGrid").jqGrid('getCell',rowid,"DTL");
			// alert(dtlKeyid);
		  if(value == '1' && (masKeyid==null || masKeyid.trim()=="")) {
	    		    flg=true;
					jsonArrO+= '{';
					jsonArrO += '"txtAsmdAsmmKeyid":"'+ ""+'",';
					jsonArrO += '"cmbProgKeyid":"'+ progkeyid+'",';  //detal
					jsonArrO += '"cmbBachKeyid":"'+ batchId+'",';  //detal
					jsonArrO += '"txtAsmdScore":"'+ scoreVal+'",';
					jsonArrO += '"txtAsmdType":"'+ Type+'",';
					jsonArrO += '"Flg":"I"';
					jsonArrO+= '},';
					
				}
		else if(value == '1' && masKeyid!=null && masKeyid.trim()!="") {
	  	    
 		    flg=true;
				jsonArrO+= '{';
				
				jsonArrO += '"cmbProgKeyid":"'+ progkeyid+'",'; 
				jsonArrO += '"cmbBachKeyid":"'+ batchId+'",';  //detal
				jsonArrO += '"txtAsmdAsmmKeyid":"'+ masKeyid+'",';
			     jsonArrO += '"txtAsmdScore":"'+ scoreVal+'",';
				jsonArrO += '"txtAsmdType":"'+ Type+'",';
			  jsonArrO += '"txtAsmdKeyid":"'+ dtlKeyid+'",';
			jsonArrO += '"Flg":"U"';
			jsonArrO+= '},';
			
	  				}
		else if(value == '0' && masKeyid!=null && masKeyid.trim()!="")
			{
			
	 		    flg=true;
					jsonArrO+= '{';
					
					jsonArrO += '"txtAsmdAsmmKeyid":"'+ masKeyid+'",';
					jsonArrO += '"cmbProgKeyid":"'+ progkeyid+'",';  //detal
					jsonArrO += '"cmbBachKeyid":"'+ batchId+'",';  //detal
					jsonArrO += '"txtAsmdScore":"'+ scoreVal+'",';
					jsonArrO += '"txtAsmdType":"'+ ""+'",';
			    jsonArrO += '"txtAsmdKeyid":"'+ dtlKeyid+'",';
			jsonArrO += '"Flg":"D"';
			jsonArrO+= '},';
			
			}
					}
					jsonArrO = jsonArrO.slice(0, -1) + "]";
					jsonArrO = (jsonArrO != ']'?jsonArrO:"");

	 return jsonArrO;
	}
   function convertMASTERT()    //master J
    {   
	//alert(convertMASTERT);
	     var row=jQuery("#TrainingGrid").jqGrid('getDataIDs');//	row get data
		 var col=jQuery("#TrainingGrid").jqGrid("getGridParam","colModel");// col get data
		 var datedata = getFieldValue('dteAsmmEvaluationDate');//jQuery("#dteAsmmEvaluationDate").datebox(getValue);	//text value
	     var jsonArrO='[';
		 for(var i=0;i<row.length;i++)
			{
		     var rowid=row[i];
		     var value = jQuery("#TrainingGrid").jqGrid('getCell',rowid,"SELCTVAL");//select val
		     var empKeyid = jQuery("#TrainingGrid").jqGrid('getCell',rowid,"EMPID");//select val
		     //alert(empKeyid);
		     var masKeyid = jQuery("#TrainingGrid").jqGrid('getCell',rowid,"MASTE");
		  //  alert(masKeyid);
		  //  alert(10);
		     //  var prog= getFieldValue('cmbProgKeyid'); //combo box value
		     //var duration = jQuery("#txtTmstDuration").val();	//text value
		     var faculty= getFieldValue('cmbFacultyId'); //combo box value
	           //alert("date  "+datedata);
		     if( value.trim()  == "1" && masKeyid.trim() == undefined)
				//alert(value.trim() );alert(masKeyid.trim());
		        if( value.trim()  == "1"&& (masKeyid.trim() == undefined || masKeyid==null || masKeyid.trim()==""))
				{	         
		    	      jsonArrO+= '{';
		    	      jsonArrO += '"txtAsmmEmpmKeyid":"'+empKeyid+'",';
		    	  	  jsonArrO += '"txtAsmdAsmmKeyid":"'+ masKeyid+'",';
		    	     // jsonArrO += '"txtTmstDuration":"'+ duration+'",';
		    	      jsonArrO += '"cmbFacultyId":"'+faculty+'",';
				      jsonArrO += '"dteAsmmEvaluationDate":"'+datedata+'",';   
					  jsonArrO+= '},';
				 
					}else  if( value.trim()  == "1"&& (masKeyid!=null || masKeyid.trim()!=""))
					{	         
				          //s alert(empKeyid);
				          // alert(empKeyid+"  "+datedata);
			    	      jsonArrO+= '{';
			    	     // jsonArrO += '"txtAsmmKeyid":"'+masKeyid+'",';
			    	      jsonArrO += '"txtAsmmEmpmKeyid":"'+empKeyid+'",';
			    	      jsonArrO += '"txtAsmdAsmmKeyid":"'+ masKeyid+'",';
			    	     // jsonArrO += '"txtTmstDuration":"'+ duration+'",';
			    	      jsonArrO += '"cmbFacultyId":"'+faculty+'",';
					      jsonArrO += '"dteAsmmEvaluationDate":"'+datedata+'",';   
						  jsonArrO+= '},';
					 
						}
					   
					    	else if(value == '0' && masKeyid!=null && masKeyid.trim()!="")
							{
							
					 		    flg=true;
					 		   jsonArrO+= '{';
					    	      //jsonArrO += '"txtAsmmKeyid":"'+masKeyid+'",';
					    	      jsonArrO += '"txtAsmmEmpmKeyid":"'+""+'",';
					    	  	jsonArrO += '"txtAsmdAsmmKeyid":"'+ masKeyid+'",';
					    	     // jsonArrO += '"txtTmstDuration":"'+ duration+'",';
					    	      jsonArrO += '"cmbFacultyId":"'+faculty+'",';
							      jsonArrO += '"dteAsmmEvaluationDate":"'+datedata+'",';   
								  jsonArrO+= '},';
							}
							}
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
    return jsonArrO;
 
 }

function ViewProgram(){
	   var progkeyid = getFieldValue('cmbProgKeyid');
		viewGrid("trngatndance_input.tatnd",'q=2&progkeyid='+progkeyid);
}

function  frmtrngattendncecmbProgKeyid_onLoadSuccess() {
	var progId =getFieldValue("cmbProgKeyid");
	if (progId.length>2) {
		readOnlyFields("cmbProgKeyid");
		//jQuery('#cmbBachKeyid').combbox('clear');
		reloadCombo("frmtrngattendnce","cmbBachKeyid","BachCombo.entbatch?&batchProgKey="+progId);
	}
}
function  frmtrngattendncecmbProgKeyid_onSelect(record) {
	reloadCombo("frmtrngattendnce","cmbBachKeyid","BachCombo.entbatch?&batchProgKey="+record.id);
	//fillDetails(record.id);
	//viewGrid("trngatndance_input.tatnd",'q=2&progkeyid='+record.id+"&date=");
	jQuery('#hdnAsmdProgKeyid').val(record.id);
	//readOnlyFields("cmbProgKeyid");
	jQuery('#cmbBachKeyid').combbox('clear');
}

function  frmtrngattendncecmbBachKeyid_onLoadSuccess() {
	var batchId =getFieldValue("cmbBachKeyid");
	var progId  = jQuery('#cmbProgKeyid').combobox('getValue');
	if (batchId.length>2) { 
		readOnlyFields("cmbBachKeyid");
		fillDetails("");
		//viewGrid("trngatndance_input.tatnd",'q=2&progkeyid='+progId+"&date="+"&bachId="+batchId);
	}	
}
function  frmtrngattendncecmbBachKeyid_onSelect(record) {
	fillDetails("");
	var progId  = jQuery('#cmbProgKeyid').combobox('getValue');
	viewGrid("trngatndance_input.tatnd",'q=2&progkeyid='+progId+"&date="+"&bachId="+record.id);
	jQuery('#hdnAsmdBachKeyid').val(record.id);
	//alert(jQuery('#hdnAsmdBachKeyid').val());
	//readOnlyFields("cmbProgKeyid");
}

function fillDetails(progKeyid){
	var progKeyid = getFieldValue("cmbProgKeyid");
	var bachKeyid = getFieldValue("cmbBachKeyid");
	//alert(bachKeyid);
	processAjaxCalls("TrainingAttence_input.tatnd?&progKeyid="+progKeyid+"&bachKeyid="+bachKeyid,"","selected_onsuccesscallback");
}
function selected_onsuccesscallback(result){
	 var value = result[0][1].split(',');
	 //alert(value); 
	jQuery("#txtTmstDuration").val(result[0][0]);
	//jQuery("#cmbFacultyId").combobox('setValues',value);
	 //alert(result[0][1]);
	var facultyIds = jQuery("#cmbFacultyId").combobox('getValues');
	if (facultyIds.length==0)
		setFieldValue("cmbFacultyId", result[0][1],"frmtrngattendnce");
	
	setFieldValue("dteAsmmEvaluationDate", result[0][3],"frmtrngattendnce");
	if(result[0][2].trim().length>0) {
	   //loadFunctionalLocation("pcmsfunLocation","functionalLoc.commonFilter", "frmtrngattendncepcmsfunLocationValues","frmtrngattendnce","&flid="+result[0][2]  );	   
	   readOnlyFields("txtTmstDuration");
	   //readOnlyFields("cmbFacultyId");
	   readOnlyFields("dteAsmmEvaluationDate");
	   
	}
	//jQuery("#dteAsmmEvaluationDate").datebox('setValue',result[0][0]);
	//jQuery("#txtAsmmEvaluationDesc").val(result[0][1]);
	//jQuery("#txtAsmmEvaluationDesc").val(result[0][1]);
//	jQuery("#cmbOlqmType").combobox('setValue',result[0][3]);
	
}
function TrainingGrid_onChange(result){
	var fieldID = result.txtId;
	var rowid = result.rowId;
	var grdId = result.gridId;

	if (fieldID != "txtAsmdScore")
		return false;
	var defaultCutof = jQuery('#txtCutoff').val();
	if ( isNaN(defaultCutof) || defaultCutof==null || defaultCutof=="0" || defaultCutof=='' || defaultCutof==' ' )
		defaultCutof = jQuery('#hdnDefaultCutoff').val();
	defaultCutof = parseInt(defaultCutof);
	var score = jQuery("#"+fieldID+"_"+grdId+"_"+rowid).val();
	//alert('onChange' + score + defaultCutof);
	score = parseInt(score);
	var result="Pass";
	if(score<defaultCutof)
		result="Fail";
	rowid = parseInt(rowid) ;
	 
	//setFieldValue('txtAsmdResult_TrainingGrid_'+rowid,result);
	var tdc = jQuery("#"+grdId + ' tr[id='+ rowid +"]").find('td[aria-describedby='+grdId+'_txtAsmdResult]');
	tdc.attr('title',result); 
	tdc.html(result);
	
}

function TrainingGrid_onBlur(result){
	var fieldID = result.txtId;
	var rowid = result.rowId;
	var grdId = result.gridId;
	if (fieldID != "txtAsmdScore")
		return false;
	
	var defaultCutof = jQuery('#txtCutoff').val();
	if ( isNaN(defaultCutof) || defaultCutof==null || defaultCutof=="0" || defaultCutof=='' || defaultCutof==' ' )
		defaultCutof = jQuery('#hdnDefaultCutoff').val();
	var score = jQuery("#"+fieldID+"_"+grdId+"_"+rowid).val();
	score = parseInt(score);
	//alert( 'onBlur' + score + defaultCutof);
	var result="Fail";
	if(score>=defaultCutof)
		result="Pass";
	rowid = parseInt(rowid) ;
	 
	//setFieldValue('txtAsmdResult_TrainingGrid_'+rowid,result);
	var tdc = jQuery("#"+grdId + ' tr[id='+ rowid +"]").find('td[aria-describedby='+grdId+'_txtAsmdResult]');
	tdc.attr('title',result); 
	tdc.html(result);
	
}
function txtFormatter(id, options, rowObject) {
	/*
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	 //alert("col:::::::"+columnNo);
	if(columnNo==1){//alert(0 );alert(columnName);
		return '<input id="employee_checkbox_'+ id +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+id + '\');}else{checkBoxUnChecked(\''+ id +'\')}"/>';
	}
	if(columnNo==6){
	var idval='txtType_';
	return '<select id='+idval+columnNo+'_'+id +'   class="easyui-text" value='+rowObject[5]+'  style="width:100px; height:20px"  ><option value="O">Oral</option><option value="W">Written</option>';
	}
	var idval='txtScore_';
	if(columnNo==5)
	return '<input id='+idval+columnNo + '_'+id +'  type="text"  onfocus=gotFocuse(this.id); value="'+rowObject[4]+'" maxlength="3" style="width: 50px; height:20px; text-align:right;" / >';		

	return '<input id='+idval+columnNo + '_'+id +'  type="text"  value="" maxlength="30" style="width: 50px; height:20px; text-align:left;" / >';		
*/}
	
</script>
<form id="frmtrngattendnce">
	<div id="wrapper">
		
				<div id="frmtrngattendnceFuntKeyIds"  >							
								<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
								<input type="hidden" id="section" name="cmbsection"  value=""></input>
								<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
								<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
								<input type="hidden" id="flid"    name="cmbAsmmTrarKeyid"     value="${requestScope.flid}"></input>
								<input type="hidden" id="elementId"    name="cmbAsmmElementId"     value=""></input>						
							</div>						
						<div id="pcmsfunLocation" style="width:89.2%;width:84%\9;"></div>	
					
					 <table>
					    <tr>
					    
					  <td>
					 <div class="easyui-paddingbfpx">
							<label  class ="mandatory-lbl">Program</label>
							</div>
							<div class="easyui-paddingbfpx">
							<input id="cmbProgKeyid" class="easyui-combobox" name="cmbProgKeyid"   value="${requestScope.progkeyId}"  style=" width : 200px;" />
						</div>
					</td>
					  
					  <td>
					 <div class="easyui-paddingbfpx" style="padding-left: 10px;">
							<label  class ="mandatory-lbl">Batch</label>
							</div>
							<div class="easyui-paddingbfpx">
							<input id="cmbBachKeyid" name="cmbBachKeyid"   class="easyui-combobox"   style="width: 215px;" value="${requestScope.batchId}" >
						</div>
					</td>
					
					<td style="padding-left: 20px" >
						<div class="easyui-paddingbfpx" >
							<label  class =" ">Duration (Mins)</label>
						</div>
						<div class="easyui-paddingbfpx">
							<input id="txtTmstDuration" type="text" name="txtTmstDuration" value="${requestScope.duration}" style=" width : 80px;" class="easyui-text" />
						</div>
					</td>
						
						
						
					<td style="padding-left: 20px" >
					    <div class="easyui-paddingbfpx">
							<label  class ="">Faculty</label>
						</div>
						<div class="easyui-paddingbfpx">
							<input id="cmbFacultyId" class="easyui-combobox" name="cmbFacultyId"  value="${requestScope.faculty}"   style=" width:200px;" />
<!--					<span style="margin-top:0px;margin-left:200px;position:absolute;">-->
<!--					            <input	type="button" onclick="ViewProgram()" style="width:80px;height:21px;" class="easyui-button" id="btnClear"	name="btnClear" value="View" />-->
<!--				    </span>-->
						</div>
					</td>
					<td style="padding-left: 20px" >
					   <div class="easyui-paddingbfpx" >
						  <label  class ="">Date</label>
					     </div>
						  <div class="easyui-paddingbfpx">
							<input id="dteAsmmEvaluationDate" name="dteAsmmEvaluationDate" class="easyui-datebox" style=" width : 90px;"  value="${requestScope.date}" />
						 </div>
					</td>
						
					<td style="padding-left:21px;" rowspan='2' valign="top">
					   <div class="easyui-paddingbfpx" >
						  <label  class =" ">Description</label>
					     </div>
						  <div class="easyui-paddingbfpx">
							<textarea id="txtAsmmEvaluationDesc" name="txtAsmmEvaluationDesc"  style=" width : 180px;" >${requestScope.assmDesc}</textarea>
						 </div>
					</td>	
				</tr>
				<tr>
					<td style="" valign="top" colspan='2'>
					    <div class="easyui-paddingbfpx">
							<label  class ="mandatory-lbl">Assesed By</label>
							<span style="margin-left:150px;">
								 <label  class =" ">Cut Off</label>
							</span>
						</div>
						<div class="easyui-paddingbfpx">
							<input id="cmbAsmmAssessedBy" class="easyui-combobox" name="cmbAsmmAssessedBy" style=" width:200px;" value="${requestScope.assesdBY}"/>
							<span style="margin-top:0px;margin-left:20px;position:absolute;">
							    <input type="text" class="easyui-text" id="txtCutoff" name="txtCutoff"  style=" width : 56px;" value="${requestScope.cutoff}"/>
						    </span>
						    <span class="tpm-errormsg" id="err_cmbAsmmAssessedBy" style=" "> </span>
						</div>
					</td>
				 	<td>
	            		<div style=" padding-left:15px; margin-top:-6px;position:relative; ">
						 <span  id="TmstFilemgr" style="position:absolute;" >
						 </span> 
						</div>	
					</td>
               </tr>	
			</table>
		     
			<div style="float: left;"> 
			<table id='TrainingGrid'>
			<tr><td></td></tr>
			</table><div id='pager'></div>
			</div>
</div>
		

	<input id="hdnAsmdProgKeyid"  name="hdnAsmdProgKeyid" type="hidden"   value="${requestScope.newEntTlAssessmentdtl.asmdProgKeyid}" / >
	<input id="hdnAsmdBachKeyid"  name="hdnAsmdBachKeyid" type="hidden"   value="${requestScope.newEntTlAssessmentdtl.asmdBachKeyid}" / >
	<input id="hdnAsmdAsmmKeyid"  name="hdnAsmdAsmmKeyid" type="hidden"   value="${requestScope.newEntTlAssessmentdtl.asmdAsmmKeyid}" / > 
	<input type="hidden" class="easyui-text" id="hdnDefaultCutoff" name="hdnDefaultCutoff"  value="${requestScope.cutoff}"/>	
    <input id="mode" type="hidden" >
</form>
