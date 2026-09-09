<script>
jQuery.noConflict();
jQuery(document).ready(function(){	
    var url = jQuery('#hiddenUrl').val();
	var abnType =getFieldValue("cmbStatus");
	viewGrid(url,"?q=2&abntype="+abnType);
	fillComboBox("frmStrIenCompleted","cmbStatus","");
	//setLoadFormCallBackFrmId("hdnAbnType");
	jQuery("#btnView").click(function(){
			var abnType =getFieldValue("cmbStatus");
			processGridnew("StratificationvsCom_input.abnStrRpt","?q=2&abntype="+abnType,"list","pager","","");
			  });
	});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		var abnType =getFieldValue("cmbStatus");
		filterString+="&abntype="+abnType+"&firstClick=N";	
		processGridnew(url,filterString,"list","pager","","","","abnStrComplete");
		return true;
	}
	return false;
}
function abnStrComplete()
{
	var gridId = "list";
	 var row = jQuery("#list").jqGrid('getDataIDs');
	 //alert("row : "+row);
	var k = 0;
	var trs = document.getElementsByTagName("tr");
	//alert("trs : "+trs);
	//hideJqGridRow('list', 'jqgridheaderrow1');
	var gridId = 'list';
	var rowId= 'jqgridheaderrow1';
	jQuery('#list  tr[class=jqgridheaderrow1]').css({display:"none"});
	for(var i=0;i<trs.length;i++)
	{
		//alert("trs["+i+"].id : "+trs[i].id);
	 //if(trs[i].id.substring(0,4) == 'list')
	//	   k++;
	 
	 if(trs[i].id)
		   k++;
	}
	for(var j=0;j<row.length;j++)
	{
		if(row[j] == '' || row[j] == ' '|| row[j] == null)
			jQuery("#list").jqGrid('setCell',row[j],"MachineNo","Total",{'color':'#d9151e','font-weight':'bold'});
	}
	//alert("k : "+k);
	//jQuery('#listghead_'+(k-1)).css('display','none');
	jQuery('#'+(k-1)).css('display','none');
		

	
}
function validateFilterSelection(filterString){
		return  true;
}


function frmFilter_enableDisableSuccessCallBack()
{
	var url = jQuery('#hiddenUrl').val();	
	enableDisableDatenMonthFilter();
	if(url=="HSE_AbnStratificationRpt_input.abnStrRpt")
	{
		jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");
		setTimeout(function() {readOnlyFields('cmbAbnmTypeid');},100);						
		
		reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
		setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
		reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
	}
	jQuery('#cboRelatedTo option[value="MCH"]').attr('selected', 'selected');
}


/*function hdnAbnType_afterLoadCallBack(){
			
			toggleCommonFilter();	
			
	}*/

</script>
<form id="frmStrIenCompleted">
 <div id="wrapperRpt" style="max-width: 1210px;">
<div  style="padding-right:20px;margin-top:0px; width:800px;height:80px;">
	<label>Abnormality</label><span>
		<select id="cmbStatus" class="easyui-combobox" name="cmbStatus" style="width:200px;">  
				<option id="abntype" value='abntype'>ABN TYPE</option>
				<option id="abnimpact" value='abnimpact'>ABN IMPACT</option>
 				<option id="abncategory" value="abncategory">ABN CATEGORY</option>
 			</select></span>
	<input id="btnView" name="btnBarGraph" class="easyui-button" style="padding-top:0;" type="button" value="View"/> </span>
	</div>	   
<br><br>
<div id="divGraphContainer" ></div>	
<div style="margin-top:-54px;margin-top:-50px\9;">
 <div style="float: left;padding-right: 10px;">
            <input type="hidden" id="factory" name="cmbFactoryid" value="${requestScope.requestScope.factory}"  ></input>           
            <input type="hidden" id="section" name="cmbSectionid" value="${requestScope.requestScope.abnmSectionid}"  ></input>
            <input type="hidden" id="cell" name="cmbCellid" value="${requestScope.requestScope.abnmCellid}"  ></input>
            <input type="hidden" id="machine" name="cmbEquipmentid1" value="${requestScope.requestScope.abnmEquipmentid}"  ></input>
            <input type="hidden" id="flid" name="cmbAchJhteam" value="${requestScope.requestScope.achJhteam}"  ></input>
             </div>
             
	<table id="list" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
	  <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
              <input type="hidden" id="hdflid" name ="hdnflid" value="" />
              <input type="hidden" id="hdnfirstClick" name ="hdnfirstClick" value="" />
              <input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
</div>
</div>
</form>

