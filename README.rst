.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/sdk_6.0.json
   :alt: sdk_6.0 badge
   :align: left

Overview
========

This project gathers examples on how to implement mocks using the Mock Framework.

A Mock is a mockup of a board support package (BSP) capability that mimics a hardware functionality for the Simulator.

Those examples are running on Simulator only.

By default, the samples will use the `STM32F7508-DK VEE Port <https://github.com/MicroEJ/VEEPort-STMicroelectronics-STM32F7508-DK>`_.

See https://github.com/search?q=org%3AMicroEJ+VEEPort&type=repositories for the list of supported boards using MICROEJ SDK.

Each example provides a ``README.md`` that contains instructions on how to run it.

Usage
=====

Each example is composed of a Mock project (``-mock``) associated with an Application project (``-app``):

| `simple-mock <simple-mock/>`__: shows how to implement a simple mock using the Mock Framework.
| `simple-app <simple-app/>`__: application interacting with the simple-mock example.
| `custom-widgets-mock <custom-widgets-mock/>`__: shows how to implement a simple mock using the Mock Framework.
| `custom-widgets-app <custom-widgets-app/>`__: application interacting with the custom-widgets-app example.
| `file-chooser-mock <file-chooser-mock/>`__: shows how to implement a simple mock using the Mock Framework.
| `file-chooser-app <file-chooser-app/>`__: application interacting with the file-chooser-app example.

Changes
=======

See the change log file `CHANGELOG.rst <CHANGELOG.rst>`__ located at the root of this repository.

License
=======

See the license file `LICENSE.txt <LICENSE.txt>`__ located at the root of this repository.

.. ReStructuredText
.. Copyright 2024 MicroEJ Corp. All rights reserved.
.. Use of this source code is governed by a BSD-style license that can be found with this software.