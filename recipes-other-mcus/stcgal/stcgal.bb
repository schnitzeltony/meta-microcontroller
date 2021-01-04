SUMMARY = "STC MCU ISP flash tool"
DESCRIPTION = "Command line flash programming tool for STC MCU Ltd. 8051 compatible microcontrollers"
HOMEPAGE = "https://github.com/grigorig/stcgal"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://setup.py;beginline=50;endline=50;md5=864858dc1f82ecc1b5ddaa6b82b2d207"

inherit setuptools3

SRC_URI = "git://github.com/grigorig/stcgal.git"
SRCREV = "75db65541941571751d71f74bac8e3b9c711820b"
PV = "1.6"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = "python3-pyserial python3-pyusb python3-tqdm"
