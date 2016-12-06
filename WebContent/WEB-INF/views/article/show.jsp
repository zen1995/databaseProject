<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<!-- publishUser=5, likeCount=0, timeStr=2016-12-02 11:17, id=1, 
time=1480648667743, title=article title-0, content=content-0 }  -->
<div class="container" ><!---->
	<!-- 显示一篇文章 -->
    <div class="row" id = "frame" ><!---->    
        <div class="col m8 offset-m2 s12"  ><!--id = "standard"-->
            <div class="card hoverable" >
                <div class="card-content ">
                    <div class="center row" >
						<span class="card-title blue-text"><c:out value="${article.title}"></c:out></span>
                        <p class="grey-text">
                        <span class="green-text">time:</span><c:out value="${article.timeStr }"></c:out>
                        &nbsp;&nbsp;
                        <span class="green-text">user:</span><c:out value="${article.name }"></c:out>
                        </p>
                        <div class="divider"></div>
                        <p style="margin-top: 10px"><c:out value="${article.content }"></c:out></p>
                    </div>
            </div>
            <div class="card-action">
                <a href="#" id="like" onClick="likeArticle()"><i class="mdi-action-favorite-outline" id="likeheart"></i>like</a>
            </div>
        </div>
    </div>
</div>
<!-- 添加点赞之后的切换红心状态的JSP -->
<script>

</script>



<%@include file="/WEB-INF/views/frame/footer.jsp"%>