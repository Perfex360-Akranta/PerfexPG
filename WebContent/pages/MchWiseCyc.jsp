<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
					function() {
						initialiseForm('frmCellManning');
						jQuery('#submitForm').val('frmCellManning'); // set the id of form to submit

						//viewGrid("PcsCycletimePrdWise_input.cllmng","?row=2"+"&type=PRD","pcsEntrymode");
						viewGrid("PcsCycletimePrdWise_input.cllmng", "",
								"pcsEntrymode");
						fillComboBox("frmCellManning", "cmbcytmMachineid",
								"machineCombo.commonFilter");
						formatDateBox('dtecytmFromdate', 'dd-MMM-yyyy');
						fillWithCurrentDate('dtecytmFromdate');
						numericTextBox('txtcytmCycletime');
						numericTextBox('txtcytmManpower');
						numericTextBox('txtcytmCavity');
						numericTextBox('txtcytmMandrels');
						/*	jQuery('#mandrelCavity').css("display","none");	
							jQuery('#mnPwr').css("display","none");
							jQuery('#cycletimeDate').css("display","none");*/

						var factId = jQuery(
								"#frmCellManning input[id='factory']").val();
						var sectionId = jQuery(
								"#frmCellManning input[id='section']").val();
						var cellId = jQuery("#frmCellManning input[id='cell']")
								.val();
						var machId = jQuery(
								"#frmCellManning input[id='machine']").val();
						fillComboBox("frmCellManning", "cmbCostCenter",
								"costCenter.commonFilter");
						var dataStr = "&factId=" + factId + "&sectionId="
								+ sectionId + "&cellId=" + cellId + "&machId="
								+ machId;
						//loadFunctionalLocation("cytmfunLocation","functionalLoc.cllmng","cytmfunLocationValues","frmCellManning",dataStr);
						//viewMode();
						entryMode();
						fillComboBox("frmCellManning", "cmbcytmProductid",
								"product.commonFilter");
					});

	function BtnFormatter() {

	}
	function viewGrid(url, filterString, showGrid) {
		/*if(showGrid=="pcsViewmode")
			processGridnew("PcsCycletimeEntryMode_input.cllmng",filterString,"pcsCycletimeGrid","pcsCycletimePager");
		else if(showGrid=="pcsEntrymode")
		{*/
		processGridnew(url, filterString, "pcsCycletimeGrid",
				"pcsCycletimePager", "", "doubleClickGrid", "",
				"Load_Complete", "frmCellManningselectRowFunction");
		//}
	}

	function frmCellManningselectRowFunction_selectAll(id, status) {

		for ( var i = 0; i < id.length; i++) {
			if (status)
				chkboxCheck(id[i]);
			else
				chkboxUnCheck(id[i]);
		}
	}

	function frmCellManningselectRowFunction_selectRow(id) {

		if (jQuery('#jqg_pcsCycletimeGrid_' + id).is(':checked'))
			chkboxCheck(id);
		else
			chkboxUnCheck(id);
	}

	function frmCellManningcmbcytmMachineid_onLoadSuccess() {
		fillComboBox("frmCellManning", "cmbcytmProductid",
				"product.commonFilter");
		//fillComboBox("frmCellManning","cmbCostCenter","costCenter.commonFilter" );
	}

	function frmCellManningcmbCostCenter_onLoadSuccess() {
		setComboDefaultValue("frmImprovementPrj", "cmbCostCenter");
		//fillComboBox("frmCellManning","cmbcytmProductid","combo_product.cllmng");

	}

	function frmCellManningcmbcytmProductid_onLoadSuccess() {
		fillComboBox("frmCellManning", "cmbcytmProdgroupid",
				"combo_subgroup.cllmng");
	}

	function frmCellManningcmbcytmProdgroupid_onSelect(record) {
		jQuery("#cmbcytmProductid").combobox('clear');
		reloadCombo("frmCellManning", "cmbcytmProductid",
				"combo_product.pcs?q=2&prdModelId=" + record.id);
	}

	function frmCellManningcmbcytmProductid_onSelect(record) {
		jQuery("#cmbcytmProdgroupid").combobox('clear');
		//reloadCombo("frmCellManning","cmbcytmProdgroupid","combo_subgroup.cllmng?prmmKeyid="+record.id);
		setProductModel(record.id);
	}

	function setProductModel(id) {
		var dataStr = "?q=2&prdId=" + id;
		processAjaxCalls("selectedProductModel.pcs", dataStr, "modelIdSuccess",
				"modelIdSuccessError");
	}

	function modelIdSuccess(result) {
		jQuery('#cmbcytmProdgroupid').combobox('setValue', result.modelKeyid);
	}
	function Load_Complete() {
		var rowId = jQuery("#pcsCycletimeGrid").jqGrid('getDataIDs');
		var cm = jQuery("#pcsCycletimeGrid").jqGrid("getGridParam", "colModel");
		formatDateBoxWithGrid("dtePrdParam_", 'dd-MMM-yyyy');
		numericTextBoxWithGrid("txtNewCyc_");
		/*for(i=1;i<=rowId.length;i++)	
		{	
			for(j=1;j<=cm.length-1;j++)	
			{		
				var controlId="txtNewCyc_"+(j-1);	
				var date="dtePrdParam_"+(j-1);
				//var date="txtNewCycDate_"+(j-1);	
				numericTextBox(controlId);
				formatDateBox(date,'dd-MMM-yyyy');
			}
		}*/
	}
	/*function  frmCellManningcmbcytmMachineid_onSelect(record)
	{
	 	loadFunctionalLocation("cytmfunLocation","functionalLoc.cllmng","cytmfunLocationValues","frmCellManning","&machId="+record.id);
	}

	function  frmCellManningcmbcytmMachineid_onClear()
	{
	 	loadFunctionalLocation("cytmfunLocation","functionalLoc.cllmng","cytmfunLocationValues","frmCellManning","");
	}
	
	function frmCellManning_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setFieldValue('cmbcytmMachineid',keyIds.machId);
		reloadMachine("frmCellManning",'cmbcytmMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		jQuery("#cmbCostCenter").combobox('clear');
		reloadCombo("frmCellManning","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  );
		var sectId = getFieldValue('section','frmCellManning');
		fillCellManningData();
	}*/
	function fillCellManningData() {
		var cellId = getFieldValue('cell', 'frmCellManning');
		var mchId = getFieldValue('cmbcytmMachineid', 'frmCellManning');
		var productId = jQuery('#cmbcytmProductid').combobox('getValue');
		var prodGrpId = getFieldValue('cmbcytmProdgroupid', 'frmCellManning');

		if (productId != null && productId.trim() != "") {
			jQuery('#hdnCycletimeId').val('');
			viewGrid("PcsCycletimePrdWise_input.cllmng", "&productId="
					+ productId + "&cellId=" + cellId + "&mchId=" + mchId
					+ "&prodGrpId=" + prodGrpId + "&type=PRD", "pcsViewmode");
		}
	}
	function frmCellManning_beforeSubmit() {
		//		var gridData  = '&pcsTlCycletime='+convertJqGridToJSONObjectArr('pcsCycletimeGrid');

		var cycleTime = jQuery('#txtcytmCycletime').val();
		var manpower = jQuery('#txtcytmManpower').val();
		var cavity = jQuery('#txtcytmCavity').val();
		var mandrels = jQuery('#txtcytmMandrels').val();
		var fromdate = jQuery('#dtecytmFromdate').datebox('getValue');
		var productid = jQuery('#cmbcytmProductid').combobox('getValue');
		var productgrpId = jQuery('#cmbcytmProdgroupid').combobox('getValue');
		var factoryid = jQuery("#frmCellManning input[id='factory']").val();
		var sectionid = jQuery("#frmCellManning input[id='section']").val();
		var cellid = jQuery("#frmCellManning input[id='cell']").val();
		var machineId = jQuery("#frmCellManning input[id='cmbcytmMachineid']")
				.combobox('getValue');

		var keyid = jQuery('#hdnCycletimeId').val();

		var jsonStr = '[{"hdnCytmKeyid":"' + keyid + '","dtecytmFromdate":"'
				+ fromdate + '","cmbcytmProductid":"' + productid
				+ '","txtcytmCycletime":"' + cycleTime
				+ '","txtcytmManpower":"' + manpower + '","txtcytmCavity":"'
				+ cavity + '","txtcytmMandrels":"' + mandrels
				+ '","cmbcytmProdgroupid":"' + productgrpId
				+ '","cmbcytmFactoryid":"' + factoryid
				+ '","cmbcytmSectionid":"' + sectionid + '","txtcytmCellid":"'
				+ cellid + '","cmbcytmMachineid":"' + machineId + '"}]';
		var gridData = '&pcsTlCycletime=' + jsonStr;
		return gridData;
	}

	function frmCellManning_successsCallback(result) {
		//frmClear();
		jQuery('#pcsCycletimeGrid').trigger('reloadGrid');
	}

	function frmCellManning_errorCallback(result) {
		setTimeout(function() {
			jQuery('#pcsCycletimeGrid').trigger('reloadGrid');
		}, 950);
	}

	function frmCellManning_exceptionCallback(result) {
	}

	jQuery('#btnCellPdMap').click(function() {
		navigateToNextForm("cellprodlnk_input.cplnk");
	});

	function doubleClickGrid(id) {
		var rowData = jQuery("#pcsCycletimeGrid").getRowData(id);

		jQuery('#txtcytmCycletime').val(rowData.txtcytmCycletime);
		jQuery('#txtcytmManpower').val(rowData.txtcytmManpower);
		jQuery('#txtcytmCavity').val(rowData.txtcytmCavity);
		jQuery('#txtcytmMandrels').val(rowData.txtcytmMandrels);
		jQuery('#hdnCycletimeId').val(rowData.txtcytmKeyid);
		jQuery('#dtecytmFromdate').datebox('setValue', rowData.dtecytmFromdate);
		jQuery('#cmbcytmProductid').combobox('setValue',
				rowData.cmbcytmProductid);
		jQuery('#cmbcytmProdgroupid').combobox('setValue', rowData.Model);
		jQuery('#cmbcytmMachineid').combobox('setValue',
				rowData.cmbcytmMachineid);
		jQuery('#hdnRowid').val(id);
		jQuery('#cmbcytmProductid').combobox('disable');
		jQuery('#cmbcytmProdgroupid').combobox('disable');
	}

	jQuery('#btnUpdate').click(
			function() {

				/*var productId = jQuery('#cmbcytmProductid').combobox('getValue');
				var cycleTime = jQuery('#txtcytmCycletime').val();
				processAjaxCalls("PcsCycletimeUpdate_getData.cllmng","product="+productId+"&cycleTime="+cycleTime,"SucessCallBack","ErrCallBack");
				 */
				var rowId = jQuery("#pcsCycletimeGrid").jqGrid('getDataIDs');
				var cm = jQuery("#pcsCycletimeGrid").jqGrid("getGridParam",
						"colModel");
				var cycleTime = jQuery('#txtcytmCycletime').val();
				var fromdate = jQuery('#dtecytmFromdate').datebox('getValue');
				var row;
				var value;
				var controlId;
				var date;
				var allRows = jQuery("#pcsCycletimeGrid").jqGrid('getRowData');
				for (i = 0; i <= rowId.length; i++) {
										//for(j=1;j<=cm.length-1;j++)	
					//var value=jQuery("#chkboxValue").val(value);

					row = allRows[i];

					value = row["TICKVAL"];

					controlId = "txtNewCyc_" + (i + 1);
					date = "dtePrdParam_" + (i + 1);

					if (value != null || value.trim() != "") {
						if (value == '1') {
							jQuery("#" + controlId).val(cycleTime);
							jQuery("#" + date).datebox("setValue", fromdate);

						}
					} else {
					}

				}
			});
	function SucessCallBack() {

	}
	function ErrCallBack() {
	}
	jQuery('#btnEntryMde').click(
			function() {

				var sectId = getFieldValue('section', 'frmCellManning');
				var cellId = getFieldValue('cell', 'frmCellManning');
				//var mchId = getFieldValue('cmbcytmMachineid','frmCellManning');

				var productId = jQuery('#cmbcytmProductid')
						.combobox('getValue');
				if (productId != null && productId.trim() != "") {
					viewGrid("PcsCycletimePrdWise_input.cllmng", "&productId="
							+ productId + "&sectId=" + sectId + "&cellId="
							+ cellId + "&mchId=" + "&pcsMode=entry"
							+ "&type=PRD", "pcsEntrymode");
					jQuery('#cmbcytmMachineid').combobox('disable');
					jQuery('#btnfrmCellManningmainFunLoc').unbind("click");
					//entryMode();
				} else {
					alert("Select Product");
					return false;
				}

			});

	jQuery('#btnInsert').click(
			function() {
				var cytmKeyid = jQuery('#hdnCycletimeId').val();
				var cytmProduct = jQuery('#cmbcytmProductid').combobox(
						'getValue');
				var cytmProdgroupid = jQuery('#cmbcytmProdgroupid').combobox(
						'getValue');
				var equipment = jQuery('#cmbcytmMachineid')
						.combobox('getValue');
				var manpower = jQuery('#txtcytmManpower').val();
				var cycleTime = jQuery('#txtcytmCycletime').val();

				var dtecytmFromdate = jQuery('#dtecytmFromdate').datebox(
						'getValue');
				var mandrels = jQuery('#txtcytmMandrels').val();
				var cavity = jQuery('#txtcytmCavity').val();
				if (equipment.trim() == "" || equipment == null) {
					alert("Select Equipment");
					return false;
				} else if (cytmProduct.trim() == "" || cytmProduct == null) {
					alert("Select Product");
					return false;
				}

				else if (manpower.trim() == "" || manpower == null) {
					alert("Enter Manpower");
					return false;
				} else if (cycleTime.trim() == "" || cycleTime == null) {
					alert("Enter RNT");
					return false;
				}

				else if (dtecytmFromdate.trim() == ""
						|| dtecytmFromdate == null) {
					alert("Select Date");
					return false;
				} else if (manpower.trim().match(/[0* ]+/)) {
					alert("Man Power should be greater than zero");
					return false;
				}

				else if (cavity.trim() == "" || cavity == null) {
					alert("Enter Cavity");
					return false;
				} else if (mandrels.trim() == "" || mandrels == null) {
					alert("Enter Mandrels");
					return false;
				} else {

					var rowObject = [];
					var id = jQuery("#pcsCycletimeGrid").getGridParam(
							'reccount') + 1;

					rowObject[0] = new Object();
					//rowObject[0].key=id;

					(rowObject[0])['txtcytmKeyid'] = cytmKeyid;
					(rowObject[0])['cmbcytmFactoryid'] = jQuery(
							"#frmCellManning input[id='factory']").val();
					(rowObject[0])['cmbcytmSectionid'] = jQuery(
							"#frmCellManning input[id='section']").val();
					(rowObject[0])['txtcytmCellid'] = jQuery(
							"#frmCellManning input[id='cell']").val();
					(rowObject[0])['cmbcytmMachineid'] = jQuery(
							"#frmCellManning input[id='cmbcytmMachineid']")
							.combobox('getValue');
					(rowObject[0])['cmbcytmProductid'] = cytmProduct;
					(rowObject[0])['cmbcytmProdgroupid'] = cytmProdgroupid;
					(rowObject[0])['txtcytmCycletime'] = cycleTime;
					(rowObject[0])['txtcytmManpower'] = manpower;
					(rowObject[0])['txtcytmCavity'] = cavity;
					(rowObject[0])['txtcytmMandrels'] = mandrels;
					(rowObject[0])['dtecytmFromdate'] = dtecytmFromdate;
					(rowObject[0])['txtSelectionid'] = "INSERT";
					wrap = jQuery(rowObject);

					var rowDataExist = jQuery("#pcsCycletimeGrid").getRowData(
							cytmKeyid);

					if (cytmKeyid.trim() != "" || cytmKeyid != "")//isEmpty(rowDataExist)
					{

						rowDataExist['txtcytmMandrels'] = mandrels;
						rowDataExist['txtcytmCycletime'] = cycleTime;
						rowDataExist['txtcytmManpower'] = manpower;
						rowDataExist['txtcytmCavity'] = cavity;
						rowDataExist['dtecytmFromdate'] = dtecytmFromdate;
						rowDataExist['txtSelectionid'] = "UPDATE";
						jQuery("#pcsCycletimeGrid").setRowData(
								jQuery('#hdnRowid').val(), rowDataExist, true);
						saveForm("frmCellManning", "CellMng_save.cllmng");

					} else {
						jQuery("#pcsCycletimeGrid").addRowData(id, rowObject,
								'last', id);
						jQuery('tr[id=undefined]').attr('id', id);
						saveForm("frmCellManning", "CellMng_save.cllmng");
					}

				}
				//}		

			});

	jQuery('#btnDelete')
			.click(
					function() {//ImgDelete
						var cytmKeyid = jQuery('#hdnCycletimeId').val();

						if (cytmKeyid != null && cytmKeyid.trim() != "") {
							var r = confirm("Are you sure to Delete ? ");
							if (r == true) {
								deleteRecord("frmCellManning",
										"CellMng_delete.cllmng?cytmKeyid="
												+ cytmKeyid);
							}
						}

					});

	function frmCellManning_deleteSuccessCallback(result) {
		frmClear();
		//jQuery('#pcsCycletimeGrid').trigger('reloadGrid');	
	}

	jQuery('#chkGrpBy').click(function() {
		if (jQuery('#chkGrpBy').is(':checked') == true) {
			jQuery('#VwEffective').hide();
			jQuery('#chkVwEfftDt').attr('checked', false);
		} else
			jQuery('#VwEffective').show();
	});

	jQuery('#btnClear').click(function() {
		frmClear();
	});

	jQuery('#btnVw').click(function() {
		fillCellManningData();
		jQuery('#pcsCycletimeGrid').trigger('reload');
	});

	function frmClear() {
		clearField('txtcytmManpower');
		clearField('txtcytmCycletime');
		clearField('cmbcytmProductid');
		clearField('cmbcytmProdgroupid');
		clearField('txtcytmMandrels');
		clearField('txtcytmCavity');
		jQuery('#hdnCycletimeId').val('');
		jQuery('#hdnRowid').val('');
		jQuery('#cmbcytmProductid').combobox('enable');
		jQuery('#cmbcytmProdgroupid').combobox('enable');
	}

	/*function convertJqGridToJSONObjectArrAA(jqGridId){
		
		var allRows = jQuery("#"+jqGridId +' tr:last').jqGrid('getRowData');

		alert("allRows ="+Object.keys(allRows))  ;
		var jsonArrO='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			jsonArrO += '{';
			
			for(var colName in row) {
				jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");

		alert(jsonArrO);
		return jsonArrO; 
	}*/

	function entryMode() {
		//alert('EntryMode');
		jQuery('#CstDiv').css("display", "none");
		jQuery('#mnPwr').css("display", "block");
		jQuery('#mandrelCavity').css("display", "block");
		jQuery('#viewmdediv').css("display", "none");
		jQuery('#entrymdediv').css("display", "block");

	}
	function viewMode() {
		jQuery('#CstDiv').css("display", "block");
		jQuery('#mnPwr').css("display", "none");
		jQuery('#mandrelCavity').css("display", "none");
		jQuery('#entrymdediv').css("display", "none");
		jQuery('#viewmdediv').css("display", "block");
		jQuery('#cycletimeDate').css("display", "none");
		jQuery('#cmbcytmMachineid').combobox('enable');
		jQuery('#btnfrmCellManningmainFunLoc').bind("click");
	}
	jQuery('#chkVwEfftDt').click(function() {

	});

	/*function ChkBoxFormatter(id, options, rowObject)
	{			
		var rowId = options.rowId;	
		return '<input id="PrdWise_checkbox" name="PrdWise_checkbox" '+ (rowObject[0]=="0" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}*/
	function chkboxCheck(rowId) {
		jQuery("#pcsCycletimeGrid").jqGrid('setCell', rowId, 'TICKVAL', '1');
	}
	function chkboxUnCheck(rowId) {
		jQuery("#pcsCycletimeGrid").jqGrid('setCell', rowId, 'TICKVAL', '0');
	}
	function TxtFormatter(id, options, rowObject) {
		var rowId = options.rowId;
		return '<input type="text" style="text-align:right;width:90px;"  id=txtNewCyc_'+rowId +' value='+id +' >';
		//return '<input type="text" style="text-align:right;width:150px;"  maxlength="7" id=txtNewCyc_'+rowId +' value='+id +' >';
	}

	function TxtNewDateFormatter(id, options, rowObject) {
		var rowId = options.rowId;
		return '<input type="text" style="text-align:right;width:90px;"  id=txtNewCycDate_'+rowId +' value='+id +' >';

	}

	function DteBoxFormatter(id, options, rowObject) {
		return '<input id="dtePrdParam_'+options.rowId+'" style="width:90px;"  class="easyui-datebox" />';
	}

	function frmCellManning_beforeSubmit() {
		var gridData = "&pcsTlCycletime="
				+ getSelectdRows("pcsCycletimeGrid", "select", "TICKVAL");

		//gridData += "&date="+jQuery('#dteEncmDate').datebox('getValue');
		var chkVal = jQuery("#chkboxValue").val();
		if (chkVal == "1")
			return gridData;
		else
			alert("Select a Row For Save!");

	}

	function getSelectdRows(jqGridId, checkBoxColName, ckeckForSelColName) {

		var allRows = jQuery("#pcsCycletimeGrid").jqGrid('getRowData');
		var cm = jQuery("#pcsCycletimeGrid").jqGrid("getGridParam", "colModel");
		var jsonArrO = '[';
		for ( var i = 0; i < allRows.length; i++) {
			var row = allRows[i];
			var value = row[ckeckForSelColName];

			if (value != null && value.trim() != "") {
				if (value == '1') {
					jQuery("#chkboxValue").val(value);

					jsonArrO += '{';
					for ( var colName in row) {
						//alert(colName);
						if (colName != 'select' && colName != 'TICKVAL'
								&& colName != "MchName" && colName != "Product"
								&& colName != "Model"
								&& colName != "txtcytmCycletime"
								&& colName != "dtecytmFromdate"
								&& colName != "slno") {
							var cellValue = parseJqGridCellValue(row[colName]);

							if (colName.trim() == "txtcytmKeyid") {
								if (cellValue == null || cellValue.trim() == "")
									cellValue = '{}';
								jsonArrO += "txtcytmKeyid :" + cellValue + ",";
							} else if (colName.trim() == "NewCycletime")
								jsonArrO += "txtcytmCycletime :" + cellValue
										+ ",";
							else if (colName.trim() == "dtecytmNewFromdate")
								jsonArrO += "dtecytmFromdate :" + cellValue
										+ ",";
							else if (colName.trim() == "cmbcytmProductid")
								jsonArrO += "cmbcytmProductid :" + cellValue
										+ ",";
							else if (colName.trim() == "txtcytmManpower")
								jsonArrO += "txtcytmManpower :" + cellValue
										+ ",";
							else if (colName.trim() == "txtcytmCavity")
								jsonArrO += "txtcytmCavity :" + cellValue + ",";
							else if (colName.trim() == "txtcytmMandrels")
								jsonArrO += "txtcytmMandrels :" + cellValue
										+ ",";
							/*else if(colName.trim()=="cmbcytmProdgroupid")				
								jsonArrO += "cmbcytmProdgroupid :" + cellValue+",";	*/
							else if (colName.trim() == "cmbcytmFactoryid")
								jsonArrO += "cmbcytmFactoryid :" + cellValue
										+ ",";
							else if (colName.trim() == "cmbcytmSectionid")
								jsonArrO += "cmbcytmSectionid :" + cellValue
										+ ",";
							else if (colName.trim() == "txtcytmCellid")
								jsonArrO += "txtcytmCellid :" + cellValue + ",";
							else if (colName.trim() == "cmbcytmMachineid")
								jsonArrO += "cmbcytmMachineid :" + cellValue
										+ ",";

						}
					}
					jsonArrO += "},";
				}
			}
		}
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']' ? jsonArrO : "");

		return jsonArrO;
	}
