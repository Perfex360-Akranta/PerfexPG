<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<noscript>
<META HTTP-EQUIV="Refresh" CONTENT="0; URL=checkjsEn.brwSettings">
</noscript>
<head>
<style>
/**toolTip*/
#pwdcpsOn{display:inline;position:relative;
 	text-decoration:none;
   background:#111;
   background:/*rgb(249, 219, 47,.8)*/#F9D663;
   border-radius:3px;
   color:RED;
   content:attr(title);
   display:block;
   right:-12%;
   bottom:38%;
   padding:5px 5px;
   position:absolute;
   white-space:nowrap;
   z-index:98;
   box-shadow: 0 2px 5px #CC0000;
   
  }
 
 /* .tooltipC:hover:before{*/
 .tipArrow{
    border:solid;
    border-color:#ff0000 transparent;
    border-width:10px 10px 0 10px;
    bottom:-10px;
    content:"";
    display:block;
    /*left:75%;*/
    position:absolute;
    z-index:99;
    
   }
/***/
.lblLog  { vertical-align: 8px;
    padding-right: 5px; color:#1074AF}
.lblakaLog  { vertical-align: 20px;
padding-right: 5px;color:#1074AF; }
.logLoading{
		left: 27%;
		position:absolute;
		z-index:1000;
		width: 132px;
		height:100px;
		margin-top:0px\9;		
		background: url('images/processin.gif') no-repeat;
		cursor: wait;
		text-shadow: 0px 1px 0px #fefefe;   
		display:none; 	     
} 

.lblSupport  { vertical-align: 20px;
padding-right: 5px;color:#1074AF; 
font-size: 14px;}
</style>
<title>Perfex</title>
<LINK REL="SHORTCUT ICON"   style="width:100px"   HREF="images/faviconTPM.png"/>
<link rel="stylesheet" type="text/css" href="css/tpm-style.css">     
<link rel="stylesheet" type="text/css" href="css/login_slide/login_style.css"/>
<link rel="stylesheet" type="text/css" href="css/login_slide/global-1366.css"/>
<link id ="size-stylesheet" rel="stylesheet" href="">
<!--<link rel="stylesheet" media="screen and (min-device-width: 800px)" href="css/login_slide/global.css" />	-->
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<script src="js/login_slide/slides.min.jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" >
	
	 jQuery(document).ready(function(){	
		//alert(navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/));
		 preloder(2500);
		 
		//	document.getElementById("btnLogin").click();

				 var url=jQuery('#tempUrl').val();

		 var userName=jQuery('#tempUserId').val();
		 var password=jQuery('#tempPassword').val();
		 
		// alert(userName +"  "+password);
		//var email="kirannikam.atpl@gamail.com";
		if(url=="ChangeRequest_input.nmoc"){
		if(userName!==null||userName!=="" && password!==null || password!=""){
			jQuery('#txtUserName').val(userName); 
			jQuery('#txtPswd').val(password);

			document.getElementById("btnLogin").click();
		
		}
		}
		 if (jQuery.browser.msie)  
         	jQuery('#ttTd').css('padding-left','5%');
		 jQuery('#loding_div').removeClass('.tpm-loading');
		 jQuery('#loginDiv').css('display','block');
		 jQuery(document).bind("contextmenu",function(e){
 	        return false;
		 }); 	    
		 adjustStyle(jQuery(this).width());
		 jQuery(document).keydown(function(e) {
			    if (e.keyCode == 20) {
			       
			       jQuery('#pwdcpsOn').css('display','none');
			    }    
			     
		 });
		 jQuery("#txtUserName").focus();
		
		 function preloder(time){
			 jQuery('#loding_div').addClass('.tpm-loading');
				jQuery("#loginDiv").fadeOut(0, function(){
					jQuery(this).fadeIn(time); 
				});
				
			}
		 jQuery("#login").submit(function(){  
		 
    		jQuery(".tpm-error").removeClass("tpm-error");
    		jQuery('div[id^="err_"]').css("display","none");
    		
    		jQuery('.logLoading').css('display','block');
    		 if (jQuery.browser.msie) {
    			//alert("while submit"+jQuery('#tt').css('width'));
    		 jQuery('#loginDiv').append('<div class="window-mask " style="display:block;opacity:0.4; "></div>') ;
		  	      jQuery('.window-mask').css('height','94%');
		  	      jQuery('.window-mask').css('width','36%');
	 			  jQuery('.window-mask').css('top','20.5%');
	 			  jQuery('.window-mask').css('left','63.1%');
	 			  jQuery('.window-mask').css('z-index','100');
    		 }else{
        		 jQuery('.logLoading').css('top','10%');
    			 jQuery('#tt').append('<div class="window-mask " style="display:block;opacity:0.4; "></div>') ;
    			 
        	 }

    		
    		//below line is commented for IE login
    		
    		//jQuery('#tt').append('<div class="window-mask " style="display:block;opacity:0.1;height:100%"></div>') ;

    		jQuery('#btnLogin').css('color','#c1c1c1');
    		//jQuery('#btnLogin').attr('disabled','disabled');
    		
			 jQuery.ajax({  
		            type: "POST",
		            xhr:window.ActiveXObject?
		    		    	function(){
		    		    	   try{
		    		    		   return new window.ActiveXObject("Microsoft.XMLHTTP");
		    		    		   }
		    		    	   catch(e){}
		    		    	   }:function(){return new window.XMLHttpRequest()},  
		            url: "validate.userLogin",  
		            data: jQuery("#login").serialize(),  
		            dataType: "json",  
		            success: function(result){  
		            	if( result.exception )
		            	{	
			            	jQuery('#tt').css('opacity','1');
			            	jQuery('.logLoading').css('display','none');
			            	jQuery('.window-mask').css('display','none');
			            	jQuery('#btnLogin').css('color','#000');
			            	//jQuery('#btnLogin').removeAttr('disabled');
		            		var validMsgs = result.messages;
		            		//alert(" Validation Msg "+validMsgs);
		            		var html = [];
		            		var errId ;
		            		var errid ;
		            		var errDiv;
		            		var errmsg ;
		            		
		            		for( var i = 0; i <validMsgs.length ;i++)
		            		{	 
			                     //alert(" Inside for "+i);
			            		 jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
			            		 errId = '#'+validMsgs[i][0];
			            		// alert(" Inside for errId "+errId);
			            		 errid = validMsgs[i][0];
			            		// alert(" Inside for errid"+errid);
			            		 errMsg=validMsgs[i][1];
			            	//	 alert(" Inside for errMsg"+errMsg);
			            		 errDiv = '<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>';
			            		 //alert("errDiv"+errDiv);
			            		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 ){
				            		
			            			//alert(" Inside if "+i);
			            			if(jQuery(errId).html().length==0){
			            				
			            				  jQuery(errId).after(errDiv );

				            			}else{
			            			jQuery(errId).html(' ');
				            			}
								   
			            		}
			            		//alert("1111111111111");
			            		jQuery('#err_'+errid ).css("display","block");
			            		//jQuery('#err_'+errid).html(jQuery('#err_'+errid).html()+errMsg); This line commented for login password Msg.
			            		jQuery('#err_'+errid).html(errMsg);
			            		
			            	
			            		
			            		
			            		
			            		if (jQuery.browser.msie) {
					            	//alert(errMsg);
					            	 
					            	jQuery('#ttTd').css('position','relative');
					            	jQuery('#tt').css('position','absolute');
					            	//jQuery('#tt').css('left','-250');
					            	jQuery('#tt').show();
				            	}
			            		
			            		if("User Account is Locked"==errMsg)
			            			{
			            			jQuery("#btnForgetPwd").removeAttr("disabled");
			            			}
				            	
		            		}
		            		
		            	}
		            	else{
		            		//document.location.href = "perfex?tpmuser="+result.tpmuser;
		            	    document.location.href = "perfex";
		            		//alert(result.tpmuser);
		            		//jQuery("#tpmuser").val(result.tpmuser);
		            		//window.open("perfex?tpmuser="+result.tpmuser,"","fullscreen=yes");
		            		//jQuery("#loginDiv").html("");
		            	}
		            		
		            },  
		            /*error: function(msg){
		            	//alert(msg);
		            	//alert(" e " + msg.exception);
		            	
		            	
		            	  
		            },*/
		            error:function(jqXHR, textStatus){
		                if(textStatus == 'timeout')
		                {     
		                     alert('Failed from timeout');         
		                    //do something. Try again perhaps?
		                }
		            },
		            Timeout:1  
		        });  
		  
		        //make sure the form doesn't post  
		        return false;  
		  
		    });
		 jQuery("#txtUserName").blur(function() {
			 	jQuery("#btnForgetPwd").css({"background-color": "#FFFFFF"}).css({"color":"#000000"});
			 	jQuery("#btnForgetPwd").attr("disabled", false).removeClass("ui-state-disabled");
			   // alert("The text has been changed.");
			});
		 jQuery("#txtUserName").keypress(function(){
			  //jQuery("#btnForgetPwd").on();
			  //enableUIButton("btnForgetPwd");
			  jQuery("#btnForgetPwd").css({"background-color": "#FFFFFF"}).css({"color":"#000000"});
			  jQuery("#btnForgetPwd").attr("disabled", false).removeClass("ui-state-disabled");
			   // alert("The text has been changed.");
			    //document.location.href = "perfex";
			});
		    /* .one( "click", function()*/
		 //**********//
		 		// jQuery("#btnForgetPwd").click(function(){  
		jQuery("#btnForgetPwd").click(function(){ 
			//jQuery("#btnForgetPwd").one( "click", function(){ 
		 			  jQuery("#btnForgetPwd").css({"background-color": "#FFFFFF"}).css({"color":"#666666"});
		 			  jQuery("#btnForgetPwd").attr("disabled", true).addClass("ui-state-disabled");		 		   	
		 		  // alert("User wants to continue!");
		 		   
			  jQuery.ajax({  
		            type: "POST",
		            xhr:window.ActiveXObject?
		    		    	function(){
		    		    	   try{
		    		    		   return new window.ActiveXObject("Microsoft.XMLHTTP");
		    		    		   }
		    		    	   catch(e){}
		    		    	   }:function(){return new window.XMLHttpRequest();},  
		            url: "forgotvalid.userLogin",  
		            data: jQuery("#login").serialize(),  
		            dataType: "json",  
		            success: function(result){  
		            	if( result.exception )
		            	{	
			            	jQuery('#tt').css('opacity','1');
			            	jQuery('.logLoading').css('display','none');
			            	jQuery('.window-mask').css('display','none');
			            	jQuery('#btnLogin').css('color','#000');			            	
		            		var validMsgs = result.messages;
		            		//alert(" Validation Msg-------- "+validMsgs);
		            		var html = [];
		            		var errId ;
		            		var errid ;
		            		var errDiv;
		            		var errmsg ;		            		
		            		for( var i = 0; i <validMsgs.length ;i++)
		            		{	 
			                    // alert(" Inside for "+i);
			            		 jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
			            		 errId = '#'+validMsgs[i][0];
			            		 errid = validMsgs[i][0];
			            		 errMsg=validMsgs[i][1];
			            		 errDiv = '<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>';
			            		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 ){
				            		
			            			if(jQuery(errId).html().length==0){
			            				
			            				  jQuery(errId).after(errDiv );

				            		}else{
			            					jQuery(errId).html(' ');
				            		}
			            		}
			            		
			            		jQuery('#err_'+errid ).css("display","block");
			            		jQuery('#err_'+errid).html(jQuery('#err_'+errid).html()+errMsg); //This line commented for login password Msg.
			            		jQuery('#err_'+errid).html(errMsg);
			            
			            		if (jQuery.browser.msie) {
					            	alert(errMsg);
					            	jQuery('#ttTd').css('position','relative');
					            	jQuery('#tt').css('position','absolute');
					            	
					            	jQuery('#tt').show();
				            	}
		            		}
		            		
		            	}
		            	else if(result.succ){
			            	//alert("Test");
		            		//alert(result.succ);			            	
			            	//document.location.href = "perfex";
							var retVal = confirm("Your password will be reset, are you sure to continue?");			        		 		
			        		if( retVal == true ){
				            	jQuery.ajax({  
				                    type: "POST",
				                    xhr:window.ActiveXObject?
				            		    	function(){
				            		    	   try{
				            		    		   return new window.ActiveXObject("Microsoft.XMLHTTP");
				            		    		   }
				            		    	   catch(e){}
				            		    	   }:function(){return new window.XMLHttpRequest()},  
				                    url: "forgotpwd.userLogin",  
				                    data: jQuery("#login").serialize(),  
				                    dataType: "json",  
				                    success: function(result){			                    	
				                    	if(result.succ){
					                    	//alert("Test2");
				                      		alert(result.succ);			            	
				                          	document.location.href = "perfex";
				                         }
				                    	else
				                    		alert("Mail Not Sent Contact FMS Team");
				        		 		}
				                    , 
				                 
				                    error:function(jqXHR, textStatus){
				                    	alert("Mail Not Sent Contact FMS Team");
				                        if(textStatus == 'timeout')
				                        {     
				                             alert('Failed from timeout');         
				                           
				                        }
				                    },
				                    Timeout:1  
				                }); 
		            		}
			                
			            	
			            }
		            },  
		            error:function(jqXHR, textStatus){
		            	alert("Mail Not Sent Contact FMS Team");
		                if(textStatus == 'timeout')
		                {     
		                     alert('Failed from timeout');         
		                   
		                }
		            },
		            Timeout:1  
		        });  
				 
		        return false;  
				 
		     });
		 //*********//
	
	
	 });
		 	

		$(function(){
			$('#slides').slides({
				preload: true,
				preloadImage: 'img/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true,
				animationStart: function(current){
					$('.caption').animate({
						bottom:-35
					},100);
					if (window.console && console.log) {
						// example return of current slide number
						console.log('animationStart on slide: ', current);
					};
				},
				animationComplete: function(current){
					$('.caption').animate({
						bottom:0
					},200);
					if (window.console && console.log) {
						// example return of current slide number
						console.log('animationComplete on slide: ', current);
					};
				},
				slidesLoaded: function() {
					$('.caption').animate({
						bottom:0
					},200);
				}
			});
		});
		function adjustStyle(width) {
		    width = screen.width;
		     
		    if ( parseInt( width,10) > 1200) {
		    	 if (jQuery.browser.msie){
					 jQuery(".tooltipC").css("right","0px");
					 jQuery(".tipArrow").css("margin-bottom","-15px");
					 jQuery(".tipArrow").css("right","72px");
					 
					 }
		    	   
		        jQuery("#size-stylesheet").attr("href", "css/login_slide/global-1366.css");
		        jQuery("#prefex_logo").attr("src","images/perfexlog_2.png");
		        jQuery("#itc_logo").attr("src","images/ITClogo.png");
		        
		    }
		     else 
			 {  
				 jQuery(".log_main_box").css("margin-left","-1%");
			 if (jQuery.browser.msie){
				 jQuery("#tt").css("margin-left","15%");
				 jQuery("#tt").css("width","87%");
				 
				 } 
			 if (jQuery.browser.msie){
				 jQuery(".tooltipC").css("right","20px");
				 jQuery(".tipArrow").css("margin-bottom","-15px");
				 jQuery(".tipArrow").css("right","75px");
				 
				 }
		    	jQuery("#size-stylesheet").attr("href", "css/login_slide/global.css");
		    	 jQuery("#prefex_logo").attr("src","images/perfexlog-1024.png");
		    	 jQuery("#itc_logo").attr("src","images/ITClogo.png");
		    }
		}

		jQuery(function() {
		    adjustStyle(jQuery(this).width());
		    jQuery(window).resize(function() {
		        adjustStyle(jQuery(this).width());
		    });
		});
		 /**Caps Lock on Indicator function**/
     function IScapLock(e,seltd) {
       kc = e.keyCode ? e.keyCode : e.which;
       sk = e.shiftKey ? e.shiftKey : ((kc == 16) ? true : false);
       	   
       if (((kc >= 65 && kc <= 90) && !sk) || ((kc >= 97 && kc <= 122) && sk)){
			if(seltd=='pwd'){
	        	jQuery('#pwdcpsOn').css('display','block');
			}
        }
        else{
        	if(seltd!='pwd')
       			jQuery('#pwdcpsOn').css('display','none');
         }
  	  }
        /*END**/
	</script>
	</head>
	<body   style="padding: 0px;margin: 0px;background-image:url('images/bg1.jpg');scroll 0 0;overflow:hidden;">
	
	<form method="post" id="login" onsubmit="btnForgetPwd.disabled = true" >
		<div id="loding_div" class=""></div>
		<div id = "loginDiv" class="log_main_box" style="display:none; " >
	<table >	
	
		<tr style="width:100%">
			<td>
				<span id="preFex_span" style="">
					<img id="itc_logo" alt="Perfex" src=""/>
					<span style="padding-left: 500px;" > <img id="prefex_logo" alt="Perfex" src=""/>
					</span>
				</span>
			</td>
		</tr>
		<tr style="width:70%">
			<td style="width:70%" >
     		<div id="wowslider-container1"  style="margin:1% 0% 0% 2%">
