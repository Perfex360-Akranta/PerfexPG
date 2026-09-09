 
<script>
	jQuery(document).ready(
			function() {
				//alert("Inside New Indicator ");
				//alert("Inside New Indicator"+jQuery("#hdnloadpopup").val());
				initialiseForm('frmnewKpiIndicatoior');
				jQuery('#submitForm').val('frmnewKpiIndicatoior');
				//jQuery('#frmnewKpiIndicatoior .easyui-text').css('text-transform', 'uppercase');
				//jQuery('#frmnewKpiIndicatoior textarea').css('text-transform', 'uppercase');	
				formatDateBox('dteKinkStartdate','dd-MMM-yyyy');
				formatDateBox('dteKinkEnddate','dd-MMM-yyyy');
				fillComboBox("frmnewKpiIndicatoior","cmbKinkParentid","combo_keyPerformParent.keyPerInd",'',false);
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkImpactarea", "combo_impact.keyPerInd",'',false);
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkSourceofkpi", "sourceOfKPI.commonFilter");
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkUomid", "uomCombo.commonFilter");
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkFrequency", "combo_Frequency.keyPerInd",'',false);
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkInputtype", "combo_TypofInput.keyPerInd",'',false);
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkManualcalctype", "combo_CalcType.keyPerInd",'',false);
				
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkInputentry", "combo_Datatype.keyPerInd",'',false);
				fillComboBox("frmnewKpiIndicatoior", "cmbKinkPillarid", "pillar.commonFilter");
				//fillComboBox("frmnewKpiIndicatoior", "cmbKinkType", "combo_indicatorType.keyPerInd",'',false);
				numericTextBox("txtAnnualTarget");
				readOnlyFields("dteKinkEnddate");

                var Loadpopup=jQuery("#hdnloadpopup").val();
                //alert("Loadpopup Outside"+Loadpopup);
                if("Loadpopup" == Loadpopup){
					jQuery('.wrpClass').removeAttr('id');
					jQuery('#pilrDiv').css('padding-left','20');
					jQuery('#mainTbl').css('padding-left','20');
                }else{
	                	jQuery('.wrpClass').attr('id','wrapper');
	    				jQuery('#pilrDiv').css('padding-left','15.2%');
	    				jQuery('#mainTbl').css('padding-left','15%');
                    }
				jQuery('#chkenableend').click(function(){
					if(jQuery('#chkenableend').is(':checked') == true)
					{	
						enableFields("dteKinkEnddate");
					}
					else{ 
						readOnlyFields("dteKinkEnddate");
						}			
		    	 	});
			
				
				jQuery("#cmbKinkInputtype").combobox("setValue","M");
				jQuery("#cmbKinkInputentry").combobox("setValue","I");
				readOnlyFields("cmbKinkPillarid");
				readOnlyFields("cmbKinkInputtype");
				readOnlyFields("cmbKinkInputentry");	
				readOnlyFields("cmbKinkType");			
				setFieldValue("cmbKinkType",getFieldValue("txtIndicatorType"));
				
				jQuery("#cmbKinkParentid").combobox({onRequest:function( ){	
					 var locationId =getFieldValue("cmbLocation");  
				   	 return "locationId="+locationId;
			   	 }});
});
	function frmnewKpiIndicatoior_successsCallback(result){
		//alert("frmnewKpiIndicator_onSuccesssCallback");
		//result.successData.
	}
</script>

<form action="" method="post" id="frmnewKpiIndicatoior">
	<div id='wrapperRpt' class="wrpClass" style="padding-left:0%">
			<!--<div id='pilrDiv'  style=" padding-top: 0px;padding-left: 12.2%;" ></div>-->
		<table id='mainTbl' style=" " >
			<tr >
				<td valign="top">
					<div>
  						<label class="mandatory-lbl">Pillar </label>
 						</div>
 						<div>
  						<input class="easyui-text" id="cmbKinkPillarid" name=cmbKinkPillarid style="width: 300px;" value="${requestScope.kpiTlIndicator.kinkPillarid}" />
					</div>
					
					<div><label class="mandatory-lbl">Impact Area</label></div>
					<div>
					<input class="easyui-text" id="cmbKinkImpactarea" name="cmbKinkImpactarea" style="width: 300px;" value="${requestScope.kpiTlIndicator.kinkImpactarea}" />
					</div>
					
					<div style="padding-top: 5px;"><label>Goals / Objective</label></div>
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" maxlength="495"  rows="2" cols="80" style="width: 300px; height : 65px;" id="txtKinkGoals" name="txtKinkGoals"> ${requestScope.kpiTlIndicator.kinkGoals}</textarea>
					
					<div style="padding-top: 10px;"><label class="mandatory-lbl">KPI Description</label></div>
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" maxlength="495"  rows="2" cols="80" style="width: 300px; height : 65px;" id="txtKinkDescription" name="txtKinkDescription">${requestScope.kpiTlIndicator.kinkDescription}</textarea>
					
					<div style="padding-top: 10px;"><label>Source of KPI</label></div>
					<div class="easyui-paddingbfpx">
						<input class="easyui-text" id="cmbKinkSourceofkpi" name="cmbKinkSourceofkpi" style="width: 300px;"	value="${requestScope.kpiTlIndicator.kinkSourceofkpi}" />
					</div>
					
					<div style="padding-top: 10px;"><label class="mandatory-lbl">UoM</label></div>
					<div class="easyui-paddingbfpx">
						<input class="easyui-text"	id="cmbKinkUomid" name="cmbKinkUomid" style="width: 300px;"	value="${requestScope.kpiTlIndicator.kinkUomid}" />
					</div>
				</td>
								
				<td style="padding-left:80px;padding-left:80px\9; " valign="top">
					<div  class="easyui-paddingbfpx">
	                    <label>Parent</label>                       
	                </div> 					                	               
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbKinkParentid" name="cmbKinkParentid" class="easyui-combobox"  style="width:350px"  value="${requestScope.kpiTlIndicator.kinkParentid}"/>
                    </div>
					<div style="padding-top: 0px;"><label>Type</label></div>
					<div class="easyui-paddingbfpx">
					<input type="hidden" id="txtIndicatorType" name="txtIndicatorType" value="${requestScope.kpiTlIndicator.kinkType}" />
						<span  style="padding-left:1px;">
					    	<select id="cmbKinkType" class="easyui-combobox" name="cmbKinkType"   style="height: 22px;width:110px;" value="${requestScope.kpiTlIndicator.kinkType}" >										
								<option value="KPI">KPI</option>	
								<option value="KPIV">KPIV</option>							
								<option value="KPOV">KPOV</option>
							</select>
				    	</span>
