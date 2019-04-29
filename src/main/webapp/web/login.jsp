<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>學子商城登錄頁面</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../images/header/logo.png" alt=""/>
        <span>歡迎登錄</span>
    </div>
</header>
<div id="container">
    <div id="cover" class="rt">
        <form id="login-form" method="post" name="form1">
            <div class="txt">
                <p>
					登錄學子商城<span><a href="register.do">新用戶註冊</a></span>
                </p>
                <div class="text">
                    <input type="text" placeholder="請輸入您的用戶名" name="lname" id="username" required>
                    <span><img src="../images/login/yhm.png"></span>
                </div>
                
                <div class="text">
                    <input type="password" id="password" placeholder="請輸入您的密碼" name="lwd" required minlength="6" maxlength="15">
                    <span><img src="../images/login/mm.png"></span>
                </div>
                <div class="chose">
                    <input type="checkbox" class="checkbox" id="ck_rmbUser"/>自動登入
                    <span>忘記密碼？</span>
                </div>
                <input class="button_login" type="button" value="登入" id="bt-login"/>
            </div>
        </form>
    </div>
</div>
<!--错误提示-->
<div id="showResult"></div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../jquery/jquery.cookie.js"></script>
<script>
	$("#username").blur(function(){
        var data = $("#username").val();
        if (data == null || data == "") {
            $("#showResult").text("用戶名不能為空！");
            $("#showResult").css("color","red");
            return false;
        }
    });
	
	$("#username").focus(function() {
		$("#showResult").html("");
	});;
</script>
<script>
    $('#bt-login').click(function(){
        // 提交登入
//         var userdata = '{' +
//         	'"username":' + $("#username").val() + ',' +
//         	'"password":' + $("#password").val() +
//         '}';
		var userdata = "username=" + $("#username").val() + "&password=" + $("#password").val();
		
        $.ajax({
        	"url": "handle_login.do",
        	"data": userdata,
        	"type": "POST",
        	"dataType": "json",
        	"success": function(jsonObj) {
        		if(jsonObj.state == 1) {
        			// 登入成功, 保存cookie
        			saveCookie();
        			// 登入成功, 則跳轉到主頁
        			location.href = "../main/index.do";
        			
        		} else {
        			// 登入失敗, 用戶名或密碼錯誤
        			$("#showResult").html(jsonObj.message);
        			$("#showResult").css("color", "#f00");
        		}
        	}
        });
    });
</script>
<script type="text/javascript">
// 頁面加載完成後, 判斷在cookie中是否有"自動登入"相關信息
// 如果有, 則勾上自動登入, 並對username password設置默認值(cookie中取出username和password)
    $(document).ready(function () {
        if ($.cookie("rmbUser") == "true") {
            $("#ck_rmbUser").attr("checked", true);
            $("#username").val($.cookie("username"));
            $("#password").val($.cookie("password"));
        }
    });

    //记住用户名密码
    function saveCookie() {
    	// 判斷是否勾選了"自動登入"
        if ($("#ck_rmbUser").prop("checked")) {
        	// 勾選了自動登入
        	// 獲取輸入框中的內容
            var str_username = $("#username").val();
            var str_password = $("#password").val();
            // 在Cookie中記錄"自動登入", "用戶名"和"密碼"
            $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("username", str_username, { expires: 7 });
            $.cookie("password", str_password, { expires: 7 });
        } else {
        	// 沒有勾選自動登入
        	// 設置或清除cookie中的數據, 並設置過期
            $.cookie("rmbUser", "false", { expire: -1 });
            $.cookie("username", "", { expires: -1 });
            $.cookie("password", "", { expires: -1 });
        }
    };
</script>
</body>
</html>