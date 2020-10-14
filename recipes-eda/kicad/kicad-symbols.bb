SUMMARY = "Official KiCad schematic symbol libraries"
LICENSE = "CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=166ee6492e38d745862bcc66892f2c44"

inherit cmake allarch

SRC_URI = "git://gitlab.com/kicad/libraries/kicad-symbols.git;branch=v5"
SRCREV = "bf475af94877e8fd9cf80e667578ff61835e02bb"
PV = "5.1.7"
S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${datadir}/kicad/library \
    ${datadir}/kicad/template \
"
