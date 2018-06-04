$.validator.setDefaults({
    highlight: function (e) {
        $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
    }, success: function (e) {
        e.closest(".form-group").removeClass("has-error").addClass("has-success")
    }, errorElement: "span", errorPlacement: function (e, r) {
        e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
    }, errorClass: "help-block m-b-none", validClass: "help-block m-b-none"
}),

$.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");





$().ready(function () {
    var e = "<i class='fa fa-times-circle'></i> ";
    $("#registForm").validate({
        rules: {
            account: "required",
            name: "required",
            phone: {
                required:true,
                isMobile:true,
            },
            password: {required: !0, minlength: 6},
            confirm_password: {required: !0, minlength: 6, equalTo: "#password"},
        },
        messages: {
            account: e + "请输入你的帐户",
            name: e + "请输入您的名字",
            phone: {
                required: e + "请输入联系电话",
                isMobile: e + "请输入正确的电话号码",
            },
            password: {required: e + "请输入您的密码", minlength: e + "密码必须6个字符以上"},
            confirm_password: {required: e + "请再次输入密码", minlength: e + "密码必须6个字符以上", equalTo: e + "两次输入的密码不一致"},
        }
    })
});
