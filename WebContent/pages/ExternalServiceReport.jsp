<script type="text/javascript">


		jQuery.noConflict();

		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();
			viewGrid(url,"");

		});
		
		
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{
				var tableCaption = "External Service Report";
				processGrid(url,filterString,"list","pager",tableCaption);
			}	
		}

		function validateFilterSelection(filterString){
			return  true;
		}
		
</script>

<form>
<div id="wrapperRpt">
	<div style=""> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
</form>