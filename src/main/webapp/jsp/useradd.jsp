<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<%
if(request.getAttribute("role")==null) request.getRequestDispatcher("/RoleList").forward(request,response);
%>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>


<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post"  enctype="multipart/form-data" >
				<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" value=""> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="ruserPassword" id="ruserPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label >用户性别：</label>
					<select name="gender" id="gender">
					    <option value="1" selected="selected">男</option>
					    <option value="2">女</option>
					 </select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" 
					readonly="readonly" onclick="WdatePicker();">
					<font color="red"></font>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <input name="address" id="address"  value="">
                </div>
                <div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<select name="userRole" id="userRole">
<%--                        <option value="1">系统管理员</option>--%>
<%--                        <option>经理</option>--%>
<%--                        <option>员工</option>--%>
    <C:forEach items="${role}" var="list">
        <option value=" ${list.id}">${list.roleName}</option>
    </C:forEach>
                    </select>
	        		<font color="red"></font>
                </div>
                <div>
                    <label>头像</label>
                    <input type="file" name="pic" id="pic" value="上传头像">
                    <img id="uploadImg" style="width: 300px;height: 200px;margin-left: 30px"/>
                    <font color="red"></font>
                </div>
<%--                <p>--%>
<%--                    <label> 上传图片 </label>--%>
<%--                    <input id="pic" name="file" type="file" class="opt_input" />--%>
<%--                    <br>--%>
<%--                    <img id="uploadImg" style="width: 300px;height: 200px;margin-left: 30px"/>--%>
<%--                </p>--%>

                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/useradd.js"></script>
<script>
    $("#pic").change(function () {
        //获取file文件对象
        var file = $(this)[0].files[0];
        //等价于document.getElementById("pic").files[0];
        //获取一个指向该元素的地址
        var path = window.URL.createObjectURL(file);
        console.log(path);
        $("#uploadImg").attr('src', path);
    })
    //提交按钮绑定点击事件
    $("#add").click(function () {
        //通过jquery的方法获取表单数据
        //var params = $("#newsAddForm").serialize();//key=value&key2=value2&....

        //创建表单数据，通过键值对来添加一系列表单控件，支持异步上传二进制文件
        var formData = new FormData($("#userForm")[0]);
//        formData.append("file", $("#pic")[0].files[0]);//添加属性
//        console.log(formData.get("file"));//获取属性
        $.ajax({
            "url": "${pageContext.servletContext.contextPath}/addUser",
            "type": "post",
            "data": formData,
            "dataType": "text",
            "processData": false,
            "contentType": false,
            "success": function (result){
                alert(result)
                if (result == "true") {
                    alert("添加用户成功");
                    location.href = "${pageContext.servletContext.contextPath}/list";
                } else {
                    alert("添加用户失败");
                }
            },
            "error": function () {
                alert("服务器异常，请稍后重试。。")
            }
        });
    })
</script>

</script>
