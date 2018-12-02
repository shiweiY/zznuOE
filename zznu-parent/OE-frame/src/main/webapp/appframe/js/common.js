

function initPage(){
	initPageMenu();//菜单初始化
	
	initPageData();//数据初始化
}

function initPageMenu(){
	var menuMap = getSerialData("usrmenu");
	var menuChildMap = null;
	var menuList = menuMap.usrmenu;
	
	$.each(menuList,function(i,menu){
		var menu_type=menu.menu_type;//菜单类型
		if("a_menu" == menu_type){
			$("#top_menu_ul").append("<li id=\"li_"+menu.menu_page+"\"><a onclick=\"gotoPages('"+menu.menu_page+"')\" name=\"menu_a\">"+menu.menu_name+"</a></li>");
		}else if("select_menu" == menu_type){
			$("#top_menu_ul").append("<li id=\"li_"+menu.m_id+"\" class=\"dropdown\">" +
					"<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" name=\"select_menu_a_"+i+"\" >"+
					menu.menu_name+"<span class=\"caret\"></span> </a></li>");
			var key = menu.m_id;
			var liId = "li_"+key;//此下拉选的li标签id
			
			key = "select_menu_"+key;//拼接前缀标识以去缓存获取子选项
			
			menuChildMap = getSerialData(key);//获取到菜单json对象

			var childList = menuChildMap[key];//获取选项value
			
			var ultag = appendMenuUlTag(childList);//完成ul节点
			$("#"+liId).append(ultag);//塞入此下拉选中
		}
	});
	
	//设置菜单为选中状态
	var title = $("title").html();
	var list = $("#top_menu_ul").children('li');
	$.each(list,function(i,li){
		var li_id = $("#"+li.id).attr("id") ;
		var li_title = "li_"+title;
		if(li_id == li_title){
			$("#"+li.id).addClass("active");
		}
	});
	
	
}


function appendMenuUlTag(childList){
	
	var ul = "<ul class=\"dropdown-menu\">";
	$.each(childList,function(i,childMenu){
		ul = ul+" \n<li><a onclick=\"gotoPages('"+childMenu.menu_page+"')\">"+childMenu.menu_name+"</a></li>"
	});
	
	ul = ul + " \n</ul>";
	
	return ul;
}


function initPageData(){
	var KeyArray = new Array();
	var i = 0;
	$("[cache]").each(function(){
        var $obj = $(this);
        var cache = $obj.attr("cache");
        KeyArray[i]= cache;
        i++;
    });
	
	if(KeyArray.length > 0){
		var jsonData = getRedisCacheData(KeyArray);
		console.log('redis中获取的页面需要用到的缓存数据: '+jsonData);
		console.log(jsonData);
		
		$("[cache]").each(function(){
			var $obj = $(this);
	        var cache = $obj.attr("cache");
	        var data = jsonData[cache];
			if(data != null ){
				if(cache.indexOf('.') == -1){
					$obj.attr("value",data);
				}else{
					var arr =  cache.split('.');
					var val = arr[arr.length-1]
					$obj.attr("value",data[val]);
				}
			}
	    });
	}
}

function getRedisCacheData(key){
	var result;
	if(key != null){
		$.ajax({
			type : "post",
			url : "TransientData/getCacheDataByKeys",
			data : {params:key},
			dataType : "json",
			traditional:true,//通过ajax提交数组时，jquery深度序列化以适应php等,它会自动在所设定的参数后面增加中括号： [] 后台取值不便,traditional为true可防止深度序列化
			async : false,
			success:function(data,flag){
				var obj = data.returnObj;
				if(obj != null && obj != undefined){
					result = obj;
				}
			}
		});
	}
	
	return result;
	
}



function getSerialData(key){
	var result;
	if(key != null){
		$.ajax({
			type : "post",
			url : "TransientData/getSerialData",
			data : {params:key},
			dataType : "json",
			traditional:true,//通过ajax提交数组时，jquery深度序列化以适应php等,它会自动在所设定的参数后面增加中括号： [] 后台取值不便,traditional为true可防止深度序列化
			async : false,
			success:function(data,flag){
				var obj = data.returnObj;
				if(obj != null && obj != undefined){
					result = obj;
				}
			}
		});
	}
	
	return result;
}


function signIn(){

	var username = $("#username").val();
	var password = $("#password").val();
	
//	username = "zhangsan";
//	password = "zs";
	
	if((username != null || username != "") || (password != null || password != "")){
		
		var params = {
				uname: username,
				pword: password
		};

		$.ajax({
			type:'post',
			url:'Login/LoginSys',
			data:params,
			dataType:'json',
			async:true,
			success:function(data){
				var flag = data.flag;
				if(flag == true || flag == "true"){
					
					var pageUrl = data.pageUrl;
					gotoPages(pageUrl);
					
				}else if(flag == false || flag == "false"){
					alert(data.message);
				}
			},
			error : function(data) {
		          alert("登录异常，请稍后再试！");
		        }
		});

	}else{
		alert("用户名或密码为空");
	}
	
}

function gotoPages(pageUrl){
	var appPath=$("body").data('appPath');
	window.location.href=appPath+"/"+pageUrl;
}
function gotoIndex(){
	gotoPages('/index')
	var appPath=$("body").data('appPath');
}