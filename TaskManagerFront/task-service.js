function httpClient(method, url, data, success) {
    $.ajax({
            type: method,
            url: "http://localhost:8080/api/v1/tasks" + url,
            data: data != null ? JSON.stringify(data) : null,
            success: success,
            headers: {"Content-Type": 'application/json; charset=utf-8'}
        }
    )
}
