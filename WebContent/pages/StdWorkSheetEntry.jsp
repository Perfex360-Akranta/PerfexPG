<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	
jQuery(document).ready(function(){	
		initialiseForm("frmStdWorkSheetGrid");
		var roleId = jQuery("#hdnUserRole").val();
	    var keyid = jQuery("#txtStwsKeyid").val();
	    var createdBy = jQuery("#hdnCreatedBy").val();
	    var currentUser = jQuery("#hdnCurrentUser").val();
	    
	    // DEBUG: Log all values to see what's being loaded
	    console.log("=== DEBUG INFO ===");
	    console.log("KeyID: " + keyid);
	    console.log("Role ID: " + roleId);
	    console.log("Current User: " + currentUser);
	    console.log("Created By: " + createdBy);
	    console.log("Is Valid KeyID: " + (keyid != null && keyid != '' && keyid.trim().length > 0));
	    console.log("==================");
	    
	    // Check if it's modification mode (keyid exists) and role validation
	    if(keyid != null && keyid != '' && keyid.trim().length > 0) {
	        // Allow access if user is DMT LEADER OR the creator of the record
	        var isDmtLeader = (roleId == 'AROL0003');
	        var isCreator = (currentUser == createdBy);
	        
	        console.log("Is DMT Leader: " + isDmtLeader);
	        console.log("Is Creator: " + isCreator);
	        console.log("Access Granted: " + (isDmtLeader || isCreator));
	        
	        if(!isDmtLeader && !isCreator) {
	            console.log("Access DENIED");
	            console.log("User Role: " + roleId);
	            console.log("Current User: " + currentUser);
	            console.log("Created By: " + createdBy);
	            
	            // Hide all form content
	            jQuery('#frmStdWorkSheetGrid').hide();
	            jQuery('#wrapperRpt').hide();
	            
	            // Show error message
	            if(typeof popupCommonErrorMsg === 'function') {
	                popupCommonErrorMsg(" Only DMT LEADER or the creator can modify this record.");
	            } else {
	                alert("Only DMT LEADER or the creator can modify this record.");
	            }
	            
	            // Redirect back after showing message
	            setTimeout(function() {
	                navigateToPrevForm();
	            }, 2000);
	            
	            return false;
	        }
	    }
	    
		jQuery('#submitForm').val('frmStdWorkSheetGrid');
        var mainForm = jQuery("#mainFormValClti").val();
		viewGrid("STDWorkSheetMain_input.stdwosh","?q=2"); 
		
		readOnlyFields("txtStwsBudgetedtime");
		if (mainForm !=true) 
		{
		 jQuery(".clitdiv").attr('id', 'wrapperRpt');
		}        
         var url = jQuery('#hiddenUrl').val();
		 var tableCaption = "Equipment Query";
         jQuery("#btnViewPDF").click(function(){
		   processAjaxCalls("openFile.file?fileName=standrizedWork_sheet.xlsx", "", "", "", "", "viewPdf");					
		});
		 var factId = jQuery("#frmStdWorkSheetGrid input[id='factory']").val();
	     var sectionId = jQuery("#frmStdWorkSheetGrid input[id='section']").val();
	     var cellId = jQuery("#frmStdWorkSheetGrid input[id='cell']").val();
	     var machId = jQuery("#frmStdWorkSheetGrid input[id='machine']").val();
	     var flid = jQuery("#frmStdWorkSheetGrid input[id='flid']").val();
	     var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+"&flid="+flid+"";
	     loadFunctionalLocation("stdfunLoc","functionalLoc.stdwosh","frmStdWorkSheetGridLocationfunLocationValues","frmStdWorkSheetGrid",dataStr);
	     formatDateBox('dteStwsBudgetedtime','dd-MMM-yyyy');
		 formatDateBox('dteStwsDate','dd-MMM-yyyy');
		
		//fillComboBox("frmStdWorkSheetGrid","cmbStwsApprovedby","employee.commonFilter" );
		 fillComboBox("frmStdWorkSheetGrid","cmbStwsProcess","process.commonFilter?&flid="+flid );
		 fillComboBox("frmStdWorkSheetGrid","cmbStwsBy","employee.commonFilter" );
		 fillWithCurrentDate('dteStwsDate');
		 numericTextBox('txtStwsBudgetedtime') ;
		 numericTextBox('txtStwsCycletime') ; 
		 fileManagerPopUp("","STD","frmStdWorkSheetGrid","btnfilemgr","StdWorkFilemgr");
		 
		 

});


function dteStwsDate_onSelect(record) {
	completedDateEvt();
} 


