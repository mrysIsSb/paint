(function($) {
	var tableinfo = {
			a:'1',
			b:''
	    };
  $.fn.initable = function(tableinfo1) {
	  var tableinfo1 = $.extend(tableinfo, tableinfo1);
	  alert(tableinfo1.a);
      return this.each(function () {
    	  
      })
  };
  $.fn.getRestHeight=function(){
	  var siblings= this.siblings();
	  for(var i=0;i<siblings.length;i++){
		  console.log(siblings[i].offsetHeight);
		  console.log(siblings[i].getAttribute('style'));
	  }
	  var parents=this.parents();
	  console.log(parents.length);
	  for(var i=0;i<parents.length;i++){
		  console.log(parents[i].offsetHeight);
		  console.log(parents[i].getAttribute('float'));
	  }
  }
})(jQuery);