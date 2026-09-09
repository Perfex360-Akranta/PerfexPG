<!-- Author : Siddharth.A -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	

			jQuery('#submitForm').val('frmKznComplete'); // set the id of form to submit
			initialiseForm('frmKznComplete');
			var url = jQuery('#hiddenUrl').val();

			//viewGrid(url,"?q=","kaizenGridOncompleteLoad");
			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			
			if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid(url,"?q=1&firstClick=Y","kaizenGridOncompleteLoad");
			else
				{	viewGrid(unescape(prevDataUrl),"q=1","kaizenGridOncompleteLoad");	}
			
			jQuery('#CompletedDtls').hide();
			jQuery('#frmKznComplete .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmKznComplete textarea').css('text-transform', 'uppercase');
			//fillComboBox("frmKznComplete","cmbkznmKeyid","combo_improvemnetNo.kaizen" );
			//fillComboBox("frmKznComplete","cmbkhdmCellid","cellCombo.commonFilter");
			//fillComboBox("frmKznComplete","cmbkhdmMachineid","machineCombo.commonFilter" );
		});
		//-------------VIEW GRID------------------------//
		function viewGrid(url,filterString,onload)
		{
			if(onload==null||onload=="undefined")
				onload="";
			processGridnew(url,filterString,"kznCompleteGrid","pager","","doubleClickGrid","",onload);
			return true;
		}		
		//----------------------------------------------//
		function kaizenGridOncompleteLoad()
		{
			var rowIds = jQuery('#kznCompleteGrid').jqGrid().getDataIDs();
			for(var i=0;i<rowIds.length;i++)
			{
				var cellVal =jQuery('#kznCompleteGrid').getCell(rowIds[i],"disp");
				
				if(cellVal=="2")
				{	
				//	jQuery("#kznCompleteGrid").jqGrid('setCell',rowIds[i],"imprvDate","",{'font-size':'13px','color':'blue'});
					jQuery("#kznCompleteGrid").jqGrid('setCell',rowIds[i],"imprvNo","",{'background-color':'#C0FFC0'});
				} 
			}
	    }
	    		
		function doubleClickGrid(rowid)
		{
			var rowData = jQuery("#kznCompleteGrid").jqGrid('getRowData',rowid);
			var selId = rowData.IMPRVNO;
			jQuery('#divLoadKznCompletion').html('');
			var url = jQuery("#kznCompleteGrid").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			navigateToNextForm("kaizen_input.kaizen?kznKeyid="+selId+"&mode=complete&filterButton=false","",null,{"filterString":url});	
		}

		function frmKznComplete_beforeDelete()
		{
			var delMsg = "Do You Want To Delete This HD  ?";
			if(confirm(delMsg) == false)
			{
					return false;
			}
			else
				{
				var checked = jQuery("#kznCompleteGrid input[name=hd_checkbox]'").length > 0;
				if (!checked){
	        			return false;
	    		}
	    		else{
	    			var kznHdDeletion = jQuery("#hdnSltedRowIds").val();
	    			
					if(kznHdDeletion != null && kznHdDeletion.length > 0)
						{var kaizenId="kznHdDeletion="+kznHdDeletion;
					//jQuery('#ImgDelete').unbind("click");
						return kaizenId;}
					else
						{
						return false;
						}
					//	process
				}
		}
		}

		function frmKznComplete_deleteSuccessCallback(result)
		{
			//jQuery('#ImgDelete').bind("click");
			jQuery('#kznCompleteGrid').trigger('reloadGrid');	
			jQuery("#hdnSltedRowIds").val('');
		}

		function frmKznComplete_exceptionCallback(result){}

		function cboxFormatter(id, options, rowObject)
		{
			var rowId = options.rowId;
			return '<input id="my_checkbox_'+ rowId +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){onSelect(\''+rowId + '\');}else{onUncheck(\''+ rowId +'\')}"/>';
		}

		function onSelect(id)
		{
			var isHdReq=jQuery('#kznCompleteGrid').getCell(id,"DISP");
			//alert(isHdReq);
			var rowData = jQuery("#kznCompleteGrid").jqGrid('getRowData',id);
			var selId = rowData.IMPRVNO;
			//alert(selId);
			var khdmkeyid=rowData.KEYID;
			//alert(khdmkeyid);
			jQuery('#hdnKznCompSelectId').val(id);
			processAjaxCalls("KznCompletedDetails.kaizen?kznKeyid="+selId+"&isHdReq="+isHdReq+"&khdmkeyid="+khdmkeyid+'&','&selectedId='+id,'kaizensuccess');
		}
		function kaizensuccess(result)
		{
			if(result!=null  && result.resultData == false)
			{	
				jQuery('#divLoadKznCompletion').html('');
				LoadPopUp("divLoadKznCompletion","fillKznCompletedDetails.kaizen?kznKeyid="+result.kznKeyid+"&khdmkeyid="+result.khdmkeyid+"&isHdReq="+result.isHdReq+"&selectedId="+result.selectedId,true,"65%","74%","-2%","5%",true,"Kaizen Completion");
			
			}
			else if(result.resultData!= "undefined" && result.resultData == true ) 
			{
				//alert(result.msg);
				var selId=jQuery('#hdnKznCompSelectId').val();
				jQuery('#my_checkbox_'+selId).removeAttr('checked');
			}
		}

		function onUncheck(id)
		{
			
		}

		//--------------------- HD VIEW CHECKBOX FORMATTER------- //
		function hdcboxFormatter(id, options, rowObject)
		{
			var rowId = options.rowId;
			return '<input name="hd_checkbox" id="hd_checkbox_'+ rowId + '"'+ (rowObject[1]=="True" ? 'checked':'') +' type="checkbox" ' + 'onclick="if(this.checked){onHDSelect(\''+rowId + '\');}else{onHDUncheck(\''+ rowId +'\')}"/>';
		}

		function onHDSelect(rowId)
		{
			var selRowIds = jQuery("#hdnSltedRowIds").val();
			
				if( selRowIds == " ")
					selRowIds = rowId +',';
				else
					selRowIds += rowId +',';
			
				jQuery("#hdnSltedRowIds").val(selRowIds);
		}

		function onHDUncheck(id)
		{
			var selRowIds = jQuery("#hdnSltedRowIds").val();
			selRowIds = selRowIds.replace(id+',','');
			//alert(selRowIds);
			jQuery("#hdnSltedRowIds").val(selRowIds);
		}
	   //-------------------------------------------------------------//	

	function divLoadKznCompletion_onClose()
	{
		var rowId=jQuery('#hdnKznCompSelectId').val();
		jQuery('#my_checkbox_'+rowId).removeAttr('checked');
		return true;
	}
	
	jQuery('._close').click(function(){
		
	});

		jQuery('#View').click(function(){
			viewGrid("KaizenComp_input.kaizen","?row=2","kaizenGridOncompleteLoad");
		});

		jQuery('#HdView').click(function(){
			jQuery("#hdnSltedRowIds").val('');
			viewGrid("KznHDComp_input.kaizen","?row=2");
		});

		/*jQuery('#ImgDelete').click(function()
		{
		//	var gridData  ='&KznHdDeletion='+JqGridToJsonSelectdRows('kznCompleteGrid','hd_checkbox','txtselectionFlag');
		//	var KznHdDeletion  =JqGridToJsonSelectdRows('kznCompleteGrid','hd_checkbox','txtselectionFlag');
		
			var checked = jQuery("#kznCompleteGrid input[name=hd_checkbox]'").length > 0;
			if (!checked){
        			return false;
    		}
    		else{
    			var kznHdDeletion = jQuery("#hdnSltedRowIds").val();
				if(kznHdDeletion != null && kznHdDeletion.length > 0)
					deleteRecord("frmKznComplete","KaizenComp_delete.kaizen?kznHdDeletion="+kznHdDeletion);
				//jQuery('#ImgDelete').unbind("click");
					
				//	processAjaxCalls("KaizenComp_delete.kaizen?kznHdDeletion="+kznHdDeletion,"","KznHdDeletesuccessCallBack");
    		}
		});*/

		function KznHdDeletesuccessCallBack(result)
		{
			alert(result.successData.msg);
			jQuery('#kznCompleteGrid').trigger('reloadGrid');	
			jQuery("#hdnSltedRowIds").val('');
		}

		jQuery('#chkAllKznHD').click(function(){
			var rowIds = jQuery('#kznCompleteGrid').jqGrid().getDataIDs();
			
			for(var i=0;i<rowIds.length;i++)
			{
				jQuery('#hd_checkbox_'+rowIds[i]).attr({'checked':true});
			}
		});

		
</script>

<form id="frmKznComplete">
<div id="wrapperRpt">
<table style="margin-top:-28px;float:left">
	<tbody>
	<tr>
		<td>
		
			<span class="bd-completed"></span></td>
		
		
		<td><span style="float:right;"><label style="font-weight: bold">Horizontal Deployment</label></span>
		</td>
		
		
		</tr>
		</tbody>
		</table>
		<table style="margin-left:814px;">
		<tr><td ><div style="margin-left:0px;margin-top: -26px"><input type="button" class="easyui-button"style="width:50px;" id="View" name="View" value="View"/></div></td>
		<td><div style="margin-top: -28px"></div><input type="button" class="easyui-button" id="HdView" name="HdView" style="width:130px;" value="HD View For Deletion"/></div></td></tr>
		</table>
<div class="cntborder">

	
	<div class="clear"></div>
		<div style="margin-top: -11px"><label class="notes" style="font-weight: bold;">Double Click on data row to View Improvement Projects</label></div></div>
			 <table id="kznCompleteGrid" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
			 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
			 <input type="hidden" id="hdnSltedRowIds" value=" " />
			 <input type="hidden" id="hdnKznCompSelectId" value=""/>
			 <input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
			 
	</div>
		<div class="clearfix">	</div>

  </div>			
</form>		
<!-- <div id="divLoadKznCompletion"> </div> -->