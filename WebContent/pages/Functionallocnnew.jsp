<script><!--
jQuery(document).ready(function(){
	
	initialiseForm('frmfnlnNode');
	jQuery('#submitForm').val('frmfnlnNode');	
	//fillComboBox("frmfnlnNode","cmbFindNode","" );
	var combovalueselect;
	/* data:[
        {id:'cmp',text:'Functional Location'},
        {id:'sbu',text:'SBU'},
        {id:'subunt',text:'PBU'},
        {id:'sect',text:'DMT'},
        {id:'cell',text:'JH'}
    ], valueField:'id',
    textField:'text', */
	jQuery("#cmbFindNode").combobox({
		
	    onSelect:function(recordid){
				
				combovalueselect= recordid.id ;//jQuery('#cmbFindNode').combobox("getValue");
				var combovalue=jQuery('#cmbFindNodeAdd').combobox("getValue");
				//alert("combovalue::::::"+combovalueselect);
				 var id=combovalue;
				 //alert(id);

				if (jQuery('#cmbFindNodeAdd').combobox("getValue") == "add")
	            {
				   if (combovalueselect == "sbu") 
					{
	                   // alert("INSIDE THE IF"+id);
	                    //refreshForm();
	                    jQuery('#cmbFindNode').combobox('disable');
	                    LoadForm("divsbuinfnln","","SBU_input.funlocn?id="+id,"","loadDivSuccesSbu","");
	                    jQuery('#hiddenUrl').val('SBU_input.funlocn');
	                  
					 }
					else if(combovalueselect == "subunt")
						{
						// refreshForm();
						 jQuery('#cmbFindNode').combobox('disable');
						LoadForm("divpbu","","PBU_input.funlocn?id="+id,"","loadDivSuccessPbu","");
						//jQuery('#submitForm').val('frmPbu');
						jQuery('#hiddenUrl').val('PBU_input.funlocn');
	                    }

					else if(combovalueselect == "sect")
					{
						//refreshForm();
						 jQuery('#cmbFindNode').combobox('disable');
						LoadForm("divsect","","section_input.sect?id="+id,"","loadDivSuccessSect","");
						jQuery('#hiddenUrl').val('section_input.sect');
						
			         }

					else if(combovalueselect == "cell")
					{   
						 jQuery('#cmbFindNode').combobox('disable');
					    LoadForm("divcell","","cell_input.cell?id="+id,"","loadDivSuccessCell","");
					//jQuery('#submitForm').val('frmCell');
					    jQuery('#hiddenUrl').val('cell_input.cell');
					    
	                }
			}
				else
					{
					if (jQuery('#cmbFindNodeAdd').combobox("getValue") == "update")
		            {
					   if (combovalueselect == "sbu") 
						{
						   jQuery('#cmbFindNode').combobox('disable');
						   LoadForm("divsbuinfnln","","SBU_input.funlocn?id="+id,"","loadDivSuccess","");
						   jQuery('#hiddenUrl').val('SBU_input.funlocn');
						 
		                }
						else if(combovalueselect == "subunt")
							{
						
							 jQuery('#cmbFindNode').combobox('disable');
							LoadForm("divpbu","","PBU_input.funlocn?id="+id,"","loadDivSuccessPbu","");
							jQuery('#hiddenUrl').val('PBU_input.funlocn');
							 
		                    }

						else if(combovalueselect == "sect")
						{
							 jQuery('#cmbFindNode').combobox('disable');
							LoadForm("divsect","","section_input.sect?id="+id,"","loadDivSuccessSect","");
							jQuery('#hiddenUrl').val('section_input.sect');
							 
		                }

						else if(combovalueselect == "cell")
						{
							 jQuery('#cmbFindNode').combobox('disable');
							LoadForm("divcell","","cell_input.cell?id="+id,"","loadDivSuccessCell","");
							jQuery('#hiddenUrl').val('cell_input.cell');
							 
		                }
		            }
					}


		            }
				
				
		
		});
	
});


	function loadDivSuccess() {
		//alert(1);
	}
	 function makeDisable()
	    {
	    	var x=document.getElementById("cmbFindNode")
		    x.disabled=true;
	    }
		//onSelect
	jQuery("#cmbFindNodeAdd").combobox({
		onChange:function(recordid){
		
            
		}
	});


		function frmfnlnNode_beforeSubmit() {

			//alert("INSIDE THE BEFORE SUBMIT");
			
			var fndNode =jQuery('#cmbFindNode').combobox("getValue");
			if(fndNode=="sbu")
				{
				// alert("INSIE THE SUBMIT IF");
				 var url = "SBU_save.funlocn";
				var formId = "frmSbu";
				saveForm(formId,url);
				//alert("aftersaveform");	
				return false;
				}
			else if (fndNode=='subunt') {
				 
				// alert("INSIDE THE IF N SUBMIT");
				var url = "PBU_save.funlocn";
				var formId = "frmPbu";
				saveForm(formId,url);
				//jQuery('#submitForm').val(formId);
				//jQuery('#submitForm').val('frmSbu'); 
				return false;
			}
			else if (fndNode=='sect') {
				 
				// alert("INSIDE THE IF N SUBMIT");
				var url = "section_save.sect";
				var formId = "frmSection";
				saveForm(formId,url);
				//jQuery('#submitForm').val(formId);
				//jQuery('#submitForm').val('frmSbu'); 
				return false;
			}
			else if (fndNode=='cell') {
				 
				 //alert("INSIDE THE IF N SUBMIT");
				var url = "cell_save.cell";
				var formId = "frmCell";
				saveForm(formId,url);
				//jQuery('#submitForm').val(formId);
				//jQuery('#submitForm').val('frmSbu'); 
				return false;
			}
			/*else if (fndNode=='subunt') {
				var url = "PBU_save.funlocn";
				var formId = "frmPbu";
				saveForm(formId,url);
				return false;
			}*/
			
		}






