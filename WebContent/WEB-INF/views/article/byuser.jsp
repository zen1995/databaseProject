<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>
<ul class="collapsible" data-collapsible="accordion" style = "margin-left: 20%; margin-right: 40%">
     <li>
       <div class="collapsible-header"><i class="mdi-social-mood"></i>By User Name</div>
       	<div class="collapsible-body">
       		<div class="row" style="margin-left:20%">
       			<form action="/article/search">
			        <div class="input-field col s6">
			        	<input name="type" value="name" style="display:none">
						<input id="userName" name="value" type="text" class="validate">
						<label for="userName">User Name</label>
			        </div>
			        <div class="input-field col s6">
			            <button class="waves-effect waves-light blue btn" type="submit"><i class="mdi-file-cloud left"></i>Search</button>
			        </div>
			    </form>
	      	</div>
		</div>
     </li>
     <li>
       <div class="collapsible-header"><i class="mdi-social-people-outline"></i>By Article Tag</div>
       	<div class="collapsible-body">
       		<div class="row" style="margin-left:20%">
       		<form action="/article/search">
		        <div class="input-field col s6">
		        <input name="type" value="tag" style="display:none">
		        <input name="value" id="ArtTag" type="text" class="validate">
		        <label for="ArtTag">Article Tag</label>
		        </div>
		        <div class="input-field col s6">
		          <button class="waves-effect waves-light blue btn" type="submit"><i class="mdi-file-cloud left" ></i>Search</button>
		        </div>
			</form>
	      	</div>
		</div>
     </li>
   </ul>
<script >
    
    $(document).ready(function(){
        $('.collapsible').collapsible({
          accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
        });
      });

</script>

<%@include file="/WEB-INF/views/frame/footer.jsp"%>