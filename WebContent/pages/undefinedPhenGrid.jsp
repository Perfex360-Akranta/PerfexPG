<script type="text/javascript">

		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();			
			if(url=="undef_view.upm")			
				jQuery('#txtformMode').val("APPROVAL");				
			else			
				jQuery('#txtformMode').val("PROPOSAL");
				
			processGridnew(url,"?q=2","list","pager","Undefined Phenomena-New Phenomena Proposal","fillForm");
		});
		function viewGrid(url,filterString)
		{
			processGridnew(url,"?q=2","list","pager","Undefined Phenomena-New Phenomena Proposal","fillForm");				
		}
		function fillForm(id)
		{
			var rowData = jQuery("#list").jqGrid('getRowData',id);			
			var bdNo = rowData.txtEmrno;
			var bdDate = rowData.dteEntrydate;
			var subUnitId = rowData.txtSubunit;
			subUnitId = subUnitId.replace('. ','.');
			
			var sectionId = rowData.txtSectid;
			var eqpId = rowData.txtMchid;
			var AssmId = rowData.txtAsmid;
			var woStart = rowData.dteWostart;
			woStart = woStart.substring(12,17);
			var woEnd = rowData.dteWoend;
			woEnd = woEnd.substring(12,17);
			var bookedTime = rowData.dteDownTime;
			var dataString = '?q=2&cmbbdmsKeyid='+bdNo+'&txtbdmsEntrydate='+bdDate+'&cmbbdmsCellid='+sectionId+'&cmbbdmsMachineid='+eqpId+'&cmbdownTimeMins='+bookedTime;
				dataString += '&cmbbdmsAssemblyid='+AssmId+'&dtebdmsWostart='+woStart+'&dtebdmsWoend='+woEnd+'&cmbbdmsSectionid='+subUnitId+'&txtformMode='+jQuery('#txtformMode').val();
				
				if(jQuery('#txtformMode').val()=="APPROVAL")
				{
					//alert('Inside');
					dataString += '&txtbnprProposedphn='+escape(rowData.txtProposedPhn);
					if(rowData.txtProposedCause != null && rowData.txtProposedCause != '' && rowData.txtProposedCause != ' ')
						dataString += '&txtbnprProposedcause='+escape(rowData.txtProposedCause);
					
					navigateToNextForm('undefined_input.upm'+dataString,'Undefined Phenomena-New Phenomena Approval');
				}
				else
					navigateToNextForm('undefined_input.upm'+dataString,'Undefined Phenomena-New Phenomena Proposal');
		}
		
</script>
<div id="wrapperRpt">
<form>

	<div style=""> 
			
			 <input type="text" style="display:none" id="txtformMode" name="txtformMode"/>
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>

</form>
</div>
