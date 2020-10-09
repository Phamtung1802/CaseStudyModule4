$(document).ready(function() {
    $('#createNewUser').submit(function()
    {
        event.preventDefault();
        let username=$('#username').val();
        let password=$('#password').val();
        let email=$('#email').val();
        let json = {"username" : username, "password" : password, "email": email};
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            filename:'create',
            url : 'create',
            dataType : 'json',
            type : "POST",
            data: JSON.stringify(json),
            success : function (result){
                alert("Success!!");
            },
            error: function () {
                alert('Error!')
            }
        });
        event.preventDefault();
    });
});
//---------------------------------------Create Post-------------------------------------------------------------------------------------//
$(document).ready(function() {
    $('#createPost').click(function()
    {
        event.preventDefault();
        $.ajax({
            headers: {
            },
            url : 'user/createPost',
            type : "GET",
            success : function (result){
                $('#mainbody').html(result);
            },
            error: function () {
                alert('Error!')
            }
        });
        event.preventDefault();
    });
});
//---------------------------------------Forum-------------------------------------------------------------------------------------//
$(document).ready(function() {
    $('#forum').click(function()
    {
        event.preventDefault();
        $.ajax({
            headers: {
            },
            url : '/forum',
            type : "GET",
            success : function (result){
                $('#mainbody').html(result);
            },
            error: function () {
                alert('Error!')
            }
        });
        event.preventDefault();
    });
});
//---------------------------------------create User view-------------------------------------------------------------------------------------//
$(document).ready(function() {
    $('#signUp').click(function()
    {
        event.preventDefault();
        $.ajax({
            headers: {
            },
            url : '/create',
            type : "GET",
            success : function (result){
                $('#mainbody').html(result);
            },
            error: function () {
                alert('Error!')
            }
        });
        event.preventDefault();
    });
});
//---------------------------------------create User view-------------------------------------------------------------------------------------//

$(document).ready(function() {
    $('#search').submit(function()
    {
        event.preventDefault();
        let query=$('#searchBar').val();
        let json={
            "postName":query
        }
        $.ajax({
            headers: {
                'Content-Type': 'application/json'
            },
            url : '/search',
            type : "POST",
            data: JSON.stringify(json),
            success : function (result){
                $('#mainbody').html(result);
                console.log("success");
                console.log(result);
            },
            error: function (result) {
                $('#mainbody').html(result);
                console.log("error!!!");
            }
        });
        event.preventDefault();
    });
});
