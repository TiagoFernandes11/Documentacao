const formulario = document.querySelector("form");
const Iemail = document.querySelector("#inputEmail");
const isenha = document.querySelector("#inputPassword");
const isenha2 = document.querySelector("#inputPassword2");

const linkAPI = "http://localhost:8080/api/clients";

let arrEmails = [];

function cadastrar() {
  if (arrEmails.includes(Iemail.value)) {
    alert("Email ja cadastrado!");
  } else {
    fetch(linkAPI, {
      headers: {
        Accept: "application/json",
        "Content-type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        email: Iemail.value,
        senha: isenha.value,
      }),
    });
    alert("Cadastro concluido");
    limpar();
  }
}

function temNoArray() {
  let i = 0;
  while (i < arrEmails.length) {
    if (arrEmails[i] == Iemail.value) {
      return true;
    }
    i++;
  }
  return false;
}

function limpar() {
  Iemail.value = "";
  isenha.value = "";
  isenha2.value = "";
}

function preencherArrayComEmailsCadastrados() {
  const items = fetch(linkAPI)
    .then((data) => {
      return data.json();
    })
    .then((post) => {
      post.forEach((items) => {
        arrEmails.push(items.email);
      });
    });
}

formulario.addEventListener("submit", function (event) {
  event.preventDefault();
  if (isenha.value === isenha2.value) {
    cadastrar();
  } else {
    alert("Senhas não são identicas");
  }
});

preencherArrayComEmailsCadastrados();
