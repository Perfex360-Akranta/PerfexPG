<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();	
	
	//viewGrid(actionPart,"?q=2");
	
	setLoadFormCallBackFrmId("frmIncedentIdVsCom");
	invokeAfterLoadFormCallBack();
	jQuery('#btnGraph').click(function(){
		
			var rowid = jQuery("#impVscomp").jqGrid('getGridParam','selrow');
			var rowData = jQuery("#impVscomp").jqGrid('getRowData',rowid);				
			var url = "chartIncidentimpVsCom.imvscom";// + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			
			showGraphData(url);
		});
});

function frmIncedentIdVsCom_afterLoadCallBack(){
	//var machineId = jQuery("#hdnmchId").val();
	//if( machineId == null || machineId.length <= 0 )
	var actionPart = jQuery('#hiddenUrl').val();
		
		if(actionPart.indexOf('filter')<0)
		{
			toggleCommonFilter();
		}	
		else
		{
			jQuery('#hdnSetFilterValues').val('Y');
			var datStr="?q=2";
			viewGrid("IncidentImproveVsComp_input.imvscom",datStr);
		}
	
}
function impVscomp_loadComplete()
{	
	setDrillDownHeader("CH1-0","impVscomp","KEYFIELD2");
	setTotalRowCss('impVscomp');
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
		if(filterString=="?q=2"){
			/*filterString+="&cmbCompid="+jQuery("#hiddenCompId").val();
			filterString+="&cmbLocnid="+jQuery("#hiddenLocnId").val();
			filterString+="&cmbMchid="+jQuery("#hiddenMchId").val();
			filterString+="&cmbFactid="+jQuery("#hiddenfact").val();
			filterString+="&cmbSectid="+jQuery("#hiddensect").val();
			filterString+="&cmbCellid="+jQuery("#hiddencell").val();
			filterString+="&dtFromMonth="+jQuery("#hdnfromMonth").val();
			filterString+="&dtToMonth="+jQuery("#hdntoMonth").val();*/
		}
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "Improvement Vs Completed";
		processGridnew("IncidentImproveVsComp_input.imvscom",filterString,"impVscomp","pager",tableCaption,"doubleClickGrid","","impVscomp_loadComplete");		
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
		/*else{
			 
			jQuery('#hdnfromDate').val(jQuery('#dtefromMonth').datebox('getValue'));
		}*/
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	}
	return  true;
}

function impVscomp_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("impVscomp","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			
					
function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("impVscomp","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
</script>
<form id="frmIncedentIdVsCom">
<div id="wrapperRpt"style= margin-top:13px;>
<table>
    <tr>
          <td>
          <div style="float: left;"><label class="notes" style="font-weight: bold;">Double Click  on Company/Factory/Section/Line to Drilldown</label>
          </div>
          </td>
          
          <td>
          <div style="float: right;">
	        <input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>  
	         </div>
          </td>
          
    </tr>
    
    <tr>
    <td colspan="2">
    <div class="clear"></div>
	<table id="impVscomp" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="divGraphContainer" ></div>	
	</td>
     </tr>
</table>
	
	
	
	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
	<input type="hidden" id="hiddenCompId" value="${requestScope.hiddenCompId}"  />
<input type="hidden" id="hiddenLocnId" value="${requestScope.hiddenLocnId}"  />
<input type="hidden" id="hiddenfact" value="${requestScope.hdnfactId}"  />
<input type="hidden" id="hiddensect" value="${requestScope.hdnsectId}"  />
<input type="hidden" id="hiddencell" value="${requestScope.hdncellId}"  />
<input type="hidden" id="hiddenMchId" value="${requestScope.hiddenMchId}"  />
<input type="hidden" id="hdnfromMonth" value="${requestScope.fromdate}"  />
<input type="hidden" id="hdntoMonth" value="${requestScope.todate}"  />
<input type="hidden" id="hdnfromdate" value="${requestScope.hdnfromdate}"  />
<input type="hidden" id="hdntodate" value="${requestScope.hdntodate}"  />
<input type="hidden" id="hdnFnlnKeyid" />
</div>
</form>


