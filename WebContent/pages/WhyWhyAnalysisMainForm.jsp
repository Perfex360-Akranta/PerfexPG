<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 --%><script type="text/javascript">
/* Added by Praveen*/
  

  jQuery(document).ready(function(){
	initialiseForm('frmWhyWhyAnalysis');
	//alert(jQuery('#hdnrefDoctype').val());
	var formMode=jQuery("#hdnMode").val();
		var formMode1=jQuery("#hdnMode2").val();
//mano

		
	//alert(formMode1+ "  123  " +formMode);
	var type = jQuery("#type").val();
	//alert(type);
	
	var documentNo =jQuery("#txtWwmsKeyid").val();
	var locationId=jQuery("#hdnlocationid").val();
	
		jQuery('#submitForm').val('frmWhyWhyAnalysis');
	
	formatDateBox('dteWwmsDate','dd-MM-yyyy');	
	formatDateBox('dteWwmsReportdate','dd-MM-yyyy');	

	fillComboBox("frmWhyWhyAnalysis","cmbWwmsMachineid","machineCombo.commonFilter");
	fileManagerPopUp("","WhyWhyAnalysis","frmWhyWhyAnalysis","btnFilManage","WhyWhyAnalysisFilemgr");


	if (getFieldValue('dteWwmsReportdate')=='' || getFieldValue('dteWwmsReportdate')==' ')
		fillWithCurrentDate('dteWwmsReportdate');
	
	if (getFieldValue('dteWwmsDate')=='' || getFieldValue('dteWwmsDate')==' ')
		fillWithCurrentDate('dteWwmsDate');
	
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsWhywhydoneby","employee.commonFilter");
	//fillComboBox("frmWhyWhyAnalysis","cmbWwmsSparesId","spareCombo.commonFilter");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsSparesId","");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsPillarid","pillar.commonFilter");	
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsProductid","product.commonFilter");
	 fillComboBox("frmWhyWhyAnalysis","cmbWwmsTradeId","Combo_Trade.abnForm");

	formatDateBox('dteNmrtOccurrencedatetime','dd-MMM-yyyy');
	spinnerKeyPress('spnNmrtOccurrencedatetime');
	spinnerChange('spnNmrtOccurrencedatetime','setShiftEvt');
	spinnerUp('spnNmrtOccurrencedatetime','setShiftEvt');
	spinnerDown('spnNmrtOccurrencedatetime','setShiftEvt');

	numericTextBox('txtWwmsTimespent');
	
	var factId = jQuery("#frmWhyWhyAnalysis input[id='factory']").val();
	var sectionId = jQuery("#frmWhyWhyAnalysis input[id='section']").val();
	var cellId = jQuery("#frmWhyWhyAnalysis input[id='cell']").val();
	var machId = jQuery("#frmWhyWhyAnalysis input[id='machine']").val();
	var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	
 	loadFunctionalLocation("whywhyfunlocation","functionalLoc.why","whywhyfunlocationValues","frmWhyWhyAnalysis",dataStr);

 	var masterkeyid=jQuery("#txtWwmsKeyid").val();
 	var refType=jQuery('#hdnrefDoctype').val();
 	if(refType=="WM" ){
 		//alert("2");
 		//jQuery('div').remove('#wrapper');
 		jQuery('#wrapper').attr('id','wras');
 		jQuery('#frmWhyWhyAnalysisFuntKeyIds').css('display','none');
 		
 	}

/* if(locationId=="LCN0000001"){
 		jQuery('#lblPillar').addClass('mandatory-lbl');
 		
 	}
 	else{
 		
 		jQuery('#lblPillar').removeClass('mandatory-lbl');
 	}
 	 */
  // var detailkeyid=jQuery("#txtWwdtKeyid").val();
 //	madhan
 	 processGridnew("whywhyanalysisgrid_input.why","q=2&masterkeyid="+masterkeyid,"AnalysisGrid","pagerAnalysis","","","","yyGrid_loadcomplete"); //TTTT  detail
 	processGridnew("yyDonebyLink_input.why","q=2&masterkeyid="+masterkeyid,"donebyGrid","pagerDoneby","","","","yyDonebyGrid_loadcomplete"); //TTTT  detail
 	processGridnew("yyprobAttbyLink_input.why","q=2&masterkeyid="+masterkeyid,"donebyGrid2","pagerDoneby2","","","","yyDonebyGrid2_loadcomplete"); //TTTT  detail
    processGridnew("whywhyproposedgrid_input.why","q=2&masterkeyid="+masterkeyid,"ProposedGrid","pagerProposed","","","","proposedgrid_loadcomplete","");
    processGridnew('rootcause_view.why','?q=2&openMode=',"rootPillarGrid","rootCausePager","","","","","");
    processGridnew('rootcause_modify.why','q=2&openMode=BDM',"rootPillarGrid","","","","","rootCauseOnLoad",""); 
    
//     processGridnew('pillar_view.why',dataString,"PillarGrid","","","","","pillarOnLoad",""); /////ttttt
   if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
    
    // Disable the Analysis Grid (AnalysisGrid)
    jQuery("#AnalysisGrid").jqGrid('setGridParam', {
        cellEdit: false,
        editable: false
    });
    
    //Disable Functional Location
    // Disable everything inside the div
   jQuery('#whywhyfunlocation').css({
  'pointer-events': 'none'
   
});
		 

    
    // Disable the Done By Grid (donebyGrid)
    jQuery("#donebyGrid").jqGrid('setGridParam', {
        cellEdit: false,
        editable: false
    });
    
    // Disable the Problem Attended By Grid (donebyGrid2)
    /* jQuery("#donebyGrid2").jqGrid('setGridParam', {
        cellEdit: false,
        editable: false
    }); */
    
    // Disable the Proposed Grid (ProposedGrid)
    /* jQuery("#ProposedGrid").jqGrid('setGridParam', {
        cellEdit: false,
        editable: false
    }); */
    
    // Disable the Root Cause Grid (rootPillarGrid)
    jQuery("#rootPillarGrid").jqGrid('setGridParam', {
        cellEdit: false,
        editable: false,
        onCellSelect: function() { return false; } // Prevent cell selection
    });
 if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        
        // Disable all form buttons
        disableUIButton("btnAddyyy");
        disableUIButton("btnAddpab");
        disableUIButton("btnAddDoneby");
        disableUIButton("btnlegend");
        
        // Disable all form fields
        jQuery("#txtWwmsArea").attr('readonly', 'readonly');
        jQuery("#cmbWwmsMachineid").combobox('disable');
        jQuery("#cmbWwmsTradeId").combobox('disable');
        jQuery("#dteWwmsDate").datebox('disable');
        jQuery("#chkWwmsSparesreplaced").attr('disabled', 'disabled');
        jQuery("#cmbWwmsSparesId").combobox('disable');
        jQuery("#txtWwmsProblem").attr('readonly', 'readonly');
        jQuery("#dteWwmsReportdate").datebox('disable');
        jQuery("#spnNmrtOccurrencedatetime").timespinner('disable');
        jQuery("#txtWwmsFinalaction").attr('readonly', 'readonly');
        jQuery("#cmbWwmsProblemattendby").combobox('disable');
        jQuery("#txtWwmsTimespent").attr('readonly', 'readonly');
        jQuery("#cmbWwmsPillarid").combobox('disable');
        jQuery("#cmbWwmsProductid").combobox('disable');
        jQuery("#cmbWwmsWhywhydoneby").attr('readonly', 'readonly');
        jQuery("#txtWwmsOthercheckpoints").attr('readonly', 'readonly');
        jQuery("#chkequipment").attr('disabled', 'disabled');
        
        // Disable counter measure buttons
        for(var i=1; i<=6; i++) {
            disableUIButton('btnbutton_'+i);
            jQuery("#btnbutton_" + i).attr('disabled', 'disabled');
        }
        
        // Keep Excel View button enabled
        enableUIButton("btnViewTemplate");
        
        // Add overlay to Analysis grid area
        jQuery('#divAnalys').append('<div id="divhide1" style="position:absolute;top:60%;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
    }

}


    jQuery(document).keydown(function(e) {
		
	    if (e.keyCode == 27) {
	    	//jQuery("#lgnd-panel").hide(0);
	    }    
	    jQuery('#lgnd-panel').focusout(function() { 
	 	});
	});
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").click(function(){
		//alert("Read From File");
		var keyId=getFieldValue("txtWwmsKeyid");
		window.open("whywhyExl_view.why?keyId="+keyId);			
	});
	
	  jQuery("#cmbWwmsMachineid").combobox({onRequest:function( ){
     	 var cellId = getFieldValue('cell','frmWhyWhyAnalysis');
		 var filterStr = "&cellId=" +cellId ;
		 return filterStr;
				
	}}); 
	  
	 jQuery('.panel-title').filter(function(){
		    return jQuery(this).parent('div').parent('div').hasClass('panel layout-panel layout-panel-center');
	}).text("Why Why Analysis ");

	 

});	
//alert(123);
  jQuery("#btnlegend").click(function() {
  	
		jQuery("#lgnd-panel").slideToggle(200);
	});
  function BtnFormatterDelete2(id, options, rowObject)
  {	
	  //mano
	  var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    
    // Don't show delete button in view mode
    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        return '';
    }
    //mano end
	  		
  	var rowId = options.rowId;
  	var gridId = options.gid;

  	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
  }
  function BtnFormatterDelete(id, options, rowObject)
  {	
	  //mano
	  var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        return '';
    }
    
    //mano end
	 		
  	var rowId = options.rowId;
  	var gridId = options.gid;

  	return '<input type="button" id="remov2" class="grdButton" value="" onclick="deleterec2(\''+rowId + '\',\''+gridId + '\');"/>';
  }

  function deleterec(rowid,gridId){
	  
	  var r = confirm("Are You Sure To Delete?");
	  if(r){
		var keyId;
		keyId = getGridCell(gridId,rowid,'keyid');
		processAjaxCalls('YYDoneBy_delete.why','&keyid='+keyId,'deleteYYDoneby_onsuccessCallBack','deleteYYDoneBy_onerrorCallBack');
	  }
  }

  function deleterec2(rowid,gridId){
	  
	  var r = confirm("Are You Sure To Delete?");
	  if(r){
		var keyId;
		
		keyId = getGridCell(gridId,rowid,'keyid');
		
		processAjaxCalls('YYProbAttBy_delete.why','&keyid='+keyId,'deleteYYProbAttBy_onsuccessCallBack','deleteYYProbAttBy_onerrorCallBack');
	  }
  }

  
	function deleteYYProbAttBy_onsuccessCallBack(result){ 
		
	      alert(result.msg);
	      jQuery("#donebyGrid2").trigger("reloadGrid");
	}
	
	
		jQuery("#btnAddpab").click(function() {

		var yykeyid=jQuery("#txtWwmsKeyid").val();
		var yypab = jQuery('#cmbWwmsProblemattendby').combobox('getValue');
		
		var ds = "yyKeyid="+yykeyid+"&yypab="+yypab;
		
		if (yykeyid==null || yykeyid==' ' || yykeyid=='') {
			popupCommonErrorMsg(" Please Save Why Why First. ");
			return false;
		}
	
		if(yypab.trim().length>0)
			
			processAjaxCalls("probAttbyLink_save.why", ds, 'probAttbySave_successCallBack','probAttbySave_errorCallBack');
		
		else
			
			popupCommonErrorMsg(" Select Why Why Done By");

	});
	
	
	
	function probAttbySave_errorCallBack(result) {
		popupCommonErrorMsg(result);
	}

