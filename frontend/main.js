document.addEventListener("DOMContentLoaded", (event) => {
  const API_BASE_URL = "http://localhost:8080/api";

  const successToast = document.getElementById("successToast");
  const errorToast = document.getElementById("errorToast");
  const limitToast = document.getElementById("limitToast"); // Asegúrate de que este elemento exista en tu HTML

  function mostrarToast(type, message, redirectUrl = null) {
    let toastElement;
    let messageSpan;

    if (type === "success") {
      toastElement = successToast || document.querySelector("#successToast");
      messageSpan = toastElement ? toastElement.querySelector("span") : null;
    } else if (type === "error") {
      toastElement = errorToast || document.querySelector("#errorToast");
      messageSpan = toastElement ? toastElement.querySelector("span") : null;
    } else if (type === "limit") {
      // Usa el elemento limitToast
      toastElement = limitToast || document.querySelector("#limitToast");
      messageSpan = toastElement ? toastElement.querySelector("span") : null;
    }

    if (toastElement && messageSpan) {
      // Establecer mensaje si se proporciona (útil para errores dinámicos)
      if (message) {
        messageSpan.textContent = message;
      }

      // Mostrar el toast
      toastElement.classList.remove("opacity-0", "translate-x-10");
      toastElement.classList.add("opacity-100", "translate-x-0");

      setTimeout(() => {
        // Ocultar el toast
        toastElement.classList.remove("opacity-100", "translate-x-0");
        toastElement.classList.add("opacity-0", "translate-x-10");

        // Redirigir si se especifica
        if (redirectUrl) {
          window.location.href = redirectUrl;
        }
      }, 3000); // Aumentado a 3 segundos para que sea visible
    }
  }

  // Elementos del Login
  const loginForm = document.getElementById("loginForm");

  // Elementos de la tabla de proyectos
  const projectsTableBody = document.querySelector("#projects-table tbody");
  const newProjectLink = document.getElementById("new-project-link");
  // const limitToast = document.getElementById("limitToast"); // Ya está definido arriba

  // Elementos del Modal de Edición
  const editModal = document.getElementById("editModal");
  const closeModalButton = document.getElementById("closeModal");
  const editProjectForm = document.getElementById("editProjectForm");
  const editProjectIdInput = document.getElementById("editProjectId");
  const newProjectNameInput = document.getElementById("newProjectName");

  // Elementos del formulario de creación
  const creationForm = document.getElementById("creationForm");
  const nombreInput = document.getElementById("nombre");
  const descripcionTextarea = document.getElementById("descripcion");
  const createProjectBtn = document.getElementById("create-project-btn");
  const fechaCreacionInput = document.getElementById("fechaCreacion");

  //Lógica del login
  if (loginForm) {
    const nombreUsuarioInput = document.getElementById("nombreUsuario");
    const passwordInput = document.getElementById("password");

    loginForm.addEventListener("submit", function (event) {
      event.preventDefault();

      const userData = {
        nombreUsuario: nombreUsuarioInput.value,
        contraseña: passwordInput.value,
      };

      fetch(`${API_BASE_URL}/usuarios/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userData),
      })
        .then((response) => {
          if (!response.ok) {
            // No lanza error aquí si quieres manejarlo en el siguiente .catch
            return response.json().then(err => {
              throw new Error(
                err.message ||
                "Credenciales incorrectas o usuario no registrado."
              );
            })
          }
          return response.json();
        })
        .then((data) => {
          localStorage.setItem("usuarioLogueado", JSON.stringify(data));
          // Puedes añadir un toast de éxito antes de redirigir si quieres verlo,
          // aunque normalmente el éxito de login es una redirección inmediata.
          window.location.href = "pantallaInicio.html";
        })
        .catch((error) => {
          console.error("Fallo de Login:", error.message);
          mostrarToast("error", "Fallo al iniciar sesión: Acceso denegado.");
        });
    });
  }

  //Carga de los proyectos
  function cargarProyectos() {
    if (!projectsTableBody) return;

    fetch(`${API_BASE_URL}/proyectos`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(
            `Error al obtener proyectos. Estado: ${response.status}`
          );
        }
        return response.json();
      })
      .then((proyectos) => {
        if (newProjectLink && limitToast) {
          // Si el límite se cumplió previamente, lo reiniciamos aquí
          newProjectLink.classList.remove("opacity-50", "pointer-events-none");
        }
        console.log("Proyectos cargados:", proyectos);
        mostrarProyectosEnTabla(proyectos);
      })
      .catch((error) => {
        console.error("Error al cargar los proyectos:", error);
        projectsTableBody.innerHTML = `<tr><td colspan="5" class="py-3 px-4 text-center text-red-600">Error al cargar los proyectos, inténtelo de nuevo más tarde</td></tr>`;
      });
  }

  function mostrarProyectosEnTabla(proyectos) {
    if (!projectsTableBody) return;

    if (proyectos.length === 0) {
      projectsTableBody.innerHTML = `<tr><td colspan="5" class="py-3 px-4 text-center text-gray-500">No hay proyectos disponibles.</td></tr>`;
      return;
    }

    const htmlFilas = proyectos
      .map((proyecto) => {
        const nombreLimpio = proyecto.nombre
          ? proyecto.nombre.replace(/'/g, "\\'")
          : "";
        const proyectoId = proyecto.idProyecto;

        return `
            <tr class="border-b hover:bg-gray-50 transition duration-150">
              <td class="py-3 px-4 font-medium text-gray-900">${
                proyecto.nombre
              }</td>
              <td class="py-3 px-4 text-gray-600">${
                proyecto.descripcion
                  ? proyecto.descripcion.substring(0, 50) + "..."
                  : "Sin descripción"
              }</td>
              <td class="py-3 px-4 text-center">
                <a href="#" onclick="abrirModalEdicion('${proyectoId}', '${nombreLimpio}')" 
                  class="text-blue-800 hover:text-blue-400 font-medium">
                  Cambiar Nombre
                </a>
              </td>
              <td class="py-3 px-4 text-center">
                <button onclick="eliminarProyecto('${proyectoId}')" class="text-red-800 hover:text-red-400 font-medium">Eliminar</button>
              </td>
            </tr>
          `;
      })
      .join("");

    projectsTableBody.innerHTML = htmlFilas;
  }

  //Modal de edición

  // Función global para abrir el modal (llamada desde el onclick)
  window.abrirModalEdicion = function (id, nombreActual) {
    if (editModal) {
      // Al abrir, guardamos el ID en el input oculto del modal
      editProjectIdInput.value = id;
      newProjectNameInput.value = nombreActual;

      editModal.classList.remove("hidden");
      editModal.classList.add("flex");
    }
  };

  // Cerrar modal al hacer clic en X o fuera
  if (closeModalButton) {
    closeModalButton.addEventListener("click", () => {
      editModal.classList.add("hidden");
      editModal.classList.remove("flex");
    });
  }

  if (editModal) {
    editModal.addEventListener("click", (e) => {
      if (e.target === editModal) {
        editModal.classList.add("hidden");
        editModal.classList.remove("flex");
      }
    });
  }

  // Manejar el envío del formulario de edición
  if (editProjectForm) {
    editProjectForm.addEventListener("submit", function (e) {
      e.preventDefault();

      const nuevoNombre = newProjectNameInput.value.trim();
      const idProyecto = editProjectIdInput.value;

      if (nuevoNombre && idProyecto) {
        console.log(
          "Enviando modificación -> ID:",
          idProyecto,
          "Nombre:",
          nuevoNombre
        );
        cambiarNombreProyecto(idProyecto, nuevoNombre);
      } else {
        mostrarToast(
          "error",
          "No se encuentra el ID del proyecto o el nombre está vacío."
        );
        console.error("ID actual:", idProyecto);
      }
    });
  }

  // Función de la API para cambiar el nombre
  function cambiarNombreProyecto(id, nuevoNombre) {
    const url = `${API_BASE_URL}/proyectos/modificar/${id}`;

    // El cuerpo solo contiene el nombre, tal como confirmaste con Postman
    const data = {
      nombre: nuevoNombre,
    };

    fetch(url, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (response.ok) {
          editModal.classList.add("hidden");
          editModal.classList.remove("flex");
          mostrarToast(
            "success",
            `Nombre del proyecto ${id} actualizado correctamente.`
          );
          cargarProyectos(); // Recargar la lista
        } else {
          return response.json().then((err) => {
            throw new Error(
              err.message ||
                `Fallo al actualizar el proyecto. Estado: ${response.status}`
            );
          });
        }
      })
      .catch((error) => {
        console.error("Error al cambiar el nombre:", error);
        mostrarToast("error", "Error al actualizar el nombre del proyecto");
      });
  }

  // Hacemos 'eliminarProyecto' global para que funcione con el 'onclick'
  window.eliminarProyecto = function (id) {
    if (
      confirm(
        `¿Estás seguro de que quieres eliminar el proyecto con ID ${id}? Esta acción es irreversible.`
      )
    ) {
      const url = `${API_BASE_URL}/proyectos/eliminar/${id}`;

      fetch(url, {
        method: "DELETE",
      })
        .then((response) => {
          if (response.ok) {
            mostrarToast("success", `Proyecto con ID ${id} eliminado correctamente.`);
            // Llama a la función para recargar la tabla de proyectos
            cargarProyectos();
          } else {
            // Intenta leer el texto de la respuesta en caso de error
            return response.text().then((text) => {
              throw new Error(
                text ||
                  `Fallo al eliminar el proyecto. Estado: ${response.status}`
              );
            });
          }
        })
        .catch((error) => {
          console.error("Error al eliminar el proyecto:", error);
          // Muestra el error en el toast
          mostrarToast("error", "Error al eliminar el proyecto.");
        });
    }
  };

  // Función para obtener el ID del usuario logueado
  function obtenerIdUsuarioLogueado() {
    try {
      const usuarioData = localStorage.getItem("usuarioLogueado");
      if (usuarioData) {
        const usuario = JSON.parse(usuarioData);

        // Asume que el ID del usuario está bajo la propiedad 'id' o 'idUsuario'
        return usuario.id || usuario.id_usuario;
      }
    } catch (e) {
      console.error(
        "Error al parsear o leer el usuario logueado de localStorage:",
        e
      );
    }
    return null;
  }

  //Función para obtener la fecha actual para pasarlo como campo al crear un proyecto
  function obtenerFechaActual() {
    const hoy = new Date();
    const anio = hoy.getFullYear();

    // getMonth() es base 0, por eso se suma 1
    const mes = String(hoy.getMonth() + 1).padStart(2, "0");
    const dia = String(hoy.getDate()).padStart(2, "0");
    return `${anio}-${mes}-${dia}`;
  }

  // Lógica para la creación de un nuevo proyecto
  if (creationForm) {
    creationForm.addEventListener("submit", function (e) {
      e.preventDefault();
      const creadoPor = obtenerIdUsuarioLogueado();

      if (!creadoPor) {
        mostrarToast("error","Error: No se pudo obtener el ID del usuario logueado. Por favor, inicia sesión de nuevo."
        );
        return; // Detiene el envío si no hay ID
      }

      //Establece el valor de la fecha de creación antes de enviar
      const fechaActual = obtenerFechaActual();
      fechaCreacionInput.value = fechaActual;

      // Deshabilita el botón mientras se procesa la solicitud
      createProjectBtn.disabled = true;
      createProjectBtn.textContent = "Creando...";
      const nuevoProyecto = {
        nombre: nombreInput.value,
        descripcion: descripcionTextarea.value,
        fechaCreacion: fechaActual,
        creadoPor: creadoPor,
      };

      fetch(`${API_BASE_URL}/proyectos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoProyecto),
      })
        .then((response) => {
          if (!response.ok) {
            return response.json().then((err) => {
              throw new Error(
                err.message ||
                  `Fallo al crear el proyecto. Estado: ${response.status}`
              );
            });
          }
          return response.json();
        })
        .then((data) => {
          console.log("Proyecto creado exitosamente:", data);
          mostrarToast("success", `Proyecto ${data.nombre} creado con éxito.`);

          // Redirige a la lista de proyectos después de la creación
          window.location.href = "listadoProyectos.html";
        })
        .catch((error) => {
          console.error("Error al crear el proyecto:", error);
          mostrarToast("error", `Error al crear el proyecto: ${error.message}`);
        })
        .finally(() => {
          // Vuelve a habilitar el botón
          createProjectBtn.disabled = false;
          createProjectBtn.textContent = "Crear Proyecto";
        });
    });
  }

  // Inicia la carga de proyectos al cargar la página
  if (projectsTableBody) {
    cargarProyectos();
  }
});