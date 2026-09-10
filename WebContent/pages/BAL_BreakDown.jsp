<script type="text/javascript">

jQuery(document).ready(function(){
  // alert(1);
	initialiseForm("frmBreak");
	
         jQuery('#submitForm').val('frmBreak');
       //  fillComboBox("frmKpov","cmbSopm","emplo.commonFilter" );
         var url = jQuery('#hiddenUrl').val();
     	fillComboBox("frmBreak","cmbOrderType","Order.commonFilter");
         
         viewGrid("Break_input.bre","q=2");
         jQuery("#btnnew").click(function(){
     		navigateToNextForm("");
     	});
     });
   
         function viewGrid(url,filterString)
     	{
    
     			var tableCaption = "Break";
     			
     		//	 var po = jQuery('#frmKpov').val();
     			 
     			//processGridnew(url,filterString,"KpovGrid?po="+po,"pager",tableCaption,"doubleClickGrid","","loadComplete","","");

     			processGridnew(url,filterString,"BreakGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
     		    return true;
     			
     		
     	} 
     	</script>
     	
<form name="frmBreak" id="frmBreak">
<div id="wrapper">

<table>
	<tr>
							<td valign="top";colspan="3">
							<div class="easyui-paddingbfpx">
									<label>Order Type</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input type="text" class="easyui-text" id="cmbOrderType" name="cmbOrderType" maxlength="30" style="width: 300px; height: 20px"/>
								</div>
                                <div class="easyui-paddingbfpx">
									<label>Work Center</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtWorkCenter" name="txtWorkCenter" type="text" class="easyui-text"  maxlength="95"  style="width: 300px; height: 21px;" value="" />
								</div>
								
									
								
	<td style="padding-left: 30px;" valign="top">	
	
	  <div class="easyui-paddingbfpx">
									<label>Sap Cost Center</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtSapCost" name="txtSapCost" type="text" class="easyui-text"  maxlength="95"  style="width: 300px; height: 21px;" value="" />
								</div>
								  <div class="easyui-paddingbfpx">
									<label>Sap Function Locn</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtSapFunction" name="txtSapFunction" type="text" class="easyui-text"  maxlength="95"  style="width: 300px; height: 21px;" value="" />
								</div>
								
		<td style="padding-left: 30px;">	
	
	  <div class="easyui-paddingbfpx">
									<label>No Of Capacity Required</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtNoOfCap" name="txtNoOfCap" type="text" class="easyui-text"  maxlength="95"  style="width: 200px; height: 21px;" value="" />
								</div>
								  <div class="easyui-paddingbfpx">
									<label>Normal Duration Of the Activity</label>
							</div>
								<div class="easyui-paddingbfpx">
									<input id="txtNormal" name="txtNormal" type="text" class="easyui-text"  maxlength="95"  style="width: 200px; height: 21px;" value="" />
									
	</div>
														
								

</td>
</tr>
<tr> 
<td  colspan="2" style="padding-top:20px";>
<div class="sub-header" style="width:450px;">No Of Spares Count:     No Of Qty </div>
</td>
<td  style="padding-top: 20px">
									<input type="button" class="easyui-button" style="width:150px;" id="btnView" name="btnView" value="Refresh Sap info"/>
						
						<input type="button" class="easyui-button" style="width:150px;" id="btnView" name="btnView" value="Spares Information"/>			
							
</td>
</tr>
</table>
<div style="float:left;">
<table id="BreakGrid"  >
</table>
		<div id="pager"></div>
		
		</div>
	 <input type="hidden" id="mode"/>
	 </div>

</form>