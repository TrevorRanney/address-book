# Address Book
A sample code project for an interview with an address book in React and some Kotlin command line utilities

## Building and Running:
Prerequisites for running react site: `node/npm`  
`npm run install-react-dependencies`  
`npm run build-site`  
`npm run serve-site`  

Prerequisites for converter site: `npm, java, kotlin, mvn`  
`npm run build-converter`  
`npm run convert-xml`  
`npm run convert-json`  

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
