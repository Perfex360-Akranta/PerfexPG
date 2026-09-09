<script>
	jQuery(document).ready(function ()
	{
		var url = jQuery('#hiddenUrl').val();
		var type = jQuery('#EmpwiseType').val();
		//alert("EmpwiseType:" + type+getFormMainHeader());
		
		if(type != "KZN"){
			jQuery("#btnShow").hide();
		//	alert("Hide:" + type);
		}else{
			jQuery("#btnShow").show();
		}
		 if(type!='KZN'){
			   jQuery("#btnTotalShow").hide();
			}
			 else{
			   jQuery("#btnTotalShow").show(); 
			 }
		var dataStr="?";			
	//	viewGrid(url,dataStr);	
	//	setLoadFormCallBackFrmId("frmabnsmryempwise");
		//viewGrid(url,dataStr); 
		

	jQuery("#btnTotalShow").click(function() {
		//  alert('clicked');
	  if( jQuery("#chkdmt").is(':checked')==true ){
		//  alert('dmt');
		processGridnew("AbnSmryEmpwiseKaizen_input.abnSmrRpt","?q=2&emptype=KZN","list","pager","","");   
	  
	  }else if( jQuery("#chkdmt").is(':checked')==false ){
	processGridnew("AbnSmryEmpwiseKaizen_input.abnSmrRpt","?q=2","list","pager","","");
				
	  }});
	
	var url = jQuery('#hiddenUrl').val();
	var filterString="?q=2";			
	viewGrid(url,filterString);	

	
													
});
	jQuery("#chkdmt").click(function() {
	    
		   jQuery('#chkdmt').attr('checked',true);

	       if(jQuery("#chkjh").is(':checked'))
	          jQuery('#chkjh').attr('checked',false);
	   
	});

	jQuery("#chkjh").click(function() {
	    
	 jQuery('#chkjh').attr('checked',true);

	 if(jQuery("#chkdmt").is(':checked'))
	     jQuery('#chkdmt').attr('checked',false);
	});
	function viewGrid(url,filterString)
	{					
		if( validateFilterSelection(filterString))
		{
			jQuery("#hiddenFileter").val(filterString);
			jQuery("#hdnflid").val(getFilterValue(filterString, 'flid'));
			jQuery("#hdnfromdate").val(getFilterValue(filterString, 'dtFromDate'));
			jQuery("#hdntodate").val(getFilterValue(filterString, 'dtToDate'));
			jQuery("#hdnfrommonth").val(getFilterValue(filterString, 'dtFromMonth'));
			jQuery("#hdntomonth").val(getFilterValue(filterString, 'dtToMonth'));
			processGridnew(url ,filterString,"EmpWiseAbnRpt","pagerid","","doubleClick");
			return true;
		}
		return false;
	}

	function validateFilterSelection(filterString){
			return true;
	}
function doubleClick()
{
	var url = jQuery('#hiddenUrl').val();
	var ftrStr = jQuery("#hiddenFileter").val();
	var rowid = jQuery("#EmpWiseAbnRpt").jqGrid('getGridParam','selrow');
	var rowDataassm = jQuery("#EmpWiseAbnRpt").jqGrid('getRowData',rowid);	
	//alert("rowDataassm"+rowDataassm._1); 
	//alert("rowDataassm"+rowDataassm._2);

	var ds="";   
	if (getFormMainHeader().indexOf("Kaizen")>0) {
	      ds ="?mode=view&empKeyId="+rowid;
        var flid=jQuery("#hdnflid").val();
        var fromdate=jQuery("#hdnfromdate").val();
        var todate=jQuery("#hdntodate").val();
        var frommonth=jQuery("#hdnfrommonth").val();
        var tomonth=jQuery("#hdntomonth").val();
	    if(flid.trim().length>0)
	       ds +='&empkzn=empkzn&flid='+flid+"&fromdate="+fromdate+"&todate="+todate+"&frommonth="+frommonth+"&tomonth="+tomonth;
		
		navigateToNextForm("KaizenView_input.kaizen"+ds,"",null,{"filterString":url});
	}
	else if (getFormMainHeader().indexOf("Abnormality")>0) {
		ds = "?mode=view&empabn=empabn&empKeyId="+rowDataassm._1;
		navigateToNextForm("AbnView_input.abnForm"+ds,"",null,{"filterString":url});
	}
	else if (getFormMainHeader().indexOf("OPL")>0) {
		ds = "?mod=view&empopl=empopl&empKeyId="+rowid;
		navigateToNextForm("oplVw_input.opl"+ds,"",null,{"filterString":url});
	}
}
//EMPM_NAME
jQuery('#btnShow').click(
		function() {
		//	alert("U clicked on button");
			var rowid = jQuery("#EmpWiseAbnRpt").jqGrid('getGridParam','selrow');
		
			if (rowid == null || rowid == ' ' || rowid == ''|| rowid == undefined) {
				alert("No Data Selected");
				return false;
			}
			//alert("Row id :" +rowid);
			var url = jQuery('#hiddenUrl').val();
			var ftrStr = jQuery("#hiddenFileter").val();
			if(ftrStr.length>1)
				ftrStr="?";
			
			if (getFormMainHeader().indexOf("Kaizen")>0){
				navigateToNextForm("employeeWiseMonthWiseTotal_input.kaizen?empKeyId="+rowid+"&"+ftrStr,"",null,{"filterString":url});
				
			}
		});
		

