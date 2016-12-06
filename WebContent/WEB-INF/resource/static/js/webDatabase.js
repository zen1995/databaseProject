

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
    	            	alert("用户名或密码错误！");
    	            }
    			}
    		});
    $.ajax(ajax);
}

function addPage(URL){
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
  
    temp.appendChild(title);
    temp.appendChild(content);
    document.body.appendChild(temp);        
    temp.submit();        
    return temp; 
}

function likeArticle(){
	var likebt = document.getElementById("likeheart");
	//alert(likebt.className);
	if(likebt.className == "mdi-action-favorite-outline"){
		likebt.className = "mdi-action-favorite";
	}
	else{
		alert("您已经点过赞啦！！");
	}
	
}


