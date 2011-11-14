.. _eclipse_setup:

========================================
Eclipse Project Setup
========================================

---------------------------
Eclipse Plugin Installation
---------------------------

You should install the following plugins in eclipse:

    - **Eclipse Mobilt Tools for Java (MTJ)**

---------------------------
Creating an Eclipse Project
---------------------------

With standard java projects, eclipse does a very good job of importing 
.classpath and .project files from an existing project directory. The
MTJ plugin only has partial support for importing existing project
definitions.

Still, you can generate basic eclipse project files with::

    ant eclipse:eclipse

Now in your eclipse workspace, create a new **Java ME MIDlet Project**.


In the subsequent create project dialogs, configure the following:

    - name: handset_tester
    - create project from existing source: (choose the project root directory)
    - Add a configuration to the project. Update the configuration with values for:
        - the deployment directory: target
        - the location of your antenna jar
        - your WTK root
    - Add a configuration.
        - Choose Manage Devices -> Manual Installation, and tell MTJ to search for
          devices in your WTK installation dir. It should find the *DefaultColorPhone*
          emulation device. 
        - You can call this configuration *DefaultColorPhone*.
    - In the MIDLet project properties, choose MIDP 2.0 under *MicroEdition Profile*.
    - Under the java settings tab, modify the source directory. 
        - Expand ``src``, and add ``src/main/java`` and ``src/main/resources``
          to the build path.
        - Remove ``src`` and ``res`` from the build path
        - ensure that the output folder is ``handset_tester/target/classes``

After you select *finish*, you should be able to navigate to ``Main.java`` in
the ``org.edc`` package and run it as as an **Emulated Java ME Midlet**. If you
see the emulator start up and display a phone, you're done.

*TODO*: proguard config

---------------------------
Development Recommendations
---------------------------

In general, use eclipse for its incremental compilation and to quickly make
changes and run the *DefaultColorPhone* emulator to test your code. To
package, preverify and obfuscate your build and generate the project jad file,
use the ant build script. You can run the ant build script from eclipse or
from the command line.

Using the ant build will ensure that the builds are standard and do not vary
based on IDE configuration differences.
