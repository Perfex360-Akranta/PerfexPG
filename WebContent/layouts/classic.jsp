<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<!--new changed classic-->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> --%>

<html height="100%">
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="chrome=1"> -->
<META http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<META CONTENT="NONE"> 
<META CONTENT="NOARCHIVE">
<LINK REL="SHORTCUT ICON"      HREF="images/faviconTPM.png"/>

<style type="text/css">
@-moz-document url-prefix(http://),url-prefix(https://) {
scrollbar {
   -moz-appearance: none !important;
   background: rgb(0,255,0) !important;
}
thumb,scrollbarbutton {
   -moz-appearance: none !important;
   background-color: rgb(0,0,255) !important;
}
 
thumb:hover,scrollbarbutton:hover {
   -moz-appearance: none !important;
   background-color: rgb(255,0,0) !important;
}
 
scrollbarbutton {
   display: none !important;
}
 
scrollbar[orient="vertical"] {
  min-width: 15px !important;
}
}
</style>


    <script type="text/javascript" >	    
	
    var formNavigations = [];

	    window.onbeforeunload = function() {
	        return "Did you save your stuff?";
	    }
    </script>

        <link rel="stylesheet" type="text/css" href="css/tpm-style.css"/>
        <link rel="stylesheet" type="text/css" href="css/tpm-style-1366.css"/>
		
		<link rel="stylesheet" type="text/css" media="screen" href="css/grid/jquery-ui-1.8.2.custom.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/grid/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" href="css/classic-style.css"/>
        <script type="text/javascript" src="js/jquery-1.6.4.js"></script>
        <!-- <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script src="js/jquery-migrate-1.1.1.min.js"></script>
        -->
        <link rel="stylesheet" href="css/pre-loader/queryLoader.css" type="text/css" />
		<script type='text/javascript' src='js/pre-loader/queryLoader.js'></script>
		<script src="js/grid.locale-en.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" media="screen" href="css/home-page-tab-style.css" />
	<!-- 	<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script> -->
		
         <script src="js/grid/core/grid.loader.js" type="text/javascript"></script>
         
        <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="js/classic.js"></script>
        <script type="text/javascript" src="js/jquery.corner.js"></script>
        <script type="text/javascript" src="js/jsTree/jquery.jstree.js"></script>
        <script type="text/javascript" src="js/jsTree/jquery.cookie.js"></script>
        <script type="text/javascript" src="js/jsTree/jquery.hotkeys.js"></script>
        <!-- <script src="js/grid/core/grid.loader.js" type="text/javascript"></script> -->
        
        <script src="js/ajaxupload.3.5.js" type="text/javascript"></script>
        <script src="js/grid/core/grid.loader.js" type="text/javascript"></script>
        
        <script src="jquery.jqGrid-4.1.1/plugins/jquery.contextmenu.js" type="text/javascript"></script>
        
        <script src="js/grid/core/language/jqDnR.js" type="text/javascript"></script>
	    <script src="js/grid/core/language/jqModal.js" type="text/javascript"></script> 
		
		<script type="text/javascript" src="js/jquery.address-1.4.min.js"></script>
		<script type="text/javascript" src="js/CommonFunctions.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="css/home-page-tab-style.css" />
		<script type="text/javascript" src="js/dashboard-content.js"></script>
		<script src="js/hcharts/charts.js" type="text/javascript" ></script>
		<script src="js/hcharts/charts-more.js" type="text/javascript" ></script>
		<script src="js/jquery.printElement.min.js" type="text/javascript" ></script>
		<!-- <tiles:getAsString name="title"/> -->
        <title>Perfex</title>
       
    </head>
    <body style="height:100%;" ondragstart="return false" draggable="false"
        ondragenter="event.dataTransfer.dropEffect='none'; event.stopPropagation(); event.preventDefault();"  
        ondragover="event.dataTransfer.dropEffect='none';event.stopPropagation(); event.preventDefault();"  
        ondrop="event.dataTransfer.dropEffect='none';event.stopPropagation(); event.preventDefault();"
    >
       

      


 <div id="filterPanel">
<div id="filterShowHide" class="filterpanel">

<div id="preLoadFilter" class="tpm-loading"></div>
<div id="loadFilter"></div>
</div>
</div>    

<div id="homePageHiddenHtml" style="display:none;width:0px;height:0px"  >
</div>

