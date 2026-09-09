<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			//setLoadFormCallBackFrmId("frmabnagerpt");
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnAbnAgeRptPrevDataUrl').val();
				
			 /* if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid(url,"?q=1");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=1");*/		
			  setLoadFormCallBackFrmId("frmabnagerpt");
			  invokeAfterLoadFormCallBack();
		});
		/*function frmabnagerpt_afterLoadCallBack(){
		
				toggleCommonFilter();	
			
		}*/
		function viewGrid(url,filterString)
		{
			var abnType=jQuery('#hdnAbnType').val();
			filterString+="&abnType="+abnType;	
			if( validateFilterSelection(filterString))
			{
				var fromDate = getFilterValue(filterString,"dtFromDate");
				var toDate = getFilterValue(filterString,"dtToDate");
				//alert(fromDate+toDate);
				var tableCaption = "Abnormality Ageing Report (From: "+fromDate+" To: "+toDate+")";
				//alert(tableCaption);
				processGridnew(url,filterString,"list","pager",tableCaption,"AbnAgeDoubleClick",tableCaption,"fillform");		
				//processGridnew(url,filterString,tableId, pagerId,tableCaption,doubleClickFunction,tableHeaderSpanCallback,ongridcompletecallback,selectRowFunction,filterNeed ){
				return true;
			}
			return false;
		}
		
		function validateFilterSelection(filterString){
			
			/*if(filterString=="?q=1")
				return true;
			else{ 
			if(!filterMonthnDateDifference(filterString,20,4))
				return false;
			return true;
				
			}		*/
			return true;
		}
		function AbnAgeDoubleClick(id)
		{	
			
			var rowData = jQuery("#list").jqGrid('getRowData',id);
			var keyid = rowData.ABNID;
			
			var filter = jQuery("#hdnAbnAgeRptPrevDataUrl").val();
			var jsonstr = '{"filter":"'+ filter+'"}';
			var perstData = jQuery.parseJSON(jsonstr);	
			if(keyid!="")
				navigateToNextForm('Abnormality_input.abnForm'+'?AbnId='+keyid+'&mode=view',"Abnormality",null,perstData);
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			
			disableField('frmAbnormalityRelated', 'chkdectbychkbox');
			disableField('frmAbnormalityRelated', 'chkdectdtchkbox');
			disableField('frmAbnormalityRelated', 'chkcauschkbox');
			disableField('frmAbnormalityRelated', 'chkabncatchkbox');
			disableField('frmAbnormalityRelated', 'chkabnimpchkbox');
			disableField('frmAbnormalityRelated', 'chkallchkbox');
			
			jQuery("#chkDatewise").attr('checked',false);
			jQuery("#chkMonthwise").attr('checked',true);
			clearField('dtefromDate');
			clearField('dtetoDate');
			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
			enableFields('chkMonthwise');
			enableFields('dtefromMonth');
			enableFields('dtetoMonth');
			
			setTimeout(function() {jQuery("#cboabnstatus").val("P");},1250);
			
			//setFieldValue('cboabnstatus', 'Pending','frmAbnormalityRelated');
			//jQuery("#frmAbnormalityRelated input[id=cboabnstatus]").val("Pending");
			disableField('frmAbnormalityRelated', 'cboabnstatus');
			//reloadCombo("frmAbnormalityRelated","cmbAbnmTypeid","Combo_Type.abnForm?abtmType=aging");	
			
		}
		function fillform(id)
		{	
			//var rowData = jQuery("#list").jqGrid('getRowData',id);	
			 //alert("rowData"+rowData);
			 var row = jQuery("#list").jqGrid('getDataIDs');
			//alert("row"+row);
			 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
			 if(jQuery('#list tr').hasClass('totalRow')){
				  jQuery(' tr.totalRow').find(' td:first-child').css('color','#000');
				  jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #8DB2E3');
				  jQuery("#list tr" ).removeClass("totalRow");
				}
				
			 setTotalRowColorForGroupby("list");
			// alert("cm"+Object.keys(cm));
			
			/* for(var i=0;i<row.length;i++)
			 {	
			    if(i == row.length-1)
				    {
			    	//jQuery("#list").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#FF8040','font-weight':'bold','font-size':'15px','background-color':'#000'});
				    	 for(var j=0;j<cm.length;j++)
				     	 {	
					     	 if(cm[j].name == 'DETECTIONDATE' &&  jQuery("#list").jqGrid('getCell',row[i],cm[j].name) == 'TOTAL')
					     	 {
					     		 for(var i=0;i<row.length;i++)
								 {	
								    if(i == row.length-1)
									    { 
								    	for(var j=0;j<cm.length;j++)
								     	 {		
					   						//jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
					   					}
								 	}
								 }
						     }	
				    	}  
			 		}
				}*/
		}	
		function tickAction(cellvalue, options, rowObject) {
			//var reccnt = jQuery("#list").getDataIDs().length;	
			//var id = options.rowId;
			//alert("rec"+reccnt);
			//alert("id"+id);
			//if(reccnt == id)return;
			if (cellvalue == "-1" || cellvalue == "0")
			{
				var formatStr  = '<span ' ;				
				if(cellvalue == "-1")
					
					formatStr  += ' style=\"color:blue;font-size:20px;\"> &#10003;';// tick 
				else if(cellvalue == "0")
					formatStr =" ";
				
				formatStr  +=  '</span>';
				return formatStr.trim() == '</span>'?" ": formatStr;
			}
				
			return cellvalue;	
		}
		
		function fillForm(id)
		{			
			jQuery('.ui-paging-info').css('font-size','12px');						    
			
		}
		function frmabnagerpt_afterLoadCallBack(){
			
			toggleCommonFilter();	
		
	}
		</script>
<form name = 'frmabnagerpt'>
<div id="wrapperRpt"> 
<div style="margin-top: -25px">
<label class="notes"  style="font-weight: bold; padding-left:0px;"> Double Click on Row to View Data</label></div>
	<div style=""> 
			 <table id="list" style="width:1000%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id="hdnAbnAgeRptPrevDataUrl" name="hdnAbnAgeRptPrevDataUrl" value="${requestScope.filterStr}" />
<!--<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="ABN" />-->
<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
</form>