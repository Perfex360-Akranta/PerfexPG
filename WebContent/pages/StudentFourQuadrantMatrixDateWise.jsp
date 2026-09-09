<script>
jQuery(document).ready(function(){
    initialiseForm('frmStudentfourquadrantDtewise');
    jQuery('#chkEmpwise').attr('checked',true);
	//alert(jQuery('#hdnflid').val().length);
    if(jQuery('#hdnflid').val().length>0)
	     processGridnew("FourQuadrantMatrixDateWise_input.sfqm","?&q=2&typeval=&flid="+jQuery('#hdnflid').val(),"QuadrantDategrid","QuadrantDatepager");

	jQuery('#btnclose').click(function(){
        navigateToPrevForm();
	});
	
	jQuery("#chkEmpwise").click(function() {
		jQuery('#chkEmpwise').attr('checked',true);
        if(jQuery("#chkOplno").is(':checked'))
          jQuery('#chkOplno').attr('checked',false);
       
	});

	jQuery("#chkOplno").click(function() {
	     
		jQuery('#chkOplno').attr('checked',true);
		   
        if(jQuery("#chkEmpwise").is(':checked'))
          jQuery('#chkEmpwise').attr('checked',false);
       
	});

	jQuery('#btnview').click(function(){
		var typeval;
	    
		if(jQuery('#chkEmpwise').is(':checked'))
			typeval="EMPWSE";
		else if(jQuery('#chkOplno').is(':checked'))
			typeval="OPLNOWSE";
		else{
			alert(" Select Employee or Opl wise checkbox to view data");
			return false;
		}
		processGridnew("FourQuadrantMatrixDateWise_input.sfqm","?&q=2&flid="+jQuery('#hdnflid').val()+"&typeval="+typeval,"QuadrantDategrid","QuadrantDatepager");
	
	});

	//setLoadFormCallBackFrmId("frmStudentfourquadrantDtewise");

});


function viewGrid(url,filterString)
{
    if(validateFilterSelection(filterString))
	{   
		var flid = getFilterValue(filterString+'&', 'flid');
	   filterString += '&flid='+ flid;	
	 //  alert(filterString);
       var tableCaption = "Four Quadrant Matrix Date Wise";
	    processGridnew("FourQuadrantMatrixDateWise_input.sfqm",filterString,"QuadrantDategrid","QuadrantDatepager",tableCaption);	
	    return true;
	}
	
}

function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
	    return true;
}




</script>
<form name="frmStudentfourquadrantDtewise" id="frmStudentfourquadrantDtewise" action=" " method="post">
<div id='WrapperRpt'>
<div style="margin-left:16px;margin-left:-10px;">
<div style="margin-top:-20px;padding-left:1140px;display:none;">
    <input type="button" class="easyui-button" id="btnclose" name="btnclose" value="Close" style="height:23px;width:70px;"/>   
</div>
<div style="margin-top:-20px;padding-left:640px;">
    <input type="checkbox" id="chkEmpwise" name="chkEmpwise" value="Y"> <label style=""> Employee Wise</label>
    <span style="padding-left:10px;">
          <input type="checkbox" id="chkOplno" name="chkOplno" value=""> <label style=""> OPL No</label>
    </span>
    <span style="padding-left:10px;">
          <input type="button" class="easyui-button" id="btnview" name="btnview" value="View" style="height:20px;">
    </span>   
</div>
<table id='QuadrantDategrid'><tr><td></td></tr></table>
<div id='QuadrantDatepager'></div>
</div>
<input type="hidden" id="hdnflid" value="${requestScope.flid}"/>
<input type="hidden" id="hdncellid" value=""/>
<input type="hidden" id="hdnfromdate" name="hdnfromdate" value="${requestScope.fromdate}"/>
<input type="hidden" id="hdntodate" name="hdntodate" value="${requestScope.todate}"/>
<input type="hidden" id="hdnfrommonth" name="hdnfrommonth" value="${requestScope.frommonth}"/>
<input type="hidden" id="hdntomonth" name="hdntomonth" value="${requestScope.tomonth}"/>
</div>
</form>