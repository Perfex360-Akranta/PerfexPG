<script type="text/javascript">
	
	jQuery(document).ready(function(){

		initialiseForm('frmInternalRejectionCalendar');
		jQuery('#submitForm').val('frmInternalRejectionCalendar');

		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		fillComboBox("frmInternalRejectionCalendar","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );
		fillComboBox("frmInternalRejectionCalendar","cmbMachid","machineCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );		
				
		formatDateBox('dteEntryDate','dd-MMM-yyyy');

//  		 jQuery('#dteEntryDate').datebox({  
//	 			 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
//  		 });  

   		var dat = jQuery('#dteEntryDate').datebox('getValue');
  		if (dat=="")
  			fillWithCurrentDate('dteEntryDate');
  			//fillWithCurrentMonth('dteEntryDate');
  		//else
  		//	jQuery('#dteEntryDate').datebox('setValue',dat.substring(3));

		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();		
		var sectionId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		var cellId = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		
		var machId = jQuery("#frmInternalRejectionCalendar input[id='machine']").val();							
		var flid = jQuery("#frmInternalRejectionCalendar input[id='flid']").val();
  	    var dataStr = "&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					

  	  	//if (cellId!=undefined && cellId!=null && cellId.length>0 )
	    loadFunctionalLocation("InternalRejectionfunLocation","functionalLoc_Calendar.ire","InternalRejectionfunLocationValues","frmInternalRejectionCalendar",dataStr);

		var sectionId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		//alert(entryDate);
		//if (!( sectionId == ""  || sectionId == ""))		
		processAjaxCalls('getDetailTableName.ire','?q=2&sectId='+sectionId+"&entryDate="+entryDate,"detailTableRecallSuccess");
		//alert(jQuery('#hdnMode').val());
		processAjaxCalls('setFormActionMode.ire','?q=2&mode='+jQuery('#hdnMode').val(),"formModeSuccess");		
		 		
	});
	function formModeSuccess(){
		
	 }
	function frmInternalRejectionCalendar_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		var cellId = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();;
		//alert(cellId);
	//	jQuery('#cmbMachid').combobox('clear');	
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		var sectId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		reloadCombo("frmInternalRejectionCalendar","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId+"&sectId="+sectId );
		var cellId = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		var flid = jQuery("#frmInternalRejectionCalendar input[id='flid']").val();
		var dataStr="?q=2&pcsEnabled=Y&factId="+factId+"&cellId="+cellId+"&flid="+flid;
		reloadCombo("frmInternalRejectionCalendar","cmbMachid","machineCombo.commonFilter"+dataStr );

		  if (keyIds.cellId!="null")
			  	setFieldValue('cmbCellid',keyIds.cellId);
			  
		  if (keyIds.machId!="null")
			  	setFieldValue('cmbMachid',keyIds.machId);	
						
	}
	
	/*function monthGrid_loadComplete() {
		alert("loaddddd");
	}*/

	jQuery('#btnViewCalendar').click(function() {
		//alert("click btnViewCalendar");
		var sectionId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');		  
		processAjaxCalls('getDetailTableName.ire','?q=2&sectId='+sectionId+"&entryDate="+entryDate,"detailTableRecallSuccess");
	});

	jQuery('#btnViewInternalRejection').click(function() {
		var dataStr = "";
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		var machId = jQuery('#cmbMachid').combobox("getValue");
		var date = jQuery('#dteEntryDate').datebox('getValue');
		var shift = jQuery('#cmbShiftid').combobox("getValue");
	
		if (cellId == "") { alert("Select Line");		return ; 	}		
		if (date == "") {   alert("Select Date");		return ;	}
		if (shift == "") {	alert("Select Shift");		return ;	}	

		var currentDate = getServerDateTime();
		var selDate = convertStringToDate(date);
		var dateDiff =  selDate.getDate() - currentDate.getDate() ;		 
		if (dateDiff>0) {
			alert("Entry Date should be less than Current Date ");		
			return ;
		}
		
		dataStr+= "q=2&machId="+machId;
		dataStr+= "&date="+date;		
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		var sectId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();		
		var cellId= jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		var flid = jQuery("#frmInternalRejectionCalendar input[id='flid']").val();
		dataStr+= "&sectId="+sectId+"&factId="+factId+"&cellId="+cellId+"&flid="+flid;
		var condParam = "";
		condParam+= "CHKCELL=Y;SECTIONID="+sectId+";CELLID="+cellId;
		condParam+= ";CHKMACHINE=Y;MACHINEID="+machId+";PCSOPTION=;";
		processAjaxCalls('getIsPcsEnabled.ire','?q=2&condParam='+condParam,"pcsEnableforShift");				
	});

	function pcsEnableforShift(result)
	 {		
		if(result.isPcsEnabled == 'Y') {
			var date = jQuery("#dteEntryDate").datebox('getValue');		
			var shift = jQuery("#cmbShiftid").combobox('getValue');
			var dataStr = "";
			var machId= jQuery('#cmbMachid').combobox("getValue");
			var  cellId  = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
			dataStr+= "q=2&cellId="+cellId+"&machId="+machId;
			dataStr+= "&date="+date;
			dataStr+= "&shift="+shift;		
			var sectId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
			var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
			var flid = jQuery("#frmInternalRejectionCalendar input[id='flid']").val();
			var mode = jQuery('#hdnMode').val();
			dataStr+= "&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid+"&mode="+mode;
			//alert(dataStr);		
			var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"machId":machId,"flid":flid,"date":date,"shift":shift,"mode":mode};			
			navigateToNextForm('intRejView_input.ire?'+dataStr,'Internal Rejection Entry',null,persistentData);		
		}
		else
			alert("Internal Rejection is Disabled for this Cell");
	}
	
	function fnViewPcs() {
		var dataStr = "";
		var cellId = jQuery('#cmbMachid').combobox("getValue");
		var date = jQuery('#dteEntryDate').datebox('getValue');
		//var shift = jQuery('#cmbShiftid').combobox("getValue");
	
	/*	if (cellId == "") {
			alert("Select Cell");		return ;
		}
		*/
		if (date == "") {
			alert("Select Date");		return ;
		}
		
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		var date = jQuery('#dteEntryDate').datebox('getValue');
		//var date = '01-' + jQuery('#dteEntryDate').datebox('getValue');
		var dataString = "?q=2&factId="+factId+"&date="+date;
		//alert(dataString);
		processGridnew('monthGrid_view.ire',dataString,"monthGrid","monthPager","Intetnal Rejection Entry - View","monthdblClick","","monthGrid_loadComplete","monthGridError");
	}
	
	function monthdblClick(id) {
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		
		if (cellId == "") { alert("Select Line");		return ; 	}
			
		var machId = jQuery('#cmbMachid').combobox("getValue");
		//if (machId == "") {		alert("Select Machine");		return ;		} 
		var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
		var date = jQuery("#monthGrid").jqGrid('getCell',rowId,0);		
		var colid = jQuery('#hdnActiveCell').val();				 
		if (colid == 0 || colid == 4 ) return ;
		var shift = jQuery("#monthGrid").jqGrid('getCell',1,colid);
		var dataStr = "";
		var machId= jQuery('#cmbMachid').combobox("getValue");
		var  cellId  = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		dataStr+= "q=2&cellId="+cellId+"&machId="+machId;
		dataStr+= "&date="+date;
		dataStr+= "&shift="+shift;		
		var sectId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		var flid = jQuery("#frmInternalRejectionCalendar input[id='flid']").val();
		var mode = jQuery('#hdnMode').val();
		dataStr+= "&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&machId="+machId+"&mode="+mode+"&flid="+flid;	
		var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"machId":machId,"flid":flid,"date":date,"shift":shift,"mode":mode};			
		navigateToNextForm('intRejView_input.ire?'+dataStr,'Internal Rejection Entry',null,persistentData);
	
	}
	
	function pcsEnabledRecallSuccess(result)
	 {		
		//alert(result.isPcsEnabled);
		if(result.isPcsEnabled == 'Y') {
			var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
			var colid = jQuery('#hdnActiveCell').val();
			if (colid == 0 ) return ; 
			var shiftCode = jQuery("#monthGrid").jqGrid('getCell',rowId,colid);
			var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
			processAjaxCalls('getShiftKeyid.ire','?q=2&factId='+factId+'&shiftCode='+shiftCode,"getShiftSuccess");		
		}
		else
			alert("PCS is Disabled for this Cell");
	}

	function getShiftSuccess(result) {
		var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
		var date = jQuery("#monthGrid").jqGrid('getCell',rowId,0);				 
		var shift = result.shift;

		var dataStr = "";
		var machId = jQuery('#cmbMachid').combobox("getValue");		
		//var date = jQuery('#dteEntryDate').datebox('getValue');
		//var shift = jQuery('#cmbShiftid').combobox("getValue");
		
		dataStr+= "q=2&cellId="+machId;
		dataStr+= "&date="+date;
		dataStr+= "&shift="+shift;
		//alert(shift);
		if (shift =="" || shift == " ") { 
			alert("Shift Not assign for the Factory"); 
			return;
		}
		
		var sectId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		var flid = jQuery("#frmInternalRejectionCalendar input[id='flid']").val();
	
		dataStr+= "&sectId="+sectId+"&factId="+factId+"&machId="+machId+"&flid="+flid;
		//alert(dataStr);							
		var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"machId":machId,"flid":flid,"date":date,"shift":shift};
		navigateToNextForm('pcsView_input.ire?'+dataStr,'Internal Rejection Entry',null,persistentData);		
	}

	function monthGrid_loadComplete(){	

		jQuery('#monthGrid').css('height','25%');
		
		hideJqGridRow('monthGrid', '1');
				
		jQuery("#monthGrid").setGridParam({
			onCellSelect:function(id,cellidx,cellvalue) {
				jQuery('#hdnActiveCell').val(cellidx);
			}
		});

		var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");

		var countCols = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel').length;
		var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');
			//alert(Object.keys(colIds[0].name));					
		for (i=1;i<= rowCnt;i++) 
		{			
			// no entry  '#ffc0ff'
			// entry exists '#c0ffc0'
			// holiday '#9d99f2' //weekly off '#aae0fa'			

			for (j=0;j<= countCols-1;j++)  {
				//alert(colIds[j].name);
				jQuery("#monthGrid").jqGrid('setCell',i,colIds[j].name,"",{'background-color':'#ffc0ff','font-size': '12','font-weight' : 'bold','color':'black'});
			}
		}		
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();
		processAjaxCalls('getHolidayDates.ire',"?q=2&entryDate="+entryDate+"&factId="+factId,"holidaySuccess");		
	}

	function holidaySuccess(result) {
		var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');
		for(var i=0; i<result.holiData.length;i++){
			var holidayDate = result.holiData[i].holidayDate;
			var holidayFlag = result.holiData[i].holidayFlag;			
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");							
			for (var j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");					
				if (date == holidayDate ) {
					
					for (var k=0; k<=3; k++) {		
						if (holidayFlag=="W")
							jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#aae0fa'});
						else
							jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#9d99f2'});
					}						 
				}
			}  
		}
		var detailTable = jQuery('#hdnDetailTable').val();		
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		var cellId = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		var machId = jQuery("#frmInternalRejectionCalendar input[id='machine']").val();
		//alert("machine:" + machId);
		var dataStr='?q=2&detailTable='+detailTable+'&entryDate='+entryDate+"&cellId="+cellId+"&machId="+machId;
		//alert(dataStr);
		processAjaxCalls('getEntryExistsDates.ire',dataStr,"entryExistsSuccess");
	}	
	
	function entryExistsSuccess(result) {
		var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');
		if(result != null ){
		for(var i=0; i<result.eedData.length;i++){
			var shiftId = result.eedData[i].shiftKeyId;
			//alert(shiftId);
			var entryDate = result.eedData[i].entryDate;
			var rejExists = result.eedData[i].rejExists;
			var intRejExists = result.eedData[i].intRejExists;
			//var fullyNoplan = result.oeeData[i].fullyNoplan;
			
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");									
			for (var j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");
				//alert( date);
				if (date == entryDate ) {					
					for (var k=1; k<=3; k++) {						
						var shiftKeyid = jQuery("#monthGrid").jqGrid('getCell',1,colIds[k].name);											
						if (shiftKeyid == shiftId) 	
							if (intRejExists=="1")						
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#c0ffc0'});
							else if (rejExists=="1")
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#ff8040'});
					}
				}
			}  
		}
		}
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		var sectId = jQuery("#frmInternalRejectionCalendar input[id='section']").val();
		var cellId = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		var mchId = jQuery('#cmbMachid').combobox("getValue"); 
		var detailTable = jQuery('#hdnDetailTable').val();
		
		var dataStr = "?q=2&detailTable="+detailTable+"&entryDate="+entryDate+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId;		
		processAjaxCalls('getDateOeeValue.ire',dataStr,"oeeValuesSuccess");
	}
	
	function oeeValuesSuccess(result) {
		var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');
      if(result != null ){
		for(var i=0; i<result.oeeData.length;i++){
			var shiftId = result.oeeData[i].oeeShift;			
			var oeeDate = result.oeeData[i].oeeDate;		
			var oeeValue = result.oeeData[i].oeeValue;
			var qrValue = result.oeeData[i].qrValue;
							
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");									
			for (var j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");				
				if (oeeDate == date ) {					
										
					for (var k=1; k<=3; k++) {
						var shiftKeyid = jQuery("#monthGrid").jqGrid('getCell',1,colIds[k].name);											
						if (shiftKeyid == shiftId) 							
							jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,qrValue);						 
					}
				}
			}  
		}
      }
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		var cellId = jQuery("#frmInternalRejectionCalendar input[id='cell']").val();
		var mchId = jQuery('#cmbMachid').combobox("getValue"); 
		var detailTable = jQuery('#hdnDetailTable').val();	
		var dataStr = "?q=2&entryDate="+entryDate+"&cellId="+cellId+"&mchId="+mchId+"&detailTable="+detailTable;
		processAjaxCalls('getPendingColor.ire',dataStr,"pendingColorSuccess");
		
	}
	function pendingColorSuccess(result)
	{
		var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');
	 if(result != null ){
		for(var i=0; i<result.pendColData.length;i++){
			var shiftId = result.pendColData[i].shiftKeyId;
			var entryDate = result.pendColData[i].entryDate;
			var balQty = result.pendColData[i].balQty;		
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");									
			for (var j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");				
				if (date == entryDate ) {					
					for (var k=1; k<=3; k++) {						
						var shiftKeyid = jQuery("#monthGrid").jqGrid('getCell',1,colIds[k].name);																
						if (shiftKeyid == shiftId) 	{
							if (balQty != null && balQty != '' && balQty != ' ')						
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#a4d885'});
						}
					}
				}  
			}
		}
	  }
	}
	function frmInternalRejectionCalendarcmbCellid_onSelect(record) 	{
		//alert(record.id);
		jQuery('#cmbMachid').combobox('clear');
		loadFunctionalLocation("InternalRejectionfunLocation","functionalLoc_Entry.ire","InternalRejectionfunLocationnValues","frmInternalRejectionCalendar","&cellId="+record.id);
	}

	
	function frmInternalRejectionCalendarcmbMachid_onLoadSuccess() 	{
		var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();		  
		fillComboBox("frmInternalRejectionCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");
	}
	
	function frmInternalRejectionCalendarcmbMachid_onSelect(record) 	{
		//alert(record.id);
		  loadFunctionalLocation("InternalRejectionfunLocation","functionalLoc_Entry.ire","InternalRejectionfunLocationnValues","frmInternalRejectionCalendar","&machId="+record.id);
	}

	function loadFunctionalLocationHierarchy_successCallback() 	{
		  var factId = jQuery("#frmInternalRejectionCalendar input[id='factory']").val();		  
		  reloadCombo("frmInternalRejectionCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");
	}
	

	function detailTableRecallSuccess(result)	{			
		jQuery('#hdnDetailTable').val(result.detailTableName);
		fnViewPcs();
		//alert(jQuery('#hdnDetailTable').val());
	}
	
