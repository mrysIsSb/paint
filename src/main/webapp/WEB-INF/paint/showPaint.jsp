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
<title>showPaint</title>
<%@ include file="/htm/jsAndCss.htm"%>
<style type="text/css">

</style>
</head>
<body>
<%@ include file="/htm/head.htm"%>
	<ol class="breadcrumb">
		<li style="margin-left: 20px;"><strong> <span
				style="color: #27a0d7">home</span>
		</strong></li>
	</ol>
	<div class="container ">
		<div class="row clearfix">
			<div>
				<div class="col-md-8 column"  id="canvasdiv"></div>
				<div class="form-horizontal col-md-4 column" >
					<div class="panel-group" id="accordion">
					    <div class="panel panel-default">
					        <div id="labeldiv" class="panel-heading" data-toggle="collapse" data-parent="#accordion" 
					                href="#describediv">
					        </div>
					        <div id="describediv" class="panel-collapse collapse in">
					            <div class="panel-body">
					            </div>
					        </div>
					    </div>
					    <div class="panel panel-default">
					        <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" 
					                href="#collapseTwo">
					                <a>详细信息</a>
					        </div>
					        <div id="collapseTwo" class="panel-collapse collapse">
					        <div id="infobody" class="panel-body">
					        </div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var Wheight = $(window).height()-110;
	var Wwidth =$(window).width()-30;
	var Wwh=Wheight>Wwidth?Wwidth:Wheight;
	var scale=Wwh/800;
	var cancas = '<canvas id="myCanvas" class="divcenter" width="'+Wwh
	+'px" height="'+Wwh
	+'px" style="border:1px solid #000000;"> 您的浏览器不支持 HTML5 canvas 标签。 </canvas>';
	$("#canvasdiv").append(cancas);
	
	//获取画布内容
	var context = document.getElementById('myCanvas').getContext("2d");
	function clearCanvas() {
		datajson = "[";
		context.clearRect(0, 0, document.getElementById('myCanvas').width,
				document.getElementById('myCanvas').height);
	}
	function showTime(datajsonObj) {
		for (var i = 0; i < datajsonObj.length; i++) {
			context.strokeStyle = datajsonObj[i]["color"];
			context.lineJoin = "round";
			context.lineWidth = datajsonObj[i]["lineWidth"]*scale;//画笔粗细
			for (var j = 0; j < datajsonObj[i]["x_y"].length; j++) {
				context.beginPath();
				if (j == 0) {
					context.moveTo(datajsonObj[i]["x_y"][j]["x"]*scale - 1,
							datajsonObj[i]["x_y"][j]["y"]*scale);
				} else {
					context.moveTo(datajsonObj[i]["x_y"][j - 1]["x"]*scale,
							datajsonObj[i]["x_y"][j - 1]["y"]*scale);
				}
				context.lineTo(datajsonObj[i]["x_y"][j]["x"]*scale,
						datajsonObj[i]["x_y"][j]["y"]*scale);
				context.closePath();
				context.stroke();
			}
		}
	}
	$(function(){
		clearCanvas();
		$.ajax({
			type:'GET',
			url : '<%=path%>/maincontroller/getlastpaint',
			ContentType : 'application/x-www-form-urlencoded',
			dataType : "json",
			success:function(data){
				console.log(data);
				var content=JSON.parse(data.content);
				showTime(JSON.parse(content.content));
				var labelarr=content.label.split(",");
				$(labelarr).each(function(i,e){
					labelspan= $(' <span class="label label-defult"><em name="em" class="text-muted">'+e+'</em></span>');
					labelspan.bind('click',function(){
						console.log(this);
					});
					$('#labeldiv').append(' ');
					$('#labeldiv').append(labelspan);
				});
				$("#describediv").find("div").html(content.describe);
				$('#infobody').append('<label>作者：'+content.nickname+'</label><br>');
				$('#infobody').append('<label>发布时间：'+content.createtime+'</label>');
			},
			errot: function(){
				console.log("error");
			}
		});
	});
</script>
</html>