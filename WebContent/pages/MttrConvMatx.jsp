<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!-- <style>
table, td, th {
    border: 1px solid green;
}

th {
    background-color: green;
    color: white;
}
</style>
-->
<script type="text/javascript">
	 jQuery(document).ready(function(){
		//var url = jQuery('#hiddenUrl').val();	
		initialiseForm('frmMttrConvMatx');
		jQuery('#submitForm').val('frmMttrConvMatx');		
		formatDateBox('dteMttrDate','dd-MM-yyyy');	
		formatDateBox('dteMttdTargetdate','dd-MM-yyyy');
		formatDateBox('dteMttdCompleteddate','dd-MM-yyyy');	
		//var flid =  jQuery("#frmMttrConvMatx input[id='flid']").val();
		fillComboBox("frmMttrConvMatx","cmbMttrMachineid","machineCombo.commonFilter");	
		fillComboBox("frmMttrConvMatx","cmbMttrTradeid","trade.commonFilter" );	
		fillComboBox("frmMttrConvMatx","cmbMttrStatus","comboStatus.ConvMatx" ,null,false);	
		fillComboBox("frmMttrConvMatx","cmbMttdStatus","comboStatus.ConvMatx" ,null,false);	
		fillComboBox("frmMttrConvMatx","cmbMttdRespoinsibility","employee.commonFilter" );	
		fillComboBox("frmMttrConvMatx","cmbMttdCompletedby","employee.commonFilter" );
		var documentNo =jQuery("#txtMttrKeyid").val();
		fileManagerPopUp(documentNo,"MTTR","frmMttrConvMatx","btnFilManage","mttrFilemgr");
		/* for functionalLocation*/
			var factId = jQuery("#frmMttrConvMatx input[id='factory']").val();
			var sectionId = jQuery("#frmMttrConvMatx input[id='section']").val();
			var cellId = jQuery("#frmMttrConvMatx input[id='cell']").val();
			var machId = jQuery("#frmMttrConvMatx input[id='machine']").val();
			var flid = jQuery("#frmMttrConvMatx input[id='flid']").val();
			var fnlnDisable=false;
			if(getFieldValue("mode").trim()=="completion" || parseInt(getFieldValue("txtMttrVersion").trim())>1)	
				fnlnDisable=true;
			var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid+"&fnlnDisable="+fnlnDisable;			
			loadFunctionalLocation("ctfxfunLocation","ConvMatx_functionalLoc.ConvMatx","ctfxfunLocationValues","frmMttrConvMatx",dataStr);
		/*---------*/		
		jQuery('#submitForm').val('frmMttrConvMatx'); // set the id of form to submit
    	viewGrid();    	
    	bindControlEvents();
   	    loadMttrReadOnly();   	    
 	    ConvertSecsToMinsTime(); 
	 	if(parseInt(getFieldValue("txtMttrVersion").trim())>1){
	 		disableMandatory();
	 	}
	 	loadMode();
 	   
	 });
	 function loadMode(){
	    if (getFieldValue("mode").trim()=="view" ){
 	    	disableForm("frmMttrConvMatx");
 	    	enableUIButton("btnXlsView");
 		}
    	else if(getFieldValue("mode").trim()=="completion"){
 	    	setFieldValue("cmbMttrStatus","P");
 	    	fillWithCurrentDate("dteMttrDate"); 	
 	    	disableMandatory();
 	    	
 	    	//added on 08-Oct-2014 by babu
 	    	var newExistTime = getFieldValue("txtMttrStdsetuptime");
 	    	newExistTime=newExistTime|0;
 	    	setFieldValue("txtMttrExistingTime",newExistTime);
 	    	setFieldValue("txtExistIntTimeMins",Math.round(newExistTime/60));

 	    	var imprInternalTime = getFieldValue("txtMttrConvertedTime");
 	    	var imprExtConvtTime = getFieldValue("txtMttrExtConvertedTime");
 	    	imprInternalTime=imprInternalTime|0;
 	    	imprExtConvtTime=imprExtConvtTime|0;
 	    	
 	    	var newStdSetupTime = parseInt(imprInternalTime) + 	parseInt(imprExtConvtTime);
			//to mins
			newStdSetupTime = Math.round(newStdSetupTime/60);
 	    	setFieldValue("txtMttrStdsetuptime",newStdSetupTime);
 	 	    	
 		}	
	 }
	 function disableMandatory(){
		readOnlyFields("cmbMttrMachineid");
    	readOnlyFields("cmbMttrTradeid");
    	readOnlyFields("txtMttrProcess");    	    	
    	//disableUIButton("btnfrmMttrConvMatxmainFunLoc");
    	//disableFunctionalLocation('true','frmMttrConvMatxctfxfunLocationValues'); 	
	 }
	 function frmMttrConvMatx_FuntLocHierarchy_SuccessCallBack(result){
		var flid = result.flid;
		var machId = result.machId;
		jQuery("#frmMttrConvMatx div[id=dispFunctionalLoc]").css('width','100%');
		if( flid == undefined || flid == null ){
			flid =  jQuery("#frmMttrConvMatx input[id='flid']").val();
		}
		if( machId == undefined || machId == null ||  machId.trim().length==0 ){
			machId =  jQuery("#frmMttrConvMatx input[id='machine']").val();
		}
		if( machId == undefined || machId == null ||  machId.trim().length==0)
			machId ="";

		if (machId==null || machId.trim().length==0)
			jQuery("#cmbMttrMachineid").combobox("clear");			
		
		reloadCombo("frmMttrConvMatx","cmbMttdRespoinsibility","employee.commonFilter?cellId="+result.cellId  );	
		reloadCombo("frmMttrConvMatx","cmbMttdCompletedby","employee.commonFilter?cellId="+result.cellId  );
		getVersionNo();
	 }
	 function btnFilManage_click(){
		 saveForm("frmMttrConvMatx","MttrConvMatxEntry_save.ConvMatx?clear=false&isFileManager=true");
     }
	 function frmMttrConvMatxcmbMttrMachineid_onSelect(record){	
		 //getMachineParent();
		 var dataStr = "&machId="+record.id;			
		 loadFunctionalLocation("ctfxfunLocation","ConvMatx_functionalLoc.ConvMatx","ctfxfunLocationValues","frmMttrConvMatx",dataStr);	
		 getVersionNo();		 
	 }     	 
	 function getMachineParent(){		
			//alert("getVersionNo");
		var machineid=getFieldValue("cmbMttrMachineid");
		if ( machineid.trim().length>0 ){
			processAjaxCalls("getParFnlnForMachine.ConvMatx", "&machineid="+machineid, 'machineSuccessCallBack','machineErrorCallBack');
		}			
	 }
	 function frmMttrConvMatx_befogreenelete()
	  {  
	  	var keyid= getFieldValue("txtMttrKeyid");
	  	
	  	if(keyid.length>0){	
	  		var r =confirm("Are you sure to Delete?");
	  		if(r){			
	  		}
	  		else
	  			return false;	
	  	}
	  	else
	  		return false;
	  }
	 function machineSuccessCallBack(result){
		 var isFlId=result.isFlId;
		 //alert("MAchine isFlId"+isFlId);
		 if(isFlId){
		 	var flid=result.flId;
		 	//alert("MAchine Parent"+flid);
			var dataStr = "&flid="+flid;			
			loadFunctionalLocation("ctfxfunLocation","ConvMatx_functionalLoc.ConvMatx","ctfxfunLocationValues","frmMttrConvMatx",dataStr);
		 }		 
	 }
	 
	 jQuery('#chkNo').click(function(){
		    if (jQuery('#chkNo').attr('checked',true)) {
		       // alert(123);
		  
		    	 var intfromhrs=jQuery("#txtIntFromTimeHrss").val();	
		    	 var intfrommins=jQuery("#txtIntFromTimeMins").val();	
		    	 var intfromTimeSecs=jQuery("#txtIntFromTimeSecs").val();	
		    	 var inttofromhrs=jQuery("#txtIntToTimeHrss").val();	
		    	 var inttofrmmins=jQuery("#txtIntToTimeMins").val();	
		    	 var inttofrmsec=jQuery("#txtIntToTimeSecs").val();	
		    	 
		    	 
		    	 var extfromhrs=jQuery("#txtExtFromTimeHrss").val();
		    	 var extfrommins=jQuery("#txtExtFromTimeMins").val();	
		    	 var extfromTimeSecs=jQuery("#txtExtFromTimeSecs").val();	
		    	 var exttofromhrs=jQuery("#txtExtToTimeHrss").val();	
		    	 var exttofrmmins=jQuery("#txtExtToTimeMins").val();	
		    	 var exttofrmsec=jQuery("#txtExtToTimeSecs").val();	
		    	
		    	 
		    	jQuery("#txtIntNewFromTimeHrss").val(intfromhrs);	
		    	 jQuery("#txtIntNewFromTimeMins").val(intfrommins);	
		    	 jQuery("#txtIntNewFromTimeSecs").val(intfromTimeSecs);	
		    	jQuery("#txtIntNewToTimeHrss").val(inttofromhrs);	
		    	 jQuery("#txtIntNewToTimeMins").val(inttofrmmins);	
		    	 jQuery("#txtIntNewToTimeSecs").val(inttofrmsec);	
		    	 
		    	 
		    	jQuery("#txtExtNewFromTimeHrss").val(extfromhrs);
		    	 jQuery("#txtExtNewFromTimeMins").val(extfrommins);	
		    	 jQuery("#txtExtNewFromTimeSecs").val(extfromTimeSecs);	
		    	jQuery("#txtExtNewToTimeHrss").val(exttofromhrs);	
		    	jQuery("#txtExtNewToTimeMins").val(exttofrmmins);	
		    	 jQuery("#txtExtNewToTimeSecs").val(exttofrmsec);	
		    	 
		    	
		    	 
		    	 jQuery("#txtMttdTotalInternalNewTime").val(jQuery("#txtMttdTotalInternalTime").val());
		    	 jQuery("#txtMttdTotalExternalNewTime").val(jQuery("#txtMttdTotalExternalTime").val());
		    	 
		    	 
		    	
		    	
		    }
		}) ;
	 
	 
	 
	 jQuery('#chkYes').click(function(){
		    if (jQuery('#chkYes').attr('checked',true)) {
		    	
		    	
		    	jQuery("#txtIntNewFromTimeHrss").val(" ");	
		    	 jQuery("#txtIntNewFromTimeMins").val(" ");	
		    	 jQuery("#txtIntNewFromTimeSecs").val(" ");	
		    	jQuery("#txtIntNewToTimeHrss").val(" ");	
		    	 jQuery("#txtIntNewToTimeMins").val(" ");	
		    	 jQuery("#txtIntNewToTimeSecs").val(" ");	
		    	 
		    	 
		    	jQuery("#txtExtNewFromTimeHrss").val(" ");
		    	 jQuery("#txtExtNewFromTimeMins").val(" ");	
		    	 jQuery("#txtExtNewFromTimeSecs").val(" ");	
		    	jQuery("#txtExtNewToTimeHrss").val(" ");	
		    	jQuery("#txtExtNewToTimeMins").val(" ");	
		    	 jQuery("#txtExtNewToTimeSecs").val(" ");	
		    	 
		    	
		    	 
		    	 jQuery("#txtMttdTotalInternalNewTime").val(" ");
		    	 jQuery("#txtMttdTotalExternalNewTime").val(" ");
		    }
	 });
	 function frmMttrConvMatxcmbMttdStatus_onSelect(record){
		//var textVal = record.text;
		loadTargetCompleted();
	 }

	 function loadTargetCompleted(){
		 if (getFieldValue("cmbMttdStatus").trim()=="C"){
			enableFields("dteMttdCompleteddate");
			enableFields("cmbMttdCompletedby");
			readOnlyFields("dteMttdTargetdate");
			readOnlyFields("cmbMttdRespoinsibility");
			jQuery("#lblResp").removeClass("mandatory-lbl");
			jQuery("#lblTargetDate").removeClass("mandatory-lbl");
			jQuery("#lblCompDate").addClass("mandatory-lbl");
			jQuery("#lblCompBy").addClass("mandatory-lbl");
		}
		else{
			enableFields("dteMttdTargetdate");
			enableFields("cmbMttdRespoinsibility");
			readOnlyFields("dteMttdCompleteddate");
			readOnlyFields("cmbMttdCompletedby");	
			setFieldValue("dteMttdCompleteddate","");	
			setFieldValue("cmbMttdCompletedby","");		
			jQuery("#lblResp").addClass("mandatory-lbl");
			jQuery("#lblTargetDate").addClass("mandatory-lbl");
			jQuery("#lblCompDate").removeClass("mandatory-lbl");
			jQuery("#lblCompBy").removeClass("mandatory-lbl");
		}
	 }     
	 function addDtl(){	
		 setFieldValue("txtIsSave","1");	
		 saveForm("frmMttrConvMatx","MttrConvMatxEntry_save.ConvMatx?clear=false&isDtlSave=true");
	 }
	 function deleteDtl(){		
		 var keyid= getFieldValue("txtMttdKeyid");
		 if( keyid == null || keyid.trim().length == 0 )
		 {
			 	showComErrMsg("No data selected to delete!");
			 	return ;
		 }	 	
		 	
		 deleteRecord("frmMttrConvMatx","MttrDtlEntry_delete.ConvMatx?clear=false");
	 }
	 function bindControlEvents(){
		jQuery("#chkYes").click(function(){
			 checkYes();
		});
		jQuery("#chkNo").click(function(){
			checkNo();
		});
		jQuery ("#btnAdd").click(function(){
			addDtl();
		});
		jQuery ("#btnMttrDelete").click(function(){			
			deleteDtl();
		});	
		jQuery("#btnXlsView").click(function(){
			var keyid= getFieldValue("txtMttrKeyid");
	    	window.open("Mttr_viewExcel.ConvMatx?keyid="+keyid);
	        
	    });
		jQuery ("#btnClear").click(function(){
		   	 cleardtlFields();
		   	 calGrandTotal();
		});		 		 
		jQuery("#dteMttrDate").datebox({  	   
		    onSelect:function(recordid){
		    	isValidMttrDate();
			} 
		 });			 
		 jQuery("#dteMttdTargetdate").datebox({  	   
		    onSelect:function(recordid){
	    		isValidTargetDate();
			} 
		 });
		 jQuery("#dteMttdCompleteddate").datebox({  	   
			onSelect:function(recordid){
				isValidCompletedDate();
			} 
		 });
		 
		 jQuery("#cmbMttrMachineid").combobox({onRequest:function( ){	
			 var cellId = jQuery("#frmMttrConvMatx input[id='cell']").val();	  
		   	 return "cellId="+cellId;
	   	 }});		 
		 jQuery("#cmbMttdRespoinsibility").combobox({onRequest:function( ){
			 var cellId = jQuery("#frmMttrConvMatx input[id='cell']").val();
		   	 return "cellId="+cellId;
	  	 }});
		 jQuery("#cmbMttdCompletedby").combobox({onRequest:function( ){
			 var cellId = jQuery("#frmMttrConvMatx input[id='cell']").val();
		   	 return "cellId="+cellId;
	  	 }});
		 
		 jQuery("#txtMttrProcess").bind("change",textProcessChange);
		 jQuery("#txtIntFromTimeHrss" ).change(function() {	calIntTime("txtIntFromTimeHrss");});
		 jQuery("#txtIntFromTimeMins" ).change(function() {calIntTime("txtIntFromTimeMins");});
		 jQuery("#txtIntFromTimeSecs" ).change(function() {calIntTime("txtIntFromTimeSecs");});
		 jQuery("#txtIntToTimeHrss" ).change(function() {calIntTime("txtIntToTimeHrss");});
		 jQuery("#txtIntToTimeMins" ).change(function() {calIntTime("txtIntToTimeMins");});
		 jQuery("#txtIntToTimeSecs" ).change(function() {calIntTime("txtIntToTimeSecs");});		 
		
		 jQuery("#txtExtFromTimeHrss" ).change(function() {calExtTime("txtExtFromTimeHrss");});
		 jQuery("#txtExtFromTimeMins" ).change(function() {calExtTime("txtExtFromTimeMins");});
		 jQuery("#txtExtFromTimeSecs" ).change(function() {calExtTime("txtExtFromTimeSecs");});
		 jQuery("#txtExtToTimeHrss" ).change(function() {calExtTime("txtExtToTimeHrss");});
		 jQuery("#txtExtToTimeMins" ).change(function() {calExtTime("txtExtToTimeMins");});
		 jQuery("#txtExtToTimeSecs" ).change(function() {calExtTime("txtExtToTimeSecs");});
		 
		 jQuery("#txtIntNewFromTimeHrss" ).change(function() {calIntNewTime("txtIntNewFromTimeHrss");});
		 jQuery("#txtIntNewFromTimeMins" ).change(function() {calIntNewTime("txtIntNewFromTimeMins");});
		 jQuery("#txtIntNewFromTimeSecs" ).change(function() {calIntNewTime("txtIntNewFromTimeSecs");});
		 jQuery("#txtIntNewToTimeHrss" ).change(function() {calIntNewTime("txtIntNewToTimeHrss");});
		 jQuery("#txtIntNewToTimeMins" ).change(function() {calIntNewTime("txtIntNewToTimeMins");});
		 jQuery("#txtIntNewToTimeSecs" ).change(function() {calIntNewTime("txtIntNewToTimeSecs");}); 
		 
		 jQuery("#txtExtNewFromTimeHrss" ).change(function() {calExtNewTime("txtExtNewFromTimeHrss");});
		 jQuery("#txtExtNewFromTimeMins" ).change(function() {calExtNewTime("txtExtNewFromTimeMins");});
		 jQuery("#txtExtNewFromTimeSecs" ).change(function() {calExtNewTime("txtExtNewFromTimeSecs");});
		 jQuery("#txtExtNewToTimeHrss" ).change(function() {calExtNewTime("txtExtNewToTimeHrss");});
		 jQuery("#txtExtNewToTimeMins" ).change(function() {calExtNewTime("txtExtNewToTimeMins");});
		 jQuery("#txtExtNewToTimeSecs" ).change(function() {calExtNewTime("txtExtNewToTimeSecs");}); 
			/*
			jQuery("#txtIntFromTimeHrss" ).change(function() {	calOldTime("txtIntFromTimeHrss");});
			 jQuery("#txtIntFromTimeMins" ).change(function() {calOldTime("txtIntFromTimeMins");});
			 jQuery("#txtIntFromTimeSecs" ).change(function() {calOldTime("txtIntFromTimeSecs");});
			 jQuery("#txtIntToTimeHrss" ).change(function() {calOldTime("txtIntToTimeHrss");});
			 jQuery("#txtIntToTimeMins" ).change(function() {calOldTime("txtIntToTimeMins");});
			 jQuery("#txtIntToTimeSecs" ).change(function() {calOldTime("txtIntToTimeSecs");});		 
			
			 jQuery("#txtExtFromTimeHrss" ).change(function() {calOldTime("txtExtFromTimeHrss");});
			 jQuery("#txtExtFromTimeMins" ).change(function() {calOldTime("txtExtFromTimeMins");});
			 jQuery("#txtExtFromTimeSecs" ).change(function() {calOldTime("txtExtFromTimeSecs");});
			 jQuery("#txtExtToTimeHrss" ).change(function() {calOldTime("txtExtToTimeHrss");});
			 jQuery("#txtExtToTimeMins" ).change(function() {calOldTime("txtExtToTimeMins");});
			 jQuery("#txtExtToTimeSecs" ).change(function() {calOldTime("txtExtToTimeSecs");});
			 
			 jQuery("#txtIntNewFromTimeHrss" ).change(function() {calNewTime("txtIntNewFromTimeHrss");});
			 jQuery("#txtIntNewFromTimeMins" ).change(function() {calNewTime("txtIntNewFromTimeMins");});
			 jQuery("#txtIntNewFromTimeSecs" ).change(function() {calNewTime("txtIntNewFromTimeSecs");});
			 jQuery("#txtIntNewToTimeHrss" ).change(function() {calNewTime("txtIntNewToTimeHrss");});
			 jQuery("#txtIntNewToTimeMins" ).change(function() {calNewTime("txtIntNewToTimeMins");});
			 jQuery("#txtIntNewToTimeSecs" ).change(function() {calNewTime("txtIntNewToTimeSecs");}); 
			 
			 jQuery("#txtExtNewFromTimeHrss" ).change(function() {calNewTime("txtExtNewFromTimeHrss");});
			 jQuery("#txtExtNewFromTimeMins" ).change(function() {calNewTime("txtExtNewFromTimeMins");});
			 jQuery("#txtExtNewFromTimeSecs" ).change(function() {calNewTime("txtExtNewFromTimeSecs");});
			 jQuery("#txtExtNewToTimeHrss" ).change(function() {calNewTime("txtExtNewToTimeHrss");});
			 jQuery("#txtExtNewToTimeMins" ).change(function() {calNewTime("txtExtNewToTimeMins");});
			 jQuery("#txtExtNewToTimeSecs" ).change(function() {calNewTime("txtExtNewToTimeSecs");}); 
			*/
		//Key Up Events
		 jQuery("#txtIntFromTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtIntFromTimeHrss");});
		 jQuery("#txtIntFromTimeMins" ).keyup(function(event) {getKeyUp(event,"txtIntFromTimeMins");});
		 jQuery("#txtIntFromTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtIntFromTimeSecs");});
		 jQuery("#txtIntToTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtIntToTimeHrss");});
		 jQuery("#txtIntToTimeMins" ).keyup(function(event) {getKeyUp(event,"txtIntToTimeMins");});
		 jQuery("#txtIntToTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtIntToTimeSecs");});		 
		
		 jQuery("#txtExtFromTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtExtFromTimeHrss");});
		 jQuery("#txtExtFromTimeMins" ).keyup(function(event) {getKeyUp(event,"txtExtFromTimeMins");});
		 jQuery("#txtExtFromTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtExtFromTimeSecs");});
		 jQuery("#txtExtToTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtExtToTimeHrss");});
		 jQuery("#txtExtToTimeMins" ).keyup(function(event) {getKeyUp(event,"txtExtToTimeMins");});
		 jQuery("#txtExtToTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtExtToTimeSecs");});

		 jQuery("#txtIntNewFromTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtIntNewFromTimeHrss");});
		 jQuery("#txtIntNewFromTimeMins" ).keyup(function(event) {getKeyUp(event,"txtIntNewFromTimeMins");});
		 jQuery("#txtIntNewFromTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtIntNewFromTimeSecs");});
		 jQuery("#txtIntNewToTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtIntNewToTimeHrss");});
		 jQuery("#txtIntNewToTimeMins" ).keyup(function(event) {getKeyUp(event,"txtIntNewToTimeMins");});
		 jQuery("#txtIntNewToTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtIntNewToTimeSecs");}); 
		
		 jQuery("#txtExtNewFromTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtExtNewFromTimeHrss");});
		 jQuery("#txtExtNewFromTimeMins" ).keyup(function(event) {getKeyUp(event,"txtExtNewFromTimeMins");});
		 jQuery("#txtExtNewFromTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtExtNewFromTimeSecs");});
		 jQuery("#txtExtNewToTimeHrss" ).keyup(function(event) {getKeyUp(event,"txtExtNewToTimeHrss");});
		 jQuery("#txtExtNewToTimeMins" ).keyup(function(event) {getKeyUp(event,"txtExtNewToTimeMins");});
		 jQuery("#txtExtNewToTimeSecs" ).keyup(function(event) {getKeyUp(event,"txtExtNewToTimeSecs");});		 
	 }
	 
	 function getKeyUp(event,changeField){
		 //alert(event.keyCode)//;tab 9		 
		 var Hrss="Hrss";
		 var Mins="Mins";
		 var Secs="Secs";
		 var from="From";
		 var to="To";
		 var iint="Int";
		 var ext="Ext";
		 var intnew="IntNew";
		 var extnew="ExtNew";
		 var fromHrs="FromTimeHrss";
		 var toHrs="ToTimeHrss";
		 var fromMins="FromTimeMins";
		 var toMins="ToTimeMins";
		 var fromSecs="FromTimeSecs";
		 var toSecs="ToTimeSecs";
		 var fromFld="";
		 var toFld="";
		 var totalFld="";
		 var prefix="txt";
		 var len=0;
		 var chageValue=getFieldValue(changeField);
		 len=chageValue.trim().length;
		 //alert(len);
		 if(((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105 )  ) ){
			 //len=len+1;
			 if (len==2 ){
				// alert(chageValue.trim().length);
				 var time=changeField.substr(changeField.length-4,4);
				 //alert("time"+time);
				 var changeFieldNew=changeField.substr(0,changeField.length-4);
				 //alert("changeFieldNew"+changeFieldNew);
				 var ctrl=""; 
				 if (time==Hrss){
					 ctrl=changeFieldNew+Mins;
				 }
				 if (time==Mins){
					 ctrl=changeFieldNew+Secs;
				 }
				 if (time==Secs){
					 ctrl=changeFieldNew+Hrss;
					 if(changeField.indexOf(from)>0){
						 ctrl=changeField.replace(from,to).replace(Secs,Hrss);
					 }
					 else if(changeField.indexOf(to)>0){
						 if(changeField.indexOf(extnew)>0){
						 	ctrl="cmbMttdRespoinsibility";
						 }
						 else if(changeField.indexOf(intnew)>0){	 
						 	ctrl=changeField.replace(to,from).replace(Secs,Hrss).replace(intnew,extnew);
						 }
						 else if(changeField.indexOf(iint)>0){
						 	ctrl=changeField.replace(to,from).replace(Secs,Hrss).replace(iint,ext);
						 }
						 else if(changeField.indexOf(ext)>0){
						 	ctrl=changeField.replace(to,from).replace(Secs,Hrss).replace(ext,intnew);
						 }
					 }
				 }	
				 
				 if(changeField.indexOf(extnew)>0){
					  fromFld="txtMttdEtNewFromtime";
					  toFld="txtMttdEtNewTotime";
					  totalFld="txtMttdTotalExternalNewTime";
					  prefix+=extnew;
				 }
				 else if(changeField.indexOf(intnew)>0){
					  fromFld="txtMttdItNewFromtime";
					  toFld="txtMttdItNewTotime";
					  totalFld="txtMttdTotalInternalNewTime";
					  //totalFld="txtMttdTotalExternalNewTime";
					  prefix+=intnew;			 
				 }
				 else if(changeField.indexOf(iint)>0){
					  var fromFld="txtMttdItExtFromtime";
					  var toFld="txtMttdItExtTotime";
					  var totalFld="txtMttdTotalInternalTime";
					  //var totalFld="txtMttdTotalExternalTime";	
					  prefix+=iint;				 
				 }
				 else if(changeField.indexOf(ext)>0){
					  var fromFld="txtMttdEtExtFromtime";
					  var toFld="txtMttdEtExtTotime";
					  var totalFld="txtMttdTotalExternalTime";
					  prefix+=ext;
				 }
				 var fromTime=getFieldValue(fromFld);
				 var toTime=getFieldValue(toFld);
				
				if( convertToFieldsToTimeCal(changeField,prefix+fromHrs,prefix+toHrs,prefix+fromMins,prefix+toMins,prefix+fromSecs,prefix+toSecs,fromFld,toFld,totalFld))
			     {
					 setFocusOnField(ctrl);
			     }
				 else{
					 setFocusOnField(changeField);
				 }		
				 /*alert("true"+ctrl);
				 if (getValidTime(changeField,prefix+fromHrs,prefix+toHrs,prefix+fromMins,prefix+toMins,prefix+fromSecs,prefix+toSecs,fromTime,toTime,totalFld)){
					 setFocusOnField(ctrl);
				 }
				 else{
					 alert("false"+ctrl);
					 setFocusOnField(changeField);
				 }
					*/		 				 
			 }
		 }
	 }
	 
	 function textProcessChange(){
		 //alert("txtMttrProcess_onTextChange"); 
		 getVersionNo();
	 }
	 
	 function calGrandTotal(){
		 //alert("calGrandTotal");
		 var grdTotIntExistTime=getFieldValue("txtIntExisttime");		 
		 var grdTotIntNewTime=getFieldValue("txtIntNewtime");
		 var grdTotExtExistTime=getFieldValue("txtExtExisttime");		 
		 var grdTotExtNewTime=getFieldValue("txtExtNewtime");
		 
		 grdTotIntExistTime=grdTotIntExistTime|0;
		 grdTotIntNewTime=grdTotIntNewTime|0;
		 grdTotExtExistTime=grdTotExtExistTime|0;
		 grdTotExtNewTime=grdTotExtNewTime|0;		 
		 //alert("grdTotIntExistTime"+grdTotIntExistTime+"grdTotIntNewTime"+grdTotIntNewTime);
		 
		 var totalInternalTime=getFieldValue("txtMttdTotalInternalTime");//getFieldValue("txtMttdTotalExternalTime");
		 var totalInternalNewTime=getFieldValue("txtMttdTotalInternalNewTime");
		 var totalExternalTime=getFieldValue("txtMttdTotalExternalTime");//getFieldValue("txtMttdTotalExternalTime");
		 var totalExternalNewTime=getFieldValue("txtMttdTotalExternalNewTime");				 
		 totalInternalTime=totalInternalTime|0;
		 totalInternalNewTime=totalInternalNewTime|0;
		 totalExternalTime=totalExternalTime|0;
		 totalExternalNewTime=totalExternalNewTime|0;		 
		 //alert("totalInternalTime"+totalInternalTime+"totalExternalTime"+totalExternalTime);
		 /*if (parseInt(totalInternalTime)-parseInt(totalInternalNewTime)<0){
			 alert("New Internal Time Is Less Than Existing Internal Time");
		 }	 
		 if (parseInt(totalExternalTime)-parseInt(totalExternalNewTime)<0){ 
			 alert("New External Time Is Less Than Existing External Time");
		 }*/
		 
		 var prevIntExistTime=getFieldValue("txtPrevIntExisttime");
		 var prevIntNewtime=getFieldValue("txtPrevIntNewtime");
		 var prevExtExistTime=getFieldValue("txtPrevExtExisttime");
		 var prevExtNewtime=getFieldValue("txtPrevExtNewtime");
		 prevIntExistTime=prevIntExistTime|0;
		 prevIntNewtime=prevIntNewtime|0;
		 prevExtExistTime=prevExtExistTime|0;
		 prevExtNewtime=prevExtNewtime|0;
		 //alert("prevIntExistTime"+prevIntExistTime+"prevExternalTime"+prevExternalTime);
		 
		 totalInternalTime=parseInt(grdTotIntExistTime)+parseInt(totalInternalTime)-parseInt(prevIntExistTime);
		 totalInternalNewTime=parseInt(grdTotIntNewTime)+parseInt(totalInternalNewTime)-parseInt(prevIntNewtime);
		 totalExternalTime=parseInt(grdTotExtExistTime)+parseInt(totalExternalTime)-parseInt(prevExtExistTime);		 
		 totalExternalNewTime=parseInt(grdTotExtNewTime)+parseInt(totalExternalNewTime)-parseInt(prevExtNewtime);
		 //alert("totalInternalTime"+totalInternalTime+"totalExternalTime"+totalExternalTime);
		 if (parseInt(totalInternalTime)<0){totalInternalTime=0;}
		 if (parseInt(totalInternalNewTime)<0){totalInternalNewTime=0;}
		 if (parseInt(totalExternalTime)<0){totalExternalTime=0;}
		 if (parseInt(totalExternalNewTime)<0){totalExternalNewTime=0;}
		 
		 var savingTime=parseInt(totalInternalTime)-parseInt(totalInternalNewTime);		 
		 if (parseInt(savingTime)<0){
			 savingTime="0";
		 }
		 var savingTimeNew=parseInt(totalExternalTime)-parseInt(totalExternalNewTime);		 
		 if (parseInt(savingTimeNew)<0){ 
			 savingTimeNew="0";
		 }

		 setFieldValue("txtMttrStdsetuptime",totalInternalNewTime);
		 setFieldValue("txtMttrExistingTime",totalInternalTime);
		 setFieldValue("txtMttrConvertedTime",totalInternalNewTime);
		 setFieldValue("txtMttrSavingTime",savingTime);	
		 setFieldValue("txtMttrExtExistingTime",totalExternalTime);
		 setFieldValue("txtMttrExtConvertedTime",totalExternalNewTime);
		 setFieldValue("txtMttrExtSavingTime",savingTimeNew);	 
		 ConvertSecsToMinsTime();
	 }
	 
	 function calIntTime(changeField) {
		  //setFieldValue("txtTempTime",changeField);	      
		  var fromHrs="txtIntFromTimeHrss";
		  var toHrs="txtIntToTimeHrss";
		  var fromMins="txtIntFromTimeMins";
		  var toMins="txtIntToTimeMins";
		  var fromSecs="txtIntFromTimeSecs";
		  var toSecs="txtIntToTimeSecs";
		  var fromFld="txtMttdItExtFromtime";
		  var toFld="txtMttdItExtTotime";
		  var totalFld="txtMttdTotalInternalTime";
		   
	      if( convertToFieldsToTimeCal(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromFld,toFld,totalFld))
	      {	
	    	  var dtlId = jQuery("#txtMttdKeyid").val();
		      calculateAcutalTotalTime(dtlId,fromHrs,fromMins,fromSecs,toHrs,toMins,toSecs,"itextfromtime","itexttotime","txtMttdTotalInternalTime","txtMttdIntActtime","totalinternaltime","itacttime");
	      }   	    
	 }
	 
	 function calExtTime(changeField) {
		  //setFieldValue("txtTempTime",changeField);	      
		  var fromHrs="txtExtFromTimeHrss";
		  var toHrs="txtExtToTimeHrss";
		  var fromMins="txtExtFromTimeMins";
		  var toMins="txtExtToTimeMins";
		  var fromSecs="txtExtFromTimeSecs";
		  var toSecs="txtExtToTimeSecs";
		  var fromFld="txtMttdEtExtFromtime";
		  var toFld="txtMttdEtExtTotime";
		  var totalFld="txtMttdTotalExternalTime";
		  
	      if(convertToFieldsToTimeCal(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromFld,toFld,totalFld))
	      {	
	    	  var dtlId = jQuery("#txtMttdKeyid").val();
	    	  calculateAcutalTotalTime(dtlId,fromHrs,fromMins,fromSecs,toHrs,toMins,toSecs,"etextfromtime","etexttotime","txtMttdTotalExternalTime","txtMttdExtActtime","totalexternaltime","etacttime");
	      }    	    
	      
	 }

	 function calIntNewTime(changeField) {
		  //setFieldValue("txtTempTime",changeField);
		  var fromHrs="txtIntNewFromTimeHrss";
		  var toHrs="txtIntNewToTimeHrss";
		  var fromMins="txtIntNewFromTimeMins";
		  var toMins="txtIntNewToTimeMins";
		  var fromSecs="txtIntNewFromTimeSecs";
		  var toSecs="txtIntNewToTimeSecs";
		  var fromFld="txtMttdItNewFromtime";
		  var toFld="txtMttdItNewTotime";
		  var totalFld="txtMttdTotalInternalNewTime";
		  
		  if(convertToFieldsToTimeCal(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromFld,toFld,totalFld))
		  {	
	    	  var dtlId = jQuery("#txtMttdKeyid").val();
	    	  calculateAcutalTotalTime(dtlId,fromHrs,fromMins,fromSecs,toHrs,toMins,toSecs,"itnewfromtime","itnewtotime","txtMttdTotalInternalNewTime","txtMttdIntActNewtime","totalInternaltotime","itactnewtime");
	      } 
	 }
	 
	 function calExtNewTime(changeField) {
		  //setFieldValue("txtTempTime",changeField);
		  var fromHrs="txtExtNewFromTimeHrss";
		  var toHrs="txtExtNewToTimeHrss";
		  var fromMins="txtExtNewFromTimeMins";
		  var toMins="txtExtNewToTimeMins";
		  var fromSecs="txtExtNewFromTimeSecs";
		  var toSecs="txtExtNewToTimeSecs";
		  var fromFld="txtMttdEtNewFromtime";
		  var toFld="txtMttdEtNewTotime";
		  var totalFld="txtMttdTotalExternalNewTime";	
		  
		  if(convertToFieldsToTimeCal(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromFld,toFld,totalFld))
		  {	
	    	  var dtlId = jQuery("#txtMttdKeyid").val();
	    	  calculateAcutalTotalTime(dtlId,fromHrs,fromMins,fromSecs,toHrs,toMins,toSecs,"etnewfromtime","etnewtotime","txtMttdTotalExternalNewTime","txtMttdExtActNewtime","totalexternaltotime","etactnewtime");
	      } 
	 }
	 
	 function calOldTime(changeField){
		var fromHrs="txtIntFromTimeHrss";
		var toHrs="txtIntToTimeHrss";
		var fromMins="txtIntFromTimeMins";
		var toMins="txtIntToTimeMins";
		var fromSecs="txtIntFromTimeSecs";
		var toSecs="txtIntToTimeSecs";
		var fromFld="txtMttdItExtFromtime";
		var toFld="txtMttdItExtTotime";
		
		var efromHrs="txtExtFromTimeHrss";
		var etoHrs="txtExtToTimeHrss";
		var efromMins="txtExtFromTimeMins";
		var etoMins="txtExtToTimeMins";
		var efromSecs="txtExtFromTimeSecs";
		var etoSecs="txtExtToTimeSecs";
		var efromFld="txtMttdEtExtFromtime";
		var etoFld="txtMttdEtExtTotime";

		var totalFld="txtMttdTotalExternalTime";

		calculateTime(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs
				  ,efromHrs,etoHrs,efromMins,etoMins,efromSecs,etoSecs,fromFld,toFld,efromFld,etoFld,totalFld);
	 }
	 
	 function calNewTime(changeField){
		var fromHrs="txtIntNewFromTimeHrss";
		var toHrs="txtIntNewToTimeHrss";
		var fromMins="txtIntNewFromTimeMins";
		var toMins="txtIntNewToTimeMins";
		var fromSecs="txtIntNewFromTimeSecs";
		var toSecs="txtIntNewToTimeSecs";
		var fromFld="txtMttdItNewFromtime";
		var toFld="txtMttdItNewTotime";

		var efromHrs="txtExtNewFromTimeHrss";
		var etoHrs="txtExtNewToTimeHrss";
		var efromMins="txtExtNewFromTimeMins";
		var etoMins="txtExtNewToTimeMins";
		var efromSecs="txtExtNewFromTimeSecs";
		var etoSecs="txtExtNewToTimeSecs";
		var efromFld="txtMttdEtNewFromtime";
		var etoFld="txtMttdEtNewTotime";
		
		var totalFld="txtMttdTotalExternalNewTime";

		calculateTime(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs
				  ,efromHrs,etoHrs,efromMins,etoMins,efromSecs,etoSecs,fromFld,toFld,efromFld,etoFld,totalFld);
	 }
	 
	 
	 function calculateTime(curFld,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs
			  ,efromHrs,etoHrs,efromMins,etoMins,efromSecs,etoSecs,fromFld,toFld,efromFld,etoFld,totalFld){
		 var isValid=true;
		 //alert("calculateTime");
		 var ctrl="";
		 var msg="From Time Should Be Greater Than To Time";
		 var mttrDate=getFieldValue("dteMttrDate");
		 if (getFieldValue(curFld).trim().length==1 && getFieldValue(curFld).trim()==0){
			 showComErrMsg("Enter 00");
			 jQuery("#"+curFld).val('');
			 //setFieldValue(curFld,"00");
			 setFocusOnField(curFld);
			 return false;
		 }
		 var timeFromHrs = getTwoDecimalTime(timeValidation(getFieldValue(fromHrs),fromHrs,23));
		 var timeFromMins = getTwoDecimalTime(timeValidation(getFieldValue(fromMins),fromMins,59));
		 var timeFromSecs = getTwoDecimalTime(timeValidation(getFieldValue(fromSecs),fromSecs,59));
		 
		 var timeToHrs = getTwoDecimalTime(timeValidation(getFieldValue(toHrs),toHrs,23));
		 var timeToMins = getTwoDecimalTime(timeValidation(getFieldValue(toMins),toMins,59));
		 var timeToSecs = getTwoDecimalTime(timeValidation(getFieldValue(toSecs),toSecs,59));

		 var etimeFromHrs = getTwoDecimalTime(timeValidation(getFieldValue(efromHrs),efromHrs,23));
		 var etimeFromMins = getTwoDecimalTime(timeValidation(getFieldValue(efromMins),efromMins,59));
		 var etimeFromSecs = getTwoDecimalTime(timeValidation(getFieldValue(efromSecs),efromSecs,59));
		 
		 var etimeToHrs = getTwoDecimalTime(timeValidation(getFieldValue(etoHrs),etoHrs,23));
		 var etimeToMins = getTwoDecimalTime(timeValidation(getFieldValue(etoMins),etoMins,59));
		 var etimeToSecs = getTwoDecimalTime(timeValidation(getFieldValue(etoSecs),etoSecs,59));

		 var convFromTime=mttrDate+" "+timeFromHrs+":"+timeFromMins+":"+timeFromSecs;
		 var convToTime=mttrDate+" "+timeToHrs+":"+timeToMins+":"+timeToSecs;	
		 var econvFromTime=mttrDate+" "+etimeFromHrs+":"+etimeFromMins+":"+etimeFromSecs;
		 var econvToTime=mttrDate+" "+etimeToHrs+":"+etimeToMins+":"+etimeToSecs;	

		 setFieldValue(fromFld,convFromTime);
		 setFieldValue(toFld,convToTime);
		 setFieldValue(efromFld,econvFromTime);
		 setFieldValue(etoFld,econvToTime);
		 
		 var fromTime=convertStringToDateMttr(convFromTime);
		 var toTime=convertStringToDateMttr(convToTime);
		 var efromTime=convertStringToDateMttr(econvFromTime);
		 var etoTime=convertStringToDateMttr(econvToTime);

		 var checkflg=true;
		 if(changeField.indexOf("New")>0){
			 checkflg=getValidTime(changeField,efromHrs,etoHrs,efromMins,etoMins,efromSecs,etoSecs,econvFromTime,econvToTime,totalFld);
		 }
		 else{
			 checkflg=getValidTime(changeField,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,convFromTime,convToTime,totalFld);	
		 }
		// alert("checkflg"+checkflg);
		 //if (checkflg==false){return false;}
		 var mindate="";
		 var maxdate="";
		 if (convFromTime!=mttrDate + " 00:00:00"){
			 mindate=convFromTime;
		 }
		 else if (efromTime!=mttrDate + " 00:00:00"){
			 mindate=convFromTime;
		 }
		 else{
			 return;
		 }
		 if (convToTime!=mttrDate + " 00:00:00"){
			 maxdate=convToTime;
		 }
		 else if (convToTime!=mttrDate + " 00:00:00"){
			 maxdate=econvToTime;
		 }
		 else{
			 return;
		 }
		 if (fromTime>efromTime && econvFromTime!=mttrDate + " 00:00:00"){
			 mindate=econvFromTime;
		 }
		 if (toTime<etoTime && etoTime!=mttrDate + " 00:00:00"){
			 maxdate=econvToTime;
		 }
		 //alert("mindate:"+mindate+"maxdate:"+maxdate);
		 var oDiff =getTimeDifference(convertStringToDateMttr(mindate),convertStringToDateMttr(maxdate));
			
		 var secs =oDiff.seconds | 0;
		 //alert("secs"+secs);
		 if (parseInt(secs) > 0){
			//alert("secs"+secs);
			clearDivs(totalFld);
			setFieldValue(totalFld,secs);
			calGrandTotal();
		 }
		 else{
			 setFieldValue(totalFld,"0");
			 calGrandTotal();
		 }
	 }
	 function getValidTime(curFld,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromTime,toTime,totalFld){
		 //alert("getValidTime");
		 var msg="From Time Should Be Greater Than To Time";
		 var timeFromHrs = getTwoDecimalTime(timeValidation(getFieldValue(fromHrs),fromHrs,23));
		 var timeFromMins = getTwoDecimalTime(timeValidation(getFieldValue(fromMins),fromMins,59));
		 var timeFromSecs = getTwoDecimalTime(timeValidation(getFieldValue(fromSecs),fromSecs,59));
		 
		 var timeToHrs = getTwoDecimalTime(timeValidation(getFieldValue(toHrs),toHrs,23));
		 var timeToMins = getTwoDecimalTime(timeValidation(getFieldValue(toMins),toMins,59));
		 var timeToSecs = getTwoDecimalTime(timeValidation(getFieldValue(toSecs),toSecs,59));
		 if (fromTime.trim().length>0){
			 fromTime=convertStringToDateMttr(fromTime);
		 }
		 if (toTime.trim().length>0){
			 toTime=convertStringToDateMttr(toTime);
		 }
		 if ((fromTime != undefined  || fromTime!="" || fromTime!=" ") && (toTime!=undefined || toTime!="" || toTime!=" ")){
			
			var oDiff =getTimeDifference(fromTime,toTime);			
			var secs =oDiff.seconds | 0;
			
			if (parseInt(secs) > 0){
			}
			else{
				 if(curFld==toHrs || curFld==toMins || curFld==toSecs){	
					 //alert("new"+curFld);				
						if(getFieldValue(fromHrs).trim().length==0 && getFieldValue(fromMins).trim().length==0 || getFieldValue(fromSecs).trim().length==0){
							
						}
						else if(getFieldValue(fromHrs).trim().length>0 && getFieldValue(fromMins).trim().length>0 && getFieldValue(fromSecs).trim().length>0){
							if(parseInt(timeToHrs)<parseInt(timeFromHrs)){
								showError(msg,totalFld,toHrs);
								return false;
							}
							else if(curFld==toMins  && parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)<parseInt(timeFromMins) ){
								showError(msg,totalFld,toMins);
								return false;
							}else if(curFld==toSecs  && parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins)  &&  parseInt(timeToSecs)<parseInt(timeFromSecs)){
								showError(msg,totalFld,toSecs);
								return false;
							}else if(parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins) && parseInt(timeToSecs)==parseInt(timeFromSecs)){
								showError(msg,totalFld,toSecs);
								return false;
							}
							
						}	
				}	
				else if(curFld==fromHrs || curFld==fromMins || curFld==fromSecs){
					//alert("old"+curFld);
					if(getFieldValue(toHrs).trim().length==0 && getFieldValue(toMins).trim().length==0 || getFieldValue(toSecs).trim().length==0){
						
					}
					else if(getFieldValue(toHrs).trim().length>0 && getFieldValue(toMins).trim().length>0 && getFieldValue(toSecs).trim().length>0){
						if(parseInt(timeToHrs)<parseInt(timeFromHrs)){
							 showError(msg,totalFld,fromHrs);
							 return false;
						}else if(curFld==fromMins  && parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)<parseInt(timeFromMins) ){
							showError(msg,totalFld,fromMins);
							return false;
						}else if(curFld==fromSecs  &&  parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins)  &&  parseInt(timeToSecs)<parseInt(timeFromSecs)){
							showError(msg,totalFld,fromSecs);
							return false;
						}else if(parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins) && parseInt(timeToSecs)==parseInt(timeFromSecs)){
							showError(msg,totalFld,fromSecs);
							return false;
						}
					}			
				}	
			}
			
	 	}
		else{
			return false;	
		}
		return true;	
	 }
	 
	 function convertToFieldsToTimeCal(curFld,fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromFld,toFld,totalFld){
		 jQuery("#"+curFld).removeClass("tpm-error");
		
		 var isValid=true;
		 var ctrl="";
		 var msg="From Time Should Be Greater Than To Time";
		 var mttrDate=getFieldValue("dteMttrDate");
		 /*if (getFieldValue(curFld).trim().length==0){
			 //alert("Enter Value");
			 setFieldValue(curFld,"00");
			 setFocusOnField(curFld);
			 return false;
		 }
		 else*/ if (getFieldValue(curFld).trim().length==1 && getFieldValue(curFld).trim()==0){
			 showComErrMsg("Enter 00");
			 jQuery("#"+curFld).val('');
			 //setFieldValue(curFld,"00");
			 setFocusOnField(curFld);
			 return false;
		 }
		 	
		 var timeFromHrs = getTwoDecimalTime(timeValidation(getFieldValue(fromHrs),fromHrs,23));
		// alert("timeFromHrs::"+timeFromHrs);
		 var timeFromMins = getTwoDecimalTime(timeValidation(getFieldValue(fromMins),fromMins,59));
		 var timeFromSecs = getTwoDecimalTime(timeValidation(getFieldValue(fromSecs),fromSecs,59));
		 
		 var timeToHrs = getTwoDecimalTime(timeValidation(getFieldValue(toHrs),toHrs,23));
	//	 alert("timeFromHrs::"+timeToHrs);
		 var timeToMins = getTwoDecimalTime(timeValidation(getFieldValue(toMins),toMins,59));
		 var timeToSecs = getTwoDecimalTime(timeValidation(getFieldValue(toSecs),toSecs,59));
			
		/* if(timeFromHrs==23&&timeToHrs!=23)
			 {
			 timeToHrs=parseInt(timeToHrs)+23;
			 }
		 */
		
		 var convFromTime=mttrDate+" "+timeFromHrs+":"+timeFromMins+":"+timeFromSecs;
		 var convToTime=mttrDate+" "+timeToHrs+":"+timeToMins+":"+timeToSecs;	
		// alert("convFromTime "+convFromTime+"convToTime"+convToTime);	
		 setFieldValue(fromFld,convFromTime);
		 setFieldValue(toFld,convToTime);
		 var fromTime = convFromTime;
		 var toTime = convToTime;
		 var fromTimesub=fromTime.substring(12,14);
		 var toTimesub=toTime.substring(12,14);
		 var datetotime=toTime.substring(0,11);
		 var totimemmss=toTime.substring(14,toTime.length);
		 
		 if(fromTimesub==23&&toTimesub!=23)
		 {
			 toTimesub=parseInt(toTimesub)+parseInt(23);
			// alert("toTimesub"+toTimesub);
			 toTime=datetotime+" "+toTimesub+totimemmss;
			//alert("totimemmss"+toTime);
		 }
		 if (fromTime.trim().length>0){
			 fromTime=convertStringToDateMttr(fromTime);
		 }
		 if (toTime.trim().length>0){
			 toTime=convertStringToDateMttr(toTime);
		 }
		 //calGrandTotal();
		// alert("calgrandtotal");
		 if ((fromTime != undefined  || fromTime!="" || fromTime!=" ") && (toTime!=undefined || toTime!="" || toTime!=" ")){
			
			// var fromhrs=fromTime.substring(0,3);
			// alert("fromhrs"+fromTime);
			var oDiff =getTimeDifference(fromTime,toTime);
			
			var secs =oDiff.seconds | 0;
			if (parseInt(secs) > 0){
		//	alert("secs"+secs);
				clearDivs(totalFld);
				setFieldValue(totalFld,secs);
				if(totalFld=="txtMttdTotalInternalTime" || totalFld=="txtMttdTotalInternalNewTime" ){
					var totalInternalTime=getFieldValue("txtMttdTotalInternalTime");//getFieldValue("txtMttdTotalExternalTime");
					var totalInternalNewTime=getFieldValue("txtMttdTotalInternalNewTime");				 
					totalInternalTime=totalInternalTime|0;
					totalInternalNewTime=totalInternalNewTime|0;
					if (parseInt(totalInternalTime)-parseInt(totalInternalNewTime)<0){
					 alert("New Internal Time Is Less Than Existing Internal Time");
					}	
				}
				if(totalFld=="txtMttdTotalExternalTime" || totalFld=="txtMttdTotalExternalNewTime" ){
					var totalExternalTime=getFieldValue("txtMttdTotalExternalTime");//getFieldValue("txtMttdTotalExternalTime");
					var totalExternalNewTime=getFieldValue("txtMttdTotalExternalNewTime");				 
					totalExternalTime=totalExternalTime|0;
					totalExternalNewTime=totalExternalNewTime|0;		 
					//alert("totalInternalTime"+totalInternalTime+"totalExternalTime"+totalExternalTime);				 
					if (parseInt(totalExternalTime)-parseInt(totalExternalNewTime)<0){ 
					 alert("New External Time Is Less Than Existing External Time");
					}
				}
				calGrandTotal();
			}
			else{
				//alert("inside the else");
				if(curFld==toHrs || curFld==toMins || curFld==toSecs){					
					if(getFieldValue(fromHrs).trim().length==0 && getFieldValue(fromMins).trim().length==0 || getFieldValue(fromSecs).trim().length==0){
					}
					else if(getFieldValue(fromHrs).trim().length>0 && getFieldValue(fromMins).trim().length>0 && getFieldValue(fromSecs).trim().length>0){
						if(parseInt(timeToHrs)<parseInt(timeFromHrs)){
							if(timeFromHrs!=23)
								{
							showError(msg,totalFld,toHrs);
							return false;
								}
						}
						else if(curFld==toMins  && parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)<parseInt(timeFromMins) ){
							showError(msg,totalFld,toMins);
							return false;
						}else if(curFld==toSecs  && parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins)  &&  parseInt(timeToSecs)<parseInt(timeFromSecs)){
							showError(msg,totalFld,toSecs);
							return false;
						}else if(parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins) && parseInt(timeToSecs)==parseInt(timeFromSecs)){
							showError(msg,totalFld,toSecs);
							return false;
						}
						
					}	
				}	
				else if(curFld==fromHrs || curFld==fromMins || curFld==fromSecs){
					if(getFieldValue(toHrs).trim().length==0 && getFieldValue(toMins).trim().length==0 || getFieldValue(toSecs).trim().length==0){
					}
					else if(getFieldValue(toHrs).trim().length>0 && getFieldValue(toMins).trim().length>0 && getFieldValue(toSecs).trim().length>0){
						if(parseInt(timeToHrs)<parseInt(timeFromHrs)){
							 showError(msg,totalFld,fromHrs);
							 return false;
						}else if(curFld==fromMins  && parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)<parseInt(timeFromMins) ){
							showError(msg,totalFld,fromMins);
							return false;
						}else if(curFld==fromSecs  &&  parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins)  &&  parseInt(timeToSecs)<parseInt(timeFromSecs)){
							showError(msg,totalFld,fromSecs);
							return false;
						}else if(parseInt(timeToHrs)==parseInt(timeFromHrs) && parseInt(timeToMins)==parseInt(timeFromMins) && parseInt(timeToSecs)==parseInt(timeFromSecs)){
							showError(msg,totalFld,fromSecs);
							return false;
						}
					}			
				}	
			}
		 }
		 
		return true;
	 }
	 
	 function clearDivs(totalFld){
		 if(totalFld=="txtMttdTotalInternalTime"){
			 clearErrDivInt();
		 }
		 else if(totalFld=="txtMttdTotalExternalTime"){
			 clearErrDivExt();					 
		 }
		 else if(totalFld=="txtMttdTotalInternalNewTime"){
			 clearErrDivIntNew();
		 }
		 else if(totalFld=="txtMttdTotalExternalNewTime"){
			 clearErrDivExtNew();
		 }
	 }
	 function showError(msg,totalFld,ctrl){
		 //alert(msg);
		 clearDivs(totalFld);
		 showValidErrMsg(ctrl,msg);
		 setFieldValue(totalFld,"0");
		 setFocusOnField(ctrl);
		 calGrandTotal();
	 }
	 
	 function getTwoDecimalTime(txtTime){
		 var convTime="00";
		 if (txtTime.trim().length<=0)
			 convTime="00";
		 else if(txtTime.trim().length==1 && txtTime.trim()=="0"){			
		  	 convTime="0"+txtTime;
		 }
		 else if(txtTime.trim().length==1)
		  	 convTime="0"+txtTime;
		 else if(txtTime.trim().length==2)
	  	 	 convTime=txtTime;
	  	 return convTime;
	 }
	 
	 function convertDateFormat(hrsField,minsField,secsField,setDateField){
		 var timeHrs = getTwoDecimalTime(timeValidation(getFieldValue(hrsField),hrsField,23));
		 var timeMins = getTwoDecimalTime(timeValidation(getFieldValue(minsField),minsField,59));
		 var timeSecs = getTwoDecimalTime(timeValidation(getFieldValue(secsField),secsField,59));
		 //alert("timeHrs"+timeHrs+"timeMins"+timeMins+"timeSecs"+timeSecs);
		 var mttrDate=getFieldValue("dteMttrDate");
		 var convTime=mttrDate+" "+timeHrs+":"+timeMins+":"+timeSecs;
		 setFieldValue(setDateField,convTime);
	 }
	 function timeValidation(txtValue,txtField,maxValue){
		 if (txtValue>maxValue){
			 alert("Max Value Should Be "+ maxValue);
			 setFieldValue(txtField,maxValue);
			 return false;
		 }
		 return txtValue;
	 }
	 function convertStringToDateMttr(dateTimeStr)
	 {
	 	dateTimeStr = dateTimeStr.substring(1,2) == "-"? "0"+dateTimeStr:dateTimeStr;
	 	var hour="00" ;
	 	var min= "00";
	 	var ss= "00";
	 	var d=new Date(year,month,day,hour,min,ss);
	 	var day=dateTimeStr.substring(0,2);
	 	var month=dateTimeStr.substring(3,6);
	 	var year=dateTimeStr.substring(7,11);
	 	if( dateTimeStr.length > 11){
		 	
	 		hour=dateTimeStr.substr(12,2);
	 	}
	 	if( dateTimeStr.length > 14){
	 		min=dateTimeStr.substr(15,2);
	 	}
	 	if( dateTimeStr.length > 17){
	 		ss=dateTimeStr.substr(18,2);
	 	}
	 	//alert("hour"+hour+"min:"+min+"ss"+ss);
	 	month=changeFormatStringtoNumber(month);		 	
	 	var d=new Date(year,month,day,hour,min,ss);	 	
	 	return d;	
	 }
	 function cleardtlFields(){
		 setFieldValue("txtPrevIntExisttime","0");	
		 setFieldValue("txtPrevIntNewtime","0");
		 setFieldValue("txtPrevExtExisttime","0");	
		 setFieldValue("txtPrevExtNewtime","0");
		 setFieldValue("txtMttdKeyid","");	
		 //setFieldValue("txtMttdSlno","");
		 setFieldValue("txtMttdTask","");
		 setFieldValue("txtMttdImprovement","");
		 setFieldValue("txtMttdPossibleForConv","Y");
		 jQuery('#chkYes').attr('checked',true);
		 enableMttrDtls();	
		 setFieldValue("cmbMttdRespoinsibility","");
		 setFieldValue("dteMttdTargetdate","");
		 
		 setFieldValue("cmbMttdStatus","P");
		 loadTargetCompleted();
		 setFieldValue("dteMttdCompleteddate","");
		 setFieldValue("cmbMttdCompletedby","");
		 
		 setFieldValue("txtMttdItExtFromtime","");
		 setFieldValue("txtMttdItExtTotime","");
		 setFieldValue("txtMttdEtExtFromtime","");
		 setFieldValue("txtMttdEtExtTotime","");
		 setFieldValue("txtMttdTotalInternalTime","");
		 setFieldValue("txtMttdTotalExternalTime","");

		 setFieldValue("txtMttdItNewFromtime","");
		 setFieldValue("txtMttdItNewTotime","");
		 setFieldValue("txtMttdTotalInternalNewTime","");
		 setFieldValue("txtMttdEtNewFromtime","");
		 setFieldValue("txtMttdEtNewTotime","");
		 setFieldValue("txtMttdTotalExternalNewTime","");
			
		 setFieldValue("txtIntFromTimeHrss","");	
		 setFieldValue("txtIntToTimeHrss","");		
		 setFieldValue("txtExtFromTimeHrss","");	
		 setFieldValue("txtExtToTimeHrss","");	
		 setFieldValue("txtExtNewFromTimeHrss","");	
		 setFieldValue("txtExtNewToTimeHrss","");	
		 setFieldValue("txtIntNewFromTimeHrss","");	
		 setFieldValue("txtIntNewToTimeHrss","");	
		 					
		 setFieldValue("txtIntFromTimeMins","");	
		 setFieldValue("txtIntToTimeMins","");	
		 setFieldValue("txtExtFromTimeMins","");	
		 setFieldValue("txtExtToTimeMins","");	
		 setFieldValue("txtExtNewFromTimeMins","");	
		 setFieldValue("txtExtNewToTimeMins","");
		 setFieldValue("txtIntNewFromTimeMins","");	
		 setFieldValue("txtIntNewToTimeMins","");
		 	
		 setFieldValue("txtIntFromTimeSecs","");	
		 setFieldValue("txtIntToTimeSecs","");	
		 setFieldValue("txtExtFromTimeSecs","");	
		 setFieldValue("txtExtToTimeSecs","");	
		 setFieldValue("txtExtNewFromTimeSecs","");	
		 setFieldValue("txtExtNewToTimeSecs","");
		 setFieldValue("txtIntNewFromTimeSecs","");	
		 setFieldValue("txtIntNewToTimeSecs","");
		 getSlNo();
		 clearErrDiv();
	 }
	 function checkYes(){
		if(jQuery('#chkYes').is(':checked') == true)
			enableMttrDtls();			
		else
			disableMttrDtls();
	 }
	 function clearErrDivOthers(){
		 clearValidationErrorMsg("txtMttdTask");
		 clearValidationErrorMsg("txtMttdImprovement");
		 clearValidationErrorMsg("cmbMttdRespoinsibility");
		 clearValidationErrorMsg("cmbMttdStatus");
		 clearValidationErrorMsg("dteMttdTargetdate");
		 clearValidationErrorMsg("cmbMttdCompletedby");
		 clearValidationErrorMsg("dteMttdCompleteddate");
	 }
	 function clearErrDivInt(){
		 clearValidationErrorMsg("txtIntFromTimeHrss");
		 clearValidationErrorMsg("txtIntFromTimeMins");
		 clearValidationErrorMsg("txtIntFromTimeSecs");
		 clearValidationErrorMsg("txtIntToTimeHrss");
		 clearValidationErrorMsg("txtIntToTimeMins");
		 clearValidationErrorMsg("txtIntToTimeSecs");
	 }
	 function clearErrDivExt(){
		 clearValidationErrorMsg("txtExtFromTimeHrss");
		 clearValidationErrorMsg("txtExtFromTimeMins");
		 clearValidationErrorMsg("txtExtFromTimeSecs");
		 clearValidationErrorMsg("txtExtToTimeHrss");
		 clearValidationErrorMsg("txtExtToTimeMins");
		 clearValidationErrorMsg("txtExtToTimeSecs");
	 }
	 function clearErrDivExtNew(){
		 clearValidationErrorMsg("txtExtNewFromTimeHrss");
		 clearValidationErrorMsg("txtExtNewToTimeHrss");
		 clearValidationErrorMsg("txtExtNewFromTimeMins");
		 clearValidationErrorMsg("txtExtNewToTimeMins");
		 clearValidationErrorMsg("txtExtNewFromTimeSecs");
		 clearValidationErrorMsg("txtExtNewToTimeSecs");
	 }
	 function clearErrDivIntNew(){
		 clearValidationErrorMsg("txtIntNewFromTimeHrss");
		 clearValidationErrorMsg("txtIntNewToTimeHrss");
		 clearValidationErrorMsg("txtIntNewFromTimeMins");
		 clearValidationErrorMsg("txtIntNewToTimeMins");
		 clearValidationErrorMsg("txtIntNewFromTimeSecs");
		 clearValidationErrorMsg("txtIntNewToTimeSecs");
	 }
	 function clearErrDiv(){
		 clearErrDivOthers();
		 clearErrDivInt();
		 clearErrDivExt();
		 clearErrDivIntNew();
		 clearErrDivExtNew();
	 }
	 function checkNo(){
		 
		if(jQuery('#chkNo').is(':checked') == true)		
			disableMttrDtls();
		else		
			enableMttrDtls();		
	 }
	 function loadMttrReadOnly(){
		 jQuery('#chkYes').attr('checked',true);	
		 numericTextBox("txtMttrStdsetuptime");
		 //numericTextBox("txtMttrVersion");	
		 numericTextBox("txtMttdSlno");	
		 numericTextBox("txtIntFromTimeHrss");	
		 numericTextBox("txtIntToTimeHrss");	
		 numericTextBox("txtExtFromTimeHrss");
		 numericTextBox("txtExtToTimeHrss");
		 numericTextBox("txtExtNewFromTimeHrss");
		 numericTextBox("txtExtNewToTimeHrss");	
		 numericTextBox("txtIntNewFromTimeHrss");
		 numericTextBox("txtIntNewToTimeHrss");	
		 				
		 numericTextBox("txtIntFromTimeMins");
		 numericTextBox("txtIntToTimeMins");
		 numericTextBox("txtExtFromTimeMins");
		 numericTextBox("txtExtToTimeMins");
		 numericTextBox("txtExtNewFromTimeMins");
		 numericTextBox("txtExtNewToTimeMins");
		 numericTextBox("txtIntNewFromTimeMins");
		 numericTextBox("txtIntNewToTimeMins");
		 
		 numericTextBox("txtIntFromTimeSecs");
		 numericTextBox("txtIntToTimeSecs");
		 numericTextBox("txtExtFromTimeSecs");
		 numericTextBox("txtExtToTimeSecs");
		 numericTextBox("txtExtNewFromTimeSecs");
		 numericTextBox("txtExtNewToTimeSecs");
		 numericTextBox("txtIntNewFromTimeSecs");
		 numericTextBox("txtIntNewToTimeSecs");

		 readOnlyFields("txtMttrVersion");		 
		 readOnlyFields("txtMttrExistingTime");
    	 readOnlyFields("txtExistIntTimeMins");
    	 readOnlyFields("txtMttrConvertedTime");
    	 readOnlyFields("txtConExtTimeMins");
    	 readOnlyFields("txtMttrSavingTime");
    	 readOnlyFields("txtSavingsMins");
    	 
    	 readOnlyFields("txtMttrExtExistingTime");
    	 readOnlyFields("txtExtExistTimeMins");
    	 readOnlyFields("txtMttrExtConvertedTime");
    	 readOnlyFields("txtExtConExtTimeMins");
    	 readOnlyFields("txtMttrExtSavingTime");
    	 readOnlyFields("txtExtSavingsMins");
    	 
    	 readOnlyFields("txtMttdTotalInternalTime");
    	 readOnlyFields("txtMttdTotalExternalTime");
    	 readOnlyFields("txtMttdTotalExternalNewTime");
    	 readOnlyFields("txtMttdTotalInternalNewTime");
    	 cleardtlFields();
    	 
	 }
	 function enableMttrDtls(){
		 setFieldValue("txtMttdPossibleForConv","Y");
		 jQuery('#chkYes').attr('checked',true);
		 jQuery('#chkNo').attr('checked',false);
		 enableFields("cmbMttdRespoinsibility");
		 enableFields("dteMttdTargetdate");
		 enableFields("cmbMttdStatus");
		 if ( getFieldValue("cmbMttdStatus").trim().length==0 && getFieldValue("txtMttdPossibleForConv").trim()=="Y")
		 	setFieldValue("cmbMttdStatus","P");	
		
		 enableFields("dteMttdCompleteddate");
		 enableFields("cmbMttdCompletedby");
		 enableFields("txtMttdImprovement");
		 
		 enableFields("txtExtNewFromTimeHrss");
		 enableFields("txtExtNewToTimeHrss");
		 enableFields("txtExtNewFromTimeMins");
		 enableFields("txtExtNewToTimeMins");	
		 enableFields("txtExtNewFromTimeSecs");
		 enableFields("txtExtNewToTimeSecs");

		 enableFields("txtIntNewFromTimeHrss");
		 enableFields("txtIntNewToTimeHrss");
		 enableFields("txtIntNewFromTimeMins");
		 enableFields("txtIntNewToTimeMins");	
		 enableFields("txtIntNewFromTimeSecs");
		 enableFields("txtIntNewToTimeSecs");
		 
		 jQuery("#lblImprov").addClass("mandatory-lbl");
		 //jQuery("#lblIntNewFrom").addClass("mandatory-lbl");
		 //jQuery("#lblIntNewTo").addClass("mandatory-lbl");
		 //jQuery("#lblExtNewFrom").addClass("mandatory-lbl");
		 //jQuery("#lblExtNewTo").addClass("mandatory-lbl");
		 jQuery("#lblResp").addClass("mandatory-lbl");
		 jQuery("#lblStatus").addClass("mandatory-lbl");
		 jQuery("#lblTargetDate").addClass("mandatory-lbl");
		 //jQuery("#lblCompDate").addClass("mandatory-lbl");
		// jQuery("#lblCompBy").addClass("mandatory-lbl");
		 loadTargetCompleted();		 
	 }
	 
	 function disableMttrDtls(){
		 jQuery('#chkNo').attr('checked',true);
		 jQuery('#chkYes').attr('checked',false);

		 setFieldValue("txtMttdTotalExternalNewTime","");

		 setFieldValue("txtMttdPossibleForConv","N");
		// setFieldValue("cmbMttdStatus","");
		 jQuery("#cmbMttdRespoinsibility").combobox("clear");

		 //setFieldValue("dteMttdTargetdate","");
		 jQuery("#dteMttdTargetdate").datebox("clear");
 		 
		 jQuery("#cmbMttdStatus").combobox("clear");
		 jQuery("#dteMttdCompleteddate").datebox("clear");			 
		 //setFieldValue("dteMttdCompleteddate","");
		 jQuery("#cmbMttdCompletedby").combobox("clear");
		 //setFieldValue("cmbMttdCompletedby","");
		 setFieldValue("txtMttdImprovement","");

		 setFieldValue("txtIntNewFromTimeHrss","");
		 setFieldValue("txtIntNewToTimeHrss","");
		 setFieldValue("txtIntNewFromTimeMins","");
		 setFieldValue("txtIntNewToTimeMins","");
		 setFieldValue("txtIntNewFromTimeSecs","");
		 setFieldValue("txtIntNewToTimeSecs","");

		 setFieldValue("txtExtNewFromTimeHrss","");
		 setFieldValue("txtExtNewToTimeHrss","");
		 setFieldValue("txtExtNewFromTimeMins","");
		 setFieldValue("txtExtNewToTimeMins","");
		 setFieldValue("txtExtNewFromTimeSecs","");
		 setFieldValue("txtExtNewToTimeSecs","");		 
		 
		 readOnlyFields("cmbMttdRespoinsibility");
		 readOnlyFields("dteMttdTargetdate");
		 readOnlyFields("cmbMttdStatus");
		 readOnlyFields("dteMttdCompleteddate");
		 readOnlyFields("cmbMttdCompletedby");
		 readOnlyFields("txtMttdImprovement");

		 readOnlyFields("txtIntNewFromTimeHrss");
		 readOnlyFields("txtIntNewToTimeHrss");	
		 readOnlyFields("txtIntNewFromTimeMins");
		 readOnlyFields("txtIntNewToTimeMins");
		 readOnlyFields("txtIntNewFromTimeSecs");
		 readOnlyFields("txtIntNewToTimeSecs");
		 
		 readOnlyFields("txtExtNewFromTimeHrss");
		 readOnlyFields("txtExtNewToTimeHrss");	
		 readOnlyFields("txtExtNewFromTimeMins");
		 readOnlyFields("txtExtNewToTimeMins");
		 readOnlyFields("txtExtNewFromTimeSecs");
		 readOnlyFields("txtExtNewToTimeSecs");

		 jQuery("#lblImprov").removeClass("mandatory-lbl");
		 jQuery("#lblIntNewFrom").removeClass("mandatory-lbl");
		 jQuery("#lblIntNewTo").removeClass("mandatory-lbl");
		 jQuery("#lblExtNewFrom").removeClass("mandatory-lbl");
		 jQuery("#lblExtNewTo").removeClass("mandatory-lbl");
		 jQuery("#lblResp").removeClass("mandatory-lbl");
		 jQuery("#lblStatus").removeClass("mandatory-lbl");
		 jQuery("#lblResp").removeClass("mandatory-lbl");
		 jQuery("#lblTargetDate").removeClass("mandatory-lbl");
		 jQuery("#lblCompDate").removeClass("mandatory-lbl");
		 jQuery("#lblCompBy").removeClass("mandatory-lbl");

		 readOnlyFields("cmbMttdRespoinsibility");
		 readOnlyFields("dteMttdTargetdate");
		 
				 
	}	 
	 function showValidErrMsg(controlId,msg)
	 {
	 	jQuery('#'+controlId).addClass("tpm-error");
	 	jQuery('#'+controlId).css("border","");
	 	
	 	if( jQuery('#err_'+controlId).length <= 0 )
	 	{	
	 		if( controlId.startsWith("cmb") || controlId.startsWith("dte") )
	 			jQuery('#'+controlId).next("span").after('<div id="err_'+controlId +'" class="tpm-errormsg" ></div>');
	 		else	
	 			jQuery('#'+controlId).after('<div id="err_'+controlId +'" class="tpm-errormsg" ></div>');
	 	}	
	 	jQuery('#err_'+controlId ).css("display","block");
	 	jQuery('#err_'+controlId).html(msg);
	 }

	 function getOtherDetailsUpdate(){
		 var rowIDs = jQuery("#listdetail").jqGrid('getDataIDs');
		 var jsoStr = "";

		 for(var i =0; i < rowIDs.length-1;i++){
			 var isUpdateNeed = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"updateneed");
				 
			 if(isUpdateNeed == "Y"){
				 var dtlId = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"keyid");
				 var totalInt = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"totalinternaltime");
				 var totalExt = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"totalexternaltime");
				 var totalNewExt = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"totalexternaltime");
				 var totalNewInt = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"totalinternaltime");
				 
				 jsoStr += '{"txtMttdKeyid":"'+dtlId +'","txtMttdTotalInternalTime":"'+totalInt +'",';
				 jsoStr += '"txtMttdTotalInternalNewTime":"'+totalNewInt +'",';
				 jsoStr += '"txtMttdTotalExternalTime":"'+totalExt +'",';				 
				 jsoStr += '"txtMttdTotalExternalNewTime":"'+totalNewExt+'"},';
			 }
		 }	
		 if( jsoStr != ""){ 
		 	jsoStr  = jsoStr.substring(0,jsoStr.length-1);
		 	jsoStr  = "&updateDtlStr=[" + jsoStr + "]"; 
		 }
		 else 
			 jsoStr = "true";	
		 return  jsoStr ;
	 }	 
	 function getJsonArrCompleteCreationDtl(){
		 var jqGridId="listdetail";
		 var mttrDate =getFieldValue("dteMttrDate");
		 var rowIDs = jQuery("#"+jqGridId).jqGrid('getRowData');		
		 var jsoStr = "";
		 for(var i =0; i < rowIDs.length-1 ;i++){
			var row = rowIDs[i];
			var keyid =parseJqGridCellValue(row["keyid"]);
			var mttdslno =parseJqGridCellValue(row["mttdslno"]);
			var mttrtask =parseJqGridCellValue(row["mttrtask"]);
			var itextfromtime =mttrDate+" "+parseJqGridCellValue(row["itextfromtime"]);
			var itexttotime =mttrDate+" "+parseJqGridCellValue(row["itexttotime"]);
			var etextfromtime =mttrDate+" "+parseJqGridCellValue(row["etextfromtime"]);
			var etexttotime =mttrDate+" "+parseJqGridCellValue(row["etexttotime"]);
			var totalinternaltime =parseJqGridCellValue(row["totalinternaltime"]);
			var totalexternaltime =parseJqGridCellValue(row["totalexternaltime"]);
			var possibleforconversion =parseJqGridCellValue(row["possibleforconversion"]);
			var responsibility =parseJqGridCellValue(row["responsibilityid"]);
			var targetdate =parseJqGridCellValue(row["targetdate"]);
			var status =parseJqGridCellValue(row["status"]).substr(0,1);	
			var completeddate = parseJqGridCellValue(row["completeddate"]);
			var completedby = parseJqGridCellValue(row["completedbyid"]);
			var improvement = parseJqGridCellValue(row["improvement"]);
			var etnewfromtime = mttrDate+" "+parseJqGridCellValue(row["etnewfromtime"]);
			var etnewtotime = mttrDate+" "+parseJqGridCellValue(row["etnewtotime"]);
			var itnewfromtime = mttrDate+" "+parseJqGridCellValue(row["itnewfromtime"]);
			var itnewtotime = mttrDate+" "+parseJqGridCellValue(row["itnewtotime"]);
			var totalexternaltotime = parseJqGridCellValue(row["totalexternaltotime"]);	
			var totalinternaltotime = parseJqGridCellValue(row["totalinternaltotime"]);				
			
			jsoStr += '{';									
			jsoStr += '"txtMttdSlno":"'+mttdslno +'",';
			jsoStr += '"txtMttdTask":"'+mttrtask +'",';
			jsoStr += '"txtMttdItExtFromtime":"'+itextfromtime +'",';
			jsoStr += '"txtMttdItExtTotime":"'+itexttotime +'",';
			jsoStr += '"txtMttdEtExtFromtime":"'+etextfromtime +'",';
			jsoStr += '"txtMttdEtExtTotime":"'+etexttotime +'",';
			jsoStr += '"txtMttdTotalInternalTime":"'+totalinternaltime +'",';
			jsoStr += '"txtMttdTotalExternalTime":"'+totalexternaltime +'",';
			jsoStr += '"txtMttdPossibleForConv":"'+possibleforconversion +'",';
			jsoStr += '"cmbMttdRespoinsibility":"'+responsibility +'",';
			jsoStr += '"dteMttdTargetdate":"'+targetdate +'",';
			jsoStr += '"cmbMttdStatus":"'+status +'",';
			jsoStr += '"dteMttdCompleteddate":"'+completeddate +'",';
			jsoStr += '"cmbMttdCompletedby":"'+completedby +'",';
			jsoStr += '"txtMttdImprovement":"'+improvement +'",';
			jsoStr += '"txtMttdEtNewFromtime":"'+etnewfromtime +'",';
			jsoStr += '"txtMttdEtNewTotime":"'+etnewtotime +'",';
			jsoStr += '"txtMttdTotalExternalNewTime":"'+totalexternaltotime+'",';
			jsoStr += '"txtMttdItNewFromtime":"'+itnewfromtime +'",';
			jsoStr += '"txtMttdItNewTotime":"'+itnewtotime +'",';
			jsoStr += '"txtMttdTotalInternalNewTime":"'+totalinternaltotime+'"';
			jsoStr += '},';		
		 }
		 if( jsoStr != ""){ 
		 	jsoStr  = jsoStr.substring(0,jsoStr.length-1);
		 	jsoStr  = "&dtlStr=[" + jsoStr + "]"; 
		 }
		 else 
			 jsoStr = "";	
		 //alert("jsoStr:"+jsoStr);
		 return  jsoStr ;
	}	 
	function getIsCompleted(){
		jqGridId="listdetail";
		var isComplete=true;
		var detailkeyId =jQuery("#txtMttdKeyid").val();			
		var mttdStatus =getFieldValue("cmbMttdStatus");
		var mttdPosForConv =getFieldValue("txtMttdPossibleForConv");				
		if(detailkeyId.trim().length>0  && mttdStatus.trim()!="C" && mttdPosForConv=="Y"){
			//alert("status"+mttdStatus+mttdPosForConv);
			showValidErrMsg("cmbMttdStatus","Complete This Detail");
			isComplete=false;
		}
		var rowIDs = jQuery("#"+jqGridId).jqGrid('getRowData');
		if (rowIDs.length==0)
			isComplete=false;	
		else {
			for(var i =0; i < rowIDs.length-1 ;i++){
				var row = rowIDs[i];
				var keyid =parseJqGridCellValue(row["keyid"]);
				var status =parseJqGridCellValue(row["status"]);//jQuery("#"+jqGridId).jqGrid('getCell', rowIDs[i],"status");	
				var possibleforconversion = parseJqGridCellValue(row["possibleforconversion"]);//jQuery("#"+jqGridId).jqGrid('getCell', rowIDs[i],"possibleforconversion");										
				if (status!="COMPLETED" && possibleforconversion=="Y" ){					
					if (keyid.trim()!=detailkeyId.trim())
						isComplete=false;					
					//alert("status"+status+possibleforconversion);	
					for ( var colName in row) {
						//alert("colName"+colName);
						jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#E03D76'});  //#94E031				
					}
				}
			}
		}
		return isComplete;
	}
	function frmMttrConvMatx_beforeSubmit(){
		//return true;
		var iFromHrId="txtIntFromTimeHrss";
		var iToHrId="txtIntToTimeHrss";
		var iFromMiId="txtIntFromTimeMins";
		var iToMiId="txtIntToTimeMins";
		var iFromSeId="txtIntFromTimeSecs";
		var iToSeId="txtIntToTimeSecs";
		
		var eFromHrId="txtExtFromTimeHrss";
		var eToHrId="txtExtToTimeHrss";
		var eFromMiId="txtExtFromTimeMins";
		var eToMiId="txtExtToTimeMins";
		var eFromSeId="txtExtFromTimeSecs";
		var eToSeId="txtExtToTimeSecs";
		
		var iFromHrNewId="txtIntNewFromTimeHrss";
		var iToHrNewId="txtIntNewToTimeHrss";
		var iFromMiNewId="txtIntNewFromTimeMins";
		var iToMiNewId="txtIntNewToTimeMins";
		var iFromSeNewId="txtIntNewFromTimeSecs";
		var iToSeNewId="txtIntNewToTimeSecs";
		
		var eFromHrNewId="txtExtNewFromTimeHrss";
		var eToHrNewId="txtExtNewToTimeHrss";
		var eFromMiNewId="txtExtNewFromTimeMins";
		var eToMiNewId="txtExtNewToTimeMins";
		var eFromSeNewId="txtExtNewFromTimeSecs";
		var eToSeNewId="txtExtNewToTimeSecs";
		
		var conv=jQuery("#chkNo").is("checked");
		
		if(conv==true)
			{
		 if (jQuery('#chkNo').attr('checked',true)) {
		       // alert(123);
		  
		    	 var intfromhrs=jQuery("#txtIntFromTimeHrss").val();	
		    	 var intfrommins=jQuery("#txtIntFromTimeMins").val();	
		    	 var intfromTimeSecs=jQuery("#txtIntFromTimeSecs").val();	
		    	 var inttofromhrs=jQuery("#txtIntToTimeHrss").val();	
		    	 var inttofrmmins=jQuery("#txtIntToTimeMins").val();	
		    	 var inttofrmsec=jQuery("#txtIntToTimeSecs").val();	
		    	 
		    	 
		    	 var extfromhrs=jQuery("#txtExtFromTimeHrss").val();
		    	 var extfrommins=jQuery("#txtExtFromTimeMins").val();	
		    	 var extfromTimeSecs=jQuery("#txtExtFromTimeSecs").val();	
		    	 var exttofromhrs=jQuery("#txtExtToTimeHrss").val();	
		    	 var exttofrmmins=jQuery("#txtExtToTimeMins").val();	
		    	 var exttofrmsec=jQuery("#txtExtToTimeSecs").val();	
		    	
		    	 
		    	jQuery("#txtIntNewFromTimeHrss").val(intfromhrs);	
		    	 jQuery("#txtIntNewFromTimeMins").val(intfrommins);	
		    	 jQuery("#txtIntNewFromTimeSecs").val(intfromTimeSecs);	
		    	jQuery("#txtIntNewToTimeHrss").val(inttofromhrs);	
		    	 jQuery("#txtIntNewToTimeMins").val(inttofrmmins);	
		    	 jQuery("#txtIntNewToTimeSecs").val(inttofrmsec);	
		    	 
		    	 
		    	jQuery("#txtExtNewFromTimeHrss").val(extfromhrs);
		    	 jQuery("#txtExtNewFromTimeMins").val(extfrommins);	
		    	 jQuery("#txtExtNewFromTimeSecs").val(extfromTimeSecs);	
		    	jQuery("#txtExtNewToTimeHrss").val(exttofromhrs);	
		    	jQuery("#txtExtNewToTimeMins").val(exttofrmmins);	
		    	 jQuery("#txtExtNewToTimeSecs").val(exttofrmsec);	
		    	 
		    	
		    	 
		    	 jQuery("#txtMttdTotalInternalNewTime").val(jQuery("#txtMttdTotalInternalTime").val());
		    	 jQuery("#txtMttdTotalExternalNewTime").val(jQuery("#txtMttdTotalExternalTime").val());
		    	 
		    	 
		    	
		    	
		    }
			}
		var defaultDate="31-Dec-2100 00:00:00";
		var msg="";
		var vmsg="";
		var chkflg=true;
		var ctrl="";
		var mode=getFieldValue("txtMttrMode");
		var isValidTime=true;
		var keyId =jQuery("#txtMttrKeyid").val();
		var txtIsSave=getFieldValue("txtIsSave");	
		var mttrStatus =getFieldValue("cmbMttrStatus");
		if (mode=="completion"){
			var returnData=getJsonArrCompleteCreationDtl();
			if (returnData.trim().length>0)
				return returnData;
			else
				return true;
		}
		
		if(keyId.trim().length>0 && mttrStatus.trim()=="C"){
			var isComplete=getIsCompleted();
			if (isComplete==false){
				setTimeout(function() {
					showCommonErrorMsg("Complete All details");
				}, 200);
				div_err();
				return false;
			}			
		}
		if ( getFieldValue("txtMttdTask").trim().length==0 && getFieldValue(iFromHrId).trim().length==0 
				&& getFieldValue(iFromMiId).trim().length==0 && getFieldValue(iFromSeId).trim().length==0
				&& getFieldValue(iToHrId).trim().length==0 && getFieldValue(iToMiId).trim().length==0 && getFieldValue(iToSeId).trim().length==0
				&& getFieldValue(eFromHrId).trim().length==0 && getFieldValue(eFromMiId).trim().length==0 && getFieldValue(eFromSeId).trim().length==0
				&& getFieldValue(eToHrId).trim().length==0 && getFieldValue(eToMiId).trim().length==0 && getFieldValue(eToSeId).trim().length==0
				){			
			return true;			
		}
		//alert("yty");		
		
		if (getFieldValue("txtMttdSlno").trim().length==0){
			msg="Enter SlNo.";
			chkflg=false;
		}
		if (getFieldValue("txtMttdTask").trim().length==0){
			ctrl+="txtMttdTask";
			msg+="Enter Task";					
			chkflg=false;
		}
		vmsg+=msg;
		if(getFieldValue(iFromHrId).trim().length==0 && getFieldValue(iFromMiId).trim().length==0 && getFieldValue(iFromSeId).trim().length==0
				&& getFieldValue(iToHrId).trim().length==0 && getFieldValue(iToMiId).trim().length==0 && getFieldValue(iToMiId).trim().length==0
				&& getFieldValue(eFromHrId).trim().length==0 && getFieldValue(eFromMiId).trim().length==0 && getFieldValue(eFromSeId).trim().length==0
				&& getFieldValue(eToHrId).trim().length==0 && getFieldValue(eToMiId).trim().length==0 && getFieldValue(eToSeId).trim().length==0
			){
			ctrl+=","+iFromHrId+","+iFromMiId+","+iFromSeId+","+iToHrId+","+iToMiId+","+iToMiId+"";
			ctrl+=","+eFromHrId+","+eFromMiId+","+eFromSeId+","+eToHrId+","+eToMiId+","+eToSeId+"";
			msg+=",,,,,,,,,,,,";
			vmsg+=",Enter Existing Internal Time Or External Time ";
			chkflg=false;
		}
		else {	
			if(getFieldValue(iFromHrId).trim().length==0 && getFieldValue(iFromMiId).trim().length==0 && getFieldValue(iFromSeId).trim().length==0
					|| getFieldValue(iToHrId).trim().length==0 && getFieldValue(iToMiId).trim().length>0 && getFieldValue(iToMiId).trim().length==0){
				setFieldValue("txtMttdItExtFromtime",defaultDate);
				setFieldValue("txtMttdItExtTotime",defaultDate);
				setFieldValue("txtMttdTotalInternalTime","0");
			}			
			else if(getFieldValue(iFromHrId).trim().length>0 || getFieldValue(iFromMiId).trim().length>0 || getFieldValue(iFromSeId).trim().length>0
				|| getFieldValue(iToHrId).trim().length>0 || getFieldValue(iToMiId).trim().length>0 || getFieldValue(iToMiId).trim().length>0){
			
				if (getFieldValue(iFromHrId).trim().length==0){
					ctrl+=","+iFromHrId+"";
					msg+=",";
					isValidTime=false;			
					chkflg=false;
				}
				if (getFieldValue(iFromMiId).trim().length==0){
					ctrl+=","+iFromMiId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(iFromSeId).trim().length==0){
					ctrl+=","+iFromSeId+"";				
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (isValidTime==false){
					vmsg+=",Enter Existing Internal From Time";	
				}		
				isValidTime=true;	
				if (getFieldValue(iToHrId).trim().length==0){
					ctrl+=","+iToHrId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(iToMiId).trim().length==0){
					ctrl+=","+iToMiId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(iToMiId).trim().length==0){
					ctrl+=","+iToMiId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (isValidTime==false){
					vmsg+=",Enter Existing Internal To Time";	
				}
			}
			if(getFieldValue(eFromHrId).trim().length==0 && getFieldValue(eFromMiId).trim().length==0 && getFieldValue(eFromSeId).trim().length==0
					&& getFieldValue(eToHrId).trim().length==0 && getFieldValue(eToMiId).trim().length==0 && getFieldValue(eToSeId).trim().length==0){
				setFieldValue("txtMttdEtExtFromtime",defaultDate);
				setFieldValue("txtMttdEtExtTotime",defaultDate);
				setFieldValue("txtMttdTotalExternalTime","0");
			}else if(getFieldValue(eFromHrId).trim().length>0 || getFieldValue(eFromMiId).trim().length>0 || getFieldValue(eFromSeId).trim().length>0
					|| getFieldValue(eToHrId).trim().length>0 || getFieldValue(eToMiId).trim().length>0 || getFieldValue(eToSeId).trim().length>0){
						
				isValidTime=true;	
				if (getFieldValue(eFromHrId).trim().length==0){
					ctrl+=","+eFromHrId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(eFromMiId).trim().length==0){
					ctrl+=","+eFromMiId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(eFromSeId).trim().length==0){
					ctrl+=","+eFromSeId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (isValidTime==false){
					vmsg+=",Enter Existing External From Time";	
				}		
				isValidTime=true;
				if (getFieldValue(eToHrId).trim().length==0){
					ctrl+=","+eToHrId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(eToMiId).trim().length==0){
					ctrl+=","+eToMiId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (getFieldValue(eToSeId).trim().length==0){
					ctrl+=","+eToSeId+"";
					msg+=",";
					isValidTime=false;	
					chkflg=false;
				}
				if (isValidTime==false){
					vmsg+=",Enter Existing External To Time";	
				}
			}		
		}
		isValidTime=true;
		var dtlSt = getFieldValue("cmbMttdStatus");
		/*alert(dtlSt);
		if( dtlSt == undefined || dtlSt == null || dtlSt =="")
			dtlSt = "P"*/
		if (getFieldValue("txtMttdPossibleForConv").trim()=="Y"){
			if (getFieldValue("txtMttdImprovement").trim().length==0){				
				ctrl+=",txtMttdImprovement";				
				msg+=",Enter Improvement Details";
				vmsg+=",Enter Improvement Details";	
				chkflg=false;
			}
			if(getFieldValue(iFromHrNewId).trim().length==0 && getFieldValue(iFromMiNewId).trim().length==0 && getFieldValue(iFromSeNewId).trim().length==0
					&& getFieldValue(iToHrNewId).trim().length==0 && getFieldValue(iToMiNewId).trim().length==0 && getFieldValue(iToSeNewId).trim().length==0
					&& getFieldValue(eFromHrNewId).trim().length==0 && getFieldValue(eFromMiNewId).trim().length==0 && getFieldValue(eFromSeNewId).trim().length==0
					&& getFieldValue(eToHrNewId).trim().length==0 && getFieldValue(eToMiNewId).trim().length==0 && getFieldValue(eToSeNewId).trim().length==0
				){
				ctrl+=","+iFromHrNewId+","+iFromMiNewId+","+iFromSeNewId+","+iToHrNewId+","+iToMiNewId+","+iToSeNewId+"";
				ctrl+=","+eFromHrNewId+","+eFromMiNewId+","+eFromSeNewId+","+eToHrNewId+","+eToMiNewId+","+eToSeNewId+"";
				msg+=",,,,,,,,,,,,";
				vmsg+=",Enter New Internal Time Or External Time ";
				chkflg=false;
			}
			else {	
				if(getFieldValue(iFromHrNewId).trim().length==0 && getFieldValue(iFromMiNewId).trim().length==0 && getFieldValue(iFromSeNewId).trim().length==0
						|| getFieldValue(iToHrNewId).trim().length==0 && getFieldValue(iToMiNewId).trim().length>0 && getFieldValue(iToSeNewId).trim().length==0){
					setFieldValue("txtMttdItNewFromtime",defaultDate);
					setFieldValue("txtMttdItNewTotime",defaultDate);
					setFieldValue("txtMttdTotalInternalNewTime","0");
				}
				else{	
					if (getFieldValue(iFromHrNewId).trim().length==0){
						ctrl+=","+iFromHrNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(iFromMiNewId).trim().length==0){
						ctrl+=","+iFromMiNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(iFromSeNewId).trim().length==0){
						ctrl+=","+iFromSeNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (isValidTime==false){
						vmsg+=",Enter Existing Internal New From Time";	
					}		
					isValidTime=true;
					if (getFieldValue(iToHrNewId).trim().length==0){
						ctrl+=","+iToHrNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(iToMiNewId).trim().length==0){
						ctrl+=","+iToMiNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(iToSeNewId).trim().length==0){
						ctrl+=","+iToSeNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (isValidTime==false){
						vmsg+=",Enter Existing Internal New To Time";	
					}
					isValidTime=true;
				}
				if(getFieldValue(eFromHrNewId).trim().length==0 && getFieldValue(eFromMiNewId).trim().length==0 && getFieldValue(eFromSeNewId).trim().length==0
						|| getFieldValue(eToHrNewId).trim().length==0 && getFieldValue(eToMiNewId).trim().length>0 && getFieldValue(eToSeNewId).trim().length==0){
					setFieldValue("txtMttdEtNewFromtime",defaultDate);
					setFieldValue("txtMttdEtNewTotime",defaultDate);
					setFieldValue("txtMttdTotalExternalNewTime","0");
				}
				else{	
					if (getFieldValue(eFromHrNewId).trim().length==0){
						ctrl+=","+eFromHrNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(eFromMiNewId).trim().length==0){
						ctrl+=","+eFromMiNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(eFromSeNewId).trim().length==0){
						ctrl+=","+eFromSeNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (isValidTime==false){
						vmsg+=",Enter Existing External New From Time";	
					}		
					isValidTime=true;
					if (getFieldValue(eToHrNewId).trim().length==0){
						ctrl+=","+eToHrNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(eToMiNewId).trim().length==0){
						ctrl+=","+eToMiNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (getFieldValue(eToSeNewId).trim().length==0){
						ctrl+=","+eToSeNewId+"";
						msg+=",";
						isValidTime=false;	
						chkflg=false;
					}
					if (isValidTime==false){
						vmsg+=",Enter Existing External New To Time";	
					}
					isValidTime=true;
				}
				
			}
			
			if (getFieldValue("cmbMttdStatus").trim().length==0){
				ctrl+=",cmbMttdStatus";					
				msg+=",Select Status";
				vmsg+=",Select Status";
				chkflg=false;
			}
			if (getFieldValue("cmbMttdStatus").trim()=="C"){
				if (getFieldValue("cmbMttdCompletedby").trim().length==0){
					ctrl+=",cmbMttdCompletedby";
					msg+=",Select Completed By";
					vmsg+=",Select Completed By";
					chkflg=false;
				}
				if (getFieldValue("dteMttdCompleteddate").trim().length==0){
					ctrl+=",dteMttdCompleteddate";
					msg+=",Select Completed Date";
					vmsg+=",Select Completed Date";
					chkflg=false;
				}
			}
			else if(dtlSt == "P" ||dtlSt == "W"){
				if (getFieldValue("cmbMttdRespoinsibility").trim().length==0){
					ctrl+=",cmbMttdRespoinsibility";
					msg+=",Select Responsibility";
					vmsg+=",Select Responsibility";
					chkflg=false;
				}
				if (getFieldValue("dteMttdTargetdate").trim().length==0){
					ctrl+=",dteMttdTargetdate";
					msg+=",Select Target Date";
					vmsg+=",Select Target Date";
					chkflg=false;
				}
			}
						
			
		}
		//alert("chkflg:"+chkflg+"vmsg:"+vmsg);
		if(chkflg==false){			
			var msgStr=msg.split(",");
			var ctrlStr=ctrl.split(",");
			for(var i=0;i<msgStr.length;i++){
				showValidErrMsg(ctrlStr[i],msgStr[i]);
			}

			setTimeout(function() {
				showCommonErrorMsg(vmsg);
			}, 200);
			div_err();
			return false;
		}
		//alert("end");				
		chkflg=chkBeforeSubmitTime(iFromHrId,iToHrId,iFromMiId,iToMiId,iFromSeId,iToSeId,"txtMttdItExtFromtime","txtMttdItExtTotime","txtMttdTotalInternalTime");
		if (chkflg==false){return false;}
		chkflg=chkBeforeSubmitTime(eFromHrId,eToHrId,eFromMiId,eToMiId,eFromSeId,eToSeId,"txtMttdEtExtFromtime","txtMttdEtExtTotime","txtMttdTotalExternalTime");
		if (chkflg==false){return false;}		
		chkflg=chkBeforeSubmitTime(iFromHrNewId,iToHrNewId,iFromMiNewId,iToMiNewId,iFromSeNewId,iToSeNewId,"txtMttdItNewFromtime","txtMttdItNewTotime","txtMttdTotalInternalNewTime");
		if (chkflg == false){ return false; }
		chkflg=chkBeforeSubmitTime(eFromHrNewId,eToHrNewId,eFromMiNewId,eToMiNewId,eFromSeNewId,eToSeNewId,"txtMttdEtNewFromtime","txtMttdEtNewTotime","txtMttdTotalExternalNewTime");
		if (chkflg == false){ return false; }
		
		if( chkflg == true || chkflg == "true" ){						
			return getOtherDetailsUpdate() ;
		}	
		if (txtIsSave.trim().length==0){return "&isDtlSave=true";}
		else{return chkflg;}		
	}
	
	function chkBeforeSubmitTime(fromHrs,toHrs,fromMins,toMins,fromSecs,toSecs,fromFld,toFld,totalFld){
		var mttrDate=getFieldValue("dteMttrDate");
		var timeFromHrs = getTwoDecimalTime(timeValidation(getFieldValue(fromHrs),fromHrs,23));
		var timeFromMins = getTwoDecimalTime(timeValidation(getFieldValue(fromMins),fromMins,59));
		var timeFromSecs = getTwoDecimalTime(timeValidation(getFieldValue(fromSecs),fromSecs,59));

		var timeToHrs = getTwoDecimalTime(timeValidation(getFieldValue(toHrs),toHrs,23));
		var timeToMins = getTwoDecimalTime(timeValidation(getFieldValue(toMins),toMins,59));
		var timeToSecs = getTwoDecimalTime(timeValidation(getFieldValue(toSecs),toSecs,59));

		var convFromTime=mttrDate+" "+timeFromHrs+":"+timeFromMins+":"+timeFromSecs;
		var convToTime=mttrDate+" "+timeToHrs+":"+timeToMins+":"+timeToSecs;
		//alert("chkBeforeSubmitTime");
		//from, to both valuess are nullfdsf
	
		
		
		clearDivs(totalFld);
		if (parseInt(timeFromHrs)==0 && parseInt(timeFromMins)==0 && parseInt(timeFromSecs)==0 
			&& parseInt(timeToHrs)==0 && parseInt(timeToMins)==0 && parseInt(timeToSecs)==0){
			//alert("");
			return true;
		}		
		//from null, to has values
		else if ((parseInt(timeFromHrs)==0 && parseInt(timeFromMins)==0 && parseInt(timeFromSecs)==0 )
				&& (parseInt(timeToHrs)>0 || parseInt(timeToMins)>0 || parseInt(timeToSecs)>0)){
			setFocusOnField(fromHrs);
			showValidErrMsg(fromHrs,"Enter From Time");			
			return false;
		}
		//from has values, to null
		else if ((parseInt(timeFromHrs)>0 || parseInt(timeFromMins)>0 || parseInt(timeFromSecs)>0 )
				&& (parseInt(timeToHrs)==0 && parseInt(timeToMins)==0 && parseInt(timeToSecs)==0)){
			showValidErrMsg(fromHrs,"From Time Should Be Less Than To Time");
			setFocusOnField(toHrs);
			return false;
		}
		//from values greater than to values		
		else if ((parseInt(timeFromHrs)>0 || parseInt(timeFromMins)>0 || parseInt(timeFromSecs)>0 )
				&& (parseInt(timeToHrs)>0 || parseInt(timeToMins)>0|| parseInt(timeToSecs)>0)){
			
			var fromTime=convertStringToDateMttr(convFromTime);
			var toTime=convertStringToDateMttr(convToTime);
			var oDiff =getTimeDifference(fromTime,toTime);
			var secs =oDiff.seconds | 0;
			//alert("secs"+secs);
			if (parseInt(secs)>0){
			//	setFieldValue(fromFld,convFromTime);
			//	setFieldValue(toFld,convToTime);
			//	setFieldValue(totalFld,secs);
				//calGrandTotal();
			}
			else{
				
				if(timeToHrs==23&&timeToHrs!=23)
					{
				showValidErrMsg(fromHrs,"From Time Should Be Less Than To Time");
				if(parseInt(timeToHrs)==0){
					setFocusOnField(toHrs);
				} 
				else if(parseInt(timeToMins)==0){
					setFocusOnField(toMins);
				} 
				else if(parseInt(timeToSecs)==0){
					setFocusOnField(toSecs);
				}
				else{setFocusOnField(toSecs);}
				return false;	
					}
			}			
		}
		return true;	
		
	}
	function frmMttrConvMatx_successsCallback(result)
	{
		//alert("result.successData.Mttrkeyid:"+result.successData.MttrKeyid);
		//alert("result.successData.Mttdkeyid:"+result.successData.MttdKeyid);
		//alert("result.successData.isFileManager:"+result.successData.isFileManager);
		//alert("result.successData.isDtlSave:"+result.successData.isDtlSave);		
		var isFileManager=result.successData.isFileManager;
		var isDtlSave=result.successData.isDtlSave;	
		var masId=result.successData.MttrKeyid;		
		var mode=result.successData.mode;		
		var versionNo=result.successData.versionNo;		
		//alert("masId:"+masId);
		setFieldValue("txtIsSave","");		
		setFieldValue("txtMttrKeyid",masId);
		setFieldValue("txtMttrMode",mode);
		setFieldValue("txtMttrVersion",versionNo);
		if(isFileManager==true){	
			fileManagerPopUp(masId,"MTTR","","","");
		} 
		else if(isDtlSave==true){
			cleardtlFields();
			viewGrid();
			setFieldValue("txtMttdSlno",result.slNo);
			var intTotal = jQuery("#txtMttrExistingTime").val();
			setFieldValue("txtIntExisttime",intTotal);
			var intNewTotal = jQuery("#txtMttrConvertedTime").val();
			setFieldValue("txtIntNewtime",intNewTotal);

			var extTotal = jQuery("#txtMttrExtExistingTime").val();
			setFieldValue("txtExtExisttime",extTotal);
			var extNewTotal = jQuery("#txtMttrExtConvertedTime").val();
			setFieldValue("txtExtNewtime",extNewTotal);
		}
		else{
			cleardtlFields();
			viewGrid();
			setFieldValue("txtMttdSlno",result.slNo);
		}	
	}

	function frmMttrConvMatx_beforeDelete()
	{
		var mode=getFieldValue("mode");
		if(mode=="view") {
			alert("View Mode, Data can not be deleted.");
			return false;
		}
		var msg ="Are you sure want to delete.";
		if(confirm(msg) == false)				
			return false;

		return true;
	}
	function frmMttrConvMatx_deleteSuccessCallback(result)
	{
		var isMaster=result.successData.isMaster;
		if (isMaster==true){
			alert(result.successData.msg);
			navigateToPrevForm("");
		}
		else{
			alert(result.successData.msg);	
			var intTime =  jQuery("#txtIntExisttime").val();
			var extTime =  jQuery("#txtIntNewtime").val();
			jQuery("#txtIntExisttime").val(intTime -  parseInt(result.successData.existingTime,10));
			jQuery("#txtIntNewtime").val(extTime - parseInt(result.successData.convertedTime,10));
			cleardtlFields();
			calGrandTotal();			
			jQuery("#listdetail").trigger("reloadGrid");
			setFieldValue("txtMttdSlno",result.slNo);
		}	  
	}
	
	function viewGrid()
	{  
		var url="MttrConvMatxDetails_input.ConvMatx";
		var keyId=jQuery('#txtMttrKeyid').val();
		processGridnew(url,"&q=2&mttrkeyid="+keyId,"listdetail","detailpager","","listdetail_docDoubleClick","","loadCompleteDtlGrid");			
		return true;	
	}
	function loadCompleteDtlGrid(){
		setTotalRowCss('listdetail');	
	}
		
	function listdetail_docDoubleClick(id)
	{
		var rowData = jQuery("#listdetail").jqGrid('getRowData',id);
		var keyId=rowData.keyid;
		var convertion=rowData.possibleforconversion;
	//	alert("possibleconverstion"+convertion);
		
		
		if(keyId.trim().length>0){
			var mttrKeyId=jQuery('#txtMttrKeyid').val();
			processAjaxCalls("MttrDtlEntry_input.ConvMatx", "keyId="+ keyId+"&mttrKeyId="+mttrKeyId, 'loadDtlsuccessCallBack','loadDtlErrorCallBack');
		}
		else{
			cleardtlFields();
		}
	}
		
	function loadDtlsuccessCallBack(result){
		//alert(result.plmTlMttrdtl.mttdKeyid);	
		setFieldValue("txtMttdKeyid",result.plmTlMttrdtl.mttdKeyid);	
		setFieldValue("txtMttdSlno",result.plmTlMttrdtl.mttdSlno);
		setFieldValue("txtMttdTask",result.plmTlMttrdtl.mttdTask);
		setFieldValue("txtMttdImprovement",result.plmTlMttrdtl.mttdImprovement);
		setFieldValue("txtMttdPossibleForConv",result.plmTlMttrdtl.mttdPossibleForConv);
		var compagreenate="31-Dec-2100 00:00:00";
		var intFromTime=result.plmTlMttrdtl.mttdItExtFromtime;
		var intToTime=result.plmTlMttrdtl.mttdItExtTotime;
		var extFromTime=result.plmTlMttrdtl.mttdEtExtFromtime;
		var extToTime=result.plmTlMttrdtl.mttdEtExtTotime;
		var newIntFromTime=result.plmTlMttrdtl.mttdItNewFromtime;
		var newIntToTime=result.plmTlMttrdtl.mttdItNewTotime;	
		var newExtFromTime=result.plmTlMttrdtl.mttdEtNewFromtime;
		var newExtToTime=result.plmTlMttrdtl.mttdEtNewTotime;
		var newTotalIntTime = result.plmTlMttrdtl.mttdTotalInternalNewTime;
		 
		if(parseInt(getFieldValue("txtMttrVersion").trim())>1){
			if (newIntFromTime.trim()!=compagreenate){intFromTime=newIntFromTime;}
			if (newIntToTime.trim()!=compagreenate){intToTime=newIntToTime;}
			if (newExtFromTime.trim()!=compagreenate){extFromTime=newExtFromTime;}
			if (newExtToTime.trim()!=compagreenate){extToTime=newExtToTime;}
			newIntFromTime="";
			newIntToTime="";
			newExtFromTime="";
			newExtToTime="";
			newTotalIntTime="";
		}	
		
		setFieldValue("cmbMttdRespoinsibility",result.plmTlMttrdtl.mttdRespoinsibility);
		setFieldValue("dteMttdTargetdate",result.plmTlMttrdtl.mttdTargetdate);
		if (result.plmTlMttrdtl.mttdStatus!="X")
			jQuery("#cmbMttdStatus").combobox("setValue",result.plmTlMttrdtl.mttdStatus);
		//setFieldValue("cmbMttdStatus",result.plmTlMttrdtl.mttdStatus);
		loadTargetCompleted();		 
		setFieldValue("dteMttdCompleteddate",result.plmTlMttrdtl.mttdCompleteddate);
		setFieldValue("cmbMttdCompletedby",result.plmTlMttrdtl.mttdCompletedby);
		setFieldValue("txtMttdItExtFromtime",intFromTime);
		setFieldValue("txtMttdItExtTotime",intToTime);
		setFieldValue("txtMttdEtExtFromtime",extFromTime);
		setFieldValue("txtMttdEtExtTotime",extToTime);
		setFieldValue("txtMttdTotalInternalTime",result.plmTlMttrdtl.mttdTotalInternalTime);
		setFieldValue("txtMttdTotalExternalTime",result.plmTlMttrdtl.mttdTotalExternalTime);
		setFieldValue("txtMttdItNewFromtime",newIntFromTime);
		setFieldValue("txtMttdItNewTotime",newIntToTime);
		//setFieldValue("txtMttdTotalInternalNewTime",result.plmTlMttrdtl.mttdTotalInternalNewTime);		
		setFieldValue("txtMttdTotalInternalNewTime",newTotalIntTime);
		setFieldValue("txtMttdEtNewFromtime",newExtFromTime);
		setFieldValue("txtMttdEtNewTotime",newExtToTime);
		setFieldValue("txtMttdTotalExternalNewTime",result.plmTlMttrdtl.mttdTotalExternalNewTime);	
			
		//getTimeFromDateTime
		setFieldValue("txtPrevIntExisttime",result.plmTlMttrdtl.mttdTotalInternalTime);
		setFieldValue("txtPrevIntNewtime",result.plmTlMttrdtl.mttdTotalInternalNewTime);
		setFieldValue("txtPrevExtExisttime",result.plmTlMttrdtl.mttdTotalExternalTime);
		setFieldValue("txtPrevExtNewtime",result.plmTlMttrdtl.mttdTotalExternalNewTime);
		var hrs=12;
		var mins=15;
		var ss=18;
		var tLen=2;
		
		var strReplace=getReplaceTime(intFromTime);			
		setFieldValue("txtIntFromTimeHrss",intFromTime.substr(hrs,tLen).replace(strReplace,""));	
		setFieldValue("txtIntFromTimeMins",intFromTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtIntFromTimeSecs",intFromTime.substr(ss,tLen).replace(strReplace,""));
		
		strReplace=getReplaceTime(intToTime);	
		setFieldValue("txtIntToTimeHrss",intToTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtIntToTimeMins",intToTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtIntToTimeSecs",intToTime.substr(ss,tLen).replace(strReplace,""));
		
		strReplace=getReplaceTime(extFromTime);	
		setFieldValue("txtExtFromTimeHrss",extFromTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtExtFromTimeMins",extFromTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtExtFromTimeSecs",extFromTime.substr(ss,tLen).replace(strReplace,""));
		
		strReplace=getReplaceTime(extToTime);
		setFieldValue("txtExtToTimeHrss",extToTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtExtToTimeMins",extToTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtExtToTimeSecs",extToTime.substr(ss,tLen).replace(strReplace,""));

		strReplace=getReplaceTime(newIntFromTime);
		setFieldValue("txtIntNewFromTimeHrss",newIntFromTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtIntNewFromTimeMins",newIntFromTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtIntNewFromTimeSecs",newIntFromTime.substr(ss,tLen).replace(strReplace,""));
		
		strReplace=getReplaceTime(newIntToTime);
		setFieldValue("txtIntNewToTimeHrss",newIntToTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtIntNewToTimeMins",newIntToTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtIntNewToTimeSecs",newIntToTime.substr(ss,tLen).replace(strReplace,""));
		
		strReplace=getReplaceTime(newExtFromTime);
		setFieldValue("txtExtNewFromTimeHrss",newExtFromTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtExtNewFromTimeMins",newExtFromTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtExtNewFromTimeSecs",newExtFromTime.substr(ss,tLen).replace(strReplace,""));
		
		strReplace=getReplaceTime(newExtToTime);
		setFieldValue("txtExtNewToTimeHrss",newExtToTime.substr(hrs,tLen).replace(strReplace,""));
		setFieldValue("txtExtNewToTimeMins",newExtToTime.substr(mins,tLen).replace(strReplace,""));
		setFieldValue("txtExtNewToTimeSecs",newExtToTime.substr(ss,tLen).replace(strReplace,""));
		
		setFieldValue("txtMttdIntActtime",result.plmTlMttrdtl.mttdIntActtime);
		setFieldValue("txtMttdExtActtime",result.plmTlMttrdtl.mttdExtActtime);
		setFieldValue("txtMttdIntActNewtime",result.plmTlMttrdtl.mttdIntActNewtime);
		setFieldValue("txtMttdExtActNewtime",result.plmTlMttrdtl.mttdExtActNewtime);
		
		if(result.plmTlMttrdtl.mttdPossibleForConv.trim()=="Y")
			enableMttrDtls();
		else{
			disableMttrDtls();	
			
			       // alert(123);
			  
			    	 var intfromhrs=jQuery("#txtIntFromTimeHrss").val();	
			    	 var intfrommins=jQuery("#txtIntFromTimeMins").val();	
			    	 var intfromTimeSecs=jQuery("#txtIntFromTimeSecs").val();	
			    	 var inttofromhrs=jQuery("#txtIntToTimeHrss").val();	
			    	 var inttofrmmins=jQuery("#txtIntToTimeMins").val();	
			    	 var inttofrmsec=jQuery("#txtIntToTimeSecs").val();	
			    	 
			    	 
			    	 var extfromhrs=jQuery("#txtExtFromTimeHrss").val();
			    	 var extfrommins=jQuery("#txtExtFromTimeMins").val();	
			    	 var extfromTimeSecs=jQuery("#txtExtFromTimeSecs").val();	
			    	 var exttofromhrs=jQuery("#txtExtToTimeHrss").val();	
			    	 var exttofrmmins=jQuery("#txtExtToTimeMins").val();	
			    	 var exttofrmsec=jQuery("#txtExtToTimeSecs").val();	
			    	
			    	 
			    	jQuery("#txtIntNewFromTimeHrss").val(intfromhrs);	
			    	 jQuery("#txtIntNewFromTimeMins").val(intfrommins);	
			    	 jQuery("#txtIntNewFromTimeSecs").val(intfromTimeSecs);	
			    	jQuery("#txtIntNewToTimeHrss").val(inttofromhrs);	
			    	 jQuery("#txtIntNewToTimeMins").val(inttofrmmins);	
			    	 jQuery("#txtIntNewToTimeSecs").val(inttofrmsec);	
			    	 
			    	 
			    	jQuery("#txtExtNewFromTimeHrss").val(extfromhrs);
			    	 jQuery("#txtExtNewFromTimeMins").val(extfrommins);	
			    	 jQuery("#txtExtNewFromTimeSecs").val(extfromTimeSecs);	
			    	jQuery("#txtExtNewToTimeHrss").val(exttofromhrs);	
			    	jQuery("#txtExtNewToTimeMins").val(exttofrmmins);	
			    	 jQuery("#txtExtNewToTimeSecs").val(exttofrmsec);	
			    	 
			    	
			    	 
			    	 jQuery("#txtMttdTotalInternalNewTime").val(jQuery("#txtMttdTotalInternalTime").val());
			    	 jQuery("#txtMttdTotalExternalNewTime").val(jQuery("#txtMttdTotalExternalTime").val());
			    	 
			    	 
			    	
			    	
			    
			
		}
		
	}
	function getReplaceTime(strTime){
		var  strReplace="00";
		var datePos=0;
		var dateLen=11;
		var compagreenate="31-Dec-2100";
		/*if(parseInt(strTime.substr(hrs,tLen).trim())>0 || parseInt(strTime.substr(mins,tLen).trim())>0 
		|| parseInt(strTime.substr(ss,tLen).trim())>0   ){strReplace="";}
		else{strReplace="00";}
		*/
		var strReplaceDate=strTime.substr(datePos,dateLen);	
		if(strReplaceDate.replace(compagreenate,"").trim().length>0){
			strReplace="";
		}		
		return strReplace;
	}

	function loadDtlErrorCallBack(result){
	}
	
	function ConvertSecsToMinsTime(){
		//alert("ConvertSecsToMinsTime");
		var existingTime=getFieldValue("txtMttrExistingTime");			
		existingTime=existingTime|0;
		if (parseInt(existingTime)>=0){
			existingTime=Math.round(parseInt(existingTime)/60);
			//alert("existingTime"+existingTime);
			setFieldValue("txtExistIntTimeMins",existingTime);
		}
		
		var conTime=getFieldValue("txtMttrConvertedTime");		
		conTime=conTime|0;
		if (parseInt(conTime)>=0){
			conTime=Math.round(parseInt(conTime)/60);
			//alert("conTime"+conTime);
			setFieldValue("txtConExtTimeMins",conTime);
		}
		
		var savings=getFieldValue("txtMttrSavingTime");		
		savings=savings|0;
		if (parseInt(savings)>=0){
			savings=Math.round(parseInt(savings)/60);
			//alert("existingTime"+conExtTime);
			setFieldValue("txtSavingsMins",savings);
			//alert("ConvertSecsToMinsTimeend");
		}

		var existingExtTime=getFieldValue("txtMttrExtExistingTime");			
		existingExtTime=existingExtTime|0;
		if (parseInt(existingExtTime)>=0){
			existingExtTime=Math.round(parseInt(existingExtTime)/60);
			//alert("existingExtTime"+existingExtTime);
			setFieldValue("txtExtExistTimeMins",existingExtTime);
		}
		
		var conExtTime=getFieldValue("txtMttrExtConvertedTime");		
		conExtTime=conExtTime|0;
		if (parseInt(conExtTime)>=0){
			conExtTime=Math.round(parseInt(conExtTime)/60);
			//alert("conExtTime"+conExtTime);
			setFieldValue("txtExtConExtTimeMins",conExtTime);
		}
		
		var savingsExt=getFieldValue("txtMttrExtSavingTime");
		//alert("savingsExt"+savingsExt);		
		savingsExt=savingsExt|0;
		if (parseInt(savingsExt)>=0){
			savings=Math.round(parseInt(savingsExt)/60);
			//alert("savingsExt"+savingsExt);
			setFieldValue("txtExtSavingsMins",savings);
			//alert("ConvertSecsToMinsTimeend");
		}
	}
	
	function getVersionNo(){		
		//alert("getVersionNo");
		var mode=getFieldValue("mode");
		if(mode.trim()=="create"){
			var flid=jQuery("#frmMttrConvMatx input[id='flid']").val();
			var machineid=getFieldValue("cmbMttrMachineid");
			var process=getFieldValue("txtMttrProcess");
			//alert("flid"+flid+"machineid"+machineid+"process"+process);
			if (flid.trim().length>0 && machineid.trim().length>0 && process.trim().length>0){
				processAjaxCalls("getVersionNo.ConvMatx", "flid="+ flid+"&machineid="+machineid+"&process="+process, 'versionNosuccessCallBack','versionNoErrorCallBack');
			}
		}
	}
	
	function versionNosuccessCallBack(result){
		//alert(result.isPending);
		//alert(result.versionNo);
		//alert(result.plmTlMttrmst.mttrKeyid);
		var keyId =result.plmTlMttrmst.mttrKeyid;
		setFieldValue("txtMttrVersion",result.versionNo);
		if(result.isPending && keyId.trim().length>0){		
			setFieldValue("txtMttrKeyid",keyId);
			setFieldValue("mode","modify");
			setFieldValue("cmbMttrTradeid",result.plmTlMttrmst.mttrTradeid);
			setFieldValue("txtMttrDescription",result.plmTlMttrmst.mttrDescription);
			setFieldValue("txtMttrStdsetuptime",result.plmTlMttrmst.mttrStdsetuptime);
			setFieldValue("cmbMttrStatus",result.plmTlMttrmst.mttrStatus);
			setFieldValue("txtMttrExistingTime",result.plmTlMttrmst.mttrExistingTime);
			setFieldValue("txtMttrConvertedTime",result.plmTlMttrmst.mttrConvertedTime);
			setFieldValue("txtMttrSavingTime",result.plmTlMttrmst.mttrSavingTime);
			setFieldValue("txtMttrNewExistingTime",result.plmTlMttrmst.mttrNewExistingTime);
			setFieldValue("txtMttrNewConvertedTime",result.plmTlMttrmst.mttrNewConvertedTime);
			setFieldValue("txtMttrNewSavingTime",result.plmTlMttrmst.mttrNewSavingTime);
			var dateStr = result.plmTlMttrmst.mttrDate.trim() ;
			
			if( dateStr.length > 11)
				dateStr = dateStr.substring(0,11);
			
			setFieldValue("dteMttrDate",dateStr );
			setFieldValue("txtMttdSlno",result.slNo);
			setFieldValue("txtIntExisttime",result.plmTlMttrmst.mttrExistingTime );
			setFieldValue("txtIntNewtime",result.plmTlMttrmst.mttrConvertedTime);
			setFieldValue("txtExtExisttime",result.plmTlMttrmst.mttrNewExistingTime );
			setFieldValue("txtExtNewtime",result.plmTlMttrmst.mttrNewConvertedTime);
			viewGrid();
		}
	}
	
	function versionNoErrorCallBack(){		
	}
	function getSlNo(){		
		//alert("getSlNo");		
		var mttrKeyId =jQuery("#txtMttrKeyid").val();
		if (mttrKeyId.trim().length>0 ){
			processAjaxCalls("getSlNo.ConvMatx", "keyId="+ mttrKeyId, 'slNosuccessCallBack','slNoErrorCallBack');
		}		
	}
	function slNosuccessCallBack(result){	
		//alert(result.slNo);	
		setFieldValue("txtMttdSlno",result.slNo);
	}
	function slNoErrorCallBack(){		
	}
	function isValidMttrDate(){
		
		var DateCtrl="dteMttrDate";
		var mttrDate = getFieldValue(DateCtrl);
		var currentDate =  getServerDateTime();
		if (mttrDate==undefined || mttrDate=="" || mttrDate==" ") {  
			
			if(convertStringToDate(mttrDate) > currentDate)
			{
				showComErrMsg('Should Not Exceed Current Date');
				fillWithCurrentDate(DateCtrl);
				return false;
			}
			else{
			    clearValidationErrorMsg(DateCtrl);
		    	return false;
			}
		}
		else {
			if(convertStringToDate(mttrDate) >currentDate )
			{
				showComErrMsg('Should Not Greater Than Current Date');
				fillWithCurrentDate(DateCtrl);
				return false;
			}
		}
	}
	
	function isValidTargetDate(){
		var mttrDate = getFieldValue("dteMttrDate");
		var DateCtrl="dteMttdTargetdate";
		var targetDate = getFieldValue(DateCtrl);
		var currentDate =  getServerDateTime();
		if (targetDate==undefined || targetDate=="" || targetDate==" ") { 
		    clearValidationErrorMsg(DateCtrl);
	    	return false;
		}
		else{
			if(convertStringToDate(targetDate) < convertStringToDate(mttrDate) )
			{
				showComErrMsg('Target Date Should Not Less Than Entry Date');
				fillWithCurrentDate(DateCtrl);
				return false;
			}
		}			
	}
	
	function isValidCompletedDate(){
		var mttrDate = getFieldValue("dteMttrDate");
		var DateCtrl="dteMttdCompleteddate";		
		var CompletedDate = getFieldValue(DateCtrl);
		var currentDate =  getServerDateTime();
		if (CompletedDate==undefined || CompletedDate=="" || CompletedDate==" ") {  
			if(convertStringToDate(CompletedDate) > currentDate)
			{
				showComErrMsg('Should Not Exceed Current Date');
				fillWithCurrentDate(DateCtrl);
				return false;
			}
			else{
			    clearValidationErrorMsg(DateCtrl);
		    	return false;
			}
		}
		else{
			if(convertStringToDate(CompletedDate) > currentDate )
			{
				showComErrMsg('Should Not Greater Than Current Date');
				fillWithCurrentDate(DateCtrl);
				return false;
			}			
			else if(convertStringToDate(CompletedDate) < convertStringToDate(mttrDate) )
			{
				showComErrMsg('Completed Date Should Not Less Than Entry Date');
				fillWithCurrentDate(DateCtrl);
				return false;
			}
		}			
	}

	function getCurrentRowIndex(rowIDs, detailId){
		for(var i =0; i < rowIDs.length - 1 ;i++){
			var tDetailId = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"keyid");
			if( tDetailId == detailId )
				return i;			
		}	
		return null;
	}
	function converToSeconds(hhmiss){
		var tiArr = hhmiss.split(":");
		var seconds = 60 * 60 * parseInt(tiArr[0],10) + 60 * parseInt(tiArr[1],10)+parseInt(tiArr[2],10);
		return seconds;		 
	}
	function showComErrMsg(msg){
		setTimeout(function() {
			showCommonErrorMsg(msg);
		}, 200);
		div_err();
	}
	/*
	function getArrray(type){
		//alert("getArrray");
		var jqGridId="listdetail";
		var rowIDs = jQuery("#"+jqGridId).jqGrid('getRowData');	
		var jsonObj = [];
		//alert("getArrray1");
		for(var i =0; i < rowIDs.length-1 ;i++){
			var row = rowIDs[i];
			//alert("row"+row);
			var keyid =parseJqGridCellValue(row["keyid"]);
			var itextfromtime =mttrDate+" "+parseJqGridCellValue(row["itextfromtime"]);
			var itexttotime =mttrDate+" "+parseJqGridCellValue(row["itexttotime"]);
			var etextfromtime =mttrDate+" "+parseJqGridCellValue(row["etextfromtime"]);
			var etexttotime =mttrDate+" "+parseJqGridCellValue(row["etexttotime"]);
			alert("keyid"+keyid);
			if (type=="old"){
				itextfromtime = mttrDate+" "+parseJqGridCellValue(row["itnewfromtime"]);
				itexttotime = mttrDate+" "+parseJqGridCellValue(row["itnewtotime"]);
				etextfromtime = mttrDate+" "+parseJqGridCellValue(row["etnewfromtime"]);
				etexttotime = mttrDate+" "+parseJqGridCellValue(row["etnewtotime"]);				
			}
			
			var possibleforconversion = parseJqGridCellValue(row["possibleforconversion"]);
			if(convertStringToDate(itextfromtime) < convertStringToDate(etextfromtime))
			{
				alert("itextfromtime <etextfromtime");
		        item = {};
		        item ["rowid"] = row;
		        item ["keyid"] = keyid;
		        item ["fromtime"] = itextfromtime;
		        item ["totime"] = itexttotime;
		        item ["type"] = "int";
		        jsonObj.push(item);	

		        item = {};
		        item ["rowid"] = row;
		        item ["keyid"] = keyid;
		        item ["fromtime"] = etextfromtime;
		        item ["totime"] = etexttotime;
		        item ["type"] = "ext";
		        jsonObj.push(item);	
			}
			else{
				item = {};
		        item ["rowid"] = row;
		        item ["keyid"] = keyid;
		        item ["fromtime"] = etextfromtime;
		        item ["totime"] = etexttotime;
		        item ["type"] = "ext";
		        jsonObj.push(item);	

		        item = {};
		        item ["rowid"] = row;
		        item ["keyid"] = keyid;
		        item ["fromtime"] = itextfromtime;
		        item ["totime"] = itexttotime;
		        item ["type"] = "int";
		        jsonObj.push(item);					
			}   	   			
		}
		alert("json"+jsonObj);
	}
	*/
	
	function calculateAcutalTotalTime(detailId,frmHrsId,frmMinsId,frmSsId,toHrsId,toMinsId,toSsId, grdFTimColIndex,grdToTimColIndx,totalTxtid,actualFrmTimId, grdTotalColIndex,grdActlFrmColIndx ){
		var chkFromTime = jQuery("#"+frmHrsId).val() +":"+jQuery("#"+frmMinsId).val() + ":"+jQuery("#"+frmSsId).val() ;
  	  	var chkToTime = jQuery("#"+toHrsId).val() +":"+jQuery("#"+toMinsId).val() + ":"+jQuery("#"+toSsId).val() ;
		var dateVal = getFieldValue("dteMttrDate");  
		
		var rowIDs = jQuery("#listdetail").jqGrid('getDataIDs');
		var chkFrmTimeScnd = converToSeconds(chkFromTime);
		var chkToTimeScnd = converToSeconds(chkToTime);
		if( rowIDs.length > 0 ){
			var cuIndx = getCurrentRowIndex(rowIDs, detailId) ;
			var prevIndx = rowIDs.length - 2; 
			
			//var nextIndex = -1;
			if(cuIndx != null )
			{
				prevIndx = cuIndx -1;
				if( cuIndx < rowIDs.length )
					nextIndex = cuIndx + 1; 			
			}	
			if( prevIndx >= 0 ){
				
				var prvFromTime = jQuery("#listdetail").jqGrid('getCell', rowIDs[prevIndx],grdFTimColIndex);
					
				var prvFrmTimeScnd = converToSeconds(prvFromTime);
				
				if( chkFrmTimeScnd < prvFrmTimeScnd )
				{
					//alert(" From time can not be less than Previous Task/operation's From time ");
					showComErrMsg(" From time can not be less than Previous Task/operation's From time ");					
					jQuery("#"+frmHrsId).val('');
					jQuery("#"+frmMinsId).val('');
					jQuery("#"+frmSsId).val('');
					showValidErrMsg(frmHrsId,"");
					showValidErrMsg(frmMinsId,"");
					showValidErrMsg(frmSsId,"");	
					setFocusOnField(frmHrsId);	
					return ;
				}		
			}
			
			for(var i =0; i < rowIDs.length-1 ;i++){
				var tDetailId = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],"keyid");
				if( tDetailId != detailId ){
					var tFromTime = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],grdFTimColIndex);
					var tToTime = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],grdToTimColIndx);
					
					var tFromTimeScnd = converToSeconds( tFromTime);
					var tToTimeScnd = converToSeconds( tToTime);
					if(  tFromTimeScnd <= chkFrmTimeScnd && chkFrmTimeScnd < tToTimeScnd &&  tToTimeScnd < chkToTimeScnd   )	
					{										
						//var actualFrmTime = chkFrmTimeScnd ;
						//var totalTimeScnd =  chkFrmTimeScnd - tFromTimeScnd;
						//jQuery("#listdetail").jqGrid('setCell', rowIDs[i],grdActlFrmColIndx,actualFrmTime);
						//jQuery("#listdetail").jqGrid('setCell', rowIDs[i],grdTotalColIndex,totalTimeScnd);
						//jQuery("#listdetail").jqGrid('setCell', rowIDs[i],"updateneed","Y");
 
						var totalTimeScnd =  chkToTimeScnd - tToTimeScnd;
						
						jQuery("#"+totalTxtid).val(totalTimeScnd);	
						jQuery("#"+actualFrmTimId).val(dateVal + " "+ tToTime); 
					}
					else if(tFromTimeScnd <= chkFrmTimeScnd && chkFrmTimeScnd < tToTimeScnd &&  chkToTimeScnd <= tToTimeScnd ){
						jQuery("#"+totalTxtid).val("0");	
						jQuery("#"+actualFrmTimId).val(dateVal + " "+chkFromTime);
					}
					else if(  chkFrmTimeScnd <= tFromTimeScnd  &&  tToTimeScnd <= chkToTimeScnd  ){

						if( "totalinternaltime" == grdTotalColIndex){
							var totalEtime = jQuery("#txtIntExisttime").val();
							var total = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],grdTotalColIndex);
							totalEtime -= total;
							jQuery("#txtIntExisttime").val(totalEtime);
							calGrandTotal();	 
						}
						else if( "totalexternaltotime" == grdTotalColIndex){
							var totalEtime = jQuery("#txtIntNewtime").val();
							var total = jQuery("#listdetail").jqGrid('getCell', rowIDs[i],grdTotalColIndex);
							totalEtime -= total;
							jQuery("#txtIntNewtime").val(totalEtime);
							calGrandTotal();	 
						}
						
							
						jQuery("#listdetail").jqGrid('setCell', rowIDs[i],grdActlFrmColIndx,tFromTime);
						jQuery("#listdetail").jqGrid('setCell', rowIDs[i],grdTotalColIndex,"0");
						jQuery("#listdetail").jqGrid('setCell', rowIDs[i],"updateneed","Y");
						
					}	
					
				/*	if(convertStringToDateMttr(tFromTime) > chkFromTime &&  chkFromTime <  convertStringToDateMttr(tFromTime) 
							&&  chkToTime >  convertStringToDateMttr(tToTime)   ){
	
						var oDiff =getTimeDifference(tToTime,chkToTime);
						var secs =oDiff.seconds | 0;
						
						jQuery("#"+totalTxtid).val(secs);
						
					}
				*/				
				}
			}
		
		}		
	}

	
 </script>

