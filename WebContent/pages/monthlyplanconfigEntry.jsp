<script type="text/javascript"><!--
jQuery.noConflict();
jQuery(document).ready(function(){
/*** Temp use ****/
	jQuery( "#treelayer" ).hide();
	jQuery( "#monthlyplan" ).show();
	jQuery( "#minicon" ).hide();
	jQuery( "#maxicon" ).show();
	jQuery('#gridDiv').css('position','');
	jQuery('#gridDiv').css('padding-right','-1%');
	jQuery('#gridDiv').css('float','left');

 jQuery('#sdm_click').prop('disabled', true);
 jQuery('#sdm_click').hide();

	var url = jQuery('#hiddenUrl').val();
    setLoadFormCallBackFrmId("maintPlanDialog");

	var aeWise=jQuery('#hdnAssEqpWise').val();
	var asseqpWise='';
	
	if(aeWise == 'EQP' ||aeWise == '' || aeWise == null)
	{
		jQuery('input:checkbox[name=chkEquipmentSelectWise]').attr('checked',true);
		asseqpWise='EQP';
	}
	else if(aeWise == 'ASS')
	{
		jQuery('input:checkbox[name=chkAssemblyWise]').attr('checked',true);
		 asseqpWise='ASS';
	}
	var machineId = jQuery("#hdnmchId").val();
	var cellId = jQuery("#hdncellId").val();
	var sectId = jQuery("#hdnsectionId").val();
	var factId = jQuery("#hdnFactId").val();
	var fromMonth = jQuery("#hdnfromMonth").val();
	var toMonth = jQuery("#hdntoMonth").val();
	var relatedTo = jQuery("#hdnrelatedTo").val();
	var filter = jQuery("#hdnfilter").val();
	var url = jQuery("#hiddenUrl").val();
	
	filter += '&chkeqpmntchkbox='+asseqpWise;
	if( fromMonth != ""){ 
		filter += '&dtFromMonth='+fromMonth;
		filter += '&dtToMonth='+toMonth;
		
	}

	
	if( filter != null && filter.length > 0 )
	{
      if( filter.indexOf("?") > 0)	
			filter = filter.substring(filter.indexOf("?")+1);
		if(jQuery("#hdnViewBoolean") != 'Y')
		{
			var filtStrview=jQuery.cookie("filterString");
			if( filtStrview != null && filtStrview.length > 0)
			{
				if(filtStrview.indexOf('hdncmbJobType') != -1)
					jQuery("#hdncmbJobType").val(getFilterValue1(filtStrview ,'hdncmbJobType'));
				else
					jQuery("#hdncmbJobType").val(getFilterValue(filtStrview ,'cmbjobtype'));
				if(filtStrview.indexOf('hdncmbTradeId') != -1)
					jQuery("#hdncmbTradeId").val(getFilterValue1(filtStrview ,'hdncmbTradeId'));
				else
					jQuery("#hdncmbTradeId").val(getFilterValue(filtStrview ,'cmbTradeid'));
			}
		}
		viewGrid("monplanconf_input.mpce",dataString);
		//processGridnew("monplanconf_input.mpce",filter,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete","selectRowFunction");
	}	
	else	{
		//toggleCommonFilter(false);
		var dataString = "";
		if( fromMonth != ""){ 
			dataString += '&dtFromMonth='+fromMonth;
			dataString += '&dtToMonth='+toMonth;
			dataString += '&chkeqpmntchkbox='+asseqpWise;
		}
		//viewGrid(url,"?q=2&"+dataString);
		viewGrid("monplanconf_input.mpce",dataString);	
		//viewGrid("JHScheduleRpt_input.jhScheduleRpt","?");
	}
	
jQuery( "#imgMaintPlan" ).click(function() {
		
		closeFlDialog('mpdialog');
	});
	
	jQuery("#findFL").combobox({
		onSelect:function(recordid){
			//jQuery("#findFLNode").combobox('clear');// comment by madhan
			//reloadCombo("frmFindNode","findFLNode",getComboUrl(recordid.text));// comment by madhan
		}
	});
	jQuery( "#findnext" ).click(function() {
		var searchNode = getSearchString(jQuery("#findFL").combobox('getText'),jQuery('#findFLNode').combobox('getText'),jQuery('#findFLNode').combobox('getValue'));
		
		if(searchNode != null && searchNode != ' ' && searchNode != '')
		{
			jQuery("#mpflTree").jstree("search",searchNode);		
		}
		else
			alert('Select Filter To Search');
			
	});
	jQuery( "#btnFindFilterClear" ).click(function() {	
		jQuery('#findFL').combobox('setValue','cmp');	
		jQuery('#findFLNode').combobox('clear');		
		reloadCombo('frmFindNode','findFLNode','companyCombo.commonFilter');
	});
	
	
	jQuery( "#mpook" ).click(function() {
		
		var filterString = '';
		
		if(jQuery('#chkAssemblyWise').is(':checked') == true)
			filterString +="&asseqpWise=ASS";
		else if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
			filterString +="&asseqpWise=EQP";
		
		filterString += '&cmbMchid='+jQuery('#hdnMachId').val();
		filterString += '&cmbAssmbid='+jQuery('#hdnAssmId').val();
		filterString += '&hdncmbTradeId='+jQuery("#hdncmbTradeId").val();
		filterString += '&hdncmbJobType='+jQuery("#hdncmbJobType").val();
		filterString += '&cmbFactid='+jQuery('#hdnFctId').val();
		filterString += '&cmbSectid='+jQuery('#hdnSectId').val();
		filterString += '&cmbCellid='+jQuery('#hdnCellId').val();
		filterString += '&dtFromMonth='+jQuery('#hdnMonth').val();
		filterString += '&weekNo='+jQuery('#hdnWeekNo').val();
			if(jQuery('#chbworkordercompln').is(':checked') == true ||jQuery('#chbviewworkorder').is(':checked') == true ||jQuery('#chbmodcompworkorder').is(':checked') == true )
		{
			 for(var i=formNavigations.length;i>3;i--) {
				 popFormNavigation();
			 }
	//		var filtStr=jQuery.cookie("filterString");
			closeFlDialog('mpdialog');
		
			if(jQuery('#chbviewworkorder').is(':checked') == true)
				filterString += '&chkselected=Y';
			else if(jQuery('#chbmodcompworkorder').is(':checked') == true){
				filterString += '&chkmodifyselected=modify';
				filterString += '&chkselected=Y';
			
			}
			else
				filterString += '&chkselected=N';
			
			filterString += '&filterButton=false';
			var jsonstr = '';
			var filtStr=jQuery('#MPgrid').jqGrid("getGridParam", "url"); //jQuery.cookie("filterString");

			var asseqpWise='';
			if(jQuery('#chkAssemblyWise').is(':checked') == true)
				asseqpWise='ASS';
			else if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
				asseqpWise='EQP';

			if(jQuery('#flsFrmMonth').val() != '' && jQuery('#flsFrmMonth').val() != ' ' && jQuery('#flsFrmMonth').val() != null)
				 jsonstr = '{"cmbMchid":"'+jQuery('#hdnMachId').val()+'" ,"cmbAssmbid":"'+jQuery('#flsAssembly').val()+'","cmbFactid":"'+jQuery('#hdnFctId').val()+'" ,"cmbSectid":"'+jQuery('#hdnSectId').val() + '" ,"cmbCellid":"'+jQuery('#hdnCellId').val()+'","dtFromMonth":"'+jQuery('#flsFrmMonth').val()+'","dtToMonth":"'+jQuery('#flsToMonth').val()+'","chkMonthwise":"1"}';
		//	var persistdata =  jQuery.parseJSON(jsonstr);// madhan
			//var persistdata = JSON.parse(jsonstr);//add
			if( filtStr.indexOf("?") > 0  )
				filtStr = filtStr.substring(filtStr.indexOf("?")+1);	
			//var persistdataFilter =jQuery.parseJSON('{"asseqpWise":"'+asseqpWise+'","filterString":"'+filtStr+'"}');	
			var persistdataFilter = JSON.parse('{"asseqpWise":"'+asseqpWise+'","filterString":"'+filtStr+'"}');	
			navigateToNextForm('generatewo_input.mpce?'+filterString,'Monthly Calendar',null,persistdataFilter);
			
			// LoadPopUp("Month","generatewo_input.mpce?q=2"+filterString,true,"40%","80%","1%","14%","","Monthly Calendar","","",true,"");

		}
		else if (jQuery('#chbcanworkorder').is(':checked') == true || jQuery('#chbupdworkorder').is(':checked') == true ){
			closeFlDialog('mpdialog');
			
			var filtStr= jQuery('#MPgrid').jqGrid("getGridParam", "url"); //jQuery.cookie("filterString");
			filterString +="&dtFromDate="+jQuery('#hdnMonth').val();
			filterString +="&dtToDate="+jQuery('#hdnMonth').val();
			
			if(jQuery('#chbupdworkorder').is(':checked')){
				
				filterString +="&generateWO=update";
			}
			else
				filterString +="&generateWO=cancel";
			//var filtStr=jQuery.cookie("filterString");
			filterString += '&filterButton=false';
			var asseqpWise='';
			if(jQuery('#chkAssemblyWise').is(':checked') == true)
				asseqpWise='ASS';
			else if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
				asseqpWise='EQP';
			
			if(jQuery('#flsFrmMonth').val() != '' && jQuery('#flsFrmMonth').val() != ' ' && jQuery('#flsFrmMonth').val() != null)
				 jsonstr = '{"cmbMchid":"'+jQuery('#hdnMachId').val()+'" ,"cmbAssmbid":"'+jQuery('#flsAssembly').val()+'","cmbFactid":"'+jQuery('#hdnFctId').val()+'" ,"cmbSectid":"'+jQuery('#hdnSectId').val() + '" ,"cmbCellid":"'+jQuery('#hdnCellId').val()+'","dtFromMonth":"'+jQuery('#flsFrmMonth').val()+'","dtToMonth":"'+jQuery('#flsToMonth').val()+'","chkMonthwise":"1","cboRelatedTo":"MCH"}';
			var persistdata =  jQuery.parseJSON(jsonstr);

			//jQuery.cookie("filterString",filterString);
			if( filtStr.indexOf("?") > 0  )
				filtStr = filtStr.substring(filtStr.indexOf("?")+1);
			
			var persistdataFilter =jQuery.parseJSON('{"asseqpWise":"'+asseqpWise+'","filterString":"'+filtStr+'"}');
			//navigateToNextForm('updatecancelgrid_input.mpce?'+filterString,'Monthly Calendar',null,{"filterString":filtStr});
			navigateToNextForm('updatecancelgrid_input.mpce?'+filterString,'Monthly Calendar',null,persistdataFilter);
		}
		else if (jQuery('#chbgenworkorder').is(':checked') == true ){
			//var filtStr=jQuery.cookie("filterString");
			uncheckdeselectedChk('chbgenworkorder');
			closeFlDialog('mpdialog');
			filterString +="&dtFromDate="+jQuery('#hdnMonth').val();
			filterString +="&dtToDate="+jQuery('#hdnMonth').val();
			filterString +="&generateWO=true";
			filterString +="&hdnLocId="+jQuery('#hdnLocId').val();
			if(jQuery("#hdnAEVal").val() == '-')
				filterString +="&actTypeSel=SDM";
			var filtStr= jQuery('#MPgrid').jqGrid("getGridParam", "url"); //jQuery.cookie("filterString");
			filterString += '&filterButton=false';				
			var asseqpWise='';
			if(jQuery('#chkAssemblyWise').is(':checked') == true)
				asseqpWise='ASS';
			else if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
				asseqpWise='EQP';
			
			if(jQuery('#flsFrmMonth').val() != '' && jQuery('#flsFrmMonth').val() != ' ' && jQuery('#flsFrmMonth').val() != null)
				 jsonstr = '{"cmbLocnid":"'+jQuery('#hdnLocId').val()+'","cmbMchid":"'+jQuery('#hdnMachId').val()+'" ,"cmbAssmbid":"'+jQuery('#flsAssembly').val()+'","cmbFactid":"'+jQuery('#hdnFctId').val()+'" ,"cmbSectid":"'+jQuery('#hdnSectId').val() + '" ,"cmbCellid":"'+jQuery('#hdnCellId').val()+'","dtFromMonth":"'+jQuery('#flsFrmMonth').val()+'","dtToMonth":"'+jQuery('#flsToMonth').val()+'","chkMonthwise":"1"}';
			var persistdata =  jQuery.parseJSON(jsonstr);

			if( filtStr.indexOf("?") > 0  )
				filtStr = filtStr.substring(filtStr.indexOf("?")+1);
			//alert(persistdata);
			var persistdataFilter =jQuery.parseJSON('{"asseqpWise":"'+asseqpWise+'","filterString":"'+filtStr+'"}');
//			jQuery.cookie("filterString",filterString);			
			navigateToNextForm('updtCancelGridsel_input.mpce?'+filterString,'',null,persistdataFilter);

		}
		else if (jQuery('#chbrescheduleact').is(':checked') == true ){
			
			closeFlDialog('mpdialog');
			var filterStr=""; 
			filterStr += 'cmbMchid='+jQuery('#hdnMachId').val();
			filterStr += '&cmbSectid='+jQuery('#hdnSectId').val();
			filterStr += '&cmbCellid='+jQuery('#hdnCellId').val();
			filterStr += '&dtFromMonth='+jQuery('#hdnMonth').val();
			filterStr += '&weekNo='+jQuery('#hdnWeekNo').val();
			filterStr += '&acttype='+jQuery("#hdnAEVal").val().trim();
			filterStr += '&cmbAssmbid='+jQuery('#hdnAssmId').val();
			var asseqpWise='';
			if(jQuery('#chkAssemblyWise').is(':checked') == true)
				asseqpWise='ASS';
			else if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
				asseqpWise='EQP';
			
			if(jQuery('#flsFrmMonth').val() != '' && jQuery('#flsFrmMonth').val() != ' ' && jQuery('#flsFrmMonth').val() != null)
				 jsonstr = '{"cmbMchid":"'+jQuery('#hdnMachId').val()+'" ,"cmbAssmbid":"'+jQuery('#flsAssembly').val()+'","cmbFactid":"'+jQuery('#hdnFctId').val()+'" ,"cmbSectid":"'+jQuery('#hdnSectId').val() + '" ,"cmbCellid":"'+jQuery('#hdnCellId').val()+'","dtFromMonth":"'+jQuery('#flsFrmMonth').val()+'","dtToMonth":"'+jQuery('#flsToMonth').val()+'","chkMonthwise":"1"}';
			var persistdata =  jQuery.parseJSON(jsonstr);
			filterStr +=  "&dtFromMonth="+jQuery('#flsFrmMonth').val()+"&dtToMonth="+jQuery('#flsToMonth').val()+"&asseqpWise="+asseqpWise;
			//jQuery.cookie("filterString",filterStr);
				LoadPopUp("loadReschedule", "reschedule_input.mpce?"+filterStr, true,"54%","48%","0%","10%", "reschedule_successCallBack","Reschedule Details");
		//	navigateToNextForm('reschedule_input.mpce?&filterString='+filterString,'');
		}
		
	});

	jQuery( "#findopt" ).click(function() {		
		var grpbyequipmnt = document.layoutfil.grpbyequipmnt.checked;		
		if(grpbyequipmnt == true)
		{
			
			jQuery( "#findoption" ).show();
			jQuery( "#layoutfilter" ).hide();
	 	}		
	});

	jQuery( "#close" ).click(function() {		
			jQuery('#findFLNode').combobox('clear');
			jQuery( "#findoption" ).hide();
			jQuery( "#layoutfilter" ).show();
	});

	jQuery( "#min" ).click(function() {		
		jQuery( "#treelayer" ).hide();
		jQuery( "#monthlyplan" ).show();
		jQuery( "#minicon" ).hide();
		jQuery( "#maxicon" ).show();
		jQuery('#gridDiv').css('position','');
		jQuery('#gridDiv').css('padding-right','-1%');
		jQuery('#gridDiv').css('float','left');
		if(screen.width <= 1024){
			jQuery( "#MPgrid" ).setGridWidth(790);
			jQuery('#gridDiv').css('margin-left','0%');
		}	
		else{
			jQuery( "#MPgrid" ).setGridWidth(1080);
		}
	});

	jQuery( "#max" ).click(function() {
		jQuery( "#treelayer" ).show();
		jQuery( "#monthlyplan" ).show();
		jQuery( "#minicon" ).show();
		jQuery( "#maxicon" ).hide();
		
		if(screen.width <= 1024){
			jQuery( "#MPgrid" ).setGridWidth(600);	
			
		}
		else{
			jQuery( "#MPgrid" ).setGridWidth(800);
		}
	});
/*
jQuery(function () {
		processTree( jQuery("#mpflTree"),'loadval.funlocn','searchnode.funlocn');
		jQuery("#mpflTree").bind("search.jstree", function (e, data) {
	          //  alert("Found " + data.rslt.nodes.length + " nodes matching '" + data.rslt.str + "'.");
		});
});*/
jQuery('#sdm_click').click(function(){
	LoadPopUp("loadSDM", "sdmOpen_input.mpce?q=2" , true,"50%","37%","0%","10%", "sdm_successCallBack","Shut Down Maintainence");
});
});

