window.onload = function () {
    document.getElementById("titulo").innerHTML = "Listagem de Alunos.";
    document.getElementById("edtAluno").style.display = "none";
    document.getElementById("cadastroAluno").style.display = "none";
    enviaGet();
};

function enviaGet() {
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
}

function myFunction(arr) {
    //var tblAlunos = document.getElementById("tblAlunos");
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
    
    var dados = new Object();
    dados.nome = txtNome.value;
    dados.idade = txtIdade.value;

    enviaPost(dados, txtId.value);

	location.reload();
}

function enviaPost(dados, id) {
	var xmlhttp = new XMLHttpRequest();
	if(id !== 0) {
		var url = "http://localhost:5000/alunos/" + id;
	} else {
		var url = "http://localhost:5000/alunos";
	}
	

	xmlhttp.onreadystatechange = function () {
	    if (this.readyState === 4 && this.status === 200) {
	        alert(this.responseText);
	    }
	};
	xmlhttp.open("POST", url, true);
	xmlhttp.setRequestHeader("Content-Type","Application/json");
	xmlhttp.send(JSON.stringify(dados));
}
	
function mostraCadastroAluno() {
	document.getElementById("principal").style.display = "none";
	document.getElementById("cadastroAluno").style.display = "block";
}

function cadSave(){
	var cadNome = document.getElementById("cadNome");
	var cadIdade = document.getElementById("cadIdade");
	var dados = new Object();
	dados.nome = cadNome.value;
	dados.idade = cadIdade.value;
	
	enviaPost(dados, 0);
	location.reload();
}