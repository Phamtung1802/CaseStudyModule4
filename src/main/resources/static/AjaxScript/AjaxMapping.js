$(document).ready(function() {
    $('#createNewUser').submit(function()
    {
        console.log("running");
        event.preventDefault();
        let username=$('#username').val();
        let password=$('#password').val();
        let email=$('#email').val();
        let json = {"username" : username, "password" : password, "email": email};
        console.log("running");
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
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

