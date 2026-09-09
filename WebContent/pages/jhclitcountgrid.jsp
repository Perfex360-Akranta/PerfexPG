<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
		{
		jQuery(document).ready(function()
		 {
		   
		   
			processGridnew("jhClit_input.jhclit","","list","pager","JH(CLIT) Standard","nxtgrid");
			
		  });

		
		});

		function nxtgrid(id) 
		{
	    //alert('Handler for .dblclick() called.');
	  	  	var rowData = jQuery("#list").jqGrid('getRowData',id);																								
			var selId = rowData.machineId;
		//	alert("amk  "+selId);
			//alert("hidden feild  :"+selId);
			   jQuery('#hien').val(selId);
			var filterData = 'machineID='+ selId;//alert("1stfrm"+filterData);
		  jQuery("#jhclitcountgrid").load("jhClit_mcharea.jhclit?q=2&mchId="+filterData, function(response, status, xhr)
		 {
			  if (status == "error") {
				    var msg = "Sorry but there was an error: ";
				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
				  }
			  else if(status == "success")
			  {
					
				  //jQuery("#Employee").show();
				   jQuery('#hien').val(selId);
				   jQuery("#cmbClisMachineid").combobox('setValue',selId);
				    var keyId=jQuery('#cmbClisMachineid').val();
				    //alert("keyId"+keyId);
				//  jQuery("#JHCLIT").load("jhClit_view.jhclit");
			  }
		 });
	}


</script>
<form name="frmjhclitcountgrid" id="frmjhclitcountgrid" action="" method="post">
	
<div id="jhclitmachinearea" >
	<input type=hidden id="first" class="easyui-text" value="a"/>
<table id="list" ></table>
<div id="pager"></div>
</div>
</form>