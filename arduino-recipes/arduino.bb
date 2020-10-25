SUMMARY = "IDE for the arduino open-source electronics platform"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://license.txt;md5=89cc74ccfabf72bc4bac6102892d2cb7"

DEPENDS = " \
    openjdk-8-native \
    ant-native \
    zip-native \
    libxslt \
"

#inherit allarch

SRC_URI = "git://github.com/arduino/Arduino.git;protocol=https"
SRCREV = "4bbd63a2ed34fb1db4f8e09be676cc9b06734205"
PV = "1.8.12"
S = "${WORKDIR}/git"

#do_configure[noexec] = "1"

ANT_CONF = " \
    -Dno_arduino_builder=true \
    -Dsystem_avr=true \
    -Dlight_bundle=true \
"

do_compile() {
    cd ${S}/build

    export JAVA=${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native/bin/java
    export JAVA_HOME=${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native
    ant -Dversion=${PV} ${ANT_CONF} -v linux32-build
}
