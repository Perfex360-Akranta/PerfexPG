 <style type="text/css">
.btnPadding{
padding:5px
}
#imgEmp{
margin-left:10px;
width:100px;
}
#subDiv{
margin-top: -5px; 
overflow: auto; 
height: 390px;
max-height:315px;
height: 355px\9;
max-height:360px\9; 
display: block;
width:920px;
width:900px\9;
}
 .dshbrdBtn{
	background-image:url(images/accordian/dashbrd.png);
}

.cntrlPnlBtn{
background-image:url(images/accordian/controlPanel.png);
}
.gradient-button {
    margin-left: 100px;
    font-family: "Arial Black", Gadget, sans-serif;
    font-size: 15px;
    padding: 9px;
    text-align: center;
    text-transform: uppercase;
    transition: 0.5s;
    background-size: 200% auto;
    color: #FFF;
    box-shadow: 0 0 20px #eee;
    border-radius: 10px;
    width: 220px;
 /*    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24); */
    transition: all 0.3s cubic-bezier(.25,.8,.25,1);
    cursor: pointer;
    display: inline-block;
    border-radius: 25px;
}
/* .gradient-button:hover{
    box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
    margin: 8px 10px 12px;
} */

.gradient-button-1 {background-image: linear-gradient(to right, #DD5E89 0%, #F7BB97 51%, #DD5E89 100%)}
 .gradient-button-1:hover { background-position: right center; } 

.flippy>img {
    /**/-moz-transform:scale(1,1);-webkit-transform:scale(1,1);
    transform:scale(1,1);
    /**/-webkit-transition:all 600ms ease;-webkit-transition:all 600ms ease;
    transition:all 600ms ease; }

    .flippy:hover>img {
        /**/-moz-transform:scale(-1,1);-webkit-transform:scale(-1,1);
        transform:scale(-1,1); }
        
        
 
</style>
<script>
function  frmEmpPagecmbLPShiftid_onLoadSuccess(){
	//alert("inside");
	getCurrentShift("cmbLPShiftid");
}
	jQuery(document).ready(function(){
		//alert("Welcome2");
		//intializeForm("frmEmpPage");
		initialiseForm("frmEmpPage");
		//alert("Welcome21");
		jQuery('#subDiv').css('height',window.innerHeight-250 );
		jQuery('#subDiv').css('max-height',window.innerHeight-260);
		jQuery('#loadBase>.sub-header').hide();
		//alert(" in side the landing page");
		jQuery('#mainlayout').layout('panel','west').panel({
				onCollapse:function(){
					jQuery('#subDiv').css('height',window.innerHeight-250 );
		     		},
		  		onExpand:function(){
					jQuery('#subDiv').css('height',(window.innerHeight-250) );
		  		},
		
		  		onResize:function(){              			
					jQuery('#subDiv').css('height',(window.innerHeight-250) ); 
		  		}
		 });
		
		jQuery('.LoadPopUp').css('background-color','#EAEAEA');
		//#EAEAEA
		
		
		
		
	
		var userkeyid = jQuery('#userLoginid').val();  
		//alert("UserLoginId : "+userkeyid);
		//fillComboBox("frmEmpPage","cmblanPageMachineid","machineCombo.commonFilter?&empId="+userkeyid);//madhan
		/* for functionalLocation*/
		var factId = jQuery("#frmEmpPage input[id='factory']").val();
		var sectionId = jQuery("#frmEmpPage input[id='section']").val();
		var cellId = jQuery("#frmEmpPage input[id='cell']").val();
		var machId = jQuery("#frmEmpPage input[id='machine']").val();
		var flid = jQuery("#frmEmpPage input[id='flid']").val();
		//machId = "MCH0002057";
		flid = getFieldValue("hdnLoginFlid");
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		loadFunctionalLocation("basefunLocation","functionalLoc.commonFilter","basefunLocationValues","frmEmpPage",dataStr);
		formatDateBox('dteEmpmdate','dd-MMM-yyyy');
		fillComboBox("frmEmpPage","cmbLPShiftid","shift.commonFilter");
		//getCurrentShift("cmbLPShiftid");
		fillWithCurrentDate('dteEmpmdate');
		
		
		
		jQuery('#pcsLossGrdBtn').click(function(){
			// navigateToNextForm("pcsLoss_input.base" ,'PCS Loss',null,persistentData);
		
			jQuery('#controlPanelDiv').hide();
			jQuery('#DivmasterForm').hide();
			jQuery('#dashboardDiv').hide(); 
			if( jQuery(".layout-split-west").is(":visible")){
				 jQuery('#mainlayout').layout('collapse','west');
				 jQuery('#subDiv').css('width','1186');
			}
			var pcsLossDivData = jQuery('#pcsLossDiv').html();
			if(pcsLossDivData.length<=0) {
			 	LoadForm("pcsLossDiv","preloadDIVid1","pcsLoss_input.base","dispErr","","pcsLossDivData_errorCallBack");
			}
			else{
				jQuery('#pcsLossDiv').show();
			} 
			
		});
		
		jQuery('#btnControlPanel').click(function(){
			jQuery('#dashboardDiv').hide();
			jQuery('#DivmasterForm').hide();
			jQuery('#pcsLossDiv').hide();
			if( jQuery(".layout-split-west").is(":visible")){
				 jQuery('#mainlayout').layout('collapse','west');
				 jQuery('#subDiv').css('width','1186');
			}
			var controlPanelData = jQuery('#controlPanelDiv').html();
			if(controlPanelData.length<=0)
			 	LoadForm("controlPanelDiv","preloadDIVid1","controlPanel_input.base","dispErr","","controlPANEL_errorCallBack");
			else{
				jQuery('#controlPanelDiv').show();
			}
		});
		
		 
		
		jQuery('#btnlpDashboard').click(function(){
			if( ! jQuery(".layout-split-west").is(":visible")){
				 jQuery('#mainlayout').layout('expand','west');
				 jQuery('#subDiv').css('width','920');
			}
			jQuery('#controlPanelDiv').hide();
			jQuery('#DivmasterForm').hide();
			showDashboard();
		});
		
		jQuery('#imgLPEmployee').click(function(){
		    var userkeyid = jQuery('#userLoginid').val();
		    var urll="EmployeInfoEdit_input.base";
		   
		    LoadPopUp("EmployeeInfo",urll+'?&userkeyid='+userkeyid,true,"60%","45%","20%","80px","","Employee Detail Update",false,false);
		});
		
		processAjaxCalls("getEmployeeData.base",'userkeyid='+userkeyid,'employee_OnSuccess','employee_OnError');
		processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#userLoginid').val() ,"EmpImgSuccess","EmpImgErr");
		showDashboard();//show Dashbord on Load Form
		jQuery('#btnLnkKpi').click(function(){
			//LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?q=2&pilar="+pilar, true,"38%","75%","0px","40%", "multiSelectOk_Callback","KPI Indicator",false);
			LoadPopUp("divEmpIndicatorPop", "empkpiIndicatorList_view.base?q=2&pilar=" , true,"48%","82%","10%","35%", "empKpiOk_Callback","Employee KPI Link",false);
		});
	});
	
	
	
	function EmpImgSuccess(result)
	{ 
		if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
		jQuery('#imgLPEmployee').attr('src','');
		jQuery('#imgLPEmployee').attr('src',result.empImg.imgToimBlobimage);	
		}
	}

	
	
	function isValidKey(e)
	{
	    var charCode = e.keyCode || e.which;
	    if (charCode == 8 || charCode == 46)
	        return false;

	    return true;
	}
	function showDashboard(){
		var dashBrdData = jQuery('#dashboardDiv').html();
		var showAll ="";
		if(jQuery(".dashboard_content_div").css("display")=="block" ){
			showAll = "false";// jQuery('#hdnDashBoardDisplay').val();
		if(dashBrdData.length<=0)
			LoadForm("dashboardDiv","preloadDIVid","Dashboard_input.base?showAll="+showAll,"dispErr","","dashboard_errorCallBack");
			jQuery("#btnSuggestion").hide();
			jQuery("#btnopl").hide();
			jQuery("#btnabn").hide();
			jQuery("#btnWhywhy").hide();
			jQuery("#btnActionplan").hide();
			jQuery("#btndmtmom").hide();
			jQuery("#btnjhmom").hide();
			jQuery("#btnpillarmom").hide();
			jQuery("#btnnearmiss").hide();
			jQuery("#btnlossentry").hide();
		    jQuery("#lbltrnlink").hide();
		    jQuery("#btnwhywhy").hide();
		    jQuery("#btnFieldObservation").hide();
		    jQuery("#btnOplView").hide();
		    jQuery("#btnKaizenView").hide();
		    jQuery("#btnKPI").hide();
		    jQuery("#btnQCLogging").hide();
		}
		else{
			jQuery('#dashboardDiv').show();
		
		}
	}
	
	function employee_OnSuccess(result){
		 jQuery("#txtEmpmName").val(result.empmName);
		 jQuery("#txtEmpmCode").val(result.empmNo);
		 jQuery('#hdnPillarId').val(result.empmPillar);
		 if("PM"!=result.empmPillar || "-"!=result.empmPillar)
			 jQuery('#btnLnkKpi').show();
		 else
			 jQuery('#btnLnkKpi').hide();
			 
		 //setFieldValue("cmblanPageMachineid", "MCH0002061","frmEmpPage");
		 if(jQuery.browser.msie ){ 
				jQuery('#dispFunctionalLoc').css('width','675px');
			}else{
				jQuery('#dispFunctionalLoc').css('width','735px');
			}	 
	}
	function chkbox_Employee(id, options, rowObject)
	{ 
		var rowId = options.rowId;	
		return '<input id="employee_checkbox_'+rowId+'" name="employee_checkbox_'+rowId+'" '+ (rowObject[2]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}
	function chkboxCheck(rowId)
	{
		jQuery("#empEqpGrid").jqGrid('setCell',rowId,'checkempvalue','1');
		var row = jQuery("#empEqpGrid").jqGrid('getDataIDs');
		 for(var i=0;i<row.length;i++)
		 {
			 if(rowId!=row[i]){
				 jQuery('input:checkbox[name=employee_checkbox_'+row[i]+']').attr('checked',false);
				 jQuery("#empEqpGrid").jqGrid('setCell',row[i],'checkempvalue','0');
			 }
		 }
		 	
	}
	function chkboxUnCheck(rowId)
	{
		jQuery("#empEqpGrid").jqGrid('setCell',rowId,'checkempvalue','0');
	}
	function  frmEmpPagecmblanPageMachineid_onSelect(record)
	{
		loadFunctionalLocation("basefunLocation","functionalLoc.commonFilter","basefunLocationValues","frmEmpPage","&machId="+record.id);
	}
	
	function frmEmpPagecmblanPageflid_onSelect(record){
		loadFunctionalLocation("basefunLocation","functionalLoc.commonFilter","basefunLocationValues","frmEmpPage","&flid="+record.id);
	}
	
	
	
	
  jQuery("#btnSuggestion").click(function(){
	 jQuery('#mainlayout').layout('collapse','west');
     jQuery('#subDiv').css('width','1186');
	 jQuery('#subDiv').css('max-height','397');
	 navigateToNextForm("KaizenBankSuggestion_input.kznbnk","","","");	
  });

  jQuery("#btnopl").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("create_input.opl","","","");  
  });

  jQuery("#btnabn").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("Abnormality_input.abnForm","","","");
	  
  });
  
  jQuery("#btnwhywhy").click(function(){
	  
	  navigateToNextForm("whywhyanalysismodify_input.why","","","");
  });
 jQuery("#btnActionplan").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	 navigateToNextForm("ActionPlan_input.api","","",""); 
 });
  
 jQuery("#btnjhmom").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("MoMeetingForm_input.mom?type=JH","","",""); 
 });
 
 jQuery("#btndmtmom").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("MoMeetingForm_input.mom?type=Dmt","","",""); 
 });
 
 jQuery("#btnpillarmom").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("MoMeetingForm_input.mom?type=Pillar","","",""); 
 });
 
 jQuery("#btnnearmiss").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("Nearmiss_input.nmrnew","","","");  
 });
 
 jQuery("#btnlossentry").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("pcsLoss_input.pcs","","","");  
 });
 
 jQuery("#btnQCLogging").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("QCLogging_input.ehsb","QC Logging Creation","","");

 });
 
 jQuery("#btnFieldObservation").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("FieldObservation_input.ehsb","Field Observation Creation","","");

 });
 jQuery("#btnOplView").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("oplVw_input.opl","OPL View","","");

});
 
 jQuery("#btnKaizenView").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("KaizenView_input.kaizen","Kaizen View","","");

});
 
 jQuery("#btnKPI").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  navigateToNextForm("kpiActualJh_view.kpiActKk","KPI View","","");

});
 
 jQuery("#btndashboard").click(function(){
		var userkeyid = jQuery('#userLoginid').val();
	//alert(userkeyid);
		processAjaxCalls("getEmployeeLocation.base",'userkeyid='+userkeyid,'employeeLocation','employeeLocation_OnError');
	 });
 
 jQuery("#btnTeamPerformance").click(function(){
	  jQuery('#mainlayout').layout('collapse','west');
	  jQuery('#subDiv').css('width','1186');
	  jQuery('#subDiv').css('max-height','397');
	  var Flid=jQuery("#hdnLoginFlid").val();
	//  alert("Flid"+Flid);
	  // navigateToNextForm("CustomerComplaint_input.ncapa?q=2&CapaId="+CapaId+"&LoginUser="+LoginUser+"&LoginDMTUser="+LoginDMTUser+"&mode="+mode+"&Status="+Status+"&TargetJH="+TargetJH+"&filterButton=false","CAPAApproval");

	  navigateToNextForm("TeamPerformance_input.apdb?q=2&Flid="+Flid,"TeamPerformance","","");

});
 
 jQuery("#btnTeamPerformanceCount").click(function(){
	 var Flid=jQuery("#hdnLoginFlid").val();
	// alert("Flid"+Flid);
	 navigateToNextForm("TeamPerformanceCount_input.apdb?q=2&Flid="+Flid,"Team Performance Count","","");
 });
 
 
 
 function employeeLocation(result){    
 	var EmpLocation=result.EmpLocation;
 	var userkeyid=jQuery('#userLoginid').val();
 	//alert(EmpLocation);	
 	//if(EmpLocation=="LCN0000002" || userkeyid=="EMP00001"){
 		 var pillarId="NDB";
 		 jQuery('#mainlayout').layout('collapse','west');
 	     jQuery('#subDiv').css('width','1186');
 		 jQuery('#subDiv').css('max-height','397');
 		 LoadPopUp("DashboardPopup", "newdashboard_input.dashboard?pilar="+pillarId,true,"99%","95%","1%","-0%", "empKpiOk_Callback","PSPD-Dashboard",false);
 //	}
 	/*else{
 		
 		alert("Unable to Access Dashboard");
 		return false;
 	}*/
 	
 	 }

 
 
