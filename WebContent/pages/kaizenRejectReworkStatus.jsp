	<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();

			jQuery('#hdnbdmode').val(url.indexOf('&bdmmode'));
			var flid=jQuery('#hdnLoginFlid').val();
			
			if(url.indexOf('&bdmmode')>0)	
				jQuery('#btnNewBkng').css("display","block");
			
			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			if( prevDataUrl == null || prevDataUrl.length <=0){		//+"&mode="+jQuery('#hdnFrmMode').val()
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
					if (url=="KaizenRejectReworkStatus_input.kaizen")
						mode="view";
					else
						mode="modify";
				}
				
				filterString+="&mode="+mode+"&empKeyId="+empKeyId+"&flid="+flid+"&empkzn="+empkzn;

		    	
			    if(url.indexOf('?')>0 && empkzn.length>0)
			    	url=url.substring(0,23);


		    	processGridnew(url,filterString,"list","pager",tableCaption,"doubleClickGrid","","kaizenGridOncompleteLoad");
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
			     var kznKeyid=jQuery("#list").jqGrid('getCell',row,"IMPRVNO");
			     //alert("kznKeyid::::"+kznKeyid);
			     var kznStatus=jQuery("#list").jqGrid('getCell',row,"STATUS");
			     //alert("The Kaizen Status"+kznStatus);
			     
			     if(kznStatus=='REJECTED'){
			    	 alert("This Kaizen Already Rejected");
			    	 return false;
			     }
			     if(kznStatus==' REWORK '){
			    	 alert("This Kaizen Already Reworked");
			    	 return false;
			     }
			     if(kznStatus=='JH LEADER APPROVAL PENDING'){
			    	 alert("This Kaizen JH LEADER APPROVAL PENDING");
			    	 return false;
			     }
			     if(kznStatus=='DMT LEADER APPROVAL PENDING'){
			    	 alert("This Kaizen DMT LEADER APPROVAL PENDING");
			    	 return false;
			     }
			     
		       LoadPopUp("loadkaizenRejRewStatus","kaizenRejStatusPopup_input.kaizen?&Keyid="+kznKeyid+"&kznStatus="+kznStatus,true,"45%","30%","10%","10%","","Kaizen Reject & Rework Status","");			
		}
		
</script>
<form name="frmKznreject" id="frmKznreject">
<div id="WrapperRpt">
	<div class="cntborder">
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
