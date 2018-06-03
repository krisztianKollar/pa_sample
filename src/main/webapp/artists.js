function onListArtistButtonClicked() {
    showContents(["logout-content", "profile-content", "artists"])
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onArtistResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/artistsservlet');
    xhr.send();
}

function onArtistResponse() {
    const responseText = JSON.parse(this.responseText);
    const artistDiv = document.getElementById("artists");
    const artistTable = document.createElement("table");
    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdArtistName = document.createElement("td");
        const tdTotalNumAlbums = document.createElement("td");
        const tdTotalNumTracks = document.createElement("td");
        const tdTotalPrice = document.createElement("td");

        tdArtistName.innerHTML = responseText[i].name;
        tdTotalNumAlbums.innerHTML = responseText[i].totalNumOfAlbums;
        tdTotalNumTracks.innerHTML = responseText[i].totalNumOfTracks;
        tdTotalPrice.innerHTML = responseText[i].totalPrice;

        tr.appendChild(tdArtistName);
        tr.appendChild(tdTotalNumAlbums);
        tr.appendChild(tdTotalNumTracks);
        tr.appendChild(tdTotalPrice);

        artistTable.appendChild(tr);
        }

        artistDiv.appendChild(artistTable);

        artistDiv.classList.remove("hidden");
}

