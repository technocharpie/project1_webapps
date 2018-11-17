/*--------------------
    Web Apps UF Fall 2018, Dr. Brown
    copyright 2018 Jose Torres (UF ID: 0739-9486)
--------------------*/

function DataController() {
	this.baseUrl = null;
	this.setBaseUrl();
}

DataController.prototype.setBaseUrl = function() {
	this.baseUrl = document.baseURI;
}






DataController.prototype.getAlbumTitles = function(callBackFunction){
	  var url = this.baseUrl +"music/albums/titles";
	  this.getData(url, callBackFunction);
	  
}

DataController.prototype.getAllAlbums = function(callBackFunction){
	  var url = this.baseUrl;
		if(this.isLocalApi)
			url += "music/albums";
		else //http://ws.audioscrobbler.com/2.0/?method=tag.gettopalbums&tag=disco&api_key=####&format=json
			url += "?method=tag.gettopalbums&tag=jazz&format=json&api_key="+this.lastFmApiKey;
	
	  this.getData(url, callBackFunction);
	 
}

DataController.prototype.getAlbumById = function(id, callBackFunction){
	  var url = this.baseUrl +"music/albums/"+id;
	  this.getData(url, callBackFunction);

}





DataController.prototype.getData = function(url, callBackFunction)
{
	  var xhttp = new XMLHttpRequest();

	  xhttp.onreadystatechange = function()
	  {
		if (this.readyState == 4 && this.status == 200) 
		{
			console.log("txt",this.responseText);

			var jsonData = JSON.parse(this.responseText);

			if(callBackFunction != null){
				callBackFunction(jsonData);
			}
		}
	  };
	  
	  console.log("url",url);
	  xhttp.open("GET", url, true);
	  xhttp.send();
}