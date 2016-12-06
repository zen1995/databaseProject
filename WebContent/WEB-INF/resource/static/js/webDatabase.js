

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

function addPage(){
	var head = document.getElementById();
	var content = document.getElementById();
}


