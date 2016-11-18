var blog = angular.module("blog",[]);

blog.constant("url",{
    getUserInfo:"/user/getUserInfo",
    getAllArticle:"/article/getAllArticle",
    editUserInfo:"/user/editUserInfo",
    cutProfileImg:"/user/cutUserImg",
    getUserArticle:"/article/getAllArticleByUser",
    signOut:"/user/signOut",
    checkRegister:"/user/checkRegister",
    insertUser:"/user/addUser",
    loginPage:"/user/login",
    login:"/user/login",
    userSpace:"/user",
    addArticle:"/article/addArticle",
    modifyArticle:"/article/modify",
});

blog.constant("ajaxConfig",{
    url:"",
    type:"POST",
    data:{},
    error:function(data){
        alert("server error!-"+this.url+"-"+this.data);
    }
});

blog.constant("defaultArticle",{
    title:"loading title",
    content:"loading content",
    type:"article"
});

blog.factory("fn",function(){
    var fn = {
        getSummernoteContent:function(){
            var content = $('#summernoteFrame')[0].contentWindow.$('#summernote').code();
            var data = {content:content};
            return content;
        },
        setSummernoteContent:function(content){
            $('#summernoteFrame')[0].contentWindow.$('#summernote').code(content);
            //$('#summernoteFrame')[0].contentWindow.setContent(content);
        },
        //insertArticleImg:function(url){
        //    var text = $("textarea").val();
        //    var tag = '<img src="+url+"/>';
        //    $("textarea").val(text +tag);
        //},
        //clickOther:function(id){
        //    $("#"+id).click();
        //}
    };
    return fn;
});

blog.value("markdownObj",{
    obj:null,
    getMarkdown:function(){
        if(this.obj == null){
            var md = new window.Remarkable('full', {
                html:         true,        // Enable HTML tags in source
                xhtmlOut:     false,        // Use '/' to close single tags (<br />)
                breaks:       false,        // Convert '\n' in paragraphs into <br>
                langPrefix:   'language-',  // CSS language prefix for fenced blocks
                linkify:      true,         // autoconvert URL-like texts to links
                linkTarget:   '',           // set target to open link in

                // Enable some language-neutral replacements + quotes beautification
                typographer:  true,

                // Double + single quotes replacement pairs, when typographer enabled,
                // and smartquotes on. Set doubles to '«»' for Russian, '„“' for German.
                quotes: '“”‘’',

            });
            this.obj = md;
        }
        return this.obj;
    }
});

blog.filter('markdown',function(markdownObj){
    return function(text){
        return markdownObj.getMarkdown().render(text);
    };

});

//blog.filter("text",function(){
//    return function(text){
//        var regexp = new RegExp("<\s*img.*?>","gim");
//        var text = text.replace(regexp,"[img]");
//        return $(text).text();
//    }
//});

blog.filter('replaceEnter',function(){
    return function(text){
        return text.replace(new RegExp('\n','gm'),"<br/>");
    }
})

blog.filter('trustHTML', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    }}]);

blog.filter('noImg',function(){
    return function(data){
        var regexp = new RegExp("<\s*img.*?>","gim");
        var data = data.replace(regexp,"[img]");
        return data;
    };
});

blog.filter('articleFilter',function(){
    return function(data){
        var regexp = new RegExp("<\s*img.*?>","gim");

        var imgTags = data.match(regexp);
        if(imgTags == null){
            return data;
        }
        var tag,newTag;
        for(i=0;i<imgTags.length;i++){
            tag = imgTags[i];
            newTag = $(tag).addClass("responsive-img")[0].outerHTML;
            data = data.replace(tag,newTag);
        }

        // try {
        //     var dom = $(data);
        //     dom.find("table").addClass("bordered").addClass("responsive-table");
        //     data = dom.html();
        //
        // }
        // catch (e) {
        //     console.log(e.message );
        // }
        return data;
    };
});

blog.filter("articleDate",function(){
    return function(data){
        var time = parseInt(data);
        if(isNaN(time)){
            return "NaN";
        }
        var date = new Date(time);
        return date.toDateString();
    }
});



blog.value("user",{
    id:-1,
    userName:"guest",
    password:"",
    email:"",
    loginStatus: false,
    userType:-1,
    birthday:0,
    description:"",
    profileImgId:"-1",
    profileImgUrl:"/resource/static/img/defaultProfileImg.png",
});



