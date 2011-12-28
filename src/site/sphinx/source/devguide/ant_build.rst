.. _ant_build:

=================
Building with Ant
=================

-----------------------------------------------
Configuring environment-specific build settings
-----------------------------------------------

Before you can build with ant, you must configure a custom build properties
file with environment-specific settings. The build is set up to look for 
a properties files in a location defined in an environment variable named::

    HANDSET_TESTER_USER_PROPERTIES_FILE

If this environment variable is undefined, the build will look for custom
properties in the file::

    src/build/user.properties

The build loads these custom user properties before the main
``project.properties`` configuration file. Because ant properties are immutable
and take the value that is specified when they are first defined, you can 
override any project property configured in ``project.properties`` in your
custom ``user.properties`` file.

There is a sample user.properties template file defined in::

    src/build/user.properties.template

You can copy this file and customize it to set the locations of your j2me
emulators and other configuration settings.

These properties should be defined in your custom user properties file:
    - **wtk home properties**:
        - ``wtk.home.sun``: the location where you installed the Sun/Oracle WTK
        - ``wtk.home.samsung``: the location of your Samsung J2ME SDK
        - ``wtk.home.nokias40``: the location of your Nokia S40 SDK

---------------------------
Notes on Ant Build Targets
---------------------------

As usual, running ``ant -p`` will print out descriptions of the most important
build targets. Run ``ant props`` to print out all the properties that are used
in your build. This can be useful to troubleshoot a misconfiguration in the
location of your environment-specific properties file.

To create packages (jar + jad files) for multiple build targets/variants, run::

    ant package:all

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Basic Emulator Execution
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

To build, package and run the MIDLet with the ``DefaultColorPhone`` device on
the basic ``sun`` wtk emulator, execute:: 

    ant run

To run the MIDLet without re-building, execute::

    ant run:midlet

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Properties that Control Emulator Behavior
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

=============== =====================================================
Property        Description
=============== =====================================================
``emulator``    the emulator that will be used with the run target
``device``      the name of the device that will be emulated
=============== =====================================================

~~~~~~~~~~~~~~~~~~~~~~~~~~~
Emulator Execution Examples
~~~~~~~~~~~~~~~~~~~~~~~~~~~

Print the names of installed emulators::

    ant list:emulators

List the devices available for, in this case, the ``samsung`` emulator::

    ant -Demulator=samsung list:devices

Run the MIDLet, emulating the ``SGH-E250`` device with the ``samsung`` emulator::

    ant -Ddevice=SGH-E250 -Demulator=samsung run:midlet

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Code Signing Properties
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

By default, the build will produce unsigned jar+jad files. If you wish to sign
the jad with a code signing certificate, you should define these properties in
your ``user.properties`` file:

============================= ==========================================================
Property                      Description
============================= ==========================================================
``sign.app``                  Defaults to *false*. Set to *true* if you want to build
                              an signed jar.

``keystore.file``             Set this to the filename of a java keystore that
                              contains your code signing certificate.

``code_signing_cert.alias``   Set this to the alias of the certificate in your java
                              keystore (equivalent to the ``-alias`` parameter
                              used by keytool).

``code_signing_key.pass``     the password of the private key in your keystore
                              (equivalent to the ``-keypass`` parameter used by
                              keytool)

``keystore.pass``             the password of your java keystore (equivalent to
                              the ``-storepass`` parameter used by keytool)
============================= ==========================================================

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Other Build Properties
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Here is a list of some other properties that you might want to customize in the
build, either by defining the property in your custom ``user.properties``
file or specifying it on the ant build command line with ``-Dprop=val``

===================== ==========================================================
Property              Description
===================== ==========================================================
``antenna.jar``       You can set this if you want to use an alternative
                      version of antenna. By default, antenna is downloaded
                      automatically using the maven ant plugin.

``wtk.proguard.home`` You can set this if you want to use an alternative
                      version of proguard. By default, proguard is downloaded
                      automatically using the maven ant plugin.
