<script>
         jQuery.noConflict();
         initialiseForm('frmAbnreportid');
	
		jQuery(document).ready(function(){
			//initialiseForm('hdnAbnType');
			setLoadFormCallBackFrmId("frmAbnreportid"); 
			invokeAfterLoadFormCallBack();
			//toggleCommonFilter();
			jQuery("#btnview").click(function() {
				  //var View=jQuery('#btnview').val();
				   //var View=jQuery('#list').jqGrid('getGridParam','selrow');
				  // var chkval=jQuery('#chkAbnmShutdownmaint').val();
			   //var chkval = getFieldValue("chkAbnmShutdownmaint");
			    // var chkval ='';
			     // if( jQuery("#chkAbnmShutdownmaint").is("Y"))
			  if( jQuery("#chkAbnmShutdownmaint").is(':checked')==true ){
			

				     
			     
			      /*if( jQuery("#chkAbnmShutdownmaint").val(View) ;
					      {
			    	  chkval == "Y";
				      }*/
			      //if (jQuery('#chkAbnmShutdownmaint').is(':checked') =="Y"){
         //var dataString = '?';
		/*if( jQuery("#chkAbnmShutdownmaint").attr("checked"))
			{
			IncludeinShutdownMaintenance += "&chkAbnmShutdownmaint=Y";
			}
		else
			IncludeinShutdownMaintenance += "&chkAbnmShutdownmaint=N";
		
			    	/*   if(IncludeinShutdownMaintenance == "Y" ){
			    		   enableFields('chkAbnmShutdownmaint');   }
			    	   else{
			    		   disableFields('chkAbnmShutdownmaint');
				    	   }*/
			   /*	if( jQuery("#chkAbnmShutdownmaint").attr("checked"))
				{
				dataString += "&chkAbnmShutdownmaint=Y";
				}
				else
				dataString += "&chkAbnmShutdownmaint=N";	
			
						var url = "chart.abnStrRpt";
						showGraphData(url+dataString);	
			});*/
			//processGridnew("url","q=2&IncludeinShutdownMaintenance="+IncludeinShutdownMaintenance,"list","pager","","");                 
			processGridnew("AbnRpt_input.abnRpt","type=Y","list","pager","","");   
			  
			  }else if( jQuery("#chkAbnmShutdownmaint").is(':checked')==false ){
			processGridnew("AbnRpt_input.abnRpt","?q=2","list","pager","","");
		  				//var type=jQuery("#chkoplmgeneral").val();
	  				//window.open("OplReport_Excelview.oplrpt?&oplId="+oplId+"&flid="+flid+"&type=general","Excel View");
				   //var WorkOrder =getFieldValue("chkWorkOrder");http://localhost:8080/perfexitc/AbnRpt_input.abnRpt
				  // processGridnew("AbnRpt_input.abnRpt","q=2&","&type=Y","list","pager","");
				  // processGridnew("AbnRpt_input.abnRpt","filterString","list","pager","");
				 // processGridnew("AbnRpt_input.abnRpt","q=2&IncludeinShutdownMaintenance="+IncludeinShutdownMaintenance,"list","pager","");
				  // processGridnew("AbnRpt_input.abnRpt","q=2&Include in Shutdown Maintenance="+IncludeinShutdownMaintenance,"list","pager","");		
		     	   //processGridnew("AbnRpt_input.abnRpt","q=2&Include in Shutdown Maintenance="+IncludeinShutdownMaintenance,"list","pager","");
			  }});
			
			//var url = jQuery('#hiddenUrl').val();
			//var dataStr="?q=2";			
			//viewGrid(url,dataStr);	

			/*var url = "AbnRpt_input.abnRpt";
			showcheckboxData(url+dataString);	*/
															
		});

jQuery("#chkAbnmShutdownmaint").click(function() {
    
	   jQuery('#chkAbnmShutdownmaint').attr('checked',true);

       if(jQuery("#chkAbnmall").is(':checked'))
          jQuery('#chkAbnmall').attr('checked',false);
    /*   else if(jQuery('#chkAbnmShutdownmaint').attr('checked',false));
       jQuery('#chkAbnmall').attr('checked',true);*/
});

