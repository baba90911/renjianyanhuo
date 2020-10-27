var ip = "http://10.11.139.79:8080";
var id = $.cookie("name");
var tp = $.cookie("stype")
$(function() {
	if (!($.cookie("name"))) {
		window.location.href = "login.html";
	}
})
//渲染数据
window.onload = function() {
	console.log(id+"-id and tp-"+tp);
	$.ajax({
		type: "GET",
		url: ip + "/v1/api/students/select_exam_message",
		Accept: "application/json",
		data: {
			number: id,
			type: tp
		},
		success: function(data) {
			console.log("examMsg: "+data);
			if(typeof data.data=="undefined"){
				console.log("服务器返回错误：照片不存在，请联系教务处高老师！");
			}
			else{console.log("照片存在");}
			var n = data.data.examClassroom2;
			var n1 = data.data.examTime2;
			if (!n) {
				$("#ea2").hide();
			} else {
				$("#eatime2").text("考试时间：" + n1);
				$("#earoom2").text("考试教室：" + n);
			}
			var m = data.data.examClassroom;
			var m1 = data.data.examTime;
			if (!m) {
				$("#ea").hide();
			} else {
				$("#eatime").text("考试时间：" + m1);
				$("#earoom").text("考试教室：" + m);
			}
			var sr = data.data.url;
			sr = sr.replace("Http://10.11.139.79:8080/", "./img/");
			$("#stimg").attr("src", sr);
			console.log(sr)
			console.log("考试标题："+data.data.examName)
            // $("#title").text(data.data.schoolName + data.data.languageName + "考试准考证" + "（" + data.data.examType + "）");
            $("#title").text(data.data.examName);
            $("#scname").text("学校名称：" + data.data.schoolName);
            $("#sccode").text("学校代码：" + data.data.shoolCode);
            $("#stname").text("考生信息：" + data.data.studentName + " " + data.data.studentNumber+" " + data.data.className);
            $("#stclass").text("班级名称：" + data.data.className);
            $("#eanum").text("考场号：" + data.data.roomNumber + "\n" + "座位号：" + data.data.seatNumber);
            $("#eacode").text("准考证号：" + data.data.examNumber);
            $.cookie("en",data.data.examNumber)
            $("#laname").text("语种名称：" + data.data.languageName);
            $("#eatitle").text("考 试 须 知");// data.data.languageName +
            for (var i = 0; i < data.data.examRule.length; i++) {
                $("#earule").append(data.data.examRule[i] + "</br>");
            }
		},
		error: function(data) {}
	})
}

//Print
function preview(oper) {
	$.ajax({
		type:"PUT",
		url:ip+"/v1/api/admin/update_print",
		Accept:"application/json",
		data:{
			examNumber:$.cookie("en")
		},
		success:function(data){},
		error:function(data){}
	})
	if (oper < 10) {
		bdhtml = window.document.body.innerHTML; //获取当前页的html代码
		sprnstr = "<!--startprint" + oper + "-->"; //设置打印开始区域
		eprnstr = "<!--endprint" + oper + "-->"; //设置打印结束区域
		prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 20); //从开始代码向后取html

		prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr)); //从结束代码向前取html
		window.document.body.innerHTML = prnhtml;
		window.print();
		window.document.body.innerHTML = bdhtml;
	} else {
		window.print();
	}
}

//All
function logout() {
	$.removeCookie('name');
	window.location.href = "login.html"
}
