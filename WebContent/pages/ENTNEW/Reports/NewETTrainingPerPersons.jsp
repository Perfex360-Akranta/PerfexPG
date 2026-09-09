<script type="text/javascript">
jQuery(document).ready(function(){	

	
	var actionPart = jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmKaizenPerPerson");
	invokeAfterLoadFormCallBack();
	//viewGrid(actionPart,"?q=2");
	
	//setLoadFormCallBackFrmId("frmKaizenPerPerson");
	
	jQuery('#btnGraph').click(function(){
		fnShowKaizenGraph("LINE");		
	});

	/* jQuery('#btnBarGraph').click(function(){
	/*	var rowid = jQuery("#kznPerPersonGrid").jqGrid('getGridParam','selrow');
		var rowData = jQuery("#kznPerPersonGrid").jqGrid('getRowData',rowid);				
		var url = "chartKaizenPerPerson.kaz?q&graphType=COLUMN";// + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
		showGraphData(url);
	*/
		//fnShowKaizenGraph("COLUMN");

	//});*/
}); 
function frmKaizenPerPerson_afterLoadCallBack(){
	toggleCommonFilter();
	}
	function fnShowKaizenGraph(graphType) {
		var rowid = jQuery("#kznPerPersonGrid").jqGrid('getGridParam','selrow');
		var flid = "";
		var FirstLevel = 'Y';
		if (rowid=='' || rowid==' ' || rowid=='null' || rowid==null  ) { 
			rowid = 1;
			FirstLevel = 'Y';
		}
		var rowData = jQuery("#kznPerPersonGrid").jqGrid('getRowData',rowid);
		flid = rowData.KEYFIELD1; // 2 to 1
		var flidArr = flid.split("#");
		var type=jQuery('#hdntype').val();
		flid =flidArr[0];
		FirstLevel = 'Y';
		//if (graphType=="LINE")
			var url = "chartNewETHrsPerPerson.newentRpt?q&graphType="+graphType+"&flid="+flid+"&FirstLevel="+FirstLevel+"&type="+type;
		//else
			//var url = "chartKaizenPerPerson.kaz?q&graphType="+graphType+"&flid="+flid+"&FirstLevel="+FirstLevel;
		
		showGraphData(url);
	}

function kznPerPersonGrid_loadComplete()
{	
	setDrillDownHeader("CH1-0","kznPerPersonGrid","KEYFIELD2");
	//setTotalRowCss('kznPerPersonGrid');
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
		var type=jQuery('#hdntype').val();
		filterString += '&drillFlag=f&firstClick=Y';
		filterString += '&type='+type;
		var tableCaption = "Kaizen Per Person Per Month";
		processGridnew("NewTrnhrsPerPersonPerMonth_input.newentRpt",filterString,"kznPerPersonGrid","pager",tableCaption,"doubleClickGrid","","kznPerPersonGrid_loadComplete");		
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

function kznPerPersonGrid_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("kznPerPersonGrid","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			
					
function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("kznPerPersonGrid","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
</script>
<form id="frmKaizenPerPerson">
<div id="wrapperRpt"style= margin-top:13px;>
<table>
    <tr>
          
          
          <td>
	          <div style="padding-left: 0px;">
		        <input id="btnGraph" class="easyui-button"  type="button" value="Line Graph"/>  
		        
<!-- 	          <span style="padding-left: 10px;"> -->
<!-- 		        <input id="btnBarGraph" class="easyui-button"  type="button" value="Bar Graph"/>   -->
<!-- 		      </span> -->
		      
		      </div>
          </td>
          <td>
          <div style="float: left;">
          	<label class="notes" style="font-weight: bold;">Double Click  on Company/Location/SBU/PBU/DMT/JH to Drilldown</label>
          </div>
          </td>

          
    </tr>
    
    <tr>
    <td colspan="2">
    <div class="clear"></div>
	<table id="kznPerPersonGrid" ><tr><td></td></tr></table>
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
<input type="hidden" id="hdntype" value="${requestScope.type}"  />
</div>
</form>


