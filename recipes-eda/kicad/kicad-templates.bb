SUMMARY = "KiCad project templates (and worksheets)"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=166ee6492e38d745862bcc66892f2c44"

inherit cmake allarch

SRC_URI = "git://gitlab.com/kicad/libraries/${BPN}.git;branch=v5"
SRCREV = "1ccbaf3704e8ff4030d0915f71e051af621ef7d7"
PV = "5.1.7"
S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${datadir}/kicad/template \
"
