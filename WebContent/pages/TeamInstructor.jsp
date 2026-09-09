<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){	

	initialiseForm('frmTeaminstructer');	

	processGridnew("Teaminstructergrid_input.tmins","?q=2","pillergrid","pager","pillergrid","db","","load","","");
	jQuery('#divPillerGrid').hide();			
	fillComboBox("frmTeaminstructer","cmbSubheader","employee.commonFilter" );
	fillComboBox("frmTeaminstructer","cmbheader","employee.commonFilter" );
	
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
		 reloadCombo("frmFindfnNode","cmbfindTreeNode",getComboUrl(recordid.text));
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
	
function load() {
	//alert(1);
}
	 jQuery(function () {
		 	processTree( jQuery("#flTreeComponent"),'loadval.tmins','searchnode.tmins');
			jQuery("#flTreeComponent") .bind("select_node.jstree", function (e, data) {
				//alert(data.rslt.obj.attr("id").substring(0,3));
				processGridnew("Teaminstructergrid_input.tmins","?q=2","pillergrid","pager","pillergrid","db","","load","","");
				if (data.rslt.obj.attr("id").substring(0,3)=="SBU")
					jQuery('#divPillerGrid').show();					
				else
					jQuery('#divPillerGrid').hide();
				//alert(data.rslt.obj.attr("id"));
					processAjaxCalls("get_image.tmins","?q=2&nodeId="+data.rslt.obj.attr("id"),"getImgSuccess","getImgErr");					
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
			  processAjaxCalls("get_image.tmins","?q=2&nodeId="+jQuery.jstree._focused()._get_node(node).attr("id"),"getImgSuccess","getImgErr");	
		}
		
		function getComboUrl(cmbTxt)
		{
			var cmbUrl = null;
			if(cmbTxt == 'Company')
				cmbUrl = "companyCombo.commonFilter";
			if(cmbTxt == 'Location')
				cmbUrl = "location.tmins";
			if(cmbTxt == 'Unit')
				cmbUrl = "factroyCombo.commonFilter";
			if(cmbTxt == 'Section')
				cmbUrl = "sectionCombo.commonFilter";
			if(cmbTxt == 'Line')
				cmbUrl = "cellCombo.commonFilter";
			if(cmbTxt == 'Equipment')
				cmbUrl = "machineCombo.commonFilter";
			if(cmbTxt == 'Assembly')
				cmbUrl = "assembly.tmins";	
			if(cmbTxt == 'Sub Assembly1')
				cmbUrl = "subassemblyCombo.tmins";
			if(cmbTxt == 'Sub Assembly2')
				cmbUrl = "subassemblyCombo.tmins";
			if(cmbTxt == 'Spare')
				cmbUrl = "spareCombo.tmins";
			if(cmbTxt == 'Instrument')
				cmbUrl = "machineCombo.commonFilter";
			if(cmbTxt == 'Sub Cell')
				cmbUrl = "subcell.tmins";
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
<div style="margin-left:1%">
	<div id="flTreeLayer" style="float:left;width:35%;height:91%;overflow:auto;background-color:#ffffff;border:1px solid #b7ceec;box-shadow: 0px 0px 4px #B6BABF;" class="sub-cntborder">
		<div id="treeMsg" style="width: 50%;display:none" align="center"></div>
		<div id="flTreeSearch" style="right:0;position:fixed;display:none;width:70%;"><img id="treeSearchLoading" src="images/FnLocn/searching.gif"/></div>
		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>
	</div>
	
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
<form name="frmTeaminstructer" id="frmTeaminstructer">
<div style="padding-left: 40%;">	
<div class="easyui-paddingbfpx" >
<label class="mandatory-lbl"> Head</label></div> 
<div class="easyui-paddingbfpx"> 
<input id="cmbSubheader" name="cmbSubheader" class="easyui-combobox"  style="width:300px;" value="EMP03362"  >
</div>	
<div class="easyui-paddingbfpx" >
<label class="mandatory-lbl">Sub Head</label></div> 
<div class="easyui-paddingbfpx"> 
<input id="cmbheader" name="cmbheader" class="easyui-combobox"  style="width:300px;" value="EMP03370"  >
</div>
<div id="divPillerGrid">
<table id='pillergrid'>			
</table>
<div id='pager'>
	</div>
</div>							
</div>
</form>
	