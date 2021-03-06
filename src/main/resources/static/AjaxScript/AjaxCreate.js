$(document).ready(function() {
    $('#createNewPost').submit(function()
    {
        event.preventDefault();
        let postName=$('#postTitle').val();
        let description=$('#postContent').val();
        let json={
            'postName':postName,
            'description':description
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url : 'user/createPost',
            dataType : 'json',
            type : "POST",
            data: JSON.stringify(json),
            success : function (result){
                location.reload();
            },
            error: function () {
                location.reload();
            }
        });
        event.preventDefault();
    });
});