jQuery('#chkEquipmentSelectWise').click(function()
		{
	jQuery('input:checkbox[name=chkEquipmentSelectWise]').prop('checked',true);
	jQuery('input:checkbox[name=chkAssemblyWise]').prop('checked',false);
	var filterString=jQuery('#hdnfilterString').val();
	if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
	{
		if(validateFilterSelection(filterString))
		{
			filterString += '&chkeqpmntchkbox=EQP';
			processGridnew("monplanconf_input.mpce","?q=2&filterString="+filterString,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete","selectRowFunction");
		}
	}
		});
		
jQuery('#chkAssemblyWise').click(function()
		{
	jQuery('input:checkbox[name=chkEquipmentSelectWise]').prop('checked',false);
	jQuery('input:checkbox[name=chkAssemblyWise]').prop('checked',true);
	var filterString=jQuery('#hdnfilterString').val();
	if(jQuery('#chkAssemblyWise').is(':checked') == true )
		{
		
		if(validateFilterSelection(filterString))
			{
				filterString += '&chkeqpmntchkbox=ASS';
				processGridnew("monplanconf_input.mpce","?q=2&filterString="+filterString,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
			}
		else
			{
				filterString += '&chkeqpmntchkbox=ASS';
			//filterString +="&asseqpWise="+'ASS';
				processGridnew("monplanconf_input.mpce","?q=2&filterString="+filterString,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
			}
		}
		
		});

		
function loadSDM_onClose(){
	var filtStr=jQuery.cookie("filterString");
	processGridnew("monplanconf_input.mpce","?q=2&filterString="+filtStr,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
	return true;
}
function maintPlanDialog_afterLoadCallBack(){
	var filter = jQuery("#hdnfilter").val();
	var url = jQuery('#hiddenUrl').val();
	if(filter == '' || filter.trim()== ''){
	//	toggleCommonFilter();
	}
	else
		processGridnew("monplanconf_input.mpce",filter,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
} 
function closeFlDialog(dlgId)
{
	 jQuery( '#'+dlgId ).hide();
	 jQuery('#mstfrm_div').removeClass('popup-mask');	
	 jQuery( '#'+dlgId ).removeClass('custom-popup');
}
function customMenu(node) {
}

function getComboUrl(cmbTxt)
{
	var cmbUrl = null;
	if(cmbTxt == 'Company')
		cmbUrl = "companyCombo.commonFilter";
	else if(cmbTxt == 'Location')
		cmbUrl = "location.funlocn";
	else if(cmbTxt == 'Unit')
		cmbUrl = "factroyCombo.commonFilter";
	else if(cmbTxt == 'Section')
		cmbUrl = "sectionCombo.commonFilter";
	else if(cmbTxt == 'Line')
		cmbUrl = "cellCombo.commonFilter";
	else if(cmbTxt == 'Equipment')
		cmbUrl = "machineCombo.commonFilter";
	else if(cmbTxt == 'Assembly')
		cmbUrl = "assembly.funlocn";
	else if(cmbTxt == 'Spare')
		cmbUrl = "spareCombo.funlocn";	

	return cmbUrl;	

}
function loadReschedule_onClose(){
	///var filtStr=jQuery.cookie("filterString");
	//processGridnew("monplanconf_input.mpce",filtStr,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
//	jQuery('#MPgrid').trigger("reloadGrid");
	return true;
}
function loadReschedule_afterclose(){
	
//	jQuery('#MPgrid').trigger("reloadGrid");
}
function reloadMPGrid(){
	refreshForm();	
}
function getSearchString(cmbTxt,dispField)
{
	var nodearr = new Array();
	nodeArr = dispField.split('-');	
	var toSearch = dispField.replace('-'+nodeArr[nodeArr.length-1],'');
	var cmbUrl = null;

	if(cmbTxt == 'Location')
		cmbUrl = nodeArr[1].trim();
	else if(cmbTxt == 'Equipment')
	{
		cmbUrl = dispField.trim();
	}
	else if(cmbTxt == 'Section' || cmbTxt == 'Line')
	{
		cmbUrl = nodeArr[0].trim();
	}
	else if(cmbTxt == 'Spare')
	{
	    cmbUrl = dispField.trim();
	}
	else
		cmbUrl =toSearch.trim();
	
	return cmbUrl;	
}

function viewGrid(url,filterString)
{
	jQuery('#hdnfilterString').val(filterString);
	if( validateFilterSelection(filterString))
	{	
		var gridurl = jQuery('#hiddenUrl').val();
		var asseqpWise='';
		if(jQuery('#chkAssemblyWise').is(':checked') == true)
			asseqpWise='ASS';
		else if(jQuery('#chkEquipmentSelectWise').is(':checked') == true)
			asseqpWise='EQP';
		if(getFilterValue(filterString ,'cmbTradeid')!= null)
			jQuery("#hdncmbTradeId").val(getFilterValue(filterString ,'cmbTradeid'));
		if(getFilterValue(filterString ,'cmbjobtype')!= null)
			jQuery("#hdncmbJobType").val(getFilterValue(filterString ,'cmbjobtype'));
		jQuery("#hdnViewBoolean").val('Y');
			
		filterString +="&asseqpWise="+asseqpWise+"&chkeqpmntchkbox="+asseqpWise;
		processGridnew(url,filterString,"MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
		
		return true;
	}
	return false;
}
function openmpdialog(){

	//jQuery('#mstfrm_div').addClass('popup-mask');	
	jQuery('#mstfrm_div').show();
	jQuery('#mpdialog').addClass('custom-popup');		
    jQuery('#mpdialog').show();		  
   // jQuery('#mpdialog').css('border','1px solid #2364CA');
    jQuery('#mpdialog').css('z-index',100);
    jQuery('#mpdialog').css('top','55');
    jQuery('#mpdialog').css('margin-left','108');
    jQuery('.popup-mask').css('z-index',3); 
}
jQuery('#chbgenworkorder').click(function(){		
	if(jQuery('#chbgenworkorder').attr('checked',true)){
	uncheckdeselectedChk('chbgenworkorder');	
	}
});
jQuery('#chbupdworkorder').click(function(){
if(jQuery('#chbupdworkorder').attr('checked',true)){
	uncheckdeselectedChk('chbupdworkorder');	
	}
});
jQuery('#chbviewworkorder').click(function(){
if(jQuery('#chbviewworkorder').attr('checked',true)){
	uncheckdeselectedChk('chbviewworkorder');	
	}
});
jQuery('#chbcanworkorder').click(function(){
	if(jQuery('#chbcanworkorder').attr('checked',true)){
		uncheckdeselectedChk('chbcanworkorder');	
		}
	});
jQuery('#chbrescheduleact').click(function(){
	if(jQuery('#chbrescheduleact').attr('checked',true)){
		uncheckdeselectedChk('chbrescheduleact');	
		}
	});
jQuery('#chbviewworkorder').click(function(){
	if(jQuery('#chbviewworkorder').attr('checked',true)){
		uncheckdeselectedChk('chbviewworkorder');	
		}
	});
jQuery('#chbmodcompworkorder').click(function(){
	if(jQuery('#chbmodcompworkorder').attr('checked',true)){
		uncheckdeselectedChk('chbmodcompworkorder');	
		}
	});
jQuery('#chbresourceplan').click(function(){
	if(jQuery('#chbresourceplan').attr('checked',true)){
		uncheckdeselectedChk('chbresourceplan');	
		}
	});
jQuery('#chbworkordercompln').click(function(){
	if(jQuery('#chbworkordercompln').attr('checked',true)){
		uncheckdeselectedChk('chbworkordercompln');	
		}
	});
function uncheckdeselectedChk( selectedChk ){

	jQuery('input:checkbox[id=chbupdworkorder]').attr('checked',false);
	jQuery('input:checkbox[id=chbcanworkorder]').attr('checked',false);
	jQuery('input:checkbox[id=chbrescheduleact]').attr('checked',false);
	jQuery('input:checkbox[id=chbviewworkorder]').attr('checked',false);
	jQuery('input:checkbox[id=chbmodcompworkorder]').attr('checked',false);
	jQuery('input:checkbox[id=chbresourceplan]').attr('checked',false);
	jQuery('input:checkbox[id=chbworkordercompln]').attr('checked',false);
	jQuery('input:checkbox[id=chbgenworkorder]').attr('checked',false);
	if(selectedChk != ' ' && selectedChk != '' && selectedChk != null)
		jQuery('input:checkbox[id='+selectedChk+']').attr('checked',true);
}



function monPlanDBLClick(id)
{	
	
	var rowData = jQuery("#MPgrid").jqGrid('getRowData',id);
	jQuery('#hdnLocId').val(rowData.location_id);
	jQuery('#hdnMachId').val(rowData.machine_id);
	jQuery('#hdnFctId').val(rowData.factory_id);
	jQuery('#hdnSectId').val(rowData.line_id);
	jQuery('#hdnCellId').val(rowData.cell_id);
	jQuery('#hdnAssmId').val(rowData.assembly_id);
	//var curDateTime = getServerDateTime();
	var selectedmonth= jQuery('#hdnMonth').val();
	var chkFutDate = compareDates(selectedmonth,"date Greater");
	 var row = jQuery("#MPgrid").jqGrid('getDataIDs');
	// alert( rowData +" rowData ");
		
	 var cm = jQuery("#MPgrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=10;j<cm.length;j++)
     	 {var zeroVal = jQuery("#MPgrid").jqGrid('getCell',row[i],cm[j].name);	
			  if(zeroVal =='4'){
			  		jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#ff8040'});
			  }
     	 }
	 }
	//if(chkFutDate){
		
		if( jQuery("#hdnAECol").val() == "" || ( jQuery("#hdnAECol").val() != null &&  jQuery("#hdnAECol").val() != '' &&  jQuery("#hdnAECol").val() != ' '))
		{ 
			
			openmpdialog();
			uncheckdeselectedChk('');
			//LoadPopUp("mpdialog", "", true,"25%","70%","0%","10%", "mpdialog_successCallBack","Maintainence Plan Options");
			readOnlyFields('chbworkordercompln');
	    	readOnlyFields('chbupdworkorder');
	    	readOnlyFields('chbcanworkorder');
	    	readOnlyFields('chbrescheduleact');
	    	//readOnlyFields('chbviewworkorder');
	    	readOnlyFields('chbmodcompworkorder');
	    	readOnlyFields('chbresourceplan');
	    	//readOnlyFields('chbgenworkorder');
	    	if( jQuery("#hdnAEVal").val() == "" ){
	    		enableFields('chbgenworkorder');
	    		readOnlyFields('chbviewworkorder');
	    		readOnlyFields('chbrescheduleact');
		    }
		    else if (jQuery("#hdnAEVal").val() == '.'  )
			{
				//alert( hdnAEVal +" hdnAEVal ");
				
				enableFields('chbworkordercompln');
		    	enableFields('chbcanworkorder');
		    	enableFields('chbgenworkorder');
		    	enableFields('chbrescheduleact');
		    	readOnlyFields('chbviewworkorder');
			}
		    else if(jQuery("#hdnAEVal").val() == '-')
			{
		    	enableFields('chbgenworkorder');
		    	//enableFields('chbrescheduleact');
		    	readOnlyFields('chbviewworkorder');
			}
		    else if(jQuery("#hdnAEVal").val() == 'A' || jQuery("#hdnAEVal").val() =='   A')
			{
		    	
		    	enableFields('chbworkordercompln');
		    	enableFields('chbcanworkorder');
		    	enableFields('chbgenworkorder');
		    	//enableFields('chbrescheduleact');
		    	/* Enable  by TEAM*/ 
		    	//enableFields('chbrescheduleact');
		    	enableFields('chbupdworkorder');
			}
		    else if(jQuery("#hdnAEVal").val() == 'IC')
			{
				//alert( hdnAEVal +" hdnAEVal ");
		    	enableFields('chbworkordercompln');
		    	enableFields('chbcanworkorder');
		    	//enableFields('chbupdworkorder');
			}

		    else if(jQuery("#hdnAEVal").val() == 'PC')
			{
		    	enableFields('chbworkordercompln');
		    	enableFields('chbcanworkorder');
		    	//enableFields('chbupdworkorder');
			}
		    else if(jQuery("#hdnAEVal").val() == String.fromCharCode(10003))
			{
		    	
		    	enableFields('chbviewworkorder');
		    	readOnlyFields('chbgenworkorder');
		    	enableFields('chbmodcompworkorder');
			}
		    
		   // jQuery('#mpdialog').css('width','200px');
			//jQuery('#mpdialog').css('height','400px');
		}
		else  {
			//openmpdialog();
			//alert( hdnAEVal +" hdnAEVal ");
			uncheckdeselectedChk('');
	    	enableFields('chbgenworkorder');
	    	readOnlyFields('chbworkordercompln');
	    	readOnlyFields('chbupdworkorder');
	    	readOnlyFields('chbcanworkorder');
	    	readOnlyFields('chbrescheduleact');
	    	readOnlyFields('chbviewworkorder');
	    	readOnlyFields('chbmodcompworkorder');
	    	readOnlyFields('chbresourceplan');
	    	
//		}
	}
}

var lastSel;
jQuery("#MPgrid").jqGrid({

   onSelectRow: function(id){ 
	  // alert(2);
      if(id && id!==lastSel){ 
         jQuery('#MPgrid').restoreRow(lastSel); 
         lastSel=id; 
      } 
      jQuery('#MPgrid').editRow(id, true); 
   },

});
 //????????
 
 function compareDates(selectedDate,errMsg)
	    {
		 	
		 			    
	 /*	var currentDate = getServerDateTime(); //new Date();
			var hours = currentDate.getHours();
			var minutes = currentDate.getMinutes();
       		var selDate = selectedDate;
       		selDate ="01-"+selDate+"00:00";
       			
			if(convertStringToDate(selDate) > currentDate )
				{   
				
				 //alert("Date Greater than current Date ");
				 
				 return false;
				}
			else*/
				return true;
	        	
		}
 /*end*/
 
 jQuery('#selectBtn').click(function()
	{
					
				var rowid = jQuery("#MPgrid").jqGrid('getGridParam','selrow');	
				if(rowid=='' || rowid== null){
					alert("Select Record to Complete The Activity");
					}	
				else	
				monPlanDBLClick(rowid);
			});
function monthlyPlanGridComplete_afterLoad(data)
{



/*	if(screen.width <= 1024){
		jQuery( "#MPgrid" ).setGridWidth("500px");
		jQuery('#gridDiv').css('position','absolute');
		jQuery('#gridDiv').css('margin-left','29%');
	}	
	else{
		jQuery( "#MPgrid" ).setGridWidth("90%");
		if(jQuery( "#maxicon" ).css('display') == 'block')
			jQuery( "#MPgrid" ).setGridWidth(1080);
	}
	*/
	 var row = jQuery("#MPgrid").jqGrid('getDataIDs');
	
	 var cm = jQuery("#MPgrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=10;j<cm.length;j++)
     	 {
			  var zeroVal = jQuery("#MPgrid").jqGrid('getCell',row[i],cm[j].name);			 

			  
			  if(zeroVal != ' ' && zeroVal != '' && zeroVal != null)
			  {
				  if(zeroVal =='A'){
					  
					jQuery("#hdnAcivity").val("A");  
					 jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,"A",{'color':'#4D27C5','font-weight':'bold','font-size':'15px','background-color':'#F9BF59'});
				  }
				  else if(zeroVal =='IC'){
					  jQuery("#hdnAcivity").val("IC");
					  jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,"IC",{'color':'#4D27C5','font-weight':'bold','font-size':'15px','background-color':'#F9BF59'});
				  }
				  if(zeroVal =='1'){
						
				  		jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,"&#10003;",{'color':'#4D27C5','font-weight':'bold','font-size':'18px','background-color':'#ff8040'});
					  }
				  else if(zeroVal =='4'){
					 // alert(zeroVal +"zeroVal");
			  		jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,".",{'color':'#ff8040','font-weight':'bold','font-size':'15px','background-color':'#ff8040'});
				  }
				  else if(zeroVal =='5'){
					 // alert(zeroVal +"zeroVal");
				  		jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,"-",{'color':'#F9BF59','font-weight':'bold','font-size':'15px','background-color':'#F9BF59'});
					  }
				  else if(zeroVal =='6'){
					  jQuery("#hdnAcivity").val("PC");
				  		jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,"PC",{'color':'#4D27C5','font-weight':'bold','font-size':'15px','background-color':'#d3d603'});
					  }
			  }
			/*  else{
				   
				  jQuery("#MPgrid").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#4D27C5','font-weight':'bold','font-size':'12px','background-color':'#fff'});
			  } */
	    }
		
	 }
	
	 jQuery("#MPgrid").setGridParam({
			onCellSelect:function(id,cellidx,cellvalue) {
				 
				jQuery(cellidx).css('border','solid 1px #000');
				if(cellvalue == '.')
				{
					 jQuery("#hdnAECol").val(cellidx);
					 jQuery("#hdnAEVal").val(cellvalue);
				}
				else if(cellvalue == '-')
				{
					 jQuery("#hdnAECol").val(cellidx);
					 jQuery("#hdnAEVal").val(cellvalue);
				}
				else if(cellvalue == 'A' || cellvalue == '   A')
				{ 
					 jQuery("#hdnAECol").val(cellidx);
					 jQuery("#hdnAEVal").val(cellvalue);
				}
				else if(cellvalue == String.fromCharCode(10003))
				{ 
					 jQuery("#hdnAECol").val(cellidx);
					 jQuery("#hdnAEVal").val(cellvalue);
				}
				else if(cellvalue == 'IC')
				{ 
					 jQuery("#hdnAECol").val(cellidx);
					 jQuery("#hdnAEVal").val(cellvalue);
				}
				else
				{
					 jQuery("#hdnAECol").val('');
					 jQuery("#hdnAEVal").val(cellvalue);
				}
				  genWOView(id,cellidx,cellvalue);
			 }
	 
	});
	 	
}
function genWOView(id,cellidx,cellvalue){

	var colm = jQuery("#MPgrid").jqGrid ('getGridParam', 'colModel');
			selId = colm[cellidx].name;
			var weekNo = selId.substring(1, 2);
			var month = selId.substring(2,10);
			jQuery('#hdnWeekNo').val(weekNo);
			jQuery('#hdnMonth').val(month);
	
	/* jQuery("#hiddenrowid").val(selId);*/
}


