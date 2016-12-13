var $p = jQuery.noConflict();
var flag1 = "0";
$p(function() {
	$p("#userName").blur(function() {
		var user = $p("#userName").val();
		var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
		var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
		if (user == null || user == "") {
			$p("#user1").show();
			$p("#user2").hide();
			$p("#user3").hide();
			$p("#user4").hide();
		} else if (!user.match(a) || user.length != 11) {
			$p("#user2").show();
			$p("#user1").hide();
			$p("#user3").hide();
			$p("#user4").hide();
		} else {
			//用户名是否存在
			$p.ajax({
				async : false,
				url : $p("#basePathId").val() + "/user/findphone",
				type : "GET",
				data : "phoneNum="+user,
				dataType : "json",
				success : function(data) {
					var member = eval("(" + data + ")");
					var result = member.result;
					if (result == "1") {
						flag1 = "1";
						$p("#user3").show();
						$p("#user1").hide();
						$p("#user2").hide();
						$p("#user4").hide();
					}
					if (result == "0") {
						flag1 = "2";
						$p("#user4").show();
						$p("#user1").hide();
						$p("#user2").hide();
						$p("#user3").hide();
					}
				}
			});
		}
	})
	$p("#password").blur(function() {
		var pwd = $p("#password").val();
		if (pwd == null || pwd == "") {
			$p("#pwd1").show();
			$p("#pwd2").hide();
			$p("#pwd3").hide();
		} else if (pwd.length < 6 || pwd.length > 16) {
			$p("#pwd2").show();
			$p("#pwd1").hide();
			$p("#pwd3").hide();
		} else {
			$p("#pwd3").show();
			$p("#pwd1").hide();
			$p("#pwd2").hide();
		}
	})
	$p("#confirmpwd").blur(function() {
		var pwd = $p("#password").val();
		var cpwd = $p("#confirmpwd").val();
		if (cpwd == "" || cpwd == null) {
			$p("#cpwd1").show();
			$p("#cpwd2").hide();
			$p("#cpwd2").hide();
		} else if (pwd != cpwd) {
			$p("#cpwd2").show();
			$p("#cpwd1").hide();
			$p("#cpwd3").hide();
		} else {
			$p("#cpwd3").show();
			$p("#cpwd1").hide();
			$p("#cpwd2").hide();
		}

	})
	$p("#yanzhengma").blur(function() {
		var yzm = $p("#yanzhengma").val();
		if (yzm == "" || yzm == null) {
			$p("#yzm1").show();
			$p("#yzm2").hide();
			$p("#yzm3").hide();
		} else if (yzm.length != 4) {
			$p("#yzm2").show();
			$p("#yzm1").hide();
			$p("#yzm3").hide();
		} else {
			$p("#yzm3").show();
			$p("#yzm1").hide();
			$p("#yzm2").hide();
		}
	})

})

function check() {
	var user = $p("#userName").val();
	var a=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
	if (user == "") {
		$p("#user1").show();
		$p("#user2").hide();
		$p("#user3").hide();
		$p("#user4").hide();
		return false;
	} else if (user.length!=11 || !user.match(a)) {
		$p("#user2").show();
		$p("#user1").hide();
		$p("#user3").hide();
		$p("#user4").hide();
		return false;
	}
	if (flag1 != "2") {
		return false;
	}
	var pwd = $p("#password").val();
	if (pwd == "") {
		$p("#pwd1").show();
		$p("#pwd2").hide();
		$p("#pwd3").hide();
		return false;
	} else if (pwd.length < 6 || pwd.length > 16) {
		$p("#pwd2").show();
		$p("#pwd1").hide();
		$p("#pwd3").hide();
		return false;
	}
	if ($p("#confirmpwd").val() == "") {
		$p("#cpwd1").show();
		$p("#cpwd2").hide();
		$p("#cpwd2").hide();
		return false;
	}
	var cpwd = $p("#confirmpwd").val();
	if (pwd != cpwd) {
		$p("#cpwd2").show();
		$p("#cpwd1").hide();
		$p("#cpwd3").hide();
		return false;
	}
	var result = document.getElementById("checkbox").checked;
	if (!result) {
		$p("#xy").show();
		return false;
	} else {
		$p("#xy").hide();
	}
	var yzm = $p("#yanzhengma").val();
	if (yzm == "" || yzm == null) {
		$p("#yzm1").show();
		$p("#yzm2").hide();
		$p("#yzm3").hide();
		return false;
	}
	return true;
}
function tijiao() {
	if (check()) {
		var user = $p("#userName").val();
		var path = document.getElementById("basePathId").value;
		document.getElementById("item").setAttribute("disabled", "disabled");
		var form = document.getElementById("register");
		form.action = path + "/user/reg1?phoneNum="+user;
		form.submit();
	}

}
$p(window).load(function() {
	setTimeout("$p('#error').hide()", 3000);
});