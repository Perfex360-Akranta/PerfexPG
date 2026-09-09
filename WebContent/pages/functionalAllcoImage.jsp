<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	 
	 var dlgTitle = 'Select Child';
	 var elemType =jQuery("#hdnelemType").val();
	 var dlgId=jQuery("#hdndlgId").val();
	 var w=jQuery("#hdnw").val();
	 var h=jQuery("#hdnh").val();
	 var dispCode=jQuery("#hdndispCode").val();
	 var elemId=jQuery("#hdnelemId").val();
	 jQuery( "#dlgSaveButton" ).hide();
	 jQuery( "#dlgDelButton" ).hide();
	 imageUpload(jQuery( "#dlgAddImg" ),'ImageUpload.commonFilter','dlgAddImg','previewField' );
	 if(jQuery('#hdnBlobimage').val() !='')		
		 jQuery( "#dlgDelButton" ).show();
	 if(elemType == 'CMP')
		{
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'COMPANY ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);				
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'LOCATION';
			}
		}
	 if(elemType == 'LCN')
		{
			if(dlgId == '#dlgAddImage')
			{
				
				dlgTitle = 'LOCATION ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'UNIT';
			}
		}
	 if(elemType == 'F')
		{
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'Unit ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);						
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'SUB UNIT';
			}
		}
	 if(elemType == 'L')
		{
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'SECTION ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
			else
			{
				dlgTitle = 'SECTION';
			}
		}
	 if(elemType == 'C')
	 {
			if(dlgId == '#dlgAddImage')
			{
				dlgTitle = 'Line ['+dispCode+']-Image';
				jQuery('#dlgElemType').val(elemType);
				jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
			}
	 }
	if(elemType == 'M')
	{
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'Equipment ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
		if(dlgId == '#dlgCutEqp')
		{
			dlgTitle = 'Select Destination Line to Paste';
		}
	}
	if(elemType == 'A')
	{
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'ASSEMBLY ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
		if(dlgId == '#dlgCopyAsm')
		{
			dlgTitle = 'Select Destination Equipment to Paste';
		}
	}
	if(elemType == 'SPR')
	{
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'SPARE ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
	}
	if(elemType == 'W')
	{
		dlgTitle = 'Inactivate Date';		
		formatDateBox('dteInactive','dd-MMM-yyyy');	
		fillWithCurrentDate('dteInactive');
		
	}
	 jQuery( dlgId ).css('width',w);
	 jQuery( dlgId ).css('height',h);
    //jQuery( dlgId.replace('dlg','lbl')).html(dlgTitle);
	 jQuery( "#dlgSve" ).click(function() {
			var imageResp = jQuery('#previewField').attr('src');
		 	processAjaxCalls('save_blob.funlocn','?q=2&fileName='+imageResp+'&locnId='+jQuery('#hdnImgLocnId').val(),'saveBlobSuccess','saveBlobFailure');
		 });	
	 jQuery( "#dlgDel").click(function() {		
			processAjaxCalls("del_img.funlocn","?q=2&nodeId="+jQuery('#hdnImgLocnId').val(),"delImgSuccess","delImgError");	
		}); 
});
function dlgAddImgOnComplete(response)
{	 
	//alert('inside load complete');
	 jQuery( "#dlgSaveButton" ).show();	 
}
</script>
<div id="dlgAddImage" class="">
				  <!--<div id="titleAddImage" class="fl-popUpHeader" style="width:618px;margin-left:-10px;">
				   	<label id="lblAddImage" style="margin-left:1px;font-size:11px;"></label> 
				 	  <img id="imgCloseAddImage" src="images/cancel.png" style="float:right;"/>
				  </div>
						 
				  --><div style="padding-bottom:4px;padding-left:4px;padding-right:4px;padding-top:4px;">
					 <img  id="previewField" class="previewField " width="580" height="340" src="">
					 <div class="easyui-paddingbfpx" style="padding-left:200px;padding-top:10px;"> 
							 <input type="button" class="easyui-button" id="dlgAddImg" name="dlgAddImg"  value="Add Image"/>
							 <span id="dlgSaveButton" style="display:none;">
									
							 <input type="button" class="easyui-button"  id="dlgSve" value="Save"/>
							 
							 </span>
							  <span id="dlgDelButton" style="display:none;">
									<input type="button" class="easyui-button"  id="dlgDel" value="Delete"/>
							 </span>
						     <input type="button" class="easyui-button" id="dlgCls" value="Close"/>
						     <input type="hidden" id="dlgElemType" name="dlgElemType">
					</div>
				</div>
			</div>
<!--elemType,dlgId,w,h,dispCode,elemId-->
<input type="hidden" id="hdnelemType" value="${requestScope.elemType }"/>
<input type="hidden" id="hdndispCode"  value="${requestScope.dlgId }"/>
<input type="hidden" id="hdnw"  value="${requestScope.width }"/>
<input type="hidden" id="hdnh"  value="${requestScope.height }"/>
<input type="hidden" id="hdndispCode "  value="${requestScope.dispCode }"/>
<input type="hidden" id="hdnelemId"  value="${requestScope.elemId }"/>
