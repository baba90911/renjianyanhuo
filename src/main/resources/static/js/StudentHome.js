var ip = "http://10.11.139.79:8080";
var id = $.cookie("name");
$(function() {
	if (!($.cookie("name"))) {
		window.location.href = "login.html";
	}
})
window.onload = function() {
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/students/select_exam",
		Accept: "application/json",
		data: {
			studentumber: id
		},
		success: function(data) {
			console.log(data)
			if (data.data.length < 1) {
				$("#title").text("没有检测到你的考试");
			} else {
				for (var i = 0; i < data.data.length; i++) {
					$("#sel").append("<option>" + data.data[i].schoolName + data.data[i].languageName + "考试准考证" + "（" + data.data[i].examType + "）" + "</option>");
				}
				var x = $("#sel").val();
				$("#title").text(x);
				var tp = x.split("（");
				var t = tp[1].split("）");
				console.log("onload:打印t");
				console.log(t);
				$.ajax({
					type: "GET",
					url: ip + "/v1/api/students/select_categoryByTypeAndNumber",
					Accept: "application/json",
					data: {
						studentNumber: id,
						type: t[0]
					},
					success: function(data) {
                        console.log(data)
						$.ajax({
							type: "GET",
							url: ip + "/v1/api/students/judge_time",
							Accept: "application/json",
							data: {
								category: data.data
							},
							success: function(data) {
                                var ruleName = data.data;
                                console.log(ruleName)
								console.log(data)
								if (data.data == "可以打印") {
                                    console.log("onload:可以打印下的t"+t)
									$.ajax({
										type: "GET",
										url: ip + "/v1/api/students/select_exam_message",
										Accept: "application/json",
										data: {
											number: id,
											type: t[0]
										},
										success: function(data) {
											$("#titlep").show();
											$("#titlep1").hide();
											$("#seone").click(function() {
                                                console.log("onload-seone-bind"+t[0])
												$.cookie("stype",t[0])
												window.location.href = "StudentPrint.html"
											});
										},
										error: function(data) {}
									})
								} else {
									$("#titlep").hide();
									$("#titlep1").show();
									$("#seone").unbind();
									$("#seone").bind("click", function() {
										alert("准考证打印时间已过");
									});
								}
							},
							error: function(data) {}
						})
					},
					error: function(data) {}
				})
			}

		},
		error: function(data) {}

	})
}

function change() {
	var x = $("#sel").val();
	$("#title").text(x);
	var tp = x.split("（");
	var t = tp[1].split("）");
    console.log("change:打印t");
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/students/select_categoryByTypeAndNumber",
		Accept: "application/json",
		data: {
			studentNumber: id,
			type: t[0]
		},
		success: function(data) {
			$.ajax({
				type: "GET",
				url: ip + "/v1/api/students/judge_time",
				Accept: "application/json",
				data: {
					category: data.data
				},
				success: function(data) {
					if (data.data == "可以打印") {
						console.log("change:可以打印下的t"+t)
						$.ajax({
							type: "GET",
							url: ip + "/v1/api/students/select_exam_message",
							Accept: "application/json",
							data: {
								number: id,
								type: t[0]
							},
							success: function(data) {
								$("#titlep").show();
								$("#titlep1").hide();
								$("#seone").click(function() {
									console.log("change-seone-bind"+t[0])
									$.cookie("stype",t[0])
									window.location.href = "StudentPrint.html"
								});
							},
							error: function(data) {
								console.log(2222)
							}
						})
					} else {
						$("#titlep").hide();
						$("#titlep1").show();
						$("#seone").unbind();
						$("#seone").bind("click", function() {
							alert("准考证打印时间已过");
						});
					}
				},
				error: function(data) {}
			})
		},
		error: function(data) {}
	})
}

function logout() {
	$.removeCookie('name');
	window.location.href = "login.html"
}
