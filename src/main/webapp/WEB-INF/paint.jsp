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
<title>画画</title>
<%@ include file="/htm/jsAndCss.htm"%>
<style type="text/css">

</style>
</head>
<body>
<%@ include file="/htm/head.htm"%>
	<ol class="breadcrumb">
		<li style="margin-left: 20px;"><strong> <span
				style="color: #27a0d7">paint</span>
		</strong></li>
	</ol>
	<div class="container ">
		<div class="row clearfix">
			<div>
				<div class="col-md-8 column" id="canvasdiv"></div>
				<div class=" col-md-4 column" >
					<div >
						<div class="btn-group btn-group-sm" style=" position: relative; left:0px; display: inline;">
							<button type="button" class="btn btn-default" data-dismiss="modal"
								onclick="clearCanvas();">清空</button>
							<button type="button" class="btn btn-default" data-dismiss="modal"
								onclick="publishAndSave(2);">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal"
								onclick="publishAndSave(1);">发布</button>
						</div>
						<div class="input-group input-group-sm" style="width:100px;position: relative; left:0px; display: inline-table;">
							<span class="input-group-btn">
							<button type="button" class="btn btn-default btn-sm">
					          	<span class="glyphicon glyphicon-pencil"></span>
					        </button>
					       	</span>
					        <input class="form-control" id="color" type="color"  name="favcolor">
				      	</div>
				        <div class="input-group input-group-sm" style="width:110px;position: relative; left:0px; display: inline-table;">
							<span class="input-group-btn">
							<button type="button" onclick="minus();" class="btn btn-default btn-sm">
					          	<span class="glyphicon glyphicon-chevron-left"></span>
					        </button>
					       	</span>
				            <input type="text" id="linewidth" pattern="[0-9]" readonly="readonly" style="text-align:center;" class="form-control">
				            <span class="input-group-btn">
							<button type="button" onclick="plus();" class="btn btn-default btn-sm">
					          	<span class="glyphicon glyphicon-chevron-right"></span>
					        </button>
					       	</span>
						</div>
					</div>	
					<hr>
					<div class="panel panel-default">
						<div id="label" class=" panel-heading" >
							<a data-toggle="collapse" data-parent="#accordion" href="#describediv" onclick="showOrHide(this);"><span class="glyphicon glyphicon-chevron-down"></span></a>
							<a href="#" onclick="lab_in_dis();"><span class="glyphicon glyphicon-plus-sign"></span></a> 
							<input id="labelinput" style="background-color:#ccc;display:none;" /> 
						</div>
						<div id="describediv" class="panel-collapse collapse in"><div  class="panel-body"><textarea rows="10" name="describe" class="form-control"></textarea></div></div>
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var Wheight = $(window).height()-110; 												//屏幕可见高-110
	var Wwidth =$(window).width()-30;													//屏幕可见宽-30
	var Wwh=Wheight>Wwidth?Wwidth:Wheight;												//选择宽高小的一个
	var scale=Wwh/800;																	//比例尺
	var lineWidth=10;																	//线宽度
	$('#linewidth').val(lineWidth);
	var type='line';
	var cancas = '<canvas id="myCanvas" class="divcenter" width="'+Wwh
	+'px" height="'+Wwh
	+'px" style="border:1px solid #000000;"> 您的浏览器不支持 HTML5 canvas 标签。 </canvas>';
	$("#canvasdiv").append(cancas);
	var clickX = new Array();
	var clickY = new Array();
	var clickDrag = new Array();
	var paint;
	var color = $("#color").val();
	var mouseX;
	var mouseY;
	var datajson = "[";
	//获取画布内容
	var context = document.getElementById('myCanvas').getContext("2d");
	//在画布中按下鼠标
	$('#myCanvas').mousedown(function(e) {
		mouseX = event.layerX;
		mouseY = event.layerY;
		//console.log(mouseX,mouseY);
		paint = true;
		addClick(mouseX, mouseY);
		redraw();
	});

	$(function() {
		$('#paint_li').addClass('active');
		var x = [];
		showTime(x);
	})

	//鼠标移动
	$('#myCanvas').mousemove(function(e) {
		mouseX = event.layerX;
		mouseY = event.layerY;
		if (paint) {//是不是按下了鼠标
			//	  console.log(mouseX,mouseY);
			addClick(mouseX, mouseY, true);
			redraw();
		}
	});
	//鼠标松开 把paint设置为false
	$('#myCanvas').mouseup(function(e) {
		paint = false;
		if (datajson.length != 1) {
			datajson = datajson.substr(0, datajson.length - 1);
			datajson += ",";
		}
		datajson += "{\"color\":\"";
		datajson += $("#color").val();
		datajson += "\",\"type\":\"";
		datajson += type;
		datajson += "\",\"lineWidth\":\"";
		datajson += lineWidth/scale;
		datajson += "\",\"x_y\":[";
		for (var i = 0; i < clickX.length; i++) {
			datajson += "{\"x\":\"";
			datajson += clickX[i]/scale;
			datajson += "\",\"y\":\"";
			datajson += clickY[i]/scale;
			datajson += "\"},";
		}
		datajson = datajson.substr(0, datajson.length - 1);
		datajson += "]}]";
		//console.log(datajson);
		clickX.splice(0, clickX.length);//清空数组
		clickY.splice(0, clickY.length);
		clickDrag.splice(0, clickDrag.length);
	});
	//鼠标移开画布
	$('#myCanvas').mouseleave(function(e) {
		paint = false;
	});

	function addClick(x, y, dragging) {
		clickX.push(x);
		clickY.push(y);
		clickDrag.push(dragging);
	}

	function redraw() {
		context.strokeStyle = $("#color").val();
		context.lineJoin = "round";
		context.lineWidth = lineWidth;//画笔粗细
		for (var i = 0; i < clickX.length; i++) {
			context.beginPath();//起始一条路径，或重置当前路径
			if (clickDrag[i] && i) {//当是拖动而且i!=0时，从上一个点开始画线
				context.moveTo(clickX[i - 1], clickY[i - 1]);//把路径移动到画布中的指定点，不创建线条
			} else {
				context.moveTo(clickX[i] - 1, clickY[i]);
			}
			context.lineTo(clickX[i], clickY[i]);//添加一个新点然后在画布中创建从该点到最后指定点的线条
			context.closePath();//创建从当前点回到起始点的路径 可以让线条变得圆润
			context.stroke();//绘制已定义的路径
		}
	}

	
	function clearCanvas() {
		datajson = "[";
		context.clearRect(0, 0, document.getElementById('myCanvas').width,
				document.getElementById('myCanvas').height);
	}
	function readData() {
		clear111();
		datajson = localStorage.datajson;
		var datajsonObj = JSON.parse(localStorage.datajson);
		showTime(datajsonObj);
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
	//提交和保存
	function publishAndSave(state){
		var url;
		var describe=$('[name="describe"]').val();
		var label;
		$('[name="em"]').each(function(i,e){
			if(i==0){
				label=e.innerHTML;
			}else{
				label=label+','+e.innerHTML;
			}
		});
		if(state==1){
			url='<%=path%>/paintcontroller/publish';
		}else if(state==2){
			url='<%=path%>/paintcontroller/save'; 
		}
		$.ajax({
			type : 'POST',
			url : url,
			data :{'userid':$.cookie('userid'),'label':label,'content':datajson,'describe':describe,'state':state},
			ContentType : 'application/x-www-form-urlencoded',
			dataType : "json",
			success : function(data) {
				console.log(data);
				if(data.state=='SUCCESS'){
					alert(data.message);
				}else{
					alert(data.message);
				}
			},
			error : function(XMLHttpRequest,textStatus,errorThrown){
				console.log(XMLHttpRequest);
				console.log(errorThrown);
				alert(textStatus);
			}
		});
	}
	//添加标签输入框
	function lab_in_dis(){
		var cssdis= $('#labelinput').css('display');
		if(cssdis=='none'){
			$('#labelinput').css('display','inline-block');
			$('#labelinput').focus();
		}else if(cssdis=='inline-block'){
			$('#labelinput').css('display','none');
		}
	}
	//添加标签
	$('#labelinput').blur(function(e){
		if(trimAllSpace(this.value)==''){
			$('#labelinput').css('display','none');
			this.value='';
			return;
		}
		$('#labelinput').css('display','none');
		var labelspan= $(' <span class="label label-defult"><em name="em" class="text-muted">'+trimAllSpace(this.value)+'</em></span>');
		labelspan.bind('click',function(){
			this.remove();
		});
		$('#label').append(' ');
		$('#label').append(labelspan);
		this.value='';
	});
	function showOrHide(e){
		var right='glyphicon glyphicon-chevron-right';
		var down='glyphicon glyphicon-chevron-down';
		var Class=$(e).children().attr('class');
		$(e).children().removeClass();
		if(right==Class){
			$(e).children().addClass('glyphicon glyphicon-chevron-down');
		}else{
			$(e).children().addClass('glyphicon glyphicon-chevron-right');
		}
		
		
	}
	function plus(){
		var line=$('#linewidth').val();
		$('#linewidth').val(line*1+1);
		lineWidth=$('#linewidth').val();
	}
	function minus(){
		var line=$('#linewidth').val();
		if(line>=2){
			$('#linewidth').val(line-1);
		}else{
			$('#linewidth').val(1);
		}
		lineWidth=$('#linewidth').val();
	}
	//去除空格
	function trimAllSpace(str){
		return str.replace(/\s/g,'');
	}
</script>
</html>