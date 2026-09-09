<meta http-equiv="X-UA-Compatible" content="IE=8" />
 <script type="text/javascript" src="js/json2.js"></script>
<style>
.skewClass{
webkit-transform: rotate(-30deg) skew(10deg);
	-moz-transform: rotate(3-0deg) skew(10deg);
	-o-transform: rotate(-30deg) skew(10deg);
}

.zomBtn:hover{
 background-color: #AFAFAF;
    border: 1px outset rgba(255, 255, 255, 0.8);
    box-shadow: 3px 3px 5px #000000;
    cursor: pointer;
    outline: medium none;
    padding: 1px 18px 2px 1px;
    z-index: 210;
    width: 21px;
    height: 20px;
    margin-top:-2;
}
.zomBtn{
 cursor:pointer;
 z-index:210;
 padding:2px 2px 0px 1px;
  height:20;
 width:21;
 /*margin-top:-7;

 float:right;*/
 background-image: url("images/zoom_in.png");
 background-repeat: no-repeat;
 background-color: #EFEFEF;
}
</style>
<script type="text/javascript">
var  fromPage  ;
var newtpe; 
jQuery(document).ready(function(){
	fromPage = jQuery('#hdnfromPage').val();
	processAjaxCalls("getRelatedData.dashboard","pillar="+jQuery("#hdnSelDashbrdPillar").val(),"selectedPillarDsh_onsuccesscallback");
	jQuery("#DBaccordian > li > div").click(function(){
		
	    if(false == jQuery(this).next().is(':visible')) {
	        jQuery('#DBaccordian ul').slideUp(300);
	    }
	    jQuery(this).next().slideToggle(300);
	});

   
	jQuery('#DBaccordian ul:eq(0)').show();
	
	setTimeout(function() { 
		jQuery('.dashbrdLodr').removeAttr('src');
		//alert("val  " + jQuery('.highcharts-title').val());
		//alert("html  " +jQuery('.highcharts-title').html());
	},15250);
});

