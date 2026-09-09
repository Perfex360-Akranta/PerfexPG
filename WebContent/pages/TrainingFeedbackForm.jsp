<script>
/*
 * Created By Sathish Kumar.v
 */
jQuery(document).ready(function() {
	initialiseForm('frmTrainingFeedback');
	jQuery('#submitForm').val('frmTrainingFeedback');
	fillComboBox("frmTrainingFeedback","cmbtfmsFaculty","facultyCombo.commonFilter");
	fillComboBox("frmTrainingFeedback","cmbtfmsParticipantname","employeeFilter.commonFilter");
	fillComboBox("frmTrainingFeedback","cmbtfmsDesignation","designation.commonFilter");
	fillComboBox('frmTrainingFeedback','cmbtfmsTitle','program.commonFilter');
	fillComboBox("frmEmployeeNomination","cmbbatch","Batch.commonFilter" );
	jQuery("#cmbtfmsTitle").combobox({onRequest:function( ){
	    var flid = jQuery("#frmTrainingFeedback input[id='flid']").val();
		return  "&flId="+flid;
		
  	}});
	
	formatDateBox('dtetfmsDate', 'dd-MMM-yyyy');
	var factId = jQuery("#frmTrainingFeedback input[id='factory']").val();
    var sectionId = jQuery("#frmTrainingFeedback input[id='section']").val();
	var cellId = jQuery("#frmTrainingFeedback input[id='cell']").val();
	var machId = jQuery("#frmTrainingFeedback input[id='machine']").val();
	var flid = jQuery("#frmTrainingFeedback input[id='flid']").val(); 

	var dataStr = "&factId=" + factId
			+ "&sectionId=" + sectionId
			+ "&cellId=" + cellId + "&machId="
			+ machId+"&flid="+ flid;
	 // alert(dataStr);
	  loadFunctionalLocation("TrainingFBfunLocation", "functionalLoc.trfb", "TrainingFBfunLocation", "frmTrainingFeedback",dataStr);
			
	
	     var url = jQuery('#hiddenUrl').val();
		 var tableCaption = "";
		 var dataString="?q=1";
		 viewGrid(url,dataString,tableCaption);

	     var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnView").val(btnName);
		jQuery("#btnView").click(function()
		 {						
	        //processAjaxCalls("openFile.file?fileName=TrainingFeedBackForm.xlsx", "", "", "", "", "new");
		    var flid = jQuery("#frmTrainingFeedback input[id='flid']").val();
			var tfbfId=jQuery("#txttfmsKeyid").val();
			var title=getFieldValue("cmbtfmsTitle","frmTrainingFeedback");
			//alert(" title :: "+title);
			//alert(" tfbfId :: "+tfbfId+" flid :: "+flid);
			window.open("TrainingFeedbackForm_Excelview.trfb?&tfbfId="+tfbfId+"&flid="+flid+"&title="+title,"Excel View");
			
		 });
		 fileManagerPopUp("","TRF","frmTrainingFeedback","btnfilemgr","TraFilemgr"); 
	
});
function frmTrainingFeedbackcmbtfmsTitle_onSelect(record){
	processAjaxCalls("progExistData_input.tcl?progId="+record.id,"","selectedProg_onsuccesscallback");
}
function selectedProg_onsuccesscallback(result){  
	 jQuery('#txttfmsDuration').val(result[0].progMaxDuration);
	 setFieldValue("dtetfmsDate","01-"+result[0].progMonth,"frmTrainingFeedback");
}
function frmTrainingFeedback_FuntLocHierarchy_SuccessCallBack(result)
{
	
	var flid = result.flid;
	jQuery("#cmbTfmsFlid").val(flid);
	
}
function btnfilemgr_click()
{
	 var mskeyid=jQuery('#txttfmsKeyid').val(); 
	if(mskeyid != null &&mskeyid != '')
		{
		fileManagerPopUp(mskeyid,"TRF","","","");
	}
	
}

