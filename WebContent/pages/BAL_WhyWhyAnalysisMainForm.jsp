  <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script>
/* Added by Praveen*/
  

  jQuery(document).ready(function(){

	initialiseForm('frmWhyWhyAnalysis'); 
	jQuery('#submitForm').val('frmWhyWhyAnalysis');
	 //readOnlyFields("txtWwmsImmediateaction");
	 disableField("frmWhyWhyAnalysis","cmbWwmsMachineid");
	 disableCounterMeasure();
	 disableField("frmWhyWhyAnalysis", "ProposedGrid");
	 //disableField("cmbWwmsSparesId");	
	  //disableFields("chkWwmsSparesreplaced");
	 //readOnlyFields("frmWhyWhyAnalysis", "whywhyfunlocation");
	 readOnlyFields("txtWwmsProblem");
	 readOnlyFields("txtWwmsArea");
	 //readOnlyFields("txtWwmsFinalaction");
	formatDateBox('dteWwmsDate','dd-MM-yyyy');	
	formatDateBox('dteReportdatetime','dd-MM-yyyy');	

	fillComboBox("frmWhyWhyAnalysis","cmbWwmsMachineid","machineCombo.commonFilter");

	if (getFieldValue('dteReportdatetime')=='' || getFieldValue('dteReportdatetime')==' ')
		fillWithCurrentDate('dteReportdatetime');
		
	if (getFieldValue('dteWwmsDate')=='' || getFieldValue('dteWwmsDate')==' ')
		fillWithCurrentDate('dteWwmsDate');
	if(jQuery('#hdnspa').val()=='N'){
		jQuery('#chkWwmsSparesreplaced').prop('checked',false);
		readOnlyFields("");
	}
	
	readOnlyFields("cmbWwmsMachineid");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsWhywhydoneby","employee.commonFilter");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsSparesId","spareCombo.commonFilter");
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsPillarid","pillar.commonFilter");	
	fillComboBox("frmWhyWhyAnalysis","cmbWwmsProductid","product.commonFilter");
	
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
 	
  // var detailkeyid=jQuery("#txtWwdtKeyid").val();
 	
     
     
 	processGridnew("whywhyanalysisgrid_input.balwhy","q=2&masterkeyid="+masterkeyid,"AnalysisGrid","pagerAnalysis","","","","yyGrid_loadcomplete"); //TTTT  detail
    processGridnew("whywhyproposedgrid_input.balwhy","q=2&masterkeyid="+masterkeyid,"ProposedGrid","pagerProposed","","","","proposedgrid_loadcomplete");
    processGridnew('rootcause_view.balwhy','?q=2&openMode=',"rootPillarGrid","rootCausePager","","","","","");
    processGridnew('rootcause_modify.balwhy','q=2&openMode=BDM',"rootPillarGrid","","","","","rootCauseOnLoad","");
//     processGridnew('pillar_view.why',dataString,"PillarGrid","","","","","pillarOnLoad",""); /////ttttt
    
    //alert(jQuery('#hdnspa').val());    
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
		var keyId=getFieldValue("txtWwmsRefdocno");
		//var bdmKey=getFieldValue("txtWwmsRefdocno");
		//alert(keyId);
		window.open("whywhyExl_view.balwhy?keyId="+keyId);			
	});
	
	  jQuery("#cmbWwmsMachineid").combobox({onRequest:function( ){
     	 var cellId = getFieldValue('cell','frmWhyWhyAnalysis');
		 var filterStr = "&cellId=" +cellId ;
		 return filterStr;
				
	}}); 
	  
	 jQuery('#divBDWhywhy .panel-title').filter(function(){
		    return jQuery(this).parent('div').parent('div').hasClass('panel layout-panel layout-panel-center');
	}).text("Why Why Analysis ");



	 jQuery("#cmbWwmsProblemattendby").combobox({onRequest:function( ){
     	 var cellId = getFieldValue('cell','frmWhyWhyAnalysis');
		 var filterStr = "&cellId=";// +cellId ;
		 return filterStr;
				
	}}); 
});	
  

  jQuery("#btnlegend").click(function() {
  	
		jQuery("#lgnd-panel").slideToggle(200);
	});
  function yyGrid_loadcomplete()
  {
	  jQuery("input[id^=txtWhy]").keypress(function(e){
		    var p = e.which;
		    if(p==13){
		    	var newId = jQuery("#AnalysisGrid").getGridParam("reccount");
		    	if( addRow(newId) )
				{
			    	var nxtIdx = jQuery("input:text").index(this) + 1;
			        jQuery(":input:text:eq(" + nxtIdx + ")").focus();
				}    
		    }
		});
  	//disableGridSort("AnalysisGrid");

  	var row  = jQuery("#AnalysisGrid").jqGrid('getDataIDs');
      //if(row.length>0)
  		//enableCounterMeasure();
  	//else
  		disableCounterMeasure();

      jQuery("#jqgh_AnalysisGrid_Answer").removeClass("ui-jqgrid-sortable");
  	jQuery("#jqgh_AnalysisGrid_Delete").removeClass("ui-jqgrid-sortable");	   
      jQuery("#gbox_AnalysisGrid").children().removeClass("ui-jqgrid-sortable");
  	disableGridSort("AnalysisGrid");	    

  }

  function divBDWhywhy_onClose(isDirect){
	  var rootcause=jQuery("#txtWwmsRootcause").val();
	  if(rootcause.length>0)
		  jQuery("#txabdanRootcause").val(rootcause);
	  
		var ret = confirm("Do you want to Close?");//,"whywhyAnalysisPopupCloseConfirmation");
		return ret;
		//return ConfirmMsg ("Do you want to Close?");
	} 
