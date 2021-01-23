SUMMARY = "Python IDE for beginners"
HOMEPAGE = "https://thonny.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=452cb116f001f264536fa2328877a3ff"

inherit setuptools3

PV = "3.3.3"
SRC_URI = "git://github.com/thonny/thonny.git"
SRCREV = "3482d71788be724f1c78598b7e4eb164baf50206"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
    python3-jedi \
    python3-pyserial \
    python3-pylint \
    python3-docutils \
    python3-mypy \
    python3-asttokens \
    python3-send2trash \
"
