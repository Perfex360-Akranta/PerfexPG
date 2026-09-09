/**
 * @Author:Admin
 * @Description:Generic JavaScript &  jQuery functions used in the project
 */

 
if (typeof String.prototype.startsWith != 'function') {
  String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
  };
}

if (typeof String.prototype.startsWith != 'function') {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g,"");
	};
}

function LoadForm(loadDIVid,preloadDIVid,url,errMsgDispDIVId,callbackFunctionOnSuccess,callbackFunctionOnError)
{
	
	if( url != null)
	{	
		if( ! jQuery("#"+preloadDIVid).hasClass("tpm-loading"))
			jQuery("#"+preloadDIVid).addClass("tpm-loading");
		
		jQuery("#"+preloadDIVid).css("display","block"); 
	 	jQuery("#"+loadDIVid).css("display", "none");
		jQuery("#"+loadDIVid).load(url, function(response, status, xhr) {
		
		  if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		    jQuery("#"+errMsgDispDIVId).html(msg + xhr.status + " " + xhr.statusText);
		    if( callbackFunctionOnError != null)
		    	eval(callbackFunctionOnError+'(response)');
		  }
		  else{
			  
			  if( callbackFunctionOnSuccess != null)
				  eval(callbackFunctionOnSuccess+'(response)');
		  }
		  
		  jQuery("#"+preloadDIVid).hide(); 
		  jQuery("#"+loadDIVid).show();

		});
	}	
}

function setGridBackgroundColor(gridId){
	jQuery("#" + gridId ).parent().parent().css("background-color", "Red");
	//jQuery("#" + gridId ).parent().parent().css("font-size", "60px");
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
			if( val == null || val == "" )
				return false;
			
			return true ;
		}	
	}
	return false;
}

