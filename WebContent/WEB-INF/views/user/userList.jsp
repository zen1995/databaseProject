
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>

<div class="row" id = "frame" ><!---->
    <c:forEach items = "${users}" var = "user"> 
        <div class="col m8 offset-m2 s12"  ><!--id = "standard"-->
            <div class="card hoverable" >
                <div class="card-content ">
                    <div class="center row" >
                    <a href='/article/show/<c:out value="${ user.name}"></c:out>' class="col s12">
                            <h5   style="width: 100%">
                            <c:out value="${ user.name} "></c:out>
                            </h5>
                        </a>
                        <span ng-bind = "'publishTime: ' +  (article.time | articleDate)" class="col s12 m4 offset-m2 grey-text">
                        	sex:<c:out value = "${user.sex }"></c:out>
                        </span>
                        <span ng-bind = "'author: ' + article.publisher.userName" class="col s12 m4 grey-text">
                        	age:<c:out value = "${user.age}"></c:out>
                        </span>
                    </div>
                    <div class="divider"></div>

                    <p class="card-content" style="max-height: 300px;overflow: hidden" id = "content"
                       ng-bind-html = "(article.type == 'markdown'? (article.content | replaceEnter) : (article.content) )| noImg | trustHTML">
                    	<c:out value = "${article.content}"></c:out>
                    </p>
                </div>
            </div>
        </div>
   </c:forEach>       
</div>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>