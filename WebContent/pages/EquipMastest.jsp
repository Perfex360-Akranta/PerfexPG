<script type="text/javascript" src="js/jquery.easyui.min.js"></script>



<script>

jQuery("#operatorInformation").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
	  ],
	  colNames:[ 'Employee Code','Employee Name','Actions'],
		colModel:[ {name:'Employee Code',index:'Employee Code',editable:false, width:150},
		           {name:'Employee Name',index:'Employee Name',editable:false, width:135},
				   {name:'Actions',index:'Actions',editable:false, width:150},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Operator Information',
	width:500,
	height:230,
	loadonce: true
});
	
jQuery("#operatorInformation1").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
	  ],
	  colNames:[ 'Skill Required','Actions'],
		colModel:[ {name:'Skill Required',index:'Skill Required',editable:false, width:200},		          
				   {name:'Actions',index:'Actions',editable:false, width:170},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager1', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Operator Information',
	width:450,
	height:230,
	loadonce: true
});
		
jQuery("#MaintenanceTeamInformation").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
	  ],
	  colNames:[ 'Maintaince Team Code','Maintaince Team Name','Actions'],
		colModel:[ {name:'Maintaince Team Code',index:'Maintaince Team Code',editable:false, width:150},
		           {name:'Maintaince Team Name',index:'Maintaince Team Name',editable:false, width:135},
				   {name:'Actions',index:'Actions',editable:false, width:150},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager2', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Maintaince Team Information',
	width:500,
	height:230,
	loadonce: true
});
	
jQuery("#MaintenanceTeamInformation1").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
	  ],
	  colNames:[ 'Skill Required','Actions'],
		colModel:[ {name:'Skill Required',index:'Skill Required',editable:false, width:190},		          
				   {name:'Actions',index:'Actions',editable:false, width:190},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager3', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Maintaince Team Information',
	width:450,
	height:230,
	loadonce: true
});
	
jQuery("#EquipmentParameter").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
	  ],
	  colNames:[ 'Parameter','Short Values','Long text'],
		colModel:[ {name:'Parameter',index:'Parameter',editable:false, width:300},		          
				   {name:'Short Values',index:'Short Values',editable:false, width:280},
				   {name:'Long text',index:'Long text',editable:false, width:250},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager4', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'Equipment Parameter',
	width:960,
	height:230,
	loadonce: true
});
	
jQuery("#SubEquipments").jqGrid({
	url:'',
	datatype: "local",
	data:[
		  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
		  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
		  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
		  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
		  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
		  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
		  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
		  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}		
	  ],
	  colNames:[ 'Select','Equipment No','Equipment Name'],
		colModel:[ {name:'Select',index:'Select',editable:false, width:300},		          
				   {name:'Equipment No',index:'Equipment No',editable:false, width:280},
				   {name:'Equipment Name',index:'Equipment Name',editable:false, width:250},
				     ],
	rowNum:50,
	rowList:[5,10,20],
	rownumbers: true,
	shrinkToFit:false,	
	pager: '#pager4', 
	sortname: 'id',
	viewrecords: true,
	sortorder: "asc", 
	caption:'SubEquipments',
	width:960,
	height:230,
	loadonce: true
});
		
	
		
		 
	jQuery('#cmbcostcen').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbcell').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
		 
	jQuery('#cmbEqpmain').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	  
	jQuery('#cmbsubsec').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbFact').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbsect').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbEquip').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	  
	jQuery('#cmbSubcatg').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	  
	jQuery('#cmbWorkCen').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbEqpRank').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbCircle').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbPurpose').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbCatg').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbPwrSupp').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbConnLoad').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	  
	jQuery('#cmbDbNo').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	 	 
	jQuery('#cmbSbNo').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
		 
	jQuery('#cmbManufacInfo').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbSuppInfo').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbMake').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbModel').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 
	jQuery('#cmbProvider').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	</script>