function getFilterValue(filterString,valueIdentifier)
{
	if( filterString != null && valueIdentifier != null )
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
										//alert("dsfg");
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
					tableString+="<div style='padding-left:2px;padding-top:1px;'>";
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
		            jQuery('#btnJqGridBack').click(function (){
		            	 try{
		            			window[tableId+"_onProcessGridBack"].apply(this,null);
		            			
		            		}catch(Exception ){
		            			
		            		}
		            });	
		            
		            jQuery("#"+tableId).jqGrid('bindKeys');
		            
		            if( tableHeaderSpanCallback!= null && tableHeaderSpanCallback.length > 0 )
		            {	
	            		var headers = mygrid[0].grid.headers;
	            		eval(tableHeaderSpanCallback +'(colM,headers,colN)');
		                
		            }	
		        },
		        error: function(status){
		        	alert('err');
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
function exportExcel(tableId)
{
    jQuery("table").attr("border","1");
    jQuery("table").attr("rules","all");
  
    //var fileName = jQuery(".ui-jqgrid-title").text();
    var fileName = getFormMainHeader();
    fileName = fileName.replace(" ","").replace(" ","");
    var exporthtml = jQuery("#gview_"+tableId).html();
    if(jQuery("#formxlexport").length <= 0 )
    {	  
        var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST" > ' +
        		   '<input type="hidden" id="fileName" name="fileName" /> '+
      	  	   '<input type="hidden" id="exporthtml" name="exporthtml"/> </form>';
        jQuery("#LoadContent").prepend(xlFormHtml);
        
    }
	 jQuery("#exporthtml").val("");
	 exporthtml = exporthtml.replace(jQuery("#pg_"+tableId+"_toppager").html(),"");
	 jQuery("#exporthtml").val(exporthtml);
	 
	 //alert(jQuery("#pg_"+tableId+"_toppager").html());
	 //jQuery("#pg_"+tableId+"_toppager").html("");
	 jQuery("#fileName").val(fileName);
	 document.formxlexport.method='POST';
	 document.formxlexport.action='exportXL';  // send it to server which will open this contents in excel file
	 document.formxlexport.target='_blank';
	 document.formxlexport.submit();
	 jQuery("#exporthtml").val("");

}

function processAjaxCalls(url,data,onsuccessCallBack,onerrorCallBack)
{
	jQuery.ajax({
	       type: "POST",
	       url: url,
	       dataType: "json",
	       data:data,
	       success: function(result)
	       {
	    	  eval( '('+onsuccessCallBack +'(result) )' ); 	
	       },
		   error:function(status)
		   {
			   eval( '('+onerrorCallBack +'(status) )' ); 
		   }
	});      
}
var glbcmbCellid,glbcmbSectionId,cmbFactId;

function fillMachineHierarchy(url,machineId,cmbCellId,cmbSectionId,cmbFactId,cmbCompId){
	glbcmbCellid=cmbCellId;
	glbcmbSectionId=cmbSectionId;
	glbcmbFactId=cmbFactId;
	processAjaxCalls(url,'machineId='+machineId,'machineHierarchy_OnSuccess','machineHierarchy_OnError');
}

function machineHierarchy_OnSuccess(result)
{
	jQuery("#"+glbcmbFactId).combobox('setValue',result.machineHirerachy.factory);
	jQuery("#"+glbcmbSectionId).combobox('setValue',result.machineHirerachy.section);
	jQuery("#"+glbcmbCellid).combobox('setValue',result.machineHirerachy.cell);
}
function machineHierarchy_OnError(result){
	
}

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


/*
function processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback ){
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
		    	   //alert(result.rowHeaders);
		            colN = result.rowHeaders;
		            colM = result.colModel;

		            var colHeaders = colN[0]; //-------test---
		            //alert( colHeaders);
		            var groupByField = result.groupByField;
					groupBy = (result.isGroupBy == "true" ? true: false);
					var rownumbers = (groupBy == true ? false : (result.isRowSumbers =="false"?true:false));
					groupSummary = (groupBy == true ? result.groupSummary == "true" ?true : false : false);
		            if( result.tableCaption != null && result.tableCaption != "")
		            	tableCaption = result.tableCaption;
		            
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
								    colNames: colHeaders,  //-------test---
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
		            
		          	
            		//var headers = mygrid[0].grid.headers;
            		columnHeaderSpan(colM,colN);
	            	
		        },
		        error: function(status){
			  	}
		});
	
	  jQuery("#preLoadContent").hide(); 
	  jQuery("#LoadContent").show();

}
function cellObject(){
	this.rowspan=1;
	this.colspan=1;
	this.caption=" ";
}

function columnHeaderSpan(colModel,colHeaders){
	
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
			for(  j = 0 ; j< colHeaders[i].length-1;j++){
				if( colHeaders[i][j ] != colHeaders[i][j+1] )
				{
					var celllObject = new cellObject();
					celllObject.colspan =colSpan;
					celllObject.rowspan=1;
					celllObject.caption = colHeaders[i][j ]; 
					colSpan=1;
					rowHeaders[rowIndx][colIndex++ ] = celllObject;
				}
				else{
					++colSpan;
				}
			}
			if( colHeaders[i][j ] == colHeaders[i][j-1] && i == 1)
				colSpan--;
			var celllObject = new cellObject();
			celllObject.colspan =colSpan;
			celllObject.caption = colHeaders[i][j-1 ]; 
			rowHeaders[rowIndx][colIndex++ ] = celllObject;
			rowIndx++;
			
		}
		for(var i=0;i<rowHeaders.length-1;i++)
		{
			for(var j =0;j<rowHeaders[i].length;j++)
			{
				for(var k=i+1;k<rowHeaders.length ;k++){

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
		for( var i = 0;i<rowHeaders.length;i++)
		{
			var tr = '<tr class="jqgridheaderrow'+(i+1) +'">';
			for( var j = 0;j<rowHeaders[i].length;j++)
			{
				var cellobj = rowHeaders[i][j];
				//alert(cellobj.rowspan);
				if( cellobj.rowspan > 0)
					tr += '<th class="ui-state-default ui-th-ltr"  colspan="'+ cellobj.colspan +'" rowspan="'+ cellobj.rowspan +'" role="columnheader">'+cellobj.caption +'</th>';
			}
			tr += '</tr>';
				
			mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
		}	
	}	
}	
	//var tr 
/*	var tr2 ="<tr class='ui-jqgrid-labels' role='rowheader'>";
		tr +=  '<th class="ui-state-default ui-th-ltr"  role="columnheader"></th>';
	 	tr1 +=  '<th class="ui-state-default ui-th-ltr"  role="columnheader">                             </th>';
		
	    for(var i=0;i<colModel.length;i++) {
			cmi = colModel[i];
			if (cmi.name == 'abnHeader') {
				if (skip === 0) {
                  	  jQuery(ths[i].el).attr("rowspan", "4");
                    }else {
                        skip--;
                    }
       		 }
	     		else {
				 if (cmi.name == 'abnIdentified') {						 	 
					      tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">Abnormalities</th>';	                         
				  }
				 else if (cmi.name == 'whiteIdentified') {
						  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">White</th>';
				 }		  
				 else if (cmi.name == 'redIdentified') {
							  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">Red</th>';		  
				  }
				 skip = 2; // because we make colspan="3" the next 2 columns should not receive the rowspan="2" attribute
			 
				if( cmi.name != "FieldID" ){
					if( i % 2 == 1)
						tr1 += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader"> ' + headernames[i] + '</th>';
						
					//tr2 += '<th class="ui-state-default ui-th-ltr"  role="columnheader"> ' + headernames[i] + '</th>';
					tr2 += getColHeaderHtml(headernames[i],"list_"+cmi.name);
				}	
	     		}	
	  }
    tr += "</tr>";
    tr2 += "</tr>";

    //jQuery("tr.ui-jqgrid-labels").prepend(tr);
     jQuery("tr.ui-jqgrid-labels").remove();
    //jQuery(".ui-jqgrid-labels").remove();
 //  mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").prepend(tr);
     mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
     mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr2);
*/    

//}


/*
function processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback ){
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
	
		            colN = result.rowHeaders;
		            colM = result.colModel;

		            var colHeaders = colN[0]; //-------test---
		            //alert( colHeaders);
		            var groupByField = result.groupByField;
					groupBy = (result.isGroupBy == "true" ? true: false);
					var rownumbers = (groupBy == true ? false : (result.isRowSumbers =="false"?true:false));
					groupSummary = (groupBy == true ? result.groupSummary == "true" ?true : false : false);
		            if( result.tableCaption != null && result.tableCaption != "")
		            	tableCaption = result.tableCaption;
		            
		        /*    for(var i = 0; i < colM.length;i++ ){
		            	
		             	if( colM[i].formatter != null){
		             		var functObj = eval('(' + colM[i].formatter + ')');
		             		colM[i]["formatter"] = functObj ;
		             	}
		             	if(colM[i].cellattr != null){
		             		var cellattrObj = eval('( function (rowId, tv, rawObject, cm, rdata){ return ' + colM[i].cellattr + ';})');
		             		colM[i]["cellattr"] = cellattrObj ;
		             	}
		            }
				----
		            jQuery("#"+tableId).GridUnload();
		            mygrid =  jQuery("#"+tableId);
		            jQuery("#"+tableId).jqGrid({
				             		url:getDataUrl,
								    datatype: 'json',
								    colNames: colHeaders,  //-------test---
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
		            
		          	
            		//var headers = mygrid[0].grid.headers;
            		columnHeaderSpan(colM,colN);
	            	
		        },
		        error: function(status){
			  	}
		});
	
	  jQuery("#preLoadContent").hide(); 
	  jQuery("#LoadContent").show();

}
*/
function processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,onloadcompletecallback,selectRowFunction ){
	//var mygrid ;
	
	var getDataUrl ="";
	var tableButton =true;
	var getColumnUrl = url.replace('input','getCol');
	
	if( filterString != null &&  filterString !="")
		getDataUrl = url.replace('input','getData');
	
	getColumnUrl = getColumnUrl.replace('view','getCol');
	if( filterString != null &&  filterString !="")
		getDataUrl = getDataUrl.replace('view','getData');
	
	getDataUrl += filterString;
		
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
		            var colN = result.rowHeaders != null ? result.rowHeaders :result.colNames;
		            var colM = result.colModel;
		            tableButton = ( result.tableButton != null ? result.tableButton==true?true:false:true);

		            var loadOnce =( result.loadOnce != null ? result.loadOnce==true?true:false:false);
		            var cellEdit =( result.cellEdit != null ? result.cellEdit==true?true:false:false);
		            var cellSubmit="";
		            if( cellEdit == true)
		            	cellSubmit	= ( result.cellSubmitLocal != null ? result.cellSubmitLocal==false?'remote':'clientArray':'clientArray');
		            var tableWidth= result.tableWidth;
		            var tableHeight=result.tableHeight;
		            
		            var colHeaders = colN[0]; 

		            if( result.dataURL != null && result.dataURL != "")
		            	getDataUrl = result.dataURL;
		            
		            var groupByField = result.groupByField;
					groupBy = (result.isGroupBy == "true" ? true: false);
					var rownumbers = (groupBy == true ? false : (result.isRowSumbers =="false"?true:false));
					groupSummary = (groupBy == true ? result.groupSummary == "true" ?true : false : false);
		            if( result.tableCaption != null && result.tableCaption != "")
		            	tableCaption = result.tableCaption;
		            
		            for(var i = 0; i < colM.length;i++ ){
		               	
			           	if( colM[i].formatter != null && colM[i].formatter.length > 0){
		             		var functObj = eval('(' + colM[i].formatter + ')');
		             		colM[i]["formatter"] = functObj ;
		             	}
		             	if(colM[i].cellattr != null && colM[i].cellattr.length > 0){
		             		var cellattrObj = eval('( function (rowId, tv, rawObject, cm, rdata){ return ' + colM[i].cellattr + ';})');
		             		colM[i]["cellattr"] = cellattrObj ;
		             	}
		            }
		            jQuery("#"+tableId).GridUnload();
		            mygrid =  jQuery("#"+tableId);
		            jQuery("#"+tableId).jqGrid({
				             		url:getDataUrl,
								    datatype: 'json',
								    colNames: colHeaders,
									colModel: colM,
									rowNum:100,
									rowList:[100,200,300],
									rownumbers: rownumbers,
									shrinkToFit:false,
									pager: pagerId, 
									sortname: 'id',
									viewrecords: true,
									sortorder: "asc",
									scroll:1,
									scrollrows:true,
									loadonce:loadOnce,
									cellEdit: cellEdit, 
									cellsubmit: cellSubmit,
									//caption:tableCaption,
									toolbar: (tableButton == true ? [true,"top"]:''),
									toppager:tableButton ,
									width: tableWidth ? tableWidth: window.innerWidth-200,
									height:tableHeight?tableHeight:'80%',
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
										if( onloadcompletecallback != null && onloadcompletecallback.length >0  )
										{																			
											eval(onloadcompletecallback+'( )');
										}	
										
									},
									 onSelectRow: function(id){    //Added by Siddharth.A
											if( selectRowFunction != null && selectRowFunction.length > 0 )
												eval(selectRowFunction+'(id)');
									 }
							});
		            
		            setGridBackgroundColor(tableId);
		           // jQuery("#gbox_" + tableId).css("left","-2%");
		            if( tableButton == true){
			            var tableString="<div id='left'><table  style='float:left;table-layout:auto; cellspacing='0' cellpadding='0' border='0' >";
			            tableString+="<tbody> <tr><td id='toolbar'>";
						tableString+="<div style='padding-left:2px;padding-top:1px;'>";
						tableString+="<div class='grid_icon' id='fil' >FILTER</div>";
						tableString+="<div class='grid_icon' id='ref' >REFRESH</div  >";
						tableString+="<div class='grid_icon' id='srch'>SEARCH</div>";
						tableString+="<div class='grid_icon' id='clr' >CLEAR</div  >";
						tableString+="<div class='grid_icon' id='del' >DELETE</div  >";
						tableString+="</td></tr></tbody></table></div>";
						
						
						var tableString1="<div id='rght' style='align:left'><table  style='table-layout:auto; cellspacing='0' cellpadding='0' border='0'>";
				        tableString1+="<tbody> <tr><td id='toolbar'>";
						tableString1+="<div style='padding-top:0px;'>";
						tableString1+="<div class='grid_icon' id='grph' >GRAPH</div>";
						tableString1+="<div class='grid_icon' id='' style='padding-top:4px;margin-right:12px;font-size:10px;color:#fff;font-family: Arial Rounded MT Bold;font-weight:bolder;'>EXPORT TO</div>";
						tableString1+="<span class='grid_icon' id='' style='border : inset 2px #000;' >";
						//alert(tableId);
						tableString1+="<div class='grid_icon' id='exptxl' > EXCEL</div>";
						tableString1+="<div class='grid_icon' id='exptpdf'> PDF</div>";
						tableString1+="<div class='grid_icon' id='exptppt'>PPT</div>";
						tableString1+="</span >";
						
						tableString1+="<div class='grid_icon' id='btnJqGridBack' >BACK</div>";
						
						
						tableString1+="</td></tr></tbody></table></div>";
						  
			            
			           /* jQuery("#t_"+tableId).css("padding-bottom","1%");
			            jQuery("#t_"+tableId).css("background-color","#548DE9");
			            jQuery("#t_"+tableId).css("border","outset 1px #5790EC");
			            jQuery("#t_"+tableId).append(tableString); */
						//jQuery("#"+tableId+"_toppager_left").css("padding-bottom","1%");
				        jQuery("#"+tableId+"_toppager_left").css("background-color","#548DE9");
				        //jQuery("#"+tableId+"_toppager_left").css("border","outset 1px #5790EC");
						jQuery("#"+tableId+"_toppager_left").append(tableString); 
						  jQuery("#"+tableId+"_toppager_right").css("background-color","#548DE9");
						 jQuery("#"+tableId+"_toppager_center").css("background-color","#C7CEFD");
						 jQuery("#"+tableId+"_toppager_center").css("border","ridge 1px #000");
						 jQuery("#"+tableId+"_toppager_center").css("height","10px");
						jQuery("#"+tableId+"_toppager_right").html(tableString1); 
		            }
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
					
		         /*jQuery("#"+tableId).jqGrid('navGrid',tableString,{edit:false,add:false,del:false})
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
		            jQuery("#"+tableId).jqGrid('bindKeys',{scrollingRows:true } );
		            
		            jQuery('#exptxl').click(function (){
		            	exportExcel(tableId);
		            });

		            jQuery('#btnJqGridBack').click(function (){
	            		
		            	 try{
		            			window[tableId+"_onProcessGridBack"].apply(this,null);
		            			
		            		}catch(Exception ){
		            			
		            		}
		            });	
            		//var headers = mygrid[0].grid.headers;
            		columnHeaderSpan(colM,colN);
	            	
		        },
		        error: function(status){
			  	}
		});
	
	  jQuery("#preLoadContent").hide(); 
	  jQuery("#LoadContent").show();

}


function cellObject(){
	this.rowspan=1;
	this.colspan=1;
	this.caption=" ";
}

function columnHeaderSpan(colModel,colHeaders){
	
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

			for(  j = 0 ; j< colHeaders[i].length-1;j++){
				if( ! colModel[j].hidden )
				{	
					if( colHeaders[i][j ] != colHeaders[i][j+1] )
					{
						var celllObject = new cellObject();
						celllObject.colspan =colSpan;
						celllObject.rowspan=1;
						celllObject.caption = colHeaders[i][j ]; 
						colSpan=1;
						rowHeaders[rowIndx][colIndex++ ] = celllObject;
					}
					else{
						++colSpan;
					}
				}	
			}
		/*	if( colHeaders[i][j ] == colHeaders[i][j-1] && i == 1)
				colSpan--;
		*/		
			var celllObject = new cellObject();
			celllObject.colspan =colSpan;
			celllObject.caption = colHeaders[i][j-1 ]; 
			rowHeaders[rowIndx][colIndex++ ] = celllObject;
			rowIndx++;
			
		}
		for(var i=0;i<rowHeaders.length-1;i++)
		{
			for(var j =0;j<rowHeaders[i].length;j++)
			{
				for(var k=i+1;k<rowHeaders.length ;k++){

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
		for( var i = 0;i<rowHeaders.length;i++)
		{
			var tr = '<tr class="jqgridheaderrow'+(i+1) +'">';
			for( var j = 0;j<rowHeaders[i].length;j++)
			{
				var cellobj = rowHeaders[i][j];
				//alert(cellobj.rowspan);
				if( cellobj.rowspan > 0)
					tr += '<th class="ui-state-default ui-th-ltr"  colspan="'+ cellobj.colspan +'" rowspan="'+ cellobj.rowspan +'" role="columnheader">'+cellobj.caption +'</th>';
			}
			tr += '</tr>';
				
			mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
		}	
	}	
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
	
	var monthArray = [ "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	 jQuery('#'+id).datebox({  
		 formatter: function(date){
			 return (date.getDate()+'-'+ monthArray[date.getMonth()] +'-'+date.getFullYear()); },
		 parser:function(s){
			 if(jQuery.trim(s)==""){
				 return new Date();
				 }
			 var dt=s.split(" ");
			 var d=dt[0];
			 var td = d.split('-');
			
			 var day = new Date().getDate(),month = new Date().getMonth(),year = new Date().getFullYear();

			 if( td[0] != null && jQuery.trim(td[0]).length >0  )
			 {
				var indx = getIndex( td[1] );  
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
			
		}
		
	 });
}





function fillComboBox(formName, id, url){
	
	jQuery('#'+id).combobox({
		//mode:'remote',
		url:url,
		dataType:'json',		
		valueField:'id',
		textField:'text',
		onSelect:function(record)
		{
			var onSelectFunctionName = formName+id +'_onSelect';
			
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				//alert(onSelectFunctionName);
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
			//alert('js');
			if( typeof eval(onSelectedFunctionName) == 'function')
			{
				eval('( '+ onSelectedFunctionName +'(event, ui))');
			}
		}
		
	});
}
function reloadCombo(formId,id,url)
{
	//alert(id);
	jQuery('#'+id).combobox("reload",url);
}

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
			//alert('js');
			if( typeof eval(onSelectedFunctionName) == 'function')
			{
				eval('( '+ onSelectedFunctionName +'(event, ui))');
			}
		}
	});
}