<form name="frmMttrConvMatx" id="frmMttrConvMatx" action="" method="post">
<div id="wrapper" >
		<table  style="padding-left:0% ; width: 90%;">
			<tr>
				<td colspan="2"  valign="top">
					<div>
						<div  id="frmMttrConvMatxFuntKeyIds">
							<div style="float: left;">
								<input type="hidden" id="factId" name="factId" value=""  ></input>
								<input type="hidden" id="sectId" name="sectId" value=""  ></input>
								<input type="hidden" id="cellId" name="cellId" value=""  ></input>
								<input type="hidden" id="machine" name="cmbMttrMachineid1" value="${requestScope.PlmTlMttrmst.mttrMachineid}"  ></input>
								<input type="hidden" id="flid" name="cmbMttrFlid" value="${requestScope.PlmTlMttrmst.mttrFlid}"  />
								
							</div>
							
							<div class="" style="width:97%;width:97%\9;">
								<div id="ctfxfunLocation" style="width: 96%; display: block;" tabindex="0">
									<div id="frmMttrConvMatxctfxfunLocationValues">
									<div id="dispFunctionalLoc" class="easyui-paddingbfpx">
									</div>
									</div>
								</div>
								<div id="txtFct" class="tpm-errormsg" style=""/></div>
								<div id="err_err_cell" class="tpm-errormsg" style=""/></div>
							</div>
							<div class="clear"></div>
						</div>	
					</div>				
				</td>
				<td colspan="1"  valign="top">
					<div><span><label >Date</label></span><span style="padding-left:84px;"><label>Version No.</label></span></div>
					<div class="easyui-paddingbfpx" >
				 		<span>
				 			<input id="dteMttrDate"  name="dteMttrDate"  clear="false" class="easyui-datebox" value="${requestScope.PlmTlMttrmst.mttrDate}"  tabindex="1" style="width: 105px;" />
				 		</span>
				 		<span  style="padding-left: 2px;">
				 			<input  id="txtMttrVersion" name="txtMttrVersion" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrVersion}" tabindex="2" style="width: 30px;" maxlength="8" >
				 		</span>
				 		<span style="padding-left:10px;margin-top:20px;position:relative;">
							 <span id="mttrFilemgr" style="position:absolute;top:-4px;"></span>
		             	</span>
		             	
		             	<span style="position:absolute;padding-left:150px;margin-top:0px;">
								<input type="button" class="easyui-button" value ="Excel View" id="btnXlsView" style="height:23px;"/>	
						</span>	
				 	</div>	
				</td>
			</tr>
			
				<tr>
					<td  valign="top">
						<div><label  class="mandatory-lbl">Process</label></div>				
						<div class="easyui-paddingbfpx">
							<input id="txtMttrProcess" name="txtMttrProcess" tabindex="3" class="easyui-text"   style="width:260px;" value="${requestScope.PlmTlMttrmst.mttrProcess}" >							
						</div>
						<div ><label  class="mandatory-lbl">Equipment</label></div>				
						<div class="easyui-paddingbfpx">
							<input id="cmbMttrMachineid" name="cmbMttrMachineid"  tabindex="4" class="easyui-combobox easyui-combo"   style="width:260px;" value="${requestScope.PlmTlMttrmst.mttrMachineid}" >							
						</div>
						<div><label >Trade</label></div>				
						<div class="easyui-paddingbfpx">
							<input id="cmbMttrTradeid" name="cmbMttrTradeid" tabindex="5" class="easyui-combobox"   style="width:260px;" value="${requestScope.PlmTlMttrmst.mttrTradeid}" >							
						</div>						
					</td>
					<td   valign="top" style="padding-left: 5px;">						
						<div ><label class="mandatory-lbl">MTTR/ SMED Description</label></div>				
						<div class="easyui-paddingbfpx">
							<textarea rows="2" cols="40" id="txtMttrDescription" tabindex="6" name="txtMttrDescription"  maxlength="480" style="width:300px;">${requestScope.PlmTlMttrmst.mttrDescription}</textarea>							
						</div>									
						<div><span><label>Standard Setup Time (mins)</label></span><span style="padding-left:24px;"><label>Status</label></span></div>
						<div class="easyui-paddingbfpx" >								
						 	<span>
						 		<input  id="txtMttrStdsetuptime" name="txtMttrStdsetuptime" tabindex="7" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrStdsetuptime}"  style="width: 100px;" maxlength="10">
							</span>
						 	<span style="padding-left: 75px;">
						 		<input id="cmbMttrStatus" name="cmbMttrStatus" tabindex="8" class="easyui-combobox" 
								 style="width:100px;" value="${requestScope.PlmTlMttrmst.mttrStatus}" <c:out value = "${ requestScope.conversionMatrixBean.disableForm == true ? ' disabled':''}"/> >
						 	</span>
						</div>	
											
					</td>
					
					<%-- <td  valign="top" style="padding-left: 0px; ">
						<div><span><label style="color: blue;">Existing Internal Time</label></span><span style="padding-left:67px;"><label style="color: blue;">Existing External Time</label></span></div>				
						<div class="easyui-paddingbfpx" >	
							<span>
						 		<input  id="txtMttrExistingTime" name="txtMttrExistingTime" tabindex="9" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExistingTime}"  style="width: 60px;text-align:right;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExistIntTimeMins" name="txtExistIntTimeMins" tabindex="10" class="easyui-text" value=""  style="width: 40px;text-align:right;" maxlength="8"> 
						 		<label>(Mins)</label>
						 	</span>
						 	<span>
						 		<input  id="txtMttrExtExistingTime" name="txtMttrExtExistingTime" tabindex="9" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExtExistingTime}"  style="width: 60px;text-align:right;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExtExistTimeMins" name="txtExtExistTimeMins" tabindex="10" class="easyui-text" value=""  style="width: 40px;text-align:right;" maxlength="8"> 
						 		<label>(Mins)</label>
						 	</span>	
						 	<span style="position:absolute;padding-left:7px;margin-top:0px;">
								<input type="button" class="easyui-button" value ="Excel View" id="btnXlsView" style="height:23px;"/>	
							</span>
						</div>									
						<div style="padding-top: 10px;"><span><label style="color: blue;">Improved Internal Time </label></span><span style="padding-left:56px;"><label style="color: blue;">Improved External Time </label></span></div>
						<div class="easyui-paddingbfpx" >								
						 	<span>
						 		<input  id="txtMttrConvertedTime" name="txtMttrConvertedTime" tabindex="11" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrConvertedTime}"  style="width: 60px;text-align:right;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>
						 	<span style="padding-left: 0px;">
						 		<input  id="txtConExtTimeMins" name="txtConExtTimeMins" tabindex="12" class="easyui-text" value=""  style="width: 40px;text-align:right;" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>	
						 	<span>
						 		<input  id="txtMttrExtConvertedTime" name="txtMttrExtConvertedTime" tabindex="11" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExtConvertedTime}"  style="width: 60px;text-align:right;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExtConExtTimeMins" name="txtExtConExtTimeMins" tabindex="12" class="easyui-text" value=""  style="width: 40px;text-align:right;" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>
						</div>	
						<div ><span><label  style="color: blue;">Internal Savings</label></span><span style="padding-left:100px;"><label  style="color: blue;">External Savings</label></span></div>				
						<div class="easyui-paddingbfpx">
							<span>
						 		<input  id="txtMttrSavingTime" name="txtMttrSavingTime" tabindex="13" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrSavingTime}"  style="width: 60px;text-align:right;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtSavingsMins" name="txtSavingsMins" tabindex="14" class="easyui-text" value=""  style="width: 40px;text-align:right;" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>	
						 	<span>
						 		<input  id="txtMttrExtSavingTime" name="txtMttrExtSavingTime" tabindex="13" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrSavingTime}"  style="width: 60px;text-align:right;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExtSavingsMins" name="txtExtSavingsMins" tabindex="14" class="easyui-text" value=""  style="width: 40px;text-align:right;" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>								
						</div>		
					</td>
					 --%>
					 
					<td style="padding-left: 20px;" width="50%">
					<table style='border: 1px solid black;width: 110%;' >
						<tr > 
						 <th style="width: 120px;height:30px;  border: 1px solid black;background-color: green;color: white;"> Time </th>
						 <th style="width: 520px;height:30px; border: 1px solid black;background-color: green;color: white;"> Internal  </th>
						 <th style="width: 520px;height:30px; border: 1px solid black;background-color: green;color: white;"> External  </th>
						</tr>
						
						<tr >  
						 <td style="width: 120px;height:30px; border: 1px solid black;background-color: green;color: white;padding-left: 5px;"> Existing </td>
						 <td style="width: 520px;height:30px; border: 1px solid black;background-color: transparent;color: black;">  
						 <span style="padding-left: 5px;">
						 		<input  id="txtMttrExistingTime" name="txtMttrExistingTime" tabindex="9" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExistingTime}"  style="width: 60px;text-align:right;color: blue;font-weight: bold;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>
						 	 	<span style="padding-left: 0px;">
						 		<input  id="txtExistIntTimeMins" name="txtExistIntTimeMins" tabindex="10" class="easyui-text" value=""  style="width: 40px;text-align:right;color: blue;font-weight: bold" maxlength="8"> 
						 		<label>(Mins)</label>
						 	</span>
						 </td>
						 <td style="width: 520px;height:30px; border: 1px solid black;background-color: transparent;color: black;">   
						 	<span style="padding-left: 5px;">
						 		<input  id="txtMttrExtExistingTime" name="txtMttrExtExistingTime" tabindex="9" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExtExistingTime}"  style="width: 60px;text-align:right;color: blue;font-weight: bold" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExtExistTimeMins" name="txtExtExistTimeMins" tabindex="10" class="easyui-text" value=""  style="width: 40px;text-align:right;color: blue;font-weight: bold" maxlength="8"> 
						 		<label>(Mins)</label>
						 	</span>	
						 	
						 </td>
						</tr>
						
						<tr >  
						 <td style="width: 120px;height:30px; border: 1px solid black;background-color: green ;color: white;padding-left: 5px;"> Improved</td>
						 <td style="width: 520px;height:30px; border: 1px solid black;background-color: transparent;color: black;">
						 	<span style="padding-left: 5px;">
						 		<input  id="txtMttrConvertedTime" name="txtMttrConvertedTime" tabindex="11" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrConvertedTime}"  style="width: 60px;text-align:right;color: blue;font-weight: bold" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>
						 	<span style="padding-left: 0px;">
						 		<input  id="txtConExtTimeMins" name="txtConExtTimeMins" tabindex="12" class="easyui-text" value=""  style="width: 40px;text-align:right;color: blue;font-weight: bold" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>	
						 
						  </td>
						 <td style="width: 520px;height:30px; border: 1px solid black;background-color: transparent;color: black;">
						 	<span style="padding-left: 5px;">
						 		<input  id="txtMttrExtConvertedTime" name="txtMttrExtConvertedTime" tabindex="11" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExtConvertedTime}"  style="width: 60px;text-align:right;color: blue;font-weight: bold" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExtConExtTimeMins" name="txtExtConExtTimeMins" tabindex="12" class="easyui-text" value=""  style="width: 40px;text-align:right;color: blue;font-weight: bold" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>
						  </td>
						</tr>
						
						<tr >  
						 <td style="width: 100px;height:30px; border: 1px solid black;background-color: green;color: white;padding-left: 5px;"> Saving </td>
						 <td style="width: 520px;height:30px; border: 1px solid black;background-color: transparent;color: colorblue;;">
						 	<span style="padding-left: 5px;">
						 		<input  id="txtMttrSavingTime" name="txtMttrSavingTime" tabindex="13" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrSavingTime}"  style="width: 60px;text-align:right;color: blue;font-weight: bold;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtSavingsMins" name="txtSavingsMins" tabindex="14" class="easyui-text" value=""  style="width: 40px;text-align:right;color: blue;font-weight: bold" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>	
						 </td>
						 <td style="width: 520px;height:30px; border: 1px solid black;background-color: transparent;color: black;">
						 	<span style="padding-left: 5px;">
						 		<input  id="txtMttrExtSavingTime" name="txtMttrExtSavingTime" tabindex="13" class="easyui-text" value="${requestScope.PlmTlMttrmst.mttrExtSavingTime}"  style="width: 60px;text-align:right;color: blue;font-weight: bold;" maxlength="8" > 
						 		<label>(Secs)</label>
						 	</span>	
						 	<span style="padding-left: 0px;">
						 		<input  id="txtExtSavingsMins" name="txtExtSavingsMins" tabindex="14" class="easyui-text" value=""  style="width: 40px;text-align:right;color: blue;font-weight: bold;" maxlength="8" > 
						 		<label>(Mins)</label>
						 	</span>  
						 </td>
						</tr>
					</table>
					</td>	
				</tr>
			</table>
		<div style="padding-left:0%;">
			<div id="actionPlanSubHdr" class="sub-header" style="text-align: left;height:18px;width:93.5%;width:83.5%\9;"><span  style="text-align:left;vertical-align:top;">Details</span>
				<span style="padding-left: 300px;">
					<label class="mandatory-lbl" > Either Internal Times or External Times is mandatory </label>
				</span>	
			</div>
			<table  style="padding-left:0% ;">
				<tr>
					<td valign="top">
						<div><label  class="mandatory-lbl">SL No.</label></div>				
						<div class="easyui-paddingbfpx">
							<input  id="txtMttdSlno" name="txtMttdSlno" tabindex="15" class="easyui-text" value="${requestScope.slNo}"  style="width: 60px;" maxlength="25" >							
						</div>
						<div><label  class="mandatory-lbl">Task/ Operation</label></div>				
						<div class="easyui-paddingbfpx">
							<textarea rows="2" cols="40" id="txtMttdTask" tabindex="16"	name="txtMttdTask"  maxlength="480" style="width:255px;"></textarea>							
						</div>	
						<div><label id="lblImprov">Improvement Details</label></div>
						<div class="easyui-paddingbfpx" >
					 		<textarea rows="2" cols="40" id="txtMttdImprovement"	name="txtMttdImprovement" tabindex="31"  maxlength="480" style="width:255px;"></textarea>
					 	</div>								
					</td>
					<td valign="top" style="padding-left: 75px;">						
						<div class="easyui-paddingbfpx">

							<span style="padding-left: 175px;">
								<span ><label>Hrs</label></span>
								<span style="padding-left: 41px;"><label >Mins</label></span>
								<span style="padding-left: 34px;"><label>Secs</label></span>
							</span>
						</div>		
						
						<div class="easyui-paddingbfpx">
							<span>
								<span>&nbsp;&nbsp;<label class="">Existing From Time(Internal)</label></span>		
								<span >					
									<input  id="txtIntFromTimeHrss" name="txtIntFromTimeHrss" tabindex="17" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtIntFromTimeMins" name="txtIntFromTimeMins" tabindex="18" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtIntFromTimeSecs" name="txtIntFromTimeSecs" tabindex="19" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>
								<span ><label style="color: blue;">Total Int. Time</label></span>
								<span id="err_txtIntFromTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtIntFromTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtIntFromTimeSecs" class="tpm-errormsg"></span>
							</span>
						</div>	
						<div class="easyui-paddingbfpx" >
							<span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="">Existing To Time(Internal)</label></span>	
								<span >	
									<input  id="txtIntToTimeHrss" name="txtIntToTimeHrss" tabindex="20" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>
								<span>					
									<input  id="txtIntToTimeMins" name="txtIntToTimeMins" tabindex="21" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>	
								<span>					
									<input  id="txtIntToTimeSecs" name="txtIntToTimeSecs" tabindex="22" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>
								<span >					
									<input  id="txtMttdTotalInternalTime" name="txtMttdTotalInternalTime" class="easyui-text" value=""  style="width: 60px;text-align:right;" maxlength="2" >
								</span> 
								<span id="err_txtIntToTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtIntToTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtIntToTimeSecs" class="tpm-errormsg"></span>
							</span> 
						</div>
						<div class="easyui-paddingbfpx" >
							
								<span>&nbsp;<label class="">Existing From Time(External)</label></span>		
								<span >					
									<input  id="txtExtFromTimeHrss" name="txtExtFromTimeHrss" tabindex="23" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtExtFromTimeMins" name="txtExtFromTimeMins" tabindex="24" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtExtFromTimeSecs" name="txtExtFromTimeSecs" tabindex="25" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span ><label style="color: blue;">Total Ext. Time</label></span>
								<span id="err_txtExtFromTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtExtFromTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtExtFromTimeSecs" class="tpm-errormsg"></span>
							</span> 
						</div>
						<div class="easyui-paddingbfpx" >
							<span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="">Existing To Time(External)</label></span>		
								<span >					
									<input  id="txtExtToTimeHrss" name="txtExtToTimeHrss" tabindex="26" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>	
								<span>					
									<input  id="txtExtToTimeMins" name="txtExtToTimeMins" tabindex="27" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>	
								<span>					
									<input  id="txtExtToTimeSecs" name="txtExtToTimeSecs" tabindex="28" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>	
								<span>					
									<input  id="txtMttdTotalExternalTime" name="txtMttdTotalExternalTime" class="easyui-text" value=""  style="width: 60px;text-align:right;" maxlength="2" >
								</span>	
								<span id="err_txtExtToTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtExtToTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtExtToTimeSecs" class="tpm-errormsg"></span>	
							</span> 											
						</div>									
						<div class="easyui-paddingbfpx">
							<span style="padding-left: 175px; padding-top:15px;">
							<span ><label>Hrs</label></span>
							<span style="padding-left: 41px;"><label>Mins</label></span>
							<span style="padding-left: 34px;" ><label>Secs</label></span>
							</span>
						</div>	
						<div class="easyui-paddingbfpx">
							<span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="lblIntNewFrom">New From Time(Internal)</label></span>		
								<span >					
									<input  id="txtIntNewFromTimeHrss" name="txtIntNewFromTimeHrss" class="easyui-text" value="" tabindex="37"  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtIntNewFromTimeMins" name="txtIntNewFromTimeMins" tabindex="38" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtIntNewFromTimeSecs" name="txtIntNewFromTimeSecs" tabindex="39" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span><label style="color: blue;">Total Int. Time</label></span>
								<span id="err_txtIntNewFromTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtIntNewFromTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtIntNewFromTimeSecs" class="tpm-errormsg"></span>	
							</span>
						</div>	
						<div class="easyui-paddingbfpx" >
							<span >
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="lblIntNewTo">New To Time(Internal)</label></span>	
								<span >	
									<input  id="txtIntNewToTimeHrss" name="txtIntNewToTimeHrss" style="width: 60px;" maxlength="2"  class="easyui-text" value=""  tabindex="40" >
								</span>
								<span>					
									<input  id="txtIntNewToTimeMins" name="txtIntNewToTimeMins" tabindex="41" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 	
								<span>					
									<input  id="txtIntNewToTimeSecs" name="txtIntNewToTimeSecs" tabindex="42" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>
								<span>					
									<input  id="txtMttdTotalInternalNewTime" name="txtMttdTotalInternalNewTime" class="easyui-text" value=""  style="width: 60px;text-align:right;" maxlength="2" >
								</span>
								<span id="err_txtIntNewToTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtIntNewToTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtIntNewToTimeSecs" class="tpm-errormsg"></span>	  								
							</span>
						</div>	
						<div class="easyui-paddingbfpx">
							<span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="lblExtNewFrom">New From Time(External)</label></span>		
								<span >					
									<input  id="txtExtNewFromTimeHrss" name="txtExtNewFromTimeHrss" class="easyui-text" value="" tabindex="43"  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtExtNewFromTimeMins" name="txtExtNewFromTimeMins" tabindex="44" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span>					
									<input  id="txtExtNewFromTimeSecs" name="txtExtNewFromTimeSecs" tabindex="45" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 
								<span><label style="color: blue;">Total Ext. Time</label></span>
								<span id="err_txtExtNewFromTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtExtNewFromTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtExtNewFromTimeSecs" class="tpm-errormsg"></span>	
							</span>
						</div>	
						<div class="easyui-paddingbfpx" >
							<span >
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="lblExtNewTo">New To Time(External)</label></span>	
								<span >	
									<input  id="txtExtNewToTimeHrss" name="txtExtNewToTimeHrss" style="width: 60px;" maxlength="2"  class="easyui-text" value=""  tabindex="46" >
								</span>
								<span>					
									<input  id="txtExtNewToTimeMins" name="txtExtNewToTimeMins" tabindex="47" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span> 	
								<span>					
									<input  id="txtExtNewToTimeSecs" name="txtExtNewToTimeSecs" tabindex="48" class="easyui-text" value=""  style="width: 60px;" maxlength="2" >
								</span>
								<span>					
									<input  id="txtMttdTotalExternalNewTime" name="txtMttdTotalExternalNewTime" class="easyui-text" value=""  style="width: 60px;text-align:right;" maxlength="2" >
								</span>
								<span id="err_txtExtNewToTimeHrss" class="tpm-errormsg"></span>
								<span id="err_txtExtNewToTimeMins" class="tpm-errormsg"></span>
								<span id="err_txtExtNewToTimeSecs" class="tpm-errormsg"></span>	  								
							</span>
						</div>
					</td>
						
					
					
					<td valign="top" style="padding-left: 63px;">
						<div ><label  class="mandatory-lbl">Possible for Conversion?</label></div>	
							<div class="easyui-paddingbfpx">
								<div style="border:1px solid #a4a4a4; width:160px">							
					 			<div style="padding-left:5px;"><label>Yes</label>
					 			<span style="padding-left:2px;">
					 				<input type="checkbox" tabindex="29" id="chkYes" />
					 			</span>
					 			<label style="padding-left:10px;">No</label>
					 				<span style="padding-left:2px;">
					 					<input type="checkbox" tabindex="30" id="chkNo" />
					 				</span>
					 			</div>
			 				</div>	
			 			</div>	
						
						<div ><label id="lblResp">Responsibility</label></div>			
						<div class="easyui-paddingbfpx">
							<input id="cmbMttdRespoinsibility" name="cmbMttdRespoinsibility"  tabindex="32" class="easyui-combobox"   style="width:260px;" value=""  >							
						</div>
						<div><label  id="lblStatus">Status</label></div>			
						<div class="easyui-paddingbfpx">
							<input id="cmbMttdStatus" name="cmbMttdStatus" class="easyui-combobox" tabindex="33"  style="width:260px;" value=""  >							
						</div>
						<div><span><label id="lblTargetDate">Target Date</label></span><span style="padding-left:80px;"><label id="lblCompDate">Completed Date</label></span></div>
					 	<div class="easyui-paddingbfpx" >
					 		<span>
						 		<span>
						 			<input id="dteMttdTargetdate"  name="dteMttdTargetdate"  clear="false" tabindex="34" class="easyui-datebox" value=""  style="width: 115px;" />
						 		</span>
						 		<span style="padding-left:25px;">
							 		<input id="dteMttdCompleteddate"  name="dteMttdCompleteddate"  tabindex="35" clear="false" class="easyui-datebox" value=""  style="width: 115px;" />
						 		</span>
						 		<span id="dteMttdTargetdate" class="tpm-errormsg"></span>
								<span id="dteMttdCompleteddate" class="tpm-errormsg"></span>
							</span>	  	
					 	</div>							
					 	<div ><label   id="lblCompBy">Completed By</label></div>			
						<div class="easyui-paddingbfpx">
							<input id="cmbMttdCompletedby" name="cmbMttdCompletedby" class="easyui-combobox" tabindex="36"  style="width:260px;" value="" >												
						</div>	
					</td>
				</tr>
				<tr>
				<td valign="top"  colspan="3" align="center">
					<div class="easyui-paddingbfpx"  >
						<span style="padding-left: 159px;padding-right:2px;">
							<span>
								<input type="button" class="easyui-button" id="btnAdd" value="Insert" tabindex="49" style="height:21;"/>
							</span>
							<span>
								<input type="button" class="easyui-button" id="btnMttrDelete" value="Delete" tabindex="50" style="height:21;"/>
							</span>
							<span>
								<input type="button" class="easyui-button" id="btnClear" value="Clear" tabindex="51" style="height:21;"/>
							</span>
						</span>
					</div>	
				</td>
				</tr>
			</table>	
		</div>
		<div style="float: left; margin-left: 0px;">
			<table id="listdetail" ></table>
			<div id="detailpager"></div>
		</div>
						
		<div id="divConvMatxPopup">
		</div>
	</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
