<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			  initialiseForm('frmETPlCompRPT');
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  //alert(" Type :: "+jQuery('#hdntype').val());
			  var  prevDataUrl = jQuery('#hdnETPlCompRPTPrevDataUrl').val();
			/*	
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid("ETplanVScomp_input.ETPlCompRPT","?q=1");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=1");		
			  */
			  
			  jQuery('#chkcomplaintgallery').attr('checked',true);
			  
			  jQuery('#btnLineGraph').click(function(){
					var rowid = jQuery("#PlanVsComGrd").jqGrid('getGridParam','selrow');
					var rowData = jQuery("#PlanVsComGrd").jqGrid('getRowData',rowid);
					var type =jQuery('#hdntype').val();
					var tot = rowData.COMPANY3;
					 if( (type=="CUSCOMPNTRPT")){
						  if (jQuery('#chkcrm').is(':checked')==true ){
							var url = "NewETplanVScomplinegrapgh.newentRpt?&flid=&chkline=1&Custype=CRM&tot=TOTAL";
							showGraphData(url);
						 }
					 }
					 
					var sel = rowData.KEYFIELD2;
					
					if(rowid == null || rowid == ""  )
					{			
						alert("Select Row To View Line Graph");
					}  
					else if(tot=="TOTAL"){
						var url="";
								var type=jQuery('#hdntype').val();
								//alert(" line :: type :: "+type);  //+rowid+"&rownum="+rowid
							    if(type.trim().length>0 && (type=="TRADHRPT"||type=="TRADHMEMBERS")){
								    
				                    url = "NewETplanVScomplinegrapgh.newentRpt?&flid=&chkline=1&type="+type+"&tot=TOTAL";
				                }
				                else if(type=="CUSCOMPNTRPT" && type.trim().length>0){
				                	if (jQuery('#chkcomplaintgallery').is(':checked')==true ){
										 url = "NewETplanVScomplinegrapgh.newentRpt?&flid=&chkline=1&Custype=ComplaintGallery&tot=TOTAL";
									 }else if (jQuery('#chkcrm').is(':checked')==true ){
								    	 url = "NewETplanVScomplinegrapgh.newentRpt?&flid=&chkline=1&Custype=CRM&tot=TOTAL";
								    }else
								    {								    	
								    	alert('Select Report Type');
								    }
								    	
				                }
				                else
				                	{
				                		 url = "NewETplanVScomplinegrapgh.newentRpt?&flid=&chkline=1";
				                	}
				             
							 //alert(" :: url :: "+url);
							     showGraphData(url);
						} 
					 
					else{
						var keyid= sel.split("#");
                		var selId= keyid[0];
                		var url ="";
						if(checkForZeroes("PlanVsComGrd",rowid,2)){	
								var type=jQuery('#hdntype').val();
							    if(type=="TRADHRPT"||type=="TRADHMEMBERS"){
				                    url = "NewETplanVScomplinegrapgh.newentRpt?&flid="+selId+"&chkline=1&type="+type;
				                }
				                else if(type=="CUSCOMPNTRPT" ){
				                	if (jQuery('#chkcomplaintgallery').is(':checked')==true ){
										 url = "NewETplanVScomplinegrapgh.newentRpt?&flid="+selId+"&chkline=1&Custype=ComplaintGallery";
									 }else if (jQuery('#chkcrm').is(':checked')==true ){
								    	 url = "NewETplanVScomplinegrapgh.newentRpt?&flid="+selId+"&chkline=1&Custype=CRM";
								    }else
								    {								    	
								    	alert('Select Report Type');
								    }
								    	
				                }
				                else
				                	{
				                	
				                		 url = "NewETplanVScomplinegrapgh.newentRpt?&flid="+selId+"&chkline=1";
				                	}
				             
							// alert(" :: url :: "+url);
							     showGraphData(url);
						} 
							 
									   else 
							{
							     alert("No Record to View Line Graph");
							}
					}
					
				});
			  jQuery('#btnGraph').click(function(){
					var rowid = jQuery("#PlanVsComGrd").jqGrid('getGridParam','selrow');
					var rowData = jQuery("#PlanVsComGrd").jqGrid('getRowData',rowid);
					var tot = rowData.COMPANY3;
					var sel = rowData.KEYFIELD2;
					if(rowid == null || rowid == ""   )
					{
						alert("Select Row To View Graph");
					}  
					else if(tot=="TOTAL"){
						if(checkForZeroes("PlanVsComGrd",rowid,2)){	
							 /*if(jQuery('#chkcummulative').is(':checked') == true){	
							   var url = "customerCumplaintTrendchart.customerComplaintRpt?rowid=" + selId + "&rownum="+rowid + "&chkcummulative=1";
							 }else{*/
								 //alert("rowid"+rowid);
							   var url = "NewETplanVScompchart.newentRpt?&tot=TOTAL" ;	 
							 //} 
							showGraphData(url);
						}else 
							alert("No Record to View Graph");
					}
					else{
						
						var keyid= sel.split("#");
						var selId= keyid[0];
						if(checkForZeroes("PlanVsComGrd",rowid,2)){	
							 /*if(jQuery('#chkcummulative').is(':checked') == true){	
							   var url = "customerCumplaintTrendchart.customerComplaintRpt?rowid=" + selId + "&rownum="+rowid + "&chkcummulative=1";
							 }else{*/
								 //alert("rowid"+rowid);
							   var url = "NewETplanVScompchart.newentRpt?&flid="+selId ;	 
							 //} 
							showGraphData(url);
						}else 
							alert("No Record to View Graph");
					}
				});
			  
			  
			  jQuery("#btnview").click(function()
			  {
				jQuery('#hdnbtnview').val("Y");
				var filterString = jQuery('#hdnFilterString').val();
				viewGrid(url,"?&q=2");
			 }); 

			  jQuery( "#chkcomplaintgallery" ).click(function() {
	            	 jQuery('#chkcrm').attr('checked',false);
	       	   });
			  
			  jQuery( "#chkcrm" ).click(function() {
	            	jQuery('#chkcomplaintgallery').attr('checked',false);
	       			 
	       	  });
			  
			  setLoadFormCallBackFrmId("frmETPlCompRPT");
			  invokeAfterLoadFormCallBack();
		
			  var type=jQuery('#hdntype').val();
				
	    	  if(type.trim().length>0)
			     jQuery('#btnGraph').hide();
	
			   
		});
	
		function frmETPlCompRPT_afterLoadCallBack(){
			
			toggleCommonFilter();	
			
	   }
		
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{
				//alert(" url :: url :: "+url);    type.trim().length>0 &&
				var type=jQuery('#hdntype').val();
				var btnval=jQuery('#hdnbtnview').val();
				if(type.trim().length==0 || type!="CUSCOMPNTRPT" && (type=="TRADHRPT"||type=="TRADHMEMBERS")){
					jQuery('#customercomplaint').hide();
					filterString += '&type='+type;
				}
				
				if(type.trim().length>0 && type=="CUSCOMPNTRPT"){
					if (jQuery('#chkcomplaintgallery').is(':checked')==true ){
						filterString += "&custmrtype=ComplaintGallery";
				    }else if (jQuery('#chkcrm').is(':checked')==true ){
				    	filterString += "&custmrtype=CRM";
				    }
				}
				//if(type.trim().length>0 && type=="CUSCOMPNTRPT"&& btnval!="Y"){
					//return true;
				//}else{
					processGridnew(url,filterString,"PlanVsComGrd","PlanVsComPlanVsCompager","Training Program Plan Vs Complete Report","PlanVsComGrd_doubleClickGrid","PlanVsComGrd_fillForm");
					return true;
				//}
			}
			return false;
		}
		
		function validateFilterSelection(filterString){
			return true;
		}
		
		function PlanVsComGrd_doubleClickGrid(id)
		{
			var keyfieldData=jQuery("#hdnFnlnKeyid").val();
			keyfieldData=setDrillDoubleClick("PlanVsComGrd","KEYFIELD2",id,keyfieldData);
			jQuery("#hdnFnlnKeyid").val(keyfieldData);
		}
		function PlanVsComGrd_fillForm()
		{
			var type=jQuery('#hdntype').val();
			var title="CH1-0";
			if(type=="CUSCOMPNTRPT"){
				title="jqgh_PlanVsComGrd_COMPANY3";
			}
	       setDrillDownHeader(title,"PlanVsComGrd","KEYFIELD2");
			
			if( (type=="TRADHRPT"||type=="TRADHMEMBERS")){
				//alert("4");
			   setTotalRowCss('');
			}
			else
				setTotalRowCss('PlanVsComGrd');
			
		}
		function PlanVsComGrd_onProcessGridBack()
		{
			var keyfieldData=jQuery("#hdnFnlnKeyid").val();
			setDrillProcesGridBack("PlanVsComGrd","KEYFIELD2",keyfieldData);
			jQuery("#hdnFnlnKeyid").val("");
		}	
		function frmFilter_enableDisableSuccessCallBack()
		{
			
			jQuery('#disableFuncLoc').val('disable');
			setTimeout(function() {readOnlyFields('dispFunctionalLoc');},1200);
			
			setTimeout(function() {readOnlyFields('cmbEquipmentid');},1200);			
			setTimeout(function() {readOnlyFields('cmbeqpGroup');},1200);			
			setTimeout(function() {readOnlyFields('cmbTrade');},1200);			
			setTimeout(function() {readOnlyFields('cmbCircle');},1200);			
			setTimeout(function() {readOnlyFields('cmbMachineRank');},1200);			
			setTimeout(function() {readOnlyFields('cmbassembly');},1200);			
			setTimeout(function() {readOnlyFields('cmbCostCenter');},1200);			
			setTimeout(function() {readOnlyFields('cmbMould');},1200);
			setTimeout(function() {readOnlyFields('chkDatewise');},1200);
			setTimeout(function() {readOnlyFields('dtefromDate');},1200);
			setTimeout(function() {readOnlyFields('dtetoDate');},1200);			
			setTimeout(function() {readOnlyFields('cmbdesignation');},1200);			
			setTimeout(function() {readOnlyFields('cmbbatch');},1200);			
			setTimeout(function() {readOnlyFields('cmbprogm');},1200);			
			setTimeout(function() {readOnlyFields('cmbpgmno');},1200);
			setTimeout(function() {readOnlyFields('cmbemployee');},1200);
			setTimeout(function() {readOnlyFields('cmbpgmbenefit');},1200);
			setTimeout(function() {readOnlyFields('cmbSkillAvg');},1200);
			disableField('frmTraining','cboknowavg');
			
			disableField('frmTraining','cboskillavg');
			
			disableField('frmTraining','cbocompavg');
			
			disableField('frmTraining','cbotrainingcategory');
			
			disableField('frmFilter','cboRelatedTo');			
			setTimeout(function() {readOnlyFields('chkSkipLine');},1200);
			setTimeout(function() {readOnlyFields('chkprogramwise');},1200);			
			setTimeout(function() {readOnlyFields('chkemployeewise');},1200);			
			clearField('dtefromDate');
			clearField('dtetoDate');
		
			jQuery('#cboRelatedTo').prepend("<option value='-'></option>");
			setFieldValue('cboRelatedTo','-');
			
			jQuery('#cboknowavg').prepend("<option value='-'></option>");
			setFieldValue('cboknowavg','-');
			
			jQuery('#cboskillavg').prepend("<option value='-'></option>");
			setFieldValue('cboskillavg','-');
			
			jQuery('#cbocompavg').prepend("<option value='-'></option>");
			setFieldValue('cbocompavg','-');


			jQuery('#cbotrainingcategory').prepend("<option value='-'></option>");
			setFieldValue('cbotrainingcategory','-');
			
			
					
		}
		/*function fillForm(id)
		{
			jQuery("#trngraph").css('display','block');
			 var row = jQuery("#PlanVsComGrd").jqGrid('getDataIDs');
				//alert("row"+row);
				 var cm = jQuery("#PlanVsComGrd").jqGrid("getGridParam", "colModel");
				// alert("cm"+Object.keys(cm));
				 for(var i=0;i<row.length;i++)
				 {*/
					 /* for(var j=8;j<cm.length;j++)
			     	 {
						
						  var zeroVal = jQuery("#PlanVsComGrd").jqGrid('getCell',row[i],cm[j].name);	//alert(zeroVal);		 
						  if(zeroVal != ' ' && zeroVal != '' && zeroVal != null)
						  {
							  if(zeroVal =='0'){
								
						  		jQuery("#PlanVsComGrd").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#FF8040','font-weight':'bold','font-size':'15px','background-color':'#fff'});
							  }
							  else{
								  
								  jQuery("#PlanVsComGrd").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#000','font-weight':'bold','font-size':'12px','background-color':'#fff2a8'});
								  
							  }
						  }
				    }
					    if(i == row.length-1)
						    {
					    	 for(var j=0;j<cm.length;j++)
					     	 {							
						   		jQuery("#PlanVsComGrd").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
					     	 }
						    }
					 */
				// }
						
			
		//}	
		
