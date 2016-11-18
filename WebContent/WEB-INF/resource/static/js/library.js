config = {
    defaultProfileImg : "/resource/static/img/defaultProfileImg.png",
    editProfileModal:{
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        //opacity: .5, // Opacity of modal background
        //in_duration: 300, // Transition in duration
        //out_duration: 200, // Transition out duration
        ready: resetMotal, // Callback for Modal open
        complete: resetMotal // Callback for Modal close
    },
	ajaxConfig:{
	    url:"",
	    type:"POST",
	    data:{},
	    error:function(data){
	        alert("server error!-"+this.url+"-"+this.data);
	    }
	}
};
function resetMotal(){
    $("#uploadDiv").css("display","block");
    $("#cutProfileImgDiv").css("display","none");
}

globalInfo = {
    user :null
};

Date.prototype.getTimestamp = function(){
    return Math.floor(this.getTime()/1000);
};
Date.createByTimestamp = function (timestamp) {
    return new Date(Math.floor(timestamp*1000));
};

String.prototype.replaceImgTag = function(){
    var regexp = new RegExp("<img.*?>","gim");
    var s = this.replace(regexp,"[img]");
    return s;
};

Location.prototype.isSubpath = function(subpath){
    var path = this.pathname;
    var regexp = new RegExp(subpath);
    if(path.search(regexp) <0){
        return false;
    }
    return true;
}

Location.prototype.getPathSegment = function(index){
    var path = this.pathname;
    var segment = path.split("/");
    if(typeof(index) == "undefined"){
        return segment;
    }
    else{
        return segment[index];
    }

}

function toObject(json){
    return eval("("+json+")");
}

//Object.prototype.convert = function(){
//    if(typeof(this.type) == 'undefined'){
//        return null;
//    }
//    if(this.status == false){
//        return null;
//    }
//    if(this.type == "article"){
//        var obj = this.obj;
//        if(obj instanceof Array){
//            var array = new Array();
//            for(i=0;i<obj.length;i++){
//                array.push(new Article(obj[i]));
//            }
//            return array;
//        }
//        return new Article(obj);
//    }
//    return null;
//}

function Article(obj){

    this.content = null;
    this.title = null;
    this.id = -1;
    this.time = 0;
    this.publisherId = -1;
    this.views = 0;

    if(typeof obj != "undefined" && typeof obj != null){
        this.content = obj.content;
        this.title = obj.title;
        this.id = obj.id;
        this.time = obj.time;
        this.publisherId = obj.publisherId;
        this.views = obj.views;
    }
}

Article.prototype.appendData = function () {
    $("#title").html(this.title);
    $("#content").html(this.content);
}

$(document).ready(function(){
    $("textarea").on('keydown',function(e){
        if(e.keyCode == 9){
            e.preventDefault();
            var indent = '\t';
            var start = this.selectionStart;
            var end = this.selectionEnd;
            var selected = window.getSelection().toString();
            selected = indent + selected.replace(/\n/g,'\n'+indent);
            this.value = this.value.substring(0,start) + selected + this.value.substring(end);
            this.setSelectionRange(start+indent.length,start+selected.length);
        }
    });
});
function clickOther(id){
    return $("#"+id).click();
}

function sendMarkdownFile() {
    var data = new FormData();
    var location = window.parent.location;
    var articleId;
    var publisherId = -1;
    if(location.isSubpath("/article/addArticle") || location.isSubpath("/article/addArticleMarkdown")){
        articleId = -1;
        publisherId = info.id
    }
    else{
        articleId = location.getPathSegment(3);
        publisherId = info.id;
    }
    var  file = $("#sendFile")[0].files[0];
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
            var text = $("textarea").val();
            var tag = '<img src='+jsonn.path+'/>';
            $("textarea").val(text +tag);
        },
        error:function(data){console.log(data);}
    });
}