blog.controller("header",function($scope,$http,user,url,ajaxConfig){
    $scope.user = user;
    var postConfig = {
        url:url.getUserInfo,
        success: function (data) {
            data = toObject(data);
            user= $.extend(user,data);
            //$scope.user = data;
            $scope.$apply();
        }
    };
    postConfig = $.extend({},ajaxConfig,postConfig);
    $.ajax(postConfig);

    $scope.signOut = function(){
        var post = {
            url:url.signOut,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    alert("注销成功！");
                    window.location.reload();
                }
                else{
                    alert("注销失败！ "+data.message);
                }
            }
        };

        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }
});

blog.controller("showAllArticle",function($scope,url,ajaxConfig){
    $scope.articles = new Array();

    var obj = {
        url:url.getAllArticle,
        success:function(data){
            data = toObject(data);
            if(data.status == true){
                $scope.articles = data.obj;
                $scope.$apply();
            }
            else{
                alert(data.message);
            }
        }
    };
    ajaxConfig = $.extend({},ajaxConfig);
    obj = $.extend(ajaxConfig,obj);
    $.ajax(obj);
});

blog.controller("userInfoCard",function($scope,url,ajaxConfig,user){
    $scope.user = user;
    $scope.birthdayText = "";
    $scope.editStatus = false;
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });
    $scope.$watch("user",function(newValue,oldValue){
        var pickadate = $('.datepicker').data().pickadate;
        pickadate.set('select',user.birthday*1000);
        $scope.birthdayText = $('.datepicker').val()
        //$scope.birthdayText =
    },true);
    $scope.switchEditStatus = function(){
        if($scope.editStatus == false){
            $scope.editStatus = true;
        }
        else{
            $scope.editStatus = false;
            $scope.birthdayText = $('.datepicker').val();
            var data = $scope.user;
            var datepicker =  $('.datepicker').data().pickadate;
            var birthdayObj = datepicker.component.item.select;
            var birthday = birthdayObj.obj.getTimestamp();
            data.birthday = birthday;
            var post = {
                url:url.editUserInfo,
                data:data,
                success:function(data){
                    data = toObject(data);
                }
            };
            post = $.extend({},ajaxConfig,post);
            $.ajax(post);
        }
    }

    //-----modal-----
    var editProfileModal = {
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
            //opacity: .5, // Opacity of modal background
            //in_duration: 300, // Transition in duration
            //out_duration: 200, // Transition out duration
            ready: resetMotal, // Callback for Modal open
            complete: resetMotal // Callback for Modal close
    }
    function resetMotal(){
        $("#uploadDiv").css("display","block");
        $("#cutProfileImgDiv").css("display","none");
    }

    $scope.openModal = function(){
        $('#modal1').openModal(editProfileModal)
    }
    $scope.closeModal = function(){
        $('#modal1').closeModal(editProfileModal)
    }


    $scope.cutImg = function(){
        var data = $scope.size.getSendVersion();
        if(data.w == 0 || data.h ==0){
            alert("invalid size!");
            return;
        }

        var post = {
            url:url.cutProfileImg,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    $("#displayProfileImg").attr("src",data.path);
                    $("#profileImg").attr("src",data.path);
                    $scope.user.patch = data.path;
                    $scope.closeModal();
                    $scope.$apply();
                }
                else{
                    alert("database error!");
                }
            }
        };
        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    };


    function updateScale(){
        $('#cutProfileImg').ready(function(){
            var img = new Image();
            img.src =$scope.user.profileImgUrl;
            img.onload = function(){
                var realwidth = img.width;
                var realheight = img.height;
                var scale =$('#cutProfileImg').width()/realwidth;
                $scope.size.scale = scale;
            }
        });

    }


    $scope.initJcrop = function() {
        $("#uploadDiv").css('display','none');
        $('#cutProfileImgDiv').css('display','block');

        $('#cutProfileImg').ready(function(){
            $(window).resize(updateScale);
            var img = new Image();
            img.src =$scope.user.profileImgUrl;

            img.onload = function(){
                var realwidth = img.width;
                var realheight = img.height;

                var scale =$('#cutProfileImg').width()/realwidth;

                $scope.size.scale = scale;
            }
        });
        jQuery(function ($) {

            // Create variables (in this scope) to hold the API and image size
            var jcrop_api,
                boundx,
                boundy,

            // Grab some information about the preview pane
                $preview = $('#preview-pane'),
                $pcnt = $('#preview-pane .preview-container'),
                $pimg = $('#preview-pane .preview-container img'),

                xsize = $pcnt.width(),
                ysize = $pcnt.height();
            xsize = 1;
            ysize = 1;
            $('#cutProfileImg').Jcrop({
                onChange: updateSize,
                onSelect: updateSize,
                onRelease: clearSize,
                aspectRatio: xsize / ysize
            }, function () {
//      // Use the API to get the real image size
//      var bounds = this.getBounds();
//      boundx = bounds[0];
//      boundy = bounds[1];
//      // Store the API in the jcrop_api variable
//      jcrop_api = this;
//
//      // Move the preview into the jcrop container for css positioning
//      $preview.appendTo(jcrop_api.ui.holder);
            });

            //function updatePreview(c) {
            //    if (parseInt(c.w) > 0) {
            //        var rx = xsize / c.w;
            //        var ry = ysize / c.h;
            //
            //        $pimg.css({
            //            width: Math.round(rx * boundx) + 'px',
            //            height: Math.round(ry * boundy) + 'px',
            //            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            //            marginTop: '-' + Math.round(ry * c.y) + 'px'
            //        });
            //    }
            //};

        });


    }
    function updateSize(s) {
        $scope.size.x = s.x;
        $scope.size.y = s.y;
        $scope.size.x2 = s.x2;
        $scope.size.y2 = s.y2;
        $scope.size.w = s.w;
        $scope.size.h = s.h;
    }

    function clearSize(s) {
        $scope.size = {x: 0, y: 0, x2: 0, y2: 0, w: 0, h: 0,scale:1};
    }

    $scope.size = {x: 0, y: 0, x2: 0, y2: 0, w: 0, h: 0,scale:1,
        getSendVersion:function(){
            return {x: Math.floor(this.x/this.scale), y: Math.floor(this.y/this.scale),
                x2: Math.floor(this.x2/this.scale), y2: Math.floor(this.y2/this.scale),
                w: Math.floor(this.w/this.scale), h: Math.floor(this.h/this.scale)
            };
        }
    };

    $scope.uploadProfileImg = function(){
        var data = new FormData();
        file = $("#file")[0].files[0];
        if(typeof (file) == "undefined"){
            alert("please select a image!");
            return;
        }
        data.append("file", file);
        var post = {
            data: data,
            type: "POST",
            processData: false,
            contentType: false,
            cache: false,
            //enctype:"multipart/form-data",
            url: "/user/uploadUserImg",
            success: function(data) {
                data = toObject(data);
                $scope.user.profileImgUrl = data.path;
                $scope.$apply();
                $scope.initJcrop(data.path);

            }
        };
        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }


});


