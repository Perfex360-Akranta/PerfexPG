<script  type="text/javascript">
		jQuery(document).ready(function(){	

		initialiseForm('frmpcm');
		formatDateBox('dtestart','dd-MMM-yyyy');
		formatDateBox('dteend','dd-MMM-yyyy');
		formatDateBox('dteActualStart','dd-MMM-yyyy');
		formatDateBox('dteEnd','dd-MMM-yyyy');
		formatDateBox('dteRevisedStart','dd-MMM-yyyy');
		formatDateBox('dteEndd','dd-MMM-yyyy');
		formatDateBox('dteplanstart','dd-MMM-yyyy');
		formatDateBox('dteplanend','dd-MMM-yyyy');
		formatDateBox('dteworkstart','dd-MMM-yyyy');
		formatDateBox('dteworkend','dd-MMM-yyyy');
		formatDateBox('dteprodstart','dd-MMM-yyyy');
		formatDateBox('dteprodend','dd-MMM-yyyy');
		spinnerKeyPress('spnstart');
		spinnerKeyPress('spnend');
		spinnerKeyPress('spnActualStart');
		spinnerKeyPress('spnEnd');
		spinnerKeyPress('spnRevisedStart');
		spinnerKeyPress('spnEndd');
		spinnerKeyPress('spnplanstart');
		spinnerKeyPress('spnplanend');
		spinnerKeyPress('spnworkstart');
		spinnerKeyPress('spnworkendd');
		spinnerKeyPress('spnprodstart');
		spinnerKeyPress('spnprodend');
		  var factId = jQuery("#frmpcm input[id='factory']").val();
		    var sectionId = jQuery("#frmpcm input[id='section']").val();
		    var cellId = jQuery("#frmpcm input[id='cell']").val();
		    var machId = jQuery("#frmpcm input[id='machine']").val();
		    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
		    loadFunctionalLocation("pcmsfunLocation","functionalLoc.brdn","pcmsfunLocationValues","frmpcm",dataStr);

			processGridnew("Reschedule_input.rsdl","q=2","list","","PCM","","","");
			processGridnew("ActivityView_input.rsdl","q=2","ActivityView","","PCM","","","");

			fillComboBox("frmpcm","cmbplan ","Plan.rsdl");
			fillComboBox("frmpcm","cmbActivitytype ","Activity.rsdl");
			fillComboBox("frmpcm","cmbtrade ","Trade.rsdl");
			fillComboBox("frmpcm","cmbassembly ","Assembly.rsdl");
			fillComboBox("frmpcm","cmbpl ","Pl.rsdl");
			fillComboBox("frmpcm","cmphenomena ","Phenomena.rsdl");
			fillComboBox("frmpcm","cmbcause ","Cause.rsdl");
			fillComboBox("frmpcm","cmbMachCond ","MachCond.rsdl");
			fillComboBox("frmpcm","cmbcmpby ","CompletedBy.rsdl");
			fillComboBox("frmpcm","cmbMachCondition ","MachCondition.rsdl");
			
		});	
		function res_loadcomplete(){
			
			jQuery('#tabplanConfig .tabs-panels').css('height','310');
			}
		function frmpcm_FuntLocHierarchy_SuccessCallBack(keyIds)
		{
			//alert('Loaded');
		}
		
	</script>
		<form name="frmpcm" id="frmpcm" action="" method="post">
			<div id="wrapper" style="height:135%;">
				<div class="main-cntborder" >
					<div class="sub-header" style="padding-right:2%;">
						<label><b>Main Information</b></label>
					</div>
					<table>
					<tr>
					<td colspan="2">
<!--						<label  style="padding-left:7%" >Functional Location</label>-->
						<div id="frmpcmFuntKeyIds" >
						<input type="hidden" id="factory" name="cmbpcmsFactoryid" ></input>
						<input type="hidden" id="section" name="cmbpcmsSectionid" ></input>
						<input type="hidden" id="cell" name="cmbpcmsCellid"  ></input>
						<input type="hidden" id="machine" name="cmbpcmsMachineidhdn" ></input>
						</div>
						<div id="pcmsfunLocation" style="width:1040px;padding-left:7%"></div>
