<style>
.bord{
border:solid 1px #000;
padding:3px;
background-color:#fff;
border-right-color: #fff;
font-size:11px;
}
.tblCls{
margin-left:7%;

background-color:#fff;
margin-top:5px;
}
.chartDivst{
/*height:60%;
width:85%;
margin-top:2%;
margin-left:7%;*/

border:ridge 1px #C1C1C1;
}
</style>
<script> 
var glbchartdata=null;
var glbchartdiv=null;;
jQuery.noConflict();
jQuery(document).ready(function()
{	
	 initialiseForm('frmKpiCharts'); 	
	var indiID    = jQuery('#indicatrId').val();
	//alert('indiID'+indiID);
	var indiSplit = indiID.split(',');
//	alert('indiSplit'+indiSplit);
	var indiName  = jQuery('#indicatrName').val();
	var nameSplit = indiName.split(',');
	
	var indiRptType  = jQuery('#indicatrRptType').val();

 
  
	indiMonthYear  = jQuery('#indicatrMonthYear').val();
	  
	 indicatrMonth=jQuery('#indicatrMonth').val();
	 
	var indiMonthYear  = jQuery('#indicatrMonthYear').val();
	var calyr=jQuery("#indicatrcalyr").val();
	var monthid=jQuery("#hdnmonth").val();
	
	//alert("calyr"+calyr);
	//alert("indiMonthYear"+indiMonthYear);
	

	for(var i =0;i<indiSplit.length;i++){
		var content='<div id ="graph_'+ i +'" class="chartDivst"></div><div id ="dataTable_'+ i +'"></div><input type="hidden" id="keyid_'+ i +'" value="'+ indiSplit[i] +'"/>';
		
		var spn = '<span class="">';
		var spnend = '</span>';
		
		if(indiSplit.length > 0){
			jQuery('#indicatrs').prepend("<option value="+i+"> [ I-"+i+" ]  "+nameSplit[i]+"</option>");
			//alert("nameSplit[i]:::"+nameSplit[i]);
			jQuery('#tabKpiChart').tabs('add',{
				title:"I-"+spn+i+spnend,
				id:nameSplit[i],
				content:content
				
			});
			
			jQuery('.tabs-title').css('width','60');
		}
	}

	jQuery('.indexval').css('color','#CBC9C1');
	if(jQuery('.tabs-selected').attr('class')== 'tabs-selected'){

    //alert("inside the tab selected");
		jQuery('.selectedIndi').css('color','#F6F4EC');
		//alert(jQuery("#indicatrId").val(indiSplit[i]));
		
	}
	else{

    //   alert("alert outsid ethe else");
		jQuery('.indexval').removeClass('selectedIndi');
		jQuery('.indexval').css('color','#CBC9C1');
		//jQuery("#indicatrId").val(indiSplit[i]);
	}
	
	
	jQuery('#tabKpiChart').tabs('select', nameSplit[0].substring(0,8)+"...."+spn+i+spnend);

	//	alert(12345);
		for(var i =0;i<indiSplit.length;i++){
			var id=jQuery('#keyid_'+i).val();
		//	alert("alert insid ein for loop");
			//alert(jQuery("#indicatrId").val(id));
				var grapDiv = jQuery('#graph_'+i).html();
				var monthid=jQuery("#hdnmonth").val();
				
				
			//	alert("grapDiv"+grapDiv);
				if(grapDiv == ' ' || grapDiv == '' || grapDiv == null){
					var args="";
					if(indiRptType=="D"){args="&rptType="+indiRptType+"&monthYear="+indiMonthYear;}	else{args="&rptType="+indiRptType+"&monthid="+monthid;}			
					
					processAjaxCalls("kpiIndicator_chart.kpiActKk?","&idicatorId="+id+args+"&chartDiv=graph_"+i,"KPI_chartSuccesscallback","","","");
				}
		}
	 jQuery("#indicatrs").change(function () {
		
         var str = "";
         var value = "";
         jQuery("select option:selected").each(function () {
               str += jQuery(this).text() + " ";
               value = jQuery("#indicatrs").val();
               
              // alert(value);
               var id=jQuery('#keyid_'+value).val();
            //   alert("id"+id);
            ///   jQuery("#indicatrId").val(id);
             //  alert(jQuery("#indicatrId").val());
               var tabs = jQuery('#tabKpiChart').tabs('tabs');  
               jQuery('#tabKpiChart').tabs('select', tabs[value].panel('options').title);
          
             });
         
       })
       .trigger('change');
	 
	 jQuery("#tabKpiChart").click(function(){
		   // alert('something');
		    var str = "";
		
		   jQuery('.indexval').removeClass('selectedIndi');
		   if(jQuery('.tabs-selected').attr('class')== 'tabs-selected'){
			 //  alert("inside the if");
			  // var inderex=jQuery('.indexval').text();
			//jQuery('.selectedIndi').text();
			  // alert(jQuery('.tabs-selected').attr('class'));
			   var index=jQuery('.tabs-selected').text();
			   
			 
		/* 	   alert("inderex::"+inderex);
			   alert("selectindi::"+selectindi);
		  var index=jQuery('.tabs-title').text();
		   alert(index); */
		  var indi= index[index.length -1];
		  
		   var arrVars = index.split("-");
			var id = arrVars.pop();
		//	alert("rowid"+id);
		   //alert(indi);

		   }
		    value = jQuery("#indicatrs").val();
		    var id=jQuery('#keyid_'+id).val();
           // alert("id"+id);
          //  jQuery("#indicatrId").val(id);
           
          /*  if(glbchartdata!=null&&glbchartdiv!=null)
            	{
            	glbchartdata.width = '1091';
            	glbchartdata.height = '300';
        	glbchartdata=result.chartData;
        	glbchartdiv=result.result.chartDiv;
        	
        	var indicatorid=jQuery("#indicatrId").val();
        	var rpttype=jQuery("#indicatrMonthYear").val();
        	var yr=jQuery("#indicatrMonthYear").val();
        	var params="&indiid="+indicatorid+"&type="+rpttype+"&yr="+yr;
        	//sendChartToExcelForKPI("3","_loadExcFormat_KPI","Y","&indiid="+indicatorid+"&type="+rpttype+"&yr="+yr);	
        	drawChartKPI(glbchartdata,glbchartdiv,'Y','Y','Y','Y','',params);	
        	jQuery('.dashToolBar').css('margin-top','0');
            	}*/
            	
           // processAjaxCalls("K?","&idicatorId="+jQuery("#indicatrId").val(id),"KPI_chartSuccesscallbacknew","","","");
		});


	    var btnName = jQuery("#hdnBtnName").val();
		//alert("btnName"+btnName);
		jQuery("#btnViewTemplate").val(btnName);
		jQuery("#btnViewTemplate").click(function(){
			
			processAjaxCalls("openFile.file?fileName= BMINDICATORS_KK.xls", "", "", "", "", "viewTemplate");		

		});
	     

		jQuery("#imgPrintChart_KPI").click(function(){		
			 PrintDivData('graphContainer_KPI');
		});
	
		
		jQuery("#imgExportChart_KPI").click(function(){	
			jQuery.noConflict();
            // alert(567);
			jQuery("#hdnChartJsonData_loadExcFormat_KPI").val( getAllGraphData("hdnChartJsonData_graph"));

			//sendChartToExcel("3","_loadExcFormat_KPI","Y","KPI_charts");	
			/** Sugumar Chanegd For KPI-14Apr2016**/
			//alert(123);
			var indicatorid=jQuery("#indicatrId").val();
			var rpttype=jQuery("#indicatrMonthYear").val();
			var yr=jQuery("#indicatrMonthYear").val();
			sendChartToExcelForKPI("3","_loadExcFormat_KPI","Y","&indiid="+indicatorid+"&type="+rpttype+"&yr="+yr);	
			/***************************************************/
			/*if( jQuery('#loadExcFormat_KPI').parent('div').parent('div').attr("id")=='grapContainerZoom' ){
				//jQuery('#loadExcFormat_KPI').css('margin-left','200');
				//jQuery('#loadExcFormat_KPI').css('margin-top','36');
				jQuery("#loadExcFormat_KPI").slideToggle(200);
			}
			else{
				
				//jQuery('#loadExcFormat_KPI').css({'margin-left':'-140','margin-top':'18'});
				jQuery("#loadExcFormat_KPI").slideToggle(200);
			}
			
			
			var loadHtml = "<div style='padding-bottom:1px;'><a href='#' id='Excel3_KPI' name='Excel3_KPI'> Send To Excel 2003</a></div>";
				loadHtml += "<div><a href='#' id='Excel7_KPI' name='Excel7_KPI' style='padding-top:5px;'> Send To Excel 2007</a></div>";
			jQuery("#loadFormat_KPI").html(loadHtml);
			jQuery( '#Excel3_KPI').click(function (){
				sendChartToExcel("3","_loadExcFormat_KPI","Y","KPI_charts");
			});
			jQuery( '#Excel7_KPI').click(function (){
				sendChartToExcel("7","_loadExcFormat_KPI","Y","KPI_charts");
			});	*/	
		});
});

