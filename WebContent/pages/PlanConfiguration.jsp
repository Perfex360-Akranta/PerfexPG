

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			checkboxsel();			//alert('hi');

		var url = jQuery('#hiddenUrl').val();
		var mode= getFilterValue(url+'&',"mode");
		initialiseForm("frmPlanConfiguration");
		fillComboBox("frmPlanConfiguration","cmbPplcMachineid","machineCombo.commonFilter");
		fillComboBox("frmEquipment","cmboptions","combo_options.plnconfig");
		fillComboBox("frmEquipment","cmbPwrmlevel","combo_Worespoptions.plnconfig");
		
		/*for setting cmb options to equipment as default*/
		if(jQuery('#chMPplcLevel').is(':checked') == true)
		{
			jQuery('#chMPplcLevel').val("M");
			jQuery('#cmboptions').combobox('setValue','MCHM');
			if(jQuery('#chMPplcLevel').is(':checked') == true)
			{
				jQuery('#cmboptions').combobox('setValue','MCHM');
				var cellId =jQuery('input:[name=cmbPplcCellid]').val();
				jQuery('#hdnchk').val('MCHM');
				//if(cellId != null || cellId != "")
					//alert("select cell");
			}
		}	
		readOnlyFields('cmboptions');
		//readOnlyFields('cmbPwrmlevel');
		jQuery('#submitForm').val('frmPlanConfiguration'); // set the id of form to submit
		formatDateBox('dtenextduedate','dd-MMM-yyyy');
		
			//alert("hiden mode  "+hdnMode);
		processGridnew(url,"","planconfig","planconfig_pager","","dblclick","","planconfig_loadComplete");
		if(screen.width >= 1280){
			jQuery("#wrapper").css('width','1094px');
		}
				
});

	jQuery("#cmbPplcMachineid").combobox({onRequest:function( ){
		var fctid = jQuery("#frmPlanConfiguration input[id='factory']").val();
		var machId = jQuery("#frmPlanConfiguration input[id='cmbPplcMachineid']").combobox("getValue");
		 //jQuery("#frmPlanConfiguration "+fieldId).combobox("getValue");
		var cellid = jQuery("#frmPlanConfiguration input[id='cell']").val();
	//	var flid = jQuery("#frmPlanConfiguration input[id='flid']").val();
		filterStr = '&factId='+fctid;
		filterStr +='&machId='+machId;
		filterStr +='&cellId='+cellid;
	//	filterStr +='&flid='+flid;
	
	 	 
		return filterStr;
	}}); 	
		jQuery('#chMPplcLevel').click(function(){
			chekChkBox("eqp");
			jQuery('#chkApplyToall').attr('checked',false);
			jQuery('#chkApplyToallAssm').attr('checked',false);
		});
		jQuery('#chAPplcLevel').click(function(){
			chekChkBox("assm");
			jQuery('#chkApplyToall').attr('checked',false);
			jQuery('#chkApplyToallAssm').attr('checked',false);
			});
		/*chkbox function*/
		 function chekChkBox(chkname){
		 if(chkname == "eqp"){
				if(jQuery('#chMPplcLevel').is(':checked') == true)
				{jQuery('#chMPplcLevel').val("M");
					jQuery('#cmboptions').combobox('setValue','MCHM');
					var cellId =jQuery('input:[name=cmbPplcCellid]').val();
					var chkval = "MCHM";
					jQuery('#hdnchk').val(chkval);
					//checkboxsel(chkval);
					var cellId =jQuery('input:[name=cmbPplcCellid]').val();
					if(cellId != null || cellId != "")	
					  // alert('select Cell');			
					jQuery('input:checkbox[name=chAPplcLevel]').attr('checked',false);
					jQuery('#chAPplcLevel').val("");
				}	
				else{
					var chkval = "ASSM";
					
					jQuery('#hdnchk').val(chkval);
					 //alert(' select Equipment');
					//checkboxsel(chkval);
					jQuery('input:checkbox[name=chAPplcLevel]').attr('checked',true);
					jQuery('#cmboptions').combobox('setValue','ASSM');
					jQuery('#chMPplcLevel').val("");
					jQuery('#chAPplcLevel').val("A");
					
				}		
			
		 }
		 else{
				if(jQuery('#chAPplcLevel').is(':checked') == true)
				{
					jQuery('#chMPplcLevel').val("");
					jQuery('#chAPplcLevel').val("A");
					jQuery('#cmboptions').combobox('setValue','ASSM');
					jQuery('input:checkbox[name=chMPplcLevel]').attr('checked',false);
					jQuery('#chMPplcLevel').val("");
					var chkval = "ASSM";
					//checkboxsel(chkval);
					jQuery('#hdnchk').val(chkval);
					var machId =jQuery('#frmPlanConfiguration  input:[id=machine]').val();
					if((machId != null && machId != "" && machId != " ")){
					  // alert('22 select Equipment');
					   //clearField('cmbPplcMachineid');
					}
					else if(cellId != null && cellId != "" && cellID != " "){
						//clearField('cmbPplcMachineid');
					  // alert('select Cell');
					}
				}
				else{
					var chkval = "MCHM";
					jQuery('#cmboptions').combobox('setValue','MCHM');
					jQuery('#hdnchk').val(chkval);
					//checkboxsel(chkval);
					jQuery('input:checkbox[name=chMPplcLevel]').attr('checked',true);
					jQuery('#chAPplcLevel').val("");
					jQuery('#chMPplcLevel').val("M");
				}			
		 	}	
		}
	function getDataFunc(){
		var url = jQuery('#hiddenUrl').val();
		var chkValue=jQuery('#hdnchk').val();
		var filterStr;
		
		
		var fctid = jQuery("#frmPlanConfiguration input[id='factory']").val();
		var machId = jQuery("#frmPlanConfiguration input[id='cmbPplcMachineid']").combobox("getValue");
		 //jQuery("#frmPlanConfiguration "+fieldId).combobox("getValue");
		var cellid = jQuery("#frmPlanConfiguration input[id='cell']").val();
		var flid = jQuery("#frmPlanConfiguration input[id='flid']").val();
		filterStr = '&fctid='+fctid;
		filterStr +='&machId='+machId;
		filterStr +='&cellid='+cellid;
		filterStr +='&flid='+flid;
		filterStr +='&chkValue='+chkValue;
		 //alert(filterStr);
		if((cellid != undefined && cellid.trim().length > 0)||(machId != undefined && machId.trim().length > 0)){
			 processGridnew(url,"?q=2filterStr="+filterStr,"planconfig","planconfig_pager","","","","planconfig_loadComplete");
			 
			 jQuery( ' #chMPplcLevel ').attr('disabled',true);
			 jQuery( ' #chAPplcLevel ').attr('disabled',true);
			 //disableField('frmPlanConfiguration','chMPplcLevel');
			 //disableField('frmPlanConfiguration','chAPplcLevel');
			 disableField('frmPlanConfiguration','cmbPplcMachineid');
			 disableField('frmPlanConfiguration','btnfrmPlanConfigurationmainFunLoc');
			 /*disabling functional location*/
			 jQuery("#btnfrmPlanConfigurationmainFunLoc").click(function() { 
			 jQuery("#functLocHierarPopupId").dialog('close'); });
			 jQuery('#dispFunctionalLoc a').click(function() { jQuery("#functLocHierarPopupId").dialog('close'); });
			
		}
		else{
				if(jQuery('#chMPplcLevel').is(':checked') == true)
				{
					alert("Select Line and View");
				}
				else
					alert("Select Machine and View");
			}
	}
		jQuery('#pcViewBtn').click(function(){
			//alert('view');
			if(jQuery('#chkApplyToall').is(':checked') == true){
				 jQuery('#chkApplyToall').attr('checked',false);
				 jQuery('#chkApplyToallAssm').attr('checked',false);
			}
			if(jQuery('#chkApplyToallAssm').is(':checked') == true){
				 jQuery('#chkApplyToall').attr('checked',false);
				 jQuery('#chkApplyToallAssm').attr('checked',false);
			}
		getDataFunc();
		});	 
		jQuery('#btnClear').click(function(){
			jQuery('#chMPplcLevel ').attr('disabled',false);
			jQuery('#chAPplcLevel ').attr('disabled',false);
			//enableFields('chMPplcLevel');
			//enableFields('chAPplcLevel');
			enableFields('cmbPplcMachineid');
			clearField('cmbPplcMachineid');
			loadFunctionalLocation("pplcfunLocation","functionalLoc.plnconfig","pplcfunLocationValues","frmPlanConfiguration","");
			enableFields('btnfrmPlanConfigurationmainFunLoc');
			jQuery("#planconfig").clearGridData();
			/*Enabling functional location*/
			//jQuery("#btnfrmPlanConfigurationmainFunLoc").click(function() { 
				//jQuery("#functLocHierarPopupId").dialog('open');
			//});
			// jQuery('#dispFunctionalLoc a').click(function() { jQuery("#functLocHierarPopupId").dialog('open'); });
			 
			 
		});
		function planconfig_loadComplete(){
			
			/*for changing color if pm extists */
			var planconfigId = jQuery("#planconfig").jqGrid('getDataIDs');
			 for(i=1;i<=planconfigId.length;i++)	
			 {			
				if(jQuery("#planconfig").getCell(i, 'pmstand')=="Y")
					jQuery("#planconfig").jqGrid('setCell',i,"Equipmentno","",{'color':'#000'});						
					
				if(jQuery("#planconfig").getCell(i, 'pmstand')=="N")
					jQuery("#planconfig").jqGrid('setCell',i,"Equipmentno","",{'color':'#F769EF'});
							
			 }		
			/**/
			jQuery("#planconfig").jqGrid( 'setGridParam',{subGridRowExpanded: function(subgrid_id, row_id) {
				/** to reduce the colspan of Sub Grid <td>**/
				jQuery('.subgrid-data').attr('colspan','9');
				jQuery('.subgrid-data').css('border-right','#fff');
				/*End*/
				var subgrid_table_id, pager_id;
				 subgrid_table_id = subgrid_id+"_t"; pager_id = "p_"+subgrid_table_id;
				
				jQuery("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
				
				var rowData = jQuery("#planconfig").jqGrid('getRowData',row_id);
				
				var selId = rowData.machineid;
				var keyId = rowData.worespkeyid;
				alert("keyId inside load complete "+keyId);
				//var optedValue=getFieldValue('cmbPwrmlevel');
				var filterStrldCmplted ="";
				var fctid = getFieldValue('factory');
				var machId = getFieldValue('machine');
				var cellid = getFieldValue('cell');
				var sectid = getFieldValue('section');
				var costcenterid = getFieldValue('cmbCostcentreid');
				filterStrldCmplted ='&workMstKeyId='+keyId;
						
				processGridnew("wrkOdrResp_input.plnconfig",filterStrldCmplted,subgrid_table_id,pager_id,'','',"","subGrid_loadComplete");
				jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false});
			
		}
	});
			
  }
