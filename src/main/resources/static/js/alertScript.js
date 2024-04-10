/*<![CDATA[*/
/* Function to display alert based on message */
function displayAlert(message) {
    var alertDiv = document.getElementById('alert-container');
    if (message.startsWith("Successfully")) {
        alertDiv.innerHTML = '<div class="alert alert-success alert-dismissible d-flex align-items-center" role="alert">' +
            '<svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:">' +
            '<use xlink:href="#check-circle-fill"/></svg>' +
            '<div>' + message + '</div>' +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            '</div>';
    } else if (message.startsWith("Failed")) {
        alertDiv.innerHTML = '<div class="alert alert-danger alert-dismissible d-flex align-items-center" role="alert">' +
            '<svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:">' +
            '<use xlink:href="#exclamation-triangle-fill"/></svg>' +
            '<div>' + message + '</div>' +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            '</div>';
    } else if(message.startsWith("Warning")){
        alertDiv.innerHTML = '<div class="alert alert-warning alert-dismissible d-flex align-items-center" role="alert">' +
            '<svg class="bi flex-shrink-0 me-2" role="img" aria-label="Warning:">' +
            '<use xlink:href="#exclamation-triangle-fill"/></svg>' +
            '<div>' + message + '</div>' +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            '</div>';
    }else if(message.startsWith("Info")){
        alertDiv.innerHTML = '<div class="alert alert-info alert-dismissible d-flex align-items-center" role="alert">' +
            '<svg class="bi flex-shrink-0 me-2" role="img" aria-label="Info:">' +
            '<use xlink:href="#info-fill"/></svg>' +
            '<div>' + message + '</div>' +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            '</div>';
    } else {
        alertDiv.innerHTML = '<div class="alert alert-secondary alert-dismissible d-flex align-items-center" role="alert">' +
            '<svg class="bi flex-shrink-0 me-2" role="img" aria-label="Secondary:">' +
            '<use xlink:href="#info-fill"/></svg>' +
            '<div>' + message + '</div>' +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
            '</div>';
    }
}
/*]]>*/