<div class="ws_images">
				<ul>
				<!-- for loading -->
				<li style="align:center;padding-top:150px;padding-left:170px;">
					<img src="images/home-page/ajax-loader.gif" alt="" title="" id=""/>
				</li>
				
				<c:forEach items="${loginSlideImgs}" var="current" varStatus="iCount">
			       <li><img src="images/loginslide/${current}" alt="" title="" style="width:710px;height:431px;" id="wows1${iCount.count}"/></li>
			    </c:forEach>
				<!-- <li><img src="images/home-page/images/itc1.jpg" alt="" title="" id="wows0"/></li>
				<li><img src="images/home-page/images/itc2.jpg" alt="" title="" id="wows1"/></li>
				<li><img src="images/home-page/images/itc3.jpg" alt="" title="" id="wows2"/></li>
				<li><img src="images/home-page/images/itc4.jpg" alt="" title="" id="wows3"/></li>
				<li><img src="images/home-page/images/itc5.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc6.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc7.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc8.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc9.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc10.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc11.jpg" alt="" title="" id="wows4"/></li>
				<li><img src="images/home-page/images/itc12.jpg" alt="" title="" id="wows4"/></li>
				 -->
				</ul></div>
				<div class="ws_bullets"><div>
				<a href="#" title=""><img src="images/home-page/tooltips/img4.jpg" alt=""/>1</a>
				<a href="#" title=""><img src="images/home-page/tooltips/img5.jpg" alt=""/>2</a>
				<a href="#" title=""><img src="images/home-page/tooltips/img0.jpg" alt=""/>3</a>
				<a href="#" title=""><img src="images/home-page/tooltips/img2.jpg" alt=""/>4</a>
				<a href="#" title=""><img src="images/home-page/tooltips/img3.jpg" alt=""/>5</a>
				</div></div>
		
			<div class="ws_shadow"></div>
			</div>
			<script type="text/javascript" src="js/login_slide/wowslider.js"></script>
			<script type="text/javascript" src="js/login_slide/script.js"></script>
	<!-- End WOWSlider.com BODY section -->
