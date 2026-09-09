<script type="text/javascript">
jQuery(document).ready(function(){	
	//if(factId!=null)
	var rowid =jQuery('#hiddenfact').val();
	var sectId =jQuery('#hiddensect').val();
	var cellId =jQuery('#hiddencell').val();
	var mchmId =jQuery('#hiddenmach').val();
	var flid =jQuery('#hdnflid').val();
	
	var fromDate= jQuery('#fromDate').val();
	var toDate= jQuery('#toDate').val();
	var fromMonth= jQuery('#frommonth').val();
	var toMonth= jQuery('#tomonth').val();
	var hdnpcsfilterString = jQuery('#hdnpcsfilterString').val();
	var hdnfromLossStratifi = jQuery('#hdnfromLossStratifi').val();		 
	var datStr ="?q=2&flid="+flid;
	if(hdnfromLossStratifi == 'true'){
		datStr+="&cmbSectid="+sectId;
		datStr+="&cmbFactid="+rowid;
		datStr+="&cmbCellid="+cellId;
	}
	if(rowid!=null && rowid!=""&&rowid!=" "){
	if(rowid.substring(0,3)=='CMP')
		datStr+="&cmbCompid="+rowid;
	if(rowid.substring(0,3)=='LCN')
		datStr+="&cmbLocnid="+rowid; 
	if(rowid.substring(0,3)=='FCT')
		datStr+="&cmbFactid="+rowid;
	if(rowid.substring(0,3)=='LIN')
		datStr+="&cmbSectid="+sectId;
	
		
	if(rowid.substring(0,3)=='CEL')
		datStr+="&cmbCellid="+cellId;
	/*if(rowid.substring(0,3)=='MCH')
		datStr+="&cmbMchid="+rowid;
		datStr+= "&dtFromMonth="+fromMonth;
		datStr+= "&dtToMonth="+toMonth;
		datStr+= "&dtFromDate="+fromDate;
		datStr+= "&dtToDate="+toDate;*/
		if(hdnpcsfilterString.trim().length>0 )
		datStr+= "&hdnpcsfilterString="+hdnpcsfilterString;
			
	}
//	viewGrid("PcsReport_input.pcsrpt",datStr);
var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
//alert(unescape(prevDataUrl) );
//alert(datStr);
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		viewGrid("PcsReport_input.pcsrpt",datStr);
	else{
		//viewGrid(unescape(prevDataUrl),datStr);
			var tableCaption = "PCS Report";
		//alert(unescape(prevDataUrl));
		processGridnew("PcsReport_input.pcsrpt?",prevDataUrl,"pcs","pager",tableCaption,"pcs_doubleClickGrid","","pcsGrid_loadComplete");
	}
	


jQuery('#chkEntry').click(function(){		
		if(jQuery('#chkEntry').is(':checked') == true)
	{
		jQuery('input:checkbox[name=chkDetail]').attr('checked',false);
					
	}
}); 	

jQuery('#chkDetail').click(function(){		
		if(jQuery('#chkDetail').is(':checked') == true)
	{
			
				jQuery('input:checkbox[name=chkEntry]').attr('checked',false);
					
	}
}); 	

	jQuery("#bdbtncomp").click(function(rowid){
	
		var row = jQuery("#pcs").jqGrid('getGridParam','selrow');
		 if(row!=null){
			var fromDate =jQuery('#hdnfromDate').val();
			var toDate 	=jQuery('#hdntoDate').val();
			//alert(fromDate +"  ::: "+ toDate);
			var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
			var cellId = jQuery("#pcs").jqGrid('getCell',rowid,3);
			var sftId = jQuery("#pcs").jqGrid('getCell',rowid,10);
			var dates = jQuery("#pcs").jqGrid('getCell',rowid,4);
			var secId = jQuery("#pcs").jqGrid('getCell',rowid,2);
			var facId = jQuery("#pcs").jqGrid('getCell',rowid,15);
			var date = dates.substring(0, 11);
			var filterString = jQuery.cookie("filterString");
			//if(rowid != null)
			//alert(rowid);
			
					var dataStr = "";
					dataStr+= "cmbSectid="+secId+"&cmbFactid="+facId;
					dataStr+= "&shift="+sftId;
					dataStr+= "&cmbCellid="+cellId;
					dataStr+= "&date="+escape(date);
					dataStr+= "&fromDate="+fromDate;
					dataStr+= "&toDate="+toDate;
					dataStr+= "&frmPcsRpt=true";
					dataStr+= "&actiopart=PcsComplianceRpt_input.pcscomp";
					//dataStr+= "&relatedFilter=PCS"
					//jQuery("#filterUrl").val("PCSRelated") ;
					jQuery('#isCommonFilterSlideOpen').val('N');
					//var persistentData = {"DatString":filterString};
//				navigateToNextForm("pcsView_input.pcs?cellId="+cellId+"&shift="+sftId+"&date="+date+"&sectId="+secId+"&factId="+facId,"PCS Report");
				//var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"date":date,"shift":sftId};
					var url = jQuery("#pcs").jqGrid('getGridParam', 'url');
					url = url.replace("PcsReport_getData.pcsrpt","PcsReport_input.pcsrpt");
					url = escape(url); 
					/*var persistentData =  {"factId":facId,"sectId":secId,"cellId":cellId,"date":date,"shift":sftId};
					var forwardData = {"factId":facId,"sectId":secId,"cellId":cellId,"date":date,"shift":sftId};
					navigateToNextForm('PcsComplianceRpt_input.pcscomp?'+dataStr,'PCS Compliance Report',forwardData,persistentData);*/
					navigateToNextForm('PcsComplianceRpt_input.pcscomp?'+dataStr,'PCS Compliance Report',null,{"filterString":url});
		 }		
			else
				alert("Select a row to view Compliance");
	
		//	navigateToNextForm("PcsComplianceRpt_input.pcscomp?Keyid="+rowid,"PCS Compliance");

});
	/*var cellId = jQuery("#pcs").jqGrid('getCell',rowid,1);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,7);
	var date = jQuery("#pcs").jqGrid('getCell',rowid,8);
 alert(cellId);
 alert(sftId);
 alert(date);
	//if(rowid != null)
	//alert(rowid);
		navigateToNextForm("pcsView_input.pcs?cellId="+cellId+"&sftId="+sftId+"&date="+date,"PCS Report");
	//else
		//alert("Select a row to view production Details");
	*/
	
jQuery("#bdbtnpcs").click(function(rowid){
	var row = jQuery("#pcs").jqGrid('getGridParam','selrow');
 if(row!=null){
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
	var cellId = jQuery("#pcs").jqGrid('getCell',rowid,3);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,10);
	var dates = jQuery("#pcs").jqGrid('getCell',rowid,4);
	var secId = jQuery("#pcs").jqGrid('getCell',rowid,2);
	var facId = jQuery("#pcs").jqGrid('getCell',rowid,15);
	var date = dates.substring(0, 11);
	var mode = "view";
	var formLock = "lock";
	var chkdate =  jQuery('#hdnchkDate').val();
	var chkmonth = jQuery('#hdnchkMonth').val();
	var hdnfrommonth = jQuery('#hdnfrommonth').val();
	var hdntomonth = jQuery('#hdntomonth').val();
	var hdnChk ;
	var hdnfromdate =jQuery('#hdnfromDate').val();
	var hdntodate 	=jQuery('#hdntoDate').val();
	var filterString = jQuery.cookie("filterString");
	if(chkmonth.trim().length>0)
		hdnChk = 'Y';
	else
		hdnChk = 'N';
	//alert(facId);
	//if(rowid != null)
	//alert(rowid);
	
			var dataStr = "";
			dataStr+= "sectId="+secId;
			dataStr+="&factId="+facId;
			dataStr+= "&shift="+sftId;
			dataStr+= "&cellId="+cellId;
			dataStr+= "&date="+escape(date);
			dataStr+= "&mode=view";
			dataStr+= "&formLock=lock";
			if(chkmonth.trim().length>0){
				dataStr+= "&dtFromMonth="+hdnfrommonth;
				dataStr+= "&dtToMonth="+hdntomonth;
				
			}
			if(chkdate.trim().length>0){
				dataStr+= "&dtFromDate="+hdnfromdate;
				dataStr+= "&dtToDate="+hdntodate;
				
			}
				var url = jQuery("#pcs").jqGrid('getGridParam', 'url');
				url = url.replace("PcsReport_getData.pcsrpt","PcsReport_input.pcsrpt");
				url = escape(url); 
				
				//"dtFromMonth:":hdnfrommonth,"dtToMonth":hdntomonth,"dtFromDate":hdnfromdate,"dtToDate":hdntodate,"chkMonthwise":hdnChk
//		navigateToNextForm("pcsView_input.pcs?cellId="+cellId+"&shift="+sftId+"&date="+date+"&sectId="+secId+"&factId="+facId,"PCS Report");
		var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"date":date,"shift":sftId,"mode":mode,"formLock":formLock,"DatString":filterString};
		var forwardData = {"factId":facId,"sectId":secId,"cellId":cellId,"date":date,"shift":sftId,"mode":mode,"formLock":formLock};
		//alert(dataStr);
		//navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS ',null,{"filterString":url} );
		//var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode};			
		navigateToNextForm('pcsCalendarTest_input.pcs?'+dataStr,'PCS Entry',forwardData,persistentData);	
 }		
	else
		alert("Select a row to view PCS ");

});
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		
		var tableCaption = "PCS Report";
		
		processGridnew(url,filterString,"pcs","pager",tableCaption,"pcs_doubleClickGrid","","pcsGrid_loadComplete");
		return true;
	}	
	return false;
}
function pcsGrid_loadComplete(){
	jQuery("#pcsnote").css('display','block');	
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');

	var rowIds = jQuery('#pcs').jqGrid().getDataIDs();
	
	for(var i=0;i<rowIds.length;i++)
	{
		var isNoPlan = jQuery("#pcs").jqGrid('getCell',rowIds[i],16);

		if(isNoPlan=="Y"){
			//jQuery("#pcs").jqGrid('setCell',rowIds[i],"edate","",{'color':'blue'});
			jQuery("#pcs").jqGrid('setRowData', rowIds[i], false, {color:'blue'});
		}
		
		
		
	} 		
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
	//readOnlyFields('cmbFunctLocMachine');
	//setTimeout(function() {readOnlyFields('cmbshift');},1200);
	setTimeout(function() {readOnlyFields('cmbLoss');},1200);
	disableField('frmFilter','cboRelatedTo');
	disableField('frmFilter','cmbMould');
	disableField('frmFilter','cmbCircle');
	enableFields('dtefromMonth');
	enableFields('dtetoMonth');
	enableFields('chkMonthwise');
	jQuery('#chkDatewise').click(function(){
 		if(jQuery('#chkDatewise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}
function pcs_doubleClickGrid(id){ 
	
	var rowData = jQuery("#pcs").jqGrid('getRowData',id);
	//alert(rowData);
 	if(jQuery('#chkEntry').is(':checked') == true){
	 
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
	var cellId = jQuery("#pcs").jqGrid('getCell',rowid,6);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,8);
	var secId = jQuery("#pcs").jqGrid('getCell',rowid,5);
	// alert(sftId);
	var rowId = rowData.cell;
	
	var dates = rowData.edate;
	var sectId = rowData.keyId;
	var shiftId =rowData.shiftid;
	var shift =rowData.shift;
	var prlmid = rowData.prlmid;
	var date = "";
	//alert(prlmid);
	//alert(prlmid.length);
	//alert(dates);
	var date = dates.substring(0, 1);
 	 
 	 window.open("PcsReport_exlView.pcsrpt?rowId="+rowId +"&date="+date+"&sectId="+sectId+"&prlmid="+prlmid+"&cellId="+cellId+"&sftId="+sftId+"&shiftId="+shiftId+"&secId="+secId+"&shift="+shift);
	//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
	}
 	else 
 	 	alert("Select Entry Format");
	 	
 	/*else if(jQuery('#chkDetail').is(':checked') == true){
		 
		var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
		var cellId = jQuery("#pcs").jqGrid('getCell',rowid,6);
		var sftId = jQuery("#pcs").jqGrid('getCell',rowid,8);
		var secId = jQuery("#pcs").jqGrid('getCell',rowid,5);
		 
		var rowId = rowData.cell;
		var dates = rowData.date;
		var sectId = rowData.keyId;
		var shiftId =rowData.shiftid;
	 	var prlmid = rowData.prlmid;
	 	var date = dates.substring(0, 11);
	 	 
	 	 window.open("PcsReport_exldetView.pcsrpt?rowId="+rowId +"&date="+date+"&sectId="+sectId+"&shiftId="+shiftId+"&prlmid="+prlmid+"&cellId="+cellId+"&sftId="+sftId+"&secId="+secId);
		//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
		}else
		alert("Select on Entry or Detail Format");	
}*/
}
	
function validateFilterSelection(filterString){
	
	jQuery.cookie("filterString",filterString);
	
	if(filterString == "?q=2" || filterString.substring(0,4) == "?q=2")
		return true;
	
	
	
	 /*if( ! checkFilterValueExist(filterString,"cmbSectid"))
		{
			alert("Select Section");
			return false;
		}	
	if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
		alert("Either Datewise or Monthwise Checkbox to be Selected");
		return false;
	}*/
		if(jQuery('#chkDatewise').is(':checked') == true){
			jQuery('#hdnchkMonth').val('');
			jQuery('#hdnchkDate').val('Y');
			/*if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
			{
				alert("Select  FromDate");
				return false;
			}
			else{*/
				 
				jQuery('#hdnfromDate').val(jQuery('#dtefromDate').datebox('getValue'));
			jQuery('#hdntoDate').val(jQuery('#dtetoDate').datebox('getValue'));
			return  true;
		  }
		else if(jQuery('#chkMonthwise').is(':checked') == true){
			
			jQuery('#hdnchkDate').val('');
			jQuery('#hdnchkMonth').val('Y');
			/*if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
			{
				alert("Select  FromMonth");
				return false;
			}
			else if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
			{
				alert("Select  ToMonth");
				return false;
			}
			else{*/
				jQuery('#hdntomonth').val(jQuery('#dtetoMonth').datebox('getValue'));
       			jQuery('#hdnfrommonth').val(jQuery('#dtefromMonth').datebox('getValue'));
       //			return  true;
		///	}alert("MonthWise");

       			return  true;
		} 
		return  true;
}

/*
function why_doubleClickGrid(rowId){ 
	//alert(rowId);
	window.open("whywhy_excelView.why?rowId="+rowId);
	//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
		
}*/
</script>




<!--	<table id="list" ></table>-->
<form name="frmpcsreport" id="frmpcsreport" >
<div id="wrapperRpt" style= "margin-top:2%;">
<div class="" style="padding-left:2px;position: relative; margin-top: -18px"><input id="chkEntry" name="chkEntry" type="checkbox" checked ="checked" value="Y"/> <label>Entry Fromat</label> 
<!--<input id="chkDetail"  name="chkDetail" type="checkbox"  /> <label>Detailed Format   </label>-->
<input id="bdbtncomp" class="easyui-button"  type="button" value=" PCS Compliance"/>   <input type="button" id="bdbtnpcs" onclick="" class="easyui-button" value="View PCS"/>  
<!--<div class="floatright"><input type="button" id="bdbtncomp" onclick="" style="padding-bottom:3px " class="easyui-button" value="PCS Compliance "/> <input type="button" id="bdbtnpcs" onclick="" class="easyui-button" value="View PCS "/>-->
<span style="position: absolute;left:33%"><label id="pcsnote" class="lossnotes" style="font-weight: bold;display: none;">${requestScope.viewdata}</label>
</span>
</div>
<!--	 <span  style="margin-left: 2px;">  <input id="chkDetail"  name="chkDetail" type="checkbox"  /> <label>Detailed Format</label></span>-->

	
<!--<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">-->
<!--	 <input id="chkEntry" name="chkEntry" type="checkbox"  value="Y"/> <label>Entry Fromat</label>-->
<!--	 <span  style="margin-left: 2px;">  <input id="chkDetail"  name="chkDetail" type="checkbox"  /> <label>Detailed Format</label></span>-->

	
<table id="pcs" ></table>
<div id="pager"></div>
<input type="hidden" id="hdnchkDate" />
<input type="hidden" id="hdnchkMonth" />
<input type="hidden" id="hdntoDate" />
<input type="hidden" id="hdnfromDate"/>
<input type="hidden" id="hdntomonth" />
<input type="hidden" id="hdnfrommonth"/> 
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdfact}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsect}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncell}" />
<input type="hidden" id="hiddenmchm" name="hiddenmchm" value="${requestScope.hdnmchm}" />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="toDate" value="${requestScope.hdtoDate}"/>
<input type="hidden" id="fromDate" value="${requestScope.hdfrmDate}"/>
<input type="hidden" id="tomonth" value="${requestScope.hdtoMonth}"/>
<input type="hidden" id="frommonth" value="${requestScope.hdfrmMonth}"/>
<input type="hidden" id="hdnpcsfilterString" value="${requestScope.pcsfilterString}"/>
<input type="hidden" id="hdnfromLossStratifi" value="${requestScope.fromLossStratifi}"/>
<input type="hidden" id="hdnflid" value="${requestScope.hdnflid}"/>

</div>
</form>


