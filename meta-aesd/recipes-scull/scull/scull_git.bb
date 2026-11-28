# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://github.com/YassineOABA/aeld-assignment-7.git;protocol=https;branch=kernel-6.18-updates \
           file://0001-Build-only-scull-and-misc-modules.patch \
           file://scull_init \
           "

# Modify these as desired
PV = "1.0+git"
SRCREV = "18b4c53b5a30263843434ea59399730084947b1a"

inherit module update-rc.d

MODULES_MODULE_SYMVERS_LOCATION = "scull"

FILES:${PN} += "${sysconfdir}/init.d/scull_init"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "scull_init"
INITSCRIPT_PARAMS = "defaults 99"

FILESEXTRAPATHS:prepend := "${THISDIR}/../files:"
do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${UNPACKDIR}/scull_init ${D}${sysconfdir}/init.d/scull_init
}

