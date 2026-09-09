<script type="text/javascript">

jQuery(document).ready(function(){

	processGridnew("KaizenBank_input.kznbnk","q=2","kaizenbankgrid","kaizenbankpager","","Kaizendoubleclick","","");
	 jQuery("#btnnew").click(function(){
		 navigateToNextForm("Kaizen_input.kznbnk");
	       		
	 		});
	
});
function Kaizendoubleclick(id){
	var rowData = jQuery("#kaizenbankgrid").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	 navigateToNextForm("Kaizen_input.kznbnk?keyid="+keyid);
}
function KaizenBank(rowid,rowObjectVal)			
{
	navigateToNextForm("kaizen_input.kaizen","Kaizen Idea Sheet");
}	


	 

function txtformatter(id, options, rowObject)
{	

	var rowId = options.rowId;
	var colId = options.pos;	
	//alert(colId);
	if(rowObject[6]=="Approved"){
		return '<input type="button" id="btnActionplan_'+rowId+'_'+colId+'"  name="btnActionplanGrid_'+rowId+'_'+colId+'" onclick="KaizenBank('+rowId+')"  style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
	}else{
		return '<input type="button" id="btnActionplan_'+rowId+'_'+colId+'" disabled="disabled" name="btnActionplanGrid_'+rowId+'_'+colId+'" onclick="KaizenBank('+rowId+')"  style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
		}
    }

</script>


<form action=" " method="post" id="frmkaizenbank">
<div style="margin-top:20px;margin-left:40px;">
<input type="button" class="easyui-button" id = "btnnew"  value = "New" />
</div>
<div style="margin-left:40px;">
		<table id='kaizenbankgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='kaizenbankpager'></div>
</div>

</form>