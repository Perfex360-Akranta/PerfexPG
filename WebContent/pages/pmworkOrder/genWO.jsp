 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 <style>
 .leftmtsect{
 	margin-left:8%;
 	margin-left:8.4%\8;
 }
 .leftAssm{
 margin-left:8.4%;
 margin-left:8.6%\9;
 }
  .leftplanDur{
 	padding-left:9%;
 	padding-left:9%\9;
 }
  .leftcompby{
 	padding-left:3.4%;
 	padding-left:3.4%\9;
 }
 .cbmreading{
 	text-align:right;
 }
 .cbmreadingtext{
 	text-align:left;
 	font-size:10px;
 	font-weight:bold;
 }
 .cbmreadingheader{
 	text-align:center;
 	font-size:12px;
 	font-weight:bold;
 }
 </style>

<script type="text/javascript">	
jQuery.noConflict();
var currentDateCBM ;
  jQuery(document).ready(function(){
	  initialiseForm('frmWOGen');
	 // initialiseForm("frmSapDtls");
	  jQuery('#submitForm').val('frmWOGen'); // set the id of form to submit.indexOf('&cmbAssmbid=')--&cmbFactid=
	  var filterString = jQuery('#hdnfilter_string').val();
		
	  var assmId = getFilterValue(filterString+"&","cmbAssmbid")  ;//filterString.substring(filterString.indexOf('ASM'),filterString.indexOf('&cmbFactid='));
	 
	  var factId = jQuery("#frmWOGen input[id='factory']").val();
	  var sectionId = jQuery("#frmWOGen input[id='section']").val();
	  var cellId = jQuery("#frmWOGen input[id='cell']").val();
	  var flid = jQuery("#frmWOGen input[id='flid']").val();
	  var machId =getFilterValue(filterString+"&","cmbMchid")  ;
	 // alert(filterString.substring(103,111) );
	fillComboBox("frmWOGen","cmbWogenCostCenter","costCenter.commonFilter");
	fillComboBox("frmWOGen","cmbWogenTradeid","combo_pmsdTrade.prv");
	fillComboBox("frmWOGen","cmbWogenAssemblyid","assembly.commonFilter");
	 setFieldValue('cmbWogenAssemblyid', assmId,'frmWOGen');
	  var weekNO = getFilterValue(filterString+"&","weekNo");//filterString.substring('112');
	  //weekNO = weekNO.replace('=',' : '); 
	  var monthstr =getFilterValue(filterString+"&","dtFromDate"); // filterString.substring(103,111);
	  jQuery('#weekNo').val(weekNO);
	
	  jQuery('#txtmonthYr').val(monthstr);
	  if( ! ( machId != '' && machId != ' ' && machId != null) )
		  machId = jQuery("#frmWOGen input[id='machine']").val();	  	
	  var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
	    loadFunctionalLocation("genwfunLocation","functionalLoc.brdn","genwfunLocationValues","frmWOGen",dataStr);
	   
	    var whenBack = jQuery('#hdnpersistdata').val();	 
  	//when coming back from resource planning this will happen
  	 if(whenBack.trim().length>0){
  		jQuery('#load1stGrid').css('display','none');
		jQuery('#load2ndGrid').css('display','block');
		jQuery(".spnComplBtn").css('display','block').css('margin-left','1060px').css('vertical-align','top').css('margin-top','-28px');
		jQuery(".spnComplfields").css('display','block').css('margin-left','780px').css('vertical-align','top').css('margin-top','-20px');
		var title = 'Cost Info';
		jQuery('#tabWorkOrder').tabs('select',title );
		processGridnew('completeWO_input.mpc','?q=2&filterString='+jQuery('#hdnfilter_string').val(),"CompWOGrid","","","CompWODBLClick","","CompWOGridComplete");
  	 }
  	  	jQuery.cookie("filterString",filterString);
	   processGridnew('genWO_input.mpc','?filterString='+filterString ,"GenWOGrid","GenWOPager","","genWODBLClick","","genWOGridComplete");
	   if(screen.width <= 1024){
		   jQuery('.leftmtsect').css('margin-left','13%');
		   jQuery('.leftAssm').css('margin-left','12%');
		   jQuery('.leftplanDur').css('padding-left','23%');
		   jQuery('.leftcompby').css('padding-left','6%'); 
	  }

	   /**** cost WO ****/
		jQuery('#btngenCostInfoWo').click(function(){
			  var woMachId = jQuery("#frmWOGen input[id='machine']").val();
			  var assmId = getFieldValue("cmbWogenAssemblyid","frmWOGen");
			  var tradeId = getFieldValue("cmbWogenTradeid","frmWOGen");
			  var dataStr = "&mchid="+woMachId+"&assmId="+assmId+"&tradeId="+tradeId+"&DocNo="+jQuery('#txtWogenwoNo').val()+"&DocType=PM";
			  var jsonstr = '{"from":"wogen"}';
			  var persistdata =  jQuery.parseJSON(jsonstr);
			 navigateToNextForm('costSummary_input.crt?&formType=Actual&fromForm=WorkOrder'+dataStr,"Resource Planning - Planned Value",null,{"from":"wogen"} );
			//LoadPopUp("loadCostInfo", "costSummary_input.crt?q=2", true,"92%","84%","-11%","2%", "costinfo_successCallBack","Cost Information");
					
			}); 
	/*******/
	
	
	/********CHECKBOX********/
	
    /*	jQuery("#tabWorkOrder").tabs({ onSelect:function(title){     
        					
    		//jQuery(".tabs-title").bind("click", function(event){
    				//if(jQuery(this).text() == "Downtime Breakup")
    				alert("INSIDE THE TABS");
    			if(title == "SAP Information" )
    				{ 
    				
    				 
    					 	var doctype=jQuery('#hdnUrl').val();
    					 	
    					    	//var refDocType=null;
    					    	if(doctype=="PM")
    					    		refDocType="PM";
    					    		//alert("test");
    					    		
    					    	if(jQuery('#chkformatter').is(':checked')==true){
    					    		 if(jQuery('#chkSapinfo').is(':checked')==true) {    	    	    				
    				        			LoadForm("loadSapinfoDiv","prevloadSapinfoText","sapInfo_input.brdn?refDocType="+refDocType,"","","");
    					jQuery('#chkSapinfo').prop('disabled', true);
    					
    					jQuery('#loadCommunicDiv').prop('disabled', true);
    					}
    				  	 else alert("Select the Submit To sap");
    					    	}
    					    	 else alert("Due You want to Submit To sap");
               
                        }
    			else if(title == "Communication" )
 				{	
 						var workorderNo = jQuery('#txtWogenwoNo').val();
 						var employeeId = jQuery('#hdnemployeeId').val();
 						if(employeeId == undefined || employeeId == 'undefined' )
 							employeeId = null;
 						//LoadPopUp("loadCommunication", "comm_Text.brdn?q=2&formMode=create&mode=pm&workorderNo="+workorderNo+"&employeeId="+employeeId, true,"65%","70%","0%","10%", "communication_successCallBack","Communication");
 						LoadForm("loadCommunicDiv","prevloadComText","comm_Text.brdn?&formMode=create&mode=pm&workorderNo="+workorderNo+"&employeeId="+employeeId,"","communication_successCallBack","communicationTextErr");
 				
 					   //processGridnew('comm_view.brdn','?q=2&bdId='+jQuery('#cmbbdmsKeyid').combobox('getValue')+'&commFlag=no',"CommnGrid","CommnPager","","","","","");
 				}
 				else if(title == "Cost Info" )
 				{
 					
 					var woId=jQuery('#txtWogenwoNo').val();									
 					processGridnew('costSummary_view.crt',"?q=2&woId="+woId,"woCostSumryGrid","woCostSumryPager","","","","costGridLoad","costGridError");
 					  // processGridnew('empCostActual_view.crt',"?q=2&formName=empCost&woId="+ woId,"tblEmpCostActGrid","empCostActPager","","tblEmpCostActGrid_dblClick","","costGridLoad","");
 				}
    		}
         	});	
    
         	*/

		jQuery("#imgCBMreadClose").click(function(){
			jQuery("#popCBMReadings").hide();
		});
  });

  /*jQuery('.chkspainfo').click(function(){
	  
	    if (jQuery('#chkSapinfo').attr('checked')) {
	    	//jQuery("#saptab").prop('disabled',true);
	    	//$(this).tab('show');
	    	 $('#spntab').attr('disabled', 'disabled');
	    	 alert("Y");
	    	//disableField("frmWOGen","spntab");
	    }
	}) ;*/
  jQuery("#btnMultipleResp").click(function(){
		
		 var machId = jQuery("#frmWOGen input[id='machine']").val();
		 var pmWOKeyid=jQuery("#txtWogenwoNo").val();
		var dataString='&pmWOKeyid='+pmWOKeyid+'&machId='+machId;
		var already=jQuery("#hdnalready").val();
		if (machId != "")		
			if(already == machId)
				jQuery("#loadMultiple").show();
			else
				{
					LoadPopUp("loadMultiple", "pmMultipleResp_input.mpc?"+dataString, true,"550px","600px","-1%","7%", "pmMultiResult_successCallBack","Multiple Responsibilty");
					jQuery("#hdnalready").val(machId);
				}
		else
			alert("Select Equipment");
	});	
  function communication_successCallBack(){
		 var workorderNo = jQuery('#txtWogenwoNo').val();
		
		  processGridnew('comm_view.brdn','?q=2&bdId='+workorderNo+'&commFlag=no&frmForm=pm',"CommnGrid","","","","","commComplete","");
		  //jQuery('#cmbbdmsKeyid').combobox('getValue')
		}
  function remarksFormatter(cellval, options, rowObject)
  {
	  var id = options.rowId;
		// var mode = jQuery('#hdnmode').val();
		  var disable ="";
		  //if( mode == "Y")
			  disable = "disabled=true";

			  
  		
    	return '<input id="txtremarks_'+id +'" value="'+ cellval +'" type="text" '+ disable +' class="easyui-text" style="height:30px;"  onclick="remarksData(\''+id + '\');""/>';
  }
  function cbmactionFormatter(cellval, options, rowObject)
  {
	  var mode = jQuery('#hdnmode').val();
	  if( mode == "Y"){
	   var zone = getZoneCbmReading(rowObject[26],rowObject[13],true) ;
	   return zone;
	  }
	  return cellval; 
  }
  function zoneColorFormatter(cellval, options, rowObject)
  {
	  var mode = jQuery('#hdnmode').val();
	  if( mode == "Y"){ 
		  var zone = getZoneCbmReading(rowObject[26],rowObject[13],false) ; 
		  if(zone.trim() != ""){	
		  	return "<span class='zoneColor' style='background-color:"+zone+"'></span>";
		  }
	  }
	  return cellval;
	  
  }
