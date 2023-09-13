$(document).ready(function () {
    getTasksList();
    $("#header").load("header.html");
    $("#footer").load("footer.html");

    $("#deadline-col").click(function () {
        getSortedTasksList("deadline");
    })
    $("#status-col").click(function () {
        getSortedTasksList("status");
    });
})

function getTasksList() {
    httpClient("GET", "", null, function (data) {
        populateTable(data);
    })
}

function deleteTask(id) {
    httpClient("DELETE", "/" + id, null, function () {
        setTimeout(500);
        getTasksList();
    });
}

function finishTask(id) {
    httpClient("PUT", "/" + id, null, function () {
        setTimeout(500);
        getTasksList();
    });
}

function getSortedTasksList(filterName) {
    httpClient("GET", "/sorted/" + filterName, null, function (data) {
        populateTable(data)
    });
}

function populateTable(data) {
    const tbody = document.getElementById("table-body");
    tbody.innerHTML = null;
    $.each(data, function (index, value) {
        tbody.appendChild(createTableRow(value));
    })
}

function createTableRow(value) {
    const row = document.createElement("tr");
    const title = document.createElement("td");
    const desc = document.createElement("td");
    const deadline = document.createElement("td");
    const status = document.createElement("td");
    const actions = document.createElement("td");
    const editBtn = document.createElement("button");
    const deleteBtn = document.createElement("button");
    const finishBtn = document.createElement("button");
    actions.appendChild(editBtn);
    actions.appendChild(deleteBtn);
    actions.appendChild(finishBtn);
    title.innerText = value.title;
    desc.innerText = value.description;
    deadline.innerText = value.deadline;
    status.innerText = value.status;
    editBtn.onclick = () => window.location.href = "./task-details.html?id=" + value.id;
    editBtn.innerText = "edit";
    deleteBtn.onclick = () => deleteTask(value.id);
    deleteBtn.innerText = "delete";
    finishBtn.onclick = () => finishTask(value.id);
    finishBtn.innerText = "finish";

    row.appendChild(title);
    row.appendChild(desc);
    row.appendChild(deadline);
    row.appendChild(status);
    row.appendChild(actions);
    return row;
}
