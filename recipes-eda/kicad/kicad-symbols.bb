SUMMARY = "Official KiCad schematic symbol libraries"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=71e4b3c2ee1c46466bb28bf761e16b72"

inherit cmake-allarch

SRC_URI = "git://gitlab.com/kicad/libraries/${BPN}.git;branch=v5"
SRCREV = "b2ba465571683b716a25a583bf1dd8bdc9680de8"
PV = "5.1.12"
S = "${WORKDIR}/git"

FILES:${PN} = " \
    ${datadir}/kicad/library \
    ${datadir}/kicad/template \
"
