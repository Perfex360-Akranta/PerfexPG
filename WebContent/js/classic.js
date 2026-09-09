            jQuery(document).ready(function() {
            	 
            	jQuery.event.add(window, "load", resizeFrame);
            	jQuery.event.add(window, "resize", resizeFrame);
            	jQuery.event.add(document, "load", resizeFrame);
            	jQuery(".layout-split-west").css("z-index",6);
            	jQuery('#home_center').css("width","95%");
            	jQuery('#home_center').css("position","relative");
            	jQuery("#filter_tab").hide();
            	//var browserIdentifier = navigator.userAgent.toLowerCase();
            	var shwhome = jQuery("#hdnshwhome").val();
        	 	
        	 	if(shwhome == "N")
        		 {
        	 		jQuery("#hdnPassword").val("true");
        		 }
				 	
				//LoadPopUp("LoaddefContenpwd","changePswd.userLogin?button=Y&frmchngpwd=Y","true","900px","350px","0px","200px","","Change Password","","",true);
				 					
            		jQuery('#settings-panel').corner('bottom');
        	
            	jQuery("#settings-link").click(function(event){
            		event.stopPropagation();
            		jQuery("#settings-panel").slideToggle(200);
        		});
            	
            	jQuery("#settings-panel").click(function(event){
            		event.stopPropagation();
            	});
    			jQuery("body").click(function(){
    				if(jQuery("#settings-panel").is(":visible") )
    					jQuery("#settings-panel").slideToggle(200);
    			});
    			
    			
    			jQuery(document).keydown(function(e) {
    			    if (e.keyCode == 27) {
    			    	jQuery("#settings-panel").hide(0);
    			    }    
    			    jQuery('#settings-panel').focusout(function() { 
    				//jQuery("#settings-panel").hide(0);
    			 });
    			
    			});
            	/* jQuery(document).bind("contextmenu",function(e){
         	        return false;
        		 });
				*/
        			 
					

            /*	jQuery(window).bind('hashchange', function () {
            		  // do some magic
          		  	alert('s');
            	});	
            */	//madhan
              //jQuery.address.init(function(event) {
      	        //console.log("init: ----" );
      	     // }).change(function(event) {
          	      
				//	alert( event.value );
				//	if( event.path.startsWith("/n&2*") ){
				//		var navigObj = popFormNavigation();
				//		LoadForm(navigObj.divId,navigObj.preLoadDivId,navigObj.URL,"dispErr",navigObj.successCallBack,navigObj.errorCallBack);	
				//	}
				          	      
      	    //  });			

              jQuery('#mainlayout').layout('panel','west').panel({
            		onCollapse:function(){
            			jQuery('#home_center').css("width","95%");
            		},
            		onExpand:function(){
            			jQuery('#home_center').css("width","94%");
            		},
            		onResize:function(){
            			jQuery('#home_center').css("width","95%");
            			jQuery('#accPersonalize').accordion('resize');
            		}
            	});

             	
            	/* Load Filter Pages*/
            /*	jQuery('.layout-button-left').filter(function(){
            		if(jQuery(this).parent('div').parents('div:first').text() == "Filters" );
				    	return jQuery(this).parent('div').parents('div:first');
				    	
				}).click(function(){
	
            		//loadCommonFilter();
            	});
    	*/        	
		    	/*jQuery('.clickme').click(function(eveent) {
		        	
		    		//alert('This functionality is not enabled');
		    		//event.stopPropagation();
		    		//return false;
		    		var pillar = jQuery(this).attr("pillarcode");
		    		
		    		if( pillar != null && pillar != undefined ){
		        		LoadForm("dashboard_content","","dashboard_input.dashboard?pillar="+pillar);
		        	}
					//jQuery(".dashboard_content_div").slideUp("fast");
					
					if (jQuery(".dashboard_content_div").is(":hidden")) {
						jQuery(".dashboard_content_div").slideDown("fast");
					//jQuery("#dashboard_content").height(jQuery('#tab_container').height());
					}
					else{
						jQuery(".dashboard_content_div").slideUp("fast");
					}
				});*/
					if(jQuery("#sidecontent_1").is(":hidden")){

					}
					else {
					
					}
              jQuery('.clos_dashboard_div').click(function() {
					
					jQuery(".dashboard_content_div").slideUp("fast");
					jQuery('#loadDashBoard').html("");
				});

			   

				jQuery('#btnCloseMstFrm').click(function(){
						navigateToPrevForm();
				});

				/* Creatd By KarthicK.T */
				jQuery('#olapHomePage').bind('click', function (evt){

					/*	var url = jQuery("#hiddenUrl").val();
						var zIndexUrl = jQuery("#hdnZ-IndexUrl").val();
						var header = getFormMainHeader();
						
						if(zIndexUrl.trim()=="" || zIndexUrl != null)
						{
							url = zIndexUrl;
							if(url.indexOf('alert') >=0)
								header = "Alerts";
							else if(url.indexOf('dash') >=0)
								header = "Dash Board";
						}
						var previousUrl = jQuery("#hdnPrevoiusHomUrl").val();
						if(previousUrl != url)	{
							var r=confirm("This will Reset Your Home Page.Do You Want to Countinue?");
						
						
							if(r==true)
								processAjaxCalls("setHomePage_save.userLogin","UscpPageuri="+url+"&header="+header,"homePageOnSuccCallBack");
												
						}*/
						setHomePage();
						/*else
							processAjaxCalls("setHomePage_save.userLogin","UscpPageuri="+url+"&header="+header);*/
						
					});
				
				jQuery('#dashboard_tab').bind('click', function (evt){
					/*jQuery('#chartAccordian .accordion-header').css('height','16');
					jQuery('#chartAccordian .accordion-header').css('width','88');
					jQuery('#chartAccordian .accordion-body').css('width','108');*/
					//return false;
					var dashBoardIsOpen = jQuery('.dashboard_content_div').css('display');
					
					if(dashBoardIsOpen == "block"){
						jQuery(".dashboard_content_div").slideUp("fast");
						jQuery('#loadDashBoard').html("");
						return ;
					}
					
					var alertDivDisplay = jQuery('.alert_content_div').css('display');
					var alertDivZindex = jQuery('.alert_content_div').css('z-index');
					var reminderDivDisplay = jQuery('.reminder_content_div').css('display');
					var reminderDivZindex = jQuery('.reminder_content_div').css('z-index');
					if(alertDivDisplay == "block" )
						jQuery(".dashboard_content_div").css("z-index",alertDivZindex+5);
					else{
						jQuery(".dashboard_content_div").css("z-index",alertDivZindex-5);
					
					}
					 if(reminderDivDisplay == "block" )
						jQuery(".dashboard_content_div").css("z-index",reminderDivZindex+5);
					else{
					
						jQuery(".dashboard_content_div").css("z-index",reminderDivZindex-5);
					}
					
					var accHtml = jQuery('#loadDashBoard').html();
					
					//acordian_select();
					if( accHtml == null ||  accHtml.trim() == ""  ){
						jQuery(".dashboard_content_div").slideDown("fast");
						jQuery(".dashboard_content_div").show();
						jQuery(".dashboard_content_div").css("display","block");
						
						var url = "empEqp_input.base?showAll=false" ;//"dashboarPage_input.dashboard?q=2";
						jQuery("#loadDashBoard").css("margin-left","10%");
						LoadForm("loadDashBoard","preloadHelp",url,"dispErr","dashBoard_successCallback");
						jQuery("#hdnZ-IndexUrl").val(url);
						jQuery("#loadDashBoard").css("margin-left","10%");
					}
					//jQuery(".dashboard_content_div").slideDown("slow");
					
					//jQuery('#intialSlide').append("<div id='accPersonalize' class='easyui-accordion' style='width:300px;height:300px;'><ul><li>ssss</li><li>dfdf</li><li>sssds</li></div>");
				});
				
				jQuery('#hideAccdrn').click(function(){

					//jQuery('#dasbrdIcon').css('display','none');
					});
				jQuery('#helpLink').click(function(){
					/*jQuery( "#DivHelp" ).show();
					jQuery( "#DivHelp" ).dialog({
							autoOpen: false,
							show: "blind",
							hide: "explode",
							height: 520,
							width: 850,
							left:30,
							top:30,
							modal: true,
							title:"Help",
							onClose:function(){}
					});*/
					
					var url = "help_input.dashboard?" + 'dataUrl='+jQuery('#hiddenUrl').val();
					//window.open(url, 'Help', 'window settings');
					var helpScreen =window.open(url, 'Help', 'left=130,top=100,width=800,height=400,toolbar=0,scrollbars=0,status=0â€‹');
					helpScreen.document.write(jQuery('#hiddenUrl').val());
					//helpScreen.document.close();
					
					return false;
					//LoadForm("loadHelp","preloadHelp",url,"dispErr","Help_successCallback");

				});
				//closePopUpDialoge("LoaddefContenpwd");		
				var chgpwd = jQuery("#hdnPassword").val();
				//chgpwd = "false";
						
				if(chgpwd.trim() == "true")
				{
					
				  
					
					
					//function LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname){ /*** Classname added By S.SugunaDevi***/ 
					//alert('WELCOME');
					//LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname)
					//LoadPopUp("LoaddefContenpwd","changePswd.userLogin?button=Y","true","900px","350px","0px","200px","","Change Password","","",true);
					//closePopUpDialoge("LoaddefContenpwd");			
					LoadPopUp("LoaddefContenpwd","changePswd.userLogin?button=Y&frmchngpwd=Y","true","900px","350px","0px","200px","","Change Password","","",false);
					//chngpwd();
					//LoadPopUp("","changePswd.userLogin",true,"350px","350px","50px","200px","");
					//LoadPopUpForPassword("LoaddefContenpwd","changePswd.userLogin?button=Y&frmchngpwd=Y","true","900px","350px","0px","200px","","Change Password","","",true);
				} else
				{
					
					//openSetHomePage();
					processAjaxCalls("CheckPwdExpires.userLogin","q=2","chkPwdOnsuccessCallBack","onerrorCallBack");
					
					jQuery('#slid-top-icon').click(function(){
						jQuery(this).removeClass('leftArrow');	
						jQuery(this).addClass('rightArrow');
						});
					
					 //LoadPopUp("loadBase", "empEqp_input.base", " ","92%","78%","11%","30", "","Base Form");
					 //closePopUpDialoge("loadBase");
					//processAjaxCalls("reminder_getData.alerts","fmode=login","reminderSuccCallBack");
			    openSetHomePage();
				/*setTimeout(function() {
				                    openSetHomePage();
				                }, 100);*/
				}

			});
            
            function onerrorCallBack(result)
            {
            	alert("error"+result)
            }
 
           /* function chkPwdOnsuccessCallBack(result)  commented by kiran
            {
                 var msg = result.successData.msg;
            	alert("successcallback")
            	var limit = result.successData.limit;
            	//alert(limit);
            	alert("successcallback1")
            	if(parseInt(limit) > 0)
            	{
            		msg = msg + " " + limit + " Days";
            	}
            	
            	
            	alert(msg); 
            	
            	
            	if(parseInt(limit) <= 0)
            	{
            		LoadPopUp("LoaddefContenpwd","changePswd.userLogin?button=Y","true","900px","350px","0px","200px","","Change Password","","",false);
            	}
            	
            }*/

			function chkPwdOnsuccessCallBack(result)  
			            {
			                 var msg = result.successData.msg;
			            	// alert("successcallback")
			            	var limit = result.successData.limit;
			            	//alert(limit);
			            	// alert("successcallback1")
			            	if(parseInt(limit) > 0)
			            	{
			            		msg = msg + " " + limit + " Days";
			            	}
			            	
			            	
			            	alert(msg); 
			            	
			            	
			            	if(parseInt(limit) <= 0)
			            	{
								jQuery("#hdnPassword").val("true");
			            		LoadPopUp("LoaddefContenpwd","changePswd.userLogin?button=Y","true","900px","350px","0px","200px","","Change Password","","",false);
			            	}
			            	
			            }
           
            
            function reminderSuccCallBack(result){
            	 alert("Remainder");
            	if(result.tblLength>0)
            		alert("result"+result);
            		jQuery('#sidecontent_0_pullout').trigger('click');
            }

            function chngpwd(){
            	
            // on 260525	
			LoadPopUp("LoaddefContenpwd","changePswd.userLogin?button=Y&frmchngpwd=Y","true","900px","350px","0px","200px","","Change Password","","",true);
            }
 			
 			function dashBoard_successCallback(response){
        		
    			
			}
        	function onerrorCallBack(){}
			function loadpopUpSuccessCallBack()
			{}
			jQuery("#spnChgPwd").click(function(){
            	
        	//	LoadPopUp("LoadContenpwd","changePswd.userLogin?button=Y",true,"900px","350px","0px","200px","loadpopUpSuccessCallBack","Change Password","","",false);
            	});
        	jQuery("#spnClose").click(function(){
            	
        		pwdAlertContentShowHide();
        	});
			function Help_successCallback(response){
				
				if( response.dataNotExist  ){
					showCommonErrorMsg(response.dataNotFoundMsg);
					jQuery( "#DivHelp" ).dialog("close");
				}	
			}
			 
			function removStyle(){
				 jQuery('.clsLi').css('background-color','#F1F3F5'); 
				 jQuery('#dashboard_grphcontent').css('border-left','solid 5px #fff');
				}
			function pwdAlertContentShowHide()
			{	        	  
				jQuery('#passwordSlideContainer').css('display','none');
				jQuery('#passwordSlideContainer').animate({ height: '0px' });
				
             }
          
          function hideAccdn(){
        	  	jQuery('#safetydiv')
	      	
	      		.animate({"width": "0"}, 500);
	      		jQuery('#pillarDiva')
	      	
	      		.animate({"width": "0"}, 500);
	      		setTimeout(function() {
		        		jQuery('#safetydiv').css('display','none');
		        		jQuery('#dashboardleft_header').css('display','none');
		        		jQuery('#dashboardright_header').css('display','block');
		        		jQuery('#dashboard_grphcontent').css('width','100%');
		        		jQuery('#dashboard_grphcontent').css('margin-right','-1');
		        		jQuery('#leftRightDiv').css('margin-top','0');
		        		jQuery('#leftRightDiv').css('float','left');
		        		//jQuery('.dashboard-cell').css('width','600px');
		        		jQuery('#dashboard_header').css('display','block');
		        		if(screen.width <= 1366)
		        			jQuery('#dashboard-grid').css('margin-left','4%');
	      		},450);
      

              }
          function showAccdn(){
        	  jQuery('#safetydiv')
      		.animate({"height": "100"}, 500)
      		.animate({"width": "150px"}, 500);
        	  jQuery('#pillarDiva')
      		.animate({"height": "100"}, 500)
      		.animate({"width": "50%"}, 500);
      		
      		setTimeout(function() {
	        		jQuery('#safetydiv').css('display','block');
	        		jQuery('#dashboardleft_header').css('display','block');
	        		jQuery('#dashboardright_header').css('display','none');
	        		jQuery('#dashboard_grphcontent').css('width','85%');
	        		jQuery('#dashboard_grphcontent').css('margin-right','6px'); 
	        		jQuery('#dashboard_grphcontent').css('margin-right','0');
	        		jQuery('#leftRightDiv').css('margin-top','-55%');
	        		jQuery('#leftRightDiv').css('float','right');
	        		//jQuery('.dashboard-cell').css('width','500px');
	        		//jQuery('.dashboard-cell').css('-moz-box-flex','1');
	        		jQuery('#dashboard_header').css('display','none');
	        		if(screen.width <= 1366)
	        			jQuery('#dashboard-grid').css('margin-left','0%');
      		},450);

              }
          function acordian_select(){
           var pillrCde = jQuery('.pillarClick').attr('pillarCode');
           
			if(pillrCde == '' || pillrCde == undefined || pillrCde == null)
     	  	processAjaxCalls("getPillars.dashboard","","DashBoardPillars_successCallback");
			
          	/*jQuery('#chartAccordian').accordion({
			         onSelect:function(title){
			            if(title == 'Safety'){
			            	processAjaxCalls("getPillars.dashboard","","DashBoardPillars_successCallback");
				          }
			         }
			      });*/
              }
         /*function DashBoardPillars_successCallback(result){
           
          	var pillarDet;
          	
	            	for( var i = 0; i< result.length ; i++){
	            		pillarDet = result[i];
	            		//alert(i+" = "+pillarDet +" -- "+pillarDet[2]);
	            		var chrtDta = jQuery('#hdnchartData').val() ;
	            		//var chrtDta_obj = JSON.parse(chrtDta);
	            		//jQuery("#safetydiv").append('<div class="pillarDiva" style="margin-top:1%;"> <a href="#" class="pillarClick" pillarCode="'+ pillarDet[1]  +'"  onclick =btnClickgrph("'+ pillarDet[1]  +'");  >'+ pillarDet[0] +'</a></div>');'+chrtDta_obj+'
	            		jQuery("#plrNameDiv").append('<input type="text" id="plrName_'+ pillarDet[1]  +'" value="'+ pillarDet[0]  +'"/>');
	            		jQuery("#DBaccordian").append('<li><div class="clsLi" id="lidiv_'+ pillarDet[1]  + '" onclick =btnClickgrph("'+ pillarDet[1]  +'");><a href="#" class="pillarClick" pillarCode="'+ pillarDet[0]  +'"    >'+ pillarDet[0] +'</a></div><ul id="'+ pillarDet[1]  +'" class=""></ul></li>');
	            		
	            	}
          	
          }*/
                   
            
            
            function fullScrn(fpTop,filpHeight,fpHeight,fpMtop,psTop){
            	var sh= screen.availHeight;
            	jQuery('.filterpanel').css('top',fpTop);
            	jQuery('.filterpanel').css('height',filpHeight);
	        	jQuery('.filterTab_border').css('height',fpHeight);
	        	jQuery('.filterTab_border').css('margin-top',fpMtop);
	        	jQuery('.layout-panel-south').css('top',psTop);
	        	jQuery('#mainlayout').layout('panel', 'west').panel('resize',{height:sh});
	            jQuery('#mainlayout').layout('panel', 'center').panel('resize',{height:sh});

            }
            function forcerFullscreen(){
            	top.resizeTo(window.screen.availwidth,width.screen.availHeight);
                    top.moveTo(0,0);
            	setTimeout("forceFullscreen()",250);
            }
            jQuery(document.documentElement).bind('keydown',function(e){
					//alert(e.keyCode );
           	 if (e.keyCode == 122) {
               	var heightH =Math.round(jQuery('div.alert_content_div').height() / jQuery('div.alert_content_div').parent().height() * 100);
               	if( heightH =="80" ){ 
           			jQuery("div.alert_content_div").css("height","83.6%");
           			jQuery("div.dashbord_content_div").css("height","83.6%");
               	}	
               	else{
               		jQuery("div.alert_content_div").css("height","80%");
               		jQuery("div.dashbord_content_div").css("height","80%");
               	}
               	//jQuery("#alert_content").css("height","97%");
           		jQuery('#mainlayout').layout.panel('resize',{resizable:true});}
           	   //jQuery('#mainlayout').layout.panel('resize').trigger();
           		/*  var sh= screen.availHeight;
	  	        var wh = jQuery(window).height();
	  	        console.log(" : h = " + sh );
	  	    	console.log(" : wh = " + wh );  }
            	      
            	    	if(wh > 350 && wh < 650){
            	    		fullScrn('11.5%','84%','480px','3%','732');
            	        }
            	        else {
            	        	fullScrn('15%','79%','420px','0%','564');
            	        }
            	 }
            */	 
            	if(handleBrowserKeyPress(e) == true)
                {
                    e.preventDefault();
                }
            });            
            
            function loadCommonFilter()
            {
                      jQuery("#filterPanel").show();
                            jQuery("#loadFilter").show();
                                  if( jQuery("#filterUrl").val() != null && jQuery("#filterUrl").val() != "" )
                            {        
                                        var relatedFilterHeader = jQuery("#filterUrl").val().replace(" ","");
                                      var curUrl = jQuery("#curUrl").val();
                                        if( (jQuery('#isCommonFilterSlideOpen').val().length <=0 || jQuery('#isCommonFilterSlideOpen').val() != "Y")  ){
                                                   var url = "get_inputForm.commonFilter?loadContentDivId=loadFilter&preLoadContentDivId=preLoadFilter&formId=commonFilter&notSetHiddenUrl=true&isHidePrevForm=false";
                                                   navigateToNextForm(url,"" ,null ,null,"commonFilterNavigateToNext_SuccessCalBack","commonFilterNavigateToNext_ErrorCalBack");
                              }
                              else{
                                      var formNavig = new formNavigation();
                                      formNavig.URL = "s?loadContentDivId=loadFilter&preLoadContentDivId=preLoadFilter&formId=commonFilter&notReloadFrm=true&isHidePrevForm=false";
                                      formNavig.divId="loadFilter";
                                      if( formNavigations == null )
                                              formNavigations = [];
                                      formNavigations.push(formNavig);
                                      
                              }                                                      
                                                
                            }
            }
    
          /*function loadCommonFilter()
          {
        	    jQuery("#filterPanel").show();
        	  	jQuery("#loadFilter").show();
      	  		if( jQuery("#filterUrl").val() != null && jQuery("#filterUrl").val() != "" )
        	  	{	
      	  	      	var relatedFilterHeader = jQuery("#filterUrl").val().replace(" ","");
		    		var curUrl = jQuery("#curUrl").val();
		      		if( (jQuery('#isCommonFilterSlideOpen').val().length <=0 || jQuery('#isCommonFilterSlideOpen').val() != "Y")  ){
					 	var url = "get_inputForm.commonFilter?loadContentDivId=loadFilter&preLoadContentDivId=preLoadFilter&formId=commonFilter&notSetHiddenUrl=true&isHidePrevForm=false";
					 	navigateToNextForm(url,"" ,null ,null,"commonFilterNavigateToNext_SuccessCalBack","commonFilterNavigateToNext_ErrorCalBack");
	        	    }
	        	    else{
	        	    	var formNavig = new formNavigation();
	        	    	formNavig.URL = "s?loadContentDivId=loadFilter&preLoadContentDivId=preLoadFilter&formId=commonFilter&notReloadFrm=true&isHidePrevForm=false";
	        	    	formNavig.divId="loadFilter";
	        	    	if( formNavigations == null )
	        	    		formNavigations = [];
	        	    	formNavigations.push(formNavig);
	        	    	
	        	    }	        	    	  		
    	  				
        	  	}
          }*/

        function resizeFrame() 
      	{
      	    var h = jQuery(window).height();
      	    var w = jQuery(window).width();
      	    jQuery('#home_center').css("width","95%");
      		if ((screen.width >= 1200)  && (screen.colorDepth>12))
       		{
      	         jQuery("link[rel=stylesheet]:first").attr({href : "css/tpm-style-1366.css"});
      	        
       		}
       		else
       		{
					/*css code for resizing the west menu panel when the resolution is changed to 1024*/
           		jQuery('.searchbox-text').css('width','220px');/*Added(Modified on 17th jun) by Manikandan on jun 16th*/
           		jQuery('#accPersonalize').css('width','308px'); /*(Modified on 17th jun)*/
           		jQuery('#accPersonalize').css('border-right','1px solid #B6BABF');/*(Modified on 17th jun)*/
           		jQuery('#accPersonalize').accordion('resize'); 
//           		jQuery('#mainlayout').layout('panel', 'west').panel('resize',{width:363});/*Changed by Manikandan on jun 16th*/
//           		jQuery('#mainlayout').layout('panel', 'center').panel('resize',{width:796});
           		//jQuery('#mainlayout').layout('panel', 'center').panel('resize',{left:363});/*changed by Manikandan on jun 16th*/
       			jQuery("link[rel=stylesheet]:first").attr({href : "css/tpm-style-1024.css"});//calling the css file for 1024 resolution
       			/*setting the width for center layout for 1024 resolution when the west menu is resized*/
       			jQuery('#mainlayout').layout('panel','west').panel({
         				onCollapse:function(){
                			jQuery('#home_center').css("width","95%");
                 		},
              		onExpand:function(){
	                    	jQuery('#home_center').css("width","95%");
              		},

              		onResize:function(){              			
						jQuery('#home_center').css("width","95%");
						jQuery('#accPersonalize').accordion('resize'); 
              		}
       			 });
       		}  
      	        	    
      	}          
function commonFilterNavigateToNext_SuccessCalBack(result){
	 jQuery('#isCommonFilterSlideOpen').val("Y");	
	 jQuery("#relatedFilterTab").val(jQuery("#filterUrl").val());
}

function commonFilterNavigateToNext_ErrorCalBack(result){
	 jQuery('#isCommonFilterSlideOpen').val("Y");	
	 jQuery("#relatedFilterTab").val(jQuery("#filterUrl").val());
}
function commonFilter_beforeCloseCurrentForm(){

	toggleCommonFilter(false);
	return false;
}
/* Creatd By KarthicK.T */
/*function chngpwd(){
	
	LoadPopUp("","changePswd.userLogin",true,"350px","350px","50px","200px","");	
}*/
     