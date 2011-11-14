.. _dev_dependencies:

========================================
Software Dependencies for Development
========================================

To set up a complete development toolchain for J2ME development, you should
install:

    - **the latest 1.6.X JDK** - You *MUST* install a 32 bit version to be able to
      launch the Sun/Oracle Wireless Toolkit (WTK) emulator from your eclipse
      IDE.
    - **The Sun/Oracle Wireless Toolkit (WTK)** - Unfortunately, as of version
      3.0.x, this is only provided for Windows and Mac environments. Because
      the developers of this project primarily use GNU/linux, we standardized
      on version 2.5.2, for which there is a 32-bit linux version.
    - **A 32-bit version of eclipse** - We've been developing with Eclipse IDE for
      Java Developers Indigo (v 3.7.1).
    - **Apache Ant (version 1.8.X is fine)**
    - `Antenna 1.2.1-beta for Ant <http://antenna.sourceforge.net/>`_. Download
      the binary jar version and either copy or symlink this in your apache lib
      directory or modify your custom user.properties file to point to the location
      of your antenna jar.


TODO: document additional emulators (nokia and samsung)
