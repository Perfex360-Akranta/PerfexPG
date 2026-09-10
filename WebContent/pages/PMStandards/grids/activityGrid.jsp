<script type="text/javascript">
	jQuery(document).ready(function(){
		//setLoadFormCallBackFrmId('frmPmStandard');
		initialiseForm('frmPmstdbdr');
		jQuery('#submitForm').val('frmPmstdbdr');
		
		jQuery('#backbutn').show();
		jQuery(".main-cntborder ").css("height","92%");
		jQuery('#frmPmstdbdr').css('display','block');
		jQuery('#btnChkDiv').css('display','block');
		jQuery('#lblHeader').html('Activity Wise');
		//jQuery('#chkboxesAA').attr('colspan','1');
		// jQuery('#btnChkDiv').css("margin-top","0%");
		var fltrStr_actv = jQuery('#fltrStr_actv').val();
		if (fltrStr_actv != ' ' && fltrStr_actv!= '' && fltrStr_actv != null)
			fltrStr_actv  = fltrStr_actv ;
		else{
			fltrStr_actv  = '';
		}
		var url = jQuery('#hiddenUrl').val();
		
		var TradeName = url.substring(url.indexOf('TN='),url.indexOf('name'));
		TradeName = TradeName.substring(3);
		jQuery('#tradeName').css('display','block');
		jQuery('#tradeNameLbl').html("Trade Wise : "+TradeName+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Double Click On The Standard To View The Activities ");
		jQuery('#tradeNameLbl').css('float','left');
		jQuery('#tradeNameLbl').css('padding-left','10px');
		
		var frmMonth = getFilterValue(url,"fromMonth");
		
		var annualPlanUrl = url.substring(url.indexOf('&Annual'),url.indexOf('&Plan'));
		var getDataurl = url.substring(url.indexOf('cmbCellid='),url.indexOf('&cmbAssmbid='));
		//var month = getDataurl.substring(getDataurl.indexOf("th="),getDataurl.indexOf("&weekNo="));
		//month = month.substring(3,month.length);
		jQuery('#month').html(frmMonth);
		var weekNo = getDataurl.substring(getDataurl.indexOf("&weekNo="));
			weekNo = weekNo.substring(weekNo.length-1);
			jQuery('#week').html(weekNo +" <b>of</b>  ");
		if(annualPlanUrl  != " " && annualPlanUrl != "" && annualPlanUrl != null)
			jQuery("#Annual_div").css('display',"block");
		 var url = jQuery('#hiddenUrl').val(); 
		var gUrl = url.substring(url.indexOf('MouldpmActivity_input.prv'),url.indexOf('?q=2'));
		 var grdUrl ='';
		
			 if(gUrl == 'pmActivity_input.prv'  )
				 grdUrl = "pmActivity_input.prv?q=2&filterString=";
			 else
				 grdUrl = "MouldpmActivity_input.prv?q=2&filterString=";
		jQuery("#top_div").css('display',"block");
		processGridnew(grdUrl,fltrStr_actv,"activityGrid","pager_actvity","","dblclick","","activity_loadcomplete");
		//jQuery('.main-cntborder').css('height','400px');	
		jQuery('#imgPendingWrkordr').css('display','block');
		 jQuery('#btnNewstd').css('display','block');
		 jQuery('#headin').css('display','block');
		 jQuery('.legnd').css('visibility','visible');
		 jQuery('#legen').css('visibility');
		 jQuery('#lblHeader').html("Activity Type ");
		 var actType =  jQuery('#hdnnewActType').val();
			
		 //setFieldValue('cmbActivitytype',actType,'frmPmStandard');
		 
		 //disableField('frmPmStandard','cmbActivitytype');
		 jQuery("img #btnfrmPmStandardmainFunLoc").click(function() {disablControl('disable');
		 });
		 jQuery('#dispFunctionalLoc a').click(function() { disablControl('disable');});
															
	});
	function dblclick(id)
	{
			
			  var calStatus = jQuery("#activityGrid").jqGrid('getCell',id,"pmclStatus");
			  if(calStatus == 'A' )
				  alert("Pending Work Order Exist For This Activity");	
		  
		var url = jQuery('#hiddenUrl').val();
		var machId=url.substring(url.indexOf("MCH"),url.indexOf("&cmbFactid="));
		var annualPlanUrl = url.substring(url.indexOf('&Annual'),url.indexOf('&Plan'));
		var rowData = jQuery("#activityGrid").jqGrid('getRowData',id);																								
		var pmstdKeyid = rowData.keyid;
		//alert(pmstdKeyid );   
		var hdnMode =jQuery('#hdnfield').val();
		var machineId="";
		if(annualPlanUrl  != " " && annualPlanUrl != "" && annualPlanUrl != null){
			if(machId != " " && machId != "" && machId != null)
				machineId = machId;
			else
				machineId=getFieldValue('cmbMachineid');//alert(machineId);
			}
		var filterData = '&pmstdKeyid='+pmstdKeyid;
		filterData += '&pmsdMachineID='+machineId;
		filterData += '&pmcalStatus='+calStatus;
		
		//alert(hdnMode );
		  // alert(filterData);
		   //grid_row_click();
		 var url = jQuery('#hiddenUrl').val(); 
		var gUrl = url.substring(url.indexOf('MouldpmActivity_input.prv'),url.indexOf('?q=2'));
		if(annualPlanUrl  != " " && annualPlanUrl != "" && annualPlanUrl != null){
			alert(filterData);
	    	navigateToNextForm("prvnt_mntncform_modify.prv?q=2&filterData="+filterData,"PMStandard");
		}
		else{ //alert("else");
			if(gUrl == "MouldpmActivity_input.prv")
				filterData+="&relatedTo=Mould";
		 navigateToNextForm("prvnt_mntncform_modify.prv?q=2&filterData="+filterData+"&loadContentDivId="+"LoadPmStdfrm"+'&preLoadContentDivId='+"preloadDIVid4","Maintenance Standards");
		//navigateToNextForm('prvnt_mntncform_modify.prv?'+filterData,'PMStandard');
		     jQuery('#imgPendingWrkordr').css('display','none');
			 jQuery('#btnNewstd').css('display','none');
			 jQuery('#headin').css('display','none');
			 jQuery('#lblHeader').html("Activity Type ");
			 jQuery("#top_div").css('display',"none");
		}
	}
	function activity_loadcomplete(ids){
			var ids =  jQuery("#activityGrid").getDataIDs();
			//refdoctype
			 if(screen.width >= 1366){
				jQuery( "#activityGrid" ).setGridWidth(1020	);
				}
			var cm = jQuery("#activityGrid").jqGrid("getGridParam", "colModel");
			  for (var i = 0; i<ids.length; i++) 
			  {		//pmclStatus
				  var calStatus = jQuery("#activityGrid").jqGrid('getCell',ids[i],"pmclStatus");
				  
				  if(calStatus == 'A' )	
				  	jQuery("#activityGrid").setCell(ids[i], 'frequency','', { 'color':'#000','background-color':'#FCD1B3'});
				  var frmYY = jQuery("#activityGrid").getCell(i, 'refdoctype');
				  if(frmYY != "BD")
					 var a;// alert(jQuery("#activityGrid").getCell(i, 'refdoctype'));
				  else{
						
			  		  	jQuery("#activityGrid").setCell(ids[i], 'activity', '', { 'color':'#000','background-color':'#D7CAF9'});
				  }  
			  }
		}	
	function sprButton(cellvalue, options, rowObject)
	{					
		var rowId = options.rowId;
		var cm = jQuery("#activityGrid").jqGrid("getGridParam", "colModel");
		for(var i=0;i<cm.length;i++){
			if(cm[i].name =='spares' )
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
		var rowData = jQuery("#activityGrid").jqGrid('getRowData',rowId);		
		var pmstdKeyid = rowData.keyid;
		processGridnew("sprpopGrid_input.prv",'&pmstdKeyid='+pmstdKeyid,"sprpopGrid","pager_spr","","sprdblclick");
		jQuery( "#sparepopDiv" ).show();
		jQuery( "#sparepopDiv" ).dialog({
			autoOpen: false,
			modal: true,
			top  :180,
			height: 360,
			width : 660,
			title:"Spares Detail"		
		});
		
	}
	jQuery("#spr_close").click(function(){
		jQuery( "#sparepopDiv" ).dialog('close');
	});
	jQuery('#btnTabSelect').click(function()
			{	
//"GenWOGrid","GenWOPager","","genWODBLClick"
var grid="activityGrid";
            if(grid=="activityGrid"){
				var rowidactivity = jQuery("#activityGrid").jqGrid('getGridParam','selrow');
				alert('rowidactivity' +rowidactivity);
			/*	var rowidasm = jQuery("#assmGrid").jqGrid('getGridParam','selrow');
				alert(rowidasm);
				if(rowidasm !='' || rowidasm != null || rowidasm !='undefined'){
					pmAssembly_dblclick(rowidasm);
					}	
				else*/ if(rowidactivity !='' || rowidactivity !=null || rowidactivity !='undefined'){
					dblclick(rowidactivity);
					}						
				else
					alert("Select The Activity To See The Details."); 					
            }else{}
				
			 });
	/**END**/
</script>
<form id="frmActivityGrid">
<div id="Annual_div" class="notes" style="display:none;margin-left:8%;margin-top:3%">
	<label > Double click on the row to view the Activity Details   </label>
	<label style="padding-left: 10px;font-weight:bold;">PM Standard for Week </label><label id ="week"></label><label id ="month"></label>
</div>
<div class="easyui-paddingbfpx">

	     <table id="activityGrid" style="width:100%"><tr><td/></tr></table>
	     <div id="pager_actvity">
	     </div>
   
     
 </div>
 <div id="sparepopDiv" style="margin:10px;display:none;">
<table id="sprpopGrid" ><tr><td></td></tr></table>
<div id="pager-spr"></div>
<div style="float:left;margin-left:20px;margin-top:10px;"><input type="button" id="spr_close" name="spr_close" class="easyui-button" value="Cancel"/></div>
</div>
 <input type="hidden" id="fltrStr_actv" value="${requestScope.filtrstr}"/>
 
 
</form>