function saveForm( FormType, formId,url)
{
	//alert(FormType);
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
	
	if (FormType=="sub") 
		jQuery("#subdispErr").html("");
	else
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
	            				jQuery('#'+validMsgs[i][0]).next("span").after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
	            			else	
	            				jQuery('#'+validMsgs[i][0]).after('<div id="err_'+validMsgs[i][0] +'" class="tpm-errormsg" ></div>');
	            		}	
	            		jQuery('#err_'+validMsgs[i][0] ).css("display","block");
	            		jQuery('#err_'+validMsgs[i][0]).html(validMsgs[i][1]);
            		}
            	}            	
            	else if( result.tpmException ){
            		alert(result.tpmException);
            		if (FormType=="sub")
            			jQuery("#subdispErr").html('<h3> ' +result.tpmException +'</h3>');
            		else
            			jQuery("#dispErr").html('<h3> ' +result.tpmException +'</h3>');
            	}	
            	else{
            		
	            	var successCallback =  formId + '_successsCallback';
	            	alert(result.successData.msg);
	            	if (FormType=="sub")
	            		jQuery("#subdispErr").html('<h3> ' +result.successData.msg +'</h3>');
	            	else
	            		jQuery("#dispErr").html('<h3> ' +result.successData.msg +'</h3>');
	            	
	            	alert(successCallback);
	            	if( typeof eval('(' + successCallback  +')') == 'function')
	            		eval('(' + successCallback  +'(result))');

            	}
            		
            },  
            error: function(msg){
            	if (FormType=="sub")
            		jQuery("#subdispErr").html(msg.responseText);
            	else
            		jQuery("#dispErr").html(msg.responseText);
            	//alert(msg.tpmException);
            	//alert(Object.keys(msg));
            	//alert(" e " + msg.exception);
            	
            	var errCallback =  formId + '_errorCallback';
            	alert( errCallback +'(msg))');
            	if( typeof eval('(' + errCallback +')') == 'function')
            		eval('(' + errCallback +'(msg))');
            	
            	  
            }  
        });  
}



