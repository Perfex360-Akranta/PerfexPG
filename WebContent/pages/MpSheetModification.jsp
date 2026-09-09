<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();

			jQuery('#hdnbdmode').val(url.indexOf('&bdmmode'));

			if(url.indexOf('&bdmmode')>0)	
				jQuery('#btnNewBkng').css("display","block");

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
				
				processGridnew(url,filterString,"list","pager","","doubleClickGrid","","mpsGridOncompleteLoad");
				return true;
			}	
		}

		function validateFilterSelection(filterString){
			return  true;
		}	

		function mpsGridOncompleteLoad()
		{
			var rowIds = jQuery('#list').jqGrid().getDataIDs();
			for(var i=0;i<rowIds.length;i++)
			{
				var cellVal =jQuery('#list').getCell(rowIds[i],"status");
				if(cellVal=="COMPLETED")
				{	
					jQuery("#list").jqGrid('setCell',rowIds[i],"createdDate","",{'color':'blue'});
					jQuery("#list").jqGrid('setCell',rowIds[i],"mpsKeyid","",{'color':'blue'});
				} 
			}
	    }
	    		
		function doubleClickGrid(rowid)
		{
			var forwardData = jQuery('#hiddenUrl').val();
			var status=jQuery('#list').getCell(rowid,"status");
			var rowData = jQuery("#list").jqGrid('getRowData',rowid);	
			var selId = rowData.mpsKeyid;
			
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			var mode="";
			if(forwardData=="MpsSheetView_input.mps")
				mode="view";
			else
				mode="modify";

			navigateToNextForm("MPSheet_input.mps?mpsKeyid="+ selId+"&mode="+mode+"&status="+status+"&closeOnSave=true&filterButton=false",null,{"filterString":url});
			
			
		}

		
		
</script>


<div id="wrapperRpt">
	<div class="cntborder">
	   <table style="margin-top:5px;">
		<tbody>
		<tr>
			<td>
				<span class="bd-countermeasurepending"></span>
			</td>
			<td>
				<label style="color:blue;font-weight: bold">Completed</label>
			</td>
			
			<td style="padding-left: 960px;">
				<input type="button" class="easyui-button" id="btnNewBkng" value="New Booking" style="display: none;" />
			</td>
		  </tr>
		 </tbody>
		</table>
	 
	
	 <div class="clear"></div>	 
	  	
				 <table id="list" style="width:100%"><tr><td/></tr></table>
				 <div id="pager"></div>
				 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
				 	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
				 <input type="hidden" id="hdnbdmode" />
		</div>
</div>		
