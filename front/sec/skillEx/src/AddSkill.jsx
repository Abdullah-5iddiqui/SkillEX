// src/components/AddSkillForm.jsx
import React, { useState } from "react";
import "./AddSkill.css"

export default function AddSkillForm() {
  const [formData, setFormData] = useState({
    name: "",
    category: "",
    description: "",
    type: "Learn", // Default type is "learn"
  });

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

    const handleSubmit = async (e) => {
      e.preventDefault();
    
      try {
        // Prepare formData to send in the request
        const payload = {
          name: formData.name,
          category: formData.category,
          description: formData.description,
          type: formData.type
        };
    
        const response = await fetch(`http://localhost:8080/UserSkills/${formData.type}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            // Authorization: `Bearer ${localStorage.getItem("token")}`, // Include token for authentication
          },
          // credentials:include,
          body: JSON.stringify(payload), // Convert payload to JSON format
          credentials: "include", // Include credentials to send cookies/session info if needed
        });
    
        // Handle the response
        if (response.ok) {
          alert("Skill added successfully");
          // Reset the form
          setFormData({
            name: "",
            category: "",
            description: "",
            type:""
          });
        } else {
          // If the response status is not OK, alert the user
          const errorData = await response.json();
          alert("Error adding skill: " + (errorData.message || "Unknown error"));
        }
      } catch (error) {
        alert("Error adding skill: " + error.message);
      }
    };
  
  return (
    <div className="add-skill-form">
      <h2>Add New Skill</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Skil Name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="category"
          placeholder="Category"
          value={formData.category}
          onChange={handleChange}
          required
        />
        <textarea
          name="description"
          placeholder="Description"
          value={formData.description}
          onChange={handleChange}
          required
        />
        <div className="type-selection">
          <label>
            <input
              type="radio"
              name="type"
              value="Learn"
              checked={formData.type === "Learn"}
              onChange={handleChange}
            />
            Learn
          </label>
          <label>
            <input
              type="radio"
              name="type"
              value="Offer"
              checked={formData.type === "Offer"}
              onChange={handleChange}
            />
            Offer
          </label>
        </div>
        <button type="submit">Add Skill</button>
      </form>
    </div>
  );
}
