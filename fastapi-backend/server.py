from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, HttpUrl
import requests
from bs4 import BeautifulSoup
from utils import summarize

app = FastAPI()

class URLRequest(BaseModel):
    url: HttpUrl

@app.post("/summarise")
def extract_text(request: URLRequest):
    url = request.url
    try:
        response = requests.get(url, verify=False)
        response.raise_for_status()
    except requests.exceptions.RequestException as e:
        raise HTTPException(status_code=400, detail=str(e))

    soup = BeautifulSoup(response.content, "html.parser")
    text = soup.get_text(separator=' ')
    cleaned_text = ' '.join(text.split())
    summarised_text = summarize(cleaned_text)

    return {"url": url, "text": cleaned_text, "summary": summarised_text}
