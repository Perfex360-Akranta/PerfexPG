a<!--  Author ManiKandan-->
<script><!--
jQuery(document).ready(function(){	

	viewGrid("ImpprojSht_input.ipsrpt","");

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Improvement Project Sheet Report";
		processGrid(url,filterString,"list","pager",tableCaption,"OpnimpexlTemp","loadComFunction");
		//jQuery("#list").setGridParam({url:"EqpReport_view.eqm"+dataString,dataType: "json" }).trigger('reloadGrid');
		
	}	
}

function validateFilterSelection(filterString){
	return  true;
}
function OpnimpexlTemp(id)
{ 
	
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	
	var kaizenId = rowData.ImprovementNo;
	//alert("kaizenId          :"+kaizenId);
    var Id = id;
    
    window.open("ImpprojSht_view.ipsrpt?kaizenId="+kaizenId);
 }
function loadComFunction(ids)
{
	var ids =  jQuery("#list").getDataIDs();
	 // alert(ids);
	  for (var i = 1; i<ids.length; i++) 
	  {		
		   if((jQuery("#list").jqGrid('getCell',ids[i],'Impl'))== 'A' )
	      {
    	     // alert("hkdh   hhhh   :"+jQuery("#list").jqGrid('getCell',ids[i],'Impl'));
  		      jQuery("#list").setCell(ids[i], 'Impl', ' ', {'background-color':'#FF8080'});
  		    //jQuery("#list").setCell(ids[i], 'HD', '');
	      }	  
		   else if((jQuery("#list").jqGrid('getCell',ids[i],'Impl'))== 'C' )
		      {
	    	      //alert(jQuery("#list").jqGrid('getCell',ids[i],'HD'));
			       jQuery("#list").setCell(ids[i], 'Impl', ' ', {'background-color':'#FFC0FF'});
		      }   
		   else if((jQuery("#list").jqGrid('getCell',ids[i],'Impl')).trim() == '' )
		      {
	    	      //alert(jQuery("#list").jqGrid('getCell',ids[i],'HD'));
	    	      //jQuery("#list").jqGrid('setCell',ids[i],'HD') = 'N/A';
			       jQuery("#list").setCell(ids[i], 'Impl', 'N/A');
		      }      	
		   if((jQuery("#list").jqGrid('getCell',ids[i],'HD'))== 'A' )
	      {
    	      //alert(jQuery("#list").jqGrid('getCell',ids[i],'HD'));
  		      jQuery("#list").setCell(ids[i], 'HD', ' ', {'background-color':'#FF8080'});
  		    //jQuery("#list").setCell(ids[i], 'HD', '');
	      }
	      
		  else if((jQuery("#list").jqGrid('getCell',ids[i],'HD'))== 'C' )
	      {
    	      //alert(jQuery("#list").jqGrid('getCell',ids[i],'HD'));
		       jQuery("#list").setCell(ids[i], 'HD', ' ', {'background-color':'#FFC0FF'});
	      }
		  else if((jQuery("#list").jqGrid('getCell',ids[i],'HD')).trim() == '' )
	      {
    	      //alert(jQuery("#list").jqGrid('getCell',ids[i],'HD'));
    	      //jQuery("#list").jqGrid('setCell',ids[i],'HD') = 'N/A';
		       jQuery("#list").setCell(ids[i], 'HD', 'N/A');
	      }
		 
	  }
	  /*  var rows= jQuery("#list").jqGrid('getRowData');   
    //alert(row);
    for (var i = 1; i<rows.length; i++) 
	  {		
  	  	  
    	  var row=rows[i];
    	  
    	 jQuery("#list").setCell(row, 'Impl', '', { 'background-color': 'red' });
    	  if((jQuery("#list").jqGrid('getCell',rows[i],'Impl'))== 'A' )
	      {
    	      alert(jQuery("#list").jqGrid('getCell',rows[i],'Impl'));
  			  jQuery("#list").setCell(rows[i], 'Impl', '', {'background-color':'#FF8080'});
	      }
    	  else if((jQuery("#list").jqGrid('getCell',rows[i],'Impl'))== 'C' )
	      {
    	      alert(jQuery("#list").jqGrid('getCell',rows[i],'Impl'));
		       jQuery("#list").setCell(rows[i], 'Impl', '', {'background-color':'#FFC0FF'});
	      }

	  }	  */  	

	  //var ids =  jQuery("#list").getDataIDs();
	 /* alert(ids);
	  for (var i = 1; i<ids.length; i++) 
		  {			    	
	      alert("Val :"+jQuery("#list").jqGrid('getCell',ids[i],'Impl'));
	    	
	            if((jQuery("#list").jqGrid('getCell',ids[i],'Impl'))== 'A' )
	    	      {
		    	      alert(jQuery("#list").jqGrid('getCell',ids[i],'Impl'));
	      		jQuery("#list").setCell(ids[i], 'Impl', '', {'background-color':'#FF8080'});
	    	      }
	            else if((jQuery("#list").jqGrid('getCell',ids[i],'Impl'))== 'C' )
	    	      {
		    	      alert(jQuery("#list").jqGrid('getCell',ids[i],'Impl'));
      		       jQuery("#list").setCell(ids[i], 'Impl', '', {'background-color':'#FFC0FF'});
	    	      }
	     	 }	*/
          }									  

        </script>
        <form id="Excelview" method="post" action="ImpprojSht_view.ipsrpt"> 	
      <div style="padding-left:120px;">  <img src="images/Improprojsht.bmp"/></div> 
	<table id="list" ></table>
	<div id="pager"></div>
	 
	 <input type="hidden" id="ipsId"/>
	</form>
	