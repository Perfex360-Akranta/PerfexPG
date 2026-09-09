	<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();
			jQuery('#hdnbdmode').val(url.indexOf('&bdmmode'));
			var flid=jQuery('#hdnLoginFlid').val();
			initialiseForm('frmKaizenDel');
			if(url.indexOf('&bdmmode')>0)	
				jQuery('#btnNewBkng').css("display","block");
			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			if( prevDataUrl == null || prevDataUrl.length <=0){
                var empkzn=jQuery('#hdnempkzn').val();
                var fromdate=jQuery('#hdnfromdate').val();
                var todate=jQuery('#hdntodate').val();
                var frommonth=jQuery('#hdnfrommonth').val();
                var tomonth=jQuery('#hdntomonth').val();
                if(empkzn.trim().length>0)
					viewGrid(url,"&dtFromMonth="+fromdate+"&dtToMonth="+todate+"&frommonth="+frommonth+"&tomonth="+tomonth);
				else
				   viewGrid(url,"&flid="+flid);
				
			}
			else{
				viewGrid(unescape(prevDataUrl),"&firstClick=Y&flid="+flid);
			}
			
		});
		
		function frmFilter_enableDisableSuccessCallBack()
		{

			jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
			
		}

		function viewGrid(url,filterString)
		{
			if( validateFilterSelection(filterString))
			{
				var tableCaption = "Kaizen Idea Sheet";
				
				var hdnFrmMode = jQuery('#hdnFrmMode').val();
				
				
				var mode=jQuery('#hdnFrmMode').val();
				var empkzn=jQuery("#hdnempkzn").val();
				var empKeyId=jQuery("#hdnempKeyId").val();
				var flid=jQuery("#hdnflid").val();

			
				if(url.indexOf('?')>0 && empkzn.length>0)
			    	url=url.substring(0,23);
		    			
				if(mode.trim().length>0)
					mode=jQuery('#hdnFrmMode').val();
				else {                         
					if (url=="KaizenViewDelete_input.kaizen")
						mode="view";
					else
						mode="modify";
				}
				
				filterString+="&mode="+mode+"&empKeyId="+empKeyId+"&flid="+flid+"&empkzn="+empkzn;

		    	
			    if(url.indexOf('?')>0 && empkzn.length>0)
			    	url=url.substring(0,23);


		    	processGridnew(url,filterString,"list","pager",tableCaption,"","","kaizenGridOncompleteLoad");
				return true;
			}	
		}

		function validateFilterSelection(filterString){
			return  true;
		}	

		function kaizenGridOncompleteLoad()
		{
			var rowIds = jQuery('#list').jqGrid().getDataIDs();
			for(var i=0;i<rowIds.length;i++)
			{
				var cellVal =jQuery('#list').getCell(rowIds[i],"status");
				if(cellVal=="COMPLETED")
				{	
					jQuery("#list").jqGrid('setCell',rowIds[i],"imprvDate","",{'color':'blue'});
					jQuery("#list").jqGrid('setCell',rowIds[i],"imprvno","",{'color':'blue'});
				} 
			}
	    }
		
		function list_selectRow(row){  
			if(jQuery('#kzbnCheckbox_'+"list"+'_'+row).is(':checked'))
				 jQuery("#list").jqGrid('setCell', row, 'CHECKVAL', '1');
			     var kaizenId=jQuery("#list").jqGrid('getCell',row,"IMPRVNO");
				 if(kaizenId==null || kaizenId=="" || kaizenId== " ")
						return false;
					else{
						var r=confirm("Are You Sure to Delete?");
						if(r){
						 alert("Data Deleted Successfully");
						processAjaxCalls("Kaizen_delete.kaizen?&kaizenId="+kaizenId+"&kznMode="+"kznMode"+"","","deleteSuccessCallback","errorCallBack");	
						jQuery("#list").trigger("reloadGrid");	
						return true;
						}
						else{
							return false;
					}
					}	 
		}	
		
	function frmKaizenDel_deleteSuccessCallback(result){
		alert(result.successData.msg);
		jQuery("#list").trigger("reloadGrid");
	}
		jQuery('#btnNewBkng').click(function(){
			var forwardData = jQuery('#hiddenUrl').val();
			var factoryId = forwardData.substring(forwardData.indexOf("factoryId")+"factoryId".length+1 );
			if(forwardData.indexOf("factoryId")>0)
				{navigateToNextForm("kaizen_input.kaizen?&factoryId="+factoryId+"&closeOnSave=true&filterButton=false","Improvement Projects - Creation");}
			else 
				{navigateToNextForm("kaizen_input.kaizen?&filterButton=false","Improvement Projects - Creation");}
		});
		
</script>
<form name="frmKaizenDel" id="frmKaizenDel">
<div id="WrapperRpt">
	<div class="cntborder">
	
	
	   <table style="margin-top:-2%;">
		<tbody>
		<tr>

			<td style="padding-left: 960px;">
				<input type="button" class="easyui-button" id="btnNewBkng" value="New Booking" style="display: none;" />
			</td>
		  </tr>
		 </tbody>
		</table>
	 
	
	 <div class="clear"></div>	 
	  	<div style="margin-top: -15px">
				 <table id="list" style="width:100%"><tr><td/></tr></table>
				 <div id="pager"></div>
				 </div>
				 <input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
				 	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
				 	<input type="hidden" id="hdnempkzn" name="hdnempkzn" value="${requestScope.empkzn}"/>
				 	<input type="hidden" id="hdnempKeyId" name="hdnempKeyId" value="${requestScope.empKeyId}"/>
				 	<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"/>
				 	<input type="hidden" id="hdnfromdate" name="hdnfromdate" value="${requestScope.fromdate}"/>
				 	<input type="hidden" id="hdntodate" name="hdntodate" value="${requestScope.todate}"/>
				 	<input type="hidden" id="hdnfrommonth" name="hdnfrommonth" value="${requestScope.frommonth}"/>
				 	<input type="hidden" id="hdntomonth" name="hdntomonth" value="${requestScope.tomonth}"/>
				 <input type="hidden" id="hdnbdmode" />
		</div>
</div>
</form>		
