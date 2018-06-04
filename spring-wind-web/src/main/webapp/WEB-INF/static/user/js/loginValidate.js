var num = 0;
$("#butSub").click(function () {
    if ($("#account").val().length == 0 || $("#password").val().length == 0) {
        if (num == 0) {
            Messenger.options = {
                extraClasses: 'messenger-fixed messenger-on-top messenger-on-right',
                theme: 'future'
            }
            $.globalMessenger().post({
                message:"提示：用户名和密码不能为空",
                type: "error",
                showCloseButton: true
            })
            num++;
        }
    } else {
        $("#loginForm").submit()
    }
})
