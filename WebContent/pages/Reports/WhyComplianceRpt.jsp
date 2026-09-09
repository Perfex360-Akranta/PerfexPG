<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	viewGrid(actionPart,"?q=1&firstClick=Y");
	

});


function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		filterString += '&drillFlag=f';
		 
		processGridnew(url,filterString,"yyGrid","pager",'',"","", "yyGrid_loadComplete");
		 
		
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}


function yyGrid_loadComplete(){
	 
	
	var rowIds = jQuery("#yyGrid").getDataIDs();
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#yyGrid").jqGrid('getCell', rowIds[0], 'mainkeyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);
				
			}
		}
	setTotalRowCss('yyGrid');
	/*jQuery("#yynotes").css('display','block');	
	jQuery("#yymonnotes").css('display','block');	
	jQuery("#yycellnotes").css('display','block');	*/
	var row = jQuery("#yyGrid").jqGrid('getDataIDs');
	
		jQuery("#yyGrid").jqGrid('setRowData', row.length, false );//{color:'blue','font-weight':'bold'}
		jQuery("#yyGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
			

			//alert(" rowid"+rowid);
			if( rowid.indexOf('-') < 0){
				
				if( jQuery("#yyGrid tr[id="+rowid +']').length > 0 ){
					
					if( ! jQuery("#yyGrid tr[id="+rowid +']').next().hasClass('ui-subgrid') )
					{	
						
						yyGrid_doubleClickGrid(rowid,iCol);
					}	
					
				}
			}
			//if(iCol != 1){
				
			
			
	}});

		jQuery("#yyGrid").jqGrid( 'setGridParam',{subGridRowExpanded: function(subgrid_id, row_id) {
			

			var subgrid_table_id, pager_id; subgrid_table_id = subgrid_id+"_t"; pager_id = "p_"+subgrid_table_id;
			jQuery("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
			var parentId = jQuery("#yyGrid").jqGrid('getCell', row_id, 'mainkeyid');
			 
			var dataString  ="";		
			dataString = 'parentId='+parentId;
		//alert(row_id);
		if(checkzeroesMon("yyGrid",row_id,2)){				
			processGridnew("WhyComplianceMonthwise_input.yyComRpt",dataString,subgrid_table_id,pager_id,'','',"","subGrid_loadComplete");
			jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false});
		}	
		else{
		
			alert("No Data Exist");
			
			//jQuery('#yyGrid').jqGrid("collapseSubGridRow",row_id);
			//alert(collapseSubGridRow);
		}
		
		
		
		//jQuery('#gbox_yyGrid_1_t').css('padding-right','350');
	}});

	jQuery('#yyGrid tr:last td:first-child').removeClass();
	jQuery('#yyGrid tr:last td:first-child').html(' ');

	
}

function yycellGrid_loadComplete(){ 
	hideShowBack(true);
}

function subGrid_loadComplete(tableId){

	setTotalRowCss(tableId);

}
function yyGrid_onProcessGridBack( ){

	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#yyGrid").getDataIDs();
  
	var parentId =  jQuery("#yyGrid").jqGrid('getCell', rowIds[0], 'mainkeyid');
	var dataString  ="";		
	var cmbSectid = getFieldValue('section');
	var fctid = getFieldValue('factory');	
	
	dataString = 'drillFlag=b';
	if( cmbSectid  != null && cmbSectid  != 'false' && cmbSectid  != false  )
		dataString = 'cmbSectid='+cmbSectid;
	if( parentId != null && parentId != 'false' && parentId != false  ){
	
	}
	else{
		dataString += '&fromDetail=Y';
	
	}
	
	processGridnew(url,dataString,"yyGrid","pager",'',"yyGrid_doubleClickGrid","", "yyGrid_loadComplete");	
		
}	

