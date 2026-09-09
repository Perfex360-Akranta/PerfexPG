<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script type="text/javascript">
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmTopicPopup'); // set the id of form to submit
	initialiseForm('frmProgPopup');	
	jQuery('#frmProgPopup .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmProgPopup textarea').css('text-transform', 'uppercase');
	jQuery('#btndlgCloseTop').click( function()
	{	
		closePopUpDialoge("divShowWhyTrainingPopup");
	});
	jQuery('#btndlgSaveTop').click( function()
	{	
		alert("Data Saved Successfully");
	});	
	
});


function resetControls(){
	jQuery("#txtProgName").val(""); 
	jQuery("#txtProgCode").val(""); 	
	jQuery("#txtProgRemarks").val("");
}



</script>
<form id="frmProgPopup" name="frmProgPopup">
	<div title="">	
	<table>
		<tr>
			<td>
				<div>					     	
				   	<div style="padding-top:10px;padding-left:10px;">
					   	<table width="100%" align="Center">
						   	<tr>
							   	<td  valign="top">						   			
									<div  class="easyui-paddingbfpx">
				                        <label class="mandatory-lbl">Program Name</label>                       
				                    </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="txtProgName" name="txtProgName" value="${requestScope.entTlTopicmst.topiName}" style="width:350px" maxlength="100" class="easyui-text"  <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>                   
				                    </div>  
				                    
				                    <div  class="easyui-paddingbfpx">
				                        <label class="mandatory-lbl">Program Code</label>                       
				                    </div> 
				                    <div class="easyui-paddingbfpx"> 
				                        <input id="txtProgCode" name="txtProgCode" value="${requestScope.entTlTopicmst.topiCode}" style="width:350px" maxlength="45" class="easyui-text"  <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>                   
				                    </div>  
				                    <div  class="easyui-paddingbfpx">
					                    <label >Remarks</label> 
					                </div> 
					                <div class="easyui-paddingbfpx">
					                    <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtProgRemarks" name="txtProgRemarks" style="resize:none;width:350px;height:70px" ></textarea>                   
					                </div>				                     
				               </td>				               
						   	</tr>
			            </table>
				    </div> 
					<div style="float:center;padding-top:10px;padding-left:10px;">	
						<table width="100%">
							<tr>
								<td align="center">
									<input type="button" class="easyui-button" id="btndlgSaveTop" value="Save" <c:out value = "${ requestScope.entTlTopicmstBean.disableForm == true ? ' disabled':''}"/>/>
									<input type="button" class="easyui-button" id="btndlgCloseTop" value="Close"/>																
								</td>
							</tr>
						</table>
			      		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/> 
	        			<input type="hidden" id="txtProgKeyid" name="txtProgKeyid" value="" />
	        			
	        		</div>
		   		</div>	
			</td>
		</tr>	
	</table>
	</div>
</form>


