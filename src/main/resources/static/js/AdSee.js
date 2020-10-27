var ip = "http://10.11.139.79:8080";
$(function(){
	if (!($.cookie("tname"))) {
		window.location.href = "admin.html";
	}
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/admin/get_all_categories",
		data: {},
		success: function(data) {
			var type = data.data;
			for (var i = 0; i < data.data.length; i++) {
				$(".sel1").append("<option>" + data.data[i] + "</option>");
			}
	
		},
		error: function() {},
	});
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/admin/select_type",
		data: {},
		success: function(data) {
			var type = data.data;
			for (var i = 0; i < data.data.length; i++) {
				$(".sel11").append("<option>" + data.data[i] + "</option>");
			}
		},
		error: function() {},
	});
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/admin/get_allData",
		data: {},
		success: function(data) {
			$(".a1").text(data.data.all + "人");
			$(".a11").text(data.data.print + "人");
			$(".a111").text(data.data.percent);
			console.log(123)
		},
		error: function() {
			console.log(123)
		},
	});
})
function change1() {;
	var str = $(".sel1").val();
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/admin/get_categoryData",
		data: {
			category: str
		},
		success: function(data) {
			$(".a2").text(data.data.all + "人");
			$(".a22").text(data.data.print + "人");
			$(".a222").text(data.data.percent);
		},
		error: function() {},
	});
}

function import1() {
	var str = $(".sel1").val();
	window.location.href = ip + "/v1/api/admin/export_byCategory?exam_category=" + str;
}

function change11() {;
	var str = $(".sel11").val();
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/admin/get_typeData",
		data: {
			type: str
		},
		success: function(data) {
			$(".a3").text(data.data.all + "人");
			$(".a33").text(data.data.print + "人");
			$(".a333").text(data.data.percent);
		},
		error: function() {},
	});
}
function import11() {
	var str = $(".sel11").val();
	window.location.href = ip + "/v1/api/admin/export_byType?exam_type=" + str;
}
function logout() {
	$.removeCookie('tname');
	window.location.href = "admin.html"
}