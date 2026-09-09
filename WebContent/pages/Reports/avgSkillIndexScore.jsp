<script type="text/javascript">
jQuery(document).ready(function(){	

	//alert(1);
	var actionPart = jQuery('#hiddenUrl').val();	
	viewGrid(actionPart,"?q=2");
//alert(1234 +"  "+actionPart);
	//setLoadFormCallBackFrmId("frmskillIndexScoreGraph");
	
	jQuery('#btnGraph').click(function(){
		fnShowskillIndexGraph("LINE");		
	});
	
	jQuery('#btnBarGraph').click(function()
			{
		
		fnShowskillIndexGraph("COLUMN");
	});
	
	/*Function designed for trade wise Report*/ 
	jQuery('#btnTradeWiseReport').click(function(){
		var rowid = jQuery("#skillIndexGrid").jqGrid('getGridParam','selrow');
	  if (rowid=='' || rowid==' ' || rowid=='null' || rowid==null  ) { 
			alert("Select a valid Cell");
			return false;
		}
		var flid = "";
		var rowData = jQuery("#skillIndexGrid").jqGrid('getRowData',rowid);
		flid = rowData.KEYID2;
		var flidArr = flid.split("#");
		flid =flidArr[0];
		var hdr =jQuery('#hdnheader').val();
		//alert("hdr :" +hdr);
		var etpmCode = ''; 
		if( hdr == 1){ 
			etpmCode ='P';
		}else if( hdr == 2){
			etpmCode ='M';
		}else if (hdr == 3){
			etpmCode ='S';
		}else if(hdr == 4){
			etpmCode ='T';
		}else if(hdr == 5){
			etpmCode ='';
		}
		/*else{
			     alert("Select Trade type cell and click on Trade Wise Report ") ;
			     return ;
		}*/
		//alert("etpmCode : " +etpmCode);
		var url="TradeWiseAverageSkillIndexRpt_input.skillIndex";
		var dataStr= "?flid="+flid + "&etpmCode="+etpmCode;
		//alert("dataStr: " +dataStr);
		navigateToNextForm(url+dataStr, "Trade Wise Report");
	});
	toggleCommonFilter();
});
/* function frmskillIndexScoreGraph_afterLoadCallBack(){
	toggleCommonFilter();
	}  */

	function fnShowskillIndexGraph(graphType) {
		var rowid = jQuery("#skillIndexGrid").jqGrid('getGridParam','selrow');
		//alert(rowid+"rowid");
		var flid = "";
		var FirstLevel = 'Y';
		if (rowid=='' || rowid==' ' || rowid=='null' || rowid==null  ) { 
			rowid = 1;
			FirstLevel = 'Y';
		}
		var rowData = jQuery("#skillIndexGrid").jqGrid('getRowData',rowid);
		flid = rowData.KEYID2;
		//alert(rowid+"flid");
		var flidArr = flid.split("#");
		flid =flidArr[0];
		FirstLevel = 'Y';
		var type=jQuery('#hdnType').val();
		
		var ds = "?q&graphType=" + graphType + 
        "&flid=" + flid +
        "&FirstLevel=" + FirstLevel +
        "&type=" + type;
		
		if (flid.startsWith("CEL")) {
	        // Do something for CSL
	        console.log("FLID starts with CEL");
	        var cellId = flid;
	        ds += "&cellId=" + cellId;   
	        // your logic hereâ¦
	    } else if (flid.startsWith("BCM")) {
	        // Do something for BCM
	        console.log("FLID starts with BCM");
	        var empId = flid;
	        ds += "&empId=" + empId;
	        // your logic hereâ¦
	    } 
		
		
		//alert(" ::  Checking Type in Jsp :: "+type);
		//var ds = "?q&graphType="+graphType+"&flid="+flid+"&FirstLevel="+FirstLevel+"&type="+type;
		var url = "chartSkillIndexScoreGraph.skillIndex"+ds;
		
		showGraphData(url);
	}

function skillIndexGrid_loadComplete()
{	
	setDrillDownHeader("CH1-0","skillIndexGrid","KEYID2");
	//setTotalRowCss('skillIndexGrid');
	jQuery("#skillIndexGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {			
			var celindx=cellidx-4;
			//alert(celindx);
			 jQuery('#hdnheader').val(celindx);
		}
	});
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
/* function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{		
		filterString += '&drillFlag=Y&firstClick=N';
		var tableCaption = " JH Self Audit Score ";
		var type=jQuery('#hdnType').val();
		filterString += "&type="+type;
		//alert(filterString +"   ....");
		processGridnew("AverageSkillIndexScore_input.skillIndex",filterString,"skillIndexGrid","pager",tableCaption,"doubleClickGrid","","skillIndexGrid_loadComplete");		
		return true;
	}
	
	return false;	
} */

