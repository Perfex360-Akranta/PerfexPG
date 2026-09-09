<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

		    initialiseForm('frmOplCreation');		
		    formatDateBox('dteJhkzDate','dd-MMM-yyyy');
			formatDateBox('dteJhkzImplementeddate','dd-MMM-yyyy');
			fillComboBox("frmKaizenCreation","cmbJhkzImplementedby","employee.commonFilter" );
			fillComboBox("frmKaizenCreation","cmbJhkzCategoryid","kaizenCategory.commonFilter");	
			fillComboBox("frmKaizenCreation","cmbJhkzKeyid","kaizenNo.commonFilter");	

			jQuery('#submitForm').val('frmKaizenCreation');


			var factId = jQuery("#frmKaizenCreation input[id='factory']").val();
			var sectionId = jQuery("#frmKaizenCreation input[id='section']").val();
			var cellId = jQuery("#frmKaizenCreation input[id='cell']").val();
			var machId = jQuery("#frmKaizenCreation input[id='machine']").val();
			
			var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
			loadFunctionalLocation("jhkzfunLocation","functionalLoc.jhKaizenForm","oplmfunLocationValues","frmKaizenCreation",dataStr);


			imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgOplmAfterimage","imgOplAfterImgFilename");
			 imageUpload(jQuery( "#dlgImgPresent" ),'ImageUpload.commonFilter','dlgImgPresent',"imgOplmPresentimage","imgOplPresentImgFilename");

			 var mode = jQuery("#hdnMode").val();
			 
				 
				 
			 if(mode == "View")
				disableForm("frmKaizenCreation");

			/*var user = jQuery("#hdnUser").val();
		
			setFieldValue('cmbJhkzImplementedby', user,'frmKaizenCreation');
			
			 fillWithCurrentDate('dteJhkzDate');
			 fillWithCurrentDate('dteJhkzImplementeddate');*/
				
		/*  jQuery("#pillar_checkbox").attr({'disabled':true});	
			viewGrid("Pillar_input.opl","","pillar");
			viewGrid("oplStudent_input.opl","","student");
				
			jQuery('#submitForm').val('frmOplCreation'); // set the id of form to submit
			//fillComboBox("frmOplCreation","cmboplmKeyid","combo_documentNo.opl" );
			var compId = getFieldValue('company','frmOplCreation');
			var locnId = getFieldValue('location','frmOplCreation');
			var factId = getFieldValue('factory','frmOplCreation');
			var sectId = getFieldValue('section','frmOplCreation');
			var cellId = getFieldValue('cell','frmOplCreation');
			var machId = getFieldValue('machine','frmOplCreation');
			
			//fillComboBox("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);
			 
			 	
	
			// for functionalLocation 
			var factId = jQuery("#frmOplCreation input[id='factory']").val();
			var sectionId = jQuery("#frmOplCreation input[id='section']").val();
			var cellId = jQuery("#frmOplCreation input[id='cell']").val();
			var machId = jQuery("#frmOplCreation input[id='machine']").val();
			
			var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
			//loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation",dataStr);
			
			
				
			jQuery('#frmOplCreation .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmOplCreation textarea').css('text-transform', 'uppercase');
			jQuery(".txtarea").attr('maxlength','499');
			jQuery(".easyui-text").attr('maxlength','36');
			formatDateBox('dteoplmDate','dd-MMM-yyyy');
			formatDateBox('dteoplmPrepareddate','dd-MMM-yyyy');
			formatDateBox('dteoplmApproveddate','dd-MMM-yyyy');
		
			var oplmDate = getFieldValue("dteoplmDate");
			var oplmApprvDate = getFieldValue("dteoplmApproveddate");
			var oplmPrepareDate = getFieldValue("dteoplmPrepareddate");

			
			if( oplmDate == null || oplmDate.length <= 0)
			{
				fillWithCurrentDate("dteoplmDate");
			}
			if( oplmApprvDate == null || oplmApprvDate.length <= 0)
			{
				fillWithCurrentDate("dteoplmApproveddate");
			}	
			if( oplmPrepareDate == null || oplmPrepareDate.length <= 1)
			{
				fillWithCurrentDate("dteoplmPrepareddate");
			}
			
			if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val().trim() !='')
			{		  
			   jQuery("#cmboplmRelated").combobox('setValue',jQuery("#relatedToCMB").val());
			   fillComboBox("frmOplCreation","cmboplmMouldid","mould.commonFilter");
				   		  
			}

			if(jQuery('#cmboplmRelated').combobox('getValue')=="MLD")
			   jQuery('#cmboplmMouldid').combobox('enable');
		   else 
			   jQuery('#cmboplmMouldid').combobox('disable');
			jQuery(".tabs-title").bind("click", function(event){
				
				if(jQuery("#studGrid").getGridParam('reccount') <= 0 && jQuery(this).text() == "Student"  ){
					jQuery("#studGrid").setGridParam({url:'oplStudent_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
				}
				else if( jQuery("#pillarGrid").getGridParam('reccount') <= 0 && jQuery(this).text() == "Lesson" ){
					alert(jQuery("#pillarGrid").getGridParam('reccount') <= 0);
					alert(jQuery(this).text());
					jQuery("#pillarGrid").setGridParam({url:'Pillar_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
				}*/
			});


			
			function frmKaizenCreationcmbJhkzKeyid_onLoadSuccess(){
				
				disableField('frmKaizenCreation', 'cmbJhkzKeyid');
				}
			/*var oplId = jQuery('#cmboplmKeyid').combobox('getValue');*/
			jQuery("#tabOpl").tabs({ onSelect:function(title){ 
				if(jQuery("#pillarGrid").getGridParam('reccount') <= 0 && title=="Lesson")
				{
					jQuery("#pillarGrid").setGridParam({url:'Pillar_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
				}
				else if(title=="Student")
				{
					jQuery("#studGrid").setGridParam({url:'oplStudent_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
					
				}
			}
			});
			
			jQuery("#imgOplmPresentimage").load(function() {
		 		if((jQuery(this).width()>415)||(jQuery(this).height()>264))
				{	jQuery('#imgOplmPresentimage').attr('src', "");
					alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
					return false;
				}
		        
		    });
					
			jQuery("#imgOplmAfterimage").load(function() {
				if((jQuery(this).width()>415)||(jQuery(this).height()>264))
				{	jQuery('#imgOplmAfterimage').attr('src', "");
					alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
					return false;
				}
		    });
			
			/*	  if(jQuery('#frmMode').val()=="View")
				  changeFormInputBackGround('frmOplCreation');
			 
				if(jQuery('#hdnbdmode').val()=="bdmmode")
					jQuery('#cmboplmMachineid').combobox('disable');

				
				

				else
				{	
					jQuery("#chkoplmIsok").attr("disabled","disabled");
				}
			   
		});

function frmOplCreation_beforeSubmit(){

	return "OplPillarLink="+JqGridToJsonSelectdRows('pillarGrid','pillar_checkbox','txtselectionFlag');
} 		
				
function viewGrid(url,filterString,category)
{
	if(category=="student")
	{
		processGridnew(url,filterString,"studGrid","studPager");
	}
	else if(category=="pillar")
	{
		var tableCaption = "";
			processGridnew(url,filterString,"pillarGrid","pillarPager",tableCaption,"","","loadCompletePillarGrid","selectPillarGridRow");
			
	}
}
	
				
function onerrorCallThis()
{
	alert("error occured");
}
			
function pillarcboxFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	
	return '<input name="pillar_checkbox" id="pillar_checkbox_'+ rowId +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + ' onclick="if(this.checked){pillarLinkSelect(\''+rowId + '\');}else{pillarLinkUncheck(\''+ rowId +'\')}"/>';
}

function pillarLinkSelect(id)
{
	jQuery('#hdnpillarId').val(id);
	var pillarId = jQuery("#pillarGrid").getCell(id,"txtOpplTpmpillarid");
		
	multiSelectPop("imprvCategory_input.opl","pillarId%3D"+pillarId, "pillarGrid",id,"txtOpplOplcategoryid,oplCategory",false,"MultiSelectCancel_CallBack","oplmultiSelectOk_Callback","OPL Category");
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if( mode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
	else if(mode=="VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","UPDATE");
	
}

function pillarLinkUncheck(id)
{
	jQuery('#hdnpillarId').val('');
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if(mode != "VIEW")
		mode =" ";
	else
		mode = "DELETE";

	jQuery("#pillarGrid").setCell(id,"oplCategory"," ");
	jQuery("#pillarGrid").setCell(id,"txtOpplOplcategoryid"," ");
	jQuery("#pillarGrid").setCell(id,"txtselectionFlag",mode);
	
}

function MultiSelectCancel_CallBack(id)
{
	if(jQuery("#pillarGrid").getCell(id,"txtDbMode") == "VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","DELETE");
	else 
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");

	jQuery("#pillarGrid").setCell(id,"pillar_checkbox","False");
	
}

function oplmultiSelectOk_Callback(id)
{
	
	id=jQuery('#hdnpillarId').val();
	if(jQuery("#pillarGrid").getCell(id,"txtDbMode") == "VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
	else 
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	jQuery("#pillarGrid").setCell(id,"pillar_checkbox","False");
}

function pillarGrid_multiselectPopUpId_onClose()
{
	var id=jQuery('#hdnpillarId').val();
	jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	jQuery("#pillarGrid").setCell(id,"pillar_checkbox","False");
}


function selectPillarGridRow(id)
{
	}
					
function loadCompletePillarGrid()
{
	var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
	for(var i=0;i<rowIds.length;i++)
	{
		var cellVal =jQuery('#pillarGrid').getCell(rowIds[i],"oplCategory");
		if(cellVal!=" ")
			jQuery("#pillar_checkbox_"+rowIds[i]).attr('checked','checked');
		
		if(jQuery('#frmMode').val()=="View")
		{
			jQuery("#pillar_checkbox_"+rowIds[i]).attr('disabled','disabled');
		}
	}
	
}




jQuery('#btnExcelview').click( function()
{
	var oplId=jQuery("#cmboplmKeyid").val();
	window.open("OplReport_Excelview.oplrpt?oplId="+oplId,"Excel View");
});	
				

function  frmOplCreationcmboplmFactoryid_onSelect(record)
{
	jQuery("#cmboplmSectionid").combobox('clear');
	jQuery("#cmboplmCellid").combobox('clear');
	jQuery("#cmboplmMachineid").combobox('clear');
	reloadCombo("frmOplCreation","cmboplmSectionid","sectionCombo.commonFilter?factId="+record.id);
	reloadCombo("frmOplCreation","cmboplmCellid","cellCombo.commonFilter?factId="+record.id  );
	reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?factId="+ record.id );
}

function  frmOplCreationcmboplmSectionid_onSelect(record)
{
	jQuery("#cmboplmCellid").combobox('clear');
	jQuery("#cmboplmMachineid").combobox('clear');
	reloadCombo("frmOplCreation","cmboplmCellid","cellCombo.commonFilter?sectId="+record.id  );
	reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?sectId="+ record.id );
	//fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmboplmFactoryid");
}

function  frmOplCreationcmboplmCellid_onSelect(record)
{
	jQuery("#cmboplmMachineid").combobox('clear');
	reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?cellId="+ record.id );
	//fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmboplmSectionid","cmboplmFactoryid");
}

function  frmOplCreationcmboplmMachineid_onSelect(record)
{
	loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","&machId="+record.id);
	//jQuery("#cmboplmSectionid").combobox('clear');
	//jQuery("#cmboplmCellid").combobox('clear');
	//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmboplmCellid","cmboplmSectionid","cmboplmFactoryid");
 	reloadCombo("frmOplCreation","cmboplmMouldid","mould.commonFilter?q=2&mchId="+record.id );
}
function frmOplCreationcmboplmMachineid_onClear()
{
	loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","&cellId="+jQuery("#frmOplCreation input[id='cell']").val());
	jQuery("#frmOplCreation input[id='machine']").val('');
	 setTimeout(function() {jQuery('#cmboplmMachineid').combobox('clear');},1250);
}

function frmOplCreationcmboplmMouldid_onSelect(record)
{
	reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?mouldId="+ record.id ); 
}

function frmOplCreationcmboplmFactoryid_onLoadSuccess(){
	fillComboBox("frmOplCreation","cmboplmSectionid","sectionCombo.commonFilter" );
}

function frmOplCreationcmboplmSectionid_onLoadSuccess(){
	fillComboBox("frmOplCreation","cmboplmCellid","cellCombo.commonFilter" );
}

function frmOplCreationcmboplmCellid_onLoadSuccess(){
	fillComboBox("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter" );
}

function frmOplCreationcmboplmMachineid_onLoadSuccess(){
	fillComboBox("frmOplCreation","cmboplmPreparedid","employee.commonFilter");
}
function frmOplCreationcmboplmPreparedid_onLoadSuccess(){
	fillComboBox("frmOplCreation","cmboplmApprovedid","employee.commonFilter");
}
function frmOplCreationcmboplmApprovedid_onLoadSuccess(){
	fillComboBox("frmOplCreation","cmboplmTradeid","Combo_Trade.abnForm");
}
function frmOplCreation_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	if(keyIds.machId.trim() != "" && keyIds.machId.trim() !="null")
	{	
		setFieldValue('cmboplmMachineid',keyIds.machId);
		reloadMachine("frmOplCreation",'cmboplmMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	}
}

jQuery("#cmboplmRelated").combobox({
	onSelect:function(recordid){
		jQuery('#relatedToCMB').val(recordid.id);			
		if(recordid.id == 'MLD')
		{		
			jQuery("#lblMld").addClass('mandatory-lbl');	
			jQuery('#lblMachine').removeClass('mandatory-lbl');
			enableFields('cmboplmMouldid');
			var mchId = jQuery("#cmboplmMachineid").combobox('getValue');
			 fillComboBox("frmOplCreation","cmboplmMouldid","mould.commonFilter");	
			if(mchId != null && mchId != ' ' && mchId !='')
			{
				jQuery('#cmboplmMouldid').combobox('clear');
				reloadCombo("frmOplCreation","cmboplmMouldid","mould.commonFilter?q=2&mchId="+mchId );
			}
			
		}
		if(recordid.id == 'MCH')
		{				
			jQuery("#lblMld").removeClass('mandatory-lbl');
			jQuery('#lblMachine').addClass('mandatory-lbl');
			jQuery("#cmboplmMouldid").combobox('clear');
			readOnlyFields('cmboplmMouldid'); 		
		}
			
	}

});    

function frmOplCreationcmbcmboplmMouldid_onClear()
{
	
	jQuery('#cmboplmRelated').combobox('setValue','MLD');
}

//Success CallBack 
 function frmOplCreation_successsCallback(result)
 {
	var mode = result.formMode;
	
	if(result.successData.successData == "bdmmode")
	{
		popFormNavigation();
		popFormNavigation();
	}
	else
	{
		if(result.mode.frmMode=="modify"&&mode != "category")
		{
		  //navigateToPrevForm("modify_view.opl","Modification");
		}
		else if( mode != null && mode == "category" )
		{
		  var persistentData = result.persistentData;
		  var forwardData = result.forwardData;
		// alert(forwardData.oplKeyid);
		 navigateToNextForm("oplCategory_input.oplcat","OPL Category",forwardData,persistentData);	
		}
		else if(result.mode.frmMode!= null && result.mode.frmMode!= "View")
			{
		 	frmOplClear();}
	}		
	
 }

 function frmOplCreation_errorCallback(result)
 {
	  alert(result.errMsg.msg);
 }

 function frmOplClear()
 {
	 jQuery('#imgOplmAfterimage').attr('src', "");
	 jQuery('#imgOplAfterImgFilename').val("");
	 jQuery('#imgOplmPresentimage').attr('src', "");
	 jQuery('#imgOplPresentImgFilename').val("");
	 fillWithCurrentDate("dteoplmDate");
	 fillWithCurrentDate("dteoplmPrepareddate");
	 fillWithCurrentDate("dteoplmApproveddate");
	 jQuery('#cmboplmRelated').combobox('setValue','MCH');
	 jQuery('#cmboplmMouldid').combobox('disable');
	 
	 loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","");			
		
	 var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"oplCategory"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectionFlag"," ");
		} 
 }

	jQuery('#btnOplCatgry').click(function ()
	{
		 saveForm('frmOplCreation','opl_category.opl');
	});
	
	jQuery('#chkUndrstndOpl').click( function () 
	{
		if(jQuery('#hdnChkUnderstoodOpl').val()== 'true')
		{
			alert('Record Already Exists');
			return false;
		}
		else
		{
			 var val = "true";
			 var opllessnDate=jQuery('#dteoplmDate').val();
			 var oplOplid=jQuery('#cmboplmKeyid').val();
			 var para="isOplCheck=" + val + "&txtOpllOplid=" + oplOplid + "&txtOpllDate=" + opllessnDate;
			 processAjaxCalls("excel_input.opl?",para);
		}
	});

function frmOplCreation_exceptionCallback()
{
	if(jQuery('#txtoplmLesson').val()=="")
		alert('Select Lesson');
}

function frmOplCreation_deleteSuccessCallback(result)
{
	if(result.successData.frmMode!="View")
		navigateToPrevForm("modify_view.opl","Modification");	
}

function frmOplCreation_deleteErrorCallback(result)
{
	alert(result.errMsg.msg);
}
function frmOplCreation_beforeDelete()
{
	 var delMsg = "Do You Want To Delete This OPL ("+jQuery('#cmboplmKeyid').combobox('getValue')+") ?";
		if(confirm(delMsg) == false)
		{
				return false;
		}
}
*/
function dlgImgPresentOnComplete(response){
}

jQuery( "#btnImgPresentClear" ).click(function() {
jQuery('#imgOplmPresentimage').attr('src', "");
jQuery('#imgOplPresentImgFilename').val("");

});

jQuery( "#btnImgAfterClear" ).click(function() {
jQuery('#imgOplmAfterimage').attr('src', "");
jQuery('#imgOplAfterImgFilename').val("");
});




	
</script> 
<!--end of grid-->
<form name="frmKaizenCreation" id="frmKaizenCreation" action="create_input.jhKaizenForm" method="post">
	<div id="wrapper">
	<div class="main-cntborder" style="width:1200px;height: 860px;">
	<table id="KaizenCreation" rules="none" border="0">
	<tr>
	<td colspan="3">
			<div  id="frmKaizenCreationFuntKeyIds"  >
			<input type="hidden" id="factory" name="cmbJhkzFactoryid" value="${requestScope.JhkTlKaizenmst.jhkzFactoryid}"  ></input>
			<input type="hidden" id="section" name="cmbJhkzSectionid" value="${requestScope.JhkTlKaizenmst.jhkzSectionid}"  ></input>
			<input type="hidden" id="cell" name="cmbJhkzCellid" value="${requestScope.JhkTlKaizenmst.jhkzCellid}"  ></input>
			<input type="hidden" id="machine" name="cmbJhkzMachineid" value="${requestScope.JhkTlKaizenmst.jhkzMachineid}"  ></input>
			</div>
			<div  class="easyui-paddingbfpx" style="padding-left: 20px;"><label>JH Kaizen No</label></div>
			<div style="padding-left: 20px;">
				<input id="cmbJhkzKeyid" name="cmbJhkzKeyid" class="easyui-combobox"  style="width:355px;" value="${requestScope.JhkTlKaizenmst.jhkzKeyid}" />
			</div>
			
			<div id="jhkzfunLocation" style="padding-left: 20px;width:60%;"></div>
			<div id="err_cell" class="tpm-errormsg" style="display: block;padding-left: 20px;"></div>
			
			
</td>
</tr>
	
	
		<tr>
<!--left  pane -->
			<td style="width:30%" valign='top'>
				<div  style="padding-left:20px;">
							<div class="sub-header " style="margin-bottom:10px;" ><label>Kaizen</label></div>
					
							<div   style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Date</label></span></div> 
		                  	<div style="padding-bottom: 15px;padding-left:px;"> 
	                			 <input id="dteJhkzDate" name="dteJhkzDate" class="easyui-datebox" style="width:160px;" value="${requestScope.JhkTlKaizenmst.jhkzDate}"  /> 
							</div>
							
							<div class="sub-header" style="  height : 17px;margin-bottom:10px;"> <label>Details</label></div>
							
				
				 
				 <div  style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Kaizen Description</label></span></div> 
                 	 <div style="padding-bottom: 3px;padding-right:10px;"> 
                		<textarea class="txtarea" id="txtJhkzDescription" name="txtJhkzDescription" rows="5" cols="6" style="width:355px;resize:none; height : 68px;" >${requestScope.JhkTlKaizenmst.jhkzDescription}</textarea>
					</div>
					
					<div style="padding-bottom: 5px; "><span><label id="lblMachine" class="mandatory-lbl"> Implemented By</label></span></div> 
					<div style="padding-bottom: 8px;padding-right:10px;"> 
                		<input id="cmbJhkzImplementedby" name="cmbJhkzImplementedby" class="easyui-combobox"  style="width:355px;" value="${requestScope.JhkTlKaizenmst.jhkzImplementedby}" />
					</div>
							
					<div   style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Implementation Date</label></span></div> 
	                  	<div style="padding-bottom: 15px;padding-left:px;"> 
                		<input id="dteJhkzImplementeddate" name="dteJhkzImplementeddate" class="easyui-datebox" style="width:160px;" value="${requestScope.JhkTlKaizenmst.jhkzImplementeddate}"/> 
					</div>
					
					
					
						 <div style="padding-bottom: 5px; ">
						 	<span> <label > Kaizen Catagory</label></span>
						 	
						 </div> 
							<div style="padding-bottom: 8px;padding-right:10px;"> 
								<input id="cmbJhkzCategoryid" name="cmbJhkzCategoryid" class="easyui-combobox"  style="width:355px;" value="${requestScope.JhkTlKaizenmst.jhkzCategoryid}"/>
		                		
							</div>	
							  <div id="err_cmboplmMouldid" class="tpm-errormsg" style="display: block;padding-left: 110px;margin-top: 5px;"></div>
							  
							
						<div   style="padding-bottom: 5px; "><span><label >Problem</label></span></div> 
	                  	<div style="padding-bottom: 15px;padding-left:px;"> 
                			<textarea class="txtarea" id="txtJhkzProblemdescription" name="txtJhkzProblemdescription" rows="5" cols="6" style="width:355px;resize:none; height : 68px;" >${requestScope.JhkTlKaizenmst.jhkzProblemdescription}</textarea> 
						</div>
					
				
				
			</td>
<!--Right  pane -->
			
			<td class="valigncnt" style="width:50%;">
	
				<div id="OplTab" style="padding-left:0px;width : 652px; height : 750px;">
					<div id="tabOpl" class="easyui-tabs" fit="true" plain="true" style="width:200px; height :auto;">
						<div id="presentTab" title="Present Condition" style="padding:10px;">
							<div class="sub-header" style=" width : 100%; margin-bottom:10px;" ><label id="prstDescsubHeader"> Present Description </label><span style="float:right;">
							<!--<input type="button" class="button" value="Export to Excel" id="exprtexcel" />-->
							</span></div>
							
		                 	 <div style="padding-bottom: 15px;padding-right:10px;"> 
		                		<textarea class="txtarea"  id="txtJhkzBeforedescription" name="txtJhkzBeforedescription" rows="5" cols="6" style="width : 614px; height : 130px;resize:none;" >${requestScope.JhkTlKaizenmst.jhkzBeforedescription}</textarea>
							</div>
						<div style="">
							<div class="sub-header " style=" margin-bottom:0px;" ><label id="prstCondsubHeader"> Present Condition  </label><span style="padding-left:35px;font-size:8px;">width:11.01 cm height:7.01 cm</span>
								<span style="float:right;height:20px;margin-top:-16px\9;">
								
									<input class="easyui-button" type="button" value ="Image" id="dlgImgPresent" name="dlgImgPresent"   style="height:20px;" onclick="presentImage();"/>
									<span style="padding-left:10px;">
										<input type="button" class="easyui-button" value="Clear" id="btnImgPresentClear" name="btnImgPresentClear" style="height:20px"  />
									</span>
								</span>
							</div>
						</div>	
							<div id="divImgPresent" style="padding-top:20px;">
								<img alt="" id="imgOplmPresentimage" name="imgOplmPresentimage" src="${requestScope.KZNBeforeImage}" >
							</div>
							
						</div>
						
						<div id="afterTab" title="After Condition" style="padding:10px;">
						<div class="sub-header " style=" width : 100%;height:14px\9; " ><label id="aftDescsubHeader">After Description</label></div>
						
	                 	 <div style="padding-bottom: 15px;padding-right:10px;"> 
	                		<textarea id="txtJhkzAfterdescription" name="txtJhkzAfterdescription" class="txtarea" rows="5" cols="6" style="width : 614px; height : 130px;resize:none;"  >${requestScope.JhkTlKaizenmst.jhkzAfterdescription}</textarea>
						</div>
						<div class="sub-header " style=" margin-bottom:0px;" ><label id="aftCondsubHeader"> After Condition </label> <span style="padding-left:35px;font-size:8px;">width:11.01 cm height:7.01 cm</span>
						  <span style="float:right;margin-top:-16px\9;height:20px">
							<input type="button" class="easyui-button" style="height:20px" value="Image" id="dlgImgAfter" name="dlgImgAfter"  />
							<span style="padding-left:10px;">
								<input type="button" class="easyui-button" value="Clear" style="height:20px" id="btnImgAfterClear" name="btnImgAfterClear"  />
							</span>
						  </span>
						</div>
						
						<div style="padding-top:20px;">
						<img alt="" id="imgOplmAfterimage" name="imgOplmAfterimage"   src="${requestScope.KZNAfterImage}" /></div>
						</div>
						
						<div title="Result" style="padding:10px;height:100%;">
							
							<div   style="padding-bottom: 5px;padding-left:10px; ">
								<span><label >Counter Measure</label></span>
							</div> 
								<div style="padding-left:10px;padding-bottom:15px;">									
					            <textarea id="txtJhkzCountermeasure" name="txtJhkzCountermeasure" class="txtarea" rows="5" cols="6" style="width :590px; height : 70px;resize:none"  >${requestScope.JhkTlKaizenmst.jhkzCountermeasure}</textarea>
								</div>
								
								<div   style="padding-bottom: 5px; padding-left:10px;">
								<span><label >Improved</label></span>
								</div> 
								<div style="padding-left:10px;padding-bottom:15px;">									
					            <textarea id="txtJhkzImprovement" name="txtJhkzImprovement" class="txtarea" rows="5" cols="6" style="width :590px; height : 70px;resize:none"  >${requestScope.JhkTlKaizenmst.jhkzImprovement}</textarea>
								</div>
								
								<div   style="padding-bottom: 5px;padding-left:10px; ">
								<span><label >Result</label></span>
								</div> 
								<div style="padding-left:10px;padding-bottom:15px;">									
					            <textarea id="txtJhkzResults" name="txtJhkzResults" class="txtarea" rows="5" cols="6" style="width :590px; height :70px;resize:none"  >${requestScope.JhkTlKaizenmst.jhkzResults}</textarea>
								</div>
								
								<div   style="padding-bottom: 5px;padding-left:10px; ">
								<span><label >Benifits</label></span>
								</div>
								<div style="padding-left:10px;padding-bottom:15px;">									
					            <textarea id="txtJhkzBenefits" name="txtJhkzBenefits" class="txtarea" rows="5" cols="6" style="width :590px; height : 70px;resize:none"  >${requestScope.JhkTlKaizenmst.jhkzBenefits}</textarea>
								</div>
								
								<div   style="padding-bottom: 5px; padding-left:10px;">
								<span><label >Remarks</label></span>
								</div>
								<div style="padding-left:10px;padding-bottom:15px;">									
					            <textarea id="txtJhkzRemarks" name="txtJhkzRemarks" class="txtarea" rows="5" cols="6" style="width :590px; height : 70px;resize:none"  >${requestScope.JhkTlKaizenmst.jhkzRemarks}</textarea>
								</div>
	
      
</div>

<input type="hidden" id="txtSelectRowid" value="" >
<input type="hidden" id="frmMode" value=""/>
<input type="hidden" id="imgOplPresentImgFilename" name="imgOplPresentImgFilename" value="" />
<input type="hidden" id="imgOplAfterImgFilename" name="imgOplAfterImgFilename" value="" />
<input type="hidden" id="hdnChkUnderstoodOpl" value="" />
<input type="hidden" id="mode" name="mode" value=""/>
<input type="hidden" id="hdnbdmode" value="}" />
<input type="hidden" id="hdnpillarId"/>
</div>
</div>


</td>
</tr>
</table>
</div>
</div>
<input type="hidden" id="mode" value="view"/>
<input type="hidden" id="hdnImage" value="requestScope.GenTlAllmoduleimgfilePre"/>
<input type="hidden" id="hdnMode" value="${requestScope.mode}" /> 
<input type="hidden" id="hdnUser" value="${requestScope.user}" />
</form>
