<script>
jQuery(document).ready(function(){
    initialiseForm('frmAuditViewLst');
	var url = jQuery('#hiddenUrl').val();
	var userkeyid = jQuery('#userLoginid').val();
	var data = url.split("?");
	url = data[0];
	var filterSreing = data[1];
	var dlgimage =  jQuery("#btnimgEmpAdd");
	var detailsid=jQuery('#hdndetailid').val();
	processAjaxCalls("get_Image.ehsb","?q=2&detailsid="+detailsid ,"getEmpImgSuccess","getEmpImgErr");
	});

function getEmpImgSuccess(result)
{ 
	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
	jQuery('#imgEmployee').attr('src','');
	jQuery('#imgEmployee').attr('src',result.empImg.imgToimBlobimage);	
	}
}

 
</script>
<form id="frmEmployeeInfoLst">
<table id="tblEmployeeInfoLst"></table>
	
							   		

    <div style="position:relative;margin-left:0px;margin-top:0px;">							
							<div id="imgEmp" style="">
						   	<img id="imgEmployee" name="imgEmployee" src="" width="700px" height="330px"/>
						   </div>
						<!--     <div>
										<span><input type="button" class="easyui-button" id="btnimgEmpAdd" name="btnimgEmpAdd" value="Close" style="width:25px;"/></span>
									</div>  -->
								</div>


	
	<div>
<input type="easyui-text" id="hdnEmpImgUrl" name="hdnEmpImgUrl" style="display:none;" value=""/>
 </div>
  <input type="hidden" id="userLoginid" name="userLoginid" value="${requestScope.userkeyid}"/>
    <input type="hidden" id="hdndetailid" name="hdndetailid" value="${requestScope.detail}"/>


</form>