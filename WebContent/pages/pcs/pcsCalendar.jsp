<script type="text/javascript">
	jQuery(document).ready(function(){
		initialiseForm('frmPcsCalendar');
		jQuery('#submitForm').val('frmPcsCalendar');
		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		fillComboBox("frmPcsCalendar","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );		
		fillComboBox("frmPcsCalendar","cmbMac	hineid","machineCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId  );				
		formatDateBox('dteEntryDate','dd-MMM-yyyy');

		var dat = jQuery('#dteEntryDate').datebox('getValue');
  		if (dat=="")
  			fillWithCurrentDate('dteEntryDate');
  		dat = jQuery('#dteEntryDate').datebox('getValue');
  			//fillWithCurrentMonth('dteEntryDate');
  		//else
  		//	jQuery('#dteEntryDate').datebox('setValue',dat.substring(3));

		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();		
		var sectionId = jQuery("#frmPcsCalendar input[id='section']").val();
		var cellId = jQuery("#frmPcsCalendar input[id='cell']").val();
		var machId = jQuery("#frmPcsCalendar input[id='machine']").val();
		var flid = jQuery("#frmPcsCalendar input[id='flid']").val();
									
  	    var dataStr = "&cmbPrlmFactoryid="+factId+"&cmbPrlmSectionid="+sectionId+"&cmbPrlmCellid="+cellId+"&cmbPrlmMachineid="+machId+"&cmbPrlmFlid="+flid;	;	  					

		if (cellId != null)
			loadFunctionalLocation("pcsCalendarfunLocation","functionalLoc_Calendar.pcs","pcsCalendarfunLocationnValues","frmPcsCalendar","&cellId="+cellId);
		else
			loadFunctionalLocation("pcsCalendarfunLocation","functionalLoc_Calendar.pcs","pcsCalendarfunLocationValues","frmPcsCalendar",dataStr);

		//var sectionId = jQuery("#frmPcsCalendar input[id='section']").val();
		
	/*	if (!( sectionId == ""  || sectionId == ""))		
			processAjaxCalls('getDetailTableName.pcs','?q=2&sectId='+sectionId+"&entryDate="+dat,"detailTableRecallSuccess");
		else
		{
			var dataString = "?q=2&factId="+factId+"&date=";//+dat+"&cellId="+cellId+"&sectId="+sectionId;		
			processGridnew('monthGrid_view.pcs',dataString,"monthGrid","monthPager","PCS Entry - View","monthdblClick","","monthGrid_loadComplete","");
		}*/
		fnViewPcs();
		
		//var mode = jQuery('#hdnMode').val();
		//processAjaxCalls('setFormActionMode.pcs','?q=2&mode='+mode,"formModeSuccess");		
		processAjaxCalls('getEntryAllowDates.pcs','',"allowDatesSuccess");			
				
			
		//var dataString = "?q=2&factId="+factId+"&date=";//+dat+"&cellId="+cellId+"&sectId="+sectionId;		
		//processGridnew('monthGrid_view.pcs',dataString,"monthGrid","monthPager","PCS Entry - View","monthdblClick","","monthGrid_loadComplete","");
		
		var serverTime = srvTime();
		var currentTime = new Date(serverTime);
		var hours = currentTime.getHours();
		var minutes = currentTime.getMinutes();
		
		if (minutes < 10){
			minutes = "0" + minutes;
		}		
		var dataString = '?q=2&factId='+jQuery("#frmPcsCalendar input[id='factory']").val()+'&sectId='+jQuery("#frmPcsCalendar input[id='section']").val();
		    dataString += '&cellId='+jQuery("#frmPcsCalendar input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
		setTimeout(function() {processAjaxCalls('txt_shift.brdn',dataString,'getPCSShift','getPCSShiftErr');},1250);
		fillComboBox("frmPcsCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");
	});
	function  getPCSShift(record)
	{
		jQuery('#cmbShiftid').combobox('setValue',record.shift);
	}   
	function allowDatesSuccess(result) {
		//alert(result.days);
		jQuery('#hdnAllowDays').val(result.days);
	}
	
	function frmPcsCalendar_FuntLocHierarchy_SuccessCallBack(keyIds)
	{	
		
		jQuery('#cmbCellid').combobox('clear');		
		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();		
		var sectId = jQuery("#frmPcsCalendar input[id='section']").val();
		
		//fillComboBox("frmPcsCalendar","cmbCellid","cellCombo.commonFilter?q=2");//?q=2&pcsEnabled=Y&factId="+factId+"&sectId="+sectId )
		reloadCombo("frmPcsCalendar","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId+"&sectId="+sectId );
	
		jQuery('#cmbShiftid').combobox('clear');
		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();		  
		//reloadCombo("frmPcsCalendar","cmbShiftid","combo_shift.pcs"+"?q=2&factId="+factId);
		reloadCombo("frmPcsCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");
	//	fillComboBox("frmPcsCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");
		  
		var cellId = jQuery("#frmPcsCalendar input[id='cell']").val();
		var dataStr="?q=2&pcsEnabled=Y&factId="+factId+"&cellId="+cellId;	
		//var dataStr="?q=2";//&pcsEnabled=Y";	  
		reloadCombo("frmPcsCalendar","cmbCalMachineid","machineCombo.commonFilter"+dataStr  );

		if (keyIds.cellId!="null")
		 	setFieldValue('cmbCellid',keyIds.cellId);
		  
		if (keyIds.machId!="null")
		  	setFieldValue('cmbCalMachineid',keyIds.machId);
						
	 }
	
	/*function monthGrid_loadComplete() {
		alert("loaddddd");
	}*/

	jQuery('#btnViewCalendar').click(function() {
			fnViewPcs();
		//var sectionId = jQuery("#frmPcsCalendar input[id='section']").val();
		//var date = jQuery('#dteEntryDate').datebox('getValue');
		//processAjaxCalls('getDetailTableName.pcs','?q=2&sectId='+sectionId+"&entryDate="+date,"detailTableRecallSuccess");
	});
	
	function pcsEnableforShift(result)
	 {		
		if(result.enableShift == 'N')
		{
			alert("Cannot Enter for Future Shift");
		}
		else{
		if(result.isPcsEnabled == 'Y') {
			var dataStr = "";
			
			var date = jQuery('#dteEntryDate').datebox('getValue');
			var cellId = jQuery('#cmbCellid').combobox("getValue");
			var shift = jQuery('#cmbShiftid').combobox("getValue");
			
			dataStr+= "q=2&cellId="+cellId;
			dataStr+= "&date="+date;
			dataStr+= "&shift="+shift;

			var sectId = jQuery("#frmPcsCalendar input[id='section']").val();
			var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
			var mchId = jQuery('#cmbCalMachineid').combobox("getValue");
			var mode = jQuery('#hdnMode').val();
			//alert(mode);
			dataStr+= "&mchId="+mchId+"&sectId="+sectId+"&factId="+factId+"&mode="+mode;
			//alert(dataStr);							
			var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode};			
			//navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS Entry',null,persistentData);
			var url = jQuery("#hiddenUrl").val();
			/*if(url.indexOf('Test') >=0)
				navigateToNextForm('pcsViewTest_input.pcs?'+dataStr,'PCS Entry',null,persistentData);			
			else
				navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS Entry',null,persistentData);*/	
			if(url.indexOf('Test') >=0)
				LoadPopUp("divPcsView",'pcsViewTest_input.pcs?'+dataStr, true,"96%","91%","2%","10px", "Pcs_View_Callback","PCS View",false,false);
			else
				LoadPopUp("divPcsView",'pcsViewTest_input.pcs?'+dataStr, true,"97%","91%","5%","13px", "Pcs_View_Callback","PCS View",false,false);
		}
		else
			alert("PCS is Disabled for this Cell");
		}
	}
	
	function fnViewPcs() {
		var dataStr = "";
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		var date = jQuery('#dteEntryDate').datebox('getValue');
		if (date == "") {
			alert("Select Date");		return ;
		}
		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		var sectionId = jQuery("#frmPcsCalendar input[id='section']").val();
		var date = jQuery('#dteEntryDate').datebox('getValue');
		//var date = '01-' + jQuery('#dteEntryDate').datebox('getValue');
		//var dataString = "?q=2&factId="+factId+"&date="+date;
		var dataString = "?q=2&factId="+factId+"&date="+date+"&cellId="+cellId+"&sectId="+sectionId;		
		//alert(dataString);
		processGridnew('monthGrid_view.pcs',dataString,"monthGrid","monthPager","PCS Entry - View","monthdblClick","","monthGrid_loadComplete","");
	}

	jQuery('#btnViewPCS').click(function() {
		var dataStr = "";
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		var date = jQuery('#dteEntryDate').datebox('getValue');
		var shift = jQuery('#cmbShiftid').combobox("getValue");
	
		if (cellId == "") { alert("Select Line");		return ; 	}		
		if (date == "") {   alert("Select Date");		return ;	}
		if (shift == "") {	alert("Select Shift");		return ;	}	

		var calOrDat ="D";
		fnValidateDate(calOrDat, date,shift);
	});
	
	function monthdblClick(id) {
		
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		if (cellId == "") {
			alert("Select Line");		return ;
		}
		
		var colid = jQuery('#hdnActiveCell').val();		
		if (colid == 0 || colid == 4 ) return ;
	
		var date = jQuery("#monthGrid").jqGrid('getCell',id,0);

		var calOrDat = "C";
		fnValidateDate(calOrDat,date,colid);	
		
	}
	function monthGridError()
	{
		alert('ERR');
	}

	function fnValidateDate(calOrDat, date,shift) {
		//var date = date;
		var currentDate = getServerDateTime();
		var selDate = convertStringToDate(date);
		var nTotalDiff = getTimeDifference (currentDate,selDate);
		var dateDiff = nTotalDiff.convtDays;
		
		if (dateDiff>0) {
			alert("Entry Date should be less than or equal to Current Date ");		
			return ;
		}
		var allowDays = jQuery('#hdnAllowDays').val();
		//alert(allowDays);
		var mode = jQuery('#hdnMode').val();
		if (mode=="create" && (-dateDiff) > allowDays ) {
			alert("Entry Date should be allow only " + allowDays + " days ");		
			return ;			 
		}		
		var dataStr = "";
		var cellId = jQuery('#cmbCellid').combobox("getValue");
		var sectId = jQuery("#frmPcsCalendar input[id='section']").val();
		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		
		
		dataStr+= "q=2&cellId="+cellId;
		dataStr+= "&date="+date;
		dataStr+= "&sectId="+sectId+"&factId="+factId;

		//send it same format don't change
		var condParam = "";
		condParam+= "CHKCELL=Y;SECTIONID="+sectId+";CELLID="+cellId+";DATE="+date+";SHIFT="+shift+";FACTID="+factId+";calOrDat="+calOrDat;
		condParam+= ";CHKMACHINE=N;MACHINEID=;PCSOPTION=;";
		

		if (calOrDat=="C") 
			processAjaxCalls('getIsPcsEnabled.pcs','?q=2&condParam='+condParam,"pcsEnabledRecallSuccess");
		else
			processAjaxCalls('getIsPcsEnabled.pcs','?q=2&condParam='+condParam,"pcsEnableforShift");
	}
	function pcsEnabledRecallSuccess(result)
	 {		
		
		if(result.enableShift == 'N')
		{
			alert("Cannot enter for future shift");
		}
		else{
		if(result.isPcsEnabled == 'Y') {
			var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
			var colid = jQuery('#hdnActiveCell').val();
			if (colid == 0 || colid == 4 ) return ; 
			//var shiftCode = jQuery("#monthGrid").jqGrid('getCell',1,colid);			
			//var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
			//processAjaxCalls('getShiftKeyid.pcs','?q=2&factId='+factId+'&shiftCode='+shiftCode,"getShiftSuccess");
			
			var shiftId = jQuery("#monthGrid").jqGrid('getCell',1,colid);			
			if (shiftId=="Total")
				return;
			getShiftSuccess(shiftId);		
		}
		else
			alert("PCS is Disabled for this Cell");
		}
	}

	function getShiftSuccess(shift) {
	//function getShiftSuccess(result) {
	
		var rowId = jQuery("#monthGrid").jqGrid('getGridParam', 'selrow');
		var date = jQuery("#monthGrid").jqGrid('getCell',rowId,0);				 
		var shift = shift;

		var dataStr = "";
		//alert(shift);
		//var date = jQuery('#dteEntryDate').datebox('getValue');
		//var shift = jQuery('#cmbShiftid').combobox("getValue");
		
		dataStr+= "q=2";
		dataStr+= "&date="+date;
		dataStr+= "&shift="+shift;
		//alert(shift);
		if (shift =="" || shift == " ") { 
			alert("Shift Not assign for the Factory"); 
			return;
		}
		
		var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		var sectId = jQuery("#frmPcsCalendar input[id='section']").val();		
		var cellId = jQuery('#cmbCellid').combobox("getValue");		
		var mchId= jQuery('#cmbCalMachineid').combobox("getValue");
		

		var mode = jQuery('#hdnMode').val();
		var formLock = "";		
		dataStr+= "&mchId="+mchId+"&cellId="+cellId+"&sectId="+sectId+"&factId="+factId+"&mode="+mode;
		//alert(dataStr);
		var persistentData = {"factId":factId,"sectId":sectId,"cellId":cellId,"mchId":mchId,"date":date,"shift":shift,"mode":mode,"formLock":formLock};
		var url = jQuery("#hiddenUrl").val();

			
		if(url.indexOf('Test') >=0)
			navigateToNextForm('pcsViewTest_input.pcs?'+dataStr,'PCS Entry',null,persistentData);			
		else
			navigateToNextForm('pcsView_input.pcs?'+dataStr,'PCS Entry',null,persistentData);
		
		/*if(url.indexOf('Test') >=0)
			LoadPopUp("divPcsView",'pcsViewTest_input.pcs?'+dataStr, true,"97%","90%","5%","13px", "Pcs_View_Callback","PCS View",false,false);
		else
			LoadPopUp("divPcsView",'pcsView_input.pcs?'+dataStr, true,"97%","90%","5%","13px", "Pcs_View_Callback","PCS View",false,false);
		*/
	}
	function Pcs_View_Callback(){
		jQuery("#divPcsView").css('margin-top',"0%");
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
			// no entry  '#ffe1f0'
			// entry exists '#c0ffc0'
			// holiday '#9d99f2' //weekly off '#aae0fa'	
			for (var j=0;j<= countCols-1;j++)  {
				//alert(colIds[j].name);
				jQuery("#monthGrid").jqGrid('setCell',i,colIds[j].name,"",{'background-color':'#ffe1f0','font-size': '12','font-weight' : 'bold','color':'black'});
			}
		}		
		var monGrid = jQuery("#monthGrid").jqGrid('getDataIDs');
		 for(i=0;i<=monGrid.length;i++)	
		 {			
			for (var k=0; k<=4; k++)
			{
				if(jQuery("#monthGrid").getCell(i, 'Total') == "W")
						jQuery("#monthGrid").jqGrid('setCell',i,colIds[k].name,k==4?" ":"",{'background-color':'#aae0fa'});
				else if(jQuery("#monthGrid").getCell(i, 'Total') == "H")
						jQuery("#monthGrid").jqGrid('setCell',i,colIds[k].name,k==4?" ":"",{'background-color':'#9d99f2'});
				if(jQuery("#monthGrid").getCell(i, colIds[k].name) == "A")
					jQuery("#monthGrid").jqGrid('setCell',i,colIds[k].name," ",{'background-color':'#c0ffc0'});	
			}					 
		 }

		 	var entryDate= jQuery('#dteEntryDate').datebox('getValue');
			var sectId = jQuery("#frmPcsCalendar input[id='section']").val();
			var cellId = jQuery("#frmPcsCalendar input[id='cell']").val();
			var mchId = jQuery('#cmbCalMachineid').combobox('getValue');
			var detailTable = jQuery('#hdnDetailTable').val();
			var dataStr = "?q=2&detailTable="+detailTable+"&entryDate="+entryDate+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId;
					
			processAjaxCalls('getDateOeeValue.pcs',dataStr,"oeeValuesSuccess");
		//var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		//var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		//processAjaxCalls('getHolidayDates.pcs',"?q=2&entryDate="+entryDate+"&factId="+factId,"holidaySuccess");		
	}

	function holidaySuccess(result) {
		for(var i=0; i<result.holiData.length;i++){
			var holidayDate = result.holiData[i].holidayDate;
			var holidayFlag = result.holiData[i].holidayFlag;			
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");							
			for (var j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");					
				if (date == holidayDate ) {
					var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');
					for (var k=0; k<=4; k++) {		
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
		var cellId = jQuery("#frmPcsCalendar input[id='cell']").val();				
		processAjaxCalls('getEntryExistsDates.pcs','?q=2&detailTable='+detailTable+'&entryDate='+entryDate+"&cellId="+cellId,"entryExistsSuccess");
	}	
	
	function entryExistsSuccess(result) {
		for(var i=0; i<result.eedData.length;i++){
			var shiftId = result.eedData[i].shiftKeyId;
			//alert(shiftId);
			var entryDate = result.eedData[i].entryDate;			
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");									
			for (j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");
				//alert( date);
				if (date == entryDate ) {					
					var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');					
					for (k=1; k<=4; k++) {						
						//alert(colIds[k].name);
						var shiftKeyid = jQuery("#monthGrid").jqGrid('getCell',1,colIds[k].name);											
						if (shiftKeyid == shiftId) 							
							jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#c0ffc0'});						 
					}
				}
			}  
		}
		var entryDate= jQuery('#dteEntryDate').datebox('getValue');
		var sectId = jQuery("#frmPcsCalendar input[id='section']").val();
		var cellId = jQuery("#frmPcsCalendar input[id='cell']").val();
		var mchId = jQuery('#cmbCalMachineid').combobox('getValue');
		var detailTable = jQuery('#hdnDetailTable').val();
		var dataStr = "?q=2&detailTable="+detailTable+"&entryDate="+entryDate+"&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId;		
		processAjaxCalls('getDateOeeValue.pcs',dataStr,"oeeValuesSuccess");
	}

	function oeeValuesSuccess(result) {
		for(var i=0; i<result.oeeData.length;i++){
			var shiftId = result.oeeData[i].oeeShift;			
			var oeeDate = result.oeeData[i].oeeDate;		
			var oeeValue = result.oeeData[i].oeeValue;
			var isPending = result.oeeData[i].isPending;
			var QACompleted = result.oeeData[i].QACompleted;
			var fullyNoplan = result.oeeData[i].fullyNoplan;
			
			
			var	rowCnt = jQuery("#monthGrid").getGridParam("reccount");									
			for (j=2;j<= rowCnt;j++) {
				var date = jQuery("#monthGrid").jqGrid('getCell',j,"date");				
				if (oeeDate == date ) {					
					var colIds  = jQuery('#monthGrid').jqGrid('getGridParam', 'colModel');	
										
					for (k=1; k<=4; k++) {
						var shiftKeyid = jQuery("#monthGrid").jqGrid('getCell',1,colIds[k].name);											
						if (shiftKeyid == shiftId) { 	
												
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,oeeValue);				
							if (isPending=="1") 
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#FC6A6A'});
							else if (QACompleted=="1")
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#00a600'});

							if (fullyNoplan=="Y")
								jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"",{'background-color':'#808080'});
								//jQuery("#monthGrid").jqGrid('setCell',j,colIds[k].name,"-");					
						}												 
					}
				}
			}  
		}
	}
	
	function frmPcsCalendarcmbCellid_onLoadSuccess() 	{
		//var factId = jQuery("#frmPcsCalendar input[id='factory']").val();		  
		//fillComboBox("frmPcsCalendar","cmbShiftid","combo_shift.pcs"+"?q=2&factId="+factId);
		//fillComboBox("frmPcsCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");			
				
	}
	
	function frmPcsCalendarcmbCellid_onSelect(record) 	{
		//alert(record.id);
		jQuery('#cmbCalMachineid').combobox('clear');
		loadFunctionalLocation("pcsCalendarfunLocation","functionalLoc_Calendar.pcs","pcsCalendarfunLocationnValues","frmPcsCalendar","&cellId="+record.id);
		jQuery("#monthGrid").clearGridData();
		//fnViewPcs();
	}
	
	function frmPcsCalendarcmbCalMachineid_onSelect(record) 	{
		//alert(record.id);
	  loadFunctionalLocation("pcsCalendarfunLocation","functionalLoc_Calendar.pcs","pcsCalendarfunLocationnValues","frmPcsCalendar","&machId="+record.id);
	  jQuery("#monthGrid").clearGridData();
	  //fnViewPcs();
	  //processGridnew('monthGrid_view.pcs','?q=2',"monthGrid","monthPager","PCS Entry - View","monthdblClick","","monthGrid_loadComplete","");
	}
	
	/*function loadFunctionalLocationHierarchy_successCallback() 	{			
		//jQuery('#cmbCellid').combobox('clear');
		//var factId = jQuery("#frmPcsCalendar input[id='factory']").val();
		//reloadCombo("frmPcsCalendar","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );		
		  jQuery('#cmbShiftid').combobox('clear');
		  var factId = jQuery("#frmPcsCalendar input[id='factory']").val();		  
		  //reloadCombo("frmPcsCalendar","cmbShiftid","combo_shift.pcs"+"?q=2&factId="+factId);
		  reloadCombo("frmPcsCalendar","cmbShiftid","shift.commonFilter"+"?q=2&factId="+factId+"&frmRfilter=yes");		  
		  var cellId = jQuery("#frmPcsCalendar input[id='cell']").val();
		  var dataStr="?q=2&pcsEnabled=Y&factId="+factId+"&cellId="+cellId;		  
		  reloadCombo("frmPcsCalendar","cmbCalMachineid","machineCombo.commonFilter"+dataStr  );
		  
	}
	*/

	function detailTableRecallSuccess(result)	{			
		jQuery('#hdnDetailTable').val(result.detailTableName);
		fnViewPcs();
		 // setTimeout(function() {fnViewPcs();},2500);
		//alert(jQuery('#hdnDetailTable').val());
	}
	
</script>
<div style="margin-left:20%; margin-top:2%; height:90%;height:90%\9; width:80%; width:100%\9; border:1px solid black;" >
<div style="width:100%;margin-left:5%; " >
<form id="frmPcsCalendar" >
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}"/>
<input type="hidden" id="hdnActiveCell" name="hdnActiveCell"/>
<input type="hidden" id="hdnDetailTable" name="hdnDetailTable"/>
<input type="hidden" id="hdnShift" name="hdnShift"/>
<input type="hidden" id="hdnAllowDays" name="hdnAllowDays"/>
		<div  class="easyui-paddingbfpx">
			<div  class="easyui-paddingbfpx" id="frmPcsCalendarFuntKeyIds">
				<input type="hidden" id="factory" name="factory" value="${requestScope.factId}"  ></input>
				<input type="hidden" id="section" name="section" value="${requestScope.sectId}"  ></input>
				<input type="hidden" id="cell" name="cell" value="${requestScope.cellId}" ></input>
				<input type="hidden" id="machine" name="machine" value="${requestScope.mchId}"  ></input>
				<input type="hidden" id="flid" name="flid" value="${requestScope.flid}"  ></input>
			</div>
			<div id="pcsCalendarfunLocation" style="padding-left: px;" ></div>
		</div>
	

	<div style="">
		<label class="mandatory-lbl" >Line</label>
		<label style="padding-left: 190px;" >Equipment</label>
		<label class="mandatory-lbl" style="padding-left: 150px;" >Date</label>
		<label style="padding-left: 80px;" >Shift</label>
	</div>
    <div class="easyui-paddingbfpx" style="" >
		<input type="text" id="cmbCellid" name="cmbCellid" class="easyui-combobox" value="${requestScope.cellId}" style="width: 200px;"/>
		<span style="padding-left: 1%;">
	    	<input type="text" id="cmbCalMachineid" name="cmbCalMachineid" class="easyui-combobox" value="${requestScope.mchId}" style="width: 200px;"/>
	    </span>	    								
		<span style="padding-left: 1%;">
	    	<input id="dteEntryDate" name="dteEntryDate" class="easyui-text" readonly="readonly" required="true" style="width: 90px;" value="${requestScope.date}"/>&nbsp;
	    </span>	    
    	<span style="padding-left: 1%;">
	    	<input type="text" class="easyui-combobox" id="cmbShiftid" name="cmbShiftid" readonly="readonly"  style="width: 100px;"  value="${requestScope.shift}"/>	    	
	    </span>	 
    </div>
     <div class="easyui-paddingbfpx" style="margin-top: 4px" >
	    <span style="padding-left: %;">
	    	<label class="mandatory-lbl"> <span style="padding-left: %;"> Double click the Shift to PCS Entry </span> </label>
		</span>
		<span style="padding-left: 5%;">
    		<input type="text" value="PCS Status Calendar with OEE" disabled="disabled" style="border:1px solid black;  font-size:11px ; width:220px;height:20px;color:black;background-color:#fae0aa;font-weight:bold;text-align:center; " />
    	</span>
		
		<span style="padding-left: 5%;">
		    	<input type="button" id="btnViewCalendar" name="btnViewCalendar" class="easyui-button"  value="   View Calendar    " style="width:15%\9"/>
		</span>
		<span style="padding-left: 1%;">
		    <input type="button" id="btnViewPCS" name="btnViewPCS" class="easyui-button"  value="Production Entry" style="width:15%\9;"/>
		</span>	
    </div>
    <table align="left" style="float: left;padding-right: px;margin-top:1px;"> 
    <tr> 
    <td>
		<div style="" align="left">
			<div style="float: center;padding-right: 40px;margin-top:5px;">
					<table id="monthGrid" width="300px" style="float: center;"></table> </div>
<!--					<div id="monthPager" style="float: center;">	</div> -->				
		</div>
	</td>
	<td valign="middle" align="left" style="margin-left: 1%;">
			<div style="padding-top: 5%;">						
				<input type="text" value="No Entry" disabled="disabled" style="width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#ffe1f0;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="PCS Completed" disabled="disabled" style="margin-top:12px; width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#c0ffc0;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="QA Completed" disabled="disabled" style="margin-top:12px;width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#00a600;font-weight:bold;text-align:center; " />
			</div>			
			<div>
				<input type="text" value="Incomplete" disabled="disabled" style="margin-top:12px;width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#FC6A6A;font-weight:bold;text-align:center; " />
			</div>			
			<div>
				<input type="text" value="Holiday" disabled="disabled" style="margin-top:12px;width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#9d99f2;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="Weekly Off" disabled="disabled" style="margin-top:12px;width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#aae0fa;font-weight:bold;text-align:center; " />
			</div>
			<div>
				<input type="text" value="Fully No Plan" disabled="disabled" style="margin-top:12px;width:150px;height:25px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#808080;font-weight:bold;text-align:center; " />
			</div>
			
	</td>
	</tr>
	</table>       

	  
</form>
</div>
</div>