function validateFilterSelection(filterString){
	
	
	jQuery.cookie("filterString",filterString);
	if(filterString == '?q=2')
	{
	
	return true;
	}	
	/*else if( getFilterValue(filterString, "cmbSectid") == "") 
		{
		alert("Select Section");
		return false;
		}*/
	else{
		jQuery('#hiddenUrl').val(' monplanconf_input.mpce');
		if(getFilterValue(filterString ,'dtFromMonth')!= ' ' && getFilterValue(filterString ,'dtFromMonth')!= null){
			jQuery('#flsFrmMonth').val(getFilterValue(filterString ,'dtFromMonth'));
			jQuery('#flsToMonth').val(getFilterValue(filterString ,'dtToMonth'));
			jQuery('#flsAssembly').val(getFilterValue(filterString ,'cmbAssmbid'));
		}
		return  true;
	}

}
function frmFilter_enableDisableSuccessCallBack()
{
	//jQuery("#chkMonthwise").attr('checked',true);	
	setTimeout(function() {jQuery('#flsFrmMonth').val(' ');},200);
	jQuery('#flsToMonth').val(' ');
	jQuery('#flsAssembly').val(' ');
	var fromMonth = jQuery("#hdnfromMonth").val();
	var toMonth = jQuery("#hdntoMonth").val();
	jQuery('#dtefromMonth').datebox('setValue',fromMonth);
	jQuery('#dtetoMonth').datebox('setValue',toMonth);	
	readOnlyFields('dteyear');
	jQuery('#cmbCircle').combobox('disable');
	
}

