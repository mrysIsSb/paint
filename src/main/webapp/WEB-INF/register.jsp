<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible"
	content="IE=edge,chrome=1 text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no">
<title>注册</title>
<%@ include file="/htm/jsAndCss.htm"%>
</head>
<body>
<%@ include file="/htm/head.htm"%>
	<ol class="breadcrumb">
		<li style="margin-left: 20px;"><strong> <span
				style="color: #27a0d7">注册</span>
		</strong></li>
	</ol>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-3 column">
		</div>
		<div class="col-md-6 column">
			<form role="form" class="form-horizontal">
				<div class="form-group has-feedback">
					 <label class="col-md-2 control-label" for="nickname">昵 称</label>
					 <div class="col-md-8">
					 <input type="text" class="form-control" id="nickname" placeholder="请输入昵称"/>
					 <span id="nickname_span" class="form-control-feedback glyphicon glyphicon-asterisk" style="color: rgb(255, 0, 0); font-size: 12px;"></span>
					</div>				
				</div>
				<div class="form-group has-feedback">
					 <label class="col-md-2 control-label" for="username">账 号</label>
					 <div class="col-md-8">
					 <input type="text" class="form-control" id="username" placeholder="请输入账号"/>
					 <span id="username_span" class="form-control-feedback glyphicon glyphicon-asterisk" style="color: rgb(255, 0, 0); font-size: 12px;"></span>
					</div>				
				</div>
				<div class="form-group has-feedback">
					 <label class="col-md-2 control-label" for="password1">密 码</label>
					 <div class="col-md-8">
					 <input type="password" class="form-control" id="password1" placeholder="请输入密码"/>
					 <span id="password1_span" class="form-control-feedback glyphicon glyphicon-asterisk" style="color: rgb(255, 0, 0); font-size: 12px;"></span>
					</div>				
				</div>
				<div class="form-group has-feedback">
					 <label class="col-md-2 control-label" for="password2">确认密码</label>
					 <div class="col-md-8">
					 <input type="password" class="form-control" id="password2" placeholder="请再次输入密码"/>
					 <span id="password2_span" class="form-control-feedback glyphicon glyphicon-asterisk" style="color: rgb(255, 0, 0); font-size: 12px;"></span>
					</div>				
				</div>
				<button type="button" class="btn btn-default" id="submit_btn">注册</button>
				
			</form>
		</div>
		<div class="col-md-3 column">
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$('#nickname').blur(function(){
	var nickname=this.value;
	if(nickname!=''){
		if(nickname.indexOf(' ')==-1){
			$('#nickname_span').removeClass();
			$('#nickname_span').addClass('form-control-feedback glyphicon glyphicon-ok-sign');
			$('#nickname_span').css({"fontSize":"20px" ,"color":"rgb(0,255,0)"});
			$('#nickname_span').attr('istrue','true');
		}else{
			$('#nickname_span').removeClass();
			$('#nickname_span').addClass('form-control-feedback glyphicon glyphicon-remove-sign');
			$('#nickname_span').css({"fontSize":"20px" ,"color":"rgb(255,0,0)"});
			$('#nickname_span').attr('istrue','false');
		}
	}else{
		$('#nickname_span').removeClass();
		$('#nickname_span').addClass('form-control-feedback glyphicon glyphicon-asterisk');
		$('#nickname_span').css({"fontSize":"12px" ,"color":"red"});
		$('#nickname_span').attr('istrue','false');
	}
});
$('#username').blur(function(){
	var username=this.value;
	if(username!=''){
		if(username.indexOf(' ')==-1&&!isHaveChinese(username)&&!isHaveMark(username)){
			$.ajax({
				type : 'GET',
				url : '<%=path%>/maincontroller/ishaveusername',
				data : {
					username : username
				},
				ContentType : 'application/x-www-form-urlencoded',
				success : function(data) {
					var bool=JSON.parse(data.content);
					if(!bool.ishave){
						$('#username_span').removeClass();
						$('#username_span').addClass('form-control-feedback glyphicon glyphicon-ok-sign');
						$('#username_span').css({"fontSize":"20px" ,"color":"rgb(0,255,0)"});
						$('#username_span').attr('istrue','true');
					}else{
						$('#username_span').removeClass();
						$('#username_span').addClass('form-control-feedback glyphicon glyphicon-remove-sign');
						$('#username_span').css({"fontSize":"20px" ,"color":"rgb(255,0,0)"});
						$('#username_span').attr('istrue','false');
					}
				},
			});
			
		}else{
			$('#username_span').removeClass();
			$('#username_span').addClass('form-control-feedback glyphicon glyphicon-remove-sign');
			$('#username_span').css({"fontSize":"20px" ,"color":"rgb(255,0,0)"});
			$('#username_span').attr('istrue','false');
		}
	}else{
		$('#username_span').removeClass();
		$('#username_span').addClass('form-control-feedback glyphicon glyphicon-asterisk');
		$('#username_span').css({"fontSize":"12px" ,"color":"red"});
		$('#username_span').attr('istrue','false');
	}
});
$('#password1').blur(function(){
	var password1=this.value;
	if(password1!=''){
		if(password1.indexOf(' ')==-1&&!isHaveChinese(password1)&&!isHaveMark(password1)){
			$('#password1_span').removeClass();
			$('#password1_span').addClass('form-control-feedback glyphicon glyphicon-ok-sign');
			$('#password1_span').css({"fontSize":"20px" ,"color":"rgb(0,255,0)"});
			$('#password1_span').attr('istrue','true');
		}else{
			$('#password1_span').removeClass();
			$('#password1_span').addClass('form-control-feedback glyphicon glyphicon-remove-sign');
			$('#password1_span').css({"fontSize":"20px" ,"color":"rgb(255,0,0)"});
			$('#password1_span').attr('istrue','false');
		}
	}else{
		$('#password1_span').removeClass();
		$('#password1_span').addClass('form-control-feedback glyphicon glyphicon-asterisk');
		$('#password1_span').css({"fontSize":"12px" ,"color":"red"});
		$('#password1_span').attr('istrue','false');
	}
});
$('#password2').blur(function(){
	var password2=this.value;
	if(password2!=''){
		if(password2.indexOf(' ')==-1&&password2==$('#password1').val()){
			$('#password2_span').removeClass();
			$('#password2_span').addClass('form-control-feedback glyphicon glyphicon-ok-sign');
			$('#password2_span').css({"fontSize":"20px" ,"color":"rgb(0,255,0)"});
			$('#password2_span').attr('istrue','true');
		}else{
			$('#password2_span').removeClass();
			$('#password2_span').addClass('form-control-feedback glyphicon glyphicon-remove-sign');
			$('#password2_span').css({"fontSize":"20px" ,"color":"rgb(255,0,0)"});
			$('#password2_span').attr('istrue','false');
		}
	}else{
		$('#password2_span').removeClass();
		$('#password2_span').addClass('form-control-feedback glyphicon glyphicon-asterisk');
		$('#password2_span').css({"fontSize":"12px" ,"color":"red"});
		$('#password2_span').attr('istrue','false');
	}
});

$('#submit_btn').click(function(){
	if($('#nickname_span').attr('istrue')=='true'&&$('#username_span').attr('istrue')=='true'&&$('#password1_span').attr('istrue')=='true'&&$('#password2_span').attr('istrue')=='true'){
		var nickname=$('#nickname').val();
		var username=$('#username').val();
		var password=$('#password1').val();
		var jsonstr={"nickname":nickname,"username":username,"password":password};
		$.ajax({
			type : 'POST',
			url : '<%=path%>/maincontroller/register',
			data :jsonstr,
			ContentType : 'application/x-www-form-urlencoded',
			dataType : "json",
			success : function(data) {
				if(data.state=='SUCCESS'){
					var userinfo=JSON.parse(data.content);
					$.cookie('userid',userinfo.id,{path:'/'});
					sessionStorage.nickname=userinfo.nickname;
					$('#user').html(userinfo.nickname + '<strong class="caret"></strong>');
					alert(data.message);
				}else{
					alert(data.message);
				}
			},
			error : function(XMLHttpRequest,textStatus,errorThrown){
				alert(textStatus);
			}
		});
	}
});
</script>
</html>