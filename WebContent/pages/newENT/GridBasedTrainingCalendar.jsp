<script type="text/javascript">
	jQuery(document).ready(
			function() {
				initialiseForm('frmGrdBsdTrgCalMod');
				jQuery("#submitForm").val("frmGrdBsdTrgCalMod");

				 
				var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
				
				formatDateBox('dteFromDate', 'DD-MMM-YYYY');
				formatDateBox("dteToDate", "DD-MMM-YYYY");
				var url = "trngcalendarGridModify_input.gbtc?";
				var funclocn = "functionalLoc.ntrc";
				if (flid.length <= 0) {
					loadFunctionalLocation("GrdBsdTrgCallFunctionalLoc", funclocn,
							"GrdBsdTrgCallFunctionalLoc", "frmGrdBsdTrgCalMod", "&flid=");
				} else {
					loadFunctionalLocation("GrdBsdTrgCallFunctionalLoc", funclocn,
							"GrdBsdTrgCallFunctionalLoc", "frmGrdBsdTrgCalMod", "&flid="
									+ flid);

				}
				fillComboBox("frmGrdBsdTrgCalMod", "cmbSectionid",
						"sectionCombo.commonFilter");
				fillComboBox("frmGrdBsdTrgCalMod", "cmbCellid",
						"cellCombo.commonFilter");
				fillComboBox("frmGrdBsdTrgCalMod", "cmbGdstTradeId",
						"trade.commonFilter");
				fillComboBox("frmGrdBsdTrgCalMod", "cmbGdstUniquePosition",
						"roleMst.commonFilter");
				var factId = jQuery("#frmGrdBsdTrgCalMod input[id='factory']").val();
				var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
				var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
				var location = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
				var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
				 var fromdate = jQuery('#dteFromDate').datebox('getValue');
				var todate = jQuery('#dteToDate').datebox('getValue');
				var uniqPostn=jQuery('#cmbGdstUniquePosition').combobox('getValue');
				var tradeId=jQuery('#cmbGdstTradeId').combobox('getValue');
				var flid2=jQuery("#hdnflid").val();
		var		 filterString ="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"&todate="+todate+"&uniqPostn="+uniqPostn+"&tradeId="+tradeId;
	// alert("filterString    "+filterString);
	 
		//frmGrdBsdTrgCalMod_FuntLocHierarchy_SuccessCallBack(keyIds);
		
		// var filterString="?q=2&flid="+flid;
		 viewGrid(url, filterString);
		///	jQuery("#trgCalendarGrid").jqGrid('setFrozenColumns');

			});

	function frmGrdBsdTrgCalMod_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		

			var flid = keyIds.flId;
			jQuery("#hdnflid").val(flid);
			var locnid=keyIds.locnId;
			var sectid=keyIds.sectId;
			var cellid=keyIds.cellId;
			 setFunctionalLocWidth('frmGrdBsdTrgCalMod','550px');
			
	}
	
	var factId = jQuery("#frmGrdBsdTrgCalMod input[id='factory']").val();
	var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
	var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
	var machId = jQuery("#frmGrdBsdTrgCalMod input[id='machine']").val();
	var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
	var hdnflid = jQuery("#hdnflid").val();
	var types = jQuery("#hdnRefDoctype").val();
	var docId = jQuery("#hdnRefDocId").val();
	var remarks = jQuery("#hdnremarks").val();
	if (hdnflid != null && hdnflid.trim().length > 0)
		flid = hdnflid;

	var dataStr = "&factId=" + factId + "&sectionId=" + sectionId + "&cellId="
			+ cellId + "&machId=" + machId + "&flid=" + flid;
	//alert(dataStr+"dataStr");


	// processGridnew("trngcalendarGridEntry_input.gbtc?","q=2","trgCalendarGrid","pager","","","","");
	var factId = jQuery("#frmGrdBsdTrgCalMod input[id='factory']").val();

	var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
	var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
	//alert(sectionId+" sectionid");
	if (sectionId != null) {
		jQuery('#cmbSectionid').val(sectionId);
	}

	if (cellId != null) {
		jQuery('#cmbCellid').val(cellId);

	}

	function viewGrid(url, filterString) {
		

		//var url = "trngcalendarGridModify_input.gbtc?";
		//filterString="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"&todate="+todate+"&uniqPostn="+uniqPostn+"&tradeId="+tradeId+"&flid="+hdnflid;
		// alert("filterString    "+filterString);

		processGridnew(url, filterString, "trgCalendarGrid", "pager", "", "","");

	}
	jQuery("#btnAddRow").click(function() {
		var row = jQuery("#trgCalendarGrid").jqGrid("getDataIDs");
		addRow(row);
	});

	jQuery("#btnView").click(
			function() {
				
				var factId = jQuery("#frmGrdBsdTrgCalMod input[id='factory']").val();
				var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
				var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();location
				var location = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
				var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
				var fromdate = jQuery('#dteFromDate').datebox('getValue');
				var todate = jQuery('#dteToDate').datebox('getValue');
				var uniqPostn=jQuery('#cmbGdstUniquePosition').combobox('getValue');
				var tradeId=jQuery('#cmbGdstTradeId').combobox('getValue');
				
				//alert(uniqPostn+'factId'+factId+"  "+sectionId+"  "+cellId+"   "+location+"  "+flid+"  "+fromdate+"  "+todate+"  "+tradeId);

				/*var sectionId = jQuery('#cmbSectionid').combobox('getValue');
				var cellId = jQuery('#cmbCellid').combobox('getValue');
				var fromdate = jQuery('#dteFromDate').datebox('getValue');
				var todate = jQuery('#dteToDate').datebox('getValue');
				*/
				//alert(flid);
				var ds = "q=2&mode=modify&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"&todate="+todate+"&uniqPostn="+uniqPostn+"&tradeId="+tradeId+"&flid="+flid;
				processGridnew("trngcalendarGridModify_input.gbtc", ds,"trgCalendarGrid", "pager", "", "", "","onLoadComplete");

			});

	function addRow(row) {
		if (row == null || row == '' || parseInt(row) <= 0) {
			var emptyItem = [ {
				slNoslNo : " ",
				Select : " ",
				TrainingNo : " ",
				Sectionid : " ",
				CellId : " ",
				Flid : " ",
				dteOfCreation : " ",
				cmbAnchoredBy : " ",
				AnchorByID : " ",
				Title : " ",
				TopicId : " ",
				TopicCategory : " ",
				TopicCategoryId : " ",
				identifiedThrg : " ",
				identifiedThrgId : " ",
				functions : " ",
				functionId : " ",

				Venue : " ",
				VenueId : " ",
				TrainingType : " ",
				TrainingTypeId : " ",

				TrainingDate : " ",
				permittedStrength : " ",
				Duration : " ",
				AssementReq : " ",
				MaterialReady : " ",
				MaterialReadyId : " ",
				MarksReq : " ",
				MarksReqId : " ",
				UniqueposAdd : " ",
				EmployeeAdd : " ",

				AssementCom : " ",
				AssementComID : " ",
				trnCompleted : " ",
				trnCompletedId : " ",
				CompletedOn : " ",
				CompletedBy : " ",
				CompletedById : " ",
				Remark : " ",
				EmployeeAtte : "",
				Ratings : "",
				RatingsId : "",
				Feedback : " ",
				FileManager : " ",
				PlannedEmployee : " ",
				AttendedEmployee : " ",
				Adherance : " "
			} ];
			jQuery("#trgCalendarGrid").jqGrid('addRowData', 1, emptyItem[0]);
		} else {
			for (var i = 0; i < row.length; i++)
				lastRow = row[i];
			var emptyItem = [ {
				hdnKzbnKeyid : " ",
				dteKzbnDate : " ",
				cmbKzbnSuggestedby : " ",
				txtKzbnKaizen : " ",
				cmbkzbnBenefit : " "
			} ];
			jQuery("#trgCalendarGrid").jqGrid('addRowData',
					parseInt(lastRow) + 1, emptyItem[0]);
		}
	}

	jQuery("#btnDelete").click(function() {

		removeRecord();
		
	});

	function removeRecord(keyid) { 
		var Abnrow = jQuery("#trgCalendarGrid").jqGrid('getDataIDs');
		for (var i = 0; i < Abnrow.length; i++) {
			var rowid = Abnrow[i];
			if (jQuery('#jqg_trgCalendarGrid_' + Abnrow[i]).is(':checked') == true) {

				keyid = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid, "txtEtcmKeyid");
				var trgCompl=jQuery('#cmbEtcmChkCompleted_trgCalendarGrid_'+rowid).combobox('getValue'); 
				var trgComId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid, "TrainingCompId");

if(trgCompl=="N"){
				if (keyid != null && keyid != 'undefined' && keyid != undefined
						&& keyid != "" && keyid.trim().length > 0) {
					var r = confirm("Do You Want To Delete?");
					if (r == true) {
						processAjaxCalls("calendar_Delete.gbtc", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
					} else {
						return false;
					}
				} else {
					var r = confirm("Do You Want To Remove Row?");
					if (r == true)
						jQuery("#trgCalendarGrid").trigger("reloadGrid");
					else
						return false;
				}
}else{
	alert("Trainig already Completed U can not Delete it.");
	return false;
}
			}
		}
	}
