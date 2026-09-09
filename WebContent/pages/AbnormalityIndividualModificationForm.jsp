<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
 
 
<script type="text/javascript">
var Glbrowid=null;
jQuery.noConflict();

jQuery(document).ready(function(){
	initialiseForm('frmabnindividualmod');
	jQuery('#submitForm').val('frmabnindividualmod');
	var url = jQuery('#hiddenUrl').val();
	var keyid=jQuery('#hdnkeyid') ; 
	var rowId=jQuery('#hdnrowId') ;
    var logId=jQuery("#hdnLoginId").val();
	var FromDate = "";
	var ToDate="";
	var url = jQuery('#hiddenUrl').val();
	if(url=="AbnTagRemove_input.abnForm")
		jQuery('#completedBlock').hide();
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	if(url=="AbnModify_input.abnForm"){
		jQuery('#status').hide();
	   jQuery('#comptxt').hide();
	   jQuery('#com').hide();
	}
	if(url=="AbnCompletion_input.abnForm"){
        jQuery('#cboStatus').val('P');
	    
    }if(url=="AbnIndividualView_input.abnForm"){
    	jQuery('#status').hide();
	    jQuery('#comptxt').hide();
	    jQuery('#com').hide();
    }
		var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		if( prevDataUrl == null || prevDataUrl.length <=0){
			viewGrid(url,"&firstClick=Y");
		}
		else{
			viewGrid(unescape(prevDataUrl),"&q=1");
		}
		fillComboBox("frmabnindividualmod","cmbType","Combo_TagClass.abnForm");
		jQuery("#btnclear").click(function(){
			clearField("cboStatus");
			clearField("cmbType");
		});

	
		jQuery("#btnview").click(function(){
            var url = jQuery('#hiddenUrl').val();
            viewGrid(url,"&q=1");
            
    });
		
		
	});

	function viewGrid(url,filterString,tableCaption)
	{
		if(url.indexOf("?")!=-1)
            url = url.substring(0,url.indexOf("?"));
        
		var abnType=jQuery('#hdnAbnType').val();
		var elementid = jQuery("#hdnElementId").val();
		var status=jQuery("#cboStatus").val();		
		filterString+="&abnType="+abnType+"&elementid="+elementid;	
		if(url == "AbnIndividualModify_input.abnForm")
			filterString += "&modeType=modify&cmbdetectedBy="+jQuery("#hdnLoginId").val();
		else if(url == "HseModify_input.abnForm")
			filterString += "&mode=view&modeType=modify&cmbdetectedBy="+jQuery("#hdnLoginId").val();
		else if(url == "AbnCompletion_input.abnForm")
			filterString += "&modeType=complete&abnStatus=COMPLETE&mode=completion&modeType=complete&cmbResponsibility="+jQuery("#hdnLoginId").val();
		else if(url == "AbnAcceptance_input.abnForm")
			filterString += "&modeType=accecpt&cmbdetectedBy="+jQuery("#hdnLoginId").val() +"&cboabnstatus=C";


		var Type =getFieldValue("cmbType","frmabnindividualmod");
        var status =jQuery("#cboStatus option:selected").val();
        var datastrng =jQuery("#list").jqGrid('getGridParam','url');

        if(Type != null  || status != null)
        	filterString+="&cmbAbnmTagclassid="+Type+"&cboabnstatus="+status;

        if( validateFilterSelection(filterString))
		{
			if(jQuery('#chkAbnViewAfeem').is(':checked')==true)
				filterString +="&AFEEM=AFEEM";
			
            processGridnew(url,filterString,"list","pager","","nxtgrid","","abnormalityGridOncompleteLoad");
			return true;
		}
	}

	function chkFormatter(id, options, rowObject)
	{	
	    var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="checkbox" id="AbnCompcheckbox_'+rowId+'_'+colId+'" class="all" name="AbnCompcheckbox"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="" onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
	}

	function chkboxCheck(rowId) {
		var rowData = jQuery("#list").jqGrid('getRowData',rowId);
		var actnplnsts=rowData.ACTNPLNSTS;
		var TagNo =jQuery("#list").jqGrid('getCell', rowId,"TAGNO");
        if(actnplnsts=="P"){
			alert(" Complete all Action Plans to complete Abnormality ");
			jQuery('#AbnCompcheckbox_1_2').attr('checked', false);
			return false;
		}

     
		if(jQuery('#AbnCompcheckbox_'+rowId+'_2').is(':checked') == true)
			{Glbrowid=rowId;
			LoadPopUp("loadAbnrmltycom","AbnCompletionVerifypopup_modify.abnForm?&keyid="+TagNo, true,"60%","40%","10%","10%", "","Abnormality Completion"," "," " );
			}
	}

	
