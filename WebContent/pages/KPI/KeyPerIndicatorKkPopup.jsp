<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmKeyPerIndicatorPopup');
	// set the id of form to submit
	initialiseForm('frmKeyPerIndicatorPopup');	
	//alert(1);
	//jQuery('#frmKeyPerIndicatorPopup .easyui-text').css('text-transform', 'uppercase');
	//jQuery('#frmKeyPerIndicatorPopup textarea').css('text-transform', 'uppercase');
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkParentid","combo_keyPerformParent.keyPerInd");	
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkInputtype","combo_InputType.keyPerInd");	
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkInputentry","combo_InputEntry.keyPerInd");	
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkUomid","combo_Uom.keyPerInd");	
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkCostarea","combo_Costarea.keyPerInd");
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkDeptKeyid","combo_Dept.keyPerInd");
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkFrequency","combo_Frequency.keyPerInd");
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkManualcalctype","combo_ManualCaltype.keyPerInd");
	fillComboBox("frmKeyPerIndicatorPopup","cmbKinkExcelname","combo_Excelname.keyPerInd");	
	var targetNeed=jQuery('#txtKinkTargetneed').val();
	//alert(targetNeed);
	if (targetNeed=='Y')	
		jQuery('#chkKinkIsTargetneed').attr('checked',true);	
	else if (targetNeed=='N')		
		jQuery('#chkKinkIsTargetneed').attr('checked',false);
		
});

jQuery("#btndlgSave").click( function()
{	
	var url="keyInd_save.keyPerInd";	
	saveForm("frmKeyPerIndicatorPopup",url);	
});

jQuery('#btndlgClose').click( function()
{	
	closePopUpDialoge("divShowKeyInd");	
});	

function frmKeyPerIndicatorPopupcmbSkilDeptKeyid_onSelect(record){
	var deptId = record.id;	
}

function frmKeyPerIndicatorPopup_beforeSubmit(){}

function frmKeyPerIndicatorPopup_successsCallback(){	
	closePopUpDialoge("divShowKeyInd");		
	//refreshTree();
	if(jQuery("#kpiTreeComponent").jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
	{
		var isLeaf = jQuery("#kpiTreeComponent").jstree("is_leaf", jQuery('#'+jQuery('#hdnMstId').val()));		
		if(isLeaf == true){				
			jQuery("#kpiTreeComponent").jstree("load_node",jQuery('#'+jQuery('#hdnMstId').val()));			
			setTimeout(function() {jQuery("#kpiTreeComponent").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));},1250);
		}
		else{	
			//jQuery("#kpiTreeComponent").jstree("refresh",jQuery('#'+jQuery('#hdnMstId').val()));
			jQuery("#kpiTreeComponent").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));
		}  
	}
	else
	  refreshNode("kpiTreeComponent",jQuery('#hdnMstId').val());	
}

function resetControls(){
	jQuery("#txtKinkName").val(""); 
	jQuery("#txtKinkRemarks").val("");
}
jQuery('#chkKinkIsTargetneed').click(function() {
	//alert(jQuery('#chkKinkIsTargetneed').attr('checked'));
	if (jQuery('#txtKinkTargetneed').val()=="N"){
		jQuery('#txtKinkTargetneed').val('Y');
		jQuery('#chkKinkIsTargetneed').attr('checked',true);	
	}
	else if (jQuery('#txtKinkTargetneed').val()=="Y")	{	
		jQuery('#txtKinkTargetneed').val('N');
		jQuery('#chkKinkIsTargetneed').attr('checked',false);
	}
	
});

