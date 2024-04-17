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

function filterUsers() {
    var selectedValue = document.getElementById("filterDropdown").value;
    var userRows = document.querySelectorAll(".user-table tbody tr");

    userRows.forEach(function(row) {
        var role = row.getAttribute("data-role");
        if (selectedValue === "ALL" || role === selectedValue) {
            row.style.display = "table-row";
        } else {
            row.style.display = "none";
        }
    });
}

function changeRowLimit(select){
    var selectedValue = select.value;
    if(selectedValue === "ALL"){
        window.location.href = '/soundbox/sound-box?page=0&size=1000000';
    }else {
        window.location.href = '/soundbox/sound-box?page=0&size=' + selectedValue;
    }
}

function filterByStatus(select){
    var selectedValue = select.value;
    window.location.href = '/soundbox/sound-box?page=0&size=10&soundBoxStatus=' + selectedValue;
}

function filterByBank(select){
    var selectedValue = select.value;
    window.location.href = '/soundbox/sound-box?page=0&size=10&bankCode=' + selectedValue;

}

window.onload = function() {
    var urlParams = new URLSearchParams(window.location.search);
    var bankCode = urlParams.get('bankCode');
    if (bankCode) {
        var bankDropdown = document.getElementById('bankFilterDropdown');
        bankDropdown.value = bankCode;
    }
};