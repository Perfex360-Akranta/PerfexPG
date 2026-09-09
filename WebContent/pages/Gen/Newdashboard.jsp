<meta http-equiv="X-UA-Compatible" content="IE=8" />
 <script type="text/javascript" src="js/json2.js"></script>
 <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />    

<!-- Bootstrap core CSS-->
 <link href="js/bootstrap.min.css" rel="stylesheet"> 

    <!-- Custom fonts for this template-->
    <link href="css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="css/dataTables.bootstrap4.css" rel="stylesheet">

 

 <script type="text/javascript" src="js/json2.js" ></script>
    <!-- Custom styles for this template-->
   
<script type="text/javascript" src="js/loader.js" ></script>
 <style>
 ul.breadcrumb{
padding:10px 18px;
list-style:none;
background-color:#666699;
} 
 
 ul.breadcrumb li{
display:inline;
font-size:18px;
} 

 ul.breadcrumb li+li:before{
padding:10px;
content:"/";
color:green;
} 

 ul.breadcrumb li a{
color:white;
text-decoration:underline;
} 
 
/* Dashboard Related CSS*/
.dashboard-row {
    -moz-box-direction: normal;
    -moz-box-flex: 1;
    -moz-box-orient: horizontal;

    height: 400px;
}
 .dashboard-cell {
   -moz-box-flex: 2;
  
    cursor: default;
  
    float: left;
    height: 450px;
    max-height:450px;
    margin: 4px;
    overflow: hidden;
   /* position: relative;
    text-align: center;*/
    width:360px;
   /*   width: 200px\9; */
    
   
} 
/*Breadcrum Related */

ul.breadcrumb{
padding:10px 18px;
list-style:none;
background-color:white;
} 
 
 ul.breadcrumb li{
display:inline;
font-size:18px;
} 

 ul.breadcrumb li+li:before{
padding:10px;
content:"/";
color:green;
} 

 ul.breadcrumb li a{
color:red;
text-decoration:underline;
} 

/* Header Menubar*/
.menu-bar{
 background: #666699;

 text-aligh:left:;
 }
  .bodybg{
  background-color:#220033; 
  }
 .menu-bar{
 background: #666699;

 text-aligh:left:;
 }
 
/*  .menu-bar ul{
 display:inline-flex;
 list-style:none;
 color:#fff;
 height:70px;
 } */
 
  .menu-bar ul li{
  width:125px;
  margin:15px;
  padding:0px;
  }
  
  .menu-bar ul li a{
  text-decoration:none;
  color:#fff;
  
  }
  .menu-bar .fa{
  margin-left:8px;
  }
  .active, .menu-bar ul li:hover{
/*   background:#ccccff; */
  border-radius:1px;
  
  }
 /* HEADER MENU BAR */
 
  .menu-bar-header{
 background: #666699;

 text-aligh:left:;
 }
 
  .menu-bar-header{
 display:inline-flex;
 list-style:none;
 color:#fff;
 height:70px;
 } 
 
  .menu-bar-header ul li{
  width:500px;
  margin:10px;
  font-size:20;
  padding:10px;
  }
  
   .menu-bar-header ul li a{
  text-decoration:none;
  color:#fff;
   
  }  
  .menu-bar-header .fa{
  margin-left:8px;
  }
  .active, .menu-bar-header ul li:hover{
 
  border-radius:3px;
  }  
  .label{
 
  font-size:18;
 
   color:#fff;
  }
  
  
  /* Accordian Related */
  #accordian{
  list-style:none;
  padding:0 0 0 0;
  width:190px;
  }
 #accordian div{
 display:block;
 background-color:#1ece76;
 font-weight:bold;
 margin:1px;
 margin-left:-5px;
 cursor:pointer;
 padding:2 2 2 5px;
 -moz-border-radius:10px;
 -webkit-border-radius:10px;
 border-radius:10px;
 }
 #accordian ul{
  list-style:none;
  margin-left:0px;
  /* padding:0 0 0 0; */
 }
  #accordian ul{
  display:none;
  width:100px;
  font-size:12px;
 }
 
   #accordian ul li{
font-weight:bold;
cursor:pointer;
margin-left:0px;
 padding:0 0 0 0px;
 }
 
 #accordian a{
   text-decoration:none;
 }
  #accordian a:hover{
  
 /*  text-decoration:underline; */
  
 
 }


 #accordian1{
  list-style:none;
  padding:0 0 0 0;
  width:190px;
  }
 #accordian1 div{
 display:block;
 background-color:#1ece76;
 font-weight:bold;
 margin:1px;
 margin-left:-5px;
 cursor:pointer;
 padding:2 2 2 5px;
 -moz-border-radius:10px;
 -webkit-border-radius:10px;
 border-radius:10px;
 }
 #accordian1 ul{
  list-style:none;
  margin-left:0px;
  /* padding:0 0 0 0; */
 }
  #accordian1 ul{
  display:none;
  width:100px;
  font-size:12px;
 }
 
   #accordian1 ul li{
font-weight:bold;
cursor:pointer;
margin-left:0px;
 padding:0 0 0 0px;
 }
 
 #accordian1 a{
   text-decoration:none;
 }
  #accordian1 a:hover{
  
 /*  text-decoration:underline; */
  
 
 }
 
  #accordian2{
  list-style:none;
  padding:0 0 0 0;
  width:190px;
  }
 #accordian2 div{
 display:block;
 background-color:#1ece76;
 font-weight:bold;
 margin:1px;
 margin-left:-5px;
 cursor:pointer;
 padding:2 2 2 5px;
 -moz-border-radius:10px;
 -webkit-border-radius:10px;
 border-radius:10px;
 }
 #accordian2 ul{
  list-style:none;
  margin-left:0px;
  /* padding:0 0 0 0; */
 }
  #accordian2 ul{
  display:none;
  width:100px;
  font-size:12px;
 }
 
   #accordian2 ul li{
font-weight:bold;
cursor:pointer;
margin-left:0px;
 padding:0 0 0 0px;
 }
 
 #accordian2 a{
   text-decoration:none;
 }
  #accordian2 a:hover{
  
 /*  text-decoration:underline; */
  
 
 }
 </style>