<!--								<input class="easyui-text"	id="cmbKinkType" name="cmbKinkType" style="width: 300px;"	value="${requestScope.kpiTlIndicator.kinkType}" />-->
					</div>
								
					<div ><label class="mandatory-lbl">Frequency</label></div>
					<div class="easyui-paddingbfpx">
							<input class="easyui-text" id="cmbKinkFrequency" name="cmbKinkFrequency" style="width: 300px;" value="${requestScope.kpiTlIndicator.kinkFrequency}" />
					</div>
					
					<div ><label class="mandatory-lbl">Reason for Tracking KPI</label></div>
					<div >
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="495"  rows="2" cols="80" style="width: 300px; height : 65px;"	id="txtKinkKpireason" name="txtKinkKpireason">${requestScope.kpiTlIndicator.kinkKpireason}</textarea>
					</div>
								
					<div style="padding-top: 10px;"><label class="mandatory-lbl">Type of Input</label></div>
					<div >
						 <input class="easyui-text" id="cmbKinkInputtype" name="cmbKinkInputtype" style="width: 300px;"	value="${requestScope.kpiTlIndicator.kinkInputtype}" />
					</div>
					
					<div style="padding-top: 0px;"><label class="mandatory-lbl">Data type</label></div>
					<div class="easyui-paddingbfpx">
						 <input class="easyui-text" id="cmbKinkInputentry" name="cmbKinkInputentry"	style="width: 300px;" value="${requestScope.kpiTlIndicator.kinkInputentry}" />
					</div>

					<div style="padding-top: 10px;"><label class="">Calculation Type</label></div>
					<div >
						 <input class="easyui-text" id="cmbKinkManualcalctype" name="cmbKinkManualcalctype" style="width: 300px;"	value="${requestScope.kpiTlIndicator.kinkManualcalctype}" />
					</div>
					
					<div style="padding-top: 10px;display: none;"><label>Annual Target</label></div>
					<div class="easyui-paddingbfpx" style="padding-top: px;display: none;">
						 <input class="easyui-text" maxlength="15" id="txtKinkAnnualtarget" name="txtKinkAnnualtarget"	style="width: 300px; text-align: right;" value="${requestScope.kpiTlIndicator.kinkAnnualtarget}" />
					</div>
					
					<div style="padding-top: 10px;display: none;"><label>Start Date</label> <span style="padding-left: 110px"><label>End Date</label></span></div>
					<div class="easyui-paddingbfpx" style="padding-top: 10px;display: none;">
						 <input class="easyui-text" id="dteKinkStartdate" name="dteKinkStartdate" style="width: 130px;"	value="${requestScope.kpiTlIndicator.kinkStartdate}" />
						 <span style="padding-left: 15px"> <input  id="chkenableend" name="chkenableend" type="checkbox" value="Q" /><span style="padding-left: 7px"><input class="easyui-text" id="dteKinkEnddate" name="dteKinkEnddate" style="width: 130px;"	value="" /></span></span>
					</div>
				</td>				
			</tr>						
	</table>			
	</div>
		 <input type="hidden" id="mode" name="mode" value=""/>
		 <input type="hidden" id="txtKinkKeyid" name="txtKinkKeyid" value="${requestScope.kpiTlIndicatorKk.kinkKeyid}" />
		 <input type="hidden" id="txtKinkLocation" name="txtKinkLocation" value="${requestScope.kpiTlIndicatorKk.kinkLocation}" />
<!--		 <input type="hidden" id="txtKinkParentid" name="txtKinkParentid" value="${requestScope.kpiTlIndicatorKk.kinkParentid}"/>-->
		 <input type="hidden" id="hdnloadpopup" name="hdnloadpopup" value="${requestScope.Loadpopup}" />
		 <input type="hidden" id="txtKinkLevelno" name="txtKinkLevelno" value="${requestScope.kpiTlIndicatorKk.kinkLevelno}" />
		 <input type="hidden" id="txtKinkSortno" name="txtKinkSortno" value="${requestScope.kpiTlIndicatorKk.kinkSortno}" />

	</form>
