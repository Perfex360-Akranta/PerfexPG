  
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
		{
		jQuery(document).ready(function()
				{
				var url = jQuery('#hiddenUrl').val();
				//alert("dsf"+jQuery('#hien').val());
				var mchId =jQuery('#hien').val();
				//jsp:useBean id="jhclitmachineID" scope="request" class="JHCLIT.java" />
				// Save and get a request-scoped value
				
			//alert( "value"+jQuery("#cmbClisMachineid").val());
				processGridnew("jhClit_input.jhclit","?q=2&mchId="+mchId,"list","pager","","dblclick");
				
				});
		});

function dblclick(id)
{
	var rowData = jQuery("#list").jqGrid('getRowData',id);																								
	var availId = rowData.keyId;//alert(availId);
	var availability = rowData.Availability;
	//alert("tick "+availability);
	//var mchId=jQuery('#hien').val();
	
	var filterData = '?q=2&assmid='+availId;
	    jQuery('#asshidn').val(availId);
	   //alert("2ndfrm"+filterData);
	    jQuery("#jhclitcountgrid").load("jhClit_grid3.jhclit"+filterData, function(response, status, xhr)
	   		 {
	   			  if (status == "error") {
	   				    var msg = "Sorry but there was an error: ";
	   				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
	   				  }
	   			  else if(status == "success")
	   			  {
	   					
	   				  //jQuery("#Employee").show();
	   				    //alert("keyId"+keyId);
	   				 //jQuery("#JHCLIT").load("jhClit_grid3.jhclit");
	   			  }
	   		 });
}
function actionFormatter(cellvalue, options, rowObject)
{
	if(cellvalue == 'Y')
	 {
   		return '<span style="font-size:15px;color:#8B0000;margin-left:250px;"> &#10003;</span>';
     }
	else  {
		return  " ";
	 }	
}

</script>
<form name="frmJhClitmcharea" id="frmJhClitmcharea" action="" method="post">


	<div style="" id="JHCLITmachinearea"> 
	<input type=hidden id="second" class="easyui-text" value="b"/>
<table id="list" ></table>
<div id="pager"></div>
	
</div>
</form>