function proposedgrid_loadcomplete(ids)
{
	fillComboBoxWithGrid("frmabnAllocation","cmbstatus_","effective.commonFilter");
	jQuery("#btnbutton_1").val('OPL');
	jQuery("#btnbutton_2").val('CLTI');
	jQuery("#btnbutton_3").val('CM');
	jQuery("#btnbutton_4").val('PM');
	jQuery("#btnbutton_5").val('KAIZEN');
	jQuery("#btnbutton_6").val('VISUAL SOP');
	disableUIButton('btnbutton_1');
	disableUIButton('btnbutton_2');
	disableUIButton('btnbutton_3');
	disableUIButton('btnbutton_4');
	disableUIButton('btnbutton_5');
	disableUIButton('btnbutton_6');
	//enableUIButton('btnbutton_4');
	
	//var rowID =  jQuery("#ProposedGrid").jqGrid('getRowData');
	var rowID = jQuery("#ProposedGrid").jqGrid('getRowData');
	var cmCnt = false;
	var disableCounter = false;
	for(var i=1;i<=rowID.length;i++)
	{	
		var rowData = jQuery("#ProposedGrid").jqGrid('getRowData',i);
		var keys = rowData.KEYID;		
		var status = jQuery("#cmbstatus_"+i).combobox('getValue');
		
		if(keys.length > 3 ){			
			cmCnt = true;
			readOnlyFields("txtWhy_"+i);
		}		
		
		if(status.toUpperCase() == 'EFFECTIVE' || status=="EFF00004" ){				
			disableCounter = true;	
		}
	}
	if (cmCnt==true){
		//jQuery('#divAnalys').append('<div id="divhide1" style="position:absolute;top:60%;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
		//EFF00004
		disableForm("frmWhyWhyAnalysis");
		enableUIButton("btnViewTemplate");
		//enableCounterMeasure();			
		disableCounterMeasure();
	}
	 if(disableCounter == true)
	{
		
		disableForm("frmWhyWhyAnalysis");
		enableUIButton("btnViewTemplate");
		disableCounterMeasure();
	}
}

