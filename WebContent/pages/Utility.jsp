  <script type="text/javascript">	
  			jQuery(document).ready(function(){
  			initialiseForm('frmUtility');
  			
  			if (jQuery('#hdnType').val() == "U") {
  				jQuery('#lblType').html("Utility");
  				jQuery('#cmbTolmType').combobox('setValue','U');
  			}
  			else {
  				jQuery('#lblType').html("Tools");
  				jQuery('#cmbTolmType').combobox('setValue','T');
  			}
  			
  			imageUpload(jQuery( "#dlgAddImg" ),'ImageUpload.commonFilter','dlgAddImg','imgprev',"imgToimFilename");
				  				
  			jQuery('#submitForm').val('frmUtility'); // set the id of form to submit
  			fillComboBox("frmUtility","cmbTolmKeyid","combo_Utility.uty" );
  			fillComboBox("frmUtility","cmbTolmCategory","combo_Category.uty" );  			

  			jQuery('#frmUtility .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmUtility textarea').css('text-transform', 'uppercase');
  				
  			//jQuery('#cmbTolmType').combobox('setValue','U');
  			jQuery('#cmbTolmType').combobox('disable');

			var Keyid=jQuery('#cmbTolmKeyid').combobox('getValue');
			
			if (Keyid != null)  
  				processAjaxCalls("Utility_recall.uty" ,"UtilField="+Keyid, "frmUtility_successCallback","frmUtility_errorCallback");  			  			          			
 });

		  	jQuery( "#dlgClr" ).click(function() 
		  		{ 		  				 
		  		  	jQuery('#imgprev').attr('src', "");
		  		  	jQuery('#imgToimFilename').val("");
		  		 });		  	  	

  			function frmUtilitycmdNewCategory_onClick()
	  		{
	  	  		alert("New Category");
		  	  	jQuery("#popcat").load('Utility_newcategory.uty', function(response, status, xhr) {
					  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
					});
				jQuery( "#popcat" ).show();
				jQuery( "#popcat" ).dialog({
					autoOpen: false,
					modal: true,
					height: 450,
					width: 900		
				});	  	  		
	  		}

		function frmUtilitycmbTolmKeyid_onSelect(record)
  		{
  			processAjaxCalls("Utility_recall.uty" ,"UtilField="+record.id, "frmUtility_successCallback","frmUtility_errorCallback");
  		}
 		 function frmUtility_successCallback(result)
 		 { 
 	 		jQuery('#imgprev').attr('src','');
 	  		jQuery("#cmbTolmCategory").combobox("setValue",result.Utility.TolmCategory);
 	  	    jQuery("#cmbTolmType").combobox("setValue",result.Utility.TolmType);
 	  		jQuery("#txtTolmName").val(result.Utility.TolmName);
 	  		jQuery("#txtTolmCode").val(result.Utility.TolmCode);
 	  		jQuery("#txaTolmUses").val(result.Utility.TolmUses);
 	  		jQuery("#txaTolmRemarks").val(result.Utility.TolmRemarks);
 	  		jQuery('#imgprev').attr('src', result.Utility.TolmFilename);
 		 }
  		 function frmUtility_errorCallback(result)
  		 {
  			alert("Error in callback");
  		 }
</script>

<form name="frmUtility" id="frmUtility" >
<div id="wrapperRpt">
<input type="hidden" id="hdnType" name="hdnType" value="${requestScope.type}"> 
<input type="hidden" id="mode" name="mode" ></input>
<div align="center"  class="main-cntborder" style="width:840px;height:360px;height:320px\9;margin-left:20px\9;">
	<table  align="center"  width="20%">
		<tr>
<!--left  pane -->
	<td valign="top" width="50%" >
		<div>
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;"><label id="lblType"> Utility </label></div> 
				 <div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;">
				    <input id="cmbTolmKeyid" name="cmbTolmKeyid" class="easyui-combobox"  style="width:280px;" value="${requestScope.genTlToolsmst.tolmKeyid}"  > 
				 </div>
				
				
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;"><label class="mandatory-lbl"> Name  </label></div> 
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;">
				    <input class="easyui-text" id="txtTolmName" name="txtTolmName"   style="width:280px;">
				</div>
						
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;"><label class="mandatory-lbl"> Code </label></div> 
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;">
				   <input class ="easyui-text" id="txtTolmCode" name="txtTolmCode" style="width:280px;">
				</div>
				
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;"><label class="mandatory-lbl">Category</label></div> 
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;">
			         <input id="cmbTolmCategory" name="cmbTolmCategory" class="easyui-combobox"   style=" width : 243px;" >
			         <input id="cmdNewCategory" name="cmdNewCategory" class="easyui-button"  type="button" value="..." style="width:26px;height:24px;" 
			         	onClick ="frmUtilitycmdNewCategory_onClick()"/>
			    </div>
										
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;"><label> Type</label></div> 
				<div class="easyui-paddingbfpx" style="padding-left:100px;padding-left:0\9px;">
			      	<select class="easyui-combobox"  id="cmbTolmType" name="cmbTolmType"  style="width:280px;" value="${requestScope.genTlToolsmst.tolmType}" >			      	    					
						<option value="U"> Utility </option>
						<option value="T"> Tools </option>
				</select> 
				</div>
		 </div>		
				</td>
				<td width="50%">
				
				<div class="easyui-paddingbfpx" style="padding-left:100px;"><label class="mandatory-lbl"> Uses</label></div> 
				<div class="easyui-paddingbfpx" style="padding-left:100px;">
			      <textarea rows="2" cols="34" id="txaTolmUses" name="txaTolmUses" ></textarea>
			    </div>
			    
				<div class="easyui-paddingbfpx" style="padding-left:100px;"><label> Remarks</label></div> 
				<div class="easyui-paddingbfpx" style="padding-left:100px;">
			      <textarea rows="2" cols="34" id="txaTolmRemarks" name="txaTolmRemarks"></textarea>
			    </div>
				
				 <div class="easyui-paddingbfpx" style="padding-left:100px;"><label> Utility Images</label></div> 
				 <div class="easyui-paddingbfpx" style="padding-left:100px;">
			      
				 <div id="dlgAddImage" >
					 
					 <div style="padding-bottom:4px;padding-left:4px;padding-right:4px;padding-top:4px;">
							<img alt=" Image..." name=imgprev id="imgprev" width="140" height="110" src="${requestScope.genTlToolsimg.toimFilename}">
							<div class="easyui-paddingbfpx" style="padding-left:;padding-top:5px;">
						 	 <input type="button" class="easyui-button" id="dlgAddImg" name="dlgAddImg"  value="Add Image" style="width:76px;"/>
						 	 <input type="button" class="easyui-button" id="dlgClr" name="dlgClr" value="Clear" style="width:46px;"/>							 
							  <input type="hidden" id="dlgElemType" name="dlgElemType">
							</div>
					</div>
				 </div>					 
				</div>					            	 
			</td>
		</tr>
	</table>
		</div>
		<div id="popcat" title="Category"> </div>
		<input type="hidden" id="imgToimFilename" name="imgToimFilename" />
</div>		
</form>