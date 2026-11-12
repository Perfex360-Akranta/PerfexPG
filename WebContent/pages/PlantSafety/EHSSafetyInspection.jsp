<script>
jQuery(document).ready(function(){
	initialiseForm('frmSafetyInspect');
	
	jQuery('#submitForm').val('frmSafetyInspect');	
	var url = jQuery('#hiddenUrl').val();	
	var frmtype = jQuery('#frmtype').val();
	var type = jQuery('#hdnaudittype').val();
	var popup=jQuery("#hdnpopup").val();
	var SFlid=jQuery("#hdnSFlid").val();
	alert(SFlid);
	// fillWithCurrentDate("dteSpsmDate");
	///alert('popup'+popup);
	var mode = jQuery('#hdnformmodes').val();

	fileManagerPopUp("","PlantSafety","frmSafetyInspect","btnFilManage","PlantSafetyFilemgr");
	//alert('mode'+mode);
	if(mode=="Create")
		{
	
		//jQuery("#btnExcelVw").attr("disabled",true).addClass("ui-state-disabled");
		//jQuery("#btnFilManage").attr("disabled",true).addClass("ui-state-disabled");
		// fillWithCurrentDate("dteSpsmDate");
		jQuery("#btnFilManage").hide();
		jQuery("#btndelete").hide();
		//jQuery('#ImgDelete').unbind('click');
		}
	else{
		jQuery('#ImgDelete').bind('click');
	}
 if(mode=="modify"){
	 jQuery("#btnFilManage").hide();
	 jQuery("#btndelete").hide();
	// jQuery('#ImgDelete').unbind('click');
 }	
 else
	 {
	 jQuery('#ImgDelete').bind('click');
	 }
 if(mode=="delete"){
	 jQuery("#btnAddNew").hide();
	 jQuery("#btnExcelVw").hide();
	 jQuery("#btnFilManage").hide();
	 //jQuery('#ImgDelete').unbind('click');
	 //jQuery('#ImgSave').unbind('click');
 }
 else
	 {
	// jQuery('#ImgDelete').bind('click');
	 }
	var userid = jQuery('#hdnuserid').val();
	var refnln = jQuery('#hdnflid').val();
	var seldate = jQuery('#hdndate').val();//hdntype
	var seltype = jQuery('#hdntype').val();
	if(frmtype=="DM" || type=="DM"){
		setFieldValue('cmbSpsmType',"DM");
		readOnlyFields('cmbSpsmType');
	}else if(frmtype=="EM" || type=="EM"){
		setFieldValue('cmbSpsmType',"EM");
		readOnlyFields('cmbSpsmType');
	}else if(frmtype=="UH" || type=="UH"){
		setFieldValue('cmbSpsmType',"UH");
		readOnlyFields('cmbSpsmType');
	}
	if(seldate.length>0){
		jQuery("#dteSpsmDate").datebox('setValue',seldate);
	}

	if(mode=='modify'){
		var type = jQuery('#hdntype').val();
		var hdnSpsmDoneby = getFieldValue("hdnSpsmDoneby");
	//	alert(hdnSpsmDoneby);
		var audittype = getFieldValue("hdnaudittype");
	    var modtype = getFieldValue("hdntype");
	//    alert("modetype : "+modtype);
	    readOnlyFields('cmbSpsmType');
      jQuery('#cmbSpsmType').combobox('setValue','UH');	
	/*    if(type=="DMT Member" || type=="DM" || modtype=="DMT Member" || modtype=="DM")
		{ //setFieldValue('cmbSpsmType',type);
	   		 jQuery('#cmbSpsmType').combobox('setValue','DM');
	   	}else if(type=="Unit Head" || type=="UH" || modtype=="Unit Head" || modtype=="UH"){
			jQuery('#cmbSpsmType').combobox('setValue','UH');	
		}else if(type=="EHS Managers" || modtype=="EM" || modtype=="EHS Manager" || modtype=="EM"){
			jQuery('#cmbSpsmType').combobox('setValue','EM');	
		}*/
		jQuery('#cmbSpsmType').combobox('setValue',jQuery("#hdnaudittype").val());

	    //for modify time selecttype
	/*    if(modtype=='DMT Member' || modtype=='DM')
		{ //setFieldValue('cmbSpsmType',type);
	   		 jQuery('#cmbSpsmType').combobox('setValue','DM');
	   	}else if(modtype=='Unit Head' || modtype=='UH'){
			jQuery('#cmbSpsmType').combobox('setValue','UH');	
		}else if(modtype=='EHS Manager' || modtype=='EM'){
			jQuery('#cmbSpsmType').combobox('setValue','EM');	
		}
	    
	  */  
	}
	if(mode=='view'){
	//	alert("inside View");
		jQuery( "#frmSafetyInspect" ).prop( "disabled", true );
	}
	fillComboBox("frmSafetyInspect","cmbSpsmDoneby","employee.commonFilter");
	setFieldValue('cmbSpsmDoneby',userid);
	formatDateBox('dteSpsmDate','DD-MON-YYY');
fillWithCurrentDate("dteSpsmDate");
	if(seldate.length!=0)
		jQuery("#dteSpsmDate").datebox('setValue',seldate);
	//else 
		//fillWithCurrentDate('dteSpsmDate');
	/*if(refnln.length>0){
		String fnlnurl="";
		if(frmtype=="EM")
		fnlnurl="functionalLocEM.safetyinspect";
		if(frmtype=="DM")
			fnlnurl="functionalLocDM.safetyinspect";
		if(frmtype=="UH")
			fnlnurl="functionalLocUH.safetyinspect";
		
		loadFunctionalLocation("SafetyFunctionalLoc",fnlnurl,"SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=EM&flid=");
	} */
	var flId = jQuery("#frmSafetyInspect input[id='flid']").val();
/*	if(flId.length<=0){
	loadFunctionalLocation("SafetyFunctionalLoc","functionalLocEM.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&flid=");
	}else{
		 loadFunctionalLocation("SafetyFunctionalLoc","functionalLoc.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&flid="+flId);	
  	}
	*/
 
  	/* if(flId.length<=0 && frmtype=="EM" ){
		loadFunctionalLocation("SafetyFunctionalLoc","functionalLocEM.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=EM&flid=");	
  	}
	else if(flId.length<=0 && frmtype=="DM" ){
		loadFunctionalLocation("SafetyFunctionalLoc","functionalLocDM.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=DM&flid=");	
  	}
	else if(flId.length<=0 && frmtype=="UH" ){
		loadFunctionalLocation("SafetyFunctionalLoc","functionalLocUH.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=UH&flid=");	
  	}
   else{
	
		 loadFunctionalLocation("SafetyFunctionalLoc","functionalLoc.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&flid="+SFlid);	
  	}
	
	 var url = jQuery('#hiddenUrl').val();
	 viewGrid(url,"&s=1");
     readOnlyFields("txtdocuno");
	 //var mode=jQuery("#hdnmode").val(); */
	 
	 var filterButton = jQuery('#hdnfilterButton').val();

if(filterButton !== 'false') {
    if(flId.length<=0 && frmtype=="EM" ){
        loadFunctionalLocation("SafetyFunctionalLoc","functionalLocEM.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=EM&flid=");	
    }
    else if(flId.length<=0 && frmtype=="DM" ){
        loadFunctionalLocation("SafetyFunctionalLoc","functionalLocDM.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=DM&flid=");	
    }
    else if(flId.length<=0 && frmtype=="UH" ){
        loadFunctionalLocation("SafetyFunctionalLoc","functionalLocUH.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&frmtype=UH&flid=");	
    }
    else{
        loadFunctionalLocation("SafetyFunctionalLoc","functionalLoc.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&flid="+SFlid);	
    }
    var url = jQuery('#hiddenUrl').val();
    viewGrid(url,"&s=1");
} else {
    loadFunctionalLocation("SafetyFunctionalLoc","functionalLoc.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&flid="+SFlid);
    var url = jQuery('#hiddenUrl').val();
    var keyid = jQuery('#hdnSpsmKeyid').val();
    var audittype = jQuery('#hdnaudittype').val();
    viewGrid(url,"&s=1&keyid="+keyid+"&TYPE="+audittype);
}
});

