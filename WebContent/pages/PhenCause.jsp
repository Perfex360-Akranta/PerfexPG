<script src="js/ajaxupload.3.5.js" type="text/javascript"></script>

 <script type="text/javascript">	
 
  			jQuery(document).ready(function(){
			//alert('K');
			
  			initialiseForm('frmPhenCause');

			//alert("pcl controls"+jQuery(requestScope.controls));		  			
 	  		//enableFieldsInCheckBoxSelect("chkExiPhen","txtBphmPhenomenaName");
 			//enableFieldsInCheckBoxSelect("chkExiPhen","txtBphmRemarks");
  	  			  	  	
  	  		//processAjaxCalls("PhenCause_recall.uty" ,"UtilField="+jQuery('#cmbbphmAssemblyid').combobox('getValue'), "frmPhenCause_successCallback","frmPhenCause_errorCallback");
  	  		
  			jQuery('#submitForm').val('frmPhenCause'); // set the id of form to submit  			
  			fillComboBox("frmPhenCause","cmbbphmAssemblyid","combo_Assembly.pcl" );  			
  				
  		//for text/textarea diasble
  			jQuery('#cmbbphmAssemblyid').combobox('disable');
  		
  			jQuery('input:checkbox[name=chkExiPhen]').attr('checked',true);
  			readOnlyFields('txtbphmPhenomenaname');  						
  			readOnlyFields('txtbphmRemarks');
  			jQuery("#txtbphmRemarks").css('background-color','#D1E2FD');
   			jQuery('#txtbphmPhenomenaname').css('background-color', '#D1E2FD');
  			jQuery('input:checkbox[name=chkExiCause]').attr('checked',true);
  			readOnlyFields('txtbcsmRemarks');
  			readOnlyFields('txtbcsmName');
  			jQuery("#txtbcsmRemarks").css('background-color','#D1E2FD');
   			jQuery('#txtbcsmName').css('background-color', '#D1E2FD');
   			//end
   			var linkBd = jQuery('#hdnLinkBd').val();
   			if(linkBd != null && linkBd != '' && linkBd != ' ')
   	   		{
   	   	   		if(linkBd == 'Cause')
   	   	   	   	{
   	   	  		 jQuery('#chkExiPhen').attr('disabled',true);
				 jQuery('#chkNewPhen').attr('disabled',true);   					  						
	  			 readOnlyFields('cmbbphmKeyid');					
   	   	   	   	}
   	   		}
  			jQuery('#frmPhenCause .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmPhenCause textarea').css('text-transform', 'uppercase');
  			});

  			jQuery('#chkbcsmIscausedefined').click(function(){
  				if(jQuery('#chkbcsmIscausedefined').is(':checked') == true)
  				{
  					jQuery('#chkExiCause').attr('disabled',true);
  					jQuery('#chkNewCause').attr('disabled',true);   					  						
	  	  			readOnlyFields('cmbbcsmKeyid');  						
	  	  			readOnlyFields('txtbcsmName');  						
	  	  			readOnlyFields('txtbcsmRemarks');
  				}
  				else
  	  			{
  					enableFields('chkExiCause'); 
  					enableFields('chkNewCause');  						
  					enableFields('cmbbcsmKeyid');  						
  					enableFields('txtbcsmName');  						
  					enableFields('txtbcsmRemarks');
  	  			}
  				  						
  	  		
 	  			 
  			});
  		//phenomena Check click fucntions		
  			jQuery('#chkExiPhen').click(function(){  	  			
  				jQuery('input:checkbox[name=chkExiPhen]').attr('checked',true);
  				jQuery('input:checkbox[name=chkNewPhen]').attr('checked',false);
  				jQuery('#cmbbphmKeyid').combobox('enable');
   				jQuery('#txtbphmPhenomenaname').attr('readonly','readonly'); 
   				jQuery('#txtbphmPhenomenaname').css('background-color', '#D1E2FD');   				
   				readOnlyFields('txtbphmRemarks');
   			 	jQuery("#txtbphmRemarks").css('background-color','#D1E2FD');
   				jQuery('#txtbphmPhenomenaname').val("");
				jQuery('#txtbphmRemarks').val("");  	
  				});
  			jQuery('#chkNewPhen').click(function(){		
  				jQuery('input:checkbox[name=chkNewPhen]').attr('checked',true);
  				jQuery('input:checkbox[name=chkExiPhen]').attr('checked',false);
  				jQuery('#cmbbphmKeyid').combobox('clear');
  				jQuery('#cmbbphmKeyid').combobox('disable');
  				jQuery('#txtbphmPhenomenaname').removeAttr('readonly'); 
  				jQuery("#txtbphmRemarks").attr('readonly',false);
  				
  				jQuery('#txtbphmPhenomenaname').val("");
  				jQuery('#txtbphmRemarks').val("");  	
  				jQuery('#txtbphmPhenomenaname').css('background-color', '#fff');
  				jQuery('#txtbphmRemarks').css('background-color', '#fff');			
  			});  			
   //cause Check click fucntions  			  		
  			jQuery('#chkExiCause').click(function(){  	  			
  				jQuery('input:checkbox[name=chkExiCause]').attr('checked',true);
  				jQuery('input:checkbox[name=chkNewCause]').attr('checked',false);
  				jQuery('#cmbbcsmKeyid').combobox('enable');
   				jQuery('#txtbcsmName').attr('readonly','readonly'); 
   				jQuery('#txtbcsmName').css('background-color', '#D1E2FD');   				
   				readOnlyFields('txtbcsmRemarks');
   			 	jQuery("#txtbcsmRemarks").css('background-color','#D1E2FD');
   				jQuery('#txtbcsmName').val("");
				jQuery('#txtbcsmRemarks').val("");  	
  				});
  			jQuery('#chkNewCause').click(function(){		
  				jQuery('input:checkbox[name=chkNewCause]').attr('checked',true);
  				jQuery('input:checkbox[name=chkExiCause]').attr('checked',false);
  				jQuery('#cmbbcsmKeyid').combobox('clear');
  				jQuery('#cmbbcsmKeyid').combobox('disable');
  				jQuery('#txtbcsmName').removeAttr('readonly'); 
  				jQuery("#txtbcsmRemarks").attr('readonly',false);
  				
  				jQuery('#txtbcsmName').val("");
  				jQuery('#txtbcsmRemarks').val("");  	
  				jQuery('#txtbcsmName').css('background-color', '#fff');
  				jQuery('#txtbcsmRemarks').css('background-color', '#fff');			
  			});  			