<!--			<img src="images/login_slide/example-frame.png" width="700" width="550" alt="Example Frame" id="frame">-->

        </td>
		
			<td id="ttTd" width="340\9px;">
			<div id="tt" style="position:relative;">
            <div class="p10" style="padding-top:0px; ">
            <img alt="" src="images/home-page/key_signin1.png"/>
           			<span class="lblM" style="position:absolute;margin-top:4px;padding-left:5px;"><h4>Sign in to Perfex</h4></span>
            </div> 
        <!--     <div class="p10" style="padding-bottom: 1px;margin-top: 6px; ">
            <span class="lblM">Server</span>
            </div> 
            <div style="padding-bottom: 2px;padding-right:10px;"> 
              <select id="cmbServer" name="cmbServer" class="easyui-combobox"  style="width:250px;" disable="true"   >
               <option>RRL</option>
               </select>
            </div>
             -->
             <div id="divLocation" style="display: none;">
            	<div class="p10" style="padding-bottom: 2px;padding-top: 10px;">
            		<span class="lblM">Location</span>
            	</div> 
                <div style="padding-bottom: 2px;padding-right:10px; width:10%;"> 
                    <select id="cmbLocation" name="cmbLocation" class="easyui-combobox"  style="width:250px;"    >
                    <option value="BCM-PSPD">BCM-PSPD</option>
                    <option value="TBN-PSPD">TBN-PSPD</option>
