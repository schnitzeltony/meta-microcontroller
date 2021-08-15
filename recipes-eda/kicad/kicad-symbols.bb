SUMMARY = "Official KiCad schematic symbol libraries"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=166ee6492e38d745862bcc66892f2c44"

inherit cmake-allarch

SRC_URI = "git://gitlab.com/kicad/libraries/${BPN}.git;branch=v5"
SRCREV = "6dec5004b6a2679c19d4857bda2f90c5ab3a5726"
PV = "5.1.9"
S = "${WORKDIR}/git"

FILES:${PN} = " \
    ${datadir}/kicad/library \
    ${datadir}/kicad/template \
"
