export class SubjectForm {
  constructor(formSelector) {
    this.form = document.querySelector(formSelector);
    this.fields = {
      id: this.form?.querySelector("#idUpsert"),
      name: this.form?.querySelector("#nameUpsert"),
      description: this.form?.querySelector("#descriptionUpsert"),
      credit: this.form?.querySelector("#creditUpsert"),
      price: this.form?.querySelector("#priceUpsert"),
      level: this.form?.querySelector("#levelUpsert"),
      active: this.form?.querySelector("#activeUpsert"),
    };
  }

  getData() {
    return {
      id: this.fields.id?.value.trim().toUpperCase() || "",
      name: this.fields.name?.value.trim() || "",
      description: this.fields.description?.value.trim() || "",
      credit: parseInt(this.fields.credit?.value) || 0,
      price: parseFloat(this.fields.price?.value) || 0,
      level: this.fields.level?.value || "",
      active: this.fields.active?.checked || false,
    };
  }

  setData(data) {
    if (this.fields.id) {
      this.fields.id.value = data.id || "";
      this.fields.id.disabled = !!data.id;
    }
    if (this.fields.name) this.fields.name.value = data.name || "";
    if (this.fields.description)
      this.fields.description.value = data.description || "";
    if (this.fields.credit) this.fields.credit.value = data.credit || "";
    if (this.fields.price) this.fields.price.value = data.price || "";
    if (this.fields.level) this.fields.level.value = data.level || "";
    if (this.fields.active) this.fields.active.checked = !!data.active; 
  }

  reset() {
    this.form?.reset();
    if (this.fields.id) this.fields.id.disabled = false;
    if (this.fields.active) this.fields.active.checked = false; 
  }

  validate() {
    const data = this.getData();
    const errors = [];

    if (!data.id || data.id.length !== 2) {
      errors.push("ID must be exactly 2 characters");
    }
    if (!data.name || data.name.length === 0) {
      errors.push("Name is required");
    }
    if (data.name.length > 50) {
      errors.push("Name must be less than 50 characters");
    }
    if (data.credit < 1 || data.credit > 6) {
      errors.push("Credit must be between 1 and 6");
    }
    if (data.price < 0) {
      errors.push("Price must be positive");
    }
    if (data.price > 999999.99) {
      errors.push("Price must be less than 999,999.99");
    }
    if (!data.level) {
      errors.push("Level is required");
    }

    return { isValid: errors.length === 0, errors };
  }
}