function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		/* if(filterString == "?q=2")
		{	
			alert("Select JH");
			return false;
		} */
		
		filterString += '&drillFlag=Y&firstClick=N';
		var tableCaption = " JH Self Audit Score ";
		var type=jQuery('#hdnType').val();
		filterString += "&type="+type;
		//alert(filterString +"   ....");
		processGridnew("AverageSkillIndexScore_input.skillIndex",filterString,"skillIndexGrid","pager",tableCaption,"doubleClickGrid","","skillIndexGrid_loadComplete");
		var sectionId=jQuery('#section').val();//Change
		if(sectionId = null || sectionId.length == 0)//Change
		{
			alert("Select JH or Dmt");
			return false;
		}//Change
		return true;
	}
	
	return false;	
}

/* function validateFilterSelection(filterString){
	
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
} */

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


	var fromMonthVal = jQuery('#dtefromMonth').val();
	var toMonthVal   = jQuery('#dtetoMonth').val();
	var year = fromMonthVal.split("-")[1];
	//var flid=jQuery('#hdnflid').val();//Change
	var flid = jQuery("#flid").val();
	//var flid = jQuery("#frmskillIndexScoreGraph input[id='flid']").val();
	//alert("flid"+flid)
	//alert("fromMonthVal "+fromMonthVal);
	//alert("toMonthVal "+toMonthVal);
	var selectedYear = fromMonthVal.split("-")[1]; // 2026
    var currentYear = new Date().getFullYear();
    var sectionId=jQuery('#section').val();//Change
    var cellId=jQuery('#cell').val();//Change
    
    
	 //console.log("sectionid "+sectionId);
	 //console.log("cellId "+cellId);
    
    if (parseInt(selectedYear) === currentYear && sectionId) 
	 {
		//alert("eneterd 2");
		var dataStr = "flid="+flid+"&date="+selectedYear;
		if(sectionId && !cellId)
		{
			dataStr+="&sectionId="+sectionId;
		}
		if(sectionId && cellId)
		{
			dataStr+="&cellId="+cellId+"&sectionId="+sectionId;
		}
		window.savedFilterString = filterString; 
		processAjaxCalls("callProcedureAverageSkillIndex.skillIndex",dataStr,'averageSkillIndexProcedure_SuccessCallBack',' ','text');
		return false;
	  }
	
	return true;
}

function averageSkillIndexProcedure_SuccessCallBack(result)
{
	if (result != null && result.trim() === "PROCEDURE-SUCESSS") {
        

        // Same logic as viewGrid — load grid with saved filterString
        var filterString = window.savedFilterString;
        filterString += '&drillFlag=Y&firstClick=N';
        var type = jQuery('#hdnType').val();
        filterString += "&type=" + type;

        processGridnew(
            "AverageSkillIndexScore_input.skillIndex",
            filterString,
            "skillIndexGrid",
            "pager",
            "JH Self Audit Score",
            "doubleClickGrid",
            "",
            "skillIndexGrid_loadComplete"
        );
        
        toggleCommonFilter(); 
    } else {
        console.log("Procedure failed: " + result);
        alert("Sync failed. Please try again.");
    }
	
	
	
}