function deleteRecord(formId,url)
{
	 
	if( url.indexOf('_input') > 0 )
		url = url.replace('_input','_delete');
	else if(url.indexOf('_modify') > 0 )
		url = url.replace('_modify','_delete');
	//else if(url.indexOf('_modify') > 0 )
	//else
	//	return false;
	var retVal = '' ;

	try{
		var beforeSubmit = eval( formId+"_beforeDelete" );
	 
		if( jQuery.isFunction(beforeSubmit)){
			retVal = window[formId+"_beforeDelete"].apply(this,null);
		}
	}catch(Exception ){
		
	}
	

	 jQuery.ajax({  
            type: "POST",  
            url: url,  
            data: jQuery('#'+formId).serialize()+'&'+retVal,  
            dataType: "json",  
            success: function(result){  
            	if( result.tpmException ){
            		jQuery("#dispErr").html('<h3> ' +result.tpmException +'</h3>');
            	}	
            	else{
            		
	            	var successCallback =  formId + '_deleteSuccessCallback';
	            	//alert(successCallback);
	            	if( typeof eval('(' + successCallback  +')') == 'function')
	            		eval('(' + successCallback  +'(result))');

            	}
            		
            },  
            error: function(msg){
            	jQuery("#dispErr").html(msg.responseText);
            	
            	
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
}

function pushFormNavigationDetails(url,formheader)
{
	var formNavig = new formNavigation();
	formNavig.URL = url;
	formNavig.caption = formheader;
//	alert(url);
	jQuery("#hiddenUrl").val(url);
	setFormMainHeader(formheader);
	var prevNavigObj = null;
	if( formNavigations != null)
		prevNavigObj = formNavigations.pop(); 
	var divId = (prevNavigObj != null ? prevNavigObj.divId:"") ;

	divId = (divId != "" ?divId.replace("LoadContent_",""):0);
	divId = parseInt(divId) +1;
	formNavig.divId = 'LoadContent_' + divId; 
	if( formNavigations == null)
		formNavigations = [];
	
	if( prevNavigObj != ""  )
		formNavigations.push(prevNavigObj);
	
	formNavigations.push(formNavig);
}

function refreshForm()
{

	if( formNavigations != null && formNavigations.length > 0){
		var formNavig = formNavigations.pop();
		var divId =  formNavig.divId;

	//	divId = divId.replace("LoadContent_","");
	//	var oldDivId = divId;
	//	divId = divId + 1;
		
	//	var newDivId = 'LoadContent_' + divId;
	//	jQuery('#LoadContent').append('<div id="'+ newDivId +'" </div> ');
	//	oldDivId = 'LoadContent_' + oldDivId ;
		var url = formNavig.URL;
//alert("refresh url"+url);
	//	jQuery('#'+oldDivId).hide();
		formNavigations.push(formNavig);
		LoadForm(divId, "preLoadContent",url,"dispErr","refresh_SuccessCalBack","refresh_ErrorCalBack");
	}
	
}
function refresh_ErrorCalBack(msg)
{
/*	var divId = formNavigations[ formNavigations.length -1 ].divId ;
	var divId = 'LoadContent_' + (formNavigations.length+1);
	var oldDivId = 'LoadContent_' + (formNavigations.length);
	//alert(oldDivId);
	jQuery('#'+oldDivId).show();
	jQuery('#'+divId).remove();
*/	
}

function refresh_SuccessCalBack(msg)
{
/*	var oldDivId = 'LoadContent_' + (formNavigations.length);
	jQuery('#'+oldDivId).remove();
	//alert('er ' + oldDivId);
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
	alert(url);
	LoadForm(divId,"preLoadContent",url,"dispErr","navigateToNext_SuccessCalBack","navigateToNext_ErrorCalBack");
}

function navigateToNext_ErrorCalBack(result){
	alert(result);
}
*/
 
function navigateToNextForm(url,formheader,forwardData,persistentData)
{
	var formNavig = null;
	if( formNavigations != null ){
		
		formNavig =  formNavigations.pop();
	}

	var divNumber =  (formNavig  != null ? formNavig.divId :"");
	divNumber = parseInt( (divNumber != "" ? divNumber.replace("LoadContent_",""):0)) +1;
	var divId = 'LoadContent_' + (divNumber);
	jQuery('#LoadContent').append('<div id="'+ divId +'" </div> ');
	if( persistentData != null)
	{
		var pData=""; 
		//alert(pData);
		//pData += convertJSONToDataString(persistentData);
		//url += pData;
		//var pData= "";
		var names = Object.keys(persistentData);
		for(var i=0;i<names.length;i++ )
			pData += names[i] + '='+persistentData[names[i]] +'&';
		formNavig.URL +=  (formNavig.URL.indexOf('?') > -1 ? "": '?') + pData;
	}	
	if( forwardData != null)
	{
		var fData = "";
		//fData += convertJSONToDataString(forwardData);
		
		var names = Object.keys(forwardData);
		for(var i=0;i<names.length;i++ )
			fData += names[i] + '='+forwardData[names[i]] +'&';

		url += (url.indexOf('?') > -1 ? "":'?') +fData;
		//alert(' url ' + url);
	}	

	
	var prevDivNum =parseInt(divNumber)-1;
	jQuery('#LoadContent_'+ prevDivNum).hide();
	jQuery('#LoadContent_'+prevDivNum).html(""); // if page is loading from server when going backward 
	
	if( formNavig != null)
		formNavigations.push(formNavig);
	
	pushFormNavigationDetails(url,formheader);
	LoadForm(divId,"preLoadContent",url,"dispErr","navigateToNext_SuccessCalBack","navigateToNext_ErrorCalBack");
}

function convertJSONToDataString(persistentData)
{
	var pData= "";
	var names = Object.keys(persistentData);
	for(var i=0;i<names.length;i++ )
		pData += names[i] + '='+persistentData[names[i]] +'&';
	return pData;
}
function navigateToNext_SuccessCalBack(msg){
//	alert(formNavigations.length);
//	alert(formNavigations[ formNavigations.length-1].URL);
}
function navigateToNext_ErrorCalBack(msg){
	if( formNavigations != null && formNavigations.length > 1 )
		navigateToPrevForm();
}
function navigateToPrev_SuccessCalBack(msg)
{
	var removeForm = formNavigations.pop();

	jQuery('#'+removeForm.divId).remove();
	var prevForm = formNavigations.pop();
	jQuery('#'+prevForm.divId).show();
	formNavigations.push(prevForm);
}
function navigateToPrev_ErrorCalBack(msg){
	var prevForm = formNavigations.pop();
	var curentForm = formNavigations.pop();
	formNavigations.push( curentForm);
	formNavigations.push( prevForm);
	jQuery('#'+prevForm.divId).show();
}

function navigateToPrevForm()
{
	var curentForm = formNavigations.pop();
	jQuery('#'+curentForm.divId).hide();
	//jQuery('#'+curentForm.divId).remove();

	var prevForm = formNavigations.pop();
	//jQuery('#'+prevForm.divId).show();
	jQuery("#hiddenUrl").val(prevForm.URL);

	setFormMainHeader(prevForm.caption);
	formNavigations.push(prevForm);
	formNavigations.push(curentForm);

	LoadForm(prevForm.divId,"preLoadContent",prevForm.URL,"dispErr","navigateToPrev_SuccessCalBack","navigateToPrev_ErrorCalBack");
}

function processTree(treeId,treeAction)
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
									"elementType" : n.attr ? n.attr("elementType") : 0
								};
                            }
                           
                        },                 
		 "progressive_render" : true
            },
         "themes" : {
                    "theme" : "classic",
                    "dots" : true,
                    "icons" : true                            
                },
                
    	 "plugins" : [ "themes", "json_data", "ui","hotkeys","contextmenu"],
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
					//alert('Wrong');
					return false;
				}
				//else
				//	alert(file);
			},
			dataType: "json",
			onComplete: function(file, response) {
				afterComplete(response);
			
			}
		});
}
*/
function imageUpload(dlgId,imgAction,imgName,bindImgId)
{
	//alert(dlgId);
	 new AjaxUpload(dlgId, {
			action: imgAction,
			name: imgName,
			onSubmit: function(file, extension) {
				if (!(extension && /^(jpg|png|jpeg|gif)$/i.test(extension))){
					//alert('Wrong');
					return false;
				}
			},
			dataType: "json",
			onComplete: function(file, response) {
				var funName = imgName+"OnComplete";
				response = response.replace("<pre>","").replace("</pre>","");
				jQuery('#'+bindImgId).attr('src', response);
				if( typeof funName == 'fuction' )
					eval( funName +'(response)' );
			
			}
		});
}

