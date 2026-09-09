<script type="text/javascript">
var procesid = jQuery('#hdnProcessid').val();
var tnStpflid = jQuery('#hdnTenStepFlid').val();
var glbQamatrixId ="";
var glbProcessMode ="";
var glbTenStepDate ="";

jQuery(document).ready(function(){

	initialiseForm('frmTenSteps');		
	jQuery('#submitForm').val('frmTenSteps');
	//'whywhyQtyReport_input.why' getWhyWHy_input.tsdi
	glbQamatrixId = jQuery('#hdnQamatrixId').val();
	
	//alert('hdnPrepreaedId'+jQuery('#hdnPrepreaedId').val());

	var preparedId = jQuery('#hdnPrepreaedId').val();
	var loginId = jQuery('#hdnLoginUserid').val();
	glbTenStepDate = jQuery('#hdnTenStepDate').val();
	var finalApprovedBy = jQuery('#hdnFinalApprovedby').val();
	if (loginId.trim() != preparedId.trim())
		glbProcessMode = "view";
	if (finalApprovedBy.trim().length>3)	
		glbProcessMode = "view";
	
	var url =jQuery('#hdnTenStepUrl').val();
	jQuery("#val").text("Step 2 : 4M and I");
	openTenSteps();
	var url =[]; var text =[];var id = []; 
	var glbShowHide=true;
	url[0] = "Qamatrixstep_input.tsdi";
	url[1] = "Qamatrixstep_input.tsdi";	
	url[2] = "fourandmi_input.tsdi?&entryMode=create&";
	url[3] = "problemchart_input.tsdi";
	url[4] = "IIMatrix_input.tsdi";
	url[5] = "getWhyWHy_input.tsdi";
	url[6] = "Step6Qm_input.tsdi";
	url[7] = "implementaction_input.tsdi";	
	url[8] = "fourandmi_input.tsdi?&entryMode=review&";	
	url[9] = "checkpoints_input.tsdi";
	//url[10] = "tenstepsdesign_input.tsdi?&frmMode=LastScreen";
	url[10] = "sustananceAction_input.tsdi?";
	url[11] = "tenstepsdesign_input.tsdi?&frmMode=LastScreen&";
	
	text[0] = "Step 0 : Select Defect";	
	text[1] = "Step 1 : QA Matrix";	
	text[2] = "Step 2 : 4M & I Methodology";	
	text[3] = "Step 3 : Problem Chart";
	text[4] = "Step 4 : Impact & Implementation Matrix";	
	text[5] = "Step 5 : Analysis of the problem";	
	text[6] = "Step 6 : Impact Assessment";
	text[7] = "Step 7 : Implementing Actions ";	
	text[8] = "Step 8 : Review 4M & I Methodology";	
	text[9] = "Step 9 : Check Points";
	text[10] = "Step 10 : Sustanance Action";
	text[11] = " Final Review";
	
	id[0] = "liStep0";
	id[1] = "liqamatrix";
	id[2] = "li4mi";
	id[3] = "liproblmchart";
	id[4] = "liiimatrix";
	id[5] = "liwhywhy";
	id[6] = "listep6";
	id[7] = "listep7";
	id[8] = "liReview4mi";
	id[9] = "licheckpoints";
	id[10] = "licltiMain";
	id[11] = "liFinalReview";
	glbShowHide==true;
	
	jQuery("#btnNextMenu").click(function(){
		var i = jQuery("#hdnVal").val();
		var j = parseInt(i);
		if(j<=10){ 	var k = j+1;jQuery("#hdnVal").val(k); 	}
		if(k<=11){
			var urlid=url[k]+"?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid+"&qamatrixId="+glbQamatrixId;
			LoadForm("divSteps","",urlid);
			jQuery("#val").text(text[k]);		
			}
		if(jQuery('.clsLi').hasClass('LIselected'))
				jQuery('.clsLi').removeClass('LIselected');	
		jQuery('#'+id[k]).addClass('LIselected');
	});
	
	jQuery("#btnPreviousmenu").click(function(){
		var j = jQuery("#hdnVal").val();	var i = parseInt(j);
		if(i>0){	var k = i-1; jQuery("#hdnVal").val(k);		}
		if(k>=0){ var urlid=url[k]+"?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid+"&qamatrixId="+glbQamatrixId;	
		LoadForm("divSteps","",urlid);	jQuery("#val").text(text[k]);
		}if(jQuery('.clsLi').hasClass('LIselected'))
		 	  jQuery('.clsLi').removeClass('LIselected');
		jQuery('#'+id[k]).addClass('LIselected');
	});

	glbShowHide==true;
	fileManagerPopUp("","TEN","frmTenSteps","btnfilemgr","tenStepFileMgr");
	//showClits();
	setTimeout(function() {
		fnShowCurrentStep(); 
	},500);
	setTimeout(function() {
		disableFilterBtn();
	},200);
}); 

