<%--
  Created by IntelliJ IDEA.
  User: czn
  Date: 11/11/2015
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>

<div class="container" ><!---->
    <div class="row" id = "frame" ><!---->
    <c:forEach items = "${articles}" var = "article"> 
    
        <div class="col m8 offset-m2 s12"  ><!--id = "standard"-->
            <div class="card hoverable" >
                <div class="card-content ">
                    <div class="center row" >

                        <a ng-href="{{'/article/show/'+article.id}}" class="col s12">
                            <h5  ng-bind = "article.title" style="width: 100%">
                            <c:out value="${article.title} "></c:out>
                            </h5>
                        </a>
                        <span ng-bind = "'publishTime: ' +  (article.time | articleDate)" class="col s12 m4 offset-m2 grey-text">
                        	time??
                        </span>
                        <span ng-bind = "'author: ' + article.publisher.userName" class="col s12 m4 grey-text">
                        	user:<c:out value = "${article.publishUser}"></c:out>
                        </span>
                    </div>
                    <div class="divider"></div>

                    <p class="card-content" style="max-height: 300px;overflow: hidden" id = "content"
                       ng-bind-html = "(article.type == 'markdown'? (article.content | replaceEnter) : (article.content) )| noImg | trustHTML">
                    	<c:out value = "${article.content}"></c:out>
                    </p>
                </div>
                <div class="card-action">
                    <a ng-href="{{'/article/show/'+article.id}}" id = "detailLink">view detail</a>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
<script>
    $(document).ready(function(){
        //getAllArticle();
    });
</script>
测试 v2016.8.19.1
<%@include file="/WEB-INF/views/frame/footer.jsp"%>
