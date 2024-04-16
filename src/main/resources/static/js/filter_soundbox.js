function filterSoundBoxes() {
    var selectedValue = document.getElementById("filterDropdown").value;
    var soundBoxRows = document.querySelectorAll(".sound-box-table tbody tr");

    soundBoxRows.forEach(function(row) {
        var status = row.getAttribute("data-status");
        if (selectedValue === "ALL" || status === selectedValue) {
            row.style.display = "table-row";
        } else {
            row.style.display = "none";
        }
    });
}