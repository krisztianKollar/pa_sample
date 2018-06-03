function onListEmplButtonClicked() {
    showContents(["logout-content", "profile-content", "employees"])
    const emplDiv = document.getElementById("employees");
    removeAllChildren(emplDiv);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onEmployeesResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/employeesservlet');
    xhr.send();
}

function onEmployeesResponse() {
    const responseText = JSON.parse(this.responseText);
    const emplDiv = document.getElementById("employees");
    const emplTable = document.createElement("table");

    const header = emplTable.createTHead();
    const row = header.insertRow(0);
    const headerList = ["Firstname", "Lastname", "Email", "Address", "Title", "Number of Represented Customers"]
    for (let h = 0; h < headerList.length; h++) {
        let cell = row.insertCell(h);
        cell.innerHTML = headerList[h];
        cell.style.fontWeight = "900";
        cell.style.backgroundColor = "green";
        cell.style.color = "white";
    }

    for (let i = 0; i < responseText.length; i++) {
        const tr = document.createElement("tr");
        const tdFirstName = document.createElement("td");
        const tdLastName = document.createElement("td");
        const tdEmail = document.createElement("td");
        const tdFullAddress = document.createElement("td");
        const tdTitle = document.createElement("td");
        const tdNumOfRepresentedCustomers = document.createElement("td");

        tdFirstName.innerHTML = responseText[i].firstName;
        tdLastName.innerHTML = responseText[i].lastName;
        tdEmail.innerHTML = responseText[i].email;
        tdFullAddress.innerHTML = responseText[i].fullAddress;
        tdTitle.innerHTML = responseText[i].title;
        tdNumOfRepresentedCustomers.innerHTML = responseText[i].numOfRepresentedCustomers;

        tr.appendChild(tdFirstName);
        tr.appendChild(tdLastName);
        tr.appendChild(tdEmail);
        tr.appendChild(tdFullAddress);
        tr.appendChild(tdTitle);
        tr.appendChild(tdNumOfRepresentedCustomers);

        emplTable.appendChild(tr);
        }

        emplDiv.appendChild(emplTable);

        emplDiv.classList.remove("hidden");
}

