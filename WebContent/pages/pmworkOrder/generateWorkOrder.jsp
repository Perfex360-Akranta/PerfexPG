 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 <style>
 .leftmtsect{
 	margin-left:9%;
 }
 .leftAssm{
 margin-left:8%;
 }
  .leftplanDur{
 	padding-left:15%;
 }
  .leftcompby{
 	padding-left:3%;
 }
 </style>

<script type="text/javascript">	
jQuery.noConflict();
  jQuery(document).ready(function(){
	  initialiseForm('frmGenerateWorkOrder');
	  jQuery('#submitForm').val('frmGenerateWorkOrder'); // set the id of form to submit.indexOf('&cmbAssmbid=')--&cmbFactid=
	  var filterString = jQuery('#hdnfilter_string').val();
	
	  var assmId = filterString.substring(filterString.indexOf('ASM'),filterString.indexOf('&cmbFactid='));
	  var factId = jQuery("#frmGenerateWorkOrder input[id='factory']").val();
	  var sectionId = jQuery("#frmGenerateWorkOrder input[id='section']").val();
	  var cellId = jQuery("#frmGenerateWorkOrder input[id='cell']").val();
	  var machId =filterString.substring(10,19);
	 // alert(filterString.substring(103,111) );
	
	  var weekNO = filterString.substring('112');
	  weekNO = weekNO.replace('=',' : '); 
	  var monthstr = filterString.substring(103,111);
	  jQuery('#weekNo').val(weekNO);
	  jQuery('#txtmonthYr').val(monthstr);
	  if(machId != '' && machId != ' ' && machId != null)
		  machId =machId;
	  else 
		  machId = jQuery("#frmGenerateWorkOrder input[id='machine']").val();
	  
	  var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
	    loadFunctionalLocation("genwfunLocation","functionalLoc.brdn","genwfunLocationValues","frmGenerateWorkOrder",dataStr);
  
  	//alert();
	   processGridnew('genWO_input.mpc','?q=2filterString='+filterString ,"genretWoGrd","GenWOPager","","genWODBLClick","","genretWoGrdComplete");
	   if(screen.width <= 1024){
		   jQuery('.leftmtsect').css('margin-left','13%');
		   jQuery('.leftAssm').css('margin-left','12%');
		   jQuery('.leftplanDur').css('padding-left','23%');
		   jQuery('.leftcompby').css('padding-left','6%'); 
	  }
  });
  function chkFormatter(id, options, rowObject)
  {
  	var id = options.rowId;
    	return '<input id="chkformatter"  type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
  }

  function selectData(rowId){

  	
  	//LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack)
  	//+escape(dataStr)
  	var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
  	var planVal= '';
	 		//alert(Object.keys(rowData));.setGridParam({cellEdit:false});	
	
	 jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[7].name,"DONE",{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});
	// cm[7].name.removeClass('not-editable-cell');
	
	 planVal = jQuery("#CompWOGrid").jqGrid('getCell',rowId,cm[6].name);
	 jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[8].name,planVal,{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});
	 //cm[8].removeClass('not-editable-cell');
	 jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[9].name,"-",{'color':'#000','font-size':'11px','background-color':'#E0DBDB'});																						
	// cm[9].removeClass('not-editable-cell');
	jQuery("#CompWOGrid").setCell(rowId, "hdnchkSel","1");
  	jQuery("#selectedDat").val(rowId);
  	for(var j=7;j<10;j++){
  	  	  	  jQuery("#CompWOGrid").setGridParam({cellEdit:true});
  	}
	}

  function unselectData(rowId){
  	
  	jQuery("#CompWOGrid").setCell(rowId, "hdnchkSel"," ");
  	var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
		//alert(Object.keys(rowData));
		var j = cm.length;
		  	for(j=7;j<10;j++){
				jQuery("#CompWOGrid").setGridParam({cellEdit:false});
		  	}
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[7].name," ",{'color':'#000','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[8].name," ",{'color':'#000','font-size':'11px','background-color':''});
		jQuery("#CompWOGrid").jqGrid('setCell',rowId,cm[9].name," ",{'color':'#000','font-size':'11px','background-color':''});																					
		
  }
 function genWODBLClick(id){
	 	var rowData = jQuery("#genretWoGrd").jqGrid('getRowData',id);		
	 		//alert(Object.keys(rowData));																					
		var woId = rowData.workorderno;
		var machineId = rowData.machineId;
		var tradeId = rowData.tradeId;
		var mode = jQuery('#hdnmode').val();
		var filterString = '';
		filterString += '&cmbMchid='+machineId ;
		filterString += '&cmbAssmbid='+getFieldValue('cmbWogenAssemblyid', 'frmGenerateWorkOrder');
		filterString += '&cmbFactid='+jQuery('#hdnFctId').val();
		filterString += '&cmbSectid='+jQuery('#hdnSectId').val();
		filterString += '&cmbCellid='+jQuery('#hdnCellId').val();
		filterString += "&cmbTradeid="+tradeId;
		filterString += '&woId='+woId;
		jQuery('#txtWogenwoNo').val(woId);
		
		jQuery('#load1stGrid').css('display','none');
		jQuery('#load2ndGrid').css('display','block');
	 	processGridnew('completeWO_input.mpc','?q=filterString='+filterString,"CompWOGrid","CompWOPager","","CompWODBLClick","","CompWOGridComplete");
	 	fillComboBox("frmGenerateWorkOrder","cmbbeanWofbCompletedby","employee.commonFilter?q=2&dept=maintenance" );
	 	
 }
 function CompWODBLClick(rowId){
	 var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);		
	  	var pmstdId = rowData.txtpmstandId;
			var freqData = rowData.frequency;
			var mach =jQuery("#frmGenerateWorkOrder input[id='machine']").val();
			
			var woDetailId=rowData.txtWksmWodetailid;//alert(woDetailId);
			var pmCalId= rowData.txtpmCalendarId;
			var completedBy= rowData.completedBy;
			var jobType=rowData.txtjobtype;
			var serverTime = srvTime();
			var currentTime = new Date(serverTime);
			var day = currentTime.getDate();
			var dataStr = "";
			dataStr += "&freqData="+escape(freqData); 
			dataStr += "&machId="+mach;
			dataStr += "&woDetailId="+woDetailId;
			dataStr += "&pmCalId="+pmCalId;
			dataStr += "&startDate="+escape(day+"-"+jQuery('#txtmonthYr').val());
			dataStr +="&pmstdId="+pmstdId;
			dataStr +="&duration="+jQuery('#txtPlanDuration').val();
			dataStr +="&jobType="+jobType;
			if(completedBy != '' && completedBy != ' ' && completedBy != null)
				dataStr +="&completedBy="+completedBy;
			if(screen.width <= 1024) 
	  	   	  LoadPopUp("loaddetailsPop", "workOrderDetails_input.mpc?dataStr="+dataStr, true,"95%","72%","0%","2%", "showResult_successCallBack","Work Order Details");
			else
		      LoadPopUp("loaddetailsPop", "workOrderDetails_input.mpc?dataStr="+dataStr, true,"75%","72%","-9%","3%", "showResult_successCallBack","Work Order Details");
	  
	 }
 function CompWOGridComplete(ids){
	 var intRegex = /^\d+$/;
	 var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
	 var mode = jQuery('#hdnmode').val();
	 if(mode == 'Y')
		 disableField('frmGenerateWorkOrder','chkformatter');
	  
		var rowid = jQuery("#CompWOGrid").jqGrid('getDataIDs');
		var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
		var sumval = null;
		 for(var i=0;i<rowid.length;i++)
		 {
			 for(var j=6;j<7;j++)
	     	 {
			 planVal = jQuery("#CompWOGrid").jqGrid('getCell',rowid[i],cm[j].name);	
			 sumval += parseInt(planVal);
	     	 }
			 for(var j=7;j<10;j++)
	     	 {
				jQuery("#CompWOGrid").setGridParam({cellEdit:false});
	     	 }		
			 
		 }
		 
		 if(intRegex.test(sumval) || floatRegex.test(sumval)) {
			 sumval=sumval;
		 }
		 else
			 sumval = 0;
		 jQuery('#txtPlanDuration').val(sumval);
		 		 
 }
 /**/
 jQuery('#backBtn2nd').click(function(){
	// jQuery('#genretWoGrd').trigger("reloadGrid");  
	 jQuery('#load1stGrid').css('display','block');
	 jQuery('#load2ndGrid').css('display','none');
 });
