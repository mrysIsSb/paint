(function($) {
	var canvasinfodefault = {
		url:undefined,
		ynum:10,
		time:5000,
		xystrokeStyle:'red',
		usinglinestrokeStyle:'#0ff',
		freelinestrokeStyle:'#f0f',
		totallinestrokeStyle:'#ff0',
		leftOffset:10,
		rightOffset:10,
		topOffset:10,
		bottomOffset:10
	};
	var canvas;   
	var context;
	var canvasinfo;
	var x1=y1=0;
	var i=0;
	var datajson;
	var usingarr;
	var freearr;
	var totalarr;
	var max;
	//var a=0;
  	$.fn.createcanvas = function(canvasinfo1) {
		canvasinfo = $.extend(canvasinfodefault, canvasinfo1);
		usingarr=new Array(canvasinfo.ynum);
		freearr=new Array(canvasinfo.ynum);
		totalarr=new Array(canvasinfo.ynum);
		var $this=$(this);
		$this.html(null);
		if(typeof(canvasinfo1.url)!="undefined"){
			inicanvas($this);
		}else{
			alert("undefined1");
		}
		
	};
	
	var addcanvashtml=function(e){
		var wh=new canvaswidthheight();
		e.append('<canvas id="'+myId()+'" width="'+wh.width+'"height="'+wh.height+'">您的浏览器不支持html5 canvas</canvas>');
		this.e=e.find('canvas')[0];
		this.id=this.e.getAttribute('id');
		this.width=wh.width;
		this.height=wh.height;
	}
		
	var inicanvas= function(e){
		canvas=new addcanvashtml(e);
		context=canvas.e.getContext('2d');
		drawall();
		setInterval(drawall,canvasinfo.time);
		//setInterval(addliz,100);
	}
	
	var canvaswidthheight =function(){
		this.width=600;
		this.height=300;
		var Wwidth=$(window).width();
		var Wheight =$(window).height();
		if(Wwidth/2>this.width&&Wheight/2>this.height){
			this.width=Math.round(Wwidth/2);
			this.height=Math.round(Wheight/2);
		}
	}
	
	var addxy =function (){
		var width=canvas.width;
		var height=canvas.height;
		var arrowHeight=10;
		var arrowWidth=6;
		context.lineWidth = 1;
		//绘制x坐标轴
		context.beginPath();
		context.strokeStyle=canvasinfo.xystrokeStyle;
		context.translate(0.5,0.5);
		context.moveTo(canvasinfo.leftOffset+30,height-30-canvasinfo.bottomOffset);
		context.lineTo(canvasinfo.leftOffset+30,0+canvasinfo.topOffset);
		context.lineTo(Math.round(canvasinfo.leftOffset+30-arrowWidth/2),Math.round(arrowHeight+canvasinfo.topOffset));
		context.lineTo(canvasinfo.leftOffset+30,Math.round(arrowHeight/2+canvasinfo.topOffset));
		context.lineTo(Math.round(canvasinfo.leftOffset+30+arrowWidth/2),arrowHeight+canvasinfo.topOffset);
		context.lineTo(canvasinfo.leftOffset+30,0+canvasinfo.topOffset);
		
		
		context.moveTo(canvasinfo.leftOffset+30,height-30-canvasinfo.bottomOffset);
		context.lineTo(width-canvasinfo.rightOffset,height-30-canvasinfo.bottomOffset);
		context.lineTo(Math.round(width-arrowHeight-canvasinfo.rightOffset),Math.round(height-30-arrowWidth/2-canvasinfo.bottomOffset));
		context.lineTo(Math.round(width-arrowHeight/2-canvasinfo.rightOffset),height-30-canvasinfo.bottomOffset);
		context.lineTo(width-arrowHeight-canvasinfo.rightOffset,Math.round(height-30+arrowWidth/2-canvasinfo.bottomOffset));
		context.lineTo(width-canvasinfo.rightOffset,height-30-canvasinfo.bottomOffset);
		context.closePath();
		context.stroke();
		context.translate(-0.5,-0.5);
	}
	
	var ajaxurl=function(){
		var r;
		$.ajax({
			type:"post",
			url:canvasinfo.url,
			async:false,
			success:function(jsonstr){
				r=jsonstr;
			}
		});
		return r;
	}
	
	var createjsonobj=function(){
		var jsonObj=JSON.parse(ajaxurl());
		usingarr.unshift(parseInt(jsonObj["total"])-parseInt(jsonObj["free"]));
		freearr.unshift(parseInt(jsonObj["free"]));
		totalarr.unshift(parseInt(jsonObj["total"]));
		if(max==null){
			max=parseInt(jsonObj["max"]);
		}
		for(;usingarr.length>10;){
			usingarr.pop();
			freearr.pop();
			totalarr.pop();
		}
		datajson="[";
		datajson+="{\"color\":\"";
		datajson+=canvasinfo.usinglinestrokeStyle;
		datajson+="\",\"x_y\":[";
		for(var i=0; i < usingarr.length; i++){
			datajson+="{\"x\":\"";
			datajson+=Math.round((i)*(canvas.width-30-canvasinfo.leftOffset-canvasinfo.rightOffset)/(canvasinfo.ynum-1)+30+canvasinfo.leftOffset);
			datajson+="\",\"y\":\"";
			datajson+=Math.round(canvas.height-(canvas.height-30-canvasinfo.topOffset-canvasinfo.bottomOffset)*usingarr[i]/max-canvasinfo.bottomOffset-30);
			datajson+="\"},";
		}
		datajson=datajson.substr(0,datajson.length-1);
		datajson+="]}]";
		
		
		
		datajson1="[";
		datajson1+="{\"color\":\"";
		datajson1+=canvasinfo.freelinestrokeStyle;
		datajson1+="\",\"x_y\":[";
		for(var i=0; i < freearr.length; i++){
			datajson1+="{\"x\":\"";
			datajson1+=Math.round((i)*(canvas.width-30-canvasinfo.leftOffset-canvasinfo.rightOffset)/(canvasinfo.ynum-1)+30+canvasinfo.leftOffset);
			datajson1+="\",\"y\":\"";
			datajson1+=Math.round(canvas.height-(canvas.height-30-canvasinfo.topOffset-canvasinfo.bottomOffset)*freearr[i]/max-canvasinfo.bottomOffset-30);
			datajson1+="\"},";
		}
		datajson1=datajson1.substr(0,datajson1.length-1);
		datajson1+="]}]";
		
		
		datajson2="[";
		datajson2+="{\"color\":\"";
		datajson2+=canvasinfo.totallinestrokeStyle;
		datajson2+="\",\"x_y\":[";
		for(var i=0; i < totalarr.length; i++){
			datajson2+="{\"x\":\"";
			datajson2+=Math.round((i)*(canvas.width-30-canvasinfo.leftOffset-canvasinfo.rightOffset)/(canvasinfo.ynum-1)+30+canvasinfo.leftOffset);
			datajson2+="\",\"y\":\"";
			datajson2+=Math.round(canvas.height-(canvas.height-30-canvasinfo.topOffset-canvasinfo.bottomOffset)*totalarr[i]/max-canvasinfo.bottomOffset-30);
			datajson2+="\"},";
		}
		datajson2=datajson2.substr(0,datajson2.length-1);
		datajson2+="]}]";
		
		
		this.usingline=datajson;
		this.freeline=datajson1;
		this.totalline=datajson2;
	}
	
	var drawline=function(){
		var jsonobj= new createjsonobj();
		showtime(JSON.parse(jsonobj.usingline));
		showtime(JSON.parse(jsonobj.freeline));
		showtime(JSON.parse(jsonobj.totalline));
	}
	
	var drawall=function(){
		clear();
		addxy();
		drawgrad();
		drawline();
	}
	
	var drawgrad=function(){
		context.strokeStyle="#f00";
		context.lineJoin = "round";
		context.globalAlpha=0.8;
		context.lineWidth = 1;
		context.beginPath();
		context.translate(0.5,0);
		for(var j=1;j<canvasinfo.ynum+1;j++){
			context.moveTo(Math.round(j*(canvas.width-30-canvasinfo.leftOffset-canvasinfo.rightOffset)/(canvasinfo.ynum-1)+30+canvasinfo.leftOffset),0+canvasinfo.topOffset);
	     	context.lineTo(Math.round(j*(canvas.width-30-canvasinfo.leftOffset-canvasinfo.rightOffset)/(canvasinfo.ynum-1)+30+canvasinfo.leftOffset),canvas.height-30-canvasinfo.bottomOffset);
	     	context.closePath();
		}
		context.translate(-0.5,0);
		context.stroke();
	}
	
	var showtime=function(datajsonObj){
		for(var i=0;i<datajsonObj.length;i++){
			context.strokeStyle=datajsonObj[i]["color"];
			context.lineJoin = "round";
			context.globalAlpha=1;
			context.lineWidth = 2;
			for(var j=0;j<datajsonObj[i]["x_y"].length;j++){
				context.beginPath();
				if(j==0){
					context.moveTo(datajsonObj[i]["x_y"][j]["x"], datajsonObj[i]["x_y"][j]["y"]);
				}else{ 
			     	context.moveTo(datajsonObj[i]["x_y"][j-1]["x"], datajsonObj[i]["x_y"][j-1]["y"]);
				} 	
		     	context.lineTo(datajsonObj[i]["x_y"][j]["x"], datajsonObj[i]["x_y"][j]["y"]);
		     	context.closePath();
		     	context.stroke();
			}
		}
	}
	
	var S4 =function(){
		return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
	}
	
	var myId =function(){
		return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
	}
	
	var clear= function(){
		context.clearRect(0,0,canvas.width,canvas.height);
	}
	
/*	function addliz(){
		console.log(a);
		
		a++;
		addliz();
		if(a==100){
			console.log("return");
			a=0;
			return;
		}
		this.x=Math.random()*canvas.width;
		this.y=Math.random()*canvas.height;
		var context=canvas.e.getContext('2d');
		context.beginPath();
		context.strokeStyle='red';
		context.moveTo(x,y);
		context.lineTo(x+1,y);
		context.stroke();
		
	}
*/	
})(jQuery);

