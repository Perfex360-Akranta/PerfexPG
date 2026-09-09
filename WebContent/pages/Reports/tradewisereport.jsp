<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

<style>

</style>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(
			function() {
				var etmcode=jQuery('#hdnEtpmCode').val();
				alert("etmcode "+etmcode);
				var url = jQuery('#hiddenUrl').val();
				viewGrid(url,"&q=2" );
				

				jQuery('#btnLineChart').click(function(){
					
					//alert("Line Chart");
					//ShowskillIndexGraph("LINE");	
					ShowBarChart();
				});
				
				jQuery('#btnRadarGraph').click(function(){
					//alert("Radder Chart");
					ShowRadarChart();
				});
				
				jQuery ("#btnCharClose").click(function(){
							jQuery("#hdnSuccessData").val("");
							closeRadarDialog("divRadarChart");
							jQuery('#divMain').show();
				});
				
				jQuery("#imgExportRadarChart").click(function(){
					jQuery("#loadRadarExcFormat").slideToggle(200);
					var loadHtml = "<div style='padding-bottom:1px;'><a href='#' id='Excel3' name='Excel3'> Send To Excel 2003</a></div>";
						loadHtml += "<div><a href='#' id='Excel7' name='Excel7' style='padding-top:5px;'> Send To Excel 2007</a></div>";
						
					jQuery("#loadRadarExc").html(loadHtml);
					jQuery( '#Excel3').click(function (){		
						sendToExcel("3");		
					});
					jQuery( '#Excel7').click(function (){
						sendToExcel("7");
					});
					
				});
				
});
function sendToExcel(format)
{

	/*var flid = jQuery("#hdnSkillFlid").val();
	var uniPosId = jQuery("#hdnSkillUniPos").val();
	var reviewDate = jQuery("#hdnReviewDate").val();
	var reviewType = jQuery("#hdnReviewType").val();*/
	//alert("result.seriesData: " +escape(jQuery("#hdnSuccessData").val()));
	var etpmCode = jQuery('#hdnEtpmCode').val();
	//alert("etpmCode: " +etpmCode);
	var dataString =  "?q=2&dataGrid="+escape(jQuery("#hdnSuccessData").val() );
	//var reviewDate = jQuery("#hdnReviewDate").val();
	//alert(reviewDate);
	dataString += "&etpmCode="+etpmCode
	  if(jQuery("#formxlexport").length <= 0 )
	  {	  
	        var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST" > ' +
	        		   '<input type="hidden" id="fileName" name="fileName" /> '+
	        		   '<input type="hidden" id="f" name="f"  /> '+
	      	  	   '<input type="hidden" id="exporthtml" name="exporthtml"/> </form>';
	        jQuery("#LoadContent").prepend(xlFormHtml);
	        
	  }
	  jQuery("#formxlexport input[id=f]").val(format);
	 document.formxlexport.method='POST';
	
	 document.formxlexport.action="exportJhAvgRadarChart.skillIndex"+dataString;
	 document.formxlexport.target='_blank';
	 document.formxlexport.submit();
	  jQuery("div[id^=loadRadarExcFormat]").hide(200); 
}
function ShowBarChart() {	
	var etpmCode = jQuery('#hdnEtpmCode').val();	
	var flid = jQuery('#hdFlid').val();
	//alert("etpmCode: " + etpmCode);
	//alert("flid: " + flid);
	
	var dataString ="?&etpmCode=" +etpmCode;
	showGraphData("chartTradeWiseSkillIndexBarGraph.skillIndex" + dataString);
}
function closeRadarDialog(dlgId)
{
	 jQuery( '#'+dlgId ).hide();
	 jQuery('#mstfrm_div').removeClass('popup-mask');	
	 jQuery( '#'+dlgId ).removeClass('custom-popup');
}
/* function ShowRadarChart(graphType) {
    jQuery('#divMain').hide();
    jQuery('#divRadarChart').addClass('custom-popup');
    jQuery('#divRadarChart').show();
    jQuery('#divRadarChart').css('border', '1px solid #F1F5FB');
    jQuery('#divRadarChart').css('z-index', 100);

    var etpmCode = jQuery('#hdnEtpmCode').val();
    var dataString = "?&etpmCode=" + etpmCode;
    processAjaxCalls("chartTradeWiseSkillIndexScoreGraph.skillIndex", dataString, "successCallBack", "pager", "", "", "", "");
} */
function ShowRadarChart(graphType) {
    jQuery('#divMain').hide();
    jQuery('#divRadarChart').addClass('custom-popup');
    jQuery('#divRadarChart').show();
    jQuery('#divRadarChart').css('border', '1px solid #F1F5FB');
    jQuery('#divRadarChart').css('z-index', 100);

    var etpmCode = jQuery('#hdnEtpmCode').val();
    var dataString = "?&etpmCode=" + etpmCode;
    
    // ✅ Use plain $.ajax instead of processAjaxCalls with pager
    jQuery.ajax({
        url: "chartTradeWiseSkillIndexScoreGraph.skillIndex" + dataString,
        type: "GET",
        success: function(result) {
            if (typeof result === 'string') result = JSON.parse(result);
            successCallBack(result);
        },
        error: function() {
            console.error("Radar chart AJAX failed");
        }
    });
}
var radarChartRendered = false;
function successCallBack(result) {
    document.getElementById("divChartDataTable").innerHTML = ('');
    var rowSize = result.successdata.length;
    
    var tableContent = "<table class='graphDatatable' style='width:100%;'>";
    for (var i = 1; i <= 2; i++) {
        tableContent += "<tr class='graphDatatableRow'>";
        if (i == 1) {
            tableContent += "<td class='graphDatatableHeader' style='text-align:center;word-wrap:break-word;'></td>";
            for (var j = 0; j < rowSize; j++) {
                tableContent += "<td class='graphDatatableHeader' style='text-align:center;word-wrap:break-word;'>";
                tableContent += result.successdata[j];
                tableContent += "</td>";
            }
        } else if (i == 2) {
            tableContent += "<td class='graphTableDatas' style='text-align:center;word-wrap:break-word;'>JH Avg Skill Index</td>";
            for (var j = 0; j < rowSize; j++) {
                tableContent += "<td class='graphTableDatas' style='text-align:center;word-wrap:break-word;'>";
                tableContent += result.seriesData[j];
                tableContent += "</td>";
            }
        }
        tableContent += "</tr>";
    }
    tableContent += "</table>";
    document.getElementById("divChartDataTable").innerHTML = (tableContent);

   /*  if (!isArray(result)) {
        var chartData = result.chartData.chartData;
        drawChart(chartData, 'radarChartHolder', 'N', 'N', 'N', 'N', 'N');
    } */
    
    if (!isArray(result)) {
        var chartData = result.chartData.chartData;
        
        // ✅ Destroy any existing charts
        Highcharts.charts.forEach(function(c) { if (c) c.destroy(); });
        
        // ✅ Recreate clean container
        document.getElementById('radarChartHolder').innerHTML = 
            '<div id="chtContainer_radarChartHolder" style="height:370px;display:block;overflow:visible;width:100%;"></div>';
        
        // ✅ Render directly — clean config, no dirty chartData properties
        try {
        	new Highcharts.Chart({
        	    chart: {
        	        renderTo: 'chtContainer_radarChartHolder',
        	        type: 'line',
        	        polar: true,
        	        backgroundColor: '#f0f4ff'  
        	    },
        	    title: { text: chartData.title ? chartData.title.text : '' },
        	    pane: {
        	        size: '80%',
        	        background: [{
        	            backgroundColor: '#e8f0fe',   
        	            borderWidth: 0
        	        }]
        	    },
        	    xAxis: {
        	        categories: chartData.xAxis.categories,
        	        tickmarkPlacement: 'on',
        	        lineWidth: 0,
        	        gridLineColor: '#99b3ff',        
        	        gridLineWidth: 1,
        	        labels: {
        	            style: { color: '#333', fontWeight: 'bold' }
        	        }
        	    },
        	    yAxis: {
        	        gridLineInterpolation: 'polygon',
        	        lineWidth: 0,
        	        min: 0,
        	        gridLineColor: '#99b3ff',         
        	        gridLineWidth: 1
        	    },
        	    tooltip: {
        	        formatter: function() {
        	            return this.x + ': ' + this.y;
        	        }
        	    },
        	    series: chartData.series.map(function(s) {
        	        return {
        	            name: s.name,
        	            data: s.data,
        	            pointPlacement: 'on',
        	            type: 'line',
        	            color: '#0044cc',             
        	            lineWidth: 2,
        	            marker: {
        	                fillColor: '#0044cc',     
        	                radius: 4
        	            }
        	        };
        	    }),
        	    legend: { enabled: true },
        	    credits: { text: 'Perfex360' },
        	    accessibility: { enabled: false }
        	});
        }catch(e) {
            console.error("Radar chart error:", e.message);
        }
    }
    var seriesData = result.seriesData;
    jQuery("#hdnSuccessData").val(seriesData);
    var title = result.title;
    document.getElementById("titleDiv").innerHTML = (title);
}


