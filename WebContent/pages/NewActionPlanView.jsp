<script>
jQuery(document).ready(function(){
	//alert("dfd")
processGridnew("NewActionPlanView_input.nacpn","q=2","NewActionPlanViewgrid","pager","");
	
	
});

function viewGrid(url,filterString){  
	if( validateFilterSelection(filterString))
	{
		
		filterString +="&mode="+mode;
		//alert(filterString)
		//alert(filterString);
		processGridnew("NewActionPlanView_input.nacpn",filterString,"NewActionPlanViewgrid","pager","","doubleClickGrid","","","","");
	   return true;
	}
	return false;
}

function validateFilterSelection(filterString){ 
	
	//alert(filterString);
	    return true;

}


</script> 

<div id=wrapperRpt style="width:85%">
<div>
</div>



<div style="margin-top: -5px">

<table id="NewActionPlanViewgrid" >
<tr>
<td></td>
</tr>
</table>

<div id='pager'></div>
</div>
</div>