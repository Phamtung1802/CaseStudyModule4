$('#createNewComment').submit(function()
{
    console.log('hello');
    event.preventDefault();
    let text=$('#commentContent').val();
    let url=$('#commentUrl').val();

    let json={
        "text":text,
        "url":url
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url : $('#createNewComment').action,
        type : "POST",
        data: JSON.stringify(json),
        success(result){
            location.reload();
        }
    });
});