<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){

 		    var dataString = " ";
		   var url = jQuery('#hiddenUrl').val();
			var actionPart = jQuery('#hiddenUrl').val();	
			
		
		var  prevDataUrl = jQuery('#hdnPreviousDataUrl').val();
		
		if( prevDataUrl == null || prevDataUrl.length <=0)
		{		
			if(url.indexOf('UPM') >=0 || url.indexOf('unplanned')>=0)
				viewGrid(url,"");
			else
				viewGrid(url,"?q=1");
		}
		else{
			//alert("Prev data " +unescape(prevDataUrl));
			if(prevDataUrl.indexOf('UPM') >=0 || url.indexOf('unplanned')>=0)
				viewGrid(unescape(prevDataUrl),"");
			else
				viewGrid(unescape(prevDataUrl),"?q=1");
			//viewGrid(unescape(prevDataUrl),"&q=1");
		}	
			
			
			//viewGrid(url,dataString);	

			jQuery("#btnNewBooking").click(function(){

				var url = jQuery('#hiddenUrl').val();	
				if( url != null && url.length > 2 ){
					var formHeader = getFormMainHeader();					
					url = url.replace("_view","_input");	
					//url += (url.indexOf('?') >= 0 ? '&':'?') + 'userEvent=new';   //
					navigateToNextForm(url,formHeader);
				}
			});			
			
		});
		function viewGrid(url,filterString)
		{
			
			if( validateFilterSelection(filterString))
			{
				url = url.replace("_getData.Bbrdn","_view.Bbrdn");
				
				if(url.indexOf('UPM') >=0 || url.indexOf('unplanned')>=0)
					filterString += '&maintMode=upm';
				processGridnew(url,filterString,"grdBdlist","pager","BreakDown Master","grdBdlist_fillForm","","");
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
			//  var url = jQuery('#hiddenUrl').val();
			var keyId = rowData.KEYID;	
			var WOID = rowData.ORDERNO;	
			
			//alert(keyId +" Keyid " +WOID +" WOID");
					
			var url = jQuery("#grdBdlist").jqGrid('getGridParam', 'url');
			url = url.replace("Breakdown_getData.Bbrdn?&","Breakdown_view.Bbrdn?").replace("UPM_getData.Bbrdn?&","UPM_view.Bbrdn?").replace("brkdown_getData.Bbrdn?&","brkdown_view.Bbrdn?").replace("unplanned_getData.Bbrdn?&","unplanned_view.Bbrdn?").replace("BDViewRpt_getData.BbrdnRpt?&","BDViewRpt_view.BbrdnRpt?").replace("unplanned_getData.Bbrdn?&","unplanned_view.Bbrdn?");
			url = escape(url);		
			var title;
			var dataString = '?filterButton=false&BDKeyid='+keyId+'&WOID='+WOID;
			var urlString='';
			if(url.indexOf('UPM') >=0)
			{
				title = 'Unplanned Maintenance Modification';
				dataString += '&activity=U';
				urlString='Breakdown_input.Bbrdn';
			}
			else if(url.indexOf('unplanned') >=0)
			{
				title = 'Unplanned Maintenance View';
				dataString += '&activity=U';
				urlString='brkdown_input.Bbrdn';
			}
			else if(url.indexOf('brkdown') >=0)
			{
				title = 'Breakdown Analysis-View';
				dataString += '&activity=B';
				urlString='brkdown_input.Bbrdn';	
			}
			else if(url.indexOf('BDViewRpt') >=0)
			{
				title = 'Breakdown Analysis-View';
				dataString += '&activity=B';
				urlString='brkdown_input.Bbrdn';	
			}
			else
			{
				title = 'Breakdown Analysis';
				dataString += '&activity=B';
				urlString='Breakdown_input.Bbrdn';	
			}
			//alert(url +" urlurl " +urlString);
			navigateToNextForm(urlString+dataString,title,null,{"filterString":url});
		}
		function breakdownMstGrid_formater(cellValue,options,rowObj)
		{
		//	var Id = options.rowId;
		// 	var pos = options.pos;
			//var cM = jQuery("#grdBdlist").jqGrid('getGridParam', 'colModel'); 
		//	var colName = cM[pos].name;
			var finalAction = rowObj[19];
			
			var whywhyS = rowObj[20];
			var counterMeasureS = rowObj[24];
			var bdStatus = rowObj[25];
			var pcsBd = rowObj[42];
		//	var val = rowObj[pos];
			var ret = cellValue;

			//alert("finalAction " +finalAction +"whywhyS "+whywhyS +" counterMeasureS "+"bdStatus "+bdStatus +"ret "+ret);
			//if(  colName == 'FINALACTION' ){
				if( finalAction =="REPEAT BREAKDOWN" )
					ret = "<div style='background-color:#F9A2AC;height: 100%'>"+ cellValue + "</div>";//jQuery("#grdBdlist").jqGrid('setCell',Id,"BDNO","",{'background-color':'#F9A2AC'});
				else if( finalAction =="PENDING" )
					ret = "<div style='background-color:#EFB6EF;height: 100%'>"+ cellValue + "</div>";//jQuery("#grdBdlist").jqGrid('setCell',Id,"BDNO","",{'background-color':'#EFB6EF'});
			//}
			if( whywhyS == "PENDING"  )
			{
				ret = "<div style='background-color:#afd6fe;height: 100%'>"+ cellValue + "</div>";
				//jQuery("#grdBdlist").jqGrid('setCell',Id,"BDNO","",{'background-color':'#afd6fe'});			
			}	
			if(counterMeasureS == "PENDING"  )
			{
				ret = "<div style='background-color:#E0C3C3;height: 100%'>"+ cellValue + "</div>";
				//jQuery("#grdBdlist").jqGrid('setCell',Id,"BDNO","",{'background-color':'#E0C3C3'});			
			}
			if( bdStatus == "COMPLETED" )
			{
				ret = "<div style='background-color:#c0ffc0;height: 100%'>"+ cellValue + "</div>";
				//jQuery("#grdBdlist").jqGrid('setCell',Id,"BDNO","",{'background-color':'#c0ffc0'});			
			}
			if( pcsBd == "" || pcsBd== null )
			{
				ret = "<div style='background-color:#A366FF;height: 100%'>"+ cellValue + "</div>";
				//jQuery("#grdBdlist").jqGrid('setCell',Id,"BDNO","",{'background-color':'#c0ffc0'});			
			}
			return ret;
			

			
			// var bdGridId = jQuery("#grdBdlist").jqGrid('getDataIDs');
			/* for(var i=1;i<=bdGridId.length;i++)	
			 {			
					if(jQuery("#grdBdlist").getCell(i, 'FINALACTION') == "REPEAT BREAKDOWN")
						jQuery("#grdBdlist").jqGrid('setCell',i,"BDNO","",{'background-color':'#F9A2AC'});
					if(jQuery("#grdBdlist").getCell(i, 'FINALACTION')=="PENDING")
						jQuery("#grdBdlist").jqGrid('setCell',i,"BDNO","",{'background-color':'#EFB6EF'});
					if(jQuery("#grdBdlist").getCell(i, 'WHYWHY')=="PENDING" || jQuery("#grdBdlist").getCell(i, 'FINALACTION')=="PENDING")
						jQuery("#grdBdlist").jqGrid('setCell',i,"BDNO","",{'background-color':'#afd6fe'});	
					if(jQuery("#grdBdlist").getCell(i, 'COUNTERMEASURE').search("PENDING") != -1)
						jQuery("#grdBdlist").jqGrid('setCell',i,"BDNO","",{'background-color':'#E0C3C3'});
					if(jQuery("#grdBdlist").getCell(i, 'STATUS')=="COMPLETED" )
						jQuery("#grdBdlist").jqGrid('setCell',i,"BDNO","",{'background-color':'#c0ffc0'});
					
			*/	
					/*else if(jQuery("#grdBdlist").getCell(i, 'status')=="COMPLETED" )
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#c0ffc0'});							
				//jQuery("#grdBdlist").getCell(i, 'finalAction')=="COMPLETED"
				
					else if(jQuery("#grdBdlist").getCell(i, 'yy')=="PENDING" || jQuery("#grdBdlist").getCell(i, 'finalAction')=="PENDING")
						jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#afd6fe'});	
					
					/*else if(jQuery("#grdBdlist").getCell(i, 'finalAction')=="PENDING")
						jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#B243A7'});	*/
					
				
					/*else if(jQuery("#grdBdlist").getCell(i, 'phen') == "NOT DEFINED")
					jQuery("#grdBdlist").jqGrid('setCell',i,"keyId","",{'background-color':'#67abde'});*/

			
				
				
			// }		
		}
		
		function frmFilter_enableDisableSuccessCallBack()
		{
			reloadCombo("frmBD","cmbMSRKeyId","msr.commonFilter?q=2&actType=B" );
			jQuery("#chkDatewise").attr('checked',false);
			jQuery("#chkMonthwise").attr('checked',true);		
			
			readOnlyFields('dtetoDate');			
			readOnlyFields('dtefromDate');
			jQuery("#dtefromDate").datebox("setValue","");
			jQuery("#dtetoDate").datebox("setValue","");
		}
		jQuery('#btnSelectTab').click( function(){
			var rowid = jQuery("#grdBdlist").jqGrid('getGridParam','selrow');
			if(rowid =='' || rowid == null || rowid =='undefined'){
				alert('Select The Record To Modify');
				}
			else{
				grdBdlist_fillForm(rowid);
				
				}
			});
		//alert(1235);
</script>

<div id="wrapperRpt">
<div style="margin-top: -28px">
<table  style="margin-top:2px;">
	<tr>
		<td><span class="bd-completed" ></span></td>
		<td><label style="color:dark brown;font-weight: bold">Completed</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-countermeasurepending"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Counter Measure Pending</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-repeatedbd"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Repeated Breakdown</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-finalActionpending"></span></td>
		<td><label style="color:dark brown;font-weight: bold;">FinalAction Pending</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-yypending"></span></td>
		<td><label style="color:dark brown;font-weight: bold">Why Why-Pending</label></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td><span class="bd-pcsbd"></span></td>
		<td><label style="color:dark brown;font-weight: bold">PCS Breakdown</label></td>
	</tr>
	<tr>
	<td colspan="14">
		<label style="color:dark brown;font-weight: bold">Double click to view the details</label>
	</td>
	
	<td>
		<input type="button" class="easyui-button" value="New" id="btnNewBooking"/> 
	</td>
	</tr>
	<tr>	
	<td>	
	<input type="button" class="easyui-button" value="For Tab" id="btnSelectTab"/> 
	</td>	
	</tr>
	
</table>

</div>
<div> 
	 <table id="grdBdlist"><tr><td/></tr></table>
<div style="float:left;"> 
	 <table id="grdBdlist" style="width:100%;"><tr><td/></tr></table>
	 <div id="pager"></div>
</div>
</div>
</div>
<input type="hidden" id="hdnPreviousDataUrl" name="hdnPreviousDataUrl" value="${requestScope.filterStr}" />
