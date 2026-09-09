<script>
jQuery(document).ready(function(){
//	alert("Check");
	/*var type = jQuery("#hdnType").val();
	//setLoadFormCallBackFrmId("frmProjectMain");
	var factId = jQuery("#frmProjectMain input[id='factory']").val();
	var sectionId = jQuery("#frmProjectMain input[id='section']").val();
	var cellId = jQuery("#frmProjectMain input[id='cell']").val();
	var machId = jQuery("#frmProjectMain input[id='machine']").val();
	var flid = jQuery("#frmProjectMain input[id='flid']").val();
	var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
	loadFunctionalLocation("ProjectMainfunction","functionalLoc.kkpp","ProjectMainfunctionLocation","frmProjectMain",dataStr);
	//setLoadFormCallBackFrmId("frmProjectMain");
	*/
	viewGrid("","");	
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
		var fiptype=jQuery("#hdnFipType").val();
		//var url = jQuery('#hiddenUrl').val();
		//filterString += '&flid='+flid;			
		filterString+="&type="+type+"&stage="+stage+"&approval="+approval+"&isClosure="+isClosure+"&checkList="+isCheckList+"&fiptype="+fiptype;
		
		//alert("url:"+url+",filterString:"+filterString);
		//processGridnew(url,filterString,"projectgrid","pager","","doubleClickGrid","","");
		var tableCaption = "FI Project Approval List";
		//alert(filterString);
		processGridnew("projectsprotomain_input.prpo", filterString, "projectgrid", "pager",tableCaption,"docDoubleClick","","loadProto_complete");
		return true;
	}
	return false;
}
function validateFilterSelection(filterString){
	return  true;
}
/*
function viewGrid(url,flid)
{  
   	var filterString="";
    filterString += '&flid='+flid;	
    filterString += '&isClosure='+jQuery("#hdnIsClosure").val();
	var tableCaption = "FI Project Approval List";
	//alert(filterString);
	processGridnew("projectsprotomain_input.prpo", filterString, "projectgrid", "pager",tableCaption,"docDoubleClick","","loadProto_complete");
}
*/
function loadProto_complete() {
	var row = jQuery("#projectgrid").jqGrid('getDataIDs');
	//alert(row.length);
	for(var i=0;i<row.length;i++){
		var rowId=	parseInt(i)+1;	
		var rowIds = row[i];
		var define = jQuery("#projectgrid").jqGrid('getCell',row[i],"DEFINE");
		var mStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"MEASURE");
		var aStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"ANALYSE");
		var iStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"IMPROVE");
		var cStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"CONTROL");
		var xStatus=jQuery("#projectgrid").jqGrid('getCell',row[i],"CLOSURE");
/* 		var dTargetDate = jQuery("#projectgrid").jqGrid('getCell',row[i],"DTARGETDATE");
		var mTargetDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"MTARGETDATE");
		var aTargetDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"ATARGETDATE");
		var iTargetDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"ITARGETDATE");
		var cTargetDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"CTARGETDATE");
		var xTargetDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"XTARGETDATE");
		var dCompletedDate = jQuery("#projectgrid").jqGrid('getCell',row[i],"DCOMPLETEDDATE");
		var mCompletedDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"MCOMPLETEDDATE");
		var aCompletedDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"ACOMPLETEDDATE");
		var iCompletedDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"ICOMPLETEDDATE");
		var cCompletedDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"CCOMPLETEDDATE");
		var xCompletedDate=jQuery("#projectgrid").jqGrid('getCell',row[i],"XCOMPLETEDDATE"); */
		//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus+",xStatus:"+xStatus);
		jQuery("#projectgrid").jqGrid('setCell',row[i],"D",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"M",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"A",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"I",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"C",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"X",'',{'color':'black','background-color':'#D9D9DB'});
		
		jQuery("#projectgrid").jqGrid('setCell',row[i],"DTARGETDATE",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"MTARGETDATE",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"ATARGETDATE",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"ITARGETDATE",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"CTARGETDATE",'',{'color':'black','background-color':'#D9D9DB'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"XTARGETDATE",'',{'color':'black','background-color':'#D9D9DB'});
		statusgridtracking(define,mStatus,aStatus,iStatus,cStatus,xStatus,row[i]);			
	}	
}

