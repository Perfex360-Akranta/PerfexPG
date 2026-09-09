<script type="text/javascript">	
   jQuery(document).ready(function(){	
	initialiseForm('frmUndefinedPhen');	         				          					
    jQuery('#submitForm').val('frmUndefinedPhen'); 
	fillComboBox("frmUndefinedPhen","cmbbphmSectionid","sectionCombo.commonFilter" );	
	readOnlyFields('cmbbphmSectionid');	
	readOnlyFields('cmbbphmFromtime');
	readOnlyFields('cmbbphmTotime');
	readOnlyFields('cmbbphmBookedtime');
	readOnlyFields('cmbbphmAssemblyid');
	readOnlyFields('txabphmUndefinedpp');
	//alert(jQuery('#formMode').val());
	if(jQuery('#formMode').val() == 'PROPOSAL' || jQuery('#formMode').val() == null || jQuery('#formMode').val() == '')
	{ 
		readOnlyFields('txtbphmPhenomenaname');
    	readOnlyFields('txtbcsmName');
    	//readOnlyFields('chkbphmCausenotneeded');
    	checkboxCause( 'chkbnprIscausereq','txtbnprProposedcause');	
	}
	if(jQuery('#formMode').val() == 'APPROVAL')
	{ 
		readOnlyFields('txtbnprProposedphn');
		readOnlyFields('txtbnprProposedcause');
    	checkboxCause('chkbphmCausenotneeded','txtbcsmName'); 
	}
   /* if(jQuery('#txtbphmPhenomenaname').val() == null)
    {
    	readOnlyFields('txtbphmPhenomenaname');
    	readOnlyFields('txtbcsmName');
    }
    if(jQuery('#txtbphmPhenomenaname').val() != null)
    {
    	checkboxCause('chkbphmCausenotneeded','txtbcsmName'); 
    }*/
	jQuery('#frmUndefinedPhen .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmUndefinedPhen textarea').css('text-transform', 'uppercase');	
	jQuery(function () {
	 	processTree( jQuery("#testtree"),'undefined_tree.upm?q=2&assmID='+jQuery("#cmbbphmAssemblyid").combobox("getValue")+'&machID='+jQuery("#cmbbphmMachineid").combobox("getValue"));
		jQuery("#testtree").bind("select_node.jstree", function (e, data) {
				jQuery('#nodeImage').attr('src',data.rslt.obj.attr("imgUrl"));
		 		setImgWidth( jQuery('#nodeImage'),580,432);
	     });	
	});	
   });

   function customMenu(node) {
   }

function frmUndefinedPhencmbbphmSectionid_onLoadSuccess()
{
	fillComboBox("frmUndefinedPhen","cmbbphmCellid","cellCombo.commonFilter" );
	readOnlyFields('cmbbphmCellid');
}

function frmUndefinedPhencmbbphmCellid_onLoadSuccess()
{
	fillComboBox("frmUndefinedPhen","cmbbphmMachineid","machineCombo.commonFilter" );
	readOnlyFields('cmbbphmMachineid');
}
function frmUndefinedPhencmbbphmMachineid_onLoadSuccess()
{
	fillComboBox("frmUndefinedPhen","cmbbphmAssemblyid","assembly.commonFilter" ); 
	readOnlyFields('cmbbphmAssemblyid');
}

