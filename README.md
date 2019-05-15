This application was created for the purpose of finding locations referenced in a piece of text, including some terms related to them in order to recommend content to a user. Three components are at work here.

The first component is an H2 database with a small table of a users column storing the users’ emails and a second column storing the sequence of text that they may need recommendations for. In order to grab a piece of text and pass it to component #2, the database is stored locally with some fake data and has an established connection with the application.

The next piece is a simple noun-recognizer. It uses Open-NLP’s model built for detecting nouns representing locations. After the text is split into an array of words, each word needs to be tokenized and passed to the model in order to detect any results matching a location.

The last ingredient of this project is Princeton’s Wordnet database, which the application is also connected to. Wordnet is more than a dictionary on its own. It pairs words with many other related terms, helping the application offer a better content-search capability and offering the user more recommendation options.

Requirements:
