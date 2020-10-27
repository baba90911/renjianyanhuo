var ip = "http://10.11.139.79:8080";
function toPage() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    $.cookie("name",username)
    console.log(username+"---"+password)
    $.ajax({
        type: "POST",
        url: ip+"/v1/api/user_login/login",
        async: true,
        Accept: "application/json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": "0",
            "username": username,
            "password": password
        }),
        success: function (data) {
            if (data.code == 0) {
                window.location.href = "StudentHome.html";
                }
            else {
                alert("用户名或密码错误");
            }
        },
        error:function(data){
			alert("未连接到服务器！！！");
        }
    });
}