function remove_successCallBack(result){
	alert("Data Delete successfully");
	jQuery("#trgCalendarGrid").trigger("reloadGrid");

}
	function trgCalendarGridbtnEmployeeAdd_onClick(result) {
		var rowid = result.rowId;
		var btnid = result.btnId;
		//var refDocId = jQuery('#hdnCalKeyId').val();//
		 var refDocId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmKeyid");
		
		 var tradeId = jQuery("#cmbEtcmTrainingfunction_trgCalendarGrid_"+rowid).combobox("getValue");
		
		 var trngType = jQuery("#chkEtcmGeneral_trgCalendarGrid_"+rowid).combobox("getValue");

		/* var tradeId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"cmbEtcmTrainingfunction_trgCalendarGrid_" + rowid);
		jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,	"cmbEtcmTrainingfunction");
		//  	alert(refDocId+" rowid "+rowid);
		var tradeId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,
				"cmbEtcmTrainingfunction_trgCalendarGrid_" + rowid);// getFieldValue("cmbEtcmTrainingfunction__trgCalendarGrid_"+rowid); 
		 */
		 var facultyId = getFieldValue("cmbEtcfFacultyId_trgCalendarGrid_"+ rowid);	

		var flid = jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmFlid");
		
		var factId = jQuery("#frmGrdBsdTrgCalMod input[id='pbu']").val(); 
		// CHANGING SECTION ID
	//	var sectionId = jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmSectionId");	
		var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
		
		// var keyId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowId,"txtEtcmKeyid");

		var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
		
		var locaioniId = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
		 
		/* 	var myGrid = jQuery('#trgCalendarGrid');
		  var selRowId = myGrid.jqGrid('getGridParam', 'selrow');
		  var TradeId = myGrid.jqGrid('getCell', selRowId, 'cmbEtcmTrainingfunction_'+myGrid+'_'+selRowId);
		 */
		//alert(tradeId + "tradeid" + rowid);
		//var mode="view";
		var ds = "&flid=" + flid + "&SectionId=" + sectionId + "&refDocId="+ refDocId + "&LocationId=" + locaioniId + "&cellId=" + cellId+"&trngType="+trngType;
		//alert(ds + "ds ds" + refDocId);
		if (refDocId.length >= 1 || refDocId == null || refDocId == '') {

			var gridval = getGridSelectArray('trgCalendarGrid');
			// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
			if (gridval != "") {
				//EmployeeAdd_input.gbtc
				var calendarDetails = gridval;//+'&flId='+flId+'&sectionId='+sectionId+"&docId="+docId+"&types="+types+"&remarks="+remarks;

				LoadPopUp("divAddEmployee", "EmployeeAdd_input.gbtc?q=2"+ ds, true, "93%", "93%", "1%", "1%", "", "", false,
						false);

			}

			// loadPopup("Employee Addition","","Calendar","","","");
		} else {

			alert('Add Faculty / Session / Unique Position');
			//	LoadPopUp("divAddEmployee","EmployeeAdd_input.gbtc?ds="+ds, true,"93%","93%","1%","1%", "","",false,false);
		}

	}
	function trgCalendarGridbtnEmployeeAttend_onClick(result) {

		var rowid = result.rowId;
        // alert(123);
		var refDocId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmKeyid");
		
			
		var factId = jQuery("#frmGrdBsdTrgCalMod input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
	
		var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
		
		var locaionId = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
		
		
		var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
		
		var gridval = getGridSelectArray('trgCalendarGrid');
		// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
		//saveForm('frmGrdBsdTrgCalMod',	'gridBasedCalendar_save.gbtc?calendarDetails='+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId);
		
		if(refDocId!=null || refDocId!=""||refDocId!="undefined"){
			
		processAjaxCalls("gridBasedCalendar_save.gbtc","calendarDetails="+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId+"&TrnCalId="+refDocId,"updateSuccessCalData","");

		}
		
		/* var markReqrd = jQuery("#cmbEtcmMarksBased_trgCalendarGrid_"+rowid).combobox("getValue");
		var assmCompl = jQuery("#AssementCompleted_trgCalendarGrid_"+rowid).combobox("getValue");
		var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
		var factId = jQuery("#frmGrdBsdTrgCalMod input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
		var locaioniId = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
		var ds = "&TraKeyid="+refDocId+"&markReqrd="+markReqrd+"&assmCompl="+assmCompl+"&flid="+flid+"&locaioniId="+locaioniId+"&sectionId="+sectionId+"&cellId="+cellId;
		 */

	}
function updateSuccessCalData(result){
	var trgCalId = result.successData.keyId;
	//jQuery("#trgCalendarGrid").trigger("reloadGrid");
	//var rowid=result.rowId;
	var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam","selrow");
	 var refDocId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmKeyid");
	 var flid =jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmFlid"); 
	 var topicId = jQuery("#cmbEtcmTopicid_trgCalendarGrid_"+rowid).combobox("getValue");	

	 var markReqrd = jQuery("#cmbEtcmMarksBased_trgCalendarGrid_"+rowid).combobox("getValue");		

	 var assmCompl = jQuery("#cmbEtcmTempfield6_trgCalendarGrid_"+rowid).combobox("getValue");	
		
     var locationId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmLocationId");
    
	 var sectionId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmSectionId");

	 
	// alert(flid +" topicId "+topicId +" markReqrd"+markReqrd+"assmCompl"+assmCompl);
var ds="&mode=modify&TraKeyid="+refDocId+"&flid="+flid+"&topicId="+topicId+"&markReqrd="+markReqrd+"&assmCompl="+assmCompl+"&locationId="+locationId+"&sectionId="+sectionId;
	LoadPopUp("divEmployeeAtt", "EmpTrainingAtt_input.gbtc?q=2"+ ds,true, "80%", "80%", "1%", "1%", "", "", false, false);
}
	function trgCalendarGridbtnUniqFactAdd_onClick(result) {

		var rowid = result.rowId;
		var btnid = result.btnId;
		
		//var refDocId = jQuery('#hdnCalKeyId').val();//jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmKeyid");
		 var refDocId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmKeyid");
		

		var factId = jQuery("#frmGrdBsdTrgCalMod input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
		var locaionId = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
		var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
		 var trngType = jQuery("#chkEtcmGeneral_trgCalendarGrid_"+rowid).combobox("getValue");

		if (refDocId.length == 1 || refDocId == null || refDocId == '') {

			var gridval = getGridSelectArray('trgCalendarGrid');
			// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
			if (gridval != "") {
				//	alert(gridval +"gridval23");

				var calendarDetails = gridval;//+'&flId='+flId+'&sectionId='+sectionId+"&docId="+docId+"&types="+types+"&remarks="+remarks;
			
				saveForm('frmGrdBsdTrgCalMod',
						'gridBasedCalendar_save.gbtc?calendarDetails='
								+ calendarDetails + "&fliId=" + flid
								+ "&locaionId=" + locaionId + "&sectionId="
								+ sectionId + "&cellId=" + cellId);

			}
		}

		else {

			LoadPopUp("divUniquePositionFaculty","trngCalUniquePosition_input.gbtc?&trgCalId="+ refDocId+ "&fliId="+ flid
					+ "&locaionId=" + locaionId + "&sectionId="	+ sectionId + "&cellId=" + cellId+"&trngType="+trngType, true, "93%", "93%", "1%", "1%", "", "",
					false, false);

			/* 
			LoadPopUp("divUniquePositionFaculty","trngCalUniquePosition_input.gbtc","?q=2&trgCalId="+ trgCalId+ "&fliId=" + flid
					+ "&locaionId=" + locaionId + "&sectionId="
					+ sectionId + "&cellId=" + cellId, true, "93%", "93%", "1%", "1%", false,false);//,false,false);
		 */
			//	LoadPopUp("divUnique Position Faculty","EmployeeAdd_input.gbtc", true,"93%","93%","1%","1%", "","",false,false);

		}

	}

	function frmGrdBsdTrgCalMod_successsCallback(result) {
		var trgCalId = result.successData.keyId;
		var flid=jQuery('#hdnflid').val();

		var mode = jQuery('#hdnMode').val();
	//	alert(mode +"  "+flid );
		processGridnew("trngcalendarGridModify_input.gbtc", "?q=2&mode="+mode+"&flid="+flid, "trgCalendarGrid", "pager", "",
				"", "", "onLoadComplete");
	}
	

	function onLoadComplete(id) {
		var rowid = jQuery("#trgCalendarGrid").jqGrid('getDataIDs');
		if (rowid.length > 0) {
			/* jQuery('input:checkbox[name=jqg_trgCalendarGrid_' + rowid + ']')
					.attr('checked', true); */
			var dateOfTrnCreted = "txtEtcmKeyid_trgCalendarGrid_" + rowid;
		/* 	jQuery('#trgCalendarGrid').jqGrid({
				editable : true
			});//setGridParam({cellEdit:true});				
			//jQuery("#gfrc1").jqGrid('setFrozenColumns');
			var myGrid = jQuery('#trgCalendarGrid'), selRowId = myGrid.jqGrid(
					'getGridParam', 'selrow'), celValue = myGrid.jqGrid(
					'getCell', selRowId, 'trgCalendarGrid_txtEtcmKeyid_'
							+ rowId); */

			var rowId = jQuery('#trgCalendarGrid').jqGrid('getGridParam',
					'selrow');
			var rowData = jQuery('#trgCalendarGrid').getRowData(rowId);
			var colData = rowData['trgCalendarGrid_txtEtcmKeyid_' + rowId];
			 var refDocId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmKeyid");

			//trngCalUniquePosition_input.gbtc?q=2
			LoadPopUp("divUniquePositionFaculty","trngCalUniquePosition_input.gbtc","?q=2&trgCalId="+ refDocId+ "&fliId=" + flid
					+ "&locaionId=" + locaionId + "&sectionId="
					+ sectionId + "&cellId=" + cellId, true, "93%", "93%", "1%", "1%", false,false);//,false,false);
		}
	}
	function trgCalendarGrid_selectRow(rowId) {
		//alert(1236);
		var jqGridId = "trgCalendarGrid"; //txtEtcmCreatedDateTime_trgCalendarGrid_1
		 var refDocId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowId,"txtEtcmKeyid");
		 numericTextBox("txtEtcmPermittedStrength_trgCalendarGrid" + "_" + rowId);
  		 numericTextBox("txtEtcmMaxDuration_trgCalendarGrid" + "_" + rowId);
		// disableField('frmGrdBsdTrgCalMod',"dteEtcmCreatedDateTime_trgCalendarGrid" + "_" + rowId);
		// disableField('frmGrdBsdTrgCalMod',"dteEtcmCreatedDateTime_trgCalendarGrid"+"_"+ rowId);

			var dateOfTrnCreted = "dteEtcmCreatedDateTime_trgCalendarGrid_" + rowId;
			readOnlyFields("cmbEtcmChkCompleted_trgCalendarGrid_"+rowId);
			disableField('frmGrdBsdTrgCalMod',"cmbEtcmTempfield6_trgCalendarGrid_"+ rowId);
			//disableField('frmGrdBsdTrgCalMod',"cmbEtcmChkCompleted_trgCalendarGrid_"+rowId);
			disableField('frmGrdBsdTrgCalMod',"dteEtcmCompletedDate_trgCalendarGrid_"+ rowId);
			disableField('frmGrdBsdTrgCalMod',"cmbEtcmCompletedBy_trgCalendarGrid_"+ rowId);
			disableField('frmGrdBsdTrgCalMod',"cmbEtcmRating_trgCalendarGrid_" +rowId);
			//disableField('frmGrdBsdTrgCalMod',"#txtEtcmComments_trgCalendarGrid_"+rowId);
			jQuery("#txtEtcmComments_trgCalendarGrid_"+rowId).attr('readonly',true); 
		var dateOfTrnCreted = "dteEtcmCreatedDateTime_trgCalendarGrid_" + rowId;
		if(refDocId==null||refDocId==""||refDocId=="undefined"){
		fillWithCurrentDate(dateOfTrnCreted);
		}
		else{
			trgCompletedCheck(rowId);
			}
		}

			function trgCompletedCheck(rowId){
				 var keyId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowId,"txtEtcmKeyid");

				// var keyId=jQuery('#trgCalendarGrid_txtEtcmKeyid_'+rowId);
			   
			
				 
				if(keyId!=null&keyId.length!=0)
					{  
					 var assmReq= jQuery('#cmbEtcmAssessmentReq_trgCalendarGrid_'+rowId).combobox('getValue');
					 var assmComp=jQuery("#cmbEtcmTempfield6_trgCalendarGrid_"+rowId).combobox('getValue');

				
					var Rating=jQuery("#cmbEtcmRating_trgCalendarGrid_"+rowId).combobox('getValue');
				/* 	if(assmReq=="Y")
					{				alert(assmReq+"assesscom"+assmComp);

						if(assmComp=="N")
							{				alert(assmReq+"assesscom"+assmComp);

							   popupCommonErrorMsg("Please complete the Assessment!!");
							   enableField('frmGrdBsdTrgCalMod',"cmbEtcmTempfield6_trgCalendarGrid_"+ rowId);

							   return false;
							}
					} */	
					
				    processAjaxCalls('chkcompleted.gbtc','&keyid='+keyId,'Chkcompleted_onsuccessCallBack','Chkcompleted_onerrorCallBack');
					}
	 
				else{
					
					popupCommonErrorMsg("Without Enter Training Calendar, Unable to complete!!!!?");
					return false;
				    } 
				
				var AnchoredBy=jQuery("#cmbEtcmAnchoredby_trgCalendarGrid_"+rowId).combobox("getValue");
			//	alert(AnchoredBy);
			//	alert(Rating);
				  if(AnchoredBy=="DHR" || AnchoredBy=="Corp.HR" || AnchoredBy=="Unit HR"){
					  if(Rating.length==0){
						   popupCommonErrorMsg("Please Enter the Rating");
			                return false;
					  }
				  }
				
				
			
		}
			
			function Chkcompleted_onsuccessCallBack(result)
			{ 
				
			
			   var chkvalue=result.iscomplete; 
			   var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam","selrow");

			 //  alert(chkvalue +"  chkvalue  "+ rowid);
			   var completedby=jQuery("#cmbEtcmCompletedBy_trgCalendarGrid_"+rowid).combobox('getValue');
			   if(chkvalue=="N")
				   {
				   
				   jQuery('#cmbEtcmChkCompleted_trgCalendarGrid_'+rowid).combobox('setValue','N');
				    // popupCommonErrorMsg("Training is not Completed");
				     return false;
				   }
			   else{
				 
				   enableFields("cmbEtcmTempfield6_trgCalendarGrid_"+rowid);
				   enableFields("cmbEtcmChkCompleted_trgCalendarGrid_"+rowid);
				   //enableFormFields('frmGrdBsdTrgCalMod',"cmbEtcmChkCompleted_trgCalendarGrid_"+rowid);
				   //jQuery("#cmbEtcmChkCompleted_trgCalendarGrid_"+rowid).combobox('readonly',false);
					enableFields("dteEtcmCompletedDate_trgCalendarGrid_"+ rowid);
					enableFields("cmbEtcmCompletedBy_trgCalendarGrid_"+ rowid);
					enableFields("cmbEtcmRating_trgCalendarGrid_" +rowid);
					enableFields("txtEtcmComments_trgCalendarGrid_" +rowid);
//alert(1);
					//disableField('frmGrdBsdTrgCalMod',"#txtEtcmComments_trgCalendarGrid_"+rowId);
					//jQuery("#txtEtcmComments_trgCalendarGrid_"+rowid).attr('disabled',false); 

			
				    
			   }
			   jQuery("#cmbEtcmChkCompleted_trgCalendarGrid_"+rowid).combobox({  	   
					onSelect:function(recordid)
						{ 
						var assmReq= jQuery('#cmbEtcmAssessmentReq_trgCalendarGrid_'+rowid).combobox('getValue');
						 var assmComp=jQuery("#cmbEtcmTempfield6_trgCalendarGrid_"+rowid).combobox('getValue');

					
						var Rating=jQuery("#cmbEtcmRating_trgCalendarGrid_"+rowid).combobox('getValue');
					 	if(assmReq=="Y")
						{				

							if(assmComp=="N")
								{				
								   jQuery('#cmbEtcmChkCompleted_trgCalendarGrid_'+rowid).combobox('setValue','N');

								   popupCommonErrorMsg("Please complete the Assessment!!");

								   return false;
								}
							//jQuery('#cmbEtcmCompletedBy_trgCalendarGrid_'+rowid).combobox('setValue','Y');
							else{
								jQuery('#cmbEtcmChkCompleted_trgCalendarGrid_'+rowid).combobox('setValue','Y');
							}
							   enableFields("dteEtcmCompletedDate_trgCalendarGrid_"+rowid);
							   fillWithCurrentDate("dteEtcmCompletedDate_trgCalendarGrid_"+rowid);
							   if(completedby.length==1)
								   {
								   jQuery("#cmbEtcmCompletedBy_trgCalendarGrid_"+rowid).combobox('setValue','');
								   }
							   
							   var crntUser=jQuery("#hdnLoginUser").val();
							   var flid = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmFlid");
							  // alert(flid +" crntUser " +crntUser);
							   fillComboBox("frmGrdBsdTrgCalMod","#cmbEtcmCompletedBy_trgCalendarGrid_"+rowid,"employee.commonFilter?&flid="+flid);
							   jQuery("#CompletedById_trgCalendarGrid_"+rowid).combobox('setValue',crntUser);

							   /*
							   enableFields("#cmbEtcmCompletedBy_trgCalendarGrid_"+rowid);
							   fillComboBox("frmGrdBsdTrgCalMod","#cmbEtcmCompletedBy_trgCalendarGrid_"+rowid,"employee.commonFilter?");
							  */
						} 
					 	else{
					 		 enableFields("dteEtcmCompletedDate_trgCalendarGrid_"+rowid);
							   fillWithCurrentDate("dteEtcmCompletedDate_trgCalendarGrid_"+rowid);

							   var crntUser=jQuery("#hdnLoginUser").val();
							   var flid = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmFlid");
							  // alert(flid +" crntUser " +crntUser);
							   jQuery("#CompletedById_trgCalendarGrid_"+rowid).combobox('setValue',crntUser);

							//   fillComboBox("frmGrdBsdTrgCalMod","#cmbEtcmCompletedBy_trgCalendarGrid_"+rowid,"employee.commonFilter?&flid="+flid);

					 	}
						}
						
						});
			   
			   jQuery("#dteEtcmCompletedDate_trgCalendarGrid_"+rowid).datebox({  	   
					onSelect:function(recordid)
						{ 
						
						var currdate=jQuery("#dteEtcmCalendarDate_trgCalendarGrid_"+rowid).datebox('getValue');
						var approvalDate =jQuery("#dteEtcmCompletedDate_trgCalendarGrid_"+rowid).datebox('getValue');
						var currentDate = getServerDateTime();
					if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
							{
				

						/*if(convertStringToDate(sysdate) < convertStringToDate(approvalDate))
						{  

						      popupCommonErrorMsg('Should Not Enter the Future Date');
						       fillWithCurrentDate(dateCtrl);
								return false;
						}*/
						//else{
							if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
								{
								popupCommonErrorMsg('Should Not Exceed the Calendar Date');
								fillWithCurrentDate(dateCtrl);
								return false
							//	}	
						}
							}	
						} 
					}); 
			   
			   
			  /*  jQuery("#dteSessiondate").datebox({  	   
					onSelect:function(recordid)
						{ 
						var sysdate=jQuery("#hdncurrentdate").val();
						var currdate=jQuery("#dteEtcmCalendarDate_trgCalendarGrid_"+rowid).datebox('getValue');
						var approvalDate =jQuery("#dteSessiondate").datebox('getValue');
						var currentDate = getServerDateTime();
					if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
							{
				

						if(convertStringToDate(sysdate) < convertStringToDate(approvalDate))
						{  
						}
						else{
							if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
								{
								popupCommonErrorMsg('Should Not Exceed the Calendar Date');
								fillWithCurrentDate(dateCtrl);
								return false
								}
							
						}
						
						
							}
						    	
						} 
					}); */ 
					
			   enableFields("cmbEtcmChkCompleted_trgCalendarGrid_"+rowid);
					//alert(2);
			}
			//----------- VIGNESH ALTERING SAVE
			jQuery("#btnSave").click(function () {

    var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam", "selrow");

    var refDocId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid, "txtEtcmKeyid");

    var factId    = jQuery("#frmGrdBsdTrgCalMod input[id='pbu']").val();
    var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
    var cellId    = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
    var locaionId = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
    var flid      = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();

    // JSON from grid
    var gridval = getGridSelectArray('trgCalendarGrid');

    // 🔴 IMPORTANT: encode JSON so [, ], {, }, spaces, etc. are safe in the URL
    var encodedCalendar = encodeURIComponent(gridval || "");

    // Also safely encode the other dynamic params (in case they contain spaces, etc.)
    var url = "gridBasedCalendar_save.gbtc"
        + "?calendarDetails=" + encodedCalendar
        + "&fliId="    + encodeURIComponent(flid      || "")
        + "&locaionId="+ encodeURIComponent(locaionId || "")
        + "&sectionId="+ encodeURIComponent(sectionId || "")
        + "&cellId="   + encodeURIComponent(cellId    || "")
        + "&TrnCalId=" + encodeURIComponent(refDocId  || "");

    saveForm("frmGrdBsdTrgCalMod", url);
});