blog.controller("userArticle",function($scope,url,ajaxConfig,user){
    $scope.articles = new Array();
    $scope.user = user;
    var post = {
        url:url.getUserArticle,
        success:function(data){
            data = toObject(data);
            if(data.status == true)
                $scope.articles = data.obj;
            $scope.$apply();
        }
    };

    post = $.extend({},ajaxConfig,post);
    $.ajax(post);

});

blog.controller("register",function($scope,url,ajaxConfig){
    $scope.userName = "";
    $scope.password = "";
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    });
    $scope.checkUserInfo = function(){
        var myurl = "/user/checkRegister";
        var data = {userName:$scope.userName,password:$scope.password};
        if(data.userName == ""){
            alert("userName required!");
            return;
        }
        if(data.password == ""){
            alert("password required!");
            return;
        }
        var post = {
            url:url.checkRegister,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    alert("信息可用")
                }
                else{
                    alert(data.message);
                }

            }
        };

        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }

    $scope.insertUser = function(){
        var datepicker = $('#datepicker').data().pickadate;
        var birthdayObj = datepicker.component.item.select;
        var birthday = birthdayObj.obj.getTimestamp();
        var data = {
            userName:$scope.userName,
            password:$scope.password,
            birthday:birthday,
            email:$scope.email,
        };
        if(data.userName == ""){
            alert("userName required!");
            return;
        }
        if(data.password == ""){
            alert("password required!");
            return;
        }
        var post = {
            url:url.insertUser,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true) {
                    alert("注册成功！");
                    window.location.href = url.loginPage;
                }
                else{
                    alert("注册失败 "+data.message);
                }
            }
        };

        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }
});

blog.controller("login",function($scope,url,ajaxConfig){
    $scope.userName = "";
    $scope.password = "";

    $scope.login = function(){
        var data = {userName:$scope.userName,password:$scope.password};
        if(data.userName == ""){
            alert("userName required!");
            return;
        }
        if(data.password == ""){
            alert("password required!");
            return;
        }
        var post = {
            url:url.login,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    alert("login success!");
                    window.location.href = url.userSpace;
                }
                else{
                    alert("登录失败！ "+data.message);
                }
            }
        };

        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }
});

