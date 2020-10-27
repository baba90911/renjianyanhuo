var ip = "http://10.11.139.79:8080";
var id="";
function toPage() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    $.cookie("tname",username)
    $.ajax({
        type: "POST",
        url: ip+"/v1/api/user_login/login",
        async: true,
        Accept: "application/json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": "1",
            "username": username,
            "password": password,
        }),
        success: function (data) {
            if (data.code == 0) {
                    window.location.href = "AdNav.html?"+username;
            }
            else {
                id=username;  
                alert("用户名或密码错误");
            }
        },
        error:function(data){
            console.log("接口连接失败");
        }
    });
}