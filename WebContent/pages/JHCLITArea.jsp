<script type="text/javascript">
jQuery(document).ready(function(){	

	viewGrid("JhStandard_input.jhStandRpt","?");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "DrillDown (JH) Report";
		processGridnew(url,filterString,"jhlist","pager",tableCaption,"doubleClickGrid");
		//alert(filterString);
		return true;
	}
	return false;	
}
function doubleClickGrid(rowid)
{	
	
		var actionPart = document.getElementById('hiddenUrl').value;
		var rowData = jQuery("#jhlist").jqGrid('getRowData',rowid);
		var keyid = rowData.jhkeyid;
		//alert(keyid);
		if(rowData.keyid != '' && rowData.keyid!= null)
		{
			//alert(rowData.keyid);
			//var keyId = escape("&cmbCompid=CMP001&cmbFactid=FCT001&cmbSectid=LIN006&cmbCostCenter=&cmbCircle=&cmbCellid=CEL004&cmbMchid=MCH000002&dtFromDate=01-Sep-2011&dtToDate=23-Feb-2012&dtFromMonth=Feb-2012&dtToMonth=Feb-2012&year=&cmbAssmbid=&cmbEqpGrpid=&cmbMchRnkid=&cmbTradeid=&chkMonthwise=1&firstClick=Y&parentId=CEL004") ;
			var mchId = jQuery('#hdnmchId').val();			 		
			var keyId = escape("&cmbMchid="+mchId+"&dtFromMonth=Feb-2012&dtToMonth=Feb-2012&chkMonthwise=1&firstClick=Y");			
			navigateToNextForm('jhcalendar_input.jhcal'+'?&keyId='+keyId,"");	
			
		}
		else
		{
		//actionPart='jhcalendar_input.jhcal';
		if(keyid.substring(0,3) != 'MMA')
		{			
			var dataString = '?q=1&rowid='+keyid;
			viewGrid(actionPart,dataString);
			jQuery('#hdnmchId').val(keyid);
		}
		}
}


function jhlist_onProcessGridBack(){

	
	var actionPart = document.getElementById('hiddenUrl').value;					
	var backVal=true;

	var rowid=jQuery("#jhlist").getDataIDs()[0];
	var rowData = jQuery("#jhlist").jqGrid('getRowData',rowid);
	var keyid = rowData.area;
	
	if(keyid.substring(0,3) != 'MCH')
	{		
		var dataString = '?q=2&backVal='+backVal;
		jQuery("#jhlist").GridUnload();
		viewGrid(actionPart,dataString);
	}
  }

function validateFilterSelection(filterString){
	
		 return true;
	}	
	






</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->

<input type="hidden" id="hdnmchId" name="hdnmchId" />
<div id="wrapper">
<div><label>
Double Click the data row to view Equipment Area Wise CLIT Count</label>
</div>
<div class="clear"></div>
<table id="jhlist" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>	