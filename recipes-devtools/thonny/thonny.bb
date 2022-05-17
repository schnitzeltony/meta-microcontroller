SUMMARY = "Python IDE for beginners"
HOMEPAGE = "https://thonny.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=24c5c49ff4c0975831f1876fc87e2e75"

inherit setuptools3

PV = "4.0.0~b3"
SRC_URI = "git://github.com/thonny/thonny.git;branch=master;protocol=https"
SRCREV = "c1c4ac05a808641bbb22dd96e6d6487223f00250"
S = "${WORKDIR}/git"

RDEPENDS:${PN} = " \
    python3-jedi \
    python3-pyserial \
    python3-pylint \
    python3-docutils \
    python3-mypy \
    python3-asttokens \
    python3-send2trash \
"