/*color formatter if pmstandard Exists*/
  function colorFormatterC(cellvalue, options, rowObject) 
	{
	   
	  var rowId = options.rowId;
		var cm = jQuery("#planconfig").jqGrid("getGridParam", "colModel");
		
		
	
	}
 
 /*End*/
	
	function checkForZeroes(tableId,selId,colNo)
	{
		var Col = colNo+1;
	 
		while(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != null)
		{	
			 
			if(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != '0')
				return true;
			Col++;
		}
		 
		return false;
			
	}
	function uncheckApplytoAll(){
		jQuery('#chkApplyToall').attr('checked',false);
		jQuery('#chkApplyToallAssm').attr('checked',false);
		}
	/**Apply to all machine**/
	 jQuery('#chkApplyToall').click(function(){
		 var applytoall= '';
		 jQuery('#chMPplcLevel').attr('checked',true);
		 jQuery('#chAPplcLevel').attr('checked',false);
		 jQuery('#chkApplyToallAssm').attr('checked',false);
		 
		 chekChkBox("eqp");
		 jQuery('#chkApplyToallAssm').attr('checked',false);
		 
			if(jQuery('#chkApplyToall').is(':checked') == true)
			{
				jQuery('#chMPplcLevel').val("M");
				 jQuery('#chAPplcLevel').val(" ");
				
				var machId = getFieldValue('machine');
				var cellid = getFieldValue('cell');
				var rowId = jQuery("#planconfig").jqGrid('getDataIDs');
				 applytoall='M';
				 var eleType = jQuery("#frmPlanConfiguration input[id=elementType]").val();
				 if(eleType == "CELL" || ( cellid != " " && cellid != "" && cellid != null ) ||(machId != " " && machId != "" && machId != null))
					showaddPlan(rowId,applytoall);
				 else{
					alert("Select Cell");
					 jQuery('#chkApplyToall').attr('checked',false);
				 }
			}
			else{
				 applytoall='';
				}
	  });	
	  /**Apply to all assembly**/
	   jQuery('#chkApplyToallAssm').click(function(){
		 var applytoall= '';
		 jQuery('#chAPplcLevel').attr('checked',true);
		 jQuery('#chMPplcLevel').attr('checked',false);
		 jQuery('#chkApplyToall').attr('checked',false);
		 jQuery('#chMPplcLevel').val("");
		 chekChkBox("assm");
			
			if(jQuery('#chkApplyToallAssm').is(':checked') == true)
			{
				jQuery('#chAPplcLevel').val("A");
				jQuery('#chMPplcLevel').val(" ");
				
				var machId = getFieldValue('machine');
				var cellid = getFieldValue('cell');
				var rowId = jQuery("#planconfig").jqGrid('getDataIDs');
				 applytoall='A';
				 var eleType = jQuery("#frmPlanConfiguration input[id=elementType]").val();
				 if(eleType == "MCHM" ||(cellid != " " && cellid != "" && cellid != null )||(machId != " " && machId != "" && machId != null))
					showaddPlan(rowId,applytoall);
				 else{
					 alert("Select Machine");
					 jQuery('#chkApplyToallAssm').attr('checked',false);
				 }
			}
			else{
				 applytoall='';
				}
	  });	
	  /**End**/
	  
	function showaddPlan(rowId,applytoall){
		
		
		var rowData = ""; 
		var selId = "";
		var keyId = "";
		var assmId = "";
		var worespkeyId = "";
		
		if (rowId != "" && rowId!= " " && rowId!= null)
			{
			rowData = jQuery("#planconfig").jqGrid('getRowData',rowId);
		selId = rowData.machineid;
		//alert("oo  "+selId);
		/*if(rowId.length>1){
			
			for(var i=0;i<rowId.length;i++){
				rowData = jQuery("#planconfig").jqGrid('getRowData',rowId[i]);
				selId = rowData.machineid;
				alert("oo  "+selId);
			}
		}
		else{
			alert("ll  "+selId);	
			rowData = jQuery("#planconfig").jqGrid('getRowData',rowId);
		}*/
			
		
		
		
		keyId = rowData.keyid;
		alert("keyId add plan inside"+keyId);
		assmId = rowData.assmid;
		/*for opening Woresp*/
		worespkeyId = rowData.worespkeyid;
		alert("worespkeyId add plan inside"+worespkeyId);
	  }
		/*var fctid = getFieldValue('factory');
		var machId = getFieldValue('machine');
		var cellid = getFieldValue('cell');
		var sectid = getFieldValue('section');*/
		var fctid = jQuery("#frmPlanConfiguration input[id='factory']").val();
		var machId = jQuery("#frmPlanConfiguration input[id='machine']").val();
		var cellid = jQuery("#frmPlanConfiguration input[id='cell']").val();
		var sectid = jQuery("#frmPlanConfiguration input[id='section']").val();
		var flid = jQuery("#frmPlanConfiguration input[id='flid']").val();
		var costcenterid = getFieldValue('cmbCostcentreid');
		var chkValue=jQuery('#hdnchk').val();
		var filterStr=" ";
		
		filterStr = '&applytoall='+applytoall;
		filterStr += '&chkValue='+chkValue;
		filterStr += '&fctid='+fctid;
		if(machId != "" && machId != " " && machId != null )
			filterStr +='&machId='+machId;
		else
			filterStr +='&machId='+selId;
		filterStr +='&cellid='+cellid;
		filterStr +='&sectid='+sectid;
		filterStr +='&costcenterid='+costcenterid;
		filterStr +='&flid='+flid;

		if(worespkeyId != "" && worespkeyId != " " && worespkeyId != null )
			filterStr +='&workMstKeyId='+worespkeyId;
		else
			filterStr +='&workMstKeyId=" "';
		/*End*/
		//alert(selId+"----"+worespkeyId);
		var datstr = "&rowmachId="+selId;
		datstr +="&grdmachId="+selId;
		if(keyId != "" && keyId != "undefined" && keyId != undefined && keyId != null && keyId.trim().length>0)
			datstr +="&keyId="+keyId;
		
		
		if(jQuery('#chAPplcLevel').is(':checked') == true)
		{
			//alert(assmId);
			datstr += "&assmId="+assmId;
		}
		var filterString = datstr;
	  //alert(filterStr);
	    datstr += "&filterStr="+filterStr;
	    //jQuery('#subFormPopUpId').html(' ');
	    jQuery('#submitForm').val('');
	    //alert(jQuery('#subFormPopUpId').html());
	    jQuery('#subFormPopUpId').html(' ');
	    //alert(jQuery('#subFormPopUpId').html());
		//subFormPop("year_pop.plnconfig", "300","20","325", "598","PlanConfiguration",datstr);
		LoadPopUp("divplanconfig","year_pop.plnconfig?q=2&datstr="+escape(datstr)+filterString, true,"598px","360px","0px","20%", "multiSelectOk_Callback","Plan Configuration");
		jQuery('.searchLayerFuncLocn').html(' ');
		jQuery('#loadSubFormPopUp').html(' ');
		jQuery('#subFormPopUpId').html(' ');
		jQuery('#popHead').html(' ');
		
	}	
	function showWOResp(rowId){
		//alert('l');
		//jQuery('#hdnControls').val('');
		var rowData = jQuery("#planconfig").jqGrid('getRowData',rowId);
		var selId = rowData.machineid;
		//var plankeyId = rowData.worespkeyid;
		alert("selId"+selId);
		var keyId = rowData.worespkeyid;
		alert("keyId inside responsible"+keyId)
		//var optedValue=getFieldValue('cmbPwrmlevel');
		var filterStr="?q=2";
		var fctid  = jQuery("#frmPlanConfiguration input[id='factory']").val();
		var machId = jQuery("#frmPlanConfiguration input[id='machine']").val();
		var cellid = jQuery("#frmPlanConfiguration input[id='cell']").val();
		var sectid = jQuery("#frmPlanConfiguration input[id='section']").val();
		var flid   = jQuery("#frmPlanConfiguration input[id='flid']").val();
		var costcenterid = getFieldValue('cmbCostcentreid');
		filterStr  = '&fctid='+fctid;
		filterStr +='&machId='+machId;
		filterStr +='&cellid='+cellid;
		filterStr +='&sectid='+sectid;
		filterStr +='&costcenterid='+costcenterid;
		filterStr +='&flid='+flid;
		
		filterStr +='&grdmachId='+selId;
	 	  //filterStr +='&workMstKeyId='+keyId;
		 //filterStr +='&keyId='+keyId;
		//alert(selId+"----"+keyId);
		if(keyId != null && keyId != "" && keyId != " " )
				filterStr +='&workMstKeyId='+keyId;
			else
				filterStr +='&workMstKeyId=';
		
		
		/*if(plankeyId != null && plankeyId != "" && plankeyId != " " )
			filterStr +='&plankeyId='+plankeyId ;
		else
			filterStr +='&plankeyId=" "';*/
			//alert("filterStr  :"+filterStr);
			//LoadPopUp("divplanconfig","wrkOdrResp_input.plnconfig?q=2&datstr="+escape(filterStr), true,"598px","300px","0px","20%", "multiSelectOk_Callback","WorkOrder Responsibility");
			
			subFormPop("wrkOdrResp_input.plnconfig","300","20","385", "608","WorkOrderResponsibility",filterStr);
	}
	function frmPlanConfigurationpop_exceptionCallback(){alert('exception');}
	function frmPlanConfigurationpop_phencauseLinkCallBack(){//alert('no key id');
		}
	function frmPlanConfigurationpop_beforeSubmit(){
		jQuery('#planconfig').trigger("reloadGrid");
		var inputs = jQuery('#frmPlanConfiguration  :input');	
		var controlId ;
		var datString='' ;
		
		jQuery(inputs).each(function () {
			//alert("ds"+datString);
		controlId = this.id;
		controlName = this.name;
		
		var valueInp;
		
		if(controlId != null && controlId != "" )	
		{	
			//alert("ct"+controlId);
			valueInp = getval(controlId);
			datString +="&"+controlName+'='+valueInp;	 
			//alert("vi"+valueInp);
		}
		
		});	
		//alert(datString);
		return datString; 
		jQuery('#planconfig').trigger("reloadGrid");
		
		}
	
	/*  commemt */
