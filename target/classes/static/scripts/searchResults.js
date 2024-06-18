$(document).ready(function () {
    $('.add-to-cart-button').click(function () {
        const itemId = $(this).data('item-id');

        $.ajax({
            type: 'POST',
            url: '/cart/add',
            data: JSON.stringify({ id: itemId }),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.error('Error saving data', error);
            }
        });
    });
});