function frmUndefinedPhen_successsCallback(result)
{
	
	if(result.tpmRedirect == "ApprovedPhenomena")
	{		
		var keyId = jQuery('#txtbphmBddetails').val().split(">");				
		navigateToPrevForm();
		//navigateToNextForm('Breakdown_input.brdn'+'?BDKeyid='+keyId[0],'Breakdown Analysis');
	}
	else if(result.successData.viewMode != null)
	{
		alert(result.successData.msg);
		navigateToPrevForm();
	//	navigateToNextForm('undefined_view.upm','Undefined Phenomena-New Phenomena Proposal');
		//processGridnew("undefined_view.upm","?q=2","list","pager","Undefined Phenomena-New Phenomena Proposal","fillForm");
	}
	else if(result.successData.approvalMsg == "proposal")
		alert('Proposal Raised');
	else
	{
		if(confirm(result.successData.approvalMsg) == true)
		{
	        jQuery('#txtbphmPhenomenaname').val(jQuery('#txtbnprProposedphn').val());
	        jQuery('#txtbcsmName').val(jQuery('#txtbnprProposedcause').val());
	    	readOnlyFields('txtbnprProposedphn');
			readOnlyFields('txtbnprProposedcause');
			//jQuery('#chkbphmCausenotneeded').attr('checked',true);
	        //jQuery("#txtbnprProposedphn").attr('readonly',false);
	        //jQuery("#txtbnprProposedcause").attr('readonly',false);
	        enableFields('txtbphmPhenomenaname');
	        enableFields('txtbcsmName');
	        jQuery("#txtbphmPhenomenaname").css('background-color', '#FFFFFF');
	        jQuery("#txtbcsmName").css('background-color', '#FFFFFF');
	        checkboxCause('chkbphmCausenotneeded','txtbcsmName');       
		}
	}
}
function checkboxCause(chbId,txtId)
{
	 jQuery('#'+chbId).click(function() {
 		if(jQuery('#'+chbId).is(':checked') == true)
     	{
 			if(chbId == 'chkbnprIscausereq')
 				jQuery('#ProposalCause').removeClass('mandatory-lbl');
 			 readOnlyFields(txtId); 			
 			 jQuery("#"+txtId).css('background-color', '#D1E2FD');
 			 jQuery("#"+txtId).val('');
     	}
 		else
     	{ 	
 			if(chbId == 'chkbnprIscausereq')
 				jQuery('#ProposalCause').addClass('mandatory-lbl');		 
 	     	 enableFields(txtId);
 			 jQuery("#"+txtId).css('background-color', '#FFFFFF');
     	}
 	});  	
}

function frmUndefinedPhen_beforeSubmit()
{
	if(jQuery('#formMode').val() == 'APPROVAL')
	{
		
	}
}

 </script>
 <form name="frmUndefinedPhen" id="frmUndefinedPhen" action="SparesMaster_input.sm" method="post">
 
 <div id="confirmApproval" title="Approval"></div> 

