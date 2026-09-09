<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<script type="text/javascript">

jQuery(document).ready(function(){
	//fillComboBox("frmBanner","cmbEmployeRoles","Combo_UserRoll.creat?loginUserRole=Y" );
	
	processAjaxCalls("Combo_UserRoll.creat","loginUserRole=Y","setDefaultRole","setDefaultRole_error");
	messageBoard();
	//setInterval( messageBoard,1000000);
	//setInterval( messageBoard,300000);
	//setInterval( messageBoard,3000);
	
	let lastActivityTime = Date.now();
	let messageBoardInterval = null;

	// Track user activity
	jQuery(document).on('mousemove keydown click scroll', function () {
	    lastActivityTime = Date.now();
	});

	// Start polling
	messageBoardInterval = setInterval(function () {
	    let idleTime = Date.now() - lastActivityTime;

	    // If user idle for more than 10 minutes, skip AJAX call
	    if (idleTime < 05 * 60 * 1000) {
	        messageBoard();
	    } else {
	        console.log("User idle, skipping messageBoard call");
	    }
	}, 300000);
	
	jQuery('#divRole').hide();
	
	//disableField("ImgMail");
	//jQuery('#ImgMail').hide();

	jQuery('#divLoginUserRole').bind("click", function(){
		
		if( jQuery("#loadSwithUser").length == 0 || jQuery("#loadSwithUser").html() == "" )
		
			fnSwitchUser();
		else if( jQuery("#loadSwithUser").css("display") == "none" ){
			
			jQuery("#loadSwithUser").css("display","block");
			if( jQuery('#loadSwithUserPopupMask') == null || jQuery('#loadSwithUserPopupMask').length == 0  ){
				jQuery('body').append('<div id="loadSwithUserPopupMask" class="popup-mask"></div>');
			};
			jQuery("#loadSwithUserPopupMask").css("z-index",10);
			jQuery("#loadSwithUserPopupMask").fadeIn(100);
			
			jQuery('div .layout-panel-north').css("zIndex",2);
			jQuery('div .layout-panel-north').css("z-index",2);
			
		}
		
	});
	
	jQuery('#ImgNew').click(function(){
		openNewForm();
	});
	jQuery('#ImgBack').click(function(event){
		/**Added By Manikandan Based on Test Case**/
	formNavigationLog(" Before Back ");
		if(formNavigations.length>1){
		var check = confirm("Are you sure to move away from this page");
			if(check)
			 backButtonNavigation();	
		}
	});

	jQuery('#ImgSave').click(function(){
		saveFormButtonPress();
	});

	jQuery('#ImgRefresh').click(function() {
		refreshButtonPress();
		messageBoard();
	});

	
	jQuery('#ImgDelete').click(function(){
		var formId = jQuery('#submitForm').val();
		//alert(formId);
		var id = jQuery.jstree._focused()._get_node().attr('id');
		//alert("banner.jspid"+id);
        var loginid=jQuery("#userLoginid").val();
       // alert("loginid>>>"+loginid);
        //processAjaxCalls("chkEmployeeavl.edp","&q=2&userkeyid="+loginid+"&menuuo="+id,"getDeleteVaildationsuccessCallBack","getDeleteVaildationrrorCallBack","","");
       deleteButtonPress();
		
	});

});

