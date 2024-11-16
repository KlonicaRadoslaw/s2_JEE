let table;
function handleErrors(id, response) {
    const errorDiv = document.getElementById(id);
    errorDiv.innerHTML = "";
    if (response.status === 404) {
        errorDiv.innerHTML += '<p class="alert alert-danger" style="display: block;">Wystąpił nieoczekiwany błąd</p>';
    } else {
        var x = JSON.parse(response.responseText)
        x['errors'].forEach(err => {
            const p = `<p class="alert alert-danger" style="display: block;">${err}</p>`;
            errorDiv.innerHTML += p;
        })
    }
}

function loadData() {
    table.innerHTML = "";
    $.ajax({
        url: "/students",
        method: "GET"
    })
            .done(res => {
                res.forEach(student => {
                    const newRow = table.insertRow(-1);
                    newRow.id = `student_${student.id}`;
                    newRow.innerHTML = `
                    <td id="id">${student.id}</td>
                    <td id="name">${student.name}</td>
                    <td id="surname">${student.surname}</td>
                    <td id="average">${student.average}</td>
                    <td>
                        <button onclick="showUpdateModal(${student.id})" class="btn btn-success">Aktualizuj</button>
                        <button onclick="showDeleteModal(${student.id})" class="btn btn-danger">Usuń</button>
                    </td>`;
                })
            })
            .fail(() => {
                alert("Wystąpił błąd w pobieraniu danych");
            });
}
function showAddModal() {
    document.getElementById("name").value = "";
    document.getElementById("surname").value = "";
    document.getElementById("average").value = "";
    $('#addStudentModal').modal('show');
}

function addStudent() {
    const studentData = {
        name: document.getElementById("name").value,
        surname: document.getElementById("surname").value,
        average: parseFloat(document.getElementById("average").value)
    };

    $.post({
        url: "/student/add",
        data: JSON.stringify(studentData),
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (res) {
        loadData();
        $('#addStudentModal').modal('hide');
    }).fail(res => {
        handleErrors("addStudentErrors", res);
    });
}

function showUpdateModal(id) {
    const row = table.querySelectorAll(`tr#student_${id} td`);
    const name = row[1].innerHTML;
    const surname = row[2].innerHTML;
    const average = row[3].innerHTML;
    document.getElementById("updateName").value = name;
    document.getElementById("updateSurname").value = surname;
    document.getElementById("updateAverage").value = average;
    document.getElementById("updateStudentId").value = id;

    $('#updateStudentModal').modal('show');
}
function updateStudent() {
    const studentData = {
        id: document.getElementById("updateStudentId").value,
        name: document.getElementById("updateName").value,
        surname: document.getElementById("updateSurname").value,
        average: parseFloat(document.getElementById("updateAverage").value)
    };

    $.ajax({
        url: `/student/${document.getElementById("updateStudentId").value}`,
        data: JSON.stringify(studentData),
        headers: {
            'Content-Type': 'application/json'
        },
        method: "PUT"
    }).done(function (res) {
        loadData();
        $('#updateStudentModal').modal('hide');
    }).fail(function (res) {
        console.log(res)
        handleErrors("updateStudentErrors", res);
    });
}
function showDeleteModal(id) {
    document.getElementById("deleteStudentId").value = id;
    $('#deleteStudentModal').modal('show');
}
function deleteStudent() {
    $.ajax({
        url: `/student/${document.getElementById("deleteStudentId").value}`,
        method: "DELETE"
    }).done(function (res) {
        loadData();
        $('#deleteStudentModal').modal('hide');
    }).fail(function (res) {
        console.log(res)
        handleErrors("deleteStudentErrors", res);
    });
}


$(function () {
    table = $("#studentsTable")[0];
    $(".modal").on("hidden.bs.modal", function () {
        $("#addStudentErrors, #updateStudentErrors, #deleteStudentErrors").html("");
    });
    loadData();
});