function viewGrid(url,dataString){
		//alert(dataString);
		processGridnew(url,dataString,"tradeViewGrd","pager","","");
		return true;
}
function drawChart_old(chartData, chartContainerId, buildTable, showTable, showImage, showTitle, addHtml) {
alert(1236);
    var ChartTitle = chartData.title.text;

    if (isIE() && "graprZoom" == chartContainerId) {
        chartData.width = 1200;
        chartData.height = 350;
    }

    var bindImg = getChartHtml(chartContainerId, ChartTitle, addHtml);
    bindChartHtml(chartData, chartContainerId, buildTable, showTable, showImage, bindImg, showTitle);

    var isRadar = chartData.polar === true;

    new Highcharts.Chart({
        chart: {
            renderTo: 'chtContainer_' + chartContainerId,
            type: chartData.chartType,                          // ✅ Fix 1: was defaultSeriesType
            margin: [25, 50, 100, 80],
            zoomType: 'xy',
            polar: isRadar,
            width: chartData.width != null ? chartData.width : null,   // ✅ Fix 2: was 0
            height: chartData.height != null ? chartData.height : null // ✅ Fix 2: was 0
        },
        title: (showTitle == 'N' ? '' : chartData.title),
        subtitle: chartData.subTitle,

        pane: isRadar ? { size: '80%' } : undefined,

        xAxis: isRadar ? {
            categories: chartData.xAxis.categories,
            tickmarkPlacement: 'on',
            lineWidth: 0
        } : chartData.xAxis,

        yAxis: isRadar ? {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        } : chartData.yAxis,

        tooltip: {
            formatter: chartData.tooltip != null && chartData.tooltip.shared == true ? '' : function () {
                var s;
                if (this.point.name) {
                    s = '' + this.point.name + ': ' + this.y + '';
                } else {
                    s = '' + this.x + ': ' + this.y;
                }
                return s;
            },
            shared: chartData.tooltip != null && chartData.tooltip.shared != null ? chartData.tooltip.shared : false
        },
        plotOptions: chartData.plotOption,

        series: isRadar
        ? Array.prototype.slice.call(chartData.series).map(function(s) {
            return Object.assign({}, s, { pointPlacement: 'on' });
          })
        : chartData.series,

        legend: chartData.legend
    });

    chartImageEvent(chartContainerId);
}


