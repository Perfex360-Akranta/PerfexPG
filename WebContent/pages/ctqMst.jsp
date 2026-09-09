<script type="text/javascript">
jQuery(document).ready(function()
{
	 
	initialiseForm('frmctq');
	jQuery('#submitForm').val('frmctq');
	var factId = jQuery("#frmctq input[id='factory']").val();
	var sectionId = jQuery("#frmctq input[id='section']").val();
	var cellId = jQuery("#frmctq input[id='cell']").val();
	var machId = jQuery("#frmctq input[id='machine']").val();
	var flid =jQuery("#frmctq input[id='flid']").val();
	//fillComboBox("frmctq","cmbName","");
	 var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
	 loadFunctionalLocation("ctqfunLocation","functionalLoc.commonFilter","ctqfunLocationfunLocation","frmctq",dataStr);
	});
	
	function frmctq_FuntLocHierarchy_SuccessCallBack(keyIds){
		//setTimeout(function() {  
			 jQuery('#LoadMasterTbl').css({'margin-left':'-25px','margin-top':'-10%'});
			 jQuery("#frmctqctqfunLocationfunLocation>div[id='dispFunctionalLoc']").css('width','650px');
			 //jQuery('#hdnkpivflid').val(keyIds.flId);
		//},1200);	
	}
	function frmctq_beforeSubmit(){
		var flid =jQuery("#frmctq input[id='flid']").val();
 
		//jQuery('#hdnkpivflid').val(flid);
	}
</script>

<form name="frmctq"  id="frmctq">
	<div id="wrapperRpt" style="">
			<table>
				<tr>
					<td>
						<div id="frmctqFuntKeyIds"  >							
									<input type="hidden" id="factory" name="factory" value=" "  ></input>			
									<input type="hidden" id="section" name="section" value=" "  ></input>
									<input type="hidden" id="cell" name="cell" value=" "  ></input>
									<input type="hidden" id="machine" name="machine" value=" "  ></input>
									<input type="hidden" id="flid" name="txtkpivflid" value=""></input>							
						</div>						
						<div id="ctqfunLocation" ></div>
					</td>
			</tr>
			
			<!-- <tr>
				<td valign="top" style="">
						<div class="easyui-paddingbfpx">
							<label >Ctq</label>
						</div>
						<div>
							<input class="easyui-combobox" id="cmbName" name="cmbName"  value="" style=" width : 250px;"  />

						</div>
					</td>
				</tr>
 -->			<tr>
				<td>
					<div class="easyui-paddingbfpx">
						<label  class ="mandatory-lbl">Name</label>
					</div>
					<div class="easyui-paddingbfpx" style=" ">
						<input id="txtkpivname" type="text" class="easyui-text"  value="" size="15" name="txtkpivname"  maxlength="50"    style="width:250px; ">
					</div>
				</td>
			</tr>	
		</table>
	</div>
	<input type='hidden' id='mode'/>
	<input id="txtkpivkeyid" type="hidden" name="txtkpivkeyid"/>
<!-- 	<input type="text" id="hdnkpivflid" name="hdnkpivflid"  ></input> -->
</form>