</script>
 
<form id="frmEmpPage" name ="frmEmpPage" >
<!-- background-color:#EAEAEA; -->
<div id="" style="">
<table style="margin-top:-12px;width:900px;margin-bottom:5px;display:${requestScope.disaplyEmp}">
	<tr>
		<td  valign="middle" style=" width:7%" > <!-- Left Pane -->
			 <!-- Employee Image -->
						   <div id="imgEmp" style="">
						   		<img id="imgLPEmployee" name="imgLPEmployee" src="images/empImage/EmpDefaultImg.jpg" width="90px" height="90px"/>
						   </div>
					<div style="margin-top:10px;display: none;"> <input type="button" value="Link KPI" class="lnkBtn" id="btnLnkKpi" title="Link KPI" style="width: 100px; height: 21px; cursor: default;background:#4572A7;border:inset 2px #c1c1c1"/></div>
 		</td>
		<td width=70% valign="top"><!-- Right Pane -->
			<table style="margin-left:0px;"><!-- Employee Information and Employee Related Machines -->
				<tr>
					<td colspan='4'>
						<div>
									<div  id="frmEmpPageFuntKeyIds">
									<input type="hidden" id="factory" name="cmblanPageFactoryid" value=""  ></input>
									<input type="hidden" id="section" name="cmblanPageSectionid" value=""  ></input>
									<input type="hidden" id="cell" name="cmblanPageCellid" value=""  ></input>
									<input type="hidden" id="machine" name="cmblanPageMachineid" value=""  ></input>
									<input type="hidden" id="flid" name="cmblanPageflid" value=""  ></input>
									</div>
								 	<div id="basefunLocation" style="width:100%;margin-top: 17px;"></div>
							 	</div>
						
					</td>
				</tr>
				<tr>
					<td valign="top" style="width:255px;width:185px\9;">
						<div id="empInfo">
							   	<div class=" "  style=" width : 257px;" >
							   		<label class="mandatory-lbl">Name</label>
							   		<span class=" " style="margin-left:100px;">
									   <label id="lblEmpmCode" class="mandatory-lbl">Employee No.</label>
									</span>
							   	</div> 
			                  	<div class="" style=" width :255px; ">
