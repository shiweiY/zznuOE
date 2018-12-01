<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(document).ready(function() {
	/* $("a[name='menu_a']").click(function(){//菜单显示点击切换
		var list = $("#top_menu_ul").children('li');
		$.each(list,function(i,li){
			if($("#"+li.id).hasClass("active") == true){
				$("#"+li.id).removeClass("active");
			}
		});
		
		var parentObj = $(this).parent();
		if(parentObj.hasClass('dropdown') == false){
			parentObj.addClass("active");
		}

	}); */

});
</script>
	<div id="top_div_menu" class="container" style="background: #A52A2A; width: auto; height: auto">
		<div class="col-md-1 column ui-sortable">
			<a id="header_img_a" onclick="gotoIndex()"><img id="header_img_logo" style="cursor:pointer" src="./appframe/images/logo.png" class="img-rounded"></img></a>
		</div>
		<!-- <div class="col-md-10 column ui-sortable"> -->
		<ul id="top_menu_ul" class="nav nav-tabs navbar-right navbar-nav linkWhite"></ul>
		
		<div class="col-md-1 column ui-sortable"></div>
	</div>