<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 页面顶部-->
<header id="top" class="fixed_nav">
	<!-- Logo區域 -->
    <div id="logo" class="lf">
        <img class="animated jello" src="../images/header/logo.png" alt="logo"/>
    </div>
    
    <!-- 搜尋條 -->
    <div id="top_input" class="lf">
        <input id="input" type="text" placeholder="請輸入您要搜尋的内容"/>
        <a href="search.html" class="rt"><img id="search" src="../images/header/search.png" alt="搜尋"/></a>
    </div>
    
    <!-- 右邊菜單 -->
    <div class="rt">
        <ul class="lf">
        	<c:if test="${uid != null }">
        		<li>
        			<a href="${pageContext.request.contextPath }/user/profile.do">
        				${username }
        			</a>
        			<b>|</b>
        		</li>
        	</c:if>
        	
            <li><a href="#favorites.html" title="我的收藏"><img class="care" src="../images/header/care.png" alt=""/></a><b>|</b></li>
            <li><a href="#orders.html" title="我的訂單"><img class="order" src="../images/header/order.png" alt=""/></a><b>|</b></li>
            <li><a href="${pageContext.request.contextPath }/cart/list.do" title="我的購物車"><img class="shopcar" src="../images/header/shop_car.png" alt=""/></a><b>|</b></li>
            <li><a href="#help.html">幫助</a><b>|</b></li>
            <c:if test="${uid != null }">
            	<li><a href="${pageContext.request.contextPath }/user/logout.do">退出</a></li>
            </c:if>
            <c:if test="${uid == null }">
            	<li><a href="${pageContext.request.contextPath }/user/login.do">登錄</a></li>
            </c:if>
        </ul>
    </div>
</header>

<!-- nav主导航-->
<nav id="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath }/main/index.do" class="acti">首頁</a></li>
        <li><a href="index.html#computer" >電腦辦公</a></li>
        <li><a href="index.html#stationery" >辦公文具</a></li>
    </ul>
</nav>