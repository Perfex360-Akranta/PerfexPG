<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
  <style>

.esr-completed
{
	display: block;
	width: 15px;
	height: 15px;
	background-color:#C0FFC0;
}
.esr-pending {
    background-color: #FDB8B8;
    display: block;
    height: 15px;
    width: 15px;
}
</style>
<script type="text/javascript">	

jQuery(document).ready(function(){
	initialiseForm('frmExternalServiceRequest');
	jQuery('#submitForm').val('frmExternalServiceRequest');
	processGridnew("ExternalSerReqMainGrid_input.ord","?status=N","ExternatSerReqGrid","ExtSerReqPager","ExternalServiceRequest", "doubleclick");
});
function doubleclick(id){
	navigateToNextForm('OrderExternal_input.ord'+'?q=1',"External Service Request");
	
}
</script>
<form id="frmExternalServiceRequest" name="frmExternalServiceRequest">
<div style="margin-left:3%; margin-top:3%;">
	<div >
		<table>
			<tr>
				<td>
					<span class="esr-pending"></span>
				</td>
				<td>
					<label style=" padding-left:10px;color:dark brown;font-weight: bold">Pending</label>
				</td>
				<td>
						<span style="margin-left:10px;" class="esr-completed"> </span>
				</td>
				<td>
					<label style=" padding-left:10px;color:dark brown;font-weight: bold">Completed</label>
				</td>
			</tr>
		</table>
	</div>
	<div>
		<div>
			<table id="ExternatSerReqGrid" ></table> 
		</div>
		<div id="ExtSerReqPager"></div>
		</div> 
	</div>
</form>