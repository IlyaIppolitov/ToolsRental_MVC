$(document).ready(function() {
    $('.edit-btn').click(function() {
        var $btn = $(this);
        var $row = $btn.closest('tr');

        $row.find('.editable').each(function() {
            var $cell = $(this);
            var content = $cell.text();
            $cell.html('<input type="text" class="form-control" value="' + content + '">');
        });

        $btn.siblings('.save-btn, .cancel-btn').removeClass('d-none');
        $btn.addClass('d-none');
    });

    $('.cancel-btn').click(function() {
        var $btn = $(this);
        var $row = $btn.closest('tr');

        $row.find('.editable').each(function() {
            var $cell = $(this);
            var $input = $cell.find('input');
            var originalContent = $input.val();
            $cell.html(originalContent);
        });

        $btn.siblings('.edit-btn').removeClass('d-none');
        $btn.siblings('.save-btn').addClass('d-none');
        $btn.addClass('d-none');
    });

    $('.save-btn').click(function() {
        var $btn = $(this);
        var $row = $btn.closest('tr');
        var data = {};

        $row.find('.editable').each(function(index) {
            var $cell = $(this);
            var $input = $cell.find('input');
            var newValue = $input.val();
            $cell.html(newValue);

            if (index === 0) data.id = newValue;
            else if (index === 1) data.username = newValue;
            else if (index === 2) data.email = newValue;
            else if (index === 3) data.role = newValue;
        });

        $.ajax({
            type: 'POST',
            url: '/your-server-endpoint',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                console.log('Data saved successfully');
            },
            error: function(error) {
                console.error('Error saving data', error);
            }
        });

        $btn.siblings('.edit-btn').removeClass('d-none');
        $btn.siblings('.cancel-btn').addClass('d-none');
        $btn.addClass('d-none');
    });
});
