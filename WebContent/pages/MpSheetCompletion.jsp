<!-- Author : Siddharth.A -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

			jQuery('#submitForm').val('frmMPsComplete'); // set the id of form to submit
			initialiseForm('frmMPsComplete');
			var url = jQuery('#hiddenUrl').val();

			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			
			if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid(url,"?q=1&firstClick=Y","mpsGridOncompleteLoad");
			else
				{	viewGrid(unescape(prevDataUrl),"q=1","mpsGridOncompleteLoad");	}
			
			jQuery('#CompletedDtls').hide();
			jQuery('#frmMPsComplete .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmMPsComplete textarea').css('text-transform', 'uppercase');
		});
		//-------------VIEW GRID------------------------//
		function viewGrid(url,filterString,onload)
		{
			if(onload==null||onload=="undefined")
				onload="";
			processGridnew(url,filterString,"mpsCompleteGrid","pager","","doubleClickGrid","",onload);
			return true;
		}		
		//----------------------------------------------//
		function mpsGridOncompleteLoad()
		{
			var rowIds = jQuery('#mpsCompleteGrid').jqGrid().getDataIDs();
			for(var i=0;i<rowIds.length;i++)
			{
				var cellVal =jQuery('#mpsCompleteGrid').getCell(rowIds[i],"disp");
				
				if(cellVal=="HD")
				{	
				//	jQuery("#mpsCompleteGrid").jqGrid('setCell',rowIds[i],"imprvDate","",{'font-size':'13px','color':'blue'});
					jQuery("#mpsCompleteGrid").jqGrid('setCell',rowIds[i],"mpsKeyid","",{'background-color':'#C0FFC0'});
				} 
			}
	    }
	    		
		function doubleClickGrid(rowid)
		{
			var rowData = jQuery("#mpsCompleteGrid").jqGrid('getRowData',rowid);
			var selId = rowData.mpsKeyid;
			jQuery('#divLoadMpsCompletion').html('');
			var url = jQuery("#mpsCompleteGrid").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			navigateToNextForm("MPSheet_input.mps?mpsKeyid="+ selId+"&mode=complete&filterButton=false","",null,{"filterString":url});	
		}

		
		function frmMPsComplete_exceptionCallback(result){}

		function cboxFormatter(id, options, rowObject)
		{
			var rowId = options.rowId;
			return '<input id="my_checkbox_'+ rowId +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){onSelect(\''+rowId + '\');}else{onUncheck(\''+ rowId +'\')}"/>';
		}

		function onSelect(id)
		{
			var isHdReq=jQuery('#mpsCompleteGrid').getCell(id,"disp");
			var rowData = jQuery("#mpsCompleteGrid").jqGrid('getRowData',id);
			var selId = rowData.mpsKeyid;
			var khdmKeyid=rowData.kznmKeyid;
			jQuery('#hdnMpsCompSelectId').val(id);
			//processAjaxCalls("MpsCompletedDetails.mps?mpsKeyid="+selId+"&isHdReq="+isHdReq+"&khdmKaizenid="+khdmKeyid+'&','&selectedId='+id,'kaizensuccess');
			jQuery('#divLoadMpsCompletion').html('');
			LoadPopUp("divLoadMpsCompletion","MpsCompletedDetails.mps?mpsKeyid="+selId+"&khdmKaizenid="+khdmKeyid+"&isHdReq="+isHdReq+"&selectedId="+id,true,450,450,5,250,"","Completed  Details");
	
		}
		function kaizensuccess(result)
		{
		
		}

		function onUncheck(id)
		{
			
		}


	function divLoadKznCompletion_onClose()
	{
		var rowId=jQuery('#hdnMpsCompSelectId').val();
		jQuery('#my_checkbox_'+rowId).removeAttr('checked');
		return true;
	}
	
	jQuery('.btn_close').click(function(){
		
	});


		
</script>

<form id="frmMPsComplete">
<div id="wrapperRpt">
<div class="cntborder">
<table style="margin-top:5px;">
	<tbody>
	<tr>
		<td>
			<span class="bd-completed"></span>
		</td>
		<td>
			<label style="font-weight: bold">Horizontal Deployment</label>
		</td>
		
	  </tr>
	 </tbody>
	</table>
	
	
	<div class="clear"></div>
		<div><label class="notes" style="font-weight: bold;">Double Click on data row to View Improvement Projects</label></div>
			 <table id="mpsCompleteGrid" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
			 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
			 <input type="hidden" id="hdnSltedRowIds" value=" " />
			 <input type="hidden" id="hdnMpsCompSelectId" value=""/>
			 <input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
			 
	</div>
		<div class="clearfix">	</div>

  </div>			
</form>		
<div id="divLoadMpsCompletion"></div>