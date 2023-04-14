window.addEventListener('load', function () {
    const formData = {
                id:"5",
                producto: document.querySelector('#producto').value,
                lote: document.querySelector('#lote').value,
                fechaVencimiento: "2023-03-27",
                pesoNeto:"40 kg",
                operario: document.querySelector('#operario').value,
                fechaActual: "null"
            };
    const url = '/api';
            const settings = {

                method: 'POST',
                mode: "core",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }
            console.log(settings)




    console.log("se ejecuto la funcion")
     //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
     //los datos que el usuario cargará del nuevo estudiante
    const formulario = document.querySelector('#etiqueta');
    console.log(formulario)
    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        console.log("Dentro del submirt")

        //creamos un JSON que tendrá los datos del nuevo estudiante
        const formData = {id:"5",
                                          producto: document.querySelector('#producto').value,
                                          lote: document.querySelector('#lote').value,
                                          fechaVencimiento: "2023-03-27",
                                          pesoNeto:"40 kg",
                                          operario: document.querySelector('#operario').value,
                                          fechaActual: "null"
        };

        //invocamos utilizando la función fetch la API estudiantes con el método POST
        //que guardará al estudiante que enviaremos en formato JSON
        const url = '/api';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        console.log(settings)

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
               //Si no hay ningun error se muestra un mensaje diciendo que el estudiante
               //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Estudiante agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 //se dejan todos los campos vacíos por si se quiere ingresar otro estudiante
                 resetUploadForm();

            })
            .catch(error => {
                 //Si hay algun error se muestra un mensaje diciendo que el estudiante
                 //no se pudo guardar y se intente nuevamente
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";
                   //se dejan todos los campos vacíos por si se quiere ingresar otro estudiante
                   resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#producto').value = "";
        document.querySelector('#lote').value = "";
        document.querySelector('#fechaVencimiento').value = "";
        document.querySelector('#operario').value = "";
    }


});