<script type="text/javascript">
var mode=jQuery("#mode").val();
jQuery(document).ready(function(){
jQuery( "#accordian1" ).hide();	
initialiseForm('frmAdminPanel');
jQuery( "#header1" ).hide();
jQuery( "#header2" ).hide();
jQuery( "#header3" ).hide();
jQuery( "#header4" ).hide();
jQuery( "#header5" ).hide();
jQuery( "#header6" ).hide();
jQuery( "#functionallocn" ).hide();
formatDateBox('dteFromdate','DDD-MM-YYYY');
formatDateBox('dteTodate','DDD-MM-YYYY');
	fillComboBox("frmAdminPanel","cmbpact","cellCombo.commonFilter");
	fillComboBox("frmAdminPanel","cmbaet","sectionCombo.commonFilter");
	 fillComboBox("frmAdminPanel", "cmbLocnid", "location.commonFilter");
	var flid=jQuery('#hdnflid').val();
	var FYearStart=jQuery("#hdnFYearStart").val();
	jQuery("#chkFinance").attr("checked",true);
	disableField("frmAdminPanel","dteFromdate");		
	disableField("frmAdminPanel","dteTodate");	
	//alert(FYearStart);
	////alert(flid);
	 var factId = jQuery("#frmAdminPanel input[id='factory']").val();
		var sectionId = jQuery("#frmAdminPanel input[id='section']").val();
		var cellId = jQuery("#frmAdminPanel input[id='cell']").val();
		var machId = jQuery("#frmAdminPanel input[id='machine']").val();
		var flid = jQuery("#frmAdminPanel input[id='flid']").val();
	
	 if(flid !=null)
	    {	 
	//////////////////////////alert("Loadfn");
	    	loadFunctionalLocation("NewMocfunLocation","functionalLoc.apdb","NewMOCfunLocationValues","frmAdminPanel","&flid="+flid);
	    	reloadCombo("frmAdminPanel","cmbpact","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
	    	//reloadCombo("frprocessAjaxmMocProject","cmbaet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
	 	   
	    }
	    else
	 	   {
	    	////////////////////////////alert("ELSELoadfn");
	 	   loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMocfunLocationValues","frmAdminPanel","");
	 	   }
	/* fillComboBox("frmAdminPanel","cmbaet","sectionCombo.commonFilter?&flid="+flid); */
	//fillComboBox("frmAdminPanel","cmbLocnid","combo_location.emp" );
    fillComboBox("frmAdminPanel", "cmbLocnid", "location.commonFilter");
	
	disableField("frmAdminPanel","cmbLocnid");		
	
	//jQuery(".dialog-content panel-body panel-body-noheader panel-body-noborder").hide();
 

//Disable
//jQuery('#fieldId').removeAttr('disabled'); //Enable
	//jQuery( ".window-shadow" ).hide(); 
	
	//jQuery('#home_center').css("background-color","#220033");

});

jQuery("#chkFinance").click(function(){
	if(jQuery("#chkFinance").is(":checked")==true){
	//	alert("Click");
		disableField("frmAdminPanel","dteFromdate");		
		disableField("frmAdminPanel","dteTodate");
		jQuery("#dteFromdate").datebox("clear");
		jQuery("#dteTodate").datebox("clear");
		}
	else{
		//  alert("Else");
    	  enableFields("dteFromdate");
	      enableFields("dteTodate");
	}
});

/* function frmAdminPaneldteFromdate_onSelect(record){
	alert("inside")
	} */

	 function dteFromdate_onSelect(date)
	{
		
		jQuery('#chkFinance').attr('checked',false);
	}
	
	 function dteTodate_onSelect(date)
		{
			
			jQuery('#chkFinance').attr('checked',false);
		}
function DmtList(){
	//alert("Hieee");
}
  
function frmAdminPanel_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var factId = "";
var pbuId=keyIds.pbuId;
//////////////////////////////////alert("pbuid"+pbuId);
	var sectId = keyIds.sectId;	
	//alert("sectId"+sectId);
	var cellId=keyIds.cellId;
	var sbuId=keyIds.sbuId;
	//alert(sbuId);
	var LocnId=keyIds.locnId;
	//alert(LocnId)
	var locnId = jQuery("#frmAdminPanel input[id='location']").val();
	var flid = jQuery("#frmAdminPanel input[id='flid']").val();
	setFieldValue('hdnflid',flid);
	setFieldValue('cmbLocnid',locnId);
	setFieldValue('cmbpact',keyIds.cellId);
	setFieldValue('cmbaet',keyIds.sectId);
	setFieldValue('hdncellId',keyIds.cellId);
	setFieldValue('hdnsectionId',keyIds.sectId);
	setFieldValue('hdnlocnid',keyIds.sectId);
	setFieldValue('hdnsbuId',keyIds.sbuId);
	setFieldValue('hdnpbuId',keyIds.pbuId);
	readOnlyFields("cmbLocnid");
	//readOnlyFields("cmbaet");
		reloadCombo("frmAdminPanel","cmbLocnid","location.commonFilter?locnId="+locnId+"&flid="+flid);

	if (sectId != null){	
		//setFieldValue('cmbpact',keyIds.cellId);
		//////////////////////////////////alert("IF");
	reloadCombo("frmAdminPanel","cmbpact","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
}
	
	if (pbuId != null){	
	//	reloadCombo("frmAdminPanel","cmbaet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
		reloadCombo("frmAdminPanel","cmbaet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId);
		
	
	}

if (keyIds.cellId!="null"){	
	//////////////////////////////////alert("In CellIf")
	setFieldValue('cmbpact',keyIds.cellId);
	//	reloadCombo("frmAdminPanel","cmbFobdname","employeefield.ehsb?&cellId="+keyIds.cellId);
}
	setFunctionalLocWidth('frmFieldObservation','625px');
}

function JH(){
	jQuery("#accordian").hide();
	jQuery("#accordian1").show();
jQuery("#accordian1 > li > div").click(function(){

	if(false==jQuery(this).next().is(':visible')){
		jQuery('#accordian1 ul').slideUp(300);	
	}
	jQuery(this).next().slideToggle(300);
});
}

function KK(){
	jQuery("#accordian").hide();
	jQuery("#accordian1").hide();
	//alert("inside")
	jQuery("#accordian2").show();
	jQuery("#accordian2 > li > div").click(function(){

		if(false==jQuery(this).next().is(':visible')){
			jQuery('#accordian2 ul').slideUp(300);	
		}
		jQuery(this).next().slideToggle(300);
	});
	}



/* function KK(){
	//alert("kk");
	jQuery("#accordian > li > div").click(function(){

		if(false==jQuery(this).next().is(':visible')){
			jQuery('#accordian ul').slideUp(300);	
		}
		jQuery(this).next().slideToggle(300);
	});
} */


function ChartClass1(){
	//closePopUpDialoge("DashboardPopup");
	//LoadPopUp("DivDBChartsPopup","newdashboardCharts_input.dashboard",true,"100%","99%","1%","-0%", "empKpiOk_Callback","Dashboard-Charts",false);
//navigateToNextForm("newdashboardCharts_input.dashboard","","","");	
	//	navigateToNextForm("newdashboardCharts_input.dashboard","","","");

	//  jQuery("#abnCumulativeGrid").jqGrid('setGridState', 'hidden');
	jQuery( "#header1" ).hide();
jQuery( "#header2" ).hide();
jQuery( "#header3" ).hide();
jQuery( "#header4" ).hide();
jQuery( "#header5" ).hide();
jQuery( "#header6" ).hide();
	 jQuery("#bread").hide();
	 jQuery("#functionallocn").hide();
	 jQuery("#dashboard-grid").show();
	 jQuery("#DashboardTableNew").hide();
	 jQuery("#DashboardTableNew1").hide();
	 jQuery("#DashboardTableNew2").hide();
	 jQuery("#DashboardTableNew3").hide();
	 jQuery("#DashboardTableNew4").hide();
	 var pillar="NDB";
	
	processAjaxCalls("getRelatedData.dashboard","pillar="+pillar,"selectedPillarDsh_onsuccesscallback");

	}

function ChartClass2(){
	//closePopUpDialoge("DashboardPopup");
	//LoadPopUp("DivDBChartsPopup","newdashboardCharts_input.dashboard",true,"100%","99%","1%","-0%", "empKpiOk_Callback","Dashboard-Charts",false);
//navigateToNextForm("newdashboardCharts_input.dashboard","","","");	
	//	navigateToNextForm("newdashboardCharts_input.dashboard","","","");

	//  jQuery("#abnCumulativeGrid").jqGrid('setGridState', 'hidden');
	jQuery( "#header1" ).hide();
jQuery( "#header2" ).hide();
jQuery( "#header3" ).hide();
jQuery( "#header4" ).hide();
jQuery( "#header5" ).hide();
jQuery( "#header6" ).hide();
	 jQuery("#bread").hide();
	 jQuery("#functionallocn").hide();
	 jQuery("#dashboard-grid").show();
	 jQuery("#DashboardTableNew").hide();
	 jQuery("#DashboardTableNew1").hide();
	 jQuery("#DashboardTableNew2").hide();
	 jQuery("#DashboardTableNew3").hide();
	 jQuery("#DashboardTableNew4").hide();
	 var pillar="NDK";
	
	processAjaxCalls("getRelatedData.dashboard","pillar="+pillar,"selectedPillarDsh_onsuccesscallback");

	}
	



function TableClass1(){
	jQuery( "#header1" ).show();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).show();
	jQuery( "#header7" ).hide();
	 jQuery("#header8").hide();
	 jQuery("#header9").hide();
	 jQuery("#header10").hide();
    jQuery("#DashboardTableNew1").show();
    jQuery("#DashboardTableNew2").hide();
    jQuery("#DashboardTableNew2").hide();
    jQuery("#DashboardTableNew3").hide();
    jQuery("#DashboardTableNew4").hide();
    jQuery("#DashboardTableNew5").hide();
    jQuery("#DashboardTableNew6").hide();
    jQuery("#DashboardTableNew7").hide();
    jQuery("#DashboardTableNew8").hide();
    jQuery("#DashboardTableNew9").hide();
    jQuery("#DashboardTableNew10").hide();
    jQuery("#bread").hide();
    jQuery("#functionallocn").hide();
    jQuery("#dashboard-grid").hide();
    
 var flid=jQuery('#hdnflid').val();
 var firstClick="Y";
var tableCaption = "EHS Metrics";
var drillFlag="f";
var type="SAFE";
//var 	Finance;
//var FromDate=jQuery("#dteFromdate").datebox('getValue');
//var FromDate=getFieldValue("#dteFromdate");
/* var FromDate= getFieldValue("dteFromdate", "frmAdminPanel");
alert(FromDate);
var ToDate=jQuery("#dteTodate").datebox('getValue');
alert(ToDate); */

var FromDate=jQuery("#dteFromdate").datebox('getValue');
var ToDate=jQuery("#dteTodate").datebox('getValue');

if(FromDate.length==10){
if(FromDate.charAt(0)=="1"){
	FromDate=FromDate.replace(FromDate.charAt(0),"01");
}
else if(FromDate.charAt(0)=="2"){
	FromDate=FromDate.replace(FromDate.charAt(0),"02");
}
else if(FromDate.charAt(0)=="3"){
	FromDate=FromDate.replace(FromDate.charAt(0),"03");
}
else if(FromDate.charAt(0)=="4"){
	FromDate=FromDate.replace(FromDate.charAt(0),"04");
}
else if(FromDate.charAt(0)=="5"){
	FromDate=FromDate.replace(FromDate.charAt(0),"05");
}
else if(FromDate.charAt(0)=="6"){
	FromDate=FromDate.replace(FromDate.charAt(0),"06");
}
else if(FromDate.charAt(0)=="7"){
	FromDate=FromDate.replace(FromDate.charAt(0),"07");
}
else if(FromDate.charAt(0)=="8"){
	FromDate=FromDate.replace(FromDate.charAt(0),"08");
}
else if(FromDate.charAt(0)=="9"){
	FromDate=FromDate.replace(FromDate.charAt(0),"09");
}
}


if(ToDate.length==10){
if(ToDate.charAt(0)=="1"){
	ToDate=ToDate.replace(ToDate.charAt(0),"01");
}
else if(ToDate.charAt(0)=="2"){
	ToDate=ToDate.replace(ToDate.charAt(0),"02");
}
else if(ToDate.charAt(0)=="3"){
	ToDate=ToDate.replace(ToDate.charAt(0),"03");

}
else if(ToDate.charAt(0)=="4"){
	ToDate=ToDate.replace(ToDate.charAt(0),"04");

}
else if(ToDate.charAt(0)=="5"){
	ToDate=ToDate.replace(ToDate.charAt(0),"05");

}
else if(ToDate.charAt(0)=="6"){
	ToDate=ToDate.replace(ToDate.charAt(0),"06");

}
else if(ToDate.charAt(0)=="7"){
	ToDate=ToDate.replace(ToDate.charAt(0),"07");

}
else if(ToDate.charAt(0)=="8"){
	ToDate=ToDate.replace(ToDate.charAt(0),"08");

}
else if(ToDate.charAt(0)=="9"){
	ToDate=ToDate.replace(ToDate.charAt(0),"09");
}
/* alert("FromDate:"+FromDate);
alert("ToDate:"+ToDate); */	

}

if(jQuery("#chkFinance").is(":checked")==true){
   var Finance="Y";
}
else{
	 var Finance="N";	
}
processGridnew("EHSMetrics_input.apdb","q=2&firstClick="+firstClick+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid1","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
return true;	
}

function TableClass2(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).show();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).hide();
	jQuery( "#header7" ).hide();
	 jQuery("#header8").hide();
	 jQuery("#header9").hide();
	 jQuery("#header10").hide();
	 jQuery("#DashboardTableNew2").show();
	 jQuery("#DashboardTableNew1").hide();
	    jQuery("#DashboardTableNew3").hide();
	    jQuery("#DashboardTableNew4").hide();
	    jQuery("#DashboardTableNew5").hide();
	    jQuery("#DashboardTableNew6").hide();
	    jQuery("#DashboardTableNew7").hide();
	    jQuery("#DashboardTableNew8").hide();
	    jQuery("#DashboardTableNew9").hide();
	    jQuery("#DashboardTableNew10").hide();
	    jQuery("#functionallocn").hide();
	    jQuery("#bread").hide();
    jQuery("#dashboard-grid").hide();
    var flid=jQuery('#hdnflid').val();
	//alert(flid);
	  var FromDate="01-Mar-2020";
	  var Todate=getCurrentDate();
	   ////alert(Todate);
	   var meetingtype="D";
	   var filterString;
	   filterString += '&type='+meetingtype;
	   filterString += '&FromDate='+FromDate+'&Todate='+Todate;
	   filterString +='&flid='+flid;
	   var DMTflid=jQuery("#cmbaet").combobox("getValue");
	   var FromDate=jQuery("#dteFromdate").datebox('getValue');
	   var ToDate=jQuery("#dteTodate").datebox('getValue');

	   if(FromDate.length==10){
	   if(FromDate.charAt(0)=="1"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"01");
	   }
	   else if(FromDate.charAt(0)=="2"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"02");
	   }
	   else if(FromDate.charAt(0)=="3"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"03");
	   }
	   else if(FromDate.charAt(0)=="4"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"04");
	   }
	   else if(FromDate.charAt(0)=="5"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"05");
	   }
	   else if(FromDate.charAt(0)=="6"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"06");
	   }
	   else if(FromDate.charAt(0)=="7"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"07");
	   }
	   else if(FromDate.charAt(0)=="8"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"08");
	   }
	   else if(FromDate.charAt(0)=="9"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"09");
	   }
	   }


	   if(ToDate.length==10){
	   if(ToDate.charAt(0)=="1"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"01");
	   }
	   else if(ToDate.charAt(0)=="2"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"02");
	   }
	   else if(ToDate.charAt(0)=="3"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"03");

	   }
	   else if(ToDate.charAt(0)=="4"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"04");

	   }
	   else if(ToDate.charAt(0)=="5"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"05");

	   }
	   else if(ToDate.charAt(0)=="6"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"06");

	   }
	   else if(ToDate.charAt(0)=="7"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"07");

	   }
	   else if(ToDate.charAt(0)=="8"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"08");

	   }
	   else if(ToDate.charAt(0)=="9"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"09");
	   }
	   /* alert("FromDate:"+FromDate);
	   alert("ToDate:"+ToDate); */	

	   }
	   if(jQuery("#chkFinance").is(":checked")==true){
	      var Finance="Y";
	     }
	    else{
	   	 var Finance="N";	
	   }
	  // alert(DMTflid);
	  // //alert(filterString);
	   var tableCaption = "Mom Attendance Month Wise Report";
	  processGridnew("momattendancemonthwisereport_input.apdb","q=2&meetingtype="+meetingtype+"&flid="+flid+"&DMTflid="+DMTflid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid2","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");				
	}
	
