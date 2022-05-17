LICENSE = "MIT & BSD-1-Clause & BSD-3-Clause & BSD-4-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b02e342e2322140ca59cf5d996e487a"

inherit pkgconfig python3native

SRC_URI = "gitsm://github.com/micropython/micropython.git;protocol=https;branch=master"
SRCREV = "da4b38e7562dfa451917f9d7f344a7f26de8c7bd"
S = "${WORKDIR}/git"
PV = "1.18"

EXTRA_OEMAKE = " \
    STRIP=echo \
    DESTDIR="${D}" \
    PREFIX="${prefix}" \
"