function fnShowCurrentStep() {
	//alert(jQuery('#hdnTenStepCurrentSep').val());
	jQuery('#filterPanel').css('display','none');
	if (jQuery("#hdnTenStepCurrentSep").val()=="4M and I") {
		loadSteps('fourandmi_input.tsdi?&entryMode=create&','li4mi',2);
	}
	else if (jQuery("#hdnTenStepCurrentSep").val()=="Problem Chart") {
		loadSteps('problemchart_input.prch','liproblmchart',3);
	}
	//'IIMatrix_input.impac','liiimatrix',4
	else if (jQuery("#hdnTenStepCurrentSep").val()=="Why Why") {
		jQuery('#filterPanel').css('display','none');
		loadSteps('getWhyWHy_input.tsdi','liwhywhy',5);
	}
	else if (jQuery("#hdnTenStepCurrentSep").val()=="Impact Assessment") {
		loadSteps('Step6Qm_input.sqm','listep6',6);
	}
	else if (jQuery("#hdnTenStepCurrentSep").val()=="Implementing Action") {
		loadSteps('implementaction_input.impac','listep7',7);
	}
	else if (jQuery("#hdnTenStepCurrentSep").val()=="Review 4M and I") {
		loadSteps('fourandmi_input.tsdi?&entryMode=review&','liReview4mi',8);
	}
	else if (jQuery("#hdnTenStepCurrentSep").val()=="CheckPoints") {
		loadSteps('checkpoints_input.impac','licheckpoints',9);
	}
	else if (jQuery("#hdnTenStepCurrentSep").val()=="Sustance Action") {
		loadSteps('sustananceAction_input.tsdi','licltiMain',10);
	}

}

function fnGlbSaveMode() {
	if (glbProcessMode=="view") {
		popupCommonErrorMsg("You do not have modification privileges.");
		return false;
	}
	return true;
}
function btnfilemgr_click()
{
 	var keyid = glbQamatrixId;   
	if(keyid != null && keyid != ''){
		fileManagerPopUp(keyid,"TEN","","","");
	}
}
function openTenSteps( ){
	
	var url =jQuery('#hdnTenStepUrl').val();	
	
	var urlid=url+"?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid+"&qamatrixId="+glbQamatrixId;
	LoadForm("divSteps","",urlid);
	
	if(jQuery('.clsLi').hasClass('LIselected'))
	 	  jQuery('.clsLi').removeClass('LIselected');
	
	var currenStp = jQuery('#nxtStepId').val();
	 	  jQuery('#'+currenStp).addClass('LIselected');
	
}

function showClits() {
	if (glbShowHide==false) {
		glbShowHide=true;
		jQuery('#liclti').show();
		jQuery('#liKaizen').show();
		jQuery('#liPM').show();
	}
	else {
		glbShowHide=false;
		jQuery('#liclti').hide();
		jQuery('#liKaizen').hide();
		jQuery('#liPM').hide();
	}
}

