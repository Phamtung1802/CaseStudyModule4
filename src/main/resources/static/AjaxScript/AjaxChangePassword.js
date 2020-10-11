//---------------------------------------Change Password View-------------------------------------------------------------------------------------//

$(document).ready(function() {
    $('#changePasswordLink').click(function()
    {
        event.preventDefault();
        $.ajax({
            headers: {
            },
            url : '/changePassword',
            type : "GET",
            success : function (result){
                $('#mainbody').html(result);
                console.log("success");
                console.log(result);
            },
            error: function (result) {
                console.log("error!!!");
            }
        });
        event.preventDefault();
    });
});
