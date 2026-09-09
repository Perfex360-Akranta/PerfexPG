<script>
jQuery(document).ready(function(){
	initialiseForm("frmMultipleSuggestion");
	jQuery("#submitForm").val("frmMultipleSuggestion");
	var url = jQuery("#hiddenUrl").val();
    viewGrid(url,"q=2");
    var location=jQuery("#hdnglbLocation").val();
    var JhFlid=jQuery("#hdnlogFlid").val();   

});



var factId = jQuery("#frmMultipleSuggestion input[id='factory']").val();
var sectionId = jQuery("#frmMultipleSuggestion input[id='section']").val();
var cellId = jQuery("#frmMultipleSuggestion input[id='cell']").val();
var machId = jQuery("#frmMultipleSuggestion input[id='machine']").val();
var flid = jQuery("#frmMultipleSuggestion input[id='flid']").val();
var hdnflid = jQuery("#hdnflid").val();
if (hdnflid!=null && hdnflid.trim().length>0 )
	flid=hdnflid;
	
var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
loadFunctionalLocation("frmKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmMultipleSuggestion",dataStr);

function viewGrid(url,filterString)
{
   //*Kp Suggestted to hide the Multiple Suggestion Entry for JH-4(Materials Finance,Project Capex and Secretarial-JH)*//	
   var location=jQuery("#hdnglbLocation").val();
   var JhFlid=jQuery("#hdnlogFlid").val();
   var JHname="JH-4(Materials Finance,Project Capex and Secretarial-JH)";
  if(JhFlid=="FNL000124048"){
	 // popupCommonErrorMsg("Unable to Access this Screen"+JHname);
	  alert("Unable to Access the Multiple Suggestion Entry Screen from "+JHname);
	  disableField("frmMultipleSuggestion","btnAdd");
	  disableField("frmMultipleSuggestion","btndelete");
	  navigateToPrevForm();
	  return false;
	 }
  else{
	  processGridnew(url,filterString,"SuggMultipleGrd","pager","","","");
		return true; 
  }
}

jQuery("#btnAdd").click(function(){
	var row=jQuery("#SuggMultipleGrd").jqGrid("getDataIDs");
	addRow(row);
});

function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnKzbnKeyid:" ",
			 dteKzbnDate:" ",
			 cmbKzbnSuggestedby:" ",
			 txtKzbnKaizen:" ",
			 cmbkzbnBenefit:" "} 
		 ];
	jQuery("#SuggMultipleGrd").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnKzbnKeyid:" ",
			 dteKzbnDate:" ",
			 cmbKzbnSuggestedby:" ",
			 txtKzbnKaizen:" ",
			 cmbkzbnBenefit:" "}];
		jQuery("#SuggMultipleGrd").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

