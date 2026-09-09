<script>
jQuery(document).ready(function(){
    initialiseForm('frmAuditViewLst');
	var url = jQuery('#hiddenUrl').val();
	var userkeyid = jQuery('#userLoginid').val();
	var data = url.split("?");
	url = data[0];
	var filterSreing = data[1];
	var dlgimage =  jQuery("#btnimgEmpAdd");
	imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");
	processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#userLoginid').val() ,"EmpImgSuccess","EmpImgErr");
	processAjaxCalls("getEmployeeData.base?",'userkeyid='+userkeyid,'employee_OnSuccess','employee_OnError');
	//var urll="JhAuditLastThree_input.jhAuditItc";
	// processGridnew("JhAuditLastThree_input.jhAuditItc", filterSreing, "tblAuditViewLst", "repAuditViewPager", "", "", "","load_complete","selectRowFunction");	 
});

var employeeName=jQuery("#txtEmpName").val();
var employeePhone=jQuery("#txtEmpPhone").val();
var employeeEmail=jQuery("#txtEmpmEmail").val();

/* jQuery("#btnimgEmpAdd").click(function(){		
    var dlgimage =  jQuery("#btnimgEmpAdd");
	imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");	  
}); */
jQuery("#btnimgEmpDel").click(function(){	
	
	jQuery('#imgEmployee').attr('src', "images/EmpDefaultImg.jpg");	   		
	});
function imgEmpAddOnComplete(response)
{   //alert(12);
	jQuery("#hdnEmpImgUrl").val(response);
	var dlgimage =  jQuery("#btnimgEmpAdd");
	 imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");	
	
}
function EmpImgSuccess(result)
{ 
	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){	
	jQuery('#imgEmployee').attr('src','');
	jQuery('#imgEmployee').attr('src',result.empImg.imgToimBlobimage);	
	}
}

function employee_OnSuccess(result){
	var EmpName= jQuery("#txtEmpName").val(result.empmName);
	var EmpName= jQuery("#txtEmpPhone").val(result.empmMobile);
	var EmpmEmail=jQuery("#txtEmpmEmail").val(result.empmEmail);
	jQuery("#txtEmpmCode").val(result.empmNo);
	 jQuery('#hdnPillarId').val(result.empmPillar);
	 if("PM"!=result.empmPillar || "-"!=result.empmPillar)
		 jQuery('#btnLnkKpi').show();
	 else
		 jQuery('#btnLnkKpi').hide();
		if(jQuery.browser.msie ){ 
			jQuery('#dispFunctionalLoc').css('width','675px');
		}else{
			jQuery('#dispFunctionalLoc').css('width','735px');
		}	 
}
 function validateFilterSelection(filterString){
  return true;
 }
 
 jQuery("#btnUpdateImg").click(function(){
     var userkeyid=jQuery('#userLoginid').val();	 
	 var Imgurl=jQuery("#hdnEmpImgUrl").val();
	 processAjaxCalls("employeeupdateImage_save.emp","?q=2&userkeyid="+userkeyid+"&Imgurl="+Imgurl,"EmpImg_successCallBack","EmpImg_errorCallBack");
 });
 
 
 function EmpImg_successCallBack(result){
	 if(result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
			popupCommonErrorMsg(result.tpmException);
		 else
			alert(result.successData.msg);
 }
 
 function EmpImg_errorCallBack(result){
	 popupCommonErrorMsg(result);
	 }
	  
 jQuery("#btnUpdateEmp").click(function(){
 var userkeyid=jQuery('#userLoginid').val();	 
 var empName=jQuery("#txtEmpName").val();
 var empPhoneno=jQuery("#txtEmpPhone").val();
 var empEmail=jQuery("#txtEmpmEmail").val();	
 var ds="userkeyid="+userkeyid+"&empName="+empName+"&empPhoneno="+empPhoneno+"&empEmail="+empEmail;
 processAjaxCalls("employeeupdatedata_save.emp",ds,'EmployeeSave_successCallBack','EmployeeSave_errorCallBack');
 });
 
 
 
 function EmployeeSave_successCallBack(result){
	if(result.tpmException!=undefined &&  result.tpmException!=null  && result.tpmException!='undefined' )
		popupCommonErrorMsg(result.tpmException);
	 else
		alert(result.successData.msg);
}
 
function EmployeeSave_errorCallBack(result){
popupCommonErrorMsg(result);
}
 
</script>
<form id="frmEmployeeInfoLst">
<table id="tblEmployeeInfoLst"></table>
	<div class=" "  style="width : 257px;" >
							   		<label class="mandatory-lbl">Employee Name</label>
							   		
							  	</div> 
			                  	<div class="" style=" width :255px;margin-top:10px; ">

				                	<input class="easyui-text"   id="txtEmpName" name="txtEmpName"  style="width:260px;width :123px\9;; height : 30px;" value="${requestScope.genTlEmployeemst.empmName}"  >
			                  		
								</div>
								<div class=" "  style="width : 350px;margin-top:10px;" >
							   		<label class="mandatory-lbl">Employee PhoneNo</label>
							   		</div>
							   		<div class="mndlbl " style="margin-left:1px;margin-top:10px;">
                          <input  class="easyui-text"  id="txtEmpPhone" name="txtEmpPhone"  style="width:260px;height : 30px;"   value="${requestScope.genTlEmployeemst.empmMobile}"  >
</div>
<div class=" "  style="width : 350px;margin-top:10px;" >
							   		<label class="mandatory-lbl">Employee Email</label>
							   		</div>
<div class="mndlbl " style="margin-left:1px;margin-top:10px;">
                          <input  class="easyui-text" id="txtEmpmEmail" name="txtEmpmEmail"  style="width:260px;height : 30px;"   value="${requestScope.genTlEmployeemst.empmEmail}"  >
</div>

    <div style="position:relative;margin-left:300px;margin-top:-184px;">							
							<div id="imgEmp" style="">
						   	<img id="imgEmployee" name="imgEmployee" src="images/empImage/EmpDefaultImg.jpg" width="180px" height="200px"/>
						   </div>
						   <div>
										<span><input type="button" class="easyui-button" id="btnimgEmpAdd" name="btnimgEmpAdd" value="+" style="width:25px;"/></span>
										<span style="padding-left: 132px " ><input type="button" class="easyui-button" id="btnimgEmpDel" name="btnimgEmpDel" value="-" style="width:26px;"/></span>
									</div>
								</div>

 <div style="margin-left:0px;margin-top:-30px;">
<input type="button" class="easyui-button" id="btnUpdateEmp" name="btnUpdateEmp" value="Save" style=" width : 50px;height:30px;"/>
</div>
 <div style="margin-left:180px;margin-top:-30px;">
<input type="button" class="easyui-button" id="btnUpdateImg" name="btnUpdateImg" value="Save Image" style=" width : 80px;height:30px;"/>		
</div>
	
	<div>
<input type="easyui-text" id="hdnEmpImgUrl" name="hdnEmpImgUrl" style="display:none;" value=""/>
 </div>
  <input type="hidden" id="userLoginid" name="userLoginid" value="${requestScope.userkeyid}"/>


</form>