const formulario = document.querySelector("form");
const btnFill = document.querySelector("#btnFill");
const btnSearch = document.querySelector("#btnSearch");
const btnUpdate = document.querySelector("#btnUpdate");
const btnDelete = document.querySelector("#btnDelete");
const btnBackToTable = document.querySelector("#btnBacktoTable");

const IAlarmID = document.querySelector("#inputIdAlarme");
const iAlarmName = document.querySelector("#inputAlarmName");
const iCriticality = document.querySelector("#inputCriticality");
const iDescription = document.querySelector("#inputDescription");
const iResponsible = document.querySelector("#inputResponsible");
const iActuation = document.querySelector("#inputActuation");
const iEscalation = document.querySelector("#inputEscalation");
const iSquad = document.querySelector("#inputSqaud");

const linkAPI = "http://localhost:8080/api/alarms"

let arrAlarms = [];

function httpGet(URL) {
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.open("GET", URL, false);
  xmlHttp.send(null);
  return JSON.parse(xmlHttp.response);
}

function prenncherCampos() {
  let getAlarm = httpGet(linkAPI +"/" + IAlarmID.value);
  iAlarmName.value = getAlarm.alarm_name;
  iCriticality.value = getAlarm.criticality;
  iDescription.value = getAlarm.description;
  iResponsible.value = getAlarm.responsible;
  iActuation.value = getAlarm.actuation;
  iEscalation.value = getAlarm.escalation;
  iSquad.value = getAlarm.squad;
}

function preencherArrayComBusca() {
  const items = fetch(linkAPI)
    .then((data) => {
      return data.json();
    })
    .then((post) => {
      post.forEach((items) => {
        arrAlarms.push(items.alarm_name);
      });
    });
}

function cadastrar() {
  fetch(linkAPI, {
    headers: {
      Accept: "application/json",
      "Content-type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({
      alarm_name: iAlarmName.value,
      criticality: iCriticality.value,
      description: iDescription.value,
      responsible: iResponsible.value,
      actuation: iActuation.value,
      escalation: iEscalation.value,
      squad: iSquad.value,
    }),
  });
  arrAlarms.push(iAlarmName.value);
  limpar();
}

function atualizar() {
  fetch(linkAPI, {
    headers: {
      Accept: "application/json",
      "Content-type": "application/json",
    },
    method: "PUT",
    body: JSON.stringify({
      id: IAlarmID.value,
      alarm_name: iAlarmName.value,
      criticality: iCriticality.value,
      description: iDescription.value,
      responsible: iResponsible.value,
      actuation: iActuation.value,
      escalation: iEscalation.value,
      squad: iSquad.value,
    }),
  });
}

function deletar() {
  fetch(linkAPI + "/" + IAlarmID.value, {
    headers: {
      Accept: "application/json",
    },
    method: "DELETE",
  });
}

function limpar() {
  iAlarmName.value = "";
  iDescription.value = "";
  iResponsible.value = "";
  iActuation.value = "";
  iEscalation.value = "";
  iSquad.value = "";
}

btnFill.addEventListener("click", function (event) {
  event.preventDefault();
  prenncherCampos();
});

btnSearch.addEventListener("click", function (event) {
  window.location.href = linkAPI + "/" + IAlarmID.value;
});

btnBackToTable.addEventListener("click", function (event) {
  window.location.href = "http://localhost:8080/table";
});

btnUpdate.addEventListener("click", function (event) {
  event.preventDefault();
  if (
    iAlarmName.value === "" ||
    iDescription.value === "" ||
    iResponsible.value === "" ||
    iActuation.value === "" ||
    iEscalation.value === "" ||
    iSquad.value === ""
  ) {
    alert("Todos os campos devem ser preenchidos!");
    event.stopPropagation();
  } else {
    atualizar();
    alert("Alarme atualizado, redirecionando...");
    window.open(linkAPI + "/" + IAlarmID.value);
  }
});

btnDelete.addEventListener("click", function (event) {
  if (IAlarmID.value === "") {
    alert("Faça uma busca no campo ID antes de excluir");
    event.stopPropagation();
  } else {
    if (confirm("Quer mesmo deletar o alarme: " + iAlarmName.value)) {
      deletar();
      alert("Alarme de id: " + IAlarmID.value + " excluido");
      limpar();
    } else {
      alert("Exclusão cancelada");
    }
  }
});

formulario.addEventListener("submit", function (event) {
  event.preventDefault();
  if (arrAlarms.includes(iAlarmName.value)) {
    alert("Nome de alarme ja cadastrado!");
  } else {
    cadastrar();
    alert("Alarme cadastrado");
  }
});

preencherArrayComBusca();