<input type="hidden" id="txtMttrMode" name="txtMttrMode" value="${requestScope.mode}"/>
<input type="hidden" id="txtDtlMode" name="txtDtlMode" value=""/>
<input type="hidden"id="txtMttrKeyid" name="txtMttrKeyid" value="${requestScope.PlmTlMttrmst.mttrKeyid}" />
<input type="hidden"id="txtMttdKeyid" name="txtMttdKeyid" value="${requestScope.PlmTlMttrdtl.MttdKeyid}" />

<input type="hidden"id="txtMttdItExtFromtime" name="txtMttdItExtFromtime" value="" />
<input type="hidden"id="txtMttdItExtTotime" name="txtMttdItExtTotime" value="" />
<input type="hidden"id="txtMttdEtExtFromtime" name="txtMttdEtExtFromtime" value="" />
<input type="hidden"id="txtMttdEtExtTotime" name="txtMttdEtExtTotime" value="" />

<input type="hidden"id="txtMttdItNewFromtime" name="txtMttdItNewFromtime" value="" />
<input type="hidden"id="txtMttdItNewTotime" name="txtMttdItNewTotime" value="" />
<input type="hidden"id="txtMttdEtNewFromtime" name="txtMttdEtNewFromtime" value="" />
<input type="hidden"id="txtMttdEtNewTotime" name="txtMttdEtNewTotime" value="" />

