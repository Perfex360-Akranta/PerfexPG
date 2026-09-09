<script type="text/javascript">
	jQuery(document).ready(function(){
		var url = jQuery('#hiddenUrl').val();
		var from = jQuery('#hdnFrom').val();
		initialiseForm('frmCriteriaMaster');
		jQuery('#submitForm').val('frmCriteriaMaster');
		jQuery('#frmCriteriaMaster-text').css('text-transform', 'uppercase');
		jQuery('#frmCriteriaMaster-combobox').css('text-transform', 'uppercase');
		
		var factId = jQuery("#frmCriteriaMaster input[id='factory']").val();
		var sectionId = jQuery("#frmCriteriaMaster input[id='section']").val();
		var cellId = jQuery("#frmCriteriaMaster input[id='cell']").val();
		var machId = jQuery("#frmCriteriaMaster input[id='machine']").val();
		var flid = jQuery("#frmCriteriaMaster input[id='flid']").val();

		var dataStr = "&factId=" + factId + "&sectionId="+ sectionId + "&cellId=" + cellId + "&machId="+ machId + "&flid=" +flid;//FNLN00000004
		loadFunctionalLocation("CriafunLocation","functionalLoc.commonFilter","CriafunLocationValues", "frmCriteriaMaster",dataStr);
		
		viewGrid(url,"?q=2");
		jQuery("#txtCriaMaximumpoints").bind("keyup",checkMaxMark);
		//processGridnew(url,"?q=2","criteriagrid","criteriapager"," ","doubleClickGrid");
		//if("criticality"!=from)
	});
	

	function checkMaxMark(){
			var val=getFieldValue("txtCriaMaximumpoints");
			val=val|0;
			if(parseInt(val)>900){
				alert("Maximum Value Should Be 900");
				setFieldValue("txtCriaMaximumpoints","900");
			}
		}
	function frmCriteriaMaster_FuntLocHierarchy_SuccessCallBack(keyIds)
	{  
		setFunctionalLocWidth('frmCriteriaMaster','499px');
		
		var flid = jQuery("#frmCriteriaMaster input[id='flid']").val();
		var url = jQuery('#hiddenUrl').val();
		
			viewGrid(url,"");
	
		
	}
	function viewGrid(url,filterString)
	{  
		var flid = jQuery("#frmCriteriaMaster input[id='flid']").val();
		if(flid == '' || flid == ' ' || flid == undefined)
		{
			
		}
		else
		{
			filterString = "flid="+flid;
			processGridnew(url,filterString,"criteriagrid","criteriapager"," ","doubleClickGrid");
		}
	}
	

</script>

<form id="frmCriteriaMaster">
	<div class="main-cntborder" >
		<table style="width:80%;height:100px;margin-left:5%;margin-top:1%">
			<tr>
				<td style=" vertical-align:top" >
					<div id="frmCriteriaMasterFuntKeyIds">
						<div style="float: left; padding-right: 20px;">
							<input type="hidden" id="factory" name="factory" value=""></input>
							 <input type="hidden" id="section" name="section" value=""></input>
							 <input type="hidden" id="cell" name="cell" value=""></input>
							  <input type="hidden" id="machine" name="machine" value=""></input>
							 <input type="hidden" id="flid" name="cmbCriaFlid" value="${requestScope.flid}"></input>
						</div>
						<div id="CriafunLocation" style="width: 550px;"></div>
					</div>					
				</td>
					
			</tr>
		
			<tr style="margin-top:2px">
				
				
			<tr style="margin-top:2px">
					
			</tr>
		</table>
		
		<input type="hidden" id="mode"/>
		<input type="hidden" id="txtCriaKeyid" name="txtCriaKeyid" value="${requestScope.PlmTlCriteriamst.criaKeyid}"/>
		
		<div style="margin-top:1%;" >
			<div class="clear"></div>
			<div style="float:left;margin-left:5%">
				<table id ='criteriagrid' >
					<tr><td></td></tr>
				</table>
				<div id ='criteriapager'></div>
			</div>
		</div>
	</div>	
</form>		