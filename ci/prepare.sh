#!/bin/bash
if [ "$CI_BUILD_STAGE" == "main" ]
then
	apt-get install p7zip-full tree
	java -version
fi