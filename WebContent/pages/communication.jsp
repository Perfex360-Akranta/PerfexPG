<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){	
	 processGridnew('commtxt_input.brdn','?q=2',"CommnGrid","CommnPager","","","","","");
});

jQuery("#btnInsert").click(function(){
var rids = jQuery('#CommnGrid').jqGrid('getDataIDs');
//alert(rids.length);
var nth_row_id = rids[rids.length-1];
//alert(nth_row_id);
var 
var newData = [{"txtWcmlKeyid":"{}", "txttWcmlWonumber": jQuery('#txtbreakdown').val(),}];



});
</script>

<div class="floatleft" style="padding-right: 20px;">&nbsp;</div>		      
<div class="floatleft">
   	<div><label>Communication</label></div>
    <div>
           <span class="easyui-paddingbfpx"></span>
           <span style="padding-right: 300px"><textarea style="width : 550px; height : 70px;resize:none;" name="txawcmlCommunicationtext" id="txawcmlCommunicationtext" cols="20" rows="3"></textarea></span>
           <span style="padding-right: 12px"><input type="button" value="Insert" id="btnInsert" class="easyui-button"  style="width:100px;height: 25px;"/></span>
           <span><input type="button" class="easyui-button"  id="btnSave" value="Save" style="width:100px;height: 25px;"/></span>
           <span><input type="button" class="easyui-button"  id="btnDel" value="Delete" style="width:100px;height: 25px;"/></span>
           	<input class="easyui-text" id="txtbreakdown" name="txtbreakdown"  value="${requestScope.breakdownId}" style="display:none;"  >
    </div>
 </div>
 <div class="clear"></div>
 <table id="CommnGrid" width="400px"></table> 
 <div id="CommnPager" style="float: left;"></div> 	