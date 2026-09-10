<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function()
	{
	var url = jQuery('#hiddenUrl').val();
	//alert(url);

	viewGrid(url,"&s=1");
	
	
	//alert("hiden mode  ");
	//alert("hiden mode  "+filterstring);
	 //processGridnew(url,"?q=2","gen_main","pager_genmain","","dblclick");
				
	});
function viewGrid(url,filterString)
{
		
	 if( validateFilterSelection(filterString))
	{//var url = jQuery('#hiddenUrl').val();
		//alert(url+""+filterString);generalMaintMould_modify.balgenmain
		var tableCaption = "Machine Activity Report";
		 processGridnew(url,filterString,"gen_main","pager_genmain","","dblclick","","gen_main_loadComplete");
		return true;
	}
	return false;	
}
function frmFilter_enableDisableSuccessCallBack()
{
	var url = jQuery('#hiddenUrl').val();
	//alert(url+""+filterString);
	clearField("dtefromDate");
	clearField("dtetoDate");
	disableField("frmFilter","dtefromDate");
	disableField("frmFilter","dtetoDate");
	setTimeout(function() {jQuery('#cbostatus')
    .empty()
    .append('<option selected="selected" value="A">All</option><option  value="P">Pending</option><option  value="C">Completed</option>');
	
	disableField("frmBD","chkoccurchkbox");
	disableField("frmBD","chktimechkbox");
	disableField("frmBD","chkallchkbox");
    },1250);

	
	if(url == "generalMaintMould_modify.balgenmain"){
		disableField("frmFilter","cboRelatedTo");
		setFieldValue("cboRelatedTo", "MLD","frmFilter");
		setTimeout(function() {enableFields("cmbMould");},1250);
		
	}
	else{
		enableFields("cboRelatedTo");
		setFieldValue("cboRelatedTo", "MCH","frmFilter");
		}
}
function validateFilterSelection(filterString){
	if(filterString == "&s=1" )
		return true;
	
	  if(jQuery('#chkDatewise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Select  FromDate");
			return false;
		}
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Select  ToDate");
			return false;
		}
	}
	else if(jQuery('#chkMonthwise').is(':checked') == true){
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select  FromMonth");
			return false;
		}
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
	} 
return true;if(filterString == "?q=2&firstClick=Y" || filterString.substring(0,4) == "?q=2")
	return true;
