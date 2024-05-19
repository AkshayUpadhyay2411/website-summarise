import React, { useState, useEffect } from 'react';
import axios from 'axios';

const API_ENDPOINT = 'http://localhost:5000/api/summary/all';

function History() {
  const [history, setHistory] = useState([
    { url: 'example.com', summary: 'This is a summary for example.com.' },
    { url: 'sample.com', summary: 'Summary for sample.com goes here.' },
    { url: 'test.com', summary: 'Test.com summary will be displayed here.' },
  ]);

  useEffect(() => {
    fetchHistory(); 
  }, []);

  async function fetchHistory() {
    try {
      const response = await axios.get(API_ENDPOINT);
      setHistory(response.data);
    } catch (error) {
      console.error("There was an error fetching the history!", error);
    }
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <h2 className="mb-4 text-center">History</h2>
          <div className="table-responsive">
            <table className="table table-striped">
              <thead>
                <tr>
                  <th scope="col">URL</th>
                  <th scope="col">Summary</th>
                </tr>
              </thead>
              <tbody>
                {history.map((item, index) => (
                  <tr key={index}>
                    <td>{item.url}</td>
                    <td>{item.summary}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default History;
