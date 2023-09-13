let taskId = null;

$(document).ready(function () {
    $("#header").load("header.html");
    $("#footer").load("footer.html");
    $('form').submit(function (event) {
        event.preventDefault();
        addOrUpdate()
    })
    checkTaskId();
});

function checkTaskId() {
    taskId = new URLSearchParams(window.location.search).get("id");
    if (taskId != null) {
        httpClient("GET", "/" + taskId, null, function (data) {
            $("#title").val(data.title)
            $("#description").val(data.description)
            $("#deadline").val(data.deadline)
            $("#status").val(data.status)
        })
    }
}

function addOrUpdate() {
    const description = $("#description").val();
    const title = $("#title").val();
    const deadline = $("#deadline").val();
    const status = $("#status").val();
    if (description.length > 300 || title.length > 100 || deadline === "") {
        alert("Invalid data!!! Please check the form");
        return;
    }

    const data = {
        id: taskId,
        title: title,
        description: description,
        deadline: deadline,
        status: status
    }
    if (taskId != null) {
        httpClient("PUT", "", data, () => navigateToTaskList());
    } else {
        httpClient("POST", "", data, () => navigateToTaskList());
    }
}

function navigateToTaskList() {
    window.location.href = "index.html";
}


