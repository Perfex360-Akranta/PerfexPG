<!-- Created By: Siddharth.A -->

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>	 

jQuery.noConflict();
jQuery(document).ready(function()
{	
    
	jQuery('#submitForm').val('frmpcsEnableDisable'); // set the id of form to submit
	initialiseForm('frmpcsEnableDisable');		
	//viewGrid("Sparepickup_input.sprpckup","?row=0");
	/** FOR FILLING COMBOBOX **/
	//fillComboBox("frmpcsEnableDisable","cmbpelcFactoryid","factroyCombo.commonFilter" );
	//fillComboBox("frmpcsEnableDisable","cmbpunsMachineid","machineCombo.commonFilter");
	//fillComboBox("frmpcsEnableDisable","cmbpelcOption","pcsEnableDisable_OptionsCombo.pcsEnable");
	viewGrid("pcsEnableDisableMCH_input.pcsEnable");
	//jQuery('#cmbpelcCellid').combobox('disable');
	//jQuery('#cmbpelcCostCenter').combobox('disable');
	//combo_onSelect();
	fillComboBox("frmpcsEnableDisable","cmbpelcCellid","cellCombo.commonFilter");
	/* for functionalLocation*/
	var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
	var sectionId = jQuery("#frmpcsEnableDisable input[id='section']").val();
	var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
	var machId = jQuery("#frmpcsEnableDisable input[id='machine']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
	
	loadFunctionalLocation("pelcfunLocation","functionalLoc.pcsEnable","pelcfunLocationValues","frmpcsEnableDisable",dataStr);
	/*---------*/	
	

});

	function viewGrid(action)
	{
		//var factId =jQuery('#cmbpelcFactoryid').combobox('getValue');
		//var sectId=jQuery('#cmbpelcLineid').combobox('getValue');
		//var cellId=jQuery('#cmbpelcCellid').combobox('getValue');
		
		var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
		var sectId = jQuery("#frmpcsEnableDisable input[id='section']").val();
		var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
		var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();

		
		processGridnew(action,"?factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&flid="+flid,"pcsEbleDbleGrid" ,"pcsEbleDblePager","","","","pcsEbleDbleGrid_completeCallBack");
	
		//processGridnew("pcsEnableDisableMCH_input.pcsEnable","?factId="+factId+"&sectId="+sectId+"&cellId="+cellId,"pcsEbleDbleGrid" ,"pcsEbleDblePager","","","","pcsEbleDbleGrid_completeCallBack");
	}
	function frmpcsEnableDisablecmbpelcFactoryid_onLoadSuccess(){
		fillComboBox("frmpcsEnableDisable","cmbpelcLineid","sectionCombo.commonFilter");
	}
	function frmpcsEnableDisablecmbpelcLineid_onLoadSuccess(){
		fillComboBox("frmpcsEnableDisable","cmbpelcCellid","cellCombo.commonFilter");
	}
	function frmpcsEnableDisablecmbpelcCellid_onLoadSuccess(){
		fillComboBox("frmpcsEnableDisable","cmbpelcCostCenter","costCenter.commonFilter");
	}

	function frmpcsEnableDisablecmbpelcCostCenter_onLoadSuccess(){
	}

	function frmpcsEnableDisable_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		if(keyIds.cellId.trim()!="undefined" && keyIds.cellId.trim()!="null" && keyIds.cellId.trim()!=null)
		{	
			setFieldValue('cmbpelcCellid',keyIds.cellId);
			setComboDefaultValue("frmpcsEnableDisable","cmbpelcCostCenter");
		}	
		jQuery("#cmbCostCenter").combobox('clear');
		reloadCombo("frmpcsEnableDisable","cmbCostCenter","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  );
	}
	
	function pcsEbleDbleGrid_completeCallBack(viewRecord)
	{
		jQuery(".ui-jqgrid tr.jqgrow td").css("padding","0px");
		jQuery("td[aria-describedby=pcsEbleDbleGrid_txtEnable]").bind("click",function (e) {
	       
	        var rowId = jQuery(e.target).closest("tr.jqgrow").attr("id");

		    var cellVal =jQuery('#pcsEbleDbleGrid').getCell(rowId,"txtEnable");
	        var txtVal = jQuery('#pcsEbleDbleGrid').getCell(rowId,"txtPelcIspcsenabled");
	        
	        
	        if((cellVal.indexOf('tick')>0)||(txtVal=='Y'))
		    { 
	        	var cssObj = {'font-weight' : 'bold','color' : 'red','font-size':'15px'};
	            jQuery(this).html("&#10005;").css(cssObj);
	       	    jQuery('#pcsEbleDbleGrid').setCell(rowId,"txtPelcIspcsenabled",'N');
		    }
		    
	        else if((cellVal.indexOf('cross')>0)||(txtVal=='N'))
		    {
	        	//var cssObj = {'font-weight' : 'bold','color' : 'blue','font-size':'20px'};
	        	jQuery("#pcsEbleDbleGrid").jqGrid('setCell',rowId,"txtEnable","",{'font-size':'15px','color':'blue'});
	    		jQuery(this).html("&#10003;");//.css(cssObj);
	       	//	jQuery('#pcsEbleDbleGrid').setCell(rowId,"txtEnable",'','',{ bgcolor:'blue', weightfont:'bold'});
		    	jQuery('#pcsEbleDbleGrid').setCell(rowId,"txtPelcIspcsenabled",'Y');
		    }
	        else if(cellVal.indexOf('blank')>0)
		    {
	        	jQuery("#pcsEbleDbleGrid").jqGrid('setCell',rowId,"txtEnable","",{'font-size':'15px','color':'blue'});
			   	jQuery(this).html("&#10003;");
		    	jQuery('#pcsEbleDbleGrid').setCell(rowId,"txtPelcIspcsenabled",'Y');
		    }
		});
	}	  
	/** SUCCESS CALLBACK **/
	function frmpcsEnableDisable_successsCallback(result)
	{
		if(result!=null){
		jQuery('#pcsEbleDbleGrid').clearGridData();
		jQuery('#cmbpelcCellid').combobox('clear');
		jQuery('#cmbpelcCostCenter').combobox('clear');
		jQuery('#cmbpelcLineid').combobox('clear');
		jQuery('#cmbpelcFactoryid').combobox('clear');
		disablePcsEnableDisable("frmpcsEnableDisable",true);
		loadFunctionalLocation("pelcfunLocation","functionalLoc.pcsEnable","pelcfunLocationValues","frmpcsEnableDisable","");
		}
		
	}
	/** ERROR CALLBACK **/
	function frmpcsEnableDisable_errorCallback(msg)
	{
	
	}
	/** EXCEPTION CALLBACK **/
	function frmpcsEnableDisable_exceptionCallback()
	{
		
	}
	
	/** BEFORE SUBMIT **/
	function frmpcsEnableDisable_beforeSubmit()
	{
		var ColVal=null;
		if(jQuery('#cmbpelcType').val()=="S")
			ColVal="txtPelcSectionid";
		else if(jQuery('#cmbpelcType').val()=="M")
			ColVal="txtPelcMachineid";
		else if(jQuery('#cmbpelcType').val()=='C')
			ColVal="Cell";

		//if(jQuery('#chkExpansion').is(':checked')==true )
		var forCell ;
		/* if(jQuery('#chkForCell').is(':checked')==true ) */
		var type =jQuery('#cmbpelcType').combobox('getValue');
		//alert(type);
		if(type=="S")
			forCell = 'Y';
		else 
			forCell = 'N';
		
		var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();
		var gridData = '&flid='+flid+'&forCell='+forCell+'&PcsEnableDisable='+JqGridToJsonSelectdRows('pcsEbleDbleGrid','',ColVal);
		//alert('gridData='+gridData);
		return gridData; 
	}
	
	/** ONSELECT FACTORY **/
/*	function  frmpcsEnableDisablecmbpelcFactoryid_onSelect(record)
	{
		jQuery("#cmbpelcLineid").combobox('clear');
		jQuery("#cmbpelcCellid").combobox('clear');
		jQuery("#cmbpunsMachineid").combobox('clear');
		reloadCombo("frmpcsEnableDisable","cmbpelcLineid","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmpcsEnableDisable","cmbpelcCellid","cellCombo.commonFilter?factId="+record.id  );
		reloadCombo("frmpcsEnableDisable","cmbpunsMachineid","machineCombo.commonFilter?factId="+ record.id );
	}
*/
	/** ONSELECT SECTION **/
 /* function  frmpcsEnableDisablecmbpelcLineid_onSelect(record)
  {
	jQuery("#cmbpelcCellid").combobox('clear');
	jQuery("#cmbpunsMachineid").combobox('clear');
	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbpelcFactoryid");
	reloadCombo("frmpcsEnableDisable","cmbpelcCellid","cellCombo.commonFilter?sectId="+record.id  );
	reloadCombo("frmpcsEnableDisable","cmbpelcMachineid","machineCombo.commonFilter?sectId="+ record.id );
 }

	/** ONSELECT CELL **/	
  function  frmpcsEnableDisablecmbpelcCellid_onSelect(record)
  {
	
	//jQuery("#cmbpelcMachineid").combobox('clear');
	//reloadCombo("frmpcsEnableDisable","cmbpelcMachineid","machineCombo.commonFilter?cellId="+ record.id );
	loadFunctionalLocation("pelcfunLocation","functionalLoc.pcsEnable","pelcfunLocationValues","frmpcsEnableDisable","&cellId="+record.id);
	
	
	reloadCombo("frmpcsEnableDisable","cmbpelcCostCenter","costCenter.commonFilter?cellId="+ record.id );
	//fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbpelcLineid","cmbpelcFactoryid","","","cmbpelcCostCenter");
  }
  
  /** ONSELECT EQUIPMENT **/
 function  frmpcsEnableDisablecmbpelcMachineid_onSelect(record)
  {
	// fillcmbbox(record.id);
//	 fillMachineHierarchy("machineHierarchy.commonFilter",record.id, "cmbpelcCellid", "cmbpelcLineid", "cmbpelcFactoryid");
  }
 
  /** ONSELECT COST CENTER **/
 function frmpcsEnableDisablecmbpelcCostCenter_onSelect(record)
 {
	
 }

 function disablePcsEnableDisable(formId,enable)
	{
		var inputs = jQuery('#' + formId + ' :input');	
		var controlId ;

		jQuery(inputs).each(function () {
			controlId = this.id;		
			
			if( controlId.substring(0,3) == "cmb") {
				if(enable==true||enable=="true")
					jQuery("#"+controlId).not('#cmbpelcType').combobox("enable");
				else 
					jQuery("#"+controlId).combobox("disable");
			}
		});
 }

	
 function combo_onSelect()
 {
	 jQuery("#cmbpelcType").combobox(
	 {
		onSelect:function(recordid)
		{
			if(recordid.id=="C")
			{
				jQuery('#cmbpelcCellid').combobox('enable');
				jQuery('#cmbpelcCostCenter').combobox('enable');
			}
			else if(recordid.id=="M")
			{
				jQuery('#cmbpelcCellid').combobox('enable');
				jQuery('#cmbpelcCostCenter').combobox('enable');
			}
			else if(recordid.id=="S")
			{
				jQuery('#cmbpelcCellid').combobox('disable');
				jQuery('#cmbpelcCostCenter').combobox('disable');
				jQuery('#cmbpelcCostCenter').combobox('clear');
				jQuery('#cmbpelcCellid').combobox('clear');
			}
		}
	 });
	 
 }

  /** FORMATTERS FOR GRID **/
 function actionFormatterimageC(cellvalue, options, rowObject) 
 {
	 var rowId = options.rowId;
 	var formatStr  = '<span id="chkOption_'+rowId+'" ' ;
 	if(cellvalue == "Y" )
 		formatStr  += 'class="tick" style=\"color:blue;font-size:15px;\"> &#10003;';// tick 
 	else if(cellvalue=="N")	
 		formatStr  += 'class="cross" style=\"color:red;font-size:15px;\"> &#10005;'; //cross
 	else
 		formatStr +='class ="blank">';
 	//	jQuery('#' + "PcsEnableDisable" + ' span').addClass("img-swap");
 	formatStr  +=  '</span>';
 
 	return formatStr;
 }

 
  /** FORMATTER FOR OPTION **/
  function SelectFormatterOption(cellvalue, options, rowObject)
  {			
	 //alert("rr"+Object.keys(rowObject));
    // alert("kk"+Object.keys(options));	
   //alert(cellvalue);
 //  alert("6="+rowObject[5]);
  // alert(cellvalue); 
  // alert(cellvalue.substring(0,1) );
 	var rowId = options.rowId;
	
 	
 	/*if(cellvalue.trim()!=null && cellvalue.trim()!="")
 	{
 	 	/*alert('if');
 		var cc=jQuery("#combpelcOption_'"+ rowId +"' option:contains('"+ cellvalue +"')").val()
		alert('cc ='+cc);
 	 	return ;*/
 	 //	alert('combpelcOption_'+ rowId );
 	 	//return((jQuery('#combpelcOption_'+ rowId +'" option[value='+cellvalue.substring(0,1)+']').attr('selected', 'selected')).context);
 	 //	return("prev"+((jQuery('#combpelcOption_'+ rowId +'" option[value='+cellvalue.substring(0,1)+']').attr('selected', 'selected')).selector));
 	 	//return (Object.keys(jQuery('#combpelcOption_'+ rowId +'" option[value='+cellvalue.substring(0,1)+']').attr('selected', 'selected')));
 	// 	return  '<select id="combpelcOption_'+ rowId +'" value ="' + cellvalue.substring(0,1) + '" class="easyui-combobox" style="width:122px;" >'
	//	+'<option value ="' + cellvalue.substring(0,1)+'">'+ cellvalue+'</option>';
	 	 	//	jQuery('select').val(cellvalue); 
 	 	//length,prevObject,context,selector
 	//}
 	//else
 	// {	
 		//alert('else');
 	
 		if(cellvalue.trim()!=null && cellvalue.trim()=="HOUR")
 	 	{
 			return '<select id="combpelcOption_'+ rowId +'" value ="' + cellvalue.substring(0,1) + '" class="easyui-combobox"  style="width:122px;height:20px;"  >'
 			+'<option  value="H">HOUR</option>'
			+'<option value="S">SHIFT</option>'
			+'<option value="D">DAY</option>'
			+'<option value="W">WEEK</option> </select>' ;

 	 	 }
 		else if(cellvalue.trim()!=null && cellvalue.trim()=="DAY")
 	 	{
 			return '<select id="combpelcOption_'+ rowId +'" value ="' + cellvalue.substring(0,1) + '" class="easyui-combobox" style="width:122px;"  >'
 			+'<option value="D">DAY</option>'
 			+'<option value="S">SHIFT</option>'
 			+'<option  value="H">HOUR</option>'
			+'<option value="W">WEEK</option> </select>' ;

 	 	 }
 		else if(cellvalue.trim()!=null && cellvalue.trim()=="WEEK")
 	 	{
 			return '<select id="combpelcOption_'+ rowId +'" value ="' + cellvalue.substring(0,1) + '" class="easyui-combobox" style="width:122px;"  >'
 			+'<option value="W">WEEK</option> ' 
 			+'<option value="D">DAY</option>'
 			+'<option value="S">SHIFT</option>'
 			+'<option  value="H">HOUR</option></select>';
	 	 }
 		else 
 	 		{
			return '<select id="combpelcOption_'+ rowId +'" value ="' + cellvalue.substring(0,1) + '" class="easyui-combobox" style="width:122px;" disabled="disabled"  >'
		
 			+'<option value="S">SHIFT</option>'
			+'<option  value="H">HOUR</option>'
			+'<option value="D">DAY</option>'
			+'<option value="W">WEEK</option> </select>' ;
 	 		}
	
  }
  /** FORMATTER FOR QTY TIME BASED **/
  function SelectFormatterQtyortimebased(cellvalue, options,  rowObject)
  {					
 	var rowId = options.rowId;
 	
 	if(cellvalue.trim()!=null && cellvalue.trim()=="TIME")
 	{
 		return '<select id="combpelcQtyortimebased_'+ rowId +'" class="easyui-combobox" style="width:122px;" disabled="disabled" >'
		+'<option value="T">TIME</option>'
		+'<option value="Q">QUANTITY</option></select>';
 	}
 	else if(cellvalue.trim()!=null && cellvalue.trim()=="QUANTITY")
 	{
 		return '<select id="combpelcQtyortimebased_'+ rowId +'" class="easyui-combobox" style="width:122px;" >'
		+'<option value="Q">QUANTITY</option>'
 		+'<option value="T">TIME</option></select>';
		
 	 }
 	else 
 		{return '<select id="combpelcQtyortimebased_'+ rowId +'" class="easyui-combobox" style="width:122px;" disabled="disabled" >'
			+'<option value="T">TIME</option>'
			+'<option value="Q">QUANTITY</option></select>';
 		}
  }
  /** FORMATTER FOR MODEL **/
  function SelectFormatterModel(cellvalue, options, rowObject)
  {					
 	var rowId = options.rowId;
 	
 	if(cellvalue!=null && cellvalue.trim()=="H")
 	 {
 		return '<select id="combpelcModel_'+ rowId +'" class="easyui-combobox" style="width:150px;" >'
		+'<option value="H">HEAT TREATEMENT</option>'
		+'<option value="R">REGULAR</option>'
		+'<option value="P">PAINT SHOP</option></select>';

	 }	
 	else if(cellvalue!=null && cellvalue.trim()=="P")
	{
 		return '<select id="combpelcModel_'+ rowId +'" class="easyui-combobox" style="width:150px;" >'
 		+'<option value="P">PAINT SHOP</option>'
 		+'<option value="R">REGULAR</option>'
 		+'<option value="H">HEAT TREATEMENT</option></select>';
 	}
 	else
 	 {	
 	return '<select id="combpelcModel_'+ rowId +'" class="easyui-combobox" style="width:150px;" disabled="disabled" >'
			+'<option value="R">REGULAR</option>'
			+'<option value="H">HEAT TREATEMENT</option>'
			+'<option value="P">PAINT SHOP</option></select>';
 	 }

  }
 /**  FORMATTER FOR GROUP BASED  **/
  function groupBasedFormatter(cellvalue, options, rowObject)
  {
	 var id = options.rowId;
	return '<input type="checkbox"  id="grpBased_checkbox"   ' + ' onclick="if(this.checked){grpBasedBoxClick(\''+id + '\')}else{grpBasedBoxUnchecked(\''+id+'\')} "/>';
	//<c:out value = "${ requestScope.pcsTlEnablelosscapture.pelcIsgroupbased == 'Y' ? ' checked' : ''}"/>
  }

  function grpBasedBoxClick(id)
  {
	// if(jQuery('#grpBased_checkbox').is(':checked'))
	//		alert('k');
	jQuery('#pcsEbleDbleGrid').setCell(id,"txtPelcIsgroupbased",'Y');
  }
  function grpBasedBoxUnchecked(id)
  {
	jQuery('#pcsEbleDbleGrid').setCell(id,"txtPelcIsgroupbased",'N');
  }

/**  END OF GROUP BASED  **/
 
	
 	
 	/**  CODE FOR VIEW BUTTON  **/
  jQuery('#btnViewRecrds').click(function()
  {
		//var factId =jQuery('#cmbpelcFactoryid').combobox('getValue');
		//var sectId=jQuery('#cmbpelcLineid').combobox('getValue');
		//var cellId=jQuery('#cmbpelcCellid').combobox('getValue');
		
		var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
		var sectId = jQuery("#frmpcsEnableDisable input[id='section']").val();
		var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
		var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();
		
		if(jQuery('#cmbpelcType').val()=="S")
		  {
			// var sectId=jQuery('#cmbpelcLineid').combobox('getValue');
			 processAjaxCalls("pcsEnableDisableSECT_getCount.pcsEnable?sectId="+sectId,"","checkSectionCntSuccess");
		  }
		  
		  else if(jQuery('#cmbpelcType').val()=="C")
		  {
			  viewGrid("pcsEnableDisableCELL_input.pcsEnable");
		  }
		  else if(jQuery('#cmbpelcType').val()=="M")	
		  {
			  if(sectId==null || sectId.trim()=="" )
			  { 
				  alert("Select Section");
				  return false;
			  }
			  if(cellId==null || cellId.trim()=="" )
			  { 
			 	  alert("Select Cell");
			  	  return false;
			  }
			  else 
			  {
			   	  viewGrid("pcsEnableDisableMCH_input.pcsEnable");
			   	 disablePcsEnableDisable("frmpcsEnableDisable",true);
			  }
		  }
	
		 
			
		  	setTimeout(function() {
		    var type = jQuery('#cmbpelcType').val();
		  	var rowIds = jQuery('#pcsEbleDbleGrid').jqGrid().getDataIDs();
			//alert("length="+rowIds.length);
			for(var i=0;i<=rowIds.length;i++)
			{
				//alert(factId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcFactoryid", factId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcLineid", sectId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcCellid", cellId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcType", type);
			} 
		 	},1250);		
	
	
  });

  function checkSectionCntSuccess(result)
  {
		if(result.jsonObject=="exists")
	 	{
			alert('Already Exists'); 	
		}
		else
		{
	 	     viewGrid("pcsEnableDisableSECT_input.pcsEnable");
		}
  }

  function checkCellCntSuccess(result)
  {
		if(result.jsonObject=="exists")
	 	{
			alert('Already Exists in Cell'); 	
		}
		else
		{
	 	     viewGrid("pcsEnableDisableCELL_input.pcsEnable");
		}
  }

  function checkMachineCntSuccess(result)
  {
		if(result.jsonObject=="exists")
	 	{
			alert('Already Exists'); 	
		}
		else
		{
	 	     viewGrid("pcsEnableDisableMCH_input.pcsEnable");
		}
  }
	

</script>
<div id="wrapperRpt">		
<div class="main-cntborder" align="center" style="height: 450px;width:1050px;margin-top:-8px;margin-left:0px;margin-top:0px\9;">
 			
<form name="frmpcsEnableDisable" id="frmpcsEnableDisable">

 
<table    align="center" style="border: 0px;text-align: left;margin: 2px;">
<tr>
<td colspan="2" style="width:65.5%;margin-left:10px;">
<div id="pelcfunLocation" style="padding-left:50px;padding-left:0px\9;text-align:left;margin-top: 5px;"></div>
</td>
</tr>



<tr>

<td valign="top" width="30%" style="padding-left:50px;padding-left:0px\9;;">

	<!-- <div   class="mandatory-lbl" > <label>Factory</label></div>
		<div class="easyui-paddingbfpx">
			<input id="cmbpelcFactoryid" name="cmbpelcFactoryid" class="easyui-combobox"  style="width:255px;"  value="${requestScope.pcsTlEnablelosscapture.pelcFactoryid}" >
		</div>
	 
		<div class="mandatory-lbl" > <label>Section</label></div>
		<div class="easyui-paddingbfpx">
			<input id="cmbpelcLineid" name="cmbpelcLineid" class="easyui-combobox"  style="width:255px;" value="${requestScope.pcsTlEnablelosscapture.pelcLineid}" >
		</div>
	-->
	 <div style="width:255px;">
		<div   class="mandatory-lbl"  style="width:255px;"> <label>JH</label></div>
		<div class="easyui-paddingbfpx" style="width:255px;">
			<input id="cmbpelcCellid" name="cmbpelcCellid" class="easyui-combobox"  style="width:255px;" value="${requestScope.pcsTlEnablelosscapture.pelcCellid}" / >
		</div>
	
		<div   class="mandatory-lbl" style="width:255px;"> <label>Cost Center</label></div>
		<div class="easyui-paddingbfpx" style="width:255px;">
			<input id="cmbpelcCostCenter" name="cmbpelcCostCenter" class="easyui-combobox"  style="width:255px;" value="${requestScope.pcsTlEnablelosscapture.pelcCostCenter}" >
		</div>
	</div>
		
</td>
<td valign="top" style="padding-left:40px;padding-left:10px\9;">

		
		<div > <label>Type</label></div>
		<div class="easyui-paddingbfpx">
		<!-- 	<input id="cmbpelcType" name="cmbpelcType" class="easyui-combobox"  style="width:255px;" value="${requestScope.pcsTlEnablelosscapture.pelcType}"  > -->
			<select id="cmbpelcType" name="cmbpelcType" class="easyui-combobox" style="width:172px;" >
				<option value="M"> EQUIPMENT</option>
				<option value="S"> JH</option>
				<!-- <option value="C"> CELL</option> -->
			</select>
		<input type="hidden" id="hdnCellValue" name="hdnCellValue" value="" />
	
		<!-- 	<div id="spnForCell" style="padding-top: 20px;" >	
				<input type="checkbox" id="chkForCell" name="chkForCell" value="Y" >
				<label style="font-family:sans-serif; ;font-size: 12px;font-weight: bold;"> For Cell</label>
			</div>
	 -->
		</div>
	
</td>
<td valign="bottom">
	<div style="padding-left:40%;padding-left:23%\9">
		<input id="btnViewRecrds" name="btnViewRecrds" type="button" value="View Records" class="easyui-button" style=" height : 21px; width:90px;">
	</div>
</td>
</tr>
</table>
		<div  id="frmpcsEnableDisableFuntKeyIds"  >
				<input type="hidden" id="factory" name="cmbpelcFactoryid" value="${requestScope.pcsTlEnablelosscapture.pelcFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbpelcLineid" value="${requestScope.pcsTlEnablelosscapture.pelcLineid}"  ></input>
				<input type="hidden" id="cell" name="cmbpelcCellid" value="${requestScope.pcsTlEnablelosscapture.pelcCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbpelcMachineid" value="${requestScope.pcsTlEnablelosscapture.pelcMachineid}"  ></input>
				
				<input type="hidden" id="flid" name="cmbpelcFlid" value="${requestScope.requestScope.pelcFlid}"  ></input>
		</div>
<div style="margin-left:10px;margin-left:20px\9;">
<table id="pcsEbleDbleGrid" style="width:100%;">
	<tr><td/></tr></table>
	<div id="pcsEbleDblePager"></div>
	</div>
<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>	  
</form>
</div>
</div>