jQuery("#chkAbnmall").click(function() {
    
 jQuery('#chkAbnmall').attr('checked',true);

 if(jQuery("#chkAbnmShutdownmaint").is(':checked'))
     jQuery('#chkAbnmShutdownmaint').attr('checked',false);
});
		function viewGrid(url,filterString)
		{
			var abnType=jQuery('#hdnAbnType').val();
			filterString+="&abnType="+abnType;						
			if( validateFilterSelection(filterString))
			{	//alert(filterString);

				if(jQuery('#chkAbnViewAfeem').is(':checked')==true)
					filterString +="&AFEEM=AFEEM";
				
				processGridnew(url ,filterString,"list","pager","","");
				
				return true;
			}
			return false;
		}

		function validateFilterSelection(filterString){
				return true;
		}
		function tickAction(cellvalue, options, rowObject) {	
			var formatStr  = '<span ' ;
			//if(cellvalue == "1")
			if(cellvalue.charCodeAt(0) == 252)
				formatStr  += ' style=\"color:blue;font-size:20px;\"> &#10003;';// tick 
			else
				formatStr =" ";
			
			formatStr  +=  '</span>';
			return formatStr.trim() == '</span>'?" ": formatStr;
		}
		
		function frmFilter_enableDisableSuccessCallBack()
		{
				
			enableDisableDatenMonthFilter();
			var url = jQuery('#hiddenUrl').val();			
			url = url.trim();
			disableField('frmAbnormalityRelated', 'cboAbndImprovementteam');
			if(url=="HSE_AbnRpt_input.abnRpt" || url == "HSE_AbnRptGen_input.abnGenRpt")
			{
				//jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");
				setTimeout(function() {readOnlyFields('cmbAbnmTypeid');},100);						
				//jQuery("#cmbAbnmTagclassid").combobox("setValue","TAG000003");	
				//setTimeout(function() {readOnlyFields('cmbAbnmTagclassid');},100);	
				setTimeout(function() {jQuery("#hdnCatgUrl").val("Combo_Category.abnForm?q=2&frmType=SHE");},1200);
				setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
				reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
				reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
			}			
			else if(url == "AbnHTAGeneral_input.abnGenRpt")
			{
				setTimeout(function() {readOnlyFields('chkdectbychkbox');},500);
				setTimeout(function() {readOnlyFields('chkdectdtchkbox');},500);
				setTimeout(function() {readOnlyFields('chkcauschkbox');},500);
				setTimeout(function() {readOnlyFields('chkAbnType');},500);
				setTimeout(function() {readOnlyFields('chkStatus');},500);
				setTimeout(function() {readOnlyFields('chkWhyWhyHappen');},500);
				setTimeout(function() {readOnlyFields('cmbResponsibility');},500);
			}
			else if(url == "AbnSOCGeneral_input.abnGenRpt")
			{
				setTimeout(function() {readOnlyFields('chkdectdtchkbox');},500);
				setTimeout(function() {readOnlyFields('chkcauschkbox');},500);
				setTimeout(function() {readOnlyFields('chkAbnType');},500);
				setTimeout(function() {readOnlyFields('chkWhyWhyHappen');},500);
				setTimeout(function() {readOnlyFields('chkHTAType');},500);
				setTimeout(function() {readOnlyFields('cmbResponsibility');},500);
			}
			else if(url == "AbnRptGen_input.abnGenRpt")
			{
				setTimeout(function() {readOnlyFields('chkdectbychkbox');},500);
				setTimeout(function() {readOnlyFields('chkdectdtchkbox');},500);
				setTimeout(function() {readOnlyFields('chkTrade');},500);
				setTimeout(function() {readOnlyFields('chkTagClass');},500);
				setTimeout(function() {readOnlyFields('chkStatus');},500);
				setTimeout(function() {readOnlyFields('chkHTAType');},500);
				setTimeout(function() {readOnlyFields('cmbResponsibility');},500);
			}
			else if(url == "AbnRpt_input.abnRpt")
			{
				enableFormFields('frmAbnormalityRelated', 'cboAbndImprovementteam');
			}
			
				jQuery("#chkdectbychkbox").attr('checked', true);
				jQuery("#chkdectdtchkbox").attr('checked', true);
				jQuery("#chkAbnType").attr('checked', true);
				jQuery("#chkabncatchkbox").attr('checked', true);
				

		}
		
	function frmAbnreportid_afterLoadCallBack(){
			toggleCommonFilter();		
	}
		
		
</script>

<form>
<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
	
	<span><div style="padding-top: 30px; padding-left:40px">
	<input type="checkbox" id="chkAbnmShutdownmaint" name="chkAbnmShutdownmaint"  value="N" >
	 <!--  <c:out value = "${ requestScope.abnormalityBean.disableForm == true ? ' disabled':''}"/>--> 
    <label>Include in Shutdown Maintenance </label> </span>
   
   <span style="padding-top: 30px; padding-left:40px">
    <input type="checkbox" id="chkAbnmall" name="chkAbnmShutdownmaint"  value="N" >
    <label>All</label>
    
    <span style="padding-top: 50px; padding-left:40px">
				 <input type="button" class="easyui-button"  value="view" id="btnview" style="height: 22px;width:90px;"/>
				 </div></span>
<div id="WrapperRpt">
	 <div> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	 </div>
</div>
</form>