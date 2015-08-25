
var argscheck = require('cordova/argscheck'),
channel = require('cordova/channel'),
utils = require('cordova/utils'),
exec = require('cordova/exec'),
cordova = require('cordova');

var IPAddress = function () {};

var IPAddressError = function(code, message) {
    this.code = code || null;
    this.message = message || '';
};

IPAddressError.NO_IP_ADDRESS = 0;

IPAddress.prototype.get = function(success,fail) {
    exec(success,fail,"IpAddress",
        "get",[]);
};

module.exports = new IPAddress();