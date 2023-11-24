
function loginAuth() {
    const username = document.querySelector('.user_name').value;
    const pass = document.querySelector('.pass').value;

    axios.post('http://localhost:8080/api/v1/auth', {
        user: username,
        password: pass
    }).then(function (response) {
        let token = response.data.token;
        sessionStorage.setItem("token",token);
        sessionStorage.setItem("username",username);
        window.location.assign("http://localhost/CookCraftFrontEnd/html/home.html");
        return false;
       
    }).catch(function (error) {
        Swal.fire({
            title: error.response.data.message,
            text: "",
            icon: "error"
        })
    });
}

function showRegisterForm() {
    document.getElementById("form-login").style.display = "none";
    document.getElementById("form-register").style.display = "block";
}

function backLoginPage() {
    document.getElementById("form-login").style.display = "block";
    document.getElementById("form-register").style.display = "none";
}

function registerFetch() {
    const username = document.querySelector('.user_name_register').value;
    const pass = document.querySelector('.pass_register').value;
    const first = document.querySelector('.first_name').value;
    const last = document.querySelector('.last_name').value;
    const perfil = document.getElementById("perfil").value;
    const email = document.querySelector('.email').value;

    console.log(username);
    console.log(pass);
    axios.post('http://localhost:8080/api/v1/users', {
        user: username,
        password: pass,
        firstName: first,
        lastName: last,
        email: email,
        userProfile:[{
            id:perfil
        }]
    }).then(function (response) {
        if(response.status == 201){
            Swal.fire({
                title: "Registro efetuado com sucesso!",
                text: "",
                icon: "success"
            }).then((result) => {
                if(result.isConfirmed){
                    backLoginPage();
                }
            })
        } else {
            Swal.fire({
                title: response.message,
                text: "",
                icon: "error"
            })
        }
     }).catch(function (error) {
        Swal.fire({
            title: error.response.data.message,
            text: "",
            icon: "error"
        })
      });
}
