// import { useEffect, useState } from "react";
// import axios from "axios";
// import "./Dashboard.css";
// import UserCard from "./UserCard";

// export default function Dashboard() {
//   const [mySkills, setMySkills] = useState([]);
//   const [searchQuery, setSearchQuery] = useState("");
//   const [searchResults, setSearchResults] = useState([]);
//   const [newSkill, setNewSkill] = useState("");

//   // Fetch own skills on load
//   useEffect(() => {
//     axios.get("localhost:8080/user/mySkills").then((res) => {
//       setMySkills(res.data);
//     });
//   }, []);

//   const handleAddSkill = async () => {
//     if (!newSkill.trim()) return;
//     try {
//       const res = await axios.post("localhost:8080/user/mySkills", { skill: newSkill });
//       setMySkills([...mySkills, res.data]);
//       setNewSkill("");
//     } catch (e) {
//       alert("Error adding skill");
//     }
//   };

//   const handleSearch = async () => {

//     try {
//         axios.defaults.withCredentials = true;
//       const res = await axios.get(`http://localhost:8080/user/search/${searchQuery}`);
//       setSearchResults(res.data);
//     } catch (e) {
//         console.log("Search error" ,e);
//       alert("Error searching skills");
//     }
//   };

//   const handleLogout = () => {
//     // Clear token/session here
//     alert("Logged out");
//     window.location.href = "/";
//   };

//   return (
//     <div className="dashboard-container">
//       <header>
//         <h1>Welcome to SkillEx</h1>
//         <button className="logout-btn" onClick={handleLogout}>Logout</button>
//       </header>

//       <section className="my-skills">
//         <h2>My Skills</h2>
//         <ul>
//           {mySkills.map((skill, idx) => (
//             <li key={idx}>{skill.name}</li>
//           ))}
//         </ul>
//         <input
//           value={newSkill}
//           onChange={(e) => setNewSkill(e.target.value)}
//           placeholder="Add new skill"
//         />
//         <button onClick={handleAddSkill}>Add</button>
//       </section>
//       <section className="search-section">
//   <h2>Search Skills or Users</h2>
//   <input
//     value={searchQuery}
//     onChange={(e) => setSearchQuery(e.target.value)}
//     placeholder="Search..."
//   />
//   <button onClick={handleSearch}>Search</button>

//   {/* <div className="search-results">
//   {searchResults.length > 0 ? (
//     searchResults.map((user, idx) => (
//       <UserCard key={idx} user={user} />
//     ))
//   ) : (
//     <p>No results found</p>
//   )} */}
// {/* </div> */}
// <div style={{ color: "#000", background: "#fff" }}>
//   {searchResults.map((user, idx) => (
//     <UserCard key={idx} user={user} />
//   ))}
// </div>

// </section>

//     </div>
//   );
// }
  

import React, { useState } from "react";
import UserCard from "./UserCard";
import "./Dashboard.css";
import axios from "axios";
import Rate from "./Rate";
import AddSkill from "./AddSkill";
// import AddSkillForm from "./AddSkill";

export default function Dashboard() {
  const [activeTab, setActiveTab] = useState("search");
  const [searchQuery, setSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [newSkill, setNewSkill] = useState({ name: "", category: "" });
  const [rateData, setRateData] = useState({ username: "", skillName: "", rating: 0 });

  const handleLogout = () => {
    alert("Logged out");
    window.location.href = "/";
  };

  const handleSearch = async () => {
    console.log(searchQuery);
    try {
      const res = await fetch(`http://localhost:8080/user/search/${searchQuery}`, {
        credentials: "include",
      });
      const data = await res.json();
      const uniqueUsers = Array.from(new Map(data.map(user => [user.username, user])).values());
      // const userMap = new Map();

      // data.forEach(user => {
      //   // Ensure skills exist before filtering
      //   const matchingSkills = (user.skills || []).filter(skill =>
      //     skill.name.toLowerCase().includes(searchQuery.toLowerCase())
      //   );
  
      //   if (matchingSkills.length === 0) return; // Skip users without matching skills
  
      //   if (userMap.has(user.username)) {
      //     // Merge unique skills only
      //     const existingUser = userMap.get(user.username);
      //     const newSkills = existingUser.skills.concat(matchingSkills);
      //     existingUser.skills = [...new Set(newSkills.map(skill => skill.name))]; // Keep unique skills
      //   } else {
      //     userMap.set(user.username, {
      //       ...user,
      //       skills: matchingSkills,
      //     });
      //   }
      // });
  
      // setSearchResults(Array.from(userMap.values()));    // console.log(res.data);
      setSearchResults(uniqueUsers);
    } catch (err) {
        // console.log(err);
      alert("Error searching: " + err.message);
    }
  };

  return (
    <div className="dashboard">
      <header>
        <h1>SkillEx Dashboard</h1>
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </header>

      <nav className="tabs">
        <button onClick={() => setActiveTab("search")} className={activeTab === "search" ? "active" : ""}>Search</button>
        <button onClick={() => setActiveTab("add")} className={activeTab === "add" ? "active" : ""}>Add Skill</button>
        <button onClick={() => setActiveTab("rate")} className={activeTab === "rate" ? "active" : ""}>Rate</button>
      </nav>

      <section className="tab-content">
        {activeTab === "search" && (
          <div className="search-tab">
            <input
              type="text"
              placeholder="Search skills or users..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
            <button onClick={handleSearch}>Search</button>
            <div className="user-card-list">
              {searchResults.map((user, idx) => (
                <UserCard key={idx} user={user} />
              ))}
            </div>
</div>
        )}
{activeTab === "add" && <AddSkill/>}


        {activeTab === "rate" && <Rate/>}
      </section>
    </div>
  );
}
