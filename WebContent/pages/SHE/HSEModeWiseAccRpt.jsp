

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			 
			  var  prevDataUrl = jQuery('#hdnModeWisePlotPrevDataUrl').val();
			  var dataString ="";
			  if( prevDataUrl == null || prevDataUrl.length <=0)	
				  {					  	
				viewGrid("hseModeWiseAccReport_input.HseAccTrendRpt","?q=1&firstClick=Y&module=Safety");
				  }
			  else
				  {
				 viewGrid(unescape(prevDataUrl),"?q=1");	
				  }
			  jQuery('#btnGraph').click(function(){
					var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
					var rowData = jQuery("#list").jqGrid('getRowData',rowid);
					var selId = rowData.keyid;
					
				
					 
						var url = "hseModeWiseAccReportchart.HseAccTrendRpt?rowid="+rowid+"&rownum=TOTAL" ;
						showGraphData(url);
					
						//if(checkForZeroes("list",rowid,2)){	
							
						// var url = "hseModeWiseAccReportchart.HseAccTrendRpt?rowid=" + rowid + "&rownum="+rowid ;
						 //var url = "AbnTagTrendRpt_getChart.abnRpt?rowid="+rowid+"&chType=column";
						//showGraphData(url);
						
				});	

			  jQuery('#btnPieGraph').click(function(){
					
				  var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
				  var rowData = jQuery("#list").jqGrid('getRowData',rowid);
				  var selId = rowData.keyid;
					
					//if (rowid== null || rowid ==""){
						//rowid=' ';
						//alert("Select Row To View Graph");
				    //}else{
				   
					 var url = "chart.HseAccTrendRpt?rowid="+ selId +"&rownum="; //+ selId; //+ "&rownum="+rowid ;
					 showGraphData(url);
					//}
						
				}); 
			
		});
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	

				filterString +='&module=Safety';
				filterString += '&drillFlag=f';
				jQuery('#hdnFilterString').val(filterString);
				url = url.replace("_getData.HseAccTrendRpt","_input.HseAccTrendRpt");
				//processGridnew(url,filterString,"list","pager","Customer Complaint Plotting","fillForm","","CustCompPlotGrid");
				processGridnew(url,filterString,"list","pager","Mode Wise Data Report","doubleClickGrid","","fillForm");
				
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			
			//fillWithCurrentMonth('dtetoMonth');
			//fillWithCurrentMonth('dtefromMonth');
			setTimeout(function() {readOnlyFields('cmbbodyPart');},1200);
			setTimeout(function() {readOnlyFields('cmbincidenttype');},1200);
			setTimeout(function() {readOnlyFields('cmbincidentno');},1200);
			setTimeout(function() {readOnlyFields('dteincdntFromDate');},1200);
			setTimeout(function() {readOnlyFields('dteincdntdToDate');},1200);
			setTimeout(function() {readOnlyFields('cboStatus');},1200);

			setTimeout(function() {readOnlyFields('cmbEquipmentid');},1200);
			setTimeout(function() {readOnlyFields('cmbeqpGroup');},1200);
			setTimeout(function() {readOnlyFields('cmbCircle');},1200);
			setTimeout(function() {readOnlyFields('cmbMachineRank');},1200);
			setTimeout(function() {readOnlyFields('cmbCostCenter');},1200);
			setTimeout(function() {readOnlyFields('cmbMould');},1200);
			setTimeout(function() {readOnlyFields('chkojtbox');},1200);
			setTimeout(function() {readOnlyFields('chkpybox');},1200);
			setTimeout(function() {readOnlyFields('chkkkbox');},1200);
			setTimeout(function() {readOnlyFields('chketbox');},1200);
			setTimeout(function() {readOnlyFields('chkminbox');},1200);
			setTimeout(function() {readOnlyFields('chkmajbox');},1200);
			//enableFields('cmbemployee');			
		}
		function doubleClickGrid(id){ 
		
			var rowData = jQuery("#list").jqGrid('getRowData',id);			
			var selId = rowData.INJURYMODE;	
			
			if(selId.substr(0,3) == 'EMP'){				
			}	
			else{
				var filterData ="?";		
				var mon = jQuery.cookie("colVal");
				var cellVal = jQuery.cookie("cellContent");
				if(cellVal == '0')
				{
					alert("No Data Found");
					return false;
				}
				if(mon == "InjuryName")
					return false;
			 	filterData += '&cmbinjuryType='+ selId+'&drillFlag=f&module=Safety';
			 	filterData += '&dtFromMonth='+mon;
				filterData += '&dtToMonth='+mon;
		
				var url = jQuery('#hiddenUrl').val();
				var filter=jQuery('#hdnFilterString').val();		
				filter = filter.replace("&firstClick=Y","");
				filter = filter.replace("?q=1","");	
				
				var jsonstr = '{"cmbinjuryType":"'+ selId + '" ,"dtFromMonth":"'+mon+'","dtToMonth":"'+mon+'"}';
				var perstData = jQuery.parseJSON(jsonstr);	
			
				navigateToNextForm("AccincidentView_input.accIncRpt"+'?filterString='+escape(filterData),"",null,perstData);
			 	//processGridnew(url,filterData,"list","pager","Mode Wise Data Report","doubleClickGrid","","fillForm");
				}				
		}
		function fillForm(id)
		{		
			 jQuery("#list").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){				 
				 var colm = jQuery("#list").jqGrid ('getGridParam', 'colModel');
				 selId = colm[iCol].name;
				 jQuery.cookie("colVal",selId);
				 jQuery.cookie("cellContent",cellcontent);
				 if(selId == "TOTAL")
					jQuery.cookie("colVal","");
				 
		  		}	});
			 
			var row = jQuery("#list").jqGrid('getDataIDs');
			var rowIds = jQuery("#list").getDataIDs();
			if(rowIds.length>=0)
				{
					var parentId =  jQuery("#list").jqGrid('getCell', rowIds[0], 'INJURYMODE');
					
					if(parentId.substr(0,3) != 'INJ'){
						hideShowBack(true);
					}
				}
				
				
						
				 for(var i=0;i<row.length;i++)
				 {
										
					if(i == row.length-1)
					 {
					
						 for(var j=0;j<cm.length;j++)
					 	 {							
							jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
							
					 	 }
					 }
						    
				 }	
				
				setTotalRowCss('list');
				
				jQuery("#trendnotes").css('display','block');					 				
				 jQuery("#trendgraph").css('display','block');
		}

		function list_onProcessGridBack(){			
					var url = jQuery('#hiddenUrl').val();					
					var rowIds = jQuery("#list").getDataIDs();
		
					var parentId =  jQuery("#list").jqGrid('getCell', rowIds[0], 'keyid');
					var dataString = 'drillFlag=b&module=Safety';
					
					var filter=jQuery('#hdnFilterString').val();	
				
					filter = filter.replace("&firstClick=Y","");
					filter = filter.replace("?q=1","");	
					filter = filter.replace("&drillFlag=f","");	
											
					dataString+=filter;	
					
					processGridnew(url,dataString,"list","pager","Mode Wise Data Report","doubleClickGrid","","fillForm");			
				}

				
</script>
<form>
<div id="wrapperRpt" style="margin-top:20px;" > 
<table>
       <tr>
       
       <td>
       <div>
	      <input id="btnGraph"  class="easyui-button"  type="button" value="Graph"/>
	      <input id="btnPieGraph" class="easyui-button"  type="button" value="Pie-Graph"/>
	   </div>
	   </td>
	   <td><label id="trendnotes"  style="font-weight: bold; padding-left:0%; margin-top:0px;" >Double Click on Injury Name to view Employee Details</label>
       <label id="trendgraph"   style="font-weight: bold; padding-left:20px;margin-top:2px;" > ${requestScope.GraphMsg}</label></td>
       <td>
	</td>
       </tr>
       <tr>
       <td colspan="3">
       <div style=""></div>
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
			 </td>
       </tr>
</table>
 
	


	
	
</div>
<input type="hidden" id="hdnModeWisePlotPrevDataUrl" name="hdnModeWisePlotPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
</form>