//  			jQuery('#cmbbphmKeyid').select(function(){
  	 			 			 
//  			});
		function frmPhenCausecmbbphmAssemblyid_onLoadSuccess() {
			var dataString = "?q=2";
			var assmid = jQuery('#cmbbphmAssemblyid').combobox('getValue');
			//if(assmid != null && assmid != '' && assmid != ' ')
				//dataString += "&mchId="+assmid;
			//var mldmchFlag = jQuery('#cmbbdmsRelatedto').combobox('getValue');
			
			//if(mldmchFlag != null && mldmchFlag != ' ' && mldmchFlag != ' ')
			//{
				//if(mldmchFlag == 'MCH')
				//{
					var mchId= jQuery('#cmbbdmsMachineid').combobox('getValue');
					var mldId= jQuery('#cmbbdmsMould').combobox('getValue');
					
					if(mldId != null && mldId != ' ' && mldId != '')
					{
						dataString += "&mldId="+mldId;	
					}
					else
					{
						if(mchId != null && mchId != ' ' && mchId != '')
						{
							dataString += "&mchId="+mchId;	
						}
					}
				//}
				//else if(mldmchFlag == 'MLD')
				//{
				
					
				//}
				
			//}
  			fillComboBox("frmPhenCause","cmbbphmKeyid","combo_phenomena.brdn"+dataString );
  			var linkBd = jQuery('#hdnLinkBd').val();
   			if(linkBd != null && linkBd != '' && linkBd != ' ')
   	   		{
   	   	   		if(linkBd == 'Cause')
   	   	   	   	{
   	   	  		 jQuery('#chkExiPhen').attr('disabled',true);
				 jQuery('#chkNewPhen').attr('disabled',true);
				 jQuery('#chkbcsmIscausedefined').attr('disabled',true);    					  						
	  			 readOnlyFields('cmbbphmKeyid');					
   	   	   	   	}
   	   		}
  			
		}
		function frmPhenCausecmbbphmKeyid_onLoadSuccess() {
			var dataString = "?q=2";
			var assmid = jQuery('#cmbbphmAssemblyid').combobox('getValue');
			//if(assmid != null && assmid != '' && assmid != ' ')
				//dataString += "&assmId="+assmid;
			fillComboBox("frmPhenCause","cmbbcsmKeyid","combo_cause.brdn"+dataString );
			if(jQuery('#chkbcsmIscausedefined').is(':checked') == true)
				readOnlyFields('cmbbcsmKeyid');
		
		}
		function frmPhenCausecmbbphmKeyid_onSelect() {
			jQuery('#cmbbcsmKeyid').combobox('clear');
			var ds = "?q=2";
			if( jQuery("#cmbbphmKeyid").combobox('getValue') != null && jQuery("#cmbbphmKeyid").combobox('getValue') !='' && jQuery("#cmbbphmKeyid").combobox('getValue') != ' ')
				 ds += "&phenId="+jQuery('#cmbbphmKeyid').combobox('getValue')+"&assmId="+jQuery('#cmbbphmAssemblyid').combobox('getValue');
			fillComboBox("frmPhenCause","cmbbcsmKeyid","combo_cause.brdn"+ds );
			if(jQuery('#chkbcsmIscausedefined').is(':checked') == true)
				readOnlyFields('cmbbcsmKeyid');
			
		}
		
		
		function frmPhenCause_successsCallback(result)
  		 {
  			//alert(" Data Saved / Updated ");
  			var returnString; 
  			returnString= 'phenId='+jQuery("#cmbbphmKeyid").combobox('getValue') ; 
  			returnString+=	'&causeId=' + jQuery("#cmbbcsmKeyid").combobox('getValue');
  			//alert(returnString);  			
  			jQuery('#hdnReturnVal').val(returnString);
  			var assmId = jQuery('#cmbbdmsAssemblyid').combobox('getValue');
  	   		var mchId = jQuery('#cmbbdmsMachineid').combobox('getValue');
  			//jQuery("#cmbbdmsFinalphenomena").combobox('setValue',jQuery("#cmbbphmKeyid").combobox('getValue'));	  			
  			//jQuery("#cmbbdmsFinalcause").combobox('setValue',jQuery("#cmbbcsmKeyid").combobox('getValue'));  	
  			if(jQuery('#chkIsAssmWise').is(':checked') == true)
			{
				
				
				if(assmId != null && assmId != '' && assmId != ' ')
				{
					jQuery('#cmbbdmsFinalphenomena').combobox('clear');
					reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.brdn?q=2&assmId="+assmId+"&mchId="+mchId);
				}
				else
				{
					
					jQuery('#cmbbdmsFinalphenomena').combobox('clear');
					jQuery('#chkIsAssmWise').attr('checked',false);
					alert('Select Assembly');
					reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.brdn?q=2&mchId="+mchId);
				}
					
			}
			else
			{
				var dataStr = "?q=2&mchId="+mchId;
				jQuery('#cmbbdmsFinalphenomena').combobox('clear');
				if(jQuery('#cmbbdmsRelatedto').combobox('getValue') == "MLD")
				{
					var mldId = jQuery('#cmbbdmsMould').combobox('getValue');
					if(mldId != null && mldId != '' && mldId != ' ')
						dataStr = "?q=2&mldId="+mldId;
				}
				reloadCombo("frmBDMaster","cmbbdmsFinalphenomena","combo_phenomena.brdn"+dataStr);
			}		
  		
  			jQuery('#submitForm').val(jQuery('#hdnReturnsubmitUrl').val());
  			
  			jQuery('#subformPopUpId').dialog('close');
  		 }
  		function frmPhenCause_errorCallback(result)
  		 {
  			alert("Error in callback");
  		 }

  		function frmPhenCause_beforeSubmit()
  		{
  			if(jQuery('#chkExiPhen').is(':checked') == true) {
  	  			if (jQuery('#cmbbphmKeyid').combobox("getValue")=="")
  	  	  		{
  	  	  			alert("Select Phenomena");
  	  				return 0;
  	  			}}	
  			else {
  				if (jQuery('#txtbphmPhenomenaname').val()=="") {
  	  	  			alert("Type Phenomena Name");
	  				return 0;
  				}} 
				
  			if(jQuery('#chkbcsmIscausedefined').is(':checked') == false) { 	  		
  			if(jQuery('#chkExiCause').is(':checked') == true) {
  	  			if (jQuery('#cmbbcsmKeyid').combobox("getValue")=="")
  	  	  		{
  	  	  			alert("Select Cause");
  	  				return 0;
  	  			}}	
  			else {
  				if (jQuery('#txtbcsmName').val()=="") {
  	  	  			alert("Type Cause Name");
	  				return 0;
  				}}
  			}
  		}		 
