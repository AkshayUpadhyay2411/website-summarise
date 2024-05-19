from dotenv import load_dotenv
load_dotenv()
import os 

os.environ["GROQ_API_KEY"] = os.getenv('GROQ_API_KEY')
from langchain_core.prompts import ChatPromptTemplate
from langchain_groq import ChatGroq
from langchain_core.output_parsers import StrOutputParser

chat = ChatGroq(temperature=0, model_name="llama3-70b-8192")
output_parser = StrOutputParser()

def summarize(text):
    if len(text) > 10000:
        text = text[:10000]
    
    system = "You are a helpful assistant whose task is to summarize the text given to you."
    human = "{text}"
    prompt = ChatPromptTemplate.from_messages([("system", system), ("human", human)])

    chain = prompt | chat | output_parser
    summarised_text = chain.invoke({"text": text})
    return summarised_text

