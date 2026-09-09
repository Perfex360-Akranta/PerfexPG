
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
	jQuery(document).ready(
			function() {  
				initialiseForm('frmSLADReport');
				var url = jQuery("#hiddenUrl").val();

				var sect=jQuery("#hdnsection").val();
				//alert("sect="+sect);
               var mode=jQuery("#hdnmode").val();
               //alert(mode);
				viewGrid("SLAEntryReport_input.slam","q=2&section="+sect+"&mode="+mode);
				


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
		var frmdptid = rowData.SLAMFROMDPTID;
		//alert("from"+frmdptid);
		var todptid = rowData.SLAMTODPTID;
		//alert("to"+todptid);
		var source = rowData.Source;
		//alert("source"+source);
		var slmkeyid = rowData.keyid;
		//alert(slmkeyid);
		
		var sleakeyid = rowData.sleakeyid;
		var athenstatus = rowData.Status;
		var athenmonth=rowData.Month;
		
		var sect=jQuery("#hdnsection").val();
		var role=jQuery("#hdnrole").val();
		var mode = jQuery("#hdnmode").val();
		var fromPillar = jQuery("#hdnfrom").val();
		var type = jQuery("#hdntype").val();
		if (mode =="View")
		{
			navigateToNextForm("SLAEntryReport_input.slam?grid=true&clearfrom=false&frmdmt="+frmdptid+"&todmt="+todptid+"&mode="+mode+"&source="+source+"&slmkeyid="+slmkeyid+"&type="+type+"&section="+sect+"&role="+role,"SLA Report");
		}else if(mode =="graph")
		{
			navigateToNextForm("SLAEntryReport_input.slam?grid=true&clearfrom=false&frmdmt="+frmdptid+"&todmt="+todptid+"&mode="+mode+"&source="+source+"&slmkeyid="+slmkeyid+"&type="+type+"&section="+sect+"&role="+role,"SLA Adherence Percentage Graph");
		}
		else if(mode =="authedication"){   
            if(role=="QM PILLAR MEMBER" || role=="DMT LEADER")
			{			
    		if(athenstatus=="ACCEPTED")
			{ 
				   alert("This is authendicated");
			}
			else{
			navigateToNextForm("SLAEntryauthendication_input.slam?grid=true&clearfrom=false&frmdmt="+frmdptid+"&todmt="+todptid+"&month="+athenmonth+"&mode="+mode+"&source="+source+"&slmkeyid="+slmkeyid+"&sleakeyid="+sleakeyid+"&type="+type+"&section="+sect+"&role="+role,"SLA Entry Authendication");
			}
			}
			else
			{
				alert("Only QM PILLAR MEMBER & DMT LEADER can authendicate");
			}
		}
		else
		{

		navigateToNextForm("SLAEntryReport_input.slam?grid=true&clearfrom=false&frmdmt="+frmdptid+"&todmt="+todptid+"&mode="+mode+"&source="+source+"&slmkeyid="+slmkeyid+"&type="+type,"SLA Entry");

		}
		
	}
	function viewGrid(url,filterString)
	{
		processGridnew(url, filterString,"DepartMentReport", "pager", "", "docDoubleClick", "","GridOnCompleteload");
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
		<!-- <div style=" margin-top:2px; padding-left: 40px;">
			<span style="padding-left:10px;"><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style="height: 21px; width : 90px;"/></span>
			</div> -->
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
		<input type="hidden" id="hdntype" name="hdntype"  value="${requestScope.type}" />
		<input type="hidden" id="hdnsection" name="hdnsection"  value="${requestScope.section}" />
		<input type="hidden" id="hdnrole" name="hdnrole"  value="${requestScope.rolename}" />		
	</form>




