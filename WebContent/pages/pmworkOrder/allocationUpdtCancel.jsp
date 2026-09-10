 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<style>
	.leftmtsect{
 	margin-left:9%;
 }
 .leftAssm{
 margin-left:8%;
 }
</style>
<script type="text/javascript">
jQuery.noConflict();
  jQuery(document).ready(function(){
	  jQuery('#mainlayout').after('<div class="custom-popup LoadPopUp" id="newactPopUp" style="display:none;width:20%;height:26%;top:40%;left:30%;" >'+jQuery('#loadnewActivity').html()+'</div>');
	  jQuery('#newActvityPopWrapper').html('');
	  initialiseForm('frmWOupdateCancel');
	  jQuery('#submitForm').val('frmWOupdateCancel'); // set the id of form to submit.indexOf('&cmbAssmbid=')--&cmbFactid=
	  var url = jQuery('#hiddenUrl').val();
	  if(url.substring(url.indexOf('updtCancelGridsel_input.mpc'),url.indexOf('?q=2&')) == 'updtCancelGridsel_input.mpc'){
		  jQuery('#descLbl').removeClass('mandatory-lbl');
			jQuery("#allotedLbl").removeClass('mandatory-lbl');
	   }
	  var filterstring = url.substring(url.indexOf('&filterstring'));
	  
	  fillComboBox("frmWOupdateCancel","cmbWogenTradeid","combo_pmsdTrade.prv");
	  fillComboBox("frmWOupdateCancel","cmbWogenAssemblyid","assembly.commonFilter");
	  
	 // alert(filterstring.substring(url.indexOf('EMP'),url.indexOf("&workorderno")));
	 var allotedto = filterstring.substring(url.indexOf('EMP'),url.indexOf("&workorderno"));
	 jQuery('#hdnAllotedto').val(allotedto);
	  var assmId = filterstring.substring(filterstring.indexOf('ASM'),filterstring.indexOf('&cmbFactid='));
	  var factId = jQuery("#frmWOupdateCancel input[id='factory']").val();
	  var sectionId = jQuery("#frmWOupdateCancel input[id='section']").val();
	  var cellId = jQuery("#frmWOupdateCancel input[id='cell']").val();
	  var machId =url.substring(url.indexOf('MCH'),url.indexOf('&cmbAssmbid='));
	  var flid = jQuery("#frmWOupdateCancel input[id='flid']").val();
	  if(machId.substring(0,3) != 'MCH')
		  machId = '';
	 //alert(machId);
	 
	 
	 fillComboBox("frmWOupdateCancel","cmbWogenCostCenter","costCenter.commonFilter?machId="+machId+"&cellId="+cellId);
	jQuery('#frmWOupdateCancel .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmWOupdateCancel textarea').css('text-transform', 'uppercase');
	 var weekNO = filterstring.substring(filterstring.indexOf('&weekNo='),filterstring.indexOf('&dtFromDate='));
	  
	  weekNO = weekNO.replace('&weekNo=','  ');
	  jQuery('#hdnWeekNo').val(weekNO);
	  var monthstr = filterstring.substring(filterstring.indexOf('dtFromMonth='),filterstring.indexOf('&weekNo='));
	  monthstr = monthstr.replace('dtFromMonth=','');
	  jQuery('#selDate').val(monthstr);
	  jQuery('#txtmonthYr').html("Week No:"+weekNO+"("+monthstr+")");
	  if( machId != '' && machId != ' ' && machId != null)
		  machId =machId;
	  else 
		  machId = jQuery("#frmWOupdateCancel input[id='machine']").val();
	  var isSDM  = jQuery('#isSDM').val();
		
	  var desc =  jQuery('#txtWodescription').val() ;
	  if(desc.trim() == ""  ){
		  var sdate = getServerDateTime();
		  var serd = "WO on " + sdate.getDate()+'-'+getMonthStringFromInt(sdate.getMonth()) +'-'+sdate.getFullYear();
		  jQuery('#txtWodescription').val(serd);
	  }
	  
	  var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
	  var locId= filterstring.substring(filterstring.indexOf("LCN"),filterstring.indexOf("&actTypeSel="));
	  if(isSDM == "SDM"){
		  dataStr += "&locnId="+locId;
		   loadFunctionalLocation("genwfunLocation","functionalLocSDM.prv","genwfunLocationValues","frmWOupdateCancel",dataStr);
//		   filllocationHierarchy("locationHierarchy.commonFilter",locId,'cmbFunctLocComp');	
	  }	  
	  else					
	    loadFunctionalLocation("genwfunLocation","functionalLoc.brdn","genwfunLocationValues","frmWOupdateCancel",dataStr);
	    var isGenWo = jQuery('#isGenWo').val();
	    var isSDM  = jQuery('#isSDM').val();
		if(isSDM == "SDM"){
			filterstring+="&activitySelcted="+isSDM;
		}
		if(isGenWo == "cancel"){
			//jQuery('#altdTo').css('display','none');
			jQuery("#allotedLbl").removeClass('mandatory-lbl');
			jQuery('#cnclWoBtn').css('display','block');
			jQuery('#genWoBtn').css('display','none');
			jQuery('#newActvit').css('display','none');
			jQuery('#Observations').css('display','none');
				
		}
		else if(isGenWo == "update"){
			//jQuery("#allotedLbl").addClass('mandatory-lbl');
			jQuery('#updateWoBtn').css('display','block');
			jQuery('#cnclWoBtn').css('display','none');
			jQuery('#genWoBtn').css('display','none');
			jQuery('#newActvit').css('display','none');
			jQuery('#Observations').css('display','none');
			}
		else{  
		    jQuery('#cnclWoBtn').css('display','none');
			jQuery('#genWoBtn').css('display','block');
			jQuery('#newActvit').css('display','block');
			jQuery('#Observations').css('display','block');
			jQuery('#popObservationPopup').window({
		        width:660,
		        height:500,
		        modal:true,
		        closed:true
		        
		     });
			jQuery('#btnAddObsvtion').click(function(){

					var machId  = jQuery("#frmWOupdateCancel input[id='machine']").val();
				   
				   jQuery('#popObservationPopup').css("display","block");
				   jQuery('#popObservationPopup').window('open');
				   processGridnew("Observations_input.mpc","flid="+machId,"grdObservations");
				   	
			});
			jQuery('#btnOkObservation').unbind("click");
			jQuery('#btnOkObservation').click(function(){

				var selectdObservations  = JqGridToJsonSelRowsReqColsObservation("grdObservations","chkSelect",["txtObsrKeyid","txtObservation"]);
			    var txtPmclFactoryid =jQuery("#frmWOupdateCancel input[id='factory']").val();
				var txtPmclSectionid = jQuery("#frmWOupdateCancel input[id='section']").val();
				var txtPmclCellid = jQuery("#frmWOupdateCancel input[id='cell']").val();
				var txtPmclMachineid  = jQuery("#frmWOupdateCancel input[id='machine']").val();

				var txtPmclAssemblyid  = jQuery("#cmbWogenAssemblyid").combobox("getValue");
				var txtPmclMonthweek  =  jQuery('#hdnWeekNo').val();
				var txtPmclCalendaryear = jQuery('#selDate').val();
				var txtPmclTradeid = jQuery("#cmbWogenTradeid").combobox("getValue");
				var txtPmclFlid = jQuery("#frmWOupdateCancel input[id='flid']").val();
			    var txtPmclLocationid =jQuery("#frmWOupdateCancel input[id='location']").val();
			    var calYear = txtPmclCalendaryear.split("-");
			    var dataStr  = "txtPmclFactoryid="+txtPmclFactoryid ;
			    	dataStr += "&txtPmclSectionid="+txtPmclSectionid;
			    	dataStr += "&txtPmclCellid="+txtPmclCellid;
			    	dataStr += "&txtPmclMachineid="+txtPmclMachineid;
			    	dataStr += "&txtPmclAssemblyid="+txtPmclAssemblyid;
			    	dataStr += "&txtPmclCalendaryear=" + calYear[calYear.length-1];
			    	dataStr += "&txtPmclTradeid="+txtPmclTradeid;
			    	dataStr += "&txtPmclFlid="+txtPmclFlid;
			    	dataStr += "&txtPmclMonthweek="+txtPmclMonthweek;
			    	dataStr += "&txtPmclLocationid="+txtPmclLocationid;
			    	dataStr += "&Observations="+selectdObservations;
			    	dataStr += "&txtPmclFromdate="+ ( 7 * parseInt(txtPmclMonthweek,10) ) +"-"+ txtPmclCalendaryear;
			    	dataStr += "&txtPmclTilldate="+ ( 7 * parseInt(txtPmclMonthweek,10) + 7 ) +"-"+ txtPmclCalendaryear;
			    	dataStr += "&txtPmclMaxcompletiondate="+ ( 7 * parseInt(txtPmclMonthweek,10) + 7 ) +"-"+ txtPmclCalendaryear;
			    	dataStr += "&txtPmclScheduledfrom="+ ( 7 * parseInt(txtPmclMonthweek,10)) +"-"+ txtPmclCalendaryear;
			    	dataStr += "&txtPmclScheduledtill="+ ( 7 * parseInt(txtPmclMonthweek,10) + 7 ) +"-"+ txtPmclCalendaryear;
			    	dataStr += "&txtPmclElementid=" +jQuery("#frmWOupdateCancel input[id='elementId']").val(); 

			    	processAjaxCalls("Observations_save.mpc",dataStr,"ObservationsSave_successClBk");			   	
			});
		}
			
  	// alert("hdnLocId  "+filterstring.substring(filterstring.indexOf("LCN"),filterstring.indexOf("&actTypeSel=")));
	   processGridnew('updtCancelGridsel_input.mpc','?q=2&filterstring='+filterstring ,"UpdtCancelGd","updtcnclPager","","updatecanclDoubleClick","","updateCancelGridComplete","woGenUpselectRowFunction");
	   if(screen.width <= 1024){
		   jQuery('.leftmtsect').css('margin-left','13%');
		   jQuery('.leftAssm').css('margin-left','12%');
		   jQuery('.leftplanDur').css('padding-left','23%');
		   jQuery('.leftcompby').css('padding-left','6%'); 
		   jQuery('#txtWodescription').css('width','195px');
		   jQuery('#txtmonthYr').css('font-size','11');
		   jQuery('#txtmonthYr').css('position','absolute');
		   jQuery('#txtmonthYr').css('top','129');
		   jQuery('#txtmonthYr').css('right','40');
		   jQuery('#newActvit').css('right','270px');
		   jQuery('#newActvit').css('top','149');
		   jQuery('#Observations').css('right','310px');
		   jQuery('#Observations').css('top','149');
		   jQuery('#btngenwoact').css('height','21');
		   jQuery('#btnnewAct').css('height','21');
		   jQuery('#genWoBtn').css('top','148');
		   jQuery('#genWoBtn').css('right','123');
		   jQuery('#cmbbeanWoAllotedTo').css('width','125');
		   jQuery('#altdTo').css('left','62');
	  }
	   jQuery('#btngenABNwoact').click(function(){
			var genGridData = getSelectdRows("list","checkbox","chkSelected","generate");
			var desc = jQuery('#txtWodescription').val() ;
			var allotedTo = jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
			var weekNo = jQuery('#txtmonthYr').html();
				weekNo = weekNo.substring(weekNo.indexOf(':'),weekNo.indexOf('('));
				weekNo = weekNo.substring('1');
			  //alert(weekNo);
			var dataStr = "?q=2";
				dataStr +="&weekno="+weekNo.trim();
			    dataStr +="&genGridData="+genGridData;
			    dataStr +="&frmDatee="+jQuery('#selDate').val();
			    dataStr +="&description="+jQuery('#txtWodescription').val();
			    dataStr +="&allotedTo="+jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
			   /// alert(dataStr);
			    if (desc == '' || desc == ' ' || desc == null ){
			    	alert('Enter Description');
			    	return false;
				}
			    if (allotedTo == '' || allotedTo == ' ' || allotedTo == null ){
			    	alert('Select Alloted To');
			    	return false;
				}
			    else{ 
			    if(genGridData != '' && genGridData != ' ' && genGridData != null){
			    	
					processAjaxCalls("generateWorkOrderABN.mpc",dataStr,"genewodata_successCallBack","generateWorkOrder_errorCallBack");
			    }
			    else
				    alert('Select Activity to Generate Work Order');
			    }
			//alert(genGridData );
		});
					  
	jQuery('#btngenwoact').click(function(){
		var genGridData ;
		  var isSDM  = jQuery('#isSDM').val();
			if(isSDM == "SDM"){
				
				genGridData = getSelectdActivityRows("UpdtCancelGd","select","chkSelected","generate");
				 
			}
			else{
				genGridData = getSelectdRows("UpdtCancelGd","select","chkSelected","generate");
			 }
				
		var desc = jQuery('#txtWodescription').val() ;
		var allotedTo = jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
		var date = jQuery('#selDate').val();
		var dataStr = "?q=2";
		    dataStr +="&genGridData="+genGridData;
		    dataStr +="&frmDatee="+date;
		    dataStr +="&description="+jQuery('#txtWodescription').val();
		    dataStr +="&allotedTo="+jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
		    dataStr +="&hdnMultiresp="+jQuery("#hdnMultiresp").val();
		    //alert(dataStr);
		   
		    if (desc == '' || desc == ' ' || desc == null ){
		    	alert('Enter Description');
		    	return false;
			}
		  /*  if (allotedTo == '' || allotedTo == ' ' || allotedTo == null ){
		    	alert('Select Alloted To');
		    	return false;
			}
		    else{ */
		    if(genGridData != '' && genGridData != ' ' && genGridData != null){
		    	
		    	 if(isSDM == "SDM"){
						var weekNo = jQuery('#txtmonthYr').html(); 
						weekNo = weekNo.substring(weekNo.indexOf(':'),weekNo.indexOf('('));
						weekNo = weekNo.substring('1');
						var monthYear = date.split('-');
						
						dataStr +="&weekNo="+weekNo+"&month="+monthYear[0]+"&year="+monthYear[1];
						processAjaxCalls("generateWorkOrderSDM.mpc",dataStr,"genewodata_successCallBack","generateWorkOrder_errorCallBack");
				    }
		    	 else{
				      processAjaxCalls("generateWorkOrder.mpc",dataStr,"genewodata_successCallBack","generateWorkOrder_errorCallBack");
		    	 }
		    }
		    else
			    alert('Select Activity to Generate Work Order');
		    //}
		//alert(genGridData );
	});
	jQuery("#btnMultipleResp").click(function(){
		
		 var machId = jQuery("#frmWOupdateCancel input[id='machine']").val();
		 var pmWOKeyid=jQuery("#txtWogenwoNo").val();
		var dataString='&pmWOKeyid=&machId='+machId;
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
	jQuery('#btngenKaizebwoact').click(function(){
		var genGridData = getSelectdRows("kaizenGd","select","chkSelected","kaizengenreate");//jQuery("#kaizenGd")
		var desc = jQuery('#txtWodescription').val() ;
		var allotedTo = jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
		var weekNo = jQuery('#txtmonthYr').html();
			weekNo = weekNo.substring(weekNo.indexOf(':'),weekNo.indexOf('('));
			weekNo = weekNo.substring('1');
		  //alert(weekNo);
		  alert(genGridData );
		var dataStr = "?q=2";
			dataStr +="&weekno="+weekNo.trim();
		    dataStr +="&genGridData="+genGridData;
		    dataStr +="&frmDatee="+jQuery('#selDate').val();
		    dataStr +="&description="+jQuery('#txtWodescription').val();
		    dataStr +="&allotedTo="+jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
		   /// alert(dataStr);
		    if (desc == '' || desc == ' ' || desc == null ){
		    	alert('Enter Description');
		    	return false;
			}
		    if (allotedTo == '' || allotedTo == ' ' || allotedTo == null ){
		    	alert('Select Alloted To');
		    	return false;
			}
		    else{ 
		    if(genGridData != '' && genGridData != ' ' && genGridData != null){
		    	
				processAjaxCalls("generateWorkOrderKaizen.mpc",dataStr,"genewodata_successCallBack","generateWorkOrder_errorCallBack");
		    }
		    else
			    alert('Select Activity to Generate Work Order');
		    }
		//alert(genGridData );
	});
	jQuery('#btnpopOk').click(function(){
		
		if(jQuery('#chkabnormality').is(':checked') == true)
		{
			closeFlDialog('newactPopUp');
			var url = jQuery('#hiddenUrl').val();
			var filterstring = url.substring(url.indexOf('&filterstring'));
			var weekNO = filterstring.substring(filterstring.indexOf('&weekNo='),filterstring.indexOf('&dtFromDate='));
			weekNO = weekNO.replace('&weekNo=','  ');
			
			var monthstr = filterstring.substring(filterstring.indexOf('dtFromMonth='),filterstring.indexOf('&weekNo='));
			monthstr = monthstr.replace('dtFromMonth=','');
			jQuery('#load1stGrid').css('display','none');
			jQuery('#loadABNGrid').css('display','block');
			jQuery('#loadkaizenGrid').css('display','none');
			//processGridnew("AbnModify_input.mpc","?q=2&weekNO="+weekNO+"&monthstr="+monthstr,"abnGrid","pager","","nxtgrid","","abnormalityGridOncompleteLoad");
			navigateToNextForm("AbnModify_input.mpc?q=4&wogen&weekNO="+weekNO+"&monthstr="+monthstr+"&loadContentDivId=loadABNGrid,&preLoadContentDivId=preloadDIVid3","Abnormality");
		    jQuery('#wrapperRpt').css('width','900px');
		    
		    jQuery('#genAbnWoBtn').css('display','block');
			jQuery('#genWoBtn').css('display','none');
		}
		else if(jQuery('#chkkaizen').is(':checked') == true)
		{
			closeFlDialog('newactPopUp');
			jQuery('#load1stGrid').css('display','none');
			jQuery('#loadABNGrid').css('display','none');
			jQuery('#loadkaizenGrid').css('display','block');
			
			var url = jQuery('#hiddenUrl').val();
			var filterstring = url.substring(url.indexOf('&filterstring'));
			filterstring +="&KAIZEN=Y";
			//navigateToNextForm("kaizen_input.kaizen?q=2","Improvement Projects - Creation");
			processGridnew('kaizenGridsel_input.mpc','?q=2filterstring='+filterstring ,"kaizenGd","kaizenPager","","kaizenDoubleClick","","kaizenComplete");
			jQuery('#genKaizenWoBtn').css('display','block');
			jQuery('#genAbnWoBtn').css('display','none');
			jQuery('#genWoBtn').css('display','none');
		}
		else if(jQuery('#chkunschedule').is(':checked') == true)
		{
			 for(var i=formNavigations.length;i>3;i--) {
				 popFormNavigation();
			 }
			closeFlDialog('newactPopUp');
			var assmId  = jQuery("#cmbWogenAssemblyid").combobox("getValue");
			var machId  = jQuery("#frmWOupdateCancel input[id='machine']").val();
			var weekNo  =  jQuery('#hdnWeekNo').val();
			var monthYr = jQuery('#selDate').val();
			var tradeId = jQuery("#cmbWogenTradeid").combobox("getValue");
			var filterString = "&machId="+machId+"&weekNO="+weekNo+"&monthstr="+monthYr+"&assmId="+assmId+"&tradeId="+tradeId;
			var forwardData="";
			var persistentData="";
			jQuery('#load1stGrid').css('display','none');
			jQuery('#loadABNGrid').css('display','none');
			jQuery('#loadkaizenGrid').css('display','none');
			
			//navigateToNextForm("unschedactivity_input.unschedacty?q=2&filterData="+filterString,"Unscheduled Activity",forwardData,persistentData);
			navigateToNextForm("unschedactivity_input.unschedacty?q=2&filterData="+filterString,"Unscheduled Activity");
		}
		else{
			alert("Select Option");
			return false;
		}
	});	
	
	jQuery('#chkabnormality').click(function(){		
 		if(jQuery('#chkabnormality').is(':checked') == true)
		{
 			jQuery('input:checkbox[id=chkkaizen]').attr('checked',false); 	
 			jQuery('input:checkbox[id=chkunschedule]').attr('checked',false);
		}	
	});
	jQuery('#chkkaizen').click(function(){		
 		if(jQuery('#chkkaizen').is(':checked') == true)
		{
 			jQuery('input:checkbox[id=chkabnormality]').attr('checked',false); 	
 			jQuery('input:checkbox[id=chkunschedule]').attr('checked',false);
		}	
	});
	jQuery('#chkunschedule').click(function(){		
 		if(jQuery('#chkunschedule').is(':checked') == true)
		{
 			jQuery('input:checkbox[id=chkabnormality]').attr('checked',false); 	
 			jQuery('input:checkbox[id=chkkaizen]').attr('checked',false);
		}	
	}); 
	jQuery('#btnnewAct').click(function(){
		openmpdialog();
		
		//LoadPopUp("loadnewActivity", "", true,"20%","20%","0%","10%", "addnew_successCallBack","New Activity");
	});
	jQuery( "#imgNewActivity" ).click(function() {
		
		closeFlDialog('newactPopUp');
	});

	
	function closeFlDialog(dlgId)
	{
			 
		 jQuery( '#'+dlgId ).hide();
		 jQuery('#newactPopUp').hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#'+dlgId ).removeClass('custom-popup');
	}
	jQuery( "#btnpopoCncl" ).click(function() {
		
		closeFlDialog('newactPopUp');
	});
	jQuery('#btnUpdate').click(function(){
				var allotedTo =jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
				if(allotedTo != ' ' && allotedTo !='' && allotedTo != null )
					cancelCall('update');
				//else
					//alert('Select Alloted to');
		});
	jQuery('#btncancel').click(function(){
		var description = jQuery('#txtWodescription').val();
		var allotedTo =jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
	    //showValidationErrorMsg('cmbbeanWoAllotedTo','Select Alloted to');
		//showValidationErrorMsg('txtWodescription','Enter Description');
	var conFcancel = confirm("Do You Want To Cancel the Activites");
	  if(conFcancel){
		 cancelCall();
	   }
			
	});
	if(screen.width >= 1280){
		jQuery("#wrapper").css('width','1100px');
	}
	jQuery(document).keyup(function(e){
		
	    if(e.keyCode === 27){
	    	 
	    	closeFlDialog('newactPopUp');
	    }

	});
	 var filterstring =  jQuery('#hiddenUrl').val();
	 var assmId = getFilterValue( filterstring+"&","cmbAssmbid"); //filterstring.substring(filterstring.indexOf('ASM'),filterstring.indexOf('&cmbFactid='));
	 if(assmId.substring(0,3)=="ASM")
	 	setFieldValue('cmbWogenAssemblyid', assmId,'frmWOupdateCancel');
	 disableField('frmWOupdateCancel','cmbWogenAssemblyid');
	 var allottedToId = jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
	 if( allottedToId.trim() == "" || allottedToId.trim().length == 0 ) {
		 if(checkResponsibilityExist()){
			 
			 setFieldValue('cmbbeanWoAllotedTo', jQuery("#hdnWoLoginEmpid").val(),'frmWOupdateCancel');
		 }	 
	 }
});
  function ObservationsSave_successClBk(result){

		if(result.successData != undefined && result.successData.msg != undefined ){
			alert(result.successData.msg);
			jQuery("#grdObservations").trigger("reloadGrid");
			
			jQuery("#UpdtCancelGd").trigger("reloadGrid");
		}	
		
	}
  function checkResponsibilityExist(){
	  var rowid = jQuery("#UpdtCancelGd").jqGrid('getDataIDs');
	  for(var i=0;i<rowid.length;i++){
		 var resp = jQuery("#UpdtCancelGd").jqGrid('getCell',rowid[i],'responsibility');
		 if( resp.trim() == "" || resp.trim.length == 0    )
			return false;	 
	  }
	  return true ;
	  
  }
  function JqGridToJsonSelRowsReqColsObservation(jqGridId,ckeckForSelColName,requiredColArr)
  {
  	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
  	var reqColLength=requiredColArr.length;
  	var jsonArrO='[';
  	for( var i = 0; i < allRows.length;i++)
  	{
  		var row = allRows[i];
  		var value = row[ckeckForSelColName];
  		var id = parseInputJqGridTrString(value);
  		if( jQuery("#"+id).is(":checked") )
  		{
  			jsonArrO += '{';
  			for(var colName in row) 
  			{
  				for(var j=0;j<reqColLength;j++)
  				{
  					if(requiredColArr[j]== colName )
  					{
  						var cellValue = parseJqGridCellValue(row[colName]);
  						//alert(cellValue.trim().length);
  						if( cellValue.trim().length > 0 && cellValue!=" " ) 
  							jsonArrO += '"'+colName +'":"' + cellValue +'",';
  					}					
  				}
  			}
  			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
  		}
  	}
  	jsonArrO = jsonArrO.slice(0, -1) + "]";
  	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
  	return jsonArrO; 
  }
  
  function ObservationCheckbox_formater(cellValue,options, rowObject){
	  var  id = "Observation_"+options.rowId;
	  
	  return '<input type="checkbox" id="chk'+id +'" />';
  }
  function loadFunctionalLocationHierarchy_successCallback(){
	  var isSDM  = jQuery('#isSDM').val();
	  var url = jQuery('#hiddenUrl').val();
	  var filterstring = url.substring(url.indexOf('&filterstring'));
	  var locId= filterstring.substring(filterstring.indexOf("LCN"),filterstring.indexOf("&actTypeSel="));
	  if(isSDM == "SDM"){
		  //dataStr += "&locnId="+locId;
		   
		   //jQuery("#frmWOupdateCancel input[id='location']").val(locId);
		  }
  }
  function kaizenComplete(){
		jQuery('#kaizenGridcalled').val('true');
  }
  /**Function for  selecting/Unselecting all Rows**/
  function woGenUpselectRowFunction_selectAll(id,status){
   
  	for(var i=0; i<id.length; i++){
  		if(status)
  			selectData(id[i]);
  		else
  			unselectData(id[i]);
  	}
  }
  /**End**/
  /**Function for  selecting/Unselecting  Row**/
  function woGenUpselectRowFunction_selectRow(id){
	 
  	if(jQuery('#jqg_UpdtCancelGd_'+id).is(':checked'))
  		selectData(id);
  	else
  		unselectData(id);
  }
  /**End**/
  
  function genewodata_successCallBack(result){
	  if(result.retmsg != 'err'){
		alert(result.retmsg);
		navigateToPrevForm();
	  }
	  else{
		  var alloted = jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
		  
		  if(alloted == "" || alloted.trim().length == 0  )
		 	 alert("No Responsibility  or Alloted to Selected "); 
		  else
			alert('Work Order Generated');
	  }		
	if(result.retmsg != 'err')
		jQuery('#UpdtCancelGd').trigger("reloadGrid");
	
	
	}
function generateWorkOrder_errorCallBack(result){
	alert('error');
	}
function openmpdialog(){

	jQuery('#mstfrm_div').addClass('popup-mask');	
	jQuery('#mstfrm_div').show();
	jQuery('#newactPopUp').show();
	jQuery('#loadnewActivity').addClass('custom-popup');		
  //jQuery('#loadnewActivity').show();		  
  jQuery('#loadnewActivity').css('border','1px solid #F1F5FB');
  jQuery('#loadnewActivity').css('z-index',100); 
}
  function cancelCall(catgery){
	  var rowId = jQuery("#selectedDat").val();
		var url = jQuery('#hiddenUrl').val();
		var workorderno = url.substring(url.indexOf('&workorderno='));
		var rowid = jQuery("#UpdtCancelGd").jqGrid('getDataIDs');
		 //alert(rowid.length);
		var dataStr = "";
		//alert('before submit' +rowId);
		if(rowid.length >0)
			dataStr += "&noofActivites="+rowid.length;
		else{
			if(catgery!= 'update')
				alert("Select Activites to Cancel");
			else
				alert("Select Activites to Update");
		}
		var gridData = getSelectdRows("UpdtCancelGd","select","chkSelected");
		if(gridData != ' ' && gridData != '' && gridData!= null)
			dataStr += "&gridData="+gridData;
		else{
			alert("Select Record");
			return false;
		}
		dataStr += workorderno;
		dataStr += "&allotedtocombo="+jQuery("#cmbbeanWoAllotedTo").combobox("getValue");
		dataStr += "&txtdescription="+jQuery('#txtWodescription').val();
		dataStr +="&hdnMultiresp="+jQuery("#hdnMultiresp").val();
		//alert(dataStr);
		if(catgery!= 'update')
			processAjaxCalls("cancelActivites_input.mpc",dataStr,"cancelActivites_successCallBack","cancelActivites_errorCallBack");
		else
			processAjaxCalls("updateActivites_input.mpc",dataStr,"updateActivites_successCallBack","updateActivites_errorCallBack");
  }
  function updateActivites_successCallBack(result){
		alert(result.successData);
		jQuery('#UpdtCancelGd').trigger("reloadGrid");
		//popFormNavigation();
		navigateToPrevForm();
  }
  function updateActivites_errorCallBack(){
		alert('Error');
	  }
  function cancelActivites_successCallBack(result){
	//alert("--"+Object.keys(result));
	alert(result.successData);
	jQuery('#UpdtCancelGd').trigger("reloadGrid");
	popFormNavigation();
	navigateToPrevForm();
 }
  function cancelActivites_errorCallBack(){
	alert('Error');
  }
  function chkFormatter(id, options, rowObject)
  {
  	var id = options.rowId;
    	return '<input  id="woChkBx" name="woChkBx" type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
  }

  function selectData(rowId){
  
	  var cm = jQuery("#UpdtCancelGd").jqGrid("getGridParam", "colModel");
	  	var kaizenGrid = jQuery('#kaizenGridcalled').val();
	  	
	  	if(kaizenGrid.trim().length>0)
	  		jQuery("#kaizenGd").setCell(rowId, "chkSelected","1");
	  	else
	  	 jQuery("#UpdtCancelGd").setCell(rowId, "chkSelected","1");
	  	jQuery("#selectedDat").val(rowId);
		
	}

  function unselectData(rowId){
	 
		var cm = jQuery("#UpdtCancelGd").jqGrid("getGridParam", "colModel");
		var kaizenGrid = jQuery('#kaizenGridcalled').val();
	  	if(kaizenGrid.trim().length>0)
	  		jQuery("#kaizenGd").setCell(rowId, "chkSelected","0");
	  	else
	  	   jQuery("#UpdtCancelGd").setCell(rowId, "chkSelected","0");																		
	
  }
 function updatecanclDoubleClick(id){
	 	var rowData = jQuery("#UpdtCancelGd").jqGrid('getRowData',id);		
	 		//alert(Object.keys(rowData));																					
		var woId = rowData.workorderId;
		var machineId = rowData.machineId;
 }
  
function updateCancelGridComplete(data)
{
	//var rowid = jQuery("#UpdtCancelGd").jqGrid('getDataIDs');
	 //alert(rowid.length);
	//jQuery("td.jqgrow:odd").css("background", "#C4C2C2");
	if(screen.width <= 1024)
		jQuery( "#MPgrid" ).setGridHeight("");
	else if(screen.width >= 1366){
		
		jQuery( "#UpdtCancelGd" ).setGridHeight("250px");
	}
	
	var intRegex = /^\d+$/;
	 var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
		var rowid = jQuery("#UpdtCancelGd").jqGrid('getDataIDs');
		var cm = jQuery("#UpdtCancelGd").jqGrid("getGridParam", "colModel");
		var sumval = null;
		var tradeid =jQuery("#UpdtCancelGd").jqGrid('getCell',1,'tradeId');
		if(tradeid.substring(0,3) == "TDE")
		 setTimeout(function() {
			 setFieldValue('cmbWogenTradeid',tradeid);
		 
		disableField('frmWOupdateCancel','cmbWogenTradeid');
		disableField('frmWOupdateCancel','cmbWogenCostCenter');},1250);
		 for(var i=0;i<rowid.length;i++)
		 {
			 for(var j=10;j<11;j++)
	     	 {
				 planVal = jQuery("#UpdtCancelGd").jqGrid('getCell',rowid[i],cm[j].name);	
				 sumval += parseInt(planVal);
				 
	     	 }
		 }
		
		 if(intRegex.test(sumval) || floatRegex.test(sumval)) {
			 sumval=sumval;
		 }
		 else
			 sumval = 0;
		jQuery('#txtplanDur').val(sumval);
		/*****onclick header check all*****/
		jQuery('.ui-paging-info').css('font-size','12px');		
		jQuery("#UpdtCancelGd_select").click(function()
		{	
					var mode = jQuery('#modechk').val();
					
					 setTimeout(function() {
					if(mode == 0 || mode == ' ' || mode == '' || mode == null)
					{	
					for(i= 0;i<=rowid.length;i++)
						{
						jQuery('input:checkbox[name=woChkBx]').attr('checked',true);
						selectData(i);
						mode = 1;
						jQuery('#modechk').val('1');
					    }
					}
					else
					{
						for(i= 0;i<=rowid.length;i++)
						{
							jQuery('input:checkbox[name=woChkBx]').attr('checked',false);
							unselectData(i);
							mode = 0;
							jQuery('#modechk').val('0');
						}						
					}	},250);			
					event.preventDefault();
		});		 
		
	    
}
function frmWOupdateCancel_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	fillComboBox("frmWOupdateCancel","cmbbeanWoAllotedTo","employee.commonFilter?sectId="+keyIds.sectId );
	fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","cmbWogenCostCenter");	
}
function  frmWOupdateCancelcmbWogenCostCenter_onLoadSuccess(){
	
	
}
function  frmWOupdateCancelcmbWogenTradeid_onLoadSuccess(){
	
}
function  frmWOupdateCancelcmbWogenAssemblyid_onLoadSuccess(){
	
	 
}
function  frmWOupdateCancelcmbbeanWoAllotedTo_onLoadSuccess(){
	//var altdToval = jQuery('#hdnAllotedto').val();
	//if(altdToval.substring(0,3)=="EMP")
	// 	setFieldValue('cmbbeanWoAllotedTo',altdToval,'frmWOupdateCancel');
	 var isGenWo = jQuery('#isGenWo').val();
	    
		if(isGenWo == "cancel"){
			disableField('frmWOupdateCancel','cmbbeanWoAllotedTo');
			disableField('frmWOupdateCancel','txtWodescription');
			jQuery('#txtWodescription').attr('disabled','disabled');
			jQuery('#txtWodescription').css('background-color', '#D1E2FD');
		}
}
function frmWOupdateCancel_beforeSubmit(){
	
/*	var rowId = jQuery("#selectedDat").val();
	var url = jQuery('#hiddenUrl').val();
	var workorderno = url.substring(url.indexOf('&workorderno='));
	var dataStr = "";
	//alert('before submit' +rowId);
	var gridData = getSelectdRows("UpdtCancelGd","select","chkSelected");
	dataStr += "&gridData="+gridData;
	dataStr += workorderno;
	alert(dataStr);
*/
return false ;
			
}
function frmWOupdateCancel_successsCallback(result){
	jQuery('#UpdtCancelGd').trigger("reloadGrid");
	
}
function getSelectdActivityRows(jqGridId,checkBoxColName,ckeckForSelColName,generateWo)
{
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		//alert("value"+value);		
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
				//jsonArrO += '{';
			for(var colName in row) {
				if(generateWo!='' && generateWo != ' ' && generateWo != null){
					
					
					if(colName == 'pmsdkeyid'  )
					{
					var cellValue = parseJqGridCellValue(row[colName]);
					//alert("colName   " +colName);planduration
					jsonArrO +=  cellValue;
					}
				
				}
					
			
			}
			jsonArrO +=  ",";
			
		}
	} 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--   "+jsonArrO);
	return jsonArrO; 
} 

