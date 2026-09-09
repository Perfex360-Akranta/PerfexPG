<script type="text/javascript"><!--

jQuery(document).ready(function(){
	
	initialiseForm('frmPCS'); 
	setLoadFormCallBackFrmId("frmPCS");
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();
	var filterStr=actionPart.substring(27);	
	actionPart=actionPart.substr(0,26);
	
 	if(screen.width == 1366){
	 	jQuery('#tabprodSummaryPcs').css('width','1366'); 	
 	}
});

function forTab(filterString){
	 processGridnew("PcsSummary_input.pcsry?","&forTabDaily=Y"+filterString,"pcsDaily","pagerDaily"," ","","","daily_LoadComplete");
	 
	 jQuery(".save-loading").css('display','block');
		jQuery("#pcsDaily").setGridParam({url:'PcsSummary_getData.pcsry' });
		processGridnew("PcsSummary_input.pcsry?","&forTab=Y"+filterString,"pcs","pager"," ");
	 	processGridnew("PcsSummary_input.pcsry?","&forTabMT=Y"+filterString,"pcsmt","pager1"," ","","","dailyMon_LoadComplete");
		processGridnew("PcsSummary_input.pcsry?","&forTabMonthly=Y"+filterString,"pcsmonthly","pagermonthly"," ","","","monthly_LoadComplete");
		
			jQuery("#pcsmt").setGridParam({url:'PcsSummary_getData.pcsry' });
			processGridnew("PcsSummary_input.pcsry?","q=2&forTabRemarks=Remarks"+filterString,"pcsRemarks","pagerRemarks"," ");
	
	 			jQuery('#tabprodSummaryPcs .tabs-panels').css('height','350');
	
}

function daily_LoadComplete(){
	jQuery(".save-loading").css('display','none');
	
	
	jQuery('#tabprodSummaryPcs .tabs-panels').css('height','392');
	var location = jQuery('#locationHalol').val();
	if(location.trim().length>0){
		hideJqGridRow("pcsDaily", 1);
		hideJqGridRow("pcsDaily", 2);
	}
	if(jQuery('#pcsDaily tr').hasClass('totalRow')){
		  jQuery(' tr.totalRow').find(' td:first-child').css('color','#000');
		  jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #8DB2E3');
		  jQuery("#pcsDaily tr" ).removeClass("totalRow");
		}
	setTotalRowCss('pcsDaily');
	
}
function dailyMon_LoadComplete(){
	jQuery('#tabprodSummaryPcs .tabs-panels').css('height','392');
	var location = jQuery('#locationHalol').val();
	if(location.trim().length>0){
		hideJqGridRow("pcsmt", 1);
		hideJqGridRow("pcsmt", 2);
	}
	if(jQuery('#pcsDaily tr').hasClass('totalRow')){
		  jQuery(' tr.totalRow').find(' td:first-child').css('color','#000');
		  jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #8DB2E3');
		  jQuery("#pcsDaily tr" ).removeClass("totalRow");
		}
	setTotalRowCss('pcsDaily');
}
function monthly_LoadComplete(){
	jQuery('#tabprodSummaryPcs .tabs-panels').css('height','390');
	if(jQuery('#pcsmonthly tr').hasClass('totalRow')){
		  jQuery(' tr.totalRow').find(' td:first-child').css('color','#000');
		  jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #8DB2E3');
		  jQuery("#pcsmonthly tr" ).removeClass("totalRow");
		}
	setTotalRowCss('pcsmonthly');
}
function frmPCS_afterLoadCallBack(){
	jQuery('#tabprodSummaryPcs .tabs-panels').css('height','390');
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf('filter')<0)
		toggleCommonFilter();
	else
		jQuery('#hdnSetFilterValues').val('Y');
		
} 
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Production Summary";
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		if(removeBlank.trim() == "")
			jQuery("#hiddenRemoveBlank").val("Y");
		else
			jQuery("#hiddenRemoveBlank").val(removeBlank);
		filterString +="&chkRemoveBlank="+jQuery("#hiddenRemoveBlank").val();
		forTab(filterString);
		
	   jQuery('#hdnfromfilter').val('y');	
		return true;
	}
	return false;	
}  
function pcs_loadComplete(){
	jQuery('.tabs-panels').css('height','375');	
	jQuery("#pcs").children().removeClass("ui-jqgrid-sortable");	
}
function validateFilterSelection(filterString){

	var locn = jQuery("#hdnLocn").val();
	if(locn.trim() == "VASAI")
	{
		if( ! checkFilterValueExist(filterString,"cmbSectid") )
		{
			alert("Select DMT");
			return false;
		}			
	}
	else 
	{
		if( ! checkFilterValueExist(filterString,"cmbMchid") && !checkFilterValueExist(filterString,"cmbCircle"))
		{
			alert("Select Equipment");
			return false;
		}	
	}
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
	{
		alert("Select  From Date");
		return false;
	}
		return true;
}		
	
