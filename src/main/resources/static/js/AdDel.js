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
			}
		},
		error: function() {},
	});
	
}
function deletedata() {
	var str = $(".sel").val();
	$.ajax({
		type: "DELETE",
		url: ip + "/v1/api/admin/delete_exam",
		Accept: "application/json",
		data: {
			category: str
		},
		success: function(data) {
			console.log(data);
			if (data.data) {
				alert("删除成功！");
			} else {
				alert("该类别考试下学生为空！");
			}
		},
		error: function() {
			alert("抱歉未删除成功，请检查是否选择正确！")
		},
	});

}
function adddata() {
	var str = $("#examcat").val();
    var examname = $("#examname").val();
    console.log(examname);
	var rul = {
		"category": str,
		"endTime": "2999-10-08T05:19:11.490Z",
        "examName": examname,
		"rule": "1.记住考试时间：考生上午8点45分（下午2点45分）进场，对号入座。上午9点(下午3点)后考生不得进入考场；\n" +
			"2.考生自带2B铅笔、黑色签字笔、橡皮及听力考试用的收音机；\n" +
			"3.不准带书本、通信（手机）、记忆录放工具、电子辞典等进入考场。在考试过程中，若发现带有者视情节轻重按违反考场纪律或考试作弊论；\n" +
			"4.必须带准考证和身份证、学生证，并放课桌上以便监考查对；\n" +
			"5.考生在考试结束前禁止提前退场，考试结束后，须经监考老师收卷清点无误，待监考教师允许后方可离开考场；\n" +
			"6.听力由学校电台发射，电台频率为F.8；\n" +
			"7.特别提醒：四、六级须将试题册背面条形码粘贴到答题卡1的条形码框内，三级必须填涂a或b卷代码，答题要用黑色签字笔，答案不得书写在规定区域以外，专用答题卡只限用2B铅笔涂点；\n" +
			"8.考生必须严格遵守考场纪律，对违反考场规则和不服从考试工作人员管理者，取消考试成绩并按校纪校规处理；\n" +
			"9.考试中错写、漏填考试相关信息均属考试违纪，成绩零分；\n" +
			"10.四六级考试采用多题多卷形式，每位考生考卷都不相同，请不要相信社会上任何关于标准答案的信息。\n",
		"startTime": "2000-10-08T05:19:11.490Z"
	}
	$.ajax({
		type: "POST",
		url: ip + "/v1/api/admin/insert_newRule",
		contentType: "application/json",
		Accept: "application/json",
		data: JSON.stringify(rul),
		success: function(data) {
			console.log(data);
			alert("添加考试类别成功！")
		},
		error: function() {
			alert("未增添到数据库！")
		},
	});
}

function logout() {
	$.removeCookie('tname');
	window.location.href = "admin.html"
}