function setDefaultRole(result){
	if( result.length > 0){
		
		//alert(result[0].id);
		//processAjaxCalls("getElementId.userLogin","roleId="+result[0].id,"getElementIdsuccessCallBack","getElementIdonerrorCallBack","","");
		
		jQuery("#divLoginUserRole").html(result[0].text);
		
		var roleId="";
		var roleFlid = result[0].id.split('-');
		var flid = "";
		if(roleFlid[0].length>0) {
			jQuery("#hdnUserRole").val(roleFlid[0]);
			roleId = roleFlid[0];
		}
		if(roleFlid[1].length>0) {
			flid=roleFlid[1];
			jQuery("#hdnLoginFlid").val(flid);
		}
	
		setLoginElementDetails(roleId, flid);
				
	}
	else{
		alert("No Role Assigned to the user !");
	}
}
function setDefaultRole_error(){
	
}
function setLoginElementDetails(roleId, flid) {
	jQuery("#hdnUserRole").val(roleId);
	jQuery("#hdnLoginFlid").val(flid);
	//alert('setLoginElementDetails=='+roleId);
	processAjaxCalls("getElementId.userLogin","roleId="+roleId+"&flid="+flid,"getElementIdsuccessCallBack","getElementIdonerrorCallBack","","");
}
function getElementIdsuccessCallBack(result)
{
	//alert(result.flid);
	jQuery("#hdnLoginElementid").val(result.elementId);
	jQuery("#hdnLoginFlid").val(result.flid);
	jQuery("#hdnIsFlidActive").val(result.inActive);//Added 20AUG2026 by madhan
	var chgpwd = jQuery("#hdnPassword").val();
	//alert("chgpwd:"+chgpwd);
	if(chgpwd.trim() != "true")
	{
		//alert("chgpwd inside:"+chgpwd);
	refreshHomePageSetRole();
	jQuery('#mainlayout').layout('expand','west');
	setFormMainHeader("Home");
	
	refreshForm();
	
	jQuery("#treMenu").jstree("refresh");

	jQuery("#preLoadContent").css("display","none");
	}
}
function refreshHomePageSetRole(){
	var fNavig = popFormNavigation();
	//var preNavig;
	while( fNavig != null ){
		
		var isHomePageC =  getFilterValue(fNavig.URL + '&','isHomePage');
		if( isHomePageC == "true"){
			formNavigations.push(fNavig);
			return;		
		}
		fNavig = popFormNavigation();	
	}
}

function getElementIdonerrorCallBack(){
	
}

//lblSwitchuser
function fnSwitchUser() {
	//openSwitchUserDialog();

	LoadPopUp("loadSwithUser", "switchUser_input.creat?", true,"350px","300px","10px","200px", "showResult_successCallBack","Switch Role",true);
}


function closeRemarksDialog(dlgId)
{
	 jQuery( '#'+dlgId ).hide();
	 jQuery('#mstfrm_div').removeClass('popup-mask');	
	 jQuery( '#'+dlgId ).removeClass('custom-popup');
}



function messageBoard(){
	setTimeout(function() {
		var roleId = jQuery('#hdnUserRole').val();
		var ds = "?roleId="+roleId;
		processAjaxCalls("message_getMsgs.dashboard"+ds, "", "messageBoard_successCalback", "", "", "new",true);
	},2050);
}
function messageBoard_successCalback(result){

	var msgs = result.msgs;
	var prevTitle =null;
	//jQuery("#marqueediv").html("");
	jQuery("#divMarqueeee").html("");
	
	var htmlStr = "";
	var color= [ "hotpink","violet","blue","green"];
	var colIndx = 0;
	for( var i =1;i<msgs.length;i++){
		var row = msgs[i];
		
		if( prevTitle != null && prevTitle != row[0] ){
			
			colIndx = ++colIndx % 4;
			if( prevTitle == null)
				htmlStr += '<span>';
			else 
				htmlStr += ' </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>';
			htmlStr += row[0];
			
		}
		colIndx = ++colIndx % 4;
		htmlStr += '<span style="font-size:12px;font-weight:bold;font-family:Verdana; color:'+ color[colIndx]+'" >';
		htmlStr += '';
		htmlStr +=  row[0];
			if (row[0].length>0)
				htmlStr += "  -  " ;
		htmlStr +=  row[1] ;
		if(i == msgs.length-1){
			
		}else{
			htmlStr += ", &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ";
		}
		
		prevTitle=row[0];
	}
	if( htmlStr != "")
		htmlStr += ' </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>';
	//jQuery("#marqueediv").html(htmlStr);
	
	var StrMar = ' <marquee id="marqueediv" name="marqueediv"   behavior="scroll" scrollamount="5" direction="left" ';  
	StrMar+= ' style="font-size:12px;height:20px;width:900px;font-weight:bold;font-color:#3A83E2;"> ';
	StrMar+= htmlStr + '</marquee>';
	jQuery("#divMarqueeee").html(StrMar);
		
}
	
	
	function getDeleteVaildationsuccessCallBack(result)
	{
		var cnt=result.empcount;
		//alert("count"+cnt);
		if(cnt>0)
			{
			deleteButtonPress();
			}
		else{
			popupCommonErrorMsg("Don't Have Rights to delete this Record, Please Contact TPM Cell or Admin");
			return false;
		}
	}

	
	jQuery('#ImgMail').click(function(){
		//openEmailPopup();		
	});
	jQuery('#ImgViewReport').click(function(){
		var fileName=jQuery("#hdnreportFileName").val();
		if(fileName.trim().length>0){
			processAjaxCalls("openFile.file?fileName="+fileName, "", "", "", "", "new");	
		}else{
			alert("No Such File!!!!!!!!!");
		}
});
	
