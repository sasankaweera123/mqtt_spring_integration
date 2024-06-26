function createDonutChart(labels, data) {
    var chartData = {
        labels: labels,
        datasets: [{
            data: data,
            backgroundColor: [
                '#FF6384',
                '#36A2EB',
                '#FFCE56',
                '#355E3B',
                '#C70039',
                '#FF5733'
            ],
            hoverBackgroundColor: [
                '#FF6384',
                '#36A2EB',
                '#FFCE56',
                '#355E3B',
                '#C70039',
                '#FF5733'
            ]
        }]
    };

    var ctx = document.getElementById("myDonutChart").getContext('2d');
    var myDonutChart = new Chart(ctx, {
        type: 'doughnut',
        data: chartData,
        options: {
            responsive: true,
            maintainAspectRatio: false
        }
    });
}
