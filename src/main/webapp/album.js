function onAddAlbumToArtistButtonElClicked() {
    const params = new URLSearchParams();
    params.append('albumname', document.getElementById("album").value);
    params.append('artistname', document.getElementById("artist").value);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onAlbumToArtistResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/albumservlet');
    xhr.send(params);
}

function onAlbumToArtistResponse() {
       const asd = JSON.parse(this.responseText);
       console.log(asd);
    clearMessages();
    showContents(['profile-content', 'logout-content']);
}


