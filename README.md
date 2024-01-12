# FuelAndFitness

# Template Installation
## Pre Initialization
You'll need to make a personal token that has workflow permissions.

Add this token to the actions, environments secrets

`REPO_SETUP_TOKEN=<your token>`

Additionally, edit the cookiecutter.json file to specify your repository
name.  

Pushing up this cookiecutter.json file change will kickoff the workflow
that starts cookiecutter.

## Repository Setup
Create a container registry at canister.io.  Make sure that the 
registry name matches the service_slug name.

make sure to add the actions/repository secrets
`CANISTER_USERNAME=<canister username>`
`CANISTER_PASSWORD=<canister password>`