function getZoneCbmReading(cbmrelated,chkReadingValStr, isCBMAction){

	if( cbmrelated.indexOf(",") <= 0 || chkReadingValStr.trim() == "" || chkReadingValStr.trim().length == 0)
		return " ";
	var  cbmreadings = cbmrelated.split(",");
	var greenReading = cbmreadings[0].split("#");
	var yellowReading = cbmreadings[1].split("#");
	var redReading = cbmreadings[2].split("#");
	
	var zone =""; 
	var reduprlmt = parseFloat( redReading[5]);//jQuery('#hdnredUpperLimit').val();
	var greenuprlmt   = parseFloat(greenReading[5]) ;//jQuery('#hdngreenUpperLimit').val();
	var yellowuprlmt   = parseFloat(yellowReading[5]) ;//jQuery('#hdnyellowUpperLimit').val();
	var redlwrlmt   = parseFloat(redReading[4]);//jQuery('#hdnredlowerLimit').val();
	var yellowlwrlmt   = parseFloat(yellowReading[4]) ;//jQuery('#hdnyellowlowerLimit').val();
	var greenlwrlmt   = parseFloat(greenReading[4]) ;//jQuery('#hdngreenlowerLimit').val();

	var chkReadingVal = parseFloat(chkReadingValStr);	
	if(chkReadingVal >= yellowlwrlmt && chkReadingVal <= yellowuprlmt){
		zone = isCBMAction ?yellowReading[8]: yellowReading[3];
	}
	else if(chkReadingVal >= redlwrlmt && chkReadingVal <= reduprlmt){
		//alert('R');
		zone=isCBMAction ?redReading[8]:redReading[3];
	}
	else if(chkReadingVal >= greenlwrlmt && chkReadingVal <= greenuprlmt){
		//alert('G');
		zone= isCBMAction ?greenReading[8]:greenReading[3];
	}
	else
		zone=isCBMAction ?redReading[8]:redReading[3];

	return zone;
}
  
  function actionTakenFormatter(cellval, options, rowObject)
  {
  		var id = options.rowId;
  	//	 var mode = jQuery('#hdnmode').val();
  		  var disable ="";
  		//  if( mode == "Y")
  			  disable = "disabled=true";
    	return '<input id="txtactionTaken_'+id +'"  value="'+ cellval +'" type="text" ' + disable + ' class="easyui-text" style="height:30px;"  onclick="actTaknData(\''+id + '\');"/>';
  }
  function observation_Formatter(cellval, options, rowObject)
  {
	 // var mode = jQuery('#hdnmode').val();
	  var disable ="";
	//  if( mode == "Y")
		  disable = "disabled=true";
  		var id = options.rowId;
    	return '<textarea id="txtobservation_'+id +'" ' + disable + '  style="width:180px;height:30px;"  >'+cellval+'</textarea>';
  }
  function  observation_resp_Formatter(cellval, options, rowObject){
	  return cellval;
  }
  function  observation_targetDt_Formatter(cellval, options, rowObject){
	  return cellval;
  }
  function durationFormatter(cellval, options, rowObject)
  {
	// var mode = jQuery('#hdnmode').val();
	  var disable ="";
	//  if( mode == "Y")
		  disable = "disabled=true";
  	var id = options.rowId;
    	return '<input id="txtDuration_'+id +'" type="text" value="'+ cellval +'"  class="easyui-text" style="height:30px;text-align:center;width:48px"  '+ disable +' onclick="duratinData(\''+id + '\');"/>';
  }
  function chkFormatter(cellValue, options, rowObject)
  {
  		var id = options.rowId;
  		var checked ="";
  		if( cellValue == "Y")
  			checked ="checked=true";
			
    	return '<input id="chkformatter" ' +  checked + ' type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
  }
  function divOpenCBM_onClose(){
	  var rowId = jQuery('#selRowId').val();
	  var currReading = jQuery('#curReading').val();
	  var zoneCondition = jQuery('#zoneCondition').val();
	  var ZoneColor = jQuery('#ZoneColor').val();
	  var pmstdid = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"txtpmstandId");
	  var calenderId = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"txtpmCalendarId");
	
	 // jQuery('#cbmreading'+rowId).val("-");
	 
	 if(currReading.trim().length>0){
		 var CBMdatStr = jQuery('#CBMWodata').val();
		  CBMdatStr += "&calenderIdCBM="+calenderId+"&pmstdidCBM="+pmstdid;
		  //alert(CBMdatStr);
		 jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmreading",currReading);
		 jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmaction",zoneCondition,{'background-color':ZoneColor});
		 jQuery('#CBMWodata').val(CBMdatStr);
	 }
	 else
	  	jQuery('input:checkbox[id=chkformatter]').attr('checked',false);		
		return true;
	}
  function selectData(rowId){
	  
	  	var activity = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"frequency");
		jQuery("#spnShowActivity").html("Frequency-[Assembly]-Activity : " + activity);  	

	    enableFields('txtobservation_'+rowId);
	    enableFields('txtactionTaken_'+rowId);
	    enableFields('txtDuration_'+rowId);
	    enableFields('txtremarks_'+rowId);
	    jQuery("#txtobservation_"+rowId).removeAttr("disabled");
	  	//LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack)
	  	//+escape(dataStr)
	  	var sectId = jQuery("#frmWOGen input[id='section']").val();
	  	
	  	setFormater("CompWOGrid","frmWOGen","",rowId,"dteObsvTargetDate");
	  	setFormater("CompWOGrid","frmWOGen","employee.commonFilter?dept=maintenance&sectId="+sectId,rowId,"cmbObsvResponName","hdnObsvResponsibility","180",false, false, false);

	  	var servdate = new Date(srvTime());
	  	var currentDate = new Date(servdate.getFullYear(),servdate.getMonth(),servdate.getDate());
	  	currentDateCBM = servdate;
		//var frequncy = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"frequency"); 
	  	jQuery("#CompWOGriddteObsvTargetDate_"+rowId).datebox({onSelect:function(date){
		  	 var targetDate = new Date(date.getFullYear(), date.getMonth(),date.getDate());
		  	if( targetDate < currentDate ){
			  	 alert("Target Date Can not lesser than Current Date");
			  	 jQuery(this).datebox('clear');
			  	 return false;
			 }
			  	 
		}});
	  		
	  	
