<script>
var rownum=null;
jQuery(document).ready(function(){
	 initialiseForm('frmKaizenDateUpdate');
	 var url = jQuery('#hiddenUrl').val();
     jQuery("#submitForm").val("frmKaizenDateUpdate");
	 viewGrid(url,"q=2");
	
	});


function checkBoxKzn(id, options, rowObject){
	return '<input type="checkbox" id="chkBox_'+options.rowId+'_'+options.pos+'" name="chk_'+options.rowId+'_'+options.pos+'" onclick="if(this.checked){chkkaizenxboxCheck(\''+options.rowId +'\');}"  style="width:80px;  height:23px;"   class="easyui-button" value=""/>';
}

function chkkaizenxboxCheck(row){
    var currentValue = jQuery("#themeCategorySpan_" + row).text();
    
    // Replace the span with a combobox
    var comboboxHtml = '<input type="text" id="cmbkznmThemecategoryid_' + row + 
                       '" style="width:200px;" name="cmbkznmThemecategoryid_' + row + 
                       '" value="' + currentValue + '" class="easyui-combobox" />';
    
    jQuery("#themeCategorySpan_" + row).replaceWith(comboboxHtml);
    
    // Initialize the combobox
    fillComboBox("frmKaizenDateUpdate", "cmbkznmThemecategoryid_" + row, "kaizenactegoryfillcombo.kaizen");
    
    // Make the result area field ready
    readOnlyFields("txtKznmResultarea_" + row);
    
    // Set up the onSelect event
    jQuery("#cmbkznmThemecategoryid_" + row).combobox({  	   
        onSelect: function(recordid) { 
            rownum = row;
            var KEYID = jQuery("#cmbkznmThemecategoryid_" + row).combobox('getValue');
            processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID=" + KEYID + "&rn=" + row, "", "CategoryrecallsuccessCallBackCategory", "CategoryerrorCallBack");
        } 
    });
}
function CategoryrecallsuccessCallBackCategory(result)
{
	var chkValue=result[0][1];
	jQuery("#txtKznmResultarea_"+rownum).val(chkValue);
	rownum=null;
	//alert("chk:::"+chkValue);
}

/* function cmbkznmThemecategoryidformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	var str = '';
	str+="<input type='text'  id='cmbkznmThemecategoryid_" + rowId+"' style='width:200px;'  name='cmbkznmThemecategoryid_"+rowId+"' value='"+id+"' class='easyui-combobox' /> ";
	return str;
}
 */
 function cmbkznmThemecategoryidformatter(id, options, rowObject){
	    var rowId = options.rowId;
	    return '<span id="themeCategorySpan_' + rowId + '">' + id + '</span>';
	}
 
function txtKznmResultareaformatter(id,options,rowObject){
	var rowId = options.rowId;
	var colId = options.pos;
	return "<input type='text' id='txtKznmResultarea_"+ rowId+"' style='width:100px;'  name='txtKznmResultarea_"+rowId+"' value='"+id+"' class='easyui-textbox' /> "; 
}

function frmKaizenDateUpdate_successsCallback(result){  
    processGridnew("kaizenThemeReport_input.kaizen", "q=2","tblKaizenDateUpdate","abnAllocationPager","","");
}


/*function tblKaizenDateUpdate_selectRow(rowId){
	var Rowrow = jQuery("#tblKaizenDateUpdate").jqGrid('getDataIDs');
	alert("selectrow"+Rowrow);
}*/




function frmKaizenDateUpdate_beforeSubmit(){
    var save=jQuery('#hdnsavebtn').val();
	if((save.trim().length== "0" || save!= "Y")){
		var gridvalue=getGridSelectArray("tblKaizenDateUpdate");
		//alert("gridvalue"+gridvalue);
       var gridData;
		if(gridvalue.trim().length<=0){
			

        	var KznthemeRpt= getSelectdRowsAtt('tblKaizenDateUpdate', 'chkBox_', 'chkBox_2');  
		    if(KznthemeRpt.trim().length<=0)
			{
				alert("No Data Selected To Save");
				return false;
			}
		    else{
		    gridData +='&KaizenThemeList='+KznthemeRpt;
		    return gridData;
        }
		}
    }   
}

function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName) {
	var Rowrow = jQuery("#tblKaizenDateUpdate").jqGrid('getDataIDs');//	row get data
	//alert("Rowrow"+Rowrow);
	var rowid ="";
	var jsonArrO = '[';
	for ( i =0; i <Rowrow.length; i++) {
		rowid = Rowrow[i];
		var isChecked = jQuery('#chkBox_'+ rowid +'_2').is(':checked');
		if(isChecked == true){ 
			var cmbkznmThemecategoryid=getFieldValue("cmbkznmThemecategoryid_"+rowid);
			var resultarea=getFieldValue("txtKznmResultarea_"+rowid);
			//alert("result area"+resultarea);
			
			
			if (cmbkznmThemecategoryid==null){ 
				popupCommonErrorMsg(" Select Responsibility");
				return false;
			}
			
		//	alert("CategoryId"+cmbkznmThemecategoryid);
			var Keyid=jQuery("#tblKaizenDateUpdate").jqGrid('getCell',rowid,"Keyid");
		
          //  alert("Keyid:::"+Keyid);
			jsonArrO += '{';
			jsonArrO += '"hdnKznmKeyid":"' + Keyid +'",';
			jsonArrO += '"cmbkznmThemecategoryid":"' + cmbkznmThemecategoryid+'",';
			jsonArrO += '"txtKznmResultarea":"' +resultarea+'" ';
			jsonArrO += '},';  
      }
    }
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO;		
}

function update_successCallBack(result) {
	alert(result.successData.msg);
}

function viewGrid(url,dataString)
{   
	processGridnew(url, dataString, "tblKaizenDateUpdate", "abnAllocationPager", "", "");
	return true;
}

</script>
<form id="frmKaizenDateUpdate">
<div id="wrapperRpt" style="margin-top:20px;">
<table id="tblKaizenDateUpdate"></table>
<div id="abnAllocationPager"></div>
</div>
<input type="hidden" id="mode" name="mode" value="create"/> 
<input type="hidden" id="hdnsavebtn" name="hdnsavebtn"/>
<input type="hidden" id="hdnsavebtn" name=""/>
</form>
