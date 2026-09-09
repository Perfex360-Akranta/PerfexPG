

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%> 
<script>
var glbType = "";
/*Created By Sathish Kumar .v */
 
    jQuery(document).ready(function()  {   //alert(1);
            initialiseForm('frmMom');
jQuery('#submitForm').val('frmMom');

var mode = jQuery("#frmMom input[id=mode]").val();
if(screen.width <= 1024)
{
jQuery('#meetinghppnd').css('padding-left','174px');
jQuery('#meetinghppnd').css('width','180px');
jQuery('#processsmeetinghppnd').css('padding-top','14px');
jQuery('#MessageBoard').css('padding-right','70px');

             }

fillComboBox("frmMom","cmbMomsEmployee","employee.commonFilter");

            formatDateBox('dteMomsDate', 'dd-MMM-yyyy');
formatDateBox('dteMomaDate', 'dd-MMM-yyyy');
fillComboBox("frmMom","cmbMomsShiftid","shift.commonFilter");
//fillComboBox("frmMom","cmbMomsShiftid","combo_shift.brdn");
fillComboBox("frmMom","cmbdEmployee","employee.commonFilter");
fillComboBox("frmMom","cmbdprtmntname","department.rsrsk");
fillComboBox("frmMom","cmbMomsMeetingtype","MeetingType.mom","",false);
fillComboBox("frmMom","cmbMomsPillarid","pillar.commonFilter");
fillComboBox("frmMom","cmbMomsPillargroup","pillargroup.mom");

//fillComboBox("frmMom","cmbMomsRole","Rolecombo.mom");

fillComboBox("frmMom","cmbMomsRole","Rolecombo.mom");

//fillComboBox("frmMom","cmbAttcombo_"+rowId+'_'+colId,"MeetingAttendance.mom");     getFieldValue

               /* var pillarmst = getFieldValue("cmbMomsPillarid", "frmMom");

if(pillarmst=='X'){//alert(1);
   
pillarmst.replace('X','');
//alert(pillarmst.replace());
//jQuery("#cmbMomsPillarid").combobox("setValue",value);
                   //alert(2);
}*/


glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();
//alert("type>>>"+Type);
if(Type=="JH"){
 setFieldValue('cmbMomsMeetingtype',"J");
 disableField("frmMom", "cmbMomsMeetingtype");
 readOnlyFields("cmbMomsPillarid");
 jQuery('#SafetyTalk').addClass("mandatory-lbl");
 jQuery('#Agenda').addClass("mandatory-lbl");    // Added By kiran on 12Feb2023

 jQuery('#pillargrouplblid').hide();
 jQuery('#spnPillargroup').hide();
}else if(Type=="Dmt"){
  setFieldValue('cmbMomsMeetingtype',"D");
  disableField("frmMom", "cmbMomsMeetingtype");
  readOnlyFields("cmbMomsPillarid");
  jQuery("#cmbMomsShiftid").hide();
  jQuery("#spnShift").hide();
                   jQuery("#lblShift").hide();
                   readOnlyFields("cmbMomsShiftid");
                   jQuery('#pillargrouplblid').hide();
                   jQuery('#spnPillargroup').hide();
                   jQuery('#Agenda').addClass("mandatory-lbl");    // Added By kiran on 12Feb2023

}else if(Type=="Pillar"){
  setFieldValue('cmbMomsMeetingtype',"P");
  disableField("frmMom", "cmbMomsMeetingtype");
  jQuery('#pillarlblid').addClass("mandatory-lbl");
                   enableFields("cmbMomsPillarid");
                   jQuery("#spnShift").hide();
                   jQuery("#lblShift").hide();
                   readOnlyFields("cmbMomsShiftid");
   jQuery('#Agenda').addClass("mandatory-lbl");    // Added By kiran on 12Feb2023
}
/* else if (Type == "Production" || Type == "Others") {

    if (Type == "Others") {
        setFieldValue('cmbMomsMeetingtype', "O");
        jQuery('#Agenda').addClass("mandatory-lbl");    // Added By Swetha on 11Mar2026
        jQuery('#lblRemarks').addClass("mandatory-lbl");//Added 
        hideProductionNotRelatedFields(); // Added By Swetha on 11Mar2026
        jQuery('#spnMomsRemarks').show();//Added 
        jQuery('#lblRemarks').show();//Added 
        jQuery('#lblRemarks').css({
            'position': 'relative',
            'right': '200px'
            
        });//Added 
        jQuery('#txtMomsRemarks').css({
            'position': 'relative',
            'right': '200px',
            'top':'5px'
        });//Added 
    } else {
        setFieldValue('cmbMomsMeetingtype', "PD");
    }

   disableField("frmMom", "cmbMomsMeetingtype");
   readOnlyFields("cmbMomsPillarid");
   readOnlyFields("cmbMomsShiftid");

           hideProductionNotRelatedFields();
 
} */
else if (Type == "Production" || Type == "Others") {

    if (Type == "Others") {
        setFieldValue('cmbMomsMeetingtype', "O");
        jQuery('#Agenda').addClass("mandatory-lbl");
        jQuery('#lblRemarks').addClass("mandatory-lbl");//Added 
        jQuery('#lblRemarks').text("Type of Meeting");
        hideProductionNotRelatedFields(); // Added By Swetha on 11Mar2026
        jQuery('#spnMomsRemarks').show();//Added 
        jQuery('#lblRemarks').show();//Added 
        jQuery('#lblRemarks').css({
            'position': 'relative',
            'right': '200px'
            
        });//Added 
        jQuery('#txtMomsRemarks').css({
            'position': 'relative',
            'right': '200px',
            'top':'5px'
        });//Added 

       
    } else {
        setFieldValue('cmbMomsMeetingtype', "PD");
        hideProductionNotRelatedFields();
    }

   disableField("frmMom", "cmbMomsMeetingtype");
   readOnlyFields("cmbMomsPillarid");
   readOnlyFields("cmbMomsShiftid");

         
 
}
else if(Type=="CEC" && glbType=="CEC"){

setFieldValue('cmbMomsMeetingtype',"CEC");
setFieldValue('cmbMomsPillarid',"TGT007");
readOnlyFields("cmbMomsPillarid");
readOnlyFields("cmbMomsMeetingtype");
//frmMomcmbMomsPillarid_onSelect();
//setFieldValue('cmbMomsShiftid',"null");
//frmMomcmbMomsPillargroup_onSelect();

jQuery('#Agenda').addClass("mandatory-lbl");
   jQuery("#cmbMomsShiftid").hide();
     jQuery("#spnShift").hide();
                jQuery("#lblShift").hide();
                jQuery("#btnjhActivity").hide();
                readOnlyFields("cmbMomsShiftid");
                //fnFillAttendanceGrid();
            setFieldValue('cmbMomsPillarid',"TGT007");
            fnFillAttendanceGrid();//added this line - momchange-8th-MAY 
            fillDetailGrid(); //added this line - momchange-8th-MAY 
}else if(Type=="DEC" && glbType=="DEC"){
setFieldValue('cmbMomsMeetingtype',"DEC");
setFieldValue('cmbMomsPillarid',"TGT007");
readOnlyFields("cmbMomsPillarid");
readOnlyFields("cmbMomsMeetingtype");
jQuery("#btnjhActivity").hide();
//fnFillAttendanceGrid();
fnFillAttendanceGrid();//added this line - momchange-8th-MAY 
fillDetailGrid(); //added this line - momchange-8th-MAY 
jQuery('#Agenda').addClass("mandatory-lbl");
   jQuery("#cmbMomsShiftid").hide();
     jQuery("#spnShift").hide();
                jQuery("#lblShift").hide();
                readOnlyFields("cmbMomsShiftid");
//frmMomcmbMomsPillargroup_onSelect();
setFieldValue('cmbMomsPillarid',"TGT007");
//frmMomcmbMomsPillarid_onSelect();
}else if(Type=="FIP"){
disableField("frmMom", "cmbMomsPillarid");
}
else if(Type=="UMC"){
//alert("isnide the if");
   setFieldValue('cmbMomsMeetingtype',"UMC");
   disableField("frmMom", "cmbMomsMeetingtype");
   readOnlyFields("cmbMomsPillarid");
               // enableFields("cmbMomsPillarid");
                jQuery("#spnShift").hide();
                jQuery("#lblShift").hide();
                readOnlyFields("cmbMomsShiftid");
               // fnFillAttendanceGrid();
jQuery('#Agenda').addClass("mandatory-lbl");
               
}

else if(Type=="OGM"){
  setFieldValue('cmbMomsMeetingtype',"OGM");
  disableField("frmMom", "cmbMomsMeetingtype");
  //jQuery('#pillarlblid').addClass("mandatory-lbl");
  readOnlyFields("cmbMomsPillarid");
            // enableFields("cmbMomsPillarid");
             jQuery("#spnShift").hide();
             jQuery("#lblShift").hide();
             readOnlyFields("cmbMomsShiftid");
             
}

var MstKeyid=jQuery('#txtMomsKeyid').val();
var recall=jQuery('#hdnrecall').val();
//var momdate= getFieldValue("dteMomsDate");
var momdate = jQuery("#dteMomsDate").datebox("getValue");
var flid = jQuery("#frmMom input[id='flid']").val();
var shift= getFieldValue("cmbMomsShiftid");


//alert(" recall :: 1234 "+recall+" momdate :: New "+momdate+" shift :: "+shift+" flid :: "+flid+" mode ::"+mode);
//alert("MstKeyid>>>"+MstKeyid);

//------------------------------------Commented By Swetha---------------------------------------------------------//
//if(mode.trim().length>0 && mode=="view")
//processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+MstKeyid+"&type="+glbType+"&mode=view", "momGrid", "pagermom","","","","loadCompleteAction");
//   else if (recall.trim().length<=0 )
//processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+MstKeyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");
//------------------------------------Commented By Swetha---------------------------------------------------------//

//------------------------------------Added By Swetha---------------------------------------------------------//
//fillDetailGrid();

//else
//processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+MstKeyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");

setFunctionalLocWidth('frmMom','600px');

jQuery('#chkMomsIsmeetinghappen').attr('checked', true);

readOnlyFields("txtMomsMeetingno");

               var meetingtitle=getFieldValue("txtMomsMeetingtitle", "frmMom");
               var meethppnd=jQuery('#hdnmeethappnd').val();

if(meethppnd=="No"){
jQuery('#chkMomsIsmeetinghappen').attr('checked', false);
chkboxmeetUnCheck();
}

var keyid = jQuery('#txtMomsKeyid').val();   // get key id
if(keyid.length==0){
               fillWithCurrentDate('dteMomsDate');
               }else
                {
                var val = getFieldValue("cmbMomsMeetingtype", "frmMom");
               
                var pasdate= getFieldValue("dteMomsDate", "frmMom");
                var currentDate = getServerDateTime();
               
                   if(val=="P"){
                    jQuery('#pillarlblid').addClass("mandatory-lbl");
                    enableFields("cmbMomsPillarid");
                   }else{
                    jQuery('#pillarlblid').removeClass("mandatory-lbl");
                    readOnlyFields("cmbMomsPillarid");
                   }
                   
                   jQuery('#btnExcelVw').show();
                   jQuery('#btnMomMail').show();
               }

var locationId = jQuery("#frmMom input[id='location']").val();
var factId = jQuery("#frmMom input[id='factory']").val();
   var sectionId = jQuery("#frmMom input[id='section']").val();
   //var cellId = jQuery("#frmMom input[id='cell']").val();
   var cellId = jQuery("#frmMom input[id='cell']").val();
   
var machId = jQuery("#frmMom input[id='machine']").val();
            var flid = jQuery("#frmMom input[id='flid']").val();
            //alert(" In Jsp ::   "+flid);
       
   var dataStr = "&factId=" + factId
+ "&sectionId=" + sectionId
+ "&cellId=" + cellId + "&machId="
+ machId+"&flid="+ flid;

   
   var Type=jQuery('#hdntype').val();
if (Type =='Pillar' || Type =='UMC' || Type =='OGM')
dataStr += "&disable=N";
   var funclocn="";
   funclocn= "functionalLoc.mom?Type="+Type ;
   dataStr += "&Type="+Type;
   /*if(Type=="JH"){
funclocn= "functionalLoc.mom?Type="+Type ;
}else if(Type=="Dmt"){
funclocn= "functionalLoc.mom?Type="+Type;  
}else
funclocn= "functionalLoc.mom";
   */
   
   //fillcurrecntshift();
   if(MstKeyid==null || MstKeyid.length < 5)
   {
    //alert("inside the shift::::");
      fillcurrecntshift();
   }
   
   loadFunctionalLocation("MomAttfunLocation", funclocn, "MomAttfunLocation", "frmMom",dataStr);

jQuery("#btnAttView").click(function()
{
      var Employee =getFieldValue('cmbdEmployee');
      var Department =getFieldValue('cmbdprtmntname');  
      processGridnew("MoMeetingAtt_input.mom","?q=2&keyid="+keyid+"&dept="+Department+"&emp="+Employee, "attandanceGrid", "pageratt");
     
});

if( mode == "view"){
disableForm();
jQuery('#chkMomsOthers').attr("readonly",true);
}
else {

jQuery("#btnjhActivity").click(function(){
LoadPopUp("divjhActivity","jhActivityMom_input.dashboard", true, "91%", "66%", "12%", "3%", " ", "Jh Activity","",false);
});
jQuery("#btnAttsave").click(function()
{

jQuery('#hdnsavebtn').val("Y");
var MomAtt= getSelectdRowsAtt('attandanceGrid', 'MomAttcheckbox_', 'chkbox');
saveForm('frmMom',"MoMeetingFormATT_save.mom?MomAtt="+MomAtt);

});
jQuery("#btnaddOthers").click(function()
   {
       var keyid = jQuery('#txtMomsKeyid').val();
       var Checkvist=jQuery('#hdnCheckvist').val("Y");
       var shift= getFieldValue("cmbMomsShiftid");
var flid =jQuery("#frmMom input[id='flid']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var glbType=jQuery('#hdntype').val();
var recall=jQuery('#hdnrecall').val();
var pillarid =getFieldValue('cmbMomsPillarid');

var ds = "?&keyid="+keyid+"&shift="+shift+"&date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid+"&recall="+recall;
       if(keyid.trim().length>0||(keyid.trim().length>0 && recall.trim().length>0))
   {
         LoadPopUp("AddVisitors","visitor_input.mom"+ds, true, "46%", "72%", "7%", "27%", " ", "Add Visitors","",false);
                           
}else{

    alert(" First Save MoM ");
                             return false;

}    
     
   });

jQuery("#btnAddNew").click(function()
{
var masterKeyId=getFieldValue('txtMomsKeyid');

var row  = jQuery("#momGrid").jqGrid('getDataIDs');
var rowId= jQuery("#momGrid").jqGrid('getRowData',row);

addRow(row);


    });

  jQuery("#btnDelete").click(function()
  {
removeRecord();
});
 
 
  jQuery("#btnAddEmployee").click(function()
{
          jQuery('#hdnaddemp').val('Y');  
      var Employee =getFieldValue('cmbMomsEmployee');
      var Employeetext =jQuery("#cmbMomsEmployee").combobox("getText");
      var empArr = Employeetext.split('-');
      var row  = jQuery("#attandanceGrid").jqGrid('getDataIDs');
  var masterKeyId=getFieldValue('txtMomsKeyid');
 
  processAjaxCalls("MoMeetingFillRole_modify.mom","emplyid="+Employee,"updateRoleSuccess","");
 
  var role=getFieldValue('cmbMomsRole');
  var roletext =jQuery("#cmbMomsRole").combobox("getText");
  var roleArr = roletext.split('-');
 
 
  //if( role.trim().length<=0){
//  alert(" Select Role");
 // return false;
  //}else{

if(masterKeyId.length==0){
if(row.length==0)
row=row+1;

       addRowEmployee(row,Employee,empArr[1],empArr[0],roleArr[0]);
     
}
else{
 if(row.length==0)
row=row+1;

  addRowEmployee(row,Employee,empArr[1],empArr[0],roleArr[0]);
    }
  //}
 
});
         
         jQuery("#btnAddAllEmployee").click(function()
  {
        var Employee =getFieldValue('cmbMomsEmployee');
        var Employeetext =jQuery("#cmbMomsEmployee").combobox("getText");
        var row  = jQuery("#attandanceGrid").jqGrid('getDataIDs');
        var empArr = Employeetext.split('-');
    var masterKeyId=getFieldValue('txtMomsKeyid');
   
    var role=getFieldValue('cmbMomsRole');
    var roletext =jQuery("#cmbMomsRole").combobox("getText");
    var roleArr = roletext.split('-');
   
    //if( role.trim().length<=0){
  //  alert(" Select Role");
    //return false;
    //}else{
  if(masterKeyId.length==0){
        addRowEmployeeMultiple(row,Employee,empArr[1],empArr[0],roleArr[0]);
  }
  else{  
    addRowEmployeeMultiple(row,Employee,empArr[1],empArr[0],roleArr[0]);
      }
    //}
   
  });

                    }
 
if(jQuery("#meeting").val() != null)
  MomeetinChechappen(jQuery("#meeting").val());
if(jQuery("#meetingatt").val() != null)
  AttandancesCheck(jQuery("#meetingatt").val());

jQuery("#tabMom").tabs(
   {
onSelect : function(title)
{
var Meetingparm = jQuery("#momGrid").getGridParam();
var Mettingtype=getFieldValue('cmbMomsMeetingtype');
glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();
var keyid=jQuery('#txtMomsKeyid').val();
var kid = jQuery("#hdnKeyId").val();


if( title == "Mom"  && (Meetingparm == undefined || Meetingparm  <=0)) {
processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid, "momGrid", "pagermom");
                                //processGridnew("MoMeetingAtt_input.mom","?q=2&keyid="+keyid, "attandanceGrid", "pageratt");
   }else if( title == "Attendance" && Mettingtype.trim().length>0 ){
                      var tabslect=jQuery('#hdntabselect').val();
        var flid =jQuery("#frmMom input[id='flid']").val();
    var cellId = jQuery("#frmMom input[id='cell']").val();
        var momdate = getFieldValue("dteMomsDate", "frmMom");
    var keyid=jQuery('#txtMomsKeyid').val();
    var row = jQuery("#attandanceGrid").jqGrid('getDataIDs');    
    //alert("row"+row);
    if(row.length==0){
      fnFillAttendanceGrid();
      }
     else{
   
    if(Mettingtype!="P")
    {
         fnFillAttendanceGrid();
    }
   
    }
     
                  }/*else if(Type=="CENEHS" || Type=="DEPEHS"){
                   processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-&keyid="+keyid+"&PILLAR="+Type, "attandanceGrid", "pageratt");
                  }*/
          }
 });

 /*
jQuery("#tabMom").tabs(
{
 onLoad : function(title)
   {
 jQuery('.tabs-panels').css('height', '325');
   }
});

*/
   

 
  //jQuery('#frmMom .easyui-text').css('text-transform', 'uppercase');
  //jQuery('#frmMom textarea').css('text-transform', 'uppercase');

 
 
  fileManagerPopUp("","MOM","frmMom","btnfilemgr","MomFilemgr",mode);

  /*jQuery('#chkMomsIsmeetinghappen').click(function(){

  var tickvalue=jQuery('#chkMomsIsmeetinghappen').val();
  alert(" tickvalue::::: "+tickvalue);
  if(tickvalue=="Y")
  untick();
 
});
  */
 
  var MeetingNo =jQuery('#txtMomsMeetingno').val();
var MeetingTitle =jQuery('#txtMomsMeetingtitle').val();//txtMomsMeetingtitle
var Meetingtype = getFieldValue("cmbMomsMeetingtype", "frmMom");
var MeetingSafety =jQuery('#txtMomsSafetytalk').val();
//alert("before");  
jQuery('#hdnMetngtitle').val(MeetingTitle);
jQuery('#hdnMomsMtntyp').val(Meetingtype);
jQuery('#hdnMomsMtngsafety').val(MeetingSafety);
jQuery('#hdnMomsMtngno').val(MeetingNo);
//alert("after");      
       jQuery('#chkMomsIsmeetinghappen').click(function(){
if(jQuery('#chkMomsIsmeetinghappen').is(':checked') == true){
   //alert(jQuery('#chkMomsIsmeetinghappen').is(':checked'));
   chkboxmeetCheck();

}else if(jQuery('#chkMomsIsmeetinghappen').is(':checked') == false){

chkboxmeetUnCheck();

}
});


       //alert(" Inside :: "+jQuery('#hdnDMT').val());

var DMT=jQuery('#hdnDMT').val();
var DMTDBLE=jQuery('#hdnDMTDBLE').val();
if(DMT.trim().length>0 && DMTDBLE.trim().length>0){//alert(1);
readOnlyFields("cmbMomsMeetingtype");
jQuery('#cmbMomsMeetingtype').combobox('setValue','D');

        }


jQuery('#btnExcelVw').click(function()
{
var momKeyId = jQuery('#txtMomsKeyid').val();
var flid = jQuery("#frmMom input[id='flid']").val();

if(momKeyId != null && momKeyId.length >0){
if((glbType=="Production" && glbType.length>0)||(glbType=="Others" && glbType.length>0 ) )
window.open("MoMeetingMom_view.mom?&momKeyId="+momKeyId+"&flid="+flid+"&glbType="+glbType);
else
window.open("MoMeetingMom_view.mom?&momKeyId="+momKeyId+"&flid="+flid);
}
});


jQuery('#chkSelectAll').click(function() {

//LoadingFormWaiter("preLoadContent","LoadContent");
   show_winMask(1);

        var row = jQuery("#attandanceGrid").jqGrid('getDataIDs');    
if(jQuery("#chkSelectAll").is(':checked')== true){
      for(var i=0;i<row.length;i++)
      {
      jQuery('#MomAttcheckbox_'+row[i]+'_1').attr('checked', true);
      chkboxAttCheck(row[i]);
      }
    }
else {
for(var i=0;i<row.length;i++)
      {
      jQuery('#MomAttcheckbox_'+row[i]+'_1').attr('checked', false);
chkboxAttUnCheck(row[i]);
      }
}

show_winMask(0);
});


jQuery('#btnMomMail').click(function(){
var type = getComboBoxText("cmbMomsMeetingtype");
var gmomType ="";
if((glbType=="Production" && glbType.length>0)||(glbType=="Others" && glbType.length>0 ) )
gmomType =glbType;
//getFieldValue("cmbMomsMeetingtype", "frmMom");
//alert(" glbType :: "+glbType);
var momKeyId = jQuery('#txtMomsKeyid').val();
var flid =  jQuery("#frmMom input[id='flid']").val();
var pasdate = getFieldValue("dteMomsDate", "frmMom");
var plrtype = getComboBoxText("cmbMomsPillarid");
            var text=plrtype.substring(0,plrtype.indexOf("-"));

var ds="momKeyId="+momKeyId+"&flid="+flid+"&glbType="+gmomType+"&date="+pasdate;

if(type=="PILLAR")
       ds+="&mtype="+escape(text);
else
ds+="&mtype="+type;

processAjaxCalls("MoMeetingMom_sendMail.mom",ds,"sendMailSuccessCalBk","");
});

var type=jQuery('#hdntype').val();
var MstKeyid=jQuery('#txtMomsKeyid').val();
   var meetingtype=getFieldValue('cmbMomsMeetingtype');
   
if(type=="FIP")
setFieldValue("cmbMomsMeetingtype",type);

if( MstKeyid.trim().length>0 && meetingtype=="P"){
disableField("frmMom", "cmbMomsPillargroup");
}


//alert(" recall :: last "+recall+" momdate :: "+momdate+" shift :: "+shift+" flid :: "+flid+" mode ::"+mode);

});

