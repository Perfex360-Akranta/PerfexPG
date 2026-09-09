<script type="text/javascript">
	jQuery(document).ready(
			function() {
				initialiseForm('frmGrdBsdTrgCalView');
				jQuery("#submitForm").val("frmGrdBsdTrgCalView");

				 
				var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
				formatDateBox('dteFromDate', 'DD-MMM-YYYY');
				formatDateBox("dteToDate", "DD-MMM-YYYY");
				var url = "trngcalendarGridView_input.gbtc?";
				var funclocn = "functionalLoc.ntrc";
				if (flid.length <= 0) {
					loadFunctionalLocation("GrdBsdTrgCallFunctionalLoc", funclocn,
							"GrdBsdTrgCallFunctionalLoc", "frmGrdBsdTrgCalView", "&flid=");
				} else {
					loadFunctionalLocation("GrdBsdTrgCallFunctionalLoc", funclocn,
							"GrdBsdTrgCallFunctionalLoc", "frmGrdBsdTrgCalView", "&flid="
									+ flid);

				}
				fillComboBox("frmGrdBsdTrgCalView", "cmbSectionid",
						"sectionCombo.commonFilter");
				fillComboBox("frmGrdBsdTrgCalView", "cmbCellid",
						"cellCombo.commonFilter");
				fillComboBox("frmGrdBsdTrgCalView", "cmbGdstTradeId",
						"trade.commonFilter");
				fillComboBox("frmGrdBsdTrgCalView", "cmbGdstUniquePosition",
						"roleMst.commonFilter");
				/* var factId = jQuery("#frmGrdBsdTrgCalView input[id='factory']").val();
				var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
				var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
				var location = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
				var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
				 var fromdate = jQuery('#dteFromDate').datebox('getValue');
				var todate = jQuery('#dteToDate').datebox('getValue');
				var uniqPostn=jQuery('#cmbGdstUniquePosition').combobox('getValue');
				var tradeId=jQuery('#cmbGdstTradeId').combobox('getValue');
				var url = "trngcalendarGridView_input.gbtc?";
 */
				var		 filterString = "q=2";
				 viewGrid(url, filterString);

			});

	var factId = jQuery("#frmGrdBsdTrgCalView input[id='factory']").val();
	var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
	var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
	var machId = jQuery("#frmGrdBsdTrgCalView input[id='machine']").val();
	var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
	var hdnflid = jQuery("#hdnflid").val();
	var types = jQuery("#hdnRefDoctype").val();
	var docId = jQuery("#hdnRefDocId").val();
	var remarks = jQuery("#hdnremarks").val();
	if (hdnflid != null && hdnflid.trim().length > 0)
		flid = hdnflid;

	var dataStr = "&factId=" + factId + "&sectionId=" + sectionId + "&cellId="
			+ cellId + "&machId=" + machId + "&flid=" + flid;
	


	// processGridnew("trngcalendarGridEntry_input.gbtc?","q=2","trgCalendarGrid","pager","","","","");
	var factId = jQuery("#frmGrdBsdTrgCalView input[id='factory']").val();

	var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
	var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
	//alert(sectionId+" sectionid");
	if (sectionId != null) {
		jQuery('#cmbSectionid').val(sectionId);
	}

	if (cellId != null) {
		jQuery('#cmbCellid').val(cellId);

	}

	function viewGrid(url, filterString) {
		
		var factId = jQuery("#frmGrdBsdTrgCalView input[id='factory']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
		var location = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
		var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
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
		var ds = "q=2&flid="+flid+"&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"&todate="+todate+"&uniqPostn="+uniqPostn+"&tradeId="+tradeId;
		//alert(ds+"   ");

		processGridnew("trngcalendarGridView_input.gbtc", ds,"trgCalendarGrid", "pager", "", "", "","onLoadComplete");

	//	processGridnew(url, filterString, "trgCalendarGrid", "pager", "", "","");

	}
	jQuery("#btnAddRow").click(function() {
		var row = jQuery("#trgCalendarGrid").jqGrid("getDataIDs");
		addRow(row);
	});
	/* var mode=jQuery("#mode").val();
	  if(mode=="view"){ */
			//disableForm("frmGrdBsdTrgCalView");
			/* disableUIButton("btnFacultySave");
			disableUIButton("btnSessionsave");
			disableUIButton("btnUPSave");
			disableUIButton("btnUniqueAdd");
			disableUIButton("lblFilemgr");
			disableUIButton("btnEmployUnqDelete");
			disableUIButton("btnTopicsave");
			disableUIButton("btnFacultyAdd");	 */
	//  }
	jQuery("#btnView").click(
			function() {
				
				var factId = jQuery("#frmGrdBsdTrgCalView input[id='factory']").val();
				var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
				var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
				var location = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
				var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
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
				var ds = "q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"&todate="+todate+"&uniqPostn="+uniqPostn+"&tradeId="+tradeId;
				processGridnew("trngcalendarGridView_input.gbtc", ds,"trgCalendarGrid", "pager", "", "", "","onLoadComplete");

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

				keyid = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,
						"txtEtcmKeyid");

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

		var tradeId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"cmbEtcmTrainingfunction_trgCalendarGrid_" + rowid);
		jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,
				"cmbEtcmTrainingfunction");
		//  	alert(refDocId+" rowid "+rowid);
		var tradeId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,
				"cmbEtcmTrainingfunction_trgCalendarGrid_" + rowid);// getFieldValue("cmbEtcmTrainingfunction__trgCalendarGrid_"+rowid); 
		var facultyId = getFieldValue("cmbEtcfFacultyId_trgCalendarGrid_"+ rowid);	

		var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
		
		var factId = jQuery("#frmGrdBsdTrgCalView input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
		var locaioniId = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
		/* 	var myGrid = jQuery('#trgCalendarGrid');
		  var selRowId = myGrid.jqGrid('getGridParam', 'selrow');
		  var TradeId = myGrid.jqGrid('getCell', selRowId, 'cmbEtcmTrainingfunction_'+myGrid+'_'+selRowId);
		 */
		//alert(tradeId + "tradeid" + rowid);
		//var mode="view";
		var ds = "&flid=" + flid + "&SectionId=" + sectionId + "&refDocId="
				+ refDocId + "&LocationId=" + locaioniId + "&cellId=" + cellId;
	//	alert(ds + "ds ds" + refDocId);
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
       //  alert(123);
		var refDocId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmKeyid");
		
		var factId = jQuery("#frmGrdBsdTrgCalView input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
		var locaionId = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
		var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
		var gridval = getGridSelectArray('trgCalendarGrid');
		// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
		//saveForm('frmGrdBsdTrgCalView',	'gridBasedCalendar_save.gbtc?calendarDetails='+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId);
		
		if(refDocId!=null || refDocId!=""||refDocId!="undefined"){
			
		processAjaxCalls("gridBasedCalendar_save.gbtc","calendarDetails="+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId+"&TrnCalId="+refDocId,"updateSuccessCalData","");

		}
		
		/* var markReqrd = jQuery("#cmbEtcmMarksBased_trgCalendarGrid_"+rowid).combobox("getValue");
		var assmCompl = jQuery("#AssementCompleted_trgCalendarGrid_"+rowid).combobox("getValue");
		var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
		var factId = jQuery("#frmGrdBsdTrgCalView input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
		var locaioniId = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
		var ds = "&TraKeyid="+refDocId+"&markReqrd="+markReqrd+"&assmCompl="+assmCompl+"&flid="+flid+"&locaioniId="+locaioniId+"&sectionId="+sectionId+"&cellId="+cellId;
		 */

	}
	// -- VIGNESH ALTERING UPDATE FOR EMPLOYEEATTENDANCE --04DEC2025 ----------------//
	function updateSuccessCalData(result){
  // keep your row resolution exactly as-is
  var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam","selrow") || (result && result.rowId);
  if (!rowid) return;

  // unchanged fields
  var refDocId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmKeyid");
  var flid     = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmFlid");

  var topicId = (function(){
    var $c = jQuery("#cmbEtcmTopicid_trgCalendarGrid_"+rowid);
    try { return $c.combobox("getValue"); } catch(e) {
      return jQuery("#trgCalendarGrid").jqGrid("getCell", rowid, "txtEtcmTopicid");
    }
  })();

  var markReqrd = (function(){
    var $c = jQuery("#cmbEtcmMarksBased_trgCalendarGrid_"+rowid);
    try { return $c.combobox("getValue"); } catch(e) {
      return jQuery("#trgCalendarGrid").jqGrid("getCell", rowid, "txtEtcmMarksBased") || "N";
    }
  })();

  var assmCompl = (function(){
    var $c = jQuery("#cmbEtcmTempfield6_trgCalendarGrid_"+rowid);
    try { return $c.combobox("getValue"); } catch(e) {
      return jQuery("#trgCalendarGrid").jqGrid("getCell", rowid, "txtEtcmTempfield6") || "N";
    }
  })();

  // 🔒 Robust fallbacks (never send blank sectionId/locationId)
  var locationId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmLocationId")
                || jQuery("#frmGrdBsdTrgCalView input[id='location']").val()
                || jQuery("#hdnlocnid").val()
                || "";

  var sectionId  = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmSectionId")
                || jQuery("#frmGrdBsdTrgCalView input[id='section']").val()
                || jQuery("#hdnSectionId").val()
                || "{}";   // 👈 key fix: never blank

  var mode = "view";

  // build the SAME query keys you already use
  var ds = "&TraKeyid="   + encodeURIComponent(refDocId || "")
         + "&flid="       + encodeURIComponent(flid || "")
         + "&mode="       + encodeURIComponent(mode)
         + "&markReqrd="  + encodeURIComponent(markReqrd || "N")
         + "&assmCompl="  + encodeURIComponent(assmCompl || "N")
         + "&locationId=" + encodeURIComponent(locationId || "")
         + "&sectionId="  + encodeURIComponent(sectionId); // never blank now

  // open popup unchanged
  LoadPopUp(
    "divEmployeeAtt",
    "EmpTrainingAtt_input.gbtc?q=2" + ds,
    true, "80%", "60%", "1%", "1%", "", "", false, false
  );
}

// function updateSuccessCalData(result){
	
// 	//jQuery("#trgCalendarGrid").trigger("reloadGrid");
// 	//var rowid=result.rowId;
// 	var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam","selrow");
	
// 	 var refDocId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmKeyid");
// 	 var flid =jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmFlid"); 
// 	 var topicId = jQuery("#cmbEtcmTopicid_trgCalendarGrid_"+rowid).combobox("getValue");
// 	 var markReqrd = jQuery("#cmbEtcmMarksBased_trgCalendarGrid_"+rowid).combobox("getValue");
// 	 var assmCompl = jQuery("#cmbEtcmTempfield6_trgCalendarGrid_"+rowid).combobox("getValue");
// 	 var locationId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmLocationId");
// 	 var sectionId = jQuery("#trgCalendarGrid").jqGrid("getCell", rowid,"txtEtcmSectionId");
//      var mode="view";
	 
// 	// alert(flid +" topicId "+topicId +" markReqrd"+markReqrd+"assmCompl"+assmCompl);
// var ds="&TraKeyid="+refDocId+"&flid="+flid+"&mode="+mode+"&markReqrd="+markReqrd+"&locationId="+locationId+"&sectionId="+sectionId;
// //alert(ds+"  dss");
// 	LoadPopUp("divEmployeeAtt", "EmpTrainingAtt_input.gbtc?q=2" + ds,
// 			true, "80%", "60%", "1%", "1%", "", "", false, false);
// }

	function trgCalendarGridbtnUniqFactAdd_onClick(result) {

		var rowid = result.rowId;
		var btnid = result.btnId;
		
		//var refDocId = jQuery('#hdnCalKeyId').val();//jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmKeyid");
		 var refDocId=jQuery("#trgCalendarGrid").jqGrid('getCell',rowid,"txtEtcmKeyid");
		

		var factId = jQuery("#frmGrdBsdTrgCalView input[id='pbu']").val();
		var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
		var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
		var locaionId = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
		var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();

		if (refDocId.length == 1 || refDocId == null || refDocId == '') {

			var gridval = getGridSelectArray('trgCalendarGrid');
			// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
			if (gridval != "") {
				//	alert(gridval +"gridval23");

				var calendarDetails = gridval;//+'&flId='+flId+'&sectionId='+sectionId+"&docId="+docId+"&types="+types+"&remarks="+remarks;
				saveForm('frmGrdBsdTrgCalView',
						'gridBasedCalendar_save.gbtc?calendarDetails='
								+ calendarDetails + "&fliId=" + flid
								+ "&locaionId=" + locaionId + "&sectionId="
								+ sectionId + "&cellId=" + cellId);

			}
		}

		else {

			LoadPopUp("divUniquePositionFaculty","trngCalUniquePosition_input.gbtc?&trgCalId="+ refDocId+ "&fliId="+ flid
					+ "&locaionId=" + locaionId + "&sectionId="	+ sectionId + "&cellId=" + cellId+"&mode=view", true, "93%", "93%", "1%", "1%", "", "",
					false, false);

			/* 
			LoadPopUp("divUniquePositionFaculty","trngCalUniquePosition_input.gbtc","?q=2&trgCalId="+ trgCalId+ "&fliId=" + flid
					+ "&locaionId=" + locaionId + "&sectionId="
					+ sectionId + "&cellId=" + cellId, true, "93%", "93%", "1%", "1%", false,false);//,false,false);
		 */
			//	LoadPopUp("divUnique Position Faculty","EmployeeAdd_input.gbtc", true,"93%","93%","1%","1%", "","",false,false);

		}

	}

	function frmGrdBsdTrgCalView_successsCallback(result) {
		var trgCalId = result.successData.keyId;
		//jQuery('#hdnCalKeyId').val(trgCalId);

		var mode = "view";
		processGridnew("trngcalendarGridView_input.gbtc", "?q=2&trgCalId="
				+  trgCalId + "&mode=" + mode, "trgCalendarGrid", "pager", "",
				"", "", "onLoadComplete");
	}
	

	function onLoadComplete(id) {
		var rowid = jQuery("#trgCalendarGrid").jqGrid('getDataIDs');
		if (rowid.length > 0) {
			/* jQuery('input:checkbox[name=jqg_trgCalendarGrid_' + rowid + ']')
					.attr('checked', true); */
			var dateOfTrnCreted = "txtEtcmKeyid_trgCalendarGrid_" + rowid;
			jQuery('#trgCalendarGrid').jqGrid({
				editable : true
			});//setGridParam({cellEdit:true});				

			var myGrid = jQuery('#trgCalendarGrid'), selRowId = myGrid.jqGrid(
					'getGridParam', 'selrow'), celValue = myGrid.jqGrid(
					'getCell', selRowId, 'trgCalendarGrid_txtEtcmKeyid_'
							+ rowId);

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

		disableField('frmGrdBsdTrgCalView',	"dteEtcmCreatedDateTime_trgCalendarGrid" + "_" + rowId);
		var dateOfTrnCreted = "dteEtcmCreatedDateTime_trgCalendarGrid_" + rowId;
		
		disableField('frmGrdBsdTrgCalView',"cmbEtcmAnchoredby_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmTopicid_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmTopiccategory_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmTrainingfunction_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmVenue_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"chkEtcmGeneral_trgCalendarGrid_"+rowId);
		disableField('frmGrdBsdTrgCalView',"dteEtcmCalendarDate_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"txtEtcmPermittedStrength_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"txtEtcmMaxDuration_trgCalendarGrid_"+ rowId);
		
		disableField('frmGrdBsdTrgCalView',"cmbEtcmAssessmentReq_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmMaterialsReady_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmMarksBased_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"txtEtcmMaxDuration_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmFunction_trgCalendarGrid_"+ rowId);

		
		disableField('frmGrdBsdTrgCalView',"cmbEtcmTempfield6_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmChkCompleted_trgCalendarGrid_"+rowId);
		disableField('frmGrdBsdTrgCalView',"dteEtcmCompletedDate_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmCompletedBy_trgCalendarGrid_"+ rowId);
		disableField('frmGrdBsdTrgCalView',"cmbEtcmRating_trgCalendarGrid_" +rowId);
		//disableField('frmGrdBsdTrgCal',"#txtEtcmComments_trgCalendarGrid_"+rowId);
		jQuery("#txtEtcmComments_trgCalendarGrid_"+rowId).attr('readonly',true); 
		jQuery("#txtEtcmPermittedStrength_trgCalendarGrid_"+rowId).attr('readonly',true); 
		jQuery("#txtEtcmMaxDuration_trgCalendarGrid_"+rowId).attr('readonly',true); 
		//jQuery("#chkEtcmGeneral_trgCalendarGrid_"+rowId).attr('readonly',true); 
		readOnlyFields("chkEtcmGeneral_trgCalendarGrid_"+rowId);

		
		if(refDocId==null||refDocId==""||refDocId=="undefined") 
		fillWithCurrentDate(dateOfTrnCreted);

	}
	function trgCalendarGridbtnFilManage_onClick(result) {
		//alert(1);
		var rowid = result.rowId;
		var refDocId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmKeyid");
		
	       var documentNo =refDocId;
	       //alert(documentNo);

	       if(documentNo.length!=1)
	    	   {
	   		if(documentNo != null && documentNo != ''){
	   			
	   			fileManagerPopUp(documentNo,"YY","","","","create");
	   		}
	    	   }
	       else{
	    	  alert("Pls Save Training First");
	    	  return false;
	       }
	       
	    };
		jQuery("#btnSave").click(function(){
			
			var rowid = jQuery("#trgCalendarGrid").jqGrid("getGridParam","selrow");

			var refDocId = jQuery("#trgCalendarGrid").jqGrid('getCell', rowid,"txtEtcmKeyid");
			
			var factId = jQuery("#frmGrdBsdTrgCalView input[id='pbu']").val();
			var sectionId = jQuery("#frmGrdBsdTrgCalView input[id='section']").val();
			var cellId = jQuery("#frmGrdBsdTrgCalView input[id='cell']").val();
			var locaionId = jQuery("#frmGrdBsdTrgCalView input[id='location']").val();
			var flid = jQuery("#frmGrdBsdTrgCalView input[id='flid']").val();
			var gridval = getGridSelectArray('trgCalendarGrid');
			// var Ism= getSelectdRowsAtt('trgCalendarGrid', 'chkAbnmShutdownmaint', '');
			//saveForm('frmGrdBsdTrgCalView',	'gridBasedCalendar_save.gbtc?calendarDetails='+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId);
			
			/* saveForm('frmGrdBsdTrgCalView','gridBasedCalendar_save.gbtc?calendarDetails='+ calendarDetails + "&fliId=" + flid
								+ "&locaionId=" + locaionId + "&sectionId="
								+ sectionId + "&cellId=" + cellId);	 */
								saveForm("frmGrdBsdTrgCalView","gridBasedCalendar_save.gbtc?calendarDetails="+ gridval + "&fliId=" + flid+ "&locaionId="+locaionId+"&sectionId="+sectionId +"&cellId="+cellId+"&TrnCalId="+refDocId);

			
	});
		
</script>
<body>
	<Form id="frmGrdBsdTrgCalView" action="">
		<div id="wrapper" style="width: 80%;">
			<table>
				<tr>
					<td colspan="3">
						<div id="frmGrdBsdTrgCalViewFuntKeyIds">
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
			<div id="Controls" style="width: 100%%";>
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
				 <!--<Span style="margin-left: 30px;"> <input type="button"
					class="easyui-button" value="Add Row" id="btnAddRow"
					style="height: 30px" />
				</Span> <Span style="margin-left: 30px;"> <input type="button"
					class="easyui-button" value="Save" id="btnSave"
					style="height: 30px" />
				</Span> <Span style="margin-left: 30px;"> <input type="button"
					class="easyui-button" value="Delete" id="btnDelete"
					style="height: 30px" />
				</Span> -->
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
	
	</Form>
</body>
<html></html>