function viewGrid(url,dataString,tableCaption)
{
	var keyid=jQuery('#txttfmsKeyid').val(); 
    processGridnew("TraininggCourse_input.trfb",dataString+"&TypeCp=CP&keyid="+keyid,"CourseGrid","pagerCourse",tableCaption,"","","loadComFunction");
    processGridnew("TrainingInstructor_input.trfb",dataString+"&TypeIP=IP&&keyid="+keyid,"InstructorGrid","pagerInstructor",tableCaption,"","","loadComFunctionIp");
}

function txtTrainingformatter(cellValue, options, rowObject)
{	
	 var rowId = options.rowId;
	 var tickVal ="";	
	 var values = cellValue.trim().length > 0 ? cellValue.split("#") : null;
	 var mskeyid=jQuery('#txttfmsKeyid').val(); 
	  if( values != null && values[ 1 ] != "" && values[ 1 ] =="1")
      {  
		   //tickVal=getTickvalue();
	   if (isIE()) 
	   {
	       tickVal = "&#x2713;";
	   }
	     else
	   { 
	        tickVal = "&#10003;";
	    }
	  } 
	var formatStr  = '<div '; 
    formatStr  += ' style=\"color:blue;font-size:20px;width:100%;\" id="tickSpan_'+rowId+'_'+options.pos +'" '+ ( tickVal.length > 0 ? 'Feed="true" ' :  'Feed="false"'  ) + ' KEYID="'+ rowObject[2] +'"dtlKeyid="'+ rowObject[1] +'" mskeyid="'+mskeyid+'" val="'+ (values != null ? values[ 0 ]:"") +'" > ' + tickVal + '</div> ';
    
    //alert(formatStr);
    return formatStr;	 
}


function txtTrainingformatterInstructor(cellValue, options, rowObject)
{	
	  var rowId = options.rowId;	  
	  var tickVal ="";	
	  var mskeyid=jQuery('#txttfmsKeyid').val(); 
	  var values = cellValue.trim().length > 0 ? cellValue.split("#") : null;    
 
	  if(  values != null && values[ 1 ] != "" && values[ 1 ] =="1"){  
		  //tickVal=getTickvalue(); 
		  if (isIE()) 
	   {
	       tickVal = "&#x2713;";
	   }
	     else
	   { 
	        tickVal = "&#10003;";
	    }		   		
         } 
	var formatStr  = '<div '; 
    formatStr  += ' style=\"color:blue;font-size:20px;width:100%;\" id="tickSpanIp_'+rowId+'_'+options.pos +'" '+ ( tickVal.length > 0 ? 'Feedv="true" ' : ' Feedv="false"'  ) + ' KEYID="'+ rowObject[2]  + ' " dtlKeyid="' + rowObject[1]+'"  mskeyid="'+mskeyid+'" val="'+ (values != null ? values[ 0 ]:"") +'" > ' + tickVal +  '</div> ';
 //  alert(formatStr);
     return formatStr;

	 
}

function loadComFunction(ids)
{	
	jQuery( 'div[id^="tickSpan_"]').click(function()
	{	
	    var colm = jQuery("#CourseGrid").jqGrid("getGridParam", "colModel");
	    
		var id = jQuery(this).attr("id");
		var currntHtml = jQuery(this).html();
      
		var colIndex = parseInt(id.substring(id.length-1,id.length));
		var colIndexName = colm[colIndex].name;
		id = id.substring(0,id.length-1);
		  
		jQuery( 'div[id^="'+id + '"]').html('   ').attr("feed","false").attr("val",colIndexName);
		if(currntHtml != String.fromCharCode(10003)){ 
			var tick="";
			//tick=getTickvalue();
	    if (isIE()) {
	    	tick = "&#x2713;";
	     }
	     else{ 
	    	 tick = "&#10003;";
	    }// alert(tick);
	        jQuery( 'div[id="' + jQuery(this).attr("id") + '"]').html(tick).attr("feed","true").attr("val", colIndexName);
			}	
		 				

 });
	jQuery("#CourseGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {

			}});


}

