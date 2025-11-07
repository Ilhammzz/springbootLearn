import { CONFIG } from '../config/config.js';

/**
 * Service layer for Subject API operations
 */
export class SubjectAPI {
  static async getAll(page = 0, size = CONFIG.pagination.defaultSize) {
    try {
      const response = await fetch(
        `${CONFIG.apiUrl}${CONFIG.endpoints.subjects}?page=${page}&size=${size}`
      );
      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
      return await response.json();
    } catch (error) {
      console.error("Error fetching subjects:", error);
      throw error;
    }
  }

  static async getById(id) {
    try {
      const response = await fetch(
        `${CONFIG.apiUrl}${CONFIG.endpoints.subjects}/${id}`
      );
      if (!response.ok) {
        if (response.status === 404) return null;
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return await response.json();
    } catch (error) {
      console.error(`Error fetching subject ${id}:`, error);
      throw error;
    }
  }

  static async create(data) {
    try {
      const response = await fetch(
        `${CONFIG.apiUrl}${CONFIG.endpoints.subjects}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data)
        }
      );
      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
      return await response.json();
    } catch (error) {
      console.error("Error creating subject:", error);
      throw error;
    }
  }

  static async update(id, data) {
    try {
      const response = await fetch(
        `${CONFIG.apiUrl}${CONFIG.endpoints.subjects}/${id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data)
        }
      );
      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
      return await response.json();
    } catch (error) {
      console.error(`Error updating subject ${id}:`, error);
      throw error;
    }
  }

  static async delete(id) {
    try {
      const response = await fetch(
        `${CONFIG.apiUrl}${CONFIG.endpoints.subjects}/${id}`,
        { method: "DELETE" }
      );
      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
      return true;
    } catch (error) {
      console.error(`Error deleting subject ${id}:`, error);
      throw error;
    }
  }
}