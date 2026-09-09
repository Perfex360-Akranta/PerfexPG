<script>

jQuery(document).ready(function(){
	var url=jQuery("#hiddenUrl").val();
	viewGrid(url,"?q=2");

	jQuery ("#btnRadarChart").click(function()		{

		document.getElementById("divChartData").innerHTML =('');
		//jQuery('#mstfrm_div').addClass('popup-mask');	
		//jQuery('#mstfrm_div').show();
		var empIds=getEmpIds();
		if(empIds=="N" ){
			alert("Select Employees to View Chart");
			return false;
		}
		else if(empIds=="L" ){
			alert("Select Less than 10 Employees");
			return false;
		}else{
			jQuery('#divMain').hide();
			jQuery("#hdnempIds").val(empIds);
			jQuery('#divRadarChart').addClass('custom-popup');		
			jQuery('#divRadarChart').show();		  
			jQuery('#divRadarChart').css('border','1px solid #F1F5FB');
			jQuery('#divRadarChart').css('z-index',100); 
			
			var flid = jQuery("#hdnSkillFlid").val();
			var uniPosId = jQuery("#hdnSkillUniPos").val();
			var reviewDate = jQuery("#hdnReviewDate").val();
			var reviewType = jQuery("#hdnReviewType").val();
			var url = "Chart.skillIndex";
			var dataString = "&flid="+flid+"&uniPosID="+uniPosId+"&reviewDate="+reviewDate+"&reviewDate="+reviewType+"&empIds="+empIds;
			//alert(dataString);
			processAjaxCalls("SkillIndexRadarChart.skillIndex",dataString,"successCallBack","pager","","","","");
			
		}
	});
	jQuery("#imgPrintRadarChart").click(function(){
		PrintDivData('divChartData');
	});
	
	
	jQuery("#imgExportRadarChart").click(function(){
		jQuery("#loadRadarExcFormat").slideToggle(200);
/* 		var loadHtml = "<div style='padding-bottom:1px;'><a href='#' id='Excel3' name='Excel3'> Send To Excel 2003</a></div>";
 */		
 var	loadHtml = "<div><a href='#' id='Excel7' name='Excel7' style='padding-top:5px;'> Send To Excel </a></div>";
			
		jQuery("#loadRadarExc").html(loadHtml);
		jQuery( '#Excel3').click(function (){		
			sendToExcel("3");		
		});
		jQuery( '#Excel7').click(function (){
			sendToExcel("7");
		});
		
	});

	//tableHeaderSpanCallback
 
	jQuery ("#btnCharClose").click(function()		
	{
		jQuery("#hdnSuccessData").val("");
		jQuery("#hdnempName").val("");
		closeRadarDialog("divRadarChart");
		jQuery('#divMain').show();
	});

	jQuery('#chkSelectAll').click(function() {
		show_winMask(1);
        fnSelectAllEmp();
		show_winMask(0);
	});
	
});