//TOTAL EMPM_NAME
jQuery('#btnTotalShow').click(
		function(){
			//var selArray =jQuery("#EmpWiseAbnRpt").jqGrid('getGridParam','selarrrow');
			//alert("The SelArray"+selArray);
			var selrowid="";
			var jsonArr='';
			  if(selArray !=null && selArray!=" " && selArray!=""){
			  for(var i=0;i<selArray.length;i++){
				selrowid=selArray[i];
			//	alert("selrowid::::"+selrowid);
		    var Keyid=jQuery("#EmpWiseAbnRpt").jqGrid('getCell', selrowid,"KEYID");
		 //   alert("The KeyId"+Keyid);		    
		    var Criteriasplit= Keyid.split(',');
		 	for(var k=0;k<Criteriasplit.length;k++){
			 	var Keyidval=Criteriasplit[k]; 
			 //	alert("Keyidval"+Keyidval);
			 	var keyvalSplit=Keyidval.split(";");
			 	var keyvalu=keyvalSplit[0];
			 	jsonArr += '"'+keyvalu + '",';	
	 	    }
		 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
			jsonArr += ',';
		}
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
		   if (selArray == null || selArray == ' ' || selArray == ''|| selArray == undefined) {
				alert("No Data Selected");
				return false;
			}
			var url = jQuery('#hiddenUrl').val();
			var ftrStr = jQuery("#hiddenFileter").val();
			if(ftrStr.length>1)
				ftrStr="?";		
			if (getFormMainHeader().indexOf("Kaizen")>0){
				//navigateToNextForm("employeeWiseMonthWiseTotal_input.kaizen?empKeyId="+selArray+"&"+ftrStr,"",null,{"filterString":url});
				navigateToNextForm("employeeWiseMonthWiseTotal_input.kaizen?empKeyId="+selArray+"&"+ftrStr+"&filterButton=false");
			}
			  }
		});		
	 
function frmabnsmryempwise_afterLoadCallBack(){
  toggleCommonFilter();	
	
}

	
</script>
<form>
	<div id="wrapperRpt">

		<!-- <div style="margin-left:100px">
			<input type="button" class="easyui-button" id="btnTotalShow"
				value="Show Month Wise" />
		</div>	
	 	<div style="margin-left:445px; margin-top:-20px;">			  
				 <input type="checkbox"  id="chkdmt" name="chkdmt"  value="N"/>
				 <label>DMT</label>	
				  </div>
				  <div style="margin-left:545px; margin-top:-15px;">			  
				 <input type="checkbox"  id="chkjh" name="chkjh"  value="N"/>
				 <label>JH</label>	
				  </div> --> 
		<div style="height: 80%; margin-top: -2px">
			<table id='EmpWiseAbnRpt'>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pagerid'></div>
		</div>
	</div>
	<input type="hidden" name="hiddenFileter" id="hiddenFileter" value="" />
	<input type="hidden" name="hdnflid" id="hdnflid" value="" />
	<input type="hidden" name="hdnfrommonth" id="hdnfrommonth" value="" />
	<input type="hidden" name="hdntomonth" id="hdntomonth" value="" />
	<input type="hidden" name="hdnfromdate" id="hdnfromdate" value="" />
	<input type="hidden" name="hdntodate" id="hdntodate" value="" />
	<input type="hidden" name="EmpwiseType" id="EmpwiseType" value="${EmpwiseType}" />
</form>