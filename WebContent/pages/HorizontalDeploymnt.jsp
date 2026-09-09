<!-- Created By Siddharth .A -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
jQuery(document).ready(function(){	

	jQuery('#submitForm').val('frmHzDplymnt'); // set the id of form to submit
	initialiseForm('frmHzDplymnt');		

	processGridnew("hrzdplymnt_input.hrzdply","?row=2","HorizDeployGrid","HorizDeployPager");					
	formatDateBox('dtekhdmStartDate','dd-MMM-yyyy');
	formatDateBox('dtekhdmEndDate','dd-MMM-yyyy');
	fillComboBox("frmHzDplymnt","cmbkhdmCellid","cellCombo.commonFilter" );
	fillComboBox("frmHzDplymnt", "cmbkhdmDmtid", "sectionCombo.commonFilter");
	jQuery( "#HdResponsibilityPopup" ).hide();
	
	enableFields("cmbkhdmDmtid");

	jQuery('#dtekhdmTargetdate').datebox({  	   
	   	onSelect:function(recordid)
			{
	   		    //completedDateEvent();
			} 
	   });	


	
	var factId = jQuery("#frmHzDplymnt input[id='factory']").val();
    var sectionId = jQuery("#frmHzDplymnt input[id='section']").val();
    var cellId = jQuery("#frmHzDplymnt input[id='location']").val();
    var machId = jQuery("#frmHzDplymnt input[id='machine']").val();
    var flid = jQuery("#frmHzDplymnt input[id='flid']").val(); 
    //alert(cellId+" cellId :: "+sectionId);

    var dataStr = "&factId=" + factId
					+ "&sectionId=" + sectionId
					+ "&cellId=" + cellId + "&machId="
					+ machId+"&flid="+ flid+"&disable=N";
	loadFunctionalLocation("HDfunLocation", "functionalLoc.hrzdply", "HDfunLocation", "frmHzDplymnt",dataStr);


	
	//btnview

	jQuery("#btnview").click(function()
   	{ 
		//alert(12);
		var dmtid=getFieldValue("cmbkhdmDmtid");
		//alert(" dmtid :: "+dmtid);
		//'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate
		processGridnew("hrzdplymnt_input.hrzdply","?q=2&dmtid="+dmtid+'&dmtlevel=dmtlevel',"HorizDeployGrid","HorizDeployPager","","","","loadCompleteGrid");
		
	});
	

	
	});	
	
	
	function loadCompleteGrid(id){//alert(" Load Complete ");
		//jQuery("#HorizDeployGrid").trigger("reloadGrid");
		//HorizDeployGrid
		//alert(" Load Complete :: ");
		checkdisabled();
	}
	
function frmHzDplymnt_FuntLocHierarchy_SuccessCallBack(result)
{   
    
	//setFunctionalLocWidth('frmHzDplymnt','600px');
	/*var pbuId = jQuery("#frmHzDplymnt input[id='pbu']").val();
    var cellId = jQuery("#frmHzDplymnt input[id='location']").val();
	var flid =jQuery("#frmHzDplymnt input[id='flid']").val();
	//alert(" flid :: "+flid);
	processGridnew("hrzdplymnt_input.hrzdply","?q=2&dmtid="+flid+'&dmtlevel=dmtlevel',"HorizDeployGrid","HorizDeployPager","","","","loadCompleteGrid");*/

	fillgrid();
	checkdisabled();
}

function fillgrid(){

	var pbuId = jQuery("#frmHzDplymnt input[id='pbu']").val();
    var cellId = jQuery("#frmHzDplymnt input[id='location']").val();
	var flid =jQuery("#frmHzDplymnt input[id='flid']").val();
	//alert(" flid :: "+flid);
	processGridnew("hrzdplymnt_input.hrzdply","?q=2&dmtid="+flid+'&dmtlevel=dmtlevel',"HorizDeployGrid","HorizDeployPager","","","","loadCompleteGrid");
}
function frmHzDplymnt_deleteSuccessCallback(result)
{
	  alert(" Data Deleted Successfully ");
	  navigateToPrevForm();
	  fillgrid();
	  //checkdisabled();
}
function frmHzDplymnt_beforeSubmit(){
	

	var checked = jQuery("#HorizDeployGrid input:checked").length > 0;
    if (!checked){
        alert("Select HD For Saving");
        return false;
    }
	
	var gridData  = '&HorizDeployGridData='+JqGridToJsonSelectdRows('HorizDeployGrid','hd_checkbox','txtselectionFlag').replace('&',',');
	//alert("grid data"+gridData);
	return gridData; 
} 		
function frmHzDplymnt_successsCallback(result)
{
	if(result!=null)
		navigateToPrevForm();
}