/*jQuery('#relatedFilterTab').tabs({
 	onSelect: function(title){
 	 	alert(title);
	    if(title =="Related")
		    {
		    	jQuery('#cmbActivity').combobox('disable');
		    	jQuery('#cmbsupplier').combobox('disable');
		    	jQuery('#cmbprodcngroup').combobox('disable');
		    	jQuery('#cmbJhStep').combobox('disable');
		    }
		    
	    else if(title =="Common")
		    {
	    		jQuery('#cmbCircle').combobox('disable');
		    }
    }

});
jQuery('#commonFilter').tabs({
 	onSelect: function(title){
 	 	alert(title);
	    if(title =="Related")
		    {
		    	jQuery('#cmbActivity').combobox('disable');
		    	jQuery('#cmbsupplier').combobox('disable');
		    	jQuery('#cmbprodcngroup').combobox('disable');
		    	jQuery('#cmbJhStep').combobox('disable');
		    }
		    
	    else if(title =="Common")
		    {
	    		jQuery('#cmbCircle').combobox('disable');
		    }
    }

});*/

function getFilterValue1(filterString,valueIdentifier)
{
	if( filterString != null && valueIdentifier != null && filterString.indexOf(valueIdentifier) > -1 )
	{
		var val;
		var substr = filterString.substring(filterString.indexOf(valueIdentifier)+valueIdentifier.length+1 );
		if(substr.indexOf("&") != -1)
			val = substr.substring(0,substr.indexOf("&"));
		else 
			val = substr;
		return val;
	}	
	
	return "";
}
function selectRowFunction(id)
{
	var rowid = jQuery("#LogMonGrid").jqGrid('getGridParam','selrow');	

}
jQuery('#refBtn').click(function(){
	filterString = '&chkeqpmntchkbox=EQP';
	var cellId = jQuery("#hdncellId").val();
	alert(cellId +" filterString");
	processGridnew("monplanconf_process.mpce","?q=2&filterString="+filterString+"&mode=click","MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete","selectRowFunction");
	
	//processGridnew("monplanconf_process.mpce","?q=2&filterString="+filterString+"&mode=click","MPgrid","MPpager","","monPlanDBLClick","","monthlyPlanGridComplete");
	processAjaxCalls(url,filterString,"monthlyPlanGridComplete","","");
	
});