function setImgWidth(imgId,w,h)
{
	 imgId.removeAttr("width"); 
	 imgId.removeAttr("height");
     var imgWidth = imgId.width();
     var imgHeight = imgId.height();
	 if(imgWidth < w && imgHeight < h)
	 {
		 imgId.attr('width', imgWidth);
	  	 imgId.attr('height', imgHeight);
	 }
	 else
	 {
		 imgId.attr('width', w);
	  	 imgId.attr('height', h);
	 }
}


function numericTextBox(id)
{
	jQuery('#'+id).keydown(function(event) {
	  if( !(event.keyCode == 8                                // backspace
		        || event.keyCode == 46                              // delete
		        || (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
		        || (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
		        || (event.keyCode >= 96 && event.keyCode <= 105))   // number on keypad
		        ) {
		            event.preventDefault();     // Prevent character input
		    }
	});

}


function enableTextBoxInComboSelect(comboId,textBoxId,disable,enable)
{
	

	jQuery("#"+comboId).combobox({
		onSelect:function(recordid){
			//alert(recordid.value);
				 if(recordid.value == disable)
					 {
					 
					 	jQuery("#"+textBoxId).val("");		
					 	jQuery("#"+textBoxId).attr('readonly','readonly');
					 	jQuery("#"+textBoxId).css('background-color', '#D1E2FD');

						
					 }
				  if(recordid.value == enable)
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
	//alert(mon[i]);
	return mon[i];
	
}
/*
 *  
 * ---------
 */
function readOnlyFields(fieldId)
{
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("disable");
	else
		jQuery("#"+fieldId).attr('readonly','readonly'); 	
}
function readOnlyFieldsWithText(fieldId,displayTxt)
{
	jQuery("#"+fieldId).val(displayTxt);
	jQuery("#"+fieldId).attr('readonly','readonly');
 	jQuery("#"+fieldId).css('background-color', '#D1E2FD');
}
function fillWithCurrentDate(fieldId)
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
		
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',day+'-'+month+'-'+year);		
	
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).val(hours + ":" + minutes);
	else
		jQuery("#"+fieldId).val(day+'-'+month+'-'+year);
}

function showCommonErrorMsg(msg)
{
	jQuery("#dispErr").html('<h3> ' +msg +'</h3>');
}

function multiSelectPop(dataUrl,condition,gridId,rowId,colNames,isMultiselect,multiSelectCancel_Callback,multiSelectOk_Callback){
	jQuery( "#multiselectPopUpId" ).show();
	jQuery( "#multiselectPopUpId" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 510,
			width: 550,
			left:20,
			top:90,
			modal: true
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

function subFormPop(mainFormUrl, subFormUrl,subFormLeft, subFormTop,subFormHeight, subFormWidth, formName,controls)
{
	
	jQuery( "#subformPopUpId" ).show();
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
	//alert(jQuery("#subFormPopUpId").attr('panel-title',formName));
	alert("controls"+controls);
	alert("subformurl"+subFormUrl);
	var url = "subForm_input.mselect?" + 'mainFormUrl='+mainFormUrl+'&subFormUrl='+subFormUrl+'&controls='+ controls ;
	LoadForm("loadSubFormPopUp","preloadSubForm",url,"subdisErr","subFormpop_successCallback");
}

function subFormpop_successCallback(response){
	if( response.dataNotExist  ){
		showCommonErrorMsg(response.dataNotFoundMsg);
		jQuery( "#subformPopUpId" ).dialog("close");
	}		
}
/*
 * 
 */

function JqGridToJsonSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		
		if( value != null  &&  value.trim()  != ""){
			
			jsonArrO += '{';
			
			for(var colName in row) {
				
				if( checkBoxColName != colName )
					jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
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
	alert(jsonArrO);
	return jsonArrO; 
}
*/
function convertJqGridToJSONStringArr(jqGridId){
	
	var rowIds = jQuery("#"+jqGridId).jqGrid('getDataIDs');
	//alert(rowIds[0]);
	var rowObject = jQuery("#"+jqGridId).getRowData(rowIds[0]);
	var totalCol = 0;
	for(var col in rowObject) totalCol++;
	
	var jsonArrO='[';
	//alert("totalCol " + totalCol);
	for( var i = 0; i < rowIds.length;i++){
		jsonArrO += '[';
		
		for(var j = 0; j<totalCol;j++) {
			//alert(rowIds[i]);
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

function readOnlyFields(fieldId)
{
	//alert(fieldId);
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("disable");
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("disable");
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner("disable");
	else
		jQuery("#"+fieldId).attr('readonly','readonly'); 	
}
function enableFields(fieldId)
{
	if(fieldId.substring(0,3) == "cmb")
		jQuery("#"+fieldId).combobox("enable");
	else if(fieldId.substring(0,3) == "dte")
		jQuery("#"+fieldId).datebox("enable");
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).spinner("enable");
	else
		jQuery("#"+fieldId).attr('readonly',false); 	
}

function getFieldValue(fieldId)
{
	var val =null;
	if(fieldId.substring(0,3) == "cmb")
		val = jQuery("#"+fieldId).combobox("getValue");
	else if(fieldId.substring(0,3) == "dte")
		val = jQuery("#"+fieldId).datebox("getValue");
	else
		val = jQuery("#"+fieldId).val();
	
	return val;
}

function displayText(fieldId,displayTxt)
{
	jQuery("#"+fieldId).val(displayTxt);
}

function fillWithCurrentDate(fieldId)
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
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',day+'-'+month+'-'+year);		
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).val(hours + ":" + minutes);
	else
		jQuery("#"+fieldId).val(day+'-'+month+'-'+year);
}
function getMonthStringFromInt(i)
{
	var mon=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
	return mon[i];	
}

function hasSpecialCharacters(str) {
		//alert(1);
	  const regex = /[^a-zA-Z0-9 _.\-()/%]/; 
	  return regex.test(str);
	}

function convertStringToDate(dateTimeStr)
{
	dateTimeStr = dateTimeStr.substring(1,2) == "-"? "0"+dateTimeStr:dateTimeStr;
	
	var day=dateTimeStr.substring(0,2);
	var month=dateTimeStr.substring(3,6);
	var year=dateTimeStr.substring(7,11);
	var hour=dateTimeStr.substring(11,13);
	var min=dateTimeStr.substring(14,16);
	
	month=changeFormatStringtoNumber(month);	
	
	var d=new Date(year,month,day,hour,min);
	return d;	
}

function compareDateTime(fromDateTimeStr,toDateTimeStr)
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

/* Created By Suresh on 05-Dec-2011 
 * Modified By Karthick on 07-Dec-2011 */
function getTimeDifference(earlierDate,laterDate)
{	   
	   var nTotalDiff = Math.abs(laterDate.getTime() - earlierDate.getTime());   
	  
       var oDiff = new Object();       
       oDiff.seconds = nTotalDiff/1000;
       oDiff.minutes = nTotalDiff/60000;
       oDiff.hours = nTotalDiff/3600000;
       oDiff.days = 1000*60*60*24;       
       oDiff.convtDays = Math.round(nTotalDiff/oDiff.days);      
       
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
	if(monthval=="Jan"){
	monthval=0;
	}
	else if (monthval=="Feb") {
	monthval=1;
	}
	else if (monthval=="Mar") {
	monthval=2;
	}
	else if (monthval=="Apr") {
	monthval=3;
	}
	else if (monthval=="May") {
	monthval=4;
	}
	else if (monthval=="Jun") {
	monthval=5;
	}
	else if (monthval=="Jul") {
	monthval=6;
	}
	else if (monthval=="Aug") {
	monthval=7;
	}
	else if (monthval=="Sep") {
	monthval=8;
	}
	else if (monthval=="Oct") {
	monthval=9;
	}
	else if (monthval=="Nov") {
	monthval=10;
	}
	else if (monthval=="Dec") {
	monthval=11;
	}
	return(monthval);
}

function showCommonErrorMsg(msg)
{
	jQuery("#dispErr").html('<h3> ' +msg +'</h3>');
}
function clearValidationErrorMsg(controlId)
{
	jQuery("#err_"+controlId).hide();
}

function spinnerEvents(spinnerId,callBackFunc)
{
	jQuery("#"+spinnerId).change(function(){
		//var onSelectFunctionName = id +'_onSelect';
		var d =new Date();
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

function fillSectionHierarchy(url,cmbSectionId,cmbFactId,cmbCompId){
	
	
	glbcmbFactId=cmbFactId;
	processAjaxCalls(url,'sectionId='+cmbSectionId,'sectionHierarchy_OnSuccess','sectionHierarchy_OnError');
}

function sectionHierarchy_OnSuccess(result)
{	
	jQuery("#"+glbcmbFactId).combobox('setValue',result.sectionHierarchy.factory);	
}

function cellHierarchy_OnError(result){
	alert("error");
}

//---------------------cell Hierarchy--------------------//

function fillCellHierarchy(url,cmbCellId,cmbSectionId,cmbFactId,cmbcompId){
	
	glbcmbSectionId=cmbSectionId;
	glbcmbFactId=cmbFactId;
	processAjaxCalls(url,'cellId='+cmbCellId,'cellHierarchy_OnSuccess','cellHierarchy_OnError');
}

function cellHierarchy_OnSuccess(result)
{
	
	jQuery("#"+glbcmbFactId).combobox('setValue',result.cellHierarchy.factory);
	jQuery("#"+glbcmbSectionId).combobox('setValue',result.cellHierarchy.section);
	
}

function cellHierarchy_OnError(result){
	alert("error");
}


/*-----------
 * 
 */
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

function initialiseForm(formId){
	
	jQuery.noConflict();
	var inputs = jQuery('#' + formId + ' :input');

	var controlId ;
	jQuery(inputs).each(function () {
		controlId = this.id;
		if( controlId.substring(0,3) == "cmb")
		{
			initialiseComboBox(controlId);
		}	
		
	});
	var divs = jQuery('#' + formId + ' div[id^="tab"]');
	jQuery(divs).each(function () {
		 var Id = this.id;
		jQuery("#"+Id).tabs();
	});	
}

function initialiseComboBox(comboboxId){
	jQuery('#'+comboboxId).combobox({  
	    url:'',  
	    valueField:'id',  
	    textField:'text'  
	}); 
}

function clearForm(id)
{
	jQuery(':input','#'+id)
	.not(':button, :submit, :reset, :hidden')
	.val('')
	.removeAttr('checked')
	.removeAttr('selected');
}

function ToolsTree(dataUrl,condition,gridId,rowId,colNames,isMultiselect){
	//alert("dataUrl  "+dataUrl);
	jQuery( "#multiselectPopUpId" ).show();
	jQuery( "#multiselectPopUpId" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
			height: 500,
			width: 550,
			left:30,
			top:50,
			modal: true
	});
	var url = "toolpop_input.mselect?" + 'dataUrl='+dataUrl+'&condition='+ condition +'&gridId='+gridId +'&rowId='+rowId +'&colNames='+colNames +'&isMultiselect='+isMultiselect;
	LoadForm("loadMultiSelectPopUp","preloadMultiSelect",url,"dispErr","toolpop_successCallback");
}
function toolpop_successCallback(response){
	//alert("sucess");
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
