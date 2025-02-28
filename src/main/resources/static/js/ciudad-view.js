document.addEventListener("DOMContentLoaded", function () {
  // Dropdown functionality
  document.querySelectorAll(".dropdown-header").forEach((header) => {
    header.addEventListener("click", function () {
      const dropdown = this.parentElement;
      dropdown.classList.toggle("active");
    });
  });

  // Close dropdowns when clicking outside
  document.addEventListener("click", function (e) {
    if (!e.target.closest(".dropdown-section")) {
      document.querySelectorAll(".dropdown-section").forEach((dropdown) => {
        dropdown.classList.remove("active");
      });
    }
  });
});
