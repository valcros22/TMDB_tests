# TMDB_tests
API tests for TMDB page

Run command line example (maven should be properly installed, JAVA_HOME environment variable should be properly set)

mvn clean -DapiUrl="https://api.themoviedb.org/" -DapiKey="8bbd4567a4d112020fcd63f86a0a70c6" -Dsurefire.suiteXmlFiles=src/test/java/com/tmdb/suites/test_suite_genres.xml test