function yyGrid_doubleClickGrid(id,iCol,colVal){ 

	//alert("iCol "+iCol+" dd=" +colVal +"id "+id);
	
	if( jQuery("tr[id='"+ id +"' ] td:first-child").hasClass("sgexpanded")  == true )
		return;
	 

	var rowData = jQuery("#yyGrid").jqGrid('getRowData',id);
	var url = jQuery('#hiddenUrl').val();
	
	var selId = rowData.mainkeyid;
	var grid = jQuery('#yyGrid');
	var sel_id = grid.jqGrid('getGridParam', 'selrow');
	var CellData = grid.jqGrid('getCell', sel_id,iCol );
 
	//if(checkForZeroes("yyGrid",selId,2)){
	var filterData ="?";
	//if(selId.substr(0,3) != 'MCH'){
	//if(CellData=='0'){
	
		
		//}
	/* }else{
		alert("No Record to View");
		return false;
	}*/
	 
	if(iCol !=9){
		if(iCol != 2){
		 
			if(CellData =='0'){
				alert("No Data to view");
			return false;
			}
			else
				{
				filterData += getParamName(selId)+"="+selId;
	  			filterData += '&fromDetail=Y';
				filterData +='&colIndex=' +  iCol ;
				processGridnew(url,filterData,"yyGrid","pager",'',"yyGrid_doubleClickGrid","", "yycellGrid_loadComplete");
			 }return false;
		}
 	 	 
	if(checkForZeroes("yyGrid",id,3)){	
		if(selId.substr(0,3) != 'MCH'){
	  		if(iCol != 1){
	 
				filterData += '&drillFlag=f';
				filterData += '&parentId='+ selId;
		 
			processGridnew(url,filterData,"yyGrid","pager",'',"yyGrid_doubleClickGrid","", "yyGrid_loadComplete");
			}
 
		}
  
	}else{
		alert("No Record to View");
		return false;
		
	}
 }/*else{
		alert("gsdg");
	}*/
	if(selId.substr(0,3) == 'MCH'){
	}	
}   
 
function getParamName(selId)
{
	if(selId.substring(0,3)=='CMP')
		return 'cmbCompid';
	else if(selId.substring(0,3)=='FCT')
		return 'cmbFactid';
	else if(selId.substring(0,3)=='LIN')
		return 'cmbSectid';
	else if(selId.substring(0,3)=='CEL')
		return 'cmbCellid';
	else if(selId.substring(0,3)=='MCH')
		return 'cmbMchid';
	else
		return null;
}
 
function checkzeroesMon(tableId,selId,colNo)
{ 
	 
	var Col = colNo+1;
	 
	while(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != null)
	{	
		 
		if(jQuery("#"+tableId).jqGrid('getCell',selId,Col) != '0')
			return true;
		Col++;
	}
	 
	return false;
		
}

/*function checkFor (tableId,rowid,colStart){
	 alert("asaa");
	var rowData = jQuery("#"+tableId).jqGrid("getRowData",rowid );
	var colModel = jQuery("#"+tableId).jqGrid("getGridParam","colModel" );
	alert("111");
	var Col = colModel ;
	alert("222");
	for(var j=colStart;j<Col.length;j++)	{
		alert("dff");
	 
			alert("33333");
		 if( rowData[ Col[j].name ] != null  && rowData[ Col[j].name ] != '0' && rowData[ Col[j].name ] != 0 ){
			 return true;
			 alert("444");
			 Col++;
		 
		}
		
		alert("dffqq");
	}
	return false;
}*/
function frmFilter_enableDisableSuccessCallBack()
{
	//fillWithCurrentDate("dtefromDate");			
	//enableFields('dteyear');
	/*jQuery("#chkDatewise").attr('checked',false);
	jQuery("#chkMonthwise").attr('checked',true);*/
	 enableDisableDatenMonthFilter();					
}

</script>
<form name="frmCompliance" id="frmCompliance" >
<div id="wrapperRpt">

<!--<div class="floatright" style="padding-right:20px;"><input type="button" id="btnBack" class="easyui-button" value="MonthWise"/></div>-->
<div class="clear"></div>
<div style="margin-top: -28px">
<label id="yynotes"     style="font-weight: bold; padding-left:5px; " > ${requestScope.drilldownMsg}</label>
<label id="yymonnotes"    style="font-weight: bold; padding-left:20px; " > ${requestScope.compgraph}</label>
<label id="yycellnotes"   style="font-weight: bold; padding-left:20px; " > ${requestScope.compcell}</label>
</div>
<table id="yyGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>
</form>
	