function genretWoGrdComplete(data)
{
	var rowid = jQuery("#genretWoGrd").jqGrid('getDataIDs');
	 //alert(rowid.length);
	 jQuery('#noAct').val("No of Activities :"+rowid.length);
	//jQuery("td.jqgrow:odd").css("background", "#C4C2C2");
	
	    
}
function frmGenerateWorkOrder_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	fillComboBox("frmGenerateWorkOrder","cmbWogenCostCenter","costCenter.commonFilter");
}
function  frmGenerateWorkOrdercmbWogenCostCenter_onLoadSuccess(){
	fillComboBox("frmGenerateWorkOrder","cmbWogenTradeid","combo_pmsdTrade.prv");
	
}
function  frmGenerateWorkOrdercmbWogenTradeid_onLoadSuccess(){
	fillComboBox("frmGenerateWorkOrder","cmbWogenAssemblyid","assembly.commonFilter");
}
function  frmGenerateWorkOrdercmbWogenAssemblyid_onLoadSuccess(){
	 var filterString = jQuery('#hdnfilter_string').val();
	 var assmId = filterString.substring(filterString.indexOf('ASM'),filterString.indexOf('&cmbFactid='));
	 setFieldValue('cmbWogenAssemblyid', assmId,'frmGenerateWorkOrder');
	 disableField('frmGenerateWorkOrder','cmbWogenAssemblyid');
}
function frmGenerateWorkOrder_beforeSubmit(){
	
	var rowId = jQuery("#selectedDat").val();
	var mach = jQuery("#frmGenerateWorkOrder input[id='machine']").val();
	var dataStr = "";
	//alert('before submit' +rowId);
	var gridData = getSelectdRows("CompWOGrid","status","hdnchkSel");
	dataStr += "&gridData="+gridData;
	dataStr += "&txtmachId="+mach;
	dataStr += "&completedBycombo="+jQuery("#cmbbeanWofbCompletedby").combobox("getValue");
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var day = currentTime.getDate();
	//alert(dataStr);
	dataStr += "&dtestartDate="+escape( jQuery('#txtmonthYr').val());
	/* var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);		
	  	var pmstdId = rowData.keyId;
			var freqData = rowData.frequency;
			var mach = jQuery("#frmGenerateWorkOrder input[id='machine']").val();
			var woDetailId=rowData.wodetail;
			var pmCalId= rowData.pmCalendarId;
			var jobType=rowData.jobtype;
			var serverTime = srvTime();
			var currentTime = new Date(serverTime);
			var day = currentTime.getDate();
			var dataStr = "";
			dataStr += "&freqData="+escape(freqData); 
			dataStr += "&machId="+mach;
			dataStr += "&woDetailId="+woDetailId;
			dataStr += "&pmCalId="+pmCalId;
			dataStr += "&startDate="+escape(day+"-"+jQuery('#txtmonthYr').val());
			dataStr +="&pmstdId="+pmstdId;
			dataStr +="&duration="+jQuery('#txtPlanDuration').val();
			dataStr +="&jobType="+jobType;
			dataStr +="&directSave=directSave";
			//alert("dataStr    :"+dataStr);
		/*For Getting values from spares Grid*/
			 var rowid = jQuery("#sprpopGrid").jqGrid('getDataIDs');
		if(rowid != ' '&& rowid != '' && rowid != null){
			 var rowData = jQuery("#sprpopGrid").jqGrid('getRowData',rowid);
			 var actual = jQuery("#sprpopGrid").jqGrid('getCell',rowid.length,'actualQty');
			 //alert(actual);
			 var planQuantity = rowData.planQty;
			 dataStr+="&planQuantity="+planQuantity;
			 var sparename = rowData.sparename;
			 dataStr+="&sparename="+sparename;
			 var spareId = rowData.keyId;
			 dataStr+="&spareId="+spareId;
			 if(actual.substring(0,1) == '<')
			 {
		 		 if(jQuery('#'+rowid.length+'_actualQty').val() != '' && jQuery('#'+rowid.length+'_actualQty').val() != null && jQuery('#'+rowid.length+'_actualQty').val() != ' ') 
		           var actualQuantity = jQuery('#'+rowid.length+'_actualQty').val();
		 				dataStr+="&actualQuantity="+actualQuantity;
			 }
		}
//alert(dataStr);
return dataStr;
			
}
function frmGenerateWorkOrder_successsCallback(result){
	jQuery('#CompWOGrid').trigger("reloadGrid");
	
}
function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
{
	
	var allRows = jQuery("#CompWOGrid").jqGrid('getRowData');
	var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
	//alert(allRows.length);
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		//for(var j=0;j<cm.length;j++)
		//{
		//jsonArrO += '{';
			var row = allRows[i];
			//alert("colName  :"+Object.keys(row));
			var value = row[ckeckForSelColName];
		//	alert("value"+value);		
			if( value != null  &&  value.trim()  != "")
			{			
				if(value == '1')				
				{
					jsonArrO += '{';
					for(var colName in row) 
					{
						if(colName != 'status' && colName != 'frequency' && colName != 'spares' && colName != 'hdnchkSel' && colName != 'supplier' && colName != 'remarks')
						{
							var cellValue = parseJqGridCellValue(row[colName]);
							jsonArrO += '"'+colName +'":"' +  cellValue+'",';
							
						}		
					}
					
					
					jsonArrO += "},";
						
				}
			} 
		//}
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert(jsonArrO);
	return jsonArrO; 
} 
/* SPARE BUTTON IN GRID AND POP UP */
 function sprWOButton(cellvalue, options, rowObject)
	{					
		var rowId = options.rowId;
		var cm = jQuery("#CompWOGrid").jqGrid("getGridParam", "colModel");
		for(var i=0;i<cm.length;i++){
			if(cm[i].name =='spares')
			{
				var formatStr ='';
				if(cellvalue == "Y" )
			     formatStr  += '<input type="button" id="sprbtn" style="height:19px;" class="easyui-button" value="..." onclick="sprbtnclick(\''+rowId + '\');"/>'
				     //<img src=images/spr_but.png style=/"cursor:pointer;/" id="sprbtn" onclick="sprbtnclick(\''+rowId + '\');"/>';
				else 
					formatStr  +='<span style=/"background-color:#fff;/"></span> '; 
				return formatStr  ;
			}
			
		}
	}
	function sprbtnclick(rowId){
		var rowData = jQuery("#CompWOGrid").jqGrid('getRowData',rowId);		
		var pmstdId = rowData.txtpmstandId;
		processGridnew("sprWOdetails_input.mpc",'&pmstdId='+pmstdId,"sprpopGrid","pager_spr","","");
		jQuery( "#sparepopDiv" ).css("display","block");
		
		
	}
	jQuery("#spr_close").click(function(){
		//alert("closed");		
		jQuery( "#sparepopDiv" ).css("display","none");
	});
 /*END*/
