<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
 
<div class="container center col m8 offset-m2 s12 " >
<div class="row">
     <div class="">
    <!--  <c:out value="${spaceUser}"></c:out> -->
       <div class="card light-blue lighten-1 ">
         <div class="card-content white-text">
           <span class="card-title">用户：<c:out value="${spaceUser.name }"></c:out></span>
           <p>性别:<c:out value="${spaceUser.sex }"></c:out>
                                    年龄:<c:out value="${spaceUser.age }"></c:out></p>
           <p>个签:<c:out value="${spaceUser.description }"></c:out></p>
           <p>喜欢的用户：</p>
           <c:forEach items = "${spaceUser.fin }" var="user">
           <c:out value="${user.name }"></c:out>
           </c:forEach>
           <p>被以下用户喜欢：</p>
           <c:forEach items = "${spaceUser.fout }" var="user">
           <c:out value="${user.name }"></c:out>
           </c:forEach>
         </div>
         <div class="card-action">
           <a onClick="changePage()">修改个人信息</a>
           <a href="#" id="like" onClick="followUser(<c:out value = "${spaceUser.id}"></c:out>)"><i class="mdi-action-favorite-outline" id="likeheart">
           </i>like</a>
       </div> 
       </div>
       
     </div>
   </div>
   <span><h2>My Article</h2> </span>
   <span></span>
	<table>
	   	<thead id="tabletitle">
	      <tr>
	        <th data-field="name">标题</th>
	        <th data-field="time">时间</th>
	     <!--     <th data-field="tag">标签</th>-->
	      </tr>
	   	</thead>
		<tbody>
			<c:forEach items = "${articles}" var = "article"> 
	           <tr>
	               <td ><a href='/article/show/<c:out value="${ article.id}"></c:out>'>
	                    <c:out value="${article.title} "></c:out>
	                    </a>
	               </td>
		           <td  class="col s12 m4 offset-m2 grey-text"><c:out value = "${article.timeStr }"></c:out>
		           </td>
		        <!--     <td  class="col s12 m4 grey-text"><c:out value = "${article.tag }"></c:out>
		           </td> -->
		           <td> <Button class="transparent blue-text"  style="border:none" 
		           onClick="articleEdit(<c:out value="${article.id }"></c:out>)">编辑   </Button>
		           </td>
		           <td> <Button class="transparent blue-text"  style="border:none"
		           onClick="articleDelete(<c:out value="${article.id }"></c:out>)">删除   </Button>
		           </td>
	           </tr>
	        </c:forEach>
		</tbody>	
	</table>
	</div>
	<script>
	
	</script>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>