<!-- 				                	<label  id="txtEmpmName" name="txtEmpmName"  style="border: 1px solid rgb(0, 139, 194); height: 29px;height: 20px\9; max-width: 275px;width: 275px;width :65px\9;padding :3 50 3 50;"   ></label> -->
				                	<input class="easyui-text"   maxlength="0"  id="txtEmpmName" name="txtEmpmName"  style="width:122px;width :123px\9;background-color:transparent; height : 23px;" value=""  >
			                  		<span class="mndlbl " style="margin-left:4px;margin-left:4px\9;"> 
<!-- 			                		<label   id="txtEmpmCode" name="txtEmpmCode"  style="border: 1px solid rgb(0, 139, 194); height: 29px;height: 20px\9;max-width: 275px; width: 275px;width: 105px\9;padding :3 50 3 50;" ></label> -->
									<input  class="easyui-text"  maxlength="0" id="txtEmpmCode" name="txtEmpmCode"  style="width:123px;height : 23px;background-color:transparent;"   value=" "  >
			                		</span>
								</div>
								    
						</div>
					</td>
					<td style="margin-top:0px;width :240px;display: none;" valign="top">
<!-- 						<div class="sub-header"> My Equipments</div> -->
						 <div  class="">
		                    <label>Equipment</label>
		                </div> 
		                <div class=""> 
		                    <input id="cmblanPageMachineid" name="cmblanPageMachineid" class="easyui-combobox"  style="width:230px;" value=""  >                    
		                </div>
						<!-- <table id="empEqpGrid"><tr><td></td></tr></table>
						<div id="empEqpPager"></div> -->
					</td>
					<td style=" width :210px;" valign="top">
						         <div class="mndlbl "  >
									<label class="mandatory-lbl">Date</label>
								<span class=" " style="margin-left:77px ;" >
									<label class="mandatory-lbl">Shift</label>
								</span>
								</div>
		                <div class=""> 
					                <input id="dteEmpmdate" disabled="disabled" name="dteEmpmdate" class="easyui-datebox" clear="false"  style="width:100px;" value=""/>  
								<span class="" style="margin-left:1%;"  >
							    	<input type="text" class="easyui-combobox" disabled="disabled" id="cmbLPShiftid" name="cmbLPShiftid" style="width:75px;"  value=" "/>
							    </span>
							    </div>
							  <!--   <div style="margin-left:200px;margin-top:-30px;">
							<span><input type="button" class="easyui-button" id="btndashboard" name="btndashboard" value="Dashboard" style="width:80px;"/></span>
							    </div> -->
							
					
					
					  	    <div style="margin-left:200px;margin-top:-30px;">
							<span><input type="button" class="easyui-button" id="btndashboard" name="btndashboard" value="Dashboard" style="width:80px;"/></span>
							    </div> 
							    
							     <div style="margin-left:285px;margin-top:-30px;">
							<span><input type="button" class="easyui-button" id="btnTeamPerformance" name="btnTeamPerformance" value="Team Performance" style="width:120px;"/></span>
							    </div>
							    
							 <div style="margin-left:410px;margin-top:-28px;">
							<span><input type="button" class="easyui-button" id="btnTeamPerformanceCount" name="btnTeamPerformanceCount" value="Team Performance Count" style="width:160px;"/></span>
							    </div>
							    
							    </td> 
							    
					
					<td style="width:114px;position:relative\9;" valign="top">
						<div style="margin-top:6px;margin-top:8px\9;cursor:pointer;position:absolute\9;display: none; " class="">
									<input type="button" class="easyui-button" id="btnlpDashboard" value="Dash Board" style="width:28%; "/> 
 <!-- 										<img id="btnlpDashboard" name="btnlpDashboard" src="images/menu-icon/dashboard.png" width="34px\9" height="34px" title="DashBoard"/>
 --> <!-- 										<img id="btnlpDashboard" name="btnlpDashboard" src="images/menu-icon/dashboard.png" width=" " height="34px" title="DashBoard" style="width:34px\9;"/>
 -->								 <span style="margin-left:-2%;cursor:pointer;">
