
<script type="text/javascript">
		jQuery.noConflict();
//		var mygrid = jQuery("#list");
		jQuery(document).ready(function(){
			viewGrid("HseAbnSummary_input.hseabnSmrRpt");  										
		});
		
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{				
				processGrid(url,filterString,"list","pager","","dblClickRow","callColSpan");
			}	
		}

		function validateFilterSelection(filterString){
			if( filterString != null)
			{	
				if( ! checkFilterValueExist(filterString, "dtFromDate"))
				{
					alert("Enter From date");
					return false;
				}
				else if( ! checkFilterValueExist(filterString, "dtToDate"))
				{
					alert("Enter To date");
					return false;
				}
			}	
			return  true;
		}
		
		function callColSpan(colModel,ths)
		{			  		  
			  cmi=0, tr = "<tr>", skip = 0;             
			  colSpanHeader(colModel,ths,cmi,tr,skip);	
		}

		function colSpanHeader(colModel,ths,cmi,tr,skip)
		{	
			    for(var i=0;i<colModel.length;i++) {
					cmi = colModel[i];
					//alert(cmi.name);
					if (cmi.name == 'Hseabnheader') {
						if (skip === 0) {
		                  	  jQuery(ths[i].el).attr("rowspan", "2");
		                    }else {
		                        skip--;
		                    }
               		 }
       	     		else {  		
	         		 // jQuery(ths[i].el).attr("colspan", "2"); 		
					 // tr += '<th class="ui-state-default ui-th-ltr"  role="columnheader">Identified</th><th class="ui-state-default ui-th-ltr"  role="columnheader">Removed</th>';
					 if (cmi.name == 'citIdentified') {						 	 
						      tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">CIT</th>';	                         
					  }
					 if (cmi.name == 'oitIdentified') {
							  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">OIT</th>';
					  }
					 if (cmi.name == 'mpIdentified') {
							  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">MP</th>';
					  }
					 if (cmi.name == 'miIdentified') {
						  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">MI</th>';
				  	  }
					 if (cmi.name == 'greenIdentified') {
						  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">GREEN TAG</th>';
				  	  }
					 skip = 2;
	                      // because we make colspan="3" the next 2 columns should not receive the rowspan="2" attribute
       	   	  }
				  }
	            tr += "</tr>";
		        //mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
	            mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
			}

			jQuery( "#back" ).click(function() {
				var actionPart = document.getElementById('hiddenUrl').value;					
				var fromDate = jQuery('#fromDate').datebox('getValue');
				var toDate = jQuery('#toDate').datebox('getValue');	
				var rowData = jQuery("#list").getDataIDs();
				var drillValue = jQuery("#list").jqGrid('getCell', rowData[0], 'FieldID');			
				if(drillValue.substr(0,3) != 'CMP')
				{		
					var dataString = '?compid='+ compid +'&dtFromDate='+ fromDate +'&dtToDate='+ toDate  +'&drillValue='+ drillValue +'&drillFlag=b';
					jQuery("#list").GridUnload();
					viewGrid(actionPart,dataString);
				}			
			});		

			function dblClickRow(id)
			{
				var rowData = jQuery("#list").jqGrid('getRowData',id);																								
				var actionPart = document.getElementById('hiddenUrl').value;													
				var fromDate = jQuery('#fromDate').datebox('getValue');
				var toDate = jQuery('#toDate').datebox('getValue');	
				var selId = rowData.FieldID;
				var headers = rowData.Hseabnheader;
				
																							
				if(selId.substr(0,3) != 'MCH')
				{
					var dataString = '?compid='+ compid +'&dtFromDate='+ fromDate +'&dtToDate='+ toDate  +'&drillValue='+ selId +'&drillFlag=f'+'&headers='+headers;
					//jQuery("#list").GridUnload();
					viewGrid(actionPart,dataString);
				}
			}

	</script>
	
	<div id="wrapperRpt">
		 <div style="float:right;padding-right:40px;">
		 	<input type="button" id="back"  class="easyui-button"  value="Back" />
		 </div>
		 <br/><br/>
	<!-- <div class="main-cntborder">  -->
		 <div style=""> 
				 <table id="list" style="width:100%"><tr><td/></tr></table>
				 <div id="pager"></div>
		 </div>
		 </div>
	<!-- 	 
	</div>
	 -->