jQuery.noConflict();
jQuery(document).ready(function(){	
	
	jQuery( "#sidecontent_0_pullout" ).click(function() 
	{
		
		
		jQuery(".reminder_content_div").slideDown("slow",function(){
		
			application_loadReminder();
			
		} );		
	});
	
	
	jQuery('.clos_reminder_div').click(function() {
		
		jQuery(".reminder_content_div").slideUp("fast");
	});
	jQuery("#sidecontent_1_pullout" ).click(function() 
     {
		//jQuery('#sidecontent_1').append("<a id='clickThe' >Qlink</a>");
		
		if(jQuery("#sidecontent_1").css('width')=='0px')
			jQuery('#sidecontent_1').css('border','solid 2px #A8A4A4');
		else
			  setTimeout(function() {jQuery('#sidecontent_1').css('border','1px #fff');},300);
			
		if(jQuery("#Qlinks").html()== ''){
			processAjaxCalls("QlinkMenus.menuTree","","qlinkmenu_successCallback");
		}
		
	 });
	jQuery(".alerttab").click(function(){
		//jQuery(".alert_content_div").slideUp("fast");
		application_loadInbox();
		
		
	});
	jQuery('.clos_alert_div').click(function() {
		
		jQuery(".alert_content_div").slideUp("fast");
	});

	/*
	jQuery(".filtertab").click(function(){
		jQuery(".filterpanel").animate({
			width: "500px"
		}, 500);
		jQuery(".filterpanel").toggle("fast");
		jQuery(this).toggleClass("active");
		loadCommonFilter();
		return false;
	});
	*/
	jQuery(".filtertab").click(function(){
		toggleCommonFilter(true);
		return false;
	});
	/*
	jQuery(".olapHelpTab").click(function(){
		var url = getSubmitFormUrl();
		if( url != "" && url.trim().length > 0 ){
			var isHome = getFilterValue( url+"&","isHomePage");
			if(isHome == "true")
				url = "Index";
			else if( url.indexOf("?") > 0 ){
				url = url.substring(0,url.indexOf("?")-1);
			}
			
			if(url.indexOf(".")>0)
				url = url.substring(0,url.indexOf("."));
			url += ".htm";
		}
		window.open('helpdoc/'+url,'');		
		return false;
	});
	*/
});

