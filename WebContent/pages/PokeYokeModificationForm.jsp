<!-- Created By: Siddharth.A -->
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();

			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			
			if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid(url,"?q=1&firstClick=Y");
			else
				{ viewGrid(unescape(prevDataUrl),"q=1");}
			
		});

		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{
				var tableCaption = "Poka Yoke Modification";
				processGridnew(url,filterString,"list","pager",tableCaption,"doubleClickGrid");
				return true;
			}	
		}

		function validateFilterSelection(filterString){
			return  true;
		}	

		
	    		
		function doubleClickGrid(rowid)
		{
			var forwardData = jQuery('#hiddenUrl').val();
			
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			var mode;
			if(forwardData=="PokaYoke_view.pky")
				mode="view";
			else
				mode="modify";
				
				navigateToNextForm("PokaYoke_input.pky?pkymKeyid="+ rowid+"&mode="+mode+"&closeOnSave=true&filterButton=false","",null,{"filterString":url});
			
		}
		
</script>


<div id="wrapperRpt">
	   
	 <div class="clear"></div>	 
	  	
				 <table id="list" style="width:100%"><tr><td/></tr></table>
				 <div id="pager"></div>
				 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
				 <input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>
