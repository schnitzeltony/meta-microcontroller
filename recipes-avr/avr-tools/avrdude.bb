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

SRC_URI = "git://github.com/schnitzeltony/avrdude.git;protocol=https"
SRCREV = "d6a0285c12ed5ec2776ddd651911ce9c3e7b4cda"
S = "${WORKDIR}/git/${BPN}"
PV = "6.3+git${SRCPV}"

RRECOMMENDS:${PN} += "avr-udev-rules"
