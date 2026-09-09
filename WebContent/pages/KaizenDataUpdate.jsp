<script>
var rownum=null;
jQuery(document).ready(function(){
	 initialiseForm('frmKaizenDataUpdate');
	 var url = jQuery('#hiddenUrl').val();
     jQuery("#submitForm").val("frmKaizenDataUpdate");
	 viewGrid(url,"q=2");
	
	});


function checkBoxKzn(id, options, rowObject){
	return '<input type="checkbox" id="chkBox_'+options.rowId+'_'+options.pos+'" name="chk_'+options.rowId+'_'+options.pos+'" onclick="if(this.checked){chkkaizenxboxCheck(\''+options.rowId +'\');}"  style="width:80px;  height:23px;"   class="easyui-button" value=""/>';
}

function chkkaizenxboxCheck(row){
	var cmbkznmThemecategoryid=getFieldValue("cmbkznmThemecategoryid_"+row);
	var txtresultarea=getFieldValue("txtKznmResultarea_"+row);
	fillComboBox("frmKaizenDataUpdate","cmbkznmThemecategoryid_"+row,"kaizenactegoryfillcombo.kaizen");
	readOnlyFields("txtKznmResultarea_"+row);
	  jQuery("#cmbkznmThemecategoryid_"+row).combobox({  	   
			onSelect:function(recordid)
				{ 
				rownum=row;
				var KEYID=jQuery("#cmbkznmThemecategoryid_"+row).combobox('getValue');
				processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID+"&rn="+row,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");				    	
				} 
			});
}

function CategoryrecallsuccessCallBackCategory(result){
	var chkValue=result[0][1];
	jQuery("#txtKznmResultarea_"+rownum).val(chkValue);
	rownum=null;
}

function cmbkznmThemecategoryidformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	var str = '';
	str+="<input type='text'  id='cmbkznmThemecategoryid_" + rowId+"' style='width:200px;'  name='cmbkznmThemecategoryid_"+rowId+"' value='"+id+"' class='easyui-combobox' /> ";
	return str;
}

function dteKznmDateformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	var str = '';
	str+="<input type='text'  id='dteKznmDate_" + rowId+"' style='width:200px;'  name='dteKznmDate_"+rowId+"' value='"+id+"' class='easyui-datebox' /> ";
	return str;
}


function cmbKznmBenefittypeformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	var str = '';
	str+="<input type='text'  id='cmbKznmBenefittype_" + rowId+"' style='width:200px;'  name='cmbKznmBenefittype_"+rowId+"' value='"+id+"' class='easyui-combobox' /> ";
	return str;
}
function txtKznmResultareaformatter(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return "<input type='text' id='txtKznmResultarea_"+ rowId+"' style='width:100px;'  name='txtKznmResultarea_"+rowId+"' value='"+id+"' class='easyui-textbox' /> "; 
}
function txtKznmBenefitvalueformatter(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return "<input type='text' id='txtKznmBenefitvalue_"+ rowId+"' style='width:100px;'  name='txtKznmBenefitvalue_"+rowId+"' value='"+id+"' class='easyui-textbox' /> "; 
}
function txtKznmVerifyamountformatter(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return "<input type='text' id='txtKznmVerifyamount_"+ rowId+"' style='width:100px;'  name='txtKznmVerifyamount_"+rowId+"' value='"+id+"' class='easyui-textbox' /> "; 
}
function txtKznmBenefitsformatter(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return "<input type='text' id='txtKznmBenefits_"+ rowId+"' style='width:200px;'  name='txtKznmBenefits_"+rowId+"' value='"+id+"' class='easyui-textbox' /> "; 
} 
function txtKznmTeammembersformatter(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return "<input type='text' id='txtKznmTeammembers_"+ rowId+"' style='width:180px;'  name='txtKznmTeammembers_"+rowId+"' value='"+id+"' class='easyui-textbox' /> "; 
}
function frmKaizenDataUpdate_successsCallback(result){  
    processGridnew("KaizenDataUpdate_input.kaizen", "q=2","tblKaizenDataUpdate","abnAllocationPager","","");
}
function frmKaizenDataUpdate_beforeSubmit(){
    var save=jQuery('#hdnsavebtn').val();
	if((save.trim().length== "0" || save!= "Y")){
		var gridvalue=getGridSelectArray("tblKaizenDataUpdate");
        var gridData;
		if(gridvalue.trim().length<=0){
        	var KznDataRpt= getSelectdRowsAtt('tblKaizenDataUpdate','chkBox_','chkBox_2');  
		    if(KznDataRpt.trim().length<=0)
			{
				alert("No Data Selected To Save");
				return false;
			}
		    else{
		    gridData +='&KaizenDataList='+KznDataRpt;
		    return gridData;
        }
		}
    }   
}

function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName) {
	var Rowrow = jQuery("#tblKaizenDataUpdate").jqGrid('getDataIDs');//	row get data
	var rowid ="";
	var jsonArrO = '[';
	for ( i =0; i <Rowrow.length; i++) {
		rowid = Rowrow[i];
		var isChecked=jQuery('#chkBox_'+ rowid +'_2').is(':checked');
		if(isChecked == true){ 
			var cmbkznmThemecategoryid=getFieldValue("cmbkznmThemecategoryid_"+rowid);
			//alert(cmbkznmThemecategoryid);
			var resultarea=getFieldValue("txtKznmResultarea_"+rowid);
			//alert(resultarea);
		    var benefitValue=getFieldValue("txtKznmBenefitvalue_"+rowid);
			//alert(benefitValue);
			var verifyAmt=getFieldValue("txtKznmVerifyamount_"+rowid);
		//	alert(verifyAmt);
			var benefit=getFieldValue("txtKznmBenefits_"+rowid);
			//alert(benefit);
	 		var teamMembers=getFieldValue("txtKznmTeammembers_"+rowid);
	 	//	alert(teamMembers);
			if (cmbkznmThemecategoryid==null){ 
				popupCommonErrorMsg(" Select Responsibility");
				return false;
			}
			var Keyid=jQuery("#tblKaizenDataUpdate").jqGrid('getCell',rowid,"Keyid");
         //   alert("Keyid:::"+Keyid);
			jsonArrO += '{';
			jsonArrO += '"hdnKznmKeyid":"' + Keyid +'",';
			jsonArrO += '"cmbkznmThemecategoryid":"' + cmbkznmThemecategoryid+'",';
			jsonArrO += '"txtKznmResultarea":"' +resultarea+'",';
			jsonArrO += '"txtKznmBenefitvalue":"' +benefitValue+'",';
			jsonArrO += '"txtKznmVerifyamount":"' +verifyAmt+'",';
			jsonArrO += '"txtKznmBenefits":"' +benefit+'",';
			jsonArrO += '"txtKznmTeammembers":"' +teamMembers+'" ';  
		    jsonArrO += '},';  
		//    alert(jsonArrO);
      }
    }
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("JSON"+jsonArrO);
	return jsonArrO;		
}

function update_successCallBack(result) {
	alert(result.successData.msg);
}

function viewGrid(url,dataString)
{   
	processGridnew(url, dataString, "tblKaizenDataUpdate", "abnAllocationPager", "", "");
	return true;
}

</script>
<form id="frmKaizenDataUpdate">
<div id="wrapperRpt" style="margin-top:20px;">
<table id="tblKaizenDataUpdate"></table>
<div id="abnAllocationPager"></div>
</div>
<input type="hidden" id="mode" name="mode" value="create"/> 
<input type="hidden" id="hdnsavebtn" name="hdnsavebtn"/>
<input type="hidden" id="hdnsavebtn" name=""/>
</form>
