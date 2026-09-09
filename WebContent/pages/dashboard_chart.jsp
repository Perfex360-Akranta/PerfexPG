

  <!--[if IE]><script language="javascript" type="text/javascript" src="excanvas.js"></script><![endif]-->
    <link rel="stylesheet" type="text/css" href="css/chart/jquery.jqplot.css" />    
    <script language="javascript" type="text/javascript" src="js/chart/jquery.jqplot.js"></script>
	
	
  <!-- pie chart -->
  <script language="javascript" type="text/javascript" src="js/jqplot.pieRenderer.js"></script>
  
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.canvasTextRenderer.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.canvasAxisTickRenderer.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.canvasAxisLabelRenderer.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.highlighter.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.canvasOverlay.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.cursor.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.pointLabels.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.pieRenderer.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.barRenderer.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.dateAxisRenderer.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jqplot.categoryAxisRenderer.js"></script>
	<script language="javascript" type="text/javascript" src="js/chart/jquery.flot.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jquery.flot.highlighter.js"></script>
    <script language="javascript" type="text/javascript" src="js/chart/jquery.flot.spider.js"></script>
	
    <script type="text/javascript">
    jQuery(document).ready(function(){
    jQuery.getJSON("dashboard_chart_xml.chr?chtype=machinebreakdown",'', machinebreakdownResult);    
    jQuery.getJSON("dashboard_chart_xml.chr?chtype=abnormalityType",'', dashboardabnormalityType);
    jQuery.getJSON("dashboard_chart_xml.chr?chtype=oeeanalysis",'', oeeanalysisdisplayResult);
    jQuery.getJSON("dashboard_chart_xml.chr?chtype=kznvwidntfvscpltd",'', kznvwidntfvscpltdResult);
    jQuery.getJSON("dashboard_chart_xml.chr?chtype=preventmaint",'', preventivemaintenanceResult);   
    jQuery.getJSON("dashboard_chart_xml.chr?chtype=breakdown",'', breakdownResult);
    });
  function machinebreakdownResult(data){ 
	  jQuery("#mchbreakdown").html('');
      /* PARATO CHART TYPE */
            var linedata1 = [];
            var linedata2 = [];
            var j=0;
              for(var i = 0; i < (data.chartDatas.length); i++){            	
            	linedata1.push([data.chartDatas[i].chart[0] , parseInt(data.chartDatas[i].chart[1])]);
            	linedata2.push([data.chartDatas[i].chart[0] , parseInt(data.chartDatas[i].chart[2])]);
            }               
              var ppplot2 = jQuery.jqplot('mchbreakdown', [linedata1, linedata2], {
            	  seriesDefaults: {
  	            	pointLabels: {show: true} 
  	            },
                series:[{renderer:jQuery.jqplot.BarRenderer}, {xaxis:'x2axis', yaxis:'y2axis'}],
                axesDefaults: {
                    tickRenderer: jQuery.jqplot.CanvasAxisTickRenderer ,
                    tickOptions: {                    	
                    	fontSize: '6pt'
                    }
                    
                },
                axes: {
                  xaxis: {
                    renderer: jQuery.jqplot.CategoryAxisRenderer,
                    label:'Machine Breakdown',
                    angle: 90
                    //label:'Month'
                  },
                  x2axis: {
                    renderer: jQuery.jqplot.CategoryAxisRenderer,
                    //label:'Machine Breakdown'
                    label:''
                  },
                  yaxis: {
                    autoscale:true,
                    label:'DOWNTIME',
                    
                    labelOptions: {
                        fontSize: '6pt'
                      }
                      
                  },
                  y2axis: {
                    autoscale:true,
                    label:'CUMULATIVE_DOWNTIME',
                    labelOptions: {                       
                        fontSize: '6pt'
                      }
                  }
                }
              });
  }
  
  function dashboardabnormalityType(data){
	  jQuery("#abnormalityType").html('');
			/* START PIE CHART CODE */

var linedata1 = []; 

for(var i = 0; i < (data.abnormalityType.length); i++){
	var linedata2 = [];
	linedata2.push(data.abnormalityType[i][0]);  
	linedata2.push(parseInt(data.abnormalityType[i][1]));
	linedata1.push(linedata2);
}

	plot1 = jQuery.jqplot('abnormalityType', [ linedata1 ], {
			title : 'Abnormality Type',
			series : [ {
				renderer : jQuery.jqplot.PieRenderer,
				rendererOptions : {
					sliceMargin : 3,
					showDataLabels : true,
					dataLabelNudge : 20,
					legend : {
						show : true,
						fontSize: '6pt'
					}
				}
			} ],
			legend : {
				show : true,
				fontSize: '6pt'
			}
		});
	}

	function oeeanalysisdisplayResult(data){
		//alert(data.oeeanalysis.length);
		var pline1 = [];
		var pline2 = [];
		var pline3 = [];
		var pline4 = [];
		var ticks = [];
		
		for(var i=0; i<data.oeeanalysis.length;i++){
			//alert(data.oeeanalysis[i].chart[2]);
			ticks.push(data.oeeanalysis[i].chart[2]);	
			pline1.push(parseInt(data.oeeanalysis[i].chart[3]));
				pline2.push(parseInt(data.oeeanalysis[i].chart[4]));
				pline3.push(parseInt(data.oeeanalysis[i].chart[5]));
				pline4.push(parseInt(data.oeeanalysis[i].chart[6]));
		}
		
		plot2 = jQuery.jqplot('oeeanalysis', [pline1, pline2, pline3, pline4], {
			title : 'OEE Analysis',			
			fontSize: '6pt',
		    legend: {
		    	show:true, 
		    	location: 'e',
		    	fontSize: '6pt'
		    	},	
		    	
		    	seriesDefaults: {
	            	pointLabels: {show: true} 
	            },
		   
		    series:[
		            {label:'BEADING'},
		            {label:'AIR COMPRESSOR NO.-02'},
		            {label:'CROWN PRESS'},
		            {label:'ALU CAPPING  FLAT CONVEYOR'}
		        ],
		    axes:{
		    	xaxis:{
		    		renderer:jQuery.jqplot.CategoryAxisRenderer, 
		    		ticks:ticks
	    			},
		    		labelOptions: {
		    			fontSize: '6pt'
		    			},
		    			yaxis:{
		    				min:0,
		    				label:"%",
		    				labelOptions: {
		    				fontSize: '6pt'
		    				},
		    				tickOptions : {
		    					angle : 90
		    					}
		    				}
		    			}
		    });
	}
	
	function kznvwidntfvscpltdResult(data){
				
		var bar1 = [];
		var bar2 = [];
		var ticks = [];
		
		for(var i=0; i<data.kznvwidntfvscpltd.length;i=i+2){			
			bar1.push(parseInt(data.kznvwidntfvscpltd[i].chart[3]));
			ticks.push(data.kznvwidntfvscpltd[i].chart[0]);
		}
		
		for(var j=1; j<data.kznvwidntfvscpltd.length;j=j+2){			
			bar2.push(parseInt(data.kznvwidntfvscpltd[j].chart[3]));
		}
		
		/* START BAR CHART CODE */	
        
        bar = jQuery.jqplot('kznvwidntfvscpltd', [bar1, bar2], {
            title: 'Improvements Identified Vs Completed - Company',
            legend: { show: true, placement: 'inside' },
            axesDefaults: {
                labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer,
                labelOptions: {
                    fontSize: '9pt'
                }
            },
            seriesDefaults: { 
            	renderer: jQuery.jqplot.BarRenderer, 
            	pointLabels: {show: true} 
            },
            series:[
                { label: 'Identified' },
                { label: 'Completed' }
            ],
            axes: {
                xaxis: {
                    renderer: jQuery.jqplot.CategoryAxisRenderer,
                    ticks: ticks,
					label: 'Month' 						
                },
                yaxis: {
                    label: 'Count' 
                }
            }
        });
	}	
	
	
	
	
	function breakdownResult(data){
		//alert(data.chartDatas[0].chart.length);
		var pline1 = [];
		var ticks = [];
		
		for(var i=2; i<data.chartDatas[0].chart.length;i++){			
			ticks.push(data.chartDatas[0].chart[i]);	
			pline1.push(parseInt(data.chartDatas[1].chart[i]));				
		}
		
		//alert(ticks);
		
		plot2 = jQuery.jqplot('preventmaint', [pline1], {
			title : 'BREAKDOWN MTTR FACTORY',			
			fontSize: '6pt',
		    legend: {
		    	show:true, 
		    	location: 'e',
		    	fontSize: '6pt'
		    	},
		    	
		    	seriesDefaults: {
	            	pointLabels: {show: true} 
	            },
		   
		    series:[
		            {label:'PCCL-02'}		            
		        ],
		    axes:{
		    	xaxis:{
		    		renderer:jQuery.jqplot.CategoryAxisRenderer,
		    		label:"MONTH",
		    		ticks:ticks
	    			},
		    		labelOptions: {
		    			fontSize: '6pt'
		    			},
		    			yaxis:{
		    				min:0,
		    				label:"MTTR(IN HOURSE)",
		    				labelOptions: {
		    				fontSize: '6pt'
		    				},
		    				tickOptions : {
		    					angle : 90
		    					}
		    				}
		    			}
		    });
	}
	
	
	
	function preventivemaintenanceResult(data){
		//alert(data.chartDatas.length);
		  jQuery("#breakdown").html('');
	      /* PARATO CHART TYPE */
	            var linedata1 = [];
	            var linedata2 = [];
	            var j=0;
	              for(var i = 0; i < (data.chartDatas.length); i++){            	
	            	linedata1.push([data.chartDatas[i].chart[0] , parseInt(data.chartDatas[i].chart[1])]);
	            	linedata2.push([data.chartDatas[i].chart[0] , parseInt(data.chartDatas[i].chart[2])]);
	            }
	              var ppplot2 = jQuery.jqplot('breakdown', [linedata1, linedata2], {
	            	  seriesDefaults: {
	  	            	pointLabels: {show: true} 
	  	            },
	                series:[{renderer:jQuery.jqplot.BarRenderer}, {xaxis:'x2axis', yaxis:'y2axis'}],
	                axesDefaults: {
	                    tickRenderer: jQuery.jqplot.CanvasAxisTickRenderer ,
	                    tickOptions: {                    	
	                    	fontSize: '6pt'
	                    }
	                    
	                },
	                axes: {
	                  xaxis: {
	                    renderer: jQuery.jqplot.CategoryAxisRenderer,
	                    label:'Machine Breakdown',
	                    angle: 90
	                    //label:'Month'
	                  },
	                  x2axis: {
	                    renderer: jQuery.jqplot.CategoryAxisRenderer,
	                    //label:'Machine Breakdown'
	                    label:''
	                  },
	                  yaxis: {
	                    autoscale:true,
	                    label:'DOWNTIME',
	                    
	                    labelOptions: {
	                        fontSize: '6pt'
	                      }
	                      
	                  },
	                  y2axis: {
	                    autoscale:true,
	                    label:'CUMULATIVE_DOWNTIME',
	                    labelOptions: {                       
	                        fontSize: '6pt'
	                      }
	                  }
	                }
	              });
	  }
	
		
