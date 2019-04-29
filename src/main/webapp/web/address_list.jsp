<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- /address/list.do -->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的訂單 - 達內學子商城</title>
    <link href="../css/orders.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <link href="../css/common.css" rel="stylesheet" />
</head>
<body>

<!-- 頂部區域 -->
<c:import url="header.jsp"></c:import>

<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="../main/index.do">首頁<span>&gt;</span>個人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">

    <!-- 左邊欄-->
    <c:import url="user_left_side_bar.jsp"></c:import>
    
    <!-- 右邊欄-->
    <div class="rightsidebar_box rt">	
        <!--标题栏-->
        <div class="rs_header">
            <span class="address_title">收貨地址管理</span>
        </div>
        
        
            <!--已有地址栏-->
            <div class="address_list">
                <div class="header">
                    <span class="tag">地址名稱</span>
                    <span class="name">姓名</span>
                    <span class="addr">地址詳情</span>
                    <span class="phone">聯繫電話</span>
                    <span class="operation">操作</span>
                </div>
                
                <div id="address_list">
	                <div class="content content_active">
	                    <span class="tag tag_active">辦公室</span>
	                    <span class="name">楊洋</span>
	                    <span class="addr">北京市海淀區北下关街道中鼎大厦B座331</span>
	                    <span class="phone">18435110514</span>
	                    <span class="operation">
	                    	<a href="###">修改</a>
	                    	<span>|</span>
	                    	<a href="###">删除</a>
	                    </span>
	                    <span class="set_default"></span>
	                </div>
	                
	                <div class="content">
	                    <span class="tag tag_normal">家里</span>
	                    <span class="name">楊洋</span>
	                    <span class="addr">北京市大興區西紅門鎮瑞海家園</span>
	                    <span class="phone">13788882346</span>
	                    <span class="operation">
	                    	<a href="###">修改</a>
	                    	<span>|</span>
	                    	<a href="###">删除</a>
	                    </span>
	                    <span class="set_default">
	                    	<a href="###">設為默認</a>
	                    </span>
	                </div>
	                
	                <div class="content">
	                    <span class="tag tag_normal">宿舍</span>
	                    <span class="name">楊洋</span>
	                    <span class="addr">台灣省新北市板橋區縣民大道三段33巷9弄17號</span>
	                    <span class="phone">13788882346</span>
	                    <span class="operation">
	                    	<a href="###">修改</a>
	                    	<span>|</span>
	                    	<a href="###">删除</a>
	                    </span>
	                    <span class="set_default">
	                    	<a href="###">設為默認</a>
	                    </span>
	                </div>
				</div>
				
            </div>
            
        	<!-- "新增收貨地址"按鈕 -->
        	<div class="button-block">
                <a class="button-blue" href="#" onclick="showPopup(0)">新增收貨地址</a>
            </div>
            
        </div>
    </div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

