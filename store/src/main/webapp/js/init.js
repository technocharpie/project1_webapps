/*--------------------
    Web Apps UF Fall 2018, Dr. Brown
    copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

(function() {
	var dc = new DataController(true); //true ==> use local API

	/**Define what to do with the album titles list */
	var populateAlbumList = function (albums){
		console.log("albums", albums);
		var albumDiv = document.getElementById("albumList");
		var titleProp = "title";
		if(albums.albums != null){
			albums = albums.albums.album;
			titleProp = "name";
		}

		/**Clear existing views */
		removeChildNodes(document.getElementById("albumDetails"));
		removeChildNodes(albumDiv);

		
		albums.forEach(function(album){
			/**Define the album "tile" */			
			var tileNode = createTileNode(album[titleProp], "album");
			tileNode.addEventListener("click", function(){
						setupDetailView(this, album.id);
						});
			albumDiv.appendChild(tileNode);
		});
	}
	
	var setupDetailView = function (albumDiv, albumId){
		var detailDiv = document.getElementById("albumDetails");
		var albumDivCopy = albumDiv.cloneNode(true);
		removeChildNodes(detailDiv);
		detailDiv.appendChild(albumDivCopy);
		dc.getAlbumById(albumId, showAlbumDetails); 
		
	}
	
	var showAlbumDetails = function (album){
		var tracks = album.tracks;
		console.log("tracks", tracks);
		var detailDiv = document.getElementById("albumDetails");
		/**
		Here you can try to call LastFM's album.getInfo() method, check format of results and list details
		*/
		

		tracks.forEach(function(track){
			/**Define the track "tile" */			
			var tileNode = createTileNode(track.title, "track");
			detailDiv.appendChild(tileNode);
		});

		/**The following loops through all properties of the album json object. 
		This can be useful if we're working with external APIs and not always aware of property
		names and types.**/
		/**But since we know what data to expect from our REST API calls, we can customize the view
		for (prop in album){
			if(Array.isArray(album[prop])){
			 createPropTextNode(prop+": ");
			 var arr = album[prop];
			 for(var i=0; i<arr.length; i++){
				obj= arr[i];
				console.log("obj",obj );				
				for(objProp in obj){
					createPropTextNode(objProp+": "+obj[objProp]);
				}
			 }				
			}else{
				createPropTextNode(prop+": "+album[prop]);
			}
		}
		*/
		
	}
	
	var createTileNode = function(title, classType){
		var tileNode = document.createElement("div");
		var titleNode = document.createTextNode(title);
		var albumNode = document.createElement("div");
		albumNode.className = classType+"Icon";
		tileNode.className = classType+"TileDiv";
		tileNode.appendChild(albumNode);
		tileNode.appendChild(titleNode);
		return tileNode;
	}
	
	var createPropTextNode = function(str){
		var detailDiv = document.getElementById("albumDetails");
		var propNode = document.createElement("div");
		var textNode = document.createTextNode(str);
		propNode.appendChild(textNode);
		detailDiv.appendChild(propNode);					
	}

	/**Set event listener for radio buttons*/
	var apiRadioButtons = document.getElementsByName("api");
	apiRadioButtons.forEach(function(button){
		button.addEventListener("click", function(){
			var value = button.value;
			if(value === "firstParty"){//use my API
				dc.isLocalApi = true;				
			}else{ // use 3rd party API
				dc.isLocalApi = false;
			}
			dc.setBaseUrl();
			dc.getAllAlbums(populateAlbumList);

		});
		
	});
	
	var removeChildNodes = function(parentDiv){
		while (parentDiv.hasChildNodes()) {
			parentDiv.removeChild(parentDiv.firstChild);
		}
	}
	
	
	/**Call the getAllAlbums function and provide the above 
	defined callback function.  The DataController will execute this callback
	function after data is successfully returned.*/
    dc.getAllAlbums(populateAlbumList);

    
})();