function sendMailSuccessCalBk(result){
alert(result.msg);
}
   
   
    jQuery('#chkMomsOthers').click(function() {
   
    //alert(" Others "+jQuery('#chkkzbnOthers').val());
   
    if(jQuery("#chkMomsOthers").is(':checked')== true){
    jQuery('#chkMomsOthers').val('Y');
    var sat = jQuery('#chkMomsOthers:checked').val();
    jQuery("#cmbMomsEmployee").combobox('setValue',"");
othersClickAction(sat);
    }else if(jQuery("#chkMomsOthers").is(':checked')== false){
        jQuery('#chkMomsOthers').val('N');
        var sat = jQuery('#chkMomsOthers').val();
        jQuery("#cmbMomsEmployee").combobox('setValue',"");
othersClickAction(sat);
}
   
   
    var MstKeyid=jQuery('#txtMomsKeyid').val();
    var meetingtype=getFieldValue('cmbMomsMeetingtype');

    if(MstKeyid.trim().length>0){
    	
if(meetingtype=="D"||meetingtype=="PD"||meetingtype=="P"||meetingtype=="O"||meetingtype=="CEC"||meetingtype=="DEC"||meetingtype=="FIP"){
//disableField("frmMom", "cmbMomsShiftid");
}else if(meetingtype=="J"){
enableFields("cmbMomsShiftid");
}
    }
   
     
   
});
    function fnFillAttendanceGrid() {
    	   
        var flid =jQuery("#frmMom input[id='flid']").val();
        //alert(" flid :: "+flid);
        var locationId = jQuery("#frmMom input[id='location']").val();
       var cellId = jQuery("#frmMom input[id='cell']").val();
    var momdate = getFieldValue("dteMomsDate", "frmMom");
    var mstkeyid=jQuery('#txtMomsKeyid').val();
    var keyid=jQuery('#txtMomsKeyid').val();
        var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
        var Mettingtype=getFieldValue('cmbMomsMeetingtype');
        var Type=jQuery('#hdntype').val();
    var Pillarid=getFieldValue("cmbMomsPillarid", "frmMom");
    var pillargroup=getFieldValue("cmbMomsPillargroup", "frmMom");
    var shift= getFieldValue("cmbMomsShiftid");
    var recall=jQuery('#hdnrecall').val();
    //alert("Mettingtype>>>"+Mettingtype);
    //var dataString="";
    //changes by priyanka on 04.05.2026
    var menumode = jQuery('#hdnMenuMode').val();
    var modeval = "";

    if (menumode !== undefined && menumode !== null && menumode.trim() !== "") {
        modeval = menumode;
       // jQuery('#hdnpillarModeValue').val(modeval);
    }
    //changes by priyanka on 04.05.2026

    if(Mettingtype=="J"||Type=="JH")
      {
        processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
     
      }else if(Mettingtype=="D"||Type=="Dmt")
      {
      //alert("locationId"+locationId);
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
     
      }else if(Mettingtype=="FIP")
      {
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+jQuery('#hdnMomsRefdocid').val()+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      }
      else if(Mettingtype=="PD"||Type=="Production")
      {
      //alert(123445);
         //dataString="?&roleid=123&meetingType=PRODUCTION&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid;
         //alert(" dataString :: "+dataString);
         
         //processGridnew("MoMeetingAtt_getCol.mom",dataString, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      }
      else if(Mettingtype=="O"||Type=="Others")
      {
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      }
     /*   else if(Mettingtype=="DEC"||Mettingtype=="CEC")
      {
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&PILLAR="+Mettingtype+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      setFieldValue('cmbMomsPillarid',"TGT007");
      }  */
      else if(Mettingtype=="DEC"||Mettingtype=="CEC") //added this line - momchange-8th-MAY 
      {
    	  setFieldValue('cmbMomsPillarid',"TGT007");
    	  
    	  var pillarIdCEC = getFieldValue("cmbMomsPillarid", "frmMom");
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&PILLAR="+Mettingtype+"&shift="+shift+"&recall="+recall+"&mode="+modeval+"&pillarid="+pillarIdCEC, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      
      }//added this line - momchange-8th-MAY 
      else if(meetingType=="P")
      {
    /* alert("inside the pillar meeting");
    alert("Pillarid"+Pillarid);
      alert("flid"+flid);
      alert("pillar group "+pillargroup); */
      
      var dsPillar = "?q=2&roleid="+Pillarid+"&PILLAR=PILLARS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval;
      if(pillargroup && pillargroup.trim() !== "" )
      {
    	  dsPillar += "&pillargroup=" + pillargroup;
    	  
      }
      setTimeout(function(){
            //processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLARS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
            
    	  processGridnew("MoMeetingAtt_input.mom",dsPillar, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      },1000);
      }
        
      else if(Mettingtype=="UMC")
      {
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=UMC"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      }
      else if(Mettingtype=="OGM")
      {
      processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OGM"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall+"&mode="+modeval, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
      }
      }
   
    /* function fnFillAttendanceGrid() {
   
    var flid =jQuery("#frmMom input[id='flid']").val();
    //alert(" flid :: "+flid);
    var locationId = jQuery("#frmMom input[id='location']").val();
   var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var mstkeyid=jQuery('#txtMomsKeyid').val();
var keyid=jQuery('#txtMomsKeyid').val();
    var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
    var Mettingtype=getFieldValue('cmbMomsMeetingtype');
    var Type=jQuery('#hdntype').val();
var Pillarid=getFieldValue("cmbMomsPillarid", "frmMom");
var pillargroup=getFieldValue("cmbMomsPillargroup", "frmMom");
var shift= getFieldValue("cmbMomsShiftid");
var recall=jQuery('#hdnrecall').val();
//alert("Mettingtype>>>"+Mettingtype);
//var dataString="";

if(Mettingtype=="J"||Type=="JH")
  {
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 
  }else if(Mettingtype=="D"||Type=="Dmt")
  {
  //alert("locationId"+locationId);
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 
  }else if(Mettingtype=="FIP")
  {
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+jQuery('#hdnMomsRefdocid').val()+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  }
  else if(Mettingtype=="PD"||Type=="Production")
  {
  //alert(123445);
     //dataString="?&roleid=123&meetingType=PRODUCTION&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid;
     //alert(" dataString :: "+dataString);
     
     //processGridnew("MoMeetingAtt_getCol.mom",dataString, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  }
  else if(Mettingtype=="O"||Type=="Others")
  {
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  }
  else if(Mettingtype=="DEC"||Mettingtype=="CEC")
  {
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&PILLAR="+Mettingtype+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  setFieldValue('cmbMomsPillarid',"TGT007");
  }
  else if(meetingType=="P")
  {

  
  var dsPillar = "?q=2&roleid="+Pillarid+"&PILLAR=PILLARS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall;
  if(pillargroup && pillargroup.trim() !== "" )
  {
	  dsPillar += "&pillargroup=" + pillargroup;
	  
  }
  setTimeout(function(){
        //processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLARS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
        
	  processGridnew("MoMeetingAtt_input.mom",dsPillar, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  },1000);
  }
    
  else if(Mettingtype=="UMC")
  {
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=UMC"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  }
  else if(Mettingtype=="OGM")
  {
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OGM"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  }
  } */
    //**********************************************************Addition By Swetha**************************************//
    
   function fillDetailGrid() {

    var keyid    = jQuery('#txtMomsKeyid').val();
/* //    var MeetingNo =jQuery('#txtMomsMeetingno').val();
	var momKeyId = jQuery('#txtMomsKeyid').val();
	var mkeyid = jQuery("#txtMomsKeyid").val();
	var meetingNo = jQuery('#txtMomsMeetingno').val();
	console.log("momkeyid "+mkeyid+"meeting no"+meetingNo);
	var keyid = (momKeyId && momKeyId.trim() !== "") ? momKeyId : meetingNo; */
    
    var flid     = jQuery("#frmMom input[id='flid']").val();
    var momdate  = getFieldValue("dteMomsDate", "frmMom");
    var shift    = getFieldValue("cmbMomsShiftid");
    var Type     = jQuery('#hdntype').val();
	var glbType = jQuery('#hdntype').val();
    var pillarid = getFieldValue("cmbMomsPillarid", "frmMom");
    var mode     = jQuery("#frmMom input[id=mode]").val();
    var recall   = jQuery('#hdnrecall').val();
    var hdndte   = jQuery("#hdnmomdate").val();
	//alert("keyid length "+keyid.trim().length > 0);
	var MenuMode = jQuery('#hdnMenuMode').val();

    if(mode == "view" && keyid.trim().length > 0) {
        processGridnew("MoMeetingMom_input.mom", "?q=2&keyid="+keyid+"&type="+glbType+"&mode=view", "momGrid", "pagermom", "", "", "", "loadCompleteAction");

    } else if(keyid && keyid.trim().length > 0) {
        processGridnew("MoMeetingMom_input.mom", "?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom", "", "", "", "loadCompleteAction");

    } else 
	{
    	
    	  
    		  var ds =  "?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType;
        	  if(pillarid && pillarid.trim() !== "" )
        	  {
        		  ds += "&pillarid=" + pillarid;
        		  
        	  }
    		  processGridnew("MoMeetingMom_input.mom",ds, "momGrid", "pagermom", "", "", "", "loadCompleteAction");	  
    	  
        
    }
}
  //**********************************************************Addition By Swetha**************************************//

   

    function hideProductionNotRelatedFields() {
    jQuery('#pillarlblid').hide();
    jQuery('#btnjhActivity').hide();
    jQuery('#spnPillar').hide();
    jQuery('#spnPillargroup').hide();
    jQuery('#pillargrouplblid').hide();
        jQuery('#MeetingTitle').hide();
    jQuery('#lblMeetingNumber').css('padding-left',0);
    jQuery('#txtMomsMeetingtitle').hide();
    jQuery('#lblShift').hide();
    jQuery('#spnShift').hide();
    jQuery('#SafetyTalk').hide();
    jQuery('#txtMomsSafetytalk').hide();
    jQuery('#lblRemarks').hide();
    jQuery('#spnMomsRemarks').hide();
    }
   
function othersClickAction(sat)
{
var cellId = jQuery("#frmMom input[id='cell']").val();
var flid = jQuery("#frmMom input[id='flid']").val();
var locationId = jQuery("#frmMom input[id='location']").val();
var roleId =getFieldValue('cmbMomsRole');

if(sat=="Y")
{
reloadCombo("frmMom","cmbMomsEmployee","rolebasedemployee.mom?&Others=Y&roleId="+roleId+"&locnId="+locationId);

}
else
{  
reloadCombo("frmMom","cmbMomsEmployee","rolebasedemployee.mom?&roleId="+roleId+"&flid="+flid);
}
}




 function disableForm(){
    disableField("frmMom", "dteMomsDate");
disableField("frmMom","cmbMomsShiftid");
disableField("frmMom","cmbMomsMeetingtype");
disableField("frmMom","cmbdEmployee");
disableField("frmMom","btnAttsave");
disableField("frmMom","btnjhActivity");
disableField("frmMom","btnaddOthers");
disableField("frmMom","btnDelete");

disableField("frmMom","btnAddNew");
disableField("frmMom","btnAddEmployee");
disableField("frmMom","cmbMomsRole");
disableField("frmMom","cmbMomsEmployee");
readOnlyFields("txtMomsAgenda");

readOnlyFields("txtMomsSafetytalk");
readOnlyFields("txtMomsRemarks");
disableField("frmMom","txtMomsMeetingtitle");
disableField("frmMom","chkMomsIsmessageboard");
disableField("frmMom","chkMomsIsmeetinghappen");

 }
   
 function addRowEmployee(row,Employee,empCode,Employeetext, roleCode){
 
  //alert(1);
     if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) {
               
if ( row == null || row == '' || parseInt(row) <= 0) {
var j=0;
var emptyItem =[{EmployeeId:Employee,EmployeeCode:empCode,Employee:Employeetext,Attendance:'',AttendanceID:' ',Role:rolecodeemp}];

      jQuery("#attandanceGrid").jqGrid('addRowData',j, emptyItem[0]);
          var k =j+1;
          jQuery("#hdnVal").val(k);
}
   else {
       var empExists = false;
       for(var i=0;i<row.length;i++){
        var rowData = jQuery("#attandanceGrid").jqGrid('getRowData',3+i);
        var empid=rowData.EmployeeId;
        if(Employee==empid){
        popupCommonErrorMsg(" This Record is already exist in grid .. ");
        return false;
        }
       }
       
       if (empExists == false) {
       
   lastRow = row[row.length-1];
  var rolecodeemp=jQuery('#hdnroledata').val();
 
  if(rolecodeemp.trim().length>0)
  var emptyItem =[{EmployeeId:Employee,EmployeeCode:empCode,Employee:Employeetext,Attendance:'',AttendanceID:' ',Role:rolecodeemp}];
  else
  var emptyItem =[{EmployeeId:Employee,EmployeeCode:empCode,Employee:Employeetext,Attendance:'',AttendanceID:' ',Role:rolecodeemp}];
 
                    if(Employeetext.trim().length>0){
            jQuery("#attandanceGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
            jQuery('#hdnaddemp').val('C');
                       chkboxAttCheck((parseInt(lastRow))+1);
                    }

        }
       }
  }
   }

    function addRowEmployeeMultiple(row,Employee,Employeetext, empCode,roleCode){
   
        if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) {//alert(" Inside Validation ");
                 
  if ( row == null || row == '' || parseInt(row) <= 0) {
    var emptyItem =[{EmployeeId:"",Employee:" ",EmployeeCode:"",Role:" ",Attendance:"",AttendanceID:" "}];
        jQuery("#attandanceGrid").jqGrid('addRowData',j, emptyItem[0]);
            var k =j+1;
            jQuery("#hdnVal").val(k);
     }
         
    else {
         var empExists = false;
         for(var i=0;i<row.length;i++){
        var rowData = jQuery("#attandanceGrid").jqGrid('getRowData',3+i);
        var empid=rowData.EmployeeId;
        //jQuery('#hdnnewaddemp').val(empid);
        if(Employee==empid){
        popupCommonErrorMsg(" This Record is already exist in grid .. ");
        return false;
        }
         }
       if (empExists == false) {
  lastRow = row[row.length-1];
               var emptyItem =[{EmployeeId:Employee,Employee:Employeetext,EmployeeCode:empCode,Role:roleCode,Attendance:' ',AttendanceID:''}];
         jQuery("#attandanceGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
        }
         }
    }
    }
       
    function fillcurrecntshift(){
   
    var serverTime = srvTime();
var currentTime = new Date(serverTime);
var hours = currentTime.getHours();
var minutes = currentTime.getMinutes();

if (minutes < 10){
minutes = "0" + minutes;
}

if (hours < 10){
hours = "0" + hours;
}

/* var frmtime=hours + ':' + minutes;
var fromTime=frmtime;

if(fromTime.indexOf(':') == 1)
fromTime = '0'+fromTime;*/

var factId = "";
var dataString = '?q=2&sectId='+jQuery("#frmMom input[id='section']").val();
   dataString += '&cellId='+jQuery("#frmMom input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
 //  dataString += '&cellId='+jQuery("#frmMom input[id='cell']").val()+'&fromTime='+fromTime;
//alert("datastring"+dataString);
processAjaxCalls('txt_shift.brdn',dataString,'getMOMShift','getMOMShiftErr');

    }

    function recallsuccessCallBack(result){//alert(" Checking Now :: "+result[0][0]);
setFieldValue('hdnAttRole',result[0][0]);
}
   

function  getMOMShift(record)
{
//alert("record.shift"+record.shift);
var mstKeyid=jQuery('#txtMomsKeyid').val();
if(mstKeyid.trim().length<=0)
  jQuery('#cmbMomsShiftid').combobox('setValue',record.shift);
}
   
   
/*  function momGrid_selectRow(id){//alert(" Inside "+id);

   
var MomPillarId =  getFieldValue("cmbMomsPillarid");
//alert(" MomPillarId :: "+MomPillarId);
var MomPillarIdGrid =  getFieldValue("cmbMomdPillar_momGrid_"+id);
//alert(" MomPillarIdGrid :: "+MomPillarIdGrid);

var MomPillarid = (MomPillarId && MomPillarId.trim() !== "") ? MomPillarId : MomPillarIdGrid;
setFieldValue("cmbMomdPillar_momGrid_"+id,MomPillarid);


jQuery("#Actplnbutton_momGrid_"+id).prop('maxlength','0');
jQuery("#Actplnbutton_momGrid_"+id).val("");
jQuery("#kpibtn_momGrid_"+id).prop('maxlength','0');
jQuery("#kpibtn_momGrid_"+id).val("");

//return "MomPillarId="+MomPillarId;
return "MomPillarId="+MomPillarid;


    } */
 
 
 function momGrid_selectRow(id){//alert(" Inside "+id);

 jQuery('#hdnRowCoId').val(id);//added this line
var MomPillarId =  getFieldValue("cmbMomsPillarid");
//alert(" MomPillarId :: "+MomPillarId);
var MomPillarIdGrid =  getFieldValue("cmbMomdPillar_momGrid_"+id);
//alert(" MomPillarIdGrid :: "+MomPillarIdGrid);

var MomPillarid = (MomPillarId && MomPillarId.trim() !== "") ? MomPillarId : MomPillarIdGrid;
setFieldValue("cmbMomdPillar_momGrid_"+id,MomPillarid);


jQuery("#Actplnbutton_momGrid_"+id).prop('maxlength','0');
jQuery("#Actplnbutton_momGrid_"+id).val("");
jQuery("#kpibtn_momGrid_"+id).prop('maxlength','0');
jQuery("#kpibtn_momGrid_"+id).val("");

//return "MomPillarId="+MomPillarId;
return "MomPillarId="+MomPillarid;


    }

    function chkboxmeetCheck()
{
    var MetingNo =jQuery('#hdnMomsMtngno').val();
   jQuery('#txtMomsMeetingno').val(MetingNo);

   var MetingTitle =jQuery('#hdnMetngtitle').val();
   jQuery('#txtMomsMeetingtitle').val(MetingTitle);

   var MetingType =jQuery('#hdnMomsMtntyp').val();
   jQuery('#cmbMomsMeetingtype').combobox('setValue',MetingType);

   var MetingSafety =jQuery('#hdnMomsMtngsafety').val();
   jQuery('#txtMomsSafetytalk').val(MetingSafety);

   jQuery('#err_txtMomsMeetingno').show();
   jQuery('#err_txtMomsMeetingtitle').show();
   jQuery('#err_cmbMomsMeetingtype').show();
   jQuery('#err_txtMomsSafetytalk').show();
   
enableFields("dteMomsDate");
enableFields("cmbMomsShiftid");
enableFields("txtMomsRemarks");
//enableFields("txtMomsMeetingno");
enableFields("txtMomsMeetingtitle");
 //enableFields("cmbMomsMeetingtype");//cmbMomsMeetingtype
enableFields("txtMomsSafetytalk");
enableFields("txtMomsRemarks");
jQuery('#MeetingType').addClass("mandatory-lbl");
   //jQuery('#MeetingNumber').addClass("mandatory-lbl");
   //jQuery('#MeetingTitle').addClass("mandatory-lbl");
   //jQuery('#SafetyTalk').addClass("mandatory-lbl");
}
    function chkboxmeetUnCheck()
{
    jQuery('#txtMomsMeetingno').val(" ");
   jQuery('#txtMomsMeetingtitle').val(" ");
   jQuery('#cmbMomsMeetingtype').val(" ");
   jQuery('#txtMomsSafetytalk').val(" ");
   setFieldValue("cmbMomsMeetingtype"," ","frmMom");
   jQuery('#MeetingType').removeClass("mandatory-lbl");
   jQuery('#MeetingNumber').removeClass("mandatory-lbl");
   //jQuery('#MeetingTitle').removeClass("mandatory-lbl");
   //jQuery('#SafetyTalk').removeClass("mandatory-lbl");
   
   var errmsgfun =jQuery('#err_MomAttfunLocation').html();
   var errmsgshift = jQuery('#err_cmbMomsShiftid').html();
   var errmsgmetno = jQuery('#err_txtMomsMeetingno').html();
   var errmsgmettitle = jQuery('#err_txtMomsMeetingtitle').html();
   var errmsgmettype = jQuery('#err_cmbMomsMeetingtype').html();
   var errmsgmettalk = jQuery('#err_txtMomsSafetytalk').html();
   
        clearValidationErrorMessages("frmMom","err_txtMomsMeetingno");

        jQuery('#err_MomAttfunLocation').html(errmsgfun);
   jQuery('#err_cmbMomsShiftid').html(errmsgshift);
   
   jQuery('#err_txtMomsMeetingno').html(errmsgmetno);
   jQuery('#err_txtMomsMeetingtitle').html(errmsgmettitle);
   jQuery('#err_cmbMomsMeetingtype').html(errmsgmettype);
   jQuery('#err_txtMomsSafetytalk').html(errmsgmettalk);
   
   jQuery('#err_MomAttfunLocation').show();
   jQuery('#err_cmbMomsShiftid').show();

   
 
   enableFields("dteMomsDate");
enableFields("cmbMomsShiftid");
enableFields("txtMomsRemarks");
readOnlyFields("txtMomsMeetingno");
readOnlyFields("txtMomsMeetingtitle");
readOnlyFields("cmbMomsMeetingtype");
readOnlyFields("txtMomsSafetytalk");
readOnlyFields("txtMomsRemarks");
}
function frmMom_deleteSuccessCallback(result)
{
        alert(result.successData.msg);
clearForm('frmMom');
jQuery('#momGrid').trigger("reloadGrid");
jQuery('#attandanceGrid').trigger("reloadGrid");
}
function formatDate(date){
	let newDate = new Date(date);
		   

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
				            months[newDate.getMonth()] + '-' +
				            newDate.getFullYear();
	return formatted;
 }
/*    function dteMomsDate_onSelect(date)
{
  //var momdate = getFieldValue("dteMomsDate", "frmMom");
  var momdate = formatDate(date);
  var flid = jQuery("#frmMom input[id='flid']").val();
  jQuery("#hdnmomdate").val(momdate);
  setFieldValue("dteMomsDate", momdate, "frmMom");
var currentDate = getServerDateTime();
if( date > currentDate){
jQuery('#dteMomsDate').datebox('clear');
jQuery('#txtMomsAgenda').val(' ');
showValidationErrorMsg('dteMomsDate','Should Not Exceed Current Date');
return false;
}
else
clearValidationErrorMsg('dteMomsDate');

fnsetvalues();
processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");
fnRecall();
} */
   
   function dteMomsDate_onSelect(date)
   {
     //var momdate = getFieldValue("dteMomsDate", "frmMom");
     var momdate = formatDate(date);
     var checkDate = new Date(date);
     var today = new Date(getServerDateTime());

     var thirtyDays = new Date(today);
     thirtyDays.setDate(thirtyDays.getDate() - 30);

     console.log(checkDate, "momdate");
     console.log(thirtyDays, "thirtyDays");
     if (checkDate < thirtyDays)
     {
         alert('Cannot Create or Update - View in view menu');
         //popupCommonErrorMsg("Enter Discussion Details");
         var dateval=jQuery("#dteMomsDate").datebox('getValue');
         setTimeout(function() {
        	 jQuery("#dteMomsDate").datebox('setValue', dateval);
        },100);
         
         //setFieldValue("dteMomsDate", dateval, "frmMom");
         console.log(dateval+"dateval");
      
         return false;
     }
     //var dateval=jQuery("#dteMomsDate").datebox('getValue');
    var flid = jQuery("#frmMom input[id='flid']").val();
     jQuery("#hdnmomdate").val(momdate);
     setFieldValue("dteMomsDate", momdate, "frmMom");
   var currentDate = getServerDateTime();
   if( date > currentDate){
   jQuery('#dteMomsDate').datebox('clear');
   jQuery('#txtMomsAgenda').val(' ');
   showValidationErrorMsg('dteMomsDate','Should Not Exceed Current Date');
   return false;
   }
   else
   clearValidationErrorMsg('dteMomsDate');

   fnsetvalues();
   processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");
   fnRecall();


   }

   function frmMomcmbMomsShiftid_onSelect(date)
{
 console.log(date);
 setFieldValue("cmbMomsShiftid",date.id);
  jQuery("#hdnmomdate").val("date");
  fnRecall();
}

   
 function frmMomcmbMomsRole_onSelect(record)
 {

     //alert(record.id);//
     var flid =jQuery("#frmMom input[id='flid']").val();
var role =getFieldValue('cmbMomsRole');

reloadCombo("frmMom","cmbMomsEmployee","rolebasedemployee.mom?&roleId="+record.id+"&flid="+flid);

jQuery('input:checkbox[name=chkMomsOthers]').attr('checked',false);
jQuery('#cmbMomsEmployee').combobox('clear');
 }

/*  function frmMomcmbMomsPillarid_onSelect(record)
 {
	 //alert(record+" record");
	// alert(record.id+" record id");
var flid =jQuery("#frmMom input[id='flid']").val();

var pillarid =getFieldValue('cmbMomsPillarid');

//alert(flid+" :: pillarid :: "+pillarid);
reloadCombo("frmMom","cmbMomsPillargroup","pillargroup.mom?&pillarid="+pillarid+"&flid="+flid+"&pillargrp=pillargrp");

} */

 /* function frmMomcmbMomsPillargroup_onSelect(record)
 {
var flid =jQuery("#frmMom input[id='flid']").val();
//var pillarid =getFieldValue('cmbMomsPillarid');
//alert(record+" record");
//alert(record.id+" record id");
//alert("pillar group on select is called");
var pillarid = getFieldValue('cmbMomsPillarid');
var type = getFieldValue("cmbMomsMeetingtype", "frmMom");
//alert("type>>>"+type);
//var pillargroup =getFieldValue('cmbMomsPillargroup');
var pillargroup = record.id;
var momdate = getFieldValue("dteMomsDate", "frmMom");
var locationId = jQuery("#frmMom input[id='location']").val();
     var cellId = jQuery("#frmMom input[id='cell']").val();
     var keyid=jQuery('#txtMomsKeyid').val();
     var dataString;
   
     if(type=="UMC")
    {
   // alert("calling on select1")  
    dataString= '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
         dataString += "&PILLAR=UMC"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
     
    }
     else  if(type=="OGM")
    {
    //alert("calling on select1")  
          dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
         dataString += "&PILLAR=OGM"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
     
    }
     else if(type=="P"){
   // alert("calling on select1" +pillargroup);  
   
    dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
         dataString += "&PILLAR=PILLAR"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
     
     }
   
         
       
      //alert(" dataString :: "+dataString);
       
     //fnRecall();
     //alert("Before Attendanace Grid called");
     processGridnew("MoMeetingAtt_input.mom",dataString,"attandanceGrid","pageratt","","","","loadCompleteAttendanceGrid1");
    // alert("calling on select1")  
} */
function frmMomcmbMomsPillargroup_onSelect(record)
{
var flid =jQuery("#frmMom input[id='flid']").val();
//var pillarid =getFieldValue('cmbMomsPillarid');
//alert(record+" record");
//alert(record.id+" record id");
//alert("pillar group on select is called");
var pillarid = getFieldValue('cmbMomsPillarid');
var type = getFieldValue("cmbMomsMeetingtype", "frmMom");
//alert("type>>>"+type);
//var pillargroup =getFieldValue('cmbMomsPillargroup');
var pillargroup = record.id;
var momdate = getFieldValue("dteMomsDate", "frmMom");
var locationId = jQuery("#frmMom input[id='location']").val();
    var cellId = jQuery("#frmMom input[id='cell']").val();
    var keyid=jQuery('#txtMomsKeyid').val();
    var dataString;
  //changes by priyanka on 04.05.2026
    var menumode = jQuery('#hdnMenuMode').val();
    var modeval = "";

  
    if(type=="UMC")
   {
  // alert("calling on select1")  
   dataString= '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
        dataString += "&PILLAR=UMC"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
    
   }
    else  if(type=="OGM")
   {
   //alert("calling on select1")  
         dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
        dataString += "&PILLAR=OGM"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
    
   }
    else if(type=="P"){
  // alert("calling on select1" +pillargroup);  
  
   dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
        dataString += "&PILLAR=PILLAR"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
    
    }
    else if(type=="DEC"){//added this line - momchange-8th-MAY 
  	  // alert("calling on select1" +pillargroup);  
  	  
  	   dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
  	        dataString += "&PILLAR=DEC"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
  	    
  	    }//added this line - momchange-8th-MAY 
  else if(type=="CEC"){//added this line - momchange-8th-MAY 
  	  // alert("calling on select1" +pillargroup);  
  	  
  	   dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
  	        dataString += "&PILLAR=CEC"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
  	    
  	    }//added this line - momchange-8th-MAY 
  
        
      
     //alert(" dataString :: "+dataString);
      
    //fnRecall();
    //alert("Before Attendanace Grid called");
    processGridnew("MoMeetingAtt_input.mom",dataString,"attandanceGrid","pageratt","","","","loadCompleteAttendanceGrid1");
   // alert("calling on select1")  
}


 function frmMomcmbMomsEmployee_onSelect(record)
 {
     //alert(" Employee Filling "+record.id);
     
processAjaxCalls("MoMeetingFillRole_modify.mom","emplyid="+record.id,"updateRoleSuccess","");
 
 }

 function updateRoleSuccess(result)
 {

	jQuery('#hdnroledata').val(result[0][1]);
 }
 
 
 function frmMomcmbMomsPillarid_onSelect(record)
{
var Pillarid=record.id;
//alert(" Pillar id  :: "+Pillarid);
//alert(" Mettingtype :: "+Mettingtype);
   //jquery('#hdnPillarIds').val(Mettingtype);
   var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
   var flid =jQuery("#frmMom input[id='flid']").val();
  // alert("flid"+flid);
   var locationId = jQuery("#frmMom input[id='location']").val();
  // alert("locationId"+locationId);
   var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var dateval=jQuery("#dteMomsDate").datebox('getValue');
//alert("momdate::"+momdate);
//alert("dateval::"+dateval);
var mstkeyid=jQuery('#txtMomsKeyid').val();
// alert("mstkeyid"+mstkeyid);
var shift= getFieldValue("cmbMomsShiftid"); 
var recall=jQuery('#hdnrecall').val();


//processAjaxCalls("MoMeetingPillaridRecall.mom","&flid="+flid+"&date="+momdate+"&shift="+Pillarid,"PillaridRecallSuccess","");
processAjaxCalls("MoMeetingPillaridRecall.mom","&flid="+flid+"&date="+momdate+"&shift="+Pillarid+"&type="+meetingType,"PillaridRecallSuccess","");//added this - momchange-8th-MAY 

//fillComboBox("frmMom","cmbMomsPillargroup","pillargroup.mom?pillarid="+Pillarid);
fillComboBox("frmMom","cmbMomsPillargroup","pillargroup.mom?pillarid="+Pillarid+"&locationId="+locationId);

/*if(mstkeyid.trim().length<=0 || recall.trim().length>0){
//alert(456);
   if(Pillarid=="TGT001"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   }else if(Pillarid=="TGT002"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT003"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT003"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT004"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT005"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT006"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT007"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT008"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } }
*/
/* else{
alert("isndie the else");
//processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+mstkeyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");

   
} */
}
 
/*  function PillaridRecallSuccess(result)
 {

var keyid=result.momkeyid;
//alert("keyid"+keyid);
var Pillarid=result.pillarid;
var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
var flid =jQuery("#frmMom input[id='flid']").val();
//  alert("flid"+flid);
   var locationId = jQuery("#frmMom input[id='location']").val();
  // alert("locationId"+locationId);
   var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
//var mstkeyid=jQuery('#txtMomsKeyid').val();
var mstkeyid=jQuery('#txtMomsKeyid').val();
//alert(mstkeyid);
var shift= getFieldValue("cmbMomsShiftid"); 
//var pillargrp=getFieldValue("cmbMomsPillargroup", "frmMom");

//var pillargroup = jQuery("#hdnpillargrpval").val(); 
//alert("pillargrp PILLAR ID SUCCESSCALLBACK"+pillargroup);
var recall=jQuery('#hdnrecall').val();
if(keyid!=null)
{
var mstkeyid=jQuery('#txtMomsKeyid').val(keyid);
jQuery("#txtMomsMeetingno").val(keyid);


//alert(" mstkeyid :: "+mstkeyid.length);
var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+Pillarid;
     processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData",""); //Added this line - Swetha
     	

processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction"); //Added this line -Swetha
 


}
else{
// alert("isnide the else");
jQuery('#txtMomsKeyid').val('');
jQuery("#txtMomsMeetingno").val('');
jQuery("#txtMomsAgenda").val('');
jQuery("#txtMomsSafetytalk").val('');
jQuery("#txtMomsRemarks").val('');
jQuery("#txtMomsMeetingtitle").val('');//added this

//if(mstkeyid.trim().length<=0 || recall.trim().length>0){
//alert(456);
 processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction"); //Added this line -Swetha

 var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+Pillarid;
      processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData",""); //Added this line - Swetha
   if(Pillarid=="TGT001"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   }else if(Pillarid=="TGT002"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT003"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT003"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT004"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT005"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT006"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT007"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT008"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
   }
   //}
}

 } */
 
 function PillaridRecallSuccess(result)
 {

var keyid=result.momkeyid;
//alert("keyid"+keyid);
var Pillarid=result.pillarid;
var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
var flid =jQuery("#frmMom input[id='flid']").val();
//  alert("flid"+flid);
   var locationId = jQuery("#frmMom input[id='location']").val();
  // alert("locationId"+locationId);
   var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
//var mstkeyid=jQuery('#txtMomsKeyid').val();
var mstkeyid=jQuery('#txtMomsKeyid').val();
//alert(mstkeyid);
var shift= getFieldValue("cmbMomsShiftid"); 
//var pillargrp=getFieldValue("cmbMomsPillargroup", "frmMom");

//var pillargroup = jQuery("#hdnpillargrpval").val(); 
//alert("pillargrp PILLAR ID SUCCESSCALLBACK"+pillargroup);
var recall=jQuery('#hdnrecall').val();
if(keyid!=null)
{
var mstkeyid=jQuery('#txtMomsKeyid').val(keyid);
jQuery("#txtMomsMeetingno").val(keyid);


//alert(" mstkeyid :: "+mstkeyid.length);
var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+Pillarid;
     processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData",""); //Added this line - Swetha
     	

processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction"); //Added this line -Swetha
 
/*  var pillargroup=getFieldValue("cmbMomsPillargroup", "frmMom");
 alert("pillargrp PILLAR ID SUCCESSCALLBACK"+pillargroup);
 var dataPillar = "?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+encodeURIComponent(keyid)+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId;
 if(pillargroup && pillargroup.trim() !== "" )
 {
 	dataPillar += "&pillargroup=" + pillargroup;
 	  
 }
  processGridnew("MoMeetingAtt_input.mom",dataPillar, "attandanceGrid", "pageratt"); */

}
else{
// alert("isnide the else");
jQuery('#txtMomsKeyid').val('');
jQuery("#txtMomsMeetingno").val('');
jQuery("#txtMomsAgenda").val('');
jQuery("#txtMomsSafetytalk").val('');
jQuery("#txtMomsRemarks").val('');
jQuery("#txtMomsMeetingtitle").val('');//added this

//if(mstkeyid.trim().length<=0 || recall.trim().length>0){
//alert(456);
 processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction"); //Added this line -Swetha

 var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+Pillarid;
      processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData",""); //Added this line - Swetha
    //changes by priyanka on 04.05.2026
    var menumode = jQuery('#hdnMenuMode').val();
      //alert("menumode for pillar"+menumode);
      var modeval = "";
      
      if (menumode !== undefined && menumode !== null && menumode.trim() !== "") {
          modeval = menumode;
      }

      
   if(Pillarid=="TGT001"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   }else if(Pillarid=="TGT002"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT003"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT003"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT004"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT005"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT006"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT007"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   } else if(Pillarid=="TGT008"&& meetingType=="P"){
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeval, "attandanceGrid", "pageratt");
   }
   //}
}

 }

 function meetingemployeetype(id){


var Mettingtype=id;
var flid =jQuery("#frmMom input[id='flid']").val();
var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var keyid=jQuery('#txtMomsKeyid').val();
//alert(" :: keyid :: "+keyid);

   if(Mettingtype=="P"){
    jQuery('#pillarlblid').addClass("mandatory-lbl");
    enableFields("cmbMomsPillarid");
}else{
    jQuery('#pillarlblid').removeClass("mandatory-lbl");
readOnlyFields("cmbMomsPillarid");
clearField("cmbMomsPillarid");
}
   
if(Mettingtype=="J")
{
 jQuery('#SafetyTalk').addClass("mandatory-lbl");
}else
 jQuery('#SafetyTalk').removeClass("mandatory-lbl");
 if(Mettingtype=="D"){
    meetingtype();
 }

if(keyid.trim().length<=0){
 if(Mettingtype=="J")
{
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");

}else if(Mettingtype=="D")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");

}else if(Mettingtype=="FIP")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
}
else if(Mettingtype=="PD")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
}
else if(Mettingtype=="O")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
}else if(Mettingtype=="DEC"||Mettingtype=="CEC"||Mettingtype=="O")
  {
  processGridnew("MoMeetingAtt_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
  }
 }else
 {
 
 if(Mettingtype=="J")
{
  processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");

}else if(Mettingtype=="D")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");

}else if(Mettingtype=="FIP")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
}
else if(Mettingtype=="PD")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
}
else if(Mettingtype=="O")
{
processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
}

 }
 }              
 function frmMomcmbMomsMeetingtype_onSelect(record)
{

meetingemployeetype(record.id);
 
var meetingtype=record.id;
 
if(meetingtype=="D"||meetingtype=="PD"||meetingtype=="P"||meetingtype=="O"||meetingtype=="CEC"||meetingtype=="DEC"||meetingtype=="FIP"){
disableField("frmMom", "cmbMomsShiftid");
}else if(meetingtype=="J"){
enableFields("cmbMomsShiftid");
}

}
 
 function meetingtype()
{//alert(1);
//alert(" Type :: "+Mettingtype);
       var rowIds = jQuery("#momGrid").jqGrid('getDataIDs');

       if(rowIds==''||rowIds==0){
               rowIds=rowIds+1;
       }
            for (var i=0;i<rowIds.length;i++){

                 var tdcol = jQuery("#momGrid" + ' tr[id='+ rowIds[i] +"]").find('td[aria-describedby="momGrid_cmbMomdDiscussionType"]');  
tdcol.attr('Mandatory',true);

   }  
//}

}