function loadAbnrmltycom_afterClose(){
var row=jQuery("#list").jqGrid('getDataIDs');
 for(i=1;i<=row.length;i++)	
 {		 
 var rowId=parseInt(i);
 var isChecked = jQuery('#AbnCompcheckbox_'+rowId+'_2').is(':checked');
 if(isChecked==true){
	 jQuery('#list input[id=AbnCompcheckbox_'+rowId+'_2]').attr('disabled',true);
	 jQuery("#list").jqGrid('setCell',rowId,"ITEM","",{'color':'#000','font-size':'12px','background-color':'green'});
    }
	}
	}
	
	function validateFilterSelection(filterString){
		return  true;
	}
	function abnormalityGridOncompleteLoad()
	{
		var rowIds = jQuery('#list').jqGrid().getDataIDs();
		
		for(var i=0;i<rowIds.length;i++)
		{
			var cellVal =jQuery('#list').getCell(rowIds[i],"STATUS");
			
			if(cellVal=="COMPLETED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"DETECTEDDATE","",{'color':'blue'});
			var tagClor = jQuery('#list').getCell(rowIds[i],"TAGCLASS");
			if(tagClor == "RED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"TAGNO","",{'color':'red'});
		} 		
    }
		  
		function nxtgrid(id) 
		{
	// alert('sk');
	  	  	var rowData = jQuery("#list").jqGrid('getRowData',id);																								
			var selId = rowData.machineId;
			//var record = rowData.Equipmentname;
			var grid = jQuery('#list');
			var sel_id = grid.jqGrid('getGridParam', 'selrow');
			var TagNo = grid.jqGrid('getCell', sel_id, 'TAGNO');
			var abnStatus=jQuery('#list').getCell(sel_id,"STATUS");
			var url = jQuery('#hiddenUrl').val();
			var tmpurl=url;
			var mode  = jQuery("#hdnFrmMode").val();
			var tableCaption = "Abnormality Modification";
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
			
			var filterString = "";
			if(url == "AbnModify_input.abnForm"){
				filterString += "&modeType=modify";
				tableCaption="Abnormality Modification";
			}
			else if(url == "AbnCompletion_input.abnForm" 
					|| url.substring(0,url.indexOf("?"))=="AbnCompletion_input.abnForm" ){
				filterString += "&modeType=complete";
				tableCaption="Abnormality Completion";
			}
			else if(url == "AbnAcceptance_input.abnForm"
				|| url.substring(0,url.indexOf("?"))=="AbnAcceptance_input.abnForm" ){
				filterString += "&modeType=accecpt";
				tableCaption="Abnormality Acceptance";
			}
			else if(url == "AbnIndividualView_input.abnForm"
				|| url.substring(0,url.indexOf("?"))=="AbnIndividualView_input.abnForm" ){
				mode="view";
				tableCaption="Abnormality View";
				//abnStatus="";
			}
			
			var tagClass=jQuery('#list').getCell(sel_id,"TAGCLASS");
			var abndKeyid =grid.jqGrid('getCell', sel_id, 'abndKeyid');
			
			var pageUrl = "Abnormality_input.abnForm";
			//var woId= jQuery('#list').getCell(sel_id,"MWNO");    "&WOID="+woId+
			var filterData = TagNo+"&tagClass="+tagClass+"&abnStatus="+abnStatus+"&mode="+mode+filterString;//alert("1stfrm"+filterData);
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			var hdnUrl = jQuery("#hiddenUrl").val();
			if(hdnUrl=="HseModify_input.abnForm")
				pageUrl="HseAbnormality_input.abnForm";
			//alert("url"+url);
       /** Modify By sugumar.k for reduce the doublrclick and grid reload on 19-Apr-2016**/
			//if(tmpurl == "AbnModify_input.abnForm")
				if(pageUrl=="Abnormality_input.abnForm")
				{
			LoadPopUp("loadAbnModify","Abnormality_input.abnForm?q=2&AbnId="+filterData+"&filterButton=false", true,"95%","90%","3%","1%", "popup_callback()","Abnormality Modify"," ",true,true );
				
				}
			else{
				navigateToNextForm(pageUrl+"?q=2&AbnId="+filterData+"&filterButton=false",tableCaption,null,{"filterString":url});
				
					}
			/******************************************************/    
	    }

		function frmFilter_enableDisableSuccessCallBack(){
			
			if(jQuery('input:checkbox[name=chkMonthwise]').attr('checked') == 'checked')
			{
				jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
				jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
				jQuery("#dtefromDate").datebox('disable');
				jQuery("#dtetoDate").datebox('disable');
				jQuery("#dtefromDate").datebox('clear');
				jQuery("#dtetoDate").datebox('clear');
			}
			else
			{
				jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
				jQuery("#dtefromMonth").datebox('disable');
				jQuery("#dtetoMonth").datebox('disable');
				jQuery("#dtefromMonth").datebox('clear');
				jQuery("#dtetoMonth").datebox('clear');
			}
			
		}

		/*function frmabnindividualmodcmbType_onSelect(record)
		{	
			var Type =getFieldValue("cmbType","frmabnindividualmod"); // jQuery("#cmbType").val();
			var datastrng =jQuery("#list").jqGrid('getGridParam','url');
			var urls = removeValueFromUrl(datastrng,"cmbAbnmTagclassid");
			urls +="&cmbAbnmTagclassid="+Type; 
			jQuery("#list").setGridParam({url:urls});
			jQuery('#list').trigger("reloadGrid");
		}*/
	/*	function frmabnindividualmodcmbType_onClear()
		{	
			//var Type =getFieldValue("cmbType","frmabnindividualmod"); // jQuery("#cmbType").val();
			var datastrng =jQuery("#list").jqGrid('getGridParam','url');
			var urls = removeValueFromUrl(datastrng,"cmbAbnmTagclassid");
			//urls +="&cmbAbnmTagclassid="; 
			jQuery("#list").setGridParam({url:urls});
			jQuery('#list').trigger("reloadGrid");
		}*/
			
		function formattorAttach(cellvalue, options, rowObject)
		{
			var formatStr  ="";
			if(cellvalue == "I"  ){
				
			formatStr  = '<input type="button" value="" ' ;
			
// 				formatStr  += ' style=background-image:url("../images/attach.jpg"); ';
				formatStr  += 'class="grdAttachimg"   ';
			
				formatStr  +=  '/>';
			}
			
			return formatStr.trim() == ''?" ": formatStr;
		}

</script>
<form id="frmabnindividualmod">
<div id="wrapperRpt" >
	<div id="completedBlock" Style="margin-top:-2px">
	<!-- 
	 	<table style="margin-top:-16px;">
		<tbody>
		<tr>
		
			<td>
			<div id="com">
				<span class="hse-comp"></span>
				</div>
			</td>
			<td>
			<div id="comptxt">
				<label style="color:black;font-weight: bold">Completed</label>
				</div>
			</td>
	
			<td>
				<span style="background-color:red;" class="hse-comp"></span>
			</td>
			<td>
				<label style="color:black;font-weight: bold">Red Tag</label>
			</td>
			<td>
			<div id="status"  style="margin-left:10%">
			<label>Status</label>
			<div>
			<select id="cboStatus" name="cboStatus" style="width:100px; height:21px;"   >				
				<option value=''>All</option>
				<option value='P'>Pending</option>
				<option value='C'>Completed</option>
			</select>
		    </div>
		    </div>
			</td>
			<td >
			     <div style="margin-left:10%">
			       <div>
	  			       <label>Tag Class</label>                       
	                 </div> 
		           <div class="easyui-paddingbfpx"> 
		              <input id="cmbType" name="cmbType" class="easyui-combobox"  style="width:100px; height:21px;" value=""  >                       
		                   </div>
		              </div>
			 </td>
			 <td>
			    <div style="margin-left:10%">
			        <input class="easyui-button" type="button" value="View" id="btnview"name="btnview" style="height: 25px;width:45px" />
			    </div>
			 </td>
			 <td>
			    <div style="margin-left:10%">
			         <input class="easyui-button" type="button" value="Clear" id="btnclear"name="btnclear" style="height: 25px;width:45px" />
		    	 </div>
			  </td>
		  </tr>
		 </tbody>
		</table> -->
	<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
	<input type="hidden" id="hdnLoginId" name="hdnLoginId" value="${requestScope.loginUser}" >
	<input type="hidden" id="hdnatnplnsts" name="hdnatnplnsts" value="" >
	<input type="hidden" id="hdnAbnkeyid" name="hdnAbnkeyid" value="" >
	 </div>	
	 <div style="" id="Abnmodify"> 
		<table id="list" ></table>
	<div id="pager"></div>
	</div>
</div>
</form>