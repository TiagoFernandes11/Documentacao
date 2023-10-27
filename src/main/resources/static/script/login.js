const formulario = document.querySelector("form");
const Iemail = document.querySelector("#inputEmail");
const isenha = document.querySelector("#inputPassword");
let arrClients = [];

const linkAPI = "http://localhost:8080/api/clients";

function preencherArrayClients() {
  const items = fetch(linkAPI)
    .then((data) => {
      return data.json();
    })
    .then((post) => {
      post.forEach((items) => {
        let clientValid = {
          email: '',
          senha:''
        };
        clientValid.email = items.email;
        clientValid.senha = items.senha;
        arrClients.push(clientValid);
      });
    });
}

function autenticar(email, senha, clients = arrClients){
  let i = 0;
  while( i < clients.length){
    if(clients[i].email === email.value){
      if(clients[i].senha === senha.value){
        return true;
      }else{
        return false;
      }
    }
    i++;
  }
}

function logar() {
  if (autenticar(Iemail, isenha)) {
    alert("Login efetuado");
  }else{
    alert("Email ou senha incorretos")
  }
}

preencherArrayClients();

formulario.addEventListener("submit", function (event) {
  event.preventDefault();
  logar();
});