function btnfilemgr_click()
{
      var keyid = jQuery('#txtMomsKeyid').val();
     
      if(keyid.trim().length<=0){
      saveForm('frmMom','MoMeetingForm_save.mom?filemanger=filemanger');
      }else if(keyid != null && keyid != ''){
      var mode = jQuery("#frmMom input[id=mode]").val();
      fileManagerPopUp(keyid,"MOM","","","",mode);
}
}

/* function frmMom_FuntLocHierarchy_SuccessCallBack(result)
{  
	
	jQuery('#hdnrecall').val("Y");
var Type=jQuery('#hdntype').val();
if (Type =='Pillar') {
readOnlyFields("cmbFunlocLocation");
readOnlyFields("cmbFunlocCompany");
jQuery('#hdnrecall').val("N");
}

setFunctionalLocWidth('frmMom','600px');
var mkeyid = jQuery("#txtMomsKeyid").val();
var cellId = jQuery("#frmMom input[id='location']").val();
var flid =jQuery("#frmMom input[id='flid']").val();
//alert(flid);
var momdate = getFieldValue("dteMomsDate", "frmMom");
//processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid","",false);
var locationId = jQuery("#frmMom input[id='location']").val();
//console.log("Location Id"+locationId);
processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");

if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
{
   reloadCombo("frmMom","cmbMomsEmployee","employee.commonFilter?&flid="+result.flId);
//reloadCombo("frmMom","cmbMomsRole","roleMst.commonFilter?&flid="+result.flId);
}

var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");

        if(mode=="view" && mode.trim().length>0){
   jQuery('#frmMom').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:10%;"> </div>');
            jQuery("#chkSelectAll").attr("readonly",true);
        }    

        fnRecall();
        //jQuery('#frmMom').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
     
   //if(Mettingtype.trim().length<=0)
    //fnFillAttendanceGrid();
   // viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
   //else if (Employee=="CEC" || Employee=="O"|| Employee=="DEC" || Employee=="DEC" )
   // viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
  

      //alert(" Mettingtype :: "+Mettingtype+" mkeyid "+mkeyid);  
    //     if((Mettingtype=="CEC" && mkeyid.trim().length>0)||(Mettingtype=="DEC" && mkeyid.trim().length>0)||(Mettingtype=="P" && mkeyid.trim().length>0)){
    //frmMomcmbMomsPillargroup_onSelect();
   //     }
        
       
    }

function fnRecall(){

        var recall=jQuery('#hdnrecall').val();
        //alert("recall value "+recall);
        glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();
var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");
var flid =jQuery("#frmMom input[id='flid']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var hdndte=jQuery("#hdnmomdate").val();
var pillarid =getFieldValue('cmbMomsPillarid');
//processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
//alert(" Outside :: ");
if (recall.trim().length>0 && recall=="Y"){//alert("1");
            processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
           
            // if(hdndte.trim().length>0){//alert("12");
         //  var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
         //  processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
         //   }
            //navigateToNextForm("MoMeetingForm_input.mom"+ds ,momType+" Minutes of Meeting");
             
             //alert(" Keyid :: 1 "+jQuery("#txtMomsKeyid").val());
             setTimeout(function() {
          fnFillAttendanceGrid();
     },300);
             
             fnsetvalues();
         }

if(hdndte.trim().length>0 && recall.trim().length<=0 && recall!="Y")
{
	//alert("recall "+recall);
	 var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType;
       processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
	processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");
      
       
       //alert(" Keyid :: 2 "+jQuery("#txtMomsKeyid").val());
       setTimeout(function() {
        fnFillAttendanceGrid();
   },300);
       
       fnsetvalues();
       }else{
      var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
           processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
           fnsetvalues();
       }    

} */