<div id="settings-panel">
<ul>
<li><a id="switchUser" href="#"><img src="images/menu-icon/dashboard.png"/><span><label id="lblSwitchuser" onClick="fnSwitchUser()"> Switch Role </label></span></a></li>
<!--<li><a href="#"><img src="images/menu-icon/update.png"/><span>Update Application</span></a></li>-->
<li><a href="#"><img src="images/menu-icon/conn.png"/><span>Configuration</span></a></li>
<li ><a href="#" id="chngpwd" onclick="chngpwd();"><img src="images/menu-icon/change-password.png" /><span>Change Password</span></a></li>
<li><a href="#"><img src="images/menu-icon/themes.png" /><span>Change Themes</span></a></li>
<li><a id="logout" href="logout.userLogin"><img src="images/menu-icon/logout.png" id="imgLogOut" name="imgLogOut"/><span>Log Out</span></a></li>

</ul>
	
</div><!-- /settings-panel -->
<!-- /settings-panel -->

            
<div class="dashboard_content_div" style="z-index: 1000; position: absolute;_position: absolute;left:1; display: none;background: #F1F3F5;border:solid 1px #C1C1C1;">
	<div class="sub-header" style="margin-top:1;">
		<span id="hideAccdrn" style="position:absolute;" >
			<label style='vertical-align:6'>Pillars</label>
		
		</span>
		<div id="lbldshbrd" style='font-size: 17px;' align="center"><label id="lblpillarName" style="font-size: 17px;"></label>Dash Board</div>
			<span class="clos_dashboard_div " style="cursor: pointer; float: right; margin-right: 0px;margin-top:-23;">
				<img id="closeBtnDPDwn" src="images/close-butt1.png" style="float:right;height:22 ; "/>
			</span>
	</div>
	<div class="clearfix"></div>
	<div id="loadDashBoard"></div>
</div>

<div class="alert_content_div"
	style="z-index: 1000; position: absolute;_position: absolute;left:1; display: none;border:solid 1px #C1C1C1;">
<div id="lblalert" style='text-align:center;margin-top:0;' class='sub-header' align="center"><label id="" style="font-size: 17px;">Inbox</label></div>
			<span class="clos_alert_div " style="cursor: pointer; float: right; margin-right: 0px;margin-top:-26;">
				<img id="closeBtnDPDwnalert" src="images/close-butt1.png" style="float:right;height:22 ; "/>
			</span>	

<div class="clearfix"></div>
<div id="alert_content" style="overflow:auto; height:97%;"></div>
</div>	  
	   
<!--/****Remider Sliding Div ****/-->
<div class="reminder_content_div"
	style="z-index: 899; position: absolute; _position: absolute;left:1;display: none;border:solid 1px #C1C1C1;">
	
<div class="clearfix"></div>
<div id="reminder_header" style="text-align:center;position:absolute;width:99.5%;top:-1.5%;z-index:-10;font-size: 14px;" class='sub-header'>
<label style="font-size: 17px;">Reminder</label></div>
	<span class="clos_reminder_div " style="cursor: pointer; ">
				<img id="closeBtnDPDwnreminder" src="images/close-butt1.png" style="float:right;height:22 ; "/>
			</span>	

<div id="reminder_content" style="overflow:auto; height:97%;"></div>
</div>
<!--	   <div id = "LoadContenpwd" align="center"></div>-->
        <div id="mainlayout" class="easyui-layout"  style="width:100%;height:100%;float:left">
            <div region="north" border="false" class="page-header " style="height:90.3px">
              <%--   <tiles:insertAttribute name="header" /> --%>
              <jsp:include page="/templates/fragments/banner.jsp" />  <div>After Header</div>
