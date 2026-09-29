// src/components/RateSection.jsx
import React, { useState } from "react";
import "./Rate.css"; // Optional, for custom styling

export default function RateSection() {
  const [formData, setFormData] = useState({
    username: "",
    skillName: "",
    rating: 0,
    comment: "",
  });

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const payload = {
        username: formData.username.toLowerCase(),
        skillName: formData.skillName,
        rating: formData.rating,
        comment: formData.comment,
      };

      const response = await fetch("http://localhost:8080/ratings/rate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        alert("Rating submitted successfully");
        setFormData({
          username: "",
          skillName: "",
          rating: 0,
          comment: "",
        });
      } else {
        const errorData = await response.json();
        alert("Error submitting rating: " + (errorData.message || "Unknown error"));
      }
    } catch (error) {
      alert("Error submitting rating: " + error.message);
    }
  };

  return (
    <div className="rate-section">
      <h2>Rate a User</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="skillName"
          placeholder="Skill Name"
          value={formData.skillName}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="rating"
          placeholder="Rating (1-5)"
          min="1"
          max="5"
          value={formData.rating}
          onChange={handleChange}
          required
        />
        <textarea
          name="comment"
          placeholder="Comment"
          value={formData.comment}
          onChange={handleChange}
          required
        />
        <button type="submit">Submit Rating</button>
      </form>
    </div>
  );
}