function txtFormatter1(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	
	if(columnNo==3)
		return '<input type="button" id="btnbutton_'+id+'" name="btnbutton_'+id+'"  value="--" onclick="openpillarform('+id+')"   style="width:70px;  height:18px;" value="button"  class="easyui-button" />';		
	if(columnNo==6){			
		return '<input id="cmbstatus_'+options.rowId+'" name="cmbstatus_"'+options.rowId+'" style="width:100px;" disabled="disabled" class="easyui-combo" value="'+rowObject[5]+'"/>';			 
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
jQuery("#chkWwmsSparesreplaced").click(function(){     //select CheckBox
	if(jQuery("#chkWwmsSparesreplaced").is(":checked") == false)
		readOnlyFields("cmbWwmsSparesId");
	else
		enableFields("cmbWwmsSparesId");
});
function openpillarform(id){
	
	var rowData = jQuery("#ProposedGrid").jqGrid('getRowData',id);

	if( checkRootCauseSelected() ){
		jQuery("#hdnCounterMeasure").val(jQuery("#btnbutton_"+id).val());
		jQuery("hdnClickCounterMeasure").val("Y");
		jQuery("#hdnCounterMeasureId").val(rowData.KEYID);
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
	
	return '<input type="text" id="txtWhy_'+options.rowId+'"   name="txtWhy_'+options.rowId+'"  value="'+rowObject[2]+' "  style="width:900px;  height:18px;text-transform:uppercase;" maxlength="250"   class="easyui-textbox" />';
}
function actionFormatterC(cellvalue, options, rowObject) 
{
	 var rowId = options.rowId;
	 var formatStr  = '<span id="rc_'+rowId+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
}



function frmWhyWhyAnalysiscmbWwmsPhenomenaid_onSelect(record)
{
	//alert("record "+record.id);

	loadFunctionalLocation("whywhyfunlocation","functionalLoc.why","whywhyfunlocationValues","frmWhyWhyAnalysis","&machId="+record.id);
	
	}


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
	//readOnlyFields("frmWhyWhyAnalysis", "whywhyfunlocation");
	disableCounterMeasure();
	jQuery('#whywhyfunlocation').append('<div id="divhide1" style="position:absolute;top:0;left:20px;width:50%;z-index:2;opacity:0.4;height:20%;"> </div>');
	var area=jQuery('#txtWwmsArea').val();
	var cellId=getFieldValue('cell','frmWhyWhyAnalysis');
	var section=getFieldValue('section','frmWhyWhyAnalysis');

	if(keyIds.section != undefined && keyIds.section.trim() != "" && keyIds.section.trim() !=null)
	{ 
	    reloadCombo("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employee.commonFilter");
		

	  //  reloadCombo("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employee.commonFilter?&section="+keyIds.section);
		//reloadCombo("frmMom","cmbMomsRole","roleMst.commonFilter?&flid="+result.flId);
	}

	if(cellId!=null && cellId.trim().length>0)
		if (getFieldValue("txtWwmsArea").trim().length==0)
		processAjaxCalls("whywhyanalysisgrid_recall.balwhy?&cellId="+cellId,"","recallsuccessCallBack","errorCallBack");
	
	if (getFieldValue("cmbWwmsMachineid").length==0) {
	//	reloadCombo("frmWhyWhyAnalysis","cmbWwmsMachineid","machineCombo.commonFilter?cellId="+keyIds.cellId+"&flid="+keyIds.flId);
	/*	setTimeout(function() {
			setFieldValue("cmbWwmsMachineid",keyIds.machId);
		},500); */
	}
	if( keyIds.machId != undefined || keyIds.machId != null )
	 	jQuery("#cmbWwmsMachineid").combobox('setValue',keyIds.machId);
 	
	if (getFieldValue("cmbWwmsProblemattendby").length==0)
		reloadCombo("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employee.commonFilter?");
		var userL="";userL=jQuery('#usr').val();
		//jQuery("#cmbWwmsProblemattendby").combobox('setValue',userL);
 		
	//reloadCombo("frmWhyWhyAnalysis","cmbWwmsProblemattendby","employeeCombo.commonFilter?&cellId="+keyIds.cellId);
// 	reloadCombo("frmWhyWhyAnalysis","cmbWwmsWhywhydoneby","employeeCombo.commonFilter?&cellId="+keyIds.cellId);
	//alert(flid);
	
	//var mode = jQuery("#mode").val();
	var mode = jQuery("#frmWhyWhyAnalysis input[id=mode]").val();
	if(mode == "View" || mode == "view"){
		disableForm("frmWhyWhyAnalysis");
		enableUIButton("btnViewTemplate");
		jQuery('#divAnalys').append('<div id="divhide1" style="position:absolute;top:60%;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
	}


		
}

function recallsuccessCallBack(result){
	if (getFieldValue("txtWwmsArea").trim().length==0)
		setFieldValue('txtWwmsArea',result[0][0]);
	
}

function oplrecallsuccessCallBack(result){
	jQuery('#hdnContrMsureStatus').val(result[0][0]);
	var CountrmsrSts=result[0][0];
	var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
	var yykeyid=jQuery("#txtWwmsKeyid").val();
	var proposedID = jQuery("#hdnCounterMeasureId").val();
	var ds = "?refDocNo="+yykeyid+"&refDocType=YY&flid="+flid+"&oplKeyId="+proposedID;
	if(CountrmsrSts.trim().length>0 && CountrmsrSts=="C")		
		ds+="&whywhymod=View&mod=view";
	else
		ds+="&mod=create";
	
	navigateToNextForm("create_input.opl"+ds,"One Point Lesson");
}

function kznrecallsuccessCallBack(result){
	
	jQuery('#hdnContrMsureStatus').val(result[0][0]);
	var CountrmsrSts=result[0][0];
	var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
	var yykeyid=jQuery("#txtWwmsKeyid").val();
	var proposedID = jQuery("#hdnCounterMeasureId").val();
	var ds = "?refDocNo="+yykeyid+"&flid="+flid+"&refDocType=YY&Keyid="+proposedID+"&";
	if(CountrmsrSts.trim().length>0 && CountrmsrSts=="V")		
		ds+="&frmmode=View";
	else
		ds+="&frmmode=create";
	
	navigateToNextForm('KaizenBankSuggestion_input.kznbnk'+ds,'Kaizen');
	
}



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
function setQuestionInGrid1() {
    var quest = jQuery('#txtWwmsFinalaction').val();
	var ImmediateActiontaken =jQuery('#txtWwmsFinalaction').val();
	
	if(ImmediateAction != "" && ImmediateActiont != " " && ImmediateAction != undefined )
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
	navigateToPrevForm("whywhyanalysis_input.balwhy");
	disableCounterMeasure();
}
function frmWhyWhyAnalysis_beforeSubmit()
{
	var date1=jQuery('#hdnval1').val();
	//alert(date1);
	var time1=jQuery('#hdnval2').val();
	var dtetime=date1+" "+time1;
	//alert(dtetime);
	//setFieldValue("hdnWwmsReportdatetime",dtetime);
	var cellId = jQuery("#frmWhyWhyAnalysis input[id='cell']").val();
	if (cellId != null && cellId.trim().length==0) {
		alert('Select JH');
		return false;
	}
		
		var gridData  = yyyCheckDatas();		
		if(gridData.trim()=="" || gridData == null)
		{
			alert("Enter Why Why Answers");
			return false;
		}
		gridData = 'YYAnalysis='+gridData;
		return gridData;
		
	}
function yyyCheckDatas(){

    var row=jQuery("#AnalysisGrid").jqGrid('getDataIDs');//	row get data
	var col=jQuery("#AnalysisGrid").jqGrid("getGridParam","colModel");// col get data
	var dtlkeyid;
	//var dtlkeyid = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"keyid");
	//alert(dtlkeyid);
	var jsonArrO='[';
	//alert("row.length" +row.length);
	  for(var i=0;i<row.length;i++)
		{
 
	        var rowid=row[i];
		    // alert(5 +"  --  "+rowid);
			
				 var ques = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"txtWwdtWhy");  //grid datas
				 // alert(ques);
				 var dtlkeyid = jQuery("#AnalysisGrid").jqGrid('getCell',rowid,"txtWwdtKeyid");
				 if( dtlkeyid == undefined || dtlkeyid == "undefined" || dtlkeyid == null )
					 dtlkeyid = "";
					 //alert(dtlkeyid);
			

				 var ans = jQuery('#txtWhy_'+rowid).val();	  //text vals
				 
				//alert(ans);
				  if((ans !=null && ans!=""))
					  {
				
				//var Keyid = jQuery(this).attr('Keyid');
				//jsonArrO += "txtWwdtWhy :\""+ques+"\",";
					jsonArrO += '{';
				jsonArrO += '"txtWwdtWhy":"'+escape(ques)+'",';
				


				//jsonArrO += '"txtOlqdKeyid":"'+keyid+'",';
				jsonArrO += '"txtWwdtKeyid":"'+dtlkeyid+'",';	 
				jsonArrO += '"txtWwdtAnswer":"' + escape(ans.replace (/\\/g,'\\\\').replace(/"/g, '\\"') )	+'"';
				
				//jsonArrO += "txtWwdtKeyid :\""+ Keyid+"\"";
				jsonArrO +=  "},";

		}
					
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
		 	
		disableCounterMeasure();

	});
	
	function enableCounterMeasure()
	{
		for(i=1;i<=6;i++){
			
			enableUIButton('btnbutton_'+i);
			readOnlyFields("cmbstatus_"+i);
		}
		
		enableUIButton('btnbutton_4');
		
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
					    	
				       	}
			      }				

		return true;
	  }



	function checkMandatoryValidation() { 
	 	
		var area = jQuery("#txtWwmsArea").val();
		var problem = jQuery("#txtWwmsProblem").val();
		var date = jQuery('#dteWwmsDate').datebox('getValue');
		var attendby = jQuery('#cmbWwmsProblemattendby').combobox('getValue');
		var yydoneby = jQuery('#cmbWwmsWhywhydoneby').combobox('getValue');
		
		if(area==null || area=='' || problem==null || problem=='' 
				|| date==null || date=='' || attendby==null || attendby==''
					|| yydoneby==null || yydoneby==''	)
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
				processAjaxCalls("WhyWhy_delete.balwhy", "keyid="+keyid, 'remove_successCallBack','remove_errorCallBack');
				jQuery("#AnalysisGrid").delRowData(id);
				
				var row  = jQuery("#AnalysisGrid").jqGrid('getDataIDs');
				//if(row.length>0)
				//	enableCounterMeasure();
				//else
					disableCounterMeasure();
					
				return true;
			}
			else 
				return false;
		}
		else{
			
			if (r==true){
				jQuery("#AnalysisGrid").delRowData(id);
			}
			else
				 return false;
		} 
			
	}
	function remove_successCallBack(result)
	{
		
		//alert(result.successData);
		
	}


	 function dteWwmsDate_onSelect(date){		//date	
			
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
			
		}
	 
	
	 function dteReportdatetime_onSelect(date){		//date	

		 	var yydate = jQuery('#dteWwmsDate').datebox('getValue');
		 	var currentDate = getServerDateTime();
		 	
			if( date > currentDate)
			{					
				jQuery('#dteReportdatetime').datebox('clear');
				showValidationErrorMsg('dteReportdatetime','Should Not Exceed Current Date');
			}			
			else if( date > convertStringToDate(yydate))
			{					
				jQuery('#dteReportdatetime').datebox('clear');
				showValidationErrorMsg('dteReportdatetime','Should  Less than or Equal to Entry Date');
			}
			else {
				clearValidationErrorMsg('dteReportdatetime');
			}
		}
	
	 
	
	
