SUMMARY = "Official KiCad 3D model libraries for rendering and MCAD integration"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=71e4b3c2ee1c46466bb28bf761e16b72"

inherit cmake-allarch

SRC_URI = "git://gitlab.com/kicad/libraries/kicad-packages3D.git;branch=v5"
SRCREV = "9bc104e7a607ad66e63c19aa095b640f406e2300"
PV = "5.1.12"
S = "${WORKDIR}/git"

FILES:${PN} = " \
    ${datadir}/kicad/modules \
"