function completedDateEvt()
{
	var currentDate = getServerDateTime();
	var dteStwsDate = jQuery('#dteStwsDate').datebox("getValue");
	
	if(convertStringToDate(dteStwsDate)> currentDate)
	{
		alert('Standardized Date should not exceed current date');
		fillWithCurrentDate('dteStwsDate');
	}
}


jQuery("#btnAddyyy").click(function()
{
	var row  = jQuery("#StdWoSheetGrid").jqGrid('getDataIDs');
	addRow(row);//row add function	 	
});

jQuery("#btnDelete").click(function()
{ 
	removeRecord();
});
function viewGrid(url,filterString)
    	{
        	var tableCaption = "STD";
    		var keyid=jQuery("#txtStwsKeyid").val();
    		if (keyid.trim() != "" || keyid.length<0){
    			filterString += "&keyid="+keyid;
    		}else {
    			filterString = " ";
    		}
    		
    processGridnew(url,filterString,"StdWoSheetGrid","Pager",tableCaption,"doubleClickDetailsGrid","","");
}
function doubleclick(id){
	      var vnew= "No";
	/*
	var rowData = jQuery('#list').jqGrid("getDataIDs",id);
	var Process = rowData.Process;
	var TypeOfManpower = rowData.TypeOfManpower;
	var FunctionalLocation = rowData.FunctionalLocation;
	var MajorSteps = rowData.MajorSteps;
	var DateTime = rowData.DateTime;
	var PreparedBy = rowData.PreparedBy;
	var ApprovedBy = rowData.ApprovedBy;
	navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&Process='+Process+'&TypeOfManpower='+TypeOfManpower+'&FunctionalLocation='+FunctionalLocation+'&MajorSteps='+MajorSteps+'&DateTime='+DateTime+'&PreparedBy='+PreparedBy+'&ApprovedBy='+ApprovedBy,"Standardized Work Sheet");
	*/
	//navigateToNextForm('STDWorkSheetForm_input.stdwosh'+'?q=1&new='+vnew,"Standardized Work Sheet");
}


function frmStdWorkSheetGrid_FuntLocHierarchy_SuccessCallBack(result)
{   
	setFunctionalLocWidth('frmStdWorkSheetGrid','600px');
	var flid =jQuery("#frmStdWorkSheetGrid input[id='flid']").val();
	//alert(" flid :: "+flid);
	fillComboBox("frmStdWorkSheetGrid","cmbStwsApprovedby","employee.commonFilter?&flid="+flid);
	reloadCombo("frmStdWorkSheetGrid","cmbStwsProcess","process.commonFilter?&flid="+flid );
}

function frmStdWorkSheetGrid_deleteSuccessCallback(result)
{
       alert(result.successData.msg);
	   clearForm('frmStdWorkSheetGrid');
	   navigateToPrevForm(); 
       jQuery('#StdWoSheetGrid').trigger("reloadGrid");
 }

function  frmStdWorkSheetGridcmbStwsProcess_onSelect(record)
{
	var KEYID=record.id;
	//alert(" Checking Now :: "+KEYID);
	processAjaxCalls("STDWorkSheetMain_recall.stdwosh?&KEYID="+KEYID,"","recallsuccessCallBack","errorCallBack");
	
}

function recallsuccessCallBack(result){  //alert(" Result :: "+result[0][0]+" Result :: 1 "+result[0][1]+" Result :: 2 "+result[0][2]);

	//alert(result[0][0]);
	
	setFieldValue('txtStwsBudgetedtime',result[0][0]);
     
}

function remove_successCallBack(result)
{
	alert(result.successData);
	jQuery("#StdWoSheetGrid").trigger("reloadGrid");
}
function remove_errorCallBack() 
{
}

function removeRecord(keyid) {
	
	var Sheetrow = jQuery("#StdWoSheetGrid").jqGrid('getDataIDs');//	row get data
	var r = confirm("Do You Want To Delete?");
    for (i = 0; i < Sheetrow.length; i++) {
    var rowid = Sheetrow[i];
        keyid = jQuery("#StdWoSheetGrid").jqGrid('getCell', rowid, "txtStwdKeyid");
        var gridvalue="&stdWorkSheetdelete="+getGridSelectArray("StdWoSheetGrid");
        
		//alert(keyid);
	if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {

		
	}

 }
			if (r == true) {
				   processAjaxCalls("StdWork_remove.stdwosh", "&keyid="+keyid+"&gridvalue="+gridvalue, 'remove_successCallBack','remove_errorCallBack');
				} else
					return false;
				
           	}