function  fnSelectAllEmp() {
	var colModel = jQuery("#SkillIndexlist").jqGrid("getGridParam","colModel" );
	for(var j=6;j<colModel.length;j++){
		if(jQuery("#chkSelectAll").is(':checked')== true){
		    jQuery('#chkEmployee_'+j).attr('checked',true);
		 }
		 else {
			 jQuery('#chkEmployee_'+j).attr('checked',false);
		 }
	 }	
}


	function closeRadarDialog(dlgId)
	{
		 jQuery( '#'+dlgId ).hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#'+dlgId ).removeClass('custom-popup');
	}
			

	function viewGrid(url,filterString)
	{
		//var filterString = jQuery("#hdnFilterString").val();
		var flid = jQuery("#hdnSkillFlid").val();
		var uniPosId = jQuery("#hdnSkillUniPos").val();
		var reviewDate = jQuery("#hdnReviewDate").val();
		var reviewType = jQuery("#hdnReviewType").val();
		//alert(reviewDate);
		//alert(flid+"----"+uniPosId);
		//alert(url);
		var dataString = "&flid="+flid+"&uniPosID="+uniPosId+"&reviewDate="+reviewDate+"&reviewType="+reviewType;
		//alert(url);
		//processAjaxCalls("EmpTopicRate_chart.skilGap?","","topicRateSuccesscallback","","","");
		
		//processAjaxCalls("SkillIndexRadarChart.skillIndex",dataString,"successCallBack","pager","","","","");
		processGridnew(url,"?q=2&firstClick=Y"+dataString,"SkillIndexlist","pager","","","tableHeader","loadcomplete");
		
	}
	function getEmpIds(){
		var empIds="";
		var count=0;
		var colModel = jQuery("#SkillIndexlist").jqGrid("getGridParam","colModel" );
		for(var j=6;j<colModel.length;j++){
			//chkEmployee_"+i+"'
			if(jQuery('#chkEmployee_'+j).is(':checked')==true){
				var colmName=colModel[j].name;
				var colNameArr=colmName.split("_");
				empIds+=colNameArr+",";
				count++;
			}
			
		}
		
		if (empIds.length>0) {
			empIds =empIds.slice(0,-1) ;
		}
		
		if(parseInt(count)>10){
			empIds="L";
		}
		if(parseInt(count)==0){
			empIds="N";
		}
		return empIds;
	}
	
	/* function successCallBack(result)
	{
		
		var rowSize=result.fillData.length;
		var tableContent=" <table class='bord'  style='width:100%;height:100%; margin-top: 0px;'>";
		
		var cnt = 1;
		
		for (var j=3;j<rowSize;j++)
		{			
			tableContent+='<tr valign="top" >';	
			 if(j==3)
	 				tableContent+="";//"<td  align=center  class='sub-header bord' style='font-size:12px; '>Sl.No</td>";
	 		 else if(j==4) 	
				tableContent+="";//"<td  align=center  class='fontSize bord' style='font-size:12px;'>&nbsp;&nbsp;&nbsp;</td>";
			 else{
				tableContent+="";//"<td  align=center class='bord' style='font-size:12px;'>"+cnt+"</td>";
	 			cnt = parseInt(cnt)+parseInt(1);
			 }
			 
			 for (var i=2;i<result.fillData[j].length-1;i++)
			  {
				 if(j==3) {
					 	if (i==2) {
					 	
			 				tableContent+=" <tr> <td colspan=7 align=center  class='sub-header bord' style='font-size:12px; '> Legend </td> </tr> ";
			 				tableContent+=" <tr class='sub-header' style='font-size:9px;color:blue; '> <td > "+result.fillData[4][3]+" "+result.fillData[4][4]+" </td>  ";
			 				tableContent+="  <td>  "+ result.fillData[5][3]+" "+result.fillData[5][4]+" </td>  ";
			 				tableContent+="  <td>  "+ result.fillData[6][3]+" "+result.fillData[6][4]+" </td>  ";
			 				tableContent+="  <td>  "+ result.fillData[7][3]+" "+result.fillData[7][4]+" </td>  ";
			 				tableContent+="  <td>  "+ result.fillData[8][3]+" "+result.fillData[8][4]+" </td>  ";
			 				tableContent+="  <td>  "+ result.fillData[9][3]+" "+result.fillData[9][4]+" </td>  ";
			 				tableContent+="  <td>  "+ result.fillData[10][3]+" "+result.fillData[10][4]+" </td>  ";
			 				tableContent+="  </tr> ";
					   }
					 	
					  if(i>4) {
						  if (i % 5 ==0 ) {
							  tableContent+="</tr> <tr>";
						  }
					 	//tableContent+="<td align=center class='sub-header' style='text-align:center;width:auto;'>"+result.fillData[j][i]+"</td>";
					  }
					 }
					 else if(j==4)
						 {	
						  if(i>=5){
							  if (i % 5	 ==0 ) {
								  tableContent+="</tr> </table> <table class='bord'  style='width:100%;vertical-align: top;'> <tr> ";
							  }
							  
						 		tableContent+="<td style='font-size:12px;text-align:center;width:auto;' class='bord' > ";
						 		tableContent+="<div align=center class='sub-header' style='text-align:center;width:auto;'>"+result.fillData[3][i]+"</div>";
						 		tableContent+="<div id='chart_"+j+"_"+i+"' style='width:100%;'></div></td>";
						  }
				 	   	  else {
				 	   		var tstr="";
				 	   		  if (i==3 || i==4)
				 	   		  	tstr="..........................................";
				 	   		  tableContent+="";//"<td valign='top'  style='font-size:12px;' class='bord' >&nbsp;"+tstr+"</td>";
				 	   	  }
					 }
					else
						 tableContent+="";// "<td align=left class='fontSize bord'>"+result.fillData[j][i]+"</td>";
			  }
			 tableContent+="</tr>";  
	 		}
		tableContent+="</table>";
		
		document.getElementById("divChartData").innerHTML =(tableContent);
		for (var k=4;k<rowSize;k++)
		{	
			for (var i=5;i<result.fillData[k].length-1;i++) 
			{
				result.successdata[i-5].chartData.width ="220";
				result.successdata[i-5].chartData.height="220";
				result.successdata[i-5].chartData.legend="";
				var successData= jQuery("#hdnSuccessData").val();
				var colModelStr = JSON.stringify(result.successdata[i-5].chartData.series);
				if(successData.length>0){
					successData+=";"+colModelStr;
				}else{
					successData=colModelStr;
				}
				jQuery("#hdnSuccessData").val(successData);

				var empData= jQuery("#hdnempName").val();
				var empDataStr = JSON.stringify(result.fillData[3][i]);
				if(empData.length>0){
					empData+=";"+empDataStr;
				}else{
					empData=empDataStr;
				}
				jQuery("#hdnempName").val(empData);
				drawChart(result.successdata[i-5].chartData,"chart_"+k+"_"+i,"","","N");
			}
			jQuery("div[id^=chtContainer_chart_]").css('height','200px');
			jQuery("div[id^=chtContainer_chart_]").css('width','120px');
		}
	}
 */	
 var empChartStore = [];
 function successCallBack(result) {

	    Highcharts.charts.forEach(function(c) { if (c) c.destroy(); });

	    var fillData = result.fillData;
	    var successdata = result.successdata;

	    var legendHtml = '<tr><td colspan="7">';
	    legendHtml += '<div style="background:#dce8f5;padding:6px 10px;font-size:11px;margin-bottom:6px;">';
	    legendHtml += '<strong>Legend&nbsp;&nbsp;</strong>';
	    for (var l = 4; l <= 10; l++) {
	        if (fillData[l] && fillData[l][3]) {
	            legendHtml += '<span style="margin-right:14px;color:#0000cc;font-weight:bold;">'
	                        + fillData[l][3] + '&nbsp;' + (fillData[l][4] || '') + '</span>';
	        }
	    }
	    legendHtml += '</div></td></tr>';

	    var chartCells = '';
	    var cellCount = 0;

	    for (var i = 0; i < successdata.length; i++) {
	        // ✅ Fix: one extra .chartData level
	        var chartData = successdata[i].chartData;  
	        
	        var empName = (fillData[3] && fillData[3][5 + i]) 
	                      ? fillData[3][5 + i] 
	                      : ('Employee ' + (i + 1));

	        // ✅ Series is directly chartData.series[0].data
	        var seriesData = (chartData.series && chartData.series[0] && chartData.series[0].data)
	                         ? chartData.series[0].data : [];

	        var avg = 0;
	        if (seriesData.length > 0) {
	            var nonZero = seriesData.filter(function(v){ return v > 0; });
	            avg = nonZero.length > 0 
	                  ? (nonZero.reduce(function(a,b){ return a+b; }, 0) / nonZero.length).toFixed(2)
	                  : '0.00';
	        }

	        // Store for export
	        empChartStore[i] = {
    empName: empName.replace(/\s*-\s*Avg\..*$/i, '').trim(), // strip "- Avg.: X.XX"
    seriesJson: JSON.stringify(successdata[i].chartData.series) // ✅ correct path
};
	        if (cellCount % 3 === 0) {
	            if (cellCount > 0) chartCells += '</tr>';
	            chartCells += '<tr valign="top">';
	        }

	        /* chartCells += '<td style="width:33%;padding:4px;vertical-align:top;">'
	            + '<div style="background:#5b9bd5;color:white;text-align:center;padding:4px 6px;'
	            + 'font-size:11px;font-weight:bold;">' + empName + '</div>'
	            + '<div style="background:#f0f5ff;border:1px solid #ccc;">'
	            + '<div id="radarChart_' + i + '" style="height:220px;width:100%;"></div>'
	            + '</div>'
	            + '</td>'; */
	            
	            chartCells += '<td style="width:33%;padding:4px;vertical-align:top;">'
	                // ââ Header bar ââ
	                + '<div style="background:#5b9bd5;color:white;text-align:center;padding:4px 6px;'
	                + 'font-size:11px;font-weight:bold;">' + empName + ' </div>'
	                // ââ Toolbar ââ
	                + '<div style="background:#e8f0fa;padding:3px 6px;border:1px solid #ccc;border-bottom:0;display:flex;align-items:center;gap:6px;">'
	                +   '<img src="images/chart_print.gif" title="Print" style="cursor:pointer;" '
	                +       'onclick="printEmpChart(' + i + ',\'' + empName.replace(/'/g,"\\\'") + '\')" />'
	                +   '<img src="images/chart_datatable.png" title="Export to Excel" style="cursor:pointer;" '
	                +       'onclick="exportEmpChart(' + i + ',\'' + empName.replace(/'/g,"\\\'") + '\')" />'
	                +   '<div id="empExcelMenu_' + i + '" style="display:none;position:absolute;background:white;'
	                +       'border:1px solid #aaa;padding:4px 8px;z-index:999;font-size:11px;">'
/* 	                +     '<div><a href="#" onclick="sendEmpToExcel(' + i + ',\'3\');return false;">Send To Excel 2003</a></div>'
 */	                +     '<div><a href="#" onclick="sendEmpToExcel(' + i + ',\'7\');return false;">Send To Excel</a></div>'
	                +     '<span onclick="jQuery(\'#empExcelMenu_' + i + '\').hide();" '
	                +         'style="float:right;font-weight:bold;cursor:pointer;margin-top:-30px;">X</span>'
	                +   '</div>'
	                + '</div>'
	                // ââ Chart container ââ (' + i + ',\'7\')
	                + '<div style="background:#f0f5ff;border:1px solid #ccc;border-top:0;">'
	                + '<div id="radarChart_' + i + '" style="height:220px;width:100%;"></div>'
	                + '</div>'
	                + '</td>';
	        cellCount++;
	    }
	    if (cellCount > 0) chartCells += '</tr>';

	    var tableHtml = '<table style="width:100%;">' + legendHtml + chartCells + '</table>';
	    document.getElementById("divChartData").innerHTML = tableHtml;

	    for (var j = 0; j < successdata.length; j++) {
	        (function(idx) {
	            // ✅ Fix: correct nesting
	            var chartData = successdata[idx].chartData;

	            var categories = chartData.xAxis && chartData.xAxis.categories
	                ? chartData.xAxis.categories.slice(0, -1)  // ✅ remove duplicate last "1"
	                : [];

	            var seriesData = chartData.series && chartData.series[0]
	                ? chartData.series[0].data
	                : [];

	            if (seriesData.length === 0) {
	                document.getElementById('radarChart_' + idx).innerHTML =
	                    '<div style="text-align:center;padding:80px 0;color:#999;">No data</div>';
	                return;
	            }

	            try {
	                new Highcharts.Chart({
	                    chart: {
	                        renderTo: 'radarChart_' + idx,
	                        type: 'line',
	                        polar: true,
	                        backgroundColor: '#f0f5ff',
	                        margin: [20, 20, 30, 20]
	                    },
	                    title: { text: '' },
	                    pane: { size: '70%' },
	                    xAxis: {
	                        categories: categories.map(function(c, i){ return i + 1; }),
	                        tickmarkPlacement: 'on',
	                        lineWidth: 0,
	                        gridLineColor: '#aaaaaa',
	                        labels: { style: { color: '#333', fontSize: '10px' } }
	                    },
	                    yAxis: {
	                        gridLineInterpolation: 'polygon',
	                        lineWidth: 0,
	                        min: 0,
	                        gridLineColor: '#aaaaaa',
	                        labels: { enabled: false }
	                    },
	                    tooltip: {
	                        formatter: function() {
	                            return (categories[this.point.x] || this.x) + ': <b>' + this.y + '</b>';
	                        }
	                    },
	                    series: [{
	                        name: 'Score',
	                        data: seriesData,
	                        pointPlacement: 'on',
	                        type: 'line',
	                        color: '#1f5faa',
	                        lineWidth: 1.5,
	                        marker: { fillColor: '#1f5faa', radius: 3 }
	                    }],
	                    legend: { enabled: false },
	                    credits: { enabled: false },
	                    accessibility: { enabled: false }
	                });
	            } catch(e) {
	                console.error("Chart " + idx + " error:", e.message);
	            }
	        })(j);
	    	    }

	    var successDataStr = '';
	    var empNameStr = '';
	    for (var k = 0; k < successdata.length; k++) {
	        var cd = typeof successdata[k] === 'string' 
	                 ? JSON.parse(successdata[k]) 
	                 : successdata[k];
	        
	        var seriesArr = cd.chartData ? cd.chartData.series : cd.series;
	        
	        // ✅ Use ; separator between AND after last item (trailing ;)
	        successDataStr += JSON.stringify(seriesArr) + ";";
	        empNameStr += (fillData[3][5 + k] || '') + ";";
	    }
	    jQuery("#hdnSuccessData").val(successDataStr);
	    jQuery("#hdnempName").val(empNameStr);
	    //alert (jQuery("#hdnSuccessData").val() +" .........")
	}
//ââ Per-employee data store (populated in successCallBack) ââ
   // store { empName, seriesJson } per index

 // ââ Print individual employee chart ââ
 function printEmpChart(idx, empName) {
     var chartDiv = document.getElementById('radarChart_' + idx);
     if (!chartDiv) return;

     var svg = chartDiv.querySelector('svg');
     if (!svg) { alert("Chart not rendered yet."); return; }

     var printWin = window.open('', '_blank', 'width=600,height=500');
     printWin.document.write(
         '<html><head><title>Radar Chart - ' + empName + '</title>'
         + '<style>body{font-family:Arial;text-align:center;} h3{color:#333;}</style>'
         + '</head><body>'
         + '<h3>' + empName + '</h3>'
         + svg.outerHTML
         + '<script>window.onload=function(){window.print();window.close();}<\/script>'
         + '</body></html>'
     );
     printWin.document.close();
 }

 function exportEmpChart(idx, empName) {
     jQuery('[id^=empExcelMenu_]').hide();
     jQuery('#empExcelMenu_' + idx).toggle();
 }
 function sendEmpToExcel(idx, format) {
	    jQuery('#empExcelMenu_' + idx).hide();

	    if (!empChartStore[idx]) {
	        alert("No data available for export.");
	        return;
	    }

	    var emp = empChartStore[idx];
	    var seriesArray = JSON.parse(emp.seriesJson);
	    var firstSeries = seriesArray[0];

	    // ✅ Format: "[{...}];" — semicolon NOT encoded, matches servlet's split(";")
	    var dataGrid = JSON.stringify([firstSeries]) + ";";
	    var empName  = emp.empName.trim() + ";";
	    var reviewDate = jQuery("#hdnReviewDate").val() || '';

	    console.log("dataGrid:", dataGrid.length);
	    console.log("empName:", empName);

	    // ✅ Use escape() to match original sendToExcel behavior
	    // escape() does NOT encode semicolons, encodeURIComponent does
	    var dataString = "?q=2"
	        + "&dataGrid=" + escape(dataGrid)
	        + "&empNames=" + escape(empName)
	        + "&reviewDate=" + reviewDate;

	    if (jQuery("#formxlexport").length <= 0) {
	        var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST">'
	            + '<input type="hidden" id="fileName" name="fileName"/>'
	            + '<input type="hidden" id="f" name="f"/>'
	            + '<input type="hidden" id="exporthtml" name="exporthtml"/>'
	            + '</form>';
	        jQuery("#LoadContent").prepend(xlFormHtml);
	    }

	    jQuery("#formxlexport input[id=f]").val(format);
	    document.formxlexport.method = 'POST';
	    document.formxlexport.action = "exportRadarChart.skillIndex" + dataString;
	    document.formxlexport.target = '_blank';
	    document.formxlexport.submit();
	}
 
 /*  function sendEmpToExcel(idx, format) {
     jQuery('#empExcelMenu_' + idx).hide();

     if (!empChartStore[idx]) {
         alert("No data available for export.");
         return;
     }

     var reviewDate = jQuery("#hdnReviewDate").val();
     var dataString = "?q=2"
         + "&dataGrid=" + escape(empChartStore[idx].seriesJson)
         + "&empNames=" + escape(JSON.stringify(empChartStore[idx].empName))
         + "&reviewDate=" + reviewDate;

     if (jQuery("#formxlexport").length <= 0) {
         var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST">'
             + '<input type="hidden" id="fileName" name="fileName"/>'
             + '<input type="hidden" id="f" name="f"/>'
             + '<input type="hidden" id="exporthtml" name="exporthtml"/>'
             + '</form>';
         jQuery("#LoadContent").prepend(xlFormHtml);
     }

     jQuery("#formxlexport input[id=f]").val(format);
     document.formxlexport.method = 'POST';
     document.formxlexport.action = "exportRadarChart.skillIndex" + dataString;
     document.formxlexport.target = '_blank';
     document.formxlexport.submit();
 } */
 function tableHeader(colM,headers,colN)
	{
		
		var len=(Object.keys(colN[4])).length;
		//alert(len +" length");
		for(var i=6;i<len;i++){
			var id="CH3-"+(i-4);
			jQuery("#"+id).html("<input id='chkEmployee_"+i+"'  value='1' style='text-align:center;' type='checkbox'/>");
		}
		
	}

	function loadcomplete()
	{
		//alert('load complete');
		//processAjaxCalls("SkillIndexRadarChart.skillIndex","","successCallBack","pager","","","","");
		//processAjaxCalls("SkillIndexRadarChart.skillIndex","","successCallBack","pager","","","","");
		
	}

	

	function sendToExcel(format)
	{
	    var dataString = "?q=2&dataGrid="
	        + encodeURIComponent(jQuery("#hdnSuccessData").val())
	        + "&empNames="
	        + encodeURIComponent(jQuery("#hdnempName").val());

	    var reviewDate = jQuery("#hdnReviewDate").val();

	    dataString += "&reviewDate=" + reviewDate;

	    if (jQuery("#formxlexport").length <= 0)
	    {
	        var xlFormHtml =
	            '<form id="formxlexport" name="formxlexport" method="POST">' +
	            '<input type="hidden" id="fileName" name="fileName" />' +
	            '<input type="hidden" id="f" name="f" />' +
	            '<input type="hidden" id="exporthtml" name="exporthtml"/>' +
	            '</form>';

	        jQuery("#LoadContent").prepend(xlFormHtml);
	    }

	    // IMPORTANT FIX
	    var exportHtml = '';

	    // Capture ALL chart containers from DOM
	    jQuery(".highcharts-container").each(function(index) {

	    	exportHtml +=
	    	    '<div class="exportChart" ' +
	    	    'style="width:100%; page-break-after:always;">'
	    	    + jQuery(this).parent().html() +
	    	    '</div>';

	        console.log("Exported Chart DIV : " + index);
	    });

	    console.log("Total Exported Charts : "
	        + jQuery(".highcharts-container").length);

	    jQuery("#exporthtml").val(exportHtml);

	    jQuery("#formxlexport input[id=f]").val(format);

	    document.formxlexport.method = 'POST';

	    document.formxlexport.action =
	        "exportRadarChart.skillIndex" + dataString;

	    document.formxlexport.target = '_blank';

	    setTimeout(function() {

	        document.formxlexport.submit();

	    }, 1000);
	}
	
	/* 	function sendToExcel(format)
	{

		/*var flid = jQuery("#hdnSkillFlid").val();
		var uniPosId = jQuery("#hdnSkillUniPos").val();
		var reviewDate = jQuery("#hdnReviewDate").val();
		var reviewType = jQuery("#hdnReviewType").val();*/
	/*	var dataString =  "?q=2&dataGrid="+escape(jQuery("#hdnSuccessData").val())+"&empNames="+escape(jQuery("#hdnempName").val());
		var reviewDate = jQuery("#hdnReviewDate").val();
		//alert(reviewDate);
		dataString += "&reviewDate="+reviewDate;
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
		
		 document.formxlexport.action="exportRadarChart.skillIndex"+dataString;
		 document.formxlexport.target='_blank';
		 document.formxlexport.submit();
		  jQuery("div[id^=loadRadarExcFormat]").hide(200); 
	} */

</script>
<style>

.padLeft
{
padding-left:5px;
}
.padRight
{
padding-right:5px;
}
.fontSize
{
font-size:11;
}
.bord{
border:solid 1px #D1D4DD;

padding:3px;
}
</style>
<form id="frmSkillindex">

<div class="easyui-paddingbfpx" id="divMain" style="padding-left: 1%;padding-top:0%; " >
	
<div id="divGraphData" style=" ">
	<span  style="padding-left: 20px;">
		<input class="easyui-button" type="button" id="btnRadarChart" name="btnRadarChart" value="Radar-Chart"> 
	</span>
	<span style="padding-left: 30px;">
		<input type="checkbox" id="chkSelectAll" name="chkSelectAll" style="margin-left:10px;" />
		<input type="text" value="Select All" disabled="disabled" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left; " />
	</span>



<table id="Maintable">
	<tr> <td>
	    <table id="SkillIndexlist" >
	    </table>
	    <div id="pager"></div>
	    </td>
	</tr>
</table>

</div>

<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="${requestScope.filter}">

<input type="hidden" id="hdnSkillFlid" name="hdnSkillFlid" value="${requestScope.flid}">
<input type="hidden" id="hdnSkillUniPos" name="hdnSkillUniPos" value="${requestScope.uniqPosid}">
<input type="hidden" id="hdnReviewDate" name="hdnReviewDate" value="${requestScope.reviewDate}">
<input type="hidden" id="hdnReviewType" name="hdnReviewType" value="${requestScope.reviewType}">
<input type="hidden" id="hdnempIds" name="hdnempIds"/>
<input type="hidden" id="hdnSuccessData" name="hdnSuccessData"/>
<input type="hidden" id="hdnempName" name="hdnempName"/>
</div>


<div title="Radar Chart" id="divRadarChart" class="divRadarChart" style="margin-top:10px;display:none;width:100%;;height:120%;">
	<table   style="width: 99%;height: 99%;vertical-align: top;">
	<tr>
	<td colspan="7" align="center"  class="sub-header bord" style="text-align: center;">
		<label >Skill Index Radar Chart ${requestScope.reviewDate}</label>
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
		<div style="vertical-align: top;" id="divChartData">
		</div>
	</td>
	</tr>
	</table>
</div> 	 
</form>
	
	