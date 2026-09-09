<script type="text/javascript">

jQuery(document).ready(function(){

//var url = jQuery('#hiddenUrl').val();
//var dataString = "?q=1&appPillar=G";
//processGridnew(url,dataString,"grdConfig","grdConfigPager","Configuration","","","");
if(screen.width <= 1024)
{
	jQuery("#tabConfig").css("width","850%");
	//jQuery("#tabConfig").css("width","850%");
	
	}
else if(screen.width > 1280 && screen.width <= 1366)
	{
	jQuery("#tabConfig").css("width","1150%");
	//jQuery("#tabConfig").css("width","850%");
	
	}
 viewGrid("config_input.conFig","");
	forTab();
});


function forTab(tab){
	jQuery("#tabConfig").tabs({ onSelect:function(title){  
		 
		// processGridnew("PcsSummary_input.pcsry?","","pcs","Pager"," ");
		//processGridnew("PcsSummary_input.pcsry?","&forTab="+forTab,"pcs","Pager"," ");
		if(title == "General"){
			
			 var  appPillar= "G";
			 
			 jQuery("#gridName").val("grdConfig");
			 var row = jQuery("#grdConfig").jqGrid('getDataIDs');
			if(row.length<=0)
			{processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdConfig","grdConfigPager","Configuration","","","pcs_loadComplete","","");
			

		 	jQuery("#grdConfig").setGridParam({url:'config_getData.conFig' });}
		 
	 	 }	
		
		else if(title == "Abnormalties" ){
			var  appPillar= "A";
			 jQuery("#gridName").val("grdAbnormalties");
			 var row = jQuery("#grdAbnormalties").jqGrid('getDataIDs');
			 if(row.length<=0)
				{
				 processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdAbnormalties","grdConfigpager1","Configuration","","","pcs_loadComplete","","");
	 			jQuery("#grdAbnormalties").setGridParam({url:'config_getData.conFig' });}
		}
		else if(title == "Planned Maintenace" ){
			var  appPillar= "P";
			jQuery("#gridName").val("grdPlanned_Maintenace");
			var row = jQuery("#grdPlanned_Maintenace").jqGrid('getDataIDs');
			 if(row.length<=0)
				{
				 processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdPlanned_Maintenace","grdConfigpager2","Configuration","","","pcs_loadComplete","","");
	 			jQuery("#grdPlanned_Maintenace").setGridParam({url:'config_getData.conFig' });}
		}
		else if(title == "Improvements" ){
			var  appPillar= "I";
			jQuery("#gridName").val("grdImprovements");
			var row = jQuery("#grdImprovements").jqGrid('getDataIDs');
			 if(row.length<=0)
				{
				 processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdImprovements","grdConfigpager3","Configuration","","","pcs_loadComplete","","");
	 			jQuery("#grdImprovements").setGridParam({url:'config_getData.conFig' });}
		}
		else if(title == "Education and Training" ){
			var  appPillar= "E";
			jQuery("#gridName").val("grdEdu_and_Training");
			var row = jQuery("#grdEdu_and_Training").jqGrid('getDataIDs');
			 if(row.length<=0)
				{
				 processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdEdu_and_Training","grdConfigpager5","Configuration","","","pcs_loadComplete");
				 
	 			jQuery("#grdEdu_and_Training").setGridParam({url:'config_getData.conFig' });}
		}
		else if(title == "Quality Maintenance" ){
			var  appPillar= "Q";
			jQuery("#gridName").val("grdQuality_Maintenance");
			var row = jQuery("#grdQuality_Maintenance").jqGrid('getDataIDs');
			 if(row.length<=0)
				{
				 processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdQuality_Maintenance","grdConfigpager4","Configuration","","","pcs_loadComplete");
	 			jQuery("#grdQuality_Maintenance").setGridParam({url:'config_getData.conFig' });}
		}
		else if(title == "BreakDown" ){
			var  appPillar= "B";
			jQuery("#gridName").val("grdBreakDown");
			var row = jQuery("#grdBreakDown").jqGrid('getDataIDs');
			 if(row.length<=0)
				{
				 processGridnew("config_input.conFig?","&appPillar="+appPillar,"grdBreakDown","grdConfigpager6","Configuration","","","pcs_loadComplete");
	 			jQuery("#grdBreakDown").setGridParam({url:'config_getData.conFig' });}
		}
		
		}});}
function frmConfiguration_afterLoadCallBack(){
	toggleCommonFilter();	
} 
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "General";
		
		processGridnew(url,filterString,"grdConfig","grdConfigpager",tableCaption,"","","pcs_loadComplete","","");
	
		
	}
	
}  
function pcs_loadComplete(){
	var gridName=jQuery("#gridName").val();
	//alert(gridName);
	setBGColor(gridName);
}
	 
	//alert("hideJqGridRow");
	//alert("hideJqGridRow" +hideJqGridRow);
		// var row = jQuery("#pcs").jqGrid('getDataIDs');
		// alert(row.length);
		//hideJqGridRow("pcs","pcsghead_0");
		//setTotalRowColorForGroupby("pcs");
		

//	alert(setTotalRowCss);
	
	

function validateFilterSelection(filterString){
		return true;
}		

