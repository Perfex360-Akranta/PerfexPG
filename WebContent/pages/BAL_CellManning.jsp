	<script type="text/javascript">
		jQuery.noConflict();
	 	jQuery(document).ready(function(){
	 	 	
	 		    initialiseForm('frmCellManning');	
	 		    jQuery('#submitForm').val('frmCellManning'); // set the id of form to submit
	            var url=jQuery("#hiddenUrl").val();
	 	 		viewGrid("CellMng_input.balcllmng","?row=2","pcsEntrymode");
	 		    fillComboBox("frmCellManning","cmbcytmCellid","cellCombo.commonFilter");
	 			fillComboBox("frmCellManning","cmbcytmProductid","combo_product.balcllmng");
	 			fillComboBox("frmCellManning","cmbcytmProdgroupid","combo_subgroup.balcllmng");
	 			fillComboBox("frmCellManning","cmbCostCenter","costCenter.commonFilter");
	 		    formatDateBox('dtecytmFromdate','dd-MMM-yyyy');	
				fillWithCurrentDate('dtecytmFromdate');			
				numericTextBox('txtcytmCycletime');
				numericTextBox('txtcytmManpower');	
				//numericTextBox('txtcytmCavity');		
				//numericTextBox('txtcytmMandrels'); 
				jQuery('#mandrelCavity').css("display","none");	
				jQuery('#mnPwr').css("display","none");
				jQuery('#cycletimeDate').css("display","none");
				//jQuery('#btnfrmCellManningmainFunLoc').bind("click");
				//jQuery('#ImgSave').unbind("click");
				//jQuery('#ImgDelete').unbind("click");
				/* for functionalLocation*/
				var factId = jQuery("#frmCellManning input[id='factory']").val();
				var sectionId = jQuery("#frmCellManning input[id='section']").val();
				var cellId = jQuery("#frmCellManning input[id='cell']").val();
				var machId = jQuery("#frmCellManning input[id='machine']").val();
			 // var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
			   var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId;
				if(dataStr.trim().length<=0)
					 dataStr = "";
				loadFunctionalLocation("cytmfunLocation","functionalLoc.balcllmng","cytmfunLocationValues","frmCellManning",dataStr);
				/*---------*/	
				//entryMode();
		});	
	
	 	function viewGrid(url,filterString,showGrid)
	 	{
			if(showGrid=="pcsViewmode")
				processGridnew("CellMng_input.balcllmng",filterString,"pcsCycletimeGrid","pcsCycletimePager");
			else if(showGrid=="pcsEntrymode")
			{
				processGridnew(url,filterString,"pcsCycletimeGrid","pcsCycletimePager","","doubleClickGrid");
			}
		}	
	
		//function frmCellManningcmbcytmMachineid_onLoadSuccess(){
			
			//fillComboBox("frmCellManning","cmbCostCenter","costCenter.commonFilter" );
		//}
	
		function frmCellManningcmbCostCenter_onLoadSuccess(){
			//alert(" Inside combo Loadsucess ");
			setComboDefaultValue("frmImprovementPrj","cmbCostCenter");
			fillComboBox("frmCellManning","cmbcytmProductid","combo_product.balcllmng");
		}
	
		function  frmCellManningcmbcytmProductid_onLoadSuccess(){
			fillComboBox("frmCellManning","cmbcytmProdgroupid","combo_subgroup.balcllmng");
	 	}
	
		function  frmCellManningcmbcytmProdgroupid_onSelect(record)	{	
			
		}
	
		function  frmCellManningcmbcytmProductid_onSelect(record)
		{
			jQuery("#cmbcytmProdgroupid").combobox('clear');
			reloadCombo("frmCellManning","cmbcytmProdgroupid","combo_subgroup.balcllmng?prmmKeyid="+record.id);
		}
		
	
	/*	function  frmCellManningcmbcytmMachineid_onSelect(record)
		{
		 	loadFunctionalLocation("cytmfunLocation","functionalLoc.cllmng","cytmfunLocationValues","frmCellManning","&machId="+record.id);
		}*/
	
		function  frmCellManningcmbcytmCellid_onSelect(record)
		{
		 	//loadFunctionalLocation("cytmfunLocation","functionalLoc.cllmng","cytmfunLocationValues","frmCellManning","&machId="+record.id);
		 	loadFunctionalLocation("cytmfunLocation","functionalLoc.balcllmng","cytmfunLocationValues","frmCellManning","&cellId="+record.id);
	
		}
		
	
	/*	function  frmCellManningcmbcytmMachineid_onClear()
		{//alert(1);
		 	loadFunctionalLocation("cytmfunLocation","functionalLoc.cllmng","cytmfunLocationValues","frmCellManning","");
		}*/
	
	function frmCellManningcmbcytmCellid_onClear(){
	
			loadFunctionalLocation("cytmfunLocation","functionalLoc.balcllmng","cytmfunLocationValues","frmCellManning","");
	}
		
		
	/*	function frmCellManning_FuntLocHierarchy_SuccessCallBack(keyIds)
		{
			setFieldValue('cmbcytmMachineid',keyIds.machId);
			reloadMachine("frmCellManning",'cmbcytmMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
			jQuery("#cmbCostCenter").combobox('clear');
			reloadCombo("frmCellManning","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  );
			var sectId = getFieldValue('section','frmCellManning');
	 		fillCellManningData();
		}  */
	
		function frmCellManning_FuntLocHierarchy_SuccessCallBack(keyIds)
		{  
			//setFieldValue('cmbcytmCellid',keyIds.machId);
		    setFieldValue('cmbcytmCellid',keyIds.cellId);
		//	reloadMachine("frmCellManning",'cmbcytmCellid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
			//reloadMachine("frmCellManning",'cmbcytmCellid',keyIds.cellId);
			 jQuery("#cmbCostCenter").combobox('clear');
		//	reloadCombo("frmCellManning","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId);
			//reloadCombo("frmCellManning","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId);
	          reloadCombo("frmCellManning","cmbcytmCellid","cellCombo.commonFilter?cellId="+keyIds.cellId);
	          reloadCombo("frmCellManning","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId);
			  var sectId = getFieldValue('section','frmCellManning');
	 		  fillCellManningData();
		}	
		
		function fillCellManningData()
		{
	     	var cellId = getFieldValue('cell','frmCellManning');
		//	var cellId=getFieldValue('cmbcytmCellid','frmCellManning');
		//var mchId = getFieldValue('cmbcytmMachineid','frmCellManning');
			var productId = jQuery('#cmbcytmProductid').combobox('getValue');//getFieldValue('cmbcytmProductid','frmCellManning');
		//	var prodGrpId = getFieldValue('cmbcytmProdgroupid','frmCellManning');
			//if(mchId != null && mchId.trim() !="")
		if(cellId != null && cellId.trim() !="")	
			{
			  jQuery('#hdnCycletimeId').val('');
			 //	viewGrid("PcsCycletimeEntryMode_input.cllmng","&productId="+productId+"&cellId="+cellId+"&mchId="+mchId+"&prodGrpId="+prodGrpId,"pcsViewmode");
	    // viewGrid("PcsCycletimeEntryMode_input.cllmng","&productId="+productId+"&cellId="+cellId+"&prodGrpId="+prodGrpId,"pcsViewmode");
	    viewGrid("btnEntryMde","&productId="+productId+"&cellId="+cellId,"pcsViewmode");
				}
		}
	
		
		function frmCellManning_beforeSubmit()
		{
		  //var gridData  = '&pcsTlCycletime='+convertJqGridToJSONObjectArr('pcsCycletimeGrid');
		 	var cycleTime = jQuery('#txtcytmCycletime').val();
		 //	alert("Cycle time"+cycleTime);
			var manpower = jQuery('#txtcytmManpower').val();
		 	//alert("The manPower"+manpower);
		//	var cavity = jQuery('#txtcytmCavity').val();
		//	var mandrels = jQuery('#txtcytmMandrels').val();
			var fromdate = jQuery('#dtecytmFromdate').datebox('getValue');
			//alert("FromDate"+fromdate);
			var productid = jQuery('#cmbcytmProductid').combobox('getValue');
			//alert("Productid"+productid);
		//	var productgrpId =jQuery('#cmbcytmProdgroupid').combobox('getValue');
			var factoryid= jQuery("#frmCellManning input[id='factory']").val();
			//alert("Factoryid"+factoryid);
			var sectionid =  jQuery("#frmCellManning input[id='section']").val();
		    var CellId = jQuery("#frmCellManning input[id='cmbcytmCellid']").combobox('getValue');
			var keyid = jQuery('#hdnCycletimeId').val();
			 var jsonStr ='[{"hdnCytmKeyid":"'+ keyid +'","dtecytmFromdate":"'+ fromdate +'","cmbcytmProductid":"'+ productid +
						   '","txtcytmCycletime":"'+ cycleTime +
						   '","txtcytmManpower":"'+ manpower +
						   '","cmbcytmFactoryid":"'+ factoryid +
						   '","cmbcytmSectionid":"'+ sectionid +
						   '","cmbcytmCellid":"'+ CellId +
						   '"}]';		
			var gridData='&pcsTlCycletime='+jsonStr ;
			return gridData; 
		}
	
		function frmCellManning_successsCallback(result)
		{
			frmClear();
			jQuery('#pcsCycletimeGrid').trigger('reloadGrid');
	    }	
	
	    function  frmCellManning_errorCallback(result)
	    {
			setTimeout(function() {	jQuery('#pcsCycletimeGrid').trigger('reloadGrid');},950);
	    }
	
	    function frmCellManning_exceptionCallback(result)
	    {}
	
		jQuery('#btnCellPdMap').click(function(){
			navigateToNextForm("cellprodlnk_input.cplnk");
		});
	
		/* function doubleClickGrid(id)
		{
			var rowData = jQuery("#pcsCycletimeGrid").getRowData(id);
			jQuery('#txtcytmCycletime').val(rowData.txtcytmCycletime);
			jQuery('#txtcytmManpower').val(rowData.txtcytmManpower);
			jQuery('#hdnCycletimeId').val(rowData.txtcytmKeyid);
			jQuery('#dtecytmFromdate').datebox('setValue',rowData.dtecytmFromdate);
			//jQuery('#cmbcytmProductid').combobox('setValue',rowData.cmbcytmProductid);
			jQuery('#cmbcytmProductid').combobox('setValue', rowData.PRODID);//today cell
			jQuery('#cmbcytmProdgroupid').combobox('setValue',rowData.Model);
			jQuery('#hdnRowid').val(id);
			//jQuery('#cmbcytmProductid').combobox('disable');
	        //jQuery('#cmbcytmProdgroupid').combobox('disable');
		}
	 */
	 //today cell
	 function doubleClickGrid(id)
	 {
	     var rowData = jQuery("#pcsCycletimeGrid").getRowData(id);
	     var vals = [];
	     for (var k in rowData) vals.push(rowData[k]);   

	  
	     jQuery('#txtcytmCycletime').val(vals[5]);
	     jQuery('#txtcytmManpower').val(vals[6]);
	     jQuery('#dtecytmFromdate').datebox('setValue', vals[9]);
	     jQuery('#hdnCycletimeId').val(vals[10]);
	     jQuery('#cmbcytmProductid').combobox('setValue', vals[1]);
	     jQuery('#cmbcytmProdgroupid').combobox('setValue', vals[4]);
	     jQuery('#hdnRowid').val(id);
	 }
	 	jQuery('#btnEntryMde').click(function(){
	 		 var sectId = getFieldValue('section','frmCellManning');
	 		// alert("The SectionId"+sectId);
	 		//var cellId = getFieldValue('cell','frmCellManning');
	 		 var cellId = getFieldValue('cmbcytmCellid','frmCellManning');
	 		// var cellId = getFieldValue('cell','frmCellManning');
	 		// alert("The CellId"+cellId);
	 		//var mchId = getFieldValue('cmbcytmMachineid','frmCellManning');	
			//if(mchId != null && mchId.trim() !="")
			if(cellId != null && cellId.trim() !="")
	 		{
		 		//viewGrid("PcsCycletimeEntryMode_input.cllmng","&sectId="+sectId+"&cellId="+cellId+"&mchId="+mchId+"&pcsMode=entry","pcsEntrymode");
	            viewGrid("CellMng_input.balcllmng","&sectId="+sectId+"&cellId="+cellId+"&pcsMode=entry","pcsEntrymode");
	            
	           
	 			
	 			//jQuery('#cmbcytmMachineid').combobox('disable');
	 			jQuery('#cmbcytmCellid').combobox('disable');
	 			jQuery('#btnfrmCellManningmainFunLoc').unbind("click");
	 			entryMode();
			 }
	 		else
				{alert("Select Cell");
				return false;}
			 });
	
	 	      jQuery('#btnInsert').click(function(){
			 	var cytmKeyid=jQuery('#hdnCycletimeId').val();
			 	//alert("CycleTimeKeyId"+cytmKeyid);
				var cytmProduct=jQuery('#cmbcytmProductid').combobox('getValue');
				//alert("The Product"+cytmProduct);
			//	var cytmProdgroupid=jQuery('#cmbcytmProdgroupid').combobox('getValue');
				//alert("Product groupid"+cytmProdgroupid);
				//var equipment=jQuery('#cmbcytmMachineid').combobox('getValue');
				var cellId=jQuery('#cmbcytmCellid').combobox('getValue');
			//	alert("The CellID"+cellId);	
				var manpower = jQuery('#txtcytmManpower').val();
			//	alert("The ManPower"+manpower);
				var cycleTime = jQuery('#txtcytmCycletime').val();
				//alert("The CycleTime"+cycleTime);	
				var dtecytmFromdate =jQuery('#dtecytmFromdate').datebox('getValue');
			//	alert("DateFormat"+dtecytmFromdate);
	
		  //	var mandrels=jQuery('#txtcytmMandrels').val();
			//	var cavity=jQuery('#txtcytmCavity').val();
			/*	if(equipment.trim() == "" || equipment == null)
				{
					alert("Select Equipment");
					return false;
				} */
				
	     if(cellId.trim()=="" || cellId==null){
	      alert("Select Cell");	
	      return false;
	    }
	    	else if(cytmProduct.trim()=="" || cytmProduct==null)
				{
					alert("Select Product");
					return false;
				}
			
				else if(manpower.trim() == "" || manpower == null)
				{
					alert("Enter Manpower");
					return false;
				}
				else if(cycleTime.trim()=="" || cycleTime==null)
				{
					alert("Enter Cycle Time");
					return false;
				}
				
				else if(dtecytmFromdate.trim()=="" || dtecytmFromdate==null)
				{
					alert("Select Date");
					return false;
				}
				else if(manpower.trim()=="" || manpower.trim()<=0) //.match(/[0* ]+/))
				{
					alert("Man Power should be greater than zero");
					return false;
				}
				
			/*	else if(cavity.trim()=="" ||cavity==null)
				{
					alert("Enter Cavity");
					return false;
				}
				else if(mandrels.trim()=="" ||mandrels==null)
				{
					alert("Enter Mandrels");
					return false;
				} */
				else
				{
					var rowObject =[];
					var id=jQuery("#pcsCycletimeGrid").getGridParam('reccount')+1;
					//alert(" Id:Inside else condition "+id);
					rowObject[0] = new Object();
					//rowObject[0].key=id;
					(rowObject[0])['txtcytmKeyid'] =cytmKeyid;
					(rowObject[0])['cmbcytmFactoryid'] = jQuery("#frmCellManning input[id='factory']").val();
					(rowObject[0])['cmbcytmSectionid'] =  jQuery("#frmCellManning input[id='section']").val();
					(rowObject[0])['txtcytmCellid'] = jQuery("#frmCellManning input[id='cell']").val();
				//	(rowObject[0])['cmbcytmMachineid'] = jQuery("#frmCellManning input[id='cmbcytmMachineid']").combobox('getValue');
				    (rowObject[0])['cmbcytmCellid'] = jQuery("#frmCellManning input[id='cmbcytmCellid']").combobox('getValue');
					(rowObject[0])['cmbcytmProductid'] = cytmProduct;
					//(rowObject[0])['cmbcytmProdgroupid'] = cytmProdgroupid;
					(rowObject[0])['txtcytmCycletime'] = cycleTime;
					(rowObject[0])['txtcytmManpower'] = manpower;
					//(rowObject[0])['txtcytmCavity'] = cavity;
					//(rowObject[0])['txtcytmMandrels'] = mandrels;
					(rowObject[0])['dtecytmFromdate'] = dtecytmFromdate;
					(rowObject[0])['txtSelectionid'] = "INSERT";
					 wrap =jQuery(rowObject);
					 //alert(" Inside else ::cytmProduct "+cytmProduct+"cytmProdgroupid "+cytmProdgroupid+" cycleTime"+cycleTime+"manpower"+manpower+"cavity"+cavity+"mandrels"+mandrels+"dtecytmFromdate"+dtecytmFromdate);
					 //alert("cytmKeyid  "+cytmKeyid);
					 var rowDataExist = jQuery("#pcsCycletimeGrid").getRowData(parseInt(cytmKeyid));	
					 //alert(" rowDataExist:Inside else condition "+Object.keys(rowDataExist));			
					if( cytmKeyid.trim()!="" || cytmKeyid!="" )//isEmpty(rowDataExist)
					{
						 //alert(" Inside cytmKeyid.trim()");
						 /*	var option=confirm("Data Already Exists...  Do You Want To Update? ");
							//if(option==true)
							//{
							*/	
							
								//rowDataExist['txtcytmMandrels'] = mandrels;
								rowDataExist['txtcytmCycletime'] = cycleTime;
								rowDataExist['txtcytmManpower'] = manpower;
								//rowDataExist['txtcytmCavity'] = cavity;
								rowDataExist['dtecytmFromdate'] = dtecytmFromdate;	
								rowDataExist['txtSelectionid'] = "UPDATE";	
								jQuery("#pcsCycletimeGrid").setRowData(jQuery('#hdnRowid').val(),rowDataExist,true);
								saveForm("frmCellManning","CellMng_save.balcllmng");
							//}
					}	
					else
					{
						jQuery("#pcsCycletimeGrid").addRowData(id,rowObject,'last',id);
						jQuery('tr[id=undefined]').attr('id',id);
						saveForm("frmCellManning","CellMng_save.balcllmng");
					}	
				}
				//}		
		 });
	 
		jQuery('#btnDelete').click(function(){//ImgDelete
			var cytmKeyid= jQuery('#hdnCycletimeId').val();
			if(cytmKeyid!=null&&cytmKeyid.trim()!="")
			{
				var r=confirm("Are you sure to Delete ? ");
				if (r==true)
	 			{
					 //var rowDataExist = jQuery("#pcsCycletimeGrid").getRowData(parseInt(cytmKeyid));
					 deleteRecord("frmCellManning","CellMng_delete.balcllmng?cytmKeyid="+cytmKeyid);
				}
			}
		});
		
		/* function frmCellManning_deleteSuccessCallback(result)
		{
			frmClear();
			jQuery('#pcsCycletimeGrid').trigger('reloadGrid');	
		} */
		
		//elumalai
		function frmCellManning_deleteSuccessCallback(result)
	{
	    var msg = "";
	    if (result) {
	        if (typeof result === "string") {
	            msg = result;
	        } else {
	            msg = result.msg || result.message ||
	                  (result.successData && (result.successData.msg || result.successData.message)) ||
	                  (result.data && (result.data.msg || result.data.message)) || "";
	        }
	    }
	    alert(msg ? msg : "Data deleted successfully");
	
	    frmClear();
	    jQuery('#pcsCycletimeGrid').trigger('reloadGrid');
	}
		
		jQuery('#chkGrpBy').click(function(){
			if(jQuery('#chkGrpBy').is(':checked')==true)
				{jQuery('#VwEffective').hide();
				jQuery('#chkVwEfftDt').attr('checked',false);
				}
			else
				jQuery('#VwEffective').show();
			 });
	
		jQuery('#btnClear').click(function(){
			frmClear();
		});
	
		jQuery('#btnVw').click(function(){
			//alert("buttonClick");
			fillCellManningData();
			jQuery('#pcsCycletimeGrid').trigger('reload');
		});
	
		function frmClear()
		{
			clearField('txtcytmManpower');
			clearField('txtcytmCycletime');
			clearField('cmbcytmProductid');
		//	clearField('cmbcytmProdgroupid');
		//	clearField('txtcytmMandrels');
		//	clearField('txtcytmCavity');
			jQuery('#hdnCycletimeId').val('');
			jQuery('#hdnRowid').val('');
		//	jQuery('#cmbcytmCellid').combobox('enable');
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
	
		function entryMode(){
			//alert('EntryMode');
			jQuery('#CstDiv').hide();
			jQuery('#mnPwr').show();
		//	jQuery('#mandrelCavity').show();
			jQuery('#viewmdediv').hide();
			jQuery('#entrymdediv').show();
			//jQuery('#cycletimeDate').css("display","block");
			/*readOnlyFields('txtcytmManpower');
			readOnlyFields('txtcytmCycletime');
			readOnlyFields('txtcytmMandrels');
			readOnlyFields('txtcytmCavity');*/
		}
		function viewMode(){
			jQuery('#CstDiv').show();
			jQuery('#mnPwr').hide();
		//	jQuery('#mandrelCavity').hide();
			jQuery('#entrymdediv').hide();
			jQuery('#viewmdediv').show();
			jQuery('#cycletimeDate').hide();
			//jQuery('#cmbcytmMachineid').combobox('enable');
			jQuery('cmbcytmCellid').combobox('enable');
			jQuery('#btnfrmCellManningmainFunLoc').bind("click");
		}
		jQuery('#chkVwEfftDt').click(function(){
			
			});
	</script>
	<form id="frmCellManning">
	 <div id="wrapper" > 
		<div    style="width: 100%;margin-top:0px;border: 1px solid #a4a4a4;height:445;margin-top:-10px;margin-top:0px\9;">
		<div id="cytmfunLocation" style="padding-left: 55px;width:760px;margin-top:6px;"></div> 
		<table   rules="none" border="0" width="30%">
	<tr>
	<!--left  pane -->
				<td   width="20%" >	
				 		<div style="margin-left: 56px;padding-right: 10px;width:300px;">
							<!--  <div style="margin-top: 1%;"><label class="mandatory-lbl">Equipment</label></div> -->
							<div style="margin-top: 1%;"><label class="mandatory-lbl">Cell</label>
					<div style="margin-top: 1%;"><input id="cmbcytmCellid" name="cmbcytmCellid" class="easyui-combobox" style="width: 290px;"/></div>	
							<div id="CstDiv">
							<div style="margin-top: 2%;"><label>Cost Center</label></div>
							<div style="margin-top: 1%;">
								<input  id="cmbCostCenter" name="cmbCostCenter" class="easyui-combobox"  style="width: 290px;" /></div>
							</div>
					 	<div  id="mnPwr" >
					 		<div style="margin-top: 2%;">
										<span style="padding-right: 9%"><label class="mandatory-lbl">Man Power</label></span>
										<span style="padding-right: 10%"><label class="mandatory-lbl">Cycle Time</label></span>
										<span><label class="mandatory-lbl">Date</label></span>			
							</div>
					 		<div   style="margin-top: 1%;">
										<span><input  id="txtcytmManpower" name="txtcytmManpower" class="easyui-text" value="${requestScope.pcsTlCycletimemst.cytmManpower}"  maxlength="4" style="width: 30%;height:22px;"/></span>
										<span><input  id="txtcytmCycletime" name="txtcytmCycletime" class="easyui-text" value="${requestScope.pcsTlCycletimemst.cytmCycletime}" maxlength="8" style="width: 30%;height:22px;"/></span>
										<%-- <span><input id="dtecytmFromdate" name="dtecytmFromdate" class="easyui-datebox" value="${requestScope.pcsTlCycletimemst.cytmFromdate}" style="width: 100%;height:20px;"/></span> --%>
										<span style="display:inline-block;width:100px;">
	    <input id="dtecytmFromdate" name="dtecytmFromdate" class="easyui-datebox" 
	           value="${requestScope.pcsTlCycletimemst.cytmFromdate}" 
	           style="width: 100px;height:20px;"/>
	</span>
							<input id="hdncytmKeyid" name="hdncytmKeyid" type="hidden" value="${requestScope.pcsTlCycletimemst.cytmKeyid}"  />
							</div>
					 	</div>
					 	</div>
					</td>
					<td valign="top">
						<div  style="width: 360px;  margin-left: 5px;">
					        <div style="margin-top: 0%;">
					        	<span style="padding-right: 120px;"><label>Product</label></span>
					        	<span><label>Product Model</label></span>
					        </div>
					        
					        <!-- elumalai -->
					        <div style="margin-top: 4px; white-space: nowrap;">
	    <span style="padding-right: 10px;"><input id="cmbcytmProductid" name="cmbcytmProductid" class="easyui-combobox" value="${requestScope.pcsTlCycletimemst.cytmProductid}" style="width: 150px;"/></span>
	    <span><input id="cmbcytmProdgroupid" name="cmbcytmProdgroupid" class="easyui-combobox" value="${requestScope.pcsTlCycletimemst.cytmProdgroupid}" style="width: 150px;"/></span>
	    <span id="viewmdediv" style="display:inline-block; margin-left:10px; vertical-align:top;">
	        <input type="button" id="btnEntryMde" class="easyui-button" value="Entry Mode" style="height:24px; width:80px;"/>
	    </span>
	</div>
					        
					        
							<%-- <div style="margin-top: 4px;">
							<span style="padding-right: 10px;"><input  id="cmbcytmProductid" name="cmbcytmProductid" class="easyui-combobox" value="${requestScope.pcsTlCycletimemst.cytmProductid}" style="width: 150px;"/></span>
							<span><input id="cmbcytmProdgroupid" name="cmbcytmProdgroupid" class="easyui-combobox" value="${requestScope.pcsTlCycletimemst.cytmProdgroupid}" style="width: 150px;"/></span>
							</div> --%>
							
							
					<!--  	<div id="mandrelCavity">
								 <div style="margin-top: 2%;">
								 <span style="padding-right: 130px;"><label  class="mandatory-lbl">Cavity</label></span>
								 <span><label  class="mandatory-lbl">Mandrels</label></span>
								 </div>
								<div style="margin-top: 1%;">
									<span style="padding-right: 10px;"><input  id="txtcytmCavity" name="txtcytmCavity" class="easyui-text"  style="width: 150px;"/></span>
									<span><input  id="txtcytmMandrels" name="txtcytmMandrels" class="easyui-text"  style="width: 150px;"/></span>
								</div>
							</div>
			!-->
			
					</div>
					</td>
		</tr>
	</table>	       <!-- 
				       	 <div class="clear" style="width:400px;"></div>
				       	  	<div id="viewmdediv" style="width:140px;margin-left:84%;margin-left:83%\9;">	
				       <div  style="width:140px;margin-left:px;">
				       		<span><input type="button" id="btnVw" value="View" class="easyui-button" style="height: 24px;width:50px;"/></span>
				    	   
				    	    <span><input type="button" id="btnEntryMde" class="easyui-button" onclick="" value="Entry Mode" style="height: 24px;width:80px;"/></span>
				       </div>
				        </div> -->
				       
				   
				   <div id="entrymdediv" style="display: none;">	
				   		<div  style="padding-right: 0px;"></div>
						<div  style="padding-right: 0px;">
						<div >
						
						</div>
				    	 </div>
				    	 <div class="clear"></div>
				 <table  style="margin: 0%;">
				   <tr>
				    <td>
				       <div style="padding-left: 75.2%;padding-left: 70.2\9%;white-space: nowrap; width: 100%;">
				      
				       			<span style="display:inline-block;"><input type="button" id="btnInsert" class="easyui-button" value="Insert" style="height: 2%"/></span>
				       			<span style="display:inline-block;"><input type="button" id="btnDelete" class="easyui-button" value="Delete" style="height: 2%" /></span>
				       			<span style="display:inline-block;"><input type="button" id="btnClear" class="easyui-button" value="Clear" style="height: 2%"/></span>
				     		 	<span style="display:inline-block;"><input type="button" id="btnVwMde" class="easyui-button" onclick="viewMode()" value="View Mode" style="height: 2%;width:80px;"/></span> 
				       
				       </div>
				     	</td>
					</tr>	
				</table> 
				  </div>
				<div  id="frmCellManningFuntKeyIds" style="width:400px;" >
					<input type="hidden" id="factory" name="cmbcytmFactoryid" value="${requestScope.pcsTlCycletimemst.cytmFactoryid}"></input>
					<input type="hidden" id="section" name="cmbcytmSectionid" value="${requestScope.pcsTlCycletimemst.cytmSectionid}"></input>
					<input type="hidden" id="cell" name="cmbcytmCellid" value="${requestScope.pcsTlCycletimemst.cytmCellid}"></input>
			     <input type="hidden" id="machine" name="cmbcytmMachineid1" value="${requestScope.pcsTlCycletimemst.cytmMachineid}"></input>  
				</div>				
	 <%-- <div class="clear"></div> 
				 	<div id="griddiv" style="float:left;margin-left:-2%;">
						<div style="margin-left:30px;"><table id="pcsCycletimeGrid" width="50%" style="float:left;"></table></div>
						<div id="pcsCycletimePager"></div> 
						<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>	 
						<input type="hidden" id="hdnCycletimeId"  value=" " />
						<input type="hidden" id="hdnRowid"/>
					</div>  --%>
					
					<!-- elumalai -->
					<div class="clear"></div>
	<div id="griddiv" style="width:100%; overflow-x:auto; clear:both; margin-top:10px;">
	    <table id="pcsCycletimeGrid"></table>
	    <div id="pcsCycletimePager"></div>
	    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	    <input type="hidden" id="hdnCycletimeId" value=" "/>
	    <input type="hidden" id="hdnRowid"/>
	</div>
	</div>
	</div>
	</form> 