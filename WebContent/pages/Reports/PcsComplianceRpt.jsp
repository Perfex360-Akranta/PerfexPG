<script type="text/javascript">
jQuery(document).ready(function(){	
	
	setLoadFormCallBackFrmId("frmpcsreport");
	invokeAfterLoadFormCallBack();
	//viewGrid("PcsComplianceRpt_input.pcscomp","");
	 
	var actionPart = jQuery('#hiddenUrl').val();	
	var  prevData = unescape(jQuery('#hdnPrevDataUrl').val());
	
	var bckfrmPcsUrl = actionPart.substring(0,actionPart.indexOf('?'));
	
	var bckpcsFiltrStr =actionPart.substring(actionPart.indexOf('?'),actionPart.length) ;
	bckpcsFiltrStr = bckpcsFiltrStr.substring(1);
	jQuery('#hdnfromBackPcs').val(prevData);
	
	/*if( prevData== null || prevData.length <=0)		
		viewGrid("PcsComplianceRpt_input.pcscomp","");
	else{
		viewGrid(unescape(prevDataUrl),"");
	}*/	
	//var persistent  = {"factId":factId,"sectId":sectId,"cellId":cellId,"shift":shift,"date":date }; 
	//alert("prevData  "+prevData);
	//alert("actionPart  "+actionPart);
	//alert("hdnPrevDataUrl  "+jQuery('#hdnPrevDataUrl').val());
			


});