function button_config(id, options, rowObject)
{
	var rowId = options.rowId;
	//alert(rowId);
	return '<input type="button" id="change" class="easyui-button" style="height:15px;" value="..." onclick="PopupLoad(\''+rowId + '\');"/>';
	}
	function PopupLoad(rowId)
	{	var gridName=jQuery("#gridName").val();
	
		var rowData = jQuery("#"+gridName).jqGrid('getRowData',rowId);
		
		var filterString="?q=2";
		var keyId=rowData.KEYID;
		var tMode=rowData.TRANSACTIONMODE;
		var code=rowData.CODE;
		var setColor=rowData.SELECTCOLOR;
		var fromDate=rowData.FROMDATE;
		var settingValue=rowData.SETTINGVALUE;
		//alert(settingValue);
		//alert(code);
		var uom=rowData.UOM;
		//alert(uom);
		filterString+="&keyId="+keyId;
		filterString+="&tMode="+tMode;
		filterString+="&code="+escape(code);
		filterString+="&setColor="+setColor;
		//alert(setColor);
		if(uom==null || uom==" " ||uom=='' )
			{
			}
		else
			{
		filterString+="&uom="+uom;
		//alert(uom);
		}
		if(fromDate==null || fromDate==" " || fromDate=='')
			{
			}
		else
			{
			filterString+="&fromDate="+fromDate;
			}
		if(setColor=="C")
			{


			//var setValue=jQuery("#bgColor").val();
			//alert('setting:'+setValue);
			filterString+="&setColorfrom="+settingValue;
			//alert('null');
			}
		else
			{
			filterString+="&settingValue="+settingValue;
			}
		//alert(filterString);
		//LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar)
		LoadPopUp("divConfiguration","configChange_input.conFig"+filterString,true,"335px","177px","27px","20%", "multiSelectOk_Callback",code);
		}
	function divConfiguration_afterClose()
	{
		
		var fromSave=jQuery("#relodstate").val();
		//alert(fromSave);
		
		var gridName=jQuery("#gridName").val();
		jQuery("#"+gridName).trigger("reloadGrid");}
		
	function setBGColor(grdId){
		// alert('in load');
		 var row = jQuery("#"+grdId).jqGrid('getDataIDs');
		//alert(row);
		 var cm = jQuery("#"+grdId).jqGrid("getGridParam", "colModel");
		 
		 for(var i=0;i<row.length;i++)
		 {
			 var rowData = jQuery("#"+grdId).jqGrid('getRowData',row[i]);
			 var setColor=rowData.SELECTCOLOR;
				//alert(setColor);
				
			 if(setColor=="C")
				 {
				 //alert(cm[6].name);
				var setValue=rowData.SETTINGVALUE;
				//jQuery("#bgColor").val(setValue);
				//alert(row[i]+'set;'+setValue);
				//var colorName=toHexString(setValue);
				//alert(colorName);
				 jQuery("#"+grdId).jqGrid('setCell',row[i],cm[6].name,setValue,{'color':'#'+setValue,'font-weight':'bold','font-size':'15px','background-color':'#'+setValue});
				 }
		 } 
	}
</script>
<form name="frmConfiguration" id="frmConfiguration" >
	<div id="wrapperRpt">
<!--<div style="height:300px;margin-bottom:2%">	-->
	 <div  id="tabConfig" class="easyui-tabs"  style="width:1100%;height:415%;padding-left: 5px;border-bottom:solid 1px #8DB2E3;">
			 <div title="General" style="padding-right:  0px;">
				
					 <div id="" style="margin-left:-2px;margin-top:15px">
						<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
						<div class="clear"></div>
						<table id="grdConfig" ></table>
						<div id="grdConfigPager"></div>
						<input type="hidden" id="hiddenStr" value="sdsd" />
				
			 <div title="Abnormalties" style="padding-right:  0px;">
				
		 			 <div id="" style="margin-left:-2px;margin-top:15px">
						<div class="clear"></div>
						<table id="grdAbnormalties" ></table>
						<div id="grdConfigpager1"></div> 
					 </div>
			 </div>
		</div>
</div>
<div title="Planned Maintenace" style="padding-right:  0px;">
				
		 			 <div id="" style="margin-left:-2px;margin-top:15px">
						<div class="clear"></div>
						<table id="grdPlanned_Maintenace" ></table>
						<div id="grdConfigpager2"></div> 
					 </div>
			 </div>
		
<div title="Improvements" style="padding-right:  0px;">
				
		 			 <div id="" style="margin-left:-2px;margin-top:15px">
						<div class="clear"></div>
						<table id="grdImprovements" ></table>
						<div id="grdConfigpager3"></div> 
					 </div>
			 </div>
	<div title="Quality Maintenance" style="padding-right:  0px;">
				
		 			 <div id="" style="margin-left:-2px;margin-top:15px">
						<div class="clear"></div>
						<table id="grdQuality_Maintenance" ></table>
						<div id="grdConfigpager4"></div> 
					 </div>
			 </div>
<div title="Education and Training" style="padding-right:  0px;">
				
		 			 <div id="" style="margin-left:-2px;margin-top:15px">
						<div class="clear"></div>
						<table id="grdEdu_and_Training" ></table>
						<div id="grdConfigpager5"></div> 
					 </div>
			 </div>

<div title="BreakDown" style="padding-right:  0px;">
				
		 			 <div id="" style="margin-left:-2px;margin-top:15px">
						<div class="clear"></div>
						<table id="grdBreakDown" ></table>
						<div id="grdConfigpager6"></div> 
					 </div>
			 </div>
</div>	 
			  </div>
		<input type="hidden" id="gridName" name="gridName" value="">
		<input type="hidden" id="setColor" name="setColor" value="${requestScope.setColor}">
		<input type="hidden" id="relodstate" name="relodstate" value="${requestScope.fromSave}"/>
		<input type="hidden" id="bgColor" name="bgColor" value=""/>
</form>