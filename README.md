###  The Challenge: ### 

Given a book in a text file (http://www.loyalbooks.com/download/text/Railway-Children-by-E-
Nesbit.txt)
1. Write an application that outputs the individual words that appear in the book, and how many times that word appears in the text file.

2. The second part is to also output whether the number of times each word appears is a prime number.

###  Assumptions: ### 

* Text Parsing: Punctuation and capitalisation is ignored
* Text Parsing: Roman numerals are considered as words
* Text Parsing & UI: The order of the list of words presented to the user is irrelevant
* Text Parsing: As this sample project is parsing a book, we know that it won’t change so an ALWAYS access caching strategy has been implemented. If future work includes changing text, then other caching strategies will have to be implemented. 

### The Solution: ### 

If the user is entering the app for the very first time, they will be welcomed with a loading spinner and a Snackbar banner stating that “Hard work is in Progress!”. The application fetches the Response from the above url and stores it as a file (in case we need it again for further processing). Then each unique word is mapped to its frequency (which is used to calculate whether the frequency is a prime number or not). The results* is then displayed to the user and stored locally in a Room database. 

The next time that the user accesses the application, the results are loaded from the database as they come out through an Observable. The application first tries to fetch data from the local source, if it is empty, it then retrieves the data from the remote source. 


*Words displayed on a light purple card are Prime numbers, words on teal cards are not Prime. 


### A word from the developer:### 

Thank you for taking the time to inspect this project. 
I am always looking to improve and challenge my own ways of tackling problems, so I appreciate any and all constructive feedback (positive and negative).

You probably have noticed some scaffolding classes and some extension functions that have not been used. I purposefully left this in order to demonstrate the scalability of the project as well as considerations made; i.e should the useCase return a Single, Observable, or a Completable? 

### Future Work:### 

* The app could definitely do with a glow up; this would include using more Material Design principles, styles and a dark mode version. 
* Each WordDAO would have to include a reference to its source when the application gets scaled to parse more sources of text. 
* Fetch a list of books (or upload their own text) for the user to select from and then retrieve the text and parse.
* The app’s security was not a high priority in the given time-frame, therefore more should be added to the Proguard-rules. 
* The algorithms related to processing the text could be better researched to improve efficiencies and the UX. 
* Rather than the processed text from the remote source being a Single, it should be an Observable so that the user sees results as they are being calculated. 
* There’s a bunch of dependencies that I copied over from another project, so some will need to be deleted or upgraded. 
* And as always there can always be more tests. 