</script>
<form name ='frmETPlCompRPT' id="frmETPlCompRPT">
<div >
<div id="wrapperRpt"> 
<table style="width:122%;width:105%\9">
<tr>
<td><div><span>
	<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/></span>
	<input id="btnLineGraph" class="easyui-button"  type="button" value="LineGraph"/>
</div>
</td>
<td style="padding-left:2px;"><span><label id="trendgraph"  class="notes"   style="font-weight: bold; display: block;" > ${requestScope.GraphMsg}</label></span></td>
<td>
<div id="customercomplaint">
<input type="checkbox" id="chkcomplaintgallery" name="chkcomplaintgallery" value="" />
<label style="padding-right: 4px;">Complaint Gallery</label>  
<span style="padding-left:20px;">
<input type="checkbox" id="chkcrm" name="chkcrm" value="" />
<label style="padding-right: 4px;">CRM</label>
</span>
<span style="padding-right: 4px;">
<input class="easyui-button" type="button" id="btnview" name="btnview"  style="width:50px;height:30px;"  value="View" />
</span>
</div>
</td>
</tr>
<tr>
</table>


<div id="divGraphContainer" ></div>
<div style="padding-top:0px;"> 
<table id='PlanVsComGrd'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='PlanVsComPlanVsCompager'></div>
			
	</div>
</div>


</div>
<%--<div class="floatright" style="valign:top;"></div>--%>

<input type="hidden" id="hdnETPlCompRPTPrevDataUrl" name="hdnETPlCompRPTPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}" />
<input type="hidden" id="hdnbtnview" name="hdnbtnview" value="" />
</form>