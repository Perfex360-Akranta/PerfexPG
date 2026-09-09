<script type="text/javascript">
jQuery(document).ready(function(){	

	//alert(1);
	var actionPart = jQuery('#hiddenUrl').val();	
	viewGrid(actionPart,"?q=2");

	//setLoadFormCallBackFrmId("frmVisualScoreGraph");
	
	jQuery('#btnGraph').click(function(){
		//alert("button click line");
		fnShowVisualGraph("LINE");		
	});

	jQuery('#btnBarGraph').click(function(){
		//alert("button click bar");
		fnShowVisualGraph("COLUMN");
	});
});

	function fnShowVisualGraph(graphType) {
		//alert("button click line 1");
		var rowid = jQuery("#VisualGrid").jqGrid('getGridParam','selrow');
		var flid = "";
		var FirstLevel = 'Y';
		if (rowid=='' || rowid==' ' || rowid=='null' || rowid==null  ) { 
			rowid = 1;
			FirstLevel = 'Y';
		}
		var rowData = jQuery("#VisualGrid").jqGrid('getRowData',rowid);
		//alert("row data"+rowData);
		flid = rowData.KEYFIELD2;
		//alert("flid"+flid);
		var flidArr = flid.split("#");
		flid =flidArr[0];
		FirstLevel = 'Y';
		var type=jQuery('#hdnType').val();
		//alert(" ::  Checking Type in Jsp :: "+type);
		var ds = "?q&graphType="+graphType+"&flid="+flid+"&FirstLevel="+FirstLevel+"&type="+type;
		var url = "chartVisualScoreGraph.visc"+ds;
		//alert("url"+url);
		
		showGraphData(url);
	}

function VisualGrid_loadComplete()
{	
	setDrillDownHeader("CH1-0","VisualGrid","KEYFIELD2");
	//setTotalRowCss('VisualGrid');
}
function frmFilter_enableDisableSuccessCallBack()
{
	if(jQuery('#hdnSetFilterValues').val() == 'Y')
		setFilterValues();	
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);

	jQuery('#chkMonthwise').click(function(){
 		if(jQuery('#chkMonthwise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}
function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{		
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = " JH Self Audit Score ";
		var type=jQuery('#hdnType').val();
		filterString += "&type="+type;
		processGridnew("visualWorkplaceScore_input.visc",filterString,"VisualGrid","pager",tableCaption,"doubleClickGrid","","VisualGrid_loadComplete");		
		return true;
	}
	
	return false;	
}

function validateFilterSelection(filterString){
	
	if(filterString.length > 0  && ! checkFilterValueExist(filterString, "cmbLocnid"))
		{
		//alert("Select Location");
		return true;
		}
	if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
	{
		alert("Select Monthwise Checkbox");
		return false;
	}
	if(jQuery('#chkMonthwise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select  FromMonth");
			return false;
		}
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	}
	return  true;
}

function VisualGrid_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("VisualGrid","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			
					
function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("VisualGrid","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
</script>
<form id="frmVisualScoreGraph">
<div id="wrapperRpt"style= margin-top:13px;>
<table>
    <tr>
          <td>
          <div style="float: left;display: none;">
          	<label class="notes" style="font-weight: bold;">Double Click  on Company/Location/SBU/PBU/DMT/JH to Drilldown</label>
          </div>
          </td>
          
          <td>
	          <div style="padding-left: 0px;">
		        <input id="btnGraph" class="easyui-button"  type="button" value="Line Graph"/>  
		        
	          <span style="padding-left: 10px;">
		        <input id="btnBarGraph" class="easyui-button"  type="button" value="Bar Graph"/>  
		      </span>
		      
		      </div>
          </td>

          
    </tr>
    
    <tr>
    <td colspan="2">
    <div class="clear"></div>
	<table id="VisualGrid" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="divGraphContainer" ></div>	
	</td>
     </tr>
</table>
		
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
<input type="hidden" id="hdnfromMonth" value="${requestScope.fromdate}"  />
<input type="hidden" id="hdntoMonth" value="${requestScope.todate}"  />
<input type="hidden" id="hdnfromdate" value="${requestScope.hdnfromdate}"  />
<input type="hidden" id="hdntodate" value="${requestScope.hdntodate}"  />
<input type="hidden" id="hdnFnlnKeyid" />
<input type="hidden" id="hdnType" name="hdnType" value="${requestScope.type}"/>
</div>
</form>