<!-- 彈出窗口 -->
<div id="mask"></div>
<div id="popup_content">
    
    <!-- 標題 -->
    <h3>新增收貨地址</h3>
    
    <!-- 表單 -->
    <!--收货人信息填写栏-->
        <div class="rs_content">
        	<form method="post" action="" id="address-form">
        	
	            <!--收货人姓名-->
	            <div class="recipients">
	                <span class="red">*</span>
	                <span class="kuan">收貨人：</span>
	                <input type="text" name="recvName" id="recvName"/>
	            </div>
	            
	            <!--收货人所在城市等信息-->
	            <div data-toggle="distpicker" class="address_content">
					 <span class="red">*</span>
					 <span class="kuan">省&nbsp;&nbsp;份：</span>
					 <select data-province="---- 選擇省 ----" id="recvProvince" name="recvProvince" onchange="getCities(-1, -1)"
					  style="width: 120px;"></select>
					  城市：<select data-city="---- 選擇市 ----" id="recvCity" name="recvCity" onchange="getAreas(-1)"
					  style="width: 120px;"></select>
					  區/縣：<select data-district="---- 選擇區 ----" id="recvArea" name="recvArea"
					   style="width: 120px;"></select>
				</div> 
	            
	            <!--收货人详细地址-->
	            <div class="address_particular">
	                <span class="red">*</span>
	                <span class="kuan">詳細地址：</span>
	                <textarea name="recvAddr" id="recvAddr" cols="60" rows="3" placeholder="建議您如實填寫詳細收貨地址"></textarea>
	            </div>
	            
	            <!--收货人地址-->
	            <div class="address_tel">
	                <span class="red">*</span>
	                <span class="kuan">手機號碼：</span>
	                <input type="tel" id="recvPhone" name="recvPhone"/>
	                	固定電話：<input type="text" name="recvTel" id="recvTel"/>
	            </div>
	            
	            <!--邮政编码-->
	            <div class="address_postcode">
	                <span class="red">&nbsp;</span>
	                <span>郵政編碼：</span>&nbsp;&nbsp;&nbsp;<input type="text" name="recvZip" id="recvZip"/>
	            </div>
	            
	            <!--地址名称-->
	            <div class="address_name">
	                <span class="red">&nbsp;</span>
	                <span>地址名稱：</span>&nbsp;&nbsp;
	                <input type="text" id="recvTag" name="recvTag"/>
	                	如：	<span class="sp">家</span>
	                		<span class="sp">公司</span>
	                		<span class="sp">宿舍</span>
	            </div>
	            
	            <!--保存收貨人信息-->
	        	<div class="button-block">
	        		<input type="hidden" name="id" id="id"/>
	                <a class="button-blue" href="###" onclick="postForm()">保存收貨人信息</a>
	                <a class="button-blue" href="###" onclick="dismissPopup()" style="margin-left: 20px;">取消</a>
	                <div style="clear: both;"></div>
	            </div>
	
    		</form>
		</div>
	</div>
</body>

<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<!-- <script type="text/javascript" src="../js/distpicker.data.js"></script> -->
<!-- <script type="text/javascript" src="../js/distpicker.js"></script> -->
<script type="text/javascript" src="../js/personal.js"></script>
<script type="text/javascript">
	$(".lxdh_normal").each(function(i,e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
</script>
<script type="text/javascript">
// ===== Template =====
// 地址列表中每條數據的模板(帶有特殊字符用於佔位的HTML代碼)
var htmlTemplate = '<div class="content %CONTENT-ACTIVE%">'
		+ 	'<span class="tag %TAG_TYPE%">%TAG%</span>'
		+ 	'<span class="name">%NAME%</span>'
		+ 	'<span class="addr">%ADDRESS%</span>'
		+ 	'<span class="phone">%PHONE%</span>'
		+ 	'<span class="operation">'
		+ 		'<a href="###" onclick="showPopup(%ID%)">修改</a>'
		+ 		'<span>|</span>'
		+ 		'<a href="###" onclick="deleteAddress(%ID%)">删除</a>'
		+ 	'</span>'
		+ 	'<span class="set_default">'
		+ 		'<a href="###" onclick="setDefault(%ID%)" style="display: %SET_DEFAULT%;">設為默認</a>'
		+ 	'</span>'
		+ 	'</div>' ;
		// replace('%NAME%', newString);

// 設為默認
function setDefault(id) {
	var url = "set_default.do";
	var data = "id=" + id;
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(jsonObj) {
			if(jsonObj.state == 1) {
				showAddressList();
			} else {
				alert(jsonObj.message);
			}
		},
		"error": function() {
			alert("登錄信息已經過期!請重新登錄!");
			location.href = "../user/login.do";
		}
	});
}
		
// 刪除
function deleteAddress(id) {
	var checkIfDelete = confirm("確定要刪除這條收貨地址嗎?刪除操作將不可恢復!");
	var url = "delete.do";
	var data = "id=" + id;
	if(checkIfDelete) {
		$.ajax({
			"url": url,
			"data": data,
			"type": "GET",
			"dataType": "json",
			"success": function(jsonObj) {
				// alert(jsonObj.message);
				if(jsonObj.state == 1) {
					showAddressList();
				}
			},
			"error": function() {
				alert("登錄信息已經過期!請重新登錄!");
				location.href = "../user/login.do";
			}
		});
	}
}