<!--	                <div style="" class="headDividedImage">-->
<!--	                   <table width="100%" height="100%" rules="all" border="1">-->
<!--	            		<tr>-->
<!--	            			<td width="40%" class="head_img0">test</td>-->
<!--	            			<td width="30%" class="head_img1">test</td>-->
<!--	            			<td width="30%" class="head_img2">test</td>-->
<!--	            		</tr>-->
<!--	            	</table>-->
<!--	             </div>-->
            </div>
            <div region="west" id='wstmenu' split="true" title="Menu" class="layout-button-left" style="width:300px;padding:10px;"> 
            	<input type="hidden" id="hiddenUrl"/>
            	<input type="hidden" id="submitForm"/>
            	<input type="hidden" id="prevUrl"/>
            	<input type="hidden" id="prevSubmitForm"/>
                <input type="hidden" id="filterUrl"/>
                <input type="hidden" id="loadFormMode"/>
                <input type="hidden" id="loadFormId"/>
                <input type="hidden" id="hdnMstFrmLink"/>
                 <input type="hidden" id="hdnMstFrmUrl"/>
                 <input type="hidden" id="hdnbaseFrmUrl"/>
                <input type="hidden" id="loadCallBackFrmId"/>
                <%-- <tiles:insertAttribute name="menu" /> --%>
                <%-- <jsp:include page="/templates/fragments/common_menu.jsp" />  --%>
                <jsp:include page="/tiles/common_menu.jsp" />
            </div>
				<div region="center" title="Home" id="home_center" style="border: none;width:0;"  >
				<div style="width:90%; " >        
				<div id="zomImg" style="position:absolute; height:60%;border:double 5px #40B0E3;width:60%;left:5%;display:none;background-color:#F2F4F4;z-index:1000;">
								<img id="imgZoom" src="" height='100%' width="100%"/>
							</div>
					<div id ="preLoadContent" class="tpm-loading"> </div>
					<div id = "LoadContent" >
						<div id = "LoadContent_1" >
							<%-- <tiles:insertAttribute name="body" /> --%>
							<%-- <jsp:include page="/templates/fragments/home_body.jsp" /> --%>
						</div>
					</div>
				 </div>   
				
				<!--  <div title="Switch User" id="divRole" class="flPopUpBox" style="margin-top:10%;marign-left:10%;display:none;width:50%;height:50%;">
					<div>
						<label> New Role </label>
					</div> 
					<input id="cmbEmployeRoles" name="cmbEmployeRoles" class="easyui-combobox"  style="width:230px" value="" <c:out value = ""/> >
				</div>
				 -->
				</div>    
				<div >
				<div id="passwordSlideContainer">
					
				<div id="pwdAlertContent">
				
					</div>
					<div id="pwdAlertLink">
						<div style="position:absolute;right:0;top:0;font-weight:bold;font-size:1.4em;"><span id="spnClose" style="cursor:pointer;" onclick="pwdAlertContentShowHide();">X</span></div>
						<div style="position:absolute;left:4%;"><span><a href="#" id="chngpwd" onclick="chngpwd();" ><span>Change Password</span></a></span></div>
					</div>
				</div>
             <div id="footerSlideContainer">
				<div id="footerSlideButton" class=" " style="display:none;"></div>
					<div id="footerSlideContent">
						<div id="footerSlideText" >
							<div id="dispErr" class="tpm-errorMsg " style="top:-22;width:85%;position:relative;">
							
							</div>
						
						</div>
						
					</div>
						
				</div>
<div id="righttool" class="border_rtTool" style="clear: both;">
		<!--test right tool	  -->
	  <script>
jQuery(document).ready(function(){
jQuery('#slid-top-icon').click(function(){
	jQuery(this).removeClass('leftArrow');	
	jQuery(this).addClass('rightArrow');
	});
		});
</script>

<div id="maincontent" style="z-index: 1;">
<div id="tab_container" style="display: block;content: inline;height:450px;">

<!-- <span class="rotate-vertically" style="font-weight:bold;position: relative;top: 3em;" >Dashborad</span>  --> 
<!--<img id="filter_tab" class="filtertab" src="images/tab-menu/side-filter-icon.jpg" title="2" style="height:18.3%;width:55px"  >-->
<div id="olapdashbrd"  >
<img id="dashboard_tab" class="dashboardtab"  src="images/tab-menu/dashboard.png" title="4"  /> 

<!--<div   class="side nav" alt="" src="images/tab-menu/dashboard.png" title="1"  style="margin-left:-1px;">
<div>
<div class="tool_hdng" ><span class="arrRight"></span><span>DashBoard</span>
</div>
 	Under development  	
	<div id="divDashBoardPillars"> 
	  	<a href="#" class="clickme">Chart1</a>
		<a href="#" class="clickme">Chart2</a>
		<a href="#" class="clickme">Chart3</a>
		<a href="#" class="clickme">Chart4</a>
		<a href="#" class="clickme">Chart5</a>
		
	</div>
