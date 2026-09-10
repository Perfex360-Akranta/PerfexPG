<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/costinfo_js.js"></script>
<link rel="stylesheet" type="text/css" href="css/easyui.css">

<div class="main-cntborder" style="display: block;">

<table  class="tablealign-center" >
    <tr>
        <!--top left content -->
        <td style="width:33%" valign=''>
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label>Factory</label>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="" name="" class="easyui-combobox"  style="width:180px;" value=""  >
                </div>
            </div>
        </td>
        <!--top Right content-->
        <td valign='top' style="width:33%;">
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label>Cost</label>
                </div> 
                <div class="easyui-paddingbfpx">
                    <input id="" name="" class="easyui-combobox"  style="width:180px;" value=""  >
                </div>
            </div>
        </td>
        <td valign='top' style="width:33%;">
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label>Doc No</label>
                    <span  style="margin-left: 130px">Actual Time</span>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="" name="" class="easyui-combobox"  style="width:180px;" value=""  >
                    <input id="" name="" type="text" class="easyui-text"  style="width:80px;" value=""  >
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <!--top left content -->
        <td style="width:33%" >
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label>Section</label>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="" name="" class="easyui-combobox"  style="width:180px;" value=""  >
                </div>
            </div>
        </td>
        <!--top Right content-->
        <td class="valigncnt" valign='top' style="width:33%;">
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label >Equipment</label>
                </div> 
                <div class="easyui-paddingbfpx">
                    <input id="" name="" class="easyui-combobox"  style="width:180px;" value=""  >
                </div>
            </div>
        </td>
        <td class="valigncnt" valign='top' style="width:33%;">
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label>
                    <ul style="float: left;margin: 0px 0px 0px 15px;padding: 0px;"><li style="float: left;">Press F11</li><li style="float: left;margin-left: 20px;">Auto Entry</li></ul>
                </label>                  
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input type="button" class="easyui-button" value="MAINTANANCE WORK ORDER">                   
                </div>
            </div>
        </td>
    </tr>
</table>

