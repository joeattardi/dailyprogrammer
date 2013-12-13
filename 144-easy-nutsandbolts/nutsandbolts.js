fs = require('fs');

var priceData = fs.readFileSync(process.argv[2], 'utf8').split('\r\n');

var numItems = parseInt(priceData[0]);
var currPriceData = priceData.slice(1, numItems + 1);
var correctPriceData = priceData.slice(numItems + 1);

var currPrices = [];

currPriceData.forEach(function(item) {
    var data = item.split(' ');
    currPrices[data[0]] = parseInt(data[1]);
});

correctPriceData.forEach(function(item) {    
    var data = item.split(' ');
    var difference = parseInt(data[1]) - currPrices[data[0]];
    if (Math.abs(difference) > 0) {
        console.log(data[0] + ' ' + (difference > 0 ? '+' : '') + difference);
    }
});
