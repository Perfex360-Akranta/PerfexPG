/* Author: 
	Antoine Wette
	01.05.2011
	www.aw-digital.com
	info@aw-digital.com
	PLEASE DONT USE THIS SCRIPT OR PARTS OF THIS SCRIPT WITHOUT MY CONSENT
*/
	var workCounter = 0;
	var blogCounter = 0;
	var size_work = 0;
	var size_blog = 0;
	var	detailsCounter = 0;		
	var	detailsAngle = 0;		
	var	size_details = 0;
	var startAngle = 70;
	var menuTop = 0;
	var work_titles, work_dates, work_folders, work_backgrounds, work_screen;	
	var blog_titles, blog_dates, blog_folders, blog_backgrounds;	
	var tempType;
	// 0 = WORK, 1 = WORK DETAILS, 2 = ABOUT ME, 3 = LAB, 4 = LAB DETAILS, 5 = AWARDS
	var currentView = 0;
	var article = '';
	
	jQuery(document).ready(function() {
		
		//loadWorkXML();
		loadSite();
		jQuery('.container').css('ms-transition-duration','1s');

		//alert(window.location);

			
		jQuery('#bg').css('opacity',0);
		jQuery('.view').css('top',-20);
		
	
		// OPEN WORK
		jQuery(".item1").click(function(element) {
			if(currentView != 0){				
				hide_last(currentView, 0);
				loadWorkXML();				
				menuTop = 0;
				jQuery('.menu_but').css('background-position','0 ' +menuTop +'px');
			}
		});
		
		// OPEN ABOUT ME
		jQuery(".item2").click(function(element) {	
			if(currentView != 2){				
				loadAbout();
			}			
		});
		
		// OPEN LAB 
		jQuery(".item3").click(function(element) {	
			if(currentView != 3){
				loadLab();
			}
		});
		
		// OPEN AWARDS 
		jQuery(".item4").click(function(element) {	
			if(currentView != 5){
				loadAwards();
			}
		});
		
		jQuery("#details .previous, #details .next, #details .show_info").mouseenter(function(element) {
			jQuery(this).stop().animate({opacity:0.9},300);
			jQuery("#details .gallery").stop().animate(
				{opacity:0.2},{duration:400}	
			);
		});
		jQuery("#details .previous, #details .next, #details .show_info").mouseleave(function(element) {
			jQuery(this).stop().animate({opacity:0},150);
			jQuery("#details .gallery").stop().animate(
				{opacity:1},{duration:150}	
			);
		});
		
		if((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i)) || (navigator.userAgent.match(/iPad/i))) {
			jQuery(".screen").click(function(){
				jQuery("#details .gallery").stop().delay(700).animate(
				{opacity:1},{duration:400}	
				);	
				jQuery("#details .infobox").stop().animate(
					{opacity:0,height:180},{duration:100, complete: function(){jQuery("#details .infobox").css('display','none');}}	
				);
				jQuery(".screenshots, .screen").css('width',700);
				jQuery(".screenshots").css('margin-left',-350);
				jQuery(".screenshots, .screen").css('border-radius',0);
				jQuery("#details .previous, #details .next, #details .show_info").stop().animate(
					{opacity:0},{duration:150}	
				);
			});
		}
		
		jQuery("#details .show_info").click(function(element) {
			jQuery("#details .infobox").css('display','block');
			jQuery("#details .infobox").stop().animate(
				{opacity:1,height:150},{duration:500}	
			);
		});
		
		jQuery("#details .infobox").mouseleave(function(element) {			
			jQuery("#details .infobox").stop().animate(
				{opacity:0,height:180},{duration:100, complete: function(){jQuery("#details .infobox").css('display','none');}}	
			);
		});
		
		jQuery(".screenshots .previous").click(function(element) {	
			setTimeout("previousScreen();",400);
			jQuery(".screenshots, .screen").css('width',400);
			jQuery(".screenshots, .screen").css('border-radius',200);
			jQuery(".screenshots").css('margin-left',-200);
			jQuery("#details .infobox").stop().animate(
					{opacity:0,height:180},{duration:100, complete: function(){jQuery("#details .infobox").css('display','none');}}	
			);
		});
		jQuery(".screenshots .next").click(function(element) {	
			setTimeout("nextScreen();",400);
			jQuery(".screenshots, .screen").css('width',400);
			jQuery(".screenshots, .screen").css('border-radius',200);
			jQuery(".screenshots").css('margin-left',-200);
			jQuery("#details .infobox").stop().animate(
				{opacity:0,height:180},{duration:100, complete: function(){jQuery("#details .infobox").css('display','none');}}	
			);
		});
		
		
		jQuery('.screenshots').mouseenter(function(element) {		
			jQuery("#details .gallery").stop().delay(700).animate(
				{opacity:1},{duration:400}	
			);	
			jQuery(".screenshots, .screen").css('width',700);
			jQuery(".screenshots").css('margin-left',-350);
			jQuery(".screenshots, .screen").css('border-radius',0);
		});
		jQuery('.screenshots').mouseleave(function(element) {					
			jQuery("#details .gallery").stop().animate(
				{opacity:0.3},{duration:200}	
			);
			jQuery(".screenshots, .screen").css('width',400);
			jQuery(".screenshots, .screen").css('border-radius',200);
			jQuery(".screenshots").css('margin-left',-200);
		});
		
		jQuery('.menu_but').click(function(element) {				
			jQuery('#menu ul, .lava_slider').css('display','block');			
			jQuery('#menu ul').stop().animate(
				{opacity: 1, left:0},200	
			);	
			jQuery('.lava_slider').stop().animate(
				{opacity: 0.6, marginLeft:0},200	
			);
			jQuery(this).css('background-position','-35px '+menuTop+'px');
		});
		jQuery('#menu').mouseleave(function(element) {			
			jQuery('#menu ul').stop().animate(
				{opacity: 0, left:-10},{duration:200,complete:function(){jQuery('#menu ul, .lava_slider').css('display','block');}}
			);
			jQuery('.lava_slider').stop().animate(
				{opacity: 0, marginLeft:0},200	
			);
			jQuery('.menu_but').css('background-position','0 ' +menuTop +'px');
		});	
		
		jQuery('#menu li').mouseenter(function(element) {	
			position = jQuery(this).position();
			jQuery('.lava_slider').stop().animate(
				{left: position.left, width:jQuery(this).css('width')},{duration:300,easing:'easeOutBack'}	
			);				
		});		
		
	});
	
	
	function loadSite(){		
		var section = '' + window.location;		
		section = section.split('#');
		article = '';
		if(section.length>1){
			var spliResult = section[1].split('=');	
			section = spliResult[0];			
			if(section.length>2){article = spliResult[1];}
			if(section == 'about'){
				setTimeout("loadAbout();",200);
			}
			if(section == 'lab'){				
				setTimeout("loadLab();",100);
				if(article != undefined){					
					setTimeout("blogCounter = article*1-1;jQuery('body').stop().animate({backgroundColor:'#'+blog_backgrounds[blogCounter]},{duration:200});"+
					"jQuery('#bg').stop().animate({opacity:0},{duration:50,complete:function(){jQuery('#bg').css('background-image','url(images/menu_action/'+blog_folders[blogCounter]+'/bg.jpg)');jQuery('#bg').delay(150).animate({opacity:0.8},{duration:1000});}}	);",1400);					
					setTimeout("open_blog();",2000);
				}					
			}
			if(section == 'awards'){
				setTimeout("loadAwards();",200);
			}
			
			if(section == 'work'){
				loadWorkXML();				
				if(article != undefined){												
					setTimeout("workCounter = article*1-1;jQuery('body').stop().animate({backgroundColor:'#'+work_backgrounds[workCounter]},{duration:200});"+
					"jQuery('#bg').stop().animate({opacity:0},{duration:50,complete:function(){jQuery('#bg').css('background-image','url(images/menu_action/'+work_folders[workCounter]+'/bg.jpg)');jQuery('#bg').delay(150).animate({opacity:0.8},{duration:1000});}}	);",700);					
					setTimeout("open_work();",1200);					
				}
			}else{
				loadWorkXML();
			}
		}
		else{
			loadWorkXML();
		}		
		
	}
	
	function changeURL(section){
		var url = '' + window.location;		
		url = url.split('#');
		url = url[0]+'#' +section;		
		window.location = url;		
	}
	
	function loadAbout(){
		hide_last(currentView, 2);				
		open_about();
		menuTop = -35;
		jQuery('.menu_but').css('background-position','0 ' +menuTop +'px');
		changeURL('about');
	}
	
	function loadLab(){
		hide_last(currentView, 3);				
		loadBlogXML();
		menuTop = -70;
		jQuery('.menu_but').css('background-position','0 ' +menuTop +'px');
		
	}
	
	function loadAwards(){
		hide_last(currentView, 5);				
		menuTop = -105;
		open_awards();
		jQuery('.menu_but').css('background-position','0 ' +menuTop +'px');
		changeURL('awards');
	}
	
	function nextScreen(){
		detailsCounter++;
		jQuery('#details .previous').css('display','block');
		if(detailsCounter==size_details-1){jQuery('#details .next').css('display','none');}
		jQuery('#details .container').css('-moz-transform','rotate('+(360*detailsCounter)+'deg)');
		jQuery('#details .container').css('-webkit-transform','rotate('+(360*detailsCounter)+'deg)');
		jQuery('#details .container').css('-o-transform','rotate('+(360*detailsCounter)+'deg)');
		jQuery('#details .container').css('ms-transform','rotate('+(360*detailsCounter)+'deg)');
		setTimeout("jQuery('.screen').css('background-image','url(images/menu_action/'+work_folders[workCounter]+'/'+ detailsCounter +'.jpg)')",300);
	}
	
	function previousScreen(){
		detailsCounter--;		
		jQuery('#details .next').css('display','block');
		if(detailsCounter==0){jQuery('#details .previous').css('display','none');}
		jQuery('#details .container').css('-moz-transform','rotate('+(360*detailsCounter)+'deg)');
		jQuery('#details .container').css('-webkit-transform','rotate('+(360*detailsCounter)+'deg)');
		jQuery('#details .container').css('-o-transform','rotate('+(360*detailsCounter)+'deg)');
		jQuery('#details .container').css('ms-transform','rotate('+(360*detailsCounter)+'deg)');
		setTimeout("jQuery('.screen').css('background-image','url(images/menu_action/'+work_folders[workCounter]+'/'+ detailsCounter +'.jpg)')",300);
	}
	
	
	function loadWorkXML(){
		changeURL('');
		jQuery.get("work.xml",function(xml){	
			
			xml = parseXml(xml);			
			size_work = jQuery('work',xml).size();		
			
			work_titles = new Array(size_work);
			work_dates = new Array(size_work);
			work_folders = new Array(size_work);		
			work_backgrounds = new Array(size_work);				
			work_descriptions = new Array(size_work);				
			work_urls = new Array(size_work);				
			work_tags = new Array(size_work);				
			work_screen = new Array(size_work);		
			work_awards = new Array(size_work);		
			jQuery('work',xml).each(function(i) {				
				work_titles[i] = jQuery(this).find("title").text();
				work_dates[i] = jQuery(this).find("date").text();
				work_folders[i] = jQuery(this).find("folder").text();				
				work_backgrounds[i] = jQuery(this).find("background").text();				
				work_descriptions[i] = jQuery(this).find("description").text();				
				work_urls[i] = jQuery(this).find("url").text();				
				work_tags[i] = jQuery(this).find("tags").text();				
				work_screen[i] = jQuery(this).find("screen").text();				
				work_awards[i] = jQuery(this).find("award").text();				
			});				
			
			workCounter = 0;			
			loadWork();	
			jQuery('body').stop().animate(
				{backgroundColor:"#"+work_backgrounds[workCounter]},{duration:200}	
			);	
			
			jQuery('#bg').stop().animate(
				{opacity:0},{duration:50,complete:function(){
					jQuery('#bg').css("background-image",'url(images/menu_action/'+work_folders[workCounter]+'/bg.jpg)');
					jQuery('#bg').delay(150).animate(
						{opacity:0.8},{duration:1000}
					);
				}}	
			);		
		
			
			
		},'xml');	
		
	}
	
	function loadBlogXML(){
		changeURL('lab');
		jQuery.get("lab.xml",function(xml){	
			
			xml = parseXml(xml);			
			size_blog = jQuery('blog',xml).size();		
			
			blog_titles = new Array(size_work);
			blog_dates = new Array(size_work);
			blog_folders = new Array(size_work);		
			blog_backgrounds = new Array(size_work);				
			blog_descriptions = new Array(size_work);				
			blog_demos = new Array(size_work);				
			blog_downloads = new Array(size_work);				
				
			jQuery('blog',xml).each(function(i) {				
				blog_titles[i] = jQuery(this).find("title").text();
				blog_dates[i] = jQuery(this).find("date").text();
				blog_folders[i] = jQuery(this).find("folder").text();				
				blog_backgrounds[i] = jQuery(this).find("background").text();								
				blog_descriptions[i] = jQuery(this).find("description").text();								
				blog_demos[i] = jQuery(this).find("demo").text();								
				blog_downloads[i] = jQuery(this).find("download").text();											
			});				
			
			blogCounter = 0;			
			loadBlog();	
			jQuery('body').stop().animate(
				{backgroundColor:"#"+blog_backgrounds[blogCounter]},{duration:200}	
			);	
			
			jQuery('#bg').stop().animate(
				{opacity:0},{duration:50,complete:function(){
					jQuery('#bg').css("background-image",'url(images/menu_action/'+blog_folders[blogCounter]+'/bg.jpg)');
					jQuery('#bg').delay(150).animate(
						{opacity:0.8},{duration:1000}
					);
				}}	
			);				
			
		},'xml');	
		
	}
	
	function loadBlog(){	
		
		blogCounter = 0;		
		jQuery('#blog').css('display','block');		
		setTimeout("jQuery('#blog .container').css('-moz-transform','rotate(0deg)');jQuery('#blog .container').css('-webkit-transform','rotate(0deg)');"
		+"jQuery('#blog .container').css('-o-transform','rotate(0deg)');jQuery('#blog .container').css('ms-transform','rotate(0deg)');",400);
		jQuery('#blog .container').html('');		
		startAngle=270;
		for(var i=0;i<size_blog;i++){
			jQuery('#blog .container').append('<div class="blog blog'+i+'"><div class="color"></div></div>');
			jQuery('.blog'+i).append('<div class="view"></div>');
			jQuery('.blog'+i).append('<div class="info"><div class="title">'+generateTitleSpan(blog_titles[i],blog_backgrounds[i]) +'</div><div class="counter"><span style="top:9px;position:relative">'+(i+1)+'/'+size_blog+'</span></div></div>');
			jQuery('.blog'+i).find('.color').css('background-image','url(images/menu_action/'+blog_folders[i]+"/color.jpg)");
			jQuery('.blog'+i).css('left',550+cosDEG(startAngle)*445);
			jQuery('.blog'+i).css('top',550-sinDEG(startAngle)*445);
			
			jQuery('.blog'+i).css('-moz-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.blog'+i).css('-webkit-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.blog'+i).css('-o-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.blog'+i).css('ms-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			if(i>1){
				jQuery('.blog'+i).css('-moz-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.blog'+i).css('-webkit-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.blog'+i).css('-o-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.blog'+i).css('ms-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.blog'+i).css('opacity',0);
			}
			
			startAngle+=45;
		}
		jQuery('.blog0').attr('class','blog blog0 active');
		jQuery('.blog1').attr('class','blog blog1 next');
		jQuery('.blog0').css('-moz-transform','scale(1,1) rotate(0deg)');	
		jQuery('.blog0').css('-webkit-transform','scale(1,1) rotate(0deg)');	
		jQuery('.blog0').css('-o-transform','scale(1,1) rotate(0deg)');	
		jQuery('.blog0').css('ms-transform','scale(1,1) rotate(0deg)');	
		bind_work('blog');
		
		Cufon.replace('.csstransitions .info .title');
	}
		
	function sinDEG(Nummer)
	{
	Nummer = Math.sin(Nummer/180*Math.PI);
	if((Nummer+"").indexOf("e")>-1)
	Nummer = 0;
	return Nummer;
	}
	
	function cosDEG(Nummer)
	{
	Nummer = Math.cos(Nummer/180*Math.PI);
	if((Nummer+"").indexOf("e")>-1)
	Nummer = 0;
	return Nummer;
	}
	
	function generateTitleSpan(title,color){		
		var mySplitResult = title.split(' ');		
		var resultString = '<span style="color:#'+color+'">';
		var len = mySplitResult.length;		
		for(var i=0; i < len; i++){				
			if(i < len-1){
				resultString+= mySplitResult[i] +' ';	
			}
			else{ 
				resultString+='</span>';
				resultString+= mySplitResult[i];				
			}	
		}		
		
		return resultString;
	}
	
	function generateTags(tags,color){		
		var mySplitResult = tags.split(' ');		
		var resultString = '';
		var len = mySplitResult.length;		
		for(var i=0; i < len; i++){				
			resultString += '<span class="tag" style="background:#'+color+'">'+mySplitResult[i]+'</span>';	
		}		
		
		return resultString;
	}
	
	function loadWork(){	
		workCounter = 0;
		detailsCounter = 0;
		size_details = work_screen[workCounter]*1;
		jQuery('#work').css('display','block');
		
		setTimeout("jQuery('#work .container').css('-moz-transform','rotate(0deg)');jQuery('#work .container').css('-webkit-transform','rotate(0deg)');"
		+"jQuery('#work .container').css('-o-transform','rotate(0deg)');jQuery('#work .container').css('ms-transform','rotate(0deg)');",400);
		jQuery('#work .container').html('');
		
		startAngle=270;
		for(var i=0;i<size_work;i++){
			jQuery('#work .container').append('<div class="work work'+i+'"><div class="color"></div></div>');
			jQuery('.work'+i).append('<div class="view"></div>');
			jQuery('.work'+i).append('<div class="info"><div class="title">'+generateTitleSpan(work_titles[i],work_backgrounds[i]) +'</div><div class="counter"><span style="top:9px;position:relative">'+(i+1)+'/'+size_work+'</span></div></div>');
			jQuery('.work'+i).find('.color').css('background-image','url(images/menu_action/'+work_folders[i]+"/color.jpg)");
			jQuery('.work'+i).css('left',550+cosDEG(startAngle)*445);
			jQuery('.work'+i).css('top',550-sinDEG(startAngle)*445);
			//jQuery('.work'+i).find('.color').css('-moz-transform','rotate('+((-i*45)%360)+'deg)');
			jQuery('.work'+i).css('-moz-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.work'+i).css('-o-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.work'+i).css('transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.work'+i).css('ms-transform','scale(.7,.7) rotate('+((-i*45)%360)+'deg)');			
			jQuery('.work'+i).css('-webkit-transform','scale(0.7,0.7) rotate('+((-i*45)%360)+'deg)');			
			if(i>1){
				jQuery('.work'+i).css('-moz-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.work'+i).css('-webkit-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.work'+i).css('transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.work'+i).css('-o-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.work'+i).css('ms-transform','scale(0,0) rotate('+((-i*45)%360)+'deg)');
				jQuery('.work'+i).css('opacity',0);
			}
			
			startAngle+=45;
		}
		jQuery('.work0').attr('class','work work0 active');
		jQuery('.work1').attr('class','work work1 next');
		jQuery('.work0').css('-moz-transform','scale(1,1) rotate(0deg)');	
		jQuery('.work0').css('-webkit-transform','scale(1,1) rotate(0deg)');	
		jQuery('.work0').css('-o-transform','scale(1,1) rotate(0deg)');	
		jQuery('.work0').css('ms-transform','scale(1,1) rotate(0deg)');	
		bind_work('work');
		
		Cufon.replace('.csstransitions .info .title');
	}
	
	
		
	function parseXml(xml)
	{		
		return xml;
	}
	
	function hide_last(lastSection, newSection){
		// 0 = WORK, 1 = WORK DETAILS, 2 = ABOUT ME, 3 = LAB, 4 = LAB DETAILS, 5 = AWARDS
		if(lastSection == 0){hide_thumbs('work');}
		if(lastSection == 1){hide_details();}
		if(lastSection == 2){hide_about();}
		if(lastSection == 3){hide_thumbs('blog');}	
		if(lastSection == 4){hide_article();}	
		if(lastSection == 5){hide_awards();}	
		currentView = newSection;		
	}
	
	function hide_thumbs(type){		
		tempType = type;
		//jQuery('#work .container').css('-moz-transform','rotate('+((workCounter+3)*45)%360+'deg)');
		//jQuery('#work .container').css('-webkit-transform','rotate('+((workCounter+3)*45)%360+'deg)');
		
		jQuery('#'+type+' .'+type).css('-moz-transform','scale(0.1,0.1) rotate(180deg)');		
		jQuery('#'+type+' .'+type).css('-webkit-transform','scale(0.1,0.1) rotate(180deg)');	
		jQuery('#'+type+' .'+type).css('opacity',0);				
	
		setTimeout("jQuery('#'+tempType+' .'+tempType).remove();"	
		+"jQuery('#'+tempType).css('display','none');"
		+"jQuery('#'+tempType+' .container').css('-moz-transform','rotate(-180deg)');"
		+"jQuery('#'+tempType+' .container').css('-o-transform','rotate(-180deg)');"
		+"jQuery('#'+tempType+' .container').css('ms-transform','rotate(-180deg)');"
		+"jQuery('#'+tempType+' .container').css('-webkit-transform','rotate(-180deg)');",700);		
	}
	
	
	function hide_details(){		
		jQuery('#details .container').css('-moz-transform','rotate('+(detailsCounter*360+180)+'deg)');
		jQuery('#details .container').css('-webkit-transform','rotate('+(detailsCounter*360+180)+'deg)');
		jQuery('#details .container').css('-o-transform','rotate('+(detailsCounter*360+180)+'deg)');
		jQuery('#details .container').css('ms-transform','rotate('+(detailsCounter*360+180)+'deg)');
		setTimeout("jQuery('#details').css('display','none');jQuery('#details .container').css('-webkit-transform','rotate(-180deg)');jQuery('#details .container').css('-moz-transform','rotate(-180deg)');"
		+"jQuery('#details .container').css('-o-transform','rotate(-180deg)');jQuery('#details .container').css('ms-transform','rotate(-180deg)');",700);
	}
	
	function hide_about(){		
		jQuery('#about .container').css('-moz-transform','rotate(180deg)');
		jQuery('#about .container').css('-webkit-transform','rotate(180deg)');
		jQuery('#about .container').css('-o-transform','rotate(180deg)');
		jQuery('#about .container').css('ms-transform','rotate(180deg)');
		setTimeout("jQuery('#about').css('display','none');jQuery('#about .container').css('-webkit-transform','rotate(-180deg)');jQuery('#about .container').css('-moz-transform','rotate(-180deg)');"
		+"jQuery('#about .container').css('-o-transform','rotate(-180deg)');jQuery('#about .container').css('ms-transform','rotate(-180deg)');",700);
	}
	
	function hide_awards(){		
		jQuery('#awards .container').css('-moz-transform','rotate(180deg)');
		jQuery('#awards .container').css('-webkit-transform','rotate(180deg)');
		jQuery('#awards .container').css('-o-transform','rotate(180deg)');
		jQuery('#awards .container').css('ms-transform','rotate(180deg)');
		setTimeout("jQuery('#awards').css('display','none');jQuery('#awards .container').css('-webkit-transform','rotate(-180deg)');jQuery('#awards .container').css('-moz-transform','rotate(-180deg)');"
		+"jQuery('#awards .container').css('-o-transform','rotate(-180deg)');jQuery('#awards .container').css('ms-transform','rotate(-180deg)');",700);
	}
	
	function hide_article(){
		jQuery('#article .container').css('-moz-transform','rotate(180deg)');
		jQuery('#article .container').css('-webkit-transform','rotate(180deg)');
		jQuery('#article .container').css('-o-transform','rotate(180deg)');
		jQuery('#article .container').css('-ms-transform','rotate(180deg)');
		setTimeout("jQuery('#article').css('display','none');jQuery('#article .container').css('-webkit-transform','rotate(-180deg)');jQuery('#article .container').css('-moz-transform','rotate(-180deg)');"
		+"jQuery('#article .container').css('-o-transform','rotate(-180deg)');jQuery('#article .container').css('ms-transform','rotate(-180deg)');",700);
	}

	
	function open_awards(){		
		jQuery('#awards').css('display','block');	
		jQuery('body').stop().animate(
			{backgroundColor:"#eac787"},{duration:500}	
		);
		jQuery('#bg').stop().animate(
			{opacity:0},{duration:50,complete:function(){
				jQuery('#bg').css("background-image",'url(images/menu_action/awards.jpg)');
				jQuery('#bg').delay(150).animate(
					{opacity:0.8},{duration:1000}
				);
			}}	
		);
		setTimeout("jQuery('#awards .container').css('-moz-transform','rotate(0deg)');jQuery('#awards .container').css('-webkit-transform','rotate(0deg)');"
		+"jQuery('#awards .container').css('-o-transform','rotate(0deg)');jQuery('#awards .container').css('ms-transform','rotate(0deg)');",700);	
	}
	
	
	
	function open_about(){				
		jQuery('#about').css('display','block');	
		jQuery('body').stop().animate(
			{backgroundColor:"#bfd0e4"},{duration:500}	
		);	
			
		jQuery('#bg').stop().animate(
			{opacity:0},{duration:50,complete:function(){
				jQuery('#bg').css("background-image",'url(images/menu_action/about_bg.jpg)');
				jQuery('#bg').delay(350).animate(
					{opacity:0.8},{duration:1000}
				);
			}}	
		);
		jQuery('#about .container').animate(
			{'rotate':'0deg'},{duration:600}
		);
		setTimeout("jQuery('#about .container').css('-moz-transform','rotate(0deg)');jQuery('#about .container').css('-webkit-transform','rotate(0deg)');"
		+"jQuery('#about .container').css('ms-transform','rotate(0deg)');jQuery('#about .container').css('-o-transform','rotate(0deg)');",700);		
	}
	
	function open_blog(){
		changeURL('lab='+(blogCounter+1));
		hide_last(3,4);
		jQuery('#article').css('display','block');	
		jQuery('#article .article').html('<div class="title">'+generateTitleSpan(blog_titles[blogCounter],blog_backgrounds[blogCounter])+'</div>');
		jQuery('#article .article').append('<div class="large" style="background-image:url(images/menu_action/'+blog_folders[blogCounter]+'/large.jpg)"></div>');
		jQuery('#article .article').append('<div class="description">'+blog_descriptions[blogCounter]+'</div>');
		var linkHTML = '<div class="links">';		
		if(blog_demos[blogCounter] != ''){
			linkHTML += '<a target="blank" href="'+blog_demos[blogCounter]+'" class="demo">DEMO</a>';
		}if(blog_downloads[blogCounter] != ''){
			linkHTML += '<a target="blank" href="'+blog_downloads[blogCounter]+'" class="download">DOWNLOAD</a>';
		}
		linkHTML +='</div>';
		jQuery('#article .article').append(linkHTML);
		Cufon.replace('.csstransitions .article .title');
		setTimeout("jQuery('#article .container').css('-moz-transform','rotate(0deg)');jQuery('#article .container').css('-webkit-transform','rotate(0deg)');"
		+"jQuery('#article .container').css('-o-transform','rotate(0deg)');jQuery('#article .container').css('ms-transform','rotate(0deg)');",700);	
	}
	
	
	function open_work(){
		changeURL('work='+(workCounter+1));
		size_details = work_screen[workCounter]*1;
		hide_last(0,1);
		var awardHTML = '';
 		jQuery('#details').css('display','block');
		jQuery('#details .infobox').html('<div class="title">'+generateTitleSpan(work_titles[workCounter],work_backgrounds[workCounter])+'</div>');
		jQuery('#details .infobox').append('<div class="description">'+work_descriptions[workCounter]+'</div>');			
		if(work_awards[workCounter] != ''){
			awardHTML += '<div class="award"><a target="blank" href="http://'+work_awards[workCounter]+'" class="siteoftheday">SITE OF THE DAY</a></div>';
		}	
		jQuery('#details .infobox').append(awardHTML);
		jQuery('#details .infobox').append('<div class="url"><a style="color:#'+work_backgrounds[workCounter]+'" href="http://'+work_urls[workCounter]+'" target="blank">'+work_urls[workCounter]+'</a></div>');
		jQuery('#details .infobox').append('<div class="tags">'+generateTags(work_tags[workCounter],work_backgrounds[workCounter])+'</div>');
		Cufon.replace('.csstransitions .infobox .title');
		jQuery('.screen').css('background-image','url(images/menu_action/'+work_folders[workCounter]+'/'+ detailsCounter%size_details +'.jpg)');
		setTimeout("jQuery('#details .previous').css('display','none');jQuery('#details .next').css('display','block');jQuery('#details .next, #details .show_info').stop().animate({opacity:1},300).delay(400).animate({opacity:0},300);"
		+"jQuery('#details .container').css('-moz-transform','rotate(0deg)');jQuery('#details .container').css('-webkit-transform','rotate(0deg)');"
		+"jQuery('#details .container').css('-o-transform','rotate(0deg)');jQuery('#details .container').css('ms-transform','rotate(0deg)');",700);
		
	}	
	
	function change(direction,type){
		if(type == 'work' ){
			if(direction == 'next'){workCounter++;}
			if(direction == 'previous'){workCounter--;}
			var tempCounter = workCounter; 
			var temp_folders = work_folders;
			var temp_backgrounds = work_backgrounds;
		}		
		if(type == 'blog' ){
			if(direction == 'next'){blogCounter++;}
			if(direction == 'previous'){blogCounter--;}
			var tempCounter = blogCounter; 
			var temp_folders = blog_folders;
			var temp_backgrounds = blog_backgrounds;
		}		
		jQuery('#'+type+' .container').css('-moz-transform','rotate('+(tempCounter*45)%360+'deg)');
		jQuery('#'+type+' .container').css('-webkit-transform','rotate('+(tempCounter*45)%360+'deg)');		
		jQuery('#'+type+' .container').css('-o-transform','rotate('+(tempCounter*45)%360+'deg)');		
		jQuery('#'+type+' .container').css('ms-transform','rotate('+(tempCounter*45)%360+'deg)');		
					
		
		
		
		jQuery('#'+type+' .'+type+(tempCounter)).css('-moz-transform','scale(1,1) rotate('+(-45*tempCounter)%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter)).css('-webkit-transform','scale(1,1) rotate('+(-45*tempCounter)%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter)).css('-o-transform','scale(1,1) rotate('+(-45*tempCounter)%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter)).css('ms-transform','scale(1,1) rotate('+(-45*tempCounter)%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter)).css('border-width',8);
		jQuery('#'+type+' .'+type+(tempCounter)).attr('class',type+' '+type+tempCounter +' active');
		jQuery('#'+type+' .'+type+(tempCounter-1)).attr('class',type+' '+type+(tempCounter-1) +' prev');
		jQuery('#'+type+' .'+type+(tempCounter-2)).attr('class',type+' '+type+(tempCounter-2));
		jQuery('#'+type+' .'+type+(tempCounter+2)).attr('class',type+' '+type+(tempCounter+2));
		jQuery('#'+type+' .'+type+(tempCounter+1)).attr('class',type+' '+type+(tempCounter+1) +' next');
		
		jQuery('#'+type+' .'+type+(tempCounter+2)).css('-moz-transform','scale(0,0) rotate('+(-45*(tempCounter+2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+2)).css('-webkit-transform','scale(0,0) rotate('+(-45*(tempCounter+2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+2)).css('-o-transform','scale(0,0) rotate('+(-45*(tempCounter+2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+2)).css('ms-transform','scale(0,0) rotate('+(-45*(tempCounter+2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+2)).css('opacity',0);
		jQuery('#'+type+' .'+type+(tempCounter-2)).css('-moz-transform','scale(0,0) rotate('+(-45*(tempCounter-2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-2)).css('-webkit-transform','scale(0,0) rotate('+(-45*(tempCounter-2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-2)).css('-o-transform','scale(0,0) rotate('+(-45*(tempCounter-2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-2)).css('ms-transform','scale(0,0) rotate('+(-45*(tempCounter-2))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-2)).css('opacity',0);
		jQuery('#'+type+' .'+type+(tempCounter-1)).css('-moz-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter-1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-1)).css('-webkit-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter-1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-1)).css('-o-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter-1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-1)).css('ms-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter-1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter-1)).css('border-width',0);
		jQuery('#'+type+' .'+type+(tempCounter+1)).css('border-width',0);
		jQuery('#'+type+' .'+type+(tempCounter+1)).css('-moz-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter+1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+1)).css('-webkit-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter+1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+1)).css('-o-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter+1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+1)).css('ms-transform','scale(0.7,0.7) rotate('+(-45*(tempCounter+1))%360+'deg)');
		jQuery('#'+type+' .'+type+(tempCounter+1)).css('opacity',1);
		jQuery('#'+type+' .'+type+(tempCounter-1)).css('opacity',1);
		
		jQuery('#bg').stop().animate(
			{opacity:0},{duration:50,complete:function(){
				jQuery('#bg').css("background-image",'url(images/menu_action/'+temp_folders[tempCounter]+'/bg.jpg)');
				jQuery('#bg').delay(500).animate(
					{opacity:0.8},{duration:600}
				);				
			}}	
		);			
		jQuery('body').stop().animate(
			{backgroundColor:"#"+temp_backgrounds[tempCounter]},{duration:500}	
		);	
	}
	
	
	
	function bind_work(type){
		jQuery('.'+type +',#'+type +' .next,#'+type +' .prev').unbind('click');
		jQuery('.'+type +', #'+type +' .active').unbind('mouseenter');
		jQuery('.'+type+'').unbind('mouseleave');
		
		jQuery('#'+type+' .active').click(function(element) {		
			if(type=='work'){
				open_work();
			}
			if(type=='blog'){
				open_blog();
			}
		});	
		
		jQuery('#'+type +' .next').click(function(element) {				
			change('next',type);
			jQuery(this).find(".view").stop().animate(
				{opacity:0,top:-20},{duration:0}	
			);
			bind_work(type);
			return false;
		});
		jQuery('#'+type +' .prev').click(function(element) {				
			change('previous',type);
			jQuery(this).find(".view").stop().animate(
				{opacity:0,top:-20},{duration:0}	
			);
			bind_work(type);
			return false;
		});		
		
		jQuery('.'+type+'').mouseenter(function(element) {		
			jQuery(this).find(".view").stop().animate(
				{opacity:1,top:0},{duration:300}	
			);						
		});
		jQuery('#'+type+' .active').mouseenter(function(element) {		
			jQuery(this).find(".info").stop().delay(100).animate(
				{height:120},{duration:200}	
			);
			jQuery(this).find(".info .counter").stop().delay(450).animate(
				{height:31},{duration:150}	
			);
			jQuery(this).find(".info .title").stop().delay(400).animate(
				{opacity:1},{duration:200}	
			);
		});		
		jQuery('.'+type+'').mouseleave(function(element) {		
			jQuery(this).find(".view").stop().animate(
				{opacity:0,top:-20},{duration:200}	
			);
			jQuery(this).find(".info").stop().animate(
				{height:0},{duration:200}	
			);
			jQuery(this).find(".info .title").stop().animate(
				{opacity:0},{duration:20}	
			);
			jQuery(this).find(".info .counter").stop().animate(
				{height:0},{duration:50}	
			);
		});
	}
	
	