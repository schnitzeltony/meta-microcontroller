SUMMARY = "AVRDUDE - AVR Downloader/UploaDEr"
HOMEPAGE = "https://www.nongnu.org/avrdude/"
SECTION = "devel"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=4f51bb496ef8872ccff73f440f2464a8"

inherit autotools gettext

# Do we need libhid?
DEPENDS = " \
    bison-native \
    flex \
    elfutils \
    libusb1 \
    libftdi \
    hidapi \
"

SRC_URI = "git://github.com/schnitzeltony/avrdude.git;protocol=https;branch=master"
SRCREV = "ef94d6edce8ec2b3a853477e806f7dc8548f71d6"
S = "${WORKDIR}/git/${BPN}"
PV = "6.4"

RRECOMMENDS:${PN} += "avr-udev-rules"