//		var rowid = jQuery("#CompWOGrid").jqGrid('getDataIDs');  
		//var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);	
	  	var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
	  	//var cmlength = cm.length;
	  	//cmlength = parseInt(cmlength);
		//var uomid = null;
	    //uomid = rowData.uomId;
	  	//var inspectionid = rowData.inspectionId;
	  	var jobType = jQuery("#CompWOGrid").jqGrid('getCell',rowId,cm[3].name);
	  	//var pmstandId = jQuery("#CompWOGrid").jqGrid('getCell',rowId,cm[1].name);
	  	/*var inspectionId = jQuery("#CompWOGrid").jqGrid('getCell',rowId,cm[cmlength].name);
	  	var UomId = jQuery("#CompWOGrid").jqGrid('getCell',rowId,cm[cmlength-1].name);
	  	alert(inspectionId+" -- -- -- "+UomId);*/
	  	jQuery('#selRowId').val(rowId);
	  	var planVal= '';
		 		//alert(Object.keys(rowData));.setGridParam({cellEdit:false});	
		if(jobType == "CBM"){
			showCBMReadings(rowId);
			//var hdnCurReadId = "curReading";
			//var hdnCBMWodata = "CBMWodata";
			//var hdnzoneCondition = "zoneCondition";
			setFormater("CompWOGrid","frmWOGen","",rowId,"txtCbmReading",null,null,null,false,true);
			setFormater("CompWOGrid","frmWOGen","",rowId,"txtCbmAdjustedReading",null,null,null,false,true);
			setFormater("CompWOGrid","frmWOGen","",rowId,"dteCbmNextDueDate");
			var freArr = activity.split("-");
			var nextDueDate = getNextDueDate(servdate, freArr[0]);
				
			

			jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox({onSelect:function(date){
			  	 var targetDate = new Date(date.getFullYear(), date.getMonth(),date.getDate());
			  	if( targetDate < currentDate ){
				  	 alert("Next Due Date Can not lesser than Current Date");
				  	 jQuery(this).datebox('setValue',nextDueDate);
				  	 return false;
				 }
				  	 
			}});

			jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("setValue",nextDueDate);
			
			//alert("&inspectionId="+inspectionid+"&uomId="+uomid+"&pmsdId="+pmstandId+"&hdnCurReadId="+hdnCurReadId+"&hdnCBMWodata="+hdnCBMWodata+"&hdnzoneCondition="+hdnzoneCondition);
			//jQuery("#CompWOGrid").setCell(rowId, "hdnchkSel","1");
			//	LoadPopUp("divOpenCBM","cbmWO_input.mpc?inspectionId="+inspectionid+"&uomId="+uomid+"&pmsdId="+pmstandId+"&hdnCurReadId="+hdnCurReadId+"&hdnCBMWodata="+hdnCBMWodata+"&hdnzoneCondition="+hdnzoneCondition+"", true,"90%","96%","-3","4%", "CBMOk_Callback","CBM");
		}
		else
			jQuery('#popCBMReadings').hide();
		//else{	
		// jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[7].name,jQuery('#txtactionTaken').val("DONE"),{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});
		// jQuery("#CompWOGrid").jqGrid("setCell", rowId, cm[7].name,  jQuery("#txaWwmsFinalaction").val().toUpperCase());
			 	//jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[11].name,"",{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});
			 	jQuery('#txtactionTaken_'+rowId).val('DONE');
/*			 	jQuery('#txtactionTaken_'+rowId).css('background-color','#E0DBDB');		
			 	jQuery('#txtactionTaken_'+rowId).css('width','133');
			 	jQuery('#txtactionTaken_'+rowId).css('height','26');
*/			 	
				// cm[7].name.removeClass('not-editable-cell'); txtremarks,txtDuration
				 planVal = jQuery("#CompWOGrid").jqGrid('getCell',rowId,cm[7].name);
