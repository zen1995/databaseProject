
//---------------user-------------------
function checkUserInfo(){
    var userName = $("#userName").val();
    var password = $("#password").val();
    var myurl = "/user/checkRegister";
    var data = {userName:userName,password:password};
    $.post(myurl,data,
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            console.log(myjson);
            alert(data);
        });
}

function insertUser(){
    var userName = $("#userName").val();
    var password = $("#password").val();
    var datepicker = $('#datepicker').data().pickadate;
    var birthdayObj = datepicker.component.item.select;
    var birthday = birthdayObj.obj.getTimestamp();
    var email = $("#email").val();
    var myurl = "/user/addUser";
    var data = {
        userName:userName,
        password:password,
        birthday:birthday,
        email:email,
    };
    $.post(myurl,data,
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            console.log(myjson);
            alert(data);
        });
}

function login(){
    var userName = $("#userName").val();
    var password = $("#password").val();
    var myurl = "/user/login";
    var data = {userName:userName,password:password};
    $.post(myurl,data,
        function(data){//alert(data);
            console.log(data);
            data=eval('('+data+')');
            if(data.status == true){
                alert("登陆成功！");
                if(window.location.pathname = "/user/login"){
                    window.location.href = "/user";
                }
                else{
                    window.location.reload();
                }
            }
            else{
                alert("登录失败！ "+data.message);
            }
        });
}

function signOut(){
    var myurl = "/user/signOut";
    $.post(myurl,{},
        function(data){//alert(data);
            console.log(data);
            var data=eval('('+data+')');
            if(data.status == true){
                alert("注销成功！");
                window.location.reload();
            }
            else{
                alert("注销失败！ "+data.message);
            }
        });
}

function isLogin(){
    var myurl = "/user/getUserInfo";
    $.post(myurl,{},
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            console.log(myjson);
            //alert(data);
            updateHeader(myjson);
            globalInfo.user = myjson;
        });
}

function getUserInfo(){
    var myurl = "/user/getUserInfo";
    $.post(myurl,{},
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            appendUserInfo(myjson);
            //globalInfo.user = myjson;
        });
}

function appendUserInfo(user){
    $('#userName').html(user.userName);
    if(window.location.pathname == "/user" || window.location.pathname == "/user/"){
        if(user.description == null){
            user.description = "";
        }
        if(user.birthday == 0){
            user.birthday = "";
        }
        if(user.email == null){
            user.email = "";
        }
        $($('#userDescrptionDiv').children()[1]).text(user.description);

        var date = Date.createByTimestamp(user.birthday);
        var pickadate = $('.datepicker').data().pickadate;
        pickadate.set('select',user.birthday*1000);
        $($('.userInfo')[1]).text($($('.userInfoEdit')[1]).children().val());
        //$($('#userBirthDayDiv').children()[1]).text(user.birthday);
        $($('#userEmailDiv').children()[1]).text(user.email);
    }
}

function switchEditInfo(){
    if(editTrigger == false){
        $('.userInfo').css("display","none");
        $('.userInfoEdit').css("display","inline");

        $($('.userInfoEdit')[0]).children().val($($('.userInfo')[0]).text());
        $($('.userInfoEdit')[1]).children().val($($('.userInfo')[1]).text());
        $($('.userInfoEdit')[2]).children().val($($('.userInfo')[2]).text());

        $("#switchEditInfo").text("finish editing");

        editTrigger = true;
    }
    else{
        $('.userInfo').css("display","inline");
        $('.userInfoEdit').css("display","none");

        $($('.userInfo')[0]).text($($('.userInfoEdit')[0]).children().val());
        $($('.userInfo')[1]).text($($('.userInfoEdit')[1]).children().val());
        $($('.userInfo')[2]).text($($('.userInfoEdit')[2]).children().val());
        editTrigger = false;

        $("#switchEditInfo").text("edit personal info");


        var datepicker =  $('.datepicker').data().pickadate;
        var birthdayObj = datepicker.component.item.select;
        var birthday = birthdayObj.obj.getTimestamp();
        var data = {
            description : $($('.userInfoEdit')[0]).children().val(),
            birthday : birthday,
            email : $($('.userInfoEdit')[2]).children().val(),
        };
        var myurl = "/user/editUserInfo";
        console.log(data);
        $.post(myurl,data,
            function(data){
                console.log(data);
                //var user=eval('('+data+')');
                //addArticlesWithUser(myjson,user);
                //addArticles(myjson);
            });
    }

}

//--------------article---------------------
function getAllArticle(){
    var myurl = "/article/getAllArticle";
    $.post(myurl,{},
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            console.log(myjson);
            alert(data);
            addArticles(myjson);
        });
}

function getUserArticle(){
    var myurl = "/article/getAllArticleByUser";
    $.post(myurl,{},
        function(data){
            var myjson=eval('('+data+')');
            console.log(myjson);
            alert(data);
            var myurl = "/user/getUserInfo";

            $.post(myurl,{},
                function(data){//alert(data);
                    console.log(data);
                    var user=eval('('+data+')');
                    console.log(data);
                    addArticlesWithUser(myjson,user);
                    //addArticles(myjson);
                });
        });
}

function getPageArticle(page){
    var myurl = window.location.href;
    console.log(myurl);
    $.post(myurl,{page:page},
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            console.log(myjson);
            alert(data);
            addArticles(myjson);
        });
}