</div>
</div>	
--><div id="olapfilter"  >
<img id="filter_tab" class="filtertab" src="images/tab-menu/newt/filter.png" title="2"   >
</div>
<div id="olapreminder" >
<div   class="side nav" alt="" src="images/tab-menu/newt/reminder.png" title="3" >
<div class="tool_hdng" ><span class="arrRight"></span><span>Reminder</span></div>
	
<!-- Under development --> 	
	<a href="#" class="clickme">Chart1</a>
	<a href="#" class="clickme">Chart2</a>
	<a href="#" class="clickme">Chart3</a>
	<a href="#" class="clickme">Chart4</a>
	<a href="#" class="clickme">Chart5</a>
		
</div>
</div>
<div id="olapalert">
 	<img id="alert_tab" class="alerttab"  src="images/tab-menu/newt/inbox.jpg" title="4"  />  
<!--  <div id="alert_tab" class=""  src="images/tab-menu/newt/alert.png" title="4"  >
 <div class="tool_hdng" ><span class="arrRight"></span><span>Alert</span></div>
    <a href="#" class="clickme">Chart1</a>
	<a href="#" class="clickme">Chart2</a>
	<a href="#" class="clickme">Chart3</a>
	<a href="#" class="clickme">Chart4</a>
	 
  </div>
  -->
</div>

<div id="olapqiklnk"  style="margin-top:25px;margin-left:3px;">

<div  class="side nav" alt="" src="images/tab-menu/newt/helpdesk11.png" title="5"  >
<div  class="tool_hdng" style="padding-top:5;padding-left:4;height:15;font-family:arial;" >
<span class="arrRight"></span><label style=""  >Contact&nbsp;Details.</label></div>
<div id='Qlinks' style='margin-top:2%;'></div>
 Perfex&nbsp;Contact&nbsp;No&nbsp;-&nbsp;2117&nbsp;
 Support&nbsp;mail&nbsp;-&nbsp;BCMTPM.SoftwareSupport@itc.in 	
</div>
</div>

<!--  
<div id="olapHelp" class="olapHelpTab" style="margin-top:25px;margin-left:-1px;"  >
<div  class="side nav" alt="" src="images/tab-menu/newt/help.jpeg" title="6"  >
<div  class="tool_hdng" style="padding-top:5;padding-left:4;height:10;font-family:arial;" >
<span class="arrRight"></span><label style="" > Help</label></div>
<div id='QHelp' style='margin-top:2%;'></div>
  
</div>
</div>
-->

<!-- <div id="olapHomePage" style="margin-top:25px;margin-left:-1px;">
<div  class="" alt=""  title="5"  >
<img id="homePage_tab"  src="images/tab-menu/newt/homepage.png" title=""  />  
<div id='HomePage' style='margin-top:2%;'></div>  
</div>
</div>
 -->

</div>

<!--<div id="olaphelp" style="margin-top:35px;margin-left:11px;cursor:pointer;">-->
<!--	<img id="helpLink" class="helpLink"  src="images/tab-menu/newt/help_ico.png" title="4"  />  -->
<div  id ="helpLink" class="" alt="" src="images/tab-menu/newt/help_ico.png" title="5"  ></div>
<!--<div id="DivHelp">-->
<!--<div id="loadHelp"></div>-->
<!--</div>-->
<!--</div>-->
</div>
<script type="text/javascript" src="js/jquery.sidecontent.js"></script>
<script type="text/javascript">
    jQuery(".side").sidecontent(true);
</script>
<!-- Just for web stats... not needed for the plugin -->
<script type="text/javascript">
    var myMasterUri = "";
    var myStatUrl = "";
    //alert(jQuery('#home_center').height());
</script>
	  
		<!--end of test	  -->
	  </div>
	 </div> 
	 </div> 
      
          <!--  <div region="east" split="true" title="Filters" style="width:700px;padding:10px;border-color: #a4a4a4;">
                
           -->    <!-- <div id ="preLoadFilter" class="tpm-loading"> </div>
                <div id="loadFilter"></div>
               -->     
          <!--       <tiles:insertAttribute name="extras" />
            </div>
            --> 
            <div region="south" border="false" style="height:35px;padding:10px;" >
                <%-- <tiles:insertAttribute name="footer" /> --%>
                <%-- <jsp:include page="/templates/fragments/credits.jsp" /> --%>
            </div>		
        
 <input type="hidden" id="userLoginid" value="${requestScope.loggedUserId }"/> 
