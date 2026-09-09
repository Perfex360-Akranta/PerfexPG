<script>
	jQuery(document).ready(function(){
		initialiseForm('frmExternalService');
		jQuery("#tabExternalService").tabs({ onLoad:function(title){ 
			 jQuery('.tabs-panels').css('height','90%%');
			 
			}
		});
		 /*jQuery("#txtExternal").change(function () {
	         var str = "";
	         var value = "";
	         jQuery("select option:selected").each(function () {
	               str += jQuery(this).text() + " ";
	               value = jQuery("#txtExternal").val();//alert(value);
	               alert(str);
	               var tabs = jQuery('#tabExternalService').tabs('tabs');  
	               if(str=="External Service"){
	               jQuery('#tabExternalService').tabs('select', "External Service");
	               }
	               if(str=="External Repair"){
		               jQuery('#tabExternalService').tabs('select', "External Repair");
		               }
	             });
	         
	       })
	       .trigger('change');*/	
		var factId = jQuery("#frmExternalService input[id='factory']").val();
        var sectionId = jQuery("#frmExternalService input[id='section']").val();
        var cellId = jQuery("#frmExternalService input[id='cell']").val();
        var machId = jQuery("#frmExternalService input[id='machine']").val();
        var flid = jQuery("#frmExternalService input[id='flid']").val();
        var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
        loadFunctionalLocation("externalServiceLocation","functionalLoc.commonFilter","externalfunlocationvalues","frmExternalService",dataStr);
		processGridnew('externalservice_input.esar','?q=2',"ExternalServiceGrid","pager","","","","template_onLoadComplete","");
		processGridnew('externalrepair_input.esar','?q=2',"ExternalRepairGrid","pagerSteps","","","","","");
	});

function external(){
	var external = jQuery("select[id='txtExternal'] option:selected").val();
	if(external == "ES"){
		
		jQuery("#repair").hide();
		jQuery("#externalService").show();
	}
	if(external == "ER"){
		
		jQuery("#repair").show();
		jQuery("#externalService").hide();
	}
}
</script>
<form id="frmExternalService">
	<div id="wrapper" style="width:90%;">
		<table>
			<tr>
				<td colspan="4">
					<div id="frmKnowFuntKeyIds">							
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>	
								<input type="hidden" id="flid" name="cmbflid"  value="${requestScope.flid}"></input>							
					</div>						
					<div id="externalServiceLocation" style="width:643px;"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" ><label>Notification No:</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtNotification" name="txtNotification" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>WO No.</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtWono" name="txtWono" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>SAP Ref No.</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtSaprefno" name="txtSaprefno" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Operation Quantity</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtQty" name="txtQty" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" ><label>Price</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtPrice" name="txtPrice" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">				
					<div class="easyui-paddingbfpx" ><label>Material Group</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtNotification" name="txtNotification" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Purch Group</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtWono" name="txtWono" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;" rowspan="2">
					<div class="easyui-paddingbfpx" ><label>Agreement</label></div>
					<div class="easyui-paddingbfpx" >
						<textarea maxlength="500" rows="2" cols="80"style="width: 255px; height : 60px;" id="txtKaizenIdea" name="txtKaizenIdea" ></textarea> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" ><label>Recipient</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtRecipient" name="txtRecipient" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Requisitioner</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtRequisitioner" name="txtRequisitioner" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">				
					<div class="easyui-paddingbfpx" ><label>Planned Delivery Time</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtTime" name="txtTime" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx" ><label>External Sub contract ?</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtExternal" name="txtExternal" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">				
					<div class="easyui-paddingbfpx" ><label>Sort Term</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtTerm" name="txtTerm" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Cost Element</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtElement" name="txtElement" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Vendor</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtVendor" name="txtVendor" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				
					
			</tr>
			<tr>
				<td >
					<div class="easyui-paddingbfpx" ><label>Info Record</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtInfo" name="txtInfo" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Unloading Point</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtPoint" name="txtPoint" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>Tracking Number</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtNumber" name="txtNumber" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx" ><label>FW Order</label></div>
					<div class="easyui-paddingbfpx" >
						<input class="easyui-text" id="txtOrder" name="txtOrder" maxlength="30" style="width:255px;height:21px;"	value=""	/> 
					</div>
				</td>
			</tr>
			<tr>
				<td >
					<div class="easyui-paddingbfpx" >
						<select class="easyui-text" id="txtExternal" name="txtExternal" onchange="external()" style="width:255px;height:21px;"	>
							<option value=''></option>
							<option value='ES'>External Service</option>
							<option value='ER'>External Repair</option>
						</select> 
					</div>
				</td>
			</tr>
		</table>
		<div id="tt" style="margin-left:-60px;width : 90%;position:relative; ">
<div  id="tabExternalService" class="easyui-tabs"  style="width:1100px;height:150px;height:220px\9;padding-left:-72px;margin-left:8%;border-bottom:solid 1px #8DB2E3;float:left;position:absolute;" >
	<div title="External Service" id="externalService" style="padding-left:10px;padding-top:10px;height:45%;">
		<div style="float:left;" >
		<table id="ExternalServiceGrid" style="width:100%;height:50%;">
		<tr><td/></tr></table>
		<div id="pager"></div>
		</div>
	</div>
	<div title="External Repair" id="repair" style="padding:10px;">
    	<div style="float:left;" >
			<table id="ExternalRepairGrid" style="width:100%;">
			<tr><td/></tr></table>
			<div id="pagerSteps"></div>
		</div>	
	</div>
	</div>
	</div>	
	</div>
</form>