jQuery("#btnExcelVw").click(function(){
    var plansafKeyid=jQuery("#txtdocuno").val();
    //alert("Keyid"+plansafKeyid);
	  var flid = jQuery("#hdnflid").val();
	  //alert("The Flid"+flid);
   if(plansafKeyid != null && plansafKeyid.length >0){
       window.open("Plantsafety_Excelview.safetyinspect?plansafetyKeyid="+plansafKeyid+"&flid="+flid);
   }
   else
  	 {
  	 return false;
  	 }
});
function viewGrid(url,filterString){  	
	  	 processGridnew(url,filterString,"safetyauditgrid","safetyauditpages","","","");
	  	//processGridnew(url,"&q=1","safetyauditgrid","safetyauditpages","Safety Audit Grid", "","","gridLoadComplete");
}
function ReloadGrid(url,filterString){  	
 	 processGridnew(url,filterString,"safetyauditgrid","safetyauditpages","","","");
 	//processGridnew(url,"&q=1","safetyauditgrid","safetyauditpages","Safety Audit Grid", "","","gridLoadComplete");
}
jQuery("#btnAddNew").click(function(){
	var row = jQuery("#safetyauditgrid").jqGrid("getDataIDs");
	addRow(row);
});


jQuery('#ImgBack').click(function(){
   //alert(1);
	});
	
