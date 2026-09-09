<!--//  Author ManiKandan-->
<script type="text/javascript"><!--
		jQuery.noConflict();
//		var mygrid = jQuery("#list");
		jQuery(document).ready(function(){
			viewGrid("Horizon_input.hsdrpt","");  										
		});
		
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{				
				processGrid(url,filterString,"list","pager","","","","callColSpan");
				//processGrid(url,filterString,"list","pager",tableCaption,"OpnimpexlTemp","loadComFunction");
			}	
		}

		function validateFilterSelection(filterString){
		
			return  true;
		}
		
		function callColSpan(colModel,ths)
		{			  		  
			  cmi=0, tr = "<tr>", skip = 0;             
			  colSpanHeader(colModel,ths,cmi,tr,skip);	
		}

		function colSpanHeader(colModel,ths,cmi,tr,skip)
		{	//alert("Inside");
			    for(var i=0;i<colModel.length;i++) {
					cmi = colModel[i];
					//alert(cmi.name)
					if (cmi.name == 'HDImprovementSummary') {
						/*if (skip === 0) {
		                  	  jQuery(ths[i].el).attr("rowspan", "2");
		                    }else {
		                        skip--;
		                    }*/
						//alert("inside if");
               		 }
       	     		else {
    					//alert(cmi.name);
	         		 // jQuery(ths[i].el).attr("colspan", "2"); 		
						 // tr += '<th class="ui-state-default ui-th-ltr"  role="columnheader">Identified</th><th class="ui-state-default ui-th-ltr"  role="columnheader">Removed</th>';
						 
						 if (cmi.name == 'hdimprovementsummary') {			//alert("inside if of HD"+cmi.name);			 	 
						      tr += '<th class="ui-state-default ui-th-ltr" colspan="26" role="columnheader">Horizontal Deployment Improvement Summary</th>';	                         
					 		 }
						 else if (cmi.name == 'horizontaldeployment') {			//alert("inside if of HD"+cmi.name);			 	 
							      tr += '<th class="ui-state-default ui-th-ltr" colspan="6" role="columnheader">Horizontal Deployment</th>';	                         
						  }
						 else if (cmi.name == 'actualstatus') {
								  tr += '<th class="ui-state-default ui-th-ltr" colspan="2" role="columnheader">Actual Status</th>';
						  }
						
						 skip = 3;
		                      // because we make colspan="3" the next 2 columns should not receive the rowspan="2" attribute
		                    //  alert(tr);
						
					  }
			  }
            tr += "</tr>";
	        //mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
            mygrid.closest("div.ui-jqgrid-view").find("table.ui-jqgrid-htable > thead").append(tr);
		}

		

</script>
	
	 <br/><br/>

	 <div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	 </div>
