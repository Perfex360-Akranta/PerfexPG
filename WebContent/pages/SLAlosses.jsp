<script>
jQuery(document).ready(function(){

	processGridnew("SLALosses_input.SerLevAgr","?q=2","SlaLossgrid","SlaLosspager");

	//formatDateBox('dteSlalossentrymonth', 'dd-MMM-yyyy');
	formatDateBox('dteSlalossentryfromdate', 'dd-MMM-yyyy');
	formatDateBox('dteSlalossentrytodate', 'dd-MMM-yyyy');
	
	fillWithCurrentDate('dteSlalossentryfromdate');
	fillWithCurrentDate('dteSlalossentrytodate');

	jQuery('#dteSlalossentrymonth').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	});

	var factId = jQuery("#frmSlaLossEntry input[id='factory']").val();
	var sectionId = jQuery("#frmSlaLossEntry input[id='section']").val();
	var cellId = jQuery("#frmSlaLossEntry input[id='cell']").val();
	var machId = jQuery("#frmSlaLossEntry input[id='machine']").val();
	var flid = jQuery("#frmSlaLossEntry input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("SlaLossEntryfunLocation","functionalLoc.commonFilter","LossEntryfunLocationValues","frmSlaLossEntry",dataStr);

	fillWithCurrentMonth('dteSlalossentrymonth');
	
});


function TxtLossess(id, options, rowObject) {
    //alert("Inside Formmter");
	var Id = options.rowId;
	//alert("Id:1"+Id);
	var columnName = options.colModel.name;
	//alert("columnName:        "+columnName);
	var columnid = options.pos;
	//alert("columnid:4       "+columnid);

    return '<input type="button" class="easyui-button" id="dtnPmRpt" onclick="openFileOption()" name="dtnPmRpt" style="width:70px;height:17px;" value="...." />';
	
}
function openFileOption()
{
document.getElementById("file1").click();
}
</script>

<input type="file" id="file1" style="display:none" onchange="allowExcel(this);">

<form id="frmSlaLossEntry" name="frmSlaLossEntry">
<div id="wrapper" >
<div style="margin-left:10px;margin-top:-25px;">
    <table>
    <tr>
    <td colspan="3">
	<div  id="frmSlaLossEntryFuntKeyIds" >
				<div style="float: left;padding-right: 20px;">
				<input type="hidden" id="factory" name="cmbSlaLossEntryFactoryid" value=""  ></input>			
				<input type="hidden" id="section" name="cmbSlaLossEntrySectionid" value=""  ></input>
				<input type="hidden" id="cell" name="cmbSlaLossEntryCellid" value=""  ></input>
				<input type="hidden" id="machine" name="cmbSlaLossEntryEquipmentid1" value=""  ></input>
				<input type="hidden" id="flid" name="cmbSlaLossEntryflid" value=" "  ></input>
				
				</div>
				<div id="SlaLossEntryfunLocation" style="width: 846px; "></div>
	
	</div>		
</td>
    </tr>
    </table>

 	<table>
	<tr>
		<td>			
			<div>				
				<label style="padding-left: 20px;">Month</label>
			</div>
			<div>
				<input type="checkbox" id="chkMonth" name="chkMonth" checked="checked" >				
				<input class="easyui-text"id="dteSlalossentrymonth" name="dteSlalossentrymonth" maxlength="20"  value=" " style="text-transform: uppercase;width: 120px; height: 21px;" value=""/>
			</div>
		</td>


		<td>
		<div style="margin-top:0px;padding-left: 50px;">			
			<div>				
				<label style="padding-left: 20px;">From Date</label>
			</div>
			<div>
				<input type="checkbox" id="chkRange" name="chkRange" >
				<input class="easyui-text"id="dteSlalossentryfromdate" name="dteSlalossentryfromdate" maxlength="20"  value="" style="width: 120px; height: 21px;" value=""/>
			</div>
			</div>
		</td>
		<td>
			
			<div style="margin-left:10px;margin-top:0px;">
				<div>
				<label>To Date</label>
			</div>
			<div>
				<input class="easyui-text"id="dteSlalossentrytodate" name="dteSlalossentrytodate" maxlength="20"  value="" style="width: 120px; height: 21px;" value=""/>
			</div>
			</div>
		</td>
				
	</tr>
	
	</table>
	<table>
		<tr>
			<td>
				<div style="padding-top:4px;">
			 	<table id='SlaLossgrid'><tr><td></td></tr></table>
			 	<div id='SlaLosspager'></div>
		 	     </div>	
			</td>
		</tr>
	</table>
</div>
</div>
</form>