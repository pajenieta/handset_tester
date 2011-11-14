.. _ant_build:

===========================
Building with Ant + Antenna
===========================

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
custom properties file.

These properties must be defined in your custom user properties file:
    - **wtk.home** :    this must be set to the path of your WTK installation
      directory
    - **antenna.jar** : this should be set to the path where you have the antenna
      jar installed. This is optional if you copy or symlink the jar in your
      ant lib directory (or otherwise ensure that it is included in ant's
      runtime classpath)

---------------------------
Notes on Ant Build Targets
---------------------------

As usual, running ``ant -p`` will print out descriptions of the most important
build targets. Run ``ant props`` to print out all the properties that are used
in your build. This can be useful to troubleshoot a misconfiguration in the
location of your environment-specific properties file.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Running the emulator
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

To run the basic Sun/Oracle DefaultColorPhone device emulator, run::

    ant run

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Notes on Running the Emulator in \*nix
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If you are developing on linux or unix anr running the WTK emulator under xorg/xwindows
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

If you still have erratic GUI behavior in your emulator, you can try to explicitly
set the AWT_TOOLKIT that is used. Try one of these values::

    export AWT_TOOLKIT=MToolkit
    export AWT_TOOLKIT=XToolkit