<div class="easyui-tabs" fit="true" plain="true" style="width:100%;height: 650px;padding-left:100px;">
    <div title="Summary and Activity" style="padding:0px;">
        <table border="0" style="width:100%;">
        <tr>
        <td class="valigncnt" colspan="2">
        <div class="main-header">Work Summary</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Assemply</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Phenomena</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Cause</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Maint Section</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Date</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="" class="easyui-datebox" required="true" style="width:180px;"/>                             
                        </div>
                    </div>
                </td>            
                <td class="valigncnt" valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx"><label>Problem</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <textarea style="width: 350px;" rows="3" 6=""></textarea>                            
                        </div> 
                        <div  class="easyui-paddingbfpx"><label>Counter measure</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <textarea style="width: 350px;" rows="3" 6=""></textarea>                   
                        </div>   
                        <div  class="easyui-paddingbfpx">
                        <label>Work Start</span>
                        <span  style="margin-left: 110px;">Work End</span>
                        </div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="" class="easyui-datebox" required="true" style="width:120px;"/>
                            <input type="text" class="easyui-text" size="6">
                            <input id="" class="easyui-datebox" required="true" style="width:120px;"/>
                            <input type="text" class="easyui-text" size="6">                                  
                        </div>                     
                    </div>	
                </td>
            </tr>
            <tr>
            <td class="valigncnt" colspan="2">
        <div class="main-header">Cost Summary</div>
        </td>
        </tr>
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center">
        <table id="grid1" style="width:100%"><tr><td/></tr></table>
        <div id="pager1"></div>
        </td>
        </tr>
        </table>
    </div>
    
       <div title="Employee Cost" style="padding:0px;">
        <table border="0" style="width:100%;">
        
        <tr>
            <td class="valigncnt" colspan="2">
        <div  class="main-header">Employee Cost Estimation</div>
        </td>
        </tr>
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center">
        <table id="grid2" style="width:100%"><tr><td/></tr></table>
        <div id="pager2"></div>
        </td>
        </tr>
        
        <tr>
        <td colspan="2">
        <div  class="main-header">Employee Cost Actual</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Grade</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Employee</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>    
                        <div  class="easyui-paddingbfpx">
                        <label>Normal</label>
                        <span  style="margin-left: 70px;">Call-Out</span>
                        <span  style="margin-left: 60px;">OT</span>
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="15" >
                            <input type="text" class="easyui-text" size="15" style="margin-left: 16px;">
                            <input type="text" class="easyui-text" size="18" style="margin-left: 16px;">                                  
                        </div>                      
                    </div>
                </td>
                <!--top Right content-->
                <td class="valigncnt" valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label>Normal Mins</label>
                        <span  style="margin-left: 10px;">Call-Out Mins</span>
                        <span  style="margin-left: 8px;">OT Mins</span>
                        <span  style="margin-left: 38px;">Total</span>
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="10" >
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">                                  
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">
                            <input type="button" class="easyui-button" value="Insert" style="width: 80px;">
                        </div>   
                        <div  class="easyui-paddingbfpx">
                        <label>Date / Activity</label>
                        </div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="" class="easyui-datebox" required="true" style="width:80px;"/>
                            <textarea style="width: 265px;height: 20px" rows="1" cols="6"></textarea>   
                            <input type="button" class="easyui-button" value="Clear" style="width: 80px;">                                                                
                        </div>  
                        <div  class="easyui-paddingbfpx"><label>Remark</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <textarea style="width: 350px;height: 25px" rows="3" cols="2"></textarea>
                            <input type="button" class="easyui-button" value="Delete" style="width: 80px;">                         
                        </div>          
                    </div>	
                </td>
            </tr>
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center">
        <table id="grid3" style="width:100%"><tr><td/></tr></table>
        <div id="pager3"></div>
        </td>
        </tr>
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center">
        <div style="float: left;margin-left: 20px;">        
        <table id="grid4" style="width:100%"><tr><td><td/></tr></table>
        <div id="pager4"></div>
        </div>
        <div style="float: left;width: 100px;text-align: left;margin-top: 30px;">        
        Number of Contractor
        <input type="text" class="easyui-text" value=""><br/><br/><br/>
        Total
        <input type="text" class="easyui-text" value="">
        </div>
        </td>
        </tr>
        </table>
    </div>
    
    
    <div title="Contractor Cost" style="padding:0px;">
        <table border="0" style="width:100%;">
        
        <tr>
            <td class="valigncnt" colspan="2">
        <div class="main-header">Contractor Cost Estimation</div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid5" style="width:100%"><tr><td/></tr></table>
        <div id="pager5"></div>
        </td>
        </tr>
        
        <tr>
        <td colspan="2">
        <div  class="main-header">Contractor Cost Actual</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Grade</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Contractor</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>    
                        <div  class="easyui-paddingbfpx">
                        <label>Normal</label>
                        <span  style="margin-left: 70px;">Call-Out</span>
                        <span  style="margin-left: 60px;">OT</span>
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="15" >
                            <input type="text" class="easyui-text" size="15" style="margin-left: 16px;">
                            <input type="text" class="easyui-text" size="18" style="margin-left: 16px;">                                  
                        </div>                      
                    </div>
                </td>
                <!--top Right content-->
                <td class="valigncnt" valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label>Normal Mins</label>
                        <span  style="margin-left: 10px;">Call-Out Mins</span>
                        <span  style="margin-left: 8px;">OT Mins</span>
                        <span  style="margin-left: 38px;">Total</span>
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="10" >
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">                                  
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">
                            <input type="button" class="easyui-button" value="Insert" style="width: 80px;">
                        </div>   
                        <div  class="easyui-paddingbfpx">
                        <label>Date / Activity</label>                        
                        </div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="" class="easyui-datebox" required="true" style="width:80px;"/>
                            <textarea style="width: 265px;height: 20px" rows="1" cols="6"></textarea>   
                            <input type="button" class="easyui-button" value="Clear" style="width: 80px;">                                                                
                        </div>  
                        <div  class="easyui-paddingbfpx"><label>Remark</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <textarea style="width: 350px;height: 25px" rows="3" cols="2"></textarea>
                            <input type="button" class="easyui-button" value="Delete" style="width: 80px;">                         
                        </div>          
                    </div>	
                </td>
            </tr>
        <tr>
        <td class="valigncnt" colspan="2" valign="middle" align="center">
        <table id="grid6" style="width:100%"><tr><td/></tr></table>
        <div id="pager6"></div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <div style="float: left;margin-left: 20px;">        
        <table id="grid7" style="width:100%"><tr><td><td/></tr></table>
        <div id="pager7"></div>
        </div>
        <div style="float: left;width: 100px;text-align: left;margin-top: 30px;">        
        <br/><br/><br/>
        <br/><br/><br/>
        Total
        <input type="text" class="easyui-text" value="">
        </div>
        </td>
        </tr>
        </table>
    </div>
    
    
    <div title="Spare Cost" style="padding:0px;">
        <table border="0" style="width:100%;">
        
        <tr>
            <td colspan="2">
        <div  class="main-header">Spare Cost Estimation</div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid8" style="width:100%"><tr><td/></tr></table>
        <div id="pager8"></div>
        </td>
        </tr>
        
        <tr>
        <td colspan="2">
        <div class="main-header">Spare Cost Actual</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Spares</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Requested By</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>    
                                             
                    </div>
                </td>
                <!--top Right content-->
                <td class="valigncnt" valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label>Normal</label>
                        <span  style="margin-left: 70px;">Call-Out</span>
                        <span  style="margin-left: 60px;">OT</span>
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="15" >
                            <input type="text" class="easyui-text" size="15" style="margin-left: 16px;">
                            <input type="text" class="easyui-text" size="18" style="margin-left: 16px;">                                  
                        </div>  
                         
                        <div style="padding-bottom: 8px;padding-right:10px;margin-top: 25px"> 
                               <input type="button" class="easyui-button" value="Insert" style="width: 80px;">
                            <input type="button" class="easyui-button" value="Clear" style="width: 80px;">   
                                <input type="button" class="easyui-button" value="Delete" style="width: 80px;">                                                             
                        </div>   
                    </div>	
                </td>
            </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid9" style="width:100%"><tr><td/></tr></table>
        <div id="pager9"></div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid10" style="width:100%"><tr><td/></tr></table>
        <div id="pager10"></div>
        <div style="float: rigth;width: 900px;text-align: right;margin-top: 5px;">
        Totel
        <input type="text" class="easyui-text" value="">
        </div>
        </td>
        </tr>
        </table>
    </div>
    
    
    <div title="Service Cost" style="padding:0px;">
        <table border="0" style="width:100%;">
        
        <tr>
            <td colspan="2">
        <div class="main-header">Service Cost Estimation</div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid11" style="width:100%"><tr><td/></tr></table>
        <div id="pager11"></div>
        </td>
        </tr>
        
        <tr>
        <td colspan="2">
        <div class="main-header">Service Cost Actual</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Service</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Job Description</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <textarea rows="3" cols="46"></textarea>                           
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Bill No</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" type="text" class="easyui-text"  style="width:345px;" value=""  >
                                                        
                        </div>               
                    </div>
                </td>
                <!--top Right content-->
                <td valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label>Bill Date</label>
                        <span  style="margin-left: 70px;">Bill Value</span>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input id=" " class="easyui-datebox" required="true" style="width:150px;"/> 
                            <input type="text" class="easyui-text" size="30" style="margin-left: 16px;">
                            <input type="button" class="easyui-button" value="Insert" style="width: 80px;">                                                
                        </div> 
                         <div  class="easyui-paddingbfpx">
                        <label>Bill Value</label>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <textarea rows="6" cols="46" style="float: left;"></textarea>
                            <div style="width: 80px;float: left;margin-left: 3px"><input type="button" class="easyui-button" value="Clear" style="width: 80px;"><br/><br/><br/>
                            <input type="button" class="easyui-button" value="Delete" style="width: 80px;"></div>
                                                                           
                        </div> 
                   
                    </div>	
                </td>
            </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid12" style="width:100%"><tr><td/></tr></table>
        <div id="pager12"></div>
        <div style="float: rigth;width: 900px;text-align: right;margin-top: 5px;">
        Total
        <input type="text" class="easyui-text" value="">
        </div>
        </td>
        </tr>        
        </table>
    </div>