<input type="hidden"id="txtMttdIntActtime" name="txtMttdIntActtime" value=""   />
<input type="hidden"id="txtMttdExtActtime" name="txtMttdExtActtime" value=""   />
<input type="hidden"id="txtMttdIntActNewtime" name="txtMttdIntActNewtime" value=""   />
<input type="hidden"id="txtMttdExtActNewtime" name="txtMttdExtActNewtime" value=""   />

<input type="hidden"id="txtMttdPossibleForConv" name="txtMttdPossibleForConv" value="" />
<input type="hidden"id="txtTempTime" name="txtTempTime" value="" />
<input type="hidden"id="txtIsSave" name="txtIsSave" value="" />

<input type="hidden"id="txtPrevIntExisttime" name="txtPrevIntExisttime" value="" />
<input type="hidden"id="txtPrevIntNewtime" name="txtPrevIntNewtime" value="" />
<input type="hidden"id="txtPrevExtExisttime" name="txtPrevExtExisttime" value="" />
<input type="hidden"id="txtPrevExtNewtime" name="txtPrevExtNewtime" value="" />

<input type="hidden"id="txtIntExisttime" name="txtIntExisttime" value="${requestScope.PlmTlMttrmst.mttrExistingTime}"   />
<input type="hidden"id="txtIntNewtime" name="txtIntNewtime" value="${requestScope.PlmTlMttrmst.mttrConvertedTime}"   />
<input type="hidden"id="txtExtExisttime" name="txtExtExisttime" value="${requestScope.PlmTlMttrmst.mttrExtExistingTime}"   />
<input type="hidden"id="txtExtNewtime" name="txtExtNewtime" value="${requestScope.PlmTlMttrmst.mttrExtConvertedTime}"   />
</form>
