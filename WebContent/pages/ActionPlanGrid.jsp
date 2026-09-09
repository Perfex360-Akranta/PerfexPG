<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
				
				
			var url = jQuery('#hiddenUrl').val();
			//alert(url);
			var dataString = "?q=1"; 
			viewGrid(url,dataString);
			
				}
		
		);
		
		function viewGrid(url,filterString)
		{
			//alert(filterString);
			var from=jQuery("#hdnFrm").val();
			
			
			if( validateFilterSelection(filterString))
			{
				var tableCaption = "";
				if(from=="view"||from=="completion"||from=="approval")
				processGridnew("ActionPlanGrid_input.api",filterString,"grdActionPlan","grdActionPlanPager",tableCaption,"doubleClickGrid","","loadComplete");
				else if(from=="report")
				processGridnew("ActionPlanGrid_input.api",filterString,"grdActionPlan","grdActionPlanPager",tableCaption,"","","loadComplete");
				return true;
			}	
		}
		function loadComplete()
		{
			var from=jQuery("#hdnFrm").val();
			
			if(from=="view" || from=="report")
				{
				 	var row = jQuery("#grdActionPlan").jqGrid('getDataIDs');
				 	
				 	var cm = jQuery("#grdActionPlan").jqGrid("getGridParam", "colModel");
				 	
				 	 var state="";
				 for(var i=0;i<row.length;i++)
					 {
					
						 var rowData = jQuery("#grdActionPlan").jqGrid('getRowData',row[i]);
				 		 var Status=rowData.Status;
				 		
						 if(Status=="C")
				 		 state="COMPLETED";
						 else if(Status=="A")
						 state="APPROVED";
						 else if(Status=="A")
					 	state="APPROVED";
						 else if(Status=="R")
						 state="CANCELLED";
						 else if(Status=="P")
							 state="PENDING";
					 jQuery("#grdActionPlan").jqGrid('setCell',row[i],cm[15].name,state,{'color':'#000000'}); 
			 }
		}
		}
		function validateFilterSelection(filterString){
			return  true;
		}
		function doubleClickGrid(rowid) 
		{
			var rowData = jQuery("#grdActionPlan").jqGrid('getRowData',rowid );
			//alert(rowData.USERKEYID);
			//alert(rowData.LOGINID);
			var from=jQuery("#hdnFrm").val();
			navigateToNextForm("ActionPlan_input.api?&mode=MODIFY&closeOnSave=true&from="+from+"&keyId="+rowData.APLM_KEYID+"&grid="+true);
		}
		
	</script>
	
	<div id="wrapperRpt" style="margin-top:10px;margin-left:60px" >
	<table id="grdActionPlan" ></table>
	<div id="grdActionPlanPager"></div></div>
	<input type="hidden" id="hdnFrm" value="${requestScope.from }"/> 	