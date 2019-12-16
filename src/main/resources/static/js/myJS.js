$('#country-select').change(function() {
    const value = $(this).val();
    switch (value) {
        case 'usa':
            $('#usa-select-block').removeAttr('hidden');
            $('#canada-select-block').attr('hidden', true);
            break;
        case 'canada':
            $('#canada-select-block').removeAttr('hidden');
            $('#usa-select-block').attr('hidden', true);
            break;
    }
})