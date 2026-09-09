<script type="text/javascript">
	jQuery(document).ready(function ()
	{
		setLoadFormCallBackFrmId("frmPlanVsRej");
		invokeAfterLoadFormCallBack();
		var url = jQuery("#hiddenUrl").val();
		//processGridnew(url,"?q=2", "tableid","pagerid","","" );

		jQuery("#btnGraph").click(function(){
		 	var rowid = jQuery("#tableid").jqGrid('getGridParam','selrow');

		 	if(rowid !=null){
				if(checkForZeroes("tableid",rowid,3)){	
		 
					var url = "chartPlanVsRej.pcsrpt?rowid="+rowid+"&"+(rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
					showGraphData(url);
			  	 }
			
				 else 
			 		alert("No Record to View Graph");
			}

		 	else {
				var url = "chart.pcsrpt?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
				showGraphData(url);
			}
		});
	});
		
	function frmPlanVsRej_afterLoadCallBack(){
		toggleCommonFilter();	
	}
	function viewGrid(url,filterString)
	{
		 if( validateFilterSelection(filterString))
		{
			 
			var tableCaption = "Production vs Line Rejection Report";
			filterString += '&drillFlag=f';
			jQuery("#hdnFilter").val(filterString);
			
			processGridnew(url,filterString,"tableid","pagerid",tableCaption,"doubleClickGrid","","tableid_loadComplete");
			return true;
		}
		return false;	
	}

	function doubleClickGrid(id){
		var url = jQuery("#hiddenUrl").val();
		var rowData=jQuery("#tableid").jqGrid('getRowData',id);
		var selId = rowData.KEYFIELD;
		var filterData="?";
		filterData += "flid="+selId+'&drillFlag=f';

		if(selId.substr(0,3) !='MCH' & selId.trim() != ''){
	
			if(checkForZeroes("tableid",rowData,3)){
				alert(" No Record to View ");
				
			}
			else{
				processGridnew(url,filterData,"tableid","pagerid","","doubleClickGrid","","tableid_loadComplete");
			}
		}
	}

	function tableid_loadComplete(){
		var rowIds = jQuery("#tableid").getDataIDs();
		
		if (rowIds.length>=0){
			var parentId =  jQuery("#tableid").jqGrid('getCell', rowIds[0], 'KEYFIELD');
			if(parentId.substr(0,3) !='LIN'){
				hideShowBack(true);
			}
		}	
	}

	function tableid_onProcessGridBack(){		
		var url = jQuery("#hiddenUrl").val();
		var rowIds = jQuery("#tableid").getDataIDs();
		var dataString = 'drillFlag=b';
		var parentId =  jQuery("#tableid").jqGrid('getCell', rowIds[0], 'KEYFIELD');
		if(parentId.substr(0,3) =='CEL'){
			dataString = jQuery("#hdnFilter").val();
		}
		processGridnew(url,dataString,"tableid","pagerid","","doubleClickGrid","","tableid_loadComplete");
	}

	function validateFilterSelection(filterString){
		if( filterString.length != 0)
		{
			if( ! checkFilterValueExist(filterString, "cmbSectid"))
			{
				alert("Select DMT");
				return false;
			}
			 return true;
		}		
	}
</script>
	<table>
	<tr>
	<td>
	<div  style="margin-top:4px;margin-top:10px\9;margin-left:36px;">
		<label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>
	</div>
	</td>	
	<td>
	    <div style="margin-left:20px;">
		<input id="btnGraph" class="easyui-button"  type="button" value="Graph" style="width:50px;"/>   	
	    </div>
	</td>
	</tr>
	</table>
	<div id="wrapperRpt" style="margin-top:1%;">
		<div class="clear"></div>
		
		<div style="margin-top:-14px\9;">
		<table id ='tableid'>
			<tr><td></td></tr>
		</table>
		
		<div id ='pagerid'></div>
		</div>
		
		<input type="hidden" id="hdnFilter" value="" />
	</div>