function loadSteps(url,id,val){

	var isClti= false;
	glbShowHide=true;
	if(id=="liclti"||id=="liPM"||id=="liKaizen"){
		glbShowHide=false;
	}
	showClits();
	
	if(id=="liStep0")	{
		jQuery("#val").text("Step 0 : Select Defect");
	}
	if(id=="liqamatrix")	{
		jQuery("#val").text("Step 1 : QA Matrix");
	}
	else if(id=="li4mi"){
		jQuery("#val").text("Step 2 : 4M and I Methodology");
	}
	else if(id=="liproblmchart"){
		jQuery("#val").text("Step 3 : Problem Chart");
	}
	else if(id=="liiimatrix"){
		jQuery("#val").text("Step 4 : Impact & Implementation Matrix");
	}
	else if(id=="liwhywhy"){
		jQuery("#val").text("Step 5 : Analysis of the problem");
		jQuery("#hdnTenStepCurrentSep").val("Why Why");
	}
	else if(id=="listep6"){
		jQuery("#val").text("Step 6 : Impact Assessment");
		jQuery("#hdnTenStepCurrentSep").val("FMEA");
	}
	else if(id=="listep7"){
		jQuery("#val").text("Step 7 : Implementing Actions");
	}
	else if(id=="liReview4mi"){
		jQuery("#val").text("Step 8 : Review 4M & I Methodology");
	}
	else if(id=="licheckpoints"){
		jQuery("#val").text("Step 9 : Check Points");
	}
	else if(id=="licltiMain"){
		jQuery("#val").text("Step 10 : Sustanance Action");
	}
	
	else if(id=="liclti"){
		isClti=true;
		jQuery("#val").text("Step 10 : CLTI");
	}
	else if(id=="liPM"){
		jQuery("#val").text("Step 10 : PM");
	}
	else if(id=="liKaizen"){
		jQuery("#val").text("Step 10 : Kaizen");
	}
	
	else if(id=="liFinalReview"){
		jQuery("#val").text(" Final Review");
	}
	

	if(jQuery('.clsLi').hasClass('LIselected'))
	 	  jQuery('.clsLi').removeClass('LIselected');
	 	  jQuery('#'+id).addClass('LIselected');
	 	   
	    	 var urlid=url+"?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid;
	    	 if(isClti==true){
                 if( (jQuery('#isCommonFilterSlideOpen').val().length <=0 || jQuery('#isCommonFilterSlideOpen').val() != "Y")  ){
                        var url = "get_inputForm.commonFilter?loadContentDivId=loadFilter&preLoadContentDivId=preLoadFilter&formId=commonFilter&notSetHiddenUrl=true";
                        //url+="&isHidePrevForm=false";
                        navigateToNextForm(url,"" ,null ,null,"commonFilterNavigateToNext_SuccessCalBack","commonFilterNavigateToNext_ErrorCalBack");
                        
                 }
	    	   }
	
	LoadForm("divSteps","",urlid);
	jQuery("#hdnVal").val(val);
}

jQuery("#btngraph").click(function()
		{
			//alert("approval");
			var url = "tenStepChart.tsdi";  
			showGraphData(url);
			
		});

</script>
<style>

#ulTenSteps li:hover {
	background-color:#539DF4;
		line-height:30px; }
	
.LIselected{
		background-color:#A6DDE4;
		line-height:30px;
	}