//CHECK

/* function frmMom_FuntLocHierarchy_SuccessCallBack(result)
{  
	console.log(result);
	
	var mode = jQuery('#hdnMenuMode').val();
	// jQuery('#hdnrecall').val("Y");
	// if(mode == "modify")//Added this line to stop recall in modify mode //Change here 16 March
	//{
	//	jQuery('#hdnrecall').val("N");
		
	//}
	 
	
var Type=jQuery('#hdntype').val();
var recall = jQuery('#hdnrecall').val();
var sbuId = result.sbuId;
var pbuId = result.pbuId;
var sectId = result.sectId;
var cellId = result.cellId;
if(mode != "modify"){
if (Type =='Pillar' && sbuId && sbuId.length > 0 && pbuId && pbuId.length > 0) 
{
readOnlyFields("cmbFunlocLocation");
readOnlyFields("cmbFunlocCompany");
//jQuery('#hdnrecall').val("N");

//alert("Sbu Id"+sbuId);
processAjaxCalls("MoMeetingFlid.mom","originalId="+sbuId,"recallingFlidSuccess","");
return;
}

if (Type =='Dmt'&& sectId && sectId.length > 0 && cellId && cellId.length > 0) 
{

//jQuery('#hdnrecall').val("N");

//alert("Sbu Id"+sbuId);
processAjaxCalls("MoMeetingFlid.mom","originalId="+sectId,"recallingFlidSuccess","");
return;
}
}


//if(Type =='Pillar')//to stop recall when chose the functional location and recall only if it choses pilarr id //Change here 16 March
//{
//	jQuery('#hdnrecall').val("N");
	
//}
 


setFunctionalLocWidth('frmMom','600px');
var mkeyid = jQuery("#txtMomsKeyid").val();
alert(mkeyid+'keyid in flid sucess');
var cellId = jQuery("#frmMom input[id='location']").val();
var flid =jQuery("#frmMom input[id='flid']").val();
//alert(flid);
var momdate = getFieldValue("dteMomsDate", "frmMom");
//processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid","",false);
var locationId = jQuery("#frmMom input[id='location']").val();
//console.log("Location Id"+locationId);
processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");

if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
{
   reloadCombo("frmMom","cmbMomsEmployee","employee.commonFilter?&flid="+result.flId);
//reloadCombo("frmMom","cmbMomsRole","roleMst.commonFilter?&flid="+result.flId);
}

var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");

        if(mode=="view" && mode.trim().length>0){
   jQuery('#frmMom').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:10%;"> </div>');
            jQuery("#chkSelectAll").attr("readonly",true);
        }    

        fnRecall();
        //jQuery('#frmMom').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
     
   //if(Mettingtype.trim().length<=0)
    //fnFillAttendanceGrid();
   // viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
 //else if (Employee=="CEC" || Employee=="O"|| Employee=="DEC" || Employee=="DEC" )
 //   viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
   //

        //alert(" Mettingtype :: "+Mettingtype+" mkeyid "+mkeyid);  
    //     if((Mettingtype=="CEC" && mkeyid.trim().length>0)||(Mettingtype=="DEC" && mkeyid.trim().length>0)||(Mettingtype=="P" && mkeyid.trim().length>0)){
   // frmMomcmbMomsPillargroup_onSelect();
    //    }
   //     
       
    } */
    
    /* function frmMom_FuntLocHierarchy_SuccessCallBack(result)
    {  
    	console.log(result);
    	
    	var mode = jQuery('#hdnMenuMode').val();
    	//alert("mode"+mode);
    	
    	
    var Type=jQuery('#hdntype').val();
    var recall = jQuery('#hdnrecall').val();
    var sbuId = result.sbuId;
    var pbuId = result.pbuId;
    var sectId = result.sectId;
    var cellId = result.cellId;
    if(mode != "modify"){//Changed this Line
    if (Type =='Pillar' && sbuId && sbuId.length > 0 && pbuId && pbuId.length > 0) 
    {
    readOnlyFields("cmbFunlocLocation");
    readOnlyFields("cmbFunlocCompany");
    //jQuery('#hdnrecall').val("N");

    //alert("Sbu Id"+sbuId);
    processAjaxCalls("MoMeetingFlid.mom","originalId="+sbuId,"recallingFlidSuccess","");
    return;
    }

    if (Type =='Dmt'&& sectId && sectId.length > 0 && cellId && cellId.length > 0) 
    {

    //jQuery('#hdnrecall').val("N");

    //alert("Sbu Id"+sbuId);
    processAjaxCalls("MoMeetingFlid.mom","originalId="+sectId,"recallingFlidSuccess","");
    return;
    }
    }//end
   


    setFunctionalLocWidth('frmMom','600px');
    var mkeyid = jQuery("#txtMomsKeyid").val();
    //alert(mkeyid+'keyid in flid sucess');
    var cellId = jQuery("#frmMom input[id='location']").val();
    var flid =jQuery("#frmMom input[id='flid']").val();
    //alert(flid);
    var momdate = getFieldValue("dteMomsDate", "frmMom");
    //processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid","",false);
    var locationId = jQuery("#frmMom input[id='location']").val();
    //console.log("Location Id"+locationId);
    processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");

    if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
    {
       reloadCombo("frmMom","cmbMomsEmployee","employee.commonFilter?&flid="+result.flId);
    //reloadCombo("frmMom","cmbMomsRole","roleMst.commonFilter?&flid="+result.flId);
    }

    var Mettingtype =getFieldValue('cmbMomsMeetingtype');
    var mode = jQuery("#frmMom input[id=mode]").val();
    var shift= getFieldValue("cmbMomsShiftid");

            if(mode=="view" && mode.trim().length>0){
       jQuery('#frmMom').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:10%;"> </div>');
                jQuery("#chkSelectAll").attr("readonly",true);
            }    

            fnRecall();
            //jQuery('#frmMom').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
         
       //if(Mettingtype.trim().length<=0)
        //fnFillAttendanceGrid();
       // viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
          
           
        } */
        
        function frmMom_FuntLocHierarchy_SuccessCallBack(result)
        {  
        	//console.log(result);
        	
        	var mode = jQuery('#hdnMenuMode').val();
        	//alert("mode"+mode);
        	/* jQuery('#hdnrecall').val("Y");
        	 if(mode == "modify")//Added this line to stop recall in modify mode //Change here 16 March
        	{
        		jQuery('#hdnrecall').val("N");
        		
        	}
        	 */
        	
        var Type=jQuery('#hdntype').val();
        var recall = jQuery('#hdnrecall').val();
        var locationId = jQuery("#frmMom input[id='location']").val();
        var sbuId = result.sbuId;
        var pbuId = result.pbuId;
        var sectId = result.sectId;
        var cellId = result.cellId;
        if(mode != "modify"){//Changed this Line
        if (Type =='Pillar' && sbuId && sbuId.length > 0 && pbuId && pbuId.length > 0) 
        {
        readOnlyFields("cmbFunlocLocation");
        readOnlyFields("cmbFunlocCompany");
        //jQuery('#hdnrecall').val("N");

        //alert("Sbu Id"+sbuId);
        processAjaxCalls("MoMeetingFlid.mom","originalId="+sbuId,"recallingFlidSuccess","");
        return;
        }

        if (Type =='Dmt'&& sectId && sectId.length > 0 && cellId && cellId.length > 0) 
        {

        //jQuery('#hdnrecall').val("N");

        //alert("Sbu Id"+sbuId);
        processAjaxCalls("MoMeetingFlid.mom","originalId="+sectId,"recallingFlidSuccess","");
        return;
        }
        if(Type =='CEC' || Type =='DEC'){//Added this line Mom - change-08-May
			fillComboBox("frmMom","cmbMomsPillargroup","pillargroup.mom?pillarid=TGT007&locationId="+locationId);
			}//Added this line Mom - change-08-May
        }//end
        /* 
        if(Type =='Pillar')//to stop recall when chose the functional location and recall only if it choses pilarr id //Change here 16 March
        {
        	jQuery('#hdnrecall').val("N");
        	
        }
         */


        setFunctionalLocWidth('frmMom','600px');
        var mkeyid = jQuery("#txtMomsKeyid").val();
        //alert(mkeyid+'keyid in flid sucess');
        var cellId = jQuery("#frmMom input[id='location']").val();
        var flid =jQuery("#frmMom input[id='flid']").val();
        //alert(flid);
        var momdate = getFieldValue("dteMomsDate", "frmMom");
        //processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid","",false);
        //var locationId = jQuery("#frmMom input[id='location']").val();
        //console.log("Location Id"+locationId);
        
    console.log("flid value >>> " + flid);
    console.log("momdate value >>> " + momdate);
    console.log("Params sending >>> " + "flid=" + flid + "&momdate=" + momdate);
        //processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");//Comented this line CHANGE
        

        if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
        {
           reloadCombo("frmMom","cmbMomsEmployee","employee.commonFilter?&flid="+result.flId);
        //reloadCombo("frmMom","cmbMomsRole","roleMst.commonFilter?&flid="+result.flId);
        }

        var Mettingtype =getFieldValue('cmbMomsMeetingtype');
        var mode = jQuery("#frmMom input[id=mode]").val();
        var shift= getFieldValue("cmbMomsShiftid");

                if(mode=="view" && mode.trim().length>0){
           jQuery('#frmMom').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:10%;"> </div>');
                    jQuery("#chkSelectAll").attr("readonly",true);
                }    

                //fnRecall();
                if(mode == "modify"){
                	
                	fnRecall();
                }
                else {
                	setTimeout(function () {
        				fnRecall();}, 1000);
                	
                }
                
                //jQuery('#frmMom').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
             
           //if(Mettingtype.trim().length<=0)
            //fnFillAttendanceGrid();
           // viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
           /*else if (Employee=="CEC" || Employee=="O"|| Employee=="DEC" || Employee=="DEC" )
            viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
           */

                //alert(" Mettingtype :: "+Mettingtype+" mkeyid "+mkeyid);  
            /*     if((Mettingtype=="CEC" && mkeyid.trim().length>0)||(Mettingtype=="DEC" && mkeyid.trim().length>0)||(Mettingtype=="P" && mkeyid.trim().length>0)){
            frmMomcmbMomsPillargroup_onSelect();
                }
             */   
               
            }     