<div class="main-cntborder" style="height: 600px">
 

               
          <table width="100%"  border="0" align="center"  >  
          
          <tr >
          <td style="padding-left:30px;">   

               	<div ><label>Equipment</label></div>
               	<div class="easyui-paddingbfpx">
               	<input id="cmbEquip" name="cmbEquip" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div ><label class="mndlbl"> Cell</label></div>
	<div class="easyui-paddingbfpx"">
	<span><input id="cmbcell" name="cmbcell" class="easyui-combobox"  style="width:255px;" value=""  ></span>	</div>
	
	<div ><label> Sub Section</label></div>
          <div class="easyui-paddingbfpx"" >
          <input id="cmbsubsec" name="cmbsubsec" class="easyui-combobox"  style="width:255px;" value=""  >
          </div>
	
	</td>
	<td>
          <div ><label>	Factory</label></div>
          <div class="easyui-paddingbfpx"" >
          <input id="cmbFact" name="cmbfact" class="easyui-combobox"  style="width:255px;" value=""  >        
		</div>
		
		  <div > <label class="mndlbl">	 Cost Center</label></div>
        <div class="easyui-paddingbfpx"">
        <input id="cmbCostCen" name="cmbCostCen" class="easyui-combobox"  style="width:255px;" value=""  > </div>
         
         
         <div > <label class="mndlbl">Equipment No</label></div>
        <div class="easyui-paddingbfpx""><span>
        <input id="eqpNo" type="text" class="easyui-text" name="eqpNo" value="" style="width: 255px; height : 21px;" height="10px";></span></div>
          </td>
         <td>
	<div><label class="mndlbl"> Section</label></div>
	<div class="easyui-paddingbfpx"">
	<input id="cmbsect" name="cmbsect" class="easyui-combobox"  style="width:255px;" value=""  > 
		</div>
		
		<div ><label class="mndlbl">Eqp Main Group</label></div>
        <div class="easyui-paddingbfpx"">
        <input id="cmbEqpmain" name="cmbcell" class="easyui-combobox"  style="width:255px;" value=""  > 
		</div>
		<div  > <label class="mndlbl"> 	 Equipment Name</label></div>
           <div class="easyui-paddingbfpx"" >
           <input id="eqpName" type="text" class="easyui-text"  name="eqpName" value="" style="width: 255px; height : 21px;" ></div>
	</td>
	          
       </table>  
       <div style="clear: both;"></div>         
       <div class="easyui-paddingbfpx" style="padding-left:65%;margin-top:10px;">
       <input type="button" value="Image" class="easyui-button" style=" height : 21px;">
       <input class="easyui-button" type="button" value="File Manager" style=" height : 21px;">
       <input class="easyui-button" type="button" value="Control Panel" style=" height : 21px;"></div>
           <div class="clear"> </div>       
               
            
	
	
	<div style="width:1060px;height:250px;">
	
		
		<div title="Tab5 with sub tabs" closable="true" iconCls="icon-cut" style="padding-left:30px;">
			<div class="easyui-tabs" fit="true" plain="true" style="height:375px;width:280px;">
				<div title="Basic" style="padding:10px;">
				
				<div>
				<div class="sub-header">
				<label  >
				<b>Equipment Details</b></label></div>
				
				<table width="75%"   align="center" >
				<tr width="50%">
				<td>
				<div ><label> Asset No</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="AssetNo" type="text" class="easyui-text"  name="AssetNo" value="" style="width: 255px; height : 21px;" ;></div>
				</td>
				
				<td>
				<div><label> Sub Category</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbSubcatg" name="cmbSubcatg" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				</tr>
				
				<tr>
				<td>
				<div > <label>Work Center</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbWorkCen" name="cmbWorkCen" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				
				<td>
				<div class="easyui-paddingbfpx"" ><label>
				Installation Date</label><span style="padding-left:25px;"><label>Effective Date</label></span>
				</div>
				<div class="easyui-paddingbfpx"" >
				<input  class="easyui-datebox"  required="true"/>
				<span style="padding-left:10px;">
				<input id="Effective" class="easyui-datebox" ></input></span>
				</div>
				</td>
				</tr>
				
				
				<tr>
				<td>
				<div ><label>Equipment Rank</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbEqpRank" name="cmbEqpRank" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				
				<td>
				<div  class="easyui-paddingbfpx sub-header"  ><b><label class="">Aut.Maint. Step Details</label></b></div>
				</td>
				</tr>
				
				<tr>
				<td>
				<div ><label>Circle</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbCircle" name="cmbCircle" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				
				<td>
				<div class="easyui-paddingbfpx"" ><label>
				Aut.Maint.Step</label><span style="padding-left:40px;"> <label>Date</label></span>
				</div>
				<div class="easyui-paddingbfpx"" >
				<input id="Aut.Maint.Step" class="easyui-datebox" />
				<span style="padding-left:10px;">
				<input id="Date" class="easyui-datebox" ></input></span>
				</div>
				</td>
				</tr>
				
				
				<tr>
				<td>
				<div ><label>Purpose</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbPurpose" name="cmbPurpose" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				</tr>
				
				<tr>
				<td>
				<div ><label>Category</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbCatg" name="cmbCatg" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				
				</tr>				
				
				</table>
				
				</div>
				
				
				
				
				
				</div>
				<div title="Power Details" style="padding:10px;">
				<table width="75%"   align="center" >
				<tr>
				<td width=50%;>
				<div><label>Power Supply</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbPwrSupp" name="cmbPwrSupp" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				
				<div><label>Connected Load</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbConnLoad" name="cmbConnLoad" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				
				<div><label>DBNO</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbDbNo" name="cmbDbNo" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				
				<div><label>SBNO</label></div>
				<div class="easyui-paddingbfpx"" >
				<input id="cmbSbNo" name="cmbSbNo" class="easyui-combobox"  style="width:255px;" value=""  >
				</div>
				</td>
				
				<td width=50%>
				
				<div class="easyui-paddingbfpx"" ><label>
				Phase No</label><span style="padding-left:50px;"><label>Wires</label></span>
				</div>
				<div class="easyui-paddingbfpx"" >
				<input id="phase" type="text" class="easyui-text" size="10" name="phase" value=""  ; style=" height : 21px;">
				<span style="padding-left:40px;">
				<input id="Wires" type="text" class="easyui-text" size="10" name="Wires" value="" style=" height : 21px;">
				</span>
				</div>
				
				<div class="easyui-paddingbfpx"" ><label>Input volt</label><span style="padding-left:50px;"><label>min</label></span>
				<span style="padding-left:75px;">max</span>
				</div> 
                  <div class="easyui-paddingbfpx"" >
                    <input id="inputvolt" type="text" class="easyui-text" size="10" name="inputvolt" value="" style=" height : 21px;">
                    <span style="padding-left:40px;">
                    <input id="min" type="text" class="easyui-text" size="10" name="min" value="" style=" height : 21px;">
                    </span>
                    <span style="padding-left:30px;">
                    <input id="min" type="text" class="easyui-text" size="10" name="min" value="" style=" height : 21px;">
                    </span>
                  </div> 
                 
                 
                 <div class="easyui-paddingbfpx"" ><label>Input Freq</label><span style="padding-left:50px;"><label>min</label></span>
				<span style="padding-left:75px;">max</span>
				</div> 
                  <div class="easyui-paddingbfpx"" >
                    <input id="inputfreq" type="text" class="easyui-text" size="10" name="inputfreq" value="" style=" height : 21px;">
                    <span style="padding-left:40px;">
                    <input id="min1" type="text" class="easyui-text" size="10" name="min1" value="" style=" height : 21px;">
                    </span>
                    <span style="padding-left:30px;">
                    <input id="max1" type="text" class="easyui-text" size="10" name="max1" value="" style=" height : 21px;">
                    </span>
                  </div> 				
				</td>				
				</tr>				
				</table>				
				</div>
				
				
				
				<div title="Manufacture (Mfr)/Supplier/MC-info" style="padding:10px;">
				
				<table width="98%"  align="center"> 
           <tr width=50%> 
             <td colspan="" class="sub-header"> <b><label >Manufacture Information</label> </b></td> 
             <td colspan="" class="sub-header"> <b><label class="">Supplier Information</label></b> </td> 
             <td colspan="" class="sub-header"><b><label class="">Maintenance Contract(MC)Information</label></b> </td> 
          </tr> 
           <tr> 
            <td width="34%" valign="top"><div class="Maindiv"> 
                <div class="easyui-paddingbfpx"" > <label>Mfr.</label>
                  <div class="easyui-paddingbfpx" >
                    <div style="vertical-align:top; "> 
                     <input id="cmbManufacInfo" name="cmbManufacInfo" class="easyui-combobox"  style="width:255px;" value=""  > 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label> Make</label>
                  <div class="easyui-paddingbfpx"" > 
                    <div style="vertical-align:top; "> 
                      <input id="cmbMake" name="cmbMake" class="easyui-combobox"  style="width:255px;" value=""  > 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label> Model</label>
                  <div class="easyui-paddingbfpx"" >
                    <div style="vertical-align:top; "> 
                      <input id="cmbModel" name="cmbModel" class="easyui-combobox"  style="width:255px;" value=""  > 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" class="mndlbl"><label> Mfr.S.No</label>
                <span style="padding-left:85px;"><label>Mfr.Dt</label></span> 
                  <div class="easyui-paddingbfpx"" > 
                    <input id="mfrSNo" type="text" class="easyui-text" size="18" name="mfrSNo" value="" style=" height : 21px;"> 
                      <span style="padding-left:20px;">
                     
                    <input id="MfrDt" class="easyui-datebox" /> 
                    </span> </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label>Remarks</label>
                  <div class="easyui-paddingbfpx"" > 
                    <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" style="width:255px;resize:none;" cols="" id="manufactRemark" name="manufactRemark"></textarea> 
                  </div> 
                </div> 
              </div></td> 
            <td width="33%" valign="top"> 
            <div class="easyui-paddingbfpx"" ><label>Supplier</label>
                <div class="easyui-paddingbfpx"" >
                  <div style="vertical-align:top; "> 
                    <input id="cmbSuppInfo" name="cmbSuppInfo" class="easyui-combobox"  style="width:255px;" value=""  > 
                    </div> 
                </div> 
              </div> 
               <div class="easyui-paddingbfpx"" ><label>P.O.No</label>
               <span style="padding-left:90px;"><label>Date</label></span>
               <div class="easyui-paddingbfpx"" > 
                   <input id="PoNo" type="text" class="easyui-text" size="18" name="PoNo" value="" style=" height : 21px;"> 
                   <span style="padding-left:20px;">  
                   <input id="PoDate" class="easyui-datebox" required="true"/> </span>
                 </div> 
              </div> 
               <div class="easyui-paddingbfpx"" > PO Price<span style="padding-left:85px;"><label>Unit</label></span> 
                <div class="easyui-paddingbfpx"" > 
                   <input id="PoPrice" type="text" class="easyui-text" size="18" name="PoPrice" value="" style=" height : 21px;"> 
                   
                <span style="padding-left:20px;">  <input id="Unit" class="easyui-datebox" /> 
                   </span> </div> 
              </div> 
               <div class="easyui-paddingbfpx"" > 
                <div class="easyui-paddingbfpx"" ><span class="lbl"><label>Purchase Date</label>
                </span><span style="padding-left:45px;"><label>Warranty End Date</label></span></div> 
                <div style="padding-bottom: 3px;"> 
                   
                  <input id="PurchaseDate" class="easyui-datebox" /> 
                  <span style="padding-left:15px;"> 
                   
                  <input id="WarrantyDate" class="easyui-datebox" /> 
                  </span> </div> 
              </div>
               <div class="easyui-paddingbfpx"" ><label>Remarks</label>
                <div class="easyui-paddingbfpx"" >
                   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" style="width: 255px;resize:none;" cols="" id="SupplierRemarks" name="SupplierRemarks"></textarea> 
                 </div> 
              </div></td> 
            <td width="33%" valign="top"> <div class="Maindiv"> 
               <div class="easyui-paddingbfpx"" >
                  <div class="easyui-paddingbfpx"" ><label>Under MC</label></div> 
                  <div class="easyui-paddingbfpx"" > 
                    <input type="radio" value="" name="CK"> 
                   <label> Yes</label>
                    <input type="radio" value="" name="CK"> 
                   <label> No</label> </div> 
                </div> 
                <div class="easyui-paddingbfpx"" ><label>Provider</label>
                  <div class="easyui-paddingbfpx"" >
                    <div style="vertical-align:top; "> 
                      <input id="cmbProvider" name="cmbProvider" class="easyui-combobox"  style="width:255px;" value=""  > 
                      </div> 
                  </div> 
                </div> 
                <div class="easyui-paddingbfpx"" >
                  <div class="easyui-paddingbfpx"" ><label>Contract Date </label>
                  <span style="padding-left:50px;"> <label>Renewal Date</label></span>   </div> 
                  <div class="easyui-paddingbfpx"" >         
                                         
                    <input id="ContDate" class="easyui-datebox" />
                    <span style="padding-left:20px;"><input id="RenewalDate" class="easyui-datebox" /></span>
                  </div> 
                </div> 
               
                <div class="easyui-paddingbfpx"" >
                  <div class="easyui-paddingbfpx"" ><label>MC Remarks</label></div> 
                  <div class="easyui-paddingbfpx"" >
                    <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" style="width: 255px;resize:none;" cols="" name=""></textarea> 
                  </div> 
                </div> 
              </div></td> 
          </tr> 
         </table>
				
				</div>
				<div title="Operators & Skills" style="padding:10px;">
				<table width="75%">
				<tr>
				<td width="50%">

	<div class="demo-info">		
	</div>
	
	
	
	<table id="operatorInformation" style="width:100%">
	<tr><td/></tr></table>

			<div id="pager"></div>
	
	</td>	
	<td style="padding-left:20px;">
	<div class="demo-info">		
	</div>
	<table id="operatorInformation1" style="width:100%">
	<tr><td/></tr></table>

			<div id="pager1"></div>
	</td>	
	</tr>
			</table>	
				</div>
				
				<div title="Maintenance Team Info" style="padding:10px;">
				
				<table>
				<tr>
				<td >

	<div class="demo-info">		
	</div>
	<table id="MaintenanceTeamInformation" style="width:100%">
	<tr><td/></tr></table>

			<div id="pager2"></div>
	</td>	
	<td style="padding-left:20px;">
	<div class="demo-info">		
	</div>
	<table id="MaintenanceTeamInformation1" style="width:100%">
	<tr><td/></tr></table>

			<div id="pager3"></div>
	</td>	
	</tr>
			</table>
				
				</div>
				<div title="Equipment Parameters" style="padding:10px;">
				<table>
				<tr>
				<td style="padding-left:20px;">

	<div class="demo-info">		
	</div>
	<table id="EquipmentParameter" style="width:100%">
	<tr><td/></tr></table>
			<div id="pager2"></div>
	
	</td>
	</tr>
	</table>
				
				
				</div>
				<div title="Sub Equipments" style="padding:10px;">
				<table>
				<tr>
				<td>

	<div class="demo-info">		
	</div>
	
	<table id="SubEquipments" style="width:100%">
	<tr><td/></tr></table>

			<div id="pager2"></div>
	
	</td>
	</tr>
	</table>
				
				</div>
			</div>
		</div>
	</div>   

 </div>       
                     
	<div class="clear"></div>  
	
