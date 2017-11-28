(function () {
    'use strict';
    var jsdom = require("jsdom");
    var {JSDOM} = jsdom;
    var {window} = new JSDOM('<!DOCTYPE html><html></html>');
    var $ = require('jquery')(window);

    var xml = require('./xml.js')();
    var {Record} = require('./xml.js')();
    var {Record_Query} = require('../config.js').DATA;
    var cf = require('../config.js').DATA;

    module.exports = function (limit, callback) {
        var methods = {};
        var dataset = [];

        methods.refresh = function (url, callback) {
            xml.fetch(url, (data) => {
                var xml_records = $(data).find("record");
                console.log("processing " + xml_records.length + " records...");
                xml_records.each(function () {
                    var record = new Record(
                            $(this).find("header"),
                            $(this).find("metadata"));
                    if (record.link && cf.FILETYPES.indexOf(record.filetype) > -1) {
                        dataset.push(record);
                        console.log(dataset.length + ". " + record.title);
                    }
                });
                var resumptionToken = $(data).find('resumptionToken').text();
                if (resumptionToken)
                    methods.refresh(cf.TWDC.URL_TOKEN + resumptionToken, callback);
                else {
                    console.log("TWDC dataset initialization completed. Total records fetched: "
                            + dataset.length);
                    callback();
                }
            });
        };

        methods.query = function (text, callback) {
            var records = [];
            var n = limit;
            var query_str = (text === '台中') ? '臺中' : text;
            for (var i = 0; i < dataset.length; i++) {
                var result = dataset[i].contains(query_str);
                if (result.length > 0) {
                    records.push(new Record_Query(dataset[i].link, result));
                }
            }
            if (records.length > n) {
                var records_selected = [];
                do {
                    var selected = Math.floor(Math.random() * records.length);
                    if (records[selected]) {
                        records_selected.push(records[selected]);
                        records[selected] = null;
                    }
                } while (records_selected.length < n);
                callback(records_selected);
            } else
                callback(records);
        };
        console.log("Preloading data from TWDC...");
        methods.refresh(cf.TWDC.URL_BASE, callback);
        return methods;
    };
}());


