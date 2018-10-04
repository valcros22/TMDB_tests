# TMDB_tests
API tests for TMDB page

This tests were created using TestNG, Maven, Java and Retrofit2.
To be able to run them, you will need to have maven correctly installed and JAVA_HOME varaiable correctly created.
To run this tests, you will have to provide the parameter "apiUrl" with the API main webpage and "apiKey" with a correct API key.
to a proper maven command line.
This tests should be run using the TestNG suites located in src/test/java/com/tmdb/suites.
Each suite has the parameter "apiUrl" with value "no_api_url" and "apiKey" with value "no_api_key". If you pass this 2 parameter values using maven commandline, the suite listener will replace its values with the input ones before running the tests. If one or both values are not passed to maven or they are not harcoded directly on the suite, the tests will report an error before starting denoting that one of the values is not correctly initialized.

Run command line example

mvn clean -DapiUrl="https://api.themoviedb.org/" -DapiKey="8bbd4567a4d112020fcd63f86a0a70c6" -Dsurefire.suiteXmlFiles=src/test/java/com/tmdb/suites/test_suite_regression.xml test
