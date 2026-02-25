// ==================== TODO EL JUEGO ==================== //
document.addEventListener("DOMContentLoaded", () => {
	
	// ==================== PANEL DE ACCIONES ==================== //

	// Mostrar carta robada o magia en zona de acciones
	function mostrarCartaAccion(srcImagen, tipo) {
	    const visual = document.getElementById("carta-robada-visual");
	    if (visual) {
	        visual.innerHTML = `<img src="${srcImagen}" class="carta">`;
	    }

	    // Mensaje descriptivo en la zona de acciones
	    agregarMensajeAccion(`🔹 ${tipo.toUpperCase()} activado`);
	}

	// Mostrar mensaje de acción en la zona de acciones
	function agregarMensajeAccion(texto) {
	    const mensajes = document.getElementById("mensajes-acciones");
	    if (!mensajes) return;
	    const p = document.createElement("p");
	    p.textContent = texto;
	    mensajes.appendChild(p);
	    mensajes.scrollTop = mensajes.scrollHeight; // auto scroll
	}

	// Mostrar resultado de batalla en la zona de acciones
	function mostrarResultadoAccion(texto) {
	    const resultadoDiv = document.getElementById("resultado-accion");
	    if (resultadoDiv) resultadoDiv.textContent = texto;
	}

	// ==================== CONTROL BOTONES JUGAR / BATALLA ==================== //
const btnJugar = document.getElementById("jugar");
const btnBatalla = document.getElementById("btnBatalla");

const btnRobarHeroe = document.getElementById("btnRobarHeroe");
const btnRobarMagia = document.getElementById("btnRobarMagia");

btnRobarHeroe.addEventListener("click", () => {
  robarCarta("heroe");
});

btnRobarMagia.addEventListener("click", () => {
  robarCarta("magia");
});


// Mostrar solo Jugar al cargar
btnJugar.style.display = "inline-block";
btnBatalla.style.display = "none";

// Al hacer clic en Jugar
btnJugar.addEventListener("click", () => {
    iniciarPartida();
    btnJugar.style.display = "none";
    btnBatalla.style.display = "inline-block";
});

// Al hacer clic en Batalla
btnBatalla.addEventListener("click", () => {
    ejecutarBatalla();
    btnBatalla.style.display = "none";
    btnJugar.style.display = "inline-block";
});

	document.getElementById("btnRobarHeroe").style.display = "none";
	document.getElementById("btnRobarMagia").style.display = "none";


// ==================== MODAL HISTORIA ==================== //
const modalHistoria = document.getElementById("modalHistoria");
const cerrarModalHistoria = document.getElementById("cerrarModal");

if (modalHistoria) {
  modalHistoria.style.display = "flex";

  if (cerrarModalHistoria) {
    cerrarModalHistoria.addEventListener("click", () => {
      modalHistoria.style.display = "none";
    });
  }

  window.addEventListener("click", (e) => {
    if (e.target === modalHistoria) modalHistoria.style.display = "none";
  });
}

// ==================== MENÚ AJUSTES ==================== //
const menu = document.getElementById("dropdownMenu");
const btnMenu = document.getElementById("openModal");

if (btnMenu && menu) {
  btnMenu.addEventListener("click", (e) => {
    e.stopPropagation();
    menu.classList.toggle("show");
  });

  window.addEventListener("click", (e) => {
    if (!btnMenu.contains(e.target) && !menu.contains(e.target)) {
      menu.classList.remove("show");
    }
  });
}

// ==================== MODAL INFORMACIÓN ==================== //
const botonInfo = document.getElementById("informacion");
const modalInfo = document.getElementById("modalInformacion");
const cerrarModalInfo = document.getElementById("cerrarModalInformacion");

if (modalInfo) modalInfo.style.display = "none";

if (botonInfo && modalInfo) {
  botonInfo.addEventListener("click", () => {
    modalInfo.style.display = "flex";
    if (menu) menu.classList.remove("show");
  });
}

if (cerrarModalInfo && modalInfo) {
  cerrarModalInfo.addEventListener("click", () => {
    modalInfo.style.display = "none";
  });
}

window.addEventListener("click", (e) => {
  if (modalInfo && e.target === modalInfo) {
    modalInfo.style.display = "none";
  }
});


  // ==================== DATOS USUARIO ==================== //
  let nivelActual = USER_NIVEL;
  let experienciaActual = USER_EXPERIENCIA;
  let puntosActual = USER_PUNTOS;

  function experienciaMaxima(nivel) {
    return 20 + nivel * 10;
  }

  let expMax = experienciaMaxima(nivelActual);

  // ==================== UI ==================== //
  const progresoBarra = document.getElementById("progreso-experiencia");
  const nivelTexto = document.getElementById("nivel-texto");
  const saldoTexto = document.querySelector("#saldo span");
// ==================== ACTUALIZAR UI ==================== //
function actualizarUI() {
  if (progresoBarra) {
    progresoBarra.style.width = (experienciaActual / expMax) * 100 + "%";
  }
  if (nivelTexto) {
    nivelTexto.textContent = `Nivel ${nivelActual}`;
  }
  if (saldoTexto) {
    saldoTexto.textContent = puntosActual;
  }
}



  // ==================== JUEGO ==================== //
  const cartasTotales = 10;
  let partidaEnCurso = false;
  let cartasJugador = [];
  let cartasRival = [];
  let cartaUsada = false;
  



  // ==================== PARTIDA ==================== //
  function iniciarPartida() {
      // 🔹 LIMPIAR ZONA DE ACCIONES al iniciar nueva partida
      const mensajes = document.getElementById("mensajes-acciones");
      if (mensajes) mensajes.innerHTML = ""; // limpia los mensajes

      const resultadoDiv = document.getElementById("resultado-accion");
      if (resultadoDiv) resultadoDiv.textContent = ""; // limpia el resultado

      const visual = document.getElementById("carta-robada-visual");
      if (visual) visual.innerHTML = "";

      cartasJugador = [];
      cartasRival = [];
      cartaUsada = false;

      document.getElementById("cartas-jugador").innerHTML = "";
      document.getElementById("cartas-rival").innerHTML = "";

      for (let i = 0; i < 3; i++) {
        cartasJugador.push(crearCarta(getCartaAleatoria(), "cartas-jugador", "jugador"));
        cartasRival.push(crearCarta(getCartaAleatoria(), "cartas-rival", "rival"));
      }

      // 🔽 CONTROL DE BOTONES
      document.getElementById("jugar").style.display = "none";
      document.getElementById("btnBatalla").style.display = "inline-block";
      document.getElementById("btnRobarHeroe").style.display = "inline-block";
      document.getElementById("btnRobarMagia").style.display = "inline-block";
  }


  function crearCarta(valor, contenedorId, tipo) {
    const contenedor = document.getElementById(contenedorId);
    const wrap = document.createElement("div");
    wrap.className = "carta-container";

    const img = document.createElement("img");
    img.src = `/sesionRegistro/assets/carta${valor}.png`;
    img.dataset.valor = valor;
    img.className = `carta ${tipo}`;

    const span = document.createElement("span");
    span.className = "poder";
    span.textContent = valor;

    wrap.append(img, span);
    contenedor.appendChild(wrap);

    return img;
  }

  // ==================== ROBO DE CARTAS ==================== //
  function robarCarta(tipo) {
      if (cartaUsada) {
        agregarMensajeAccion("⚠️ Solo puedes robar UNA carta por partida");
        return;
      }
      cartaUsada = true;

      if (tipo === "heroe") {
          const valor = getCartaAleatoria();
          const srcCarta = `/sesionRegistro/assets/carta${valor}.png`;

          mostrarCartaAccion(srcCarta, "Carta robada");

          habilitarSeleccionJugador((indexJugador) => {
              const jugador = document.querySelectorAll("#cartas-jugador img")[indexJugador];
              jugador.src = srcCarta;
              jugador.dataset.valor = valor;
              actualizarPoder(jugador);
              agregarMensajeAccion("✅ Carta intercambiada correctamente");
          });
      } else if (tipo === "magia") {
          const poder = Math.floor(Math.random() * 3) + 1;
          const srcMagia = `/sesionRegistro/assets/poder${poder}.png`;

          mostrarCartaAccion(srcMagia, "Magia robada");

          if (poder === 1) {
              agregarMensajeAccion("🔄 Intercambia una carta");
              habilitarSeleccionJugador((jIndex) => {
                  habilitarSeleccionRival((rIndex) => {
                      const j = document.querySelectorAll("#cartas-jugador img")[jIndex];
                      const r = document.querySelectorAll("#cartas-rival img")[rIndex];
                      [j.src, r.src] = [r.src, j.src];
                      [j.dataset.valor, r.dataset.valor] = [r.dataset.valor, j.dataset.valor];
                      actualizarPoder(j);
                      actualizarPoder(r);
                      agregarMensajeAccion("✅ Cartas intercambiadas");
                  });
              });
          } else if (poder === 2) {
              agregarMensajeAccion("🔥 Anula una carta enemiga");
              habilitarSeleccionRival((index) => {
                  const r = document.querySelectorAll("#cartas-rival img")[index];
                  r.dataset.valor = 0;
                  actualizarPoder(r);
                  agregarMensajeAccion("✅ Carta enemiga anulada");
              });
          } else {
              document.querySelectorAll("#cartas-rival img").forEach(r => {
                  r.dataset.valor = 0;
                  actualizarPoder(r);
              });
              agregarMensajeAccion("💀 Todas las cartas enemigas anuladas");
          }
      }
  }




  // ==================== GANAR EXPERIENCIA ==================== //
function ganarExperiencia(puntos) {
    experienciaActual += puntos;

    // Subir de nivel si supera el máximo
    while (experienciaActual >= experienciaMaxima(nivelActual)) {
        experienciaActual -= experienciaMaxima(nivelActual);
        nivelActual++;
        expMax = experienciaMaxima(nivelActual); // actualizar máximo para nueva barra
        mostrarModal(`🎉 ¡Has subido al nivel ${nivelActual}!`);
    }

    actualizarUI();
}

// ==================== BATALLA ==================== //
function ejecutarBatalla() {
    const j = document.querySelectorAll("#cartas-jugador img");
    const r = document.querySelectorAll("#cartas-rival img");

    let victorias = 0;
    let puntosGanados = 0;

    for (let i = 0; i < 3; i++) {
        const valorJ = Number(j[i].dataset.valor);
        const valorR = Number(r[i].dataset.valor);

        if (valorJ > valorR) {
            r[i].dataset.valor = 0;
            actualizarPoder(r[i]);
            victorias++;
            puntosGanados += valorJ;
            agregarMensajeAccion(`✅ Carta rival ${i+1} derrotada`);
        } else if (valorR > valorJ) {
            j[i].dataset.valor = 0;
            actualizarPoder(j[i]);
            agregarMensajeAccion(`⚠️ Tu carta ${i+1} ha sido derrotada`);
        } else {
            j[i].dataset.valor = 0;
            r[i].dataset.valor = 0;
            actualizarPoder(j[i]);
            actualizarPoder(r[i]);
            agregarMensajeAccion(`⚔️ Carta ${i+1} empate → ambas derrotadas`);
        }
    }

    if (victorias >= 2) {
        puntosActual += puntosGanados;
        ganarExperiencia(puntosGanados);
        mostrarResultadoAccion(`🏆 Victoria +${puntosGanados} puntos`);
    } else {
        mostrarResultadoAccion("💀 Derrota");
    }

    actualizarUI();

    document.getElementById("btnBatalla").style.display = "none";
    document.getElementById("btnRobarHeroe").style.display = "none";
    document.getElementById("btnRobarMagia").style.display = "none";
    document.getElementById("jugar").style.display = "inline-block";
}



  // ==================== PODERES ==================== //
  function habilitarSeleccionJugador(callback) {
    limpiarSeleccion();

    document.querySelectorAll("#cartas-jugador img").forEach((carta, index) => {
      carta.classList.add("carta-seleccionable");
      carta.onclick = () => {
        callback(index);
        limpiarSeleccion();
      };
    });
  }

  function habilitarSeleccionRival(callback) {
    limpiarSeleccion();

    document.querySelectorAll("#cartas-rival img").forEach((carta, index) => {
      carta.classList.add("carta-seleccionable");
      carta.onclick = () => {
        callback(index);
        limpiarSeleccion();
      };
    });
  }

  function limpiarSeleccion() {
    document.querySelectorAll("img.carta").forEach(carta => {
      carta.classList.remove("carta-seleccionable");
      carta.onclick = null;
    });
  }

  function intercambiarCarta(index) {
    const j = document.querySelectorAll("#cartas-jugador img")[index];
    const r = document.querySelectorAll("#cartas-rival img")[index];

    [j.src, r.src] = [r.src, j.src];
    [j.dataset.valor, r.dataset.valor] = [r.dataset.valor, j.dataset.valor];

    actualizarPoder(j);
    actualizarPoder(r);
  }

  function aplicarHechizo(index) {
    const r = document.querySelectorAll("#cartas-rival img")[index];
    r.dataset.valor = Math.max(0, r.dataset.valor - 3);
    actualizarPoder(r);
    mostrarModal("🔥 Hechizo aplicado (-3 poder)");
  }

  
  
 function actualizarPoder(carta) {
  const valor = Number(carta.dataset.valor);
  const poderSpan = carta.parentElement.querySelector(".poder");

  poderSpan.textContent = valor;

  if (valor <= 0) {
    carta.classList.add("carta-derrotada");
    poderSpan.classList.add("poder-derrotado");
  } else {
    carta.classList.remove("carta-derrotada");
    poderSpan.classList.remove("poder-derrotado");
  }
}



  // ==================== SERVIDOR ==================== //
  function guardarJugador() {
    const idUsuario = Number(document.getElementById("id_usuario").value);

    fetch("/juego/actualizarNivel", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        id: idUsuario,
        nivel: nivelActual,
        experiencia: experienciaActual,
        puntos: puntosActual
      })
    });
  }

  // ==================== UTIL ==================== //
  function getCartaAleatoria() {
    return Math.floor(Math.random() * cartasTotales) + 1;
  }

  function mostrarModal(msg) {
    const modal = document.getElementById("modalResultado");
    document.getElementById("mensajeResultado").innerHTML = msg;
    modal.classList.add("show");

    document.getElementById("btnCerrarModalResultado").onclick = () => {
      modal.classList.remove("show");
    };
  }
  const musica = document.getElementById("musicaJuego");
let musicaIniciada = false;

function iniciarMusica() {
  if (!musica || musicaIniciada) return;

  musica.volume = 0.5; // volumen inicial (opcional)
  musica.play()
    .then(() => musicaIniciada = true)
    .catch(err => console.log("Autoplay bloqueado:", err));
}
if (cerrarModalHistoria) {
  cerrarModalHistoria.addEventListener("click", () => {
    modalHistoria.style.display = "none";
    iniciarMusica();
  });
}




});
