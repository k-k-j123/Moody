Moody is a simple note taking application that uses AI - sentiment analysis. 
It uses vader library of python to analyze each note and categorized them into 3 sentiments namely positive, neutral and negative.
---
### Technical Details:
* The Spring boot backend runs on port 8080 which is the main server and communicates with the vader server running on port 5000
* The vader server and spring boot server has to be run separately make sure to install all the libraries for python mentioned in "requirements.txt" folder 
* The front end is inside in the resources folder
---
### Future Improvements:
* adding a dashboard that will show the overall mood of the user over the time.
* a feature that will analyze the mood data over a period and identify triggers for bad mood.
* displaying mindfullness tips and daily quotes.
---
### Fork Instructions:
* feel free to fork the project and add new features to the project.
* It will be really appreciated if you can integrate the AI into the spring boot server itself so that there will be no need to run two separate servers.