<!-- 									<input type="button" class="cntrlPnlBtn" id="btnControlPanel" value="Control Panel" style="width:31%;" \> -->
										<img id="btnControlPanel" name="btnControlPanel" src="images/defaultIcons/edit_page_lg32.png" width="34px\9" 
										height="34px" title="Control Panel"/>
										<!-- <img id="btnControlPanel" name="btnControlPanel" src="images/defaultIcons/edit_page_lg32.png" width=" " height="34px" style="width:34px\9;" title="Control Panel"/> -->
								</span>
									<span style="position:relative;">
									<!-- <input type="button" class="easyui-button" value="..." id="pcsLossGrdBtn"/> -->
									<img id="pcsLossGrdBtn" name="pcsLossGrdBtn" src="images/accordian/loss.png" width="34px\9" height="34px" style="position:absolute;" title="Loss Capture"/>
<!-- 									<img id="pcsLossGrdBtn" name="pcsLossGrdBtn" src="images/accordian/loss.png" width=" " height="34px" style="position:absolute;top:-34;width:34px\9;top:3\9;" title="Loss Capture"/>
 -->									</span>
								</div>
					</td>
				</tr>
				 
			</table>
 		</td>
	</tr>
	
</table>
<!-- <hr style="width:900px;"><br> -->
<div id="preloadDIVid"></div>
<div id="preloadDIVid1"></div>