function recallingFlidSuccess(result)
{
	console.log(result);
	var flid = result.flid;
	var dataStrFlid ="&flid=" + flid;
	funclocnStr= "functionalLoc.mom?Type=Pillar";
	loadFunctionalLocation("MomAttfunLocation", funclocnStr, "MomAttfunLocation", "frmMom",dataStrFlid);
	
	
	}

/* function fnRecall(){

        var recall=jQuery('#hdnrecall').val();
        //alert("recall value in Modify"+recall);
        glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();

var mode = jQuery('#hdnMenuMode').val();
//alert("mode"+mode);
var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");
var flid =jQuery("#frmMom input[id='flid']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var hdndte=jQuery("#hdnmomdate").val();
//alert("hdndte value in Modify"+hdndte);
//alert("recall value length in Modify"+ recall.trim().length);
var pillarid =getFieldValue('cmbMomsPillarid');
//processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
//alert(" Outside :: ");
if (recall.trim().length>0 && recall=="Y"){//alert("1");
            processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
           
            //if(hdndte.trim().length>0){//alert("12");
           var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
           processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
            }
            //navigateToNextForm("MoMeetingForm_input.mom"+ds ,momType+" Minutes of Meeting");
             
             //alert(" Keyid :: 1 "+jQuery("#txtMomsKeyid").val());
             setTimeout(function() {
          fnFillAttendanceGrid();
     },300);
             
             fnsetvalues();
         }

if(hdndte.trim().length>0 && recall.trim().length<=0 && recall!="Y" && Type != "Pillar")//Change here 16 March
{
	//alert("recall "+recall);
	 var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType;
       processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
	processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");
      
       
       //alert(" Keyid :: 2 "+jQuery("#txtMomsKeyid").val());
       setTimeout(function() {
        fnFillAttendanceGrid();
   },300);
       
       fnsetvalues();
       }else{
      var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
           processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
           fnsetvalues();
       }    

}*/
/* function fnRecall(){

    var recall=jQuery('#hdnrecall').val();
    //alert("recall value in Modify"+recall);
    glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();

var menuMode = jQuery('#hdnMenuMode').val();
//alert("mode"+menuMode);
var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");
var flid =jQuery("#frmMom input[id='flid']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var hdndte=jQuery("#hdnmomdate").val();
//alert("hdndte value in Modify"+hdndte);
//alert("recall value length in Modify"+ recall.trim().length);
var pillarid =getFieldValue('cmbMomsPillarid');
//processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
//alert(" Outside :: ");
	  var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
       processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
       
       if(menuMode == "modify")
       {
    	 //  fnsetvalues();
       }
       
       
       fillDetailGrid();
       
   setTimeout(function() {
    fnFillAttendanceGrid();
},300);
   
   fnsetvalues();


} */

function fnRecall(){

    var recall=jQuery('#hdnrecall').val();
    //alert("recall value in Modify"+recall);
    glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();

var menuMode = jQuery('#hdnMenuMode').val();
//alert("mode"+menuMode);
var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();

var shift= getFieldValue("cmbMomsShiftid");
var flid =jQuery("#frmMom input[id='flid']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var hdndte=jQuery("#hdnmomdate").val();
//alert("Type value in create"+Type);
//alert("recall value length in Modify"+ recall.trim().length);
var pillarid =getFieldValue('cmbMomsPillarid');
//processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
//alert(" Outside :: ");
	  var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
       processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
       
       
       
       if(menuMode != "modify" && Type =='JH')//CHANGE
       {
    	   processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");
       }//CHANGE
       
       
       fillDetailGrid();
       
   setTimeout(function() {
    fnFillAttendanceGrid();
},300);
   
   fnsetvalues();


}


/* function frmMom_FuntLocHierarchy_SuccessCallBack(result)
{  
	console.log(result);
	
var Type=jQuery('#hdntype').val();
var recall = jQuery('#hdnrecall').val();
if (Type =='Pillar' && recall != "N") 
{
readOnlyFields("cmbFunlocLocation");
readOnlyFields("cmbFunlocCompany");
jQuery('#hdnrecall').val("N");
var sbuId = result.sbuId;
//alert("Sbu Id"+sbuId);
processAjaxCalls("MoMeetingFlid.mom","originalId="+sbuId,"recallingFlidSuccess","");
}

if (Type =='Dmt' && recall != "Y") 
{

//jQuery('#hdnrecall').val("N");
var sectId = result.sectId;
//alert("Sbu Id"+sbuId);
processAjaxCalls("MoMeetingFlid.mom","originalId="+sectId,"recallingFlidSuccess","");
}


if(Type !='Pillar')
{
	jQuery('#hdnrecall').val("Y");
}



setFunctionalLocWidth('frmMom','600px');
var mkeyid = jQuery("#txtMomsKeyid").val();
var cellId = jQuery("#frmMom input[id='location']").val();
var flid =jQuery("#frmMom input[id='flid']").val();
//alert(flid);
var momdate = getFieldValue("dteMomsDate", "frmMom");
//processGridnew("MoMeetingAtt_input.mom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId+"&mkeyid="+keyid, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid","",false);
var locationId = jQuery("#frmMom input[id='location']").val();
//console.log("Location Id"+locationId);
processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");

if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
{
   reloadCombo("frmMom","cmbMomsEmployee","employee.commonFilter?&flid="+result.flId);
//reloadCombo("frmMom","cmbMomsRole","roleMst.commonFilter?&flid="+result.flId);
}

var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");

        if(mode=="view" && mode.trim().length>0){
   jQuery('#frmMom').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:10%;"> </div>');
            jQuery("#chkSelectAll").attr("readonly",true);
        }    

        fnRecall();
        //jQuery('#frmMom').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
     
   //if(Mettingtype.trim().length<=0)
    //fnFillAttendanceGrid();
   // viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
   // else if (Employee=="CEC" || Employee=="O"|| Employee=="DEC" || Employee=="DEC" )
 //   viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+mkeyid+'&cellId='+cellId+'&momdate='+momdate);
   //

        //alert(" Mettingtype :: "+Mettingtype+" mkeyid "+mkeyid);  
    //     if((Mettingtype=="CEC" && mkeyid.trim().length>0)||(Mettingtype=="DEC" && mkeyid.trim().length>0)||(Mettingtype=="P" && mkeyid.trim().length>0)){
  //  frmMomcmbMomsPillargroup_onSelect();
      //  }
        
       
    }
function recallingFlidSuccess(result)
{
	console.log(result);
	var flid = result.flid;
	var dataStrFlid = "&flid=" + flid;
	funclocnStr= "functionalLoc.mom?Type=Pillar";
	loadFunctionalLocation("MomAttfunLocation", funclocnStr, "MomAttfunLocation", "frmMom",dataStrFlid);
	
	
	}

function fnRecall(){

        var recall=jQuery('#hdnrecall').val();
        //alert("recall value "+recall);
        glbType=jQuery('#hdntype').val();
var Type=jQuery('#hdntype').val();
//alert("Type"+Type);
var Mettingtype =getFieldValue('cmbMomsMeetingtype');
var mode = jQuery("#frmMom input[id=mode]").val();
var shift= getFieldValue("cmbMomsShiftid");
var flid =jQuery("#frmMom input[id='flid']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var hdndte=jQuery("#hdnmomdate").val();
var pillarid =getFieldValue('cmbMomsPillarid');
//processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
//alert(" Outside :: ");
if (recall.trim().length>0 && recall=="Y"){//alert("1");
            processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
           
            // if(hdndte.trim().length>0){//alert("12");
         //  var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
         //  processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
          //  }
            //navigateToNextForm("MoMeetingForm_input.mom"+ds ,momType+" Minutes of Meeting");
             
             //alert(" Keyid :: 1 "+jQuery("#txtMomsKeyid").val());
             setTimeout(function() {
          fnFillAttendanceGrid();
     },300);
             
             fnsetvalues();
         }

if(hdndte.trim().length>0 && recall.trim().length<=0 && recall!="Y" && Type != "Pillar")
{
	//alert("recall "+recall);
	 var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType;
       processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
	processGridnew("MoMeetingMom_input.mom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");
      
       
       //alert(" Keyid :: 2 "+jQuery("#txtMomsKeyid").val());
       setTimeout(function() {
        fnFillAttendanceGrid();
   },300);
       
       fnsetvalues();
       }else{
      var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
           processAjaxCalls("MoMeetingRecalling_input.mom",ds,"RecallingSuccessData","");
           fnsetvalues();
       }    

} */

function fnsetvalues(){

setFieldValue("txtMomsKeyid","");
setFieldValue("txtMomsMeetingno","");
//setFieldValue("chkMomsIsmeetinghappen",jQuery("#hdnIsmthpn").val());
setFieldValue("txtMomsSafetytalk","");
setFieldValue("txtMomsRemarks","");
setFieldValue("txtMomsMeetingtitle","");
//setFieldValue("txtMomsMeetingno",jQuery("#hdnmtype").val());
setFieldValue("txtMomsAgenda","");
setFieldValue("cmbMomsPillarid","");

//alert(" FFFF "+jQuery("#hdnagnda").val());

setFieldValue("txtMomsKeyid",jQuery("#hdnMno").val());
setFieldValue("txtMomsMeetingno",jQuery("#hdnMno").val());
//setFieldValue("chkMomsIsmieetinghappen",jQuery("#hdnIsmthpn").val());
setFieldValue("txtMomsSafetytalk",jQuery("#hdnsfty").val());
setFieldValue("txtMomsRemarks",jQuery("#hdnrmrk").val());
setFieldValue("txtMomsMeetingtitle",jQuery("#hdntitle").val());
//setFieldValue("txtMomsMeetingno",jQuery("#hdnmtype").val());
setFieldValue("txtMomsAgenda",jQuery("#hdnagnda").val());

if(jQuery("#hdnpillarid").val().trim().length>0)
 setFieldValue("cmbMomsPillarid",jQuery("#hdnpillarid").val());

if(jQuery("#hdnpillarid").val().trim().length>0){
 setFieldValue("cmbMomsPillargroup",jQuery("#hdnpillargrpid").val());
 //frmMomcmbMomsPillargroup_onSelect();
 disableField("frmMom", "cmbMomsPillargroup");
}

}
function RecallingSuccessData(result){
//alert("Recall 1");
setFieldValue("txtMomsKeyid","");
        setFieldValue("txtMomsMeetingno","");
        setFieldValue("txtMomsSafetytalk","");
        setFieldValue("txtMomsRemarks","");
        setFieldValue("txtMomsMeetingtitle","");
        setFieldValue("txtMomsAgenda","");
        //setFieldValue("cmbMomsPillarid","");
        //alert("Recall 2");*/
       
        //alert(" FFFF--2 "+result[0][6]);
       
        setFieldValue("txtMomsKeyid",result[0][0]);
        setFieldValue("txtMomsMeetingno",result[0][0]);
        setFieldValue("txtMomsSafetytalk",result[0][2]);
        setFieldValue("txtMomsRemarks",result[0][3]);
        setFieldValue("txtMomsMeetingtitle",result[0][4]);
        setFieldValue("txtMomsAgenda",result[0][6]);
        if(result[0][7].trim().length>0)
            setFieldValue("cmbMomsPillarid",result[0][7]);
        if(result[0][8].trim().length>0){
            setFieldValue("cmbMomsPillargroup",result[0][8]);
            //frmMomcmbMomsPillargroup_onSelect();
            disableField("frmMom", "cmbMomsPillargroup");
            //jQuery('#hdnpillargrpval').val(result[0][8]);
        }
        
        
        fnFillAttendanceGrid();
       
    }

function viewGrid(url,filterString)
{
if (dataString=="?")
processGridnew("mom_input.mom",dataString,"list","pager","","docDoubleClick");
else if(validateFilterSelection(dataString))
{  
  processGridnew(url,filterString,"attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");//,"","","","loadCompleteAttendanceGrid"
    }
}
   
  function FiptoMom_afterClose()
   {
   
    jQuery("#list").trigger("reloadGrid");
   
   }

 
  function loadCompleteAttendanceGrid1(id){
 
  var row = jQuery("#"+id).jqGrid('getDataIDs');
   
    var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
  // alert("type:"+meetingType);
    var flid =jQuery("#frmMom input[id='flid']").val();
var locationId = jQuery("#frmMom input[id='location']").val();
  // alert("locationId"+locationId);
var cellId = jQuery("#frmMom input[id='cell']").val();

var pillargrp=getFieldValue("cmbMomsPillargroup", "frmMom");
    var momdate = getFieldValue("dteMomsDate", "frmMom");
var keyid=jQuery('#txtMomsMeetingno').val();
    var recall=jQuery('#hdnrecall').val();
var Pillarid=getFieldValue("cmbMomsPillarid", "frmMom");
    if(meetingType=="P")
    {
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&pillargroup="+pillargrp, "attandanceGrid", "pageratt");
    }
    else  if(meetingType=="UMC")
       {
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=UMC"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&pillargroup="+pillargrp, "attandanceGrid", "pageratt");
   
       }
    else  if(meetingType=="OGM")
       {
    processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=OGM"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&pillargroup="+pillargrp, "attandanceGrid", "pageratt");
   
       }
   
 
 
 
  }
 
 
  /* function loadCompleteAttendanceGrid(id){
     //alert(1);
     var row = jQuery("#"+id).jqGrid('getDataIDs');
     
     var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
   //  alert("type:"+meetingType);
     if(meetingType=="P")
    {
   var flid =jQuery("#frmMom input[id='flid']").val();
   var locationId = jQuery("#frmMom input[id='location']").val();
  // alert("locationId"+locationId);
   var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var keyid=jQuery('#txtMomsMeetingno').val();
var recall=jQuery('#hdnrecall').val();
var Pillarid=getFieldValue("cmbMomsPillarid", "frmMom");
//alert(" mstkeyid :: "+mstkeyid.length);
var pillargroup = getFieldValue("cmbMomsPillargroup", "frmMom");
//alert("Load Attendance Grid Call back from that URL");
//alert("pillar group value here"+pillargroup);

var callBackString = "?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId;
if(pillargroup && pillargroup.trim() !== "")
{
	callBackString += "&pillargroup=" + encodeURIComponent(pillargroup);
}
//alert("before grid");
      //processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
	processGridnew("MoMeetingAtt_input.mom",callBackString, "attandanceGrid", "pageratt");

   
   }
    jQuery('#hdnaddemp').val('');
} */

