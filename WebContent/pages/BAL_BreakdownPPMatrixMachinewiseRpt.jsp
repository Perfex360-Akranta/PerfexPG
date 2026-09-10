
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
			//setLoadFormCallBackFrmId("frmBdPPMatrixMchWise");	
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  var  prevDataUrl = jQuery('#hdnBDPPMatMachinePrevDataUrl').val();
				
			  if( prevDataUrl == null || prevDataUrl.length <=0)		
				//viewGrid("ppMatrixMachineRpt_input.ppMatrixRpt","?q=1");
				  processGridnew("ppMatrixMachineRpt_input.ppMatrixRpt","?q=2","list","pager","PP Matrix Machine wise","","");
			  else
				 viewGrid(unescape(prevDataUrl),"&q=2");		
			
		});
		//function frmBdPPMatrixMchWise_afterLoadCallBack(){
			
			//toggleCommonFilter();	
		
	//}
		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{	
				
				processGridnew(url,filterString,"list","pager","PP Matrix Machine wise","","");
				return true;	
			}
			return false;	
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			jQuery("#chkoccurchkbox").attr('checked',true);
			jQuery("#chktimechkbox").attr('checked',false);	
			jQuery("#chkallchkbox").attr('checked',false);
			//readOnlyFields('chkallchkbox');
			disableField('frmBD', 'chkallchkbox');

			disableField('frmBD', 'chkfachkbox');
			disableField('frmBD', 'chkpillarchkbox');
			disableField('frmBD', 'chkrcchkbox');
			disableField('frmBD', 'chkrccchkbox');
			disableField('frmBD', 'chkcmchkbox');
			enableFields('chkoccurchkbox');
			enableFields('chktimechkbox');
			enableFields('chkallchkbox');
		
			
		}
		function validateFilterSelection(filterString){
			
				if(jQuery('#chkoccurchkbox').is(':checked') == true && jQuery('#chktimechkbox').is(':checked') == true)
		 		{
		 				alert("Select Either Time or Occurence ");
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
			// alert("row"+row);
			 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
			// alert("cm"+Object.keys(cm));
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
				    if(i == row.length-1)
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
<form name = 'frmBdPPMatrixMchWise'>
<div id="wrapperRpt"> 
	<div style=""> 
			 <table id="list" style="width:50%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<input type="hidden" id="hdnBDPPMatMachinePrevDataUrl" name="hdnBDPPMatMachinePrevDataUrl" value="${requestScope.filterStr}" />
</form>