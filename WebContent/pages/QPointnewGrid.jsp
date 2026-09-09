<script>
jQuery(document).ready(function(){//alert(12);
	viewGrid('q=2&type=PH');
	var factId = jQuery("#frmQPoints input[id='factory']").val();
	var sectionId = jQuery("#frmQPoints input[id='section']").val();
	var cellId = jQuery("#frmQPoints input[id='cell']").val();
	var machId = jQuery("#frmQPoints input[id='machine']").val();
	var flid = jQuery("#frmQPoints input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
 	loadFunctionalLocation("qpointfunlocation","functionalLoc.commonFilter","qpointfunlocationValues","frmQPoints",dataStr);
});
function viewGrid(filter){
	processGridnew("QPointsnew_input.qp",filter,"jqGridQpoint","jqGridQpointPager","","","","");	
}
function frmQPoints_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmQPoints","650px");
}
function Select(val){
	viewGrid('q=2&type='+val);
}
</script>
<form id="frmQPoints" name="frmQPoints">
	<div id="wrapperRpt" style="width:90%" >
		<table>
			<tr>
				<td colspan="3">
					<div  id="frmQPointsFuntKeyIds"  >
							<div style="padding-right: 20px;">
								<input type="hidden" id="factory" name="factory" value=" "  ></input>			
								<input type="hidden" id="section" name="section" value=" "  ></input>
								<input type="hidden" id="cell" name="cell" value=" "  ></input>
								<input type="hidden" id="machine" name="machine" value=" "  ></input>
								<input type="hidden" id="flid" name="flid" value=" "  ></input>
							</div>
							<div id="qpointfunlocation" class="padding" style="width:90%;"></div>
					</div>
				</td>
				<td style="padding-left:10px;">
					<div class="easyui-paddingbfpx"><label>KPOV</label></div>
					<div>	
						<select class="easyui-text"	id="cmbKPOV" name="cmbKPOV" onChange=Select(this.value) style="width: 250px; height: 21px;">
							<option value='PH'>Main steam pH (<8.4 , >9.1)</option>
							<option value='CN'>Main steam Conductivity (>5 us/cm)</option>
							<option value='PW'>Process water PH ( <6.7 & >7.6 )</option>
							<option value='LH'>LP steam temperature high (>175 deg.C)</option>
							<option value='FRC'>Process water FRC (<0.5 , >1.0 ppm)</option>
						</select>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-top:10px; float: left;">
				<table id="jqGridQpoint">
					<tr><td></td></tr>
				</table>
				<div id="jqGridQpointPager"></div>
		</div>
	</div>
</form>