<div title="Utilities Cost" style="padding:0px;">
        <table border="0" style="width:100%;">
        
        <tr>
            <td colspan="2">
        <div  class="main-header">Utility Cost Estimation</div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid13" style="width:100%"><tr><td/></tr></table>
        <div id="pager13"></div>
        </td>
        </tr>
        
        <tr>
        <td colspan="2">
        <div  class="main-header">Utility Cost Actual</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Utilities</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Requested By</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:350px;" value=""  >
                        </div>
                        <div  class="easyui-paddingbfpx">
                        <label>Date</span>
                        <span  style="margin-left: 135px;">Quality</span>
                        <span  style="margin-left: 60px;">Minutes</span>
                        </div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id=" " class="easyui-datebox" required="true" style="width:150px;"/> 
                            <input type="text" class="easyui-text" size="13" style="margin-left: 16px;">
                            <input type="text" class="easyui-text" size="10" style="margin-left: 16px;">
                        </div>               
                    </div>
                </td>
                <!--top Right content-->
                <td class="valigncnt" valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label>Rate</label>
                        <span  style="margin-left: 150px;">Cost</span>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="28" >
                            <input type="text" class="easyui-text" size="28" style="margin-left: 16px;">
                            <input type="button" class="easyui-button" value="Insert" style="width: 80px;">                                                
                        </div> 
                         <div  class="easyui-paddingbfpx">
                        <label>Remarks</label>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <textarea rows="4" cols="36" style="float: left;"></textarea>
                            <div style="width: 80px;float: left;margin-left: 3px"><input type="button" class="easyui-button" value="Clear" style="width: 80px;"><br/><br/><br/>
                            <input type="button" class="easyui-button" value="Delete" style="width: 80px;"></div>
                                                                           
                        </div> 
                   
                    </div>	
                </td>
            </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid14" style="width:100%"><tr><td/></tr></table>
        <div id="pager14"></div>
        <div style="float: rigth;width: 900px;text-align: right;margin-top: 5px;">
        Totel
        <input type="text" class="easyui-text" value="">
        </div>
        </td>
        </tr>        
        </table>
    </div>
    
    <div title="Other Cost" style="padding:0px;">
        <table border="0" style="width:100%;">
        
        <tr>
            <td colspan="2">
        <div  class="main-header">Other Cost Estimation</div>
        </td>
        </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid15" style="width:100%"><tr><td/></tr></table>
        <div id="pager15"></div>
        </td>
        </tr>
        
        <tr>
        <td colspan="2">
        <div  class="main-header easyui-paddingbfpx">Other Cost Actual</div>
        </td>
        </tr>
            <tr>
                <!--top left content -->
                <td class="valigncnt" style="width:50%" >
                    <div style="float:left;margin-left: 20px;">
                         <div  class="easyui-paddingbfpx"><label>Expense</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:315px;" value=""  >
                            <input type="button" class="easyui-button" value="...">                            
                        </div>
                        <div  class="easyui-paddingbfpx"><label>Requested By</label></div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id="cmbMachine" name="cmbMachine" class="easyui-combobox"  style="width:350px;" value=""  >
                        </div>
                        <div  class="easyui-paddingbfpx">
                        <label>Date</label>
                        <span  style="margin-left: 135px;">Cost</span>                        
                        </div> 
                        <div class="easyui-paddingbfpx"> 
                            <input id=" " class="easyui-datebox" required="true" style="width:150px;"/> 
                            <input type="text" size="13" style="margin-left: 16px;" class="easyui-text">                            
                        </div>               
                    </div>
                </td>
                <!--top Right content-->
                <td class="valigncnt" valign='top' style="width:50%;">
                    <div  style="padding-left:0px;">
                        <div  class="easyui-paddingbfpx">
                        <label>Rate</label>
                        <span  style="margin-left: 150px;">Cost</span>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <input type="text" class="easyui-text" size="28" >
                            <input type="text" class="easyui-text" size="28" style="margin-left: 16px;">
                            <input type="button" class="easyui-button" value="Insert" style="width: 80px;">                                                
                        </div> 
                         <div  class="easyui-paddingbfpx">
                        <label>Remarks</label>                        
                        </div> 
                        <div class="easyui-paddingbfpx">
                            <textarea rows="4" cols="36" style="float: left;"></textarea>
                            <div style="width: 80px;float: left;margin-left: 3px"><input type="button" class="easyui-button" value="Clear" style="width: 80px;"><br/><br/><br/>
                            <input type="button" class="easyui-button" value="Delete" style="width: 80px;"></div>
                                                                           
                        </div> 
                   
                    </div>	
                </td>
            </tr>
        <tr>
        <td colspan="2" valign="middle" align="center">
        <table id="grid16" style="width:100%"><tr><td/></tr></table>
        <div id="pager16"></div>
        <div style="float: rigth;width: 900px;text-align: right;margin-top: 5px;">
        Total
        <input type="text" class="easyui-text" value="">
        </div>
        </td>
        </tr>        
        </table>
    </div>
    </div>
    <div class="clearfix"></div>
    </div>