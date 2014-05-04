var exec = require('cordova/exec');

var lokasi = {
   location: function(fnSuccess, fnError){
      exec(fnSuccess, fnError, "Lokasi", "location", []);
   },
   address: function(fnSuccess, fnError){
      exec(fnSuccess, fnError, "Lokasi", "address", []);
   },
   addressByLocation: function(fnSuccess, fnError, latitude, longitude){
      exec(fnSuccess, fnError, "Lokasi", "addressByLocation", [latitude, longitude]);
   }
};

module.exports = lokasi;