</script>
<div class="wrapper" style="width:100%">
<div id="dialog_wnd" title="Filter" style="display:none" >
<input type="hidden" id="hdnAECol" name="hdnAECol">
<input type="hidden" id="hdnAEVal" name="hdnAEVal">
<form id="filterDialog" name="filterDialog">

	<table border="0" align="center">
		<tr>
			<td colspan="2">
					<div style="padding-left:100px;">
				     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:400px;padding-left:30px;">
							<input id="grpByCell" type="checkbox"/><span  style="margin-left: 2px;"><label>Group By Cell</label></span>
							<span  style="margin-left: 2px;"><input id="grpByEqp" type="checkbox"/></span><span  style="margin-left: 2px;"><label>Group By Equipment</label></span>
							<span  style="margin-left: 2px;"><input id="allEqp" type="checkbox"/></span><span  style="margin-left: 2px;"><label>All Equipment</label></span>
					</div>
					</div>
			</td>
		</tr>
		<tr>
			<td style="width:50%" valign='top'>
					 <div  class="easyui-paddingbfpx" style="padding-left:30px;">
				           <label>Factory</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
						   <input  id="cmbfact" name="cmbfact" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				            <label>Section</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							<input  id="cmbsect" name="cmbsect" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				            <label>Cost Center</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							<input  id="cmbcc" name="cmbcc" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				            <label>Cell</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							 <input  id="cmbcell" name="cmbcell" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				             <label>Equipment Group</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							 <input  id="cmbeqptgrp" name="cmbeqptgrp" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
				</td>
				<td style="width:50%" valign='top'>
	
							 
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				             <label>Equipment</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							 <input  id="cmbeqpt" name="cmbeqpt" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				             <label>Maint. Section</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							 <input  id="cmbmaintsec" name="cmbmaintsec" class="easyui-combobox" style="width:255px;"/ >                       
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				             <label>Frequency</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							  <select id="freq" class="easyui-combobox" name="freq" style="width:130px;" required="true">
									<option value="-"> All</option>
							 </select> 
					</div>
					
					<div  class="easyui-paddingbfpx" style="padding-left:30px;">
				             <label>Equipment Cond</label>                       
				    </div> 
					<div class="easyui-paddingbfpx" style="padding-left:30px;"> 
							<select id="eqpcond" class="easyui-combobox" name="eqpcond" style="width:130px;" required="true">
								<option value="-"> Both</option>
							</select>
					</div>
	
				   <div  class="easyui-paddingbfpx" style="padding-left:30px;">
		                  	<input id="frommonth" type="checkbox"/><label>From Month</label>
		                   	<span  style="margin-left: 78px;"><label>To Month</label></span>
		          </div> 
				   <div class="easyui-paddingbfpx" style="padding-left:30px;"> 
					       <input id="fromonth" name="fromonth" class="easyui-datebox" required="true" style="width:130px;"/>
					        <span  style="margin-left: 15px;"> 
					             <input id="tomonth" name="tomonth" class="easyui-datebox" required="true" style="width:130px;"/>
					        </span> 
				   </div>
		</td>
  
    <tr>
    	<td colspan="2">
    <div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
    		<table id="dialoglist" style="width:100%"><tr><td/></tr></table>
			<div id="dialogpager"></div>
    </div>
    </td>
   </tr>
    <tr>
    	<td colspan="2" align="center">
    
     <div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;">
               <input type="button" id="ok"  class="easyui-button" onclick="" value="Ok"/>
               <input type="button" id="clear"  class="easyui-button" onclick="" value="Clear"/>
    </div>
    </td>
    </tr>
    </table>

