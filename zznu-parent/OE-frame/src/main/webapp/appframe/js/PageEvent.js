$(document).ready(function() {
	
	$("a[name='menu_a']").click(function(){//菜单显示点击切换
		var list = $("#top_menu_ul").children('li');
		$.each(list,function(i,li){
			if($("#"+li.id).hasClass("active") == true){
				$("#"+li.id).removeClass("active");
			}
		});
		
		var parentObj = $(this).parent();
		parentObj.addClass("active");

//		$(this).removeAttr("href");
	});

	

});