function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName,generateWo)
{
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		//alert("value"+value);		
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
				//jsonArrO += '{';
			for(var colName in row) {
				if(generateWo!='' && generateWo != ' ' && generateWo != null){
					
					if(generateWo == 'kaizengenreate'){
						if(colName == 'workorderId')
						{
						var cellValue = parseJqGridCellValue(row[colName]);
						//alert("colName   " +colName);
						jsonArrO +=  "'"+cellValue+"'";
						}
					}
				else{
					if(colName == 'pmcalId' ||colName == 'TagNo')
					{
					var cellValue = parseJqGridCellValue(row[colName]);
					//alert("colName   " +colName);planduration
					jsonArrO +=  cellValue;
					}
				 }
				}
				else{
					if(colName == 'workorderId')
					{
					var cellValue = parseJqGridCellValue(row[colName]);
					//alert("colName   " +colName);
					jsonArrO +=  "'"+cellValue+"'";
					}
				}	
			
			}
			jsonArrO +=  ",";
			
		}
	} 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--   "+jsonArrO);
	return jsonArrO; 
} 



</script>
<form name="frmWOupdateCancel" id="frmWOupdateCancel">
<div id="wrapper" style="">

<div class="" style="" >
 
		<table width=100% >
			<tr>
				<td >
				<div id="frmWOupdateCancelFuntKeyIds">
								<input type="hidden" id="location" name="cmbPmsdLocation" value="${requestScope.plmTlStandards.pmsdLocation} "  ></input>
								<input type="hidden" id="factory" name="cmbgenwFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}"  ></input>
								<input type="hidden" id="section" name="cmbgenwSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}"  ></input>
								<input type="hidden" id="cell" name="cmbgenwCellid" value="${requestScope.bdmTlMst.bdmsCellid}" ></input>
								<input type="hidden" id="machine" name="cmbgenwMachineid" value="${requestScope.bdmTlMst.bdmsMachineid}"  ></input>
								<input type="hidden" id="flid" name="cmbgenwFlid" value="${requestScope.bdmTlMst.bdmsFlid}"  ></input>
				</div>
				<div id="genwfunLocation" style="padding-left:20px;"></div>
				</td>
			</tr>
			<tr>
				<td style="padding-left:20px;" >
					<div  class="easyui-paddingbfpx" style="padding-top:10px;">
					<label>Cost Center</label>
					<span  class="easyui-paddingbfpx leftmtsect" style="">
		                 <label>Maint Section</label>                    
		             </span>
		             <span  class="easyui-paddingbfpx leftAssm" style="">
		                 <label>Assembly</label>                    
		             </span>
		             <span style="margin-left:9.5%;">
			           <label id= "descLbl" class="mandatory-lbl">Description </label>
			    	</span>
			    	<span style="margin-left:21.4%;;">
			           <label class="">Plan Duration </label>
			    	</span>
					</div> 
		            <div class="easyui-paddingbfpx" > 
		            <span style="vertical-align: top;">
		                <input id="cmbWogenCostCenter" name="cmbWogenCostCenter" class="easyui-combobox"  style="width:150px;" value=""  >
		             </span>
		             <span class="easyui-paddingbfpx" style="padding-left:10px;vertical-align: top;">  
		                 <input id="cmbWogenTradeid" name="cmbWogenTradeid" class="easyui-combobox"  style="width:150px;"  value="" >                    
		             </span>
		              <span class="easyui-paddingbfpx" style="padding-left:10px;vertical-align: top;">  
		                 <input id="cmbWogenAssemblyid" name="cmbWogenAssemblyid" class="easyui-combobox"  style="width:150px;"  value="" >                    
		             </span>   
					<span class="easyui-paddingbfpx" >
				     	<textarea rows="1" style="width: 286px;" cols="" rows="3"  id="txtWodescription" name="txtWodescription" maxlength="500" <c:out value = "${requestScope.workOrderDetailsBean.disableForm == true ? ' disabled':''}"/>>${requestScope.plmTlWofeedback.wofbFeedback}</textarea>
				     </span>
				     <span id="err_txtWodescription" class="tpm-errormsg" style="float:right;margin-right:47%;"></span>
			     	 <span style="vertical-align: top">
				     <input id="txtplanDur"  style="" disabled=disabled class="easyui-text" ></input>
				     <label id="txtmonthYr"  style="background-color:transparent;width:154px;color:#2465CB;font-weight:bold;font-size:15;"></label>
				     </span>
			     </div>
			     <div id="altdTo" style="position: absolute;top:30%;left:4.7%">
				     <div style="margin-left:10px; ">
				     	<label id="allotedLbl" class="">Alloted To</label>
				     </div>
				     <div class="easyui-paddingbfpx" style="padding-left:10px;vertical-align:top;">
					       		<input id="cmbbeanWoAllotedTo" name="cmbbeanWoAllotedTo" class="easyui-combobox"  style="width:269px;" value="${requestScope.prepByid}" / >
					       		<input type="button" class="easyui-button" id="btnMultipleResp" value="..." style="height: 22px;" />
					</div>
				</div>
				<div style="position:relative;margin-top:-13%;">
				<div id="Observations" style="position: absolute;top:140;right:300;display:none;">
				<input type="button" value="Add From Observations" class="easyui-button" id="btnAddObsvtion" style="height:21px;width:155px;">
				</div>
				<!-- <div id="newActvit" style="position: absolute;top:140;right:208;display:none;"><input type="button" value="New Activity" class="easyui-button" id="btnnewAct" style="height:21px;"></div> -->
				
				<div id="cnclWoBtn" style="position: absolute;top:135;right:81;"><input type="button" value="Cancel Activity" class="easyui-button" id="btncancel" style="height:21px;"></div>
				<div id="updateWoBtn" style="position: absolute;top:135;right:81;display:none;"><input type="button" value="Update Activity" class="easyui-button" id="btnUpdate" style="height:21px;"></div>
				<div id="genWoBtn" style="position: absolute;top:140;right:69;display:none;"><input type="button" value="Generate Work Order" class="easyui-button" id="btngenwoact" style="height:21px;width:135px;"></div>
				
				
				
				<div id="genKaizenWoBtn" style="position: absolute;top:135;right:81;display:none;"><input type="button" value="Generate Work Order" class="easyui-button" id="btngenKaizebwoact" style="height:21px;width:135px;"></div>
				<div id="genAbnWoBtn" style="position: absolute;top:135;right:81;display:none;"><input type="button" value="Generate Work Order" class="easyui-button" id="btngenABNwoact" style="height:21px;width:135px;"></div>
				</div>
				</td>
			</tr>
		</table>
				
				
						<div class="floatleft" style="padding-left:20px;margin-top: 24px;margin-top: 100px\9;">
							<div id="load1stGrid" style="float:left;">
							    <table id="UpdtCancelGd" style="width:100%"><tr><td/></tr></table>
								<div id="updtcnclPager"></div>
								
							</div>
							<div id="loadABNGrid" style="float:left;display:none">
								<!--<table id="abnGrid" ></table>
								<div id="pager"></div>
							--></div>
							<div id="loadkaizenGrid" style="float:left;display:none">
							  <table id="kaizenGd" style="width:100%"><tr><td/></tr></table>
							  <div id="kaizenPager"></div>
							</div>
				        </div>
				     
					
				
		</div>
