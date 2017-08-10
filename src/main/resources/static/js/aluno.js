window.onload = function () {
    document.getElementById("titulo").innerHTML = "Listagem de Alunos.";
    document.getElementById("edtAluno").style.display = "none";
};

var xmlhttp = new XMLHttpRequest();
var url = "http://localhost:5000/alunos";

xmlhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        var myArr = JSON.parse(this.responseText);
        myFunction(myArr);
    }
};
xmlhttp.open("GET", url, true);
xmlhttp.send();

function myFunction(arr) {
    var tblAlunos = document.getElementById("tblAlunos");
    arr.forEach(function (object) {
        var tr = document.createElement("tr");
        tr.setAttribute("id", "btn" + object.identifier);
        tr.innerHTML = "<td><button onclick=\"editar('btn" + object.identifier + "')\">Editar</button></td>" +
                "<td><button onclick='apagar()'>Apagar</button></td>" +
                "<td>" + object.identifier + "</td>" +
                "<td>" + object.nome + "</td>" +
                "<td>" + object.idade + "</td>";
        tblAlunos.appendChild(tr);
    });
}

function editar(btnId) {
    document.getElementById("edtAluno").style.display = "block";
    var tr = document.getElementById(btnId);
    var txtId = document.getElementById("txtId");
    var txtNome = document.getElementById("txtNome");
    var txtIdade = document.getElementById("txtIdade");
    txtId.value = tr.cells[2].textContent;
    txtNome.value = tr.cells[3].textContent;
    txtIdade.value = tr.cells[4].textContent;
}

function apagar() {
    
}

function edtUpdate() {
	var txtId = document.getElementById("txtId");
    var txtNome = document.getElementById("txtNome");
    var txtIdade = document.getElementById("txtIdade");
    
    alert(txtId.value + txtNome.value + txtIdade.value);
	
	document.getElementById("edtAluno").style.display = "none";
}
	