/* function KPI_chartSuccesscallback(result)
{
	
	result.chartData.width = '1091';
	result.chartData.height = '300';
	var indiRptType  = jQuery('#indicatrRptType').val();
	
	//if(indiRptType=='DM')
		//drawChart(result.chartData,result.chartDiv,'Y','Y','Y','Y','');
    //	 else
	// drawChart(result.chartData,result.chartDiv,'Y','Y','Y','Y','');	

	
	var indiRptType  = jQuery('#indicatrRptType').val();
	var yr=jQuery("#indicatrMonthYear").val();
	var calyr=jQuery("#indicatrcalyr").val();
	
	var monthid=jQuery("#hdnmonth").val();
	
	// drawChart(result.chartData,result.chartDiv,'Y','Y','Y','Y','');	
   

    var actvalue=null;
  
   // if(indiRptType!='DM'){
    var semicolon=result.KPIActual.contains(';');
  
    if(semicolon!='-1')
    	{
    	 var value=result.KPIActual.split(";");
    	 actvalue= value[0];
    	}
    else{
    	actvalue="0";
    }
 //   }
   
/*   if(result.freq.trim()=="D")
	  {
	  calyr=yr;
	  }
  else if(result.freq.trim()=="M")
	  {
	  calyr=year;
	  
	  }
    */
	

	//glbchartdiv=result.result.chartDiv;
	

	/*var indicatorid=jQuery("#indicatrId").val();
	var rpttype=jQuery("#indicatrMonthYear").val();
	var yr=jQuery("#indicatrMonthYear").val();
	var calyr=jQuery("#indicatrcalyr").val();
	
	if(indiRptType=='D'){
		
		//alert("new chk");
	
	yr=yr.substring(4,8);
	calyr=yr;
	}
   if(indiRptType=='DM'){
	//   actvalue="0";
		//alert("new chk");
	
	monthid=monthid.substring(4,8);
	calyr=monthid;
	}
	
	var params="&indiid="+indicatorid+"&type="+rpttype+"&yr="+yr;
	
	//sendChartToExcelForKPI("3","_loadExcFormat_KPI","Y","&indiid="+indicatorid+"&type="+rpttype+"&yr="+yr);	
   //
	// alert("result.chartData"+result.chartData);
//	alert("result.chartData"+result.chartDiv);
   // drawChart(result.chartData,result.chartDiv,'Y','Y','Y','Y','');	  

	//drawChartKPI(result.chartData,result.chartDiv,'Y','Y','Y','Y','');	
	
	drawChartKPI(result.chartData,result.chartDiv,'Y','Y','Y','Y','',result.indiid,calyr,result.freq,result.flid,actvalue);	
	//drawChartKPI(result.chartData,result.chartDiv,'Y','Y','Y','Y','',result.indiid,monthid,result.freq,result.flid);
	jQuery('.dashToolBar').css('margin-top','0');
}
 */
 
 
 //sriram 21-Nov-2025
 
 function KPI_chartSuccesscallback(result)
 {
     console.log("=== Callback Started ===");
     console.log("Result:", result);
     
     result.chartData.width = '1091';
     result.chartData.height = '300';
     
     var indiRptType = jQuery('#indicatrRptType').val();
     var yr = jQuery("#indicatrMonthYear").val();
     var calyr = jQuery("#indicatrcalyr").val();
     var monthid = jQuery("#hdnmonth").val();
     
     var actvalue = null;
     
     // FIX: Change contains() to indexOf()
     var hasSemicolon = result.KPIActual.indexOf(';') !== -1;
     
     console.log("Has semicolon?", hasSemicolon);
     
     if(hasSemicolon) {
         var value = result.KPIActual.split(";");
         actvalue = value[0];
     } else {
         actvalue = "0";
     }
     
     console.log("Actual value:", actvalue);
     
     var indicatorid = jQuery("#indicatrId").val();
     var rpttype = jQuery("#indicatrMonthYear").val();
     
     if(indiRptType == 'D') {
         yr = yr.substring(4, 8);
         calyr = yr;
     }
     
     if(indiRptType == 'DM') {
         monthid = monthid.substring(4, 8);
         calyr = monthid;
     }
     
     console.log("About to draw chart...");
     console.log("Chart div:", result.chartDiv);
     console.log("Element exists?", document.getElementById(result.chartDiv));
     
     // Draw the chart
     drawChartKPI(
         result.chartData,
         result.chartDiv,
         'Y', 'Y', 'Y', 'Y', '',
         result.indiid,
         calyr,
         result.freq,
         result.flid,
         actvalue
     );
     
     console.log("=== Callback Completed ===");
     
     jQuery('.dashToolBar').css('margin-top', '0');
 }
