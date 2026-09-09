<script type="text/javascript">
var frmMode = jQuery('#hdnFrmMode').val();
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	 fillComboBox("frmAbnTeamPerform","cmbSect","sectionCombo.commonFilter");
	 fillComboBox("frmAbnTeamPerform","cmbCell","cellCombo.commonFilter");
	viewGrid(url,"q=2");
	 disableField("frmAbnTeamPerform","txtEmpmName");
	 disableField("frmAbnTeamPerform","txtEmpmCode");
	 disableField("frmAbnTeamPerform","txtToDate");
	 disableField("frmAbnTeamPerform","txtFromDate");
	 disableField("frmAbnTeamPerform","txtTotal");
	 disableField("frmAbnTeamPerform","cmbCell");
	 disableField("frmAbnTeamPerform","cmbSect");
	 var jh=jQuery("#hdnJH").val();
		// alert(jh);
		 var DMT=jQuery("#hdnDMT").val();
		 //alert(DMT);
		 //jQuery("#cmbSect").combobox("setValue",DMT);
		 setComboValueSilent("cmbSect", DMT);
		 //jQuery("#cmbCell").combobox("setValue",jh);
		 setComboValueSilent("cmbCell", jh);
	 if(jQuery("#hdnempKeyId").val() != null && jQuery("#hdnempKeyId").val() != '')
		{
			processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery("#hdnempKeyId").val() ,"getEmpImgSuccess","getEmpImgErr");
		}
});
function getEmpImgSuccess(result)
{ 

	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
	//	alert("inside");
	jQuery('#imgEmployee').attr('src','');
	jQuery('#imgEmployee').attr('src',result.empImg.imgToimBlobimage);	
	}
	else{
		 jQuery('#imgEmployee').attr('src', "tmp/images/LIGHTHOUSE_1335854915.JPG");	
	   
			}
		//setImgWidth( jQuery('#nodeImage'),580,432);
}
function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString))
	{
	   var empKeyId=jQuery("#hdnempKeyId").val();
		filterString +="&empKeyId="+empKeyId;
	//	alert(filterString);
		processGridnew("AbnormalityTeamPer_input.apdb",filterString,"AbnTeamgrid","pager","","","","","","");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){
	    return true;
}


</script>
<form name="frmAbnTeamPerform" id="frmAbnTeamPerform" >
<div id="wrapperRpt"  >


	<div class="easyui-paddingbfpx" style=" margin-left:80px;margin-top:-36px;"> 
		<div  style=" color:blue" ><label ><b>User Name</b></label></div> 
	                	<input class="easyui-text"  id="txtEmpmName" name="txtEmpmName"  style="width:200px; height : 21px;" value="${requestScope.EmployeeName}"  >
							<div  class="easyui-paddingbfpx" style="margin-top:6px;">
							 <div  style="color:blue "><label id="lblEmpmCode" ><b>User LoginId</b></label></div> 
              	  
							 <input  class="easyui-text" maxlength="12" id="txtEmpmCode" name="txtEmpmCode"  style="width:200px;height : 21px;" value="${requestScope.EmployeeLogin}"  >
				  </div>
						</div>
						</div>
	     <div style="float: left;  position: absolute; top: -4px; left: 10px;">
		<img id="imgEmployee" name="imgEmployee" src="images/EmpDefaultImg.jpg" width="100px" height="100px"/>
	</div>
	<div style="margin-left:350px;margin-top:-75px;">
<label class="mandatory-lbl">DMT</label>
	<input class="easyui-combobox" id="cmbSect" name="cmbSect" style="width: 240px;"	value="" />
	
</div>
<div  style=" color:blue;margin-left:650px;margin-top:-20px;" ><label ><b>Total Number of Records :</b></label>
<input class="easyui-text"  id="txtTotal" name="txtTotal"  style="width:50px; height : 21px;" value="${requestScope.TotalAbnormalityCount}"  >
</div> 
    
<div style="margin-left:360px;margin-top:22px;">
<label class="mandatory-lbl">JH</label>
		<!-- <input class="easyui-combobox" id="cmbPact" name="cmbPact" style="width: 200px;"	value="" /> -->
		<input class="easyui-combobox" id="cmbCell" name="cmbCell"  style="width:240px;"  value="" />
				
  
</div>
<div  style=" color:blue;margin-left:650px;margin-top:-20px;" ><label ><b>From Date:</b></label>
<input class="easyui-text"  id="txtFromDate" name="txtFromDate"  style="width:138px; height : 21px;text-align:center;" value="${requestScope.FYearStart}"  >

</div>

<div  style=" color:blue;margin-left:863px;margin-top:-20px;" ><label ><b>To Date:</b></label>
<input class="easyui-text"  id="txtToDate" name="txtToDate"  style="width:120px; height : 21px;text-align:center;" value="${requestScope.TillDate}"  >

</div>
	
	<div class="easyui-paddingbfpx"  style="padding-left:0%;margin-top:20px;" >
<table id="AbnTeamgrid"></table>
	<div id="pager"></div>
</div>
<input type="hidden" id='hdnFrmMode' value="${requestScope.mode}"/>
<input type="hidden" id='hdnempKeyId' value="${requestScope.empKeyId}"/>
<input type="hidden" id='hdnDMT' value="${requestScope.DMT}"/>
<input type="hidden" id='hdnJH' value="${requestScope.JH}"/>
<input type="hidden" id='hdnEmployeeLogin' value="${requestScope.EmployeeLogin}"/>
<input type="hidden" id='hdnEmployeeName' value="${requestScope.EmployeeName}"/>
</form>