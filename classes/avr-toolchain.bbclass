# AVR toolchain to build avr projects during YOCTO build

inherit avr-toolchain-base

PACKAGE_ARCH = "all"

DEPENDS:append = " avr-libc-native"

python () {
    # slightly reduced set from allarch.bbclass

    # No need for virtual/libc or a cross compiler
    d.setVar("INHIBIT_DEFAULT_DEPS","1")

    # Set these to a common set of values, we shouldn't be using them other that for WORKDIR directory
    # naming anyway
    d.setVar("baselib", "lib")
    d.setVar("TARGET_ARCH", "allarch")
    d.setVar("TARGET_OS", "linux")
    d.setVar("TARGET_CC_ARCH", "none")
    d.setVar("TARGET_LD_ARCH", "none")
    d.setVar("TARGET_AS_ARCH", "none")
    d.setVar("TARGET_FPU", "")
    d.setVar("TARGET_PREFIX", "")
    # Expand PACKAGE_EXTRA_ARCHS since the staging code needs this
    # (this removes any dependencies from the hash perspective)
    d.setVar("PACKAGE_EXTRA_ARCHS", d.getVar("PACKAGE_EXTRA_ARCHS"))
    d.setVar("SDK_ARCH", "none")
    d.setVar("SDK_CC_ARCH", "none")
    d.setVar("TARGET_CPPFLAGS", "none")
    d.setVar("TARGET_CFLAGS", "none")
    d.setVar("TARGET_CXXFLAGS", "none")
    d.setVar("TARGET_LDFLAGS", "none")
    d.setVar("POPULATESYSROOTDEPS", "")

    # No need to do shared library processing or debug symbol handling
    d.setVar("EXCLUDE_FROM_SHLIBS", "1")
    d.setVar("INHIBIT_PACKAGE_DEBUG_SPLIT", "1")
    d.setVar("INHIBIT_PACKAGE_STRIP", "1")

    # These multilib values shouldn't change allarch packages so exclude them
    d.appendVarFlag("emit_pkgdata", "vardepsexclude", " MULTILIB_VARIANTS")
    d.appendVarFlag("write_specfile", "vardepsexclude", " MULTILIBS")
    d.appendVarFlag("do_package", "vardepsexclude", " package_do_shlibs")
}

CFLAGS = "-I${STAGING_DIR_NATIVE}${prefix}/avr/include"
LDFLAGS = "-L${STAGING_DIR_NATIVE}${prefix}/avr/lib"

INSANE_SKIP:${PN} = "arch"
