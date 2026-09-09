
<script>
jQuery(document).ready(function(){	
	var formMode=jQuery("#txtFormMode").val();
	var selId = jQuery("#hdnSelId").val();
	var url = jQuery('#hiddenUrl').val();
	jQuery('#submitForm').val('frmFiveSAudit'); 
	initialiseForm('frmFiveSAudit');	
	formatDateBox('dteFromMonth','dd-MMM-yyyy');
	formatDateBox('dteToMonth','dd-MMM-yyyy');
	var rptType = '';
	if(url == "FSAuditScoreRpt_view.fsas")
		rptType = 'S';
	viewGrid(url,"?q=2",rptType);
	jQuery('#btnGraph').hide();
	if(formMode=="view"){
		jQuery("#divtop").hide();
		jQuery("#Areatype").show();
		jQuery('#btnGraph').show();
	}


jQuery("#chkNoAudit").click(function(){
	
	jQuery("#chkSelfAudit").attr('checked',false);
	
});
jQuery("#chkSelfAudit").click(function(){
	
	jQuery("#chkNoAudit").attr('checked',false);
	
	
});


/*jQuery("#btnView").click(function(){
	
	var FromMonth =getFieldValue("dteFromMonth", "frmFiveSAudit");	
	var ToMonth = getFieldValue("dteToMonth", "frmFiveSAudit");
	var dataString ="";
	//alert("button");
	dataString += "?dtFromMonth="+FromMonth;
	dataString += "&dtToMonth="+ToMonth;
	//alert("button" +dataString);
	viewGrid("testreport_input.tstrep",dataString);
	
	
});*/
jQuery('#btnGraph').click(function(){
		var url = "chart.fsas";
		showGraphData(url);
});


});
function viewGrid(url,filterString,rptType)
{
	jQuery.cookie("filterString",filterString);
	
	if(rptType == 'undefined' ||rptType == undefined){
		rptType = getFieldValue('cboAreaType');
		
		
	}
	
	
	filterString += '&rptType='+rptType;
	processGridnew(url,filterString,"FSAudit","pager","FiveS Audit Score","","","FSAuditloadcomplete");
	return true;
		
}

function txtFormatter(id, options, rowObject)
{	
	
	var columnKey="";
	var color='';
	var id = options.rowId;
	var columnName = options.colModel.name;	
	var columnNo=columnName.substring(columnName.indexOf("_")+1);
	var columnVal =rowObject[columnName.substring(columnName.indexOf("_")+1)];
	var status= null;
	
	var colKeyId="";
	var monthYear=columnName.substring(0,columnName.indexOf("_"),0);
	
	if(columnVal!=null && columnVal!=undefined && columnVal!=""){
		
		if(columnVal.indexOf(";") > 0)
		{
			colKeyId=columnVal.substring(0,columnVal.indexOf(";"));
		
			colKeyId=columnVal.split(';');
			if(colKeyId.length >1)
				columnVal = colKeyId[2];
			else 
				columnVal = colKeyId[1];
			
				columnKey = colKeyId[0];
				if(columnKey.trim().length<=0)
					columnKey = "";	

				status=colKeyId[1];
						
			
        	if(columnVal=='0')
			columnVal="N/A";
        	else if(status=="S"){
				color='red';
            	}
        	else
        		status= "N";
       }
		else{
			columnVal="";
			}
	}
	else{columnVal="";}
	
		return '<input id="txtFsas_'+columnNo + '_'+id +'" onkeypress=keypress("'+columnKey+'","'+columnNo+'","'+id+'"); onfocus="gotFocuse(this.id)" onblur=noFocus("'+columnKey+'","'+columnNo+'","'+id+'"); onclick=fsAudit('+id+','+(parseInt(columnNo)+1)+'); type="text" keyId="'+columnKey+'" value="'+columnVal+'"  monthYear="'+monthYear+'" status="'+status+'" maxlength="5" style="width: 70px;text-align:right;background-color:'+color+'" / >';
	
}
function keypress(id,colId,rowId){
	
	var colValue = jQuery("#txtFsas_"+(colId)+"_"+rowId).val();
	if(colValue=="N/A"){
		jQuery("#txtFsas_"+(colId)+"_"+rowId).val('');
		return false;
}
}
function noFocus(id,colId,rowId) {
	
	var colValue = jQuery("#txtFsas_"+(colId)+"_"+rowId).val();
	
			if(colValue=="N/A"){
				jQuery("#txtFsas_"+(colId)+"_"+rowId).val();
				return true;
			}
			else if(parseFloat(colValue)>100){
					//alert("id"+(colId)+"_"+rowId);
					//alert("Value"+colValue);
					jQuery("#txtFsas_"+(colId)+"_"+rowId).val('');
					alert("Enter the Value Less than 100");
					return false;
					}
			else if(parseFloat(colValue)){
				
			var num=parseFloat(colValue);
			//alert("num"+num);
			jQuery("#txtFsas_"+(colId)+"_"+rowId).val(num.toFixed(2)); 
			 //alert("number"+num.toFixed(2));
			}

}
function gotFocuse(id) {
	numericTextBox(id);
}
function FSAuditloadcomplete(){
	var url = jQuery('#hiddenUrl').val();
	var fsaudit=jQuery("#FSAudit").jqGrid('getDataIDs');	
	var col=jQuery("#FSAudit").jqGrid ('getGridParam','colModel');
	
	for(i=0;i<fsaudit.length;i++)
	{
		for(j=12;j<col.length-1;j++){
 		var colkeyid = jQuery("#FSAudit").jqGrid('getCell',fsaudit[i],"KEYID_1");
 		  var colValue = jQuery("#FSAudit").jqGrid('getCell',fsaudit[i],col[j].name);
 		 //alert("col"+col.length);
		  if(colkeyid!= ' ' || colkeyid!= '' || colkeyid!= null ){
			 
			 if(colValue.substring(0,1)=='S')
				  jQuery("#FSAudit").jqGrid('setCell',fsaudit[i],col[j].name,colValue.substring(1),{'background-color':'red'});
		   }
			else {alert("value"+colValue);}
		  if(url == "FSAuditScoreRpt_view.fsas"){
			  setTotalRowCss("FSAudit");
			  jQuery("#" + fsaudit[fsaudit.length-2]).find("td").addClass('auditRow');	
				  }
	  }
	}  
	
	jQuery("#FSAudit").jqGrid( 'setGridParam',{onCellSelect:function(id,columnNo,cellcontent,e){
		 fsAudit(id,columnNo,cellcontent);
		 
		
		}});
}

