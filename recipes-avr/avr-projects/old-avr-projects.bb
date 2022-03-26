SUMMARY = "AVR projects done very long time ago "
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=ebac4df911fae1957ac61dbc38e95ee4 \
"

inherit avr-toolchain

SRC_URI = "git://github.com/schnitzeltony/old-avr-projects.git;protocol=https;branch=wip"
SRCREV = "e737b6aea3b41baa3ba6f029c172527a8524e744"
S = "${WORKDIR}/git"
PV = "0.0.0"

do_compile() {
    cd ${S}/j-ramp/src
    base_do_compile

    cd ${S}/p-traffic-light/src
    base_do_compile
}
