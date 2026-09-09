<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){	

	initialiseForm('frmFindNode');		
	fillComboBox("frmFindNode","findTreeNode","companyCombo.commonFilter" );

	jQuery( "#legendButton" ).click(function() {
		jQuery("#lgnd-panel").slideToggle(200);
	});
	
	jQuery(document).keydown(function(e) {
	    if (e.keyCode == 27) {
	    	jQuery("#lgnd-panel").hide(0);
	    }    
	    jQuery('#lgnd-panel').focusout(function() { 
	 });
	});
	
	jQuery("#findNode").combobox({
		onSelect:function(recordid){
		 jQuery("#cmbfindTreeNode").combobox('clear');
		 reloadCombo("frmFindNode","cmbfindTreeNode",getComboUrl(recordid.text));
		}
	});
jQuery( "#btnfindNext" ).click(function() {			
		
		var searchNode = getSearchString(jQuery("#findNode").combobox('getText'),jQuery('#cmbfindTreeNode').combobox('getText'),jQuery('#cmbfindTreeNode').combobox('getValue'));
		
		if(searchNode != null && searchNode != ' ' && searchNode != '')
		{
			jQuery("#flTreeSearch").css('display','block');
			jQuery("#flTreeComponent").jstree("search",searchNode);		
		}
		else
		{
			alert('Select Filter To Search');
		}
	});
	

	 jQuery(function () {
		 	processTree( jQuery("#flTreeComponent"),'loadval.funlocn','searchnode.funlocn');
			jQuery("#flTreeComponent") .bind("select_node.jstree", function (e, data) {
				
					processAjaxCalls("get_image.funlocn","?q=2&nodeId="+data.rslt.obj.attr("id"),"getImgSuccess","getImgErr");					
			 		//jQuery('#elemIdToPasteAsm').val(data.rslt.obj.attr("elementType")+':'+data.rslt.obj.attr("elementId"));
		     });
			jQuery("#flTreeComponent").bind("search.jstree", function (e, data) {
				          //  alert("Found " + data.rslt.nodes.length + " nodes matching '" + data.rslt.str + "'.");
			});
	 });

});
function loadval_searchCallBack(result){
	 jQuery("#flTreeSearch").css('display','none');
	 jQuery('.search_parent_flu-s').removeClass('search_parent_flu-s');

    if(result != null){
  	  
		  var str = result.toString(); 	
		  var parentIds ="";
        if( str.indexOf(",") > 0)
        {
      	  parentIds = str.substring(str.indexOf(",")+1 , str.lastIndexOf(",") < 0 ?str.length:str.lastIndexOf(",")+1);
            parentIds = parentIds.replace(/#/g," > ul > li ").replace(/[0-9,A-Z]/g,' ').replace(/,/g,' ');
         }
        str = str.substring( str.lastIndexOf(",") > 0 ? str.lastIndexOf(",")+1:0);
        parentIds += str.replace(/#/g," > ul > li[id=");
        parentIds += ']';
      //  alert(parentIds);
        
        jQuery("#flTreeComponent " + parentIds).addClass("search_parent_flu-s");
        
    }    
}


		function customMenu(node) {	
			  processAjaxCalls("get_image.funlocn","?q=2&nodeId="+jQuery.jstree._focused()._get_node(node).attr("id"),"getImgSuccess","getImgErr");	
		}
		
		function getComboUrl(cmbTxt)
		{
			var cmbUrl = null;
			if(cmbTxt == 'Company')
				cmbUrl = "companyCombo.commonFilter";
			if(cmbTxt == 'Location')
				cmbUrl = "location.funlocn";
			if(cmbTxt == 'Unit')
				cmbUrl = "factroyCombo.commonFilter";
			if(cmbTxt == 'Section')
				cmbUrl = "sectionCombo.commonFilter";
			if(cmbTxt == 'Line')
				cmbUrl = "cellCombo.commonFilter";
			if(cmbTxt == 'Equipment')
				cmbUrl = "machineCombo.commonFilter";
			if(cmbTxt == 'Assembly')
				cmbUrl = "assembly.funlocn";	
			if(cmbTxt == 'Sub Assembly1')
				cmbUrl = "subassemblyCombo.funlocn";
			if(cmbTxt == 'Sub Assembly2')
				cmbUrl = "subassemblyCombo.funlocn";
			if(cmbTxt == 'Spare')
				cmbUrl = "spareCombo.funlocn";
			if(cmbTxt == 'Instrument')
				cmbUrl = "machineCombo.commonFilter";
			if(cmbTxt == 'Sub Cell')
				cmbUrl = "subcell.funlocn";
			//alert(cmbUrl);
			return cmbUrl;	

		}

		function getSearchString(cmbTxt,dispField,cmbId)
		{
			
			var nodearr = new Array();
			nodeArr = dispField.split('-');	
			
			var toSearch = dispField.replace('-'+nodeArr[nodeArr.length-1],'');
			var mchToSearch = null;
			
		
			var cmbUrl = null;
		
			if(cmbTxt == 'Location')
				cmbUrl = nodeArr[1].trim();
			else if(cmbTxt == 'Equipment')
			{
		
				cmbUrl = dispField.trim();
			}
			else if(cmbTxt == 'Section' || cmbTxt == 'Line')
			{
				cmbUrl = nodeArr[0].trim();
			}
			else if(cmbTxt == 'Spare')
			{
			  cmbUrl = dispField.trim();	
			}
			else
				cmbUrl =toSearch.trim();
						//alert(cmbUrl);
			return cmbUrl;	

		}
		function getImgSuccess(result)
		{
			
			jQuery('#nodeImage').attr('src','');
			jQuery('#nodeImage').attr('src',result.nodeImg.imgToimBlobimage);	
			jQuery('#hdnBlobimage').val(result.nodeImg.imgToimBlobimage);
			if(screen.width <= 1024)
				setImgWidth( jQuery('#nodeImage'),400,416);
			else		
				setImgWidth( jQuery('#nodeImage'),480,416);
		}

	 function refreshTree()
	 {
	 	var tree = jQuery.jstree._reference("#flTreeComponent");
	 	var currentNode = tree._get_node(null, false);
	 	var parentNode = tree._get_parent(currentNode);
	 	tree.refresh(parentNode);
	 }

</script>

  <div class="searchLayerFuncLocn" >
	<form id="frmFindNode" name="frmFindNode">
	
		   <label>Find</label>
		   <select  id="findNode" class="easyui-combobox" name="findNode" style="width:120px;">
			   <option value="cmp">Company</option>
			   <option value="lcn">Location</option>
			   <option value="unt">Unit</option>
			   <option value="subunt">Section</option>
			   <option value="sect">Line</option>
			   <option value="eqp">Equipment</option>
			   <option value="assm">Assembly</option>
			   <option value="spr">Spare</option>
		   </select> 
		   <input id="cmbfindTreeNode" clear="false" name="cmbfindTreeNode" class="easyui-combobox" style="width:200px;"/>  
		   <input type="button" class="easyui-button" id="btnfindNext" value="Find Next"/>
		   <input type="button" class="easyui-button" id="btnfindPnlClear" value="Clear"/>
<!--		   <input type="button" class="easyui-button"  id="btnFrmView" value="View"/>			 -->
<!--		   <input type="button" class="easyui-button" id="btnFrmEdit" value="Edit"/>	-->
   
			<input type="button" class="easyui-button" style="display:none" id="btnPstAsm" value="Paste"/>    
			<input type="button" class="easyui-button" id="legendButton" value="Legend" style="float:right;"/> 
	</form>
</div>
<div style="margin-left:1%">
	<div id="flTreeLayer" style="float:left;width:50%;height:91%;overflow:auto;background-color:#ffffff;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;" class="sub-cntborder">
		<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
		<div id="flTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>
	</div>
	<span>
	<div id="DocMgrFileLayer" style="width:50%;height:91%;margin-left:51%;overflow:auto;">
	  	<img id="nodeImage">
	</div>
	</span>
</div>
	 <div id="lgnd-panel">
		<ul>
			<li><a href="#"><img src="images/FnLocn/company.jpg"/><span>Company</span></a></li>
			<li><a href="#"><img src="images/FnLocn/location.jpg"/><span>Location</span></a></li>
			<li><a href="#"><img src="images/FnLocn/factory.jpg"/><span>Unit</span></a></li>
			<li><a href="#"><img src="images/FnLocn/unit.jpg"/><span>Section</span></a></li>
			<li><a href="#"><img src="images/FnLocn/section.jpg"/><span>Line</span></a></li>
			<li><a href="#"><img src="images/FnLocn/machine.jpg"/><span>Equipment</span></a></li>
			<li><a href="#"><img src="images/FnLocn/assembly.jpg"/><span>Assembly</span></a></li>
			<li><a href="#"><img src="images/FnLocn/spare.png"/><span>Spare</span></a></li>
		</ul>
  	 </div>
  		   
<input type="text" style="display:none;" id="hdnBlobimage" name="hdnBlobimage"/>
	