function rootCauseOnLoad()
		{
			var formType = jQuery('#hdnformType').val();
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
				saveForm('frmWhywhyAnalysis','whywhyanalysismodify_save.balwhy');
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
		

		function frmWhyWhyAnalysis_successsCallback(result)
		{
			
			//alert(Object.keys(result));
			//var openForm = result.OpenForm;	
			var disableYY = jQuery("hdnClickCounterMeasure").val();
			//alert(disableYY);
			var openForm = result.counterMeasure;
			var msg = "Do You Want to open the Counter Measure?";
			
			var yykeyid = result.successData.keyId;
			var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
			var machine = jQuery("#frmWhyWhyAnalysis input[id='machine']").val();
			var persistentData = result.persistentData;
			var forwardData = result.forwardData;
			var proposedID = jQuery("#hdnCounterMeasureId").val();
			 if( openForm != null && openForm == "Design")
			 {
				 if(confirm(msg) == true)
				 {		
					navigateToNextForm('kaizen_input.kaizen?closeOnSave=true&refDocId='+yykeyid+"&refdocType=YY&kznKeyid="+proposedID,'Kaizen',forwardData,persistentData );
				 }
				 else
					 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "TRN")
			 {
				 if(confirm(msg))
				 {	
					 navigateToNextForm('WhyWhyTrainingList_input.balwhy','Training Program List',forwardData,persistentData );				
				 }	
				 else
					 navigateToPrevForm();
				 
					 /*var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
					 //navigateToNextForm('WhyWhyTrainingList_input.why?flid='+flid,'Training Program List');
				 if(confirm(msg))
					 navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );
						
					else
						navigateToNextForm("create_input.opl?flid="+flid,"One Point Lesson");*/
				 
				 
			 }
			 else if( openForm != null && openForm == "OPL")
			 {
				 
				 if(confirm(msg) == true)
				 {	
					 //oplKeyId
					 //navigateToNextForm('WhyWhyTrainingList_input.why','One Point Lesson',forwardData,persistentData );
					 
					 var masterkeyid=jQuery("#txtWwmsKeyid").val();
					 var proposedID = jQuery("#hdnCounterMeasureId").val();
					 
				     if(masterkeyid.trim().length<=0 || proposedID.trim().length<=0)
					    navigateToNextForm("create_input.opl?refDocNo="+yykeyid+"&frm=Create&refDocType=YY&flid="+flid+"&mod=modify&oplKeyId="+proposedID+"&","One Point Lesson",forwardData,persistentData);
					// navigateToNextForm('modify_input.opl','One Point Lesson',forwardData,persistentData );
					 else {
						processAjaxCalls("whywhyanalysisgrid_recall.balwhy?&frm=Opl&keyid="+masterkeyid,"","oplrecallsuccessCallBack","errorCallBack");
					 }
				 }	
				 else
					 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "CLTI")
			 {
				 if(confirm(msg) == true)
				 {				 
					navigateToNextForm("jhClit_input.jhclit?yyId="+yykeyid+"&refdocType=YY&machineID="+machine+"&flid="+flid,'JH Clit Standard',forwardData,persistentData );
				 }
				 else
					 navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "PM")
			 {
				 	
				 if(confirm(msg) == true)
				 {	
					navigateToNextForm("preventive_input.prv?&refDocId="+yykeyid+"&refdocType=YY",'PM Standards',forwardData,persistentData );
				 }
				 else
					 navigateToPrevForm();		 
			 }
			 else if( openForm != null && openForm == "CM")
			 {
				 navigateToNextForm("PMReport_input.prv?refDocId="+yykeyid+"&refdocType=YY","Condition Monitoring",forwardData,persistentData);
				//closePopUp();
				 //navigateToPrevForm();
			 }
			 else if( openForm != null && openForm == "KAIZEN")
			 {
				 var flid = jQuery("#frmWhyWhyAnalysis input[id='flid']").val();
				 var masterkeyid=jQuery("#txtWwmsKeyid").val();
				 var proposedID = jQuery("#hdnCounterMeasureId").val();
			     if(masterkeyid.trim().length<=0 || proposedID.trim().length<=0){
			    	 var dss = '?closeOnSave=true&refDocNo='+yykeyid+"&flid="+flid+"&refDocType=YY&kznKeyid="+proposedID+"&";
			    	 navigateToNextForm('KaizenBankSuggestion_input.kznbnk'+dss,'Kaizen',forwardData,persistentData );
			     }
				else {
					
					processAjaxCalls("whywhyanalysisgrid_recall.balwhy?&frm=Kzn&keyid="+masterkeyid,"","kznrecallsuccessCallBack","errorCallBack");
				 }
				 
			     //navigateToNextForm("create_input.opl?refDocNo="+yykeyid+"&frm=Create&refDocType=YY&flid="+flid+"&mod=modify&oplKeyId="+proposedID+"&","One Point Lesson",forwardData,persistentData);
			     
				 //navigateToNextForm("KaizenBankSuggestion_input.kznbnk?&refDocNo="+yykeyid+"&refDocType=YY&Keyid="+proposedID+"&","Kaizen",forwardData,persistentData);
			 }	 
			 
			 else if( openForm != null && openForm == "VISUAL SOP")
			 {
			 	navigateToNextForm("VisualSopDetailUpdate_input.VisualSop?refDocId="+yykeyid+"&refdocType=YY","SOP",forwardData,persistentData);
			 }
			 else{
				 //navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );
				 navigateToPrevForm();
				 
			 }
			
		}  
		
		function frmWhyWhyAnalysiscmbWwmsMachineid_onSelect(record)
		{
			//alert(123);
			//alert(record.id);
			loadFunctionalLocation("whywhyfunlocation","functionalLoc.why","whywhyfunlocationValues","frmWhyWhyAnalysis","&machId="+record.id);
		}	
			
</script>
<form id="frmWhyWhyAnalysis" name="frmWhyWhyAnalysis">
<!-- <div class="" style="height:780px;" align="center"> -->
<div id="wrapper" style="width:100%;">
<div class="main-cntborder" style="height: 780px;width:1180px;">

<!-- style="width:80%;width:984px\9;" -->
	<table  style="width:1100px;" >
		<tr>
			<td colspan="3">
				<div  id="frmWhyWhyAnalysisFuntKeyIds"  >
						<div style="padding-right: 20px;">
							<input type="hidden" id="factory" name="factory" value=" "  ></input>			
							<input type="hidden" id="section" name="section" value="${requestScope.newBdmTlWhywhymst.wwmsSectionid}"  ></input>
							<input type="hidden" id="cell" name="cell" value=" "  ></input>
							<input type="hidden" id="machine" name="machine" value=" "  ></input>
							<input type="hidden" id="flid" name="txtWwmsFlid" value="${requestScope.newBdmTlWhywhymst.wwmsFlid}"/></input>
							<input type="hidden" id="elementId" name="txtelementid" value="${requestScope.newBdmTlWhywhymst.elementid}"/></input>
						</div>
						<div id="whywhyfunlocation" class="padding" style="width:99%;"></div>
				</div>
			</td>
			<td>
				<div style="padding-top:20px;">
				<span style="margin-left:-250px;">
						<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="Excel View" style="height: 25px; width : 102px;"/>
					</span>
					<%--<input type="button" id="btnlegend" name="btnlegend" class="easyui-button" clear="false"  style=" width : 70px;height:25px;" value="Legend" />--%>
				</div>
			</td>
			<td>
				<div style="padding-top:20px;">
				<span style="margin-left:-150px;">
	              <input type="button" id="btnlegend" name="btnlegend" class="easyui-button" clear="false"  style=" width : 70px;height:25px;" value="Legend" />
					</span>
					
				</div>
			</td>
		</tr>
		<tr>
		<div style="margin-left:10;">
			<td valign="top" style="padding-top:0px; " >
				<div><label class="mandatory-lbl"  >Area</label></div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-text" style=" width :255px;text-transform: uppercase;" id="txtWwmsArea" name="txtWwmsArea"  value="${requestScope.newBdmTlWhywhymst.wwmsArea}"/>
				</div>	
		 	</td>
		 	<td valign="top"   style="padding-left:10px;padding-top:0px;">
				 <div><label >Equipment</label></div>
				 <div class="easyui-paddingbfpx" >
				 	<input  class="easyui-combobox" style=" width :255px;" id="cmbWwmsMachineid" name="cmbWwmsMachineid"  disabled="disabled" value="${requestScope.newBdmTlWhywhymst.wwmsMachineid}" style="width: 300px;" <c:out value = "${requestScope.newBdmTlWhywhymst.wwmsMachineid == true ? ' disabled':''}"/>/>
				 	
				 </div>	
		 	</td>
		 	<td valign="top" style="padding-left:10px;padding-top:0px;">
				<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Date</label><label style="padding-left:125px;">Spares</label></div>
				<div>
					<input id="dteWwmsDate" name="dteWwmsDate" disabled="disabled" class="easyui-text" clear="false"  style=" width : 120px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsDate}"/>
					<span style="padding-left:-3px;">
					<input id="chkWwmsSparesreplaced" name="chkWwmsSparesreplaced" disabled="disabled" type="checkbox" value="N" <c:out value = "${requestScope.sparecheck== 'Y' ? 'checked':''}"/> />
<%-- 					<input id="txtVersion" name="txtVersion" class="easyui-text" clear="false"  style=" width : 70px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsSparesreplaced}" /></span> --%>

					</span>	<span>
					<input  class="easyui-combobox" style=" width :150px;" id="cmbWwmsSparesId" name="cmbWwmsSparesId" disabled="disabled"value="${requestScope.newBdmTlWhywhymst.wwmsSparesId}"/></span>
				</div>
					<table>
					<tr>
					<td><span id="err_dteWwmsDate" class="tpm-errormsg"></span></td>
					<td style="padding-left:32px;"><span id="err_cmbWwmsSparesId" class="tpm-errormsg"></span></td>
					</tr>
					</table>
			</td>
		 </div>
		</tr>
		<tr>
			<td style="padding-top:-1px;">
			 	<div>
			 		<label class="mandatory-lbl">Problem/Observation</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<textarea rows="2" id="txtWwmsProblem" name="txtWwmsProblem" maxlength="495" style="width : 255px;height:40px;height:70px\9;text-transform: uppercase;">${requestScope.newBdmTlWhywhymst.wwmsProblem}</textarea>
				</div>
		 	</td><td style="padding-left:10px;padding-top:0px;">
			 	<div>
			 		<label >Immediate Action</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<textarea rows="2" id="txtWwmsImmediateaction" name="txtWwmsImmediateaction" maxlength="495" disabled="disabled" setQuestionInGrid()" style="height:40px;height:70px\9;text-transform: uppercase; width : 285px;">${requestScope.newBdmTlWhywhymst.wwmsImmediateaction}</textarea>
				</div>
		 	</td><td style="padding-left:10px;padding-top:0px;">
			 	<div>
			 		<label>Final Action</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<textarea rows="2" id="txtWwmsFinalaction" name="txtWwmsFinalaction" disabled="disabled" maxlength="495" onblur="setQuestionInGrid1()" style="height:40px;height:70px\9;text-transform: uppercase; width : 285px;">${requestScope.newBdmTlWhywhymst.wwmsFinalaction}</textarea>
				</div>
		 	</td>
		 	
			
		 	<td style="padding-left:10px;padding-top:0px;">
			 	<div>
<!--			 		<label>Spares Used</label>-->
			 	</div>
<!--				<div class="easyui-paddingbfpx" >-->
<!--					<input  class="easyui-combobox" style=" width :150px;" id="cmbSparesused" name="cmbSparesused"  value=""/>-->
<!--				</div>-->
		 	</td>
		</tr>
		<tr>
		<td style="padding-left:-1px;padding-top:0px;" valign="top">
				<div >
					<label >Reported Date & Time </label>
				</div>
				<div class="easyui-paddingbfpx">
					<span>
							<input id="dteReportdatetime"  name="dteReportdatetime" disabled="disabled"  class="easyui-datebox" value="${requestScope.rptdate}"  style="width:190px;width:190px\9;height:21px;"  />
					</span>
					<span class="spinner easyi-paddingbfpx">
							<input  id="spnNmrtOccurrencedatetime"  name="spnNmrtOccurrencedatetime" disabled="disabled"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.rpttime}"  style="width: 60px;" />
					</span>
				</div>
			</td>
			
		 	<td style="padding-left:10px;padding-top:0px;">
				<div><label >Time Spent to correct the problem (Hrs) </label></div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-text" style=" width :255px;" id="txtWwmsTimespent" name="txtWwmsTimespent"  value="${requestScope.newBdmTlWhywhymst.wwmsTimespent}"/>
				</div>
				<span id=" " class="tpm-errormsg"></span>	
		 	</td>
		 	<td style="padding-left:10px;padding-top:1px;">
				<div>
				<span style="padding-left:-20px;"><label class="mandatory-lbl">Pillar</label></span>
				<span style="padding-left:106px;"><label>Product</label></span>
				</div>
				<div class="easyui-paddingbfpx">
					
					<span>
						<input  class="easyui-combobox" style=" width : 140px; height : 22px;" id="cmbWwmsPillarid" name="cmbWwmsPillarid"  value="${requestScope.newBdmTlWhywhymst.wwmsPillarid}"/>
					</span>
					<span>
						<input  class="easyui-combobox" style=" width : 140px; height : 22px;" id="cmbWwmsProductid" name="cmbWwmsProductid" disabled="disabled" value="${requestScope.newBdmTlWhywhymst.wwmsProductid}"/>
					</span>
					
				</div>	
		 	</td>
		</tr>
		<tr>
		<td style="padding-top:1px;">
			 	<div>
			 		<label class="mandatory-lbl">Problem Attended By</label>
			 	</div>
				<div class="easyui-paddingbfpx" >
					<input  class="easyui-combobox" style=" width : 255px; height : 22px;" id="cmbWwmsProblemattendby" name="cmbWwmsProblemattendby"  value="${requestScope.newBdmTlWhywhymst.wwmsProblemattendby}"/>
					 
				</div>
				
		 	</td>
		 	<td style="padding-left:10px;padding-top:1px;">
				<div><label class="mandatory-lbl" >Why Why Done by</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input  class="easyui-text" style="width :180px;" id="cmbWwmsWhywhydoneby" name="cmbWwmsWhywhydoneby"  value="${requestScope.newBdmTlWhywhymst.wwmsWhywhydoneby}"/>
		 	</div>
		 	</td>
		 	</tr>
		 	
		<tr>
		<td>
		<span id="err_cmbWwmsProblemattendby" class="tpm-errormsg"></span>
		</td>
		<td>
		</td>
		<td>
		 <span id="err_cmbWwmsWhywhydoneby" class="tpm-errormsg"></span>
		</td>
		</tr>
	</table>
	<div id="divAnalys" style="margin-top:-30">
	<table>
		<tr>
			<td colspan="4">
			<div>
				<div class="sub-header" style="visibility: hidden;margin-top:-180;text-align: left;float:left;width:920px; width:800px\9;height:18px\9;position:relative;margin-right:4%">
      				<span style="position:absolute;">Analysis</span></div>
      				
      				<span style="padding-left: 62%; margin-top:-100px;"><input type="button" class="easyui-button" style="width:288px;height:23; margin-top:-35px;margin-left:-25px;" id="btnAddyyy" name="btnAddyyy" value="Add"/></span>
      				
      				</div>
