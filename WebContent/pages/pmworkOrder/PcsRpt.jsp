<script type="text/javascript">
jQuery(document).ready(function(){	
	//if(factId!=null)
	var rowid =jQuery('#hiddenfact').val();
	var sectId =jQuery('#hiddensect').val();
	var cellId =jQuery('#hiddencell').val();
	var mchmId =jQuery('#hiddenmach').val();
	var flid =jQuery('#hdnflid').val();
	
	//alert("flid"+flid+"sectionId"+sectId+"....... "+cellId);

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

//uery('.chkDetail').prop('checked',true)
/*	
jQuery("#btnView").click(function(rowid){


/*	var rowData = jQuery("#pcs").jqGrid("getRowData",id);
	alert("........"+rowData);
	if((jQuery("#chkDetail").is(':checked') == true) || (jQuery("#chkEntry").is(':checked')== true)){
	alert("LLLLLLLL"+rowData);
 	
	 alert(".............");
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
	var cellId = jQuery("#pcs").jqGrid('getCell',rowid,8);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,12);
	var secId = jQuery("#pcs").jqGrid('getCell',rowid,7);
	var datess =  jQuery("#pcs").jqGrid('getCell',rowid,10);
	alert("CELLID       "+cellId+"          SECID    "+secId+"          sftid     "+sftId);
	alert("--------"+datess);
	var rowId = rowData.cell;
	
	var dates = rowData.edate;
	var sectId = rowData.secId;
	var shiftId =rowData.sftId;//shiftid;
	var shift =  rowData.shift;
	var prlmid = rowData.prlmid;
	var date = "";
	alert("......."+dates);
	//alert(prlmid.length);
	
	var date = dates.substring(0, 1);
 	 
 	 window.open("PcsReport_exlView.pcsrpt?rowId="+rowId +"&date="+date+"&sectId="+secId+"&prlmid="+prlmid+"&cellId="+cellId+"&sftId="+sftId+"&shiftId="+shiftId+"&secId="+secId+"&shift="+shift);
	//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
	}
	else {
			alert("Select Entry Format Or Detail Format");
		}
	
	var row = jQuery("#pcs").jqGrid('getGridParam','selrow');
 if(row!=null){
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
	var cellId = jQuery("#pcs").jqGrid('getCell',rowid,8);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,12);
	var dates = jQuery("#pcs").jqGrid('getCell',rowid,10);
	var secId = jQuery("#pcs").jqGrid('getCell',rowid,7);
	var facId = jQuery("#pcs").jqGrid('getCell',rowid,6);

	alert("cellId  "+cellId+"sftId....... "+sftId+"dates / "+dates+"secId........"+secId   +"facid..... "+facId);
	var date = dates.substring(0, 11);
	var mode = "view";

    

	alert("oooooooo"+rowid);
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
				url = url.replace("PcsReport_getData.pcsrpt","PcsReport_exlView.pcsrpt");
				url = escape(url); 
				
				//"dtFromMonth:":hdnfrommonth,"dtToMonth":hdntomonth,"dtFromDate":hdnfromdate,"dtToDate":hdntodate,"chkMonthwise":hdnChk
				//navigateToNextForm("pcsView_input.pcs?cellId="+cellId+"&shift="+sftId+"&date="+date+"&sectId="+secId+"&factId="+facId,"PCS Report");
		var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":sftId,"date":date,"mode":mode,"formLock":formLock,"DatString":filterString};
		var forwardData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":sftId,"date":date,"mode":mode,"formLock":formLock};
		//alert(dataStr);
		//navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS ',null,{"filterString":url} );
		//var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode};			
		window.open('PcsReport_exldetView.pcsrpt?'+dataStr,'PCS Entry',forwardData,persistentData);	
	 	// window.open("PcsReport_exlView.pcsrpt?rowId="+rowId +"&date="+date+"&sectId="+secId+"&prlmid="+prlmid+"&cellId="+cellId+"&sftId="+sftId+"&shiftId="+shiftId+"&secId="+secId+"&shift="+shift);
			
			 
		 }		
	else
		alert("Select a row to view PCS ");
 	
});
*/
	
jQuery("#bdbtnpcs").click(function(rowid){
	var row = jQuery("#pcs").jqGrid('getGridParam','selrow');
 if(row!=null){
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
	var cellId = jQuery("#pcs").jqGrid('getCell',rowid,8);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,12);
	var dates = jQuery("#pcs").jqGrid('getCell',rowid,10);
	var secId = jQuery("#pcs").jqGrid('getCell',rowid,5);
	var facId = jQuery("#pcs").jqGrid('getCell',rowid,6);

	//alert("cellId  "+cellId+"sftId....... "+sftId+"dates / "+dates+"secId........"+secId   +"facid..... "+facId);
	var date = dates.substring(0, 11);
	var mode = "view";

//	alert("oooooooo"+rowid);
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
		var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":sftId,"date":date,"mode":mode,"formLock":formLock,"DatString":filterString};
		var forwardData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":sftId,"date":date,"mode":mode,"formLock":formLock};
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
	//alert("LLLLLLLL"+rowData);
 	if(jQuery('#chkEntry').is(':checked') == true){
	// alert(".............");
	var rowid = jQuery('#pcs').jqGrid('getGridParam','selrow');
		var cellId = jQuery('#pcs').jqGrid('getCell',rowid,8);
		var sftId = jQuery('#pcs').jqGrid('getCell',rowid,12);
		var secId = jQuery('#pcs').jqGrid('getCell',rowid,7);
		var datess =  jQuery('#pcs').jqGrid('getCell',rowid,10);
		var keyId =  jQuery('#pcs').jqGrid('getCell',rowid,16);	

		// navigateToNextForm("pcsLoss_input.pcs?q=2&keyId="+keyId+"&mode=View");
		}
	/*
	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
	var cellId = jQuery("#pcs").jqGrid('getCell',rowid,8);
	var sftId = jQuery("#pcs").jqGrid('getCell',rowid,12);
	var secId = jQuery("#pcs").jqGrid('getCell',rowid,7);
	var datess =  jQuery("#pcs").jqGrid('getCell',rowid,10);
	//alert("Rowid   "+rowid+"   CELLID       "+cellId+"          SECID    "+secId+"          sftid     "+sftId);
	//alert("--------"+datess);
	var rowId = rowData.CELLID;
	var cellId=rowData.CELLID;
	var dates = rowData.ENTRYDATE;
	var sectId = rowData.SECTID;
	var shiftId =rowData.SHIFTID;//shiftid;
	var shift =  rowData.SHIFT;
	var prlmid = rowData.PCSMSTID;
	var date = "";
	//alert("......."+rowId);
	//alert(prlmid.length);
	//http://localhost:8080/perfexitc/PcsReport_exlView.pcsrpt?rowId=undefined&date=04-MAY-2015&sectId=SEC0000009&prlmid=PRL0000074&cellId=CEL0000452&sftId=SFT006&shiftId=SFT006&secId=SEC0000009&shift=1
	var date = dates.substring(0, 11);
 	 
 	 window.open("PcsReport_exlView.pcsrpt?rowId="+rowId +"&date="+date+"&sectId="+secId+"&prlmid="+prlmid+"&cellId="+cellId+"&sftId="+sftId+"&shiftId="+shiftId+"&secId="+secId+"&shift="+shift);
	//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
	}
 /*	else 
 	 	alert("Select Entry Format");
	 */	

	/* commented for temporary purpose by kiran 13_aug_15
 	else if(jQuery('#chkDetail').is(':checked') == true){
		 
		var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
		var cellId = jQuery("#pcs").jqGrid('getCell',rowid,8);
		var sftId = jQuery("#pcs").jqGrid('getCell',rowid,12);
		var secId = jQuery("#pcs").jqGrid('getCell',rowid,7);
		var datess =  jQuery("#pcs").jqGrid('getCell',rowid,10);
		//alert("CELLID       "+cellId+"          SECID    "+secId+"          sftid     "+sftId);
		//alert("--------"+datess);
		/*
		var rowId=rowData.rowId; 
		alert("000000000000      "+ rowId);
		var cellId = rowData.cell;
		var dates = rowData.date;
		var sectId = rowData.keyId;
		var shiftId =rowData.shiftid;
	 	var prlmid = rowData.prlmid;
	 	var date = dates.substring(0, 1);
	 	*/

	 	/* continued
	 	var rowId = rowData.CELLID;
		var cellId=rowData.CELLID;
		var dates = rowData.ENTRYDATE;
		var sectId = rowData.SECTID;
		var shiftId =rowData.SHIFTID;//shiftid;
		var shift =  rowData.SHIFT;
		var prlmid = rowData.PCSMSTID;
		var date = "";
		var date = dates.substring(0, 11);
	 	 window.open("PcsReport_exldetView.pcsrpt?rowId="+rowId +"&date="+date+"&sectId="+sectId+"&shiftId="+shiftId+"&prlmid="+prlmid+"&cellId="+cellId+"&shift="+shift+"&secId="+secId);
		//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");
		}else
		alert("Select an Entry or Detail Format");	*/
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
<!-- Commented for temporary purpose
<div  class="" style="padding-left:2px;position: relative; margin-top: -18px"><label>Entry Format</label><input style="margin-left:10px;margin-top: 18px; position: relative;" id="chkEntry" name="chkEntry"  type="checkbox"  value="Y"/> <label  style="margin-left:30px;position: relative;">Detailed Fromat</label><input style="margin-left:10px;margin-top: 18px; position: relative;" id="chkDetail" name="chkDetail"  type="checkbox"  /> 

      
   <input style="margin-left:50px; position: relative;" type="button" id="btnView" onclick="" class="easyui-button" value="View"/>  
   
   
   <input style="margin-left:50px; position: relative;" type="button" id="bdbtnpcs" onclick="" class="easyui-button" value="View PCS"/>  
 
  
 </div>
 -->
 <div class=""style="padding-left:2px;position: relative; margin-top: 10px">
<!--<div class="floatright"><input type="button" id="bdbtncomp" onclick="" style="padding-bottom:3px " class="easyui-button" value="PCS Compliance "/> <input type="button" id="bdbtnpcs" onclick="" class="easyui-button" value="View PCS "/>-->

<!--  commented for tmp purpose by kiran
<span style="position: relativ;padding-left:5px"><label id="pcsnote" class="lossnotes" style="font-weight: bold;display: none;">${requestScope.viewdata}</label>
</span> -->
</div>

<!--	 <span  style="margin-left: 2px;">  <input id="chkDetail"  name="chkDetail" type="checkbox"  /> <label>Detailed Format</label></span>-->

	
<!--<div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">-->
<!--	 <input id="chkEntry" name="chkEntry" type="checkbox"  value="Y"/> <label>Entry Fromat</label>-->
<!--	 <span  style="margin-left: 2px;">  <input id="chkDetail"  name="chkDetail" type="checkbox"  /> <label>Detailed Format</label></span>-->

	
<table id="pcs" Width="100%"></table>
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


