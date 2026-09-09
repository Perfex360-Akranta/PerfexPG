<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){

	var url = jQuery('#hiddenUrl').val();
	if(url=="HseTagRemove_input.abnForm")
		jQuery('#completedBlock').hide();
	
		var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		if( prevDataUrl == null || prevDataUrl.length <=0)		
			viewGrid(url,"?q=1&firstClick=Y");
		else{
			viewGrid(unescape(prevDataUrl),"&q=1");
		}

	
	});

	function viewGrid(url,filterString,tableCaption)
	{
		if( validateFilterSelection(filterString))
		{
			processGridnew(url,filterString,"list","pager","","nxtgrid","","abnormalityGridOncompleteLoad");
			return true;
		}
	}
	function validateFilterSelection(filterString){
		return  true;
	}
	function abnormalityGridOncompleteLoad()
	{
		var rowIds = jQuery('#list').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			var cellVal =jQuery('#list').getCell(rowIds[i],"Status");
			if(cellVal=="COMPLETED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"DetectedDate","",{'color':'blue'});
			var tagClor = jQuery('#list').getCell(rowIds[i],"classification");
			if(tagClor == "GREEN")
				jQuery("#list").jqGrid('setCell',rowIds[i],"DetectedDate","",{'color':'green'});
		} 		
    }
		  
		function nxtgrid(id) 
		{
			
			var grid = jQuery('#list');
			var sel_id = grid.jqGrid('getGridParam', 'selrow');
			var abnStatus=jQuery('#list').getCell(sel_id,"Status");
			var TagNo = grid.jqGrid('getCell', sel_id, 'abnKeyid');
			var abndKeyid =grid.jqGrid('getCell', sel_id, 'abndKeyid');
			
			var mode  = jQuery("#hdnFrmMode").val();
			
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			
			navigateToNextForm("HseAbnormality_input.abnForm?q=2&AbnId="+TagNo+"&HseAbnId="+abndKeyid+"&abnStatus="+abnStatus+"&mode="+mode+"&filterButton=false","",null,{"filterString":url});
			
		 
	}

		function frmFilter_enableDisableSuccessCallBack()
		{
			var url = jQuery('#hiddenUrl').val();
			
			enableFields('chkDatewise');
			
			jQuery("#chkDatewise").attr('checked',true);
			jQuery("#chkMonthwise").attr('checked',false);		
			
			if(url=="HseModify_input.abnForm" || url=="HseTagRemove_input.abnForm" || url == "HseAbnormality_view.abnForm")
			{
				jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");
				setTimeout(function() {readOnlyFields('cmbAbnmTypeid');},100);						
				//jQuery("#cmbAbnmTagclassid").combobox("setValue","TAG000003");	
				//setTimeout(function() {readOnlyFields('cmbAbnmTagclassid');},100);	
				setTimeout(function() {jQuery("#hdnCatgUrl").val("Combo_Category.abnForm?q=2&frmType=SHE");},1200);
				setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
				reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
				reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
			}
			else 
				fillWithCurrentDate("dtefromDate");		
		}
		
		
</script>
<div id="wrapperRpt">
	<div id="completedBlock">
	
	 	<table style="margin-top:5px;">
		<tbody>
		<tr>
			<td>
				<span class="hse-comp"></span>
			</td>
			<td>
				<label style="color:black;font-weight: bold">Completed</label>
			</td>
	
		  </tr>
		 </tbody>
		</table>
	
	 
	<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
	 </div>	
	 <div style="" id="Abnmodify"> 
		<table id="list" ></table>
	<div id="pager"></div>
	</div>
</div>