let url = window.location.pathname;
$(document).ready(function() {
    $("#locales").change(function () {
        let selectedOption = $('#locales').val();
        if (selectedOption !== ''){
            window.location.replace(url + '?lang=' + selectedOption);
        }
    });
});
