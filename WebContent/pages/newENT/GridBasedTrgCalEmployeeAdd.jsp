 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> 
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<script type="text/javascript">
	jQuery(document).ready(function() {

		initialiseForm("frmGridBasedAddEmployee");
		jQuery("#submitForm").val("frmGridBasedAddEmployee");
		fillComboBox("frmGridBasedAddEmployee","cmbRole","employeeRole.commonFilter");
		fillComboBox("frmGridBasedAddEmployee","cmbProgsEmployee","employee.commonFilter");
	    fillComboBox("frmGridBasedAddEmployee","cmbProgPillargroup","pillargroup.mom");
		fillComboBox("frmGridBasedAddEmployee", "cmbLocation","companyCombo.commonFilter"); 
		fillComboBox("frmGridBasedAddEmployee","cmbetcuRoleKeyid","roleMst.commonFilter"); 
		fillComboBox("frmGridBasedAddEmployee","cmbetcmFunctionId","trade.commonFilter"); 
		 formatDateBox('dteSessiondate','dd-MMM-yyyy');
		 fillWithCurrentDate('dteSessiondate');
		 fillWithCurrentDate('dteSessiondate');

		//fillComboBox("frmGridBasedAddEmployee", "cmbspu","");
		var url = jQuery("#hiddenUrl").val();
		var flid=jQuery("#hdnflid").val();
		//alert(jQuery("#hdnuniq").val());
		//alert("The flid"+flid);
	//	alert(jQuery("#hdncalendar").val());
		var keyid=jQuery("#hdncalendar").val();

		

		//jQuery("#level").combobox('clear');
	  //  alert(keyid);
	    jQuery("#cmbLocation").combobox("disable");
		//viewGrid(url, "q=2&flid="+flid);
		fillComboBox("frmGridBasedAddEmployee","cmbTrainingEmployee","employee.commonFilter");
		fillComboBox("frmGridBasedAddEmployee","cmbRole","employeeRole.commonFilter");
		fillComboBox("frmGridBasedAddEmployee","cmbTrainingPillarGrp","pillargroup.mom");
		fillComboBox("frmGridBasedAddEmployee","cmbSession","Sessionlist.ntrc?&calkeyid="+keyid);
		
		//fillComboBox("frmNewUniquePosition","cmbSession","Sessionlist.ntrc?&calkeyid="+keyid);

		fillComboBox("frmGridBasedAddEmployee","cmbEtcmSectionid","sectionCombo.commonFilter" );
		fillComboBox("frmGridBasedAddEmployee","cmbEtcmJHId","cellCombo.commonFilter" );

   	 var sectionid=jQuery('#hdnSectionId').val();
       // alert(' sectionidsectionid'+sectionid);
   		jQuery('#cmbEtcmSectionid').combobox('setValue',sectionid);
   		var cellId=jQuery('#hdnJhId').val();
   		//alert(cellId +"cellIdcellId");
	    var flid=jQuery("#hdnflid").val();

   		//alert(flid+" Flid");
			reloadCombo("frmGridBasedAddEmployee","cmbEtcmJHId","cellCombo.commonFilter?sectionid="+sectionid );
			reloadCombo("frmGridBasedAddEmployee","cmbetcuRoleKeyid","roleMst.commonFilter?flid="+flid);


   		if(cellId==null||cellId=='undefined'||cellId==''){

   		}
   		else{
   			jQuery('#cmbEtcmJHId').combobox('setValue',cellId);

   		}
   		var mode=jQuery('#hdnmode').val();
   	//	alert(mode);
   	 if(mode=="view"){
		  disableForm("frmGrdUniqPos");
	  }
		viewGrid(url, "q=2");

	});		
	
	//----- vignesh ------------- 2dec2025 ----------------------------//
	
// jQuery('#btnNewView').click(function() {
// 	//alert(123);
// 				//var url = jQuery('#hiddenUrl').val();
// 				var ds="q=2"; 
				
// 				/* var cmbLevelVal=getFieldValue("level");
// 				var EmployeeType=getFieldValue("cmbEmployeetype");
// 				//alert("EmployeeType"+EmployeeType)
// 				var EmployeeGender=getFieldValue("cmbEmpmGender");
// 				 */
				
// 				var flid=jQuery('#hdnflid').val();
				
// 			    var DmtVal =jQuery('#cmbEtcmSectionid').combobox('getValue');
// 			    var JhVal =jQuery('#cmbEtcmJHId').combobox('getValue');
// 			    var UniqPos =jQuery('#cmbetcuRoleKeyid').combobox('getValue');
// 			    var FunctId =jQuery('#cmbetcmFunctionId').combobox('getValue');
// 			   // alert(jQuery("#cmbLocation").combobox('getValue'));
// 			       var trngType=null;
// 			       if(jQuery("#chkOtherEmployee").is(':checked')== true){
// 			    	    trngType="";
// 			       }
// 			       else{
// 			    	   trngType=jQuery('#hdnTrngType').val();
// 			       }
// 			    if (DmtVal!=null){
// 					ds+="&SectionId="+DmtVal;
// 			 }
// 			 if (JhVal!=null){
// 					ds+="&cellId="+JhVal;
// 			 }
// 			 if (UniqPos!=null){
// 					ds+="&UniqPos="+UniqPos;
// 				//	alert(UniqPos +"UniqPos");
// 			 }
// 			    if(FunctId!=null)
// 			    	{
// 			    	ds+="&FunctId="+FunctId;
// 			    	}
			   
// 			    	ds+="&flid="+flid;
			   