</form>
</div>

<div id="mpdialog" class="flPopUpBox" style="width:300px;background: none repeat scroll 0 0 #E0F3FA;">
<div id="titleMaintPlan" class="sub-header " style="width:318px;margin-left:-12px;margin-left:-8px\9;margin-top:-10px;">
		<label id="lblMaintPlan" style="margin-left:1px;font-size:11px;">Maintenance Plan Options</label> 
		<img id="imgMaintPlan" src="images/close-butt1.png" style="float:right;margin-top:-5%\9; "/>
</div>
<form id="maintPlanDialog" name="maintPlanDialog">

	
			 <div class="easyui-paddingbtpx" style="padding-left:50px;padding-top:30px;">
			 	<input id="chbgenworkorder" type="checkbox"/><span style="padding-left:2px;"><label style="FONT-WEIGHT: bold;">Generate Work Order </label></span>
			 </div>
		
			  <div class="easyui-paddingbtpx"  style="padding-left:50px;">
			 	<input id="chbupdworkorder" type="checkbox"/><span style="padding-left:2px;"><label>Update Work Order</label></span>
			 </div>
			 
			  <div class="easyui-paddingbtpx"  style="padding-left:50px;">
			 	<input id="chbcanworkorder" type="checkbox"/><span style="padding-left:2px;"><label>Cancel Work Order</label></span>
			 </div>
			 
			  <div class="easyui-paddingbtpx"  style="padding-left:50px;">
			 	<input id="chbrescheduleact" type="checkbox"/><span style="padding-left:2px;"><label>Reschedule Activity</label></span>
			 </div>
			 
			  <div class="easyui-paddingbtpx" style="padding-left:50px;">
			 	<input id="chbworkordercompln" type="checkbox"/><span style="padding-left:2px;"><label>Work Order Completion</label></span>
			 </div>
			 
			  <div class="easyui-paddingbtpx"  style="padding-left:50px;">
			 	<input id="chbviewworkorder" type="checkbox"/><span style="padding-left:2px;"><label>View Work Order</label></span>
			 </div>
			 
			  <div class="easyui-paddingbtpx" style="padding-left:50px;">
			 	<input id="chbmodcompworkorder" type="checkbox"/><span style="padding-left:2px;"><label>Modify Completed Work Order</label></span>
			 </div>
			 
			  <div class="easyui-paddingbtpx"  style="padding-left:50px;">
			 	<input id="chbresourceplan" type="checkbox"/><span style="padding-left:2px;"><label>Resource Plan</label></span>
			 </div>
			 
			   <div class="easyui-paddingbtpx" style="padding-left:120px;">
		               <input type="button" id="mpook"  class="easyui-button" onclick="" value="Ok"/>
