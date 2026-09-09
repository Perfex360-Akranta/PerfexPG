<script type="text/javascript">
jQuery(document).ready(function(){	
	
	setLoadFormCallBackFrmId("frmMachinewisePhenRpt");
	invokeAfterLoadFormCallBack();
	//viewGrid("PcsComplianceRpt_input.pcscomp","");
	 
	var actionPart = jQuery('#hiddenUrl').val();
	jQuery('#hdnEField').val(' ');
	var  prevData = unescape(jQuery('#hdnPrevDataUrl').val());
		viewGrid(actionPart,"", jQuery("#frmMachinewisePhenRpt select[id= cboReportType ]").val());
		
});

function frmMachinewisePhenRpt_afterLoadCallBack(){
		//toggleCommonFilter();
}

	
function viewGrid(url,filterString,rptType)
{
	if(  validateFilterSelection(filterString))
	{
		
		var tableCaption = "Machinewise Phenomena Report";
		//alert("view Grid "+rptType);
		if(rptType == 'undefined' ||rptType == undefined){
		
			//rptType = getFieldValue('cboReportType');
			rptType = jQuery("#frmMachinewisePhenRpt select[id= cboReportType ]").val();
			
		}
		
		filterString += '&drillFlag=f&rptType='+rptType;
		jQuery.cookie("filterString",filterString.substring(0,filterString.length-9));
		/*
		filterString += '&drillFlag=f&rptType='+rptType;
		alert(firstClick.trim().length+ "  after  "+filterString);
		
		
		
			
		if( firstClick.trim().length > 0){alert('ff');
			 filterString = jQuery.cookie("filterString")+'&drillFlag=f&rptType='+rptType;
		}*/
		processGridnew(url,filterString,"machPhnRptGrd","pager_machPhnRpt",tableCaption,"machPhnRpt_doubleClickGrid","","machPhnRpt_loadComplete");
		return true;
	}

		
	return false;
}
function machPhnRpt_loadComplete_loadComplete(){ 

		jQuery("#machPhnRptGrd").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			jQuery("#hiddencelVal").val(cellcontent);
			jQuery("#hiddeniCol").val(iCol);
				 
			//pcs_doubleClickGrid(rowid,iCol,cellcontent);
		}});
		
		jQuery(".jqgrid-rownum").each(function(){
			jQuery(this).html(   parseInt(jQuery(this).html()) - 2 );
		}); 
		/*var getPage = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('w'),jQuery('.ui-paging-info').html().indexOf('-'));
		getPage = getPage.substring(1);
		var tworow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('-'),jQuery('.ui-paging-info').html().indexOf('of'));
		tworow =tworow.substring(1);
		var totrow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('of'));
		totrow =totrow.substring(2);
		/*alert( jQuery('.ui-paging-info').html());
		alert(getPage);
		alert(tworow );
		alert(totrow );*/
		//jQuery('.ui-paging-info').html('View '+getPage +' - '+totrow+' of '+totrow);*/
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('#hdnEField').val("true");
	enableFields('cmbEquipmentid');	
	setTimeout(function() {readOnlyFields('cmbshift');},1200);
	setTimeout(function() {readOnlyFields('cboRelatedTo');},1200);
	fillWithCurrentMonth('dtetoMonth');
	monthDiff(5,"dtefromMonth");

	enableFields('dtefromMonth');
	enableFields('dtetoMonth');		
	enableFields('chkMonthwise');
	jQuery("#chkDatewise").attr("checked",false);
	jQuery("#chkMonthwise").attr("checked",true);
		jQuery('#chkDatewise').click(function(){
	 		if(jQuery('#chkDatewise').is(':checked') == false && jQuery('#chkDatewise').is(':checked') == false)
			{
	 			jQuery("#dtefromDate").datebox('disable');
				jQuery("#dtetoDate").datebox('disable');
	 			alert("Select Datewise Checkbox");			
			}
	 			
		});
}
function validateFilterSelection(filterString){
	if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
		alert("Either Datewise or Monthwise Checkbox to be Selected");
		return false;
	}
	  if(jQuery('#chkDatewise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Select  FromDate");
			return false;
		}
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Select  ToDate");
			return false;
		}
	}
	else if(jQuery('#chkMonthwise').is(':checked') == true){
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
		//compareFromToDate(getFilterValue(filterString, "dtFromDate"),getFilterValue(filterString, "dtToDate"),"40");
			
	}
	return  true;
}
function machPhnRpt_loadComplete_afterLoad(data)
{
	 var rowid = jQuery("#machPhnRptGrd").jqGrid('getDataIDs');
	// alert(rowid.length);
	 var cm = jQuery("#machPhnRptGrd").jqGrid("getGridParam", "colModel");
	
	for(var i=1;i<rowid.length;i++)
	 {
		  
		 for(var j=0;j<cm.length;j++)
    	 {
	     	 
		  var zeroVal = jQuery("#machPhnRptGrd").jqGrid('getCell',rowid[i],'Loss');
		//  alert(jQuery('td').element.attr('title'));
		   if (jQuery('td').attr('title') == zeroval){
				jQuery(this).attr('rowspan',zeroval.length);
			   }
    	 }
	 }
}


function machPhnRpt_doubleClickGrid(id){
	 
	var iCol =    jQuery("#hiddeniCol").val();
	 
	var colVal =jQuery("#hiddencelVal").val();
 
 }
function openGrid(){
	var rptType = jQuery("#frmMachinewisePhenRpt select[id= cboReportType ]").val();
	var filtStr=jQuery.cookie("filterString");
	var firstClick = jQuery('#hdnEField').val();
//	alert(rptType+" --  "+firstClick.trim().length+" --  "+filtStr); 	
	if( firstClick.trim().length > 0){
		viewGrid("lossBreakupReport_input.pcsrpt",filtStr,rptType );
	}
	else
		viewGrid("lossBreakupReport_input.pcsrpt",'',rptType );
}		 
</script>

<!--	<table id="list" ></table>-->
<form name="frmMachinewisePhenRpt" id="frmMachinewisePhenRpt" >

<div id="wrapperRpt" style= margin-top:2px;>
<label style="margin-right:6px;">Report Type:</label>
<select id="cboReportType" class="easyui-combobox" onchange="openGrid();" style="width:200px;padding-left:10px;">
	<option value="M">MACHINEWISE</option>
	<option value="L">LOSSWISE</option>
</select>
<!--<div class="floatright"><input type="button" id="bdbtn" onclick="" style="padding-top:3px " class="easyui-button" value="View "/></div>-->
<table id="machPhnRptGrd" ></table>
<div id="pager_machPhnRpt"></div>
<input type="hidden" id="hdnEField" name="hdnEField"/>
</div>
</form>
