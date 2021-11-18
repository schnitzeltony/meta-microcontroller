SUMMARY = "Official KiCad Footprint Libraries for KiCad"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=71e4b3c2ee1c46466bb28bf761e16b72"

inherit cmake-allarch

SRC_URI = "git://gitlab.com/kicad/libraries/${BPN}.git;branch=v5"
SRCREV = "2008696713910875e0da3ac805adc2b3e81ac56c"
PV = "5.1.12"
S = "${WORKDIR}/git"

FILES:${PN} = " \
    ${datadir}/kicad/modules \
    ${datadir}/kicad/template \
"