function TableClass3(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).show();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).hide();
	jQuery( "#header7" ).hide();
	 jQuery("#header8").hide();
	 jQuery("#header9").hide();
	 jQuery("#header10").hide();
	jQuery("#DashboardTableNew3").show();
	 jQuery("#DashboardTableNew1").hide();
	    jQuery("#DashboardTableNew2").hide();
	    jQuery("#DashboardTableNew4").hide();
	    jQuery("#DashboardTableNew5").hide();
	    jQuery("#DashboardTableNew6").hide();
	    jQuery("#DashboardTableNew7").hide();
	    jQuery("#DashboardTableNew8").hide();
	    jQuery("#DashboardTableNew9").hide();
	    jQuery("#DashboardTableNew10").hide();
    jQuery("#bread").hide();
    jQuery("#functionallocn").hide();
    jQuery("#dashboard-grid").hide();
	   var flid=jQuery('#hdnflid').val();
	  //alert(flid);
	  var FromDate="01-Mar-2020";
	  var Todate=getCurrentDate();
	   ////alert(Todate);
	   var meetingtype="J";
	   var FromDate=jQuery("#dteFromdate").datebox('getValue');
	   var ToDate=jQuery("#dteTodate").datebox('getValue');

	   if(FromDate.length==10){
	   if(FromDate.charAt(0)=="1"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"01");
	   }
	   else if(FromDate.charAt(0)=="2"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"02");
	   }
	   else if(FromDate.charAt(0)=="3"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"03");
	   }
	   else if(FromDate.charAt(0)=="4"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"04");
	   }
	   else if(FromDate.charAt(0)=="5"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"05");
	   }
	   else if(FromDate.charAt(0)=="6"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"06");
	   }
	   else if(FromDate.charAt(0)=="7"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"07");
	   }
	   else if(FromDate.charAt(0)=="8"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"08");
	   }
	   else if(FromDate.charAt(0)=="9"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"09");
	   }
	   }


	   if(ToDate.length==10){
	   if(ToDate.charAt(0)=="1"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"01");
	   }
	   else if(ToDate.charAt(0)=="2"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"02");
	   }
	   else if(ToDate.charAt(0)=="3"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"03");

	   }
	   else if(ToDate.charAt(0)=="4"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"04");

	   }
	   else if(ToDate.charAt(0)=="5"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"05");

	   }
	   else if(ToDate.charAt(0)=="6"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"06");

	   }
	   else if(ToDate.charAt(0)=="7"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"07");

	   }
	   else if(ToDate.charAt(0)=="8"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"08");

	   }
	   else if(ToDate.charAt(0)=="9"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"09");
	   }
	   /* alert("FromDate:"+FromDate);
	   alert("ToDate:"+ToDate); */	

	   }
	   if(jQuery("#chkFinance").is(":checked")==true){
	      var Finance="Y";
	     }
	    else{
	   	 var Finance="N";	
	   }
	   var filterString;
	   filterString += '&type='+meetingtype;
	   filterString += '&FromDate='+FromDate+'&Todate='+Todate;
	   filterString +='&flid='+flid;
	  // //alert(filterString);
	   var tableCaption = "Mom Attendance Month Wise Report";
		processGridnew("momattendancemonthwisereport_input.apdb","q=2&meetingtype="+meetingtype+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid3","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
		
	}
	
function TableClass4(){
	// alert("INSIDE"); 
	  var maintype="MOM";

		    	    jQuery( "#header1" ).hide();
		    	    jQuery( "#header2" ).hide();
		    	    jQuery( "#header3" ).hide();
		    	    jQuery( "#header4" ).show();
		    	    jQuery( "#header5" ).hide();
		    	    jQuery( "#header6" ).hide();
		    	    jQuery( "#header7" ).hide();
		    	    jQuery("#header8").hide();
		   		    jQuery("#header9").hide();
		   		    jQuery("#header10").hide();
		    	    jQuery("#DashboardTableNew1").hide();
		    	    jQuery("#DashboardTableNew2").hide();
		    	    jQuery("#DashboardTableNew3").hide();
		    	    jQuery("#DashboardTableNew4").show();
		    	    jQuery("#DashboardTableNew5").hide();
		    	    jQuery("#DashboardTableNew6").hide();
		    	    jQuery("#DashboardTableNew7").hide();
		    	    jQuery("#DashboardTableNew8").hide();
		    	    jQuery("#DashboardTableNew9").hide();
		    	    jQuery("#DashboardTableNew10").hide();
		    	    jQuery("#functionallocn").hide();
		    	    jQuery("#bread").hide();
		    	    jQuery("#dashboard-grid").hide();
		    	    var flid=jQuery('#hdnflid').val();
		    		// alert(flid);    	    
		    	    var FromDate=jQuery("#dteFromdate").datebox('getValue');
		    	    var ToDate=jQuery("#dteTodate").datebox('getValue');

		    	    if(FromDate.length==10){
		    	    if(FromDate.charAt(0)=="1"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"01");
		    	    }
		    	    else if(FromDate.charAt(0)=="2"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"02");
		    	    }
		    	    else if(FromDate.charAt(0)=="3"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"03");
		    	    }
		    	    else if(FromDate.charAt(0)=="4"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"04");
		    	    }
		    	    else if(FromDate.charAt(0)=="5"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"05");
		    	    }
		    	    else if(FromDate.charAt(0)=="6"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"06");
		    	    }
		    	    else if(FromDate.charAt(0)=="7"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"07");
		    	    }
		    	    else if(FromDate.charAt(0)=="8"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"08");
		    	    }
		    	    else if(FromDate.charAt(0)=="9"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"09");
		    	    }
		    	    }


		    	    if(ToDate.length==10){
		    	    if(ToDate.charAt(0)=="1"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"01");
		    	    }
		    	    else if(ToDate.charAt(0)=="2"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"02");
		    	    }
		    	    else if(ToDate.charAt(0)=="3"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"03");

		    	    }
		    	    else if(ToDate.charAt(0)=="4"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"04");

		    	    }
		    	    else if(ToDate.charAt(0)=="5"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"05");

		    	    }
		    	    else if(ToDate.charAt(0)=="6"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"06");

		    	    }
		    	    else if(ToDate.charAt(0)=="7"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"07");

		    	    }
		    	    else if(ToDate.charAt(0)=="8"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"08");

		    	    }
		    	    else if(ToDate.charAt(0)=="9"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"09");
		    	    }
		    	    /* alert("FromDate:"+FromDate);
		    	    alert("ToDate:"+ToDate); */	

		    	    }
		    		   if(jQuery("#chkFinance").is(":checked")==true){
		    		      var Finance="Y";
		    		     }
		    		    else{
		    		   	 var Finance="N";	
		    		   }
	processGridnew("JhauditActionPlanScore_input.apdb","q=2&maintype="+maintype+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid4","pager","","doubleClickGrid","","cumulativeGrid");		

}
function TableClass5(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).show();
	jQuery( "#header6" ).hide();
	jQuery( "#header7" ).hide();
	 jQuery("#header8").hide();
	 jQuery("#header9").hide();
	 jQuery("#header10").hide();
	    jQuery("#DashboardTableNew5").show();
	    jQuery("#DashboardTableNew1").hide();
	    jQuery("#DashboardTableNew2").hide();
	    jQuery("#DashboardTableNew3").hide();
	    jQuery("#DashboardTableNew4").hide();
	    jQuery("#DashboardTableNew6").hide();
	    jQuery("#DashboardTableNew7").hide();
	    jQuery("#DashboardTableNew8").hide();
	    jQuery("#DashboardTableNew9").hide();
	    jQuery("#DashboardTableNew10").hide();
	    jQuery("#bread").hide();
	    jQuery("#functionallocn").hide();
	    jQuery("#dashboard-grid").hide();
	    var flid=jQuery('#hdnflid').val();
	    var FromDate=jQuery("#dteFromdate").datebox('getValue');
	    var ToDate=jQuery("#dteTodate").datebox('getValue');

	    if(FromDate.length==10){
	    if(FromDate.charAt(0)=="1"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"01");
	    }
	    else if(FromDate.charAt(0)=="2"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"02");
	    }
	    else if(FromDate.charAt(0)=="3"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"03");
	    }
	    else if(FromDate.charAt(0)=="4"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"04");
	    }
	    else if(FromDate.charAt(0)=="5"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"05");
	    }
	    else if(FromDate.charAt(0)=="6"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"06");
	    }
	    else if(FromDate.charAt(0)=="7"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"07");
	    }
	    else if(FromDate.charAt(0)=="8"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"08");
	    }
	    else if(FromDate.charAt(0)=="9"){
	    	FromDate=FromDate.replace(FromDate.charAt(0),"09");
	    }
	    }


	    if(ToDate.length==10){
	    if(ToDate.charAt(0)=="1"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"01");
	    }
	    else if(ToDate.charAt(0)=="2"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"02");
	    }
	    else if(ToDate.charAt(0)=="3"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"03");

	    }
	    else if(ToDate.charAt(0)=="4"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"04");

	    }
	    else if(ToDate.charAt(0)=="5"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"05");

	    }
	    else if(ToDate.charAt(0)=="6"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"06");

	    }
	    else if(ToDate.charAt(0)=="7"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"07");

	    }
	    else if(ToDate.charAt(0)=="8"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"08");

	    }
	    else if(ToDate.charAt(0)=="9"){
	    	ToDate=ToDate.replace(ToDate.charAt(0),"09");
	    }
	    /* alert("FromDate:"+FromDate);
	    alert("ToDate:"+ToDate); */	

	    }
		   if(jQuery("#chkFinance").is(":checked")==true){
		      var Finance="Y";
		     }
		    else{
		   	 var Finance="N";	
		   }
	 var firstClick="Y";
	var tableCaption = "Kaizen Graphical Summary";
