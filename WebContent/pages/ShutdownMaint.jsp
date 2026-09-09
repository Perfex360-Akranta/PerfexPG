<script type="text/javascript">
 
	jQuery(document).ready(function(){
		initialiseForm('frmSDM');	
		formatDateBox('dtesdmfromDate','dd-MMM-yyyy');
		formatDateBox('dtesdmtoDate','dd-MMM-yyyy');
		  fillComboBox("frmSDM","cmbsdmFrequencyunit","combo_pmsdwhtfreq.prv");
		/* for functionalLocation*/
		var factId = jQuery("#frmSDM input[id='factory']").val();
		var sectionId = jQuery("#frmSDM input[id='section']").val();
		var cellId = jQuery("#frmSDM input[id='cell']").val();
		var machId = jQuery("#frmSDM input[id='machine']").val();
		var flid = jQuery("#frmSDM input[id='flid']").val();
		
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		//alert(dataStr);
		loadFunctionalLocation("SDMfunLocation","functionalLocSDM.prv","pmsdfunLocationValues","frmSDM",dataStr+"&actType=SDM");
		jQuery('#btnGenCal').click(function(){
			var locId = jQuery("#frmSDM input[id='location']").val();
			var factId = jQuery("#frmSDM input[id='factory']").val();
			var sectionId = jQuery("#frmSDM input[id='section']").val();
			var cellId = jQuery("#frmSDM input[id='cell']").val();
			var machId = jQuery("#frmSDM input[id='machine']").val();
			var flid = jQuery("#frmSDM input[id='flid']").val();
			var valFromDate = getFieldValue('dtesdmfromDate','frmSDM');
			var valToDate = getFieldValue('dtesdmtoDate','frmSDM');
			 
			if(locId.trim().length<=0)
			{
				alert("Select Location");
				return false;
			}
			if(valFromDate.trim().length<=0)
			{
				alert("Select From Date");
				return false;
			}
			if(valToDate.trim().length<=0)
			{
				alert("Select To Date");
				return false;
			}
			var dataStr = "&locId="+locId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid+"&valFromDate="+valFromDate+"&valToDate="+valToDate+"&valfreq=";
			
			//processAjaxCalls("sdm_GenrateCal.prv",dataStr,'genCal_OnSuccess','genCal_OnError');
			saveForm("frmSDM","sdm_save.prv?&dataStr="+dataStr);
			 
			
		//PmsdEffectivedate,pmsdFactoryid,pmsdSectionid,pmsdCellid,pmsdMachineid,pmsdAssemblyid,pmsdFrequencyunit,pmsdMouldid
		});
	});
	function genCal_OnSuccess(result){

	}
</script>
<form id="frmSDM">
<div style="margin-left:1%;">
<table width="100%"  style="">  
			 <tr >
			 	<td colspan='3'>
			 	<div  id="frmSDMFuntKeyIds"  >
			 	<input type="hidden" id="location" name="cmbsdmLocationid" value=" "  ></input>
				<input type="hidden" id="factory" name="cmbsdmFactoryid" value=" "  ></input>
				<input type="hidden" id="section" name="cmbsdmSectionid" value=" "  ></input>
				<input type="hidden" id="cell" name="cmbsdmCellid" value=" "  ></input>
				<input type="hidden" id="machine" name="cmbsdmMachineid" value=" "  ></input>
				<input type="hidden" id="flid" name="cmbsdmFlid" value=" "  ></input>
				</div>
			 			<div id="SDMfunLocation" style=" "></div>
			 	</td>
			 </tr>
			 <tr>
			 	<td style=" width : 129px;">
			 		<div  class="">
	                    	<label class="mandatory-lbl">From Date</label>
	                    </div>
	                    <div class="easyui-paddingbfpx"  style="padding-top: 5px;"> 
	                        <input id="dtesdmfromDate" name="dtesdmfromDate" class="easyui-datebox"  style="width:110px;"  value="" />
						</div>
			 		
			 	</td>
			 	<td style=" width : 145px;">
			 		<div  class="">
	                    	<label class="mandatory-lbl">To Date</label>
	                    </div>
	                    <div class="easyui-paddingbfpx"  style="padding-top: 5px;"> 
	                    	<input id="dtesdmtoDate" name="dtesdmtoDate" class="easyui-datebox"  style="width:110px;"  value="" />
						</div>
			 		
			 	</td> 
			 	<td valign="bottom">
			 		   <div class="easyui-paddingbfpx"  style="padding-top: 5px;"> 
				 		<input id="btnGenCal" type="button" class="easyui-button" value="Generate Calendar"/>
				 		</div>
				 	</td>
			 </tr>
</table>
</div>
</form>