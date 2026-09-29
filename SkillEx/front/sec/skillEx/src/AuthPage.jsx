import { useState } from "react";
import axios from "axios";
import "./AuthPage.css";
import { useNavigate } from "react-router-dom";


export default function AuthPage() {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
    address: "",
  });

  const toggleForm = () => {
    setIsLogin((prev) => !prev);
    setFormData({ username: "", password: "", email: "", address: "" });
  };
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData((f) => ({ ...f, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      if (isLogin) {
        const response = await axios.post("http://localhost:8080/public/login", {
          username: formData.username,
          password: formData.password,
        });
  
        alert("Login successful");
        navigate("/dashboard");
        // Save session/token if returned
        // Example: localStorage.setItem("token", response.data.token);
        // window.location.href = "/dashboard"; // redirect to dashboard
      } else {
        await axios.post("http://localhost:8080/public/register", formData);
        alert("Registration successful, now login.");
        setIsLogin(true);
      }
    } catch (error) {
      alert("Error: " + (error.response?.data || error.message));
    }
  };
  

  return (
    <div className="auth-container">
      <div className="auth-form">
        <h2>{isLogin ? "Login" : "Register"}</h2>
        <form onSubmit={handleSubmit}>
  {/* Username always shown */}
  <input
    type="text"
    name="username"
    placeholder="Username"
    value={formData.username}
    onChange={handleChange}
    required
  />

  {/* Password always shown */}
  <input
    type="password"
    name="password"
    placeholder="Password"
    value={formData.password}
    onChange={handleChange}
    required
  />

  {/* These two shown only on registration */}
  {!isLogin && (
    <>
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required
      />
      <input
        type="text"
        name="address"
        placeholder="Address"
        value={formData.address}
        onChange={handleChange}
        required
      />
    </>
  )}

  <button type="submit">{isLogin ? "Login" : "Register"}</button>
</form>

        <p onClick={toggleForm} className="toggle-link">
          {isLogin
            ? "Don't have an account? Register"
            : "Already have an account? Login"}
        </p>
      </div>
    </div>
  );
}
