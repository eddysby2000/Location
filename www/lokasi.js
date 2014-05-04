var exec = require('cordova/exec');

var lokasi = {
   list: function(fnSuccess, fnError){
      exec(fnSuccess, fnError, "Lokasi", "location", []);
   },
   open: function(fnSuccess, fnError){
      exec(fnSuccess, fnError, "Lokasi", "address", []);
   },
   close: function(fnSuccess, fnError, latitude, longitude){
      exec(fnSuccess, fnError, "Lokasi", "addressByLocation", [latitude, longitude]);
   }
};

module.exports = lokasi;