<!-- for pop up// margin-top: -8px; overflow: auto; height: 390px; display: block;width:100%;max-width:100%;  -->


<div id="subDiv" style="border-top: inset 2px #c1c1c1">


<table>
<tr>
<td>

 <div class="sub-header" id="lbltrnlink" style="text-align: left;width:911px;"><span>Quick Links</span></div>
 
 </td>
 </tr>
 
 
  <tr>
 <td>
<div style="margin-left:-17px;">
<img id="btnabn" name="btnabn" src="images/menu-icon/abnormality.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Abnormality"/>
</div>
</td>
</tr>
 

<tr>
<td>
<div style="margin-left:150px;margin-top:-106px;">
<img id="btnActionplan" name="btnActionplan" src="images/menu-icon/actionplan.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Action Plan"/>
</div>
</td>
</tr>


<tr>
<td>
<div style="margin-left:318px;margin-top:-107px;">
<img id="btnjhmom" name="btnjhmom" src="images/menu-icon/Jhmom.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Jh-Mom"/>
</div>
</td>
</tr>


<tr>
<td>
<div style="margin-left:486px;margin-top:-108px;">
<img id="btndmtmom" name="btndmtmom" src="images/menu-icon/Dmtmom.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Dmt-Mom"/>
</div>
</td>
</tr>