function selectedPillarDsh_onsuccesscallback(result){
	
	var columns = 2;
	
	var html="";
	var wdth=0;
	for( var i = 0; i< result.length ; i++){
		//alert(result[i].title);
		//alert(result[i].reportUrl);
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
				 "<div id='dashbd_"+result[i].keyId +"' style='position:relative; width:"+wdth+"; height: 90% ;overflow:auto\9;display:block;margin-top:2%;'><img class='dashbrdLodr' alt='' src='images/dashboardloader.gif' style='margin-top:33%;margin-left:45%'></div>";
				 
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
	//alert("lastTwoyear"+lastTwoyear);
	var toMonth = jQuery("#hdnToMonth").val();
	var cellId = jQuery("#frmEmpPageFuntKeyIds > [id=cell]").val();
	var sectId = jQuery("#frmEmpPageFuntKeyIds > [id=section]").val();	
	var pbuId = jQuery("#frmEmpPageFuntKeyIds > [id=pbu]").val();
	var sbuId = jQuery("#frmEmpPageFuntKeyIds > [id=sbu]").val();
	var flid = jQuery("#frmEmpPageFuntKeyIds > [id=flid]").val();

	var funcLoctionStr =  getFunctionalLocation("frmEmpPage");
	 
		for( var i = 0; i< result.length ; i++){
			//alert(" URL :: "+result[i].reportUrl);
			var plrtype=result[i].reportUrl;
			var text=plrtype.substring(0,plrtype.indexOf("newgrph"));
			var textnw=plrtype.substring(0,plrtype.indexOf("newgrphThree"));
			var texttwoyear=plrtype.substring(0,plrtype.indexOf("newgrphTwoyear"));
			//alert("The textnw::::"+texttwoyear);
		
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
			//alert(keyid);
		if( result[i].type != "TBL" )	{
			jQuery.ajax({
			       type: "GET",
			       url: result[i].reportUrl,
			       dataType:  "json",
			       data:"dashboardIdent="+ result[i].keyId +"&dashboard=true&dtFromMonth="+newtpe+"&dtToMonth="+toMonth+"&drillFlag=f&chkMonthwise=1&cellId="+cellId+"&parentId="+sectId+"&sectId="+sectId+"&flid="+flid +"&flId="+flid ,
			  
			       success: function(result)
			       {
						//alert(Object.keys(result.chartData));
				      if(jQuery.browser.msie && "landing" == fromPage)
						{
				    		 result.chartData.width = 300;
							 result.chartData.height = 350;
				    	 } 
			    	 /**Added By Manikandan for LAndpage chart in IE**/
			    	 if(jQuery.browser.msie && "landing" == fromPage)
					{
			    		// alert('dsh2');
			    		 //result.chartData.width = 380;
			    		 jQuery('.dashboard-cell').css('max-width','380px ');
			    		 jQuery('.dashboard-cell').css('height','480px ');
					}
			    	 
			    //	 if (result.chartData.subTitle.text.trim().length==0)
			    	 //	result.chartData.subTitle.text=funcLoctionStr;	
			    	 jQuery("#hdnchartData").append('<span  id='+result.dashboardIdent+'>'+JSON.stringify(result.chartData)+'</span>');	
			    	 //alert('dsh3');
			    	// result.chartData.subTitle.text="";
			    	 
			    	 
			    	  if( result.chartData != null && result.chartData.type != "gauge" ){
			    		    //alert(" chartData :: keys :: "+Object.keys(result.chartData));
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
		
	//jQuery("#dashbd_DSH00010").jqGrid('setColProp','CH0-0',{width:80});
	var grdId = jQuery('#grdId').val();
	var row = jQuery("#"+grdId).jqGrid('getDataIDs');
	 var cm = jQuery("#"+grdId).jqGrid("getGridParam", "colModel");
	 jQuery("#"+grdId).jqGrid('setGridWidth',530);
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=0;j<cm.length;j++)
     	 {
			 var Val = jQuery("#"+grdId).jqGrid('getCell',row[i],cm[j].name);	
			// jQuery("#"+grdId).jqGrid('setColProp',cm[j].name,{width:80});
			// jQuery("#"+grdId).jqGrid("gridResize", { shrinkToFit: false });
			//alert(grdId);
			
			 //jQuery("#"+grdId).jqGrid('setCell',row[i],cm[j].name,Val,{'color':'#000000'},{'width':80});
     	 }
	 }
			  		
/*jQuery(window).resize(function() {
	jQuery("#dashbd_DSH00010").jqGrid('setGridWidth', jQuery('.portlet-content').width()-5, true);
}).trigger('resize');*/
}
function btnSinglegrph(grphurl,keyid){
	//alert(grphData);
	jQuery.ajax({
		       type: "GET",
		       url: grphurl,
		       dataType:  "json",
		       data:"dashboardIdent="+ keyid +"&dashboard=true&dtFromMonth=Apr-2012&dtToMonth=Sep-2012&drillFlag=f&chkMonthwise=1",
		     
		       success: function(result)
		       {
		    	  // result.chartData.width = 400;
		    	 //  result.chartData.height = 300;
		    	 
		    	  jQuery('#dashboard-grid').css('display','none');
		    	  drawChart(result.chartData,'grapSingleContainer','Y','','','N','dashboard')	;
		       },
			   error:function(status)
			   {
			   }
		}); 
	//drawChart(grphData,"grapSingleContainer");
	
}
function funcZoomOutGrd(grdId){
jQuery("div.zomBtn").css("display","block");
jQuery("div.zomOut").css("display","none");
	
	jQuery('.dashboard-view attr[gridDiv'+closestDivclass+']').animate({'top':'-=20px','left':'-=40px', 'width':'-=500px'});
	 jQuery("#"+grdId).jqGrid('setGridWidth',530);	
}
function funcZoomGrd(grdId){
	//jQuery("div.zomBtn").css("display","none");
	//jQuery("div.zomOut").css("display","block");
	var closestDivclass = jQuery("div.zomBtn").parent().parent().attr("gridDiv");//alert(closestDivclass);
	var url = jQuery('#grdUrl').html();
	var zoominDiv = jQuery('#grapContainerZoom').html();
	/*var zomOutBtn = "<span style='position:absolute;left:15%;' class='' id='spnzoomOut'>";
	 zomOutBtn +="<input type='button' class='zomOut' id='btnzoomOut' onclick='funcZoomout();'/>";
	 zomOutBtn +="</span>";*/
	 var zomOutBtn = ' <span id="closeZoom" class="" style="float:right;"><img id="btnZoomClose" title="Close"  src="images/new_close.jpg"  onclick="") ></span>';
	if(zoominDiv != null || zoominDiv != ' '){
	
		setTimeout(function() { jQuery('#grapContainerZoom').html( zomOutBtn+jQuery('#grapContainerZoom').html());},1020);
	    navigateToNextForm( url+"?q=2&loadContentDivId=grapContainerZoom");
	}
	jQuery("#"+grdId).trigger("reloadGrid") ;
	jQuery("#"+grdId).jqGrid('setGridWidth',930);
	//alert(jQuery("#"+grdId).jqGrid('getGridWidth'));
	jQuery("#"+grdId).trigger("reloadGrid") ;
	jQuery('#graphzoom').css('display','block');
	
	//jQuery('#graphzoom').css('margin-left',mgLeft);
	jQuery('#graphzoom')
	.animate( {"opacity": "0.15"},
            "fast")
    .animate({"height": "80%"}, 500)
    .animate({"width": "80%"}, 500)
	.animate( {"opacity": "2.15"},
    "fast")
    
   // .animate({"margin-left":mgLeft}, 500);
    
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
	
	//jQuery('#graphzoom').css('display','block');
	jQuery('#graphzoom')
	.animate( {"opacity": "0.15"},
            "fast")
 	.animate({"height": "80%"}, 500)
    .animate({"width": "80%"}, 500)
	.animate( {"opacity": "2.15"},
    "fast")
    
   // .animate({"margin-left":mgLeft}, 500);
    
	//jQuery('#dashboard-grid').hide();
	
	 jQuery('.zomBtn').css('display','block');
	 //jQuery('#dashboard_grphcontent')
	//alert(jQuery('#dashbd_'+divId).parent('div').parent('div').attr('id').offset().top);
	 //jQuery('#graphzoom').css('transform','skew(1.08deg,1.05deg)');
	 
	var titleWidth;
	setTimeout(function() {
		//jQuery('#spnzoomin').css('display','none');		
		jQuery('#ChrtNewTitle').css('margin-top','1');
		////jQuery('#spnzoomout').show();
		//jQuery("#dataTableContainer_grapContainerZoom").css('margin-top','3%');
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
		//	 jQuery('#ChrtNewTitle').css('width',titleWidth);
			 //jQuery('.dashToolBar').css('width',titleWidth);
			
		}
		
		
		},1250);
	
	  //drawChart(chrtDta_obj,"grapContainerZoom",'Y','Y','','N','dashboard')	;
			 
     LoadPopUp("grapZoom", "zoomChart_input.dashboard?chrtdivId="+divId, true,"96%","78%","13%","1%", "multiSelectOk_Callback","Dashboard",false);
	 //var closebutt="<span id='closeZoom' class='' style='position:absolute;right:0.5%;cursor:pointer;top:6%;'><img id='btnZoomClose' title='Close'  src='images/new_close.jpg'  onclick='funcZoomout();' ) ></span>";
	 jQuery('#ChrtNewTitle').prepend(closebutt);
	
}
function grapZoom_onClose(){
	funcZoomout();
	return true;
}
function funcZoomout(){
	jQuery("div.zomBtn").css("display","block");
	/*setTimeout(function() { jQuery('#spnzoomin').css('display','block');jQuery('.dashToolBar').css('margin-top','-7');},1250);
	jQuery('#graphzoom')
	.animate({"opacity": "0.15"},
    "slow");*/
    //.animate({"margin-left": "0%"}, 500);
     
	 setTimeout(function() {jQuery('#graphzoom').hide();
	// jQuery('#dashboard-grid').animate( {"opacity": "2.15"},
	 //   "slow")
	    //.animate({"height": "80%"}, {"queue": false, "duration": 500});
	jQuery('#dashboard-grid').show();
	
	jQuery('input.zomBtn').addClass('zomBtn');
	jQuery('.zomBtn ').show();
	jQuery('.dashToolBar').css('width','99%');
	//jQuery(window).scrollTop(jQuery('#ImgGraphDiv_dashbd_DSH00003').offset().top);
	jQuery('#spnzoomin').show();},1050);
}


function drawGaugeChart(id,chartData){

	
var bindImg = getChartHtml(id,chartData.title,"dashboard");
	
	bindChartHtml(chartData,id,"N","N","Y",bindImg,"N");
	
	 new Highcharts.Chart({
		
	    chart: {
	        renderTo: 'chtContainer_' + id,
	        type: 'gauge',
	        plotBackgroundColor: null,
	        plotBackgroundImage: null,
	        plotBorderWidth: 0,
	        plotShadow: false
	    },
	    
	    title: {
	        text: chartData.chType
	    },
	    
	    pane: {
	        startAngle: -150,
	        endAngle: 150,
	        background: [{
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#CCC'],
	                    [1, '#444']
	                ]
	            },
	            borderWidth: 0,
	            outerRadius: '109%'
	        }, {
	            backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	                stops: [
	                    [0, '#555'],
	                    [1, '#DDD']
	                ]
	            },
	            borderWidth: 1,
	            outerRadius: '107%'
	        }, {
	            // default background
	        }, {
	            backgroundColor: '#DDD',
	            borderWidth: 0,
	            outerRadius: '105%',
	            innerRadius: '103%'
	        }]
	    },
	       
	    // the value axis
	    yAxis: {
	        min: 0,
	        max: 100,
	        
	        minorTickInterval: 'auto',
	        minorTickWidth: 1,
	        minorTickLength: 10,
	        minorTickPosition: 'inside',
	        minorTickColor: '#678',
	
	        tickPixelInterval: 30,
	        tickWidth: 2,
	        tickPosition: 'inside',
	        tickLength: 10,
	        tickColor: '#567',
	        labels: {
	            step: 2,
	            rotation: 'auto'
	        },
	        title: {
	            text: '%'
	        },
	        plotBands: [{
	            from: 0,
	            to: 40,
	            color: '#DF5353' // red
	            	
	            
	        },
			{
	            from: 40,
	            to: 60,
	            color: '#9D4566' // green
	        }, {
	            from: 60,
	            to: 80,
	            color: '#DDDF0D' // yellow
	        }, {
	            from: 80,
	            to: 100,
	            color: '#ACDE56' // green
	        }]        
	    },
	
	    series: [{
	        name: chartData.chType,
	        data: [chartData.data],
	        tooltip: {
	            valueSuffix: ' %'
	        }
	    }]
	
	});
	/*, 
	// Add some life
	function (chart) {
	    setInterval(function () {
	        var point = chart.series[0].points[0],
	            newVal,
	            inc = Math.round((Math.random() - 0.5) * 20);
	        
	        newVal = point.y + inc;
	        if (newVal < 0 || newVal > 200) {
	            newVal = point.y - inc;
	        }
	        
	        point.update(newVal);
	        
			
	    }, 3000);
	
	});*/
}
</script>
<style>
#dashboard-vertical-margin {
    -moz-box-flex: 1;
    -moz-box-orient: vertical;
    display: -moz-box;
    position: relative;
}
#dashboard-margin-top {
    -moz-box-flex: 1;
    max-height: 0px;
    min-height: 0px;
}
#dashboard-margin-bottom {
    -moz-box-flex: 1;
    max-height: 100px;
    min-height: 40px;
}
#dashboard-horizontal-margin {
    -moz-box-flex: 5;
    display: -moz-box;
}
.dashboard-side-margin {
    -moz-box-flex: 2;
    max-width: 300px;
    min-width: 40px;
}


