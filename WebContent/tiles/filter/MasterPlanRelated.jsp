<script  type="text/javascript">
		jQuery(document).ready(function(){	

		initialiseForm('frmMasterPlanRelated');
		formatDateBox('dtestart','dd-MMM-yyyy');
		formatDateBox('dteend','dd-MMM-yyyy');
		formatDateBox('dteActualStart','dd-MMM-yyyy');
		formatDateBox('dteEnd','dd-MMM-yyyy');
		
		 
			fillComboBox("frmMasterPlanRelated","cmbCategory ","categoryCmb.conf");
			fillComboBox("frmMasterPlanRelated","cmbSubCategory ","subCategoryCmb.conf");
			fillComboBox("frmMasterPlanRelated","cmbActivity ","Activity.conf");
			fillComboBox("frmMasterPlanRelated","cmbResponsibility ","employee.commonFilter");
			fillComboBox("frmMasterPlanRelated","cmbAssignedto ","employee.commonFilter");
			fillComboBox("frmMasterPlanRelated","cmbStatus ","Status.conf");
			
			
		});	
		function getRelatedFilterValues()
		{
			var filterStr='';
			var cmbActivity = jQuery("#cmbActivity").combobox("getValue");
			filterStr += "&cmbActivity="+cmbActivity;
			var cmbCategory = jQuery("#cmbCategory").combobox("getValue");
			filterStr += "&cmbCategory="+cmbCategory;
			var cmbSubCategory = jQuery("#cmbSubCategory").combobox("getValue");
			filterStr += "&cmbSubCategory="+cmbSubCategory;
			var cmbResponsibility = jQuery("#cmbResponsibility").combobox("getValue");
			filterStr += "&cmbResponsibility="+cmbResponsibility;
			var cmbAssignedto = jQuery("#cmbAssignedto").combobox("getValue");
			filterStr += "&cmbAssignedto="+cmbAssignedto;
			var cmbStatus = jQuery("#cmbStatus").combobox("getValue");
			filterStr += "&cmbStatus="+cmbStatus;
			var dtestart = jQuery("#dtestart").datebox("getValue");
			filterStr += "&dtestart="+dtestart;
			var dteend = jQuery("#dteend").datebox("getValue");
			filterStr += "&dteend="+dteend;
			var dteActualStart = jQuery("#dteActualStart").datebox("getValue");
			filterStr += "&dteActualStart="+dteActualStart;
			var dteEnd = jQuery("#dteEnd").datebox("getValue");
			filterStr += "&dteEnd="+dteEnd;
			
			//alert(filterStr);
			return filterStr;

		}
	</script>
		<form name="frmMasterPlanRelated" id="frmMasterPlanRelated" action="" method="post">
			<div id="wrapper">
				<div class="main-cntborder" >
					<table>
					<tr>
					<td valign="top" style="padding-left:10%;">
						<label  >Category</label>
					
						<div class="easyui-paddingbfpx">
							<input id="cmbCategory" name="cmbCategory" style="width:304px;" class="easyui-combobox" />
						</div>
						<label >Sub Category</label>
						<div class="easyui-paddingbfpx">
							<input id="cmbSubCategory" name="cmbSubCategory" style="width:304px;" class="easyui-combobox" />
						</div>
						<label >Activity</label>
							
						<div class="easyui-paddingbfpx">
							<input id="cmbActivity" name="cmbActivity" style="width:304px;" class="easyui-combobox" />
						</div>
						
						<div>
						<label >Plan From Date</label>
						<label style="padding-left:65px">Plan To Date</label>
						</div>
						<div class="easyui-paddingbfpx" > 
							<span ><input id="dtestart" name="dtestart" class="easyui-combobox"  style="width:150px;" value=""></span>
							<span><input id="dteend" name="dteend" class="easyui-combobox"  style="width:150px;" value=""  ></span>
							
						</div>
						</td>
						<td  valign="top" style="width:35%">
						<label >Responsibility</label>
						<div class="easyui-paddingbfpx">
							<input id="cmbResponsibility" name="cmbResponsibility"class="easyui-combobox" style="width:304px"/>
						</div>	
						<label >Assigned to</label>
						<div class="easyui-paddingbfpx">
							<input id="cmbAssignedto" name="cmbAssignedto"class="easyui-combobox" style="width:304px"/>
						</div>
						<div>
						<label>Status</label>
						<div class="easyui-paddingbfpx">
							<input id="cmbStatus" name="cmbStatus"class="easyui-combobox" style="width:304px"/>
						</div>
						<label >Actual From Date</label>
						<label style="padding-left:60px">Actual To Date</label>
						</div>
						<div class="easyui-paddingbfpx"style="padding-left:1.3%" > 
							<span style="margin-left:-3px;"><input id="dteActualStart" name="dteActualStart" class="easyui-combobox"  style="width:150px;" value=""></span>
							<span><input id="dteEnd" name="dteEnd" class="easyui-combobox"  style="width:150px;" value=""  ></span>
							
						</div>
						
					</td>
					
					</tr>
					</table>
				</div>
				
				</div>
		</form>