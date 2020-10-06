SUMMARY = "The Visualization Toolkit"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=074efbe58f4b7cbec2a2f6e6bdcb31e1"

inherit cmake qemu features_check
REQUIRED_DISTRO_FEATURES = "opengl x11"

SRC_URI = " \
    https://www.vtk.org/files/release/9.0/VTK-${PV}.tar.gz \
    file://0001-Do-not-try-to-find-VTKCompileTools.patch \
"
SRC_URI[sha256sum] = "1b39a5e191c282861e7af4101eaa8585969a2de05f5646c9199a161213a622c7"
PV = "9.0.1"
LIBEXT = "9.0"
S = "${WORKDIR}/VTK-${PV}"

DEPENDS = " \
    qemu-native \
    virtual/libgl \
    lz4 \
    jsoncpp \
    expat \
    tiff \
    libeigen \
    freetype \
    zlib \
    pegtl \
    libogg \
    proj \
    pugixml \
    libxml2 \
    libpng \
    libtheora \
    glew \
    sqlite3 \
    jpeg \
"
# * libharu -> 2.4.0
# * hdf5:
#    * cmake complains for executable missing 
#    * upgrade
#    * mpich?
# * -DVTK_WRAP_PYTHON=ON  VTK::WrapPythonInit: command not found


ARCH_OECMAKE = " \
    -DH5_DISABLE_SOME_LDOUBLE_CONV_RUN__TRYRUN_OUTPUT=0 \
"
ARCH_OECMAKE_powerpc64le = " \
    -DH5_DISABLE_SOME_LDOUBLE_CONV_RUN__TRYRUN_OUTPUT=1 \
"

# stolen from meta-oe'a hdf5 and adjusted to avoid TRY_RUN
EXTRA_OECMAKE += " \
    -DVTK_PYTHON_VERSION=3 \
    -DCMAKE_REQUIRE_LARGE_FILE_SUPPORT=0 \
    -DCMAKE_REQUIRE_LARGE_FILE_SUPPORT__TRYRUN_OUTPUT=0 \
    -DTEST_LFS_WORKS_RUN=0 \
    -DTEST_LFS_WORKS_RUN__TRYRUN_OUTPUT=0 \
    -DH5_PRINTF_LL_TEST_RUN=1 \
    -DH5_PRINTF_LL_TEST_RUN__TRYRUN_OUTPUT='8' \
    -DH5_LDOUBLE_TO_LONG_SPECIAL_RUN=0 \
    -DH5_LDOUBLE_TO_LONG_SPECIAL_RUN__TRYRUN_OUTPUT= \
    -DH5_LONG_TO_LDOUBLE_SPECIAL_RUN=0 \
    -DH5_LONG_TO_LDOUBLE_SPECIAL_RUN__TRYRUN_OUTPUT= \
    -DH5_LDOUBLE_TO_LLONG_ACCURATE_RUN=0 \
    -DH5_LDOUBLE_TO_LLONG_ACCURATE_RUN__TRYRUN_OUTPUT= \
    -DH5_LLONG_TO_LDOUBLE_CORRECT_RUN=0 \
    -DH5_LLONG_TO_LDOUBLE_CORRECT_RUN__TRYRUN_OUTPUT= \
    -DH5_NO_ALIGNMENT_RESTRICTIONS_RUN=0 \
    -DH5_NO_ALIGNMENT_RESTRICTIONS_RUN__TRYRUN_OUTPUT= \
    -DH5_DISABLE_SOME_LDOUBLE_CONV_RUN=0 \
    ${ARCH_OECMAKE} \
"

EXTRA_OECMAKE += " \
    -DVTK_MODULE_USE_EXTERNAL_VTK_lz4=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_jsoncpp=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_expat=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_tiff=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_eigen=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_freetype=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_zlib=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_pegtl=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_lzma=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_ogg=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_libproj=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_pugixml=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_libxml2=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_png=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_theora=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_glew=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_sqlite=ON \
    -DVTK_MODULE_USE_EXTERNAL_VTK_jpeg=ON \
"

def qemu_run_binary_builddir(data, rootfs_path):
    libdir = rootfs_path + data.getVar("libdir")
    base_libdir = rootfs_path + data.getVar("base_libdir")
    build_libdir = data.getVar("B") + "/lib"
    return qemu_wrapper_cmdline(data, rootfs_path, [libdir, base_libdir, build_libdir])

do_configure_append() {
    # Since we cannot cannot use native vtk-compile-tools because host/target
    # cannot be expected to share same 32/64bit-ness so ajust build.ninja so
    # that tools ar run under qemu
    for qemureplace in vtkH5detect vtkH5make_libsettings vtkParseJava-${LIBEXT} vtkWrapHierarchy-${LIBEXT} vtkWrapJava-${LIBEXT} vtkWrapPython-${LIBEXT} vtkWrapPythonInit-${LIBEXT}; do
        sed -i 's|${B}/bin/'$qemureplace'|${@qemu_run_binary_builddir(d, '${STAGING_DIR_TARGET}')} ${B}/bin/'$qemureplace'|g' ${B}/build.ninja
    done
}

FILES_${PN}-doc += "${datadir}/licenses/VTK"