if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
	alert("Either Datewise or Monthwise Checkbox to be Selected");
	return false;
}
  if(jQuery('#chkDatewise').is(':checked') == true){
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
	{
		alert("Select  FromDate");
		return false;
	}
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
	{
		alert("Select  ToDate");
		return false;
	}
}
else if(jQuery('#chkMonthwise').is(':checked') == true){
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
	{
		alert("Select  FromMonth");
		return false;
	}
	if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
	{
		alert("Select  ToMonth");
		return false;
	}
} 
return true;
}
function dblclick(id)
{
	var rowData = jQuery("#gen_main").jqGrid('getRowData',id);																								
	//var docno = rowData.docno;
	//var WOID = rowData.workorderno;
	var docno = rowData.DOCNO;          //  uppercase
	var WOID = rowData.WORKORDERNO;     //  uppercase
	var menumode = jQuery('#hdnsetupandadj').val();
	
	var url  = jQuery('#hiddenUrl').val();
	var hdnMode =jQuery('#hdnfield').val();
	
	if(url == "generalMaint_modify.balgenmain" || url =="generalMaintMould_modify.balgenmain"){
		 hdnMode ="MODIFY";
	}
	else{//generalMaint_view.balgenmain
		if(menumode != ' ' && menumode != '' && menumode != null){
			if(url == "generalSetAndAdj_modify.balgenmain"){
				 hdnMode ="MODIFY";
			}
			else
				hdnMode ="VIEW"; 	
		}
		else
		 	hdnMode = "VIEW";
	}
	
	var filterData = '?q=2&docno='+docno;
	filterData +='&WOID='+WOID;
	//alert(hdnMode );
	if(menumode != ' ' && menumode != '' && menumode != null)
		filterData +='&menumode='+menumode; 
	 navigateToNextForm('generalMaintcreat_input.balgenmain?q=2&filterData='+filterData+'&vurl='+url+'&closeOnSave=true'+'&mode='+hdnMode,'');
	   
}
jQuery('#genMain').click(function(){
	 navigateToNextForm('generalMaintcreat_input.balgenmain','Machine Activity');
	/*jQuery("#GeneralMaintMain").load('generalMaint_newFrm.balgenmain','', function(response, status, xhr) {
		
		   if (status == "error") {
		    var msg = "Sorry but there was an error: ";
		   jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		  }
		});
	*/	
});
/*function gen_main_loadComplete()
{//alert('ds');
	var gen_mainId = jQuery("#gen_main").jqGrid('getDataIDs');
	 for(i=1;i<=gen_mainId.length;i++)	
	 {		///alert('ds' +jQuery("#gen_main").getCell(i, 'status'));	 
		if(jQuery("#gen_main").getCell(i, 'status')=="Completed")
			jQuery("#gen_main").jqGrid('setCell',i,"docno","",{'background-color':'#c0ffc0'});							
		else
			jQuery("#gen_main").jqGrid('setCell',i,"docno","",{'background-color':'#FDB8B8'});
	 }		
}*/
function gen_main_loadComplete()
{
    var gen_mainId = jQuery("#gen_main").jqGrid('getDataIDs');
    for(var i = 0; i < gen_mainId.length; i++)
    {
        var rowid = gen_mainId[i];
        var rowData = jQuery("#gen_main").jqGrid('getRowData', rowid);
        
        var status = rowData.STATUS || rowData.status || '';
        var woCol = rowData.WORKORDERNO !== undefined ? 'WORKORDERNO' : 'workorderno';
        
        if(status == "Pending" || status == "pending")
        {
            jQuery("#gen_main").jqGrid('setCell', rowid, woCol, "", {'background-color':'#FDB8B8'});
        }
        else if(status == "Completed" || status == "completed")
        {
            jQuery("#gen_main").jqGrid('setCell', rowid, woCol, "", {'background-color':'#c0ffc0'});
        }
    }
}
</script>
<form name="frmGenMaintMain" id="frmGenMaintMain" action="" method="post">
<div id="wrapperRpt">
<div style="margin-top:-20px;margin-top:0px\9;">
	<div style="" id="GeneralMaintMain"> 
	<input type=hidden id="second" class="easyui-text" value="b"/>
	<!--<div>
		<span class="bd-pending" style="margin-left:2%;">Pending</span>
		<span class="bd-completed" style="margin-left:2%;">Completed</span>
	</div>
	--><table>
		<tr>
			<td style=" width : 0px;"></td>
			<td style=" width : 90px;"><span class="bd-pending" ><label style="margin-left:25px;font-weight:bold;">Pending</label></span></td>
			<td><span class="bd-completed"><label style="margin-left:25px;font-weight:bold;">Completed</label></span></td>
		</tr>
	</table>
<!--	<div style="float:right;"><input type="button"  value="New Form" id="genMain" class="easyui-button" /></div><br><br>-->
	<input type="hidden"  value="" id="hiddenUrl" class="easyui-button" />
<table id="gen_main" ></table>
<div id="pager_genmain"></div>
</div>
<input type="hidden" id="hdnfield" name ="hdnfield" class="easyui-text" value=""/>
<input type="hidden" id="hdndrillValue" name ="hdndrillValue" class="easyui-text" value="${requestScope.drillId } "/>
<input type="hidden" id="hdnsetupandadj" name ="hdnsetupandadj" class="easyui-text" value="${requestScope.setupandadjustment } "/>
</div>
</div>
</form>