function frmKeyPerIndicatorPopupcmbKinkReftype_onLoadSuccess(){}
</script>
<form id="frmKeyPerIndicatorPopup" name="frmKeyPerIndicatorPopup">
	<div title="">	
	<table width="100%" >
		<tr>
		<td>
			<div>					     	
			   	<div style="padding-top:0px;padding-left:10px;">
				   	<table width="100%" align="Center">
					   	<tr>
						   	<td>	
						   	<div style="padding-top:20px;">
						   		<div  class="easyui-paddingbfpx">
			                        <label class="mandatory-lbl"> Name</label>                       
			                    </div> 				                    
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="txtKinkIndicatorname" name="txtKinkIndicatorname" maxlength="100" value="${requestScope.kpiTlIndicatorKk.kinkIndicatorname}" style="width:350px" maxlength="100" class="easyui-text"  <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>                   
			                    </div>	
			                    
			                    <div  class="easyui-paddingbfpx">
			                        <label class="mandatory-lbl"> Code</label>                       
			                    </div> 				                    
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="txtKinkIndicatorcode" name="txtKinkIndicatorcode" maxlength="15" value="${requestScope.kpiTlIndicatorKk.kinkIndicatorcode}" style="width:350px" maxlength="100" class="easyui-text"  <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>                   
			                    </div>	
			                    	
			                    <div  class="easyui-paddingbfpx">
				                    <label>Description</label> 
				                </div> 
				                <div class="easyui-paddingbfpx">
				                    <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtKinkDescription" name="txtKinkDescription" maxlength="500" style="resize:none;width:350px;height:70px" <c:out value = "${requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kpiTlIndicatorKk.kinkDescription}</textarea>                   
				                </div>
				                
			                    <div  class="easyui-paddingbfpx">
				                    <label class="mandatory-lbl">Input Type</label>                       
				                </div>         
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkInputtype" name="cmbKinkInputtype" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkInputtype}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>
			                    	 
			                    <div  class="easyui-paddingbfpx">
				                    <label class="mandatory-lbl">Input Entry</label>                       
				                </div>	        
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkInputentry" name="cmbKinkInputentry" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkInputentry}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>
			                    				                    
			                    <div  class="easyui-paddingbfpx">
				                    <label class="mandatory-lbl">Identifier</label>                       
				                </div>	         
				                <div class="easyui-paddingbfpx"> 				                        
			                        <input id="txtKinkIdentifier" name="txtKinkIdentifier" maxlength="5" value="${requestScope.kpiTlIndicatorKk.kinkIdentifier}" style="width:350px" maxlength="100" class="easyui-text"  <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>                       
			                    </div>
			                    
			                    <div class="easyui-paddingbfpx">  		
					        		<span>			        			
					        			<input type="checkbox"  id="chkKinkIsTargetneed" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>>        			
					        			<label>Target Need</label>		        			
					        		</span>
					        		<input type="hidden" id="txtKinkTargetneed" name="txtKinkTargetneed" value="${requestScope.kpiTlIndicatorKk.kinkTargetneed}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>  
				        		</div>
			        		</div>
		               </td>	
		               <td>
		               		<div style="padding-left:20px;">
			               		<div  class="easyui-paddingbfpx">
				                    <label>Parent</label>                       
				                </div> 					                	               
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkParentid" name="cmbKinkParentid" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkParentid}"/>
			                    </div>  				                									
								<div  class="easyui-paddingbfpx">
				                    <label>Uom</label>                       
				                </div> 					                	               
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkUomid" name="cmbKinkUomid" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkUomid}"  <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>           
			                    </div>
			                    
			                    <div  class="easyui-paddingbfpx">
				                    <label class="mandatory-lbl">Responsibility</label>                       
				                </div>         
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkDeptKeyid" name="cmbKinkDeptKeyid" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkDeptKeyid}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>
			                    
			                    <div  class="easyui-paddingbfpx">
				                    <label class="mandatory-lbl">Cost Area</label>                       
				                </div> 
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkCostarea" name="cmbKinkCostarea" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkCostarea}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>
	                   
			                    <div  class="easyui-paddingbfpx">
				                    <label>Entry Frequency</label>                       
				                </div>         
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkFrequency" name="cmbKinkFrequency" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkFrequency}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>	
			                    
			                    <div  class="easyui-paddingbfpx">
				                    <label>Manual Entry Actual</label>                       
				                </div>          
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkManualcalctype" name="cmbKinkManualcalctype" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkManualcalctype}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>
			                    
			                    <div  class="easyui-paddingbfpx">
				                    <label>Graph Excel Sheet</label>                       
				                </div>         
				                <div class="easyui-paddingbfpx"> 
			                        <input id="cmbKinkExcelname" name="cmbKinkExcelname" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicatorKk.kinkExcelname}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/> >                       
			                    </div>
			                    <div class="easyui-paddingbfpx">  		
			                    </div>
		                    </div>
		               </td>			               
				   	</tr>
		        </table>
			    </div> 
					<div style="float:center;padding-top:10px;padding-left:10px;">	
						<table width="100%">
							<tr>
								<td align="center">
									<input type="button" class="easyui-button" id="btndlgSave" value="Save" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>									
									<input type="button" class="easyui-button" id="btndlgClose" value="Close"/>
								</td>
							</tr>
						</table>
			      		<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/> 
			      		<input type="hidden" id="txtKinkPillarid" name="txtKinkPillarid" value="${requestScope.kpiTlIndicatorKk.kinkPillarid}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>
	        			<input type="hidden" id="txtKinkKeyid" name="txtKinkKeyid" value="${requestScope.kpiTlIndicatorKk.kinkKeyid}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>
	        			<input type="hidden" id="txtKinkLevelno" name="txtKinkLevelno" value="${requestScope.kpiTlIndicatorKk.kinkLevelno}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>
	        			<input type="hidden" id="txtKinkIschild" name="txtKinkIschild" value="${requestScope.kpiTlIndicatorKk.kinkIschild}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>
	        			<input type="hidden" id="txtKinkSortno" name="txtKinkSortno" value="${requestScope.kpiTlIndicatorKk.kinkSortno}" <c:out value = "${ requestScope.kpiTlIndicatorKkBean.disableForm == true ? ' disabled':''}"/>/>
	        		</div>
		   		</div>	
			</td>
		</tr>	
	</table>
	</div>
</form>