//				 jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[12].name,planVal,{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});
				 //cm[8].removeClass('not-editable-cell');
				 jQuery('#txtDuration_'+rowId).val(planVal);
	/*			 jQuery('#txtDuration_'+rowId).css('background-color','#E0DBDB');	
				 	
//				 jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[13].name,"-",{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});																						
				// cm[9].removeClass('not-editable-cell');
	*/			 jQuery('#txtremarks_'+rowId).val("-");
		/*		 jQuery('#txtremarks_'+rowId).css('background-color','#E0DBDB'); */
				 jQuery("#CompWOGrid").setCell(rowId, "hdnchkSel","1");
			  	 jQuery("#selectedDat").val(rowId);
			  	 jQuery('#txtactionTaken_'+rowId).css('display','block');
				 jQuery('#txtDuration_'+rowId).css('display','block');
				 jQuery('#txtremarks_'+rowId).css('display','block');
				 /* 	for(var j=8;j<11;j++){
				  	  	jQuery("#CompWOGrid").setGridParam({cellEdit:true});
				  	  	  	  
				  	 }*/
		 //  }
	
  }

  function CompWOGrid_onBlur(record){
	  if( record.txtId==  "txtCbmReading" || record.txtId == "txtCbmAdjustedReading"){
		  txtLostFocus(record.txtId,record.rowId);
	  }/*else if( record.txtId== "txtCbmAdjustedReading" ){
		  txtLostFocus(record.txtId,record.rowId);
	  }*/
	  
  }
  function showCBMReadings(rowId){
	  var cbmrelated = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"hdnCbmReleated");
	 
	  //popCBMReadings
	  jQuery('#popCBMReadings').hide();
	  jQuery("#popCBMReadingsCont").html("");
	 	var  cbmreadings = cbmrelated.split(",");
		var greenReading = cbmreadings[0].split("#");
		var yellowReading = cbmreadings[1].split("#");
		var redReading = cbmreadings[2].split("#");
		var htmlStr = "<table border='1' style='border:1px;solid #d4d4d4'><tr class='cbmreadingheader'><th>Zone</th><th>MinReading</th><th>MaxReading</th><th>CorrectiveAction</th></tr>";
		htmlStr += "<tr><td class='cbmreadingtext' style='background-color:"+greenReading[3]+"'>"+greenReading[2]+"</td><td class='cbmreading'>"+greenReading[4]+"</td><td class='cbmreading'>"+greenReading[5]+"</td><td class='cbmreadingtext'>"+greenReading[8]+"</td></tr>";
		htmlStr += "<tr><td class='cbmreadingtext' style='background-color:"+yellowReading[3]+"'>"+yellowReading[2]+"</td><td class='cbmreading'>"+yellowReading[4]+"</td><td class='cbmreading'>"+yellowReading[5]+"</td><td class='cbmreadingtext'>"+yellowReading[8]+"</td></tr>";
		htmlStr += "<tr><td class='cbmreadingtext' style='background-color:"+redReading[3]+"'>"+redReading[2]+"</td><td class='cbmreading'>"+redReading[4]+"</td><td class='cbmreading'>"+redReading[5]+"</td><td class='cbmreadingtext'>"+redReading[8]+"</td></tr>";
		htmlStr +="</table>";
		jQuery("#popCBMReadingsCont").html(htmlStr);
		//jQuery('#popCBMReadings').addClass('custom-popup');		
	    jQuery('#popCBMReadings').show();		  
	   // jQuery('#mpdialog').css('border','1px solid #2364CA');
	    jQuery('#popCBMReadings').css('z-index',100);
	    jQuery('#popCBMReadings').css('top','5');
	    jQuery('#popCBMReadings').css('margin-left','108');
  }
  function txtLostFocus(txtid,rowId){
		
		var chkReadingValStr = jQuery('#CompWOGrid'+txtid+"_"+rowId).val();
		if( txtid == "txtCbmReading" ){

			 jQuery("#CompWOGrid").jqGrid("setCell", rowId, "colZoneColor","",{'background-color':'white'});
			 jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmaction","");
			 jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMinReading", " ");
			 jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMaxReading", "");

			 if(chkReadingValStr.trim() == "")
				return;
			jQuery("#CompWOGridtxtCbmAdjustedReading_"+rowId).val("").removeAttr('disabled').css('background-color', 'white');	
		}
		var cbmrelated = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"hdnCbmReleated");
		var  cbmreadings = cbmrelated.split(",");
		var greenReading = cbmreadings[0].split("#");
		var yellowReading = cbmreadings[1].split("#");
		var redReading = cbmreadings[2].split("#");
		
		var greenid ='';
		var yellowid='';
		var redid = '';
		
		var greenC =greenReading[3]; //jQuery('#hdngreen').val('green');

		var yellowC=yellowReading[3];//jQuery('#hdnyellow').val('yellow');
		var redC= redReading[3];//jQuery('#hdnred').val('red');

		var reduprlmt = parseFloat( redReading[5]);//jQuery('#hdnredUpperLimit').val();
		var greenuprlmt   = parseFloat(greenReading[5]) ;//jQuery('#hdngreenUpperLimit').val();
		var yellowuprlmt   = parseFloat(yellowReading[5]) ;//jQuery('#hdnyellowUpperLimit').val();
		var redlwrlmt   = parseFloat(redReading[4]);//jQuery('#hdnredlowerLimit').val();
		var yellowlwrlmt   = parseFloat(yellowReading[4]) ;//jQuery('#hdnyellowlowerLimit').val();
		var greenlwrlmt   = parseFloat(greenReading[4]) ;//jQuery('#hdngreenlowerLimit').val();

		var chkReadingVal = parseFloat(chkReadingValStr);	
		if(chkReadingVal >= yellowlwrlmt && chkReadingVal <= yellowuprlmt){
			//alert('Y');
			yellowid='yellow';
		}
		else if(chkReadingVal >= redlwrlmt && chkReadingVal <= reduprlmt){
			//alert('R');
			redid='red';
		}
		else if(chkReadingVal >= greenlwrlmt && chkReadingVal <= greenuprlmt){
			//alert('G');
			greenid='green';
		}
		else if(chkReadingVal > reduprlmt){
			alert("Current Reading Should be between the upper and lower limit values");
			jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
		}
		else if(chkReadingVal < greenlwrlmt){
			alert("Current Reading Should be between the upper and lower limit values");
			jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
		}
		else
			greenid='green';

		 
		 //enableFields("CompWOGridtxtCbmAdjustedReading_"+rowId);
	if( txtid == "txtCbmReading" ){	 
		var nxtDate = jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("getValue");

		if(nxtDate.trim() == "" ){
			var frequncy = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"frequency");
			var freArr = frequncy.split("-");

			var nextDueDate = getNextDueDate(currentDateCBM, freArr[0]);
				
			jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("setValue",nextDueDate);
		}		
	}

	if(yellowid.length<=0 && redid.length<=0){		
		if(chkReadingVal >= greenlwrlmt && chkReadingVal <= greenuprlmt && greenid == 'green'){
			// greenid   = "green";
			if( txtid == "txtCbmReading" ){
				jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmaction",greenReading[8]);
				jQuery("#CompWOGrid").jqGrid("setCell", rowId, "colZoneColor","",{'background-color':greenC});
				jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMinReading", greenlwrlmt);
				jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMaxReading", greenuprlmt);
				jQuery("#CompWOGridtxtCbmAdjustedReading_"+rowId).val("").attr('disabled','disabled').css('background-color', '#D1E2FD');
				
				jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("disable");
			}
			//disableField("frmWOGen","CompWOGridtxtCbmAdjustedReading_"+rowId);
			/*jQuery('#hdnchkAdjustreading').val('true');
			jQuery('#txtZcolor').css('background-color','#94E031');
			jQuery('#txtZcolor').css('border','none');
			jQuery('#txtStatus').val('Ok');
			jQuery('#txtStatus').css('color','blue');
			jQuery('#txtStatus').css('text-align','center');
			jQuery('#txtCondition').css('color','blue');
			jQuery('#txtCondition').val('No Action');
			jQuery('#txtCondition').css('text-align','center');*/
			//disableField("frmCBMWO","txtCmcdAdjustedreading");
			
		}	
		//else
			//alert("G Current Reading Should be between the upper and lower limit values");
	}
		
		else if(greenid.length<=0 && redid.length<=0){
			if(chkReadingVal >= yellowlwrlmt && chkReadingVal <= yellowuprlmt){
			
				// yellowid  ='yellow' ;
				/*jQuery('#hdnchkAdjustreading').val('true');
				jQuery('#txtZcolor').css('background-color','#EDED6F');
				jQuery('#txtZcolor').css('border','none');
				jQuery('#txtStatus').val('Ok');
				jQuery('#txtStatus').css('color','blue');
				jQuery('#txtStatus').css('text-align','center');
				jQuery('#txtCondition').css('color','blue');
				jQuery('#txtCondition').val('Adjust');
				jQuery('#txtCondition').css('text-align','center');
				enableFields("txtCmcdAdjustedreading");*/

				
				if( txtid == "txtCbmReading" ){	
					jQuery("#CompWOGrid").jqGrid("setCell", rowId, "colZoneColor"," ",{'background-color':yellowC});
					jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmaction",yellowReading[8]);
					jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMinReading", yellowlwrlmt);
					jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMaxReading", yellowuprlmt);
					jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("enable");
					
				}
				else if( txtid == "txtCbmAdjustedReading" ){
					var znCols = jQuery("tr[id="+rowId+"] > td[aria-describedby=CompWOGrid_colZoneColor]").css("background-color");//jQuery("#CompWOGrid").jqGrid("getCell", rowId, "colZoneColor",["background-color"]);

					var znCol = colorToHex(znCols);
					if( znCol.replace("#",'').toUpperCase() == redC.replace("#",'').toUpperCase()  ){
						alert(" Adjusted Reading should be between the Green Zone Values ");
						jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
					}
				}
				
			}
			else{
				alert("Y Current Reading Should be between the upper and lower limit values");
				jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
			}		
		}
		else if(greenid.length<=0 && yellowid.length<=0){
			if(chkReadingVal >= redlwrlmt && chkReadingVal <= reduprlmt ){
				
			//	redid     ='red';
			/*	jQuery('#hdnchkAdjustreading').val('');
				jQuery('#txtZcolor').css('background-color','#E52222');
				jQuery('#txtZcolor').css('border','none');
				jQuery('#txtStatus').val('Ok');
				jQuery('#txtStatus').css('color','blue');
				jQuery('#txtStatus').css('text-align','center');
				jQuery('#txtCondition').css('color','blue');
				jQuery('#txtCondition').val('Replace');
				jQuery('#txtCondition').css('text-align','center');
				enableFields("txtCmcdAdjustedreading");*/

				
				if( txtid == "txtCbmReading" ){	
					jQuery("#CompWOGrid").jqGrid("setCell", rowId, "colZoneColor"," ",{'background-color':redC});
					jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmaction",redReading[8]);
					jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMinReading", redlwrlmt);
					jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMaxReading", reduprlmt);
					jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("disable");
					//jQuery("#CompWOGridtxtCbmAdjustedReading_"+rowId).val("").attr('disabled','disabled').css('background-color', '#D1E2FD');
				}
				else if( txtid == "txtCbmAdjustedReading" ){
					var znCol = jQuery("tr[id="+rowId+"] > td[aria-describedby=CompWOGrid_colZoneColor]").css("background-color");//jQuery("#CompWOGrid").jqGrid("getCell", rowId, "colZoneColor",["background-color"]);

					znCol = colorToHex(znCol);
					if( znCol.replace("#",'').toUpperCase() == redC.replace("#",'').toUpperCase() ){
						alert(" Adjusted Reading should be between the Green Zone Values ");
						jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
					}else{
						alert(" Adjusted Reading should be between the Green / Yellow Zone Values ");
						jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
					}
						
				}
					 
			}	
			else{
				alert(" R Current Reading Should be between the upper and lower limit values");
				jQuery('#CompWOGrid'+txtid+"_"+rowId).val("");
			}	
		}
		else{
			if( txtid == "txtCbmReading" ){
				jQuery("#CompWOGrid").jqGrid("setCell", rowId, "colZoneColor"," ",{'background-color':redC});
				jQuery("#CompWOGrid").jqGrid("setCell", rowId, "cbmaction",redReading[8]);	
				jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMinReading", redlwrlmt);
				jQuery("#CompWOGrid").jqGrid("setCell", rowId,"hdnCbmMaxReading", reduprlmt);

				jQuery("#CompWOGriddteCbmNextDueDate_"+rowId).datebox("disable");
			}	
		}
	}

  /*function rgbToHex(r, g, b) {
	    return ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
	}
	*/
	function getNextDueDate(date, Frequncy){
		Frequncy = Frequncy.trim();
		var nextDate = null;
		if( typeof date.getMonth != 'function' ){
			var dateArr = date.split("-");
			nextDate = new Date(dateArr[2],getIndex(dateArr[1]),dateArr[0]);
		}
		else{
			nextDate = date;
		}	
		if( Frequncy == "W" ){
			nextDate.setDate(nextDate.getDate()+7);
		}
		else if( Frequncy == "F" ){
			nextDate.setDate(nextDate.getDate()+15);
		}
		else if( Frequncy == "M" ){
			nextDate.setMonth(nextDate.getMonth()+1);
		}
		else if( Frequncy == "Q" ){
			nextDate.setMonth(nextDate.getMonth()+3);
		}
		else if( Frequncy == "H" ){
			nextDate.setMonth(nextDate.getMonth()+6);
		}
		else if( Frequncy.substring(0,1) == "Y" ){
			var noYear = 1;
			if( Frequncy.trim().length > 1 )
				noYear = parseInt(Frequncy.substring(1),10);
			nextDate.setYear(nextDate.getYear()+noYear);
		}
		var month = nextDate.getMonth();	
		
		var day =  nextDate.getDate();
		var year = nextDate.getFullYear();
			month = getMonthStringFromInt(month);

		var nxtDate = ("00" + day).slice(-2) +"-" + month + "-" + year;	
		
		return  nxtDate;
							
	}
	function colorToHex(color) {
		
	    if (color.substr(0, 1) === '#' || color.substr(0,3) != "rgb") {
	        return color;
	    }
	    
		    
	    var digits = /(.*?)rgb\((\d+), (\d+), (\d+)\)/.exec(color);

	    var red = parseInt(digits[2]);
	    var green = parseInt(digits[3]);
	    var blue = parseInt(digits[4]);

	    var rgb = blue | (green << 8) | (red << 16);
	    return digits[1] + '#' + rgb.toString(16);
	}
	
  function unselectData(rowId){

	  //removeFormater(gridId,formtType,rowId,value,colName);
	  removeFormater("CompWOGrid","frmWOGen",rowId,"","cmbObsvResponName");
	  removeFormater("CompWOGrid","frmWOGen",rowId,"","txtCbmReading");
	  removeFormater("CompWOGrid","frmWOGen",rowId,"","txtCbmAdjustedReading");
	  removeFormater("CompWOGrid","frmWOGen",rowId,"","dteCbmNextDueDate");
		
  	jQuery("#CompWOGrid").setCell(rowId, "hdnchkSel"," ");
  	var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
		//alert(Object.keys(rowData));
		var j = cm.length;
		 /* 	for(j=7;j<10;j++){
				jQuery("#CompWOGrid").setGridParam({cellEdit:false});
				
		  	}
		  */	 jQuery('#txtactionTaken_'+rowId).hide();
			 jQuery('#txtDuration_'+rowId).hide();
			 jQuery('#txtremarks_'+rowId).hide();
		//jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[7].name," ",{'color':'#000','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[8].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[9].name," ",{'color':'#000','font-size':'11px','background-color':''});																					
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[10].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[12].name," ",{'color':'#000','font-size':'11px','background-color':''});

		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[13].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[14].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[15].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[16].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[17].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[18].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[19].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[20].name," -",{'color':'#000','font-size':'11px','background-color':''});
				
  }
  function getFilterValue1(filterString,valueIdentifier)
  {
  	if( filterString != null && valueIdentifier != null && filterString.indexOf(valueIdentifier) > -1 )
  	{
  		var val;
  		var substr = filterString.substring(filterString.indexOf(valueIdentifier)+valueIdentifier.length+1 );
  		if(substr.indexOf("&") != -1)
  			val = substr.substring(0,substr.indexOf("&"));
  		else 
  			val = substr;
  		return val;
  	}	
  	
  	return "";
  }
 function genWODBLClick(id){
	 	var rowData = jQuery("#GenWOGrid").jqGrid('getRowData',id);		
	 	var filter = jQuery.cookie("filterString");	
	 	var jobt=getFilterValue1(filter ,'hdncmbJobType');
	 	var woId = rowData.workorderno;
		var machineId = rowData.machineId;
		var tradeId = rowData.tradeId;
		var mode = jQuery('#hdnmode').val();
		var jobtype = rowData.jobtype;
	    var filterString1 = jQuery('#hdnfilter_string').val();
	 //   var weekNO = filterString1.substring('112');
	   
	//	 fillMachineHierarchy("machineHierarchy.commonFilter",machineId,"cmbgenwCellid","cmbgenwSectionid ","cmbgenwFactoryid","", "","cmbWogenCostCenter");
		 
		setFieldValue("cmbWogenTradeid", tradeId,"frmWOGen");
		var filterString = '';
		filterString += '&cmbMchid='+machineId ;
		filterString += '&cmbAssmbid='+getFieldValue('cmbWogenAssemblyid', 'frmWOGen');
		filterString += '&cmbFactid='+ jQuery("#frmWOGen input[id='factory']").val();//jQuery('#hdnFctId').val();
		filterString += '&cmbSectid='+  jQuery("#frmWOGen input[id='section']").val();//jQuery('#hdnSectId').val();
		filterString += '&cmbCellid='+jQuery("#frmWOGen input[id='cell']").val(); //jQuery('#hdnCellId').val();
		filterString += "&cmbTradeid="+tradeId;
		filterString += '&woId='+woId;
		if(jobtype != 'jobtype')
			filterString += '&jobtype='+jobtype;
		else
			filterString += '&jobtype='+jobt;
		filterString +='&weekNo='+jQuery("#weekNo").val();
		//filterString +=  '&'+weekNO;
		var sectId = jQuery("#frmWOGen input[id='section']").val();//jQuery('#hdnSectId').val();
		jQuery('#txtWogenwoNo').val(woId);
		//jQuery('#hdnfilter_string').val(jQuery('#hdnfilter_string').val()+filterString);
		jQuery('#load1stGrid').css('display','none');
		jQuery('#load2ndGrid').css('display','block');
		jQuery(".spnComplfields").css('display','block').css('margin-left','780px').css('vertical-align','top').css('margin-top','-20px');
		jQuery(".spnComplBtn").css('display','block').css('margin-left','1060px').css('vertical-align','top').css('margin-top','-28px');;
	 	processGridnew('completeWO_input.mpc',filterString,"CompWOGrid","","","CompWODBLClick","","CompWOGridComplete");
	 	fillComboBox("frmWOGen","cmbbeanWofbCompletedby","employee.commonFilter?dept=maintenance&sectId="+sectId );
	 	
 }
 function CompWODBLClick(rowId){

	 		var jobType = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"txtjobtype");
	 		if(jobType == "CBM"){
	 			showCBMReadings(rowId);
	 			var activity = jQuery("#CompWOGrid").jqGrid('getCell',rowId,"frequency");
	 			jQuery("#spnShowActivity").html("Frequency-[Assembly]-Activity : " + activity);  	
	 		}
	 			
		 	/*var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);		
	  		var pmstdId = rowData.txtpmstandId;
			var freqData = rowData.frequency;
			
			var mach =jQuery("#frmWOGen input[id='machine']").val();
			
			var woDetailId=rowData.txtWksmWodetailid;//alert(woDetailId);
			var pmCalId= rowData.txtpmCalendarId;
			var observation= jQuery("#txtobservation_"+rowId).val();  ;//rowData.txtWofbObservation;

			var completedBy= rowData.completedby;
			var jobType=rowData.txtjobtype;
			var serverTime = srvTime();
			var currentTime = new Date(serverTime);
			var day = currentTime.getDate();
			var dataStr = "";


			dataStr += "freqData="+escape(freqData); 
			dataStr += "&machId="+mach;
			dataStr += "&woDetailId="+woDetailId;
			dataStr += "&pmCalId="+pmCalId;
			//dataStr += "&startDate="+escape(day+"-"+jQuery('#txtmonthYr').val());
			dataStr +="&pmstdId="+pmstdId;
			dataStr +="&duration="+jQuery('#txtPlanDuration').val();
			dataStr +="jobType=" + jobType + "&observation="+observation.trim() +"&";
			if(completedBy != '' && completedBy != ' ' && completedBy != null)
				dataStr +="&completedBy="+completedBy;
	*/
				