</div>
<div id="newActvityPopWrapper">
 <div id="loadnewActivity" class="flPopUpBox" style="margin-top:16%;display:none;width:22%;height:30%;">
	<div id="titleNewActivity" class="sub-header" style="width:107%;margin-left:-11px;margin-bottom:15px;height:19px;margin-top:-10px;">
		<label id="lblNewActivity" style="margin-left:1px;font-size:11px;">Select Activity Type</label> 
		<img id="imgNewActivity" src="images/close-butt1.png" style="float:right;cursor:pointer;margin-top:-15px\9;"/>
	</div>
		<div style="padding-bottom:5px;margin-left:20%"><input type="checkbox" id="chkabnormality" name="abnormality" /><label style="margin-left:5px;">Abnormality Red Tag</label></div>
		<div style="padding-bottom:5px;margin-left:20%"><input type="checkbox" id="chkkaizen" name="kaizen" /><label style="margin-left:5px;">Kaizen</label></div>
		<div style="padding-bottom:15px;margin-left:20%"><input type="checkbox" id="chkunschedule" name="unschedule" /><label style="margin-left:5px;">Unscheduled Activity</label></div>
		<div style="margin-left:50px;margin-top:2%;"><input type="button" id="btnpopOk" value="Ok" class="easyui-button" />
		<span style="margin-left:10px;">
		<input type="button" id="btnpopoCncl" class="easyui-button" value="Cancel"/>
		</span>
		</div>
 </div>
