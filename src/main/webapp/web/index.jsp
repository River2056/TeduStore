<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head lang="cn">
    <meta charset="UTF-8">
    <title>學子商城首頁</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/index.css" rel="stylesheet"/>
    <link href="../css/slide.css" rel="stylesheet"/>
    </head>
<body>

<!-- 頂部區域 -->
<c:import url="header.jsp"></c:import>

<!-- banner部分-->
<div class="ck-slide">
    <ul class="ck-slide-wrapper">
        <li>
            <a href="product_details.html"><img src="../images/itemCat/itemCat_banner1.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="product_details.html"><img src="../images/itemCat/itemCat_banner2.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="product_details.html"><img src="../images/itemCat/itemCat_banner3.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="product_details.html"><img src="../images/itemCat/itemCat_banner4.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="product_details.html"><img src="../images/itemCat/itemCat_banner1.png" alt=""></a>
        </li>
    </ul>
    <a href="javascript:;" class="ctrl-slide ck-prev">上一張</a> <a href="javascript:;" class="ctrl-slide ck-next">下一張</a>
    <div class="ck-slidebox">
        <div class="slideWrap">
            <ul class="dot-wrap">
                <li class="current"><em>1</em></li>
                <li><em>2</em></li>
                <li><em>3</em></li>
                <li><em>4</em></li>
                <li><em>5</em></li>
            </ul>
        </div>
    </div>
</div>

<!--/*楼梯1f*/-->
<h2 id="computer" class="stair"><span><img src="../images/itemCat/computer_icon.png" alt=".stair"/></span>辦公電腦 /1F</h2>

<div class="lf1">
    <div class="lf1_top">
        <!-- 上面部分左侧区域-->
        <div class="left lf">
            <div class="left_pro lf">
                <p class="top_ys1">靈越 燃7000系列</p>

                <p class="top_ys2">
                    酷睿雙核i5處理器|256GB SSD| 8GB内存
                    </br>
                    英特爾HD顯卡620含共享顯卡内存
                </p>

                <p class="top_ys3">￥4999.00</p>

                <p class="top_ys4 color_2"><a href="product_details.html">查看詳情</a></p>
            </div>
            <span><img src="../images/itemCat/study_computer_img1.png" alt=""/></span>
        </div>
        <!-- 上面部分右侧区域-->
        <div class="right lf">
            <div class="right_pro lf">
                <p class="top_ys1">颜值 框不住</p>

                <p class="top_ys2">
                    酷睿雙核i5處理器|256GB SSD| 8GB内存
                    </br>
                    英特爾HD顯卡620含共享顯卡内存
                </p>

                <p class="top_ys3">￥6888.00</p>

                <p class="top_ys4 color_2"><a href="product_details.html">查看詳情</a></p>
            </div>
            <span><img src="../images/itemCat/study_computer_img2.png" alt=""/></span>
        </div>
    </div>
    <div class="lf1_bottom">
        <div class="item_cat lf">
            <div class="cat_header color_2">
                <span>
                    <img src="../images/itemCat/computer_icon1.png" alt=""/>
                    	電腦,辦公/1F
                </span>
            </div>
            <div class="item_cat_all">
            	<!-- 顯示電腦的分類訊息 -->
         		<p>${categories161[0].name }</p>
         		<ul>
         			<c:forEach items="${ computerCategories[0] }" var="category">
         				<li><a href="#${category.id }">${category.name }</a></li>
         			</c:forEach>
         		</ul>
         		
         		
         		<p>${categories161[1].name }</p>
         		<ul>
         			<c:forEach items="${ computerCategories[1] }" var="category">
         				<li><a href="#${category.id }">${category.name }</a></li>
         			</c:forEach>
         		</ul>
         		
         		<p>${categories161[2].name }</p>
         		<ul>
	          		<c:forEach items="${ computerCategories[2] }" var="category">
	          			<li><a href="#${category.id }">${category.name }</a></li>
	          		</c:forEach>
	          	</ul>
            	
            </div>
        </div>
        <c:forEach items="${computers }" var="computer">
        	<div class="item_msg lf">
	            <img width="190px" height="130px" src="${pageContext.request.contextPath }${computer.image }" alt=""/>
	
	            <p class="bottom_ys2">${computer.title }</p>
	
	            <p class="bottom_ys3">￥${computer.price }.00</p>
	
	            <p class="bottom_ys4 color_2"><a href="#${computer.id }">查看詳情</a></p>
        	</div>
        </c:forEach>
    </div>