</style>
<form id="frmTenSteps" name="frmTenSteps">
	<div id="wrapper" style="margin-top: 0;margin-left: 0.2%;width:120%;">
		<div>
		
			<div  style="width: 15.5%;float:left;background-color:#FCFA88; height: 472px;border:inset 1px #000;" >
			<div style=" width:182px;margin-top:-1px; " class=" sub-header">
				<label style="margin-left:19%;font-size: 16px;font-weight: bold ;">Step 1 - 10</label>
			</div>				
						<ul id="ulTenSteps" style="padding:10;line-height: 30px">
						
								<li class="clsLi" id="liqamatrix"  style="cursor: pointer;font-size: 14px;font-weight: bold;'" onclick1="loadSteps('Qamatrixstep_input.qams','liqamatrix',1)">QA Matrix</li>
								<li class="clsLi" id="li4mi"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('fourandmi_input.tsdi?&entryMode=create&','li4mi',2)">4M and I</li>
								<li class="clsLi" id="liproblmchart"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('problemchart_input.prch','liproblmchart',3)">Problem Chart</li>
								<li class="clsLi" id="liiimatrix"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('IIMatrix_input.impac','liiimatrix',4)">Impact Imp. Matrix</li>
								<li class="clsLi" id="liwhywhy"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('getWhyWHy_input.tsdi','liwhywhy',5)">Why Why</li>
								<li class="clsLi" id="listep6"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('Step6Qm_input.sqm','listep6',6)">Impact Assessment</li>
								<li class="clsLi" id="listep7"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('implementaction_input.impac','listep7',7)">Implementing Action</li>
								<li class="clsLi" id="liReview4mi"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('fourandmi_input.tsdi?&entryMode=review&','liReview4mi',8)">Review 4M and I</li>
								<li class="clsLi" id="licheckpoints"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('checkpoints_input.impac','licheckpoints',9)">Check Points</li>
								<!-- <li class="clsLi" id="licnti"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick="loadSteps('CLTI_input.clti','licnti',9)">CLTI</li> -->								
								<!-- <li class="clsLi" id="licltiMain"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick="showClits()">Sustanance Action</li> -->
								<li class="clsLi" id="licltiMain"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('sustananceAction_input.tsdi','licltiMain',10)"">Sustanance Action</li>
								<li class="clsLi" id="liclti"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('jhplancreation_input.jhcal','liclti',10)" hidden="false"> <label>    CLTI</label></li>
								<li class="clsLi" id="liPM"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('preventive_input.prv','liPM',10)" hidden="false"> <label>    PM</label></li>
								<li class="clsLi" id="liKaizen"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick1="loadSteps('kaizen_input.kaizen','liKaizen',10)" hidden="false"><label>    KAIZEN</label></li>
								 <li class="clsLi" id="liFinalReview"  style="cursor: pointer;font-size: 14px;font-weight: bold;" 
										onclick1="loadSteps('tenstepsdesign_input.tsdi','liFinalReview',9)">Final Review</li>
								
							</ul>
							
					<input type="button" name="btngraph" id="btngraph" class="easyui-button"  value="10 Step Graph" style="display: none;"/>
					<div></div>
					<div>
				   	<span  id="tenStepFileMgr" style="padding-left:50px;padding-top:30px;" >
				   </span>
				   	</div>
				   
					
			</div>
			
			<div class="sub-header" style="text-align: center;width:81%; width:97%\9;height:25px\9;position:relative;margin-left:16%">
			<span style="position:absolute;left:50%;font-size:15px;" id="val"></span>
					<span style="position:absolute; right:10%; right:24%\9; " id="Nextbtn">
					<img class="" style="cursor: pointer;z-index:210;margin-top:-3;height:21px;border:outset 1px;background:#c1c1c1; " onclick="steps()" src="images/pagination_next.gif" title="Next Step" alt="" id="btnNextMenu">
			</span>
					<span style="position:absolute; left:32%; left:32%\9; " id="Previousbtn">
							<img class="" style="cursor: pointer;z-index:00;margin-top:-3;height:24px;height:21px;border:outset 1px;background:#c1c1c1; " onclick="step()" src="images/pagination_prev.gif" title="Previous Step" alt="" id="btnPreviousmenu">
					</span>
					<label style="float:left;font-size:15px;">${requestScope.processname}</label>
			</div>
			<div id="divSteps" style="width: 82.00%; height: 92.2%; height:420px\9;margin-top:2px;border: solid 1px #c1c1c1;overflow: auto;float: right;margin-right:2%;" >
			</div>
		</div>
	</div>
<input type="hidden" id="hdnVal" value="2"></input>
<input type='hidden' id="nxtStepId" value="li4mi"/>
<input type='hidden' id="hdnTenStepUrl" value="fourandmi_input.tsdi?&entryMode=create&"/>
<input type='hidden' id="hdnProcessid" value="${requestScope.processId}"/>
<input type='hidden' id="hdnTenStepFlid" value="${requestScope.flid}"/>
<input type="hidden" name = "hdnQamatrixId" id ="hdnQamatrixId" value="${requestScope.qamatrixId}"/>
<input type="hidden" name = "hdnPrepreaedId" id ="hdnPrepreaedId" value="${requestScope.preparedId}"/>
<input type="hidden" name = "hdnLoginUserid" id ="hdnLoginUserid" value="${requestScope.loginUserid}"/>
<input type="hidden" name = "hdnTenStepDate" id ="hdnTenStepDate" value="${requestScope.tenStepDate}"/>
<input type="hidden" name = "hdnFinalApprovedby" id ="hdnFinalApprovedby" value="${requestScope.finalApprovedby}"/>

</form>
