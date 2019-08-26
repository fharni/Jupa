#!/bin/bash
rm -rf Jupa
git clone -b wildlfy https://github.com/fharni/Jupa

cd Jupa
mvn clean package -DskipTests

cp /Jupa/target/*.war /target/
