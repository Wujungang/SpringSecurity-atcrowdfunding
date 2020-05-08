<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<%@ include file="/WEB-INF/jsp/common/css.jsp" %>
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container ">

      <form id="loginForm" class="form-signin" role="form" action="${PATH }/doLogin" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
	       	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	       	  <div class="form-group has-success has-feedback" >
					${SPRING_SECURITY_LAST_EXCEPTION.message }
			  </div> 
		  </c:if>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control  layui-anim layui-anim-up" id="loginacct" value="aaa" name="loginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div> 
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="userpswd" name="userpswd" value="123456" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  
		  <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
		  
		  	<input type="text" class="layui-input layui-icon layui-icon-date autofocus" id="test1" name="date" placeholder="请选择登陆日期">
		  	<span><i class="layui-icon layui-icon-date"></i></span>
		  	<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #1E9FFF;"></i> 
		  </div>
		  
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="reg.html">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <%@ include file="/WEB-INF/jsp/common/js.jsp" %>
    <script>
    function dologin() {
       $("#loginForm").submit();
    }
    </script>
    
    <script>
		layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#test1' //指定元素
		  });
		});
	</script>
  </body>
</html>