$(document).ready(function () {
    $('.add-to-cart-button').click(function () {
        const itemId = $(this).data('item-id');

        $.ajax({
            type: 'POST',
            url: '/order/add',
            data: JSON.stringify({ id: itemId, amount: 1 }),
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
