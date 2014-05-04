var exec = require('cordova/exec');

var lokasi = {
   coordinate: function(fnSuccess, fnError){
      /*
       * fnSuccess: {latitude:xxx, longitude:yyy}
       */
      exec(fnSuccess, fnError, "Lokasi", "coordinate", []);
   },
   address: function(fnSuccess, fnError){
      exec(fnSuccess, fnError, "Lokasi", "address", []);
   },
   addressByCoordinate: function(fnSuccess, fnError, latitude, longitude){
      /*
       * fnSuccess: {address:["","",""], postalCode:"09090"}
       */
      exec(fnSuccess, fnError, "Lokasi", "addressByCoordinate", [latitude, longitude]);
   }
};

module.exports = lokasi;
