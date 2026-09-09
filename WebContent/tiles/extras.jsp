<style>
 
 .todate
 {
 margin-left:140px;
 margin-left:150px\9;
 }
 .tomonth
 {
 margin-left:135px;
 margin-left:145px\9;
 }
 .breakup
 {
 margin-right:26px;
 margin-right:40px\9;
 }
 .table
 {
 margin-left:60px;
 margin-left: 54px\9;
 }
 </style>
 
 <script type="text/javascript" >

 jQuery(document).ready(function(){
	 if(jQuery('.filter-header').html()== null)
	 	jQuery('#filterShowHide').prepend('<div class="filter-header">Filter</div>');
	 initialiseForm('frmFilter');	
	 //divcombo("frmFilter","cmbEquipmentid","divmultiCombo","onLoad","mutlicombo","hdnCompareto","CMP");
	 formatDateBox("dtefromDate","dd-MMM-yyyy");
	 formatDateBox("dtetoDate","dd-MMM-yyyy");
	 
	 formatMonthBox("dtefromMonth","MMM-yyyy");
	 formatMonthBox("dtetoMonth","MMM-yyyy");
	 
	 if(screen.width <= 1024){
		 jQuery('#btns').css('margin-top','4px');
		 jQuery('#frmFilterfunLocation').css('padding-left','24px');
		 jQuery('#advancedFilter').css('padding-left','20px');
		// jQuery('.filterTab_border').css('margin-left','60px'); 
		 jQuery('#Filter').css('width','850px');
		 jQuery('.table').css('margin-left','24px');
		 jQuery('.sub-header').css('width','91%');
		 
		}	
		else{
			
		}
	 
/* 	 jQuery('#dtefromMonth').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	 });  */ 

	 /* jQuery('#dtetoMonth').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	 });  */ 

	 jQuery('#dteyear').datebox({  
		 formatter: function(date){ return date.getFullYear(); }  
	 });  
		
		
	 		
	 
	 jQuery('#Filter').tabs({
		 	border:false,
		    onSelect: function(title){
		    	loadRelatedFilters(title);
		    }
	    
		});

	
		var compId = jQuery("#frmFilter input[id='company']").val();
		var locnId = jQuery("#frmFilter input[id='location']").val();
		var factId = jQuery("#frmFilter input[id='factory']").val();
		var sectionId = jQuery("#frmFilter input[id='seciton']").val();
		var cellId = jQuery("#frmFilter input[id='cell']").val();
		var machId = jQuery("#frmFilter input[id='machine']").val();
		var flid = jQuery("#frmFilter input[id='flid']").val();
		
		 commonFilterFunctionalLoc(false) ;	
		 
		//fillComboBox("frmFilter","cmbComp","companyCombo.commonFilter" ); 
			
		jQuery('#frmFilter .easyui-text').css('text-transform','uppercase');
		jQuery('#frmFilter textarea').css('text-transform','uppercase');

		if(  ! jQuery('#cmbEquipmentid').is(':disabled') )
			fillComboBox("frmFilter","cmbEquipmentid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid);
		
		if(  !jQuery('#cmbMachineRank').is(':disabled') )
			fillComboBox("frmFilter","cmbMachineRank","machinerank.commonFilter");	
		
	    if(  !jQuery('#cmbCostCenter').is(':disabled') )			
				fillComboBox("frmFilter","cmbCostCenter","costCenter.commonFilter");	
									
		if(  !jQuery('#cmbassembly').is(':disabled') )
			fillComboBox("frmFilter","cmbassembly","assembly.commonFilter");
		
		if(  !jQuery('#cmbEqpSubGrp').is(':disabled') )	
			fillComboBox("frmFilter","cmbEqpSubGrp","EquipmentSubGroup.commonFilter");

		if(  !jQuery('#cmbeqpGroup').is(':disabled') )	
			fillComboBox("frmFilter","cmbeqpGroup","equipmentgroup.commonFilter");//
		
		if(  !jQuery('#cmbCircle').is(':disabled') )
			fillComboBox("frmFilter","cmbCircle","circle.commonFilter" );
		
		if(  !jQuery('#cmbTrade').is(':disabled') )
			fillComboBox("frmFilter","cmbTrade","trade.commonFilter" );
		
		if( !jQuery('#cmbMould').is(':disabled') )
			fillComboBox("frmFilter","cmbMould","mould.commonFilter");
		
		var relatedto = jQuery("#cboRelatedTo").val();		
		
		if(relatedto.trim() == "" || relatedto == "MCH")
		{
			setTimeout(function() {readOnlyFields('cmbMould');},500);
			
		}
		setTimeout(function() {
			if ((jQuery('#chkMonthwise').is(':disabled'))==false)
			{
				if((jQuery('#chkMonthwise').is(':checked'))==true)
					jQuery('#btnBreakUp').attr({'disabled':false});
				else
					jQuery('#btnBreakUp').attr({'disabled':true});
			}
			else
				jQuery('#btnBreakUp').attr({'disabled':true});

			fnchkMonthwise();
			
		},300);

});

 /*jQuery("body").click(function(event){
		if(jQuery("#Multiplediv").is(":visible") ){
			jQuery("#Multiplediv").hide();
			jQuery("#hdnCompareto").val('false');
		}
		
	});*/
	

 jQuery("#btnBreakUp").click(function(){

	 	var FromDate=getFieldValue('dtefromMonth');
		var ToDate=getFieldValue('dtetoMonth');

		url="breakUp_input.commonFilter?&q=1&fromDate="+FromDate+"&toDate="+ToDate;
		LoadPopUp("divBreakUp",url,true,"50%","50%","1%","25%","","BreakUp Format","",false);

});

function commonFilterFunctionalLoc(isClear){	/* for functionalLocation*/
	var compId = jQuery("#frmFilter input[id='company']").val();
	var locnId = jQuery("#frmFilter input[id='location']").val();
	var factId = jQuery("#frmFilter input[id='factory']").val();
	var sectionId = jQuery("#frmFilter input[id='seciton']").val();
	var cellId = jQuery("#frmFilter input[id='cell']").val();
	var machId = jQuery("#frmFilter input[id='machine']").val();
	var flid = jQuery("#frmFilter input[id='flid']").val();
/*	jQuery('#hdnFactid').val(factId);
	jQuery('#hdnSectid').val(sectionId);
	jQuery('#hdnCellid').val(cellId);
	jQuery('#hdnMchid').val(machId);
	jQuery('#hdnCompid').val(compId);
*/
//	var subgrp = jQuery("#frmPcsRelated input[id='cmbpcssubgrp']");

	if(sectionId==null){
		
		jQuery("#cmbpcssubgrp").combobox("disable");
	}
	else{
		
	}
	if( isClear == true)
	{
		flid ="";
		machId ="";	
		cellId ="";
		sectionId ="";
		factId ="";
		locnId="";
		compId ="";
	}	
	var url = jQuery('#hiddenUrl').val();

	var disableFuncLoc = null;
	if( jQuery('#disableFuncLoc').length > 0 ) 
		disableFuncLoc = jQuery('#disableFuncLoc').val();
	
	var disable='Y';
	if( disableFuncLoc == "Y" || disableFuncLoc == "N" )
		disable = disableFuncLoc;
		
	var enable = "";
	if( jQuery('#enableFunctionalLocElement').length > 0 )
		enable = jQuery('#enableFunctionalLocElement').val();

	
	if (url=='MeetingMin_input.mom?type=Pillar'||url=='MeetingMin_input.mom?type=Pillar&mode=view'||url=='momattendancereport_input.mom?type=OTHERS'){
		disable='N';
		enable='SBU';
	}
	
	var dataStr = "&compId="+compId +"&locnId="+locnId +"&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid+"&disable="+disable+"&enable="+enable;
	
	loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter",dataStr);
/*---------*/
}
 
  function frmFiltercmbassembly_onSelect(record){
	var machId = jQuery("#frmFilter input[id='machine']").val();
	if(machId != " " && machId !="" && machId != null)
		{}
	else{
		clearField('cmbassembly');
		alert('Select Machine');
	 }	
	}
	jQuery('#cboRelatedTo').change(function() {
		 var val = jQuery("#cboRelatedTo").val();
		 var chkdisable = jQuery('#cmbassembly').is(':disabled');
		 if(val == "MLD")
		 { 
			 enableFields('cmbMould');
		if(chkdisable == true)
			 reloadCombo("frmFilter","cmbassembly","assembly.commonFilter?q=2&relatedto=MLD");
			
		 }
		 else{
			 readOnlyFields('cmbMould');
			 clearField('cmbMould');
			if(chkdisable == true)
			 	reloadCombo("frmFilter","cmbassembly","assembly.commonFilter?q=2&relatedto=MCH" );
		 }
		});
	
 	/*jQuery('#chkDatewise').click(function(){
 		if(jQuery('#chkDatewise').is(':checked') == true)
		{	
			jQuery("#dtefromMonth").datebox('disable');
			jQuery("#dtetoMonth").datebox('disable');
			jQuery("#dtefromDate").datebox('enable');
			jQuery("#dtetoDate").datebox('enable');		  
			//jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
			jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
			jQuery('#chkDatewise').val("Y");
			jQuery('#chkMonthwise').val("");
	
		}
 		
 	}); 		

	jQuery('#chkMonthwise').click(function(){
		if(jQuery('#chkMonthwise').is(':checked') == true)
			{	
				jQuery("#dtefromDate").datebox('disable');
				jQuery("#dtetoDate").datebox('disable');	
				jQuery("#dtefromMonth").datebox('enable');
				jQuery("#dtetoMonth").datebox('enable');  	  			
				//jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
				jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
				jQuery('#chkDatewise').val("");
				jQuery('#chkMonthwise').val("Y");
			
			}
	
			
	});*/
	


	
/*	jQuery("#btnClear").click(function (){	 
		
		
		reloadCombo("frmFilter","cmbComp","companyCombo.commonFilter" );
		 
		 jQuery('#extras') .find('input[type=hidden]').val(''); 
		 jQuery('input:checkbox').each( function() {
			 if( this.checked)
		     	this.checked = !this.checked;
		});

		 
	});
*/
	
	jQuery("#btnClear").click(function (){	 
		//reloadCombo("frmFilter","cmbComp","companyCombo.commonFilter" );
		if(jQuery('#hdnfilterTitle').val()=="Common")	
		{	
		
			clearForm('commonFilter');
			commonFilterFunctionalLoc(true) ;
			//loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter","&disable=N&enable=LCN");
			
			var checkMonth = jQuery('#chkMonthwise').is(':disabled');
			var checkDate = jQuery('#chkDatewise').is(':disabled');
			
			var checkFromMonth = jQuery('#dtefromMonth').is(':disabled');
			var checkToMonth = jQuery('#dtetoMonth').is(':disabled');
		
			
			if(checkDate == true && checkMonth == false)
			{
				var fromMonth = jQuery("#fromMonVal").val();
				var toMonth = jQuery("#toMonVal").val();
				jQuery("#frmFilter input[id=dtefromMonth]").datebox("setValue",fromMonth);
				jQuery("#frmFilter input[id=dtetoMonth]").datebox("setValue",toMonth);
				//jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
				jQuery('input:checkbox[name="chkMonthwise"]').prop('checked', true);
				enableFields('dtefromMonth');
				enableFields('dtetoMonth');
				jQuery('#btnBreakUp').attr({'disabled':false});
			}
			else if(checkMonth == true && checkDate == false)
			{
				var fromDate = jQuery("#fromdtVal").val();
				var toDate = jQuery("#todtVal").val();
				jQuery("#frmFilter input[id=dtefromDate]").datebox("setValue",fromDate);
				jQuery("#frmFilter input[id=dtetoDate]").datebox("setValue",toDate);
				//jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
				jQuery('input:checkbox[name="chkDatewise"]').prop('checked', true);
				enableFields('dtefromDate');
				enableFields('dtetoDate');
			}
			
			else if(checkMonth == false && checkDate == false)
			{
				if(checkFromMonth = false && checkToMonth == false)
					{
						var fromDate = jQuery("#fromdtVal").val();
						var toDate = jQuery("#todtVal").val();
						jQuery("#frmFilter input[id=dtefromDate]").datebox("setValue",fromDate);
						jQuery("#frmFilter input[id=dtetoDate]").datebox("setValue",toDate);
						jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
						enableFields('dtefromDate');
						enableFields('dtetoDate');
					}
				else
					{
						var fromMonth = jQuery("#fromMonVal").val();
						var toMonth = jQuery("#toMonVal").val();
						jQuery("#frmFilter input[id=dtefromMonth]").datebox("setValue",fromMonth);
						jQuery("#frmFilter input[id=dtetoMonth]").datebox("setValue",toMonth);
						jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
						enableFields('dtefromMonth');
						enableFields('dtetoMonth');
						jQuery('#btnBreakUp').attr({'disabled':false});
					}
			}
			jQuery("#cmbCircle").combobox("enable");
			jQuery("#hdnclearbtnval").val('true');
			jQuery("#hdnCompareto").val('true');
		}	
	    else if(jQuery('#hdnfilterTitle').val()=="Related")
	    {	 
		   // clearForm('relatedFilterTab');
		    var curUrl = jQuery("#curUrl").val();
	    	loadRelatedFilterPage(curUrl);  
	    }
		enableDisableFilterWraper();
	});
	
	jQuery("#btnFilterClose").click(function (){	
		toggleCommonFilter(true);
	});
	jQuery("#btnFilterCloseicn").click(function (){	
		toggleCommonFilter(true);
	});
	
	/*function frmFiltercmbComp_onLoadSuccess()
	{
		// fillComboBox("frmFilter","cmbFact","factroyCombo.commonFilter" );
	}
	function frmFiltercmbFact_onLoadSuccess()
	{
		//fillComboBox("frmFilter","cmbSect","sectionCombo.commonFilter" );
	}
	function frmFiltercmbSect_onLoadSuccess()
	{
		//fillComboBox("frmFilter","cmbCell","cellCombo.commonFilter" );
	}
	function frmFiltercmbCell_onLoadSuccess()
	{
		//fillComboBox("frmFilter","cmbEquipmentid","machineCombo.commonFilter" );*
	}*/
	function frmFiltercmbEquipmentid_onLoadSuccess()
	{
		jQuery('#modal_div1').removeClass('window-maskgrid');
		//alert(1);
		//if(!jQuery('#cmbeqpGroup').is(':disabled'))
		//	fillComboBox("frmFilter","cmbeqpGroup","equipmentgroup.commonFilter");
		//fillComboBox("frmFilter","cmbEqpSubGrp","EquipmentSubGroup.commonFilter");
		//fillComboBox("frmFilter","cmbCostCenter","costCenter.commonFilter");
	}
	/*function frmFiltercmbCostCenter_onLoadSuccess()
	{
		if(  jQuery('#cmbassembly').is(':disabled') ){
			fillComboBox("frmFilter","cmbMachineRank","machinerank.commonFilter");		
		}	
		else
			fillComboBox("frmFilter","cmbassembly","assembly.commonFilter");
	}
	function frmFiltercmbassembly_onLoadSuccess()
	{
		fillComboBox("frmFilter","cmbMachineRank","machinerank.commonFilter");
	}
	function frmFiltercmbMachineRank_onLoadSuccess()
	{
		fillComboBox("frmFilter","cmbeqpGroup","equipmentgroup.commonFilter");
	}
	function frmFiltercmbeqpGroup_onLoadSuccess()
	{
		if(  jQuery('#cmbassembly').is(':disabled') ){
			fillComboBox("frmFilter","cmbCircle","circle.commonFilter" );
		}	
		else{
			fillComboBox("frmFilter","cmbTrade","trade.commonFilter" );
		}	
	}

	function frmFiltercmbTrade_onLoadSuccess()
	{
		fillComboBox("frmFilter","cmbCircle","circle.commonFilter" );
	}*/
	function frmFiltercmbCircle_onLoadSuccess()	
	{
		//setComboDefaultValue("frmFilter", "cmbCircle");
		
    }
	function loadFunctionalLocationHierarchy_successCallback(){
		
		if(jQuery('#disableFuncLoc').val() == 'disable'){
			disableFunctionalLocation('true','frmFilterfunLocation');//for Disabling FunctionalLocation
		}
		else{
			jQuery('#disFuncLocDiv').remove();
		}
	}
    function aftercircleLoad(){
			/*****for disableing funcitonal location*****/
					
					jQuery(".disableFuncButton").click(function() {
						var disableFunctionalColumn = jQuery('#disableFuncLoc').val();
						
						if(disableFunctionalColumn == "true")
						{
							return false;
							jQuery("#functLocHierarPopupId").dialog('close');
							
						}		
						else
							return true;
				});
				jQuery('.disableFuncButton a').click(function() {
					
					 jQuery("#functLocHierarPopupId").dialog('close');
					 
				 });
    }
	function frmFiltercmbCircle_onSelect(record)	
	{
		reloadCombo("frmFilter","cmbEquipmentid","machineCombo.commonFilter?circleId="+record.id);
		jQuery('#btnfrmFiltermainFunLoc').addClass('disableFuncButton');
		jQuery('#dispFunctionalLoc').addClass('disableFuncButton');
		jQuery('#disableFuncLoc').val('true');
		 aftercircleLoad();
		//loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter","&disableFuncLoc=true");
	}
	/*Edited By Dhanalakshmi*/
	function frmFiltercmbeqpGroup_onSelect(record)
	{
		reloadCombo("frmFilter","cmbEqpSubGrp","EquipmentSubGroup.commonFilter?eqpGrpId="+record.id);
		reloadCombo("frmFilter","cmbEquipmentid","machineCombo.commonFilter?eqpGrpId="+record.id);
		jQuery("#cmbEqpSubGrp").combobox('clear'); 
	}
	function frmFiltercmbEqpSubGrp_onSelect(record)
	{
		reloadCombo("frmFilter","cmbEquipmentid","machineCombo.commonFilter?eqpSubGrpId="+record.id);
		reloadCombo("frmFilter","cmbeqpGroup","equipmentgroup.commonFilter?eqpSubGrpId="+record.id );
	}
 /*   	function  frmFiltercmbComp_onSelect(record)
	{
		//jQuery("#cmbFact").combobox('clear');
		reloadCombo("frmFilter","cmbFact","factroyCombo.commonFilter?compId="+record.id);
	}
	function  frmFiltercmbFact_onSelect(record)
	{
		jQuery("#cmbSect").combobox('clear');
		jQuery("#cmbCell").combobox('clear');
		jQuery("#cmbEquipmentid").combobox('clear');
		jQuery("#cmbCircle").combobox('clear');
		jQuery("#cmbeqpGroup").combobox('clear');
		jQuery("#cmbCostCenter").combobox('clear');
		
		reloadCombo("frmFilter","cmbSect","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmFilter","cmbCell","cellCombo.commonFilter?factId="+record.id  );
		reloadCombo("frmFilter","cmbEquipmentid","machineCombo.commonFilter?factId="+ record.id );
		reloadCombo("frmFilter","cmbCircle","circle.commonFilter?factId="+ record.id );
		reloadCombo("frmFilter","cmbeqpGroup","equipmentgroup.commonFilter?factId="+ record.id );
		reloadCombo("frmFilter","cmbCostCenter","costCenter.commonFilter?factId="+record.id+"&cellId="+getFieldValue('cmbCell'));
	
	}
	function  frmFiltercmbSect_onSelect(record)
	{
		jQuery("#cmbCell").combobox('clear');
		jQuery("#cmbEquipmentid").combobox('clear');
		reloadCombo("frmFilter","cmbCell","cellCombo.commonFilter?sectId="+record.id  );
		reloadCombo("frmFilter","cmbEquipmentid","machineCombo.commonFilter?sectId="+ record.id );
		fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbFact","cmbComp");
	}

	function  frmFiltercmbCell_onSelect(record)
	{		
				jQuery("#cmbMachine").combobox('clear');
		reloadCombo("frmFilter","cmbMachine","machineCombo.commonFilter?cellId="+ record.id );
		fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbSect","cmbFact","cmbComp");		
	}
*/
	
	
	function  frmFiltercmbEqpmntImpr_selected(event,ui)
	{
		//if( == null )
		
		//fillComboBox("frmFilter","cmbEqpmntImpr","machineCombo.commonFilter" );
	}
	function dtetoDate_onSelect(date)
  	{
	  		
	  	var fromdate = jQuery('#dtefromDate').datebox('getValue');
	 	var fromDate = convertStringToDate(fromdate);
		var currentDate = getServerDateTime();	
		var todateValidation=jQuery("#hdnEscFutureDateVal").val();
		if(  todateValidation != "true"  &&   date > currentDate)
		{					
			jQuery('#dtetoDate').datebox('clear');
			showValidationErrorMsg('dtetoDate','Should Not Exceed Current Date');	
		}	
		else if(fromDate > date)
		{
			jQuery('#dtetoDate').datebox('clear');
			showValidationErrorMsg('dtetoDate','To Date Must be Greater then FromDate');	
		}
		else
			clearValidationErrorMsg('dtetoDate');		
  	}
  	
	function dtefromDate_onSelect(date)
  	{			
		var currentDate = getServerDateTime();
		var fromDateValidation=jQuery("#hdnEscFutureDateVal").val();
		displayDate(date);
		if(fromDateValidation !="true" && date > currentDate)	
		{					
			jQuery('#dtefromDate').datebox('clear');
			showValidationErrorMsg('dtetoDate','Should Not Exceed Current Date');	
		}	
		
		else
			clearValidationErrorMsg('dtetoDate');		
  	}
	function dtefromMonth_onSelect(date)
  	{		
	  
		var currentDate = getServerDateTime();
		//alert("date:"+date);
		//alert("currentDate:"+currentDate);
		//displayDate(date);	
		if('01-'+date > currentDate)
		{					
			jQuery('#dtefromMonth').datebox('clear');
			showValidationErrorMsg('dtetoMonth','Should Not Exceed Current Date');	
		}	
		
		else
			clearValidationErrorMsg('dtetoMonth');		
  	}
	function displayDate(today)
	{
	
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-3)<=0)
	year=today.getFullYear();
	var backdate = new Date(year,month-3,date);
	
	}
	function frmFilter_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		
		if( keyIds.machId != null && keyIds.machId !="" && keyIds.machId !=" " && keyIds.machId != undefined && keyIds.machId != 'null')
		{
			setFieldValue('cmbEquipmentid',keyIds.machId);
			//reloadMachine("frmFilter",'cmbEquipmentid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
			reloadCombo("frmFilter","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  );
			reloadCombo("frmFilter","cmbCircle","circle.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  +"&sectId="+keyIds.sectId  );
			reloadCombo("frmFilter","cmbeqpGroup","equipmentgroup.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  +"&sectId="+keyIds.sectId  );
		}
		else
			jQuery("#cmbEquipmentid").combobox('clear');
		
		//reloadCombo("frmFilter","cmbEquipmentid","machineCombo.commonFilter?compId="+keyIds.compId +'&factId='+keyIds.factId+'&sectId='+keyIds.sectId+"&cellId="+keyIds.cellId);
		reloadMachine("frmFilter",'cmbEquipmentid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		
		 if(keyIds.sectId.length>0){

				jQuery("#cmbCircle").combobox("clear");
				jQuery("#cmbCircle").combobox("disable");
				
			}
		else if(keyIds.cellId.length>0){

			jQuery("#cmbCircle").combobox("clear");
			jQuery("#cmbCircle").combobox("disable");
		}
		
		
		if(jQuery("#frmPcsRelated input[id='cmbpcsprrod']").length>0){
			
			reloadCombo("frmPcsRelated","cmbpcsprrod","product.commonFilter?compId="+keyIds.compId + "&locnId="+keyIds.locnId +"&factId="+keyIds.factId+"&sectId="+keyIds.sectId+"&cellId="+keyIds.cellId +"&machId="+keyIds.machId);
		}
		
		if(jQuery("#frmBD input[id='cmbdefectpheno']").length>0){
			reloadCombo("frmBD","cmbdefectpheno","phenomena.commonFilter? &machId="+keyIds.machId);
		}
		var defect = jQuery("#frmBD input[id='cmbdefectpheno']");
		
		
	}	
		
/*	jQuery( "#view" ).click(function(response) {
			 var url = jQuery('#hiddenUrl').val();
			  var dataString = getCommonFilterValues();
		//	url = url.replace("input","view");
			if( typeof getRelatedFilterValues == 'function' )
				dataString += getRelatedFilterValues();

			viewGrid(url,dataString);
			//jQuery("#filterPanel").hide();
			
		});
*/	

	jQuery( "#view" ).click(function(response) {
		var dtFromDate = jQuery('#dtefromDate').datebox('getValue');		
		var dtToDate = jQuery('#dtetoDate').datebox('getValue');
		var dtFromMonth = jQuery('#dtefromMonth').datebox('getValue');
		var disFromDate = jQuery('#chkMonthwise').is(':disabled');
		var dtToMonth = jQuery('#dtetoMonth').datebox('getValue');
		
			if(jQuery('#chkDatewise').is(':checked') == true)
			{
				var fromDate = dtFromDate;
				var	ToDate = dtToDate;		
				if(fromDate == '' && !jQuery('#dtefromDate').is(':disabled'))
				{
					alert("Select From Date");
					return;
				}
				else if(ToDate == '' && !jQuery('#dtetoDate').is(':disabled'))
				{
					alert("Select To Date");
					return;
				}
				else if( compareDate( fromDate,ToDate) == -1 && !jQuery('#dtetoDate').is(':disabled'))
				{	
					alert('To Date can not be less than From Date');
					return ;
				}
				//if (compareFromToDate(dtFromDate,dtToDate)==false)
					//return;
			}
		
			
				if(jQuery('#chkMonthwise').is(':checked') == true){
					
					var fromMonth = "01-"+dtFromMonth;
					var	ToMonth = "01-"+dtToMonth;		
					if(dtFromMonth == '' && !jQuery('#dtefromMonth').is(':disabled'))
					{
						alert("Select From Month");
						return;
					}
					else if(dtToMonth == '' && !jQuery('#dtetoMonth').is(':disabled'))
					{
						alert("Select To Month");
						return;
					}
					else if( compareDate( fromMonth,ToMonth) == -1)
					{	
						 if( !jQuery('#dtefromMonth').is(':disabled') && !jQuery('#dtetoMonth').is(':disabled')  ){
							alert('To Month can not be less than From Month');
							return ;
						 }
						
					}
					//else if (compareFromToMonth(dtFromMonth,dtToMonth)==false)
					//	return;
		 			
				}
				
		var url = jQuery('#hiddenUrl').val();		
		var breakup = jQuery('#hdnbreakup').val();
		var datastring = getAllFilterValues();
		var filterStr = datastring+"&BREAKUP="+breakup;
		
		if( viewGrid(url,filterStr) == true )
		{
			toggleCommonFilter(true);
		}	
		
	});
function fillDrilFunctlocCombo(keyid)
{
	if( keyid != null && keyid.length > 3){
		
		if( keyid.substr(0,3)=='CMP' )
		{
			jQuery("#cmbComp").combobox("setValue");
		}
		else if( keyid.substr(0,3)=='LCN' )
		{
			jQuery("#cmbLocn").combobox('setValue');
		}
		else if( keyid.substr(0,3)=='FCT' )
		{
			jQuery("#cmbFact").combobox('setValue');
		}
		else if( keyid.substr(0,3)=='LIN' )
		{
			jQuery("#cmbSect").combobox('setValue');
		}
		else if( keyid.substr(0,3)=='CEL' )
		{
			jQuery("#cmbCell").combobox('setValue');
		}	
		else if( keyid.substr(0,3)=='MCH' )
		{
			jQuery("#cmbEquipmentid").combobox("setValue");
		}
		else if( keyid.substr(0,3)=='ASM' )
		{
			jQuery("#cmbAssembly").combobox("setValue");
		}	
				
	}		
}
function getAllFilterValues()
{

	var dataString = getCommonFilterValues();
	try{
	if( typeof getRelatedFilterValues == 'function' )
		dataString += getRelatedFilterValues();
	}catch(Exception ){}

	return dataString;
}			
/*function compareFromToDate(dtFromDate,dtToDate)
{
	
	var today = convertStringToDate(dtToDate);
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-1)<=0)
	year=today.getFullYear();
	  var backdate= new Date(year, month, date-45);
	  
	//var backdate = new Date(year,month-1,date);
	
	if(convertStringToDate(dtFromDate)  < backdate){
		alert("From Date Should be within 45 Days");
		//jQuery('#dtefromDate').datebox('clear');		
		return false;   
	}
	return ;
}
function compareFromToMonth(dtFromMonth,dtToMonth)
{
	var today = convertStringToDate('01-'+dtToMonth);
	var month,day,year
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-48)<=0)
	year=today.getFullYear();
	var backdate = new Date(year,month-48,date);
	
	if(convertStringToDate('01-'+dtFromMonth)  < backdate){
		alert("From Month Should be within 4 Years");
		//jQuery('#dtefromDate').datebox('clear');		
		return false;

	}
	return ;
}
*/
function getCommonFilterValues()
{
	
	var dataString="?q=2";

	if(jQuery("#company").length > 0){
	var cmbCompid = jQuery("#company").val();
	dataString = "&cmbCompid="+cmbCompid;
	}
	var cmbLocnid = jQuery("#location").val();
	dataString += "&cmbLocnid="+cmbLocnid;
	
	var cmbFactid = jQuery("#factory").val();
	dataString += "&cmbFactid="+cmbFactid;
	
	var cmbSbuid = jQuery("#sbu").val();
	dataString += "&cmbSbuid="+cmbSbuid;
	
	var cmbPbuid = jQuery("#pbu").val();
	dataString += "&cmbPbuid="+cmbPbuid;
	
	var cmbSectid = jQuery("#section").val();
	dataString += "&cmbSectid="+cmbSectid;

	var cmbCostCenter = jQuery("#cmbCostCenter").combobox('getValue');
	dataString += "&cmbCostCenter="+cmbCostCenter;
	
	var cmbCircle = jQuery("#cmbCircle").combobox('getValue');
	dataString += "&cmbCircle="+cmbCircle;
	
	var cmbCellid = jQuery("#cell").val();;
	dataString += "&cmbCellid="+cmbCellid;
	
	var cmbMchid = jQuery("#cmbEquipmentid").combobox("getValue");	
	dataString += "&cmbMchid="+cmbMchid;
	
	var flid = jQuery("#flid").val();;
	dataString += "&flid="+flid;
	
	
	var dtFromDate = jQuery('#dtefromDate').datebox('getValue');		
	dataString += "&dtFromDate="+dtFromDate;
	
	var dtToDate = jQuery('#dtetoDate').datebox('getValue');
	dataString += "&dtToDate="+dtToDate;
	
	var dtFromMonth = jQuery('#dtefromMonth').datebox('getValue');
	dataString += "&dtFromMonth="+dtFromMonth;
	
	var dtToMonth = jQuery('#dtetoMonth').datebox('getValue');
	dataString += "&dtToMonth="+dtToMonth;
	
	var year = jQuery('#dteyear').datebox('getValue');
	dataString += "&year="+year;

	var cmbAssmbid = jQuery("#cmbassembly").combobox("getValue");	
	dataString += "&cmbAssmbid="+cmbAssmbid;
	
	var cmbEqpGrpid = jQuery("#cmbeqpGroup").combobox("getValue");	
	dataString += "&cmbEqpGrpid="+cmbEqpGrpid;
	

	var cmbEqpSubGrp=jQuery("#cmbEqpSubGrp").combobox("getValue");
	dataString += "&cmbEqpSubGrp="+cmbEqpSubGrp;
	

	var cmbMchRnkid = jQuery("#cmbMachineRank").combobox("getValue");	
	dataString += "&cmbMchRnkid="+cmbMchRnkid;
	
	var cmbTradeid = jQuery("#cmbTrade").combobox("getValue");	
	dataString += "&cmbTradeid="+cmbTradeid;

	var chkSkipLine = getChkBoxVal("chkSkipLine");
	
	/*var cboRelatedTo = jQuery("#cboRelatedTo").val();
	dataString += "&cboRelatedTo="+cboRelatedTo;
	
	var cmbMould = jQuery("#cmbMould").combobox("getValue");	
	dataString += "&cmbMould="+cmbMould;*/
	
		
	dataString += "&chkMonthwise="+getChkBoxVal('chkMonthwise');
	dataString += "&chkDatewise="+getChkBoxVal('chkDatewise');
	dataString += "&skipLine="+ (chkSkipLine == "1" || chkSkipLine == 1 ? 'Y':'N') ;
	dataString += "&firstClick=Y";
	
	var parentId = getParentId();
	parentId = parentId.trim();


	var multipleval=jQuery("#checkedvalues").val();
	dataString += "&multipleval="+multipleval;

	var checkedtype=jQuery("#checkedtype").val();
	dataString += "&checkedtype="+checkedtype;
	
	if( parentId != null && parentId!= undefined && parentId != 'undefined' && parentId.length > 0 && parentId != "" )		
		dataString += "&parentId="+parentId;

	//alert(dataString);
	return dataString;
}
function getParentId()
{
	var compId = getFieldValue('company','frmFilter');
	var locnId = getFieldValue('location','frmFilter');
	var factId = getFieldValue('factory','frmFilter');
	var sectId = getFieldValue('section','frmFilter');
	var cellId = getFieldValue('cell','frmFilter');
	var machId = getFieldValue('cmbEquipmentid','frmFilter');

	//var eqpRank = getFieldValue('cmbMachineRank','frmFilter');
	
	var parentId ="";
	 
	if(machId!=null && machId!= undefined && machId.length > 0)
		parentId=cellId;
	else if(cellId!=null && cellId!= undefined && cellId.length > 0)
		parentId=sectId;
	else if(sectId!=null && sectId!= undefined && sectId.length > 0)
		parentId=factId;

	else if(factId!=null && factId!= undefined && factId.length > 0)
		parentId=locnId;
	
	else if(locnId!=null && locnId!= undefined && locnId.length > 0)
		parentId=compId;
	
	else if(compId!=null && compId!= undefined && compId.length > 0)
		parentId="";

	return 	parentId != undefined ? parentId:'';
}

function getChkBoxVal(Id) {		
	if(jQuery('#'+Id).is(':checked') == true)  		
		return 1;
	else
		return 0;
}
 function chekActi_deselect(chk_Act)
 {

 divopt_uncheckall();
 jQuery("#"+chk_Act).attr('checked', true);

 }
 function divopt_uncheckall()
 {
 	jQuery("#awisechkbox").attr('checked', false);
 	jQuery("#atypechkbox").attr('checked', false);
 	jQuery("#mwisechkbox").attr('checked', false);
 	jQuery("#summarychkbox").attr('checked', false);
 }

 function setComboxes(combId,url)
 {
	 jQuery('#'+combId).combobox({
			mode:'remote',
			url:url,
			valueField:'id',
			textField:'text',
			onSelect:function(record)
			{
				
				
			},
			onUnselect:function()
			{
				
			},
			
			selected: function(event, ui) {
				//var compId = $("#cmbCompId").val();
			 }
	 });	
 }


 function onlyNumbers(evt)
 {
 	   var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57))
           return false;

        return true;
 }
 
 
 jQuery("#chkDatewise").click(function(){
		
		if(jQuery("#chkDatewise").is(':checked') == false)
		{	
			readOnlyFields('dtefromDate');	
			readOnlyFields('dtetoDate');
			jQuery("#frmFilter input[id=dtefromDate]").datebox("setValue",'');
			jQuery("#frmFilter input[id=dtetoDate]").datebox("setValue",'');
			//jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
			
		}
		else if(jQuery("#chkDatewise").is(':checked') == true){		
			enableFields('dtefromDate');
			enableFields('dtetoDate');
			readOnlyFields('dtefromMonth');	
			readOnlyFields('dtetoMonth');
			jQuery('#btnBreakUp').attr({'disabled':true});
			//jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
			jQuery('input:checkbox[name="chkMonthwise"]').prop('checked', false);
			var fromDate = jQuery("#fromdtVal").val();
			var toDate = jQuery("#todtVal").val();
			jQuery("#frmFilter input[id=dtefromDate]").datebox("setValue",fromDate);
			jQuery("#frmFilter input[id=dtetoDate]").datebox("setValue",toDate);
			jQuery("#frmFilter input[id=dtefromMonth]").datebox("setValue",'');
			jQuery("#frmFilter input[id=dtetoMonth]").datebox("setValue",'');
		
		}
	});


 function  frmFiltercmbEquipmentid_onSelect(record)
 {

 		jQuery("#frmFilter input[id='machine']").val(record.id);
 		jQuery("#frmFilter input[id='flid']").val("");
 		commonFilterFunctionalLoc(false);
 		fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbCell","cmbSection","cmbFactory","", "","cmbCostCenter");
 		reloadCombo("frmFilter","cmbassembly","assembly.commonFilter?q=2&machineId="+ record.id);
 		
 }
 function fnchkMonthwise(){
	    enableFields('dtefromMonth');
		enableFields('dtetoMonth');
		readOnlyFields('dtefromDate');	
		readOnlyFields('dtetoDate');
		jQuery('#btnBreakUp').attr({'disabled':false});
		//jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
		jQuery('input:checkbox[name="chkDatewise"]').prop('checked', false);
		var fromMonth = jQuery("#fromMonVal").val();
		var toMonth = jQuery("#toMonVal").val();
		jQuery("#frmFilter input[id=dtefromMonth]").datebox("setValue",fromMonth);
		jQuery("#frmFilter input[id=dtetoMonth]").datebox("setValue",toMonth);
		jQuery("#frmFilter input[id=dtefromDate]").datebox("setValue",'');
		jQuery("#frmFilter input[id=dtetoDate]").datebox("setValue",'');
	 }
 jQuery("#chkMonthwise").click(function(){
		
		if(jQuery("#chkMonthwise").is(':checked') == false)
		{	
			readOnlyFields('dtefromMonth');	
			readOnlyFields('dtetoMonth');
			jQuery('#btnBreakUp').attr({'disabled':true});
			jQuery("#frmFilter input[id=dtefromMonth]").datebox("setValue",'');
			jQuery("#frmFilter input[id=dtetoMonth]").datebox("setValue",'');
			//jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
			
		}
		else if(jQuery("#chkMonthwise").is(':checked') == true){	
		   fnchkMonthwise();
		}
 });



</script>
<form id="frmFilter" name="frmFilter">
<span id="btnFilterCloseicn" style="float:right;margin: -27px 2px;"><img alt="close" src="images/close-butt1.png" style="cursor:pointer;vertical-align: top;height:18px;"></span>
<div class="filterTab_border" style="width:1000px;width:700px\9;height:380px\9;margin-left:14%;">

<!--   Filter Tab	-->
	<div  title="Filter" style="padding:0px;">
		<div  class="inner_div" style="width:900px;" >
			<div id="Filter"  class="easyui-tabs ftab_size" fit="true" plain="true" style="width:1000px;height:900px\9;">
						<!--   Common Tab	-->
				<div id="commonFilter" title="Common" style="width:960px;width:1000px\9;">
							<div class="sub-header" style="width:99%;width:100%\9;">Regular Filter</div>
							<div>
								
								<div  id="frmFilterFuntKeyIds"  >
									<input type="hidden" id="factory" name="cmbFact" value=""  ></input>
									<input type="hidden" id="sbu" name="cmbSbu" value=""  ></input>
									<input type="hidden" id="pbu" name="cmbPbu" value=""  ></input>
									<input type="hidden" id="section" name="cmbSect" value=""  ></input>
									<input type="hidden" id="cell" name="cmbCell" value=""  ></input>
									<input type="hidden" id="machine" name="cmbMachine" value=""  ></input>
									<input type="hidden" id="flid" name="hdnflid" value=""  ></input>
								</div>
									
								<div id="frmFilterfunLocation" style="padding-left: 60px;padding-left: 54px\9;"></div>
						
								<div >
									<table class="table" >
									<tr>										
										<td valign="top">
										   <!--  <div  class="easyui-paddingbfpx">
			                        			<label>Company</label>                       
			                    		   </div>
			                    		   
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbComp" name="cmbComp" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div  class="easyui-paddingbfpx">
			                        			<label>Factory</label>                       
			                    		   </div> 
						                   <div class="easyui-paddingbfpx"> 
						                        <input id="cmbFact" name="cmbFact" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div  class="easyui-paddingbfpx">
			                        			<label>Section</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbSect" name="cmbSect" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						               -->    
										   <div>
			                        			<label>Equipment</label>                       
			                    		   </div> 

						                    <div  class="easyui-paddingbfpx" style="width:101.1%;width:280px\9;"> 
						                       <span id="mutlicombo"><input id="cmbEquipmentid" name="cmbEquipmentid" class="easyui-combobox"  style="width:250px" value=""  ></span>                        

						                    <!--<div class="easyui-paddingbfpx"> 
						                        <input id="cmbEquipmentid" name="cmbEquipmentid" class="easyui-combobox"  style=" width : 249px;" value=""  >                       
															-->
						                   </div>
						                      <!--  <div id="divmultiCombo" class="combobox-multiselect" >
			           						 </div> -->
						                   <div>
			                        			<label>Trade</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbTrade" name="cmbTrade" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div>
			                        			<label>Assembly</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbassembly" name="cmbassembly" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div style="padding-top: 2px;">
						                   	<span><label> Skip Line</label>	<input type="checkbox" id="chkSkipLine" name="chkSkipLine"  value="" /></span>
						                   		
						                   		<span  style="padding-left:5px;">
			                        			<label>Related To</label>                       
			                    		   </span> 
			                    		   <span> 
						                   		<select id="cboRelatedTo" name="cboRelatedTo"  style=" width :110px;"   > 
						                        	<option value="">All</option>
						                        	<option value="MCH">Machine</option>
						                        	<option value="MLD">Mould</option>
						                        </select>
			                    		   </span>
						                   </div>
						                   
										</td>
										<td valign="top" style=" padding-left:20px;">
					<!-- 	                   <div  class="easyui-paddingbfpx">
			                        			<label>Line</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
					 -->                   
										   <div>
			                        			<label>Machine Group</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx">
						                        <input id="cmbeqpGroup" name="cmbeqpGroup" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div>
			                        			<label>Circle</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbCircle" name="cmbCircle" class="easyui-combobox"  style="width:250px" value=""  >                       
						                   </div>
						                   
						                   <div>
			                        			<label>Cost Center</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbCostCenter" name="cmbCostCenter" class="easyui-combobox"  style="width:250px" value=""  >                  
						                   </div>
						                 
						                </td>
										<td valign="top" style=" padding-left:20px;">
										   <div>
			                        			<label>Equipment Sub Group</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbEqpSubGrp" name="cmbEqpSubGrp" class="easyui-combobox"  style="width:225px" value=""  >               
						                   </div>
										   <div>
			                        			<label>Machine Rank</label>                       
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
						                        <input id="cmbMachineRank" name="cmbMachineRank" class="easyui-combobox"  style="width:225px" value=""  >                       
						                   </div>
						                   
						                   
						                    <div>
<!-- 			                        			<label>Mould</label>                        -->
			                    		   </div> 
						                    <div class="easyui-paddingbfpx"> 
<!-- 						                         <input id="cmbMould" name="cmbMould" class="easyui-combobox"  style="width:225px" value=""  >      -->
						                   </div>
						                   
						                   
						                    
						                   
										</td>
										</tr>
										</table></div>
			                   </div>
			                   <div class="sub-header" style="width:99%;width:100%\9;">Advanced Filter</div>
			                   <div id="advancedFilter"  style="padding-left: 60px;padding-left: 54px\9;height:120px;">
								<table >
									<tr >
										<td>
										 <div  class="easyui-paddingbfpx">
		                  					  <label>From Date</label>
		                   					   <span  class="todate"><label>To Date</label></span>
		                   					  

		                    			</div>
					                     <div class="easyui-paddingbfpx">
					                     	<span>
					                     		<input id="chkDatewise" name="chkDatewise" type="checkbox" /> 
					                        	<input id="dtefromDate" class="easyui-datebox" clear="false"  style="width:160px;"value="${requestScope.filterValues.fromDate}"/>
					                        	
											</span>

<!--		                    			</div> -->
<!--					                     <div class="easyui-paddingbfpx"> -->
<!--					                        <input id="dtefromDate" class="easyui-datebox" clear="false" style="width:160px;"value="${requestScope.filterValues.fromDate}"/>-->

					                        <span  style="margin-left: 25px;"> 
					                       		 <input id="dtetoDate" class="easyui-datebox" clear="false" style="width:160px;"value="${requestScope.filterValues.toDate}"/>
					 							 <span id="err_dtetoDate" class="tpm-errormsg"></span>					 						
										   </span> 		
										   <span class="breakup" style="float:right;margin-top:-30px;">
										   		<input type="button" id="btnBreakUp" style="height: 24px;width:70px;" class="easyui-button"  value="Break Up" />
										   </span>									                    
					                     </div>
					                     
					                     <div style="margin-top:-4px;">
		                  					   <label>From Month</label>
		                   					   <span  class="tomonth"><label>To Month</label></span>
		                   					   <span  style="margin-left: 135px;"><label>Year</label></span>
		                    			</div> 

					                     <div class="easyui-paddingbfpx">
					                     	<span>  <input id="chkMonthwise"  name="chkMonthwise" type="checkbox" checked="checked" /> </span> 
					                        <input id="dtefromMonth" class="easyui-datebox" clear="false" style="width:160px;" value="${requestScope.filterValues.fromMonth}"/>
<!---->
<!--					                     <div class="easyui-paddingbfpx"> -->
<!--					                        <input id="dtefromMonth" class="easyui-datebox" clear="false" style="width:160px;"/>-->

					                         <span  style="margin-left: 25px;">

					                        	<input id="dtetoMonth" class="easyui-datebox" clear="false" style="width:160px;" value="${requestScope.filterValues.toMonth}"/>

<!--					                        	<input id="dtetoMonth" class="easyui-datebox" clear="false" style="width:160px;"/>-->

					                         </span>
					                          <span  style="margin-left: 25px;">
					                         <input id="dteyear" name="year" class="easyui-datebox"  disabled="true" "width:160px" value=""  >
					                         </span>
					                     </div>
									</td>
								</tr>
							</table>
							</div>
						</div>
						<div id="relatedFilterTab" title="Related" style="" >
						<div style=" ">
							<div id="preLoadRelatedFilter" ></div>
							<div id="loadRelatedFilter" ></div>
						</div>
						</div>
						
					</div>
					
				</div>
				</div>
	
			<input type="hidden" id="prevHiddenUrl" />
			
			</div>
			<div id="btns" align="center" style="margin-top: 0px\9;">
	               		 <input type="button" id="view"  class="easyui-button"  style="height:20px;" value="View" />
	               		 <input type="button" id="btnClear"  class="easyui-button" style="height:20px;" value="Clear"  />
	               		 <input type="button" id="btnFilterClose"  class="easyui-button" style="height:20px;" value="Close"  />
            </div>
		
</form>				
<input type="hidden" id="hdncheckLoad" />
<input type="hidden" id="hdnclearbtnval" />
<input type="hidden" id="curUrl" />
<input type="hidden" id="hdnfilterTitle" />
<input type="hidden" id="fromdtVal" value="${requestScope.filterValues.fromDate}"/>
<input type="hidden" id="todtVal" value="${requestScope.filterValues.toDate}"/>
<input type="hidden" id="fromMonVal" value="${requestScope.filterValues.fromMonth}"/>
<input type="hidden" id="toMonVal" value="${requestScope.filterValues.toMonth}"/>
<input type="hidden" id="disableFuncLoc" />
<input type="hidden" id="hdnCompareto" name="hdnCompareto" value="true"/>
<input type="hidden" id="hdnbreakup" value=" "/>


