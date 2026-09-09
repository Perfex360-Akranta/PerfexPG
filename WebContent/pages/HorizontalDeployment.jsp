	<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();
			jQuery('#hdnbdmode').val(url.indexOf('&bdmmode'));
			var flid=jQuery('#hdnLoginFlid').val();
			
						
			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			if( prevDataUrl == null || prevDataUrl.length <=0){		
                var empkzn=jQuery('#hdnempkzn').val();
                var fromdate=jQuery('#hdnfromdate').val();
                var todate=jQuery('#hdntodate').val();
                var frommonth=jQuery('#hdnfrommonth').val();
                var tomonth=jQuery('#hdntomonth').val();
                if(empkzn.trim().length>0)
					viewGrid(url,"&fromdate="+fromdate+"&todate="+todate+"&frommonth="+frommonth+"&tomonth="+tomonth);
				else
				   viewGrid(url,"&flid="+flid);
				
			}
			else{ 
				viewGrid(unescape(prevDataUrl),"&firstClick=Y&flid="+flid);
			}
			
		});
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{
				var tableCaption = "Kaizen Idea Sheet";
				
				var hdnFrmMode = jQuery('#hdnFrmMode').val();
				
				//   filterString+="&flid="+flid;//"&q=1&firstClick=Y&mode=modify"+
				
				var mode=jQuery('#hdnFrmMode').val();
				var empkzn=jQuery("#hdnempkzn").val();
				var empKeyId=jQuery("#hdnempKeyId").val();
				var flid=jQuery("#hdnflid").val();

				if(url.indexOf('?')>0 && empkzn.length>=0)
			    	url=url.substring(0,45);
		    			
				if(mode.trim().length>0)
					mode=jQuery('#hdnFrmMode').val();
				else {                         
					if (url=="KaizenView_input.kaizen")
						mode="view";
					else
						mode="modify";
				}

				
				
				filterString+="&mode="+mode+"&empKeyId="+empKeyId+"&flid="+flid+"&empkzn="+empkzn;

			    //filterString+="&mode="+mode+"&empKeyId="+empKeyId;
		    	
			    if(url.indexOf('?')>0 && empkzn.length>=0)
			    	url=url.substring(0,45);

		    	processGridnew(url,filterString,"list","pager",tableCaption,"doubleClickGrid","","horizonDeployGridOncompleteLoad");
				return true;
			}	
		}

		function validateFilterSelection(filterString){
			return  true;
		}	

		function horizonDeployGridOncompleteLoad()
		{
			var rowIds = jQuery('#list').jqGrid().getDataIDs();
			for(var i=0;i<rowIds.length;i++)
			{
				var cellVal =jQuery('#list').getCell(rowIds[i],"status");
				if(cellVal=="COMPLETED")
				{	
					jQuery("#list").jqGrid('setCell',rowIds[i],"imprvDate","",{'color':'blue'});
					jQuery("#list").jqGrid('setCell',rowIds[i],"imprvno","",{'color':'blue'});
				} 
			}
	    }
	    		
		function doubleClickGrid(rowid)
		{
			var forwardData = jQuery('#hiddenUrl').val();
			var keyid=jQuery('#list').getCell(rowid,"KZBNKEYID"); //IMPRVNO
			var hdkeyid=jQuery('#list').getCell(rowid,"HDNO");
			//alert("The hdKeyid"+hdkeyid);
			var status=jQuery('#list').getCell(rowid,"STATUS");
			var cellId=jQuery('#list').getCell(rowid,'FLNID');
			var idea=jQuery('#list').getCell(rowid,'IDEA');
			//alert(" cellId :: "+cellId);
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
		  //navigateToNextForm("kaizen_input.kaizen?&kznStatus="+status+"&Mode=create"+"&KZNBFlid="+cellId+"&filterButton=false","Kaizen Idea Sheet",null,{"filterString":url});
     	navigateToNextForm("KaizenBankSuggestion_input.kznbnk?&hdkeyid="+hdkeyid+"&HD="+"HD"+"&kznStatus="+status+"&kaizen="+idea+"&flid="+cellId+"&filterButton=false","Suggestion");
		}
		
</script>

<form name="viewImprove" id="viewImprove">
<div id="WrapperRpt">
	<div class="cntborder">
	 <div class="clear"></div>	 
	  	<div style="margin-top: -15px">
				 <table id="list" style="width:100%"><tr><td/></tr></table>
				 <div id="pager"></div>
				 </div>
				 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
				 	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
				 	<input type="hidden" id="hdnempkzn" name="hdnempkzn" value="${requestScope.empkzn}"/>
				 	<input type="hidden" id="hdnempKeyId" name="hdnempKeyId" value="${requestScope.empKeyId}"/>
				 	<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"/>
				 	<input type="hidden" id="hdnfromdate" name="hdnfromdate" value="${requestScope.fromdate}"/>
				 	<input type="hidden" id="hdntodate" name="hdntodate" value="${requestScope.todate}"/>
				 	<input type="hidden" id="hdnfrommonth" name="hdnfrommonth" value="${requestScope.frommonth}"/>
				 	<input type="hidden" id="hdntomonth" name="hdntomonth" value="${requestScope.tomonth}"/>
				 <input type="hidden" id="hdnbdmode" />
		</div>
</div>
</form>		