/*  jQuery("#btnFilManage").click(function(){
	  //alert("1");
	    var documentNo =result.successData.wwmsKeyid;
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"WWA","","","");
		}
 });
*/
    document.onkeypress = function(evt){
	    evt = evt || window.event;
	    var charCode = (typeof evt.which == "number") ? evt.which : evt.keyCode;
	    if (String.fromCharCode(charCode) == '"') {
		    alert(" Double Quotes is not allowed");
	        return false;
	    }
	};
	
  function yyGrid_loadcomplete()
  {
  	//mano
  	var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    //mano end
  	//disableGridSort("AnalysisGrid");

  	var row  = jQuery("#AnalysisGrid").jqGrid('getDataIDs');
      if(row.length>0)
  		enableCounterMeasure();
  	else
  		disableCounterMeasure();

      jQuery("#jqgh_AnalysisGrid_Answer").removeClass("ui-jqgrid-sortable");
  	  jQuery("#jqgh_AnalysisGrid_Delete").removeClass("ui-jqgrid-sortable");	   
      jQuery("#gbox_AnalysisGrid").children().removeClass("ui-jqgrid-sortable");
  	  disableGridSort("AnalysisGrid");	
  	if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        for(var i = 0; i < row.length; i++) {
            var rowid = row[i];
            jQuery('#txtWhy_' + rowid).attr('readonly', 'readonly');
            jQuery('#txtWhy_' + rowid).addClass('readonly');
            // Hide delete buttons
            jQuery('#btnDelete_' + rowid).hide();
        }
    }else{
    	for(var i = 0; i < row.length; i++) {
            var rowid = row[i];
            jQuery("#txtWhy_"+rowid).on(' click keydown', function(e){
			    e.stopPropagation();
			});
       
        }
    }
  }
 
  function yyDonebyGrid_loadcomplete() {
	  
	  //alert('completed');
  }
  function yyDonebyGrid2_loadcomplete() {
	    var mode = jQuery("#mode").val();
	    var formMode1 = jQuery("#hdnMode2").val();
	    
	    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
	        var row = jQuery("#donebyGrid2").jqGrid('getDataIDs');
	        for(var i = 0; i < row.length; i++) {
	            var rowid = row[i];
	            // Hide delete buttons in view mode
	            jQuery('#remov2').hide();
	        }
	        
	        // Disable cell editing
	        jQuery("#donebyGrid2").jqGrid('setGridParam', {
	            cellEdit: false,
	            editable: false
	        });
	    }
	}
  function btnFilManage_click(){
      
	    var documentNo =jQuery("#txtWwmsKeyid").val();
	    //alert(" documentNo:::1234::: "+documentNo);
	    
		if(documentNo != null && documentNo != ''){
			//alert(frmMode);
			//var frmMode=jQuery('#frmMode').val();
			var formMode1=jQuery("#hdnMode2").val();
			
			apMode = "create";
			if(formMode1=="View")
			   apMode = "view";
			
		
			fileManagerPopUp(documentNo,"YY","","","",apMode);
				
		} else
	    {
			 saveForm('frmWhyWhyAnalysis','whywhyanalysismodify_save.why?filemanager=filemanager');
	     }	
}
  function BtnFormatterDelete(id, options, rowObject)
  {	
	  //mano
	   var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        return '';
    }
    //mano end 
  	var rowId = options.rowId;
  	var gridId = options.gid;

  	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
  }

  function deleterec(rowid,gridId){
	  
	  var r = confirm("Are You Sure To Delete?");
	  if(r){
		var keyId;
		keyId = getGridCell(gridId,rowid,'keyid');
		processAjaxCalls('YYDoneBy_delete.why','&keyid='+keyId,'deleteYYDoneby_onsuccessCallBack','deleteYYDoneBy_onerrorCallBack');
	  }
  }
  
	function deleteYYDoneby_onsuccessCallBack(result){ 
	      alert(result.msg);
	      jQuery("#donebyGrid").trigger("reloadGrid");
	}	   
	function BtnFormatterDelete(id, options, rowObject)
	  {		
		 		
	  	var rowId = options.rowId;
	  	var gridId = options.gid;

	  	return '<input type="button" id="remov2" class="grdButton" value="" onclick="deleterec2(\''+rowId + '\',\''+gridId + '\');"/>';
	  }
	function deleterec2(rowid,gridId){
		  
		  var r = confirm("Are You Sure To Delete?");
		  if(r){
			var keyId;
			
			keyId = getGridCell(gridId,rowid,'keyid');
			
			processAjaxCalls('YYProbAttBy_delete.why','&keyid='+keyId,'deleteYYProbAttBy_onsuccessCallBack','deleteYYProbAttBy_onerrorCallBack');
		  }
	  }
	function probAttbySave_successCallBack(result) {
		jQuery("#donebyGrid2").trigger("reloadGrid");
		if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		else
			alert(result.successData.msg);
	}
	
	//Changed Dec-14-2018 By Ilanthamilan
function proposedgrid_loadcomplete(ids)
{
	console.log("after load Start");
		//mano
	var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    //mano end 
	//fillComboBoxWithGrid("frmabnAllocation","cmbstatus_","effective.commonFilter");
	fillComboBoxWithGrid("frmWhyWhyAnalysis","cmbstatus_","effective.commonFilter");
	jQuery("#btnbutton_1").val('OPL');
	jQuery("#btnbutton_2").val('CLTI');
	jQuery("#btnbutton_3").val('CM');
	jQuery("#btnbutton_4").val('PM');
	jQuery("#btnbutton_5").val('KAIZEN');
	jQuery("#btnbutton_6").val('Action plan');
	//jQuery("#btnbutton_6").val('VISUAL SOP');
	//disableUIButton('btnbutton_2');
	//disableUIButton('btnbutton_3');
	//disableUIButton('btnbutton_4');
	
	//var rowID =  jQuery("#ProposedGrid").jqGrid('getRowData');
	var rowID = jQuery("#ProposedGrid").jqGrid('getRowData');
	var cmCnt = false;
	var disableCounter = false;
	console.log("after load Start 1");
	for(var i=1;i<=rowID.length;i++)
	{	
		var rowData = jQuery("#ProposedGrid").jqGrid('getRowData',i);
		var keys = rowData.keyid;	
		console.log("keys :"+keys);
		var status = jQuery("#cmbstatus_"+i).combobox('getValue');
		
		if(keys.length > 3 ){			
			cmCnt = true;
			readOnlyFields("txtWhy_"+i);
		}		
		
		if(status.toUpperCase() == 'EFFECTIVE' || status=="EFF00004" ){				
			disableCounter = true;	
		}
		//mano
		if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
            jQuery("#btnbutton_" + i).attr('disabled', 'disabled');
            jQuery("#cmbstatus_" + i).combobox('readonly',true);
            //jQuery("#cmbstatus_" + i).combobox('disable');
        }
	}
	console.log("after load Start 2");
	if (cmCnt==true){
		//jQuery('#divAnalys').append('<div id="divhide1" style="position:absolute;top:60%;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
		//EFF00004
		//disableForm("frmWhyWhyAnalysis");
		enableUIButton("btnViewTemplate");
		enableCounterMeasure();			
	}
	console.log("after load Start 3");
	 if(disableCounter == true)
	{
		
		//disableForm("frmWhyWhyAnalysis");
		enableUIButton("btnViewTemplate");
		disableCounterMeasure();
	}
	 console.log("after load Start 4");
	 //mano
	 if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        for(var i=1; i<=6; i++) {
            disableUIButton('btnbutton_'+i);
            jQuery("#btnbutton_" + i).attr('disabled', 'disabled');
            jQuery("#btnbutton_" + i).addClass('disabled');
        }
    }
	 console.log("after load 5");
}

function txtFormatter1(id, options, rowObject) {
	//mano
	var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
   //mano end 
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	//alert("rowid:"+id);
	//mano
	 var disabled = '';
    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        disabled = 'disabled="disabled"';
    }
    //mano end
	
	if(columnNo==3)
		return '<input type="button" id="btnbutton_'+id+'" name="btnbutton_'+id+'"  value="--" onclick="openpillarform('+id+')"   style="width:70px;  height:18px;" value="button"  class="easyui-button" />';		
	if(columnNo==6){			
		return '<input id="cmbstatus_'+id+'" name="cmbstatus_'+id+'" style="width:100px;" readonly="readonly" class="easyui-combo" value="'+rowObject[5].trim()+'"/>';			 
	}
	
// 	return '<img id="imgStatusimage_'+id+'" name="imgStatusimage_'+id+'" src="images/green0.jpg" width="20px" height="20px" align="middle" />';
		
}
 
//jQuery("#txtWwmsOthercheckpoints").hide();

jQuery("#chkequipment").click(function(){     //select CheckBox
		if(jQuery("#chkequipment").is(":checked") == false)
			readOnlyFields("txtWwmsOthercheckpoints");
		else
			enableFields("txtWwmsOthercheckpoints");
	});
function openpillarform(id){
	//mano
	var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    
    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        alert("Counter measures cannot be opened in View Mode");
        return false;
    }
    if(id == 2 || id == 3 || id == 4 ) {
    	id = 6;
    }
	var rowData = jQuery("#ProposedGrid").jqGrid('getRowData',id);
	if( checkRootCauseSelected() ){
		//alert("inside");
		
		jQuery("#hdnCounterMeasure").val(jQuery("#btnbutton_"+id).val());
		jQuery("hdnClickCounterMeasure").val("Y");
		jQuery("#hdnCounterMeasureId").val(rowData.keyid);
		//alert("rowData.keyid:"+rowData.keyid);
		//alert("rowData.keyid:"+jQuery("#hdnCounterMeasureId").val());
		jQuery("#ImgSave").trigger("click");
	}
	else{
		alert(" Select Root Cause");
	}
	
	
	
	//saveForm('frmWhywhyAnalysis','whywhyanalysismodify_save.why');
	
	/*if(id==1){
		
		var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
		var msg = "Click Ok for Traning Program List / Click Cancel for OPL";
		if(confirm(msg))
			navigateToNextForm('WhyWhyTrainingList_input.why?flid='+flid,'Training Program List');
		else
			navigateToNextForm("create_input.opl?flid="+flid,"One Point Lesson");
		//LoadPopUp("OPL","create_input.opl", true, "87%", "80%", "7%", "6%", " ", "One Point Lesson","",true);
		}
	else if(id==2){
		navigateToNextForm("jhcalendar_input.jhcal?filterString=?q=2&dtFromMonth=Dec-2013&dtToMonth=Dec-2013&cmbMchid=undefined&areaId=MMA00051","CLTI");
		//LoadPopUp("CLTI","jhcalendar_input.jhcal?filterString=?q=2&dtFromMonth=Dec-2013&dtToMonth=Dec-2013&cmbMchid=undefined&areaId=MMA00051", true, "87%", "80%", "7%", "6%", " ", "CLTI","",true);
	}
	else if(id==3){
		navigateToNextForm("PMReport_input.prv","Condition Monitoring");
		//LoadPopUp("Condition","PMReport_input.prv", true, "87%", "80%", "7%", "6%", " ", "Condition Monitoring","",true);
	}
	else if(id==4){
		navigateToNextForm("monplanconf_input.mpc","Preventive Maintenance");
		//LoadPopUp("Preventive","monplanconf_input.mpc", true, "87%", "80%", "7%", "6%", " ", "Preventive Maintenance","",true);
	}
	else if(id==5){
		navigateToNextForm("kaizen_input.kaizen","Kaizen");
		//LoadPopUp("Kaizen","kaizen_input.kaizen", true, "87%", "80%", "7%", "6%", " ", "Kaizen","",true);
	}
	else if(id==6){
		navigateToNextForm("Sopmodify_input.sop","SOP");
		//LoadPopUp("SOP","Sopmodify_input.sop", true, "87%", "80%", "7%", "6%", " ", "SOP","",true);
	}*/
}

function checkRootCauseSelected(){
	
	var row  = jQuery("#rootPillarGrid").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++)
	{
		var tick = jQuery("#rc_"+row[i]).html();
		if(tick.trim().length > 0)
			return true;
		
	}	
	return false;
}
function txtFormatter(id, options, rowObject){ //Answer      value set in grid
	//mano
	var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    
    var disabled = '';
    if(mode == "View" || mode == "view" || formMode1 == "View" || formMode1 == "view") {
        disabled = 'readonly="readonly" readonly';
    }//mano end
	return '<input type="text" id="txtWhy_'+options.rowId+'"   name="txtWhy_'+options.rowId+'"  value="'+rowObject[2]+' "  style="width:900px;  height:18px;" maxlength="250"   class="easyui-textbox" />';
}
function actionFormatterC(cellvalue, options, rowObject) 
{
	 var rowId = options.rowId;
	 var formatStr  = '<span id="rc_'+rowId+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
}

