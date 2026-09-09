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
	 
	 if(jQuery('#hdnBlobimage').val() !='')		
		 jQuery( "#dlgDelButton" ).show();
	 if(elemType == 'C')
		{
	
			jQuery("#forsectChild").css('display','block');		
			jQuery("#foreqpChild").css('display','none');		
			jQuery("#forassmChild").css('display','none');
			jQuery("#cboeqpChild").val('-');	
			jQuery("#cboassmChild").val('-');				
		}
		else if(elemType == 'M')
		{
			
			jQuery("#forassmChild").css('display','none');		
			jQuery("#foreqpChild").css('display','block');
			jQuery("#forsectChild").css('display','none');
			jQuery("#cbosectChild").val('-');	
			jQuery("#cboassmChild").val('-');	
		}
		else if(elemType == 'A')
		{
				
			jQuery("#foreqpChild").css('display','none');
			jQuery("#forsectChild").css('display','none');
			jQuery("#forassmChild").css('display','block');
			jQuery("#cboeqpChild").val('-');	
			jQuery("#cbosectChild").val('-');
		}
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
   
});

</script>

 <div id="dlgAddChild" class=""><!--
			  <div id="titleAddChild" class="fl-popUpHeader" style="width:318px;margin-left:-10px;">
			   	 <label id="lblAddChild" style="margin-left:1px;font-size:11px;"></label> 
			 	 <img id="imgCloseAddChild" src="images/cancel.png" style="float:right;"/>
			  </div>
			  --><form name="frmAddChild" id="frmAddChild">
			 	  <div class="easyui-paddingbfpx" style="padding-left:30px;padding-top:25px;">
						<label> Child Details</label>
				  </div> 
				  <div class="easyui-paddingbfpx" id="forsectChild" style="padding-left:30px;display:none"> 
						<select id="cbosectChild"  name="cbosectChild" class="easyui-combobox" style="width:170px;" required="true" >
						     <option value="-"> ---</option>
							 <option value="E"> Equipment</option>
						</select> 
				  </div>
				  <div class="easyui-paddingbfpx" id="foreqpChild" style="padding-left:30px;display:none"> 
						 <select id="cboeqpChild" name="cboeqpChild" class="easyui-combobox" style="width:170px;" required="true" >
							  <option value="-"> ---</option>
							  <option value="A"> Assembly</option>
							  <option value="S"> Spare</option>
						 </select>
				  </div>		
				  <div class="easyui-paddingbfpx" id="forassmChild" style="padding-left:30px;display:none"> 	
						 <select id="cboassmChild"  name="cboassmChild" class="easyui-combobox"  style="width:170px;" required="true" >
								<option value="-"> ---</option>						
								<option value="S"> Spare</option>
						 </select> 
				   </div>
				    <div class="easyui-paddingbfpx" style="padding-left:70px;padding-top:30px;"> 
				    		<input type="button" class="easyui-button" id="btndlgOk" value="OK"/>
					      	<input type="button" class="easyui-button" id="dlgCancel" value="Cancel"/>
					</div>
				</form>
		</div>
		
		<input type="hidden" id="hdnelemType" value="${requestScope.elemType }"/>
		<input type="hidden" id="hdndispCode"  value="${requestScope.dlgId }"/>
		<input type="hidden" id="hdnw"  value="${requestScope.width }"/>
		<input type="hidden" id="hdnh"  value="${requestScope.height }"/>
		<input type="hidden" id="hdndispCode "  value="${requestScope.dispCode }"/>
		<input type="hidden" id="hdnelemId"  value="${requestScope.elemId }"/>
		