function frmHzDplymnt_errorCallback(result)
{
	  alert(result.errMsg.msg);
}

function frmHzDplymnt_beforeDelete(){

	/*var row = jQuery("#HorizDeployGrid").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++){
    var status=jQuery("#HorizDeployGrid").getCell(row,"status");
    if(status=="Completed"){alert(" completed ");
        alert(" Kaizen implemented for this JH ");
        return false;
      }
	}*/
	
	var checked = jQuery("#HorizDeployGrid input:checked").length > 0;
    if (!checked){
        alert("Select HD For Deleting");
        return false;
    }
    
	var gridData  = '&HorizDeployGridData='+JqGridToJsonSelectdRows('HorizDeployGrid','hd_checkbox','txtselectionFlag');
	//alert("grid data"+gridData);
	return gridData; 
} 		

/*
function  frmHzDplymntcmbkhdmDmtid_onSelect(record)
{
	alert(1);
	
	//cmbkhdmDmtid , formId);
	var dmtid=getFieldValue("cmbkhdmDmtid");
	//alert(" dmtid :: "+dmtid);
	//'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate
	processGridnew("hrzdplymnt_input.hrzdply","?q=2&dmtid="+dmtid+'&dmtlevel=dmtlevel',"HorizDeployGrid","HorizDeployPager");
	
	
}
*/



function  frmHzDplymntdtekhdmTargetdate_onSelect(record)
{
	completedDateEvent();	
}

/*
function dtekhdmTargetdate_onSelect(record) {

} */


function completedDateEvent(){

var currentDate = getServerDateTime();
var dtekhdmEndDate=jQuery('#dtekhdmEndDate').datebox("getValue");

var dtekhdmTargetdate=jQuery('#dtekhdmTargetdate').datebox("getValue");

if(convertStringToDate(dtekhdmEndDate )< dtekhdmTargetdate)
{
	alert('Target cannot be back date of kaizen End Date ');
	
}	
}

function frmHzDplymntcmbkhdmCellid_onSelect(record){
	//alert(12);
	var jhid=getFieldValue("cmbkhdmCellid");
	//alert(" jhid :: "+jhid);
	processAjaxCalls("hrzdplymntfnln_input.hrzdply", "jhid="+ jhid, 'remove_successCallBack','remove_errorCallBack');
	
	//fillComboBox("frmHzDplymnt","cmbkhdmMachineid","machineCombo.commonFilter" );
}


function frmHzDplymntcmbkhdmCellid_onLoadSuccess(){
	fillComboBox("frmHzDplymnt","cmbkhdmMachineid","machineCombo.commonFilter" );
}

function frmHzDplymntcmbkhdmMachineid_onLoadSuccess(){
	fillComboBox("frmHzDplymnt","cmbkznmAssemblyid","assembly.commonFilter" );
}

function frmHzDplymntcmbkznmAssemblyid_onLoadSuccess(){
	fillComboBox("frmHzDplymnt","cmbkznmPhenomenaid","phenomena.commonFilter");
}
function frmHzDplymntcmbkznmPhenomenaid_onLoadSuccess(){
	fillComboBox("frmHzDplymnt","cmbkznmCauseid","cause.commonFilter");
}


function hdScancboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
	//alert(" id :: "+id);
	var checked="";
	if(rowObject[0]=="1")
		checked="checked=checked";
	else
		checked="";
	return '<input id="hd_checkbox_'+ id +'"  type="checkbox" ' + ' onclick="if(this.checked){checkBoxClick(\''+id + '\')}else{checkBoxUnchecked(\''+id+'\')}" '+ checked  +'/>';
}