<!--		               <input type="button" id="mpoclear"  class="easyui-button" onclick="" value="Clear"/>-->
   			   </div>
		
</form>
</div>

<div id="resdetdialog" title="Reschedule Details" style="display:none">
<form id="resdetfilterDialog" name="resdetfilterDialog">
	<table border="0" align="center">
	<tr>
	<td style="width:50%" valign='top'>
	<div class="easyui-paddingbfpx" style="padding-left:30px;padding-top:30px;"><label> Activities For Week </label></div> 
	<div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
			<input type="text" id="actforweek" name="actforweek" class="easyui-text" style="width:130px;" value=""  >
	</div>
	
	<div class="easyui-paddingbfpx" style="padding-left:30px;">
		<label> Section</label>
	</div>
	<div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
		<input id="cmbsect2" name="cmbsect2" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div class="easyui-paddingbfpx" style="padding-left:30px;">
		<label> Cell</label>
	</div>
	<div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
		<input id="cmbcell2" name="cmbcell2" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
		<div class="easyui-paddingbfpx" style="padding-left:30px;">
		<label> Equipment</label>
	</div>
	<div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
		<input id="cmbeqpt2" name="cmbeqpt2" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
							
	<div class="easyui-paddingbfpx" style="padding-left:30px;">
		<label class="mandatory-lbl"> Reschedule By</label>
	</div>
	<div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
		<input id="cmbrescheduleby" name="cmbrescheduleby" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>


	</td>
	<td style="width:50%" valign='top'>
	
	 <div  class="easyui-paddingbfpx" style="padding-left:30px;padding-top:30px;">
                  	<label class="mandatory-lbl">Reschedule Date</label>
                   	<span  style="margin-left: 34px;"><label>Reschedule Week</label></span>
     </div> 
	 <div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
			       <input id="rescheduledate" name="rescheduledate" class="easyui-datebox" required="true" style="width:110px;"/>
			        <span  style="margin-left: 32px;"> 
			            <input type="text" id="rescheduleweek" name="rescheduleweek"  class="easyui-text" style="width:110px;" value="" / >
			        </span> 
	 </div>
	
	
   
   <div class="easyui-paddingbfpx" style="padding-left:30px;">
   					<label class="mandatory-lbl">Reason</label>
   </div> 
   <div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
			<textarea rows="3" cols="34" id="reason" name="reason"></textarea>
   </div>
   
    <div class="easyui-paddingbfpx" style="padding-left:30px;">
   					<label class="mandatory-lbl">Remarks</label>
   </div> 
   <div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;"> 
			<textarea rows="3" cols="34" id="remarks" name="remarks"></textarea>
   </div>
				

	</td>
	</tr>
	<tr>
	<td colspan="2" align="center">
	
	<div class="easyui-paddingbfpx" style="padding-right:10px;padding-left:30px;">
		     <input type="button" id="resok"  class="easyui-button" onclick="" value="Ok"/>
		     <input type="button" id="rescancel"  class="easyui-button" onclick="" value="Cancel"/>
   </div>
   </td>
   </tr>
   </table>
</form>
</div>
<br/><br/>
	<div style="margin-left:3%" class="monthlyPlan_border">
<!-- <div style="float:left;padding-right:25px;width:278px;" id="treelayer">-->
			<div style="float:left;padding-right:2%;width:30%;background-color:#fff;" id="treelayer">
<!--			<div style="width:300px;padding-bottom:2px;height:400px;" class="sub-cntborder">-->

				<div style="width:106%;height:100%;overflow:auto;" class="sub-cntborder">				
					<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
					<div id="mpflTree" class="demo" style="width: 50%;"></div>			
				</div>
			
<!--			<div id="layoutfilter" style="width:300px;height:195px" class="sub-cntborder">-->

				<!-- Commented Temporarily  
				<div id="layoutfilter" style="width:106%;height:40%" class="sub-cntborder">
					<form id="layoutfil" name="layoutfil">
						<div class="sub-header"> Layout Filter and Find Option</div>
							<div class="easyui-paddingbfpx" style="margin-top:5%;padding-left:30%;"> 
									<input id="grpbyequipmnt" type="checkbox"/> <span style="padding-right:2px;"><label>Group By Equipment</label></span>
							</div>						
							 <div class="easyui-paddingbfpx" style="padding-left:20%">
				               		 <input type="button" id="findopt"  class="easyui-button" onclick="" value="Find Option"/>
				               		 <input type="button" id="showcal"  class="easyui-button" onclick="" value="Show Calendar"/>
		                	</div>
		             </form>
				</div>
			
--><!--			<div id="findoption" style="width:300px;display:none;height:195px;" class="sub-cntborder">-->
					<div id="findoption" style="width:106%;display:none;height:50%;" class="sub-cntborder">
					<form id="frmFindFLNode" name="frmFindFLNode">
					<div class="sub-header"> Find Option</div>
					<table border="0">
					<tr><td>
						<div  style="padding-left:2%; "><label>Find</label></div>
						<div class="easyui-paddingbfpx" style="padding-left:2%;">
							<select  id="findFL" class="easyui-combobox" name="findFL" style="width:120px;">
								<option value="cmp">Company</option>
								<option value="lcn">Location</option>
								<option value="unt">Unit</option>
								<option value="subunt">Section</option>
								<option value="sect">Line</option>
								<option value="eqp">Equipment</option>
								<option value="assm">Assembly</option>
<!--								<option value="subassm">Sub Assembly1</option>-->
<!--								<option value="subassm2">Sub Assembly2</option>-->
								<option value="spr">Spare</option>
