# API-CP Template Repository

This is a template repository for Common Platform (CP) APIs in HMCTS. It defines naming conventions, structure, and validation tooling for OpenAPI specifications.

> 🔗 API definitions should follow the [HMCTS RESTful API Standards](https://hmcts.github.io/restful-api-standards/).  
> 📘 See [APIVERSIONING.md](./APIVERSIONING.md) for our CP API versioning strategy based on media types and SemVer.

## Naming Convention

> NOTE: Avoid using terms like `common, core, base, utils, helpers, misc, or shared`.
> These names often allow for ambiguous ownership and quickly become black holes where cohesion goes to die.

Repository names follow a pattern from generic to specific:

```
api-cp-[case-type]-{product-domain}-{name-of-entity}
```
The optional `case-type` parameter could be:

* civil 
* crime 
* family 
* tribunal

HMCTS manages all Civil, Criminal, Family (separate from civil), and Tribunal cases.

### Reference Data Repositories

Reference data APIs use the following naming format:

```
api-cp-refdata-{product-domain}-{name-of-entity}
```
Some might argue that `product-domain` should be optional for reference data, placing it under global ownership. But global ownership often means no ownership — and no accountability. Therefore, `product-domain` is **required**.

## OpenAPI Specification Validation

This repository includes a GitHub Action to validate OpenAPI specifications. The schema is sourced via the environment variable `OAPI_SCHEMA_URL`, or defaults to the official 3.1 schema.

### Run Validation Locally

> Python must be installed on your machine.

Install dependencies:
```bash
pip install -r .github/scripts/requirements.txt
```

Validate the OpenAPI specification:
```bash
python ./.github/scripts/validate_openapi.py ./openapi
```
Or test with example files:
```bash
python ./.github/scripts/validate_openapi.py ./.github/examples
```

## Post-Template Cleanup

After using this template to create your repository, the following files are no longer needed and **should be deleted**:

- `./APIVERSIONING.md`
- `.github/examples/invalid-openapi-spec.yml`
- `.github/examples/test-openapi-spec.yml`

Update the `./README.md` to reflect the context of the new created repository

The following file is used to import a repository ruleset.  
Once the ruleset has been successfully imported via GitHub Settings, this file **should be deleted**:

- `.github/rulesets/master.json`

To import the ruleset, follow GitHub’s instructions here:  
👉 [Importing a ruleset](https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/managing-rulesets/managing-rulesets-for-a-repository#importing-a-ruleset)

## License

This project is licensed under the [MIT License](LICENSE).