function checkBoxClick(id)
{
	
	jQuery('#hdnSelectedVal').val(id);

	/*var status=jQuery("#HorizDeployGrid").getCell(id,"status"); 
    if(status=="Completed"){
      alert(" Kaizen implemented for this JH ");
      return false;
    }*/
	
	loadResponsibility();
	var mode = jQuery("#HorizDeployGrid").getCell(id,"txtDbMode");
/*	if( mode == "INSERT")
		jQuery("#HorizDeployGrid").setCell(id,"txtselectionFlag","UPDATE");
	else 
*/
	jQuery("#HorizDeployGrid").setCell(id,"txtselectionFlag","INSERT");
	/*	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if( mode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
	else if(mode=="VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","UPDATE");
	*/
}

function checkBoxUnchecked(id)
{
	var mode = jQuery("#HorizDeployGrid").getCell(id,"txtDbMode");
	if( mode == "INSERT")
		jQuery("#HorizDeployGrid").setCell(id,"txtselectionFlag","DELETE");
	else	
		jQuery("#HorizDeployGrid").setCell(id,"txtselectionFlag"," ");
}
function loadResponsibility()
{
	jQuery("#cmbkhdmResponsibilityid").combobox('clear');
	fillComboBox("HdResponsibilityPopup","cmbkhdmResponsibilityid","employee.commonFilter");
	formatDateBox('dtekhdmTargetdate','dd-MMM-yyyy');
	fillWithCurrentDate('dtekhdmTargetdate');	
	
	//alert(" Target vdate "+getFieldValue('dtekhdmTargetdate'));
	
	jQuery( "#HdResponsibilityPopup" ).show();
	jQuery( "#HdResponsibilityPopup" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 245,
			width: 350,
			left:420,
			top:150,	
			modal: true,
			title:'Responsibility and Target for HD'
	});
}

//function kznResponsibilityOk_Callback(id)
jQuery('#btnOk').click(function()
{
	var responsibility=getComboBoxText("cmbkhdmResponsibilityid");	
	var responsibilityId=jQuery('#cmbkhdmResponsibilityid').combobox('getValue');
	var targetDate=getFieldValue('dtekhdmTargetdate');
	//jQuery('#dtekhdmTargetdate').datebox('getValue');
	var endDate=jQuery('#dtekhdmEndDate').datebox("getValue");
	
	var rowId=jQuery('#hdnSelectedVal').val();
	//alert("6");
	if(jQuery('#chkAllHD').is(':checked'))
		{applyHdtoAll(responsibilityId,responsibility,targetDate);}
	//alert("3");
	
	
	if( compareDate(endDate,targetDate) < 0 ) //convertStringToDate(endDate) < targetDate)
	{
		alert('Target cannot be less than Kaizen End Date ');
		return false;
	}
	
	
	if(responsibilityId.trim().length<= 0)
	{
		alert("Select Responsibility");
		//jQuery("#err_cmbkhdmResponsibilityid").css("display","block");
		//jQuery("#err_cmbkhdmResponsibilityid").html("Select Responsibility");
		return false;
	}
	else if(targetDate==null||targetDate.trim()=="")
	{
		alert("Enter Date");
		return false;
	}
	else 
	{
		jQuery('#HorizDeployGrid').setCell(rowId,"cmbkhdmResponsibilityid",responsibilityId);
		jQuery('#HorizDeployGrid').setCell(rowId,"cmbkhdmResponsibility",responsibility);
		jQuery('#HorizDeployGrid').setCell(rowId,"dtekhdmTargetdate",targetDate);
        setTimeout(function() {
            jQuery( "#HdResponsibilityPopup" ).hide();
            jQuery("#HdResponsibilityPopup").dialog("close");
        },1000);
	}
});

jQuery('#btnCancel').click(function()
{
	setTimeout(function() {jQuery("#HdResponsibilityPopup").dialog("close");},1000);
	
	var rowno=jQuery('#hdnSelectedVal').val();
	
	jQuery('#hd_checkbox_'+rowno).attr('checked', false);
	
	
});
jQuery('#chkAllHD').click(function()
{
	var rowIds = jQuery('#HorizDeployGrid').jqGrid().getDataIDs();
	if(!(jQuery('#chkAllHD').is(':checked')))
	{	applyHdtoAll("","","",false);
		checkAll(rowIds,false);
	}
	else
		{loadResponsibility();}
});

