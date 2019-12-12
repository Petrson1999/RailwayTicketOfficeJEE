function toastMesseg(type, message) {
    if (type === true) {
        document.getElementById("errorToast").innerHTML = "";
        document.getElementById("successTost").innerHTML = '<div class="toast shadow-lg mb-2" id=\'toastSuccess\'>\n' +
            '                    <div class="toast-header bg-success p-2">\n' +
            '                        <strong class="mr-auto text-light" >Успешно!\n' +
            '                        </strong>\n' +
            '                        <button type="button" class="mb-1 close" data-dismiss="toastSucc" aria-label="Close">\n' +
            '                            <span aria-hidden="true">&times;</span>\n' +
            '                        </button>\n' +
            '                    </div>\n' +
            '                    <div class="toast-body p-2" id="success"><!--toastr success text-->\n' +
            '                    </div>\n' +
            '                </div>';
        $('#toastSuccess').toast({
            animation: true,
            autohide: true,
            delay: 5000
        });

        document.getElementById("success").innerHTML = message;
        $('.toast').toast('show');
    } else {
        document.getElementById("successTost").innerHTML = "";
        document.getElementById("errorToast").innerHTML = '<div class="toast shadow-lg mb-2" id=\'toastError\'>\n' +
            '                    <div class="toast-header bg-danger p-2">\n' +
            '                        <strong class="mr-auto text-light" >Ошибка!\n' +
            '                        </strong>\n' +
            '                        <button type="button" class="mb-1 close" data-dismiss="toastErr" aria-label="Close">\n' +
            '                            <span aria-hidden="true">&times;</span>\n' +
            '                        </button>\n' +
            '                    </div>\n' +
            '                    <div class="toast-body p-2" id="error"><!--toastr error text-->\n' +
            '                    </div>\n' +
            '                </div>';
        $('#toastError').toast({
            animation: true,
            autohide: true,
            delay: 5000
        });
        document.getElementById("error").innerHTML = message;
        $('.toast').toast('show');
    }
}