//	processGridnew(url,filterString,"impVscomp","pager",tableCaption,"doubleClickGrid","","impVscomp_loadComplete");		
	processGridnew("KaizenGraphicalSumm_input.apdb","q=2&firstClick="+firstClick+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid5","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
	return true;		
}


function TableClass6(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).show();
	jQuery( "#header7" ).hide();
	 jQuery("#header8").hide();
	 jQuery("#header9").hide();
	 jQuery("#header10").hide();
     jQuery("#DashboardTableNew1").hide();
	 jQuery("#DashboardTableNew2").hide();
	 jQuery("#DashboardTableNew3").hide();
	 jQuery("#DashboardTableNew4").hide();
     jQuery("#DashboardTableNew5").hide();
	 jQuery("#DashboardTableNew6").show();
	 jQuery("#DashboardTableNew7").hide();
	    jQuery("#DashboardTableNew8").hide();
	    jQuery("#DashboardTableNew9").hide();
	    jQuery("#DashboardTableNew10").hide();
    jQuery("#bread").hide();
    jQuery("#functionallocn").hide();
    jQuery("#dashboard-grid").hide();
	   var flid=jQuery('#hdnflid').val();
	   var FromDate=jQuery("#dteFromdate").datebox('getValue');
	   var ToDate=jQuery("#dteTodate").datebox('getValue');

	   if(FromDate.length==10){
	   if(FromDate.charAt(0)=="1"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"01");
	   }
	   else if(FromDate.charAt(0)=="2"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"02");
	   }
	   else if(FromDate.charAt(0)=="3"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"03");
	   }
	   else if(FromDate.charAt(0)=="4"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"04");
	   }
	   else if(FromDate.charAt(0)=="5"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"05");
	   }
	   else if(FromDate.charAt(0)=="6"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"06");
	   }
	   else if(FromDate.charAt(0)=="7"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"07");
	   }
	   else if(FromDate.charAt(0)=="8"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"08");
	   }
	   else if(FromDate.charAt(0)=="9"){
	   	FromDate=FromDate.replace(FromDate.charAt(0),"09");
	   }
	   }


	   if(ToDate.length==10){
	   if(ToDate.charAt(0)=="1"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"01");
	   }
	   else if(ToDate.charAt(0)=="2"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"02");
	   }
	   else if(ToDate.charAt(0)=="3"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"03");

	   }
	   else if(ToDate.charAt(0)=="4"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"04");

	   }
	   else if(ToDate.charAt(0)=="5"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"05");

	   }
	   else if(ToDate.charAt(0)=="6"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"06");

	   }
	   else if(ToDate.charAt(0)=="7"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"07");

	   }
	   else if(ToDate.charAt(0)=="8"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"08");

	   }
	   else if(ToDate.charAt(0)=="9"){
	   	ToDate=ToDate.replace(ToDate.charAt(0),"09");
	   }
	   /* alert("FromDate:"+FromDate);
	   alert("ToDate:"+ToDate); */	

	   }
	   if(jQuery("#chkFinance").is(":checked")==true){
	      var Finance="Y";
	     }
	    else{
	   	 var Finance="N";	
	   }
	   var firstClick="Y";
	 //  var filterString="firstClick=Y";
		////alert(filterString);
		var tableCaption = "Abnormality  Report";
		
		processGridnew("AbnCumulative_input.apdb","q=2&firstClick="+firstClick+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid6","pager",tableCaption,"doubleClickGrid","","cumulativeGridLoadComplete");		
		return true;	
	}

 
function TableClass7(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).hide();
	jQuery( "#header7" ).show();
	 jQuery("#header8").hide();
	 jQuery("#header9").hide();
	 jQuery("#header10").hide();
    jQuery("#DashboardTableNew7").show();
    jQuery("#DashboardTableNew1").hide();
    jQuery("#DashboardTableNew2").hide();
    jQuery("#DashboardTableNew3").hide();
    jQuery("#DashboardTableNew4").hide();
    jQuery("#DashboardTableNew5").hide();
    jQuery("#DashboardTableNew6").hide();
    jQuery("#DashboardTableNew8").hide();
    jQuery("#DashboardTableNew9").hide();
    jQuery("#DashboardTableNew10").hide();
    jQuery("#bread").hide();
    jQuery("#functionallocn").hide();
    jQuery("#dashboard-grid").hide();
 var firstClick="Y";
 var flid=jQuery('#hdnflid').val();
var tableCaption = "LMP Tools";
var drillFlag="f";
var FromDate=jQuery("#dteFromdate").datebox('getValue');
var ToDate=jQuery("#dteTodate").datebox('getValue');

if(FromDate.length==10){
if(FromDate.charAt(0)=="1"){
	FromDate=FromDate.replace(FromDate.charAt(0),"01");
}
else if(FromDate.charAt(0)=="2"){
	FromDate=FromDate.replace(FromDate.charAt(0),"02");
}
else if(FromDate.charAt(0)=="3"){
	FromDate=FromDate.replace(FromDate.charAt(0),"03");
}
else if(FromDate.charAt(0)=="4"){
	FromDate=FromDate.replace(FromDate.charAt(0),"04");
}
else if(FromDate.charAt(0)=="5"){
	FromDate=FromDate.replace(FromDate.charAt(0),"05");
}
else if(FromDate.charAt(0)=="6"){
	FromDate=FromDate.replace(FromDate.charAt(0),"06");
}
else if(FromDate.charAt(0)=="7"){
	FromDate=FromDate.replace(FromDate.charAt(0),"07");
}
else if(FromDate.charAt(0)=="8"){
	FromDate=FromDate.replace(FromDate.charAt(0),"08");
}
else if(FromDate.charAt(0)=="9"){
	FromDate=FromDate.replace(FromDate.charAt(0),"09");
}
}


if(ToDate.length==10){
if(ToDate.charAt(0)=="1"){
	ToDate=ToDate.replace(ToDate.charAt(0),"01");
}
else if(ToDate.charAt(0)=="2"){
	ToDate=ToDate.replace(ToDate.charAt(0),"02");
}
else if(ToDate.charAt(0)=="3"){
	ToDate=ToDate.replace(ToDate.charAt(0),"03");

}
else if(ToDate.charAt(0)=="4"){
	ToDate=ToDate.replace(ToDate.charAt(0),"04");

}
else if(ToDate.charAt(0)=="5"){
	ToDate=ToDate.replace(ToDate.charAt(0),"05");

}
else if(ToDate.charAt(0)=="6"){
	ToDate=ToDate.replace(ToDate.charAt(0),"06");

}
else if(ToDate.charAt(0)=="7"){
	ToDate=ToDate.replace(ToDate.charAt(0),"07");

}
else if(ToDate.charAt(0)=="8"){
	ToDate=ToDate.replace(ToDate.charAt(0),"08");

}
else if(ToDate.charAt(0)=="9"){
	ToDate=ToDate.replace(ToDate.charAt(0),"09");
}
/* alert("FromDate:"+FromDate);
alert("ToDate:"+ToDate); */	

}
if(jQuery("#chkFinance").is(":checked")==true){
   var Finance="Y";
  }
 else{
	 var Finance="N";	
}
/* var fromMonth="Mar-2020";
var toMonth="Oct-2020"; */

processGridnew("transactionSummary_input.apdb","q=2&firstClick="+firstClick+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid7","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
return true;	
}

function TableClass8(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).hide();
	jQuery( "#header7" ).hide();
	jQuery( "#header8" ).show();
	jQuery( "#header9" ).hide();
	 jQuery("#header10").hide();
    jQuery("#DashboardTableNew1").hide();
    jQuery("#DashboardTableNew2").hide();
    jQuery("#DashboardTableNew3").hide();
    jQuery("#DashboardTableNew4").hide();
    jQuery("#DashboardTableNew5").hide();
    jQuery("#DashboardTableNew6").hide();
    jQuery("#DashboardTableNew7").hide();
    jQuery("#DashboardTableNew9").hide();
    jQuery("#DashboardTableNew8").show();
    jQuery("#DashboardTableNew10").hide();
    jQuery("#bread").hide();
    jQuery("#functionallocn").hide();
    jQuery("#dashboard-grid").hide();
 var firstClick="Y";
 var flid=jQuery('#hdnflid').val();
var tableCaption = "MonthWise JH";

var meetingtype="D";
var DMTflid=jQuery("#cmbaet").combobox("getValue");
var FromDate=jQuery("#dteFromdate").datebox('getValue');
var ToDate=jQuery("#dteTodate").datebox('getValue');

if(FromDate.length==10){
if(FromDate.charAt(0)=="1"){
	FromDate=FromDate.replace(FromDate.charAt(0),"01");
}
else if(FromDate.charAt(0)=="2"){
	FromDate=FromDate.replace(FromDate.charAt(0),"02");
}
else if(FromDate.charAt(0)=="3"){
	FromDate=FromDate.replace(FromDate.charAt(0),"03");
}
else if(FromDate.charAt(0)=="4"){
	FromDate=FromDate.replace(FromDate.charAt(0),"04");
}
else if(FromDate.charAt(0)=="5"){
	FromDate=FromDate.replace(FromDate.charAt(0),"05");
}
else if(FromDate.charAt(0)=="6"){
	FromDate=FromDate.replace(FromDate.charAt(0),"06");
}
else if(FromDate.charAt(0)=="7"){
	FromDate=FromDate.replace(FromDate.charAt(0),"07");
}
else if(FromDate.charAt(0)=="8"){
	FromDate=FromDate.replace(FromDate.charAt(0),"08");
}
else if(FromDate.charAt(0)=="9"){
	FromDate=FromDate.replace(FromDate.charAt(0),"09");
}
}