function fillColor()
{
	
var allRowsId = jQuery('#pcs').jqGrid('getDataIDs');

	for(var i=0;i<allRowsId.length;i++)
		{
		var ID=allRowsId[i];
		var nextTr = jQuery("#pcs tr[id="+ID+"]").next('tr').attr("id");
		var nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);
		var color=parseInt(nextRowData.Group).toString(16);
	
		if(nextRowData.GroupF == "M" || nextRowData.GroupF == "N"){
			while(nextRowData.GroupF=="M" || nextRowData.GroupF == "N")
			{				
				jQuery("#pcs").jqGrid('setCell',nextTr,"GroupField","",{'background-color':'#'+color});
				 nextTr = jQuery("#pcs tr[id="+nextTr+"]").next('tr').attr("id");				
				 nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);					
			}	}
		}
}

function frmFilter_enableDisableSuccessCallBack(){
						
enableFields('chkDatewise');

disableField("frmFilter","dtetoDate");
jQuery("#chkDatewise").attr("checked",true);
jQuery("#chkMonthwise").attr("checked",false);
fillWithCurrentDate('dtefromDate');
if(jQuery('#hdnSetFilterValues').val() == 'Y')
	setFilterValues();	
}
</script>
<form name="frmPCS" id="frmPCS" >
<div id="wrapperRpt"  style="margin-top: 4px;">

	 <div  id="tabprodSummaryPcs" class="easyui-tabs"  style="width:1100px;height:420px;padding-left: 5px;">
			 <div title="Production Daily Report" style="padding-right:  0px;height:325px;">
			 

							 <div id="" style="margin-left:15px;">

							<div class="clear"></div>
							<table id="pcsDaily" ></table>
							<div id="pagerDaily"></div> 
							
					</div>	 
			  </div>
			 <div title="Production Result" style="padding-right:  0px;height:325px;">

					 <div id="" style="margin-left:15px;">

						<div class="clear"></div>
						<table id="pcs" ></table>
						<div id="pager"></div> 
						<input type="hidden" id="hiddenStr" value="sdsd" />
				</div>	 
			  </div>
			 <div title="Production MTD Result" style="padding-right:  0px;height:325px;">

		 			 <div id="" style="margin-left:15px;">
						<div class="clear"></div>
						<table id="pcsmt" ></table>
						<div id="pager1"></div> 
					 </div>
			 </div>
			 <div title="Production MTD Report" style="padding-right:  0px;height:325px;">
		 			 <div id="" style="margin-left:15px;">
						<div class="clear"></div>
						<table id="pcsmonthly" ></table>
						<div id="pagermonthly"></div> 
					 </div>
			 </div>
			 <div title="Remarks" style="padding-right:  0px;height:325px;">
		 			 <div id="" style="margin-left:15px;">
						<div class="clear"></div>
						<table id="pcsRemarks" ></table>
						<div id="pagerRemarks"></div> 
					 </div>
			 </div>
		</div>
</div>
<input type="hidden" id="hdnfromfilter" />
<input type="hidden" id="locationHalol" value="${requestScope.locationHalol}"/>
<input type="hidden" id="hiddenRemoveBlank" name="hiddenRemoveBlank" value="" />
<input type="hidden" id="hdnLocn" value="${requestScope.locn}"/>	
<input type="hidden" id="hiddenfact" value="${requestScope.hdnfactId}"/>	
<input type="hidden" id="hiddensect" value="${requestScope.hdnsectId}"/>	
<input type="hidden" id="hdnfromdate" value="${requestScope.hdnfromdate}"/>	
<input type="hidden" id="hdntodate" value="${requestScope.hdntodate}"/>	
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />

</form>