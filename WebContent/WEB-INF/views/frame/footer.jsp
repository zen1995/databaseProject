<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</main>
<footer class="page-footer blue darken-1"
        style="background-image: url('/resource/static/img/headerBackground.png');background-position: bottom center"><!--
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>-->
    <div class="footer-copyright">
        <div class="container">
            © 2015 Copyright 空想科学
            &nbsp;&nbsp;&nbsp;访问总数：<% out.print(request.getAttribute("viewCount"));%>
            <a class="grey-text text-lighten-4 right" href="/">utopialab.cn</a>
        </div>
    </div>
</footer>

</body>
</html>
