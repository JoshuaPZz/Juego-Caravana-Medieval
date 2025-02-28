document.addEventListener("DOMContentLoaded", function () {
  // Funcionalidad para los dropdowns
  document.querySelectorAll(".dropdown-header").forEach((header) => {
    header.addEventListener("click", function () {
      const dropdown = this.parentElement;
      dropdown.classList.toggle("active");

      // Cerrar otros dropdowns
      document.querySelectorAll(".dropdown").forEach((otherDropdown) => {
        if (otherDropdown !== dropdown) {
          otherDropdown.classList.remove("active");
        }
      });
    });
  });

  // Cerrar dropdowns al hacer click fuera
  document.addEventListener("click", function (e) {
    if (!e.target.closest(".dropdown")) {
      document.querySelectorAll(".dropdown").forEach((dropdown) => {
        dropdown.classList.remove("active");
      });
    }
  });
});
