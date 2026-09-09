<script>
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	var userkeyid = jQuery('#userLoginid').val();
	var data = url.split("?");
	url = data[0];
	var filterSreing = data[1];
	var dlgimage =  jQuery("#btnimgEmpAdd");
	var detailsid=jQuery('#hdndetailid').val();
	processAjaxCalls("get_Image.ehsb","?q=2&detailsid="+detailsid ,"getEmpImgSuccess","getEmpImgErr");
});

function getEmpImgSuccess(result){ 
	var image=result.empImg.imgToimBlobimage;
	if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
	jQuery('#imgQcImageview').attr('src',result.empImg.imgToimBlobimage);	
	}
}



 
</script>
<form id="frmQcImageview">
<table id="frmQcImageview"></table>		 
    <div style="position:relative;margin-left:0px;margin-top:0px;">							
							<div id="imgEmp" style="">
						   	<img id="imgQcImageview" name="imgQcImageview" src="" width="700px" height="330px"/>
						   </div>
						   </div>
<input type="hidden" id="userLoginid" name="userLoginid" value="${requestScope.userkeyid}"/>
<input type="hidden" id="hdndetailid" name="hdndetailid" value="${requestScope.detail}"/>
</form>