<div align="center" class="main-cntborder">

	<table align="center" >
		<tr>
			<td>
				<div  style="float:left;padding-left:100px;padding-right:50px;">
					
					<div class="easyui-paddingbfpx "><label>Section</label></div> 
                	  <div class="easyui-paddingbfpx "> 
	                	<input class="easyui-combobox" id="cmbbphmSectionid" name="cmbbphmSectionid"  value="${requestScope.undefinedPhenBean.bdmsSectionid}" style="width:300px; height : 21px;" value=""  >
	                	<input class="easyui-text" id="txtbphmBddetails" name="txtbphmBddetails"  value="${requestScope.undefinedPhenBean.bdDetails}" style="display:none;"  >
	                	<input class="easyui-text" id="txtbphmFactoryid" name="txtbphmFactoryid"  value="${requestScope.undefinedPhenBean.bdmsFactoryid}" style="display:none;"  >
	                	<input type="text" style="display:none" id="formMode" name="formMode" value="${requestScope.undefinedPhenBean.formMode}"/>
					 </div>
					
					<div class="easyui-paddingbfpx "><label>Line</label></div> 
                 	 <div class="easyui-paddingbfpx "> 
                	<input id="cmbbphmCellid" name="cmbbphmCellid" class="easyui-combobox" value="${requestScope.undefinedPhenBean.bdmsCellid}" style="width:300px;height : 21px;" value=""  >
					</div>
					
				    <div class="easyui-paddingbfpx "><label>Equipment</label></div> 
                  	<div class="easyui-paddingbfpx "> 
                	<input id="cmbbphmMachineid" name="cmbbphmMachineid" class="easyui-combobox" value="${requestScope.undefinedPhenBean.bdmsMachineid}" style="width:300px;" value=""  >
					</div>
					
				    <div class="easyui-paddingbfpx "><label>From </label><span class="lblR" style="padding-left:80px;" >To</span><span class="lblR" style="padding-left:80px;" >Book time</span></div> 
				    <div class="easyui-paddingbfpx "> 
				    	<span class="spinner"><input  id="cmbbphmFromtime" name="cmbbphmFromtime" value="${requestScope.undefinedPhenBean.bdmsWostart}" class="easyui-timespinner spinner-text validatebox-text"  min="00:00" showseconds="false" style="width: 90px;"></span>                		
						<span style="padding-left: 10px;">
								<span class="spinner"><input  id="cmbbphmTotime" name="cmbbphmTotime" value="${requestScope.undefinedPhenBean.bdmsWoend}" class="easyui-timespinner spinner-text validatebox-text"  min="00:00" showseconds="false" style="width: 90px;"></span>
						</span>
                        <span> 
                            <input  class="easyui-text"  id="cmbbphmBookedtime" name="cmbbphmBookedtime" required="true" value="${requestScope.undefinedPhenBean.downTimeMins}" style="width:100px;"></input>
                       </span> 
                  </div>
                  
                  <div class="easyui-paddingbfpx "><label>Assembly</label></div> 
                  <div class="easyui-paddingbfpx "> 
                	<input id="cmbbphmAssemblyid" name="cmbbphmAssemblyid" class="easyui-combobox" value="${requestScope.undefinedPhenBean.bdmsAssemblyid}"  style="width:300px;" value=""  >
				</div>
                  
                  
                  
                  <div class="sub-header">Undefined PP Description</div>
                 	 <div class="easyui-paddingbfpx "> 
                		<textarea  id="txabphmUndefinedpp" name="txabphmUndefinedpp" readonly="readonly" rows="4" cols="33" ></textarea>
					</div>
					
					 <div class="sub-header "  >Proposal</div>
				  <div class="easyui-paddingbfpx"><label class="mandatory-lbl">Phenomena</label></div> 
                  <div class="easyui-paddingbfpx "> 
                  <input class="easyui-text"  id="txtbnprProposedphn" name="txtbnprProposedphn"   style="width:300px;" value="${requestScope.undefinedPhenBean.bnprProposedphn}"  >
                   </div>
                  <input type="checkbox" value ="N" id="chkbnprIscausereq" name="chkbnprIscausereq" style="padding-left:10px;"/><label style="vertical-align: top;">Cause not Applicable for this Phenomena</label>
					
				  
					<div class="easyui-paddingbfpx" ><label id="ProposalCause" class="mandatory-lbl">Cause</label></div> 
                 	 <div class="easyui-paddingbfpx ">                 
                	<input class="easyui-text"  id="txtbnprProposedcause" name="txtbnprProposedcause"  style="width:300px;height : 21px;" value="${requestScope.undefinedPhenBean.bnprProposedcause}"  >
					</div>
					
					<div class="cntborder "  >
				  <div class="sub-header cntborder " >Approval</div>
				  <div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Phenomena</label></div> 
                  <div class="easyui-paddingbfpx "> 
                  <input class="easyui-text" id="txtbphmPhenomenaname" name="txtbphmPhenomenaname"   style="width:300px;" value="${requestScope.undefinedPhenBean.bnprProposedphn}"   >
                   </div>
                  
						  <input type="checkbox" value ="N" id="chkbphmCausenotneeded" name="chkbphmCausenotneeded"/><label style="vertical-align: top;">Cause not Applicable for this Phenomena</label>
				 
					<div class="easyui-paddingbfpx" ><label>Cause</label></div> 
                 	<div class="easyui-paddingbfpx "> 
                	<input class="easyui-text"  id="txtbcsmName" name="txtbcsmName"  style="width:300px;height : 21px;" value="${requestScope.undefinedPhenBean.bnprProposedcause}"   >
					</div>
						</div>
					
					</div>
	
		
			</td>
			<td valign="top"" style="border-width:medium;border-color:black;">

			   <div class="sub-cntborder" style="height : 666px; width : 481px;padding-right:40px;background-color:white">
					<div style="padding-bottom:600px">
						<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
						<div id="testtree" class="demo" style="width: 50%"></div>
					</div>

<!--					<div align="center">-->
<!--						<input class="easyui-button" type="button" class ="button" value="OK" id="ok" "/>-->
<!--					</div>-->
				</div>
		   </td>
</tr>
		

</table>
</div>
<input type="hidden" id="mode" name="mode"/>
</form>
