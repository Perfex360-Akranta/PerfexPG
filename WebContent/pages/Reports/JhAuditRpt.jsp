

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnJhAuditRptPrevDataUrl').val();
			  setLoadFormCallBackFrmId("MomActionPlanGrid");
			  invokeAfterLoadFormCallBack();
			  
			 /* if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid(url,"&q=2");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=2");*/	

              
			  
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
		function MomActionPlanGrid_afterLoadCallBack(){ 
			toggleCommonFilter();    
			 }
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
			
                 var maintype=jQuery('#hdnmaintype').val();
                 if(maintype.trim().length>0)
			         filterString+="&maintype="+maintype;
			     
				 url = url.replace("_getData.JhAuditRpt","_input.JhAuditRpt");
				//processGridnew(url,filterString,"list","pager","Customer Complaint Plotting","fillForm","","CustCompPlotGrid");
				//alert("url"+url);
				processGridnew(url,filterString,"list","pager","Jh Audit Report","openYYExcel","","fillForm");
		
				return true;	
			}
			return false;	
		}


		function openYYExcel(id){ 
			
			return false;
			var rowData = jQuery("#list").jqGrid('getRowData',id);

			var rowId = rowData.Masterid;

		   //alert(rowId);
			window.open("JhAuditReport_view.JhAuditRpt?rowId="+rowId);
			
				
		}
		function validateFilterSelection(filterString){
			return true;
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
				  var url = jQuery('#hiddenUrl').val();
				  if(url == "JhauditActionPlanScore_input.JhAuditRpt")
					return false;
				  else
				 	jQuery("#trendYYnotes").css('display','block');
		}	
</script>
<form>
<div id="wrapperRpt"> 
	<div style="">
	<!-- 
 <label id="trendYYnotes"  class=""   style="font-weight: bold; padding-left:-4px; margin-top:2px;display: none;"> Click on Data Row to view Details</label>
	-->
	<div style="height: 10px;"></div>
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id=hdnJhAuditRptPrevDataUrl name="hdnJhAuditRptPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id=hdnmaintype name="hdnmaintype" value="${requestScope.maintype}" />
</form>