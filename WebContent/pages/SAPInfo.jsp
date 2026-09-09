<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){
		var url = jQuery('#hiddenUrl').val();
		var mode = "VIEW";
		var dataString ="";	
		viewGrid(url);												
	});

	function viewGrid(url)
	{	
		processGridnew(url,"&q=2","list","pager","","docDoubleClick");
		return true;	
	}	
</script>
<form id="frmSAPInfo">	
	<div id="wrapperRpt" style="padding-left:1%;">
		<div style="padding-left:0%">
		<span style="padding-left:10px; ">Double Click on row to input/view details</span>
		</div>
		<div class="clear" ></div>
		<div style="margin-top:-25px\9;">
		<label class="notes"   style="font-weight: bold; padding-left:0px; " > ${requestScope.DoubleClick}</label>
		<div style="margin-top:6px;margin-top:10px\9;">
		<table id="list" ></table>
		<div id="pager"></div>
		</div>
		
		</div>
	</div>	
</form>