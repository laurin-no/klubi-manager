# klubi-manager
Simple web app to manage members of a club

## How to run

`sbt run`

## Possible Improvements

### Backend

* The Alpakka Slick connector could be used to fully utilize streams on the database level: [Docs](https://doc.akka.io/docs/alpakka/current/slick.html)
* Investigate future-proofness of the chunked response format -> HTTP 1.1 only

### Frontend

* Parsing of concatenated JSON...
* mixing the `fetch` API and `axios` in one service feels wrong

### Tooling

* stopping the run command does not stop the frontend
* frontend execution can get stuck when running it via sbt
* adding a command for just running the backend