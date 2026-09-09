<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<style>

.padding
{
  padding-left:74px;
  padding-left:80px\9;
}

</style>
 <script type="text/javascript">	
  jQuery(document).ready(function(){
	  initialiseForm('frmAuditCreation');   
	  fillComboBox("frmAuditCreation","cmbSectionID","sectionCombo.commonFilter");
	  fillComboBox("frmAuditCreation","cmbCellId","cellCombo.commonFilter");
	  fillComboBox("frmAuditCreation","cmbJhamMachineid","machineCombo.commonFilter");
	  fillComboBox("frmAuditCreation","cmbJhamJhstepid","jhstepCombo.jhAudit" );
	  fillComboBox("frmAuditCreation","cmbJhamKeyid","jhAudit.commonFilter" );
	  fillComboBox("frmAuditCreation","cmbJhamAuditteamid","jhAuditLevel.commonFilter");
	  formatDateBox('dteJhamAuditdate','dd-MMM-yyyy');
	  fillWithCurrentDate('dteJhamAuditdate'); 
	  jQuery('#submitForm').val('frmAuditCreation'); 
	  var url = "jhAuditCreation_input.jhAudit";
	  var equipmentId = jQuery('#cmbJhamMachineid').combobox("getValue");
	  var jhamKeyID=jQuery('#cmbJhamKeyid').combobox("getValue");
	  jQuery("#txtJhamTotalpoints").attr('readonly','readonly');
	  jQuery("#txtJhamTotalpoints").attr('readonly','readonly');
	  jQuery("#txtJhamStatus").attr('readonly','readonly');
	  jQuery("#txtminmarks").attr('readonly','readonly');
	  //readOnlyFields('cmbJhamAuditteamid');
	  var status =  jQuery("#txtJhamStatus").val();
	  
	  if(status=='P'){
		 jQuery("#txtJhamStatus").val('Pass');
		 jQuery("#txtJhamStatus").css('background-color','#72a98b');
	  }else if(status=='F'){
		 jQuery("#txtJhamStatus").val('Fail'); 
		 jQuery("#txtJhamStatus").css('background-color','#ff9999');
	  }
	  if(equipmentId != null || equipmentId != ''){
		 viewGrid(url,"&s=1&machineId="+equipmentId+"&jhamKeyID="+jhamKeyID);
		 loadFunctionalLocation("frmAuditCreationfunLocation","functionalLoc.commonFilter","frmAuditCreationfunLocationfunLocationValues","frmAuditCreation","&machId="+equipmentId);
	  }else{
	  	 viewGrid(url,"&s=1");
	  	 var compId = jQuery("#frmAuditCreation input[id='company']").val();
		 var locnId = jQuery("#frmAuditCreation input[id='location']").val();
		 var factId = jQuery("#frmAuditCreation input[id='factory']").val();
		 var sectionId = jQuery("#frmAuditCreation input[id='seciton']").val();
		 var cellId = jQuery("#frmAuditCreation input[id='cell']").val();
		 var machId = jQuery("#frmAuditCreation input[id='machine']").val();
		 var flid = jQuery("#frmAuditCreation input[id='flid']").val();
		 
	     var dataStr = "&compId="+compId +"&locnId="+locnId +"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	     loadFunctionalLocation("frmAuditCreationfunLocation","functionalLoc.commonFilter","frmAuditCreationfunLocationfunLocationValues","frmAuditCreationfunLocation",dataStr);
	  }
	  disableField('frmAuditCreation','cmbJhamKeyid');
  });	
  function txtbox_pointsScored(cellValue, options, rowObject)
  {
	var rowId = options.rowId;	
	if(cellValue==null || cellValue=='' || cellValue==undefined){
		return "<input id='pointsScored_txtbox"+rowId+"' style='text-align:right;width:80px;height:40px;' name='pointsScored_txtbox' type='text'  onchange='{fillgrade(\""+rowId + "\");}' onkeypress='return isNumberKey(event);'  />";
	}else{
		return "<input id='pointsScored_txtbox"+rowId+"' style='text-align:right;width:80px;height:40px;' name='pointsScored_txtbox' type='text'  onchange='{fillgrade(\""+rowId + "\");}' onkeypress='return isNumberKey(event);' value ='"+cellValue+"' />";
	}	
  }	
  function viewGrid(url,filterString)
  {	
  	if( validateFilterSelection(filterString))
  	{	
  	    processGridnew(url,filterString,"jhNewAudit","pagerJHA","","","","auditGrid_Loadcallback");
  		return true;
  	}
  	return false;
  }
  function fillgrade(rowId){
	  var rowData = jQuery("#jhNewAudit").jqGrid('getRowData',rowId);
	  var keyID = rowData.txtJhadParameterid;
	  var parameterId = rowData.parameterid;
	  var maxPoints =rowData.txtJhadMaximumpoints;
	  var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');		
	  
	  var total =  jQuery("#txtJhamTotalpoints").val();
	  if(total == null || total == '' || total == ' ')
		  total ='0';
	  var point = jQuery("#jhNewAudit").jqGrid('getCell',rowId,"txtJhadPointsscored");
	  if(point.substring(0,6) == '<input')
	  {
		 var pointScored = jQuery("#pointsScored_txtbox"+rowId).val(); 
		 var totalpoint='0';
		 if(parseInt(pointScored)>parseInt(maxPoints))
		 {
			jQuery("#pointsScored_txtbox"+rowId).val('');	
			return false;
		 }
		 for(i=1;i<=jhNewAudit.length;i++)	
		 {
		    var row=jhNewAudit[i-1];
			var pointScore=jQuery("#pointsScored_txtbox"+row).val();
			if(pointScore==null || pointScore==''||pointScore==' '){
			}else{	
				totalpoint=parseInt(totalpoint)+parseInt(pointScore);
				jQuery("#txtJhamTotalpoints").val(totalpoint);
			}	
		 }			
		
	    pointScored = jQuery("#pointsScored_txtbox"+rowId).val(); 
	    
	  	if(pointScored==null || pointScored=='' || pointScored == ' '){
		  	pointScored=jQuery("#pointsScored_txtbox"+rowId).val();
	  		var pointvalue = parseInt(total) + parseInt(pointScored);
	  		jQuery("#txtJhamTotalpoints").val(pointvalue);
	  		processAjaxCalls("gradeid_recall.jhAudit","?q=2&point="+pointScored+"&keyID="+keyID+"&rowId="+rowId,"gradeRecallSuccess","gradeRecallError");
		}else {
  	  		processAjaxCalls("gradeid_recall.jhAudit","?q=2&point="+pointScored+"&keyID="+keyID+"&rowId="+rowId,"gradeRecallSuccess","gradeRecallError");
  	  	}
	 	 
  	}
  }
  function gradeRecallSuccess(result){
	  
	 if(result!=null || result!=undefined){
		 var keyid=result.keyid;
		
		 var grade=result.name;
		 var rowid = result.rowid;
		
		 jQuery("#jhNewAudit").jqGrid('setCell',rowid,"txtJhadGradeid",keyid);
		 jQuery("#jhNewAudit").jqGrid('setCell',rowid,"txtGrade",grade);
		
	 }
  }
  function gradeRecallError(result){
  }
  function auditGrid_Loadcallback(){
	  var row = jQuery("#jhNewAudit").jqGrid('getDataIDs');
	  var rowIds = jQuery("#jhNewAudit").getDataIDs();
	  var parameterId =  jQuery("#jhNewAudit").jqGrid('getCell', rowIds[0], 'parameterid');
	  
	  jQuery('#hdnParamId').val(parameterId);
	  
  }
  function frmAuditCreationcmbJhamMachineid_onSelect(record)
  {
	  var url = "jhAuditCreation_input.jhAudit";
	  viewGrid(url,"&s=1&machineId="+record.id);
	 
	  loadFunctionalLocation("frmAuditCreationfunLocation","functionalLoc.commonFilter","frmAuditCreationfunLocationfunLocationValues","frmAuditCreation","&machId="+record.id);
	  processAjaxCalls("jhStepFill.jhAudit",'&machineId='+record.id ,'filljhStep_OnSuccess','filljhStep_OnError');
  }
  function filljhStep_OnSuccess(result){
	var machineId=result.jhStepID.equipment;

	if(result.jhStepID.jhStepkey==null || result.jhStepID.jhStepkey==''){
		jQuery("#cmbJhamJhstepid").combobox('setValue','');
	}else{
		jQuery("#cmbJhamJhstepid").combobox('setValue',result.jhStepID.jhStepkey);
	}	
	if((result.jhStepID.auditL)==null || (result.jhStepID.auditL=='')){
		jQuery("#cmbJhamAuditteamid").combobox('setValue','');
		
	}else{
		jQuery("#cmbJhamAuditteamid").combobox('setValue',result.jhStepID.auditL);
	}
	if((result.jhStepID.msg)==null || (result.jhStepID.msg)=='' || (result.jhStepID.msg)==undefined){
	}else{
		alert(result.jhStepID.msg);
		jQuery('#cmbJhamAuditteamid').val('');
		return false;
	}
	if((result.jhStepID.minMarks)==null || (result.jhStepID.minMarks=='')){
		jQuery("#txtminmarks").val('');
	}else{
		jQuery("#txtminmarks").val(result.jhStepID.minMarks);
	}
		
  }
  function filljhStep_OnError(){

  }	
  function frmAuditCreationcmbJhamKeyid_onSelect(record){
	 processAjaxCalls("jhAuditCreation_recall.jhAudit?keyId="+record.id+"&mode=EDIT","cmbJhamKeyid_onSelectSuccess","cmbJhamKeyid_onSelectError");
  }
  function cmbJhamKeyid_onSelectSuccess(result){
	 
  }
  function cmbJhamKeyid_onSelectError(result){
	 	
	  	jQuery("#cmbJhamMachineid").combobox("setValue",result.jhAuditCreationdata.JhamMachineid);
		jQuery("#cmbJhamJhstepid").combobox("setValue",result.jhAuditCreationdata.JhamJhstepid);
		jQuery("#txtJhamAuditorname").val(result.jhAuditCreationdata.JhamAuditorname);
		jQuery("#dteJhamAuditdate").datebox("setValue",result.jhAuditCreationdata.JhamAuditdate);
		jQuery("#txtJhamLeadername").val(result.jhAuditCreationdata.JhamLeadername);
		jQuery("#txtJhamTotalpoints").val(result.jhAuditCreationdata.JhamTotalpoints);
		jQuery("#txtJhamStatus").val(result.jhAuditCreationdata.JhamStatus);
		
		jQuery("#cboJhamAuditortype").val(result.jhAuditCreationdata.JhamAuditortype);
		var url = "jhAuditCreation_input.jhAudit";
		viewGrid(url,"&s=1&machineId="+result.jhAuditCreationdata.JhamMachineid+"&jhamKeyID="+result.keyid);
		loadFunctionalLocation("frmAuditCreationfunLocation","functionalLoc.commonFilter","frmAuditCreationfunLocationfunLocationValues","frmAuditCreation","&machId="+result.jhAuditCreationdata.JhamMachineid);
		jQuery("#cmbJhamAuditteamid").combobox("setValue",result.jhAuditCreationdata.JhamAuditteamid);
	 	  if(status='P'){
			  jQuery("#txtJhamStatus").val('Pass');
		  }else if(status='F'){
			  jQuery("#txtJhamStatus").val('Fail'); 
		  }
		  
  }
  function frmAuditCreationcmbJhamAuditteamid_onSelect(record)
  {
	  var machineId = jQuery('#cmbJhamMachineid').combobox("getValue");
	  if(machineId==null || machineId=='' || machineId==' '){
	  }else{
		  processAjaxCalls("jhStepFill.jhAudit",'&machineId='+machineId ,'filljhStep_OnSuccess','filljhStep_OnError');
	  }
	  //var parameter=jQuery('#hdnParamId').val();
	  //processAjaxCalls("fillStatus_recall.jhAudit","?q=2&parameter="+parameter+"&auditTeam="+record.id+"&rowId="+rowId,"minPointsRecallSuccess","minPointsRecallError");
  }
  function minPointsRecallSuccess(result){
	  if(result!=null || result!=undefined){
			 var points=result.point;
			 jQuery('#hdnMinpoints').val(points);
	  }
  }
  function frmAuditCreation_beforeSubmit()
  {
	  
	  var jhNewAudit = jQuery("#jhNewAudit").jqGrid('getDataIDs');	
	
	  var machineId = jQuery('#cmbJhamMachineid').combobox("getValue");
	  
	  if (machineId==null || machineId=='' || machineId==' '){
	  }else{
		  if(jhNewAudit.length==0){
				alert('No parameters for this Machine');
			  	return false;
		  }
	  }
	 for(i=1;i<=jhNewAudit.length;i++)	
	 {
		var rowid=jhNewAudit[i-1];
		pointScored=jQuery("#pointsScored_txtbox"+rowid).val();
		if(pointScored== null || pointScored=='' || pointScored==' '){
			alert('Enter Scored Point for SI.No :'+rowid);
			return false;
		}
	 }		
	 var points =jQuery('#txtminmarks').val();
	 
	 if(points==null || points==''){
		points='0';
	 }
	 var totalPoints=jQuery('#txtJhamTotalpoints').val();
	 if(totalPoints==null || totalPoints==''){
		 totalPoints='0';
	 }
	
	 points = parseInt(points);
	 totalPoints = parseInt(totalPoints);
	 
	 if(totalPoints<points){
		jQuery('#txtJhamStatus').val('F');
	 }else{
		jQuery('#txtJhamStatus').val('P');
	 }
	 var gridData ='';
	 gridData += '&audit='+convertJHGridToJSONArr('jhNewAudit');
	
	 return gridData ; 
  }	 	
  function frmAuditCreation_successsCallback(result){
	  jQuery("#jhNewAudit").clearGridData();
  }
  function convertJHGridToJSONArr(jqGridId){
		
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		var jsonArrO='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			jsonArrO += '{';
			
			for(var colName in row) {
				//alert(row[colName]);
				if(colName != 'Item' && colName != 'Desc')
				{
				if(row[colName].substring(0,6)!='<input')
				{
					
					
						jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
				}
				else
				{
					var x=row[colName].indexOf("id=")+4;
					var y=row[colName].substring(x);
					var z = y.indexOf('"');	
					
								
						jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val() +'",'; 
				}
				}
				
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");		
		
		return jsonArrO; 
	}
  function isNumberKey(evt)
  {
     var charCode = (evt.which) ? evt.which : event.keyCode;
     if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
     return true;
  }

  function frmAuditCreation_exceptionCallback(result){

  }
  function frmAuditCreation_deleteSuccessCallback(result)
  {
	 //alert(result.successData.msg);
	 jQuery("#jhNewAudit").clearGridData();
  }	
  function dteJhamAuditdate_onSelect(){
  }
	
 </script>
 
 <form name="frmAuditCreation" id="frmAuditCreation" >
 <div id="wrapperRpt">
 <table id=" " border="0" width="80% " style=" " class="">
		<tr>
			<td colspan='3'>
				<div id="frmAuditCreationfunLocation" class="padding"></div>
			</td>
		</tr>
		
	 	<tr>
	 	
			<td  style="">
		 		<div  class="padding" style="margin-right:35px;">
			 	<div  class=""><label class="mandatory-lbl">Equipment</label><br></div>
			    <div  class="" style="width:280px;"><input id="cmbJhamMachineid" name="cmbJhamMachineid" type="text" class="easyui-combobox" style="float:right;width:300px;"  value="${requestScope.jhaTlAuditmst.jhamMachineid}"/></div>
			 	<div  class=""><label >JH Audit</label></div>
			 	<div  class="" style="width:280px;"><input id="cmbJhamKeyid" name="cmbJhamKeyid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.jhaTlAuditmst.jhamKeyid}"/> </div>
			 	<div  class=""><label>JH Step</label></div>
			 	<div  class="" style="width:280px;"><input id="cmbJhamJhstepid" name="cmbJhamJhstepid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.jhaTlAuditmst.jhamJhstepid}" /></div>
			 	</div>
		 	</td>
	 		<td valign="top" style=""  >
			 	<div  class=""><label class="mandatory-lbl" >Audit Level</label><br></div>
			    <div  class=""><input id="cmbJhamAuditteamid" name="cmbJhamAuditteamid" type="text" class="easyui-combobox" style="width: 300px;" value="${requestScope.jhaTlAuditmst.jhamAuditteamid}" o/></div>
			    <div class=""><label class="mandatory-lbl">Audit Name</label><br></div>
				 <div class=""><input id="txtJhamAuditorname" name="txtJhamAuditorname" type="text" class="easyui-text" style="width: 300px;"  value="${requestScope.jhaTlAuditmst.jhamAuditorname}"/></div>
			    <table>
			    <tr>
			    <td  class=""><label class="mandatory-lbl" >Audit Date</label>
			    <label class="mandatory-lbl" style="padding-left:58px;">Audit Type</label><br>
			    </td>
			     <tr>
			     <tr>
			    <td style="padding-left:0px;width:60%;">
			    <span style="float:left;padding-right:0px;">
					 <!-- here the changes have to made -->
					 	<input class="easyui-text" style=" width : 87px;" id="dteJhamAuditdate" name="dteJhamAuditdate" value="${requestScope.jhaTlAuditmst.jhamAuditdate}"/>
				      </span>
			   
			     <span style="margin-left:28px;margin-right:00%;">
				    <select  id="cboJhamAuditortype" class="easyui-combobox" name="cboJhamAuditortype" style=" width : 88px;height:23px" required="true" >
							<option value="I"> Internal </option>
							<option value="E"> External </option>
							
					 </select>
					 </span>
					 
					 </td>
					 </tr>	
					 </table>
				  
			</td>
			<td style="padding-left:20px;"  >
				<div style=" ">
					<div  class=""><label class="mandatory-lbl" >Leader</label><br></div>
				    <div  class=""><input id="txtJhamLeadername" name="txtJhamLeadername" type="text" class="easyui-text" style="width: 225px;" value="${requestScope.jhaTlAuditmst.jhamLeadername}" /></div>
				    <div  class=""><label class="mandatory-lbl" >Total Points</label><br></div>
				    <div class=""><input id="txtJhamTotalpoints" name="txtJhamTotalpoints" type="text" class="easyui-text" style="width: 100px;text-align:right;" value="${requestScope.jhaTlAuditmst.jhamTotalpoints}" /></div>
				    <div class="">
				    	<label>Min. Marks to pass</label>
				    	<label style="padding-left:16px;">Status</label><br>
				    </div>
				    <div style="width:250px;">
				    	<input id="txtJhamStatus" name="txtJhamStatus" type="text" class="easyui-text"  style="width:100px;background-color:transparent;" value="${requestScope.jhaTlAuditmst.jhamStatus}" />
				    	<span  style="padding-left:18px;"><input id="txtminmarks" name="txtminmark" type="text"  class="easyui-text" style="width: 100px; background-color:transparent ;text-align:right;" value="${requestScope.minMarks}"/></span>
				    </div>
				 </div>
			</td>
		 </tr>
 </table>
 	<div style="height:3px;float:left;margin-left:8%;">
 		<table id="jhNewAudit" style=" ">
			<tr>
				<td>
				<td/>
			</tr>
		</table>
		
		<div id="pagerJHA"></div>
	</div>
	</div>
	
 
 <input type="hidden" id="mode" name="mode" value=>
 <input type="hidden" id="hdnParamId" name="hdnParamId" />
 <input type="hidden" id="hdnMinpoints" name="hdnMinpoints" />


 </form>	
 	