

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
	var title = $("title").val();
	//var content = document.getElementById("content");
	var content = $("content").val();
	var myurl = "article/add";
	var data = {Title:title, Content:content};
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
            alert(id);
            console.log(data);
            /*if(data.status == true){
            	//window.location.reload();
            }
            else{
            }*/
		}
	});
$.ajax(ajax);
}

function articleEdit(id){
}