</script>

<div style="margin-left:20%; margin-top:2%; height:90%; width:80%; width:100%\9; border:1px solid black;" >
<div style="width:100%;margin-left:5%; " >
<form id="frmInternalRejectionCalendar" >
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}"/>
<input type="hidden" id="hdnActiveCell" name="hdnActiveCell"/>
<input type="hidden" id="hdnDetailTable" name="hdnDetailTable"/>
<input type="hidden" id="hdnShift" name="hdnShift"/>
		<div  class="easyui-paddingbfpx">
			<div  class="easyui-paddingbfpx" id="frmInternalRejectionCalendarFuntKeyIds">
				<input type="hidden" id="factory" name="factory" value="${requestScope.factId}"  ></input>
				<input type="hidden" id="section" name="section" value="${requestScope.sectId}"  ></input>
				<input type="hidden" id="cell" name="cell" value="${requestScope.cellId}" ></input>
				<input type="hidden" id="machine" name="machine" value="${requestScope.machId}"  ></input>
				<input type="hidden" id="flid" name="flid" value="${requestScope.flid}"  ></input>
			</div>
			<div id="InternalRejectionfunLocation" style="padding-left: px;" ></div>
		</div>
	

	<div style="">
		<label class="mandatory-lbl" >Line</label>
		<label style="padding-left: 190px;" >Equipment</label>
		<label class="mandatory-lbl" style="padding-left: 190px;" >Date</label>
		<label style="padding-left: 80px;" >Shift</label>
	</div>
    <div class="easyui-paddingbfpx" style="" >
    	<input type="text" id="cmbCellid" name="cmbCellid" class="easyui-combobox" value="${requestScope.cellId}" style="width: 200px;"/>
		<span style="padding-left: 1%;">
			<input type="text" id="cmbMachid" name="cmbMachid" class="easyui-combobox" value="${requestScope.machId}" style="width: 215px;"/>						
		</span>
		<span style="padding-left: 1%;">
	    	<input id="dteEntryDate" name="dteEntryDate" class="easyui-text" readonly="readonly" required="true" style="width: 100px;" value="${requestScope.date}"/>&nbsp;
	    </span>	    
    	<span style="padding-left: 1%;">
	    	<input type="text" class="easyui-combobox" id="cmbShiftid" name="cmbShiftid" readonly="readonly"  style="width: 110px;"  value="${requestScope.shift}"/>	    	
	    </span>	 
	    <span style="padding-left: 1%;">
	    	<input type="button" id="btnViewCalendar" name="btnViewCalendar" class="easyui-button"  value="View Calendar" style="width: 90px;"/>
	    </span>
	    <span style="padding-left: 1%;">
	    	<input type="hidden" id="btnViewInternalRejection" name="btnViewInternalRejection" class="easyui-button"  value="Entry" style=""/>
	    </span>	    
    </div>	
    <div class="easyui-paddingbfpx" style="margin-top: 4px" >
    	<label class="mandatory-lbl"> <span style="padding-left: %;"> Double click the Shift to Internal Rejection Entry </span> </label>
    	<span style="padding-left: 10%;">
    		<input type="text" value="Internal Rejection Status Calendar with QR" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:350px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
    	</span>

	</div>
    
    <table align="left" style="float: center;padding-right: 40px;margin-top:1px;"> 
    <tr> 
    <td>
		<div style="" align="center">
			<div style="float: center;padding-right: 40px;margin-top:5px;">
					<table id="monthGrid" width="400px" style="float: center;"></table> </div>
<!--					<div id="monthPager" style="float: center;">	</div> -->				
		</div>
	</td>
	<td valign="middle">
			<div>						
				<input type="text" value="No Entry" disabled="disabled" style="width:160px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#ffc0ff;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="PCS Rejection" disabled="disabled" style="margin-top:12px; width:160px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#ff8040;font-weight:bold;text-align:center; " />
			</div>			
			<div>
				<input type="text" value="Completed Entry" disabled="disabled" style="margin-top:12px; width:160px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#c0ffc0;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="Incomplete Entry" disabled="disabled" style="margin-top:12px; width:160px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#a4d885;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="Holiday" disabled="disabled" style="margin-top:12px;width:160px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#9d99f2;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="Weekly Off" disabled="disabled" style="margin-top:12px;width:160px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#aae0fa;font-weight:bold;text-align:center; " />
			</div>
	</td>
	</tr>
	</table>       

	  
</form>
</div>
</div>