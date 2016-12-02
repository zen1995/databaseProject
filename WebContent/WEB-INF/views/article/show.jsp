<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<!-- publishUser=5, likeCount=0, timeStr=2016-12-02 11:17, id=1, 
time=1480648667743, title=article title-0, content=content-0 }  -->
<div class="container" ><!---->
	<!-- Dropdown Trigger -->
    <div class="row" id = "frame" ><!---->    
        <div class="col m8 offset-m2 s12"  ><!--id = "standard"-->
            <div class="card hoverable" >
                <div class="card-content ">
                    <div class="center row" >
						<span class="card-title blue-text"><c:out value="${article.title}"></c:out></span>
                        <p class="grey-text">
                        <span class="green-text">time:</span><c:out value="${article.timeStr }"></c:out>
                        &nbsp;&nbsp;
                        <span class="green-text">user:</span><c:out value="${article.publishUser }"></c:out>
                        </p>
                        <div class="divider"></div>
                        <p style="margin-top: 10px"><c:out value="${article.content }"></c:out></p>
                    </div>
            </div>
            <div class="card-action">
                <a ng-href="{{'/article/show/'+article.id}}" id = "detailLink">view detail</a>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/views/frame/footer.jsp"%>