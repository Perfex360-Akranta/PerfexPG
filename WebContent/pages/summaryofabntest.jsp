<script type="text/javascript">
		jQuery.noConflict();
//		var mygrid = jQuery("#list");
		jQuery(document).ready(function(){
			viewGrid("AbnSummary_input.abnSmrRpt","");  										
		});
		
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{				
				processGrid(url,filterString,"list","pager","","dblClickRow","callColSpan");
			}	
		}

		function validateFilterSelection(filterString){
			if( checkFilterValueExist(filterString, "dtFromDate"))
			{
				
			}
			
			return  true;
		}
		
		function callColSpan(colModel,ths,headernames)
		{			  		  
			  cmi=0, tr = "<tr class='ui-jqgrid-labels' role='rowheader' >", skip = 0;             
			  colSpanHeader(colModel,ths,cmi,tr,skip,headernames);	
		}

		function colSpanHeader(colModel,ths,cmi,tr,skip,headernames)
		{	
			//var tr1 ="<tr>";
			var tr2 ="<tr class='ui-jqgrid-labels' role='rowheader'>";
			/*	tr +=  '<th class="ui-state-default ui-th-ltr"  role="columnheader"></th>';
			 	tr1 +=  '<th class="ui-state-default ui-th-ltr"  role="columnheader">                             </th>';
			*/	
			    for(var i=0;i<colModel.length;i++) {
					cmi = colModel[i];
					if (cmi.name == 'abnHeader') {
						if (skip === 0) {
		                  	  jQuery(ths[i].el).attr("rowspan", "4");
		                  	tr += getLeftColHeaderHtml(headernames[i],cmi.name);
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
		/*					if( i % 2 == 1)
								tr1 += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader"> ' + headernames[i] + '</th>';
		*/						
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
				//jQuery("#list").GridUnload();
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
																											
			if(selId.substr(0,3) != 'MCH')
			{
				var dataString = '?compid='+ compid +'&dtFromDate='+ fromDate +'&dtToDate='+ toDate  +'&drillValue='+ selId +'&drillFlag=f';
				//jQuery("#list").GridUnload();
				viewGrid(actionPart,dataString);
			}
		}

</script>
	 <div style="float:right;padding-right:40px;">
	 	<input type="button" id="back"  class="easyui-button"  value="Back" />
	 </div>
	 <br/><br/>
<!-- <div class="main-cntborder">  -->
	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	 </div>
<!-- 	 
</div>
 -->