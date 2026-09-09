<script>
jQuery(document).ready(function(){	
	//alert("12");
	setLoadFormCallBackFrmId("frmProjectMain");
	invokeAfterLoadFormCallBack();
	
	//viewGrid("","");	
});

function frmProjectMain_afterLoadCallBack(){	
	toggleCommonFilter();		
}

function frmProjectMain_FuntLocHierarchy_SuccessCallBack(result){	
	var filterString = '&flid='+result.flId;	
	viewGrid("",filterString);	
}
function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{		 
		var type=jQuery("#hdnType").val();
		var stage=jQuery("#hdnStage").val();
		var approval=jQuery("#hdnApproval").val();
		var isClosure=jQuery("#hdnIsClosure").val();
		var isCheckList=jQuery("#hdnIsCheckList").val();		
		filterString+="&stage="+stage+"&approval="+approval+"&isClosure="+isClosure+"&checkList="+isCheckList+"&type="+type;

		var tableCaption = "FI Project Approval List";
		processGridnew("ChangeProLead_input.prpo", filterString, "projectgrid", "pager",tableCaption,"docDoubleClick","","loadProto_complete");
		return true;
	}
	return false;
}

function projectgrid_selectRow(rowId)
{
	var gridId="projectgrid";
	var frommode=jQuery('#hdnFormMode').val();

		if(jQuery('#jqg_'+ gridId +'_'+rowId).is(':checked')== true)
		 {
			// var chkid=jQuery("#projectgrid").jqGrid('getCell', rowId,"1");
	     	var Projectid =jQuery("#projectgrid").jqGrid('getCell', rowId,"PROJECTNUMBER");
			setFieldValue('hdnprjectid',Projectid);
			//alert("Projectid="+Projectid);
			processAjaxCalls("changeprojectchamp_select.prpo","?q=2&Keyid="+Projectid,"frmoldemployee_successsCallback"," ");
            setTimeout(function() {
            	var hdnoldemployeenameid = jQuery("#hdnoldemployeenameid").val();
    			//alert(hdnoldemployeenameid);
    			LoadPopUp("divChangeprolead","ChangeProLeadBankPopup_select.prpo?q=2&Projectid="+Projectid+"&oldemployeenameid="+hdnoldemployeenameid,true,"35%","30%","8%","8%","LoadSuccess","Change Project leader ","","");
    			
 		    },300);				
				}
		
		
}


function frmoldemployee_successsCallback(result)
 {	        

			var oldemployeenameid =result.Data[0][0];
			setFieldValue('hdnoldemployeenameid',oldemployeenameid);

			 }

function validateFilterSelection(filterString){
	return  true;
}

function loadProto_complete() {
	var row = jQuery("#projectgrid").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++){
		var rowId=	parseInt(i)+1;	
		var rowIds = row[i];
		var define = jQuery("#projectgrid").jqGrid('getCell',row[i],"DEFINE");
		var mStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"MEASURE");
		var aStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"ANALYSE");
		var iStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"IMPROVE");
		var cStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"CONTROL");
		var xStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"CLOSURE");
		//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus+",xStatus:"+xStatus);
		jQuery("#projectgrid").jqGrid('setCell',row[i],"D",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"M",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"A",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"I",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"C",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"X",'',{'color':'black','background-color':'#D9D9DB'});
		statusgridtracking(define,mStatus,aStatus,iStatus,cStatus,xStatus,row[i]);			
	}	
}