// 			    var keyid = jQuery('#hdncalendar').val();
// 			  //  alert(keyid);
// 			    ds+="&refDocId="+keyid+"&trngType="+trngType;
			    
			   
// 			 //   alert(ds+"fnLocationVal"+FunctId);

// 		// COmmented on 14oct23 by Kiran	processGridnew("EmployeeAdd_input.gbtc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");

				
// 		});
	
// jQuery 1.6.4-safe: unbind any old handler, then bind a fresh one
jQuery('#btnNewView').unbind('click').bind('click', function () {

  // build query string compactly (skip null/empty)
  var ds = 'q=2';
  function add(name, val){
    if (val != null && val !== '') {
      ds += '&' + name + '=' + encodeURIComponent(val);
    }
  }

  // read current selections
  var flid    = jQuery('#hdnflid').val();
  var DmtVal  = jQuery('#cmbEtcmSectionid').combobox('getValue'); // Section
  var JhVal   = jQuery('#cmbEtcmJHId').combobox('getValue');      // Cell
  var UniqPos = jQuery('#cmbetcuRoleKeyid').combobox('getValue');
  var FunctId = jQuery('#cmbetcmFunctionId').combobox('getValue');

  var trngType = jQuery('#chkOtherEmployee').is(':checked') ? '' : (jQuery('#hdnTrngType').val() || '');
  var keyid    = jQuery('#hdncalendar').val();

  // add params
  add('SectionId', DmtVal);
  add('cellId',    JhVal);
  add('UniqPos',   UniqPos);
  add('FunctId',   FunctId);
  add('flid',      flid);
  add('refDocId',  keyid);
  add('trngType',  trngType);

  // now actually load the grid
  // (this is the call you had commented out)
  processGridnew(
    'EmployeeAdd_input.gbtc',
    ds,
    'NewUniqueGrid',
    'pager',
    '', '', '', 'SessionEmploadComplete', ' '
  );

  return false; // prevent default
});

		//----- vignesh ------------- 2dec2025 ----------------------------//
	
	
jQuery("#level").combobox(
		{
			onSelect : function(recordid) {
				jQuery("#cmbLocation").combobox('clear');
				reloadCombo("frmGridBasedAddEmployee", "cmbLocation",
						getComboUrl(recordid.text));
				//jQuery("#cmbLocation").focus();						
			}
		});
    
