<script type="text/javascript"><!--
jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId("frmKaizenSuggestionCount");
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();	
	//alert("actionPart::::::::"+actionPart);
	//viewGrid(actionPart,"?q=1");
	
	jQuery('#btnGraph').click(function(){	
			var rowid = jQuery("#impVscomp").jqGrid('getGridParam','selrow');
			var rowData = jQuery("#impVscomp").jqGrid('getRowData',rowid);	
				var url = "kznsgncount_getchart.imvscom";
				showGraphData(url);	
			}
	);					
});	
function setDrillHeader(eleType){
	var levHeader="";
	if(eleType.trim()=="CMP")
		levHeader="Company";
	else if(eleType.trim()=="LCN")
		levHeader="Mill";
	else if(eleType.trim()=="SBU")
		levHeader="SBU";
	else if(eleType.trim()=="PBU")
		levHeader="PBU";
	else if(eleType.trim()=="L")
		levHeader="DMT";
	else if(eleType.trim()=="C")
		levHeader="JH";
	else if(eleType.trim()=="M")
		levHeader="Machine";
	return levHeader;			
}

function frmFilter_enableDisableSuccessCallBack()
{
	//jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
}
function frmFilter_enableDisableSuccessCallBack()
{
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
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		//alert("The Filter String:::"+filterString);
		//filterString += '&skipLine=Y';
		var tableCaption = "Improvement Vs Completed";
		processGridnew(url,filterString,"impVscomp","pager",tableCaption,"doubleClickGrid","","impVscomp_loadComplete");		
		return true;
	}
	return false;	
}
function frmKaizenSuggestionCount_afterLoadCallBack(){
	 toggleCommonFilter();
	}
function validateFilterSelection(filterString){
	if(filterString == "?q=1" )
		return true;
	/*if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
	{
		alert("Select Monthwise Checkbox");
		return false;
	}*/
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


function impVscomp_loadComplete()
{
	
	setDrillDownHeader("CH1-0","impVscomp","KEYFIELD2");
	setTotalRowCss('impVscomp');
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
<form id="frmKaizenSuggestionCount">
<div id="wrapperRpt"style= margin-top:2px;>
<table>
      <tr>
      		<td><div  style="">
	        <input id="btnGraph" class="easyui-button"  type="button" value="Bar Graph"/>  </div> 
	        </td>
           <td style="float:left;">
                     </td>
           
      </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="impVscomp" ></table>
	                <div id="pager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
	
	
</div>	
	
	
	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddencircle" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />
</form>



