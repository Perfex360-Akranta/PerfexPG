/*function DashBoardPillars_successCallback(result){
	var pillarDet;
	for( var i = 0; i< result.length ; i++){
		pillarDet = result[i];
		jQuery("#divDashBoardPillars").append('<a href="#" class="clickme" pillarCode="'+ pillarDet[1]  +'">'+ pillarDet[0] +' </a>');
	}	
}*/

(function($)
{
	var classModifier = "";
	var sliderCount = 0;
	var sliderWidth = "400px";
	
	var attachTo = "rightside";
	
	var totalPullOutHeight = 124;
	
	function CloseSliders (thisId) {
		// Reset previous sliders
		//return false;
		
		for (var i = 0; i < sliderCount; i++) {
			var sliderId = classModifier + "_" + i;
			var pulloutId = sliderId + "_pullout";
			
			// Only reset it if it is shown
			if ($("#" + sliderId).width() > 0) {
									if (sliderId == thisId) {
					// They have clicked on the open slider, so we'll just close it
					showSlider = false;
				}
				
				// Close the slider
				$("#" + sliderId).animate({
					width: "0px"
				}, 500);
				
				jQuery(".dashboard_content_div").slideUp("fast");
				
				// Reset the pullout
				if (attachTo == "leftside") {
					$("#" + pulloutId).animate({
						left: "0px"
					}, 1500);
				} else {
					$("#" + pulloutId).animate({
						right: "0px"
					}, 500);
				}
			}
		}
	}
	
	
	
	function ToggleSlider () {
//		alert('This functionality is not enabled');
//		return false;
		var rel = $(this).attr("rel");

		var thisId = classModifier + "_" + rel;
		var thisPulloutId = thisId + "_pullout";
		var showSlider = true;
		
		$("#" + thisId).height($('#tab_container').height()-30);
				
	  
		if ($("#" + thisId).width() > 0) {
			showSlider = false;
		}
		
		CloseSliders(thisId);
		
		if (showSlider) {
			/****Added BY manikandan for altering the z-index when multiple divs are shown*****/
			var dashbrdZind = jQuery(".dashboard_content_div").css("z-index");
			var dashbrdDivDisplay = jQuery('.dashboard_content_div').css('display');
			var alertDivDisplay = jQuery('.alert_content_div').css('display');
			var alertDivZindex = jQuery('.alert_content_div').css('z-index');
			var sliderZindex = jQuery("#" + thisId).css("z-index");
			//alert(jQuery(".dashboard_content_div").css("z-index"));
			if(alertDivDisplay == "block")
				jQuery("#" + thisId).css("z-index",alertDivZindex+2);
			if(dashbrdDivDisplay == "block")
				jQuery("#" + thisId).css("z-index",dashbrdZind+2);
			else{
				jQuery("#" + thisId).css("z-index",sliderZindex);
			}
			
			//sidecontent_0
			// Open this slider
			if(thisId != "sidecontent_0"){
				$("#" + thisId).animate({
					width: sliderWidth
				}, 500);
			}
		/*	// Move the pullout
			if (attachTo == "leftside") {
				$("#" + thisPulloutId).animate({
					left: sliderWidth
				}, 500);
			} else {
				$("#" + thisPulloutId).animate({
					right: sliderWidth
				}, 500);
			} */
		}
		return false;
	};

	$.fn.sidecontent = function (settings) {
		
//		processAjaxCalls("getPillars.dashboard","","DashBoardPillars_successCallback");
		
		var config = {
			classmodifier: "sidecontent",
			attachto: "rightside",
			width: "255px",
			opacity: "1",
			pulloutpadding: "0",
			textdirection: "vertical",
			text:"sidecontent",
			clickawayclose: false
		};
		
		if (settings) {
			
			$.extend(config, settings);
		}
		
		return this.each(function () {
		
			$This = $(this);
			
			// Hide the content to avoid flickering
			$This.css({ opacity: 1 });
			
			classModifier = config.classmodifier;
			sliderWidth = config.width;
			attachTo = config.attachto;
			
			var sliderId = classModifier + "_" + sliderCount;
			var sliderTitle = config.title;
			
			// Get the title for the pullout
			//alert($This.css("width"));
			var imgsrc = $This.attr("src");
			var imgstyle = $This.attr("style");
			
			// Start the totalPullOutHeight with the configured padding
			if (totalPullOutHeight == 0) {
				totalPullOutHeight += parseInt(config.pulloutpadding);
			}
/*
			if (config.textdirection == "vertical") {
				var newTitle = "";
				var character = "";
				for (var i = 0; i < sliderTitle.length; i++) {
					character = sliderTitle.charAt(i).toUpperCase();
					if (character == " ") {
						character = "&nbsp;";
					}
					newTitle = newTitle + "<span>" + character + "</span>";
				}
				sliderTitle = newTitle;
			}
			*/
			// Wrap the content in a slider and add a pullout		
			//alert(imgsrc);
			
			if( jQuery("#" + sliderId).length <= 0) {
				$This.wrap('<div class="' + classModifier + '" id="' + sliderId + '">').wrap('<div style="width: ' + sliderWidth + '"></div>');
				$("#" + sliderId).before('<img style="'+imgstyle+'" src="'+imgsrc+'" class="' + classModifier + 'pullout" id="' + sliderId + '_pullout" rel="' + sliderCount + '">');
			}
			if (config.textdirection == "vertical") {
				$("#" + sliderId + "_pullout span").css({
					display: "block",
					textAlign: "center"
				});
			}
			/** added by mani 30-march-2012**/
			$('.arrRight').click( function () {
				//alert('s');
				ToggleSlider ();
				 setTimeout(function() {jQuery('#sidecontent_1').css('border','1px #fff');},300);
			});
			/**end*/
			// Hide the slider
			$("#" + sliderId).css({
				position: "absolute",
				overflow: "hidden",
				top: "-3px",
				width: "0px",
				
				opacity: config.opacity
			});
			
			// For left-side attachment
			if (attachTo == "leftside") {
				$("#" + sliderId).css({
					left: "0px"
				});
			} else {
				$("#" + sliderId).css({
					right: "4px"
				});
			}
			/**sidecontent_1 click function*/
			// Set up the pullout
			$("#" + sliderId + "_pullout").css({
				
				//zIndex: "0",
				//position: "relative",
				
				cursor: "pointer",
				opacity: config.opacity
			});
			/*Jquery_update*/
			$(document).on('click', "#" + sliderId + "_pullout",ToggleSlider );
			/*$("#" + sliderId + "_pullout").live("click", ToggleSlider);*/
			
			var pulloutWidth = $("#" + sliderId + "_pullout").width();
			
			// For left-side attachment
			if (attachTo == "leftside") {
				$("#" + sliderId + "_pullout").css({
					left: "0px"
				//	width: pulloutWidth + "px"
				});
			} else {
				$("#" + sliderId + "_pullout").css({
					right: "0px"
					//width: pulloutWidth + "px"
				});
			}
			
			totalPullOutHeight += parseInt($("#" + sliderId + "_pullout").height());
			totalPullOutHeight += parseInt(config.pulloutpadding);
		
			var suggestedSliderHeight = totalPullOutHeight + 30;
			if (suggestedSliderHeight > $("#" + sliderId).height()) {
				$("#" + sliderId).css({
					//height: "300px"
				});
			}
			
			if (config.clickawayclose) {
				
				$("body").click( function () {
					CloseSliders("");
				});
			}
			
			// Put the content back now it is in position
			$This.css({ opacity: 1 });
			
			sliderCount++;
		});
		
		return this;
	};
})(jQuery);