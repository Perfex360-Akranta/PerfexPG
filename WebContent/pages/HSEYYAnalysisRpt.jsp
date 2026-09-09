

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnhseYYAnalysisPrevDataUrl').val();
				
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid("hseYYAnalysisReport_input.HseAccTrendRpt","?q=1");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=1");	

			/*  jQuery('#btnGraph').click(function(){
					var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
					var rowData = jQuery("#list").jqGrid('getRowData',rowid);
					var selId = rowData.keyid;
					if(rowid == null || rowid =="")
					{
						
							alert("Select Row To View Graph");
					}  
					else{
						if(checkForZeroes("list",rowid,2)){	
							
						 var url = "hsehseBodyVsAccReportchart.HseAccTrendRpt?rowid=" + selId + "&rownum="+rowid ;
						showGraphData(url);
						}else 
							alert("No Record to View Graph");
					}
				});	*/
			
		});
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				url = url.replace("_getData.HseAccTrendRpt","_input.HseAccTrendRpt");
				//processGridnew(url,filterString,"list","pager","Customer Complaint Plotting","fillForm","","CustCompPlotGrid");
				processGridnew(url,filterString,"list","pager","Why Why Analysis Report","openYYExcel","","fillForm");
		
				return true;	
			}
			return false;	
		}


		function openYYExcel(id){ 
			
			var rowData = jQuery("#list").jqGrid('getRowData',id);

			var rowId = rowData.WWMS_REFDOCNO;
			window.open("whywhyHSEReport_view.HseAccTrendRpt?rowId="+rowId);
			
				
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			//fillWithCurrentMonth('dtetoMonth');
			//fillWithCurrentMonth('dtefromMonth');
			//disableField('frmFilter','dtetoMonth');	
			setTimeout(function() {readOnlyFields('cmbbodyPart');},1200);
			setTimeout(function() {readOnlyFields('cmbinjuryType');},1200);
			setTimeout(function() {readOnlyFields('cmbincidenttype');},1200);			
			setTimeout(function() {readOnlyFields('dteincdntFromDate');},1200);
			setTimeout(function() {readOnlyFields('dteincdntdToDate');},1200);
			setTimeout(function() {readOnlyFields('cboStatus');},1200);
			setTimeout(function() {readOnlyFields('chkSkipLine');},1200);
			setTimeout(function() {readOnlyFields('chkminbox');},1200);
			setTimeout(function() {readOnlyFields('chkmajbox');},1200);
			
			
		}
		function fillForm(id)
		{
			/* var row = jQuery("#list").jqGrid('getDataIDs');
				//alert("row"+row);
				 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
				// alert("cm"+Object.keys(cm));
				 for(var i=0;i<row.length;i++)
				 {
					
					    if(i == row.length-1)
						    {
					    	 for(var j=0;j<cm.length;j++)
					     	 {							
						   		jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
					     	 }
						    }
						    
				 }	*/				
				 jQuery("#trendYYnotes").css('display','block');
		}	
</script>
<form>
<div id="wrapperRpt"> 
	<div style=""> 
	
<label id="trendYYnotes"  class=""   style="font-weight: bold; padding-left:2px; margin-top:-20px;display: none;"> Click on Data Row to view why why Details</label>
	<div style=""></div>
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id=hdnhseYYAnalysisPrevDataUrl name="hdnhseYYAnalysisPrevDataUrl" value="${requestScope.filterStr}" />
</form>