</script>
<form name="frmGenerateWorkOrder" id="frmGenerateWorkOrder">
<div id="sparepopDiv" style="display:none;position:absolute;z-index:999;left:100;background-color: lightskyblue">
 <table id="sprpopGrid" style="width:100%"><tr><td/></tr></table>
 <div id="pager_spr"></div>
 <div style="text-align: center;margin-top:10px">
<!--	<input type="button" value="Ok" class="easyui-button" id="btnokWo" style="height:21px;width:85px">-->
	<input type="button" value="Close" class="easyui-button" id="spr_close"style="height:21px;width:85px">
</div>
</div>
<div id="wrapper" style="">

<div class="main-cntborder" style="">
<table width=100% style="padding-left:10px;">
	<tr>
		<td >
		<div id="frmGenerateWorkOrderFuntKeyIds">
						<input type="hidden" id="factory" name="cmbgenwFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbgenwSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}"  ></input>
						<input type="hidden" id="cell" name="cmbgenwCellid" value="${requestScope.bdmTlMst.bdmsCellid}" ></input>
						<input type="hidden" id="machine" name="cmbgenwMachineid" value="${requestScope.bdmTlMst.bdmsMachineid}"  ></input>
		</div>
		<div id="genwfunLocation" style="padding-left:20px;"></div>
		</td>
	</tr>
	<tr>
		<td style="padding-left:20px;">
			<div  class="easyui-paddingbfpx" style="padding-top:10px;">
			<label>Cost Center</label>
			<span  class="easyui-paddingbfpx leftmtsect" style="">
                 <label>Maint Section</label>                    
             </span>
             <span  class="easyui-paddingbfpx leftAssm" style="">
                 <label>Assembly</label>                    
             </span>
			</div> 
            <div class="easyui-paddingbfpx"> 
                <input id="cmbWogenCostCenter" name="cmbWogenCostCenter" class="easyui-combobox"  style="width:150px;" value=""  >
             <span class="easyui-paddingbfpx" style="padding-left:10px;">  
                 <input id="cmbWogenTradeid" name="cmbWogenTradeid" class="easyui-combobox"  style="width:150px;"  value="" >                    
             </span>
              <span class="easyui-paddingbfpx" style="padding-left:10px;">  
                 <input id="cmbWogenAssemblyid" name="cmbWogenAssemblyid" class="easyui-combobox"  style="width:150px;"  value="" >                    
             </span>   
			
			<span style="vertical-align:top;">
				<input id="weekNo" type="text" style="border: 1px solid black; font-size: 10px;height: 20px;width:100px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent;" disabled="disabled" value="">
				
				<input id="txtmonthYr" type="text" style="border: 1px solid black; font-size: 10px;height: 20px;width:100px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent;" disabled="disabled" value="">
				
				<input id="noAct" type="text" style="border: 1px solid black; font-size: 10px;height: 20px; color:#337DDE; font-weight: bold; text-align: center; text-transform: uppercase; cursor: default;background: transparent; width : 138px;" disabled="disabled" value="">
			</span>
		</div>
		</td>
		<tr>
		<td>
	     <div id="load1stGrid" style="float:left;">
			 <table id="genretWoGrd" style="width:100%"><tr><td/></tr></table>
				<div id="GenWOPager"></div>
		 </div>
		 <div id="preloadDiv"></div>
		</td>
	</tr>
</table>
</div>
</div>
<input type="hidden" id="hdnfilter_string" name="hdnfilter_string" value="${requestScope.filter_string}"/>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="selectedDat" name="selectedDat"/>
<input type="hidden" id="hdnmode" value="${requestScope.mode}"/>
<!--</div>
</div>
--></form>	