function  openEmailPopup(){
	LoadPopUp("","email_input.email", true,"50%", "57%", "1%", "5%"," ","E-Mail");	
}
	
	function qq(value,name){
		alert("This functionality is not enabled");
	}
	/**Added By Manikandan to return to home from any page```**/
	jQuery('#imgHome').click(function(){
		jQuery("#hiddenUrl").val("");
		//openSetHomePage(); 	/*redirects to home **/
	});

	jQuery("marquee").hover(function () {
	    this.stop();
	}, function () {
	    this.start();
	});
	 


	function ClearMarque(status){
		if(status=="hide"){
			jQuery('#imgclear').hide();
			jQuery('#imgshow').show();
			jQuery('#marqueediv').hide();
		}
		else{
			jQuery('#imgclear').show();
			jQuery('#imgshow').hide();
			jQuery('#marqueediv').show();
		}
	}


function msgPop(id){
	//alert(jQuery("#spnmsg2").text().trim());
	//var msgString=jQuery("#spnmsg1").text().trim()+jQuery("#spnmsg2").text().trim()+jQuery("#spanMsg3").text().trim();
	LoadPopUp("divpop1","MessagePopUp_input.dashboard", true,"50%", "57%", "1%", "5%"," ","Message");
	
}


function setComboFirstValue(formName, id) {
	var data = jQuery('#'+id).combobox('getData');
	if (data.length>=1) {	
		var first = data[0];
		jQuery('#'+id).combobox('setValue',first.id);		
		var onSelectFunctionName = formName+id +'_onSelect';
		var args = [first];
		dynamicFunctionCall(onSelectFunctionName,args);				
	}
}

</script>

<form id="frmBanner" >
  <div style="" class="headDividedImage">
	                   <table width="100%" height="100%" cellpadding="0" cellspacing="0">
	            		<tr>
	            			<td width="20%" class="head_img0" ></td>
	            			<td width="10%" class="head_img1" ></td>
	            			<td width="70%" class="head_img2" ></td>
	            		</tr>
	            	</table>
	            	
	             </div> 
<!-- 	             <div class="headDividedImage">
  <div class="head_img0"></div>
  <div class="head_img1"></div>
  <div class="head_img2"></div>
</div> -->
<div style="font-size: 20px;margin: 25px 0px 0px 10px;width:200px;float: left">
<span> <img alt="" id="imgHome" style="cursor:pointer;" src="images/perfex-blue-.png"></span>
</div>
<div id="playstpDiv" style=" ">
<span style="padding-top: 10px">
<img alt="" id="imgmessagesetting"  onClick="msgPop(this.id)" style="cursor:pointer;height:20px;opacity:0.95;" src="images/accordian/msgImage.png" title="Message Setting"></span>
<span><img alt="" id="imgplay"  onClick="document.getElementById('marqueediv').start();jQuery('#imgstop').show();jQuery('#imgplay').hide();" style="cursor:pointer;height:20px;opacity:0.75;display:none; " src="images/accordian/playicon1.png" title="Play"></span>
<span><img alt="" id="imgstop"  onClick="document.getElementById('marqueediv').stop();jQuery('#imgplay').show();jQuery('#imgstop').hide();" style="cursor:pointer;height:20px;opacity:0.75;" src="images/accordian/stopicon1.png" title="Stop"></span>
<span><img alt="" id="imgclear"  onClick="ClearMarque('hide');" style="cursor:pointer;height:20px; " src="images/accordian/marqClear1.png" title="Clear"></span>
<span><img alt="" id="imgshow"  onClick="ClearMarque('show');" style="cursor:pointer;height:20px;display:none; " src="images/accordian/showBtn.png" title="Show"></span>
</div>
<!-- Link on the right top corner Div-->

