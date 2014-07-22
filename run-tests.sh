#!/bin/bash

# Compile and install
for ARTIFACT_ID in zucchini-parent zucchini-bdd zucchini-web zucchini-web-junit zucchini-examples; do
	cd $ARTIFACT_ID && \
	mvn clean install -DskipTests || exit 1
	cd ..
done

# Test run
for ARTIFACT_ID in zucchini-parent zucchini-bdd; do
	cd $ARTIFACT_ID && \
	mvn test || exit 1
	cd ..
done