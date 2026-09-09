
<script type="text/javascript">	
jQuery.noConflict();
jQuery(document).ready(function(){
	
	jQuery('#submitForm').val('frmAddDoc'); 
	initialiseForm("frmAddDoc");
	jQuery('#frmAddDoc .easyui-text').css('text-transform', 'uppercase');
    jQuery('#frmAddDoc textarea').css('text-transform', 'uppercase');	

    var folderMode =  jQuery("#hdnfolderMode").val();
    if(folderMode != null && folderMode != '' && folderMode != ' ')
    	jQuery("#btnAddFolder").val("Rename");
});
jQuery("#btnAddFolder").click(function(){
	var folderName = jQuery('#folderName').val();
	//alert(folderName);
 	if(folderName != null  &&  folderName !="" && folderName !=" " &&folderName !="null"){
	   	
	    jQuery("#preLodDiv").css('display','block');
		jQuery("#preLodDiv").addClass("tpm-loading");
		show_winMask(1);
		 var folderMode =  jQuery("#hdnfolderMode").val();
		 var ds = '?q=2&folder='+folderName;
		 if(folderMode != null  &&  folderMode !="" && folderMode !=" ")
			 ds += '&folderMode='+folderMode;
		
		processAjaxCalls('folder_save.dcm',ds,'InsertFolder','InsertFolderErr');
	}
});



</script>
<form id="frmAddDoc" name="frmAddDoc">

<table>
<tr><td  style="padding-left: 12px; padding-left: 0px\9;">

		<div class="AddFolderDivDocMgr">
			<label style='word-wrap: break-word;color:blue;font-weight:bold;font-size:11'>${requestScope.folderPath}</label>
		</div>
		<div class="easyui-paddingbfpx" style="padding-left: 13px padding-left: 0px\9">
			 <input id="folderName" name="folderName" class="easyui-text" type="text" maxlength="30" style="width:250px;height:30px;"/>	
		 </div>	 	

	<div style="padding-left:40%;margin-top:10px; padding-left:40%">
		<input id="btnAddFolder" class="easyui-button" name="btnAddFolder"  type="button" value="Create" style="height:20px; width : 58px;"  />
	</div>
	<input type="hidden" id="hdnfolderMode" name="hdnfolderMode" value="${requestScope.folderMode}"/>
</tr>	
</table>
</form>