function frmpcsreport_afterLoadCallBack(){
	var actionPart = jQuery('#hiddenUrl').val();
	var filterString;
	if(jQuery('#hdnPrevDataUrl').val().length > 0)
	{ 
		jQuery('#hdnSetFilterValues').val('Y');
		var factId =jQuery('#hiddenfact').val();
		var sectId =jQuery('#hiddensect').val();
		var cellId =jQuery('#hiddencell').val();
		var date =jQuery('#hiddendate').val(); 
		var shift =jQuery('#hiddenshift').val();				
		var dataStr ="q=2";
		if(factId != null && factId != '' && factId != ' ')
			dataStr+="&cmbFactid="+factId;
		if(sectId!= null && sectId != '' && sectId != ' ')
			dataStr+="&cmbSectid="+sectId;
		if(cellId!= null && cellId != '' && cellId != ' ')
			dataStr+="&cmbCellid="+cellId;
		if(date!= null && date != '' && date != ' ')
		{
			dataStr+= "&dtFromDate="+escape(date);
			dataStr+= "&dtToDate="+escape(date);
		}
		if(shift!= null && shift != '' && shift != ' ')
			dataStr+= "&shift="+shift;
			dataStr+= "&chkMonthwise=0";
			dataStr+= "&chkDatewise=1";
		if(factId.length>0)
			filterString = 	dataStr;
		filterString = "&"+actionPart.substring(actionPart.indexOf("?")).substring(1);
		viewGrid(actionPart,filterString);
	}		
	else{
		toggleCommonFilter();
	}


	
		/*{
		var urlArr = actionPart.split('?')
		processGridnew(url,filterString,"pcs","pager",tableCaption,"pcs_doubleClickGrid","","pcs_loadComplete");
		}*/
	//if(jQuery('#hdnfromBackPcs').val() == "")
		
	//jQuery('#hdnPrevDataUrl').val('');	
}

	/*jQuery("#bdbtn").click(function(rowid){
	
		var rowid = jQuery("#why").jqGrid('getGridParam','selrow');
	
		navigateToNextForm("brkdown_input.brdn?BDKeyid="+rowid,"Breakdown Analysis View");

});
*/
function viewGrid(url,filterString)
{
	if(  validateFilterSelection(filterString))
	{
		if(jQuery('#hdnPrevDataUrl').val().length > 0){
			//alert("inside");
			var factId =jQuery('#hiddenfact').val();
			var sectId =jQuery('#hiddensect').val();
			var cellId =jQuery('#hiddencell').val();
			var date =jQuery('#hiddendate').val(); 
			var shift =jQuery('#hiddenshift').val();
				 //alert(factId +" -- "+sectId+" -- "+cellId+" -- "+date);
			var dataStr ="";
			
			    dataStr+="&cmbFactid="+factId;
			//if(sectId.substring(0,3)=='LIN')
				dataStr+="&cmbSectid="+sectId;
			//if(cellId.substring(0,3)=='CEL')
				dataStr+="&cmbCellid="+cellId;
			//if(date.substring(0,3)=='MCH')
				//datStr+="&cmbMchid="+rowid;
				dataStr+= "&dtFromDate="+escape(date);
				dataStr+= "&dtToDate="+escape(date);
				dataStr+= "&shift="+shift;
				dataStr+= "&chkMonthwise=0";
				dataStr+= "&chkDatewise=1";
				if(factId.length>0)
					filterString = 	dataStr;			
		}
		var tableCaption = "PCS Report";
		filterString += '&drillFlag=f';

		if(filterString != "q=2&drillFlag=f")
			processGridnew(url,filterString,"pcs","pager",tableCaption,"pcs_doubleClickGrid","","pcs_loadComplete");
		
			
				
				//processGridnew(url,filterString,"pcs","pager",tableCaption,"pcs_doubleClickGrid","","pcs_loadComplete");		
		
			
		return true;
	}

		
	return false;
}
function pcs_loadComplete(){ 

	
		jQuery("#pcsnote").css('display','block');
		hideJqGridRow("pcs","1");
		hideJqGridRow("pcs","2");
		jQuery("#pcs").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			jQuery("#hiddencelVal").val(cellcontent);
			jQuery("#hiddeniCol").val(iCol);
				 
			//pcs_doubleClickGrid(rowid,iCol,cellcontent);
		}});
		
		jQuery(".jqgrid-rownum").each(function(){
			jQuery(this).html(   parseInt(jQuery(this).html()) - 2 );
		}); 
		var getPage = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('w'),jQuery('.ui-paging-info').html().indexOf('-'));
		getPage = getPage.substring(1);
		var tworow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('-'),jQuery('.ui-paging-info').html().indexOf('of'));
		tworow =tworow.substring(1);
		var totrow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('of'));
		totrow =totrow.substring(2);
		/*alert( jQuery('.ui-paging-info').html());
		alert(getPage);
		alert(tworow );
		alert(totrow );*/
		jQuery('.ui-paging-info').html('View '+getPage +' - '+totrow+' of '+totrow);
		
}
function frmFilter_enableDisableSuccessCallBack()
{
		setTimeout(function() {readOnlyFields('chkRemoveBlank');},1200);	
		if(jQuery('#hdnPrevDataUrl').val().length > 0){
			setFilterValues();
		}	
		else
		{
			if(jQuery('#hdnSetFilterValues').val() == 'Y')
				setFilterValues();						
		}	
		jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
		jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);

		jQuery('#chkDatewise').click(function(){
	 		if(jQuery('#chkDatewise').is(':checked') == false)
			{
	 			jQuery("#dtefromDate").datebox('disable');
				jQuery("#dtetoDate").datebox('disable');
	 			alert("Select Datewise Checkbox");			
			}
	 			
		});
}



