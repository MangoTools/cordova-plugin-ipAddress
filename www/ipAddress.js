
var cordova = require('cordova');

var IpAddress = function () {};

var IpAddressError = function(code, message) {
    this.code = code || null;
    this.message = message || '';
};

IpAddressError.NO_IP_ADDRESS = 0;

IpAddress.prototype.get = function(success,fail) {
    return cordova.exec(success,fail,"IpAddress","get",[]);
};

module.exports = new IpAddress();