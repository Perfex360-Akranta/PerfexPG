/**
 * 
 */

/*function frmMasterTbl_MNUCTQMST_beforeSubmit(){
	var elementType=jQuery("#frmMasterTbl input[id='elementType']").val();
	alert(" elementType :: "+elementType);
    if(  elementType!= "LOCN"){
    	 alert("Select LOCN");
    	 return false;
     }
}*/

function frmMasterTbl_FuntLocHierarchy_SuccessCallBack(result)
{
	var flid =jQuery("#frmMasterTbl input[id='flid']").val();
	
	if(flid != undefined && flid.trim() != "" && flid.trim() !=null)
	{ 
	    reloadCombo("frmMasterTbl","cmbqpdmctqid","getCtq.qams?&flid="+result.flId);
	}
	setFunctionalLocWidth('frmMasterTbl','400px');
}
