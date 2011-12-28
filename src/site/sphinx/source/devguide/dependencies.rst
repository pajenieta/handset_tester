.. _dev_dependencies:

========================================
Software Dependencies for Development
========================================

To set up a complete development toolchain for J2ME development, you should
install:

    - **the latest 1.6.X JDK** - You *MUST* install a 32 bit version because
      the Sun/Oracle WTK, samsung, and nokia J2ME emulators are only available
      in 32-bit.
    - **The Sun/Oracle Wireless Toolkit (WTK)** - Unfortunately, as of version
      3.0.x, this is only provided for Windows and Mac environments. Because
      the developers of this project primarily use GNU/linux, we standardized
      on version 2.5.2, for which there is a 32-bit linux version.
    - **Apache Ant (version 1.8.X is fine)**
    - **A 32-bit version of eclipse** *(optional)*- We've been developing with
      Eclipse IDE for Java Developers Indigo (v 3.7.1). Of course, you can use
      any other IDE or text editor. We provide some notes below on getting
      eclipse set up for j2me development.

--------------------------------------
Setting/Fixing the Path to Java in WTK
--------------------------------------

In WTK 2.5.2, on both windows and linux, the path to your
``JAVA_HOME/bin/java`` executable gets hard-coded into all the executable
wrapper scripts in ``WTK_HOME/bin``. You may need to change these paths
for any of the following reasons:

    - you install a newer JRE/JDK, which lives in a different directory
    - your default system JDK is 64-bit. WTK only works on 32-bit JREs.  On a
      64-bit system, if your JAVA_HOME points to a 64-bit JDK or you launch the
      WTK installer from a 64-bit java, the path to your 64-bit java executable
      may get hard-coded into all the WTK scripts at installation time.

~~~~~
linux
~~~~~

On linux, all the shell scripts, *e.g.*, the ``WTK_HOME/bin/emulator`` script,
set the path to your JRE/JDK bin directory like this:

.. code-block:: bash

    javapathtowtk=/opt/java/current_64/bin/

On linux, if the ``JAVA_HOME/bin`` configured in the scripts is *e.g.*,
``/opt/java/current_64/bin``, and you want to set it to
``/opt/java/current_32/bin``, you can fix this with something like:

.. code-block:: bash

    cd /path/to/your/wtk_home/bin
    find . -executable -type f \
        | xargs -L1 awk '/bin\/sh/ {if(NR>1){exit}; print FILENAME}' \
        | xargs grep -l '^javapathtowtk=' \
        | xargs sed -i '/^javapathtowtk=/ s!=/opt/java/current_64/bin/!=/opt/java/current_32/bin/!'

~~~~~~~
windows
~~~~~~~

On windows, WTK configures the path to your JDK/JRE in \*.vm files in your
``WTK_HOME/bin`` directory at installation time.

If you want to change the configured java and your desired JDK/JRE is in the
``JAVA_HOME`` variable, you can do this with the following code in bash running
from cygwin (note, ``JAVA_HOME`` must not have any trailing slashes for the
following code to work):

.. code-block:: bash

    cd /path/to/your/wtk_home/bin
    WTK_JAVA_HOME=`cygpath -d "$JAVA_HOME" |sed 's/\\\\/\\\\\\\\/g'`
    ls *.vm |xargs -L1 sed -i "s/^[^ ]*/${WTK_JAVA_HOME}\\\\bin\\\\java/"