<!--							<div class="easyui-paddingbfpx" style="margin-left:7%">-->
<!--						<span class="combo" style="padding-left:7%">-->
<!--						<input class="easyui-combobox" value="" type="text" style="padding-left:7%"/>-->
<!--						</span>-->
						
					</td>
					</tr>
					<tr>
					<td style="padding-left:7%;width:18%;" valign="top">
						<label  class="mandatory-lbl">Plan Area</label>
					
						<div class="easyui-paddingbfpx">
							
							<input id="cmbplan" name="cmbplan" style="width:211px;" class="easyui-combobox" />
							
				   		 					
							<input type="text" value="MCA011000005" style="width: 22%;"class="easyui-text"/>
							
						</div>
						<div>
						<label class="mandatory-lbl">Start Date</label>
						<label  class="mandatory-lbl" style="padding-left:100px">End Date</label>
						</div>
						<div class="easyui-paddingbfpx" > 
							<span ><input id="dtestart" name="dtestart" class="easyui-combobox"  style="width:85px;" value=""></span>
							<span class="spinner" ><input  id="spnstart" name="spnstart"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
							<span><input id="dteend" name="dteend" class="easyui-combobox"  style="width:85px;" value=""  ></span>
							<span class="spinner"><input  id="spnend" name="spnend"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
							
						</div>
						<td   width="19%">
						<label class="mandatory-lbl">Activity Type</label>
						<div >
							
							<input id="cmbActivitytype" name="cmbActivitytype"class="easyui-combobox" style="width:188px"/>
							<input type="text" value="WORKORDER ALLOTTED"style="width:20%;" class="easyui-text"/>
						</div>
						
						<label >Others</label>
						<div class="easyui-paddingbfpx" >
						<span valign="top">
							<textarea class="txtarea" rows="1" tabindex="8" value="" style="width:323px; padding-left:7%;height: 80px;" cols="" ></textarea>
						</span>
						
					</div>
					</td>
					</tr>
					</table>
					
						<div id="tabplanConfig" class="easyui-tabs"   style="height:310px;width:850px;padding:10px;margin-left:6%;margin-top:-1%;" >
							<div title="Activity Request" style="padding:10px;">
							<div class="sub-header" style="width:98%;padding-right:2%;margin-top:-1%">
										<label><b>Details for Planned Corrective Maintenance Request</b></label>
										</div>
										<table width="90%"   align="left">
										<tr >
										<td colspan="2" valign="top" style="width:100%;">
										<div><label>Trade</label></div>
										<div class="easyui-paddingbfpx" >
											<input  id="cmbtrade"  class="easyui-combobox" name="cmbtrade" style="width:304px;"/>
											
										</div>
										<div><label>Assembly</label></div>
										<div class="easyui-paddingbfpx" >
											<input  id="cmbassembly"  class="easyui-combobox" name="cmbassembly" style="width:272px;"/>
										
				   		 					<input type="button" class="easyui-button" value="..." id="btnbut" style="height:33%"/>
										</div>
										<div><label>Part Location</label></div>
										<div class="easyui-paddingbfpx" >
											<input  id="cmbpl"  class="easyui-combobox" name="cmbpl" style="width:304px;"/>
											
										</div>
										<div><label>Phenomena</label></div>
										<div class="easyui-paddingbfpx">
											<input  id="cmphenomena"  class="easyui-combobox" name="cmphenomena" style="width:272px;"/>
											
				   		 					<input type="button" class="easyui-button" value="..." id="btnbut" style="height:33%"/>
										</div>
										<div><label>Cause</label></div>
										<div class="easyui-paddingbfpx" >
											<input  id="cmbcause"  class="easyui-combobox" name="cmbcause" style="width:304px;">
											
										</div>
										<label  class="mandatory-lbl">Reason</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										<label  class="mandatory-lbl">Action Proposed</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										</td>
										<td colspan="2" valign="top">
										<label class="mandatory-lbl">Machine Condition</label>
										<div >
										<div class="easyui-paddingbfpx" >
											<input id="cmbMachCond" name="cmbMachCond" class="easyui-combobox"  style="width:213px;">
											
				   		 					<label >Plan Duration</label>
											<input type="text" value="0"style="width:16%;" class="easyui-text"/>
										</div>
										</div>
									<label>Root Cause</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:356px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										<label>Counter Measure</label>
										<div class="easyui-paddingbfpx" style="margin-bottom:5%;">
										<textarea class="txtarea" rows="1" tabindex="8" style="width:356px;resize:none; height: 80px;" cols="" ></textarea>
										</div>
										
										<div>
										<label class="mandatory-lbl">Plan Start</label>
										<label  class="mandatory-lbl" style="padding-left:113px"> Plan End </label>
										</div>
										<div class="easyui-paddingbfpx" > 
											<span ><input id="dteplanstart" name="dteplanstart" class="easyui-combobox"  style="width:101px;" value=""></span>
											<span class="spinner" ><input  id="spnplanstart" name="spnstart"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
											<span><input id="dteplanend" name="dteplanend" class="easyui-combobox"  style="width:101px;" value=""  ></span>
											<span class="spinner"><input  id="spnplanend" name="spnplanend"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
										</div>
										<label>Remarks</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:356px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										</td>
										</tr>
										</table>
										
							
							</div>
								<div title="Activity Completion" style="padding:10px;">
								<div class="sub-header" style="width:98%;padding-right:2%;margin-top:-1%">
										<label><b>Details for Planned Corrective Maintenance Completion</b></label>
										</div>
										<table width="100%"   align="left">
										<tr >
										<td valign="top" style="width: 45%">
								
										<label>Remarks</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:310px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										<div>
										<label   class="mandatory-lbl">Work Start</label>
										<label  class="mandatory-lbl" style="padding-left: 100px;">Work End </label>
										</div>
										<div class="easyui-paddingbfpx" > 
											<span ><input id="dteworkstart" name="dteworkstart" class="easyui-combobox"  style="width:87px;" value=""></span>
											<span class="spinner" ><input  id="spnworkstart" name="spnworkstart"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
											<span><input id="dteworkend" name="dteworkend" class="easyui-combobox"  style="width:87px;" value=""  ></span>
											<span class="spinner"><input  id="spnworkend" name="spnworkend"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
										</div>
										<div>
										<label   class="mandatory-lbl">Production Start</label>
										<label  class="mandatory-lbl" style="padding-left:62px;">Completed Date </label>
										</div>
										<div class="easyui-paddingbfpx" > 
											<span ><input id="dteprodstart" name="dteprodstart" class="easyui-combobox"  style="width:87px;" value=""></span>
											<span class="spinner" ><input  id="spnprodstart" name="spnprodstart"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
											<span><input id="dteprodend" name="dteprodend" class="easyui-combobox"  style="width:87px;" value=""  ></span>
											<span class="spinner"><input  id="spnprodend" name="spnprodend"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width:60px;" /></span>
										</div>
										<label  class="mandatory-lbl" >Completed By</label>
										<div class="easyui-paddingbfpx" >
											<input  id="cmbcmpby"  class="easyui-combobox" name="cmbcmpby" style="width:312px;">
											
										</div>
										<div>
										<label  class="mandatory-lbl" >Machine Condition</label>
										<label style="padding-left: 25%">Actual Duration</label>
										</div>
										<div class="easyui-paddingbfpx" >
											<input  id="cmbMachCondition"  class="easyui-combobox" name="cmbMachCondition" style="width:196px;">
											<input type="text" value="0"style="width:31%;" class="easyui-text"/>
										</div>
										<div>
										<div>
										<label >ManPower</label>
										<label style="padding-left:40px;" >Spare</label>
										<label style="padding-left:72px;">Service</label>
										</div>
											<input type="text" style="width:28%;" class="easyui-text"/>
											
											<input type="text" style="width:28%;" class="easyui-text"/>
											
											<input type="text" style="width:28%;" class="easyui-text"/>
										</div>
										<label>Root Cause</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:310px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
								</td>
								<td valign="top">
								<label>Counter Measure</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										<div><label >Observation</label></div>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;margin-top:1%;" cols="" ></textarea>
										</div>
										<div><label>Feed Back</label></div>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;margin-top:1%;" cols="" ></textarea>
										</div>
										<div><label>Remarks</label></div>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;margin-top:1%;" cols="" ></textarea>
										</div>
								
								</td>
							</tr>
						</table>
								
								
								</div>
									<div title="Reschedule Activity" style="padding:10px;">
										<div class="sub-header" style="width:98%;padding-right:2%;margin-top:-1%">
										<label><b>Details for Planned Corrective Maintenance Re-Scheduled</b></label>
										</div>
										<table width="100%"   align="left">
										<tr >
										<td valign="top">
										<div ><label> Actual Start </label></div>
										<div class="easyui-paddingbfpx" >
											<span><input id="dteActualStart" name="dteActualStart" class="easyui-datebox"  style="width:105px;" value=""  ></span>
											<span class="spinner"><input  id="spnActualStart" name="spnActualStart"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /></span>
										</div>
										<div ><label> Revised Start </label></div>
										<div class="easyui-paddingbfpx" >
											<span><input id="dteRevisedStart" name="dteRevisedStart" class="easyui-datebox"  style="width:105px;" value=""  ></span>
											<span class="spinner"><input  id="spnRevisedStart" name="spnRevisedStart"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /></span>
										</div>
										<label  class="mandatory-lbl" >Revised By</label>
										<div class="easyui-paddingbfpx" >
											<select  id="cmbrev"  class="easyui-combobox" name="cmbrev" style="width:172px;">
											<option value="1">Sachin Pilot</option>
				   		 					<option value="2">Vikram Bhat</option>		
				   		 					<option value="3">Sanjay Singhania</option>
				   		 					<option value="4">Ramgopal Varma</option>	
				   		 					</select>
											
										</div>
										
										</td>
										<td  valign="top" >
										<div ><label> End Date </label></div>
										<div class="easyui-paddingbfpx" >
											<span><input id="dteEnd" name="dteEnd" class="easyui-datebox"  style="width:105px;" value=""  ></span>
											<span class="spinner"><input  id="spnEnd" name="spnEnd"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /></span>
										</div>
										<div ><label> End Date </label></div>
										<div class="easyui-paddingbfpx" >
											<span><input id="dteEndd" name="dteEndd" class="easyui-datebox"  style="width:105px;" value=""  ></span>
											<span class="spinner"><input  id="spnEndd" name="spnEndd"  class="easyui-timespinner spinner-text validatebox-text" value=""  style="width: 60px;" /></span>
										</div>
										<label class="mandatory-lbl">Responsibility</label>
											<div class="easyui-paddingbfpx"  >
											
											<select id="cmbres" name="cmbres"  class="easyui-combobox" style="width:172px" >
											<option value="1">Sanjay RamaSwamy</option>
				   		 					<option value="2">Sachin Pilot</option>		
				   		 					<option value="3">Pranjyoth Roy</option>
				   		 					<option value="4">Ramgopal Varma</option>	
				   		 					</select>
											
											</div>
										</td>
										<td valign="top">
										<label  class="mandatory-lbl">Reason</label>
										<div class="easyui-paddingbfpx" >
										<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										<label  class="mandatory-lbl">Remarks</label>
										<div class="easyui-paddingbfpx" >
											<textarea class="txtarea" rows="1" tabindex="8" style="width:305px;resize:none; height: 68px;" cols="" ></textarea>
										</div>
										</td>
										</tr>
										</table>
										<div style="float:left;margin-top:-1%">
										
										<table id="list"><tr><td></td></tr></table>	
									</div>								
									</div>
									<div title="Activity View" style="padding:10px">
									<div class="sub-header" style="width:98%;padding-right:2%;margin-top:-1%">
										<label><b>Details for Planned Corrective Maintenance View</b></label>
										</div>
									<div style="float:left;margin-top:-1%;padding-left:2%;">
										<table id="ActivityView" style="margin-top:-1%" ><tr><td></td></tr></table>	
									</div>								
									
									</div>
									

					</div>
				
				</div>
			
			</div>
				
		</form>