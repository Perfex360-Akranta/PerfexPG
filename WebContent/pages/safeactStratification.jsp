<script type="text/javascript">
	jQuery.noConflict();
	var url="";
	jQuery(document).ready(
			function() {
				 url = jQuery('#hiddenUrl').val();
				setLoadFormCallBackFrmId("frmSafeactStratification");
				invokeAfterLoadFormCallBack();
				var type =jQuery('#hdntype').val();
				jQuery('#chkUnsafeAct').attr('checked',true);
				var type=jQuery('#hdntype').val();
			
				
			/*	 jQuery('#btngraphh').click(function(){
						var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
						var rowData = jQuery("#list").jqGrid('getRowData',rowid);
						var tot = rowData.COMPANY3;
						var sel = rowData.KEYFIELD2;
						var url = "safeactStratificationbar.nnrm";
						alert(url);
						showGraphData(url);
				 });	*/
			
						
			}); 
	
	jQuery('#chkUnsafeAct').click(function(){
	//	alert(123);
        jQuery('#chkUnsafeAct').val("UA");
       if(jQuery("#chkUnsafeAct").is(":checked")==true){
			  jQuery('#chkUnsafeCond').attr('checked',false); 
			  jQuery('#chkUnsafeCond').val(" ");
		  }
      // 	var url = "safeactStatification_input.nnrm";
	//    var filterString;
	   viewGrid("safeactStatification_input.nnrm","?&q=2&type=UA");
	});
	
	/*  jQuery("#chkUnsafeAct").click(function() {
		  if(jQuery("#chkUnsafeAct").is(":checked")==true){
			  jQuery('#chkUnsafeCond').attr('checked',false); 
			  
		  }
	   });*/
	 
	
	jQuery('#chkUnsafeCond').click(function(){
//	alert(1234);
		jQuery('#chkUnsafeCond').val("UC");
       	if(jQuery("#chkUnsafeCond").is(":checked")==true){
			jQuery('#chkUnsafeAct').attr('checked',false);	
			 jQuery('#chkUnsafeAct').val(" ");
		}
       //var url = "unsafeactStatification_input.nnrm";
	   //var filterString;
		viewGrid("unsafeactStatification_input.nnrm","?&q=2&type=UC");
	});
	
	  /* jQuery("#chkUnsafeCond").click(function() {
		if(jQuery("#chkUnsafeCond").is(":checked")==true){
			jQuery('#chkUnsafeAct').attr('checked',false);	
		}	 
 	  }); */
 	
	function frmSafeactStratification_afterLoadCallBack(){
		toggleCommonFilter();
		}	

	function viewGrid(url,filterString)
	{ 
		if( validateFilterSelection(filterString))
		{ 	
			var type=jQuery('#hdntype').val();
			//alert("type"+type);
			filterString += '&Flid=f&firstClick=Y';	
			filterString += '&type='+type;
			if(url!="unsafeactStatification_input.nnrm"){
				//alert("Inside UA");
				//filterString += '&type=UA';
				//alert("UA filter"+filterString);
			    processGridnew("safeactStatification_input.nnrm","?&q=2&type=UA","SafeactGrid","pager");
			}
			else{
				filterString += '&type=UC';
				//alert("UC filter"+filterString);
				//alert("Inside UC");
				processGridnew("unsafeactStatification_input.nnrm","?&q=2&type=UC","SafeactGrid","pager");		
			}
             return true;
	}
      return false;	
	}
  function validateFilterSelection(filterString){
    return  true;
}
  
	jQuery('#btnGraph').click(function(){
		//alert(1111);
		//alert("URL:"+url);
		var type=jQuery('#hdntype').val();
		if(url=="safeactStatification_input.nnrm" && (jQuery("#chkUnsafeAct").is(":checked")==true)){
		//	if(type=="UA"){
		//	alert("Graph Inside UA");
			var rowid = jQuery("#UnSafeactGrid").jqGrid('getGridParam','selrow');
			var urlData = "safeactStratificationpie.nnrm?&type=UA";
			showGraphData(urlData);
		//	alert(urlData);
		}
		else{
			//alert("Graph Inside UC else");
			var rowid = jQuery("#UnSafeactGrid").jqGrid('getGridParam','selrow');
			var urlData = "safeactStratificationpie.nnrm?&type=UC";
			showGraphData(urlData);
			//alert(urlData);
		}
	});	

</script>
<form  id="frmSafeactStratification">
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}" />
<input type="hidden" id="hdnbtnview" name="hdnbtnview" value="" />
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
<div  style="padding-right:20px;margin-top:0px; width:500px;height:50px">
<span style="position:relative;top:5px\9;"><input id="btnGraph" class="easyui-button" style="padding-top:0;" type="button" value="Pie-Graph"/> </span>
	<label>UnSafe Act</label>
	<span style="border: solid 2px #c1c1c1; padding: 3px;"><input id="chkUnsafeAct" name="chkUnsafeAct" type="checkbox" /></span>
	<label>Unsafe Condition</label>
		<span style="border: solid 2px #c1c1c1; padding: 3px;"><input id="chkUnsafeCond" name="chkUnsafeCond" type="checkbox" /></span>
	
</div>
<br><br>

<div style="margin-top: -39px; margin-left:0px;">
<table id="UnSafeactGrid" ><tr><td/></tr></table>
<div id="pager" ></div>
</div>

<table id="SafeactGrid" ><tr><td/></tr></table>
<div id="pager" ></div>
</div>

	
</form>
	