function loadCompleteAttendanceGrid(id){
    //alert(1);
    var row = jQuery("#"+id).jqGrid('getDataIDs');
    
    var meetingType=getFieldValue("cmbMomsMeetingtype", "frmMom");
  //  alert("type:"+meetingType);
    if(meetingType=="P")
   {
  var flid =jQuery("#frmMom input[id='flid']").val();
  var locationId = jQuery("#frmMom input[id='location']").val();
 // alert("locationId"+locationId);
  var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");
var keyid=jQuery('#txtMomsMeetingno').val();
var recall=jQuery('#hdnrecall').val();
var Pillarid=getFieldValue("cmbMomsPillarid", "frmMom");
//alert(" mstkeyid :: "+mstkeyid.length);
var pillargroup = getFieldValue("cmbMomsPillargroup", "frmMom");
//alert("Load Attendance Grid Call back from that URL");
//alert("pillar group value here"+pillargroup);
//Added by priyanka on 04.05.2026
var menumode = jQuery('#hdnMenuMode').val();
var modeVal = "";

if (menumode !== undefined && menumode !== null && menumode.trim() !== "") {
	modeVal = menumode;
  // jQuery('#hdnpillarModeValue').val(modeval);
}
//changes by priyanka on 04.05.2026

var callBackString = "?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mode="+modeVal;
if(pillargroup && pillargroup.trim() !== "")
{
	callBackString += "&pillargroup=" + encodeURIComponent(pillargroup);
}
//alert("before grid");
     //processGridnew("MoMeetingAtt_input.mom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");
	processGridnew("MoMeetingAtt_input.mom",callBackString, "attandanceGrid", "pageratt");

  
  }/*
   for(var i=0;i<row.length;i++)
   {
   var refKeyid = jQuery("#"+id).jqGrid('getCell',row[i],"KeyId");
  
    if(refKeyid.trim().length>0){//alert(1);
   jQuery('#MomAttcheckbox_'+row[i]+'_1').attr('checked', true);
             var addemp=jQuery('#hdnaddemp').val();
             if(addemp.trim().length>0) {
            if (i==row.length-1)
             chkboxAttCheck(row[i]);
           }
                else
            chkboxAttCheck(row[i]);
            
         }
    }*/
   jQuery('#hdnaddemp').val('');
	
	
	}

   
   
function addRow(row){//alert(" row :: "+row+" row :: "+1);

var Date=getFieldValue('dteMomsDate');
var shift =getComboBoxText('cmbMomsShiftid');
var saftytalk=getFieldValue('txtMomsSafetytalk');
var Pillar=getComboBoxText('cmbMomsPillarid');
var Meetingno=getFieldValue('txtMomsMeetingno');
var Meetingtitle=getFieldValue('txtMomsMeetingtitle');
var Meetintype=getComboBoxText('cmbMomsMeetingtype');


var val =  jQuery("#hdnVal").val();
var j = parseInt(val);
var checkval=jQuery('#chkMomsIsmeetinghappen').is(':checked');
//alert(" checkval :: "+checkval);
//var checkvals=jQuery("#momGrid").jqGrid('getCell','1',"check");
   
if(checkval==true){//alert(" checkval Inside :: "+checkval);
 if(Date==null || Date==''){
  alert("Select Date");
}
 if(shift==null ||shift==' '||shift==''){
 var type="CENTRAL EHS COMMITTEE";
 if(Meetintype=='JH')
 {
	 alert("Select Shift");

 }
/*  else
{

 
return false ;
} */
}    
 
 if(Meetintype==null || Meetintype==''){
  alert("Select Meeting Type");
  return false ;
}
 
}else if(shift==null ||shift==' '||shift==''){
  alert("Select Shift");
  return false ;
 }
//alert("outside");
      if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) {//alert(" Inside Validation ");
     //alert("Select Shif1t");
                var Type=jQuery("#momGrid").jqGrid('getCell',row.length,"cmbMomdDiscussionType");
                var Pillar = jQuery("#momGrid").jqGrid('getCell',row.length,"cmbMomdPillar");
                var DisCussion=jQuery("#momGrid").jqGrid('getCell',row.length,"txtMomdDiscussionDetails");
                var Remarks = jQuery("#momGrid").jqGrid('getCell',row.length,"txtMomdRemarks");
 
   if (jQuery('#jqg_momGrid_'+row.length).is(':checked') == true) {
   
                var Type = jQuery("#cmbMomdDiscussionType_momGrid_"+row.length).val();
                var Pillar = jQuery("#cmbMomdPillar_momGrid_"+row.length).combobox("getValue");
                var dis=jQuery("#txtMomdDiscussionDetails_momGrid_"+row.length).val();
   var Remarks=jQuery("#txtMomdRemarks_momGrid_"+row.length).val();
     }
 }
       
if ( row == null || row == '' || parseInt(row) <= 0) {
  //var PillarId=jQuery('#hdnPillarIds').val();
  //alert(" PillarId :: "+PillarId);
   //alert("Select Shift2");
               var emptyItem =[{txtMomdKeyid:" ",txtMomdMomsKeyid:" ",dteMomsDate:" ",cmbMomsShiftid:" ",cmbMomdDiscussionType:" ",cmbMomdPillar:"",txtMomdDiscussionDetails:" ",txtMomsSafetytalk:"  ",txtMomdRemarks:"  "}];
      jQuery("#momGrid").jqGrid('addRowData',j, emptyItem[0]);
          var k =j+1;
          jQuery("#hdnVal").val(k);
   }
       
  else {              
       for(var i=0;i<row.length;i++)
lastRow = row[i];
       //alert("Select Shift3");
       var emptyItem =[{txtMomdKeyid:" ",txtMomdMomsKeyid:" ",dteMomsDate:" ",cmbMomsShiftid:" ",cmbMomdDiscussionType:" ",cmbMomdPillar:"",txtMomdDiscussionDetails:" ",txtMomsSafetytalk:"  ",txtMomdRemarks:"  "}];
       jQuery("#momGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
       }

if(Meetintype=='DMT'){
        meetingtype();
  }//checking now

  /*
  jQuery("#momGrid").jqGrid('setCell',row.length+1,'Date',getFieldValue('dteMomsDate'));
jQuery("#momGrid").jqGrid('setCell',row.length+1,'Shift',shift);
jQuery("#momGrid").jqGrid('setCell',row.length+1,'SafetyTalk',getFieldValue('txtMomsSafetytalk'));
*/

/*
var rid=row.length+1;
jQuery('#jqg_momGrid_'+rid).attr('checked', true);
jQuery("#momGrid").jqGrid('setCell', rid, 'check', '1');
chkboxDiscuss(rid);
*/
}


function formatterCheckBox(id, options, rowObject)
{
var rowId = options.rowId;
var colId = options.pos;
return '<input type="checkbox" id="Momcheckbox_'+rowId+'_'+colId+'" name="Momcheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxDiscuss(\''+rowId + '\');}else{chkboxUnCheckDiscuss(\''+ rowId +'\');}" />';
}

function getCurrentTime(){
var serverTime = srvTime();
var currentTime = new Date(serverTime);
var hours = currentTime.getHours();
var minutes = currentTime.getMinutes();
if (minutes < 10){
minutes = "0" + minutes;
}
return hours + ":" + minutes;
}

function chkMomAttformatter(id, options, rowObject)
{

var keyid = jQuery('#txtMomsKeyid').val();    
if(keyid.length==0){
disable=' ';
        }
/*   else
         {
            var val = getFieldValue("cmbMomsMeetingtype", "frmMom");
       
        var momdate= getFieldValue("dteMomsDate", "frmMom");
        var currentDate = getServerDateTime();

        var currenttime=getCurrentTime();
       
        //alert(convertStringToDate((momdate+currenttime)));
       
            if(currentDate > convertStringToDate((momdate+currenttime)))//
        {
                  //alert(1);
                  //disable='disabled="disabled"';
            }
            else if(currentDate < convertStringToDate((momdate+currenttime))){
                 //disable=' ';  '+disable+'
            }
         }
*/        
var rowId = options.rowId;
var colId = options.pos;

var mode = jQuery("#frmMom input[id=mode]").val();
if(mode=="view" && mode.trim().length>0){
          disable='disabled="disabled"';   //
 return '<input '+disable+' type="checkbox" id="MomAttcheckbox_'+rowId+'_'+colId+'" name="MomAttcheckbox_'+rowId+'_'+colId+'" '+ ' style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxAttCheck(\''+rowId + '\');}else{chkboxAttUnCheck(\''+ rowId +'\');}" />';
}else
 return '<input type="checkbox" id="MomAttcheckbox_'+rowId+'_'+colId+'" name="MomAttcheckbox_'+rowId+'_'+colId+'" '+ ' style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxAttCheck(\''+rowId + '\');}else{chkboxAttUnCheck(\''+ rowId +'\');}" />';
}
function txtMomAttformatter(id, options, rowObject)
{
var rowId = options.rowId;
var colId = options.pos;
return '<input class="easyui-combobox" id="cmbAttcombo_'+rowId+'_'+colId+'" name="cmbAttcombo_'+rowId+'" value="'+rowObject[7]+'"  style=" width :100px;"/> ';                                          
}
function chkboxCheck(rowId)
{
jQuery("#attandanceGrid").jqGrid('setCell',rowId,'CHKBOX','1');
}
function chkboxUnCheck(rowId)
{
jQuery("#attandanceGrid").jqGrid('setCell',rowId,'CHKBOX','0');
}

function chkboxDiscuss(rowId) {

makeRowEditable("momGrid",rowId);
jQuery("#momGrid").jqGrid('setCell', rowId, 'check', '1');
setTimeout(function() {
setFocusOnField("txtMomdDiscussionDetails_momGrid_"+rowId);
},100);
/*
setFormater("momGrid","frmMom","type.commonFilter",rowId,"cmbMomdDiscussionType","TypeID","96px",false);
setFormater("momGrid","frmMom","pillar.commonFilter",rowId,"cmbMomdPillar","PillarID","250px",false);
setFormater("momGrid","frmMom","",rowId,"txtMomdDiscussionDetails",null,null,true,true);
setFormater("momGrid","frmMom","",rowId,"txtMomdRemarks",null,null,false,true,true);
setFormater("momGrid","frmMom","",rowId,"button","ActionPlan",null,false);
setFormater("momGrid","frmMom","",rowId,"kpibtn","KPI",null,false);
*/

}

function chkboxUnCheckDiscuss(rowId) {
jQuery("#momGrid").jqGrid('setCell', rowId, 'check', '0');
/* removeFormater("momGrid","",rowId,"","cmbMomdDiscussionType");
removeFormater("momGrid","",rowId,"","cmbMomdPillar");
removeFormater("momGrid","",rowId,"","txtMomdDiscussionDetails");
removeFormater("momGrid","",rowId,"","txtMomdRemarks");
removeFormater("momGrid","",rowId,"","button");
removeFormater("momGrid","",rowId,"","kpibtn");
*/
}

function chkboxAttCheck(rowId) {//alert(1);//attandanceGrid_CHKBOX

   
   jQuery("#attandanceGrid").jqGrid('setCell', rowId, 'chkbox', '1');
      setFormater("attandanceGrid","frmMom","MeetingAttendance.mom",rowId,"AttendanceID","Attendance","96px",false);
      //alert(" Checking Value ::  "+"attandanceGridcmbMomaAttandance_"+rowId);
      if(getFieldValue("attandanceGridcmbMomaAttandance_"+rowId).trim().length<=0)
    {
    setFieldValue("attandanceGridcmbMomaAttandance_"+rowId,"P");
    jQuery("#attandanceGrid").jqGrid('setCell',rowId,'Attendance','P');
       jQuery('#MomAttcheckbox_'+rowId+'_1').attr('checked', true);

       if(jQuery('#hdnaddemp').val()=='C'){
            setFieldValue("cmbMomsEmployee"," ");
       }
    }
     
}

function chkboxAttUnCheck(rowId) {

jQuery("#attandanceGrid").jqGrid('setCell', rowId, 'chkbox', '0');
removeFormater("attandanceGrid","",rowId,"","AttendanceID");

}

   function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName) {//alert(1234567890);
var momrow = jQuery("#attandanceGrid").jqGrid('getDataIDs');// row get data
        var momcol = jQuery("#attandanceGrid").jqGrid("getGridParam","colModel");// col get data
var rowid = "";
var acontrolId = "";
var aKeyId;
var colValue = "";
var acontrolId1 = "";
var colValue1 = "";
var newcombovalue= "";
var jsonArrO = '[';
for (i = 0; i < momrow.length; i++) {
rowid = momrow[i];

//var Checkval= jQuery("#attandanceGrid").jqGrid('getCell',rowid,"chkbox");
var isChecked = jQuery('#MomAttcheckbox_'+ rowid +'_1').is(':checked');
           // alert(" Checkval :: "+Checkval);
if(isChecked == true  ){ //Checkval=="1" && Checkval!=null && Checkval!=' '){
AttendanceKeyid = jQuery("#attandanceGrid").jqGrid('getCell', rowid,"KeyId"); // Call detail Key Id
var colkeyid = jQuery('#txtMomsKeyid').val(); //call master key id  
var EmpID = jQuery("#attandanceGrid").jqGrid('getCell',rowid,"EmployeeId");
           var FLID= jQuery("#frmMom input[id='flid']").val();
           var Role = jQuery("#attandanceGrid").jqGrid('getCell',rowid,"Role");
           var AttendanceId = jQuery("#attandanceGrid").jqGrid('getCell',rowid,"Attendance");

            if ((FLID != null && FLID != "")|| (FLID != null && FLID != "")) {
jsonArrO += '{';
jsonArrO += '"txtMomaKeyid":"' + AttendanceKeyid + '",';
jsonArrO += '"txtMomaMomsKeyid":"' + colkeyid + '",';
jsonArrO += '"txtMomaFlid":"' + FLID + '",';
jsonArrO += '"txtMomaEmployeeid":"' + EmpID + '",';
jsonArrO += '"txtMomaAttandance":"' + AttendanceId + '"';
jsonArrO += '},';
}
          }
   }
jsonArrO = jsonArrO.slice(0, -1) + "]";
jsonArrO = (jsonArrO != ']'?jsonArrO:"");
        //alert(" Inside Json Conversion :: "+jsonArrO);
return jsonArrO;


}

   

   /* function Actionplane_onClose(){

         var keyid = jQuery('#txtMomsKeyid').val();  
         var APLKeyid = jQuery('#txtAplmKeyid').val();
         var row =  jQuery('#hdnRowCoId').val();
         //jQuery('#txtMomsKeyid').val(result.MomMstkeyid);
         jQuery('#hdnactnmode').val('');
         jQuery("#momGrid").jqGrid('setCell',row,'txtMomdActionplanId',APLKeyid);
         var dtlKeyid=jQuery("#momGrid").jqGrid('getCell',row,'txtMomdKeyid');
         //alert(" dtlKeyid : "+dtlKeyid);
         //var filterStr="?dtlKeyid="+dtlKeyid+"&aplKeyid="+APLKeyid;
         processAjaxCalls("MoMeetingForm_update.mom","dtlKeyid="+dtlKeyid+"&aplKeyid="+APLKeyid,"updateSuccess","");
         //processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid, "momGrid", "pagermom");
       
         return true;
         
        } */
        
   function Actionplane_onClose(){

       var keyid = jQuery('#txtMomsKeyid').val();  
       var APLKeyid = jQuery('#txtAplmKeyid').val();
       var row =  jQuery('#hdnRowCoId').val();
       //jQuery('#txtMomsKeyid').val(result.MomMstkeyid);
       jQuery('#hdnactnmode').val('');
       jQuery("#momGrid").jqGrid('setCell',row,'txtMomdActionplanId',APLKeyid);
       var dtlKeyid=jQuery("#momGrid").jqGrid('getCell',row,'txtMomdKeyid');
       //alert(" dtlKeyid : "+dtlKeyid);
       //var filterStr="?dtlKeyid="+dtlKeyid+"&aplKeyid="+APLKeyid;
       processAjaxCalls("MoMeetingForm_update.mom","dtlKeyid="+dtlKeyid+"&aplKeyid="+APLKeyid,"updateSuccess","");
       //processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+keyid, "momGrid", "pagermom");
       // Added by priyanka on 04.05.26
       processGridnew("MoMeetingMom_input.mom", "?q=2&keyid=" + keyid + "&type=" + glbType, "momGrid", "pagermom", "", "", "", "loadCompleteAction");
     
       return true;
       
      }

    function updateSuccess(){
        //alert("updateSuccess");
    jQuery("#momGrid").trigger("reloadGrid");
    }

    function updateSuccessAgendaData(result){
        var keyid=jQuery('#txtMomsKeyid').val();
        var type=jQuery('#hdntype').val();
       
        /*   if(keyid.trim().length=="0" && (type=='JH' || type=='Dmt')){
        //setFieldValue("txtMomsAgenda"," ");
         setTimeout(function() {
        setFieldValue("txtMomsAgenda",result[0][0]);
    },500);
           
        } */
       
        //alert(" updateSuccessAgendaData :: "+result[0][0]);
        setFieldValue("txtMomsAgenda",result[0][0]);
    }