</script>
 <div id="mchbreakdown" style="height:400px; width:500px;float: left;margin:20px;text-align:center;">
 <img alt="" src="images/dashboardloader.gif" style="margin-top:150px"> 
 </div>
 
  <div id="abnormalityType" style="height:400px; width:450px;float: left;margin: 20px;text-align:center;">
 <img alt="" src="images/dashboardloader.gif" style="margin-top:150px">
 </div>
 
 <div class="clearfix"></div>
 
 <div id="oeeanalysis" style="height:400px; width:450px;float: left;margin: 20px;text-align:center;" class="jqplot-target">
 <img alt="" src="images/dashboardloader.gif" style="margin-top:150px">
 </div>
  
 <div id="kznvwidntfvscpltd" style="height:400px; width:500px;float: left;margin: 20px;text-align:center;">
 <img alt="" src="images/dashboardloader.gif" style="margin-top:150px">
 </div>
 
  <div id="preventmaint" style="height:400px; width:400px;float: left;margin: 20px;text-align:center;">
 <img alt="" src="images/dashboardloader.gif" style="margin-top:150px">
 </div>
 
  <div id="breakdown" style="height:400px; width:400px;float: left;margin: 20px;text-align:center;">
 <img alt="" src="images/dashboardloader.gif" style="margin-top:150px">
 </div>