SUMMARY = "KiCad project templates (and worksheets)"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=71e4b3c2ee1c46466bb28bf761e16b72"

inherit cmake-allarch

SRC_URI = "git://gitlab.com/kicad/libraries/${BPN}.git;branch=v5"
SRCREV = "9cd81a8918ae4bf5229c65f890361b7e4240a840"
PV = "5.1.12"
S = "${WORKDIR}/git"

FILES:${PN} = " \
    ${datadir}/kicad/template \
"
