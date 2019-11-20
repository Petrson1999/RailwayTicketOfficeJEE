$(function () {
    $('#datetimepicker1').datetimepicker({
        icons:
            {
                up: 'icon icon-up',
                down: 'icon icon-down'
            },
        format: 'LT'
    });
});

$(function () {
    $('#datetimepicker').datetimepicker({
        sideBySide: true,
        format: 'DD-MM-YYYY'
    });
});