function validateFilterSelection(filterString){
	var sectId =jQuery('#hiddensect').val();
	var actionPart = jQuery('#hiddenUrl').val();	
	//alert(jQuery('#hdnfromBackPcs').val() );
	//alert(jQuery('#hdnfilterClicked').val() );
	if(filterString == "q=2")
		return true;
	
	if(actionPart.indexOf('filter')<0)
	{
		if(jQuery('#hdnfromBackPcs').val() == "" || jQuery('#hdnfilterClicked').val()== "true"){
			if(getFilterVal(filterString, "cmbSectid") == ""  && !checkFilterValueExist(filterString,"cmbCircle")){
				alert("Select Section");
				return false;
				}
			
		}
		//if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
		if((filterString.length > 0   &&  getFilterVal(filterString, "chkDatewise") == '0') && (filterString.length > 0   && getFilterVal(filterString, "chkMonthwise") == '0')){
			alert("Either Datewise or Monthwise Checkbox to be Selected");
			return false;
		}
		
		  //if(jQuery('#chkDatewise').is(':checked') == true){
		  if(getFilterVal(filterString, "chkDatewise") == '1'){
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
			compareFromToDate(getFilterVal(filterString, "dtFromDate"),getFilterVal(filterString, "dtToDate"),"40");
		}
		
		else if(getFilterVal(filterString, "chkMonthwise") == '1'){
			//else if(jQuery('#chkMonthwise').is(':checked') == true){
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
		
	
			
	}
	
	return  true;
}
function getFilterVal(filterString,identifier){
	var len= identifier.length+1;
	var substr = filterString.substring(filterString.indexOf(identifier)+len);
	var endval = substr.indexOf("&");
	var val;
		if (endval=="-1")
	 		val= substr.substring(0);
		else
			val= substr.substring(0,endval); 
	return val;
}
function pcs_loadComplete_afterLoad(data)
{
	 var rowid = jQuery("#pcs").jqGrid('getDataIDs');
	// alert(rowid.length);
	 var cm = jQuery("#pcs").jqGrid("getGridParam", "colModel");
	 for(var i=2;i<rowid.length;i++)
	 {
		  
		 for(var j=7;j<cm.length;j++)
     	 {
	     	 
		  var zeroVal = jQuery("#pcs").jqGrid('getCell',rowid[i],cm[j].name);	
			 // alert("zeroVal"+		zeroVal); 
			  if(zeroVal != "" && zeroVal != " " && zeroVal != null)
			  {
				//alert(zeroVal+" zeroVal "+cm[j].name );
				  if(zeroVal =='1'||zeroVal =='4')	{				  
			  		jQuery("#pcs").jqGrid('setCell',rowid[i],cm[j].name,"&#10003",{'color':'#9d99f2','font-weight':'bold','font-size':'15px'});
			  		
			  		/*var selId = cm[j].name;
			  		jQuery("table.ui-jqgrid-htable > thead > tr.jqgridheaderrow1 > th.ui-state-default").not(":contains('"+cm[j].name+"')").css('color','red');*/
				  }
				  else if(zeroVal =='2')
				  		jQuery("#pcs").jqGrid('setCell',rowid[i],cm[j].name," ",{'color':'#9d99f2','font-weight':'bold','font-size':'15px','background-color':'#999999'});
				  else if(zeroVal =='3')
				  		jQuery("#pcs").jqGrid('setCell',rowid[i],cm[j].name," ",{'color':'#9d99f2','font-weight':'bold','font-size':'15px','background-color':'#9d99f2'});
				  else					
					  jQuery("#pcs").jqGrid('setCell',rowid[i],cm[j].name," ",{'color':'blue','font-weight':'bold','font-size':'15px'});
			  }
	    }
	 }
}


function pcs_doubleClickGrid(id){
	 
	var iCol =    jQuery("#hiddeniCol").val();
	 
	var colVal =jQuery("#hiddencelVal").val();
	if (iCol < 12) return ; 
	/*	 var rowid = jQuery("#pcs").jqGrid('getDataIDs');
		// alert(rowid.length);
		 var cm = jQuery("#pcs").jqGrid("getGridParam", "colModel");
		 for(var i=2;i<rowid.length;i++)
		{
			  
			 for(var j=12;j<cm.length;j++)
	     	{
	  	 var zeroVal = jQuery("#pcs").jqGrid('getCell',rowid[i],cm[j].name);	
		 // alert("zeroVal"+		zeroVal); 
	if(zeroVal != "" && zeroVal != " " && zeroVal != null)
	{*/

	if(colVal != "" && colVal != " " && colVal != null) {
		var date = jQuery("#pcs").jqGrid('getCell',1,iCol);
		jQuery('#hdnDate').val(date);		
		var shiftCode = jQuery("#pcs").jqGrid('getCell',2,iCol);
	   
		var facId = jQuery("#pcs").jqGrid('getCell',id,1);
		//alert(date+" -- "+shiftCode +" -- "+facId);
		var frmPcsRpt = jQuery('#hdnfrmPcsRpt').val();
		
		if(frmPcsRpt.trim().length<=0)
			processAjaxCalls('getShiftKeyid.pcscomp','?q=2&factId='+facId+'&shiftCode='+shiftCode,"getShiftSuccess");
		 
	}
	//}
	//	}}
	//else 
		//alert("Click on Tick");
 }
		 
function getShiftSuccess(result) {

	
	//	var date = dates.substring(0, 11);					 

	//var row = jQuery("#pcs").jqGrid('getGridParam','selrow');	
 	//if(row!=null){
		var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
		var cellId = jQuery("#pcs").jqGrid('getCell',rowid,3);		
		var secId = jQuery("#pcs").jqGrid('getCell',rowid,2);
		var facId = jQuery("#pcs").jqGrid('getCell',rowid,1);
		var date = jQuery('#hdnDate').val();
		var shift = result.shift;
		var formLock="lock";
		
	/*	var dataStr = "";
				dataStr+="factId="+facId;
				dataStr+= "&sectId="+secId;				
				dataStr+= "&shift="+shift;
				dataStr+= "&cellId="+cellId;
				dataStr+= "&date="+date;
				dataStr+= "&mode=view";
				dataStr+= "&formLock=lock";
		*/
			
			//navigateToNextForm("pcsView_input.pcs?cellId="+cellId+"&shift="+shift+"&date="+date+"&sectId="+secId+"&factId="+facId,"PCS Report");
		//alert("factId"+facId+"sectId"+secId+"cellId"+cellId+"shift"+shift+"date"+date+"mode"+"view");
			var url = jQuery("#pcs").jqGrid('getGridParam', 'url');
			url = url.replace("PcsComplianceRpt_getData.pcscomp","PcsComplianceRpt_input.pcscomp");
			url = escape(url);
			var fo = {"filterString":url};
			var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":shift,"date":date,"mode":"view","formLock":formLock}; 
			//navigateToNextForm("pcsView_input.pcs?cellId="+cellId+"&shift="+shift+"&date="+date+"&sectId="+secId+"&factId="+facId,"PCS Report",null,{"filterString":url});
			//navigateToNextForm("pcsView_input.pcs"+dataStr ,"PCS Entry-Time Based (View Mode)",null,{"filterString":url});
					
			navigateToNextForm("pcsViewTest_input.pcs?"+"&mode=view" ,"PCS Entry-Time Based (View Mode)", persistentData,{"filterString":url});
		//window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View", "height=200, width=200");{"filterString":url}
	// }
}
</script>

<!--	<table id="list" ></table>-->
<form name="frmpcsreport" id="frmpcsreport" >

<div id="wrapperRpt" style= margin-top:2px;>

<!--<div class="floatright"><input type="button" id="bdbtn" onclick="" style="padding-top:3px " class="easyui-button" value="View "/></div>-->
<table  style="margin-top:2px;padding-left:0px;">
 <tr><td style="border: solid 1px #c1c1c1;border-right:none;padding:3px;">
 <label id="pcsnote" class="lossnotes" style="font-weight: bold;width:250px;padding-left:20px;padding-right:20px;display: none;">${requestScope.pcstick} </label>
</td> 
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;padding:3px;">
 <span class="wo-priority" style="background-color:#9d99f2"></span></td>
 <td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;padding:3px;"><label style="color: ;padding-left:10px;font-weight: bold">Holiday</label></td>
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;padding:3px;"> <span class="wo-priority" style="background-color:#999999"></span></td>
<td style="border: solid 1px #c1c1c1;border-left:none;padding:3px;"><label style="color: ;padding-left:10px;font-weight: bold"> No Plan</label> </td> 
	</tr>
</table>
<table id="pcs" ></table>
<div id="pager"></div>

<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnDate" name="hdnDate"/>
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdnfactId}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncellId}" />
<input type="hidden" id="hiddendate" name="hiddedate" value="${requestScope.hdndateId}" />
<input type="hidden" id="hiddenshift" name="hiddeshift" value="${requestScope.hdnshftId}" />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnPcsDataUrl" name="hdnPcsDataUrl" />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddencelVal"  />
<input type="hidden" id="hdnfromdate" name="hdnfromdate" value="${requestScope.hdnfromdate}" />
<input type="hidden" id="hdntodate" name="hdntodate" value="${requestScope.hdntodate}" />
<input type="hidden" id="hdnfromBackPcs" name="hdnfromBackPcs"  />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
<input type="hidden" id="hdnfrmPcsRpt" name="hdnfrmPcsRpt"  value="${requestScope.frmPcsRpt}"/>
<input type="hidden" id="hdnfilterClicked" name="hdnfilterClicked"  />
</div>
</form>
