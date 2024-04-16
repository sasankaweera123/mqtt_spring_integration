$(document).ready(function() {
    $('#searchInput').on('keyup', function() {
        var searchText = $(this).val().toLowerCase();
        // Loop through each table row
        $('.table tbody tr').each(function() {
            var rowData = $(this).text().toLowerCase();
            // Show/hide table row based on search text
            if (rowData.indexOf(searchText) === -1) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
    });

    // Handle click on Bootstrap close icon to show full table
    $('#searchInput').on('input', function() {
        if ($(this).val() === "") {
            $('.table tbody tr').show(); // Show all table rows if input is empty
        }
    });
});