jQuery('#btnVwEqp').click(function()
{
	 jQuery('#HorizDeployGrid').trigger("reloadGrid");
});

jQuery('#btnVwCell').click(function()
{
	jQuery('#HorizDeployGrid').trigger("reloadGrid");
});

jQuery('#chkSelectAll').click(function()
{
	var rowIds = jQuery('#HorizDeployGrid').jqGrid().getDataIDs();
	if(jQuery('#chkSelectAll').is(':checked'))
		checkAll(rowIds,true);
	else
		checkAll(rowIds,false);
});

function checkdisabled(){
	var row = jQuery("#HorizDeployGrid").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++){
	  var status=jQuery("#HorizDeployGrid").jqGrid().getCell(row,"status");
	    if(status=="Completed"){
	    	setTimeout(function() {
	    		jQuery('#hd_checkbox_'+row).attr("disabled",true);    
	        },1000);
	    }
	}
	
}
function checkAll(rowIds,para)
{
	for(var i=0;i<rowIds.length;i++)
	{
		jQuery('#hd_checkbox_'+rowIds[i]).attr({'checked':para});
		if(para===true)
			jQuery("#HorizDeployGrid").setCell(rowIds[i],"txtselectionFlag","INSERT");
		else 
			jQuery("#HorizDeployGrid").setCell(rowIds[i],"txtselectionFlag"," ");
	}
}

function applyHdtoAll(responsibilityId,responsibility,targetDate,applyAll)
{
	var rowIds = jQuery('#HorizDeployGrid').jqGrid().getDataIDs();
	checkAll(rowIds,true);
	for(var i=0;i<rowIds.length;i++)
	{
		if(applyAll==false)
		{
			jQuery('#HorizDeployGrid').setCell(rowIds[i],"cmbkhdmResponsibilityid"," ");
			jQuery('#HorizDeployGrid').setCell(rowIds[i],"cmbkhdmResponsibility"," ");
			jQuery('#HorizDeployGrid').setCell(rowIds[i],"dtekhdmTargetdate"," ");
		}
		else
		{		
			jQuery('#HorizDeployGrid').setCell(rowIds[i],"cmbkhdmResponsibilityid",responsibilityId);
			jQuery('#HorizDeployGrid').setCell(rowIds[i],"cmbkhdmResponsibility",responsibility);
			jQuery('#HorizDeployGrid').setCell(rowIds[i],"dtekhdmTargetdate",targetDate);
		}
	}
}
		

</script>

<br/>
<form id="frmHzDplymnt" name="frmHzDplymnt" action="">
	<div class="" style="width: 90%;margin: 0 auto;">
	
	<table>
        <tr>
        <td colspan="2">
        <div id="frmHzDplymntFuntKeyIds">
					<input type="hidden" id="factory" name="cmbkhdmFactoryid" value=""></input>
					<input type="hidden" id="section" name="cmbkhdmSectionid" value=""></input>
					<input type="hidden" id="location" name="cmbkhdmLocationId" value=""></input> 
					<input type="hidden" id="sbu" name="cmbkhdmSbu" value=""></input> 
					<input type="hidden" id="pbu"    name="cmbkhdmPbu" value=""></input>
					<input type="hidden" id="dmt"    name="cmbkhdmDmt" value=""></input>
					<input type="hidden" id="jh"    name="cmbkhdmJh" value=""></input>
					<input type="hidden" id="cell"    name="cmbkhdmCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbkhdmMachineid" value=""></input>
					<input type="hidden" id="flid"    name="cmbkhdmFlid" value="${requestScope.mom. momsFlid} "></input>        
		</div>
		<div  class="easyui-paddingbfpx" id="HDfunLocation" style="width: 104%;margin-top:-12px;width:108%\9;"></div>
	</td>
	</tr>
	</table>
	
	<table>
        <tr>
