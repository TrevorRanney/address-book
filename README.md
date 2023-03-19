# Address Book
A sample code project for an interview with an address book in React and some Kotlin command line utilities

## Building and Running:
Prerequisites for running react site: `node/npm`  
`npm run install-react-dependencies`  
`npm run build-site`  
`npm run serve-site`  

Prerequisites for converter util: `npm, java, kotlin, mvn`  
`npm run build-converter`  
`npm run convert-xml`  
`npm run convert-json`

Prerequisites for shuffle util: `npm, java, kotlin, mvn`  
`npm run build-shuffle`  
`npm run shuffle TOURNAMENT DINNER DINTOUR`  

Versions used:  
npm 6.14.11  
node v14.16.0  
java version "19.0.2" 2023-01-17  
Kotlin version 1.8.10-release-430 (JRE 19.0.2+7-44)  
apache-maven-3.9.1  


## Requirements:
### React App:
An address book application  
- The data source is supplied in an XML file ab.xml  
- The application should present the available contacts in two different views
    1. Table view

    2. Business card view, that presents several business cards on a screen

- Add search and filtering capabilities

<br>  

### Kotlin Command line utilities: 
- The data source is supplied in an XML file ab.xml  
    1. Create “Address Book” app - a command line utility which is able to:
        - convert address book xml to json
        - convert address book json to xml
        - validate schema

    2. Create “Shuffle” (see definition below) tool - a command line utility which is:
        - For a given two original words and a Shuffle*, determines whether the shuffle is
“legitimate” (i.e. produced correctly). The input of your program is a file “input.txt” or
command line arguments , which contains 3 entries (separated by a space): two original
words and the shuffle. The output of your program is a file “output.txt", which should
contain either CORRECT, if the shuffle is produced correctly from two original words, or
INCORRECT otherwise.
        - Example:
Input example 1: TOURNAMENT DINNER TOUINN
Output: INCORRECT
Input example 2: TOURNAMENT DINNER TDINOURNANMENTER
Output: CORRECT
Input example 3: TOURNAMENT DINNER DINTOUR
Output: CORRECT
        * Shuffle - a combination of two words (word is defined as any Alpha sequence) where
the letters of the shuffle come from the original words in such a way that the relative
order of the letters coming from the same word is maintained, however each letter of the
shuffle can be drawn from either of the words. For example, a shuffle of TOURNAMENT
and DINNER could be TDINOURNANMENTER. The shuffle would essentially weave
two original words together, but does not have to utilize all the letters.





