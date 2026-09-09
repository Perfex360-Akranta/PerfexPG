<script>
jQuery(document).ready(function(){
	
	//setLoadFormCallBackFrmId("frmWhyWhyModify");
	
		viewGrid("whywhyanalysisageingrpt_input.why","q=2&"+jQuery("#hdnFilterString").val());
});
function frmWhyWhyModify_afterLoadCallBack(){	
	toggleCommonFilter();
}

function viewGrid(url, filterStr) {

//filterStr+=filterStr+"&mode="+jQuery("#hdnFormMode").val();
	processGridnew("whywhyanalysisageingrpt_input.why",filterStr,"ageingrptgrid","pager","","docDoubleClick");
	return true;
}


function validateFilterSelection(filterString)
{
	return  true;
}

/* function docDoubleClick(rowid)
{	//alert('in side the dblClick');
	var rowData = jQuery("#achievementgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.KEYID;
	var type="whywhymodify";
	//navigateToNextForm("whywhyanalysismodify_input.why?keyid="+keyid+"&type="+type+"&hdnMode="+jQuery("#hdnMode").val()+"&"+jQuery("#hdnFilterString").val()+"&filterButton=false");

	LoadPopUp("divWhyModify","whywhyanalysismodify_input.why?keyid="+keyid+"&type="+type+"&hdnMode="+jQuery("#hdnFormMode").val()+"&"+jQuery("#hdnFilterString").val()+"&filterButton=false",true,"95%","90%","3%","1%", "popup_callback()","WHYWHY Modify"," ",true);
	//alert('in side the dblClick 2');
} */

</script>


<form  name="frmWhyWhyAgeingRpt" id="frmWhyWhyAgeingRpt">

<div id='wrapperRpt' style="width:120%;width:100%\9">


<div style="width:120%;width:100%\9">
<table>
	<tr>
	<td >
			<!-- <div style="padding-left:0%;padding-left:0%\9;margin-top: -31px ">
				<input id="btnNew" name="btnNew" class="easyui-button"  type="button" value="New Entry" style="width:70px; height:24px;"/>
			</div> -->
		
<!--		<td style="margin-top: -10px" >-->
			<!-- <div style="width : 332px;width: 325px\9;margin-top: -17px;padding-left: 77px" > -->
				<!-- <span style="padding-left:10px;"><label class="notes";style="margin-top: -10px"> Double Click on row to input/view details </span> -->
			</label>
			<!--</div>
		</td>-->
		</td>
	</tr>
</table>

</div>
<div style="margin-top: -9px">
<table id='ageingrptgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
</div>
</div>

 <input type="hidden" id="hdnFilterString" value="${requestScope.filterStr}" />
 <input type="hidden" id="hdnMode" value="${requestScope.mode}" /> 
 <input type="hidden" id="hdnrefdoctype" name="hdnrefdoctype" value="${requestScope.refdoctype}"/>
 <input type="hidden" id="hdnFormMode" name="hdnFormMode" value="${requestScope.formMode}"/>

</form>