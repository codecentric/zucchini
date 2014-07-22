#!/bin/bash

# Test run
for ARTIFACT_ID in zucchini-parent zucchini-bdd zucchini-web zucchini-web-junit zucchini-examples; do
	cd $ARTIFACT_ID && \
	mvn clean compile test package install -U
	if [ "$?" != "0" ]; then
		echo "Test run failed."
		exit $?
	fi
	cd ..
done