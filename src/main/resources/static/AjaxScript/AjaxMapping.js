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
