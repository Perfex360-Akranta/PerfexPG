<script type="text/javascript">  
jQuery.noConflict();
jQuery(document).ready(function(){
	jQuery('#submitForm').val('frmMenuRights'); 
	initialiseForm("frmMenuRights");	
	fillComboBox("frmMenuRights","cmbRoleKeyid","employeeRole.commonFilter?");
	var openFrom = jQuery('#hdnOpenFrom').val();	
	LoadForm("divDashboardRights","","empkpiIndicatorList_view.base?EmpGrid=Y","onsuccess","onsuccess","");
	jQuery("#divDashboardRights").show();
	if(openFrom != null && openFrom != '' && openFrom != ' ')
	{
		jQuery('.roleMenuRightsTreeLayer').css('height','360px;margin-top:-100px');
	}
});
jQuery(function () {	
	processMenuRightsTree();		    
});

jQuery("#btnnewView").click(function(){
    navigateToNextForm("menu_Viewsdata.menuTree");
	});

function onsuccess(result){
	jQuery("#divDashboardRights").val(result);
}
jQuery("#btnSaveMenuRoleRights").click(function(){
	var selMenus = jQuery("#hdnSelectedMenus").val();	
	var role = jQuery("#cmbRoleKeyid").combobox('getValue');
	var ds = '?q=2';
	if(role != null && role != '' && role != ' ')
	{
		ds += '&role='+role;
	}
	else
	{ 
		alert('Select Role');
		return false; 
	}
	/*if(selMenus != null && selMenus != '' && selMenus != ' ')
	{
		ds += '&selectedMenus='+selMenus;
	}
	else
	{  */
	var selMenu = null;
	jQuery('#menuRightsTree').find('li').each(function(){				 
	if( jQuery(this).hasClass('jstree-checked') || jQuery(this).hasClass('jstree-undetermined'))
	{
		if( !jQuery(this).hasClass('jstree-unchecked') )
		{
			if(selMenu != null && selMenu != '' && selMenu != ' ')
			{
				selMenu = selMenu+","+this.id;		
			}
			else
			{
				selMenu = this.id;
			}
		}
	}
	});
	if(selMenu != null && selMenu != '' && selMenu != ' ')
	{
		ds += '&selectedMenus='+selMenu;
	}
	else
	{
		alert('Select Menu');
		return false; 
	}
//	}
	
	saveForm('frmMenuRights','save_rolerights.menuTree'+ds);
});
jQuery("#rightsTreeRef").click(function(){
	//jQuery("#menuRightsTree").jstree("refresh");
	processMenuRightsTree();	
});
jQuery("#rightsTreeExpand").click(function(){	
	jQuery("#menuRightsTree").jstree("open_all", -1);	
	
});
jQuery("#rightsTreeCollapse").click(function(){
	jQuery("#menuRightsTree").jstree("close_all", -1);	
});
function getselected_value(){
	var selMenu = null;
	jQuery("#menuRightsTree").find(".jstree-checked").each(function(i, element){   
		var selectedType = jQuery(element).attr("id");
		if(selMenu == null)
			selMenu = selectedType;
		else
			selMenu = selMenu+","+selectedType;		
	});	
	jQuery("#menuRightsTree").find(".jstree-unchecked").each(function(i, element){   
		var selectedType = jQuery(element).attr("id");
		if(selMenu == null)
			selMenu = selectedType+":DEL";
		else
			selMenu = selMenu+","+selectedType+":DEL";
	
	});
	//alert(selMenu);
jQuery("#hdnSelectedMenus").val(selMenu);
//jQuery("#menuRightsTree").jstree("save_opened");
}
function frmMenuRights_successsCallback(result)
{
	//jQuery("#menuRightsTree").jstree("refresh");
	processMenuRightsTree();
}
function processMenuRightsTree()
{
	
	
	var url="load_menurights.menuTree";
	var dataStr = '?q=2';
	var role = jQuery("#cmbRoleKeyid").combobox('getValue');	
	if(role != null && role != '' && role != ' ')
		dataStr += '&role='+role;

	jQuery("#menuRightsTree").jstree({
		
	      "json_data" : {
                            "ajax" : {
                                "url" : url+dataStr,
                                "data" : function (n) {
                                	//alert(Object.keys(n));
                                	return {
                                    	
                                		"operation" : "get_children", 
                                        "id" : n.attr ? n.attr("id") : 0
                                        
                                	};
	                    }
			   	},
			   "progressive_render" : true
	            },
	            "themes" : {
                            "theme" : "classic",
                            "dots" : true,
                            "icons" : false
                           },                
               "plugins" : [ "themes", "json_data", "checkbox","ui","hotkeys" ]
        });
		jQuery("#menuRightsTree").bind("check_node.jstree", function (e, data) {	
		   jQuery("#menuRightsTree").jstree("open_all",jQuery('#'+data.rslt.obj.attr("id")));
		 //  jQuery('#hdnOpenAll').val('');
		   setTimeout(function() {getselected_value();},250);
		   
				/*if(data.rslt.obj.attr("elementtype")=="C" ||data.rslt.obj.attr("elementtype")=="R")
					{
						alert("Cannot select Parent");
					}
				else {
					var isMultiSelect = jQuery("#hdnIsMultiselect").val();
					if(isMultiSelect=="false")*/
					// getselected_value();
					//}
		 });
	jQuery("#menuRightsTree").bind("uncheck_node.jstree", function (e, data) {
			
		   setTimeout(function() {getselected_value();},250);
	});

	jQuery("#menuRightsTree").bind("loaded.jstree", function (event, data) {	
	
		jQuery('#menuRightsTree').find('li').each(function(){	
				/*
				if(jQuery('#'+this.id).attr('elementType') != 'assigned')
				{
					
					jQuery('#'+this.id).addClass('jstree-checked');
					jQuery("#menuRightsTree").jstree("open_node",jQuery('#'+this.id));
				}*/
				
				 if( jQuery(this).hasClass('jstree-checked'))
				 {
					 jQuery("#flTreeSearch").css('display','block');	
					//jQuery("#menuRightsTree").jstree("open_all",jQuery('#'+this.id));
					jQuery("#menuRightsTree").jstree("open_node",jQuery('#'+this.id));
				 }			 
		 });
		
		// jQuery("#flTreeSearch").css('display','none');
			 //alert(jQuery('#'+data.rslt.obj.attr("id").hasClass('jstree-checked')));
			 //	if(data.rslt.obj.attr("elementtype")=="C" )	
			 //jQuery("#menuRightsTree").jstree("open_all", -1);
		 });
	jQuery("#menuRightsTree").bind("open_node.jstree", function (event, data) {
			
		jQuery("#flTreeSearch").css('display','block');	
		var assignFlag = jQuery('#'+data.rslt.obj.attr("id")).attr('elementType');
		if(assignFlag != null && assignFlag != '' && assignFlag != ' ' && assignFlag != 'undefined')
		{
			jQuery('#'+data.rslt.obj.attr("id")).find('li').each(function(){
				//if(jQuery('#'+this.id).attr('elementType') != 'assigned')
					//jQuery('#'+this.id).addClass('jstree-unchecked');
					
				if( jQuery(this).hasClass('jstree-checked'))
				{
					if(jQuery('#'+this.id).attr('elementType') == 'assigned')
					{
						jQuery("#flTreeSearch").css('display','block');	
						jQuery("#menuRightsTree").jstree("open_node",jQuery('#'+this.id));
					}
					else
						jQuery('#'+this.id).addClass('jstree-unchecked');
				}
					
			});
		}
		
		jQuery("#flTreeSearch").css('display','none');	
	});

	
	
}