if(ToDate.length==10){
if(ToDate.charAt(0)=="1"){
	ToDate=ToDate.replace(ToDate.charAt(0),"01");
}
else if(ToDate.charAt(0)=="2"){
	ToDate=ToDate.replace(ToDate.charAt(0),"02");
}
else if(ToDate.charAt(0)=="3"){
	ToDate=ToDate.replace(ToDate.charAt(0),"03");

}
else if(ToDate.charAt(0)=="4"){
	ToDate=ToDate.replace(ToDate.charAt(0),"04");

}
else if(ToDate.charAt(0)=="5"){
	ToDate=ToDate.replace(ToDate.charAt(0),"05");

}
else if(ToDate.charAt(0)=="6"){
	ToDate=ToDate.replace(ToDate.charAt(0),"06");

}
else if(ToDate.charAt(0)=="7"){
	ToDate=ToDate.replace(ToDate.charAt(0),"07");

}
else if(ToDate.charAt(0)=="8"){
	ToDate=ToDate.replace(ToDate.charAt(0),"08");

}
else if(ToDate.charAt(0)=="9"){
	ToDate=ToDate.replace(ToDate.charAt(0),"09");
}
/* alert("FromDate:"+FromDate);
alert("ToDate:"+ToDate); */	

}
if(jQuery("#chkFinance").is(":checked")==true){
   var Finance="Y";
  }
 else{
	 var Finance="N";	
}
processGridnew("momattendancemonthwisereportNew_input.apdb","q=2&firstClick="+firstClick+"&meetingtype="+meetingtype+"&flid="+flid+"&DMTflid="+DMTflid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid8","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
return true;	
}
function TableClass9(){
	jQuery( "#header1" ).hide();
	jQuery( "#header2" ).hide();
	jQuery( "#header3" ).hide();
	jQuery( "#header4" ).hide();
	jQuery( "#header5" ).hide();
	jQuery( "#header6" ).hide();
	jQuery( "#header7" ).hide();
	jQuery( "#header9" ).show();
	jQuery( "#header8" ).hide();
	 jQuery("#header10").hide();
    jQuery("#DashboardTableNew1").hide();
    jQuery("#DashboardTableNew2").hide();
    jQuery("#DashboardTableNew3").hide();
    jQuery("#DashboardTableNew4").hide();
    jQuery("#DashboardTableNew5").hide();
    jQuery("#DashboardTableNew6").hide();
    jQuery("#DashboardTableNew7").hide();
    jQuery("#DashboardTableNew8").hide();
    jQuery("#DashboardTableNew9").show();
    jQuery("#DashboardTableNew10").hide();
    jQuery("#bread").hide();
    jQuery("#functionallocn").hide();
    jQuery("#dashboard-grid").hide();
 var firstClick="Y";
 var flid=jQuery('#hdnflid').val();
var tableCaption = "DMT MonthWise";
var drillFlag="f";

var meetingtype="J";
/* var fromMonth="Mar-2020";
var toMonth="Oct-2020"; */
var FromDate=jQuery("#dteFromdate").datebox('getValue');
var ToDate=jQuery("#dteTodate").datebox('getValue');

if(FromDate.length==10){
if(FromDate.charAt(0)=="1"){
	FromDate=FromDate.replace(FromDate.charAt(0),"01");
}
else if(FromDate.charAt(0)=="2"){
	FromDate=FromDate.replace(FromDate.charAt(0),"02");
}
else if(FromDate.charAt(0)=="3"){
	FromDate=FromDate.replace(FromDate.charAt(0),"03");
}
else if(FromDate.charAt(0)=="4"){
	FromDate=FromDate.replace(FromDate.charAt(0),"04");
}
else if(FromDate.charAt(0)=="5"){
	FromDate=FromDate.replace(FromDate.charAt(0),"05");
}
else if(FromDate.charAt(0)=="6"){
	FromDate=FromDate.replace(FromDate.charAt(0),"06");
}
else if(FromDate.charAt(0)=="7"){
	FromDate=FromDate.replace(FromDate.charAt(0),"07");
}
else if(FromDate.charAt(0)=="8"){
	FromDate=FromDate.replace(FromDate.charAt(0),"08");
}
else if(FromDate.charAt(0)=="9"){
	FromDate=FromDate.replace(FromDate.charAt(0),"09");
}
}


if(ToDate.length==10){
if(ToDate.charAt(0)=="1"){
	ToDate=ToDate.replace(ToDate.charAt(0),"01");
}
else if(ToDate.charAt(0)=="2"){
	ToDate=ToDate.replace(ToDate.charAt(0),"02");
}
else if(ToDate.charAt(0)=="3"){
	ToDate=ToDate.replace(ToDate.charAt(0),"03");

}
else if(ToDate.charAt(0)=="4"){
	ToDate=ToDate.replace(ToDate.charAt(0),"04");

}
else if(ToDate.charAt(0)=="5"){
	ToDate=ToDate.replace(ToDate.charAt(0),"05");

}
else if(ToDate.charAt(0)=="6"){
	ToDate=ToDate.replace(ToDate.charAt(0),"06");

}
else if(ToDate.charAt(0)=="7"){
	ToDate=ToDate.replace(ToDate.charAt(0),"07");

}
else if(ToDate.charAt(0)=="8"){
	ToDate=ToDate.replace(ToDate.charAt(0),"08");

}
else if(ToDate.charAt(0)=="9"){
	ToDate=ToDate.replace(ToDate.charAt(0),"09");
}
/* alert("FromDate:"+FromDate);
alert("ToDate:"+ToDate); */	

}
if(jQuery("#chkFinance").is(":checked")==true){
   var Finance="Y";
  }
 else{
	 var Finance="N";	
}
processGridnew("momattendancemonthwisereportNew_input.apdb","q=2&firstClick="+firstClick+"&meetingtype="+meetingtype+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid9","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
return true;	
}

function TableClass10(){
	// alert("INSIDE"); 
	 // var ="MOM";
		var maintype=jQuery("#cmbpact").combobox("getValue");
       // alert(maintype);
		    	    jQuery( "#header1" ).hide();
		    	    jQuery( "#header2" ).hide();
		    	    jQuery( "#header3" ).hide();
		    	    jQuery( "#header4" ).hide();
		    	    jQuery( "#header5" ).hide();
		    	    jQuery( "#header6" ).hide();
		    	    jQuery( "#header7" ).hide();
		    	    jQuery("#header8").hide();
		   		    jQuery("#header9").hide();
		   		    jQuery("#header10").show();
		    	    jQuery("#DashboardTableNew1").hide();
		    	    jQuery("#DashboardTableNew2").hide();
		    	    jQuery("#DashboardTableNew3").hide();
		    	    jQuery("#DashboardTableNew4").hide();
		    	    jQuery("#DashboardTableNew5").hide();
		    	    jQuery("#DashboardTableNew6").hide();
		    	    jQuery("#DashboardTableNew7").hide();
		    	    jQuery("#DashboardTableNew8").hide();
		    	    jQuery("#DashboardTableNew9").hide();
		    	    jQuery("#DashboardTableNew10").show();

		    	    jQuery("#functionallocn").hide();
		    	    jQuery("#bread").hide();
		    	    jQuery("#dashboard-grid").hide();
		    	    var flid=jQuery('#hdnflid').val();
		    		// alert(flid);    	    
		    	    var FromDate=jQuery("#dteFromdate").datebox('getValue');
		    	    var ToDate=jQuery("#dteTodate").datebox('getValue');

		    	    if(FromDate.length==10){
		    	    if(FromDate.charAt(0)=="1"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"01");
		    	    }
		    	    else if(FromDate.charAt(0)=="2"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"02");
		    	    }
		    	    else if(FromDate.charAt(0)=="3"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"03");
		    	    }
		    	    else if(FromDate.charAt(0)=="4"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"04");
		    	    }
		    	    else if(FromDate.charAt(0)=="5"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"05");
		    	    }
		    	    else if(FromDate.charAt(0)=="6"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"06");
		    	    }
		    	    else if(FromDate.charAt(0)=="7"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"07");
		    	    }
		    	    else if(FromDate.charAt(0)=="8"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"08");
		    	    }
		    	    else if(FromDate.charAt(0)=="9"){
		    	    	FromDate=FromDate.replace(FromDate.charAt(0),"09");
		    	    }
		    	    }


		    	    if(ToDate.length==10){
		    	    if(ToDate.charAt(0)=="1"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"01");
		    	    }
		    	    else if(ToDate.charAt(0)=="2"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"02");
		    	    }
		    	    else if(ToDate.charAt(0)=="3"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"03");

		    	    }
		    	    else if(ToDate.charAt(0)=="4"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"04");

		    	    }
		    	    else if(ToDate.charAt(0)=="5"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"05");

		    	    }
		    	    else if(ToDate.charAt(0)=="6"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"06");

		    	    }
		    	    else if(ToDate.charAt(0)=="7"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"07");

		    	    }
		    	    else if(ToDate.charAt(0)=="8"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"08");

		    	    }
		    	    else if(ToDate.charAt(0)=="9"){
		    	    	ToDate=ToDate.replace(ToDate.charAt(0),"09");
		    	    }
		    	    /* alert("FromDate:"+FromDate);
		    	    alert("ToDate:"+ToDate); */	

		    	    }
		    		   if(jQuery("#chkFinance").is(":checked")==true){
		    		      var Finance="Y";
		    		     }
		    		    else{
		    		   	 var Finance="N";	
		    		   }
	    processGridnew("AbnormalityAegingReport_input.apdb","q=2&maintype="+maintype+"&flid="+flid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance,"abnCumulativeGrid10","pager","","doubleClickGrid","","cumulativeGrid");
 }

