<script type="text/javascript">

jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId('frmEdtreport');
	invokeAfterLoadFormCallBack();
	///toggleCommonFilter();		
	var action = jQuery('#hiddenUrl').val();
	//viewGrid("PrdLogRpt_input.prdlogMon","");	 
/*	var rowid =jQuery('#hiddenIds').val();
	 
	var datStr ="";
	if(rowid!=null && rowid!=""&&rowid!=" "){
		 
	if(rowid.substring(0,3)=='FCT')
		datStr+="cmbFactid="+rowid;
	if(rowid.substring(0,3)=='LIN')
		datStr+="&cmbSectid="+rowid;
	if(rowid.substring(0,3)=='CEL')
		datStr+="&cmbCellid="+rowid;
	if(rowid.substring(0,3)=='MCH')
		datStr+="&cmbMchid="+rowid;
	}	*/
	//datStr+= '&';
	//var actionPart = jQuery('#hiddenUrl').val();?q=1&firstClick=Y		
//	viewGrid("PrdLogRpt_input.prdlogMon","");
	
	
	
	 
});

function frmEdtreport_afterLoadCallBack(){ 
	//var frmlossStrati =jQuery('#hiddenIds').val();
	//if(frmlossStrati.trim.length()>0)
	 
			//toggleCommonFilter(true);
		
		var actionPart = jQuery('#hiddenUrl').val();
		
		if(actionPart.indexOf('filter')<0)
		{
			 toggleCommonFilter();		
			 var frmlossStrati =jQuery('#hiddenIds').val();
			 if(frmlossStrati!= ""){	
				setTimeout(function() {jQuery( "#view" ).trigger('click');	},2500);
		}}
		else
		{
			jQuery('#hdnSetFilterValues').val('Y');
			var compId=jQuery("#hiddenCompId").val();
			var datStr="?q=2";
			datStr+="&cmbCompid="+jQuery("#hiddenCompId").val();
			datStr+="&cmbLocnid="+jQuery("#hiddenLocnId").val();
			datStr+="&cmbMchid="+jQuery("#hiddenMchId").val();
			datStr+="&cmbFactid="+jQuery("#hiddenFactId").val();
			datStr+="&cmbSectid="+jQuery("#hiddenSectId").val();
			datStr+="&cmbCellid="+jQuery("#hiddenCellId").val();
			datStr+="&dtFromMonth="+jQuery("#hdnfromMonth").val();
			datStr+="&dtToMonth="+jQuery("#hdntoMonth").val();
			
			viewGrid("ProductionLoss_input.prdloss",datStr);
		}			
}
function frmFilter_enableDisableSuccessCallBack(){ 

	var rowid =jQuery('#hiddenIds').val();
	 
	if(rowid!= null && rowid!="" && rowid!=" " ){
		 
		var datStr ="";
		if(rowid.substring(0,3)=='FCT')
			datStr+="&factId="+rowid;
		if(rowid.substring(0,3)=='LIN')
			datStr+="&sectId="+rowid;
		if(rowid.substring(0,3)=='CEL')
			datStr+="&cellId="+rowid;
		if(rowid.substring(0,3)=='MCH')
			datStr+="&machId="+rowid;
		
		loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter",datStr);
		
	 }	
	 
	jQuery("#dtefromDate").datebox('disable');
	jQuery("#dtetoDate").datebox('disable');
	
	
		//fillWithCurrentMonth("dtefromMonth");
	
}
function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		var rowid =jQuery('#hiddenIds').val();
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		if(removeBlank.trim().length<=0)
			removeBlank = 'Y';

		
		/*if(rowid!= null && rowid!="" && rowid!=" " ){
			 
			var datStr ="";
			if(rowid.substring(0,3)=='FCT')
				datStr+="&cmbFactid="+rowid;
			if(rowid.substring(0,3)=='LIN')
				datStr+="&cmbSectid="+rowid;
			if(rowid.substring(0,3)=='CEL')
				datStr+="&cmbCellid="+rowid;
			if(rowid.substring(0,3)=='MCH')
				datStr+="&cmbMachid="+rowid;

			filterString +=datStr; 
		}*/
		var tableCaption = "MonthWise Report";
		filterString += '&drillFlag=f';
		filterString +="&chkRemoveBlank=Y";
		processGridnew(url,filterString,"LogMonGrid","pager",tableCaption,"doubleClickGrid","","LogMonGrid_loadComplete");
		
		
		return true;
	}
	return false;	
}

