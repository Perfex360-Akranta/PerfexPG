<!-- Created By: Siddharth.A -->

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script><!--	 
jQuery.noConflict();
jQuery(document).ready(function()
{
	jQuery('#submitForm').val('frmpcsEnableDisable');
	initialiseForm('frmpcsEnableDisable');
	var url=jQuery("#hiddenUrl").val();
    fillComboBox("frmpcsEnableDisable","cmbSectionId","sectionCombo.commonFilter");
	var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
	var sectionId = jQuery("#frmpcsEnableDisable input[id='section']").val();
	var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
	var machId = jQuery("#frmpcsEnableDisable input[id='machine']").val();
	var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
    loadFunctionalLocation("pelcfunLocation","functionalLoc.balpcsEnable","pelcfunLocationValues","frmpcsEnableDisable",dataStr);
    viewGrid();
});
	function viewGrid()
	{
        var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
		var sectId = jQuery("#frmpcsEnableDisable input[id='section']").val();
		var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
		var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();
        processGridnew("pcsEnableDisableMCH_input.balpcsEnable","factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&flid="+flid,"pcsEbleDbleGrid" ,"pcsEbleDblePager","","","","pcsEbleDbleGrid_completeCallBack");
}
	
function frmpcsEnableDisablecmbpelcFactoryid_onLoadSuccess(){
	 // 	fillComboBox("frmpcsEnableDisable","cmbpelcFactoryid","sectionCombo.commonFilter");
		}
	function frmpcsEnableDisablecmbpelcLineid_onLoadSuccess(){
	//	fillComboBox("frmpcsEnableDisable","cmbpelcLineid","sectionCombo.commonFilter");	
	}
	function frmpcsEnableDisablecmbSectionId_onLoadSuccess(){
		var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();
	}
	function frmpcsEnableDisablecmbpelcCellid_onLoadSuccess(){
		fillComboBox("frmpcsEnableDisable","cmbpelcCostCenter","costCenter.commonFilter");
	}
	function frmpcsEnableDisablecmbpelcCostCenter_onLoadSuccess(){
	}	
	
	function frmpcsEnableDisable_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		var fact="";
		var flid=keyIds.flid;
		reloadCombo("frmpcsEnableDisable","cmbSectionId","sectionCombo.commonFilter?factoryId="+fact);
        viewGrid();	
	}
	
	/* function frmpcsEnableDisable_successsCallback(result){
		
    processGridnew("pcsEnableDisableMCH_input.balpcsEnable","factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&flid="+flid,"pcsEbleDbleGrid" ,"pcsEbleDblePager","","","","pcsEbleDbleGrid_completeCallBack");

	}  */
	
	//elumalai
	function frmpcsEnableDisable_successsCallback(result){
    jQuery('#pcsEbleDbleGrid').jqGrid('GridUnload');
    viewGrid();
} 

	
  function frmpcsEnableDisablecmbSectionId_onSelect(record){
	loadFunctionalLocation("pelcfunLocation","functionalLoc.balpcsEnable","pelcfunLocationValues","frmpcsEnableDisable","&sectId="+record.id);
 }


	function pcsEbleDbleGrid_completeCallBack(viewRecord)
	{   
		    //alert("completecallBack");
		    jQuery(".ui-jqgrid tr.jqgrow td").css("padding","0px");
		    //elumali
		    jQuery("td[aria-describedby=pcsEbleDbleGrid_txtEnable]").off("click.enable").on("click.enable",function (e) {
		    //jQuery("td[aria-describedby=pcsEbleDbleGrid_txtEnable]").bind("click",function (e) {
	        var rowId = jQuery(e.target).closest("tr.jqgrow").attr("id");
	        //alert("Row Id"+rowId);
		    var cellVal =jQuery('#pcsEbleDbleGrid').getCell(rowId,"txtEnable");
	       // alert("The CellVal"+cellVal);
	        var txtVal = jQuery('#pcsEbleDbleGrid').getCell(rowId,"txtPelcIspcsenabled");
	       // alert("The txtEnable"+txtVal);
	        if((cellVal.indexOf('tick')>0)||(txtVal=='Y'))
		    { 
	        	var cssObj = {'font-weight' : 'bold','color' : 'red','font-size':'15px'};
	            jQuery(this).html("&#10005;").css(cssObj);
	       	    jQuery('#pcsEbleDbleGrid').setCell(rowId,"txtPelcIspcsenabled",'N');
		    }
		    
	        else if((cellVal.indexOf('cross')>0)||(txtVal=='N'))
		    {
	        	jQuery("#pcsEbleDbleGrid").jqGrid('setCell',rowId,"txtEnable","",{'font-size':'15px','color':'blue'});
	    		jQuery(this).html("&#10003;");
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
/*	function frmpcsEnableDisable_successsCallback(result)
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
	} */
	
	/** ERROR CALLBACK **/
	function frmpcsEnableDisable_errorCallback(msg)
	{
	
	}
	/** EXCEPTION CALLBACK **/
	function frmpcsEnableDisable_exceptionCallback()
	{
		
	}
	
	/** BEFORE SUBMIT **/
		function frmpcsEnableDisable_beforeSubmit(){ 
		 	var ColVal=null;
	   		ColVal="txtPelcIspcsenabled";	
	   		var gridData= '&PcsEnableDisable='+JqGridToJsonSel('pcsEbleDbleGrid','txtEnable',ColVal);
	   	//	alert('gridData='+gridData);
	   		return gridData;	   		     			  	  			     			  	    			  	
}

	//elumalai
		function JqGridToJsonSel(jqGridId, checkBoxColName, ckeckForSelColName){
	        var allRows = jQuery("#" + jqGridId).jqGrid('getRowData');
	        var ids     = jQuery("#" + jqGridId).jqGrid('getDataIDs');
	        var jsonArrO = '[';

	        for (var i = 0; i < allRows.length; i++) {
	            var row = allRows[i];
	            jsonArrO += '{';

	            for (var colName in row) {
	                if (colName == 'grpBased_checkbox') {
	                    var val = jQuery('#grpBased_checkbox_' + ids[i]).is(':checked') ? 'Y' : 'N';
	                    jsonArrO += '"chkPelcIsgroupbased":"' + val + '" , ';
	                }
	                else if (checkBoxColName != colName) {
	                    var cellValue = parseJqGridCellValues(row[colName]);
	                    jsonArrO += '"' + colName + '":"' + cellValue + '",';
	                }
	            }
	            jsonArrO = jsonArrO.slice(0, -1) + "},";
	        }

	        jsonArrO = jsonArrO.slice(0, -1) + "]";
	        jsonArrO = (jsonArrO != ']' ? jsonArrO : "");
	        return jsonArrO;
	    }

	/*	  function parseJqGridCellValues(value){
       function JqGridToJsonSel(jqGridId,checkBoxColName,ckeckForSelColName){
		var allRows =jQuery("#"+jqGridId).jqGrid('getRowData');
		var jsonArrO='[';
	    var val="";
	   // var vall="";	
		for(var i = 0; i <allRows.length;i++){
			var row = allRows[i];			
			//  var value =row["txtEnable"];			
			 // if(value != null  &&  value.trim()  != "" &&  value.trim() != "0"){
				jsonArrO += '{';
				     for(var colName in row){
					   if(colName=='grpBased_checkbox'){
						var x = row[colName].indexOf("id=")+4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox")
							val = jQuery('#' +cellId).is(':checked') ? 'Y'
								:'N';
					//	if(jQuery("#"+cellId).attr("type")=="chkOption_")
						//	vall=jQuery('#' +cellId).is(':checked') ? 'Y'
							//		:'N';	
						//alert("Value1"+vall);
							//alert("value"+val);
					     //cellValue = "Y"; 							
				     jsonArrO += '"chkPelcIsgroupbased":"' +val +'" , ';	
				//	jsonArrO += '"PelcIspcsenabled":"' +val +'",';			
					}
					else if(checkBoxColName !=colName){
						var cellValue = parseJqGridCellValues(row[colName]);
						jsonArrO += '"'+colName +'":"' + cellValue +'",';
					}	
				}
				jsonArrO = jsonArrO.slice(0, -1) + "},";
	//	}
	//	}
} 
	// alert("The JsonArray"+jsonArrO);
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		return jsonArrO; 
	}*/

	  function parseJqGridCellValues(value){
		  if( value.indexOf("<input") >= 0 || value.indexOf("<select") >= 0 || value.indexOf("<textarea") >= 0){
			var id =parseInputJqGridTrStrings(value);	
			value=getFieldValue(id);
		}
		if( value.indexOf("<span") >= 0 ){
				
			value = "";
		}
		return value;
	}
		
	function parseInputJqGridTrStrings(value){
		var id = value.substr(value.indexOf("id=")+4);
		if( id.length > 0){
			if( id.indexOf("'") < id.indexOf(" ") && id.indexOf("'") != -1)
				id= id.substr(0,id.indexOf("'"));
			  else
				id= id.substr(0,id.indexOf('"'));
		}
	return id;
	}	
		
	/** ONSELECT FACTORY **/
	function  frmpcsEnableDisablecmbpelcFactoryid_onSelect(record)
	{ 
		
		jQuery("#cmbpelcLineid").combobox('clear');
		jQuery("#cmbpelcCellid").combobox('clear');
		jQuery("#cmbpunsMachineid").combobox('clear');
		var fact="";
		reloadCombo("frmpcsEnableDisable","cmbpelcLineid","sectionCombo.commonFilter?factoryId="+fact);	
	}
function frmpcsEnableDisablecmbpelcLineid_onSelect(record){
     //jQuery('#cmbpelcLineid').combobox('clear');
//	 reloadCombo("frmpcsEnableDisable","cmbpelcLineid","sectionCombo.commonFilter?factId="+record.id);   
	}
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
	loadFunctionalLocation("pelcfunLocation","functionalLoc.balpcsEnable","pelcfunLocationValues","frmpcsEnableDisable","&cellId="+record.id);
	reloadCombo("frmpcsEnableDisable","cmbpelcCostCenter","costCenter.commonFilter?cellId="+ record.id);
  }
  /** ONSELECT EQUIPMENT **/
 function  frmpcsEnableDisablecmbpelcMachineid_onSelect(record)
  {
	// fillcmbbox(record.id);
//	 fillMachineHierarchy("machineHierarchy.commonFilter",record.id, "cmbpelcCellid", "cmbpelcLineid", "cmbpelcFactoryid");
  }
 
  /** ONSELECT COST CENTER **/
  
  
  
  /*function frmpcsEnableDisablecmbSectionId_onSelect(record)
 {
	
	viewGrid();
 }*/

  
  function frmpcsEnableDisablecmbpelcFlid_onSelect(record){
  //   alert("The Flid"+record.id);
	// reloadCombo("frmpcsEnableDisable","cmbpelcFlid","sectionCombo.commonFilter?flid="+record.id); 
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
 		formatStr  += 'class="tick" style=\"color:blue;font-size:15px;\"> &#10003;';
 	else if(cellvalue=="N")	
 		formatStr  += 'class="cross" style=\"color:red;font-size:15px;\"> &#10005;';
 	else
 	  formatStr +='class ="blank">';
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
 		return '<select id="combpelcQtyortimebased_'+ rowId +'" class="easyui-combobox" style="width:122px;" enabled="enabled">'
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
 		{return '<select id="combpelcQtyortimebased_'+ rowId +'" class="easyui-combobox" style="width:122px;"  >'
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
 	return '<select id="combpelcModel_'+ rowId +'" class="easyui-combobox" style="width:150px;">'
			+'<option value="R">REGULAR</option>'
			+'<option value="H">HEAT TREATEMENT</option>'
			+'<option value="P">PAINT SHOP</option></select>';
 	 }

  }
 /**  FORMATTER FOR GROUP BASED  **/
   function groupBasedFormatter(cellvalue, options, rowObject)
  {
	//var id =options.rowId;
	//alert("The id"+cellvalue);
	//alert(id);
	//return '<input type="checkbox"  id="grpBased_checkbox"   ' + ' onclick="if(this.checked){grpBasedBoxClick(\''+id + '\')}else{grpBasedBoxUnchecked(\''+id+'\')} "/>';
	//<c:out value = "${ requestScope.pcsTlEnablelosscapture.pelcIsgroupbased == 'Y' ? ' checked' : ''}"/>
     var gridId = options.gid;
    //  alert("The GridID"+gridId);
      var checked ="";
	  if(cellvalue == "Y")
	  checked ="checked";		
    // return '<input id="grpBased_checkbox'+gridId+rowObject[1]+'" name="groupBasedFormatter'+gridId+rowObject[1]+'" '+ checked+' type="checkbox" />';
    //elumalai
     return '<input id="grpBased_checkbox_' + options.rowId + '" ' + checked + ' type="checkbox" />';
    // return '<input type="checkbox"  id="grpBased_checkbox"   ' + ' onclick="if(this.checked){grpBasedBoxClick(\''+id + '\')}else{grpBasedBoxUnchecked(\''+id+'\')} "/>';
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

  /* jQuery('#btnViewRecrds').click(function()
  {	   
		var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
		var sectId = jQuery("#frmpcsEnableDisable input[id='section']").val();
		var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
		
		if(jQuery('#cmbpelcType').val()=="S")
		  {
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
			for(var i=0;i<=rowIds.length;i++)
			{
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcFactoryid", factId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcLineid", sectId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcCellid", cellId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcType", type);
			} 
		 	},1250);			
  });   */
   
  /*jQuery('#btnViewRecrds').click(function()
  {   	
	    alert("BtnClicked");
		var factId = jQuery("#frmpcsEnableDisable input[id='factory']").val();
		var sectId = jQuery("#frmpcsEnableDisable input[id='section']").val();
		var cellId = jQuery("#frmpcsEnableDisable input[id='cell']").val();
		var flid = jQuery("#frmpcsEnableDisable input[id='flid']").val();
		
		if(jQuery('#cmbpelcType').val()=="S")
		  {
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
			for(var i=0;i<=rowIds.length;i++)
			{
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcFactoryid", factId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcLineid", sectId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcCellid", cellId);
				jQuery('#pcsEbleDbleGrid').setCell(rowIds[i],"cmbPelcType", type);
			} 
		 	},1250);		
  });*/


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
<td valign="top" width="30%" style="padding-left:35px;padding-left:0px\9;;">
<div class="mandatory-lbl" > <label>Shop</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbSectionId" name="cmbSectionId" class="easyui-combobox"  style="width:255px;" value="${requestScope.pcsTlEnablelosscapture.pelcLineid}" >
</div>
</td>
<td valign="top" style="padding-left:40px;padding-left:10px\9;">
<input type="hidden" id="hdnCellValue" name="hdnCellValue" value="" />
</td>
<td valign="bottom">

</td>
</tr>
</table>
		<div  id="frmpcsEnableDisableFuntKeyIds">
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