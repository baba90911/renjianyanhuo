var ip = "http://10.11.139.79:8080";
$(function(){
	if (!($.cookie("tname"))) {
		window.location.href = "admin.html";
	}
});
window.onload = function(){
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/admin/get_all_categories",
		data: {},
		success: function(data) {
			var type = data.data;
			for (var i = 0; i < data.data.length; i++) {
				$(".sel").append("<option>" + data.data[i] + "</option>");
				$(".sel2").append("<option>" + data.data[i] + "</option>");
			}
		},
		error: function() {},
	});
	
}
function showrule() {
	var str = $(".sel").val();
	$.ajax({
		type: "PUT",
		url: ip + "/v1/api/admin/select_rule",
		Accept: "application/json",
		data: {
			category: str
		},
		success: function(data) {
			$(".tet").text(data);

		},
		error: function() {
			alert("未成功连接到数据库！");
		},
	});
}
function modify() {
	var tet = $(".tet").val();
	var str = $(".sel").val();
	$.ajax({
		type: "PUT",
		url: ip + "/v1/api/admin/update_rule",
		Accept: "application/json",
		data: {
			rule: tet,
			category: str
		},
		success: function(data) {
			alert("修改成功！");
		},
		error: function() {
			alert("抱歉未修改成功，请检查是否选择正确！")
		},
	});
}
function set() {
	var str = $(".sel2").val();
	var ti = $("#s1").val();
	var ti1 = $("#e1").val();
	$.ajax({
		type: "PUT",
		url: ip + "/v1/api/admin/update_startTime",
		Accept: "application/json",
		data: {
			category: str,
			startTime: ti
		},
		success: function(data) {
			$.ajax({
				type: "PUT",
				url: ip + "/v1/api/admin/update_endTime",
				Accept: "application/json",
				data: {
					category: str,
					endTime: ti1
				},
				success: function(data) {
					console.log(data);
					if (data.data == 1) {
						alert("设置成功！");
					} else {
						alert("未设置成功，请选择时间和类别！");
					}
				},
				error: function() {
					alert("抱歉未设置成功，请检查是否选择正确！")
				},
			});
		},
		error: function() {
			alert("抱歉未设置成功，请检查是否选择正确！")
		},
	});
}
function logout() {
	$.removeCookie('tname');
	window.location.href = "admin.html"
}