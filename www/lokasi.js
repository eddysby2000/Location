var exec = require('cordova/exec');

var lokasi = {
   location: function(fnSuccess, fnError){
      /*
       * fnSuccess:{latitude:xxx, longitude:yyy}
       */
      exec(fnSuccess, fnError, "Lokasi", "location", []);
   },
   address: function(fnSuccess, fnError){
      exec(fnSuccess, fnError, "Lokasi", "address", []);
   },
   addressByLocation: function(fnSuccess, fnError, latitude, longitude){
      /*
       * fnSuccess:{address:["","",""], postalCode:"09090"}
       */
      exec(fnSuccess, fnError, "Lokasi", "addressByLocation", [latitude, longitude]);
   }
};

module.exports = lokasi;