function frmMenuRightscmbRoleKeyid_onSelect(record)
{
	processMenuRightsTree();
}
</script>
<form name="frmMenuRights" id="frmMenuRights">

<div style="padding-top:-20px;margin-left:5px;">

	<div>
		<label class="mandatory-lbl">Role</label>                       
	</div> 
	<div class="easyui-paddingbfpx"> 
		<input id="cmbRoleKeyid" name="cmbRoleKeyid" class="easyui-combo" style="width:260px;" maxlength="20" value="${requestScope.roleKey}" />	
		<!-- <span style="margin-left:10px;;/* margin-left:30\9; */">
		<a href='#' title='Refresh Menu' tabindex="0" id="rightsTreeRef" class='tre-refresh-menu' style="text-decoration:none;">
			<img src='images/refresh_menu.jpg'  /> Refresh
		</a>
	   <a href='#' title='Open All Nodes' tabindex="0"  id="rightsTreeExpand" class='tre-expand-all' style="text-decoration:none;"> 
	   		<img src='images/expand_menu.jpg'  /> Expand All
	   </a> 
	   <a href='#' title='Close All Nodes' tabindex="0" id="rightsTreeCollapse" class='tre-collapse-all' style="text-decoration:none;">
	   		<img src='images/collapse_menu.jpg'  /> Collapse All	
	   	</a> 
		</span>	 -->       
		<span style="/* margin-left:3%; *//* margin-left:2%\9; */">
			<input type="button" class ="easyui-button" value="Save" id="btnSaveMenuRoleRights"/>
		</span>
		<span style="/* margin-left:3%; */margin-left:2%\9;">
			<input type="button" class ="easyui-button" style="width:100px;height:25px;" value="View Rights" id="btnnewView"/>
		</span>                
	</div>
	<table>
		<tr style="vertical-align: top;">
			<td><span style="margin-left:10px;;/* margin-left:30\9; */">
		<a href='#' title='Refresh Menu' tabindex="0" id="rightsTreeRef" class='tre-refresh-menu' style="text-decoration:none;">
			<img src='images/refresh_menu.jpg'  /> Refresh
		</a>
	   <a href='#' title='Open All Nodes' tabindex="0"  id="rightsTreeExpand" class='tre-expand-all' style="text-decoration:none;"> 
	   		<img src='images/expand_menu.jpg'  /> Expand All
	   </a> 
	   <a href='#' title='Close All Nodes' tabindex="0" id="rightsTreeCollapse" class='tre-collapse-all' style="text-decoration:none;">
	   		<img src='images/collapse_menu.jpg'  /> Collapse All	
	   	</a> 
		</span>	
				<div   class="sub-cntborder roleMenuRightsTreeLayer" style="height: 420px;width:400px;">
					<div id="treeMsg" style="width: 50%;display:none;" align="center"></div>
					<div id="flTreeSearch" style="right:0;position:fixed;display:none;width:70%;">
					<img id="treeSearchLoading" src="images/332.gif"/></div>
					<div id="menuRightsTree" class="demo" style="width: 50%;"></div>
				</div>
			</td>
			<td >
				<div style="margin-left:10px;margin-top:-30px;" class="sub-header">Dashboard Rights</div>
				<div id="divDashboardRights" style="margin-top:-20px"></div>
			</td>
		</tr>
	</table>


</div>
	<input type="hidden" id="hdnSelectedMenus" name="hdnSelectedMenus"/>
	<input type="hidden" id="hdnOpenFrom" name="hdnOpenFrom" value="${requestScope.openFrom}"/>
	<input type="hidden" id="hdncheckFlag" name="hdncheckFlag"/>


</form>