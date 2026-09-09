<script type="text/javascript" src="js/jquery.easyui.min.js"></script> 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){
jQuery("#grid1").jqGrid({
		datatype: "local",
		colNames:[ 'SL NO','Activity','Precent Skill' ,'Expected Skill','Skill Gap','Action Plane','Training Start Date','Training Completed date'],
		colModel:[ {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100},
		           {name:'aumWHYWHY',index:'WHY',editable:false, width:100}
				  ],
		    data:[
			  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
			  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false}									  							
		  ],
		rowNum:50,
		rowList:[5,10,20],
		rownumbers: true,
		shrinkToFit:false,								
		pager: '#pager1', 
		sortname: 'id',
		viewrecords: true,
		sortorder: "asc", 
		caption:'Skill Updation',
		width:895,
		height:100,
		loadonce: true
	});
});

</script>
 	
<div class="main-cntborder" >

    <table  border="0" class="tablealign-center"  >
        <tr>
            <!--top left content -->
            <td style="width:50%" class="align-center" >
                <div class="mrgnleftsxt" >
                    <div  class="easyui-paddingbfpx"><span class="lbl">Factory</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:350px;" value=""  > 
                    </div>
                    <div  class="easyui-paddingbfpx"><span class="lbl">Section</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:350px;" value=""  > 
                    </div>
                    <div  class="easyui-paddingbfpx"><span class="lbl">Cost Center</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbphnom" name="cmbphnom" class="easyui-combobox"  style="width:350px;" value=""  >	
                    </div>
                    <div  class="easyui-paddingbfpx"><span class="lbl">Cell</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:350px;" value=""  >
                    </div>
                    
                </div>
            </td>
            <!--top Right content-->
            <td style="width:50%" valign=''>
                <div class="mrgnleftft" >
                    <div  class="easyui-paddingbfpx"><span class="lbl">Name</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:350px;" value=""  > 
                    </div>
                    <div  class="easyui-paddingbfpx"><span class="lbl">Token No</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbSection" name="cmbSection" class="easyui-combobox"  style="width:350px;" value=""  > 
                    </div>
                    <div  class="easyui-paddingbfpx"><span class="lbl">Skill Category</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbphnom" name="cmbphnom" class="easyui-combobox"  style="width:350px;" value=""  >	
                    </div>
                    <div  class="easyui-paddingbfpx"><span class="lbl">Prepared By</span></div> 
                    <div class="easyui-paddingbfpx"> 
                        <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:350px;" value=""  >
                    </div>
                    
                </div>
            </td>
        </tr>
        <tr>
        <td colspan="2">
        <span style="margin-right: 30px;">Date<input id="" class="easyui-datebox" required="true" style="width:90px;"/></span> 
        <span style="margin-right: 30px;">Knowledge Averager<input type="text" class="easyui-text" size="10" value=""></span>
        <span style="margin-right: 30px;">Skill Average<input type="text" class="easyui-text" size="10" value=""></span>
        Competency Averagr<input type="text" class="easyui-text" size="10" value="">
        <input type="button" class="easyui-button" value="Upload">
        </td>
        </tr>
        <tr>
        <td colspan="2">
        <input type="radio"> Completed Activity
        <input type="radio"> Skill gap should be greater than 1
        <input type="radio"> Knowledge Parameter
        <span class="notes">Date Format Should be "MM-DD-YYYY"</span>
        <input type="button" class="easyui-button" value="View">
        </td>
        </tr>
    </table>
    <div >    
    <table id="grid1" ><tr><td/></tr></table>
    <div id="pager1"></div>                    
</div>
</div>