function loadComFunctionIp(ids)
{
	
	jQuery( 'div[id^="tickSpanIp_"]').click(function()
	{
		    var colm = jQuery("#CourseGrid").jqGrid("getGridParam", "colModel");
			var id = jQuery(this).attr("id");
			var currntHtml = jQuery(this).html();
			
			var colIndex = parseInt(id.substring(id.length-1,id.length));
			//alert(colIndex); 
			var colIndexName = colm[colIndex].name; 
			id = id.substring(0,id.length-1);

			jQuery( 'div[id^="'+id + '"]').html('   ').attr("feedv","false").attr("val", colIndexName);

			if(currntHtml != String.fromCharCode(10003)){
				var tick="";
				//tick=getTickvalue();	
	if (isIE()) 
	   {
		tick = "&#x2713;";
	   }
	     else
	   { 
	    	 tick = "&#10003;";
	    }									
			jQuery( 'div[id="' + jQuery(this).attr("id") + '"]').html(tick).attr("feedv","true").attr("val", colIndexName);;	
			}
		
	});
	 
}

function frmTrainingFeedback_beforeSubmit()
{	 
	    var gridData  = '&FeedbackCheckData='+FeedbackCheckData()+'&FeeDbackIpcheakdata='+FeedbackIpCheckData();
		return gridData;		
}

function FeedbackCheckData(){

	
	var jsonArrO='[';
	jQuery("#CourseGrid").find('div[feed="true"]').each (function()
			{		    
				jsonArrO += '{';
				var keyid=jQuery(this).attr('KEYID');
				var val=jQuery(this).attr('val');
				var dtlKeyid = jQuery(this).attr('dtlKeyid');
				var MsKeyid = jQuery(this).attr('mskeyid');
			//	alert("Dtail Keyid:::"+dtlKeyid);
				if(dtlKeyid==" ")
					dtlKeyid="";
				jsonArrO += '"txttfmsKeyid":"'+ MsKeyid+"\",";
				jsonArrO += '"txtfbfdFermKeyid":"'+val+"\",";
				jsonArrO += '"txtfbfdFbpmKeyid":"'+ keyid+"\",";
				jsonArrO += '"txtfbfdKeyid":"'+ dtlKeyid+"\"";
				jsonArrO +=  "},";
			});
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert(" json Arr ::"+jsonArrO);
	return jsonArrO; 
}
function FeedbackIpCheckData(){
	
	var jsonArrO='[';
	
	jQuery("#InstructorGrid").find('div[feedv="true"]').each (function()
			{
				jsonArrO += '{';
				var aria=jQuery(this).attr('KEYID');
				var val=jQuery(this).attr('val');
				var dtlKeyid = jQuery(this).attr('dtlKeyid');
				var MsKeyid = jQuery(this).attr('mskeyid');
				if(dtlKeyid==" ")
					dtlKeyid="";
				jsonArrO += '"txttfmsKeyid":"'+ MsKeyid+"\",";
				jsonArrO += '"txtfbfdFermKeyid":"'+val+"\",";
				jsonArrO += '"txtfbfdFbpmKeyid":"'+ aria+"\",";
				jsonArrO += '"txtfbfdKeyid":"'+ dtlKeyid+"\"";
				jsonArrO +=  "},";
			});
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrIp ::  " +jsonArrO);
	return jsonArrO; 
}
function frmTrainingFeedback_successsCallback(result)
{	
	jQuery("#CourseGrid").trigger("reloadGrid");
	jQuery("#InstructorGrid").trigger("reloadGrid");
		             
}
function frmTrainingFeedback_deleteSuccessCallback(result){
	alert(result.successData.msg);
	jQuery("#CourseGrid").trigger("reloadGrid");
	jQuery("#InstructorGrid").trigger("reloadGrid");
	clearForm("frmTrainingFeedback");
}
</script>
<form id=frmTrainingFeedback>
<div id='wrapper' style="margin-top:16px;width:100%;">
        <div id="frmmomFuntKeyIds">
					<input type="hidden" id="factory" name="factory" value=""></input> 
					<input type="hidden" id="section" name=section value=""></input> 
					<input type="hidden" id="cell"    name="cell" value=""></input> 
					<input type="hidden" id="machine" name="machine" value=""></input>
					<input type="hidden" id="flid" name="cmbTfmsFlid" value="${requestScope.TRT.tfmsFlid}"></input>
		</div>
  <div style="padding-left:774px;padding-top:1px;padding-left:650px\9;"><span style="padding-right:5px;padding-left:10px\9;"><input class="easyui-button" type="button" id="btnView" name="btnView" value="View Report"></span><span  id="TraFilemgr" style="position:absolute;margin-left:3px;margin-left:15px\9;"></span></div>
     <div  class="easyui-paddingbfpx" id="TrainingFBfunLocation" style="width: 95%;width:104%\9;padding-left:28px;padding-left:0px\9;"> 
     		 </div>
