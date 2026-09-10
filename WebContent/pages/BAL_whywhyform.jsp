  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
  <script type="text/javascript">
  
  jQuery(document).ready(function(){
	  var yyMode = jQuery('#yyMode').val();
	  if(yyMode != null && yyMode != '' && yyMode != ' ')
	  {
		if(yyMode=='view')
		{
			 readOnlyFields('txtbdanWwno');	
			 disableUIButton('btnYYLink');
			 readOnlyFields('txtbdanPreventivemeasure');
		}
	  }
	  	var bdanNo = jQuery('#bdanNo').val();
		var dataString = '?q=2';
		if(bdanNo != null && bdanNo != '' && bdanNo != ' ')
		{
			jQuery('#txtbdanWwno').val(bdanNo);
			dataString += '&wwNo='+bdanNo;					
		}			
		
		processGridnew('yy_view.brdn',dataString,"yyGrid","","","","","yyComplete","");
  });
  /*commented for prototype purpose code belongs to yylink btnclick */
  jQuery("#btnYYLink").click(function(){
   if(jQuery("#yyModeField").val() != null && jQuery("#yyModeField").val() != '' && jQuery("#yyModeField").val() != ' ')
		{
			var finalAction = '';
			var phenomena = '';
			 var formName = ''; 
			if(jQuery("#yyModeField").val() == 'bd')
			{
				finalAction = jQuery('#txtbdanFinalaction').val();
				phenomena = jQuery("#cmbbdmsFinalphenomena").combobox('getText');
				if(finalAction != null && finalAction != '' && finalAction != ' ')
				{
					//if(phenomena != 'NOT DEFINED')		{	
					 								
						saveForm('frmBDMaster','Breakdown_yy.brdn?q=2&finalAction='+finalAction);	
				/*	}
					else
						alert("This is Undefined Phenomena");
						*/	
				}	
				else
					alert("Enter Final Action");
			}
			else if(jQuery("#yyModeField").val() == 'WOCompletion')
			{ 
			
				 var refDocId = jQuery('#txtwomsActivityid').val();
				 var yyNO = jQuery('#txtbdanWwno').val();
				 var womsKey = jQuery('#txtwomsKeyid').val();
				 var formType = null;
				 var forwardData = null;
				 if(refDocId != null && refDocId != '' && refDocId != ' ')
				 {
					 if(refDocId.substring(0,2) == 'BD')
						 formType = 'BD';
					 else if(refDocId.substring(0,2) == 'AB')
						 formType = 'ABN';
					 else
						 formType = 'GM';
				 }
				 var persistentData = null;
				 var forwardData = {'whywhyRefDocID':refDocId,'txtformType':formType,'womsKey':womsKey};
				 var names = Object.keys(forwardData);
				 	var fData ="";
					for(var i=0;i<names.length;i++ )
					{
						
						fData += names[i] + '='+escape(forwardData[names[i]]) +'&';	
					}
					
				 LoadPopUp("divBDWhywhy", 'whywhy_input.why?'+fData, true,null,"100%","10px",null, null, "Why Why Analysis",false, true,true);
				 //navigateToNextForm('whywhy_input.why','Why Why Analysis',forwardData,persistentData ); 
				//saveForm('frmWorkOrder','bdCompletion_save.work?q=2');	
			}		
		}
	/*
  
	/*jQuery("#btnYYLink").click(function(){
		 //navigateToNextForm('whywhy_input.why','Why Why Analysis' );
		LoadPopUp("divBDWhywhy", 'whywhy_input.why?'+fData, true,null,"100%","10px",null, null, "Why Why Analysis",false, true,true);			
	*/
	});
	function actionFormatterSave(cellvalue, options, rowObject) 
	{
		return " ";
	}
	function actionFormatterAM(cellvalue, options, rowObject) 
	{
		var rowId = options.rowId;
		var formatStr  = '<span id="am_'+rowId+'"' ;
			formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
			formatStr  +=  '</span>';
		return formatStr;	
	}
	function actionFormatterPM(cellvalue, options, rowObject) 
	{
		var rowId = options.rowId;
		var formatStr  = '<span id="pm_'+rowId+'"' ;
			formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
			formatStr  +=  '</span>';
		return formatStr;
	}
	function actionFormatterCI(cellvalue, options, rowObject) 
	{
		var rowId = options.rowId;
		var formatStr  = '<span id="ci_'+rowId+'"' ;
			formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		    formatStr  +=  '</span>';
		return formatStr;
	}
	function actionFormatterET(cellvalue, options, rowObject) 
	{
		var rowId = options.rowId;
		var formatStr  = '<span id="et_'+rowId+'"' ;
			formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
			formatStr  +=  '</span>';
		return formatStr;
	}
	function actionFormatterC(cellvalue, options, rowObject) 
	{
		 var rowId = options.rowId;
		 var formatStr  = '<span id="rc_'+rowId+'"' ;
			 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
			 formatStr  +=  '</span>';
		return formatStr;
	}
	function selectedRC(id) 
	{
		
		var formatStr  = '<span id="selectedRC"' ;
			formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
			formatStr  +=  '</span>';
		return formatStr;
	}
	function yyComplete()
	{
		if(jQuery('#txtbdanWwno').val() != null && jQuery('#txtbdanWwno').val() != '' && jQuery('#txtbdanWwno').val() != ' ')
			processAjaxCalls('setrc_values.brdn','?q=2&yyNO='+jQuery('#txtbdanWwno').val(),'setRCSuccess');
		
		 var dataString = '?q=2&wwmsKey='+jQuery('#txtbdanWwno').val();
		// alert("dataString "+dataString);
		 processGridnew('rootcauseBD_view.why','?q=2',"rootCauseGrid","","","","","RootCauseComplete","");
		 //proces}+Gridnew('rootcauseBD_view.why','?q=2',"PillarBDGrid","","","","","pillarComplete","");
		 processGridnew('pillarBD_view.why',dataString,"PillarBDGrid","","","","","pillarComplete","");
		/*if(jQuery('#txtbdanWwno').val() != null && jQuery('#txtbdanWwno').val() != '' && jQuery('#txtbdanWwno').val() != ' ')
			processAjaxCalls('setrc_values.brdn','?q=2&yyNO='+jQuery('#txtbdanWwno').val(),'setRCSuccess');
		else
			processGridnew('rootcause_view.brdn','?q=2&wwNo='+jQuery('#txtbdanWwno').val(),"rootCauseGrid","rootCausePager","","","","RootCauseComplete","");*/
	}
	function setRCSuccess(result)
	{
	
		jQuery("#hdnRCID").val(result.rcId);
		jQuery("#hdnIsJh").val(result.isJH);
		jQuery("#hdnIsPM").val(result.isPM);
		jQuery("#hdnIsCI").val(result.isCI);
		jQuery("#hdnIsET").val(result.isET);
		//alert(" hdnRCID "+jQuery("#hdnRCID").val()+" hdnIsJh "+jQuery("#hdnIsJh").val()+" hdnIsPM "+jQuery("#hdnIsPM").val()+" hdnIsCI "+jQuery("#hdnIsCI").val()+" hdnIsET "+jQuery("#hdnIsET").val());
		 //var dataString = '?q=2&wwmsKey='+jQuery('#txtbdanWwno').val();	
		//processGridnew('rootcauseBD_view.why','?q=2',"rootCauseGrid","","","","","RootCauseComplete","");
		 //processGridnew('pillarBD_view.why',dataString,"PillarBDGrid","","","","","pillarComplete","");
		//processGridnew('rootcause_view.why','?q=2&wwNo='+jQuery('#txtbdanWwno').val(),"rootCauseGrid","rootCausePager","","","","RootCauseComplete","");
	}
	function pillarComplete()
	{  
		if(screen.width >=1250)
		{
			jQuery( "#PillarBDGrid" ).setGridWidth(350);
		}
	}
	function RootCauseComplete()
	{
		if(screen.width >=1250)
		{
			jQuery( "#rootCauseGrid" ).setGridWidth(350);
			
		}
		
		var rowID =  jQuery("#rootCauseGrid").jqGrid('getRowData');
		var selectedRC = null;				
		for(var i=1;i<=rowID.length;i++)
		{
			
			if(jQuery("#rootCauseGrid").jqGrid('getCell',i,'txtWwmsrootcauseid') == jQuery("#hdnRCID").val())
			{
				selectedRC = i;
				jQuery("#rc_"+selectedRC).html("&#10003");
			}
		}
	
		/*if(jQuery("#hdnIsJh").val() == 'Y')
			jQuery("#am_"+selectedRC).html("&#10003");	
		if(jQuery("#hdnIsPM").val() == 'Y')
			jQuery("#pm_"+selectedRC).html("&#10003");
		if(jQuery("#hdnIsCI").val() == 'Y')
			jQuery("#ci_"+selectedRC).html("&#10003");
		if(jQuery("#hdnIsET").val() == 'Y')
			jQuery("#et_"+selectedRC).html("&#10003");*/
	}
	