//		LoadPopUp("loaddetailsPop", "workOrderDetails_input.mpc?"+dataStr, true,"95%","72%","0%","2%", "","Work Order Details");


/*			if(screen.width <= 1024) 
	  	   	  LoadPopUp("loaddetailsPop", "workOrderDetails_input.mpc?"+dataStr, true,"95%","72%","0%","2%", "","Work Order Details");
			else if(screen.width >= 1028 && screen.width < 1366)
			  LoadPopUp("loaddetailsPop", "workOrderDetails_input.mpc?"+dataStr, true,"92%","90%","1%","3%", "","Work Order Details");
			else
		      LoadPopUp("loaddetailsPop", "workOrderDetails_input.mpc?"+dataStr, true,"75%","72%","1%","3%", "","Work Order Details");
	 */ 
	 }
 function CompWOGridComplete(ids){
	 var intRegex = /^\d+$/;
	 var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
	 var mode = jQuery('#hdnmode').val();
	 var modifymode = jQuery('#hdnmodeModify').val();
	 
	 //var rowid = jQuery("#GenWOGrid").jqGrid('getDataIDs');
	 //var rowId = jQuery("#CompWOGrid").jqGrid('getDataIDs');
	 
	jQuery(".zoneColor").each(function(){
	
		 jQuery(this).parent("td").attr("style",jQuery(this).attr("style"));
		 jQuery(this).remove();
		 
	});
	 
			 
	 
	 if(mode == 'Y'){
		 disableField('frmWOGen','chkformatter');
		 if(modifymode == 'modify'){
			 enableFields('cmbbeanWofbCompletedby');
			 /*for(var i=0;i<rowId.length;i++)
			 {
			 jQuery("#CompWOGrid").setCell(rowId, "hdnchkSel","1"); //for rrlhalvasai
			 // jQuery("#CompWOGrid").setCell(rowid, "hdnchkSel","1");-----for rrlhaltrn
			 
			 }*/
		 }
		 else
		 	disableField('frmWOGen','cmbbeanWofbCompletedby');
		 //jQuery('input:checkbox[id=chkformatter]').attr('checked',true);
		
	 }
	  
		var rowid = jQuery("#CompWOGrid").jqGrid('getDataIDs');
		//var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
	jQuery('#noAct').val("No of Activities :"+rowid.length);
		
		var sumval = 0;
		var planVal ="";
		 for(var i=0;i<rowid.length;i++)
		 {
			 if(mode == 'Y' )
			 	jQuery("#CompWOGrid").setCell(rowid[i], "hdnchkSel","1");
			 	
			 planVal = jQuery("#CompWOGrid").jqGrid('getCell',rowid[i],"plannedduration");	
			 sumval += parseInt(planVal,10);
	     	
			/* for(var j=8;j<11;j++)
	     	 {
				jQuery("#CompWOGrid").setGridParam({cellEdit:false});
	     	 }		
			 */
		 }
		 
		 if(intRegex.test(sumval) || floatRegex.test(sumval)) {
			 sumval=sumval;
		 }
		 else
			 sumval = 0;
		 jQuery('#txtPlanDuration').val(sumval);
		 		 
 }
 /**/
 jQuery('#backBtn2nd').click(function(){
	// jQuery('#GenWOGrid').trigger("reloadGrid");
	var filterString = jQuery.cookie("filterString");
	//alert("second  "+filterString);
	  processGridnew('genWO_input.mpc','?filterString='+filterString ,"GenWOGrid","GenWOPager","","genWODBLClick","","genWOGridComplete");  
	var rowid = jQuery("#GenWOGrid").jqGrid('getDataIDs');
	 //alert(rowid.length);
	 jQuery('#noAct').val("No of Activities :"+rowid.length);
	 jQuery('#load1stGrid').css('display','block');
	 jQuery('#load2ndGrid').css('display','none');
	 jQuery(".spnComplfields").css('display','none');
	 jQuery(".spnComplBtn").css('display','none');
	 jQuery("#txtWogenwoNo").val("");
	 jQuery("#txtPlanDuration").val("");
 });
