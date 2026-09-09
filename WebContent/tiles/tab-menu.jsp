<link rel="stylesheet" type="text/css" media="screen"
	href="css/home-page-tab-style.css" />
<script type="text/javascript" src="js/dashboard-content.js"></script>

<style type="text/css">
.side div.nav {
	list-style: none;
	display: block;
	width: 200px;
	position: relative;
	top: 0px;
	left: 0px;
	padding: 0px 0 0px 0;
	margin-top: 0px;
	-webkit-background-size: 50% 100%;
}

.side{
	margin: 0px 0 5px 0;
}

div.nav a {
	-webkit-transition: all 0.3s ease-out;
	background: #cbcbcb url(images/border.png) no-repeat;
	color: #174867;
	padding: 7px 15px 7px 15px;
	-webkit-border-top-right-radius: 10px;
	-webkit-border-bottom-right-radius: 10px;
	display: block;
	text-decoration: none;
	-webkit-box-shadow: 2px 2px 4px #888;
	width: 170px;
	margin-top: 10px;
}

div.nav a:hover {
	background: #ebebeb url(border.png) no-repeat;
	color: #67a5cd;
	padding: 7px 15px 7px 30px;
}

/* for fillter tab and panel*/
.filterpanel {
	position: absolute;
	right: 0;
	display: none;
	background: url("images/content-bg.png") no-repeat scroll 0 0
		transparent;
	color: #000000;
	border: 1px solid #a4a4a4;
	width: 95.9%;
	height: 500px;
	filter: alpha(opacity = 85);
	opacity: 1;
	z-index: 999;
	margin-right: 55px;
	/*
	-moz-border-radius-topleft: 20px;
-webkit-border-top-left-radius: 20px;
-moz-border-radius-bottomleft: 20px;
-webkit-border-bottom-left-radius: 20px;
*/
}

.filterpanel p {
	margin: 0 0 15px 0;
	padding: 0;
	color: #cccccc;
}

.filterpanel a,.filterpanel a:visited {
	margin: 0;
	padding: 0;
	color: #9FC54E;
	text-decoration: none;
	border-bottom: 1px solid #9FC54E;
}

.filterpanel a:hover,.filterpanel a:visited:hover {
	margin: 0;
	padding: 0;
	color: #ffffff;
	text-decoration: none;
	border-bottom: 1px solid #ffffff;
}

a.filtertab {
	position: absolute;
	text-decoration: none;
	right: 0;
	font-size: 16px;
	letter-spacing: -1px;
	font-family: verdana, helvetica, arial, sans-serif;
	color: #fff;
	font-weight: 700;
	background: #333333 url(images/tab-menu/screen-17_04.png) no-repeat;
	border: 1px solid #a4a4a4;
	display: block;
	margin-top: 15%;
	margin-right: 50px;
	z-index: 999;
	height: 139px;
	width: 30px;
}

a.filtertab:hover {
	background: #222222 url(images/tab-menu/screen-17_04.png) no-repeat;
	border: 1px solid #a4a4a4;
	display: block;
	height: 139px;
	width: 30px;
}

a.active.filtertab {
	background: #222222 url(images/tab-menu/screen-17_04.png) no-repeat;
}

a.filtertab,a.filtertab:hover {
	top: 80px;
}
</style>


<div id="maincontent" style="z-index: 1">
<div id="tab_container" style="display: block;content: inline;">

<div id="filterPanel">
<div class="filterpanel">&nbsp;
<div id="preLoadFilter" class="tpm-loading"></div>
<div id="loadFilter"></div>
</div>
</div>

<div id="slid-top-icon"></div>

<!-- <span class="rotate-vertically" style="font-weight:bold;position: relative;top: 3em;" >Dashborad</span>  --> 
<div  class="side nav" alt="" src="images/tab-menu/side-dashboard-icon.png" title="1" style="height:17%;width:55px" >
	<a href="#" class="clickme" id="equipment_failure" >Equipment&nbsp;Failure</a>
</div>

<img id="filter_tab" class="filtertab" src="images/fil-ter.png" title="2" style="height:18.3%;width:55px"  >

<div   class="side nav" alt="" src="images/quicklinks.png" title="3" style="height:17%;width:55px" >
 	Under development	
<!-- <a href="#" class="clickme">Chart1</a>
	<a href="#" class="clickme">Chart2</a>
	<a href="#" class="clickme">Chart3</a>
	<a href="#" class="clickme">Chart4</a>q
	<a href="#" class="clickme">Chart5</a>
-->		
</div>

<div  class="side nav" alt="" src="images/reminder.png" title="4" style="height:21%;width:55px" >
	Under development
<!-- 	<a href="#" class="clickme">Remainder 1</a>
	<a href="#" class="clickme">Remainder 1</a>
	<a href="#" class="clickme">Remainder 1</a>
	<a href="#" class="clickme">Remainder 1</a>
	<a href="#" class="clickme">Remainder 1</a>
-->	
</div>

<div  class="side nav" alt="" src="images/alert.png" title="5" style="height:18.3%;width:55px" >
<!-- 	<a href="#" class="clickme">Chart1</a>
	<a href="#" class="clickme">Chart2</a>
	<a href="#" class="clickme">Chart3</a>
	<a href="#" class="clickme">Chart4</a>
	<a href="#" class="clickme">Chart5</a>
-->		
</div>


</div>
</div>



<div id="newMstFrm">
	<span>
		<span id="mstFrmHeader"></span>
		<span style="float:right"><img id="goIn" name="goIn" src="images/tab-menu/go_in.ico" style="width:30px"/></span>
	</span>
	<div id="preloadMstFrm"></div>
	<div id="loadMstFrm" class="loadFont"></div>
</div>
<script type="text/javascript" src="js/jquery.sidecontent.js"></script>
<script type="text/javascript">
    jQuery(".side").sidecontent();
    jQuery( "#goIn" ).click(function() {
    	jQuery("#newMstFrm").hide(0);
    	jQuery("#mstFrmHeader").html('');
    });
</script>
<!-- Just for web stats... not needed for the plugin -->
<script type="text/javascript">
    var myMasterUri = "";
    var myStatUrl = "";
    //alert(jQuery('#home_center').height());
</script>