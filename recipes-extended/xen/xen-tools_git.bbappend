require xen-version.inc

SRC_URI += " \
    file://0001-python-pygrub-pass-DISTUTILS-xen.4.12.patch \
    "

PACKAGES += " ${PN}-lcm-tool"

FILES_${PN}-lcm-tool = "\
    ${libdir}/xen/bin/lcm-tool \
    "
