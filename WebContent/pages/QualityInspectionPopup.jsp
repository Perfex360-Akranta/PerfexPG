<script>
jQuery(document).ready(function(){
	initialiseForm('frmInsp');
	processGridnew("qualityInspectionpopgrid_input.qins", "q=2", "QualityPopGrid", "pagerpop", "", "", "");
	jQuery("#btnView").click(function(){
		//alert('view');
		var inspection = jQuery("#txtInspected").val();
		
		processGridnew("qualityInspectionpopgrid_input.qins", "q=2&inspection="+inspection, "QualityPopGrid", "pagerpop", "", "", "");
	});
});

function formatterJHLevel(id, options, rowObject)
{
	
	var columnKey="";
	var color='';
	var id = options.rowId;
	var columnName = options.colModel.name;	
	var columnNo=columnName.substring(columnName.indexOf("_")+1);
	var idval;
	if(columnName=="Results"){
		idval='txtTotal_';
		columnNo='score';
		return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 50px;text-align:right;" maxlength="2" value="" onfocus="gotFocuse('+id+','+columnNo+','+columnKey+')" onChange="outFocus('+id+','+columnNo+','+columnKey+')">';		
      }
	
	 else  if(columnName=="1") {
			idval='txt1_';
			return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 50px;text-align:right;background-color:'+color+'" / >';
					
			} 
	 else if(columnName=="2") {
			idval='txt2_';
			return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 50px;text-align:right;background-color:'+color+'" / >';
					
			} 
	 else if(columnName=="3") {
			idval='txt3_';
			return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 50px;text-align:right;background-color:'+color+'" / >';
					
			} 
	 else if(columnName=="4") {
			idval='txt4_';
			return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 50px;text-align:right;background-color:'+color+'" / >';
					
			} 
	 else if(columnName=="5") {
			idval='txt5_';
			return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 50px;text-align:right;background-color:'+color+'" / >';
					
			} 
	else if(columnName=="Avg"){
		idval='txtAvg_';
		columnNo='score';
		return '<input type="text" id="'+idval+columnNo + '_'+id +'" disabled="disabled" style="width: 50px;text-align:right;" maxlength="2" value="" onfocus="gotFocuse('+id+','+columnNo+','+columnKey+')" onChange="outFocus('+id+','+columnNo+','+columnKey+')">';		
      }
	else{
		idval='txtKkp_';
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id); onChange=outFocus("'+id+'","'+columnNo+'","'+columnKey+'"); type="text" keyId="'+columnKey+'" value="" maxlength="2" style="width: 100px;text-align:right;background-color:'+color+'" / >';		

		}
	
}

function outFocus(id,colId,rowId) {
	var one="txt1_"+colId + "_"+id;
	var two="txt2_"+colId + "_"+id;
	var three="txt3_"+colId + "_"+id;
	var four="txt4_"+colId + "_"+id;
	var five="txt5_"+colId + "_"+id;
	//var Max="txtMax_"+colId + "_"+id;
	var Target="txtAvg_score_"+id;
	
	//var  MinValue = jQuery("#"+Min).val();
	//var  MaxValue = jQuery("#"+Max).val();
	
	
	//var minval=jQuery("#minValue").val();
	//alert('MinValue'+MinValue);
	

	
	var  oneValue = jQuery("#"+one).val();
	var  twoValue = jQuery("#"+two).val();

	var  threeValue = jQuery("#"+three).val();
	var  fourValue = jQuery("#"+four).val();
	var  fiveValue = jQuery("#"+five).val();
	var res=jQuery("#"+Target).val();
	if(undefined == res || res.trim().length<=0 )
		 res=0;
	
		 var mul= parseInt(oneValue);
		 var mul1 = parseInt(twoValue);
		 var mul2 = parseInt(threeValue);
		 var mul3 = parseInt(fourValue);
		 var mul4 = parseInt(fiveValue);
			var result=parseInt(res)+parseInt(mul);
			var op = parseInt(result)/5;  
	 	    jQuery("#"+Target).val(op);
	
		
	

}
function taroutFocus(id,colId,rowId) {
	var Min="txtMin_"+colId + "_"+id;
	var Max="txtMax_"+colId + "_"+id;
	var Target="txtTar_"+colId + "_"+id;
	
	var  MinValue = jQuery("#"+Min).val();
	var  MaxValue = jQuery("#"+Max).val();
	var  TarValue = jQuery("#"+Target).val();
	var minval=jQuery("#minvalue").val();
	var maxval=jQuery("#maxvalue").val();
	//alert(maxval);
	if((TarValue > minval) && (TarValue > maxval)){
		
		alert('The Target Value should be in between min value and max value.');
		return true;
		}
		
	
	else {
		
		return false;
		}

}
function gotFocuse(id,colId,rowId){

	numericTextBox(id);
}

</script>
<form id='frmInsp' name='frmInsp' method="post">
<div>
	<table>
		<tr>
			<td valign="top">
				<div class='easyui-paddingbfpx'><label>Material Code</label></div>
				<div class='easyui-paddingbfpx'>
					<input type='text' id='txtCode' name='txtCode' class='easyui-text' maxlength='20' style="width:255px;height:21px;"  value="${requestScope.material }"/>
				</div>
				<div style="padding-top:10px;"class='easyui-paddingbfpx'><label>Material Description</label></div>
				<div class='easyui-paddingbfpx'>
					<textarea rows="2" cols="80" id='txtDescription' name='txtDescription'  maxlength='20' style="width:255px;height:75px;" >${requestScope.description }</textarea>
				</div>
			</td>
			<td style="padding-left:10px;">
				<div class='easyui-paddingbfpx'><label>Quality Inspected</label></div>
				<div class='easyui-paddingbfpx'>
					<input type='text' id="txtInspected" name="txtInspected" class='easyui-text' maxlength='20' style="width:255px;height:21px;text-align: right;" value="${requestScope.inspec }"/>
				</div>
				<div style="padding-top:10px;" class='easyui-paddingbfpx'><label>Quality Accepted</label></div>
				<div class='easyui-paddingbfpx'>
					<input type='text' id='txtAccepted' name='txtAccepted' class='easyui-text' maxlength='20' style="width:255px;height:21px;text-align: right;" value="${requestScope.accepted }"/>
				</div>
				<div style="padding-top:10px;" class='easyui-paddingbfpx'><label>Quality Rejected</label></div>
				<div class='easyui-paddingbfpx'>
					<input type='text' id='txtRejected' name='txtRejected' class='easyui-text' maxlength='20' style="width:255px;height:21px;text-align: right;" value="${requestScope.rejected }"/>
				</div>
			</td>
			<td style="padding-left:10px;"rowspan="3" valign="bottom">
				<div><input type="button" id="btnView" class="easyui-button" name='btnView' value='View' style="width:50px;height:25px;"/></div> 
			</td>
		</tr>
	</table>
	<div style="margin-left:-50px; margin-left:0px\9;">
	<table id='QualityPopGrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pagerpop'></div>
</div>
</div>
</form>
<input type="hidden" id ="minValue"/>