</div>
<div id="popObservationPopup" style="width: 200px">
<div id="" style="margin-left:35px">
	<table id="grdObservations" style="width:100%"><tr><td/></tr></table>
	<div style="vertical-align:bottom;width:100%">
	<input type="button" id="btnOkObservation" class="easyui-button" value="Add" style="cursor:pointer;margin-left:34%;margin-top:20px;width: 200px;height: 20px"/>
	</div>
</div>	
</div>
<input type="hidden" id="hdnfilter_string" name="hdnfilter_string" value="${requestScope.filter_string}"/>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="selectedDat" name="selectedDat"/>
<input type="hidden" id="selecDat" name="selecDat"/>
<input type="hidden" id="selDate" name="selDate"/>
<input type="hidden" id="isGenWo" name="isGenWo" value="${requestScope.generateWO}"/>
<input type="hidden" id="hdnAllotedto" name="hdnAllotedto"/>
<input type="hidden" id="modechk" value=""/>
<input type="hidden" id="hdnWeekNo" value=""/>
<input type="hidden" id="kaizenGridcalled" name = "kaizenGridcalled" value=""/>
<input type="hidden" id="isSDM" name="isSDM" value="${requestScope.actTypeSel}"/>
<input type="hidden" id="hdnWoLoginEmpid"  value="${requestScope.loginEmpId}"/>
<input type="hidden" id="hdnMultiresp" name="hdnMultiresp" value=""/> 
 <input type="hidden" id="hdnalready" name="hdnalready" value=""/>
<!--</div>
</div>
--></form>	