jQuery("#btndelete").click(function()
	   	{ 
			 removeRecord();
		});

  function removeRecord(keyid){
   var Abnrow = jQuery("#SuggMultipleGrd").jqGrid('getDataIDs');
   for (var i = 0; i < Abnrow.length; i++) {
   var rowid = Abnrow[i];
   if (jQuery('#jqg_SuggMultipleGrd_'+Abnrow[i]).is(':checked') == true) {

   keyid = jQuery("#SuggMultipleGrd").jqGrid('getCell', rowid, "hdnAbndKeyid"); 

    if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
	    var r = confirm("Do You Want To Delete?");
		if (r == true) {
			//processAjaxCalls("MoMeeting_remove.mom", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
		} else{
			return false;
		} 
   	}else {
        var r = confirm("Do You Want To Remove Row?");
		if (r == true)
			jQuery("#SuggMultipleGrd").trigger("reloadGrid");
		else
			return false;
	}
 }
}	
}
 
  function SuggMultipleGrd_selectRow(rowId){
	  var KzbndateCtrl="dteKzbnDate_SuggMultipleGrd_"+rowId;
	  var ResultArea="txtKzbnPqcdsme_SuggMultipleGrd_"+rowId;
	  readOnlyFields(ResultArea);
	  setTimeout(function(){
	 		fillWithCurrentDate(KzbndateCtrl);
	 		},550); 
	  jQuery("#"+KzbndateCtrl).datebox({  	   
	 		onSelect:function(recordid)
	 			{ 
	 			isValidSuggestionDate(KzbndateCtrl,rowId);    	
	 			} 
	 		});
	  
	  jQuery("#cmbkzbnBenefit_SuggMultipleGrd_"+rowId).combobox({  	   
			onSelect:function(recordid)
				{ 
				rownum=rowId;
				var KEYID=recordid.id; //jQuery("#cmbkzbnBenefit_SuggMultipleGrd_"+rowId).combobox('getValue');
			    processAjaxCalls("kaizenThemeCategory_recall.kznbnk?&KEYID="+KEYID+"&rn="+rowId,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");		    	
				} 
			}); 
	 }
  
  function CategoryrecallsuccessCallBackCategory(result)
  {
	var jqGridId="SuggMultipleGrd";	  
  	var chkValue=result[0][1];
  	jQuery("#txtKzbnPqcdsme_"+jqGridId+"_"+rownum).val(chkValue);
  	rownum=null;
  }
  
   
  function isValidSuggestionDate(KzbndateCtrl,rowId){
	    var currdate=jQuery("#hdncurrentDate").val();
	    var approvalDate = getFieldValue(KzbndateCtrl);
		var currentDate = getServerDateTime();
		if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
			{
		var stringdate=convertStringToDate(approvalDate);
	if(convertStringToDate(approvalDate)== currentDate)
			{
			}
		if(convertStringToDate(currdate) < convertStringToDate(approvalDate))
		{  
			if(stringdate==convertStringToDate(currdate))
				{
				  clearValidationErrorMsg(KzbndateCtrl);
			    	return false;
				}
			else{
			 popupCommonErrorMsg('Date cannot be Greater than current Date');
			 fillWithCurrentDate(KzbndateCtrl);
			return false;
			}
		}
		}
	}


  function validateMandtory(gridSelArr){
  var selArr = JSON.parse(gridSelArr);
  for(var i = 0;i<selArr.length;i++){
  	if(selArr[i].cmbKzbnSuggestedby.trim()==""){
  		alert("Select SuggestedBy");
  		return false;
  	}

  	if(selArr[i].txtKzbnKaizen.trim()==""){
  	  	alert("Enter the Suggestion");
  	  	return false;
  	}
  }
  return true;
  }

  
function   frmMultipleSuggestion_beforeSubmit(){
	 var sectionId=jQuery("#frmMultipleSuggestion input[id='section']").val();
	 var cellId=jQuery("#frmMultipleSuggestion input[id='cell']").val(); 
	 var flId=jQuery("#frmMultipleSuggestion input[id='flid']").val();
	 if(cellId==null || cellId==''){
	  alert("Enter the JH");
	  return false;
	 }
	 var gridval=getGridSelectArray('SuggMultipleGrd');
	 if(gridval != ""){
	  if( validateMandtory(gridval))	
	    return 'SuggestionDetails='+gridval+"&flId="+flId+"&sectionId="+sectionId;
	 }
	 return false;
}
  
function frmMultipleSuggestion_successsCallback(result){
    
	var keyid=result.keyId;
	navigateToPrevForm();
	
}


</script>
<form id="frmMultipleSuggestion">
<div id='wrapperRpt' >
<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;">
<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
			<div  id="frmMultipleAbnormalityFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
			<input type="hidden" id="factory" name="factory"  value="" ></input>
			<input type="hidden" id="section" name="section"  value=""></input>
			<input type="hidden" id="cell"    name="cell"     value=""></input>
			<input type="hidden" id="machine" name="machine"  value=""></input>
			<input type="hidden" id="flid" name="cmbKzbnFlid"  value="${requestScope.kaizenbank.kzbnFlid }"></input>
			<input type="hidden" id="elementId" name="cmbKzbnElementid"  value="${requestScope.kaizenbank.kzbnElementid}"></input>							
			</div>
		   <input type="hidden" id="elementId" name="cmbKzbnElementid"  value="${requestScope.kaizenbank.kzbnElementid}"></input>							
			<div id="frmKaizenfunloc" style="width: 50%; "></div>
			<table>
			<tr>
			<td>	
			<span id="err_abnmfunLocation" class="tpm-errormsg"></span>
			</td>
			</tr>
			</table>
             <div style="margin-left:0px; margin-top:10px;">
						  <input type="button" class="easyui-button" value ="Add" id="btnAdd" style="height:25px;" >
						  </div>
						   <div style="margin-left:55px; margin-top:-25px;">
						  <span>
						  <input type="button" class="easyui-button" value ="Delete" id="btndelete" style="height:23px;" >
						  </span>	
						  </div>
						  </div>
						  </td>
						  </tr></table>
<div style="margin-top: 0px">
<table  id='SuggMultipleGrd' >
<tr>
<td>
</td>
</tr>
</table>
<div id='pager'></div>
</div>
</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="hdncurrentDate" name="hdncurrentDate" value="${requestScope.currentDate}">
<input type="hidden" id="hdnglbLocation" name="hdnglbLocation" value="${requestScope.glbLocation}">
<input type="hidden" id="hdnlogFlid" name="hdnlogFlid" value="${requestScope.logFlid}">
</form>