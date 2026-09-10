
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			//setLoadFormCallBackFrmId("frmBdPPMatrixMonWise");	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnBDPPMatMonthPrevDataUrl').val();
				
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				//viewGrid("ppMatrixMonthRpt_input.ppMatrixRpt","?q=1");
					
				  processGridnew("ppMatrixMonthRpt_input.ppMatrixRpt","?q=1","list","pager","PP Matrix Month wise","","","");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=1");		
			
		});
	//function frmBdPPMatrixMonWise_afterLoadCallBack(){
			
			//toggleCommonFilter();	
		
	//}
		function frmFilter_enableDisableSuccessCallBack()
		{
			jQuery("#chkoccurchkbox").attr('checked',true);
			jQuery("#chktimechkbox").attr('checked',false);	
			jQuery("#chkallchkbox").attr('checked',false);
			//readOnlyFields('chkDatewise');
			readOnlyFields('dtetoDate');			
			readOnlyFields('dtefromDate');
			jQuery("#dtefromDate").datebox("setValue","");
			jQuery("#dtetoDate").datebox("setValue","");
			disableField('frmBD', 'chkallchkbox');
			
			
		}
				
	
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				
				processGridnew(url,filterString,"list","pager","PP Matrix Month wise","","","");
				return true;	
			}
			return false;	
		}
		function validateFilterSelection(filterString){		
			
				if(jQuery('#chkoccurchkbox').is(':checked') == true && jQuery('#chktimechkbox').is(':checked') == true)
		 		{
		 				alert("Select Either Time or Occurence");
		 				return false;
				}
				else
				{
					if(!filterMonthnDateDifference(filterString,null,48))
						return false;
						return  true;
				}
				
				
		 
		}
		
		function fillForm(id)
		{
			
			
			//var rowData = jQuery("#list").jqGrid('getRowData',id);	
			 //alert("rowData"+rowData);
			 var row = jQuery("#list").jqGrid('getDataIDs');
			
			 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
			 
			
			 for(var i=0;i<row.length;i++)
			 {
				 for(var j=3;j<cm.length;j++)
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
			    }
				    if(i == row.length-1 && i<4)
					    {
				    	 for(var j=0;j<cm.length;j++)
				     	 {							
					   		jQuery("#list").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
				     	 }
					    }
					    
			 }
					
			/*var keyId = rowData.keyId;
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace("ppMatrixMonthRpt_getData.ppMatrixRpt?&","ppMatrixMonthRpt_input.ppMatrixRpt?");			
			url = escape(url);				
			navigateToNextForm('ppMatrixMonthRpt_input.ppMatrixRpt'+'?filterButton=false&CustKeyid='+keyId,'PP Matrix Month wise',null,{"filterString":url});*/
		}	
</script>
<form name = 'frmBdPPMatrixMonWise'>
<div id="wrapperRpt" style="margin-top: 15px;">
	<div style=""> 
			 <table id="list" style="width:50%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id="hdnBDPPMatMonthPrevDataUrl" name="hdnBDPPMatMonthPrevDataUrl" value="${requestScope.filterStr}" />
</form>