<!--                     <option value="VASAI">VASAI</option> -->

                    </select>
               </div>
             </div>
            <div class="p10" style="padding-bottom: 2px;padding-top: 10px;">
            <span class="lblM">Environment</span>
            </div> 
                <div style="padding-bottom: 2px;padding-right:10px;width:10%;"> 
                    <select id="cmbEnvironment" name="cmbEnvironment" class="easyui-combobox"  style="width:250px;"   >
					 <option value="QUALITY">QUALITY</option>
					 <option value="PRODUCTION">PRODUCTION</option>    
                     <option value="TEST">TRAINING</option>                  
                 
                    </select>
                </div>
                <div style="">
                <div class="p10" style="padding-bottom: 2px; padding-top: 10px;"><span class="lblM">User Name</span></div> 
                <div style="padding-bottom: 2px;margin-left:0%;padding-right:10px;">
                
                <input type="text" id="txtUserName" value="" name="txtUserName" class="easyui-text" style="width: 250px;text-transform: uppercase;"  />
                </div>
<!--                <div id="err_txtUserName" class="tpm-errormsg"></div>-->
                <div class="p10" style="padding-bottom: 2px;padding-top: 10px;"><span class="lbl">Password</span></div> 
                <div style="padding-bottom: 2px;padding-right:10px;"> 
                    <span id="pwdcpsOn" class="tooltipC" style="display:none;">Caps Lock On<span class="tipArrow" ></span></span>
                    <input type="password" id="txtPswd" value="" class="easyui-text" name="txtPswd" style="width:250px;"  onkeypress="IScapLock(event,'pwd');" /> 
                </div>
