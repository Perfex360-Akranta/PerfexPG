<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script>
jQuery(document).ready(function(){
		initialiseForm('frmMocModification');
		jQuery('#submitForm').val('frmMocModification');

		setLoadFormCallBackFrmId("frmMocModification");
		var url =jQuery('#hiddenUrl').val();
	//	//alert("URL:::"+url);
	disableUIButton("btnDirectMOC");
		var mode=jQuery("#mode").val();
	    viewGrid(url,"&q=2");	
       
	
});


function viewGrid(url,filterString)
{ 
	if( validateFilterSelection(filterString))
	{ 
		
		processGridnew("MocCreationGrid_input.mocn",filterString,"MocModificationGrid","pager","","MOCDoubleClick","");
        return true;
}
 return false;	
}

jQuery("#btnDirectMOC").click(function()
		 {
		
	var mode="create";	 
	alert("Clicked direct button sent mode == " + mode);
	   navigateToNextForm("ChangeRequest_input.mocn?q=2&mode="+mode+"&filterButton=false","MOCCreation");

		
			 
   });

function MOCDoubleClick(id)
{	
	
	var mode=jQuery("#mode").val();

//	alert("mode ==  " + mode);

	var rowData=jQuery("#MocModificationGrid").jqGrid('getRowData',id);	
	var Suggestion=rowData.KZBN_KAIZEN;
	var SuggestionId=rowData.KZBN_KEYID;
	var SuggestionFlid=rowData.KZBN_FLID;
	//alert("fLID=="+SuggestionFlid)
	var psrmkey=rowData.PSRM_KEYID;
	var MocKeyid=rowData.MOC_RFC_KEYID;
	var Responsibility=rowData.KZBN_RESPONSIBILITY
	var WhatifKey=rowData.WIFM_KEYID;
	var HazopKey=rowData.HZOM_KEYID;
	var Status=rowData.KZBN_STATUS

	
    navigateToNextForm("ChangeRequest_input.mocn?q=2&SuggestionFlid="+SuggestionFlid+"&WhatifKey="+WhatifKey+"&HazopKey="+HazopKey+"&Suggestion="+Suggestion+"&SuggestionId="+SuggestionId+"&Status="+Status+"&MocKeyid="+MocKeyid+"&Responsibility="+Responsibility+"&psrmkey="+psrmkey+"&filterButton=false","MOCCreation");


}



function validateFilterSelection(filterString){
return  true;
}


</script>
<form name="frmMocModification" id='frmMocModification'>
<table>
 <tr>
 <td>
 <div style="margin-left:0px;margin-top:0px;">
 <div  style="margin-top: 10px;margin-left:0px;">
	       <input type="button" class="easyui-button" value ="DirectMOC" id="btnDirectMOC" style="height:23px"/>
	      
	      
     	</div>
 </div>
       </td>
       </tr> 
      </table>

<div id="wrapperRpt"style= margin-top:2px;>
<table>
      <tr>
           <td style="float:left;">
                     </td>
           
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

    <div style="margin-left:10px; margin-left:65px\9;margin-top:5px;">
       <table  id='MocModificationGrid'>
			<tr>
			<td>
			<div style="margin-top: -26px"></div>
			</td>
			</tr>
		</table>
<div id="pager"></div>
</div>


<input type="hidden" id="mode" name="mode"  value="${requestScope.mode}"/> 
</form>
