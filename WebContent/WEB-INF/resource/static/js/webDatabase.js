

function login(){
    var userName = $("#userName").val();
    var password = $("#password").val();
    var myurl = "/user/login";
    var data = {userName:userName,password:password};
    
    var ajax = $.extend({},config.ajaxConfig,
    		{
    			url:myurl,
    			data:data,
    			success:function(data){//alert(data);
    	            //console.log(data);
    	            data=eval('('+data+')');
    	            if(data.status == true){
    	                alert("登陆成功！"); 
    	                if(window.location.pathname = "/user/login"){
    	                	window.location.href = "/user/";
    	                    
    	                }
    	                else{
    	                    window.location.reload();
    	                    
    	                }
    	            }
    	            else{
    	            	alert("用户名或密码错误！");
    	            }
    			}
    		});
    $.ajax(ajax);
}
///article/add
/*var temp = document.createElement("form");
temp.action = URL;
temp.method = "post";
temp.style.display = "none";     
temp.appendChild(title);
temp.appendChild(content);
document.body.appendChild(temp);        
temp.submit();
return temp; */
function addPage(){
	//var title = document.getElementById("title");
	var Title = $("#title").val();
	//var content = document.getElementById("content");
	var Content = $("#content").val();
	var myurl = "/article/add";
	var data = {title:Title, content:Content};
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:myurl,
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.href = "/user/";
            }
            else{
            	alert("失败");
            }
		}
	});
$.ajax(ajax);
}

function likeArticle(id){
	var likenum = $("#likeNum");
	var likebt = document.getElementById("likeheart");
	//alert(likebt.className);
	if(likebt.className == "mdi-action-favorite-outline"){
		likebt.className = "mdi-action-favorite";
		var command = "like";
	}
	else{
		likebt.className = "mdi-action-favorite-outline"
		var command = "unlike";
	}
	var data={aid:id, command:command};
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/article/like",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            //alert(id);
            if(data.status == true){
                //window.location.reload();
            	if(command == "like")
            		likenum.text(parseInt(likenum.text())+1);
            	else
            		likenum.text(parseInt(likenum.text())-1);
            }
		}
	});
	 
	$.ajax(ajax);
}

function articleDelete(id){
	var data = {aid: id};
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/article/delete",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            window.location.reload();
		}
	});
$.ajax(ajax);
}

function articleEdit(id){
}

function signOut(){
	var data;
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/user/logout",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.href = "/";
            }
            else{
            }
		}
	});
$.ajax(ajax);
}
function addTagInput(){
	var input = $("#inputTagName");
	var lable = $("#labTagName");
	var submit = $("#TagSubmit")
	if(input.css("display")=="none"){
		$("#inputTagName").css("display","inline");
		$("#labTagName").css("display","inline");
		$("#TagSubmit").css("display","inline");
		//alert($("#inputTagName").css("display"));
	}
	else{
		input.css("display", "none");
		lable.css("display", "none");
		$("#TagSubmit").css("display","none");
	}
}
function addTag(id){
	var data = {aid:id, tname:$("#inputTagName").val()};
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/article/addTag",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.reload();
            }
            else{
            }
		}
	});
$.ajax(ajax);
}
function deletTag(Aid, Tid){
	var data= {aid:Aid, tid:Tid};
	event.returnValue = confirm("删除是不可恢复的，你确认要删除吗？");
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/article/deleteTag",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.reload();
            }
            else{
            }
		}
	});
	if(event.returnValue){
		$.ajax(ajax);
	}
}
function insertUser(){
	var act = $("#useraccount").val();
	var pw = $("#password").val();
    var pwc = $("#password_check").val();
    var nm = $("#username").val();
    var sex;
    var mal = $('input:radio[name="sex"]:checked').val();
    var warning1 = $("#warning1"); 
    var warning2 = $("#warning2"); 
    var data={account:act, name:nm, password:pw, sex:sex};
    if(mal == 1)
    	sex="male";
    else
    	sex="female";
    if(pw != pwc){
    	warning1.css("display", "inline");
    	return;
    }
    if(pw.length < 6){
    	warning2.css("display", "inline");
    	return;
    }
    var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/user/register/register",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.href="/";
            }
            else{
            }
		}
	});
	if(event.returnValue){
		$.ajax(ajax);
	}
}
function articleEdit(aid){
	window.location.href="/article/modifyPage/"+aid;
}
function modifyPage(Aid){
	var Title = $("#title").val();
	//var content = document.getElementById("content");
	var Content = $("#content").val();
	var myurl = "/article/modify";
	var data = {aid:Aid, title:Title, content:Content};
	var ajax = $.extend({},config.ajaxConfig,
    		{
		url:myurl,
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.href = "/user/";
            }
            else{
            	//alert("失败");
            }
		}
	});
$.ajax(ajax);
}

function changePage(){
	window.location.href="/user/editInfo";
}

function editUser(){
	var act = $("#useraccount").val();
	var pw = $("#password").val();
    var pwc = $("#password_check").val();
    var nm = $("#username").val();
    var sex;
    var age=$("#userage").val();
    var mal = $('input:radio[name="sex"]:checked').val();
    var warning1 = $("#warning1"); 
    var warning2 = $("#warning2"); 
    var content=$("#content").val();
    if(mal == 1)
    	sex="male";
    else
    	sex="female";
    if(pw != pwc){
    	warning1.css("display", "inline");
    	return;
    }
    else{
    	warning1.css("display", "none");
    }
    if(pw.length < 6){
    	warning2.css("display", "inline");
    	return;
    }
    else{
    	warning2.css("display", "none");
    }
    var data={name:nm, sex:sex, age:age, password:pw, description:content};
    var ajax = $.extend({},config.ajaxConfig,
    		{
		url:"/user/editInfo/edit",
		data:data,
		success:function(data){
            data=eval('('+data+')');
            if(data.status == true){
            	window.location.href="/";
            }
            else{
            }
		}
	});
	$.ajax(ajax);
}
function findUser(){
	var name = window.prompt("请输入要查找的用户名：","SB泽年");
	alert(name);
}