jQuery("#btnRemarks").click(function()
		{
	       var indicatrId=jQuery('#indicatrId').val();
	       var rptType=jQuery('#indicatrRptType').val();
	       var monthyear=jQuery('#indicatrMonthYear').val();
	       var dbyear=jQuery('#hdndbyear').val();
	       var kpiMonth=getFieldValue("hdnKPIMonth");
	       
			       
	       //alert("dbyear"+dbyear);
	       filterString= "&Indicator="+indicatrId+"&Frequency="+rptType+"&dbyear="+dbyear+'&kpiMonth='+kpiMonth;
	      // alert("filterString"+filterString);
	      // alert(rptType);
	    //   closePopUpDialoge("frmKpiCharts");
	       LoadPopUp("divkpiRemarksReport","kpiRemarksReport_input.kpiActKk?"+filterString,true,"500","600","100","400", "","Kpi Remarks Report");
	     //  LoadPopup("kpiRemarksReport_input.kpiActKk","","","");
		});
function getAllGraphData(idStartWith){
	var inputs = jQuery("input[id^="+idStartWith+"]");
	var postData = "[";
	jQuery(inputs).each(function () {
		postData += jQuery(this).val() +",";	
	});			
	postData = postData.slice(0, -1) +"]";
	//alert("postData:::"+postData);
	return postData; 	 	
}
/*function ParameterRpt()
{
	
	var IndicatiorName = "Production per hour";
	var Frequency = "Monthly";
	var Uom = "Nos";
	var filterString  = escape("&IndicatiorName="+ IndicatiorName+'&Frequency='+Frequency+"&Uom="+Uom);
	LoadPopUp("","kpiRemarks_input.kpiActKk?q=2"+filterString,true,"35%","50%","35%","55%","","Remarks","","",true,"setFileManagerdimension");
}*/