//mano 


function frmWhyWhyAnalysiscmbWwmsPhenomenaid_onSelect(record)
{
	//alert("record "+record.id);

	loadFunctionalLocation("whywhyfunlocation","functionalLoc.why","whywhyfunlocationValues","frmWhyWhyAnalysis","&machId="+record.id);
	
	}
	
/* function frmWhyWhyAnalysiscmbWwmsTradeId_onSelect(record)
{
	//alert("record "+record.id);
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter?trade=" +record.id+" &yymode=Y&others=N");
	} */
	
	function frmWhyWhyAnalysiscmbWwmsTradeId_onSelect(record)
	  {
		//alert("record "+record.id);
		var cellId = jQuery("#frmWhyWhyAnalysis input[id='cell']").val();
		//alert("cellId "+cellId);
		fillComboBox("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter?trade=" +record.id+" &yymode=Y&others=N&cellId="+cellId);
		
		jQuery('#chkWwmsPabOthers').prop('checked', false);
		
		//fillComboBox("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter?yymode=Y&others=N&cellId="+cellId);
		}
	

	jQuery('#chkWwmsPabOthers').click(function() {
		 
		 var tradeId = jQuery("#cmbWwmsTradeId").combobox('getValue');
		 var cellId = jQuery("#frmWhyWhyAnalysis input[id='cell']").val();
		 if(jQuery("#chkWwmsPabOthers").is(':checked')== true){
	 	
		     // Show employees from other trades (excluding selected trade)
		    
		        fillComboBox(
		            "frmWhyWhyAnalysis",
		            "cmbWwmsProblemattendby",
		            "employeeCombo.commonFilter?trade=" + tradeId + "&yymode=Y&others=Y&cellId="+cellId);
		 }
		 else if(jQuery("#chkWwmsPabOthers").is(':checked')== false){
			 fillComboBox(
			            "frmWhyWhyAnalysis",
			            "cmbWwmsProblemattendby",
			            "employeeCombo.commonFilter?trade=" + tradeId + "&yymode=Y&others=N&cellId="+cellId);
			 
		 }
		        
		     });



function frmWhyWhyAnalysiscmbWwmsPillarid_onSelect(record)
{
	var data  = record.text.split("-");
	
	if(data[1]=="QM")
		enableFields('cmbWwmsProductid');
		
}

function frmWhyWhyAnalysis_FuntLocHierarchy_SuccessCallBack(keyIds)
{	
	//var flid = result.flid;
	//jQuery("#txtWwmsFlid").val(flid);  cellId="+keyIds.cellId+"&flid="+keyIds.flId
	
	var area=jQuery('#txtWwmsArea').val();
	var cellId=getFieldValue('cell','frmWhyWhyAnalysis');
	
	if(cellId!=null && cellId.trim().length>0)
		if (getFieldValue("txtWwmsArea").trim().length==0)
		processAjaxCalls("whywhyanalysisgrid_recall.why?&cellId="+cellId,"","recallsuccessCallBack","errorCallBack");
	
	if (getFieldValue("cmbWwmsMachineid").length==0) {
		reloadCombo("frmWhyWhyAnalysis","cmbWwmsMachineid","machineCombo.commonFilter?cellId="+keyIds.cellId+"&flid="+keyIds.flId);
		setTimeout(function() {
			setFieldValue("cmbWwmsMachineid",keyIds.machId);
		},500);
	}
	 else{
		 reloadCombo("frmWhyWhyAnalysis","cmbWwmsSparesId","spareCombo.commonFilter?machId="+getFieldValue("cmbWwmsMachineid"));//madhan
	} 
	if (getFieldValue("cmbWwmsProblemattendby").length==0)
		reloadCombo("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employee.commonFilter?&cellId="+keyIds.cellId);
 	
	//reloadCombo("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter?&cellId="+keyIds.cellId);
// 	reloadCombo("frmWhyWhyAnalysis","cmbWwmsWhywhydoneby","employeeCombo.commonFilter?&cellId="+keyIds.cellId);
	//alert(flid);
	
	
	
}

function recallsuccessCallBack(result){
	if (getFieldValue("txtWwmsArea").trim().length==0)
		setFieldValue('txtWwmsArea',result[0][0]);
	
}
var mode = jQuery("#mode").val();
var formMode1=jQuery("#hdnMode2").val();

if(mode == "View" || mode == "view" || formMode1=="View" || formMode1 == "view" ){
	
	
	disableForm("frmWhyWhyAnalysis");  
	disableUIButton("btnAddyyy");
	disableUIButton("btnAddpab");
	disableUIButton("btnAddDoneby");
	
disableUIButton("btnlegend");  
// Disable Legend button
//disableUIButton("btnFilManage");
    
    // Disable file manager button (if it exists)
   // jQuery("#btnFilManage").attr('disabled', 'disabled');
   // jQuery("#btnFilManage").addClass('disabled');
	for(var i=1; i<=6; i++) {
	        disableUIButton('btnbutton_'+i);
	    }
	 
	enableUIButton("btnViewTemplate");
	
	jQuery('#divAnalys').append('<div id="divhide1" style="position:absolute;top:60%;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
}
function oplrecallsuccessCallBack(result){
	jQuery('#hdnContrMsureStatus').val(result[0][0]);
	var CountrmsrSts=result[0][0];
	var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
	var yykeyid=jQuery("#txtWwmsKeyid").val();
	var proposedID = jQuery("#hdnCounterMeasureId").val();
	var ds = "refDocNo="+yykeyid+"&refDocType=YY&flid="+flid+"&oplKeyId="+proposedID;
	if(CountrmsrSts.trim().length>0 && CountrmsrSts=="C"){		
		ds+="&whywhymod=View&mod=view";
	}
	else{
		ds+="&mod=modify";
	}
	
	//navigateToNextForm("create_input.opl"+ds,"One Point Lesson");
	LoadPopUp("OPL", "create_input.opl?" + ds, true, "97%", "91%", "5%", "13px", "", "One Point Lesson", true, true);
	//closePopUpDialoge("OPL"); 
}


function kznrecallsuccessCallBack(result){
	
	jQuery('#hdnContrMsureStatus').val(result[0][0]);
	var CountrmsrSts=result[0][0];
	var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
	var yykeyid=jQuery("#txtWwmsKeyid").val();
	var proposedID = jQuery("#hdnCounterMeasureId").val();
	var ds = "refDocNo="+yykeyid+"&flid="+flid+"&refDocType=YY&Keyid="+proposedID+"&";
	if(CountrmsrSts.trim().length>0 && CountrmsrSts=="V")		
		ds+="&frmmode=View";
	else
		ds+="&frmmode=create";
	
	 
		LoadPopUp("Kaizen",'KaizenBankSuggestion_input.kznbnk?'+ds, true,"97%","91%","5%","13px", "","Kaizen",false,true);
	//navigateToNextForm('KaizenBankSuggestion_input.kznbnk'+ds,'Kaizen');
	
	
}
/*
 
function actionplanrecallsuccessCallBack(result){
	
	jQuery('#hdnContrMsureStatus').val(result[0][0]);
	alert("inside successcallback");
	var CountrmsrSts=result[0][0];
	var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
	var yykeyid=jQuery("#txtWwmsKeyid").val();
	var proposedID = jQuery("#hdnCounterMeasureId").val();
	var ds = "?actPlanRefMasId="+yykeyid+"&flid="+flid+"&actPlanRefDocType=YY&actPlanKeyId="+proposedID+"&";
	/*if(CountrmsrSts.trim().length>0 && CountrmsrSts=="V")		
		ds+="&apmode=View";
	else
		ds+="&apmode=create";
	
	navigateToNextForm('ActionPlan_input.api'+ds,'Action plan');
}
*/

function setQuestionInGrid() {
    var quest = jQuery('#txtWwmsFinalaction').val();
	var ImmediateAction =jQuery('#txtWwmsFinalaction').val();
	
	if(ImmediateAction != "" && ImmediateAction != " " && ImmediateAction != undefined )
	{
		var row  = jQuery("#AnalysisGrid").jqGrid('getDataIDs');
		if (row<=0) { 	
			var emptyItem =[{Keyid:" ", ques :quest, ans:" "}];		 
	    	jQuery("#AnalysisGrid").jqGrid('addRowData',1, emptyItem[0]);
	    	
	    	jQuery("#AnalysisGrid").jqGrid('setCell',1,"txtWwdtWhy",quest);
	    	jQuery("#AnalysisGrid").jqGrid('setCell',1,"txtWwdtKeyid",""); 				
	    	jQuery('#txtWhy_'+1).val(" ");
		}
	}		

}


function frmWhyWhyAnalysis_deleteSuccessCallback(result)
{
	alert(result.msg);
	//navigateToPrevForm("whywhyanalysis_input.why");
}
function frmWhyWhyAnalysis_beforeSubmit()
{
	//alert("inside YY");
	var mode = jQuery("#mode").val();
var formMode1=jQuery("#hdnMode2").val();

if(mode == "View" || mode == "view" || formMode1=="View" || formMode1 == "view" ){
alert(" You can not Update the Data in View Mode ");
return false;
}
	
var locationId=jQuery("#hdnlocationid").val();
	var cellId = jQuery("#frmWhyWhyAnalysis input[id='cell']").val();
	if (cellId.trim().length==0) {
		alert('Select JH');
		return false;
	}
		
		var gridData  = yyyCheckDatas();		
		if(gridData.trim()=="" || gridData == null)
		{
			alert("Enter Why Why ");
			return false;
		}

		var reportdate=getFieldValue("dteWwmsReportdate");
		if(reportdate==" " || reportdate.trim().length==0){  //emppillar!=null ||
			popupCommonErrorMsg(" Select Reported Date & Time ");
			return false;
		}
		
             var PillarID=getFieldValue("cmbWwmsPillarid");
		
		if(PillarID.length==0){		
			popupCommonErrorMsg("Select the Pillar");
			return false;
		}
		
		   var tradeId=getFieldValue("cmbWwmsTradeId");
			if(tradeId.length==0){		
				popupCommonErrorMsg("Select the Trade");
				return false;
			}
		
		var timeSpent=jQuery("#txtWwmsTimespent").val();
	
		if(timeSpent==" " ||timeSpent==""||timeSpent==null||timeSpent=="undefined"){
		alert("Enter the Time Spent to Correct the Problem ");
		return false;
		}
		

		
		var whywhydate=getFieldValue("dteWwmsReportdate")+ " " +getFieldValue("spnNmrtOccurrencedatetime");
		setFieldValue("hdnWwmsReportdatetime",whywhydate);
		gridData = 'YYAnalysis='+gridData; 
		// ---------- 15-day save validation added by Gopi on may 04.05.2026 ----------
		var today = getServerDateTime();
		var fifteenDaysAgo = new Date(today);
		fifteenDaysAgo.setDate(fifteenDaysAgo.getDate() - 90);
		
		var wwmsDate = jQuery('#dteWwmsDate').datebox('getValue');
		if(wwmsDate != null && wwmsDate != '' && wwmsDate != ' '){
			var selectedDate = convertStringToDate(wwmsDate);
			if(selectedDate < fifteenDaysAgo){
				alert('You cannot save older than 3 months data');
				return false;
			}
		}
		
		var reportDate = getFieldValue("dteWwmsReportdate");
		if(reportDate != null && reportDate != '' && reportDate.trim().length > 0){
			var selectedReportDate = convertStringToDate(reportDate);
			if(selectedReportDate < fifteenDaysAgo){
				alert('You cannot save older than 3 months data');
				return false;
			}
		}
		// ---------- end of 15-day validation ----------
		var formMode=jQuery("#hdnMode").val();
		var formMode1=jQuery("#frmMode").val();
		var empArr="&empIds="+getFieldValue("cmbWwmsWhywhydoneby")+"&pillarId="+PillarID+"&timeSpent="+timeSpent+"&formMode="+formMode;
		return gridData+empArr;
		
}
       /* function yyyCheckDatas(){

        var row=jQuery("#AnalysisGrid").jqGrid('getDataIDs');//	row get data
		var col=jQuery("#AnalysisGrid").jqGrid("getGridParam","colModel");// col get data
		var dtlkeyid;
		//var dtlkeyid = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"keyid");
		//alert(dtlkeyid);
		var jsonArrO='[';
		//alert("row.length" +row.length);
		  for(var i=0;i<row.length;i++)
			{
			 // alert(4); 
		        var rowid=row[i];
			    // alert(5 +"  --  "+rowid);
				
					 var ques = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"txtWwdtWhy");  //grid datas
			 					 var dtlkeyid = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"txtWwdtKeyid");
				var rownnno=jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"rn");
				//jsonArrO += '"txtWwdtSlno":"'+escape(rownnno)+'",';

					 var ans = jQuery('#txtWhy_'+rowid).val();	  //text vals
					 
					//alert(ans);
					  if((ans !=null && ans!=""))
						  {
					
					//var Keyid = jQuery(this).attr('Keyid');
					//jsonArrO += "txtWwdtWhy :\""+ques+"\",";
					ques=ques.trim();
					
						jsonArrO += '{';
					jsonArrO += '"txtWwdtWhy":"'+escape(ques)+'",';
					jsonArrO += '"txtWwdtSlno":"'+escape(rownnno)+'",';
					//jsonArrO += '"txtOlqdKeyid":"'+keyid+'",';
					jsonArrO += '"txtWwdtKeyid":"'+dtlkeyid+'",';	 
					//ans = ans.trim().replace(/["~!@#$%^&*\(\)_+=`\[\]\|\\'\/?"\-\t\r\n]+/g, '-');
					ans=ans.trim();
					jsonArrO += '"txtWwdtAnswer":"'+escape(ans)+'"';
					//jsonArrO += '"txtWwdtAnswer":"\n/"\n/""';
					//jsonArrO += "txtWwdtKeyid :\""+ Keyid+"\"";
					jsonArrO +=  "},";

			}
						
			}
						
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		return jsonArrO; 
	
}
 */
 function yyyCheckDatas(){
	    var row=jQuery("#AnalysisGrid").jqGrid('getDataIDs');// row get data
	    var col=jQuery("#AnalysisGrid").jqGrid("getGridParam","colModel");// col get data
	    var dtlkeyid;
	    var jsonArrO='[';
	    
	    for(var i=0;i<row.length;i++) {
	        var rowid=row[i];
	        var ques = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"txtWwdtWhy"); //grid datas
	        var dtlkeyid = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"txtWwdtKeyid");
	        var rownnno=jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"rn");
	        var ans = jQuery('#txtWhy_'+rowid).val(); //text vals
	        
	        // Check if answer is empty or null
	        if(ans == null || ans.trim() == "" || ans.trim().length == 0) {
	            alert("Please enter the Why Why for the given analyses");
	            return ""; // Return empty string to trigger the validation in beforeSubmit
	        }
	        
	        // If answer exists, add to JSON
	        ques = ques.trim();
	        jsonArrO += '{';
	        jsonArrO += '"txtWwdtWhy":"'+escape(ques)+'",';
	        jsonArrO += '"txtWwdtSlno":"'+escape(rownnno)+'",';
	        jsonArrO += '"txtWwdtKeyid":"'+dtlkeyid+'",';
	        ans = ans.trim();
	        jsonArrO += '"txtWwdtAnswer":"'+escape(ans)+'"';
	        jsonArrO += "},";
	    }
	    
	    jsonArrO = jsonArrO.slice(0, -1) + "]";
	    jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	    return jsonArrO;
	}