// ===== 顯示地址列表 =====
function showAddressList() {
	var url = "get_list.do";
	$.ajax({
		"url": url,
		"type": "GET",
		"dataType": "json",
		"success": function(jsonObj) {
			// 清空原有列表
			$("#address_list").empty();
			// 聲明變量, 表示將填入到#address_list中
			var htmlString = "";
			// jsonObj是服務器響應的整個json字符串的json對象
			// 應該遍歷jsonObj.data, 決定如何顯示
			for(var i = 0 ; i < jsonObj.data.length ; i++) {
				var address = jsonObj.data[i];
				htmlString += htmlTemplate;
				htmlString = htmlString.replace("%TAG%", address.recvTag);
				htmlString = htmlString.replace("%NAME%", address.recvName);
				htmlString = htmlString.replace("%ADDRESS%", address.recvDistrict + address.recvAddr);
				htmlString = htmlString.replace("%PHONE%", address.recvPhone);
				htmlString = htmlString.replace(/%ID%/g, address.id);
				
				if(address.isDefault == 1) {
					htmlString = htmlString.replace("%CONTENT-ACTIVE%", "content_active");
					htmlString = htmlString.replace("%TAG_TYPE%", "tag_active");
					htmlString = htmlString.replace("%SET_DEFAULT%", "none");
				} else {
					htmlString = htmlString.replace("%CONTENT-ACTIVE%", "");
					htmlString = htmlString.replace("%TAG_TYPE%", "tag_normal");
					htmlString = htmlString.replace("%SET_DEFAULT%", "inline");
				}
				
			}
			$("#address_list").html(htmlString);
		}
	});
}

var actionId;

// 彈出窗口
function showPopup(id) {
	// 清空表單中各控件已有的值
	$("#address-form")[0].reset();
	
	// 將id設置到隱藏域中, 以便於後須一併提交
	$("#id").val(id);
	
    // 根據ID判斷當前操作的類型是增加還是編輯
    var title = id == 0 ? "新增收貨地址" : "修改收貨地址";
    $("#popup_content h3").html(title);
    actionId = id;
    
    // 設置彈出區域尺寸
    var popupWidth = 700;
    var popupHeight = 400;
    var windowWidth = $(window).width();
    var windowHeight = $(document).height();

    // popup mask (use json format for css)
    $("#mask").css({
        "width": windowWidth,
        "height": windowHeight
    });
    $("#mask").show();
    
    // popup window
    $("#popup_content").css({
        "width": popupWidth,
        "height": popupHeight,
        "left": (windowWidth - popupWidth) / 2,
        "top": 120
    });
    $("#popup_content").show();
    // 調用getProvinces()加載省列表
    
    // 發出AJAX請求獲取需要編輯的數據, 並顯示到各控件中
    // 按照編輯模式處理頁面
    if(id != 0) {
    	var url = "get.do";
    	var data = "id=" + id;
    	$.ajax({
    		"url": url,
    		"data": data,
    		"type": "GET",
    		"dataType": "json",
    		"success": function(jsonObj) {
    			var address = jsonObj.data;
    			$("#recvName").val(address.recvName);
    			$("#recvAddr").val(address.recvAddr);
    			$("#recvPhone").val(address.recvPhone);
    			$("#recvTel").val(address.recvTel);
    			$("#recvZip").val(address.recvZip);
    			$("#recvTag").val(address.recvTag);
    			getProvinces(address.recvProvince, address.recvCity, address.recvArea);
    		}
    	});
    } else {
	    // 加載省列表
	    getProvinces(-1, -1, -1);
    	
    }
    
}

// 隱藏彈出窗口
function dismissPopup() {
    $("#mask").hide();
    $("#popup_content").hide();
}

