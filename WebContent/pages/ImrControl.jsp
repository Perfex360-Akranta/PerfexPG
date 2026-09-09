<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>	
jQuery(document).ready(function(){
	
		initialiseForm('frmImrControl');
		jQuery('#submitForm').val('frmImrControl');
		fillComboBox('frmImrControl','cmbImrgCharacteristicid','ImrControl_Form.imc');
		fillComboBox('frmImrControl','cmbImrgProductid','comboGradeSpec.commonFilter');	
	    
		//viewGrid("","");
    	var factId = jQuery("#frmImrControl input[id='factory']").val();
        var sectionId = jQuery("#frmImrControl input[id='section']").val();
        var cellId = jQuery("#frmImrControl input[id='cell']").val();
        var machId = jQuery("#frmImrControl input[id='machine']").val();
        var flid = jQuery("#frmImrControl input[id='flid']").val();
        var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
        if(dataStr.trim().length<=0)
        	dataStr = "";	  					
        loadFunctionalLocation("ImrfunLoc","functionalLoc.imc","frmImrLocationfunLocationValues","frmImrControl",dataStr);
        numericTextBox('txtImrgUpperlimit') ;
        numericTextBox('txtImrgLowerlimit') ;
        processGridnew("ImrControl_input.imc","q=2","ImrControlGrid","Pager","","DoubleClick","","load_complete");
});

/*jQuery('#clr').click(function(){
	//alert('Clear');
	jQuery("#ImrControlGrid").clearGridData();
	
});*/

 jQuery("#btnAdd").click(function()
		{
		saveForm('frmImrControl','ImrControl_save.imc');
		
		});

function setLowerLimit()
{
      var Upper=jQuery("#txtImrgUpperlimit").val();
      var Lower=jQuery("#txtImrgLowerlimit").val();
		if (Upper != null  &&  Lower != null) 
		//
	    {
	    	Upper = parseInt(Upper);
	        Lower =  parseInt(Lower);  
			  //alert(j<k);
		  if(Upper <=Lower){ 
		   alert("LowerLimit Should be lesser than Upper limit",''); 
		   return false;
		  }
         }
       // else 
		//{
        	 //alert("LowerLimit Should be lesser than Upper limit"); 
		//}
}

/*function  frmImrControl_beforeSubmit()
{
var upper=jQuery("#txtImrgUpperlimit").val();
var lower=jQuery("#txtImrgLowerlimit").val();
  if(upper<=lower)
	{
	alert(" Select Atleast One Employee For Attendance ");
	return false;
	}
	
}*/


 function frmImrControl_successsCallback(result)
 {
	var keyid=result.masterId;
	//alert(keyid);
	 jQuery("#txtImrgKeyid").val(result.keyid);
 	 processGridnew("ImrControl_input.imc","q=2","ImrControlGrid","Pager","","DoubleClick","","load_complete");
 	 setFieldValue('txtImrgKeyid','');
 	 setFieldValue('cmbImrgCharacteristicid','');
 	 setFieldValue('cmbImrgProductid','');
 	 setFieldValue('txtImrgUpperlimit','');
 	 setFieldValue('txtImrgLowerlimit','');
 	   //clearForm('frmImrControl');
 	//jQuery("#ImrControlGrid").trigger("reloadGrid");
 }
 function DoubleClick(id)
 {
 	 var rowData = jQuery("#ImrControlGrid").jqGrid('getRowData',id );
 	 //alert(rowData.cmbImrgCharacteristicid+"  rowData.Productid  "+rowData.cmbImrgProductid);
 	 setFieldValue("txtImrgKeyid",rowData.Keyid);
 	 setFieldValue("cmbImrgCharacteristicid",rowData.cmbImrgCharacteristicid);
 	 setFieldValue("cmbImrgProductid",rowData.cmbImrgProductid);   // cmbImrgProductid
 	 setFieldValue("txtImrgUpperlimit",rowData.Upperlimit);
 	 setFieldValue("txtImrgLowerlimit",rowData.Lowerlimit);
 
 }



	
 /*function frmImrControl_selectedRow(id)
 {
	 alert(10);

    jQuery('#ImrControlGrid_Buttons'+id).removeClass('easyui-button');
	jQuery('#ImrControlGrid_Buttons'+id).addClass('grdBtnClick');
 }*/
 function frmImrControl_onSuccesssCallback()
 {
		clearForm("frmImrControl");
     jQuery("#ImrControlGrid").trigger("reloadGrid");
 }

 jQuery("#btnDelete").click(function()
		 { 
		 	removeRecord();
		 });
 function removeRecord(keyid) {
		
		var Imrrow = jQuery("#ImrControlGrid").jqGrid('getDataIDs');//	row get data
		var r = confirm("Do You Want To Delete?");
	
	    for (i = 0; i < Imrrow.length; i++) {
	        var rowid = Imrrow[i];
	        keyid = jQuery("#ImrControlGrid").jqGrid('getCell', rowid, "Keyid");
	        //alert(keyid);
	       // var gridvalue="&stdWorkSheetdelete="+getGridSelectArray("StdWoSheetGrid");
	        
			//alert(keyid);
		if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {

		}
	    }

	    if (r == true) 
		    {
				 processAjaxCalls("IMRControl_remove.imc","Keyid="+keyid, 'remove_successCallBack','remove_errorCallBack');
           }else
		return false;
					

 }

 //jQuery("#"+tableId).clearGridData();

 function remove_successCallBack(result)
 {
 	alert(result.successData);
 	setFieldValue("cmbImrgCharacteristicid",'');
	setFieldValue("cmbImrgProductid",'');
	setFieldValue("txtImrgUpperlimit",'');
	setFieldValue("txtImrgLowerlimit",'');
 	jQuery("#ImrControlGrid").trigger("reloadGrid");
 }
 function remove_errorCallBack() 
 {
	 
 }
  
       
 </script>  
  <form id="frmImrControl" name="frmImrControl">
