<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>商城購物車</title>
    <link rel="stylesheet" href="../css/header.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../css/cart.css"/>
</head>
<body>

<!-- 頂部區域 -->
<c:import url="header.jsp"></c:import>

<div class="modal" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            删除提醒
        </div>
        <div class="modal_information">
            <img src="../images/model/model_img2.png" alt=""/>
            <span>確定删除您的這個寶貝吗？</span>
        </div>
        <div class="yes"><span>删除</span></div>
        <div class="no"><span>不删除</span></div>
    </div>
</div>
<div class="modalNo" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            删除提示
            <img src="../images/model/model_img1.png" alt="" class="rt close"/>
        </div>
        <div class="modal_information">
            <img src="../images/model/model_img2.png" alt=""/>
            <span>請選擇商品</span>
        </div>
    </div>
</div>
<div class="big">
    <section id="section" >
        <div id="title">
            <b>購物車</b>
            <p>
                已選<span class="total color">0</span>件商品<span class="interval"></span>合計(不含運費):<span class="totalPrices color susum">0.00</span><span class="unit color">元</span>
            </p>
        </div>
    <form action="../order/confirm.do" method="GET">
        <div id="box" >
            <div id="content_box">
                <div class="imfor_top">
                    <div class="check_top">
                        <div class="all">
                            <span class="normal">
<!--                                 <img src="../images/cart/product_normal.png" alt=""/> -->
									<input type="checkbox" name="all" value="all"/>
                            </span>  <input type="hidden" name="" value="">全選
                        </div>
                    </div>
                    <div class="pudc_top">商品</div>
                    <div class="pices_top">單價(元)</div>
                    <div class="num_top">數量</div>
                    <div class="totle_top">金额</div>
                    <div class="del_top">操作</div>
                </div>
                
                <c:forEach items="${userCartList }" var="userCartList">
                	<div class="imfor">
	                    <div class="check">
	                        <div class="Each">
	                            <span class="normal">
<!-- 	                                <img src="../images/cart/product_normal.png" alt=""/> -->
	                                <input class="eachCheckBox" type="checkbox" name="cartId" value="${userCartList.id }"/>
	                            </span>
	                            <input type="hidden" name="" value="">
	                        </div>
	                    </div>
	                    <div class="pudc">
	                        <div class="pudc_information" id="pudcId3">
	                            <img width="84px" height="84px" src="..${userCartList.goodsImage }" class="lf"/>
	                            <input type="hidden" name="" value="">
		                        <span class="des lf" style="text-align: center; line-height: 85px;">
		                            ${userCartList.goodsTitle }
		                              <input type="hidden" name="" value="">
		                        </span>
		                        <p class="col lf" style="text-align: center; line-height: 45px;">
		                        	<span>分類：</span>
		                        	<span class="color_des">${userCartList.goodsItemType }  <input type="hidden" name="" value=""></span>
		                        </p>
	                        </div>
	                    </div>
	                    <div class="pices">
	                        <p class="pices_des">達內專享價</p>
	                        <p class="pices_information"><b>￥</b><span>${userCartList.goodsPrice }.00  <input type="hidden" name="" value=""></span></p>
	                    </div>
	                    <div class="num"><span class="reduc">&nbsp;-&nbsp;</span><input type="text" value="${userCartList.goodsCount }" ><span class="add">&nbsp;+&nbsp;</span></div>
	                    <div class="totle">
	                        <span>￥</span>
	                        <span class="totle_information">${userCartList.goodsPrice * userCartList.goodsCount }.00</span>
	                    </div>
	                    <div class="del">
	                        <!-- <div>
	                            <img src="img/true.png" alt=""/>
	                            <span>已移入收藏夹</span>
	                        </div>
	                         <a href="javascript:;" class="del_yr">移入收藏夹</a>
	                        -->
	                        <a onclick="deleteCartItem(${userCartList.id })" class="del_d">删除</a>
	                    </div>
                	</div>
                </c:forEach>
                
            </div>
            <div class="foot">
                <div class="foot_check">
                    <div class="all">
                        <span class="normal">
<!--                                 <img src="../images/cart/product_normal.png" alt=""/> -->
									<input type="checkbox" name="all" value="all"/>
                            </span>  <input type="hidden" name="" value="">全選
                    </div>
                </div>
                <a href="javascript:;" class="foot_del">删除</a>
                <!--<a href="javascript:;" class="foot_yr">移入收藏夹</a>-->
                <div class="foot_qk">清空失效商品</div>
                <div class="foot_cash" id="go-buy">
                	<input type="submit" value="去結算"/>
                </div>
                <div class="foot_tol"><span>合计(不含運費):</span><span  class="foot_pices susumOne">0.00</span><span class='foot_des'>元</span></div>
                <div class="foot_selected">
                    已選<span class="totalOne color">0</span>件商品
                </div>
            </div>
        </div>
    </form>
    </section>
    <div class="none" style="display: none">
        <p class="none_title">購物車</p>
        <div class="none_top"></div>
        <div class="none_content">
            <img src="../images/30.png" alt="" class="lf"/>
            <p class="lf">您的購物車竟然还是空哒( ⊙ o ⊙ )</p>
            <span class="lf">趕快去下單吧！</span>
            <a href="#" class="lf">去購物>></a>
        </div>

    </div>
</div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/cart.js"></script>
<script src="../js/index.js"></script>
<script>
    <!-- 显示空购物车页面-->
    $(function(){
        if(!$(".imfor")) {
            $('#section').hide();
            $('.none').show();
        }
    })
    $("#go-buy").click(function(){
        window.location.href="orderConfirm.html";
    })
</script>

<script type="text/javascript">
function deleteCartItem(id) {
	var checkIfDelete = confirm("您確定刪除寶貝嗎?");
	var url = "delete.do";
	var data = "id=" + id;
	if(checkIfDelete) {
		$.ajax({
			url: url,
			data: data,
			type: "GET",
			dataType: "json",
			success: function(jsonObj) {
				if(jsonObj.state == 1) {
					alert(jsonObj.message);
					location.href = "list.do";
				}
			}
		});
	}
	
}
</script>
</body>
</html>