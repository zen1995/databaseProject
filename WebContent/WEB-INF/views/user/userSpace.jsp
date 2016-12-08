<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
 
<div class="container center col m8 offset-m2 s12 " >
<div class="row">
     <div class="">
       <div class="card light-blue lighten-1 ">
         <div class="card-content white-text">
           <span class="card-title">用户：<c:out value="${user.name }"></c:out></span>
           <p>性别:<c:out value="${user.sex }"></c:out>
                                    年龄:<c:out value="${user.age }"></c:out></p>
           <p>个签:<c:out value="${user.note }"></c:out>
         </div>
       </div>
     </div>
   </div>
	<table>
	   	<thead id="tabletitle">
	      <tr>
	        <th data-field="name">标题</th>
	        <th data-field="time">时间</th>
	        <th data-field="tag">标签</th>
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
		           <td  class="col s12 m4 grey-text"><c:out value = "${article.tag }"></c:out>
		           </td>
		           <td> <a href="#">编辑   </a>
		           </td>
		           </td>
		           <td> <a href="#">删除   </a>
		           </td>
	           </tr>
	        </c:forEach>
		</tbody>	
	</table>
	</div>
	<script>
	
	</script>
<%@include file="/WEB-INF/views/frame/footer.jsp"%>