var _account=null;
var _pwd=null;
function login(){
	var _flag1=checkAccount();
	var _flag2=checkPwd();
	if(_flag1&&_flag2){
			console.log("_flag1:"+_flag1+"_flag2:"+_flag2);
			console.log("_account:"+_account+"_pwd:"+_pwd);
			//执行ajax
			$.ajax({
				type:'post',
				url:"/starApp/login",
				data:{user:_account,pwd:_pwd},
				dataType:"json",
				success : function(feed_data){
					console.log(feed_data);

						console.log("登陆成功!");
                        window.location.href = 'forget_pwd';

					
				}
					
					})
	}else{
		console.log("账户或密码有问题");
	}
	
}
function checkAccount(){
	 _account=$("#account").val();
	console.log(_account);
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	//test()方法用于检测一个字符串是否匹配某个模式
    if (filter.test(_account)) {
    	
        return true; 
    } else {
    	console.log("账户不匹配，不是邮件格式")
        return false;
    } 
	
}
function checkPwd(){
	_pwd=$("#pwd").val();
	console.log(_pwd);
	if(_pwd){//不为空判断
		return true;
	}else{
		console.log("密码为空，请重新输入")
		return false;
	}
}
function register(){
    var _flag1=checkAccount();
    var _flag2=checkPwd();
    if(_flag1&&_flag2){
        console.log("_flag1:"+_flag1+"_flag2:"+_flag2);
        console.log("_account:"+_account+"_pwd:"+_pwd);
        //执行ajax
        $.ajax({
            type:'post',
            url:"/starApp/register",
            data:{user:_account,pwd:_pwd},
            dataType:"json",
            success : function(feed_data){
                console.log(feed_data);
                if("success"==feed_data.message){
                    console.log("注册成功!");
                }

            }

        })
    }else{
        console.log("账户或密码有问题");
    }
}