</div>
<!--楼梯2f-->
<h2 id="stationery" class="stair"><span><img src="../images/itemCat/stationery_icon.png" alt=".stair"/></span>辦公文具 /2F</h2>

<div class="lf1">
    <div class="lf1_top">
        <!-- 上面部分左侧区域-->
        <div class="left lf">
            <div class="left_ys1 lf"><img src="../images/itemCat/study_stationery_img1.png" alt=""/></div>
            <div class="left_pro lf">
                <p class="top_ys1">雅致布面年曆本</p>

                <p class="top_ys2">
                    有色更有范！變色PU皮，撞色搭配，絢麗色彩，張揚個性，點亮生活新時尚！
                </p>

                <p class="top_ys3 price_ys3">僅售 ￥49.00</p>

                <p class="top_ys4 color_1"><a href="product_details.html">查看詳情</a></p>
            </div>
        </div>
        <!-- 上面部分右侧区域-->
        <div class="right lf">
            <div class="left_ys2 lf"><img src="../images/itemCat/study_stationery_img2.png" alt=""/></div>
            <div class="right_ys rt">
                <p class="top_ys1">透視網格拉鏈袋</p>
                <p class="top_ys2">
                    韓國創意卡通 叢林物語網格文件袋
                </p>
                <p class="top_ys3 price_ys3">僅售 ￥28.00</p>

                <p class="top_ys4 color_1"><a href="product_details.html">查看詳情</a></p>
            </div>
        </div>
    </div>
    <div class="lf1_bottom">
        <div class="item_cat lf">
            <div class="cat_header color_1">
                <span>
                    <img src="../images/itemCat/stationery_icon1.png" alt=""/>
                    辦公文具/2F
                </span>
            </div>
            <div class="item_cat_all item_color">
                <p>辦公設備</p>
                <ul>
                    <li><a href="#">投影機</a></li>
                    <li><a href="#">打印機</a></li>
                    <li><a href="#">點鈔機</a></li>
                    <li><a href="#">碎紙機</a></li>
                    <li><a href="#">考勤機</a></li>
                    <li><a href="#">保險柜</a></li>
                </ul>
                <p>文具耗材</p>
                <ul>
                    <li><a href="#">學生文具</a></li>
                    <li><a href="#">辦公文具</a></li>
                    <li><a href="#">紙類</a></li>
                    <li><a href="#">打印耗材</a></li>
                    <li><a href="#">服務器</a></li>
                    <li><a href="#">联想</a></li>
                </ul>
                <p>電腦整機</p>
                <ul>
                    <li><a href="#">筆記本</a></li>
                    <li><a href="#">遊戲機</a></li>
                    <li><a href="#">台式機</a></li>
                    <li><a href="#">一體機</a></li>
                    <li><a href="#">服務器</a></li>
                    <li><a href="#">聯想</a></li>
                </ul>
            </div>
        </div>
        <div class="item_msg lf">
            <img src="../images/itemCat/study_stationery_img3.png" alt=""/>

            <p class="bottom_ys2">得力（deli）1548A商務辦公桌面計算器 太陽能雙電源</p>

            <p class="bottom_ys3 price_ys3">￥58.00</p>

            <p class="bottom_ys4 color_1"><a href="product_details.html">查看詳情</a></p>
        </div>
        <div class="item_msg lf">
            <img src="../images/itemCat/study_stationery_img4.png" alt=""/>

            <p class="bottom_ys2">施耐德（Schneider） K15 經典款圓硃筆 </p>

            <p class="bottom_ys3 price_ys3">￥12.00</p>

            <p class="bottom_ys4 color_1"><a href="product_details.html">查看詳情</a></p>
        </div>

        <div class="item_msg lf">
            <a href="product_details.html">
            <img src="../images/itemCat/study_stationery_img5.png" alt=""/>
            <p class="bottom_ys2">齊心皮面日程本子2017.1-2018.6計畫記事本效率手册</p>
            <p class="bottom_ys3 price_ys3">￥23.00</p>
            <p class="bottom_ys4 color_1"><a href="product_details.html" id="iii">查看詳情</a></p>
            </a>
        </div>

    </div>
</div>

<!-- 頁面底部 -->
<c:import url="footer.jsp"></c:import>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script src="../js/slide.js"></script>
<script>
    $('.ck-slide').ckSlide({
        autoPlay: true,//默认为不自动播放，需要时请以此设置
        dir: 'x',//默认效果淡隐淡出，x为水平移动，y 为垂直滚动
        interval:3000//默认间隔2000毫秒
    });
</script>
<script>
    $("#iii").click(function(){
        location.href="product_details.html";
    })
</script>
</body>
</html>