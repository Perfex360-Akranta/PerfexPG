</script>
<form name="frmWorkorder" id="frmWorkorder" action="" method="post">
<div id="wrapper">
<input type="easyui-text" id="hdnEmpImgUrl" name="hdnEmpImgUrl" style="display:none;"/>

	<table align="center" width=100%>
		<tr>
			<td width="33.5">
				<div  style="float:left;padding-left:150px;" >
				    <div class="easyui-paddingbfpx"><label>Factory</label></div> 
                  	<div class="easyui-paddingbfpx" > 
                	<input id="cmbFactory" name="cmbFactory" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.factory}"  >
					</div>					
				    <div class="easyui-paddingbfpx"><label>Section</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                  	<input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.section}"  >
                   	</div>
                   	<div class="easyui-paddingbfpx"><label>Cost Center</label></div> 
                  	<div class="easyui-paddingbfpx" > 
                	<input id="cmbCostcenter" name="cmbCostcenter" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.costcenter}"  >
					</div>					
				    <div class="easyui-paddingbfpx"><label>Line</label></div> 
                  	<div class="easyui-paddingbfpx"> 
                  	<input id="cmbLine" name="cmbLine" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.line}"  >
                   	</div>
                  </div> 
                   
                   <div  style=" margin-left:70px;float:left; width : 300px;" >
	                   
	                  	<div class="easyui-paddingbfpx" style=" width : 255px;"> 
	                	<div class="easyui-paddingbfpx"><label>Equipment</label></div> 
	                  	<div class="easyui-paddingbfpx" > 
	                	<input id="cmbEquipment" name="cmbEquipment" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.equipment}"  >
						</div>					
					    <div class="easyui-paddingbfpx"><label>Assembly</label></div> 
	                  	<div class="easyui-paddingbfpx"> 
	                  	<input id="cmbAssembly" name="cmbAssembly" class="easyui-combobox"  style="width:255px;" value="${requestScope.genTlEmployeemst.assembly}"  >
	                   	</div>
                		<div class="easyui-paddingbfpx"><label>Description</label></div>
	          			<div class="easyui-paddingbfpx"><textarea id="txtDescription" name="txtDescription" cols="30" rows="3" maxlength="95" ></textarea></div>
	          		   	</div>
				 </div>
                  
                 <div style="float: left; padding-left: 50px">
                 <div class="easyui-paddingbfpx"><label class="mandatory-lbl">Plan Duration</label></div>
	          		<div class="easyui-paddingbfpx"><input id="txtAssmName" name="txtAssmName"   type="text" class="easyui-text"  maxlength="49" style="width: 60px;" value="${requestScope.genTlAssemblymst.assmName}" /></div>
					<div class="easyui-paddingbfpx" style="padding-top: 20%" ><label></label></div>
					<input type="button" class="easyui-button" id="btnok" name="imgEmpDel"style="width:80px;" value="Ok"/>
					</div>
				
			</td>
		</tr>
	</table>
	
		<div  id="tabActivity" class="easyui-tabs"  style="height:440px;width:1080px;padding-left:25px; ">
				<!--First tab Start-->
				<div title="Activity Details" style="padding:10px;">
				<div id="wrapper">
				<div>
				<div class="sub-header">
				<label  >
				<b>Selected Activity Details</b></label></div>
				
				<table width="75%"   align="center" >
				<tr width="50%">
				<td>
				<div ><label> Allotted to</label></div>
				<div class="easyui-paddingbfpx" >
				<input id="txtMchmControltype" type="text" class="easyui-text"  name="txtMchmControltype"  style="width: 255px; height : 21px;" ; value="${requestScope.genTlMachinemst.mchmControltype}" /></div>
					
				</td>
				
						<td valign="top">
				
				<div ><label></label></div>
				<div class="easyui-paddingbfpx"" >
				<input type="button" class="easyui-button" id="btnnew" name="newactive"style="width:120px;" value="New Activity"/>
				<input type="button" class="easyui-button" id="btnallotted" name="allotted"style="width:150px;" value="Allot Work Order"/>
				</div>
				</td>
				</tr>
				</table>
			</div>
		</div>
	</div>
</div>
</form>