</script>

<form name="frmPhenCause" id="frmPhenCause" >

<!--<div  class=" main-cntborder">-->

<div  >
<table style="float:left;" border-style="none">	
	<tr>
	  <td>
	  <table>
		<tr>
		<td style=" padding-left:0px;">		
				<div class="easyui-paddingbfpx" ><label> Assembly </label></div> 
				<input id="cmbbphmAssemblyid" name="cmbbphmAssemblyid" class="easyui-combobox"  style="width:350px;height:25px;" value="${requestScope.assmId}" >
				<br>
				<div style="height:10px;"> </div>
				<div>
				<input type="checkbox" id="chkExiPhen" name="chkExiPhen"/><span style="margin-left:2px;"><label> Existing Phenomena</label></span>
				<input type="checkbox" id="chkNewPhen" name="chkNewPhen"/><span style="margin-left:2px;width:100px;" ><label> New Phenomena</label></span>
				</div>
				<div></div>
				<div class="easyui-paddingbfpx" ><label> Phenomena </label></div> 
					<input id="cmbbphmKeyid" name="cmbbphmKeyid" class="easyui-combobox"  style="width:350px;height:25px;" value="${requestScope.phenId}"  > 
								
				<div class="easyui-paddingbfpx  " > <label class="mandatory-lbl">Name</label></div>
					<input id="txtbphmPhenomenaname" name="txtbphmPhenomenaname" class="easyui-text" style="width:350px;height:25px;" value=""  >
								
				<div  class="easyui-paddingbfpx"><label>Remarks</label></div> 
			         <textarea id="txtbphmRemarks" name="txtbphmRemarks"   rows="3" cols="34" style="width:350px;" ></textarea>
				<div></div>				
			</td>
		</tr>
		</table>
		</td>
		<td style="width:100px;" ></td>
	    <td>
	    <table>	    
		<tr>
		<td style=" padding-left:0px;">
		<div>	
			<input type="checkbox" id="chkbcsmIscausedefined"  name="chkbcsmIscausedefined" value="Y"/><span style="margin-left:2px;"><label> Cause Not Applicable for this Phenomena </label></span>
		</div>
		
			<div style="height:37px;"> </div>
				<div>
					<input type="checkbox" id="chkExiCause" name="chkExiCause"/><span style="margin-left:2px;"><label> Existing Cause</label></span>
					<input type="checkbox" id="chkNewCause" name="chkNewCause"/><span style="margin-left:2px;"><label> New Cause</label></span>
				</div>
			<div></div>				
				<div class="easyui-paddingbfpx" ><label> Cause </label></div> 
					<input id="cmbbcsmKeyid" name="cmbbcsmKeyid" class="easyui-combobox"  style="width:350px;height:25px;" value="${requestScope.causeId}"  > 
								
				<div class="easyui-paddingbfpx  " > <label class="mandatory-lbl">Name</label></div>
					<input id="txtbcsmName" name="txtbcsmName" class="easyui-text" style="width:350px;height:25px;" value=""  >
								
				<div  class="easyui-paddingbfpx"><label>Remarks</label></div> 
			         <textarea id="txtbcsmRemarks" name="txtbcsmRemarks"   rows="3" cols="34" style="width:350px;" ></textarea>				
			</td>
			</tr>
			</table>
		</tr>		
	</table>	
</div>	
<input type="hidden"  id="hdnCombinedId" name ="hdnCombinedId" value="${requestScope.combinedId}">
<input type="hidden"  id="hdnLinkBd" name ="hdnLinkBd" value="${requestScope.linkBd}">  	
</form>