#dashboard-grid {
    -moz-box-flex: 140;
    -moz-box-orient: vertical;
    -moz-transition-delay: 0s;
    -moz-transition-duration: 100ms;
    -moz-transition-property: opacity;
    -moz-transition-timing-function: ease-out;
    display: -moz-box;
    min-height: 600px;
    min-width: 600px;
}
.dashboard-row {
    -moz-box-direction: normal;
    -moz-box-flex: 1;
    -moz-box-orient: horizontal;
    display: -moz-box;
    height: 500px;
}
.dashboard-cell {
   -moz-box-flex: 1;
    border: 5px solid #FFFFFF;
    box-shadow: 0 0px 5px #AAAAAA;
    cursor: default;
    display: -moz-box;
    float: left;
    height: 460px;
    max-height: 460px;
    margin: 3px;
    overflow: hidden;
   /* position: relative;
    text-align: center;*/
    width: 600px;
     width: 300px\9;
    
   
}
.dashboard-cell:hover {
    -moz-box-flex: 1;
    border-image: none;
    border-style: solid;
    border-width: 5px 1px 5px 5px;
    max-height: 430px;
    height: 430px;
    margin: 4px;
    width: 600px;
   /* transform: scale(1.02,1.02);
    box-shadow: 1px 2px 18px #000;
   */
   
}
 .mask {
  border: 150px solid rgba(0, 0, 0, 0.7);
    border-radius: 89px 66px 61px 50px;
    cursor: pointer;
    /*display: inline-block;
    left: 100px;
   
     transform: scale(4);
    height: 300px;*/
      top: 50px;
    height: auto;
    opacity: 1;
     transform:translateY(-125px);
   transition: transform 0.3s ease-in, opacity 0.1s ease-in-out;
    position: absolute;
    transition: all 0.3s ease-in-out 0s;
    visibility: visible;
    width: 250px;
}
.mask {
   opacity: 0;
   border:0px solid rgba(0,0,0,0.7);
   visibility:hidden;
}
.dashboard-view {
    -moz-box-flex: 1;
    -moz-transition-delay: 0s;
    -moz-transition-duration: 100ms;
    -moz-transition-property: top, left, opacity;
    -moz-transition-timing-function: ease-out;
    position: relative;
}
.dashboard-view[frozen] {
    pointer-events: none;
    position: absolute;
}
.dashboard-view[dragged] {
    -moz-transition-property: none;
    z-index: 10;
}
.dashboard-link, .dashboard-thumbnail {
    bottom: 0;
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
}