function genWOGridComplete(data)
{
	var rowid = jQuery("#GenWOGrid").jqGrid('getDataIDs');
	 //alert(rowid.length);
	 jQuery('#noAct').val("No of Activities :"+rowid.length);
	//jQuery("td.jqgrow:odd").css("background", "#C4C2C2");
	
	    
}
function frmWOGen_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFunctionalLocWidth("frmWOGen","500px");
	
}
function  frmWOGencmbWogenCostCenter_onLoadSuccess(){
	
	   
}
function  frmWOGencmbWogenTradeid_onLoadSuccess(){
	
	
}
function  frmWOGencmbWogenAssemblyid_onLoadSuccess(){
	 var filterString = jQuery('#hdnfilter_string').val();
	//var assmId = filterString.substring(filterString.indexOf('ASM'),filterString.indexOf('&cmbFactid='));
	 var assmId = getFilterValue(filterString ,"ASM") ;
	 //alert(assmId);
	 ///setFieldValue('cmbWogenAssemblyid', assmId,'frmWOGen');
	 //fillComboBox("frmWOGen","cmbWogenAssemblyid","assembly.commonFilter");
	 disableField('frmWOGen','cmbWogenAssemblyid');
}
function frmWOGen_beforeSubmit(){
	
	var rowId = jQuery("#selectedDat").val();
	var mach = jQuery("#frmWOGen input[id='machine']").val();
	var dataStr = "";
	//alert('before submit' +rowId);
	var gridData = getSelectdRows("CompWOGrid","status","hdnchkSel");
	if( gridData == false)
	return false;
	dataStr += "&gridData="+gridData;
	dataStr += "&txtmachId="+mach;
	dataStr += "&completedBycombo="+jQuery("#cmbbeanWofbCompletedby").combobox("getValue");
	//dataStr += "&hdnMultiresp="+jQuery("#hdnMultiresp").val();
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var day = currentTime.getDate();
	//alert(dataStr);
	dataStr += "&dtestartDate="+escape( jQuery('#txtmonthYr').val());
	/* var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);		
	  	var pmstdId = rowData.keyId;
			var freqData = rowData.frequency;
			var mach = jQuery("#frmWOGen input[id='machine']").val();
			var woDetailId=rowData.wodetail;
			var pmCalId= rowData.pmCalendarId;
			var jobType=rowData.jobtype;
			var serverTime = srvTime();
			var currentTime = new Date(serverTime);
			var day = currentTime.getDate();
			var dataStr = "";
			dataStr += "&freqData="+escape(freqData); 
			dataStr += "&machId="+mach;
			dataStr += "&woDetailId="+woDetailId;
			dataStr += "&pmCalId="+pmCalId;
			dataStr += "&startDate="+escape(day+"-"+jQuery('#txtmonthYr').val());
			dataStr +="&pmstdId="+pmstdId;
			dataStr +="&duration="+jQuery('#txtPlanDuration').val();
			dataStr +="&jobType="+jobType;
			dataStr +="&directSave=directSave";
			//alert("dataStr    :"+dataStr);
		/*For Getting values from spares Grid*/
			 var rowid = jQuery("#sprpopGrid").jqGrid('getDataIDs');
			 if(rowid != ' '&& rowid != '' && rowid != null){
			 var rowData = jQuery("#sprpopGrid").jqGrid('getRowData',rowid);
			 var actual = jQuery("#sprpopGrid").jqGrid('getCell',rowid.length,'actualQty');
			// alert('spare');
			 var planQuantity = rowData.planQty;
			 dataStr+="&planQuantity="+planQuantity;
			 var sparename = rowData.sparename;
			 dataStr+="&sparename="+sparename;
			 var spareId = rowData.keyId;
			 dataStr+="&spareId="+spareId;
			// alert(actual.substring(0,1));
			 if(actual.substring(0,1) == '<')
			 {//alert('sd');
		 		 if(jQuery('#'+rowid.length+'_actualQty').val() != '' && jQuery('#'+rowid.length+'_actualQty').val() != null && jQuery('#'+rowid.length+'_actualQty').val() != ' ') 
		           var actualQuantity = jQuery('#'+rowid.length+'_actualQty').val();
		 				dataStr+="&actualQuantity="+actualQuantity;
		 				//alert(dataStr);
			 }
			 
		}

 var modifymode = jQuery('#hdnmodeModify').val();
 var CBMWodata = jQuery('#CBMWodata').val();
 if(CBMWodata.trim().length>0)
	 dataStr+="&cbmData="+CBMWodata;
var completedBy = getFieldValue('cmbbeanWofbCompletedby','frmWOGen');
if(completedBy != '' && completedBy != ' ' && completedBy != null){
	if(modifymode == 'modify')
		dataStr+="&modeselected=modify";
	return dataStr;
}
else{
	alert('Select Completed By');
	return false;
}
//return false;			
}
function frmWOGen_successsCallback(result){
	jQuery('#CompWOGrid').trigger("reloadGrid");
	 var pmWOKeyid=jQuery("#txtWogenwoNo").val();
	
	if (jQuery('#chkSapinfo').is(':checked')==true) {
				 var qty=null;
		 var allRows = jQuery('#sapInfoGrid').jqGrid('getRowData');							
			for( var i = 1; i <= allRows.length;i++){
				
			 qty=jQuery('#txtSspmQuantity_'+i).val();						 
			
			}
			if(qty>=1){	
														 
				 	submitToSap("Preventive Maintenance", " PM_ORDER_CREATE", pmWOKeyid);						
				 }
			     else {alert('Enetr the Quantity'); 			 	 	
					
				}
			}	
}
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
{
	
	var allRows = jQuery("#CompWOGrid").jqGrid('getRowData');
	var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
	//alert(allRows.length);
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		//for(var j=0;j<cm.length;j++)
		//{
		//jsonArrO += '{';
			var row = allRows[i];
			//alert("colName  :"+Object.keys(row));
			var value = row[ckeckForSelColName];
		//	alert("value"+value);		
			if( value != null  &&  value.trim()  != "")
			{			
				if(value == '1')				
				{
					jsonArrO += '{';
					for(var colName in row) 
					{
						if(colName != 'status' && colName != 'frequency' && colName != 'spares' && colName != 'hdnchkSel' && colName != 'supplier' )
						{
							var cellValue = parseJqGridCellValue(row[colName]);
							if( row["txtjobtype"] == "CBM" ) {
								if( colName == "txtCbmReading" && cellValue == ""  ){
									alert("Enter CBM Reading For Selected record(s)");
									return false;
								}
								else if( colName == "txtCbmAdjustedReading" && cellValue == "" ){
									var cbmRelated = row["hdnCbmReleated"];
									var  cbmreadings = cbmRelated.split(",");									
									var redReading = cbmreadings[2].split("#");
									var redC= redReading[3];								
									var znCol = jQuery("tr[id="+(i+1)+"] > td[aria-describedby=CompWOGrid_colZoneColor]").css("background-color");
									znCol = colorToHex(znCol);
									if( znCol.replace("#",'').toUpperCase() == redC.replace("#",'').toUpperCase()  ){
										alert(" Adjusted Reading is mandatory for Red Zone ");
										return false;
									}	
								}
								else if( colName == "dteCbmNextDueDate" && cellValue == "" ){
									alert(" Select Next due date of CBM Activities ");
									return false;
								}	
							}	
							jsonArrO += '"'+colName +'":"' + cellValue+'",';
						//	alert("colName      "+colName+"       value    "+cellValue);
						}		
					}
					jsonArrO += "},";
				}
			} 
		//}
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'? jsonArrO:"");
	//alert(jsonArrO+"     ........");
	return jsonArrO; 
} 
/* SPARE BUTTON IN GRID AND POP UP */
 function sprWOButton(cellvalue, options, rowObject)
	{					
		var rowId = options.rowId;
		var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
		for(var i=0;i<cm.length;i++){
			if(cm[i].name =='spares' )
			{
				var formatStr ='';
				if(cellvalue == "Y" )
			     formatStr  += '<input type="button" id="sprbtn" style="height:19px;height:15px\9;" class="easyui-button" value="..." onclick="sprbtnclick(\''+rowId + '\');"/>'
				     //<img src=images/spr_but.png style=/"cursor:pointer;/" id="sprbtn" onclick="sprbtnclick(\''+rowId + '\');"/>';
				else 
					formatStr  +='<span style=/"background-color:#fff;/"></span> '; 
				return formatStr  ;
			}
			
		}
	}
	function sprbtnclick(rowId){
		var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);		
		var pmstdId = rowData.txtpmstandId;
		processGridnew("sprWOdetails_input.mpc",'&pmstdId='+pmstdId,"sprpopGrid","pager_spr","","");
		//jQuery( "#sparepopDiv" ).css("display","block");
		openmpdialog();
		
	}
	jQuery("#spr_close").click(function(){
		//alert("closed");		
		//jQuery( "#sparepopDiv" ).css("display","none");
		closeFlDialog('sparepopDiv');
	});
	jQuery("#btnMultipleResp").click(function(){
		
		 var machId = jQuery("#frmWOGen input[id='machine']").val();
		 var pmWOKeyid = jQuery("txtWogenwoNo").val();
		var dataString='&pmWOKeyid='+pmWOKeyid+'&machId='+machId;
		var already=jQuery("#hdnalready").val();
		if (machId != "")		
			if(already == machId)
				jQuery("#loadMultiple").show();
			else
				{
					LoadPopUp("loadMultiple", "pmMultipleResp_input.mpc?"+dataString, true,"550px","600px","-1%","7%", "bdMultiResult_successCallBack","Multiple Responsibilty");
					jQuery("#hdnalready").val(machId);
				}
		else
			alert("Select Equipment");
	});	
	function openmpdialog(){

		//jQuery('#mstfrm_div').addClass('popup-mask');	
		jQuery('#mstfrm_div').show();
		jQuery('#sparepopDiv').addClass('custom-popup');		
	  jQuery('#sparepopDiv').show();		  
	  jQuery('#sparepopDiv').css('border','1px solid #F1F5FB');
	  jQuery('#sparepopDiv').css('z-index',100); 
	  if(screen.width >= 1366)
	  	jQuery('#titleSpare').css('width','463');
	}
	jQuery( "#imgSpare" ).click(function() {
		closeFlDialog('sparepopDiv');
	});
	function sparepopDiv_onClose(){
		
		closeFlDialog('sparepopDiv');
	}
	function closeFlDialog(dlgId)
	{
		 jQuery('#'+dlgId ).hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#'+dlgId ).removeClass('custom-popup');
	}
 /*END*/
 
 /*****For tabs******/
	
	
	jQuery("#tabWorkOrder").tabs({ onSelect:function(title){     					
		//jQuery(".tabs-title").bind("click", function(event){
				//if(jQuery(this).text() == "Downtime Breakup")
				//alert("inside tab");
				 if(title == "Communication" )
				{	
						var workorderNo = jQuery('#txtWogenwoNo').val();
						var employeeId = jQuery('#hdnemployeeId').val();
						if(employeeId == undefined || employeeId == 'undefined' )
							employeeId = null;
						//LoadPopUp("loadCommunication", "comm_Text.brdn?q=2&formMode=create&mode=pm&workorderNo="+workorderNo+"&employeeId="+employeeId, true,"65%","70%","0%","10%", "communication_successCallBack","Communication");
						LoadForm("loadCommunicDiv","prevloadComText","comm_Text.brdn?&formMode=create&mode=pm&workorderNo="+workorderNo+"&employeeId="+employeeId,"","communication_successCallBack","communicationTextErr");
				
					   //processGridnew('comm_view.brdn','?q=2&bdId='+jQuery('#cmbbdmsKeyid').combobox('getValue')+'&commFlag=no',"CommnGrid","CommnPager","","","","","");
				}
				else if(title == "Cost Info" )
				{
					
					var woId=jQuery('#txtWogenwoNo').val();									
					processGridnew('costSummary_view.crt',"?q=2&woId="+woId,"woCostSumryGrid","woCostSumryPager","","","","costGridLoad","costGridError");
					  // processGridnew('empCostActual_view.crt',"?q=2&formName=empCost&woId="+ woId,"tblEmpCostActGrid","empCostActPager","","tblEmpCostActGrid_dblClick","","costGridLoad","");
				}

	    	else if(title == "SAP Information" ){
	    		if(jQuery('#chkformatter').is(':checked')==true){
	    			 var sapMsg = "Do You Want To Submit To SAP ";
					 if(confirm(sapMsg) == false)
					{
							return false;
					}
					 else {	
						 alert("Select The Submit SAP");				 
						     jQuery('#chkSapinfo').click(function(){						 
							 var pmWOKeyid=jQuery("#txtWogenwoNo").val();
							 if (jQuery('#chkSapinfo').is(':checked')==true) {	
								//jQuery('#chkSapinfo').prop('disabled',true);
								
								var refDocType=null;
								var doctype=jQuery('#hdnUrl').val();				    	
						    	    if(doctype=="PM"){
						    		 refDocType="PM";				    		
						    	    }
						    	    else					
									 refDocType="BDM";
									 
									//LoadForm("loadSapInfo","prevloadSapInfo","sapInfo_input.brdn?formMode="+mode+"&isSpres="+isSpres+"&flid="+flid+"&bdkeyId="+bdkeyId+"&refDocType=BDM&woId="+woId+"&functionalloc="+cell+"&costCenter="+costCenter+"&machine="+machine,"","sapInfoComplete","sapInfoErr");
							  //  jQuery('#tabWorkOrder').width('1200')	;
							//	jQuery('#spntab').width('1230');
							LoadForm("loadSapinfoDiv","prevloadSapinfoText","sapInfo_input.brdn?refDocType="+refDocType+"&bdkeyId="+pmWOKeyid+"&isSpres=Y&flid="+flid,"","sapInfoComplete","sapInfoErr");
							
					 }
						 else
								alert("Select The Submit  to SAP");
							
						     }); 
							 	    					
					 	}
	    			}
				 
	    		else{
	    			alert("No FeedBack");
				}
				
	    	}
			}
    	 });
			function sapInfoComplete(){}
			function sapInfoErr(){}

			       	 	
			var machId = jQuery("#frmWOGen input[id='machine']").val();
    		
			//var  machId = jQuery("#frmWOGen input[id='machine']").val();
			//alert("machine_______"+machId  +  "---------"+    jQuery('#machine').val());

   /*END TABS*/
