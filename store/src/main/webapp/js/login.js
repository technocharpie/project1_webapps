/*--------------------
    Web Apps UF Fall 2018, Dr. Brown
    copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

const dc = new DataController();

const username_check = function(user) {
	if (user != "")
	{
		const username = JSON.parse(user).username;
		setCookie("ONLINE-STORE", username, 1);
		console.log(getCookie("ONLINE-STORE"));
		//window.location.href = "http://charpie.info/";
	}
	else
		window.alert("No such username");
}

const new_user_check = function(username) {
	if (username != "")
	{
		setCookie("ONLINE-STORE", username, 1);
		console.log(getCookie("ONLINE-STORE"));
		//window.location.href = "http://charpie.info/";
	}
	else
		window.alert("Invalid input");
}

const setCookie = function(cname, cvalue, exdays) {
    const date = new Date(); //Create an date object
    date.setTime(date.getTime() + (exdays*1000*60*60*24));
    const expires = "expires=" + date.toGMTString();
    window.document.cookie = cname+"="+cvalue+"; "+expires;
}

const getCookie = function(cname) {
    const name = cname + "="; 
    const cArr = window.document.cookie.split(';'); 
     
   	for(var i = 0; i < cArr.length; i++) {
        var cookie = cArr[i].trim();
        if (cookie.indexOf(name) == 0) 
            return cookie.substring(name.length, cookie.length);
    }
     
    return "";
}

$("#login").submit(function(event) {
	/* stop form from submitting normally */
	event.preventDefault();

	/* get some values from elements on the page: */
	var $form = $(this),
	    username = $form.find('input[name="username"]').val();

	dc.username_check(username, username_check);

});

$("#signup").submit(function(event) {
	/* stop form from submitting normally */
	event.preventDefault();

	/* get some values from elements on the page: */
	var $form = $(this),
	    username = $form.find('input[name="username"]').val(),
	    fname    = $form.find('input[name="fname"]').val(),
	    lname    = $form.find('input[name="lname"]').val(),
	    email    = $form.find('input[name="email"]').val();

	dc.new_user_check(username, fname, lname, email, new_user_check);

});