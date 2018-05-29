

function onListCustButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onCustResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/customersservlet');
    xhr.send();
}

function onCustResponse() {
    const responseText = JSON.parse(this.responseText);
    const custDiv = document.getElementById("customers");
    const custTable = document.createElement("table");
    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdFirstName = document.createElement("td");
        const tdLastName = document.createElement("td");
        const tdCompany = document.createElement("td");
        const tdFullAddress = document.createElement("td");
        const tdSalesReprContactInf = document.createElement("td");
        const tdNumOfPurchases = document.createElement("td");

        tdFirstName.innerHTML = responseText[i].firstName;
        tdLastName.innerHTML = responseText[i].lastName;
        tdCompany.innerHTML = responseText[i].company;
        tdFullAddress.innerHTML = responseText[i].fullAddress;
        tdSalesReprContactInf.innerHTML = responseText[i].salesReprContactInf;
        tdNumOfPurchases.innerHTML = responseText[i].numOfPurchases;

        tr.appendChild(tdFirstName);
        tr.appendChild(tdLastName);
        tr.appendChild(tdCompany);
        tr.appendChild(tdFullAddress);
        tr.appendChild(tdSalesReprContactInf);
        tr.appendChild(tdNumOfPurchases);

        custTable.appendChild(tr);
        }

        custDiv.appendChild(custTable);

        custDiv.classList.remove("hidden");
}

