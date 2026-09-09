<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	// var dlgTitle = 'Select Child';
	 var elemType =jQuery("#hdnelemType").val();
	 var dlgId=jQuery("#hdndlgId").val();
	// alert("dlgId: " +dlgId);
	 var w=jQuery("#hdnw").val();
	 var h=jQuery("#hdnh").val();
	 var dispCode=jQuery("#hdndispCode").val();
	 var elemId=jQuery("#hdnelemId").val();
	 jQuery( "#dlgSaveButton" ).hide();
	 jQuery( "#dlgDelButton" ).hide();
	 
	 if(jQuery('#hdnBlobimage').val() !='')		
		 jQuery( "#dlgDelButton" ).show();
	 /*
	 if(elemType == 'C')
		{
	
			jQuery("#forsectChild").css('display','block');		
			jQuery("#foreqpChild").css('display','none');		
			jQuery("#forassmChild").css('display','none');
			jQuery("#cboeqpChild").val('-');	
			jQuery("#cboassmChild").val('-');			
		}
		else*/
		if(elemType == 'M')
		{
			//alert("dlgId1: " +dlgId);
			/*jQuery("#forassmChild").css('display','none');		
			jQuery("#foreqpChild").css('display','block');
			jQuery("#forsectChild").css('display','none');
			jQuery("#cbosectChild").val('-');	
			jQuery("#cboassmChild").val('-');	*/
			
			fillComboBox("frmCutEqp","cmbdialogUnit","location.funlocn" );		
			fillComboBox("frmCutEqp","cmbdialogsubUnit","sectionCombo.commonFilter" );
			//fillComboBox("frmCutEqp","cmbdialogsect","cellCombo.commonFilter?lineNotToShown="+id );
			fillComboBox("frmCutEqp","cmbdialogsect","cellCombo.commonFilter");
		}
	/*	else if(elemType == 'A')
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
	 }*/
	/*if(elemType == 'M')
	{
		
		if(dlgId == '#dlgAddImage')
		{
			dlgTitle = 'Equipment ['+dispCode+']-Image';
			jQuery('#dlgElemType').val(elemType);
			jQuery('#previewField').attr('src',jQuery('#hdnBlobimage').val());
		}
		if(dlgId == 'dlgCutEqp')
		{
			//alert("dlgId 3:"+dlgId);
			dlgTitle = 'Select Destination Line to Paste';
		}
	}*/
/*	if(elemType == 'A')
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
	 jQuery( dlgId ).css('height',h);*/
	 jQuery( "#dlgCncl" ).click(function() {
		 closePopUpDialoge('dlgCutEqp')
	 });
	 
		jQuery( "#dlgPaste" ).click(function() {
			var flag = true;
			if(jQuery("#cmbdialogUnit").combobox('getValue') ==null || jQuery("#cmbdialogUnit").combobox('getValue') ==''||jQuery("#cmbdialogUnit").combobox('getValue')==' ')
			{
				alert('Select Location');
				flag = false;
			}
			            
			else if(jQuery("#cmbdialogsubUnit").combobox('getValue') ==null || jQuery("#cmbdialogsubUnit").combobox('getValue') ==''||jQuery("#cmbdialogsubUnit").combobox('getValue')==' ')
			{
				alert('Select DMT');
				flag = false;
			}
			else if(jQuery("#cmbdialogsect").combobox('getValue') ==null || jQuery("#cmbdialogsect").combobox('getValue') ==''||jQuery("#cmbdialogsect").combobox('getValue')==' ')
			{
				alert('Select JH');
				flag = false;
			}
			if(flag == true)
			{
				var dataString="?q=2&unitId="+jQuery("#cmbdialogUnit").combobox('getValue') + "&subUnitId="+jQuery("#cmbdialogsubUnit").combobox('getValue')+"&sectId="+jQuery("#cmbdialogsect").combobox('getValue');
				dataString+= "&elemId="+elemId+"&dispCode="+dispCode;
				//alert("Cut: " +dataString);
					/*var dataString="?q=2&unitId="+jQuery("#cmbdialogUnit").combobox('getValue')+"&subUnitId="+jQuery("#cmbdialogsubUnit").combobox('getValue')+"&sectId="+jQuery("#cmbdialogsect").combobox('getValue');
						dataString+= "&elemId="+jQuery("#txtDlgElemId").val()+"&dispCode="+jQuery("#txtDlgDispCode").val();*/
				processAjaxCalls("paste_eqp.funlocn",dataString,"pasteEqpSuccess","pasteEqpRecallError");
					//alert(getSearchString('Line',jQuery("#cmbdialogsect").combobox('getText'))+'||'+jQuery("#cmbdialogsect").combobox('getValue'));
					//alert(dataString);
				//	jQuery("#flTreeComponent").jstree("search",getSearchString('Line',jQuery("#cmbdialogsect").combobox('getText'))+'||'+jQuery("#cmbdialogsect").combobox('getValue'));		
					//processAjaxCalls("get_bd.funlocn",dataString,"getBdSuccess","getBdError");
					
			}
		});
	jQuery("#cmbdialogUnit").combobox(
				{
						onSelect : function(record) {
							// alert("record.id: " +record.id)
							jQuery("#cmbLocation").combobox('clear');
							reloadCombo("frmCutEqp","cmbdialogsubUnit","sectionCombo.commonFilter?locnId="+ record.id );	
							//jQuery("#cmbLocation").focus();						
				}
	});
	 
	jQuery("#cmbdialogsubUnit").combobox(
			{
					onSelect : function(record) {
						// alert("record.id: " +record.id)
						jQuery("#cmbLocation").combobox('clear');
						reloadCombo("frmCutEqp", "cmbdialogsect","cellCombo.commonFilter?sectId="+ record.id );
						//jQuery("#cmbLocation").focus();						
			}
	});
	
   
});

