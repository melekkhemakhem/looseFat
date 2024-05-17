#!/bin/bash

# Emplacement où les licences doivent être stockées
ANDROID_HOME=${ANDROID_HOME:-$PWD/android-sdk-linux}
mkdir -p $ANDROID_HOME/licenses

# Contenu des licences acceptées
echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > $ANDROID_HOME/licenses/android-sdk-license
echo "d56f5187479451eabf01fb78af6dfcb131a6481e" >> $ANDROID_HOME/licenses/android-sdk-license
echo "24333f8a63b6825ea9c5514f83c2829b004d1fee" >> $ANDROID_HOME/licenses/android-sdk-license

echo "d56f5187479451eabf01fb78af6dfcb131a6481e" > $ANDROID_HOME/licenses/android-googletv-license