function LogMonGrid_loadComplete(){
	fillColor();
	hideShowBack(false);
	/*var rowIds = jQuery("#LogMonGrid").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#LogMonGrid").jqGrid('getCell', rowIds[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);
				
			}
		}*/
	//jQuery("#pcsnote").css('display','block');	
	 
	jQuery("tr.jqgridheaderrow1").children().bind("dblclick", function(){
		
		var cell = jQuery(this);
		var index = cell.parent('tr').children().index(cell);
		index = parseInt(index) +3;
		 
		var colm = jQuery("#LogMonGrid").jqGrid ('getGridParam', 'colModel');
		selId = colm[index].name;
	 
	 
		var filterData ="?";
	 
		filterData += '&parentId='+ selId+'&drillFlag=f';
		var url = jQuery('#hiddenUrl').val();
		 
		processGridnew(url,filterData,"LogMonGrid","pager",'',"","","LogMonGrid_loadComplete");	
	});


	    jQuery("tr.jqgridheaderrow1").children().bind("click", function(){
	  
		var cell = jQuery(this);
		var index = cell.parent('tr').children().index(cell);
		index = parseInt(index) +3;
		
		var colm = jQuery("#LogMonGrid").jqGrid ('getGridParam', 'colModel');
		selId = colm[index].name;
	 
		jQuery("#hiddenkey").val(selId);
	});	

}
	 
function doubleClickGrid(id)
{
	 
	    var cell = jQuery(this);
        var index = cell.parent('tr').children().index(cell);
		index = parseInt(index) +3;
		
		var colm = jQuery("#LogMonGrid").jqGrid ('getGridParam', 'colNames');
		//alert("colmn+"+colm);
		selId = colm[index];
		//alert("dee::"+ selId);
		jQuery('#LogMonGrid').jqGrid ('getCell', 1, selId);
		
		var colm = jQuery("#LogMonGrid").jqGrid ('getGridParam', 'colModel',id);
		var selId = jQuery("#hiddenkey").val(selId);
   		try{ 
			fillDrilFunctlocCombo(selId);
		}catch(Exception	){
		}
		if(selId.substr(0,3) != 'MCH'){
		var filterData ="?";
		
		filterData += '&parentId='+ selId+'&drillFlag=f';
		var url = jQuery('#hiddenUrl').val();
		
		processGridnew(url,filterData,"LogMonGrid","pager",'',"doubleClickGrid","","LogMonGrid_loadComplete");	
	}
	
	if(selId.substr(0,3) == 'MCH'){
		 
	}
 	
} 

