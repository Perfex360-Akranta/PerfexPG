<script type="text/javascript">
jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId("frmKaizenIdenVsComp");
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();
});

	
function setDrillHeader(eleType){
	var levHeader="";
	if(eleType.trim()=="CMP")
		levHeader="Company";
	else if(eleType.trim()=="LCN")
		levHeader="Mill";
	else if(eleType.trim()=="SBU")
		levHeader="SBU";
	else if(eleType.trim()=="PBU")
		levHeader="PBU";
	else if(eleType.trim()=="L")
		levHeader="DMT";
	else if(eleType.trim()=="C")
		levHeader="JH";
	else if(eleType.trim()=="M")
		levHeader="Machine";
	return levHeader;			
}

function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);

	jQuery('#chkMonthwise').click(function(){
 		if(jQuery('#chkMonthwise').is(':checked') == false)
		{
 			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
 			//alert("Select Datewise Checkbox");			
		}
 			
	});
	
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		filterString += '&drillFlag=f&firstClick=Y';
		var tableCaption = "Improvement Vs Completed";
		processGridnew(url,filterString,"impVscomp","pager",tableCaption,"doubleClickGrid","impVscomp_loadComplete","fillForm");		
		
		return true;
	}
	return false;	
}

function fillForm(id)
{
	 var row = jQuery("#impVscomp").jqGrid('getDataIDs');
		//alert("row"+row);
		 var cm = jQuery("#impVscomp").jqGrid("getGridParam", "colModel");
		 //alert("cm"+Object.keys(cm));
		 for(var i=0;i<row.length;i++)
		 {
			
			    if(i == row.length-1)
				    {
			    	 for(var j=0;j<cm.length;j++)
			     	 {							
				   		jQuery("#impVscomp").jqGrid('setCell',row[i],cm[j].name,"",{'color':'#000','font-size':'12px','background-color':'#fec488'});
			     	 }
				    }
				    
		 }					
		
}


function frmKaizenIdenVsComp_afterLoadCallBack(){
	 toggleCommonFilter();
	}
function validateFilterSelection(filterString){
	if(filterString == "?q=1" )
		return true;
   
	if(jQuery('#chkMonthwise').is(':checked') == true){
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
	return  true;
}


function impVscomp_loadComplete()
{
	setDrillDownHeader("CH1-0","impVscomp","KEYFIELD2");
	setTotalRowCss('impVscomp');
}

function impVscomp_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("impVscomp","KEYFIELD2",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}			

function doubleClickGrid(id){ 	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("impVscomp","KEYFIELD2",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}

</script>
<form id="frmKaizenIdenVsComp">
<div id="wrapperRpt"style= margin-top:2px;>
<table>
      <tr> 
      </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="impVscomp" ></table>
	                <div id="pager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
	
	
</div>	
	
	
	<input type="hidden" id="hiddenStr" value="sdsd" />
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddencircle" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />




