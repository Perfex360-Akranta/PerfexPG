 <script type="text/javascript">	
   jQuery(document).ready(function(){
     
		processGridnew("sapInfo_view.brdn","&q=2","sapinfogrid","sapinfopager","","");
			jQuery("#btnSparesInfo").click(function(){
				 //navigateToNextForm("Sapstackinformation_input.sapinfo","SAP Stock Information");
				//openSAPSpareInfo("");
				LoadPopUp("divSAPSpares","Sapstackinformation_input.sapinfo", true,"85%","90%","0px","2%", "SapSpares_Callback","Spares");
			 });
			jQuery('#btnsprReplacedSave').click(function(){
	    		saveForm("frmSparesReplaced","sapspareInfo_save.sapinfo");
	    	});   
	        
	});
   function frmSparesReplaced_beforeSubmit(){alert(1);
	     var btnClicked = jQuery('#hdnSprOkBtnClick').val();
	     var spareSAPData = "";
	    
	  	  if("Y"==btnClicked){ 
	  		spareSAPData = jQuery('#hdnSaveSpareSapInfo').html( );
	  		
	  	  }alert("spareSAPData  "+spareSAPData);
	  	 var griddata  = "&spareSAPData="+spareSAPData.toString();
	  	// alert(griddata);
	  	return griddata+"&existDocNumber="+jQuery("#txtExistwoid").val()+"&refDocId="+jQuery("#txtRefDocId").val();
   	}
     function loadSAPSpares_onClose(){
    	 
    	 return true;
     }
</script>
<form name="frmSparesReplaced" id="frmSparesReplaced">
	<div  style="padding-top: 20px">
	  <input type="button" class="easyui-button" style="width:110px;" id="btnRefreshSapInfo" name="btnRefreshSapInfo" value="Refresh Sap info"/>										
	  <input type="button" class="easyui-button" style="width:120px;" id="btnSparesInfo" name="btnSparesInfo" value="Spares Information"/>
	  <input type="button" class="easyui-button" style="width:120px;" id="btnsprReplacedSave" name="btnsprReplacedSave" value="Save"/>
	  
	</div>
	<div style="float:left;">
	  <table id="sapinfogrid"></table>
	  <div id="sapinfopager"></div>
	</div>
	<input type="hidden" id="txtExistwoid" name="txtExistwoid" value="${requestScope.existWoId }"/>
	<input type="hidden" id="txtRefDocId" name="txtRefDocId" value="${requestScope.refdocId }"/>
	 

</form>