require recipes-extended/xen/xen.inc
require recipes-extended/xen/xen-tools.inc
require xen-version.inc

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/xen/bin/lcm-tool"
FILES_${PN}-dbg = " \
    ${libdir}/xen/bin/.debug/lcm-tool \
    ${libdir}/xen/bin/.debug \
    "

PACKAGES = "${PN} ${PN}-dbg"
PROVIDES = "${PN}"
RPROVIDES_${PN} = "${PN}"

do_compile() {
    cd ${S}
    oe_runmake build-tools-public-headers
    oe_runmake -C tools/launch
}

do_install() {
    cd ${S}
    oe_runmake DESTDIR="${D}" -C tools/launch install
}

#--- enable the native build

do_configure() {
    cd ${S}

    unset CFLAGS

    # do configure
    oe_runconf --disable-xen \
               --disable-docs \
               --disable-stubdom \
               --disable-monitors \
               --disable-ocamltools \
               --disable-xsmpolicy \
               --disable-ovmf \
               --disable-seabios \
               --disable-qemu-traditional \
               --disable-rombios \
               --disable-system-qemu \
               --disable-system-ovmf \
               --disable-system-seabios \
               --disable-ipxe \
               --disable-9pfs \
               --disable-pvshim \
	       EXTRA_CFLAGS_XEN_CORE="${EXTRA_CFLAGS_XEN_CORE}" \
               EXTRA_CFLAGS_XEN_TOOLS="${EXTRA_CFLAGS_XEN_TOOLS}" \
               PYTHON="${PYTHON}"

}

DEPENDS = " \
    autoconf-native \
    automake-native \
    libtool-native \
    gettext-native \
    iasl-native \
    glib-2.0 \
    yajl \
    zlib \
"

RDEPENDS_${PN} = ""
RRECOMMENDS_${PN} = ""
BLKTAP_PACKAGES = ""
BLKTAP_PROVIDES = ""
BLKTAP_RRECOMMENDS = ""

BBCLASSEXTEND = "native"
PACKAGECONFIG = ""
