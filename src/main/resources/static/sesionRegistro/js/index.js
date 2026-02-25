
document.addEventListener("DOMContentLoaded", function () {
    const modalError = document.getElementById("modalError");
    const modalClose = document.getElementById("modalClose");
    const modalMessage = document.getElementById("modalMessage");

    // Cerrar al hacer clic en la "X"
    modalClose.addEventListener("click", function () {
        modalError.style.display = "none";
    });

    // Cerrar al hacer clic fuera del contenido del modal
    window.addEventListener("click", function (event) {
        if (event.target === modalError) {
            modalError.style.display = "none";
        }
    });

    // Función para mostrar el modal con mensaje de error
    window.mostrarMensaje = function (mensaje) {
        modalMessage.textContent = mensaje; // Se actualiza solo el texto dentro del modal
        modalError.style.display = "block"; // Se muestra el modal
    };
});

function submitGet() {
    var nombre = $("#usuario").val();
    var password = $("#password").val();

    $.ajax({
        type: "GET",
        url: `/usuarios/login`,
        data: { nombre: nombre, password: password },
        async: true,
        success: function (response) {
            console.log("Respuesta del servidor:", response);

            if (response !== "NO OK") {
                window.location.href = "/juego/juego?id_usuario=" + response;
            } else {
                mostrarMensaje("Usuario o contraseña incorrectos.");
            }
        },
        error: function (error) {
            console.error("Error en la solicitud AJAX:", error);
            mostrarMensaje("Ocurrió un error. Inténtalo de nuevo.");
        }
    });
}