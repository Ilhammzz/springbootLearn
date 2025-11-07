import { SubjectAPI } from '../services/subject-api.js';
import { ModalManager } from '../utils/modal-manager.js';
import { SubjectForm } from '../components/subject-form.js';
import { SubjectTable } from '../components/subject-table.js';

/**
 * Main controller that orchestrates all components
 */
export class SubjectManager {
  constructor() {
    this.upsertModal = new ModalManager("upsertModal");
    this.deleteModal = new ModalManager("deleteModal");
    this.form = new SubjectForm(".upsert-form");
    this.table = new SubjectTable(
      "tbody",
      (id) => this.handleEdit(id),
      (id) => this.handleDelete(id)
    );
    this.currentDeleteId = null;
    this.init();
  }

  init() {
    this.setupEventListeners();
    this.loadData();
  }

  setupEventListeners() {
    // Create button
    const createBtn = document.querySelector(".create-btn");
    createBtn?.addEventListener("click", () => this.handleCreate());

    // Upsert form submit
    const upsertSubmitBtn = document.querySelector("#upsertSubmitBtn");
    upsertSubmitBtn?.addEventListener("click", (e) => {
      e.preventDefault();
      this.handleUpsertSubmit();
    });

    // Delete form submit
    const deleteSubmitBtn = document.querySelector("#deleteConfirmBtn");
    deleteSubmitBtn?.addEventListener("click", (e) => {
      e.preventDefault();
      this.handleDeleteConfirm();
    });
  }

  async loadData() {
    try {
      const pageData = await SubjectAPI.getAll();
      this.table.render(pageData);
    } catch (error) {
      alert("Failed to load subjects. Please try again.");
    }
  }

  handleCreate() {
    this.form.reset();
    this.upsertModal.open();
  }

  async handleEdit(id) {
    try {
      const subject = await SubjectAPI.getById(id);
      if (subject) {
        this.form.setData(subject);
        this.upsertModal.open();
      }
    } catch (error) {
      alert(`Failed to load subject: ${error.message}`);
    }
  }

  handleDelete(id) {
    this.currentDeleteId = id;
    const deleteIdInput = document.querySelector("#deleteForm #idDelete");
    if (deleteIdInput) deleteIdInput.value = id;
    this.deleteModal.open();
  }

  async handleUpsertSubmit() {
    const validation = this.form.validate();
    
    if (!validation.isValid) {
      alert("Validation errors:\n" + validation.errors.join("\n"));
      return;
    }

    const formData = this.form.getData();

    try {
      const existing = await SubjectAPI.getById(formData.id);

      if (existing) {
        await SubjectAPI.update(formData.id, formData);
        alert("Subject updated successfully!");
      } else {
        await SubjectAPI.create(formData);
        alert("Subject created successfully!");
      }

      this.upsertModal.close();
      this.loadData();
    } catch (error) {
      alert(`Failed to save subject: ${error.message}`);
    }
  }

  async handleDeleteConfirm() {
    if (!this.currentDeleteId) return;

    try {
      await SubjectAPI.delete(this.currentDeleteId);
      alert("Subject deleted successfully!");
      this.deleteModal.close();
      this.currentDeleteId = null;
      this.loadData();
    } catch (error) {
      alert(`Failed to delete subject: ${error.message}`);
    }
  }
}