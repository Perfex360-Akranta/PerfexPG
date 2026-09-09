<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function chartGetData_ErrorCallBack(result) {
    alert('Error in the Charts');
}

function renderChart(chartData) {
    jQuery("#divPanel").hide();
    jQuery("#imgPanel").hide();
    jQuery("#chartHolder").show();

    // Build toolbar and container HTML
    var toolbarHtml = '<div id="ImgGraphDiv_chartHolder" style="padding-bottom:0px;">';
    toolbarHtml += '<div id="dashTool" class="dashToolBar" style="margin-bottom:5px;">';
    toolbarHtml += '<span class="tBarImg" style="cursor:pointer;"><img id="imgPrintChart_chartHolder" alt="Print" src="images/chart_print.gif" title="Print"></span>';
    toolbarHtml += '<span class="tBarImg" style="cursor:pointer;margin-left:5px;"><img id="imgExportChart_chartHolder" title="Export to Excel" src="images/chart_datatable.png"></span>';
    toolbarHtml += '</div>';
    toolbarHtml += '<div class="loadExpToExcel" id="loadExcFormat_chartHolder" style="display:none;margin-left:-140px;margin-top:-16px;height:42px;">';
    toolbarHtml += '<span onclick="closeXlContiner(\'loadExcFormat_chartHolder\');" style="float:right;font-weight:bold;cursor:pointer;">X</span>';
    toolbarHtml += '<div class="loadExc" id="loadFormat_chartHolder"></div>';
    toolbarHtml += '</div>';
    toolbarHtml += '<input type="hidden" id="hdnChartJsonData_chartHolder" name="hdnChartJsonData_chartHolder"/>';
    toolbarHtml += '<div id="graphContainer_chartHolder">';
    toolbarHtml += '<div id="chtContainer_chartHolder" style="position:relative;height:366px;display:block;width:100%;"></div>';
    toolbarHtml += '<div id="dataTableContainer_chartHolder" style="position:relative;width:100%;overflow:auto;display:block;margin-top:10px;"></div>';
    toolbarHtml += '</div>';
    toolbarHtml += '<div id="dummyTableContainer" style="display:none;"></div>';
    toolbarHtml += '</div>';

    jQuery('#chartHolder').html(toolbarHtml);

    // Render Highcharts chart
    var chart = Highcharts.chart('chtContainer_chartHolder', {
        chart: {
            type: chartData.series[0].type || 'column'
        },
        title: {
            text: chartData.title.text
        },
        subtitle: {
            text: chartData.subTitle ? chartData.subTitle.text : ''
        },
        xAxis: chartData.xAxis,
        yAxis: chartData.yAxis,
        tooltip: {
            shared: false,
            formatter: function() {
                return this.x + ': ' + this.y;
            }
        },
        plotOptions: {
            column: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        series:   chartData.series,
        legend:   { enabled: true },
        credits: {
            text: 'Perfex360'
        }
    });

    // Build data table below chart
    buildDataTable(
        chartData,
        'dataTableContainer_chartHolder',
        'hdnChartJsonData_chartHolder'
    );

    // Bind Print button
    jQuery("#imgPrintChart_chartHolder").off('click').on('click', function() {
        PrintDivData('graphContainer_chartHolder');
    });

    // Bind Export to Excel button
    jQuery("#imgExportChart_chartHolder").off('click').on('click', function() {
        jQuery('#loadExcFormat_chartHolder').css({
            'margin-left': '-140px',
            'margin-top': '18px'
        });
        jQuery("#loadExcFormat_chartHolder").slideToggle(200);

        var loadHtml = "<div style='padding-bottom:1px;'>"
                     + "<a href='#' id='Excel3_chartHolder'> Send To xls</a>"
                     + "</div>";
        loadHtml    += "<div>"
                     + "<a href='#' id='Excel7_chartHolder' style='padding-top:5px;'> Send To xlsx</a>"
                     + "</div>";
        jQuery("#loadFormat_chartHolder").html(loadHtml);

        jQuery('#Excel3_chartHolder').off('click').on('click', function(e) {
            e.preventDefault();
            sendChartToExcel("3", "loadExcFormat_chartHolder");
        });
        jQuery('#Excel7_chartHolder').off('click').on('click', function(e) {
            e.preventDefault();
            sendChartToExcel("7", "loadExcFormat_chartHolder");
        });
    });

    // Force reflow after popup finishes layout
    setTimeout(function() {
        if (chart && typeof chart.reflow === 'function') {
            chart.reflow();
        }
    }, 200);
}

function chartGetData_SuccessCallBack(result) {
    var chartData = !isArray(result) ? result.chartData : result[0].chartData;
    setTimeout(function() {
        renderChart(chartData);
    }, 100);
}

jQuery(document).ready(function() {
    var url = jQuery('#hdnChartDataUrl').val();
    if (url != null && url != "") {
        processAjaxCalls(
            url,
            "",
            "chartGetData_SuccessCallBack",
            "chartGetData_ErrorCallBack"
        );
    }
});

</script>

<div id="container" style="position:relative; height:auto; display:block;">
    <div id="divPanel" style="top:39%; left:50%; position:relative;">Loading...</div>
    <img id="imgPanel" alt="" src="images/ajaxVloader.gif"
         style="height:10px; margin-left:50%; margin-top:25%;"/>
    <div id="chartHolder" style="width:100%; height:auto; display:none;"></div>
</div>

<input type="hidden" id="hdnTabCount"      name="hdnTabCount"/>
<input type="hidden" id="hdnChartDataUrl"  value="${requestScope.chartUrl}"/>