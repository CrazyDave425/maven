<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>密码修改页面</span>
            </div>
            <div class="providerAdd">
                <form id="userForm" name="userForm" method="post" action=" ">
                    <input type="hidden" name="method" value="savepwd">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <div class="info">${message}</div>
                    <div class="">
                        <input type="hidden" id="id" value="<%=request.getParameter("id")%>">
                        <label for="oldPassword">旧密码：</label>
                        <input type="password" name="oldpassword" id="oldpassword" value=""> 
						<font color="red"></font>
                    </div>
                    <div>
                        <label for="newPassword">新密码：</label>
                        <input type="password" name="newpassword" id="newpassword" value=""> 
						<font color="red"></font>
                    </div>
                    <div>
                        <label  >确认新密码：</label>
                        <input type="password" name="rnewpassword" id="rnewpassword" value=""> 
						<font color="red"></font>
                    </div>
                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="button" name="save" id="save" value="保存" class="input-button">
                    </div>
                </form>
            </div>
        </div>
    </section>
<%@include file="/jsp/common/foot.jsp" %>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/pwdmodify.js"></script>--%>
<script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
<script>
    $("input[name='newPassword']").blur(function () {
        if ($(this).val()==""){
            $(this).next().html("密码不能为空")
        } else {
            $(this).next().html("")
        }
    })
    $("input[name='rnewpassword']").blur(function () {
        if ($(this).val()==""){
            $(this).next().html("二次密码不能为空")
        }
        var oldpassword=$("input[name='oldpassword']").val();
        if ($(this).val()!=oldpassword){
            $(this).next().html("两次密码不一致")
        }else {
            $(this).next().html("")
        }
    })
    $("input[name='oldpassword']").blur(function () {
        if ($(this).val()==""){
            $(this).next().html("原始密码不能为空")
        }
        $.ajax({
            "url":"${pageContext.servletContext.contextPath}/checkPwd?id=<%=request.getParameter("id")%>&&oldpassword="+$("#oldpassword").val(),
            "type":"post",
            "dataType":"text",
            "success":function (result){
                if (result=="true"){
                    $("#oldpassword").next().html("√").css("color","green")
                }else {
                    $("#oldpassword").next().html("×").css("color","red")
                }
            },
            "error":function (){
                alert("服务器异常")
            }
        })
    })
    $("input[type='button']").click(function () {
        $.ajax({
            "url":"${pageContext.servletContext.contextPath}/updatePwd?id=<%=request.getParameter("id")%>&&newpassword="+$("#newpassword").val(),
            "type":"post",
            "dataType":"text",
            "success":function (result){
                if (result=="true"){
                  location.href="providerlist.jsp"
                }else {
                    location.href="jsp/pwdmodify.jsp"
                }
            },
            "error":function (){
                alert("服务器异常")
            }
        })
    })
</script>