<!--left  pane -->
			<td class="valigncnt" style="width:40%;">
			<div class="floatleft" style=""></div>
					<div class="floatleft" style="padding-right: 2%">
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Start Date</label><span style="padding-left:60px;"><label class="mandatory-lbl">End Date</label></span></div> 
						 <div class="easyui-paddingbfpx"> 
                			 <input id="dtekhdmStartDate" name="dtekhdmStartDate" class="easyui-datebox" style="width:110px;" value="${requestScope.horizontalDeploymentBean.startDate}" disabled="${requestScope.horizontalDeploymentBean.disablestartDate }"/> 
                  			<span class="floatR2"> 
                 				 <input id="dtekhdmEndDate" name="dtekhdmEndDate" class="easyui-datebox" style="width:110px;" value="${requestScope.horizontalDeploymentBean.endDate}" disabled="${requestScope.horizontalDeploymentBean.disableendDate }"/>
                 			 </span> 
                 		 </div>	
						<div style="display:none;">
	                        <div style="margin-top: 2%;"><label>DMT</label></div>
							<div style="margin-top: 1%;"><input  id="cmbkhdmDmtid" name="cmbkhdmDmtid" class="easyui-combobox" value=""  style=" width : 400px;"/></div>
							<div style="margin-top: 2%;"><label>JH</label></div>
							<div style="margin-top: 1%;"><input  id="cmbkhdmCellid" name="cmbkhdmCellid" class="easyui-combobox" value=""  style=" width : 400px;"/></div>
							<div style="margin-top: 2%;"><label>Equipment</label></div>
							<div style="margin-top: 1%;"><input id="cmbkhdmMachineid" name="cmbkhdmMachineid"  class="easyui-combobox" value="${requestScope.horizontalDeploymentBean.machineId}"  style="width: 400px;"/>
							<span style="padding-left:10px;">
							<input type="button" id="btnview" name="btnview" class="easyui-button" style="width:60px;height:22px;" value="View" />
							</span>
							</div>
						</div>
					</div>
					
			<!--  <div class="floatleft" style="padding-right: 2%">	
					<div class="easyui-paddingbfpx " style="margin-left: 20px;"><label>Assembly</label></div> 
	                  <div class="easyui-paddingbfpx" style="margin-left: 20px;" > 
	                	<input id="cmbkznmAssemblyid" name="cmbkznmAssemblyid" class="easyui-combobox" value="${requestScope.horizontalDeploymentBean.assemblyId}" style="width:255px;"/>
					 </div>
					<div class="easyui-paddingbfpx " style="margin-left: 20px;"><label>Phenomena</label></div> 
                    <div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
                        <input id="cmbkznmPhenomenaid" name="cmbkznmPhenomenaid" class="easyui-combobox" value="${requestScope.horizontalDeploymentBean.phenomenaId}" style="width:255px;"/>
          			</div>
					<div class="easyui-paddingbfpx " style="margin-left: 20px;"><label>Cause</label></div> 
                  <div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
                     <input id="cmbkznmCauseid" name="cmbkznmCauseid" class="easyui-combobox"  value="${requestScope.horizontalDeploymentBean.causeId}" style="width:255px;"/>
                  </div>
				</div>	
				
				-->
					<div class="floatleft">
						<div><label>Theme</label></div>
						<div><textarea rows="5" cols="40" id="txtarTheme" style="width : 566px; height : 30%;resize:none;" disabled="${requestScope.horizontalDeploymentBean.disablestartDate }">${requestScope.horizontalDeploymentBean.theme}</textarea></div>
			        </div>
			      
			   <div style="clear: both;"></div> 
			   <div id="HdResponsibilityPopup"  >
			
			<div class="easyui-paddingbfpx "; style="margin-left: 20px;"><label>Responsibility </label></div> 
			    <div class="easyui-paddingbfpx"style="margin-left: 20px;"> 
				    <input id="cmbkhdmResponsibilityid" name="cmbkhdmResponsibilityid" class="easyui-combobox"  style="width:255px;" value=""/>
				 <span id="err_cmbkhdmResponsibilityid" class="tpm-errormsg" style=""></span> 
				</div>
				<div class="easyui-paddingbfpx"style="margin-left: 20px;">
					<label>Target On</label>