function  frmGridBasedAddEmployeecmbEtcmSectionid_onSelect(record)
{
		reloadCombo("frmGrdBsdTrgCal","cmbEtcmJHId","cellCombo.commonFilter?sectionid="+record.id);

}
  
     
function getComboUrl(cmbTxt) {
	var cmbUrl = null;
	

	
	var locationId=jQuery("#hdnlocnid").val();
	if (cmbTxt == 'Company')
		cmbUrl = "companyCombo.commonFilter";
	if (cmbTxt == 'Location')
		cmbUrl = "location.funlocn";
	if (cmbTxt == 'SBU')
		{
		cmbUrl = "sbuCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
	if (cmbTxt == 'PBU')
		{
		cmbUrl = "pbuCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
	if (cmbTxt == 'DMT')
		{
		cmbUrl = "sectionCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
	if (cmbTxt == 'JH')
		{
		cmbUrl = "cellCombo.commonFilter?locnId=" + locationId;
		jQuery("#cmbLocation").combobox("enable");
		}
    	return cmbUrl;

}
	/*function viewGrid(url, filterString){
      processGridnew("EmployeeUniqueAdd_input.ntrc", filterString,"NewUniqueGrid", "pager", "", "doubleClick");
	}*/
	
	
	/* jQuery("#btnView").click(function() {
alert(123);
			var url = jQuery('#hiddenUrl').val();
			var ds="q=2"; 
			var  dataString="q=2";
			var cmbLevelVal=getFieldValue("level");
			var EmployeeType=getFieldValue("cmbEmployeetype");
			//alert("EmployeeType"+EmployeeType)
			var EmployeeGender=getFieldValue("cmbEmpmGender");
			
			
			var flid=jQuery("#hdnflid").val();
			
		    var DmtVal =jQuery("#cmbEtcmSectionid").combobox('getValue');//******
		    var JhVal =jQuery("#cmbEtcmJHId").combobox('getValue');
		    var UniqPos =jQuery("#cmbetcuRoleKeyid").combobox('getValue');
		    var FunctId =jQuery("#cmbetcmFunctionId").combobox('getValue');
		   // alert(jQuery("#cmbLocation").combobox('getValue'));
		 //  alert("fnLocationVal"+fnLocationVal);
		    if (DmtVal!=null){
				ds+="&DmtVal="+EmployeeType;
		 }
		 if (JhVal!=null){
				ds+="&JhId="+EmployeeType;
		 }
		 if (UniqPos!=null){
				ds+="&UniqPos="+EmployeeGender;
		 }
		    if(FunctId!=null && fnLocationVal.length > 0)
		    	{
		    	ds+="&FunctId="+fnLocationVal;
		    	}
		    else{
		    	ds+="&flid="+flid;
		    }
		    var keyid = jQuery('#hdncalendar').val();
		    ds+="&TrainingId="+keyid;
		   
		processGridnew("EmployeeAdd_input.gbtc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");

			
	});
	
 */
 jQuery("#chkOtherEmployee").click(function(){
	//  valueChanged() Commented by Kiran on 14Oct 2023
});
 
/*  function  fnSelectAllEmp(){
		var row = jQuery("#EmpAttenGrid").jqGrid('getDataIDs');

		for(var i=0;i<row.length;i++)
		 {
			if(jQuery("#chkSelectAllEmp").is(':checked')== true){ */
 function valueChanged()
 {    	 

     //if(jQuery('.chkOtherEmployee').is(":checked"))   
    	 if(jQuery("#chkOtherEmployee").is(':checked')== true)
     { 
		 var ds="q=2"; 		
			
			var flid=jQuery('#hdnflid').val();
			
		    var DmtVal =jQuery('#cmbEtcmSectionid').combobox('getValue');
		    var JhVal =jQuery('#cmbEtcmJHId').combobox('getValue');
		    var UniqPos =jQuery('#cmbetcuRoleKeyid').combobox('getValue');
		    var FunctId =jQuery('#cmbetcmFunctionId').combobox('getValue');
		  //  var trngType=jQuery('#hdnTrngType').val();
		   // alert(jQuery("#cmbLocation").combobox('getValue'));
		
		  
		    if (DmtVal!=null){
				ds+="&SectionId="+DmtVal;
		 }
		 if (JhVal!=null){
				ds+="&cellId="+JhVal;
		 }
		 if (UniqPos!=null){
				ds+="&UniqPos="+UniqPos;
		 }
		    if(FunctId!=null)
		    	{
		    	ds+="&FunctId="+FunctId;
		    	}
		   
		    	ds+="&flid="+flid;
		   
		    var keyid = jQuery('#hdncalendar').val();
		  //  alert(keyid);
		    ds+="&refDocId="+keyid;//+"&trngType="+trngType; 
		    
		   
		 // alert(" In side the Checked");
//alert(ds);
		processGridnew("EmployeeAdd_input.gbtc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");

	 }
	 else{
		// jQuery("#chkOtherEmployee").prop("checked",false);

		// alert(" In side the UnChecked");
		 viewGrid();
	 }
 }
	
	
	function  frmGridBasedAddEmployeecmbRole_onSelect(record)
	{
		var flid=jQuery("#hdnflid").val();
		var role=record.id;
		var keyid = jQuery('#hdncalendar').val();
		//alert(role);
	  //  processGridnew("EmployeeUniqueAdd_input.ntrc","?q=2&flid="+flid+"&role="+role+"&TrainingId="+keyid,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");	
	}
	function  frmGridBasedAddEmployeecmbTrainingPillarGrp_onSelect(record)
	{
		var flid=jQuery("#hdnflid").val();
		var role=record.id;
		if(role!=null && role.length >1)
			{
			jQuery("#cmbRole").combobox('clear');
			//alert("role="+jQuery("#cmbRole").combobox('getValue'));
			jQuery("#cmbLocation").combobox('clear');
			//alert("Location="+jQuery("#cmbLocation").combobox('getValue'));
			}
		var keyid = jQuery('#hdncalendar').val();
		//alert("keyid"+keyid);
	   // processGridnew("EmployeeUniqueAdd_input.ntrc","?q=2&flid="+flid+"&pillarrole="+role+"&TrainingId="+keyid,"NewUniqueGrid", "pager","","","","SessionEmploadComplete","");	
	}

	  function chkEmployee_onsuccessCallBack(result)
	  {
		  var empcount=result.empcnt;
		  var Mstkeyid=result.keyid;
		  var empid=result.empid;
		  var sessionid=result.SessionId;
		  if(empcount==0)
			  {
		  var ds="Mstkeyid="+Mstkeyid+"&upEmpId="+empid+"&SessionId="+sessionid;
		   if(empid.trim().length>0 && sessionid.trim().length>0){
			 processAjaxCalls("entAddEmployeeUp_save.ntrc",ds,'employeebySave_successCallBack','');
		   }
			  }
		   else{
			   alert("Already this employee is selected for this session!!!!!");
			   return false;
		   }
			 
	  }	  

	  // ------ vignesh altered for save ---//
	  function upEmpSave(){
		    // alert(123);
		    var Mstkeyid  = jQuery("#hdncalendar").val();
		    var upEmpId   = jQuery('#cmbTrainingEmployee').combobox('getValue');
		    var SessionId = jQuery('#cmbSession').combobox('getValue');
		    // alert("The SessionId"+SessionId);

		    var ds = "Mstkeyid=" + Mstkeyid + "&upEmpId=" + upEmpId + "&SessionId=" + SessionId;
		    if (SessionId && SessionId.trim().length > 0) {
		        // alert(123);
		        processAjaxCalls("entAddEmployeeUp_save.ntrc", ds, 'employeebySave_successCallBack', '');
		    }
		}

		jQuery("#btnSaveEmpAdd").click(function () {
		   // alert("inside save button");
		    var empid = jQuery('#cmbTrainingEmployee').combobox('getValue');
		    if (empid == null || empid === "" || empid === 'undefined') {
		        dataSave();
		    } else {
		        upEmpSave();
		    }
		});

		function dataSave() {

		    var sessionid = jQuery('#cmbSession').combobox('getValue');
		    // alert("The sessionid::::"+sessionid);
		    var keyid     = jQuery('#hdncalendar').val();
		    var prmStrgth = jQuery('#hdnPrmtStrg').val();

		    if (sessionid && sessionid.length !== 0) {

		        var selRowIds = jQuery('#NewUniqueGrid').jqGrid('getGridParam', 'selarrrow');
		        // alert ('The number of selected rows: ' + selRowIds.length);

		        var paramconvertArr = convertJsonArr() || "[]";
		        // alert("paramconvertArr"+paramconvertArr);

		        // ✅ Encode JSON so [ { } " spaces, etc. don’t break the URL
		        var paramconvertArrParam = encodeURIComponent(paramconvertArr);

		        // ✅ Build a safe, RFC-compliant URL
		        var url = "entAddEmployee_save.ntrc"
		            + "?sessionid="      + encodeURIComponent(sessionid || "")
		            + "&keyid="          + encodeURIComponent(keyid     || "")
		            + "&paramconvertArr=" + paramconvertArrParam;

		        // if(selRowIds<=prmStrgth){
		        saveForm("frmGridBasedAddEmployee", url);
		        // } else { ... }

		    } else {
		        alert("Without select Session Unable to Save Employee!!!! ");
		        return false;
		    }
		}

	  
	  
// 	  jQuery("#btnSaveEmpAdd").click(function(){
// 		  alert("inside save button");
// 		  var empid=jQuery('#cmbTrainingEmployee').combobox('getValue');
// 		  if(empid==null || empid=="" || empid=='undefined' ){
// 			  dataSave()
// 		  }
// 		  else{
// 			  upEmpSave();
// 		  }
// 		  });
// 			  function dataSave(){
	  
// 		  var sessionid=jQuery('#cmbSession').combobox('getValue');
// 		// alert("The sessionid::::"+sessionid);
// 		 var keyid = jQuery('#hdncalendar').val();
// 		 var prmStrgth = jQuery('#hdnPrmtStrg').val();

		 
// 		 if(sessionid.length!=0 && sessionid!=null)
// 			 {
// 			 var selRowIds = jQuery('#NewUniqueGrid').jqGrid('getGridParam', 'selarrrow');
// 			// alert ('The number of selected rows: ' + selRowIds.length);
// 		     var paramconvertArr =convertJsonArr();
// 		//   alert("paramconvertArr"+paramconvertArr);
// 	  		 //var paramconvertArr=paramconvertArr;
// 	  		// if(selRowIds<=prmStrgth){
// 	  	     saveForm("frmGridBasedAddEmployee","entAddEmployee_save.ntrc?&sessionid="+sessionid+"&keyid"+keyid+"&paramconvertArr="+paramconvertArr);
// 	  		 /* }
// 	  		 else{
// 	  			 alert("You Have Selected More  than Permitted Strength !!!! ");
// 				 return false;
// 	  		 } */
// 	  		 }else{
// 				 alert("Without select Session Unable to Save Employee!!!! ");
// 				 return false;
// 			 }
			
// 	  };	 
	  	
// 	  function upEmpSave(){
// 		 // alert(123);
// 			var Mstkeyid=jQuery("#hdncalendar").val();
// 			var upEmpId=jQuery('#cmbTrainingEmployee').combobox('getValue');
// 			var SessionId=jQuery('#cmbSession').combobox('getValue');
// 		//	alert("The SessionId"+SessionId);
		
			 
			  
// 			  var ds="Mstkeyid="+Mstkeyid+"&upEmpId="+upEmpId+"&SessionId="+SessionId;
// 			   if(SessionId.trim().length>0){
// 				 //  alert(123);
			   
// 				 processAjaxCalls("entAddEmployeeUp_save.ntrc",ds,'employeebySave_successCallBack','');
// 			   }
				  
			
			
// 			/* 
			
// 			if(SessionId.length!=0&&SessionId!=null)
// 				{
// 	   processAjaxCalls('chkEmployee.ntrc','&keyid='+Mstkeyid+"&empid="+upEmpId+"&SessionId="+SessionId,'chkEmployee_onsuccessCallBack','chkEmployee_onerrorCallBack');
// 				}
// 			else{
// 				alert("Without select Session Unable to Save Employee!!!! ");
// 				return false; */
			
			
// 		/*	if(SessionId.length!=0&&SessionId!=null)
// 				{
// 			var ds="Mstkeyid="+Mstkeyid+"&upEmpId="+upEmpId+"&SessionId="+SessionId;
// 		   if(upEmpId.trim().length>0 && SessionId.trim().length>0){
// 			 processAjaxCalls("entAddEmployeeUp_save.ntrc",ds,'employeebySave_successCallBack','');
// 		  }
// 		   else{
// 				  alert("Select the Employee & Session");
// 			  }
// 				}
// 			else{
// 				alert("Without select Session Unable to Save Employee!!!! ");
// 				return false;
// 			}*/
			
// 			}
  // ------ vignesh altered for save ---//
	  jQuery("#btnClose").click(function(){
		  closePopUpDialoge("divAddEmployee");
			jQuery('#trgCalendarGrid').trigger('reloadGrid');

	  });
	  
	  
	
		function convertJsonArr(){
			var allrow = jQuery("#NewUniqueGrid").jqGrid('getRowData');
			var jsonArrO = '';
			var val = "";
			for ( var i = 0; i < allrow.length; i++) {
				var row = allrow[i];
				var rowno = parseInt(i) + 1;
				if(jQuery('#jqg_NewUniqueGrid_' + rowno).is(':disabled')==true)
					{
					continue;
					}
	               
	                
				
				if (jQuery('#jqg_NewUniqueGrid_' + rowno).is(':checked')) {
					
					jsonArrO += '{';
					for ( var colName in row) {
						if (row[colName].substring(0, 6) != '<input') {
							jsonArrO += '"' + colName + '":"' + row[colName] + '",';
						} else {
							var x = row[colName].indexOf("id=") + 4;
							var y = row[colName].substring(x);
							var z = y.indexOf('"');
							var cellId = y.substring(0, z);
							if (jQuery("#" + cellId).attr("type") == "checkbox"){
								val = jQuery('#' + cellId).is(':checked') ? 'Y'
										:'N';					  
							} else {
								val = jQuery('#' + cellId).val();
							}
							jsonArrO += '"' + colName + '":"' + val + '",';
						}
					}
					jsonArrO = jsonArrO.slice(0, -1) + '},';
				}
			}
			
			return '[' + jsonArrO.slice(0, -1) + ']';
		}
     
		function EmpChkBoxChk(rowId){
			var MstId = jQuery('#hdncalendar').val();
			//alert("The MstId:::"+MstId);
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'txtetceEtcmKeyid',MstId);
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'selctVal','1');
		}
		function EmpChkBoxUnChk(rowId){
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'txtetceEtcmKeyid'," ");
			jQuery("#NewUniqueGrid").jqGrid('setCell',rowId,'selctVal','0');
		}
		
		
   function NewUniqueGrid_selectRow(id){
	   if(jQuery('#jqg_NewUniqueGrid_'+id).is(':checked'))
	  		EmpChkBoxChk(id);
	  	else
	  		EmpChkBoxUnChk(id);
	  }  
	  
   function viewGrid() {
	    var ds="q=2";
		var flid=jQuery('#hdnflid').val();
	    var keyid = jQuery('#hdncalendar').val();
	    var locationId = jQuery('#hdnlocnid').val();
	    var sectionId = jQuery('#hdnSectionId').val();
	    var jhId = jQuery('#hdnJhId').val();
	  //  var tradeId=jQuery("#hdnTragdeId").val();
		var trngType=jQuery("#hdnTrngType").val();
 
			ds+="&SectionId="+sectionId;	
			ds+="&cellId="+jhId;	 
	    	ds+="&locationId="+locationId;	    	
	    	ds+="&flid="+flid;	  
	        ds+="&refDocId="+keyid+"&trngType="+trngType;//+"&tradeId="+tradeId
	   // alert(ds+"fnLocationVal"+FunctId);
		/* var keyid = jQuery('#hdncalendar').val();
		var uniq=jQuery("#hdnuniq").val(); */
	//	alert("The TrainingID::"+keyid);
	    var flid=jQuery("#hdnflid").val();
	//	alert("The Flid"+flid);
		
		
		
		processGridnew("EmployeeAdd_input.gbtc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");
	   //  processGridnew("multipleUniquePositionLink_input.gbtc","q=2&TraKeyid=","UniqueGrid", "Uniquepagerid","","uniqPosdoubleclickGrid", "", "");

		//	 processGridnew("session_input.gbtc","q=2&TraKeyid=ETC000000000797","SesGrid","Sespagerid","","","","SessionEmploadComplete","");
	}  	
   
   
