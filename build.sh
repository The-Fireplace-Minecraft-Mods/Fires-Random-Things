# Set variables in and from mcmod.info
cp -p /src/main/resources/mcmod.info modversion.info
sed -i -e'/"version"/!d' modversion.info
sed -i -e 's/  "version": "//g' modversion.info
sed -i -e 's/",//g' modversion.info
VERSION="$(sed '/AAAA/d' modversion.info)"
# Begin work on the buildscript and build itself
sed -i -e s/!!VERSION!!/$VERSION/g  build.gradle
unset JAVA_OPTS
bash gradle clean build uploadToModsIO curseforge
# Cleanup
rm modversion.info