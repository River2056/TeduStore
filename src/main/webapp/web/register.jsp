<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>學子商城註冊页面</title>
  <link href="../css/header.css" rel="stylesheet"/>
  <link href="../css/footer.css" rel="stylesheet"/>
  <link href="../css/animate.css" rel="stylesheet"/>
  <link href="../css/login.css" rel="stylesheet" />
</head>
<body>
<!-- 页面顶部-->
<header id="top">
  <div class="top">
    <img src="../images/header/logo.png" alt=""/>
    <span>歡迎註冊</span>
  </div>
</header>
<div class="parent">
  <!--<video src="audio/snow.mp4" width="100%" autoplay loop muted></video>-->
  <div class="container">
    <div class="panel rt">
      <form id="form-register" method="post" action="">
        <div class="txt">
          <p>新用戶註冊
            <span>
              <a href="login.do">直接登錄</a>
            </span>
          </p>
        </div>
        <div class="form-group">
          <label for="uname">用戶名：</label>
          <input autocomplete required minlength="6" maxlength="9" type="text" placeholder="請輸入用戶名" autofocus name="uname" id="uname"/>
          <span class="msg-default" id="username_hint">用戶名長度在6到9位之間</span>
        </div>
        <div class="form-group">
          <label for="upwd">登錄密碼：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="請輸入密碼" name="upwd" autofocus id="upwd"/>
          <span class="msg-default hidden">密碼長度在6到12位之間</span>
        </div>
		 <div class="form-group">
          <label for="upwdconfirm">確認密碼：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="請確認密碼" name="upwdconfirm" autofocus id="upwdconfirm"/>
          <span class="msg-default hidden">密碼長度在6到12位之間</span>
        </div>
        <div class="form-group">
          <label for="email">電子信箱：</label>
          <input autocomplete required type="email" placeholder="請输入電子信箱" name="email" id="email"/>
          <span class="msg-default hidden" id="email_hint">請输入合法的電子信箱</span>
        </div>
        <div class="form-group">
          <label for="phone">手機號碼：</label>
          <input id="phone" name="phone" placeholder="請输入您的手機號碼" pattern="(\d{10})|^((\d{4})-(\d{3})-(\d{3})|(\d{4})-(\d{6}))$" required type="text" />
          <span class="msg-default hidden" id="phone_hint">請输入合法的手機號碼</span>
        </div>
        <div class="form-group">
          <label></label>
          <input type="button" value="提交註冊訊息" id="bt-register"/>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

<!--弹出的小广告-->
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
  $('#bt-register').click(function(){
    var count = 0;
    $('.form-group').each(function(){
      if($(this).find('span').hasClass('msg-success')){
        count++;
      }
    });
      if(count == 5){
        $.ajax({
        	"url": "handle_register.do",
        	"data": $("#form-register").serialize(),
        	"type": "POST",
        	"dataType": "json",
        	"success": function(jsonObj) {
        		if(jsonObj.state == 1) {
        			// 註冊成功
        			location.href = "login.do";
        		} else {
        			// 註冊失敗
        			alert(jsonObj.message);
        			$("#username_hint").html(jsonObj.message);
        			$("#username_hint").attr("class", "msg-error");
        		}
        	}
        });
      }
  })
</script>
<script>
  /*1.对用户名进行验证*/
  uname.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '用戶名不能為空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('用戶名不能為空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '用戶名不能少於6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('用戶名不能少於6位');
    }else {
      this.nextElementSibling.innerHTML = '用戶名格式正確';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
      var data =document.getElementById("uname").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户名是否已经存在**/
      /* 使用jQuery發AJAX異步GET請求檢查用戶名是否已被占用 */
      $.ajax({
    	  "url": "check_username.do",
    	  "data": "username=" + $("#uname").val(),
    	  "type": "GET",
    	  "dataType": "json",
    	  "success": function(jsonObj) {
    		  var cls = jsonObj.state == 1 ? "msg-success" : "msg-error";
    		  
    		  $("#username_hint").html(jsonObj.message);
    		  // set class attribute to appropiate class
    		  $("#username_hint").attr("class", cls);
    	  }
      });
    }
  }

  uname.onfocus = function(){
    this.nextElementSibling.innerHTML = '用戶名長度在6到9位之間';
    this.nextElementSibling.className = 'msg-default';
  }
  upwd.onfocus = function(){
    this.nextElementSibling.innerHTML = '密碼長度在6到12位之間';
    this.nextElementSibling.className = 'msg-default';
  }
  upwd.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '密碼不能為空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密碼不能為空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '密碼長度盡量别少於6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密碼長度盡量别少於6位');
    }else {
      this.nextElementSibling.innerHTML = '密碼格式正確';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
    }
  }
  

  upwdconfirm.onfocus = function(){
    this.nextElementSibling.innerHTML = '密碼長度在6到12位之間';
    this.nextElementSibling.className = 'msg-default';
  }
  upwdconfirm.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '密碼不能為空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密碼不能為空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '密碼長度盡量别少於6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密碼長度盡量别少於6位');
    }else {
      this.nextElementSibling.innerHTML = '密碼格式正確';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
    }
  }
  
  /*3.对邮箱地址进行验证*/
  email.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '電子信箱不能為空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('電子信箱不能為空');
    }else if(this.validity.typeMismatch){
      this.nextElementSibling.innerHTML = '電子信箱格式不正確';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('電子信箱格式不正確');
    }else {
      this.nextElementSibling.innerHTML = '電子信箱格式正確';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');

      var data =document.getElementById("email").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户名是否已经存在**/
      /* 使用jQuery發AJAX異步GET請求檢查電子信箱是否已被占用 */
      $.ajax({
    	  "url": "check_email.do",
    	  "data": "email=" + $("#email").val(),
    	  "type": "GET",
    	  "dataType": "json",
    	  "success": function(jsonObj) {
    		  var cls = jsonObj.state == 1 ? "msg-success" : "msg-error";
    		  
    		  $("#email_hint").html(jsonObj.message);
    		  // set class attribute to appropiate class
    		  $("#email_hint").attr("class", cls);
    	  }
      });
    }
  }
  email.onfocus = function(){
    this.nextElementSibling.innerHTML = '請輸入合法的電子信箱';
    this.nextElementSibling.className = 'msg-default';
  }
  /*4.对手机号进行验证*/
  phone.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '手機號碼不能為空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('手機號碼不能為空');
    }else if(this.validity.patternMismatch){
      this.nextElementSibling.innerHTML = '手機號碼格式不正確';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('手機號碼格式不正確');
    }else {
      this.nextElementSibling.innerHTML = '手機號碼格式正確';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');

      var data =document.getElementById("phone").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户名是否已经存在**/
      /* 使用jQuery發AJAX異步GET請求檢查手機號碼是否已被占用 */
      $.ajax({
    	  "url": "check_phone.do",
    	  "data": "phone=" + $("#phone").val(),
    	  "type": "GET",
    	  "dataType": "json",
    	  "success": function(jsonObj) {
    		  var cls = jsonObj.state == 1 ? "msg-success" : "msg-error";
    		  
    		  $("#phone_hint").html(jsonObj.message);
    		  // set class attribute to appropiate class
    		  $("#phone_hint").attr("class", cls);
    	  }
      });
    }
  }
  phone.onfocus = function(){
    this.nextElementSibling.innerHTML = '請輸入合法的手機號碼';
    this.nextElementSibling.className = 'msg-default';
  }
</script>
</body>
</html>