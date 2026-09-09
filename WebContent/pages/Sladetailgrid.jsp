<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
	jQuery(document).ready(function() {  
			initialiseForm('frmSLADetailGridReport');
			var url = jQuery("#hiddenUrl").val();
			var mode=jQuery("#hdnmode").val();
			var todmtid=jQuery("#todmtid").val();
			viewGrid(url,"q=2");
			
            if(mode=="Authentication"){
                jQuery("#sladtlgrdbtn").css("display","none");
                jQuery("#slawrpt").css("margin-top",'30px');
            	jQuery("#slawrpt").css("margin-left",'40px');
            }else{
            	jQuery("#slawrpt").css("margin-top",'0px');
            	jQuery("#slawrpt").css("margin-left",'40px');
            }
            
			jQuery("#btnNewDet").click(	function() {
				navigateToNextForm("Department_input.SerLevAgr?grid=true&clearfrom=false&mode="+mode+"&todmtid="+todmtid,"SLA Definition Authentication");
			});
				
	});
	
	function docDoubleClick(id) {
		var rowData = jQuery("#SLADetailGridReport").jqGrid('getRowData',id);
		var mstid=rowData.MasterId;
		var dtlid=rowData.DetailId;
        var mode=jQuery("#hdnmode").val();
    //    alert("mode"+mode);
        var status=rowData.Status;

		if(status=="A"){
			alert(" This Record is already Approved ");
			return false;	
		}
		
		navigateToNextForm("Department_input.SerLevAgr?&Keyid="+mstid+"&dtlid="+dtlid+"&mode="+mode+"&hidebutton=Y","SLA Definition Entry");
	}
	
	function viewGrid(url,filterString)
	{ 
		var frmdmtid = jQuery("#frmdmtid").val();
		var todmtid= jQuery('#todmtid').val();
		var sladtype= jQuery("#hdnsladtype").val();
		var sladrolevel=jQuery("#hdnsladrollvl").val();
		var dataString="&frmdmtid="+frmdmtid+"&todmtid="+todmtid+"&sladtype="+sladtype+"&sladrolevel="+sladrolevel;
		processGridnew("Slafrmtodmt_input.SerLevAgr",dataString,"SLADetailGridReport", "SLADetailGridpager", "", "docDoubleClick");
	}

</script>


	<form name="frmSLADetailGridReport" id="frmSLADetailGridReport" action=" " method="post">
		<div id="sladtlgrdbtn" style="margin-top:2px;padding-left:40px;">
			<input id="btnNewDet" name="btnNewDet" class="easyui-button" style="width: 75px;height: 21px;" type="button" value="New Detail"/>
		</div>
		<div id='slawrpt'>
			<table id="SLADetailGridReport">
				<tr>
					<td>
					</td>
				</tr>
			</table>
			<div id='SLADetailGridpager'></div>
		</div>
		
		<input type="hidden" id="frmdmtid" name="hdnfrom"  value="${requestScope.frmdmtid}" />	
		<input type="hidden" id="todmtid" name="todmtid"  value="${requestScope.todmtid}" />
		<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}" />
		<input type="hidden" id="hdnsladtype" name="hdnsladtype"  value="${requestScope.slatype}" />
		<input type="hidden" id="hdnsladrollvl" name="hdnsladrollvl"  value="${requestScope.rolelevel}" />
		
	</form>




