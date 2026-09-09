
<script>
jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId('frmQmBaseLine');
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();				
	var url = jQuery('#hiddenUrl').val();
	var FromDate=null;
	var ToDate = null;
	//var dataString ="";
	var factId =jQuery('#hiddenfact').val();
	var sectId =jQuery('#hiddensect').val();
	var cellId =jQuery('#hiddencell').val();
	var date =jQuery('#hiddendate').val(); 
	var shift =jQuery('#hiddenshift').val();				
	var dataStr ="q=2";
	if(factId != null && factId != '' && factId != ' ')
		dataStr+="&cmbFactid="+factId;
	if(sectId!= null && sectId != '' && sectId != ' ')
		dataStr+="&cmbSectid="+sectId;
	if(cellId!= null && cellId != '' && cellId != ' ')
		dataStr+="&cmbCellid="+cellId;
	if(date!= null && date != '' && date != ' ')
	{
		dataStr+= "&dtFromDate="+escape(date);
		dataStr+= "&dtToDate="+escape(date);
	}
	if(shift!= null && shift != '' && shift != ' ')
		dataStr+= "&shift="+shift;
		dataStr+= "&chkMonthwise=0";
	if(factId.length>0)
		filterString = 	dataStr;
	if(jQuery('#hdnPrevDataUrl').val().length <= 0) {

		viewGrid(actionPart,filterString);
		}
	
	});

function frmQmBaseLine_afterLoadCallBack(){

	var actionPart = jQuery('#hiddenUrl').val();
	
	if(actionPart.indexOf('filter')<0)
	toggleCommonFilter();		
	else
		
		jQuery('#hdnSetFilterValues').val('Y');
}

function viewGrid(url,filterString)
{
	
	
	 if( validateFilterSelection(filterString))
	{
		 if(jQuery('#hdnPrevDataUrl').val().length > 0){
				//alert("inside");
				var factId =jQuery('#hiddenfact').val();
				var sectId =jQuery('#hiddensect').val();
				var cellId =jQuery('#hiddencell').val();
				var date =jQuery('#hiddendate').val(); 
				var shift =jQuery('#hiddenshift').val();
					 //alert(factId +" -- "+sectId+" -- "+cellId+" -- "+date);
				var dataStr ="";
				
				    dataStr+="&cmbFactid="+factId;
				//if(sectId.substring(0,3)=='LIN')
					dataStr+="&cmbSectid="+sectId;
				//if(cellId.substring(0,3)=='CEL')
					dataStr+="&cmbCellid="+cellId;
				//if(date.substring(0,3)=='MCH')
					//datStr+="&cmbMchid="+rowid;
					dataStr+= "&dtFromDate="+escape(date);
					dataStr+= "&dtToDate="+escape(date);
					dataStr+= "&shift="+shift;
					dataStr+= "&chkMonthwise=0";
					if(factId.length>0)
					filterString = 	dataStr;			
			}
		var tableCaption = "QM BaseLine Report";
		processGridnew(url,filterString,"list","pager",tableCaption,'','',"QMBaseLine_loadComplete");
		
		return true;
	}	
	return false;
}

function QMBaseLine_loadComplete()
{
	var rptLbl = jQuery("#hdnReportType").val();
	var lblText;
	if(rptLbl=="MACHINE")
		lblText="MACHINE WISE";
	else if(rptLbl=="WEEK")
		lblText="WEEK WISE";
	else if(rptLbl=="SHIFT")
		lblText="SHIFT WISE";
	else if(rptLbl=="MONTH")
		lblText="MONTH WISE";
	else if(rptLbl=="DEFECT")
		lblText="DEFECT WISE";
	else if(rptLbl=="REJQTYDETL")
		lblText="REJECTED QUANTITY DETAILS WISE";
	else if(rptLbl=="REJQTY")
		lblText="REJECTED QUANTITY WISE";
	
jQuery("#lblRptType").text(lblText);
jQuery("#hdnReportName").val(lblText);

var lblNote = jQuery("#hdnReportNote").val();
if(rptLbl == "REJQTYDETL" || rptLbl == "REJQTY")
	lblNote = "";
jQuery("#lblNotes").text(lblNote);

setTotalRowCss("list");
}
function validateFilterSelection(filterString){

var rptType = getFieldValue('cborptType');

if(rptType==null || rptType.trim()=="")
	rptType="SHIFT";
	
jQuery("#hdnReportType").val(rptType);
		 if( ! checkFilterValueExist(filterString,"cmbCellid"))
		{
			alert("Select Line");
			return false;
		}
		 return true;	
}

jQuery('#btnGraph').click(function()
{	
	var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
	var rowData = jQuery("#list").jqGrid('getRowData',rowid);	
	var dataString ="";
	var rptType = jQuery("#hdnReportType").val();
	//alert(Object.keys(rowData));
	var rptName = jQuery("#hdnReportName").val();
	
	dataString += "&RPTType="+rptType;
	dataString += "&RptName="+rptName;
	var url = "chart.baseLine?";
	
	if(rptType=="SHIFT" || rptType=="WEEK" || rptType=="MONTH" || rptType=="MACHINE" || rptType=="DEFECT")
	{		
		if(rowid != null && rowid.trim() != "")
		{			
			dataString += "&PARAMCODE="+rowid;					
			dataString += "&ProductId="+rowData.MON;
			showGraphData(url+dataString);				
		}
		else
		{
			
			alert("Select Valid Row");			
		}
	}
	else
		showGraphData(url+dataString);	
			
});

function frmFilter_enableDisableSuccessCallBack()
{

	if(jQuery('#hdnSetFilterValues').val() == 'Y')
		setFilterValues();
	enableDisableDatenMonthFilter();

	jQuery('#cborptType').change(function() {
		 var type = jQuery("#cborptType").val();
		
		 if(type == "MONTH" || type == "WEEK")
		 { 			 
			 readOnlyFields("chkDatewise");
			 readOnlyFields("dtefromDate");
			 readOnlyFields("dtetoDate");
			 jQuery("#chkMonthwise").attr('checked',true);
			 jQuery("#chkDatewise").attr('checked',false);
			 enableFields("dtefromMonth");
			 enableFields("dtetoMonth");
		 }	
		 else{
			 enableFields("chkDatewise");
			 enableDisableDatenMonthFilter();
			 }		 
		});
} 

</script>
<form id="frmQmBaseLine" name="frmQmBaseLine">


<div id="wrapperRpt" style="max-width:100%;">

<div style="width: 102%;width:106%\9;height: 23px;">
<div style="float: left;">
<label style="font-weight: bold;  " id="lblRptType" ></label></div>
<label class="notes" id="lblNotes"  style="font-weight: bold; padding-left:20px; padding-top:1px\9; " > </label>
<div  style="float:right;margin-top:-6px;margin-top:-20px\9;" >
	<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>   	
</div>
</div>

<table id="list" style="width:100%;">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdnfactId}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncellId}" />
<input type="hidden" id="hiddendate" name="hiddedate" value="${requestScope.hdndateId}" />
<input type="hidden" id="hiddenshift" name="hiddeshift" value="${requestScope.hdnshftId}" />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
</form>
<input type="hidden" id="hdnReportType" value=""/>
<input type="hidden" id="hdnReportName" value=""/>
<input type="hidden" id="hdnReportNote" value="${requestScope.Forgraph}"/>