<!--                <div id="err_txtPswd" class="tpm-errormsg"></div>					-->
               <!--  <div class="p10" style="padding-bottom: 2px; "><span class="lblM">Language</span></div> 
                <div style="padding-bottom: 2px;padding-right:10px;"> 
                    <select id="cmbLanguage" name="cmbLanguage" class="easyui-combobox"  style="width:250px;" disabled="true" >
                    <option>ENGLISH</option>
                    </select>
                </div>
                 -->
                </div> 
                <div id="chekckLogin" style="padding-bottom: 8px;" class="tpm-login">
                </div>
                <div style="padding-bottom: 8px;padding-top: 10px;position:relative;"> 
                    <input id="btnLogin" type="submit" value="Login" style="height:21px;"  class="easyui-button"  name="btnLogin" />
                    <div class="logLoading " style=""><label style="float:right;color:#fff;margin-right:-8%">Processing....</label></div>
				</div>
					<!-- Changes -->
					<div>
					<!-- label id="btnForgetPwd"><b><u><font color="blue">Forgot Password</font></u></b></label-->
					<input id="btnForgetPwd" type="button" value="Forgot Password" style="height:21px;"  class="easyui-button"  name="btnForgetPwd" />
					
					
					</div>
                <div style="clear: both;"></div>
                <div style="padding-bottom: 8px;padding-right:10px;margin-top: 20px;"> 
                </div>
                <div style="padding-bottom: 8px;padding-right:10px;margin-top: 20px;"> 
 
                </div>
            </div>	
			</td>
		</tr>
	</table>
