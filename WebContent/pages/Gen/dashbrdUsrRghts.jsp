<script>
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	initialiseForm('frmDBUserRights');	
  	jQuery('#submitForm').val('frmDBUserRights'); 
  	viewGrid(url,"?q=1");
	
});
function viewGrid(url,filterString)
{
	processGridnew(url,filterString,"DBUserRights","pager","DB User Rights","","","DBUserRightsloadcomplete","","");
	return true;
  }	
function DBUserRightsloadcomplete()
{
	
	//jQuery("tr.ui-jqgrid-labels>th").css('border','solid 1px #AFD6FE');
	jQuery("tr.jqgridheaderrow2>th").css('border-top','solid 1px #AFD6FE');
	jQuery("tr.jqgridheaderrow2>th#CH1-0").css('border-left','solid 1px #8DB2E3');
	
	//jQuery("tr.jqgridheaderrow1").css('border','solid 1px #AFD6FE');
	jQuery("tr.jqgridheaderrow1").hide();
	
	//if(jQuery("#sno").hasClass("snclass")){}
	//else
	//jQuery('.jqgridheaderrow1').prepend('<th id="sno" class="snclass" style="width:15px;"></th>');
	var allRows = jQuery("#DBUserRights").jqGrid('getRowData');
	var cm = jQuery("#DBUserRights").jqGrid("getGridParam", "colModel");
	var i=0;
	
	for(var i=0;i<=allRows.length;i++)
	{
		var rowid=allRows[i];
		for(var j=3;j<=cm.length-2;j+=2)
		{
			controlId="DBUserRightscheckbox_"+j+"_"+rowid;	
			colValue=jQuery('#' +controlId).val();
				var status=jQuery("#DBUserRightscheckbox_"+j+"_"+rowid).attr('checked');
				if(status=="checked")
				{
					jQuery("#DBUserRightscheckbox_"+j+"_"+rowid).attr('value','1');
				}
				else
				{
					jQuery("#DBUserRightscheckbox_"+j+"_"+rowid).attr('value','0');
				}
			
		}
	} 
	/*var length=(cm.length-3)/2;
	var newArray= new Array();
	var j=3;
	for(var i=0;i<=length;i++)
		{
		if(i==0)
			newArray[i]=0;
		else
			{
			newArray[i]=j;
			j+=2;
			}
		}*/
	

	
			  
}

function chkbox_DBUserRights(id, options, rowObject)
{ 
	var rowId = options.rowId;
	//alert(id+"  rowId:  "+rowId);
	var columnName = options.colModel.name;	
	var columnNo=columnName.substring(columnName.indexOf("_")+1);
	var columnVal =rowObject[columnName.substring(columnName.indexOf("_")+1)];
	//alert("clname  "+columnName+" num  "+columnNo+" val "+columnVal);
	var colKeyId="";
	var status=null;
	var columnKey="";	
	
if(columnVal!=null && columnVal!=undefined && columnVal!=""){
		
		if(columnVal==0 ||columnVal==1 )
		{
			colKeyId=columnName.substring(0,6,columnName.indexOf("_"));
			
				columnKey = colKeyId;
				if(columnVal==0)
					status="I";
				else if(columnVal==1)
					status="U";
				

		}
}	
	
		return '<input id="DBUserRightscheckbox_'+columnNo+'_'+rowId+'" status="'+status+'"  value="'+columnVal+'" keyId="'+columnKey+'" name="DBUserRightscheckbox" '+ (columnVal=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+columnNo + '\',\''+rowId+'\');}else{chkboxUnCheck(\''+ columnNo +'\',\''+rowId+'\')}"/>';
			
	
}

function frmDBUserRights_beforeSubmit()
{
	
	  var gridData = '&selectedDBUserRights='+convertDBUserRightsToJSONString();
	  
	  return gridData ; 
}

function chkboxCheck(rowId,columnNo)
{
	
	var position=parseInt(columnNo);
	var controlId="DBUserRightscheckbox_"+rowId+"_"+position;	
	var status=jQuery('#' + controlId).attr('status');
	var value=jQuery('#' + controlId).attr('value');
	if(status=="D" && value=="2"){
		//alert("status "+status+"  value"+value);
		jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('value','1');
		jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('status','U');
	}
	else{
		
		jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('value','1');
		
	}
}
function chkboxUnCheck(rowId,columnNo)
{
	var position=parseInt(columnNo);
	
	var controlId="DBUserRightscheckbox_"+rowId+"_"+position;	
	var status=jQuery('#' + controlId).attr('status');
	if(status=="U"){
		//alert(status+"----"+jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('value'));
		jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('value','2');
		jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('status','D');
		}
	else{
		//alert("else");
	jQuery("#DBUserRightscheckbox_"+rowId+"_"+position).attr('value','0');
	}
	
}
function convertDBUserRightsToJSONString()
{
	var DBUserId=jQuery("#DBUserRights").jqGrid('getDataIDs');	
	var cm=jQuery("#DBUserRights").jqGrid("getGridParam","colModel");
	var rowid="";
	var controlId="";
	var colValue="";
	var cKeyId="";
	var jsonArrO='[';
	for(i=0;i<DBUserId.length;i++)
	{
		 rowid=DBUserId[i];
		 var colkeyid = jQuery("#DBUserRights").jqGrid('getCell',DBUserId[i],"KEYFIELD_1");
		
		 //alert("keyid"+colkeyid);
		  for(j=3;j<cm.length;j++){
			
				controlId="DBUserRightscheckbox_"+j+"_"+rowid;	
				colValue=jQuery('#' +controlId).val();
				cKeyId=jQuery('#'+ controlId).attr('keyId');
				var status=jQuery('#' + controlId).attr('status');
				
				
				if((colValue!=null && colValue!="" && colValue!="0" || colValue=="2")){
					
					jsonArrO+= '{';
					jsonArrO+= '"chkDburRoleid":"' + cKeyId +'",';
					jsonArrO+= '"chkDburMenuid":"'+colkeyid+'",';
					jsonArrO+= '"chkDburstatus":"'+status+'"},';
				}
				
		} 
	}
jsonArrO = jsonArrO.slice(0,-1)+ "]";
	//alert("jsonarray"+jsonArrO);
	return jsonArrO; 	
} 
function frmDBUserRights_successsCallback(result){
	 jQuery("#DBUserRights").trigger("reloadGrid");
	 
}
</script>
<form name="frmDBUserRights" id="frmDBUserRights"> 
	<div id="wrapperRpt"> 
		<table id="DBUserRights"><tr><td></td></tr></table>
			<div id="pager"></div>
		</div>
		<input type="hidden" id="hdnmenuid" value="${requestScope.menuid }"/>
		<input type="hidden" id="modechk" />
		<input type="hidden" id="pos"/>
			<input type="hidden" id="mode" name="mode" value=""/>
			
</form>
