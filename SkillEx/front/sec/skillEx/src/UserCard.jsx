import React from "react";
import "./UserCard.css"; // optional for styling

export default function UserCard({ user }) {
  return (
    <div className="user-card">
      <h3>{user.username}</h3>
      <p><strong>Skill:</strong> {user.skillName}</p>
      <p><strong>Category:</strong> {user.category}</p>
      <p><strong>Description:</strong> {user.description || "No description"}</p>
      <p><strong>Rating:</strong> {user.rating}</p>
    </div>
  );
}
