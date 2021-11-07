SUMMARY = "A Cross Platform and Open Source Electronics Design Automation Suite"
LICENSE = "LGPLv2.1 & OCCT_LGPL_EXCEPTION"
LIC_FILES_CHKSUM = " \
    file://LICENSE_LGPL_21.txt;md5=6ab17b41640564434dda85c06b7124f7 \
    file://OCCT_LGPL_EXCEPTION.txt;md5=509623a9302457aa45a3621bf7ca591c \
"

DEPENDS = " \
    doxygen-native \
    tcl-native \
    freetype \
    tk \
    ffmpeg \
    tbb-2020 \
    vtk \
"

inherit cmake features_check

REQUIRED_DISTRO_FEATURES = "opengl x11"

SRC_URI = " \
    git://github.com/Open-Cascade-SAS/OCCT.git;branch=master;protocol=https \
    file://0001-Steal-some-fixes.patch \
"
SRCREV = "628c0211d53c7fe1036a85e7a7b2b067c9c50f7a"
PV = "7.5.0"
S = "${WORKDIR}/git"

VTKVER = "9.0"

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -D3RDPARTY_VTK_INCLUDE_DIR=${STAGING_INCDIR}/vtk-${VTKVER} \
    -D3RDPARTY_VTK_LIBRARY_DIR=${STAGING_LIBDIR} \
    -DUSE_FFMPEG=ON \
    -DUSE_TBB=ON \
    -DUSE_VTK=ON \
"

RDEPENDS:${PN} += "bash tk-lib"
