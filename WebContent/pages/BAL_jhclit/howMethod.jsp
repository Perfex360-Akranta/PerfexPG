<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
		{
		jQuery(document).ready(function()
				{
				/*for new standard button hide*/
				jQuery("#newstndard").css('display','none');
				
				});
		});
	//for close grid
		function close_div(){
			
			jQuery( "#toolspop").dialog('close');
			
			
		}

</script>

<form name="frmhowmethodgrid" id="frmhowmethodgrid" action="" method="post">

<div id="toolstree">


<input type="button" class ="easyui-button" value="CANCEL" id="closebtn" style="float:right"onclick='close_div();'/>
<input type="button" class ="easyui-button" value="OK" id="clrbtn" style="float:right"onclick=''/>
</div>
</form>