<!--<div style="width: 4.1%; height: 100%; vertical-align: middle; float: right;">-->
<!---->
<!--<div style="clear: both;border-right: none; height: 90px; background-image: url('images/header-right-bg.png'); background-repeat: repeat-x; background-position: -1px -1px;">-->
<!---->
<!--<div style="float: right; margin-top: 8px; margin-right: 0px; margin-bottom: 26px; z-index: 10000">-->
<!--<img id="settings-link" alt="" src="images/menu-icon/setting.png" width="40px" height="36px">-->
<!--</div>-->


<!---->
<!--<div id="verticle_center" style="clear: both;height: 80%;background-image: url('images/favicon/RT_BG8.png'); background-repeat: no-repeat;">-->
<!---->
<!--<tiles:insertAttribute name="verticaltab-menu" />-->
<!---->
<!--	</div>-->
<!--<div border="false" id="tab-bttm-img" style="position:absolute; right:0px; bottom:0px; background-image: url('images/tab-menu/tab-bttm-img-bg.png'); background-repeat: repeat-x;">-->
<!---->
<!--<script type="text/javascript">-->
<!--function timedCount()-->
<!--{-->
<!---->
<!--var currentTime = new Date();-->
<!--var hours = currentTime.getHours();-->
<!--var minutes = currentTime.getMinutes();-->
<!--var am_pm = null;-->
<!--if (minutes < 10){-->
<!--minutes = "0" + minutes-->
<!--}	-->
<!--if(hours > 11){-->
<!--	am_pm = "PM";-->
<!--} else {-->
<!--	am_pm = "AM";-->
<!--}-->
<!--//alert(hours + ":" + minutes + ""+am_pm);-->
<!--jQuery("#bottom_time").text(hours + ":" + minutes);-->
<!---->
<!--t=setTimeout("timedCount()",1000);-->
<!--}-->
<!--timedCount();-->
<!--</script>-->
<!---->
<!--<div id="bottom_time" style="z-index:100000">12:00 PM</div>-->
</div>

</div>
<div id="modal_div" style="width: 100%; height: 0%; display: block; z-index: 9010;">
	<div class="save-loading" ></div>
</div>
<div id="modal_div1" style="width: 100%; display: none; z-index: 9010;">
	<div class="save-loadinggrid" ></div>
</div>
<div id="mstfrm_div" style="display: none;"></div>
<div id="DIV_FLMASK" style="display: none;"></div>
        
        
<script>
QueryLoader.selectorPreload = "body";
QueryLoader.init();
</script>
<div id="loadDialog"></div>
<div id="newMstFrm">
<div id="passwordChg" ></div>
   <div class="fl-header">
	   <span id="mstFrmHeader"> </span>
	   <span style="float:right;">
	  	<img id="btnCloseMstFrm" src="images/close-butt.png" />
	  </span>
  </div>
  <input type="hidden"  id="hdnPassword" value="false" />
     <c:if test="${sessionScope.userLogin.password  eq sessionScope.userLogin.defaultpwd}">
     	<script>
     		jQuery("#hdnPassword").val("true");
     	</script>
	 </c:if>
	 
	 
	<div id="preloadMstFrm"></div>
	<div id="loadMstFrm" class="loadFont"></div>
	<input type="hidden" id="isCommonFilterSlideOpen" />
	<input type="hidden" id="hdnPrevoiusHomUrl" value="" />
	<input type="hidden" id="hdnZ-IndexUrl" value="" />
	<input type="hidden" id="hdnZ-IndexHeader" value="" />
	<input type="hidden" id="hdnTeamLevel" value="1" />
	<input type="hidden" id="hdnBtnName" value="Download Prototype" />
	<input type="hidden" id="hdnshwhome" value="${requestScope.shwhome}" />
	<input type="hidden" id="hdnTenStepCurrentSep" />
	<input type="hidden" id="hdnSelectedFlid" name="hdnSelectedFlid" value="" />
	<input type="hidden" id="hdnSelectedCtqid" name="hdnSelectedCtqid" value=""/>
	<input type="hidden" id="hdnSelectedGradeSpec" name="hdnSelectedGradeSpec" value=""/>
	<input type="hidden" id="hdnSelectedProcessid" name="hdnSelectedProcessid" value=""/>
	<input type="hidden" id="hdnSelectedDate" name="hdnSelectedDate" value=""/>	
</div>
    </body>
</html>