<div id="wrapper">
<div id="frmImrControlGridFuntKeyIds"  >							
	<input type="hidden" id="factory" name="factory"  value="" ></input>
	<input type="hidden" id="section" name="section"  value=""></input>
	<input type="hidden" id="cell"    name="cell"     value=""></input>
	<input type="hidden" id="machine" name="machine"  value=""></input>
   <input type="hidden" id="flid" name="txtImrgFlid"  value="${requestScope.newGenTlImrcharacter.imrgFlid}"></input>


	<div id="ImrfunLoc" style="width:500px; width:700px\9"></div></div>
	<table>
		<tr>
		<td>
				<div class="easyui-paddingbfpx"> 
				<label class ="mandatory-lbl"> Characteristic </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="cmbImrgCharacteristicid" name="cmbImrgCharacteristicid" class="easyui-combobox"   value="${requestScope.newGenTlImrcharacter.imrgCharacteristicid}"  style="width:200px"; />
				
				</div>
			</td>
			
			<td style="padding-left: 5px">
				<div class="easyui-paddingbfpx"> 
				<label class ="mandatory-lbl"> Product </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="cmbImrgProductid" name="cmbImrgProductid" class="easyui-combobox"  maxlength="15"  value="${requestScope.newGenTlImrcharacter.imrgProductid}" style="width:200px;" />
				
				</div>
			</td>
		
		
			<td style="padding-left: 5px">
				<div class="easyui-paddingbfpx"> 
				<label class ="mandatory-lbl"> LowerLimit </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="txtImrgLowerlimit" name="txtImrgLowerlimit"  class="easyui-text"  maxlength="15"  onblur="setLowerLimit();"    style="width:180px;"  />
				
				
				</div>
			</td>
			<td style="padding-left: 5px">
				<div class="easyui-paddingbfpx"> 
				<label class ="mandatory-lbl"> UpperLimit </label>
				</div>
				<div  class="easyui-paddingbfpx">
				<input id="txtImrgUpperlimit" name="txtImrgUpperlimit" class="easyui-text"  maxlength="15"   onblur="setLowerLimit();"    style="width:180px;" />
				
				</div>
			</td>
			
	</tr>
	</table>
		<span style="padding-left:600px">
      				<input type="button" class="easyui-button" style="width:45px;" id="btnAdd" name="btnAdd" value="Insert"/></span>
		             <input type="button" class="easyui-button" value ="Delete" id="btnDelete"  name="btnDelete" style="width:45px;""/> 
		           

		<div style="float:left;">
			 <table id="ImrControlGrid" ></table> 
			<div id="Pager"></div>
		</div> 
		
		 <input type="hidden" id="mode"/>

		  <input id="txtImrgKeyid" type='hidden' name="txtImrgKeyid" value="${requestScope.newGenTlImrcharacter.imrgKeyid}" />
<!--		    <input id="cmbImrgCharacteristicid" type='hidden' name="cmbImrgCharacteristicid" value="${requestScope.newGenTlImrcharacter.imrgCharacteristicid}" />-->
<!--		      <input id="hdnImrgProductid" type='hidden' name="cmbImrgProductid" value="${requestScope.newGenTlImrcharacter.imrgProductid}" />-->
</div>
</form>