function Home(){
	//alert("Home");
	  jQuery("#DashboardTableNew4").hide();
	    jQuery("#bread").show();
	    jQuery("#dashboard-grid").hide();
	    jQuery("#DashboardTableNew1").hide();
		 jQuery("#DashboardTableNew2").hide();
		 jQuery("#DashboardTableNew3").hide();
		 jQuery("#DashboardTableNew5").hide();
		 jQuery("#DashboardTableNew6").hide();
		 jQuery("#DashboardTableNew7").hide();
		 jQuery("#DashboardTableNew8").hide();
		 jQuery("#DashboardTableNew9").hide();
 	     jQuery("#DashboardTableNew10").hide();
		 jQuery("#header1").hide();
		 jQuery("#header2").hide();
		 jQuery("#header3").hide();
		 jQuery("#header4").hide();
		 jQuery("#header5").hide();
		 jQuery("#header6").hide();
		 jQuery("#header7").hide();
		 jQuery("#header8").hide();
		 jQuery("#header9").hide();
}
function frmAdminPanelcmbpact_onSelect(record){
	////alert("after JH ");
	var dataStr="&sectId="+record.id;
	loadFunctionalLocation("NewMocfunLocation","functionalLoc.apdb","NewMOCfunLocationValues","frmAdminPanel",dataStr);
	
	var sectId = jQuery("#frmAdminPanel input[id='sect']").val();
	var flid = jQuery("#frmAdminPanel input[id='flid']").val();
	var cellId = jQuery("#frmAdminPanel input[id='cell']").val();
	
	reloadCombo("frmAdminPanel","cmbpact","cellCombo.commonFilter?cellId="+cellId+"&flid="+flid);	
	reloadCombo("frmAdminPanel","cmbaet","sectionCombo.commonFilter?sectId="+sectId+"&flid="+flid);	
}
/* 
function frmAdminPanelcmbaet_onSelect(record){
	//alert("after DMT");	
} 

 */
 
 
 jQuery("#cmbaet").combobox(
			{
				
				onSelect : function(recordid) {
				//	//alert("click");
					var dataStr="&sectId="+recordid.id;	
					////alert("click"+dataStr);
					loadFunctionalLocation("NewMocfunLocation","functionalLoc.apdb","NewMOCfunLocationValues","frmAdminPanel",dataStr);
					
					var sectId = jQuery("#frmAdminPanel input[id='sect']").val();
					var flid = jQuery("#frmAdminPanel input[id='flid']").val();
					//alert(flid);
					//reloadCombo("frmAdminPanel","cmbpact","sectionCombo.commonFilter?sectId="+sectId+"&flid="+flid);
					var cellId = jQuery("#frmAdminPanel input[id='cell']").val();
					reloadCombo("frmAdminPanel","cmbpact","cellCombo.commonFilter?cellId="+cellId+"&flid="+flid);	
					reloadCombo("frmAdminPanel","cmbaet","sectionCombo.commonFilter?sectId="+sectId+"&flid="+flid);	
				}
			});
function selectedPillarDsh_onsuccesscallback(result){
	
	var columns = 3;
	
	var html="";
	var wdth=0;
	for( var i = 0; i< result.length ; i++){
		////alert(result[i].title);
		////alert(result[i].reportUrl);
		//jQuery('#'+jQuery("#hdnSelDashbrdPillar").val()).css('display','block');
		//jQuery('#'+jQuery("#hdnSelDashbrdPillar").val()).append('<li id=""><a href="#" onclick =btnSinglegrph("'+ result[i].reportUrl  +'","'+result[i].keyId +'");>'+result[i].title+'<a></li>');
		if( i % columns == 0 ){
			if( i != 0)
				html +="</div>";
			html += "<div class='dashboard-row'>";
		}
		html += "<div class='dashboard-cell'> "+
				"<div class='dashboard-view' draggable='true'> "+
				" <a class='dashboard-link'> " +
				" <span class='dashboard-title' style='color:#fff;'> " + result[i].title + " </span> ";
				
				if(jQuery.browser.msie ){
					wdth="290";
				}else{
					wdth="100%";
				}
			if( result[i].type != "TBL" ){
				html +=//"<input type='button' class='zomBtn' chrtDt="+result[i].chartData+" onclick=funcZoom('"+result[i].keyId +"');></input>"+
				// "<div id='dashbd_"+result[i].keyId +"' style='position:relative; width:"+wdth+"; height: 90% ;overflow:auto\9;display:block;margin-top:-1%;'><img class='dashbrdLodr' alt='' src='images/dashboardloader.gif' style='margin-top:33%;margin-left:45%'></div>";
					"<div id='dashbd_"+result[i].keyId +"' style='position:relative; width:"+wdth+"; height: 100% ;overflow:auto\9;display:block;margin-top:-1%;'></div>";
			}
			else if( result[i].type == "TBL" )
			{
				html +=	"<div class='zomBtn' chrtDt='"+result[i].reportUrl +"' onclick=funcZoomGrd('dashbd_"+result[i].keyId +"'); style='position:absolute;right:0;background-color:transparent;'></div>"+
						"<div id='div_dashbd_"+result[i].keyId +"'> <table id='dashbd_"+result[i].keyId +"'></table><div id='dashbd_item_pager_"+i +"'></div><input type='hidden' id='grdId' value='dashbd_"+result[i].keyId +"'><div id='grdUrl' style='display:none;'>"+result[i].reportUrl +"</div></div>";
						/*setTimeout(function() {
							jQuery('.dashboard-view').attr('gridDiv','dashboard-grd'+i);
							},1500);*/
			}	
				
		html +=	"</a> " +
				" <div class='mask'></div>  </div></div>";							
	}
	jQuery('#dashboard-grid').html(html);
	var fromMonth = jQuery("#hdnFromMonth").val();
	var lstfromMonth = jQuery("#hdnLstFromMonth").val();
	var lastTheeMonth= jQuery("#hdnLstThrMonth").val();
	var lastTwoyear=jQuery("#hdnLstTwoyears").val();
	////alert("lastTwoyear"+lastTwoyear);
	var toMonth = jQuery("#hdnToMonth").val();
	var cellId = jQuery('#hdncellId').val();
	//alert("flidcellId"+cellId);
	var sectId = jQuery('#hdnsectionId').val();
	var pbuId = jQuery('#hdnpbuid').val();
	var sbuId = jQuery('#hdnsbuid').val();
	var flid=jQuery('#hdnflid').val();
    //alert("flid"+flid);
	var funcLoctionStr =  getFunctionalLocation("frmEmpPage");
	 
		for( var i = 0; i< result.length ; i++){
			////alert(" URL :: "+result[i].reportUrl);
			var plrtype=result[i].reportUrl;
			var text=plrtype.substring(0,plrtype.indexOf("newgrph"));
			var textnw=plrtype.substring(0,plrtype.indexOf("newgrphThree"));
			var texttwoyear=plrtype.substring(0,plrtype.indexOf("newgrphTwoyear"));
			////alert("The textnw::::"+texttwoyear);
		
			//var newtypethe;
			if(text.trim().length>0){
				newtpe=lstfromMonth;
			}else{
				newtpe=fromMonth;
			}
			
			if(textnw.trim().length>0){
				newtpe=lastTheeMonth;
			}
			if(texttwoyear.trim().length>0){
				newtpe=lastTwoyear;
			}
	/* 		 else{
				 newtpe=fromMonth;
			}  */
			
			var keyid = result[i].keyId ;
			////alert(keyid);
		if( result[i].type != "TBL" )	{
			jQuery.ajax({
			       type: "GET",
			       url: result[i].reportUrl,
			       dataType:  "json",
			       data:"dashboardIdent="+ result[i].keyId +"&dashboard=true&dtFromMonth="+newtpe+"&dtToMonth="+toMonth+"&drillFlag=f&chkMonthwise=1&cellId="+cellId+"&parentId="+sectId+"&sectId="+sectId+"&flid="+flid +"&flId="+flid ,
			  
			       success: function(result)
			       {
						////alert(Object.keys(result.chartData));
				      if(jQuery.browser.msie && "landing" == fromPage)
						{
				    		 result.chartData.width = 350;
							 result.chartData.height = 80;
				    	 } 
			    	 /**Added By Manikandan for LAndpage chart in IE**/
			    	 if(jQuery.browser.msie && "landing" == fromPage)
					{
			    		// //alert('dsh2');
			    		 //result.chartData.width = 380;
			    		 jQuery('.dashboard-cell').css('max-width','380px ');
			    		 jQuery('.dashboard-cell').css('height','480px ');
					}
			    	 
			    //	 if (result.chartData.subTitle.text.trim().length==0)
			    	 //	result.chartData.subTitle.text=funcLoctionStr;	
			    	 jQuery("#hdnchartData").append('<span  id='+result.dashboardIdent+'>'+JSON.stringify(result.chartData)+'</span>');	
			    	 ////alert('dsh3');
			    	// result.chartData.subTitle.text="";
			    	 
			    	 
			    	  if( result.chartData != null && result.chartData.type != "gauge" ){
			    		    ////alert(" chartData :: keys :: "+Object.keys(result.chartData));
			    	 		drawChart(result.chartData,'dashbd_'+result.dashboardIdent,'Y','','','N','dashboard')	;
			       	  }		
			    	  else {
			    			drawGaugeChart('dashbd_'+result.dashboardIdent,result.chartData);
			    	  }			
			    	 
			       },
				   error:function(status)
				   {
					   
				   }
			});   	   
			
		}
		
	else{
		//LoadForm("div_dashbd_"+result[i].keyId , "preLoadContent", result[i].reportUrl,"dispErr","dshGrd_SuccessCalBack","dshGrd_ErrorCalBack");
		processGridnew(result[i].reportUrl,"?q=2&fromDashBoard=true","dashbd_"+result[i].keyId ,"dashbd_item_pager_"+i ,"","dblclick","","GRDloadComplete");
	}
 }
}

function GRDloadComplete(){
	var grdId = jQuery('#grdId').val();
	var row = jQuery("#"+grdId).jqGrid('getDataIDs');
	 var cm = jQuery("#"+grdId).jqGrid("getGridParam", "colModel");
	 jQuery("#"+grdId).jqGrid('setGridWidth',530);
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=0;j<cm.length;j++)
     	 {
			 var Val = jQuery("#"+grdId).jqGrid('getCell',row[i],cm[j].name);	
     	 }
	 }
}