</script>
<div id="divMain"> 
<div id="wrapperRpt"   style= margin-top:13px;>

<table>
    <tr>
           <td>
	          <div style="padding-left: 0px;">
		        <input id="btnLineChart" class="easyui-button"  type="button" value="Line Graph"/>  
		        
	          <span style="padding-left: 10px;">
		        <input id="btnRadarGraph" class="easyui-button"  type="button" value="Raddar Graph"/>  
		      </span>
		      
		      <span style="padding-left: 10px;">
		        <label style="background-color:blue;color:#C7CEFD;border:solid 1px;font-size:12px;font-weight:bold;font-size:14px;">Select The Date From Filter to View The Graph</label>  
		      </span>
		      
		      </div>
          </td>

          
    </tr>
    
    <tr>
    <td colspan="2">
    <div class="clear"></div>
	<table id="tradeViewGrd" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="divGraphContainer" style="display:block; height:auto;"></div>	
	</td>
     </tr>
</table>

</div>

</div>


<div title="Radar Chart" id="divRadarChart" class="divRadarChart" style="display:none; width:100%; height:auto;">


	<table   style="width: 99%;height: 99%;vertical-align: top;">
	<tr>
	<td colspan="7" align="center"  class="sub-header bord" style="text-align: center;">
		<label> 
		 <c:out value="${fnlnDescription}"></c:out>	 - 	Avg skill index report -
		 <c:if test="${etpmCode == 'P' }">
		       PROCESS
		 </c:if>
		 <c:if test="${etpmCode == 'M' }">
		       RESOURCE
		 </c:if>
		 <c:if test="${etpmCode == 'S' }">
		 		SERVICE
		 </c:if>
		 <c:if test="${etpmCode == 'T' }">
		       TECHNICAL
		 </c:if>
		  
		  <font id="titleDiv"></font>
		
		</label>
	</td> 

	<tr valign="top">
		<td>
			<img id="imgPrintRadarChart" alt=""  src="images/chart_print.gif" title="Print">
			<img id="imgExportRadarChart" alt=""  src="images/chart_datatable.png" title="Export To Excel" >
		 	
		 <span  style="padding-left:80%;">
			<input class="easyui-button" type="button" id="btnCharClose" name="btnCharClose" value="Close">
			 </span>
			<div class="loadExpToExcel" id="loadRadarExcFormat" style="margin-left:-140px;margin-top:-10px;">
			<div class='loadRadarExc' id='loadRadarExc'></div></div>
		</td>
	</tr>
	<tr valign="top">
	<td>
	    <div id="radarChartHolder" style="width:100%;height:380px;min-height:380px;"></div>
	
<!-- 		<div style="vertical-align: top;" id="divChartData" style="margin-top:10px;display:none;width:100%;;height:92%;">
 		</div> -->
	</td>
	
	</tr>
	
	<tr valign="top">
	<td>
		<div style="vertical-align: top;" id="divChartDataTable">
		</div>
	</td>
	
	</tr>
	</table>
</div> 	 


<input type="hidden" id="hdnEtpmCode" name="hdnEtpmCode" value ="${requestScope.etpmCode}"/>
<input type="hidden" id="hdFlid" name="hdFlid" value ="${requestScope.flid}"/>
<input type="hidden" id="hdnSuccessData" name="hdnSuccessData"/>
<input type="hidden" id="hdnTitle" name="hdnTitle" value =""/>
