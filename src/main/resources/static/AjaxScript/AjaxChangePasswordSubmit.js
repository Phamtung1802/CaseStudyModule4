$(document).ready(function() {
    $('#changePasswordSubmit').submit(function()
    {
        event.preventDefault();
        let data = new FormData();
        if ($('#newPasswordConfirm').val()!=$('#newPassword').val()){
            alert('nhap lai password moi');
            return;
        }
        data.append('oldPassword',$('#oldPassword').val());
        data.append('newPassword',$('#newPassword').val());
        $.ajax({
            headers: {
                contentType:'text/plain'
            },
            url:'/changePassword',
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            method: 'PATCH',
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