function funcZoomOutGrd(grdId){
jQuery("div.zomBtn").css("display","block");
jQuery("div.zomOut").css("display","none");
	
	jQuery('.dashboard-view attr[gridDiv'+closestDivclass+']').animate({'top':'-=20px','left':'-=40px', 'width':'-=500px'});
	 jQuery("#"+grdId).jqGrid('setGridWidth',530);	
}
function funcZoomGrd(grdId){
	var closestDivclass = jQuery("div.zomBtn").parent().parent().attr("gridDiv");
	var url = jQuery('#grdUrl').html();
	var zoominDiv = jQuery('#grapContainerZoom').html();
	 var zomOutBtn = ' <span id="closeZoom" class="" style="float:right;"><img id="btnZoomClose" title="Close"  src="images/new_close.jpg"  onclick="") ></span>';
	if(zoominDiv != null || zoominDiv != ' '){
	
		setTimeout(function() { jQuery('#grapContainerZoom').html( zomOutBtn+jQuery('#grapContainerZoom').html());},1020);
	    navigateToNextForm( url+"?q=2&loadContentDivId=grapContainerZoom");
	}
	jQuery("#"+grdId).trigger("reloadGrid") ;
	jQuery("#"+grdId).jqGrid('setGridWidth',930);
	jQuery("#"+grdId).trigger("reloadGrid") ;
	jQuery('#graphzoom').css('display','block');
	jQuery('#graphzoom')
	.animate( {"opacity": "0.15"},
            "fast")
    .animate({"height": "80%"}, 500)
    .animate({"width": "80%"}, 500)
	.animate( {"opacity": "2.15"},
    "fast")
        
    jQuery('.zomBtn').css('display','none');
	jQuery('#dashboard-grid').css('display','none');
	jQuery('#btnzoomOut').css('position','absolute');
	jQuery('#btnzoomOut').css('right','8');
	jQuery('#btnzoomOut').css('background-color','transparent');
	jQuery('#spnzoomOut').css('display','block');
    
}
function funcZoom(divId){
	var gridHTml = jQuery('#dashboard-grid').html();
	var chrtDta = jQuery('#'+divId).html() ;
	var chrtDta_obj = JSON.parse(chrtDta);
	var  fromPage = jQuery('#hdnfromPage').val();
	var chartwidth ;
	if(screen.width <= 1024){
		chrtDta_obj.height ="410";
		chrtDta_obj.width ="625";
	}
	else{
		if("landing"==fromPage)
			chartwidth="825";
		else
			chartwidth="925";
		chrtDta_obj.height ="450";
		chrtDta_obj.width =chartwidth;
	}
	var mgLeft = '';
	if(screen.width <= 1366)
		mgLeft ="7%";
	else
		mgLeft ="5%";
	
	if("landing"!=fromPage)
		jQuery('#graphzoom').css('margin-left',mgLeft);
	jQuery('#graphzoom')
	.animate( {"opacity": "0.15"},
            "fast")
 	.animate({"height": "80%"}, 500)
    .animate({"width": "80%"}, 500)
	.animate( {"opacity": "2.15"},
    "fast")
	
	 jQuery('.zomBtn').css('display','block');
	var titleWidth;
	setTimeout(function() {
		jQuery('#ChrtNewTitle').css('margin-top','1');
		if("landing" == fromPage){
			jQuery("#closeZoom").css('top','10%');
			jQuery("#closeZoom").css('right','2%');
			jQuery("#closeZoom").css('z-index','1');
		}
			
		if(screen.width <= 1024){
		jQuery('#ChrtNewTitle').css('width','690');
		jQuery('.dashToolBar').css('width','690');
		}
		else{
			if("landing" == fromPage)
				titleWidth = "820";
			else
				titleWidth = "890";
		}
		
		
		},1250);
				 
     LoadPopUp("grapZoom", "zoomChart_input.dashboard?chrtdivId="+divId, true,"96%","78%","13%","1%", "multiSelectOk_Callback","Dashboard",false);
	 jQuery('#ChrtNewTitle').prepend(closebutt);
	
}

function multiSelectOk_Callback(){
	setTimeout(function(){
		jQuery('.dashboard-cell').css('max-height','330px');
		jQuery('.dashboard-cell').css('max-width','390px\9');	
	},1150);
	}

function grapZoom_onClose(){
	funcZoomout();
	return true;
}
function funcZoomout(){
	jQuery("div.zomBtn").css("display","block");
	 setTimeout(function() {jQuery('#graphzoom').hide();
	jQuery('#dashboard-grid').show();
	jQuery('input.zomBtn').addClass('zomBtn');
	jQuery('.zomBtn ').show();
	jQuery('.dashToolBar').css('widtha','99%');
	jQuery('#spnzoomin').show();},50);
}

