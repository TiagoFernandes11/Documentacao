import { Constantes } from "./Constantes/Constantes";

btnRelocate = document.getElementsByTagName("button")[0];
iSearch = document.querySelector("#inputIdAlarme");
table = document.getElementsByTagName("table")[0];

const linkAPI = Constantes.BASE_API_ALARMS;

//open("url", "nome da janela", "width = 600,height = 300") -> popup, fazer tela de procedimento

function renderTableByWord(word) {
  const items = fetch(linkAPI)
    .then((data) => {
      return data.json();
    })
    .then((post) => {
      post.forEach((items) => {
        if (items.alarm_name.toLowerCase().includes(word)) {
          table.innerHTML +=
            "<tr><td><a href=" +
            linkAPI +
            "/" +
            items.id.value +
            ">" +
            items.id +
            "</a>" +
            "</td>" +
            "<td>" +
            items.alarm_name +
            "</td>" +
            "<td>" +
            items.criticality +
            "</td>" +
            "<td>" +
            items.responsible +
            "</td>" +
            "<td>" +
            items.description +
            "</td>" +
            "<td>" +
            items.actuation +
            "</td>" +
            "<td>" +
            items.escalation +
            "</td>" +
            "<td>" +
            items.squad +
            "</td>" +
            //tentativa de botao popup
            "<td>" +
            '<button type="button" class="procedimentos">' +
            "P" +
            "</button>" +
            "</td>";
        }
      });
    });
}

function cleanTable() {
  table.innerHTML =
    '<thead><tr><th scope="col">ID</th><th scope="col">Alarme</th>' +
    '<th scope="col">Criticalidade</th><th scope="col">Responsavel</th>' +
    '<th scope="col">Descrição</th><th scope="col">Acionamento</th>' +
    '<th scope="col">Escalation</th><th scope="col">Squad</th>' +
    "</tr></thead><tbody></tbody>";
}

function renderTable() {
  const items = fetch(linkAPI)
    .then((data) => {
      return data.json();
    })
    .then((post) => {
      post.forEach((items) => {
        table.innerHTML +=
          "<tr><td><a href=" +
          linkAPI +
          "/" +
          items.id +
          ">" +
          items.id +
          "</a>" +
          "</td>" +
          "<td>" +
          items.alarm_name +
          "</td>" +
          "<td>" +
          items.criticality +
          "</td>" +
          "<td>" +
          items.responsible +
          "</td>" +
          "<td>" +
          items.description +
          "</td>" +
          "<td>" +
          items.actuation +
          "</td>" +
          "<td>" +
          items.escalation +
          "</td>" +
          "<td>" +
          items.squad +
          "</td>" +
          //tentativa de botao popup
          "<td>" +
          '<button type="button" class="procedimentos">' +
          "P" +
          "</button>" +
          "</td>";
      });
    });
}

btnRelocate.addEventListener("click", function (event) {
  window.location.href = Constantes.CADASTRO_ALARMES;
});

iSearch.addEventListener("input", function () {
  cleanTable();
  renderTableByWord(iSearch.value.toLowerCase());
});

if (iSearch.value === "") {
  renderTable();
}
