var ip = "http://10.11.139.79:8080";
$(function(){
	if (!($.cookie("tname"))) {
		window.location.href = "admin.html";
	}
});
function importexamData() {
	var formData = new FormData();
	formData.append('filename', document.getElementById("sfile").files[0]);
	console.log(formData + "666");
	$.ajax({
		type: "POST",
		url: ip + "/v1/api/admin/import_exam",
		data: formData,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.data.message) {
				alert(data.data.message + "！");
			} else {
				alert("导入成功！")
			}
		},
		error: function() {

			alert("抱歉未导入成功，请检查操作顺序！")
		},
	});
}
function importmodel() {
	console.log(ip + "/v1/api/admin/export");
	window.location.href = ip + "/v1/api/admin/export";
}
function logout() {
	$.removeCookie('tname');
	window.location.href = "admin.html"
}