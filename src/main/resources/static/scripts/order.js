$(document).ready(function() {
    // Блокировка от частой отправуи запросов при изменении поля
    function debounce(func, delay) {
        let debounceTimer;
        return function() {
            const context = this;
            const args = arguments;
            clearTimeout(debounceTimer);
            debounceTimer = setTimeout(() => func.apply(context, args), delay);
        };
    }

    // Обработчик имзегения состояния поля ввода данных
    function handleInputChange(event) {
        const input = $(event.target);
        const amount = input.val();
        const id = input.data('id');  // Получение идентификатора позиции заказа

        // Проверка, что введено число
        if (!/^\d*$/.test(amount)) {
            alert('Вводите только число в количество!');
            return;
        }

        const row = input.closest('tr');
        const inStockValue = row.find('.td-in-stock').val();
        const inStock = parseInt(inStockValue, 10);
        if (inStock < amount){
            alert('Вводите количество меньшее доступного!');
            return;
        }

        // Send the POST request
        $.ajax({
            url: '/order/update',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ id: id, amount: amount }),
            success: function(response) {
                console.log('Success:', response);
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });

        updateTotal();
    }

    // Функция для рассчёта суммы заказаза
    function updateTotal() {
        let total = 0;
        $('tr').each(function() {
            const price = $(this).find('.td-price').val();
            const amount = $(this).find('.td-amount').val();
            if (price && amount) {
                total += parseFloat(price) * parseInt(amount, 10);
            }
        });
        $('#total-value').text(total);
    }

    updateTotal();

    // Подключение обработчиков
    $('.td-amount').on('input', debounce(handleInputChange, 300));

    $('.delete-btn').click(function () {
        const itemId = $(this).data('id');

        $.ajax({
            type: 'POST',
            url: '/order/delete',
            data: JSON.stringify({ id: itemId, amount: 0 }),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
            },
            error: function(error) {
                console.error('Error saving data', error);
            }
        });

        const row = $(this).closest('tr');
        row.remove();
        updateTotal();
    });
});