jQuery("#btnAddyyy").click(function()
	{
		var row  = jQuery("#AnalysisGrid").jqGrid('getDataIDs');
		
		/*if (row.length>=5) 
			alert('Maximum allowed Why Why is 5.');
		else*/ 
		 	addRow(row.length);//row add function	 	
		 	
		enableCounterMeasure();

	});

	jQuery("#btnAddDoneby").click(function() {

		var yykeyid=jQuery("#txtWwmsKeyid").val();
		var yydoneby = jQuery('#cmbWwmsWhywhydoneby').combobox('getValue');
		var ds = "yyKeyid="+yykeyid+"&yyDonebyid="+yydoneby;
		if (yykeyid==null || yykeyid==' ' || yykeyid=='') {
			popupCommonErrorMsg(" Please Save Why Why First. ");
			return false;
		}
		
		if(yydoneby.trim().length>0)
			processAjaxCalls("doneByLink_save.why", ds, 'donebySave_successCallBack','donebySave_errorCallBack');
		else
			popupCommonErrorMsg(" Select Why Why Done By");

	});
		

	function donebySave_successCallBack(result) {
		jQuery("#donebyGrid").trigger("reloadGrid");
		if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		else
			alert(result.successData.msg);
	}
	
	function donebySave_errorCallBack(result) {
		popupCommonErrorMsg(result);
	}
	
	function enableCounterMeasure()
	{
		for(i=1;i<=6;i++){
			
			enableUIButton('btnbutton_'+i);
			readOnlyFields("cmbstatus_"+i);
		}
		
		//disableUIButton('btnbutton_4');
		
		/*enableUIButton('btnbutton_2');
		enableUIButton('btnbutton_3');
		enableUIButton('btnbutton_4');
		enableUIButton('btnbutton_5');
		enableUIButton('btnbutton_6');*/
		
		
	}
	function disableCounterMeasure()
	{
		
		for(i=1;i<=6;i++){
			disableUIButton('btnbutton_'+i);
			readOnlyFields("cmbstatus_"+i);
		}
		
		/*disableUIButton('btnbutton_1');
		disableUIButton('btnbutton_2');
		disableUIButton('btnbutton_3');
		disableUIButton('btnbutton_4');
		disableUIButton('btnbutton_5');
		disableUIButton('btnbutton_6');*/
		
	}
	function addRow(row)
	{
		var i=0;
	    var val =  jQuery("#hdnval").val();	    
	    var answer=getFieldValue('Answer');
	    var j = parseInt(val);
		if (row == null || row == '' || parseInt(row)<=0) 
				    { 
						 var ImmAction= jQuery("#txtWwmsFinalaction").val();
						/*if(ImmAction==null || ImmAction==' '|| ImmAction=='')
						{
							alert("Enter the ImmediateAction");
							return false;
						}*/
						if (checkMandatoryValidation()==false) {
							return false;
						}
					
			 	        var emptyItem =[{Keyid:"", ques :ImmAction, ans:" "}];     //immedaia
			 	    
				        jQuery("#AnalysisGrid").jqGrid('addRowData',j, emptyItem[0]);

				    	jQuery("#AnalysisGrid").jqGrid('setCell',j,"txtWwdtWhy",ImmAction);
				    	jQuery("#AnalysisGrid").jqGrid('setCell',j,"txtWwdtKeyid",""); 				
				    	jQuery('#txtWhy_'+(j)).val(" ");
				    	jQuery('#txtWhy_'+(j)).on(' click keydown', function(e){
						    e.stopPropagation();
						});
			            var k=j+1;
			            jQuery("#hdnVal").val(k);
				    }	
			 else
			      {
					 	lastRow = row;					 	
				       	var quest = jQuery('#txtWhy_'+lastRow).val();
				       	
				       	if(quest==null || quest==' '|| quest=='' || quest=='undefined')
						{
							alert("Enter the WhyWhy");
							return false;
						}
				       	else {
					       	var emptyItem =[{Keyid:"", ques :quest, ans:" "}];
					        jQuery("#AnalysisGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);					        	
					        var k = j+1;
					    	jQuery("#hdnVal").val(k);

					    	jQuery("#AnalysisGrid").jqGrid('setCell',lastRow+1,"txtWwdtWhy",quest); 				
					    	jQuery("#AnalysisGrid").jqGrid('setCell',lastRow+1,"txtWwdtKeyid","");
					    	jQuery('#txtWhy_'+(lastRow+1)).val(" ");
					    	
					    	jQuery('#txtWhy_'+(lastRow+1)).on(' click keydown', function(e){
							    e.stopPropagation();
							});
				       	}
			      }				
		
	  }


	function checkMandatoryValidation() { 
	 	
		var area = jQuery("#txtWwmsArea").val();
		var problem = jQuery("#txtWwmsProblem").val();
		var date = jQuery('#dteWwmsDate').datebox('getValue');
		var attendby = jQuery('#cmbWwmsProblemattendby').combobox('getValue');
		var yydoneby = jQuery('#cmbWwmsWhywhydoneby').combobox('getValue');
		var reportdate = jQuery('#dteWwmsReportdate').datebox('getValue');
		
		if(area==null || area=='' || problem==null || problem=='' 
				|| date==null || date=='' || attendby==null || attendby==''
					|| yydoneby==null || yydoneby=='' || reportdate=='')
		{
			alert("Enter Mandatory Fileds");
			return false;
		}
	}
		
	function actionFormatterDel(id, options, rowObject)     //delete
	{
		
		var id = options.rowId;
		var columnName = options.colModel.name;
		var columnNo = options.pos;
		var keyid = rowObject[0];
		var gid=options.gid;
	
				return '<input id="btnDelete_'+id+'" class="grdButton"  name="btnDelete" style="height:20px;height:16px\9;text-align:center;" value=""  type="button"  onclick=removeRecord("'+keyid+'","'+id+'"); style="text-align:left"/>';

	}

	function removeRecord(keyid,id){
		var r=confirm("Do You Want To Delete Row?");
		
		if(keyid!=null && keyid!='undefined' && keyid!=""){
			
			if (r==true)
			{
				processAjaxCalls("WhyWhy_delete.why", "keyid="+keyid, 'remove_successCallBack','remove_errorCallBack');
				//jQuery("#AnalysisGrid").delRowData(id);
				var row  = jQuery("#AnalysisGrid").jqGrid('getDataIDs');
				if(row.length>0)
					enableCounterMeasure();
				else
					disableCounterMeasure();
					
				return true;
			}
			else 
				return false;
		}
		else{
			
			if (r==true){
				jQuery("#AnalysisGrid").delRowData(id);
				jQuery("#txtWhy_"+id).remove();
			}
			else
				 return false;
		} 
			
	}
	
	function remove_successCallBack(result)
	{
		var masterkeyid=jQuery("#txtWwmsKeyid").val();
		processGridnew("whywhyanalysisgrid_input.why","q=2&masterkeyid="+masterkeyid,"AnalysisGrid","pagerAnalysis","","","","yyGrid_loadcomplete"); //TTTT  detail
		//alert(result.successData);
		
	}


	 /* function dteWwmsDate_onSelect(date){		//date	
			
		 	var currentDate = getServerDateTime();
			var refdocdate = jQuery('#txtRefdocdate').val();
			
			//alert('refdocdate'+refdocdate);
			if( date > currentDate)
			{					
				jQuery('#dteWwmsDate').datebox('clear');
				showValidationErrorMsg('dteWwmsDate','Should Not Exceed Current Date');
			}
			else {
				//alert(date < convertStringToDate(refdocdate));
				if( date < convertStringToDate(refdocdate))
				{					
					jQuery('#dteWwmsDate').datebox('clear');
					showValidationErrorMsg('dteWwmsDate','Should Not Less than Detected Date');
				}
				else {
					clearValidationErrorMsg('dteWwmsDate');
				}
			}
			
		} */
		 function formatDate(date){
			let newDate = new Date(date);
				   

			const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

			// Format DD-MMM-YYYY
		    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
						            months[newDate.getMonth()] + '-' +
						            newDate.getFullYear();
			return formatted;
		 }
		function dteWwmsDate_onSelect(date){	
			date = formatDate(date);
	        var currentDate = getServerDateTime();
	        var refdocdate = jQuery('#txtRefdocdate').val();
	        
	        var fifteenDaysAgo = new Date(currentDate);
	        fifteenDaysAgo.setDate(fifteenDaysAgo.getDate() - 15);
	        fifteenDaysAgo.setHours(0,0,0,0);
	        
	        var selected = new Date(date);
	        selected.setHours(0,0,0,0);
	        
	        if(selected > currentDate) {					
	            jQuery('#dteWwmsDate').datebox('clear');
	            setTimeout(function(){ fillWithCurrentDate('dteWwmsDate');}, 50);
	            showValidationErrorMsg('dteWwmsDate','Should Not Exceed Current Date');
	        }
	        else if(selected < fifteenDaysAgo) {
	            alert('You cannot save the Date before 15 days');
	            setTimeout(function(){
	                // Get current date in exact same format formatDateBox uses: d-MMM-yyyy
	                var now = new Date(currentDate);
	                var dd = now.getDate(); // no padStart - matches original format "4-May-2026"
	                var mm = ['Jan','Feb','Mar','Apr','May','Jun',
	                          'Jul','Aug','Sep','Oct','Nov','Dec'][now.getMonth()];
	                var yyyy = now.getFullYear();
	                var resetVal = dd + '-' + mm + '-' + yyyy;
	                
	                // Target the actual visible input EasyUI creates
	                jQuery('#dteWwmsDate').datebox('setValue', resetVal);
	                jQuery('#dteWwmsDate').textbox('setValue', resetVal);
	            }, 50);
	        }
	        else {
	            if(date < convertStringToDate(refdocdate)) {					
	                jQuery('#dteWwmsDate').datebox('clear');
	                showValidationErrorMsg('dteWwmsDate','Should Not Less than Detected Date');
	            }
	            else {
	                clearValidationErrorMsg('dteWwmsDate');
	            }
	        }
	    }
	 
	
	/*  function dteWwmsReportdate_onSelect(date){		//date	

		 	var yydate = jQuery('#dteWwmsDate').datebox('getValue');
		 	var currentDate = getServerDateTime();
		 	
			if( date > currentDate)
			{					
				jQuery('#dteWwmsReportdate').datebox('clear');
				showValidationErrorMsg('dteWwmsReportdate','Should Not Exceed Current Date');
			}			
			else if( date > convertStringToDate(yydate))
			{					
				jQuery('#dteWwmsReportdate').datebox('clear');
				showValidationErrorMsg('dteWwmsReportdate','Should  Less than or Equal to Entry Date');
			}
			else {
				clearValidationErrorMsg('dteWwmsReportdate');
			}
		} */
	 
	 function dteWwmsReportdate_onSelect(date){		
	        var yydate = jQuery('#dteWwmsDate').datebox('getValue');
	        var currentDate = getServerDateTime();
	        
	        var fifteenDaysAgo = new Date(currentDate);
	        fifteenDaysAgo.setDate(fifteenDaysAgo.getDate() - 15);
	        fifteenDaysAgo.setHours(0,0,0,0);
	        
	        var selected = new Date(date);
	        selected.setHours(0,0,0,0);
	        
	        if(selected > currentDate) {					
	            jQuery('#dteWwmsReportdate').datebox('clear');
	            setTimeout(function(){ fillWithCurrentDate('dteWwmsReportdate');}, 50);
	            showValidationErrorMsg('dteWwmsReportdate','Should Not Exceed Current Date');
	        }
	        else if(selected < fifteenDaysAgo) {
	            alert('You cannot save the Date before 15 days');
	            setTimeout(function(){
	                var now = new Date(currentDate);
	                var dd = now.getDate();
	                var mm = ['Jan','Feb','Mar','Apr','May','Jun',
	                          'Jul','Aug','Sep','Oct','Nov','Dec'][now.getMonth()];
	                var yyyy = now.getFullYear();
	                var resetVal = dd + '-' + mm + '-' + yyyy;
	                
	                jQuery('#dteWwmsReportdate').datebox('setValue', resetVal);
	                jQuery('#dteWwmsReportdate').textbox('setValue', resetVal);
	            }, 50);
	        }
	        else if(date > convertStringToDate(yydate)) {					
	            jQuery('#dteWwmsReportdate').datebox('clear');
	        }
	        else {
	            clearValidationErrorMsg('dteWwmsReportdate');
	        }
	    }

	
	 
	
	
function rootCauseOnLoad()
		{
			var formType = jQuery('#hdnformType').val();
			//mano
			var mode = jQuery("#mode").val();
    var formMode1 = jQuery("#hdnMode2").val();
    //mano end
			//alert("jQuery('#hdnformType').val()"+formType);
			if(screen.width >=1250)
			{
				
				if(formType != null && formType != ' ' && formType != '')
				{
					if(formType == 'SHE')
					{
						jQuery( "#rootPillarGrid" ).setGridWidth(428);
						jQuery( "#PillarGrid" ).setGridWidth(428);
						jQuery( "#PillarGrid" ).setGridHeight(85);
					}
					else
					{	
						jQuery( "#rootPillarGrid" ).setGridWidth(270);
						jQuery( "#PillarGrid" ).setGridWidth(388);
					}
				}
				else
				{	
					jQuery( "#rootPillarGrid" ).setGridWidth(270);
					jQuery( "#PillarGrid" ).setGridWidth(388);
				}	
			}
			else
			{
				if(formType == 'SHE')
				{
					jQuery( "#rootPillarGrid" ).setGridWidth(428);
					jQuery( "#PillarGrid" ).setGridWidth(400);
					jQuery( "#PillarGrid" ).setGridHeight(85);
				}
				
			}
			

			
			selectedRC();
			if(mode != "View" && mode != "view" && formMode1 != "View" && formMode1 != "view") {
			jQuery("#rootPillarGrid").setGridParam({
				onCellSelect:function(id,cellidx,cellvalue) {  
				
						if(cellidx == 1)
							{						
								if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
								{
									var rcId= jQuery("#rootPillarGrid").jqGrid('getCell',id,cellidx-1);	
									var rc= jQuery("#rootPillarGrid").jqGrid('getCell',id,cellidx+1);	
									
									jQuery("#hdnWwmsRootcauseid").val(rcId);
									if(formType != 'SHE')
										jQuery("#hdnWwmsRootcause").val(rc);
									jQuery("#rc_"+id).html("&#10003");	
									jQuery("#hdnWwmsRootcauseid").val(rcId);
									disableRootCause(id);									
								}
								else
								{
									jQuery("#rc_"+id).html("");
									jQuery("#hdnWwmsRootcauseid").val();
									jQuery("#hdnWwmsRootcauseid").val("");
									if(formType != 'SHE')
										jQuery("#hdnWwmsRootcause").val("");
								}
								
							}
					/*	else if(cellidx == 3)
							{
								if(jQuery("#am_"+id).html() == '' || jQuery("#am_"+id).html() == null)
								{
									if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
										alert("Select Root Cause");
									else
									{
										jQuery("#am_"+id).html("&#10003");
										jQuery("#pm_"+id).html("");
										jQuery("#et_"+id).html("");
										jQuery("#ci_"+id).html("");	
										jQuery("#hdnwwmsPillarmode").val("am");					
									}					
										
								}
								else
								{
									jQuery("#am_"+id).html("");
									jQuery("#hdnwwmsPillarmode").val("");
								}
							}
						else if(cellidx == 4)
							{
								if(jQuery("#pm_"+id).html() == '' || jQuery("#pm_"+id).html() == null)
								{
									if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
										alert("Select Root Cause");
									else
									{
										jQuery("#am_"+id).html("");							
										jQuery("#et_"+id).html("");
										jQuery("#ci_"+id).html("");	
										jQuery("#pm_"+id).html("&#10003");
										jQuery("#hdnwwmsPillarmode").val("pm");	
									}
								}
								else
								{
									jQuery("#pm_"+id).html("");
									jQuery("#hdnwwmsPillarmode").val("");
								}
							}
						else if(cellidx == 5)
							{
								if(jQuery("#ci_"+id).html() == '' || jQuery("#ci_"+id).html() == null)
								{
									if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
										alert("Select Root Cause");
									else
									{
										if(jQuery("#hdnWwmsRootcause").val() == 'POOR BASIC CONDITION')
										{}
										else
										{
											jQuery("#am_"+id).html("");
											jQuery("#pm_"+id).html("");
											jQuery("#et_"+id).html("");							
											jQuery("#ci_"+id).html("&#10003");
											jQuery("#hdnwwmsPillarmode").val("ci");
										}	
									}
								}
								else
								{
									jQuery("#ci_"+id).html("");
									jQuery("#hdnwwmsPillarmode").val("");
								}
							}
						else if(cellidx == 6)
							{
								if(jQuery("#et_"+id).html() == '' || jQuery("#et_"+id).html() == null)
								{
									if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
										alert("Select Root Cause");
									else
									{
										if(jQuery("#hdnWwmsRootcause").val() == 'POOR BASIC CONDITION')
										{}
										else
										{
											jQuery("#am_"+id).html("");
											jQuery("#pm_"+id).html("");													
											jQuery("#ci_"+id).html("");
											jQuery("#et_"+id).html("&#10003");
											jQuery("#hdnwwmsPillarmode").val("et");	
										}
									}
								}
								else
								{
									jQuery("#et_"+id).html("");
									jQuery("#hdnwwmsPillarmode").val("");
								}
							}*/
				}
			});
		}
		}
		function disableRootCause(id)
		{
			for(var i=1;i<=5;i++)
			{
				if(i != id)
				{
				  jQuery("#rc_"+i).html("");
				  jQuery("#am_"+i).html("");
				  jQuery("#pm_"+i).html("");
				  jQuery("#ci_"+i).html("");
				  jQuery("#et_"+i).html("");
				}
			}
		}
		function selectedRC()
		{
			//alert(1);
			var rowID =  jQuery("#rootPillarGrid").jqGrid('getRowData');
			var selectedRC = null;
			
			for(var i=1;i<=rowID.length;i++)
			{
			
				if(jQuery("#rootPillarGrid").jqGrid('getCell',i,'txtWwmsrootcauseid') == jQuery("#hdnWwmsRootcauseid").val())
				{
					selectedRC = i;
					jQuery("#rc_"+selectedRC).html("&#10003");	
				}
			}
			
			/*if(jQuery("#hdnIsJh").val() == 'Y')
			{
				jQuery("#am_"+selectedRC).html("&#10003");	
				jQuery("#hdnwwmsPillarmode").val("am");
			}
			if(jQuery("#hdnIsPM").val() == 'Y')
			{
				jQuery("#pm_"+selectedRC).html("&#10003");
				jQuery("#hdnwwmsPillarmode").val("am");
			}
			if(jQuery("#hdnIsCI").val() == 'Y')
			{
				jQuery("#ci_"+selectedRC).html("&#10003");
				jQuery("#hdnwwmsPillarmode").val("ci");
			}
			if(jQuery("#hdnIsET").val() == 'Y')
			{
				jQuery("#et_"+selectedRC).html("&#10003");
				jQuery("#hdnwwmsPillarmode").val("et");
			}*/
		}

		
		function actionFormatterSave(cellvalue, options, rowObject)     ////Tom
		{
			 var rowId = options.rowId;	
			 var formatStr  = '<input id="pillar_'+rowId+'"' ;
				 formatStr  += 'type="button" class="easyui-button" style="height:20px;height:16px\9;" value="..." onclick="selectPillar(\''+rowId + '\');">';// tick 
				 formatStr  +=  '</span>';
			return formatStr;
		}
		function selectPillar(id)
		{
			var flag = jQuery('#hdnCounterMsrFlag').val();
			var jh = "am";
			if(flag != null && flag != ' ' && flag != '')
			{
				if(flag == "YES")
					jh = "jh";
			}

			if(jQuery('#hdnformType').val() == "CC" || jQuery('#hdnformType').val() == "IMT")
			{
				if(id == '1')
					jQuery("#hdnwwmsPillarmode").val("am");
				else if(id == '2')
					jQuery("#hdnwwmsPillarmode").val(jh);
				else if(id == '3')
					jQuery("#hdnwwmsPillarmode").val("ci");
				else if(id == '4')
					jQuery("#hdnwwmsPillarmode").val("et");
				else if(id == '5')
					jQuery("#hdnwwmsPillarmode").val("am");
				
			}
			else if(jQuery('#hdnformType').val() == "SHE" || jQuery('#hdnformType').val() == "DOCK")
			{
				if(id == '1')
					jQuery("#hdnwwmsPillarmode").val("ci");
				else if(id == '2')
					jQuery("#hdnwwmsPillarmode").val("oj");
				else if(id == '3')
					jQuery("#hdnwwmsPillarmode").val("et");
				else if(id == '4')
				{
				  if(jQuery('#hdnformType').val() == "DOCK")
					jQuery("#hdnwwmsPillarmode").val("so");
				  else
					jQuery("#hdnwwmsPillarmode").val("am");
				}		
			}
			else
			{
				if(id == '1')
					jQuery("#hdnwwmsPillarmode").val(jh);
				else if(id == '2')
					jQuery("#hdnwwmsPillarmode").val("ci");
				else if(id == '3')
					jQuery("#hdnwwmsPillarmode").val("et");
				else if(id == '4')
					jQuery("#hdnwwmsPillarmode").val("pm");
				else if(id == '5')
					jQuery("#hdnwwmsPillarmode").val("trn");
			}
			//if(id != '1')
			if(jQuery("#hdnwwmsPillarmode").val() != "am" && jQuery("#hdnwwmsPillarmode").val() != "oj" && jQuery("#hdnwwmsPillarmode").val() != "so")
				saveForm('frmWhywhyAnalysis','whywhyanalysismodify_save.why');
			else
			{
				if(jQuery("#hdnwwmsPillarmode").val() == "oj")
					 openPopUp('OJT');	
				else if(jQuery("#hdnwwmsPillarmode").val() == "so")
					 openPopUp('SOP');	
			}
			//var rowData = jQuery("#list").jqGrid('getRowData',id);
			//jQuery("#hdnwwmsPillarmode").val("am");
		}
		/*function frmWhywhyAnalysis_successsCallback(result)
		{
			alert(111);
			var openForm = result.OpenForm;	
			var msg = "Do You Want to open the Counter Measure?";
			//alert("openForm:"+openForm);
			 if( openForm != null && openForm == "Design")
			 {
				 var persistentData = result.persistentData;
				 var forwardData = result.forwardData;	
				 if(confirm(msg) == true)
				 {						  
					navigateToNextForm('kaizen_input.kaizen?closeOnSave=true&','Kaizen',forwardData,persistentData );
				 }
				 else
					 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "TRN")
			 {
				 var persistentData = result.persistentData;
				 var forwardData = result.forwardData;	
				 if(confirm(msg) == true)
				 {	
					 navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );				
				 }	
				 else
					 navigateToPrevForm();
				 
			 }
			 else if( openForm != null && openForm == "ET")
			 {
				 var persistentData = result.persistentData;
				 var forwardData = result.forwardData;	
				 if(confirm(msg) == true)
				 {	
					 navigateToNextForm('WhyWhyTrainingList_input.why','One Point Lesson',forwardData,persistentData );
					// navigateToNextForm('modify_input.opl','One Point Lesson',forwardData,persistentData );	
				 }	
				 else
					 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "JH")
			 {
				 var persistentData = result.persistentData;
				 var forwardData = result.forwardData;	
				 if(confirm(msg) == true)
				 {				 
					navigateToNextForm('jhClit_input.jhclit','JH Clit Standard',forwardData,persistentData );
				 }
				 else
					 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "PM")
			 {
				 var persistentData = result.persistentData;
				 var forwardData = result.forwardData;	
				 if(confirm(msg) == true)
				 {	
					navigateToNextForm('preventive_input.prv','PM Standards',forwardData,persistentData );
				 }
				 else
					 navigateToPrevForm();		 
			 }
			 else if( openForm != null && openForm == "OJ")
			 {
				 closePopUp();
				 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "SO")
			 {
				 closePopUp();
				 navigateToPrevForm();
			 }	 
			 else{
				 //navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );
				 navigateToPrevForm();
				 
			 }
			
		}*/


		function openPopUp(title)
		{
			jQuery('#mstfrm_div').addClass('popup-mask');
			//jQuery('#mstfrm_div').css('z-index',100);
			jQuery('#mstfrm_div').show();
			jQuery('#dlgOJTSOP').addClass('custom-popup');			
		    jQuery( '#dlgOJTSOP' ).show();    
		    jQuery( '#dlgOJTSOP' ).css('border','1px solid #2364CA');
		   jQuery( '#dlgOJTSOP' ).css('margin-top',400);     
		    jQuery( '#dlgOJTSOP' ).css('width',350);
			jQuery( '#dlgOJTSOP' ).css('height',300);	
			jQuery( '#lblOJTSOP' ).html(title);	
			if(title == 'OJT')
				 jQuery( '#sopRemarks' ).css('display','none');
			else if(title == 'SOP')
				 jQuery( '#ojtRemarks' ).css('display','none');
				
		}
		function closePopUp()
		{	
			 jQuery('#dlgOJTSOP').hide();
			 jQuery('#mstfrm_div').removeClass('popup-mask');	
			 jQuery('#dlgOJTSOP').removeClass('custom-popup');	
		}
		

		function frmWhyWhyAnalysis_exceptionCallback(err)
		{
			alert(err.tpmException);
			navigateToPrevForm();
		}
		
		/* function MailsendsuccessCallBack(){
			alert(" Mail Sent Successfully. ");
		} */
		function frmWhyWhyAnalysis_successsCallback(result)
		{
			//return false;
			
			//alert(Object.keys(result));
			//var openForm = result.OpenForm;txtWwmsTimespent
			    var yykeyid = result.successData.keyId;				
				var problem=result.successData.problem;	
				var spentTime=result.successData.timeSpent;	
				var area=result.successData.area; 	
				var frmMode=result.successData.formMode;
				var formMode=jQuery("#hdnMode").val();
				var flid=result.successData.flid;

			   // alert(formMode +" .... "+spentTime +" spentTime ... "+frmMode);
 
			/* if(yykeyid!=null || yykeyid!=" "){
	
			processAjaxCalls("whywhyAnalysisMailSend.why?&whywhyId="+yykeyid+"&problem="+problem+"&spentTime="+spentTime+"&area="+area,"","MailsendsuccessCallBack","errorCallBack");

			} */
			
			

			var filemanager =result.successData.filemanager;
			
			 
		   var Types=result.Typenavigate;
			var disableYY = jQuery("hdnClickCounterMeasure").val();
			//alert(disableYY);
			var keyId = result.successData.keyId;
			setFieldValue("txtWwmsKeyid",keyId);
			var openForm = result.counterMeasure;
			var msg = "Do You Want to open the Counter Measure?";
			
			var yykeyid = result.successData.keyId;
			
			var machine = jQuery("#frmWhyWhyAnalysis input[id='machine']").val();
			var persistentData = result.persistentData;
			var forwardData = result.forwardData;
			var forwardData1 = result.forwardData.cellId;
			var proposedID = jQuery("#hdnCounterMeasureId").val();
			//alert('proposedID:'+proposedID);
			//alert('form:'+openForm);
			
			if(filemanager==true ){
				closePopUpDialoge("loadWHYModify");
				 var keyid = result.successData.wwmsKeyId;
				 jQuery('#txtWwmsKeyid').val(keyid);
				 //alert(keyid);
				 fileManagerPopUp(keyid,"YY","","","");
				
				return ;
			 }  
			else if( openForm != null && openForm == "Design")
			 {
				 if(confirm(msg) == true)
				 {	
					 closePopUpDialoge("loadWHYModify");	
					navigateToNextForm('kaizen_input.kaizen?closeOnSave=true&refDocId='+yykeyid+"&refdocType=YY&kznKeyid="+proposedID,'Kaizen',forwardData,persistentData );
				 }
				 else
					 {	
					 closePopUpDialoge("loadWHYModify");
					 navigateToPrevForm();
					 }
			 }
			 else if( openForm != null && openForm == "TRN")
			 {
				 if(confirm(msg))
				 {	
					 closePopUpDialoge("loadWHYModify");
					 navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );				
				 }	
				 else{	
					 closePopUpDialoge("loadWHYModify");
					 navigateToPrevForm();
				 }
				 
					 /*var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
					 //navigateToNextForm('WhyWhyTrainingList_input.why?flid='+flid,'Training Program List');
				 if(confirm(msg))
					 navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );
						
					else
						navigateToNextForm("create_input.opl?flid="+flid,"One Point Lesson");*/
				 
				 
			 }
			 else if( openForm != null && openForm == "OPL")
			 {
					closePopUpDialoge("loadWHYModify");
				 if(confirm(msg) == true)
				 {	
					 //oplKeyId
					 //navigateToNextForm('WhyWhyTrainingList_input.why','One Point Lesson',forwardData,persistentData );
					 
					 var masterkeyid=jQuery("#txtWwmsKeyid").val();

					 var proposedID = jQuery("#hdnCounterMeasureId").val();
					 
				     if(masterkeyid.trim().length<=0 || proposedID.trim().length<=0)
				    	 {
				    		closePopUpDialoge("loadWHYModify");
					   //navigateToNextForm("create_input.opl?refDocNo="+yykeyid+"&frm=Create&refDocType=YY&flid="+flid+"&mod=modify&oplKeyId="+proposedID+"&","One Point Lesson",forwardData,persistentData);
					    var datastr="refDocNo="+yykeyid+"&frm=Create&refDocType=YY&flid="+flid+"&mod=modify&oplKeyId="+encodeURIComponent(proposedID)+"&";
					    LoadPopUp("OPL","create_input.opl?"+datastr, true,"97%","91%","5%","13px", "","One Point Lesson",false,true);

					    
					    // navigateToNextForm('modify_input.opl','One Point Lesson',forwardData,persistentData );
				    	 } else {
						processAjaxCalls("whywhyanalysisgrid_recall.why?&frm=Opl&keyid="+masterkeyid,"","oplrecallsuccessCallBack","errorCallBack");
					 }
				 }	
				 else
					 navigateToPrevForm();
			 } 
			 else if( openForm != null && openForm == "CLTI")
			 {				    		

				 if(confirm(msg) == true)
				 {	 	closePopUpDialoge("loadWHYModify");			 
					navigateToNextForm("jhClit_input.jhclit?yyId="+yykeyid+"&refdocType=YY&machineID="+machine+"&flid="+flid,'Creation',forwardData,persistentData );
				/*  var dss="yyId="+yykeyid+"&refdocType=YY&machineID="+machine+"&flid="+flid
					LoadPopUp("CLTI",'jhClit_input.jhclit?'+dss, true,"97%","91%","5%","13px", "","CLTI Creation",false,false);
 */
				 }
				 else
					 { 	closePopUpDialoge("loadWHYModify");
					 navigateToPrevForm();
					 }
			 }
			 else if( openForm != null && openForm == "PM")
			 {
				 	
				 if(confirm(msg) == true)
				 {	
						closePopUpDialoge("loadWHYModify");
					navigateToNextForm("preventive_input.prv?&refDocId="+yykeyid+"&refdocType=YY",'PM Standards',forwardData,persistentData );
				 }
				 else{
						closePopUpDialoge("loadWHYModify");
					 navigateToPrevForm();	
				 }
			 }
			 else if( openForm != null && openForm == "CM")
			 {
					closePopUpDialoge("loadWHYModify");
				 navigateToNextForm("PMReport_input.prv?refDocId="+yykeyid+"&refdocType=YY","Condition Monitoring",forwardData,persistentData);
				//closePopUp();
				 //navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "KAIZEN")
			 {
					closePopUpDialoge("loadWHYModify");
				// var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
				 var masterkeyid=jQuery("#txtWwmsKeyid").val();
				 var proposedID = jQuery("#hdnCounterMeasureId").val();
				// alert("proposedID:"+proposedID);
				
				 if(masterkeyid.trim().length<=0 || proposedID.trim().length<=0){
			    	  var dss = '?closeOnSave=true&refDocNo='+yykeyid+"&flid="+flid+"&refDocType=YY&kznKeyid="+encodeURIComponent(proposedID)+"&";
						LoadPopUp("Kaizen",'KaizenBankSuggestion_input.kznbnk?'+dss, true,"97%","91%","5%","13px", "","Kaizen",false,true);
 
			    	 //navigateToNextForm('KaizenBankSuggestion_input.kznbnk'+dss,'Kaizen',forwardData,persistentData );
			   
				 }
				else {
					
					processAjaxCalls("whywhyanalysisgrid_recall.why?&frm=Kzn&keyid="+masterkeyid,"","kznrecallsuccessCallBack","errorCallBack");
				 }
				 
			     //navigateToNextForm("create_input.opl?refDocNo="+yykeyid+"&frm=Create&refDocType=YY&flid="+flid+"&mod=modify&oplKeyId="+proposedID+"&","One Point Lesson",forwardData,persistentData);
			     
				 //navigateToNextForm("KaizenBankSuggestion_input.kznbnk?&refDocNo="+yykeyid+"&refDocType=YY&Keyid="+proposedID+"&","Kaizen",forwardData,persistentData);
			 }	 
			 
			 else if( openForm != null && openForm == "VISUAL SOP")
			 {
					closePopUpDialoge("loadWHYModify");
			 	navigateToNextForm("VisualSopDetailUpdate_input.VisualSop?refDocId="+yykeyid+"&refdocType=YY","SOP",forwardData,persistentData);
			
			 
			 }
			 else if(openForm !=null && openForm == "Action plan")
			 {
				// var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
				
				 var masterkeyid=jQuery("#txtWwmsKeyid").val();
				 var proposedID = jQuery("#hdnCounterMeasureId").val();	
				// alert (proposedID +"    proposedID ")
			      var dss='?actPlanRefMasId='+yykeyid+"&flid="+flid+"&actPlanRefDocType=YY&actPlanKeyId="+encodeURIComponent(proposedID)+"&actPlanMainTask="+encodeURIComponent(problem)+"&";
					//alert(dss +" dss ");
			      LoadPopUp("ActionPlan",'ActionPlan_input.api'+dss, true,"97%","91%","5%","13px", "","Action Plan",false,false);


			 }
			/*  else if(Types!="Typenavigate"){
		    	setTimeout(function(){
		    		navigateToPrevForm();
		    	},1000)		    	
		    }  */
			 else{
				 //navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );
				 if(jQuery('#hdnrefDoctype').val()=="WM"){
					//alert(1); 
				 }
				  var type = jQuery("#type").val();
					//alert("type:"+type)
					if(type=="whywhymodify")
						{
						//navigateToPrevForm();
						closePopUpDialoge("loadWHYModify");
						}
					else{
					var masterkeyid=jQuery("#txtWwmsKeyid").val();
					refreshButtonPress();
					//processGridnew("yyprobAttbyLink_input.why","q=2&masterkeyid="+masterkeyid,"donebyGrid2","pagerDoneby2","","","","yyDonebyGrid2_loadcomplete"); //TTTT  detail
					//processGridnew("yyDonebyLink_input.why","q=2&masterkeyid="+masterkeyid,"donebyGrid","pagerDoneby","","","","yyDonebyGrid_loadcomplete"); //TTTT  detail
					}
				 }
			jQuery("#achievementgrid").trigger("reloadGrid");
			//closePopUpDialoge("divWhyModify");

			}  
		
		function frmWhyWhyAnalysiscmbWwmsMachineid_onSelect(record)
		{
			//fillComboBox("frmWhyWhyAnalysis","cmbWwmsSparesId","spareCombo.commonFilter");
			reloadCombo("frmWhyWhyAnalysis","cmbWwmsSparesId","spareCombo.commonFilter?machId="+record.id);
			//alert(123);
			//alert(record.id);
			//loadFunctionalLocation("whywhyfunlocation","functionalLoc.why","whywhyfunlocationValues","frmWhyWhyAnalysis","&machId="+record.id);
		}		
</script>
<form id="frmWhyWhyAnalysis" name="frmWhyWhyAnalysis">
<!-- <div class="" style="height:780px;" align="center"> -->
<div id="wrapper" style="width:100%;">
<div class="main-cntborder" style="height: 780px;width:1300px;">

<!-- style="width:80%;width:984px\9;" -->
	<table  style="width:1100px;" >
		<tr>
			<td colspan="3">
				<div  id="frmWhyWhyAnalysisFuntKeyIds"  >
						<div style="padding-right: 20px;">
							<input type="hidden" id="factory" name="factory" value=" "  ></input>			
							<input type="hidden" id="section" name="txtWwmsSectionid" value=" "  ></input>
							<input type="hidden" id="cell" name="txtWwmsCellid" value=" "  ></input>
							<input type="hidden" id="machine" name="txtWwmsMachineid" value=" "  ></input>
							<input type="hidden" id="flid" name="txtWwmsFlid" value="${requestScope.newBdmTlWhywhymst.wwmsFlid}"/></input>
							<input type="hidden" id="elementId" name="txtelementid" value="${requestScope.newBdmTlWhywhymst.elementid}"/></input>
						</div>
						<div id="whywhyfunlocation" class="padding" style="width:99%;"></div>
				</div>
			</td>
			</tr>
			<!--<tr>
			<td>
				<div style="padding-top:20px;">
					<input type="button" id="btnlegend" name="btnlegend" class="easyui-button" clear="false"  style=" width : 70px;height:25px;" value="Legend" />
				</div>
			</td>
		</tr>
		--><tr>
			<td valign="top" style="padding-top:10px;" >
				<div><label class="mandatory-lbl"  >JH</label></div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-text" style=" width :255px;text-transform: uppercase;" id="txtWwmsArea" name="txtWwmsArea" readonly="readonly" onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.newBdmTlWhywhymst.wwmsArea}"/>
				</div>	
		 	</td>
		 	<td valign="top"   style="padding-left:10px;padding-top:10px;">
				 <div><label >Equipment</label></div>
				 <div class="easyui-paddingbfpx" >
				 	<input  class="easyui-combobox" style=" width :255px;" id="cmbWwmsMachineid" name="cmbWwmsMachineid"  value="${requestScope.newBdmTlWhywhymst.wwmsMachineid}"/>
				 	
				 </div>	
		 	</td>
		 	
		 	<td valign="top"   style="padding-left:10px;padding-top:-10px;">
				 <div class="easyui-paddingbfpx"><label class="mandatory-lbl" >Trade</label> <label class="mandatory-lbl" style="padding-left:105px;">Date</label><label style="padding-left:125px;">Spares</label></div>
				 <div class="easyui-paddingbfpx" >
				 	<input  class="easyui-combobox" style=" width :130px;" id="cmbWwmsTradeId" name="cmbWwmsTradeId"  value="${requestScope.newBdmTlWhywhymst.wwmsTradeId}"/>
				 	<span style="padding-left:10px;">
				 	<input id="dteWwmsDate" name="dteWwmsDate" class="easyui-text" clear="false"  style=" width : 80px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsDate}"/>
					</span>
					<span style="padding-left:10px;">
					<input id="chkWwmsSparesreplaced" name="chkWwmsSparesreplaced"  type="checkbox" value="N" ${ requestScope.newBdmTlWhywhymst.wwmsSparesreplaced == 'Y' ? ' checked':''}" />
					</span>
<%-- 				
	<input id="txtVersion" name="txtVersion" class="easyui-text" clear="false"  style=" width : 70px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsSparesreplaced}" /></span> --%>

					<span>
					<input  class="easyui-combobox" style=" width :170px;" id="cmbWwmsSparesId" name="cmbWwmsSparesId" value="${requestScope.newBdmTlWhywhymst.wwmsSparesId}"/></span>
				
				 </div>	
		 	
				
				<%-- <div>
					<input id="dteWwmsDate" name="dteWwmsDate" class="easyui-text" clear="false"  style=" width : 80px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsDate}"/>
					<span style="padding-left:10px;">
					<input id="chkWwmsSparesreplaced" name="chkWwmsSparesreplaced"  type="checkbox" value="N" <c:out value = "${ requestScope.newBdmTlWhywhymst.wwmsSparesreplaced == 'Y' ? ' checked':''}"/> />
					<input id="txtVersion" name="txtVersion" class="easyui-text" clear="false"  style=" width : 70px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsSparesreplaced}" /></span>

					</span>	<span>
					<input  class="easyui-combobox" style=" width :150px;" id="cmbWwmsSparesId" name="cmbWwmsSparesId" value="${requestScope.newBdmTlWhywhymst.wwmsSparesId}"/></span>
				</div> --%>
					<table>
					<tr>
					<td><span id="err_dteWwmsDate" class="tpm-errormsg"></span></td>
					<td style="padding-left:32px;"><span id="err_cmbWwmsSparesId" class="tpm-errormsg"></span></td>
					</tr>
					</table>
			</td>
			

			
		 	<td style="padding-left:0px;padding-top:5px;">
					<span style="padding-left:0px;">
						<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="Excel View" style="height: 25px; width : 102px;"/>			
					</span>
					
<!--				<div class="easyui-paddingbfpx"><label>Format No:</label><label style="padding-left:25px;">Rev:</label><label style="padding-left:56px;">Ver:</label></div>-->
				<div>
<!--					<input id="txtFormat" name="txtFormat" class="easyui-text" clear="false"  style=" width : 70px;height:21px;" value="" />-->
<!--					<span style="padding-left:10px;">-->
<!--					<input id="txtRevision" name="txtRevision" class="easyui-text" clear="false"  style=" width : 70px;height:21px;" value="" /></span>-->
<!--					<span style="padding-left:10px;">-->
<!--					<input id="txtVersion" name="txtVersion" class="easyui-text" clear="false"  style=" width : 70px;height:21px;" value="" /></span>-->
				</div>
			</td>
			<td style="padding-left:10px;padding-top:5px;">
			 <input type="button" id="btnlegend" name="btnlegend" class="easyui-button" clear="false"  style=" width : 70px;height:25px;" value="Legend" />
			</td>
			<td>
			<div style="position: relative;"><span id="WhyWhyAnalysisFilemgr"
			style="position: absolute; right: 130px; right: 70px\9; top: -60px;">

		</span></div></td>
		</tr>
		<tr>
			<td style="padding-top:-1px;">
			 	<div>
			 		<label class="mandatory-lbl">Problem/Observation</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" id="txtWwmsProblem" name="txtWwmsProblem" maxlength="495" style="width : 255px;height:40px;height:70px\9;text-transform: uppercase;" >${requestScope.newBdmTlWhywhymst.wwmsProblem}</textarea>
				</div>
		 	</td>
		 	<td style="padding-left:10px;padding-top:10px;" valign="top">
				<div >
					<label class="mandatory-lbl">Reported Date & Time </label>
				</div>
				<div class="easyui-paddingbfpx">
					<span>
							<input type="hidden" id="hdnWwmsReportdatetime" name="hdnWwmsReportdatetime" value="${requestScope.newBdmTlWhywhymst.wwmsReportdatetime}"/>
							<input id="dteWwmsReportdate"  name="dteWwmsReportdate"  class="easyui-datebox" value="${requestScope.newBdmTlWhywhymst.wwmsReportdatetime}"  style="width:180px;width:190px\9;/* height:21px; */"  />
					</span>
					<span class="spinner easyi-paddingbfpx">
							<input  id="spnNmrtOccurrencedatetime"  name="spnNmrtOccurrencedatetime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.time}"  style="width: 60px;" />
					</span>
				</div>
				<div>
				<span id="err_dteWwmsReportdate" class="tpm-errormsg" style="padding-left:0px;"></span>
				</div>
			</td>
			<td style="padding-left:10px;padding-top:5px;">
			 	<div>
			 		<label>Immediate Action</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" id="txtWwmsFinalaction" name="txtWwmsFinalaction" maxlength="495" onblur="setQuestionInGrid()" style="height:40px;height:70px\9;text-transform: uppercase; width : 425px;" >${requestScope.newBdmTlWhywhymst.wwmsFinalaction}</textarea>
				</div>
		 	</td>
		 	<td style="padding-left:0px;padding-top:5px;">
			 	<div>
			 		<label>COBD?</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					 <select class="easyui-text" id="cmbWwmsIscobd" name="cmbWwmsIscobd"  readonly    style="width: 80px;"  >
		<option value="N" ${requestScope.newBdmTlWhywhymst.wwmsIscobd == 'N' ? 'Selected' : ''}>No</option>
		<option value="Y" ${requestScope.newBdmTlWhywhymst.wwmsIscobd == 'Y' ? 'Selected' : ''}>Yes</option>
		
		
	</select> </div>
		 	</td>
		 	
		 	<td style="padding-left:0px;padding-top:5px;">
			 	<div>
			 		<label>Value (INR)</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-text"  style=" width :80px; " id="txtWwmsCobdvalue"  name="txtWwmsCobdvalue" readonly onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.newBdmTlWhywhymst.wwmsCobdvalue}"/>
				</div>
		 	</td>
		 	<td style="padding-left:10px;padding-top:5px;">
			 	<div>
			 		<label>Time (Hours)</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-text"  style=" width :80px;" id="txtWwmsCobdhours"  name="txtWwmsCobdhours" readonly  onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.newBdmTlWhywhymst.wwmsCobdhours}"/>
				</div>
		 	</td>
		 	<td style="padding-left:10px;padding-top:10px;">
			 	<div>
<!--			 		<label>Spares Used</label>-->
			 	</div>
<!--				<div class="easyui-paddingbfpx" >-->
<!--					<input  class="easyui-combobox" style=" width :150px;" id="cmbSparesused" name="cmbSparesused"  value=""/>-->
<!--				</div>-->
		 	</td>
		 	
		</tr>
		<tr>
			<td style="padding-top:1px;">
			 	<div>
			 		<label class="mandatory-lbl">Problem Attended By</label>
			 		<span style="padding-left:5px;">
            			<input type="checkbox" id="chkWwmsPabOthers" name="chkWwmsPabOthers"  />
						<label for="chkWwmsPabOthers">Others</label>
          				</span>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-combobox" style=" width : 195px;/*  height : 22px; */" id="cmbWwmsProblemattendby" name="cmbWwmsProblemattendby"  value="${requestScope.newBdmTlWhywhymst.wwmsProblemattendby}"/>
					<span>
		 				<input type="button" class="easyui-button" style="width:45px;" id="btnAddpab" name="btnAddpab" value="Add"/>
		 				</span>
				</div>
				
		 	</td>
		 	<td style="padding-left:10px;padding-top:10px;">
				<div><label class="mandatory-lbl" >Time Spent to correct the problem (Hrs) </label></div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-text"  style=" width :255px;" id="txtWwmsTimespent"  name="txtWwmsTimespent" onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.newBdmTlWhywhymst.wwmsTimespent}"/>
				</div>
				<span id=" " class="tpm-errormsg"></span>	
		 	</td>
		 	<td style="padding-left:10px;padding-top:1px;">
				<div>
					<span style="padding-left:0px;"><label  class="mandatory-lbl">Pillar</label></span>
					<span style="padding-left:80px;"><label>Product</label></span>
					<span style="padding-left:80px;"> <label class="mandatory-lbl" >Why Why Done by</label> </span>
				</div>
				<div class="easyui-paddingbfpx" style="width:480px;">
					<span>
						<input  class="easyui-combobox" style=" width : 110px; /* height : 22px; */" id="cmbWwmsPillarid" name="cmbWwmsPillarid"  value="${requestScope.newBdmTlWhywhymst.wwmsPillarid}"/>
					</span>
					<span>
						<input  class="easyui-combobox" style=" width : 90px; /* height : 22px; */" id="cmbWwmsProductid" name="cmbWwmsProductid" readonly="readonly" value="${requestScope.newBdmTlWhywhymst.wwmsProductid}"/>
					</span>
					<span>
						<input  class="easyui-text" style="width :170px;" id="cmbWwmsWhywhydoneby" name="cmbWwmsWhywhydoneby" onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" value="${requestScope.newBdmTlWhywhymst.wwmsWhywhydoneby}"/>
					</span>
					
					<span style="padding-left: 0px;top:-0px;"><input type="button" class="easyui-button" style="width:40px;" id="btnAddDoneby" name="btnAddDoneby" value="Add"/></span>
					
				</div>	
		 	</td>
		</tr>
		<tr>
		<td v>
		<span id="err_cmbWwmsProblemattendby" class="tpm-errormsg"></span>
		</td>
		<td>
		</td>
		<td>
		 <span id="err_cmbWwmsWhywhydoneby" class="tpm-errormsg"></span>
		</td>
		</tr>
	</table>


	<table style="width: 90%;"> <tr>
	<td  style="width: 48%;">
	<div id="divAnalys" style="width: 45%;">
	<table>
		<tr>
			<td colspan="3">
				<div class="sub-header" style="text-align: left;float:left;width:490px; width:450px\9;height:18px\9;position:relative;margin-right:4%">
      				<span style="position:absolute;">Analysis</span>
      				<span style="padding-left: 95%;top:-0px;"><input type="button" class="easyui-button" style="width:45px;" id="btnAddyyy" name="btnAddyyy" value="Add"/></span>
      		</div>
      		<div >
      		
      		</div>
			</td>
		</tr>
		<tr>
			<td  colspan="3" style="float: left;">
				<table id="AnalysisGrid">
					<tr><td></td></tr>
				</table>
				<div id="pagerAnalyis"></div>
			</td>
		</tr>
		</table>
		</div>
	</td>
	<td style="width: 5%;">
	<div id="divDoneby" >
	<table>
		<tr>
			<td colspan="1">
				<div class="sub-header" style="text-align: left;float:left;width:280px; width:300px\9;height:18px\9;position:relative;margin-right:1%">
      				<span style="position:absolute;">Problem Attended By</span>
      				
      				
      		</div>
      		<div >
      		</div>
			</td>
		</tr>
		<tr>
			<td  style="float: left;">
				<table id="donebyGrid2">
					<tr><td></td></tr>
				</table>
				<div id="pagerdd2"></div>
			</td>
		</tr>
		</table>
		</div>
	
	<td style="width: 35%;">
	<div id="divDoneby" >
	<table>
		<tr>
			<td colspan="1">
				<div class="sub-header" style="text-align: left;float:left;width:280px; width:300px\9;height:18px\9;position:relative;margin-right:1%">
      				<span style="position:absolute;">Why Why Done By</span>
      				
      		</div>
      		<div >
      		</div>
			</td>
		</tr>
		<tr>
			<td  style="float: left;">
				<table id="donebyGrid">
					<tr><td></td></tr>
				</table>
				<div id="pagerdd"></div>
			</td>
		</tr>
		</table>
		</div>
	</td>
	</tr></table>
	
		<table>
		<tr>
			<td colspan="2">
				<div>
					<label>Is there anything to be checked?</label>
					<span>
					    <input type="checkbox" id="chkequipment"  name="chkequipment" value="N"    value = "${ requestScope.otherCheck == 'Y' ? ' checked':''}" />
						<input id="txtWwmsOthercheckpoints" name="txtWwmsOthercheckpoints" class="easyui-text"  style=" width : 650px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsOthercheckpoints}"   value = "${requestScope.otherCheck == 'Y' ? '':'readonly'}" />
					</span>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table>
			<tr>
				<td colspan="2">
					<div class="sub-header" style="text-align: left;float:left;width:350px; width:350px\9;height:18px\9;position:relative;margin-right:4%">
	      				<span style="position:absolute;">Root Cause</span>
	      				</div>
				</td>
				<td colspan="2" style="padding-left:16px;">
					<div class="sub-header" style="text-align: left;float:left;width:550px; width:550px\9;height:18px\9;position:relative;margin-right:4%">
	      				<span style="position:absolute;">Counter Measures</span>
	      				</div>
				</td>
			</tr>
		</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="float: left;">
			
				<table id="rootPillarGrid">
					<tr><td></td></tr>
				</table>
				<div id="rootCausepager"></div>
			</td>
			<td id="divProposed" colspan="2" style="float: left;padding-left:125px; ">
				<table id="ProposedGrid">
					<tr><td></td></tr>
				</table>
				<div id="pagerProposed"></div>
			</td>
			<td>   
<!-- 			<table id="PillarGrid" width="" style="float: left;"></table> -->
<!-- 		        <div id="PillarPager"></div>    -->
		        </td>
			
		</tr>
	</table>
	 <div id="lgnd-panel" style="position:absolute;right:100px;top:50px;right:24px\9;top:54px\9;">
	 
		<ul>
			<li><a href="#"><img src="images/Green1.jpg"/><span>Agreed</span></a></li>
			<li><a href="#"><img src="images/green2.jpg"/><span>Planned</span></a></li>
			<li><a href="#"><img src="images/green-3.jpg"/><span>Implemented</span></a></li>
			<li><a href="#"><img src="images/green4.jpg"/><span>Effective</span></a></li>
		</ul>
  	 </div><!-- /settings-panel -->
</div> 
<input id="hdnwwmsPillarmode" name="hdnwwmsPillarmode" style="display:none"/>
<input type="hidden" id="hdnformType" name="hdnformType" value="${requestScope.yyFormBean.formType}"/>
<input type="hidden" id="hdnWwmsRootcauseid" name="hdnWwmsRootcauseid"  value="${requestScope.rcId}"/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" /> 
<input id="txtWwmsKeyid" type='hidden' name="txtWwmsKeyid" value="${requestScope.newBdmTlWhywhymst.wwmsKeyid}" />
<input id="txtWwmsRefdocno" type='hidden' name="txtWwmsRefdocno" value="${requestScope.newBdmTlWhywhymst.wwmsRefdocno}" />
<input id="txtWwmsRefdoctype" type='hidden' name="txtWwmsRefdoctype" value="${requestScope.newBdmTlWhywhymst.wwmsRefdoctype}" />
<input id="txtRefdocdate" type='hidden' name="txtRefdocdate" value="${requestScope.refDocdate}" />
<input type="hidden" id="frmMode" value="${requestScope.yyFormBean.formMode}" />
<input type="hidden" id="hdnrefDoctype" name="hdnrefDoctype" value="${requestScope.refDoctype}"/>
<input type="hidden" id="hdnTimeSpent" name="hdnTimeSpent" value="${requestScope.timeSpent}"/>
<input type="hidden" id="hdnCounterMeasure" name="hdnCounterMeasure" value="" />
<input type="hidden" id="hdnCounterMeasureId" name="hdnCounterMeasureId" value="" />
<input type="hidden" id="hdnval" name="hdnval" value="1" />
<input type="hidden" id="hdnContrMsureStatus" name="hdnContrMsureStatus"  value=""/>
<input type="hidden" id="mode" name="mode" value="${requestScope.Mode}"/>
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.hdnmode}"/>

<input type="hidden" id="hdnMode2" name="hdnMode2" value="${requestScope.hdnmode}"/>
<input type="hidden"id="txtWwmsKeyid" name="txtWwmsKeyid" value="" />
<input type="hidden" id="type" name="type" value="${requestScope.type}"/>
<input type="hidden" id="hdnkeyId" name="hidden" value="${requestScope.keyId}"/>
<input type="hidden" id="hdnlocationid" name="hdnlocationid" value="${requestScope.locationid}"/>
</div>
</form>
