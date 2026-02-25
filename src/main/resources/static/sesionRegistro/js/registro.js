document.addEventListener("DOMContentLoaded", function () {
    const modals = document.querySelectorAll(".modal");
    const closeButtons = document.querySelectorAll(".contenido-modal button");
    const checkTerms = document.getElementById("checkTerms");

    // Cerrar modales al hacer clic en los botones de cerrar
    closeButtons.forEach(button => {
        button.addEventListener("click", function () {
            this.closest(".modal").classList.remove("mostrar");
        });
    });

    // Cerrar modales al hacer clic fuera del contenido-modal
    window.addEventListener("click", function (event) {
        modals.forEach(modal => {
            if (event.target === modal) {
                modal.classList.remove("mostrar");
            }
        });
    });

    // Manejo del envío del formulario
    document.getElementById("form").addEventListener("submit", function (event) {
        event.preventDefault();

        let errores = [];

        const dni = document.getElementById("dni").value.trim();
        const nombre = document.getElementById("nombre_cliente").value.trim();
        const apellidos = document.getElementById("apellidos").value.trim();
        const username = document.getElementById("usuario").value.trim();
        const email = document.getElementById("email").value.trim();
        const psw = document.getElementById("password").value;
        const psw2 = document.getElementById("password2").value;
        const fecha_nac = document.getElementById("fecha_nac").value;

        const fechaNacimientoDate = new Date(fecha_nac);
        const fechaActual = new Date();
        const edad = fechaActual.getFullYear() - fechaNacimientoDate.getFullYear();

        const dniRegex = /^[0-9]{8}[A-Za-z]$/;

        if (!checkTerms.checked) errores.push("modalTerms");
        if (!dniRegex.test(dni)) errores.push("modal6");
        if (isNaN(fechaNacimientoDate.getTime()) || edad < 18) errores.push("modal3");
        if (psw !== psw2 || psw === "") errores.push("modal2");
        if (/[0-9]/.test(nombre) || nombre === "") errores.push("modal5");
        if (/[0-9]/.test(apellidos) || apellidos === "") errores.push("modal4");
        if (email === "") errores.push("modal4");
        if (username === "") errores.push("modal5");

        // Ocultar todos los modales antes de mostrar errores nuevos
        modals.forEach(modal => modal.classList.remove("mostrar"));

        if (errores.length > 0) {
            errores.forEach(id => document.getElementById(id).classList.add("mostrar"));
        } else {
			const formData = {
			    dni: dni,
			    nombre: nombre,
			    apellidos: apellidos,
			    username: username,
			    email: email,
			    password: psw, 
			    nacimiento: fecha_nac
			};


            console.log("Datos enviados al servidor:", formData);

            $.ajax({
                type: "POST",
                url: "/usuarios/registrar",
                contentType: "application/json",
                data: JSON.stringify(formData),
                success: function (response) {
					if (response === "ERROR") {
					    document.getElementById("modalMal").classList.add("mostrar");
					} else if (response === "OK") {
					    document.getElementById("modal1").classList.add("mostrar");
					    setTimeout(function () {
					        window.location.href = "/";
					    }, 2000); // espera 2 segundos antes de redirigir
					}

                },
                error: function (error) {
                    console.error("Error en la solicitud AJAX:", error);
                }
            });
        }
    });
});
