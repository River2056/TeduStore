<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>確認訂單</title>
    <link href="../css/orderConfirm.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="Stylesheet"/>
    <link href="../css/order_Confirm.css" rel="Stylesheet"/>
    </head>
<body>

<!-- 頂部區域 -->
<c:import url="header.jsp"></c:import>

<div id="navlist">
    <ul>
        <li class="navlist_blue_left"></li>
        <li class="navlist_blue">確認訂單信息</li>
        <li class="navlist_blue_arro"><canvas id="canvas_blue" width="20" height="38"></canvas>
        </li>
        <li class="navlist_gray">支付訂單<b></b></li>
        <li class="navlist_gray_arro"><canvas id="canvas_gray" width="20" height="38"></canvas>
        </li>
        <li class="navlist_gray">支付完成<b></b></li>
        <li class="navlist_gray_right"></li>
    </ul>
</div>
<!--订单确认-->
<div id="container" class="clear">
    <!--收货地址-->
    <div class="adress_choice">
        <p>收貨人信息<span class="rt" id="choose">新增收貨地址</span></p>
        
        	<div class="addrList addressList">
		        <c:forEach items="${addressList }" var="address" varStatus="status">
					<div class="addr">
						<div class="left">${address.recvName } ${address.recvProvince }</div>
						<div class="center">${address.recvDistrict } ${address.recvAddr }</div>
						<div class="right">設為默認地址</div>
					</div>
		        </c:forEach>
			</div>
        
        <a id="more" href="javascript:void(0);">
            更多地址 &gt;&gt;
        </a>
    </div>
    
    <!-- 商品信息-->
    <form name="" method="post" action="#">
       	<p>確認商品信息</p>
        <ul class="item_title">
            <li class="p_info">商品信息</li>
            <li class="p_price">單價(元)</li>
            <li class="p_count">數量</li>
            <li class="p_tPrice">金額</li>
        </ul>
    	<c:forEach items="${cartList }" var="item">
    		<div class="product_confirm">
	            <ul class="item_detail">
	                <li class="p_info">
	                    <b><img width="84px" height="84px" src="..${item.goodsImage }"/></b>
	
	                    <b class="product_name lf">
	                        ${item.goodsTitle }
	                    </b>
	                    <br/>
	                <span class="product_color ">
	                   ${item.goodsItemType }
	                </span>
	                </li>
	                <li class="p_price">
	                    <i>達內專屬價</i>
	                    <br/>
	                    <span class="pro_price">￥<span class="ppp_price">${item.goodsPrice }.00</span></span>
	                </li>
	                <li class="p_count">X<span>${item.goodsCount }</span></li>
	                <li class="p_tPrice">￥<span>${item.goodsCount * item.goodsPrice }</span></li>
	            </ul>
        	</div>
    	</c:forEach>
    
        
    </form>
    
    <!-- 结算条-->
    <div id="count_bar">
        <span class="go_cart"><a href="#" >&lt;&lt;返回购物车</a></span>
        <span class="count_bar_info">已选<b  id="count"> 0 </b>件商品&nbsp;&nbsp;合计(不含运费):<b class="zj"></b> <input type="hidden" name="Payment" value=""/>元</span>
        <span class="go_pay">确认并付款</span>
    </div>
</div>
<!--标题栏-->
<div class="modal" style="display:none">

        <!--收货人信息填写栏-->     
        <div class="rs_content rs_content_1">
        	<p class="cha">×</p>
        	<form method="post" action="">
	            <!--收货人姓名-->
	            <div class="recipients">
	                <span class="red">*</span><span class="kuan">收货人：</span><input type="text" name="receiverName" id="receiverName"/>
	            </div>
	            <!--收货人所在城市等信息-->
	            <div data-toggle="distpicker" class="address_content">
					 <span class="red">*</span><span class="kuan">省&nbsp;&nbsp;份：</span><select data-province="---- 选择省 ----" id="receiverState"></select>
					  城市：<select data-city="---- 选择市 ----" id="receiverCity"></select>
					  区/县：<select data-district="---- 选择区 ----" id="receiverDistrict"></select>
				</div> 
	            <!--收货人详细地址-->
	            <div class="address_particular">
	                <span class="red">*</span><span class="kuan">详细地址：</span><textarea name="receiverAddress" id="receiverAddress" cols="60" rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
	            </div>
	            <!--收货人地址-->
	            <div class="address_tel">
	                <span class="red">*</span><span class="kuan">手机号码：</span><input type="tel" id="receiverMobile" name="receiverMobile"/>固定电话：<input type="text" name="receiverPhone" id="receiverPhone"/>
	            </div>
	            <!--邮政编码-->
	            <div class="address_postcode">
	                <span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input type="text" name="receiverZip"/>
	            </div>
	            <!--地址名称-->
	            <div class="address_name">
	                <span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input type="text" id="addressName" name="addressName"/>如：<span class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
	            </div>
	            <!--保存收货人信息-->
	            <div class="save_recipient">
	                保存收货人信息
	            </div>
	
    		</form>
    		</div>