</script>
<form id="frmKpiCharts">
<table>
<tr>
<td>
	<div  class="" style="margin-left:75%">
		<!-- <input type="button" id="btnRemarks" name="btnRemarks" class="easyui-button" value="Remarks"  onclick="ParameterRpt();"/> -->
		
		<!-- <input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style=" width : 90px;"/> -->
	</div>
	<div  class="" style="margin-left:89%">
	
	
    </div>
</td>
<td style="padding-left:10px;">
<div id="graphheadin" class="" style="">
	<span id="selBox" style="padding-left:0px;">Indicator &nbsp;&nbsp;&nbsp;</span><select id="indicatrs" name="indicatrs" style="width:300px"></select></div>
	
	</td>
	<div style="padding: 20px;">
		<span style="padding-left:20px;"><input type="button" id="btnRemarks"  name="btnRemarks" class="easyui-button" value="Remarks"/></span>
	<td><div id="" class="" style=""> 
	<!-- <span class="tBarImg" style="">
	<img id="imgPrintChart_KPI" alt="Print"  src="images/chart_print.gif" title="Print">
	</span> -->
	<span style="" class="">
	<!-- input id="imgExportChart_KPI" type="button" class="easyui-button"  value="Export to Excel"  style="cursor:pointer;width: 100px;height:20px" /-->
	<!-- <img id="imgExportChart_KPI" title="Export to Excel"  src="images/chart_datatable.png"  >
	 --></span>
	<div>
	<div class='loadExpToExcel' id='loadExcFormat_KPI' style='height:42px ;margin-left:300px'>
		<span onclick="closeXlContiner('loadExcFormat_KPI');" style='float:right;font-weight:bold;margin-top:-10;margin-right:-12;cursor:pointer;'>X</span>
		<div class='loadExc' id='loadFormat_KPI'></div>
		<input id="hdnChartJsonData_loadExcFormat_KPI" type="hidden" />
	</div>
		</div>
	</div>
	
	</td>
	</tr>
	</table>
	<div id="populateChartDiv" style="">
	<div  id="tabKpiChart" class="easyui-tabs"  style="width:80%%;height:356px;padding-left:20px;">
	
	</div>
	</div>
	<input type="hidden" id="indicatrId" value="${requestScope.indicatorId }"/>
	
	<input type="hidden" id="indicatrName" value="${requestScope.indicatorName }"/>
	<input type="hidden"  id="indicatrRptType" value='${requestScope.rptType}'/>
	<input type="hidden"  id="indicatrMonthYear" value='${requestScope.monthYear}'/>

<input type="hidden"  id="hdnmonth" value='${requestScope.month}'/>


	<input type="hidden"  id="hdndbyear" value='${requestScope.dbyear}'/>

	<input type="hidden"  id="indicatrcalyr" value='${requestScope.calyr}'/>

	<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
</form>