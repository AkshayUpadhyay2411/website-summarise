import React from 'react';

function Home() {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8 text-center">
          <h1 className="display-4 mb-4">Welcome to the Website Summarizer</h1>
          <p className="lead">
            Use this tool to summarize any website by entering the URL in the form page.
          </p>
          <p>
            Navigate to the URL Form page to get started, and check the History page to view all previous summaries.
          </p>
          <a href="/url-form" className="btn btn-primary btn-lg mt-3">
            Get Started
          </a>
        </div>
      </div>
    </div>
  );
}

export default Home;