</div>
	<div id="clint_logo" style="display: none;">
		<span id="aka_logo" style="padding-left:0px;">
		<label  class="lblLog">Licensed To</label><img alt="ITC" src="images/logo/clientlogo.png" height="52px" style="scroll 0 0"></span>
	</div>
		
	<div id="akra_logo" style="">
		<span id="aka_logo" style="padding-left:0px;">
		<label  class="lblakaLog">Developed By</label><img alt="AKRANTA" src="images/home-page/cmpLogo.png"/></span>
	</div>

<div id="contactInfo" style="padding-left: 120px;">
<!--		<div id="" style="padding-left:0px;">-->
<!--			<span>-->
<!--			<label  class="lblSupport"> BCM  -->
<!--			<span style="padding-left:10px;">:</span>-->
<!--			</label>-->
<!--			</span>-->
<!--			<span id="" style="padding-left:0px;">-->
<!--			   <label  class="lblSupport"> Support Mail : </label> -->
<!--			</span>-->
<!--			<span>-->
<!--			   <label  class="lblSupport"> BCM TPM Software Support ,</label>-->
<!--		    </span>-->
<!--		    <span>-->
<!--		        <label  class="lblSupport"> Contact No.  </label>-->
<!--		    </span>-->
<!--		    <span> -->
<!--			    <label  class="lblSupport">   4119,  5081 </label>-->
<!--			</span>-->
<!--		</div>-->
<!--	-->
<!--				-->
<!--		<div id="" style="padding-left:0px;">-->
<!--			<label  class="lblSupport"> KOVAI :</label> -->
<!--			-->
<!--			<span id="" style="padding-left:0px;">-->
<!--				<label  class="lblSupport"> Support Mail : </label> -->
<!--			</span>-->
<!--			<span>-->
<!--				<label  class="lblSupport"> KOV TPM Software Support ,</label>-->
<!--		   </span>-->
<!--		   <span>-->
<!--		   <label  class="lblSupport"> Contact No.  </label> -->
<!--		   </span>-->
<!--		   <span>-->
<!--			<label  class="lblSupport">   1601,  1610 </label>-->
<!--		   </span>-->
<!--		</div>-->

