import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import "./App.css";
import Home from "./components/Home";
import UrlForm from "./components/UrlForm";
import History from "./components/History";

function App() {
  return (
    <Router>
      <div className="App">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid">
            <Link className="navbar-brand" to="/">
              Summarizer
            </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav ms-auto">
                <li className="nav-item mx-2">
                  <Link className="nav-link" to="/url-form">
                    URL Form
                  </Link>
                </li>
                <li className="nav-item mx-2">
                  <Link className="nav-link" to="/history">
                    History
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <div className="container mt-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/url-form" element={<UrlForm />} />
            <Route path="/history" element={<History />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
