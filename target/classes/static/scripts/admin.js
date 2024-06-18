$(document).ready(function() {
    const roles = ["ROLE_USER", "ROLE_ADMIN", "ROLE_EMPLOYEE"];

    $('.edit-btn').click(function() {
        var $btn = $(this);
        var $row = $btn.closest('tr');

        $row.find('.editable').each(function() {
            var $cell = $(this);
            var content = $cell.text();
            $cell.data('original-content', content);

            if ($cell.data('role')) {
                var currentRole = $cell.data('role');
                var selectHtml = '<select class="form-control">';
                roles.forEach(function(role) {
                    var selected = (role === currentRole) ? 'selected' : '';
                    selectHtml += '<option value="' + role + '" ' + selected + '>' + role + '</option>';
                });
                selectHtml += '</select>';
                $cell.html(selectHtml);
            } else {
                $cell.html('<input type="text" class="form-control" value="' + content + '">');
            }
        });

        $btn.siblings('.save-btn, .cancel-btn').removeClass('d-none');
        $btn.addClass('d-none');
    });

    $('.cancel-btn').click(function() {
        var $btn = $(this);
        var $row = $btn.closest('tr');

        $row.find('.editable').each(function() {
            var $cell = $(this);
            var originalContent = $cell.data('original-content');
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

        $row.find('.non-editable').each(function() {
            var $cell = $(this);
            data.id = $cell.text();
        });

        $row.find('.editable').each(function(index) {
            var $cell = $(this);
            var newValue;

            if ($cell.find('select').length) {
                newValue = $cell.find('select').val();
                $cell.data('role', newValue);
            } else {
                newValue = $cell.find('input').val();
            }

            $cell.html(newValue);

            if (index === 0) data.username = newValue;
            else if (index === 1) data.email = newValue;
            else if (index === 2) data.role = newValue;
        });

        $.ajax({
            type: 'POST',
            url: '/admin/update',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
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
