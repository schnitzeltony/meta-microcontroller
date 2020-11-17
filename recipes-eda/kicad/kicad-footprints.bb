SUMMARY = "Official KiCad Footprint Libraries for KiCad"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=166ee6492e38d745862bcc66892f2c44"

inherit cmake-allarch

SRC_URI = "git://gitlab.com/kicad/libraries/${BPN}.git;branch=v5"
SRCREV = "302ac78bac21825532f970fb92714fa5973ad79b"
PV = "5.1.7"
S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${datadir}/kicad/modules \
    ${datadir}/kicad/template \
"