<!--					<input type="button" class="easyui-button" style="width:45px;" id="btnDelete" name="btnDelete" value="Delete"/>-->
      		
      		<div >
      		
      		</div>
			</td>
		</tr>
		<tr>
		
			<td  colspan="4" style="float: left;">
			
				<table id="AnalysisGrid">
					<tr><td></td></tr>
				</table>
				<div id="pagerAnalyis"></div>
				
			</td>
			
		</tr>
		</table>
		</div>
		<table>
		<tr>
			<td colspan="2">
				<div>
					<label>Is there anything to be checked?</label>
					<span>
					    <input type="checkbox" id="chkequipment"  name="chkequipment" value="N"   <c:out value = "${ requestScope.otherCheck == 'Y' ? ' checked':''}"/> />
						<input id="txtWwmsOthercheckpoints" name="txtWwmsOthercheckpoints" class="easyui-text"  style=" width : 650px;height:21px;" value="${requestScope.newBdmTlWhywhymst.wwmsOthercheckpoints}"  <c:out value = "${requestScope.otherCheck == 'Y' ? '':'disabled'}"/> />
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
				<label> Root Cause</label>
				<label style="padding-left: 330px;">Counter Measures</label>
			</td>
			
		</tr>
		<tr>
			<td colspan="2" style="float: left;">
			<textarea rows="2" id="txtWwmsRootcause" name="txtWwmsRootcause" maxlength="499" onblur="setQuestionInGrid1()" style="height:40px;height:70px\9;text-transform: uppercase; width : 355px;">${requestScope.newBdmTlWhywhymst.wwmsRootcause}</textarea>
			<!--<input  class="easyui-text" id="txtWwmsRootcause" name="txtWwmsRootcause"  value="${requestScope.newBdmTlWhywhymst.wwmsRootcause}"/>-->
			<span style="padding-left: 40px;">
			<textarea rows="2" id="txtWwmsCountermeasure" name="txtWwmsCountermeasure" maxlength="499" onblur="setQuestionInGrid1()" style="height:40px;height:70px\9;text-transform: uppercase; width : 285px;">${requestScope.newBdmTlWhywhymst.wwmsCountermeasure}</textarea>
			<!--<input  class="easyui-text" id="txtWwmsCountermeasure" name="txtWwmsCountermeasure"  value="${requestScope.newBdmTlWhywhymst.wwmsCountermeasure}"/>-->
			</span>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="float: left;">
			
				<table id="rootPillarGrid">
					<tr><td></td></tr>
				</table>
				<div id="rootCausepager"></div>
			</td>
			<td id="divProposed" colspan="2" style="float: left;margin-left:125px; ">
			<%--<td id="divProposed" colspan="2" margin-left:100px; ">--%>
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
	 <div id="lgnd-panel" style="position:absolute;right:33px;top:50px;right:24px\9;top:54px\9;">
	 
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
<input type="hidden" id="hdnspa" name="hdnspa" value="${requestScope.sparecheck}" />
<input type="hidden" id="hdnWwmsReportdatetime" name="hdnWwmsReportdatetime" value="" />
<input type="hidden" id="hdnCounterMeasure" name="hdnCounterMeasure" value="" />
<input type="hidden" id="hdnCounterMeasureId" name="hdnCounterMeasureId" value="" />
<input type="hidden" id="hdnval" name="hdnval" value="1" />
<input type="hidden" id="hdnval1" name="hdnval1" value="${requestScope.rptdate}" />
<input type="hidden" id="hdnval2" name="hdnval2" value="${requestScope.rpttime}" />
<input type="hidden" id="hdnContrMsureStatus" name="hdnContrMsureStatus"  value=""/>
 <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
 <input type="hidden" id="usr" name="usr" value="${requestScope.user}"/>
 
</div>
</form>
