SUMMARY = "A Cross Platform and Open Source Electronics Design Automation Suite"
LICENSE = "AGPLv3"
LIC_FILES_CHKSUM = " \
    file://LICENSE.AGPLv3;md5=eb1e647870add0502f8f010b19de32af \
    file://LICENSE.README;md5=3e1d508996685cfd58fe185c2b74d33e \
"

DEPENDS = " \
    doxygen-native \
    swig-native \
    boost \
    glew \
    glm \
    zlib \
    curl \
    cairo \
    pixman \
    boost \
    python3 \
    wxwidgets \
    opencascade \
    ngspice \
"

inherit cmake python3native features_check gtk-icon-cache mime mime-xdg

REQUIRED_DISTRO_FEATURES = "x11 opengl"

SRC_URI = " \
    git://gitlab.com/kicad/code/kicad.git;branch=5.1 \
    file://0001-Do-not-strip-executables.patch \
    file://0002-Do-not-kill-build-system-s-linker-flags.patch \
"
SRCREV = "f607e913d5e604e2a620a0f10cf38933156be63b"
PV = "5.1.9"
S = "${WORKDIR}/git"

# TODO
# spice: ngspice
# wxwidgets: native wxrc
# wxpython: ??

# | ninja: error: build.ninja:17346: multiple rules generate pcbnew/_pcbnew.so [-w dupbuild=err]
OECMAKE_GENERATOR = "Unix Makefiles"

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -DKICAD_BUILD_QA_TESTS=OFF \
    -DKICAD_SCRIPTING_PYTHON3=ON \
    -DKICAD_SCRIPTING_WXPYTHON=OFF \
    -DKICAD_SPICE=ON \
    -DKICAD_USE_OCC=ON \
    -DCMAKE_BUILD_TYPE=Release \
"

FILES_${PN} += " \
    ${datadir}/appdata \
    ${datadir}/mime \
    ${PYTHON_SITEPACKAGES_DIR} \
"

RDEPENDS_${PN} += " \
    ${PN}-symbols \
    ${PN}-footprints \
    ${PN}-packages3d \
    ${PN}-templates \
    ${PN}-i18n \
"