</script>

<form id="frmCellManning">
	<div id="wrapper" style="margin-left:-10px;margin-left:40px\9;">
		<div class="main-cntborder" style="width: 90%; height:390px\9;">
          <div style="margin-left:-60px\9;">
			<table rules="none" border="0" style="margin-left: 0px;margin-left:60px\9;float:left\9;margin-top:4px;">
				<tr>

					<td style="">
						<div style="padding-left: 55px;padding-left: 0px\9;">
							<label>Product Model</label>
						</div>
						<div style="padding-left: 55px;padding-left: 0px\9;">
							<input id="cmbcytmProdgroupid" name="cmbcytmProdgroupid"
								class="easyui-combobox"
								value="${requestScope.pcsTlCycletimemst.cytmProdgroupid}"
								style="width: 190px;" />
						</div></td>
					<td style="padding-left: 10px;">
						<div style="margin-top: 1%;">
							<label class="mandatory-lbl">Product</label>
						</div>
						<div>
							<input id="cmbcytmProductid" name="cmbcytmProductid"
								class="easyui-combobox"
								value="${requestScope.pcsTlCycletimemst.cytmProductid}"
								style="width: 250px;" />
						</div></td>
				</tr>
				<tr>
					<td >
						<div id="mnPwr">
							<div>
								<span style="padding-left: 55px;padding-left: 0px\9;"><label
									class="mandatory-lbl">Cycle Time</label>
								</span> <span style="padding-left: 8%;"><label
									class="mandatory-lbl">Date</label>
								</span>
							</div>
							<div>
								<span style="padding-left: 55px;padding-left: 0px\9;"><input
									id="txtcytmCycletime" name="txtcytmCycletime"
									class="easyui-text"
									value="${requestScope.pcsTlCycletimemst.cytmCycletime}"
									maxlength="8" style="width: 32%;width:36%\9;height:22px\9;" />
								</span> 
								 <span style="padding-left: 10px\9;"><input id="dtecytmFromdate" name="dtecytmFromdate"
									class="easyui-datebox"
									value="${requestScope.pcsTlCycletimemst.cytmFromdate}"
									style="width: 100px;" />
								</span> <input id="hdncytmKeyid" name="hdncytmKeyid" type="hidden"
									value="${requestScope.pcsTlCycletimemst.cytmKeyid}" />
							</div>
						</div></td>
					<td style="padding-top: 20px;margin-left:10px;"><span><input
							type="button" id="btnEntryMde" class="easyui-button" onclick=""
							value="View" style="height: 2%;margin-left:10px;" />
					</span> <span><input type="button" id="btnUpdate"
							class="easyui-button" value="Apply To All" style="height: 2%;width:90px;;" />
					</span></td>
				</tr>
			</table>
           </div>
			<div class="clear"></div>
			<div id="griddiv" style="margin-left: 0px;">
				<div>
					<table id="pcsCycletimeGrid" width="80%"
						style="float: left; margin-top: -2"></table>
				</div>
				<div id="pcsCycletimePager"></div>
				<input type="hidden" id="mode" name="mode"
					value="${requestScope.mode}" /> <input type="hidden"
					id="hdnCycletimeId" /> <input type="hidden" id="hdnRowid" />
			</div>


		</div>
	</div>
	<input type="hidden" id="chkboxValue" name="chkboxValue" value="0" />
</form>