blog.controller("showSingleArticle",function($scope,url,ajaxConfig,defaultArticle){
    $scope.article = defaultArticle;

    var post = {
        url:window.location.pathname,
        success:function(data){
            data = toObject(data);
            if(data.status == true){
                $scope.article = data.obj;
                $scope.$apply();
            }
            else{
                alert("unable to get article!");
            }
        }
    }

    var post = $.extend({},ajaxConfig,post);
    $.ajax(post);
});
blog.controller("addArticle",function($scope,url,ajaxConfig,fn,defaultArticle,user){
    $scope.title = "";
    $scope.articleStatus = true;
    $scope.previewContent = "";
    $scope.addArticle = function(type){
        var data = new Object();
        data = $.extend(data,defaultArticle);
        if(type == 'markdown'){
            $scope.content = $("#textarea1").val();
            data.content = $scope.content;
            data.type = "markdown";
        }else{
            var f = fn.getSummernoteContent;
            data.content = fn.getSummernoteContent();
            data.type = "article";
        }

        data.title = $scope.title;
        var articleStatus = 1;
        if($scope.articleStatus == false){
            articleStatus = 0;
        }
        data.status = articleStatus;
        if(data.title == ""){
            alert("please input title!");
            return;
        }
        var post = {
            url:url.addArticle,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    alert("添加成功！");
                    window.location.href = "/user";
                }
                else{
                    alert("添加失败！ "+data.message);
                }
            }
        }
        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }

    $scope.preview = function(){
        $scope.content = $("#textarea1").val();
        $scope.previewContent = $scope.content;
        $('#modal1').openModal();
    }

    $scope.clickOther = fn.clickOther;

    //$scope.a = function(){console.log('c')}

});



blog.controller("modifyArticle",function($scope,url,ajaxConfig,fn,defaultArticle){
    $scope.article = defaultArticle;
    $scope.articleStatus = false;
    var iframe = document.getElementById("summernoteFrame");
    iframe.onload = function () {
        var pageUrl = window.location.pathname;
        var articleUrl = pageUrl.replace("modify", "show");
        var post = {
            url: articleUrl,
            success: function (data) {
                data = toObject(data);
                if (data.status == true) {
                    $scope.article = data.obj;
                    if(data.obj.status == 1){
                        $scope.articleStatus  = true;
                    }
                    else{
                        $scope.articleStatus = false;
                    }

                    fn.setSummernoteContent(data.obj.content);
                    $scope.$apply();
                }
            }
        };

        post = $.extend({}, ajaxConfig, post);
        $.ajax(post);
    };

    $scope.modifyArticle = function(){
        var location = window.location;
        var articleId = location.getPathSegment(3);
        var content   = $('#summernoteFrame')[0].contentWindow.getSummernoteContent();
        var articleStatus = 1;
        if($scope.articleStatus == false){
            articleStatus = 0;
        }

        var data = $.extend({
            title:$scope.article.title,
            content:content,
            articleId:articleId,
            status:articleStatus
        },this.article);
        if(data.title == ""){
            alert("please input title!");
            return;
        }
        var post = {
            url:url.modifyArticle,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    alert("修改成功！");
                    window.location.href = url.userSpace;
                }
                else{
                    alert(data.message);
                }
            }
        };
        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }

});

blog.controller("modifyMarkdown",function($scope,url,ajaxConfig,fn,defaultArticle){
    $scope.article = defaultArticle;
    $scope.articleStatus = false;
    var pageUrl = window.location.pathname;
    var articleUrl = pageUrl.replace("modify", "show");
    var post = {
        url: articleUrl,
        success: function (data) {
            data = toObject(data);
            if (data.status == true) {
                $scope.article = data.obj;
                if(data.obj.status == true){
                    $scope.articleStatus  = true;
                }
                else{
                    $scope.articleStatus = false;
                }
                $scope.$apply();
            }
        }
    };

    post = $.extend({}, ajaxConfig, post);
    $.ajax(post);

    $scope.preview = function(){
        $scope.article.content = $("#textarea1").val();
        $scope.previewContent = $scope.article.content;
        $('#modal1').openModal();
    }

    $scope.modifyArticle = function(){
        var location = window.location;
        var articleId = location.getPathSegment(3);
        $scope.article.content = $("#textarea1").val();
        var content   = $scope.article.content;
        var articleStatus = 1;
        if($scope.articleStatus == false){
            articleStatus = 0;
        }
        var data = $.extend({
            title:$scope.article.title,
            content:content,
            articleId:articleId,
            status:articleStatus
        },this.article);
        if(data.title == ""){
            alert("please input title!");
            return;
        }
        var post = {
            url:url.modifyArticle,
            data:data,
            success:function(data){
                data = toObject(data);
                if(data.status == true){
                    alert("修改成功！");
                    window.location.href = url.userSpace;
                }
                else{
                    alert(data.message);
                }
            }
        };
        post = $.extend({},ajaxConfig,post);
        $.ajax(post);
    }
});