function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnSpsdKeyid:" ",hdnSpsdTempfield1:" ",hdnSpsdTempfield3:" ",hdnSpsdTempfield4:" ",txtSpsdObserve:" ",cmbSpsdType:" ",cmbSpsdRelatedto:" ",txtSpsdRecomAction:" ",
		 				txtSpsdCorrectAction:" ",txtSpsdPreventiveAction:" ",cmbSpsdTempfield2:" ",cmbSpsdResponsibilty:" ",dteSpsdTargetDate:" ",
		 				cmbSpsdStatus:" ",hdnSpsdActiontaken:" ",btnFilManage:" " ,cmbSpsdCompletedby:" ",dteSpsdCompletedate:" ",
		 				cmbSpsdVerified:" ",cmbSpsdVerifiedBy:" ",dteSpsdVerifiedDate:" " }];
	jQuery("#safetyauditgrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		// alert("INSIDE THE ESLE");
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnMandKeyid:" ",hdnSpsdTempfield1:" ",hdnSpsdTempfield3:" ",hdnSpsdTempfield4:" ",txtSpsdObserve:" ",cmbSpsdType:" ",Auditdetail:" ",cmbSpsdRelatedto:" ",Safetydetail:" ",txtSpsdRecomAction:" ",
	txtSpsdCorrectAction:" ",txtSpsdPreventiveAction:" ",cmbSpsdTempfield2:" ",cmbSpsdResponsibilty:" ",Responsibiltyby:" ",dteSpsdTargetDate:" ",cmbSpsdStatus:" ",
	statusdetail:" ",hdnSpsdActiontaken:" ",btnFilManage:" " ,cmbSpsdCompletedby:" ",completedby:" ",dteSpsdCompletedate:" " ,cmbSpsdVerified:" ",
	verifystatus:" ",cmbSpsdVerifiedBy:" ",verifiedby:" ",dteSpsdVerifiedDate:" "}];
		jQuery("#safetyauditgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

/*function btnFilManage_click(){
    
    var documentNo =jQuery("#hdnSpsmKeyid").val();
    alert("The documentNo :::"+documentNo);
    //alert(" documentNo:::1234::: "+documentNo);
	if(documentNo != null && documentNo != ''){
		//alert(frmMode);
		var frmMode=jQuery('#frmMode').val();
		
		
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		
	
		fileManagerPopUp(documentNo,"YY","","","",apMode);
			
	} else   
    {
		 saveForm('frmSafetyInspect','safetyinspection_save.safetyinspect?filemanager=filemanager');
     }	
}*/

/*function safetyauditgridbtnFilManage_onClick(result){
alert("Inside the FileManager");
var documentNo =jQuery("#hdnSpsdKeyid").val();
//var documentNo =jQuery("#hdnspsdSdamKeyid").val();
alert("The documentNo :::"+documentNo);
//alert(" documentNo:::1234::: "+documentNo);
if(documentNo != null && documentNo != ''){
	//alert(frmMode);
	var frmMode=jQuery('#frmMode').val();
	apMode = "create";
	if(frmMode=="View")
	   apMode = "view";

	fileManagerPopUp(documentNo,"YY","","","",apMode);
		
} else   
{  
	 saveForm('frmSafetyInspect','safetyinspection_save.safetyinspect?filemanager=filemanager');
 }	
}*/
function cmbSpsdTempfield2_safetyauditgrid_onSelect(record,rowId){
	
	 var status=jQuery("#cmbSpsdTempfield2_safetyauditgrid_"+rowId).combobox('getValue');
	
	var cellid=jQuery("#frmSafetyInspect input[id='cell']").val();
if(cellid==""&&status=="AB"||cellid==""&&status=="SUG"){
	
 	popupCommonErrorMsg("Select JH for Abnormality or Suggestion");	
	return false;
	
}

	


}
function safetyauditgrid_selectRow(rowId)
{ 
    var plandtlkeyid=jQuery("#safetyauditgrid").jqGrid('getCell',rowId,"hdnSpsdKeyid");
   // alert("The Dtl Keyid"+plandtlkeyid);
	var pillarchampion=jQuery('#hdnrolename').val();
	var menumode=jQuery('#hdnmode').val();
	var ehspillar="DMT MEMBER";
	var dateCtrl="dteSpsdTargetDate_safetyauditgrid_"+rowId;
	var filemanagerbtn="btnFilManage_safetyauditgrid_"+rowId;

//	var mode="create";
	var jqGridId="safetyauditgrid";
//	alert("pillarchampion: " +pillarchampion+"menumode"+menumode);
	    jQuery("#cmbSpsdStatus_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
	       // enableUIButton("btnFilManage_"+jqGridId+"_"+rowId);
	       // jQuery("#btnFilManage_safetyauditgrid"+jqGridId+"-"+rowId);
	      //  jQuery("#btnFilManage_").click(function(){
	        //	fileManagerPopUp(documentNo,"YY","","","",apMode);
	       // });
	        
			disableField('frmSafetyInspect', "safetyauditgrid_hdnSpsdActiontaken");
			enableUIButton("btnFilManage_"+jqGridId+"_"+rowId);//dteSpsdVerifiedDate_safetyauditgrid_1
			disableField('frmSafetyInspect', "cmbSpsdCompletedby_"+jqGridId+"_"+rowId);
			disableField('frmSafetyInspect', "dteSpsdCompletedate_"+jqGridId+"_"+rowId);
			jQuery("#cmbSpsdVerified_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
			disableField('frmSafetyInspect', "cmbSpsdVerified_"+jqGridId+"_"+rowId);
			disableField('frmSafetyInspect', "cmbSpsdVerifiedBy_"+jqGridId+"_"+rowId);
//			jQuery("#dteSpsdVerifiedDate_"+jqGridId+"_"+rowId).hide(); 
			disableField('frmSafetyInspect', "dteSpsdVerifiedDate_"+jqGridId+"_"+rowId);
			
			
			
			
			
			
			jQuery("#"+dateCtrl).datebox({  	   
					onSelect:function(recordid)
						{ 
						isValidDate(dateCtrl,rowId);
						    	
						} 
					});
			
			jQuery("#"+filemanagerbtn).click(function()
	            {
	            	//alert("Clicked button");
	            	var documentNo =plandtlkeyid;
	            	if(documentNo.length!=0)
	            		{
	            	//var documentNo =jQuery("#hdnSpsdKeyid").val();
	            	//alert("The documentNo :::"+documentNo);
	            	if(documentNo != null && documentNo != ''){
	            	    var frmMode=jQuery('#frmMode').val();
	            		apMode = "create";
	            		if(frmMode=="View")
	            		   apMode = "view";

	            		fileManagerPopUp(documentNo,"PS","","","",apMode);	
	            	}
	            		}else{
	            			alert("Pls Save Plant Safety");
	            		}
	            }
			);		
		
	if(pillarchampion==ehspillar)
	{ 
	   if(menumode=="Create" || menumode=="modify")
		   {
		  //  alert("inside enable/disable");
		    jQuery("#cmbSpsdStatus_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
		    enableUIButton("btnFilManage_"+jqGridId+"_"+rowId);
			disableField('frmSafetyInspect', "safetyauditgrid_hdnSpsdActiontaken");
		    enableUIButton("btnFilManage_"+jqGridId+"_"+rowId);//dteSpsdVerifiedDate_safetyauditgrid_1
			disableField('frmSafetyInspect', "cmbSpsdCompletedby_"+jqGridId+"_"+rowId);
			disableField('frmSafetyInspect', "dteSpsdCompletedate_"+jqGridId+"_"+rowId);
			jQuery("#cmbSpsdVerified_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
			disableField('frmSafetyInspect', "cmbSpsdVerified_"+jqGridId+"_"+rowId);
			disableField('frmSafetyInspect', "cmbSpsdVerifiedBy_"+jqGridId+"_"+rowId);
//			jQuery("#dteSpsdVerifiedDate_"+jqGridId+"_"+rowId).hide(); 
			disableField('frmSafetyInspect', "dteSpsdVerifiedDate_"+jqGridId+"_"+rowId);
		}
	}
else{   
	 jQuery("#cmbSpsdStatus_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
	    enableUIButton("btnFilManage_"+jqGridId+"_"+rowId);
		disableField('frmSafetyInspect', "safetyauditgrid_hdnSpsdActiontaken");
		enableUIButton("btnFilManage_"+jqGridId+"_"+rowId);//dteSpsdVerifiedDate_safetyauditgrid_1
		disableField('frmSafetyInspect', "cmbSpsdCompletedby_"+jqGridId+"_"+rowId);
		disableField('frmSafetyInspect', "dteSpsdCompletedate_"+jqGridId+"_"+rowId);
		jQuery("#cmbSpsdVerified_"+jqGridId+"_"+rowId).prop('disabled', 'disabled');
		disableField('frmSafetyInspect', "cmbSpsdVerified_"+jqGridId+"_"+rowId);
		disableField('frmSafetyInspect', "cmbSpsdVerifiedBy_"+jqGridId+"_"+rowId);
//		jQuery("#dteSpsdVerifiedDate_"+jqGridId+"_"+rowId).hide(); 
		disableField('frmSafetyInspect', "dteSpsdVerifiedDate_"+jqGridId+"_"+rowId);
		
}
}

function frmSafetyInspect_successsCallback(result)
{
	var msg = result.successData.msg; 
	var popup = result.successData.popup; 
	
	 closePopUpDialoge("Plantsafety");
	// var popup=jQuery("#hdnpopup").val();
	// alert("popup"+popup);
	 if(popup!="Y")
		 {

	 var spsmkeyid =  result.successData.spsmkeyid;
//	 //alert(spsmkeyid);
	 var spsmflid =  result.successData.spsmflid;
	
//	// alert(spsmflid);
	 var spsmdate = result.successData.spsmdate;
//	// alert(spsmdate);
	 var spsmtype = result.successData.spsmtype;

	 var keyid = result.successData.keyId;
	
	 var url = "safetyinspection_input.safetyinspect";

	 setFieldValue('txtdocuno',spsmkeyid);
	 var filterstr="&s=1&keyid="+spsmkeyid+"&TYPE="+spsmtype;
	 var filterstring="&s=1&keyid="+spsmkeyid+"&auditflid="+spsmflid+"&auditdate="+spsmdate+"&TYPE="+spsmtype;
	// alert("filterstr : "+filterstring );
	// navigateToNextForm("safetyinspection_input.safetyinspect?&s=1&keyid="+spsmkeyid+"&auditflid="+spsmflid+"&auditdate="+spsmdate+"&TYPE="+spsmtype);
	
	navigateToNextForm("safetyinspection_input.safetyinspect?&mode=Create"+"&TYPE="+spsmtype+"&keyid="+spsmkeyid+"&auditflid="+spsmflid);
	 
//	loadFunctionalLocation("SafetyFunctionalLoc","functionalLoc.safetyinspect","SafetyFunctionalLocValues","frmSafetyInspect","&flid=")
	 //ReloadGrid(url,filterstring);
	// jQuery('#safetyauditgrid').trigger("reloadGrid");
		 }

		 
}
function frmSafetyInspect_beforeSubmit()
{		
	var mode = jQuery('#hdnformmodes').val();
	
	var Members=jQuery('#txtSpsmMembers').val();
	
	if(Members.length<1||Members==null){
		popupCommonErrorMsg("Please select Members");
		return false;
	}
	if(mode!="delete" || mode.length!=4 )
	{
	var taskGrdIds = jQuery("#safetyauditgrid").jqGrid('getDataIDs');
	var cmb=jQuery("#cmbSpsmType").combobox('getValue');
	alert(cmb +"  cmbcmb");
	if(cmb=='DMT Member'){
		jQuery('#cmbSpsmType').combobox('setValue','DM');
	}
	else if(cmb=='Unit Head'){
		jQuery('#cmbSpsmType').combobox('setValue','UH');
	}
	else if(cmb=='EHS Manager'){
		jQuery('#cmbSpsmType').combobox('setValue','EM');
	}
	alert(jQuery('#cmbSpsmType').combobox('getValue') +".......");
		var gridval=getGridSelectArray('safetyauditgrid');
		var sbuId = jQuery("#frmSafetyInspect input[id='sbu']").val();
		var dmtId = jQuery("#frmSafetyInspect input[id='section']").val();
		var locnId=jQuery("#frmSafetyInspect input[id='location']").val();
		if(cmb=="UH"){
		if(locnId.trim().length==0){
			alert(" Select Location ");
			return false;
		}
		}
			if(cmb=="DM")
				{
				if(dmtId.trim().length==0){
					alert(" Select DMT");
					return false;
				
				}
				}
			
			//alert('locnId'+locnId);
			if(cmb=="EM"){
				if(locnId.trim().length==0){
					alert("select Location");
					return false;
				}
			}
			//if(cmb=""
		//alert(gridval);
		var gridData  = '&safetydetails='+gridval;	
	    if(gridval.trim().length>0)	{
			return gridData;
		}
	    else {
	    	popupCommonErrorMsg("Select Any Row Details To Save");
	        return false;
	    }
	  var flid = getFieldValue('flid');  
		}
	else{
		return false;
		
		
	}
}
function frmSafetyInspect_CallbackExcptionmsg(){
	var savetype=jQuery('#savetype').val();
	var savemode=jQuery('#savemode').val();
	var flid = getFieldValue('flid');
	var title="";
	if(savetype.length!=0){
		if(savetype=="UH"){
		title="UNIT HEAD";
		}
		else if(savetype=="DM"){
		title="DMT MEMBER";
		}
		else if(savetype=="EM"){
			title="EHS MANAGER";
			}
	}
	var role=jQuery("#hdnrolename").val();
	if(role.length!=0){
		  var rolename=role;}
	else{
		var rolename="DMT MEMBER";}
	var answer=confirm(rolename+" in "+flid+" Function Location and Date are Already Exists?Do you want to modify?");
	var savetype=jQuery('#savetype').val();
	if(answer==true)
	{ 
		if(savetype=="UH"){   
		navigateToNextForm("safetyinspection_input.safetyinspect?mode=modify&TYPE=UH&");
		}else if(savetype=="DM"){
		   navigateToNextForm("safetyinspection_input.safetyinspect?mode=modify&TYPE=DM&");
	   }else if(savetype=="EM"){
		   navigateToNextForm("safetyinspection_input.safetyinspect?mode=modify&TYPE=EM&");
	   }
	}
else{
	if(savetype=="UH"){   
	navigateToNextForm("safetyinspection_input.safetyinspect?mode=Create&TYPE=UH&");
	}else if(savetype=="DM"){
	   navigateToNextForm("safetyinspection_input.safetyinspect?mode=Create&TYPE=DM&");
   }else if(savetype=="EM"){
	   navigateToNextForm("safetyinspection_input.safetyinspect?mode=Create&TYPE=EM&");
   }	
}
}
function isValidDate(dateCtrl,ctrlRowId){
	
//var audtype=jQuery('#hdnaudittype').val();
	//var modetype=jQuery('#hdnmodetype').val();
	
	/*if(audtype.length!=0)
		{
	if(audtype=="divisional")
		{
		var jqGridId="dailyauditgrid";
		}
	else
		{
		var jqGridId="dailyauditgrid";
		}
		}*/
	var approvalDate = getFieldValue(dateCtrl);
	//alert("approvalDate"+approvalDate);

	var currentDate = getServerDateTime();
	//alert("currentDate"+currentDate);
	//var deriskPreparedDate = jQuery("#dteSdamDate").datebox("getValue");
	
	var hdndate=jQuery("#hdncurr").val();
	//alert("hdndate:::"+hdndate);
	if(approvalDate!=hdndate)
		{
	var stringdate=convertStringToDate(approvalDate);
	
	if(convertStringToDate(approvalDate)== currentDate)
		{
		// alert(1);
		}

	if(convertStringToDate(hdndate) > convertStringToDate(approvalDate))
	{  
		
	 //  alert(currentDate);
	 //  alert(stringdate);
		if(stringdate==convertStringToDate(hdndate))
			{
			 // alert(3);
			  clearValidationErrorMsg(dateCtrl);
		    	return false;
			}
		else{
		 popupCommonErrorMsg('Should Not Enter Past Date');
		fillWithCurrentDate(dateCtrl);
		return false;
		}
	}
		}
}	 


function frmSafetyInspect_beforeDelete()
{  
	var mode = jQuery('#hdnformmodes').val();
	if(mode=="Create"||mode=="modify"||mode=="delete")
		{
		return false;
		}
	/*var keyid= jQuery("#txtSpsmmKeyid").val();
	
	if(keyid.length>0){	
		var r =confirm("Are you sure to Delete?");
		if(r){	
		
		}
		else
			return false;	
	}
	else
		return false;*/
}
function frmSafetyInspect_FuntLocHierarchy_SuccessCallBack(keyIds)
{ 
	var flid = getFieldValue('flid');  //var flid = jQuery("#frmSafetyInspect input[id='flid']").val();
	//alert(flid);
	//var filterstring="&s=1&keyid="+spsmkeyid+"&auditflid="+spsmflid+"&auditdate="+spsmdate+"&TYPE"+spsmtype;
	var filterstr="&s=1&auditflid="+flid;
	//ReloadGrid(url,filterstr);
	//navigateToNextForm("safetyinspection_input.safetyinspect?keyid="+spsmkeyid);
}
jQuery("#dteSpsmDate").datebox({  	   
	onSelect:function(recordid){ 
		var currentDate = getServerDateTime();
		var datevalue=jQuery("#dteSpsmDate").datebox('getValue');
		var stringdate=convertStringToDate(datevalue);
		if(stringdate > currentDate){  
			 popupCommonErrorMsg('Should Not Enter Audit For  Future Date');
			fillWithCurrentDate('dteSpsmDate');
			return false;
		}
		var flId = jQuery("#frmSafetyInspect input[id='flid']").val();
		var audittype=jQuery("#cmbSpsmType").combobox('getValue');
		var  datevalue=jQuery('#dteSpsmDate').datebox('getValue');
		//var modetype=jQuery('#hdnmodetype').val();
	    //var audtype=jQuery('#hdnaudittype').val();
	    var audtype = jQuery('#frmtype').val();
	    var modetype = jQuery('#hdnmode').val();
		if(audtype.length!=0){
	    	   if(audtype=="DM"){
	    	    processAjaxCalls("PlantsafetyExist_select.safetyinspect" ,"&audittype="+"DM"+"&flId="+flId+"&datevalue="+datevalue+"&combotype="+audittype, "frmSafetyInspectFill_successsCallback","frmSafetyInspectFill_errorCallback");
		    }
	    	   if(audtype=="EM"){
    		   processAjaxCalls("PlantsafetyExist_select.safetyinspect" ,"&audittype="+"EM"+"&flId="+flId+"&datevalue="+datevalue+"&combotype="+audittype, "frmSafetyInspectFill_successsCallback","frmSafetyInspectFill_errorCallback");
			}
	    	   if(audtype=="UH"){
	    	   processAjaxCalls("PlantsafetyExist_select.safetyinspect" ,"&audittype="+"UH"+"&flId="+flId+"&datevalue="+datevalue+"&combotype="+audittype, "frmSafetyInspectFill_successsCallback","frmSafetyInspectFill_errorCallback");
			}
	    }
	}	
	});
	
jQuery("#btndelete").click(function(){
	var modetype=jQuery('#hdnmode').val();
	//alert(modetype);
	var gridval;
    var gridData ; 
	var gridId="safetyauditgrid";
	
	if(modetype=="delete"){
		var selArray =  jQuery("#safetyauditgrid").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
		 var jsonArr1='  ';
	  if(selArray !=null && selArray!=" " && selArray!=""){
	   for(var i=0;i<selArray.length;i++){
		selrowid=selArray[i];
		var Keyid =jQuery("#safetyauditgrid").jqGrid('getCell', selrowid,"hdnSpsdKeyid");
		var APm =jQuery("#safetyauditgrid").jqGrid('getCell', selrowid,"hdnSpsdTempfield1");
		 var Criteriasplit= Keyid.split(',');
		  var apmsplit=APm.split(',');
		  for(var k=0;k<Criteriasplit.length;k++){
			var Keyidval=Criteriasplit[k]; 
			 	var keyvalSplit=Keyidval.split(";");
			 	var keyvalu=keyvalSplit[0];
			 	jsonArr += '"'+keyvalu + '",';	
	 	    }
		 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
			jsonArr += ',';
		 	for(var j=0;j<apmsplit.length;j++){
			 	var Keyidval1=apmsplit[j]; 
			 	var keyvalSplit1=Keyidval1.split(";");
			 	var keyvalu1=keyvalSplit1[0];
			 	jsonArr1 += '"'+keyvalu1 + '",';	
	 	    }
		 	jsonArr1 = jsonArr1.substring(0,jsonArr1.length-1);
			jsonArr1 += ',';
		
		}	
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr1 = jsonArr1.substring(0,jsonArr1.length-1);
	    var UpdateList=jsonArr;
	    var deletelist=jsonArr1;
	  
	    if(UpdateList!=null){
	    	//alert("updatelist");
	    		var mode = jQuery('#hdnformmodes').val();
	    		var flId = jQuery("#frmSafetyInspect input[id='flid']").val();
	    		var audittype=jQuery("#cmbSpsmType").combobox('getValue');
	    	var keyid=jQuery("#txtdocuno").val();
	    	processAjaxCalls("SafetymainDelete_delete.safetyinspect" ,"&UpdateList="+UpdateList+"&deletelist="+deletelist, " "," ");
			  
	    	//navigateToNextForm("SafetymainDelete_delete.safetyinspect?&UpdateList="+UpdateList+"&deletelist="+deletelist);
	    	setTimeout(function() {
	    	alert("Data Deleted SuccessFully");
	    	jQuery('#safetyauditgrid').trigger("reloadGrid");
	    	//navigateToPrevForm();
	        //navigateToNextForm("safetyinspection_input.safetyinspect?keyid="+Keyid+"&frmmode="+"delete"+"&modetype="+"divisional");
	    //navigateToNextForm("safetyinspection_input.safetyinspect?keyid="+keyid+"&mode="+mode+"&audittype="+audittype+"&auditflid="+flId);	
	       },1000);
	       }
	       
	  }
	}
	
});
function frmSafetyInspectFill_successsCallback(result){
	 
	var msg = result.successData.datapresent; 
//	alert(msg);
	 var flId =  result.successData.flId;
//	 alert(flId);
	 var datevalue =  result.successData.datevalue;
//	 alert(datevalue);
	 var combotype = result.successData.combotype;
	 var filemanager =result.successData.filemanager;
//	 alert(combotype);
	if(msg>0){
		var flid = getFieldValue('flid');
		var flidname = getFieldValue('linfrmSafetyInspectSection');
		// var txt=jQuery('input:text[id=section]').val();
		//var txt = jQuey("#section").attr("title").val();
		// alert("txt :"+txt);
		var role=jQuery("#hdnrolename").val();
		//alert("The role"+role);
		if(role.length!=0){
			var rolename=role;}
		else{
			var rolename="DMT MEMBER";}
		if(filemanager==true ){
			 var keyid = result.successData.spsdSdamKeyid;
			// alert("The File Manager keyid"+keyid);
			// jQuery('#hdnSpsmKeyid').val(keyid);
			jQuery('#hdnSpsdKeyid').val(keyid);
			 //alert(keyid);
			 fileManagerPopUp(keyid,"YY","","","");
			return ;
		 } 
		
		var answer=confirm("For this "+rolename+" in "+flid+" Function Location  Already Data Exists?");
		
	if(answer==true){ 
		//processGridnew(url,filterString,"safetyauditgrid","safetyauditpages","","","");
		processGridnew("safetyinspection_input.safetyinspect?&s=1","&auditflid="+flId+"&auditdate="+datevalue+"&TYPE="+combotype+"&mode=modify&","safetyauditgrid","safetyauditpages","", "","","");
		setTimeout(function() {
			var ans=confirm("Do you want to modify? ");
			if(ans==true){
				navigateToNextForm("safetyinspectiondetails_input.safetyinspect?&TYPE=UH&mode=modify");
			}
			else{
				navigateToNextForm("safetyinspection_input.safetyinspect?&TYPE=UH&mode=Create");
			}
		},2000);
		//navigateToNextForm("safetyinspection_input.safetyinspect?&s=1&auditflid="+flId+"&auditdate="+datevalue+"&TYPE="+combotype+"&mode=modify");
	}else{
		navigateToNextForm("safetyinspection_input.safetyinspect?&TYPE=UH&mode=Create");
	}
		
	}else{
		 setFieldValue('dteSpsmDate',datevalue);
		//navigateToNextForm("safetyinspection_input.safetyinspect?&TYPE=UH&mode=Create&auditdate="+datevalue);
	}
}

</script>
<form id="frmSafetyInspect">
	<div id="wrapper" style="width:80%;">
		<table>
			<tr>
				<td colspan="3">
					<div id="frmSafetyInspectFuntKeyIds">
						<input type="hidden" id="company" name="cmbSPcompany" value=""></input>
						<input type="hidden" id="factory" name="cmbSPFactoryid" value=""></input>
						<input type="hidden" id="location" name="cmbSPLocationid" value=""></input>
						<input type="hidden" id="section" name="cmbSPSectionid" value=""></input>
						<input type="hidden" id="cell" name="cmbSPCellid" value=""></input>
						<input type="hidden" id="machine" name="cmbSPMachineid" value=""></input>
						<input type="hidden" id="flid" name="cmbSpsmFlid" value="${requestScope.sheTlPlantSafetymst.spsmFlid}"></input>
						<input type="hidden" id="elementId" name="cmbelementid" value="${requestScope.sheTlPlantSafetymst.elementid}"></input>
			  		</div>
			 		<div id="SafetyFunctionalLoc"   style="width:120%;" ></div>	
				</td colspan="3">
				<td>
				<div style="margin-left:32%; font-weight: bolder;"><label><b>Document No</b></label></div>
				  <div style="margin-left:32%; margin-bottom:-4%;">
				 <%--  <input id="txtdocuno" name="txtdocuno" type="text" class="easyui-text"  style="width:110px; height:30px; text-align: center; cursor: default; font-weight: bolder;" value="${requestScope.sheTlPlantSafetymst.spsmKeyid}"> --%>
				  <input id="txtdocuno" name="txtdocuno" type="text" class="easyui-text" 
  style="width:110px; height:30px; text-align: center; cursor: default; font-weight: bolder; background-color: #f0f0f0;" 
  value="${requestScope.sheTlPlantSafetymst.spsmKeyid}" readonly="readonly">
				  
				  
				  </div>
				  
				  
				  </td>
				  <td>
				  <div style="margin-top:20px;">
				   <input type="button" class="easyui-button" id="btnExcelVw" name="btnExcelVw" value="Excel View" style="height:32px;width:70px;display:block;"/>
 
				  </div>
				  
				</td>
			</tr>
		   <tr>
		   <td>
		   	<div id="Type"  style="margin-left:-0%;">
			<label class="mandatory-lbl"><b>Type</b></label>
			<div>
		<select id="cmbSpsmType" class="easyui-combobox" name="cmbSpsmType"   style="width:110px; " value="${requestScope.sheTlPlantSafetymst.spsmType}" >	
				<option value='UH'><b>Unit Head</b></option>
				<option value='DM'><b>DMT Member</b></option>
		     <option value='EM'><b>EHS Manager</b></option>
			</select>
		    </div>
		    </div>
		   </td>
		   <td>
		   <div style="margin-left:-60%;"><label class="mandatory-lbl">Date</label></div>
								<div style="margin-left:-60%;">
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 83px;" id="dteSpsmDate" name="dteSpsmDate" value="${requestScope.sheTlPlantSafetymst.spsmDate}" />
							      	</span>
								</div>
		   </td>
		   <td>
		      <div style="margin-left:-45%;"><label class="mandatory-lbl">Prepared By</label></div>
								<div style="margin-left:-45%;">
									<input id="cmbSpsmDoneby" name="cmbSpsmDoneby" class="easyui-combobox"  style="width: 200px;" value="${requestScope.sheTlPlantSafetymst.spsmDoneby}" />
								</div>
		   </td>
		   <td>
		   <div style="margin-left:-210%;"><label class="mandatory-lbl">Members Present</label></div>
					<div style="margin-left:-210%;">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSpsmMembers" name="txtSpsmMembers" style="resize:none;width:180px;" maxlength="100" >${requestScope.sheTlPlantSafetymst.spsmMembers}</textarea>
					</div>
		   </td>
		   <td>
		   <div style="margin-left:-202%;"><label class="mandatory-lbl">Remarks</label></div>
					<div style="margin-left:-202%;">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSpsmRemarks" name="txtSpsmRemarks" style="resize:none;width:180px;" maxlength="100" >${requestScope.sheTlPlantSafetymst.spsmRemarks}</textarea>
					</div>
		   </td>
		   <td>
		   <div style="margin-left:-207%; padding-right:78px;">
								<input type="button" class="easyui-button" value ="Add New" id="btnAddNew" style="height:23px;" >
						  <span><input type="button" class="easyui-button" value ="Delete" id="btndelete" style="height:23px;" ></span>	
						  
							</div>
		   </td>
		    <td>
			<div style="margin-left:-230%; padding-right:30px;"><span id="PlantSafetyFilemgr">

		</span></div></td>
		   </tr>
		</table>
		<table>
		<tr>
		<td colspan="3">
					<div>
						<table id="safetyauditgrid" ></table> 
						<div id="safetyauditpages"></div>
					</div>
				</td>
		</tr>
		</table>
		
		<input type="hidden" id="hdnSFlid" name="hdnSFlid" value="${requestScope.sheTlPlantSafetymst.spsmFlid}" /> 
		<input type="hidden" id="mode" name="mode" value="" />
		<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.modtype}" />
		<input type="hidden" id="frmtype" name="frmtype" value="${requestScope.frmtype}" />
		<input type="hidden" id="hdnaudittype" name="hdnaudittype" value="${requestScope.audittype}" />
		<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}" />
		<input type="hidden" id="hdnmodeType" name="hdnmodeType" value="${requestScope.modeType}" />
		<input type="hidden" id="hdnSpsmKeyid" name="hdnSpsmKeyid" value="${requestScope.sheTlPlantSafetymst.spsmKeyid}" /> 	
		<input type="hidden" id="hdnSpsmDoneby" name="hdnSpsmDoneby" value="${requestScope.sheTlPlantSafetymst.spsmDoneby}" />
		<input type="hidden" id="hdnspsdSdamKeyid" name="hdnspsdSdamKeyid" value="${requestScope.SheTlPlantSafetydtl.spsdSdamKeyid}"/>
		<input type="hidden" id="hdnehspillar" name="hdnehspillar" value="${requestScope.ehspillarchampion}"></input>
		<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.rolename}"></input>
		<input type="hidden" id="hdnuserid" name="hdnuserid" value="${requestScope.userid}"></input>
		<input type="hidden" id="savemode" name="savemode" value="${requestScope.savemode}" />
		<input type="hidden" id="savetype" name="savetype" value="${requestScope.savetype}" />
		<input type="hidden" id="hdndate" name="hdndate" value="${requestScope.seldate}" />
		<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.seltype}" />
		<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.selflid}" />
		<input type="hidden" id="hdnformmodes" name="hdnformmodes" value="${requestScope.formmodes}" />
		 <input type="hidden" id="hdncurr" name="hdncurr" value="${requestScope.currdate}"/>
		  <input type="hidden" id="hdnpopup" name="hdnpopup" value="${requestScope.popup}"/>
		  <input type="hidden" id="hdnfilterButton" name="hdnfilterButton" value="${requestScope.filterButton}" />
	</div>
</form>