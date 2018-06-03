function onListPurchaseButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPurchaseResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/purchasehistoryservlet');
    xhr.send();
}

function onPurchaseResponse() {
    const responseText = JSON.parse(this.responseText);
    const purchaseDiv = document.getElementById("purchase");
    const purchaseTable = document.createElement("table");
    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdInvoiceId = document.createElement("td");
        const tdTotalNumTracks = document.createElement("td");
        const tdTotalPrice = document.createElement("td");

        tdInvoiceId.innerHTML = responseText[i].invoiceId;
        tdTotalNumTracks.innerHTML = responseText[i].numberOfTracks;
        tdTotalPrice.innerHTML = responseText[i].totalPrice;

        tr.appendChild(tdInvoiceId);
        tr.appendChild(tdTotalNumTracks);
        tr.appendChild(tdTotalPrice);

        purchaseTable.appendChild(tr);
        }

        purchaseDiv.appendChild(purchaseTable);

        purchaseDiv.classList.remove("hidden");

}

function onDetailedPurchaseButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onDetailedPurchaseResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/purchasehistoryservlet');
    xhr.send();
}

function onDetailedPurchaseResponse() {
    const responseText = JSON.parse(this.responseText);
    const purchase2Div = document.getElementById("purchase2");
    const purchase2Table = document.createElement("table");
    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdInvoiceId = document.createElement("td");
        const tdArtistName = document.createElement("td");
        const tdAlbumTitle = document.createElement("td");
        const tdTrackTitle = document.createElement("td");
        const tdUnitPrice = document.createElement("td");
        const tdGenre = document.createElement("td");

        tdInvoiceId.innerHTML = responseText[i].invoiceId;
        tdArtistName.innerHTML = responseText[i].artistName;
        tdAlbumTitle.innerHTML = responseText[i].albumTitle;
        tdTrackTitle.innerHTML = responseText[i].trackTitle;
        tdUnitPrice.innerHTML = responseText[i].unitPrice;
        tdGenre.innerHTML = responseText[i].genre;

        tr.appendChild(tdInvoiceId);
        tr.appendChild(tdArtistName);
        tr.appendChild(tdAlbumTitle);
        tr.appendChild(tdTrackTitle);
        tr.appendChild(tdUnitPrice);
        tr.appendChild(tdGenre);

        purchase2Table.appendChild(tr);
        }

        purchase2Div.appendChild(purchase2Table);

        purchase2Div.classList.remove("hidden");

}