<tr>
<td>
<div style="margin-left:655px;margin-top:-109px;">
<img id="btnpillarmom" name="btnpillarmom" src="images/menu-icon/pillarmom.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Pillar-Mom"/>
</div>
</td>
</tr>


  <tr>
 <td>
<div style="margin-left:-15px;margin-top:-4px;">
<img id="btnopl" name="btnopl" src="images/menu-icon/OPE.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Opl"/>
</div>
</td>
</tr>





<tr>
<td>
<div style="margin-left:148px;margin-top:-106px;">
<img id="btnlossentry" name="btnlossentry" src="images/menu-icon/Lossentry.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Loss Entry"/>
</div>
</td>
</tr> 



<tr>
<td>
<div style="margin-left:317px;margin-top:-106px;">
<img id="btnnearmiss" name="btnnearmiss" src="images/menu-icon/NearMiss.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Near Miss"/>
</div>
</td>
</tr> 

 <tr>
<td>
<div style="margin-left:488px;margin-top:-106px;">
<img id="btnSuggestion" name="btnSuggestion" src="images/menu-icon/su.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Suggestion"/>
</div>

</td>
</tr>

 <tr>
<td>
<div style="margin-left:655px;margin-top:-106px;">
<img id="btnQCLogging" name="btnQCLogging" src="images/menu-icon/QC1.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="QC Logging"/>
</div>