function  fsAudit(id,columnNo,cellcontent){
	var fsaudit=jQuery("#FSAudit").jqGrid('getDataIDs');	
	for(i=0;i<fsaudit.length;i++)
	{
		var colkeyid = jQuery("#FSAudit").jqGrid('getCell',fsaudit[i],"KEYID_1");
	}
	var colm = jQuery("#FSAudit").jqGrid ('getGridParam', 'colModel');
	selId = colm[columnNo].name;
	
	var row=jQuery("#FSAudit").jqGrid('getDataIDs');	
	var col=jQuery("#FSAudit").jqGrid ('getGridParam', 'colModel');
	
	if(jQuery("#chkNoAudit").is(':checked')) {
		jQuery("#txtFsas_"+(columnNo-1)+"_"+id).attr('status',"N");
		jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color','#ffff');
		jQuery("#txtFsas_"+(columnNo-1)+"_"+id).val('N/A');
	
	}
	else if(jQuery("#chkSelfAudit").is(':checked')){
		var notaudit=jQuery("#txtFsas_"+(columnNo-1)+"_"+id);
		jQuery("#txtFsas_"+(columnNo-1)+"_"+id).attr('status',"S");
		if(notaudit.val() == 'N/A'){
			//notaudit=jQuery("#txtFsas_"+(columnNo-1)+"_"+id).val('N/A');
			//notaudit.val('');
			if(jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color')!='rgb(255, 0, 0)'){		
				jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color','red');
				jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('color','red');
			}
			else
			{
				 jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color','white');
				 jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('color','black');
			}
		}
		else
		{
			//alert(jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color'));
				 if(jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color')=='rgb(255, 0, 0)'){				
					jQuery("#txtFsas_"+(columnNo-1)+"_"+id).attr('status',"");
					jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color','white');
				 }
				 else
					 jQuery("#txtFsas_"+(columnNo-1)+"_"+id).css('background-color','red');
				
			 }
			}
	else{
		jQuery("#txtFsas_"+(columnNo-1)+"_"+id).attr('status',"N");
		}
	
}
function frmFiveSAudit_successsCallback(result){
	 jQuery("#FSAudit").trigger("reloadGrid");
}
function frmFiveSAudit_deleteSuccessCallback(result){
	jQuery("#FSAudit").trigger("reloadGrid");
	
}
function frmFiveSAudit_beforeSubmit(){	
	var gridData  = '&dataconversion='+convertJHFSAuditToJSONString();
	//alert(gridData);	
	return gridData; 
}
function convertJHFSAuditToJSONString(){
	
	var fsaudit=jQuery("#FSAudit").jqGrid('getDataIDs');	
	var cm=jQuery("#FSAudit").jqGrid("getGridParam","colModel");
	var rowid="";
	var MonthYear="";
	var controlId="";
	var colValue="";
	var cKeyId="";
	var jsonArrO='[';
	for(i=0;i<fsaudit.length;i++)
	{
		 rowid=fsaudit[i];
		 var colkeyid = jQuery("#FSAudit").jqGrid('getCell',fsaudit[i],"KEYID_1");
		
		 var SubArea= jQuery("#FSAudit").jqGrid('getCell',fsaudit[i],"SUBAREA_8");
		
		 var TeamLeader= jQuery("#FSAudit").jqGrid('getCell',fsaudit[i],"EMPID_3");
		
			
		  for(j=11;j<cm.length-2;j++){
			controlId="txtFsas_"+j+"_"+rowid;	
			colValue=jQuery('#' +controlId).val();
			if(colValue=="N/A"){
				colValue='0';}
			cKeyId=jQuery('#'+ controlId).attr('keyId');
			
			var status=jQuery('#' + controlId).attr('status');
			var MonthYear=jQuery('#' + controlId).attr('monthYear');
			
			if((colValue!=null && colValue!="")){ 
				
			
				jsonArrO+= '{';
				jsonArrO += '"txtFvadKeyid":"' + cKeyId +'",';
				jsonArrO+= '"txtFvadFvasKeyid":"'+colkeyid+'",';
				jsonArrO+= '"txtSubarea":"'+SubArea+'",';
				jsonArrO+= '"txtFvadResponsibilityid":"'+TeamLeader+'",';
				jsonArrO += '"txtFvadDate":"01-' + MonthYear +'",';
				jsonArrO += '"txtFvadStatus":"'+status+'",';
				jsonArrO += '"txtFvadValue":"' + colValue +'"},'; 
				
			}
			else{
					if(cKeyId!=undefined && cKeyId.trim().length>0  ){
					
						jsonArrO+= '{';
						jsonArrO += '"txtFvadKeyid":"' + cKeyId +'",';
						jsonArrO+= '"txtFvadFvasKeyid":"'+colkeyid+'",';
						jsonArrO+= '"txtSubarea":"'+SubArea+'",';
						jsonArrO+= '"txtFvadResponsibilityid":"'+TeamLeader+'",';
						jsonArrO += '"txtFvadDate":"01-' + MonthYear +'",';
						jsonArrO += '"txtFvadStatus":"'+status+'",';
						jsonArrO += '"txtFvadValue":"' + colValue +'"},'; 
					}
				}
		}
	}
	jsonArrO = jsonArrO.slice(0,-1)+ "]";
	//alert(jsonArrO);
	return jsonArrO; 	
}
function openGrid(){
	var filtStr=jQuery.cookie("filterString");
	var rptType = jQuery("#cboAreaType").val();
	viewGrid("FSAuditScoreRpt_view.fsas",filtStr,rptType);
	
}