</script>
<form name="frmWOGen" id="frmWOGen">
<div id="" style=" width:100%">
<div id="sparepopDiv" class="flPopUpBox" style="margin-left:-25%;display:none;width:35%;height:56%;">
	<div id="titleSpare" class="sub-header" style="width:427px;width:390px\9;margin-left:-11px;margin-left:-9px\9;margin-bottom:15px;height:19px;margin-top:-10px;">
		<label id="lblSpare" style="margin-left:1px;font-size:11px;">Spare</label> 
		<img id="imgSpare" src="images/close-butt1.png" style="float:right;margin-top:-4%\9;"/>
	</div>
 <table id="sprpopGrid" style="width:100%"><tr><td/></tr></table>
 <div id="pager_spr"></div>
 <div style="text-align: center;margin-top:10px">
<!--	<input type="button" value="Ok" class="easyui-button" id="btnokWo" style="height:21px;width:85px">-->
	<input type="button" value="Close" class="easyui-button" id="spr_close"style="height:21px;width:85px">
</div>
</div>

<div>
<table style="width:100%;padding-left:10px;">
	<tr>
		<td >
		<div id="frmWOGenFuntKeyIds">
						<input type="hidden" id="factory" name="cmbgenwFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbgenwSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}"  ></input>
						<input type="hidden" id="cell" name="cmbgenwCellid" value="${requestScope.bdmTlMst.bdmsCellid}" ></input>
						<input type="hidden" id="machine" name="cmbgenwMachineid" value="${requestScope.bdmTlMst.bdmsMachineid}"  ></input>
						<input type="hidden" id="flid" name="hdnGenwFlid" value="${requestScope.bdmTlMst.bdmsFlid}"  ></input>
						<input type="hidden" id="elementId" name="hdnGenwElementid" value=""  />
						<input type="hidden" id="location" name="hdnGenwLocationid" value=""  />
		</div>
		<div>
		<div id="genwfunLocation" style="padding-left:20px;"></div>
              <div style="margin-left:550px ;margin-top:-25px;width:600px;">
              <span style="vertical-align:top;">
				<input id="weekNo" type="text" style="border: 1px solid black; font-size: 10px;height: 20px;width:60px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent;" disabled="disabled" value="">
				
				<input id="txtmonthYr" type="text" style="border: 1px solid black; font-size: 10px;height: 20px;width:100px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent;" disabled="disabled" value="">
				
				<input id="noAct" type="text" style="border: 1px solid black; font-size: 10px;height: 20px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent; width : 138px;" disabled="disabled" value="">
			</span>
		  		
			</div>
			   
        </div>           
		</td>
	</tr>
	<tr>
		<td style="padding-left:20px;">
			<div  class="" style="">
			<label>Cost Center</label>
			<span  class="leftmtsect" style="">
                 <label>Maint Section</label>                    
             </span>
             <span  class="leftAssm" style="">
                 <label>Assembly</label>                    
             </span>
             <span >
		  		<label style="margin-left:100px">Work Order No</label>      
                 <span style="" class="leftplanDur"><label>Plan Duration</label></span>	
		  		</span>
		  		<span style="padding-bottom:2px; display:none" class="spnComplfields" ><label class="mandatory-lbl">Completed By</label></span>
			</div> 
            <div class=""> 
                <input id="cmbWogenCostCenter" name="cmbWogenCostCenter" class="easyui-combobox"  style="width:150px;" value=""  >
             <span class="easyui-paddingbfpx" style="padding-left:10px;">  
                 <input id="cmbWogenTradeid" name="cmbWogenTradeid" class="easyui-combobox"  style="width:150px;"  value="" >                    
             </span>
              <span class="easyui-paddingbfpx" style="padding-left:10px;">  
                 <input id="cmbWogenAssemblyid" name="cmbWogenAssemblyid" class="easyui-combobox"  style="width:150px;"  value="" >                    
             </span>   
		  		<span style="padding-left:10px;" >
		  		<input type="text" id="txtWogenwoNo" name="txtWogenwoNo" class="easyui-text" readonly="readonly" style="width:170px;background-color: transparent;text-align:center " value=""  >
		  		</span>
                <span style="padding-left:10px;">
                	<input type="text" id="txtPlanDuration" name="txtPlanDuration" class="easyui-text" readonly="readonly"  style="width:100px;background-color: transparent;text-align:center " value=""  >
                </span>
                <span class="easyui-paddingbfpx spnComplfields" style="padding-left:10px;vertical-align:top;display:none">
		       		<input id="cmbbeanWofbCompletedby" name="cmbbeanWofbCompletedby" class="easyui-combobox"  style="width:269px;" value="${requestScope.completedByid}" />
		       		
			 	</span>
                <span style="vertical-align:top;display:none" class="spnComplBtn">
                <input type="button" class="easyui-button" id="btnMultipleResp" value="..." style="height: 22px;" />
				</span>
				 <span> <input type="button" value="Back" id="backBtn2nd" class="easyui-button" style="height:25px"></span>
							
				<span id="err_cmbbeanWofbCompletedby" class="tpm-errormsg" style="margin-left:360px;"></span>
             
		</div>
		
		</td>
		<tr>
		<td>
		
	    
		<div  style="padding-left:20px;">
		 
					<div id="load1stGrid" >
			 <table id="GenWOGrid" style="width:100%"><tr><td/></tr></table>
				<div id="GenWOPager"></div>
			</div>
			
			<div id="preloadDiv"></div>
		 <div id="load2ndGrid" style="display:none;">
		 <div class="chkspainfo">
		
		 <span style="padding-left:786px; margin-top:-10px;"><label style='padding-left:1px;'><b> Submit To SAP </b> </label>	
		 <input style="padding-left:786px; margin-top:-10px;" type="checkbox"  id="chkSapinfo" name="chkSapinfo"/></span>		
				
			<!-- <label id="spnShowActivity" style="padding-left: 5px;"></label></div> -->
