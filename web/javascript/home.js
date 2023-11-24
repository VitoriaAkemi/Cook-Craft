const token = sessionStorage.getItem("token");
const username = sessionStorage.getItem("username");
var profileId;
var receitas;
var contadorIngredientes = 0;

axios.get(`http://localhost:8080/api/v1/users/user/${username}`,{
    headers:{
        'Authorization': `Bearer ${token}`
    }
}).then(function(response) {
    profileId = response.data.userProfile[0].id;
    axios.get(`http://localhost:8080/api/v1/profiles/${profileId}`,{
        headers:{
            'Authorization': `Bearer ${token}`
        }
    }).then(function(response) {
        receitas = response.data.receitas;
        createCards(receitas);
    }).catch(function (error) {
        console.log(error);
    });
}).catch(function (error) {
    console.log(error);
});


function createCards(receitas) {
    
    let container = document.getElementById('receita-container');
    container.innerHTML = "";
    let count = 0;
    let countCards = 0;
    receitas.forEach(receita => {
        container.innerHTML += `<div class='col-6'>
                                    <div class='card' style='width: 18rem;'>
                                        <div class="card-body">
                                            <h5>${receita.nomeReceita}</h5>
                                            <p class='card-text'>Tempo de preparo: ${receita.tempoPreparo}</p>
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#card${countCards}">
                                                Ingredientes
                                            </button>
                                            <button type="button" onclick='excluirReceita(${receita.id})' class="btn btn-danger">
                                                Excluir
                                            </button>
                                        </div>
                                    </div>
                                </div>`
        
        let listaIngredientes = '<ul>';
        receita.ingredientes.forEach(ingrediente => {
            listaIngredientes += '<li>'+ ingrediente.nomeIngrediente + ' - ' + ingrediente.quantidade + '</li>';
        })
        listaIngredientes += '</ul>';

        container.innerHTML += `<div class="modal fade" id="card${countCards}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Ingredientes - ${receita.nomeReceita}</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                                                </button>
                                                </div>
                                                    <div class="modal-body">`
                                                    +listaIngredientes+
                                                    `</div>
                                                <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>`

        if(count == 3){
            
            count = 0;
        }
        count++;
        countCards++;
    })

    let containerHtml = container.innerHTML;
            container.innerHTML = "<div class='row'>" + containerHtml + "</div>";
}

function excluirReceita(id){
    axios.delete(`http://localhost:8080/api/v1/receitas/${id}`,{
        headers:{
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => {
        Swal.fire({
            title: "Receita deletada!",
            text: "",
            icon: "success"
        })
        location.reload();
  })
  .catch(error => {
    console.error(error);
  });
}

function addLabelIngrediente(){
    let listIngredientes = document.getElementById("ingredientes-list");
    
    contadorIngredientes++;

     var novaDivRow = document.createElement("div");
     novaDivRow.className = "row mt-2";
 
     var novaDivCol1 = document.createElement("div");
     novaDivCol1.className = "col-6";
     novaDivCol1.innerHTML = '<input type="text" id="ingrediente' + contadorIngredientes + '" class="form-control" placeholder="Nome ingrediente" />';
 
     var novaDivCol2 = document.createElement("div");
     novaDivCol2.className = "col-6";
     novaDivCol2.innerHTML = '<input type="text" id="quantidade' + contadorIngredientes + '" class="form-control" placeholder="Quantidade" />';
 
     novaDivRow.appendChild(novaDivCol1);
     novaDivRow.appendChild(novaDivCol2);
 
     listIngredientes.appendChild(novaDivRow);
  
}

function salvarReceita(){
    let receita = {};
    receita.nomeReceita = document.getElementById("nomeReceita").value;
    receita.tempoPreparo = document.getElementById("tempoPreparo").value;
    receita.ingredientes = [];
    receita.profile = {
        'id':profileId
    }
    for (var i = 0; i <= contadorIngredientes; i++) {
        var ingrediente = document.getElementById("ingrediente" + i).value;
        var quantidade = document.getElementById("quantidade" + i).value;

        receita.ingredientes.push({'nomeIngrediente':ingrediente, 'quantidade': quantidade});
    }

    axios.post('http://localhost:8080/api/v1/receitas', { 
        nomeReceita :  receita.nomeReceita,
        tempoPreparo: receita.tempoPreparo,
        ingredientes: receita.ingredientes,
        profile: receita.profile
    },
    { headers:{
        'Authorization': `Bearer ${token}`
    }
        
    }).then(function (response) {
        console.log(response);
        Swal.fire({
            title: "Receita cadastrada!",
            text: "",
            icon: "success"
        }).then((result) => {
            if(result.isConfirmed){
                location.reload();
            }
        })
    }).catch(function (error) {
        Swal.fire({
            title: error.response.data.message,
            text: "",
            icon: "error"
        })
    });

}

function validateAuth(){
    axios.post(`http://localhost:8080/api/v1/validateAuth/${token}`)
    .then(response => {
        if(response.status !== 202){
            window.location.assign("http://localhost/CookCraftFrontEnd/html/login.html");
        }
    })
    .catch(error => {
        window.location.assign("http://localhost/CookCraftFrontEnd/html/login.html");
    });
}

validateAuth();