</script>
<form name="frmFiveSAudit" id="frmFiveSAudit"> 
   
	
		<div id="wrapperRpt" style="margin-top: 5px;"> 
			<div class="easyui-paddingbfpx" id="divtop" style="margin-top:1%;width:100%;padding-left:30%">
              <span style="padding-left:50%;">
					<label id="lblNameControl" style="font-weight: 100;">Not Audited(NA) </label>
					<input id="chkNoAudit" type="checkbox" value="" name="txtFvadStatus" style="cursor: default;">
					<label id="lblGroupCat" style="font-weight: 100;">Self Audit Not Done </label>
					<input id="chkSelfAudit" type="checkbox" value="" name="txtFvadStatus"  style="cursor: default;">
				</span>
					
			</div>
			<span style="float: right;margin-right:-24%"><input id="btnGraph" class="easyui-button btn-HeightSmall" style="float: right" type="button" value="Graph" /></span>
			<input type="hidden" id="txtFormMode" name="txtFormMode"  value="${requestScope.formMode}"/>
			<div id="Areatype" style="display:none">
			<select id="cboAreaType"  name="cboAreaType" onchange="openGrid();" style="width:200px;">
								<option value="S">Shop</option>	
								<option value="O">Office</option>	
								
			</select></div>
			<input type="hidden" id="hdnId" name="hdnId" />			
			<div class="easyui-paddingbfpx"" >
			</div>
			<table id="FSAudit"><tr><td></td></tr></table>
			<div id="pager"></div>
		</div>
		<input type="hidden" id="mode" name="mode" value=""/>
	</form>