function application_loadReminder(){
	if(jQuery('#reminder_content').html() == ""){ 
		//processAjaxCalls("reminder_input.alerts","","reminder_successCallback");
		LoadForm("reminder_content","","reminder_input.alerts?q=2&forDashboard=true");
	}
	else{
		jQuery("#reminderGrd").trigger('reloadGrid');
	}
	//LoadForm("reminder_content","","reminder_input.alerts?q=2&forDashboard=true");
	//alert('This functionality is not enabled');
	//return false;
	jQuery("#sidecontent_0").css('width','0'); 
	if(jQuery("#sidecontent_0").width() == 0){
    // jQuery(".dashboard_content_div").slideDown("fast"); 
     /*jQuery("#dashboard_content").load('dashboard_input.chr', function(response, status, xhr) {
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		  } 
		});
		*/		
	 }
}
function application_loadInbox(){
	  var dashbrdZind = jQuery(".dashboard_content_div").css("z-index");
	  var dashbrdDivDisplay = jQuery('.dashboard_content_div').css('display');
	  var reminderDivDisplay = jQuery('.reminder_content_div').css('display');
	  var reminderDivZindex = jQuery('.reminder_content_div').css('z-index');
	  var sidecontentDiv = jQuery("#sidecontent_1").css("width");
	  var sidecontentzind = jQuery("#sidecontent_1").css("z-index");
	 
	  if(dashbrdDivDisplay == "block"){
		
			jQuery("div.alert_content_div").css("z-index",dashbrdZind+2);
	  }
	  else{
		  jQuery("div.alert_content_div").css("z-index",dashbrdZind-2);
		
	  }
	   if(reminderDivDisplay == "block" ){
		   
		   setTimeout(function() { jQuery("div.alert_content_div").css("z-index",parseInt(reminderDivZindex)+102);},500);
			
	   }
	  else{
		
			jQuery("div.alert_content_div").css("z-index",parseInt(reminderDivZindex)-102);
	  }
 		if(sidecontentDiv >'0px'){
			jQuery("div.alert_content_div").css("z-index",sidecontentzind+2);
 		}
 		else
 			jQuery("div.alert_content_div").css("z-index",sidecontentzind-2);
	jQuery(".alert_content_div").slideDown("fast");
	
	
	if(jQuery('#alert_content').html() == "")
		LoadForm("alert_content","","inbox_input.alerts?forDashboard=true");//LoadForm("alert_content","","inbox_input.alerts?q=2&forDashboard=true");
	else{
		
		jQuery("#grdApprovals").trigger('reloadGrid');
	}	
	//jQuery("#hdnZ-IndexUrl").val("alerts_input.alerts");
	jQuery("#hdnZ-IndexUrl").val("inbox_input.alerts");
}
function reminder_successCallback(result){
	
	//alert(Object.keys(result.retReminderData[0]));
	//alert(Object.keys(result.retReminderData).get(1));
	//alert(Object.keys(result.retReminderData).get(2));
	var rowSize = result.retReminderData.length;//alert("rowSize  "+rowSize);
	
	var tableContent= "<div style='margin-top:10%;' align='center'>";
	 tableContent+="<table id='reminderTbl' class='cellbord' rules='all' cellpadding='3' cellspacing='5' border='1' style='font-size:12px;font-weight:bold;' >";
	tableContent+="<tr style='background-color: #5FAEF9 '><th class='bordHeader' >S.no</th><th class='bordHeader'>Activity</th><th class='bordHeader'>Pending Count</th><th class='bordHeaderLst'>Last Entry Date</th></tr>";
	tableContent+="<tr>";
	for (var j=0;j<rowSize;j++)
	{	
		var v=j+1;
		tableContent+="<td  ALIGN=center class='cellbord' style='font-size:12px'>"+v+"</td>";
		 for (var i=0;i<3;i++)
		 {
			// alert(j+" -- "+i+" -- "+result.retReminderData[i][j]);
			 var data = result.retReminderData[j][i];
			
			 var align = 'left';
			 if(data == null )
				 data = ' - ';
			 if(data.trim().length<=0)
				 data = "-";
			 var numberRegex = /^[+-]?\d+(\.\d+)?([eE][+-]?\d+)?$/;
			
			 if(numberRegex.test(data)) {
				 align = 'right';
			 }
			 var styleName;
			 if(i=="2")
				 styleName=" border-right:1px solid #000 !important";
			 else
				 styleName=""; 
			 
			 tableContent+="<TD ALIGN=left class='fontSize cellbord' style='text-align:"+align+";"+ styleName+";"+"'>"+data+"</TD>";
		 }
		 tableContent+="</tr>";  
	}
	tableContent+="</table></div>";
	
	jQuery("#reminder_content").html(tableContent);
}
function qlinkmenu_successCallback(result){
//	alert("Sucess");
	jQuery("#sidecontent_1").css('background-color','#c1c1c1');
	if(jQuery("#Qlinks").html()== ''){
		for(var i=0; i<=result.length;i++){
			//similarColumn,loadforArgument,masterSql,tablename,masterIntegrateSq
			//alert(result[i].masterSql);//("+result[i].loadforArgument+","+result[i].masterSql+")
			var sno =  i+1 ;
			var linkA  ="";
			//if(  menuCaption =="" ||menuCaption != result[i][0] )
				 // linkA  +='<div style="padding:10px;border:inset 1px #c1c1c1;background-image:url(images/accordian/btnImage.png);width:170px;">'+result[i][0]+'</div>';
				// menuCaption = result[i][0];
			linkA += "<span  style='background-color:#c1c1c1;position:absolute;font-family:tahoma;font-size:11;margin-right:2px;font-weight: bold;margin-top:2;margin-left:2px;color:#54756B'> </span><span> <a id='qLink_"+i+"' class='clickThe' menuName='"+result[i].loadforArgument+"' ";
			linkA +=" isMaster='"+result[i][0]+"' formheader='"+result[i][1]+"'  isFilterNeed='"+result[i][0]+"'";
			linkA +=" relatedFilter='"+result[i][0]+"' ";
			linkA +=" loadforArgument='"+result[i][2]+"' ";
			linkA +=" style='line-height:0.7;color:#000;opacity:0.7;S' ";
			linkA +=" onclick=triggerPage('qLink_"+i+"');>"+result[i][1]+"</a></span>";
			
			jQuery("#Qlinks").append(linkA);
		}
	}
}
function triggerPage(id){
	/***actionpart is the url of the form that is to be loaded****/
	/***formheader is the header  of the form that is to be loaded****/
	/***isMaster is the to check whether master form or not****/
	/***isFilterNeed is the to check whether filter to be needed or not****/
	/***relatedFilter wat related filter to be loaded ****/

	var formheader = jQuery('#'+id).attr('formHeader');
	var isMaster  = jQuery('#'+id).attr('isMaster');
	var isFilterNeed = jQuery('#'+id).attr('isFilterNeed');
	var relatedFilter = jQuery('#'+id).attr('relatedFilter');
	var actionpart = jQuery('#'+id).attr('loadforArgument');
	/*alert(formheader);
	alert(isMaster);
	alert(isFilterNeed);
	alert(actionpart);*/
	//navigateToNextForm(actionpart,formheader);
	loadFormsFromMenu("false",actionpart,isMaster,formheader,isFilterNeed,relatedFilter);
	//jQuery('#sidecontent_1').css('border','0px transparent');
	/**for slider to slide in**/
	jQuery("#sidecontent_1").css({
		position: "absolute",
		overflow: "hidden",
		top: "-3px",
		width: "0px",
		border:"none"
	});
	
	//jQuery('.layout-split-west').hide();
	//jQuery('#mainlayout').layout('collapse','west');	
}
/*function toggleCommonFilter(poptrue){

	if( jQuery('#filter_tab').attr('disabled') )
		return;
	
	jQuery(".filterpanel").toggle("fast");
	jQuery(this).toggleClass("active");

	
	if(jQuery("#filterShowHide").css("opacity") == 0 )
		loadCommonFilter(); 
	else if( poptrue != null && poptrue == true){
		if( formNavigations != null)
			formNavigations.pop();
	}	
	
}*/
/*function toggleCommonFilter(poptrue){
alert("commonFilter1");
	if( jQuery('#filter_tab').attr('disabled') )
		return;
	alert("commonFilter2");
	jQuery(".filterpanel").toggle("fast");
	jQuery(this).toggleClass("active");
	alert("commonFilter3");
	alert(jQuery("#filterShowHide").css("opacity"));
	if(jQuery("#filterShowHide").css("opacity") == 0 ){
		alert("commonFilter4");
		loadCommonFilter();
	} 
	alert(parseFloat(jQuery("#filterShowHide").css("opacity")));
		if (parseFloat(jQuery("#filterShowHide").css("opacity")) < 0.001) {
			alert("commonFilter4");
			loadCommonFilter();
		}
	else if( poptrue != null && poptrue == true){
		alert("commonFilter5");
		if( formNavigations != null)
			formNavigations.pop();
	}	
	
}*/

