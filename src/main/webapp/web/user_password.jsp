<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的訂單 - 達內學子商城</title>
    <link href="../css/orders.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <link href="../css/common.css" rel="stylesheet" />
    
</head>
<body>

<!-- 頂部區域 -->
<c:import url="header.jsp"></c:import>

<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首頁<span>&gt;</span>個人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">

    <!-- 左邊欄-->
    <c:import url="user_left_side_bar.jsp"></c:import>
    
    <!-- 右邊欄-->
    <!--個人信息頭部-->
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span ><a href="profile.do">我的信息</a></span>
            <span class="rs_header_active"><a href="password.do">安全管理</a></span>
        </div>

        <!--安全管理 -->
        <div class="rs_content">
            <p class="change_password_title">更改密碼</p>
            <div class="new_password">
                <span class="word">輸入舊密碼：</span>
                <input id="old_password" type="password" maxlength="32"/>
                <span id="old_password_hint" class="change_hint"></span>
            </div>
            
            <div class="new_password">
                <span class="word">輸入新密碼：</span>
                <input id="new_password" type="password" maxlength="32"/>
                <span id="new_password_hint" class="change_hint"></span>
            </div>
            
            <div class="confirm_password">
                <span class="word">確認新密码：</span>
                <input id="confirm_password" type="password" maxlength="32"/>
                <span id="confirm_password_hint" class="confirm_hint"></span>
            </div>
            
            <div class="button-block">
                <a class="button-blue" href="#" onclick="changePassword()">保存更改</a>
            </div>
            
        </div>

    </div>
</div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

</body>
<style>
.msg-error { background: #d00; color: #fff; padding: 5px; border-radius: 4px;}
.msg-correct { background: #0d0; color: #fff; padding: 5px; border-radius: 4px; }
</style>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript">
// ===== 修改密碼相關的驗證 =====
// 規則1: 密碼至少6位長度
// 規則2: 2次輸入的新密碼必須一致
// 事件1: 適用於3個密碼的輸入框, 當丟失焦點時, 驗證"規則1"
// 事件2: 適用於2個新密碼輸入框, 當丟失焦點時, 驗證"規則2"
function changePassword() {
	// 獲取3個密碼輸入框的值
	var pwd1 = $("#old_password").val();
	var pwd2 = $("#new_password").val();
	var pwd3 = $("#confirm_password").val();
	// 基本判斷
	if(checkPasswordLength(pwd1) && 
			checkPasswordLength(pwd2) && 
			checkPasswordLength(pwd3) && 
			checkPasswordEquals()) {
		// 同時滿足2個規則
		$.ajax({
			"url": "handle_change_password.do",
			"data": "old_password=" + pwd1 + "&new_password=" + pwd2,
			"type": "POST",
			"dataType": "json",
			"success": function(jsonObj) {
				alert(jsonObj.message);
				$("#old_password").val("");
				$("#new_password").val("");
				$("#confirm_password").val("");
				$("#old_password_hint").html("").removeClass();
				$("#new_password_hint").html("").removeClass();
				$("#confirm_password_hint").html("").removeClass();
			},
			"error": function() {
				alert("出現未知錯誤!");
				location.href = "../main/index.do";
			}
		});
		
	} else {
		// 有規則不滿足
		alert("請檢察錯誤, 再重新提交!");
	}
	
}

function checkPasswordLength(pwd) {
	return pwd.length >= 6;
}

function checkPasswordEquals() {
	// 驗證兩個新密碼是否一致
	var pwd1 = $("#new_password").val();
	var pwd2 = $("#confirm_password").val();
	if(pwd1 == pwd2) {
		// 2個新密碼一致
		$("#confirm_password_hint").html("密碼一致");
		$("#confirm_password_hint").attr("class", "msg-correct");
		return true;
	} else {
		// 2個密碼不一致
		$("#confirm_password_hint").html("兩次輸入的密碼不一致");
		$("#confirm_password_hint").attr("class", "msg-error");
		return false;
	}
	
}

// 原密碼丟失焦點時
$("#old_password").blur(function() {
	// 獲取密碼
	var pwd = $("#old_password").val();
	if(checkPasswordLength(pwd)) {
		// 長度OK
		$("#old_password_hint").html("密碼格式正確");
		$("#old_password_hint").attr("class", "msg-correct");
	} else {
		// 長度不夠
		$("#old_password_hint").html("密碼的長度必須為6位以上");
		$("#old_password_hint").attr("class", "msg-error");
	}
});

// 新密碼丟失焦點時
$("#new_password").blur(function() {
	// 獲取密碼
	var pwd = $("#new_password").val();
	if(checkPasswordLength(pwd)) {
		// 長度OK
		$("#new_password_hint").html("密碼格式正確");
		$("#new_password_hint").attr("class", "msg-correct");
		// 長度確認過後, 調用檢查密碼
		if(pwd != "" && $("#confirm_password").val() != "") {
			checkPasswordEquals();
			return true;
			
		}
	} else {
		// 長度不夠
		$("#new_password_hint").html("密碼的長度必須為6位以上");
		$("#new_password_hint").attr("class", "msg-error");
		return false;
	}
	
});

// 確認密碼丟失焦點時
$("#confirm_password").blur(function() {
	// 獲取密碼
	var pwd = $("#confirm_password").val();
	if(checkPasswordLength(pwd)) {
		// 長度OK
		$("#confirm_password_hint").html("密碼格式正確");
		$("#confirm_password_hint").attr("class", "msg-correct");
		// 長度確認過後, 調用檢查密碼
		if(pwd != "" && $("#new_password").val() != "") {
			checkPasswordEquals();
			return true;
			
		}
		
	} else {
		// 長度不夠
		$("#confirm_password_hint").html("密碼的長度必須為6位以上");
		$("#confirm_password_hint").attr("class", "msg-error");
		return false;
	}
	
});

// ===== 左邊欄默認顯示"帳號管理"區塊
$(function() {
	$("#leftsidebar_box dd").hide();
	$("#leftsidebar_box .count_managment dd").show();
	$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
	$("#leftsidebar_box .count_managment").find('img').attr("src","../images/myOrder/myOrder1.png");
	
});
	
</script>
</html>