var TWDC = require("../twdc.js")(20, () => {
    TWDC.query('Francis', (data) => {
        console.log('records.length = ' + data.length);
        for (var i = 0; i < data.length; i++) {
            console.log(data[i].img_url); //, data[i].content
        }
    });
});