function skillIndexGrid_onProcessGridBack(){
	var rowId=jQuery('#skillIndexGrid').jqGrid("getGridParam", 'selrow');
	
	var celValue = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'KEYID2');
	var keyfieldData=jQuery("#hdnFnlnKeyid").val(celValue);
	//alert(keyfieldData);
	setDrillProcesGridBack("skillIndexGrid","KEYID2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			
					
/* function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	var rowId=jQuery('#skillIndexGrid').jqGrid('getGridParam', 'selrow');
	alert(rowId +" rowId "+id);
	/* var colName=jQuery('#skillIndexGrid').jqGrid("getGridParam", 'colModel');
	var cln=colName[iCol];
	alert(cln +"lk");
	alert(colName +"colname"); */
/*	var celValue = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'KEYID2'); //Company3
	//alert(celValue +" keyfieldData");
	var filt=celValue.substr(0,3);
	if(filt=="CEL"){
		filt="EMP";
	}
	 if(filt=="SEC"){
		filt="CELL";
	}
	/* else{
		filt="SECT";
	} */
	
/*	var flid = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'Flid3'); //Company3
	
	var totalsr = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'SCORE9'); //Company3
	/* if(totalsr==" "||totalsr==null){
		totalsr="Z";
	} */
 //    alert(celValue+"  12  "+totalsr);
	//var cellId = celValue.substr(0,);
	//alert(celValue +" keyfieldData"+flid.substr(0,12));
	//keyfieldData=setDrillDoubleClick("skillIndexGrid","KEYID2",id,cellId);
	/*var url="AverageSkillIndexScore_input.skillIndex";
	var filterData="?q=2&flid="+flid.substr(0,12);
	
	var filterString = '?q=2&drillFlag=f&firstClick=Y';
	var tableCaption = " JH Self Audit Score ";
	var type=jQuery('#hdnType').val();
	filterString += "&type="+type+"&flid="+flid.substr(0,12)+"&Keyid="+celValue;//+"&totalsr="+totalsr;//+"&filt="+filt;
	alert(filterString +"  filterString")
	processGridnew(url,filterString,"skillIndexGrid","pager","","doubleClickGrid",tableCaption,"skillIndexGrid_loadComplete");	
//alert(123);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
} */

/* function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	var rowId=jQuery('#skillIndexGrid').jqGrid("getGridParam", 'selrow');
	 //alert(rowId +" rowId ");
	var flid = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'Flid3');
	var celValue = jQuery('#skillIndexGrid').jqGrid('getCell', rowId, 'KEYID2');
	//var cellId = celValue.substr(0,);
	//alert(celValue +" keyfieldData");
	//keyfieldData=setDrillDoubleClick("skillIndexGrid","KEYFIELD2",id,cellId);
	var url="AverageSkillIndexScore_input.skillIndex";
	var filterData="?q=2&flid="+flid.substr(0,12);
	//alert (filterData);
	var filterString = '?q=2&drillFlag=f&firstClick=N&flid1='+flid.substr(0,12);
	var tableCaption = " JH Self Audit Score ";
	filterString += getAllFilterValues()  ;
    //alert(filterString +"   filterStringfilterString");
	var type=jQuery('#hdnType').val();
	var fromMnth=jQuery('#hdnfromMonth').val();
	var toMnth=jQuery('#hdntoMonth').val();
	//alert(fromMnth +" ... "+flid.substr(0,12) );
	filterString += "&type="+type+"&flid="+flid.substr(0,12)+"&Keyid="+celValue+"&toMnth="+toMnth+"&fromMnth="+fromMnth;
	processGridnew(url,filterString,"skillIndexGrid","pager","","doubleClickGrid",tableCaption,"skillIndexGrid_loadComplete");	

	jQuery("#hdnFnlnKeyid").val(keyfieldData);
} */

/* function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	var rowId=jQuery('#skillIndexGrid').jqGrid("getGridParam", 'selrow');
	 //alert(rowId +" rowId ");
	var flid = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'Flid3');
	var celValue = jQuery('#skillIndexGrid').jqGrid('getCell', rowId, 'KEYID2');
	
	var celValuedmt = jQuery('#skillIndexGrid').jqGrid('getCell', rowId, 'CODEFIELD4');//Swetha
	//var cellId = celValue.substr(0,);
	//alert(celValuedmt +" celValuedmt");
	var match = celValuedmt.match(/\d{4}-h[12]/i);//Swetha
	var halfYearValue = match ? match[0].toUpperCase() : "";//Swetha

	console.log(halfYearValue);//Swetha
	// var halfYearValue = celValuedmt.split('-').slice(2).join('-');

	console.log(halfYearValue); // 2026-H1
	//keyfieldData=setDrillDoubleClick("skillIndexGrid","KEYFIELD2",id,cellId);
	var url="AverageSkillIndexScore_input.skillIndex";
	var filterData="?q=2&flid="+flid.substr(0,12);
	//alert (filterData);
	var filterString = '?q=2&drillFlag=f&firstClick=N&flid1='+flid.substr(0,12);
	var tableCaption = " JH Self Audit Score ";
	filterString += getAllFilterValues()  ;
    //alert(filterString +"   filterStringfilterString");
	var type=jQuery('#hdnType').val();
	var fromMnth=jQuery('#hdnfromMonth').val();
	var toMnth=jQuery('#hdntoMonth').val();
	//alert(fromMnth +" ... "+flid.substr(0,12) );
	
	filterString += "&type="+type+"&flid="+flid.substr(0,12)+"&Keyid="+celValue+"&toMnth="+toMnth+"&fromMnth="+fromMnth;
	if (halfYearValue != null && halfYearValue) {//Swetha
	    filterString += "&halfyear=" + halfYearValue;//Swetha
	}//Swetha
	processGridnew(url,filterString,"skillIndexGrid","pager","","doubleClickGrid",tableCaption,"skillIndexGrid_loadComplete");	

	jQuery("#hdnFnlnKeyid").val(keyfieldData);
} */
function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	var rowId=jQuery('#skillIndexGrid').jqGrid("getGridParam", 'selrow');
	 //alert(rowId +" rowId ");
	var flid = jQuery('#skillIndexGrid').jqGrid ('getCell', rowId, 'Flid3');
	var celValue = jQuery('#skillIndexGrid').jqGrid('getCell', rowId, 'KEYID2');
	//alert("entered");
	var celValuedmt = jQuery('#skillIndexGrid').jqGrid('getCell', rowId, 'CODEFIELD4');//CHANGE
	var halfYearemp = jQuery('#hdnHalfYear').val();//CHANGE
	//alert("entered 2"+celValuedmt);
	//alert("entered 3"+halfYearemp);
	//alert("entered 3"+halfYearemp.length);
	//var cellId = celValue.substr(0,);
	//alert(celValuedmt +" celValuedmt");
	
	var match = celValuedmt.match(/\d{4}-h[12]/i);//CHANGE
	//alert(match +" match");
	if(halfYearemp.length == 0)//CHANGE
	{
		halfYearemp = match ? match[0].toUpperCase() : "";//CHANGE
	}//CHANGE
	//var halfYearValue = match ? match[0].toUpperCase() : "";
	
	//console.log(halfYearValue);
	// var halfYearValue = celValuedmt.split('-').slice(2).join('-');

	//console.log(halfYearValue); // 2026-H1
	jQuery('#hdnHalfYear').val(halfYearemp);//CHANGE
	//keyfieldData=setDrillDoubleClick("skillIndexGrid","KEYFIELD2",id,cellId);
	var url="AverageSkillIndexScore_input.skillIndex";
	var filterData="?q=2&flid="+flid.substr(0,12);
	//alert (filterData);
	var filterString = '?q=2&drillFlag=f&firstClick=N&flid1='+flid.substr(0,12);
	var tableCaption = " JH Self Audit Score ";
	filterString += getAllFilterValues()  ;
    //alert(filterString +"   filterStringfilterString");
	var type=jQuery('#hdnType').val();
	var fromMnth=jQuery('#hdnfromMonth').val();
	var toMnth=jQuery('#hdntoMonth').val();
	//var halfYearemp = jQuery('#hdnHalfYear').val();
	//alert(halfYearemp +" halfYearemp");
	//alert(fromMnth +" ... "+flid.substr(0,12) );
	
	filterString += "&type="+type+"&flid="+flid.substr(0,12)+"&Keyid="+celValue+"&toMnth="+toMnth+"&fromMnth="+fromMnth;
	if (halfYearemp != null && halfYearemp) {//CHANGE
	    filterString += "&halfyear=" + halfYearemp;
	}//CHANGE
	/* if (halfYearValue != null && halfYearValue) {
	    filterString += "&halfyear=" + halfYearValue;
	} */
	processGridnew(url,filterString,"skillIndexGrid","pager","","doubleClickGrid",tableCaption,"skillIndexGrid_loadComplete");	

	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
</script>
<form id="frmskillIndexScoreGraph">
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
		      
		        <span style="padding-left: 10px;">
		       	 <input id="btnTradeWiseReport" class="easyui-button"  type="button" value="Trade Wise Report"/>  
		       </span>
		      
		      </div>
          </td>

          
    </tr>
    
    <tr>
    <td colspan="2">
    <div class="clear"></div>
	<table id="skillIndexGrid" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="divGraphContainer" ></div>	
	</td>
     </tr>
</table>
		
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
<input type="hidden" id="hdnfromMonth" name="hdnfromMonth" value="${requestScope.fromMonth}"  >
<input type="hidden" id="hdntoMonth" name="hdntoMonth" value="${requestScope.toMonth}"  >
<input type="hidden" id="hdnHalfYear" name="hdnHalfYear" value="${requestScope.halfYear}"  >

<input type="hidden" id="hdnfromdate" value="${requestScope.hdnfromdate}"  />
<input type="hidden" id="hdntodate" value="${requestScope.hdntodate}"  />
<input type="hidden" id="hdnFnlnKeyid" />
<input type="hidden" id="hdnType" name="hdnType" value="${requestScope.type}"/>
<input type="hidden" id="hdnheader"/>
</div>
</form>