/* 	function frmPlanConfigurationpop_onSuccess(response){
	    jQuery('#planconfig').trigger("reloadGrid");
	}
 */	
	function getval(fieldId)
	{
		//alert(fieldId);
		if(fieldId.substring(0,3) == "cmb")
			val = jQuery("#"+fieldId).combobox("getValue");
		else if(fieldId.substring(0,3) == "dte")
			val = jQuery("#"+fieldId).datebox("getValue");
		else
			val = jQuery("#"+fieldId).val();

		//alert("val  "+val);
		return val;
	}
	
	/*function yearLoad_OnSuccess()
	{
		alert('sucess');
	}
	function yearLoad_OnError()
	{
		alert('failure');
	}*/
	function  frmPlanConfigurationcmbPplcMachineid_onSelect(record)
	{
		fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"","","","", "","cmbCostcentreid");
		//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbGmntLineid","cmbGmntSectionid","cmbGmntFactoryid","cmbComp");
		loadFunctionalLocation("pplcfunLocation","functionalLoc.plnconfig","pplcfunLocationValues","frmPlanConfiguration","&machId="+record.id);
	}
	function  frmPlanConfigurationcmbPplcMachineid_onLoadSuccess()
	{
		fillComboBox("frmEquipment","cmbCostcentreid","costCenter.commonFilter");
	}
	function  frmPlanConfigurationcmbCostcentreid_onLoadSuccess()
	{
		
	}
	
	 function BtnFormatteraddpln(id, options, rowObject)
	  {					
	  	var rowId = options.rowId;
	  //alert('s');
	  var applytoall='N';
	  	return '<input type="button" id="btnaddPlan" class="easyui-button" style="height:21px;height:16px\9;width:120px\9;font-size:10px;" value="AddPlan" onclick="showaddPlan(\''+rowId + '\',\''+applytoall + '\');"/>';
	  }
	 function BtnFormatterwoResp(id, options, rowObject)
	  {					
	  	var rowId = options.rowId;
	 //alert('s');
	  	return '<input type="button" id="woresp" class="easyui-button" style="height:21px;width:65px\9;height:16px\9;font-size:10px;" value="WOResponsibility" onclick="showWOResp(\''+rowId + '\');"/>';
	  }
	  function checkboxsel(chkval){
		 // alert("chkval  "+chkval);
		 
		  jQuery('#pplcfunLocation').html('');
		  	if(chkval == "" || chkval == null || chkval == "undefined"){
				chkval="mchm";
				//alert("if  "+chkval);
				//alert("select Cell");
		  	}
			var eqpWise = jQuery('#chMPplcLevel').attr('checked'); 
			var assemblyWise = jQuery('#chAPplcLevel').attr('checked');
			 /* for functionalLocation*/
			var factId = jQuery("#frmPlanConfiguration input[id='factory']").val();
			var sectionId = jQuery("#frmPlanConfiguration input[id='seciton']").val();
			var cellId = jQuery("#frmPlanConfiguration input[id='line']").val();
			var machId = jQuery("#frmPlanConfiguration input[id='machine']").val();
			var flid = jQuery("#frmPlanConfiguration input[id='flid']").val();
			
			if(factId == "" || factId == null || factId == "undefined"){
				//alert("factId  "+factId);
				factId += "";
			}
			if(sectionId == undefined ||sectionId =="undefined"){
				
				sectionId += "";
				//alert("sectionId "+sectionId);
			}
			//var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&chk="+chkval;
			var dataStr = "&chk="+chkval;
			//alert("dataStr   "+dataStr);
		    loadFunctionalLocation("pplcfunLocation","functionalLoc.plnconfig?chk="+chkval,"pplcfunLocationValues","frmPlanConfiguration",dataStr);	
    }
	  function frmPlanConfiguration_FuntLocHierarchy_SuccessCallBack(keyIds)
		{
		  var machId = jQuery("#frmPlanConfiguration input[id='machine']").val();
		 // alert("mechine"+machId);
		  setFieldValue("cmbPplcMachineid",machId); 
		  setFunctionalLocWidth("frmPlanConfiguration", 1032);
		    reloadCombo("frmPlanConfiguration","cmbPplcMachineid","machineCombo.commonFilter");
			//reloadMachine("frmPlanConfiguration",'cmbPplcMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
			
			
			//sbu
			var sbuVal = jQuery("#frmPlanConfiguration input[name='hdnsbu']").val();
			  // Fallback by id, in case it's rendered as id instead of name in some cases
			  if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
			    sbuVal = jQuery("#hdnsbu").val();
			  console.log("sbuVal from hdnsbu:", sbuVal);
			  if(sbuVal != null && sbuVal != '' && sbuVal != undefined) {
			    jQuery("#frmPlanConfiguration input[id='factory']").val(sbuVal);
			  }
		} 
	 
