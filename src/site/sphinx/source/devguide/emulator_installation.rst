.. _emulator_installation:

==========================
Installing Other Emulators
==========================

----------------
Samsung J2ME SDK
----------------

The samsung J2ME SDK emulator requires quicktime to play mp3/aac files. You
should install quicktime before you run the samsung J2ME SDK installer.

In the `Samsung J2ME SDK Installation Guide
<http://innovator.samsungmobile.com/cms/cnts/knowledge.detail.view.do?platformId=3&cntsId=1405>`_,
scroll down to the *Download SDK Installer* section and click on the *New
Samsung SDK* link.

----------------------------
Nokia S40 Version 5 J2ME SDK
----------------------------

When you run a Sun/Oracle JDK installer on windows, there is an option to
install a separate "public JRE." If you enable this option (the default), the
installer will install a full JDK and a separate standalone JRE. This
standalone JRE is unnecessary because the JDK already includes the full JRE.

However, the Nokia S40 SDK installer expects that you have a standalone JRE
installed. Furthermore, it will print error messages unless you specificically
have a 1.5.x JRE. You can hack around this during the installation process
by editing the windows registry to create keys for the JRE location that point
to your JDK, but you will run into problems when emulator.exe runs (from the
ant/antenna build) because the emulator will check that the directory that you
designate for the JRE in the registry actually contains the string "jre". Since
windows does not support real symbolic links, you could not merely symlink your
jdk directory to a directory called, e.g., jre1.5.0_22.

The best thing to do is to install the standalone Sun/Oracle 1.5.X JRE. A full
1.5.X JDK is not necessary.
