<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<script type="text/javascript">


jQuery(document).ready(function(){
	
	var url=jQuery('#hdnPopupUrl').val();
	//alert(url);
	
	var reqParam = jQuery('#hdnControls').val();	
	//alert(url);
	//jQuery('#submitForm').val(url);
	//url = url + "?q=2"+escape(reqParam);
//	url = url + "?"+"controls="+escape(reqParam);	
	//alert(url);
	var controlStr =jQuery('#hdnControls').val();	
	
		
	LoadForm("subFormPopUpId", "subpreLoadContent",url+'?'+unescape(controlStr),"subdispErr","subform_SuccessCallBack");
	
	 jQuery('#ImgSFBack').click(function(){

		
		 jQuery('#subFormPopUpId').dialog('close');		
	});	

		
	
		jQuery('#ImgSFSave').click(function(){
			var formId = jQuery('#submitForm').val(); //defined in classic.jsp
			//alert(formId);
			var url = jQuery('#hdnPopupUrl').val(); //defined in classic.jsp
			//alert(url);
			if(formId.length > 0 )
			{			
				saveForm(formId,url,"sub");
			}	
		});

		jQuery('#ImgSFRefresh').click(function(){
			//alert("refresh");
			refreshForm();	
		});

});


function subform_SuccessCallBack(result) {
	//alert("loaded ok");
}

function subform_errorCallBack(result) {
	alert("Error in Saving");
}
</script>

<style>
html, 
body {  }
#header { height: 5px;     left: 0;    right: 0;z-index:-1;   
	/* background: #0F0;*/
}
#footer {
    height: 25px;    position: absolute;    bottom: 0;    left: 0;    right: 0;    
	/* background: #0F0;*/
}
#content {
      margin-top: 0px;    bottom: 50px;    left: 0;    right: 0;    overflow: auto;padding:15px;    
	/* background: #F00;*/
}
</style>

<form name="frmSubFormPopup" id="frmSubFormPopup" >
<center>
<!--<div id= "popHead" style="vertical-align: top;margin-right: 30px;border-style:outset;border-color:#0080FF;border-width:thin;margin-top:10px;float: center;overflow:false" >-->
<!--<div id= "popHead" style="vertical-align: top;margin-right: 30px;border-style:hidden;border-color:#0080FF;border-width:thin;margin-top:10px;float: center;overflow:false" >-->
	<div id="popHead" data-role="header" style="margin-bottom:10px;" >
		<img id="ImgSFSave" alt=""  title="Save" src="images/menu-icon/imgsave.png" style="cursor: pointer;" width="34px" height="34px" >
<!--		<img id="ImgSFDelete" alt="" title="Delete" src="images/menu-icon/imgclose.png" style="cursor: pointer;" width="34px" height="34px" >-->
<!--		<img id="ImgSFRefresh" alt="" title="Refresh" src="images/menu-icon/imgreferesh.png" style="cursor: pointer;" width="34px" height="34px" >-->
<!--		<img id="ImgSFBack" alt="" title="Previous Page" src="images/menu-icon/imgback.png" style="cursor: pointer;" width="34px" height="34px" >-->

<!--		<img id="ImgSFRefresh" alt="" title="Refresh" src="images/menu-icon/imgreferesh.png" style="cursor: pointer;" width="34px" height="34px" >-->
<!--		<img id="ImgSFBack" alt="" title="Previous Page" src="images/menu-icon/imgback.png" style="cursor: pointer;" width="34px" height="34px" >-->
	</div>

<div id="content">

	<div id="subFormPopUpId" style="padding: 10px; width: auto; height:75%;border-color:#0080FF;border-width:thin;border-style:outset" title=""  > 

	</div>
	<div id ="subpreLoadContent" class="tpm-loading"> </div>
</div>

<div id="footer">
	<div id="subdispErr" class="tpm-errorMsg" > </div>
</div>
</center>
</form>

<input type="hidden" id="hdnPopupUrl" value="${requestScope.subFormUrl}" >
<input type="hidden" id="hdnFormName" value="${requestScope.formName}" >
<input type="hidden" id="hdnControls" value="${requestScope.controls}" >
<input type="hidden" id="hdnReturnVal" value="">
<input type="hidden" id="hdnCancelCallBack" value="${requestScope.subFormCancel_Callback}" >
<input type="hidden" id="hdnokCallback" value="${requestScope.subFormOk_Callback}"/>

