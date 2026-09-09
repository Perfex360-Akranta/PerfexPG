<script>
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"&q=2");
	jQuery ("#btnNew").click(function(){
		loadFmeaEntry("");
	});	
	
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnfmeaView").val(btnName);
	/*jQuery("#btnfmeaView").click(function(){
		//alert("Read From File");
		processAjaxCalls("openFile.file?fileName=Design FMEA.xls", "", "", "", "", "new");					
	}); */
});
function viewGrid(url,filterString)
{  
	processGridnew(url,filterString,"designGrid","pagerdesign","","docDoubleClick");
	return true;
}
function docDoubleClick(id)
{	var rowData = jQuery("#designGrid").jqGrid('getRowData',id);
	var keyid = rowData.keyid;
	loadFmeaEntry(keyid);	
}

function loadFmeaEntry(keyid){
	var type=jQuery("#txtFMEAType").val();
	var fmeaDocType=jQuery("#txtFmeaDoctype").val();
	var fmeaDocmstid=jQuery("#txtFmeaDocmstid").val();
	var fmeaDocdtlsid=jQuery("#txtFmeaDocdtlsid").val();
	var processId=jQuery("#txtProcessid").val();
	var subProcessid=jQuery("#txtSubprocessid").val();
	var ds ="?&filterButton=false&type="+type+"&keyid="+keyid+"&fmeaDocType="+fmeaDocType+"&fmeaDocmstid="+fmeaDocmstid;
		ds = ds+"&fmeaDocdtlsid="+fmeaDocdtlsid+"&processId="+processId+"&subProcessid="+subProcessid;
	navigateToNextForm("FMEAEntry_input.fmeaf"+ds);
}
</script>
<form id="fmFmea">
<div id='wrapperRpt' style="">
	<div>
		<table>
			<tr>
				<td>
					<div style="padding-left:2% ;margin-top: -28px">
						<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry"/>
					</div>
				</td>
				<!--<td>
					<div style="margin-top: -26px"><input type="button" class="easyui-button" id="btnfmeaView"	name="btnfmeaView" value="ViewFormat" style="height: 25px; width : 102px;"/></div>
				</td>
				-->
				<td >
					<div style="width : 332px;padding-left:10px;padding-left:0px\9;margin-top: -21px" >
						<label class="notes"> Double Click on row to input/view details </label>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<table id='designGrid'>
		<tr>
			<td></td>
		</tr>
	</table>
	<div id='pagerdesign'></div>
	</div>
	<input type="hidden" id="txtFMEAType" name="txtFMEAType" value="${requestScope.fmeaType}" />
	<input type="hidden" id="txtFmeaDoctype" name="txtFmeaDoctype" value="${requestScope.fmeaDocType}" />
	<input type="hidden" id="txtFmeaDocmstid" name="txtFmeaDocmstid" value="${requestScope.fmeaDocmstid}" />
	<input type="hidden" id="txtFmeaDocdtlsid" name="txtFmeaDocdtlsid" value="${requestScope.fmeaDocdtlsid}" />
	<input type="hidden" id="txtProcessid" name="txtProcessid" value="${requestScope.processId}" />
	<input type="hidden" id="txtSubprocessid" name="txtSubprocessid" value="${requestScope.subProcessid}" />

</form>