.dashboard-thumbnail {
    -moz-transition-delay: 0s;
    -moz-transition-duration: 100ms;
    -moz-transition-property: opacity;
    -moz-transition-timing-function: ease-out;
    opacity: 0.8;
} 	
.dashboard-thumbnail[dragged], .dashboard-link:-moz-focusring > .dashboard-thumbnail, .dashboard-view:hover > .dashboard-link > .newtab-thumbnail {
    opacity: 1;
}
.dashboard-title {
    top: 0;
    left: 0;
    overflow-x: hidden;
    overflow-y: hidden;
    position: absolute;
    right: 0;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.dashboard-control {
    -moz-transition-delay: 0s;
    -moz-transition-duration: 100ms;
    -moz-transition-property: opacity;
    -moz-transition-timing-function: ease-out;
    opacity: 0;
    position: absolute;
    top: 4px;
}
.dashboard-control:-moz-focusring, .dashboard-view:hover > .dashboard-control {
    opacity: 1;
}
.dashboard-control[dragged] {
    opacity: 0 !important;
}
.dashboard-control {
    opacity: 1;
}
.dashboard-control-options:-moz-locale-dir(ltr), .dashboard-control-block:-moz-locale-dir(rtl) {
    left: 4px;
}
.dashboard-control-block:-moz-locale-dir(ltr), .dashboard-control-options:-moz-locale-dir(rtl) {
    right: 4px;
}
.dashboard-drag {
    background-color: #FFFFFF;
    height: 1px;
    opacity: 0.01;
    width: 1px;
}
.zomOut:hover{
 background-color: #AFAFAF;
    border: 1px outset rgba(255, 255, 255, 0.8);
    box-shadow: 3px 3px 5px #000000;
    cursor: pointer;
    outline: medium none;
    padding: 1px 18px 2px 1px;
    z-index: 210;
    width: 21px;
    height: 20px;
    margin-top:-2;
}
.zomOut{
cursor:pointer;
 z-index:210;
 padding:2px 2px 0px 1px;
  height:20;
 width:21;
 /*margin-top:-7;

 float:right;*/
 background-image: url("images/zoom_out.png");
 background-repeat: no-repeat;
 background-color: #EFEFEF;
 }
/*.zomOut{
background-image: url("images/zooomout.png");
background-repeat:no-repeat;
background-color:#fff;
height:29px;
width:25px;
cursor:pointer;
z-index:1522;
margin-right:11px;

}*/
</style>
<div id='graphzoom' style='height:0;width:0;'>
	<!--<span style='display:none;position:absolute;right:0;' class='' id="spnzoomOut">
	<input type="button" class='zomOut' id="btnzoomOut" onclick='funcZoomout();'/>
		<img  class="" style="cursor: pointer;z-index:210;margin-top:-3" src="images/zoom_out.png" title="zoomout" alt="" id="btnzoomOut">
	</span>
	-->
	<div id="grapContainerZoom">
		
		
	</div>
	<div id="grapSingleContainer">
		<div class=""></div>
	</div>
</div>
<div id='dashboard-margin-top'></div>
<div class='dashboard-side-margin'></div>
<div id="dashboard-grid" style="margin-left: 0%;">
</div>
<div class='dashboard-side-margin'></div>
<div id="dashboard-margin-bottom"></div>
<input type="hidden" id="hdnSelDashbrdPillar" value ="${requestScope.pillar }" />
<input type="hidden" id="hdnFromMonth" value ="${requestScope.FromMonth}" />
<input type="hidden" id="hdnLstFromMonth" value ="${requestScope.LstFromMonth}" />
<input type="hidden" id="hdnLstThrMonth" value="${requestScope.lastTheeMonth}"/>
<input type="hidden" id="hdnLstTwoyears" value="${requestScope.lastTwoyear}"/>
<input type="hidden" id="hdnToMonth" value ="${requestScope.ToMonth}" />
<input type="hidden" id="hdnfromPage" value ="${requestScope.fromPage}" />

<div  id="hdnchartData" style="display:none" ></div>