function pasteEqpSuccess(result)
{	
	
	alert(result.successMsg);	
	refreshTree();		
	openNode('flTreeComponent',result.pasteTo);
	jQuery("#flTreeComponent").jstree("search",getSearchString('Equipment',result.pasted));
	closePopUpDialoge('dlgCutEqp');
	//jQuery('#dlgCutEqp').dialog('close');	

}

 function closeFlDialog(dlgId)
 { 
 
	 jQuery( '#'+dlgId ).hide();
	// jQuery('#DIV_FLMASK').removeClass('popup-mask');	
	// jQuery( '#'+dlgId ).removeClass('custom-popup');
 }


</script>

 	
			 <div id="dlgCutEqp" class="">
				 <form id="frmCutEqp" name="frmCutEqp">
					  <input type="text" style="display:none;" id="txtDlgElemId" name="txtDlgElemId"/>
					  <input type="text" style="display:none;" id="txtDlgDispCode" name="txtDlgDispCode"/> 
		 			  <div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
						   <label class="mandatory-lbl">Location</label>                       
					  </div> 
					  <div class="easyui-paddingbfpx" style="padding-left:10px;"> 
							   <input id="cmbdialogUnit" name="cmbdialogUnit" class="easyui-combobox" style="width:260px;"/ >                       
					 </div> 
		 			 <div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
						   <label class="mandatory-lbl">DMT</label>                       
					 </div> 
					 <div class="easyui-paddingbfpx" style="padding-left:10px;"> 
						   <input id="cmbdialogsubUnit" name="cmbdialogsubUnit" class="easyui-combobox" style="width:260px;"/ >                       
					 </div>
				   	 <div  class="easyui-paddingbfpx" style="padding-left:10px;">
						   <label class="mandatory-lbl">JH</label>                       
					</div> 
					<div class="easyui-paddingbfpx" style="padding-left:10px;"> 
						   <input  id="cmbdialogsect" name="cmbdialogsect" class="easyui-combobox" style="width:260px;"/ >                       
					</div>
					 <div class="easyui-paddingbfpx" style="padding-left:80px;padding-top:20px;"> 
		 	    		    <input type="button" class="easyui-button" id="dlgPaste" value="Paste"/>
				      		<input type="button" class="easyui-button" id="dlgCncl" value="Cancel"/>
					</div>
			    </form>
			 </div>
		
		<input type="hidden" id="hdnelemType" value="${requestScope.elemType }"/>
		<input type="hidden" id="hdndlgId"  value="${requestScope.dlgId }"/>
		<input type="hidden" id="hdnw"  value="${requestScope.width }"/>
		<input type="hidden" id="hdnh"  value="${requestScope.height }"/>
		<input type="hidden" id="hdndispCode"  value="${requestScope.dispCode }"/>
		<input type="hidden" id="hdnelemId"  value="${requestScope.elemId }"/>
		