function addRow(row)
{
	if ( row == null || row == '' || parseInt(row) <= 0) {
		var emptyItem =[{txtStwdMajorsteps:" ", txtStwdTypeofmanpower:" ", txtStwdMantime:" ",txtStwdProcesstime:" ", txtStwdWaittime:" ", txtStwdTraveltime:" "}];     //immedaia
		jQuery("#StdWoSheetGrid").jqGrid('addRowData',1, emptyItem[0]);
}	
	 else
{
		 for(var i=0;i<row.length;i++)
         lastRow = row[i];
		var emptyItem =[{txtStwdMajorsteps:" ", txtStwdTypeofmanpower:" ", txtStwdMantime:" ",txtStwdProcesstime:" ", txtStwdWaittime:" ", txtStwdTraveltime:" "}];     //immedaia
		jQuery("#StdWoSheetGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}	

/* function frmStdWorkSheetGrid_beforeSubmit() 
{
    var gridvalue="&stdWorkSheet="+getGridSelectArray("StdWoSheetGrid");
    return gridvalue;
} */



function frmStdWorkSheetGrid_beforeSubmit() 
{
    // Primary check: dmt hidden field
    var dmtValue = jQuery("#frmStdWorkSheetGrid input[id='dmt']").val();
    
    // Fallback check: read directly from functional location breadcrumb text
    // e.g. "Company / 5000 / SBU(N/A) / FINANCE / DMT-10 /"
    var funLocText = jQuery("#stdfunLoc").text().trim();
    var slashCount = (funLocText.match(/\//g) || []).length;
    
    console.log("DMT hidden field value: [" + dmtValue + "]");
    console.log("FunLoc breadcrumb: " + funLocText);
    console.log("Slash count: " + slashCount);
    
    // DMT selected = at least 5 slashes (Company/Location/SBU/PBU/DMT/)
    var isDmtSelected = (dmtValue != null && dmtValue != '' && dmtValue.trim() != '')
                        || (slashCount >= 5);
    
    if(!isDmtSelected) {
        if(typeof popupCommonErrorMsg === 'function') {
            popupCommonErrorMsg("DMT/AET/SET is mandatory. Please select a value in Functional Location.");
        } else {
            alert("DMT/AET/SET is mandatory. Please select a value in Functional Location.");
        }
        return false;
    }

    var gridvalue = "&stdWorkSheet=" + getGridSelectArray("StdWoSheetGrid");
    return gridvalue;
}
function btnfilemgr_click()
{
 	var keyid =jQuery('#txtStwsKeyid').val();   
  	
	if(keyid != null && keyid != ''){
			fileManagerPopUp(keyid,"STD","","","");
	}else {
		saveForm('frmStdWorkSheetGrid','STDWorkSheetMain_save.stdwosh?filemanger=filemanger');
	}
}                            
function frmStdWorkSheetGrid_successsCallback(result)
{
	clearForm("frmStdWorkSheetGrid");
	navigateToPrevForm();
    jQuery("#StdWoSheetGrid").trigger("reloadGrid");
    
    var filemanager=result.filemanger;
    var keyid=result.StwsKeyid;
    
    if(result.successData.mode=="Modify")
	{
	navigateToPrevForm("/STDWorkSheet_view.stdwosh");
	}
    
    if(filemanager==true)
   	{
   		fileManagerPopUp(keyid,"STD","","","");
   	}else{
   		clearForm('frmStdWorkSheetGrid');
   	}
   		
    	
}




</script>
<form id="frmStdWorkSheetGrid" name="frmStdWorkSheetGrid">
<div id="wrapperRpt" style="width:100%;margin-left:160px;margin-top:140px;" >
<table>
<tr>
<td colspan="2">
<div id="frmStdWorkSheetGridFuntKeyIds"  >							
	<input type="hidden" id="factory" name="factory"  value="" ></input>
	<input type="hidden" id="section" name="section"  value=""></input>
	<input type="hidden" id="cell"    name="cell"     value=""></input>
	<input type="hidden" id="machine" name="machine"  value=""></input>
	
	 <input type="hidden" id="location" name="location" value=""></input>
    <input type="hidden" id="sbu"      name="sbu"      value=""></input>
    <input type="hidden" id="pbu"      name="pbu"      value=""></input>
    <input type="hidden" id="dmt"      name="cmbStwsDmt" value=""></input>
    <input type="hidden" id="flid" name="txtStwsFlid"  value="${requestScope.newStdTlStdworksheetmst.stwsFlid}"></input>


	<div id="stdfunLoc" style="width:500px;"></div></div>
	
	</td>
	<td>
	<div style="position:relative;">
	   <span  id="StdWorkFilemgr" style="position:absolute;left:130px;top:-4px;" >
	   </span>
	   </div>
	</td>
</tr>
</table>

			<table style="width:100%">
		<tr>
		
		<td>
				<div class="easyui-paddingbfpx"> 
				<label class ="mandatory-lbl"> Process </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="cmbStwsProcess" name="cmbStwsProcess" class="easyui-combobox" value="${requestScope.newStdTlStdworksheetmst.stwsProcess}"  style="width:300px;" />
               </div>
			</td>
			
			<td style="padding-left: 22px">
				<div class="easyui-paddingbfpx"> 
				<label > Actual Budget Time(Mins) </label>
				<span style="padding-left:30px;"><label class ="mandatory-lbl"> Date </label></span>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="txtStwsBudgetedtime" name="txtStwsBudgetedtime"  class="easyui-text"  maxlength="15" value="${requestScope.newStdTlStdworksheetmst.stwsBudgetedtime}"  style="width:130px;text-align:right;"  />
				<span style="padding-left:44px;">
				<input id="dteStwsDate" name="dteStwsDate" class="easyui-datebox"  value="${requestScope.newStdTlStdworksheetmst.stwsDate}"  style="width:100px;\9; width : 108px; height : 22px;" />
				</span>
				
				</div>
				<table>
					<tr>
						<td>
						    <span id="err_txtStwsBudgetedtime" class="tpm-errormsg"></span>
						</td>
						<td>
						    <span id="err_dteStwsDate" class="tpm-errormsg"></span>
						</td>
					</tr>
				</table>
			</td>
			
			
			<td>
			    <div style="padding-left:20px;">
					<div class="easyui-paddingbfpx"> 
					<label class ="mandatory-lbl"> Sample Budgeted Time(Mins) </label>
					</div>
					<div  class="easyui-paddingbfpx">
					
				     <input id="txtStwsCycletime" name="txtStwsCycletime"  class="easyui-text"  maxlength="15" value="${requestScope.newStdTlStdworksheetmst.stwsCycletime}"  style="width:190px;"  />
					
					</div>
					
					<table>
					<tr>
						<td>
						<span id="err_txtStwsCycletime" class="tpm-errormsg"></span>
						</td>
					</tr>
		</table>
		
				</div>
			</td>
			</tr>
			<tr>
			<td style="width:200px">
				<div class="easyui-paddingbfpx"   style ="padding-right:20%"> 
				<label class ="mandatory-lbl">  By </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="cmbStwsBy" name="cmbStwsBy" class="easyui-combobox" value="${requestScope.newStdTlStdworksheetmst.stwsBy}"  style="width:300px;" />
				
				</div>
		
			
			<td style="width:200px;padding-left: 20px">
				<div class="easyui-paddingbfpx"   style ="padding-right:20%"> 
				<label class ="mandatory-lbl"> Approved By </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="cmbStwsApprovedby"  name="cmbStwsApprovedby" class="easyui-combobox"  value="${requestScope.newStdTlStdworksheetmst.stwsApprovedby}" style="width:290px;" />
				
				</div>
			</td>
			</tr>
			
			
	<tr>
	<td>	
			<div style="margin-left:-12px;">
<!--			<input type="button" class="easyui-button" value ="View Report" name="btnViewPDF" id="btnViewPDF" style="height: 23px; margin-left: 50px; "/>-->
			</div>
		 </td>
		 </tr>
		
		
		</table>
<!--		<span style=top:-5px;">-->
<!--      				<input type="button" class="easyui-button" style="width:45px;" id="btnAddyyy" name="btnAddyyy" value="Add"/></span>-->
<!--		             <input type="button" class="easyui-button" value ="Delete" id="btnDelete"  name="btnDelete" style="width:45px;""/> -->

		
		<div style="float:left;">
			 <table id="StdWoSheetGrid" ></table> 
			<div id="Pager"></div>
		</div> 
		
		 <input type="hidden" id="mode"/>
		 <input id="txtStwsKeyid" type='hidden' name="txtStwsKeyid" value="${requestScope.newStdTlStdworksheetmst.stwsKeyid}" />
	 <input type="hidden" id="hdnVal" name="hdnVal" value="1"/>
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
	<input type="hidden" id="hdnUserRole" value="${sessionScope.userRole}" />
<input type="hidden" id="hdnCreatedBy" value="${requestScope.newStdTlStdworksheetmst.stwsCreatedby}" />
<input type="hidden" id="hdnCurrentUser" value="${requestScope.currentUserCcno}" />
	
	</div>
	
</form>