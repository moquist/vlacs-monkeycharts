# vlacs-monkeycharts

These are some relataively simple charts for VLACS, in a terrifically unifinished state.

N.B.: If you're not at VLACS, this project is not useful for you at all at this point. Eventually this project will generate sample data, and at that point it might be interesting as an example for someone else.

## To make it go at this early stage:

1. ```cp config-db-dist.edn config-db.edn```
1. Edit ```config-db.edn``` to have the correct values for your VLACS database.
1. ```rlwrap lein figwheel```
1. Load up http://localhost:3449
  1. Edit the following locations if you need to use a different host and/or port:
  
    In ```project.clj```, edit :figwheel :server-port.
  
    In ```dev_src/monkeycharts/dev.cljs```, edit :websocket-url.
