<script>
jQuery(document).ready(function() 
		{

	
		
	initialiseForm('frmMttrMtbfDtl');
	jQuery('#submitForm').val('frmMttrMtbfDtl');
	//formatDateBox('dteMttrDate', 'dd-mm-yyyy');

	//alert(getFieldValue('hdnSecid'));
	   var url = jQuery('#hiddenUrl').val();
	   var tableCaption = "";
	   var dataString="?q=1";
	   var secid = jQuery('#hdnSecid').val();
	   var month = jQuery('#hdnMonth').val();
	  // viewGrid(url,dataString,tableCaption);

		
	   processGridnew("MTTRMTBFDetails_input.MTTR",dataString+"&sectId="+secid+"&month="+month,"MttrMtbfdetailsGrid","pagerMttrMtbf",tableCaption,"","",""); //"MTTRMTBFDetails_input.MTTR"
	
	    var factId = jQuery("#frmMttrMtbfDtl input[id='factory']").val();
	    var sectionId = jQuery("#frmMttrMtbfDtl input[id='section']").val();
		var cellId = jQuery("#frmMttrMtbfDtl input[id='cell']").val();
		var machId = jQuery("#frmMttrMtbfDtl input[id='machine']").val();
	    var flid = jQuery("#flid").val();
		var dataStr = "&factId=" + factId
				     + "&sectionId=" + sectionId
				     + "&cellId=" + cellId + "&machId="
				     + machId+"&flid="+ flid;
	  //alert(dataStr);
	  jQuery("#hdnFilterString").val(dataStr);
	  loadFunctionalLocation("MttrMtbffunLocation", "functionalLoc.mom", "MttrMtbffunLocation", "frmMttrMtbfDtl",dataStr);


	  jQuery('#dteMttrDate').datebox({  
			 formatter: function(date){ 
				 return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); 
				 }
		 });  
	
		});

		
		function formatterMttrDetails(id, options, rowObject)
		{	
			
			var rowId = options.rowId;
			var colId = options.pos;	
		if(colId==4)
			{
			return '<input type="button" id="btnActionplan_'+rowId+'_'+colId+'" name="btnActionplanGrid_'+rowId+'_'+colId+'" onclick="Breakdown('+rowId+','+colId+')"   style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
			}	
		}
		function Breakdown(rowid, colid)
		{
			
			//closePopUpDialoge('MttrMtbfView');
			var jsonstr = '{"mchId":"'+ rowid + '" ,"areaId":"'+rowid+'","month":"'+colid+'"}';
			var perstData = jQuery.parseJSON(jsonstr);		
			var filterString =  jQuery("#hdnFilterString").val();
			//navigateToNextForm('Breakdown_input.brdn?&Flid='+flid,"Breakdown Analysis");
			closePopUpDialoge('MttrMtbfView');
			navigateToNextForm('Breakdown_input.brdn?filterString='+escape(filterString),"",null,perstData);
			
		   // LoadPopUp("Breakdown","Breakdown_input.brdn", true, "87%", "90%", "7%", "6%", " ", "Breakdown Analysis","",true);
		}

		function MttrMtbfView_onClose(){
			return true;
		}
		
</script>

<form id=frmMttrMtbfDtl>
 <div id='wrapper' style="width:93%;">
         <div id="frmmomFuntKeyIds">
					<input type="hidden" id="factory" name="cmbMomdFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbMomdSectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbMomdCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbMomdMachineid" value=""></input>
					<input type="hidden" id="flid" name="cmbMomsFlid" value=" "></input>
		</div>
     <div  class="easyui-paddingbfpx" id="MttrMtbffunLocation" style="width: 77%;width:104%\9;padding-left: 58px;padding-left:0px\9;"> 
     </div>
    <div style="padding-left:5.7%;"><label>Month</label><div><input class="easyui-text"id="dteMttrDate" name="dteImrcDate" maxlength="20"	value="${requestScope.month}" style="height: 22px; width : 202px;"value=""/></div></div>
<div style="margin-left:-80px;">
<table id='MttrMtbfdetailsGrid'>
		<tr>
				<td></td>
			</tr>
</table>
<div id='pagerMttrMtbf'></div>
</div>
<input type="hidden" id="hdnSecid" name="hdnSecid" value="${requestScope.sectId}" />
<input type="hidden" id="hdnMonth" name="hdnMonth" value="${requestScope.month}" />
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
</div>


</form>