<!--								<option value="ins">Instrument</option>-->
<!--								<option value="cel">Sub Cell</option>-->
							</select> 
						</div>
						<div style="padding-left:2%; "><label>Select</label></div>
						<div class="easyui-paddingbfpx" style="padding-left:2%;">
						 	<input id="findFLNode" name="findFLNode" class="easyui-combobox" style="width:200px;"/> 
						 </div> 
						 <div class="easyui-paddingbfpx" style="padding-left:2%;">
						 
						 	<input type="button" id="findnext"  class="easyui-button" onclick="" value="Find Next"/>
			               	<input type="button" id="btnFindFilterClear"  class="easyui-button" onclick="" value="Clear"/>
				            <input type="button" id="close"  class="easyui-button" onclick="" value="Close"/>
			             </div>
			      </td></tr>
		     </table>
		     <input type="hidden" id="flsFrmMonth" name="flsFrmMonth" value="" />
			<input type="hidden" id="flsToMonth" name="flsToMonth" value="" />		
			<input type="hidden" id="flsIsMonthWise" name="flsIsMonthWise" value="" />
			<input type="hidden" id="flsAssembly" name="flsAssembly" value="" />
		     </form>
		     </div>
			
			
<!--				<div class="sub-header"> Find Option</div>-->
<!--				<table border="0">-->
<!--				<tr>-->
<!--				<td>-->
<!--					<div  style="padding-left:2%; "><label>Find</label></div> -->
<!--					<div class="easyui-paddingbfpx" style="padding-left:2%;"> -->
<!--			     			<select id="find" class="easyui-combobox" name="find" style="width:130px;" required="true">-->
<!--								<option value="-"> Both</option>-->
<!--							</select> -->
<!--					</div>-->
<!--					<div style="padding-left:2%; "><label>Select</label></div> -->
<!--					<div class="easyui-paddingbfpx" style="padding-left:2%;"> -->
<!--							<input id="selectcmb" name="selectcmb" class="easyui-combobox"  style="width:235px;" value=""  >-->
<!--					</div>-->
<!--						-->
<!--					<div class="easyui-paddingbfpx" style="padding-left:2%;">-->
<!--		               		<input type="button" id="findnext"  class="easyui-button" onclick="" value="Find Next"/>-->
<!--		               		<input type="button" id="clear"  class="easyui-button" onclick="" value="Clear"/>-->
<!--		               		<input type="button" id="close"  class="easyui-button" onclick="" value="Close"/>-->
<!--	                </div>-->
<!--	            </td>-->
<!--	            </tr>-->
<!--	            </table>-->
<!--		   </div>-->
	  </div>
		
		<div style="width:111%;margin-top: -28px" id="monthlyplan">
				<div style="float:left" id="minicon">
					<img src="images/layout_button_left.gif" id="min" />
				</div>
				
				<div style="float:left;display:none;" id="maxicon">
<!--					<img src="images/layout_button_right.gif" id="max" />-->
				</div>
				
				<div>
				<table  style="margin-top:2px;">
					<tr>
						<td><span class="mp-activity"></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Activity Exists</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity" ><label style="color:#0000ff;margin-left:4px;"><b>A</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Allotted</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity"><label style="color:#0000ff;margin-left:4px;"><b>IC</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Incomplete</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity"><label style="color:#0000ff;margin-left:4px;"><b>&#10003</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Completed</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><span class="mp-activity"><label style="color:#0000ff;margin-left:0px;"><b>PC</b></label></span></td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Par.Completed</label></td>
						<td><span style="padding-left:2%;">
							<input id="sdm_click" type="button" class="easyui-button" value="ShutDownMaintenance"  /></span>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>
						<input id="chkAssemblyWise" name="chkAssemblyWise" type="checkbox" value="" />
						</td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Assembly Wise</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>
						<input id="chkEquipmentSelectWise" name="chkEquipmentSelectWise" type="checkbox" value="" />
						</td>
						<td><label style="color:dark brown;font-weight: bold;font-size:11px;">Equipment Wise</label></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td ><input style="padding-left:15px;" type="button" id="refBtn"  class="easyui-button" onclick="" value="Refresh Grid"/>
							</td>				
								<td>&nbsp;&nbsp;&nbsp;</td>
					     <td>
							<input type="button" id="selectBtn"  class="easyui-button" onclick="" value=" For Tab"/>
						</td>
					</tr>
				</table>
<!--					<span  style="padding-left:1%;"><img src="images/annualcal/annualplan1.png" id="show" /></span>-->
				</div>
<!--				 <div style="">-->
<!--				 	<div style="float:left;padding-left:3%;">-->
<!--				 		 <input type="button" id="wogen"  class="easyui-button" onclick="" value="Work Order Generation"/>-->
<!--				 	</div>-->
<!--				 	<div style="float:left;padding-left:10%">-->
<!--				 		<input type="button" id="view"  class="easyui-button" onclick="" value="View"/>-->
<!--               		 	<input type="button" id="filter"  class="easyui-button" onclick="" value="Filter"/>-->
<!--               		 	<input type="button" id="excel"  class="easyui-button" onclick="" value="Export To Excel"/>-->
<!--				 	</div>-->
<!--				 </div>-->
				  <div  id="gridDiv" style="float:right;padding-right:1%">
				  		<table id="MPgrid" style="width:100%"><tr><td/></tr></table>
						<div id="MPpager"></div>
				  </div>
				  <div class="clearfix"></div>
		</div>
	
</div>
</div>

<input type="hidden" id="hdnLocId" name="hdnLocId" value="" />
<input type="hidden" id="hdnMachId" name="hdnMachId" value="" />
<input type="hidden" id="hdnFctId" name="hdnFactId" value="" />
<input type="hidden" id="hdnSectId" name="hdnSectId" value="" />
<input type="hidden" id="hdnCellId" name="hdnCellId" value="" />
<input type="hidden" id="hdnAssmId" name="hdnAssmId" value="" />
<input type="hidden" id="hdnWeekNo" name="hdnWeekNo" value="" />
<input type="hidden" id="hdnMonth" name="hdnMonth" value="" />
<input type="hidden" id="hdnAcivity" name="hdnAcivity" value="" />
<input type="hidden" id="chkvwtrue" name="chkvwtrue" value="" />
<input type="hidden" id="hdnAssEqpWise" name="hdnAssEqpWise" value="${requestScope.asseqpWise}" />
<input type="hidden" id="hdnmchId" name="hdnmchId" value="${requestScope.machineId}"/>
<input type="hidden" id="hdncellId" name="hdncellId" value="${requestScope.cellId}"/>
<input type="hidden" id="hdnsectionId" name="hdnsectionId" value="${requestScope.sectionId}"/>
<input type="hidden" id="hdnFactId" name="hdnFactId" value="${requestScope.factId}"/>

<input type="hidden" id="hdnrelatedTo" name="hdnrelatedTo" value="${requestScope.relatedTo}"/>
<input type="hidden" id="hdnfromMonth" name="hdnfromMonth" value="${requestScope.fromMonth}"/>
<input type="hidden" id="hdntoMonth" name="hdntoMonth" value="${requestScope.toMonth}"/>
<input type="hidden" id="hdnfilter" name="hdnfilter" value="${requestScope.filterString}"/>
<input type="hidden" id="hdnfilterString" name="hdnfilterString" value=""/>
<input type="hidden" id="hdncmbTradeId" name="hdncmbTradeId" value="${requestScope.hdncmbTradeId}"/>
<input type="hidden" id="hdncmbJobType" name="hdncmbJobType" value="${requestScope.hdncmbJobType}"/>
<input type="hidden" id="hdnViewBoolean" name="hdnViewBoolean" value=""/>

