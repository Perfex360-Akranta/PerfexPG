

<script>
jQuery(document).ready(function(){
	var type = jQuery("#hdntype").val();
	processGridnew("projectsprotomain_input.prpo","q=2","projectgrid","pager","","docDoubleClick","","loadProto_complete");
});

function loadProto_complete() {
	var row = jQuery("#projectgrid").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++){
		var rowId=	parseInt(i)+1;	
		var rowIds = row[i];
		var define = jQuery("#projectgrid").jqGrid('getCell',row[i],"DEFINE");
		var maic = jQuery("#projectgrid").jqGrid('getCell',row[i],"MAIC");
		jQuery("#projectgrid").jqGrid('setCell',row[i],"D",'',{'color':'black','background-color':'#FC3D50'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"M",'',{'color':'black','background-color':'#FC3D50'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"A",'',{'color':'black','background-color':'#FC3D50'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"I",'',{'color':'black','background-color':'#FC3D50'});
		jQuery("#projectgrid").jqGrid('setCell',row[i],"C",'',{'color':'black','background-color':'#FC3D50'});
		statusgridtracking(define,maic,row[i]);	
		//alert("rowId"+rowId);
		//alert("rowIds"+rowIds);
		/*for(var colName in rowIds) {
			//alert("colName"+colName);
			if(colName=="14" || colName=="10" || colName=="11" || colName=="12" ||colName=="13"){
				alert(colName);				
				var stageStatus=jQuery("#projectgrid").jqGrid('getCell',row[i], colName);	
				alert(stageStatus);		
				if (stageStatus=="C"){
					jQuery("#projectgrid").jqGrid('setCell',row[i],colName,'',{'color':'black','background-color':'#95FB94'});
				}
				else if(stageStatus=="W" || stageStatus=="I" || (stageStatus=="P")){
					jQuery("#projectgrid").jqGrid('setCell',row[i],colName,'',{'color':'black','background-color':'#FAF687'});
				}			
			}
		}*/
	}	
}
function statusgridtracking(define,maic,rowid){
	//alert('definedefine'+define);
	//alert("maic"+maic);
	if (define=="C"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#95FB94'});
	}
	else if(define=="P"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#FAF687'});
	}
	var mStatus=maic.substring(0,2);
	var aStatus=maic.substring(2,4);
	var iStatus=maic.substring(4,6);
	var cStatus=maic.substring(6,8);
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if(mStatus=="MC"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(mStatus=="MI" || mStatus=="MW" || mStatus=="MP"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FAF687'});
	}
	if(aStatus=="AC"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(aStatus=="AI" || aStatus=="AW" || aStatus=="AP"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FAF687'});
	}
	if(iStatus=="IC"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(iStatus=="II" || iStatus=="IW" || iStatus=="IP"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FAF687'});
	}
	if(cStatus=="CC"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(cStatus=="CI" || cStatus=="CW" || cStatus=="CP"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FAF687'});
	}
	/*if(define=="C"&&maic=="-"){
		jQuery("#projectgrid").jqGrid('setCell',rowid,"D",'',{'color':'black','background-color':'#95FB94'});
		
	}else if(define=="C"){
		
		if(maic=="MC"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			
		}else if(maic=="MI"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#FAF687'});
		}
		else if(maic=="MCAI"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#FAF687'});
			
		}else if(maic=="MCAC"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
		}
		else if(maic=="MCACII"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#FAF687'});
			
		}else if(maic=="MCACIC"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
		}
		else if(maic=="MCACICCI"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#FAF687'});
			
		}else if(maic=="MCACICCC"){
			jQuery("#projectgrid").jqGrid('setCell',rowid,"M",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"A",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"I",'',{'color':'black','background-color':'#95FB94'});
			jQuery("#projectgrid").jqGrid('setCell',rowid,"C",'',{'color':'black','background-color':'#95FB94'});
		}
		}*/
}

function chkBoxFormatter(cellvalue, options, rowObject) {	
	var rowId = options.rowId;
	var chkVal="";
	var bgClr ="#EFEFEF";
	//alert(cellvalue);
	if (cellvalue=='G') {
		chkVal='checked="checked"';
	}

	//<input type="checkbox" id="chkTrimming" name="chkTrimming" value="Y" >
	var formatStr  = '<input id="chk_'+rowId+'" '+chkVal+' ' ;
	formatStr  += ' type="checkbox" style="height: 22px;" > ';
	
	return formatStr;
}


function docDoubleClick(id)
{	
	var type = jQuery("#hdntype").val();
	navigateToNextForm("projectsprotoview_input.prpo?type="+type+"&keyid="+id );
	//navigateToNextForm("projectsprotoview_input.prpo?type=team"+"&keyid="+id );
}
</script>



<form id= "frmProjectMain">
<div id='wrapperRpt' style="width:85%">
<table id='projectgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
<input type="hidden" id="hdntype" value="${requestScope.type}"/>
</div>

 </form>

