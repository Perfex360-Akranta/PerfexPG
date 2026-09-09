<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmPMAdherencegrid');
	jQuery('#submitForm').val('frmPMAdherencegrid');
	//formatDateBox('dteMonthdate', 'dd-MMM-yyyy');
	 //processGridnew("equipmentPM_input.eqp",dataString,"PmadherenceGrid","pager",tableCaption,"docDoubleClick");
	 processGridnew("PMAdherenceForm_input.eqp","?q=1&tasktype=TAS","PmadherenceGrid","pager"," ", "doubleclick");
		

	 jQuery('#dteMonthdate').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	 });  

	 jQuery("#btnViewReport").click(function()
				{
			//alert("Read From File");
			
		 processAjaxCalls("openFile.file?fileName=PMAdherencReport.xls", "", "", "", "", "viewPdf");				
		});
	 
});


</script>

<form id="frmPMAdherencegrid">

    <div class="easyui-paddingbfpx" id="wrapperRpt">
    <div style="width: 110%;" class="easyui-paddingbfpx"><label>Month</label>
       <span><label style="padding-left: 510px;" class="mandatory-lbl"> Current Month % =  Completed / Planned for the Month * 100  </label></span> 
    
    </div>
    <div style="width: 110%;" class="easyui-paddingbfpx">
    <input class="easyui-text" id="dteMonthdate" name="dteMonthdate" maxlength="80" style=" width : 100px;height:22px;">
   <span><input  type="button" class="easyui-button"  id="btnViewReport" name="btnViewReport" value="View Report" maxlength="80" style=" width : 100px;height:22px;"> </span>
   <span><label style="padding-left: 340px;" class="mandatory-lbl"> Cumulative % =  Completed / (Planned for the Month + Backlog) * 100   </label></span>
   </div>
		<table id='PmadherenceGrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>
		
	
	</div>
	<input type="hidden" id="mode" /> 
</form>