function addArticlesWithUser(articles,user){
    for(var i=0;i<articles.length;i++){
        var article = articles[i];
        var standard = $("#standard");
        $("#title").html(article.title).addClass("add");
        $("#content").html(article.content.replaceImgTag()).addClass("add");
        $('#editLink').attr("href","/article/modify/"+user.id+"/"+article.id);
        var s = standard.clone(true);
        s.addClass("add");
        s.css("display","block");
        $(".add").removeClass("add");
        $("#frame").append(s);
        $(".add").removeAttr("id").removeClass("add");
    }
    $("#standard").remove();
}



function addArticles(articles){
    for(var i=0;i<articles.length;i++){
        var article = articles[i];
        var standard = $("#standard");
        $("#title").html(article.title).addClass("add");
        $("#content").html(article.content).addClass("add");
        //$("#detailLink").html("/article/view/"+article.publicId).addClass("add");
        var s = standard.clone(true);
        s.addClass("add");
        s.css("display","block");
        $(".add").removeClass("add");
        $("#frame").append(s);
        $(".add").removeAttr("id").removeClass("add");
    }
    $("#standard").remove();
}

function getSingleArticle(){
    var url = window.location.pathname;
    $.post(url,{},
        function(data){
            console.log(data);
            data = "("+data+")";
            data = eval(data);
            if(data.status == true){
                $("#title").html(data.obj.title);
                $("#content").html(data.obj.content);
            }
            else{
                alert(data.message);
            }
        }
    )
}

function updateHeader(data){
    if(data.loginStatus == false){
        $("#loginStatus").text("游客");
        $('#loginStatus').attr("href","#");
        $('#registerBtn').css('display','block');
        $('#signInBtn').css("display","block");
    }
    else{
        $("#loginStatus").text("userName: "+data.userName);
        $('#signOutBtn').css("display","block");

    }

}

function addArticle(){
    var myurl = "/article/addArticle";
    //var myurl = "/addimgs";
    //var myurl = "/addRichText";
    var title = $("#title").val();
    //var content = $("#content").html();
    //var content = $('#summernote').code();
    //var data = {title:title,content:content};
    var data = new Object();
    data.content = $('#summernoteFrame')[0].contentWindow.getSummernoteContent();
    data.title = title;
    console.log(data);
    $.post(myurl,data,
        function(data){//alert(data);
            console.log(data);
            data=eval('('+data+')');;
            if(data.status == true){
                alert("添加成功！");
                window.location.href = "/user";
            }
            else{
                alert("添加失败！ "+data.message);
            }
        });
}

function getSummernoteContent(){
    //var title = $("#title").val();
    //var content = $("#content").html();
    var content = $('#summernote').code();
    var data = {content:content};
    return content;
}

function setSummernoteContent(data){
    $('#summernote').code(data.content);
}

function modifyArticle(){//articleId,publisherId
    var articleId = info.articleId;
    var publisherId = info.publisherId;
    var myurl = "/article/modify";
    var title = $("#title").val();
    var content   = $('#summernoteFrame')[0].contentWindow.getSummernoteContent();
    var data = {title:title,content:content,
        articleId:articleId,publisherId:publisherId};
    console.log(data);
    $.post(myurl,data,
        function(data){//alert(data);
            console.log(data);
            var myjson=eval('('+data+')');
            console.log(myjson);
            alert(data);
        });
}

function addPhoto(){
   var fileForm = $("#file");
    fileForm.ajaxForm({
        enctype:"multipart/form-data",
        success: function (data) {
            console.log(data);
        }
    }).submit();
}

function sendFile(file, editor, $editable) {
    var data = new FormData();
    var location = window.parent.location;
    var articleId;
    var publisherId = -1;
    if(location.isSubpath("/article/addArticle")){
        articleId = -1;
        publisherId = info.id
    }
    else{
        articleId = location.getPathSegment(3);
        publisherId = info.id;
    }
    data.append("file", file);
    data.append("publisherId",publisherId);
    data.append("articleId",articleId);
    $.ajax({
        data: data,
        type: "POST",
        processData: false,
        contentType: false,
        cache: false,
        //enctype:"multipart/form-data",
        url: "/article/addImage",
        success: function(data) {
            console.log(data);
            var jsonn = eval('('+data+')');
            //$('#summernoteFrame')[0].contentWindow.
            $('#summernote').summernote('editor.insertImage',jsonn.path );

        },
        error:function(data){console.log(data);}
    });
}


function initSummernote(){
    var height = 550;
    $('#summernote').summernote({
        height: height,
        minHeight: height,             // set minimum height of editor
        maxHeight: height,
        tabsize: 2,
        codemirror: {
            theme: 'monokai'
        },
        onImageUpload: function(files, editor, welEditable) {
            sendFile(files[0],editor,welEditable);
        }
    });
}

function uploadProfileImg(){
    var data = new FormData();
    file = $("#file")[0].files[0];
    console.log(file);
    data.append("file", file);
    $.ajax({
        data: data,
        type: "POST",
        processData: false,
        contentType: false,
        cache: false,
        //enctype:"multipart/form-data",
        url: "/user/uploadUserImg",
        success: function(data) {
            console.log(data);
            var json = eval('('+data+')');
            //console.log( $("#profileImg").width())
            $("#profileImg").attr("src",json.path);
            $("#cutProfileImg").attr("src",json.path);
            $("#displayProfileImg").attr("src",json.path);
            initJcrop(json.path);
            //$('#summernote').summernote('editor.insertImage',jsonn.relativePath );
        },
        error:function(data){console.log(data);}
    });
}

function initEditProfilePage(){
    var myurl = "/user/getUserInfo";


    $.post(myurl,{},
        function(data){//alert(data);
            var user=eval('('+data+')');
            console.log(user.profileImgUrl);
            //$("#profileImg").attr("src",user.profileImgUrl);
            $(".displayProfileImg").attr("src",user.profileImgUrl);
        });
}