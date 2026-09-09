<script>
jQuery(document).ready(function(){

	processGridnew("OtherLossEntry_input.pcs","?q=2","otherlossgrid","otherlosspager");

	//formatDateBox('dteOtherlossentrymonth', 'dd-MMM-yyyy');
	formatDateBox('dteOtherlossentryfromdate', 'dd-MMM-yyyy');
	formatDateBox('dteOtherlossentrytodate', 'dd-MMM-yyyy');
	
	fillWithCurrentDate('dteOtherlossentryfromdate');
	fillWithCurrentDate('dteOtherlossentrytodate');

	jQuery('#dteOtherlossentrymonth').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	});

	var factId = jQuery("#frmLossEntry input[id='factory']").val();
	var sectionId = jQuery("#frmLossEntry input[id='section']").val();
	var cellId = jQuery("#frmLossEntry input[id='cell']").val();
	var machId = jQuery("#frmLossEntry input[id='machine']").val();
	var flid = jQuery("#frmLossEntry input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("LossEntryfunLocation","functionalLoc.commonFilter","LossEntryfunLocationValues","frmLossEntry",dataStr);

	fillWithCurrentMonth('dteOtherlossentrymonth');
	
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
/*function allowExcel(sender) 
{
    var validExts = new Array(".xlsx", ".xls", ".csv");
    var fileExt = sender.value;
    fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
    if (validExts.indexOf(fileExt) < 0) 
        {
          alert("Invalid file selected, valid files are of " + validExts.toString() + " ");
          return false;
    }
    else return true;
}
*/
</script>

<!--  <input type="file" id="file1" style="display:none" onchange="allowExcel(this);"> -->

<form id="frmLossEntry" name="frmLossEntry">
<div id="" style="width:90%;margin-left:20px;margin-top:20px;">
<div style="margin-left:10px;margin-top:10px;">
    <table>
    <tr>
    <td colspan="3">
	<div  id="frmLossEntryFuntKeyIds" >
				<div style="float: left;padding-right: 20px;">
				<input type="hidden" id="factory" name="cmbLossEntryFactoryid" value=""  ></input>			
				<input type="hidden" id="section" name="cmbLossEntrySectionid" value=""  ></input>
				<input type="hidden" id="cell" name="cmbLossEntryCellid" value=""  ></input>
				<input type="hidden" id="machine" name="cmbLossEntryEquipmentid1" value=""  ></input>
				<input type="hidden" id="flid" name="cmbLossEntryflid" value=" "  ></input>
				
				</div>
				<div id="LossEntryfunLocation" style="width: 846px; "></div>
	
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
				<input class="easyui-text"id="dteOtherlossentrymonth" name="Otherlossentrymonth" maxlength="20"  value=" " style="text-transform: uppercase;width: 120px; height: 21px;" value=""/>
			</div>
		</td>


		<td>
		<div style="margin-top:0px;padding-left: 50px;">			
			<div>				
				<label style="padding-left: 20px;">From Date</label>
			</div>
			<div>
				<input type="checkbox" id="chkRange" name="chkRange" >
				<input class="easyui-text"id="dteOtherlossentryfromdate" name="dteOtherlossentryfromdate" maxlength="20"  value="" style="width: 120px; height: 21px;" value=""/>
			</div>
			</div>
		</td>
		<td>
			
			<div style="margin-left:10px;margin-top:0px;">
				<div>
				<label>To Date</label>
			</div>
			<div>
				<input class="easyui-text"id="dteOtherlossentrytodate" name="dteOtherlossentrytodate" maxlength="20"  value="" style="width: 120px; height: 21px;" value=""/>
			</div>
			</div>
		</td>
				
	</tr>
	
	</table>
	<table>
		<tr>
			<td>
				<div style="padding-top:4px;">
			 	<table id='otherlossgrid'><tr><td></td></tr></table>
			 	<div id='otherlosspager'></div>
		 	     </div>	
			</td>
		</tr>
	</table>
</div>
</div>
</form>