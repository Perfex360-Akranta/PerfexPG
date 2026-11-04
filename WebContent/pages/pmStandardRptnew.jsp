
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();
			
			viewGrid(url,"&q=2");	
			//setLoadFormCallBackFrmId("frmActWiseRpt");
		});
	/*	function frmActWiseRpt_afterLoadCallBack(){
			toggleCommonFilter();	
		}	*/					
		
		function viewGrid(url,filterString)
		{			
			if( validateFilterSelection(filterString))
			{				
				processGridnew(url,filterString,"pmstandard","pager","","doubleClick","","loadCompleteFunction");
				return true;
			}	
			return false;
		}
		
		function validateFilterSelection(filterString){

			/* if( ! checkFilterValueExist(filterString,"cmbSectid"))
				{
					alert("Select Section");
					return false;
				}	*/
			 return true;
		}
		function loadCompleteFunction(id){
		jQuery("#pmstandard").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			//jQuery('#getcellContent').val(cellcontent);
			jQuery('#getiCol').val(iCol);	
		}});
		}
		function doubleClick(id)
		{
			var rowData = jQuery("#pmstandard").jqGrid('getRowData',id);
			var row = jQuery("#pmstandard").jqGrid('getDataIDs');
			var iCol = jQuery('#getiCol').val();
			var colRowData=jQuery('#pmstandard').jqGrid('getGridParam', 'colNames');
			var colm = jQuery("#pmstandard").jqGrid ('getGridParam','colModel');
			var tradeId = colm[iCol].name;
			var tradeKeyid='';
			var filterString='';
			var persistdata='';
			var title="PM Standard Assmebly";
			filterString += "&machId="+rowData.KEYID;
			
			filterString += "&equipmentname="+rowData.Equipment;
			filterString += "&equipmentno="+rowData.EquipmentCode;
			
			if(tradeId == 'Civil' || tradeId == 'Electrical' || tradeId == 'General' || tradeId == 'Mechanical'|| tradeId == 'Electronics' || tradeId == 'Hydraulics' || tradeId == 'Pneumatics' )
				{
				
					if(tradeId == 'Civil')
						tradeKeyId='TDE000004'; 
					else if(tradeId == 'Electronics')
						tradeKeyId='TDE000002';
					else if(tradeId == 'Mechanical')
						tradeKeyId='TDE000003';
					else if(tradeId == 'Electrical') 
						tradeKeyId='TDE000005';
					else if(tradeId == 'Hydraulics') 
						tradeKeyId='TDE000006';
					else if(tradeId == 'Pneumatics') 
						tradeKeyId='TDE000007';
					else 
						tradeKeyId='TDE000001';
					filterString += "&tradeId="+tradeKeyId;
					filterString += "&tradeName="+tradeId;
					
					navigateToNextForm("PmStandardrptTemplateAssembly_input.pmStandarsRpt?"+filterString,title);
					//navigateToNextForm("PmStandardrptTemplateAssembly_input.pmStandarsRpt"+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm,&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false",'',null,persistdata);
				}
			else
				{
					//filterString += "&tradeId="+tradeKeyId;
					//filterString += "&machId="+rowData.KEYID;
					filterString += "&tradeName=All";
					navigateToNextForm("PmStandardrptTemplateAssembly_input.pmStandarsRpt?"+filterString,title);
					//navigateToNextForm("PmStandardrptTemplateAssembly_input.pmStandarsRpt"+"&filterString="+filterString+"&loadContentDivId=Loadactivitygridfrm,&preLoadContentDivId=preloadDIVid3&isHidePrevForm=false",'',null,persistdata);
				}
			/* for(var i=0;i<row.length;i++)
			 {
				 for(var j=10;j<cm.length;j++)
		     	 {
			     	 var zeroVal = jQuery("#pmstandard").jqGrid('getCell',row[i],cm[j].name);	
					  if(zeroVal =='4'){
					  		jQuery("#pmstandard").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#ff8040'});
					  }
		     	 }
			 }*/
		}

		function validateFilterSelection(filterString){	
			
			/* if( getFilterValue(filterString, "cmbSectid") == "") 
				{
				alert("Select Section");
				return false;
				}
			else*/
				return  true;
		}

		function frmFilter_enableDisableSuccessCallBack()
		{
			jQuery("#chkMonthwise").attr('checked',false);
			/*jQuery("#chkDatewise").attr('checked',false);*/
			jQuery('#cboPmjobtype').children('option[value="MBM"]').hide();
			jQuery('#cboPmjobtype').children('option[value="RBM"]').hide();
			jQuery('#cboPmjobtype').children('option[value="CAL"]').hide();
			jQuery('#cboPmjobtype').children('option[value="SDM"]').hide();
			jQuery('#dtefromMonth').datebox('setValue','');
			jQuery('#dtetoMonth').datebox('setValue','');	
			readOnlyFields('dteyear');
			readOnlyFields('dtetoMonth');
			readOnlyFields('dtefromMonth');
			readOnlyFields('dtefromDate');
			readOnlyFields('dtetoDate');
			readOnlyFields('dtefromDate');
			readOnlyFields('chkMonthwise');
			readOnlyFields('chkDatewise');
			//alert("Advaned filter");
			jQuery('#dtefromDate').datebox('setValue','');
			jQuery('#dtetoDate').datebox('setValue','');
			jQuery('#cmbCircle').combobox('disable');
		}
</script>
<form id="frmActWiseRpt">
	<div id="wrapperRpt">
	<div style="margin-top: -28px"> 
			 <table id="pmstandard" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div></div>
	</div>
	<input type="hidden" id="getiCol" name="getiCol"/>
</form>