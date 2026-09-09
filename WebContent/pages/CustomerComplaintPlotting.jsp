

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnCustCumPlotPrevDataUrl').val();
				
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid("customerCmptPlotting_input.customerComplaintRpt","?q=1");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=1");		
			
		});
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				url = url.replace("_getData.CustomerComplaintRpt","_input.CustomerComplaintRpt");
				//processGridnew(url,filterString,"list","pager","Customer Complaint Plotting","fillForm","","CustCompPlotGrid");
				processGridnew(url,filterString,"list","pager","Customer Complaint Plotting","","","fillForm");
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			fillWithCurrentMonth('dtetoMonth');
			fillWithCurrentMonth('dtefromMonth');
			disableField('frmFilter','dtetoMonth');				
		}
		function fillForm(id)
		{
			 var row = jQuery("#list").jqGrid('getDataIDs');
				//alert("row"+row);
				 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
				// alert("cm"+Object.keys(cm));
				 for(var i=0;i<row.length;i++)
				 {
					/* for(var j=3;j<cm.length;j++)
			     	 {
						  var zeroVal = jQuery("#list").jqGrid('getCell',row[i],cm[j].name);	//alert(zeroVal);		 
						  if(zeroVal != ' ' && zeroVal != '' && zeroVal != null)
						  {
							  if(zeroVal =='0'){
								
						  		jQuery("#list").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#FF8040','font-weight':'bold','font-size':'15px','background-color':'#fff'});
							  }
							  else{
								 
								  jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-weight':'bold','font-size':'12px','background-color':'#89c583'});
							  }
						  }
				    }*/
					    if(i == row.length-1)
						    {
					    	 for(var j=0;j<cm.length;j++)
					     	 {							
						   		jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
					     	 }
						    }
						    
				 }
						
			/*var rowData = jQuery("#list").jqGrid('getRowData',id);			
			var keyId = rowData.keyId;
			alert("keyId"+keyId);
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace("customerCmptPlotting_getData.CustomerComplaintRpt?&","customerCmptPlotting_input.CustomerComplaintRpt?");			
			url = escape(url);				
			navigateToNextForm('customerCmptPlotting_input.CustomerComplaintRpt'+'?filterButton=false&CustKeyid='+keyId,'Customer Plotting',null,{"filterString":url});*/
		}	
</script>
<form>
<div id="wrapperRpt"> 
	<div style="margin-top: -28px"> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id="hdnCustCumPlotPrevDataUrl" name="hdnCustCumPlotPrevDataUrl" value="${requestScope.filterStr}" />
</form>