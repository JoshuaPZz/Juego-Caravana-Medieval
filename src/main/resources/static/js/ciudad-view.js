document.addEventListener("DOMContentLoaded", function () {
  console.log("DOM completamente cargado. Iniciando script...");

  // Obtener todos los encabezados de los dropdowns
  const dropdownHeaders = document.querySelectorAll(".dropdown-header");

  if (dropdownHeaders.length === 0) {
    console.error("No se encontraron elementos con la clase .dropdown-header");
    return;
  }

  dropdownHeaders.forEach((header) => {
    header.addEventListener("click", function () {
      console.log("Dropdown clickeado");
      const dropdown = this.closest(".dropdown-section");

      if (!dropdown) {
        console.error("No se encontró el contenedor .dropdown-section");
        return;
      }

      // Alternar la clase 'active' en el dropdown
      dropdown.classList.toggle("active");

      // Encontrar el contenido y alternar su visibilidad
      const dropdownContent = dropdown.querySelector(".dropdown-content");
      if (dropdown.classList.contains("active")) {
        dropdownContent.style.display = "block";
      } else {
        dropdownContent.style.display = "none";
      }

      console.log(
        `Dropdown ${
          dropdown.classList.contains("active") ? "abierto" : "cerrado"
        }`
      );
    });
  });

  // Cerrar dropdowns cuando se hace clic fuera de ellos
  document.addEventListener("click", function (e) {
    if (!e.target.closest(".dropdown-section")) {
      console.log("Clic fuera del dropdown. Cerrando todos los dropdowns...");
      document.querySelectorAll(".dropdown-section").forEach((dropdown) => {
        dropdown.classList.remove("active");
        const dropdownContent = dropdown.querySelector(".dropdown-content");
        dropdownContent.style.display = "none";
      });
    }
  });
});