</script>


<form id="frmfnlnNode" name="frmfnlnNode">

<!--	style="margin-top:30px;margin-left:94px; cellspacing="5"-->

<table style="margin-top:60px;margin-left:250px;">
<tr>
	<td>
	
		<div style="margin-left:25px"><label><b>Add/Update:</b></label>
			<span>
			<br><select id="cmbFindNodeAdd" class="easyui-combox" name="cmbFindNodeAdd" style="width:150px;/* height:10px; */"></br>
		  	
			   <option value="" >-Add/Update-</option>
			    
			  <option value="add">Add</option>
			   <option value="update">Update</option>
		   </select>
			</span>
		</div>
		
	</td>
</tr>
<tr>
<td>  <div style="margin-left:25px">
		<div style="padding-top:30px;"><label><b>Functional Location Type:</b></label>
			<span><!-- data-options="url:'/pages/GenTlPbumst.jsp'" -->
			</br><select id="cmbFindNode" class="easyui-combobox" name="cmbFindNode"  style="width:205px;postion:absolute;/* height:20px; */" ></br>

			   <option value="cmp">Functional Location</option>
<!--			   <option value="lcn">Location</option>-->
			   <option value="sbu">SBU</option>
			   <option value="subunt">PBU</option>
			   <option value="sect">DMT</option>
			   <option value="cell">JH</option>
			   <!--<option value="eqp">Equipment</option>
			   <option value="assm">Assembly</option>
			   <option value="spr">Spare</option>
		   --></select>
			</span>
			</div>
		</div>
		
			</td>
</tr>
		<tr id="fnlocn" class="fnlocn">
		<td>
	
		<div id="divsbuinfnln" >		
		
</div>
		</td>
		</tr>
		<tr>
		<td>
	<div id="divpbu"></div>
		</td>
		</tr>
<tr>
<td>
<div id="divsect"></div>

</td>
</tr>
		<tr>
		<td>
		<div id="divcell"></div>
		</td>
		</tr>

</table>

	<!--<input type="hidden" id="mode" name="mode" />
	 -->
	 <input type="hidden" id="hdnformmode" value="${requestScope.formType}" >
	 <input type="hidden" id="sbuid" name="sbuid" value="sbuid">
	
	</form>