</script>
<form id="frmPlanConfiguration" name="frmPlanConfiguration">
<div id="wrapper" style="width:1000px">
<div class="main-cntborder"  style="width:1072px">
<div style="margin-left:2%;">

<table  border="0" style="width:100%;" >
		<tr >
			<td colspan="3">
				<div  id="frmPlanConfigurationFuntKeyIds"  >
				<input type="hidden" id="factory" name="cmbPplcFactoryid" value=""  ></input>
				<input type="hidden" id="section" name="cmbPplcSectionid" value=""  ></input>
				<input type="hidden" id="cell" name="cmbPplcCellid" value=""  ></input>
				<input type="hidden" id="machine" name="hdnPplcMachineid" value=""></input>
				<input type="hidden" id="flid" name="cmbPplcFlid" value=""></input>
				<input type="hidden" id="elementId" name="hdnPplcElementid" value=""></input>
				</div>
				<div id="pplcfunLocation" style="padding-top: 15px";></div>
			</td>
		</tr>
		<tr >
<!--left  pane -->
		<td valign="top" style="width:22%;" valign="top">
			 <div  class="easyui-paddingbfpx">
                   <label> Cost Center</label>                       
                </div> 
		     <div class="easyui-paddingbfpx"> 
		         <input  id="cmbCostcentreid" name="cmbCostcentreid" class="easyui-combobox" style="width:269px;"/ >                       
		     </div>
			</td>
			<td style="width:23%" >
			<div style="float:left;padding-right:5px;">
			<div  class="easyui-paddingbfpx">
                    <label> Equipment</label>                       
              </div> 
			   <div class="easyui-paddingbfpx"> 
			          <input id="cmbPplcMachineid" name="cmbPplcMachineid" class="easyui-combobox"  style="width:269px;" value=""  >                     
			   </div>
			   
			 <!--<div  class="easyui-paddingbfpx">
             <label>Next Due</label>
              <span  style="padding-left:80px;"><label>WOResp Options</label></span>
             </div> 
			 --><!--<div class="easyui-paddingbtpx"> 
			     
			     <input id="dtenextduedate" name="dtenextduedate" class="easyui-datebox" required="true" style="width:120px;"/>
			     <span  style="padding-left:15px;"> 
			     <input  id="cmbPwrmlevel" name="cmbPwrmlevel" class="easyui-combobox" style="width:130px;"/ >
			     </span>
			 </div>
		  	
		  	--><!--<div  style="padding-top:;" class="easyui-paddingbtpx">
					<input id="chkapplytoall" name="chkapplytoall" value="Y" type="checkbox"/> <span style="padding-right:2px;"><label>Apply to all</label></span>
					<span style="padding-left:50px;"><input id="chkapplytonew" name="chkapplytonew" value="Y" type="checkbox"/> <span><label>Apply to new plan</label></span></span>
		     </div>
		     
		     
			 --></div>
			</td>
			
			 <td valign='top' style="width:33%;">
			   <div  class="easyui-paddingbfpx">
                    <label> Options</label>                       
              </div> 
			   <div class=""> 
			   <input  id="cmboptions" name="cmboptions" class="easyui-combobox" style="width:130px;"/ >
			         <!--<select id="cmboptions" name="cmboptions" class="easyui-combobox" style="width:130px;" required="true">
									<option value="E"> EQUIPMENT</option>
									<option value="A"> ASSEMBLY</option>
						</select> 
						-->
			   </div>
			    	<span  style="font-weight:bold;margin-top:-5%;float:right;margin-right:20%"> 
						<img src="images/blue-circle.png" height="18px"/>&nbsp;&nbsp;<label style="font-size: 12px;vertical-align: text-top;">Standard Not Available</label>
					</span>
		   </td>
		  
		</tr>
		<!--<tr>
		<td valign="top" colspan="3">
				 <div  style="" class="">
				 		<span  style="font-weight:bold;padding-left:5px;margin-right:15%;padding-top:8px;"> 
								<img src="images/blue-circle.png" height="18px"/>&nbsp;&nbsp;<label style="font-size: 12px;vertical-align: text-top;">Standard Not Available</label>
						</span>
 
                         <input id="chMPplcLevel" name="chMPplcLevel" value="" type="checkbox" checked=""/><label>Equipment Wise</label>
                    
						 <span  style="margin-left: 1%;">  <input id="chAPplcLevel" name="chAPplcLevel" value="" type="checkbox"/> <label>Assembly Wise</label></span>
                  		 <span  style="margin-left: 1%;">  <input id="chkApplyToall" name="chkApplyToall" value="M" type="checkbox"/> <label>Apply to All Machine</label></span>
                  		 <span  style="margin-left: 0%;">  <input id="chkApplyToallAssm" name="chkApplyToallAssm" value="A" type="checkbox"/> <label>Apply to All Assembly</label></span><br/><br/>
				</div>
				
			</td>
		</tr>
		-->
		<tr >
			<td colspan="3">
				<div class="sub-header" style="width:1000px\9;" > Enter Week No and specify due date in early column
				 <span  style="vertical-align:top;" class="">
                         <input id="chMPplcLevel" name="chMPplcLevel" value="" type="checkbox" checked=""/><label>Equipment Wise</label>
						 <span  style="margin-left: 1%;vertical-align:top;">  <input id="chAPplcLevel" name="chAPplcLevel" value="" type="checkbox"/> <label>Assembly Wise</label></span>
                  		 <span  style="margin-left: 1%;vertical-align:top;">  <input id="chkApplyToall" name="chkApplyToall" value="M" type="checkbox"/> <label>Apply to All Machine</label></span>
                  		 <span  style="margin-left: 0%;vertical-align:top;">  <input id="chkApplyToallAssm" name="chkApplyToallAssm" value="A" type="checkbox"/> <label>Apply to All Assembly</label></span>
						 <span style="margin-left: 1%;vertical-align:top;">
							 <input type="button" id="pcViewBtn"  name="pcViewBtn" style="height:21px" class="easyui-button" onclick="" value="View"/>
							 <input type="reset" id="btnClear"  name="btnClear" style="height:21px" class="easyui-button" onclick="" value="Clear"/>
		   		  		 </span>
   		  		 </span>
				</div>
			</td>
		</tr>
		</table>
		
	</div>	
			<div style="margin-left:20px\9;">
				<table id="planconfig"  ><tr><td/></tr></table>
				<div id="planconfig_pager"></div>
				<div class="clearfix"></div>
			</div>
</div>
</div>
</form>
<div id="addPlan" style="display:none">
<div style="padding:5% 1% 2% 1%;">
<div class="main-cntborder" >

</div>
</div>	
</div>
<input type ="hidden" id="hdnchk" name="hdnchk"/>
<input type ="hidden" id="hdnvalappall" name="hdnvalappall" value=""/>
