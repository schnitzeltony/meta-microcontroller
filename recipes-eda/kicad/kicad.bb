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
"

inherit cmake python3native features_check gtk-icon-cache mime mime-xdg

REQUIRED_DISTRO_FEATURES = "x11 opengl"

SRC_URI = " \
    https://gitlab.com/kicad/code/kicad/-/archive/${PV}/${BPN}-${PV}.tar.gz \
    file://0001-Do-not-strip-executables.patch \
    file://0002-Do-not-kill-build-system-s-linker-flags.patch \
"
SRC_URI[sha256sum] = "96ad30aa289ed6f77ffcd8283d0877b700139187e5f1957acad8ad4dbad472bc"
PV = "5.1.7"

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
    -DKICAD_SPICE=OFF \
    -DKICAD_USE_OCC=ON \
    -DCMAKE_BUILD_TYPE=Release \
"

FILES_${PN} += " \
    ${datadir}/appdata \
    ${datadir}/mime \
    ${PYTHON_SITEPACKAGES_DIR} \
"

RDEPENDS_${PV} += " \
    ${PN}-symbols \
"
