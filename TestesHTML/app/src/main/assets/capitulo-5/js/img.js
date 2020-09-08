var img = document.querySelector("#imagem img");

var imagens = ["../img/android_blue.png", "../img/android_green.png", "../img/images.png"];

var imagemAtual = 0;

function trocarImagem() {
    imagemAtual = (imagemAtual + 1) % 3;

    img.src = imagens[imagemAtual];
}

var timer = setInterval(trocarImagem, 4000);