// ===== 加載省列表 =====
function getProvinces(provinceCode, cityCode, areaCode) {
	$("#recvProvince").empty();
	$("#recvCity").empty();
	$("#recvArea").empty();
	var url = "../dict/provinces.do";
	$.ajax({
		"url": url,
		"type": "GET",
		"dataType": "json",
		"success": function(jsonObj) {
			// 添加默認節點
			var op = document.createElement("option");
			op.value = -1;
			op.text = "----- 請選擇 -----";
			document.getElementById("recvProvince").appendChild(op);
			
			for(var i = 0 ; i < jsonObj.data.length ; i++) {
				var op = document.createElement("option");
				op.value = jsonObj.data[i].provinceCode;
				op.text = jsonObj.data[i].provinceName;
				document.getElementById("recvProvince").appendChild(op);
			}
			// 選中默認的選項(option)
			$("#recvProvince").val(provinceCode);
			// 加載城市列表
			getCities(cityCode, areaCode);
		}
	});
}

//===== 加載市列表 =====
function getCities(cityCode, areaCode) {
	$("#recvCity").empty();
	$("#recvArea").empty();
	var url = "../dict/cities.do";
	var data = "provinceCode=" + $("#recvProvince").val();
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(jsonObj) {
			// 添加默認節點
			var op = document.createElement("option");
			op.value = -1;
			op.text = "----- 請選擇 -----";
			document.getElementById("recvCity").appendChild(op);
			
			for(var i = 0 ; i < jsonObj.data.length ; i++) {
				var op = document.createElement("option");
				op.value = jsonObj.data[i].cityCode;
				op.text = jsonObj.data[i].cityName;
				document.getElementById("recvCity").appendChild(op);
			}
			// 選中默認的選項(option)
			$("#recvCity").val(cityCode);
			getAreas(areaCode);
		}
	});
}

//===== 加載區列表 =====
function getAreas(areaCode) {
	$("#recvArea").empty();
	var url = "../dict/areas.do";
	var data = "cityCode=" + $("#recvCity").val();
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(jsonObj) {
			// 添加默認節點
			var op = document.createElement("option");
			op.value = -1;
			op.text = "----- 請選擇 -----";
			document.getElementById("recvArea").appendChild(op);
			
			for(var i = 0 ; i < jsonObj.data.length ; i++) {
				var op = document.createElement("option");
				op.value = jsonObj.data[i].areaCode;
				op.text = jsonObj.data[i].areaName;
				document.getElementById("recvArea").appendChild(op);
			}
			// 選中默認的選項(option)
			$("#recvArea").val(areaCode);
		}
	});
}

// ===== 提交 =====
function postForm() {
	// 獲取隱藏域中設置的id值
	var id = $("#id").val();
	// 判斷提交按鈕的路徑: 是新增還是修改, 從而決定數據提交到哪個路徑
	var url = id == 0 ? "add.do" : "handle_update.do";
	// 序列化表單中的數據, 也包括隱藏域
	var data = $("#address-form").serialize();
	// 發出請求並處理響應
	$.ajax({
		"url": url,
		"data": data,
		"type": "POST",
		"dataType": "json",
		"success": function(jsonObj) {
			// 操作提示成功
			alert(jsonObj.message);
			// 關閉彈出窗口
			dismissPopup();
			// 刷新列表
			showAddressList();
			
		},
		"error": function() {
			alert("登錄信息已經過期!請重新登錄!");
			location.href = "../user/login.do";
		}
	});
}


//===== 頁面加載完成時應該執行的任務 =====
$(function() {
	// ----- 左邊欄默認顯示"收貨地址"區塊 -----
	$("#leftsidebar_box dd").hide();
	$("#leftsidebar_box .address dd").show();
	$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
	$("#leftsidebar_box .address").find('img').attr("src","../images/myOrder/myOrder1.png");
	// ----- 加載地址列表 -----
	showAddressList();
	
});
</script>

</html>