/**
 * Created by goodyi on 7-29-029.
 * All Rights Reversed.
 **/
$(function () {
    $('#addChoice').on('click', function () {
        $("#addQue").append(
            "<div class='input-field col s12'> <input name='choices' id='select' type='text' class='validate sel''/> <label for='select'>选项：</label> </div>");
    });
    $("#submitQue").on('click', function () {
        var arr = [];

        var submitForm = $("#submitQuestion");

        $('.sel').each(function (i) {
            var choiceVal = $(this).val();
            arr.push(choiceVal);
        });
        $.ajax({
            url: submitForm.attr('action'),
            data: JSON.stringify({
                "department": $("select option:selected").val(),
                "question": $("textarea").val(),
                "choices": arr
            }),
            type: "POST",
            dataType: "json",
            headers: {
                'X-CSRF-TOKEN': submitForm.find(">input[name=_csrf]").val()
            },
            contentType: "application/json;charset=UTF-8",
            error: function () {
                alert('error');
            },
            success: function (data) {
                alert(data.status);
            }
        });
        return false;
    });
});

//{"question": "Hello World", "choices": ["Apple", "Banana","Grape"], "department": "计算机部"}