function LogMonGrid_onProcessGridBack(){

	
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#LogMonGrid").getDataIDs();

	var parentId =  jQuery("#LogMonGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString = 'drillFlag=b';		
	processGridnew(url,dataString,"LogMonGrid","pager",'',"doubleClickGrid","","LogMonGrid_loadComplete");			
}
function validateFilterSelection(filterString){

	var actionPart = jQuery('#hiddenUrl').val();
	
	if(actionPart.indexOf('filter')<0)
	{
	
		if( filterString.length > 0   && ! checkFilterValueExist(filterString, "cmbMchid") )
		{
			alert("Select  Equipment");
			return false;
		} 
		if((filterString.length > 0   && jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false)){
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
	}}
	return  true;
}		
/*
function checkForZeroes(tableId,selId,colNo)
{
	alert("srfg");
	var Col = colNo+1;
	
	while(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != null)
	{		
		if(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != '0')
			return true;
		Col++;
	}
		return false;
		
}*/
function fillColor()
{
	 

var allRowsId = jQuery('#LogMonGrid').jqGrid('getDataIDs');

	/*for(var i=0;i<allRowsId.length;i++)
		{
		var ID=allRowsId[i];
		var nextTr = jQuery("#LogMonGrid tr[id="+ID+"]").next('tr').attr("id");
		var nextRowData = jQuery("#LogMonGrid").jqGrid('getRowData',nextTr);
		var color=parseInt(nextRowData.color).toString(16);
	
		if( nextRowData.note == "N" || nextRowData.note == "S" ||  nextRowData.note == "M"){
			while(nextRowData.note== "N" || nextRowData.note == "S" || nextRowData.note == "M")
			{				
				jQuery("#LogMonGrid").jqGrid('setCell',nextTr,"key","",{'background-color':'#'+color});
				 nextTr = jQuery("#LogMonGrid tr[id="+nextTr+"]").next('tr').attr("id");				
				 nextRowData = jQuery("#LogMonGrid").jqGrid('getRowData',nextTr);					
			}	}
		}*/
	//var allRowsId = jQuery('#LossGrid').jqGrid('getDataIDs');
	var ID=	"";
	var flag= true;
		for(var i=0;i<allRowsId.length;)
			{
			ID=allRowsId[i];
			//var nextTr = jQuery("#LogMonGrid tr[id="+ID+"]").next('tr').attr("id");
			var nextRowData = jQuery("#LogMonGrid").jqGrid('getRowData',ID);
			var color=parseInt(nextRowData.color).toString(16);
			
			if( nextRowData.note == "M" ){
				flag = true;
				while( flag )
				{				
					jQuery("#LogMonGrid").jqGrid('setCell',ID,"key","",{'background-color':'#'+color});
					// nextTr = jQuery("#LogMonGrid tr[id="+nextTr+"]").next('tr').attr("id");	
					 ID= allRowsId[++i]			;
					 nextRowData = jQuery("#LogMonGrid").jqGrid('getRowData',ID);
					 if( nextRowData.note.trim() != "S")
						 flag =false;	
						 	
				}	
			}
			color=parseInt(nextRowData.color).toString(16);
			jQuery("#LogMonGrid").jqGrid('setCell',ID,"key","",{'background-color':'#'+color});
			i++;
		}
		
}

</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->

<form name="frmEdtreport" id="frmEdtreport" >
<div id="wrapperRpt">
<!--<div class="floatright" style="padding-right:20px;"><input id="btnGraph" class="easyui-button"  type="button" value="Graph"/> -->
<!--  <input type="button" id="bdbtn" onclick="" class="easyui-button" value=" "/> </div>--> 

<!--<div class="clear"></div>-->
<label id="pcsnote" class="notes" style="font-weight: bold;padding-left:20px;padding-right:20px;display: none;">${requestScope.pcsmon}</label>
<table id="LogMonGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenIds" name="hiddenIds" value="${requestScope.hiddenIds}" />
<input type="hidden" id="hiddenkey"  />
<input type="hidden" id="hiddenIds" name="hdnFilter" value="${requestScope.filterFrmLossStr}" />
<input type="hidden" id="hiddenCompId" value="${requestScope.hiddenCompId}"  />
<input type="hidden" id="hiddenLocnId" value="${requestScope.hiddenLocnId}"  />
<input type="hidden" id="hiddenFactId" value="${requestScope.hiddenFactId}"  />
<input type="hidden" id="hiddenSectId" value="${requestScope.hiddenSectId}"  />
<input type="hidden" id="hiddenCellId" value="${requestScope.hiddenCellId}"  />
<input type="hidden" id="hiddenMchId" value="${requestScope.hiddenMchId}"  />
<input type="hidden" id="hdnfromMonth" value="${requestScope.hiddenFromMonth}"  />
<input type="hidden" id="hdntoMonth" value="${requestScope.hiddenToMonth}"  />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
 </div>
</form>
	