// 		jQuery("#btnSave").click(function(){
			
// 			var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam","selrow");

// 			var refDocId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmKeyid");
			
// 			var factId = jQuery("#frmGrdBsdTrgCalMod input[id='pbu']").val();
// 			var sectionId = jQuery("#frmGrdBsdTrgCalMod input[id='section']").val();
// 			var cellId = jQuery("#frmGrdBsdTrgCalMod input[id='cell']").val();
// 			var locaionId = jQuery("#frmGrdBsdTrgCalMod input[id='location']").val();
// 			var flid = jQuery("#frmGrdBsdTrgCalMod input[id='flid']").val();
			
// 			var gridval = getGridSelectArray('trgCalendarGrid');
			
// 			// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
// 			//saveForm('frmGrdBsdTrgCalMod',	'gridBasedCalendar_save.gbtc?calendarDetails='+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId);
			
// 			/* saveForm('frmGrdBsdTrgCalMod','gridBasedCalendar_save.gbtc?calendarDetails='+ calendarDetails + "&fliId=" + flid
// 								+ "&locaionId=" + locaionId + "&sectionId="
// 								+ sectionId + "&cellId=" + cellId);	 */
// 								saveForm("frmGrdBsdTrgCalMod","gridBasedCalendar_save.gbtc?calendarDetails="+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId+"&TrnCalId="+refDocId);

			
// 	});

		//----------- VIGNESH ALTERING SAVE
