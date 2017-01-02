<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<style type="text/css">
	.tag {background: transparent;
			border: none;}
	.tag:hover{background: yellow lighten-4;}
	span.badge.new:after{
	content : "";
	/*font-weight: 300; */
    
	}
</style>
<div class="container" ><!---->
	<!-- 显示一篇文章 -->
    <div class="row" id = "frame" ><!---->    
        <div class="col m8 offset-m2 s12"  ><!--id = "standard"-->
            <div class="card hoverable" >
                <div class="card-content ">
                    <div class="center row" >
						<span class="card-title blue-text"><c:out value="${article.title}"></c:out></span>
						<p>
						
						
                        </p>
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
            
                <a href="#" id="like" <c:if test="${user.status == true}"> onClick="likeArticle(<c:out value = "${article.id}"></c:out>)"</c:if>><i class="mdi-action-favorite-outline" id="likeheart">
            
                </i>like <span id="likeNum"><c:out value="${article.likeCount}"></c:out></span> </a>
            
            <div class="row">
            	<c:forEach items = "${tag }" var = "tag">
            	 <a onClick="deletTag(<c:out value="${article.id}"></c:out>,<c:out value="${tag.tagId}"></c:out>)"> 
            <!--  <span class="new badge" style="position:relative; margin-right:10px; left:10px;font-weight: 400; font-size: 1.2rem;">
            	 <c:out value="${tag.tagName }"></c:out></span> -->
            	 <span class="new badge" style="position:relative; margin-right:10px; left:10px;font-weight: 400; font-size: 1.2rem;"> <c:out value="${tag.tagName }"></c:out></span>
            	 </a>
			    </c:forEach> 
			    <div>
			   	  <div class="col s1">
		          <a onClick="addTagInput()"><i class="small mdi-content-add-circle-outline" ></i></a>
		          </div>
		          <div class="col s6">
		          <input id="inputTagName" style="display:none" type="text" class="validate">
		          <label id="labTagName" style="display:none" for="icon_prefix">Tag Name</label>
		          </div>
		          <a id="TagSubmit" style="display:none" onClick="addTag(<c:out value="${article.id}"></c:out>)"><p>ADD</p></a>
           		</div>
			</div>
				
           </div>
           
        </div>
    </div>
</div>
<!-- 添加点赞之后的切换红心状态的JSP -->
<script>

</script>



<%@include file="/WEB-INF/views/frame/footer.jsp"%>