function toggleCommonFilter(poptrue) {

    if (jQuery('#filter_tab').is(':disabled')) return;

    var panel = jQuery('.filterpanel');

    var wasHidden = !panel.is(':visible');

    panel.toggle();

    if (wasHidden) {
        loadCommonFilter();
    }

    if (poptrue === true && formNavigations != null) {
        //formNavigations.pop();//madhan
		var formNavObj = formNavigations[formNavigations.length-1];
		if( formNavObj.divId == "loadFilter"){
			formNavigationLog("Before pop toggleCommonFilter ");
			formNavigations.pop();
			formNavigationLog("After pop toggleCommonFilter ");			
		}
    }
}

function toggleCommonFilterWithoutPop(poptrue) {

    if (jQuery('#filter_tab').is(':disabled')) return;

    var panel = jQuery('.filterpanel');

    var wasHidden = !panel.is(':visible');

    panel.toggle();

   /* if (wasHidden) {
        loadCommonFilter();
    }

    if (poptrue === true && formNavigations != null) {
        formNavigations.pop();
    }*/
}

function popFormNavigationToggleCommonFilter(){
	
	
	var formNavObj = formNavigations[formNavigations.length-1];
	formNavigationLog(" After popFormNavigation");
	
	if( formNavObj != null )
	{
		if (formNavObj && formNavObj.divId == "loadFilter") {
		toggleCommonFilterWithoutPop(true);
		}
		
	}	
	return formNavObj;
}