function momGridActplnbutton_onClick(result){

var rowid=result.rowId;
var btnid=result.btnId;
var refDocId = jQuery("#momGrid").jqGrid('getCell', rowid, "txtMomdKeyid");
var Type=jQuery('#hdntype').val();
var Pillarid=getFieldValue("cmbMomsPillarid", "frmMom");

        if(Pillarid.trim().length==0)
        Pillarid='-';
       
var mode = jQuery("#frmMom input[id=mode]").val();
var mainTask;
//alert("Called");

        if(mode=="view")
        mainTask = jQuery("#momGrid").jqGrid('getCell',rowid,"txtMomdDiscussionDetails");
        else
        mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
     
//var mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);

if (mainTask.trim().length==0) {
popupCommonErrorMsg("Enter Discussion Details");
return false;
}

var mom = "MOM";
var flid = jQuery("#frmMom input[id='flid']").val();
var Mstkeyid = jQuery('#txtMomsKeyid').val();

if(Mstkeyid.trim().length>0){//alert(" refDocId :: "+refDocId);
if(refDocId.trim().length>0){
var flid = jQuery("#frmMom input[id='flid']").val();
var pasdate= getFieldValue("dteMomsDate", "frmMom");
var keyid=jQuery('#txtMomsKeyid').val();

if(mode=="view"){
    apMode = "view";
openActionPlan("Actionplane",keyid,"MOM",flid,mainTask,refDocId,pasdate,apMode);
}else
   {
//saveForm('frmMom','MoMeetingForm_save.mom?momactnpln=momactnpln&rowid='+rowid);
//openActionPlan("Actionplane",keyid,"MOM",flid,mainTask,refDocId,pasdate,"create");
                   saveForm('frmMom','MoMeetingForm_save.mom?momactnpln=momactnpln&rowid='+rowid);
        }
}
else
saveForm('frmMom','MoMeetingForm_save.mom?momactnpln=momactnpln&rowid='+rowid);
//openActionPlan("Actionplane",MstKeyid,"MOM",flid,mainTask,refDocId,pasdate,"create");
}else
saveForm('frmMom','MoMeetingForm_save.mom?momactnpln=momactnpln&rowid='+rowid);



/* if(Mstkeyid.trim().length>0)
   {
if(refDocId.trim().length>0){
var keyid=jQuery('#txtMomsKeyid').val();
var flid=jQuery("#flid").val();
jQuery('#hdnRowCoId').val(rowid);
openActionPlan("Actionplane",keyid,"MOM",flid,mainTask,refDocId);

saveForm('frmMom','MoMeetingForm_save.mom?momactnpln=momactnpln&rowid='+rowid);
   }else
   {
saveForm('frmMom','MoMeetingForm_save.mom?momactnpln=momactnpln&rowid='+rowid);

   }

}
else
   {
saveForm('frmMom','MoMeetingForm_save.mom?&momactnpln=momactnpln&rowid='+rowid);

   } */
}
 

function momGridkpibtn_onClick(result){
//alert(" click");
var rowid=result.rowId;
var btnid=result.btnId;
var Pillar ;
var mode = jQuery("#frmMom input[id=mode]").val();
        if(mode=="view")
        Pillar = jQuery("#momGrid").jqGrid('getCell',rowid,"cmbMomdPillar");
        else
        Pillar = jQuery("#cmbMomdPillar_momGrid_"+rowid).combobox("getValue");

/*if(Pillar.length== 0 ||Pillar==null || Pillar== '' || Pillar== ' '){
            alert("Select Pillar");
            return false;
        }*/

//else

if(Pillar.length > 0 ||Pillar!=null || Pillar!= ''){
dtlKeyid = jQuery("#momGrid").jqGrid('getCell', rowid, "txtMomsKeyid");

var mom = "MOM";    
var pillarCode =jQuery("#momGrid").jqGrid('getCell', rowid, "PillarID");
var kpikeyid =jQuery("#momGrid").jqGrid('getCell',rowid,"txtMokpKeyid");

if(kpikeyid .trim().length<=0)
  kpikeyid ="";

jQuery('#hdnRowCoId').val(rowid);
var flid =jQuery("#frmMom input[id='flid']").val();

/*if (pillarCode=='' || pillarCode==' ') {
alert('Select Pillar');
return false;
}*/

var ds = "?from=MOM"+"&flid="+flid+"&kpikeyid="+kpikeyid+"&pillar="+Pillar;

LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk"+ds,true, "38%", "75%", "0px", "40%", "multiSelectOk_Callback","KPI Indicator", false);
//LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.prpo?from=MOM&pillar="+Pillar+"&kpikeyid="+kpikeyid,true, "38%", "75%", "0px", "40%", "multiSelectOk_Callback","KPI Indicator", false);
   }
}

function divIndicatorPop_onClose(){

  var indicators = jQuery('#selePillarId').val();
  //alert(" indicators :: "+indicators);
  var KpiKeyid = jQuery('#seleKeyid').val();
  //alert(" indicators :: "+KpiKeyid);
  var row =  jQuery('#hdnRowCoId').val();
  //alert(" indicators :: "+row);
  jQuery("#momGrid").jqGrid('setCell',row,'kpi',indicators);
  jQuery("#momGrid").jqGrid('setCell',row,'txtMokpKeyid',KpiKeyid);
  return true;
}


function frmMom_beforeDelete() {
if (confirm("Are you sure want to delete ?")==false)
return false;

}

function frmMom_beforeSubmit() {
//alert(" Before :: ");

var cellId = jQuery("#frmMom input[id='cell']").val();
console.log("Cell"+cellId.length);

var sectId = jQuery("#frmMom input[id='section']").val();
var Type=jQuery('#hdntype').val();
console.log("Type "+Type);

//Special characters
var agenda=jQuery('#txtMomsAgenda').val();
var safetyTalk = jQuery('#txtMomsSafetytalk').val();
var remarks = jQuery('#txtMomsRemarks').val();

if (hasSpecialCharacters(agenda)) {
    alert("Special Characters Not Allowed in Agenda");
    return false; // stop further execution if needed
}

if (hasSpecialCharacters(safetyTalk)) {
    alert("Special Characters Not Allowed in Safety Talk");
    return false;
}

if (hasSpecialCharacters(remarks)) {
    alert("Special Characters Not Allowed in Remarks");
    return false;
}	






if((Type=="CEC" && agenda.length==0)||(Type=="DEC" && agenda.length==0 || Type=="JH" && agenda.length==0 || Type=="Dmt" && agenda.length==0 || Type=="Pillar" && agenda.length==0)){
popupCommonErrorMsg("Enter Agenda");
return false;
}

if(Type=="JH" && cellId.length==0){
popupCommonErrorMsg("Select JH");
return false;
}
if(Type=="DMT" && sectId.length==0){
popupCommonErrorMsg("Select DMT");
return false;
}
   var save=jQuery('#hdnsavebtn').val();
if((save.trim().length== "0" || save!= "Y")){//alert(1);
var gridvalue=getGridSelectArray("momGrid");
            var mstkeyid=jQuery('#txtMomsKeyid').val();

if(gridvalue.trim().length<=0){//alert(2);

        var MomAtt= getSelectdRowsAtt('attandanceGrid', 'MomAttcheckbox_', 'chkbox');  
   var val = getFieldValue("cmbMomsMeetingtype", "frmMom");
   
   if(MomAtt.trim().length<=0 && mstkeyid.trim().length<=0)
{
alert(" Select Atleast One Employee For Attendance ");
return false;
}else
{  
   gridData +='&MomAtt=' +MomAtt;
   return gridData;
}

              return true;
       }else{

       var gridData = '&momDetails=' + gridvalue;
   gridData +='&KpiIndicator=' + getselectKPI();
   var MomAtt= getSelectdRowsAtt('attandanceGrid', 'MomAttcheckbox_', 'chkbox');  
   if(MomAtt.trim().length<=0 && mstkeyid.trim().length<=0)
{
alert(" Select Atleast One Employee For Attendance ");
return false;
}
   else
{  
   gridData +='&MomAtt=' +MomAtt;
   return gridData;
}
        }
   }
   
}

function convertJHFSAuditToJSONString() {
var momrow = jQuery("#momGrid").jqGrid('getDataIDs');// row get data
var momcol = jQuery("#momGrid").jqGrid("getGridParam", "colModel");// col get data
var rowid = "";
var controlId = "";
var cKeyId; //det keyid
var colValue = "";
var remarksVal = "";
var colValue1 = "";
var colValue2 = "";
var controlId2 = "";
var jsonArrO = '[';
for (i = 0; i < momrow.length; i++) {
rowid = momrow[i];
cKeyId = jQuery("#momGrid").jqGrid('getCell', rowid, "Keyid"); // propertie index id of value detailkeyid
var colkeyid = jQuery('#txtMomsKeyid').val(); // call master key id
       var actionplan = jQuery("#momGrid").jqGrid('getCell', momrow[i],"actPlan");
if (actionplan!= null || actionplan != ""){
actionplan ="";
}
   var checkval=jQuery("#momGrid").jqGrid('getCell',rowid,"check");
   if(checkval=="1"||checkval==""||checkval!=0){
var type = getComboBoxText("momGridcmbMomdDiscussionType_"+momrow[i]);
var Pillar = jQuery("#momGrid").jqGrid('getCell',rowid,"PillarID");
controlId ="momGridtxtMomdDiscussionDetails_"+ momrow[i];
            colValue = jQuery('#' + controlId).val();
remarksVal = jQuery("#momGridtxtMomdRemarks_"+ momrow[i]).val();
        if ((colValue != null && colValue != "")|| (colValue1 != null && colValue1 != "")) {
jsonArrO += '{';
jsonArrO += '"txtMomdKeyid":"' + cKeyId + '",';
jsonArrO += '"txtMomdMomsKeyid":"' + colkeyid + '",';
jsonArrO += '"txtMomdDiscussionDetails":"' + colValue + '",';
jsonArrO += '"txtMomdRemarks":"' + remarksVal + '",';
jsonArrO += '"txtMomdactPlan":"' + actionplan + '",';
jsonArrO += '"cmbMomdDiscussionType":"' + type + '",';
jsonArrO += '"cmbMomdPillar":"' + Pillar + '"';
jsonArrO += '},';
}
 }
}
if (jsonArrO != "[")
jsonArrO = jsonArrO.slice(0, -1) + "]";
else
jsonArrO = "";
return jsonArrO;
}

function getselectKPI() { //alert(1);
//alert("1");
var momrow = jQuery("#momGrid").jqGrid('getDataIDs');// row get data
var momcol = jQuery("#momGrid").jqGrid("getGridParam", "colModel");// col get data
var rowid = "";
//alert("momrow "+momrow);


var jsonArrO = '[';
for (i = 0; i < momrow.length; i++) {

if(jQuery('#jqg_momGrid_'+momrow[i]).is(':checked') == true){

var Kpikeyid = jQuery("#momGrid").jqGrid('getCell', momrow[i],"txtMokpKeyid"); // Kpi Indicator Keyid
var KpiInKeyid = Kpikeyid.split(',');
//alert("KpiInKeyid "+KpiInKeyid);
for (j = 0; j < KpiInKeyid.length; j++) {//alert(2);
               
var Maskeyid = jQuery('#txtMomsKeyid').val(); // Master Keyid  
var DtlKeyId = jQuery("#momGrid").jqGrid('getCell', momrow[i],"txtMomdKeyid"); // Details keyid
var kpi = jQuery("#momGrid").jqGrid('getCell', momrow[i],"kpi"); // Details keyid
if(kpi.length>0){
if (((Maskeyid != null || Maskeyid != "") || (DtlKeyId != null || DtlKeyId != ""))
|| ((Maskeyid == null || Maskeyid == "") || (DtlKeyId == null || DtlKeyId == "")))
{
jsonArrO += '{';
jsonArrO += '"txtmokpKinkKeyid":"' + KpiInKeyid[j] + "\",";
jsonArrO += '"txtmokpMomdKeyid":"' + DtlKeyId + "\",";
jsonArrO += '"txtmokpMomsKeyid":"' + Maskeyid + "\"";
jsonArrO += '},';  
//alert("jsonArrO "+jsonArrO) ;
}
}
}

}
}
if (jsonArrO != "[")
jsonArrO = jsonArrO.slice(0, -1) + "]";
else
jsonArrO = "";
//alert(" Inside jsonArrO "+jsonArrO);
return jsonArrO;

}
function MomeetinChechappen(meeting) {//alert(" meeting:::: "+meeting);
if (meeting == 'Y') {
jQuery('#chkMomsIsmeetinghappen').attr('checked', true);
} else if (meeting == 'N') {
jQuery('#chkMomsIsmeetinghappen').attr('checked', false);
}
}

function AttandancesCheck(meetingatt) {
if (meetingatt == 'Y') {
jQuery('#Att_checkbox_').attr('checked', true);
} else if (meetingatt == 'N') {
jQuery('#Att_checkbox_').attr('checked', false);
}
}

function MommeetigSortable_loadComplete() {
jQuery("#momGrid").children().removeClass("ui-jqgrid-sortable");
}
function MommeetigSortable1_loadComplete() {
jQuery("#attandanceGrid").children().removeClass("ui-jqgrid-sortable");
}

function remove_successCallBack(result) {
alert(result.successData);
jQuery("#momGrid").trigger("reloadGrid");
}
function remove_errorCallBack() {
}
function removeRecord(keyid) {
var momrow = jQuery("#momGrid").jqGrid('getDataIDs');// row get data

for (i = 0; i < momrow.length; i++) {
        var rowid = momrow[i];
        if (jQuery('#jqg_momGrid_'+momrow[i]).is(':checked') == true) {//alert(1);
   
keyid = jQuery("#momGrid").jqGrid('getCell', rowid, "txtMomdKeyid");

             if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
   var r = confirm("Do You Want To Delete?");
if (r == true) {
processAjaxCalls("MoMeeting_remove.mom", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
} else{
return false;
}
                }else {
                    var r = confirm("Do You Want To Remove Row?");
if (r == true)
jQuery("#momGrid").trigger("reloadGrid");
else
return false;
}
             }
           } }


    function frmMom_successsCallback(result) { //alert(" SuccessCallBack :: "+result.MomMstkeyid);  

        var rowid=result.RowId;
   jQuery('#hdnrowid').val(rowid);
       
        var momactnpln =result.momactnpln;
        jQuery('#hdnactnmode').val(momactnpln);
       
        //alert(" momactnpln :: "+momactnpln);//MomMstkeyid
       
        var filemanger =result.filemanger;
        var mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
        var checkvis=jQuery('#hdnCheckvist').val();
       
        var MstNo=result.MomMstNo;
        jQuery('#txtMomsMeetingno').val(MstNo);
       
        var MstKeyid=result.MomMstkeyid;
        jQuery('#txtMomsKeyid').val(MstKeyid);
        var flid =jQuery("#frmMom input[id='flid']").val();
        var cellId = jQuery("#frmMom input[id='cell']").val();
var momdate = getFieldValue("dteMomsDate", "frmMom");//

if(filemanger==true){
        if(MstKeyid.trim().length>0){
  var keyid=result.MomMstkeyid;
  fileManagerPopUp(keyid,"MOM","","","");
    }
    }

//processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+MstKeyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");
fillDetailGrid();

jQuery('#btnExcelVw').show();
jQuery('#btnMomMail').show();
// processGridnew("MoMeetingMom_input.mom","?q=2&keyid="+MstKeyid, "momGrid", "pagermom");
//viewGrid("MoMeetingAtt_input.mom",'q=2&flid='+flid+'&mkeyid='+MstKeyid+'&cellId='+cellId+'&momdate='+momdate);

jQuery('#chkSelectAll').attr('checked', false);

//frmMomcmbMomsPillargroup_onSelect();
         var Mettingtype =getFieldValue('cmbMomsMeetingtype');
         var mkeyid = jQuery("#txtMomsKeyid").val();

         
setTimeout(function() {
fnFillAttendanceGrid();
},500);

//alert(" Mettingtype :: "+Mettingtype);
/* if((Mettingtype=="CEC"||Mettingtype=="DEC")||(Mettingtype=="P" && mkeyid.trim().length>0)){
       setTimeout(function() {
           frmMomcmbMomsPillargroup_onSelect();
    },500);
        } */

       
}
   
   
    function loadCompleteAction()
{
       var momactnpln=jQuery('#hdnactnmode').val();
var rowid=jQuery('#hdnrowid').val();
var MstKeyid=jQuery('#txtMomsKeyid').val();
//alert(" MstKeyid :: "+MstKeyid);//jQuery('#txtMomsKeyid').val(MstKeyid);
if(momactnpln==true || momactnpln=='true'   ){
//alert(" 1234 ");
        if(MstKeyid.trim().length>0){
//var keyid=result.MomMstkeyid;
//alert(" Inside keyid "+keyid);
//var flid=jQuery("#flid").val();
var flid = jQuery("#frmMom input[id='flid']").val();
var refDocId = jQuery("#momGrid").jqGrid('getCell',rowid, "txtMomdKeyid");  
var mainTask = jQuery("#momGrid").jqGrid('getCell',rowid, "txtMomdDiscussionDetails");
//var mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
var pasdate= getFieldValue("dteMomsDate", "frmMom");
var keyid=jQuery('#txtMomsKeyid').val();
var mode = jQuery("#frmMom input[id=mode]").val();
var type=jQuery('#hdntype').val();
var pillarid=getFieldValue("cmbMomsPillarid", "frmMom");

       if(pillarid.trim().length==0)
        pillarid='-';
       
if(mode.trim().length>0){
apMode = "view";
      openActionPlan("Actionplane",MstKeyid,"MOM",flid,mainTask,refDocId,pasdate,apMode);
}
else{
//openActionPlan("Actionplane",MstKeyid,"MOM",flid,mainTask,refDocId,pasdate,"create");

var dataStr ="actPlanRefMasId="+MstKeyid+"&actPlanRefDocType=MOM"+"&flid="+flid+"&actPlanMainTask="+escape(mainTask) +"&actPlanRefDtlId="+refDocId;
dataStr+="&actPlanRefDate="+pasdate+"&apMode=create"+"&type="+type+"&pillarid="+pillarid;
LoadPopUp("Actionplane","ActionPlan_input.api?"+dataStr,true,"83%","90%","3%","7%","","Action Plan","",false);
}
}
}
       else
{
            //jQuery('#txtMomsKeyid').val("");
    //jQuery('#txtMomsKeyid').val(result.MomMstkeyid);
return false;
}
}