//    function viewGrid() {
// 	    // Build a clean query string (NO leading '?')
// 	    var ds = "?q=2";

// 	    var flid       = jQuery('#hdnflid').val()        || "";
// 	    var keyid      = jQuery('#hdncalendar').val()    || "";
// 	    var locationId = jQuery('#hdnlocnid').val()      || "";
// 	    var sectionId  = jQuery('#hdnSectionId').val()   || "";
// 	    var jhId       = jQuery('#hdnJhId').val()        || "";
// 	    var trngType   = jQuery("#cmbEtcmType").val()    || "";

// 	    // Append params exactly as before, but safely
// 	    ds += "&SectionId="  + encodeURIComponent(sectionId);
// 	    ds += "&cellId="     + encodeURIComponent(jhId);
// 	    ds += "&LocationId=" + encodeURIComponent(locationId);
// 	    ds += "&flid="       + encodeURIComponent(flid);
// 	    ds += "&refDocId="   + encodeURIComponent(keyid);
// 	    ds += "&trngType="   + encodeURIComponent(trngType);

// 	    // Reload grid with the same URL + new query string
// 	    jQuery('#NewUniqueGrid').jqGrid('GridUnload');
// 	    processGridnew(
// 	        "EmployeeAdd_input.gbtc",
// 	        ds,
// 	        "NewUniqueGrid",
// 	        "pager",
// 	        "",
// 	        "",
// 	        "",
// 	        "SessionEmploadComplete",
// 	        " "
// 	    );
// 	}

   
	/* function viewGrid() {
		var keyid = jQuery('#hdncalendar').val();
		var uniq=jQuery("#hdnuniq").val();
		//alert("The TrainingID::"+keyid);
	    var flid=jQuery("#hdnflid").val();
		//alert("The Flid"+flid);
		var ds ="?q=2&TrainingKeyid="+keyid+"&flid="+flid+"&TrainingId="+keyid+"&uniq="+uniq; 
		 processGridnew("session_input.ntrc","q=2&TraKeyid=ETC000000000797","Sespagerid","","","","","");
	}   */
  
   function SessionEmploadComplete(){
      var row = jQuery("#NewUniqueGrid").jqGrid('getDataIDs');
	  var cm = jQuery("#NewUniqueGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		   var etcskeyid = jQuery("#NewUniqueGrid").jqGrid('getCell',row[i],"txtEtceEtcsKeyid");	
		  // alert("etcskeyid"+etcskeyid);
		 
		   //jQuery('#jqg_grdbachEmployee_'+row[i])
		   if(etcskeyid.trim().length>0){ 
			  // jQuery('#jqg_grdbachEmployee_'+row[i]).attr('checked',true);
			    jQuery('#NewUniqueGrid').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_NewUniqueGrid_'+row[i]+']').attr('checked',true);
			    jQuery("#NewUniqueGrid").jqGrid('setCell',row[i],'selctVal','1');
			    jQuery('input:checkbox[id=jqg_NewUniqueGrid_'+row[i]+']').attr('disabled',true);
		   }
	 }
   }
   function employeebySave_successCallBack(result) {
		if (result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		else
			alert(result.successData.msg);
		jQuery("#trgCalendarGrid").trigger("reloadGrid");
	}
 /*  function viewGrid(url,filterString)
	{ 
		if( validateFilterSelection(filterString))
		{
			var functionalloc=getFilterValue(filterString, "cmbCircle");
			jQuery("#hiddencircle").val(functionalloc);
			var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
			jQuery("#hiddenRemoveBlank").val(removeBlank);
			filterString += '&Flid=f&firstClick=Y';	
            processGridnew(url,filterString,"NewUniqueGrid", "pager", "", "doubleClick");
  return true;
	}
return false;	
	}*/
 /*  function validateFilterSelection(filterString){
    return  true;
}*/
   
function frmGridBasedAddEmployee_FuntLocHierarchy_SuccessCallBack(result){  
		/*var flid =jQuery("#frmGridBasedAddEmployee input[id='flid']").val();
		if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
		{ 
		    reloadCombo("frmGridBasedAddEmployee","cmbTrainingEmployee","employee.commonFilter?&flid="+result.flId);
		}*/
viewGrid();
}


function frmGridBasedAddEmployee_successsCallback(result){
	// alert("unique Successcallback");
/*	var compl=getFieldValue("chkEtcmChkCompleted","frmNewTraCal");
	alert("compl"+compl);
	enableFormFields("frmNewTraCal","chkEtcmChkCompleted") ;*/
	var keyid = jQuery('#hdncalendar').val();
	//	alert("The TrainingID::"+keyid);
	    var flid=jQuery("#hdnflid").val();
		//alert("The Flid"+flid);
		//var ds ="?q=2&TrainingKeyid="+keyid+"&flid="+flid+"&TrainingId="+keyid; 
		//processGridnew("EmployeeUniqueAdd_input.ntrc",ds,"NewUniqueGrid","pager","","","","SessionEmploadComplete"," ");
	    jQuery("#NewUniqueGrid").trigger("reloadGrid");
}

/* function viewGrid(filterString){
//    processGridnew("NewuniquePositionLink_input.ntrc",filterString, "UniqueGrid", "Uniquepagerid","","uniqPosdoubleclickGrid", "", "");
	//   processGridnew("Faculty_input.ntrc",filterString,"Gengrid","pagerid","","facultydoubleclickGrid","","");
  } */
  
function sessiondoubleclickGrid(id){
	 var rowData = jQuery("#SesGrid").jqGrid('getRowData',id);
	 var sessionId=rowData.ETCS_KEYID;
	 var frmTime = rowData.FromTime;
	 var tilTime = rowData.ToTime;
	 var sessiondte=rowData.Session;
	 setFieldValue("dteSessiondate",sessiondte,"frmNewTraCal");
	 setFieldValue("spnsessionFromTime",frmTime,"frmNewTraCal");
	 setFieldValue("spnsessionTillTime",tilTime,"frmNewTraCal");
	 jQuery("#hdnSessionId").val(sessionId);  
	
}

function BtnFormatterDelete(id, options, rowObject)
{					
	var rowId = options.rowId;
	var gridId = options.gid;

	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
}

function deleterec(rowid,gridId){
	var mode=jQuery("#hdnMode").val();
	if(mode=="view"){
		alert("View Mode Not Possible To Delete");
		return false;
	}
	var keyId;
	if("Gengrid"==gridId)
		keyId = getGridCell(gridId,rowid,'etcf_keyid');
	else if("UniqueGrid"==gridId)
		keyId = getGridCell(gridId,rowid,'etcu_keyid');
	else
	keyId = getGridCell(gridId,rowid,'etcs_keyid');
	var session=getGridCell(gridId,rowid,'etcs_name');
	if(session=="Session1")
		{
		 popupCommonErrorMsg("First session unable to delete,please modify session time");
		 return false;
		}
	 var TraKeyid = jQuery("#txtEtcmKeyid").val();
	 var r = confirm("Are You Sure To Delete?");
	  if(r==true){
	processAjaxCalls('deleteDetailRecord.ntrc','&keyid='+keyId+'&gridId='+gridId+"&TraKeyid="+TraKeyid,'deleteDetail_onsuccessCallBack','deleteDetail_onerrorCallBack');
	  }
	  else{
		  return false;
		  } 
	  }

function deleteDetail_onsuccessCallBack(result){ 
		  alert(result.msg);
	      jQuery("#"+result.gridid).trigger("reloadGrid");  
	}
  
function MUnique(){
	 //var JHName=jQuery("#cmbEtcuRoleJh").combobox("getValue");
	 //var DMTName=jQuery("#cmbEtcuRoleDmt").combobox("getValue");
	 var Calendarflid = jQuery("#frmNewTraCal input[id='flid']").val();
	// alert("Flid:"+Calendarflid);
	// alert(DMTName);
	 var CalendarId=jQuery("#txtEtcmKeyid").val();
	// alert(CalendarId);
	/* if(DMTName.length==0){
	  alert("Select the JH");
	  return false;
	  
	 }*/
	 //alert("Flid"+flid);
	 var sectionId = jQuery("#frmNewTraCal input[id='section']").val();
	 var cellId = jQuery("#frmNewTraCal input[id='cell']").val();
	 //alert(sectionId); EmpTrainingAtt_getCol.ntrc
	 //alert(cellId);
	LoadPopUp("DivMultiUniquePosition","multipleUniquePositionLink_input.gbtc?q=2&Calendarflid="+Calendarflid+"&CalendarId="+CalendarId+"&sectionId="+sectionId+"&cellId="+cellId,true,"800px","490px","4px","4%", "multiSelectOk_Callback","Multi Unique Position Add");

	}

</script>
<form id="frmGridBasedAddEmployee" name="frmGridBasedAddEmployee">
<tr>

         <div style="margin-left:15px;" class="easyui-paddingbfpx">
			 		
			 		<span style="margin-left:10px;"><label id= "" class="mandatory-lbl" >DMT</label> </span>
			 		<span style="margin-left:225px;"><label id= ""  >JH</label> </span>
			 		<span style="margin-left:268px;"><label id= ""  >Function</label> </span>
			 		<span style="margin-left:212px;Display:none;"><label id= ""  ></label>Unique Position</span>
			</div>
			<div> 
          <span style="margin-left:25px;">
						<input id="cmbEtcmSectionid" name="cmbEtcmSectionid" value="${requestScope.entTlTrgCalUnqp.etcuRoleKeyid}" class="easyui-combobox" style="width:240px;" />
			 		</span>
			 		<span style="margin-left:20px;">
						<input id="cmbEtcmJHId" name="cmbEtcmJHId" value="${requestScope.entTlTrgCalUnqp.etcuRoleKeyid}" class="easyui-combobox" style="width:260px;" />
			 		</span>
			 			<span style="margin-left:20px;">
			 				<input id="cmbetcmFunctionId" name="cmbetcmFunctionId" class="easyui-combobox" style="width:240px;" />
			 			</span>  
			 		<span style="margin-left:20px; Display:none;">
			 			<input id="cmbetcuRoleKeyid" name="cmbetcuRoleKeyid" class="easyui-combobox" style="width:240px;" />
			 		</span>
			 		<span style="margin-left:20px;">   
   						<input type="button" class="easyui-button" value="View" id="btnNewView"/>
   					<span style="margin-left:20px;">
			 		</div>
			 			<div style="margin-left:20x;margin-top:10px;">
			 			<span style="margin-left:10px;"><label id= "" class="mandatory-lbl" >Session</label> </span>
			 			<span style="margin-left:220px;"><label id= "" >Employee</label> </span>
			 			 <span class="easyui-paddingbfpx" style=" margin-left:20px; ">
				<input type="checkbox" id="chkOtherEmployee" name="chkOtherEmployee"   value="" /><!-- onchange="valueChanged()" /> -->
								
	  </span>
			 		<span style="margin-left:5px;"><label id= ""  >Others </label> </span>
	
			 			</div>
			 		<div style="margin-left:25px;margin-top:5px;">
       <input id="cmbSession" name="cmbSession" class="easyui-combobox" value="" style="width:170px;"/>
		 <span style="margin-left:85px;">
				<input id="cmbTrainingEmployee" name="cmbTrainingEmployee" class="easyui-combobox"  value="" style="width:260px;"/>
		
	  </span> 
	  <span style="margin-left:25px;">
<input type="button" class="easyui-button" value="Save" id="btnSaveEmpAdd"/>
 </span>
  <span style="margin-left:25px;">
<input type="button" class="easyui-button" value="Close" id="btnClose"/>
 </span>
	
	  
	   
	  <span class="easyui-paddingbfpx" style=" margin-left:20px; Display:none;">
				<input type="checkbox" id="chkOtherEmployee" name="chkOtherEmployee"   value="" /><!-- onchange="valueChanged()" /> -->
								
	  </span>
			 		<span style="margin-left:5px;Display:none;"><label id= ""  >Others Employee</label> </span>
				
				
		</div>
		
   
   <td>     
   

  	 <div style="margin-left:10px;margin-top:20px; display:none" >
	 <label><b>Specify Query Criteria and Select Employee to add to Training Program</label></b>	
	 </div>
	
	 
	 	<div style="margin-top:20px; margin-left:9px;">
		<table id="NewUniqueGrid">
		</table>
		<div id="pager"></div>
	</div>
  
  
</tr> 



 


<input type="hidden" id="mode" name="mode" value="create" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="${requestScope.locnid}" />
<input type="hidden" id="hdnlocaid" name="hdnlocaid" value="${loginLocnId}" />
<input type="hidden" id="hdncalendar" name="hdncalendar" value="${requestScope.hdnkeyid}" />
<input type="hidden" id="hdnSectionId" name="hdnSectionId" value="${requestScope.sectionId}" />
<input type="hidden" id="hdnJhId" name="hdnJhId" value="${requestScope.cellId}" />
<input type="hidden" id="hdnTragdeId" name="hdnTragdeId" value="${requestScope.tradeId}" />
<input type="hidden" id="hdnTrngType" name="hdnTrngType" value="${requestScope.trainigType}" /> 
<input type="hidden" id="hdnPrmtStrg" name="hdnPrmtStrg" value="${requestScope.prmtStrngth}" />
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}" />

