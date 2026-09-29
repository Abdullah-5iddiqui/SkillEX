import React,{useState} from "react";
import "./UserCard.css";


const UserCard = ({ user }) => {
  const [requestSent, setRequestSent] = useState(false);

  const handleConnect = () => {
    setRequestSent(true);
  };

  return (
    <div className="user-card">
      <h3>{user.username}</h3>
      <p><strong>Average Rating:</strong> {user.rating?.toFixed(2) || "N/A"}</p>

      {user.skills && user.skills.length > 0 ? (
        user.skills.map((skill, idx) => (
          <div key={idx} className="skill-block">
            <p><strong>Skill:</strong> {skill.name}</p>
            <p><strong>Category:</strong> {skill.category}</p>
            <p>
              <strong>Type:</strong>{" "}
              <span
                className={`skill-type ${
                  skill.type.toLowerCase() === "offer" ? "offer" : "learn"
                }`}
              >
                {skill.type}
              </span>
            </p>
          </div>
        ))
      ) : (
        <p>No skills found</p>
      )}
      <button onClick={handleConnect}
  disabled={requestSent}
  className={`mt-auto px-4 py-1 rounded transition-colors duration-200 focus:outline-none
    ${requestSent
      ? "bg-gray-400 text-white cursor-not-allowed"
      : "bg-green-500 text-white hover:bg-green-600 hover:border-white border border-transparent"}`}
>   {requestSent ? 'Request Sent' : 'Connect'}
</button>

    </div>
  );
}
export default UserCard;
