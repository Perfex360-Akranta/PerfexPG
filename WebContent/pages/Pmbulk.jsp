<script type="text/javascript">
jQuery(document).ready(function(){	
	
	
	initialiseForm('frmPmbulk');
	formatDateBox('dtedate','dd-MMM-yyyy');

	var factId = jQuery("#frmPmbulk input[id='factory']").val();
    var sectionId = jQuery("#frmPmbulk input[id='section']").val();
    var cellId = jQuery("#frmPmbulk input[id='cell']").val();
    var machId = jQuery("#frmPmbulk input[id='machine']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
   loadFunctionalLocation("PmbulkfunLocation","functionalLoc.brdn","PmbulkfunLocationValues","frmPmbulk",dataStr);

   processGridnew("Pmbulk_input.pmb","q=2","Pmbulkupload","pager","PMBulk Upload","","","");
   
});

</script>
<form name="frmPmbulk" id="frmPmbulk" action="" method="post">
			<div id="wrapper">
				<div class="main-cntborder" >
			<table>
					<tr>
					<td colspan="2" >
						<div id="frmPmbulkFuntKeyIds" >
							<input type="hidden" id="factory" name="cmbPmbulkFactoryid" ></input>
							<input type="hidden" id="section" name="cmbPmbulkSectionid" ></input>
							<input type="hidden" id="cell" name="cmbPmbulkCellid"  ></input>
							<input type="hidden" id="machine" name="cmbPmbulkMachineidhdn" ></input>
						</div>
						<div id="PmbulkfunLocation" style="padding-left:3%;width:800px"></div>
					</td>
					</tr>
					<tr>
					<td style="padding-left:3%;width:25%">
					<div><label>Equipment</label></div>
						<div class="easyui-paddingbfpx" > 
							<input  id="cmbmac" class="easyui-combobox" name="cmbmac"  style="width:200px;">
								<option value="1">MCH0000000001</option>
				   		 					<option value="2">MCH0000000002</option>		
				   		 					<option value="3">MCH0000000003</option>
				   		 					<option value="4">MCH0000000004</option>	
				   		 					</select>
											
						</div>
					</td>
					</tr>
					<tr>
					<td style="padding-left:3%">
					<div class="easyui-paddingbfpx" >
					<input type="radio" name="problemindata" value="" class=""/>
					<label><b>Problem in Data</b></label>
					<input type="radio" name="problemindata" value="" class=""/>
					<label><b>Problem in Saving</b></label>
					<input type="radio" name="problemindata" value="" class=""/>
					<label><b>Already Exist</b></label>
					</div>
					</td>
					</tr>
					</table>
					<div style="margin-top: -1%;padding-left:1%">
								<table id="Pmbulkupload"><tr><td></td></tr></table>
							</div>
					
			</div>
		</div>
	</form>