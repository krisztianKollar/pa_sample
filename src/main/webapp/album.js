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
    alert("You have added the album.")
    clearMessages();
    showContents(['profile-content', 'logout-content']);
}


function onListAlbumButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onAlbumResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/albumservlet');
    xhr.send();
}

function onAlbumResponse() {
    const responseText = JSON.parse(this.responseText);
    const albumsDiv = document.getElementById("albums");
    const albumTable = document.createElement("table");
    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdArtistName = document.createElement("td");
        const tdTitle = document.createElement("td");
        const tdTotalNumTracks = document.createElement("td");
        const tdTotalPrice = document.createElement("td");

        tdArtistName.innerHTML = responseText[i].artistName;
        tdTitle.innerHTML = responseText[i].title;
        tdTotalNumTracks.innerHTML = responseText[i].numOfTracks;
        tdTotalPrice.innerHTML = responseText[i].totalPrice;

        tr.appendChild(tdArtistName);
        tr.appendChild(tdTitle);
        tr.appendChild(tdTotalNumTracks);
        tr.appendChild(tdTotalPrice);

        albumTable.appendChild(tr);
        }

        albumsDiv.appendChild(albumTable);

        albumsDiv.classList.remove("hidden");
}




