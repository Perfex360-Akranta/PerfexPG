<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
		{
		jQuery(document).ready(function()
				{
				var url = jQuery('#hiddenUrl').val();
				//alert("dsf"+jQuery('#hien').val());
				var mchId =jQuery('#hien').val();//alert(mchId);
				var assId =jQuery('#asshidn').val();
				
				var filterData = '?q=2&mchId='+mchId;
				filterData += '&assmid='+assId;
			  //alert( "value"+filterData);
				processGridnew("jhClit_input.jhclit","?q=2&filterData="+filterData,"list","pager","","dblclick");
				
				});
		});

function dblclick(id)
{
	var rowData = jQuery("#list").jqGrid('getRowData',id);																								
	var stdId = rowData.keyId;
	jQuery('#stdhidn').val(stdId);
	  jQuery("#jhclitcountgrid").load("jhClit_frm.jhclit","?q=2&stdId="+stdId, function(response, status, xhr)
				 {
					  if (status == "error") {
						    var msg = "Sorry but there was an error: ";
						    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
						  }
					  else if(status == "success")
					  {
						
					  }
				 });																								
	
}
function new_standard(){

	 jQuery("#jhclitcountgrid").load("jhClit_frm.jhclit", function(response, status, xhr)
			 {
				  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
				  else if(status == "success")
				  {
					
				  }
			 });		
}

</script>
<div style="float:right">
<input type="button" class ="easyui-button" value="New Standard" id="bck" style="float:right;padding-top:5px;"onclick='new_standard();'/><br><br>
</div>
<form name="frmjhclitactivitygrid" id="frmjhclitactivitygrid" action="" method="post">

<div id="stdgrid" style="padding-top:25px;" >
<input type=hidden id="third" class="easyui-text" value="c"/>
<table id="list" ></table>
<div id="pager"></div>
</div>
</form>