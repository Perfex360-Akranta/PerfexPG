<script type="text/javascript">
jQuery(document).ready(function(){	


	var machineId = jQuery("#hdnmchId").val();
	if( machineId != null && machineId.length > 0 )
	{
		var actionPart = jQuery("#hiddenUrl").val();
	
		var month = jQuery('#hdnMonth').val();
		var dataString = '?q=1&machineId='+machineId;
		dataString += '&month='+month;
		dataString += "rowid="+machineId;
		processGridnew(actionPart,dataString,"jhlist","pager","","doubleClickGrid","","JHStandard_loadComplete");
	}	
	else			
		viewGrid("JhStandard_input.jhStandRpt","?");
	
});

function JHStandard_loadComplete()
{		
	var url = jQuery('#hiddenUrl').val();	
	var rowIds = jQuery("#jhlist").getDataIDs();
	var parentId = jQuery("#jhlist").jqGrid('getCell', rowIds[0], 'jhlist_keyid');			

	/*if(parentId.substr(0,3) != 'CMP')		
		{
		
		hideShowBack(true);
		}
	else		
		{
	
		hideShowBack(false);
		}*/
var url = jQuery('#hiddenUrl').val();	
var rowIds = jQuery("#jhlist").getDataIDs();
var parentId = jQuery("#jhlist").jqGrid('getCell', rowIds[0], 'keyid');			

if(parentId.substr(0,3) == 'MCH')			
	hideShowBack(false);	
else	
	hideShowBack(true);	
		
}

function viewGrid(url,filterString)
{

	
	if(filterString=="?")
		processGridnew(url,filterString,"jhlist","pager",tableCaption,"doubleClickGrid","","JHStandard_loadComplete");
	
	else if( validateFilterSelection(filterString))
	{
		jQuery('#hdnFilter').val(filterString);
		var tableCaption = "DrillDown (JH) Report";
		processGridnew(url,filterString,"jhlist","pager",tableCaption,"doubleClickGrid");
		return true;
	}
	return false;	
}
function doubleClickGrid(rowid)
{		
	
		var actionPart = jQuery("#hiddenUrl").val(); //document.getElementById('hiddenUrl').value;
		var rowData = jQuery("#jhlist").jqGrid('getRowData',rowid);
		var keyid = rowData.KEYIDFIELD;  //machine Keyid
		var calKeyid = rowData.keyid; //Calender keyid
		
		if(rowData.keyid != '' && rowData.keyid!= null)
		{
			
				var jsonstr = "{\"mchId\":\""+ mchId + "\"}";
				var perstData = jQuery.parseJSON(jsonstr);		
				var filter = jQuery('#hdnFilter').val();	
				var month = jQuery('#hdnMonth').val();
				var mchId = jQuery('#hdnmchId').val();	
				var filterString = "?q=2&dtFromMonth="+month;
				filterString += "&dtToMonth="+month;
				filterString += "&cmbMchid="+mchId;
				filterString += "&areaId="+calKeyid;
				
				//navigateToNextForm('jhcalendar_input.jhcal'+'?filterString='+escape(filter),"",null,perstData);
				navigateToNextForm('jhcalendar_input.jhcal'+'?filterString='+escape(filterString)+'&filterButton=false',"",null,perstData);
		}
		else
		{
		
		if(keyid.substring(0,3) != 'MMA')
		{			
			var month = jQuery('#hdnMonth').val();
			var dataString = '?q=1&machineId='+keyid;
			dataString += '&month='+month;

			processGridnew(actionPart,dataString,"jhlist","pager","","doubleClickGrid","","JHStandard_loadComplete");
		
			jQuery('#hdnmchId').val(keyid);
		}
		}
}


function jhlist_onProcessGridBack(){

	
	var actionPart = jQuery("#hiddenUrl").val();//document.getElementById('hiddenUrl').value;					
	var backVal=true;

	var rowid=jQuery("#jhlist").getDataIDs()[0];
	var rowData = jQuery("#jhlist").jqGrid('getRowData',rowid);
	var keyid = rowData.keyid;
	
	if(keyid.substring(0,3) != 'MCH')
	{		
		var dataString = '?q=2&backVal='+backVal;
		jQuery("#jhlist").GridUnload();
		viewGrid(actionPart,dataString);
	}
	else
		{
		
		viewGrid("JhStandard_input.jhStandRpt","?");
		}
	
  }

function validateFilterSelection(filterString){

	/*if( filterString.length != 0)
	{
		 if( ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Enter Month");
			return false;
		}

		 return true;
	}	*/
	return true;
}



</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
<div id="wrapperRpt">
<div class="clear"></div>
<div style="margin-top: -28px">
<label class="notes"   style="font-weight: bold; padding-left:20px; " > ${requestScope.drilldownMsg}</label></div>
<table id="jhlist" ></table>
<div id="pager"></div>
<input type="hidden" id="hdnMonth" name="hdnMonth" value="${requestScope.month}" />
<input type="hidden" id="hdnFilter" name="hdnFilter" />
<input type="hidden" id="hdnmchId" name="hdnmchId" value="${requestScope.machineId}"/>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>	