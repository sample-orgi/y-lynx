# Use an official base image
FROM python:3.11-slim

# Set working directory
WORKDIR /app

# Copy files
COPY . .

# Install dependencies (if you have a requirements.txt)
RUN pip install --no-cache-dir -r requirements.txt

# Default command
CMD ["python", "main.py"]
