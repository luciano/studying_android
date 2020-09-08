
var main = document.querySelector("section#main");

var paragrafosSecuntaria = document.querySelectorAll("section#secundaria p");

var titulo = document.querySelector("#titulo");

var button = document.querySelector("#inputSubmit");
button.onclick = acao;

function acao() {
    titulo.textContent = "Mudando o titulo";
    paragrafosSecuntaria[0].textContent = "Primeiro paragrafo alterado";
    paragrafosSecuntaria[1].textContent = "Segundo paragrafo alterado";
    paragrafosSecuntaria[2].style.background = 'pink';
    paragrafosSecuntaria[2].style.color = 'white';
    return false;
}

function moveu(){
    document.querySelector("section#main p").textContent = "Moveu o mouse aqui";
    document.getElementById("p1").style.background= 'red';
    return false;
}