</td>
</tr>

 <tr>
<td>
<div style="margin-left:-15px;margin-top:0px;">
<img id="btnFieldObservation" name="btnFieldObservation" src="images/menu-icon/FO.png" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Field Observation"/>
</div>

</td>
</tr>

 <tr>
<td>
<div style="margin-left:145px;margin-top:-106px;">
<img id="btnOplView" name="btnOplView" src="images/menu-icon/NOP.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Field Observation"/>
</div>

</td>
</tr>

<tr>
<td>
<div style="margin-left:320px;margin-top:-106px;">
<img id="btnKaizenView" name="btnKaizenView" src="images/menu-icon/KaizenView.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Kaizen View"/>


</div>

</td>
</tr>

<tr>
<td>
<div style="margin-left:490px;margin-top:-106px;">
<img id="btnKPI" name="btnKPI" src="images/menu-icon/NKP.jpg" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="Field Observation"/>

</div>

</td>
</tr>

 <!-- <tr>
 <td>
<div style="margin-left:656px;margin-top:-109px;">
<img id="btnwhywhy" name="btnwhywhy" src="images/menu-icon/WhyWhy.JPG" width="150px\9" height="95px" style="margin-left:35px; margin-top:10px;" title="WhyWhy"/>
</div>
</td>
</tr> -->

 


</table>
<!-- 
<div class="flippy">
    <img src="images/menu-icon/WHY-WHY.jpg"/>
</div> -->

	<div id="dashboardDiv" style="  "></div>
	<div id="controlPanelDiv" style="margin-left:0; "></div>
	<div id="pcsLossDiv" style="margin-left:4;"></div>
	<div id="DivmasterForm" style="margin-left:4;"></div>
</div>
</div>
<input type="hidden" id="hdnPillarId" name="hdnPillarId"/>
<input type="hidden" id="hdnLoginFlid" name="hdnLoginFlid"/>
</form>
 