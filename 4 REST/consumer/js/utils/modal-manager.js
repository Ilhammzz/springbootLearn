/**
 * Reusable modal management utility
 */
export class ModalManager {
  constructor(modalId) {
    this.modal = document.getElementById(modalId);
    this.closeBtn = this.modal?.querySelector(".upsert-close, .delete-close");
    this.init();
  }

  init() {
    if (!this.modal) return;

    // Close button click
    this.closeBtn?.addEventListener("click", () => this.close());

    // Click outside modal
    window.addEventListener("click", (e) => {
      if (e.target === this.modal) this.close();
    });
  }

  open() {
    if (this.modal) this.modal.style.display = "block";
  }

  close() {
    if (this.modal) this.modal.style.display = "none";
  }
}