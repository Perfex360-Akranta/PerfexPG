
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
	jQuery(document).ready(
			function() {  
				initialiseForm('frmSLADReport');
				var url = jQuery("#hiddenUrl").val();
                viewGrid(url,"q=2");
				jQuery("#btnNewDet").click(	function() {
					var fromPillar = jQuery("#hdnfrom").val();
					var mode = jQuery("#hdnmode").val();
					var type = jQuery("#hdnformType").val();
					navigateToNextForm("Department_input.SerLevAgr?grid=false&clearfrom=true&from="+fromPillar+"&mode="+mode+"&type="+type+"&status=","SLA Definition Entry");
				});


				var btnName = jQuery("#hdnBtnName").val();
				jQuery("#btnViewTemplate").val(btnName);
				jQuery("#btnViewTemplate").click(function()
						{
					//alert("Read From File");
					
					processAjaxCalls("openFile.file?fileName=SLA Blank Format.xls", "", "", "", "", "new");					
				});
				
			});
	function docDoubleClick(id) {
		var rowData = jQuery("#DepartMentReport").jqGrid('getRowData',id);
		var Keyid = rowData.keyid;
		var status = rowData.Status;
		var type = jQuery("#hdnformType").val();		
		var mode = jQuery("#hdnmode").val();
		var fromPillar = jQuery("#hdnfrom").val();
		var slatype= jQuery('#hdnslatype').val();
		var rolelevel= jQuery('#hdnroleLevel').val();
		var frmdmtid=rowData.SLAMFROMDPTID;
		var todmtid=rowData.SLAMTODPTID;
		
		navigateToNextForm("Slafrmtodmt_input.SerLevAgr?&frmdmtid="+frmdmtid+"&todmtid="+todmtid+"&mode="+mode+"&slatype="+slatype+"&rolelevel="+rolelevel,"SLA Definition Entry");

		//if (mode == "Authentication")
			//navigateToNextForm("Department_input.SerLevAgr?grid=true&clearfrom=false&Keyid="+Keyid+"&from="+fromPillar+"&mode="+mode+"&status="+status+"&type="+type+"&slatype="+slatype+"&rolelevel="+rolelevel,"SLA Definition Authentication");
		//else
			//navigateToNextForm("Department_input.SerLevAgr?grid=true&clearfrom=false&Keyid="+Keyid+"&from="+fromPillar+"&mode="+mode+"&status="+status+"&type="+type+"&slatype="+slatype+"&rolelevel="+rolelevel,"SLA Definition Entry");
	
	}
	function viewGrid(url,filterString)
	{ 
		//alert(" url :: "+url);
		var mode = jQuery("#hdnmode").val();
		var slatype= jQuery('#hdnslatype').val();
		var dataString="&mode="+mode+"&slatype="+slatype;
		processGridnew(url,dataString,"DepartMentReport", "pager", "", "docDoubleClick", "","GridOnCompleteload");
	}
	function GridOnCompleteload() {
		 
		 var row = jQuery("#DepartMentReport").jqGrid('getDataIDs');
		 var cm = jQuery("#DepartMentReport").jqGrid("getGridParam", "colModel");
		 
		 for(var i=0;i<row.length;i++)
		 {
		var status = jQuery("#DepartMentReport").jqGrid('getCell',row[i],"STATUS");
		
		if(status=="REJECTED")
		jQuery("#DepartMentReport").setCell(row[i], "STATUS", "",{'background-color':'#EC1708'});
		else if (status=="ACCEPTED")
		jQuery("#DepartMentReport").setCell(row[i], "STATUS", "",{'background-color':'#76CA00'});	
		 }
	}
</script>


	<form name="frmSLADReport" id="frmSLADReport" action=" " method="post">
		<div style=" margin-top:2px; padding-left: 40px;">
			<c:if test="${true  != requestScope.SlaFormBean.disableForAuth }">
				<input id="btnNewDet" name="btnNewDet" class="easyui-button" style="width: 75px;height: 21px;display:block;" type="button" value="New Detail"/>
			</c:if>
<!--			<span style="padding-left:10px;"><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 21px; width : 90px;"/></span>-->
			</div>
<div id='wrapperRpt' style=" margin-top:0%;">
		
				
			<table id="DepartMentReport">
				<tr>
					<td>
					</td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
		
		<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
		<input type="hidden" id="hdnfrom" name="hdnfrom"  value="${requestScope.from}" />	
		<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}" />
		<input type="hidden" id="hdnformType" name="hdnformType"  value="${requestScope.formType}" />
		<input type="hidden" id="hdnslatype" name="hdnslatype"  value="${requestScope.slatype}" />
		<input type="hidden" id="hdnroleLevel" name="hdnroleLevel"  value="${requestScope.roleLevel}" />		
	</form>




