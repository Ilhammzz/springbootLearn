/**
 * Manages table rendering and interactions
 */
export class SubjectTable {
  constructor(tableBodySelector, onEdit, onDelete) {
    this.tbody = document.querySelector(tableBodySelector);
    this.tfoot = document.querySelector("tfoot");
    this.onEdit = onEdit;
    this.onDelete = onDelete;
  }

  render(pageData) {
    if (!this.tbody) return;

    const rows = pageData.content.map(subject => `
      <tr>
        <td>
          <button class="btn upsert-btn update-btn" data-id="${subject.id}">Edit</button>
          <button class="btn delete-btn" data-id="${subject.id}">Delete</button>
        </td>
        <td>${subject.id}</td>
        <td>${subject.name}</td>
        <td>${subject.credit}</td>
        <td>${subject.price}</td>
        <td>${subject.active}</td>
      </tr>
    `).join("");

    this.tbody.innerHTML = rows;
    this.attachEventListeners();
    this.renderPagination(pageData.page);
  }

  attachEventListeners() {
    // Edit buttons
    this.tbody.querySelectorAll(".update-btn").forEach(btn => {
      btn.addEventListener("click", () => {
        const id = btn.getAttribute("data-id");
        this.onEdit(id);
      });
    });

    // Delete buttons
    this.tbody.querySelectorAll(".delete-btn").forEach(btn => {
      btn.addEventListener("click", () => {
        const id = btn.getAttribute("data-id");
        this.onDelete(id);
      });
    });
  }

  renderPagination(pageData) {
    if (!this.tfoot) return;

    const currentPage = pageData.number + 1;
    const totalPages = pageData.totalPages;

    const currentPageEl = this.tfoot.querySelector(".current-page");
    const lastPageEl = this.tfoot.querySelector(".last-page");

    if (currentPageEl) currentPageEl.textContent = currentPage;
    if (lastPageEl) lastPageEl.textContent = totalPages;
  }
}