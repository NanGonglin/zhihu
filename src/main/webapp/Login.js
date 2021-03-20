function overtips(content) {
    document.getElementById("tips").placeholder.innerText = content;
}

function outtips() {
    document.getElementById("tips").placeholder;
}

function ajaxLogin() {
    $.ajax({
        url: "./Login",
        type: "post",
        data: $("form").serialize(),
        dataType: "json",
        success: function (res) {
            var msg = res["msg"];
            $("#loginmsg")[0].innerText = msg;
            if ("恭喜您，登录成功"==msg) {
                $("#loginmsg")[0].style.color = "green";
                window.location.href = "./user.html";
            } else {
                $("#loginmsg")[0].style.color = "red";
            }
        },
        error: function () {
            alert("请求失败");
        }
    });
}

function userInfo() {
    $.ajax({
        url:"./GetUserInfo",
        type:"post",
        dataType:"json",
        success:function (res) {
            $("#username")[0].innerText=res["username"];
            $("#nickname")[0].innerText=res["nickname"];
            $("#describe")[0].innerText=res["describe"];
        },
        error:function () {
            alert("请求用户信息失败,请检查");
        }

    })
}

function logout() {
    $.ajax({
        url: "./Logout",
        type: "post",
        dataType: "json",
        success: function (res) {
            alert(res["msg"]);
            window.location.href = "./index.html";
        },
        error: function () {
            alert("请求失败");
        }
    });
}