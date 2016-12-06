<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/frame/header.jsp"%>



<div class="row center">
    <form class="col m6 offset-m3 s12">
      <div class="row">
        <div class="input-field col s6">
          <input id="title" type="text" name="title" length="10">
          <label for="title">标题</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="content" name="content" class="materialize-textarea" length="1200"></textarea>
          <label for="content">内容</label>
        </div>
      </div>
      <div class="row center">
      <a class="waves-effect waves-light btn light-blue 
      lighten-1" id="submit" onClick="addPage('/article/add')">提交</a></div>
    </form>
</div>

<script>

</script>



<%@include file="/WEB-INF/views/frame/footer.jsp"%>