<table>
  <tr style="border: 1px solid black;">
    
    <th align="left" style="border-top: 1px solid black;border-bottom: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;">Location</th>
    <td align="center" style="border-top: 1px solid black;border-bottom: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;">Bhadrachalam</td>
    <td align="center" style="border-top: 1px solid black;border-bottom: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;">Tribeni</td>
    <td align="center" style="border-top: 1px solid black;border-bottom: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;">Kovai</td>
    <td align="center" style="border-top: 1px solid black;border-bottom: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;">Bollaram</td>
    <td align="center" style="border-top: 1px solid black;border-bottom: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;width:130px;border-right: 1px solid black;">DHQ</td>
<!--    <td align="center" style="border-top: 1px solid black;border-bottom: 1px solid black;border-right: 1px solid black;border-left: 1px solid black;background-color: #C6D2F5;width:130px;">Tribeni</td>-->
  </tr>
  <tr>
    <th style="border-left: 1px solid black;border-bottom: 1px solid black;padding-right:10px;background-color: #C6D2F5;">Support Mail</th>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-right:10px;padding-left:4px;font-size: 10px;"> BCMTPM.SoftwareSupport@itc.in </td>  
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-right:10px;padding-left:4px;font-size: 10px;"> TBN Perfex Support </td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-right:10px;padding-left:4px;font-size: 10px;"> KOV TPM Software Support </td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-right:10px;padding-left:4px;font-size: 10px;"> PSPD BOL Perfex Support </td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;border-right: 1px solid black;padding-right:10px;padding-left:4px;font-size: 10px;"> PSPD DHQ Perfex Support </td>
<!--    <td style="border-left: 1px solid black;border-bottom: 1px solid black;border-right: 1px solid black;padding-right:10px;padding-left:4px;"> - </td>-->
  </tr>
  <tr>  
    <th align="left" style="border-left: 1px solid black;border-bottom: 1px solid black;background-color: #C6D2F5;">Contact No</th>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-left:4px;font-size: 10px;">Perfex Clarification-2117,<br>Unlocking-4114,2114</td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-left:4px;font-size: 10px;">7200,9903540684</td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-left:4px;font-size: 10px;">1601,1610</td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;padding-left:4px;font-size: 10px;">201,235</td>
    <td style="border-left: 1px solid black;border-bottom: 1px solid black;border-right: 1px solid black;padding-right:10px;padding-left:4px;font-size: 10px;">519, 398</td>
<!--    <td style="border-left: 1px solid black;border-bottom: 1px solid black;border-right: 1px solid black;padding-left:4px;"> - </td>-->
  </tr>
</table>


			
	</div>
		<!-- perfex -->
	</form>
<form id="frmGetLogin" method="POST"  action="perfex">
<input type="hidden" id="tpmuser" name="tpmuser"/>
<input type="hidden" id="tpmuser" name="tpmuser"/>
<input type="hidden" id="tpmuser" name="tpmuser"/>
<input type="hidden" id="tempUserId"  name="tempUserId" value="${requestScope.userId}"/>
<input type="hidden" id="tempPassword" name="tempPassword"  value="${requestScope.password}"/>
<input type="hidden" id="tempUrl"  name="tempUrl" value="${requestScope.url}"/>

		

<input type="hidden" id="hdnattemnt" name="hdnattemnt" value="" /> 
</form>
</body>
</html>