<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){  										
		});
		function viewGrid(actionPart,dataString)
		{		
			//alert(dataString);	
			var getDataUrl ="";
			var getColumnUrl = actionPart.replace('view','getCol');
			if( dataString != null &&  dataString !="")
				getDataUrl = actionPart.replace('view','getData');
			 jQuery.ajax( {
					       type: "POST",
					       url: getColumnUrl,
					       data: dataString,
					       dataType: "json",
					       success: function(result)
				           {
					           	colN = result.colNames;
					            colM = result.colModel;
							    colD = result.colData;
								colModel=0, i=0, cmi=0, tr = "<tr>", skip = 0, ths=0;
								mygrid = jQuery("#list");	
					            //mygrid.GridUnload();
							   // displayGrid(colN,colM,colD,colModel,i,cmi,tr,skip,ths,actionPart,dataString);		
					            mygrid.jqGrid({
								            	/*jsonReader : {
								            		repeatitems: true,
													id: "0"
								                },
												datatype: "jsonstring",	*/					
												//datastr : colD,
												url:getDataUrl+dataString,
								    			datatype: 'json',
												colNames: colN,
												//colModel: colM,
												colModel:[ {name:'company',index:'company',editable:false, width:400},
														   {name:'abnidentfcn',index:'abnidentfcn',editable:false, width:100},	
												           {name:'abnRemoved',index:'abnRemoved',editable:false, width:100},	
												           {name:'redidentfcn',index:'redidentfcn',editable:false, width:100},	
												           {name:'redRemoved',index:'redRemoved',editable:false, width:100},	
												           {name:'whiteidentfcn',index:'whiteidentfcn',editable:false, width:100},	
												           {name:'whiteRemoved',index:'whiteRemoved',editable:false, width:100},
												           {name:'fieldId',index:'fieldId',editable:true,editrules:{required:true, edithidden:true}, hidden:true},																
														],
												rowNum:1000,
												rowList:[500,1000,1500],
												//rownumbers: true,
												shrinkToFit:false,
												pager: '#pager', 
												sortname: 'id',
												viewrecords: true,
												sortorder: "asc", 
												caption:'Summary of Abnormality - '+result.colNames[0].toUpperCase()+'WISE',
												width:1050,
												height:350,
												ondblClickRow: function(id){
													var rowData = jQuery("#list").jqGrid('getRowData',id);													
													var myLoc = document.getElementById('hiddenUrl').value;
													var actionPart = myLoc.replace('input','view');
													var fromDate = jQuery('#fromDate').datebox('getValue');
													var toDate = jQuery('#toDate').datebox('getValue');	
													var selId = rowData.fieldId;
													//alert(selId);
													if(selId.substr(0,3) != 'MCH')
													{											
														var dataString = '?compid='+ compid +'&dtFromDate='+ fromDate +'&dtToDate='+ toDate  +'&drillValue='+ selId;
														jQuery("#list").GridUnload();
														viewGrid(actionPart,dataString);
													}
													//jQuery("#list").setGridParam({url:dataString,dataType: "json" }).trigger('reloadGrid');											
												}
											});			          
								  colModel = mygrid[0].p.colModel;
					              ths = mygrid[0].grid.headers;
								  colSpanHeader(colModel,ths);				       
				           }
					});			
		}

		function colSpanHeader(colModel,ths)
		{
			 for(i=0;i<colModel.length;i++) {
                cmi = colModel[i];
				if (cmi.name == 'company') {
                    if (skip === 0) {
                  	  jQuery(ths[i].el).attr("rowspan", "2");
                    }else {
                        skip--;
                    }
                }
       	     else {           		  		
	         		 // jQuery(ths[i].el).attr("colspan", "2"); 		
					 // tr += '<th class="ui-state-default ui-th-ltr"  role="columnheader">Identified</th><th class="ui-state-default ui-th-ltr"  role="columnheader">Removed</th>';
					 if (cmi.name == 'abnidentfcn') {
						      tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">Abnormalities</th>';
	                         
					  }
					 if (cmi.name == 'redidentfcn') {
							  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">Red</th>';
					  }
					 if (cmi.name == 'whiteidentfcn') {
							  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">White</th>';
					  }
					 skip = 2;
	                      // because we make colspan="3" the next 2 columns should not receive the rowspan="2" attribute
				  }
			  }
            tr += "</tr>";
            mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
		}

		jQuery( "#back" ).click(function() {
			var myLoc = document.getElementById('hiddenUrl').value;
			var actionPart = myLoc.replace('input','view');			
			var fromDate = jQuery('#fromDate').datebox('getValue');
			var toDate = jQuery('#toDate').datebox('getValue');	
			var rowData = jQuery("#list").getDataIDs();
			var drillValue = jQuery("#list").jqGrid('getCell', rowData[0], 'fieldId');			
			if(drillValue.substr(0,3) != 'CMP')
			{		
				var dataString = '?compid='+ compid +'&fromDate='+ fromDate +'&toDate='+ toDate  +'&drillValue='+ drillValue;
				jQuery("#list").GridUnload();
				viewGrid(actionPart,dataString);
			}			
		});		

</script>
<form>
<div id="wrapperRpt">
	 <div style="float:right;padding-right:40px;">
	 	<input type="button" id="back"  class="easyui-button"  value="Back" />
	 </div>
	 <br/><br/>
<div class="main-cntborder">
	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	 </div>
</div>
</div>
</form>