</form>

<%-- <input type="hidden" id="hdnuniq" name="hdnuniq" value="${requestScope.uniq}" /> --%>





<%-- <tr>
       
         <td>
        <div class="easyui-paddingbfpx" style="padding-top: 0px;">
				<label style="padding-left: 10px;">Function Location Level</label>
				<label style="padding-left: 55px;">Functional Location</label>
				
			</div>
			<div>
				<span style="vertical-align: top; padding-left: 8px;"> 
				     <select id="level" class="easyui-combobox" name="level" style="width: 130px; height: 20px;">
						<!-- <option value="cmp">Company</option>
						<option value="lcn">Location</option> -->
						<option value=" "> </option>
						<option value="sbu">SBU</option>
						<option value="subunt">PBU</option>
						<option value="sect">DMT</option>
						<option value="cell">JH</option>

				    </select>
				</span> 
				<span style="padding-left: 35px; padding-left: 45px\9; vertical-align: top;">
					<input id="cmbLocation" name="cmbLocation" class="easyui-combobox" style="width: 180px; height: 20px;" />
				</span>
				<span id="msg" style="padding-left: 10px;vertical-align: top;padding-top: 1px;">
				
					
				</span>
				
			
			</div>
       
      <td>
		 		<div style="margin-left:380px;margin-top:10px"><label id="" >Role</label></div>
				<div style="margin-left:380px"; class="easyui-paddingbfpx">
				<input class="easyui-combobox" id="cmbRole" name="cmbRole"  style=" width : 200px;"  value=" " />
				</div>
				<td >
	 			<!-- <div style="margin-left:620px;margin-top:43px">
	 				<label>Session</label>
	 				<span style="margin-left:60px;"><label>From</label></span>
	 				<span style="margin-left:35px;"><label>To</label></span>
	 			</div>
	 			<div class="easyui-paddingbfpx" style="margin-left:620px; width : 330px;">
				 	<input id="dteSessiondate" name="dteSessiondate" class="easyui-datebox" style="width:90px;"  />
					
					<span style="margin-left:10px;">
						<input  id="spnsessionFromTime" name="spnsessionFromTime" class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /> 
					</span>
					<span style="margin-left:10px;">
						<input  id="spnsessionTillTime" name="spnsessionTillTime"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /> 
					</span>

					<span style="margin-left:10px;">
						<input type="button" class="easyui-button" value="Add" id="btnSessionsave"/> -->
					<!--  </span>	
				<span style="margin-left:320; margin-top:-20px;"> 
				<input style="margin-left:360; margin-top:-28px;" type="button" class="easyui-button" value="Multiple Unique Position" id="btnMultipleUnique" onclick="MUnique();" ></input>
			</span>		 	
				</div>-->
				 		</td>
	 		
         
   <td>
  
 
         
	       <td style="margin-left:-1px;" valign="top">
	 	    	<div style="margin-left:10px; margin-top:-135px;"><label id="">Employee</label>
		 	    	<span style="margin-left:8px;">
						<input type="checkbox"  id="chkTrainingOthers" name="chkTrainingOthers" value=""> 
			 		<label>Others</label></span>
			 		</div>
				<div class="easyui-paddingbfpx" style=" width : 200px; margin-left:10px;">
				<input id="cmbTrainingEmployee" name="cmbTrainingEmployee" class="easyui-combobox" style="width:300px; value="" />
				
				</div>
				
	 </td>	
	 	
	   
	     <td>
         <div style="margin-top:-45px; margin-left:200px;">
         <span style="margin-left:180px;">
		<!-- <label id="">Session</label> -->
		<label id="" class="mandatory-lbl">Session</label>
		</span>
		<div style="margin-left:180px;">
       <input id="cmbSession" name="cmbSession" class="easyui-combobox" value="" style="width:150px;"/>
		</div>
		
		</div>
		
		</td>
   
   <td>     
   <div style="margin-left:545px;margin-top:-27px;">
  <input type="button" class="easyui-button" value="Add" id="btnEmpSave" onclick="upEmpSave();"/>
 </div>
  </td>   
  	
	
	 
   <td>
		 		<div style="margin-left:15px;margin-top:12px"><label id="" >Pillar Group</label></div>
				<div  style="margin-left:15px"; class="easyui-paddingbfpx" >
			<input class="easyui-combobox" id="cmbTrainingPillarGrp" name="cmbTrainingPillarGrp"  style=" width : 185px;"  value=" "/>
		 </div>
	 	    </td>
	 	    <td>   <div style="margin-top:-38px; margin-left:200px;">
  <label style="padding-left: 50px;">Employee Type</label></div>
  <div style="padding-left: 250px;">
	   <select id="cmbEmployeetype" class="easyui-combobox" name="cmbEmployeetype" style="width: 150px; height: 20px;">
														
										<option value=" "> </option>
									<option value="R"> EMPLOYEE</option>
									<option value="A"> ASSOCIATE</option>
									<option value="C"> CONTRACT</option>
									<option value="M"> MANAGER</option>
									<option value="B"> BADLI</option>
									<option value="T"> TRAINEE</option>
									<option value="E"> EXECUTIVE</option>
									<option value="O"> OTHERS</option>
							</select></div>
				      <td>
		 <div style="margin-left:415px;margin-top:-40px;"><label class="mandatory-lbl">Gender</label></div> 
						<div class="easyui-paddingbfpx"style="margin-left:415px;" > 
						<select id="cmbEmpmGender" name="cmbEmpmGender" style="width:100px;">
									<option value="M">MALE</option>
									<option value="F">FEMALE</option>
							</select>
				        </div>
				         <td>      
   <div style="margin-left:535px;margin-top:-31px;">
   <input type="button" class="easyui-button" value="View" id="btnView"/>
   </div>
   </td>
	 <td>
	 <div style="margin-left:10px;margin-top:20px;">
	 <label><b>Specify Query Criteria and Select Employee to add to Training Program</label></b>	
	 </div>
	 </td>	
	 
	
	
	 
	 	<div style="margin-top:18px; margin-left:9px;">
		<table id="NewUniqueGrid">
		</table>
		<div id="pager"></div>
	</div>
  
   
</tr> 


<div style="margin-left:480px;margin-top:10px;">
<input type="button" class="easyui-button" value="Save" id="btnSave"/>
<input type="button" class="easyui-button" value="Close" id="btnClose"/>
 </div>
 

<input type="hidden" id="mode" name="mode" value="create" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="${requestScope.locnid}" />
<input type="hidden" id="hdnlocaid" name="hdnlocaid" value="${loginLocnId}" />
<input type="hidden" id="hdncalendar" name="hdncalendar" value="${requestScope.keyid}" />
<input type="hidden" id="hdnuniq" name="hdnuniq" value="${requestScope.uniq}" /> 
</form>--%>