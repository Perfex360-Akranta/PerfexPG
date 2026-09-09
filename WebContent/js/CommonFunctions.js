/**

 * @Author:Admin
 * @Description:Generic Javascript &  jQuery functions used in the project
 */
//if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0") {
var glbMainFormUrl =null;
var frmMode = new formMode();

function isArray(obj) {
    return obj.constructor == Array;
}
if(!Object.keys) Object.keys = function(o){
	if (o !== Object(o))
	throw new TypeError('Object.keys called on non-object');
	var ret=[],p;
	for(p in o) if(Object.prototype.hasOwnProperty.call(o,p)) ret.push(p);
	return ret;
};
Array.prototype.remove = function(e) {
    var t, _ref;
    if ((t = this.indexOf(e)) > -1) {
        return ([].splice.apply(this, [t, t - t + 1].concat(_ref = []), _ref));
    }
};

if (typeof String.prototype.startsWith != 'function') {
  String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
  };	
}
jQuery.fn.extend(
		{
		  scrollTo : function(speed, easing)
		  {
		    return this.each(function()
		    {
		      var targetOffset = jQuery(this).offset().top;
		      jQuery('html,body').animate({scrollTop: targetOffset}, speed, easing);
		    });
		  }
		});

/*
jQuery.ctrl = function(key, callback, args) {
	jQuery(document).keydown(function(e) {
        if(!args) args=[]; // IE barks when args is null
        if(e.keyCode == key.charCodeAt(0) && e.ctrlKey) {
        	
            callback.apply(this, args);
            return false;
        }
    });
};
*/
jQuery.fn.hasAttr = function (attr) {
	if( this[0] != undefined && this[0] != null ){
	    for (var i = 0; i < this[0].attributes.length; i++) {
	        if (this[0].attributes[i].nodeName == attr) {return true;}
	    }
	}
   return false;
};

if (typeof String.prototype.trim != 'function') {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g,"");
	};
}

function hasSpecialCharacters(str) {
	//alert(1);
 // const regex = /[^a-zA-Z0-9 _.\-()/%,]/; 
  const regex = /[^\w\s._\-()/%,]/;
  return regex.test(str);
}

function loadFormSessionTimeOut(response, status, xhr){
	
	if( (response != null && (response.sExpires == "true")) || ( response != null && response.indexOf('sExpires') > -1 && response.indexOf('true' > -1)) ) {
		  showCommonErrorMsg("Session has expired! Please login again" ) ;
		  div_err();
		  setTimeout(reLogin,3000);
		  return false;
	}	
	return true;
}

function reLogin(){
	 document.location.href = "perfex";
}

jQuery.fn.extend({
	  slideRight: function() {
	    return this.each(function() {
	      jQuery(this).animate({width: 'show'});
	    });
	  },
	  slideLeft: function() {
	    return this.each(function() {
	      jQuery(this).animate({width: 'hide'});
	    });
	  },
	  slideToggleWidth: function() {
	    return this.each(function() {
	      var el = jQuery(this);
	      if (el.css('display') == 'none') {
	        el.slideRight();
	      } else {
	        el.slideLeft();
	      }
	    });
	  }
	});

/*function LoadForm(loadDIVid,preloadDIVid,url,errMsgDispDIVId,callbackFunctionOnSuccess,callbackFunctionOnError)
{
	if( url != null)
	{	
		var args = loadDIVid+'((*))'+preloadDIVid+'((*))'+url+'((*))'+errMsgDispDIVId+'((*))'+callbackFunctionOnSuccess+'((*))'+callbackFunctionOnError +'((*))';
		
		jQuery.address.path(args);
	}	
}
*/
function LoadingFormWaiter(preloadDIVid,loadDIVid){
	if( preloadDIVid != null && preloadDIVid.length > 0 && jQuery("#"+preloadDIVid).length > 0  ){
		
		var loadingClass = jQuery("#"+preloadDIVid).attr("class");

		if( loadingClass == null || loadingClass.indexOf("loading") < 0 )
			jQuery("#"+preloadDIVid).addClass("tpm-loading");
		
		jQuery("#"+preloadDIVid).css("display","block");
	}
	jQuery("#"+preloadDIVid).html("Loading...");
 	jQuery("#"+loadDIVid).css("display", "none");

}

var loadFormRequests = {};

//function LoadFormAddress(loadDIVid,preloadDIVid,url,errMsgDispDIVId,callbackFunctionOnSuccess,callbackFunctionOnError)
function LoadForm(loadDIVid,preloadDIVid,url,errMsgDispDIVId,callbackFunctionOnSuccess,callbackFunctionOnError)
{
	if( url != null)
	{	
		// jQuery.address.path(loadDIVid.slice(-1));
		
		/*if( preloadDIVid != null && preloadDIVid.length > 0 && jQuery("#"+preloadDIVid).length > 0  ){
		
			var loadingClass = jQuery("#"+preloadDIVid).attr("class");

			if( loadingClass == null || loadingClass.indexOf("loading") < 0 )
				jQuery("#"+preloadDIVid).addClass("tpm-loading");
			
			jQuery("#"+preloadDIVid).css("display","block");
		}
		jQuery("#"+preloadDIVid).html("Loading...");
	 	jQuery("#"+loadDIVid).css("display", "none");
	 	*/
		LoadingFormWaiter(preloadDIVid,loadDIVid);
	 	if( ! jQuery("#"+loadDIVid).hasAttr("tabindex"))
	 		jQuery("#"+loadDIVid).attr("tabindex","0");
	 	
	 	
	 	show_winMask(1);
	 	
	 	if( loadFormRequests[loadDIVid] )
			loadFormRequests[loadDIVid].abort();
	 	
	 	/*	jQuery("#"+loadDIVid).load(url, function(response, status, xhr) { */
		
	 	loadFormRequests[loadDIVid] = jQuery.get(url,function(response, status, xhr){
	 		
	 		jQuery("#"+loadDIVid).html(response);
	 		
	 		if( loadFormRequests[loadDIVid] );
	 			delete loadFormRequests[loadDIVid];
			
			
			jQuery('#home_center').css("width","95.9%");
			jQuery('#home_center').css("position","relative");
			if (status == "error") {	
			
				var msg = "Sorry but there was an error: ";
				jQuery("#"+errMsgDispDIVId).html(msg + xhr.status + " " + xhr.statusText);
	          	
			    if( callbackFunctionOnError != null)
			    	eval(callbackFunctionOnError+'(response)');
			}
			else{
							
				 jQuery("#"+preloadDIVid).hide();
				  show_winMask(0);
				  jQuery("#"+loadDIVid).show();
				  jQuery("#"+loadDIVid).css("display", "block");
				if( callbackFunctionOnSuccess != null)
					eval(callbackFunctionOnSuccess+'(response)');
				/*********************/
				invokeAfterLoadFormCallBack(); /* Do not misplace this function call */
				/*********************/
					
			} 
		  	  
		  jQuery("#"+preloadDIVid).hide();
		  show_winMask(0);
		  jQuery("#"+loadDIVid).show();
		  jQuery("#"+loadDIVid).css("display", "block");
		  
		  if( ! loadFormSessionTimeOut(response, status, xhr) )
			  return ;
		});
	}
	
}


function setGridBackgroundColor(gridId){
	jQuery("#" + gridId ).parent().parent().css("background-color", "#e0ebeb");
}
function setFormMainHeader(formMainHeader )
{
	jQuery('.panel-title').filter(function(){
	    return jQuery(this).parent('div').parent('div').hasClass('layout-panel-center');
	}).text(formMainHeader);
}
function getFormMainHeader( )
{
	return jQuery('.panel-title').filter(function(){
	    return jQuery(this).parent('div').parent('div').hasClass('layout-panel-center');
	}).text();
}

function checkFilterValueExist(filterString,valueIdentifier)
{
	if( filterString != null && valueIdentifier != null )
	{
		if( filterString.indexOf(valueIdentifier) > 0)
		{	
			var substr = filterString.substring(filterString.indexOf(valueIdentifier)+valueIdentifier.length+1 );
			
		
			var val = substr.substring(0,substr.indexOf("&"));
			if( val == null || val.trim() == "" )
				return false;
		
			return true ;
		}	
	}
	return false;
}

function getFilterValue(filterString,valueIdentifier)
{
	if( filterString != null && valueIdentifier != null && filterString.indexOf(valueIdentifier) > -1 )
	{
		
		var substr = filterString.substring(filterString.indexOf(valueIdentifier)+valueIdentifier.length+1 );
		var val = substr.substring(0,substr.indexOf("&"));

		return val;
	}	
	
	return "";
}

function processGrid(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback ){
	//var mygrid ;
	
	var getDataUrl ="";
	var getColumnUrl = url.replace('input','getCol');
	if( filterString != null &&  filterString !="")
		getDataUrl = url.replace('input','getData')+filterString;
	
	if( ! jQuery("#preLoadContent").hasClass("tpm-loading"))
		jQuery("#preLoadContent").addClass("tpm-loading");
	
	jQuery("#preLoadContent").css("display","block"); 
 	jQuery("#LoadContent").css("display", "none");

	jQuery.ajax({
		       type: "POST",
		       url: getColumnUrl,
		       dataType: "json",
		       data:filterString,
		       success: function(result)
		       {
		            colN = result.colNames;
		            colM = result.colModel;

		            var headerNames = []; //-------test---
		            
		            var groupByField = result.groupByField;
					groupBy = (result.isGroupBy == "true" ? true: false);
					var rownumbers = (groupBy == true ? false : (result.isRowSumbers =="false"?true:false));
					groupSummary = (groupBy == true ? result.groupSummary == "true" ?true : false : false);
		            
					if( result.tableCaption != null && result.tableCaption != "")
		            	tableCaption = result.tableCaption;
					
					
		            
		             headerNames[ 0 ] = colN[ 0 ]; //-------test---
		             for( var j = 1 ; j < colN.length;j++) //-------test---
		            	headerNames[ j ] =colN[j];// ""; //-------test---
		            
		            for(var i = 0; i < colM.length;i++ ){
		            	
		             	if( colM[i].formatter != null){
		             		var functObj = eval('(' + colM[i].formatter + ')');
		             		colM[i]["formatter"] = functObj ;
		             	}
		             	if(colM[i].cellattr != null){
		             		var cellattrObj = eval('( function (rowId, tv, rawObject, cm, rdata){ return ' + colM[i].cellattr + ';})');
		             		colM[i]["cellattr"] = cellattrObj ;
		             	}
		            }

		           
				    jQuery("#"+tableId).GridUnload();
		            mygrid =  jQuery("#"+tableId);
		            jQuery("#"+tableId).jqGrid({
				             		url:getDataUrl,
								    datatype: 'json',
								    colNames: colN,  //-------test---
									colModel: colM,
									rowNum:1000,
									rowList:[500,1000,1500],
									rownumbers: rownumbers,
									shrinkToFit:false,
									//pager: pagerId,
									sortname: 'id',
									//toolbar: [true,"top"] ,
									viewrecords: true,
									sortorder: "asc",
									//caption:tableCaption,
									width:window.innerWidth-200,
									height:'80%',
									toppager:true,
									grouping: groupBy,
									groupingView : {
										groupField : [groupByField],
										groupColumnShow : [false],
										groupText : ['<b>{0}</b>'],
										groupCollapse : false,
										groupOrder: ['asc'],
										groupSummary : [groupSummary],
										groupDataSorted : true
									},
									ondblClickRow: function(id){
										if( doubleClickFunction != null && doubleClickFunction.length > 0 )
											eval(doubleClickFunction+'(id)');
									},
									gridComplete:function() {
										
										
									//	jQuery("div[id^=jqgh_" +tableId +"]:gt(8)").css("height","150px");
									//	jQuery("div[id^=jqgh_" +tableId +"]:gt(8)").css("width","50px");
										jQuery("div[id^=jqgh_" +tableId +"]:gt(8)").addClass("rotate");
										if( onloadcompletecallback != null && onloadcompletecallback.length >0  )
										{
											eval(onloadcompletecallback+'( )');
										}	
										
									}
							});
		            //jQuery("#" + tableId ).parent().parent().css("background-color", "white");
		            //jQuery("#" + tableId).css("background-color", "white");
		            
		            setGridBackgroundColor(tableId);
		            jQuery("#gbox_" + tableId).css("left","-2%");
		           //for left side buttons
		            var tableString="<div id='left'><table  style='float:left;table-layout:auto; cellspacing='0' cellpadding='0' border='0' >";
		            tableString+="<tbody> <tr><td id='toolbar'>";
					tableString+="<div style='padding-left:2px;padding-top:0px;'>";
					tableString+="<div class='grid_icon' id='fil' >FILTER</div>";
					tableString+="<div class='grid_icon' id='ref' >REFRESH</div  >";
					tableString+="<div class='grid_icon' id='srch'>SEARCH</div>";
					tableString+="<div class='grid_icon' id='clr' >CLEAR</div  >";
					tableString+="<div class='grid_icon' id='del' >DELETE</div  >";
					tableString+="</td></tr></tbody></table></div>";
					
					  //for right side buttons
					var tableString1="<div id='rght' style='align:left;'><table  style='table-layout:auto; cellspacing='0' cellpadding='0' border='0'>";
			        tableString1+="<tbody> <tr><td id='toolbar'>";
			        tableString1+="<div class='grid_icon' id='grph' >GRAPH</div>";
					tableString1+="<div class='grid_icon' id='' style='padding-top:4px;margin-right:12px;font-size:11px;font-family: Arial Rounded MT Bold;font-weight:bolder;color:#000;'>EXPORT TO</div>";
					tableString1+="<span class='grid_icon' id='' style='border : inset 1px #fff;vertical-align:top;' >";

					tableString1+="<div class='grid_icon' id='exptxl' >EXCEL</div>";
					tableString1+="<div class='grid_icon' id='exptpdf'>PDF</div>";
					tableString1+="<div class='grid_icon' id='exptppt'>PPT</div>";
					tableString1+="</span >";
					
					tableString1+="<div class='grid_icon' id='btnJqGridBack' >BACK</div>";
					tableString1+="</td></tr></tbody></table></div>";
					  
			      //  jQuery("#"+tableId+"_toppager_left").css("background-color","#548DE9");
					jQuery("#"+tableId+"_toppager_left").css("background-color","#548DE9");
					jQuery("#"+tableId+"_toppager_left").append(tableString); 
					jQuery("#"+tableId+"_toppager_right").css("background-color","#548DE9");
					jQuery("#"+tableId+"_toppager_center").css("background-color","#C7CEFD");
					jQuery("#"+tableId+"_toppager_center").css("border","ridge 1px #000");
				
					jQuery("#"+tableId+"_toppager_right").html(tableString1); 
					
		            jQuery("#"+tableId).jqGrid('navGrid','#t_'+tableId,{edit:false,add:false,del:false})
		            .navButtonAdd('#t_'+tableId,{
	                     caption:"Export to Excel", 
	                     buttonicon:"ui-icon-save", 
	                     onClickButton: function(){
//	                    	 jQuery("#"+tableId).excelExport();
	                    	 exportExcel(tableId);
	                     }, 
	                     position:"last"
	                 });
		            
		            jQuery('#exptxl').click(function (){
		            	
		            	exportExcel(tableId);
		            });
		           /* jQuery('#btnJqGridBack').click(function (){
		            	 try{
		            		//asdasda
		            			window[tableId+"_onProcessGridBack"].apply(this,null);
		            			
		            		}catch(Exception ){
		            			
		            		}
		            });	*/
		            
		            jQuery("#"+tableId).jqGrid('bindKeys');
		            
		            if( tableHeaderSpanCallback!= null && tableHeaderSpanCallback.length > 0 )
		            {	
	            		var headers = mygrid[0].grid.headers;
	            		eval(tableHeaderSpanCallback +'(colM,headers,colN)');
		                
		            }	
		        },
		        error: function(status){
		        	//alert('err');
			  	}
		});
	
	  jQuery("#preLoadContent").hide(); 
	  jQuery("#LoadContent").show();
}

/*function processGrid(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback ){
	//var mygrid ;
	
	var getDataUrl ="";
	var getColumnUrl = url.replace('input','getCol');
	if( filterString != null &&  filterString !="")
		getDataUrl = url.replace('input','getData')+filterString;
	
//	if( jQuery("#preloadtableId" ).length <= 0 )
	//jQuery("#"+tableId).prepend('<div id="preloadtableId"/>');
		
	if( ! jQuery("#preLoadContent").hasClass("tpm-loading"))
		jQuery("#preLoadContent").addClass("tpm-loading");
	
	jQuery("#preLoadContent").css("display","block"); 
 	jQuery("#LoadContent").css("display", "none");
	 
	jQuery.ajax({
		       type: "POST",
		       url: getColumnUrl,
		       dataType: "json",
		       data:filterString,
		       success: function(result)
		       {
		            colN = result.colNames;
		            colM = result.colModel;

		            var headerNames = []; //-------test---
		            
		            var groupByField = result.groupByField;
					groupBy = (result.isGroupBy == "true" ? true: false);
					var rownumbers = (groupBy == true ? false : (result.isRowSumbers =="false"?true:false));
					groupSummary = (groupBy == true ? result.groupSummary == "true" ?true : false : false);
		            if( result.tableCaption != null && result.tableCaption != "")
		            	tableCaption = result.tableCaption;
		            
		             headerNames[ 0 ] = colN[ 0 ]; //-------test---
		             for( var j = 1 ; j < colN.length;j++) //-------test---
		            	headerNames[ j ] =colN[j];// ""; //-------test---
		            
		            for(var i = 0; i < colM.length;i++ ){
		            	
		             	if( colM[i].formatter != null){
		             		var functObj = eval('(' + colM[i].formatter + ')');
		             		colM[i]["formatter"] = functObj ;
		             	}
		             	if(colM[i].cellattr != null){
		             		var cellattrObj = eval('( function (rowId, tv, rawObject, cm, rdata){ return ' + colM[i].cellattr + ';})');
		             		colM[i]["cellattr"] = cellattrObj ;
		             	}
		            }

		            jQuery("#"+tableId).GridUnload();
		            mygrid =  jQuery("#"+tableId);
		            jQuery("#"+tableId).jqGrid({
				             		url:getDataUrl,
								    datatype: 'json',
								    colNames: colN,  //-------test---
									colModel: colM,
									rowNum:1000,
									rowList:[500,1000,1500],
									rownumbers: rownumbers,
									shrinkToFit:false,
									pager: pagerId, 
									sortname: 'id',
									viewrecords: true,
									sortorder: "asc", 
									caption:tableCaption,
									width:window.innerWidth-200,
									height:'80%',
									grouping: groupBy, 
									groupingView : { 
										groupField : [groupByField],
										groupColumnShow : [false],
										groupText : ['<b>{0}</b>'],
										groupCollapse : false,
										groupOrder: ['asc'],
										groupSummary : [groupSummary],
										groupDataSorted : true 
									},
									ondblClickRow: function(id){
										if( doubleClickFunction != null && doubleClickFunction.length > 0 )
											eval(doubleClickFunction+'(id)');
									},
									gridComplete:function() {
										
									//	jQuery("div[id^=jqgh_" +tableId +"]:gt(8)").css("height","150px");
									//	jQuery("div[id^=jqgh_" +tableId +"]:gt(8)").css("width","50px");
										jQuery("div[id^=jqgh_" +tableId +"]:gt(8)").addClass("rotate");
										if( onloadcompletecallback != null && onloadcompletecallback.length >0  )
										{
											eval(onloadcompletecallback+'( )');
										}	
									}
							});
		            
		            jQuery("#"+tableId).jqGrid('navGrid','#'+pagerId,{edit:false,add:false,del:false})
		            .navButtonAdd('#'+pagerId,{
	                     caption:"Export to Excel", 
	                     buttonicon:"ui-icon-save", 
	                     onClickButton: function(){
//	                    	 jQuery("#"+tableId).excelExport();
	                    	 exportExcel(tableId);
	                     }, 
	                     position:"last"
	                 });
		            
		            jQuery("#"+tableId).jqGrid('bindKeys' );
		            
		            if( tableHeaderSpanCallback!= null && tableHeaderSpanCallback.length > 0 )
		            {	
	            		var headers = mygrid[0].grid.headers;
	            		eval(tableHeaderSpanCallback +'(colM,headers,colN)');
		                
		            }	
		        },
		        error: function(status){
			  	}
		});
	
	  jQuery("#preLoadContent").hide(); 
	  jQuery("#LoadContent").show();

}
*/
function exportExcel(tableId,format)
{
  if(jQuery("#formxlexport").length <= 0 )
    {	  
        var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST" > ' +
        		   '<input type="hidden" id="fileName" name="fileName" /> '+
        		   '<input type="hidden" id="f" name="f"  /> '+
				   '<input type="hidden" id="multiple" name="multiple"  /> '+
				   '<input type="hidden" id="fileName" name="fileName"  /> '+
				   '<input type="hidden" id="chartData" name="chartData"  /> '+
      	  	       '<input type="hidden" id="exporthtml" name="exporthtml"/>  '+
      	  	       '<input type="hidden" id="colModel" name="colModel"/>  </form>';
        jQuery("#LoadContent").prepend(xlFormHtml);
        
    }
  
    var rownumber = jQuery("#"+tableId).jqGrid('getGridParam', 'rownumbers'); 
    var colModel = jQuery("#"+tableId).jqGrid('getGridParam', 'colModel'); 
    var colNames = jQuery("#"+tableId).jqGrid('getGridParam', 'colHeaders');
    var groupBy = jQuery("#"+tableId).jqGrid('getGridParam', 'grouping');
    var groupingView = jQuery("#"+tableId).jqGrid('getGridParam', 'groupingView');
    var groupByField = groupingView.groupField[0];
    
    var gridEdit = jQuery("#"+tableId).jqGrid('getGridParam', 'gridEdit');
    var cModel = [];
    var sIndx =  1;
    if(gridEdit != undefined && gridEdit == true)
    	sIndx =2;
    if(rownumber!=undefined && rownumber ){
    	for( var k = sIndx;k<colModel.length;k++)
    		cModel[k-sIndx] =colModel[k]; //cModel.splice(1,cModel.length-1);
    }
    else{
    	cModel=colModel;
    }
    var tblModel = new Object();
    tblModel.colModel = cModel;
    tblModel.colNames=colNames;
    tblModel.groupBy = groupBy;
    tblModel.groupByField=groupByField;

    
    var colModelStr = JSON.stringify(tblModel);
    jQuery("#formxlexport input[id=f]").val(format);
    jQuery("#formxlexport input[id=colModel]").val(colModelStr);
     var url = jQuery("#"+tableId).jqGrid('getGridParam', 'url');
 	 document.formxlexport.method='POST';
	 document.formxlexport.action=url.replace("_getData","_getExcel") ;  // send it to server which will open this contents in excel file
	 document.formxlexport.target='_blank';
	 document.formxlexport.submit();

}

/*
var ajaxQ = [];
var processAjax = true;
function ajaxQObj(url,data,onsuccessCallBack,onerrorCallBack,dataType){
	this.url = url;
	this.data = data;
	this.onsuccessCallBack = onsuccessCallBack;
	this.onerrorCallBack = onerrorCallBack;
	this.dataType = dataType;
}


function processAjaxCalls(url,data,onsuccessCallBack,onerrorCallBack,dataType)
{	
	
	var ajaxCallObj = new ajaxQObj(url,data,onsuccessCallBack,onerrorCallBack,dataType);
	ajaxQ.push(ajaxCallObj);
	if( processAjax){
		show_winMask(1);
		processAjax = false;
		var ajxElmnt = ajaxQ.shift();
		processAjaxCall(ajxElmnt.url,ajxElmnt.data,ajxElmnt.onsuccessCallBack,
				ajxElmnt.ajxElmnt.onerrorCallBack,ajxElmnt.dataType);
	}	
}	imageUpload
*/

var ajaxRequests = {};
function processAjaxCalls(url,data,onsuccessCallBack,onerrorCallBack,dataType,requestId,hideProcessing){	
	
	if( requestId != null && requestId != undefined && requestId.length > 0 && requestId != "")
	{
		if( ajaxRequests[ requestId ] )
			ajaxRequests[ requestId ].abort();
	}	
	if(hideProcessing!=true)
		show_winMask(1);
	var reqObj = jQuery.ajax({
	       type: "POST",
	       url: url,
	       dataType: (dataType != null && dataType != undefined && dataType.length > 0)? dataType :  "json",
	       data:data,
	       success: function(result)
	       {
	    	   if( ajaxRequests[ requestId ] )
		    	  delete ajaxRequests[ requestId ];
	    	  if(hideProcessing!=true)  
	    		  show_winMask(0);
	    	  if( result != null && result.sExpires){
	    		   loadFormSessionTimeOut(result);
	    	  }
	    	  //eval( '('+onsuccessCallBack +'(result) )' );
	    	  var args = [result];
	    	  dynamicFunctionCall(onsuccessCallBack, args);
	    
	       },
		   error:function(status)
		   {
		
			   if( status.statusText != "abort"){
				   if( ajaxRequests[ requestId ] )
		    		  delete ajaxRequests[ requestId ];
				   if(hideProcessing!=true)
					   show_winMask(0);
				 
					// eval( '('+onerrorCallBack +'(status) )' );
					var args = [status];
					dynamicFunctionCall(onerrorCallBack, args);
				   
			  }
		      
			   if( status != null && status.sExpires){
	    		   loadFormSessionTimeOut(status);
			   } 
		   }
		   
	}); 
	
	if( requestId != null && requestId != undefined && requestId.length > 0 && requestId != "")
		ajaxRequests[ requestId ] = reqObj;
}
var glbcmbCellid,glbcmbSectionId,cmbFactId,glbCmbCostCenter, glbCmbLocnId, glbCmbCircleId;

function fillMachineHierarchy(url,machineId,cmbCellId,cmbSectionId,cmbFactId,cmbCompId, cmbLocnId,cmbCostCenter){

	glbcmbCellid=cmbCellId;
	glbcmbSectionId=cmbSectionId;
	glbcmbFactId=cmbFactId;
	glbCmbCompId = cmbCompId;
	glbCmbLocnId = cmbLocnId;
	glbCmbCostCenter = cmbCostCenter;
	
	processAjaxCalls(url,'machineId='+machineId,'machineHierarchy_OnSuccess','machineHierarchy_OnError');
}
function machineHierarchy_OnSuccess(result)
{ 
	jQuery("#"+glbcmbCellid).combobox('setValue',result.machineHirerachy.cell);
	jQuery("#"+glbcmbSectionId).combobox('setValue',result.machineHirerachy.section);
	jQuery("#"+glbcmbFactId).combobox('setValue',result.machineHirerachy.factory);
	jQuery("#"+glbCmbLocnId).combobox('setValue',result.machineHirerachy.location);
	jQuery("#"+glbCmbCompId).combobox('setValue',result.machineHirerachy.company);
 
	//if(result.machineHirerachy.costcenterid != null && result.machineHirerachy.costcenterid != '' && result.machineHirerachy.costcenterid != ' ')
	//alert(result.machineHirerachy.costcenterid);	
	jQuery("#"+glbCmbCostCenter).combobox('setValue',result.machineHirerachy.costcenterid);
		 
	//else
		
}
function machineHierarchy_OnError(result){
	
}

function fillFunctionalLocHierarchy(originalId){

/*	glbcmbCellid=cmbCellId;
	glbcmbSectionId=cmbSectionId;
	glbcmbFactId=cmbFactId;
	glbCmbCompId = cmbCompId;
	glbCmbLocnId = cmbLocnId;
	glbCmbCostCenter = cmbCostCenter;
*/	
	processAjaxCalls("fetchFLHierarchy.commonFilter",'originalId='+originalId,'functionalLocHierarchy_OnSuccess','functionalLocHierarchy_OnError');
}

function setComboValueSilent(comboId, value) {
    var cmb = jQuery('#' + comboId);
    var opts = cmb.combobox('options');

    var oldOnSelect = opts.onSelect;
    opts.onSelect = function () {};   // temporarily disable

    cmb.combobox('setValue', value);

    opts.onSelect = oldOnSelect;      // restore
}

function functionalLocHierarchy_OnSuccess(result)
{
       
       if( result.flHirerachy.company != null && result.flHirerachy.company != undefined   )
    	   if(  ! jQuery('#cmbFunctLocComp').is(':disabled'))
               setComboValueSilent("cmbFunctLocComp",result.flHirerachy.company);
    	   else
       		   setComboValueSilent("cmbFunctLocComp",result.flHirerachy.company);
       
       if( result.flHirerachy.location != null && result.flHirerachy.location != undefined  )
    	   if(!jQuery('#cmbFunctLocLocn').is(':disabled')) 
               setComboValueSilent("cmbFunctLocLocn",result.flHirerachy.location); 
    	   else
    		   setComboValueSilent("cmbFunctLocLocn",result.flHirerachy.location); 
       
       if( result.flHirerachy.sbu != null && result.flHirerachy.sbu != undefined ) 
    	   if(   !jQuery('#cmbFunctLocSBU').is(':disabled') )
               setComboValueSilent("cmbFunctLocSBU",result.flHirerachy.sbu);
    	   else 
    		   setComboValueSilent("cmbFunctLocSBU",result.flHirerachy.sbu);
       
       if( result.flHirerachy.factory != null && result.flHirerachy.factory != undefined )
    	   if(   !jQuery('#cmbFunctLocFact').is(':disabled') )
               setComboValueSilent("cmbFunctLocFact",result.flHirerachy.factory); 
    	   else
    		   setComboValueSilent("cmbFunctLocFact",result.flHirerachy.factory); 
       
       if( result.flHirerachy.pbu != null && result.flHirerachy.pbu != undefined )
    	   if(!jQuery('#cmbFunctLocPBU').is(':disabled')  )
               setComboValueSilent("cmbFunctLocPBU",result.flHirerachy.pbu); 
           else
        	   setComboValueSilent("cmbFunctLocPBU",result.flHirerachy.pbu);
       
       if( result.flHirerachy.section != null && result.flHirerachy.section != undefined ) 
    	   if ( !jQuery('#cmbFunctLocSect').is(':disabled')  )        
               setComboValueSilent("cmbFunctLocSect",result.flHirerachy.section); 
    	   else
    		   setComboValueSilent("cmbFunctLocSect",result.flHirerachy.section);
       
       if( result.flHirerachy.cell != null && result.flHirerachy.cell != undefined  ) 
    	   if (  !jQuery('#cmbFunctLocCell').is(':disabled') ){

               setComboValueSilent("cmbFunctLocCell",result.flHirerachy.cell); //
    	   }
    	   else
    		    setComboValueSilent("cmbFunctLocCell",result.flHirerachy.cell);
       
       //if(result.flHirerachy.costcenterid != null && result.flHirerachy.costcenterid != '' && result.flHirerachy.costcenterid != ' ')
       //        jQuery("#"+glbCmbCostCenter).combobox('setValue',result.flHirerachy.costcenterid);
               
       //else
}

/*function functionalLocHierarchy_OnSuccess(result)
{
       
       if( result.flHirerachy.company != null && result.flHirerachy.company != undefined   )
    	   if(  ! jQuery('#cmbFunctLocComp').is(':disabled'))
               setFieldValue("cmbFunctLocComp",result.flHirerachy.company);
    	   else
       		   jQuery("#cmbFunctLocComp").combobox('setValue',result.flHirerachy.company);
       
       if( result.flHirerachy.location != null && result.flHirerachy.location != undefined  )
    	   if(!jQuery('#cmbFunctLocLocn').is(':disabled')) 
               setFieldValue("cmbFunctLocLocn",result.flHirerachy.location); 
    	   else
    		   jQuery("#cmbFunctLocLocn").combobox('setValue',result.flHirerachy.location);
       
       if( result.flHirerachy.sbu != null && result.flHirerachy.sbu != undefined ) 
    	   if(   !jQuery('#cmbFunctLocSBU').is(':disabled') )
               setFieldValue("cmbFunctLocSBU",result.flHirerachy.sbu);
    	   else 
    		   jQuery("#cmbFunctLocSBU").combobox('setValue',result.flHirerachy.sbu);
       
       if( result.flHirerachy.factory != null && result.flHirerachy.factory != undefined )
    	   if(   !jQuery('#cmbFunctLocFact').is(':disabled') )
               setFieldValue("cmbFunctLocFact",result.flHirerachy.factory); 
    	   else
    		   jQuery("#cmbFunctLocFact").combobox('setValue',result.flHirerachy.factory);
       
       if( result.flHirerachy.pbu != null && result.flHirerachy.pbu != undefined )
    	   if(!jQuery('#cmbFunctLocPBU').is(':disabled')  )
               setFieldValue("cmbFunctLocPBU",result.flHirerachy.pbu); 
           else
        	   jQuery("#cmbFunctLocPBU").combobox('setValue',result.flHirerachy.pbu);
       
       if( result.flHirerachy.section != null && result.flHirerachy.section != undefined ) 
    	   if ( !jQuery('#cmbFunctLocSect').is(':disabled')  )        
               setFieldValue("cmbFunctLocSect",result.flHirerachy.section); 
    	   else
    		   jQuery("#cmbFunctLocSect").combobox('setValue',result.flHirerachy.section);
       
       if( result.flHirerachy.cell != null && result.flHirerachy.cell != undefined  ) 
    	   if (  !jQuery('#cmbFunctLocCell').is(':disabled') ){

               setFieldValue("cmbFunctLocCell",result.flHirerachy.cell); //
    	   }
    	   else
    		   jQuery("#cmbFunctLocCell").combobox('setValue',result.flHirerachy.cell);
       
       //if(result.flHirerachy.costcenterid != null && result.flHirerachy.costcenterid != '' && result.flHirerachy.costcenterid != ' ')
       //        jQuery("#"+glbCmbCostCenter).combobox('setValue',result.flHirerachy.costcenterid);
               
       //else
}*/

 function functionalLocHierarchy_OnError(result){
	
}


function fillfactoryHierarchy(url,factId,cmbCompanyid, cmbLocnId){
	
	glbCmbCompId=cmbCompanyid;
	glbCmbLocnId = cmbLocnId;
	processAjaxCalls(url,'factId='+factId,'factoryHierarchy_OnSuccess','factoryHierarchy_OnError');
}
function factoryHierarchy_OnSuccess(result)
{
	
	jQuery("#"+glbCmbLocnId).combobox('setValue',result.factoryHierarchy.location);
	jQuery("#"+glbCmbCompId).combobox('setValue',result.factoryHierarchy.company);
	
}
function factoryHierarchy_OnError(result){
	
}
function filllocationHierarchy(url,locnId,cmbCompanyid){
	
	glbcmbFactCompanyid=cmbCompanyid;
	processAjaxCalls(url,'locnId='+locnId,'locationHierarchy_OnSuccess','locationHierarchy_OnError');
}
function locationHierarchy_OnSuccess(result)
{
	
	jQuery("#"+glbcmbFactCompanyid).combobox('setValue',result.locationHierarchy.company);
	
}
function locationHierarchy_OnError(result){
	
}
/**Added By Manikandan on 12.05.12**/
function fillDesignation(url,empId,id){
	
	glbCmbDesgId=id;
	
	processAjaxCalls(url,'empId='+empId ,'filldesg_OnSuccess','filldesg_OnError');
}
function filldesg_OnSuccess(result){
	
	
	
	jQuery("#"+glbCmbDesgId).combobox('setValue',result.desgnationID.desgId);
	//setFieldValue('cmbClisResponsibilitydesgid',result.id);
}
function filldesg_OnError(){

}

function fillSpoke(url,progkeyid,id){
	//alert("--"+id);
	glbCmbDesgId=id;
	
	processAjaxCalls(url,'progkeyid='+progkeyid ,'fillSpoke_OnSuccess','fillSpoke_OnError');
}
function fillSpoke_OnSuccess(result){
	
	
	jQuery("#"+glbCmbDesgId).combobox('setValue',result.spokekeyID.spokekey);
	//setFieldValue('cmbClisResponsibilitydesgid',result.id);
}
function fillSpoke_OnError(){

}
/**End**/


function getColHeaderHtml(headerName,colId)
{
	return '<th id="'+ colId  +'" class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 100px;">'+
	'<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span>'+
	'<div id="jqgh_'+ colId  +'" class="ui-jqgrid-sortable">'+
	 headerName +
	'<span class="s-ico" style="display:none">'+
	'<span class="ui-grid-ico-sort ui-icon-asc ui-state-disabled ui-icon ui-icon-triangle-1-n ui-sort-ltr" sort="asc"></span>'+
	'<span class="ui-grid-ico-sort ui-icon-desc ui-state-disabled ui-icon ui-icon-triangle-1-s ui-sort-ltr" sort="desc"></span>'+
	'</span>'+
	'</div>'+
	'</th>';
}

function getLeftColHeaderHtml(headerName,colId)
{
	return '<th id="'+ colId +'" class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 500px;" rowspan="4" aria-selected="true"> ' +
	'<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span> ' +
	'<div id="jqgh_'+ colId +'" class="ui-jqgrid-sortable"> ' +
	headerName +
	'<span class="s-ico" style="display: inline;"> '+
	'<span class="ui-grid-ico-sort ui-icon-asc ui-icon ui-icon-triangle-1-n ui-sort-ltr" sort="asc"></span> '+
	'<span class="ui-grid-ico-sort ui-icon-desc ui-state-disabled ui-icon ui-icon-triangle-1-s ui-sort-ltr" sort="desc"></span> '+
	'</span> '+
	'</div> '+
	'</th> ';
}
var processGridRequests = {};
function processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,ongridcompletecallback,selectRowFunction,filterNeed ){
	//
	var getDataUrl ="";
	var tableButton =true;
	var chartButton =true;
	var getColumnUrl = url.replace('input','getCol');
	getColumnUrl = getColumnUrl.replace('_modify','_getCol');
	var gridCombo = getFilterValue(filterString ,"mode") ;
	if( filterString != null &&  filterString !="" && "grid" != gridCombo ){
			getDataUrl = url.replace('input','getData');
			
			getDataUrl = getDataUrl.replace('_modify','_getData');
	}
	if("grid" == gridCombo  ){
		getColumnUrl = url+"&getCol=true";
		getDataUrl = getFilterValue(filterString ,"cmbUrl");
//		 alert("getDataUrl  "+getDataUrl);
		
	}
	 
	getColumnUrl = getColumnUrl.replace('view','getCol');
	if( filterString != null &&  filterString !="")		
		getDataUrl = getDataUrl.replace('view','getData');
	
	getColumnUrl += (getColumnUrl.indexOf('?') < 0 ?  filterString.indexOf('?') < 0 ? '?':'':'')+ filterString ;
	getDataUrl += (getDataUrl.indexOf('?') < 0 ?  filterString.indexOf('?') < 0 ? '?':'':'')+ filterString ;
	
	
	if( ! jQuery("#preLoadContent").hasClass("tpm-loading"))
		jQuery("#preLoadContent").addClass("tpm-loading");
	
	jQuery("#preLoadContent").css("display","block"); 
 	jQuery("#LoadContent").css("display", "none");

 	//show_winMask(1);
 	showLoadingMsg(1);
	//alert("processGridRequests :"+processGridRequests[ tableId ] );
 	if( processGridRequests[ tableId ] )
 		processGridRequests[ tableId ].abort();
 	
	var processTbl = jQuery.ajax({
		       type: "POST",
		       xhr:window.ActiveXObject?
		    	function(){
		    	   try{
		    		   return new window.ActiveXObject("Microsoft.XMLHTTP");
		    		   }
		    	   catch(e){}
		    	   }:function(){return new window.XMLHttpRequest();},
		       url: getColumnUrl,
		       dataType: "json",
		       data:filterString,
		       success: function(result)
		       {
		    	   

		    	   if( result !=null && result.sExpires){
		    		   loadFormSessionTimeOut(result);
		    	   }
		    	   if( processGridRequests[ tableId ] )
				    	  delete processGridRequests[ tableId ];
		    	   
		    	    if( result == null || result.noData != null && result.noData == true )
		    	    {
		    	    	showLoadingMsg(0);
		    	    	if( result != null && result.noDataMsg != null  )
		    	    		msgBox(result.noDataMsg);
		    	    	
		    	    	jQuery("#"+tableId).clearGridData();
		    	    	return;
		    	    }
		    	    var rowList = "";
		    	    var scroll = 1;
		    	    var scrollrow = true;
		    	    if( result.paginate){
		    	    	rowList = [10,50,100];
		    	    	scrollrow = false;
		    	    	scroll = null;
		    	    }
					
					//rowList = [10,50,100];
		    	    showLoadingMsg(1);
		            var colN = result.rowHeaders != null ? result.rowHeaders :result.colNames;
		            var filterEnable = result.enableFilter != null ? result.enableFilter : false;
		            filterNeed =  filterEnable == true || filterNeed == true ? true:false;
		            var colM = result.colModel;
		            tableButton = ( result.tableButton != null ? result.tableButton==true || result.tableButton=="true" ?true:false:true);
		            chartButton = ( result.chartButton != null ? result.chartButton==true?true:false:false);

		            var loadOnce =( result.loadOnce != null ? result.loadOnce==true?true:false:false);
		            var cellEdit =( result.cellEdit != null ? result.cellEdit==true?true:false:false);
		            var cellSubmit="";
		            if( cellEdit == true)
		            	cellSubmit	= ( result.cellSubmitLocal != null ? result.cellSubmitLocal==false?'remote':'clientArray':'clientArray');
		           
		            var rowNums = result.pageRowCount != null ? result.pageRowCount : 100; 
		            
		            var tableHeight;
		            var tableWidth= result.tableWidth;
		            var tableHeightTmp= result.tableHeight;
					//alert("tableWidth:"+tableWidth);
					//alert("height:"+tableHeightTmp);
		            /*Added BY Manikandan for getting width in cross browsers*/
		            var winInnerHeight =window.innerHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
		            
		            if( tableHeightTmp != null && tableHeightTmp != undefined && tableHeightTmp.length > 0 && tableHeightTmp.indexOf('%%') > 0 )
		            {	
		            	tableHeightTmp  = (winInnerHeight - 250 ) * parseFloat( tableHeightTmp.replace("%%") ) /100;
		            	tableHeight = parseInt(tableHeightTmp,10);
		            }
		            else
		            	tableHeight = tableHeightTmp;
		            
		            tableHeight = parseInt(tableHeight,10) <=0 ? undefined: tableHeight;
		            
		           var tableWidthTmp= result.tableWidth;
		           /*Added BY Manikandan for getting width in cross browsers*/ 
		           var winInnerWidth = window.innerWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		           
		            if( tableWidthTmp != null && tableWidthTmp != undefined && tableWidthTmp.length > 0 && tableWidthTmp.indexOf('%%') > 0 )
		            {	
		                tableWidthTmp  = (winInnerWidth - 250 ) * parseFloat( tableWidthTmp.replace("%%") ) /100;
		            	 
		            	tableWidth = parseInt(tableWidthTmp,10);
		            	 
		            }
		            else
		            	tableWidth = tableWidthTmp;
	
					//alert("tableWidth:"+tableWidth);
					
					//alert("tableWidth:" + tableWidth);
		            
		            var colHeaders = colN[0]; 
		            
		            if( result.dataURL != null && result.dataURL != "")
		            	getDataUrl = result.dataURL;
		           
		            var groupByField = result.groupByField;		            
					groupBy = (result.groupBy == "true" ? true: result.groupBy == true? true:  false);
					var rownumbers = (groupBy == true ? false : (result.rowNumbers == true?true:false));
					groupSummary = (groupBy == true ? result.groupSummary == "true" ?true : result.groupSummary == true ? true :false : false);
					
					var subGrid = (result.subGrid == true || result.subGrid == "true") ? true : false;
					
					//if( result.rowSpanCol != null && result.rowSpanCol != "")
						// rowSpanCol = result.rowSpanCol;
					//var rowSpan = (result.rowSpan == "true" ? true: result.rowSpan == true? true:  false);
		            if( result.tableCaption != null && result.tableCaption != "")
		            	tableCaption = result.tableCaption;
		            
		            //if( tableCaption != null && tableCaption != 'undefined'  )
		            	//setFormMainHeader(tableCaption);
		            for(var i = 0; i < colM.length;i++ ){
		            	
			           	if( colM[i].formatter != null && colM[i].formatter.length > 0){
		             		var functObj = eval('(' + colM[i].formatter + ')');
		             		colM[i]["formatter"] = functObj ;
		             	}
		             	if(colM[i].cellattr != null && colM[i].cellattr.length > 0){
		             		var cellattrObj = eval('( function (rowId, tv, rawObject, cm, rdata){ return ' + colM[i].cellattr + ';})');
		             		colM[i]["cellattr"] = cellattrObj ;
		             	}
/*		             	if( i < 4 ){
		             		colM[i]["frozen"] = true;
		             	}
*/		             	
		             	//colM[i]["searchoptions"] = { sopt:['eq'] };
		            }
		            /* For Grid Edit*/
		            var griEdit = false;
		            if( result != null && result.gridEdit != null && result.gridEdit != undefined)
		            	griEdit = (result.gridEdit == true ? true:false);
		            var multiselect = (griEdit == true ? true: (result.multiSelect == true?true:false));
		            
		            try{ 
		            jQuery("#"+tableId).GridUnload();
		            }catch(Exception){}
		           	//alert('ss');
		           	//alert(result.data);
					//alert("Scroll:"+scroll);
					//alert("scrollrow:"+scrollrow);
					//alert("getDataUrl:"+getDataUrl);
					//var hasUrl = (getDataUrl && jQuery.trim(getDataUrl).length > 1); 
					//alert("HasURl:"+hasUrl);
		            mygrid =  jQuery("#"+tableId);
					
					jQuery("#" + tableId).jqGrid('setGridWidth', tableWidth || (winInnerWidth - 200));
					jQuery("#" + tableId).jqGrid('setGridHeight', tableHeight || (winInnerHeight - 250));
															
		            jQuery("#"+tableId).jqGrid({
				             		url: getDataUrl,
		            				//data:result.data,
								    datatype: 'json',
								    colNames: colHeaders,
								    colHeaders:colN,
									colModel: colM,
									rowNum:rowNums,
									gridEdit:griEdit,
									rowList:rowList,
									//rowSpan : rowSpan,
								//	rowSpanCol : rowSpanCol,
									rownumbers: rownumbers,
									shrinkToFit:false,
									pager: pagerId, 
									sortname: '1',
									viewrecords: true,
									sortorder: "asc",
									scroll:scroll,
									scrollrows:scrollrow,
									loadonce:loadOnce,	
									//cellEdit: cellEdit,
									cellEdit:true,
									//footerrow : true, 
								//	userDataOnFooter : true,
									cellsubmit: cellSubmit,
									//caption:tableCaption,
									toolbar: (tableButton == true ? [true,"top"]:''),
									//toppager: ! tableButton ,
									multiselect:multiselect,
									width: tableWidth != undefined ? tableWidth : winInnerWidth-200,
									height: tableHeight != undefined ? tableHeight : winInnerHeight - 250,
									subGrid: subGrid,
									grouping: groupBy,
									sortable: false,
									groupingView : { 
										
										groupField : [groupByField],
										groupColumnShow : [false],
										groupText : ['<b>{0}</b>'],
										groupCollapse : false,
										groupOrder: ['asc'],
										groupSummary : [groupSummary],
										groupDataSorted : true 
									},
									ondblClickRow: function(id){
										
										if( doubleClickFunction != null && doubleClickFunction.length > 0 ){
											var args = [ id ];
											dynamicFunctionCall(doubleClickFunction, args);
											//eval(doubleClickFunction+'(id)');
										}
											
									},
									/*serializeGridData: function (postData) {
									    var $grid = jQuery(this);
									    var rowIds = $grid.getDataIDs();
									    var rowNum = $grid.getGridParam("rowNum");
										var page = jQuery(this).getGridParam("page");
										console.log("Overriding jqGrid page before :", postData);
										console.log("jqGrid last row →", rowIds[rowIds.length - 1]);
										console.log("jqGrid scroll :", scroll);
									    if (rowIds.length > 0) {
											if(scroll == 1){
												var newPage = Math.floor(rowIds.length / rowNum) + 1  ;
												var newRows = (page * rowNum) -  rowIds.length;
												postData.page = newPage;
												postData.tempRows = newRows;
												if(newRows != rowNum){
													postData.tempPage = newPage -1;
												}
												console.log("Overriding jqGrid page →", newPage);
												console.log("Overriding jqGrid rows →", newRows);
												$grid.setGridParam({
												            rowNum: newRows
												        });
												$grid.setGridParam({
														page:newPage
												});
											}else{
												postData.tempRows = rowNum;
												//var newPage = Math.floor(rowIds[rowIds.length - 1] / rowNum) + 1;
											}
									        //var newPage = Math.floor(rowIds.length / rowNum) + 1;
											//var newPage = Math.floor(rowIds[rowIds.length - 1] / rowNum) + 1;
									       
									    }else{
											postData.tempRows = rowNum;
										}
										console.log("Overriding jqGrid page After :", postData);
									    return postData;
									},*/
									serializeGridData: function (postData) {
																		    var $grid = jQuery(this);
																		    var rowIds = $grid.getDataIDs();
																		    var rowNum = $grid.getGridParam("rowNum");
																			var page = jQuery(this).getGridParam("page");
																			//console.log("Overriding jqGrid page before :", postData);
																			//console.log("jqGrid last row →", rowIds[rowIds.length - 1]);
																			//console.log("jqGrid scroll :", scroll);
																		    if (rowIds.length > 0) {
																				if(scroll == 1){
																					var newPage = Math.floor(rowIds.length / rowNum) + 1  ;
																					//var newRows = (page * rowNum) -  rowIds.length;
																					postData.page = newPage;
																					/*postData.tempRows = newRows;
																					if(newRows != rowNum){
																						postData.tempPage = newPage -1;
																					}*/
																					//console.log("Overriding jqGrid page →", newPage);
																					//console.log("Overriding jqGrid rows →", newRows);
																					/*$grid.setGridParam({
																					            rowNum: newRows
																					        });*/
																					/*$grid.setGridParam({
																							page:newPage
																					});*/
																				}else{
																					//postData.tempRows = rowNum;
																					//var newPage = Math.floor(rowIds[rowIds.length - 1] / rowNum) + 1;
																				}
																		        //var newPage = Math.floor(rowIds.length / rowNum) + 1;
																				//var newPage = Math.floor(rowIds[rowIds.length - 1] / rowNum) + 1;
																		       
																		    }else{
																				//postData.tempRows = rowNum;
																			}
																			//console.log("Overriding jqGrid page After :", postData);
																		    return postData;
																		},
									beforeRequest: function(data)  {
										//alert("hh "+Object.keys(jQuery(this)));
										//console.log("About to request page:", jQuery(this).getGridParam("page"));
										//var $grid = jQuery(this);
										//console.log("Page:", jQuery(this).getGridParam("page"));
										   //console.log("Records:", jQuery(this).getGridParam("records"));
										   //console.log("LastPage:", jQuery(this).getGridParam("lastpage"));
										/*var $grid = jQuery(this);

										   // Log which page is about to load
										   console.log("About to request page:", $grid.getGridParam("page"));

										   // Get all current row IDs
										   var rowIds = $grid.getDataIDs();

										   if (rowIds.length > 0) {
										       var lastRowId = rowIds[rowIds.length - 1];
										       console.log("Last row ID before request:", lastRowId);
										   } else {
										       console.log("No rows currently loaded.");
										   }*/
										   
										  /* var $grid = jQuery(this);

										       // how many rows per page
										       var rowsPerPage = $grid.getGridParam("rowNum");

										       // current loaded rows
										       var rowIds = $grid.getDataIDs();
										       var totalLoaded = rowIds.length;

										       if (totalLoaded > 0) {
										           var lastRowId = rowIds[rowIds.length - 1];

										           // Calculate next page number
										           var nextPage = Math.floor(totalLoaded / rowsPerPage) + 1;

										           // Set jqGrid's page param manually
										          jQuery(this).setGridParam({ page: nextPage });

										           console.log("Total loaded:", totalLoaded);
										           console.log("Rows per page:", rowsPerPage);
										           console.log("Last row ID:", lastRowId);
										           console.log("Setting next page to:", nextPage);
										       } else {
										           console.log("No rows yet — setting page = 1");
										           $grid.setGridParam({ page: 1 });
										       }
											   
											   console.log("After to request page:", jQuery(this).getGridParam("page"));*/
										   
										if(griEdit==true )
											jQuery("#jqgh_"+tableId +"_cb").html("Edit");
										else
											jQuery("#jqgh_"+tableId +"_cb").html("");
										  //alert(1);
										 var cm = jQuery("#"+this.id).jqGrid("getGridParam", "colModel");
										  var colNo ="" ;
										  if( result.formatterIndex  != null && result.formatterIndex  != "") {
											  for(var k=0;k<result.formatterIndex.length;k++)
											  {
												  colNo =  result.formatterIndex ; //alert(colNo +' -- '+colNo[k]);
												  colNo[k] = colNo[k].toString(); 
												  var colData = colNo[k].split("#");
												  var colName = colData[0];
												  var colNum = colData[1];
												  
											  for(var j =0 ;j<cm.length;j++){												   
													 if(colNum == j){  
														  colHeader = jQuery("#jqgh_" + jQuery.jgrid.jqID(this.id + "_" +cm[j].name));
														  colHeader.attr('formatterType', colName);//alert(2);
												 	}
												  }
											  }
										  }

									   
 											
									},
									gridComplete:function() {
										
										/*jQuery(this).setGridParam({
																						            rowNum: rowNums
																						        });*/
										
										/*if( filterNeed  ){
												  jQuery("#"+tableId).jqGrid('filterToolbar',{stringResult: true,clearSearch: false,searchOnEnter : true});
										}*/
																
										/*jQuery("#" + tableId).jqGrid('setGridWidth', tableWidth || (winInnerWidth - 200));
										jQuery("#" + tableId).jqGrid('setGridHeight', tableHeight || (winInnerHeight - 250));*/
										//jQuery('#gs_').remove();
										if(griEdit==true )
											jQuery("#jqgh_"+tableId +"_cb").html("Edit");
								//		jQuery("#"+tableId).jqGrid.addJSONData(eval("("+result.data+")"));
										//show_winMask(0);
										showLoadingMsg(0);
									/*	if( onloadcompletecallback != null && onloadcompletecallback.length >0  )
										{																			
											eval(onloadcompletecallback+'( )');
										}
									*/	
										var args = [ tableId ];
										dynamicFunctionCall(ongridcompletecallback, args);
										 
									},
									loadComplete:function(data){
										/*jQuery(this).setGridParam({
																						            rowNum: rowNums
																						        });*/
										
										jQuery("#" + tableId).jqGrid('setGridWidth', tableWidth || (winInnerWidth - 200));
										jQuery("#" + tableId).jqGrid('setGridHeight', tableHeight || (winInnerHeight - 250));
										//jQuery("#" + tableId).jqGrid("setFrozenColumns");
										if( tableHeaderSpanCallback!= null && tableHeaderSpanCallback.length > 0 )
							            {	
						            		var headers = mygrid[0].grid.headers;
						            		var argstable = [ colM,headers,colN ];
						            		dynamicFunctionCall(tableHeaderSpanCallback, argstable);
							            }
										
										if(griEdit==true )
											jQuery("#jqgh_"+tableId +"_cb").html("Edit");
									//	jQuery("#"+tableId).jqGrid.addJSONData(eval("("+result.data+")"));
										if( data != null && data.sExpires){
								    		   loadFormSessionTimeOut(data);
								    	   }
 
										
										if("true" == result.rowHeight || result.rowHeight == true){	
											var grid = jQuery("#"+tableId);
										        var ids = grid.getDataIDs();
										        for (var i = 0; i < ids.length; i++) {
										            grid.setRowData ( ids[i], false, {height: 25+i*2} );
										        }
										}
										//hideShowBack(true); // temp										
										//show_winMask(0);
										showLoadingMsg(0);
										 
										
										var args = [ data ];
										dynamicFunctionCall(ongridcompletecallback+'_afterLoad', args);
									},
									loadError:function(xhr,status,error){
										jQuery("#" + tableId).jqGrid('setGridWidth', tableWidth || (winInnerWidth - 200));
									    jQuery("#" + tableId).jqGrid('setGridHeight', tableHeight || (winInnerHeight - 250));
										//show_winMask(0);
										showLoadingMsg(0);
									},
									onSelectRow: function(id){    //Added by Siddharth.A
										
										if(jQuery('#jqg_'+ tableId +'_'+id).is(':checked')){
											try{
											makeRowEditable(tableId,id);
											}catch(Exc){}
											
										}
										else{
											restoreEdit(tableId,id);
										}
										
										if( tableId != null && tableId.length > 0 ){
												var args1 = [ id ];
												dynamicFunctionCall(tableId+'_selectRow', args1);
												 
										}
									 }
									,
									onSelectAll: function(rowIdxArray, status)/**Added by manikandan**/
								    { 
										var args = [ rowIdxArray, status ];
										dynamicFunctionCall(tableId+'_selectAll', args); 
									}
									/* columnChooser: function (perm) {alert(1);
										      if (perm) {
										          // "OK" button are clicked
										          this.jqGrid("remapColumns", perm, true);
										          // the grid width is probably changed co we can get new width
										          // and adjust the width of other elements on the page
										          //var gwdth = this.jqGrid("getGridParam","width");
										          //this.jqGrid("setGridWidth",gwdth);
										      } else {
										          // we can do some action in case of "Cancel" button clicked
										      }
										   }
									
									*/
								/*	 beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){//Added by Siddharth.A
											
											// if( onbeforeSubmitCell != null && onbeforeSubmitCell.length >0  )
											//	{	
																												
													//if( typeof eval('(' + beforeSubmitCellFunction +')') == 'function')
														eval('(' + beforeSubmitCellFunction +'(rowid, cellname, value, iRow, iCol))');
									 }	*/				
								
									
							});
		            //alert(rowSpan);
		            
		            //setTimeout(function() {if(rowSpan)
						//jQuery('#'+tableId).applyRowSpan();},1200);	
		            setGridBackgroundColor(tableId);
					setTimeout(function () {
											    var $g = jQuery("#"+tableId);
											    $g.closest(".ui-jqgrid")
											      .find(".ui-jqgrid-htable")
											      .css("height", "22px");

											    $g.jqGrid(
											        "setGridWidth",
											        $g.closest(".ui-jqgrid").parent().width(),
											        false
											    );
											}, 0);
					//jQuery("#" + tableId).jqGrid('setGridWidth', tableWidth || (winInnerWidth - 200));
					//jQuery("#" + tableId).jqGrid('setGridHeight', tableHeight || (winInnerHeight - 250));
		            //jQuery("#"+tableId).jqGrid('setFrozenColumns');
					//jQuery("#"+tableId).jqGrid("setFrozenColumns");
		           // jQuery("#gbox_" + tableId).css("left","-2%");
		            //madhan
		               if( filterNeed  ){
		            	jQuery("#"+tableId).jqGrid('filterToolbar',{stringResult: true,clearSearch: false,searchOnEnter : true});
		                }
						
					
						
						//madhan
						
		            
		                if( tableButton == true){
		            	/* jQuery("#"+tableId).jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
				            jQuery("#"+tableId).jqGrid('filterToolbar', {stringResult: true, searchOnEnter: true, defaultSearch : "cn",
				            	beforeSearch: function() {
				              
				                var postData = jQuery("#"+tableId).jqGrid('getGridParam','postData');
				                
				          }});	
		            	*/
		            	var tableString="<div id='left'><table  style='float:left;table-layout:auto;width:100%;' cellspacing='0' cellpadding='0' border='0' >";
			            tableString+="<tbody> <tr><td id='toolbar' >";
						tableString+="<div style='padding-left:2px;padding-top:0px;'>";
						/*tableString+="<div class='grid_icon' id='fil'title='Filter' >  </div>";*/						
						tableString+="<div id='loadGridFilter'><div id='gridFilterHdr' style='border-bottom:dotted 1px black;'>Filter</div><div id='gridFilter'></div></div>";
						tableString+="<div class='grid_icon' id='ref'  title='Refresh'></div  >";

//						tableString+="<div class='grid_icon' id='srch'>SEARCH</div>";
						tableString+="<div class='grid_icon' id='clr'  title='Clear'></div>";
						tableString+="<div class='grid_icon' id='del' style='width:0;' ></div  >";
						
						tableString+="<div class='grid_icon' id='grph'  title='Graph'></div>";						
						tableString+="<div class='grid_icon exptxl cls_" + tableId  + "' id='exptxl'  title='Export to Excel'></div>";
						tableString+="<div class='loadExpToExcel' id='loadExportToExcel_"+tableId+"'><div class='loadExc' id='loadExcel_"+tableId+"'></div></div>";
						
						tableString+="<div class='grid_icon' id='btnJqGridBack'  title='Back'></div>";
						
						tableString+="<div class='grid_icon' id='exptpdf'></div>";
						tableString+="<div class='grid_icon' id='exptppt'></div>";
						tableString+="<div class='grid_icon print_grid' style='display:block' id='printGrid_"+tableId +"' title='Print'></div  >";			
												
						tableString+="</td></tr></tbody></table></div>";
						  
					
						jQuery("#t_"+tableId).append(tableString); 
						jQuery("#t_"+tableId).css('height','35px');
						//jQuery("exptxl_"+tableId).css('background-image','url("../images/tool_icons/xlsx.png")');
						//jQuery("#t_"+tableId).css("background-color","#548DE9");
					jQuery("#t_"+tableId).css("background-color","#B5B5B5");
						//jQuery("#t_"+tableId).css("background-color","#6666cc");
				    	if(chartButton == true){
		            		jQuery('#grph').css('display','block');
		            	}
			    
		            }
  
		            	/*commented for ie purpose..
		            	 * jQuery("#"+tableId).jqGrid('navGrid','#t_'+tableId,{edit:false,add:false,del:false})
		            .navButtonAdd('#t_'+tableId,{
	                     caption:"Export to Excel", 
	                     buttonicon:"ui-icon-save", 
	                     onClickButton: function(){
//	                    	 jQuery("#"+tableId).excelExport();
	                    	  
	                    	 exportExcel(tableId);
	                     }, 
	                     position:"last"
	                 });
*/
					/*for Refresh grid*/
						jQuery('#ref').click(function(){
							//alert('Refresh');
							jQuery("#"+tableId).trigger("reloadGrid");
							
						});
					/*end*/
					
					/*for clear grid*/
						jQuery('#clr').click(function(){
							//alert('Clear');
							jQuery("#"+tableId).clearGridData();
							
						});
						
						jQuery('#printGrid_'+tableId).click(function(){
							PrintGrid(tableId);
							
						});
					/*end*/
					/*	jQuery("#"+tableId).jqGrid('navGrid',"#"+pagerId,
								{add:false,edit:false,del:false,search:false,refresh:false}); 
						jQuery("#"+tableId).jqGrid('navButtonAdd',"#"+pagerId,{
							caption: "Columns",
							title: "Reorder Columns",
							col : {
								caption: "Show/Hide Columns",
								bSubmit: "Submit",
								bCancel: "Cancel"
							},
							onClickButton : function (){
								//showColumnList(tableId);
								  //jQuery("#"+tableId).jqGrid('columnChooser'); 
								   return false; 
								}
						});
					*/	
		         /*jQuery("#"+tableId).jqGrid('navGrid','#'+pagerId,{edit:false,add:false,del:false})
		            .navButtonAdd('#'+pagerId,{
	                     caption:"Export to Excel", 
	                     buttonicon:"ui-icon-save", 
	                     onClickButton: function(){
//	                    	 jQuery("#"+tableId).excelExport();
	                    	 
	                    	 exportExcel(tableId);
	                     }, 
	                     position:"last"
	                 });
		          */  
		            jQuery("#"+tableId).jqGrid('bindKeys',{ scrollingRows:true } );
		            
		            jQuery('.cls_'+tableId).bind("click",function (event){		            	
		            	 
		            	event.stopPropagation();
		            	jQuery("#loadExportToExcel_"+tableId).slideToggle(200);
		            	
		            	
		            	var loadExcelHtml = "<div style='padding-bottom:0px;margin-top:-10px;'><a href='#' id='" + tableId +"sendToExcel3' name='sendToExcel3'> Send To xls</a></div>";
		            		//loadExcelHtml += "<br/>";		            		
		            		loadExcelHtml += "<div><a href='#' id='" + tableId +"sendToExcel7' name='sendToExcel7' style='padding-top:5px;'> Send To xlsx</a></div>";
		            		
		            	jQuery("#loadExcel_"+tableId).html(loadExcelHtml);
		            	jQuery( '#'+tableId +'sendToExcel3').click(function (){
		            		
		            		exportExcel(tableId,"3");
		            		
		            	});
		            	jQuery( '#'+tableId +'sendToExcel7').click(function (){
		            		exportExcel(tableId,"7");
		            	});
		            	
		            	//exportExcel(tableId);
		            });
		            jQuery('#fil').click(function (){
		            //	PrintDivData(tableId);
		            	//PrintGrid(tableId);
		            	//alert('Filter');
		            	alert("This functionality is not enabled");
		            	//jQuery("#"+tableId).jqGrid('columnChooser');
		       //madhan-12-11-2025     	//return false;
		            	jQuery("#loadGridFilter").slideToggle(200);
		            	//jQuery("#loadGridFilter").show();
		            	var cm = jQuery("#"+tableId).jqGrid("getGridParam", "colNames");
		            	//var colNames ='?q=2';
		            	var colName = null;
		            	var colNameCombo = "<select id='columnFilter' name='columnFilter'>";
		            	for(var i=0;i<cm.length;i++)
		            	{
		            		colName = jQuery.trim(ReplaceCharInStr(cm[i],' ','' ));		            		
		            		colNameCombo += "<option value="+tableId+"_"+colName+">";
		            		colNameCombo += colName +"</option>";
		            		//colNames += '&col'+jQuery.trim(ReplaceCharInStr(cm[i],' ','' ))+'='+jQuery.trim(ReplaceCharInStr(cm[i],' ','_' ));
		            	}
		            	colNameCombo += "</select>";
		            	var ftrCondnCombo =  "<select id='filterCondn' name='filterCondn'>";
		            		ftrCondnCombo += "<option value='E'>Equals</Option>";
		            		ftrCondnCombo += "<option value='NE'>Not Equals</Option>";
		            		ftrCondnCombo += "<option value='LT'>Less Than</Option>";
		            		ftrCondnCombo += "<option value='GT'>Greater Than</Option>";
		            		ftrCondnCombo += "<option value='BW'>Begins With</Option>";
		            		ftrCondnCombo += "<option value='EW'>Ends With</Option>";
		            		ftrCondnCombo += "</select>";
		            		
		            	var filterValue = "<input type='text' class='easyui-text' id='ftrVal' name='ftrVal'/>";
		            	var gridFilterHtml = "";
		            	jQuery("#gridFilter").html(colNameCombo);
		            	
		            	//LoadForm("gridFilter","preloadgridFilter",'filter_grid.gnms'+colNames,"dispErr","loadGridFilterSuccess");
		            });
		           var grdBackId = tableId+"_onProcessGridBack";
		            jQuery('#btnJqGridBack').click(function (){
	            		
		            	 try{ 
		            		 	//window[grdBackId].apply(this,null);
		            		 if (isIE()) {
		            				if( typeof eval('('+grdBackId +')') == 'function')
		            				{
		            				  eval('( '+grdBackId +'())');
		            				}
		            		 }else{
		            			 window[grdBackId].apply(this,null);
		            		 }
		            	 }catch(Exception ){
		            			
		            	 }
		            });	
            		//var headers = mygrid[0].grid.headers;
		         /*   alert(" ColM " +colM);
		            alert(" ColN " +colN);
		            alert(" rownumbers " +rownumbers);*/
            		columnHeaderSpan(colM,colN,rownumbers,multiselect);
	            	
		        },
		        error: function(status){
		        	
		        	show_winMask(0);
		        	showLoadingMsg(0);
		        	if( status != null && status.sExpires){
			    		   loadFormSessionTimeOut(status);
			    	   }
		        	if( status.statusText != "abort"){
						   if( processGridRequests[ tableId ] )
				    		  delete processGridRequests[ tableId ];
		        	}	   
		        	
			  	}
		});
	  
	  jQuery("#preLoadContent").hide(); 
	  jQuery("#LoadContent").show();
	  jQuery("div .panel").click(function(){
		  if( jQuery('div[id^=loadExportToExcel_]').is(":visible")) 
			  jQuery("div[id^=loadExportToExcel_]").hide(200); 
	  });
	  
	  if( tableId != null && tableId != undefined && tableId.length > 0 && tableId != "")
		  processGridRequests[ tableId ] = processTbl;
}

function showColumnList(tableId){
	 
	LoadPopUp("ColumnPopDivId","openColumn_input.commonFilter?q=2&tableId="+tableId,true,"45%","80%","20px","20px","ColumnSuccessCallBack","Column Chooser");
}

function ReplaceCharInStr(Source,stringToFind,stringToReplace){

	  var temp = Source;

	    var index = temp.indexOf(stringToFind);

	        while(index != -1){

	            temp = temp.replace(stringToFind,stringToReplace);

	            index = temp.indexOf(stringToFind);

	        }

	        return temp;

	}

	function loadGridFilterSuccess()
	{
		
	}
function cellObject(){
	this.rowspan=1;
	this.colspan=1;
	this.caption=" ";
	this.isNeed=false;
}

function columnHeaderSpan(colModel,colHeaders, isRowNumbers,isMultiSelect){
	
	var rowHeaders = [];
	var colSpan = 0;
	//var rowSpan= 1;
	if( colHeaders.length > 1){
		var rowIndx = 0;
		for( var i = 1 ; i< colHeaders.length;i++)
		{
			rowHeaders[ rowIndx ] = new Array();
			colSpan=1;
			var colIndex =0;
			var j = 0;

			for(  j = 0 ; j< colHeaders[i].length;j++){
				
				if( ! colModel[j].hidden )
				{
					var isNeed = false;
					if( j < colHeaders[i].length &&  colHeaders[i][j ] != colHeaders[i][j+1] )
					{
						/*var celllObject = new cellObject();
						celllObject.colspan =colSpan;
						celllObject.rowspan=1;
						celllObject.caption = colHeaders[i][j ]; 
						colSpan=1;
						rowHeaders[rowIndx][colIndex++ ] = celllObject;*/
						isNeed = true;
						
						
					}
					else{
						++colSpan;
					}
					var celllObject = new cellObject();
					celllObject.colspan =colSpan;
					
					celllObject.rowspan=1;
					celllObject.caption = colHeaders[i][j ]; 
					celllObject.isNeed = isNeed;
					rowHeaders[rowIndx][colIndex++ ] = celllObject;
					if( isNeed) colSpan=1;
				}
				
			}
		/*	if( colHeaders[i][j ] == colHeaders[i][j-1] && i == 1)
				colSpan--;
		*/		
			/*var celllObject = new cellObject();
			celllObject.colspan =colSpan;
			celllObject.caption = colHeaders[i][j-1 ]; 
			rowHeaders[rowIndx][colIndex++ ] = celllObject;
			*/rowIndx++;
			
		}
		for(var i=0;i<rowHeaders.length-1;i++)
		{
			for(var j =0;j<rowHeaders[i].length;j++)
			{
				for(var k=i+1;k<rowHeaders.length ;k++){
					
					if( rowHeaders[k][j] != undefined && rowHeaders[k][j] != null){
						if( rowHeaders[i][j].rowspan > 0 && rowHeaders[k][j].caption == rowHeaders[i][j].caption && 
								rowHeaders[k][j].colspan == rowHeaders[i][j].colspan )
						{
							rowHeaders[i][j].rowspan++;
							rowHeaders[k][j].rowspan=0;
						}
						else
							break;
					}
				}
			}	
		}
		for( var i = 0;i<rowHeaders.length;i++)
		{
			var tr = '<tr class="jqgridheaderrow'+(i+1)  +'" height="100%" > '; // style="background-color:rgb('+(200+i*10) +','+ (200+i*10) +','+ (200+i*10) +')" >';
			if( isRowNumbers  && rowHeaders.length >= 1 )
			{
				tr += '<th style="width:25px" >  </th>';
				if( isMultiSelect)
					tr += '<th>  </th>';
			}	
	
			for( var j = 0;j<rowHeaders[i].length;j++)
			{
				var cellobj = rowHeaders[i][j];
				

				if( cellobj.rowspan > 0 && cellobj.isNeed )
					tr += '<th  id="CH'+i + '-'+ j  +'" class="ui-state-default ui-th-ltr "  colspan="'+ cellobj.colspan +'" rowspan="'+ cellobj.rowspan +'" role="columnheader"' + (cellobj.colspan == 1 ? ' style="width:'+ colModel[j].width +'px;'+ (i == 0? "border-top:none;":"") +'" ':'' )  +'>'+cellobj.caption +'</th>';
/*
				if( cellobj.rowspan > 0 && cellobj.isNeed )
					tr += '<th  id="CH'+i + '-'+ j  +'" class="ui-state-default ui-th-ltr"  colspan="'+ cellobj.colspan +'" rowspan="'+ cellobj.rowspan +'" role="columnheader">'+cellobj.caption +'</th>';
*/
			}
			tr += '</tr>';
			mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
			//mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead tr:first-child").css("border","none");
			//mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead tr:first-child th").css("border","none");
		}	
	}	
}		
function getMonthNo(mon)
{	
	var monthArray = [ "00","00","01","02","03","04","05","06","07","08","09","10","11"];	
	return monthArray[mon];
}
function getIndex(month)
{
	var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	for(var i = 0;i<monthArray.length; i++)
		if( (monthArray[i].toUpperCase()).indexOf(month.toUpperCase()) == 0)
			return i;
	return -1;
}
function formatDateBox(id,format)
{	
	 var serverTime = srvTime();
	 var dateTime = new Date(serverTime);
	 var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	 jQuery('#'+id).datebox({  
		 formatter: function(date){
			 return (date.getDate()+'-'+ monthArray[date.getMonth()] +'-'+date.getFullYear()); },			
		 parser:function(s){
			//alert(s);
			 if(jQuery.trim(s)==""){
				 //return new Date();
				 return dateTime; 
			 }
			
			 var dt=s.split(" ");
			 var d=dt[0];
			 var td = d.split('-');
			//alert(td[0]);
			//alert(td[1]);
			//alert(td[2]);
			//alert(s.getDate());
			 var day = dateTime.getDate(),month = dateTime.getMonth(),year = dateTime.getFullYear();			 
			 if( td[0] != null && jQuery.trim(td[0]).length >0  )
			 {
				var regMon = /^[0-1]{1}?[0-9]{1}$/;
				td[1] = td[1].length == 1?'0'+td[1]:td[1];
				var tempMon = td[1].length;
				
				if(tempMon == 2)
				{
					td[1] = (td[1] == '08'?'8':td[1] == '09'?'9':td[1]);					
					td[1] =getMonthNo(parseInt(td[1]));
				}
				
				var indx =  ''; 				
				
				if (regMon.test(td[1])) {											
						indx = td[1];
				}
				else
					indx = getIndex( td[1] );
				
				if(  indx >= 0 ){
					if(td[2])
						year = td[2];
					month = indx;	
				}
				//alert(td[0] + " - Length :"+td[0].length);
				//day = td[0].length == 1?'0'+td[0]:td[0]; //madhan
				//alert(day + " - Length ");
				day = td[0];
			 }	 	
			
			 return new Date(year,month,day);
		 },		
		onSelect:function(date){			
			var onSelectFunctionName = id +'_onSelect';
			//madhan
			//if( typeof eval('('+onSelectFunctionName +')') == 'function')
			if( typeof window[onSelectFunctionName] == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}	
		},
		onChange:function(date){			
			var onChangeFunctionName = id +'_onChange';
			var args = [ date ];
			dynamicFunctionCall(onChangeFunctionName, args);
		/*	if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}*/	
		}
	 });
}

function formatMonthBox(id,format) {

    var serverTime = srvTime();
    var dateTime = new Date(serverTime);

    var monthArray = ["Jan","Feb","Mar","Apr","May","Jun",
                      "Jul","Aug","Sep","Oct","Nov","Dec"];

    jQuery('#' + id).datebox({

        /* DISPLAY FORMAT */
        formatter: function (date) {
            return monthArray[date.getMonth()] + '-' + date.getFullYear();
        },

        /* PARSE USER INPUT */
        parser: function (s) {

            if (jQuery.trim(s) === "") {
                return new Date(dateTime.getFullYear(), dateTime.getMonth(), 1);
            }

            // Expected: Jan-2025
            var parts = s.split('-');
            if (parts.length !== 2) {
                return new Date(dateTime.getFullYear(), dateTime.getMonth(), 1);
            }

            var monthText = parts[0];
            var year = parseInt(parts[1], 10);

            var monthIndex = monthArray.indexOf(monthText);
            if (monthIndex < 0 || isNaN(year)) {
                return new Date(dateTime.getFullYear(), dateTime.getMonth(), 1);
            }

            return new Date(year, monthIndex, 1);
        },

        onSelect: function (date) {
            var fn = window[id + '_onSelect'];
            if (typeof fn === 'function') {
                fn(date);
            }
        },

        onChange: function (date) {
            dynamicFunctionCall(id + '_onChange', [date]);
        }
    });
}

/*function formatMonthBox(id,format)
{	
	 var serverTime = srvTime();
	 var dateTime = new Date(serverTime);
	 var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	 jQuery('#'+id).datebox({  
		 formatter: function(date){
			 return (monthArray[date.getMonth()] +'-'+date.getFullYear()); },			
		 parser:function(s){
			alert(s);
			 if(jQuery.trim(s)==""){
				 //return new Date();
				 return (monthArray[dateTime.getMonth()] +'-'+dateTime.getFullYear());; 
			 }
			
			 var dt=s.split(" ");
			 var d=dt[0];
			 var td = d.split('-');
			
			 var day = dateTime.getDate(),month = dateTime.getMonth(),year = dateTime.getFullYear();			 
			 if( td[0] != null && jQuery.trim(td[0]).length >0  )
			 {
				var regMon = /^[0-1]{1}?[0-9]{1}$/;
				td[1] = td[1].length == 1?'0'+td[1]:td[1];
				var tempMon = td[1].length;
				
				if(tempMon == 2)
				{
					td[1] = (td[1] == '08'?'8':td[1] == '09'?'9':td[1]);					
					td[1] =getMonthNo(parseInt(td[1]));
				}
				
				var indx =  ''; 				
				
				if (regMon.test(td[1])) {											
						indx = td[1];
				}
				else
					indx = getIndex( td[1] );
				
				if(  indx >= 0 ){
					if(td[2])
						year = td[2];
					month = indx;	
				}
				day = td[0];
			 }	 	
			
			 return new Date(year,month,day);
		 },		
		onSelect:function(date){			
			var onSelectFunctionName = id +'_onSelect';
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}	
		},
		onChange:function(date){			
			var onChangeFunctionName = id +'_onChange';
			var args = [ date ];
			dynamicFunctionCall(onChangeFunctionName, args);
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}	
		}
	 });
}*/

function formatDateBoxWithGrid(id,format)
{	
	 var serverTime = srvTime();
	 var dateTime = new Date(serverTime);
	 var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	 
	 jQuery('[id^='+id+']').datebox({  //	 jQuery('#'+id)
		 formatter: function(date){
			
			 return (date.getDate()+'-'+ monthArray[date.getMonth()] +'-'+date.getFullYear()); },			
		 parser:function(s){
			 if(jQuery.trim(s)==""){
				 //return new Date();
				 return dateTime; 
			 }
			
			 var dt=s.split(" ");
			 var d=dt[0];
			 var td = d.split('-');
			
			 var day = dateTime.getDate(),month = dateTime.getMonth(),year = dateTime.getFullYear();			 
			 if( td[0] != null && jQuery.trim(td[0]).length >0  )
			 {
				var regMon = /^[0-1]{1}?[0-9]{1}$/;
				td[1] = td[1].length == 1?'0'+td[1]:td[1];
				var tempMon = td[1].length;
				
				if(tempMon == 2)
				{
					td[1] = (td[1] == '08'?'8':td[1] == '09'?'9':td[1]);					
					td[1] =getMonthNo(parseInt(td[1]));
				}
				
				var indx =  ''; 				
				
				if (regMon.test(td[1])) {											
						indx = td[1];
				}
				else
					indx = getIndex( td[1] );
				
				if(  indx >= 0 ){
					if(td[2])
						year = td[2];
					month = indx;	
				}
				day = td[0];
			 }	 	
			
			 return new Date(year,month,day);
		 },		
		onSelect:function(date){			
			var onSelectFunctionName = id +'_onSelect';
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}	
		},
		onChange:function(date){			
			var onChangeFunctionName = id +'_onChange';
			var args = [ date ];
			dynamicFunctionCall(onChangeFunctionName, args);
		/*	if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}*/	
		}
	 });
}

function setComboDefaultValue(formName, id) {
	var data = jQuery('#'+id).combobox('getData');
	if (data.length ==1) {	
		var first = data[0];
		jQuery('#'+id).combobox('setValue',first.id);		
		var onSelectFunctionName = formName+id +'_onSelect';
		var args = [first];
		dynamicFunctionCall(onSelectFunctionName,args);				
	}
}

/*function fillComboBox(formName, id, url,searchContains,remote,multiple){
	url = removeValueFromUrl(url,"combokey");
	 var comboKey ="combokey="+getFieldValue(id) ;//added by manikandan

	 if(url.indexOf("?") < 0)
		 url=url+"?" + comboKey;
	 else
		 url=url+"&"+comboKey;


	if(remote == null || remote == undefined)
		remote = true;
	//if(comboKey.trim().length>0)
	//	remote = false;
	 
	jQuery('#'+id).combobox({//jQuery('[id^='+id+']')
//		mode:remote==true?'remote':'local',
		mode:remote==true?'remote':'local',
		url:url,
		dataType:'json',		
		valueField:'id',
		textField:'text',
		multiple:multiple,
		panelHeight:130,
		onLoadSuccess:function()
		{
			
			
			var onLoadSuccessName = formName+id +'_onLoadSuccess';
	
			try{
				if( typeof eval('('+onLoadSuccessName +')') == 'function')
				{
					
					eval('( '+ onLoadSuccessName +'())');
				}
			}catch(Exception){}
			var args = [];
			dynamicFunctionCall(onLoadSuccessName,args);
		},
		
		onSelect:function(record)
		{
			var onSelectFunctionName = formName+id +'_onSelect';
			
			try{
				if( typeof eval('('+onSelectFunctionName +')') == 'function')
				{
					
					eval('( '+ onSelectFunctionName +'(record))');
				}	
			}catch(Exception){}
			
			var args = [record];
			dynamicFunctionCall(onSelectFunctionName,args);
		},
		onUnselect:function()
		{
			var onUnSelectFunctionName = formName+id +'_onUnSelect';
			try{
				if( typeof eval(onUnSelectFunctionName) == 'function')
				{
					eval('( '+ onUnSelectFunctionName +'())');
				}
			}catch(Exception){}	
		},
		onClear: function(opts) {
			var onClearFunctionName = formName+id +'_onClear';
			try{
				if( typeof eval(onClearFunctionName) == 'function')
				{
					eval('( '+ onClearFunctionName +'())');
				}
				
			}catch(Exception){}	
		}
		
		
		,
		keyHandler: {
			up: function(){
				var opts = jQuery(this).combobox('options');
				opts.keyHandler.up.call(this);
			},
			down: function(){ 
				var opts = jQuery(this).combobox('options');
				opts.keyHandler.down.call(this);
			},
			enter: function(){
				var onKeyPress = formName+id +'_onEnterKeyPress';			
				var args = null;
				dynamicFunctionCall(onKeyPress,args);
				
			},
			query: function(q){}
		}
		
		
	});
	if(searchContains){
		jQuery("#"+id).combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');
				return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
			}
		});	
	}
}*/
window.EASYUI_COMBO = {
	    	    isAutoSelect: false,
	    	    isUserAction: false
	    	};
/*function fillComboBoxwithoutClear(formName, id, url, searchContains, remote, multiple){

    url = removeValueFromUrl(url,"combokey");
    var comboKey ="combokey="+getFieldValue(id);

    if(url.indexOf("?") < 0)
        url = url + "?" + comboKey;
    else
        url = url + "&" + comboKey;

    if(remote == null || remote == undefined)
        remote = true;

    var $combo = jQuery('#' + id);

    $combo.combobox({
        mode: remote == true ? 'remote' : 'local',
        url: url,
        dataType: 'json',
        valueField: 'id',
        textField: 'text',
        multiple: multiple,
        panelHeight: 130,

         ✅ USER ACTION DETECTION 
        onShowPanel: function () {
			//alert("select from combobox");
            EASYUI_COMBO.isUserAction = true;
			//alert("select from combobox:"+EASYUI_COMBO.isUserAction);
        },

        onLoadSuccess: function () {
            var fnName = formName + id + '_onLoadSuccess';
			//alert("onLoadSuccess:"+fnName);
            dynamicFunctionCall(fnName, []);
        },

        onSelect: function (record) {
			//alert("before "+id+":"+record);
			
			//alert("before "+id+":"+EASYUI_COMBO.isUserAction);

             ❌ IGNORE AUTO RELOAD / setValue 
            if (!EASYUI_COMBO.isUserAction || EASYUI_COMBO.isAutoSelect) {
                EASYUI_COMBO.isUserAction = false;
               // EASYUI_COMBO.isAutoSelect = false;
                return;
            }

            EASYUI_COMBO.isUserAction = false;

             ✅ USER SELECT ONLY 
            var fnName = formName + id + '_onSelect';
            dynamicFunctionCall(fnName, [record]);
        },

        onUnselect: function () {
            var fnName = formName + id + '_onUnSelect';
            dynamicFunctionCall(fnName, []);
        },

        onClear: function (opt) {//madhan  onClear changed to onChange
			
		
                var fnName = formName + id + '_onClear';
                dynamicFunctionCall(fnName, []);
			
        }
    });
	
	
	

     search contains 
    if(searchContains){
        $combo.combobox({
            filter: function(q, row){
                var opts = jQuery(this).combobox('options');
                return row[opts.textField]
                    .toLowerCase()
                    .indexOf(q.toLowerCase()) >= 0;
            }
        });
    }
}*/

/*var selecting = false;//madhan

function fillComboBox(formName, id, url, searchContains, remote, multiple){

    url = removeValueFromUrl(url,"combokey");
    var comboKey ="combokey="+getFieldValue(id);

    if(url.indexOf("?") < 0)
        url = url + "?" + comboKey;
    else
        url = url + "&" + comboKey;

    if(remote == null || remote == undefined)
        remote = true;

    var $combo = jQuery('#' + id);

    $combo.combobox({
        mode: remote == true ? 'remote' : 'local',
        url: url,
        dataType: 'json',
        valueField: 'id',
        textField: 'text',
        multiple: multiple,
        panelHeight: 130,

		
         ✅ USER ACTION DETECTION 
        onShowPanel: function () {
			//alert("select from combobox");
            EASYUI_COMBO.isUserAction = true;
			//alert("select from combobox:"+EASYUI_COMBO.isUserAction);
        },
		
		

        onLoadSuccess: function () {
            var fnName = formName + id + '_onLoadSuccess';
			//alert("onLoadSuccess:"+fnName);
            dynamicFunctionCall(fnName, []);
        },

        onSelect: function (record) {
			//alert("before "+id+":"+record);
			
			//alert("before "+id+":"+EASYUI_COMBO.isUserAction);

             ❌ IGNORE AUTO RELOAD / setValue 
            if (!EASYUI_COMBO.isUserAction || EASYUI_COMBO.isAutoSelect) {
                EASYUI_COMBO.isUserAction = false;
               // EASYUI_COMBO.isAutoSelect = false;
                return;
            }
			selecting = true;
            EASYUI_COMBO.isUserAction = false;

             ✅ USER SELECT ONLY 
            var fnName = formName + id + '_onSelect';
            dynamicFunctionCall(fnName, [record]);
        },

        onUnselect: function () {
            var fnName = formName + id + '_onUnSelect';
            dynamicFunctionCall(fnName, []);
        },

        onChange: function (newValue,oldValue) {//madhan  onClear changed to onChange
			
			if (selecting) {
			           selecting = false;
			           return;
			       }
				 
			alert("oldValue :"+oldValue);
			alert( id + newValue);
			//if (oldValue !== "" && (!newValue || newValue === "")){
		if (oldValue.length !== 0 &&  (!newValue || newValue.length == 0 )){
				var clearRecord = {
					    	    new: newValue,
					    	    old: oldValue
					    	}
                var fnName = formName + id + '_onClear';
				//alert(fnName);
                dynamicFunctionCall(fnName, []);
			}
        }
    });
	
	
	

     search contains 
    if(searchContains){
        $combo.combobox({
            filter: function(q, row){
                var opts = jQuery(this).combobox('options');
                return row[opts.textField]
                    .toLowerCase()
                    .indexOf(q.toLowerCase()) >= 0;
            }
        });
    }
}*/


var selecting = false;//madhan

function fillComboBox(formName, id, url, searchContains, remote, multiple){

    url = removeValueFromUrl(url,"combokey");
    var comboKey ="combokey="+getFieldValue(id);

    if(url.indexOf("?") < 0)
        url = url + "?" + comboKey;
    else
        url = url + "&" + comboKey;

    if(remote == null || remote == undefined)
        remote = true;

    var $combo = jQuery('#' + id);

    $combo.combobox({
        mode: remote == true ? 'remote' : 'local',
        url: url,
        dataType: 'json',
        valueField: 'id',
        textField: 'text',
        multiple: multiple,
        panelHeight: 130,

		
        /* ✅ USER ACTION DETECTION */
        onShowPanel: function () {
			//alert("select from combobox");
           // EASYUI_COMBO.isUserAction = true;
			jQuery(this).data("isUserAction", true);
			//alert("select from combobox:"+EASYUI_COMBO.isUserAction);
        },
		
		

        onLoadSuccess: function () {
            var fnName = formName + id + '_onLoadSuccess';
			//alert("onLoadSuccess:"+fnName);
            dynamicFunctionCall(fnName, []);
        },

        onSelect: function (record) {
			//alert("before "+id+":"+record);
			
			//alert("before "+id+":"+EASYUI_COMBO.isUserAction);

            /* ❌ IGNORE AUTO RELOAD / setValue */
            /*if (!EASYUI_COMBO.isUserAction || EASYUI_COMBO.isAutoSelect) {
                EASYUI_COMBO.isUserAction = false;
               // EASYUI_COMBO.isAutoSelect = false;
                return;
            }
			selecting = true;
            EASYUI_COMBO.isUserAction = false;*/
			var $this = jQuery(this);
			var isUserAction = $this.data("isUserAction") || false;

			    if (!isUserAction || EASYUI_COMBO.isAutoSelect) {
			        $this.data("isUserAction", false);
			        return;
			    }

			    selecting = true;
			    $this.data("isUserAction", false);

            /* ✅ USER SELECT ONLY */
            var fnName = formName + id + '_onSelect';
            dynamicFunctionCall(fnName, [record]);
        },

        onUnselect: function () {
            var fnName = formName + id + '_onUnSelect';
            dynamicFunctionCall(fnName, []);
        },

        onChange: function (newValue,oldValue) {//madhan  onClear changed to onChange
			
			if (selecting) {
			           selecting = false;
			           return;
			       }
				 
			/*alert("oldValue :"+oldValue);
			alert( id + newValue);*/
			//if (oldValue !== "" && (!newValue || newValue === "")){
		if (oldValue.length !== 0 &&  (!newValue || newValue.length == 0 )){
				var clearRecord = {
					    	    new: newValue,
					    	    old: oldValue
					    	}
                var fnName = formName + id + '_onClear';
				//alert(fnName);
                dynamicFunctionCall(fnName, []);
			}
        }
    });
	
	
	

    /* search contains */
    if(searchContains){
        $combo.combobox({
            filter: function(q, row){
                var opts = jQuery(this).combobox('options');
                return row[opts.textField]
                    .toLowerCase()
                    .indexOf(q.toLowerCase()) >= 0;
            }
        });
    }
}
function fillComboBoxWithGrid(formName, id, url,searchContains,multiple){
 
	jQuery('input[id^='+id+']').combobox({
		mode:'local',
		url:url,
		dataType:'json',		
		valueField:'id',
		textField:'text',
		multiple:multiple,
		panelHeight:130,
		
		onShowPanel: function () {
					//alert("select from combobox");
		            EASYUI_COMBO.isUserAction = true;
					//alert("select from combobox:"+EASYUI_COMBO.isUserAction);
		        },
		onLoadSuccess:function()
		{
			
			
			var onLoadSuccessName = formName+id +'_onLoadSuccess';
			/*try{
				if( typeof eval('('+onLoadSuccessName +')') == 'function')
				{
					
					eval('( '+ onLoadSuccessName +'())');
				}
			}catch(Exception){}*/
			var args = [];
			dynamicFunctionCall(onLoadSuccessName,args);
		},
		
		onSelect:function(record)
		{
			
			if (!EASYUI_COMBO.isUserAction || EASYUI_COMBO.isAutoSelect) {
			                EASYUI_COMBO.isUserAction = false;
			               // EASYUI_COMBO.isAutoSelect = false;
			                return;
			            }
						
			            EASYUI_COMBO.isUserAction = false;
			var onSelectFunctionName = formName+id +'_onSelect';
			/*try{
				if( typeof eval('('+onSelectFunctionName +')') == 'function')
				{
					
					eval('( '+ onSelectFunctionName +'(record))');
				}	
			}catch(Exception){}
			*/
			var args = [record];
			dynamicFunctionCall(onSelectFunctionName,args);
		},
		onUnselect:function()
		{
			var onUnSelectFunctionName = formName+id +'_onUnSelect';
			try{
				if( typeof eval(onUnSelectFunctionName) == 'function')
				{
					eval('( '+ onUnSelectFunctionName +'())');
				}
			}catch(Exception){}	
		},
		onClear: function(opts) {
			var onClearFunctionName = formName+id +'_onClear';
			try{
				if( typeof eval(onClearFunctionName) == 'function')
				{
					eval('( '+ onClearFunctionName +'())');
				}
				
			}catch(Exception){}	
		}
		
		
		/*,
		keyHandler: {
			up: function(){
				var opts = jQuery(this).combobox('options');
				opts.keyHandler.up.call(this);
			},
			down: function(){ 
				var opts = jQuery(this).combobox('options');
				opts.keyHandler.down.call(this);
			},
			enter: function(){
				var onKeyPress = formName+id +'_onEnterKeyPress';			
				var args = null;
				dynamicFunctionCall(onKeyPress,args);
				
			},
			query: function(q){}
		}*/
		
		
	});
	if(searchContains){
		jQuery("#"+id).combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');
				return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
			}
		});	
	}
}


/*function fillComboBoxWithGrid(formName, id, url,searchContains,multiple){
 
	jQuery('[id^='+id+']').combobox({
		mode:'local',
		url:url,
		dataType:'json',		
		valueField:'id',
		textField:'text',
		multiple:multiple,
		panelHeight:130,
		onLoadSuccess:function()
		{
			
			
			var onLoadSuccessName = formName+id +'_onLoadSuccess';
			try{
				if( typeof eval('('+onLoadSuccessName +')') == 'function')
				{
					
					eval('( '+ onLoadSuccessName +'())');
				}
			}catch(Exception){}
			var args = [];
			dynamicFunctionCall(onLoadSuccessName,args);
		},
		
		onSelect:function(record)
		{
			var onSelectFunctionName = formName+id +'_onSelect';
			try{
				if( typeof eval('('+onSelectFunctionName +')') == 'function')
				{
					
					eval('( '+ onSelectFunctionName +'(record))');
				}	
			}catch(Exception){}
			
			var args = [record];
			dynamicFunctionCall(onSelectFunctionName,args);
		},
		onUnselect:function()
		{
			var onUnSelectFunctionName = formName+id +'_onUnSelect';
			try{
				if( typeof eval(onUnSelectFunctionName) == 'function')
				{
					eval('( '+ onUnSelectFunctionName +'())');
				}
			}catch(Exception){}	
		},
		onClear: function(opts) {
			var onClearFunctionName = formName+id +'_onClear';
			try{
				if( typeof eval(onClearFunctionName) == 'function')
				{
					eval('( '+ onClearFunctionName +'())');
				}
				
			}catch(Exception){}	
		}
		
		
		,
		keyHandler: {
			up: function(){
				var opts = jQuery(this).combobox('options');
				opts.keyHandler.up.call(this);
			},
			down: function(){ 
				var opts = jQuery(this).combobox('options');
				opts.keyHandler.down.call(this);
			},
			enter: function(){
				var onKeyPress = formName+id +'_onEnterKeyPress';			
				var args = null;
				dynamicFunctionCall(onKeyPress,args);
				
			},
			query: function(q){}
		}
		
		
	});
	if(searchContains){
		jQuery("#"+id).combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');
				return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
			}
		});	
	}
}*/

function reloadCombo(formId,id,url)
{
	//madhan
	
	
	 jQuery('#'+id).combobox("reload",url);
	 /*var cmb = jQuery('#' + id);

	 cmb.combobox({
	     autoSelect: false
	 });

	 cmb.combobox('reload', url);*/

	/* var cmb = jQuery('#' + id);

	    // Save current value
	    var oldValue = cmb.combobox('getValue');
		alert(id+" : "+oldValue);

	    // DO NOT reinitialize combobox
	    cmb.combobox('reload', url);

	    // Restore after data loads
	    cmb.combobox({
	        onLoadSuccess: function () {
	            if (oldValue) {
	                cmb.combobox('setValue', oldValue);
	            }
	        }
	    });
	 */
	//fillComboBox(formId,id,url);
}

/*function reloadCombo(formId, id, url) {

    var cmb = $('#' + id);

    // Save current value
    var oldValue = cmb.combobox('getValue');
    alert(id + " : " + oldValue);

    // Attach handler ONCE (no reload triggered)
	if (oldValue != undefined && oldValue != null) {
		alert(id + "2 : " + oldValue);
    cmb.combobox('options').onLoadSuccess = function () {
        if (oldValue != undefined) {
            cmb.combobox('setValue', oldValue);
        }
    };
	}

    // Single reload
    cmb.combobox('reload', url);
}*/



function loadComboBox(formName, id, url,data,loadFirst){
	
	if( loadFirst != null && loadFirst )
		createComboBox(formName, id);

	jQuery.ajax({
	       type: "POST",
	       url: url,
	       dataType: "json",
	       data:data,
	       success: function(result)
	       {
	    	   jQuery('#'+id).loaddata(result.comboboxData);
	    	   	//jQuery('#'+id).combo
	       },
		   error:function(status)
		   {
 
		   }
	});
		
}


function createComboBox(formName, id){
	jQuery('#'+id).combobox({
		mode:'local',
		dataType:'json',
		valueField:'id',
		textField:'text',
		onSelect:function(record)
		{
			var onSelectFunctionName = formName+id +'_onSelect';
			if( typeof eval(onSelectFunctionName) == 'function')
			{
				eval('( '+ onSelectFunctionName +'(record))');
			}	
		},
		onUnselect:function()
		{
			var onUnSelectFunctionName = formName+id +'_onUnSelect';
			if( typeof eval(onUnSelectFunctionName) == 'function')
			{
				eval('( '+ onUnSelectFunctionName +'())');
			}
		},
		
		selected: function(event, ui) {
			var onSelectedFunctionName = formName+id +'_selected';
			
			if( typeof eval(onSelectedFunctionName) == 'function')
			{
				eval('( '+ onSelectedFunctionName +'(event, ui))');
			}
		}
	});
}

 
var formSaveArr = [];
function 	saveForm(formId,url,ErrLabel)
{
	//alert("sf1");
	//alert("formId:"+formId);
	//alert("url:"+url);
	
	
	var inActive = jQuery("#hdnIsFlidActive").val();
	if(inActive == "N"){
		jQuery('#dispErr').html('<h5> Function Location is InActive </h5>');
		div_err();
		return;
	}
					
    if( formSaveArr[ formId ] == formId  ){
    	jQuery.messager.alert('Perfex','Please wait while saving..','info');
    	return ;
    }
    	

    show_winMask(1);
    jQuery(".save-loading").html("Please wait while saving...");
	var DisErrField =null;
	if (ErrLabel==null || ErrLabel=="")
		DisErrField="dispErr";
	else
		DisErrField="subdispErr";	
	
	
	if( url.indexOf('_input') > 0 )
		url = url.replace('_input','_save');
	else if(url.indexOf('_modify') > 0 )
		url = url.replace('_modify','_save');
	//else if(url.indexOf('_view') > 0 )
		//url = url.replace('_view','_save');
	//else if(url.indexOf('_modify') > 0 )
	//else
	//	return false;
	var retVal = true ;	
	jQuery("div .combo-panel").parent().hide();
	try{
		var beforeSubmit = eval( formId+"_beforeSubmit" );
	 
		if( jQuery.isFunction(beforeSubmit)){
			//retVal = window[formId+"_beforeSubmit"].apply(this,null);
			retVal = dynamicFunctionCall(formId+"_beforeSubmit",null);
		}
	}catch(Exception ){
	}

	if( retVal == false){
		show_winMask(0);
		return ;
	}	
	    clearValidationErrorMessages(formId,DisErrField);
	    //jQuery(".save-loading").html("Please wait while saving...");
	    //show_winMask(1);
	    if( formSaveArr[ formId ] != formId  ){
	    	formSaveArr[ formId ] = formId;
	    }
	    jQuery.ajax({  
            type: "POST",  
            url: url,  
            data: jQuery('#'+formId).serialize()+'&'+retVal,  
            dataType: "json",  
            success: function(result){  
            	delete formSaveArr[ formId ];
            	show_winMask(0);
            	
            	if( result.exception )
            	{	
            		var validMsgs = result.messages;
            		var validTpmMsgs ="";
            		for(var i = 0; i < validMsgs.length;i++)
            		{	
            			if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") )
            				jQuery('#'+validMsgs[i][0]).next("span").addClass("tpm-error");
            			else	
            				jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
	            		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 )
	            		{	
	            			if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") ){
	            				jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+ validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
	            			}	
	            			else	
	            				jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
	            		}	
	            		jQuery('#err_'+validMsgs[i][0] ).css("display","block").html("* "+validMsgs[i][1]);
	            		//jQuery('#err_'+validMsgs[i][0]).html("* "+validMsgs[i][1]);
	            		
	            		validTpmMsgs += validMsgs[i][1] +",";
	            		
            		}
            		jQuery('#'+DisErrField).html('<h5> ' + validTpmMsgs +'</h5>');
            		div_err();
            		
            		var exceptionCallback =  formId + '_exceptionCallback';//Added by Siddharth.A
            		var expargs = [result ];
            		dynamicFunctionCall(exceptionCallback, expargs);
            		
            	/*	if( typeof eval('(' + exceptionCallback  +')') == 'function')
	            		eval('(' + exceptionCallback  +'(result))');
	            */
            		
            	}
            	else if( result.tpmException ){
            		show_winMask(0);
            		jQuery('#'+DisErrField).html('<h5> ' +result.tpmException +'</h5>');
            		div_err();
            		
            		var exceptionCallback =  formId + '_exceptionCallback';//Added by Siddharth.A
            		if( typeof eval('(' + exceptionCallback  +')') == 'function')
	            		eval('(' + exceptionCallback  +'(result))');
            	}	
            	else{
            		try{
            			/*alert(
            			"msg "+result.successData.msg+
            			"displyMsg "+result.displyMsg+
            			"forwardData "+Object.keys(result.forwardData)+
            			"txtwwmsFinalaction "+result.successData.txtwwmsFinalaction+
            			"txtformType "+result.successData.txtformType+
            			"PhenomenaFlag "+result.PhenomenaFlag+
            			"formMode "+result.formMode+
            			"persistentData "+Object.keys(result.persistentData));*/
		            	var successCallback =  formId + '_successsCallback';
		            	var successMsg = result != null ? (result.successData !=null? result.successData.msg:""):"";
		            	 
		           		show_success(successMsg );
		           		 
		            	if( result != null && (result.displyMsg == null || result.displyMsg == true) && result.successData != null && result.successData != undefined ){
		               		 
		            		msgBox(result.successData.msg); //div_err();
		            	}
		             
		            	if(  result.formClear == null || result.formClear == true) {      	
		            		 
		            		clearForm(formId);	 
		            	}
		            	 
		            	var args = [ result ];
		            	 
		            	//alert("result  "+Object.keys(result));
		            	if( dynamicFunctionCall(successCallback,args) == false ){
							return ;
						}
						 
						var retVal = getFilterValue(url + '&',"closeOnSave") ;
							
		            	if( retVal != "" && retVal.length > 0 &&  retVal === 'true'){
		            		navigateToPrevForm(result);
		            	}   
            		}catch(Exception){
            			
            		}

            	}
            		
            },  
            error: function(msg){
            	delete formSaveArr[ formId ];
            	show_winMask(0);
            	jQuery('#'+DisErrField).html(msg.responseText);
            	
            	div_err();
            	var errCallback =  formId + '_errorCallback';
            	if( typeof eval('(' + errCallback +')') == 'function')
            		eval('(' + errCallback +'(msg))');
            }  
        }); 
}


function clearValidationErrorMessages(formId,DisErrField){
	jQuery('#'+formId + ' .tpm-error').removeClass("tpm-error");
	jQuery('#'+formId + ' div[id^="err_"]').css("display","none");
	jQuery('#'+formId + ' span[id^="err_"]').css("display","none");
	jQuery('#'+DisErrField).html("");
}

/*function saveForm(formId,url)
{	

	if( url.indexOf('_input') > 0 )
		url = url.replace('_input','_save');
	else if(url.indexOf('_modify') > 0 )
		url = url.replace('_modify','_save');
	//else if(url.indexOf('_modify') > 0 )
	//else
	//	return false;
	var retVal = true ;

	try{
		var beforeSubmit = eval( formId+"_beforeSubmit" );
	 
		if( jQuery.isFunction(beforeSubmit)){
			retVal = window[formId+"_beforeSubmit"].apply(this,null);
		}
	}catch(Exception ){
		
	}
	
	if( retVal == false)
		return ;
	
	jQuery('#'+formId + ' .tpm-error').removeClass("tpm-error");
	jQuery('#'+formId + ' div[id^="err_"]').css("display","none");
	jQuery('#'+formId + ' span[id^="err_"]').css("display","none");
	jQuery("#dispErr").html("");

	 jQuery.ajax({  
            type: "POST",  
            url: url,  
            data: jQuery('#'+formId).serialize()+'&'+retVal,  
            dataType: "json",  
            success: function(result){  
            	
            	if( result.exception )
            	{	
            		var validMsgs = result.messages;
            		for(var i = 0; i < validMsgs.length;i++)
            		{	
	            		jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
	            		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 )
	            		{	
	            			if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") )
	            				jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+ validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
	            			else	
	            				jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
	            		}	
	            		jQuery('#err_'+validMsgs[i][0] ).css("display","block");
	            		jQuery('#err_'+validMsgs[i][0]).html(validMsgs[i][1]);
            		}
            	}
            	else if( result.tpmException ){            		
            		jQuery("#dispErr").html('<h3> ' +result.tpmException +'</h3>');
            	}	
            	else{
            		try{
            			
		            	var successCallback =  formId + '_successsCallback';
		            
		            	if( result.displyMsg == null || result.displyMsg == true)
		            	   
		            	
		            	if(  result.formClear == null || result.formClear == true)       	
		            		clearForm(formId);	            	
		            	
		            	
		            	//if( result.clearForm == null || result.clearForm == true)
		            	//	clearForm(formId);
		            	
		            	
		            	
		          
		            	

		            	
		            	//jQuery("#dispErr").html('<h3> ' +result.successData.msg +'</h3>');
		            	if( typeof eval('(' + successCallback  +')') == 'function')
		            		eval('(' + successCallback  +'(result))');
            		}catch(Exception){
            			
            		}

            	}
            		
            },  
            error: function(msg){
            	jQuery("#dispErr").html(msg.responseText);
            
            	
            	var errCallback =  formId + '_errorCallback';
            	if( typeof eval('(' + errCallback +')') == 'function')
            		eval('(' + errCallback +'(msg))');
            	
            	  
            }  
        });  
}*/

/*
function saveForm(formId,url,action)
{
	 
	if( url.indexOf('_input') > 0 )
		url = url.replace('_input',action);
	else if(url.indexOf('_modify') > 0 )
		url = url.replace('_modify',action);
	//else if(url.indexOf('_modify') > 0 )
	//else
	//	return false;

	
	 		
 		jQuery('#'+formId + ' .tpm-error').removeClass("tpm-error");
 		jQuery('#'+formId + ' div[id^="err_"]').css("display","none");
 		jQuery("#dispErr").html("");
 		
			 jQuery.ajax({  
		            type: "POST",  
		            url: url,  
		            data: jQuery('#'+formId).serialize(),  
		            dataType: "json",  
		            success: function(result){  
		            	if( result.exception )
		            	{	
		            		var validMsgs = result.messages;
		            		for(var i = 0; i < validMsgs.length;i++)
		            		{	
			            		jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
			            		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 )
			            		{	
			            			if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") )
			            				jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
			            			else	
			            				jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
			            		}	
			            		jQuery('#err_'+validMsgs[i][0] ).css("display","block");
			            		jQuery('#err_'+validMsgs[i][0]).html(validMsgs[i][1]);
		            		}
		            	}
		            	else if( result.tpmException ){
		            		jQuery("#dispErr").html('<h3> ' +result.tpmException +'</h3>');
		            	}	
		            	else{
		            		
			            	var successCallback =  formId + '_successsCallback';
			            	
			            	if( typeof eval('(' + successCallback  +')') == 'function')
			            		eval('(' + successCallback  +'(result))');
							clearForm(formId);
		            	}
		            		
		            },  
		            error: function(msg){
		            	jQuery("#dispErr").html(msg.responseText);
		            	
		            	
		            	var errCallback =  formId + '_errorCallback';
		            	if( typeof eval('(' + errCallback +')') == 'function')
		            		eval('(' + errCallback +'(msg))');
		            	
		            	  
		            }  
		        });  
		  
		        //make sure the form doesn't post  
//		        return false;  
 
}*/

function deleteRecord(formId,url)
{	
	var inActive = jQuery("#hdnIsFlidActive").val();
		if(inActive == "N"){
			jQuery('#dispErr').html('<h5> Function Location is InActive </h5>');
			div_err();
			return;
		}
	if( url.indexOf('_input') > 0 )
		url = url.replace('_input','_delete');
	else if(url.indexOf('_modify') > 0 )
		url = url.replace('_modify','_delete');
	
	var retVal = ' ' ;
	
	if( url.indexOf('_delete') <= 0  ) return "";
	
	try{
		var beforeSubmit = formId+"_beforeDelete" ;
	 
	/*	if( jQuery.isFunction(beforeSubmit)){
			retVal = window[formId+"_beforeDelete"].apply(this,null);
		}
		*/
		var befSArg = null;
		retVal = dynamicFunctionCall(beforeSubmit, befSArg);
		
	}catch(Exception ){
		
	}
	
	if( retVal == false)
		return ;
	//jQuery('#modal_div').addClass('window-mask');//for disabling window while processing and saving
	//jQuery('save-loading').show();//save processing ajax
	show_winMask(1);
	 jQuery.ajax({  
            type: "POST",  
            url: url,  
            data: jQuery('#'+formId).serialize()+'&'+retVal,  
            dataType: "json",  
            success: function(result){  
            	show_winMask(0);
            	//jQuery('#modal_div').removeClass('window-mask');//for disabling window while processing and saving
            	//jQuery('save-loading').hide();//save processing ajax
            	
            	if( result.tpmException ){
            		
            		var deleteExceCallback =  formId + '_deleteExceptionCallback';
            		
            		if( result.displyMsg == null || result.displyMsg == true){
            			msgBox(result.tpmException);       		
            			
            		}
            		var expMsgdisp ="";
            		if(result.tpmException != undefined && result.tpmException != null && result.tpmException.confirm!=undefined && result.tpmException.confirm != null)
            			expMsgdisp = result.tpmException.confirm;
            		else if(result.tpmException != undefined && result.tpmException != null)
            			expMsgdisp = result.tpmException;
            		
            		jQuery("#dispErr").html('<h5> ' +expMsgdisp+'</h5>');
            	
            		var delArgs = [result];
            		
            		dynamicFunctionCall(deleteExceCallback, delArgs);
            		div_err();
            	}	
            	else{
	            	var successCallback =  formId + '_deleteSuccessCallback';
	            		            	
	            	
	            	if( ( result.displyMsg == true) && result.successData != undefined && result.successData != null && result.successData.msg != undefined)
	            	{	
	            		
	            		alert(result.successData.msg);
	            	}
	            	
	            	if(  result.formClear == null || result.formClear == true){       	
	            	    
	            		//clearForm(formId);	 
	            	}
	            	//alert(url);
	            	var retVal = getFilterValue(url + '&',"closeOnSave") ;
					
	            	if( retVal != "" && retVal.length > 0 &&  retVal === 'true'){
	            		navigateToPrevForm(result);
	            	}	
	            	//afterMasterFrmDelete();
	            	var delArgs = [ result ];
	            	dynamicFunctionCall(successCallback, delArgs);
	            /*	if( typeof eval('(' + successCallback  +')') == 'function')
	            		eval('(' + successCallback  +'(result))');
				*/
            	}
            		
            },  
            error: function(msg){
            	show_winMask(0);
            	//jQuery('#modal_div').removeClass('window-mask');//for disabling window while processing and saving
            	//jQuery('save-loading').hide();//save processing ajax
            	
            	jQuery("#dispErr").html(msg.responseText);
          
            	div_err();
            	
            	var errCallback =  formId + '_deleteErrorCallback';
            	if( typeof eval('(' + errCallback +')') == 'function')
            		eval('(' + errCallback +'(msg))');
            	
            	  
            }  
        });  
}


function formNavigation(){
	this.URL;
	this.caption;// Form header caption
	this.divId;// 
	this.formId;
	this.params;
/*	this.preLoadDivId;
	this.successCallBack;
	this.errorCallBack;
*/	
}

function pushFormNavigationDetails(url,formheader,params) //,preLoadDivId,success_CallBack,err_CallBack)
{
	formNavigationLog("Start push pushFormNavigationDetails ");
	var formNavig = new formNavigation();
	formNavig.URL = url;
	formNavig.caption = formheader;
	formNavig.params = ( params != undefined && params != null ? params:"");
	
	var notSetHiddenUrl = getFilterValue(url,"notSetHiddenUrl"); // do not set url to  hiddenUrl element
	var fromDate = getFilterValue(url,"dtFromDate");
	var toDate = getFilterValue(url,"dtToDate");
	var fromMonth = getFilterValue(url,"dtFromMonth");
	var toMonth = getFilterValue(url,"dtToMonth");
	var datewise = getFilterValue(url,"chkDatewise");
	var monthwise = getFilterValue(url,"chkMonthwise");
	//alert(datewise+"------"+monthwise);
	var dateHeader = " (From: "+fromMonth+" To: "+toMonth+")";
	if(datewise=="1")
		dateHeader = " (From: "+fromDate+" To: "+toDate+")";
	else if(fromMonth=="" || toMonth=="")
		dateHeader  =  " ";
	
	if(  (notSetHiddenUrl == true || notSetHiddenUrl =='true') ){}
	else	
		jQuery("#hiddenUrl").val(url);
	
	if( formheader != '' && formheader != null)
		setFormMainHeader(formheader+dateHeader);
	
	var divId = null;
	//alert(url);
	if( url.indexOf("loadContentDivId=") > -1){
		divId = getFilterValue(url,"loadContentDivId");
		if( formNavigations == null)
			formNavigations = [];
	}
	else{
		//alert("else");
		var prevNavigObj = null;
		//alert("formNavigations.length :"+ formNavigations.length);
		if(formNavigations.length >0 ){
		if( formNavigations != null)
			prevNavigObj = formNavigations[formNavigations.length -1]; // formNavigations.pop(); 
		//alert("prevNavigObj :"+prevNavigObj.divId);
		divId = (prevNavigObj != null ? prevNavigObj.divId:"") ;
	    if( divId != null && divId.indexOf('LoadContent_') >= 0 ){
			divId = (divId != "" ?divId.replace("LoadContent_",""):0);
			divId = parseInt(divId) +1;
		}	
	    else{
			//alert("inside  loadpopup "+prevNavigObj.divId);
			divId =0;
			jQuery("[id^='LoadContent_']").each(function () {
			            var id = jQuery(this).attr("id");
			            var n = parseInt(id.replace("LoadContent_", ""), 10);
			            if (!isNaN(n)) divId = Math.max(divId, n);
			        });
					//divId =0;
		   //alert("after id :"+ divId);
		   
		   
		   if(prevNavigObj.divId == "loadPopUpLoaddefContenpwd"){
			divId =1;
			//alert("after id loadPopUpLoaddefContenpwd :"+ divId)
		   }
		}
	    	
	    }else{
			divId =1;
		}
		
		//divId = parseInt(divId) +1;
		
		divId = 'LoadContent_' + divId;
		//alert("div push :"+divId);
		if( formNavigations == null)
			formNavigations = [];
		formNavigationLog("Before push pushFormNavigationDetails ");
		/*var ids = [];

					for (var i = 0; i < formNavigations.length; i++) {
					    if (formNavigations[i] && formNavigations[i].divId) {
					        ids.push(formNavigations[i].divId);
					    }
					}

					alert("formNavigations before (" + formNavigations.length + "):\n" + ids.join("\n"));*/
		//if( prevNavigObj != "" &&  prevNavigObj != null  )
			//formNavigations.push(prevNavigObj);
	}	
/*	if( preLoadDivId != null && preLoadDivId != undefined && preLoadDivId.length > 0 ){
		formNavig.preLoadDivId = preLoadDivId;
	}
	if( success_CallBack != null && success_CallBack != undefined && success_CallBack.length > 0 ){
		formNavig.successCallBack = success_CallBack;
	}
	if( err_CallBack != null && err_CallBack != undefined && err_CallBack.length > 0 ){
		formNavig.errorCallBack = err_CallBack;
	}
*/	
	formNavig.divId = divId;
	
	formNavigations.push(formNavig);
	formNavigationLog("After push pushFormNavigationDetails ");
	/*var ids = [];

			for (var i = 0; i < formNavigations.length; i++) {
			    if (formNavigations[i] && formNavigations[i].divId) {
			        ids.push(formNavigations[i].divId);
			    }
			}

			alert("formNavigations (" + formNavigations.length + "):\n" + ids.join("\n"));*/
	//alert("formNavigations.length after push :"+ formNavigations.length);
}

function formNavigationLog(msg){
	var ids = [];

				for (var i = 0; i < formNavigations.length; i++) {
				    if (formNavigations[i] && formNavigations[i].divId) {
				        ids.push(formNavigations[i].divId);
				    }
				}

				//alert("formNavigations -"+msg+" (" + formNavigations.length + "):\n" + ids.join("\n"));
				console.log("formNavigations -"+msg+" (" + formNavigations.length + "):\n" + ids.join("\n"));
}

function refreshHomePageForMenu(){
	//formNavigationLog(" Start refreshHomePageForMenu");
	var chgpwd = jQuery("#hdnPassword").val();
		//alert("chgpwd1:"+chgpwd);
		if(chgpwd.trim() != "true")
		{
			//alert("chgpwd1 inside:"+chgpwd);
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
}

function popFormNavigation(){
	
	//alert("formNavigations.length popFormNavigation :"+ formNavigations.length);
	//var formNavObj =   formNavigations[formNavigations.length-1];
	//alert("formNavigations popFormNavigation :"+ formNavObj.divId);
	formNavigationLog(" Start popFormNavigation");
	/*if (formNavObj && !formNavObj.divId.startsWith("LoadContent_")) {
		
		return null;
	}*/
	/*if (formNavObj && formNavObj.divId.equals("loadPopUpLoaddefContenpwd")) {
			//alert("inside skip");
			return null;
		}*/
	
	
	var formNavObj =   formNavigations.pop(); //formNavigations[formNavigations.length-1];
	formNavigationLog(" After popFormNavigation");
	
	if( formNavObj != null )
	{
		
		var isHomePageC =  getFilterValue(formNavObj.URL + '&','isHomePage');
		if (formNavObj && formNavObj.divId == "loadFilter") {
			//alert(formNavObj.divId);
		toggleCommonFilterWithoutPop(true);
		}
		else if (formNavObj && !formNavObj.divId.startsWith("LoadContent_")) {
			//alert(formNavObj.divId);
			closePopUpDialoge(formNavObj.divId);
				//jQuery('#'+formNavObj.divId).remove();//		closePopUpDialoge(formNavObj.divId);
				//jQuery('#filterShowHide').hide();
		}
		else if( isHomePageC != "true"  && formNavObj.divId != null && formNavObj.divId != undefined && jQuery("#"+formNavObj.divId).length > 0 ){
			jQuery("#"+formNavObj.divId).html(" ");
			jQuery("#"+formNavObj.divId).css('display','none');
			jQuery("#"+formNavObj.divId).remove();
		}
		
	}	
	return formNavObj;
}

function refreshForm()
{
	jQuery("#preLoadContent").css("display","none");
	var formId = jQuery('#submitForm').val(); 	
	var beforeRefreshCallback = formId+'_beforeRefreshCallback';
	var retVal = true ;
	try{
		var beforeRefresh = eval( beforeRefreshCallback);			
		if( jQuery.isFunction(beforeRefresh)){			
			retVal = window[beforeRefreshCallback].apply(this,null);
		}
	}catch(Exception ){
		
	}
	if( retVal == false)
		return ;
	if( formNavigations != null && formNavigations.length > 0){
		var formNavig = formNavigations.pop();
		
		var divId =  formNavig.divId;
	

	//	var url = formNavig.URL   ;
		var url = formNavig.URL +  (formNavig.URL.indexOf('?') > -1 ? "&": '?') + formNavig.params;
		formNavigations.push(formNavig);
		//jQuery('#LoadContent').append('<div id="'+ divId +'" </div> ');47

		
		if( formId != null && formId != undefined && formId.trim().length > 0 )
			clearValidationErrorMessages(formId,"dispErr");
		
		/*var isHomePageC =  getFilterValue(formNavig.URL + '&','isHomePage');
		if( isHomePageC == "true" ){
			//if( jQuery('#'+formNavig.divId).length <= 0 )
			//	jQuery('#LoadContent').append('<div id="'+ curentForm.divId +'" > </div> ');
			jQuery("#homePageHiddenHtml").html('');
		}	*/
		
		LoadForm(divId, "preLoadContent",url,"dispErr","refresh_SuccessCalBack","refresh_ErrorCalBack");
	}
	
}

function refresh_ErrorCalBack(msg)
{
	
		
/*	var divId = formNavigations[ formNavigations.length -1 ].divId ;
	var divId = 'LoadContent_' + (formNavigations.length+1);
	var oldDivId = 'LoadContent_' + (formNavigations.length);
	
	jQuery('#'+oldDivId).show();
	jQuery('#'+divId).remove();
*/	
}

function refresh_SuccessCalBack(msg)
{
/*	var oldDivId = 'LoadContent_' + (formNavigations.length);
	jQuery('#'+oldDivId).remove();
	
*/	
}
/*
function navigateToNextForm(url,formheader,forwardData,persistentData)
{
		
	var data = "url="+url +"&formheader="+formheader + "&forwardData="+forwardData +"&persistentData="+persistentData;
	processAjaxCalls("nextForm.formNavig", data, "navigateToNext_onsuccessCallBack", "navigateToNext_onerrorCallBack");
	
}
function navigateToNext_onsuccessCallBack(result){
	var divId=result.divId;
	var url = result.url;
	
	LoadForm(divId,"preLoadContent",url,"dispErr","navigateToNext_SuccessCalBack","navigateToNext_ErrorCalBack");
}

function navigateToNext_ErrorCalBack(result){
	
}
*/
 
/*
 * 
 */
function navigateToNextForm(url,formheader,forwardData,persistentData,navigateToNext_SuccessCalBack,navigateToNext_ErrorCalBack)
{
	
	var formNavig = null;
	var actUrl = url;
	//alert("actUrl:"+actUrl);
	
	if( formNavigations != null ){
		formNavigationLog("Start push navigateToNextForm ");
		/*var ids = [];

		for (var i = 0; i < formNavigations.length; i++) {
		    if (formNavigations[i] && formNavigations[i].divId) {
		        ids.push(formNavigations[i].divId);
		    }
		}

		alert("formNavigations (" + formNavigations.length + "):\n" + ids.join("\n"));*/
		//alert("formNavigations.length navigateToNextForm :"+ formNavigations.length);
		formNavig = formNavigations.pop(); // formNavigations[formNavigations.length -1]; // formNavigations.pop();
		//prevUrl = formNavig.URL;
	}
	var divId = getFilterValue(url + '&',"loadContentDivId");
	//alert("divId:"+divId);
	var preLoadDivId = getFilterValue(url+'&',"preLoadContentDivId");
	//alert("preLoadDivId:"+preLoadDivId);
	//var userEvent = getFilterValue(url + '&',"userEvent");
	var filterButton = getFilterValue(url + '&','filterButton');
	if( preLoadDivId == null || preLoadDivId == "" )
		preLoadDivId = "preLoadContent";
	
	
	
	if( filterButton == 'false')
		disableFilterBtn();
	
	
	
	if( (divId == "" || divId == null)){
	//if( (divId == "" || divId == null))
		//alert("formNavig:"+formNavig.divId);
		divId = genNextLoadContentDivId(formNavig);
		//alert("divId New:"+divId);
		if( jQuery('#'+divId).length <= 0 )
			jQuery('#LoadContent').append('<div id="'+ divId +'" > </div> ');
	}
	else if( divId == "" || divId == null)
		divId = formNavig.divId;
	
	LoadingFormWaiter(preLoadDivId,divId);
	//alert("S");
	if( persistentData != null)
	{
		var pData= jQuery.trim( formNavig.URL.substring(jQuery.trim(formNavig.URL).length-1)) == "&" ? "" :"&" ;
		var names = Object.keys(persistentData);
		var tmpUrl = null;
		for(var i=0;i<names.length;i++ ){
			if( formNavig.URL.indexOf('&'+names[i]+'=') > 0 || formNavig.URL.indexOf('?'+names[i]+'=') > 0  ){
				tmpUrl = formNavig.URL.substring( formNavig.URL.indexOf(names[i])) ;
				tmpUrl = tmpUrl.substring( (tmpUrl.indexOf('&') >= 0 ? tmpUrl.indexOf("&") : tmpUrl.length)  );

				formNavig.URL = formNavig.URL.substring (0,formNavig.URL.indexOf(names[i])) + tmpUrl;  
			}	
			pData += names[i] + '='+escape(persistentData[names[i]]) +'&';
		}	
		pData = pData.substring(0, pData.length-1);
		//formNavig.URL +=  (formNavig.URL.indexOf('?') > -1 ? "": '?') + pData;
		formNavig.params =  pData;
	}	
	var fData = "";
	if( forwardData != null)
	{
		
		var names = Object.keys(forwardData);
		
		for(var i=0;i<names.length;i++ )
		{
			
			fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
		}
			
		url += (url.indexOf('?') > -1 ? "":'?') +fData;
		
	}
	
	if( formNavig != null){
		//formNavig.divId = divId;
		formNavigationLog("before push navigateToNextForm ");
		formNavig.formId = jQuery('#submitForm').val(); ;
		formNavigations.push(formNavig);
		formNavigationLog("After push navigateToNextForm ");
	}
	//if( userEvent != "new"){
	//alert("formNavig   "+formNavig.divId);
		if( formNavig != null &&  formNavig.divId.indexOf("LoadContent_") > -1 ){
			//var prevDivNum =parseInt(divNumber)-1;
			
			var isHidePrevForm = getFilterValue(url+'&',"isHidePrevForm");
			//alert("isHidePrevForm:"+isHidePrevForm);
			if( isHidePrevForm != "false" )
				jQuery('#'+ formNavig.divId).hide();
			var isHomePageC =  getFilterValue(formNavig.URL + '&','isHomePage');
			//alert("isHomePageC:"+isHomePageC);
			if( isHomePageC != "true" && isHidePrevForm != "false"){
				jQuery('#'+formNavig.divId).html(""); // if page is loading from server when going backward
			}
		}
		else if( formNavig != null ){
			var insideFormDivId = getFilterValue(url+'&',"LoadMasterFormDivId");
			//alert("insideFormDivId:"+insideFormDivId);
			if( insideFormDivId != undefined && insideFormDivId != null && insideFormDivId.length > 0 )
			{
				//alert("insideFormDivId:"+insideFormDivId);
				jQuery('#'+insideFormDivId).html(""); /* previous div id //do not change to divid */
				//jQuery('#'+insideFormDivId).hide();
				
				divId = insideFormDivId; 
			}else if(divId.indexOf("LoadContent_") >= 0){	//madhan		
				var	PdivId = parseInt( (divId != "" ? divId.replace("LoadContent_",""):0)) -1;	
							var isHidePrevForm = getFilterValue(url+'&',"isHidePrevForm");
							//alert("isHidePrevForm div :"+PdivId);
							if( isHidePrevForm != "false" ){ 
								//alert("isHidePrevForm div:"+PdivId);
								//jQuery('#'+divId).html(""); /* previous div id //do not change to divid */
								jQuery('#LoadContent_'+PdivId).hide();
							}	
			}else{	
				var isHidePrevForm = getFilterValue(url+'&',"isHidePrevForm");
				//alert("isHidePrevForm:"+isHidePrevForm);
				if( isHidePrevForm != "false" ){ 
					//alert("isHidePrevForm:"+isHidePrevForm);
					jQuery('#'+formNavig.divId).html(""); /* previous div id //do not change to divid */
					jQuery('#'+formNavig.divId).hide();
				}	
			}
			 
		}
	//}
	
	
	
	
	if(navigateToNext_SuccessCalBack == null )
		navigateToNext_SuccessCalBack ="navigateToNext_SuccessCalBack";
	if( navigateToNext_ErrorCalBack == null )
		navigateToNext_ErrorCalBack ="navigateToNext_ErrorCalBack";
	
	var navigateNext = null;
	if(url != null )
		navigateNext = getFilterValue(url + '&','navigateNext');
	
	if( navigateNext != "false" )
		pushFormNavigationDetails(actUrl,formheader,fData ); //,preLoadDivId,navigateToNext_SuccessCalBack,navigateToNext_ErrorCalBack);
	
	
//	jQuery.address.path("n&2*"+new Date());

	//alert(divId );
	LoadForm(divId,preLoadDivId,url,"dispErr",navigateToNext_SuccessCalBack,navigateToNext_ErrorCalBack);
	
	resetErrMessg();
}
/* madhan
function genNextLoadContentDivId(formNavig){
	var divNumber =  (formNavig  != null ? formNavig.divId :"");
	alert("divNumber:"+divNumber);
	if( divNumber.indexOf("LoadContent_") >= 0)
		divNumber = parseInt( (divNumber != "" ? divNumber.replace("LoadContent_",""):0)) +1;
	else
		divNumber =1;
	
	return 'LoadContent_' + (divNumber);
	 
}*/

function genNextLoadContentDivId(formNavig) {

    // If first navigation or invalid object → start from 1
    if (!formNavig || !formNavig.divId) {
        return "LoadContent_1";
    }
	//alert("formNavig old :"+formNavig.divId);

    var prevId = formNavig.divId.trim();
   //alert("prevId :"+prevId);
    // If previous div is NOT a LoadContent_* then generate fresh LoadContent
    if (!prevId.startsWith("LoadContent_")) {
        // Always continue from the highest existing LoadContent number
        var maxNum = 0;

        jQuery("[id^='LoadContent_']").each(function () {
            var id = jQuery(this).attr("id");
            var n = parseInt(id.replace("LoadContent_", ""), 10);
            if (!isNaN(n)) maxNum = Math.max(maxNum, n);
        });
		//jQuery("#LoadContent_"+maxNum ).hide();
		return "LoadContent_" + (maxNum + 1);
		/*if(maxNum == 1){
			return "LoadContent_" + maxNum;
		}else{
			return "LoadContent_" + (maxNum + 1);
		}*/

        
    }

    // If prevId is a proper LoadContent_N
    var num = parseInt(prevId.replace("LoadContent_", ""), 10);
    if (isNaN(num)) num = 0;

    return "LoadContent_" + (num + 1);
}


function convertJSONToDataString(persistentData)
{
	var pData= "";
	var names = Object.keys(persistentData);
	for(var i=0;i<names.length;i++ )
		pData += names[i] + '='+escape(persistentData[names[i]]) +'&';
	return pData;
}
function navigateToNext_SuccessCalBack(msg){	
	
	if(jQuery('#mstFrmHeader').length>0)
	{
		jQuery('#mstFrmHeader').html(getFrmTitle(jQuery('#submitForm').val()));
		jQuery('#mstFrmHeader').css({'margin-left':'1%'});
	}
	
	

}
function navigateToNext_ErrorCalBack(msg){
	if( formNavigations != null && formNavigations.length > 1 ){

		navigateToPrevForm();
	}	
}
function navigateToPrev_SuccessCalBack(msg)
{ 
	var removeForm = formNavigations.pop();

	//alert(" removeForm " +removeForm.divId + " prevForm.formId " + removeForm.formId);
	//jQuery('#'+removeForm.divId).remove();
	
	
	jQuery('#'+removeForm.divId).hide();
	jQuery('#'+removeForm.divId).html("");
		
	var prevForm = formNavigations.pop();
	//alert("formNavigations.length navigateToPrev_SuccessCalBack :"+ formNavigations.length);
	var prevURL = prevForm.URL + (prevForm.URL.indexOf('?') > -1 ? "&": '?') + prevForm.params;
	prevURL = prevURL.replace("%3F","?");
	
	//alert(" prevForm " +prevForm.divId + " prevForm.formId " + prevForm.formId);
	jQuery('#'+prevForm.divId).show();
	if( prevForm.formId != undefined && prevForm.formId != null && prevForm.formId.trim().length > 0 )
		jQuery('#submitForm').val(prevForm.formId);
	
	//jQuery('#hiddenUrl').val(prevForm.URL);
	setSubmitFormUrl(prevURL);
	formNavigations.push(prevForm);
	//alert("formNavigations.length navigateToPrev_SuccessCalBack :"+ formNavigations.length);
}

function navigateToPrev_ErrorCalBack(msg){
	var prevForm = formNavigations.pop();
	var curentForm = formNavigations.pop();
	formNavigations.push( curentForm);
	formNavigations.push( prevForm);
	jQuery('#'+prevForm.divId).show();
}

function navigateToPrevForm(result)
{
	clearCommonErrorMsg();
	var curentForm = null;
	//alert("formNavigations.length navigateToPrevForm(result) :"+ formNavigations.length);
	for(var curentForm1 in formNavigations) {
		curentForm = formNavigations.pop();
		var skipF = getFilterValue(curentForm.URL + '&','skipForm');
		if( skipF != "true"){
		break;
		}
	}
	//alert("url "+curentForm.URL+" caption- "+curentForm.caption+" params- "+curentForm.params+" divid- "+curentForm.divId);
	if( curentForm == null ){
	return;
	}

	var homePageUrl = jQuery("#hdnPrevoiusHomUrl").val();
	var currentUrl = curentForm.URL;
	currentUrl = currentUrl.split("?");
	if(currentUrl[0] == homePageUrl){
	formNavigations.push(curentForm);
	return false;
	}

	//if( )
	//alert( " cc " + currentUrl[0]);
	if(curentForm !=null ){
	var isHomePageC = getFilterValue(curentForm.URL + '&','isHomePage');
	if( isHomePageC == "true" ){

	jQuery('#'+curentForm.divId).show();
	/* if( jQuery('#'+curentForm.divId).length <= 0 )
	jQuery('#LoadContent').append('<div id="'+ curentForm.divId +'" > </div> ');
	jQuery('#'+curentForm.divId).html(jQuery("#homePageHiddenHtml").html());

	*/

	formNavigations.push(curentForm);
	return;
	}

	//jQuery('#'+curentForm.divId).hide();madhan
	
	var url = curentForm.URL + (curentForm.URL.indexOf('?') > -1 ? "&": '?') + curentForm.params;
	var curentFormId = getFilterValue(url + '&',"formId");
	var toolBarMode = getFilterValue(url + '&',"fromMode");
	var filterButton = getFilterValue(url + '&','filterButton');
	jQuery('#'+curentForm.divId).remove();
/*	if( filterButton == 'false')
	enableFilterBtn();*/

	/**Added By Manikandan for toolbar back **/
	if("reminder"==toolBarMode)
	{
		//alert("remainder comm.");
		jQuery('#reminder_content').html("");
		jQuery(".reminder_content_div").slideDown("slow");
		application_loadReminder();
	}
	else if("inbox"==toolBarMode)
	{
		//jQuery('#reminder_content').html("");
		jQuery(".alert_content_div").slideDown("slow");
		application_loadInbox();
	}
	//*alert(curentFormId);
	//if( currentUrl[0] == "prvnt_mntncform_input.prv");
	// popFormNavigation();


	if( curentFormId != null && curentFormId != "" && curentFormId.trim().length > 0){

	var beforeCloseCurrentFormCallBack = curentFormId +'_beforeCloseCurrentForm';

	var retValue = true;//dynamicFunctionCall(beforeCloseCurrentFormCallBack,null) ;
	try{
	if( typeof eval('('+beforeCloseCurrentFormCallBack +')') == 'function')
	{

	eval('( '+ beforeCloseCurrentFormCallBack +'())');
	}
	}catch(Exception){}

	if( retValue == false ) {
	resetErrMessg();
	formNavigations.push(curentForm);
	navigateToPrev_SuccessCalBack();
	return ;
	}
	}
	}
	//jQuery('#'+curentForm.divId).hide();madhan
	jQuery('#'+curentForm.divId).remove();

	var prevForm = null;//formNavigations.pop();
	for(var prevForm1 in formNavigations) {
	prevForm = formNavigations.pop();
	if( prevForm == undefined )
	break ;
	var skipF = getFilterValue(prevForm.URL + '&','skipForm');
	if( skipF != "true"){
	break;
	}
	}

	var prevURL ="";
	if( prevForm != undefined && prevForm != null ){
		if( prevForm.params != undefined && prevForm.params != "" && prevForm.params.length > 0)
			prevURL = prevForm.URL + (prevForm.URL.indexOf('?') > -1 ? "&": '?') + prevForm.params;
		else
			prevURL = prevForm.URL;
		prevURL = prevURL.replace("%3F","?");

		
		jQuery("#hiddenUrl").val(prevURL);
		
		if( prevForm.caption != null && prevForm.caption != '' )
		setFormMainHeader(prevForm.caption);
		
		formNavigations.push(prevForm);
		formNavigations.push(curentForm);
		
		var preLoadDivId = getFilterValue(prevURL+'&',"preLoadContentDivId");
		if( preLoadDivId == null && preLoadDivId == "" )
		preLoadDivId = "preLoadContent";
		
		/** added on 4-Feb-12 **/
		var formId = getFilterValue(prevURL + '&',"formId");
		
		if(getFilterValue(prevURL + '&',"OpenTab") != null && getFilterValue(prevURL+ '&',"OpenTab") != '' && getFilterValue(prevURL + '&',"OpenTab") != ' ')
		frmMode.openTab = getFilterValue(prevURL + '&',"OpenTab");
		if( formId != null && formId != "" && formId.trim().length > 0){
		var beforeLoadCurrentFormCallBack = formId +'_beforeLoadCurrentForm';
		var args = null ;
		if( result != null && result != undefined && result != 'undefined' )
			args = [ result];
		
		var retValue = true;//dynamicFunctionCall(beforeLoadCurrentFormCallBack,args) ;
			try{
			if( typeof eval('('+beforeLoadCurrentFormCallBack +')') == 'function')
			{
			
			eval('( '+ beforeLoadCurrentFormCallBack +'())');
			}
			}catch(Exception){}
		if( retValue == false )
		{
			return ;
		}
		else if( retValue == "OK"){
			navigateToPrev_SuccessCalBack();
			return ;
		}
		
	}
		
		var isHomePage = getFilterValue(prevForm.URL + '&','isHomePage');
		if( isHomePage == "true" ){
		jQuery('#'+prevForm.divId).show();
		/*if( jQuery('#'+prevForm.divId).length <= 0 )
		jQuery('#LoadContent').append('<div id="'+ prevForm.divId +'" > </div> ');
		jQuery('#'+prevForm.divId).html(jQuery("#homePageHiddenHtml").html());
		*/
		/** added By Manikandan for opening of menu during back click**/
		if( ! jQuery(".layout-split-west").is(":visible"))
		jQuery('#mainlayout').layout('expand','west');
		/** End **/
		setFormMainHeader("Home");
		
		jQuery("#filter_tab").hide();
		navigateToPrev_SuccessCalBack("");
		return;
		}
		/**^^^^^^^^^^ ^^^^^^^^^^^ **/
		var notReloadFrm = getFilterValue(prevURL,"notReloadFrm");
		if( notReloadFrm == true || notReloadFrm == "true" )
		return;
		LoadForm(prevForm.divId,preLoadDivId,prevURL,"dispErr","navigateToPrev_SuccessCalBack","navigateToPrev_ErrorCalBack");
	}
	else{
	jQuery("#hiddenUrl").val("");

	if( jQuery("#homePageHiddenHtml").html() == "" )
	openSetHomePage();
	else{
	//jQuery('#LoadContent_1').html(jQuery("#homePageHiddenHtml").html());
	}
	if( ! jQuery(".layout-split-west").is(":visible"))
	jQuery('#mainlayout').layout('expand','west');

	setFormMainHeader("Home");

	jQuery("#filter_tab").hide();
	}

	resetErrMessg();
}


function resetErrMessg(){
	jQuery('#dispFrontPageErr').css('display','none');
	jQuery('#dispFrontPageSuccess').css('display','none');
	jQuery('#footerSlideContent').hide();
	jQuery(this).css('backgroundPosition', 'top left');
	jQuery('#dispErr').css('display','none');
	setTimeout(function(){
		jQuery('#footerSlideContainer').css('z-index','-9999');
		},1150);
}
function processTree(treeId,treeAction,treeSearch)
{	
	treeId.jstree({
        "json_data" : {        	
                        "ajax" : {
                            "url" : treeAction,
                           
                            "data" : function (n) {                            	
                            	return {
                            		"operation" : "get_children", 
                                    "elementId" : n.attr ? n.attr("elementId") : 0,
                                    "parentId" : n.attr ? n.attr("parentId") : 0,
									"elementType" : n.attr ? n.attr("elementType") : 0,
									"id" : n.attr ? n.attr("id") : 0 
								};
                            },
                            "success":function(resp){
                                
                                if(! isArray(resp) )
                                {	
                           	 	if( resp !=null && resp.sExpires){
                              
                          		  		loadFormSessionTimeOut(resp, "", "");
                                  	}
                                }
                       	    	     
                               },
                           "error":function(resp){
                               if( resp != null && resp.sExpires){
                               	loadFormSessionTimeOut(resp);
                                }
                             } 
                           
                        }                   
		 //"progressive_render" : true
	//"progressive_unload" : true
            },
         "themes" : {
                    "theme" : "classic",
                    "dots" : true,
                    "icons" : true                            
                },
         "search" : {
             "case_insensitive" : true,
              "ajax" : {
                    "url" :treeSearch,//"searchnode.funlocn",
                    "data" : function (str) {
                    
                    	return {
                    	 	   "operation" : "search",
                    	       "search_str" : str                    	      
                    	}; 
                    },
                    "success":function(result){   
                    	
                    	 if( result != null && result.sExpires){
                            	loadFormSessionTimeOut(result);
                         }
                    	 else if( result == null ) {
                    		jQuery('#dispErr').html("Searching is done");
                     		div_err();
                    	 }
	                    var args = [ result ];
						dynamicFunctionCall(treeAction.split('.')[0]+'_searchCallBack', args);
                    },
                    "error":function(reponse){
                        if( reponse != null && reponse.sExpires){
                           	loadFormSessionTimeOut(reponse);
                            }
                    }
              }
	                 
	     },
	    /* "ui" : {
	            // this makes the node with ID node_4 selected onload
	    	             "initially_select" : [ "0" ]
	    	        },*/
	    // "core" : { "initially_open" : [ "root" ] },
                
    	 "plugins" : [ "themes", "json_data", "ui","hotkeys","contextmenu","search"],
    	  
    	
         "contextmenu":{"items": customMenu}  	 
    	            	 
	  });
}

/*function imageUpload(imgId,imgAction,imgName)
{
	 new AjaxUpload(imgId, {
			action: imgAction,
			name: imgName,
			onSubmit: function(file, extension) {
				if (!(extension && /^(jpg|png|jpeg|gif)$/i.test(extension))){
					
					return false;
				}
				//else
				
			},
			dataType: "json",
			onComplete: function(file, response) {
				afterComplete(response);
			
			}
		});
}
*/

var imagArr = [];

/*function imageUpload(dlgId,imgAction,imgName,bindImgId, hdnSaveImgId,width,height,isaddtop)
{ 
	if( imgAction == undefined || imgAction == null)
	{
		imgAction = 'ImageUpload.commonFilter';
	}
	if( imgAction.indexOf("?")<0)
		imgAction += "?";
	
	if( width != undefined && width != null && width.length > 0)
		imgAction += "width="+width;
	
	if( height != undefined && height != null && height.length > 0)
		imgAction += "&height="+height;
	
	var id = jQuery(dlgId).attr("id");
	 // alert(id+"  -  "+imgAction+"  -  "+imgName+"  -  "+bindImgId+"  -  "+ hdnSaveImgId);
	 //jQuery("input[type=file][name="+ id +"]" ).trigger('click');
	 jQuery("input[type=file][name="+ id +"]" ).remove();
	    
		var ajaxUplod = new AjaxUpload(dlgId, {		
			action: imgAction, // 'ImageUpload.commonFilter',
			name: imgName,
			 xhr:window.ActiveXObject?
				    	function(){
				    	   try{
				    		   return new window.ActiveXObject("Microsoft.XMLHTTP")
				    		   }
				    	   catch(e){}
				    	   }:function(){return new window.XMLHttpRequest()},
			onChange:function(file,extension){ 
				if (!(extension && /^(jpg|png|jpeg)$/i.test(extension))){
					alert('Select JPG, PNG, JPEG File to Upload ');
					return false;
				}
			},	
			onSubmit: function(file, extension) {
				 
				jQuery(this._input).css("z-index","-1");
				jQuery('#'+bindImgId).addClass('loadingGridRow');
				if( isaddtop == undefined  || isaddtop == null || isaddtop == true)
					jQuery('.loadingGridRow').css("margin-top","25");
				if (!(extension && /^(jpg|png|jpeg)$/i.test(extension))){
					alert('Select JPG, PNG, JPEG File to Upload ');
					return false;
				}
				//jQuery("input:file[name='"+ imgName +"']").remove();
				//jQuery(" input[type='file' name='"+ imgName +"']").remove();
			    //jQuery("input:file[name='"+ imgName +"']").ajaxupload('destroy');
			    //jQuery('input[type=file]').ajaxupload('destroy');
			},
			dataType: "json",
			onComplete: function(file, response) { 
			jQuery('#'+bindImgId).removeClass('loadingGridRow');
				jQuery(this._input).css("z-index","-1");
				//jQuery(" input[type=file name='"+ imgName +"']").remove();
			//	jQuery("input:file[name='"+ imgName +"']").ajaxupload('destroy');
				var funName = imgName+"OnComplete";
				response = response.replace("<pre>","").replace("</pre>","");
				// alert(response);
				jQuery('#'+bindImgId).attr('src', response);
				 
				if( hdnSaveImgId!=null  )
					jQuery("#"+hdnSaveImgId).val(response);
				var  callback =  funName +'(response)';
				 //alert(callback);
				var args = null;
				//dynamicFunctionCall(callback, args);
				*Changed for IE
				 if( typeof eval('('+funName +'(response)' +')') == 'function' )	
					eval( funName +'(response)' ); 
			
			}
			
		});
		
		
		jQuery("input[type=file][name="+ id + "]").bind("blur",function(){
			jQuery(this).hide();
		});
		
		jQuery("input[type=file][name="+ id + "]").css("z-index","-1");
		
		jQuery(dlgId).bind("keydown" ,function(e){
			if(e.keyCode == 13)
				jQuery("input[type=file][name="+id+"]" ).trigger("click");
		});
		jQuery(dlgId).click(function(){ 
			 jQuery("input[type=file][name="+id+"]" ).trigger("click");
		});
		jQuery(dlgId).bind("blur" ,function(){
			jQuery("input[type=file][name="+id+"]" ).hide();
		});
		
		return ajaxUplod;

}*/

//madhan
function imageUpload(
    dlgId,
    imgAction,
    imgName,
    bindImgId,
    hdnSaveImgId,
    width,
    height,
    isaddtop
) {
    if (!imgAction) {
        imgAction = 'ImageUpload.commonFilter';
    }
	//alert(bindImgId);

    var params = [];
    if (width) params.push("width=" + width);
    if (height) params.push("height=" + height);

    if (params.length > 0) {
        imgAction += (imgAction.indexOf("?") === -1 ? "?" : "&") + params.join("&");
    }

    var $dlg = jQuery(dlgId);
    var id = $dlg.attr("id");

    // remove old file input
    jQuery("#" + id + "_file").remove();

    // create hidden file input
    var $fileInput = jQuery(
        '<input type="file" accept=".jpg,.jpeg,.png" style="display:none;">'
    ).attr("id", id + "_file");

    jQuery("body").append($fileInput);

    $fileInput.on("change", function () {
		//alert(1);
        var file = this.files[0];
		//alert(2);
        if (!file) return;
        // alert(file.name);
        var ext = file.name.split('.').pop().toLowerCase();
        if (!/^(jpg|jpeg|png)$/.test(ext)) {
            alert("Select JPG, PNG, JPEG File to Upload");
            this.value = "";
            return;
        }

        jQuery('#' + bindImgId).addClass('loadingGridRow');
        if (isaddtop !== false) {
            jQuery('.loadingGridRow').css("margin-top", "25px");
        }

        var formData = new FormData();
        formData.append(imgName, file);

        jQuery.ajax({
            url: imgAction,
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                jQuery('#' + bindImgId).removeClass('loadingGridRow');

                // handle <pre> wrapped response (legacy server)
                if (typeof response === "string") {
                    response = response.replace("<pre>", "").replace("</pre>", "");
                }

                jQuery('#' + bindImgId).attr('src', response);

                if (hdnSaveImgId) {
                    jQuery("#" + hdnSaveImgId).val(response);
                }

                // dynamic callback: imgName + "OnComplete"
                var fn = window[imgName + "OnComplete"];
                if (typeof fn === "function") {
                    fn(response);
                }
            },
            error: function () {
                jQuery('#' + bindImgId).removeClass('loadingGridRow');
                alert("Image upload failed");
            }
        });
    });

    // trigger upload on dialog click / Enter key
    $dlg.off("click.upload").on("click.upload", function () {
        $fileInput.trigger("click");
    });

    $dlg.off("keydown.upload").on("keydown.upload", function (e) {
        if (e.keyCode === 13) {
            $fileInput.trigger("click");
        }
    });

    return $fileInput;
}

function setImgWidth(imgId,w,h)
{
	imgId.removeAttr("width"); 
	imgId.removeAttr("height");
	imgId.attr('width', w);
	imgId.attr('height', h);
	/*if(imgId.width() < w )
		imgId.attr('width', imgId.width() );
	else
		imgId.attr('width', w);
	if(imgId.height() < h )
		imgId.attr('height', imgId.height() );
	else
		imgId.attr('height', h);*/
	
}



function numericTextBox(id, allowMinus)
{
	/*jQuery('#'+id).keydown(function(event) {
	  if( !(event.keyCode == 8                                // backspace
		        || event.keyCode == 46                              // delete
		        || (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		        || (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		        || (event.keyCode >= 96 && event.keyCode <= 105))   // number on keypad
		        ) {
		            event.preventDefault();     // Prevent character input
		    }
		    
	});	
	jQuery('#'+id).keypress(function(event) {
		
		var retVal =  AlphaNumericOnly(event,false,true);
		
		if ( ! retVal )
		{	
			
		 	event.preventDefault();
			return false;
		}	
	});
	*/
	jQuery('#'+id).keydown(function(event) {
        // Allow: backspace, delete, tab and escape
		var cVal = jQuery(this).val();
		
		if(   (  event.keyCode === 190 || event.keyCode == 110) )
		{
			if(( cVal != undefined 
            		&& cVal != null && cVal.indexOf(".") > -1 ))
				event.preventDefault();
            		
		}
		
		else if ( ( (allowMinus != undefined && allowMinus==true) && event.keyCode == 109  ) ||
        ( (allowMinus != undefined && allowMinus==true) && event.keyCode == 173  ) ) {
			if(( cVal != undefined 
            		&& cVal != null && cVal.indexOf("-") > -1  ))
				event.preventDefault();
		}
		
		else if(event.keyCode === 46 ||  event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 110 ||
		
			// Allow: + and -
	        ( (allowMinus != undefined && allowMinus==true) && event.keyCode == 109  ) ||
	        ( (allowMinus != undefined && allowMinus==true) && event.keyCode == 173  ) ||
            // Allow: Ctrl+A
	        (event.keyCode == 65 && event.ctrlKey === true) || 
             // Allow: home, end, left, right
            (event.keyCode >= 35 && event.keyCode <= 39))  {
                 // let it happen, don't do anything
    		
                 return;
        }
        
        else {
            // Ensure that it is a number and stop the keypress
        	
            if(  ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )  ) ){
            	
            	event.preventDefault();
            }   
        }
    }).css("text-align","right");
	

	

}



function enableTextBoxInComboSelect(comboId,textBoxId,disable,enable)
{
	jQuery("#"+comboId).combobox({
		onSelect:function(recordid){					
		 if(recordid.id == disable)
		 {
		 	jQuery("#"+textBoxId).val("");		
		 	jQuery("#"+textBoxId).attr('readonly','readonly');
		 	jQuery("#"+textBoxId).css('background-color', '#D1E2FD');
		 }
		  if(recordid.id == enable)
		  {
		  	jQuery("#"+textBoxId).attr('readonly',false);
		  	jQuery("#"+textBoxId).css('background-color', '#ffffff');
		  }
		}
				
	});
}

function getMonthStringFromInt(i)
{
	var mon=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
	
	return mon[i];
	
}
/*
 *  
 * ---------
 */

function readOnlyFieldsWithText(fieldId,displayTxt)
{
	jQuery("#"+fieldId).val(displayTxt);
	jQuery("#"+fieldId).attr('readonly','readonly');
 	jQuery("#"+fieldId).css('background-color', '#D1E2FD');
}

function fillWithCurrentDate(fieldId)
{
	//var currentTime = new Date();
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	var month = currentTime.getMonth();	
	
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
		month = getMonthStringFromInt(month);		
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}
		
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',day+'-'+month+'-'+year);		
	
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).val(hours + ":" + minutes);
	else
		jQuery("#"+fieldId).val(day+'-'+month+'-'+year);
}


function getCurrentDate()
{
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	var month = currentTime.getMonth();	
	
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
		month = getMonthStringFromInt(month);		
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}

	return day+'-'+month+'-'+year;
}

function fillWithCurrentMonth(fieldId)
{
	var serverTime = srvTime();
	 var dateTime = new Date(serverTime);
	var currentTime = dateTime; // new Date();
	var month = currentTime.getMonth();	
	
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
		month = getMonthStringFromInt(month);		
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}
		
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',day+'-'+month+'-'+year);		
	
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).val(hours + ":" + minutes);
	else
		jQuery("#"+fieldId).val(month+'-'+year);
}

function clearCommonErrorMsg()
{
	jQuery("#dispErr").html('');
}


function multiSelectPop(dataUrl,condition,gridId,rowId,colNames,isMultiselect,multiSelectCancel_Callback,multiSelectOk_Callback,title,formHeight, formWidth){
	
	jQuery( "#multiselectPopUpId" ).show();
	jQuery( "#multiselectPopUpId" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: formHeight ? formHeight : 480 ,
			width: formWidth ? formWidth : 500,
			left:20,
			top:90,
			modal: true,
			title:title,
			onClose:function(){	
				
				var closeCallback = gridId+"_multiselectPopUpId_onClose";
				var args = null;
				dynamicFunctionCall(closeCallback, args);
				jQuery('#hdnPopupUrl').val(' ');
				jQuery('#hdnPopupUrl').html(' ');
				//fnClearTeamEmp(divId);	
				}
	});
	jQuery('.panel-tool-close').filter(function(){
		
		if( jQuery(this).parent().parent().next().attr("id") == "multiselectPopUpId" ) 
			return true;
		return false;	
	}).click(function(event){
		var closeCallback = gridId+"_multiselectPopUpId_onClose";
		var args = null;
		dynamicFunctionCall(closeCallback, args);
	});
	
	
	var url = "mutliSelect_input.mselect?" + 'dataUrl='+dataUrl+'&condition='+ condition +'&gridId='+gridId +'&rowId='+rowId +'&colNames='+colNames +'&isMultiselect='+isMultiselect+'&multiSelectCancel_Callback='+multiSelectCancel_Callback+"&multiSelectOk_Callback="+multiSelectOk_Callback;
	
	LoadForm("loadMultiSelectPopUp","preloadMultiSelect",url,"dispErr","multiSelectpop_successCallback");
	
}


function multiSelectpop_successCallback(response){
	if( response.dataNotExist  ){
		showCommonErrorMsg(response.dataNotFoundMsg);
		jQuery( "#multiselectPopUpId" ).dialog("close");
	}		

}
/*
 * 
 */
function JqGridToJsonSelRowsReqCols(jqGridId,ckeckForSelColName,requiredColArr)
{
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var reqColLength=requiredColArr.length;
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		if( value != null  &&  value.trim()  != "" &&  value.trim().length  != "0")
		{
			jsonArrO += '{';
			for(var colName in row) 
			{
				for(var j=0;j<reqColLength;j++)
				{
					if(requiredColArr[j]== colName )
					{
						var cellValue = parseJqGridCellValue(row[colName]);
						//alert(cellValue.trim().length);
						if( cellValue.trim().length > 0 && cellValue!=" " ) 
							jsonArrO += '"'+colName +'":"' + cellValue +'",';
					}					
				}
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
}			
function JqGridToJsonSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		if( value != null  &&  value.trim()  != "" &&  value.trim()  != "0"){
			
			jsonArrO += '{';
			
			for(var colName in row) {
				
				if( checkBoxColName != colName ){
					var cellValue = parseJqGridCellValue(row[colName]);
					
					jsonArrO += '"'+colName +'":"' + cellValue +'",';
				}	
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
}

function parseJqGridCellValue(value){
	// alert("caps   "+ value.indexOf("<INPUT"));
	// alert("lower  "+ value.indexOf("<input") );
	if( value.indexOf("<INPUT") >= 0 || value.indexOf("<input") >= 0 || value.indexOf("<select") >= 0 || value.indexOf("<textarea") >= 0 ){
		
		var id = parseInputJqGridTrString(value);	
	
		value = getFieldValue(id);
		
	}
	
	if( value.indexOf("<span") >= 0 ){
			
		value = "";
	}
	return value;
}

function parseInputJqGridTrString(value){
	var id = value.substr(value.indexOf("id=")+4);

	if( id.length > 0){
		if( id.indexOf("'") < id.indexOf(" ") && id.indexOf("'") != -1)
			id= id.substr(0,id.indexOf("'"));
		else
			id= id.substr(0,id.indexOf('"'));
	}
	
	return id;
}
function JqGridInputToJsonSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];		
		var value = row[ckeckForSelColName];
		
		if( value != null  &&  value.trim()  != ""){
			
			jsonArrO += '{';
			
			for(var colName in row) {
				
				if( checkBoxColName != colName )
					if(row[colName].substring(0,5)!='<span')
						if(row[colName].substring(0,7)!='<select')		
							if(row[colName].substring(0,4)!=',Yes')
								if(row[colName].substring(0,2)!=',Y')
							jsonArrO += '"'+colName +'":"' + row[colName].trim() +'",'; 
				
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = jsonArrO.length > 1 ?jsonArrO:"";
	
	return jsonArrO; 
}

function convertJqGridToJSONObjectArr(jqGridId){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		jsonArrO += '{';
		
		for(var colName in row) {
			jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
		}
		jsonArrO = jsonArrO.slice(0, -1) + "},"; 
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO; 
}
/*
 * 
 */
/*function convertJqGridToJSONStringArr(jqGridId){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		jsonArrO += '[';
		
		for(var j = 0; j<row.length;j++) {
		
			jsonArrO += '"' + row[j ]+'",'; 
		}
		jsonArrO = jsonArrO.slice(0, -1) + "],"; 
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	
	return jsonArrO; 
}
*/
function convertJqGridToJSONStringArr(jqGridId){
	
	var rowIds = jQuery("#"+jqGridId).jqGrid('getDataIDs');

	var rowObject = jQuery("#"+jqGridId).getRowData(rowIds[0]);
	var totalCol = 0;
	for(var col in rowObject) totalCol++;
	
	var jsonArrO='[';
	
	for( var i = 0; i < rowIds.length;i++){
		jsonArrO += '[';
		
		for(var j = 0; j<totalCol;j++) {
			
			jsonArrO += '"' + jQuery("#"+jqGridId).jqGrid('getCell',rowIds[i],j) +'",'; 
		}
		jsonArrO = jsonArrO.slice(0, -1) + "],"; 
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";

	return jsonArrO; 
}

function enableFieldsInCheckBoxSelect(chbId,fieldId)
{
	jQuery('#'+chbId).click(function() {
		
		if(jQuery('#'+chbId).is(':checked') == true)
			enableFields(fieldId);
		else
			readOnlyFields(fieldId);
	});
}

function setFunctionalLocWidth(formId,width){
	jQuery('#'+formId +' div[id=dispFunctionalLoc]').css('width',width);
}


/*function readOnlyFields(fieldId)
{		
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("disable");
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("disable");
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner("disable");
	else if(fieldId.substring(0,3) == "chk")
		jQuery("#"+fieldId).attr("disabled",true);
	else if(fieldId.substring(0,3) == "chb")
		jQuery("#"+fieldId).attr("disabled",true);
	else if(fieldId.substring(0,3) == "cbo")
		jQuery("#"+fieldId).attr("disabled",true);
	else
		jQuery("#"+fieldId).attr('readonly','readonly'); 
		jQuery("#"+fieldId).css('background-color', '#ece9d8');
}*/

function readOnlyFields(fieldId)
{		
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("readonly", true);
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("readonly", true);
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner("readonly", true);
	else if(fieldId.substring(0,3) == "chk"){
		var hiddenId = "hdn" + fieldId.substring(3); // chkResult → hdnResult
		var hidden = jQuery("#" + hiddenId);
		
		if (jQuery("#"+fieldId).is(":checked")) {
		    hidden.val(jQuery("#"+fieldId).val());
		} else {
		    hidden.val("");
		}
	hidden.prop("disabled", false);
	jQuery("#"+fieldId).prop("disabled",true);
	}	
	else if(fieldId.substring(0,3) == "chb")
		jQuery("#"+fieldId).prop("readonly",true);
	else if(fieldId.substring(0,3) == "cbo")
		jQuery("#"+fieldId).prop("readonly",true);
	else
		jQuery("#"+fieldId).prop('readonly','readonly'); 
		jQuery("#"+fieldId).css('background-color', '#ece9d8');
}

/*function disableField(formID, fieldId)
{
	
	if(fieldId.substring(0,3) == "cmb"){
		
		jQuery("#"+formID + ' input[id=' +fieldId + ']').combobox("disable");
		jQuery("#"+formID + ' input[id=' +fieldId + ']').css('color','#000000');
	}
	else if(fieldId.substring(0,3) == "dte"){
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').datebox("disable");
	}
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+formID + ' input[id=' +fieldId +']').spinner("disable");
	else if(fieldId.substring(0,3) == "cbo"){	
		jQuery("#"+formID + ' select[id=' +fieldId +']').attr('disabled',"disabled");
		}
	else if(fieldId.substring(0,3) == "chk"){	
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').attr('disabled',true);
	}
	else if(fieldId.substring(0,3)=="div")	{	
		jQuery("#"+formID + ' div[id=' +fieldId +'  ] :input ').attr('disabled', true);
		jQuery("#"+formID + ' div[id=' +fieldId +'  ] ').css('background-color', '#D1E2ff');
	}	
	else if(fieldId.substring(0,3)=="txt")	{
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').attr('disabled','disabled');
	}
else if(fieldId.substring(0,3)=="btn")	{
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').attr('disabled','disabled');
	}
	jQuery("#"+formID + ' input[id=' +fieldId +']').css('background-color', '#D1E2FD');
	
}*/

function disableField(formID, fieldId)
{
	
	if(fieldId.substring(0,3) == "cmb"){
		
		jQuery("#"+formID + ' input[id=' +fieldId + ']').combobox("readonly", true);
		jQuery("#"+formID + ' input[id=' +fieldId + ']').css('color','#000000');
	}
	else if(fieldId.substring(0,3) == "dte"){
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').datebox("readonly", true);
	}
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+formID + ' input[id=' +fieldId +']').spinner("readonly", true);
	else if(fieldId.substring(0,3) == "cbo"){	
		jQuery("#"+formID + ' select[id=' +fieldId +']').attr('readonly',true);
		
		/*var obj = jQuery("#" + formID + ' select[id=' + fieldId + ']');
		obj.css("pointer-events","none");
		    obj.css("background-color","#eeeeee");*/
		}
	else if(fieldId.substring(0,3) == "chk"){	
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').attr('disabled',true);
	}
	else if(fieldId.substring(0,3)=="div")	{	
		jQuery("#"+formID + ' div[id=' +fieldId +'  ] :input ').attr('readonly', true);
		jQuery("#"+formID + ' div[id=' +fieldId +'  ] ').css('background-color', '#D1E2ff');
	}	
	else if(fieldId.substring(0,3)=="txt")	{
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').attr('readonly',true);
	}
else if(fieldId.substring(0,3)=="btn")	{
		
		jQuery("#"+formID + ' input[id=' +fieldId +']').attr('disabled',true);
	}
	jQuery("#"+formID + ' input[id=' +fieldId +']').css('background-color', '#D1E2FD');
	
}

/*function enableFields(fieldId)
{	
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("enable");
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("enable");
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner("enable");
	else if(fieldId.substring(0,3) == "chk")
		jQuery(' input[id=' +fieldId +']').attr('disabled',false);	
	else if(fieldId.substring(0,3) == "chb")
		jQuery(' input[id=' +fieldId +']').attr('disabled',false);	
	else if(fieldId.substring(0,3) == "txt") {
		jQuery(' input[id=' +fieldId +']').removeAttr('disabled');
		jQuery("#"+fieldId).attr('readonly',false);
	}
	else
		jQuery("#"+fieldId).attr('readonly',false); 	
	jQuery("#"+fieldId).css('background-color', '#FFFFFF');
	jQuery("#"+fieldId + ' ' +fieldId).css('background-color', 'white');
}*/

function enableFields(fieldId)
{	
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox('readonly',false);
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox('readonly',false);
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner('readonly',false);
	else if(fieldId.substring(0,3) == "chk"){
			var hiddenId = "hdn" + fieldId.substring(3); // chkResult → hdnResult
			var hidden = jQuery("#" + hiddenId);
		hidden.prop("disabled", true);
		jQuery(' input[id=' +fieldId +']').prop('disabled',false);
		}		
	else if(fieldId.substring(0,3) == "chb")
		jQuery(' input[id=' +fieldId +']').attr('readonly',false);	
	else if(fieldId.substring(0,3) == "txt") {
		jQuery(' input[id=' +fieldId +']').removeAttr('readonly');
		jQuery("#"+fieldId).attr('readonly',false);
	}
	else
		jQuery("#"+fieldId).attr('readonly',false); 	
	jQuery("#"+fieldId).css('background-color', '#FFFFFF');
	jQuery("#"+fieldId + ' ' +fieldId).css('background-color', 'white');
}

/*function enableFormFields(formId, fieldId)
{
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+formId + ' input[id=' +fieldId +']').combobox("enable");
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+formId + ' input[id=' +fieldId+']').datebox("enable");
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+formId + ' input[id=' +fieldId+']').spinner("enable");
	else if(fieldId.substring(0,3) == "cbo"){	
		jQuery("#"+formId + ' select[id=' +fieldId +']').attr('disabled',false);
		}
	else
		jQuery("#"+formId + ' input[id=' +fieldId+']').attr('readonly',false); 	
}*/
// chla
function enableFormFields(formId, fieldId)
{
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+formId + ' input[id=' +fieldId +']').combobox('readonly',false);
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+formId + ' input[id=' +fieldId+']').datebox('readonly',false);
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+formId + ' input[id=' +fieldId+']').spinner('readonly',false);
	else if(fieldId.substring(0,3) == "cbo"){	
		jQuery("#"+formId + ' select[id=' +fieldId +']').attr('readonly',false);
		}
	else
		jQuery("#"+formId + ' input[id=' +fieldId+']').attr('readonly',false); 	
}

function enableForm(formId)
{
	var inputs = jQuery('#' + formId + ' :input ');
	jQuery(inputs).each(function () {
		enableFormFields(formId,this.id);
	});
}


function getFieldValue(fieldId, formId)
{

	var val =null;
	if( formId == null || formId.length <= 0){
		if(fieldId.substring(0,3) == "dsp"){			
			val = jQuery("#"+fieldId).datebox("getValue");
			fieldId = fieldId.replace("dsp","spn");			
			val += " "+jQuery("#"+fieldId).spinner('getValue');			
		}
		else if(( jQuery("#"+ fieldId).hasClass("easyui-combo") || 
				jQuery("#"+ fieldId).hasClass("easyui-combobox") ) && 
				jQuery("#"+ fieldId).hasClass("combobox-f")) //fieldId.substring(0,3) == "cmb")
		{	
			var cmbOpts = jQuery("#"+fieldId).combobox('options');
			if(cmbOpts.multiple )
				val = jQuery("#"+fieldId).combobox('getValues');
			else
				val = jQuery("#"+fieldId).combobox('getValue');
		}	
		else if(jQuery("#"+ fieldId).hasClass("easyui-datebox") ||
				jQuery("#"+ fieldId).hasClass("datebox-f") ) //fieldId.substring(0,3) == "dte")
			val = jQuery("#"+fieldId).datebox("getValue");
		else if(jQuery("#"+ fieldId).hasClass("spinner-text"))
			val = jQuery("#"+fieldId).spinner('getValue');
		else if(jQuery("#"+ fieldId).is(':checkbox')){
			val = jQuery('#'+fieldId).is(':checked');
		}
		else
			val = jQuery("#"+fieldId).val();
	}
	else{
		if(fieldId.substring(0,3) == "dsp"){			
			val = jQuery("#"+formId + ' input[id='+fieldId+']').datebox("getValue");
			fieldId = fieldId.replace("dsp","spn");
			val += jQuery("#"+formId + ' input[id='+fieldId+']').spinner('getValue');			
		}
		else if( ( jQuery("#"+ formId + ' input[id='+fieldId+']').hasClass("easyui-combo") || 
				jQuery("#"+ formId + ' input[id='+fieldId+']').hasClass("easyui-combobox") )&& 
				jQuery("#"+ formId + ' input[id='+fieldId+']').hasClass("combobox-f") )//fieldId.substring(0,3) == "cmb")
		{
			var cmbOpts = jQuery("#"+ formId + ' input[id='+fieldId+']').combobox('options');
			if(cmbOpts.multiple )
				val = jQuery("#"+ formId + ' input[id='+fieldId+']').combobox('getValues');
			else
				val = jQuery("#"+ formId + ' input[id='+fieldId+']').combobox('getValue');
		}	
		else if(jQuery("#"+ formId + ' input[id='+fieldId+']').hasClass("easyui-datebox")
				||jQuery("#"+ formId + ' input[id='+fieldId+']').hasClass("datebox-f"))
			val = jQuery("#"+formId + ' input[id='+fieldId+']').datebox("getValue");
		else if(jQuery("#"+ formId + ' input[id='+fieldId+']').hasClass("spinner-text"))
			val = jQuery("#"+ formId + ' input[id='+fieldId+']').spinner('getValue');
		else
			val = jQuery("#"+ formId + ' input[id='+fieldId+']').val();
	}		
	return val;
}

function setGridCell(gridId,rowId,colIndex,value){
	jQuery('#'+gridId).jqGrid().setCell(rowId,colIndex,value);
}
function getGridCell(gridId,rowId,colIndex){
	return jQuery('#'+gridId).jqGrid().getCell(rowId,colIndex);
}


function setFieldValue(fieldId, value,formId)
{
	
	if( formId == null || formId.length <= 0){
		if( (jQuery("#"+ fieldId).hasClass("easyui-combo") || jQuery("#"+ fieldId).hasClass("easyui-combobox"))
					&& jQuery("#"+ fieldId).hasClass("combobox-f"))//fieldId.substring(0,3) == "cmb")
		{	
			var cmbOpts = jQuery("#"+fieldId).combobox('options');
			if(cmbOpts.multiple ){
				var arrayVal = value.split(",");
				jQuery("#"+fieldId).combobox("setValues",arrayVal);
			}	
			else{
				setComboValueSilent(fieldId, value);
				//jQuery("#"+fieldId).combobox("setValue",value);
			}
				

			var url = cmbOpts.url;

			url = removeValueFromUrl(url, "combokey");
			if( url.indexOf("?") > 0 )
				url += "&";
			else
				url += "?";
			url += "combokey="+value;
			if( value != undefined && value.trim().length>0)
				reloadCombo(null,fieldId,url);
			
		}
		else if(fieldId.substring(0,3) == "dte")
			jQuery("#"+fieldId).datebox("setValue",value);
		else
			jQuery("#"+fieldId).val(value);
	}
	else{
		if( ( jQuery("#"+ formId + ' input[id='+fieldId+"]").hasClass("easyui-combobox") || jQuery("#"+ formId + ' input[id='+fieldId+"]").hasClass("easyui-combo") ) &&
				jQuery("#"+ formId + ' input[id='+fieldId+"]").hasClass("combobox-f") ){//fieldId.substring(0,3) == "cmb")
			//jQuery("#"+ formId + ' input[id='+fieldId+"]").combobox("setValue",value);
			setComboValueSilent(formId + ' input[id='+fieldId+"]", value);
			var cmbOpts = jQuery("#"+ formId + ' input[id='+fieldId+"]").combobox('options');
			if(cmbOpts.multiple ){
				var arrayVal = value.split(",");
				jQuery("#"+ formId + ' input[id='+fieldId+"]").combobox('setValues',arrayVal);
			}	
			else{
				setComboValueSilent(formId + ' input[id='+fieldId+"]", value);
				//jQuery("#"+ formId + ' input[id='+fieldId+"]").combobox('setValue',value);
			}
				

			var url = cmbOpts.url;

			url = removeValueFromUrl(url,"combokey");
			if( url.indexOf("?") > 0 )
				url += "&";
			else
				url += "?";

			url += "combokey="+value;

			if( value != undefined && value.trim().length>0)
				reloadCombo(formId,fieldId,url);
		}	
		else if(jQuery("#"+ formId + ' input[id='+fieldId+"]").hasClass("easyui-datebox")
				||jQuery("#"+ formId + ' input[id='+fieldId+"]").hasClass("datebox-f"))
			jQuery("#"+formId + ' input[id='+fieldId+"]").datebox("setValue",value);
		else
			jQuery("#"+ formId + ' input[id='+fieldId+"]").val(value);
	}
	/*var comboKey = "";	
	var comboopts = '';
	var url = '';
	if( formId == null || formId.length <= 0){ 
		//comboopts = jQuery(" input[id=" +  fieldId +"]").combobox("options");
	}
	else{
		  
		comboopts = jQuery("#"+formId + " input[id=" +  fieldId +"]").combobox("options");
		comboKey = getFilterValue( comboopts.url+"&","combokey");
		if(comboKey.trim().length>0 )
			url = comboopts.url;
		else
			comboopts.url+value;
		 
		reloadCombo(formId,fieldId,url);
		//alert((formId+" -  "+ fieldId+" -  "+ comboopts.url+value);
	}
	 */
}



function removeValueFromUrl(url,identifier){
	var sIndex = url.indexOf(identifier);
	if(sIndex > -1 ){
		var endInd = url.indexOf("&",sIndex) == -1? url.length:(url.indexOf("&",sIndex)+1);
		var str = url.substring(sIndex,endInd);
		var urlL = url.replace(str,"")  ;
		urlL = urlL.replace("&&","&")  ; /********added by sugunadevi.s********/
		return urlL;
	}
	return url;
}
function fillWithCurrentMonth(fieldId)
{
	
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	//var currentTime = new Date();
	var month = currentTime.getMonth();
	var year = currentTime.getFullYear();	
	month = getMonthStringFromInt(month);
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',month+'-'+year);	
}

function fillWithPrevMonth(fieldId,noofmonth)
{
	
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	//var currentTime = new Date();
	var month = currentTime.getMonth();
	var year = currentTime.getFullYear();	
	month = getMonthStringFromInt(month-noofmonth);
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',month+'-'+year);	
}

function fillWithCurrentDate(fieldId)
{
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);

	//var currentTime = new Date();
	var month = currentTime.getMonth();
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();	
		month = getMonthStringFromInt(month);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	
	if (minutes < 10){
		minutes = "0" + minutes;
	}		
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',day+'-'+month+'-'+year);		
	else if(fieldId.substring(0,3) == "spn")		
		displayText(fieldId,hours + ":" + minutes);//jQuery("#"+fieldId).val(hours + ":" + minutes);		
	else
		jQuery("#"+fieldId).val(day+'-'+month+'-'+year);
}
/* added by Prabhu K ON 19 NOV 2014*/
function fillWithCurrentDatePloneeighty(fieldId)
{
	
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	 currentTime.setMonth(currentTime.getMonth() + 6);
	//var currentTime = new Date();
	var month = currentTime.getMonth();
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();	
		month = getMonthStringFromInt(month);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	
	if (minutes < 10){
		minutes = "0" + minutes;
	}		
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',day+'-'+month+'-'+year);		
	else if(fieldId.substring(0,3) == "spn")		
		displayText(fieldId,hours + ":" + minutes);//jQuery("#"+fieldId).val(hours + ":" + minutes);		
	else
		jQuery("#"+fieldId).val(day+'-'+month+'-'+year);
}

/*added on may 02 2012*/
function fillWithCurrentDatePlOne(fieldId)
{
	
	var currentTime = new Date();
	var month = currentTime.getMonth();
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();	
		month = getMonthStringFromInt(month);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	
	if (minutes < 10){
		minutes = "0" + minutes;
	}	
	if(day == 28 && month == "Feb")
		var adDay = 1;
	
	if(day == 30 || day == 31){
		
		var adDay = 1;
	}
	else
		var adDay = day+1;
		
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',adDay+'-'+month+'-'+year);		
	else if(fieldId.substring(0,3) == "spn")		
		displayText(fieldId,hours + ":" + minutes);//jQuery("#"+fieldId).val(hours + ":" + minutes);		
	else
		jQuery("#"+fieldId).val(adDay+'-'+month+'-'+year);
}

function convertStringToDate(dateTimeStr)
{
	dateTimeStr = dateTimeStr.substring(1,2) == "-"? "0"+dateTimeStr:dateTimeStr;

	var hour="00" ;
	var min= "00";
	var d=new Date(year,month,day,hour,min);
	var day=dateTimeStr.substring(0,2);
	var month=dateTimeStr.substring(3,6);
	var year=dateTimeStr.substring(7,11);
	if( dateTimeStr.length > 12)
		hour=dateTimeStr.substring(11,13);
	if( dateTimeStr.length > 15)
		min=dateTimeStr.substring(14,16);
	
	month=changeFormatStringtoNumber(month);	
	
	var d=new Date(year,month,day,hour,min);
	
	return d;	
}

function compareDateTime(fromDateTimeStr,toDateTimeStr)
{	
	if(convertStringToDate(fromDateTimeStr)>=convertStringToDate(toDateTimeStr))
		return -1;
	else
		return 1;
}
function compareDate(fromDateTimeStr,toDateTimeStr)
{	
	if(convertStringToDate(fromDateTimeStr)>convertStringToDate(toDateTimeStr))
		return -1;
	else
		return 1;
}

function timeDifference(firstDateTimeStr,secDateTimeStr)
{	
	oDiff = getTimeDifference(convertStringToDate(firstDateTimeStr),convertStringToDate(secDateTimeStr));
	return oDiff;
}
function getMonthMMM(monthNo){
	var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	return monthArray[monthNo];
}
function addMinutesTodateTime(dateTimeStr,minutes){
	var date = convertStringToDate(dateTimeStr);
	var datetime = new Date(date.getTime() + parseInt(minutes) * ( 60 * 1000));
	return datetime.getDate()+'-'+ getMonthMMM(datetime.getMonth()) +'-'+datetime.getFullYear() +" " + datetime.getHours() +":"+datetime.getMinutes();
}
function getDateFromDateTime(dateTimeStr){
	return dateTimeStr.substring(0,dateTimeStr.indexOf(" "));
}
function getTimeFromDateTime(dateTimeStr){
	return dateTimeStr.substring(dateTimeStr.indexOf(" ")+1,dateTimeStr.length);
}

/* Created By Suresh on 05-Dec-2011 
 * Modified By Karthick on 07-Dec-2011 */
function getTimeDifference(earlierDate,laterDate)
{	
	   var nTotalDiff = laterDate.getTime() - earlierDate.getTime();   
	  
       var oDiff = new Object();       
       oDiff.seconds = nTotalDiff/1000;
       oDiff.minutes = nTotalDiff/60000;
       oDiff.hours = nTotalDiff/3600000;
       oDiff.days = 1000*60*60*24;          
       oDiff.convtDays = Math.round(nTotalDiff/oDiff.days);  
       oDiff.years = Math.round(oDiff.convtDays/365);
       
    
       return oDiff; 
}

function showValidationErrorMsg(controlId,msg)
{
	jQuery('#'+controlId).addClass("tpm-error");
	if( jQuery('#err_'+controlId).length <= 0 )
	{	
		if( controlId.startsWith("cmb") || controlId.startsWith("dte") )
			jQuery('#'+controlId).next("span").after('<div id="err_'+controlId +'" class="tpm-errormsg" ></div>');
		else	
			jQuery('#'+controlId).after('<div id="err_'+controlId +'" class="tpm-errormsg" ></div>');
	}	
	jQuery('#err_'+controlId ).css("display","block");
	jQuery('#err_'+controlId).html(msg);
}

function changeFormatStringtoNumber(monthval){
    
	//alert(monthval);
	monthval = monthval.toUpperCase(); 
	//alert(monthval); 
	
	if(monthval=="JAN"){
	monthval=0;
	}
	else if (monthval=="FEB") {
	monthval=1;
	}
	else if (monthval=="MAR") {
	monthval=2;
	}
	else if (monthval=="APR") {
	monthval=3;
	}
	else if (monthval=="MAY") {
	monthval=4;
	}
	else if (monthval=="JUN") {
	monthval=5;
	}
	else if (monthval=="JUL") {
	monthval=6;
	}
	else if (monthval=="AUG") {
	monthval=7;
	}
	else if (monthval=="SEP") {
	monthval=8;
	}
	else if (monthval=="OCT") {
	monthval=9;
	}
	else if (monthval=="NOV") {
	monthval=10;
	}
	else if (monthval=="DEC") {
	monthval=11;
	}
	//alert(monthval);
	return(monthval);
}

function showCommonErrorMsg(msg)
{
	jQuery("#dispErr").html('<h5> ' +msg +'</h5>');
	
}
function popupCommonErrorMsg(msg)
{
	jQuery("#dispErr").html('<h5> ' +msg +'</h5>');
	div_err();
	
}
function clearValidationErrorMsg(controlId)
{
	jQuery("#"+controlId).css('border','1px solid #008BC2');
	jQuery("#err_"+controlId).hide();
	
}

function spinnerEvents(spinnerId,callBackFunc)
{
	var serverTime = srvTime();
	 
	var d =  new Date(serverTime);
	jQuery("#"+spinnerId).change(function(){
		//var onSelectFunctionName = id +'_onSelect';
		
		if( typeof eval('('+callBackFunc +')') == 'function')
		{
			
			eval('( '+ callBackFunc +'('+d+'))');
		}		
			
	});
	jQuery("#"+spinnerId).spinner({
		onSpinUp:function(){
			if( typeof eval('('+callBackFunc +')') == 'function')
			{
				eval('( '+ callBackFunc +'('+d+'))');
			}	
		}
	});
	jQuery("#"+spinnerId).spinner({
		onSpinDown:function(){
			if( typeof eval('('+callBackFunc +')') == 'function')
			{
				eval('( '+ callBackFunc +'('+d+'))');
			}		
		}
	});


}

function fillSectionHierarchy(url,cmbSectionId,cmbFactId,cmbCompId,cmbLocnId){
	
	
	glbcmbFactId=cmbFactId;
	glbcmbCompId=cmbCompId;
	glbCmbLocnId=cmbLocnId;
	processAjaxCalls(url,'sectionId='+cmbSectionId,'sectionHierarchy_OnSuccess','sectionHierarchy_OnError');
	
}

function sectionHierarchy_OnSuccess(result)
{	
	jQuery("#"+glbcmbCompId).combobox('setValue',result.sectionHierarchy.company);
	jQuery("#"+glbcmbFactId).combobox('setValue',result.sectionHierarchy.factory);	
	jQuery("#"+glbCmbLocnId).combobox('setValue',result.sectionHierarchy.location);
}

//------------------------ Year Hierarchy -----------------------

function fillYearHierarchy(url,cmbYearly,cmbMonthly,cmbQuartely,cmbHalfYearly){
	glbcmbMonthly=cmbMonthly;
	glbcmbQuartely=cmbQuartely;
	glbcmbHalfYearly=cmbHalfYearly;
	processAjaxCalls(url,'selYear='+cmbYearly,'yearHierarchy_OnSuccess','yearHierarchy_OnError');
}

function yearHierarchy_OnSuccess(result)
{	
	jQuery("#"+glbcmbMonthly).combobox('setValue',result.yearHierarchy.monthly);
	jQuery("#"+glbcmbQuartely).combobox('setValue',result.yearHierarchy.quartely);	
	jQuery("#"+glbcmbHalfYearly).combobox('setValue',result.yearHierarchy.halfyearly);	
}

function yearHierarchy_OnError(result){
	//alert("error");
}

//---------------------cell Hierarchy--------------------//

function fillCellHierarchy(url,cmbCellId,cmbSectionId,cmbFactId,cmbcompId, cmbLocnId,cmbCostCenter){
	
	glbCmbCostCenter = cmbCostCenter;
	glbcmbSectionId=cmbSectionId;
	glbcmbFactId=cmbFactId;
	glbCmbCompId = cmbcompId;
	glbCmbLocnId = cmbLocnId;
	processAjaxCalls(url,'cellId='+cmbCellId,'cellHierarchy_OnSuccess','cellHierarchy_OnError');
}

function cellHierarchy_OnSuccess(result)
{
	if( result != null ){
		jQuery("#"+glbcmbFactId).combobox('setValue',result.cellHierarchy.factory);
		jQuery("#"+glbCmbCostCenter).combobox('setValue',result.cellHierarchy.costcentre);
		jQuery("#"+glbcmbSectionId).combobox('setValue',result.cellHierarchy.section);
		jQuery("#"+glbCmbCompId).combobox('setValue',result.cellHierarchy.company);
		jQuery("#"+glbCmbLocnId).combobox('setValue',result.cellHierarchy.location);
	}
}

function cellHierarchy_OnError(result){
	//alert("error");
}


//------------------------ City Hierarchy -----------------------

function fillCityHierarchy(url,cmbEmpdCityid,cmbEmpdStateid,cmbEmpdCountryid){
	glbcmbEmpdStateid=cmbEmpdStateid;
	glbcmbEmpdCountryid=cmbEmpdCountryid;
	processAjaxCalls(url,'cityId='+cmbEmpdCityid,'cityHierarchy_OnSuccess','cityHierarchy_OnError');
}

function cityHierarchy_OnSuccess(result)
{	
	jQuery("#"+glbcmbEmpdStateid).combobox('setValue',result.cityHierarchy.state);
	jQuery("#"+glbcmbEmpdCountryid).combobox('setValue',result.cityHierarchy.country);	
}

function cityHierarchy_OnError(result){
	//alert("error");
}
function setFieldsInCheckBoxSelect(chbId,fieldId,chbTrueTxt,chbFalseTxt)
{
	jQuery('#'+chbId).click(function() {
		if(jQuery('#'+chbId).is(':checked') == true)
			displayText(fieldId,chbTrueTxt);
		else
			displayText(fieldId,chbFalseTxt);
	});
}

function displayText(fieldId,displayTxt)
{
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox('setValue',displayTxt);
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox('setValue',displayTxt);
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner('setValue',displayTxt);
	else
		jQuery("#"+fieldId).val(displayTxt);
}

/* function disableForm(formId){
	
	jQuery.noConflict();
	var inputs = jQuery('#' + formId + ' :input');
	var controlId ;
	jQuery(inputs).each(function () {
		controlId = this.id;
		readOnlyFields(controlId);		
	});
}
*/

function initialiseForm(formId){
	
	jQuery.noConflict();
	var inputs = jQuery('#' + formId + ' :input');
	
	var controlId ;
	//jQuery('#submitForm').val(formId);
    //jQuery(":input").keypress(function(e){ 
	
	//e.preventDefault();
	  
    //return AlphaNumericOnly(e);
    //});
	//$Spelling.SpellCheckAsYouType('all');
	
	 
	jQuery(inputs).each(function () {
		controlId = this.id;
		
		if( controlId.substring(0,3) == "cmb")
		{
			initialiseComboBox(controlId);
		}
		else if( controlId.substring(0,3) == "spn")
		{
			initialiseSpinner(controlId);
		}
		else if( controlId.substring(0,3) == "dte")
		{
			//formatDateBox(controlId,'dd-MMM-yyyy');
		}
		
		
	});
	var divs = jQuery('#' + formId + ' div[id^="tab"]');
	jQuery(divs).each(function () {
		 var Id = this.id;
		jQuery("#"+Id).tabs();
	});
	//jQuery("#" + formId + " :input[type=text] ").css('text-transform', 'uppercase');
	//jQuery("#" + formId + " textarea  ").css('text-transform', 'uppercase');
	if(!jQuery("#" + formId + " textarea  ").hasClass('limit-length'))
	jQuery("#" + formId + " textarea  ").addClass('limit-length'); 
		
	jQuery("input").draggable('disable');
	jQuery("#" + formId + ':first *:input[type!=hidden]:first').focus();

}

function initialiseDateFields( formId){
	
	jQuery.noConflict();
	var inputs = jQuery('#' + formId + ' :input');
	var controlId ;
	jQuery(inputs).each(function () {
		controlId = this.id;
		if( controlId.substring(0,3) == "dte")
		{
			formatDateBox(controlId,'dd-MMM-yyyy');
		}	
		
	});
}
	
function AlphaNumericOnly(e,isAlphaonly,isnumeric)
{
	//AlphaNumericOnly(event,false,true)
   var key = [e.keyCode||e.which];

   var keychar = String.fromCharCode([e.keyCode||e.which]);
   keychar = keychar.toLowerCase();
 
   if( isnumeric ==true)
	   	 checkString="0123456789";
   else if(isAlphaonly=='true')
         checkString="abcdefghijklmnopqrstuvwxyz";
   else 
         checkString="abcdefghijklmnopqrstuvwxyz0123456789";
   
   
   if ((key==null) || (key==0) || (key==8) ||(key==9) || (key==13)  || (key==27)
		   || (key==32) || (key == 36)||(key==37)(key==38)||(key==39)||(key==40))  
        return true;
   else if (((checkString).indexOf(keychar) > -1))
        return true;
   else
        return false;
}
/*madhan*/
var comboInit = {};
function initialiseComboBox(comboboxId){
	comboInit[comboboxId] = true;
	jQuery('#'+comboboxId).combobox({  
	    url:'',  
	    valueField:'id',  
	    textField:'text' ,
		onSelect: function () {
		            if (comboInit[comboboxId]) return; // ignore init
		            // real onSelect logic here
		        },
		        onLoadSuccess: function () {
		            comboInit[comboboxId] = false;
		        } 
	}); 
}
function initialiseSpinner(spinnerId){
	
	jQuery('#'+spinnerId).timespinner({ 			
			min:"00:00",
			showseconds:false,   
		    increment: 1,
		    editable: true  
		    
	});	
}
function initialiseSpinnerWithGrid(spinnerId){
	
	jQuery('[id^='+spinnerId+']').timespinner({ 			
			min:"00:00",
			showseconds:false,   
		    increment: 1,
		    editable: true   			
	});	
}

/*function clearForm(id)
{    
	
	
	var inputs = jQuery('#'+id+ ' :input ');
	  
	var controlId ;
	jQuery(inputs).each(function () {
		 
		switch(inputs.attr('type')) {
			case 'password':
			case 'select-multiple':
			case 'select-one':
			case 'text':
			case 'textarea':
			case 'hidden':	
				jQuery(this).val('');
				break;
			case 'checkbox':
			case 'radio':
				this.checked = false;
		}
		
		if( inputs.attr('type').toUpperCase() != 'BUTTON' ) 
		{	
			controlId = inputs.id;
			if( controlId.substring(0,3) == "btn"){
				alert(1);
			}
			if( controlId.substring(0,3) == "cmb"){
			//	alert(id+" cmb  :"+controlId);
				jQuery('#'+id + ' input[id=' + controlId+']').combobox('clear');
			}
			else if( controlId.substring(0,3) == "dte"){
				//alert(id+" dte  :"+controlId);
				jQuery('#'+id + ' input[id=' + controlId+']').datebox('clear');
			}
			
			/*else if( this.type != "checkbox") {
				
				jQuery('#'+id + ' input[id=' + controlId+']').val('');
			}
			*/
		/*}
		else{
			
		}
	});
	
}*/

function clearForm(id)
{   
	jQuery('#'+id+ ' :input').not('input[type="button"],[type=checkbox],[type=hidden]').val('');
	jQuery('#'+id+ ' :input[type=checkbox]').attr('checked',false);
	
    jQuery('#'+id+ ' :input[type="hidden"]').filter(function(){
    	var parentId = jQuery(this).parent().attr("id");
    	if( parentId != undefined && parentId != null && parentId.indexOf("FuntKeyIds")>0)
    		return false;
    	return true;
    }).val('');
	
	/*var inputs = jQuery('#'+id+ ' :input ');
	var controlId ;
	jQuery(inputs).each(function () {
		 
		switch(inputs.attr('type')) {
			case 'password':				
			case 'select-multiple':				
			case 'select-one':
			case 'text':
			case 'textarea':			
			case 'hidden':	
			if( this.id.substring(0,3).toUpperCase() != 'BTN' ) 
			{
				jQuery(this).val('');
			}
				break;
			case 'checkbox':
			case 'radio':
				this.checked = false;
		}
		if( inputs.attr('type').toUpperCase() != "BUTTON" ) 
		{	
			
			controlId = inputs.attr("id");
			if( controlId.substring(0,3) == "cmb"){
				jQuery('#'+id + ' input[id=' + controlId+']').combobox('clear');
			}
			else if( controlId.substring(0,3) == "dte"){
				jQuery('#'+id + ' input[id=' + controlId+']').datebox('clear');
			}
			
			/*else if( this.type != "checkbox") {
				
				jQuery('#'+id + ' input[id=' + controlId+']').val('');
			}
		
		}
		
	});
	*/	
}

function ToolsTree(dataUrl,condition,gridId,rowId,colNames,isMultiselect){

	jQuery( "#multiselectPopUpId" ).show();
	jQuery( "#multiselectPopUpId" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 520,
			width: 550,
			left:30,
			top:30,
			modal: true,
			title:"Tools",
			onClose:function(){jQuery('#hdnPopupUrl').val(' ');}
	});
	var url = "toolpop_input.mselect?" + 'dataUrl='+dataUrl+'&condition='+ condition +'&gridId='+gridId +'&rowId='+rowId +'&colNames='+colNames +'&isMultiselect='+isMultiselect;
	LoadForm("loadMultiSelectPopUp","preloadMultiSelect",url,"dispErr","toolpop_successCallback");
}
function toolpop_successCallback(response){
	
	///alert(Object.keys(response));
	if( response.dataNotExist  ){
		showCommonErrorMsg(response.dataNotFoundMsg);
		jQuery( "#multiselectPopUpId" ).dialog("close");
	}	
}	

/**modified on 24-sep-2012**/
function funcnLocnPopUp(dataUrl,condition,gridId,rowId,colNames,isMultiselect,multiSelectCancel_Callback,multiSelectOk_Callback,header,isHeading){
	
	// jQuery( "#multiselectPopUpId" ).show();
	var left = 500;
	var top = 80;
	if(screen.width <= 1024)
		left = 200;	
	if (isIE()) {
		top=50;
	}
	jQuery( "#multiselectPopUpId" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 530,
			width: 630,
			left:left,
			top:top,
			modal: true,
			title:header!=null?header!='undefined'?header:'multiselectPopUpId':'multiselectPopUpId',
			onClose:function(){jQuery('#loadMultiSelectPopUp').val(' ');}
	
	});
	if(isHeading == null || isHeading == 'undefined')
	{
		if (isIE()) {}
		else{
			    jQuery('div .panel-title').filter(function(){
			    	return jQuery(this).html()=='multiselectPopUpId';
			    }).remove();
		}
	}
	//jQuery('div .panel-header panel-header-noborder window-header').css('height',4);
	//jQuery('div .panel-title').css('margin-top',-11);
	//jQuery('div .panel-tool').css('top',-8);
    var url = "funcnLocn_input.mselect?" + 'dataUrl='+dataUrl+'&condition='+ condition +'&gridId='+gridId +'&rowId='+rowId +'&colNames='+colNames +'&isMultiselect='+isMultiselect+'&multiSelectCancel_Callback='+multiSelectCancel_Callback+"&multiSelectOk_Callback="+multiSelectOk_Callback;
	LoadForm("loadMultiSelectPopUp","preloadMultiSelect",url,"dispErr","multiSelectOk_Callback");
}
/****/
function funcnLocn_successCallback(response){
	
	if( response.dataNotExist  ){
		showCommonErrorMsg(response.dataNotFoundMsg);
		jQuery( "#multiselectPopUpId" ).dialog("close");
	}	
}
function isEmpty(obj) {
    for(var prop in obj) {
        if(obj.hasOwnProperty(prop))
            return false;
    }

    return true;
}

function subFormPop(subFormUrl,subFormLeft, subFormTop,subFormHeight, subFormWidth, formName,controls)
{	
	jQuery( "#subformPopUpId" ).show();

	if (subFormLeft==null)
		subFormLeft=90;
	if (subFormTop==null)
		subFormTop=100;
	if (subFormHeight==null)
		subFormHeight=450;
	if (subFormWidth==null)
		subFormWidth=1050;
	
	jQuery( "#subformPopUpId" ).dialog({
			autoOpen: false,
			title: formName.replace('-',' '),
			show: "blind",
			hide: "explode",
			height: subFormHeight,
			width: subFormWidth,
			left:subFormLeft,
			top:subFormTop,	
			modal: true
			
			
	});	

	jQuery( "#subformPopUpId" ).dialog({
	    onOpen:function(){	    
	    	glbMainFormUrl=jQuery('#submitForm').val();
	    	
	    }  			
	});

	jQuery( "#subformPopUpId" ).dialog({
		    onClose:function(result){
		    	jQuery('#submitForm').val(glbMainFormUrl);
		    	jQuery('#hdnPopupUrl').val(' ');
		    	uncheckApplytoAll();
		    	try{
				var successCallback = glbMainFormUrl +  '_phencauseLinkCallBack';
			
				if( typeof eval('(' + successCallback  +')') == 'function')
				{	
					eval('(' + successCallback  +'(result) )');
				}
		     }catch(Exception ){}
				

		    }
	});
	
	
	var url = "subForm_input.mselect?" +'subFormUrl='+subFormUrl+'&controls='+ escape(controls) ;
	LoadForm("loadSubFormPopUp","preloadSubForm",url,"subdisErr","subFormpop_successCallback");
}

function subFormpop_successCallback(response){
	if( response.dataNotExist  ){
		showCommonErrorMsg(response.dataNotFoundMsg);
		
		jQuery( "#subformPopUpId" ).dialog("close");
	}		
}


function multiSelectSavePop(dataUrl,condition,gridId,rowId,colNames,ismultiselectSave,multiSelectSaveCancel_Callback,multiSelectSaveOk_Callback){
	var isIE = !!window.ActiveXObject || "ActiveXObject" in window;
	jQuery( "#multiSelectSavePopUpId" ).show();
	jQuery( "#multiSelectSavePopUpId" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 565,
			width: 430,
			left:800,
			top:10,	
			modal: true,
			title:isIE ?'-':'multiSelectSavePopUpId',
			
			onClose:function(){jQuery('#hdnPopupUrl').val(' ');}
	});
	if(!isIE()){ 
	 jQuery('div .panel-title ').filter(function() { 
		  return jQuery(this).html() == 'multiSelectSavePopUpId'; 
		}).remove(); 
	}
	jQuery(".window-shadow").hide();
	//jQuery(".panel-title").hide()  ;
	//jQuery(".panel-header ").hide();
	//jQuery(".window-shadow").hide();
	//jQuery(".dialog-content panel-body panel-body-noheader panel-body-noborder").hide();
	var url = "multiSelectSave_input.mselect?" + 'dataUrl='+dataUrl+'&condition='+ condition;

	url += '&gridId='+gridId +'&rowId='+rowId +'&colNames='+colNames +'&ismultiSelectSave='+ismultiselectSave+'&multiSelectSaveCancel_Callback='+multiSelectSaveCancel_Callback+"&multiSelectSaveOk_Callback="+multiSelectSaveOk_Callback;

	LoadForm("loadMultiSelectSavePopUp","preloadMultiSelectSave",url,"dispErr","multiSelectSavePopUpId_successCallback");
}
function multiSelectSavePopUpId_successCallback(response){

		//showCommonErrorMsg(response);
		//jQuery('#multiSelectSaveGrid').trigger("reloadGrid");
		//jQuery( "#multiSelectSavePopUpId" ).dialog("close");
}

function setGridCell(gridId,rowId,colIndex,value){
	jQuery('#'+gridId).jqGrid().setCell(rowId,colIndex,value);
}
function getGridCell(gridId,rowId,colIndex){
	return jQuery('#'+gridId).jqGrid().getCell(rowId,colIndex);
}


/*function setFieldValue(fieldId , value)
{
	
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("setValue",value);
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("setValue",value);
	else
		jQuery("#"+fieldId).val(value);

}*/
function setSubmitFormUrl(url){
	jQuery('#hiddenUrl').val(url);
}
function getSubmitFormUrl(){
	return jQuery('#hiddenUrl').val();
}

function invokeAfterLoadFormCallBack(){
	//madhan
	//alert("invokeAfterLoadFormCallBack");
	var lodfrmClbck =  getLoadFormCallBackFrmId();
	//alert("invokeAfterLoadFormCallBack:"+lodfrmClbck);
	setLoadFormCallBackFrmId('');
	 if(lodfrmClbck != null && lodfrmClbck != undefined && lodfrmClbck.length > 0)
	 {	
		 lodfrmClbck += "_afterLoadCallBack";
		 var args = [];
		 dynamicFunctionCall(lodfrmClbck, args); 
	 }
}
/*
 * 
function toggle_commonFilter(){

	jQuery(".filterpanel").toggle("fast");
	jQuery(".filtertab").toggleClass("active");
	
}
*/
function hideJqGridRow(gridId, rowId){
	jQuery("#"+gridId + ' tr[id='+ rowId +"]").css({display:"none"});
}

function disableForm(formId) {	
	var inputs = jQuery('#' + formId + ' :input');	
	var controlId ;

	jQuery(inputs).each(function () {
		controlId = this.id;		
		
		if( controlId.substring(0,3) == "cmb") {			
			jQuery("#"+controlId).combobox("disable");			
		}
		else if( controlId.substring(0,3) == "spn") {
			jQuery("#"+controlId).spinner("disable");
		}	
		else if( controlId.substring(0,3) == "txt" || controlId.substring(0,3) == "txt") {
			readOnlyFields(controlId);
			jQuery("#"+controlId).css('background-color', '#D1E2FD');
		}
		else if( controlId.substring(0,3) == "dte") {
			
			jQuery("#"+controlId).datebox('disable');
			jQuery("#"+controlId).css('background-color', '#D1E2FD');
			
		}		
		else if( controlId.substring(0,3) == "grd") {
			jQuery("#"+controlId).tableNavigation({disabled:true});
			
		jQuery("#"+controlId).css('background-color', '#D1E2FD');
		}		
		else
			jQuery("#"+controlId).attr('disabled', 'disabled');
			//jQuery("#"+controlId).attr('readonly','readonly');
	});
		return true;
	}



/*Created By Suresh.K*/
function openMasterForm(url,mode,formId,formHeader,successCallback)
{	
	jQuery("#newMstFrm").slideToggle(200);
	jQuery('#loadFormId').val(formId);
	loadMasterForm(url,mode,successCallback,formHeader);
}
function loadMasterForm(url,mode,formId,formHeader)
{	
/*	jQuery('#loadFormMode').val(mode);	
	jQuery('#mstFrmHeader').html('');
	var height = jQuery('.layout-panel-center').css('height');
	var top = jQuery('.layout-panel-center').css('top');
	var footerTop = jQuery('.layout-panel-south').css('top');	
	fldisableLayout();
	url += '&loadContentDivId=loadMstFrm&preLoadContentDivId=preloadMstFrm';
*/	
	if(formId !='undefined' && formId != null && formId.trim().length>0)
		url += '&formId='+formId;
	var height = jQuery('.layout-panel-center').css('height');
	var top = jQuery('.layout-panel-center').css('top');
	
	LoadPopUp("newMstFrm", url, true,null,height,top,null, null, formHeader,false, true,true);
	//navigateToNextForm(url,formHeader);	
}
function mstFrm_beforeCloseCurrentForm()
{
	
	flenableLayout();	
	
	if (isIE()) {
		 
	jQuery("#newMstFrm").css('display','none');
	
	}else
		jQuery("#newMstFrm").hide(0);
	
	var linkFormId = jQuery('#loadFormId').val();		
	if( linkFormId != null && linkFormId != "" && linkFormId.trim().length > 0){
		var beforeCloseCurrentFormCallBack = linkFormId +'_beforeCloseCurrentForm';
		try{
			if( typeof eval('('+beforeCloseCurrentFormCallBack +')') == 'function')
			{
				
				eval('( '+ beforeCloseCurrentFormCallBack +'())');
			}
		}catch(Exception){}
		//dynamicFunctionCall(beforeCloseCurrentFormCallBack,null) ;
		jQuery('#loadFormId').val('');
		
	}
	return false;
}


function fldisableLayout()
{ 
	var height = jQuery('.layout-panel-center').css('height');
	var top = jQuery('.layout-panel-center').css('top');
	//var top = jQuery('.layout-panel-center').css('top');
	
	jQuery('#mstfrm_div').addClass('popup-mask');
	jQuery('#newMstFrm').addClass('custom-popup');
	jQuery('#mstfrm_div').css('display','block');
	jQuery('#mstfrm_div').css('margin-top',top);
	jQuery('#mstfrm_div').css('height',height);	
}
function flenableLayout()
{  
	jQuery('#mstfrm_div').removeClass('popup-mask');
	jQuery('#newMstFrm').removeClass('custom-popup');
	jQuery('#mstfrm_div').css('display','none');
	jQuery('#mstfrm_div').css('margin-top','0px');
	jQuery('#mstfrm_div').css('height','0px');	
}



function loadMstFrmSuccess(response)
{
	jQuery('#mstFrmHeader').html('');
	jQuery('#mstFrmHeader').html(getFrmTitle(jQuery('#submitForm').val()));	
	//jQuery('#mstFrmHeader').css({'margin-left':'40%'});	
	//jQuery('#mstFrmHeader').css({ 'font-size':15});
}
function convertGridToJSONArr(jqGridId){
	//alert("Insidefunction");
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	//alert(allRows+"allRows");
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		jsonArrO += '{';
		
		for(var colName in row) {
			//alert(colName+"colName");
			if(row[colName].substring(0,6)!='<input')
			{
				jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
			}
			else
			{
				var x=row[colName].indexOf("id=")+4;
				var y=row[colName].substring(x);
				var z = y.indexOf('"');					
				jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val() +'",'; 
			}
		}
		jsonArrO = jsonArrO.slice(0, -1) + "},"; 
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO"+jsonArrO);
	return jsonArrO; 
}

function getFrmTitle(frmId)
{
	var frmTitle = null;
	if(frmId == 'frmCompany')
		frmTitle = 'Company Master';
	else if(frmId == 'frmLocation')
		frmTitle = 'Location Master';
	else if(frmId == 'frmFactory')
		frmTitle = 'Unit Master';
	else if(frmId == 'frmSection')
		frmTitle = 'Section Master';
	else if(frmId == 'frmCell')
		frmTitle = 'Line Master';
	else if(frmId == 'frmEquipment')
		frmTitle = 'Equipment Master';
	else if(frmId == 'frmAssembly')
		frmTitle = 'Assembly Master';
	else if(frmId == 'frmSparesMaster')
		frmTitle = 'Spares Master';	
	else if(frmId == 'frmPhenomena')
		frmTitle = 'Phenomena Master';
	else if(frmId == 'frmCause')
		frmTitle = 'Cause Master';
	else
		{
			var caption = jQuery('#hdnmenuCaption').val();
			if( caption != null && caption != "" && caption.trim().length > 0)
				frmTitle = caption;
		}	
	return frmTitle;
}
function formMode()
{
	this.create = 'CREATE';
	this.edit = 'MODIFY';
	this.view = 'VIEW';
	this.frmMode = 'mode';
	this.keyId = 'keyId';
	this.lockFields = 'lockFields';	
	this.openTab = null;
}

var mode = (function() {
    var private = {
        'NEW': 'CREATE',
        'EDIT': 'MODIFY',
        'VIEW':'VIEW'
    };
    
   /* return {
       get: function(name) { return private[name]; }
   };*/
})();
/*Created By Suresh.K*/
/*function disableUIButton(btnId)
{	
	jQuery("#"+btnId).prop("disabled", true).addClass("ui-state-disabled");    
}
function enableUIButton(btnId)
{	
	jQuery("#"+btnId).prop("disabled", false).removeClass("ui-state-disabled");	
}*/

function disableUIButton(btnId) {
    var btn = jQuery("#" + btnId);
    btn.prop("disabled", true)
        .addClass("ui-state-disabled")
        .attr("aria-disabled", "true");
}

function enableUIButton(btnId) {
    var btn = jQuery("#" + btnId);
    btn.prop("disabled", false)
        .removeClass("ui-state-disabled")
        .removeAttr("aria-disabled");
}

function refreshNode(treeId,nodeId)
{
	jQuery("#"+treeId).jstree("refresh",jQuery('#'+nodeId)); 
}

function changeFormInputBackGround(frmId)
{
	jQuery(':input','#'+frmId)
	.css({"background-color": "#FFFFFF"}).css({"color":"#333333"});
}

function frmFuncnLocn_backToPrevForm(){	
	
//	jQuery( "#goIn" ).click();	
	jQuery("#newMstFrm").hide(0);
	jQuery("#mstFrmHeader").html('');	
	jQuery('#formFlag').val(jQuery('#submitForm').val());
	jQuery('#submitForm').val(jQuery('#prevSubmitForm').val());
	jQuery('#hiddenUrl').val(jQuery('#prevUrl').val());	
	
	if(jQuery('#loadFormMode').val() == 'CREATE')
	{
		
		var selId = jQuery('#txtelemId').val();
		
		if(jQuery('#txtformFld').val() == '-')
			selId = selId;
		else
			selId = selId+ '&formField='+jQuery('#txtformFld').val();
		
		if(selId == null || selId == '' || selId == ' ')
			var y = null;
		else
			 funcnLocnPopUp("funcnLocn_input.funlocn",selId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");
	}

}

/*
 *Author: Prasanth
 */
/*
 *Author: Prasanth
 */

/*function loadFunctionalLocation( hierLoadDivId,checkMandUrl,divid,formId,dataStr){
	
	var url = checkMandUrl.substring(0,(checkMandUrl.indexOf('?')>=0?checkMandUrl.indexOf('?'):checkMandUrl.length)) +"?load=true&url="+escape(checkMandUrl) +'&divId='+divid +'&formId='+formId+dataStr;
	 
	var roleId = getFieldValue("cmbEmployeRoles","frmBanner");
	if( roleId != undefined )
		url += "&roleId="+roleId;

	//jQuery('#preLoad'+hierLoadDivId).addClass("funtional-loc-loading");
	if(jQuery('#preLoad'+hierLoadDivId).length <= 0 )
		jQuery('#'+hierLoadDivId).before('<div id="preLoad'+hierLoadDivId +'" class="funtional-loc-loading" ></div>');	

	LoadForm(hierLoadDivId,"preLoad"+hierLoadDivId,url,"dispErr","loadFunctionalLocationHierarchy_successCallback");
}*/

function loadFunctionalLocation( hierLoadDivId,checkMandUrl,divid,formId,dataStr){

    var url = checkMandUrl.substring(0,(checkMandUrl.indexOf('?')>=0?checkMandUrl.indexOf('?'):checkMandUrl.length)) +"?load=true&url="+escape(checkMandUrl) +'&divId='+divid +'&formId='+formId+dataStr;
   
    var roleId = jQuery("#hdnUserRole").val(); //getFieldValue("cmbEmployeRoles","frmBanner");
    if( roleId != undefined )
        url += "&roleId="+roleId;

    //alert(roleId);
    //jQuery('#preLoad'+hierLoadDivId).addClass("funtional-loc-loading");
    if(jQuery('#preLoad'+hierLoadDivId).length <= 0 )
        jQuery('#'+hierLoadDivId).before('<div id="preLoad'+hierLoadDivId +'" class="funtional-loc-loading" ></div>');   

    LoadForm(hierLoadDivId,"preLoad"+hierLoadDivId,url,"dispErr","loadFunctionalLocationHierarchy_successCallback");
}
function loadFunctionalLocationHierarchy_successCallback(result){
	
}
function fillFunctionalLocationHierarchy(divId,formId, url,dataStr){
	//alert("divId:"+divId);
	//alert("formId:"+formId);
	//alert("url:"+url);
	//alert("dataStr:"+dataStr);
	
	jQuery("#functLocHierarPopupId").dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 500,
			width: 500,
			left:20,
			top:90,
			modal: true,
			title:"Functional Location"
	});
	/*jQuery("#functLocHierarPopupId").dialog({
	    autoOpen: false,
	    modal: true,
	    height: 500,
	    width: 500,
		closeText: "Close", 
	    title: "Functional Location",
	    show: { effect: "blind", duration: 300 },
	    hide: { effect: "explode", duration: 300 },
	    position: {
	        my: "left top",
	        at: "left+20 top+90",
	        of: window
	    }
	});*/
	
	//console.log(jQuery.ui.dialog);
	
	url = url + (url.indexOf('?') >= 0 ? '&' : '?') + 'divId='+divId +"&formId="+formId +dataStr;
	LoadForm("functLocHierarLoadId","functLocHierarPreLoadId",url,"dispErr","fillFunctionalLocationHierarchy_successCallback","fillFunctionalLocationHierarchy_errorCallback");
	
}

function fillFunctionalLocationHierarchy_successCallback(response){

}

function fillFunctionalLocationHierarchy_errorCallback(response){
}

function proccessValidationMessages(validMsgs){
	var validTpmMsgs = "";
	
	for(var i = 0; i < validMsgs.length;i++)
	{	
		jQuery('#'+validMsgs[i][0]).addClass("tpm-error");
		if( jQuery('#err_'+validMsgs[i][0]).length <= 0 )
		{	
			if( validMsgs[i][0].startsWith("cmb") || validMsgs[i][0].startsWith("dte") )
				jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+ validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
			else	
				jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
		}	
		jQuery('#err_'+validMsgs[i][0] ).css("display","block");
		jQuery('#err_'+validMsgs[i][0]).html(validMsgs[i][1]);
		
		validTpmMsgs += validMsgs[i][1] +",";
		
	}

	return validTpmMsgs;
}
function show_success(msg){
	jQuery('#dispFrontPageErr').css('display','none');
	jQuery('#dispFrontPageSuccess').css('display','block');
	//var show = true ;
	jQuery('#success_msg').css('display','block');
	jQuery('#success_msg').addClass('dispSuccicon');
	jQuery('#success_msg').html('<h5 style="margin-left:30px;"> ' + msg +'</h5>');
	/*if(show = true){
	jQuery('#dispFrontPageErr').delay(2000);
	jQuery('#dispFrontPageSuccess').css('display','none');
	}*/
	
}
function div_err(){
	var maxIndex = 0;
    jQuery(".popup-mask, .custom-popup,.filterpanel,.panel,.window").each(function(){
	   var currIndex = parseInt(jQuery(this).css("z-index"), 10);
		if(currIndex > maxIndex) {
		    maxIndex = currIndex;
		}
    });
	jQuery('#footerSlideContent').css('display','block');
	//jQuery('#footerSlideContainer').css('z-index','9999');
	jQuery('#footerSlideContainer').css('z-index',maxIndex+1);
		jQuery('#dispFrontPageSuccess').css('display','none');
		jQuery('#footerSlideContent').animate({ height: '200px' });
		jQuery("#err_popUp ").attr("src","images/callBack_img/error_save_hvr.png");
		jQuery('#dispFrontPageSuccess').css('display','none');
		jQuery('#success_msg').css('display','none');
		jQuery('#dispFrontPageErr').css('display','block');
		jQuery('.layout-panel-south').css('z-index','999');
	/*******************************************************/	
		jQuery(this).css('backgroundPosition', 'bottom left');
		jQuery('#dispErr').css('display','block');
		jQuery('#footerSlideContent').delay(3000);
		jQuery('#footerSlideContent').slideDown(5000,function(){
			jQuery('#footerSlideButton').css('display','block');
			jQuery('#footerSlideContent').animate({ height: '0px' });
			jQuery("#err_popUp ").attr("src","images/callBack_img/error_save.png");
			jQuery('#dispErr').css('display','none');
			setTimeout(function(){
				jQuery('#footerSlideContainer').css('z-index','-9999');
				},1150);
		});
	jQuery("#err_popUp ").hover(function() {
		jQuery(this).attr("src","images/callBack_img/error_save_hvr.png");
			}, function() {
		jQuery(this).attr("src","images/callBack_img/error_save.png");
	});
	
	var open = false;
	jQuery('#err_popUp').click(function () {
		if(open === false) {
			jQuery('#dispErr').css('display','block');
			jQuery('#footerSlideContent').animate({ height: '200px' });
			jQuery("#err_popUp ").attr("src","images/callBack_img/error_save_hvr.png");
			jQuery(this).css('backgroundPosition', 'bottom left');
			jQuery('#footerSlideContainer').css('z-index','9999');
			open = true;
			
		} else {
			jQuery('#footerSlideContent').animate({ height: '0px' });
			
			jQuery("#err_popUp ").attr("src","images/callBack_img/error_save.png");
			jQuery(this).css('backgroundPosition', 'top left');
			open = false;
			jQuery('#dispErr').css('display','none');
			setTimeout(function(){
				jQuery('#footerSlideContainer').css('z-index','-9999');
				},1150);
		}
	});	
 }
function slideErrmsg(){
	var open = false;
	if(open === false) {
		jQuery('#dispErr').css('display','block');
		jQuery('#footerSlideContent').animate({ height: '200px' });
		jQuery(this).css('backgroundPosition', 'bottom left');
		jQuery('#footerSlideContainer').css('z-index','9999');
		open = true;
		
	} else {
		
		jQuery('#footerSlideContent').animate({ height: '0px' });
		jQuery(this).css('backgroundPosition', 'top left');
		setTimeout(function(){
		jQuery('#footerSlideContainer').css('z-index','-9999');
		},1150);
		//jQuery('#footerSlideContent').animate({ height: '10px' });
		open = false;
		jQuery('#dispErr').css('display','none');
	}
}
/*grpId Param added by Dhanalakshmi*/
function reloadMachine(formId, fieldId,cellId,sectId, factId,locnId,compId,machId,eqpGrpId){
	//madhan06MAR2026
	jQuery('#'+fieldId).combobox("reload","machineCombo.commonFilter?compId="+ compId +'&factId='+factId+'&sectId='+sectId+"&cellId="+cellId +'&machId='+machId+'&eqpGrpId='+eqpGrpId);
	

}


function show_winMask(flag){
	
	if(flag == 1 || flag == "1"){
		
		jQuery('#modal_div1').addClass('window-mask');//for disabling window while processing and saving
		jQuery('#modal_div1 > div.save-loading').show();//show processing ajax
	}
	else {
		
		if(flag == '4'){
			//alert("44444444444444444444444444444444");jQuery("div[id*=PopupMask]").parent('div').parent('div').attr("id")
			//alert("z-index  :"+jQuery("div[id*=PopupMask]").css('z-index'));
			//jQuery("div[id*=PopupMask]").parent('div').css('z-index',parseInt(jQuery("div[id*=PopupMask]").css('z-index'))+10);
			//jQuery("div[id*=PopupMask]").removeClass('popup-mask');//remove disabling window
			//jQuery('#modal_div').css('display','block');
			//jQuery("div[id*=PopupMask]").css('z-index',jQuery("div[id*=PopupMask]").css('z-index')-100);
		}
		else{
		jQuery('#modal_div1').removeClass('window-mask');//remove disabling window
		jQuery('#modal_div1 > div.save-loading').hide();//hiding ajax loading
		}
	}
}

function showLoadingMsg(flag){
	
	if(flag == 1){
		jQuery('#modal_div1').show();
		jQuery('#modal_div1').addClass('window-maskgrid');//for disabling window while processing and saving
		jQuery('#modal_div1 > div.save-loadinggrid').show();//show processing ajax
	}
	else {
		jQuery('#modal_div1').removeClass('window-maskgrid');//remove disabling window
		jQuery('#modal_div1 > div.save-loadinggrid').hide();//hiding ajax loading
	}
}

function handleBrowserKeyPress(e){
    var doPrevent;
    
    if (e.keyCode == 8 || e.keyCode == 116 || e.keyCode == 112 || e.keyCode == 114 ) {
        var d = e.srcElement || e.target;

        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
            doPrevent = d.readOnly || d.disabled;
            doPrevent = false; 
        }
        else
            doPrevent = true;
    }
    else {
    	if( e.keyCode == 9 )
    	{
    		//alert(e.target);
    		jQuery(e.target).focus();
    	}
    	//else if( e.keyCode == 122){
    	//	resizeFrame(); 
    	//}

    		
    	
        doPrevent = false;
    }    
    if (doPrevent ){
    	
    	if( e.keyCode == 8 ) //back space
    	{	
    		e.preventDefault();
    		e.stopPropagation();
    		if( ! isPopUpOpened()  )
    			backButtonNavigation();
    		return true;
    	}	
    	else if( e.keyCode == 116){//F5
    		if( ! isPopUpOpened()  )
    			refreshButtonPress();
    		
    		e.preventDefault();
        }
    	else if( e.keyCode == 112){ //F1
    		if( ! isPopUpOpened()  )
    			openNewForm();
    	}
    	else if( e.keyCode == 114){  //F3
    		if( ! isPopUpOpened()  )
    			deleteButtonPress();
        }
    	else if( e.keyCode == 119){
        	var d = e.srcElement || e.target;
    		
    		e.preventDefault();
   		 	e.stopPropagation();
    	}
    		
        e.preventDefault();
    }    
    else if (e.keyCode == 113){
    	if( ! isPopUpOpened()  )
    		saveFormButtonPress();
    }
    
    
}
function isPopUpOpened(){
	var ret = false;
	jQuery(".window-mask,.popup-mask").each(function (){
		if( jQuery(this).css('display') == 'block')
			ret =true;
	});
	return ret;
}


function openNewForm(){
	
	var url = jQuery('#hiddenUrl').val();

	var isMasterForm = getFilterValue(url+'&', 'masterForm');

	if( url.indexOf("loadFormArg")>0){
		url = getFilterValue(url+'&', 'loadFormArg');
		url = unescape(url);
	}
	if(isMasterForm != 'Y')
		return 0;

	if( url != "" && url.indexOf("_view") > 0 ){
		getFormMode("getMode"+url);
	}	
	else if(jQuery('#loadFormMode').val() != null  )
	{
		if(jQuery('#loadFormMode').val() != frmMode.view && jQuery('#loadFormMode').val() != frmMode.edit)
		{
		  if( url != null && url.length > 2 ){
			var formHeader = getFormMainHeader();	
			//url = url.replace("_view","_input");	
			//url = url.replace("masterForm=Y","");
			//url = url.replace("?masterForm=Y","");
			jQuery('#hiddenUrl').val(url);	
		//	url += (url.indexOf('?') >= 0 ? '&':'?') + 'userEvent=new';   //
			var formNavigOb = formNavigations.pop();
			var masterFormDiv = getFilterValue(formNavigOb.URL+'&',"LoadMasterFormDivId") ;
			if( formNavigOb.URL.indexOf("masterForm") < 0)
				formNavigOb.URL += "&masterForm=Y";
			
			if( masterFormDiv != "" && masterFormDiv.length > 0 )
				url = url+'&loadContentDivId='+masterFormDiv;
			
			formNavigations.push(formNavigOb);
			var index =  formNavigOb.URL.indexOf("?") > 0?formNavigOb.URL.indexOf("?"):formNavigOb.URL.length-1;  
			var tempUrl = formNavigOb.URL.substring(0,index);
			index =  url.indexOf("?") > 0?url.indexOf("?"):url.length-1;
			var thdnUrl = url.substring(0,index);
			
			if( tempUrl != thdnUrl)
				navigateToNextForm(url,formHeader,null,{'masterForm':'Y'});
		  }
		}
	}
}

function getFormMode(url){
	
	jQuery.ajax({
	       type: "GET",
	       url: url,
	       dataType: "json",
	       //data:data,
	       success: function(result)
	       {
	    	   if( result != undefined && result != "" && result.mode != null  ){
					if( result.mode == "create" &&  result.url != null && result.url.length > 0 )
					{
						
						url = result.url;
						jQuery('#hiddenUrl').val(url);
						url += (url.indexOf('?') >= 0 ? '&':'?') + '';   //
						navigateToNextForm(url,result.formHeader);
					}	
				}
	    	   //return result; 	
	       },
		   error:function(status)
		   {
			   return ""; 
		   }
	       
	});
}

function backButtonNavigation(){
	var prevFormId = jQuery('#prevSubmitForm').val();
//alert("back button"+prevFormId);
	if(prevFormId.length>0)
	{
		var backToPrevForm =   'frmFuncnLocn_backToPrevForm';
		//alert("inside the previous form");
		if( typeof eval('(' + backToPrevForm  +')') == 'function')
			eval('(' + backToPrevForm  +'())');
	}
	else{
		
		
		navigateToPrevForm();
	}	

}

function saveFormButtonPress(){
	
	var formId = jQuery('#submitForm').val(); 
	
	if( jQuery('#'+ formId + " input[id=mode]" ).length > 0 ){
		
		var mode = jQuery('#'+ formId + " input[id=mode]" ).val();		
		if( mode != null && mode != "null" && mode != undefined  && mode != 'view' )
		{
			var url = jQuery('#hiddenUrl').val(); 
			url = url.replace("_view","_save");
			
			if(formId.length > 0   )
			{
				saveForm(formId,url);
			}	
		}
	}
}


function viewButtonPress(filename)
{
	var formId = jQuery('#submitForm').val();
	 
	   if( jQuery('#'+ formId + " input[id=mode]" ).length > 0 ){
			
			var mode = jQuery('#'+ formId + " input[id=mode]" ).val();
			//alert("mode"+mode);
			if( mode != null && mode != "null" && mode != undefined  && mode != 'view'&&mode.length!=0 )
			{ //  var win = window.open();
			  //alert("formId"+formId);
			   if(formId=="frmAbnormality")
			   { 
				   window.open().document.write('<embed src="Usermanuals/App3.pdf" width="1300" height="700" alt="pdf" pluginspage="http://www.adobe.com/products/acrobat/readstep2.html">');
				   window.focus();
					 window.close(); 
			   }
			   else{
				   return false;
			   }
			 
			}
	   }	


}



function refreshButtonPress(){
	/*if(jQuery('#loadFormMode').val() != null)
	{
		if(jQuery('#loadFormMode').val() != frmMode.view)
		*/
	       jQuery("#preLoadContent").css("display","block");
			refreshForm();
	//}
}

function deleteButtonPress(){

	if(jQuery('#loadFormMode').val() != null)
	{

	 	
	  if(jQuery('#loadFormMode').val() != frmMode.view && jQuery('#loadFormMode').val() != frmMode.edit)
	  {
		var formId = jQuery('#submitForm').val();

		var url = jQuery('#hiddenUrl').val();
		if(formId.length > 0   )
		{
			deleteRecord(formId,url);
		}
	  }
	}
}

 
function dynamicFunctionCall(callBackFunction,args)
{

	 var retVal = true ;

		try{
			//alert("callBackFunction   :"+callBackFunction );
			var functionName = eval( callBackFunction );
			//alert("functionName   :"+functionName );
			//alert("jQuery.isFunction(functionName)   :"+jQuery.isFunction(functionName) );
			if("dlgAddImgOnComplete(response)"==dynamicFunctionCall)
		      {
				///alert("functionName   "+functionName );
		    // alert(args);
		      }
			if( jQuery.isFunction(functionName)){	
				//Changed By Babu for IE correction
				if (args==undefined || args==null)
					retVal = window[callBackFunction].apply(this);
				else
					retVal = window[callBackFunction].apply(this,args); 
				
			}
		}catch(Exception  ){
			//alert(Exception);
			//console.error(Exception);
		}		
		
		if( retVal == false )
			return false;
		
		return retVal;
}


function enableDisableFilters(url)
{	

	if( url != null ){

		processAjaxCalls( "filterXml"+url,"","enDisFiltersXml_onSuccessCallback","enDisFiltersXml_onErrorCallback","xml");

	}	
}

function enDisFiltersXml_onSuccessCallback(xml){	


	jQuery(xml).find("form").each(function() {
		var formId = jQuery(this).attr("id");
		enableForm(formId);
		
		
		

			
	//	var header = jQuery(this).attr("headerCaption");

    	//var pp = jQuery('#Filter').tabs('getSelected');
    /*	var tab = pp.panel('options').tab;   
    	var titleC = pp.panel('options').title;
    
		console.log(" tab " + Object.keys(tab));
		console.log(" tab co2 " + Object.keys(tab.prevObject));
		//console.log(" tab co3 " + Object.keys(tab.selector));
		console.log(" tab co1 " + tab.context);
		console.log(" tab co2 " + tab.prevObject);
		console.log(" tab co3 " + tab.selector);
		
    	if( header != null && header.length> 0){
    		
    		
    	}
    */	
        jQuery(this).find("control").each(function(){
        	var id = jQuery(this).attr("id");        		
        	var disable = jQuery(this).attr("disable");
        	
        	
        	if( disable == true || disable=="true"){
        		clearField(id);
        		disableField(formId,id);
        	}
        	else
        		enableFormFields(formId,id);
        });
        var args = []; 
        
		var successCallback = formId +"_enableDisableSuccessCallBack";
		dynamicFunctionCall(successCallback,args);
 
	});
	
}

function enableDisableFilterWraper(){
	
	var hdnurl = jQuery('#hiddenUrl').val();

	var prevHiddenUrl = jQuery("#frmFilter input[id=prevHiddenUrl]").val();
	if( hdnurl != null && hdnurl.length > 0 ){
		var actionPart =hdnurl;
		if( hdnurl.indexOf('?') >= 0)
			actionPart = hdnurl.substring(0,hdnurl.indexOf('?'));
    	if( prevHiddenUrl !=  actionPart ){
    		
    		enableDisableFilters(hdnurl);
    		jQuery("#frmFilter input[id=prevHiddenUrl]").val(actionPart);
    	}	
	}
}

function enDisFiltersXml_onErrorCallback(status){
	console.log("status :"+Object.keys(status));
	console.log("status-status  :"+status.status);
	console.log("status.statusText  :"+status.statusText);
}

function clearField(fieldId)
{ 

	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("clear");
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("clear");
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner("clear");
	else
		jQuery("#"+fieldId).val(''); 

} 


function setTotalRowCss(gridId)
{
	jQuery("#"+gridId +' tr:last').addClass("totalRow");
	jQuery(' tr.totalRow').find(' td:first-child').css('color','#fec488');
	jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #fec488');
	
}

function setTotalRowColorForGroupby(gridId){
	
	jQuery("#"+gridId +' tr:last').addClass("totalRow");
	jQuery(' tr.totalRow').find(' td:first-child').css('color','#fec488');
	jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #fec488');
}

/*Extras.jsp*/
/*Hide show back on report tool Bar Grid*/
function hideShowBack(isHide){
	if(isHide){
		
		jQuery('#btnJqGridBack').css('display','block');
	}
	else if(!isHide){
		
		jQuery('#btnJqGridBack').css('display','none');
	}
}
/*disable filter icon*/
function disableFilterBtn(){
	jQuery('#filter_tab').attr('disabled','disabled');
	jQuery('#filter_tab').removeClass('filtertab');
	/**Added By Manikandan For disable in IE**/
	var isIE = !!window.ActiveXObject || "ActiveXObject" in window;
	if(isIE)
		jQuery('#filter_tab').attr('src','images/tab-menu/newt/filter_disabled.png');
	else{
		jQuery('#filter_tab').css('opacity','0.2');
		jQuery('#filter_tab').css('filter', 'alpha(opacity=20)');
	}
}
/*enable filter icon*/
function enableFilterBtn(){
	jQuery('#filter_tab').attr('disabled', false);
	/**Added By Manikandan For disable in IE**/
	/* jquery_update */
	var isIE = !!window.ActiveXObject || "ActiveXObject" in window;
	if(isIE)
		jQuery('#filter_tab').attr('src','images/tab-menu/newt/filter.png');
	jQuery('#filter_tab').addClass('filtertab');
	jQuery('#filter_tab').css('opacity','1');
}
function loadRelatedFilters(title){
	if(title == "Related" )
	{	
    	var relatedFilterHeader = jQuery("#filterUrl").val().replace(" ","");
    	var curUrl = jQuery("#curUrl").val();
    	
    	if( relatedFilterHeader != curUrl )
    	{
    		loadRelatedFilterPage(relatedFilterHeader);
    	}
	}
	enableDisableFilterWraper();	
	jQuery('#hdnfilterTitle').val(title);

}

function loadRelatedFilterPage(relatedFilterHeader){
	var url = relatedFilterHeader+".commonFilter";
	LoadForm("preLoadRelatedFilter","loadRelatedFilter",url,"dispErr","relatedFilterLoad_successCallBack");
	jQuery("#curUrl").val(relatedFilterHeader);
}

function relatedFilterLoad_successCallBack(result){
	var url = jQuery('#hiddenUrl').val();
	enableDisableFilters(url);	
}
/* * */

function getComboBoxText(id){
	return jQuery("#"+id).combobox("getText");
}
/* *Added for File Manager Pop Up on August 8th 2012 * */
function fileCountSuccess(result){
	//alert(result.formName+" -- "+result.buttonId+" -- "+result.docCount);
		jQuery("#hdnFileMgrCount").val(result.docCount);
		
	jQuery("#"+result.formName + ' span[id=lblFilemgr]').html("File Manager <span id='fileCount' style='  vertical-align:middle;' class='fileCnt'> "+result.docCount+"</span>");

	//jQuery(" <span id='fileCount' style='  vertical-align:middle;' class='fileCnt'> "+result.docCount+"</span>").insertAfter("#"+result.formName + ' span[id=lblFilemgr]');
	}
function fileManagerPopUp(documentNo,documentType,formName,buttonId,appendDivId, fmgMode,title,description){
	
	//alert("Document no :"+documentNo);
	if( appendDivId != null && appendDivId.trim().length > 0  ){
		jQuery("#"+buttonId).css('height',"21px");
		//jQuery("#"+buttonId).css('width',"105px");
		//jQuery('#'+buttonId).css('text-align',"left");
		var filemgrbtn= "<div class='filemgrBtn' id='"+buttonId+"' onclick="+buttonId+"_click();>";
		filemgrbtn+="<span id='lblFilemgr' style='padding:3px;padding-left:9px;display:inline-block; vertical-align:middle;'>File Manager</span> </div>";
		if(appendDivId.trim().length!= null || appendDivId.trim().length>0)
			jQuery("#"+appendDivId).append(filemgrbtn); 
	}
	var isIE = !!window.ActiveXObject || "ActiveXObject" in window;
		
	if (isIE){
		 jQuery(".filemgrBtn").css('width',"125px");
		 jQuery("#fileCount").css("margin-left","10px");
	}
	if(formName != null && formName.trim().length>0)
	{
		jQuery("#hdnFileName").val(formName);
		var dataStr = "?&docNo="+documentNo+'&docType='+documentType+"&buttonId="+buttonId+"&formName="+formName;
		dataStr+="&fmgMode="+fmgMode;
		
		processAjaxCalls("getFileCount.file",dataStr,"fileCountSuccess","fileCountSuccess");
		
	}
	else{
		var dataStr = "?q=2&documentNo="+ documentNo+'&documentType='+documentType+"&buttonId="+buttonId;
		dataStr+="&fmgMode="+fmgMode;
		if( title != undefined && title.trim().length >0)
			dataStr+="&title="+title;
		if( description != undefined && description.trim().length >0)
			dataStr+="&description="+description;
		
		LoadPopUp("fileManagerDivId","file_input.file"+dataStr,true,"70%","92%","1%","14%","","File Manager","","",true,"setFileManagerdimension");/*** Classname added By S.SugunaDevi***/
	}
}

function fileManagerDivId_afterClose(){
		var refDocno = jQuery("#hdnDmdmRefdocno").val();
		var formName = jQuery("#hdnformName").val();
		var refDocType = jQuery("#hdnDmdmRefdoctype").val();
		var buttonId = jQuery("#hdnbuttonId").val();
		var refDocno = jQuery("#hdnDmdmRefdocno").val();
		var docCount = jQuery("#hdnFileMgrCount").val();
		
		if(formName != null && formName.trim().length>0)
			fileManagerPopUp(refDocno,refDocType,formName,buttonId,null, "");
		else
			formName = jQuery("#hdnFileName").val();
		var recordData = '{"formName":"'+formName+'" ,"refDocType":"'+refDocType+'" ,"refDocno":"'+refDocno+'","docCount":"'+docCount+'"}';
		var record = JSON.parse(recordData);
		var afterCloseFunction = [record];
		
		dynamicFunctionCall(formName+"_afterFileMgrClose", afterCloseFunction);
	}

/* @divId
 * 
 * 
 */

var curntLoadPopSettingQ = [];
function curntFormSettings(){
	this.sbtFormId; //submitFormid
	this.submitUrl; //submiturl
	this.loadFormMode; //LoadformMode
}

function popup_OnNewForm(){
	jQuery("#ImgNew").trigger("click");
}
function popup_OnSaveForm(){
	jQuery("#ImgSave").trigger("click");
}
function popup_OnDeleteForm(){
	jQuery("#ImgDelete").trigger("click");
	
}
function popup_OnRefreshForm(){
	jQuery("#ImgRefresh").trigger("click");
}
/* 
 * 
 * @[divId] : where popup has to display : its an optional argument;
 * @url : server request page url;
 * @isModel:boolean, optional argument: default is true
 * @width,height,top,left: width,height, top, left of the popup. can use either in % or in px based on situation;
 * @loadpopUpSuccessCallBack: callback function invokes after loading the  url. Optional argument
 * @title: title of the popup
 * @inSide: boolean, optional, default false. displays popup inside the layout(Home) if true.
 * @toolBar:boolean, optional, default false. displays toolBar for new, save, delete, refresh buttons
 * 
 * fires an event when closing the popup ( divId + "_close") [optional]:  return true closes the popup 
 */

function LoadPopUpForPassword(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname){ /*** Classname added By S.SugunaDevi***/
	
	//console.log("=== LoadPopUp Started ===");
	//console.log("Parameters:", {divId, url, isModel, width, height, top, left, title, isInside, toolBar, needClose, classname});
	
	var firstTime = false;
	if( divId == null || divId.trim().length <= 0)
		divId = "LoadPopUpDiv"+curntLoadPopSettingQ.length+1;
	var appendDiv = "mainlayout";
	
	//console.log("Using divId:", divId);
	//console.log("appendDiv:", appendDiv);
	
	if ((isInside == "true" || isInside == true) )
		appendDiv = "LoadContent";
	if( url != null && url.length > 0)
	{	
		//console.log("Removing existing div if present:", "#"+divId);
		jQuery("#"+divId).remove();
		if( jQuery("#"+divId).length <= 0 ){
			//console.log("Creating new div:", divId);
			jQuery("#"+appendDiv).after('<div id="'+divId+'" > </div>' );
			if( isInside == null ||  isInside || isInside == "true" )
					jQuery("#"+divId).css('margin-top',"1%"); // //changed old value 10% to 1% by KarthicK.T
		}
		jQuery("#"+divId).css('border',"6px solid #444444").css("border-radius", "8px");
	}	
	if(  jQuery("#"+divId +' > div ').hasClass('sub-header') != true ){
		 var toolBarHtml = "<div class='sub-header' style='width:101.45%;_width:100.7%; margin-top:-10px; margin-left:-9.5px;' >";
        var loadpopuptoolbarDiv;
		if( title != null && title.length > 0)
			toolBarHtml += title ;
		
		if(toolBar == true ||toolBar=="true" )
		{
			//console.log("Adding toolbar");
			if(isIE()){
				toolBarHtml = "<div class='sub-header' style='width:102.7%;_width:107.7%; margin-top:-10px; margin-left:-9px;position:relative;' >";
				loadpopuptoolbarDiv ='<div  class ="" align="center" style="margin-top:-1.6;position: absolute; margin-top: -1px; width: 30%; top: -30%; left: 40%;"> '; 
			}else{
				loadpopuptoolbarDiv ='<div  class ="loadpopuptoolbar" align="center" style="margin-top:-1.6;"> ' ;
			}
			
             
			 toolBarHtml +=loadpopuptoolbarDiv+
						  '<img id="popupImgNew" alt="" title="New (F1)" src="images/menu-icon/imgpluse.png" style="cursor: pointer;"  width="34px" height="34px" class="top_menu_efct" onClick="popup_OnNewForm();"> ' +
						  '<img id="popupImgSave" alt=""  title="Save (F2)" src="images/menu-icon/imgsave.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct" onClick="popup_OnSaveForm();"> '+
						  '<img id="popupImgDelete" alt="" title="Delete (F3)" src="images/menu-icon/imgclose.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct" onClick="popup_OnDeleteForm();">'+
						  '<img id="popupImgRefresh" alt="" title="Refresh (F5)" src="images/menu-icon/imgreferesh.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct" onClick="popup_OnRefreshForm();">'+
						  '</div>';
			
			jQuery("#"+divId).addClass("toolBar");
		}
		if( toolBarHtml != "")
			toolBarHtml += "</div>";
		
		if( url != null && url.length > 0)
			jQuery("#"+divId).html(toolBarHtml);
		else{
			jQuery("#"+divId).prepend(toolBarHtml);
		}
	}
	else{
		jQuery("#"+divId +' > div ').text(title);
	}
	
	if( isModel == true || isModel == "true"){
		//console.log("Setting up modal mode");
		//alert("submitForm :"+jQuery("#submitForm").val());
		//alert("submitUrl :"+getSubmitFormUrl());
		//alert("loadFormMode :"+jQuery('#loadFormMode').val());
		var loadPopSetting = new curntFormSettings();
		loadPopSetting.sbtFormId = jQuery("#submitForm").val();
		loadPopSetting.submitUrl = getSubmitFormUrl();
		loadPopSetting.loadFromMode = jQuery('#loadFormMode').val();
		curntLoadPopSettingQ.push(loadPopSetting);
		var formNavObj = new formNavigation();
		formNavObj.URL = url;
		formNavObj.divId = "loadPopUp" + divId;
		formNavigations.push(formNavObj);
		if( url != null && url.trim().length > 0){
			if(url.indexOf("?")<= 0 )
				url +="?"; 
			setSubmitFormUrl(url.replace("_view","_input")+"&navigateNext=false");
		}	
		else
			setSubmitFormUrl("");
	}	
	//jQuery("#"+divId).addClass("toolBar");
	
		
	jQuery("#"+appendDiv).scrollTop( 0);
	jQuery("#"+divId).scrollTop( 0);
	
	var curentForm = null;
	if( formNavigations != null)
		curentForm = formNavigations[formNavigations.length-1];
	if( curentForm != null )
		jQuery("#"+curentForm.divId).scrollTop( 0);
	
	if( jQuery("#"+divId + " > div.LoadPopUpContent").length <=0  )
	{	
		//console.log("Creating LoadPopUpContent div (firstTime = true)");
		firstTime =true;
		if(  url != null  && url.trim().length > 0 )
			jQuery("#"+divId).append("<div class='LoadPopUpContent' style='padding:6px;height:92%;margin-top:10px;'> </div>");
		else
			jQuery("#"+divId +' > div ').append("<div class='LoadPopUpContent' style='padding:6px;height:92%;margin-top:10px;'/>");
		
		jQuery("#"+divId).addClass("custom-popup");
		jQuery("#"+divId).addClass("LoadPopUp");
		if(toolBar == true ||toolBar=="true" ){
			jQuery("#"+divId + " > div.LoadPopUpContent").css("height","90%");
			jQuery("#"+divId + " > div.LoadPopUpContent").css("margin-top","40px");
		}	
	}	
	
	// *** CRITICAL FIX: Capture display state BEFORE setting it to 'block' ***
	var display = jQuery("#"+divId).css('display');
	//console.log("*** DISPLAY STATE CAPTURED HERE ***");
	//console.log("Current display value:", display);
	//console.log("firstTime:", firstTime);
	


	if(classname!=null){
		//console.log("Adding custom classname:", classname);
		jQuery("#"+divId).addClass(classname);/*** Classname added By S.SugunaDevi***/
	}
	else{
		//console.log("Setting dimensions:", {width, height, top, left});
		if( width != null ) 
		{
			jQuery("#"+divId).css('width',width);
		}
		if( height != null ) 
		{
			jQuery("#"+divId).css('height',height);
		}
		if( top != null ) 
		{
			jQuery("#"+divId).css('top',top);
		}
		if( left != null ) 
		{
			jQuery("#"+divId).css('left',left);
		}
	}
	var maxIndex = 0;
    //if( firstTime ){ 
		//console.log("Calculating z-index...");
		jQuery(".popup-mask, .custom-popup,.filterpanel,.panel,.window").each(function(){
			var currIndex = parseInt(jQuery(this).css("z-index"), 10);
			
			
		    if(currIndex > maxIndex) {
		    	maxIndex = currIndex;
		    }
		});
		
		//console.log("Setting z-index to:", maxIndex+1);
		jQuery("#"+divId ).css("z-index",maxIndex+1);	
		if( (isModel == null || (isModel != false && isModel != 'false'))  )
		{	
			//console.log("Creating popup mask");
			if( jQuery('#'+divId +'PopupMask') == null || jQuery('#'+divId +'PopupMask').length <= 0  ){
				jQuery('body').append('<div id="'+divId +'PopupMask" class="popup-mask"></div>');
			};
			jQuery("#"+divId +"PopupMask ").css("z-index",maxIndex);
			jQuery("#"+divId +"PopupMask ").fadeIn(100);
			
			jQuery('div .layout-panel-north').css("zIndex",2);
			jQuery('div .layout-panel-north').css("z-index",2);
		}	
		//if( jQuery(".popup-mask") != null && jQuery(".popup-mask") != undefined  && jQuery(".popup-mask").css("display") == 'block')
		
	
	//jQuery("#"+divId).html("<input type='hidden' id='setFocusId' > </input>");
	//console.log("*** SETTING DISPLAY TO BLOCK ***");
	jQuery("#"+divId).css("display","block");
	//console.log("Display after setting to block:", jQuery("#"+divId).css('display'));
	
	jQuery("#"+divId ).attr("tabIndex","1");
	jQuery("#"+divId).unbind("keyup");
	jQuery("#"+divId ).focus();	
	jQuery("#"+divId).unbind("keyup");
	if(needClose != false){
		//console.log("Adding ESC key handler");
		jQuery("#"+divId).keyup( function(e){
			if( e.keyCode  == 27){
				closePopUpDialoge(this.id,true);
			}
		});
	}
	
	//console.log("*** CHECKING IF SHOULD LOAD CONTENT ***");
	//console.log("Condition check - display:", display, "url:", url);
	//console.log("Will load content:", (display == 'none' && url != null && url.trim().length > 0));
		  
	if(display == 'none' && url != null  && url.trim().length > 0 ){
		//console.log("*** LOADING CONTENT FROM URL ***");
		//if( jQuery("#loadPopUp"+divId).length <=0 )
			jQuery("#"+divId +" div.LoadPopUpContent").html("<div style='width:auto;' id= 'loadPopUp1" +divId +"' >   </div>" );
		
		//console.log("Showing loading message...");
		jQuery("#loadPopUp1"+divId).html("<div class='page-loading' style='height:100%;display:block;' ><div style='margin-top:20px;'>Please wait while loading...<div></div>");
		
		LoadForm("loadPopUp1"+divId,"",url,"", loadpopUpSuccessCallBack,"loadPopUp_ErrorCallback");
		//jQuery("#loadPopUp"+divId).addClass("save-loading");
	
		//console.log("Loading URL:", url);
		jQuery("#loadPopUp"+divId).load(url, function(response, status, xhr) {
			
			//console.log("Load complete - Status:", status);
			//console.log("Response length:", response ? response.length : 0);
			
			jQuery("#loadPopUp"+divId + ' :input:first').focus();


			if (status == "error") {					
				console.error("Load error:", xhr.status, xhr.statusText);
				var msg = "There was an error: ";
				//jQuery("#"+errMsgDispDIVId).html(msg + xhr.status + " " + xhr.statusText);
	          	
				var args = [ response];
				dynamicFunctionCall("loadPopUp_ErrorCallback", args);
			}
			else{
				//console.log("Load success - calling callback:", loadpopUpSuccessCallBack);
				var args = [ response];
				dynamicFunctionCall(loadpopUpSuccessCallBack, args);
			}
	//	  jQuery("#loadPopUp"+divId).removeClass("save-loading"); 
//		  jQuery("#"+preloadDIVid).hide(); 
//		  jQuery("#"+divId).show();
//		  jQuery("#"+loadDIVid).css("display", "block");
		  
		  if( ! loadFormSessionTimeOut(response, status, xhr) )
			  return ;

		});
		
	} else {
		//console.log("*** SKIPPING CONTENT LOAD ***");
		//console.log("Reason: display was not 'none' OR url is empty");
	}
	
	if( firstTime && needClose != false){
		//console.log("Adding dialog close image");
		addDialogCloseImage(divId);
	}
	
	//console.log("Setting timeout for show_winMask...");
	setTimeout(function(){show_winMask(4);},1250);
	
	//console.log("=== LoadPopUp Completed ===\n");
}

function LoadPopUp(divId, url, isModel,width,height,top,left, loadpopUpSuccessCallBack, title,isInside, toolBar,needClose,classname){ /*** Classname added By S.SugunaDevi***/
	
	
	var firstTime = false;
	if( divId == null || divId.trim().length <= 0)
		divId = "LoadPopUpDiv"+curntLoadPopSettingQ.length+1;
	var appendDiv = "mainlayout";
	
	if ((isInside == "true" || isInside == true) )
		appendDiv = "LoadContent";
	if( url != null && url.length > 0)
	{	
		jQuery("#"+divId).remove();
		if( jQuery("#"+divId).length <= 0 ){
			jQuery("#"+appendDiv).after('<div id="'+divId+'" > </div>' );
			if( isInside == null ||  isInside || isInside == "true" )
					jQuery("#"+divId).css('margin-top',"1%"); // //changed old value 10% to 1% by KarthicK.T
		}
		jQuery("#"+divId).css('border',"6px solid #444444").css("border-radius", "8px");
	}	
	if(  jQuery("#"+divId +' > div ').hasClass('sub-header') != true ){
		 var toolBarHtml = "<div class='sub-header' style='width:101.45%;_width:100.7%; margin-top:-10px; margin-left:-9.5px;' >";
        var loadpopuptoolbarDiv;
		if( title != null && title.length > 0)
			toolBarHtml += title ;
		
		if(toolBar == true ||toolBar=="true" )
		{
			if(isIE()){
				toolBarHtml = "<div class='sub-header' style='width:102.7%;_width:107.7%; margin-top:-10px; margin-left:-9px;position:relative;' >";
				loadpopuptoolbarDiv ='<div  class ="" align="center" style="margin-top:-1.6;position: absolute; margin-top: -1px; width: 30%; top: -30%; left: 40%;"> '; 
			}else{
				loadpopuptoolbarDiv ='<div  class ="loadpopuptoolbar" align="center" style="margin-top:-1.6;"> ' ;
			}
			
             
			 toolBarHtml +=loadpopuptoolbarDiv+
						  '<img id="popupImgNew" alt="" title="New (F1)" src="images/menu-icon/imgpluse.png" style="cursor: pointer;"  width="34px" height="34px" class="top_menu_efct" onClick="popup_OnNewForm();"> ' +
						  '<img id="popupImgSave" alt=""  title="Save (F2)" src="images/menu-icon/imgsave.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct" onClick="popup_OnSaveForm();"> '+
						  '<img id="popupImgDelete" alt="" title="Delete (F3)" src="images/menu-icon/imgclose.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct" onClick="popup_OnDeleteForm();">'+
						  '<img id="popupImgRefresh" alt="" title="Refresh (F5)" src="images/menu-icon/imgreferesh.png" style="cursor: pointer;" width="34px" height="34px" class="top_menu_efct" onClick="popup_OnRefreshForm();">'+
						  '</div>';
			
			jQuery("#"+divId).addClass("toolBar");
		}
		if( toolBarHtml != "")
			toolBarHtml += "</div>";
		
		if( url != null && url.length > 0)
			jQuery("#"+divId).html(toolBarHtml);
		else{
			jQuery("#"+divId).prepend(toolBarHtml);
		}
	}
	else{
		jQuery("#"+divId +' > div ').text(title);
	}
	
	if( isModel == true || isModel == "true"){
		var loadPopSetting = new curntFormSettings();
		loadPopSetting.sbtFormId = jQuery("#submitForm").val();
		loadPopSetting.submitUrl = getSubmitFormUrl();
		loadPopSetting.loadFromMode = jQuery('#loadFormMode').val();
		curntLoadPopSettingQ.push(loadPopSetting);
		var formNavObj = new formNavigation();
		formNavObj.URL = url;
		formNavObj.divId = "loadPopUp" + divId;
		formNavigations.push(formNavObj);
		if( url != null && url.trim().length > 0){
			if(url.indexOf("?")<= 0 )
				url +="?"; 
			setSubmitFormUrl(url.replace("_view","_input")+"&navigateNext=false");
		}	
		else
			setSubmitFormUrl("");
	}	
	//jQuery("#"+divId).addClass("toolBar");
	
		
	jQuery("#"+appendDiv).scrollTop( 0);
	jQuery("#"+divId).scrollTop( 0);
	
	var curentForm = null;
	if( formNavigations != null)
		curentForm = formNavigations[formNavigations.length-1];
	if( curentForm != null )
		jQuery("#"+curentForm.divId).scrollTop( 0);
	
	if( jQuery("#"+divId + " > div.LoadPopUpContent").length <=0  )
	{	
		firstTime =true;
		if(  url != null  && url.trim().length > 0 )
			jQuery("#"+divId).append("<div class='LoadPopUpContent' style='padding:6px;height:92%;margin-top:10px;'> </div>");
		else
			jQuery("#"+divId +' > div ').append("<div class='LoadPopUpContent' style='padding:6px;height:92%;margin-top:10px;'/>");
		
		jQuery("#"+divId).addClass("custom-popup");
		jQuery("#"+divId).addClass("LoadPopUp");
		if(toolBar == true ||toolBar=="true" ){
			jQuery("#"+divId + " > div.LoadPopUpContent").css("height","90%");
			jQuery("#"+divId + " > div.LoadPopUpContent").css("margin-top","40px");
		}	
	}	
	
	var display = jQuery("#"+divId).css('display');
	if(classname!=null){
		jQuery("#"+divId).addClass(classname);/*** Classname added By S.SugunaDevi***/
	}
	else{
		if( width != null ) 
		{
			jQuery("#"+divId).css('width',width);
		}
		if( height != null ) 
		{
			jQuery("#"+divId).css('height',height);
		}
		if( top != null ) 
		{
			jQuery("#"+divId).css('top',top);
		}
		if( left != null ) 
		{
			jQuery("#"+divId).css('left',left);
		}
	}
	var maxIndex = 0;
    //if( firstTime ){ 
		jQuery(".popup-mask, .custom-popup,.filterpanel,.panel,.window").each(function(){
			var currIndex = parseInt(jQuery(this).css("z-index"), 10);
			
			
		    if(currIndex > maxIndex) {
		    	maxIndex = currIndex;
		    }
		});
		
		jQuery("#"+divId ).css("z-index",maxIndex+1);	
		if( (isModel == null || (isModel != false && isModel != 'false'))  )
		{	
			
			if( jQuery('#'+divId +'PopupMask') == null || jQuery('#'+divId +'PopupMask').length <= 0  ){
				jQuery('body').append('<div id="'+divId +'PopupMask" class="popup-mask"></div>');
			};
			jQuery("#"+divId +"PopupMask ").css("z-index",maxIndex);
			jQuery("#"+divId +"PopupMask ").fadeIn(100);
			
			jQuery('div .layout-panel-north').css("zIndex",2);
			jQuery('div .layout-panel-north').css("z-index",2);
		}	
		//if( jQuery(".popup-mask") != null && jQuery(".popup-mask") != undefined  && jQuery(".popup-mask").css("display") == 'block')
		
			
		
		
		
		
	
	//jQuery("#"+divId).html("<input type='hidden' id='setFocusId' > </input>");
	jQuery("#"+divId).css("display","block");
	jQuery("#"+divId ).attr("tabIndex","1");
	jQuery("#"+divId).unbind("keyup");
	jQuery("#"+divId ).focus();	
	jQuery("#"+divId).unbind("keyup");
	if(needClose != false){
	jQuery("#"+divId).keyup( function(e){
		if( e.keyCode  == 27){
			closePopUpDialoge(this.id,true);
		}
	});
	}
		  
	if(display == 'none' && url != null  && url.trim().length > 0 ){
		if( jQuery("#loadPopUp"+divId).length <=0 )
			jQuery("#"+divId +" div.LoadPopUpContent").html("<div style='width:auto;' id= 'loadPopUp" +divId +"' >   </div>" );
		
		jQuery("#loadPopUp"+divId).html("<div class='page-loading' style='height:100%;display:block;' ><div style='margin-top:20px;'>Please wait while loading...<div></div>");
		//LoadForm(divId,"",url,"", loadpopUpSuccessCallBack,"loadPopUp_ErrorCallback");
		//jQuery("#loadPopUp"+divId).addClass("save-loading");
		jQuery("#loadPopUp"+divId).load(url, function(response, status, xhr) {
			
			
			jQuery("#loadPopUp"+divId + ' :input:first').focus();


			if (status == "error") {					
				var msg = "There was an error: ";
				//jQuery("#"+errMsgDispDIVId).html(msg + xhr.status + " " + xhr.statusText);
	          	
				var args = [ response];
				dynamicFunctionCall("loadPopUp_ErrorCallback", args);
			}
			else{
				var args = [ response];
				dynamicFunctionCall(loadpopUpSuccessCallBack, args);
			}
	//	  jQuery("#loadPopUp"+divId).removeClass("save-loading"); 
//		  jQuery("#"+preloadDIVid).hide(); 
//		  jQuery("#"+divId).show();
//		  jQuery("#"+loadDIVid).css("display", "block");
		  
		  if( ! loadFormSessionTimeOut(response, status, xhr) )
			  return ;

		});
		
	}//else
	if( firstTime && needClose != false)
		addDialogCloseImage(divId);
	
	setTimeout(function(){show_winMask(4);},1250);
}

function preventDefault(e) {
	  e = e || window.event;
	  if (e.preventDefault)
	      e.preventDefault();
	  e.returnValue = false;  
	}


function loadGraphPage_successCallBack(response){
	//jQuery("#divGraphContainer").prepend("<a href='#' class='close'><img src='images/dialog_close.png' width='16px;' height='16px;' class='btn_close' title='' alt='Close' /></a>");
	//addDialogCloseImage("divGraphContainer");	
	jQuery(".LoadPopUpContent").css('margin-top','-5px');
	jQuery(".LoadPopUpContent").css('padding','0px');
	jQuery(".LoadPopUpContent").css('margin-left','-10px');
	jQuery(".LoadPopUpContent").css('height','95%');
		
}

function addDialogCloseImage(divId){
	if( divId != null){

		jQuery("#"+divId).prepend("<a href='#' class='close'><img src='images/close-butt1.png' width='21px;' height='21px;' class='btn_close' style='_position:absolute;right:12px;top:7px;margin-top:-9.5'  title='Close(Esc)' alt='Close' onclick='closePopUpDialoge(&#39;"+divId+"&#39;,true);'/></a>");

		
		//jQuery("#"+divId).prepend("<a href='#' class='close'><div class='btn_close' title='Close(Esc)' alt='Close' style=' background: url(images/panel_tools.gif) no-repeat scroll -16px 0 transparent;' ></div></a>");
	}	
	
	/*jQuery("#"+divId +' > a.close').live('click', function() {
		
		closePopUpDialoge(divId);
	});
	*/
}

function closePopUpDialoge(divId,direct){
	
	if(direct!=true) direct=false;
	
	var args1 = [direct];
	if( dynamicFunctionCall(divId+"_onClose", args1) ){
		
			jQuery('#'+divId + 'PopupMask').fadeOut(100 , function() {

				jQuery(this).remove();
				var loadPopSetting = curntLoadPopSettingQ.pop();

				if(loadPopSetting != null){


					jQuery("#submitForm").val(loadPopSetting.sbtFormId);
					setSubmitFormUrl(loadPopSetting.submitUrl);
					jQuery('#loadFormMode').val(loadPopSetting.loadFromMode );
				
					//var formNavObj ;
					
					for( var i=formNavigations.length-1;i>=0;i--){
						
						/*formNavObj = formNavigations.pop();
						if( formNavObj.divId == "loadPopUp" + divId){
							break;
						}*/
						
						if( formNavigations[i].divId == "loadPopUp" + divId){
							formNavigationLog("Before pop closePopUpDialoge ");
							formNavigations.splice(i, 1);
							formNavigationLog("After pop closePopUpDialoge ");
							break;
						}
							
					}
				}
				dynamicFunctionCall(divId+"_afterClose", args1);

			});
			jQuery('#'+divId ).fadeOut(100 , function() {
				jQuery('#'+divId + 'PopupMask').remove();
				jQuery(this).remove(); //madhan 
				//jQuery('#'+divId).remove();
				//if(jQuery("#"+divId).hasClass("toolBar") ){
							//}
			});
			clearCommonErrorMsg();
			jQuery('#LoadContent_1').unbind("keydown");
			//jQuery("#"+divId +' > a.close').unbind('click');
			formNavigationLog("End pop closePopUpDialoge ");	
	}
	
}
function loadPopUp_ErrorCallback(response){
	
}

/****  Graph and Charts  *****/


function showGraphData(url,isNewPage){
	var widthGph , heightGph , topGph ;
	if(isIE()){
	widthGph = "108%";
	heightGph = "430px";
	topGph = "-2%"; //Width and height changed by Sugunadevi
	}else{
	widthGph = "95%";
	heightGph = "95%";
	topGph = "0%";
	}
	if( isNewPage == true)
	LoadPopUp("divGraphContainer",url,true,widthGph,heightGph,topGph,"1px","loadGraphPage_successCallBack", "Chart",true);
	else
	LoadPopUp("divGraphContainer","test.charts?url="+ escape(url),true,widthGph,heightGph,topGph,"1px","loadGraphPage_successCallBack", "Chart",true);
	/*var display = jQuery("#divGraphContainer").css('display');
	jQuery("#divGraphContainer").addClass("custom-popup");
	jQuery('body').append('<div id="dialog-mask"></div>');
	jQuery('#dialog-mask').fadeIn(300);

	if(display == 'none' ){
	LoadForm("divGraphContainer","","test.charts?url="+ escape(url),"", "loadGraphPage_successCallBack");

	}
	*/
}
/*function loadGraphPage_successCallBack(response){
	jQuery("#divGraphContainer").prepend("<a href='#' class='close'><img src='images/dialog_close.png' width='16px;' height='16px;' class='btn_close' title='' alt='Close' /></a>");


	jQuery('a.close').live('click', function() { 
		jQuery('.custom-popup').fadeOut(300 , function() {
			jQuery('#dialog-mask').remove();  
		});
	});	
}
*/
function showChageData(url){ 
}

function hideGraphData(){

	 var display = jQuery("#divGraphContainer").css('display');
	 
	 if(display == 'block' )
		 jQuery("#divGraphContainer").slideToggle(200);
}


/*check for zero in row*/
function checkForZeroes(tableId,rowid,colStart){
	
	var rowData = jQuery("#"+tableId).jqGrid("getRowData",rowid );
	var colModel = jQuery("#"+tableId).jqGrid("getGridParam","colModel" );

	for(var j= colStart;j<colModel.length;j++)	{
		 if( rowData[ colModel[j].name ] != null  && rowData[ colModel[j].name ] != '0' && rowData[ colModel[j].name ] != 0 ){
			 return true;
		 }
	}
	return false;
}


function getParamName(rowid)
{
	if(rowid.substring(0,3)=='CMP')
		return 'cmbCompid';
	else if(rowid.substring(0,3)=='LCN')
		return 'cmbLocnid';
	else if(rowid.substring(0,3)=='FCT')
		return 'cmbFactid';
	else if(rowid.substring(0,3)=='LIN')
		return 'cmbSectid';
	else if(rowid.substring(0,3)=='CEL')
		return 'cmbCellid';
	else if(rowid.substring(0,3)=='MCH')
		return 'cmbMchid';
	else if(rowid.substring(0,3)=='ASM')
		return 'cmbAssmbid';
	else if(rowid.substring(0,3)=='PHM')
		return 'cmbPhenomenaId';
	else if(rowid.substring(0,3)=='PRS')
		return 'cmbprocess';
	else if(rowid.substring(0,3)=='QPH')
		return 'cmbdefphen';
	else if(rowid.substring(0,3)=='QCM')
		return 'cmbCauseId';
	else if(rowid == 'TOTAL')
		return 'rowTotal';
	else
		return null;
}

function msgBox(msg){
	//jQuery.messager.confirm("Info",msg);
	
	alert(msg);
}
/* Created By Suresh.K */

function spinnerChange(spinnerId,callBackFunc)
{       	
		jQuery("#"+spinnerId).change(function(){	
		alert(spinnerId);	 
		if( typeof eval('('+callBackFunc +')') == 'function')
		{
			eval('( '+ callBackFunc +'())');
		}
		});
		jQuery("#"+spinnerId).spinner({
				onChange:function(){
					//alert(spinnerId);
					if( typeof eval('('+callBackFunc +')') == 'function')
					{
						eval('( '+ callBackFunc +'())');
					}
				}
				});
}
function spinnerUp(spinnerId,callBackFunc)
{
		jQuery("#"+spinnerId).spinner({
		onSpinUp:function(){
			if( typeof eval('('+callBackFunc +')') == 'function')
			{
				eval('( '+ callBackFunc +'())');
			}
		}
		});
}
function spinnerDown(spinnerId,callBackFunc)
{
		jQuery("#"+spinnerId).spinner({
		onSpinDown:function(){
			if( typeof eval('('+callBackFunc +')') == 'function')
			{
				eval('( '+ callBackFunc +'())');
			}
		}
		});
}

function spinnerKeyPress(spinnerId)
	{
		var tempField = null;
		 jQuery('#'+spinnerId).keydown(function(event) {
			/* if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || 
		        		
		             // Allow: Ctrl+A
		            (event.keyCode == 65 && event.ctrlKey === true)
		             // Allow: home, end, left, right
		            (event.keyCode >= 35 && event.keyCode <= 39 ) ) {
		                 // let it happen, don't do anything
		                 return;
		        }
		        
		        else {*/
			 
		            // Ensure that it is a number and stop the keypress			
		            if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 ) && (event.keyCode < 35 || event.keyCode > 40 ) && event.keyCode != 8 && event.keyCode !=9 && event.keyCode !=46) {
		            	
		            	event.preventDefault(); 
		            }
		            else 
		            {
		             var el =jQuery('#'+spinnerId).spinner('getValue');
					 var s =  jQuery('#'+spinnerId).prop('selectionStart');     
					
					 if(s==5)
					 {
						 
						 if(event.keyCode != 37 && event.keyCode != 8 && event.keyCode !=9 && event.keyCode != 46)
							 event.preventDefault();
					 }
					 if(s==2)
					 {						
						
						if(event.keyCode != 37)
						{
							event.preventDefault(); 
							 jQuery('#'+spinnerId).prop({
					                selectionStart: 3,
					                selectionEnd: 3
					            });
						}
					 }
					
					 if(s==0)
					 {
						
						//if ((event.keyCode == 48 || event.keyCode == 49 || event.keyCode ==50 || event.keyCode == 96 || event.keyCode == 97 || event.keyCode == 98) ) {
							var hrs = el.substring(0,el.indexOf(':'));
							tempField = hrs;
						//}
						 if(event.keyCode == 38)
						{
						 		if(hrs == 23)
							 		hrs = 0;
						 		else
							 		hrs = parseInt(hrs) + parseInt(1);
						 		
						 	var timeInc = 	hrs + el.substring(el.indexOf(':'));								 	
						 	jQuery('#'+spinnerId).spinner('setValue',timeInc);
						 }
						 if(event.keyCode == 40)
						 {
							 
						 		if(hrs == 0 || hrs == 0)
							 		hrs = 23;
						 		else
							 		hrs = parseInt(hrs) - parseInt(1);
			
							var timeDec = 	hrs + el.substring(el.indexOf(':'));
							jQuery('#'+spinnerId).spinner('setValue',timeDec);									 		
						 }
						//else
							//event.preventDefault(); 
						 if(event.keyCode != 37)
						 {
							 jQuery('#'+spinnerId).prop({
					                selectionStart: 0,
					                selectionEnd: 2
					            });
						 }
						 
					 }
					 if(s==3)
					 {						
						
						 var mins = el.substring(el.indexOf(':')+1);																		
						 if(event.keyCode == 38)
						 {
						 		if(mins == 59)
						 			mins = 0;
						 		else
						 			mins = parseInt(mins) + parseInt(1);
						 		
						 	var timeInc = 	el.substring(0,el.indexOf(':')+1) + mins;								 	
						 	jQuery('#'+spinnerId).spinner('setValue',timeInc);
						 }
						 if(event.keyCode == 40)
						 {
						 		if(mins == 0)
						 			mins = 59;
						 		else
						 			mins = parseInt(mins) - parseInt(1);
						 		
						 	var timeDec = 	el.substring(0,el.indexOf(':')+1) + mins;								 	
						 	jQuery('#'+spinnerId).spinner('setValue',timeDec);									 		
						 }
						 if(event.keyCode != 37)
						 {
							 jQuery('#'+spinnerId).prop({
					                selectionStart: 3,
					                selectionEnd: 5
					            });			
						 }
						 
					 }          
				 }
		       // }
});
}


function getChkBoxVal(Id) {		
	
	if(jQuery('#'+Id).is(':checked') == true)  		
		return 1;
	else
		return 0;
}

function monthDiff(val,fieldId)
{	
	var serverTime = srvTime();
	var currentTime =  new Date(serverTime);
	//var currentTime = new Date();
	var month = currentTime.getMonth();	
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var monthCount = month-parseInt(val);	
	if(monthCount >=0)	
		month = getMonthStringFromInt(monthCount);
	else
		{
		month = getMonthStringFromInt(12 + monthCount);
		year=year-1;		
		}	
	
	jQuery("#"+fieldId).datebox('setValue',month+'-'+year);
		
}

function setLoadFormCallBackFrmId(frmId){ 
	 
	jQuery("#loadCallBackFrmId").val(frmId);
}

function getLoadFormCallBackFrmId(){
	return jQuery("#loadCallBackFrmId").val();
}

var xmlHttp;
function srvTime(){
	try {
		//FF, Opera, Safari, Chrome
		xmlHttp = new XMLHttpRequest();
	}
	catch (err1) {
		//IE
		try {
			xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
		}
		catch (err2) {
			try {
				xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
			}
			catch (eerr3) {
				//AJAX not supported, use CPU time.
				
			}
		}
	}
	xmlHttp.open('HEAD',window.location.href.toString(),false);
	xmlHttp.setRequestHeader("Content-Type", "text/html");
	xmlHttp.setRequestHeader("x-getServerDate", "Y");
	xmlHttp.send();
	return xmlHttp.getResponseHeader("Date");
}

function getServerDateTime(){
	var dateTime = srvTime();
	if( dateTime != undefined)
		return new Date(dateTime);
	else
		return new Date();
	
}

function disableGridSort(gridID){
	jQuery("#"+gridID).children().removeClass("ui-jqgrid-sortable");
}
function getChartHtml(chartContainerId,ChartTitle,addHtml)
{
	var mgTop;
	if (isIE ) {
		mgTop=2;
	}else
		mgTop=-100;
	var bindImg =  '<div id="ImgGraphDiv_'+chartContainerId+'" style="padding-bottom:0px;">';
	if(addHtml == "dashboard"){
	bindImg += '<div id="ChrtNewTitle" class="sub-header" style="margin-top:'+mgTop+';text-align:center;">'+ ChartTitle +'</div>';
	}
	bindImg += '<div id="dashTool" class="dashToolBar" style="">';
	bindImg += '<span class="tBarImg" style=""><img id="imgPrintChart_'+chartContainerId+'" alt="Print"  src="images/chart_print.gif" title="Print"></span><span style="" class="tBarImg"><img id="imgExportChart_'+chartContainerId+'" title="Export to Excel"  src="images/chart_datatable.png"  ></span>';
	if(addHtml == "dashboard"){
	bindImg +='<span id="spnzoomin" class="tBarImg" style=""><img id="imgZoomin_'+chartContainerId+'" title="ZoomIn"  src="images/zoom_in.png" onclick=funcZoom("'+chartContainerId.substring(chartContainerId.indexOf("_")+1,chartContainerId.length )+'") ></span>';
	//<input type="button" class="zomBtn " title="Zoom In " onclick=funcZoom("'+chartContainerId.substring(chartContainerId.indexOf("_")+1,chartContainerId.length )+'");></input></span>';zoom_out_1_2
	//bindImg +='<span id="spnzoomout" style="position:absolute;display:none"><input type="button" class="zomOut"  title="Zoom Out "  style="position:absolute;top:-18px;left:55px;" id="btnzoomOut" onclick="funcZoomout();"/></span>';
	bindImg +='<span id="spnzoomout" class="tBarImg" style="display:none;"><img id="imgZoomout_'+chartContainerId+'" title="Zoomout"  src="images/zoom_out.png"  onclick="funcZoomout();") ></span>';
	}
	bindImg += '</div>';
	bindImg += "<div class='loadExpToExcel' id='loadExcFormat_"+chartContainerId+"' style='margin-left:-140px;margin-top:-16px; height:42px ;'><span onclick=closeXlContiner('loadExcFormat_"+chartContainerId+"'); style='float:right;font-weight:bold;margin-top:-10;margin-right:-12;cursor:pointer;'>X</span><div class='loadExc' id='loadFormat_"+chartContainerId+"'></div></div></div>";
    bindImg += '<input type="hidden" id="hdnChartJsonData_'+chartContainerId+'" name="hdnChartJsonData_'+chartContainerId+'"/>';
  
    bindImg += '<div id="graphContainer_'+chartContainerId+'">';
    bindImg += '<div id="chtContainer_'+chartContainerId+'" style="position:relative;height:366px;display:block;width:100%" ></div>';
    bindImg += '<div id="dataTableContainer_'+chartContainerId+'"  style="position:relative; width:100%;overflow=auto;display:none;" >';
    bindImg += '</div>';
    bindImg += '</div>';
    bindImg += '<div id="dummyTableContainer" style="display:none">';
    bindImg +='</div>'; 
	
    return bindImg;
}

function bindChartHtml(chartData,chartContainerId,buildTable,showTable,showImage,bindImg,showTitle)
{
	
	/*var bindImg =  '<div id="ImgGraphDiv_'+chartContainerId+'" style="padding-bottom:0px;">';
	bindImg += '<img id="imgPrintChart_'+chartContainerId+'" alt=""  src="images/chart_print.gif" title="Print"><img id="imgExportChart_'+chartContainerId+'" alt=""  src="images/chart_datatable.png" title="Show Datatable" >';
	//bindImg +='<span id="spnzoomin"><input type="button" class="zomBtn" chrtDt="'+chartData+'" onclick=funcZoom("'+chartContainerId.substring(chartContainerId.indexOf("_")+1,chartContainerId.length )+'");></input></span>';
	bindImg += "<div class='loadExpToExcel' id='loadExcFormat_"+chartContainerId+"' style='margin-left:-140px;margin-top:-10px;'><div class='loadExc' id='loadFormat_"+chartContainerId+"'></div></div></div>";
    bindImg += '<input type="hidden" id="hdnChartJsonData_'+chartContainerId+'" name="hdnChartJsonData_'+chartContainerId+'"/>';
  
    bindImg += '<div id="graphContainer_'+chartContainerId+'">';
    bindImg += '<div id="chtContainer_'+chartContainerId+'" style="position:relative;height:316px;display:block;" ></div>';
    bindImg += '<div id="dataTableContainer_'+chartContainerId+'"  style="position:relative; width:80%%;overflow=auto;display:none;" >';
    bindImg += '</div>';
    bindImg += '</div>';
    bindImg += '<div id="dummyTableContainer" style="display:none">';
    bindImg +='</div>'; */
	
    jQuery('#'+chartContainerId).html(bindImg);
    if(showTable == 'Y')
    	jQuery("#dataTableContainer_"+chartContainerId).css('display','block');
    if(buildTable == 'Y')
    	buildDataTable(chartData,"dataTableContainer_"+chartContainerId,'hdnChartJsonData_'+chartContainerId);
    if(showImage == 'N')
    {
    	//jQuery('#ImgGraphDiv_'+chartContainerId).css('display','none');
    	jQuery('#spnzoomin').css('display','none');
    	jQuery('#spnzoomout').css('display','block');
    }
    else{
    	//jQuery('#spnzoomout').css('display','none');
    	
    }
 
    if(showTitle == 'N'){
    	jQuery('#ChrtNewTitle').css('display','block');
    }
    else{
    	jQuery('#ChrtNewTitle').css('display','block');
    }
    
}
function closeXlContiner(xlId){
	
	jQuery("#"+xlId).slideToggle(200);
}
function drawChart(chartData,chartContainerId,buildTable,showTable,showImage,showTitle,addHtml){
	   
	 var ChartTitle = '';


	/**for dash board title*/
	//if(showTitle =='N'){
		
		  ChartTitle = chartData.title.text;
		
	  //}
	/**Added By Manikandan for ie**/
	if(isIE() && "graprZoom"==chartContainerId){
		chartData.width = 1200;
		chartData.height =350;
		
	//title: (showTitle =='N'?'':chartData.title)
	//	ChartTitle = chartData.title.text;
	}
	//ChartTitle = chartData.title.text;
	var bindImg = getChartHtml(chartContainerId,ChartTitle,addHtml);	
	//alert("bindImg"+bindImg);
	bindChartHtml(chartData,chartContainerId,buildTable,showTable,showImage,bindImg,showTitle);
	new Highcharts.Chart({
		chart: {
			renderTo: 'chtContainer_'+chartContainerId,//'chartContainer'
			defaultSeriesType: chartData.chartType,
			margin: [25,50,100,80],
			zoomType: 'xy',
			polar:chartData.polar !=null && chartData.polar != undefined ? chartData.polar:false,
			width: chartData.width != null &&  chartData.width != undefined ? chartData.width : 0,
			height: chartData.height != null && chartData.height != undefined ? chartData.height:0
		},
	//	title: (showTitle =='N'?'':chartData.title),
		title: (showTitle =='N'?'':chartData.title),
		//subtitle:(showTitle =='Y'?'':chartData.title),
		subtitle: chartData.subTitle,
		xAxis: chartData.xAxis,
		yAxis: chartData.yAxis,
		tooltip: {
			formatter: chartData.tooltip != null && chartData.tooltip.shared == true? '': function() {
				var s;
				if (this.point.name) { // the pie chart
					s = ''+
						this.point.name +': '+ this.y +'';
				} else {
					s = ''+
						this.x  +': '+ this.y;
				}
				return s; //'' + eval(chartData.tooltip.x) +': '+ eval(chartData.tooltip.y) +'';
			},
			shared: chartData.tooltip != null && chartData.tooltip.shared != null && chartData.tooltip.shared != undefined?chartData.tooltip.shared:false
		},
		plotOptions:chartData.plotOption,
		series: chartData.series,
		legend: chartData.legend
	});
	chartImageEvent(chartContainerId);
	
}

/* Added By sugumar For KPI*/
function drawChartKPI(chartData,chartContainerId,buildTable,showTable,showImage,showTitle,addHtml,Params,calyr,freq,flid,actvalue){
	 //  alert("drawchartkpi");
	 var ChartTitle = '';
	/**for dash board title*/
	if(showTitle =='N'){
		
		  ChartTitle = chartData.title.text;
		
	   }
	/**Added By Manikandan for ie**/
	if(isIE() && "graprZoom"==chartContainerId){
		chartData.width = 1200;
		chartData.height =350;
	//	ChartTitle = chartData.title.text;
	}
	var bindImg = getChartHtml(chartContainerId,ChartTitle,addHtml);	
	bindChartHtml(chartData,chartContainerId,buildTable,showTable,showImage,bindImg,showTitle);
	new Highcharts.Chart({
		chart: {
			renderTo: 'chtContainer_'+chartContainerId,//'chartContainer'
			defaultSeriesType: chartData.chartType,
			margin: [25,50,100,80],
			zoomType: 'xy',
			polar:chartData.polar !=null && chartData.polar != undefined ? chartData.polar:false,
			width: chartData.width != null &&  chartData.width != undefined ? chartData.width : 0,
			height: chartData.height != null && chartData.height != undefined ? chartData.height:0
		},
		title: (showTitle =='N'?'':chartData.title),
		subtitle: chartData.subTitle,
		xAxis: chartData.xAxis,
		yAxis: chartData.yAxis,
		tooltip: {
			formatter: chartData.tooltip != null && chartData.tooltip.shared == true? '': function() {
				var s;
				if (this.point.name) { // the pie chart
					s = ''+
						this.point.name +': '+ this.y +'';
				} else {
					s = ''+
						this.x  +': '+ this.y;
				}
				return s; //'' + eval(chartData.tooltip.x) +': '+ eval(chartData.tooltip.y) +'';
			},
			shared: chartData.tooltip != null && chartData.tooltip.shared != null && chartData.tooltip.shared != undefined?chartData.tooltip.shared:false
		},
		plotOptions:chartData.plotOption,
		series: chartData.series,
		legend: chartData.legend
	});
	chartImageEventKPI(chartContainerId,Params,calyr,freq,flid,actvalue);
	
}
///////////////////////////////////
/*Added By sugumar for KPI*/
function chartImageEventKPI(chartContainerId,Params,calyr,freq,flid,actvalue)
{
	// alert("charinmagKPI");
	jQuery("#imgPrintChart_"+chartContainerId).click(function(){		
		 PrintDivData('graphContainer_'+chartContainerId);
	});
	jQuery("#imgExportChart_"+chartContainerId).click(function(){	
		jQuery.noConflict();
		
		if( jQuery('#loadExcFormat_'+chartContainerId).parent('div').parent('div').attr("id")=='grapContainerZoom' ){
			jQuery('#loadExcFormat_'+chartContainerId).css('margin-left','140');
			jQuery('#loadExcFormat_'+chartContainerId).css('margin-top','36');
			jQuery("#loadExcFormat_"+chartContainerId).slideToggle(200);
		}
		else{
			
			jQuery('#loadExcFormat_'+chartContainerId).css({'margin-left':'-140','margin-top':'18'});
			jQuery("#loadExcFormat_"+chartContainerId).slideToggle(200);
		}
		
		
		var loadHtml = "<div style='padding-bottom:1px;'><a href='#' id='Excel3_"+chartContainerId+"' name='Excel3_"+chartContainerId+"'> Send To xls</a></div>";
			loadHtml += "<div><a href='#' id='Excel7_"+chartContainerId+"' name='Excel7_"+chartContainerId+"' style='padding-top:5px;'> Send To xlsx</a></div>";
		jQuery("#loadFormat_"+chartContainerId).html(loadHtml);
		jQuery( '#Excel3_'+chartContainerId).click(function (){
			sendChartToExcelForKPI("3","loadExcFormat_"+chartContainerId,"",Params,calyr,freq,flid,actvalue);
		});
		jQuery( '#Excel7_'+chartContainerId).click(function (){
			sendChartToExcelForKPI("7","loadExcFormat_"+chartContainerId,"",Params,calyr,freq,flid,actvalue);
		});		
	});
}
////////////////////////////////
function chartImageEvent(chartContainerId)
{
	jQuery("#imgPrintChart_"+chartContainerId).click(function(){		
		 PrintDivData('graphContainer_'+chartContainerId);
	});
	jQuery("#imgExportChart_"+chartContainerId).click(function(){	
		jQuery.noConflict();
		
		if( jQuery('#loadExcFormat_'+chartContainerId).parent('div').parent('div').attr("id")=='grapContainerZoom' ){
			jQuery('#loadExcFormat_'+chartContainerId).css('margin-left','140');
			jQuery('#loadExcFormat_'+chartContainerId).css('margin-top','36');
			jQuery("#loadExcFormat_"+chartContainerId).slideToggle(200);
		}
		else{
			
			jQuery('#loadExcFormat_'+chartContainerId).css({'margin-left':'-140','margin-top':'18'});
			jQuery("#loadExcFormat_"+chartContainerId).slideToggle(200);
		}
		
		
		var loadHtml = "<div style='padding-bottom:1px;'><a href='#' id='Excel3_"+chartContainerId+"' name='Excel3_"+chartContainerId+"'> Send To xls</a></div>";
			loadHtml += "<div><a href='#' id='Excel7_"+chartContainerId+"' name='Excel7_"+chartContainerId+"' style='padding-top:5px;'> Send To xlsx</a></div>";
		jQuery("#loadFormat_"+chartContainerId).html(loadHtml);
		jQuery( '#Excel3_'+chartContainerId).click(function (){
			sendChartToExcel("3","loadExcFormat_"+chartContainerId);
		});
		jQuery( '#Excel7_'+chartContainerId).click(function (){
			sendChartToExcel("7","loadExcFormat_"+chartContainerId);
		});		
	});
}
/* Sugumar created For KPI-14Apr2016*/
function sendChartToExcelForKPI(format,loadId,multiple,params,calyr,freq,flid,actvalue)
{  // alert("isnide the kpi method");
   var ChartName;
	if(jQuery("#formxlexport").length <= 0 )
	{	  
	     var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST" > ' +
	        		   	  '<input type="hidden" id="fileName" name="fileName" /> '+
	        		      '<input type="hidden" id="f" name="f"  /> '+
	        		      '<input type="hidden" id="multiple" name="multiple"  /> '+
	        		      '<input type="hidden" id="fileName" name="fileName"  /> '+
	        		      '<input type="hidden" id="chartData" name="chartData"  /> '+
	      	  	          '<input type="hidden" id="exporthtml" name="exporthtml"/> '+
	        		      '<input type="hidden" id="indicatorid" name="indicatorid"/> '+
	        		      '<input type="hidden" id="frequency" name="frequency"/> '+
	        		      '<input type="hidden" id="actvalue" name="actvalue"/> '+
	        		      '<input type="hidden" id="flid" name="flid"/> '+
	        		      '<input type="hidden" id="calyear" name="calyear"/> </form>';
	     jQuery("#LoadContent").prepend(xlFormHtml);
	     
	 }
	 jQuery("#formxlexport input[id=f]").val(format);
	 if( multiple == undefined )
		 multiple = "N";
	 if(ChartName == undefined)
		 ChartName ="";
	 
	 jQuery("#formxlexport input[id=multiple]").val(multiple);
	 jQuery("#formxlexport input[id=fileName]").val("");
	 jQuery("#formxlexport input[id=indicatorid]").val(params);
	 jQuery("#formxlexport input[id=frequency]").val(freq);
	 jQuery("#formxlexport input[id=flid]").val(flid);
	 jQuery("#formxlexport input[id=actvalue]").val(actvalue);
	 jQuery("#formxlexport input[id=calyear]").val(calyr);
	 jQuery("#formxlexport input[id=chartData]").val(jQuery('#hdnChartJsonData'+loadId.substring(loadId.indexOf('_'))).val());	 
	 setTimeout(function(){
	 document.formxlexport.method='POST';
    // alert(jQuery("#formxlexport input[id=indicatorid]").val());
	 document.formxlexport.action="exportChart.kpiActKk?&q=2"+params;//f="+format+"&multiple="+multiple +"&fileName="+ ChartName + "&chartData="+ jQuery('#hdnChartJsonData'+loadId.substring(loadId.indexOf('_'))).val();  // send it to server which will open this contents in excel file
//	 document.formxlexport.action="exportChart.charts?f="+format+"&multiple="+multiple +"&fileName="+ ChartName + "&chartData="+ jQuery('#hdnChartJsonData'+loadId.substring(loadId.indexOf('_'))).val();  // send it to server which will open this contents in excel file
	 document.formxlexport.target='_blank';
	 document.formxlexport.submit();
	 jQuery("div[id^="+loadId+"]").hide(200);
	 },600);
}

/* Sugumar created For KPI-14Apr2016*/

function sendChartToExcel(format,loadId,multiple,params){  
	
	var ChartName;
	if(jQuery("#formxlexport").length <= 0 )
	{	  
	     var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST" > ' +
	        		   	  '<input type="hidden" id="fileName" name="fileName" /> '+
	        		      '<input type="hidden" id="f" name="f"  /> '+
	        		      '<input type="hidden" id="multiple" name="multiple"  /> '+
	        		      '<input type="hidden" id="fileName" name="fileName"  /> '+
	        		      '<input type="hidden" id="chartData" name="chartData"  /> '+
						  '<input type="hidden" id="colModel" name="colModel"/> ' +
	      	  	          '<input type="hidden" id="exporthtml" name="exporthtml"/> </form>';
	     jQuery("#LoadContent").prepend(xlFormHtml);
	        
	 }
	 jQuery("#formxlexport input[id=f]").val(format);
	 if( multiple == undefined )
		 multiple = "N";
	 if(ChartName == undefined)
		 ChartName ="";
	 
	 jQuery("#formxlexport input[id=multiple]").val(multiple);
	 jQuery("#formxlexport input[id=fileName]").val("");
	 jQuery("#formxlexport input[id=chartData]").val(jQuery('#hdnChartJsonData'+loadId.substring(loadId.indexOf('_'))).val());	 
	 setTimeout(function(){
	 document.formxlexport.method='POST';

 document.formxlexport.action="exportChart.charts";//f="+format+"&multiple="+multiple +"&fileName="+ ChartName + "&chartData="+ jQuery('#hdnChartJsonData'+loadId.substring(loadId.indexOf('_'))).val();  // send it to server which will open this contents in excel file
 //document.formxlexport.action="exportChart.charts?f="+format+"&multiple="+multiple +"&fileName="+ ChartName + "&chartData="+ jQuery('#hdnChartJsonData'+loadId.substring(loadId.indexOf('_'))).val();  // send it to server which will open this contents in excel file
	// document.formxlexport.action="exportChart.kpiActKk?&q=2"+params;
	 document.formxlexport.target='_blank';
	 document.formxlexport.submit();
	 jQuery("div[id^="+loadId+"]").hide(200);
	 },600);
}
function buildDataTable(chartData,dataTableId,hdnField)
{
	var series = chartData.series;	
	var type =  series[0].type;
	
	if(type != null && type != '' && type != ' ' && type != 'undefined')
	{
		if(type == "spline" || type=="line") 
			jQuery("#"+dataTableId).html(lineDatatable(chartData,hdnField));
		if(type == "pie")
			jQuery("#"+dataTableId).html(pieDatatable(chartData,hdnField)); 
		if(type == "column") 
			jQuery("#"+dataTableId).html(lineDatatable(chartData,hdnField));
	}
	else
		jQuery("#"+dataTableId).html(combinedPieDatatable(chartData,hdnField));
}
function lineDatatable(chartData,hdnField)
{
	var header = chartData.xAxis.categories;
	var series = chartData.series;
	//var dataCount =  series[0].data;
	var headerDatas = '["';
	var rowHeaders = '["';
	var fields = '[';
	var type = "line";
	var tempType = null;
	var typeFlag = '';
	var tableHtml = "<table class='graphDatatable' style='width:100%;'><tr class='graphDatatableHeader'><td></td>";
	for(var i=0;i<header.length;i++)
	{
		tableHtml += "<td style='text-align:center;word-wrap: break-word;'>"+header[i]+"</td>";
		headerDatas += replaceAllDoubleQuots(header[i]) + '","';
	}
	headerDatas = headerDatas.substring(0,headerDatas.length-2)+"]";

	tableHtml += "</tr>";
	for(var i=0;i<series.length;i++)
	{
		
		fields += "[";
		var datas = series[i].data;
		rowHeaders +=  ( series[i].name != undefined && series[i].name != 'undefined'  ? replaceAllDoubleQuots(series[i].name) : "count" )  + '","' ;
		typeFlag += series[i].type;
		if(series[i].yAxis != null && series[i].yAxis != '' && series[i].yAxis != ' '&& series[i].yAxis != 'undefined' && series[i].yAxis == '1')
			type = 'LineSecondary';
		
		if(series[i].type != null && series[i].type != '' && series[i].type != ' '&& series[i].type != 'undefined' && series[i].type == 'column')
			tempType = 'Pareto';
		tableHtml +="<tr class='graphDatatableRow'><td class='graphTableDatasHeader'>" + ( series[i].name != undefined && series[i].name != 'undefined'  ? series[i].name : "count" )   +"</td>";
		if( ! isArray(datas[0])){
			for(var j=0;j<datas.length;j++)
			{
				if(datas[j] != null && datas[j] != '' && datas[j] != ' ' && datas[j] != 'null')
					tableHtml += "<td class='graphTableDatas'>"+datas[j]+"</td>";
				else
					tableHtml += "<td class='graphTableDatas'></td>";
				fields += datas[j] + ",";
			}
		}
		else{
			for(var j=0;j<datas.length;j++)
			{
				if(datas[j][1] != null && datas[j][1] != '' && datas[j][1] != ' ' && datas[j][1] != 'null')
					tableHtml += "<td class='graphTableDatas'>"+datas[j][1]+"</td>";
				else
					tableHtml += "<td class='graphTableDatas'></td>";
				fields += datas[j][1] + ",";
			}
		}
		fields = fields.substring(0,fields.length-1)+"],";	
		tableHtml +="</tr>";
	}	
	if(chartData.chartType != null && chartData.chartType != '' && chartData.chartType != ' '&& chartData.chartType != 'undefined' && chartData.chartType == 'column')
	{
		tempType = chartData.title.text.indexOf('Pareto')>=0?tempType: series[0].type;
	}
	if(tempType != null)
	{
		tempType = chartData.title.text.indexOf('Pareto')>=0?tempType: series[0].type;
		if(typeFlag.indexOf('column')>=0 && typeFlag.indexOf('line')>=0 )
			tempType = 'Pareto';
		type=tempType;
	}
	tableHtml +="</table>";
	rowHeaders = rowHeaders.substring(0,rowHeaders.length-2)+"]";		
	fields = fields.substring(0,fields.length-1)+"]";
	var yAxisTitle = (chartData.yAxis!= null && chartData.yAxis !=  undefined )? chartData.yAxis[0].title.text:'';
	var toSend = '{"chartDatas":{"headers":{"rowHeaders":' + rowHeaders +',"title":"'+replaceAllDoubleQuots(chartData.title.text)+'","colHeaders":'+headerDatas +',"xAxis":"'+chartData.xAxis.title.text+'","yAxis":"'+yAxisTitle +'"}},';
	toSend +=  '"values":{"type":"'+type+'","datas":'+fields+'}}';	
	jQuery('#'+hdnField).val(toSend);//headerDatas + ';'+rowHeaders+';'+fields
	return tableHtml;
	
}
/*function pieDatatable(chartData,hdnField)
{
	jQuery('#'+hdnField).val('');
	var series = chartData.series;
	var headerDatas = "[]";
	var rowHeaders = "[";
	var fields = '[';
	var tableHtml = "<table class='graphDatatable' style='width:60%;'><tr class='graphDatatableHeader'><td style='width:50%;'></td><td style='width:30%;'></td></tr>";
	var tableDatas = "";
	for(var i=0;i<series.length;i++)
	{
		
		var dataArr = series[i].data;
		for(var i=0;i<dataArr.length;i++)
		{
			fields += "[";
			tableHtml +="<tr class='graphDatatableRow'><td class='graphTableDatasHeader'>"+dataArr[i][0] +"</td>";
			tableHtml  += "<td class='graphTableDatas'>"+dataArr[i][1]+"</td>";
			tableHtml += "</tr>";
			rowHeaders += '"'+ replaceAllDoubleQuots(dataArr[i][0])+ '",';
			fields += dataArr[i][1] + "],";
		}
		//fields = fields.substring(0,fields.length-1)+"],";	
	}
	tableHtml +="</table>";
	rowHeaders = rowHeaders.substring(0,rowHeaders.length-1)+"]";		
	fields = fields.substring(0,fields.length-1)+"]";	

	var toSend = '{"chartDatas":{"headers":{"rowHeaders":'+ rowHeaders +',"title":"'+replaceAllDoubleQuots(chartData.title.text)+'","colHeaders":'+ headerDatas +',"xAxis":"","yAxis":""}},';
	toSend +=  '"values":{"type":"pie","datas":'+fields+'}}';	
	jQuery('#'+hdnField).val(toSend);
	return tableHtml;
}*/
/** PIE ARE MODIFIED BY ILANTHAMILAN  AT 27AUG2016*/
function pieDatatable(chartData,hdnField)
{
	
	jQuery('#'+hdnField).val('');
	var series = chartData.series;
	var headerDatas = "[]";
	var rowHeaders = "[";
	var fields = '[';
	var tableHtml = "<table class='graphDatatable' style='width:27%;margin-left:440px;'><tr class='graphDatatableHeader'><td style='width:30%;'></td><td style='width:30%;'></td></tr>";
	var tableDatas = "";
	for(var i=0;i<series.length;i++)
	{
		
		var dataArr = series[i].data;
		for(var i=0;i<dataArr.length;i++)
		{
			fields += "[";
			tableHtml +="<tr class='graphDatatableRow' ><td class='graphTableDatasHeader'>"+dataArr[i][0] +"</td>";
			tableHtml  += "<td class='graphTableDatas'>"+dataArr[i][1]+"</td>";
			tableHtml += "</tr>";
			rowHeaders += '"'+ replaceAllDoubleQuots(dataArr[i][0])+ '",';
			fields += dataArr[i][1] + "],";
		}
		//fields = fields.substring(0,fields.length-1)+"],";	
	}
	tableHtml +="</table>";
	rowHeaders = rowHeaders.substring(0,rowHeaders.length-1)+"]";		
	fields = fields.substring(0,fields.length-1)+"]";	

	var toSend = '{"chartDatas":{"headers":{"rowHeaders":'+ rowHeaders +',"title":"'+replaceAllDoubleQuots(chartData.title.text)+'","colHeaders":'+ headerDatas +',"xAxis":"","yAxis":""}},';
	toSend +=  '"values":{"type":"pie","datas":'+fields+'}}';	
	jQuery('#'+hdnField).val(toSend);
	return tableHtml;
}
function combinedPieDatatable(chartData,hdnField)
{
	var headerDatas = "[";
	var rowHeaders = "[";
	var fields = '[';
	var type="pie";	
	if(chartData.chartType != null && chartData.chartType != '' && chartData.chartType != ' '&& chartData.chartType != 'undefined' && chartData.chartType == 'column')
		type = 'column';
	var series = chartData.series;
	var header = chartData.xAxis.title;
	var tableHtml = "<table class='graphDatatable' style='width:60%;'><tr class='graphDatatableHeader'><td style='width:50%;word-wrap: break-word;text-align:center;'>"+header.text+"</td><td style='width:30%;word-wrap: break-word;text-align:center;'>"+chartData.yAxis[0].title.text+"</td></tr>";
	var tableDatas = "";
	for(var i=1;i<series.length;i++)
	{
		
		var dataArr = series[i].data;	
		rowHeaders += replaceAllDoubleQuots(series[i].name)+",";
		if(series[i].type != null && series[i].type != '' && series[i].type != ' '&& series[i].type != 'undefined' && series[i].type == 'pie')
			type = (type == 'column'?'PieColumn':type);
		fields += '[';
		for(var i=0;i<dataArr.length;i++)
		{
			//fields += '[';
			tableHtml +="<tr class='graphDatatableRow'><td class='graphTableDatasHeader'>"+dataArr[i].name +"</td>";
			tableHtml  += "<td class='graphTableDatas'>"+dataArr[i].y+"</td>";
			tableHtml += "</tr>";
			//rowHeaders +=dataArr[i].name+",";
			headerDatas += replaceAllDoubleQuots(dataArr[i].name) + ",";
			//fields += dataArr[i].y + "],";
			fields += dataArr[i].y+"," ;
		}
		fields = fields.substring(0,fields.length-1)+"]";	
	}
	
	tableHtml +="</table>";
	headerDatas = headerDatas.substring(0,headerDatas.length-1)+"]";	
	rowHeaders = rowHeaders.substring(0,rowHeaders.length-1)+"]";		
	fields =  fields+"]";
	var toSend = '{"chartDatas":{"headers":{"rowHeaders":'+ rowHeaders +',"title":"'+replaceAllDoubleQuots(chartData.title.text)+'","colHeaders":'+ headerDatas +',"xAxis":"'+replaceAllDoubleQuots(header.text)+'","yAxis":"'+chartData.yAxis[0].title.text+'"}},';
	toSend +=  '"values":{"type":"'+type+'","datas":'+ fields +'}}';	
	jQuery('#'+hdnField).val(toSend);
	return tableHtml;
}

function enableDisableDatenMonthFilter()
{
	if(jQuery('input:checkbox[name=chkMonthwise]').attr('checked') == 'checked')
	{
		jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
		jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
		jQuery("#dtefromDate").datebox('disable');
		jQuery("#dtetoDate").datebox('disable');
		jQuery("#dtefromDate").datebox('clear');
		jQuery("#dtetoDate").datebox('clear');
	}
	else
	{
		jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
		jQuery("#dtefromMonth").datebox('disable');
		jQuery("#dtetoMonth").datebox('disable');
		jQuery("#dtefromMonth").datebox('clear');
		jQuery("#dtetoMonth").datebox('clear');
	}
}

function compareFromToDate(dtFromDate,dtToDate,noOfDays)
{
	
	var today = convertStringToDate(dtToDate);
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-1)<=0)
	year=today.getFullYear();
	  var backdate= new Date(year, month, date-noOfDays);
	  
	//var backdate = new Date(year,month-1,date);
	
	if(convertStringToDate(dtFromDate)  < backdate){
		alert("From Date Should be within "+noOfDays+" Days");
		//jQuery('#dtefromDate').datebox('clear');		
		return false;   
	}
	return true;
}
function compareFromToMonth(dtFromMonth,dtToMonth,noOfMonths)
{
	var today = convertStringToDate('01-'+dtToMonth);	
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-noOfMonths)<=0)
	year=today.getFullYear();
	var backdate = new Date(year,month-noOfMonths,date);
	if(convertStringToDate('01-'+dtFromMonth)  < backdate){
		alert("From Month Should be within "+noOfMonths+" Month");
		//jQuery('#dtefromDate').datebox('clear');		
		return false;
	}
	return true;
}
function filterMonthnDateDifference(filterString,days,months)
{
	
	if(months != null && months != '' && months != ' ')
	{
		var dtFromMonth = getFieldValue("dtefromMonth");					
		var dtToMonth = getFieldValue("dtetoMonth");	
		if(getFilterValue(filterString, "chkMonthwise") == '1'){						
			if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
			{
				alert("Select  FromMonth");
				return false;
			}					
			if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
			{
				alert("Select  ToMonth");
				return false;
			}
			
			 if (!compareFromToMonth(dtFromMonth,dtToMonth,months))
					return false;
			 else
				 return true;
		}
	}
	if(days != null && days != '' && days != ' ')
	{
		var dtFromDate = getFieldValue("dtefromDate");					
		var dtToDate = getFieldValue("dtetoDate");	
				
		if(getFilterValue(filterString, "chkMonthwise") == '0'){		
		
			 if (!compareFromToDate(dtFromDate,dtToDate,days))
					return false;
			 else
				 return true;
			 
			 
		}
	}
	return true;
}

/*function PrintDivData(divId)
{  
	
	var ctrlcontent = document.getElementById(divId);
	var printscreen = window.open('','','left=1,top=1,width=1,height=1,toolbar=0,scrollbars=0,status=0â€‹');
	printscreen.document.write(ctrlcontent.innerHTML);
	printscreen.document.close();
	printscreen.focus();
	printscreen.print();
	printscreen.close();
}*/

function PrintDivData(divId) {  
    var ctrlcontent = document.getElementById(divId);

    var printscreen = window.open('', '', 'width=1300,height=600');

    printscreen.document.write(`
        <html>
        <head>
            <title>Print</title>
            <style>
                @media print {
					transform: scale(0.77);
					      transform-origin: top left;
					      width: 130%; 
						  }

                    @page {
                        size: auto;  or A4 
                        margin: 10mm;
                    }
                }
            </style>
        </head>
        <body>
            ${ctrlcontent.innerHTML}
        </body>
        </html>
    `);

    printscreen.document.close();
    printscreen.focus();
    printscreen.print();
    printscreen.close();
}


// setup grid print capability. Add print button to navigation bar and bind to click.


  /*function PrintGrid(gid){
   // empty the print div container.
   jQuery('#prt-container').empty();

   // copy and append grid view to print div container.
   jQuery('#gview_'+gid).clone().appendTo('#prt-container').css({'page-break-after':'auto'});
   //alert(jQuery('#prt-container').html());
   // remove navigation divs.
   jQuery('#prt-container div').remove('.ui-jqgrid-toppager,.ui-jqgrid-titlebar,.ui-jqgrid-pager');

   // print the contents of the print container.
   jQuery('#prt-container').printElement({pageTitle:"", overrideElementCSS:[{ href:'css/print-grid.css',media:'print'}]});
  }
*/
function changeShift(fromTime,factoryId,sectId,cellId,occuredDate,shiftId,shiftDateId)
{
	if(fromTime.indexOf(':') == 1)
		fromTime = '0'+fromTime;
	
	var dataString = '?q=2&factId='+factoryId+'&sectId='+sectId;
    dataString += '&cellId='+cellId+'&fromTime='+fromTime+'&occurreddate='+occuredDate;
    dataString += '&ShiftId='+shiftId+'&ShiftDateID='+shiftDateId;
    
	processAjaxCalls('txt_shift.brdn',dataString,'setShiftIdAndDate','getShiftErr');
}

function  setShiftIdAndDate(record)
{
	
	if(record.shiftDate != null && record.shiftDate != '' && record.shiftDate != ' ')
	{
		 
		jQuery('#'+record.ShiftId).combobox('setValue',record.shift);  
		var shift = jQuery('#'+record.ShiftId).combobox('getText');			
		if(shift == 'C')
			jQuery('#'+record.ShiftDateId).datebox('setValue',record.shiftDate);
	}
	readOnlyFields(record.ShiftId);       
}

/*function loadFormsFromMenu(isParent,menuName,isMaster,formName,isFilterNeed,relatedFilter,isShowMenu){
	alert("menuform");
	 if(jQuery("#ifrExternalForm").length > 0 )
		 jQuery("#ifrExternalForm").remove();
	
	 if( jQuery('.layout-split-west').css('left')<0 )
		 return;		

	 if( isParent ==  "false" )
	 {
		var lastFormNavigT = formNavigations.pop();
		if( lastFormNavigT != null){
			var isHome1 =  getFilterValue(lastFormNavigT.URL + '&','isHomePage');
			if( isHome1 != "true")
				jQuery("#"+lastFormNavigT.divId).html('');
		
			formNavigations.push(lastFormNavigT);
		}
		
	
	 //if( jQuery('.layout-split-west').css('left')<0 )
	//	 return;		
	
		 
		 
		 var ref = menuName;
		// var isMaster = isMaster;
		 if( isMaster == 'true' || isMaster == true )
			 ref += (ref.indexOf('?') > 0 ? '':'?')+'masterForm=Y'; 
		 
		jQuery("#hiddenUrl").val("");	
		 var formHeader =formName;
		 //setFormMainHeader(formHeader);
		 jQuery("#hiddenUrl").val(ref);
		 alert(ref);
		 resetErrMessg();
		 //jQuery("#preLoadContent").css("display","block");  loading Effect 
		 //jQuery("#LoadContent").css("display", "none"); 
		 //jQuery("#LoadContent").hide();
		 LoadingFormWaiter("preLoadContent","LoadContent");
		 jQuery("#dispErr").html("");
		 
		 var formNavig = null;
		 //formNavigations =null;
		 var isHomePage = "false";
		 if( formNavigations != null ){
			 alert( formNavigations.length);
			 
			 
			 if( formNavigations.length > 1 ){
				 formNavig = formNavigations[1];
				 isHomePage =  getFilterValue(formNavig.URL + '&','isHomePage');
				  
			 }
		 	 if(isHomePage != "true"){
				 formNavig = formNavigations[0];
				 if( formNavig != null && formNavig.URL != undefined )
					 isHomePage =  getFilterValue(formNavig.URL + '&','isHomePage');
			 }
		 	 
		 	var lastFormNavig = formNavigations.pop();
		 	if(isHomePage != 'true' && lastFormNavig != null ){
		 		jQuery("#"+lastFormNavig.divId).hide();
		 		jQuery("#"+lastFormNavig.divId).html('');
		 	}
		 	for(var i=0;i<formNavigations.length;i++){
		 		var frmN = formNavigations[i];
		 		if(frmN != null){
		 			var isHome =  getFilterValue(frmN.URL + '&','isHomePage');
		 			if( isHome != "true")
		 				jQuery("#"+frmN.divId).html('');
		 			jQuery("#"+frmN.divId).hide();
		 		}	
		 	}
		 	
		 	formNavigations = null;
		 	formNavigations =[];
		 	 if(isHomePage == "true" ){
		 		 
		 		 formNavigations.push(formNavig);
		 		 alert("isHomePage :"+formNavig.divId);
		 		//jQuery("#homePageHiddenHtml").html(jQuery("#"+formNavig.divId).html());
	 //alert(glbHomepageCont);
		 		jQuery("#"+formNavig.divId).hide();
		 	 }

			 
			 
		 }
	     //jQuery("#LoadContent").html("");		 
		 if( isFilterNeed != "E" ){
			 var divId = genNextLoadContentDivId(formNavig);
			 if(jQuery("#"+divId ).length <= 0 ) {
				 jQuery("#LoadContent").append('<div id="'+ divId +'" tabindex="0" > </div>' );
			 }
			 alert("new div:"+divId);
			 //jQuery("#"+divId ).html('');
			 jQuery("#"+divId ).show();
			 var filterHeader = relatedFilter;
			 
			 jQuery("#filterUrl").val( filterHeader);
			 show_winMask(1);	
			 setLoadFormCallBackFrmId("");
			 jQuery('#isCommonFilterSlideOpen').val("");
			 
			
			 if( isShowMenu == undefined || isShowMenu == 'undefined')
				 isShowMenu = false;
			 
			 if( ! isShowMenu ){
				  
				 jQuery('#mainlayout').layout('collapse','west');
			 }
			 
			 
			 jQuery("#"+divId).load(ref, function(response, status, xhr) { load page 
				 show_winMask(0);
				 
				 jQuery("#treMenu").jstree("disable_hotkeys");						 
				  if( ! loadFormSessionTimeOut(response, status, xhr))
					  return true;


//				  jQuery('#LoadContent_1').focus();
				  if( jQuery('#'+divId +' input:first') )	
				  		jQuery('#'+ divId +' input:first').focus();
						  						
				  if (status == "error") {
				    var msg = "Sorry but there was an error: ";
				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
				    
				  }
				  else{
					  	
					  ref = jQuery("#hiddenUrl").val();
				//	  var tempArr = [];
					  //if( formNavigations != null)
				//	  while( formNavigations != null){
				//		  tempArr.push(formNavigations.pop());
				//	  }	  
					  pushFormNavigationDetails(ref,formHeader); //added on 24-Jan-2012
					  
				//	  if( tempArr.length > 0 )
				//	  {
				//		  while( tempArr != null){
				//			  formNavigations.push(tempArr.pop());
				//		  }	 
				//	  }	  	

					  //alert(" before h " + formNavigations.length);
					 if( isHomePage != "true" && formNavigations.length > 2){
						  	formNavigations = formNavigations.reverse();
					 }
					 else if( isHomePage == "true" && formNavigations.length > 3){
						 var formNavigObj = formNavigations[0];
						 formNavigations = formNavigations.reverse();
						 formNavigations.pop();
						 formNavigations.splice(0, 0, formNavigObj);
					 }
						 	
					 invokeAfterLoadFormCallBack();	 
					 //alert(" after h " + formNavigations.length);
					   
					 //jQuery('#mainlayout').layout('collapse','west');
//					 pushFormNavigationDetails(ref,formHeader); //added on 2-Nov-2011
				  }
			 	  
				  if( isFilterNeed == "Y" ){
					  jQuery("#filter_tab").show();
					  jQuery("#filterPanel").show();
					  jQuery('#isCommonFilterSlideOpen').val("N");
					  enableFilterBtn();
				  }	  
				  else{	  
					  jQuery("#filter_tab").hide(); //hide filter
				  }	  
				  
				  jQuery("#preLoadContent").hide();
				  jQuery("#LoadContent").show();
				  jQuery("#LoadContent").css("display","block");
				});			 
		 }
		 else if(isFilterNeed == "E"){
			// alert('ji');
			 
			 jQuery("#LoadContent").append('<iframe src='+ ref + ' width="100%" id="ifrExternalForm" height="100%"> </iframe>');
			  jQuery("#preLoadContent").hide();
			  jQuery("#LoadContent").show();
			  jQuery("#LoadContent").css("display","block");
		 }
			 	 			 
	}	
}*/

function loadFormsFromMenu(isParent,menuName,isMaster,formName,isFilterNeed,relatedFilter,isShowMenu){
	//alert("menuform");
	 if(jQuery("#ifrExternalForm").length > 0 )
		 jQuery("#ifrExternalForm").remove();
	
	 if( jQuery('.layout-split-west').css('left')<0 )
		 return;		
  //alert("isParent :"+isParent)
  var chgpwd = jQuery("#hdnPassword").val();
 if(chgpwd.trim() == "true") return;
  				
	 if( isParent ==  "false" )
	 {
		/*var ids = [];

				for (var i = 0; i < formNavigations.length; i++) {
				    if (formNavigations[i] && formNavigations[i].divId) {
				        ids.push(formNavigations[i].divId);
				    }
				}*/

				//alert("formNavigations (" + formNavigations.length + "):\n" + ids.join("\n"));
		//alert("formNavigations.length :"+ formNavigations.length);
		formNavigationLog("before refresh in loadFormsFromMenu ");
		//refreshHomePageForMenu();
		if(chgpwd.trim() != "true")
				{
					//alert("chgpwd1 inside:"+chgpwd);
			var fNavig = popFormNavigation();
			//var preNavig;
			while( fNavig != null ){
				
				var isHomePageC =  getFilterValue(fNavig.URL + '&','isHomePage');
				if( isHomePageC == "true"){
					formNavigations.push(fNavig);
					break;		
				}
				fNavig = popFormNavigation();	
			}
			}
		formNavigationLog("after refresh in loadFormsFromMenu ");
		if(formNavigations.length > 0){
		//var lastFormNavigT = formNavigations[formNavigations.length -1 ];// formNavigations.pop();
		//alert("lastFormNavigT :"+lastFormNavigT.divId);
		//alert("lastFormNavigT :"+lastFormNavigT.URL)
		
		/*if( lastFormNavigT != null){
			var isHome1 =  getFilterValue(lastFormNavigT.URL + '&','isHomePage');
			if( isHome1 != "true")
				jQuery("#"+lastFormNavigT.divId).html('');
		
			//formNavigations.push(lastFormNavigT);
		}*/
		}
		
	
	 //if( jQuery('.layout-split-west').css('left')<0 )
	//	 return;		
	
		 
		 
		 var ref = menuName;
		// var isMaster = isMaster;
		 if( isMaster == 'true' || isMaster == true )
			 ref += (ref.indexOf('?') > 0 ? '':'?')+'masterForm=Y'; 
		 
		jQuery("#hiddenUrl").val("");	
		 var formHeader =formName;
		 //setFormMainHeader(formHeader);
		 jQuery("#hiddenUrl").val(ref);
		 //alert(ref);
		 resetErrMessg();
		 //jQuery("#preLoadContent").css("display","block");  loading Effect 
		 //jQuery("#LoadContent").css("display", "none"); 
		 //jQuery("#LoadContent").hide();
		 LoadingFormWaiter("preLoadContent","LoadContent");
		 jQuery("#dispErr").html("");
		 
		 var formNavig = null;
		 //formNavigations =null;
		 var isHomePage = "false";
		 if( formNavigations != null ){
			 ///alert("formNavigations.length :"+ formNavigations.length);
			 
			 
			 if( formNavigations.length > 1 ){
				 formNavig = formNavigations[1];
				 isHomePage =  getFilterValue(formNavig.URL + '&','isHomePage');
				  
			 }
			 if( formNavigations.length > 0 ){
		 	 if(isHomePage != "true"){
				 formNavig = formNavigations[0];
				 if( formNavig != null && formNavig.URL != undefined )
					 isHomePage =  getFilterValue(formNavig.URL + '&','isHomePage');
			 }
			//console.log("isHomePage :"+isHomePage);
			 var lastFormNavig =formNavigations[formNavigations.length - 1] ; //= formNavigations.pop();
			/* while (formNavigations.length > 0) {

			     lastFormNavig = formNavigations.pop();
				 alert("lastFormNavig.divId while :"+ lastFormNavig.divId);
			     if (lastFormNavig && lastFormNavig.divId) {

			         // Always cleanup popup containers
			         if (lastFormNavig.divId.startsWith("LoadContent_")) {
			             $("#" + lastFormNavig.divId).html("");
			             $("#" + lastFormNavig.divId).hide();
			             continue;
			         }

			         // First non-popup = previous page
			         break;
			     }
			 }*/
		 	 
		 	//var lastFormNavig = formNavigations.pop();
			//alert("lastFormNavig.divId :"+ lastFormNavig.divId);
			//lastFormNavig = formNavigations.pop();
							//alert("lastFormNavig.divId next if :"+ lastFormNavig.divId);
			if (lastFormNavig && lastFormNavig.divId.startsWith("LoadContent_")) {
			    //lastFormNavig = formNavigations.pop();
				if(isHomePage != 'true' && lastFormNavig != null ){
								//alert("insidehomepage if");
								//jQuery("#"+lastFormNavig.divId).html("");
						 		jQuery("#"+lastFormNavig.divId).hide();
						 		
						 	}
				//alert("lastFormNavig.divId if :"+ lastFormNavig.divId);
			}else{
				var maxNum = 0;
                jQuery("[id^='LoadContent_']").each(function () {
				     var id = jQuery(this).attr("id");
				     var n = parseInt(id.replace("LoadContent_", ""), 10);
				    if (!isNaN(n)) maxNum = Math.max(maxNum, n);
			    });
			//  alert("lastFormNavig.divId next :"+ maxNum);
			  if(isHomePage != 'true' && lastFormNavig != null ){
			  			//	alert("insidehomepage");
							//jQuery("#LoadContent_"+maxNum).html("");
			  		 		jQuery("#LoadContent_"+maxNum).hide();
							//jQuery("#LoadContent_"+maxNum).remove();
			  		 		
			  		 	}
						
						for(var i=2;i <= maxNum;i++){
								 		var frmN = "LoadContent_"+i;
											//console.log("frmN.divId :"+frmN);
								 			
								 			//if( frmN != "LoadContent_1")
								 			//jQuery("#"+frmN).html("");
								 			//jQuery("#"+frmN).hide();
											jQuery("#"+frmN).remove();
								 	}
			}
			/*jQuery("#LoadContent_1").html("");
			jQuery("#LoadContent_1").hide();*/
			/*if (!lastFormNavig.divId.startsWith("LoadContent_")) {
				//lastFormNavig = formNavigations.pop();
				alert("lastFormNavig.divId next :"+ lastFormNavig.divId);
				}
				alert(lastFormNavig.divId);
		 	if(isHomePage != 'true' && lastFormNavig != null ){
				alert("insidehomepage");
		 		jQuery("#"+lastFormNavig.divId).hide();
		 		jQuery("#"+lastFormNavig.divId).html('');
		 	}
		 	for(var i=0;i<formNavigations.length;i++){
		 		var frmN = formNavigations[i];
		 		if(frmN != null){
					alert("frmN.divId :"+frmN.divId);
		 			var isHome =  getFilterValue(frmN.URL + '&','isHomePage');
		 			if( isHome != "true")
		 				jQuery("#"+frmN.divId).html('');
		 			jQuery("#"+frmN.divId).hide();
		 		}	
		 	}*/
		 	
		 	//formNavigations = null;
		//	alert("formNavigations.length before :"+ formNavigations.length);
			//isHomePage
		 	//formNavigations =[];
		 	 if(isHomePage == "true" ){
		 		 
		 		 //formNavigations.push(formNavig);
		 		// alert(formNavig.divId);
		 		 //jQuery("#homePageHiddenHtml").html(jQuery("#"+formNavig.divId).html());
	             //alert(glbHomepageCont);
		 		jQuery("#"+formNavig.divId).hide();
		 	 }

			} 
			 
		 }
		// alert("formNavigations.length after :"+ formNavigations.length);
	     //jQuery("#LoadContent").html("");		 
		 if( isFilterNeed != "E" ){
			 var divId = genNextLoadContentDivId(formNavig);
			 if(jQuery("#"+divId ).length <= 0 ) {
				 jQuery("#LoadContent").append('<div id="'+ divId +'" tabindex="0" > </div>' );
			 }
			 //jQuery("#"+divId ).html('');
			 jQuery("#"+divId ).show();
			 var filterHeader = relatedFilter;
		//	 alert("new div :"+divId);
			 
			 jQuery("#filterUrl").val( filterHeader);
			 show_winMask(1);	
			 setLoadFormCallBackFrmId("");
			 jQuery('#isCommonFilterSlideOpen').val("");
			 
			
			 if( isShowMenu == undefined || isShowMenu == 'undefined')
				 isShowMenu = false;
			 
			 if( ! isShowMenu ){
				  
				 jQuery('#mainlayout').layout('collapse','west');
			 }
			 
			 
			 jQuery("#"+divId).load(ref, function(response, status, xhr) { //load page 
				 show_winMask(0);
				 
				 jQuery("#treMenu").jstree("disable_hotkeys");						 
				  if( ! loadFormSessionTimeOut(response, status, xhr))
					  return true;


//				  jQuery('#LoadContent_1').focus();
				  if( jQuery('#'+divId +' input:first') )	
				  		jQuery('#'+ divId +' input:first').focus();
						  						
				  if (status == "error") {
				    var msg = "Sorry but there was an error: ";
				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
				    
				  }
				  else{
					  	
					  ref = jQuery("#hiddenUrl").val();
				//	  var tempArr = [];
					  //if( formNavigations != null)
				//	  while( formNavigations != null){
				//		  tempArr.push(formNavigations.pop());
				//	  }	
			//	alert("ref :"+ref);
			//	alert("formNavigations.length before push navigation :"+ formNavigations.length);  
					  pushFormNavigationDetails(ref,formHeader); //added on 24-Jan-2012
					  
				//	  if( tempArr.length > 0 )
				//	  {
				//		  while( tempArr != null){
				//			  formNavigations.push(tempArr.pop());
				//		  }	 
				//	  }	  	

					  //alert(" before h " + formNavigations.length);
					/* if( isHomePage != "true" && formNavigations.length > 2){
						  	formNavigations = formNavigations.reverse();
					 }
					 else if( isHomePage == "true" && formNavigations.length > 3){
						 var formNavigObj = formNavigations[0];
						 formNavigations = formNavigations.reverse();
						 formNavigations.pop();
						 formNavigations.splice(0, 0, formNavigObj);
					 }*/  // MADHAN   load problem
						 	
					 invokeAfterLoadFormCallBack();	 
					// alert(" after h " + formNavigations.length);
					   
					 //jQuery('#mainlayout').layout('collapse','west');
//					 pushFormNavigationDetails(ref,formHeader); //added on 2-Nov-2011
				  }
			 	  
				  if( isFilterNeed == "Y" ){
					  jQuery("#filter_tab").show();
					  jQuery("#filterPanel").show();
					  jQuery('#isCommonFilterSlideOpen').val("N");
					  enableFilterBtn();
				  }	  
				  else{	  
					  jQuery("#filter_tab").hide(); //hide filter
				  }	  
				  
				  jQuery("#preLoadContent").hide();
				  jQuery("#LoadContent").show();
				  jQuery("#LoadContent").css("display","block");
				});			 
		 }
		 else if(isFilterNeed == "E"){
			// alert('ji');
			 
			 jQuery("#LoadContent").append('<iframe src='+ ref + ' width="100%" id="ifrExternalForm" height="100%"> </iframe>');
			  jQuery("#preLoadContent").hide();
			  jQuery("#LoadContent").show();
			  jQuery("#LoadContent").css("display","block");
		 }
			 	 			 
	}	
}
/* To Set Focus On Field */
function setFocusOnField(id)
{
	if(id.substring(0,3) == 'cmb')
		jQuery("#"+id).next('span').children().focus();
	else
		jQuery("#"+id).focus();			
}

/**Employee Filter pop Up By Manikandan**/
function loadDeptRoleMgr(divId,top,right,loadSuccesCallBack,filterString){
	//var newDiv = "<div id='loadpopRoleMgrDpt' style='display:block;postion:absolute;height:"+height+";width:"+width+";top:"+top+";left:"+left+"></div>";
	var prevID = jQuery('#prevDiv').html();
	var newDiv;
	//alert(divId);
	if(filterString == "onLoad")
	{
	
		jQuery('#'+divId+'>.sub-header').append("<span id="+divId+"_openEmpFilter class='' title='Employee Filter' style='top:"+top+";right:"+right+";cursor:pointer;'><span id="+divId+"_tip class='IMArrow' title=' Employee Filter ' style=''></span></span>" );
	}
	else{//alert(divId +" != "+ prevID);
		 if(divId != prevID){
			newDiv = " ";
			jQuery("#loadpopRoleMgrDpt").html(" ");
			jQuery("#prevDiv").html(" ");
			jQuery("#tst").html(" ");
			clearField("cmbRole");
			clearField("cmbDeparttKeyid");
			clearField("cmbManager");
		}
		if(jQuery("#loadpopRoleMgrDpt").html()== null || jQuery("#loadpopRoleMgrDpt").html() == undefined ||jQuery("#loadpopRoleMgrDpt").html()== " " ){
			
			newDiv = "<div id='tst'><input type='hidden' value="+divId +" id='divId'/><div id='loadpopRoleMgrDpt' style=top:"+top+";left:"+right+" class='empPopUp'></div><div id='prevDiv' style='display:none;'>"+divId+"</div><input type='hidden' id='hdnEmpFilterstr' name='hdnEmpFilterstr' value="+filterString+"  /></div>";
			LoadForm("loadpopRoleMgrDpt", "preLoadContent","DeptRolePopUp_input.prgEnt","dispErr","rolemgrdpt_SuccessCalBack","refresh_ErrorCalBack");
			jQuery('#'+divId+'>.sub-header').append(newDiv );
			jQuery('#loadpopRoleMgrDpt').slideDown(200);
			
			event.stopPropagation();
		}
		else{
			jQuery('#loadpopRoleMgrDpt').slideDown(200);
		}
	}
}

function changeZindex(){
jQuery(function() {
    var zIndexNumber = 1000;
    // Put your target element(s) in the selector below!
    jQuery(".popup-mask").each(function() {
    	jQuery(this).css('zIndex', zIndexNumber);
            zIndexNumber -= 10;
    });
});
}


function loadTeamEmp(divId,top,right,loadSuccesCallBack,filterString){
	var prevID = jQuery('#prevDiv').html();
	var newDiv;
//	var divId="loadMultiSelectPopUp";
	
	if(filterString == "onLoad")
	{
		if(divId == prevID){
			jQuery("#loadMultiSelectPopUp").after("<span id="+divId+"_openTeamFilter class='' title='Team Employee Filter' style='top:"+top+";left:"+right+"%;cursor:pointer;position:absolute'>");
		}
		else
		jQuery("#loadMultiSelectPopUp").after("<span id="+divId+"_openTeamFilter class='' title='Team Employee Filter' style='top:"+top+";left:"+right+"%;cursor:pointer;position:absolute'><span id="+divId+"_tip class='IMArrow' title=' Team Employee Filter ' style=''></span></span>" );
		
		}
	
	else{
			
		if(divId != prevID){
			newDiv = " ";
			jQuery("#loadpopTeamEmp").html(" ");
			jQuery("#prevDiv").html(" ");
			jQuery("#tst").html(" ");
			clearField("cmbFactory");
			clearField("cmbDeparttKeyid");
			clearField("cmbDesignation");
		}
		
if(jQuery("#loadpopTeamEmp").html()== null || jQuery("#loadpopTeamEmp").html() == undefined ||jQuery("#loadpopTeamEmp").html()== " " ){
			
			newDiv = "<div id='tst'><input type='hidden' value="+divId +" id='divId'/><div id='loadpopTeamEmp' style=top:"+top+";left:"+right+"%;cursor:pointer;position:absolute class='TeamPopUp'></div><div id='prevDiv' style='display:none;'>"+divId+"</div><input type='hidden' id='hdnEmpFilterstr' name='hdnEmpFilterstr' value="+filterString+"  /></div>";
			LoadForm("loadpopTeamEmp", "preLoadContent","TeamFilter_input.team","dispErr","teamEmp_SuccessCalBack","refresh_ErrorCalBack");
			jQuery("#loadMultiSelectPopUp").after(newDiv );
			jQuery('#loadpopTeamEmp').slideDown(200);
			
			event.stopPropagation();
		}
		else{
			jQuery('#loadpopTeamEmp').slideDown(200);
		}
		
	}
	
}

function setFilterValues()
{
	var factId =jQuery('#hiddenfact').val();
	var sectId =jQuery('#hiddensect').val();
	var cellId =jQuery('#hiddencell').val();
	var date =jQuery('#hiddendate').val(); 
	var shift =jQuery('#hiddenshift').val();
	var dataStr ="";

    dataStr+="&factId="+factId;
	dataStr+="&sectId="+sectId;
	dataStr+="&cellId="+cellId;
	dataStr+= "&date="+escape(date);
	dataStr+= "&shift="+shift;
	jQuery('#hdnfilterClicked').val("true");


function disableFunctionalLocation(disableFuncLocation,funcLocDivId){

	 /**Added by Manikandan**/
	if(disableFuncLocation == 'true'){		
		jQuery('#'+funcLocDivId).css('position','relative');
		jQuery('#'+funcLocDivId).append('<div id="disFuncLocDiv" style="position: absolute;top:-2%;left:10%;width: 80%;height:100%;z-index:2;opacity:0.1;filter: alpha(opacity = 40); "></div>');
		jQuery('#dispFunctionalLoc').css('background-color','#EFEFEF');
		//'<div id="disFuncLocDiv" style="position: absolute;top:0;left:0;width: 100%;height:100%;z-index:2;opacity:0.4;filter: alpha(opacity = 50)"></div>'
	}
	else{
		jQuery('#disFuncLocDiv').remove();
	}

}
	
	
}
/*  added by karthick.t*/
function openSetHomePage()
{
	processAjaxCalls("recallHomePage.userLogin","","HomePageonsuccessCallBack","HomePageonerrorCallBack","","reqHomPage");
}
function HomePageonsuccessCallBack(result)
{
	var url = result.successData.url;
	
	if(url.indexOf('dash')>=0)
	{
	
	
	}
	if(url != null && url.indexOf('?')<0)
		url += "?";
	else
		url += "&";
	
	url +=  "isHomePage=true";
	
	
	var formName = result.successData.formName;
	var isMaster = result.successData.isMaster;
	var isFilter = result.successData.isFilter;
	var relatedFilter = result.successData.relatedFilter;

	loadFormsFromMenu("false",url,isMaster,formName,isFilter,relatedFilter,true);
	//jQuery('#mainlayout').layout('expand','west');
	jQuery("#hdnPrevoiusHomUrl").val(url);
	
}
function HomePageonerrorCallBack(result)
{

}
function setHomePage()
{
	
	var url = jQuery("#hiddenUrl").val();
	
	var zIndexUrl = jQuery("#hdnZ-IndexUrl").val();
	var header = getFormMainHeader();
	
	if(zIndexUrl.trim().length > 0)
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
							
	}
}
/*function divcombo(formname,comboname,divid,filterstring,spanid,hidden,type,keyid){
	
	if(filterstring == "onLoad"){
		jQuery('#'+ spanid).append('<span style="padding-left:1%"><input type="button" id="btncmbcompareto" name="btncompareto"  class="easyui-button"  value=".." style="height: 10%" /></span>');
	}
	jQuery("#btncmbcompareto").click(function (event){
		event.stopPropagation();
		var bool = jQuery('#'+ hidden).val();
		if(bool == true || bool == "true"){
			
			LoadForm(divid,"preLoadContent","divcombo_input.mchact?&comboname="+comboname+"&type="+type+"&divId="+divid+"&KEYID="+keyid+"&formname="+formname,"dispErr","divcombo_SuccessCalBack","refresh_ErrorCalBack");
			setTimeout(function(){jQuery('#'+ hidden).val(false);},100);
		}
		else{
			jQuery('#'+ formname + ' div[id= '+ divid+ ']').css("display","block");
		}
	});
	}*/
function divcombo(formname,comboname,divid,filterstring,spanid,hidden,type,keyid){

	var html='<span style="padding-left:1%;position:relative;"><input type="button" onclick=buttonclick("'+formname+'","'+comboname+'","'+divid+'","'+hidden+'","'+type+'","'+keyid+'"); id="btncmbcompareto'+divid+'" name="btncompareto"  class="easyui-button"  value=".." style="height: 10%" /></span>';
	if(filterstring == "onLoad"){
		jQuery('#'+ spanid).append(html);
	}
	
	//else
		//buttonclick(formname,comboname,divid,hidden,type,keyid);
	/*jQuery("#btncmbcompareto").click(function (event){
		var bool = jQuery('#'+ hidden).val();
		if(bool == true || bool == "true"){
			//alert("commmon filter");
			LoadForm(divid,"preLoadContent","divcombo_input.mchact?&comboname="+comboname+"&type="+type+"&divId="+divid+"&KEYID="+keyid+"&formname="+formname,"dispErr","divcombo_SuccessCalBack","refresh_ErrorCalBack");
			setTimeout(function(){jQuery('#'+ hidden).val(false);},100);
		}
		else{
			jQuery('#'+ formname + ' div[id= '+ divid+ ']').css("display","block");
		}
		event.stopPropagation();
	});*/
	}
function buttonclick(formname,comboname,divid,hidden,Type,keyid){
	
	var prevID = jQuery('#previousDiv').html();
	
	var type=jQuery("#btncmbcompareto"+divid).attr('multipletype');
	
	var Divhtml=jQuery('#'+divid).html();
	if(type=='undefined' || type==undefined){
		type=Type;
	}
	if(divid!=prevID){
		jQuery("#Multiplediv").remove();
		jQuery("#btncmbcompareto"+prevID).remove();
		jQuery("#hdnCompareto").val("true");
	}
	if(Divhtml==null || Divhtml=="null" || Divhtml=='' || Divhtml==' '){
		jQuery("#Multiplediv").remove();
		var div='<div id="Multiplediv"  class="combobox-multiselect"><div id="'+divid+'" ></div><div id="previousDiv" style="display:none;">'+divid+'</div></div>';
		jQuery("#btncmbcompareto"+divid).after(div);
	}
	var bool = jQuery('#'+ hidden).val();
	if(bool == true || bool == "true"){
		LoadForm(divid,"preLoadContent","divcombo_input.mchact?&comboname="+comboname+"&type="+type+"&divId="+divid+"&KEYID="+keyid+"&formname="+formname,"dispErr","divcombo_SuccessCalBack","refresh_ErrorCalBack");
		setTimeout(function(){jQuery('#'+ hidden).val(false);},100);
	}
	else{
		jQuery("#Multiplediv").css("display","block");
		}
	jQuery("#btncmbcompareto"+divid).click(function (event){
		event.stopPropagation();
		jQuery("#Multiplediv").css("display","block");
		
	});
	jQuery("#Multiplediv").click(function (event){
		event.stopPropagation();
	});
	
}
/**Function To identify Browser as  IE**/
function isIE(){
	var isIE = !!window.ActiveXObject || "ActiveXObject" in window;
	if (isIE) 
		return true;
	 
	return false;
}


function openActionPlan(divId,actPlanRefMasId,actPlanRefDocType,flId,actPlanMainTask,actPlanRefDtlId, actPlanRefDate, apMode)
{	
	if(actPlanRefDate == null || actPlanRefDate == ''){
		actPlanRefDate = getCurrentDate();
	}
	if(divId== null || divId.trim() == '' && actPlanRefMasId== null || actPlanRefMasId.trim() == '' && actPlanRefDocType== null || actPlanRefDocType.trim() == '')
	{
		alert(" Enter Valid Conditions ");
		return false;
	}
	else{		
		var dataStr ="actPlanRefMasId="+actPlanRefMasId+"&actPlanRefDocType="+actPlanRefDocType+"&flid="+flId+"&actPlanMainTask="+escape(actPlanMainTask) +"&actPlanRefDtlId="+actPlanRefDtlId;
		dataStr+="&actPlanRefDate="+actPlanRefDate+"&apMode="+apMode+"&taskid=''";
		LoadPopUp(divId,"ActionPlan_input.api?"+dataStr,true,"83%","90%","3%","7%","","Action Plan","",false);
    }
}

/*function openActionPlan(divId,keyid,refDocType,flid,mainTask,refDocDtl)
{	
	
	if(divId== null || divId.trim() == '' && keyid== null || keyid.trim() == '' && refDocType== null || refDocType.trim() == '')
	{
		alert(" Enter Valid Conditions ");
		return false;
	}
	else{
		LoadPopUp(divId,"ActionPlan_input.api?refDocId="+keyid+"&refDocType="+refDocType+"&flid="+flid+"&refDocDtl="+refDocDtl+"&mainTask="+mainTask ,true,"90%","500px","1%","3%","","Action Plan","",true);
        }
}*/
var GblShiftID;
function getCurrentShift (shiftId )
{
	GblShiftID = shiftId;
	processAjaxCalls('getCurrentShift.pcs','',"getCurrentShiftSuccess");
}

function getCurrentShiftSuccess(result) {		
	//setFieldValue(GblShiftID, result.shift);
	setComboValueSilent(GblShiftID,result.shift);//madhan
}
 
 
function LoadComboPop(url,cmbId,frmId){
	 var popupCaption = jQuery("#" + cmbId ).parent().prev().find('label').html();
	 LoadPopUp("ComboPopDivId","openComboGrid_input.commonFilter?cmbUrl="+escape(url)+"&frmName="+frmId+"&cmbId="+cmbId,true,"45%","80%","20px","20px","comBopopUpSuccessCallBack",popupCaption);	
}
function ComboPopDivId_onClose(){
/*	var selectedKeyid = jQuery('#hdnCmbSelectedID').val();
	var cmbfrmName = jQuery('#cmbfrmName').val();
	var cmbUrl = jQuery('#cmbUrl').val();
	var cmbId = jQuery('#cmbId').val();
	var recorddat = '{"text":" " ,"id":"'+selectedKeyid+'"}';
	var record = JSON.parse(recorddat);
 //alert(cmbfrmName+" -- "+cmbId+" -- "+cmbUrl+" -- "+selectedKeyid+"  "+selectedKeyid.trim().length);
	 
	if(selectedKeyid.trim().length>0){
		setFieldValue(cmbId,selectedKeyid);
		reloadCombo(cmbfrmName,cmbId,cmbUrl); 
		/**for triggering onselect combo**/ 
/*		var opts1 = jQuery("#"+cmbfrmName + " input[id=" +  cmbId +"]").combobox("options");
		opts1.onSelect.call(jQuery("#"+cmbId),record);
		/**end**/
		/*if("cmbPmsdMachineid"==cmbId)
			 setTimeout(function(){setFieldValue(cmbId,selectedKeyid);cmbfrmName+cmbId+_onSelect( record);},5000);
		 else
			 setTimeout(setFieldValue(cmbId,selectedKeyid),5000);
		*/	 
//	}
		
	return true;
}
function comBopopUpSuccessCallBack(result){
	 
	 
} 
function ZoomImage(id){ 
    // document.getElementById('bigImage').src=img;
    var imgUrl = jQuery('#'+id).attr('src');
    //alert(imgUrl);
    if(imgUrl.trim().length>0){
	    jQuery('#imgZoom').attr('src',imgUrl);
		jQuery('#zomImg').show();  
    }
}
function removeZoom(){
	jQuery('#imgZoom').attr('src',' ');
	jQuery('#zomImg').hide();
}  
/*function setFormater(gridId,formtType,fieldId,formId,url,rowId,value,colIndex,multiple){ 
	var row = jQuery("#"+gridId).jqGrid('getDataIDs');
	var grid = jQuery("#"+gridId );  
	jQuery("#"+gridId).jqGrid({
		cellattr: function(rowId, val, rawObject) {
		    if (parseFloat(val) > 200) {
		        return " class='ui-state-error-text ui-state-error'";
		    }
		});
	
	
	if(multiple != "" || multiple != null || multiple != undefined || "true"!=multiple ) 
		multiple = 'false';
	
	grid.find('td[role=gridcell]').each (function()
			{  
				var id   = jQuery(this).parent().attr('id');
 				var aria = jQuery(this).attr('aria-describedby'); 
 				
				if(id == rowId && gridId+"_"+colIndex ==aria ){ 
					var fieldID = jQuery("#jqgh_" +gridId+"_"+colIndex).attr("formatterType");
<<<<<<< .mine
					var value= jQuery("#"+gridId).jqGrid('getCell',rowId,colIndex);
					if(fieldID.substring(0,3) == "cmb") {
	 					value= jQuery("#"+gridId).jqGrid('getCell',rowId,colIndex+"1");
	 				}
					jQuery(this).attr('colValue',value);
=======
					//alert("inside   fieldId.........."+fieldID.substring(0,3)+"..");
>>>>>>> .r3514
					if(fieldID.substring(0,3) == "dte") { 
						jQuery(this).html('<input id="'+fieldID+rowId+'" name="'+fieldID+"-"+rowId+'"/> ');
						formatDateBox( fieldId+rowId ,'dd-MMM-yyyy');
					}
					if(fieldID.substring(0,3) == "cmb") {
						jQuery(this).html('<input id="'+fieldId+rowId +'" name="'+fieldID+"-"+rowId+'" class="easyui-combo"/> ');
						fillComboBoxWithGrid(formId,fieldID+rowId ,url,'',multiple);
					}
					if(fieldID.substring(0,3) == "txt") { 
					
						jQuery(this).html('<input id="'+fieldID+rowId+'" name="'+fieldID+"-"+rowId+'" onchange="" onblur="" onfocus="" class="easyui-text"/> ');
						
					}
					
					
					setFieldValue(fieldId+rowId, value);
				}
			});
}*/
function getColWidth(cm,columnName) {
    var i=0, l=cm.length;
    while (i<l) {
        if (cm[i].name===columnName) 
            return cm[i].width;
       i++; 
    }
    return -1;
}

function getColModelForColName(cm,columnName) {
    var i=0, l=cm.length;
    while (i<l) {
        if (cm[i].name===columnName) 
            return cm[i];
       i++; 
    }
    return -1;
}

function makeRowEditable(gridId,rowId){
	 
	
	//var row = jQuery("#"+gridId).jqGrid('getDataIDs');
	var colName = ''; 
	
	var grid = jQuery("#"+gridId );
	var colModels = grid.jqGrid("getGridParam", "colModel");
	
	for( var i =0;i< colModels.length ;i++){
		var colM = colModels[i];
		
		if( colM.pEditable == true && colM.pEditOptions != undefined){
			
			var editOpt =colM.pEditOptions;
			
			var editType = editOpt.editType.toLowerCase();
			/*madhan*/
			var width = colM.width - 4;
			var align = colM.align != undefined ? colM.align: "left";
			
			colName = colM.name; 	
			
			var tdcol = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]');
			var fieldID =  colName + "_" + gridId+"_"+rowId ; //jQuery("#jqgh_" +gridId+"_"+colName).attr("formatterType");
			var value="";
			//var idValue="";
			var idColName ="";
			var valueName="";
			value = jQuery("#"+gridId).jqGrid('getCell',rowId,colName);

			if(value==null || value.indexOf("<input") >= 0 || value.indexOf("<select") >= 0 || value.indexOf("<textarea") >= 0 )
				value = "";
		
			//if( idColName != undefined && idColName != null && idColName.trim().length > 0){
			//	idValue = jQuery("#"+gridId).jqGrid('getCell',rowId,colName);
			//}
			
			var maxLength =  editOpt.maxLength != undefined ?editOpt.maxLength:null;
			var maxLengthHtml = maxLength != 0 ? "maxlength='"+ maxLength +"'":"";
			if( editType == "text"){   //fieldID.substring(0,3) == "txt" && isTextArea != true ) {
				tdcol.html('<input id="'+fieldID+'" name="'+fieldID+'" onchange=txtOnChange("'+gridId+'","'+colName+'","'+rowId+'","'+colName+'"); onblur=txtOnBlur("'+gridId+'","'+colName+'","'+rowId+'","'+colName+'"); onfocus=txtOnFocus("'+gridId+'","'+colName+'","'+rowId+'"); '+ maxLengthHtml +' class="easyui-text" style="width:'+width+';text-align:'+align+'" value="'+value+'"/> ');
				if(editOpt.isNumber || editOpt.number){
					numericTextBox(fieldID);
				}
				jQuery("#"+fieldID).on(' click keydown', function(e){
								    e.stopPropagation();
								});
			}
			else if( editType == "textarea" ){// fieldID.substring(0,3) == "txt" ) {
				var rows = editOpt.rows != undefined ? editOpt.rows:2;
//				var cols =editOpt.cols != undefined ? editOpt.cols:3;
				tdcol.html('<textarea  id="'+fieldID+'" name="'+fieldID+'" onchange=txtOnChange("'+gridId+'","'+colName+'","'+rowId+'"); onblur=txtOnBlur("'+gridId+'","'+colName+'","'+rowId+'","'+colName+'"); onfocus=txtOnFocus("'+gridId+'","'+colName+'","'+rowId+'"); '+ maxLengthHtml +' class="easyui-text limit-length" rows="'+rows+'"  style="width:'+width+';text-align:'+align+'" >' + value+ '</textarea>');			
				jQuery("#"+fieldID).on(' click keydown', function(e){
				    e.stopPropagation();
				});			
			}
			else if(editType == "datebox") { 
				var dateFormat = editOpt.dateFormat != undefined? editOpt.dateFormat :'dd-MMM-yyyy';
				tdcol.html('<input id="'+fieldID+'" name="'+fieldID+'" style="width:'+width+';" value="'+value +'"/> ');
				formatDateBox( fieldID ,dateFormat);
				if( editOpt.defaultVal != undefined && value.trim() == "" && editOpt.defaultVal == "sysdate" )
					fillWithCurrentDate(fieldID);
				//setFieldValue(fieldID,value);
				
				jQuery("#"+fieldID).on(' click keydown', function(e){
								    e.stopPropagation();
								});	
				
			}
			else if(editType == "combobox") {
				var isMultiple=( editOpt.multiple == true ? true:false);
				var url = editOpt.url;
				var mode = editOpt.isLocal == true?'local':'remote';
				var panelHeight = editOpt.panelHeight != undefined ? editOpt.panelHeight:130;
				var panelWidth = editOpt.panelWidth != undefined ? editOpt.panelWidth:null;
				idColName = editOpt.idColName;
				var cmbValue = jQuery("#"+gridId).jqGrid('getCell',rowId,idColName);
				
				if( cmbValue.trim().indexOf("<input") >=0 ){
					cmbValue = getFieldValue(fieldID);
					if( cmbValue == undefined || cmbValue == null)
					  cmbValue="";
				}	
				tdcol.html('<input id="'+fieldID+'" name="'+fieldID+'" value="'+cmbValue+'" class="easyui-combo" style="width:'+width+'" colname="'+colName +'" idcolname="'+ editOpt.idColName+'" /> ');
				
				url = removeValueFromUrl(url, "combokey");
				if( url.indexOf("?") > 0 )
					url += "&";
				else
					url += "?";
				url += "combokey="+cmbValue;
				
				jQuery('#'+fieldID).combobox({
					mode:mode,
					url:url,
					dataType:'json',		
					valueField:'id',
					textField:'text',
					multiple:isMultiple ,
					//panelHeight:panelHeight,
					panelWidth:panelWidth,
					onSelect:function(record)
					{
						
						value=record.id;
						valueName=record.text;
						//madhan
						/*var cId = null ;
						if( jQuery(this).selector)
							cId  = jQuery(this).selector ;
						else if( jQuery(this).context)
							cId  = jQuery(this).context ;
						*/
						var cId = jQuery(this).attr("id");
						
						//alert("cId:"+cId);
						var cmbIdColName = jQuery("#"+cId).attr("idcolname");
						var cmbColName = jQuery("#"+cId).attr("colname");
						//alert("cmbIdColName:"+cmbIdColName);
						//alert("cmbColName:"+cmbColName);
						jQuery("#"+gridId).jqGrid('setCell',rowId,cmbIdColName ,record.id);

						jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+cmbColName+' ]').attr('title',valueName);
						 
						var onSelectFunctionName = cmbColName + "_"+gridId +'_onSelect';
						//alert("onSelectFunctionName:"+onSelectFunctionName);
						var args = [record,rowId]; 
						dynamicFunctionCall(onSelectFunctionName,args);
					} 
				} );

			/*	if(isMultiple){
					var arrayVal = cmbValue.split(","); //JSON.parse("[" + value + "]");
					jQuery("#"+fieldID).combobox("setValues",arrayVal);
				}
				else{
					jQuery("#"+fieldID).combobox("setValue",cmbValue);
				}
			*/	
				//valueName = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]').attr('title');
				//tdcol.attr('comboName',cmbValue);
				
				jQuery("#"+fieldID).on(' click keydown', function(e){
								    e.stopPropagation();
								});	
			}
			else if(editType == "button") {
				//var rHeight = jQuery ("table.ui-jqgrid-htable", jQuery("#"+gridId)).css ("height");
				
				var caption = editOpt.caption!=undefined?editOpt.caption:"...";
				tdcol.html('<input id="'+fieldID+'" name="'+fieldID+'" onclick=grdBtnClick("'+gridId+colName+'","'+rowId+'","'+gridId+'"); value="'+caption+'"  class="easyui-button" type="button" style="width:'+width+'; "/> ');						
			}
			else if( editType == "select"){

				var  optSelHtml ='<select id="'+fieldID+'" name="'+fieldID+'" style="width:'+width+';">';
				var selOptions  = editOpt.options;
				for(var j = 0; j< selOptions.length;j++ ){
					var selected ="";
					if( selOptions[j].text == value)
						selected ="selected='true'";
					optSelHtml += "<option  "+ selected + " value='"+ selOptions[j].id+"'>" +selOptions[j].text + "</Option>";
				} 
				optSelHtml += '</select>';
				tdcol.html(optSelHtml);
				
				jQuery("#"+fieldID).on(' click keydown ', function(e){
				    e.stopPropagation();
				});
				
				//jQuery("#"+fieldID).val(value);
			}
			else if( editType == "checkbox"){
				var checked ="";
				if(value=="1" || value=="Y" )
					checked ='checked="checked"';
				tdcol.html('<input id="'+fieldID+'" '+checked +' value="1" name="'+fieldID+'" type="checkbox" style="text-align:center;"/>');
			}
			
			tdcol.attr('presrvCellValue',value);
			/*if( editType == "combobox" ) 
				jQuery("#"+ fieldID).next('span').children("input").css('text-transform', 'uppercase');
			else if(editType != "button" )
				jQuery("#" + fieldID ).css('text-transform', 'uppercase');
			*/
			if(colM.mandatory  != undefined && (  colM.mandatory == true || colM.mandatory ==  "true") )
			{
				setMandatoryCell(gridId,rowId,colM.name);
			}		
			
		}	
	}
}

function setMandatoryCell(gridId,rowId,colName){
	jQuery("#"+gridId +" tr[id="+ rowId +"] td[aria-describedby="+ gridId +"_"+colName+"]").css("background-color","green");
}

function removeMandatoryCell(gridId,rowId,colName){
	jQuery("#"+gridId +" tr[id="+ rowId +"] td[aria-describedby="+ gridId +"_"+colName+"]").css('background-color','');
}

function restoreEdit(gridId, rowId){
	var grid = jQuery("#"+gridId );
	var colModels = grid.jqGrid("getGridParam", "colModel");
	for( var i =0;i< colModels.length ;i++){
		var colM = colModels[i];
		if( colM.pEditable == true && colM.pEditOptions != undefined){
			var colName = colM.name;
			var tdcol = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]');
			//var fieldID = colName + "_" + gridId+"_"+rowId ;//jQuery("#jqgh_" +gridId+"_"+colName).attr("formatterType");
			var value=tdcol.attr('presrvCellValue');
			if( value=="")
				value =" ";
			if(colM.mandatory  != undefined && (  colM.mandatory == true || colM.mandatory ==  "true") )
				removeMandatoryCell(gridId,rowId,colM.name);
			//if(fieldID.substring(0,3)=="cmb")
			//	value=tdcol.attr('comboName');
			
			tdcol.html(value);
		}
	}	
}

function getGridSelectArray(gridId){
	clearCommonErrorMsg();
	//var colName = ''; 
	
	var grid = jQuery("#"+gridId );
	var colModels = grid.jqGrid("getGridParam", "colModel");
	var selArray =  grid.jqGrid('getGridParam', 'selarrrow');

	if( selArray.length <= 0 ) return "";
	var jsonArr = '[';
	var value ="";
	var errMsg ="";
	var colHeader =  grid.jqGrid('getGridParam', 'colHeader');
	
	for( var ind=0;ind<selArray.length;ind++){
		jsonArr += '{';
		for( var i =0;i< colModels.length ;i++){
			var colM = colModels[i];
			var rowId = selArray[ind] ;
			if(colM.pSave ){
				
				if( colM.pEditable == true ){
					var fieldId = colM.name + "_" + gridId+"_"+rowId ;
					value = getFieldValue(fieldId);
					if(false == value || "false" == value )
						value = " ";
					else if(true == value || "true" == value )
						value = "1" ;
					
				}	
				else{
					value = jQuery("#"+gridId).jqGrid('getCell',rowId,colM.name);
					if( value.indexOf("<input") >= 0 || value.indexOf("<select") >=0 )
					{
						
						var idE = jQuery(value).attr("id");
						if( idE != undefined ){
							value =getFieldValue(idE);
							if(value == "true" || value==true )
							{	
								value = jQuery("#"+idE).val();
							}
							else if( value == "false" || value == false ){
								value = jQuery("#"+idE).val();
								if( value=="Y"  )
									value ="N";
								else if(value=="1")
									value= "0";
							}
						}
						else
							value = jQuery(value).val();
						
					}	
					//if( )
				}
				if(value != 0){ // madhan
					value = value.trim();
				//   alert("value trim: "+value);
				}
				//value = value.trim();
				/*if( colM.isMandatory &&   value.length <=0  ){
					errMsg += colHeader[ i ] + ", " ; 
					setErrCell(gridId,rowId,colM.name);
					*/
				var colname=colM.name;

				var tdcol = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colname+' ]');
				 
                if( colM.mandatory  &&   value.length <=0  || (  ( tdcol != undefined && tdcol != null && (tdcol.attr('Mandatory')=='true'|| tdcol.attr('Mandatory')==true) ) &&   value.length <=0  )){
	 					errMsg += colM.name + ", " ; 
	 					setErrCell(gridId,rowId,colM.name);
				}
				else if ( errMsg == "" ) {
					value = value.trim().replace(/["~!@#$%^&*\(\)_+=`\[\]\|\\'\/?"\-\t\r\n]+/g, '-');
					jsonArr += '"'+ colM.name +'":"'+value+'",'; // madhan escape(value)
					//jsonArr += '"'+ colM.name +'":"'+value+'",';  
				}
			}
		}
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += '},';
	}
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
	jsonArr += ']';
	//alert(jsonArr);
	if( errMsg.trim() == "" )
		return jsonArr;
	errMsg = "Please provide data for Mandatory Fields in Grid";
	//alert(" errMsg "  errMsg);
	showCommonErrorMsg(errMsg);
	div_err();
	return "";
} 
function getGridUnselectedData(gridId, chkColName){
	var grid = jQuery("#"+gridId );
	var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');

	var jsonArr = '[';
	var value ="";

	for( var ind=0;ind<rowIds.length;ind++){
		var rowId = rowIds[ind];
		var isSelected = jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked');
		var checkVal = jQuery("#"+gridId).jqGrid('getCell',rowId,chkColName);
		if( isSelected || (checkVal !="1" )) continue;
		
		jsonArr += '{';
		for( var i =0;i< colModels.length ;i++){
			var colM = colModels[i];
			
			if(colM.pSave ){
				
				if( colM.pEditable == true ){
					var fieldId = colM.name + "_" + gridId+"_"+rowId ;
					value = getFieldValue(fieldId);
				}	
				else{
					value = jQuery("#"+gridId).jqGrid('getCell',rowId,colM.name);
					if( value.indexOf("<input") >= 0 || value.indexOf("<select") >=0 )
					{
						
						var idE = jQuery(value).attr("id");
						if( idE != undefined ){
							value =getFieldValue(idE);
							if(value == "true" || value==true )
							{	
								value = jQuery("#"+idE).val();
							}
							else if( value == "false" || value == false ){
								value = jQuery("#"+idE).val();
								if( value=="Y"  )
									value ="N";
								else if(value=="1")
									value= "0";
							}
						}
						else
							value = jQuery(value).val();
						
					}	
					//if( )
				}
				value = value.trim();
			}
		}
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += '},';
	}
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
	jsonArr += ']';
	return  jsonArr.length>5?jsonArr:"";
}

function getGridUnselectedKeyids(gridId,keyIdColName, chkColName){
	var grid = jQuery("#"+gridId );
	var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');

	var jsonArr = '[';
	var value ="";

	for( var ind=0;ind<rowIds.length;ind++){
		var rowId = rowIds[ind];
		var isSelected = jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked');
		var checkVal = jQuery("#"+gridId).jqGrid('getCell',rowId,chkColName);
		if( isSelected || (checkVal !="1" && checkVal !="Y")) continue;
		
		var keyIdVal = jQuery("#"+gridId).jqGrid('getCell',rowId,keyIdColName);
		
		jsonArr += '"'+keyIdVal + '",';
		
	}
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
	jsonArr += ']';
	return  jsonArr.length>3?jsonArr:"";
}
function getGridRowData(gridId,rowId){
	clearCommonErrorMsg();
	//var colName = ''; 
	
	var grid = jQuery("#"+gridId );
	var colModels = grid.jqGrid("getGridParam", "colModel");
	//var selArray =  grid.jqGrid('getGridParam', 'selarrrow');

	//if( selArray.length <= 0 ) return "";
	var jsonArr = '[';
	var value ="";
	var errMsg ="";
	//var colHeader =  grid.jqGrid('getGridParam', 'colHeader');
	
	//for( var ind=0;ind<selArray.length;ind++){
		jsonArr += '{';
		for( var i =0;i< colModels.length ;i++){
			var colM = colModels[i];
			//var rowId = selArray[ind] ;
			if(colM.pSave ){
				
				if( colM.pEditable == true ){
					var fieldId = colM.name + "_" + gridId+"_"+rowId ;
					value = getFieldValue(fieldId);
				}	
				else{
					value = jQuery("#"+gridId).jqGrid('getCell',rowId,colM.name);
					if( value.indexOf("<input") >= 0 || value.indexOf("<select") >=0 )
					{
						var idE = jQuery(value).attr("id");
						if( idE != undefined ){
							value =getFieldValue(idE);
							if(value == "true" || value==true)
								value = jQuery("#"+idE).val();
							else{
								value = jQuery("#"+idE).val();
								if( value=="Y"  )
									value ="N";
								else if(value=="1")
									value= "0";
							}
						}
						else
							value = jQuery(value).val();
						
					}	
					//if( )
				}
				value = value.trim();
				if( colM.isMandatory &&   value.length <=0  ){
					//errMsg += colHeader[ i ] + ", " + 
					setErrCell(gridId,rowId,colM.name);
				}
				else if ( errMsg == "" ) {
					jsonArr += '"'+ colM.name +'":"'+value+'",';  
				}
			}
		}
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += '},';
	//}
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
	jsonArr += ']';
	//alert(jsonArr);
	if( errMsg.trim() == "" )
		return jsonArr;
	//alert(" errMsg "  errMsg);
	showCommonErrorMsg(errMsg);
	div_err();
	return "";
}

function setErrCell(gridId,rowId,colName){
	jQuery("#"+gridId +" tr[id="+ rowId +"] td[aria-describedby="+ gridId +"_"+colName+"]").css("background-color","red");
}


function setFormater(gridId,formId,url,rowId,colName,idColName,widthN,multiple, isTextArea, isNuemeric){
	 
	//var row = jQuery("#"+gridId).jqGrid('getDataIDs');
	 
	var grid = jQuery("#"+gridId );
	var colModel = grid.jqGrid("getGridParam", "colModel");
	var colM = getColModelForColName(colModel,colName);
	/*madhan*/
	var width = colM.width-4;
	var align = colM.align != undefined ? colM.align: "left";
	//var editType = colM.edittype 

	
	var isMultiple=( multiple== true ? true:false);
	var tdcol = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]');
	var fieldID = jQuery("#jqgh_" +gridId+"_"+colName).attr("formatterType");
	var value="";
	//var idValue="";
	var valueName="";
	value = jQuery("#"+gridId).jqGrid('getCell',rowId,colName);
	if(value==null || value.indexOf("<input") >=0 || value.indexOf("<select") >= 0 || value.indexOf("<textarea") >= 0 )
		value = "";
	else
		value = value.trim();
	
	if( idColName != undefined && idColName != null && idColName.trim().length > 0){
		idValue = jQuery("#"+gridId).jqGrid('getCell',rowId,colName);
	}
	

	if(fieldID.substring(0,3) == "txt" && isTextArea != true ) {
		
		tdcol.html('<input id="'+gridId+fieldID+"_"+rowId+'" name="'+gridId+fieldID+"_"+rowId+'" onchange=txtOnChange("'+gridId+fieldID+'","'+rowId+'"); onblur=txtOnBlur("'+gridId+'","'+fieldID+'","'+rowId+'","'+colName+'"); onfocus=txtOnFocus("'+gridId+fieldID+'","'+rowId+'"); class="easyui-text" style="width:'+width+';text-align:'+align+'" value="'+value+'"/> ');
		if(isNuemeric){
			numericTextBox(gridId+fieldID+"_"+rowId);
		}
	}
	else if(fieldID.substring(0,3) == "txt" ) {
		tdcol.html('<textarea  id="'+gridId+fieldID+"_"+rowId+'" name="'+gridId+fieldID+"_"+rowId+'" onchange=txtOnChange("'+gridId+fieldID+'","'+rowId+'"); onblur=txtOnBlur("'+gridId+'","'+fieldID+'","'+rowId+'","'+colName+'"); onfocus=txtOnFocus("'+gridId+fieldID+'","'+rowId+'"); class="easyui-text" rows="2" style="width:'+width+';text-align:'+align+'" >' + value+ '</textarea>');						
	}
	else if(fieldID.substring(0,3) == "dte") { 
		tdcol.html('<input id="'+gridId+fieldID+"_"+rowId+'" name="'+gridId+fieldID+"_"+rowId+'" style="width:'+width+';"/> ');
		formatDateBox( gridId+fieldID+"_"+rowId ,'dd-MMM-yyyy');
		setFieldValue(gridId+fieldID+"_"+rowId,value);
		
	}
	else if(fieldID.substring(0,3) == "cmb") {
		
		tdcol.html('<input id="'+gridId+fieldID+"_"+rowId+'" name="'+gridId+fieldID+"_"+rowId+'" class="easyui-combo" style="width:'+width+';"/> ');
		
		jQuery('#'+gridId+fieldID+"_"+rowId).combobox({
			mode:'local',
			url:url,
			dataType:'json',		
			valueField:'id',
			textField:'text',
			multiple:isMultiple ,
			panelHeight:130,
			onSelect:function(record)
			{
				var onSelectFunctionName = gridId+fieldID +'_onSelect';
				value=record.id;
				valueName=record.text;
				
				jQuery("#"+gridId).jqGrid('setCell',rowId,idColName,record.id);
				//alert("title  "+jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]').attr('title'));
				jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]').attr('title',valueName);
				//jQuery('tr[id='+rowId+']td[aria-describedby='+gridId+'_'+fieldID+' ]').attr('title',valueName);
				 
				
				var args = [record]; 
				 dynamicFunctionCall(onSelectFunctionName,args);
			} 
		} );
		
		jQuery('#'+gridId+fieldID+"_"+rowId).combobox({
			filter: function(q, row){
				var opts = jQuery(this).combobox('options');
				return row[opts.textField].toLowerCase().indexOf(q.toLowerCase()) >= 0;			
			}
		});
		
		if(isMultiple){
			var arrayVal = value.split(","); //JSON.parse("[" + value + "]");
			jQuery("#"+gridId+fieldID+"_"+rowId).combobox("setValues",arrayVal);
		}
		else{
			//jQuery("#"+gridId+fieldID+"_"+rowId).combobox("setValue",value);
			setComboValueSilent(gridId+fieldID+"_"+rowId, value);
		}
		valueName = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]').attr('title');
		tdcol.attr('comboName',valueName);
	}
	else if(fieldID.substring(0,3) == "btn") { 		
		tdcol.html('<input id="'+gridId+fieldID+"_"+rowId+'" name="'+gridId+fieldID+"_"+rowId+'" onclick=grdBtnClick("'+gridId+fieldID+'","'+rowId+'"); value="'+idColName+'"  class="easyui-button" style="width:'+width+';"/> ');						
	}
	
	tdcol.attr('presrvCellValue',value);

}
function removeFormater(gridId,formtType,rowId,value,colName){
	//var grid = jQuery("#"+gridId); 
	var tdcol = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]');
	var fieldID = jQuery("#jqgh_" +gridId+"_"+colName).attr("formatterType");
	var value=tdcol.attr('presrvCellValue');
	if(fieldID.substring(0,3)=="cmb")
		value=tdcol.attr('comboName');
	
	//alert("cmb"+value);
	tdcol.html(value);

}
/*function removeFormater(gridId,formtType,rowId,value,colName){
	var grid = jQuery("#"+gridId );  
	grid.find('td[role=gridcell]').each (function()
			{ 
				var id=jQuery(this).parent().attr('id');
				var aria=jQuery(this).attr('aria-describedby');
				var fieldID = jQuery("#jqgh_" +gridId+"_"+colName).attr("formatterType");
				var value=jQuery(this).attr('colValue');
				 
				if(fieldID.substring(0,3)=="cmb")
					value=jQuery(this).attr('comboName');
				
				if(id == rowId && gridId+"_"+colName ==aria ){ 
					 
					jQuery(this).html(value); 
					//jQuery("#"+gridId).jqGrid('setCell',rowId,'TargetDate2',value);
					jQuery(this).html('<input id="cmbTargetDate_'+rowId+'" name="cmbTargetDate_'+rowId+'" class="easyui-combo"/> ');
					 fillComboBox("frmTrngCalendar","cmbTargetDate_"+rowId,"employee.commonFilter");
				}
			
			});
        }
*/ 
function txtOnChange(gridId,txtId,rowId,colName ){
	var recordData = '{"gridId":"'+gridId+'" ,"txtId":"'+txtId+'" ,"rowId":"'+rowId+'"}';
	var record = JSON.parse(recordData);
	var args = [record];
	var txtfuncName = gridId+"_onChange";
	 dynamicFunctionCall(txtfuncName,args);
}
function txtOnBlur(gridId,txtId,rowId,colName ){ 
	  
	var tdcol = jQuery("#"+gridId + ' tr[id='+ rowId +"]").find('td[aria-describedby='+gridId+'_'+colName+' ]');
	var txtValue = jQuery('#'+gridId+txtId+"_"+rowId).val();
	tdcol.attr('colvalue',txtValue);
	tdcol.attr('title',txtValue);
	//var txtfuncName = txtId.substring(0,txtId.indexOf('_'))+"_onBlur";
	var recordData = '{"gridId":"'+gridId+'" ,"txtId":"'+txtId+'" ,"rowId":"'+rowId+'"}';
	var record = JSON.parse(recordData);
	var args = [record];
	var txtfuncName = gridId+"_onBlur";
	dynamicFunctionCall(txtfuncName,args);
}
function txtOnFocus(gridId,txtId,rowId,colName ){
	var recordData = '{"gridId":"'+gridId+'" ,"txtId":"'+txtId+'" ,"rowId":"'+rowId+'"}';
	var record = JSON.parse(recordData);
	var args = [record];
	var txtfuncName = gridId+"_onFocus";
	// dynamicFunctionCall(txtfuncName,args);
	setTimeout(function(){
	        dynamicFunctionCall(txtfuncName, args);
	    }, 0);
}

function grdBtnClick(btnId,rowId,gridId){
	var recordData = "";
	if( gridId != undefined && gridId != null && gridId != "")
		recordData = '{"btnId":"'+btnId+'" ,"rowId":"'+rowId+'","gridId":"'+gridId+'"}';
	else
		recordData = '{"btnId":"'+btnId+'" ,"rowId":"'+rowId+'"}';
	
	var record = JSON.parse(recordData);
	var args = [record];

	var btnfuncName = btnId+"_onClick";
	 dynamicFunctionCall(btnfuncName,args);
}


function workFlow(divId,isPopup,transCode, documentId, documentType, flId,minDate,maxDate,enabledY){
	var refRoleId= jQuery("#hdnUserRole").val();//getFieldValue("cmbEmployeRoles");	
	if( enabledY == undefined )
		enabledY = "Y";
	
	var urlStr = "wrktrn_workflowapp_input.workflow?refId="+ documentId+'&refType='+documentType ;
	urlStr += "&transCode="+transCode+"&flId="+flId+"&minDate="+minDate+"&maxDate="+maxDate;
	urlStr += "&refRoleId="+refRoleId+"&divId="+divId +"&enable="+enabledY;
	if( isPopup == true){
		LoadPopUp(divId,urlStr,true,"70%","92%","1%","14%","","Work Flow","","",true,"");
	}	
	else{
		LoadForm(divId,"",urlStr,"div_err","workFlow_Load_callbackOnSuccess","workFlow_Load_callbackOnError");
		
	}
}

function workFlow_Load_callbackOnSuccess(result){
	
}
function workFlow_Load_callbackOnError(result){
}

function dmcworkFlow(divId,isPopup,transCode, documentId, documentType, flId,minDate,maxDate,enabledY){
	var refRoleId= jQuery("#hdnUserRole").val();//getFieldValue("cmbEmployeRoles");	
	if( enabledY == undefined )
		enabledY = "Y";
	
	var urlStr = "dmcwrktrn_workflowapp_input.workflow?refId="+ documentId+'&refType='+documentType ;
	urlStr += "&transCode="+transCode+"&flId="+flId+"&minDate="+minDate+"&maxDate="+maxDate;
	urlStr += "&refRoleId="+refRoleId+"&divId="+divId +"&enable="+enabledY;
	if( isPopup == true){
		LoadPopUp(divId,urlStr,true,"70%","92%","1%","14%","","Work Flow","","",true,"");
	}	
	else{
		LoadForm(divId,"",urlStr,"div_err","dmcworkFlow_Load_callbackOnSuccess","dmcworkFlow_Load_callbackOnError");
		
	}
}

function dmcworkFlow_Load_callbackOnSuccess(result){
	
}
function dmcworkFlow_Load_callbackOnError(result){
	
}

function duplicateGridRow(gridId,rowId){
	   var newId = jQuery("#"+gridId).getGridParam("reccount")+1;
	   
	   var curData = jQuery("#"+gridId).jqGrid('getRowData',rowId);
	   jQuery("#"+gridId).jqGrid('addRowData', newId, curData , "after", rowId);
}
function addNewGridRow(gridId){
	   var newId = jQuery("#"+gridId).getGridParam("reccount")+1;
	   
	   var curData = jQuery("#"+gridId).jqGrid('getRowData',"0");
	   jQuery("#"+gridId).jqGrid('addRowData', newId, curData );
}

function getGridUnselectedData(gridId, chkColName){
	var grid = jQuery("#"+gridId );
	var rowIds = jQuery("#"+gridId).jqGrid('getDataIDs');

	var jsonArr = '[';
	var value ="";

	for( var ind=0;ind<rowIds.length;ind++){
		var rowId = rowIds[ind];
		var isSelected = jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked');
		var checkVal = jQuery("#"+gridId).jqGrid('getCell',rowId,chkColName);
		if( isSelected || (checkVal !="1" )) continue;
		
		jsonArr += '{';
		for( var i =0;i< colModels.length ;i++){
			var colM = colModels[i];
			
			if(colM.pSave ){
				
				if( colM.pEditable == true ){
					var fieldId = colM.name + "_" + gridId+"_"+rowId ;
					value = getFieldValue(fieldId);
				}	
				else{
					value = jQuery("#"+gridId).jqGrid('getCell',rowId,colM.name);
					if( value.indexOf("<input") >= 0 || value.indexOf("<select") >=0 )
					{
						
						var idE = jQuery(value).attr("id");
						if( idE != undefined ){
							value =getFieldValue(idE);
							if(value == "true" || value==true )
							{	
								value = jQuery("#"+idE).val();
							}
							else if( value == "false" || value == false ){
								value = jQuery("#"+idE).val();
								if( value=="Y"  )
									value ="N";
								else if(value=="1")
									value= "0";
							}
						}
						else
							value = jQuery(value).val();
						
					}	
					//if( )
				}
				value = value.trim();
			}
		}
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += '},';
	}
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
	jsonArr += ']';
	return  jsonArr.length>3?jsonArr:"";
}

/**Added BY Manikandan restrict the max number pasted in texTAREA**/
jQuery('.limit-length').onpaste = function(e){
    //do some IE browser checking for e
    var max = test.getAttribute("maxlength");
    e.clipboardData.getData('text/plain').slice(0, max);
};

jQuery('.limit-length').keyup(function() { 
	  var maxChar = jQuery(this).attr('maxlength'),
	      current_val = jQuery(this).val();
	  if(current_val.length > maxChar){
		  current_val(current_val.substr(0, maxChar));
	}	
});	 


//setup grid print capability.  Add print button to navigation bar and bind to click.
/*function setPrintGrid(gid,pid,pgTitle){
    // print button title.
    var btnTitle = 'Print Grid';

   /* // setup print button in the grid top navigation bar.
    jQuery('#'+gid).jqGrid('navSeparatorAdd','#'+gid+'_toppager_left', {sepclass :'ui-separator'});
    jQuery('#'+gid).jqGrid('navButtonAdd','#'+gid+'_toppager_left', {caption: '', title: btnTitle, position: 'last', buttonicon: 'ui-icon-print', onClickButton: function() {    PrintGrid();    } });

    // setup print button in the grid bottom navigation bar.
    jQuery('#'+gid).jqGrid('navSeparatorAdd','#'+pid, {sepclass : "ui-separator"});
    jQuery('#'+gid).jqGrid('navButtonAdd','#'+pid, {caption: '', title: btnTitle, position: 'last', buttonicon: 'ui-icon-print', onClickButton: function() { PrintGrid();    } });
*/
    function PrintGrid(gid){
        // attach print container style and div to DOM.
        jQuery('head').append('<style type="text/css">.prt-hide {display:none;}</style>');
        jQuery('body').append('<div id="prt-container" class="prt-hide"></div>');

       
        // copy and append grid view to print div container.
        jQuery('#gview_'+gid).clone().appendTo('#prt-container').css({'page-break-after':'auto'});

        // remove navigation divs.
        jQuery('#prt-container div').remove('.ui-jqgrid-toppager,.ui-jqgrid-titlebar,.ui-jqgrid-pager');
        jQuery('#prt-container div').remove('.ui-search-toolbar');

        // print the contents of the print container.    
        jQuery('#prt-container').printElement({pageTitle:"", overrideElementCSS:[{href:'css/print-container.css',media:'print'}]});

        // remove print container style and div from DOM after printing is done.
        jQuery('head').removeClass(".prt-hide");
        jQuery('body #prt-container').remove();
    }
//}
    function getValueBySeparator(filterString,valueIdentifier,separator)
    {
    	var val="";
    	if(separator.length==0)
    		separator="#";
    	if( filterString != null && valueIdentifier != null && filterString.indexOf(valueIdentifier) > -1 )
    	{
    		var substr = filterString.substring(filterString.indexOf(valueIdentifier)+valueIdentifier.length+1 );
    		if(substr.indexOf(separator)<substr.length && substr.indexOf(separator)!= -1){
    			val = substr.substring(0,substr.indexOf(separator));
    		}else
    			val = substr.substring(0,substr.length);
    	}
    	return val;
    }
    function loadNextDrillLevel(gridId, rowid,drillflag,field,keyfieldData){
    	var url=jQuery("#"+gridId).jqGrid('getGridParam','url');
    	url = url.replace('input','getData');
    	
    	var rowIds = jQuery("#"+gridId).getDataIDs();
    	var filterData ="";	
    	var selId="";
    	if(rowIds.length>0){
	    	var keyField =  jQuery("#"+gridId).jqGrid('getCell', rowid,field );
	    	var celldata= keyField.split("#");	
	    	selId=celldata[0];
    	}else{
    		var KeyFieldArr=keyfieldData.split(",");
    		var KeyLen=KeyFieldArr.length;
    		selId=KeyFieldArr[KeyLen-2];
		}
    	
    	url = removeValueFromUrl(url, "drillFlag");
    	var firstClick = getValueBySeparator(url,"firstClick","&");
    	url = removeValueFromUrl(url, "firstClick");
    	if( firstClick == "B")
    		url = url +"&firstClick=Y";
    	else if(firstClick == "Y" )
    		url = url +"&firstClick=B";
    	else{
    		url = removeValueFromUrl(url, "flid");
    		url = url + "&firstClick=N";
    		filterData += '&flid='+ selId; 
    	}	
    	
    	
    	filterData += '&drillFlag='+drillflag;
    	
    	
    	url+=filterData;
    	jQuery("#"+gridId).setGridParam({url:url}).trigger('reloadGrid');
    	if( firstClick == "B"){
	    	//url = removeValueFromUrl(url, "firstClick");
	    	//jQuery("#"+gridId).setGridParam({url:url});
    	}	
    }
        
    
    function openWhyWhy(divId,isPopup,refDocId,refDocType,flId, refDocDate, problem, yyMode, forwardData, persistentData, attendedBy,area,pillar)
    {	
    	if( refDocId== null || refDocId.trim() == '' && problem== null || problem.trim() == '')
    	{
    		alert(" Enter Valid Conditions ");
    		return false;
    	}
    	else{		
    	
    		
    		var dataStr ="whywhyRefDocID="+refDocId+"&whywhyRefDocType="+refDocType+"&flid="+flId;
    		dataStr+="&refDocDate="+refDocDate+"&prbolem="+problem+"&yyMode="+yyMode+"&pillar="+pillar;
    		
    		if (attendedBy==undefined || attendedBy== null || attendedBy==' '|| attendedBy=='undefined'  )
    			dataStr+="&attendedBy=";
    		else
    			dataStr+="&attendedBy="+attendedBy;
    		
    		if (area==undefined || area== null || area==' '|| area=='undefined'  )
    			area='';
    		else
    			dataStr+="&area="+area;
    		
    		
    		if (isPopup==true) 
    			LoadPopUp(divId,"whywhy_input.why?"+dataStr,true,"83%","90%","3%","7%","","Why Why Analysis","",true);
    		else
    			navigateToNextForm("whywhy_input.why?"+dataStr,'Why Why Analysis', forwardData, persistentData);
        }
		//whywhyanalysismodify_input
    }
    function setDrillHeader(eleType){
    	var levHeader="";
    	if(eleType.trim()=="CMP")
    		levHeader="Company";
    	else if(eleType.trim()=="LCN")
    		levHeader="Location";
    	else if(eleType.trim()=="SBU")
    		levHeader="SBU";
    	else if(eleType.trim()=="PBU")
    		levHeader="PBU";
    	else if(eleType.trim()=="L")
    		levHeader="DMT";
    	else if(eleType.trim()=="C")
    		levHeader="JH";
    	else if(eleType.trim()=="M")
    		levHeader="Machine";
    	return levHeader;			
    }
    function setDrillDownHeader(headerDiv,gridId,hdrColIndex){
    	
    	var rowIds = jQuery("#"+gridId).getDataIDs();
    	if(rowIds.length>=0)
		{
			var keyField =  jQuery("#"+gridId).jqGrid('getCell', rowIds[0], hdrColIndex);	
			var celldata= keyField.split("#");	
			var parentId=getValueBySeparator(celldata[2],"N","#");
			var url=jQuery("#"+gridId).jqGrid('getGridParam','url');
			var firstClick = getValueBySeparator(url,"firstClick","&");
			url = removeValueFromUrl(url, "firstClick");
			var elementType = getValueBySeparator(url,"elementType","&");
			var element=getValueBySeparator(celldata[1],"L","#");

			var setEltype = getValueBySeparator(url,"setEltype","&");
			if( setEltype == "Y"){
				url = removeValueFromUrl(url, "setEltype");
			}
			if(firstClick == "Y" || setEltype == "Y"){
				url = removeValueFromUrl(url, "elementType");
				url +='&elementType='+getValueBySeparator(celldata[1],"L","#");
				
			}
			if(firstClick == "Y" )
				parentId="B";
			
			jQuery("#"+gridId).setGridParam({url:url});
				
			if(element.trim()==elementType.trim() && firstClick != "Y")
				parentId="B";
			
			if(parentId.contains('B') ){			
				hideShowBack(false);
			}	
			else			
				hideShowBack(true);		
    		
	    	var eleType=getValueBySeparator(celldata[1],"L","#");
	    	
	    	var levHeader= setDrillHeader(eleType);
	    	jQuery("#"+headerDiv).html(levHeader);
		}
   }
    
    function setDrillProcesGridBack(gridId,hdrColIndex,keyfieldData){
    	var rowIds = jQuery("#"+gridId).getDataIDs();
    	if(rowIds.length>0){
    		var keyField =  jQuery("#"+gridId).jqGrid('getCell', rowIds[0], hdrColIndex);
    		var celldata= keyField.split("#");
    		if((keyField.trim()).length>0){
    				
    			var click=getValueBySeparator(celldata[2],"N","#");	
    			if(click.contains('B'))
    				return false;
    		}
    		var url=jQuery("#"+gridId).jqGrid('getGridParam','url');
    		var elType =  getValueBySeparator(url,"elementType","&");
    		var eleType=getValueBySeparator(celldata[1],"L","#");
    		var setEtype = getValueBySeparator(url,"setEltype","&");
    		
    		if(elType == eleType || setEtype == "Y" ){
    			url = removeValueFromUrl(url, "firstClick");
    			url = url + "&firstClick=B";
    			var setEtype = getValueBySeparator(url,"setEltype","&");
    			jQuery("#"+gridId).setGridParam({url:url});
    		}
    		loadNextDrillLevel(gridId,rowIds[0],'b',hdrColIndex,keyfieldData);
    	}else
    		loadNextDrillLevel(gridId,rowIds[0],'f',hdrColIndex,keyfieldData);
    }
    
  /*  function setDrillDoubleClick(gridId,hdrColIndex,id,keyfieldData){
    	var rowIds = jQuery("#"+gridId).getDataIDs();
    	if(rowIds.length>0){
    		var keyField =  jQuery("#"+gridId).jqGrid('getCell', rowIds[0], hdrColIndex);
    		var celldata= keyField.split("#");	
    		var click=getValueBySeparator(celldata[2],"N","#");
    		if(click.contains('Y') || click.contains('B')){
    			if((keyfieldData.trim()).length>0)
    				keyfieldData=keyfieldData+","+celldata[0];
    			else
    				keyfieldData=celldata[0];
				loadNextDrillLevel(gridId,id,'f',hdrColIndex,keyfieldData);	
				return keyfieldData;
    		}else
    			return false;
    	}
    }
    */
    function setDrillDoubleClick(gridId,hdrColIndex,id,keyfieldData){
    	var rowIds = jQuery("#"+gridId).getDataIDs();
    	if(rowIds.length>0){
    		var url=jQuery("#"+gridId).jqGrid('getGridParam','url');
    		var fClick = getValueBySeparator(url,"firstClick","&");

    		//var sELt = getValueBySeparator(url,"setEltype","&");
    		//if( sELt == "Y" )
    		//	url = removeValueFromUrl(url, "setEltype");
    		
    		if( fClick == "Y" )
    			url = url + "&setEltype=Y";
    		
    		jQuery("#"+gridId).setGridParam({url:url});
    		
    		var keyField =  jQuery("#"+gridId).jqGrid('getCell', rowIds[0], hdrColIndex);
    		var celldata= keyField.split("#");	
    		var click=getValueBySeparator(celldata[2],"N","#");
    		if(click.contains('Y') || click.contains('B')){
    			if(celldata.length>4)
    				keyfieldData=getValueBySeparator(celldata[2],"PF","#");
    			else
    				keyfieldData=getValueBySeparator(celldata[3],"PF","#");
    			/*if((keyfieldData.trim()).length>0)
    				keyfieldData=keyfieldData+","+celldata[0];
    			else
    				keyfieldData=celldata[0];*/
				loadNextDrillLevel(gridId,id,'f',hdrColIndex,keyfieldData);	
				return keyfieldData;
    		}else
    			return false;
    	}
    }
    
    function getFunctionalLocation(formId){
        
        var child = jQuery('#'+formId +' div[id=dispFunctionalLoc]').children();
       var funLocStr = "";
       jQuery(child).each(function(){
               
                funLocStr += jQuery(this).children().children().html() + " / "; // attr("title") + "/";  
               
       });
       
       return funLocStr;
    }
    
    function replaceAllDoubleQuots(str){
    	return str != undefined ? str.replace(/"/g, '\\"'):"";
    }
