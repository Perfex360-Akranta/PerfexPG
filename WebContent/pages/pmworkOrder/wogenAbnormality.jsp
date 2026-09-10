<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){

	var url = jQuery('#hiddenUrl').val();
	//alert(url.substring(url.indexOf('4'),url.indexOf('&wogen')));
	var wogen = url.substring(url.indexOf('4'),url.indexOf('&wogen'));
		
	var FromDate = "";
	var ToDate="";
	var url = jQuery('#hiddenUrl').val();
	if(url=="AbnTagRemove_input.abnForm")
		jQuery('#completedBlock').hide();
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;

	/*if(url=="AbnTagRemove_input.abnForm")
		dataString+="&abnStatus=removal";
	else if(url=="AbnModify_input.abnForm")
		dataString+="&abnStatus=modification";*/

		var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		if( prevDataUrl == null || prevDataUrl.length <=0)		
			viewGrid(url,"?q=1&firstClick=Y");
		else{
			viewGrid(unescape(prevDataUrl),"&q=1");
		}

		
		//viewGrid(url,dataString);
	});

	function viewGrid(url,filterString,tableCaption)
	{
		var abnType=jQuery('#hdnAbnType').val();
		filterString+="&abnType="+abnType;	
		if( validateFilterSelection(filterString))
		{
			processGridnew(url,filterString,"list","pager","","nxtgrid","","abnormalityGridOncompleteLoad");
			return true;
		}
	}
	function validateFilterSelection(filterString){
		return  true;
	}
	 function woGencheckBox(id, options, rowObject)
	  {
	  	var id = options.rowId;
	    	return '<input  type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
	  }

	  function selectData(rowId){

	  	var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
	  	jQuery("#list").setCell(rowId, "chkSelected","1");
	  	jQuery("#selectedDat").val(rowId);
		}

	  function unselectData(rowId){
	  	
			var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
		  	jQuery("#list").setCell(rowId, "chkSelected","0");																	
		
	  }
	function abnormalityGridOncompleteLoad()
	{
		var rowIds = jQuery('#list').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			var cellVal =jQuery('#list').getCell(rowIds[i],"Status");
			if(cellVal=="COMPLETED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"DetectedDate","",{'color':'blue'});
			var tagClor = jQuery('#list').getCell(rowIds[i],"TagClass");
			if(tagClor == "RED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"TagNo","",{'color':'red'});
			
		} 		
    }
		  
		function nxtgrid(id) 
		{
	  
	  	  	var rowData = jQuery("#list").jqGrid('getRowData',id);																								
			var selId = rowData.machineId;
			//var record = rowData.Equipmentname;
			var grid = jQuery('#list');
			var sel_id = grid.jqGrid('getGridParam', 'selrow');
			var TagNo = grid.jqGrid('getCell', sel_id, 'TagNo');
			var url = jQuery('#hiddenUrl').val();
			
			if(url=="AbnModify_input.abnForm")
				tableCaption="Abnormality Modification";
			else if(url=="AbnHTAView_input.abnForm")
				tableCaption="HTA Abnormality View";
			else if(url=="AbnSOCView_input.abnForm")
				tableCaption="SOC Abnormality View";
			else if(url=="AbnUNSView_input.abnForm")
				tableCaption="UNS Abnormality View";
			else if (url=="AbnTagRemove_input.abnForm")
				tableCaption="Abnormality Removal Tag";
			
			
			var tagClass=jQuery('#list').getCell(sel_id,"TagClass");
			
			var abnStatus=jQuery('#list').getCell(sel_id,"Status");
			var mode  = jQuery("#hdnFrmMode").val();
			var woId= jQuery('#list').getCell(sel_id,"MWNo");
			var filterData = TagNo+"&tagClass="+tagClass+"&abnStatus="+abnStatus+"&mode="+mode+"&WOID="+woId;//alert("1stfrm"+filterData);
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			
			navigateToNextForm("Abnormality_input.abnForm?q=2&AbnId="+filterData+"&filterButton=false",tableCaption,null,{"filterString":url});
			
		 
	}


</script>
<div id="">
	<div id="completedBlock">
	
	 	
	
	 
	<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
	<input type="hidden" id="selectedDat" name="selectedDat"/>
	 </div>	
	 <div style="" id="Abnmodify"> 
		<table id="list" ></table>
	<div id="pager"></div>
	</div>
</div>