<script>
jQuery(document).ready(function(){
	initialiseForm('frmFactory'); 
	var employeeid = jQuery('#hdnEmpname').val();
	var funcLocType = jQuery('#hdnfunloclink').val();
    //alert("funcLocType"+funcLocType);   
	processGridnew("fac_input.emp","filterString="+employeeid+"&funcLocType="+funcLocType,"Funcloclinkgridid","Factpagerid"," "," ","","Factorymstloadcomplete","FacaddselectRowFunction","");
    //alert("funcLocType"+funcLocType);
    //alert("fact"+fact);
	jQuery('#submitForm').val('frmFactory');	

});
  
  function Factorymstloadcomplete()
 {
	var factorymst=jQuery("#Funcloclinkgridid").jqGrid('getDataIDs');	
	//alert("factorymst"+ factorymst);
	var col=jQuery("#Funcloclinkgridid").jqGrid ('getGridParam','colModel');
	//alert("factorymst.length" +factorymst.length);
	
	for(i=0;i<factorymst.length;i++)
	{
		//alert("factorymst["+i+"]:::::::"+factorymst[i]);
		    
 			var colkeyid = jQuery("#Funcloclinkgridid").jqGrid('getCell',factorymst[i],"Funclocnlink_keyid");
 			//alert("colkeyid" + colkeyid);
 	 		if(colkeyid!= " " && colkeyid!= "" && colkeyid!= null && colkeyid!="null" && colkeyid!=''){
			//alert("colkeyid0000" + colkeyid);
				jQuery('input:checkbox[id=jqg_Funcloclinkgridid_'+factorymst[i]+']').attr('checked',true);
				chkboxCheck(factorymst[i]);
	}	
		
	} 
} 	
  function FacaddselectRowFunction_selectAll(id,status){
	//alert("selected all rows");
	for(var i=0; i<id.length; i++){
		if(status)
			chkboxCheck(id[i]);
		else
			chkboxUnCheck(id[i]);
	}
}
/**End**/
/**Function for  selecting/Unselecting  Row**/
  function FacaddselectRowFunction_selectRow(id){
	//alert("selected  one row");

	
	if(jQuery('#jqg_Funcloclinkgridid_'+id).is(':checked'))
		chkboxCheck(id);
	else
		chkboxUnCheck(id);
	}
/**End**/

   function chkboxCheck(rowId)
{
   
	jQuery("#Funcloclinkgridid").jqGrid('setCell',rowId,'FunclocnlinkHide','1');	
	
}
  function chkboxUnCheck(rowId)
{
	
	jQuery("#Funcloclinkgridid").jqGrid('setCell',rowId,'FunclocnlinkHide','0');
}

  function frmFactory_beforeSubmit()
{
	 var empid = jQuery('#hdnEmpname').val();
	 var factorymst=getSelectdRowsFact('Funcloclinkgridid','null','FunclocnlinkHide');
	 
	 var Emplink =jQuery('#hdnfunloclink').val(); 
	 //alert("Emplink"+Emplink);
	 if(factorymst.trim().length<=0 )
			{
				 if(Emplink=="FACT")
				 {	 		
					 alert("Select Factory");
					 return false;
				 }
				 else if(Emplink=="SECT")
			     {		 
					 alert("Select Section");
				     return false;
			     }
				 else if(Emplink=="CELL")
			     {		 
					 alert("Select Line");
				     return false;
			     }
				 else(Emplink=="MCHM")
			     {		 
					 alert("Select Equipment");
				     return false;
			     }
			}
			 else
			{	
			 var gridData = '&factory='+factorymst; 
		     gridData+='&empid='+empid;
		     //alert("factory"+factory);
	         //alert("gridData"+gridData);  
		     return gridData;
			}
}
 /*  function getSelectdRowsFact(jqGridId,checkBoxColName,ckeckForSelColName){
	var allRows = jQuery("#Funcloclinkgridid").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
	var row = allRows[i];
	var value = row[ckeckForSelColName];
		
		if( value != null  &&  value.trim()  != " "){			
			if(value == '1')				
			{	jsonArrO += '{';
			for(var colName in row) {
				var cellValue = parseJqGridCellValue(row[colName]);	
					if(colName == 'Keyid')
						{
						jsonArrO += '"cmbefllfunclocn":"' + cellValue+'"';
						}
			}
			jsonArrO +=  "},";
			}
			}
		 }
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
    //alert(jsonArrO);
	return jsonArrO;
	 }   */
	 
	// added by sriram for employee master Equipment link
	 function getSelectdRowsFact(jqGridId, checkBoxColName, ckeckForSelColName){
			
		    // ✅ Get only selected rows (multiselect)
		    var selectedIds = jQuery("#" + jqGridId).jqGrid('getGridParam', 'selarrrow');

		    var jsonArrO = '[';

		    for(var i = 0; i < selectedIds.length; i++){
		        var row = jQuery("#" + jqGridId).jqGrid('getRowData', selectedIds[i]);

		        // ✅ Same logic you had: find Keyid and convert to cmbefllfunclocn
		        jsonArrO += '{';
		        for(var colName in row) {
		            var cellValue = parseJqGridCellValue(row[colName]);
		            if(colName == 'Keyid'){
		                jsonArrO += '"cmbefllfunclocn":"' + cellValue + '"';
		            }
		        }
		        jsonArrO += '},';
		    }

		    // ✅ Close JSON cleanly
		    jsonArrO = jsonArrO.slice(0, -1) + "]";

		    jsonArrO = (jsonArrO != ']' ? jsonArrO : "");
		    return jsonArrO;
		} 

jQuery('#btnSaveFact').click(function() {
	
	
	//navigateToNextForm("fac_save.emp","Factory");

	saveForm('frmFactory','fac_save.emp');

 
				
});

function frmFactory_successsCallback(result)
{
	
	jQuery("#Funcloclinkgridid").trigger("reloadGrid");
	
	  
}
</script>

<form name="frmFactory" id="frmFactory" action=" " method="post">

<div id='' >
<table id='Funcloclinkgridid'><tr><td></td></tr></table>
<div id='Factpagerid'></div>
</div>
<div style="padding-top:30;margin-left:160px;"><span style="">
<input type="button" id="btnSaveFact" name="btnSaveFact" class="easyui-button" value="Save" style="width: 50px;"></span>	    
</div>		
<input type="hidden" id="mode" name="mode" />                                   
<input type="hidden" id="hdnEmpname" name="hdnEmpname" value="${requestScope.employid}"/>
<input type="hidden" id="hdnfunloclink" name="hdnfunloclink" value="${requestScope.Funloclink}"/>


</form>