</script>
<body>
	<Form id="frmGrdBsdTrgCalMod" action="">
		<div id="wrapper" style="width: 80%;">
			<table>
				<tr>
					<td colspan="3">
						<div id="frmGrdBsdTrgCalModFuntKeyIds">
							<input type="hidden" id="factory" name="cmbTraFactoryid" value=""></input>
							<input type="hidden" id="section" name="cmbTraSectionid" value=""></input>
							<input type="hidden" id="flid" name="cmbEtcmFlid"
								value="${requestScope.entTlTragcalmst.etcmFlid}"></input> <input
								type="hidden" id="location" name="cmbEtcmLocation"
								value="${requestScope.entTlTragcalmst.etcmLocation}"></input> <input
								type="hidden" id="sbu" name="cmbTraSbu" value=""></input> <input
								type="hidden" id="pbu" name="cmbTraPbu" value=""></input> <input
								type="hidden" id="section" name="cmbEtcmDmt"
								value="${requestScope.entTlTragcalmst.etcmDmt}"></input> <input
								type="hidden" id="cell" name="cmbEtcmJh"
								value="${requestScope.entTlTragcalmst.etcmJh}"></input> <input
								type="hidden" id="cell" name="cmbTraCellid" value=""></input>
						</div>
						<div id="GrdBsdTrgCallFunctionalLoc"
							style="width: 70%; margin-left: 20px;"></div>
					</td>
				</tr>
			</table>
			<div style="margin-top: 20px;">
				<span style="margin-left: 20px; margin-top: 20px;"> <label
					id="" class="">Unique Position</label>
				</span> <span style="margin-left: 170px; margin-top: 20px;"> <label
					id="" class="">Function</label>
				</span> <Span style="margin-left: 110px; margin-top: 20px;"> <label
					id="lblFromDate">From Date</label>
				</Span> <Span style="margin-left: 70px;"> <label id="lblToDate">To
						Date</label>
				</Span>
			</div>
			<div style="width: 100%%";>
				<span style="margin-left: 20px;"> <input
					id="cmbGdstUniquePosition" name="cmbGdstUniquePosition"
					class="easyui-combobox" style="width: 230px;">
				</span> <span style="margin-left: 20px;"> <input id="cmbGdstTradeId"
					name="cmbGdstTradeId" class="easyui-combobox" style="width: 150px;">
				</span> <Span style="margin-left: 20px;"> <input id="dteFromDate"
					name="dteFromDate" class="easyui-datebox" style="width: 100px;">
				</Span> <Span style="margin-left: 30px;"> <input id="dteToDate"
					name="dteToDate" class="easyui-datebox" style="width: 100px;">
				</Span>
			</div>

			<div id="dateBtn" style="margin-top: 15px; margin-left: 20px;">

				<Span style="margin-left: 30px;"> <input type="button"
					class="easyui-button" value="View" id="btnView"
					style="height: 30px" />
				</Span> 
				<!-- <Span style="margin-left: 30px;Display:none;"> <input type="button"
					class="easyui-button" value="Add Row" id="btnAddRow"
					style="height: 30px" />
				</Span> --> 
				<Span style="margin-left: 30px;"> <input type="button"
					class="easyui-button" value="Save" id="btnSave"
					style="height: 30px" />
				</Span> <Span style="margin-left: 30px;"> <input type="button"
					class="easyui-button" value="Delete" id="btnDelete"
					style="height: 30px" />
				</Span>
			</div>
			<div>

				<table id="trgCalendarGrid">
					<tr>
						<td></td>
					</tr>
				</table>
			</div>
			<div id="pager"></div>
		</div>
		<%-- ${requestScope.keyid} --%>
		<input type="hidden" id="hdnCalKeyId" name="hdnCalKeyId" value="">
				<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode} "> 
		<input type="hidden" id="hdnflid" name="hdnflid" value="">
		<input type="hidden" id="hdnLoginUser" name="hdnLoginUser" value="${requestScope.loginUser} ">
	</Form>
</body>
<html></html>