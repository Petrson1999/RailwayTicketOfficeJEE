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
    $('#departure-time').datetimepicker({
        icons:
            {
                up: 'icon icon-up',
                down: 'icon icon-down'
            },
        format: 'LT'
    });
});

$(function () {
    $('#arrival-time').datetimepicker({
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

$(function () {
    $('#date-departure').datetimepicker({
        sideBySide: true,
        format: 'DD-MM-YYYY'
    });
});

$(function () {
    $('#date-arrival').datetimepicker({
        sideBySide: true,
        format: 'DD-MM-YYYY'
    });
});