function statusgridtracking(define,mStatus,aStatus,iStatus,cStatus,xStatus,rowid){
	//alert('definedefine'+define);
	if (define=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#95FB94'});
	}
	else if(define=="P"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FAF687'});
	}
	else if(define=="R" || define=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FC6767'});
	}
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if(mStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(mStatus=="I" || mStatus=="W" || mStatus=="P" || mStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FAF687'});
	}
	else if(mStatus=="R" || mStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FC6767'});
	}
	
	if(aStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(aStatus=="I" || aStatus=="W" || aStatus=="P" || aStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FAF687'});
	}
	else if(aStatus=="R" || aStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FC6767'});
	}
	
	if(iStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(iStatus=="I" || iStatus=="W" || iStatus=="P" || iStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FAF687'});
	}else if(iStatus=="R" || iStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FC6767'});
	}
	
	if(cStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(cStatus=="I" || cStatus=="W" || cStatus=="P" || cStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FAF687'});
	}else if(cStatus=="R" || cStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FC6767'});
	}
	
	if(xStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(xStatus=="P"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#FAF687'});
	}else if(xStatus=="R" || xStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#FC6767'});
	}
}

function chkBoxFormatter(cellvalue, options, rowObject) {	
	var rowId = options.rowId;
	var chkVal="";
	var bgClr ="#EFEFEF";
	//alert(cellvalue);
	if (cellvalue=='C') {
		chkVal='checked="checked"';
	}
	//<input type="checkbox" id="chkTrimming" name="chkTrimming" value="Y" >
	var formatStr  = '<input id="chk_'+rowId+'" disabled="disabled" '+chkVal+'' ;
	formatStr  += ' type="checkbox" style="height: 22px;" > ';	
	return formatStr;
}


function docDoubleClick(id)
{	
	var rowData = jQuery("#auditgriddetails").jqGrid('getRowData',id);
	var keyid = id.projectgrid_1;
	var projeckeyid=id.PROJECTNUMBER;

	var type = jQuery("#hdnType").val();
	var isClosure = jQuery("#hdnIsClosure").val();
	var isCheckList=jQuery("#hdnIsCheckList").val();
	var mode = jQuery("#hdnFormMode").val();
	var stage=jQuery("#hdnStage").val();
	var approval=jQuery("#hdnApproval").val();
	
	//var keyid =jQuery("#auditgriddetails").jqGrid('getCell', rowId,"projectgrid_1");
	//var Projectid =jQuery("#auditgriddetails").jqGrid('getCell', rowId,"PROJECTNUMBER");
	if(frmmode==""||frmmode==" "||frmmode==null||(!frmmode)== null)
		{
		jQuery('#jqg_'+ "projectgrid" +'_'+id).attr('disabled');
		 return false;
		 
		}
	else{
	
	navigateToNextForm("projectsprotoview_input.prpo?type="+type+"&filterButton=false&keyid="+id+"&isClosure="+isClosure+"&checkList="+isCheckList +"&mode="+mode+"&stage="+stage+"&approval="+approval);
}
}
</script>
<form id= "frmProjectMain">
	<div id='wrapperRpt' style="width:85%">
		<table>
			<tr>
				<td>
				 	<div id="frmProjectMainFormatFuntKeyIds" >						
						<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
						<input type="hidden" id="section" name="cmbsection"  value=""></input>
						<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
						<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
						<input type="hidden" id= "flid"  name= "txtKppmFlid" value="${requestScope.fild}"/>
						<div id="ProjectMainfunction" style=" ">
						</div>
					</div>
				</td>
			</tr>
		</table>		
		<table id='projectgrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='pager'></div>
		<input type="hidden" id="hdnKeyid" name="hdnKeyid" value="${requestScope.Keyid}"/>
		<input type="hidden" id="hdnType" value="${requestScope.type}"/>
		<input type="hidden" id="hdnStage" value="${requestScope.stage}"/>
		<input type="hidden" id="hdnApproval" value="${requestScope.approval}"/>
		<input type="hidden" id="hdnIsClosure" name="hdnIsClosure" value="${requestScope.isClosure}"/>
		<input type="hidden" id="hdnIsCheckList" name="hdnIsCheckList" value="${requestScope.checkList}"/>
		<input type="hidden" id="hdnFormMode" name="hdnFormMode" value="${requestScope.mode}"/>
		<input type="hidden" id="hdnChartType" name="hdnChartType" value="${requestScope.ChartType}"/>
		<input type="hidden" id="hdnoldemployeenameid" name="hdnoldemployeenameid" value=""/>
		<input type="hidden" id="hdnprjectid" name="hdnprjectid" value=""/>
		
	</div>
 </form>

