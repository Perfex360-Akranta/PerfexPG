<script>
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
 	viewGrid("","");	
});
function frmFipviewdel_afterLoadCallBack(){	
	toggleCommonFilter();		
}

function frmFipviewdel_FuntLocHierarchy_SuccessCallBack(result){	
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
		filterString+="&type="+type+"&stage="+stage+"&approval="+approval+"&isClosure="+isClosure+"&checkList="+isCheckList;
	
		var tableCaption = "FI Project Approval List";
		processGridnew("projectsprotomain_input.prpo", filterString, "projectgrid", "pager",tableCaption,"docDoubleClick","","loadProto_complete");
		return true;
	}
	return false;
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
	var type = jQuery("#hdnType").val();
	var isClosure = jQuery("#hdnIsClosure").val();
	var isCheckList=jQuery("#hdnIsCheckList").val();
	var mode = jQuery("#hdnFormMode").val();
	var stage=jQuery("#hdnStage").val();
	var approval=jQuery("#hdnApproval").val();
	
	navigateToNextForm("projectsprotoview_input.prpo?type="+type+"&filterButton=false&keyid="+id+"&isClosure="+isClosure+"&checkList="+isCheckList +"&mode="+mode+"&stage="+stage+"&approval="+approval);
}
</script>
<form id= "frmFipviewdel">
	<div id='wrapperRpt' style="width:85%">
		<table>
			<tr>
				<td>
				 	<div id="frmFipviewdelFormatFuntKeyIds" >						
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
		<input type="hidden" id="hdnType" value="${requestScope.type}"/>
		<input type="hidden" id="hdnStage" value="${requestScope.stage}"/>
		<input type="hidden" id="hdnApproval" value="${requestScope.approval}"/>
		<input type="hidden" id="hdnIsClosure" name="hdnIsClosure" value="${requestScope.isClosure}"/>
		<input type="hidden" id="hdnIsCheckList" name="hdnIsCheckList" value="${requestScope.checkList}"/>
		<input type="hidden" id="hdnFormMode" name="hdnFormMode" value="${requestScope.mode}"/>
	</div>
 </form>