<div style="float: right;margin-right: 5px;margin-top: 8px">
 	<img id="settings-link" alt="" src="images/menu-icon/setting.png" width="40px" height="36px">
</div>

<div class="wordwrap" style="float: right;font-size:10px; margin-top: -10px;color: #ffffff; width:27%">
    ${sessionScope.userLogin.employeeName}
    <span id="divLoginUserRole" class="wordwrap SU" style="cursor: pointer;marging-left:25px;font-size:10px; color: #ffffff; width:17%">
    </span>
</div>
<div class="tcodeSearch" style="width:0px; visibility: hidden; ">

<input  id="t-code-search" class="easyui-searchbox"
			searcher="qq"
			prompt="T Code search"  style="width:150px"></input>
</div>

<div id="msgMarque" >
<div id="divMarqueeee" > </div>
<!-- 
<marquee id="marqueediv"  behavior="scroll" scrollamount="5" direction="left"  style="font-size:12px;height:20px;width:900px;font-weight:bold;font-color:#3A83E2;">
 	<span id="spanMsg3"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
</marquee>
 -->
</div>

<style>

.SU::after {             
	background: url('images/su.gif') no-repeat;
	content:"    ";	            
}

#playstpDiv{
position:absolute;
_position:absolute;
left:230;
top:46;
}
#msgMarque{
position:absolute;
_position:absolute;
bottom:10;
margin-left:315px;
margin-left:40px\9;
width:1080px\9;
}
#settings-link,.top_menu_efct{
opacity:.70;
filter:alpha(opacity=70);
filter: “alpha(opacity=70)”;
}
#settings-link:hover,.top_menu_efct:hover{
opacity:1;
filter:alpha(opacity=100);
filter: “alpha(opacity=100)”;
}

</style>


<div style="" class="bannerIcon">
<img id="ImgNew" alt="" title="New (F1)" src="images/menu-icon/imgpluse.png" style="cursor: pointer;"  width="34px" height="34px" class="top_menu_efct">

<img id="ImgSave" alt=""  title="Save (F2)" src="images/menu-icon/imgsave.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct">

<img id="ImgDelete" alt="" title="Delete (F3)" src="images/menu-icon/imgclose.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct">

<img id="ImgRefresh" alt="" title="Refresh (F5)" src="images/menu-icon/imgreferesh.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct">

<img id="ImgMail" alt="" title="Send Mail" src="images/menu-icon/imgmail.png" style="cursor: pointer;vertical-align:top;margin-top:2" width="29px" height="29px" class="top_menu_efct">

<img id="ImgViewReport" alt="" title="View Report" src="images/menu-icon/ImdFileOpen.png" style="cursor: pointer;vertical-align:top;margin-top:2" width="29px" height="29px" class="top_menu_efct">

<img id="ImgBack" alt="" title="Previous Page (Back Space)" src="images/menu-icon/imgback.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct">
</div>
<input type="hidden" id="hdnFirstTime" name="hdnFirstTime" value="Y">
<input type="hidden" id="hdnLoginElementid" name="hdnLoginElementid" value="">
<input type="hidden" id="hdnLoginFlid" name="hdnLoginFlid" value="">
<input type="hidden" id="hdnUserRole" name="hdnUserRole" value="">
<input type="hidden" id="hdnIsFlidActive" name="hdnIsFlidActive" value="">
</form>