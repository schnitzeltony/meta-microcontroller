SUMMARY = "A Cross Platform and Open Source Electronics Design Automation Suite"
LICENSE = "LGPL-2.1-only & OCCT_LGPL_EXCEPTION"
LIC_FILES_CHKSUM = " \
    file://LICENSE_LGPL_21.txt;md5=6ab17b41640564434dda85c06b7124f7 \
    file://OCCT_LGPL_EXCEPTION.txt;md5=509623a9302457aa45a3621bf7ca591c \
"

DEPENDS = " \
    doxygen-native \
    tcl-native \
    freetype \
    tk \
    tbb-2020 \
    vtk \
"

inherit cmake features_check

REQUIRED_DISTRO_FEATURES = "opengl x11"

SRC_URI = " \
    git://github.com/Open-Cascade-SAS/OCCT.git;branch=OCCT-7.6;protocol=https \
    file://0001-Steal-some-fixes.patch \
"
SRCREV = "d2abb6d844231cb8f29be6894440874a4700e4a5"
PV = "7.6.1"
S = "${WORKDIR}/git"

VTKVER = "9.0"

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -D3RDPARTY_VTK_INCLUDE_DIR=${STAGING_INCDIR}/vtk-${VTKVER} \
    -D3RDPARTY_VTK_LIBRARY_DIR=${STAGING_LIBDIR} \
    -DUSE_TBB=ON \
    -DUSE_VTK=ON \
"

RDEPENDS:${PN} += "bash tk-lib"
