SUMMARY = "Python IDE for beginners"
HOMEPAGE = "https://thonny.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=10aef48ba764d30aa5ed883b601aa250"

inherit setuptools3

PV = "3.3.14"
SRC_URI = "git://github.com/thonny/thonny.git;branch=3.3-fixes;protocol=https"
SRCREV = "ab591903b21ca323a486322eec386134477e02fb"
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
