SUMMARY = "AVRDUDE - AVR Downloader/UploaDEr"
HOMEPAGE = "https://www.nongnu.org/avrdude/"
SECTION = "devel"
LICENSE = "GPL-2.0-or-later"

LIC_FILES_CHKSUM = "file://COPYING;md5=4f51bb496ef8872ccff73f440f2464a8"

inherit cmake gettext

# Do we need libhid?
DEPENDS = " \
    bison-native \
    flex \
    elfutils \
    libusb1 \
    libftdi \
    hidapi \
"

SRC_URI = "git://github.com/avrdudes/avrdude.git;protocol=https;branch=main"
SRCREV = "4c92030e3a486cfbaeb36e298f6f2929e8e031eb"
S = "${WORKDIR}/git"
PV = "7.0+git${SRCPV}"

RRECOMMENDS:${PN} += "avr-udev-rules"