<!--					<span style="padding-left:70px;"><label>Status</label></span>-->
				</div> 
				<div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
				    <input id="dtekhdmTargetdate" name="dtekhdmTargetdate" class="easyui-datebox" style="width:110px;" />
				    <span style="padding-left:20px;">
				       
				    </span> 
				</div>
				<div class="easyui-paddingbfpx"style="margin-left: 100px;margin-top: 40px;">
					<input id="btnOk" name="btnOk" class="easyui-button" type="button" value ="Ok" />
					<input id="btnCancel" name="btnCancel" class="easyui-button" type="button" value="Cancel" />
				</div>
			   
			   </div>
			   <div class="sub-header" style="width: 1100px;">Select Areas where the Kaizen has to be Horizontally Deployed</div>
			   <table class="sub-cntborder" rules="none" style="margin-left: 1%;margin-top: 1%;margin-bottom: 1%;margin-right: 1%;margin: 0%">
			   		<tr>
			   			<td style="width:0%; valign='top'">
			   			<div class="floatleft"  style=" padding-left: 6%">	
			   				<div class="sub-cntborder"  id="FilterDiv" style=" width : 95%; height : 30%;display: none;margin-top: 1%;margin-left: 5%;">
			   					<div style="margin-top: 1%;">
									<span class="headin" style="padding-right: 4%"><label>&nbsp;Filter</label></span>
									<span style="padding-right: 5%;">Search Column:</span>
									<span ><label>PRODUCT CODE</label></span>
								</div>
								<div style=" width : 110%;margin-top: 1%;">
									<span style="padding-right: .5%"><label>&nbsp;Options</label></span>
									<span style="padding-right: 1%;margin-top: 1%"><input type="text" class="easyui-combobox"/></span>
									<span><input type="text" class="easyui-text" style="height: 2%;margin-top: 1%"/></span>
									<span><input type="button" value="Apply" id="btnApply" class="easyui-button" style="height: 2%" /></span>
									<span><input type="button" value="Close" id="btnClse" class="easyui-button" onclick="clse()" style="height: 2%" /></span>
								</div>
			   			
			   				</div><!-- Filter Div -->
			   			</div>
			   				<div class="floatleft" style=" padding-right: 15%">&nbsp;
			   				<input type="checkbox" id="chkSelectAll" name="chkSelectAll" />
			   				<span style="margin-top:-10px;">
			   				<label>Select All</label>
			   				</span>
			   				<span style="padding-left:60px;">
			   				<input type="checkbox" id="chkAllHD" name="chkAllHD" />	
			   				</span>
			   				<span style="margin-top:-10px;"> 
			   				<label>Apply to All HD Values</label>  </span></div>
			   				<div class="floatright">
			   					<div>
			   					<!-- 	<span><input type="button" value="Filter" id="btnFltr" class="easyui-button" onclick="replace()" style="height: 2%" /></span>
										<span><input type="button" value="Find" id="btnFnd" class="easyui-button" onclick="clse()" style="height: 2%" /></span>
								 	<span><input type="button" value="View Cell" id="btnVwCell" class="easyui-button" style="height: 2%" disabled="${requestScope.horizontalDeploymentBean.disableBtnCell}"   /></span>
										<span><input type="button" value="View Equipment" id="btnVwEqp" class="easyui-button"  style="height: 2%" disabled="${requestScope.horizontalDeploymentBean.disableBtnMachine }" /></span>
			   					</div><!-- <c:out value = "${ requestScope.horizontalDeploymentBean.disableBtnMachine == true ? ' disabled':''}"/> -->
			   				</div>
			   				
			   				<div class="clear"></div>
			   				<div id="griddiv" class="floatleft" ><!-- Grid -->
								<div><table id="HorizDeployGrid" class="floatleft" width="80%" ></table> </div>
								<div id="HorizDeployPager"></div>  
	   							<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>	  
								<input type="hidden" id="hdnSelectedVal" name="hdnSelectedVal" value=""/>	 
							</div>
			   				
			   
			   			</td>	
			        </tr>
			   </table>
			   
			</td>
	  </tr>
	
    </table>
  </div>
</form>				