</div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script>
    var html=0;
    var total=0;
    $(function(){
        $(".item_detail").each(function() {
            html+=1;
            var p=parseFloat($(this).children('.p_price').children('.pro_price').children('.ppp_price').html());
            console.log(p);
            var sl=parseFloat($(this).children('.p_count').children('span').html());
            var xj=parseFloat(p*sl).toFixed(2);
            console.log("xiaoji"+xj);
            $(this).children('.p_tPrice').children('span').html(xj);
            total+=xj-0;
        })
        console.log("zongji"+total);
        $("#count").html(html)-0;
        $('.zj').html(total.toFixed(2))-0;
        var input_zj=parseFloat($('.zj').html()).toFixed(2);
        $('#payment').val(input_zj);
    })
</script>
<script>
    $(".go_pay").click(function () {
        location.href = "payment.html";
    })
</script>
<script>
    var canvas=document.getElementById("canvas_gray");
    var cxt=canvas.getContext("2d");
    var gray = cxt.createLinearGradient (10, 0, 10, 38);
    gray.addColorStop(0, '#f5f4f5');
    gray.addColorStop(1, '#e6e6e5');
    cxt.beginPath();
    cxt.fillStyle = gray;
    cxt.moveTo(20,19);
    cxt.lineTo(0,38);
    cxt.lineTo(0,0);
    cxt.fill();
    cxt.closePath();
</script>
<script>
    var canvas=document.getElementById("canvas_blue");
    var cxt=canvas.getContext("2d");
    var blue = cxt.createLinearGradient (10, 0, 10, 38);
    blue.addColorStop(0, '#27b0f6');
    blue.addColorStop(1, '#0aa1ed');
    cxt.beginPath();
    cxt.fillStyle = blue;
    cxt.moveTo(20,19);
    cxt.lineTo(0,38);
    cxt.lineTo(0,0);
    cxt.fill();
    cxt.closePath();
</script>
<script src="../js/distpicker.data.js"></script>
<script src="../js/distpicker.js"></script>
<script>
	$("#choose").click(function(){
		$(".modal").show();
	})
	$(".cha").click(function(){
		$(".modal").hide();
	})
	
// 	$("#more").click(function(){
// 		if($(this).hasClass("hide")) {
//  			$(".base").show();
//  			$("#addresId3").hide();
// 			$("#more").html("更多地址>>");
// 			$("#more").removeClass("hide");
// 		} else {
//  			$(".base").hide();
//  			$("#addresId3").show();
// 			$("#more").html("收起地址>>");
// 			$("#more").addClass("hide");
// 		}
// 	})
</script>
<script>
	$(document).on("mouseover",".base",function(){
		$(this).find("i:eq(2)").show();
	})
	$(document).on("mouseout",".base",function(){
		$(this).find("i:eq(2)").hide();
	})
	$(".user_site").click(function(){
		$(this).parent().attr("class","base_select");
		$(this).parent().siblings().attr("class","base");
		$(this).hide();
	})
</script>

<script type="text/javascript">
$(function() {
	//頁面加載完畢, 默認將第一項添加active樣式
	$(".addrList .addr").first().addClass("active");
	// 控制地址行的默認樣式  // 非active隱藏
	$(".addr:not(.active)").hide();
	
	// 控制地址行內設置默認鏈接的顯示與隱藏
	$(".addr .right").hide();
	
	// 給地址行添加mouseover/mouseout監聽
	$(".addr:not(.active)").mouseover(function() {
		// 找到當前標籤的子標籤中的class屬性等於right的標籤
		$(this).children(".right").show();
		$(this).children(".right").css("cursor", "pointer");
	});
	$(".addr:not(.active)").mouseout(function() {
		$(this).children(".right").hide();
	});
	
	// 給設置默認地址鏈接添加監聽
	$(".addr .right").click(function() {
		$(this).parent().siblings().removeClass("active");
		$(this).parent().addClass("active");
		// 重新給地址行標籤添加mouseover/mouseout
		$(".addr:not(.active)").mouseover(function() {
			$(this).children(".right").show();
			$(this).css("cursor", "pointer");
		});
		$(".addr:not(.active)").mouseout(function() {
			$(this).children(".right").hide();
		});
		// 移除父標籤的mouseover事件
		$(this).parent().unbind("mouseover");
		$(this).hide();
	});
	
	// 給more的標籤添加監聽
	$("#more").click(function() {
		var text = $(this).text();
		if(text == "更多地址列表>>") {
			$(".addr").show("fast");
			$(this).text("收起地址列表>>");
		} else {
			$(".addr:not(.active)").hide("fast");
			$(this).text("更多地址列表>>");
		}
	});
});
</script>
</body>
</html>

