import React, { useState } from 'react';
import axios from 'axios';

const API_ENDPOINT = 'http://localhost:5000/api/summary/add';

function UrlForm() {
  const [url, setUrl] = useState('');
  const [summary, setSummary] = useState('');
  const [loading, setLoading] = useState(false); // Add loading state

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true); // Set loading to true when starting request
    try {
      const response = await axios.post(API_ENDPOINT, { url });
      setSummary(response.data.summary);
    } catch (error) {
      console.error("There was an error!", error);
    } finally {
      setLoading(false); // Set loading to false when request completes
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <h2 className="mb-4 text-center">URL Form</h2>
          <form onSubmit={handleSubmit} className="mb-4">
            <div className="form-group">
              <input
                type="text"
                value={url}
                onChange={(e) => setUrl(e.target.value)}
                className="form-control"
                placeholder="Enter URL"
                required
              />
            </div>
            <div className="text-center">
              <button type="submit" className="btn btn-primary btn-lg mt-3">
                {loading ? 'Fetching...' : 'Summarize'}
              </button>
            </div>
          </form>
          {summary && (
            <div className="mt-5">
              <h3>Summary</h3>
              <div className="card">
                <div className="card-body">
                  <p>{summary}</p>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default UrlForm;
