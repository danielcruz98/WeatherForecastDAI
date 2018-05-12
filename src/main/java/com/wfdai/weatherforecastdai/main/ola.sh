#!/bin/bash

find ./ -iname "*.java" -type f -exec sh -c 'pandoc "${0}" -o "${0%.md}.pdf"' {} ;
