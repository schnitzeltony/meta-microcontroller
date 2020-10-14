SUMMARY = "Official KiCad 3D model libraries for rendering and MCAD integration"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=166ee6492e38d745862bcc66892f2c44"

inherit cmake allarch

SRC_URI = "git://gitlab.com/kicad/libraries/kicad-packages3D.git;branch=v5"
SRCREV = "7abe02f30fd79b8f4f66c01589861df7f8f72f04"
PV = "5.1.7"
S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${datadir}/kicad/modules \
"