<table style="padding-left:2.5%;">
<tr>
   <td><label class="mandatory-lbl">Feedback Title</label>
   <div><input  class="easyui-combobox" id="cmbtfmsTitle" name="cmbtfmsTitle" style="width:300px;height: 22px;" value="${requestScope.TRT.tfmsTitle}"/></div></td>
   <td style="padding-left:3%"><label class="mandatory-lbl">Date</label>
   <div class="easyui-paddingbfpx"><input class="easyui-text"id="dtetfmsDate" name="dtetfmsDate" maxlength="20"	style="height: 22px; width : 202px;"	value="${requestScope.TRT.tfmsDate}"/></div> 
   <td style="padding-left:6%;padding-left:3%\9;"><label class="mandatory-lbl">Duration</label> <div><input type="text"  class="easyui-text" id="txttfmsDuration" name="txttfmsDuration" style=" width : 104px; height: 22px;" value="${requestScope.TRT.tfmsDuration}"/></div></td>
   <td>
   <label class=" ">Batch</label> 
   <div><input type="text"  class="easyui-combobox" id="cmbbatch" name="cmbbatch" style=" width : 204px; height: 22px;" value="${requestScope.TRT.tfmsDuration}"/></div>
   </td>
   <td rowspan="2" style="padding-left:10%;padding-left:10px\9;">
   <div style='position:relative;'>
   <div style='position:absolute;right:25;top:-3;'>
   <label class="mandatory-lbl">Remarks</label>  
   <div class="easyui-paddingbfpx">
   <textarea id="txttfmsRemarks" name ="txttfmsRemarks" style="width:205px;width:200px\9;height:70px\9; ">${requestScope.TRT.tfmsRemarks}</textarea>
   </div>
   </div>
   </div>
   </td>
</tr>
<tr>
      <td>
         <label class="mandatory-lbl">Faculty/Instructor</label>
         <div>
           <input class="easyui-text"id="cmbtfmsFaculty" name="cmbtfmsFaculty"  style="height: 22px; width : 300px;"value="${requestScope.TRT.tfmsFaculty}" />
          </div>
      </td>
      <td style="padding-left:3%">
           <label class="mandatory-lbl">Participant Name</label>
          <div>
          <input class="easyui-text"id="cmbtfmsParticipantname" name="cmbtfmsParticipantname"  style="width: 200px;height: 22px;"value="${requestScope.TRT.tfmsParticipantname}" />
      </div>
     </td>
      <td style="padding-left:6%;padding-left:3%\9;">
      <label class="mandatory-lbl">Designation</label>
      <div>
      <input class="easyui-text"id="cmbtfmsDesignation" name="cmbtfmsDesignation"  style="width: 200px;height: 22px;"value="${requestScope.TRT.tfmsDesignation}" />
      </div>
      </td>
      <td></td>
<tr>

</table><br><br>
<div style="padding-left:27px;">
<table id='CourseGrid'>
		<tr>
				<td></td>
			</tr>
</table>
<div id='pagerCourse'></div>
<table id='InstructorGrid'>
		<tr>
			<td></td>
		</tr>
</table>

<div id='pagerInstructor'></div>
</div>
</div>
    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
    <input type="hidden" id="txttfmsKeyid" name="txttfmsKeyid" value="${requestScope.TRT.tfmsKeyid}"/>
	<input type="hidden" id="colNo" value=""/>
	<input type="hidden" id="hdnBtnName" value="View Report"/> 
	<input type="hidden" id="hdnVal" value=""/>
</form>