===================== ==========================================================

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Notes on Running the Emulator in \*nix
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If you are developing on linux or unix and running the WTK emulator under xorg/xwindows
with a `non-reparenting window manager
<http://en.wikipedia.org/wiki/Re-parenting_window_manager>`_ (this includes
most tiling window managers like dwm, awesome, xmonad, etc as well as barebones
window managers like twm), the tracking of mouse clicks will be messed up when
you run the emulator. To fix this, install ``wmname`` from `the suckless tools 
project <http://tools.suckless.org/>`_. On debian and some derivatives, you can
install this with the ``suckless-tools`` package.

Once you have wmname installed, use it to change the name by which your window
manager identifies itself::

    wmname LG3D

In this case, java is coded to know that ``LG3D`` is a non-reparenting WM, and
it will adjust the behavior of mouse tracking. This should then work for any
non-reparenting WM.

If you still have erratic GUI behavior in your emulator, you can try to
explicitly set the AWT_TOOLKIT that is used. Try one of these values::

    export AWT_TOOLKIT=MToolkit
    export AWT_TOOLKIT=XToolkit

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Running the Emulator in a Windows XP VM in Linux
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The Sun/Oracle WTK is the only J2ME emulator that distributes an official linux
build. While it may be possible to copy jar and configuration files from other
emulator distributions and reconfigure them to run on top of the sun WTK, the
only way to run other emulators with OOTB installation is to use windows.

If you are developing on linux, you can set up a windows XP virtualmachine
with kvm, qemu or virtualbox to run other emulators. It is possible to 
mount your project source directory from your linux host as a SMB/CIFS
network share in windows so you can run, *e.g.*, the samsung or nokias40
emulator in windows against your linux project directory.

In my setup, I use cygwin on windows to do this with the following
configuration:

    - configure your ~/.bashrc to include ``$HOME/bin`` on your path

    - install a 1.6.X JDK in your windows XP VM

    - install a 1.5.X JRE in your windows XP VM (for the nokias40 emulator)

    - in ``Control Panel -> System -> Advanced -> Environment Variables``:
        - define a ``JAVA_HOME`` environment variable to point to the windows
          path of your 1.6 JDK (*e.g.*, ``C:\opt\java\jdk1.6.0_29``)
        - add the ``bin`` directory of the jdk to the end of the windows
          ``Path`` environment variable

    - install apache ant, *e.g.*, to ``E:\opt\ant\apache-ant-1.8.2`` 

    - create a symlink in cygwin from the ``ant`` POSIX shell script in the
      ``$ANT_HOME/bin`` directory to your ``$HOME/bin``, *e.g.*,:

        .. code-block:: bash

            ln -s /cygdrive/e/opt/ant/apache-ant-1.8.2/bin/ant $HOME/bin/ant

    - configure SAMBA in linux to export your MIDLet project directory as
      a SMB/CIFS share. Example from smb.conf::

            [handset_tester]
                comment = handset_tester project
                path =  /home/you/ws/handset_tester
                browseable = yes
                read only = no
                create mask = 0700
                directory mask = 0700
                valid users = greg

    - In windows, map the ``handset_tester`` share as a network drive (*e.g.*, ``W:\``)
      so it is easy to access from cygwin as ``/cygdrive/w``

    - Define a ``HANDSET_TESTER_USER_PROPERTIES_FILE`` environment variable in
      your ``~/.bashrc`` to point to a custom ``handset_tester.properties`` file
      in your cygwin home directory (the value should be a windows path expression), *e.g.*:

        .. code-block:: bash

            export HANDSET_TESTER_USER_PROPERTIES_FILE='E:/cygwin/home/you/ws/handset_tester.properties'

Here is a sample ``handset_tester.properties``::

    wtk.home.sun=E:/opt/j2me/wtk/WTK2.5.2_01
    wtk.home.samsung=E:/opt/j2me/samsung/samsung_sdk-1.1
    wtk.home.nokias40=E:/opt/j2me/nokia/S40_5th_Edition_SDK

Now you should be able to navigate to ``/cygdrive/w`` and invoke a J2ME emulator with ant:

.. code-block:: bash

    cd /cygdrive/w
    ant -Ddevice=SGH-E250 -Demulator=samsung run:midlet