function statusgridtracking(define,mStatus,aStatus,iStatus,cStatus,xStatus,rowid){
	//alert('definedefine'+define);
	var dTargetDate = jQuery("#projectgrid").jqGrid('getCell',rowid,"DTARGETDATE");
	//alert(dTargetDate);
	var mTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"MTARGETDATE");
	//alert(mTargetDate);
	var aTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ATARGETDATE");
	//alert(aTargetDate);
	var iTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ITARGETDATE");
	//alert(iTargetDate);
	var cTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"CTARGETDATE");
	//alert(cTargetDate);
	var xTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"XTARGETDATE");
	//alert(xTargetDate);
	var dCompletedDate = jQuery("#projectgrid").jqGrid('getCell',rowid,"DCOMPLETEDDATE");
	//alert(dCompletedDate);
	var mCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"MCOMPLETEDDATE");
	//alert(mCompletedDate);
	var aCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ACOMPLETEDDATE");
	//alert(aCompletedDate);
	var iCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ICOMPLETEDDATE");
	//alert(iCompletedDate);
	var cCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"CCOMPLETEDDATE");
	//alert(cCompletedDate);
	var xCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"XCOMPLETEDDATE");
	
	//alert(xCompletedDate);
if (define=="C"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#95FB94'});
	if(dTargetDate != "-" && dCompletedDate != "-"){
	if(compareDateTime(dTargetDate,dCompletedDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,dCompletedDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	}
}
else if(define=="P"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FAF687'});
	if(dTargetDate != "-" ){
 	var currentDate = getCurrentDate();
 	if(compareDateTime(dTargetDate,currentDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,currentDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	}
}
else if(define=="R" || define=="E"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FC6767'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}else if(define=="-"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}
//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);

if(mStatus=="C"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
	if(mTargetDate != "-" && mCompletedDate != "-"){
	if(compareDateTime(mTargetDate,mCompletedDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,dCompletedDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	}
	
}else if(mStatus=="I" || mStatus=="W" || mStatus=="P" || mStatus=="L"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FAF687'});
	if(mTargetDate != "-" ){
	var currentDate = getCurrentDate();
 	if(compareDateTime(mTargetDate,currentDate) > 0 )
 	{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	}
}
else if(mStatus=="R" || mStatus=="E"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FC6767'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}else if(mStatus=="-" && define=="C"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}
//alert("aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
if(aStatus=="C"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
	if(aTargetDate != "-" && aCompletedDate != "-"){
	if(compareDateTime(aTargetDate,aCompletedDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,dCompletedDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	}
	
}else if(aStatus=="I" || aStatus=="W" || aStatus=="P" || aStatus=="L"){
	if(aTargetDate != "-" ){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FAF687'});
	var currentDate = getCurrentDate();
 	if(compareDateTime(aTargetDate,currentDate) > 0 )
 	{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	}
}
else if(aStatus=="R" || aStatus=="E"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FC6767'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}else if(aStatus=="-" && mStatus=="C"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}
//alert("iStatus"+iStatus+"cStatus"+cStatus);
if(iStatus=="C"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
	if(iTargetDate != "-" && iCompletedDate != "-"){
	if(compareDateTime(iTargetDate,iCompletedDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,dCompletedDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	}
	
}else if(iStatus=="I" || iStatus=="W" || iStatus=="P" || iStatus=="L"){
	if(iTargetDate != "-" ){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FAF687'});
	var currentDate = getCurrentDate();
 	if(compareDateTime(iTargetDate,currentDate) > 0 )
 	{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	}
}else if(iStatus=="R" || iStatus=="E"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FC6767'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}else if(iStatus=="-" && aStatus=="C"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}
//alert("cStatus"+cStatus);
if(cStatus=="C"){
	if(cTargetDate != "-" && cCompletedDate != "-"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#95FB94'});
	if(compareDateTime(cTargetDate,cCompletedDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,dCompletedDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	}
	
}else if(cStatus=="I" || cStatus=="W" || cStatus=="P" || cStatus=="L"){
	if(cTargetDate != "-" ){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FAF687'});
	var currentDate = getCurrentDate();
 	if(compareDateTime(cTargetDate,currentDate) > 0 )
 	{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	}
}else if(cStatus=="R" || cStatus=="E"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}else if(cStatus=="-" && iStatus=="C"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}
//alert("xStatus"+xStatus);
if(xStatus=="C"){
	if(xTargetDate != "-" && xCompletedDate != "-"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#95FB94'});
	if(compareDateTime(xTargetDate,xCompletedDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,dCompletedDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	}
	
}else if(xStatus=="P"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#FAF687'});
	if(xTargetDate != "-" ){
	var currentDate = getCurrentDate();
 	if(compareDateTime(xTargetDate,currentDate) > 0 )
 	{
 		//alert(compareDateTime(dTargetDate,currentDate));
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
 	}
 	else{
 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
 	}
	}else{
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	}
}else if(xStatus=="R" || xStatus=="E"){
	//jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#FC6767'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}else if(xStatus=="-" && cStatus=="C"){
	jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
	jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
}

//alert("xStatus2"+xStatus);
}

/* function statusgridtracking(define,mStatus,aStatus,iStatus,cStatus,xStatus,rowid){
	//alert('definedefine'+define);
	
		var dTargetDate = jQuery("#projectgrid").jqGrid('getCell',rowid,"DTARGETDATE");
		//alert(dTargetDate);
		var mTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"MTARGETDATE");
		//alert(mTargetDate);
		var aTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ATARGETDATE");
		//alert(aTargetDate);
		var iTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ITARGETDATE");
		//alert(iTargetDate);
		var cTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"CTARGETDATE");
		//alert(cTargetDate);
		var xTargetDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"XTARGETDATE");
		//alert(xTargetDate);
		var dCompletedDate = jQuery("#projectgrid").jqGrid('getCell',rowid,"DCOMPLETEDDATE");
		//alert(dCompletedDate);
		var mCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"MCOMPLETEDDATE");
		//alert(mCompletedDate);
		var aCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ACOMPLETEDDATE");
		//alert(aCompletedDate);
		var iCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"ICOMPLETEDDATE");
		//alert(iCompletedDate);
		var cCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"CCOMPLETEDDATE");
		//alert(cCompletedDate);
		var xCompletedDate=jQuery("#projectgrid").jqGrid('getCell',rowid,"XCOMPLETEDDATE");
		
		//alert(xCompletedDate);
	if (define=="C"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#95FB94'});
		if(compareDateTime(dTargetDate,dCompletedDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,dCompletedDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	 	}
	}
	else if(define=="P"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FAF687'});
	 	var currentDate = getCurrentDate();
	 	if(compareDateTime(dTargetDate,currentDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,currentDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	 	}
	}
	else if(define=="R" || define=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FC6767'});
	}else if(define=="-"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"DCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
	}
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	
	if(mStatus=="C"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
		
		if(compareDateTime(mTargetDate,mCompletedDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,dCompletedDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	 	}
		
	}else if(mStatus=="I" || mStatus=="W" || mStatus=="P" || mStatus=="L"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FAF687'});
		var currentDate = getCurrentDate();
	 	if(compareDateTime(mTargetDate,currentDate) > 0 )
	 	{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	 	}
	}
	else if(mStatus=="R" || mStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FC6767'});
	}else if(mStatus=="-" && define=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"MCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
	}
	//alert("aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if(aStatus=="C"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
		if(compareDateTime(aTargetDate,aCompletedDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,dCompletedDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	 	}
		
	}else if(aStatus=="I" || aStatus=="W" || aStatus=="P" || aStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FAF687'});
	}
	else if(aStatus=="R" || aStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FC6767'});
	}else if(aStatus=="-" && mStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ATARGETDATE",'',{'color':'black','background-color':'#FFA500'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ACOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
	}
	//alert("iStatus"+iStatus+"cStatus"+cStatus);
	if(iStatus=="C"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
		if(compareDateTime(iTargetDate,iCompletedDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,dCompletedDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	 	}
		
	}else if(iStatus=="I" || iStatus=="W" || iStatus=="P" || iStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FAF687'});
	}else if(iStatus=="R" || iStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FC6767'});
	}else if(iStatus=="-" && aStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ITARGETDATE",'',{'color':'black','background-color':'#FFA500'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"ICOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
	}
	//alert("cStatus"+cStatus);
	if(cStatus=="C"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#95FB94'});
		if(compareDateTime(cTargetDate,cCompletedDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,dCompletedDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	 	}
		
	}else if(cStatus=="I" || cStatus=="W" || cStatus=="P" || cStatus=="L"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FAF687'});
	}else if(cStatus=="R" || cStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FC6767'});
	}else if(cStatus=="-" && iStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"CCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
	}
	//alert("xStatus"+xStatus);
	if(xStatus=="C"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#95FB94'});
		if(compareDateTime(xTargetDate,xCompletedDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,dCompletedDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#95FB94'});
	 	}
		
	}else if(xStatus=="P"){
		//jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#FAF687'});
		var currentDate = getCurrentDate();
	 	if(compareDateTime(xTargetDate,currentDate) > 0 )
	 	{
	 		//alert(compareDateTime(dTargetDate,currentDate));
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FC6767'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FC6767'});
	 	}
	 	else{
	 		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FAF687'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FAF687'});
	 	}
	}else if(xStatus=="R" || xStatus=="E"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"X",'',{'color':'black','background-color':'#FC6767'});
	}else if(xStatus=="-" && cStatus=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XTARGETDATE",'',{'color':'black','background-color':'#FFA500'});
		jQuery("#projectgrid").jqGrid('setCell',rowid,"XCOMPLETEDDATE",'',{'color':'black','background-color':'#FFA500'});
	}
	
	//alert("xStatus2"+xStatus);
} */

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
	var fiptype=jQuery("#hdnFipType").val();
	var rowData = jQuery("#projectgrid").jqGrid('getRowData',id);
	var dfiwkeyid = rowData.DFIWKEYID;
	//alert(stage +"    "+type);
	//alert(dfiwkeyid);
	if(fiptype=="DMC")
	{
	//navigateToNextForm("dmcprojectsprotoview_input.prpo?type="+type+"&filterButton=false&keyid="+id+"&dfiwkeyId="+dfiwkeyid+"&isClosure="+isClosure+"&checkList="+isCheckList +"&mode="+mode+"&stage="+stage+"&approval="+approval);
		 LoadPopUp("loadFPI","dmcprojectsprotoview_input.prpo?type="+type+"&filterButton=false&keyid="+id+"&dfiwkeyId="+dfiwkeyid+"&isClosure="+isClosure+"&checkList="+isCheckList +"&mode="+mode+"&stage="+stage+"&approval="+approval, true,"95%","90%","3%","1%", "popup_callback()","DMC FIP CheckList"," ",true,true );	
	}
	else
    {
	// navigateToNextForm("projectsprotoview_input.prpo?type="+type+"&filterButton=false&keyid="+id+"&isClosure="+isClosure+"&checkList="+isCheckList +"&mode="+mode+"&stage="+stage+"&approval="+approval);
     //alert("Else");
	 LoadPopUp("loadFPI","projectsprotoview_input.prpo?type="+type+"&filterButton=false&keyid="+id+"&isClosure="+isClosure+"&checkList="+isCheckList +"&mode="+mode+"&stage="+stage+"&approval="+approval, true,"95%","90%","3%","1%", "popup_callback()","FIP CheckList"," ",true,true );
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
		<input type="hidden" id="hdnType" value="${requestScope.type}"/>
		<input type="hidden" id="hdnStage" value="${requestScope.stage}"/>
		<input type="hidden" id="hdnApproval" value="${requestScope.approval}"/>
		<input type="hidden" id="hdnIsClosure" name="hdnIsClosure" value="${requestScope.isClosure}"/>
		<input type="hidden" id="hdnIsCheckList" name="hdnIsCheckList" value="${requestScope.checkList}"/>
		<input type="hidden" id="hdnFipType" name="hdnFipType" value="${requestScope.fiptype}"/>
		<input type="hidden" id="hdnFormMode" name="hdnFormMode" value="${requestScope.mode}"/>
	</div>
 </form>

