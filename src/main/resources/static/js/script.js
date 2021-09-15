function verify() {
	var password1 = document.forms['form']['password'].value;
	var password2 = document.forms['form']['verify-password'].value;
	if (password1 == null || password1 == "" || password1 != password2) {
		document.getElementById("error").innerHTML = "Passwords Do not Match!! Please Re-Check The Passwords";
		return false;
	}
}