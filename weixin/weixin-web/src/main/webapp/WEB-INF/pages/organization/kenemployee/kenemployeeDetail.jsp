<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
    <style type="text/css">
        body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
<script type="text/javascript"> 
         $(function(){
            $("#brBranchId").bind('click',function(){
               dialog();
               $("#brBranchId").blur();            
            });
            $("#subButton").bind('click',function(){
                if(!$("#form1").valid()){return;}; 
                var action = $('#form1').attr('action');
                var data = $("#form1").serialize();
                alert(action);
                $.ajax({
                    url:action,
                    data:data,
                    type:'POST'
                });
            });
        });
        function dialog(){
            var reData = showModalDialog('${path}acdLookup/kenBankbranch.action','',"dialogWidth:35em; dialogHeight:30em;");
            if(reData.buttonType=="submit"){
                $("#brBranchId").val(reData.brBranchId);
            }else if(reData.buttonType=="clear"){
                $("#brBranchId").val('');
            }else{
            
            }
        }
    </script>      
    
</head>
<body style="padding:10px;">
<div id="search">
  			<div class="search-bar-top">
		  		<div><sc:message key="kenEmployeedetail"/></div>
		  	</div>
   </div>
    <sc:form action="${path}kenEmployeeAction/${detailTag==null?'add.action':'update.action'}" id="form1">
        <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
             <tr>
                <td class="form_label"><fmt:message key="employeeId"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="kenEmployee.id.bankorgId" value="${kenEmployee.id.bankorgId }" type="hidden"/>
                    <input type="text" name="kenEmployee.id.employeeId" value="${kenEmployee.id.employeeId }" type="text" id="txtName" ltype="text" validate="{required:true,maxlength:10}" ${detailTag==null?'':'readonly'} />
                </td>
                <td class="form_label"><fmt:message key="brBranchId"/>:</td>
                <td align="left" class="l-table-edit-td"><input type="text" id="brBranchId" name="kenEmployee.brBranchId" value="${kenEmployee.brBranchId}" /></td>

                <td class="form_label"><fmt:message key="employeeName"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="employeeName" type="text" name="kenEmployee.employeeName" value="${kenEmployee.employeeName }"  validate="{maxlength:20}" type="text" />      
                </td>
              </tr>
           
            <tr>
                <td class="form_label"><fmt:message key="gender"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="kenEmployee.gender" value="${kenEmployee.gender }" />
                </td>
                <td class="form_label"><fmt:message key="birthDate"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <sc:date type="text" id="birthDate" name="kenEmployee.birthDate" value="${kenEmployee.birthDate }" format="yy-MM-dd"/>
                </td>
               <td class="form_label"><fmt:message key="empStatus"/>:</td>
                <td align="left" class="l-table-edit-td"><input name="kenEmployee.empStatus" value="${kenEmployee.empStatus}" type="text" /></td>
</tr>
           
            <tr>
               <td class="form_label"><fmt:message key="leaveInd"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <sc:dict dictId="YorN" name="kenEmployee.leaveInd" empty="false" value="${kenEmployee.leaveInd }"/>
                </td>
              <td class="form_label"><fmt:message key="leavStartDate"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <sc:date id="leaveStartDate" type="text" name="kenEmployee.leaveStartDate" value="${kenEmployee.leaveStartDate }" format="yy-MM-dd"/></td>

                <td class="form_label"><fmt:message key="leaveEndDate"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <sc:date id="leaveEndDate" type="text" name="kenEmployee.leaveEndDate" value="${kenEmployee.leaveEndDate}" format="yy-MM-dd"/>
                </td>
                </tr>
                <tr>
               <td class="form_label"><fmt:message key="joiningDate"/>:</td>
                <td align="left" class="l-table-edit-td"><sc:date id="joiningDate" name="kenEmployee.joiningDate" value="${kenEmployee.joiningDate}" type="text"  format="yy-MM-dd"/></td>
            
               <td class="form_label"><fmt:message key="dismissDate"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <sc:date id="dismissDate" type="text" name="kenEmployee.dismissDate" value="${kenEmployee.dismissDate }"  format="yy-MM-dd"/>      
                </td>
               <td class="form_label"><fmt:message key="countryAlphaId"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="kenEmployee.countryAlphaId" value="${kenEmployee.countryAlphaId }" /></td>
            </tr>
           
            <tr>
              <td class="form_label"><fmt:message key="stateId"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input type="text" name="kenEmployee.stateId" value="${kenEmployee.stateId }" />
                </td>
               <td class="form_label"><fmt:message key="cityId"/>:</td>
                <td align="left" class="l-table-edit-td"><input name="kenEmployee.cityId" value="${kenEmployee.cityId}" type="text" /></td>
               <td class="form_label"><fmt:message key="homeAddress1"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.homeAddress1" value="${kenEmployee.homeAddress1 }"  type="text" />      
                </td>
                </tr><tr>
               <td class="form_label"><fmt:message key="homeAddress2"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.homeAddress2" value="${kenEmployee.homeAddress2 }"  type="text" />      
                </td>
                <td class="form_label"><fmt:message key="homeAddress3"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.homeAddress3" value="${kenEmployee.homeAddress3 }"  type="text" />      
                </td>
              <td class="form_label"><fmt:message key="homeAddress4"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.homeAddress4" value="${kenEmployee.homeAddress4 }"  type="text" />      
                </td>
            </tr>

            <tr>
                <td class="form_label"><fmt:message key="homeTelphone"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="kenEmployee.homeTelphone" value="${kenEmployee.homeTelphone }" /></td>
               <td class="form_label"><fmt:message key="homePostCode"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input type="text" name="kenEmployee.homePostCode" value="${kenEmployee.homePostCode }" />
                </td>
                <td class="form_label"><fmt:message key="email"/>:</td>
                <td align="left" class="l-table-edit-td"><input name="kenEmployee.email" value="${kenEmployee.email}" type="text" /></td>
             
              </tr>  

              <tr>
               <td class="form_label"><fmt:message key="fanxo"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.faxno" value="${kenEmployee.faxno }"  type="text" />      
                </td>
           
                <td class="form_label"><fmt:message key="mobileNo"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="kenEmployee.mobileNo" value="${kenEmployee.mobileNo }" type="text"/></td>
                <td class="form_label"><fmt:message key="officeName"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input type="text" name="kenEmployee.officePhone" value="${kenEmployee.officePhone }" type="text"/>
                </td>
            </tr>   
             <tr>
                <td class="form_label"><fmt:message key="idtypeId"/>:</td>
                <td align="left" class="l-table-edit-td"><input name="kenEmployee.idtypeId" value="${kenEmployee.idtypeId}" type="text" /></td>
                <td class="form_label"><fmt:message key="idNo"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.idNo" value="${kenEmployee.idNo }"  type="text" />      
                </td>
            <td class="form_label"><fmt:message key="positionId"/>:</td>
                <td align="left" class="l-table-edit-td"><input name="kenEmployee.positionId" value="${kenEmployee.positionId}" type="text" /></td>
           </tr><tr>
               <td class="form_label"><fmt:message key="proflTitleId"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="kenEmployee.proflTitleId" value="${kenEmployee.proflTitleId }"  type="text" />      
                </td>
                <td class="form_label">
				</td><td>
				</td>
				<td class="form_label">
				</td><td>
				</td>
            </tr>  
             <tr style="height:50px">
				     <td colspan="6">
<input type="submit" value="<fmt:message key="submit"/>" id="subButton" style = "float:right;margin:4px;" class="l-button l-button-submit" /> 
</td>
            </tr>
        </table>
 </sc:form>
</body>    
</html>