jQuery("#btnExcel").click(function(){
	var flid=jQuery('#hdnflid').val();
//	alert(flid);
   // var fromStartMonth = jQuery("#hdnYearStartMonth").val();
	//var toEndMonth = jQuery("#hdnYearEndMonth").val();
    var fromMonth=jQuery("#hdnStartMonth").val();
//	var toMonth=jQuery("#hdnEndMonth").val();
//	alert(toMonth);
	var toMonth=jQuery("#hdnsixMonth").val();
	var FirstMonth=jQuery("#hdnFirstMonth").val();
	var DMTflid=jQuery("#cmbaet").combobox("getValue");
	var JHkeyid=jQuery("#cmbpact").combobox("getValue");
	var FromDate=jQuery("#dteFromdate").datebox('getValue');
	var ToDate=jQuery("#dteTodate").datebox('getValue');
	if(FromDate.length==10){
	if(FromDate.charAt(0)=="1"){
		FromDate=FromDate.replace(FromDate.charAt(0),"01");
	}
	else if(FromDate.charAt(0)=="2"){
		FromDate=FromDate.replace(FromDate.charAt(0),"02");
	}
	else if(FromDate.charAt(0)=="3"){
		FromDate=FromDate.replace(FromDate.charAt(0),"03");
	}
	else if(FromDate.charAt(0)=="4"){
		FromDate=FromDate.replace(FromDate.charAt(0),"04");
	}
	else if(FromDate.charAt(0)=="5"){
		FromDate=FromDate.replace(FromDate.charAt(0),"05");
	}
	else if(FromDate.charAt(0)=="6"){
		FromDate=FromDate.replace(FromDate.charAt(0),"06");
	}
	else if(FromDate.charAt(0)=="7"){
		FromDate=FromDate.replace(FromDate.charAt(0),"07");
	}
	else if(FromDate.charAt(0)=="8"){
		FromDate=FromDate.replace(FromDate.charAt(0),"08");
	}
	else if(FromDate.charAt(0)=="9"){
		FromDate=FromDate.replace(FromDate.charAt(0),"09");
	}
	}


	if(ToDate.length==10){
	if(ToDate.charAt(0)=="1"){
		ToDate=ToDate.replace(ToDate.charAt(0),"01");
	}
	else if(ToDate.charAt(0)=="2"){
		ToDate=ToDate.replace(ToDate.charAt(0),"02");
	}
	else if(ToDate.charAt(0)=="3"){
		ToDate=ToDate.replace(ToDate.charAt(0),"03");

	}
	else if(ToDate.charAt(0)=="4"){
		ToDate=ToDate.replace(ToDate.charAt(0),"04");

	}
	else if(ToDate.charAt(0)=="5"){
		ToDate=ToDate.replace(ToDate.charAt(0),"05");

	}
	else if(ToDate.charAt(0)=="6"){
		ToDate=ToDate.replace(ToDate.charAt(0),"06");

	}
	else if(ToDate.charAt(0)=="7"){
		ToDate=ToDate.replace(ToDate.charAt(0),"07");

	}
	else if(ToDate.charAt(0)=="8"){
		ToDate=ToDate.replace(ToDate.charAt(0),"08");

	}
	else if(ToDate.charAt(0)=="9"){
		ToDate=ToDate.replace(ToDate.charAt(0),"09");
	}

	}
	//alert("FromDate:"+FromDate);
	//alert("ToDate:"+ToDate);


	if(jQuery("#chkFinance").is(":checked")==true){
	   var Finance="Y";
	}
	else{
		 var Finance="N";	
	}
	window.open("NewDashboard_Excelview.dashboard?flid="+flid+"&fromMonth="+fromMonth+"&toMonth="+toMonth+"&FirstMonth="+FirstMonth+"&DMTflid="+DMTflid+"&JHkeyid="+JHkeyid+"&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance);	
});


jQuery("#btnClear").click(function(){
	jQuery("#cmbpact").combobox("clear");
	jQuery("#cmbaet").combobox("clear");
	jQuery("#cmbLocnid").combobox("clear");
	enableFields("cmbpact");
	enableFields("cmbaet");
});

jQuery("#btnView").click(function(){
	disableField("frmAdminPanel","cmbpact");
	disableField("frmAdminPanel","cmbaet");
});

</script>
<form id ='frmAdminPanel'>
<div class="bodybg"style="height:110%;width:100%;">
<div class="menu-bar-header" style="margin-top:0px;width:100%;height:55px;">
<ul>
<!-- <li class="newactive"><a href="#"><b>PERFEX 360 DASHBOARD</b></a> -->
<li class="newactive">
<img id="hometab"  onclick="Home()" class="hometab"  src="images/HomePageNew.png" title="Home" style="margin-top:-12px;margin-left:-5px;"/> 
</li>


</ul>
<table>
<tr><td>
	<div>
	  	<label class="mandatory-lbl"style="margin-left:-460px;font-size:13;color:white"><b>From Date</b></label>  
	  	                     
	     </div> 
			   <div style="margin-left:-460px;margin-top:0px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 90px; height: 24px" id="dteFromdate" name="dteFromdate" value=""/>
			        </div> 
			        
			        </td>
			        <td>
	<div>
	  	<label class="mandatory-lbl"style="margin-left:-350px;font-size:13;color:white"><b>TO Date</b></label>  
	  	                     
	     </div> 
			   <div style="margin-left:-350px;margin-top:0px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 90px; height: 24px" id="dteTodate" name="dteTodate" value=""/>
			        </div> 
			        
			        </td>
			      <td>
			      <div>
			      	<label style="margin-left:-245px;font-size:15;color:white;"><b>Financial Year</b></label>  
	  
			      </div>
			       <div style="margin-left:-205px;margin-top:0px;">
			       <input type="checkbox"  id="chkFinance" name="chkFinance"  value=""/>
			     </div>
			      </td>    
	</tr>		        
</table>			        
<div class="easyui-paddingbfpx"style="margin-left:-130px;margin-top:20px;">
					       <span style="padding-left:0px;"><label class="Label"><b>Location</b></label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" style="margin-left:10px;margin-top:25px;" >
					         <span style="position:relative;padding-left: -100px">	
						    <input class="easyui-combobox" id="cmbLocnid" name="cmbLocnid"  style="width:100px;"  value="" />
				
						    </span>
					       </div>
					        	<div class="easyui-paddingbfpx"style="margin-left:10px;margin-top:20px;">
					       <span style="padding-left:0px;"><label class="label"><b>DMT/AET</b></label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" style="margin-left:10px;margin-top:25px;">
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbaet" name="cmbaet"   style="width:170px;"  value="" />
				
						    </span>
					       </div>
					            	<div class="easyui-paddingbfpx"style="margin-left:10px;margin-top:20px;">
					       <span style="padding-left:0px;"><label class="label"><b>JH/PACT</b></label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" style="margin-left:10px;margin-top:25px;">
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbpact" name="cmbpact"  style="width:170px;"  value="" />
				
						    </span>
					       </div>
					       
					          <div style="margin-left:10px;margin-top:25px;">
 	<input id="btnView" class="easyui-button" style="padding-top:0;" type="button" value="View"/>
	</div>		
						       
	 <div style="margin-left:15px;margin-top:25px;">
 	<input id="btnClear" class="easyui-button" style="padding-top:0;" type="button" value="Clear"/>
	</div>	
				       
	 <div style="margin-left:20px;margin-top:25px;">	
	<img id="btnExcel" name="btnExcel" src="images/menu-icon/ExcelIcon.png"  style="padding-top:0px;" title="Excel"/>
	</div>	
</div>

 
 <div class="menu-bar" style="margin-top:-15px;width:140px;height:90%">
<ul id="accordian">
<li><div>Charts</div>
<ul>
<li onclick="ChartClass1()"><a href="#">JH Charts</a></li>
<li onclick="ChartClass2()"><a href="#">KK Charts</a></li>
<!-- <li><a href="#">Chart2</a></li> -->

</ul>
</li>
<li><div>Tables</div>
<ul>
<li onclick="TableClass1()"><a href="#">EHS Metrics</a></li>
<li onclick="TableClass2()"><a href="#">DMT/AET Adherence</a></li>

<li onclick="TableClass3()"><a href="#">JH/PACT Adherence</a></li>

<li onclick="TableClass4()"><a href="#">Mom Review</a></li>
<li onclick="TableClass5()"><a href="#">Kaizen Status</a></li>
<li onclick="TableClass6()"><a href="#">Abnormality Report</a></li>
<li onclick="TableClass7()"><a href="#">LMP Tools</a></li>
<li onclick="TableClass8()"><a href="#">MonthWise AET Adherence</a></li>
<li onclick="TableClass9()"><a href="#">MonthWise Pact Adherence</a></li>
<li onclick="TableClass10()"><a href="#">Abn Aeging Report</a></li>
</ul>
</li>
</ul>
<ul id="accordian1" style="display:none;">
<li><div>Charts</div>
<ul>
<li onclick="ChartClass1()"><a href="#">JH Charts</a></li>
<!-- <li onclick="ChartClass2()"><a href="#">KK Charts</a></li> -->
<!-- <li><a href="#">Chart2</a></li> -->

</ul>
</li>
<li><div>Tables</div>
<ul>
<li onclick="TableClass1()"><a href="#">EHS Metrics</a></li>
<li onclick="TableClass2()"><a href="#">DMT/AET Adherence</a></li>

<li onclick="TableClass3()"><a href="#">JH/PACT Adherence</a></li>

<li onclick="TableClass4()"><a href="#">Mom Review</a></li>
<li onclick="TableClass5()"><a href="#">Kaizen Status</a></li>
<li onclick="TableClass6()"><a href="#">Abnormality Report</a></li>
<li onclick="TableClass7()"><a href="#">LMP Tools</a></li>
<li onclick="TableClass8()"><a href="#">MonthWise AET Adherence</a></li>
<li onclick="TableClass9()"><a href="#">MonthWise Pact Adherence</a></li>
<li onclick="TableClass10()"><a href="#">Abn Aeging Report</a></li>
</ul>
</li>
</ul>
<ul id="accordian2" style="display:none;">
<li><div>Charts</div>
<ul>
<!-- <li onclick="ChartClass1()"><a href="#">JH Charts</a></li> -->
 <li onclick="ChartClass2()"><a href="#">KK Charts</a></li> 
<!-- <li><a href="#">Chart2</a></li> -->

</ul>
</li>
<li><div>Tables</div>
<ul>
<li onclick="TableClass1()"><a href="#">EHS Metrics</a></li>
<li onclick="TableClass2()"><a href="#">DMT/AET Adherence</a></li>

<li onclick="TableClass3()"><a href="#">JH/PACT Adherence</a></li>

<li onclick="TableClass4()"><a href="#">Mom Review</a></li>
<li onclick="TableClass5()"><a href="#">Kaizen Status</a></li>
<li onclick="TableClass6()"><a href="#">Abnormality Report</a></li>
<li onclick="TableClass7()"><a href="#">LMP Tools</a></li>
<li onclick="TableClass8()"><a href="#">MonthWise AET Adherence</a></li>
<li onclick="TableClass9()"><a href="#">MonthWise Pact Adherence</a></li>
<li onclick="TableClass10()"><a href="#">Abn Aeging Report</a></li>
</ul>
</li>
</ul>
</div>
<ul class="breadcrumb" id="header1" style="margin-top:-540px;margin-left:160px;width:83%;height:30px;display: none;background-color:#666699;">

<li><b>EHS Metrics(From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header2" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>AET Attendance Adherence (From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header3" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>Pact Attendance Adherence(From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header4" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>Last Review Points and Status (From Dec-2020 To Feb-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header5" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>Status of Implemented Kaizens(From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header6" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>Status of Abnormalities(From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header7" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>LMP Tools Usage Summary(From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header8" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>AET MonthWise Attendance(From Apr-2020 To Mar-2021)</b></li>
</ul>
<ul class="breadcrumb" id="header9" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>Pact Monthwise Attendance(From Apr-2020 To Mar-2021)</b></li>
</ul>

<ul class="breadcrumb" id="header10" style="margin-top:-540px;margin-left:160px;width:83%;height:35px;display: none;background-color:#666699;">

<li><b>Abnormality Aeging Report(From Apr-2020 To Mar-2021)</b></li>
</ul>

   <!-- Icon Cards-->
          <div class="row" id="bread"style="margin-top:-500px;margin-left:130px;width:105%;">
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-comments"></i>
                  </div>
                  <div class="mr-5">Jishu Hozen</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left" onclick="JH()">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-warning o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-list"></i>
                  </div>
                  <div class="mr-5">Kobetzu Kaizen</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left" onclick="KK()">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-success o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-shopping-cart"></i>
                  </div>
                  <div class="mr-5">EHS Pillar</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-danger o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                  </div>
                  <div class="mr-5">Quality Maintainance</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
              <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-secondary o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-comments"></i>
                  </div>
                  <div class="mr-5">Education & Training</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left" onclick="JH()">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-info o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-list"></i>
                  </div>
                  <div class="mr-5">Planned Maintainance</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-dark o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-shopping-cart"></i>
                  </div>
                  <div class="mr-5">Early Management</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white  o-hidden h-100" style="background-color:purple;">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                  </div>
                  <div class="mr-5">Office Tpm</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
          </div>


<div id="dashboard-grid" style="margin-left:11%;margin-top:-550px;">
</div>
<div id='graphzoom' style='height:0;width:0;'>
	
	<div id="grapContainerZoom">
		
		
	</div>
	<div id="grapSingleContainer">
		<div class=""></div>
	</div>
</div>

<div id='dashboard-margin-top'></div>
<div class='dashboard-side-margin'></div>

<div class='dashboard-side-margin'></div>
<div id="dashboard-margin-bottom"></div>




<input type="hidden" id="hdnSelDashbrdPillar" value ="${requestScope.pillar }" />
<input type="hidden" id="hdnFromMonth" value ="${requestScope.FromMonth}" />
<input type="hidden" id="hdnLstFromMonth" value ="${requestScope.LstFromMonth}" />
<input type="hidden" id="hdnLstThrMonth" value="${requestScope.lastTheeMonth}"/>
<input type="hidden" id="hdnLstTwoyears" value="${requestScope.lastTwoyear}"/>
<input type="hidden" id="hdnToMonth" value ="${requestScope.ToMonth}" />
<input type="hidden" id="hdnfromPage" value ="${requestScope.fromPage}" />
<input type="hidden" id="hdnflid" value ="${requestScope.flid}" />
<input type="hidden" id="hdnStartMonth" value ="${requestScope.StartMonth}" />
<input type="hidden" id="hdnEndMonth" value ="${requestScope.EndMonth}" />
<input type="hidden" id="hdnYearStartMonth" value ="${requestScope.YearStartMonth}" />
<input type="hidden" id="hdnYearEndMonth" value ="${requestScope.YearEndMonth}" />
<input type="hidden" id="hdnsixMonth" value ="${requestScope.sixMonth}" />
<input type="hidden" id="hdnFirstMonth" value ="${requestScope.FirstMonth}" />
<input type="hidden" id="hdnFYearStart" value ="${requestScope.FYearStart}" />
<input type="hidden" id="hdnsectionId" value ="" />
<input type="hidden" id="hdncellId" value ="" />
<input type="hidden" id="hdnpbuid" value ="" />
<input type="hidden" id="hdnsbuid" value ="" />
<input type="hidden" id="hdnlocnid" value ="" />
<div  id="hdnchartData" style="display:none" ></div>

<div id="DashboardTableNew1"style="margin-left:160px;margin-top:475px">
<table id="abnCumulativeGrid1" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew2"style="margin-left:150px;margin-top:16px">
<table id="abnCumulativeGrid2" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew3"style="margin-left:150px;margin-top:20px">
<table id="abnCumulativeGrid3" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew4"style="margin-left:150px;margin-top:-0px">
<table id="abnCumulativeGrid4" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew5"style="margin-left:165px;margin-top:-5px">
<table id="abnCumulativeGrid5" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew6"style="margin-left:165px;margin-top:-5px">
<table id="abnCumulativeGrid6" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew7"style="margin-left:165px;margin-top:-5px">
<table id="abnCumulativeGrid7" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew8"style="margin-left:165px;margin-top:-5px">
<table id="abnCumulativeGrid8" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew9"style="margin-left:165px;margin-top:-5px">
<table id="abnCumulativeGrid9" ></table>
<div id="pager"></div>
</div>
<div id="DashboardTableNew10"style="margin-left:165px;margin-top:-5px">
<table id="abnCumulativeGrid10" ></table>
<div id="pager"></div>
</div>
<div id="functionallocn">
  <div  id="frmAdminPanelFuntKeyIds">
  
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu} "  ></input>	
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>		
					<input type="hidden" id="section" name="cmbaet" value="${requestScope.dmt}"></input>
					<input type="hidden" id="cell" name="cmbpact" value="${requestScope.jh}"  ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.mchId} "  ></input>
					<input type="hidden" id="flid" name="flid" value="${requestScope.flid}"></input>	
				</div>
				
			 	<div id="NewMocfunLocation" style="width:100%;margin-top:0px;margin-left:-35px"></div>
		</div>	
	</div>
</form>