<script type="text/javascript">
jQuery(document).ready(function(){	
	
	
	initialiseForm('frmrbms');
	formatDateBox('dtedate','dd-MMM-yyyy');

	var factId = jQuery("#frmrbms input[id='factory']").val();
    var sectionId = jQuery("#frmrbms input[id='section']").val();
    var cellId = jQuery("#frmrbms input[id='cell']").val();
    var machId = jQuery("#frmrbms input[id='machine']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
   loadFunctionalLocation("rbmsfunLocation","functionalLoc.brdn","rbmsfunLocationValues","frmrbms",dataStr);

   processGridnew("rbms_input.rbm","?q=2","list","pager","RBMS Entry","","","");
   
});

</script>
<form name="frmrbms" id="frmrbms" action="" method="post">
			<div id="wrapper">
				<div class="main-cntborder" >
			<table>
					<tr>
					<td colspan="2" >
						<div id="frmrbmsFuntKeyIds" >
						<input type="hidden" id="factory" name="cmbrbmsFactoryid" ></input>
						<input type="hidden" id="section" name="cmbrbmsSectionid" ></input>
						<input type="hidden" id="cell" name="cmbrbmsCellid"  ></input>
						<input type="hidden" id="machine" name="cmbrbmsMachineidhdn" ></input>
						</div>
						<div id="rbmsfunLocation" style="padding-left:4%;width:800px"></div>
					</td>
					</tr>
					<tr>
					<td style="padding-left:4%;width:25%">
					<div><label > Date</label></div>
					<div class="easyui-paddingbfpx" > 
						<span><input id="dtedate" name="dteend" class="easyui-combobox"  style="width:150px;padding-left:-5%" value=""  ></span>
					</div>
					</td>
					<td>
					<div><label>Equipment</label></div>
					<div class="easyui-paddingbfpx" > 
					<select  id="cmbmac" class="easyui-combobox" name="cmbmac"  style="width:200px;">
								<option value="1">MCH0000000001</option>
				   		 					<option value="2">MCH0000000002</option>		
				   		 					<option value="3">MCH0000000003</option>
				   		 					<option value="4">MCH0000000004</option>	
				   		 					</select>
					</div>
					</td>
					</tr>
					</table>
					<div class="easyui-paddingbfpx" > 
<!--							  <div class="sub-header" style="width:973px;margin-left:3%;padding-left:2%;">-->
<!--								<label style="padding-left:3%"><b>RBMS Count</b></label>-->
<!--							</div>-->
							</div>
								<div style="margin-left:-14%;margin-top:-1%;padding-left:8%;">
									<table id="list"><tr><td></td></tr></table>
								</div>
				</div>	
			</div>
		</form>