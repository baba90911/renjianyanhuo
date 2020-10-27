$(function(){
	if (!($.cookie("tname"))) {
		window.location.href = "admin.html";
	}
});
function one(){
	window.location.href="AdImport.html"
}
function two(){
	window.location.href="AdDel.html"
}
function three(){
	window.location.href="AdSet.html"
}
function four(){
	window.location.href="AdSee.html"
}
function logout(){
	$.removeCookie('tname');
	window.location.href = "admin.html"
}