SUMMARY = "Translations for KiCad source code"
# There is no license file so assume same as source code
LICENSE = "AGPLv3"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/AGPL-3.0;md5=73f1eb20517c55bf9493b7dd6e480788"

DEPENDS = "gettext-native"

inherit cmake-allarch

SRC_URI = "https://gitlab.com/kicad/code/kicad-i18n/-/archive/${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "be72fc4488d8b614b2b7997669641d0adeabe689083253b5c6bfb99853171542"
PV = "5.1.7"

# should send a patch to oe-core/package.bbclass not to hard-code locale dir...
python package_do_split_locales() {
    if (d.getVar('PACKAGE_NO_LOCALE') == '1'):
        bb.debug(1, "package requested not splitting locales")
        return

    packages = (d.getVar('PACKAGES') or "").split()

    datadir = d.getVar('datadir')
    if not datadir:
        bb.note("datadir not defined")
        return

    dvar = d.getVar('PKGD')
    pn = d.getVar('LOCALEBASEPN')

    if pn + '-locale' in packages:
        packages.remove(pn + '-locale')

    localedir = os.path.join(dvar + datadir, 'kicad', 'internat')

    if not cpath.isdir(localedir):
        bb.debug(1, "No locale files in this package")
        return

    locales = os.listdir(localedir)

    summary = d.getVar('SUMMARY') or pn
    description = d.getVar('DESCRIPTION') or ""
    locale_section = d.getVar('LOCALE_SECTION')
    mlprefix = d.getVar('MLPREFIX') or ""
    for l in sorted(locales):
        ln = legitimize_package_name(l)
        pkg = pn + '-locale-' + ln
        packages.append(pkg)
        d.setVar('FILES_' + pkg, os.path.join(datadir, 'kicad', 'internat', l))
        d.setVar('RRECOMMENDS_' + pkg, '%svirtual-locale-%s' % (mlprefix, ln))
        d.setVar('RPROVIDES_' + pkg, '%s-locale %s%s-translation' % (pn, mlprefix, ln))
        d.setVar('SUMMARY_' + pkg, '%s - %s translations' % (summary, l))
        d.setVar('DESCRIPTION_' + pkg, '%s  This package contains language translation files for the %s locale.' % (description, l))
        if locale_section:
            d.setVar('SECTION_' + pkg, locale_section)

    d.setVar('PACKAGES', ' '.join(packages))

    # Disabled by RP 18/06/07
    # Wildcards aren't supported in debian
    # They break with ipkg since glibc-locale* will mean that
    # glibc-localedata-translit* won't install as a dependency
    # for some other package which breaks meta-toolchain
    # Probably breaks since virtual-locale- isn't provided anywhere
    #rdep = (d.getVar('RDEPENDS_%s' % pn) or "").split()
    #rdep.append('%s-locale*' % pn)
    #d.setVar('RDEPENDS_%s' % pn, ' '.join(rdep))
}

ALLOW_EMPTY_${PN} = "1"
