

<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnViewBDPrevDataUrl').val();

				if( prevDataUrl == null || prevDataUrl.length <=0)
				{		
					if(url.indexOf('unplanned') >=0)
						viewGrid(url,"?q=1&maintMode=upm");
					else
						viewGrid(url,"?q=1");
				}
				else{
					//alert("Prev data " +unescape(prevDataUrl));
					if(prevDataUrl.indexOf('unplanned') >=0)
						viewGrid(unescape(prevDataUrl),"?q=1&maintMode=upm");
					else
						viewGrid(unescape(prevDataUrl),"?q=1");
					//viewGrid(unescape(prevDataUrl),"&q=1");
				}	
						 // viewGrid(url,dataString);	


				if(url == "brkdown_view.Bbrdn")
					jQuery('#btnNewBooking').css('display','none');
				else if(url == "unplanned_view.Bbrdn")
					jQuery('#btnNewBooking').css('display','none');
			
		});
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				url = url.replace("_getData.Bbrdn","_view.Bbrdn");
				processGridnew(url,filterString,"grdBdlist","pager","BreakDown Master","grdBdlist_fillForm","","breakdownMstGrid");
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){
			return true;
		}
		function grdBdlist_fillForm(id)
		{
			var rowData = jQuery("#grdBdlist").jqGrid('getRowData',id);			
			var keyId = rowData.keyId;
			var url = jQuery("#grdBdlist").jqGrid('getGridParam', 'url');
			url = url.replace("brkdown_getData.Bbrdn?&","brkdown_view.Bbrdn?").replace("unplanned_getData.Bbrdn?&","unplanned_view.Bbrdn?");			
			url = escape(url);	
			var dataString = '?filterButton=false&BDKeyid='+keyId;
			if(url.indexOf('unplanned') >=0)
				{
				title = 'Unplanned Maintenance View';
				dataString += '&activity=U';
				}
			else
				{
				title = 'Breakdown Analysis View';	
				dataString += '&activity=B';
				}		
			alert('s33');
			navigateToNextForm('brkdown_input.Bbrdn'+dataString,title,null,{"filterString":url});
		}
		function breakdownMstGrid()
		{
			var bdGridId = jQuery("#grdBdlist").jqGrid('getDataIDs');
			 for(i=1;i<=bdGridId.length;i++)	
			 {			 
				if(jQuery("#grdBdlist").getCell(i, 'status')=="COMPLETED")
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#c0ffc0'});							
					
				if(jQuery("#grdBdlist").getCell(i, 'counterMeasure').indexOf("PENDING") > 0)
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'color':'Blue'});
				
				if(jQuery("#grdBdlist").getCell(i, 'yy')=="PENDING")
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#afd6fe'});	
				
				if(jQuery("#grdBdlist").getCell(i, 'phen') == "NOT DEFINED")
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#67abde'});
				
				if(jQuery("#grdBdlist").getCell(i, 'finalAction') == "REPEAT BREAKDOWN")
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'color':'Red'});
				
			 }		
		}
		
</script>

<div id="wrapperRpt"> 
<table  style="margin-top:2px;">
	<tr>
		<td><span class="bd-undefinedPhen"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Undefined Phenomena</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-completed"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Completed</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-countermeasurepending"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Counter Measure Pending</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-repeatedbd"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Repeated Breakdown</label></td>	
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-yypending"></span></td>
		<td><label style="color:dark brown;font-weight: bold">YY-Pending</label></td>	
	</tr>
</table>

 <span style="padding-left:1080px;margin-top:0px;"><input type="button" class="easyui-button"  id="btnNewBooking" value="New Booking" style="width:100px;height: 25px;"/></span>
	<div style=""> 
			 <table id="grdBdlist" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id="hdnViewBDPrevDataUrl" name="hdnViewBDPrevDataUrl" value="${requestScope.filterStr}" />
