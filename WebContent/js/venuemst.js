/**
 * 
 */


function frmMasterTbl_MNUETVENUEMST_beforeSubmit(){
	var elementType=jQuery("#frmMasterTbl input[id='elementType']").val();
    if(  elementType!= "LOCN"){
    	 alert("Select LOCN");
    	 return false;
     }
}