</script>
         <table>
         
		<tr style="padding-left:0px;">
        	<td  class="sub-header" style="padding-right:470px;">Why Why Analysis</td>
        	<td  style="padding-right: 23px;"   >
        	<input type="text" class="easyui-text" readonly="readonly" id="txtbdanWwno" name="txtbdanWwno" value="${requestScope.bdmTlWhywhymst.wwmsKeyid}" style="width: 90px" <c:out value = "${requestScope.bdFormBean.disablebdmsEntrydate == true ? ' disabled':''}"/>/></td>
        	<td><input type="button" class="easyui-button"  value="Why Why Link" id="btnYYLink" style="height: 22px"/></td>
        </tr>
        
        </table>
        <div class="floatleft" style="padding-left:0px;margin-top:0px;">	
	        <table id="yyGrid" width="400px" style=""></table>			
		</div>
		<div id="yyPager"></div>  
        <div style="clear: both"></div>
        <table>
        <tr><td>
        <div class="sub-header floatleft" style="width:98%;" >Root Cause</div>
        </td>
        <td><div class="sub-header floatleft" style="width:98%;">Counter Measure</div></td>
        <td><div class="sub-header floatleft" style="width:99%;padding-left:5px">Counter Measure Details</div></td>
        	<tr>
        	<td valign='top'>
        	 <div style="padding-left:0px;">	
        	
	<!--        <div class="floatleft sub-header" style=" width : 305px; height : 17px;margin-bottom:10px;margin-left:10px;">Kaizen idea and Schedule Details </div>	-->
	             <div class="" style="float:left;">
					 <table id="rootCauseGrid" width="400px" style="float: left;">
		        	 </table>
		        	 <div id="rootCausePager"></div>   
	       		</div>
        	</div>
        	</td>
        	<td valign='top'>
        	<div>
        	
				<table id="PillarBDGrid" width="400px" style="float: left;"></table>
				<div id="PillarBDPager"></div> 
				</div> 
        	</td>
        	<td>
        	
		       <div style="float: left;margin-top:4px;">
		       		<textarea style="width : 205px; height : 130px;resize:none" name="txtbdanPreventivemeasure" id="txtbdanPreventivemeasure" >${requestScope.bdmTlWhywhymst.wwmsPreventivemeasure}</textarea>
		       </div>
        	</td>
        	</tr>
        </table>
       
          
	     
		        
<!--	   <div style="float: left;margin-left:15px;">-->
<!--       		<textarea style="width : 305px; height : 195px;resize:none" name="txtbdanPreventivemeasure" id="txtbdanPreventivemeasure" >${requestScope.bdmTlDtl.bdanPreventivemeasure}</textarea>-->
<!--       </div>	-->
       
       <input type="hidden" id="yyModeField" name="yyModeField" value="${requestScope.whywhyMode}"/>
       <input type="hidden" id="yyMode" name="yyMode" value="${requestScope.formModeFlag}"/>
       <input type="hidden" id="hdnRCID" name="hdnRCID" value="${requestScope.rcId}">
	   <input type="hidden" id="hdnIsJh" name="hdnIsJh" value="${requestScope.isJH}">
	   <input type="hidden" id="hdnIsPM" name="hdnIsPM" value="${requestScope.isPM}">
	   <input type="hidden" id="hdnIsCI" name="hdnIsCI" value="${requestScope.isCI}">
	   <input type="hidden" id="hdnIsET" name="hdnIsET" value="${requestScope.isET}">
       