<!--			 Tabs   -->
  	
					
			<div  id="tabWorkOrder" class="easyui-tabs"  style="width:1230px;height:352px;padding-left: 5px;">
			          <div title="Details"  style="padding-right:  0px;" index='0'>
						<table id="CompWOGrid" style="width:100%; "><tr><td/></tr></table>
						
						<!-- <div id="CompWOPager"></div> -->
					</div>
	
				
				<div title="Cost Info" id="costInfo" style="padding-right:  0px;height:325px;padding:10px;" index='1'>
					
					<div style="float:left;margin-left:3%;">
					<table id="woCostSumryGrid" width="400px"></table>
					<div id="woCostSumryPager"></div> 
					</div>
					<div id="genCostinfo" style="float:right;margin-right:3%;margin-top:2%;" index='2'>
					<input type="button" value="Resource Planning" class="easyui-button" id="btngenCostInfoWo" style="">
					</div>
				</div>
				<div title="Communication" style="padding-right:  0px;height:325px;padding:10px;">
				<div id='prevloadComText'></div>
					<div id="loadCommunicDiv"  style='margin-left:5%;'>
					</div>
			    </div>
			    <div title="SAP Information" id="spntab" style="padding-right:  0px;height:325px;padding:10px;width:1200px;">
				<div id='prevloadSapinfoText'></div>
					<div id="loadSapinfoDiv"  style='margin-left:5%;'>
			</div>
			
			
<!--		END  Tabs   -->			
		</div>
		 </div> 
		 </div>
	
	 
	</div>
	
	</div>
		</td>
		
	</tr>
	
</table>
<!--			 <div title="Unscheduled Activities" style="padding:20px;">-->
<!--			</div>-->
		 
</div>

<div id="popCBMReadings" class="flPopUpBox custom-popup">
<div >
 
		<img id="imgCBMreadClose" src="images/close-butt1.png" style="cursor:pointer;float:right;margin-top:-10px;margin-right:-11px"/>
</div>
<div id="popCBMReadingsCont"></div>

</div>
 
 <input type="hidden" id="hdnpersistdata" name="hdnpersistdata" value="${requestScope.persistdata}"/>
<input type="hidden" id="hdnfilter_string" name="hdnfilter_string" value="${requestScope.filter_string}"/>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="selectedDat" name="selectedDat"/>
<input type="hidden" id="hdnmode" value="${requestScope.mode}"/>
<input type="hidden" id="hdnmodeModify" value="${requestScope.modeModify}"/>
<input type="hidden" id="hdnweekno" value="${requestScope.strweekNo}"/>
<input type="hidden" id="hdnMnthyr" value="${requestScope.dtFromMonth}"/>
<input type="hidden" id="curReading" name="curReading"/>
<input type="hidden" id="minReading" name="minReading"/>
<input type="hidden" id="zoneCondition" name="zoneCondition"/>
<input type="hidden" id="CBMWodata" name="CBMWodata"/>
<input type="hidden" id="selRowId" name="selRowId"/>
<input type="hidden" id="ZoneColor" name="ZoneColor"/>
<input type="hidden" id="hdnMultiresp" name="hdnMultiresp" value=""/> 
<input type="hidden" id="hdnalready" name="hdnalready" value=""/>
<input type="hidden" id="hdnUrl" name="hdnUrl" value="${requestScope.url}"/>

<!--</div>
</div>
-->
</div></form>	