</script>



<form name="frmMom" id="frmMom">
        <div id="wrapperRpt" style="width :100%">
        <table>
        <tr>
        <td colspan="2">
        <div id="frmmomFuntKeyIds">
<input type="hidden" id="factory" name="cmbMomdFactoryid" value=""></input>
<input type="hidden" id="section" name="cmbMomdSectionid" value=""></input>
<input type="hidden" id="location" name="cmbMomdLocationId" value=""></input>
<input type="hidden" id="sbu" name="cmbMomdSbu" value=""></input>
<input type="hidden" id="pbu"    name="cmbMomdPbu" value=""></input>
<input type="hidden" id="dmt"    name="cmbMomdDmt" value=""></input>
<input type="hidden" id="jh"    name="cmbMomdJh" value=""></input>
<input type="hidden" id="cell"    name="cmbMomdCellid" value=""></input>
<input type="hidden" id="machine" name="cmbMomdMachineid" value=""></input>
<input type="hidden" id="flid"    name="cmbMomsFlid" value="${requestScope.mom. momsFlid} "></input>
<input type="hidden" id="elementId"    name="cmbelementid" value="${requestScope.mom. elementid} "></input>        
</div>

     <div  class="easyui-paddingbfpx" id="MomAttfunLocation" style="width: 104%;margin-top:-12px;width:108%\9;"></div>
     </td>
     <td valign="top" style="position:relative;">
     <div style="margin-top:-10px;margin-left:10px;position:absolute;width:200px;">
<label class="mandatory-lbl">Date</label>
<span style="padding-left:80px;">
<label id="lblShift" class="mandatory-lbl" style="padding-left:0px;">Shift</label>
</span>
<div style="padding-left:5px;">
<input  id="dteMomsDate" name="dteMomsDate" class="easyui-datebox" style="width:90px;" value="${requestScope.mstDate}"/>
<span id="spnShift">
<input class="easyui-combobox" id="cmbMomsShiftid" name="cmbMomsShiftid"  style=" width : 80px;" value="${requestScope.mom.momsShiftid}" />
</span>
<div>
<table>
<tr>
<td>
</td>
<td>
<span id="err_dteMomsDate" class="tpm-errormsg"></span>
</td>
</tr>
</table>
</div>
</div>
</div>
<table>
<tr>
<td style="padding-left:0px;padding-top:-10px;"><span id="err_dteMomsDate" class="tpm-errormsg"></span></td>
<td style="padding-left:32px;"><span id="err_cmbMomsShiftid" class="tpm-errormsg"></span></td>
</tr>
</table>

</td>
     
      <td valign="top" style="margin-top:-10px;">
      <div id="meetinghppnd" style="padding-top:14px;padding-left:170px;width:160px;">
<!--      <span style="padding-left:4px; display:none;">-->
<!--       <input type="checkbox" id="chkMomsIsmeetinghappen" name="chkMomsIsmeetinghappen" value="Y"> <label style="vertical-align: top;" m> Meeting Happened?</label>-->
<!--      </span>-->
       <span style="padding-left:6px;">
 <input type="checkbox" id="chkMomsIsmeetinghappen" name="chkMomsIsmeetinghappen" value="Y"> <label style="vertical-align: top" class="mandatory-lbl"> Meeting Happened?</label>
  </span>
       </div>
       
       </td>
       
       <td>
       <div style="margin-left:6px;margin-top:-6px;margin-bottom:2px;">
      <input type="button" class="easyui-button" id="btnMomMail" name="btnMomMail" value="Mail" style="height:23px;width:70px;display:none;"/>
</div>
  <span style="padding-left:6px;">
<input type="button" class="easyui-button" id="btnjhActivity" name="btnjhActivity" value="Jh Activity" style="height:23px;width:70px;"/>
   </span>

    </td>

     </tr>
    <tr>
   
    <td colspan="3">
   
     <div id="MessageBoard" style="margin-right:-26px;width:180px;float:right;display: none;">
     <input type="checkbox" id="chkMomsIsmessageboard" name="chkMomsIsmessageboard" <c:out value = "${requestScope.mom.momsIsmessageboard == 'Y' ? ' checked':' '}"/> value="Y" > <label style="vertical-align: top"> Show In Message Board</label>
</div>
       
   </td>
   <td>
   <div style="margin-left:6px;">
      <input type="button" class="easyui-button" id="btnExcelVw" name="btnExcelVw" value="Excel View" style="height:23px;width:70px;display:none;"/>
</div>

   </td>    
    </tr>
   
     </table>
 <div id="tabMom" class="easyui-tabs" style="height:auto; width: 1110px; width:1110px\9; margin-top:-0px; float: left;">

 <div title="Mom">
 <table style="margin-left:8px;margin-left:2px\9;">
 <tr>
 
 <td valign="top">
         <div style="padding-top:12px;">
         <span style="margin-left:5px;">
<label id="MeetingType" class="mandatory-lbl">Meeting Type</label>
</span>
<span style="padding-left:45px;"><label id="pillarlblid">Pillar</label></span>
<div style="margin-left:5px;">
<input class="easyui-combobox" id="cmbMomsMeetingtype" name="cmbMomsMeetingtype" readonly="readonly" style=" width : 120px;"  value="${requestScope.mom.momsMeetingtype}" />
<span id="spnPillar" style="padding-left:0px;">
<input class="easyui-combobox" id="cmbMomsPillarid" name="cmbMomsPillarid"  style=" width : 120px;"  value="${requestScope.mom.momsPillarid}" />
</span>
</div>
</div>

<div style="padding-left:4px;">
<label id="MeetingTitle" >Meeting Title</label>

<span style="padding-left:90px;" id="lblMeetingNumber">
<label id="MeetingNumber" >Meeting Number</label>
</span>
<div>
       
        </div>
        <div><input type="text" class="easyui-text" id="txtMomsMeetingtitle" name="txtMomsMeetingtitle" maxlength="98"   style=" width : 160px;" value="${requestScope.mom.momsMeetingtitle}"/>
        <span >
        <input type="text" class="easyui-text"  id="txtMomsMeetingno" name="txtMomsMeetingno" maxlength="10"   style=" width : 120px; text-align:left;" value="${requestScope.mom.momsKeyid}"/>
        </span>
        </div>
</div>

<table>
<tr>
<td><span id="err_cmbMomsMeetingtype" class="tpm-errormsg"></span></td>
<td style="padding-left:126px;"><span id="err_cmbMomsPillarid" class="tpm-errormsg"></span></td>
</tr>
</table>
</td>

<td valign="top" style="padding-top:16px;padding-left:6px;">
       
    </td>

  <td valign="top" style="padding-left:6px;padding-top:16px;">
       <div style="margin-left:5px;"><label id="Agenda">Agenda </label></div>
            <textarea  rows="3"  cols="17" id="txtMomsAgenda" maxlength="500" name="txtMomsAgenda"  style="height : 50px; margin-left: 5px;text-transform: ; width : 220px;"   >${requestScope.mom.momsAgenda}</textarea>
  </td>
 
<td valign="top" style="padding-top:16px;padding-left:0%; width : 220px;">
         
         <div style="margin-left:5px;"><label id="SafetyTalk" >Safety Talk </label></div>
               <textarea  rows="3"  cols="17" id="txtMomsSafetytalk" maxlength="500" name="txtMomsSafetytalk"  style="height : 50px; margin-left: 5px;text-transform: ; width : 220px;"   >${requestScope.mom.momsSafetytalk}</textarea>
               <span id="err_txtMomsSafetytalk" class="tpm-errormsg" style="padding-left:10px;"></span>
       
         </td>
  <td valign="top" style="padding-top:16px;width:220px;">
         <div style="margin-left:5px;"><label id="lblRemarks">Remarks </label></div>
         <span id="spnMomsRemarks">  
         <textarea  rows="3"  cols="17" id="txtMomsRemarks" maxlength="500" name="txtMomsRemarks" style="height : 50px; margin-left: 5px;text-transform: ; width : 220px;"  >${requestScope.mom.momsRemarks}</textarea>
         </span>
         <span id="err_txtMomsRemarks" class="tpm-errormsg"></span>
         
         <div  style="margin-top: 10px;margin-left:-18px;">
      <input type="button" class="easyui-button" value ="Add" id="btnAddNew" style="height:23px"/>
      <span style="padding-left:8px;">
         <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;"/>
      </span>
       <span  id="MomFilemgr" style="position:absolute;margin-left:10px;margin-left:85px\9;margin-top:-1px;margin-top:-3px\9;" >
    </span>
      </div>
  </td>

       
 </tr>
    <tr>
       
    </tr>
    <tr>
       
       <td valign="middle" >
       <div  style="margin-top: 0px;margin-left:10%;display:none;">
       <input type="button" class="easyui-button" value ="Add" id="btnAddNew" style="height:23px"/>
       <span style="padding-left:8px;">
       <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;"/>
       </span>
       
<span  id="MomFilemgr" style="position:absolute;margin-left:10px;margin-left:85px\9;margin-top:-1px;margin-top:-3px\9;" >
      </span>
      </div>
       </td>
     </tr>
   
</table>
     <div style="margin-left:0%;margin-left:39px\9;margin-top:-8px;\0\margin-top:5px;" >
     <div style="margin-left:75%;margin-left:60%\9;">
     
     </div>
     <div style="margin-left:14px;margin-top:10px;">
<table id="momGrid" style=" "> <tr> <td> </td></tr> </table>
</div>
<div style="height:10px;"></div>
</div>
</div>

<div  title="Attendance">
<table style="padding-left:20px;padding-top:10px;padding-left:50px\9">
<tr>

<td valign="bottom">
<input type="checkbox" id="chkSelectAll" name="chkSelectAll" style="margin-left:10px;" />
<input type="text" value="Select All" readonly="readonly" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left; " />
</td>

<td valign="top" style="padding-left: 20px;">
<div>
<label>Role</label>
</div>
<div>
  <input class="easyui-combobox" id="cmbMomsRole" name="cmbMomsRole"  style=" width : 260px;"  value=" " />
</div>
    </td>

<td valign="bottom">
<div style="padding-left:20px;">
  <input type="button" class="easyui-button" id="btnAddAllEmployee" name="btnAddAllEmployee" value="Add All Employee" style="height:23px;display:none;"/>
</div>
</td>

<td valign="bottom">
<div style="margin-top:-10px;">
<div style="padding-left:0px;">
  <span style="padding-left:0px;"><label id="pillargrouplblid">Pillar Group</label></span>
</div>
<div>
<span id="spnPillargroup" style="padding-left:0px;">
<input class="easyui-combobox" id="cmbMomsPillargroup" name="cmbMomsPillargroup"  style=" width : 100px;"  value="${requestScope.mom.momsPillargroup}"/>
</span>
</div>
</div>
</td>
<td valign="top" >
<div style="padding-left:20px;margin-top:-4px;">
<div>
<label>Employee</label>
<input type="checkbox" id="chkMomsOthers" name="chkMomsOthers" style="margin-left:10px;" />
   <span>
   <label> Others</label>
   </span>
</div>
<div>
  <input class="easyui-combobox" id="cmbMomsEmployee" name="cmbMomsEmployee"  style=" width : 220px;"  value="" />
</div>
</div>
    </td>
   
    <td valign="top">
      <div style="padding-left:20px;margin-top:10px;">
       <input type="button" class="easyui-button" id="btnAddEmployee" name="btnAddEmployee" value="Add Employee" style="height:23px;"/>
      </div>
    </td>
   

<td valign="top" >
<div style="margin-left:0%">
<input type="button" class="easyui-button" id="btnAttsave" name="btnAttsave" value="Save" style="height:23px;display:none;"/>
<span style="padding-left: 14px;">
<label>External Members</label>
</span>
    <span style="padding-left: 0px;">
    <input type="button" id="btnaddOthers" class="easyui-button" value="..." style="cursor: default;"/>
    </span>
    </div>
    </td>
</tr>
</table>


  <div style="margin-left:24px; margin-left:65px\9;">
 
<table id="attandanceGrid">
<tr><td></td></tr>
</table>
       <div id="pageratt"></div>
   </div>
   <div style="height:20px;"></div>

  </div>
  </div>

   </div>
     
      <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
      <input type="hidden" id="hdndate" name="hdndate"/>
      <input type="hidden" id="hdnsavebtn" name="hdnsavebtn"/>
      <input type="hidden" id="hdnCheckvist" name="hdnCheckvist"/>
      <input type="hidden" id="hdnMetngtitle" name="hdnMetngtitle"/>
      <input type="hidden" id="hdnMomsMtntyp" name="hdnMomsMtntyp" />
      <input type="hidden" id="hdnMomsMtngno" name="hdnMomsMtngno"/>
      <input type="hidden" id="hdnMomsMtngsafety"/>
      <input type="hidden" id="txtMomdMomsKeyid" name="txtMomdMomsKeyid" value="${requestScope.key}"/>
      <input type="hidden" id="txtMomaMomsKeyid" name="txtMomaMomsKeyid" value="${requestScope.key}"/>    <%--  att master Key Id --%>  
      <input type="hidden" id="txtMomsKeyid" name="txtMomsKeyid" value="${requestScope.mstkeyid}"/>
      <input type="hidden" id="meeting" name="meeting" value="${requestScope.mom.momsIsmeetinghappen}"/>
      <input type="hidden" id="meetingatt" name="meetingatt" value="${requestScope.moma. momaAttandance}"/>
      <input type="hidden" id="checkempvalue" name="checkempvalue" value="${requestScope.CHKBOX}"/>
      <input type="hidden" id="hdnVal" name="hdnVal" value="1"/>
      <input type="hidden" id="hdnValcheck" name="hdnValcheck" value="Y"/>
      <input type="hidden" id="hdnRowCoId" name="hdnRowCoId" value=""/>
      <input type="hidden" id="hdnPillarIds" name="hdnPillarIds" />
      <input type="hidden" id="hdnmeethappnd" name="hdnmeethappnd" value="${requestScope.MEETHAPPEN}"/>
      <input type="hidden" id="hdnDMT" name="hdnDMT" value="${requestScope.DMT}"/>
      <input type="hidden" id="hdnDMTDBLE" name="hdnDMTDBLE" value="${requestScope.DMTDBLE}"/>
      <input type="hidden" id="hdnnewaddemp" name="hdnnewaddemp" />
      <input type="hidden" id="hdnactnmode" name="hdnactnmode" value=""/>
      <input type="hidden" id="hdnrowid" name="hdnrowid" value=""/>
      <input type="hidden" id="hdnMomsRefdocid" name="hdnMomsRefdocid" value="${requestScope.momRefDocId}"/>
 <input type="hidden" id="hdnMomsRefdoctype" name="hdnMomsRefdoctype" value="${requestScope.momRefDocType}"/>    
      <input type="hidden" id="hdnAttRole" name="hdnAttRole" value=""/>  
      <input type="hidden" id="hdnaddemp" name="hdnaddemp" value=""/>
      <input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>
      <input type="hidden" id="hdntabselect" name="hdntabselect" value="N"/>
      <input type="hidden" id="hdnroledata" name="hdnroledata" value=""/>
      <input type="hidden" id="hdnrecall" name="hdnrecall" value="${requestScope.recall}"/>
      <input type="hidden" id="hdnMno" name="hdnMno" value="${requestScope.Mno}"/>
      <input type="hidden" id="hdnIsmthpn" name="hdnIsmthpn" value="${requestScope.Ismthpn}"/>
      <input type="hidden" id="hdnsfty" name="hdnsfty" value="${requestScope.sfty}"/>
      <input type="hidden" id="hdnrmrk" name="hdnrmrk" value="${requestScope.rmrk}"/>
      <input type="hidden" id="hdntitle" name="hdntitle" value="${requestScope.title}"/>
      <input type="hidden" id="hdnmtype" name="hdnmtype" value="${requestScope.mtype}"/>
      <input type="hidden" id="hdnagnda" name="hdnagnda" value="${requestScope.agnda}"/>
      <input type="hidden" id="hdnpillarid" name="hdnpillarid" value="${requestScope.pillarid}"/>
      <input type="hidden" id="hdnpillargrpid" name="hdnpillargrpid" value="${requestScope.pillargrpid}"/>
      
      <input type="hidden" id="hdnpillargrpval" name="hdnpillargrpval" value=""/>
      
      <input type="hidden" id="hdnKeyId" name="hdnKeyId" value="${requestScope.momkeyid}"/>
      <input type="hidden" id="hdnmomdate" name="hdnmomdate" value=" "/>
      <input type="hidden" id="hdnMenuMode" name="hdnMenuMode" value="${requestScope.menumode}"/>
  </form>
